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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.model.UserGroupGroupRole;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the user group group role service. This utility wraps {@link com.liferay.portal.service.persistence.impl.UserGroupGroupRolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupGroupRolePersistence
 * @see com.liferay.portal.service.persistence.impl.UserGroupGroupRolePersistenceImpl
 * @generated
 */
@ProviderType
public class UserGroupGroupRoleUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(UserGroupGroupRole userGroupGroupRole) {
		getPersistence().clearCache(userGroupGroupRole);
	}

	/**
	 * @see BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserGroupGroupRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserGroupGroupRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserGroupGroupRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserGroupGroupRole update(
		UserGroupGroupRole userGroupGroupRole) {
		return getPersistence().update(userGroupGroupRole);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserGroupGroupRole update(
		UserGroupGroupRole userGroupGroupRole, ServiceContext serviceContext) {
		return getPersistence().update(userGroupGroupRole, serviceContext);
	}

	/**
	* Returns all the user group group roles where userGroupId = &#63;.
	*
	* @param userGroupId the user group ID
	* @return the matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByUserGroupId(long userGroupId) {
		return getPersistence().findByUserGroupId(userGroupId);
	}

	/**
	* Returns a range of all the user group group roles where userGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userGroupId the user group ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @return the range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByUserGroupId(long userGroupId,
		int start, int end) {
		return getPersistence().findByUserGroupId(userGroupId, start, end);
	}

	/**
	* Returns an ordered range of all the user group group roles where userGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userGroupId the user group ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByUserGroupId(long userGroupId,
		int start, int end,
		OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence()
				   .findByUserGroupId(userGroupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user group group roles where userGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userGroupId the user group ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByUserGroupId(long userGroupId,
		int start, int end,
		OrderByComparator<UserGroupGroupRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserGroupId(userGroupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first user group group role in the ordered set where userGroupId = &#63;.
	*
	* @param userGroupId the user group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group group role
	* @throws NoSuchUserGroupGroupRoleException if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole findByUserGroupId_First(long userGroupId,
		OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence()
				   .findByUserGroupId_First(userGroupId, orderByComparator);
	}

	/**
	* Returns the first user group group role in the ordered set where userGroupId = &#63;.
	*
	* @param userGroupId the user group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group group role, or <code>null</code> if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole fetchByUserGroupId_First(
		long userGroupId,
		OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence()
				   .fetchByUserGroupId_First(userGroupId, orderByComparator);
	}

	/**
	* Returns the last user group group role in the ordered set where userGroupId = &#63;.
	*
	* @param userGroupId the user group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group group role
	* @throws NoSuchUserGroupGroupRoleException if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole findByUserGroupId_Last(long userGroupId,
		OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence()
				   .findByUserGroupId_Last(userGroupId, orderByComparator);
	}

	/**
	* Returns the last user group group role in the ordered set where userGroupId = &#63;.
	*
	* @param userGroupId the user group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group group role, or <code>null</code> if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole fetchByUserGroupId_Last(long userGroupId,
		OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence()
				   .fetchByUserGroupId_Last(userGroupId, orderByComparator);
	}

	/**
	* Returns the user group group roles before and after the current user group group role in the ordered set where userGroupId = &#63;.
	*
	* @param userGroupGroupRolePK the primary key of the current user group group role
	* @param userGroupId the user group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group group role
	* @throws NoSuchUserGroupGroupRoleException if a user group group role with the primary key could not be found
	*/
	public static UserGroupGroupRole[] findByUserGroupId_PrevAndNext(
		UserGroupGroupRolePK userGroupGroupRolePK, long userGroupId,
		OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence()
				   .findByUserGroupId_PrevAndNext(userGroupGroupRolePK,
			userGroupId, orderByComparator);
	}

	/**
	* Removes all the user group group roles where userGroupId = &#63; from the database.
	*
	* @param userGroupId the user group ID
	*/
	public static void removeByUserGroupId(long userGroupId) {
		getPersistence().removeByUserGroupId(userGroupId);
	}

	/**
	* Returns the number of user group group roles where userGroupId = &#63;.
	*
	* @param userGroupId the user group ID
	* @return the number of matching user group group roles
	*/
	public static int countByUserGroupId(long userGroupId) {
		return getPersistence().countByUserGroupId(userGroupId);
	}

	/**
	* Returns all the user group group roles where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the user group group roles where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @return the range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByGroupId(long groupId,
		int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the user group group roles where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user group group roles where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<UserGroupGroupRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user group group role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group group role
	* @throws NoSuchUserGroupGroupRoleException if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole findByGroupId_First(long groupId,
		OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first user group group role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group group role, or <code>null</code> if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole fetchByGroupId_First(long groupId,
		OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last user group group role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group group role
	* @throws NoSuchUserGroupGroupRoleException if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole findByGroupId_Last(long groupId,
		OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last user group group role in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group group role, or <code>null</code> if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole fetchByGroupId_Last(long groupId,
		OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the user group group roles before and after the current user group group role in the ordered set where groupId = &#63;.
	*
	* @param userGroupGroupRolePK the primary key of the current user group group role
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group group role
	* @throws NoSuchUserGroupGroupRoleException if a user group group role with the primary key could not be found
	*/
	public static UserGroupGroupRole[] findByGroupId_PrevAndNext(
		UserGroupGroupRolePK userGroupGroupRolePK, long groupId,
		OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(userGroupGroupRolePK, groupId,
			orderByComparator);
	}

	/**
	* Removes all the user group group roles where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of user group group roles where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching user group group roles
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the user group group roles where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByRoleId(long roleId) {
		return getPersistence().findByRoleId(roleId);
	}

	/**
	* Returns a range of all the user group group roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @return the range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByRoleId(long roleId, int start,
		int end) {
		return getPersistence().findByRoleId(roleId, start, end);
	}

	/**
	* Returns an ordered range of all the user group group roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByRoleId(long roleId, int start,
		int end, OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence()
				   .findByRoleId(roleId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user group group roles where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByRoleId(long roleId, int start,
		int end, OrderByComparator<UserGroupGroupRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRoleId(roleId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user group group role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group group role
	* @throws NoSuchUserGroupGroupRoleException if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole findByRoleId_First(long roleId,
		OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence().findByRoleId_First(roleId, orderByComparator);
	}

	/**
	* Returns the first user group group role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group group role, or <code>null</code> if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole fetchByRoleId_First(long roleId,
		OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence().fetchByRoleId_First(roleId, orderByComparator);
	}

	/**
	* Returns the last user group group role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group group role
	* @throws NoSuchUserGroupGroupRoleException if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole findByRoleId_Last(long roleId,
		OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence().findByRoleId_Last(roleId, orderByComparator);
	}

	/**
	* Returns the last user group group role in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group group role, or <code>null</code> if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole fetchByRoleId_Last(long roleId,
		OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence().fetchByRoleId_Last(roleId, orderByComparator);
	}

	/**
	* Returns the user group group roles before and after the current user group group role in the ordered set where roleId = &#63;.
	*
	* @param userGroupGroupRolePK the primary key of the current user group group role
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group group role
	* @throws NoSuchUserGroupGroupRoleException if a user group group role with the primary key could not be found
	*/
	public static UserGroupGroupRole[] findByRoleId_PrevAndNext(
		UserGroupGroupRolePK userGroupGroupRolePK, long roleId,
		OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence()
				   .findByRoleId_PrevAndNext(userGroupGroupRolePK, roleId,
			orderByComparator);
	}

	/**
	* Removes all the user group group roles where roleId = &#63; from the database.
	*
	* @param roleId the role ID
	*/
	public static void removeByRoleId(long roleId) {
		getPersistence().removeByRoleId(roleId);
	}

	/**
	* Returns the number of user group group roles where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the number of matching user group group roles
	*/
	public static int countByRoleId(long roleId) {
		return getPersistence().countByRoleId(roleId);
	}

	/**
	* Returns all the user group group roles where userGroupId = &#63; and groupId = &#63;.
	*
	* @param userGroupId the user group ID
	* @param groupId the group ID
	* @return the matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByU_G(long userGroupId,
		long groupId) {
		return getPersistence().findByU_G(userGroupId, groupId);
	}

	/**
	* Returns a range of all the user group group roles where userGroupId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userGroupId the user group ID
	* @param groupId the group ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @return the range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByU_G(long userGroupId,
		long groupId, int start, int end) {
		return getPersistence().findByU_G(userGroupId, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the user group group roles where userGroupId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userGroupId the user group ID
	* @param groupId the group ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByU_G(long userGroupId,
		long groupId, int start, int end,
		OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence()
				   .findByU_G(userGroupId, groupId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the user group group roles where userGroupId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userGroupId the user group ID
	* @param groupId the group ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByU_G(long userGroupId,
		long groupId, int start, int end,
		OrderByComparator<UserGroupGroupRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_G(userGroupId, groupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first user group group role in the ordered set where userGroupId = &#63; and groupId = &#63;.
	*
	* @param userGroupId the user group ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group group role
	* @throws NoSuchUserGroupGroupRoleException if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole findByU_G_First(long userGroupId,
		long groupId, OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence()
				   .findByU_G_First(userGroupId, groupId, orderByComparator);
	}

	/**
	* Returns the first user group group role in the ordered set where userGroupId = &#63; and groupId = &#63;.
	*
	* @param userGroupId the user group ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group group role, or <code>null</code> if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole fetchByU_G_First(long userGroupId,
		long groupId, OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence()
				   .fetchByU_G_First(userGroupId, groupId, orderByComparator);
	}

	/**
	* Returns the last user group group role in the ordered set where userGroupId = &#63; and groupId = &#63;.
	*
	* @param userGroupId the user group ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group group role
	* @throws NoSuchUserGroupGroupRoleException if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole findByU_G_Last(long userGroupId,
		long groupId, OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence()
				   .findByU_G_Last(userGroupId, groupId, orderByComparator);
	}

	/**
	* Returns the last user group group role in the ordered set where userGroupId = &#63; and groupId = &#63;.
	*
	* @param userGroupId the user group ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group group role, or <code>null</code> if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole fetchByU_G_Last(long userGroupId,
		long groupId, OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence()
				   .fetchByU_G_Last(userGroupId, groupId, orderByComparator);
	}

	/**
	* Returns the user group group roles before and after the current user group group role in the ordered set where userGroupId = &#63; and groupId = &#63;.
	*
	* @param userGroupGroupRolePK the primary key of the current user group group role
	* @param userGroupId the user group ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group group role
	* @throws NoSuchUserGroupGroupRoleException if a user group group role with the primary key could not be found
	*/
	public static UserGroupGroupRole[] findByU_G_PrevAndNext(
		UserGroupGroupRolePK userGroupGroupRolePK, long userGroupId,
		long groupId, OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence()
				   .findByU_G_PrevAndNext(userGroupGroupRolePK, userGroupId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the user group group roles where userGroupId = &#63; and groupId = &#63; from the database.
	*
	* @param userGroupId the user group ID
	* @param groupId the group ID
	*/
	public static void removeByU_G(long userGroupId, long groupId) {
		getPersistence().removeByU_G(userGroupId, groupId);
	}

	/**
	* Returns the number of user group group roles where userGroupId = &#63; and groupId = &#63;.
	*
	* @param userGroupId the user group ID
	* @param groupId the group ID
	* @return the number of matching user group group roles
	*/
	public static int countByU_G(long userGroupId, long groupId) {
		return getPersistence().countByU_G(userGroupId, groupId);
	}

	/**
	* Returns all the user group group roles where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @return the matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByG_R(long groupId, long roleId) {
		return getPersistence().findByG_R(groupId, roleId);
	}

	/**
	* Returns a range of all the user group group roles where groupId = &#63; and roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @return the range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByG_R(long groupId, long roleId,
		int start, int end) {
		return getPersistence().findByG_R(groupId, roleId, start, end);
	}

	/**
	* Returns an ordered range of all the user group group roles where groupId = &#63; and roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByG_R(long groupId, long roleId,
		int start, int end,
		OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence()
				   .findByG_R(groupId, roleId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user group group roles where groupId = &#63; and roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user group group roles
	*/
	public static List<UserGroupGroupRole> findByG_R(long groupId, long roleId,
		int start, int end,
		OrderByComparator<UserGroupGroupRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_R(groupId, roleId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user group group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group group role
	* @throws NoSuchUserGroupGroupRoleException if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole findByG_R_First(long groupId, long roleId,
		OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence()
				   .findByG_R_First(groupId, roleId, orderByComparator);
	}

	/**
	* Returns the first user group group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user group group role, or <code>null</code> if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole fetchByG_R_First(long groupId,
		long roleId, OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence()
				   .fetchByG_R_First(groupId, roleId, orderByComparator);
	}

	/**
	* Returns the last user group group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group group role
	* @throws NoSuchUserGroupGroupRoleException if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole findByG_R_Last(long groupId, long roleId,
		OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence()
				   .findByG_R_Last(groupId, roleId, orderByComparator);
	}

	/**
	* Returns the last user group group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user group group role, or <code>null</code> if a matching user group group role could not be found
	*/
	public static UserGroupGroupRole fetchByG_R_Last(long groupId, long roleId,
		OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence()
				   .fetchByG_R_Last(groupId, roleId, orderByComparator);
	}

	/**
	* Returns the user group group roles before and after the current user group group role in the ordered set where groupId = &#63; and roleId = &#63;.
	*
	* @param userGroupGroupRolePK the primary key of the current user group group role
	* @param groupId the group ID
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user group group role
	* @throws NoSuchUserGroupGroupRoleException if a user group group role with the primary key could not be found
	*/
	public static UserGroupGroupRole[] findByG_R_PrevAndNext(
		UserGroupGroupRolePK userGroupGroupRolePK, long groupId, long roleId,
		OrderByComparator<UserGroupGroupRole> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence()
				   .findByG_R_PrevAndNext(userGroupGroupRolePK, groupId,
			roleId, orderByComparator);
	}

	/**
	* Removes all the user group group roles where groupId = &#63; and roleId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	*/
	public static void removeByG_R(long groupId, long roleId) {
		getPersistence().removeByG_R(groupId, roleId);
	}

	/**
	* Returns the number of user group group roles where groupId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param roleId the role ID
	* @return the number of matching user group group roles
	*/
	public static int countByG_R(long groupId, long roleId) {
		return getPersistence().countByG_R(groupId, roleId);
	}

	/**
	* Caches the user group group role in the entity cache if it is enabled.
	*
	* @param userGroupGroupRole the user group group role
	*/
	public static void cacheResult(UserGroupGroupRole userGroupGroupRole) {
		getPersistence().cacheResult(userGroupGroupRole);
	}

	/**
	* Caches the user group group roles in the entity cache if it is enabled.
	*
	* @param userGroupGroupRoles the user group group roles
	*/
	public static void cacheResult(List<UserGroupGroupRole> userGroupGroupRoles) {
		getPersistence().cacheResult(userGroupGroupRoles);
	}

	/**
	* Creates a new user group group role with the primary key. Does not add the user group group role to the database.
	*
	* @param userGroupGroupRolePK the primary key for the new user group group role
	* @return the new user group group role
	*/
	public static UserGroupGroupRole create(
		UserGroupGroupRolePK userGroupGroupRolePK) {
		return getPersistence().create(userGroupGroupRolePK);
	}

	/**
	* Removes the user group group role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userGroupGroupRolePK the primary key of the user group group role
	* @return the user group group role that was removed
	* @throws NoSuchUserGroupGroupRoleException if a user group group role with the primary key could not be found
	*/
	public static UserGroupGroupRole remove(
		UserGroupGroupRolePK userGroupGroupRolePK)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence().remove(userGroupGroupRolePK);
	}

	public static UserGroupGroupRole updateImpl(
		UserGroupGroupRole userGroupGroupRole) {
		return getPersistence().updateImpl(userGroupGroupRole);
	}

	/**
	* Returns the user group group role with the primary key or throws a {@link NoSuchUserGroupGroupRoleException} if it could not be found.
	*
	* @param userGroupGroupRolePK the primary key of the user group group role
	* @return the user group group role
	* @throws NoSuchUserGroupGroupRoleException if a user group group role with the primary key could not be found
	*/
	public static UserGroupGroupRole findByPrimaryKey(
		UserGroupGroupRolePK userGroupGroupRolePK)
		throws com.liferay.portal.kernel.exception.NoSuchUserGroupGroupRoleException {
		return getPersistence().findByPrimaryKey(userGroupGroupRolePK);
	}

	/**
	* Returns the user group group role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userGroupGroupRolePK the primary key of the user group group role
	* @return the user group group role, or <code>null</code> if a user group group role with the primary key could not be found
	*/
	public static UserGroupGroupRole fetchByPrimaryKey(
		UserGroupGroupRolePK userGroupGroupRolePK) {
		return getPersistence().fetchByPrimaryKey(userGroupGroupRolePK);
	}

	public static java.util.Map<java.io.Serializable, UserGroupGroupRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the user group group roles.
	*
	* @return the user group group roles
	*/
	public static List<UserGroupGroupRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the user group group roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @return the range of user group group roles
	*/
	public static List<UserGroupGroupRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the user group group roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user group group roles
	*/
	public static List<UserGroupGroupRole> findAll(int start, int end,
		OrderByComparator<UserGroupGroupRole> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user group group roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user group group roles
	* @param end the upper bound of the range of user group group roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user group group roles
	*/
	public static List<UserGroupGroupRole> findAll(int start, int end,
		OrderByComparator<UserGroupGroupRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the user group group roles from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user group group roles.
	*
	* @return the number of user group group roles
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UserGroupGroupRolePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (UserGroupGroupRolePersistence)PortalBeanLocatorUtil.locate(UserGroupGroupRolePersistence.class.getName());

			ReferenceRegistry.registerReference(UserGroupGroupRoleUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static UserGroupGroupRolePersistence _persistence;
}