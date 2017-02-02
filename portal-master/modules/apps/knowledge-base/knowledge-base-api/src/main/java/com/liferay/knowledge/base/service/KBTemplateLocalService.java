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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.knowledge.base.model.KBTemplate;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for KBTemplate. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplateLocalServiceUtil
 * @see com.liferay.knowledge.base.service.base.KBTemplateLocalServiceBaseImpl
 * @see com.liferay.knowledge.base.service.impl.KBTemplateLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KBTemplateLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KBTemplateLocalServiceUtil} to access the k b template local service. Add custom service methods to {@link com.liferay.knowledge.base.service.impl.KBTemplateLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the k b template to the database. Also notifies the appropriate model listeners.
	*
	* @param kbTemplate the k b template
	* @return the k b template that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public KBTemplate addKBTemplate(KBTemplate kbTemplate);

	public KBTemplate addKBTemplate(long userId, java.lang.String title,
		java.lang.String content, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Creates a new k b template with the primary key. Does not add the k b template to the database.
	*
	* @param kbTemplateId the primary key for the new k b template
	* @return the new k b template
	*/
	public KBTemplate createKBTemplate(long kbTemplateId);

	/**
	* Deletes the k b template from the database. Also notifies the appropriate model listeners.
	*
	* @param kbTemplate the k b template
	* @return the k b template that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	@SystemEvent(action = SystemEventConstants.ACTION_SKIP, type = SystemEventConstants.TYPE_DELETE)
	public KBTemplate deleteKBTemplate(KBTemplate kbTemplate)
		throws PortalException;

	/**
	* Deletes the k b template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template that was removed
	* @throws PortalException if a k b template with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public KBTemplate deleteKBTemplate(long kbTemplateId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBTemplate fetchKBTemplate(long kbTemplateId);

	/**
	* Returns the k b template matching the UUID and group.
	*
	* @param uuid the k b template's UUID
	* @param groupId the primary key of the group
	* @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBTemplate fetchKBTemplateByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Returns the k b template with the primary key.
	*
	* @param kbTemplateId the primary key of the k b template
	* @return the k b template
	* @throws PortalException if a k b template with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBTemplate getKBTemplate(long kbTemplateId)
		throws PortalException;

	/**
	* Returns the k b template matching the UUID and group.
	*
	* @param uuid the k b template's UUID
	* @param groupId the primary key of the group
	* @return the matching k b template
	* @throws PortalException if a matching k b template could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBTemplate getKBTemplateByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the k b template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbTemplate the k b template
	* @return the k b template that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public KBTemplate updateKBTemplate(KBTemplate kbTemplate);

	public KBTemplate updateKBTemplate(long kbTemplateId,
		java.lang.String title, java.lang.String content,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupKBTemplatesCount(long groupId);

	/**
	* Returns the number of k b templates.
	*
	* @return the number of k b templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBTemplatesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledge.base.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledge.base.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBTemplate> getGroupKBTemplates(long groupId, int start,
		int end, OrderByComparator<KBTemplate> orderByComparator);

	/**
	* Returns a range of all the k b templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledge.base.model.impl.KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @return the range of k b templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBTemplate> getKBTemplates(int start, int end);

	/**
	* Returns all the k b templates matching the UUID and company.
	*
	* @param uuid the UUID of the k b templates
	* @param companyId the primary key of the company
	* @return the matching k b templates, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBTemplate> getKBTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of k b templates matching the UUID and company.
	*
	* @param uuid the UUID of the k b templates
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of k b templates
	* @param end the upper bound of the range of k b templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching k b templates, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBTemplate> getKBTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<KBTemplate> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBTemplate> search(long groupId, java.lang.String title,
		java.lang.String content, Date startDate, Date endDate,
		boolean andOperator, int start, int end,
		OrderByComparator<KBTemplate> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void deleteGroupKBTemplates(long groupId) throws PortalException;

	public void deleteKBTemplates(long[] kbTemplateIds)
		throws PortalException;

	public void updateKBTemplateResources(KBTemplate kbTemplate,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws PortalException;
}