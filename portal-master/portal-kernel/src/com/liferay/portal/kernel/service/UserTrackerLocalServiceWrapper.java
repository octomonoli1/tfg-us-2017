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
 * Provides a wrapper for {@link UserTrackerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserTrackerLocalService
 * @generated
 */
@ProviderType
public class UserTrackerLocalServiceWrapper implements UserTrackerLocalService,
	ServiceWrapper<UserTrackerLocalService> {
	public UserTrackerLocalServiceWrapper(
		UserTrackerLocalService userTrackerLocalService) {
		_userTrackerLocalService = userTrackerLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _userTrackerLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userTrackerLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _userTrackerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userTrackerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userTrackerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the user tracker to the database. Also notifies the appropriate model listeners.
	*
	* @param userTracker the user tracker
	* @return the user tracker that was added
	*/
	@Override
	public com.liferay.portal.kernel.model.UserTracker addUserTracker(
		com.liferay.portal.kernel.model.UserTracker userTracker) {
		return _userTrackerLocalService.addUserTracker(userTracker);
	}

	@Override
	public com.liferay.portal.kernel.model.UserTracker addUserTracker(
		long companyId, long userId, java.util.Date modifiedDate,
		java.lang.String sessionId, java.lang.String remoteAddr,
		java.lang.String remoteHost, java.lang.String userAgent,
		java.util.List<com.liferay.portal.kernel.model.UserTrackerPath> userTrackerPaths) {
		return _userTrackerLocalService.addUserTracker(companyId, userId,
			modifiedDate, sessionId, remoteAddr, remoteHost, userAgent,
			userTrackerPaths);
	}

	/**
	* Creates a new user tracker with the primary key. Does not add the user tracker to the database.
	*
	* @param userTrackerId the primary key for the new user tracker
	* @return the new user tracker
	*/
	@Override
	public com.liferay.portal.kernel.model.UserTracker createUserTracker(
		long userTrackerId) {
		return _userTrackerLocalService.createUserTracker(userTrackerId);
	}

	/**
	* Deletes the user tracker from the database. Also notifies the appropriate model listeners.
	*
	* @param userTracker the user tracker
	* @return the user tracker that was removed
	*/
	@Override
	public com.liferay.portal.kernel.model.UserTracker deleteUserTracker(
		com.liferay.portal.kernel.model.UserTracker userTracker) {
		return _userTrackerLocalService.deleteUserTracker(userTracker);
	}

	/**
	* Deletes the user tracker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userTrackerId the primary key of the user tracker
	* @return the user tracker that was removed
	* @throws PortalException if a user tracker with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.UserTracker deleteUserTracker(
		long userTrackerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userTrackerLocalService.deleteUserTracker(userTrackerId);
	}

	@Override
	public com.liferay.portal.kernel.model.UserTracker fetchUserTracker(
		long userTrackerId) {
		return _userTrackerLocalService.fetchUserTracker(userTrackerId);
	}

	/**
	* Returns the user tracker with the primary key.
	*
	* @param userTrackerId the primary key of the user tracker
	* @return the user tracker
	* @throws PortalException if a user tracker with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.UserTracker getUserTracker(
		long userTrackerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userTrackerLocalService.getUserTracker(userTrackerId);
	}

	/**
	* Updates the user tracker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userTracker the user tracker
	* @return the user tracker that was updated
	*/
	@Override
	public com.liferay.portal.kernel.model.UserTracker updateUserTracker(
		com.liferay.portal.kernel.model.UserTracker userTracker) {
		return _userTrackerLocalService.updateUserTracker(userTracker);
	}

	/**
	* Returns the number of user trackers.
	*
	* @return the number of user trackers
	*/
	@Override
	public int getUserTrackersCount() {
		return _userTrackerLocalService.getUserTrackersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _userTrackerLocalService.getOSGiServiceIdentifier();
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
		return _userTrackerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserTrackerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _userTrackerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserTrackerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _userTrackerLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the user trackers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserTrackerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user trackers
	* @param end the upper bound of the range of user trackers (not inclusive)
	* @return the range of user trackers
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.UserTracker> getUserTrackers(
		int start, int end) {
		return _userTrackerLocalService.getUserTrackers(start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.UserTracker> getUserTrackers(
		long companyId, int start, int end) {
		return _userTrackerLocalService.getUserTrackers(companyId, start, end);
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
		return _userTrackerLocalService.dynamicQueryCount(dynamicQuery);
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
		return _userTrackerLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public UserTrackerLocalService getWrappedService() {
		return _userTrackerLocalService;
	}

	@Override
	public void setWrappedService(
		UserTrackerLocalService userTrackerLocalService) {
		_userTrackerLocalService = userTrackerLocalService;
	}

	private UserTrackerLocalService _userTrackerLocalService;
}