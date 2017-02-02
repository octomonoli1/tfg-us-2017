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

package com.liferay.portal.repository.capabilities;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.lock.Lock;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.capabilities.CapabilityProvider;
import com.liferay.portal.kernel.repository.event.RepositoryEventTrigger;
import com.liferay.portal.kernel.repository.event.RepositoryEventType;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.repository.model.RepositoryEntry;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.File;
import java.io.InputStream;

import java.util.List;

/**
 * @author Adolfo Pérez
 */
public class CapabilityRepository
	extends BaseCapabilityRepository<Repository> implements Repository {

	public CapabilityRepository(
		Repository repository, CapabilityProvider capabilityProvider,
		RepositoryEventTrigger repositoryEventTrigger) {

		super(repository, capabilityProvider);

		_repositoryEventTrigger = repositoryEventTrigger;
	}

	@Override
	public FileEntry addFileEntry(
			long userId, long folderId, String sourceFileName, String mimeType,
			String title, String description, String changeLog, File file,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		FileEntry fileEntry = repository.addFileEntry(
			userId, folderId, sourceFileName, mimeType, title, description,
			changeLog, file, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Add.class, FileEntry.class, fileEntry);

		return fileEntry;
	}

	@Override
	public FileEntry addFileEntry(
			long userId, long folderId, String sourceFileName, String mimeType,
			String title, String description, String changeLog, InputStream is,
			long size, ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		FileEntry fileEntry = repository.addFileEntry(
			userId, folderId, sourceFileName, mimeType, title, description,
			changeLog, is, size, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Add.class, FileEntry.class, fileEntry);

		return fileEntry;
	}

	/**
	 * @deprecated As of 7.0.0, see {@link #addFileEntry(long, long, String,
	 *             String, String, String, String, File, ServiceContext)}
	 */
	@Deprecated
	@Override
	public FileEntry addFileEntry(
			long folderId, String sourceFileName, String mimeType, String title,
			String description, String changeLog, File file,
			ServiceContext serviceContext)
		throws PortalException {

		return addFileEntry(
			com.liferay.portal.kernel.repository.util.RepositoryUserUtil.
				getUserId(),
			folderId, sourceFileName, mimeType, title, description, changeLog,
			file, serviceContext);
	}

	/**
	 * @deprecated As of 7.0.0, see {@link #addFileEntry(long, long, String,
	 *             String, String, String, String, InputStream, long,
	 *             ServiceContext)}
	 */
	@Deprecated
	@Override
	public FileEntry addFileEntry(
			long folderId, String sourceFileName, String mimeType, String title,
			String description, String changeLog, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException {

		return addFileEntry(
			com.liferay.portal.kernel.repository.util.RepositoryUserUtil.
				getUserId(),
			folderId, sourceFileName, mimeType, title, description, changeLog,
			is, size, serviceContext);
	}

	@Override
	public FileShortcut addFileShortcut(
			long userId, long folderId, long toFileEntryId,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		FileShortcut fileShortcut = repository.addFileShortcut(
			userId, folderId, toFileEntryId, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Add.class, FileShortcut.class, fileShortcut);

		return fileShortcut;
	}

	@Override
	public Folder addFolder(
			long userId, long parentFolderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		Folder folder = repository.addFolder(
			userId, parentFolderId, name, description, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Add.class, Folder.class, folder);

		return folder;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #addFolder(long, long,
	 *             String, String, ServiceContext)}
	 */
	@Deprecated
	@Override
	public Folder addFolder(
			long parentFolderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		return addFolder(
			com.liferay.portal.kernel.repository.util.RepositoryUserUtil.
				getUserId(),
			parentFolderId, name, description, serviceContext);
	}

	@Override
	public FileVersion cancelCheckOut(long fileEntryId) throws PortalException {
		Repository repository = getRepository();

		FileVersion fileVersion = repository.cancelCheckOut(fileEntryId);

		if (fileVersion != null) {
			_repositoryEventTrigger.trigger(
				RepositoryEventType.Update.class, FileEntry.class,
				fileVersion.getFileEntry());
		}

		return fileVersion;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #checkInFileEntry(long, long,
	 *             boolean, String, ServiceContext)}
	 */
	@Deprecated
	@Override
	public void checkInFileEntry(
			long fileEntryId, boolean major, String changeLog,
			ServiceContext serviceContext)
		throws PortalException {

		checkInFileEntry(
			com.liferay.portal.kernel.repository.util.RepositoryUserUtil.
				getUserId(),
			fileEntryId, major, changeLog, serviceContext);
	}

	@Override
	public void checkInFileEntry(
			long userId, long fileEntryId, boolean majorVersion,
			String changeLog, ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		repository.checkInFileEntry(
			userId, fileEntryId, majorVersion, changeLog, serviceContext);

		FileEntry fileEntry = repository.getFileEntry(fileEntryId);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Update.class, FileEntry.class, fileEntry);
	}

	@Override
	public void checkInFileEntry(
			long userId, long fileEntryId, String lockUuid,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		repository.checkInFileEntry(
			userId, fileEntryId, lockUuid, serviceContext);

		FileEntry fileEntry = repository.getFileEntry(fileEntryId);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Update.class, FileEntry.class, fileEntry);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #checkInFileEntry(long, long,
	 *             String, ServiceContext)}
	 */
	@Deprecated
	@Override
	public void checkInFileEntry(
			long fileEntryId, String lockUuid, ServiceContext serviceContext)
		throws PortalException {

		checkInFileEntry(
			com.liferay.portal.kernel.repository.util.RepositoryUserUtil.
				getUserId(),
			fileEntryId, lockUuid, serviceContext);
	}

	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		FileEntry fileEntry = repository.checkOutFileEntry(
			fileEntryId, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Update.class, FileEntry.class, fileEntry);

		return fileEntry;
	}

	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, String owner, long expirationTime,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		FileEntry fileEntry = repository.checkOutFileEntry(
			fileEntryId, owner, expirationTime, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Update.class, FileEntry.class, fileEntry);

		return fileEntry;
	}

	@Override
	public FileEntry copyFileEntry(
			long userId, long groupId, long fileEntryId, long destFolderId,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		FileEntry fileEntry = repository.copyFileEntry(
			userId, groupId, fileEntryId, destFolderId, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Add.class, FileEntry.class, fileEntry);

		return fileEntry;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #copyFileEntry(long, long,
	 *             long, long, ServiceContext)}
	 */
	@Deprecated
	@Override
	public FileEntry copyFileEntry(
			long groupId, long fileEntryId, long destFolderId,
			ServiceContext serviceContext)
		throws PortalException {

		return copyFileEntry(
			com.liferay.portal.kernel.repository.util.RepositoryUserUtil.
				getUserId(),
			groupId, fileEntryId, destFolderId, serviceContext);
	}

	@Override
	public void deleteAll() throws PortalException {
		Repository repository = getRepository();

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Delete.class, Repository.class, repository);

		repository.deleteAll();
	}

	@Override
	public void deleteFileEntry(long fileEntryId) throws PortalException {
		Repository repository = getRepository();

		FileEntry fileEntry = repository.getFileEntry(fileEntryId);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Delete.class, FileEntry.class, fileEntry);

		repository.deleteFileEntry(fileEntryId);
	}

	@Override
	public void deleteFileEntry(long folderId, String title)
		throws PortalException {

		Repository repository = getRepository();

		FileEntry fileEntry = repository.getFileEntry(folderId, title);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Delete.class, FileEntry.class, fileEntry);

		repository.deleteFileEntry(folderId, title);
	}

	@Override
	public void deleteFileShortcut(long fileShortcutId) throws PortalException {
		Repository repository = getRepository();

		FileShortcut fileShortcut = repository.getFileShortcut(fileShortcutId);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Delete.class, FileShortcut.class, fileShortcut);

		repository.deleteFileShortcut(fileShortcutId);
	}

	@Override
	public void deleteFileShortcuts(long toFileEntryId) throws PortalException {
		Repository repository = getRepository();

		FileEntry fileEntry = repository.getFileEntry(toFileEntryId);

		List<FileShortcut> fileShortcuts = fileEntry.getFileShortcuts();

		for (FileShortcut fileShortcut : fileShortcuts) {
			_repositoryEventTrigger.trigger(
				RepositoryEventType.Delete.class, FileShortcut.class,
				fileShortcut);
		}

		repository.deleteFileShortcuts(toFileEntryId);
	}

	@Override
	public void deleteFileVersion(long fileEntryId, String version)
		throws PortalException {

		Repository repository = getRepository();

		FileEntry fileEntry = repository.getFileEntry(fileEntryId);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Update.class, FileEntry.class, fileEntry);

		repository.deleteFileVersion(fileEntryId, version);
	}

	@Override
	public void deleteFolder(long folderId) throws PortalException {
		Repository repository = getRepository();

		Folder folder = repository.getFolder(folderId);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Delete.class, Folder.class, folder);

		repository.deleteFolder(folderId);
	}

	@Override
	public void deleteFolder(long parentFolderId, String name)
		throws PortalException {

		Repository repository = getRepository();

		Folder folder = repository.getFolder(parentFolderId, name);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Delete.class, Folder.class, folder);

		repository.deleteFolder(parentFolderId, name);
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, int status, int start, int end,
			OrderByComparator<FileEntry> obc)
		throws PortalException {

		return getRepository().getFileEntries(
			folderId, status, start, end, obc);
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, int start, int end, OrderByComparator<FileEntry> obc)
		throws PortalException {

		return getRepository().getFileEntries(folderId, start, end, obc);
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, long fileEntryTypeId, int start, int end,
			OrderByComparator<FileEntry> obc)
		throws PortalException {

		return getRepository().getFileEntries(
			folderId, fileEntryTypeId, start, end, obc);
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator<FileEntry> obc)
		throws PortalException {

		return getRepository().getFileEntries(
			folderId, mimeTypes, start, end, obc);
	}

	@Override
	public List<RepositoryEntry> getFileEntriesAndFileShortcuts(
			long folderId, int status, int start, int end)
		throws PortalException {

		return getRepository().getFileEntriesAndFileShortcuts(
			folderId, status, start, end);
	}

	@Override
	public int getFileEntriesAndFileShortcutsCount(long folderId, int status)
		throws PortalException {

		return getRepository().getFileEntriesAndFileShortcutsCount(
			folderId, status);
	}

	@Override
	public int getFileEntriesAndFileShortcutsCount(
			long folderId, int status, String[] mimeTypes)
		throws PortalException {

		return getRepository().getFileEntriesAndFileShortcutsCount(
			folderId, status, mimeTypes);
	}

	@Override
	public int getFileEntriesCount(long folderId) throws PortalException {
		return getRepository().getFileEntriesCount(folderId);
	}

	@Override
	public int getFileEntriesCount(long folderId, int status)
		throws PortalException {

		return getRepository().getFileEntriesCount(folderId, status);
	}

	@Override
	public int getFileEntriesCount(long folderId, long fileEntryTypeId)
		throws PortalException {

		return getRepository().getFileEntriesCount(folderId, fileEntryTypeId);
	}

	@Override
	public int getFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException {

		return getRepository().getFileEntriesCount(folderId, mimeTypes);
	}

	@Override
	public FileEntry getFileEntry(long fileEntryId) throws PortalException {
		return getRepository().getFileEntry(fileEntryId);
	}

	@Override
	public FileEntry getFileEntry(long folderId, String title)
		throws PortalException {

		return getRepository().getFileEntry(folderId, title);
	}

	@Override
	public FileEntry getFileEntryByUuid(String uuid) throws PortalException {
		return getRepository().getFileEntryByUuid(uuid);
	}

	@Override
	public FileShortcut getFileShortcut(long fileShortcutId)
		throws PortalException {

		return getRepository().getFileShortcut(fileShortcutId);
	}

	@Override
	public FileVersion getFileVersion(long fileVersionId)
		throws PortalException {

		return getRepository().getFileVersion(fileVersionId);
	}

	@Override
	public Folder getFolder(long folderId) throws PortalException {
		return getRepository().getFolder(folderId);
	}

	@Override
	public Folder getFolder(long parentFolderId, String name)
		throws PortalException {

		return getRepository().getFolder(parentFolderId, name);
	}

	@Override
	public List<Folder> getFolders(
			long parentFolderId, boolean includeMountFolders, int start,
			int end, OrderByComparator<Folder> obc)
		throws PortalException {

		return getRepository().getFolders(
			parentFolderId, includeMountFolders, start, end, obc);
	}

	@Override
	public List<Folder> getFolders(
			long parentFolderId, int status, boolean includeMountFolders,
			int start, int end, OrderByComparator<Folder> obc)
		throws PortalException {

		return getRepository().getFolders(
			parentFolderId, status, includeMountFolders, start, end, obc);
	}

	@Override
	public List<RepositoryEntry> getFoldersAndFileEntriesAndFileShortcuts(
			long folderId, int status, boolean includeMountFolders, int start,
			int end, OrderByComparator<?> obc)
		throws PortalException {

		return getRepository().getFoldersAndFileEntriesAndFileShortcuts(
			folderId, status, includeMountFolders, start, end, obc);
	}

	@Override
	public List<RepositoryEntry> getFoldersAndFileEntriesAndFileShortcuts(
			long folderId, int status, String[] mimetypes,
			boolean includeMountFolders, int start, int end,
			OrderByComparator<?> obc)
		throws PortalException {

		return getRepository().getFoldersAndFileEntriesAndFileShortcuts(
			folderId, status, mimetypes, includeMountFolders, start, end, obc);
	}

	@Override
	public int getFoldersAndFileEntriesAndFileShortcutsCount(
			long folderId, int status, boolean includeMountFolders)
		throws PortalException {

		return getRepository().getFoldersAndFileEntriesAndFileShortcutsCount(
			folderId, status, includeMountFolders);
	}

	@Override
	public int getFoldersAndFileEntriesAndFileShortcutsCount(
			long folderId, int status, String[] mimetypes,
			boolean includeMountFolders)
		throws PortalException {

		return getRepository().getFoldersAndFileEntriesAndFileShortcutsCount(
			folderId, status, mimetypes, includeMountFolders);
	}

	@Override
	public int getFoldersCount(long parentFolderId, boolean includeMountfolders)
		throws PortalException {

		return getRepository().getFoldersCount(
			parentFolderId, includeMountfolders);
	}

	@Override
	public int getFoldersCount(
			long parentFolderId, int status, boolean includeMountfolders)
		throws PortalException {

		return getRepository().getFoldersCount(
			parentFolderId, status, includeMountfolders);
	}

	@Override
	public int getFoldersFileEntriesCount(List<Long> folderIds, int status)
		throws PortalException {

		return getRepository().getFoldersFileEntriesCount(folderIds, status);
	}

	@Override
	public List<Folder> getMountFolders(
			long parentFolderId, int start, int end,
			OrderByComparator<Folder> obc)
		throws PortalException {

		return getRepository().getMountFolders(parentFolderId, start, end, obc);
	}

	@Override
	public int getMountFoldersCount(long parentFolderId)
		throws PortalException {

		return getRepository().getMountFoldersCount(parentFolderId);
	}

	@Override
	public List<FileEntry> getRepositoryFileEntries(
			long userId, long rootFolderId, int start, int end,
			OrderByComparator<FileEntry> obc)
		throws PortalException {

		return getRepository().getRepositoryFileEntries(
			userId, rootFolderId, start, end, obc);
	}

	@Override
	public List<FileEntry> getRepositoryFileEntries(
			long userId, long rootFolderId, String[] mimeTypes, int status,
			int start, int end, OrderByComparator<FileEntry> obc)
		throws PortalException {

		return getRepository().getRepositoryFileEntries(
			userId, rootFolderId, mimeTypes, status, start, end, obc);
	}

	@Override
	public int getRepositoryFileEntriesCount(long userId, long rootFolderId)
		throws PortalException {

		return getRepository().getRepositoryFileEntriesCount(
			userId, rootFolderId);
	}

	@Override
	public int getRepositoryFileEntriesCount(
			long userId, long rootFolderId, String[] mimeTypes, int status)
		throws PortalException {

		return getRepository().getRepositoryFileEntriesCount(
			userId, rootFolderId, mimeTypes, status);
	}

	@Override
	public long getRepositoryId() {
		return getRepository().getRepositoryId();
	}

	@Override
	public void getSubfolderIds(List<Long> folderIds, long folderId)
		throws PortalException {

		getRepository().getSubfolderIds(folderIds, folderId);
	}

	@Override
	public List<Long> getSubfolderIds(long folderId, boolean recurse)
		throws PortalException {

		return getRepository().getSubfolderIds(folderId, recurse);
	}

	@Override
	public Lock lockFolder(long folderId) throws PortalException {
		return getRepository().lockFolder(folderId);
	}

	@Override
	public Lock lockFolder(
			long folderId, String owner, boolean inheritable,
			long expirationTime)
		throws PortalException {

		return getRepository().lockFolder(
			folderId, owner, inheritable, expirationTime);
	}

	@Override
	public FileEntry moveFileEntry(
			long userId, long fileEntryId, long newFolderId,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		FileEntry fileEntry = repository.moveFileEntry(
			userId, fileEntryId, newFolderId, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Move.class, FileEntry.class, fileEntry);

		return fileEntry;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #moveFileEntry(long, long,
	 *             long, ServiceContext)}
	 */
	@Deprecated
	@Override
	public FileEntry moveFileEntry(
			long fileEntryId, long newFolderId, ServiceContext serviceContext)
		throws PortalException {

		return moveFileEntry(
			com.liferay.portal.kernel.repository.util.RepositoryUserUtil.
				getUserId(),
			fileEntryId, newFolderId, serviceContext);
	}

	@Override
	public Folder moveFolder(
			long userId, long folderId, long parentFolderId,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		Folder folder = repository.moveFolder(
			userId, folderId, parentFolderId, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Move.class, Folder.class, folder);

		return folder;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #moveFolder(long, long, long,
	 *             ServiceContext)}
	 */
	@Deprecated
	@Override
	public Folder moveFolder(
			long folderId, long newParentFolderId,
			ServiceContext serviceContext)
		throws PortalException {

		return moveFolder(
			com.liferay.portal.kernel.repository.util.RepositoryUserUtil.
				getUserId(),
			folderId, newParentFolderId, serviceContext);
	}

	@Override
	public Lock refreshFileEntryLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException {

		return getRepository().refreshFileEntryLock(
			lockUuid, companyId, expirationTime);
	}

	@Override
	public Lock refreshFolderLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException {

		return getRepository().refreshFolderLock(
			lockUuid, companyId, expirationTime);
	}

	@Override
	public void revertFileEntry(
			long userId, long fileEntryId, String version,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		repository.revertFileEntry(
			userId, fileEntryId, version, serviceContext);

		FileEntry fileEntry = getFileEntry(fileEntryId);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Update.class, FileEntry.class, fileEntry);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #revertFileEntry(long, long,
	 *             String, ServiceContext)}
	 */
	@Deprecated
	@Override
	public void revertFileEntry(
			long fileEntryId, String version, ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		repository.revertFileEntry(fileEntryId, version, serviceContext);

		FileEntry fileEntry = getFileEntry(fileEntryId);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Update.class, FileEntry.class, fileEntry);
	}

	@Override
	public Hits search(long creatorUserId, int status, int start, int end)
		throws PortalException {

		return getRepository().search(creatorUserId, status, start, end);
	}

	@Override
	public Hits search(
			long creatorUserId, long folderId, String[] mimeTypes, int status,
			int start, int end)
		throws PortalException {

		return getRepository().search(
			creatorUserId, folderId, mimeTypes, status, start, end);
	}

	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		return getRepository().search(searchContext);
	}

	@Override
	public Hits search(SearchContext searchContext, Query query)
		throws SearchException {

		return getRepository().search(searchContext, query);
	}

	@Override
	public void unlockFolder(long folderId, String lockUuid)
		throws PortalException {

		getRepository().unlockFolder(folderId, lockUuid);
	}

	@Override
	public void unlockFolder(long parentFolderId, String name, String lockUuid)
		throws PortalException {

		getRepository().unlockFolder(parentFolderId, name, lockUuid);
	}

	@Override
	public FileEntry updateFileEntry(
			long userId, long fileEntryId, String sourceFileName,
			String mimeType, String title, String description, String changeLog,
			boolean majorVersion, File file, ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		FileEntry fileEntry = repository.updateFileEntry(
			userId, fileEntryId, sourceFileName, mimeType, title, description,
			changeLog, majorVersion, file, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Update.class, FileEntry.class, fileEntry);

		return fileEntry;
	}

	@Override
	public FileEntry updateFileEntry(
			long userId, long fileEntryId, String sourceFileName,
			String mimeType, String title, String description, String changeLog,
			boolean majorVersion, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		FileEntry fileEntry = repository.updateFileEntry(
			userId, fileEntryId, sourceFileName, mimeType, title, description,
			changeLog, majorVersion, is, size, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Update.class, FileEntry.class, fileEntry);

		return fileEntry;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #updateFileEntry(long, long,
	 *             String, String, String, String, String, boolean, File,
	 *             ServiceContext)}
	 */
	@Deprecated
	@Override
	public FileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, File file, ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		FileEntry fileEntry = repository.updateFileEntry(
			fileEntryId, sourceFileName, mimeType, title, description,
			changeLog, majorVersion, file, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Update.class, FileEntry.class, fileEntry);

		return fileEntry;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #updateFileEntry(long, long,
	 *             String, String, String, String, String, boolean, InputStream,
	 *             long, ServiceContext)}
	 */
	@Deprecated
	@Override
	public FileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		FileEntry fileEntry = repository.updateFileEntry(
			fileEntryId, sourceFileName, mimeType, title, description,
			changeLog, majorVersion, is, size, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Update.class, FileEntry.class, fileEntry);

		return fileEntry;
	}

	@Override
	public FileShortcut updateFileShortcut(
			long userId, long fileShortcutId, long folderId, long toFileEntryId,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		FileShortcut fileShortcut = repository.updateFileShortcut(
			userId, fileShortcutId, folderId, toFileEntryId, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Update.class, FileShortcut.class, fileShortcut);

		return fileShortcut;
	}

	@Override
	public void updateFileShortcuts(
			long oldToFileEntryId, long newToFileEntryId)
		throws PortalException {

		Repository repository = getRepository();

		FileEntry oldToFileEntry = repository.getFileEntry(oldToFileEntryId);

		List<FileShortcut> fileShortcuts = oldToFileEntry.getFileShortcuts();

		for (FileShortcut fileShortcut : fileShortcuts) {
			_repositoryEventTrigger.trigger(
				RepositoryEventType.Update.class, FileShortcut.class,
				fileShortcut);
		}

		repository.updateFileShortcuts(oldToFileEntryId, newToFileEntryId);
	}

	@Override
	public Folder updateFolder(
			long folderId, long parentFolderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		Folder folder = repository.updateFolder(
			folderId, parentFolderId, name, description, serviceContext);

		_repositoryEventTrigger.trigger(
			RepositoryEventType.Update.class, Folder.class, folder);

		return folder;
	}

	@Override
	public Folder updateFolder(
			long folderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		Repository repository = getRepository();

		Folder folder = repository.updateFolder(
			folderId, name, description, serviceContext);

		if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			_repositoryEventTrigger.trigger(
				RepositoryEventType.Update.class, Folder.class, folder);
		}

		return folder;
	}

	@Override
	public boolean verifyFileEntryCheckOut(long fileEntryId, String lockUuid)
		throws PortalException {

		return getRepository().verifyFileEntryCheckOut(fileEntryId, lockUuid);
	}

	@Override
	public boolean verifyFileEntryLock(long fileEntryId, String lockUuid)
		throws PortalException {

		return getRepository().verifyFileEntryLock(fileEntryId, lockUuid);
	}

	@Override
	public boolean verifyInheritableLock(long folderId, String lockUuid)
		throws PortalException {

		return getRepository().verifyInheritableLock(folderId, lockUuid);
	}

	private final RepositoryEventTrigger _repositoryEventTrigger;

}