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

package com.liferay.dynamic.data.mapping.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DDMStructureLayout. This utility wraps
 * {@link com.liferay.dynamic.data.mapping.service.impl.DDMStructureLayoutLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructureLayoutLocalService
 * @see com.liferay.dynamic.data.mapping.service.base.DDMStructureLayoutLocalServiceBaseImpl
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMStructureLayoutLocalServiceImpl
 * @generated
 */
@ProviderType
public class DDMStructureLayoutLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.dynamic.data.mapping.service.impl.DDMStructureLayoutLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.dynamic.data.mapping.model.DDMFormLayout getStructureLayoutDDMFormLayout(
		com.liferay.dynamic.data.mapping.model.DDMStructureLayout structureLayout)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStructureLayoutDDMFormLayout(structureLayout);
	}

	/**
	* Adds the d d m structure layout to the database. Also notifies the appropriate model listeners.
	*
	* @param ddmStructureLayout the d d m structure layout
	* @return the d d m structure layout that was added
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLayout addDDMStructureLayout(
		com.liferay.dynamic.data.mapping.model.DDMStructureLayout ddmStructureLayout) {
		return getService().addDDMStructureLayout(ddmStructureLayout);
	}

	public static com.liferay.dynamic.data.mapping.model.DDMStructureLayout addStructureLayout(
		long userId, long groupId, long structureVersionId,
		com.liferay.dynamic.data.mapping.model.DDMFormLayout ddmFormLayout,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addStructureLayout(userId, groupId, structureVersionId,
			ddmFormLayout, serviceContext);
	}

	/**
	* Creates a new d d m structure layout with the primary key. Does not add the d d m structure layout to the database.
	*
	* @param structureLayoutId the primary key for the new d d m structure layout
	* @return the new d d m structure layout
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLayout createDDMStructureLayout(
		long structureLayoutId) {
		return getService().createDDMStructureLayout(structureLayoutId);
	}

	/**
	* Deletes the d d m structure layout from the database. Also notifies the appropriate model listeners.
	*
	* @param ddmStructureLayout the d d m structure layout
	* @return the d d m structure layout that was removed
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLayout deleteDDMStructureLayout(
		com.liferay.dynamic.data.mapping.model.DDMStructureLayout ddmStructureLayout) {
		return getService().deleteDDMStructureLayout(ddmStructureLayout);
	}

	/**
	* Deletes the d d m structure layout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param structureLayoutId the primary key of the d d m structure layout
	* @return the d d m structure layout that was removed
	* @throws PortalException if a d d m structure layout with the primary key could not be found
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLayout deleteDDMStructureLayout(
		long structureLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDDMStructureLayout(structureLayoutId);
	}

	public static com.liferay.dynamic.data.mapping.model.DDMStructureLayout fetchDDMStructureLayout(
		long structureLayoutId) {
		return getService().fetchDDMStructureLayout(structureLayoutId);
	}

	/**
	* Returns the d d m structure layout matching the UUID and group.
	*
	* @param uuid the d d m structure layout's UUID
	* @param groupId the primary key of the group
	* @return the matching d d m structure layout, or <code>null</code> if a matching d d m structure layout could not be found
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLayout fetchDDMStructureLayoutByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService()
				   .fetchDDMStructureLayoutByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the d d m structure layout with the primary key.
	*
	* @param structureLayoutId the primary key of the d d m structure layout
	* @return the d d m structure layout
	* @throws PortalException if a d d m structure layout with the primary key could not be found
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLayout getDDMStructureLayout(
		long structureLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDDMStructureLayout(structureLayoutId);
	}

	/**
	* Returns the d d m structure layout matching the UUID and group.
	*
	* @param uuid the d d m structure layout's UUID
	* @param groupId the primary key of the group
	* @return the matching d d m structure layout
	* @throws PortalException if a matching d d m structure layout could not be found
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLayout getDDMStructureLayoutByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDDMStructureLayoutByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.dynamic.data.mapping.model.DDMStructureLayout getStructureLayout(
		long structureLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStructureLayout(structureLayoutId);
	}

	public static com.liferay.dynamic.data.mapping.model.DDMStructureLayout getStructureLayoutByStructureVersionId(
		long structureVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getStructureLayoutByStructureVersionId(structureVersionId);
	}

	/**
	* Updates the d d m structure layout in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ddmStructureLayout the d d m structure layout
	* @return the d d m structure layout that was updated
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLayout updateDDMStructureLayout(
		com.liferay.dynamic.data.mapping.model.DDMStructureLayout ddmStructureLayout) {
		return getService().updateDDMStructureLayout(ddmStructureLayout);
	}

	public static com.liferay.dynamic.data.mapping.model.DDMStructureLayout updateStructureLayout(
		long structureLayoutId,
		com.liferay.dynamic.data.mapping.model.DDMFormLayout ddmFormLayout,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStructureLayout(structureLayoutId, ddmFormLayout,
			serviceContext);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of d d m structure layouts.
	*
	* @return the number of d d m structure layouts
	*/
	public static int getDDMStructureLayoutsCount() {
		return getService().getDDMStructureLayoutsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMStructureLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMStructureLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the d d m structure layouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMStructureLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of d d m structure layouts
	* @param end the upper bound of the range of d d m structure layouts (not inclusive)
	* @return the range of d d m structure layouts
	*/
	public static java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructureLayout> getDDMStructureLayouts(
		int start, int end) {
		return getService().getDDMStructureLayouts(start, end);
	}

	/**
	* Returns all the d d m structure layouts matching the UUID and company.
	*
	* @param uuid the UUID of the d d m structure layouts
	* @param companyId the primary key of the company
	* @return the matching d d m structure layouts, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructureLayout> getDDMStructureLayoutsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getDDMStructureLayoutsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of d d m structure layouts matching the UUID and company.
	*
	* @param uuid the UUID of the d d m structure layouts
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of d d m structure layouts
	* @param end the upper bound of the range of d d m structure layouts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching d d m structure layouts, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructureLayout> getDDMStructureLayoutsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.mapping.model.DDMStructureLayout> orderByComparator) {
		return getService()
				   .getDDMStructureLayoutsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void deleteStructureLayout(
		com.liferay.dynamic.data.mapping.model.DDMStructureLayout structureLayout) {
		getService().deleteStructureLayout(structureLayout);
	}

	public static void deleteStructureLayout(long structureLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteStructureLayout(structureLayoutId);
	}

	public static DDMStructureLayoutLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DDMStructureLayoutLocalService, DDMStructureLayoutLocalService> _serviceTracker =
		ServiceTrackerFactory.open(DDMStructureLayoutLocalService.class);
}