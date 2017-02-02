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
 * Provides a wrapper for {@link DLFolderLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DLFolderLocalService
 * @generated
 */
@ProviderType
public class DLFolderLocalServiceWrapper implements DLFolderLocalService,
	ServiceWrapper<DLFolderLocalService> {
	public DLFolderLocalServiceWrapper(
		DLFolderLocalService dlFolderLocalService) {
		_dlFolderLocalService = dlFolderLocalService;
	}

	@Override
	public boolean hasDLFileEntryTypeDLFolder(long fileEntryTypeId,
		long folderId) {
		return _dlFolderLocalService.hasDLFileEntryTypeDLFolder(fileEntryTypeId,
			folderId);
	}

	@Override
	public boolean hasDLFileEntryTypeDLFolders(long fileEntryTypeId) {
		return _dlFolderLocalService.hasDLFileEntryTypeDLFolders(fileEntryTypeId);
	}

	@Override
	public boolean hasFolderLock(long userId, long folderId) {
		return _dlFolderLocalService.hasFolderLock(userId, folderId);
	}

	@Override
	public boolean hasInheritableLock(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.hasInheritableLock(folderId);
	}

	@Override
	public boolean verifyInheritableLock(long folderId,
		java.lang.String lockUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.verifyInheritableLock(folderId, lockUuid);
	}

	/**
	* Adds the document library folder to the database. Also notifies the appropriate model listeners.
	*
	* @param dlFolder the document library folder
	* @return the document library folder that was added
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFolder addDLFolder(
		com.liferay.document.library.kernel.model.DLFolder dlFolder) {
		return _dlFolderLocalService.addDLFolder(dlFolder);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder addFolder(
		long userId, long groupId, long repositoryId, boolean mountPoint,
		long parentFolderId, java.lang.String name,
		java.lang.String description, boolean hidden,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.addFolder(userId, groupId, repositoryId,
			mountPoint, parentFolderId, name, description, hidden,
			serviceContext);
	}

	/**
	* Creates a new document library folder with the primary key. Does not add the document library folder to the database.
	*
	* @param folderId the primary key for the new document library folder
	* @return the new document library folder
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFolder createDLFolder(
		long folderId) {
		return _dlFolderLocalService.createDLFolder(folderId);
	}

	/**
	* Deletes the document library folder from the database. Also notifies the appropriate model listeners.
	*
	* @param dlFolder the document library folder
	* @return the document library folder that was removed
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFolder deleteDLFolder(
		com.liferay.document.library.kernel.model.DLFolder dlFolder) {
		return _dlFolderLocalService.deleteDLFolder(dlFolder);
	}

	/**
	* Deletes the document library folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param folderId the primary key of the document library folder
	* @return the document library folder that was removed
	* @throws PortalException if a document library folder with the primary key could not be found
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFolder deleteDLFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.deleteDLFolder(folderId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder deleteFolder(
		com.liferay.document.library.kernel.model.DLFolder dlFolder)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.deleteFolder(dlFolder);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder deleteFolder(
		com.liferay.document.library.kernel.model.DLFolder dlFolder,
		boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.deleteFolder(dlFolder,
			includeTrashedEntries);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder deleteFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.deleteFolder(folderId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder deleteFolder(
		long folderId, boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.deleteFolder(folderId,
			includeTrashedEntries);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder deleteFolder(
		long userId, long folderId, boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.deleteFolder(userId, folderId,
			includeTrashedEntries);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder fetchDLFolder(
		long folderId) {
		return _dlFolderLocalService.fetchDLFolder(folderId);
	}

	/**
	* Returns the document library folder matching the UUID and group.
	*
	* @param uuid the document library folder's UUID
	* @param groupId the primary key of the group
	* @return the matching document library folder, or <code>null</code> if a matching document library folder could not be found
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFolder fetchDLFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _dlFolderLocalService.fetchDLFolderByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder fetchFolder(
		long folderId) {
		return _dlFolderLocalService.fetchFolder(folderId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder fetchFolder(
		long groupId, long parentFolderId, java.lang.String name) {
		return _dlFolderLocalService.fetchFolder(groupId, parentFolderId, name);
	}

	/**
	* Returns the document library folder with the primary key.
	*
	* @param folderId the primary key of the document library folder
	* @return the document library folder
	* @throws PortalException if a document library folder with the primary key could not be found
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFolder getDLFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.getDLFolder(folderId);
	}

	/**
	* Returns the document library folder matching the UUID and group.
	*
	* @param uuid the document library folder's UUID
	* @param groupId the primary key of the group
	* @return the matching document library folder
	* @throws PortalException if a matching document library folder could not be found
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFolder getDLFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.getDLFolderByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder getFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.getFolder(folderId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder getFolder(
		long groupId, long parentFolderId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.getFolder(groupId, parentFolderId, name);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder getMountFolder(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.getMountFolder(repositoryId);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder moveFolder(
		long userId, long folderId, long parentFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.moveFolder(userId, folderId,
			parentFolderId, serviceContext);
	}

	/**
	* Updates the document library folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dlFolder the document library folder
	* @return the document library folder that was updated
	*/
	@Override
	public com.liferay.document.library.kernel.model.DLFolder updateDLFolder(
		com.liferay.document.library.kernel.model.DLFolder dlFolder) {
		return _dlFolderLocalService.updateDLFolder(dlFolder);
	}

	/**
	* @deprecated As of 7.0.0, replaced {@link #updateFolder(long, long,
	String, String, long, List, int, ServiceContext)}
	*/
	@Deprecated
	@Override
	public com.liferay.document.library.kernel.model.DLFolder updateFolder(
		long folderId, java.lang.String name, java.lang.String description,
		long defaultFileEntryTypeId,
		java.util.List<java.lang.Long> fileEntryTypeIds,
		boolean overrideFileEntryTypes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.updateFolder(folderId, name, description,
			defaultFileEntryTypeId, fileEntryTypeIds, overrideFileEntryTypes,
			serviceContext);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder updateFolder(
		long folderId, java.lang.String name, java.lang.String description,
		long defaultFileEntryTypeId,
		java.util.List<java.lang.Long> fileEntryTypeIds, int restrictionType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.updateFolder(folderId, name, description,
			defaultFileEntryTypeId, fileEntryTypeIds, restrictionType,
			serviceContext);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #updateFolder(long, long,
	String, String, long, List, int, ServiceContext)}
	*/
	@Deprecated
	@Override
	public com.liferay.document.library.kernel.model.DLFolder updateFolder(
		long folderId, long parentFolderId, java.lang.String name,
		java.lang.String description, long defaultFileEntryTypeId,
		java.util.List<java.lang.Long> fileEntryTypeIds,
		boolean overrideFileEntryTypes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.updateFolder(folderId, parentFolderId,
			name, description, defaultFileEntryTypeId, fileEntryTypeIds,
			overrideFileEntryTypes, serviceContext);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder updateFolder(
		long folderId, long parentFolderId, java.lang.String name,
		java.lang.String description, long defaultFileEntryTypeId,
		java.util.List<java.lang.Long> fileEntryTypeIds, int restrictionType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.updateFolder(folderId, parentFolderId,
			name, description, defaultFileEntryTypeId, fileEntryTypeIds,
			restrictionType, serviceContext);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #
	updateFolderAndFileEntryTypes(long, long, long, String,
	String, long, List, int, ServiceContext)}
	*/
	@Deprecated
	@Override
	public com.liferay.document.library.kernel.model.DLFolder updateFolderAndFileEntryTypes(
		long userId, long folderId, long parentFolderId, java.lang.String name,
		java.lang.String description, long defaultFileEntryTypeId,
		java.util.List<java.lang.Long> fileEntryTypeIds,
		boolean overrideFileEntryTypes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.updateFolderAndFileEntryTypes(userId,
			folderId, parentFolderId, name, description,
			defaultFileEntryTypeId, fileEntryTypeIds, overrideFileEntryTypes,
			serviceContext);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder updateFolderAndFileEntryTypes(
		long userId, long folderId, long parentFolderId, java.lang.String name,
		java.lang.String description, long defaultFileEntryTypeId,
		java.util.List<java.lang.Long> fileEntryTypeIds, int restrictionType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.updateFolderAndFileEntryTypes(userId,
			folderId, parentFolderId, name, description,
			defaultFileEntryTypeId, fileEntryTypeIds, restrictionType,
			serviceContext);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFolder updateStatus(
		long userId, long folderId, int status,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.updateStatus(userId, folderId, status,
			workflowContext, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dlFolderLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dlFolderLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dlFolderLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dlFolderLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.lock.Lock lockFolder(long userId,
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.lockFolder(userId, folderId);
	}

	@Override
	public com.liferay.portal.kernel.lock.Lock lockFolder(long userId,
		long folderId, java.lang.String owner, boolean inheritable,
		long expirationTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.lockFolder(userId, folderId, owner,
			inheritable, expirationTime);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dlFolderLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getCompanyFoldersCount(long companyId) {
		return _dlFolderLocalService.getCompanyFoldersCount(companyId);
	}

	@Override
	public int getDLFileEntryTypeDLFoldersCount(long fileEntryTypeId) {
		return _dlFolderLocalService.getDLFileEntryTypeDLFoldersCount(fileEntryTypeId);
	}

	/**
	* Returns the number of document library folders.
	*
	* @return the number of document library folders
	*/
	@Override
	public int getDLFoldersCount() {
		return _dlFolderLocalService.getDLFoldersCount();
	}

	@Override
	public int getFileEntriesAndFileShortcutsCount(long groupId, long folderId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<?> queryDefinition) {
		return _dlFolderLocalService.getFileEntriesAndFileShortcutsCount(groupId,
			folderId, queryDefinition);
	}

	@Override
	public int getFoldersAndFileEntriesAndFileShortcutsCount(long groupId,
		long folderId, java.lang.String[] mimeTypes,
		boolean includeMountFolders,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<?> queryDefinition) {
		return _dlFolderLocalService.getFoldersAndFileEntriesAndFileShortcutsCount(groupId,
			folderId, mimeTypes, includeMountFolders, queryDefinition);
	}

	@Override
	public int getFoldersCount(long groupId, long parentFolderId) {
		return _dlFolderLocalService.getFoldersCount(groupId, parentFolderId);
	}

	@Override
	public int getFoldersCount(long groupId, long parentFolderId,
		boolean includeMountfolders) {
		return _dlFolderLocalService.getFoldersCount(groupId, parentFolderId,
			includeMountfolders);
	}

	@Override
	public int getFoldersCount(long groupId, long parentFolderId, int status,
		boolean includeMountfolders) {
		return _dlFolderLocalService.getFoldersCount(groupId, parentFolderId,
			status, includeMountfolders);
	}

	@Override
	public int getMountFoldersCount(long groupId, long parentFolderId) {
		return _dlFolderLocalService.getMountFoldersCount(groupId,
			parentFolderId);
	}

	@Override
	public int getRepositoryFoldersCount(long repositoryId) {
		return _dlFolderLocalService.getRepositoryFoldersCount(repositoryId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _dlFolderLocalService.getOSGiServiceIdentifier();
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
		return _dlFolderLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dlFolderLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dlFolderLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getCompanyFolders(
		long companyId, int start, int end) {
		return _dlFolderLocalService.getCompanyFolders(companyId, start, end);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getDLFileEntryTypeDLFolders(
		long fileEntryTypeId) {
		return _dlFolderLocalService.getDLFileEntryTypeDLFolders(fileEntryTypeId);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getDLFileEntryTypeDLFolders(
		long fileEntryTypeId, int start, int end) {
		return _dlFolderLocalService.getDLFileEntryTypeDLFolders(fileEntryTypeId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getDLFileEntryTypeDLFolders(
		long fileEntryTypeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFolder> orderByComparator) {
		return _dlFolderLocalService.getDLFileEntryTypeDLFolders(fileEntryTypeId,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the document library folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @return the range of document library folders
	*/
	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getDLFolders(
		int start, int end) {
		return _dlFolderLocalService.getDLFolders(start, end);
	}

	/**
	* Returns all the document library folders matching the UUID and company.
	*
	* @param uuid the UUID of the document library folders
	* @param companyId the primary key of the company
	* @return the matching document library folders, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getDLFoldersByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _dlFolderLocalService.getDLFoldersByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of document library folders matching the UUID and company.
	*
	* @param uuid the UUID of the document library folders
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of document library folders
	* @param end the upper bound of the range of document library folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching document library folders, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getDLFoldersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFolder> orderByComparator) {
		return _dlFolderLocalService.getDLFoldersByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<java.lang.Object> getFileEntriesAndFileShortcuts(
		long groupId, long folderId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<?> queryDefinition) {
		return _dlFolderLocalService.getFileEntriesAndFileShortcuts(groupId,
			folderId, queryDefinition);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getGroupFolderIds(long,
	long)}
	*/
	@Deprecated
	@Override
	public java.util.List<java.lang.Long> getFolderIds(long groupId,
		long parentFolderId) {
		return _dlFolderLocalService.getFolderIds(groupId, parentFolderId);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getFolders(
		long groupId, long parentFolderId) {
		return _dlFolderLocalService.getFolders(groupId, parentFolderId);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getFolders(
		long groupId, long parentFolderId, boolean includeMountfolders) {
		return _dlFolderLocalService.getFolders(groupId, parentFolderId,
			includeMountfolders);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getFolders(
		long groupId, long parentFolderId, boolean includeMountfolders,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFolder> obc) {
		return _dlFolderLocalService.getFolders(groupId, parentFolderId,
			includeMountfolders, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getFolders(
		long groupId, long parentFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFolder> obc) {
		return _dlFolderLocalService.getFolders(groupId, parentFolderId, start,
			end, obc);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getFolders(
		long groupId, long parentFolderId, int status,
		boolean includeMountfolders, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFolder> obc) {
		return _dlFolderLocalService.getFolders(groupId, parentFolderId,
			status, includeMountfolders, start, end, obc);
	}

	@Override
	public java.util.List<java.lang.Object> getFoldersAndFileEntriesAndFileShortcuts(
		long groupId, long folderId, java.lang.String[] mimeTypes,
		boolean includeMountFolders,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<?> queryDefinition) {
		return _dlFolderLocalService.getFoldersAndFileEntriesAndFileShortcuts(groupId,
			folderId, mimeTypes, includeMountFolders, queryDefinition);
	}

	@Override
	public java.util.List<java.lang.Long> getGroupFolderIds(long groupId,
		long parentFolderId) {
		return _dlFolderLocalService.getGroupFolderIds(groupId, parentFolderId);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getMountFolders(
		long groupId, long parentFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFolder> obc) {
		return _dlFolderLocalService.getMountFolders(groupId, parentFolderId,
			start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getNoAssetFolders() {
		return _dlFolderLocalService.getNoAssetFolders();
	}

	@Override
	public java.util.List<java.lang.Long> getRepositoryFolderIds(
		long repositoryId, long parentFolderId) {
		return _dlFolderLocalService.getRepositoryFolderIds(repositoryId,
			parentFolderId);
	}

	@Override
	public java.util.List<com.liferay.document.library.kernel.model.DLFolder> getRepositoryFolders(
		long repositoryId, int start, int end) {
		return _dlFolderLocalService.getRepositoryFolders(repositoryId, start,
			end);
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
		return _dlFolderLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dlFolderLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public long getFolderId(long companyId, long folderId) {
		return _dlFolderLocalService.getFolderId(companyId, folderId);
	}

	/**
	* Returns the fileEntryTypeIds of the document library file entry types associated with the document library folder.
	*
	* @param folderId the folderId of the document library folder
	* @return long[] the fileEntryTypeIds of document library file entry types associated with the document library folder
	*/
	@Override
	public long[] getDLFileEntryTypePrimaryKeys(long folderId) {
		return _dlFolderLocalService.getDLFileEntryTypePrimaryKeys(folderId);
	}

	@Override
	public void addDLFileEntryTypeDLFolder(long fileEntryTypeId,
		com.liferay.document.library.kernel.model.DLFolder dlFolder) {
		_dlFolderLocalService.addDLFileEntryTypeDLFolder(fileEntryTypeId,
			dlFolder);
	}

	@Override
	public void addDLFileEntryTypeDLFolder(long fileEntryTypeId, long folderId) {
		_dlFolderLocalService.addDLFileEntryTypeDLFolder(fileEntryTypeId,
			folderId);
	}

	@Override
	public void addDLFileEntryTypeDLFolders(long fileEntryTypeId,
		java.util.List<com.liferay.document.library.kernel.model.DLFolder> dlFolders) {
		_dlFolderLocalService.addDLFileEntryTypeDLFolders(fileEntryTypeId,
			dlFolders);
	}

	@Override
	public void addDLFileEntryTypeDLFolders(long fileEntryTypeId,
		long[] folderIds) {
		_dlFolderLocalService.addDLFileEntryTypeDLFolders(fileEntryTypeId,
			folderIds);
	}

	@Override
	public void clearDLFileEntryTypeDLFolders(long fileEntryTypeId) {
		_dlFolderLocalService.clearDLFileEntryTypeDLFolders(fileEntryTypeId);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #deleteAllByGroup(long)}
	*/
	@Deprecated
	@Override
	public void deleteAll(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFolderLocalService.deleteAll(groupId);
	}

	@Override
	public void deleteAllByGroup(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFolderLocalService.deleteAllByGroup(groupId);
	}

	@Override
	public void deleteAllByRepository(long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFolderLocalService.deleteAllByRepository(repositoryId);
	}

	@Override
	public void deleteDLFileEntryTypeDLFolder(long fileEntryTypeId,
		com.liferay.document.library.kernel.model.DLFolder dlFolder) {
		_dlFolderLocalService.deleteDLFileEntryTypeDLFolder(fileEntryTypeId,
			dlFolder);
	}

	@Override
	public void deleteDLFileEntryTypeDLFolder(long fileEntryTypeId,
		long folderId) {
		_dlFolderLocalService.deleteDLFileEntryTypeDLFolder(fileEntryTypeId,
			folderId);
	}

	@Override
	public void deleteDLFileEntryTypeDLFolders(long fileEntryTypeId,
		java.util.List<com.liferay.document.library.kernel.model.DLFolder> dlFolders) {
		_dlFolderLocalService.deleteDLFileEntryTypeDLFolders(fileEntryTypeId,
			dlFolders);
	}

	@Override
	public void deleteDLFileEntryTypeDLFolders(long fileEntryTypeId,
		long[] folderIds) {
		_dlFolderLocalService.deleteDLFileEntryTypeDLFolders(fileEntryTypeId,
			folderIds);
	}

	@Override
	public void getGroupSubfolderIds(java.util.List<java.lang.Long> folderIds,
		long groupId, long folderId) {
		_dlFolderLocalService.getGroupSubfolderIds(folderIds, groupId, folderId);
	}

	@Override
	public void getRepositorySubfolderIds(
		java.util.List<java.lang.Long> folderIds, long repositoryId,
		long folderId) {
		_dlFolderLocalService.getRepositorySubfolderIds(folderIds,
			repositoryId, folderId);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getGroupSubfolderIds(List,
	long, long)}
	*/
	@Deprecated
	@Override
	public void getSubfolderIds(java.util.List<java.lang.Long> folderIds,
		long groupId, long folderId) {
		_dlFolderLocalService.getSubfolderIds(folderIds, groupId, folderId);
	}

	@Override
	public void rebuildTree(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFolderLocalService.rebuildTree(companyId);
	}

	@Override
	public void rebuildTree(long companyId, long parentFolderId,
		java.lang.String parentTreePath, boolean reindex)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFolderLocalService.rebuildTree(companyId, parentFolderId,
			parentTreePath, reindex);
	}

	@Override
	public void setDLFileEntryTypeDLFolders(long fileEntryTypeId,
		long[] folderIds) {
		_dlFolderLocalService.setDLFileEntryTypeDLFolders(fileEntryTypeId,
			folderIds);
	}

	@Override
	public void unlockFolder(long folderId, java.lang.String lockUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFolderLocalService.unlockFolder(folderId, lockUuid);
	}

	@Override
	public void unlockFolder(long groupId, long parentFolderId,
		java.lang.String name, java.lang.String lockUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFolderLocalService.unlockFolder(groupId, parentFolderId, name,
			lockUuid);
	}

	@Override
	public void updateLastPostDate(long folderId, java.util.Date lastPostDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dlFolderLocalService.updateLastPostDate(folderId, lastPostDate);
	}

	@Override
	public DLFolderLocalService getWrappedService() {
		return _dlFolderLocalService;
	}

	@Override
	public void setWrappedService(DLFolderLocalService dlFolderLocalService) {
		_dlFolderLocalService = dlFolderLocalService;
	}

	private DLFolderLocalService _dlFolderLocalService;
}