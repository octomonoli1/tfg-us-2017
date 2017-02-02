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

package com.liferay.portal.kernel.webdav;

import com.liferay.document.library.kernel.util.DL;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.comparator.GroupFriendlyURLComparator;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceRegistration;
import com.liferay.registry.ServiceTrackerCustomizer;
import com.liferay.registry.collections.ServiceRegistrationMap;
import com.liferay.registry.collections.ServiceRegistrationMapImpl;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class WebDAVUtil {

	public static final Namespace DAV_URI = SAXReaderUtil.createNamespace(
		"D", "DAV:");

	public static final int SC_LOCKED = 423;

	public static final int SC_MULTI_STATUS = 207;

	public static final String TOKEN_PREFIX = "opaquelocktoken:";

	public static void addStorage(WebDAVStorage storage) {
		getInstance()._addStorage(storage);
	}

	public static Namespace createNamespace(String prefix, String uri) {
		Namespace namespace = null;

		if (uri.equals(WebDAVUtil.DAV_URI.getURI())) {
			namespace = WebDAVUtil.DAV_URI;
		}
		else if (Validator.isNull(prefix)) {
			namespace = SAXReaderUtil.createNamespace(uri);
		}
		else {
			namespace = SAXReaderUtil.createNamespace(prefix, uri);
		}

		return namespace;
	}

	public static void deleteStorage(WebDAVStorage storage) {
		getInstance()._deleteStorage(storage);
	}

	public static long getDepth(HttpServletRequest request) {
		String value = GetterUtil.getString(request.getHeader("Depth"));

		if (_log.isDebugEnabled()) {
			_log.debug("\"Depth\" header is " + value);
		}

		if (value.equals("0")) {
			return 0;
		}
		else {
			return -1;
		}
	}

	public static String getDestination(
		HttpServletRequest request, String rootPath) {

		String headerDestination = request.getHeader("Destination");
		String[] pathSegments = StringUtil.split(headerDestination, rootPath);

		String destination = pathSegments[pathSegments.length - 1];

		destination = HttpUtil.decodePath(destination);

		if (_log.isDebugEnabled()) {
			_log.debug("Destination " + destination);
		}

		return destination;
	}

	public static long getGroupId(long companyId, String path)
		throws WebDAVException {

		String[] pathArray = getPathArray(path);

		return getGroupId(companyId, pathArray);
	}

	public static long getGroupId(long companyId, String[] pathArray)
		throws WebDAVException {

		try {
			if (pathArray.length == 0) {
				return 0;
			}

			String name = pathArray[0];

			Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(
				companyId, StringPool.SLASH + name);

			if (group != null) {
				return group.getGroupId();
			}

			User user = UserLocalServiceUtil.fetchUserByScreenName(
				companyId, name);

			if (user != null) {
				group = user.getGroup();

				return group.getGroupId();
			}
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}

		return 0;
	}

	public static List<Group> getGroups(long userId) throws Exception {
		User user = UserLocalServiceUtil.getUser(userId);

		return getGroups(user);
	}

	public static List<Group> getGroups(User user) throws Exception {

		// Guest

		if (user.isDefaultUser()) {
			List<Group> groups = new ArrayList<>();

			Group group = GroupLocalServiceUtil.getGroup(
				user.getCompanyId(), GroupConstants.GUEST);

			groups.add(group);

			return groups;
		}

		// Communities

		Set<Group> groups = new HashSet<>();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("usersGroups", user.getUserId());

		OrderByComparator<Group> orderByComparator =
			new GroupFriendlyURLComparator(true);

		groups.addAll(
			GroupLocalServiceUtil.search(
				user.getCompanyId(), null, null, params, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, orderByComparator));

		// Organizations

		groups.addAll(
			GroupLocalServiceUtil.getUserOrganizationsGroups(
				user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS));

		// User

		if (!user.isDefaultUser()) {
			groups.add(user.getGroup());
		}

		List<Group> groupsList = new ArrayList<>(groups);

		Collections.sort(groupsList, orderByComparator);

		return groupsList;
	}

	public static WebDAVUtil getInstance() {
		PortalRuntimePermission.checkGetBeanProperty(WebDAVUtil.class);

		return _instance;
	}

	public static String getLockUuid(HttpServletRequest request)
		throws WebDAVException {

		String token = StringPool.BLANK;

		String value = GetterUtil.getString(request.getHeader("If"));

		if (_log.isDebugEnabled()) {
			_log.debug("\"If\" header is " + value);
		}

		if (value.contains("(<DAV:no-lock>)")) {
			if (_log.isWarnEnabled()) {
				_log.warn("Lock tokens can never be <DAV:no-lock>");
			}

			throw new WebDAVException();
		}

		int beg = value.indexOf(TOKEN_PREFIX);

		if (beg >= 0) {
			beg += TOKEN_PREFIX.length();

			if (beg < value.length()) {
				int end = value.indexOf(CharPool.GREATER_THAN, beg);

				token = GetterUtil.getString(value.substring(beg, end));
			}
		}

		return token;
	}

	public static String[] getPathArray(String path) {
		return getPathArray(path, false);
	}

	public static String[] getPathArray(String path, boolean fixTrailing) {
		path = HttpUtil.fixPath(path, true, fixTrailing);

		return StringUtil.split(path, CharPool.SLASH);
	}

	public static String getResourceName(String[] pathArray) {
		if (pathArray.length <= 2) {
			return StringPool.BLANK;
		}
		else {
			return pathArray[pathArray.length - 1];
		}
	}

	public static WebDAVStorage getStorage(String token) {
		return getInstance()._getStorage(token);
	}

	public static String getStorageToken(Portlet portlet) {
		WebDAVStorage webDAVStorageInstance =
			portlet.getWebDAVStorageInstance();

		if (webDAVStorageInstance == null) {
			return null;
		}

		return webDAVStorageInstance.getToken();
	}

	public static Collection<String> getStorageTokens() {
		return getInstance()._getStorageTokens();
	}

	public static long getTimeout(HttpServletRequest request) {
		long timeout = 0;

		String value = GetterUtil.getString(request.getHeader("Timeout"));

		if (_log.isDebugEnabled()) {
			_log.debug("\"Timeout\" header is " + value);
		}

		int index = value.indexOf(_TIME_PREFIX);

		if (index >= 0) {
			index += _TIME_PREFIX.length();

			if (index < value.length()) {
				timeout = GetterUtil.getLong(value.substring(index));
			}
		}

		return timeout * Time.SECOND;
	}

	public static boolean isOverwrite(HttpServletRequest request) {
		return getInstance()._isOverwrite(request);
	}

	public static String stripManualCheckInRequiredPath(String url) {
		return stripToken(url, DL.MANUAL_CHECK_IN_REQUIRED_PATH);
	}

	public static String stripOfficeExtension(String url) {
		String strippedUrl = stripToken(url, DL.OFFICE_EXTENSION_PATH);

		if (strippedUrl.length() != url.length()) {
			strippedUrl = FileUtil.stripExtension(strippedUrl);
		}

		return strippedUrl;
	}

	public static String stripToken(String url, String token) {
		if (Validator.isNull(url)) {
			return StringPool.BLANK;
		}

		int index = url.indexOf(token);

		if (index >= 0) {
			url =
				url.substring(0, index) + url.substring(index + token.length());
		}

		return url;
	}

	private WebDAVUtil() {
		_storages = ServiceTrackerCollections.openSingleValueMap(
			WebDAVStorage.class, "webdav.storage.token",
			new ServiceTrackerCustomizer<WebDAVStorage, WebDAVStorage>() {

				@Override
				public WebDAVStorage addingService(
					ServiceReference<WebDAVStorage> serviceReference) {

					Registry registry = RegistryUtil.getRegistry();

					WebDAVStorage webDAVStorage = registry.getService(
						serviceReference);

					setToken(serviceReference, webDAVStorage);

					return webDAVStorage;
				}

				@Override
				public void modifiedService(
					ServiceReference<WebDAVStorage> serviceReference,
					WebDAVStorage webDAVStorage) {

					setToken(serviceReference, webDAVStorage);
				}

				@Override
				public void removedService(
					ServiceReference<WebDAVStorage> serviceReference,
					WebDAVStorage webDAVStorage) {
				}

				protected void setToken(
					ServiceReference<WebDAVStorage> serviceReference,
					WebDAVStorage webDAVStorage) {

					String token = (String)serviceReference.getProperty(
						"webdav.storage.token");

					webDAVStorage.setToken(token);
				}

			});
	}

	private void _addStorage(WebDAVStorage storage) {
		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<WebDAVStorage> serviceRegistration =
			registry.registerService(WebDAVStorage.class, storage);

		_serviceRegistrations.put(storage, serviceRegistration);
	}

	private void _deleteStorage(WebDAVStorage storage) {
		ServiceRegistration<WebDAVStorage> serviceRegistration =
			_serviceRegistrations.remove(storage);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	private WebDAVStorage _getStorage(String token) {
		return _storages.getService(token);
	}

	private Collection<String> _getStorageTokens() {
		return _storages.keySet();
	}

	private boolean _isOverwrite(HttpServletRequest request) {
		String value = GetterUtil.getString(request.getHeader("Overwrite"));

		if (StringUtil.equalsIgnoreCase(value, "F") ||
			!GetterUtil.getBoolean(value)) {

			return false;
		}
		else {
			return true;
		}
	}

	private static final String _TIME_PREFIX = "Second-";

	private static final Log _log = LogFactoryUtil.getLog(WebDAVUtil.class);

	private static final WebDAVUtil _instance = new WebDAVUtil();

	private final ServiceRegistrationMap<WebDAVStorage> _serviceRegistrations =
		new ServiceRegistrationMapImpl<>();
	private final ServiceTrackerMap<String, WebDAVStorage> _storages;

}