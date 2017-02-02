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

package com.liferay.ratings.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RatingsStatsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RatingsStatsLocalService
 * @generated
 */
@ProviderType
public class RatingsStatsLocalServiceWrapper implements RatingsStatsLocalService,
	ServiceWrapper<RatingsStatsLocalService> {
	public RatingsStatsLocalServiceWrapper(
		RatingsStatsLocalService ratingsStatsLocalService) {
		_ratingsStatsLocalService = ratingsStatsLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ratingsStatsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ratingsStatsLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ratingsStatsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ratingsStatsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ratingsStatsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the ratings stats to the database. Also notifies the appropriate model listeners.
	*
	* @param ratingsStats the ratings stats
	* @return the ratings stats that was added
	*/
	@Override
	public com.liferay.ratings.kernel.model.RatingsStats addRatingsStats(
		com.liferay.ratings.kernel.model.RatingsStats ratingsStats) {
		return _ratingsStatsLocalService.addRatingsStats(ratingsStats);
	}

	@Override
	public com.liferay.ratings.kernel.model.RatingsStats addStats(
		long classNameId, long classPK) {
		return _ratingsStatsLocalService.addStats(classNameId, classPK);
	}

	/**
	* Creates a new ratings stats with the primary key. Does not add the ratings stats to the database.
	*
	* @param statsId the primary key for the new ratings stats
	* @return the new ratings stats
	*/
	@Override
	public com.liferay.ratings.kernel.model.RatingsStats createRatingsStats(
		long statsId) {
		return _ratingsStatsLocalService.createRatingsStats(statsId);
	}

	/**
	* Deletes the ratings stats from the database. Also notifies the appropriate model listeners.
	*
	* @param ratingsStats the ratings stats
	* @return the ratings stats that was removed
	*/
	@Override
	public com.liferay.ratings.kernel.model.RatingsStats deleteRatingsStats(
		com.liferay.ratings.kernel.model.RatingsStats ratingsStats) {
		return _ratingsStatsLocalService.deleteRatingsStats(ratingsStats);
	}

	/**
	* Deletes the ratings stats with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param statsId the primary key of the ratings stats
	* @return the ratings stats that was removed
	* @throws PortalException if a ratings stats with the primary key could not be found
	*/
	@Override
	public com.liferay.ratings.kernel.model.RatingsStats deleteRatingsStats(
		long statsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ratingsStatsLocalService.deleteRatingsStats(statsId);
	}

	@Override
	public com.liferay.ratings.kernel.model.RatingsStats fetchRatingsStats(
		long statsId) {
		return _ratingsStatsLocalService.fetchRatingsStats(statsId);
	}

	@Override
	public com.liferay.ratings.kernel.model.RatingsStats fetchStats(
		java.lang.String className, long classPK) {
		return _ratingsStatsLocalService.fetchStats(className, classPK);
	}

	/**
	* Returns the ratings stats with the primary key.
	*
	* @param statsId the primary key of the ratings stats
	* @return the ratings stats
	* @throws PortalException if a ratings stats with the primary key could not be found
	*/
	@Override
	public com.liferay.ratings.kernel.model.RatingsStats getRatingsStats(
		long statsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ratingsStatsLocalService.getRatingsStats(statsId);
	}

	@Override
	public com.liferay.ratings.kernel.model.RatingsStats getStats(
		java.lang.String className, long classPK) {
		return _ratingsStatsLocalService.getStats(className, classPK);
	}

	@Override
	public com.liferay.ratings.kernel.model.RatingsStats getStats(long statsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ratingsStatsLocalService.getStats(statsId);
	}

	/**
	* Updates the ratings stats in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ratingsStats the ratings stats
	* @return the ratings stats that was updated
	*/
	@Override
	public com.liferay.ratings.kernel.model.RatingsStats updateRatingsStats(
		com.liferay.ratings.kernel.model.RatingsStats ratingsStats) {
		return _ratingsStatsLocalService.updateRatingsStats(ratingsStats);
	}

	/**
	* Returns the number of ratings statses.
	*
	* @return the number of ratings statses
	*/
	@Override
	public int getRatingsStatsesCount() {
		return _ratingsStatsLocalService.getRatingsStatsesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ratingsStatsLocalService.getOSGiServiceIdentifier();
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
		return _ratingsStatsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.ratings.model.impl.RatingsStatsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ratingsStatsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.ratings.model.impl.RatingsStatsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ratingsStatsLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the ratings statses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.ratings.model.impl.RatingsStatsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ratings statses
	* @param end the upper bound of the range of ratings statses (not inclusive)
	* @return the range of ratings statses
	*/
	@Override
	public java.util.List<com.liferay.ratings.kernel.model.RatingsStats> getRatingsStatses(
		int start, int end) {
		return _ratingsStatsLocalService.getRatingsStatses(start, end);
	}

	@Override
	public java.util.List<com.liferay.ratings.kernel.model.RatingsStats> getStats(
		java.lang.String className, java.util.List<java.lang.Long> classPKs) {
		return _ratingsStatsLocalService.getStats(className, classPKs);
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
		return _ratingsStatsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ratingsStatsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteStats(java.lang.String className, long classPK) {
		_ratingsStatsLocalService.deleteStats(className, classPK);
	}

	@Override
	public RatingsStatsLocalService getWrappedService() {
		return _ratingsStatsLocalService;
	}

	@Override
	public void setWrappedService(
		RatingsStatsLocalService ratingsStatsLocalService) {
		_ratingsStatsLocalService = ratingsStatsLocalService;
	}

	private RatingsStatsLocalService _ratingsStatsLocalService;
}