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
import com.liferay.portal.kernel.service.CompanyServiceUtil;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CompanyServiceUtil} service utility. The
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
 * @see CompanyServiceSoap
 * @see HttpPrincipal
 * @see CompanyServiceUtil
 * @generated
 */
@ProviderType
public class CompanyServiceHttp {
	public static com.liferay.portal.kernel.model.Company addCompany(
		HttpPrincipal httpPrincipal, java.lang.String webId,
		java.lang.String virtualHost, java.lang.String mx, boolean system,
		int maxUsers, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"addCompany", _addCompanyParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, webId,
					virtualHost, mx, system, maxUsers, active);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Company deleteCompany(
		HttpPrincipal httpPrincipal, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"deleteCompany", _deleteCompanyParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, companyId);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteLogo(HttpPrincipal httpPrincipal, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"deleteLogo", _deleteLogoParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, companyId);

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

	public static com.liferay.portal.kernel.model.Company getCompanyById(
		HttpPrincipal httpPrincipal, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"getCompanyById", _getCompanyByIdParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, companyId);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Company getCompanyByLogoId(
		HttpPrincipal httpPrincipal, long logoId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"getCompanyByLogoId", _getCompanyByLogoIdParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, logoId);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Company getCompanyByMx(
		HttpPrincipal httpPrincipal, java.lang.String mx)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"getCompanyByMx", _getCompanyByMxParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, mx);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Company getCompanyByVirtualHost(
		HttpPrincipal httpPrincipal, java.lang.String virtualHost)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"getCompanyByVirtualHost",
					_getCompanyByVirtualHostParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					virtualHost);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Company getCompanyByWebId(
		HttpPrincipal httpPrincipal, java.lang.String webId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"getCompanyByWebId", _getCompanyByWebIdParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, webId);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void removePreferences(HttpPrincipal httpPrincipal,
		long companyId, java.lang.String[] keys)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"removePreferences", _removePreferencesParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, keys);

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

	public static com.liferay.portal.kernel.model.Company updateCompany(
		HttpPrincipal httpPrincipal, long companyId,
		java.lang.String virtualHost, java.lang.String mx, int maxUsers,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"updateCompany", _updateCompanyParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, virtualHost, mx, maxUsers, active);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Company updateCompany(
		HttpPrincipal httpPrincipal, long companyId,
		java.lang.String virtualHost, java.lang.String mx,
		java.lang.String homeURL, boolean logo, byte[] logoBytes,
		java.lang.String name, java.lang.String legalName,
		java.lang.String legalId, java.lang.String legalType,
		java.lang.String sicCode, java.lang.String tickerSymbol,
		java.lang.String industry, java.lang.String type, java.lang.String size)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"updateCompany", _updateCompanyParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, virtualHost, mx, homeURL, logo, logoBytes, name,
					legalName, legalId, legalType, sicCode, tickerSymbol,
					industry, type, size);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Company updateCompany(
		HttpPrincipal httpPrincipal, long companyId,
		java.lang.String virtualHost, java.lang.String mx,
		java.lang.String homeURL, boolean logo, byte[] logoBytes,
		java.lang.String name, java.lang.String legalName,
		java.lang.String legalId, java.lang.String legalType,
		java.lang.String sicCode, java.lang.String tickerSymbol,
		java.lang.String industry, java.lang.String type,
		java.lang.String size, java.lang.String languageId,
		java.lang.String timeZoneId,
		java.util.List<com.liferay.portal.kernel.model.Address> addresses,
		java.util.List<com.liferay.portal.kernel.model.EmailAddress> emailAddresses,
		java.util.List<com.liferay.portal.kernel.model.Phone> phones,
		java.util.List<com.liferay.portal.kernel.model.Website> websites,
		com.liferay.portal.kernel.util.UnicodeProperties properties)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"updateCompany", _updateCompanyParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, virtualHost, mx, homeURL, logo, logoBytes, name,
					legalName, legalId, legalType, sicCode, tickerSymbol,
					industry, type, size, languageId, timeZoneId, addresses,
					emailAddresses, phones, websites, properties);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Company updateCompany(
		HttpPrincipal httpPrincipal, long companyId,
		java.lang.String virtualHost, java.lang.String mx,
		java.lang.String homeURL, java.lang.String name,
		java.lang.String legalName, java.lang.String legalId,
		java.lang.String legalType, java.lang.String sicCode,
		java.lang.String tickerSymbol, java.lang.String industry,
		java.lang.String type, java.lang.String size)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"updateCompany", _updateCompanyParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, virtualHost, mx, homeURL, name, legalName,
					legalId, legalType, sicCode, tickerSymbol, industry, type,
					size);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Company updateCompany(
		HttpPrincipal httpPrincipal, long companyId,
		java.lang.String virtualHost, java.lang.String mx,
		java.lang.String homeURL, java.lang.String name,
		java.lang.String legalName, java.lang.String legalId,
		java.lang.String legalType, java.lang.String sicCode,
		java.lang.String tickerSymbol, java.lang.String industry,
		java.lang.String type, java.lang.String size,
		java.lang.String languageId, java.lang.String timeZoneId,
		java.util.List<com.liferay.portal.kernel.model.Address> addresses,
		java.util.List<com.liferay.portal.kernel.model.EmailAddress> emailAddresses,
		java.util.List<com.liferay.portal.kernel.model.Phone> phones,
		java.util.List<com.liferay.portal.kernel.model.Website> websites,
		com.liferay.portal.kernel.util.UnicodeProperties properties)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"updateCompany", _updateCompanyParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, virtualHost, mx, homeURL, name, legalName,
					legalId, legalType, sicCode, tickerSymbol, industry, type,
					size, languageId, timeZoneId, addresses, emailAddresses,
					phones, websites, properties);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void updateDisplay(HttpPrincipal httpPrincipal,
		long companyId, java.lang.String languageId, java.lang.String timeZoneId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"updateDisplay", _updateDisplayParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, languageId, timeZoneId);

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

	public static com.liferay.portal.kernel.model.Company updateLogo(
		HttpPrincipal httpPrincipal, long companyId, byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"updateLogo", _updateLogoParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, bytes);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Company updateLogo(
		HttpPrincipal httpPrincipal, long companyId,
		java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"updateLogo", _updateLogoParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, inputStream);

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

			return (com.liferay.portal.kernel.model.Company)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void updatePreferences(HttpPrincipal httpPrincipal,
		long companyId,
		com.liferay.portal.kernel.util.UnicodeProperties properties)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"updatePreferences", _updatePreferencesParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, properties);

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

	public static void updateSecurity(HttpPrincipal httpPrincipal,
		long companyId, java.lang.String authType, boolean autoLogin,
		boolean sendPassword, boolean strangers, boolean strangersWithMx,
		boolean strangersVerify, boolean siteLogo)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CompanyServiceUtil.class,
					"updateSecurity", _updateSecurityParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, authType, autoLogin, sendPassword, strangers,
					strangersWithMx, strangersVerify, siteLogo);

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

	private static Log _log = LogFactoryUtil.getLog(CompanyServiceHttp.class);
	private static final Class<?>[] _addCompanyParameterTypes0 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, boolean.class, int.class, boolean.class
		};
	private static final Class<?>[] _deleteCompanyParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteLogoParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCompanyByIdParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCompanyByLogoIdParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCompanyByMxParameterTypes5 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _getCompanyByVirtualHostParameterTypes6 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _getCompanyByWebIdParameterTypes7 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _removePreferencesParameterTypes8 = new Class[] {
			long.class, java.lang.String[].class
		};
	private static final Class<?>[] _updateCompanyParameterTypes9 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			int.class, boolean.class
		};
	private static final Class<?>[] _updateCompanyParameterTypes10 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, boolean.class, byte[].class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class
		};
	private static final Class<?>[] _updateCompanyParameterTypes11 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, boolean.class, byte[].class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.util.List.class, java.util.List.class,
			java.util.List.class, java.util.List.class,
			com.liferay.portal.kernel.util.UnicodeProperties.class
		};
	private static final Class<?>[] _updateCompanyParameterTypes12 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _updateCompanyParameterTypes13 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, java.util.List.class,
			java.util.List.class, java.util.List.class, java.util.List.class,
			com.liferay.portal.kernel.util.UnicodeProperties.class
		};
	private static final Class<?>[] _updateDisplayParameterTypes14 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _updateLogoParameterTypes15 = new Class[] {
			long.class, byte[].class
		};
	private static final Class<?>[] _updateLogoParameterTypes16 = new Class[] {
			long.class, java.io.InputStream.class
		};
	private static final Class<?>[] _updatePreferencesParameterTypes17 = new Class[] {
			long.class, com.liferay.portal.kernel.util.UnicodeProperties.class
		};
	private static final Class<?>[] _updateSecurityParameterTypes18 = new Class[] {
			long.class, java.lang.String.class, boolean.class, boolean.class,
			boolean.class, boolean.class, boolean.class, boolean.class
		};
}