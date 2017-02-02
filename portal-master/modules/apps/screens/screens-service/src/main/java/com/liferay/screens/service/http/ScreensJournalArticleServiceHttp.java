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

package com.liferay.screens.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.liferay.screens.service.ScreensJournalArticleServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link ScreensJournalArticleServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author José Manuel Navarro
 * @see ScreensJournalArticleServiceSoap
 * @see HttpPrincipal
 * @see ScreensJournalArticleServiceUtil
 * @generated
 */
@ProviderType
public class ScreensJournalArticleServiceHttp {
	public static java.lang.String getJournalArticleContent(
		HttpPrincipal httpPrincipal, long classPK, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ScreensJournalArticleServiceUtil.class,
					"getJournalArticleContent",
					_getJournalArticleContentParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, classPK,
					locale);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getJournalArticleContent(
		HttpPrincipal httpPrincipal, long classPK, long ddmTemplateId,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ScreensJournalArticleServiceUtil.class,
					"getJournalArticleContent",
					_getJournalArticleContentParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, classPK,
					ddmTemplateId, locale);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getJournalArticleContent(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String articleId,
		long ddmTemplateId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ScreensJournalArticleServiceUtil.class,
					"getJournalArticleContent",
					_getJournalArticleContentParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					articleId, ddmTemplateId, locale);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ScreensJournalArticleServiceHttp.class);
	private static final Class<?>[] _getJournalArticleContentParameterTypes0 = new Class[] {
			long.class, java.util.Locale.class
		};
	private static final Class<?>[] _getJournalArticleContentParameterTypes1 = new Class[] {
			long.class, long.class, java.util.Locale.class
		};
	private static final Class<?>[] _getJournalArticleContentParameterTypes2 = new Class[] {
			long.class, java.lang.String.class, long.class,
			java.util.Locale.class
		};
}