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

package com.liferay.portal.kernel.portlet.configuration.icon;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.collections.ServiceReferenceMapper;
import com.liferay.registry.util.StringPlus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class PortletConfigurationIconServiceReferenceMapper
	implements ServiceReferenceMapper<String, PortletConfigurationIcon> {

	@Override
	public void map(
		ServiceReference<PortletConfigurationIcon> serviceReference,
		Emitter<String> emitter) {

		String portletId = (String)serviceReference.getProperty(
			"javax.portlet.name");

		if (Validator.isNull(portletId)) {
			portletId = StringPool.STAR;
		}

		List<String> paths = StringPlus.asList(
			serviceReference.getProperty("path"));

		if (ListUtil.isEmpty(paths)) {
			paths = new ArrayList<>();

			paths.add(StringPool.DASH);
		}

		for (String path : paths) {
			emitter.emit(getKey(portletId, path));
		}
	}

	protected String getKey(String portletId, String path) {
		return portletId + StringPool.COLON + path;
	}

}