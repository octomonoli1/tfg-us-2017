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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for RatingsEntry. This utility wraps
 * {@link com.liferay.portlet.ratings.service.impl.RatingsEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RatingsEntryLocalService
 * @see com.liferay.portlet.ratings.service.base.RatingsEntryLocalServiceBaseImpl
 * @see com.liferay.portlet.ratings.service.impl.RatingsEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class RatingsEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.ratings.service.impl.RatingsEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
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
	* Adds the ratings entry to the database. Also notifies the appropriate model listeners.
	*
	* @param ratingsEntry the ratings entry
	* @return the ratings entry that was added
	*/
	public static com.liferay.ratings.kernel.model.RatingsEntry addRatingsEntry(
		com.liferay.ratings.kernel.model.RatingsEntry ratingsEntry) {
		return getService().addRatingsEntry(ratingsEntry);
	}

	/**
	* Creates a new ratings entry with the primary key. Does not add the ratings entry to the database.
	*
	* @param entryId the primary key for the new ratings entry
	* @return the new ratings entry
	*/
	public static com.liferay.ratings.kernel.model.RatingsEntry createRatingsEntry(
		long entryId) {
		return getService().createRatingsEntry(entryId);
	}

	/**
	* Deletes the ratings entry from the database. Also notifies the appropriate model listeners.
	*
	* @param ratingsEntry the ratings entry
	* @return the ratings entry that was removed
	*/
	public static com.liferay.ratings.kernel.model.RatingsEntry deleteRatingsEntry(
		com.liferay.ratings.kernel.model.RatingsEntry ratingsEntry) {
		return getService().deleteRatingsEntry(ratingsEntry);
	}

	/**
	* Deletes the ratings entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the ratings entry
	* @return the ratings entry that was removed
	* @throws PortalException if a ratings entry with the primary key could not be found
	*/
	public static com.liferay.ratings.kernel.model.RatingsEntry deleteRatingsEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRatingsEntry(entryId);
	}

	public static com.liferay.ratings.kernel.model.RatingsEntry fetchEntry(
		long userId, java.lang.String className, long classPK) {
		return getService().fetchEntry(userId, className, classPK);
	}

	public static com.liferay.ratings.kernel.model.RatingsEntry fetchRatingsEntry(
		long entryId) {
		return getService().fetchRatingsEntry(entryId);
	}

	/**
	* Returns the ratings entry with the matching UUID and company.
	*
	* @param uuid the ratings entry's UUID
	* @param companyId the primary key of the company
	* @return the matching ratings entry, or <code>null</code> if a matching ratings entry could not be found
	*/
	public static com.liferay.ratings.kernel.model.RatingsEntry fetchRatingsEntryByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().fetchRatingsEntryByUuidAndCompanyId(uuid, companyId);
	}

	public static com.liferay.ratings.kernel.model.RatingsEntry getEntry(
		long userId, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEntry(userId, className, classPK);
	}

	/**
	* Returns the ratings entry with the primary key.
	*
	* @param entryId the primary key of the ratings entry
	* @return the ratings entry
	* @throws PortalException if a ratings entry with the primary key could not be found
	*/
	public static com.liferay.ratings.kernel.model.RatingsEntry getRatingsEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRatingsEntry(entryId);
	}

	/**
	* Returns the ratings entry with the matching UUID and company.
	*
	* @param uuid the ratings entry's UUID
	* @param companyId the primary key of the company
	* @return the matching ratings entry
	* @throws PortalException if a matching ratings entry could not be found
	*/
	public static com.liferay.ratings.kernel.model.RatingsEntry getRatingsEntryByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRatingsEntryByUuidAndCompanyId(uuid, companyId);
	}

	public static com.liferay.ratings.kernel.model.RatingsEntry updateEntry(
		long userId, java.lang.String className, long classPK, double score,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateEntry(userId, className, classPK, score,
			serviceContext);
	}

	/**
	* Updates the ratings entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ratingsEntry the ratings entry
	* @return the ratings entry that was updated
	*/
	public static com.liferay.ratings.kernel.model.RatingsEntry updateRatingsEntry(
		com.liferay.ratings.kernel.model.RatingsEntry ratingsEntry) {
		return getService().updateRatingsEntry(ratingsEntry);
	}

	public static int getEntriesCount(java.lang.String className, long classPK,
		double score) {
		return getService().getEntriesCount(className, classPK, score);
	}

	/**
	* Returns the number of ratings entries.
	*
	* @return the number of ratings entries
	*/
	public static int getRatingsEntriesCount() {
		return getService().getRatingsEntriesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.ratings.model.impl.RatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.ratings.model.impl.RatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.ratings.kernel.model.RatingsEntry> getEntries(
		java.lang.String className, long classPK) {
		return getService().getEntries(className, classPK);
	}

	public static java.util.List<com.liferay.ratings.kernel.model.RatingsEntry> getEntries(
		java.lang.String className, long classPK, double score) {
		return getService().getEntries(className, classPK, score);
	}

	public static java.util.List<com.liferay.ratings.kernel.model.RatingsEntry> getEntries(
		long userId, java.lang.String className,
		java.util.List<java.lang.Long> classPKs) {
		return getService().getEntries(userId, className, classPKs);
	}

	/**
	* Returns a range of all the ratings entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.ratings.model.impl.RatingsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ratings entries
	* @param end the upper bound of the range of ratings entries (not inclusive)
	* @return the range of ratings entries
	*/
	public static java.util.List<com.liferay.ratings.kernel.model.RatingsEntry> getRatingsEntries(
		int start, int end) {
		return getService().getRatingsEntries(start, end);
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

	public static void deleteEntry(
		com.liferay.ratings.kernel.model.RatingsEntry entry, long userId,
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteEntry(entry, userId, className, classPK);
	}

	public static void deleteEntry(long userId, java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteEntry(userId, className, classPK);
	}

	public static RatingsEntryLocalService getService() {
		if (_service == null) {
			_service = (RatingsEntryLocalService)PortalBeanLocatorUtil.locate(RatingsEntryLocalService.class.getName());

			ReferenceRegistry.registerReference(RatingsEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static RatingsEntryLocalService _service;
}