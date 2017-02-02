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

package com.liferay.document.library.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for DLFileRank. This utility wraps
 * {@link com.liferay.portlet.documentlibrary.service.impl.DLFileRankLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileRankLocalService
 * @see com.liferay.portlet.documentlibrary.service.base.DLFileRankLocalServiceBaseImpl
 * @see com.liferay.portlet.documentlibrary.service.impl.DLFileRankLocalServiceImpl
 * @generated
 */
@ProviderType
public class DLFileRankLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.documentlibrary.service.impl.DLFileRankLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the document library file rank to the database. Also notifies the appropriate model listeners.
	*
	* @param dlFileRank the document library file rank
	* @return the document library file rank that was added
	*/
	public static com.liferay.document.library.kernel.model.DLFileRank addDLFileRank(
		com.liferay.document.library.kernel.model.DLFileRank dlFileRank) {
		return getService().addDLFileRank(dlFileRank);
	}

	public static com.liferay.document.library.kernel.model.DLFileRank addFileRank(
		long groupId, long companyId, long userId, long fileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService()
				   .addFileRank(groupId, companyId, userId, fileEntryId,
			serviceContext);
	}

	/**
	* Creates a new document library file rank with the primary key. Does not add the document library file rank to the database.
	*
	* @param fileRankId the primary key for the new document library file rank
	* @return the new document library file rank
	*/
	public static com.liferay.document.library.kernel.model.DLFileRank createDLFileRank(
		long fileRankId) {
		return getService().createDLFileRank(fileRankId);
	}

	/**
	* Deletes the document library file rank from the database. Also notifies the appropriate model listeners.
	*
	* @param dlFileRank the document library file rank
	* @return the document library file rank that was removed
	*/
	public static com.liferay.document.library.kernel.model.DLFileRank deleteDLFileRank(
		com.liferay.document.library.kernel.model.DLFileRank dlFileRank) {
		return getService().deleteDLFileRank(dlFileRank);
	}

	/**
	* Deletes the document library file rank with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileRankId the primary key of the document library file rank
	* @return the document library file rank that was removed
	* @throws PortalException if a document library file rank with the primary key could not be found
	*/
	public static com.liferay.document.library.kernel.model.DLFileRank deleteDLFileRank(
		long fileRankId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDLFileRank(fileRankId);
	}

	public static com.liferay.document.library.kernel.model.DLFileRank fetchDLFileRank(
		long fileRankId) {
		return getService().fetchDLFileRank(fileRankId);
	}

	/**
	* Returns the document library file rank with the primary key.
	*
	* @param fileRankId the primary key of the document library file rank
	* @return the document library file rank
	* @throws PortalException if a document library file rank with the primary key could not be found
	*/
	public static com.liferay.document.library.kernel.model.DLFileRank getDLFileRank(
		long fileRankId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDLFileRank(fileRankId);
	}

	/**
	* Updates the document library file rank in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dlFileRank the document library file rank
	* @return the document library file rank that was updated
	*/
	public static com.liferay.document.library.kernel.model.DLFileRank updateDLFileRank(
		com.liferay.document.library.kernel.model.DLFileRank dlFileRank) {
		return getService().updateDLFileRank(dlFileRank);
	}

	public static com.liferay.document.library.kernel.model.DLFileRank updateFileRank(
		long groupId, long companyId, long userId, long fileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService()
				   .updateFileRank(groupId, companyId, userId, fileEntryId,
			serviceContext);
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
	* Returns the number of document library file ranks.
	*
	* @return the number of document library file ranks
	*/
	public static int getDLFileRanksCount() {
		return getService().getDLFileRanksCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFileRankModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFileRankModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the document library file ranks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFileRankModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of document library file ranks
	* @param end the upper bound of the range of document library file ranks (not inclusive)
	* @return the range of document library file ranks
	*/
	public static java.util.List<com.liferay.document.library.kernel.model.DLFileRank> getDLFileRanks(
		int start, int end) {
		return getService().getDLFileRanks(start, end);
	}

	public static java.util.List<com.liferay.document.library.kernel.model.DLFileRank> getFileRanks(
		long groupId, long userId) {
		return getService().getFileRanks(groupId, userId);
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

	public static void checkFileRanks() {
		getService().checkFileRanks();
	}

	public static void deleteFileRank(
		com.liferay.document.library.kernel.model.DLFileRank dlFileRank) {
		getService().deleteFileRank(dlFileRank);
	}

	public static void deleteFileRank(long fileRankId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteFileRank(fileRankId);
	}

	public static void deleteFileRanksByFileEntryId(long fileEntryId) {
		getService().deleteFileRanksByFileEntryId(fileEntryId);
	}

	public static void deleteFileRanksByUserId(long userId) {
		getService().deleteFileRanksByUserId(userId);
	}

	public static void disableFileRanks(long fileEntryId) {
		getService().disableFileRanks(fileEntryId);
	}

	public static void disableFileRanksByFolderId(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().disableFileRanksByFolderId(folderId);
	}

	public static void enableFileRanks(long fileEntryId) {
		getService().enableFileRanks(fileEntryId);
	}

	public static void enableFileRanksByFolderId(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().enableFileRanksByFolderId(folderId);
	}

	public static DLFileRankLocalService getService() {
		if (_service == null) {
			_service = (DLFileRankLocalService)PortalBeanLocatorUtil.locate(DLFileRankLocalService.class.getName());

			ReferenceRegistry.registerReference(DLFileRankLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static DLFileRankLocalService _service;
}