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

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.model.PortletInstance;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * The default friendly URL mapper to use with friendly URL routes.
 *
 * <p>
 * In most cases, to add friendly URL mapping to a portlet, simply set this
 * class as the friendly URL mapper in <code>liferay-portlet.xml</code>, and
 * write a <code>friendly-url-routes.xml</code> file.
 * </p>
 *
 * <p>
 * If you do need to extend this class, the key methods to override are {@link
 * #buildPath(LiferayPortletURL)} and {@link #populateParams(String, Map, Map)}.
 * </p>
 *
 * @author Connor McKay
 * @see    Router
 */
public class DefaultFriendlyURLMapper extends BaseFriendlyURLMapper {

	public DefaultFriendlyURLMapper() {
		defaultIgnoredParameters = new LinkedHashSet<>();

		defaultIgnoredParameters.add("p_p_id");
		defaultIgnoredParameters.add("p_p_col_id");
		defaultIgnoredParameters.add("p_p_col_pos");
		defaultIgnoredParameters.add("p_p_col_count");

		defaultReservedParameters = new LinkedHashMap<>();

		defaultReservedParameters.put("p_p_lifecycle", "0");
		defaultReservedParameters.put(
			"p_p_state", WindowState.NORMAL.toString());
		defaultReservedParameters.put("p_p_mode", PortletMode.VIEW.toString());
	}

	/**
	 * Adds a default ignored parameter.
	 *
	 * <p>
	 * A default ignored parameter will always be hidden in friendly URLs.
	 * </p>
	 *
	 * @param name the name of the parameter
	 */
	public void addDefaultIgnoredParameter(String name) {
		defaultIgnoredParameters.add(name);
	}

	/**
	 * Adds a default reserved parameter.
	 *
	 * <p>
	 * A default reserved parameter will be hidden in friendly URLs when it is
	 * set to its default value.
	 * </p>
	 *
	 * @param name the name of the parameter
	 * @param value the default value of the parameter
	 */
	public void addDefaultReservedParameter(String name, String value) {
		defaultReservedParameters.put(name, value);
	}

	@Override
	public String buildPath(LiferayPortletURL liferayPortletURL) {
		Map<String, String> routeParameters = new HashMap<>();

		buildRouteParameters(liferayPortletURL, routeParameters);

		String friendlyURLPath = router.parametersToUrl(routeParameters);

		if (Validator.isNull(friendlyURLPath)) {
			return null;
		}

		addParametersIncludedInPath(liferayPortletURL, routeParameters);

		friendlyURLPath = StringPool.SLASH.concat(getMapping()).concat(
			friendlyURLPath);

		return friendlyURLPath;
	}

	/**
	 * Returns the default ignored parameters.
	 *
	 * @return the ignored parameter names
	 * @see    #addDefaultIgnoredParameter(String)
	 */
	public Set<String> getDefaultIgnoredParameters() {
		return defaultIgnoredParameters;
	}

	/**
	 * Returns the default reserved parameters.
	 *
	 * @return the default reserved parameter names and values
	 * @see    #addDefaultReservedParameter(String, String)
	 */
	public Map<String, String> getDefaultReservedParameters() {
		return defaultReservedParameters;
	}

	@Override
	public void populateParams(
		String friendlyURLPath, Map<String, String[]> parameterMap,
		Map<String, Object> requestContext) {

		friendlyURLPath = friendlyURLPath.substring(getMapping().length() + 1);

		if (friendlyURLPath.endsWith(StringPool.SLASH)) {
			friendlyURLPath = friendlyURLPath.substring(
				0, friendlyURLPath.length() - 1);
		}

		Map<String, String> routeParameters = new HashMap<>();

		if (!router.urlToParameters(friendlyURLPath, routeParameters)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No route could be found to match URL " + friendlyURLPath);
			}

			return;
		}

		String namespace = null;

		String portletInstanceKey = getPortletId(routeParameters);

		if (Validator.isNotNull(portletInstanceKey)) {
			namespace = PortalUtil.getPortletNamespace(portletInstanceKey);

			addParameter(namespace, parameterMap, "p_p_id", portletInstanceKey);
		}
		else if (isAllPublicRenderParameters(routeParameters)) {

			// Portlet namespace is not needed if all the parameters are public
			// render parameters

			addParameter(null, parameterMap, "p_p_id", getPortletId());
		}
		else {
			return;
		}

		populateParams(parameterMap, namespace, routeParameters);
	}

	/**
	 * Adds the parameters included in the path to the portlet URL.
	 *
	 * <p>
	 * Portlet URLs track which parameters are included in the friendly URL
	 * path. This method hides all the default ignored parameters, the
	 * parameters included in the path by the router, and the reserved
	 * parameters set to their defaults.
	 * </p>
	 *
	 * @param liferayPortletURL the portlet URL to which to add the parameters
	 *        included in the path
	 * @param routeParameters the parameter map populated by the router
	 * @see   com.liferay.portlet.PortletURLImpl#addParameterIncludedInPath(
	 *        String)
	 */
	protected void addParametersIncludedInPath(
		LiferayPortletURL liferayPortletURL,
		Map<String, String> routeParameters) {

		// Hide default ignored parameters

		for (String name : defaultIgnoredParameters) {
			liferayPortletURL.addParameterIncludedInPath(name);
		}

		// Hide application parameters removed by the router

		Map<String, String[]> portletURLParameters =
			liferayPortletURL.getParameterMap();

		for (String name : portletURLParameters.keySet()) {
			if (!routeParameters.containsKey(name)) {
				liferayPortletURL.addParameterIncludedInPath(name);
			}
		}

		// Hide reserved parameters removed by the router or set to the defaults

		Map<String, String> reservedParameters =
			liferayPortletURL.getReservedParameterMap();

		for (Map.Entry<String, String> entry : reservedParameters.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			if (!routeParameters.containsKey(key) ||
				value.equals(defaultReservedParameters.get(key))) {

				liferayPortletURL.addParameterIncludedInPath(key);
			}
		}
	}

	/**
	 * Builds the parameter map to be used by the router by copying parameters
	 * from the portlet URL.
	 *
	 * <p>
	 * This method also populates the special virtual parameters
	 * <code>p_p_id</code> and <code>instanceId</code> for instanceable
	 * portlets.
	 * </p>
	 *
	 * @param liferayPortletURL the portlet URL to copy parameters from
	 * @param routeParameters the parameter map to populate for use by the
	 *        router
	 */
	protected void buildRouteParameters(
		LiferayPortletURL liferayPortletURL,
		Map<String, String> routeParameters) {

		// Copy application parameters

		Map<String, String[]> portletURLParameters =
			liferayPortletURL.getParameterMap();

		for (Map.Entry<String, String[]> entry :
				portletURLParameters.entrySet()) {

			String[] values = entry.getValue();

			if (values.length > 0) {
				routeParameters.put(entry.getKey(), values[0]);
			}
		}

		// Populate virtual parameters for instanceable portlets

		String portletInstanceKey = liferayPortletURL.getPortletId();

		if (Validator.isNotNull(portletInstanceKey)) {
			routeParameters.put("p_p_id", portletInstanceKey);

			PortletInstance portletInstance =
				PortletInstance.fromPortletInstanceKey(portletInstanceKey);

			routeParameters.put(
				"userIdAndInstanceId",
				portletInstance.getUserIdAndInstanceId());

			if (portletInstance.hasInstanceId()) {
				routeParameters.put(
					"instanceId", portletInstance.getInstanceId());
			}
		}

		// Copy reserved parameters

		routeParameters.putAll(liferayPortletURL.getReservedParameterMap());
	}

	/**
	 * Returns the portlet ID, including the instance ID if applicable, from the
	 * parameter map.
	 *
	 * @deprecated As of 7.0.0, replaced by {@link #getPortletInstanceKey(Map)}
	 * @param  routeParameters the parameter map. For an instanceable portlet,
	 *         this must contain either <code>p_p_id</code> or
	 *         <code>instanceId</code>.
	 * @return the portlet ID, including the instance ID if applicable, or
	 *         <code>null</code> if it cannot be determined
	 */
	@Deprecated
	protected String getPortletId(Map<String, String> routeParameters) {
		return getPortletInstanceKey(routeParameters);
	}

	/**
	 * Returns the portlet instance key, including the instance ID if
	 * applicable, from the parameter map.
	 *
	 * @param  routeParameters the parameter map. For an instanceable portlet,
	 *         this must contain either <code>p_p_id</code> or
	 *         <code>instanceId</code>.
	 * @return the portlet instance key, including the instance ID if
	 *         applicable, or <code>null</code> if it cannot be determined
	 */
	protected String getPortletInstanceKey(
		Map<String, String> routeParameters) {

		String userIdAndInstanceId = routeParameters.remove(
			"userIdAndInstanceId");

		if (!isPortletInstanceable() && Validator.isNull(userIdAndInstanceId)) {
			return getPortletId();
		}

		String portletInstanceKey = routeParameters.remove("p_p_id");

		if (Validator.isNotNull(portletInstanceKey)) {
			return portletInstanceKey;
		}

		if (Validator.isNotNull(userIdAndInstanceId)) {
			PortletInstance portletInstance =
				PortletInstance.fromPortletNameAndUserIdAndInstanceId(
					getPortletId(), userIdAndInstanceId);

			return portletInstance.getPortletInstanceKey();
		}

		String instanceId = routeParameters.remove("instanceId");

		if (Validator.isNotNull(instanceId)) {
			return PortletConstants.assemblePortletId(
				getPortletId(), instanceId);
		}

		if (!isAllPublicRenderParameters(routeParameters)) {
			_log.error(
				"Either p_p_id or instanceId must be provided for an " +
					"instanceable portlet");
		}

		return null;
	}

	/**
	 * Returns <code>true</code> if all the route parameters are public render
	 * parameters.
	 *
	 * @param  routeParameters the parameter map
	 * @return <code>true</code> if all the route parameters are public render
	 *         parameters; <code>false</code> otherwise
	 */
	protected boolean isAllPublicRenderParameters(
		Map<String, String> routeParameters) {

		Set<String> routeParameterKeys = routeParameters.keySet();

		Map<String, String> publicRenderParameters =
			FriendlyURLMapperThreadLocal.getPRPIdentifiers();

		return routeParameterKeys.containsAll(publicRenderParameters.keySet());
	}

	/**
	 * Populates the parameter map using the parameters from the router and the
	 * default reserved parameters.
	 *
	 * @param parameterMap the parameter map to populate. This should be the map
	 *        passed to {@link #populateParams(String, Map, Map)} by {@link
	 *        com.liferay.portal.util.PortalImpl}.
	 * @param namespace the namespace to use for parameters added to
	 *        <code>parameterMap</code>
	 * @param routeParameters the parameter map populated by the router
	 */
	protected void populateParams(
		Map<String, String[]> parameterMap, String namespace,
		Map<String, String> routeParameters) {

		// Copy route parameters

		for (Map.Entry<String, String> entry : routeParameters.entrySet()) {
			addParameter(
				namespace, parameterMap, entry.getKey(), entry.getValue());
		}

		// Copy default reserved parameters if they are not already set

		for (Map.Entry<String, String> entry :
				defaultReservedParameters.entrySet()) {

			String key = entry.getKey();

			if (!parameterMap.containsKey(key)) {
				addParameter(namespace, parameterMap, key, entry.getValue());
			}
		}
	}

	protected Set<String> defaultIgnoredParameters;
	protected Map<String, String> defaultReservedParameters;

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultFriendlyURLMapper.class);

}