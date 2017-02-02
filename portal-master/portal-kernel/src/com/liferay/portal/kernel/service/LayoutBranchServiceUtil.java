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

package com.liferay.portal.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for LayoutBranch. This utility wraps
 * {@link com.liferay.portal.service.impl.LayoutBranchServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutBranchService
 * @see com.liferay.portal.service.base.LayoutBranchServiceBaseImpl
 * @see com.liferay.portal.service.impl.LayoutBranchServiceImpl
 * @generated
 */
@ProviderType
public class LayoutBranchServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.LayoutBranchServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.model.LayoutBranch addLayoutBranch(
		long layoutRevisionId, java.lang.String name,
		java.lang.String description, boolean master,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLayoutBranch(layoutRevisionId, name, description,
			master, serviceContext);
	}

	public static com.liferay.portal.kernel.model.LayoutBranch updateLayoutBranch(
		long layoutBranchId, java.lang.String name,
		java.lang.String description, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateLayoutBranch(layoutBranchId, name, description,
			serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void deleteLayoutBranch(long layoutBranchId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteLayoutBranch(layoutBranchId);
	}

	public static LayoutBranchService getService() {
		if (_service == null) {
			_service = (LayoutBranchService)PortalBeanLocatorUtil.locate(LayoutBranchService.class.getName());

			ReferenceRegistry.registerReference(LayoutBranchServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static LayoutBranchService _service;
}