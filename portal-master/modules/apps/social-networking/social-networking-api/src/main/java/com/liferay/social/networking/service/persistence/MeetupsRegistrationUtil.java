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

import com.liferay.social.networking.model.MeetupsRegistration;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the meetups registration service. This utility wraps {@link com.liferay.social.networking.service.persistence.impl.MeetupsRegistrationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistrationPersistence
 * @see com.liferay.social.networking.service.persistence.impl.MeetupsRegistrationPersistenceImpl
 * @generated
 */
@ProviderType
public class MeetupsRegistrationUtil {
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
	public static void clearCache(MeetupsRegistration meetupsRegistration) {
		getPersistence().clearCache(meetupsRegistration);
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
	public static List<MeetupsRegistration> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MeetupsRegistration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MeetupsRegistration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MeetupsRegistration> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MeetupsRegistration update(
		MeetupsRegistration meetupsRegistration) {
		return getPersistence().update(meetupsRegistration);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MeetupsRegistration update(
		MeetupsRegistration meetupsRegistration, ServiceContext serviceContext) {
		return getPersistence().update(meetupsRegistration, serviceContext);
	}

	/**
	* Returns all the meetups registrations where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @return the matching meetups registrations
	*/
	public static List<MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId) {
		return getPersistence().findByMeetupsEntryId(meetupsEntryId);
	}

	/**
	* Returns a range of all the meetups registrations where meetupsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsRegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @return the range of matching meetups registrations
	*/
	public static List<MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end) {
		return getPersistence().findByMeetupsEntryId(meetupsEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the meetups registrations where meetupsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsRegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching meetups registrations
	*/
	public static List<MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end,
		OrderByComparator<MeetupsRegistration> orderByComparator) {
		return getPersistence()
				   .findByMeetupsEntryId(meetupsEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the meetups registrations where meetupsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsRegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching meetups registrations
	*/
	public static List<MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end,
		OrderByComparator<MeetupsRegistration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByMeetupsEntryId(meetupsEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration
	* @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	*/
	public static MeetupsRegistration findByMeetupsEntryId_First(
		long meetupsEntryId,
		OrderByComparator<MeetupsRegistration> orderByComparator)
		throws com.liferay.social.networking.exception.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByMeetupsEntryId_First(meetupsEntryId, orderByComparator);
	}

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	*/
	public static MeetupsRegistration fetchByMeetupsEntryId_First(
		long meetupsEntryId,
		OrderByComparator<MeetupsRegistration> orderByComparator) {
		return getPersistence()
				   .fetchByMeetupsEntryId_First(meetupsEntryId,
			orderByComparator);
	}

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration
	* @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	*/
	public static MeetupsRegistration findByMeetupsEntryId_Last(
		long meetupsEntryId,
		OrderByComparator<MeetupsRegistration> orderByComparator)
		throws com.liferay.social.networking.exception.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByMeetupsEntryId_Last(meetupsEntryId, orderByComparator);
	}

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	*/
	public static MeetupsRegistration fetchByMeetupsEntryId_Last(
		long meetupsEntryId,
		OrderByComparator<MeetupsRegistration> orderByComparator) {
		return getPersistence()
				   .fetchByMeetupsEntryId_Last(meetupsEntryId, orderByComparator);
	}

	/**
	* Returns the meetups registrations before and after the current meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsRegistrationId the primary key of the current meetups registration
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meetups registration
	* @throws NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	*/
	public static MeetupsRegistration[] findByMeetupsEntryId_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId,
		OrderByComparator<MeetupsRegistration> orderByComparator)
		throws com.liferay.social.networking.exception.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByMeetupsEntryId_PrevAndNext(meetupsRegistrationId,
			meetupsEntryId, orderByComparator);
	}

	/**
	* Removes all the meetups registrations where meetupsEntryId = &#63; from the database.
	*
	* @param meetupsEntryId the meetups entry ID
	*/
	public static void removeByMeetupsEntryId(long meetupsEntryId) {
		getPersistence().removeByMeetupsEntryId(meetupsEntryId);
	}

	/**
	* Returns the number of meetups registrations where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @return the number of matching meetups registrations
	*/
	public static int countByMeetupsEntryId(long meetupsEntryId) {
		return getPersistence().countByMeetupsEntryId(meetupsEntryId);
	}

	/**
	* Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or throws a {@link NoSuchMeetupsRegistrationException} if it could not be found.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the matching meetups registration
	* @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	*/
	public static MeetupsRegistration findByU_ME(long userId,
		long meetupsEntryId)
		throws com.liferay.social.networking.exception.NoSuchMeetupsRegistrationException {
		return getPersistence().findByU_ME(userId, meetupsEntryId);
	}

	/**
	* Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	*/
	public static MeetupsRegistration fetchByU_ME(long userId,
		long meetupsEntryId) {
		return getPersistence().fetchByU_ME(userId, meetupsEntryId);
	}

	/**
	* Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	*/
	public static MeetupsRegistration fetchByU_ME(long userId,
		long meetupsEntryId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_ME(userId, meetupsEntryId, retrieveFromCache);
	}

	/**
	* Removes the meetups registration where userId = &#63; and meetupsEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the meetups registration that was removed
	*/
	public static MeetupsRegistration removeByU_ME(long userId,
		long meetupsEntryId)
		throws com.liferay.social.networking.exception.NoSuchMeetupsRegistrationException {
		return getPersistence().removeByU_ME(userId, meetupsEntryId);
	}

	/**
	* Returns the number of meetups registrations where userId = &#63; and meetupsEntryId = &#63;.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the number of matching meetups registrations
	*/
	public static int countByU_ME(long userId, long meetupsEntryId) {
		return getPersistence().countByU_ME(userId, meetupsEntryId);
	}

	/**
	* Returns all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @return the matching meetups registrations
	*/
	public static List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status) {
		return getPersistence().findByME_S(meetupsEntryId, status);
	}

	/**
	* Returns a range of all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsRegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @return the range of matching meetups registrations
	*/
	public static List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status, int start, int end) {
		return getPersistence().findByME_S(meetupsEntryId, status, start, end);
	}

	/**
	* Returns an ordered range of all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsRegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching meetups registrations
	*/
	public static List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status, int start, int end,
		OrderByComparator<MeetupsRegistration> orderByComparator) {
		return getPersistence()
				   .findByME_S(meetupsEntryId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsRegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching meetups registrations
	*/
	public static List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status, int start, int end,
		OrderByComparator<MeetupsRegistration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByME_S(meetupsEntryId, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration
	* @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	*/
	public static MeetupsRegistration findByME_S_First(long meetupsEntryId,
		int status, OrderByComparator<MeetupsRegistration> orderByComparator)
		throws com.liferay.social.networking.exception.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByME_S_First(meetupsEntryId, status, orderByComparator);
	}

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	*/
	public static MeetupsRegistration fetchByME_S_First(long meetupsEntryId,
		int status, OrderByComparator<MeetupsRegistration> orderByComparator) {
		return getPersistence()
				   .fetchByME_S_First(meetupsEntryId, status, orderByComparator);
	}

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration
	* @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	*/
	public static MeetupsRegistration findByME_S_Last(long meetupsEntryId,
		int status, OrderByComparator<MeetupsRegistration> orderByComparator)
		throws com.liferay.social.networking.exception.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByME_S_Last(meetupsEntryId, status, orderByComparator);
	}

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	*/
	public static MeetupsRegistration fetchByME_S_Last(long meetupsEntryId,
		int status, OrderByComparator<MeetupsRegistration> orderByComparator) {
		return getPersistence()
				   .fetchByME_S_Last(meetupsEntryId, status, orderByComparator);
	}

	/**
	* Returns the meetups registrations before and after the current meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsRegistrationId the primary key of the current meetups registration
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meetups registration
	* @throws NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	*/
	public static MeetupsRegistration[] findByME_S_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId, int status,
		OrderByComparator<MeetupsRegistration> orderByComparator)
		throws com.liferay.social.networking.exception.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByME_S_PrevAndNext(meetupsRegistrationId,
			meetupsEntryId, status, orderByComparator);
	}

	/**
	* Removes all the meetups registrations where meetupsEntryId = &#63; and status = &#63; from the database.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	*/
	public static void removeByME_S(long meetupsEntryId, int status) {
		getPersistence().removeByME_S(meetupsEntryId, status);
	}

	/**
	* Returns the number of meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @return the number of matching meetups registrations
	*/
	public static int countByME_S(long meetupsEntryId, int status) {
		return getPersistence().countByME_S(meetupsEntryId, status);
	}

	/**
	* Caches the meetups registration in the entity cache if it is enabled.
	*
	* @param meetupsRegistration the meetups registration
	*/
	public static void cacheResult(MeetupsRegistration meetupsRegistration) {
		getPersistence().cacheResult(meetupsRegistration);
	}

	/**
	* Caches the meetups registrations in the entity cache if it is enabled.
	*
	* @param meetupsRegistrations the meetups registrations
	*/
	public static void cacheResult(
		List<MeetupsRegistration> meetupsRegistrations) {
		getPersistence().cacheResult(meetupsRegistrations);
	}

	/**
	* Creates a new meetups registration with the primary key. Does not add the meetups registration to the database.
	*
	* @param meetupsRegistrationId the primary key for the new meetups registration
	* @return the new meetups registration
	*/
	public static MeetupsRegistration create(long meetupsRegistrationId) {
		return getPersistence().create(meetupsRegistrationId);
	}

	/**
	* Removes the meetups registration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @return the meetups registration that was removed
	* @throws NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	*/
	public static MeetupsRegistration remove(long meetupsRegistrationId)
		throws com.liferay.social.networking.exception.NoSuchMeetupsRegistrationException {
		return getPersistence().remove(meetupsRegistrationId);
	}

	public static MeetupsRegistration updateImpl(
		MeetupsRegistration meetupsRegistration) {
		return getPersistence().updateImpl(meetupsRegistration);
	}

	/**
	* Returns the meetups registration with the primary key or throws a {@link NoSuchMeetupsRegistrationException} if it could not be found.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @return the meetups registration
	* @throws NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	*/
	public static MeetupsRegistration findByPrimaryKey(
		long meetupsRegistrationId)
		throws com.liferay.social.networking.exception.NoSuchMeetupsRegistrationException {
		return getPersistence().findByPrimaryKey(meetupsRegistrationId);
	}

	/**
	* Returns the meetups registration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @return the meetups registration, or <code>null</code> if a meetups registration with the primary key could not be found
	*/
	public static MeetupsRegistration fetchByPrimaryKey(
		long meetupsRegistrationId) {
		return getPersistence().fetchByPrimaryKey(meetupsRegistrationId);
	}

	public static java.util.Map<java.io.Serializable, MeetupsRegistration> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the meetups registrations.
	*
	* @return the meetups registrations
	*/
	public static List<MeetupsRegistration> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the meetups registrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsRegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @return the range of meetups registrations
	*/
	public static List<MeetupsRegistration> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the meetups registrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsRegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of meetups registrations
	*/
	public static List<MeetupsRegistration> findAll(int start, int end,
		OrderByComparator<MeetupsRegistration> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the meetups registrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupsRegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of meetups registrations
	*/
	public static List<MeetupsRegistration> findAll(int start, int end,
		OrderByComparator<MeetupsRegistration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the meetups registrations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of meetups registrations.
	*
	* @return the number of meetups registrations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MeetupsRegistrationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MeetupsRegistrationPersistence, MeetupsRegistrationPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MeetupsRegistrationPersistence.class);
}