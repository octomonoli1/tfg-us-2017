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

package com.liferay.journal.content.asset.addon.entry.print.internal;

import com.liferay.journal.content.asset.addon.entry.common.UserToolAssetAddonEntry;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPAssetAddonEntry;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 */
@Component(immediate = true, service = UserToolAssetAddonEntry.class)
public class PrintUserToolAssetAddonEntry
	extends BaseJSPAssetAddonEntry implements UserToolAssetAddonEntry {

	@Override
	public String getIcon() {
		return "print";
	}

	@Override
	public String getJspPath() {
		return "/print.jsp";
	}

	@Override
	public String getKey() {
		return "enablePrint";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "print");
	}

	@Override
	public Double getWeight() {
		return 2.0;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.journal.content.asset.addon.entry.print)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

}