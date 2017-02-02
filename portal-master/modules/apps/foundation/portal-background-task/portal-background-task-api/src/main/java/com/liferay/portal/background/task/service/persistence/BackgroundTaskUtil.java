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

package com.liferay.portal.background.task.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the background task service. This utility wraps {@link com.liferay.portal.background.task.service.persistence.impl.BackgroundTaskPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BackgroundTaskPersistence
 * @see com.liferay.portal.background.task.service.persistence.impl.BackgroundTaskPersistenceImpl
 * @generated
 */
@ProviderType
public class BackgroundTaskUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(BackgroundTask backgroundTask) {
		getPersistence().clearCache(backgroundTask);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<BackgroundTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BackgroundTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BackgroundTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static BackgroundTask update(BackgroundTask backgroundTask) {
		return getPersistence().update(backgroundTask);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static BackgroundTask update(BackgroundTask backgroundTask,
		ServiceContext serviceContext) {
		return getPersistence().update(backgroundTask, serviceContext);
	}

	/**
	* Returns all the background tasks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the background tasks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByGroupId(long groupId, int start,
		int end, OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByGroupId(long groupId, int start,
		int end, OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByGroupId_First(long groupId,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByGroupId_First(long groupId,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByGroupId_Last(long groupId,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByGroupId_Last(long groupId,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the background tasks before and after the current background task in the ordered set where groupId = &#63;.
	*
	* @param backgroundTaskId the primary key of the current background task
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next background task
	* @throws NoSuchBackgroundTaskException if a background task with the primary key could not be found
	*/
	public static BackgroundTask[] findByGroupId_PrevAndNext(
		long backgroundTaskId, long groupId,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(backgroundTaskId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the background tasks where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of background tasks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching background tasks
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the background tasks where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the background tasks where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByCompanyId(long companyId,
		int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByCompanyId(long companyId,
		int start, int end, OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first background task in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByCompanyId_First(long companyId,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first background task in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByCompanyId_First(long companyId,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByCompanyId_Last(long companyId,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByCompanyId_Last(long companyId,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the background tasks before and after the current background task in the ordered set where companyId = &#63;.
	*
	* @param backgroundTaskId the primary key of the current background task
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next background task
	* @throws NoSuchBackgroundTaskException if a background task with the primary key could not be found
	*/
	public static BackgroundTask[] findByCompanyId_PrevAndNext(
		long backgroundTaskId, long companyId,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(backgroundTaskId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the background tasks where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of background tasks where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching background tasks
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the background tasks where completed = &#63;.
	*
	* @param completed the completed
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByCompleted(boolean completed) {
		return getPersistence().findByCompleted(completed);
	}

	/**
	* Returns a range of all the background tasks where completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByCompleted(boolean completed,
		int start, int end) {
		return getPersistence().findByCompleted(completed, start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByCompleted(boolean completed,
		int start, int end, OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByCompleted(completed, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByCompleted(boolean completed,
		int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompleted(completed, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first background task in the ordered set where completed = &#63;.
	*
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByCompleted_First(boolean completed,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByCompleted_First(completed, orderByComparator);
	}

	/**
	* Returns the first background task in the ordered set where completed = &#63;.
	*
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByCompleted_First(boolean completed,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByCompleted_First(completed, orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where completed = &#63;.
	*
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByCompleted_Last(boolean completed,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByCompleted_Last(completed, orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where completed = &#63;.
	*
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByCompleted_Last(boolean completed,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByCompleted_Last(completed, orderByComparator);
	}

	/**
	* Returns the background tasks before and after the current background task in the ordered set where completed = &#63;.
	*
	* @param backgroundTaskId the primary key of the current background task
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next background task
	* @throws NoSuchBackgroundTaskException if a background task with the primary key could not be found
	*/
	public static BackgroundTask[] findByCompleted_PrevAndNext(
		long backgroundTaskId, boolean completed,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByCompleted_PrevAndNext(backgroundTaskId, completed,
			orderByComparator);
	}

	/**
	* Removes all the background tasks where completed = &#63; from the database.
	*
	* @param completed the completed
	*/
	public static void removeByCompleted(boolean completed) {
		getPersistence().removeByCompleted(completed);
	}

	/**
	* Returns the number of background tasks where completed = &#63;.
	*
	* @param completed the completed
	* @return the number of matching background tasks
	*/
	public static int countByCompleted(boolean completed) {
		return getPersistence().countByCompleted(completed);
	}

	/**
	* Returns all the background tasks where status = &#63;.
	*
	* @param status the status
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByStatus(int status) {
		return getPersistence().findByStatus(status);
	}

	/**
	* Returns a range of all the background tasks where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByStatus(int status, int start,
		int end) {
		return getPersistence().findByStatus(status, start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByStatus(int status, int start,
		int end, OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByStatus(int status, int start,
		int end, OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first background task in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByStatus_First(int status,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the first background task in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByStatus_First(int status,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByStatus_Last(int status,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByStatus_Last(int status,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence().fetchByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the background tasks before and after the current background task in the ordered set where status = &#63;.
	*
	* @param backgroundTaskId the primary key of the current background task
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next background task
	* @throws NoSuchBackgroundTaskException if a background task with the primary key could not be found
	*/
	public static BackgroundTask[] findByStatus_PrevAndNext(
		long backgroundTaskId, int status,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByStatus_PrevAndNext(backgroundTaskId, status,
			orderByComparator);
	}

	/**
	* Removes all the background tasks where status = &#63; from the database.
	*
	* @param status the status
	*/
	public static void removeByStatus(int status) {
		getPersistence().removeByStatus(status);
	}

	/**
	* Returns the number of background tasks where status = &#63;.
	*
	* @param status the status
	* @return the number of matching background tasks
	*/
	public static int countByStatus(int status) {
		return getPersistence().countByStatus(status);
	}

	/**
	* Returns all the background tasks where groupId = &#63; and taskExecutorClassName = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByG_T(long groupId,
		java.lang.String taskExecutorClassName) {
		return getPersistence().findByG_T(groupId, taskExecutorClassName);
	}

	/**
	* Returns a range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T(long groupId,
		java.lang.String taskExecutorClassName, int start, int end) {
		return getPersistence()
				   .findByG_T(groupId, taskExecutorClassName, start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T(long groupId,
		java.lang.String taskExecutorClassName, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByG_T(groupId, taskExecutorClassName, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T(long groupId,
		java.lang.String taskExecutorClassName, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_T(groupId, taskExecutorClassName, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByG_T_First(long groupId,
		java.lang.String taskExecutorClassName,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_T_First(groupId, taskExecutorClassName,
			orderByComparator);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByG_T_First(long groupId,
		java.lang.String taskExecutorClassName,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByG_T_First(groupId, taskExecutorClassName,
			orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByG_T_Last(long groupId,
		java.lang.String taskExecutorClassName,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_T_Last(groupId, taskExecutorClassName,
			orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByG_T_Last(long groupId,
		java.lang.String taskExecutorClassName,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByG_T_Last(groupId, taskExecutorClassName,
			orderByComparator);
	}

	/**
	* Returns the background tasks before and after the current background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63;.
	*
	* @param backgroundTaskId the primary key of the current background task
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next background task
	* @throws NoSuchBackgroundTaskException if a background task with the primary key could not be found
	*/
	public static BackgroundTask[] findByG_T_PrevAndNext(
		long backgroundTaskId, long groupId,
		java.lang.String taskExecutorClassName,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_T_PrevAndNext(backgroundTaskId, groupId,
			taskExecutorClassName, orderByComparator);
	}

	/**
	* Returns all the background tasks where groupId = any &#63; and taskExecutorClassName = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param taskExecutorClassNames the task executor class names
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByG_T(long[] groupIds,
		java.lang.String[] taskExecutorClassNames) {
		return getPersistence().findByG_T(groupIds, taskExecutorClassNames);
	}

	/**
	* Returns a range of all the background tasks where groupId = any &#63; and taskExecutorClassName = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param taskExecutorClassNames the task executor class names
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T(long[] groupIds,
		java.lang.String[] taskExecutorClassNames, int start, int end) {
		return getPersistence()
				   .findByG_T(groupIds, taskExecutorClassNames, start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = any &#63; and taskExecutorClassName = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param taskExecutorClassNames the task executor class names
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T(long[] groupIds,
		java.lang.String[] taskExecutorClassNames, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByG_T(groupIds, taskExecutorClassNames, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T(long[] groupIds,
		java.lang.String[] taskExecutorClassNames, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_T(groupIds, taskExecutorClassNames, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; from the database.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	*/
	public static void removeByG_T(long groupId,
		java.lang.String taskExecutorClassName) {
		getPersistence().removeByG_T(groupId, taskExecutorClassName);
	}

	/**
	* Returns the number of background tasks where groupId = &#63; and taskExecutorClassName = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @return the number of matching background tasks
	*/
	public static int countByG_T(long groupId,
		java.lang.String taskExecutorClassName) {
		return getPersistence().countByG_T(groupId, taskExecutorClassName);
	}

	/**
	* Returns the number of background tasks where groupId = any &#63; and taskExecutorClassName = any &#63;.
	*
	* @param groupIds the group IDs
	* @param taskExecutorClassNames the task executor class names
	* @return the number of matching background tasks
	*/
	public static int countByG_T(long[] groupIds,
		java.lang.String[] taskExecutorClassNames) {
		return getPersistence().countByG_T(groupIds, taskExecutorClassNames);
	}

	/**
	* Returns all the background tasks where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByG_S(long groupId, int status) {
		return getPersistence().findByG_S(groupId, status);
	}

	/**
	* Returns a range of all the background tasks where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_S(long groupId, int status,
		int start, int end) {
		return getPersistence().findByG_S(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_S(long groupId, int status,
		int start, int end, OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByG_S(groupId, status, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_S(long groupId, int status,
		int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_S(groupId, status, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByG_S_First(long groupId, int status,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_S_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByG_S_First(long groupId, int status,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByG_S_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByG_S_Last(long groupId, int status,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_S_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByG_S_Last(long groupId, int status,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByG_S_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the background tasks before and after the current background task in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param backgroundTaskId the primary key of the current background task
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next background task
	* @throws NoSuchBackgroundTaskException if a background task with the primary key could not be found
	*/
	public static BackgroundTask[] findByG_S_PrevAndNext(
		long backgroundTaskId, long groupId, int status,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_S_PrevAndNext(backgroundTaskId, groupId, status,
			orderByComparator);
	}

	/**
	* Removes all the background tasks where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	*/
	public static void removeByG_S(long groupId, int status) {
		getPersistence().removeByG_S(groupId, status);
	}

	/**
	* Returns the number of background tasks where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching background tasks
	*/
	public static int countByG_S(long groupId, int status) {
		return getPersistence().countByG_S(groupId, status);
	}

	/**
	* Returns all the background tasks where taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByT_S(
		java.lang.String taskExecutorClassName, int status) {
		return getPersistence().findByT_S(taskExecutorClassName, status);
	}

	/**
	* Returns a range of all the background tasks where taskExecutorClassName = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByT_S(
		java.lang.String taskExecutorClassName, int status, int start, int end) {
		return getPersistence()
				   .findByT_S(taskExecutorClassName, status, start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where taskExecutorClassName = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByT_S(
		java.lang.String taskExecutorClassName, int status, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByT_S(taskExecutorClassName, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where taskExecutorClassName = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByT_S(
		java.lang.String taskExecutorClassName, int status, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByT_S(taskExecutorClassName, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first background task in the ordered set where taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByT_S_First(
		java.lang.String taskExecutorClassName, int status,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByT_S_First(taskExecutorClassName, status,
			orderByComparator);
	}

	/**
	* Returns the first background task in the ordered set where taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByT_S_First(
		java.lang.String taskExecutorClassName, int status,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByT_S_First(taskExecutorClassName, status,
			orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByT_S_Last(
		java.lang.String taskExecutorClassName, int status,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByT_S_Last(taskExecutorClassName, status,
			orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByT_S_Last(
		java.lang.String taskExecutorClassName, int status,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByT_S_Last(taskExecutorClassName, status,
			orderByComparator);
	}

	/**
	* Returns the background tasks before and after the current background task in the ordered set where taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param backgroundTaskId the primary key of the current background task
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next background task
	* @throws NoSuchBackgroundTaskException if a background task with the primary key could not be found
	*/
	public static BackgroundTask[] findByT_S_PrevAndNext(
		long backgroundTaskId, java.lang.String taskExecutorClassName,
		int status, OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByT_S_PrevAndNext(backgroundTaskId,
			taskExecutorClassName, status, orderByComparator);
	}

	/**
	* Returns all the background tasks where taskExecutorClassName = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param taskExecutorClassNames the task executor class names
	* @param status the status
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByT_S(
		java.lang.String[] taskExecutorClassNames, int status) {
		return getPersistence().findByT_S(taskExecutorClassNames, status);
	}

	/**
	* Returns a range of all the background tasks where taskExecutorClassName = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param taskExecutorClassNames the task executor class names
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByT_S(
		java.lang.String[] taskExecutorClassNames, int status, int start,
		int end) {
		return getPersistence()
				   .findByT_S(taskExecutorClassNames, status, start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where taskExecutorClassName = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param taskExecutorClassNames the task executor class names
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByT_S(
		java.lang.String[] taskExecutorClassNames, int status, int start,
		int end, OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByT_S(taskExecutorClassNames, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where taskExecutorClassName = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByT_S(
		java.lang.String[] taskExecutorClassNames, int status, int start,
		int end, OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByT_S(taskExecutorClassNames, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the background tasks where taskExecutorClassName = &#63; and status = &#63; from the database.
	*
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	*/
	public static void removeByT_S(java.lang.String taskExecutorClassName,
		int status) {
		getPersistence().removeByT_S(taskExecutorClassName, status);
	}

	/**
	* Returns the number of background tasks where taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @return the number of matching background tasks
	*/
	public static int countByT_S(java.lang.String taskExecutorClassName,
		int status) {
		return getPersistence().countByT_S(taskExecutorClassName, status);
	}

	/**
	* Returns the number of background tasks where taskExecutorClassName = any &#63; and status = &#63;.
	*
	* @param taskExecutorClassNames the task executor class names
	* @param status the status
	* @return the number of matching background tasks
	*/
	public static int countByT_S(java.lang.String[] taskExecutorClassNames,
		int status) {
		return getPersistence().countByT_S(taskExecutorClassNames, status);
	}

	/**
	* Returns all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName) {
		return getPersistence().findByG_N_T(groupId, name, taskExecutorClassName);
	}

	/**
	* Returns a range of all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		int start, int end) {
		return getPersistence()
				   .findByG_N_T(groupId, name, taskExecutorClassName, start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		int start, int end, OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByG_N_T(groupId, name, taskExecutorClassName, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_N_T(groupId, name, taskExecutorClassName, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByG_N_T_First(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_N_T_First(groupId, name, taskExecutorClassName,
			orderByComparator);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByG_N_T_First(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByG_N_T_First(groupId, name, taskExecutorClassName,
			orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByG_N_T_Last(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_N_T_Last(groupId, name, taskExecutorClassName,
			orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByG_N_T_Last(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByG_N_T_Last(groupId, name, taskExecutorClassName,
			orderByComparator);
	}

	/**
	* Returns the background tasks before and after the current background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* @param backgroundTaskId the primary key of the current background task
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next background task
	* @throws NoSuchBackgroundTaskException if a background task with the primary key could not be found
	*/
	public static BackgroundTask[] findByG_N_T_PrevAndNext(
		long backgroundTaskId, long groupId, java.lang.String name,
		java.lang.String taskExecutorClassName,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_N_T_PrevAndNext(backgroundTaskId, groupId, name,
			taskExecutorClassName, orderByComparator);
	}

	/**
	* Returns all the background tasks where groupId = any &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T(long[] groupIds,
		java.lang.String name, java.lang.String taskExecutorClassName) {
		return getPersistence()
				   .findByG_N_T(groupIds, name, taskExecutorClassName);
	}

	/**
	* Returns a range of all the background tasks where groupId = any &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T(long[] groupIds,
		java.lang.String name, java.lang.String taskExecutorClassName,
		int start, int end) {
		return getPersistence()
				   .findByG_N_T(groupIds, name, taskExecutorClassName, start,
			end);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = any &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T(long[] groupIds,
		java.lang.String name, java.lang.String taskExecutorClassName,
		int start, int end, OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByG_N_T(groupIds, name, taskExecutorClassName, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T(long[] groupIds,
		java.lang.String name, java.lang.String taskExecutorClassName,
		int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_N_T(groupIds, name, taskExecutorClassName, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	*/
	public static void removeByG_N_T(long groupId, java.lang.String name,
		java.lang.String taskExecutorClassName) {
		getPersistence().removeByG_N_T(groupId, name, taskExecutorClassName);
	}

	/**
	* Returns the number of background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @return the number of matching background tasks
	*/
	public static int countByG_N_T(long groupId, java.lang.String name,
		java.lang.String taskExecutorClassName) {
		return getPersistence()
				   .countByG_N_T(groupId, name, taskExecutorClassName);
	}

	/**
	* Returns the number of background tasks where groupId = any &#63; and name = &#63; and taskExecutorClassName = &#63;.
	*
	* @param groupIds the group IDs
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @return the number of matching background tasks
	*/
	public static int countByG_N_T(long[] groupIds, java.lang.String name,
		java.lang.String taskExecutorClassName) {
		return getPersistence()
				   .countByG_N_T(groupIds, name, taskExecutorClassName);
	}

	/**
	* Returns all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_C(long groupId,
		java.lang.String taskExecutorClassName, boolean completed) {
		return getPersistence()
				   .findByG_T_C(groupId, taskExecutorClassName, completed);
	}

	/**
	* Returns a range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_C(long groupId,
		java.lang.String taskExecutorClassName, boolean completed, int start,
		int end) {
		return getPersistence()
				   .findByG_T_C(groupId, taskExecutorClassName, completed,
			start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_C(long groupId,
		java.lang.String taskExecutorClassName, boolean completed, int start,
		int end, OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByG_T_C(groupId, taskExecutorClassName, completed,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_C(long groupId,
		java.lang.String taskExecutorClassName, boolean completed, int start,
		int end, OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_T_C(groupId, taskExecutorClassName, completed,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByG_T_C_First(long groupId,
		java.lang.String taskExecutorClassName, boolean completed,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_T_C_First(groupId, taskExecutorClassName,
			completed, orderByComparator);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByG_T_C_First(long groupId,
		java.lang.String taskExecutorClassName, boolean completed,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByG_T_C_First(groupId, taskExecutorClassName,
			completed, orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByG_T_C_Last(long groupId,
		java.lang.String taskExecutorClassName, boolean completed,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_T_C_Last(groupId, taskExecutorClassName, completed,
			orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByG_T_C_Last(long groupId,
		java.lang.String taskExecutorClassName, boolean completed,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByG_T_C_Last(groupId, taskExecutorClassName,
			completed, orderByComparator);
	}

	/**
	* Returns the background tasks before and after the current background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param backgroundTaskId the primary key of the current background task
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next background task
	* @throws NoSuchBackgroundTaskException if a background task with the primary key could not be found
	*/
	public static BackgroundTask[] findByG_T_C_PrevAndNext(
		long backgroundTaskId, long groupId,
		java.lang.String taskExecutorClassName, boolean completed,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_T_C_PrevAndNext(backgroundTaskId, groupId,
			taskExecutorClassName, completed, orderByComparator);
	}

	/**
	* Returns all the background tasks where groupId = any &#63; and taskExecutorClassName = any &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param taskExecutorClassNames the task executor class names
	* @param completed the completed
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_C(long[] groupIds,
		java.lang.String[] taskExecutorClassNames, boolean completed) {
		return getPersistence()
				   .findByG_T_C(groupIds, taskExecutorClassNames, completed);
	}

	/**
	* Returns a range of all the background tasks where groupId = any &#63; and taskExecutorClassName = any &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param taskExecutorClassNames the task executor class names
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_C(long[] groupIds,
		java.lang.String[] taskExecutorClassNames, boolean completed,
		int start, int end) {
		return getPersistence()
				   .findByG_T_C(groupIds, taskExecutorClassNames, completed,
			start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = any &#63; and taskExecutorClassName = any &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param taskExecutorClassNames the task executor class names
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_C(long[] groupIds,
		java.lang.String[] taskExecutorClassNames, boolean completed,
		int start, int end, OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByG_T_C(groupIds, taskExecutorClassNames, completed,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_C(long[] groupIds,
		java.lang.String[] taskExecutorClassNames, boolean completed,
		int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_T_C(groupIds, taskExecutorClassNames, completed,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63; from the database.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	*/
	public static void removeByG_T_C(long groupId,
		java.lang.String taskExecutorClassName, boolean completed) {
		getPersistence().removeByG_T_C(groupId, taskExecutorClassName, completed);
	}

	/**
	* Returns the number of background tasks where groupId = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @return the number of matching background tasks
	*/
	public static int countByG_T_C(long groupId,
		java.lang.String taskExecutorClassName, boolean completed) {
		return getPersistence()
				   .countByG_T_C(groupId, taskExecutorClassName, completed);
	}

	/**
	* Returns the number of background tasks where groupId = any &#63; and taskExecutorClassName = any &#63; and completed = &#63;.
	*
	* @param groupIds the group IDs
	* @param taskExecutorClassNames the task executor class names
	* @param completed the completed
	* @return the number of matching background tasks
	*/
	public static int countByG_T_C(long[] groupIds,
		java.lang.String[] taskExecutorClassNames, boolean completed) {
		return getPersistence()
				   .countByG_T_C(groupIds, taskExecutorClassNames, completed);
	}

	/**
	* Returns all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_S(long groupId,
		java.lang.String taskExecutorClassName, int status) {
		return getPersistence()
				   .findByG_T_S(groupId, taskExecutorClassName, status);
	}

	/**
	* Returns a range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_S(long groupId,
		java.lang.String taskExecutorClassName, int status, int start, int end) {
		return getPersistence()
				   .findByG_T_S(groupId, taskExecutorClassName, status, start,
			end);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_S(long groupId,
		java.lang.String taskExecutorClassName, int status, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByG_T_S(groupId, taskExecutorClassName, status, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_S(long groupId,
		java.lang.String taskExecutorClassName, int status, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_T_S(groupId, taskExecutorClassName, status, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByG_T_S_First(long groupId,
		java.lang.String taskExecutorClassName, int status,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_T_S_First(groupId, taskExecutorClassName, status,
			orderByComparator);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByG_T_S_First(long groupId,
		java.lang.String taskExecutorClassName, int status,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByG_T_S_First(groupId, taskExecutorClassName, status,
			orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByG_T_S_Last(long groupId,
		java.lang.String taskExecutorClassName, int status,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_T_S_Last(groupId, taskExecutorClassName, status,
			orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByG_T_S_Last(long groupId,
		java.lang.String taskExecutorClassName, int status,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByG_T_S_Last(groupId, taskExecutorClassName, status,
			orderByComparator);
	}

	/**
	* Returns the background tasks before and after the current background task in the ordered set where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param backgroundTaskId the primary key of the current background task
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next background task
	* @throws NoSuchBackgroundTaskException if a background task with the primary key could not be found
	*/
	public static BackgroundTask[] findByG_T_S_PrevAndNext(
		long backgroundTaskId, long groupId,
		java.lang.String taskExecutorClassName, int status,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_T_S_PrevAndNext(backgroundTaskId, groupId,
			taskExecutorClassName, status, orderByComparator);
	}

	/**
	* Returns all the background tasks where groupId = &#63; and taskExecutorClassName = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassNames the task executor class names
	* @param status the status
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_S(long groupId,
		java.lang.String[] taskExecutorClassNames, int status) {
		return getPersistence()
				   .findByG_T_S(groupId, taskExecutorClassNames, status);
	}

	/**
	* Returns a range of all the background tasks where groupId = &#63; and taskExecutorClassName = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassNames the task executor class names
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_S(long groupId,
		java.lang.String[] taskExecutorClassNames, int status, int start,
		int end) {
		return getPersistence()
				   .findByG_T_S(groupId, taskExecutorClassNames, status, start,
			end);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassNames the task executor class names
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_S(long groupId,
		java.lang.String[] taskExecutorClassNames, int status, int start,
		int end, OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByG_T_S(groupId, taskExecutorClassNames, status, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_T_S(long groupId,
		java.lang.String[] taskExecutorClassNames, int status, int start,
		int end, OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_T_S(groupId, taskExecutorClassNames, status, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the background tasks where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	*/
	public static void removeByG_T_S(long groupId,
		java.lang.String taskExecutorClassName, int status) {
		getPersistence().removeByG_T_S(groupId, taskExecutorClassName, status);
	}

	/**
	* Returns the number of background tasks where groupId = &#63; and taskExecutorClassName = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassName the task executor class name
	* @param status the status
	* @return the number of matching background tasks
	*/
	public static int countByG_T_S(long groupId,
		java.lang.String taskExecutorClassName, int status) {
		return getPersistence()
				   .countByG_T_S(groupId, taskExecutorClassName, status);
	}

	/**
	* Returns the number of background tasks where groupId = &#63; and taskExecutorClassName = any &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param taskExecutorClassNames the task executor class names
	* @param status the status
	* @return the number of matching background tasks
	*/
	public static int countByG_T_S(long groupId,
		java.lang.String[] taskExecutorClassNames, int status) {
		return getPersistence()
				   .countByG_T_S(groupId, taskExecutorClassNames, status);
	}

	/**
	* Returns all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T_C(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		boolean completed) {
		return getPersistence()
				   .findByG_N_T_C(groupId, name, taskExecutorClassName,
			completed);
	}

	/**
	* Returns a range of all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T_C(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		boolean completed, int start, int end) {
		return getPersistence()
				   .findByG_N_T_C(groupId, name, taskExecutorClassName,
			completed, start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T_C(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		boolean completed, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByG_N_T_C(groupId, name, taskExecutorClassName,
			completed, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T_C(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		boolean completed, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_N_T_C(groupId, name, taskExecutorClassName,
			completed, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByG_N_T_C_First(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		boolean completed, OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_N_T_C_First(groupId, name, taskExecutorClassName,
			completed, orderByComparator);
	}

	/**
	* Returns the first background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByG_N_T_C_First(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		boolean completed, OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByG_N_T_C_First(groupId, name, taskExecutorClassName,
			completed, orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task
	* @throws NoSuchBackgroundTaskException if a matching background task could not be found
	*/
	public static BackgroundTask findByG_N_T_C_Last(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		boolean completed, OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_N_T_C_Last(groupId, name, taskExecutorClassName,
			completed, orderByComparator);
	}

	/**
	* Returns the last background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching background task, or <code>null</code> if a matching background task could not be found
	*/
	public static BackgroundTask fetchByG_N_T_C_Last(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		boolean completed, OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .fetchByG_N_T_C_Last(groupId, name, taskExecutorClassName,
			completed, orderByComparator);
	}

	/**
	* Returns the background tasks before and after the current background task in the ordered set where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param backgroundTaskId the primary key of the current background task
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next background task
	* @throws NoSuchBackgroundTaskException if a background task with the primary key could not be found
	*/
	public static BackgroundTask[] findByG_N_T_C_PrevAndNext(
		long backgroundTaskId, long groupId, java.lang.String name,
		java.lang.String taskExecutorClassName, boolean completed,
		OrderByComparator<BackgroundTask> orderByComparator)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence()
				   .findByG_N_T_C_PrevAndNext(backgroundTaskId, groupId, name,
			taskExecutorClassName, completed, orderByComparator);
	}

	/**
	* Returns all the background tasks where groupId = any &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @return the matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T_C(long[] groupIds,
		java.lang.String name, java.lang.String taskExecutorClassName,
		boolean completed) {
		return getPersistence()
				   .findByG_N_T_C(groupIds, name, taskExecutorClassName,
			completed);
	}

	/**
	* Returns a range of all the background tasks where groupId = any &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T_C(long[] groupIds,
		java.lang.String name, java.lang.String taskExecutorClassName,
		boolean completed, int start, int end) {
		return getPersistence()
				   .findByG_N_T_C(groupIds, name, taskExecutorClassName,
			completed, start, end);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = any &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T_C(long[] groupIds,
		java.lang.String name, java.lang.String taskExecutorClassName,
		boolean completed, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence()
				   .findByG_N_T_C(groupIds, name, taskExecutorClassName,
			completed, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching background tasks
	*/
	public static List<BackgroundTask> findByG_N_T_C(long[] groupIds,
		java.lang.String name, java.lang.String taskExecutorClassName,
		boolean completed, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_N_T_C(groupIds, name, taskExecutorClassName,
			completed, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	*/
	public static void removeByG_N_T_C(long groupId, java.lang.String name,
		java.lang.String taskExecutorClassName, boolean completed) {
		getPersistence()
			.removeByG_N_T_C(groupId, name, taskExecutorClassName, completed);
	}

	/**
	* Returns the number of background tasks where groupId = &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @return the number of matching background tasks
	*/
	public static int countByG_N_T_C(long groupId, java.lang.String name,
		java.lang.String taskExecutorClassName, boolean completed) {
		return getPersistence()
				   .countByG_N_T_C(groupId, name, taskExecutorClassName,
			completed);
	}

	/**
	* Returns the number of background tasks where groupId = any &#63; and name = &#63; and taskExecutorClassName = &#63; and completed = &#63;.
	*
	* @param groupIds the group IDs
	* @param name the name
	* @param taskExecutorClassName the task executor class name
	* @param completed the completed
	* @return the number of matching background tasks
	*/
	public static int countByG_N_T_C(long[] groupIds, java.lang.String name,
		java.lang.String taskExecutorClassName, boolean completed) {
		return getPersistence()
				   .countByG_N_T_C(groupIds, name, taskExecutorClassName,
			completed);
	}

	/**
	* Caches the background task in the entity cache if it is enabled.
	*
	* @param backgroundTask the background task
	*/
	public static void cacheResult(BackgroundTask backgroundTask) {
		getPersistence().cacheResult(backgroundTask);
	}

	/**
	* Caches the background tasks in the entity cache if it is enabled.
	*
	* @param backgroundTasks the background tasks
	*/
	public static void cacheResult(List<BackgroundTask> backgroundTasks) {
		getPersistence().cacheResult(backgroundTasks);
	}

	/**
	* Creates a new background task with the primary key. Does not add the background task to the database.
	*
	* @param backgroundTaskId the primary key for the new background task
	* @return the new background task
	*/
	public static BackgroundTask create(long backgroundTaskId) {
		return getPersistence().create(backgroundTaskId);
	}

	/**
	* Removes the background task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param backgroundTaskId the primary key of the background task
	* @return the background task that was removed
	* @throws NoSuchBackgroundTaskException if a background task with the primary key could not be found
	*/
	public static BackgroundTask remove(long backgroundTaskId)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence().remove(backgroundTaskId);
	}

	public static BackgroundTask updateImpl(BackgroundTask backgroundTask) {
		return getPersistence().updateImpl(backgroundTask);
	}

	/**
	* Returns the background task with the primary key or throws a {@link NoSuchBackgroundTaskException} if it could not be found.
	*
	* @param backgroundTaskId the primary key of the background task
	* @return the background task
	* @throws NoSuchBackgroundTaskException if a background task with the primary key could not be found
	*/
	public static BackgroundTask findByPrimaryKey(long backgroundTaskId)
		throws com.liferay.portal.background.task.exception.NoSuchBackgroundTaskException {
		return getPersistence().findByPrimaryKey(backgroundTaskId);
	}

	/**
	* Returns the background task with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param backgroundTaskId the primary key of the background task
	* @return the background task, or <code>null</code> if a background task with the primary key could not be found
	*/
	public static BackgroundTask fetchByPrimaryKey(long backgroundTaskId) {
		return getPersistence().fetchByPrimaryKey(backgroundTaskId);
	}

	public static java.util.Map<java.io.Serializable, BackgroundTask> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the background tasks.
	*
	* @return the background tasks
	*/
	public static List<BackgroundTask> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the background tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of background tasks
	*/
	public static List<BackgroundTask> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the background tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of background tasks
	*/
	public static List<BackgroundTask> findAll(int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the background tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of background tasks
	*/
	public static List<BackgroundTask> findAll(int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the background tasks from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of background tasks.
	*
	* @return the number of background tasks
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static BackgroundTaskPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BackgroundTaskPersistence, BackgroundTaskPersistence> _serviceTracker =
		ServiceTrackerFactory.open(BackgroundTaskPersistence.class);
}