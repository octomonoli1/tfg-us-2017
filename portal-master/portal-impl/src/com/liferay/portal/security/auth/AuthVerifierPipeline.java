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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierConfiguration;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.registry.Filter;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import jodd.util.Wildcard;

/**
 * @author Tomas Polesovsky
 * @author Peter Fellwock
 */
public class AuthVerifierPipeline {

	public static final String AUTH_TYPE = "auth.type";

	public static String getAuthVerifierPropertyName(String className) {
		String simpleClassName = StringUtil.extractLast(
			className, StringPool.PERIOD);

		return PropsKeys.AUTH_VERIFIER.concat(simpleClassName).concat(
			StringPool.PERIOD);
	}

	public static AuthVerifierResult verifyRequest(
			AccessControlContext accessControlContext)
		throws PortalException {

		return _instance._verifyRequest(accessControlContext);
	}

	private AuthVerifierPipeline() {
		Registry registry = RegistryUtil.getRegistry();

		Filter filter = registry.getFilter(
			"(objectClass=" + AuthVerifier.class.getName() + ")");

		_serviceTracker = registry.trackServices(
			filter, new AuthVerifierTrackerCustomizer());

		_serviceTracker.open();
	}

	private AuthVerifierResult _createGuestVerificationResult(
			AccessControlContext accessControlContext)
		throws PortalException {

		AuthVerifierResult authVerifierResult = new AuthVerifierResult();

		authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);

		HttpServletRequest request = accessControlContext.getRequest();

