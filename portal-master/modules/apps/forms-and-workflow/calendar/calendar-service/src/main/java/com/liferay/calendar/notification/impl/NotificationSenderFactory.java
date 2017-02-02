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

package com.liferay.calendar.notification.impl;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.Map;

/**
 * @author Eduardo Lundgren
 */
public class NotificationSenderFactory {

	public static NotificationSender getNotificationSender(
			String notificationType)
		throws PortalException {

		NotificationSender notificationSender = _notificationSenders.get(
			notificationType);

		if (notificationSender == null) {
			throw new PortalException(
				"Invalid notification type " + notificationType);
		}

		return notificationSender;
	}

	public void setNotificationSenders(
		Map<String, NotificationSender> notificationSenders) {

		_notificationSenders = notificationSenders;
	}

	private static Map<String, NotificationSender> _notificationSenders;

}