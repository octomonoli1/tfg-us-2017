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

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.UserBag;
import com.liferay.portal.kernel.security.permission.UserBagFactory;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.util.PropsValues;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Preston Crary
 */
public class UserBagFactoryImpl implements UserBagFactory {

	@Override
	public UserBag create(long userId) throws PortalException {
		UserBag userBag = PermissionCacheUtil.getUserBag(userId);

		if (userBag != null) {
			return userBag;
		}

		try {
			List<Group> userGroups = GroupLocalServiceUtil.getUserGroups(
				userId, true);

			Collection<Organization> userOrgs = getUserOrgs(userId);

			Set<Group> userOrgGroups = new HashSet<>(userOrgs.size());

			for (Organization organization : userOrgs) {
				userOrgGroups.add(organization.getGroup());
			}

			if (userGroups.isEmpty()) {
				long[] userRoleIds = UserLocalServiceUtil.getRolePrimaryKeys(
					userId);

				userBag = new UserBagImpl(
					userId, userGroups, userOrgs, userOrgGroups, userRoleIds);
			}
			else {
				List<Role> userRoles = RoleLocalServiceUtil.getUserRelatedRoles(
					userId, userGroups);

				userBag = new UserBagImpl(
					userId, userGroups, userOrgs, userOrgGroups, userRoles);
			}

			PermissionCacheUtil.putUserBag(userId, userBag);

			return userBag;
		}
		catch (Exception e) {
			PermissionCacheUtil.removeUserBag(userId);

			throw e;
		}
	}

	protected Collection<Organization> getUserOrgs(long userId)
		throws PortalException {

		List<Organization> userOrgs =
			OrganizationLocalServiceUtil.getUserOrganizations(userId);

		if (userOrgs.isEmpty()) {
			return Collections.emptyList();
		}

		if (PropsValues.ORGANIZATIONS_MEMBERSHIP_STRICT) {
			return userOrgs;
		}

		Set<Organization> organizations = new LinkedHashSet<>();

		for (Organization organization : userOrgs) {
			if (organizations.add(organization)) {
				List<Organization> ancestorOrganizations =
					OrganizationLocalServiceUtil.getParentOrganizations(
						organization.getOrganizationId());

				organizations.addAll(ancestorOrganizations);
			}
		}

		return organizations;
	}

}