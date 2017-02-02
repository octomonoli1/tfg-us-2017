/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.internal;

import com.liferay.portal.kernel.exception.NoSuchResourceException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchPermissionChecker;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.UserBag;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceBlockLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Allen Chiang
 * @author Bruno Farache
 * @author Raymond Augé
 * @author Amos Fong
 * @author Preston Crary
 */
@Component(immediate = true, service = SearchPermissionChecker.class)
public class SearchPermissionCheckerImpl implements SearchPermissionChecker {

	@Override
	public void addPermissionFields(long companyId, Document document) {
		try {
			long groupId = GetterUtil.getLong(document.get(Field.GROUP_ID));

			String className = document.get(Field.ENTRY_CLASS_NAME);
			String classPK = document.get(Field.ENTRY_CLASS_PK);

			if (Validator.isNull(className) && Validator.isNull(classPK)) {
				className = document.get(Field.ROOT_ENTRY_CLASS_NAME);
				classPK = document.get(Field.ROOT_ENTRY_CLASS_PK);
			}

			boolean relatedEntry = GetterUtil.getBoolean(
				document.get(Field.RELATED_ENTRY));

			if (relatedEntry) {
				long classNameId = GetterUtil.getLong(
					document.get(Field.CLASS_NAME_ID));

				className = _portal.getClassName(classNameId);
				classPK = document.get(Field.CLASS_PK);
			}

			if (Validator.isNull(className) || Validator.isNull(classPK)) {
				return;
			}

			Indexer<?> indexer = _indexerRegistry.nullSafeGetIndexer(className);

			if (!indexer.isPermissionAware()) {
				return;
			}

			String viewActionId = document.get(Field.VIEW_ACTION_ID);

			if (Validator.isNull(viewActionId)) {
				viewActionId = ActionKeys.VIEW;
			}

			doAddPermissionFields_6(
				companyId, groupId, className, classPK, viewActionId, document);
		}
		catch (NoSuchResourceException nsre) {
			if (_log.isDebugEnabled()) {
				_log.debug(nsre, nsre);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public BooleanFilter getPermissionBooleanFilter(
		long companyId, long[] groupIds, long userId, String className,
		BooleanFilter booleanFilter, SearchContext searchContext) {

		try {
			booleanFilter = doGetPermissionBooleanFilter(
				companyId, groupIds, userId, className, booleanFilter,
				searchContext);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return booleanFilter;
	}

	@Override
	public void updatePermissionFields(
		String resourceName, String resourceClassPK) {

		try {
			doUpdatePermissionFields(resourceName, resourceClassPK);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void doAddPermissionFields_6(
			long companyId, long groupId, String className, String classPK,
			String viewActionId, Document doc)
		throws Exception {

		List<Role> roles = null;

		if (_resourceBlockLocalService.isSupported(className)) {
			roles = _resourceBlockLocalService.getRoles(
				className, Long.valueOf(classPK), viewActionId);
		}
		else {
			roles = _resourcePermissionLocalService.getRoles(
				companyId, className, ResourceConstants.SCOPE_INDIVIDUAL,
				classPK, viewActionId);
		}

		if (roles.isEmpty()) {
			return;
		}

		List<Long> roleIds = new ArrayList<>();
		List<String> groupRoleIds = new ArrayList<>();

		for (Role role : roles) {
			if ((role.getType() == RoleConstants.TYPE_ORGANIZATION) ||
				(role.getType() == RoleConstants.TYPE_SITE)) {

				groupRoleIds.add(groupId + StringPool.DASH + role.getRoleId());
			}
			else {
				roleIds.add(role.getRoleId());
			}
		}

		doc.addKeyword(
			Field.ROLE_ID, roleIds.toArray(new Long[roleIds.size()]));
		doc.addKeyword(
			Field.GROUP_ROLE_ID,
			groupRoleIds.toArray(new String[groupRoleIds.size()]));
	}

	protected BooleanFilter doGetPermissionBooleanFilter(
			long companyId, long[] searchGroupIds, long userId,
			String className, BooleanFilter booleanFilter,
			SearchContext searchContext)
		throws Exception {

		Indexer<?> indexer = _indexerRegistry.getIndexer(className);

		if (!indexer.isPermissionAware()) {
			return booleanFilter;
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		User user = permissionChecker.getUser();

		if ((user == null) || (user.getUserId() != userId)) {
			user = _userLocalService.fetchUser(userId);

			if (user == null) {
				return booleanFilter;
			}

			permissionChecker = PermissionCheckerFactoryUtil.create(user);
		}

		if (permissionChecker.getUserBag() == null) {
			return booleanFilter;
		}

		if (permissionChecker.isCompanyAdmin(companyId)) {
			return booleanFilter;
		}

		Set<Role> roles = new HashSet<>();
		Map<Long, List<Role>> usersGroupIdsToRoles = new HashMap<>();

		populate(
			companyId, userId, permissionChecker, roles, usersGroupIdsToRoles);

		return doGetPermissionFilter_6(
			companyId, searchGroupIds, userId, permissionChecker, className,
			booleanFilter, roles, usersGroupIdsToRoles);
	}

	protected BooleanFilter doGetPermissionFilter_6(
			long companyId, long[] searchGroupIds, long userId,
			PermissionChecker permissionChecker, String className,
			BooleanFilter booleanFilter, Set<Role> roles,
			Map<Long, List<Role>> usersGroupIdsToRoles)
		throws Exception {

		BooleanFilter permissionBooleanFilter = new BooleanFilter();

		if (userId > 0) {
			permissionBooleanFilter.addTerm(Field.USER_ID, userId);
		}

		TermsFilter groupsTermsFilter = new TermsFilter(Field.GROUP_ID);
		TermsFilter groupRolesTermsFilter = new TermsFilter(
			Field.GROUP_ROLE_ID);
		TermsFilter rolesTermsFilter = new TermsFilter(Field.ROLE_ID);

		List<Long> roleIds = new ArrayList<>(roles.size());
		List<Long> regularRoleIds = new ArrayList<>();

		for (Role role : roles) {
			roleIds.add(role.getRoleId());

			if (role.getType() == RoleConstants.TYPE_REGULAR) {
				regularRoleIds.add(role.getRoleId());
			}

			rolesTermsFilter.addValue(String.valueOf(role.getRoleId()));
		}

		long[] roleIdsArray = ArrayUtil.toLongArray(roleIds);

		if (_resourcePermissionLocalService.hasResourcePermission(
				companyId, className, ResourceConstants.SCOPE_COMPANY,
				String.valueOf(companyId), roleIdsArray, ActionKeys.VIEW)) {

			return booleanFilter;
		}

		if (_resourcePermissionLocalService.hasResourcePermission(
				companyId, className, ResourceConstants.SCOPE_GROUP_TEMPLATE,
				String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID),
				ArrayUtil.toLongArray(regularRoleIds), ActionKeys.VIEW)) {

			return booleanFilter;
		}

		for (Map.Entry<Long, List<Role>> entry :
				usersGroupIdsToRoles.entrySet()) {

			long groupId = entry.getKey();
			List<Role> groupRoles = entry.getValue();

			if (permissionChecker.isGroupAdmin(groupId) ||
				_resourcePermissionLocalService.hasResourcePermission(
					companyId, className, ResourceConstants.SCOPE_GROUP,
					String.valueOf(groupId), roleIdsArray, ActionKeys.VIEW) ||
				_resourcePermissionLocalService.hasResourcePermission(
					companyId, className,
					ResourceConstants.SCOPE_GROUP_TEMPLATE,
					String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID),
					ListUtil.toLongArray(groupRoles, Role.ROLE_ID_ACCESSOR),
					ActionKeys.VIEW)) {

				groupsTermsFilter.addValue(String.valueOf(groupId));
			}

			for (Role groupRole : groupRoles) {
				groupRolesTermsFilter.addValue(
					groupId + StringPool.DASH + groupRole.getRoleId());
			}
		}

		if (ArrayUtil.isNotEmpty(searchGroupIds)) {
			Set<Long> usersGroupIds = usersGroupIdsToRoles.keySet();

			for (long searchGroupId : searchGroupIds) {
				if (!usersGroupIds.contains(searchGroupId) &&
					_resourcePermissionLocalService.hasResourcePermission(
						companyId, className, ResourceConstants.SCOPE_GROUP,
						String.valueOf(searchGroupId), roleIdsArray,
						ActionKeys.VIEW)) {

					groupsTermsFilter.addValue(String.valueOf(searchGroupId));
				}
			}
		}

		if (!groupsTermsFilter.isEmpty()) {
			permissionBooleanFilter.add(groupsTermsFilter);
		}

		if (!groupRolesTermsFilter.isEmpty()) {
			permissionBooleanFilter.add(groupRolesTermsFilter);
		}

		if (!rolesTermsFilter.isEmpty()) {
			permissionBooleanFilter.add(rolesTermsFilter);
		}

		if (!permissionBooleanFilter.hasClauses()) {
			return booleanFilter;
		}

		BooleanFilter fullBooleanFilter = new BooleanFilter();

		if ((booleanFilter != null) && booleanFilter.hasClauses()) {
			fullBooleanFilter.add(booleanFilter, BooleanClauseOccur.MUST);
		}

		fullBooleanFilter.add(permissionBooleanFilter, BooleanClauseOccur.MUST);

		return fullBooleanFilter;
	}

	protected void doUpdatePermissionFields(
			String resourceName, String resourceClassPK)
		throws Exception {

		Indexer<?> indexer = _indexerRegistry.nullSafeGetIndexer(resourceName);

		indexer.reindex(resourceName, GetterUtil.getLong(resourceClassPK));
	}

	protected void populate(
			long companyId, long userId, PermissionChecker permissionChecker,
			Set<Role> roles, Map<Long, List<Role>> usersGroupIdsToRoles)
		throws Exception {

		UserBag userBag = permissionChecker.getUserBag();

		if (permissionChecker.isSignedIn()) {
			roles.addAll(userBag.getRoles());

			roles.add(
				_roleLocalService.getRole(companyId, RoleConstants.GUEST));
		}
		else {
			Group guestGroup = _groupLocalService.getGroup(
				companyId, GroupConstants.GUEST);

			roles.addAll(
				_roleLocalService.getUserRelatedRoles(
					userId, Collections.singletonList(guestGroup)));
		}

		Role organizationUserRole = _roleLocalService.getRole(
			companyId, RoleConstants.ORGANIZATION_USER);
		Role siteMemberRole = _roleLocalService.getRole(
			companyId, RoleConstants.SITE_MEMBER);

		for (Group group : userBag.getGroups()) {
			long[] roleIds = permissionChecker.getRoleIds(
				userId, group.getGroupId());

			List<Role> groupRoles = _roleLocalService.getRoles(roleIds);

			roles.addAll(groupRoles);

			Iterator<Role> iterator = groupRoles.iterator();

			while (iterator.hasNext()) {
				Role groupRole = iterator.next();

				if ((groupRole.getType() != RoleConstants.TYPE_ORGANIZATION) &&
					(groupRole.getType() != RoleConstants.TYPE_SITE)) {

					iterator.remove();
				}
			}

			if (group.isOrganization() &&
				!groupRoles.contains(organizationUserRole)) {

				groupRoles.add(organizationUserRole);
			}

			if (group.isSite() && !groupRoles.contains(siteMemberRole)) {
				groupRoles.add(siteMemberRole);
			}

			usersGroupIdsToRoles.put(group.getGroupId(), groupRoles);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SearchPermissionCheckerImpl.class);

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private IndexerRegistry _indexerRegistry;

	@Reference
	private Portal _portal;

	@Reference
	private ResourceBlockLocalService _resourceBlockLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}