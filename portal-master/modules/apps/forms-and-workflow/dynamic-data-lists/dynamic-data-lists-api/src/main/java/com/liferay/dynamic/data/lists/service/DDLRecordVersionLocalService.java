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

package com.liferay.dynamic.data.lists.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.lists.model.DDLRecordVersion;

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
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for DDLRecordVersion. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordVersionLocalServiceUtil
 * @see com.liferay.dynamic.data.lists.service.base.DDLRecordVersionLocalServiceBaseImpl
 * @see com.liferay.dynamic.data.lists.service.impl.DDLRecordVersionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DDLRecordVersionLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DDLRecordVersionLocalServiceUtil} to access the d d l record version local service. Add custom service methods to {@link com.liferay.dynamic.data.lists.service.impl.DDLRecordVersionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the d d l record version to the database. Also notifies the appropriate model listeners.
	*
	* @param ddlRecordVersion the d d l record version
	* @return the d d l record version that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DDLRecordVersion addDDLRecordVersion(
		DDLRecordVersion ddlRecordVersion);

	/**
	* Creates a new d d l record version with the primary key. Does not add the d d l record version to the database.
	*
	* @param recordVersionId the primary key for the new d d l record version
	* @return the new d d l record version
	*/
	public DDLRecordVersion createDDLRecordVersion(long recordVersionId);

	/**
	* Deletes the d d l record version from the database. Also notifies the appropriate model listeners.
	*
	* @param ddlRecordVersion the d d l record version
	* @return the d d l record version that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public DDLRecordVersion deleteDDLRecordVersion(
		DDLRecordVersion ddlRecordVersion);

	/**
	* Deletes the d d l record version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param recordVersionId the primary key of the d d l record version
	* @return the d d l record version that was removed
	* @throws PortalException if a d d l record version with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DDLRecordVersion deleteDDLRecordVersion(long recordVersionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordVersion fetchDDLRecordVersion(long recordVersionId);

	/**
	* Returns the d d l record version with the primary key.
	*
	* @param recordVersionId the primary key of the d d l record version
	* @return the d d l record version
	* @throws PortalException if a d d l record version with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordVersion getDDLRecordVersion(long recordVersionId)
		throws PortalException;

	/**
	* Returns the record's latest record version.
	*
	* @param recordId the primary key of the record
	* @return the latest record version for the given record
	* @throws PortalException if a portal exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordVersion getLatestRecordVersion(long recordId)
		throws PortalException;

	/**
	* Returns the record version matching the record and version.
	*
	* @param recordId the primary key of the record
	* @param version the record version
	* @return the record version matching the record primary key and version
	* @throws PortalException if a matching record set could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordVersion getRecordVersion(long recordId,
		java.lang.String version) throws PortalException;

	/**
	* Returns the record version by its ID.
	*
	* @param recordVersionId the primary key of the record version
	* @return the record version with the ID
	* @throws PortalException if a matching record set could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordVersion getRecordVersion(long recordVersionId)
		throws PortalException;

	/**
	* Updates the d d l record version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ddlRecordVersion the d d l record version
	* @return the d d l record version that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DDLRecordVersion updateDDLRecordVersion(
		DDLRecordVersion ddlRecordVersion);

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
	* Returns the number of d d l record versions.
	*
	* @return the number of d d l record versions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDDLRecordVersionsCount();

	/**
	* Returns the number of record versions matching the record ID.
	*
	* @param recordId the primary key of the record
	* @return the number of matching record versions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRecordVersionsCount(long recordId);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.lists.model.impl.DDLRecordVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.lists.model.impl.DDLRecordVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the d d l record versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.lists.model.impl.DDLRecordVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of d d l record versions
	* @param end the upper bound of the range of d d l record versions (not inclusive)
	* @return the range of d d l record versions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecordVersion> getDDLRecordVersions(int start, int end);

	/**
	* Returns an ordered range of record versions matching the record's ID.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to <code>QueryUtil.ALL_POS</code> will return the
	* full result set.
	* </p>
	*
	* @param recordId the primary key of the record
	* @param start the lower bound of the range of record versions to return
	* @param end the upper bound of the range of record versions to return
	(not inclusive)
	* @param orderByComparator the comparator used to order the record
	versions
	* @return the range of matching record versions ordered by the comparator
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecordVersion> getRecordVersions(long recordId, int start,
		int end, OrderByComparator<DDLRecordVersion> orderByComparator);

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
}