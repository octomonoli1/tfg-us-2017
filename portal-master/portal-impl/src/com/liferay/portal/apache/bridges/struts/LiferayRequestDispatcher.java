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

package com.liferay.portal.apache.bridges.struts;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletApp;
import com.liferay.portal.kernel.servlet.DynamicServletRequest;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.PortletRequestImpl;
import com.liferay.portlet.PortletResponseImpl;
import com.liferay.portlet.PortletServletRequest;
import com.liferay.portlet.PortletServletResponse;
import com.liferay.taglib.servlet.PipingServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Michael Young
 * @author Brian Myunghun Kim
 * @author Brian Wing Shun Chan
 * @author Deepak Gothe
 */
public class LiferayRequestDispatcher implements RequestDispatcher {

	public LiferayRequestDispatcher(
		RequestDispatcher requestDispatcher, String path) {

		_requestDispatcher = requestDispatcher;
		_path = path;
	}

	@Override
	public void forward(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IOException, ServletException {

		PortletRequest portletRequest =
			(PortletRequest)servletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_REQUEST);

		if (portletRequest != null) {
			invoke(servletRequest, servletResponse, false);
		}
		else {
			_requestDispatcher.forward(servletRequest, servletResponse);
		}
	}

	@Override
	public void include(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IOException, ServletException {

		PortletRequest portletRequest =
			(PortletRequest)servletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_REQUEST);

		if (portletRequest != null) {
			invoke(servletRequest, servletResponse, true);
		}
		else {
			_requestDispatcher.include(servletRequest, servletResponse);
		}
	}

	public void invoke(
			ServletRequest servletRequest, ServletResponse servletResponse,
			boolean include)
		throws IOException, ServletException {

		String pathInfo = null;
		String queryString = null;
		String requestURI = null;
		String servletPath = null;

		PortletRequest portletRequest =
			(PortletRequest)servletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_REQUEST);

		PortletResponse portletResponse =
			(PortletResponse)servletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_RESPONSE);

		if (_path != null) {
			String pathNoQueryString = _path;

			int pos = _path.indexOf(CharPool.QUESTION);

			if (pos != -1) {
				pathNoQueryString = _path.substring(0, pos);
				queryString = _path.substring(pos + 1);

				servletRequest = DynamicServletRequest.addQueryString(
					(HttpServletRequest)servletRequest, queryString);
			}

			Set<String> servletURLPatterns = getServletURLPatterns(
				servletRequest, portletRequest, portletResponse);

			for (String urlPattern : servletURLPatterns) {
				if (urlPattern.endsWith("/*")) {
					pos = urlPattern.indexOf("/*");

					urlPattern = urlPattern.substring(0, pos + 1);

					if (pathNoQueryString.startsWith(urlPattern)) {
						pathInfo = pathNoQueryString.substring(
							urlPattern.length());
						servletPath = urlPattern;

						break;
					}
				}
			}

			if ((pathInfo == null) && (servletPath == null)) {
				pathInfo = StringPool.BLANK;
				servletPath = pathNoQueryString;
			}

			requestURI = portletRequest.getContextPath() + pathNoQueryString;
		}

		HttpServletRequest portletServletRequest = getPortletServletRequest(
			servletRequest, portletRequest, pathInfo, queryString, requestURI,
			servletPath, include);

		HttpServletResponse portletServletResponse = getPortletServletResponse(
			servletResponse, portletRequest, portletResponse, include);

		if (include) {
			_requestDispatcher.include(
				portletServletRequest, portletServletResponse);
		}
		else {
			_requestDispatcher.forward(
				portletServletRequest, portletServletResponse);
		}
	}

	protected HttpServletRequest getPortletServletRequest(
		ServletRequest servletRequest, PortletRequest portletRequest,
		String pathInfo, String queryString, String requestURI,
		String servletPath, boolean include) {

		HttpServletRequest request = (HttpServletRequest)servletRequest;
		boolean named = false;

		PortletRequestImpl portletRequestImpl =
			PortletRequestImpl.getPortletRequestImpl(portletRequest);

		return new PortletServletRequest(
			request, portletRequestImpl, pathInfo, queryString, requestURI,
			servletPath, named, include);
	}

	protected HttpServletResponse getPortletServletResponse(
			ServletResponse servletResponse, PortletRequest portletRequest,
			PortletResponse portletResponse, boolean include)
		throws IOException {

		HttpServletResponse response = (HttpServletResponse)servletResponse;

		PortletResponseImpl portletResponseImpl =
			(PortletResponseImpl)portletResponse;

		HttpServletResponse httpServletResponse = new PortletServletResponse(
			response, portletResponseImpl, include);

		PrintWriter printWriter = servletResponse.getWriter();

		if (printWriter != null) {
			httpServletResponse = new PipingServletResponse(
				httpServletResponse, printWriter);
		}

		return httpServletResponse;
	}

	protected Set<String> getServletURLPatterns(
		ServletRequest servletRequest, PortletRequest portletRequest,
		PortletResponse portletResponse) {

		PortletRequestImpl portletRequestImpl =
			PortletRequestImpl.getPortletRequestImpl(portletRequest);

		Portlet portlet = portletRequestImpl.getPortlet();

		PortletApp portletApp = portlet.getPortletApp();

		return portletApp.getServletURLPatterns();
	}

	private final String _path;
	private final RequestDispatcher _requestDispatcher;

}