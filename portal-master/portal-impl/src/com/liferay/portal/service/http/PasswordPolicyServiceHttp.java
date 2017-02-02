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

package com.liferay.portal.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.PasswordPolicyServiceUtil;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link PasswordPolicyServiceUtil} service utility. The
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
 * @author Brian Wing Shun Chan
 * @see PasswordPolicyServiceSoap
 * @see HttpPrincipal
 * @see PasswordPolicyServiceUtil
 * @generated
 */
@ProviderType
public class PasswordPolicyServiceHttp {
	public static com.liferay.portal.kernel.model.PasswordPolicy addPasswordPolicy(
		HttpPrincipal httpPrincipal, java.lang.String name,
		java.lang.String description, boolean changeable,
		boolean changeRequired, long minAge, boolean checkSyntax,
		boolean allowDictionaryWords, int minAlphanumeric, int minLength,
		int minLowerCase, int minNumbers, int minSymbols, int minUpperCase,
		java.lang.String regex, boolean history, int historyCount,
		boolean expireable, long maxAge, long warningTime, int graceLimit,
		boolean lockout, int maxFailure, long lockoutDuration,
		long resetFailureCount, long resetTicketMaxAge,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(PasswordPolicyServiceUtil.class,
					"addPasswordPolicy", _addPasswordPolicyParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					description, changeable, changeRequired, minAge,
					checkSyntax, allowDictionaryWords, minAlphanumeric,
					minLength, minLowerCase, minNumbers, minSymbols,
					minUpperCase, regex, history, historyCount, expireable,
					maxAge, warningTime, graceLimit, lockout, maxFailure,
					lockoutDuration, resetFailureCount, resetTicketMaxAge,
					serviceContext);

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

			return (com.liferay.portal.kernel.model.PasswordPolicy)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deletePasswordPolicy(HttpPrincipal httpPrincipal,
		long passwordPolicyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(PasswordPolicyServiceUtil.class,
					"deletePasswordPolicy", _deletePasswordPolicyParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					passwordPolicyId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.PasswordPolicy fetchPasswordPolicy(
		HttpPrincipal httpPrincipal, long passwordPolicyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(PasswordPolicyServiceUtil.class,
					"fetchPasswordPolicy", _fetchPasswordPolicyParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					passwordPolicyId);

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

			return (com.liferay.portal.kernel.model.PasswordPolicy)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.PasswordPolicy updatePasswordPolicy(
		HttpPrincipal httpPrincipal, long passwordPolicyId,
		java.lang.String name, java.lang.String description,
		boolean changeable, boolean changeRequired, long minAge,
		boolean checkSyntax, boolean allowDictionaryWords, int minAlphanumeric,
		int minLength, int minLowerCase, int minNumbers, int minSymbols,
		int minUpperCase, java.lang.String regex, boolean history,
		int historyCount, boolean expireable, long maxAge, long warningTime,
		int graceLimit, boolean lockout, int maxFailure, long lockoutDuration,
		long resetFailureCount, long resetTicketMaxAge,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(PasswordPolicyServiceUtil.class,
					"updatePasswordPolicy", _updatePasswordPolicyParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					passwordPolicyId, name, description, changeable,
					changeRequired, minAge, checkSyntax, allowDictionaryWords,
					minAlphanumeric, minLength, minLowerCase, minNumbers,
					minSymbols, minUpperCase, regex, history, historyCount,
					expireable, maxAge, warningTime, graceLimit, lockout,
					maxFailure, lockoutDuration, resetFailureCount,
					resetTicketMaxAge, serviceContext);

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

			return (com.liferay.portal.kernel.model.PasswordPolicy)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PasswordPolicyServiceHttp.class);
	private static final Class<?>[] _addPasswordPolicyParameterTypes0 = new Class[] {
			java.lang.String.class, java.lang.String.class, boolean.class,
			boolean.class, long.class, boolean.class, boolean.class, int.class,
			int.class, int.class, int.class, int.class, int.class,
			java.lang.String.class, boolean.class, int.class, boolean.class,
			long.class, long.class, int.class, boolean.class, int.class,
			long.class, long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deletePasswordPolicyParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchPasswordPolicyParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updatePasswordPolicyParameterTypes3 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			boolean.class, boolean.class, long.class, boolean.class,
			boolean.class, int.class, int.class, int.class, int.class, int.class,
			int.class, java.lang.String.class, boolean.class, int.class,
			boolean.class, long.class, long.class, int.class, boolean.class,
			int.class, long.class, long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}