		long companyId = PortalUtil.getCompanyId(request);

		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);

		authVerifierResult.setUserId(defaultUserId);

		return authVerifierResult;
	}

	private List<AuthVerifierConfiguration> _getAuthVerifierConfigurations(
		AccessControlContext accessControlContext) {

		HttpServletRequest request = accessControlContext.getRequest();

		List<AuthVerifierConfiguration> authVerifierConfigurations =
			new ArrayList<>();

		String requestURI = request.getRequestURI();

		String contextPath = request.getContextPath();

		requestURI = requestURI.substring(contextPath.length());

		for (AuthVerifierConfiguration authVerifierConfiguration :
				_authVerifierConfigurations) {

			authVerifierConfiguration = _mergeAuthVerifierConfiguration(
				authVerifierConfiguration, accessControlContext);

			if (_isMatchingRequestURI(authVerifierConfiguration, requestURI)) {
				authVerifierConfigurations.add(authVerifierConfiguration);
			}
		}

		return authVerifierConfigurations;
	}

	private boolean _isMatchingRequestURI(
		AuthVerifierConfiguration authVerifierConfiguration,
		String requestURI) {

		Properties properties = authVerifierConfiguration.getProperties();

		String[] urlsExcludes = StringUtil.split(
			properties.getProperty("urls.excludes"));

		if ((urlsExcludes.length > 0) &&
			(Wildcard.matchOne(requestURI, urlsExcludes) > -1)) {

			return false;
		}

		String[] urlsIncludes = StringUtil.split(
			properties.getProperty("urls.includes"));

		if (urlsIncludes.length == 0) {
			return false;
		}

		if (Wildcard.matchOne(requestURI, urlsIncludes) > -1) {
			return true;
		}

		return false;
	}

	private AuthVerifierConfiguration _mergeAuthVerifierConfiguration(
		AuthVerifierConfiguration authVerifierConfiguration,
		AccessControlContext accessControlContext) {

		Map<String, Object> settings = accessControlContext.getSettings();

		String authVerifierSettingsKey = getAuthVerifierPropertyName(
			authVerifierConfiguration.getAuthVerifierClassName());

		boolean merge = false;

		Set<String> settingsKeys = settings.keySet();

		Iterator<String> iterator = settingsKeys.iterator();

		while (iterator.hasNext() && !merge) {
			String settingsKey = iterator.next();

			if (settingsKey.startsWith(authVerifierSettingsKey)) {
				if (settings.get(settingsKey) instanceof String) {
					merge = true;
				}
			}
		}

		if (!merge) {
			return authVerifierConfiguration;
		}

		AuthVerifierConfiguration mergedAuthVerifierConfiguration =
			new AuthVerifierConfiguration();

		mergedAuthVerifierConfiguration.setAuthVerifier(
			authVerifierConfiguration.getAuthVerifier());

		Properties mergedProperties = new Properties(
			authVerifierConfiguration.getProperties());

		for (Map.Entry<String, Object> entry : settings.entrySet()) {
			String settingsKey = entry.getKey();

			if (settingsKey.startsWith(authVerifierSettingsKey)) {
				Object settingsValue = entry.getValue();

				if (settingsValue instanceof String) {
					String propertiesKey = settingsKey.substring(
						authVerifierSettingsKey.length());

					mergedProperties.setProperty(
						propertiesKey, (String)settingsValue);
				}
			}
		}

		mergedAuthVerifierConfiguration.setProperties(mergedProperties);

		return mergedAuthVerifierConfiguration;
	}

	private Map<String, Object> _mergeSettings(
		Properties properties, Map<String, Object> settings) {

		Map<String, Object> mergedSettings = new HashMap<>(settings);

		if (properties != null) {
			for (Map.Entry<Object, Object> entry : properties.entrySet()) {
				mergedSettings.put((String)entry.getKey(), entry.getValue());
			}
		}

		return mergedSettings;
	}

	private AuthVerifierResult _verifyRequest(
			AccessControlContext accessControlContext)
		throws PortalException {

		if (accessControlContext == null) {
			throw new IllegalArgumentException(
				"Access control context is null");
		}

		List<AuthVerifierConfiguration> authVerifierConfigurations =
			_getAuthVerifierConfigurations(accessControlContext);

		for (AuthVerifierConfiguration authVerifierConfiguration :
				authVerifierConfigurations) {

			AuthVerifierResult authVerifierResult = null;

			AuthVerifier authVerifier =
				authVerifierConfiguration.getAuthVerifier();

			Properties properties = authVerifierConfiguration.getProperties();

			try {
				authVerifierResult = authVerifier.verify(
					accessControlContext, properties);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					Class<?> authVerifierClass = authVerifier.getClass();

					_log.debug("Skipping " + authVerifierClass.getName(), e);
				}

				continue;
			}

			if (authVerifierResult == null) {
				Class<?> authVerifierClass = authVerifier.getClass();

				_log.error(
					"Auth verifier " + authVerifierClass.getName() +
						" did not return an auth verifier result");

				continue;
			}

			if (authVerifierResult.getState() !=
					AuthVerifierResult.State.NOT_APPLICABLE) {

				Map<String, Object> settings = _mergeSettings(
					properties, authVerifierResult.getSettings());

				settings.put(AUTH_TYPE, authVerifier.getAuthType());

				authVerifierResult.setSettings(settings);

				return authVerifierResult;
			}
		}

		return _createGuestVerificationResult(accessControlContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AuthVerifierPipeline.class);

	private static final AuthVerifierPipeline _instance =
		new AuthVerifierPipeline();

	private final List<AuthVerifierConfiguration> _authVerifierConfigurations =
		new CopyOnWriteArrayList<>();
	private final ServiceTracker<AuthVerifier, AuthVerifierConfiguration>
		_serviceTracker;

	private class AuthVerifierTrackerCustomizer
		implements
			ServiceTrackerCustomizer<AuthVerifier, AuthVerifierConfiguration> {

		@Override
		public AuthVerifierConfiguration addingService(
			ServiceReference<AuthVerifier> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			AuthVerifier authVerifier = registry.getService(serviceReference);

			if (authVerifier == null) {
				return null;
			}

			Class<?> authVerifierClass = authVerifier.getClass();

			AuthVerifierConfiguration authVerifierConfiguration =
				new AuthVerifierConfiguration();

			authVerifierConfiguration.setAuthVerifier(authVerifier);
			authVerifierConfiguration.setAuthVerifierClassName(
				authVerifierClass.getName());
			authVerifierConfiguration.setProperties(
				_loadProperties(serviceReference, authVerifierClass.getName()));

			if (!_validate(authVerifierConfiguration)) {
				return null;
			}

			_authVerifierConfigurations.add(0, authVerifierConfiguration);

			return authVerifierConfiguration;
		}

		@Override
		public void modifiedService(
			ServiceReference<AuthVerifier> serviceReference,
			AuthVerifierConfiguration authVerifierConfiguration) {

			AuthVerifierConfiguration newAuthVerifierConfiguration =
				new AuthVerifierConfiguration();

			newAuthVerifierConfiguration.setAuthVerifier(
				authVerifierConfiguration.getAuthVerifier());
			newAuthVerifierConfiguration.setAuthVerifierClassName(
				authVerifierConfiguration.getAuthVerifierClassName());
			newAuthVerifierConfiguration.setProperties(
				_loadProperties(
					serviceReference,
					authVerifierConfiguration.getAuthVerifierClassName()));

			if (_authVerifierConfigurations.remove(authVerifierConfiguration)) {
				if (!_validate(authVerifierConfiguration)) {
					return;
				}

				_authVerifierConfigurations.add(
					0, newAuthVerifierConfiguration);
			}
		}

		@Override
		public void removedService(
			ServiceReference<AuthVerifier> serviceReference,
			AuthVerifierConfiguration authVerifierConfiguration) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_authVerifierConfigurations.remove(authVerifierConfiguration);
		}

		private Properties _loadProperties(
			ServiceReference<AuthVerifier> serviceReference,
			String authVerifierClassName) {

			Properties properties = new Properties();

			String authVerifierPropertyName = getAuthVerifierPropertyName(
				authVerifierClassName);

			Map<String, Object> serviceReferenceProperties =
				serviceReference.getProperties();

			for (Map.Entry<String, Object> entry :
					serviceReferenceProperties.entrySet()) {

				String key = entry.getKey();

				if (key.startsWith(authVerifierPropertyName)) {
					key = key.substring(authVerifierPropertyName.length());
				}

				Object value = entry.getValue();

				properties.setProperty(key, String.valueOf(value));
			}

			return properties;
		}

		private boolean _validate(
			AuthVerifierConfiguration authVerifierConfiguration) {

			Properties properties = authVerifierConfiguration.getProperties();

			String[] urlsIncludes = StringUtil.split(
				properties.getProperty("urls.includes"));

			if (urlsIncludes.length == 0) {
				if (_log.isWarnEnabled()) {
					String authVerifierClassName =
						authVerifierConfiguration.getAuthVerifierClassName();

					_log.warn(
						"Auth verifier " + authVerifierClassName +
							" does not have URLs configured");
				}

				return false;
			}

			return true;
		}

	}

}