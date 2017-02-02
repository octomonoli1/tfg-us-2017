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

package com.liferay.portal.servlet.filters.compoundsessionid;

import com.liferay.portal.kernel.servlet.WrapHttpServletRequestFilter;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * See https://issues.liferay.com/browse/LPS-18587.
 * </p>
 *
 * @author Michael C. Han
 */
public class CompoundSessionIdFilter
	extends BasePortalFilter implements WrapHttpServletRequestFilter {

	public CompoundSessionIdFilter() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			CompoundSessionIdServletRequestFactory.class);

		_serviceTracker.open();
	}

	@Override
	public void destroy() {
		_serviceTracker.close();

		super.destroy();
	}

	@Override
	public HttpServletRequest getWrappedHttpServletRequest(
		HttpServletRequest request, HttpServletResponse response) {

		CompoundSessionIdServletRequestFactory
			compoundSessionIdServletRequestFactory =
				_serviceTracker.getService();

		if (compoundSessionIdServletRequestFactory != null) {
			return compoundSessionIdServletRequestFactory.create(request);
		}

		return request;
	}

	@Override
	public boolean isFilterEnabled() {
		if (_serviceTracker.isEmpty()) {
			return false;
		}

		return true;
	}

	private final ServiceTracker
		<CompoundSessionIdServletRequestFactory,
			CompoundSessionIdServletRequestFactory> _serviceTracker;

}