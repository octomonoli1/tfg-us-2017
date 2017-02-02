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

package com.liferay.document.library.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.document.library.kernel.model.DLFileEntryType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service interface for DLFileEntryType. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryTypeServiceUtil
 * @see com.liferay.portlet.documentlibrary.service.base.DLFileEntryTypeServiceBaseImpl
 * @see com.liferay.portlet.documentlibrary.service.impl.DLFileEntryTypeServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DLFileEntryTypeService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DLFileEntryTypeServiceUtil} to access the document library file entry type remote service. Add custom service methods to {@link com.liferay.portlet.documentlibrary.service.impl.DLFileEntryTypeServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public DLFileEntryType addFileEntryType(long groupId,
		java.lang.String fileEntryTypeKey,
		Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, long[] ddmStructureIds,
		ServiceContext serviceContext) throws PortalException;

	public DLFileEntryType addFileEntryType(long groupId,
		java.lang.String name, java.lang.String description,
		long[] ddmStructureIds, ServiceContext serviceContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DLFileEntryType getFileEntryType(long fileEntryTypeId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFileEntryTypesCount(long[] groupIds);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long[] groupIds,
		java.lang.String keywords, boolean includeBasicFileEntryType);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DLFileEntryType> getFileEntryTypes(long[] groupIds);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DLFileEntryType> getFileEntryTypes(long[] groupIds, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DLFileEntryType> getFolderFileEntryTypes(long[] groupIds,
		long folderId, boolean inherited) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DLFileEntryType> search(long companyId, long[] groupIds,
		java.lang.String keywords, boolean includeBasicFileEntryType,
		int start, int end, OrderByComparator<DLFileEntryType> orderByComparator);

	public void deleteFileEntryType(long fileEntryTypeId)
		throws PortalException;

	public void updateFileEntryType(long fileEntryTypeId,
		java.lang.String name, java.lang.String description,
		long[] ddmStructureIds, ServiceContext serviceContext)
		throws PortalException;

	public void updateFileEntryType(long fileEntryTypeId,
		Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, long[] ddmStructureIds,
		ServiceContext serviceContext) throws PortalException;
}