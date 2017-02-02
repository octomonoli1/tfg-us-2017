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

package com.liferay.message.boards.kernel.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.message.boards.kernel.exception.NoSuchMailingListException;
import com.liferay.message.boards.kernel.model.MBMailingList;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the message boards mailing list service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.messageboards.service.persistence.impl.MBMailingListPersistenceImpl
 * @see MBMailingListUtil
 * @generated
 */
@ProviderType
public interface MBMailingListPersistence extends BasePersistence<MBMailingList> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MBMailingListUtil} to access the message boards mailing list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the message boards mailing lists where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching message boards mailing lists
	*/
	public java.util.List<MBMailingList> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the message boards mailing lists where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBMailingListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of message boards mailing lists
	* @param end the upper bound of the range of message boards mailing lists (not inclusive)
	* @return the range of matching message boards mailing lists
	*/
	public java.util.List<MBMailingList> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the message boards mailing lists where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBMailingListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of message boards mailing lists
	* @param end the upper bound of the range of message boards mailing lists (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching message boards mailing lists
	*/
	public java.util.List<MBMailingList> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator);

	/**
	* Returns an ordered range of all the message boards mailing lists where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBMailingListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of message boards mailing lists
	* @param end the upper bound of the range of message boards mailing lists (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching message boards mailing lists
	*/
	public java.util.List<MBMailingList> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first message boards mailing list in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards mailing list
	* @throws NoSuchMailingListException if a matching message boards mailing list could not be found
	*/
	public MBMailingList findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator)
		throws NoSuchMailingListException;

	/**
	* Returns the first message boards mailing list in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards mailing list, or <code>null</code> if a matching message boards mailing list could not be found
	*/
	public MBMailingList fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator);

	/**
	* Returns the last message boards mailing list in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards mailing list
	* @throws NoSuchMailingListException if a matching message boards mailing list could not be found
	*/
	public MBMailingList findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator)
		throws NoSuchMailingListException;

	/**
	* Returns the last message boards mailing list in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards mailing list, or <code>null</code> if a matching message boards mailing list could not be found
	*/
	public MBMailingList fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator);

	/**
	* Returns the message boards mailing lists before and after the current message boards mailing list in the ordered set where uuid = &#63;.
	*
	* @param mailingListId the primary key of the current message boards mailing list
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message boards mailing list
	* @throws NoSuchMailingListException if a message boards mailing list with the primary key could not be found
	*/
	public MBMailingList[] findByUuid_PrevAndNext(long mailingListId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator)
		throws NoSuchMailingListException;

	/**
	* Removes all the message boards mailing lists where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of message boards mailing lists where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching message boards mailing lists
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the message boards mailing list where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchMailingListException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching message boards mailing list
	* @throws NoSuchMailingListException if a matching message boards mailing list could not be found
	*/
	public MBMailingList findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchMailingListException;

	/**
	* Returns the message boards mailing list where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching message boards mailing list, or <code>null</code> if a matching message boards mailing list could not be found
	*/
	public MBMailingList fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the message boards mailing list where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching message boards mailing list, or <code>null</code> if a matching message boards mailing list could not be found
	*/
	public MBMailingList fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the message boards mailing list where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the message boards mailing list that was removed
	*/
	public MBMailingList removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchMailingListException;

	/**
	* Returns the number of message boards mailing lists where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching message boards mailing lists
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the message boards mailing lists where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching message boards mailing lists
	*/
	public java.util.List<MBMailingList> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the message boards mailing lists where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBMailingListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of message boards mailing lists
	* @param end the upper bound of the range of message boards mailing lists (not inclusive)
	* @return the range of matching message boards mailing lists
	*/
	public java.util.List<MBMailingList> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the message boards mailing lists where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBMailingListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of message boards mailing lists
	* @param end the upper bound of the range of message boards mailing lists (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching message boards mailing lists
	*/
	public java.util.List<MBMailingList> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator);

	/**
	* Returns an ordered range of all the message boards mailing lists where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBMailingListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of message boards mailing lists
	* @param end the upper bound of the range of message boards mailing lists (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching message boards mailing lists
	*/
	public java.util.List<MBMailingList> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first message boards mailing list in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards mailing list
	* @throws NoSuchMailingListException if a matching message boards mailing list could not be found
	*/
	public MBMailingList findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator)
		throws NoSuchMailingListException;

	/**
	* Returns the first message boards mailing list in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards mailing list, or <code>null</code> if a matching message boards mailing list could not be found
	*/
	public MBMailingList fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator);

	/**
	* Returns the last message boards mailing list in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards mailing list
	* @throws NoSuchMailingListException if a matching message boards mailing list could not be found
	*/
	public MBMailingList findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator)
		throws NoSuchMailingListException;

	/**
	* Returns the last message boards mailing list in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards mailing list, or <code>null</code> if a matching message boards mailing list could not be found
	*/
	public MBMailingList fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator);

	/**
	* Returns the message boards mailing lists before and after the current message boards mailing list in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param mailingListId the primary key of the current message boards mailing list
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message boards mailing list
	* @throws NoSuchMailingListException if a message boards mailing list with the primary key could not be found
	*/
	public MBMailingList[] findByUuid_C_PrevAndNext(long mailingListId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator)
		throws NoSuchMailingListException;

	/**
	* Removes all the message boards mailing lists where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of message boards mailing lists where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching message boards mailing lists
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the message boards mailing lists where active = &#63;.
	*
	* @param active the active
	* @return the matching message boards mailing lists
	*/
	public java.util.List<MBMailingList> findByActive(boolean active);

	/**
	* Returns a range of all the message boards mailing lists where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBMailingListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of message boards mailing lists
	* @param end the upper bound of the range of message boards mailing lists (not inclusive)
	* @return the range of matching message boards mailing lists
	*/
	public java.util.List<MBMailingList> findByActive(boolean active,
		int start, int end);

	/**
	* Returns an ordered range of all the message boards mailing lists where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBMailingListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of message boards mailing lists
	* @param end the upper bound of the range of message boards mailing lists (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching message boards mailing lists
	*/
	public java.util.List<MBMailingList> findByActive(boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator);

	/**
	* Returns an ordered range of all the message boards mailing lists where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBMailingListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of message boards mailing lists
	* @param end the upper bound of the range of message boards mailing lists (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching message boards mailing lists
	*/
	public java.util.List<MBMailingList> findByActive(boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first message boards mailing list in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards mailing list
	* @throws NoSuchMailingListException if a matching message boards mailing list could not be found
	*/
	public MBMailingList findByActive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator)
		throws NoSuchMailingListException;

	/**
	* Returns the first message boards mailing list in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards mailing list, or <code>null</code> if a matching message boards mailing list could not be found
	*/
	public MBMailingList fetchByActive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator);

	/**
	* Returns the last message boards mailing list in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards mailing list
	* @throws NoSuchMailingListException if a matching message boards mailing list could not be found
	*/
	public MBMailingList findByActive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator)
		throws NoSuchMailingListException;

	/**
	* Returns the last message boards mailing list in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards mailing list, or <code>null</code> if a matching message boards mailing list could not be found
	*/
	public MBMailingList fetchByActive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator);

	/**
	* Returns the message boards mailing lists before and after the current message boards mailing list in the ordered set where active = &#63;.
	*
	* @param mailingListId the primary key of the current message boards mailing list
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message boards mailing list
	* @throws NoSuchMailingListException if a message boards mailing list with the primary key could not be found
	*/
	public MBMailingList[] findByActive_PrevAndNext(long mailingListId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator)
		throws NoSuchMailingListException;

	/**
	* Removes all the message boards mailing lists where active = &#63; from the database.
	*
	* @param active the active
	*/
	public void removeByActive(boolean active);

	/**
	* Returns the number of message boards mailing lists where active = &#63;.
	*
	* @param active the active
	* @return the number of matching message boards mailing lists
	*/
	public int countByActive(boolean active);

	/**
	* Returns the message boards mailing list where groupId = &#63; and categoryId = &#63; or throws a {@link NoSuchMailingListException} if it could not be found.
	*
	* @param groupId the group ID
	* @param categoryId the category ID
	* @return the matching message boards mailing list
	* @throws NoSuchMailingListException if a matching message boards mailing list could not be found
	*/
	public MBMailingList findByG_C(long groupId, long categoryId)
		throws NoSuchMailingListException;

	/**
	* Returns the message boards mailing list where groupId = &#63; and categoryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param categoryId the category ID
	* @return the matching message boards mailing list, or <code>null</code> if a matching message boards mailing list could not be found
	*/
	public MBMailingList fetchByG_C(long groupId, long categoryId);

	/**
	* Returns the message boards mailing list where groupId = &#63; and categoryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param categoryId the category ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching message boards mailing list, or <code>null</code> if a matching message boards mailing list could not be found
	*/
	public MBMailingList fetchByG_C(long groupId, long categoryId,
		boolean retrieveFromCache);

	/**
	* Removes the message boards mailing list where groupId = &#63; and categoryId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param categoryId the category ID
	* @return the message boards mailing list that was removed
	*/
	public MBMailingList removeByG_C(long groupId, long categoryId)
		throws NoSuchMailingListException;

	/**
	* Returns the number of message boards mailing lists where groupId = &#63; and categoryId = &#63;.
	*
	* @param groupId the group ID
	* @param categoryId the category ID
	* @return the number of matching message boards mailing lists
	*/
	public int countByG_C(long groupId, long categoryId);

	/**
	* Caches the message boards mailing list in the entity cache if it is enabled.
	*
	* @param mbMailingList the message boards mailing list
	*/
	public void cacheResult(MBMailingList mbMailingList);

	/**
	* Caches the message boards mailing lists in the entity cache if it is enabled.
	*
	* @param mbMailingLists the message boards mailing lists
	*/
	public void cacheResult(java.util.List<MBMailingList> mbMailingLists);

	/**
	* Creates a new message boards mailing list with the primary key. Does not add the message boards mailing list to the database.
	*
	* @param mailingListId the primary key for the new message boards mailing list
	* @return the new message boards mailing list
	*/
	public MBMailingList create(long mailingListId);

	/**
	* Removes the message boards mailing list with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mailingListId the primary key of the message boards mailing list
	* @return the message boards mailing list that was removed
	* @throws NoSuchMailingListException if a message boards mailing list with the primary key could not be found
	*/
	public MBMailingList remove(long mailingListId)
		throws NoSuchMailingListException;

	public MBMailingList updateImpl(MBMailingList mbMailingList);

	/**
	* Returns the message boards mailing list with the primary key or throws a {@link NoSuchMailingListException} if it could not be found.
	*
	* @param mailingListId the primary key of the message boards mailing list
	* @return the message boards mailing list
	* @throws NoSuchMailingListException if a message boards mailing list with the primary key could not be found
	*/
	public MBMailingList findByPrimaryKey(long mailingListId)
		throws NoSuchMailingListException;

	/**
	* Returns the message boards mailing list with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mailingListId the primary key of the message boards mailing list
	* @return the message boards mailing list, or <code>null</code> if a message boards mailing list with the primary key could not be found
	*/
	public MBMailingList fetchByPrimaryKey(long mailingListId);

	@Override
	public java.util.Map<java.io.Serializable, MBMailingList> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the message boards mailing lists.
	*
	* @return the message boards mailing lists
	*/
	public java.util.List<MBMailingList> findAll();

	/**
	* Returns a range of all the message boards mailing lists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBMailingListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of message boards mailing lists
	* @param end the upper bound of the range of message boards mailing lists (not inclusive)
	* @return the range of message boards mailing lists
	*/
	public java.util.List<MBMailingList> findAll(int start, int end);

	/**
	* Returns an ordered range of all the message boards mailing lists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBMailingListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of message boards mailing lists
	* @param end the upper bound of the range of message boards mailing lists (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of message boards mailing lists
	*/
	public java.util.List<MBMailingList> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator);

	/**
	* Returns an ordered range of all the message boards mailing lists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBMailingListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of message boards mailing lists
	* @param end the upper bound of the range of message boards mailing lists (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of message boards mailing lists
	*/
	public java.util.List<MBMailingList> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBMailingList> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the message boards mailing lists from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of message boards mailing lists.
	*
	* @return the number of message boards mailing lists
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}