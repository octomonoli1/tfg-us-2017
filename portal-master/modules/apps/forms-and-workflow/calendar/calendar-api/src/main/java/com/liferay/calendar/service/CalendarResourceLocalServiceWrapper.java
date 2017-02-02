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

package com.liferay.calendar.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CalendarResourceLocalService}.
 *
 * @author Eduardo Lundgren
 * @see CalendarResourceLocalService
 * @generated
 */
@ProviderType
public class CalendarResourceLocalServiceWrapper
	implements CalendarResourceLocalService,
		ServiceWrapper<CalendarResourceLocalService> {
	public CalendarResourceLocalServiceWrapper(
		CalendarResourceLocalService calendarResourceLocalService) {
		_calendarResourceLocalService = calendarResourceLocalService;
	}

	/**
	* Adds the calendar resource to the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResource the calendar resource
	* @return the calendar resource that was added
	*/
	@Override
	public com.liferay.calendar.model.CalendarResource addCalendarResource(
		com.liferay.calendar.model.CalendarResource calendarResource) {
		return _calendarResourceLocalService.addCalendarResource(calendarResource);
	}

	@Override
	public com.liferay.calendar.model.CalendarResource addCalendarResource(
		long userId, long groupId, long classNameId, long classPK,
		java.lang.String classUuid, java.lang.String code,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarResourceLocalService.addCalendarResource(userId,
			groupId, classNameId, classPK, classUuid, code, nameMap,
			descriptionMap, active, serviceContext);
	}

	/**
	* Creates a new calendar resource with the primary key. Does not add the calendar resource to the database.
	*
	* @param calendarResourceId the primary key for the new calendar resource
	* @return the new calendar resource
	*/
	@Override
	public com.liferay.calendar.model.CalendarResource createCalendarResource(
		long calendarResourceId) {
		return _calendarResourceLocalService.createCalendarResource(calendarResourceId);
	}

	/**
	* Deletes the calendar resource from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResource the calendar resource
	* @return the calendar resource that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.calendar.model.CalendarResource deleteCalendarResource(
		com.liferay.calendar.model.CalendarResource calendarResource)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarResourceLocalService.deleteCalendarResource(calendarResource);
	}

	/**
	* Deletes the calendar resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @return the calendar resource that was removed
	* @throws PortalException if a calendar resource with the primary key could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarResource deleteCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarResourceLocalService.deleteCalendarResource(calendarResourceId);
	}

	@Override
	public com.liferay.calendar.model.CalendarResource fetchCalendarResource(
		long calendarResourceId) {
		return _calendarResourceLocalService.fetchCalendarResource(calendarResourceId);
	}

	@Override
	public com.liferay.calendar.model.CalendarResource fetchCalendarResource(
		long classNameId, long classPK) {
		return _calendarResourceLocalService.fetchCalendarResource(classNameId,
			classPK);
	}

	@Override
	public com.liferay.calendar.model.CalendarResource fetchCalendarResource(
		long groupId, java.lang.String code) {
		return _calendarResourceLocalService.fetchCalendarResource(groupId, code);
	}

	/**
	* Returns the calendar resource matching the UUID and group.
	*
	* @param uuid the calendar resource's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarResource fetchCalendarResourceByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _calendarResourceLocalService.fetchCalendarResourceByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the calendar resource with the primary key.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @return the calendar resource
	* @throws PortalException if a calendar resource with the primary key could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarResource getCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarResourceLocalService.getCalendarResource(calendarResourceId);
	}

	/**
	* Returns the calendar resource matching the UUID and group.
	*
	* @param uuid the calendar resource's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar resource
	* @throws PortalException if a matching calendar resource could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarResource getCalendarResourceByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarResourceLocalService.getCalendarResourceByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the calendar resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarResource the calendar resource
	* @return the calendar resource that was updated
	*/
	@Override
	public com.liferay.calendar.model.CalendarResource updateCalendarResource(
		com.liferay.calendar.model.CalendarResource calendarResource) {
		return _calendarResourceLocalService.updateCalendarResource(calendarResource);
	}

	@Override
	public com.liferay.calendar.model.CalendarResource updateCalendarResource(
		long calendarResourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarResourceLocalService.updateCalendarResource(calendarResourceId,
			nameMap, descriptionMap, active, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _calendarResourceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _calendarResourceLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _calendarResourceLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _calendarResourceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarResourceLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarResourceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of calendar resources.
	*
	* @return the number of calendar resources
	*/
	@Override
	public int getCalendarResourcesCount() {
		return _calendarResourceLocalService.getCalendarResourcesCount();
	}

	@Override
	public int searchCount(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String code, java.lang.String name,
		java.lang.String description, boolean active, boolean andOperator) {
		return _calendarResourceLocalService.searchCount(companyId, groupIds,
			classNameIds, code, name, description, active, andOperator);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String keywords, boolean active) {
		return _calendarResourceLocalService.searchCount(companyId, groupIds,
			classNameIds, keywords, active);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _calendarResourceLocalService.getOSGiServiceIdentifier();
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
		return _calendarResourceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _calendarResourceLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _calendarResourceLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the calendar resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of calendar resources
	*/
	@Override
	public java.util.List<com.liferay.calendar.model.CalendarResource> getCalendarResources(
		int start, int end) {
		return _calendarResourceLocalService.getCalendarResources(start, end);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarResource> getCalendarResources(
		long groupId) {
		return _calendarResourceLocalService.getCalendarResources(groupId);
	}

	/**
	* Returns all the calendar resources matching the UUID and company.
	*
	* @param uuid the UUID of the calendar resources
	* @param companyId the primary key of the company
	* @return the matching calendar resources, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.calendar.model.CalendarResource> getCalendarResourcesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _calendarResourceLocalService.getCalendarResourcesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of calendar resources matching the UUID and company.
	*
	* @param uuid the UUID of the calendar resources
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching calendar resources, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.calendar.model.CalendarResource> getCalendarResourcesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarResource> orderByComparator) {
		return _calendarResourceLocalService.getCalendarResourcesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarResource> search(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String code, java.lang.String name,
		java.lang.String description, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarResource> orderByComparator) {
		return _calendarResourceLocalService.search(companyId, groupIds,
			classNameIds, code, name, description, active, andOperator, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarResource> searchByKeywords(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String keywords, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarResource> orderByComparator) {
		return _calendarResourceLocalService.searchByKeywords(companyId,
			groupIds, classNameIds, keywords, active, andOperator, start, end,
			orderByComparator);
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
		return _calendarResourceLocalService.dynamicQueryCount(dynamicQuery);
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
		return _calendarResourceLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteCalendarResources(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_calendarResourceLocalService.deleteCalendarResources(groupId);
	}

	@Override
	public void updateAsset(long userId,
		com.liferay.calendar.model.CalendarResource calendarResource,
		long[] assetCategoryIds, java.lang.String[] assetTagNames,
		java.lang.Double priority)
		throws com.liferay.portal.kernel.exception.PortalException {
		_calendarResourceLocalService.updateAsset(userId, calendarResource,
			assetCategoryIds, assetTagNames, priority);
	}

	@Override
	public CalendarResourceLocalService getWrappedService() {
		return _calendarResourceLocalService;
	}

	@Override
	public void setWrappedService(
		CalendarResourceLocalService calendarResourceLocalService) {
		_calendarResourceLocalService = calendarResourceLocalService;
	}

	private CalendarResourceLocalService _calendarResourceLocalService;
}