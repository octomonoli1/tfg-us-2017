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

import com.liferay.portal.osgi.web.servlet.context.helper.definition.ListenerDefinition;

import java.util.List;

import javax.servlet.ServletContext;

import org.osgi.framework.Bundle;

/**
 * @author Raymond Augé
 */
public interface ModifiableServletContext {

	public Bundle getBundle();

	public List<ListenerDefinition> getListenerDefinitions();

	public ServletContext getWrappedServletContext();

	public void registerFilters();

	public void registerServlets();

}