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

package com.liferay.announcements.kernel.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.kernel.service.permission.RolePermissionUtil;
import com.liferay.portal.kernel.service.permission.UserGroupPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Raymond Augé
 */
public class AnnouncementsUtil {

	public static LinkedHashMap<Long, long[]> getAnnouncementScopes(long userId)
		throws PortalException {

		LinkedHashMap<Long, long[]> scopes = new LinkedHashMap<>();

		// General announcements

		scopes.put(Long.valueOf(0), new long[] {0});

		// Personal announcements

		scopes.put(_USER_CLASS_NAME_ID, new long[] {userId});

		// Organization announcements

		List<Group> groupsList = new ArrayList<>();

		List<Organization> organizations =
			OrganizationLocalServiceUtil.getUserOrganizations(userId);

		if (!organizations.isEmpty()) {
			List<Organization> organizationsList = new ArrayList<>();

			organizationsList.addAll(organizations);

			for (Organization organization : organizations) {
				groupsList.add(organization.getGroup());

				List<Organization> parentOrganizations =
					OrganizationLocalServiceUtil.getParentOrganizations(
						organization.getOrganizationId());

				for (Organization parentOrganization : parentOrganizations) {
					organizationsList.add(parentOrganization);
					groupsList.add(parentOrganization.getGroup());
				}
			}

			scopes.put(
				_ORGANIZATION_CLASS_NAME_ID,
				_getOrganizationIds(organizationsList));
		}

		// Site announcements

		List<Group> groups = GroupLocalServiceUtil.getUserGroups(userId, true);

		if (!groups.isEmpty()) {
			scopes.put(_GROUP_CLASS_NAME_ID, _getGroupIds(groups));

			groupsList.addAll(groups);
		}

		// User group announcements

		List<UserGroup> userGroups =
			UserGroupLocalServiceUtil.getUserUserGroups(userId);

		if (!userGroups.isEmpty()) {
			scopes.put(_USER_GROUP_CLASS_NAME_ID, _getUserGroupIds(userGroups));

			for (UserGroup userGroup : userGroups) {
				groupsList.add(userGroup.getGroup());
			}
		}

		// Role announcements

		Set<Role> roles = new LinkedHashSet<>();

		if (!groupsList.isEmpty()) {
			roles.addAll(
				RoleLocalServiceUtil.getUserRelatedRoles(userId, groupsList));

			for (Group group : groupsList) {
				roles.addAll(
					RoleLocalServiceUtil.getUserGroupRoles(
						userId, group.getGroupId()));
				roles.addAll(
					RoleLocalServiceUtil.getUserGroupGroupRoles(
						userId, group.getGroupId()));
			}
		}
		else {
			roles.addAll(RoleLocalServiceUtil.getUserRoles(userId));
		}

		List<Team> teams = TeamLocalServiceUtil.getUserTeams(userId);

		for (Team team : teams) {
			roles.add(team.getRole());
		}

		if (_PERMISSIONS_CHECK_GUEST_ENABLED) {
			User user = UserLocalServiceUtil.getUserById(userId);

			Role guestRole = RoleLocalServiceUtil.getRole(
				user.getCompanyId(), RoleConstants.GUEST);

			roles.add(guestRole);
		}

		if (!roles.isEmpty()) {
			scopes.put(_ROLE_CLASS_NAME_ID, _getRoleIds(roles));
		}

		return scopes;
	}

	public static List<Group> getGroups(ThemeDisplay themeDisplay)
		throws Exception {

		List<Group> filteredGroups = new ArrayList<>();

		List<Group> groups = GroupLocalServiceUtil.getUserGroups(
			themeDisplay.getUserId(), true);

		for (Group group : groups) {
			if (((group.isOrganization() && group.isSite()) ||
				 group.isRegularSite()) &&
				GroupPermissionUtil.contains(
					themeDisplay.getPermissionChecker(), group.getGroupId(),
					ActionKeys.MANAGE_ANNOUNCEMENTS)) {

				filteredGroups.add(group);
			}
		}

		return filteredGroups;
	}

