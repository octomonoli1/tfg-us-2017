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

package com.liferay.portal.workflow.kaleo.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KaleoTimerInstanceTokenLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerInstanceTokenLocalService
 * @generated
 */
@ProviderType
public class KaleoTimerInstanceTokenLocalServiceWrapper
	implements KaleoTimerInstanceTokenLocalService,
		ServiceWrapper<KaleoTimerInstanceTokenLocalService> {
	public KaleoTimerInstanceTokenLocalServiceWrapper(
		KaleoTimerInstanceTokenLocalService kaleoTimerInstanceTokenLocalService) {
		_kaleoTimerInstanceTokenLocalService = kaleoTimerInstanceTokenLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _kaleoTimerInstanceTokenLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kaleoTimerInstanceTokenLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _kaleoTimerInstanceTokenLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerInstanceTokenLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerInstanceTokenLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the kaleo timer instance token to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerInstanceToken the kaleo timer instance token
	* @return the kaleo timer instance token that was added
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken addKaleoTimerInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		return _kaleoTimerInstanceTokenLocalService.addKaleoTimerInstanceToken(kaleoTimerInstanceToken);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken addKaleoTimerInstanceToken(
		long kaleoInstanceTokenId, long kaleoTaskInstanceTokenId,
		long kaleoTimerId, java.lang.String kaleoTimerName,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerInstanceTokenLocalService.addKaleoTimerInstanceToken(kaleoInstanceTokenId,
			kaleoTaskInstanceTokenId, kaleoTimerId, kaleoTimerName,
			workflowContext, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken completeKaleoTimerInstanceToken(
		long kaleoTimerInstanceTokenId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerInstanceTokenLocalService.completeKaleoTimerInstanceToken(kaleoTimerInstanceTokenId,
			serviceContext);
	}

	/**
	* Creates a new kaleo timer instance token with the primary key. Does not add the kaleo timer instance token to the database.
	*
	* @param kaleoTimerInstanceTokenId the primary key for the new kaleo timer instance token
	* @return the new kaleo timer instance token
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken createKaleoTimerInstanceToken(
		long kaleoTimerInstanceTokenId) {
		return _kaleoTimerInstanceTokenLocalService.createKaleoTimerInstanceToken(kaleoTimerInstanceTokenId);
	}

	/**
	* Deletes the kaleo timer instance token from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerInstanceToken the kaleo timer instance token
	* @return the kaleo timer instance token that was removed
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken deleteKaleoTimerInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		return _kaleoTimerInstanceTokenLocalService.deleteKaleoTimerInstanceToken(kaleoTimerInstanceToken);
	}

	/**
	* Deletes the kaleo timer instance token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	* @return the kaleo timer instance token that was removed
	* @throws PortalException if a kaleo timer instance token with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken deleteKaleoTimerInstanceToken(
		long kaleoTimerInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerInstanceTokenLocalService.deleteKaleoTimerInstanceToken(kaleoTimerInstanceTokenId);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken fetchKaleoTimerInstanceToken(
		long kaleoTimerInstanceTokenId) {
		return _kaleoTimerInstanceTokenLocalService.fetchKaleoTimerInstanceToken(kaleoTimerInstanceTokenId);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken getKaleoTimerInstanceToken(
		long kaleoInstanceTokenId, long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerInstanceTokenLocalService.getKaleoTimerInstanceToken(kaleoInstanceTokenId,
			kaleoTimerId);
	}

	/**
	* Returns the kaleo timer instance token with the primary key.
	*
	* @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	* @return the kaleo timer instance token
	* @throws PortalException if a kaleo timer instance token with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken getKaleoTimerInstanceToken(
		long kaleoTimerInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerInstanceTokenLocalService.getKaleoTimerInstanceToken(kaleoTimerInstanceTokenId);
	}

	/**
	* Updates the kaleo timer instance token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerInstanceToken the kaleo timer instance token
	* @return the kaleo timer instance token that was updated
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken updateKaleoTimerInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		return _kaleoTimerInstanceTokenLocalService.updateKaleoTimerInstanceToken(kaleoTimerInstanceToken);
	}

	/**
	* Returns the number of kaleo timer instance tokens.
	*
	* @return the number of kaleo timer instance tokens
	*/
	@Override
	public int getKaleoTimerInstanceTokensCount() {
		return _kaleoTimerInstanceTokenLocalService.getKaleoTimerInstanceTokensCount();
	}

	@Override
	public int getKaleoTimerInstanceTokensCount(long kaleoInstanceTokenId,
		boolean completed, boolean blocking,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _kaleoTimerInstanceTokenLocalService.getKaleoTimerInstanceTokensCount(kaleoInstanceTokenId,
			completed, blocking, serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _kaleoTimerInstanceTokenLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken> addKaleoTimerInstanceTokens(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.util.Collection<com.liferay.portal.workflow.kaleo.model.KaleoTimer> kaleoTimers,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTimerInstanceTokenLocalService.addKaleoTimerInstanceTokens(kaleoInstanceToken,
			kaleoTaskInstanceToken, kaleoTimers, workflowContext, serviceContext);
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
		return _kaleoTimerInstanceTokenLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _kaleoTimerInstanceTokenLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _kaleoTimerInstanceTokenLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the kaleo timer instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo timer instance tokens
	* @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	* @return the range of kaleo timer instance tokens
	*/
	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken> getKaleoTimerInstanceTokens(
		int start, int end) {
		return _kaleoTimerInstanceTokenLocalService.getKaleoTimerInstanceTokens(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken> getKaleoTimerInstanceTokens(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _kaleoTimerInstanceTokenLocalService.getKaleoTimerInstanceTokens(kaleoInstanceTokenId,
			completed, blocking, serviceContext);
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
		return _kaleoTimerInstanceTokenLocalService.dynamicQueryCount(dynamicQuery);
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
		return _kaleoTimerInstanceTokenLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void completeKaleoTimerInstanceTokens(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken> kaleoTimerInstanceTokens,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_kaleoTimerInstanceTokenLocalService.completeKaleoTimerInstanceTokens(kaleoTimerInstanceTokens,
			serviceContext);
	}

	@Override
	public void completeKaleoTimerInstanceTokens(long kaleoInstanceTokenId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_kaleoTimerInstanceTokenLocalService.completeKaleoTimerInstanceTokens(kaleoInstanceTokenId,
			serviceContext);
	}

	@Override
	public void deleteKaleoTimerInstanceToken(long kaleoInstanceTokenId,
		long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_kaleoTimerInstanceTokenLocalService.deleteKaleoTimerInstanceToken(kaleoInstanceTokenId,
			kaleoTimerId);
	}

	@Override
	public void deleteKaleoTimerInstanceTokens(long kaleoInstanceId) {
		_kaleoTimerInstanceTokenLocalService.deleteKaleoTimerInstanceTokens(kaleoInstanceId);
	}

	@Override
	public KaleoTimerInstanceTokenLocalService getWrappedService() {
		return _kaleoTimerInstanceTokenLocalService;
	}

	@Override
	public void setWrappedService(
		KaleoTimerInstanceTokenLocalService kaleoTimerInstanceTokenLocalService) {
		_kaleoTimerInstanceTokenLocalService = kaleoTimerInstanceTokenLocalService;
	}

	private KaleoTimerInstanceTokenLocalService _kaleoTimerInstanceTokenLocalService;
}