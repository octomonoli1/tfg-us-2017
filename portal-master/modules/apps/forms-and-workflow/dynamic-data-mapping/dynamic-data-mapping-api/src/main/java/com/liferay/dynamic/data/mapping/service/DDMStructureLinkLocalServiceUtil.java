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
 * Provides the local service utility for DDMStructureLink. This utility wraps
 * {@link com.liferay.dynamic.data.mapping.service.impl.DDMStructureLinkLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructureLinkLocalService
 * @see com.liferay.dynamic.data.mapping.service.base.DDMStructureLinkLocalServiceBaseImpl
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMStructureLinkLocalServiceImpl
 * @generated
 */
@ProviderType
public class DDMStructureLinkLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.dynamic.data.mapping.service.impl.DDMStructureLinkLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the d d m structure link to the database. Also notifies the appropriate model listeners.
	*
	* @param ddmStructureLink the d d m structure link
	* @return the d d m structure link that was added
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLink addDDMStructureLink(
		com.liferay.dynamic.data.mapping.model.DDMStructureLink ddmStructureLink) {
		return getService().addDDMStructureLink(ddmStructureLink);
	}

	public static com.liferay.dynamic.data.mapping.model.DDMStructureLink addStructureLink(
		long classNameId, long classPK, long structureId) {
		return getService().addStructureLink(classNameId, classPK, structureId);
	}

	/**
	* Creates a new d d m structure link with the primary key. Does not add the d d m structure link to the database.
	*
	* @param structureLinkId the primary key for the new d d m structure link
	* @return the new d d m structure link
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLink createDDMStructureLink(
		long structureLinkId) {
		return getService().createDDMStructureLink(structureLinkId);
	}

	/**
	* Deletes the d d m structure link from the database. Also notifies the appropriate model listeners.
	*
	* @param ddmStructureLink the d d m structure link
	* @return the d d m structure link that was removed
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLink deleteDDMStructureLink(
		com.liferay.dynamic.data.mapping.model.DDMStructureLink ddmStructureLink) {
		return getService().deleteDDMStructureLink(ddmStructureLink);
	}

	/**
	* Deletes the d d m structure link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param structureLinkId the primary key of the d d m structure link
	* @return the d d m structure link that was removed
	* @throws PortalException if a d d m structure link with the primary key could not be found
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLink deleteDDMStructureLink(
		long structureLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDDMStructureLink(structureLinkId);
	}

	public static com.liferay.dynamic.data.mapping.model.DDMStructureLink fetchDDMStructureLink(
		long structureLinkId) {
		return getService().fetchDDMStructureLink(structureLinkId);
	}

	/**
	* Returns the d d m structure link with the primary key.
	*
	* @param structureLinkId the primary key of the d d m structure link
	* @return the d d m structure link
	* @throws PortalException if a d d m structure link with the primary key could not be found
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLink getDDMStructureLink(
		long structureLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDDMStructureLink(structureLinkId);
	}

	public static com.liferay.dynamic.data.mapping.model.DDMStructureLink getStructureLink(
		long structureLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStructureLink(structureLinkId);
	}

	public static com.liferay.dynamic.data.mapping.model.DDMStructureLink getUniqueStructureLink(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUniqueStructureLink(classNameId, classPK);
	}

	/**
	* Updates the d d m structure link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ddmStructureLink the d d m structure link
	* @return the d d m structure link that was updated
	*/
	public static com.liferay.dynamic.data.mapping.model.DDMStructureLink updateDDMStructureLink(
		com.liferay.dynamic.data.mapping.model.DDMStructureLink ddmStructureLink) {
		return getService().updateDDMStructureLink(ddmStructureLink);
	}

	public static com.liferay.dynamic.data.mapping.model.DDMStructureLink updateStructureLink(
		long structureLinkId, long classNameId, long classPK, long structureId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStructureLink(structureLinkId, classNameId, classPK,
			structureId);
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
	* Returns the number of d d m structure links.
	*
	* @return the number of d d m structure links
	*/
	public static int getDDMStructureLinksCount() {
		return getService().getDDMStructureLinksCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMStructureLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMStructureLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructureLink> getClassNameStructureLinks(
		long classNameId) {
		return getService().getClassNameStructureLinks(classNameId);
	}

	/**
	* Returns a range of all the d d m structure links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMStructureLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of d d m structure links
	* @param end the upper bound of the range of d d m structure links (not inclusive)
	* @return the range of d d m structure links
	*/
	public static java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructureLink> getDDMStructureLinks(
		int start, int end) {
		return getService().getDDMStructureLinks(start, end);
	}

	public static java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure> getStructureLinkStructures(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStructureLinkStructures(classNameId, classPK);
	}

	public static java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructureLink> getStructureLinks(
		long classNameId, long classPK) {
		return getService().getStructureLinks(classNameId, classPK);
	}

	public static java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructureLink> getStructureLinks(
		long structureId) {
		return getService().getStructureLinks(structureId);
	}

	public static java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructureLink> getStructureLinks(
		long structureId, int start, int end) {
		return getService().getStructureLinks(structureId, start, end);
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

	public static void deleteStructureLink(
		com.liferay.dynamic.data.mapping.model.DDMStructureLink structureLink) {
		getService().deleteStructureLink(structureLink);
	}

	public static void deleteStructureLink(long classNameId, long classPK,
		long structureId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteStructureLink(classNameId, classPK, structureId);
	}

	public static void deleteStructureLink(long structureLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteStructureLink(structureLinkId);
	}

	public static void deleteStructureLinks(long classNameId, long classPK) {
		getService().deleteStructureLinks(classNameId, classPK);
	}

	public static void deleteStructureStructureLinks(long structureId) {
		getService().deleteStructureStructureLinks(structureId);
	}

	public static DDMStructureLinkLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DDMStructureLinkLocalService, DDMStructureLinkLocalService> _serviceTracker =
		ServiceTrackerFactory.open(DDMStructureLinkLocalService.class);
}