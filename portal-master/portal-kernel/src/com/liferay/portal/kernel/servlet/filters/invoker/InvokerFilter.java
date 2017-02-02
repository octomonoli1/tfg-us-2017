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

package com.liferay.portal.kernel.servlet.filters.invoker;

import com.liferay.portal.kernel.cache.key.CacheKeyGenerator;
import com.liferay.portal.kernel.cache.key.CacheKeyGeneratorUtil;
import com.liferay.portal.kernel.concurrent.ConcurrentLFUCache;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpOnlyCookieServletResponse;
import com.liferay.portal.kernel.servlet.NonSerializableObjectRequestWrapper;
import com.liferay.portal.kernel.servlet.SanitizedServletResponse;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class InvokerFilter extends BasePortalLifecycle implements Filter {

	@Override
	public void destroy() {
		portalDestroy();
	}

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)servletRequest;

		String uri = getURI(request);

		HttpServletResponse response = (HttpServletResponse)servletResponse;

		String requestURL = getURL(request);

		if (requestURL.length() > _invokerFilterURIMaxLength) {
			response.sendError(HttpServletResponse.SC_REQUEST_URI_TOO_LONG);

			if (_log.isWarnEnabled()) {
				StringBundler sb = new StringBundler(5);

				sb.append("Rejected ");
				sb.append(StringUtil.shorten(uri, _invokerFilterURIMaxLength));
				sb.append(" because it has more than ");
				sb.append(_invokerFilterURIMaxLength);
				sb.append(" characters");

				_log.warn(sb.toString());
			}

			return;
		}

		request = handleNonSerializableRequest(request);

		response =
			HttpOnlyCookieServletResponse.getHttpOnlyCookieServletResponse(
				response);

		response = secureResponseHeaders(request, response);

		request.setAttribute(WebKeys.INVOKER_FILTER_URI, uri);

		try {
			InvokerFilterChain invokerFilterChain = getInvokerFilterChain(
				request, uri, filterChain);

			Thread currentThread = Thread.currentThread();

			ClassLoader contextClassLoader =
				currentThread.getContextClassLoader();

			invokerFilterChain.setContextClassLoader(contextClassLoader);

			invokerFilterChain.doFilter(request, response);
		}
		finally {
			request.removeAttribute(WebKeys.INVOKER_FILTER_URI);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		_filterConfig = filterConfig;

		ServletContext servletContext = _filterConfig.getServletContext();

		_contextPath = servletContext.getContextPath();

		boolean registerPortalLifecycle = GetterUtil.getBoolean(
			_filterConfig.getInitParameter("register-portal-lifecycle"), true);

		if (registerPortalLifecycle) {
			registerPortalLifecycle();
		}
		else {
			try {
				doPortalInit();
			}
			catch (Exception e) {
				_log.error(e, e);

				throw new ServletException(e);
			}
		}
	}

	protected void clearFilterChainsCache() {
		if (_filterChains != null) {
			_filterChains.clear();
		}
	}

	@Override
	protected void doPortalDestroy() {
		ServletContext servletContext = _filterConfig.getServletContext();

		InvokerFilterHelper invokerFilterHelper =
			(InvokerFilterHelper)servletContext.getAttribute(
				InvokerFilterHelper.class.getName());

		if (invokerFilterHelper != null) {
			servletContext.removeAttribute(InvokerFilterHelper.class.getName());

			invokerFilterHelper.destroy();
		}
	}

	@Override
	protected void doPortalInit() throws Exception {
		_invokerFilterChainSize = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.INVOKER_FILTER_CHAIN_SIZE));

		if (_invokerFilterChainSize > 0) {
			_filterChains = new ConcurrentLFUCache<>(_invokerFilterChainSize);
		}

		_invokerFilterURIMaxLength = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.INVOKER_FILTER_URI_MAX_LENGTH));

		ServletContext servletContext = _filterConfig.getServletContext();

		InvokerFilterHelper invokerFilterHelper =
			(InvokerFilterHelper)servletContext.getAttribute(
				InvokerFilterHelper.class.getName());

		if (invokerFilterHelper == null) {
			invokerFilterHelper = new InvokerFilterHelper();

			servletContext.setAttribute(
				InvokerFilterHelper.class.getName(), invokerFilterHelper);

			invokerFilterHelper.init(_filterConfig);
		}

		_invokerFilterHelper = invokerFilterHelper;

		_invokerFilterHelper.addInvokerFilter(this);

		String dispatcher = GetterUtil.getString(
			_filterConfig.getInitParameter("dispatcher"));

		if (dispatcher.equals("ERROR")) {
			_dispatcher = Dispatcher.ERROR;
		}
		else if (dispatcher.equals("FORWARD")) {
			_dispatcher = Dispatcher.FORWARD;
		}
		else if (dispatcher.equals("INCLUDE")) {
			_dispatcher = Dispatcher.INCLUDE;
		}
		else if (dispatcher.equals("REQUEST")) {
			_dispatcher = Dispatcher.REQUEST;
		}
		else {
			throw new IllegalArgumentException(
				"Invalid dispatcher " + dispatcher);
		}
	}

	protected InvokerFilterChain getInvokerFilterChain(
		HttpServletRequest request, String uri, FilterChain filterChain) {

		if (_filterChains == null) {
			return _invokerFilterHelper.createInvokerFilterChain(
				request, _dispatcher, uri, filterChain);
		}

		CacheKeyGenerator cacheKeyGenerator =
			CacheKeyGeneratorUtil.getCacheKeyGenerator(
				InvokerFilter.class.getName());

		String key = String.valueOf(cacheKeyGenerator.getCacheKey(uri));

		InvokerFilterChain invokerFilterChain = _filterChains.get(key);

		if (invokerFilterChain == null) {
			invokerFilterChain = _invokerFilterHelper.createInvokerFilterChain(
				request, _dispatcher, uri, filterChain);

			_filterChains.put(key, invokerFilterChain);
		}

		return invokerFilterChain.clone(filterChain);
	}

	protected String getURI(HttpServletRequest request) {
		String uri = null;

		if (_dispatcher == Dispatcher.ERROR) {
			uri = (String)request.getAttribute(
				JavaConstants.JAVAX_SERVLET_ERROR_REQUEST_URI);
		}
		else if (_dispatcher == Dispatcher.INCLUDE) {
			uri = (String)request.getAttribute(
				JavaConstants.JAVAX_SERVLET_INCLUDE_REQUEST_URI);
		}
		else {
			uri = request.getRequestURI();
		}

		if (Validator.isNotNull(_contextPath) &&
			!_contextPath.equals(StringPool.SLASH) &&
			uri.startsWith(_contextPath)) {

			uri = uri.substring(_contextPath.length());
		}

		return HttpUtil.normalizePath(uri);
	}

	protected String getURL(HttpServletRequest request) {
		StringBuffer requestURL = request.getRequestURL();

		if (requestURL == null) {
			return StringPool.BLANK;
		}

		String queryString = request.getQueryString();

		if (!Validator.isBlank(queryString)) {
			requestURL.append(StringPool.QUESTION);
			requestURL.append(request.getQueryString());
		}

		return requestURL.toString();
	}

	protected HttpServletRequest handleNonSerializableRequest(
		HttpServletRequest request) {

		if (ServerDetector.isWebLogic()) {
			if (!NonSerializableObjectRequestWrapper.isWrapped(request)) {
				request = new NonSerializableObjectRequestWrapper(request);
			}
		}

		return request;
	}

	protected HttpServletResponse secureResponseHeaders(
		HttpServletRequest request, HttpServletResponse response) {

		if (!GetterUtil.getBoolean(
				request.getAttribute(_SECURE_RESPONSE), true)) {

			return response;
		}

		request.setAttribute(_SECURE_RESPONSE, Boolean.FALSE);

		return SanitizedServletResponse.getSanitizedServletResponse(
			request, response);
	}

	private static final String _SECURE_RESPONSE =
		InvokerFilter.class.getName() + "SECURE_RESPONSE";

	private static final Log _log = LogFactoryUtil.getLog(InvokerFilter.class);

	private String _contextPath;
	private Dispatcher _dispatcher;
	private ConcurrentLFUCache<String, InvokerFilterChain> _filterChains;
	private FilterConfig _filterConfig;
	private int _invokerFilterChainSize;
	private InvokerFilterHelper _invokerFilterHelper;
	private int _invokerFilterURIMaxLength;

}