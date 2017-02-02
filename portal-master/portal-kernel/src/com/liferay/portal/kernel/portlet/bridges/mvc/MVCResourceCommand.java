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

package com.liferay.portal.kernel.portlet.bridges.mvc;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * Provides an interface to allow the portlet to serve a resource. This
 * interface can only be used when the portlet is based on {@link MVCPortlet}.
 *
 * <p>
 * The resource command to be invoked is determined by two factors:
 * </p>
 *
 * <ul>
 * <li>
 * The portlet name that the resource URL is referring to.
 * </li>
 * <li>
 * The <code>ResourceID</code> of the resource request.
 * </li>
 * </ul>
 *
 * <p>
 * Implementations of this interface must be OSGi components that are registered
 * in the OSGi Registry with the following properties:
 * </p>
 *
 * <ul>
 * <li>
 * <code>javax.portlet.name</code>: The portlet name associated to this resource
 * command.
 * </li>
 * <li>
 * <code>mvc.command.name</code>: the command name that matches the
 * <code>ResourceID</code> of the resource request. This name cannot contain any
 * comma (<code>,</code>).
 * </li>
 * </ul>
 *
 * <p>
 * The method {@link MVCPortlet#serveResource(ResourceRequest,
 * ResourceResponse)} searches the OSGi Registry for the resource command that
 * matches both the resource request's portlet name with the property
 * <code>javax.portlet.name</code> and the resource request's
 * <code>ResourceID</code> with the property <code>mvc.command.name</code>.
 * </p>
 *
 * <p>
 * In general, only one resource command is executed per portlet resource URL.
 * If the ResourceID of the resource request is, however, a comma separated list
 * of multiple names, {@link MVCPortlet} finds the resource commands and invokes
 * them sequentially in the order they're specified in the list.
 * </p>
 *
 * <p>
 * When there are multiple resource commands registered for the same portlet
 * name and with the same command name, only the resource command with the
 * highest service ranking is invoked.
 * </p>
 *
 * <p>
 * {@link BaseMVCResourceCommand} is an abstract class that implements this
 * interface and can be extended to simplify using resource commands.
 * </p>
 *
 * @author Sergio González
 */
public interface MVCResourceCommand extends MVCCommand {

	public static final MVCResourceCommand EMPTY = new MVCResourceCommand() {

		@Override
		public boolean serveResource(
			ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {

			return false;
		}

	};

	/**
	 * Invoked by {@link MVCPortlet} to allow the portlet to serve a resource.
	 *
	 * @param  resourceRequest the resource request
	 * @param  resourceResponse the resource response
	 * @return <code>true</code> if an error occurs in serving the resource;
	 *         <code>false</code> otherwise
	 */
	public boolean serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException;

}