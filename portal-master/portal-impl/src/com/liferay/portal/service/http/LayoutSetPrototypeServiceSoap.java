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
import com.liferay.portal.kernel.service.LayoutSetPrototypeServiceUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * {@link LayoutSetPrototypeServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portal.kernel.model.LayoutSetPrototypeSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portal.kernel.model.LayoutSetPrototype}, that is translated to a
 * {@link com.liferay.portal.kernel.model.LayoutSetPrototypeSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetPrototypeServiceHttp
 * @see com.liferay.portal.kernel.model.LayoutSetPrototypeSoap
 * @see LayoutSetPrototypeServiceUtil
 * @generated
 */
@ProviderType
public class LayoutSetPrototypeServiceSoap {
	public static com.liferay.portal.kernel.model.LayoutSetPrototypeSoap addLayoutSetPrototype(
		java.lang.String[] nameMapLanguageIds,
		java.lang.String[] nameMapValues,
		java.lang.String[] descriptionMapLanguageIds,
		java.lang.String[] descriptionMapValues, boolean active,
		boolean layoutsUpdateable,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(nameMapLanguageIds,
					nameMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);

			com.liferay.portal.kernel.model.LayoutSetPrototype returnValue = LayoutSetPrototypeServiceUtil.addLayoutSetPrototype(nameMap,
					descriptionMap, active, layoutsUpdateable, serviceContext);

			return com.liferay.portal.kernel.model.LayoutSetPrototypeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #addLayoutSetPrototype(Map,
	Map, boolean, boolean, ServiceContext)}
	*/
	@Deprecated
	public static com.liferay.portal.kernel.model.LayoutSetPrototypeSoap addLayoutSetPrototype(
		java.lang.String[] nameMapLanguageIds,
		java.lang.String[] nameMapValues, java.lang.String description,
		boolean active, boolean layoutsUpdateable,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(nameMapLanguageIds,
					nameMapValues);

			com.liferay.portal.kernel.model.LayoutSetPrototype returnValue = LayoutSetPrototypeServiceUtil.addLayoutSetPrototype(nameMap,
					description, active, layoutsUpdateable, serviceContext);

			return com.liferay.portal.kernel.model.LayoutSetPrototypeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteLayoutSetPrototype(long layoutSetPrototypeId)
		throws RemoteException {
		try {
			LayoutSetPrototypeServiceUtil.deleteLayoutSetPrototype(layoutSetPrototypeId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutSetPrototypeSoap fetchLayoutSetPrototype(
		long layoutSetPrototypeId) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.LayoutSetPrototype returnValue = LayoutSetPrototypeServiceUtil.fetchLayoutSetPrototype(layoutSetPrototypeId);

			return com.liferay.portal.kernel.model.LayoutSetPrototypeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutSetPrototypeSoap getLayoutSetPrototype(
		long layoutSetPrototypeId) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.LayoutSetPrototype returnValue = LayoutSetPrototypeServiceUtil.getLayoutSetPrototype(layoutSetPrototypeId);

			return com.liferay.portal.kernel.model.LayoutSetPrototypeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutSetPrototypeSoap[] search(
		long companyId, java.lang.Boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.LayoutSetPrototype> obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portal.kernel.model.LayoutSetPrototype> returnValue =
				LayoutSetPrototypeServiceUtil.search(companyId, active, obc);

			return com.liferay.portal.kernel.model.LayoutSetPrototypeSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutSetPrototypeSoap updateLayoutSetPrototype(
		long layoutSetPrototypeId, java.lang.String[] nameMapLanguageIds,
		java.lang.String[] nameMapValues,
		java.lang.String[] descriptionMapLanguageIds,
		java.lang.String[] descriptionMapValues, boolean active,
		boolean layoutsUpdateable,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(nameMapLanguageIds,
					nameMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);

			com.liferay.portal.kernel.model.LayoutSetPrototype returnValue = LayoutSetPrototypeServiceUtil.updateLayoutSetPrototype(layoutSetPrototypeId,
					nameMap, descriptionMap, active, layoutsUpdateable,
					serviceContext);

			return com.liferay.portal.kernel.model.LayoutSetPrototypeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link
	#updateLayoutSetPrototype(long, Map, Map, boolean, boolean,
	ServiceContext)}
	*/
	@Deprecated
	public static com.liferay.portal.kernel.model.LayoutSetPrototypeSoap updateLayoutSetPrototype(
		long layoutSetPrototypeId, java.lang.String[] nameMapLanguageIds,
		java.lang.String[] nameMapValues, java.lang.String description,
		boolean active, boolean layoutsUpdateable,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(nameMapLanguageIds,
					nameMapValues);

			com.liferay.portal.kernel.model.LayoutSetPrototype returnValue = LayoutSetPrototypeServiceUtil.updateLayoutSetPrototype(layoutSetPrototypeId,
					nameMap, description, active, layoutsUpdateable,
					serviceContext);

			return com.liferay.portal.kernel.model.LayoutSetPrototypeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutSetPrototypeSoap updateLayoutSetPrototype(
		long layoutSetPrototypeId, java.lang.String settings)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.model.LayoutSetPrototype returnValue = LayoutSetPrototypeServiceUtil.updateLayoutSetPrototype(layoutSetPrototypeId,
					settings);

			return com.liferay.portal.kernel.model.LayoutSetPrototypeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LayoutSetPrototypeServiceSoap.class);
}