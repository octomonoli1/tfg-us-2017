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
import com.liferay.portal.kernel.model.PortletItem;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the portlet item service. This utility wraps {@link com.liferay.portal.service.persistence.impl.PortletItemPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortletItemPersistence
 * @see com.liferay.portal.service.persistence.impl.PortletItemPersistenceImpl
 * @generated
 */
@ProviderType
public class PortletItemUtil {
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
	public static void clearCache(PortletItem portletItem) {
		getPersistence().clearCache(portletItem);
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
	public static List<PortletItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PortletItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PortletItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PortletItem> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PortletItem update(PortletItem portletItem) {
		return getPersistence().update(portletItem);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PortletItem update(PortletItem portletItem,
		ServiceContext serviceContext) {
		return getPersistence().update(portletItem, serviceContext);
	}

	/**
	* Returns all the portlet items where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @return the matching portlet items
	*/
	public static List<PortletItem> findByG_C(long groupId, long classNameId) {
		return getPersistence().findByG_C(groupId, classNameId);
	}

	/**
	* Returns a range of all the portlet items where groupId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of portlet items
	* @param end the upper bound of the range of portlet items (not inclusive)
	* @return the range of matching portlet items
	*/
	public static List<PortletItem> findByG_C(long groupId, long classNameId,
		int start, int end) {
		return getPersistence().findByG_C(groupId, classNameId, start, end);
	}

	/**
	* Returns an ordered range of all the portlet items where groupId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of portlet items
	* @param end the upper bound of the range of portlet items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching portlet items
	*/
	public static List<PortletItem> findByG_C(long groupId, long classNameId,
		int start, int end, OrderByComparator<PortletItem> orderByComparator) {
		return getPersistence()
				   .findByG_C(groupId, classNameId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the portlet items where groupId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of portlet items
	* @param end the upper bound of the range of portlet items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching portlet items
	*/
	public static List<PortletItem> findByG_C(long groupId, long classNameId,
		int start, int end, OrderByComparator<PortletItem> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_C(groupId, classNameId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first portlet item in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portlet item
	* @throws NoSuchPortletItemException if a matching portlet item could not be found
	*/
	public static PortletItem findByG_C_First(long groupId, long classNameId,
		OrderByComparator<PortletItem> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchPortletItemException {
		return getPersistence()
				   .findByG_C_First(groupId, classNameId, orderByComparator);
	}

	/**
	* Returns the first portlet item in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portlet item, or <code>null</code> if a matching portlet item could not be found
	*/
	public static PortletItem fetchByG_C_First(long groupId, long classNameId,
		OrderByComparator<PortletItem> orderByComparator) {
		return getPersistence()
				   .fetchByG_C_First(groupId, classNameId, orderByComparator);
	}

	/**
	* Returns the last portlet item in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portlet item
	* @throws NoSuchPortletItemException if a matching portlet item could not be found
	*/
	public static PortletItem findByG_C_Last(long groupId, long classNameId,
		OrderByComparator<PortletItem> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchPortletItemException {
		return getPersistence()
				   .findByG_C_Last(groupId, classNameId, orderByComparator);
	}

	/**
	* Returns the last portlet item in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portlet item, or <code>null</code> if a matching portlet item could not be found
	*/
	public static PortletItem fetchByG_C_Last(long groupId, long classNameId,
		OrderByComparator<PortletItem> orderByComparator) {
		return getPersistence()
				   .fetchByG_C_Last(groupId, classNameId, orderByComparator);
	}

	/**
	* Returns the portlet items before and after the current portlet item in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param portletItemId the primary key of the current portlet item
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next portlet item
	* @throws NoSuchPortletItemException if a portlet item with the primary key could not be found
	*/
	public static PortletItem[] findByG_C_PrevAndNext(long portletItemId,
		long groupId, long classNameId,
		OrderByComparator<PortletItem> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchPortletItemException {
		return getPersistence()
				   .findByG_C_PrevAndNext(portletItemId, groupId, classNameId,
			orderByComparator);
	}

	/**
	* Removes all the portlet items where groupId = &#63; and classNameId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	*/
	public static void removeByG_C(long groupId, long classNameId) {
		getPersistence().removeByG_C(groupId, classNameId);
	}

	/**
	* Returns the number of portlet items where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @return the number of matching portlet items
	*/
	public static int countByG_C(long groupId, long classNameId) {
		return getPersistence().countByG_C(groupId, classNameId);
	}

	/**
	* Returns all the portlet items where groupId = &#63; and portletId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @return the matching portlet items
	*/
	public static List<PortletItem> findByG_P_C(long groupId,
		java.lang.String portletId, long classNameId) {
		return getPersistence().findByG_P_C(groupId, portletId, classNameId);
	}

	/**
	* Returns a range of all the portlet items where groupId = &#63; and portletId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of portlet items
	* @param end the upper bound of the range of portlet items (not inclusive)
	* @return the range of matching portlet items
	*/
	public static List<PortletItem> findByG_P_C(long groupId,
		java.lang.String portletId, long classNameId, int start, int end) {
		return getPersistence()
				   .findByG_P_C(groupId, portletId, classNameId, start, end);
	}

	/**
	* Returns an ordered range of all the portlet items where groupId = &#63; and portletId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of portlet items
	* @param end the upper bound of the range of portlet items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching portlet items
	*/
	public static List<PortletItem> findByG_P_C(long groupId,
		java.lang.String portletId, long classNameId, int start, int end,
		OrderByComparator<PortletItem> orderByComparator) {
		return getPersistence()
				   .findByG_P_C(groupId, portletId, classNameId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the portlet items where groupId = &#63; and portletId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of portlet items
	* @param end the upper bound of the range of portlet items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching portlet items
	*/
	public static List<PortletItem> findByG_P_C(long groupId,
		java.lang.String portletId, long classNameId, int start, int end,
		OrderByComparator<PortletItem> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P_C(groupId, portletId, classNameId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first portlet item in the ordered set where groupId = &#63; and portletId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portlet item
	* @throws NoSuchPortletItemException if a matching portlet item could not be found
	*/
	public static PortletItem findByG_P_C_First(long groupId,
		java.lang.String portletId, long classNameId,
		OrderByComparator<PortletItem> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchPortletItemException {
		return getPersistence()
				   .findByG_P_C_First(groupId, portletId, classNameId,
			orderByComparator);
	}

	/**
	* Returns the first portlet item in the ordered set where groupId = &#63; and portletId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portlet item, or <code>null</code> if a matching portlet item could not be found
	*/
	public static PortletItem fetchByG_P_C_First(long groupId,
		java.lang.String portletId, long classNameId,
		OrderByComparator<PortletItem> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_C_First(groupId, portletId, classNameId,
			orderByComparator);
	}

	/**
	* Returns the last portlet item in the ordered set where groupId = &#63; and portletId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portlet item
	* @throws NoSuchPortletItemException if a matching portlet item could not be found
	*/
	public static PortletItem findByG_P_C_Last(long groupId,
		java.lang.String portletId, long classNameId,
		OrderByComparator<PortletItem> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchPortletItemException {
		return getPersistence()
				   .findByG_P_C_Last(groupId, portletId, classNameId,
			orderByComparator);
	}

	/**
	* Returns the last portlet item in the ordered set where groupId = &#63; and portletId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portlet item, or <code>null</code> if a matching portlet item could not be found
	*/
	public static PortletItem fetchByG_P_C_Last(long groupId,
		java.lang.String portletId, long classNameId,
		OrderByComparator<PortletItem> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_C_Last(groupId, portletId, classNameId,
			orderByComparator);
	}

	/**
	* Returns the portlet items before and after the current portlet item in the ordered set where groupId = &#63; and portletId = &#63; and classNameId = &#63;.
	*
	* @param portletItemId the primary key of the current portlet item
	* @param groupId the group ID
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next portlet item
	* @throws NoSuchPortletItemException if a portlet item with the primary key could not be found
	*/
	public static PortletItem[] findByG_P_C_PrevAndNext(long portletItemId,
		long groupId, java.lang.String portletId, long classNameId,
		OrderByComparator<PortletItem> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchPortletItemException {
		return getPersistence()
				   .findByG_P_C_PrevAndNext(portletItemId, groupId, portletId,
			classNameId, orderByComparator);
	}

	/**
	* Removes all the portlet items where groupId = &#63; and portletId = &#63; and classNameId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	*/
	public static void removeByG_P_C(long groupId, java.lang.String portletId,
		long classNameId) {
		getPersistence().removeByG_P_C(groupId, portletId, classNameId);
	}

	/**
	* Returns the number of portlet items where groupId = &#63; and portletId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @return the number of matching portlet items
	*/
	public static int countByG_P_C(long groupId, java.lang.String portletId,
		long classNameId) {
		return getPersistence().countByG_P_C(groupId, portletId, classNameId);
	}

	/**
	* Returns the portlet item where groupId = &#63; and name = &#63; and portletId = &#63; and classNameId = &#63; or throws a {@link NoSuchPortletItemException} if it could not be found.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @return the matching portlet item
	* @throws NoSuchPortletItemException if a matching portlet item could not be found
	*/
	public static PortletItem findByG_N_P_C(long groupId,
		java.lang.String name, java.lang.String portletId, long classNameId)
		throws com.liferay.portal.kernel.exception.NoSuchPortletItemException {
		return getPersistence()
				   .findByG_N_P_C(groupId, name, portletId, classNameId);
	}

	/**
	* Returns the portlet item where groupId = &#63; and name = &#63; and portletId = &#63; and classNameId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @return the matching portlet item, or <code>null</code> if a matching portlet item could not be found
	*/
	public static PortletItem fetchByG_N_P_C(long groupId,
		java.lang.String name, java.lang.String portletId, long classNameId) {
		return getPersistence()
				   .fetchByG_N_P_C(groupId, name, portletId, classNameId);
	}

	/**
	* Returns the portlet item where groupId = &#63; and name = &#63; and portletId = &#63; and classNameId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching portlet item, or <code>null</code> if a matching portlet item could not be found
	*/
	public static PortletItem fetchByG_N_P_C(long groupId,
		java.lang.String name, java.lang.String portletId, long classNameId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_N_P_C(groupId, name, portletId, classNameId,
			retrieveFromCache);
	}

	/**
	* Removes the portlet item where groupId = &#63; and name = &#63; and portletId = &#63; and classNameId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @return the portlet item that was removed
	*/
	public static PortletItem removeByG_N_P_C(long groupId,
		java.lang.String name, java.lang.String portletId, long classNameId)
		throws com.liferay.portal.kernel.exception.NoSuchPortletItemException {
		return getPersistence()
				   .removeByG_N_P_C(groupId, name, portletId, classNameId);
	}

	/**
	* Returns the number of portlet items where groupId = &#63; and name = &#63; and portletId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @param classNameId the class name ID
	* @return the number of matching portlet items
	*/
	public static int countByG_N_P_C(long groupId, java.lang.String name,
		java.lang.String portletId, long classNameId) {
		return getPersistence()
				   .countByG_N_P_C(groupId, name, portletId, classNameId);
	}

	/**
	* Caches the portlet item in the entity cache if it is enabled.
	*
	* @param portletItem the portlet item
	*/
	public static void cacheResult(PortletItem portletItem) {
		getPersistence().cacheResult(portletItem);
	}

	/**
	* Caches the portlet items in the entity cache if it is enabled.
	*
	* @param portletItems the portlet items
	*/
	public static void cacheResult(List<PortletItem> portletItems) {
		getPersistence().cacheResult(portletItems);
	}

	/**
	* Creates a new portlet item with the primary key. Does not add the portlet item to the database.
	*
	* @param portletItemId the primary key for the new portlet item
	* @return the new portlet item
	*/
	public static PortletItem create(long portletItemId) {
		return getPersistence().create(portletItemId);
	}

	/**
	* Removes the portlet item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param portletItemId the primary key of the portlet item
	* @return the portlet item that was removed
	* @throws NoSuchPortletItemException if a portlet item with the primary key could not be found
	*/
	public static PortletItem remove(long portletItemId)
		throws com.liferay.portal.kernel.exception.NoSuchPortletItemException {
		return getPersistence().remove(portletItemId);
	}

	public static PortletItem updateImpl(PortletItem portletItem) {
		return getPersistence().updateImpl(portletItem);
	}

	/**
	* Returns the portlet item with the primary key or throws a {@link NoSuchPortletItemException} if it could not be found.
	*
	* @param portletItemId the primary key of the portlet item
	* @return the portlet item
	* @throws NoSuchPortletItemException if a portlet item with the primary key could not be found
	*/
	public static PortletItem findByPrimaryKey(long portletItemId)
		throws com.liferay.portal.kernel.exception.NoSuchPortletItemException {
		return getPersistence().findByPrimaryKey(portletItemId);
	}

	/**
	* Returns the portlet item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param portletItemId the primary key of the portlet item
	* @return the portlet item, or <code>null</code> if a portlet item with the primary key could not be found
	*/
	public static PortletItem fetchByPrimaryKey(long portletItemId) {
		return getPersistence().fetchByPrimaryKey(portletItemId);
	}

	public static java.util.Map<java.io.Serializable, PortletItem> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the portlet items.
	*
	* @return the portlet items
	*/
	public static List<PortletItem> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the portlet items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of portlet items
	* @param end the upper bound of the range of portlet items (not inclusive)
	* @return the range of portlet items
	*/
	public static List<PortletItem> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the portlet items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of portlet items
	* @param end the upper bound of the range of portlet items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of portlet items
	*/
	public static List<PortletItem> findAll(int start, int end,
		OrderByComparator<PortletItem> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the portlet items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of portlet items
	* @param end the upper bound of the range of portlet items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of portlet items
	*/
	public static List<PortletItem> findAll(int start, int end,
		OrderByComparator<PortletItem> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the portlet items from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of portlet items.
	*
	* @return the number of portlet items
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static PortletItemPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PortletItemPersistence)PortalBeanLocatorUtil.locate(PortletItemPersistence.class.getName());

			ReferenceRegistry.registerReference(PortletItemUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static PortletItemPersistence _persistence;
}