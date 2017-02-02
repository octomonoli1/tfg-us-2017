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

package com.liferay.sync.engine.service;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.document.library.util.FileEventManager;
import com.liferay.sync.engine.file.system.Watcher;
import com.liferay.sync.engine.file.system.util.WatcherManager;
import com.liferay.sync.engine.model.ModelListener;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.model.SyncSiteModelListener;
import com.liferay.sync.engine.service.persistence.SyncSitePersistence;
import com.liferay.sync.engine.util.FileKeyUtil;
import com.liferay.sync.engine.util.FileUtil;

import java.io.IOException;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.sql.SQLException;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncSiteService {

	public static SyncSite activateSyncSite(
			long syncSiteId, List<SyncFile> ignoredSyncFiles, boolean reset)
		throws Exception {

		// Sync site

		SyncSite syncSite = fetchSyncSite(syncSiteId);

		if (syncSite.isActive()) {
			return syncSite;
		}

		syncSite.setActive(true);
		syncSite.setState(SyncSite.STATE_SYNCED);
		syncSite.setUiEvent(SyncSite.UI_EVENT_NONE);

		if (reset) {
			syncSite.setRemoteSyncTime(-1);

			deleteSyncFiles(syncSite);
		}

		update(syncSite);

		// Sync file

		String filePathName = syncSite.getFilePathName();

		if (!Files.exists(Paths.get(filePathName))) {
			Files.createDirectories(Paths.get(filePathName));

			SyncFile syncFile = SyncFileService.fetchSyncFile(filePathName);

			FileKeyUtil.writeFileKey(
				Paths.get(filePathName),
				String.valueOf(syncFile.getSyncFileId()), true);
		}

		// Sync files

		for (SyncFile syncFile : ignoredSyncFiles) {
			syncFile.setModifiedTime(0);
			syncFile.setState(SyncFile.STATE_UNSYNCED);
			syncFile.setSyncAccountId(syncSite.getSyncAccountId());

			SyncFileService.update(syncFile);
		}

		return syncSite;
	}

	public static SyncSite deactivateSyncSite(long syncSiteId) {
		SyncSite syncSite = fetchSyncSite(syncSiteId);

		if (!syncSite.isActive()) {
			return syncSite;
		}

		return deactivateSyncSite(syncSite);
	}

	public static void deleteSyncSite(long syncSiteId) {
		try {

			// Sync site

			SyncSite syncSite = fetchSyncSite(syncSiteId);

			List<SyncFile> syncFiles = SyncFileService.findSyncFiles(
				syncSite.getGroupId(), SyncFile.STATE_IN_PROGRESS,
				syncSite.getSyncAccountId());

			syncFiles.add(
				SyncFileService.fetchSyncFile(syncSite.getFilePathName()));

			for (SyncFile syncFile : syncFiles) {
				Set<Event> events = FileEventManager.getEvents(
					syncFile.getSyncFileId());

				for (Event event : events) {
					event.cancel();
				}
			}

			_syncSitePersistence.deleteById(syncSiteId);

			// Sync file

			SyncFile syncFile = SyncFileService.fetchSyncFile(
				syncSite.getFilePathName());

			SyncFileService.deleteSyncFile(syncFile);

			// Sync files

			try {
				deleteSyncFiles(syncSite);
			}
			catch (IOException ioe) {
				_logger.error(ioe.getMessage(), ioe);
			}
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}
	}

	public static SyncSite fetchSyncSite(long syncSiteId) {
		try {
			return _syncSitePersistence.queryForId(syncSiteId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static SyncSite fetchSyncSite(long groupId, long syncAccountId) {
		try {
			return _syncSitePersistence.fetchByG_S(groupId, syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static SyncSite fetchSyncSite(
		String filePathName, long syncAccountId) {

		try {
			return _syncSitePersistence.fetchByF_S(filePathName, syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static List<SyncSite> findSyncSites(long syncAccountId) {
		try {
			return _syncSitePersistence.findBySyncAccountId(syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return Collections.emptyList();
		}
	}

	public static Set<Long> getActiveSyncSiteIds(long syncAccountId) {
		try {
			Set<Long> activeSyncSiteIds = _activeSyncSiteIds.get(syncAccountId);

			if (activeSyncSiteIds != null) {
				return activeSyncSiteIds;
			}

			activeSyncSiteIds = new HashSet<>(
				_syncSitePersistence.findByA_S(true, syncAccountId));

			_activeSyncSiteIds.put(syncAccountId, activeSyncSiteIds);

			return activeSyncSiteIds;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return Collections.emptySet();
		}
	}

	public static SyncSitePersistence getSyncSitePersistence() {
		if (_syncSitePersistence != null) {
			return _syncSitePersistence;
		}

		try {
			_syncSitePersistence = new SyncSitePersistence();

			registerModelListener(new SyncSiteModelListener());

			return _syncSitePersistence;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static void registerModelListener(
		ModelListener<SyncSite> modelListener) {

		_syncSitePersistence.registerModelListener(modelListener);
	}

	public static SyncSite setFilePathName(
		long syncSiteId, String targetFilePathName) {

		// Sync site

		SyncSite syncSite = fetchSyncSite(syncSiteId);

		String sourceFilePathName = syncSite.getFilePathName();

		syncSite.setFilePathName(targetFilePathName);

		update(syncSite);

		// Sync file

		SyncFile syncFile = SyncFileService.fetchSyncFile(sourceFilePathName);

		syncFile.setName(syncSite.getName());
		syncFile.setFilePathName(targetFilePathName);

		SyncFileService.update(syncFile);

		// Sync files

		SyncFileService.renameSyncFiles(sourceFilePathName, targetFilePathName);

		return syncSite;
	}

	public static void unregisterModelListener(
		ModelListener<SyncSite> modelListener) {

		_syncSitePersistence.unregisterModelListener(modelListener);
	}

	public static SyncSite update(SyncSite syncSite) {
		try {
			_syncSitePersistence.createOrUpdate(syncSite);

			return syncSite;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	protected static SyncSite deactivateSyncSite(SyncSite syncSite) {

		// Sync site

		syncSite.setActive(false);

		syncSite = update(syncSite);

		List<SyncFile> syncFiles = SyncFileService.findSyncFiles(
			syncSite.getGroupId(), SyncFile.STATE_IN_PROGRESS,
			syncSite.getSyncAccountId());

		syncFiles.add(
			SyncFileService.fetchSyncFile(syncSite.getFilePathName()));

		for (SyncFile syncFile : syncFiles) {
			Set<Event> events = FileEventManager.getEvents(
				syncFile.getSyncFileId());

			for (Event event : events) {
				event.cancel();
			}
		}

		// Sync files

		try {
			deleteSyncFiles(syncSite);
		}
		catch (IOException ioe) {
			_logger.error(ioe.getMessage(), ioe);
		}

		if (_logger.isDebugEnabled()) {
			_logger.debug("Sync site {} deactivated", syncSite.getName());
		}

		return syncSite;
	}

	protected static void deleteSyncFiles(SyncSite syncSite)
		throws IOException {

		SyncFile syncFile = SyncFileService.fetchSyncFile(
			syncSite.getFilePathName());

		if (syncFile != null) {
			SyncFileService.deleteSyncFiles(syncFile, true);
		}

		Path filePath = Paths.get(syncSite.getFilePathName());

		if (!Files.exists(filePath)) {
			return;
		}

		final Watcher watcher = WatcherManager.getWatcher(
			syncSite.getSyncAccountId());

		Files.walkFileTree(
			filePath,
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult postVisitDirectory(
						Path filePath, IOException ioe)
					throws IOException {

					if (ioe != null) {
						return super.postVisitDirectory(filePath, ioe);
					}

					watcher.addDeletedFilePathName(filePath.toString());

					FileUtil.deleteFile(filePath);

					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(
						Path filePath, BasicFileAttributes basicFileAttributes)
					throws IOException {

					watcher.addDeletedFilePathName(filePath.toString());

					FileUtil.deleteFile(filePath);

					return FileVisitResult.CONTINUE;
				}

			});
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		SyncSiteService.class);

	private static final Map<Long, Set<Long>> _activeSyncSiteIds =
		new HashMap<>();
	private static SyncSitePersistence _syncSitePersistence =
		getSyncSitePersistence();

}