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

package com.liferay.push.notifications.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PushNotificationsDeviceService}.
 *
 * @author Bruno Farache
 * @see PushNotificationsDeviceService
 * @generated
 */
@ProviderType
public class PushNotificationsDeviceServiceWrapper
	implements PushNotificationsDeviceService,
		ServiceWrapper<PushNotificationsDeviceService> {
	public PushNotificationsDeviceServiceWrapper(
		PushNotificationsDeviceService pushNotificationsDeviceService) {
		_pushNotificationsDeviceService = pushNotificationsDeviceService;
	}

	@Override
	public com.liferay.push.notifications.model.PushNotificationsDevice addPushNotificationsDevice(
		java.lang.String token, java.lang.String platform)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushNotificationsDeviceService.addPushNotificationsDevice(token,
			platform);
	}

	@Override
	public com.liferay.push.notifications.model.PushNotificationsDevice deletePushNotificationsDevice(
		java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushNotificationsDeviceService.deletePushNotificationsDevice(token);
	}

	@Override
	public com.liferay.push.notifications.model.PushNotificationsDevice deletePushNotificationsDevice(
		long pushNotificationsDeviceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushNotificationsDeviceService.deletePushNotificationsDevice(pushNotificationsDeviceId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _pushNotificationsDeviceService.getOSGiServiceIdentifier();
	}

	@Override
	public void sendPushNotification(java.lang.String platform,
		java.util.List<java.lang.String> tokens, java.lang.String payload)
		throws com.liferay.portal.kernel.exception.PortalException {
		_pushNotificationsDeviceService.sendPushNotification(platform, tokens,
			payload);
	}

	@Override
	public void sendPushNotification(long[] toUserIds, java.lang.String payload)
		throws com.liferay.portal.kernel.exception.PortalException {
		_pushNotificationsDeviceService.sendPushNotification(toUserIds, payload);
	}

	@Override
	public PushNotificationsDeviceService getWrappedService() {
		return _pushNotificationsDeviceService;
	}

	@Override
	public void setWrappedService(
		PushNotificationsDeviceService pushNotificationsDeviceService) {
		_pushNotificationsDeviceService = pushNotificationsDeviceService;
	}

	private PushNotificationsDeviceService _pushNotificationsDeviceService;
}