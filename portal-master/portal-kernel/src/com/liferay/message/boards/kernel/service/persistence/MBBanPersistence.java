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

import com.liferay.message.boards.kernel.exception.NoSuchBanException;
import com.liferay.message.boards.kernel.model.MBBan;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the message boards ban service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.messageboards.service.persistence.impl.MBBanPersistenceImpl
 * @see MBBanUtil
 * @generated
 */
@ProviderType
public interface MBBanPersistence extends BasePersistence<MBBan> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MBBanUtil} to access the message boards ban persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the message boards bans where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching message boards bans
	*/
	public java.util.List<MBBan> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the message boards bans where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @return the range of matching message boards bans
	*/
	public java.util.List<MBBan> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the message boards bans where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching message boards bans
	*/
	public java.util.List<MBBan> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns an ordered range of all the message boards bans where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching message boards bans
	*/
	public java.util.List<MBBan> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first message boards ban in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards ban
	* @throws NoSuchBanException if a matching message boards ban could not be found
	*/
	public MBBan findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Returns the first message boards ban in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns the last message boards ban in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards ban
	* @throws NoSuchBanException if a matching message boards ban could not be found
	*/
	public MBBan findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Returns the last message boards ban in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns the message boards bans before and after the current message boards ban in the ordered set where uuid = &#63;.
	*
	* @param banId the primary key of the current message boards ban
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message boards ban
	* @throws NoSuchBanException if a message boards ban with the primary key could not be found
	*/
	public MBBan[] findByUuid_PrevAndNext(long banId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Removes all the message boards bans where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of message boards bans where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching message boards bans
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the message boards ban where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBanException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching message boards ban
	* @throws NoSuchBanException if a matching message boards ban could not be found
	*/
	public MBBan findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchBanException;

	/**
	* Returns the message boards ban where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the message boards ban where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the message boards ban where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the message boards ban that was removed
	*/
	public MBBan removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchBanException;

	/**
	* Returns the number of message boards bans where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching message boards bans
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the message boards bans where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching message boards bans
	*/
	public java.util.List<MBBan> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the message boards bans where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @return the range of matching message boards bans
	*/
	public java.util.List<MBBan> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the message boards bans where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching message boards bans
	*/
	public java.util.List<MBBan> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns an ordered range of all the message boards bans where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching message boards bans
	*/
	public java.util.List<MBBan> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first message boards ban in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards ban
	* @throws NoSuchBanException if a matching message boards ban could not be found
	*/
	public MBBan findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Returns the first message boards ban in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns the last message boards ban in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards ban
	* @throws NoSuchBanException if a matching message boards ban could not be found
	*/
	public MBBan findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Returns the last message boards ban in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns the message boards bans before and after the current message boards ban in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param banId the primary key of the current message boards ban
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message boards ban
	* @throws NoSuchBanException if a message boards ban with the primary key could not be found
	*/
	public MBBan[] findByUuid_C_PrevAndNext(long banId, java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Removes all the message boards bans where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of message boards bans where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching message boards bans
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the message boards bans where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching message boards bans
	*/
	public java.util.List<MBBan> findByGroupId(long groupId);

	/**
	* Returns a range of all the message boards bans where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @return the range of matching message boards bans
	*/
	public java.util.List<MBBan> findByGroupId(long groupId, int start, int end);

	/**
	* Returns an ordered range of all the message boards bans where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching message boards bans
	*/
	public java.util.List<MBBan> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns an ordered range of all the message boards bans where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching message boards bans
	*/
	public java.util.List<MBBan> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first message boards ban in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards ban
	* @throws NoSuchBanException if a matching message boards ban could not be found
	*/
	public MBBan findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Returns the first message boards ban in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns the last message boards ban in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards ban
	* @throws NoSuchBanException if a matching message boards ban could not be found
	*/
	public MBBan findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Returns the last message boards ban in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns the message boards bans before and after the current message boards ban in the ordered set where groupId = &#63;.
	*
	* @param banId the primary key of the current message boards ban
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message boards ban
	* @throws NoSuchBanException if a message boards ban with the primary key could not be found
	*/
	public MBBan[] findByGroupId_PrevAndNext(long banId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Removes all the message boards bans where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of message boards bans where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching message boards bans
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the message boards bans where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching message boards bans
	*/
	public java.util.List<MBBan> findByUserId(long userId);

	/**
	* Returns a range of all the message boards bans where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @return the range of matching message boards bans
	*/
	public java.util.List<MBBan> findByUserId(long userId, int start, int end);

	/**
	* Returns an ordered range of all the message boards bans where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching message boards bans
	*/
	public java.util.List<MBBan> findByUserId(long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns an ordered range of all the message boards bans where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching message boards bans
	*/
	public java.util.List<MBBan> findByUserId(long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first message boards ban in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards ban
	* @throws NoSuchBanException if a matching message boards ban could not be found
	*/
	public MBBan findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Returns the first message boards ban in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns the last message boards ban in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards ban
	* @throws NoSuchBanException if a matching message boards ban could not be found
	*/
	public MBBan findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Returns the last message boards ban in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns the message boards bans before and after the current message boards ban in the ordered set where userId = &#63;.
	*
	* @param banId the primary key of the current message boards ban
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message boards ban
	* @throws NoSuchBanException if a message boards ban with the primary key could not be found
	*/
	public MBBan[] findByUserId_PrevAndNext(long banId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Removes all the message boards bans where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of message boards bans where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching message boards bans
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the message boards bans where banUserId = &#63;.
	*
	* @param banUserId the ban user ID
	* @return the matching message boards bans
	*/
	public java.util.List<MBBan> findByBanUserId(long banUserId);

	/**
	* Returns a range of all the message boards bans where banUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param banUserId the ban user ID
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @return the range of matching message boards bans
	*/
	public java.util.List<MBBan> findByBanUserId(long banUserId, int start,
		int end);

	/**
	* Returns an ordered range of all the message boards bans where banUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param banUserId the ban user ID
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching message boards bans
	*/
	public java.util.List<MBBan> findByBanUserId(long banUserId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns an ordered range of all the message boards bans where banUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param banUserId the ban user ID
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching message boards bans
	*/
	public java.util.List<MBBan> findByBanUserId(long banUserId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first message boards ban in the ordered set where banUserId = &#63;.
	*
	* @param banUserId the ban user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards ban
	* @throws NoSuchBanException if a matching message boards ban could not be found
	*/
	public MBBan findByBanUserId_First(long banUserId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Returns the first message boards ban in the ordered set where banUserId = &#63;.
	*
	* @param banUserId the ban user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByBanUserId_First(long banUserId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns the last message boards ban in the ordered set where banUserId = &#63;.
	*
	* @param banUserId the ban user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards ban
	* @throws NoSuchBanException if a matching message boards ban could not be found
	*/
	public MBBan findByBanUserId_Last(long banUserId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Returns the last message boards ban in the ordered set where banUserId = &#63;.
	*
	* @param banUserId the ban user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByBanUserId_Last(long banUserId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns the message boards bans before and after the current message boards ban in the ordered set where banUserId = &#63;.
	*
	* @param banId the primary key of the current message boards ban
	* @param banUserId the ban user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message boards ban
	* @throws NoSuchBanException if a message boards ban with the primary key could not be found
	*/
	public MBBan[] findByBanUserId_PrevAndNext(long banId, long banUserId,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator)
		throws NoSuchBanException;

	/**
	* Removes all the message boards bans where banUserId = &#63; from the database.
	*
	* @param banUserId the ban user ID
	*/
	public void removeByBanUserId(long banUserId);

	/**
	* Returns the number of message boards bans where banUserId = &#63;.
	*
	* @param banUserId the ban user ID
	* @return the number of matching message boards bans
	*/
	public int countByBanUserId(long banUserId);

	/**
	* Returns the message boards ban where groupId = &#63; and banUserId = &#63; or throws a {@link NoSuchBanException} if it could not be found.
	*
	* @param groupId the group ID
	* @param banUserId the ban user ID
	* @return the matching message boards ban
	* @throws NoSuchBanException if a matching message boards ban could not be found
	*/
	public MBBan findByG_B(long groupId, long banUserId)
		throws NoSuchBanException;

	/**
	* Returns the message boards ban where groupId = &#63; and banUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param banUserId the ban user ID
	* @return the matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByG_B(long groupId, long banUserId);

	/**
	* Returns the message boards ban where groupId = &#63; and banUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param banUserId the ban user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching message boards ban, or <code>null</code> if a matching message boards ban could not be found
	*/
	public MBBan fetchByG_B(long groupId, long banUserId,
		boolean retrieveFromCache);

	/**
	* Removes the message boards ban where groupId = &#63; and banUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param banUserId the ban user ID
	* @return the message boards ban that was removed
	*/
	public MBBan removeByG_B(long groupId, long banUserId)
		throws NoSuchBanException;

	/**
	* Returns the number of message boards bans where groupId = &#63; and banUserId = &#63;.
	*
	* @param groupId the group ID
	* @param banUserId the ban user ID
	* @return the number of matching message boards bans
	*/
	public int countByG_B(long groupId, long banUserId);

	/**
	* Caches the message boards ban in the entity cache if it is enabled.
	*
	* @param mbBan the message boards ban
	*/
	public void cacheResult(MBBan mbBan);

	/**
	* Caches the message boards bans in the entity cache if it is enabled.
	*
	* @param mbBans the message boards bans
	*/
	public void cacheResult(java.util.List<MBBan> mbBans);

	/**
	* Creates a new message boards ban with the primary key. Does not add the message boards ban to the database.
	*
	* @param banId the primary key for the new message boards ban
	* @return the new message boards ban
	*/
	public MBBan create(long banId);

	/**
	* Removes the message boards ban with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param banId the primary key of the message boards ban
	* @return the message boards ban that was removed
	* @throws NoSuchBanException if a message boards ban with the primary key could not be found
	*/
	public MBBan remove(long banId) throws NoSuchBanException;

	public MBBan updateImpl(MBBan mbBan);

	/**
	* Returns the message boards ban with the primary key or throws a {@link NoSuchBanException} if it could not be found.
	*
	* @param banId the primary key of the message boards ban
	* @return the message boards ban
	* @throws NoSuchBanException if a message boards ban with the primary key could not be found
	*/
	public MBBan findByPrimaryKey(long banId) throws NoSuchBanException;

	/**
	* Returns the message boards ban with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param banId the primary key of the message boards ban
	* @return the message boards ban, or <code>null</code> if a message boards ban with the primary key could not be found
	*/
	public MBBan fetchByPrimaryKey(long banId);

	@Override
	public java.util.Map<java.io.Serializable, MBBan> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the message boards bans.
	*
	* @return the message boards bans
	*/
	public java.util.List<MBBan> findAll();

	/**
	* Returns a range of all the message boards bans.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @return the range of message boards bans
	*/
	public java.util.List<MBBan> findAll(int start, int end);

	/**
	* Returns an ordered range of all the message boards bans.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of message boards bans
	*/
	public java.util.List<MBBan> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator);

	/**
	* Returns an ordered range of all the message boards bans.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MBBanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of message boards bans
	* @param end the upper bound of the range of message boards bans (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of message boards bans
	*/
	public java.util.List<MBBan> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MBBan> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the message boards bans from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of message boards bans.
	*
	* @return the number of message boards bans
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}