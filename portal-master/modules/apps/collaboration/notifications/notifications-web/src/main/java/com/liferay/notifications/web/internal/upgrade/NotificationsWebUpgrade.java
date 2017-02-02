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

package com.liferay.notifications.web.internal.upgrade;

import com.liferay.notifications.web.internal.constants.NotificationsPortletKeys;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.kernel.upgrade.BaseReplacePortletId;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 * @author Roberto Díaz
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class NotificationsWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.notifications.web", "0.0.0", "2.1.0",
			new DummyUpgradeStep());

		registry.register(
			"com.liferay.notifications.web", "0.0.1", "1.0.0",
			new com.liferay.notifications.web.internal.upgrade.v1_0_0.
				UpgradeUserNotificationEvent(
					_userNotificationEventLocalService));

		registry.register(
			"com.liferay.notifications.web", "1.0.0", "1.3.0",
			new DummyUpgradeStep());

		registry.register(
			"com.liferay.notifications.web", "1.1.0", "1.3.0",
			new DummyUpgradeStep());

		registry.register(
			"com.liferay.notifications.web", "1.2.0", "1.3.0",
			new DummyUpgradeStep());

		registry.register(
			"com.liferay.notifications.web", "1.3.0", "2.0.0",
			new com.liferay.notifications.web.internal.upgrade.v2_0_0.
				UpgradeUserNotificationEvent(
					_userNotificationEventLocalService));

		UpgradeStep upgradePortletId = new BaseReplacePortletId() {

			@Override
			protected String[][] getRenamePortletIdsArray() {
				return new String[][] {
					{
						"1_WAR_notificationsportlet",
						NotificationsPortletKeys.NOTIFICATIONS
					},
					{
						"2_WAR_notificationsportlet",
						NotificationsPortletKeys.NOTIFICATIONS
					}
				};
			}

		};

		registry.register(
			"com.liferay.notifications.web", "2.0.0", "2.1.0",
			new com.liferay.notifications.web.internal.upgrade.v2_1_0.
				UpgradeUserNotificationEvent(
					_userNotificationEventLocalService),
			upgradePortletId);
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(
		UserNotificationEventLocalService userNotificationEventLocalService) {

		_userNotificationEventLocalService = userNotificationEventLocalService;
	}

	private UserNotificationEventLocalService
		_userNotificationEventLocalService;

}