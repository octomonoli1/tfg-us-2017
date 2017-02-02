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

package com.liferay.map;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jürgen Kappler
 */
public abstract class BaseJSPMapProvider implements MapProvider {

	public abstract String getConfigurationJspPath();

	public abstract String getJspPath();

	@Override
	public boolean include(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		return includeJSP(request, response, getJspPath());
	}

	@Override
	public boolean includeConfiguration(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		return includeJSP(request, response, getConfigurationJspPath());
	}

	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	protected boolean includeJSP(
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
		BaseJSPMapProvider.class);

	private ServletContext _servletContext;

}