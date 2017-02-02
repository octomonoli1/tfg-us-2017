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

import com.liferay.portal.kernel.exception.NoSuchUserGroupRoleException;
import com.liferay.portal.kernel.model.UserGroupRole;

/**
 * The persistence interface for the user group role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.persistence.impl.UserGroupRolePersistenceImpl
 * @see UserGroupRoleUtil
 * @generated
 */
@ProviderType
public interface UserGroupRolePersistence extends BasePersistence<UserGroupRole> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserGroupRoleUtil} to access the user group role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the user group roles where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching user group roles
	*/
	public java.util.List<UserGroupRole> findByUserId(long userId);

	/**
	* Returns a range of all the user group roles where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @return the range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByUserId(long userId, int start,
		int end);

	/**
	* Returns an ordered range of all the user group roles where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns an ordered range of all the user group roles where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user group role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role
	* @throws NoSuchUserGroupRoleException if a matching user group role could not be found
	*/
	public UserGroupRole findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Returns the first user group role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role, or <code>null</code> if a matching user group role could not be found
	*/
	public UserGroupRole fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns the last user group role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role
	* @throws NoSuchUserGroupRoleException if a matching user group role could not be found
	*/
	public UserGroupRole findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Returns the last user group role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role, or <code>null</code> if a matching user group role could not be found
	*/
	public UserGroupRole fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns the user group roles before and after the current user group role in the ordered set where userId = &#63;.
	*
	* @param userGroupRolePK the primary key of the current user group role
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group role
	* @throws NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	*/
	public UserGroupRole[] findByUserId_PrevAndNext(
		UserGroupRolePK userGroupRolePK, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Removes all the user group roles where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of user group roles where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user group roles
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the user group roles where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching user group roles
	*/
	public java.util.List<UserGroupRole> findByGroupId(long groupId);

	/**
	* Returns a range of all the user group roles where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @return the range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the user group roles where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns an ordered range of all the user group roles where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user group role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role
	* @throws NoSuchUserGroupRoleException if a matching user group role could not be found
	*/
	public UserGroupRole findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Returns the first user group role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role, or <code>null</code> if a matching user group role could not be found
	*/
	public UserGroupRole fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns the last user group role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role
	* @throws NoSuchUserGroupRoleException if a matching user group role could not be found
	*/
	public UserGroupRole findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Returns the last user group role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role, or <code>null</code> if a matching user group role could not be found
	*/
	public UserGroupRole fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns the user group roles before and after the current user group role in the ordered set where groupId = &#63;.
	*
	* @param userGroupRolePK the primary key of the current user group role
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group role
	* @throws NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	*/
	public UserGroupRole[] findByGroupId_PrevAndNext(
		UserGroupRolePK userGroupRolePK, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Removes all the user group roles where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of user group roles where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching user group roles
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the user group roles where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the matching user group roles
	*/
	public java.util.List<UserGroupRole> findByRoleId(long roleId);

	/**
	* Returns a range of all the user group roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @return the range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByRoleId(long roleId, int start,
		int end);

	/**
	* Returns an ordered range of all the user group roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByRoleId(long roleId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns an ordered range of all the user group roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByRoleId(long roleId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user group role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role
	* @throws NoSuchUserGroupRoleException if a matching user group role could not be found
	*/
	public UserGroupRole findByRoleId_First(long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Returns the first user group role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role, or <code>null</code> if a matching user group role could not be found
	*/
	public UserGroupRole fetchByRoleId_First(long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns the last user group role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role
	* @throws NoSuchUserGroupRoleException if a matching user group role could not be found
	*/
	public UserGroupRole findByRoleId_Last(long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Returns the last user group role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role, or <code>null</code> if a matching user group role could not be found
	*/
	public UserGroupRole fetchByRoleId_Last(long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns the user group roles before and after the current user group role in the ordered set where roleId = &#63;.
	*
	* @param userGroupRolePK the primary key of the current user group role
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group role
	* @throws NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	*/
	public UserGroupRole[] findByRoleId_PrevAndNext(
		UserGroupRolePK userGroupRolePK, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Removes all the user group roles where roleId = &#63; from the database.
	*
	* @param roleId the role ID
	*/
	public void removeByRoleId(long roleId);

	/**
	* Returns the number of user group roles where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the number of matching user group roles
	*/
	public int countByRoleId(long roleId);

	/**
	* Returns all the user group roles where userId = &#63; and groupId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @return the matching user group roles
	*/
	public java.util.List<UserGroupRole> findByU_G(long userId, long groupId);

	/**
	* Returns a range of all the user group roles where userId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @return the range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByU_G(long userId, long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the user group roles where userId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByU_G(long userId, long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns an ordered range of all the user group roles where userId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByU_G(long userId, long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user group role in the ordered set where userId = &#63; and groupId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role
	* @throws NoSuchUserGroupRoleException if a matching user group role could not be found
	*/
	public UserGroupRole findByU_G_First(long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Returns the first user group role in the ordered set where userId = &#63; and groupId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role, or <code>null</code> if a matching user group role could not be found
	*/
	public UserGroupRole fetchByU_G_First(long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns the last user group role in the ordered set where userId = &#63; and groupId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role
	* @throws NoSuchUserGroupRoleException if a matching user group role could not be found
	*/
	public UserGroupRole findByU_G_Last(long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Returns the last user group role in the ordered set where userId = &#63; and groupId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role, or <code>null</code> if a matching user group role could not be found
	*/
	public UserGroupRole fetchByU_G_Last(long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns the user group roles before and after the current user group role in the ordered set where userId = &#63; and groupId = &#63;.
	*
	* @param userGroupRolePK the primary key of the current user group role
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group role
	* @throws NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	*/
	public UserGroupRole[] findByU_G_PrevAndNext(
		UserGroupRolePK userGroupRolePK, long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Removes all the user group roles where userId = &#63; and groupId = &#63; from the database.
	*
	* @param userId the user ID
	* @param groupId the group ID
	*/
	public void removeByU_G(long userId, long groupId);

	/**
	* Returns the number of user group roles where userId = &#63; and groupId = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @return the number of matching user group roles
	*/
	public int countByU_G(long userId, long groupId);

	/**
	* Returns all the user group roles where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @return the matching user group roles
	*/
	public java.util.List<UserGroupRole> findByG_R(long groupId, long roleId);

	/**
	* Returns a range of all the user group roles where groupId = &#63; and roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @return the range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByG_R(long groupId, long roleId,
		int start, int end);

	/**
	* Returns an ordered range of all the user group roles where groupId = &#63; and roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByG_R(long groupId, long roleId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns an ordered range of all the user group roles where groupId = &#63; and roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user group roles
	*/
	public java.util.List<UserGroupRole> findByG_R(long groupId, long roleId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role
	* @throws NoSuchUserGroupRoleException if a matching user group role could not be found
	*/
	public UserGroupRole findByG_R_First(long groupId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Returns the first user group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group role, or <code>null</code> if a matching user group role could not be found
	*/
	public UserGroupRole fetchByG_R_First(long groupId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns the last user group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role
	* @throws NoSuchUserGroupRoleException if a matching user group role could not be found
	*/
	public UserGroupRole findByG_R_Last(long groupId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Returns the last user group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group role, or <code>null</code> if a matching user group role could not be found
	*/
	public UserGroupRole fetchByG_R_Last(long groupId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns the user group roles before and after the current user group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param userGroupRolePK the primary key of the current user group role
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group role
	* @throws NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	*/
	public UserGroupRole[] findByG_R_PrevAndNext(
		UserGroupRolePK userGroupRolePK, long groupId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator)
		throws NoSuchUserGroupRoleException;

	/**
	* Removes all the user group roles where groupId = &#63; and roleId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	*/
	public void removeByG_R(long groupId, long roleId);

	/**
	* Returns the number of user group roles where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @return the number of matching user group roles
	*/
	public int countByG_R(long groupId, long roleId);

	/**
	* Caches the user group role in the entity cache if it is enabled.
	*
	* @param userGroupRole the user group role
	*/
	public void cacheResult(UserGroupRole userGroupRole);

	/**
	* Caches the user group roles in the entity cache if it is enabled.
	*
	* @param userGroupRoles the user group roles
	*/
	public void cacheResult(java.util.List<UserGroupRole> userGroupRoles);

	/**
	* Creates a new user group role with the primary key. Does not add the user group role to the database.
	*
	* @param userGroupRolePK the primary key for the new user group role
	* @return the new user group role
	*/
	public UserGroupRole create(UserGroupRolePK userGroupRolePK);

	/**
	* Removes the user group role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userGroupRolePK the primary key of the user group role
	* @return the user group role that was removed
	* @throws NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	*/
	public UserGroupRole remove(UserGroupRolePK userGroupRolePK)
		throws NoSuchUserGroupRoleException;

	public UserGroupRole updateImpl(UserGroupRole userGroupRole);

	/**
	* Returns the user group role with the primary key or throws a {@link NoSuchUserGroupRoleException} if it could not be found.
	*
	* @param userGroupRolePK the primary key of the user group role
	* @return the user group role
	* @throws NoSuchUserGroupRoleException if a user group role with the primary key could not be found
	*/
	public UserGroupRole findByPrimaryKey(UserGroupRolePK userGroupRolePK)
		throws NoSuchUserGroupRoleException;

	/**
	* Returns the user group role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userGroupRolePK the primary key of the user group role
	* @return the user group role, or <code>null</code> if a user group role with the primary key could not be found
	*/
	public UserGroupRole fetchByPrimaryKey(UserGroupRolePK userGroupRolePK);

	@Override
	public java.util.Map<java.io.Serializable, UserGroupRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the user group roles.
	*
	* @return the user group roles
	*/
	public java.util.List<UserGroupRole> findAll();

	/**
	* Returns a range of all the user group roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @return the range of user group roles
	*/
	public java.util.List<UserGroupRole> findAll(int start, int end);

	/**
	* Returns an ordered range of all the user group roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user group roles
	*/
	public java.util.List<UserGroupRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator);

	/**
	* Returns an ordered range of all the user group roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user group roles
	* @param end the upper bound of the range of user group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user group roles
	*/
	public java.util.List<UserGroupRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserGroupRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the user group roles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of user group roles.
	*
	* @return the number of user group roles
	*/
	public int countAll();
}