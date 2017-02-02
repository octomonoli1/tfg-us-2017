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

package com.liferay.expando.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoColumn;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * Provides the remote service interface for ExpandoColumn. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoColumnServiceUtil
 * @see com.liferay.portlet.expando.service.base.ExpandoColumnServiceBaseImpl
 * @see com.liferay.portlet.expando.service.impl.ExpandoColumnServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ExpandoColumnService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ExpandoColumnServiceUtil} to access the expando column remote service. Add custom service methods to {@link com.liferay.portlet.expando.service.impl.ExpandoColumnServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public ExpandoColumn addColumn(long tableId, java.lang.String name, int type)
		throws PortalException;

	public ExpandoColumn addColumn(long tableId, java.lang.String name,
		int type, java.lang.Object defaultData) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExpandoColumn fetchExpandoColumn(long columnId)
		throws PortalException;

	public ExpandoColumn updateColumn(long columnId, java.lang.String name,
		int type) throws PortalException;

	public ExpandoColumn updateColumn(long columnId, java.lang.String name,
		int type, java.lang.Object defaultData) throws PortalException;

	public ExpandoColumn updateTypeSettings(long columnId,
		java.lang.String typeSettings) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	public void deleteColumn(long columnId) throws PortalException;
}