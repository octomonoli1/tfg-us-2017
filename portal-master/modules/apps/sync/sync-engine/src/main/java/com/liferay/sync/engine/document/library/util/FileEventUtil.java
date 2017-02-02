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

package com.liferay.sync.engine.document.library.util;

import com.liferay.sync.engine.document.library.event.AddFileEntryEvent;
import com.liferay.sync.engine.document.library.event.AddFolderEvent;
import com.liferay.sync.engine.document.library.event.CancelCheckOutEvent;
import com.liferay.sync.engine.document.library.event.CheckInFileEntryEvent;
import com.liferay.sync.engine.document.library.event.CheckOutFileEntryEvent;
import com.liferay.sync.engine.document.library.event.CopyFileEntryEvent;
import com.liferay.sync.engine.document.library.event.DownloadFileEvent;
import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.document.library.event.GetAllFolderSyncDLObjectsEvent;
import com.liferay.sync.engine.document.library.event.GetSyncDLObjectUpdateEvent;
import com.liferay.sync.engine.document.library.event.MoveFileEntryEvent;
import com.liferay.sync.engine.document.library.event.MoveFileEntryToTrashEvent;
import com.liferay.sync.engine.document.library.event.MoveFolderEvent;
import com.liferay.sync.engine.document.library.event.MoveFolderToTrashEvent;
import com.liferay.sync.engine.document.library.event.PatchFileEntryEvent;
import com.liferay.sync.engine.document.library.event.UpdateFileEntryEvent;
import com.liferay.sync.engine.document.library.event.UpdateFolderEvent;
import com.liferay.sync.engine.document.library.handler.GetAllFolderSyncDLObjectsHandler;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.IODeltaUtil;
import com.liferay.sync.engine.util.PropsValues;
import com.liferay.sync.engine.util.ServerInfo;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class FileEventUtil {

	public static void addFile(
		Path filePath, long folderId, long repositoryId, long syncAccountId,
		String checksum, String name, String mimeType, SyncFile syncFile) {

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("changeLog", "");
		parameters.put("checksum", checksum);
		parameters.put("description", "");
		parameters.put("filePath", filePath);
		parameters.put("folderId", folderId);
		parameters.put("mimeType", mimeType);
		parameters.put("repositoryId", repositoryId);
		parameters.put("serviceContext.attributes.overwrite", true);
		parameters.put("sourceFileName", name);
		parameters.put("syncFile", syncFile);
		parameters.put("title", name);

		AddFileEntryEvent addFileEntryEvent = new AddFileEntryEvent(
			syncAccountId, parameters);

		addFileEntryEvent.run();
	}

	public static void addFolder(
		long parentFolderId, long repositoryId, long syncAccountId, String name,
		SyncFile syncFile) {

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("description", "");
		parameters.put("name", name);
		parameters.put("parentFolderId", parentFolderId);
		parameters.put("repositoryId", repositoryId);
		parameters.put("serviceContext.attributes.overwrite", true);
		parameters.put("syncFile", syncFile);

		AddFolderEvent addFolderEvent = new AddFolderEvent(
			syncAccountId, parameters);

		addFolderEvent.run();
	}

	public static void cancelCheckOut(long syncAccountId, SyncFile syncFile) {
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("fileEntryId", syncFile.getTypePK());
		parameters.put("syncFile", syncFile);

		CancelCheckOutEvent cancelCheckOutEvent = new CancelCheckOutEvent(
			syncAccountId, parameters);

		cancelCheckOutEvent.run();
	}

	public static void checkInFile(long syncAccountId, SyncFile syncFile) {
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("changeLog", syncFile.getChangeLog());
		parameters.put("fileEntryId", syncFile.getTypePK());
		parameters.put("majorVersion", false);
		parameters.put("syncFile", syncFile);

		CheckInFileEntryEvent checkInFileEntryEvent = new CheckInFileEntryEvent(
			syncAccountId, parameters);

		checkInFileEntryEvent.run();
	}

	public static void checkOutFile(long syncAccountId, SyncFile syncFile) {
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("fileEntryId", syncFile.getTypePK());
		parameters.put("syncFile", syncFile);

		CheckOutFileEntryEvent checkOutFileEntryEvent =
			new CheckOutFileEntryEvent(syncAccountId, parameters);

		checkOutFileEntryEvent.run();
	}

	public static void copyFile(
		long sourceFileEntryId, long folderId, long repositoryId,
		long syncAccountId, String name, SyncFile syncFile) {

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("folderId", folderId);
		parameters.put("repositoryId", repositoryId);
		parameters.put("sourceFileEntryId", sourceFileEntryId);
		parameters.put("sourceFileName", name);
		parameters.put("syncFile", syncFile);
		parameters.put("title", name);

		CopyFileEntryEvent copyFileEntryEvent = new CopyFileEntryEvent(
			syncAccountId, parameters);

		copyFileEntryEvent.run();
	}

	public static void deleteFile(long syncAccountId, SyncFile syncFile) {
		SyncFile parentSyncFile = SyncFileService.fetchSyncFile(
			syncFile.getRepositoryId(), syncAccountId,
			syncFile.getParentFolderId());

		if ((parentSyncFile == null) ||
			(parentSyncFile.getUiEvent() == SyncFile.UI_EVENT_DELETED_LOCAL)) {

			return;
		}

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("fileEntryId", syncFile.getTypePK());
		parameters.put("syncFile", syncFile);

		MoveFileEntryToTrashEvent moveFileEntryToTrashEvent =
			new MoveFileEntryToTrashEvent(syncAccountId, parameters);

		moveFileEntryToTrashEvent.run();
	}

	public static void deleteFolder(long syncAccountId, SyncFile syncFile) {
		SyncFile parentSyncFile = SyncFileService.fetchSyncFile(
			syncFile.getRepositoryId(), syncAccountId,
			syncFile.getParentFolderId());

		if ((parentSyncFile == null) ||
			(parentSyncFile.getUiEvent() == SyncFile.UI_EVENT_DELETED_LOCAL)) {

			return;
		}

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("folderId", syncFile.getTypePK());
		parameters.put("syncFile", syncFile);

		MoveFolderToTrashEvent moveFolderToTrashEvent =
			new MoveFolderToTrashEvent(syncAccountId, parameters);

		moveFolderToTrashEvent.run();
	}

	public static void downloadFile(long syncAccountId, SyncFile syncFile) {
		downloadFile(syncAccountId, syncFile, true);
	}

	public static void downloadFile(
		long syncAccountId, SyncFile syncFile, boolean batch) {

		if (isDownloadInProgress(syncFile)) {
			return;
		}

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("batch", batch);
		parameters.put("patch", false);
		parameters.put("syncFile", syncFile);

		DownloadFileEvent downloadFileEvent = new DownloadFileEvent(
			syncAccountId, parameters);

		downloadFileEvent.run();
	}

	public static void downloadPatch(
		long sourceVersionId, long syncAccountId, SyncFile syncFile,
		long targetVersionId) {

		if (isDownloadInProgress(syncFile)) {
			return;
		}

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("batch", true);
		parameters.put("patch", true);
		parameters.put("sourceVersionId", sourceVersionId);
		parameters.put("syncFile", syncFile);
		parameters.put("targetVersionId", targetVersionId);

		DownloadFileEvent downloadFileEvent = new DownloadFileEvent(
			syncAccountId, parameters);

		downloadFileEvent.run();
	}

	public static List<SyncFile> getAllFolders(
		long repositoryId, long syncAccountId) {

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("repositoryId", repositoryId);

		SyncSite syncSite = SyncSiteService.fetchSyncSite(
			repositoryId, syncAccountId);

		SyncFile syncFile = SyncFileService.fetchSyncFile(
			syncSite.getFilePathName());

		parameters.put("syncFile", syncFile);

		GetAllFolderSyncDLObjectsEvent getAllFolderSyncDLObjectsEvent =
			new GetAllFolderSyncDLObjectsEvent(syncAccountId, parameters);

		getAllFolderSyncDLObjectsEvent.run();

		GetAllFolderSyncDLObjectsHandler getAllFolderSyncDLObjectsHandler =
			(GetAllFolderSyncDLObjectsHandler)getAllFolderSyncDLObjectsEvent.
				getHandler();

		return getAllFolderSyncDLObjectsHandler.getSyncFiles();
	}

	public static void getUpdates(
		long repositoryId, long syncAccountId, SyncSite syncSite,
		boolean retrieveFromCache) {

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("repositoryId", repositoryId);

		if (ServerInfo.supportsRetrieveFromCache(syncAccountId)) {
			parameters.put("retrieveFromCache", retrieveFromCache);
		}

		SyncFile syncFile = SyncFileService.fetchSyncFile(
			syncSite.getFilePathName());

		parameters.put("syncFile", syncFile);

		parameters.put("syncSite", syncSite);

		GetSyncDLObjectUpdateEvent getSyncDLObjectUpdateEvent =
			new GetSyncDLObjectUpdateEvent(syncAccountId, parameters);

		getSyncDLObjectUpdateEvent.run();
	}

	public static void moveFile(
		long folderId, long syncAccountId, SyncFile syncFile) {

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("fileEntryId", syncFile.getTypePK());
		parameters.put("newFolderId", folderId);
		parameters.put(
			"serviceContext.scopeGroupId", syncFile.getRepositoryId());
		parameters.put("syncFile", syncFile);

		MoveFileEntryEvent moveFileEntryEvent = new MoveFileEntryEvent(
			syncAccountId, parameters);

		moveFileEntryEvent.run();
	}

	public static void moveFolder(
		long parentFolderId, long syncAccountId, SyncFile syncFile) {

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("folderId", syncFile.getTypePK());
		parameters.put("parentFolderId", parentFolderId);
		parameters.put(
			"serviceContext.scopeGroupId", syncFile.getRepositoryId());
		parameters.put("syncFile", syncFile);

		MoveFolderEvent moveFolderEvent = new MoveFolderEvent(
			syncAccountId, parameters);

		moveFolderEvent.run();
	}

	public static void resyncFolder(long syncAccountId, SyncFile syncFile) {
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("lastAccessTime", -1);
		parameters.put("parentFolderId", syncFile.getTypePK());
		parameters.put("repositoryId", syncFile.getRepositoryId());
		parameters.put("syncFile", syncFile);

		GetSyncDLObjectUpdateEvent getSyncDLObjectUpdateEvent =
			new GetSyncDLObjectUpdateEvent(syncAccountId, parameters);

		getSyncDLObjectUpdateEvent.run();
	}

	public static void retryFileTransfers(long syncAccountId)
		throws IOException {

		List<SyncFile> deletingSyncFiles = SyncFileService.findSyncFiles(
			syncAccountId, SyncFile.UI_EVENT_DELETED_LOCAL, "syncFileId", true);

		for (SyncFile deletingSyncFile : deletingSyncFiles) {
			if (!Files.notExists(
					Paths.get(deletingSyncFile.getFilePathName()))) {

				deletingSyncFile.setState(SyncFile.STATE_SYNCED);
				deletingSyncFile.setUiEvent(SyncFile.UI_EVENT_NONE);

				SyncFileService.update(deletingSyncFile);

				continue;
			}

			if (deletingSyncFile.isFolder()) {
				deleteFolder(syncAccountId, deletingSyncFile);
			}
			else {
				deleteFile(syncAccountId, deletingSyncFile);
			}
		}

		List<SyncFile> downloadingSyncFiles = SyncFileService.findSyncFiles(
			syncAccountId, SyncFile.UI_EVENT_DOWNLOADING, "size", true);

		for (SyncFile downloadingSyncFile : downloadingSyncFiles) {
			downloadFile(syncAccountId, downloadingSyncFile);
		}

		BatchEventManager.fireBatchDownloadEvents();

		List<SyncFile> uploadingSyncFiles = SyncFileService.findSyncFiles(
			syncAccountId, SyncFile.UI_EVENT_UPLOADING, "size", true);

		for (SyncFile uploadingSyncFile : uploadingSyncFiles) {
			Path filePath = Paths.get(uploadingSyncFile.getFilePathName());

			if (Files.notExists(filePath)) {
				if (uploadingSyncFile.getTypePK() == 0) {
					SyncFileService.deleteSyncFile(uploadingSyncFile, false);
				}

				continue;
			}

			if (uploadingSyncFile.isFolder()) {
				if (uploadingSyncFile.getTypePK() > 0) {
					updateFolder(
						filePath, uploadingSyncFile.getSyncAccountId(),
						uploadingSyncFile);
				}
				else {
					addFolder(
						uploadingSyncFile.getParentFolderId(),
						uploadingSyncFile.getRepositoryId(), syncAccountId,
						uploadingSyncFile.getName(), uploadingSyncFile);
				}

				continue;
			}

			String checksum = FileUtil.getChecksum(filePath);

			uploadingSyncFile.setChecksum(checksum);

			uploadingSyncFile.setSize(Files.size(filePath));

			SyncFileService.update(uploadingSyncFile);

			IODeltaUtil.checksums(uploadingSyncFile);

			if (uploadingSyncFile.getTypePK() > 0) {
				updateFile(
					filePath, syncAccountId, uploadingSyncFile, null,
					uploadingSyncFile.getName(), "", null, 0, checksum);
			}
			else {
				addFile(
					filePath, uploadingSyncFile.getParentFolderId(),
					uploadingSyncFile.getRepositoryId(), syncAccountId,
					checksum, uploadingSyncFile.getName(),
					uploadingSyncFile.getMimeType(), uploadingSyncFile);
			}
		}

		List<SyncFile> movingSyncFiles = SyncFileService.findSyncFiles(
			syncAccountId, SyncFile.UI_EVENT_MOVED_LOCAL, "syncFileId", true);

		for (SyncFile movingSyncFile : movingSyncFiles) {
			if (movingSyncFile.isFolder()) {
				moveFolder(
					movingSyncFile.getParentFolderId(), syncAccountId,
					movingSyncFile);
			}
			else {
				moveFile(
					movingSyncFile.getParentFolderId(), syncAccountId,
					movingSyncFile);
			}
		}

		BatchEventManager.fireBatchEvents();

		List<SyncFile> resyncingSyncFiles = SyncFileService.findSyncFiles(
			syncAccountId, SyncFile.UI_EVENT_RESYNCING, "syncFileId", true);

		for (SyncFile resyncingSyncFile : resyncingSyncFiles) {
			resyncFolder(syncAccountId, resyncingSyncFile);
		}
	}

	public static void updateFile(
			Path filePath, long syncAccountId, SyncFile syncFile,
			Path deltaFilePath, String name, String sourceChecksum,
			String sourceFileName, long sourceVersionId, String targetChecksum)
		throws IOException {

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("changeLog", syncFile.getChangeLog());
		parameters.put("checksum", targetChecksum);
		parameters.put("description", syncFile.getDescription());
		parameters.put("fileEntryId", syncFile.getTypePK());
		parameters.put("majorVersion", false);
		parameters.put("mimeType", syncFile.getMimeType());
		parameters.put("sourceFileName", name);
		parameters.put("syncFile", syncFile);
		parameters.put("title", name);

		if (FileUtil.checksumsEqual(sourceChecksum, targetChecksum)) {
			parameters.put("-file", null);
		}
		else {
			if ((deltaFilePath != null) && (sourceVersionId != 0) &&
				((Files.size(filePath) / Files.size(deltaFilePath)) >=
					PropsValues.SYNC_FILE_PATCHING_THRESHOLD_SIZE_RATIO)) {

				parameters.put("deltaFilePath", deltaFilePath);
				parameters.put("sourceFileName", sourceFileName);
				parameters.put("sourceVersionId", sourceVersionId);

				PatchFileEntryEvent patchFileEntryEvent =
					new PatchFileEntryEvent(syncAccountId, parameters);

				patchFileEntryEvent.run();

				return;
			}

			parameters.put("filePath", filePath);
		}

		UpdateFileEntryEvent updateFileEntryEvent = new UpdateFileEntryEvent(
			syncAccountId, parameters);

		updateFileEntryEvent.run();
	}

	public static void updateFolder(
		Path filePath, long syncAccountId, SyncFile syncFile) {

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("description", syncFile.getDescription());
		parameters.put("folderId", syncFile.getTypePK());
		parameters.put("name", String.valueOf(filePath.getFileName()));
		parameters.put("syncFile", syncFile);

		UpdateFolderEvent updateFolderEvent = new UpdateFolderEvent(
			syncAccountId, parameters);

		updateFolderEvent.run();
	}

	protected static boolean isDownloadInProgress(SyncFile syncFile) {
		Set<Event> events = FileEventManager.getEvents(
			syncFile.getSyncFileId());

		for (Event event : events) {
			if (event instanceof DownloadFileEvent) {
				SyncFile downloadingSyncFile =
					(SyncFile)event.getParameterValue("syncFile");

				if (downloadingSyncFile.getVersionId() !=
						syncFile.getVersionId()) {

					continue;
				}

				if (_logger.isDebugEnabled()) {
					_logger.debug(
						"Download already in progress {}",
						syncFile.getFilePathName());
				}

				return true;
			}
		}

		return false;
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		FileEventUtil.class);

}