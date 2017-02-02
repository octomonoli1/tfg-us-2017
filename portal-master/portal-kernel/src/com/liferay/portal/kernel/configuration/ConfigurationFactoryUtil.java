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

package com.liferay.portal.kernel.configuration;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class ConfigurationFactoryUtil {

	public static Configuration getConfiguration(
		ClassLoader classLoader, String name) {

		return getConfigurationFactory().getConfiguration(classLoader, name);
	}

	public static ConfigurationFactory getConfigurationFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			ConfigurationFactoryUtil.class);

		return _configurationFactory;
	}

	public static void setConfigurationFactory(
		ConfigurationFactory configurationFactory) {

		PortalRuntimePermission.checkSetBeanProperty(
			ConfigurationFactoryUtil.class);

		_configurationFactory = configurationFactory;
	}

	private static ConfigurationFactory _configurationFactory;

}