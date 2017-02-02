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
import com.liferay.portal.kernel.service.LayoutSetServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link LayoutSetServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portal.kernel.model.LayoutSetSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portal.kernel.model.LayoutSet}, that is translated to a
 * {@link com.liferay.portal.kernel.model.LayoutSetSoap}. Methods that SOAP cannot
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
 * @see LayoutSetServiceHttp
 * @see com.liferay.portal.kernel.model.LayoutSetSoap
 * @see LayoutSetServiceUtil
 * @generated
 */
@ProviderType
public class LayoutSetServiceSoap {
	/**
	* Updates the state of the layout set prototype link.
	*
	* <p>
	* <strong>Important:</strong> Setting
	* <code>layoutSetPrototypeLinkEnabled</code> to <code>true</code> and
	* <code>layoutSetPrototypeUuid</code> to <code>null</code> when the layout
	* set prototype's current uuid is <code>null</code> will result in an
	* <code>IllegalStateException</code>.
	* </p>
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout set is private to the group
	* @param layoutSetPrototypeLinkEnabled whether the layout set prototype is
	link enabled
	* @param layoutSetPrototypeUuid the uuid of the layout set prototype to
	link with
	*/
	public static void updateLayoutSetPrototypeLinkEnabled(long groupId,
		boolean privateLayout, boolean layoutSetPrototypeLinkEnabled,
		java.lang.String layoutSetPrototypeUuid) throws RemoteException {
		try {
			LayoutSetServiceUtil.updateLayoutSetPrototypeLinkEnabled(groupId,
				privateLayout, layoutSetPrototypeLinkEnabled,
				layoutSetPrototypeUuid);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateLogo(long groupId, boolean privateLayout,
		boolean logo, byte[] bytes) throws RemoteException {
		try {
			LayoutSetServiceUtil.updateLogo(groupId, privateLayout, logo, bytes);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutSetSoap updateLookAndFeel(
		long groupId, boolean privateLayout, java.lang.String themeId,
		java.lang.String colorSchemeId, java.lang.String css)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.model.LayoutSet returnValue = LayoutSetServiceUtil.updateLookAndFeel(groupId,
					privateLayout, themeId, colorSchemeId, css);

			return com.liferay.portal.kernel.model.LayoutSetSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutSetSoap updateSettings(
		long groupId, boolean privateLayout, java.lang.String settings)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.model.LayoutSet returnValue = LayoutSetServiceUtil.updateSettings(groupId,
					privateLayout, settings);

			return com.liferay.portal.kernel.model.LayoutSetSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutSetSoap updateVirtualHost(
		long groupId, boolean privateLayout, java.lang.String virtualHost)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.model.LayoutSet returnValue = LayoutSetServiceUtil.updateVirtualHost(groupId,
					privateLayout, virtualHost);

			return com.liferay.portal.kernel.model.LayoutSetSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LayoutSetServiceSoap.class);
}