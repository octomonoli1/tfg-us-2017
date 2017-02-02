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
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the resource action service. This utility wraps {@link com.liferay.portal.service.persistence.impl.ResourceActionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceActionPersistence
 * @see com.liferay.portal.service.persistence.impl.ResourceActionPersistenceImpl
 * @generated
 */
@ProviderType
public class ResourceActionUtil {
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
	public static void clearCache(ResourceAction resourceAction) {
		getPersistence().clearCache(resourceAction);
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
	public static List<ResourceAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ResourceAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ResourceAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ResourceAction> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ResourceAction update(ResourceAction resourceAction) {
		return getPersistence().update(resourceAction);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ResourceAction update(ResourceAction resourceAction,
		ServiceContext serviceContext) {
		return getPersistence().update(resourceAction, serviceContext);
	}

	/**
	* Returns all the resource actions where name = &#63;.
	*
	* @param name the name
	* @return the matching resource actions
	*/
	public static List<ResourceAction> findByName(java.lang.String name) {
		return getPersistence().findByName(name);
	}

	/**
	* Returns a range of all the resource actions where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of resource actions
	* @param end the upper bound of the range of resource actions (not inclusive)
	* @return the range of matching resource actions
	*/
	public static List<ResourceAction> findByName(java.lang.String name,
		int start, int end) {
		return getPersistence().findByName(name, start, end);
	}

	/**
	* Returns an ordered range of all the resource actions where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of resource actions
	* @param end the upper bound of the range of resource actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource actions
	*/
	public static List<ResourceAction> findByName(java.lang.String name,
		int start, int end, OrderByComparator<ResourceAction> orderByComparator) {
		return getPersistence().findByName(name, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource actions where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of resource actions
	* @param end the upper bound of the range of resource actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching resource actions
	*/
	public static List<ResourceAction> findByName(java.lang.String name,
		int start, int end,
		OrderByComparator<ResourceAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByName(name, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first resource action in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource action
	* @throws NoSuchResourceActionException if a matching resource action could not be found
	*/
	public static ResourceAction findByName_First(java.lang.String name,
		OrderByComparator<ResourceAction> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourceActionException {
		return getPersistence().findByName_First(name, orderByComparator);
	}

	/**
	* Returns the first resource action in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource action, or <code>null</code> if a matching resource action could not be found
	*/
	public static ResourceAction fetchByName_First(java.lang.String name,
		OrderByComparator<ResourceAction> orderByComparator) {
		return getPersistence().fetchByName_First(name, orderByComparator);
	}

	/**
	* Returns the last resource action in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource action
	* @throws NoSuchResourceActionException if a matching resource action could not be found
	*/
	public static ResourceAction findByName_Last(java.lang.String name,
		OrderByComparator<ResourceAction> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourceActionException {
		return getPersistence().findByName_Last(name, orderByComparator);
	}

	/**
	* Returns the last resource action in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource action, or <code>null</code> if a matching resource action could not be found
	*/
	public static ResourceAction fetchByName_Last(java.lang.String name,
		OrderByComparator<ResourceAction> orderByComparator) {
		return getPersistence().fetchByName_Last(name, orderByComparator);
	}

	/**
	* Returns the resource actions before and after the current resource action in the ordered set where name = &#63;.
	*
	* @param resourceActionId the primary key of the current resource action
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource action
	* @throws NoSuchResourceActionException if a resource action with the primary key could not be found
	*/
	public static ResourceAction[] findByName_PrevAndNext(
		long resourceActionId, java.lang.String name,
		OrderByComparator<ResourceAction> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchResourceActionException {
		return getPersistence()
				   .findByName_PrevAndNext(resourceActionId, name,
			orderByComparator);
	}

	/**
	* Removes all the resource actions where name = &#63; from the database.
	*
	* @param name the name
	*/
	public static void removeByName(java.lang.String name) {
		getPersistence().removeByName(name);
	}

	/**
	* Returns the number of resource actions where name = &#63;.
	*
	* @param name the name
	* @return the number of matching resource actions
	*/
	public static int countByName(java.lang.String name) {
		return getPersistence().countByName(name);
	}

	/**
	* Returns the resource action where name = &#63; and actionId = &#63; or throws a {@link NoSuchResourceActionException} if it could not be found.
	*
	* @param name the name
	* @param actionId the action ID
	* @return the matching resource action
	* @throws NoSuchResourceActionException if a matching resource action could not be found
	*/
	public static ResourceAction findByN_A(java.lang.String name,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.NoSuchResourceActionException {
		return getPersistence().findByN_A(name, actionId);
	}

	/**
	* Returns the resource action where name = &#63; and actionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @param actionId the action ID
	* @return the matching resource action, or <code>null</code> if a matching resource action could not be found
	*/
	public static ResourceAction fetchByN_A(java.lang.String name,
		java.lang.String actionId) {
		return getPersistence().fetchByN_A(name, actionId);
	}

	/**
	* Returns the resource action where name = &#63; and actionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param actionId the action ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching resource action, or <code>null</code> if a matching resource action could not be found
	*/
	public static ResourceAction fetchByN_A(java.lang.String name,
		java.lang.String actionId, boolean retrieveFromCache) {
		return getPersistence().fetchByN_A(name, actionId, retrieveFromCache);
	}

	/**
	* Removes the resource action where name = &#63; and actionId = &#63; from the database.
	*
	* @param name the name
	* @param actionId the action ID
	* @return the resource action that was removed
	*/
	public static ResourceAction removeByN_A(java.lang.String name,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.NoSuchResourceActionException {
		return getPersistence().removeByN_A(name, actionId);
	}

	/**
	* Returns the number of resource actions where name = &#63; and actionId = &#63;.
	*
	* @param name the name
	* @param actionId the action ID
	* @return the number of matching resource actions
	*/
	public static int countByN_A(java.lang.String name,
		java.lang.String actionId) {
		return getPersistence().countByN_A(name, actionId);
	}

	/**
	* Caches the resource action in the entity cache if it is enabled.
	*
	* @param resourceAction the resource action
	*/
	public static void cacheResult(ResourceAction resourceAction) {
		getPersistence().cacheResult(resourceAction);
	}

	/**
	* Caches the resource actions in the entity cache if it is enabled.
	*
	* @param resourceActions the resource actions
	*/
	public static void cacheResult(List<ResourceAction> resourceActions) {
		getPersistence().cacheResult(resourceActions);
	}

	/**
	* Creates a new resource action with the primary key. Does not add the resource action to the database.
	*
	* @param resourceActionId the primary key for the new resource action
	* @return the new resource action
	*/
	public static ResourceAction create(long resourceActionId) {
		return getPersistence().create(resourceActionId);
	}

	/**
	* Removes the resource action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceActionId the primary key of the resource action
	* @return the resource action that was removed
	* @throws NoSuchResourceActionException if a resource action with the primary key could not be found
	*/
	public static ResourceAction remove(long resourceActionId)
		throws com.liferay.portal.kernel.exception.NoSuchResourceActionException {
		return getPersistence().remove(resourceActionId);
	}

	public static ResourceAction updateImpl(ResourceAction resourceAction) {
		return getPersistence().updateImpl(resourceAction);
	}

	/**
	* Returns the resource action with the primary key or throws a {@link NoSuchResourceActionException} if it could not be found.
	*
	* @param resourceActionId the primary key of the resource action
	* @return the resource action
	* @throws NoSuchResourceActionException if a resource action with the primary key could not be found
	*/
	public static ResourceAction findByPrimaryKey(long resourceActionId)
		throws com.liferay.portal.kernel.exception.NoSuchResourceActionException {
		return getPersistence().findByPrimaryKey(resourceActionId);
	}

	/**
	* Returns the resource action with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param resourceActionId the primary key of the resource action
	* @return the resource action, or <code>null</code> if a resource action with the primary key could not be found
	*/
	public static ResourceAction fetchByPrimaryKey(long resourceActionId) {
		return getPersistence().fetchByPrimaryKey(resourceActionId);
	}

	public static java.util.Map<java.io.Serializable, ResourceAction> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the resource actions.
	*
	* @return the resource actions
	*/
	public static List<ResourceAction> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the resource actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource actions
	* @param end the upper bound of the range of resource actions (not inclusive)
	* @return the range of resource actions
	*/
	public static List<ResourceAction> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the resource actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource actions
	* @param end the upper bound of the range of resource actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of resource actions
	*/
	public static List<ResourceAction> findAll(int start, int end,
		OrderByComparator<ResourceAction> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the resource actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource actions
	* @param end the upper bound of the range of resource actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of resource actions
	*/
	public static List<ResourceAction> findAll(int start, int end,
		OrderByComparator<ResourceAction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the resource actions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of resource actions.
	*
	* @return the number of resource actions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ResourceActionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ResourceActionPersistence)PortalBeanLocatorUtil.locate(ResourceActionPersistence.class.getName());

			ReferenceRegistry.registerReference(ResourceActionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static ResourceActionPersistence _persistence;
}