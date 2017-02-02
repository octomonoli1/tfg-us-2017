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

package com.liferay.asset.publisher.web.internal.configuration;

import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;

/**
 * @author Eudaldo Alonso
 */
public class AssetPublisherWebConfigurationUtil {

	public static String get(String key) {
		return _configuration.get(key);
	}

	public static String[] getArray(String key) {
		return _configuration.getArray(key);
	}

	private static final Configuration _configuration =
		ConfigurationFactoryUtil.getConfiguration(
			AssetPublisherWebConfigurationUtil.class.getClassLoader(),
			"portlet");

}