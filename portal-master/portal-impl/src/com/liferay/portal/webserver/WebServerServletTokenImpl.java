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

package com.liferay.portal.webserver;

import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.webserver.WebServerServletToken;
import com.liferay.portal.servlet.filters.cache.CacheUtil;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.dependency.ServiceDependencyListener;
import com.liferay.registry.dependency.ServiceDependencyManager;

/**
 * @author Brian Wing Shun Chan
 * @since  6.1, replaced com.liferay.portal.servlet.ImageServletTokenImpl
 */
@DoPrivileged
public class WebServerServletTokenImpl implements WebServerServletToken {

	public void afterPropertiesSet() {
		ServiceDependencyManager serviceDependencyManager =
			new ServiceDependencyManager();

		serviceDependencyManager.addServiceDependencyListener(
			new ServiceDependencyListener() {

				@Override
				public void dependenciesFulfilled() {
					Registry registry = RegistryUtil.getRegistry();

					MultiVMPool multiVMPool = registry.getService(
						MultiVMPool.class);

					_portalCache =
						(PortalCache<Long, String>)multiVMPool.getPortalCache(
							_CACHE_NAME);
				}

				@Override
				public void destroy() {
				}

			});

		serviceDependencyManager.registerDependencies(MultiVMPool.class);
	}

	@Override
	public String getToken(long imageId) {
		Long key = imageId;

		String token = _portalCache.get(key);

		if (token == null) {
			token = _createToken();

			_portalCache.put(key, token);
		}

		return token;
	}

	@Override
	public void resetToken(long imageId) {
		_portalCache.remove(imageId);

		// Layout cache

		CacheUtil.clearCache();
	}

	private String _createToken() {
		return String.valueOf(System.currentTimeMillis());
	}

	private static final String _CACHE_NAME =
		WebServerServletToken.class.getName();

	private PortalCache<Long, String> _portalCache;

}