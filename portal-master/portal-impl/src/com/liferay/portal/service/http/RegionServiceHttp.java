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
import com.liferay.portal.kernel.service.RegionServiceUtil;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link RegionServiceUtil} service utility. The
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
 * @see RegionServiceSoap
 * @see HttpPrincipal
 * @see RegionServiceUtil
 * @generated
 */
@ProviderType
public class RegionServiceHttp {
	public static com.liferay.portal.kernel.model.Region addRegion(
		HttpPrincipal httpPrincipal, long countryId,
		java.lang.String regionCode, java.lang.String name, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(RegionServiceUtil.class,
					"addRegion", _addRegionParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					countryId, regionCode, name, active);

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

			return (com.liferay.portal.kernel.model.Region)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Region fetchRegion(
		HttpPrincipal httpPrincipal, long regionId) {
		try {
			MethodKey methodKey = new MethodKey(RegionServiceUtil.class,
					"fetchRegion", _fetchRegionParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, regionId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Region)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Region fetchRegion(
		HttpPrincipal httpPrincipal, long countryId, java.lang.String regionCode) {
		try {
			MethodKey methodKey = new MethodKey(RegionServiceUtil.class,
					"fetchRegion", _fetchRegionParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					countryId, regionCode);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.model.Region)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Region getRegion(
		HttpPrincipal httpPrincipal, long regionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(RegionServiceUtil.class,
					"getRegion", _getRegionParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, regionId);

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

			return (com.liferay.portal.kernel.model.Region)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.model.Region getRegion(
		HttpPrincipal httpPrincipal, long countryId, java.lang.String regionCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(RegionServiceUtil.class,
					"getRegion", _getRegionParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					countryId, regionCode);

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

			return (com.liferay.portal.kernel.model.Region)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Region> getRegions(
		HttpPrincipal httpPrincipal) {
		try {
			MethodKey methodKey = new MethodKey(RegionServiceUtil.class,
					"getRegions", _getRegionsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Region>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Region> getRegions(
		HttpPrincipal httpPrincipal, boolean active) {
		try {
			MethodKey methodKey = new MethodKey(RegionServiceUtil.class,
					"getRegions", _getRegionsParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, active);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Region>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Region> getRegions(
		HttpPrincipal httpPrincipal, long countryId) {
		try {
			MethodKey methodKey = new MethodKey(RegionServiceUtil.class,
					"getRegions", _getRegionsParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, countryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Region>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Region> getRegions(
		HttpPrincipal httpPrincipal, long countryId, boolean active) {
		try {
			MethodKey methodKey = new MethodKey(RegionServiceUtil.class,
					"getRegions", _getRegionsParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					countryId, active);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Region>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(RegionServiceHttp.class);
	private static final Class<?>[] _addRegionParameterTypes0 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			boolean.class
		};
	private static final Class<?>[] _fetchRegionParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchRegionParameterTypes2 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getRegionParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getRegionParameterTypes4 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getRegionsParameterTypes5 = new Class[] {  };
	private static final Class<?>[] _getRegionsParameterTypes6 = new Class[] {
			boolean.class
		};
	private static final Class<?>[] _getRegionsParameterTypes7 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getRegionsParameterTypes8 = new Class[] {
			long.class, boolean.class
		};
}