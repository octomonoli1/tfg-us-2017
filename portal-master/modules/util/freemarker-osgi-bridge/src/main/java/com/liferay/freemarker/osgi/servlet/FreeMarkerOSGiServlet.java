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

package com.liferay.freemarker.osgi.servlet;

import com.liferay.freemarker.osgi.internal.BundleTemplateLoader;

import freemarker.cache.TemplateLoader;

import freemarker.ext.servlet.FreemarkerServlet;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Carlos Sierra Andrés
 */
public class FreeMarkerOSGiServlet extends FreemarkerServlet {

	@Override
	protected TemplateLoader createTemplateLoader(String templatePath) {
		Bundle bundle = FrameworkUtil.getBundle(getClass());

		return new BundleTemplateLoader(bundle);
	}

}