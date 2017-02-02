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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DLFileEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryLocalService
 * @generated
 */
@ProviderType
public class DLFileEntryLocalServiceWrapper implements DLFileEntryLocalService,
	ServiceWrapper<DLFileEntryLocalService> {
	public DLFileEntryLocalServiceWrapper(
		DLFileEntryLocalService dlFileEntryLocalService) {
		_dlFileEntryLocalService = dlFileEntryLocalService;
	}

	@Override
	public boolean hasExtraSettings() {
		return _dlFileEntryLocalService.hasExtraSettings();
	}

	@Override
	public boolean hasFileEntryLock(long userId, long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.hasFileEntryLock(userId, fileEntryId);
	}

	@Override
	public boolean isFileEntryCheckedOut(long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.isFileEntryCheckedOut(fileEntryId);
	}

	@Override
	public boolean isKeepFileVersionLabel(long fileEntryId,
		boolean majorVersion,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.isKeepFileVersionLabel(fileEntryId,
			majorVersion, serviceContext);
	}

	/**
	* As of 7.0.0, replaced by {@link #isKeepFileVersionLabel(long, boolean,
	* ServiceContext)}
	*/
	@Deprecated
	@Override
	public boolean isKeepFileVersionLabel(long fileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.isKeepFileVersionLabel(fileEntryId,
			serviceContext);
	}

	@Override
	public boolean verifyFileEntryCheckOut(long fileEntryId,
		java.lang.String lockUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.verifyFileEntryCheckOut(fileEntryId,
			lockUuid);
	}

	@Override
	public boolean verifyFileEntryLock(long fileEntryId,
		java.lang.String lockUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.verifyFileEntryLock(fileEntryId,
			lockUuid);
	}

	/**
	* Adds the document library file entry to the database. Also notifies the appropriate model listeners.
	*
	* @param dlFileEntry the document library file entry
	* @return the document library file entry that was added
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry addDLFileEntry(
		com.liferay.document.library.kernel.model.DLFileEntry dlFileEntry) {
		return _dlFileEntryLocalService.addDLFileEntry(dlFileEntry);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry addFileEntry(
		long userId, long groupId, long repositoryId, long folderId,
		java.lang.String sourceFileName, java.lang.String mimeType,
		java.lang.String title, java.lang.String description,
		java.lang.String changeLog, long fileEntryTypeId,
		java.util.Map<java.lang.String, com.liferay.dynamic.data.mapping.kernel.DDMFormValues> ddmFormValuesMap,
		java.io.File file, java.io.InputStream is, long size,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.addFileEntry(userId, groupId,
			repositoryId, folderId, sourceFileName, mimeType, title,
			description, changeLog, fileEntryTypeId, ddmFormValuesMap, file,
			is, size, serviceContext);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry checkOutFileEntry(
		long userId, long fileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.checkOutFileEntry(userId, fileEntryId,
			serviceContext);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry checkOutFileEntry(
		long userId, long fileEntryId, java.lang.String owner,
		long expirationTime,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.checkOutFileEntry(userId, fileEntryId,
			owner, expirationTime, serviceContext);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry copyFileEntry(
		long userId, long groupId, long repositoryId, long fileEntryId,
		long destFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.copyFileEntry(userId, groupId,
			repositoryId, fileEntryId, destFolderId, serviceContext);
	}

	/**
	* Creates a new document library file entry with the primary key. Does not add the document library file entry to the database.
	*
	* @param fileEntryId the primary key for the new document library file entry
	* @return the new document library file entry
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry createDLFileEntry(
		long fileEntryId) {
		return _dlFileEntryLocalService.createDLFileEntry(fileEntryId);
	}

	/**
	* Deletes the document library file entry from the database. Also notifies the appropriate model listeners.
	*
	* @param dlFileEntry the document library file entry
	* @return the document library file entry that was removed
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry deleteDLFileEntry(
		com.liferay.document.library.kernel.model.DLFileEntry dlFileEntry) {
		return _dlFileEntryLocalService.deleteDLFileEntry(dlFileEntry);
	}

	/**
	* Deletes the document library file entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileEntryId the primary key of the document library file entry
	* @return the document library file entry that was removed
	* @throws PortalException if a document library file entry with the primary key could not be found
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry deleteDLFileEntry(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.deleteDLFileEntry(fileEntryId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry deleteFileEntry(
		com.liferay.document.library.kernel.model.DLFileEntry dlFileEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.deleteFileEntry(dlFileEntry);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry deleteFileEntry(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.deleteFileEntry(fileEntryId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry deleteFileEntry(
		long userId, long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.deleteFileEntry(userId, fileEntryId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry deleteFileVersion(
		long userId, long fileEntryId, java.lang.String version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.deleteFileVersion(userId, fileEntryId,
			version);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry fetchDLFileEntry(
		long fileEntryId) {
		return _dlFileEntryLocalService.fetchDLFileEntry(fileEntryId);
	}

	/**
	* Returns the document library file entry matching the UUID and group.
	*
	* @param uuid the document library file entry's UUID
	* @param groupId the primary key of the group
	* @return the matching document library file entry, or <code>null</code> if a matching document library file entry could not be found
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry fetchDLFileEntryByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _dlFileEntryLocalService.fetchDLFileEntryByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry fetchFileEntry(
		long groupId, long folderId, java.lang.String title) {
		return _dlFileEntryLocalService.fetchFileEntry(groupId, folderId, title);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry fetchFileEntryByAnyImageId(
		long imageId) {
		return _dlFileEntryLocalService.fetchFileEntryByAnyImageId(imageId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry fetchFileEntryByFileName(
		long groupId, long folderId, java.lang.String fileName) {
		return _dlFileEntryLocalService.fetchFileEntryByFileName(groupId,
			folderId, fileName);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry fetchFileEntryByName(
		long groupId, long folderId, java.lang.String name) {
		return _dlFileEntryLocalService.fetchFileEntryByName(groupId, folderId,
			name);
	}

	/**
	* Returns the document library file entry with the primary key.
	*
	* @param fileEntryId the primary key of the document library file entry
	* @return the document library file entry
	* @throws PortalException if a document library file entry with the primary key could not be found
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry getDLFileEntry(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getDLFileEntry(fileEntryId);
	}

	/**
	* Returns the document library file entry matching the UUID and group.
	*
	* @param uuid the document library file entry's UUID
	* @param groupId the primary key of the group
	* @return the matching document library file entry
	* @throws PortalException if a matching document library file entry could not be found
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry getDLFileEntryByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getDLFileEntryByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry getFileEntry(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFileEntry(fileEntryId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry getFileEntry(
		long groupId, long folderId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFileEntry(groupId, folderId, title);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry getFileEntryByName(
		long groupId, long folderId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFileEntryByName(groupId, folderId,
			name);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry getFileEntryByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFileEntryByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry moveFileEntry(
		long userId, long fileEntryId, long newFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.moveFileEntry(userId, fileEntryId,
			newFolderId, serviceContext);
	}

	/**
	* Updates the document library file entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dlFileEntry the document library file entry
	* @return the document library file entry that was updated
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry updateDLFileEntry(
		com.liferay.document.library.kernel.model.DLFileEntry dlFileEntry) {
		return _dlFileEntryLocalService.updateDLFileEntry(dlFileEntry);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry updateFileEntry(
		long userId, long fileEntryId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		boolean majorVersion, long fileEntryTypeId,
		java.util.Map<java.lang.String, com.liferay.dynamic.data.mapping.kernel.DDMFormValues> ddmFormValuesMap,
		java.io.File file, java.io.InputStream is, long size,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.updateFileEntry(userId, fileEntryId,
			sourceFileName, mimeType, title, description, changeLog,
			majorVersion, fileEntryTypeId, ddmFormValuesMap, file, is, size,
			serviceContext);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry updateFileEntryType(
		long userId, long fileEntryId, long fileEntryTypeId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.updateFileEntryType(userId,
			fileEntryId, fileEntryTypeId, serviceContext);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry updateStatus(
		long userId, long fileVersionId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.updateStatus(userId, fileVersionId,
			status, serviceContext, workflowContext);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileVersion cancelCheckOut(
		long userId, long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.cancelCheckOut(userId, fileEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dlFileEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dlFileEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dlFileEntryLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dlFileEntryLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.lock.Lock lockFileEntry(long userId,
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.lockFileEntry(userId, fileEntryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long groupId,
		long userId, long creatorUserId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.search(groupId, userId, creatorUserId,
			status, start, end);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long groupId,
		long userId, long creatorUserId, long folderId,
		java.lang.String[] mimeTypes, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.search(groupId, userId, creatorUserId,
			folderId, mimeTypes, status, start, end);
	}

	/**
	* Returns the number of document library file entries.
	*
	* @return the number of document library file entries
	*/
	@Override
	public int getDLFileEntriesCount() {
		return _dlFileEntryLocalService.getDLFileEntriesCount();
	}

	@Override
	public int getExtraSettingsFileEntriesCount() {
		return _dlFileEntryLocalService.getExtraSettingsFileEntriesCount();
	}

	@Override
	public int getFileEntriesCount() {
		return _dlFileEntryLocalService.getFileEntriesCount();
	}

	/**
	* @deprecated As of 7.0.0, with no direct replacement
	*/
	@Deprecated
	@Override
	public int getFileEntriesCount(long groupId,
		com.liferay.portal.kernel.util.DateRange dateRange, long repositoryId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<com.liferay.document.library.kernel.model.DLFileEntry> queryDefinition) {
		return _dlFileEntryLocalService.getFileEntriesCount(groupId, dateRange,
			repositoryId, queryDefinition);
	}

	@Override
	public int getFileEntriesCount(long groupId, long folderId) {
		return _dlFileEntryLocalService.getFileEntriesCount(groupId, folderId);
	}

	@Override
	public int getFileEntriesCount(long groupId, long folderId, int status) {
		return _dlFileEntryLocalService.getFileEntriesCount(groupId, folderId,
			status);
	}

	@Override
	public int getFileEntriesCount(long groupId, long userId,
		java.util.List<java.lang.Long> folderIds, java.lang.String[] mimeTypes,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<com.liferay.document.library.kernel.model.DLFileEntry> queryDefinition)
		throws java.lang.Exception {
		return _dlFileEntryLocalService.getFileEntriesCount(groupId, userId,
			folderIds, mimeTypes, queryDefinition);
	}

	@Override
	public int getFileEntriesCount(long groupId, long userId,
		java.util.List<java.lang.Long> repositoryIds,
		java.util.List<java.lang.Long> folderIds, java.lang.String[] mimeTypes,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<com.liferay.document.library.kernel.model.DLFileEntry> queryDefinition)
		throws java.lang.Exception {
		return _dlFileEntryLocalService.getFileEntriesCount(groupId, userId,
			repositoryIds, folderIds, mimeTypes, queryDefinition);
	}

	@Override
	public int getGroupFileEntriesCount(long groupId) {
		return _dlFileEntryLocalService.getGroupFileEntriesCount(groupId);
	}

	@Override
	public int getGroupFileEntriesCount(long groupId, long userId) {
		return _dlFileEntryLocalService.getGroupFileEntriesCount(groupId, userId);
	}

	@Override
	public int getRepositoryFileEntriesCount(long repositoryId) {
		return _dlFileEntryLocalService.getRepositoryFileEntriesCount(repositoryId);
	}

	@Override
	public java.io.File getFile(long fileEntryId, java.lang.String version,
		boolean incrementCounter)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFile(fileEntryId, version,
			incrementCounter);
	}

	@Override
	public java.io.File getFile(long fileEntryId, java.lang.String version,
		boolean incrementCounter, int increment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFile(fileEntryId, version,
			incrementCounter, increment);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getFile(long, String,
	boolean)}
	*/
	@Deprecated
	@Override
	public java.io.File getFile(long userId, long fileEntryId,
		java.lang.String version, boolean incrementCounter)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFile(userId, fileEntryId, version,
			incrementCounter);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getFile(long, String,
	boolean, int)}
	*/
	@Deprecated
	@Override
	public java.io.File getFile(long userId, long fileEntryId,
		java.lang.String version, boolean incrementCounter, int increment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFile(userId, fileEntryId, version,
			incrementCounter, increment);
	}

	@Override
	public java.io.InputStream getFileAsStream(long fileEntryId,
		java.lang.String version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFileAsStream(fileEntryId, version);
	}

	@Override
	public java.io.InputStream getFileAsStream(long fileEntryId,
		java.lang.String version, boolean incrementCounter)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFileAsStream(fileEntryId, version,
			incrementCounter);
	}

	@Override
	public java.io.InputStream getFileAsStream(long fileEntryId,
		java.lang.String version, boolean incrementCounter, int increment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFileAsStream(fileEntryId, version,
			incrementCounter, increment);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getFileAsStream(long,
	String)}
	*/
	@Deprecated
	@Override
	public java.io.InputStream getFileAsStream(long userId, long fileEntryId,
		java.lang.String version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFileAsStream(userId, fileEntryId,
			version);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getFileAsStream(long,
	String, boolean)}
	*/
	@Deprecated
	@Override
	public java.io.InputStream getFileAsStream(long userId, long fileEntryId,
		java.lang.String version, boolean incrementCounter)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFileAsStream(userId, fileEntryId,
			version, incrementCounter);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getFileAsStream(long,
	String, boolean, int)}
	*/
	@Deprecated
	@Override
	public java.io.InputStream getFileAsStream(long userId, long fileEntryId,
		java.lang.String version, boolean incrementCounter, int increment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getFileAsStream(userId, fileEntryId,
			version, incrementCounter, increment);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _dlFileEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.lang.String getUniqueTitle(long groupId, long folderId,
		long fileEntryId, java.lang.String title, java.lang.String extension)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFileEntryLocalService.getUniqueTitle(groupId, folderId,
			fileEntryId, title, extension);
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
		return _dlFileEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFileEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dlFileEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFileEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dlFileEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getDDMStructureFileEntries(
		long groupId, long[] ddmStructureIds) {
		return _dlFileEntryLocalService.getDDMStructureFileEntries(groupId,
			ddmStructureIds);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getDDMStructureFileEntries(
		long[] ddmStructureIds) {
		return _dlFileEntryLocalService.getDDMStructureFileEntries(ddmStructureIds);
	}

	/**
	* Returns a range of all the document library file entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFileEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of document library file entries
	*/
	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getDLFileEntries(
		int start, int end) {
		return _dlFileEntryLocalService.getDLFileEntries(start, end);
	}

	/**
	* Returns all the document library file entries matching the UUID and company.
	*
	* @param uuid the UUID of the document library file entries
	* @param companyId the primary key of the company
	* @return the matching document library file entries, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getDLFileEntriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _dlFileEntryLocalService.getDLFileEntriesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of document library file entries matching the UUID and company.
	*
	* @param uuid the UUID of the document library file entries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching document library file entries, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getDLFileEntriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> orderByComparator) {
		return _dlFileEntryLocalService.getDLFileEntriesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getExtraSettingsFileEntries(
		int start, int end) {
		return _dlFileEntryLocalService.getExtraSettingsFileEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getFileEntries(
		int start, int end) {
		return _dlFileEntryLocalService.getFileEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getFileEntries(
		long folderId, java.lang.String name) {
		return _dlFileEntryLocalService.getFileEntries(folderId, name);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getFileEntries(
		long groupId, long folderId) {
		return _dlFileEntryLocalService.getFileEntries(groupId, folderId);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getFileEntries(
		long groupId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> obc) {
		return _dlFileEntryLocalService.getFileEntries(groupId, folderId,
			start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getFileEntries(
		long groupId, long folderId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> obc) {
		return _dlFileEntryLocalService.getFileEntries(groupId, folderId,
			status, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getFileEntries(
		long groupId, long userId, java.util.List<java.lang.Long> folderIds,
		java.lang.String[] mimeTypes,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<com.liferay.document.library.kernel.model.DLFileEntry> queryDefinition)
		throws java.lang.Exception {
		return _dlFileEntryLocalService.getFileEntries(groupId, userId,
			folderIds, mimeTypes, queryDefinition);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getFileEntries(
		long groupId, long userId,
		java.util.List<java.lang.Long> repositoryIds,
		java.util.List<java.lang.Long> folderIds, java.lang.String[] mimeTypes,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<com.liferay.document.library.kernel.model.DLFileEntry> queryDefinition)
		throws java.lang.Exception {
		return _dlFileEntryLocalService.getFileEntries(groupId, userId,
			repositoryIds, folderIds, mimeTypes, queryDefinition);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getGroupFileEntries(
		long groupId, int start, int end) {
		return _dlFileEntryLocalService.getGroupFileEntries(groupId, start, end);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getGroupFileEntries(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> obc) {
		return _dlFileEntryLocalService.getGroupFileEntries(groupId, start,
			end, obc);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getGroupFileEntries(
		long groupId, long userId, int start, int end) {
		return _dlFileEntryLocalService.getGroupFileEntries(groupId, userId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getGroupFileEntries(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> obc) {
		return _dlFileEntryLocalService.getGroupFileEntries(groupId, userId,
			start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getGroupFileEntries(
		long groupId, long userId, long repositoryId, long rootFolderId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> obc) {
		return _dlFileEntryLocalService.getGroupFileEntries(groupId, userId,
			repositoryId, rootFolderId, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getGroupFileEntries(
		long groupId, long userId, long rootFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> obc) {
		return _dlFileEntryLocalService.getGroupFileEntries(groupId, userId,
			rootFolderId, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getMisversionedFileEntries() {
		return _dlFileEntryLocalService.getMisversionedFileEntries();
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getNoAssetFileEntries() {
		return _dlFileEntryLocalService.getNoAssetFileEntries();
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getOrphanedFileEntries() {
		return _dlFileEntryLocalService.getOrphanedFileEntries();
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getRepositoryFileEntries(
		long repositoryId, int start, int end) {
		return _dlFileEntryLocalService.getRepositoryFileEntries(repositoryId,
			start, end);
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
		return _dlFileEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dlFileEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void checkInFileEntry(long userId, long fileEntryId,
		boolean majorVersion, java.lang.String changeLog,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFileEntryLocalService.checkInFileEntry(userId, fileEntryId,
			majorVersion, changeLog, serviceContext);
	}

	@Override
	public void checkInFileEntry(long userId, long fileEntryId,
		java.lang.String lockUuid,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFileEntryLocalService.checkInFileEntry(userId, fileEntryId,
			lockUuid, serviceContext);
	}

	@Override
	public void convertExtraSettings(java.lang.String[] keys)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFileEntryLocalService.convertExtraSettings(keys);
	}

	@Override
	public void copyFileEntryMetadata(long companyId, long fileEntryTypeId,
		long fileEntryId, long fromFileVersionId, long toFileVersionId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFileEntryLocalService.copyFileEntryMetadata(companyId,
			fileEntryTypeId, fileEntryId, fromFileVersionId, toFileVersionId,
			serviceContext);
	}

	@Override
	public void deleteFileEntries(long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFileEntryLocalService.deleteFileEntries(groupId, folderId);
	}

	@Override
	public void deleteFileEntries(long groupId, long folderId,
		boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFileEntryLocalService.deleteFileEntries(groupId, folderId,
			includeTrashedEntries);
	}

	@Override
	public void deleteRepositoryFileEntries(long repositoryId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFileEntryLocalService.deleteRepositoryFileEntries(repositoryId,
			folderId);
	}

	@Override
	public void deleteRepositoryFileEntries(long repositoryId, long folderId,
		boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFileEntryLocalService.deleteRepositoryFileEntries(repositoryId,
			folderId, includeTrashedEntries);
	}

	@Override
	public void incrementViewCounter(
		com.liferay.document.library.kernel.model.DLFileEntry dlFileEntry,
		int increment) {
		_dlFileEntryLocalService.incrementViewCounter(dlFileEntry, increment);
	}

	@Override
	public void rebuildTree(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFileEntryLocalService.rebuildTree(companyId);
	}

	@Override
	public void revertFileEntry(long userId, long fileEntryId,
		java.lang.String version,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFileEntryLocalService.revertFileEntry(userId, fileEntryId, version,
			serviceContext);
	}

	@Override
	public void setTreePaths(long folderId, java.lang.String treePath,
		boolean reindex)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFileEntryLocalService.setTreePaths(folderId, treePath, reindex);
	}

	@Override
	public void unlockFileEntry(long fileEntryId) {
		_dlFileEntryLocalService.unlockFileEntry(fileEntryId);
	}

	@Override
	public void updateSmallImage(long smallImageId, long largeImageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFileEntryLocalService.updateSmallImage(smallImageId, largeImageId);
	}

	@Override
	public void validateFile(long groupId, long folderId, long fileEntryId,
		java.lang.String fileName, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFileEntryLocalService.validateFile(groupId, folderId, fileEntryId,
			fileName, title);
	}

	@Override
	public DLFileEntryLocalService getWrappedService() {
		return _dlFileEntryLocalService;
	}

	@Override
	public void setWrappedService(
		DLFileEntryLocalService dlFileEntryLocalService) {
		_dlFileEntryLocalService = dlFileEntryLocalService;
	}

	private DLFileEntryLocalService _dlFileEntryLocalService;
}