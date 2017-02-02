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
import com.liferay.portal.kernel.service.OrganizationServiceUtil;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link OrganizationServiceUtil} service utility. The
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
 * @see OrganizationServiceSoap
 * @see HttpPrincipal
 * @see OrganizationServiceUtil
 * @generated
 */
@ProviderType
public class OrganizationServiceHttp {
	public static void addGroupOrganizations(HttpPrincipal httpPrincipal,
		long groupId, long[] organizationIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"addGroupOrganizations",
					_addGroupOrganizationsParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					organizationIds);

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

	public static com.liferay.portal.kernel.model.Organization addOrganization(
		HttpPrincipal httpPrincipal, long parentOrganizationId,
		java.lang.String name, java.lang.String type, long regionId,
		long countryId, long statusId, java.lang.String comments, boolean site,
		java.util.List<com.liferay.portal.kernel.model.Address> addresses,
		java.util.List<com.liferay.portal.kernel.model.EmailAddress> emailAddresses,
		java.util.List<com.liferay.portal.kernel.model.OrgLabor> orgLabors,
		java.util.List<com.liferay.portal.kernel.model.Phone> phones,
		java.util.List<com.liferay.portal.kernel.model.Website> websites,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"addOrganization", _addOrganizationParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentOrganizationId, name, type, regionId, countryId,
					statusId, comments, site, addresses, emailAddresses,
					orgLabors, phones, websites, serviceContext);

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

