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

package com.liferay.portal.lock.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.jdbc.aop.MasterDataSource;
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
import com.liferay.portal.lock.model.Lock;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for Lock. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see LockLocalServiceUtil
 * @see com.liferay.portal.lock.service.base.LockLocalServiceBaseImpl
 * @see com.liferay.portal.lock.service.impl.LockLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LockLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LockLocalServiceUtil} to access the lock local service. Add custom service methods to {@link com.liferay.portal.lock.service.impl.LockLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasLock(long userId, java.lang.String className,
		java.lang.String key);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasLock(long userId, java.lang.String className, long key);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isLocked(java.lang.String className, java.lang.String key);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isLocked(java.lang.String className, long key);

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
	* Adds the lock to the database. Also notifies the appropriate model listeners.
	*
	* @param lock the lock
	* @return the lock that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Lock addLock(Lock lock);

	/**
	* Creates a new lock with the primary key. Does not add the lock to the database.
	*
	* @param lockId the primary key for the new lock
	* @return the new lock
	*/
	public Lock createLock(long lockId);

	/**
	* Deletes the lock from the database. Also notifies the appropriate model listeners.
	*
	* @param lock the lock
	* @return the lock that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Lock deleteLock(Lock lock);

	/**
	* Deletes the lock with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lockId the primary key of the lock
	* @return the lock that was removed
	* @throws PortalException if a lock with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Lock deleteLock(long lockId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Lock fetchLock(long lockId);

	/**
	* Returns the lock with the matching UUID and company.
	*
	* @param uuid the lock's UUID
	* @param companyId the primary key of the company
	* @return the matching lock, or <code>null</code> if a matching lock could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Lock fetchLockByUuidAndCompanyId(java.lang.String uuid,
		long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Lock getLock(java.lang.String className, java.lang.String key)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Lock getLock(java.lang.String className, long key)
		throws PortalException;

	/**
	* Returns the lock with the primary key.
	*
	* @param lockId the primary key of the lock
	* @return the lock
	* @throws PortalException if a lock with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Lock getLock(long lockId) throws PortalException;

	/**
	* Returns the lock with the matching UUID and company.
	*
	* @param uuid the lock's UUID
	* @param companyId the primary key of the company
	* @return the matching lock
	* @throws PortalException if a matching lock could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Lock getLockByUuidAndCompanyId(java.lang.String uuid, long companyId)
		throws PortalException;

	@MasterDataSource
	public Lock lock(java.lang.String className, java.lang.String key,
		java.lang.String expectedOwner, java.lang.String updatedOwner);

	@MasterDataSource
	public Lock lock(java.lang.String className, java.lang.String key,
		java.lang.String owner);

	public Lock lock(long userId, java.lang.String className,
		java.lang.String key, java.lang.String owner, boolean inheritable,
		long expirationTime) throws PortalException;

	public Lock lock(long userId, java.lang.String className, long key,
		java.lang.String owner, boolean inheritable, long expirationTime)
		throws PortalException;

	public Lock refresh(java.lang.String uuid, long companyId,
		long expirationTime) throws PortalException;

	/**
	* Updates the lock in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lock the lock
	* @return the lock that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Lock updateLock(Lock lock);

	/**
	* Returns the number of locks.
	*
	* @return the number of locks
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLocksCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.lock.model.impl.LockModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.lock.model.impl.LockModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the locks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.lock.model.impl.LockModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of locks
	* @param end the upper bound of the range of locks (not inclusive)
	* @return the range of locks
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Lock> getLocks(int start, int end);

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

	public void clear();

	public void unlock(java.lang.String className, java.lang.String key);

	@MasterDataSource
	public void unlock(java.lang.String className, java.lang.String key,
		java.lang.String owner);

	public void unlock(java.lang.String className, long key);
}