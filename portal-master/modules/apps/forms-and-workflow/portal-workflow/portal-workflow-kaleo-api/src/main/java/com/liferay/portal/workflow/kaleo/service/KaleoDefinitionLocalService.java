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

package com.liferay.portal.workflow.kaleo.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
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
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for KaleoDefinition. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoDefinitionLocalServiceUtil
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoDefinitionLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoDefinitionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KaleoDefinitionLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoDefinitionLocalServiceUtil} to access the kaleo definition local service. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoDefinitionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

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
	* Adds the kaleo definition to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDefinition the kaleo definition
	* @return the kaleo definition that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public KaleoDefinition addKaleoDefinition(KaleoDefinition kaleoDefinition);

	public KaleoDefinition addKaleoDefinition(java.lang.String name,
		java.lang.String title, java.lang.String description,
		java.lang.String content, int version, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Creates a new kaleo definition with the primary key. Does not add the kaleo definition to the database.
	*
	* @param kaleoDefinitionId the primary key for the new kaleo definition
	* @return the new kaleo definition
	*/
	public KaleoDefinition createKaleoDefinition(long kaleoDefinitionId);

	/**
	* Deletes the kaleo definition from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDefinition the kaleo definition
	* @return the kaleo definition that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public KaleoDefinition deleteKaleoDefinition(
		KaleoDefinition kaleoDefinition);

	/**
	* Deletes the kaleo definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDefinitionId the primary key of the kaleo definition
	* @return the kaleo definition that was removed
	* @throws PortalException if a kaleo definition with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public KaleoDefinition deleteKaleoDefinition(long kaleoDefinitionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDefinition fetchKaleoDefinition(long kaleoDefinitionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDefinition fetchLatestKaleoDefinition(java.lang.String name,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDefinition getKaleoDefinition(java.lang.String name,
		int version, ServiceContext serviceContext) throws PortalException;

	/**
	* Returns the kaleo definition with the primary key.
	*
	* @param kaleoDefinitionId the primary key of the kaleo definition
	* @return the kaleo definition
	* @throws PortalException if a kaleo definition with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDefinition getKaleoDefinition(long kaleoDefinitionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDefinition getLatestKaleoDefinition(java.lang.String name,
		ServiceContext serviceContext) throws PortalException;

	public KaleoDefinition incrementKaleoDefinition(Definition definition,
		java.lang.String title, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Updates the kaleo definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoDefinition the kaleo definition
	* @return the kaleo definition that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public KaleoDefinition updateKaleoDefinition(
		KaleoDefinition kaleoDefinition);

	public KaleoDefinition updateTitle(java.lang.String name, int version,
		java.lang.String title, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Returns the number of kaleo definitions.
	*
	* @return the number of kaleo definitions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoDefinitionsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoDefinitionsCount(boolean active,
		ServiceContext serviceContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoDefinitionsCount(ServiceContext serviceContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoDefinitionsCount(java.lang.String name, boolean active,
		ServiceContext serviceContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoDefinitionsCount(java.lang.String name,
		ServiceContext serviceContext);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<KaleoDefinition> getKaleoDefinitions(boolean active, int start,
		int end, OrderByComparator<KaleoDefinition> orderByComparator,
		ServiceContext serviceContext);

	/**
	* Returns a range of all the kaleo definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @return the range of kaleo definitions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoDefinition> getKaleoDefinitions(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoDefinition> getKaleoDefinitions(int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator,
		ServiceContext serviceContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoDefinition> getKaleoDefinitions(java.lang.String name,
		boolean active, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator,
		ServiceContext serviceContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoDefinition> getKaleoDefinitions(java.lang.String name,
		int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator,
		ServiceContext serviceContext);

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

	public void activateKaleoDefinition(java.lang.String name, int version,
		ServiceContext serviceContext) throws PortalException;

	public void activateKaleoDefinition(long kaleoDefinitionId,
		ServiceContext serviceContext) throws PortalException;

	public void activateKaleoDefinition(long kaleoDefinitionId,
		long startKaleoNodeId, ServiceContext serviceContext)
		throws PortalException;

	public void deactivateKaleoDefinition(java.lang.String name, int version,
		ServiceContext serviceContext) throws PortalException;

	public void deleteCompanyKaleoDefinitions(long companyId);

	public void deleteKaleoDefinition(java.lang.String name, int version,
		ServiceContext serviceContext) throws PortalException;
}