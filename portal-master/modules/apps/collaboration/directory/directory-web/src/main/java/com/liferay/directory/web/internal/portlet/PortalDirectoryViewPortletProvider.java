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

package com.liferay.directory.web.internal.portlet;

import com.liferay.admin.kernel.util.PortalDirectoryApplicationType;
import com.liferay.directory.web.internal.constants.DirectoryPortletKeys;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.ViewPortletProvider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pei-Jung Lan
 */
@Component(
	immediate = true,
	property = {
		"model.class.name=" + PortalDirectoryApplicationType.PortalDirectory.CLASS_NAME
	},
	service = ViewPortletProvider.class
)
public class PortalDirectoryViewPortletProvider
	extends BasePortletProvider implements ViewPortletProvider {

	@Override
	public String getPortletName() {
		return DirectoryPortletKeys.DIRECTORY;
	}

}