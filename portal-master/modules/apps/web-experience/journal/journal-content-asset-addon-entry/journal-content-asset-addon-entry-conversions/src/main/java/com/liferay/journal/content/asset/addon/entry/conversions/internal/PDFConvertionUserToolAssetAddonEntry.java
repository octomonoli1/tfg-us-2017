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

package com.liferay.journal.content.asset.addon.entry.conversions.internal;

import com.liferay.journal.content.asset.addon.entry.common.UserToolAssetAddonEntry;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 */
@Component(immediate = true, service = UserToolAssetAddonEntry.class)
public class PDFConvertionUserToolAssetAddonEntry
	extends BaseConvertionUserToolAssetAddonEntry
	implements UserToolAssetAddonEntry {

	@Override
	public String getExtension() {
		return "pdf";
	}

	@Override
	public String getIcon() {
		return "file-text";
	}

	@Override
	public String getKey() {
		return "enablePDF";
	}

	@Override
	public Double getWeight() {
		return 3.0;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.journal.content.asset.addon.entry.conversions)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

}