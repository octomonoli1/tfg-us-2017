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

import com.liferay.mobile.device.rules.model.MDRAction;

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
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the local service interface for MDRAction. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Edward C. Han
 * @see MDRActionLocalServiceUtil
 * @see com.liferay.mobile.device.rules.service.base.MDRActionLocalServiceBaseImpl
 * @see com.liferay.mobile.device.rules.service.impl.MDRActionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface MDRActionLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MDRActionLocalServiceUtil} to access the m d r action local service. Add custom service methods to {@link com.liferay.mobile.device.rules.service.impl.MDRActionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public MDRAction addAction(long ruleGroupInstanceId,
		Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, java.lang.String type,
		UnicodeProperties typeSettingsProperties, ServiceContext serviceContext)
		throws PortalException;

	public MDRAction addAction(long ruleGroupInstanceId,
		Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, java.lang.String type,
		java.lang.String typeSettings, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Adds the m d r action to the database. Also notifies the appropriate model listeners.
	*
	* @param mdrAction the m d r action
	* @return the m d r action that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public MDRAction addMDRAction(MDRAction mdrAction);

	/**
	* Creates a new m d r action with the primary key. Does not add the m d r action to the database.
	*
	* @param actionId the primary key for the new m d r action
	* @return the new m d r action
	*/
	public MDRAction createMDRAction(long actionId);

	/**
	* Deletes the m d r action from the database. Also notifies the appropriate model listeners.
	*
	* @param mdrAction the m d r action
	* @return the m d r action that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public MDRAction deleteMDRAction(MDRAction mdrAction);

	/**
	* Deletes the m d r action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param actionId the primary key of the m d r action
	* @return the m d r action that was removed
	* @throws PortalException if a m d r action with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public MDRAction deleteMDRAction(long actionId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRAction fetchAction(long actionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRAction fetchMDRAction(long actionId);

	/**
	* Returns the m d r action matching the UUID and group.
	*
	* @param uuid the m d r action's UUID
	* @param groupId the primary key of the group
	* @return the matching m d r action, or <code>null</code> if a matching m d r action could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRAction fetchMDRActionByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRAction getAction(long actionId) throws PortalException;

	/**
	* Returns the m d r action with the primary key.
	*
	* @param actionId the primary key of the m d r action
	* @return the m d r action
	* @throws PortalException if a m d r action with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRAction getMDRAction(long actionId) throws PortalException;

	/**
	* Returns the m d r action matching the UUID and group.
	*
	* @param uuid the m d r action's UUID
	* @param groupId the primary key of the group
	* @return the matching m d r action
	* @throws PortalException if a matching m d r action could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MDRAction getMDRActionByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	public MDRAction updateAction(long actionId,
		Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, java.lang.String type,
		UnicodeProperties typeSettingsProperties, ServiceContext serviceContext)
		throws PortalException;

	public MDRAction updateAction(long actionId,
		Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, java.lang.String type,
		java.lang.String typeSettings, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Updates the m d r action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mdrAction the m d r action
	* @return the m d r action that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public MDRAction updateMDRAction(MDRAction mdrAction);

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
	public int getActionsCount(long ruleGroupInstanceId);

	/**
	* Returns the number of m d r actions.
	*
	* @return the number of m d r actions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMDRActionsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mobile.device.rules.model.impl.MDRActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mobile.device.rules.model.impl.MDRActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<MDRAction> getActions(long ruleGroupInstanceId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MDRAction> getActions(long ruleGroupInstanceId, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MDRAction> getActions(long ruleGroupInstanceId, int start,
		int end, OrderByComparator<MDRAction> obc);

	/**
	* Returns a range of all the m d r actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mobile.device.rules.model.impl.MDRActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m d r actions
	* @param end the upper bound of the range of m d r actions (not inclusive)
	* @return the range of m d r actions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MDRAction> getMDRActions(int start, int end);

	/**
	* Returns all the m d r actions matching the UUID and company.
	*
	* @param uuid the UUID of the m d r actions
	* @param companyId the primary key of the company
	* @return the matching m d r actions, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MDRAction> getMDRActionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of m d r actions matching the UUID and company.
	*
	* @param uuid the UUID of the m d r actions
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of m d r actions
	* @param end the upper bound of the range of m d r actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching m d r actions, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MDRAction> getMDRActionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<MDRAction> orderByComparator);

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

	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public void deleteAction(MDRAction action);

	public void deleteAction(long actionId);

	public void deleteActions(long ruleGroupInstanceId);
}