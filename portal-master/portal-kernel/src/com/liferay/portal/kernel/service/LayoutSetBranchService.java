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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the remote service interface for LayoutSetBranch. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetBranchServiceUtil
 * @see com.liferay.portal.service.base.LayoutSetBranchServiceBaseImpl
 * @see com.liferay.portal.service.impl.LayoutSetBranchServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LayoutSetBranchService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LayoutSetBranchServiceUtil} to access the layout set branch remote service. Add custom service methods to {@link com.liferay.portal.service.impl.LayoutSetBranchServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public LayoutSetBranch addLayoutSetBranch(long groupId,
		boolean privateLayout, java.lang.String name,
		java.lang.String description, boolean master,
		long copyLayoutSetBranchId, ServiceContext serviceContext)
		throws PortalException;

	public LayoutSetBranch mergeLayoutSetBranch(long layoutSetBranchId,
		long mergeLayoutSetBranchId, ServiceContext serviceContext)
		throws PortalException;

	public LayoutSetBranch updateLayoutSetBranch(long groupId,
		long layoutSetBranchId, java.lang.String name,
		java.lang.String description, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LayoutSetBranch> getLayoutSetBranches(long groupId,
		boolean privateLayout);

	public void deleteLayoutSetBranch(long layoutSetBranchId)
		throws PortalException;
}