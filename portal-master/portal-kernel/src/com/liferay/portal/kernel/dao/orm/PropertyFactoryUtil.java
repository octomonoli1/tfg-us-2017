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

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class PropertyFactoryUtil {

	public static Property forName(String propertyName) {
		return getPropertyFactory().forName(propertyName);
	}

	public static PropertyFactory getPropertyFactory() {
		PortalRuntimePermission.checkGetBeanProperty(PropertyFactoryUtil.class);

		return _projectionFactory;
	}

	public void setPropertyFactory(PropertyFactory projectionFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_projectionFactory = projectionFactory;
	}

	private static PropertyFactory _projectionFactory;

}