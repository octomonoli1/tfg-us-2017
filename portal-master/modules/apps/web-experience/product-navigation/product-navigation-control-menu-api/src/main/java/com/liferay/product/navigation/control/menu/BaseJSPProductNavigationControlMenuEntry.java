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

package com.liferay.product.navigation.control.menu;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Julio Camarero
 */
public abstract class BaseJSPProductNavigationControlMenuEntry
	extends BaseProductNavigationControlMenuEntry
	implements ProductNavigationControlMenuEntry {

	public String getBodyJspPath() {
		return StringPool.BLANK;
	}

	public abstract String getIconJspPath();

	@Override
	public String getLabel(Locale locale) {
		return null;
	}

	@Override
	public String getURL(HttpServletRequest request) {
		return null;
	}

	@Override
	public boolean includeBody(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		return include(request, response, getBodyJspPath());
	}

	@Override
	public boolean includeIcon(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		return include(request, response, getIconJspPath());
	}

	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	protected boolean include(
			HttpServletRequest request, HttpServletResponse response,
			String jspPath)
		throws IOException {

		if (Validator.isNull(jspPath)) {
			return false;
		}

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher(jspPath);

		try {
			requestDispatcher.include(request, response);
		}
		catch (ServletException se) {
			_log.error("Unable to include " + jspPath, se);

			return false;
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseJSPProductNavigationControlMenuEntry.class);

	private ServletContext _servletContext;

}