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
 * Provides a wrapper for {@link SystemEventLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SystemEventLocalService
 * @generated
 */
@ProviderType
public class SystemEventLocalServiceWrapper implements SystemEventLocalService,
	ServiceWrapper<SystemEventLocalService> {
	public SystemEventLocalServiceWrapper(
		SystemEventLocalService systemEventLocalService) {
		_systemEventLocalService = systemEventLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _systemEventLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _systemEventLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _systemEventLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _systemEventLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _systemEventLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the system event to the database. Also notifies the appropriate model listeners.
	*
	* @param systemEvent the system event
	* @return the system event that was added
	*/
	@Override
	public com.liferay.portal.kernel.model.SystemEvent addSystemEvent(
		com.liferay.portal.kernel.model.SystemEvent systemEvent) {
		return _systemEventLocalService.addSystemEvent(systemEvent);
	}

	@Override
	public com.liferay.portal.kernel.model.SystemEvent addSystemEvent(
		long companyId, java.lang.String className, long classPK,
		java.lang.String classUuid, java.lang.String referrerClassName,
		int type, java.lang.String extraData)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _systemEventLocalService.addSystemEvent(companyId, className,
			classPK, classUuid, referrerClassName, type, extraData);
	}

	@Override
	public com.liferay.portal.kernel.model.SystemEvent addSystemEvent(
		long userId, long groupId, java.lang.String className, long classPK,
		java.lang.String classUuid, java.lang.String referrerClassName,
		int type, java.lang.String extraData)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _systemEventLocalService.addSystemEvent(userId, groupId,
			className, classPK, classUuid, referrerClassName, type, extraData);
	}

	/**
	* Creates a new system event with the primary key. Does not add the system event to the database.
	*
	* @param systemEventId the primary key for the new system event
	* @return the new system event
	*/
	@Override
	public com.liferay.portal.kernel.model.SystemEvent createSystemEvent(
		long systemEventId) {
		return _systemEventLocalService.createSystemEvent(systemEventId);
	}

	/**
	* Deletes the system event from the database. Also notifies the appropriate model listeners.
	*
	* @param systemEvent the system event
	* @return the system event that was removed
	*/
	@Override
	public com.liferay.portal.kernel.model.SystemEvent deleteSystemEvent(
		com.liferay.portal.kernel.model.SystemEvent systemEvent) {
		return _systemEventLocalService.deleteSystemEvent(systemEvent);
	}

	/**
	* Deletes the system event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param systemEventId the primary key of the system event
	* @return the system event that was removed
	* @throws PortalException if a system event with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.SystemEvent deleteSystemEvent(
		long systemEventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _systemEventLocalService.deleteSystemEvent(systemEventId);
	}

	@Override
	public com.liferay.portal.kernel.model.SystemEvent fetchSystemEvent(
		long groupId, long classNameId, long classPK, int type) {
		return _systemEventLocalService.fetchSystemEvent(groupId, classNameId,
			classPK, type);
	}

	@Override
	public com.liferay.portal.kernel.model.SystemEvent fetchSystemEvent(
		long systemEventId) {
		return _systemEventLocalService.fetchSystemEvent(systemEventId);
	}

	/**
	* Returns the system event with the primary key.
	*
	* @param systemEventId the primary key of the system event
	* @return the system event
	* @throws PortalException if a system event with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.SystemEvent getSystemEvent(
		long systemEventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _systemEventLocalService.getSystemEvent(systemEventId);
	}

	/**
	* Updates the system event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param systemEvent the system event
	* @return the system event that was updated
	*/
	@Override
	public com.liferay.portal.kernel.model.SystemEvent updateSystemEvent(
		com.liferay.portal.kernel.model.SystemEvent systemEvent) {
		return _systemEventLocalService.updateSystemEvent(systemEvent);
	}

	/**
	* Returns the number of system events.
	*
	* @return the number of system events
	*/
	@Override
	public int getSystemEventsCount() {
		return _systemEventLocalService.getSystemEventsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _systemEventLocalService.getOSGiServiceIdentifier();
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
		return _systemEventLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.SystemEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _systemEventLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.SystemEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _systemEventLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the system events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.SystemEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of system events
	* @param end the upper bound of the range of system events (not inclusive)
	* @return the range of system events
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.SystemEvent> getSystemEvents(
		int start, int end) {
		return _systemEventLocalService.getSystemEvents(start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.SystemEvent> getSystemEvents(
		long groupId, long classNameId, long classPK) {
		return _systemEventLocalService.getSystemEvents(groupId, classNameId,
			classPK);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.SystemEvent> getSystemEvents(
		long groupId, long classNameId, long classPK, int type) {
		return _systemEventLocalService.getSystemEvents(groupId, classNameId,
			classPK, type);
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
		return _systemEventLocalService.dynamicQueryCount(dynamicQuery);
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
		return _systemEventLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteSystemEvents(long groupId) {
		_systemEventLocalService.deleteSystemEvents(groupId);
	}

	@Override
	public void deleteSystemEvents(long groupId, long systemEventSetKey) {
		_systemEventLocalService.deleteSystemEvents(groupId, systemEventSetKey);
	}

	@Override
	public SystemEventLocalService getWrappedService() {
		return _systemEventLocalService;
	}

	@Override
	public void setWrappedService(
		SystemEventLocalService systemEventLocalService) {
		_systemEventLocalService = systemEventLocalService;
	}

	private SystemEventLocalService _systemEventLocalService;
}