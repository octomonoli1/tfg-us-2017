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
 * Provides a wrapper for {@link RecentLayoutRevisionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutRevisionLocalService
 * @generated
 */
@ProviderType
public class RecentLayoutRevisionLocalServiceWrapper
	implements RecentLayoutRevisionLocalService,
		ServiceWrapper<RecentLayoutRevisionLocalService> {
	public RecentLayoutRevisionLocalServiceWrapper(
		RecentLayoutRevisionLocalService recentLayoutRevisionLocalService) {
		_recentLayoutRevisionLocalService = recentLayoutRevisionLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _recentLayoutRevisionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _recentLayoutRevisionLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _recentLayoutRevisionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _recentLayoutRevisionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _recentLayoutRevisionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the recent layout revision to the database. Also notifies the appropriate model listeners.
	*
	* @param recentLayoutRevision the recent layout revision
	* @return the recent layout revision that was added
	*/
	@Override
	public com.liferay.portal.kernel.model.RecentLayoutRevision addRecentLayoutRevision(
		com.liferay.portal.kernel.model.RecentLayoutRevision recentLayoutRevision) {
		return _recentLayoutRevisionLocalService.addRecentLayoutRevision(recentLayoutRevision);
	}

	@Override
	public com.liferay.portal.kernel.model.RecentLayoutRevision addRecentLayoutRevision(
		long userId, long layoutRevisionId, long layoutSetBranchId, long plid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _recentLayoutRevisionLocalService.addRecentLayoutRevision(userId,
			layoutRevisionId, layoutSetBranchId, plid);
	}

	/**
	* Creates a new recent layout revision with the primary key. Does not add the recent layout revision to the database.
	*
	* @param recentLayoutRevisionId the primary key for the new recent layout revision
	* @return the new recent layout revision
	*/
	@Override
	public com.liferay.portal.kernel.model.RecentLayoutRevision createRecentLayoutRevision(
		long recentLayoutRevisionId) {
		return _recentLayoutRevisionLocalService.createRecentLayoutRevision(recentLayoutRevisionId);
	}

	/**
	* Deletes the recent layout revision from the database. Also notifies the appropriate model listeners.
	*
	* @param recentLayoutRevision the recent layout revision
	* @return the recent layout revision that was removed
	*/
	@Override
	public com.liferay.portal.kernel.model.RecentLayoutRevision deleteRecentLayoutRevision(
		com.liferay.portal.kernel.model.RecentLayoutRevision recentLayoutRevision) {
		return _recentLayoutRevisionLocalService.deleteRecentLayoutRevision(recentLayoutRevision);
	}

	/**
	* Deletes the recent layout revision with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param recentLayoutRevisionId the primary key of the recent layout revision
	* @return the recent layout revision that was removed
	* @throws PortalException if a recent layout revision with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.RecentLayoutRevision deleteRecentLayoutRevision(
		long recentLayoutRevisionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _recentLayoutRevisionLocalService.deleteRecentLayoutRevision(recentLayoutRevisionId);
	}

	@Override
	public com.liferay.portal.kernel.model.RecentLayoutRevision fetchRecentLayoutRevision(
		long recentLayoutRevisionId) {
		return _recentLayoutRevisionLocalService.fetchRecentLayoutRevision(recentLayoutRevisionId);
	}

	@Override
	public com.liferay.portal.kernel.model.RecentLayoutRevision fetchRecentLayoutRevision(
		long userId, long layoutSetBranchId, long plid) {
		return _recentLayoutRevisionLocalService.fetchRecentLayoutRevision(userId,
			layoutSetBranchId, plid);
	}

	/**
	* Returns the recent layout revision with the primary key.
	*
	* @param recentLayoutRevisionId the primary key of the recent layout revision
	* @return the recent layout revision
	* @throws PortalException if a recent layout revision with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.RecentLayoutRevision getRecentLayoutRevision(
		long recentLayoutRevisionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _recentLayoutRevisionLocalService.getRecentLayoutRevision(recentLayoutRevisionId);
	}

	/**
	* Updates the recent layout revision in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param recentLayoutRevision the recent layout revision
	* @return the recent layout revision that was updated
	*/
	@Override
	public com.liferay.portal.kernel.model.RecentLayoutRevision updateRecentLayoutRevision(
		com.liferay.portal.kernel.model.RecentLayoutRevision recentLayoutRevision) {
		return _recentLayoutRevisionLocalService.updateRecentLayoutRevision(recentLayoutRevision);
	}

	/**
	* Returns the number of recent layout revisions.
	*
	* @return the number of recent layout revisions
	*/
	@Override
	public int getRecentLayoutRevisionsCount() {
		return _recentLayoutRevisionLocalService.getRecentLayoutRevisionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _recentLayoutRevisionLocalService.getOSGiServiceIdentifier();
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
		return _recentLayoutRevisionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RecentLayoutRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _recentLayoutRevisionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RecentLayoutRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _recentLayoutRevisionLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the recent layout revisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RecentLayoutRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of recent layout revisions
	* @param end the upper bound of the range of recent layout revisions (not inclusive)
	* @return the range of recent layout revisions
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.RecentLayoutRevision> getRecentLayoutRevisions(
		int start, int end) {
		return _recentLayoutRevisionLocalService.getRecentLayoutRevisions(start,
			end);
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
		return _recentLayoutRevisionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _recentLayoutRevisionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteRecentLayoutRevisions(long layoutRevisionId) {
		_recentLayoutRevisionLocalService.deleteRecentLayoutRevisions(layoutRevisionId);
	}

	@Override
	public void deleteUserRecentLayoutRevisions(long userId) {
		_recentLayoutRevisionLocalService.deleteUserRecentLayoutRevisions(userId);
	}

	@Override
	public RecentLayoutRevisionLocalService getWrappedService() {
		return _recentLayoutRevisionLocalService;
	}

	@Override
	public void setWrappedService(
		RecentLayoutRevisionLocalService recentLayoutRevisionLocalService) {
		_recentLayoutRevisionLocalService = recentLayoutRevisionLocalService;
	}

	private RecentLayoutRevisionLocalService _recentLayoutRevisionLocalService;
}