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

package com.liferay.portal.spring.aop;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Shuyang Zhou
 */
public class ServiceBeanAopCacheManagerUtil {

	public static void registerServiceBeanAopCacheManager(
		ServiceBeanAopCacheManager serviceBeanAopCacheManager) {

		_serviceBeanAopCacheManagers.add(serviceBeanAopCacheManager);
	}

	public static void reset() {
		for (ServiceBeanAopCacheManager serviceBeanAopCacheManager :
				_serviceBeanAopCacheManagers) {

			serviceBeanAopCacheManager.reset();
		}
	}

	public static void unregisterServiceBeanAopCacheManager(
		ServiceBeanAopCacheManager serviceBeanAopCacheManager) {

		_serviceBeanAopCacheManagers.remove(serviceBeanAopCacheManager);
	}

	private static final List<ServiceBeanAopCacheManager>
		_serviceBeanAopCacheManagers = new CopyOnWriteArrayList<>();

}