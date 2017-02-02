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
 * Provides the local service utility for DLContent. This utility wraps
 * {@link com.liferay.portlet.documentlibrary.service.impl.DLContentLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DLContentLocalService
 * @see com.liferay.portlet.documentlibrary.service.base.DLContentLocalServiceBaseImpl
 * @see com.liferay.portlet.documentlibrary.service.impl.DLContentLocalServiceImpl
 * @generated
 */
@ProviderType
public class DLContentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.documentlibrary.service.impl.DLContentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasContent(long companyId, long repositoryId,
		java.lang.String path, java.lang.String version) {
		return getService().hasContent(companyId, repositoryId, path, version);
	}

	public static com.liferay.document.library.kernel.model.DLContent addContent(
		long companyId, long repositoryId, java.lang.String path,
		java.lang.String version, byte[] bytes) {
		return getService()
				   .addContent(companyId, repositoryId, path, version, bytes);
	}

	public static com.liferay.document.library.kernel.model.DLContent addContent(
		long companyId, long repositoryId, java.lang.String path,
		java.lang.String version, java.io.InputStream inputStream, long size) {
		return getService()
				   .addContent(companyId, repositoryId, path, version,
			inputStream, size);
	}

	/**
	* Adds the document library content to the database. Also notifies the appropriate model listeners.
	*
	* @param dlContent the document library content
	* @return the document library content that was added
	*/
	public static com.liferay.document.library.kernel.model.DLContent addDLContent(
		com.liferay.document.library.kernel.model.DLContent dlContent) {
		return getService().addDLContent(dlContent);
	}

	/**
	* Creates a new document library content with the primary key. Does not add the document library content to the database.
	*
	* @param contentId the primary key for the new document library content
	* @return the new document library content
	*/
	public static com.liferay.document.library.kernel.model.DLContent createDLContent(
		long contentId) {
		return getService().createDLContent(contentId);
	}

	/**
	* Deletes the document library content from the database. Also notifies the appropriate model listeners.
	*
	* @param dlContent the document library content
	* @return the document library content that was removed
	*/
	public static com.liferay.document.library.kernel.model.DLContent deleteDLContent(
		com.liferay.document.library.kernel.model.DLContent dlContent) {
		return getService().deleteDLContent(dlContent);
	}

	/**
	* Deletes the document library content with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentId the primary key of the document library content
	* @return the document library content that was removed
	* @throws PortalException if a document library content with the primary key could not be found
	*/
	public static com.liferay.document.library.kernel.model.DLContent deleteDLContent(
		long contentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDLContent(contentId);
	}

	public static com.liferay.document.library.kernel.model.DLContent fetchDLContent(
		long contentId) {
		return getService().fetchDLContent(contentId);
	}

	public static com.liferay.document.library.kernel.model.DLContent getContent(
		long companyId, long repositoryId, java.lang.String path)
		throws com.liferay.document.library.kernel.exception.NoSuchContentException {
		return getService().getContent(companyId, repositoryId, path);
	}

	public static com.liferay.document.library.kernel.model.DLContent getContent(
		long companyId, long repositoryId, java.lang.String path,
		java.lang.String version)
		throws com.liferay.document.library.kernel.exception.NoSuchContentException {
		return getService().getContent(companyId, repositoryId, path, version);
	}

	/**
	* Returns the document library content with the primary key.
	*
	* @param contentId the primary key of the document library content
	* @return the document library content
	* @throws PortalException if a document library content with the primary key could not be found
	*/
	public static com.liferay.document.library.kernel.model.DLContent getDLContent(
		long contentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDLContent(contentId);
	}

	/**
	* Updates the document library content in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dlContent the document library content
	* @return the document library content that was updated
	*/
	public static com.liferay.document.library.kernel.model.DLContent updateDLContent(
		com.liferay.document.library.kernel.model.DLContent dlContent) {
		return getService().updateDLContent(dlContent);
	}

	public static com.liferay.document.library.kernel.model.DLContentDataBlobModel getDataBlobModel(
		java.io.Serializable primaryKey) {
		return getService().getDataBlobModel(primaryKey);
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
	* Returns the number of document library contents.
	*
	* @return the number of document library contents
	*/
	public static int getDLContentsCount() {
		return getService().getDLContentsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.document.library.kernel.model.DLContent> getContents(
		long companyId, long repositoryId) {
		return getService().getContents(companyId, repositoryId);
	}

	public static java.util.List<com.liferay.document.library.kernel.model.DLContent> getContents(
		long companyId, long repositoryId, java.lang.String path) {
		return getService().getContents(companyId, repositoryId, path);
	}

	public static java.util.List<com.liferay.document.library.kernel.model.DLContent> getContentsByDirectory(
		long companyId, long repositoryId, java.lang.String dirName) {
		return getService()
				   .getContentsByDirectory(companyId, repositoryId, dirName);
	}

	/**
	* Returns a range of all the document library contents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of document library contents
	* @param end the upper bound of the range of document library contents (not inclusive)
	* @return the range of document library contents
	*/
	public static java.util.List<com.liferay.document.library.kernel.model.DLContent> getDLContents(
		int start, int end) {
		return getService().getDLContents(start, end);
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

	public static void deleteContent(long companyId, long repositoryId,
		java.lang.String path, java.lang.String version)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteContent(companyId, repositoryId, path, version);
	}

	public static void deleteContents(long companyId, long repositoryId,
		java.lang.String path) {
		getService().deleteContents(companyId, repositoryId, path);
	}

	public static void deleteContentsByDirectory(long companyId,
		long repositoryId, java.lang.String dirName) {
		getService().deleteContentsByDirectory(companyId, repositoryId, dirName);
	}

	public static void updateDLContent(long companyId, long oldRepositoryId,
		long newRepositoryId, java.lang.String oldPath, java.lang.String newPath) {
		getService()
			.updateDLContent(companyId, oldRepositoryId, newRepositoryId,
			oldPath, newPath);
	}

	public static DLContentLocalService getService() {
		if (_service == null) {
			_service = (DLContentLocalService)PortalBeanLocatorUtil.locate(DLContentLocalService.class.getName());

			ReferenceRegistry.registerReference(DLContentLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static DLContentLocalService _service;
}