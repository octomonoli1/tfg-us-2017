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
import com.liferay.portal.kernel.service.LayoutSetBranchServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link LayoutSetBranchServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portal.kernel.model.LayoutSetBranchSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portal.kernel.model.LayoutSetBranch}, that is translated to a
 * {@link com.liferay.portal.kernel.model.LayoutSetBranchSoap}. Methods that SOAP cannot
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
 * @see LayoutSetBranchServiceHttp
 * @see com.liferay.portal.kernel.model.LayoutSetBranchSoap
 * @see LayoutSetBranchServiceUtil
 * @generated
 */
@ProviderType
public class LayoutSetBranchServiceSoap {
	public static com.liferay.portal.kernel.model.LayoutSetBranchSoap addLayoutSetBranch(
		long groupId, boolean privateLayout, java.lang.String name,
		java.lang.String description, boolean master,
		long copyLayoutSetBranchId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.model.LayoutSetBranch returnValue = LayoutSetBranchServiceUtil.addLayoutSetBranch(groupId,
					privateLayout, name, description, master,
					copyLayoutSetBranchId, serviceContext);

			return com.liferay.portal.kernel.model.LayoutSetBranchSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteLayoutSetBranch(long layoutSetBranchId)
		throws RemoteException {
		try {
			LayoutSetBranchServiceUtil.deleteLayoutSetBranch(layoutSetBranchId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutSetBranchSoap[] getLayoutSetBranches(
		long groupId, boolean privateLayout) throws RemoteException {
		try {
			java.util.List<com.liferay.portal.kernel.model.LayoutSetBranch> returnValue =
				LayoutSetBranchServiceUtil.getLayoutSetBranches(groupId,
					privateLayout);

			return com.liferay.portal.kernel.model.LayoutSetBranchSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutSetBranchSoap mergeLayoutSetBranch(
		long layoutSetBranchId, long mergeLayoutSetBranchId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.model.LayoutSetBranch returnValue = LayoutSetBranchServiceUtil.mergeLayoutSetBranch(layoutSetBranchId,
					mergeLayoutSetBranchId, serviceContext);

			return com.liferay.portal.kernel.model.LayoutSetBranchSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutSetBranchSoap updateLayoutSetBranch(
		long groupId, long layoutSetBranchId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.model.LayoutSetBranch returnValue = LayoutSetBranchServiceUtil.updateLayoutSetBranch(groupId,
					layoutSetBranchId, name, description, serviceContext);

			return com.liferay.portal.kernel.model.LayoutSetBranchSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LayoutSetBranchServiceSoap.class);
}