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

package com.liferay.sync.engine.document.library.handler;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.document.library.model.SyncContext;
import com.liferay.sync.engine.document.library.util.FileEventManager;
import com.liferay.sync.engine.document.library.util.FileEventUtil;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.util.ConnectionRetryUtil;
import com.liferay.sync.engine.util.ServerInfo;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class RetryServerConnectionHandler extends GetSyncContextHandler {

	public RetryServerConnectionHandler(Event event) {
		super(event);
	}

	@Override
	public void processResponse(String response) throws Exception {
		SyncContext syncContext = doProcessResponse(response);

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			getSyncAccountId());

		if (ServerInfo.isCompatible(syncContext)) {
			if (_logger.isDebugEnabled()) {
				_logger.debug("Connected to {}", syncAccount.getUrl());
			}

			syncAccount.setState(SyncAccount.STATE_CONNECTED);
			syncAccount.setUiEvent(SyncAccount.UI_EVENT_NONE);

			SyncAccountService.update(syncAccount);

			List<SyncFile> syncFiles = SyncFileService.findSyncFiles(
				SyncFile.STATE_IN_PROGRESS, syncAccount.getSyncAccountId());

			for (SyncFile syncFile : syncFiles) {
				Set<Event> events = FileEventManager.getEvents(
					syncFile.getSyncFileId());

				for (Event event : events) {
					event.cancel();
				}
			}

			FileEventUtil.retryFileTransfers(getSyncAccountId());

			ConnectionRetryUtil.resetRetry(getSyncAccountId());
		}
		else {
			syncAccount.setState(SyncAccount.STATE_DISCONNECTED);
			syncAccount.setUiEvent(SyncAccount.UI_EVENT_SYNC_WEB_OUT_OF_DATE);

			SyncAccountService.update(syncAccount);
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		RetryServerConnectionHandler.class);

}