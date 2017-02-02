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

package com.liferay.portal.kernel.security.auth.session;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Tomas Polesovsky
 */
public class AuthenticatedSessionManagerUtil {

	public static AuthenticatedSessionManager getAuthenticatedSessionManager() {
		PortalRuntimePermission.checkGetBeanProperty(
			AuthenticatedSessionManagerUtil.class);

		return _instance._serviceTracker.getService();
	}

	public static long getAuthenticatedUserId(
			HttpServletRequest request, String login, String password,
			String authType)
		throws PortalException {

		return getAuthenticatedSessionManager().getAuthenticatedUserId(
			request, login, password, authType);
	}

	public static void login(
			HttpServletRequest request, HttpServletResponse response,
			String login, String password, boolean rememberMe, String authType)
		throws Exception {

		getAuthenticatedSessionManager().login(
			request, response, login, password, rememberMe, authType);
	}

	public static void logout(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		getAuthenticatedSessionManager().logout(request, response);
	}

	public static HttpSession renewSession(
			HttpServletRequest request, HttpSession session)
		throws Exception {

		return getAuthenticatedSessionManager().renewSession(request, session);
	}

	public static void signOutSimultaneousLogins(long userId) throws Exception {
		getAuthenticatedSessionManager().signOutSimultaneousLogins(userId);
	}

	private AuthenticatedSessionManagerUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			AuthenticatedSessionManager.class);

		_serviceTracker.open();
	}

	private static final AuthenticatedSessionManagerUtil _instance =
		new AuthenticatedSessionManagerUtil();

	private final ServiceTracker<?, AuthenticatedSessionManager>
		_serviceTracker;

}