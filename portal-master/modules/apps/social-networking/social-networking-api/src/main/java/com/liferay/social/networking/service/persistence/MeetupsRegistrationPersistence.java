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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.social.networking.exception.NoSuchMeetupsRegistrationException;
import com.liferay.social.networking.model.MeetupsRegistration;

/**
 * The persistence interface for the meetups registration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.social.networking.service.persistence.impl.MeetupsRegistrationPersistenceImpl
 * @see MeetupsRegistrationUtil
 * @generated
 */
@ProviderType
public interface MeetupsRegistrationPersistence extends BasePersistence<MeetupsRegistration> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MeetupsRegistrationUtil} to access the meetups registration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the meetups registrations where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @return the matching meetups registrations
	*/
	public java.util.List<MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId);

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
	public java.util.List<MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end);

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
	public java.util.List<MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator);

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
	public java.util.List<MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration
	* @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	*/
	public MeetupsRegistration findByMeetupsEntryId_First(long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator)
		throws NoSuchMeetupsRegistrationException;

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	*/
	public MeetupsRegistration fetchByMeetupsEntryId_First(
		long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator);

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration
	* @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	*/
	public MeetupsRegistration findByMeetupsEntryId_Last(long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator)
		throws NoSuchMeetupsRegistrationException;

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	*/
	public MeetupsRegistration fetchByMeetupsEntryId_Last(long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator);

	/**
	* Returns the meetups registrations before and after the current meetups registration in the ordered set where meetupsEntryId = &#63;.
	*
	* @param meetupsRegistrationId the primary key of the current meetups registration
	* @param meetupsEntryId the meetups entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meetups registration
	* @throws NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	*/
	public MeetupsRegistration[] findByMeetupsEntryId_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator)
		throws NoSuchMeetupsRegistrationException;

	/**
	* Removes all the meetups registrations where meetupsEntryId = &#63; from the database.
	*
	* @param meetupsEntryId the meetups entry ID
	*/
	public void removeByMeetupsEntryId(long meetupsEntryId);

	/**
	* Returns the number of meetups registrations where meetupsEntryId = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @return the number of matching meetups registrations
	*/
	public int countByMeetupsEntryId(long meetupsEntryId);

	/**
	* Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or throws a {@link NoSuchMeetupsRegistrationException} if it could not be found.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the matching meetups registration
	* @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	*/
	public MeetupsRegistration findByU_ME(long userId, long meetupsEntryId)
		throws NoSuchMeetupsRegistrationException;

	/**
	* Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	*/
	public MeetupsRegistration fetchByU_ME(long userId, long meetupsEntryId);

	/**
	* Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	*/
	public MeetupsRegistration fetchByU_ME(long userId, long meetupsEntryId,
		boolean retrieveFromCache);

	/**
	* Removes the meetups registration where userId = &#63; and meetupsEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the meetups registration that was removed
	*/
	public MeetupsRegistration removeByU_ME(long userId, long meetupsEntryId)
		throws NoSuchMeetupsRegistrationException;

	/**
	* Returns the number of meetups registrations where userId = &#63; and meetupsEntryId = &#63;.
	*
	* @param userId the user ID
	* @param meetupsEntryId the meetups entry ID
	* @return the number of matching meetups registrations
	*/
	public int countByU_ME(long userId, long meetupsEntryId);

	/**
	* Returns all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @return the matching meetups registrations
	*/
	public java.util.List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status);

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
	public java.util.List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status, int start, int end);

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
	public java.util.List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator);

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
	public java.util.List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration
	* @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	*/
	public MeetupsRegistration findByME_S_First(long meetupsEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator)
		throws NoSuchMeetupsRegistrationException;

	/**
	* Returns the first meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	*/
	public MeetupsRegistration fetchByME_S_First(long meetupsEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator);

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration
	* @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	*/
	public MeetupsRegistration findByME_S_Last(long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator)
		throws NoSuchMeetupsRegistrationException;

	/**
	* Returns the last meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	*/
	public MeetupsRegistration fetchByME_S_Last(long meetupsEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator);

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
	public MeetupsRegistration[] findByME_S_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator)
		throws NoSuchMeetupsRegistrationException;

	/**
	* Removes all the meetups registrations where meetupsEntryId = &#63; and status = &#63; from the database.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	*/
	public void removeByME_S(long meetupsEntryId, int status);

	/**
	* Returns the number of meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	*
	* @param meetupsEntryId the meetups entry ID
	* @param status the status
	* @return the number of matching meetups registrations
	*/
	public int countByME_S(long meetupsEntryId, int status);

	/**
	* Caches the meetups registration in the entity cache if it is enabled.
	*
	* @param meetupsRegistration the meetups registration
	*/
	public void cacheResult(MeetupsRegistration meetupsRegistration);

	/**
	* Caches the meetups registrations in the entity cache if it is enabled.
	*
	* @param meetupsRegistrations the meetups registrations
	*/
	public void cacheResult(
		java.util.List<MeetupsRegistration> meetupsRegistrations);

	/**
	* Creates a new meetups registration with the primary key. Does not add the meetups registration to the database.
	*
	* @param meetupsRegistrationId the primary key for the new meetups registration
	* @return the new meetups registration
	*/
	public MeetupsRegistration create(long meetupsRegistrationId);

	/**
	* Removes the meetups registration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @return the meetups registration that was removed
	* @throws NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	*/
	public MeetupsRegistration remove(long meetupsRegistrationId)
		throws NoSuchMeetupsRegistrationException;

	public MeetupsRegistration updateImpl(
		MeetupsRegistration meetupsRegistration);

	/**
	* Returns the meetups registration with the primary key or throws a {@link NoSuchMeetupsRegistrationException} if it could not be found.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @return the meetups registration
	* @throws NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	*/
	public MeetupsRegistration findByPrimaryKey(long meetupsRegistrationId)
		throws NoSuchMeetupsRegistrationException;

	/**
	* Returns the meetups registration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @return the meetups registration, or <code>null</code> if a meetups registration with the primary key could not be found
	*/
	public MeetupsRegistration fetchByPrimaryKey(long meetupsRegistrationId);

	@Override
	public java.util.Map<java.io.Serializable, MeetupsRegistration> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the meetups registrations.
	*
	* @return the meetups registrations
	*/
	public java.util.List<MeetupsRegistration> findAll();

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
	public java.util.List<MeetupsRegistration> findAll(int start, int end);

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
	public java.util.List<MeetupsRegistration> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator);

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
	public java.util.List<MeetupsRegistration> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupsRegistration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the meetups registrations from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of meetups registrations.
	*
	* @return the number of meetups registrations
	*/
	public int countAll();
}