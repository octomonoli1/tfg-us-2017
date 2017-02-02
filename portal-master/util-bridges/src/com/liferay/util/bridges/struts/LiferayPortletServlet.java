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

package com.liferay.util.bridges.struts;

import com.liferay.portal.kernel.servlet.ServletContextProvider;

import javax.servlet.ServletContext;

import org.apache.portals.bridges.struts.PortletServlet;

/**
 * @author Michael Young
 */
public class LiferayPortletServlet extends PortletServlet {

	@Override
	public ServletContext getServletContext() {
		ServletContext servletContext = super.getServletContext();

		ServletContextProvider servletContextProvider =
			(ServletContextProvider)servletContext.getAttribute(
				ServletContextProvider.STRUTS_BRIDGES_CONTEXT_PROVIDER);

		if (servletContextProvider != null) {
			servletContext = servletContextProvider.getServletContext(
				servletContext);
		}

		return servletContext;
	}

}