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

package com.liferay.portal.kernel.locale.test;

import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author André de Oliveira
 */
public class LocaleTestUtil {

	public static Map<Locale, String> getDefaultLocaleMap(String value) {
		Map<Locale, String> map = new HashMap<>();

		map.put(LocaleUtil.getSiteDefault(), value);

		return map;
	}

}