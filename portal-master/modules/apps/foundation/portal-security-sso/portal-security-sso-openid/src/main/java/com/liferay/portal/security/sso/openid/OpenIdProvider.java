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

package com.liferay.portal.security.sso.openid;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class OpenIdProvider {

	public String[] getAxSchema() {
		return _axSchema;
	}

	public Map<String, String> getAxTypes() {
		return _axTypes;
	}

	public String getName() {
		return _name;
	}

	public String getUrl() {
		return _url;
	}

	public void setAxSchema(String[] axSchema) {
		_axSchema = axSchema;
	}

	public void setAxTypes(String name, String value) {
		_axTypes.put(name, value);
	}

	public void setName(String name) {
		_name = name;
	}

	public void setUrl(String url) {
		_url = url;
	}

	private String[] _axSchema;
	private final Map<String, String> _axTypes = new HashMap<>();
	private String _name;
	private String _url;

}