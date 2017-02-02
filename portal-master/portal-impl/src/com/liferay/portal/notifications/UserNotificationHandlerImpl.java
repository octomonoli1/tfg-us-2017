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

package com.liferay.portal.notifications;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.notifications.UserNotificationFeedEntry;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Jonathan Lee
 */
public class UserNotificationHandlerImpl implements UserNotificationHandler {

	public UserNotificationHandlerImpl(
		UserNotificationHandler userNotificationHandler) {

		_userNotificationHandler = userNotificationHandler;
	}

	@Override
	public String getPortletId() {
		return _userNotificationHandler.getPortletId();
	}

	@Override
	public String getSelector() {
		return _userNotificationHandler.getSelector();
	}

	@Override
	public UserNotificationFeedEntry interpret(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws PortalException {

		return _userNotificationHandler.interpret(
			userNotificationEvent, serviceContext);
	}

	@Override
	public boolean isDeliver(
			long userId, long classNameId, int notificationType,
			int deliveryType, ServiceContext serviceContext)
		throws PortalException {

		return _userNotificationHandler.isDeliver(
			userId, classNameId, notificationType, deliveryType,
			serviceContext);
	}

	@Override
	public boolean isOpenDialog() {
		return _userNotificationHandler.isOpenDialog();
	}

	private final UserNotificationHandler _userNotificationHandler;

}