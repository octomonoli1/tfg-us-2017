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

package com.liferay.knowledge.base.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.knowledge.base.model.KBTemplate;
import com.liferay.knowledge.base.model.KBTemplateSearchDisplay;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.List;

/**
 * Provides the remote service interface for KBTemplate. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplateServiceUtil
 * @see com.liferay.knowledge.base.service.base.KBTemplateServiceBaseImpl
 * @see com.liferay.knowledge.base.service.impl.KBTemplateServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=kb", "json.web.service.context.path=KBTemplate"}, service = KBTemplateService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KBTemplateService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KBTemplateServiceUtil} to access the k b template remote service. Add custom service methods to {@link com.liferay.knowledge.base.service.impl.KBTemplateServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public KBTemplate addKBTemplate(java.lang.String portletId,
		java.lang.String title, java.lang.String content,
		ServiceContext serviceContext) throws PortalException;

	public KBTemplate deleteKBTemplate(long kbTemplateId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBTemplate getKBTemplate(long kbTemplateId)
		throws PortalException;

	public KBTemplate updateKBTemplate(long kbTemplateId,
		java.lang.String title, java.lang.String content,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBTemplateSearchDisplay getKBTemplateSearchDisplay(long groupId,
		java.lang.String title, java.lang.String content, Date startDate,
		Date endDate, boolean andOperator, int[] curStartValues, int cur,
		int delta, OrderByComparator<KBTemplate> orderByComparator)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupKBTemplatesCount(long groupId);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBTemplate> getGroupKBTemplates(long groupId, int start,
		int end, OrderByComparator<KBTemplate> orderByComparator);

	public void deleteKBTemplates(long groupId, long[] kbTemplateIds)
		throws PortalException;
}