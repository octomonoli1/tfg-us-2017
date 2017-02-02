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

package com.liferay.dynamic.data.lists.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DDLRecordVersion. This utility wraps
 * {@link com.liferay.dynamic.data.lists.service.impl.DDLRecordVersionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordVersionLocalService
 * @see com.liferay.dynamic.data.lists.service.base.DDLRecordVersionLocalServiceBaseImpl
 * @see com.liferay.dynamic.data.lists.service.impl.DDLRecordVersionLocalServiceImpl
 * @generated
 */
@ProviderType
public class DDLRecordVersionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.dynamic.data.lists.service.impl.DDLRecordVersionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the d d l record version to the database. Also notifies the appropriate model listeners.
	*
	* @param ddlRecordVersion the d d l record version
	* @return the d d l record version that was added
	*/
	public static com.liferay.dynamic.data.lists.model.DDLRecordVersion addDDLRecordVersion(
		com.liferay.dynamic.data.lists.model.DDLRecordVersion ddlRecordVersion) {
		return getService().addDDLRecordVersion(ddlRecordVersion);
	}

	/**
	* Creates a new d d l record version with the primary key. Does not add the d d l record version to the database.
	*
	* @param recordVersionId the primary key for the new d d l record version
	* @return the new d d l record version
	*/
	public static com.liferay.dynamic.data.lists.model.DDLRecordVersion createDDLRecordVersion(
		long recordVersionId) {
		return getService().createDDLRecordVersion(recordVersionId);
	}

	/**
	* Deletes the d d l record version from the database. Also notifies the appropriate model listeners.
	*
	* @param ddlRecordVersion the d d l record version
	* @return the d d l record version that was removed
	*/
	public static com.liferay.dynamic.data.lists.model.DDLRecordVersion deleteDDLRecordVersion(
		com.liferay.dynamic.data.lists.model.DDLRecordVersion ddlRecordVersion) {
		return getService().deleteDDLRecordVersion(ddlRecordVersion);
	}

	/**
	* Deletes the d d l record version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param recordVersionId the primary key of the d d l record version
	* @return the d d l record version that was removed
	* @throws PortalException if a d d l record version with the primary key could not be found
	*/
	public static com.liferay.dynamic.data.lists.model.DDLRecordVersion deleteDDLRecordVersion(
		long recordVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDDLRecordVersion(recordVersionId);
	}

	public static com.liferay.dynamic.data.lists.model.DDLRecordVersion fetchDDLRecordVersion(
		long recordVersionId) {
		return getService().fetchDDLRecordVersion(recordVersionId);
	}

	/**
	* Returns the d d l record version with the primary key.
	*
	* @param recordVersionId the primary key of the d d l record version
	* @return the d d l record version
	* @throws PortalException if a d d l record version with the primary key could not be found
	*/
	public static com.liferay.dynamic.data.lists.model.DDLRecordVersion getDDLRecordVersion(
		long recordVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDDLRecordVersion(recordVersionId);
	}

	/**
	* Returns the record's latest record version.
	*
	* @param recordId the primary key of the record
	* @return the latest record version for the given record
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.dynamic.data.lists.model.DDLRecordVersion getLatestRecordVersion(
		long recordId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLatestRecordVersion(recordId);
	}

	/**
	* Returns the record version matching the record and version.
	*
	* @param recordId the primary key of the record
	* @param version the record version
	* @return the record version matching the record primary key and version
	* @throws PortalException if a matching record set could not be found
	*/
	public static com.liferay.dynamic.data.lists.model.DDLRecordVersion getRecordVersion(
		long recordId, java.lang.String version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRecordVersion(recordId, version);
	}

	/**
	* Returns the record version by its ID.
	*
	* @param recordVersionId the primary key of the record version
	* @return the record version with the ID
	* @throws PortalException if a matching record set could not be found
	*/
	public static com.liferay.dynamic.data.lists.model.DDLRecordVersion getRecordVersion(
		long recordVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRecordVersion(recordVersionId);
	}

	/**
	* Updates the d d l record version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ddlRecordVersion the d d l record version
	* @return the d d l record version that was updated
	*/
	public static com.liferay.dynamic.data.lists.model.DDLRecordVersion updateDDLRecordVersion(
		com.liferay.dynamic.data.lists.model.DDLRecordVersion ddlRecordVersion) {
		return getService().updateDDLRecordVersion(ddlRecordVersion);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Returns the number of d d l record versions.
	*
	* @return the number of d d l record versions
	*/
	public static int getDDLRecordVersionsCount() {
		return getService().getDDLRecordVersionsCount();
	}

	/**
	* Returns the number of record versions matching the record ID.
	*
	* @param recordId the primary key of the record
	* @return the number of matching record versions
	*/
	public static int getRecordVersionsCount(long recordId) {
		return getService().getRecordVersionsCount(recordId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.lists.model.impl.DDLRecordVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.lists.model.impl.DDLRecordVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the d d l record versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.lists.model.impl.DDLRecordVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of d d l record versions
	* @param end the upper bound of the range of d d l record versions (not inclusive)
	* @return the range of d d l record versions
	*/
	public static java.util.List<com.liferay.dynamic.data.lists.model.DDLRecordVersion> getDDLRecordVersions(
		int start, int end) {
		return getService().getDDLRecordVersions(start, end);
	}

	/**
	* Returns an ordered range of record versions matching the record's ID.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to <code>QueryUtil.ALL_POS</code> will return the
	* full result set.
	* </p>
	*
	* @param recordId the primary key of the record
	* @param start the lower bound of the range of record versions to return
	* @param end the upper bound of the range of record versions to return
	(not inclusive)
	* @param orderByComparator the comparator used to order the record
	versions
	* @return the range of matching record versions ordered by the comparator
	*/
	public static java.util.List<com.liferay.dynamic.data.lists.model.DDLRecordVersion> getRecordVersions(
		long recordId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.lists.model.DDLRecordVersion> orderByComparator) {
		return getService()
				   .getRecordVersions(recordId, start, end, orderByComparator);
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

	public static DDLRecordVersionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DDLRecordVersionLocalService, DDLRecordVersionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(DDLRecordVersionLocalService.class);
}