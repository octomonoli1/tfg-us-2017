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

package com.liferay.portal.util;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.openid.OpenId;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

/**
 * @author Jorge Ferrer
 */
@ProviderType
public class OpenIdUtil {

	public static boolean isEnabled(long companyId) {
		return getOpenId().isEnabled(companyId);
	}

	protected static OpenId getOpenId() {
		return _instance._serviceTracker.getService();
	}

	private OpenIdUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(OpenId.class);

		_serviceTracker.open();
	}

	private static final OpenIdUtil _instance = new OpenIdUtil();

	private final ServiceTracker<OpenId, OpenId> _serviceTracker;

}