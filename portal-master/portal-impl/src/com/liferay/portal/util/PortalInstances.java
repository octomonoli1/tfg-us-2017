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

package com.liferay.portal.util;

import com.liferay.portal.events.EventsProcessorUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.PortletCategory;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.VirtualHost;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.VirtualHostLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Jose Oliver
 * @author Atul Patel
 * @author Mika Koivisto
 */
public class PortalInstances {

	public static void addCompanyId(long companyId) {
		_instance._addCompanyId(companyId);
	}

	public static long getCompanyId(HttpServletRequest request) {
		return _instance._getCompanyId(request);
	}

	public static long[] getCompanyIds() {
		return _instance._getCompanyIds();
	}

	public static long[] getCompanyIdsBySQL() throws SQLException {
		return _instance._getCompanyIdsBySQL();
	}

	public static long getDefaultCompanyId() {
		return _instance._getDefaultCompanyId();
	}

	public static String[] getWebIds() {
		return _instance._getWebIds();
	}

	public static long initCompany(
		ServletContext servletContext, String webId) {

		return _instance._initCompany(servletContext, webId);
	}

	public static boolean isAutoLoginIgnoreHost(String host) {
		return _instance._isAutoLoginIgnoreHost(host);
	}

	public static boolean isAutoLoginIgnorePath(String path) {
		return _instance._isAutoLoginIgnorePath(path);
	}

	public static boolean isCompanyActive(long companyId) {
		return _instance._isCompanyActive(companyId);
	}

	public static boolean isVirtualHostsIgnoreHost(String host) {
		return _instance._isVirtualHostsIgnoreHost(host);
	}

	public static boolean isVirtualHostsIgnorePath(String path) {
		return _instance._isVirtualHostsIgnorePath(path);
	}

	public static void reload(ServletContext servletContext) {
		_instance._reload(servletContext);
	}

	public static void removeCompany(long companyId) {
		_instance._removeCompanyId(companyId);
	}

	private PortalInstances() {
		_companyIds = new long[0];
		_autoLoginIgnoreHosts = SetUtil.fromArray(
			PropsUtil.getArray(PropsKeys.AUTO_LOGIN_IGNORE_HOSTS));
		_autoLoginIgnorePaths = SetUtil.fromArray(
			PropsUtil.getArray(PropsKeys.AUTO_LOGIN_IGNORE_PATHS));
		_virtualHostsIgnoreHosts = SetUtil.fromArray(
			PropsUtil.getArray(PropsKeys.VIRTUAL_HOSTS_IGNORE_HOSTS));
		_virtualHostsIgnorePaths = SetUtil.fromArray(
			PropsUtil.getArray(PropsKeys.VIRTUAL_HOSTS_IGNORE_PATHS));
	}

	private void _addCompanyId(long companyId) {
		if (ArrayUtil.contains(_companyIds, companyId)) {
			return;
		}

		long[] companyIds = new long[_companyIds.length + 1];

		System.arraycopy(_companyIds, 0, companyIds, 0, _companyIds.length);

		companyIds[_companyIds.length] = companyId;

		_companyIds = companyIds;
	}

