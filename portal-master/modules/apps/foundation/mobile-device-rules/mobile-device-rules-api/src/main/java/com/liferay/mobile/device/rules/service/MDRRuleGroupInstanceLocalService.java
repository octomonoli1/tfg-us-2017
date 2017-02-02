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

package com.liferay.mobile.device.rules.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;

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

import java.util.List;

/**
 * Provides the local service interface for MDRRuleGroupInstance. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Edward C. Han
 * @see MDRRuleGroupInstanceLocalServiceUtil
 * @see com.liferay.mobile.device.rules.service.base.MDRRuleGroupInstanceLocalServiceBaseImpl
 * @see com.liferay.mobile.device.rules.service.impl.MDRRuleGroupInstanceLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface MDRRuleGroupInstanceLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MDRRuleGroupInstanceLocalServiceUtil} to access the m d r rule group instance local service. Add custom service methods to {@link com.liferay.mobile.device.rules.service.impl.MDRRuleGroupInstanceLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the m d r rule group instance to the database. Also notifies the appropriate model listeners.
	*
	* @param mdrRuleGroupInstance the m d r rule group instance
	* @return the m d r rule group instance that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public MDRRuleGroupInstance addMDRRuleGroupInstance(
		MDRRuleGroupInstance mdrRuleGroupInstance);

	public MDRRuleGroupInstance addRuleGroupInstance(long groupId,
		java.lang.String className, long classPK, long ruleGroupId,
		ServiceContext serviceContext) throws PortalException;

	public MDRRuleGroupInstance addRuleGroupInstance(long groupId,
		java.lang.String className, long classPK, long ruleGroupId,
		int priority, ServiceContext serviceContext) throws PortalException;

	/**
	* Creates a new m d r rule group instance with the primary key. Does not add the m d r rule group instance to the database.
	*
	* @param ruleGroupInstanceId the primary key for the new m d r rule group instance
	* @return the new m d r rule group instance
	*/
	public MDRRuleGroupInstance createMDRRuleGroupInstance(
		long ruleGroupInstanceId);

	/**
	* Deletes the m d r rule group instance from the database. Also notifies the appropriate model listeners.
	*
	* @param mdrRuleGroupInstance the m d r rule group instance
	* @return the m d r rule group instance that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public MDRRuleGroupInstance deleteMDRRuleGroupInstance(
		MDRRuleGroupInstance mdrRuleGroupInstance);

	/**
	* Deletes the m d r rule group instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ruleGroupInstanceId the primary key of the m d r rule group instance
	* @return the m d r rule group instance that was removed
	* @throws PortalException if a m d r rule group instance with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public MDRRuleGroupInstance deleteMDRRuleGroupInstance(
		long ruleGroupInstanceId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRRuleGroupInstance fetchMDRRuleGroupInstance(
		long ruleGroupInstanceId);

	/**
	* Returns the m d r rule group instance matching the UUID and group.
	*
	* @param uuid the m d r rule group instance's UUID
	* @param groupId the primary key of the group
	* @return the matching m d r rule group instance, or <code>null</code> if a matching m d r rule group instance could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRRuleGroupInstance fetchMDRRuleGroupInstanceByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRRuleGroupInstance fetchRuleGroupInstance(
		java.lang.String className, long classPK, long ruleGroupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRRuleGroupInstance fetchRuleGroupInstance(long ruleGroupInstanceId);

	/**
	* Returns the m d r rule group instance with the primary key.
	*
	* @param ruleGroupInstanceId the primary key of the m d r rule group instance
	* @return the m d r rule group instance
	* @throws PortalException if a m d r rule group instance with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRRuleGroupInstance getMDRRuleGroupInstance(
		long ruleGroupInstanceId) throws PortalException;

	/**
	* Returns the m d r rule group instance matching the UUID and group.
	*
	* @param uuid the m d r rule group instance's UUID
	* @param groupId the primary key of the group
	* @return the matching m d r rule group instance
	* @throws PortalException if a matching m d r rule group instance could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRRuleGroupInstance getMDRRuleGroupInstanceByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRRuleGroupInstance getRuleGroupInstance(
		java.lang.String className, long classPK, long ruleGroupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRRuleGroupInstance getRuleGroupInstance(long ruleGroupInstanceId)
		throws PortalException;

	/**
	* Updates the m d r rule group instance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mdrRuleGroupInstance the m d r rule group instance
	* @return the m d r rule group instance that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public MDRRuleGroupInstance updateMDRRuleGroupInstance(
		MDRRuleGroupInstance mdrRuleGroupInstance);

	public MDRRuleGroupInstance updateRuleGroupInstance(
		long ruleGroupInstanceId, int priority) throws PortalException;

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

	/**
	* Returns the number of m d r rule group instances.
	*
	* @return the number of m d r rule group instances
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMDRRuleGroupInstancesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRuleGroupInstancesCount(java.lang.String className,
		long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRuleGroupInstancesCount(long ruleGroupId);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mobile.device.rules.model.impl.MDRRuleGroupInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mobile.device.rules.model.impl.MDRRuleGroupInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Returns a range of all the m d r rule group instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mobile.device.rules.model.impl.MDRRuleGroupInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m d r rule group instances
	* @param end the upper bound of the range of m d r rule group instances (not inclusive)
	* @return the range of m d r rule group instances
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MDRRuleGroupInstance> getMDRRuleGroupInstances(int start,
		int end);

	/**
	* Returns all the m d r rule group instances matching the UUID and company.
	*
	* @param uuid the UUID of the m d r rule group instances
	* @param companyId the primary key of the company
	* @return the matching m d r rule group instances, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MDRRuleGroupInstance> getMDRRuleGroupInstancesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of m d r rule group instances matching the UUID and company.
	*
	* @param uuid the UUID of the m d r rule group instances
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of m d r rule group instances
	* @param end the upper bound of the range of m d r rule group instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching m d r rule group instances, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MDRRuleGroupInstance> getMDRRuleGroupInstancesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<MDRRuleGroupInstance> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MDRRuleGroupInstance> getRuleGroupInstances(
		java.lang.String className, long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MDRRuleGroupInstance> getRuleGroupInstances(
		java.lang.String className, long classPK, int start, int end,
		OrderByComparator<MDRRuleGroupInstance> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MDRRuleGroupInstance> getRuleGroupInstances(long ruleGroupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MDRRuleGroupInstance> getRuleGroupInstances(long ruleGroupId,
		int start, int end);

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

	public void deleteGroupRuleGroupInstances(long groupId);

	@SystemEvent(action = SystemEventConstants.ACTION_SKIP, type = SystemEventConstants.TYPE_DELETE)
	public void deleteRuleGroupInstance(MDRRuleGroupInstance ruleGroupInstance);

	public void deleteRuleGroupInstance(long ruleGroupInstanceId);

	public void deleteRuleGroupInstances(long ruleGroupId);
}