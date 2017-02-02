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

package com.liferay.portal.kernel.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.NoSuchUserNotificationEventException;
import com.liferay.portal.kernel.model.UserNotificationEvent;

/**
 * The persistence interface for the user notification event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.persistence.impl.UserNotificationEventPersistenceImpl
 * @see UserNotificationEventUtil
 * @generated
 */
@ProviderType
public interface UserNotificationEventPersistence extends BasePersistence<UserNotificationEvent> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserNotificationEventUtil} to access the user notification event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the user notification events where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByUuid(
		java.lang.String uuid);

	/**
	* Returns a range of all the user notification events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByUuid(
		java.lang.String uuid, int start, int end);

	/**
	* Returns an ordered range of all the user notification events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where uuid = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByUuid_PrevAndNext(
		long userNotificationEventId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of user notification events where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching user notification events
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the user notification events where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByUuid_C(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of all the user notification events where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the user notification events where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByUuid_C_PrevAndNext(
		long userNotificationEventId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of user notification events where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching user notification events
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the user notification events where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByUserId(long userId);

	/**
	* Returns a range of all the user notification events where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByUserId(long userId,
		int start, int end);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByUserId(long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByUserId(long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByUserId_PrevAndNext(
		long userNotificationEventId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of user notification events where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user notification events
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the user notification events where type = &#63;.
	*
	* @param type the type
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByType(
		java.lang.String type);

	/**
	* Returns a range of all the user notification events where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByType(
		java.lang.String type, int start, int end);

	/**
	* Returns an ordered range of all the user notification events where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByType(
		java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByType(
		java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByType_First(java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByType_First(java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByType_Last(java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByType_Last(java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where type = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByType_PrevAndNext(
		long userNotificationEventId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where type = &#63; from the database.
	*
	* @param type the type
	*/
	public void removeByType(java.lang.String type);

	/**
	* Returns the number of user notification events where type = &#63;.
	*
	* @param type the type
	* @return the number of matching user notification events
	*/
	public int countByType(java.lang.String type);

	/**
	* Returns all the user notification events where userId = &#63; and deliveryType = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT(long userId,
		int deliveryType);

	/**
	* Returns a range of all the user notification events where userId = &#63; and deliveryType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT(long userId,
		int deliveryType, int start, int end);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and deliveryType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT(long userId,
		int deliveryType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and deliveryType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT(long userId,
		int deliveryType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and deliveryType = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_DT_First(long userId,
		int deliveryType,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and deliveryType = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_DT_First(long userId,
		int deliveryType,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and deliveryType = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_DT_Last(long userId, int deliveryType,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and deliveryType = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_DT_Last(long userId,
		int deliveryType,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and deliveryType = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByU_DT_PrevAndNext(
		long userNotificationEventId, long userId, int deliveryType,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where userId = &#63; and deliveryType = &#63; from the database.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	*/
	public void removeByU_DT(long userId, int deliveryType);

	/**
	* Returns the number of user notification events where userId = &#63; and deliveryType = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @return the number of matching user notification events
	*/
	public int countByU_DT(long userId, int deliveryType);

	/**
	* Returns all the user notification events where userId = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_D(long userId,
		boolean delivered);

	/**
	* Returns a range of all the user notification events where userId = &#63; and delivered = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_D(long userId,
		boolean delivered, int start, int end);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and delivered = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_D(long userId,
		boolean delivered, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and delivered = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_D(long userId,
		boolean delivered, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_D_First(long userId,
		boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_D_First(long userId,
		boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_D_Last(long userId, boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_D_Last(long userId,
		boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and delivered = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByU_D_PrevAndNext(
		long userNotificationEventId, long userId, boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where userId = &#63; and delivered = &#63; from the database.
	*
	* @param userId the user ID
	* @param delivered the delivered
	*/
	public void removeByU_D(long userId, boolean delivered);

	/**
	* Returns the number of user notification events where userId = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @return the number of matching user notification events
	*/
	public int countByU_D(long userId, boolean delivered);

	/**
	* Returns all the user notification events where userId = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param archived the archived
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_A(long userId,
		boolean archived);

	/**
	* Returns a range of all the user notification events where userId = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_A(long userId,
		boolean archived, int start, int end);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_A(long userId,
		boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_A(long userId,
		boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_A_First(long userId, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_A_First(long userId,
		boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_A_Last(long userId, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_A_Last(long userId, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and archived = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByU_A_PrevAndNext(
		long userNotificationEventId, long userId, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where userId = &#63; and archived = &#63; from the database.
	*
	* @param userId the user ID
	* @param archived the archived
	*/
	public void removeByU_A(long userId, boolean archived);

	/**
	* Returns the number of user notification events where userId = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param archived the archived
	* @return the number of matching user notification events
	*/
	public int countByU_A(long userId, boolean archived);

	/**
	* Returns all the user notification events where userId = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_D(long userId,
		int deliveryType, boolean delivered);

	/**
	* Returns a range of all the user notification events where userId = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_D(long userId,
		int deliveryType, boolean delivered, int start, int end);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_D(long userId,
		int deliveryType, boolean delivered, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_D(long userId,
		int deliveryType, boolean delivered, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_DT_D_First(long userId,
		int deliveryType, boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_DT_D_First(long userId,
		int deliveryType, boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_DT_D_Last(long userId,
		int deliveryType, boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_DT_D_Last(long userId,
		int deliveryType, boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByU_DT_D_PrevAndNext(
		long userNotificationEventId, long userId, int deliveryType,
		boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where userId = &#63; and deliveryType = &#63; and delivered = &#63; from the database.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	*/
	public void removeByU_DT_D(long userId, int deliveryType, boolean delivered);

	/**
	* Returns the number of user notification events where userId = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @return the number of matching user notification events
	*/
	public int countByU_DT_D(long userId, int deliveryType, boolean delivered);

	/**
	* Returns all the user notification events where userId = &#63; and deliveryType = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param archived the archived
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_A(long userId,
		int deliveryType, boolean archived);

	/**
	* Returns a range of all the user notification events where userId = &#63; and deliveryType = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_A(long userId,
		int deliveryType, boolean archived, int start, int end);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and deliveryType = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_A(long userId,
		int deliveryType, boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and deliveryType = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_A(long userId,
		int deliveryType, boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_DT_A_First(long userId,
		int deliveryType, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_DT_A_First(long userId,
		int deliveryType, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_DT_A_Last(long userId,
		int deliveryType, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_DT_A_Last(long userId,
		int deliveryType, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and archived = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByU_DT_A_PrevAndNext(
		long userNotificationEventId, long userId, int deliveryType,
		boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where userId = &#63; and deliveryType = &#63; and archived = &#63; from the database.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param archived the archived
	*/
	public void removeByU_DT_A(long userId, int deliveryType, boolean archived);

	/**
	* Returns the number of user notification events where userId = &#63; and deliveryType = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param archived the archived
	* @return the number of matching user notification events
	*/
	public int countByU_DT_A(long userId, int deliveryType, boolean archived);

	/**
	* Returns all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_D_A(long userId,
		boolean delivered, boolean actionRequired);

	/**
	* Returns a range of all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_D_A(long userId,
		boolean delivered, boolean actionRequired, int start, int end);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_D_A(long userId,
		boolean delivered, boolean actionRequired, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_D_A(long userId,
		boolean delivered, boolean actionRequired, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_D_A_First(long userId,
		boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_D_A_First(long userId,
		boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_D_A_Last(long userId,
		boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_D_A_Last(long userId,
		boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByU_D_A_PrevAndNext(
		long userNotificationEventId, long userId, boolean delivered,
		boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63; from the database.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	*/
	public void removeByU_D_A(long userId, boolean delivered,
		boolean actionRequired);

	/**
	* Returns the number of user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @return the number of matching user notification events
	*/
	public int countByU_D_A(long userId, boolean delivered,
		boolean actionRequired);

	/**
	* Returns all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_A_A(long userId,
		boolean actionRequired, boolean archived);

	/**
	* Returns a range of all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_A_A(long userId,
		boolean actionRequired, boolean archived, int start, int end);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_A_A(long userId,
		boolean actionRequired, boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_A_A(long userId,
		boolean actionRequired, boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_A_A_First(long userId,
		boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_A_A_First(long userId,
		boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_A_A_Last(long userId,
		boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_A_A_Last(long userId,
		boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByU_A_A_PrevAndNext(
		long userNotificationEventId, long userId, boolean actionRequired,
		boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63; from the database.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	*/
	public void removeByU_A_A(long userId, boolean actionRequired,
		boolean archived);

	/**
	* Returns the number of user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @return the number of matching user notification events
	*/
	public int countByU_A_A(long userId, boolean actionRequired,
		boolean archived);

	/**
	* Returns all the user notification events where userId = &#63; and type = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_T_DT_D(long userId,
		java.lang.String type, int deliveryType, boolean delivered);

	/**
	* Returns a range of all the user notification events where userId = &#63; and type = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_T_DT_D(long userId,
		java.lang.String type, int deliveryType, boolean delivered, int start,
		int end);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and type = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_T_DT_D(long userId,
		java.lang.String type, int deliveryType, boolean delivered, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and type = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_T_DT_D(long userId,
		java.lang.String type, int deliveryType, boolean delivered, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and type = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_T_DT_D_First(long userId,
		java.lang.String type, int deliveryType, boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and type = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_T_DT_D_First(long userId,
		java.lang.String type, int deliveryType, boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and type = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_T_DT_D_Last(long userId,
		java.lang.String type, int deliveryType, boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and type = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_T_DT_D_Last(long userId,
		java.lang.String type, int deliveryType, boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and type = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param type the type
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByU_T_DT_D_PrevAndNext(
		long userNotificationEventId, long userId, java.lang.String type,
		int deliveryType, boolean delivered,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where userId = &#63; and type = &#63; and deliveryType = &#63; and delivered = &#63; from the database.
	*
	* @param userId the user ID
	* @param type the type
	* @param deliveryType the delivery type
	* @param delivered the delivered
	*/
	public void removeByU_T_DT_D(long userId, java.lang.String type,
		int deliveryType, boolean delivered);

	/**
	* Returns the number of user notification events where userId = &#63; and type = &#63; and deliveryType = &#63; and delivered = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @return the number of matching user notification events
	*/
	public int countByU_T_DT_D(long userId, java.lang.String type,
		int deliveryType, boolean delivered);

	/**
	* Returns all the user notification events where userId = &#63; and deliveryType = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param actionRequired the action required
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_D_A(long userId,
		int deliveryType, boolean delivered, boolean actionRequired);

	/**
	* Returns a range of all the user notification events where userId = &#63; and deliveryType = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_D_A(long userId,
		int deliveryType, boolean delivered, boolean actionRequired, int start,
		int end);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and deliveryType = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_D_A(long userId,
		int deliveryType, boolean delivered, boolean actionRequired, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and deliveryType = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_D_A(long userId,
		int deliveryType, boolean delivered, boolean actionRequired, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_DT_D_A_First(long userId,
		int deliveryType, boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_DT_D_A_First(long userId,
		int deliveryType, boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_DT_D_A_Last(long userId,
		int deliveryType, boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_DT_D_A_Last(long userId,
		int deliveryType, boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByU_DT_D_A_PrevAndNext(
		long userNotificationEventId, long userId, int deliveryType,
		boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where userId = &#63; and deliveryType = &#63; and delivered = &#63; and actionRequired = &#63; from the database.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param actionRequired the action required
	*/
	public void removeByU_DT_D_A(long userId, int deliveryType,
		boolean delivered, boolean actionRequired);

	/**
	* Returns the number of user notification events where userId = &#63; and deliveryType = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param delivered the delivered
	* @param actionRequired the action required
	* @return the number of matching user notification events
	*/
	public int countByU_DT_D_A(long userId, int deliveryType,
		boolean delivered, boolean actionRequired);

	/**
	* Returns all the user notification events where userId = &#63; and deliveryType = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param actionRequired the action required
	* @param archived the archived
	* @return the matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_A_A(long userId,
		int deliveryType, boolean actionRequired, boolean archived);

	/**
	* Returns a range of all the user notification events where userId = &#63; and deliveryType = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param actionRequired the action required
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_A_A(long userId,
		int deliveryType, boolean actionRequired, boolean archived, int start,
		int end);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and deliveryType = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param actionRequired the action required
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_A_A(long userId,
		int deliveryType, boolean actionRequired, boolean archived, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and deliveryType = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param actionRequired the action required
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification events
	*/
	public java.util.List<UserNotificationEvent> findByU_DT_A_A(long userId,
		int deliveryType, boolean actionRequired, boolean archived, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_DT_A_A_First(long userId,
		int deliveryType, boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_DT_A_A_First(long userId,
		int deliveryType, boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws NoSuchUserNotificationEventException if a matching user notification event could not be found
	*/
	public UserNotificationEvent findByU_DT_A_A_Last(long userId,
		int deliveryType, boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	*/
	public UserNotificationEvent fetchByU_DT_A_A_Last(long userId,
		int deliveryType, boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and deliveryType = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userNotificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent[] findByU_DT_A_A_PrevAndNext(
		long userNotificationEventId, long userId, int deliveryType,
		boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator)
		throws NoSuchUserNotificationEventException;

	/**
	* Removes all the user notification events where userId = &#63; and deliveryType = &#63; and actionRequired = &#63; and archived = &#63; from the database.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param actionRequired the action required
	* @param archived the archived
	*/
	public void removeByU_DT_A_A(long userId, int deliveryType,
		boolean actionRequired, boolean archived);

	/**
	* Returns the number of user notification events where userId = &#63; and deliveryType = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param deliveryType the delivery type
	* @param actionRequired the action required
	* @param archived the archived
	* @return the number of matching user notification events
	*/
	public int countByU_DT_A_A(long userId, int deliveryType,
		boolean actionRequired, boolean archived);

	/**
	* Caches the user notification event in the entity cache if it is enabled.
	*
	* @param userNotificationEvent the user notification event
	*/
	public void cacheResult(UserNotificationEvent userNotificationEvent);

	/**
	* Caches the user notification events in the entity cache if it is enabled.
	*
	* @param userNotificationEvents the user notification events
	*/
	public void cacheResult(
		java.util.List<UserNotificationEvent> userNotificationEvents);

	/**
	* Creates a new user notification event with the primary key. Does not add the user notification event to the database.
	*
	* @param userNotificationEventId the primary key for the new user notification event
	* @return the new user notification event
	*/
	public UserNotificationEvent create(long userNotificationEventId);

	/**
	* Removes the user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userNotificationEventId the primary key of the user notification event
	* @return the user notification event that was removed
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent remove(long userNotificationEventId)
		throws NoSuchUserNotificationEventException;

	public UserNotificationEvent updateImpl(
		UserNotificationEvent userNotificationEvent);

	/**
	* Returns the user notification event with the primary key or throws a {@link NoSuchUserNotificationEventException} if it could not be found.
	*
	* @param userNotificationEventId the primary key of the user notification event
	* @return the user notification event
	* @throws NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent findByPrimaryKey(long userNotificationEventId)
		throws NoSuchUserNotificationEventException;

	/**
	* Returns the user notification event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userNotificationEventId the primary key of the user notification event
	* @return the user notification event, or <code>null</code> if a user notification event with the primary key could not be found
	*/
	public UserNotificationEvent fetchByPrimaryKey(long userNotificationEventId);

	@Override
	public java.util.Map<java.io.Serializable, UserNotificationEvent> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the user notification events.
	*
	* @return the user notification events
	*/
	public java.util.List<UserNotificationEvent> findAll();

	/**
	* Returns a range of all the user notification events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of user notification events
	*/
	public java.util.List<UserNotificationEvent> findAll(int start, int end);

	/**
	* Returns an ordered range of all the user notification events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user notification events
	*/
	public java.util.List<UserNotificationEvent> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the user notification events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user notification events
	*/
	public java.util.List<UserNotificationEvent> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the user notification events from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of user notification events.
	*
	* @return the number of user notification events
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}