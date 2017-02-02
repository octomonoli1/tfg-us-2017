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

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.url.URLContainer;

import java.net.URL;

import java.security.Permission;
import java.security.Policy;

import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public interface PACLPolicy {

	public ClassLoader getClassLoader();

	public String getContextName();

	public Policy getPolicy();

	public Properties getProperties();

	public String getProperty(String key);

	public String[] getPropertyArray(String key);

	public boolean getPropertyBoolean(String key);

	public Set<String> getPropertySet(String key);

	public URLContainer getURLContainer();

	public List<URL> getURLs();

	public boolean hasJNDI(String name);

	public boolean hasSQL(String sql);

	public boolean implies(Permission permission);

	public boolean isActive();

	public boolean isCheckablePermission(Permission permission);

}