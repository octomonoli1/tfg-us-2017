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

package com.liferay.journal.content.asset.addon.entry.related.assets.internal;

import com.liferay.journal.content.asset.addon.entry.common.ContentMetadataAssetAddonEntry;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPAssetAddonEntry;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 */
@Component(immediate = true, service = ContentMetadataAssetAddonEntry.class)
public class RelatedAssetsContentMetadataAssetAddonEntry
	extends BaseJSPAssetAddonEntry implements ContentMetadataAssetAddonEntry {

	@Override
	public String getIcon() {
		return "file";
	}

	@Override
	public String getJspPath() {
		return "/related_assets.jsp";
	}

	@Override
	public String getKey() {
		return "enableRelatedAssets";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "related-assets");
	}

	@Override
	public Double getWeight() {
		return 1.0;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.journal.content.asset.addon.entry.related.assets)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

}