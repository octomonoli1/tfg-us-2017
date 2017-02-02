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

package com.liferay.portal.osgi.web.wab.extender.internal.adapter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Raymond Augé
 */
public class ServletContextListenerExceptionAdapter
	implements ServletContextListener {

	public ServletContextListenerExceptionAdapter(
		ServletContextListener servletContextListener,
		ServletContext servletContext) {

		_servletContextListener = servletContextListener;
		_servletContext = servletContext;
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		try {
			_servletContextListener.contextDestroyed(
				new ServletContextEvent(_servletContext));
		}
		catch (Exception e) {
			_exception = e;
		}
	}

	@Override
	public void contextInitialized(
		final ServletContextEvent servletContextEvent) {

		try {
			_servletContextListener.contextInitialized(
				new ServletContextEvent(_servletContext));
		}
		catch (Exception e) {
			_exception = e;
		}
	}

	public Exception getException() {
		return _exception;
	}

	private Exception _exception;
	private final ServletContext _servletContext;
	private final ServletContextListener _servletContextListener;

}