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

package com.liferay.social.networking.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.social.networking.model.MeetupsEntry;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the meetups entry service. This utility wraps {@link com.liferay.social.networking.service.persistence.impl.MeetupsEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsEntryPersistence
 * @see com.liferay.social.networking.service.persistence.impl.MeetupsEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class MeetupsEntryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(MeetupsEntry meetupsEntry) {
		getPersistence().clearCache(meetupsEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MeetupsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MeetupsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MeetupsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MeetupsEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MeetupsEntry update(MeetupsEntry meetupsEntry) {
		return getPersistence().update(meetupsEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MeetupsEntry update(MeetupsEntry meetupsEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(meetupsEntry, serviceContext);
	}

	/**
	* Returns all the meetups entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching meetups entries
	*/
	public static List<MeetupsEntry> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the meetups entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @return the range of matching meetups entries
	*/
	public static List<MeetupsEntry> findByCompanyId(long companyId, int start,
		int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the meetups entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching meetups entries
	*/
	public static List<MeetupsEntry> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<MeetupsEntry> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the meetups entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching meetups entries
	*/
	public static List<MeetupsEntry> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<MeetupsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first meetups entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups entry
	* @throws NoSuchMeetupsEntryException if a matching meetups entry could not be found
	*/
	public static MeetupsEntry findByCompanyId_First(long companyId,
		OrderByComparator<MeetupsEntry> orderByComparator)
		throws com.liferay.social.networking.exception.NoSuchMeetupsEntryException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first meetups entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups entry, or <code>null</code> if a matching meetups entry could not be found
	*/
	public static MeetupsEntry fetchByCompanyId_First(long companyId,
		OrderByComparator<MeetupsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last meetups entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups entry
	* @throws NoSuchMeetupsEntryException if a matching meetups entry could not be found
	*/
	public static MeetupsEntry findByCompanyId_Last(long companyId,
		OrderByComparator<MeetupsEntry> orderByComparator)
		throws com.liferay.social.networking.exception.NoSuchMeetupsEntryException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last meetups entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups entry, or <code>null</code> if a matching meetups entry could not be found
	*/
	public static MeetupsEntry fetchByCompanyId_Last(long companyId,
		OrderByComparator<MeetupsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the meetups entries before and after the current meetups entry in the ordered set where companyId = &#63;.
	*
	* @param meetupsEntryId the primary key of the current meetups entry
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meetups entry
	* @throws NoSuchMeetupsEntryException if a meetups entry with the primary key could not be found
	*/
	public static MeetupsEntry[] findByCompanyId_PrevAndNext(
		long meetupsEntryId, long companyId,
		OrderByComparator<MeetupsEntry> orderByComparator)
		throws com.liferay.social.networking.exception.NoSuchMeetupsEntryException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(meetupsEntryId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the meetups entries where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of meetups entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching meetups entries
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the meetups entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching meetups entries
	*/
	public static List<MeetupsEntry> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the meetups entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @return the range of matching meetups entries
	*/
	public static List<MeetupsEntry> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the meetups entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching meetups entries
	*/
	public static List<MeetupsEntry> findByUserId(long userId, int start,
		int end, OrderByComparator<MeetupsEntry> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the meetups entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching meetups entries
	*/
	public static List<MeetupsEntry> findByUserId(long userId, int start,
		int end, OrderByComparator<MeetupsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first meetups entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups entry
	* @throws NoSuchMeetupsEntryException if a matching meetups entry could not be found
	*/
	public static MeetupsEntry findByUserId_First(long userId,
		OrderByComparator<MeetupsEntry> orderByComparator)
		throws com.liferay.social.networking.exception.NoSuchMeetupsEntryException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first meetups entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups entry, or <code>null</code> if a matching meetups entry could not be found
	*/
	public static MeetupsEntry fetchByUserId_First(long userId,
		OrderByComparator<MeetupsEntry> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last meetups entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups entry
	* @throws NoSuchMeetupsEntryException if a matching meetups entry could not be found
	*/
	public static MeetupsEntry findByUserId_Last(long userId,
		OrderByComparator<MeetupsEntry> orderByComparator)
		throws com.liferay.social.networking.exception.NoSuchMeetupsEntryException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last meetups entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups entry, or <code>null</code> if a matching meetups entry could not be found
	*/
	public static MeetupsEntry fetchByUserId_Last(long userId,
		OrderByComparator<MeetupsEntry> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the meetups entries before and after the current meetups entry in the ordered set where userId = &#63;.
	*
	* @param meetupsEntryId the primary key of the current meetups entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meetups entry
	* @throws NoSuchMeetupsEntryException if a meetups entry with the primary key could not be found
	*/
	public static MeetupsEntry[] findByUserId_PrevAndNext(long meetupsEntryId,
		long userId, OrderByComparator<MeetupsEntry> orderByComparator)
		throws com.liferay.social.networking.exception.NoSuchMeetupsEntryException {
		return getPersistence()
				   .findByUserId_PrevAndNext(meetupsEntryId, userId,
			orderByComparator);
	}

	/**
	* Removes all the meetups entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of meetups entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching meetups entries
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Caches the meetups entry in the entity cache if it is enabled.
	*
	* @param meetupsEntry the meetups entry
	*/
	public static void cacheResult(MeetupsEntry meetupsEntry) {
		getPersistence().cacheResult(meetupsEntry);
	}

	/**
	* Caches the meetups entries in the entity cache if it is enabled.
	*
	* @param meetupsEntries the meetups entries
	*/
	public static void cacheResult(List<MeetupsEntry> meetupsEntries) {
		getPersistence().cacheResult(meetupsEntries);
	}

	/**
	* Creates a new meetups entry with the primary key. Does not add the meetups entry to the database.
	*
	* @param meetupsEntryId the primary key for the new meetups entry
	* @return the new meetups entry
	*/
	public static MeetupsEntry create(long meetupsEntryId) {
		return getPersistence().create(meetupsEntryId);
	}

	/**
	* Removes the meetups entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsEntryId the primary key of the meetups entry
	* @return the meetups entry that was removed
	* @throws NoSuchMeetupsEntryException if a meetups entry with the primary key could not be found
	*/
	public static MeetupsEntry remove(long meetupsEntryId)
		throws com.liferay.social.networking.exception.NoSuchMeetupsEntryException {
		return getPersistence().remove(meetupsEntryId);
	}

	public static MeetupsEntry updateImpl(MeetupsEntry meetupsEntry) {
		return getPersistence().updateImpl(meetupsEntry);
	}

	/**
	* Returns the meetups entry with the primary key or throws a {@link NoSuchMeetupsEntryException} if it could not be found.
	*
	* @param meetupsEntryId the primary key of the meetups entry
	* @return the meetups entry
	* @throws NoSuchMeetupsEntryException if a meetups entry with the primary key could not be found
	*/
	public static MeetupsEntry findByPrimaryKey(long meetupsEntryId)
		throws com.liferay.social.networking.exception.NoSuchMeetupsEntryException {
		return getPersistence().findByPrimaryKey(meetupsEntryId);
	}

	/**
	* Returns the meetups entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param meetupsEntryId the primary key of the meetups entry
	* @return the meetups entry, or <code>null</code> if a meetups entry with the primary key could not be found
	*/
	public static MeetupsEntry fetchByPrimaryKey(long meetupsEntryId) {
		return getPersistence().fetchByPrimaryKey(meetupsEntryId);
	}

	public static java.util.Map<java.io.Serializable, MeetupsEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the meetups entries.
	*
	* @return the meetups entries
	*/
	public static List<MeetupsEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the meetups entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @return the range of meetups entries
	*/
	public static List<MeetupsEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the meetups entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of meetups entries
	*/
	public static List<MeetupsEntry> findAll(int start, int end,
		OrderByComparator<MeetupsEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the meetups entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of meetups entries
	*/
	public static List<MeetupsEntry> findAll(int start, int end,
		OrderByComparator<MeetupsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the meetups entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of meetups entries.
	*
	* @return the number of meetups entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MeetupsEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MeetupsEntryPersistence, MeetupsEntryPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MeetupsEntryPersistence.class);
}