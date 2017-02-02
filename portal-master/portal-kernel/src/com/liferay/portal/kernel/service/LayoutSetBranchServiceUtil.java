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
 * Provides the remote service utility for LayoutSetBranch. This utility wraps
 * {@link com.liferay.portal.service.impl.LayoutSetBranchServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetBranchService
 * @see com.liferay.portal.service.base.LayoutSetBranchServiceBaseImpl
 * @see com.liferay.portal.service.impl.LayoutSetBranchServiceImpl
 * @generated
 */
@ProviderType
public class LayoutSetBranchServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.LayoutSetBranchServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.model.LayoutSetBranch addLayoutSetBranch(
		long groupId, boolean privateLayout, java.lang.String name,
		java.lang.String description, boolean master,
		long copyLayoutSetBranchId, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLayoutSetBranch(groupId, privateLayout, name,
			description, master, copyLayoutSetBranchId, serviceContext);
	}

	public static com.liferay.portal.kernel.model.LayoutSetBranch mergeLayoutSetBranch(
		long layoutSetBranchId, long mergeLayoutSetBranchId,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .mergeLayoutSetBranch(layoutSetBranchId,
			mergeLayoutSetBranchId, serviceContext);
	}

	public static com.liferay.portal.kernel.model.LayoutSetBranch updateLayoutSetBranch(
		long groupId, long layoutSetBranchId, java.lang.String name,
		java.lang.String description, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateLayoutSetBranch(groupId, layoutSetBranchId, name,
			description, serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.portal.kernel.model.LayoutSetBranch> getLayoutSetBranches(
		long groupId, boolean privateLayout) {
		return getService().getLayoutSetBranches(groupId, privateLayout);
	}

	public static void deleteLayoutSetBranch(long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteLayoutSetBranch(layoutSetBranchId);
	}

	public static LayoutSetBranchService getService() {
		if (_service == null) {
			_service = (LayoutSetBranchService)PortalBeanLocatorUtil.locate(LayoutSetBranchService.class.getName());

			ReferenceRegistry.registerReference(LayoutSetBranchServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static LayoutSetBranchService _service;
}