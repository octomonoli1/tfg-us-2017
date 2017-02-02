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

package com.liferay.sync.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for SyncDLObject. This utility wraps
 * {@link com.liferay.sync.service.impl.SyncDLObjectServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObjectService
 * @see com.liferay.sync.service.base.SyncDLObjectServiceBaseImpl
 * @see com.liferay.sync.service.impl.SyncDLObjectServiceImpl
 * @generated
 */
@ProviderType
public class SyncDLObjectServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.sync.service.impl.SyncDLObjectServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.model.Group getGroup(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGroup(groupId);
	}

	public static com.liferay.sync.model.SyncDLObject addFileEntry(
		long repositoryId, long folderId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		java.io.File file, java.lang.String checksum,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addFileEntry(repositoryId, folderId, sourceFileName,
			mimeType, title, description, changeLog, file, checksum,
			serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject addFolder(
		long repositoryId, long parentFolderId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addFolder(repositoryId, parentFolderId, name, description,
			serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject cancelCheckOut(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().cancelCheckOut(fileEntryId);
	}

	public static com.liferay.sync.model.SyncDLObject checkInFileEntry(
		long fileEntryId, boolean majorVersion, java.lang.String changeLog,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .checkInFileEntry(fileEntryId, majorVersion, changeLog,
			serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject checkOutFileEntry(
		long fileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().checkOutFileEntry(fileEntryId, serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject checkOutFileEntry(
		long fileEntryId, java.lang.String owner, long expirationTime,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .checkOutFileEntry(fileEntryId, owner, expirationTime,
			serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject copyFileEntry(
		long sourceFileEntryId, long repositoryId, long folderId,
		java.lang.String sourceFileName, java.lang.String title,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .copyFileEntry(sourceFileEntryId, repositoryId, folderId,
			sourceFileName, title, serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject getFileEntrySyncDLObject(
		long repositoryId, long folderId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getFileEntrySyncDLObject(repositoryId, folderId, title);
	}

	public static com.liferay.sync.model.SyncDLObject getFolderSyncDLObject(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFolderSyncDLObject(folderId);
	}

	public static com.liferay.sync.model.SyncDLObject getFolderSyncDLObject(
		long repositoryId, long parentFolderId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getFolderSyncDLObject(repositoryId, parentFolderId, name);
	}

	public static com.liferay.sync.model.SyncDLObject moveFileEntry(
		long fileEntryId, long newFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .moveFileEntry(fileEntryId, newFolderId, serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject moveFileEntryToTrash(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveFileEntryToTrash(fileEntryId);
	}

	public static com.liferay.sync.model.SyncDLObject moveFolder(
		long folderId, long parentFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveFolder(folderId, parentFolderId, serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject moveFolderToTrash(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveFolderToTrash(folderId);
	}

	public static com.liferay.sync.model.SyncDLObject patchFileEntry(
		long fileEntryId, long sourceVersionId,
		java.lang.String sourceFileName, java.lang.String mimeType,
		java.lang.String title, java.lang.String description,
		java.lang.String changeLog, boolean majorVersion,
		java.io.File deltaFile, java.lang.String checksum,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .patchFileEntry(fileEntryId, sourceVersionId,
			sourceFileName, mimeType, title, description, changeLog,
			majorVersion, deltaFile, checksum, serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject restoreFileEntryFromTrash(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().restoreFileEntryFromTrash(fileEntryId);
	}

	public static com.liferay.sync.model.SyncDLObject restoreFolderFromTrash(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().restoreFolderFromTrash(folderId);
	}

	public static com.liferay.sync.model.SyncDLObject updateFileEntry(
		long fileEntryId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		boolean majorVersion, java.io.File file, java.lang.String checksum,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateFileEntry(fileEntryId, sourceFileName, mimeType,
			title, description, changeLog, majorVersion, file, checksum,
			serviceContext);
	}

	public static com.liferay.sync.model.SyncDLObject updateFolder(
		long folderId, java.lang.String name, java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateFolder(folderId, name, description, serviceContext);
	}

	public static java.lang.Object getSyncContext()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSyncContext();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.lang.String getSyncDLObjectUpdate(long repositoryId,
		long lastAccessTime, int max)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getSyncDLObjectUpdate(repositoryId, lastAccessTime, max);
	}

	public static java.lang.String getSyncDLObjectUpdate(long repositoryId,
		long lastAccessTime, int max, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getSyncDLObjectUpdate(repositoryId, lastAccessTime, max,
			retrieveFromCache);
	}

	public static java.lang.String getSyncDLObjectUpdate(long repositoryId,
		long parentFolderId, long lastAccessTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getSyncDLObjectUpdate(repositoryId, parentFolderId,
			lastAccessTime);
	}

	public static java.util.List<com.liferay.sync.model.SyncDLObject> getAllFolderSyncDLObjects(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAllFolderSyncDLObjects(repositoryId);
	}

	public static java.util.List<com.liferay.sync.model.SyncDLObject> getFileEntrySyncDLObjects(
		long repositoryId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFileEntrySyncDLObjects(repositoryId, folderId);
	}

	public static java.util.List<com.liferay.sync.model.SyncDLObject> getFolderSyncDLObjects(
		long repositoryId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFolderSyncDLObjects(repositoryId, parentFolderId);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserSitesGroups()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserSitesGroups();
	}

	public static java.util.Map<java.lang.String, java.lang.Object> updateFileEntries(
		java.io.File zipFile)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateFileEntries(zipFile);
	}

	public static long getLatestModifiedTime()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLatestModifiedTime();
	}

	public static SyncDLObjectService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SyncDLObjectService, SyncDLObjectService> _serviceTracker =
		ServiceTrackerFactory.open(SyncDLObjectService.class);
}