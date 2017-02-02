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

package com.liferay.portal.kernel.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Function;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author André de Oliveira
 */
public class ServiceContextFunction
	implements Function<String, ServiceContext> {

	public ServiceContextFunction(HttpServletRequest request) {
		_request = request;

		_portletRequest = null;
	}

	public ServiceContextFunction(PortletRequest portletRequest) {
		_portletRequest = portletRequest;

		_request = null;
	}

	@Override
	public ServiceContext apply(String className) {
		try {
			if (_portletRequest != null) {
				return ServiceContextFactory.getInstance(
					className, _portletRequest);
			}
			else {
				return ServiceContextFactory.getInstance(className, _request);
			}
		}
		catch (PortalException pe) {
			throw new RuntimeException(pe);
		}
	}

	private final PortletRequest _portletRequest;
	private final HttpServletRequest _request;

}