	public static List<Organization> getOrganizations(ThemeDisplay themeDisplay)
		throws Exception {

		List<Organization> filteredOrganizations = new ArrayList<>();

		List<Organization> organizations =
			OrganizationLocalServiceUtil.getUserOrganizations(
				themeDisplay.getUserId());

		for (Organization organization : organizations) {
			if (OrganizationPermissionUtil.contains(
					themeDisplay.getPermissionChecker(),
					organization.getOrganizationId(),
					ActionKeys.MANAGE_ANNOUNCEMENTS)) {

				filteredOrganizations.add(organization);
			}
		}

		return filteredOrganizations;
	}

	public static List<Role> getRoles(ThemeDisplay themeDisplay)
		throws Exception {

		List<Role> filteredRoles = new ArrayList<>();

		List<Role> roles = RoleLocalServiceUtil.getRoles(
			themeDisplay.getCompanyId());

		for (Role role : roles) {
			if (role.isTeam()) {
				Team team = TeamLocalServiceUtil.getTeam(role.getClassPK());

				if (GroupPermissionUtil.contains(
						themeDisplay.getPermissionChecker(), team.getGroupId(),
						ActionKeys.MANAGE_ANNOUNCEMENTS) ||
					RolePermissionUtil.contains(
						themeDisplay.getPermissionChecker(),
						themeDisplay.getScopeGroupId(), role.getRoleId(),
						ActionKeys.MANAGE_ANNOUNCEMENTS)) {

					filteredRoles.add(role);
				}
			}
			else if (RolePermissionUtil.contains(
						themeDisplay.getPermissionChecker(), role.getRoleId(),
						ActionKeys.MANAGE_ANNOUNCEMENTS)) {

				filteredRoles.add(role);
			}
		}

		return filteredRoles;
	}

	public static List<UserGroup> getUserGroups(ThemeDisplay themeDisplay)
		throws Exception {

		List<UserGroup> filteredUserGroups = new ArrayList<>();

		List<UserGroup> userGroups = UserGroupLocalServiceUtil.getUserGroups(
			themeDisplay.getCompanyId());

		for (UserGroup userGroup : userGroups) {
			if (UserGroupPermissionUtil.contains(
					themeDisplay.getPermissionChecker(),
					userGroup.getUserGroupId(),
					ActionKeys.MANAGE_ANNOUNCEMENTS)) {

				filteredUserGroups.add(userGroup);
			}
		}

		return filteredUserGroups;
	}

	private static long[] _getGroupIds(List<Group> groups) {
		long[] groupIds = new long[groups.size()];

		int i = 0;

		for (Group group : groups) {
			groupIds[i++] = group.getGroupId();
		}

		return groupIds;
	}

	private static long[] _getOrganizationIds(
		List<Organization> organizations) {

		long[] organizationIds = new long[organizations.size()];

		int i = 0;

		for (Organization organization : organizations) {
			organizationIds[i++] = organization.getOrganizationId();
		}

		return organizationIds;
	}

	private static long[] _getRoleIds(Set<Role> roles) {
		long[] roleIds = new long[roles.size()];

		int i = 0;

		for (Role role : roles) {
			roleIds[i++] = role.getRoleId();
		}

		return roleIds;
	}

	private static long[] _getUserGroupIds(List<UserGroup> userGroups) {
		long[] userGroupIds = new long[userGroups.size()];

		int i = 0;

		for (UserGroup userGroup : userGroups) {
			userGroupIds[i++] = userGroup.getUserGroupId();
		}

		return userGroupIds;
	}

	private static final long _GROUP_CLASS_NAME_ID = PortalUtil.getClassNameId(
		Group.class.getName());

	private static final long _ORGANIZATION_CLASS_NAME_ID =
		PortalUtil.getClassNameId(Organization.class.getName());

	private static final boolean _PERMISSIONS_CHECK_GUEST_ENABLED =
		GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.PERMISSIONS_CHECK_GUEST_ENABLED));

	private static final long _ROLE_CLASS_NAME_ID = PortalUtil.getClassNameId(
		Role.class.getName());

	private static final long _USER_CLASS_NAME_ID = PortalUtil.getClassNameId(
		User.class.getName());

	private static final long _USER_GROUP_CLASS_NAME_ID =
		PortalUtil.getClassNameId(UserGroup.class.getName());

}