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

package com.liferay.portal.workflow.kaleo.runtime.internal.notification.recipient;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupGroupRole;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.workflow.kaleo.definition.NotificationReceptionType;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationRecipient;
import com.liferay.portal.workflow.kaleo.runtime.notification.recipient.NotificationRecipientBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = {"recipient.type=ROLE"},
	service = {
		NotificationRecipientBuilder.class,
		RoleNotificationRecipientBuilder.class
	}
)
public class RoleNotificationRecipientBuilder
	implements NotificationRecipientBuilder {

	@Override
	public void processKaleoNotificationRecipient(
			Set<NotificationRecipient> notificationRecipients,
			KaleoNotificationRecipient kaleoNotificationRecipient,
			NotificationReceptionType notificationReceptionType,
			ExecutionContext executionContext)
		throws Exception {

		addRoleRecipientAddresses(
			notificationRecipients,
			kaleoNotificationRecipient.getRecipientClassPK(),
			kaleoNotificationRecipient.getRecipientRoleType(),
			notificationReceptionType, executionContext);
	}

	@Override
	public void processKaleoTaskAssignmentInstance(
			Set<NotificationRecipient> notificationRecipients,
			KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
			NotificationReceptionType notificationReceptionType,
			ExecutionContext executionContext)
		throws Exception {

		long roleId = kaleoTaskAssignmentInstance.getAssigneeClassPK();

		Role role = _roleLocalService.getRole(roleId);

		addRoleRecipientAddresses(
			notificationRecipients, roleId, role.getType(),
			notificationReceptionType, executionContext);
	}

	protected void addRoleRecipientAddresses(
			Set<NotificationRecipient> notificationRecipients, long roleId,
			int roleType, NotificationReceptionType notificationReceptionType,
			ExecutionContext executionContext)
		throws Exception {

		List<User> users = getRoleUsers(roleId, roleType, executionContext);

		for (User user : users) {
			if (user.isActive()) {
				NotificationRecipient notificationRecipient =
					new NotificationRecipient(user, notificationReceptionType);

				notificationRecipients.add(notificationRecipient);
			}
		}
	}

	protected List<User> getRoleUsers(
			long roleId, int roleType, ExecutionContext executionContext)
		throws Exception {

		if (roleType == RoleConstants.TYPE_REGULAR) {
			return _userLocalService.getInheritedRoleUsers(
				roleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		}

		List<User> users = new ArrayList<>();

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		List<UserGroupRole> userGroupRoles =
			_userGroupRoleLocalService.getUserGroupRolesByGroupAndRole(
				kaleoInstanceToken.getGroupId(), roleId);

		for (UserGroupRole userGroupRole : userGroupRoles) {
			users.add(userGroupRole.getUser());
		}

		List<UserGroupGroupRole> userGroupGroupRoles =
			_userGroupGroupRoleLocalService.
				getUserGroupGroupRolesByGroupAndRole(
					kaleoInstanceToken.getGroupId(), roleId);

		for (UserGroupGroupRole userGroupGroupRole : userGroupGroupRoles) {
			users.addAll(
				_userLocalService.getUserGroupUsers(
					userGroupGroupRole.getUserGroupId()));
		}

		return users;
	}

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserGroupGroupRoleLocalService _userGroupGroupRoleLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}