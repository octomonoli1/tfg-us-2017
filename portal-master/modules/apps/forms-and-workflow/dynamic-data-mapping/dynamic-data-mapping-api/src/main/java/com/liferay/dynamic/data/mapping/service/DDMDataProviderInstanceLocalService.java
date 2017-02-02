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

package com.liferay.dynamic.data.mapping.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the local service interface for DDMDataProviderInstance. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see DDMDataProviderInstanceLocalServiceUtil
 * @see com.liferay.dynamic.data.mapping.service.base.DDMDataProviderInstanceLocalServiceBaseImpl
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMDataProviderInstanceLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DDMDataProviderInstanceLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DDMDataProviderInstanceLocalServiceUtil} to access the d d m data provider instance local service. Add custom service methods to {@link com.liferay.dynamic.data.mapping.service.impl.DDMDataProviderInstanceLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the d d m data provider instance to the database. Also notifies the appropriate model listeners.
	*
	* @param ddmDataProviderInstance the d d m data provider instance
	* @return the d d m data provider instance that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DDMDataProviderInstance addDDMDataProviderInstance(
		DDMDataProviderInstance ddmDataProviderInstance);

	public DDMDataProviderInstance addDataProviderInstance(long userId,
		long groupId, Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap,
		DDMFormValues ddmFormValues, java.lang.String type,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Creates a new d d m data provider instance with the primary key. Does not add the d d m data provider instance to the database.
	*
	* @param dataProviderInstanceId the primary key for the new d d m data provider instance
	* @return the new d d m data provider instance
	*/
	public DDMDataProviderInstance createDDMDataProviderInstance(
		long dataProviderInstanceId);

	/**
	* Deletes the d d m data provider instance from the database. Also notifies the appropriate model listeners.
	*
	* @param ddmDataProviderInstance the d d m data provider instance
	* @return the d d m data provider instance that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public DDMDataProviderInstance deleteDDMDataProviderInstance(
		DDMDataProviderInstance ddmDataProviderInstance);

	/**
	* Deletes the d d m data provider instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dataProviderInstanceId the primary key of the d d m data provider instance
	* @return the d d m data provider instance that was removed
	* @throws PortalException if a d d m data provider instance with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DDMDataProviderInstance deleteDDMDataProviderInstance(
		long dataProviderInstanceId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMDataProviderInstance fetchDDMDataProviderInstance(
		long dataProviderInstanceId);

	/**
	* Returns the d d m data provider instance matching the UUID and group.
	*
	* @param uuid the d d m data provider instance's UUID
	* @param groupId the primary key of the group
	* @return the matching d d m data provider instance, or <code>null</code> if a matching d d m data provider instance could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMDataProviderInstance fetchDDMDataProviderInstanceByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMDataProviderInstance fetchDataProviderInstance(
		long dataProviderInstanceId);

	/**
	* Returns the d d m data provider instance with the primary key.
	*
	* @param dataProviderInstanceId the primary key of the d d m data provider instance
	* @return the d d m data provider instance
	* @throws PortalException if a d d m data provider instance with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMDataProviderInstance getDDMDataProviderInstance(
		long dataProviderInstanceId) throws PortalException;

	/**
	* Returns the d d m data provider instance matching the UUID and group.
	*
	* @param uuid the d d m data provider instance's UUID
	* @param groupId the primary key of the group
	* @return the matching d d m data provider instance
	* @throws PortalException if a matching d d m data provider instance could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMDataProviderInstance getDDMDataProviderInstanceByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMDataProviderInstance getDataProviderInstance(
		long dataProviderInstanceId) throws PortalException;

	/**
	* Updates the d d m data provider instance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ddmDataProviderInstance the d d m data provider instance
	* @return the d d m data provider instance that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DDMDataProviderInstance updateDDMDataProviderInstance(
		DDMDataProviderInstance ddmDataProviderInstance);

	public DDMDataProviderInstance updateDataProviderInstance(long userId,
		long dataProviderInstanceId, Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap,
		DDMFormValues ddmFormValues, ServiceContext serviceContext)
		throws PortalException;

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
	* Returns the number of d d m data provider instances.
	*
	* @return the number of d d m data provider instances
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDDMDataProviderInstancesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long[] groupIds,
		java.lang.String keywords);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long[] groupIds,
		java.lang.String name, java.lang.String description, boolean andOperator);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMDataProviderInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMDataProviderInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the d d m data provider instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMDataProviderInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of d d m data provider instances
	* @param end the upper bound of the range of d d m data provider instances (not inclusive)
	* @return the range of d d m data provider instances
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMDataProviderInstance> getDDMDataProviderInstances(
		int start, int end);

	/**
	* Returns all the d d m data provider instances matching the UUID and company.
	*
	* @param uuid the UUID of the d d m data provider instances
	* @param companyId the primary key of the company
	* @return the matching d d m data provider instances, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMDataProviderInstance> getDDMDataProviderInstancesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of d d m data provider instances matching the UUID and company.
	*
	* @param uuid the UUID of the d d m data provider instances
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of d d m data provider instances
	* @param end the upper bound of the range of d d m data provider instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching d d m data provider instances, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMDataProviderInstance> getDDMDataProviderInstancesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<DDMDataProviderInstance> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMDataProviderInstance> getDataProviderInstances(
		long[] groupIds);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMDataProviderInstance> search(long companyId,
		long[] groupIds, java.lang.String keywords, int start, int end,
		OrderByComparator<DDMDataProviderInstance> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMDataProviderInstance> search(long companyId,
		long[] groupIds, java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		OrderByComparator<DDMDataProviderInstance> orderByComparator);

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

	public void deleteDataProviderInstance(
		DDMDataProviderInstance dataProviderInstance) throws PortalException;

	public void deleteDataProviderInstance(long dataProviderInstanceId)
		throws PortalException;

	public void deleteDataProviderInstances(long companyId, long groupId)
		throws PortalException;
}