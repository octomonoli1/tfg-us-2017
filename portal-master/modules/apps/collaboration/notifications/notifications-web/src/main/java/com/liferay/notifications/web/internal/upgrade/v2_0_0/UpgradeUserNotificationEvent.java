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

package com.liferay.notifications.web.internal.upgrade.v2_0_0;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Sergio González
 * @author Roberto Díaz
 */
public class UpgradeUserNotificationEvent extends UpgradeProcess {

	public UpgradeUserNotificationEvent(
		UserNotificationEventLocalService userNotificationEventLocalService) {

		_userNotificationEventLocalService = userNotificationEventLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasTable("Notifications_UserNotificationEvent")) {
			return;
		}

		updateUserNotificationEvents();
	}

	protected void updateUserNotificationEvents() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"select userNotificationEventId from " +
					"Notifications_UserNotificationEvent");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long userNotificationEventId = rs.getLong(
					"userNotificationEventId");

				UserNotificationEvent userNotificationEvent =
					_userNotificationEventLocalService.getUserNotificationEvent(
						userNotificationEventId);

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
					userNotificationEvent.getPayload());

				userNotificationEvent.setDeliveryType(
					UserNotificationDeliveryConstants.TYPE_WEBSITE);
				userNotificationEvent.setDelivered(true);

				boolean actionRequired = jsonObject.getBoolean(
					"actionRequired");

				jsonObject.remove("actionRequired");

				userNotificationEvent.setPayload(jsonObject.toString());

				userNotificationEvent.setActionRequired(actionRequired);

				_userNotificationEventLocalService.updateUserNotificationEvent(
					userNotificationEvent);
			}
		}
	}

	private final UserNotificationEventLocalService
		_userNotificationEventLocalService;

}