			return (com.liferay.portal.kernel.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Organization addOrganization(
		HttpPrincipal httpPrincipal, long parentOrganizationId,
		java.lang.String name, java.lang.String type, long regionId,
		long countryId, long statusId, java.lang.String comments, boolean site,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"addOrganization", _addOrganizationParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentOrganizationId, name, type, regionId, countryId,
					statusId, comments, site, serviceContext);

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

			return (com.liferay.portal.kernel.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void addPasswordPolicyOrganizations(
		HttpPrincipal httpPrincipal, long passwordPolicyId,
		long[] organizationIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"addPasswordPolicyOrganizations",
					_addPasswordPolicyOrganizationsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					passwordPolicyId, organizationIds);

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

	public static void deleteLogo(HttpPrincipal httpPrincipal,
		long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"deleteLogo", _deleteLogoParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId);

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

	public static void deleteOrganization(HttpPrincipal httpPrincipal,
		long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"deleteOrganization", _deleteOrganizationParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId);

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

	public static com.liferay.portal.kernel.model.Organization fetchOrganization(
		HttpPrincipal httpPrincipal, long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"fetchOrganization", _fetchOrganizationParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId);

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

			return (com.liferay.portal.kernel.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Organization getOrganization(
		HttpPrincipal httpPrincipal, long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"getOrganization", _getOrganizationParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId);

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

			return (com.liferay.portal.kernel.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long getOrganizationId(HttpPrincipal httpPrincipal,
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"getOrganizationId", _getOrganizationIdParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, name);

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

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Organization> getOrganizations(
		HttpPrincipal httpPrincipal, long companyId, long parentOrganizationId) {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"getOrganizations", _getOrganizationsParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, parentOrganizationId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Organization>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Organization> getOrganizations(
		HttpPrincipal httpPrincipal, long companyId, long parentOrganizationId,
		int start, int end) {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"getOrganizations", _getOrganizationsParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, parentOrganizationId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Organization>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getOrganizationsCount(HttpPrincipal httpPrincipal,
		long companyId, long parentOrganizationId) {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"getOrganizationsCount",
					_getOrganizationsCountParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, parentOrganizationId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Organization> getUserOrganizations(
		HttpPrincipal httpPrincipal, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"getUserOrganizations",
					_getUserOrganizationsParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId);

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

			return (java.util.List<com.liferay.portal.kernel.model.Organization>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void setGroupOrganizations(HttpPrincipal httpPrincipal,
		long groupId, long[] organizationIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"setGroupOrganizations",
					_setGroupOrganizationsParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					organizationIds);

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

	public static void unsetGroupOrganizations(HttpPrincipal httpPrincipal,
		long groupId, long[] organizationIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"unsetGroupOrganizations",
					_unsetGroupOrganizationsParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					organizationIds);

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

	public static void unsetPasswordPolicyOrganizations(
		HttpPrincipal httpPrincipal, long passwordPolicyId,
		long[] organizationIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"unsetPasswordPolicyOrganizations",
					_unsetPasswordPolicyOrganizationsParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					passwordPolicyId, organizationIds);

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

	public static com.liferay.portal.kernel.model.Organization updateOrganization(
		HttpPrincipal httpPrincipal, long organizationId,
		long parentOrganizationId, java.lang.String name,
		java.lang.String type, long regionId, long countryId, long statusId,
		java.lang.String comments, boolean logo, byte[] logoBytes,
		boolean site,
		java.util.List<com.liferay.portal.kernel.model.Address> addresses,
		java.util.List<com.liferay.portal.kernel.model.EmailAddress> emailAddresses,
		java.util.List<com.liferay.portal.kernel.model.OrgLabor> orgLabors,
		java.util.List<com.liferay.portal.kernel.model.Phone> phones,
		java.util.List<com.liferay.portal.kernel.model.Website> websites,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"updateOrganization", _updateOrganizationParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId, parentOrganizationId, name, type, regionId,
					countryId, statusId, comments, logo, logoBytes, site,
					addresses, emailAddresses, orgLabors, phones, websites,
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

			return (com.liferay.portal.kernel.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Organization updateOrganization(
		HttpPrincipal httpPrincipal, long organizationId,
		long parentOrganizationId, java.lang.String name,
		java.lang.String type, long regionId, long countryId, long statusId,
		java.lang.String comments, boolean site,
		java.util.List<com.liferay.portal.kernel.model.Address> addresses,
		java.util.List<com.liferay.portal.kernel.model.EmailAddress> emailAddresses,
		java.util.List<com.liferay.portal.kernel.model.OrgLabor> orgLabors,
		java.util.List<com.liferay.portal.kernel.model.Phone> phones,
		java.util.List<com.liferay.portal.kernel.model.Website> websites,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"updateOrganization", _updateOrganizationParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId, parentOrganizationId, name, type, regionId,
					countryId, statusId, comments, site, addresses,
					emailAddresses, orgLabors, phones, websites, serviceContext);

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

			return (com.liferay.portal.kernel.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Organization updateOrganization(
		HttpPrincipal httpPrincipal, long organizationId,
		long parentOrganizationId, java.lang.String name,
		java.lang.String type, long regionId, long countryId, long statusId,
		java.lang.String comments, boolean site,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class,
					"updateOrganization", _updateOrganizationParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId, parentOrganizationId, name, type, regionId,
					countryId, statusId, comments, site, serviceContext);

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

			return (com.liferay.portal.kernel.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(OrganizationServiceHttp.class);
	private static final Class<?>[] _addGroupOrganizationsParameterTypes0 = new Class[] {
			long.class, long[].class
		};
	private static final Class<?>[] _addOrganizationParameterTypes1 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			long.class, long.class, long.class, java.lang.String.class,
			boolean.class, java.util.List.class, java.util.List.class,
			java.util.List.class, java.util.List.class, java.util.List.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addOrganizationParameterTypes2 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			long.class, long.class, long.class, java.lang.String.class,
			boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addPasswordPolicyOrganizationsParameterTypes3 =
		new Class[] { long.class, long[].class };
	private static final Class<?>[] _deleteLogoParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteOrganizationParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchOrganizationParameterTypes6 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getOrganizationParameterTypes7 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getOrganizationIdParameterTypes8 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getOrganizationsParameterTypes9 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getOrganizationsParameterTypes10 = new Class[] {
			long.class, long.class, int.class, int.class
		};
	private static final Class<?>[] _getOrganizationsCountParameterTypes11 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getUserOrganizationsParameterTypes12 = new Class[] {
			long.class
		};
	private static final Class<?>[] _setGroupOrganizationsParameterTypes13 = new Class[] {
			long.class, long[].class
		};
	private static final Class<?>[] _unsetGroupOrganizationsParameterTypes14 = new Class[] {
			long.class, long[].class
		};
	private static final Class<?>[] _unsetPasswordPolicyOrganizationsParameterTypes15 =
		new Class[] { long.class, long[].class };
	private static final Class<?>[] _updateOrganizationParameterTypes16 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class, long.class, long.class, long.class,
			java.lang.String.class, boolean.class, byte[].class, boolean.class,
			java.util.List.class, java.util.List.class, java.util.List.class,
			java.util.List.class, java.util.List.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateOrganizationParameterTypes17 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class, long.class, long.class, long.class,
			java.lang.String.class, boolean.class, java.util.List.class,
			java.util.List.class, java.util.List.class, java.util.List.class,
			java.util.List.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateOrganizationParameterTypes18 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class, long.class, long.class, long.class,
			java.lang.String.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}