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

package com.liferay.sync.engine.document.library.event;

import com.liferay.sync.engine.document.library.event.constants.EventURLPaths;
import com.liferay.sync.engine.document.library.handler.DownloadFilesHandler;
import com.liferay.sync.engine.document.library.handler.Handler;
import com.liferay.sync.engine.document.library.util.ServerUtil;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class DownloadFilesEvent extends BaseEvent {

	public DownloadFilesEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(syncAccountId, EventURLPaths.DOWNLOAD_FILES, parameters);

		_handler = new DownloadFilesHandler(this);
	}

	@Override
	public Handler<Void> getHandler() {
		return _handler;
	}

	@Override
	protected void processRequest() throws Exception {
		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			getSyncAccountId());

		String url = ServerUtil.getDownloadURL(
			syncAccount.getSyncAccountId(), syncAccount.getUrl());

		executeAsynchronousPost(url + getURLPath(), getParameters(), _handler);
	}

	private final Handler<Void> _handler;

}