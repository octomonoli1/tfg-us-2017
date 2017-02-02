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

package com.liferay.push.notifications.exception;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Bruno Farache
 */
@ProviderType
public class PushNotificationsException extends PortalException {

	public PushNotificationsException() {
	}

	public PushNotificationsException(String msg) {
		super(msg);
	}

	public PushNotificationsException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public PushNotificationsException(Throwable cause) {
		super(cause);
	}

}