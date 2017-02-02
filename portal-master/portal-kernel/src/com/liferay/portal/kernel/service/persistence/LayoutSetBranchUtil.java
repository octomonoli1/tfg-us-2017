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
import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the layout set branch service. This utility wraps {@link com.liferay.portal.service.persistence.impl.LayoutSetBranchPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetBranchPersistence
 * @see com.liferay.portal.service.persistence.impl.LayoutSetBranchPersistenceImpl
 * @generated
 */
@ProviderType
public class LayoutSetBranchUtil {
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
	public static void clearCache(LayoutSetBranch layoutSetBranch) {
		getPersistence().clearCache(layoutSetBranch);
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
	public static List<LayoutSetBranch> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LayoutSetBranch> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LayoutSetBranch> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LayoutSetBranch update(LayoutSetBranch layoutSetBranch) {
		return getPersistence().update(layoutSetBranch);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LayoutSetBranch update(LayoutSetBranch layoutSetBranch,
		ServiceContext serviceContext) {
		return getPersistence().update(layoutSetBranch, serviceContext);
	}

	/**
	* Returns all the layout set branchs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching layout set branchs
	*/
	public static List<LayoutSetBranch> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the layout set branchs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @return the range of matching layout set branchs
	*/
	public static List<LayoutSetBranch> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the layout set branchs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set branchs
	*/
	public static List<LayoutSetBranch> findByGroupId(long groupId, int start,
		int end, OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the layout set branchs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout set branchs
	*/
	public static List<LayoutSetBranch> findByGroupId(long groupId, int start,
		int end, OrderByComparator<LayoutSetBranch> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first layout set branch in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set branch
	* @throws NoSuchLayoutSetBranchException if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch findByGroupId_First(long groupId,
		OrderByComparator<LayoutSetBranch> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first layout set branch in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set branch, or <code>null</code> if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch fetchByGroupId_First(long groupId,
		OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last layout set branch in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set branch
	* @throws NoSuchLayoutSetBranchException if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch findByGroupId_Last(long groupId,
		OrderByComparator<LayoutSetBranch> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last layout set branch in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set branch, or <code>null</code> if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch fetchByGroupId_Last(long groupId,
		OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the layout set branchs before and after the current layout set branch in the ordered set where groupId = &#63;.
	*
	* @param layoutSetBranchId the primary key of the current layout set branch
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set branch
	* @throws NoSuchLayoutSetBranchException if a layout set branch with the primary key could not be found
	*/
	public static LayoutSetBranch[] findByGroupId_PrevAndNext(
		long layoutSetBranchId, long groupId,
		OrderByComparator<LayoutSetBranch> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(layoutSetBranchId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the layout set branchs that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching layout set branchs that the user has permission to view
	*/
	public static List<LayoutSetBranch> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the layout set branchs that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @return the range of matching layout set branchs that the user has permission to view
	*/
	public static List<LayoutSetBranch> filterFindByGroupId(long groupId,
		int start, int end) {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the layout set branchs that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set branchs that the user has permission to view
	*/
	public static List<LayoutSetBranch> filterFindByGroupId(long groupId,
		int start, int end, OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the layout set branchs before and after the current layout set branch in the ordered set of layout set branchs that the user has permission to view where groupId = &#63;.
	*
	* @param layoutSetBranchId the primary key of the current layout set branch
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set branch
	* @throws NoSuchLayoutSetBranchException if a layout set branch with the primary key could not be found
	*/
	public static LayoutSetBranch[] filterFindByGroupId_PrevAndNext(
		long layoutSetBranchId, long groupId,
		OrderByComparator<LayoutSetBranch> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(layoutSetBranchId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the layout set branchs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of layout set branchs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching layout set branchs
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of layout set branchs that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching layout set branchs that the user has permission to view
	*/
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the layout set branchs where groupId = &#63; and privateLayout = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @return the matching layout set branchs
	*/
	public static List<LayoutSetBranch> findByG_P(long groupId,
		boolean privateLayout) {
		return getPersistence().findByG_P(groupId, privateLayout);
	}

	/**
	* Returns a range of all the layout set branchs where groupId = &#63; and privateLayout = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @return the range of matching layout set branchs
	*/
	public static List<LayoutSetBranch> findByG_P(long groupId,
		boolean privateLayout, int start, int end) {
		return getPersistence().findByG_P(groupId, privateLayout, start, end);
	}

	/**
	* Returns an ordered range of all the layout set branchs where groupId = &#63; and privateLayout = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set branchs
	*/
	public static List<LayoutSetBranch> findByG_P(long groupId,
		boolean privateLayout, int start, int end,
		OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence()
				   .findByG_P(groupId, privateLayout, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the layout set branchs where groupId = &#63; and privateLayout = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout set branchs
	*/
	public static List<LayoutSetBranch> findByG_P(long groupId,
		boolean privateLayout, int start, int end,
		OrderByComparator<LayoutSetBranch> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P(groupId, privateLayout, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first layout set branch in the ordered set where groupId = &#63; and privateLayout = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set branch
	* @throws NoSuchLayoutSetBranchException if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch findByG_P_First(long groupId,
		boolean privateLayout,
		OrderByComparator<LayoutSetBranch> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence()
				   .findByG_P_First(groupId, privateLayout, orderByComparator);
	}

	/**
	* Returns the first layout set branch in the ordered set where groupId = &#63; and privateLayout = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set branch, or <code>null</code> if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch fetchByG_P_First(long groupId,
		boolean privateLayout,
		OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_First(groupId, privateLayout, orderByComparator);
	}

	/**
	* Returns the last layout set branch in the ordered set where groupId = &#63; and privateLayout = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set branch
	* @throws NoSuchLayoutSetBranchException if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch findByG_P_Last(long groupId,
		boolean privateLayout,
		OrderByComparator<LayoutSetBranch> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence()
				   .findByG_P_Last(groupId, privateLayout, orderByComparator);
	}

	/**
	* Returns the last layout set branch in the ordered set where groupId = &#63; and privateLayout = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set branch, or <code>null</code> if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch fetchByG_P_Last(long groupId,
		boolean privateLayout,
		OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_Last(groupId, privateLayout, orderByComparator);
	}

	/**
	* Returns the layout set branchs before and after the current layout set branch in the ordered set where groupId = &#63; and privateLayout = &#63;.
	*
	* @param layoutSetBranchId the primary key of the current layout set branch
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set branch
	* @throws NoSuchLayoutSetBranchException if a layout set branch with the primary key could not be found
	*/
	public static LayoutSetBranch[] findByG_P_PrevAndNext(
		long layoutSetBranchId, long groupId, boolean privateLayout,
		OrderByComparator<LayoutSetBranch> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence()
				   .findByG_P_PrevAndNext(layoutSetBranchId, groupId,
			privateLayout, orderByComparator);
	}

	/**
	* Returns all the layout set branchs that the user has permission to view where groupId = &#63; and privateLayout = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @return the matching layout set branchs that the user has permission to view
	*/
	public static List<LayoutSetBranch> filterFindByG_P(long groupId,
		boolean privateLayout) {
		return getPersistence().filterFindByG_P(groupId, privateLayout);
	}

	/**
	* Returns a range of all the layout set branchs that the user has permission to view where groupId = &#63; and privateLayout = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @return the range of matching layout set branchs that the user has permission to view
	*/
	public static List<LayoutSetBranch> filterFindByG_P(long groupId,
		boolean privateLayout, int start, int end) {
		return getPersistence()
				   .filterFindByG_P(groupId, privateLayout, start, end);
	}

	/**
	* Returns an ordered range of all the layout set branchs that the user has permissions to view where groupId = &#63; and privateLayout = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set branchs that the user has permission to view
	*/
	public static List<LayoutSetBranch> filterFindByG_P(long groupId,
		boolean privateLayout, int start, int end,
		OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence()
				   .filterFindByG_P(groupId, privateLayout, start, end,
			orderByComparator);
	}

	/**
	* Returns the layout set branchs before and after the current layout set branch in the ordered set of layout set branchs that the user has permission to view where groupId = &#63; and privateLayout = &#63;.
	*
	* @param layoutSetBranchId the primary key of the current layout set branch
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set branch
	* @throws NoSuchLayoutSetBranchException if a layout set branch with the primary key could not be found
	*/
	public static LayoutSetBranch[] filterFindByG_P_PrevAndNext(
		long layoutSetBranchId, long groupId, boolean privateLayout,
		OrderByComparator<LayoutSetBranch> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence()
				   .filterFindByG_P_PrevAndNext(layoutSetBranchId, groupId,
			privateLayout, orderByComparator);
	}

	/**
	* Removes all the layout set branchs where groupId = &#63; and privateLayout = &#63; from the database.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	*/
	public static void removeByG_P(long groupId, boolean privateLayout) {
		getPersistence().removeByG_P(groupId, privateLayout);
	}

	/**
	* Returns the number of layout set branchs where groupId = &#63; and privateLayout = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @return the number of matching layout set branchs
	*/
	public static int countByG_P(long groupId, boolean privateLayout) {
		return getPersistence().countByG_P(groupId, privateLayout);
	}

	/**
	* Returns the number of layout set branchs that the user has permission to view where groupId = &#63; and privateLayout = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @return the number of matching layout set branchs that the user has permission to view
	*/
	public static int filterCountByG_P(long groupId, boolean privateLayout) {
		return getPersistence().filterCountByG_P(groupId, privateLayout);
	}

	/**
	* Returns the layout set branch where groupId = &#63; and privateLayout = &#63; and name = &#63; or throws a {@link NoSuchLayoutSetBranchException} if it could not be found.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param name the name
	* @return the matching layout set branch
	* @throws NoSuchLayoutSetBranchException if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch findByG_P_N(long groupId,
		boolean privateLayout, java.lang.String name)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence().findByG_P_N(groupId, privateLayout, name);
	}

	/**
	* Returns the layout set branch where groupId = &#63; and privateLayout = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param name the name
	* @return the matching layout set branch, or <code>null</code> if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch fetchByG_P_N(long groupId,
		boolean privateLayout, java.lang.String name) {
		return getPersistence().fetchByG_P_N(groupId, privateLayout, name);
	}

	/**
	* Returns the layout set branch where groupId = &#63; and privateLayout = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching layout set branch, or <code>null</code> if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch fetchByG_P_N(long groupId,
		boolean privateLayout, java.lang.String name, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_P_N(groupId, privateLayout, name, retrieveFromCache);
	}

	/**
	* Removes the layout set branch where groupId = &#63; and privateLayout = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param name the name
	* @return the layout set branch that was removed
	*/
	public static LayoutSetBranch removeByG_P_N(long groupId,
		boolean privateLayout, java.lang.String name)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence().removeByG_P_N(groupId, privateLayout, name);
	}

	/**
	* Returns the number of layout set branchs where groupId = &#63; and privateLayout = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param name the name
	* @return the number of matching layout set branchs
	*/
	public static int countByG_P_N(long groupId, boolean privateLayout,
		java.lang.String name) {
		return getPersistence().countByG_P_N(groupId, privateLayout, name);
	}

	/**
	* Returns all the layout set branchs where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @return the matching layout set branchs
	*/
	public static List<LayoutSetBranch> findByG_P_M(long groupId,
		boolean privateLayout, boolean master) {
		return getPersistence().findByG_P_M(groupId, privateLayout, master);
	}

	/**
	* Returns a range of all the layout set branchs where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @return the range of matching layout set branchs
	*/
	public static List<LayoutSetBranch> findByG_P_M(long groupId,
		boolean privateLayout, boolean master, int start, int end) {
		return getPersistence()
				   .findByG_P_M(groupId, privateLayout, master, start, end);
	}

	/**
	* Returns an ordered range of all the layout set branchs where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set branchs
	*/
	public static List<LayoutSetBranch> findByG_P_M(long groupId,
		boolean privateLayout, boolean master, int start, int end,
		OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence()
				   .findByG_P_M(groupId, privateLayout, master, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the layout set branchs where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout set branchs
	*/
	public static List<LayoutSetBranch> findByG_P_M(long groupId,
		boolean privateLayout, boolean master, int start, int end,
		OrderByComparator<LayoutSetBranch> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P_M(groupId, privateLayout, master, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first layout set branch in the ordered set where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set branch
	* @throws NoSuchLayoutSetBranchException if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch findByG_P_M_First(long groupId,
		boolean privateLayout, boolean master,
		OrderByComparator<LayoutSetBranch> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence()
				   .findByG_P_M_First(groupId, privateLayout, master,
			orderByComparator);
	}

	/**
	* Returns the first layout set branch in the ordered set where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout set branch, or <code>null</code> if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch fetchByG_P_M_First(long groupId,
		boolean privateLayout, boolean master,
		OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_M_First(groupId, privateLayout, master,
			orderByComparator);
	}

	/**
	* Returns the last layout set branch in the ordered set where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set branch
	* @throws NoSuchLayoutSetBranchException if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch findByG_P_M_Last(long groupId,
		boolean privateLayout, boolean master,
		OrderByComparator<LayoutSetBranch> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence()
				   .findByG_P_M_Last(groupId, privateLayout, master,
			orderByComparator);
	}

	/**
	* Returns the last layout set branch in the ordered set where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout set branch, or <code>null</code> if a matching layout set branch could not be found
	*/
	public static LayoutSetBranch fetchByG_P_M_Last(long groupId,
		boolean privateLayout, boolean master,
		OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_M_Last(groupId, privateLayout, master,
			orderByComparator);
	}

	/**
	* Returns the layout set branchs before and after the current layout set branch in the ordered set where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* @param layoutSetBranchId the primary key of the current layout set branch
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set branch
	* @throws NoSuchLayoutSetBranchException if a layout set branch with the primary key could not be found
	*/
	public static LayoutSetBranch[] findByG_P_M_PrevAndNext(
		long layoutSetBranchId, long groupId, boolean privateLayout,
		boolean master, OrderByComparator<LayoutSetBranch> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence()
				   .findByG_P_M_PrevAndNext(layoutSetBranchId, groupId,
			privateLayout, master, orderByComparator);
	}

	/**
	* Returns all the layout set branchs that the user has permission to view where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @return the matching layout set branchs that the user has permission to view
	*/
	public static List<LayoutSetBranch> filterFindByG_P_M(long groupId,
		boolean privateLayout, boolean master) {
		return getPersistence().filterFindByG_P_M(groupId, privateLayout, master);
	}

	/**
	* Returns a range of all the layout set branchs that the user has permission to view where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @return the range of matching layout set branchs that the user has permission to view
	*/
	public static List<LayoutSetBranch> filterFindByG_P_M(long groupId,
		boolean privateLayout, boolean master, int start, int end) {
		return getPersistence()
				   .filterFindByG_P_M(groupId, privateLayout, master, start, end);
	}

	/**
	* Returns an ordered range of all the layout set branchs that the user has permissions to view where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout set branchs that the user has permission to view
	*/
	public static List<LayoutSetBranch> filterFindByG_P_M(long groupId,
		boolean privateLayout, boolean master, int start, int end,
		OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence()
				   .filterFindByG_P_M(groupId, privateLayout, master, start,
			end, orderByComparator);
	}

	/**
	* Returns the layout set branchs before and after the current layout set branch in the ordered set of layout set branchs that the user has permission to view where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* @param layoutSetBranchId the primary key of the current layout set branch
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout set branch
	* @throws NoSuchLayoutSetBranchException if a layout set branch with the primary key could not be found
	*/
	public static LayoutSetBranch[] filterFindByG_P_M_PrevAndNext(
		long layoutSetBranchId, long groupId, boolean privateLayout,
		boolean master, OrderByComparator<LayoutSetBranch> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence()
				   .filterFindByG_P_M_PrevAndNext(layoutSetBranchId, groupId,
			privateLayout, master, orderByComparator);
	}

	/**
	* Removes all the layout set branchs where groupId = &#63; and privateLayout = &#63; and master = &#63; from the database.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	*/
	public static void removeByG_P_M(long groupId, boolean privateLayout,
		boolean master) {
		getPersistence().removeByG_P_M(groupId, privateLayout, master);
	}

	/**
	* Returns the number of layout set branchs where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @return the number of matching layout set branchs
	*/
	public static int countByG_P_M(long groupId, boolean privateLayout,
		boolean master) {
		return getPersistence().countByG_P_M(groupId, privateLayout, master);
	}

	/**
	* Returns the number of layout set branchs that the user has permission to view where groupId = &#63; and privateLayout = &#63; and master = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param master the master
	* @return the number of matching layout set branchs that the user has permission to view
	*/
	public static int filterCountByG_P_M(long groupId, boolean privateLayout,
		boolean master) {
		return getPersistence()
				   .filterCountByG_P_M(groupId, privateLayout, master);
	}

	/**
	* Caches the layout set branch in the entity cache if it is enabled.
	*
	* @param layoutSetBranch the layout set branch
	*/
	public static void cacheResult(LayoutSetBranch layoutSetBranch) {
		getPersistence().cacheResult(layoutSetBranch);
	}

	/**
	* Caches the layout set branchs in the entity cache if it is enabled.
	*
	* @param layoutSetBranchs the layout set branchs
	*/
	public static void cacheResult(List<LayoutSetBranch> layoutSetBranchs) {
		getPersistence().cacheResult(layoutSetBranchs);
	}

	/**
	* Creates a new layout set branch with the primary key. Does not add the layout set branch to the database.
	*
	* @param layoutSetBranchId the primary key for the new layout set branch
	* @return the new layout set branch
	*/
	public static LayoutSetBranch create(long layoutSetBranchId) {
		return getPersistence().create(layoutSetBranchId);
	}

	/**
	* Removes the layout set branch with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranchId the primary key of the layout set branch
	* @return the layout set branch that was removed
	* @throws NoSuchLayoutSetBranchException if a layout set branch with the primary key could not be found
	*/
	public static LayoutSetBranch remove(long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence().remove(layoutSetBranchId);
	}

	public static LayoutSetBranch updateImpl(LayoutSetBranch layoutSetBranch) {
		return getPersistence().updateImpl(layoutSetBranch);
	}

	/**
	* Returns the layout set branch with the primary key or throws a {@link NoSuchLayoutSetBranchException} if it could not be found.
	*
	* @param layoutSetBranchId the primary key of the layout set branch
	* @return the layout set branch
	* @throws NoSuchLayoutSetBranchException if a layout set branch with the primary key could not be found
	*/
	public static LayoutSetBranch findByPrimaryKey(long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.NoSuchLayoutSetBranchException {
		return getPersistence().findByPrimaryKey(layoutSetBranchId);
	}

	/**
	* Returns the layout set branch with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param layoutSetBranchId the primary key of the layout set branch
	* @return the layout set branch, or <code>null</code> if a layout set branch with the primary key could not be found
	*/
	public static LayoutSetBranch fetchByPrimaryKey(long layoutSetBranchId) {
		return getPersistence().fetchByPrimaryKey(layoutSetBranchId);
	}

	public static java.util.Map<java.io.Serializable, LayoutSetBranch> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the layout set branchs.
	*
	* @return the layout set branchs
	*/
	public static List<LayoutSetBranch> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the layout set branchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @return the range of layout set branchs
	*/
	public static List<LayoutSetBranch> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the layout set branchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of layout set branchs
	*/
	public static List<LayoutSetBranch> findAll(int start, int end,
		OrderByComparator<LayoutSetBranch> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the layout set branchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutSetBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout set branchs
	* @param end the upper bound of the range of layout set branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of layout set branchs
	*/
	public static List<LayoutSetBranch> findAll(int start, int end,
		OrderByComparator<LayoutSetBranch> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the layout set branchs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of layout set branchs.
	*
	* @return the number of layout set branchs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LayoutSetBranchPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (LayoutSetBranchPersistence)PortalBeanLocatorUtil.locate(LayoutSetBranchPersistence.class.getName());

			ReferenceRegistry.registerReference(LayoutSetBranchUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static LayoutSetBranchPersistence _persistence;
}