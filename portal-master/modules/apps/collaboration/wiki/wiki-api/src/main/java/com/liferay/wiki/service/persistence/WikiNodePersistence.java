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

package com.liferay.wiki.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.wiki.exception.NoSuchNodeException;
import com.liferay.wiki.model.WikiNode;

/**
 * The persistence interface for the wiki node service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.wiki.service.persistence.impl.WikiNodePersistenceImpl
 * @see WikiNodeUtil
 * @generated
 */
@ProviderType
public interface WikiNodePersistence extends BasePersistence<WikiNode> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WikiNodeUtil} to access the wiki node persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the wiki nodes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching wiki nodes
	*/
	public java.util.List<WikiNode> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the wiki nodes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @return the range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the wiki nodes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns an ordered range of all the wiki nodes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first wiki node in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns the first wiki node in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the last wiki node in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns the last wiki node in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the wiki nodes before and after the current wiki node in the ordered set where uuid = &#63;.
	*
	* @param nodeId the primary key of the current wiki node
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next wiki node
	* @throws NoSuchNodeException if a wiki node with the primary key could not be found
	*/
	public WikiNode[] findByUuid_PrevAndNext(long nodeId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Removes all the wiki nodes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of wiki nodes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching wiki nodes
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the wiki node where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchNodeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchNodeException;

	/**
	* Returns the wiki node where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the wiki node where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the wiki node where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the wiki node that was removed
	*/
	public WikiNode removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchNodeException;

	/**
	* Returns the number of wiki nodes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching wiki nodes
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the wiki nodes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching wiki nodes
	*/
	public java.util.List<WikiNode> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the wiki nodes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @return the range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the wiki nodes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns an ordered range of all the wiki nodes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first wiki node in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns the first wiki node in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the last wiki node in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns the last wiki node in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the wiki nodes before and after the current wiki node in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param nodeId the primary key of the current wiki node
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next wiki node
	* @throws NoSuchNodeException if a wiki node with the primary key could not be found
	*/
	public WikiNode[] findByUuid_C_PrevAndNext(long nodeId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Removes all the wiki nodes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of wiki nodes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching wiki nodes
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the wiki nodes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching wiki nodes
	*/
	public java.util.List<WikiNode> findByGroupId(long groupId);

	/**
	* Returns a range of all the wiki nodes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @return the range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the wiki nodes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns an ordered range of all the wiki nodes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first wiki node in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns the first wiki node in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the last wiki node in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns the last wiki node in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the wiki nodes before and after the current wiki node in the ordered set where groupId = &#63;.
	*
	* @param nodeId the primary key of the current wiki node
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next wiki node
	* @throws NoSuchNodeException if a wiki node with the primary key could not be found
	*/
	public WikiNode[] findByGroupId_PrevAndNext(long nodeId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns all the wiki nodes that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching wiki nodes that the user has permission to view
	*/
	public java.util.List<WikiNode> filterFindByGroupId(long groupId);

	/**
	* Returns a range of all the wiki nodes that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @return the range of matching wiki nodes that the user has permission to view
	*/
	public java.util.List<WikiNode> filterFindByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the wiki nodes that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching wiki nodes that the user has permission to view
	*/
	public java.util.List<WikiNode> filterFindByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the wiki nodes before and after the current wiki node in the ordered set of wiki nodes that the user has permission to view where groupId = &#63;.
	*
	* @param nodeId the primary key of the current wiki node
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next wiki node
	* @throws NoSuchNodeException if a wiki node with the primary key could not be found
	*/
	public WikiNode[] filterFindByGroupId_PrevAndNext(long nodeId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Removes all the wiki nodes where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of wiki nodes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching wiki nodes
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of wiki nodes that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching wiki nodes that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns all the wiki nodes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching wiki nodes
	*/
	public java.util.List<WikiNode> findByCompanyId(long companyId);

	/**
	* Returns a range of all the wiki nodes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @return the range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByCompanyId(long companyId, int start,
		int end);

	/**
	* Returns an ordered range of all the wiki nodes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns an ordered range of all the wiki nodes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first wiki node in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns the first wiki node in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the last wiki node in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns the last wiki node in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the wiki nodes before and after the current wiki node in the ordered set where companyId = &#63;.
	*
	* @param nodeId the primary key of the current wiki node
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next wiki node
	* @throws NoSuchNodeException if a wiki node with the primary key could not be found
	*/
	public WikiNode[] findByCompanyId_PrevAndNext(long nodeId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Removes all the wiki nodes where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of wiki nodes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching wiki nodes
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns the wiki node where groupId = &#63; and name = &#63; or throws a {@link NoSuchNodeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByG_N(long groupId, java.lang.String name)
		throws NoSuchNodeException;

	/**
	* Returns the wiki node where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByG_N(long groupId, java.lang.String name);

	/**
	* Returns the wiki node where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByG_N(long groupId, java.lang.String name,
		boolean retrieveFromCache);

	/**
	* Removes the wiki node where groupId = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the wiki node that was removed
	*/
	public WikiNode removeByG_N(long groupId, java.lang.String name)
		throws NoSuchNodeException;

	/**
	* Returns the number of wiki nodes where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching wiki nodes
	*/
	public int countByG_N(long groupId, java.lang.String name);

	/**
	* Returns all the wiki nodes where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching wiki nodes
	*/
	public java.util.List<WikiNode> findByG_S(long groupId, int status);

	/**
	* Returns a range of all the wiki nodes where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @return the range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByG_S(long groupId, int status,
		int start, int end);

	/**
	* Returns an ordered range of all the wiki nodes where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByG_S(long groupId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns an ordered range of all the wiki nodes where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByG_S(long groupId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first wiki node in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByG_S_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns the first wiki node in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByG_S_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the last wiki node in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByG_S_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns the last wiki node in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByG_S_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the wiki nodes before and after the current wiki node in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param nodeId the primary key of the current wiki node
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next wiki node
	* @throws NoSuchNodeException if a wiki node with the primary key could not be found
	*/
	public WikiNode[] findByG_S_PrevAndNext(long nodeId, long groupId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns all the wiki nodes that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching wiki nodes that the user has permission to view
	*/
	public java.util.List<WikiNode> filterFindByG_S(long groupId, int status);

	/**
	* Returns a range of all the wiki nodes that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @return the range of matching wiki nodes that the user has permission to view
	*/
	public java.util.List<WikiNode> filterFindByG_S(long groupId, int status,
		int start, int end);

	/**
	* Returns an ordered range of all the wiki nodes that the user has permissions to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching wiki nodes that the user has permission to view
	*/
	public java.util.List<WikiNode> filterFindByG_S(long groupId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the wiki nodes before and after the current wiki node in the ordered set of wiki nodes that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param nodeId the primary key of the current wiki node
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next wiki node
	* @throws NoSuchNodeException if a wiki node with the primary key could not be found
	*/
	public WikiNode[] filterFindByG_S_PrevAndNext(long nodeId, long groupId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Removes all the wiki nodes where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	*/
	public void removeByG_S(long groupId, int status);

	/**
	* Returns the number of wiki nodes where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching wiki nodes
	*/
	public int countByG_S(long groupId, int status);

	/**
	* Returns the number of wiki nodes that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching wiki nodes that the user has permission to view
	*/
	public int filterCountByG_S(long groupId, int status);

	/**
	* Returns all the wiki nodes where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the matching wiki nodes
	*/
	public java.util.List<WikiNode> findByC_S(long companyId, int status);

	/**
	* Returns a range of all the wiki nodes where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @return the range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByC_S(long companyId, int status,
		int start, int end);

	/**
	* Returns an ordered range of all the wiki nodes where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByC_S(long companyId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns an ordered range of all the wiki nodes where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching wiki nodes
	*/
	public java.util.List<WikiNode> findByC_S(long companyId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first wiki node in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByC_S_First(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns the first wiki node in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByC_S_First(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the last wiki node in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wiki node
	* @throws NoSuchNodeException if a matching wiki node could not be found
	*/
	public WikiNode findByC_S_Last(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Returns the last wiki node in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wiki node, or <code>null</code> if a matching wiki node could not be found
	*/
	public WikiNode fetchByC_S_Last(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns the wiki nodes before and after the current wiki node in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param nodeId the primary key of the current wiki node
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next wiki node
	* @throws NoSuchNodeException if a wiki node with the primary key could not be found
	*/
	public WikiNode[] findByC_S_PrevAndNext(long nodeId, long companyId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator)
		throws NoSuchNodeException;

	/**
	* Removes all the wiki nodes where companyId = &#63; and status = &#63; from the database.
	*
	* @param companyId the company ID
	* @param status the status
	*/
	public void removeByC_S(long companyId, int status);

	/**
	* Returns the number of wiki nodes where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the number of matching wiki nodes
	*/
	public int countByC_S(long companyId, int status);

	/**
	* Caches the wiki node in the entity cache if it is enabled.
	*
	* @param wikiNode the wiki node
	*/
	public void cacheResult(WikiNode wikiNode);

	/**
	* Caches the wiki nodes in the entity cache if it is enabled.
	*
	* @param wikiNodes the wiki nodes
	*/
	public void cacheResult(java.util.List<WikiNode> wikiNodes);

	/**
	* Creates a new wiki node with the primary key. Does not add the wiki node to the database.
	*
	* @param nodeId the primary key for the new wiki node
	* @return the new wiki node
	*/
	public WikiNode create(long nodeId);

	/**
	* Removes the wiki node with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nodeId the primary key of the wiki node
	* @return the wiki node that was removed
	* @throws NoSuchNodeException if a wiki node with the primary key could not be found
	*/
	public WikiNode remove(long nodeId) throws NoSuchNodeException;

	public WikiNode updateImpl(WikiNode wikiNode);

	/**
	* Returns the wiki node with the primary key or throws a {@link NoSuchNodeException} if it could not be found.
	*
	* @param nodeId the primary key of the wiki node
	* @return the wiki node
	* @throws NoSuchNodeException if a wiki node with the primary key could not be found
	*/
	public WikiNode findByPrimaryKey(long nodeId) throws NoSuchNodeException;

	/**
	* Returns the wiki node with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nodeId the primary key of the wiki node
	* @return the wiki node, or <code>null</code> if a wiki node with the primary key could not be found
	*/
	public WikiNode fetchByPrimaryKey(long nodeId);

	@Override
	public java.util.Map<java.io.Serializable, WikiNode> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the wiki nodes.
	*
	* @return the wiki nodes
	*/
	public java.util.List<WikiNode> findAll();

	/**
	* Returns a range of all the wiki nodes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @return the range of wiki nodes
	*/
	public java.util.List<WikiNode> findAll(int start, int end);

	/**
	* Returns an ordered range of all the wiki nodes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of wiki nodes
	*/
	public java.util.List<WikiNode> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator);

	/**
	* Returns an ordered range of all the wiki nodes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WikiNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of wiki nodes
	* @param end the upper bound of the range of wiki nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of wiki nodes
	*/
	public java.util.List<WikiNode> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WikiNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the wiki nodes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of wiki nodes.
	*
	* @return the number of wiki nodes
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}