	private long _getCompanyId(HttpServletRequest request) {
		if (_log.isDebugEnabled()) {
			_log.debug("Get company id");
		}

		Long companyIdObj = (Long)request.getAttribute(WebKeys.COMPANY_ID);

		if (_log.isDebugEnabled()) {
			_log.debug("Company id from request " + companyIdObj);
		}

		if (companyIdObj != null) {
			return companyIdObj.longValue();
		}

		long companyId = _getCompanyIdByVirtualHosts(request);

		if (_log.isDebugEnabled()) {
			_log.debug("Company id from host " + companyId);
		}

		if (companyId <= 0) {
			long cookieCompanyId = GetterUtil.getLong(
				CookieKeys.getCookie(request, CookieKeys.COMPANY_ID, false));

			if (cookieCompanyId > 0) {
				try {
					if (CompanyLocalServiceUtil.fetchCompanyById(
							cookieCompanyId) == null) {

						if (_log.isWarnEnabled()) {
							_log.warn(
								"Company id from cookie " + cookieCompanyId +
									" does not exist");
						}
					}
					else {
						companyId = cookieCompanyId;

						if (_log.isDebugEnabled()) {
							_log.debug("Company id from cookie " + companyId);
						}
					}
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
		}

		if (companyId <= 0) {
			companyId = _getDefaultCompanyId();

			if (_log.isDebugEnabled()) {
				_log.debug("Default company id " + companyId);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Set company id " + companyId);
		}

		request.setAttribute(WebKeys.COMPANY_ID, Long.valueOf(companyId));

		CompanyThreadLocal.setCompanyId(companyId);

		if (Validator.isNotNull(PropsValues.VIRTUAL_HOSTS_DEFAULT_SITE_NAME) &&
			(request.getAttribute(WebKeys.VIRTUAL_HOST_LAYOUT_SET) == null)) {

			try {
				Group group = GroupLocalServiceUtil.getGroup(
					companyId, PropsValues.VIRTUAL_HOSTS_DEFAULT_SITE_NAME);

				LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
					group.getGroupId(), false);

				if (Validator.isNull(layoutSet.getVirtualHostname())) {
					request.setAttribute(
						WebKeys.VIRTUAL_HOST_LAYOUT_SET, layoutSet);
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return companyId;
	}

	private long _getCompanyIdByVirtualHosts(HttpServletRequest request) {
		String host = PortalUtil.getHost(request);

		if (_log.isDebugEnabled()) {
			_log.debug("Host " + host);
		}

		if (Validator.isNull(host) || _isVirtualHostsIgnoreHost(host)) {
			return 0;
		}

		try {
			VirtualHost virtualHost =
				VirtualHostLocalServiceUtil.fetchVirtualHost(host);

			if (virtualHost == null) {
				return 0;
			}

			if (virtualHost.getLayoutSetId() != 0) {
				LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
					virtualHost.getLayoutSetId());

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Company " + virtualHost.getCompanyId() +
							" is associated with layout set " +
								virtualHost.getLayoutSetId());
				}

				request.setAttribute(
					WebKeys.VIRTUAL_HOST_LAYOUT_SET, layoutSet);
			}

			return virtualHost.getCompanyId();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return 0;
	}

	private long[] _getCompanyIds() {
		return _companyIds;
	}

	private long[] _getCompanyIdsBySQL() throws SQLException {
		List<Long> companyIds = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(_GET_COMPANY_IDS);

			rs = ps.executeQuery();

			while (rs.next()) {
				long companyId = rs.getLong("companyId");

				companyIds.add(companyId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return ArrayUtil.toArray(
			companyIds.toArray(new Long[companyIds.size()]));
	}

	private long _getDefaultCompanyId() {
		return _companyIds[0];
	}

	private String[] _getWebIds() {
		if (_webIds != null) {
			return _webIds;
		}

		if (Validator.isNull(PropsValues.COMPANY_DEFAULT_WEB_ID)) {
			throw new RuntimeException("Default web id must not be null");
		}

		try {
			List<Company> companies = CompanyLocalServiceUtil.getCompanies(
				false);

			List<String> webIdsList = new ArrayList<>(companies.size());

			for (Company company : companies) {
				String webId = company.getWebId();

				if (webId.equals(PropsValues.COMPANY_DEFAULT_WEB_ID)) {
					webIdsList.add(0, webId);
				}
				else {
					webIdsList.add(webId);
				}
			}

			_webIds = webIdsList.toArray(new String[webIdsList.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (ArrayUtil.isEmpty(_webIds)) {
			_webIds = new String[] {PropsValues.COMPANY_DEFAULT_WEB_ID};
		}

		return _webIds;
	}

	private long _initCompany(ServletContext servletContext, String webId) {

		// Begin initializing company

		if (_log.isDebugEnabled()) {
			_log.debug("Begin initializing company with web id " + webId);
		}

		long companyId = 0;

		try {
			Company company = CompanyLocalServiceUtil.checkCompany(webId);

			companyId = company.getCompanyId();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		Long currentThreadCompanyId = CompanyThreadLocal.getCompanyId();

		String currentThreadPrincipalName = PrincipalThreadLocal.getName();

		try {
			CompanyThreadLocal.setCompanyId(companyId);

			String principalName = null;

			long userId = PrincipalThreadLocal.getUserId();

			if (userId > 0) {
				User user = UserLocalServiceUtil.fetchUser(userId);

				if ((user != null) && (user.getCompanyId() == companyId)) {
					principalName = currentThreadPrincipalName;
				}
			}

			PrincipalThreadLocal.setName(principalName);

			// Initialize display

			if (_log.isDebugEnabled()) {
				_log.debug("Initialize display");
			}

			try {
				String xml = HttpUtil.URLtoString(
					servletContext.getResource("/WEB-INF/liferay-display.xml"));

				PortletCategory portletCategory =
					(PortletCategory)WebAppPool.get(
						companyId, WebKeys.PORTLET_CATEGORY);

				if (portletCategory == null) {
					portletCategory = new PortletCategory();
				}

				PortletCategory newPortletCategory =
					PortletLocalServiceUtil.getEARDisplay(xml);

				portletCategory.merge(newPortletCategory);

				for (int i = 0; i < _companyIds.length; i++) {
					long currentCompanyId = _companyIds[i];

					PortletCategory currentPortletCategory =
						(PortletCategory)WebAppPool.get(
							currentCompanyId, WebKeys.PORTLET_CATEGORY);

					if (currentPortletCategory != null) {
						portletCategory.merge(currentPortletCategory);
					}
				}

				WebAppPool.put(
					companyId, WebKeys.PORTLET_CATEGORY, portletCategory);
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			// Process application startup events

			if (_log.isDebugEnabled()) {
				_log.debug("Process application startup events");
			}

			try {
				EventsProcessorUtil.process(
					PropsKeys.APPLICATION_STARTUP_EVENTS,
					PropsValues.APPLICATION_STARTUP_EVENTS,
					new String[] {String.valueOf(companyId)});
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			// End initializing company

			if (_log.isDebugEnabled()) {
				_log.debug(
					"End initializing company with web id " + webId +
						" and company id " + companyId);
			}

			addCompanyId(companyId);
		}
		finally {
			CompanyThreadLocal.setCompanyId(currentThreadCompanyId);

			PrincipalThreadLocal.setName(currentThreadPrincipalName);
		}

		return companyId;
	}

	private boolean _isAutoLoginIgnoreHost(String host) {
		return _autoLoginIgnoreHosts.contains(host);
	}

	private boolean _isAutoLoginIgnorePath(String path) {
		return _autoLoginIgnorePaths.contains(path);
	}

	private boolean _isCompanyActive(long companyId) {
		try {
			Company company = CompanyLocalServiceUtil.fetchCompanyById(
				companyId);

			if (company != null) {
				return company.isActive();
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	private boolean _isVirtualHostsIgnoreHost(String host) {
		return _virtualHostsIgnoreHosts.contains(host);
	}

	private boolean _isVirtualHostsIgnorePath(String path) {
		return _virtualHostsIgnorePaths.contains(path);
	}

	private void _reload(ServletContext servletContext) {
		_companyIds = new long[0];
		_webIds = null;

		String[] webIds = _getWebIds();

		for (String webId : webIds) {
			_initCompany(servletContext, webId);
		}
	}

	private void _removeCompanyId(long companyId) {
		try {
			EventsProcessorUtil.process(
				PropsKeys.APPLICATION_SHUTDOWN_EVENTS,
				PropsValues.APPLICATION_SHUTDOWN_EVENTS,
				new String[] {String.valueOf(companyId)});
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		_companyIds = ArrayUtil.remove(_companyIds, companyId);
		_webIds = null;

		_getWebIds();

		WebAppPool.remove(companyId, WebKeys.PORTLET_CATEGORY);
	}

	private static final String _GET_COMPANY_IDS =
		"select companyId from Company";

	private static final Log _log = LogFactoryUtil.getLog(
		PortalInstances.class);

	private static final PortalInstances _instance = new PortalInstances();

	private final Set<String> _autoLoginIgnoreHosts;
	private final Set<String> _autoLoginIgnorePaths;
	private long[] _companyIds;
	private final Set<String> _virtualHostsIgnoreHosts;
	private final Set<String> _virtualHostsIgnorePaths;
	private String[] _webIds;

}