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
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the resource permission service. This utility wraps {@link com.liferay.portal.service.persistence.impl.ResourcePermissionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourcePermissionPersistence
 * @see com.liferay.portal.service.persistence.impl.ResourcePermissionPersistenceImpl
 * @generated
 */
@ProviderType
public class ResourcePermissionUtil {
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
	public static void clearCache(ResourcePermission resourcePermission) {
		getPersistence().clearCache(resourcePermission);
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
	public static List<ResourcePermission> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ResourcePermission> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ResourcePermission> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ResourcePermission update(
		ResourcePermission resourcePermission) {
		return getPersistence().update(resourcePermission);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ResourcePermission update(
		ResourcePermission resourcePermission, ServiceContext serviceContext) {
		return getPersistence().update(resourcePermission, serviceContext);
	}

	/**
	* Returns all the resource permissions where name = &#63;.
	*
	* @param name the name
	* @return the matching resource permissions
	*/
	public static List<ResourcePermission> findByName(java.lang.String name) {
		return getPersistence().findByName(name);
	}

	/**
	* Returns a range of all the resource permissions where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @return the range of matching resource permissions
	*/
	public static List<ResourcePermission> findByName(java.lang.String name,
		int start, int end) {
		return getPersistence().findByName(name, start, end);
	}

	/**
	* Returns an ordered range of all the resource permissions where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByName(java.lang.String name,
		int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence().findByName(name, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource permissions where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByName(java.lang.String name,
		int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByName(name, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first resource permission in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByName_First(java.lang.String name,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence().findByName_First(name, orderByComparator);
	}

	/**
	* Returns the first resource permission in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByName_First(java.lang.String name,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence().fetchByName_First(name, orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByName_Last(java.lang.String name,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence().findByName_Last(name, orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByName_Last(java.lang.String name,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence().fetchByName_Last(name, orderByComparator);
	}

	/**
	* Returns the resource permissions before and after the current resource permission in the ordered set where name = &#63;.
	*
	* @param resourcePermissionId the primary key of the current resource permission
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource permission
	* @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	*/
	public static ResourcePermission[] findByName_PrevAndNext(
		long resourcePermissionId, java.lang.String name,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByName_PrevAndNext(resourcePermissionId, name,
			orderByComparator);
	}

	/**
	* Removes all the resource permissions where name = &#63; from the database.
	*
	* @param name the name
	*/
	public static void removeByName(java.lang.String name) {
		getPersistence().removeByName(name);
	}

	/**
	* Returns the number of resource permissions where name = &#63;.
	*
	* @param name the name
	* @return the number of matching resource permissions
	*/
	public static int countByName(java.lang.String name) {
		return getPersistence().countByName(name);
	}

	/**
	* Returns all the resource permissions where scope = &#63;.
	*
	* @param scope the scope
	* @return the matching resource permissions
	*/
	public static List<ResourcePermission> findByScope(int scope) {
		return getPersistence().findByScope(scope);
	}

	/**
	* Returns a range of all the resource permissions where scope = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scope the scope
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @return the range of matching resource permissions
	*/
	public static List<ResourcePermission> findByScope(int scope, int start,
		int end) {
		return getPersistence().findByScope(scope, start, end);
	}

	/**
	* Returns an ordered range of all the resource permissions where scope = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scope the scope
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByScope(int scope, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence().findByScope(scope, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource permissions where scope = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scope the scope
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByScope(int scope, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByScope(scope, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first resource permission in the ordered set where scope = &#63;.
	*
	* @param scope the scope
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByScope_First(int scope,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence().findByScope_First(scope, orderByComparator);
	}

	/**
	* Returns the first resource permission in the ordered set where scope = &#63;.
	*
	* @param scope the scope
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByScope_First(int scope,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence().fetchByScope_First(scope, orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where scope = &#63;.
	*
	* @param scope the scope
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByScope_Last(int scope,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence().findByScope_Last(scope, orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where scope = &#63;.
	*
	* @param scope the scope
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByScope_Last(int scope,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence().fetchByScope_Last(scope, orderByComparator);
	}

	/**
	* Returns the resource permissions before and after the current resource permission in the ordered set where scope = &#63;.
	*
	* @param resourcePermissionId the primary key of the current resource permission
	* @param scope the scope
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource permission
	* @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	*/
	public static ResourcePermission[] findByScope_PrevAndNext(
		long resourcePermissionId, int scope,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByScope_PrevAndNext(resourcePermissionId, scope,
			orderByComparator);
	}

	/**
	* Returns all the resource permissions where scope = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scopes the scopes
	* @return the matching resource permissions
	*/
	public static List<ResourcePermission> findByScope(int[] scopes) {
		return getPersistence().findByScope(scopes);
	}

	/**
	* Returns a range of all the resource permissions where scope = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scopes the scopes
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @return the range of matching resource permissions
	*/
	public static List<ResourcePermission> findByScope(int[] scopes, int start,
		int end) {
		return getPersistence().findByScope(scopes, start, end);
	}

	/**
	* Returns an ordered range of all the resource permissions where scope = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scopes the scopes
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByScope(int[] scopes, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .findByScope(scopes, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource permissions where scope = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scope the scope
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByScope(int[] scopes, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByScope(scopes, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Removes all the resource permissions where scope = &#63; from the database.
	*
	* @param scope the scope
	*/
	public static void removeByScope(int scope) {
		getPersistence().removeByScope(scope);
	}

	/**
	* Returns the number of resource permissions where scope = &#63;.
	*
	* @param scope the scope
	* @return the number of matching resource permissions
	*/
	public static int countByScope(int scope) {
		return getPersistence().countByScope(scope);
	}

	/**
	* Returns the number of resource permissions where scope = any &#63;.
	*
	* @param scopes the scopes
	* @return the number of matching resource permissions
	*/
	public static int countByScope(int[] scopes) {
		return getPersistence().countByScope(scopes);
	}

	/**
	* Returns all the resource permissions where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the matching resource permissions
	*/
	public static List<ResourcePermission> findByRoleId(long roleId) {
		return getPersistence().findByRoleId(roleId);
	}

	/**
	* Returns a range of all the resource permissions where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @return the range of matching resource permissions
	*/
	public static List<ResourcePermission> findByRoleId(long roleId, int start,
		int end) {
		return getPersistence().findByRoleId(roleId, start, end);
	}

	/**
	* Returns an ordered range of all the resource permissions where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByRoleId(long roleId, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .findByRoleId(roleId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource permissions where roleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param roleId the role ID
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByRoleId(long roleId, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRoleId(roleId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first resource permission in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByRoleId_First(long roleId,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence().findByRoleId_First(roleId, orderByComparator);
	}

	/**
	* Returns the first resource permission in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByRoleId_First(long roleId,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence().fetchByRoleId_First(roleId, orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByRoleId_Last(long roleId,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence().findByRoleId_Last(roleId, orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where roleId = &#63;.
	*
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByRoleId_Last(long roleId,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence().fetchByRoleId_Last(roleId, orderByComparator);
	}

	/**
	* Returns the resource permissions before and after the current resource permission in the ordered set where roleId = &#63;.
	*
	* @param resourcePermissionId the primary key of the current resource permission
	* @param roleId the role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource permission
	* @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	*/
	public static ResourcePermission[] findByRoleId_PrevAndNext(
		long resourcePermissionId, long roleId,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByRoleId_PrevAndNext(resourcePermissionId, roleId,
			orderByComparator);
	}

	/**
	* Removes all the resource permissions where roleId = &#63; from the database.
	*
	* @param roleId the role ID
	*/
	public static void removeByRoleId(long roleId) {
		getPersistence().removeByRoleId(roleId);
	}

	/**
	* Returns the number of resource permissions where roleId = &#63;.
	*
	* @param roleId the role ID
	* @return the number of matching resource permissions
	*/
	public static int countByRoleId(long roleId) {
		return getPersistence().countByRoleId(roleId);
	}

	/**
	* Returns all the resource permissions where companyId = &#63; and primKey LIKE &#63;.
	*
	* @param companyId the company ID
	* @param primKey the prim key
	* @return the matching resource permissions
	*/
	public static List<ResourcePermission> findByC_LikeP(long companyId,
		java.lang.String primKey) {
		return getPersistence().findByC_LikeP(companyId, primKey);
	}

	/**
	* Returns a range of all the resource permissions where companyId = &#63; and primKey LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param primKey the prim key
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @return the range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_LikeP(long companyId,
		java.lang.String primKey, int start, int end) {
		return getPersistence().findByC_LikeP(companyId, primKey, start, end);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and primKey LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param primKey the prim key
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_LikeP(long companyId,
		java.lang.String primKey, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .findByC_LikeP(companyId, primKey, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and primKey LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param primKey the prim key
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_LikeP(long companyId,
		java.lang.String primKey, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_LikeP(companyId, primKey, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first resource permission in the ordered set where companyId = &#63; and primKey LIKE &#63;.
	*
	* @param companyId the company ID
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByC_LikeP_First(long companyId,
		java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_LikeP_First(companyId, primKey, orderByComparator);
	}

	/**
	* Returns the first resource permission in the ordered set where companyId = &#63; and primKey LIKE &#63;.
	*
	* @param companyId the company ID
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByC_LikeP_First(long companyId,
		java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .fetchByC_LikeP_First(companyId, primKey, orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where companyId = &#63; and primKey LIKE &#63;.
	*
	* @param companyId the company ID
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByC_LikeP_Last(long companyId,
		java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_LikeP_Last(companyId, primKey, orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where companyId = &#63; and primKey LIKE &#63;.
	*
	* @param companyId the company ID
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByC_LikeP_Last(long companyId,
		java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .fetchByC_LikeP_Last(companyId, primKey, orderByComparator);
	}

	/**
	* Returns the resource permissions before and after the current resource permission in the ordered set where companyId = &#63; and primKey LIKE &#63;.
	*
	* @param resourcePermissionId the primary key of the current resource permission
	* @param companyId the company ID
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource permission
	* @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	*/
	public static ResourcePermission[] findByC_LikeP_PrevAndNext(
		long resourcePermissionId, long companyId, java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_LikeP_PrevAndNext(resourcePermissionId, companyId,
			primKey, orderByComparator);
	}

	/**
	* Removes all the resource permissions where companyId = &#63; and primKey LIKE &#63; from the database.
	*
	* @param companyId the company ID
	* @param primKey the prim key
	*/
	public static void removeByC_LikeP(long companyId, java.lang.String primKey) {
		getPersistence().removeByC_LikeP(companyId, primKey);
	}

	/**
	* Returns the number of resource permissions where companyId = &#63; and primKey LIKE &#63;.
	*
	* @param companyId the company ID
	* @param primKey the prim key
	* @return the number of matching resource permissions
	*/
	public static int countByC_LikeP(long companyId, java.lang.String primKey) {
		return getPersistence().countByC_LikeP(companyId, primKey);
	}

	/**
	* Returns all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @return the matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S(long companyId,
		java.lang.String name, int scope) {
		return getPersistence().findByC_N_S(companyId, name, scope);
	}

	/**
	* Returns a range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @return the range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S(long companyId,
		java.lang.String name, int scope, int start, int end) {
		return getPersistence().findByC_N_S(companyId, name, scope, start, end);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S(long companyId,
		java.lang.String name, int scope, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .findByC_N_S(companyId, name, scope, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S(long companyId,
		java.lang.String name, int scope, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_N_S(companyId, name, scope, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByC_N_S_First(long companyId,
		java.lang.String name, int scope,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_N_S_First(companyId, name, scope, orderByComparator);
	}

	/**
	* Returns the first resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByC_N_S_First(long companyId,
		java.lang.String name, int scope,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .fetchByC_N_S_First(companyId, name, scope, orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByC_N_S_Last(long companyId,
		java.lang.String name, int scope,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_N_S_Last(companyId, name, scope, orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByC_N_S_Last(long companyId,
		java.lang.String name, int scope,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .fetchByC_N_S_Last(companyId, name, scope, orderByComparator);
	}

	/**
	* Returns the resource permissions before and after the current resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63;.
	*
	* @param resourcePermissionId the primary key of the current resource permission
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource permission
	* @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	*/
	public static ResourcePermission[] findByC_N_S_PrevAndNext(
		long resourcePermissionId, long companyId, java.lang.String name,
		int scope, OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_N_S_PrevAndNext(resourcePermissionId, companyId,
			name, scope, orderByComparator);
	}

	/**
	* Removes all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	*/
	public static void removeByC_N_S(long companyId, java.lang.String name,
		int scope) {
		getPersistence().removeByC_N_S(companyId, name, scope);
	}

	/**
	* Returns the number of resource permissions where companyId = &#63; and name = &#63; and scope = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @return the number of matching resource permissions
	*/
	public static int countByC_N_S(long companyId, java.lang.String name,
		int scope) {
		return getPersistence().countByC_N_S(companyId, name, scope);
	}

	/**
	* Returns all the resource permissions where companyId = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param companyId the company ID
	* @param scope the scope
	* @param primKey the prim key
	* @return the matching resource permissions
	*/
	public static List<ResourcePermission> findByC_S_P(long companyId,
		int scope, java.lang.String primKey) {
		return getPersistence().findByC_S_P(companyId, scope, primKey);
	}

	/**
	* Returns a range of all the resource permissions where companyId = &#63; and scope = &#63; and primKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param scope the scope
	* @param primKey the prim key
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @return the range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_S_P(long companyId,
		int scope, java.lang.String primKey, int start, int end) {
		return getPersistence()
				   .findByC_S_P(companyId, scope, primKey, start, end);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and scope = &#63; and primKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param scope the scope
	* @param primKey the prim key
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_S_P(long companyId,
		int scope, java.lang.String primKey, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .findByC_S_P(companyId, scope, primKey, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and scope = &#63; and primKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param scope the scope
	* @param primKey the prim key
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_S_P(long companyId,
		int scope, java.lang.String primKey, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_S_P(companyId, scope, primKey, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first resource permission in the ordered set where companyId = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param companyId the company ID
	* @param scope the scope
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByC_S_P_First(long companyId,
		int scope, java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_S_P_First(companyId, scope, primKey,
			orderByComparator);
	}

	/**
	* Returns the first resource permission in the ordered set where companyId = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param companyId the company ID
	* @param scope the scope
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByC_S_P_First(long companyId,
		int scope, java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .fetchByC_S_P_First(companyId, scope, primKey,
			orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where companyId = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param companyId the company ID
	* @param scope the scope
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByC_S_P_Last(long companyId,
		int scope, java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_S_P_Last(companyId, scope, primKey,
			orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where companyId = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param companyId the company ID
	* @param scope the scope
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByC_S_P_Last(long companyId,
		int scope, java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .fetchByC_S_P_Last(companyId, scope, primKey,
			orderByComparator);
	}

	/**
	* Returns the resource permissions before and after the current resource permission in the ordered set where companyId = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param resourcePermissionId the primary key of the current resource permission
	* @param companyId the company ID
	* @param scope the scope
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource permission
	* @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	*/
	public static ResourcePermission[] findByC_S_P_PrevAndNext(
		long resourcePermissionId, long companyId, int scope,
		java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_S_P_PrevAndNext(resourcePermissionId, companyId,
			scope, primKey, orderByComparator);
	}

	/**
	* Removes all the resource permissions where companyId = &#63; and scope = &#63; and primKey = &#63; from the database.
	*
	* @param companyId the company ID
	* @param scope the scope
	* @param primKey the prim key
	*/
	public static void removeByC_S_P(long companyId, int scope,
		java.lang.String primKey) {
		getPersistence().removeByC_S_P(companyId, scope, primKey);
	}

	/**
	* Returns the number of resource permissions where companyId = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param companyId the company ID
	* @param scope the scope
	* @param primKey the prim key
	* @return the number of matching resource permissions
	*/
	public static int countByC_S_P(long companyId, int scope,
		java.lang.String primKey) {
		return getPersistence().countByC_S_P(companyId, scope, primKey);
	}

	/**
	* Returns all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @return the matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P(long companyId,
		java.lang.String name, int scope, java.lang.String primKey) {
		return getPersistence().findByC_N_S_P(companyId, name, scope, primKey);
	}

	/**
	* Returns a range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @return the range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P(long companyId,
		java.lang.String name, int scope, java.lang.String primKey, int start,
		int end) {
		return getPersistence()
				   .findByC_N_S_P(companyId, name, scope, primKey, start, end);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P(long companyId,
		java.lang.String name, int scope, java.lang.String primKey, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .findByC_N_S_P(companyId, name, scope, primKey, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P(long companyId,
		java.lang.String name, int scope, java.lang.String primKey, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_N_S_P(companyId, name, scope, primKey, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByC_N_S_P_First(long companyId,
		java.lang.String name, int scope, java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_N_S_P_First(companyId, name, scope, primKey,
			orderByComparator);
	}

	/**
	* Returns the first resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByC_N_S_P_First(long companyId,
		java.lang.String name, int scope, java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .fetchByC_N_S_P_First(companyId, name, scope, primKey,
			orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByC_N_S_P_Last(long companyId,
		java.lang.String name, int scope, java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_N_S_P_Last(companyId, name, scope, primKey,
			orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByC_N_S_P_Last(long companyId,
		java.lang.String name, int scope, java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .fetchByC_N_S_P_Last(companyId, name, scope, primKey,
			orderByComparator);
	}

	/**
	* Returns the resource permissions before and after the current resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param resourcePermissionId the primary key of the current resource permission
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource permission
	* @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	*/
	public static ResourcePermission[] findByC_N_S_P_PrevAndNext(
		long resourcePermissionId, long companyId, java.lang.String name,
		int scope, java.lang.String primKey,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_N_S_P_PrevAndNext(resourcePermissionId, companyId,
			name, scope, primKey, orderByComparator);
	}

	/**
	* Removes all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	*/
	public static void removeByC_N_S_P(long companyId, java.lang.String name,
		int scope, java.lang.String primKey) {
		getPersistence().removeByC_N_S_P(companyId, name, scope, primKey);
	}

	/**
	* Returns the number of resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @return the number of matching resource permissions
	*/
	public static int countByC_N_S_P(long companyId, java.lang.String name,
		int scope, java.lang.String primKey) {
		return getPersistence().countByC_N_S_P(companyId, name, scope, primKey);
	}

	/**
	* Returns all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param roleIds the role IDs
	* @return the matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P_R(long companyId,
		java.lang.String name, int scope, java.lang.String primKey,
		long[] roleIds) {
		return getPersistence()
				   .findByC_N_S_P_R(companyId, name, scope, primKey, roleIds);
	}

	/**
	* Returns a range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param roleIds the role IDs
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @return the range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P_R(long companyId,
		java.lang.String name, int scope, java.lang.String primKey,
		long[] roleIds, int start, int end) {
		return getPersistence()
				   .findByC_N_S_P_R(companyId, name, scope, primKey, roleIds,
			start, end);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param roleIds the role IDs
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P_R(long companyId,
		java.lang.String name, int scope, java.lang.String primKey,
		long[] roleIds, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .findByC_N_S_P_R(companyId, name, scope, primKey, roleIds,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param roleId the role ID
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P_R(long companyId,
		java.lang.String name, int scope, java.lang.String primKey,
		long[] roleIds, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_N_S_P_R(companyId, name, scope, primKey, roleIds,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the resource permission where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = &#63; or throws a {@link NoSuchResourcePermissionException} if it could not be found.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param roleId the role ID
	* @return the matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByC_N_S_P_R(long companyId,
		java.lang.String name, int scope, java.lang.String primKey, long roleId)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_N_S_P_R(companyId, name, scope, primKey, roleId);
	}

	/**
	* Returns the resource permission where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param roleId the role ID
	* @return the matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByC_N_S_P_R(long companyId,
		java.lang.String name, int scope, java.lang.String primKey, long roleId) {
		return getPersistence()
				   .fetchByC_N_S_P_R(companyId, name, scope, primKey, roleId);
	}

	/**
	* Returns the resource permission where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param roleId the role ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByC_N_S_P_R(long companyId,
		java.lang.String name, int scope, java.lang.String primKey,
		long roleId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByC_N_S_P_R(companyId, name, scope, primKey, roleId,
			retrieveFromCache);
	}

	/**
	* Removes the resource permission where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param roleId the role ID
	* @return the resource permission that was removed
	*/
	public static ResourcePermission removeByC_N_S_P_R(long companyId,
		java.lang.String name, int scope, java.lang.String primKey, long roleId)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .removeByC_N_S_P_R(companyId, name, scope, primKey, roleId);
	}

	/**
	* Returns the number of resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param roleId the role ID
	* @return the number of matching resource permissions
	*/
	public static int countByC_N_S_P_R(long companyId, java.lang.String name,
		int scope, java.lang.String primKey, long roleId) {
		return getPersistence()
				   .countByC_N_S_P_R(companyId, name, scope, primKey, roleId);
	}

	/**
	* Returns the number of resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = any &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKey the prim key
	* @param roleIds the role IDs
	* @return the number of matching resource permissions
	*/
	public static int countByC_N_S_P_R(long companyId, java.lang.String name,
		int scope, java.lang.String primKey, long[] roleIds) {
		return getPersistence()
				   .countByC_N_S_P_R(companyId, name, scope, primKey, roleIds);
	}

	/**
	* Returns all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleId the role ID
	* @param viewActionId the view action ID
	* @return the matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		java.lang.String name, int scope, long primKeyId, long roleId,
		boolean viewActionId) {
		return getPersistence()
				   .findByC_N_S_P_R_V(companyId, name, scope, primKeyId,
			roleId, viewActionId);
	}

	/**
	* Returns a range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleId the role ID
	* @param viewActionId the view action ID
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @return the range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		java.lang.String name, int scope, long primKeyId, long roleId,
		boolean viewActionId, int start, int end) {
		return getPersistence()
				   .findByC_N_S_P_R_V(companyId, name, scope, primKeyId,
			roleId, viewActionId, start, end);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleId the role ID
	* @param viewActionId the view action ID
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		java.lang.String name, int scope, long primKeyId, long roleId,
		boolean viewActionId, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .findByC_N_S_P_R_V(companyId, name, scope, primKeyId,
			roleId, viewActionId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleId the role ID
	* @param viewActionId the view action ID
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		java.lang.String name, int scope, long primKeyId, long roleId,
		boolean viewActionId, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_N_S_P_R_V(companyId, name, scope, primKeyId,
			roleId, viewActionId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleId the role ID
	* @param viewActionId the view action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByC_N_S_P_R_V_First(long companyId,
		java.lang.String name, int scope, long primKeyId, long roleId,
		boolean viewActionId,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_N_S_P_R_V_First(companyId, name, scope, primKeyId,
			roleId, viewActionId, orderByComparator);
	}

	/**
	* Returns the first resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleId the role ID
	* @param viewActionId the view action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByC_N_S_P_R_V_First(long companyId,
		java.lang.String name, int scope, long primKeyId, long roleId,
		boolean viewActionId,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .fetchByC_N_S_P_R_V_First(companyId, name, scope, primKeyId,
			roleId, viewActionId, orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleId the role ID
	* @param viewActionId the view action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission
	* @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	*/
	public static ResourcePermission findByC_N_S_P_R_V_Last(long companyId,
		java.lang.String name, int scope, long primKeyId, long roleId,
		boolean viewActionId,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_N_S_P_R_V_Last(companyId, name, scope, primKeyId,
			roleId, viewActionId, orderByComparator);
	}

	/**
	* Returns the last resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleId the role ID
	* @param viewActionId the view action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	*/
	public static ResourcePermission fetchByC_N_S_P_R_V_Last(long companyId,
		java.lang.String name, int scope, long primKeyId, long roleId,
		boolean viewActionId,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .fetchByC_N_S_P_R_V_Last(companyId, name, scope, primKeyId,
			roleId, viewActionId, orderByComparator);
	}

	/**
	* Returns the resource permissions before and after the current resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	*
	* @param resourcePermissionId the primary key of the current resource permission
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleId the role ID
	* @param viewActionId the view action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource permission
	* @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	*/
	public static ResourcePermission[] findByC_N_S_P_R_V_PrevAndNext(
		long resourcePermissionId, long companyId, java.lang.String name,
		int scope, long primKeyId, long roleId, boolean viewActionId,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence()
				   .findByC_N_S_P_R_V_PrevAndNext(resourcePermissionId,
			companyId, name, scope, primKeyId, roleId, viewActionId,
			orderByComparator);
	}

	/**
	* Returns all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = any &#63; and viewActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleIds the role IDs
	* @param viewActionId the view action ID
	* @return the matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		java.lang.String name, int scope, long primKeyId, long[] roleIds,
		boolean viewActionId) {
		return getPersistence()
				   .findByC_N_S_P_R_V(companyId, name, scope, primKeyId,
			roleIds, viewActionId);
	}

	/**
	* Returns a range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = any &#63; and viewActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleIds the role IDs
	* @param viewActionId the view action ID
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @return the range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		java.lang.String name, int scope, long primKeyId, long[] roleIds,
		boolean viewActionId, int start, int end) {
		return getPersistence()
				   .findByC_N_S_P_R_V(companyId, name, scope, primKeyId,
			roleIds, viewActionId, start, end);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = any &#63; and viewActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleIds the role IDs
	* @param viewActionId the view action ID
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		java.lang.String name, int scope, long primKeyId, long[] roleIds,
		boolean viewActionId, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence()
				   .findByC_N_S_P_R_V(companyId, name, scope, primKeyId,
			roleIds, viewActionId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleId the role ID
	* @param viewActionId the view action ID
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource permissions
	*/
	public static List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		java.lang.String name, int scope, long primKeyId, long[] roleIds,
		boolean viewActionId, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_N_S_P_R_V(companyId, name, scope, primKeyId,
			roleIds, viewActionId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Removes all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleId the role ID
	* @param viewActionId the view action ID
	*/
	public static void removeByC_N_S_P_R_V(long companyId,
		java.lang.String name, int scope, long primKeyId, long roleId,
		boolean viewActionId) {
		getPersistence()
			.removeByC_N_S_P_R_V(companyId, name, scope, primKeyId, roleId,
			viewActionId);
	}

	/**
	* Returns the number of resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleId the role ID
	* @param viewActionId the view action ID
	* @return the number of matching resource permissions
	*/
	public static int countByC_N_S_P_R_V(long companyId, java.lang.String name,
		int scope, long primKeyId, long roleId, boolean viewActionId) {
		return getPersistence()
				   .countByC_N_S_P_R_V(companyId, name, scope, primKeyId,
			roleId, viewActionId);
	}

	/**
	* Returns the number of resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = any &#63; and viewActionId = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param scope the scope
	* @param primKeyId the prim key ID
	* @param roleIds the role IDs
	* @param viewActionId the view action ID
	* @return the number of matching resource permissions
	*/
	public static int countByC_N_S_P_R_V(long companyId, java.lang.String name,
		int scope, long primKeyId, long[] roleIds, boolean viewActionId) {
		return getPersistence()
				   .countByC_N_S_P_R_V(companyId, name, scope, primKeyId,
			roleIds, viewActionId);
	}

	/**
	* Caches the resource permission in the entity cache if it is enabled.
	*
	* @param resourcePermission the resource permission
	*/
	public static void cacheResult(ResourcePermission resourcePermission) {
		getPersistence().cacheResult(resourcePermission);
	}

	/**
	* Caches the resource permissions in the entity cache if it is enabled.
	*
	* @param resourcePermissions the resource permissions
	*/
	public static void cacheResult(List<ResourcePermission> resourcePermissions) {
		getPersistence().cacheResult(resourcePermissions);
	}

	/**
	* Creates a new resource permission with the primary key. Does not add the resource permission to the database.
	*
	* @param resourcePermissionId the primary key for the new resource permission
	* @return the new resource permission
	*/
	public static ResourcePermission create(long resourcePermissionId) {
		return getPersistence().create(resourcePermissionId);
	}

	/**
	* Removes the resource permission with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourcePermissionId the primary key of the resource permission
	* @return the resource permission that was removed
	* @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	*/
	public static ResourcePermission remove(long resourcePermissionId)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence().remove(resourcePermissionId);
	}

	public static ResourcePermission updateImpl(
		ResourcePermission resourcePermission) {
		return getPersistence().updateImpl(resourcePermission);
	}

	/**
	* Returns the resource permission with the primary key or throws a {@link NoSuchResourcePermissionException} if it could not be found.
	*
	* @param resourcePermissionId the primary key of the resource permission
	* @return the resource permission
	* @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	*/
	public static ResourcePermission findByPrimaryKey(long resourcePermissionId)
		throws com.liferay.portal.kernel.exception.NoSuchResourcePermissionException {
		return getPersistence().findByPrimaryKey(resourcePermissionId);
	}

	/**
	* Returns the resource permission with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param resourcePermissionId the primary key of the resource permission
	* @return the resource permission, or <code>null</code> if a resource permission with the primary key could not be found
	*/
	public static ResourcePermission fetchByPrimaryKey(
		long resourcePermissionId) {
		return getPersistence().fetchByPrimaryKey(resourcePermissionId);
	}

	public static java.util.Map<java.io.Serializable, ResourcePermission> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the resource permissions.
	*
	* @return the resource permissions
	*/
	public static List<ResourcePermission> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the resource permissions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @return the range of resource permissions
	*/
	public static List<ResourcePermission> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the resource permissions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of resource permissions
	*/
	public static List<ResourcePermission> findAll(int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource permissions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource permissions
	* @param end the upper bound of the range of resource permissions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of resource permissions
	*/
	public static List<ResourcePermission> findAll(int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the resource permissions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of resource permissions.
	*
	* @return the number of resource permissions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ResourcePermissionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ResourcePermissionPersistence)PortalBeanLocatorUtil.locate(ResourcePermissionPersistence.class.getName());

			ReferenceRegistry.registerReference(ResourcePermissionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static ResourcePermissionPersistence _persistence;
}