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

import com.liferay.portal.kernel.exception.NoSuchMembershipRequestException;
import com.liferay.portal.kernel.model.MembershipRequest;

/**
 * The persistence interface for the membership request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.persistence.impl.MembershipRequestPersistenceImpl
 * @see MembershipRequestUtil
 * @generated
 */
@ProviderType
public interface MembershipRequestPersistence extends BasePersistence<MembershipRequest> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MembershipRequestUtil} to access the membership request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the membership requests where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching membership requests
	*/
	public java.util.List<MembershipRequest> findByGroupId(long groupId);

	/**
	* Returns a range of all the membership requests where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @return the range of matching membership requests
	*/
	public java.util.List<MembershipRequest> findByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the membership requests where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching membership requests
	*/
	public java.util.List<MembershipRequest> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator);

	/**
	* Returns an ordered range of all the membership requests where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching membership requests
	*/
	public java.util.List<MembershipRequest> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first membership request in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching membership request
	* @throws NoSuchMembershipRequestException if a matching membership request could not be found
	*/
	public MembershipRequest findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException;

	/**
	* Returns the first membership request in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching membership request, or <code>null</code> if a matching membership request could not be found
	*/
	public MembershipRequest fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator);

	/**
	* Returns the last membership request in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching membership request
	* @throws NoSuchMembershipRequestException if a matching membership request could not be found
	*/
	public MembershipRequest findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException;

	/**
	* Returns the last membership request in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching membership request, or <code>null</code> if a matching membership request could not be found
	*/
	public MembershipRequest fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator);

	/**
	* Returns the membership requests before and after the current membership request in the ordered set where groupId = &#63;.
	*
	* @param membershipRequestId the primary key of the current membership request
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next membership request
	* @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	*/
	public MembershipRequest[] findByGroupId_PrevAndNext(
		long membershipRequestId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException;

	/**
	* Removes all the membership requests where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of membership requests where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching membership requests
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the membership requests where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching membership requests
	*/
	public java.util.List<MembershipRequest> findByUserId(long userId);

	/**
	* Returns a range of all the membership requests where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @return the range of matching membership requests
	*/
	public java.util.List<MembershipRequest> findByUserId(long userId,
		int start, int end);

	/**
	* Returns an ordered range of all the membership requests where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching membership requests
	*/
	public java.util.List<MembershipRequest> findByUserId(long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator);

	/**
	* Returns an ordered range of all the membership requests where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching membership requests
	*/
	public java.util.List<MembershipRequest> findByUserId(long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first membership request in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching membership request
	* @throws NoSuchMembershipRequestException if a matching membership request could not be found
	*/
	public MembershipRequest findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException;

	/**
	* Returns the first membership request in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching membership request, or <code>null</code> if a matching membership request could not be found
	*/
	public MembershipRequest fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator);

	/**
	* Returns the last membership request in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching membership request
	* @throws NoSuchMembershipRequestException if a matching membership request could not be found
	*/
	public MembershipRequest findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException;

	/**
	* Returns the last membership request in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching membership request, or <code>null</code> if a matching membership request could not be found
	*/
	public MembershipRequest fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator);

	/**
	* Returns the membership requests before and after the current membership request in the ordered set where userId = &#63;.
	*
	* @param membershipRequestId the primary key of the current membership request
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next membership request
	* @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	*/
	public MembershipRequest[] findByUserId_PrevAndNext(
		long membershipRequestId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException;

	/**
	* Removes all the membership requests where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of membership requests where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching membership requests
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the membership requests where groupId = &#63; and statusId = &#63;.
	*
	* @param groupId the group ID
	* @param statusId the status ID
	* @return the matching membership requests
	*/
	public java.util.List<MembershipRequest> findByG_S(long groupId,
		long statusId);

	/**
	* Returns a range of all the membership requests where groupId = &#63; and statusId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param statusId the status ID
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @return the range of matching membership requests
	*/
	public java.util.List<MembershipRequest> findByG_S(long groupId,
		long statusId, int start, int end);

	/**
	* Returns an ordered range of all the membership requests where groupId = &#63; and statusId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param statusId the status ID
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching membership requests
	*/
	public java.util.List<MembershipRequest> findByG_S(long groupId,
		long statusId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator);

	/**
	* Returns an ordered range of all the membership requests where groupId = &#63; and statusId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param statusId the status ID
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching membership requests
	*/
	public java.util.List<MembershipRequest> findByG_S(long groupId,
		long statusId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	*
	* @param groupId the group ID
	* @param statusId the status ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching membership request
	* @throws NoSuchMembershipRequestException if a matching membership request could not be found
	*/
	public MembershipRequest findByG_S_First(long groupId, long statusId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException;

	/**
	* Returns the first membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	*
	* @param groupId the group ID
	* @param statusId the status ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching membership request, or <code>null</code> if a matching membership request could not be found
	*/
	public MembershipRequest fetchByG_S_First(long groupId, long statusId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator);

	/**
	* Returns the last membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	*
	* @param groupId the group ID
	* @param statusId the status ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching membership request
	* @throws NoSuchMembershipRequestException if a matching membership request could not be found
	*/
	public MembershipRequest findByG_S_Last(long groupId, long statusId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException;

	/**
	* Returns the last membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	*
	* @param groupId the group ID
	* @param statusId the status ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching membership request, or <code>null</code> if a matching membership request could not be found
	*/
	public MembershipRequest fetchByG_S_Last(long groupId, long statusId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator);

	/**
	* Returns the membership requests before and after the current membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	*
	* @param membershipRequestId the primary key of the current membership request
	* @param groupId the group ID
	* @param statusId the status ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next membership request
	* @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	*/
	public MembershipRequest[] findByG_S_PrevAndNext(long membershipRequestId,
		long groupId, long statusId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException;

	/**
	* Removes all the membership requests where groupId = &#63; and statusId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param statusId the status ID
	*/
	public void removeByG_S(long groupId, long statusId);

	/**
	* Returns the number of membership requests where groupId = &#63; and statusId = &#63;.
	*
	* @param groupId the group ID
	* @param statusId the status ID
	* @return the number of matching membership requests
	*/
	public int countByG_S(long groupId, long statusId);

	/**
	* Returns all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statusId the status ID
	* @return the matching membership requests
	*/
	public java.util.List<MembershipRequest> findByG_U_S(long groupId,
		long userId, long statusId);

	/**
	* Returns a range of all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statusId the status ID
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @return the range of matching membership requests
	*/
	public java.util.List<MembershipRequest> findByG_U_S(long groupId,
		long userId, long statusId, int start, int end);

	/**
	* Returns an ordered range of all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statusId the status ID
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching membership requests
	*/
	public java.util.List<MembershipRequest> findByG_U_S(long groupId,
		long userId, long statusId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator);

	/**
	* Returns an ordered range of all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statusId the status ID
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching membership requests
	*/
	public java.util.List<MembershipRequest> findByG_U_S(long groupId,
		long userId, long statusId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statusId the status ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching membership request
	* @throws NoSuchMembershipRequestException if a matching membership request could not be found
	*/
	public MembershipRequest findByG_U_S_First(long groupId, long userId,
		long statusId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException;

	/**
	* Returns the first membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statusId the status ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching membership request, or <code>null</code> if a matching membership request could not be found
	*/
	public MembershipRequest fetchByG_U_S_First(long groupId, long userId,
		long statusId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator);

	/**
	* Returns the last membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statusId the status ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching membership request
	* @throws NoSuchMembershipRequestException if a matching membership request could not be found
	*/
	public MembershipRequest findByG_U_S_Last(long groupId, long userId,
		long statusId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException;

	/**
	* Returns the last membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statusId the status ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching membership request, or <code>null</code> if a matching membership request could not be found
	*/
	public MembershipRequest fetchByG_U_S_Last(long groupId, long userId,
		long statusId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator);

	/**
	* Returns the membership requests before and after the current membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	*
	* @param membershipRequestId the primary key of the current membership request
	* @param groupId the group ID
	* @param userId the user ID
	* @param statusId the status ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next membership request
	* @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	*/
	public MembershipRequest[] findByG_U_S_PrevAndNext(
		long membershipRequestId, long groupId, long userId, long statusId,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException;

	/**
	* Removes all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statusId the status ID
	*/
	public void removeByG_U_S(long groupId, long userId, long statusId);

	/**
	* Returns the number of membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statusId the status ID
	* @return the number of matching membership requests
	*/
	public int countByG_U_S(long groupId, long userId, long statusId);

	/**
	* Caches the membership request in the entity cache if it is enabled.
	*
	* @param membershipRequest the membership request
	*/
	public void cacheResult(MembershipRequest membershipRequest);

	/**
	* Caches the membership requests in the entity cache if it is enabled.
	*
	* @param membershipRequests the membership requests
	*/
	public void cacheResult(
		java.util.List<MembershipRequest> membershipRequests);

	/**
	* Creates a new membership request with the primary key. Does not add the membership request to the database.
	*
	* @param membershipRequestId the primary key for the new membership request
	* @return the new membership request
	*/
	public MembershipRequest create(long membershipRequestId);

	/**
	* Removes the membership request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param membershipRequestId the primary key of the membership request
	* @return the membership request that was removed
	* @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	*/
	public MembershipRequest remove(long membershipRequestId)
		throws NoSuchMembershipRequestException;

	public MembershipRequest updateImpl(MembershipRequest membershipRequest);

	/**
	* Returns the membership request with the primary key or throws a {@link NoSuchMembershipRequestException} if it could not be found.
	*
	* @param membershipRequestId the primary key of the membership request
	* @return the membership request
	* @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	*/
	public MembershipRequest findByPrimaryKey(long membershipRequestId)
		throws NoSuchMembershipRequestException;

	/**
	* Returns the membership request with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param membershipRequestId the primary key of the membership request
	* @return the membership request, or <code>null</code> if a membership request with the primary key could not be found
	*/
	public MembershipRequest fetchByPrimaryKey(long membershipRequestId);

	@Override
	public java.util.Map<java.io.Serializable, MembershipRequest> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the membership requests.
	*
	* @return the membership requests
	*/
	public java.util.List<MembershipRequest> findAll();

	/**
	* Returns a range of all the membership requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @return the range of membership requests
	*/
	public java.util.List<MembershipRequest> findAll(int start, int end);

	/**
	* Returns an ordered range of all the membership requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of membership requests
	*/
	public java.util.List<MembershipRequest> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator);

	/**
	* Returns an ordered range of all the membership requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of membership requests
	* @param end the upper bound of the range of membership requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of membership requests
	*/
	public java.util.List<MembershipRequest> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MembershipRequest> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the membership requests from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of membership requests.
	*
	* @return the number of membership requests
	*/
	public int countAll();
}