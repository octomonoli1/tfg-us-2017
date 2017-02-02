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

package com.liferay.portal.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.GroupPermission;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.service.permission.UserPermissionUtil;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
@OSGiBeanProperties(
	property = {"model.class.name=com.liferay.portal.kernel.model.Group"}
)
public class GroupPermissionImpl
	implements BaseModelPermissionChecker, GroupPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker, Group group, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, group, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, Group.class.getName(), group.getGroupId(),
				actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, Group.class.getName(), groupId, actionId);
		}
	}

	@Override
	public void check(PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, Group.class.getName(), Long.valueOf(0),
				actionId);
		}
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, primaryKey, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, Group group, String actionId)
		throws PortalException {

		if ((actionId.equals(ActionKeys.ADD_LAYOUT) ||
			 actionId.equals(ActionKeys.MANAGE_LAYOUTS)) &&
			((group.hasLocalOrRemoteStagingGroup() &&
			  PropsValues.STAGING_LIVE_GROUP_LOCKING_ENABLED) ||
			 group.isLayoutPrototype())) {

			return false;
		}

		long groupId = group.getGroupId();

		if (group.isStagingGroup()) {
			group = group.getLiveGroup();
		}

		if (group.isUser()) {

			// An individual user would never reach this block because he would
			// be an administrator of his own layouts. However, a user who
			// manages a set of organizations may be modifying pages of a user
			// he manages.

			User user = UserLocalServiceUtil.getUserById(group.getClassPK());

			if ((permissionChecker.getUserId() != user.getUserId()) &&
				UserPermissionUtil.contains(
					permissionChecker, user.getUserId(),
					user.getOrganizationIds(), ActionKeys.UPDATE)) {

				return true;
			}
		}

		if (actionId.equals(ActionKeys.ADD_COMMUNITY) &&
			(permissionChecker.hasPermission(
				groupId, Group.class.getName(), groupId,
				ActionKeys.MANAGE_SUBGROUPS) ||
			 PortalPermissionUtil.contains(
				 permissionChecker, ActionKeys.ADD_COMMUNITY))) {

			return true;
		}
		else if (actionId.equals(ActionKeys.ADD_LAYOUT) &&
				 permissionChecker.hasPermission(
					 groupId, Group.class.getName(), groupId,
					 ActionKeys.MANAGE_LAYOUTS)) {

			return true;
		}
		else if ((actionId.equals(ActionKeys.EXPORT_IMPORT_LAYOUTS) ||
				  actionId.equals(ActionKeys.EXPORT_IMPORT_PORTLET_INFO) ||
				  actionId.equals(ActionKeys.PUBLISH_PORTLET_INFO)) &&
				 permissionChecker.hasPermission(
					 groupId, Group.class.getName(), groupId,
					 ActionKeys.PUBLISH_STAGING)) {

			return true;
		}
		else if (actionId.equals(ActionKeys.VIEW) &&
				 (permissionChecker.hasPermission(
					 groupId, Group.class.getName(), groupId,
					 ActionKeys.ASSIGN_USER_ROLES) ||
				  permissionChecker.hasPermission(
					 groupId, Group.class.getName(), groupId,
					 ActionKeys.MANAGE_LAYOUTS))) {

			return true;
		}
		else if (actionId.equals(ActionKeys.VIEW_STAGING) &&
				 (permissionChecker.hasPermission(
					 groupId, Group.class.getName(), groupId,
					 ActionKeys.MANAGE_LAYOUTS) ||
				  permissionChecker.hasPermission(
					 groupId, Group.class.getName(), groupId,
					 ActionKeys.MANAGE_STAGING) ||
				  permissionChecker.hasPermission(
					 groupId, Group.class.getName(), groupId,
					 ActionKeys.PUBLISH_STAGING) ||
				  permissionChecker.hasPermission(
					 groupId, Group.class.getName(), groupId,
					 ActionKeys.UPDATE))) {

			return true;
		}

		// Group id must be set so that users can modify their personal pages

		if (permissionChecker.hasPermission(
				groupId, Group.class.getName(), groupId, actionId)) {

			return true;
		}

		while (!group.isRoot()) {
			if (contains(
					permissionChecker, group.getParentGroupId(),
					ActionKeys.MANAGE_SUBGROUPS)) {

				return true;
			}

			group = group.getParentGroup();
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

		if (groupId > 0) {
			Group group = GroupLocalServiceUtil.getGroup(groupId);

			return contains(permissionChecker, group, actionId);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, String actionId) {

		return permissionChecker.hasPermission(
			0, Group.class.getName(), Group.class.getName(), actionId);
	}

}