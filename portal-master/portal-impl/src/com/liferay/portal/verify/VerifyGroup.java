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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.GroupFriendlyURLException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.model.UserGroupGroupRole;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.service.impl.GroupLocalServiceImpl;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.RobotsUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class VerifyGroup extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		verifyCompanyGroups();
		verifyNullFriendlyURLGroups();
		verifyOrganizationNames();
		verifyRobots();
		verifySites();
		verifyStagedGroups();
		verifyTree();
	}

	protected String getRobots(LayoutSet layoutSet) {
		if (layoutSet == null) {
			return RobotsUtil.getDefaultRobots(null);
		}

		String virtualHostname = StringPool.BLANK;

		try {
			virtualHostname = layoutSet.getVirtualHostname();
		}
		catch (Exception e) {
		}

		return GetterUtil.get(
			layoutSet.getSettingsProperty(
				layoutSet.isPrivateLayout() + "-robots.txt"),
			RobotsUtil.getDefaultRobots(virtualHostname));
	}

	protected void verifyCompanyGroups() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<Company> companies = CompanyLocalServiceUtil.getCompanies();

			for (Company company : companies) {
				GroupLocalServiceUtil.checkCompanyGroup(company.getCompanyId());

				GroupLocalServiceUtil.checkSystemGroups(company.getCompanyId());
			}
		}
	}

	protected void verifyNullFriendlyURLGroups() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<Group> groups =
				GroupLocalServiceUtil.getNullFriendlyURLGroups();

			for (Group group : groups) {
				String friendlyURL = StringPool.SLASH + group.getGroupId();

				User user = null;

				if (group.isCompany() && !group.isCompanyStagingGroup()) {
					friendlyURL = GroupConstants.GLOBAL_FRIENDLY_URL;
				}
				else if (group.isUser()) {
					user = UserLocalServiceUtil.getUserById(group.getClassPK());

					friendlyURL = StringPool.SLASH + user.getScreenName();
				}
				else if (group.getClassPK() > 0) {
					friendlyURL = StringPool.SLASH + group.getClassPK();
				}

				try {
					GroupLocalServiceUtil.updateFriendlyURL(
						group.getGroupId(), friendlyURL);
				}
				catch (GroupFriendlyURLException gfurle) {
					if (user != null) {
						long userId = user.getUserId();
						String screenName = user.getScreenName();

						if (_log.isWarnEnabled()) {
							_log.warn(
								"Updating user screen name " + screenName +
									" to " + userId + " because it is " +
									"generating an invalid friendly URL " +
									friendlyURL);
						}

						UserLocalServiceUtil.updateScreenName(
							userId, String.valueOf(userId));
					}
					else {
						_log.error("Invalid Friendly URL " + friendlyURL);

						throw gfurle;
					}
				}
			}
		}
	}

	protected void verifyOrganizationNames() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			StringBundler sb = new StringBundler(5);

			sb.append("select groupId, name from Group_ where name like '%");
			sb.append(GroupLocalServiceImpl.ORGANIZATION_NAME_SUFFIX);
			sb.append("%' and name not like '%");
			sb.append(GroupLocalServiceImpl.ORGANIZATION_NAME_SUFFIX);
			sb.append("'");

			try (PreparedStatement ps1 = connection.prepareStatement(
					sb.toString());
				PreparedStatement ps2 =
					AutoBatchPreparedStatementUtil.concurrentAutoBatch(
						connection,
						"update Group_ set name = ? where groupId = ?");
				ResultSet rs = ps1.executeQuery()) {

				while (rs.next()) {
					long groupId = rs.getLong("groupId");
					String name = rs.getString("name");

					if (name.endsWith(
							GroupLocalServiceImpl.ORGANIZATION_NAME_SUFFIX) ||
						name.endsWith(
							GroupLocalServiceImpl.
								ORGANIZATION_STAGING_SUFFIX)) {

						continue;
					}

					int pos = name.indexOf(
						GroupLocalServiceImpl.ORGANIZATION_NAME_SUFFIX);

					pos = name.indexOf(" ", pos + 1);

					String newName =
						name.substring(pos + 1) +
							GroupLocalServiceImpl.ORGANIZATION_NAME_SUFFIX;

					ps2.setString(1, newName);
					ps2.setLong(2, groupId);

					ps2.addBatch();
				}

				ps2.executeBatch();
			}
		}
	}

	protected void verifyRobots() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<Group> groups = GroupLocalServiceUtil.getLiveGroups();

			for (Group group : groups) {
				LayoutSet privateLayoutSet = group.getPrivateLayoutSet();
				LayoutSet publicLayoutSet = group.getPublicLayoutSet();

				String privateLayoutSetRobots = getRobots(privateLayoutSet);
				String publicLayoutSetRobots = getRobots(publicLayoutSet);

				UnicodeProperties typeSettingsProperties =
					group.getTypeSettingsProperties();

				typeSettingsProperties.setProperty(
					"true-robots.txt", privateLayoutSetRobots);
				typeSettingsProperties.setProperty(
					"false-robots.txt", publicLayoutSetRobots);

				GroupLocalServiceUtil.updateGroup(
					group.getGroupId(), typeSettingsProperties.toString());
			}
		}
	}

	protected void verifySites() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			ActionableDynamicQuery actionableDynamicQuery =
				GroupLocalServiceUtil.getActionableDynamicQuery();

			actionableDynamicQuery.setAddCriteriaMethod(
				new ActionableDynamicQuery.AddCriteriaMethod() {

					@Override
					public void addCriteria(DynamicQuery dynamicQuery) {
						dynamicQuery.add(
							RestrictionsFactoryUtil.eq(
								"classNameId",
								PortalUtil.getClassNameId(Organization.class)));
						dynamicQuery.add(
							RestrictionsFactoryUtil.eq("site", false));
					}

				});
			actionableDynamicQuery.setParallel(true);
			actionableDynamicQuery.setPerformActionMethod(
				new ActionableDynamicQuery.PerformActionMethod<Group>() {

					@Override
					public void performAction(Group group) {
						if ((group.getPrivateLayoutsPageCount() > 0) ||
							(group.getPublicLayoutsPageCount() > 0)) {

							group.setSite(true);

							GroupLocalServiceUtil.updateGroup(group);
						}
					}

				});

			actionableDynamicQuery.performActions();
		}
	}

	protected void verifyStagedGroups() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<Group> groups = GroupLocalServiceUtil.getLiveGroups();

			for (Group group : groups) {
				if (!group.hasStagingGroup()) {
					continue;
				}

				UnicodeProperties typeSettingsProperties =
					group.getTypeSettingsProperties();

				typeSettingsProperties.setProperty(
					"staged", Boolean.TRUE.toString());
				typeSettingsProperties.setProperty(
					"stagedRemotely", Boolean.FALSE.toString());

				verifyStagingTypeSettingsProperties(typeSettingsProperties);

				GroupLocalServiceUtil.updateGroup(
					group.getGroupId(), typeSettingsProperties.toString());

				Group stagingGroup = group.getStagingGroup();

				if (group.getClassNameId() != stagingGroup.getClassNameId()) {
					stagingGroup.setClassNameId(group.getClassNameId());

					GroupLocalServiceUtil.updateGroup(stagingGroup);
				}

				if (!stagingGroup.isStagedRemotely()) {
					verifyStagingGroupOrganizationMembership(stagingGroup);
					verifyStagingGroupRoleMembership(stagingGroup);
					verifyStagingGroupUserGroupMembership(stagingGroup);
					verifyStagingGroupUserMembership(stagingGroup);
					verifyStagingUserGroupRolesAssignments(stagingGroup);
					verifyStagingUserGroupGroupRolesAssignments(stagingGroup);
				}
			}
		}
	}

	protected void verifyStagingGroupOrganizationMembership(Group stagingGroup)
		throws Exception {

		List<Organization> stagingOrganizations =
			OrganizationLocalServiceUtil.getGroupOrganizations(
				stagingGroup.getGroupId());

		if (ListUtil.isEmpty(stagingOrganizations)) {
			return;
		}

		List<Organization> liveOrganizations =
			OrganizationLocalServiceUtil.getGroupOrganizations(
				stagingGroup.getLiveGroupId());

		for (Organization stagingGroupOrganization : stagingOrganizations) {
			if (!liveOrganizations.contains(stagingGroupOrganization)) {
				OrganizationLocalServiceUtil.addGroupOrganization(
					stagingGroup.getLiveGroupId(), stagingGroupOrganization);
			}
		}

		OrganizationLocalServiceUtil.clearGroupOrganizations(
			stagingGroup.getGroupId());
	}

	protected void verifyStagingGroupRoleMembership(Group stagingGroup) {
		List<Role> stagingRoles = RoleLocalServiceUtil.getGroupRoles(
			stagingGroup.getGroupId());

		if (ListUtil.isEmpty(stagingRoles)) {
			return;
		}

		List<Role> liveRoles = RoleLocalServiceUtil.getGroupRoles(
			stagingGroup.getLiveGroupId());

		for (Role stagingRole : stagingRoles) {
			if (!liveRoles.contains(stagingRole)) {
				RoleLocalServiceUtil.addGroupRole(
					stagingGroup.getLiveGroupId(), stagingRole);
			}
		}

		RoleLocalServiceUtil.clearGroupRoles(stagingGroup.getGroupId());
	}

	protected void verifyStagingGroupUserGroupMembership(Group stagingGroup) {
		List<UserGroup> stagingUserGroups =
			UserGroupLocalServiceUtil.getGroupUserGroups(
				stagingGroup.getGroupId());

		if (ListUtil.isEmpty(stagingUserGroups)) {
			return;
		}

		List<UserGroup> liveUserGroups =
			UserGroupLocalServiceUtil.getGroupUserGroups(
				stagingGroup.getLiveGroupId());

		for (UserGroup stagingUserGroup : stagingUserGroups) {
			if (!liveUserGroups.contains(stagingUserGroup)) {
				UserGroupLocalServiceUtil.addGroupUserGroup(
					stagingGroup.getLiveGroupId(), stagingUserGroup);
			}
		}

		UserGroupLocalServiceUtil.clearGroupUserGroups(
			stagingGroup.getGroupId());
	}

	protected void verifyStagingGroupUserMembership(Group stagingGroup) {
		List<User> stagingGroupUsers = UserLocalServiceUtil.getGroupUsers(
			stagingGroup.getGroupId());

		if (ListUtil.isEmpty(stagingGroupUsers)) {
			return;
		}

		List<User> liveGroupUsers = UserLocalServiceUtil.getGroupUsers(
			stagingGroup.getLiveGroupId());

		for (User stagingGroupUser : stagingGroupUsers) {
			if (!liveGroupUsers.contains(stagingGroupUser)) {
				UserLocalServiceUtil.addGroupUser(
					stagingGroup.getLiveGroupId(), stagingGroupUser);
			}
		}

		UserLocalServiceUtil.clearGroupUsers(stagingGroup.getGroupId());
	}

	protected void verifyStagingTypeSettingsProperties(
		UnicodeProperties typeSettingsProperties) {

		Set<String> keys = typeSettingsProperties.keySet();

		Iterator<String> iterator = keys.iterator();

		while (iterator.hasNext()) {
			String key = iterator.next();

			if (ArrayUtil.contains(
					_LEGACY_STAGED_PORTLET_TYPE_SETTINGS_KEYS, key)) {

				if (_log.isInfoEnabled()) {
					_log.info("Removing type settings property " + key);
				}

				iterator.remove();
			}
		}
	}

	protected void verifyStagingUserGroupGroupRolesAssignments(
		Group stagingGroup) {

		DynamicQuery dynamicQuery =
			UserGroupGroupRoleLocalServiceUtil.dynamicQuery();

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"id.groupId", stagingGroup.getGroupId()));

		List<UserGroupGroupRole> stagingUserGroupGroupRoles =
			UserGroupGroupRoleLocalServiceUtil.dynamicQuery(dynamicQuery);

		if (stagingUserGroupGroupRoles.isEmpty()) {
			return;
		}

		dynamicQuery = UserGroupGroupRoleLocalServiceUtil.dynamicQuery();

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"id.groupId", stagingGroup.getLiveGroupId()));

		List<UserGroupGroupRole> liveUserGroupGroupRoles =
			UserGroupGroupRoleLocalServiceUtil.dynamicQuery(dynamicQuery);

		for (UserGroupGroupRole userGroupGroupRole :
				stagingUserGroupGroupRoles) {

			userGroupGroupRole.setGroupId(stagingGroup.getLiveGroupId());

			if (!liveUserGroupGroupRoles.contains(userGroupGroupRole)) {
				UserGroupGroupRoleLocalServiceUtil.updateUserGroupGroupRole(
					userGroupGroupRole);
			}
		}

		UserGroupGroupRoleLocalServiceUtil.deleteUserGroupGroupRolesByGroupId(
			stagingGroup.getGroupId());
	}

	protected void verifyStagingUserGroupRolesAssignments(Group stagingGroup) {
		List<UserGroupRole> stagingUserGroupRoles =
			UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroup(
				stagingGroup.getGroupId());

		if (ListUtil.isEmpty(stagingUserGroupRoles)) {
			return;
		}

		List<UserGroupRole> liveUserGroupRoles =
			UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroup(
				stagingGroup.getLiveGroupId());

		for (UserGroupRole stagingUserGroupRole : stagingUserGroupRoles) {
			stagingUserGroupRole.setGroupId(stagingGroup.getLiveGroupId());

			if (!liveUserGroupRoles.contains(stagingUserGroupRole)) {
				UserGroupRoleLocalServiceUtil.updateUserGroupRole(
					stagingUserGroupRole);
			}
		}

		UserGroupRoleLocalServiceUtil.deleteUserGroupRolesByGroupId(
			stagingGroup.getGroupId());
	}

	protected void verifyTree() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			long[] companyIds = PortalInstances.getCompanyIdsBySQL();

			for (long companyId : companyIds) {
				GroupLocalServiceUtil.rebuildTree(companyId);
			}
		}
	}

	private static final String[] _LEGACY_STAGED_PORTLET_TYPE_SETTINGS_KEYS = {
		"staged-portlet_39", "staged-portlet_54", "staged-portlet_56",
		"staged-portlet_59", "staged-portlet_107", "staged-portlet_108",
		"staged-portlet_110", "staged-portlet_166", "staged-portlet_169"
	};

	private static final Log _log = LogFactoryUtil.getLog(VerifyGroup.class);

}