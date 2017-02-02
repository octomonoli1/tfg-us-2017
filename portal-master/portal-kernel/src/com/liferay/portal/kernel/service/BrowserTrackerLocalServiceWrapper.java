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

package com.liferay.portal.kernel.service;

import aQute.bnd.annotation.ProviderType;

/**
 * Provides a wrapper for {@link BrowserTrackerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BrowserTrackerLocalService
 * @generated
 */
@ProviderType
public class BrowserTrackerLocalServiceWrapper
	implements BrowserTrackerLocalService,
		ServiceWrapper<BrowserTrackerLocalService> {
	public BrowserTrackerLocalServiceWrapper(
		BrowserTrackerLocalService browserTrackerLocalService) {
		_browserTrackerLocalService = browserTrackerLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _browserTrackerLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _browserTrackerLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _browserTrackerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Adds the browser tracker to the database. Also notifies the appropriate model listeners.
	*
	* @param browserTracker the browser tracker
	* @return the browser tracker that was added
	*/
	@Override
	public com.liferay.portal.kernel.model.BrowserTracker addBrowserTracker(
		com.liferay.portal.kernel.model.BrowserTracker browserTracker) {
		return _browserTrackerLocalService.addBrowserTracker(browserTracker);
	}

	/**
	* Creates a new browser tracker with the primary key. Does not add the browser tracker to the database.
	*
	* @param browserTrackerId the primary key for the new browser tracker
	* @return the new browser tracker
	*/
	@Override
	public com.liferay.portal.kernel.model.BrowserTracker createBrowserTracker(
		long browserTrackerId) {
		return _browserTrackerLocalService.createBrowserTracker(browserTrackerId);
	}

	/**
	* Deletes the browser tracker from the database. Also notifies the appropriate model listeners.
	*
	* @param browserTracker the browser tracker
	* @return the browser tracker that was removed
	*/
	@Override
	public com.liferay.portal.kernel.model.BrowserTracker deleteBrowserTracker(
		com.liferay.portal.kernel.model.BrowserTracker browserTracker) {
		return _browserTrackerLocalService.deleteBrowserTracker(browserTracker);
	}

	/**
	* Deletes the browser tracker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param browserTrackerId the primary key of the browser tracker
	* @return the browser tracker that was removed
	* @throws PortalException if a browser tracker with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.BrowserTracker deleteBrowserTracker(
		long browserTrackerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _browserTrackerLocalService.deleteBrowserTracker(browserTrackerId);
	}

	@Override
	public com.liferay.portal.kernel.model.BrowserTracker fetchBrowserTracker(
		long browserTrackerId) {
		return _browserTrackerLocalService.fetchBrowserTracker(browserTrackerId);
	}

	/**
	* Returns the browser tracker with the primary key.
	*
	* @param browserTrackerId the primary key of the browser tracker
	* @return the browser tracker
	* @throws PortalException if a browser tracker with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.BrowserTracker getBrowserTracker(
		long browserTrackerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _browserTrackerLocalService.getBrowserTracker(browserTrackerId);
	}

	@Override
	public com.liferay.portal.kernel.model.BrowserTracker getBrowserTracker(
		long userId, long browserKey) {
		return _browserTrackerLocalService.getBrowserTracker(userId, browserKey);
	}

	/**
	* Updates the browser tracker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param browserTracker the browser tracker
	* @return the browser tracker that was updated
	*/
	@Override
	public com.liferay.portal.kernel.model.BrowserTracker updateBrowserTracker(
		com.liferay.portal.kernel.model.BrowserTracker browserTracker) {
		return _browserTrackerLocalService.updateBrowserTracker(browserTracker);
	}

	@Override
	public com.liferay.portal.kernel.model.BrowserTracker updateBrowserTracker(
		long userId, long browserKey) {
		return _browserTrackerLocalService.updateBrowserTracker(userId,
			browserKey);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _browserTrackerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _browserTrackerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of browser trackers.
	*
	* @return the number of browser trackers
	*/
	@Override
	public int getBrowserTrackersCount() {
		return _browserTrackerLocalService.getBrowserTrackersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _browserTrackerLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _browserTrackerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BrowserTrackerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _browserTrackerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BrowserTrackerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _browserTrackerLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the browser trackers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BrowserTrackerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of browser trackers
	* @param end the upper bound of the range of browser trackers (not inclusive)
	* @return the range of browser trackers
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.BrowserTracker> getBrowserTrackers(
		int start, int end) {
		return _browserTrackerLocalService.getBrowserTrackers(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _browserTrackerLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _browserTrackerLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteUserBrowserTracker(long userId) {
		_browserTrackerLocalService.deleteUserBrowserTracker(userId);
	}

	@Override
	public BrowserTrackerLocalService getWrappedService() {
		return _browserTrackerLocalService;
	}

	@Override
	public void setWrappedService(
		BrowserTrackerLocalService browserTrackerLocalService) {
		_browserTrackerLocalService = browserTrackerLocalService;
	}

	private BrowserTrackerLocalService _browserTrackerLocalService;
}