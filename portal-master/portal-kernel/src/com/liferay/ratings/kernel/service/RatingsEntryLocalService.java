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

package com.liferay.ratings.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

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

import com.liferay.ratings.kernel.model.RatingsEntry;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for RatingsEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see RatingsEntryLocalServiceUtil
 * @see com.liferay.portlet.ratings.service.base.RatingsEntryLocalServiceBaseImpl
 * @see com.liferay.portlet.ratings.service.impl.RatingsEntryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface RatingsEntryLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RatingsEntryLocalServiceUtil} to access the ratings entry local service. Add custom service methods to {@link com.liferay.portlet.ratings.service.impl.RatingsEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
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
	* Adds the ratings entry to the database. Also notifies the appropriate model listeners.
	*
	* @param ratingsEntry the ratings entry
	* @return the ratings entry that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public RatingsEntry addRatingsEntry(RatingsEntry ratingsEntry);

	/**
	* Creates a new ratings entry with the primary key. Does not add the ratings entry to the database.
	*
	* @param entryId the primary key for the new ratings entry
	* @return the new ratings entry
	*/
	public RatingsEntry createRatingsEntry(long entryId);

	/**
	* Deletes the ratings entry from the database. Also notifies the appropriate model listeners.
	*
	* @param ratingsEntry the ratings entry
	* @return the ratings entry that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public RatingsEntry deleteRatingsEntry(RatingsEntry ratingsEntry);

	/**
	* Deletes the ratings entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the ratings entry
	* @return the ratings entry that was removed
	* @throws PortalException if a ratings entry with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public RatingsEntry deleteRatingsEntry(long entryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RatingsEntry fetchEntry(long userId, java.lang.String className,
		long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RatingsEntry fetchRatingsEntry(long entryId);

	/**
	* Returns the ratings entry with the matching UUID and company.
	*
	* @param uuid the ratings entry's UUID
	* @param companyId the primary key of the company
	* @return the matching ratings entry, or <code>null</code> if a matching ratings entry could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RatingsEntry fetchRatingsEntryByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RatingsEntry getEntry(long userId, java.lang.String className,
		long classPK) throws PortalException;

	/**
	* Returns the ratings entry with the primary key.
	*
	* @param entryId the primary key of the ratings entry
	* @return the ratings entry
	* @throws PortalException if a ratings entry with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RatingsEntry getRatingsEntry(long entryId) throws PortalException;

	/**
	* Returns the ratings entry with the matching UUID and company.
	*
	* @param uuid the ratings entry's UUID
	* @param companyId the primary key of the company
	* @return the matching ratings entry
	* @throws PortalException if a matching ratings entry could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RatingsEntry getRatingsEntryByUuidAndCompanyId(
		java.lang.String uuid, long companyId) throws PortalException;

	public RatingsEntry updateEntry(long userId, java.lang.String className,
		long classPK, double score, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Updates the ratings entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ratingsEntry the ratings entry
	* @return the ratings entry that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public RatingsEntry updateRatingsEntry(RatingsEntry ratingsEntry);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getEntriesCount(java.lang.String className, long classPK,
		double score);

	/**
	* Returns the number of ratings entries.
	*
	* @return the number of ratings entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRatingsEntriesCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.ratings.model.impl.RatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.ratings.model.impl.RatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<RatingsEntry> getEntries(java.lang.String className,
		long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RatingsEntry> getEntries(java.lang.String className,
		long classPK, double score);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RatingsEntry> getEntries(long userId,
		java.lang.String className, List<java.lang.Long> classPKs);

	/**
	* Returns a range of all the ratings entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.ratings.model.impl.RatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ratings entries
	* @param end the upper bound of the range of ratings entries (not inclusive)
	* @return the range of ratings entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RatingsEntry> getRatingsEntries(int start, int end);

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
	public void deleteEntry(RatingsEntry entry, long userId,
		java.lang.String className, long classPK) throws PortalException;

	public void deleteEntry(long userId, java.lang.String className,
		long classPK) throws PortalException;
}