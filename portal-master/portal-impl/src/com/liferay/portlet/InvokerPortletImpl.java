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

package com.liferay.portlet;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.portlet.InvokerFilterContainer;
import com.liferay.portal.kernel.portlet.InvokerPortlet;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.LiferayPortletContext;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletFilterUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.servlet.BufferCacheServletResponse;
import com.liferay.portal.kernel.servlet.PluginContextListener;
import com.liferay.portal.kernel.servlet.PortletServlet;
import com.liferay.portal.kernel.util.ClassLoaderUtil;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.tools.deploy.PortletDeployer;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.UnavailableException;
import javax.portlet.filter.ActionFilter;
import javax.portlet.filter.EventFilter;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
import javax.portlet.filter.ResourceFilter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Brian Wing Shun Chan
 * @author Brian Myunghun Kim
 * @author Raymond Augé
 */
public class InvokerPortletImpl
	implements InvokerFilterContainer, InvokerPortlet {

	public static void clearResponse(
		HttpSession session, long plid, String portletId, String languageId) {

		String sesResponseId = encodeResponseKey(plid, portletId, languageId);

		getResponses(session).remove(sesResponseId);
	}

	public static void clearResponses(HttpSession session) {
		getResponses(session).clear();
	}

	public static void clearResponses(PortletSession session) {
		getResponses(session).clear();
	}

	public static String encodeResponseKey(
		long plid, String portletId, String languageId) {

		StringBundler sb = new StringBundler(5);

		sb.append(StringUtil.toHexString(plid));
		sb.append(StringPool.UNDERLINE);
		sb.append(portletId);
		sb.append(StringPool.UNDERLINE);
		sb.append(languageId);

		return sb.toString();
	}

	public static Map<String, InvokerPortletResponse> getResponses(
		HttpSession session) {

		Map<String, InvokerPortletResponse> responses =
			(Map<String, InvokerPortletResponse>)session.getAttribute(
				WebKeys.CACHE_PORTLET_RESPONSES);

		if (responses == null) {
			responses = new ConcurrentHashMap<>();

			session.setAttribute(WebKeys.CACHE_PORTLET_RESPONSES, responses);
		}

		return responses;
	}

	public static Map<String, InvokerPortletResponse> getResponses(
		PortletSession portletSession) {

		return getResponses(
			((PortletSessionImpl)portletSession).getHttpSession());
	}

	public InvokerPortletImpl(
		com.liferay.portal.kernel.model.Portlet portletModel, Portlet portlet,
		PortletConfig portletConfig, PortletContext portletContext,
		InvokerFilterContainer invokerFilterContainer, boolean checkAuthToken,
		boolean facesPortlet, boolean strutsPortlet,
		boolean strutsBridgePortlet) {

		_initialize(
			portletModel, portlet, portletConfig, portletContext,
			invokerFilterContainer, checkAuthToken, facesPortlet, strutsPortlet,
			strutsBridgePortlet);
	}

	public InvokerPortletImpl(
		com.liferay.portal.kernel.model.Portlet portletModel, Portlet portlet,
		PortletContext portletContext,
		InvokerFilterContainer invokerFilterContainer) {

		Map<String, String> initParams = portletModel.getInitParams();

		boolean checkAuthToken = GetterUtil.getBoolean(
			initParams.get("check-auth-token"), true);

		boolean facesPortlet = false;

		if (ClassUtil.isSubclass(
				portlet.getClass(), PortletDeployer.JSF_STANDARD)) {

			facesPortlet = true;
		}

		boolean strutsPortlet = ClassUtil.isSubclass(
			portlet.getClass(), StrutsPortlet.class);

		boolean strutsBridgePortlet = ClassUtil.isSubclass(
			portlet.getClass(),
			"org.apache.portals.bridges.struts.StrutsPortlet");

		_initialize(
			portletModel, portlet, null, portletContext, invokerFilterContainer,
			checkAuthToken, facesPortlet, strutsPortlet, strutsBridgePortlet);
	}

	@Override
	public void destroy() {
		if (PortletConstants.hasInstanceId(_portletModel.getPortletId())) {
			if (_log.isWarnEnabled()) {
				_log.warn("Destroying an instanced portlet is not allowed");
			}

			return;
		}

		ClassLoader contextClassLoader =
			ClassLoaderUtil.getContextClassLoader();

		try {
			if (_portletClassLoader != null) {
				ClassLoaderUtil.setContextClassLoader(_portletClassLoader);
			}

			Closeable closeable = (Closeable)_invokerFilterContainer;

			closeable.close();

			_portlet.destroy();
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
		finally {
			if (_portletClassLoader != null) {
				ClassLoaderUtil.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public List<ActionFilter> getActionFilters() {
		return _invokerFilterContainer.getActionFilters();
	}

	@Override
	public List<EventFilter> getEventFilters() {
		return _invokerFilterContainer.getEventFilters();
	}

	@Override
	public Integer getExpCache() {
		return _expCache;
	}

	@Override
	public Portlet getPortlet() {
		return _portlet;
	}

	@Override
	public ClassLoader getPortletClassLoader() {
		ClassLoader classLoader =
			(ClassLoader)_liferayPortletContext.getAttribute(
				PluginContextListener.PLUGIN_CLASS_LOADER);

		if (classLoader == null) {
			classLoader = ClassLoaderUtil.getPortalClassLoader();
		}

		return classLoader;
	}

	@Override
	public PortletConfig getPortletConfig() {
		return _liferayPortletConfig;
	}

	@Override
	public PortletContext getPortletContext() {
		return _liferayPortletContext;
	}

	@Override
	public Portlet getPortletInstance() {
		return _portlet;
	}

	@Override
	public List<RenderFilter> getRenderFilters() {
		return _invokerFilterContainer.getRenderFilters();
	}

	@Override
	public List<ResourceFilter> getResourceFilters() {
		return _invokerFilterContainer.getResourceFilters();
	}

	@Override
	public void init(PortletConfig portletConfig) throws PortletException {
		_liferayPortletConfig = (LiferayPortletConfig)portletConfig;

		ClassLoader contextClassLoader =
			ClassLoaderUtil.getContextClassLoader();

		_portletClassLoader = getPortletClassLoader();

		try {
			if (_portletClassLoader != null) {
				ClassLoaderUtil.setContextClassLoader(_portletClassLoader);
			}

			_portlet.init(portletConfig);
		}
		finally {
			if (_portletClassLoader != null) {
				ClassLoaderUtil.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public boolean isCheckAuthToken() {
		return _checkAuthToken;
	}

	@Override
	public boolean isFacesPortlet() {
		return _facesPortlet;
	}

	@Override
	public boolean isStrutsBridgePortlet() {
		return _strutsBridgePortlet;
	}

	@Override
	public boolean isStrutsPortlet() {
		return _strutsPortlet;
	}

	@Override
	public void processAction(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		try {
			invokeAction(actionRequest, actionResponse);
		}
		catch (Exception e) {
			processException(e, actionRequest, actionResponse);
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"processAction for " + _portletId + " takes " +
					stopWatch.getTime() + " ms");
		}
	}

	@Override
	public void processEvent(
		EventRequest eventRequest, EventResponse eventResponse) {

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		try {
			invokeEvent(eventRequest, eventResponse);
		}
		catch (Exception e) {
			processException(e, eventRequest, eventResponse);
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"processEvent for " + _portletId + " takes " +
					stopWatch.getTime() + " ms");
		}
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PortletException portletException =
			(PortletException)renderRequest.getAttribute(
				_portletId + PortletException.class.getName());

		if (portletException != null) {
			throw portletException;
		}

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		String remoteUser = renderRequest.getRemoteUser();

		if ((remoteUser == null) || (_expCache == null) ||
			(_expCache.intValue() == 0)) {

			invokeRender(renderRequest, renderResponse);
		}
		else {
			RenderResponseImpl renderResponseImpl =
				(RenderResponseImpl)renderResponse;

			BufferCacheServletResponse bufferCacheServletResponse =
				(BufferCacheServletResponse)
					renderResponseImpl.getHttpServletResponse();

			PortletSession portletSession = renderRequest.getPortletSession();

			long now = System.currentTimeMillis();

			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);

			Map<String, InvokerPortletResponse> sessionResponses = getResponses(
				portletSession);

			String sessionResponseId = encodeResponseKey(
				layout.getPlid(), _portletId,
				LanguageUtil.getLanguageId(renderRequest));

			InvokerPortletResponse response = sessionResponses.get(
				sessionResponseId);

			if (response == null) {
				String title = invokeRender(renderRequest, renderResponse);

				response = new InvokerPortletResponse(
					title, bufferCacheServletResponse.getString(),
					now + Time.SECOND * _expCache.intValue());

				sessionResponses.put(sessionResponseId, response);
			}
			else if ((response.getTime() < now) && (_expCache.intValue() > 0)) {
				String title = invokeRender(renderRequest, renderResponse);

				response.setTitle(title);
				response.setContent(bufferCacheServletResponse.getString());
				response.setTime(now + Time.SECOND * _expCache.intValue());
			}
			else {
				renderResponseImpl.setTitle(response.getTitle());

				PrintWriter printWriter =
					bufferCacheServletResponse.getWriter();

				printWriter.print(response.getContent());
			}
		}

		Map<String, String[]> properties =
			((RenderResponseImpl)renderResponse).getProperties();

		if (properties.containsKey("clear-request-parameters")) {
			((RenderRequestImpl)renderRequest).clearRenderParameters();
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"render for " + _portletId + " takes " + stopWatch.getTime() +
					" ms");
		}
	}

	@Override
	public void serveResource(
		ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		try {
			invokeResource(resourceRequest, resourceResponse);
		}
		catch (Exception e) {
			processException(e, resourceRequest, resourceResponse);
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"serveResource for " + _portletId + " takes " +
					stopWatch.getTime() + " ms");
		}
	}

	/**
	 * @deprecated As of 7.0.0
	 */
	@Deprecated
	@Override
	public void setPortletFilters() {
	}

	protected void invoke(
			LiferayPortletRequest portletRequest,
			LiferayPortletResponse portletResponse, String lifecycle,
			List<? extends PortletFilter> filters)
		throws IOException, PortletException {

		FilterChain filterChain = new FilterChainImpl(_portlet, filters);

		if (_liferayPortletConfig.isWARFile()) {
			String invokerPortletName = _liferayPortletConfig.getInitParameter(
				INIT_INVOKER_PORTLET_NAME);

			if (invokerPortletName == null) {
				invokerPortletName = _liferayPortletConfig.getPortletName();
			}

			String path = StringPool.SLASH + invokerPortletName + "/invoke";

			ServletContext servletContext =
				_liferayPortletContext.getServletContext();

			RequestDispatcher requestDispatcher =
				servletContext.getRequestDispatcher(path);

			HttpServletRequest request = portletRequest.getHttpServletRequest();
			HttpServletResponse response =
				portletResponse.getHttpServletResponse();

			request.setAttribute(JavaConstants.JAVAX_PORTLET_PORTLET, _portlet);
			request.setAttribute(PortletRequest.LIFECYCLE_PHASE, lifecycle);
			request.setAttribute(
				PortletServlet.PORTLET_SERVLET_FILTER_CHAIN, filterChain);

			try {

				// Resource phase must be a forward because includes do not
				// allow you to specify the content type or headers

				if (lifecycle.equals(PortletRequest.RESOURCE_PHASE)) {
					requestDispatcher.forward(request, response);
				}
				else {
					requestDispatcher.include(request, response);
				}
			}
			catch (ServletException se) {
				Throwable cause = se.getRootCause();

				if (cause instanceof PortletException) {
					throw (PortletException)cause;
				}

				throw new PortletException(cause);
			}
		}
		else {
			PortletFilterUtil.doFilter(
				portletRequest, portletResponse, lifecycle, filterChain);
		}

		portletResponse.transferMarkupHeadElements();

		Map<String, String[]> properties = portletResponse.getProperties();

		if (MapUtil.isNotEmpty(properties)) {
			if (_expCache != null) {
				String[] expCache = properties.get(
					RenderResponse.EXPIRATION_CACHE);

				if ((expCache != null) && (expCache.length > 0) &&
					(expCache[0] != null)) {

					_expCache = Integer.valueOf(
						GetterUtil.getInteger(expCache[0]));
				}
			}
		}
	}

	protected void invokeAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		LiferayPortletRequest portletRequest =
			(LiferayPortletRequest)actionRequest;
		LiferayPortletResponse portletResponse =
			(LiferayPortletResponse)actionResponse;

		invoke(
			portletRequest, portletResponse, PortletRequest.ACTION_PHASE,
			_invokerFilterContainer.getActionFilters());
	}

	protected void invokeEvent(
			EventRequest eventRequest, EventResponse eventResponse)
		throws IOException, PortletException {

		LiferayPortletRequest portletRequest =
			(LiferayPortletRequest)eventRequest;
		LiferayPortletResponse portletResponse =
			(LiferayPortletResponse)eventResponse;

		invoke(
			portletRequest, portletResponse, PortletRequest.EVENT_PHASE,
			_invokerFilterContainer.getEventFilters());
	}

	protected String invokeRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		LiferayPortletRequest portletRequest =
			(LiferayPortletRequest)renderRequest;
		LiferayPortletResponse portletResponse =
			(LiferayPortletResponse)renderResponse;

		try {
			invoke(
				portletRequest, portletResponse, PortletRequest.RENDER_PHASE,
				_invokerFilterContainer.getRenderFilters());
		}
		catch (Exception e) {
			processException(e, renderRequest, renderResponse);

			throw e;
		}

		RenderResponseImpl renderResponseImpl =
			(RenderResponseImpl)renderResponse;

		return renderResponseImpl.getTitle();
	}

	protected void invokeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		LiferayPortletRequest portletRequest =
			(LiferayPortletRequest)resourceRequest;
		LiferayPortletResponse portletResponse =
			(LiferayPortletResponse)resourceResponse;

		invoke(
			portletRequest, portletResponse, PortletRequest.RESOURCE_PHASE,
			_invokerFilterContainer.getResourceFilters());
	}

	protected void processException(
		Exception e, PortletRequest portletRequest,
		PortletResponse portletResponse) {

		if (portletResponse instanceof StateAwareResponseImpl) {

			// PLT.5.4.7, TCK xxiii and PLT.15.2.6, cxlvi

			StateAwareResponseImpl stateAwareResponseImpl =
				(StateAwareResponseImpl)portletResponse;

			stateAwareResponseImpl.reset();
		}

		if (e instanceof RuntimeException) {

			// PLT.5.4.7, TCK xxv

			e = new PortletException(e);
		}

		if (e instanceof UnavailableException) {

			// PLT.5.4.7, TCK xxiv

			destroy();

			PortletLocalServiceUtil.deletePortlet(_portletModel);
		}

		if (e instanceof PortletException) {
			if (!(portletRequest instanceof RenderRequest)) {
				portletRequest.setAttribute(
					_portletId + PortletException.class.getName(), e);
			}
		}
		else {
			ReflectionUtil.throwException(e);
		}
	}

	private void _initialize(
		com.liferay.portal.kernel.model.Portlet portletModel, Portlet portlet,
		PortletConfig portletConfig, PortletContext portletContext,
		InvokerFilterContainer invokerFilterContainer, boolean checkAuthToken,
		boolean facesPortlet, boolean strutsPortlet,
		boolean strutsBridgePortlet) {

		_portletModel = portletModel;
		_portlet = portlet;
		_liferayPortletConfig = (LiferayPortletConfig)portletConfig;
		_portletId = _portletModel.getPortletId();
		_liferayPortletContext = (LiferayPortletContext)portletContext;
		_invokerFilterContainer = invokerFilterContainer;
		_checkAuthToken = checkAuthToken;
		_facesPortlet = facesPortlet;
		_strutsPortlet = strutsPortlet;
		_strutsBridgePortlet = strutsBridgePortlet;
		_expCache = portletModel.getExpCache();

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Create instance cache wrapper for " +
					_liferayPortletContext.getPortlet().getPortletId());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		InvokerPortletImpl.class);

	private boolean _checkAuthToken;
	private Integer _expCache;
	private boolean _facesPortlet;
	private InvokerFilterContainer _invokerFilterContainer;
	private LiferayPortletConfig _liferayPortletConfig;
	private LiferayPortletContext _liferayPortletContext;
	private Portlet _portlet;
	private ClassLoader _portletClassLoader;
	private String _portletId;
	private com.liferay.portal.kernel.model.Portlet _portletModel;
	private boolean _strutsBridgePortlet;
	private boolean _strutsPortlet;

}