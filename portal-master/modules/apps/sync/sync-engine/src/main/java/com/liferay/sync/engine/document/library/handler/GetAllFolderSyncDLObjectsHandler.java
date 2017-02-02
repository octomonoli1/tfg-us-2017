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

import com.fasterxml.jackson.core.type.TypeReference;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.util.JSONUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shinn Lok
 */
public class GetAllFolderSyncDLObjectsHandler extends BaseJSONHandler {

	public GetAllFolderSyncDLObjectsHandler(Event event) {
		super(event);
	}

	public List<SyncFile> getSyncFiles() {
		return _syncFiles;
	}

	@Override
	public void processResponse(String response) throws Exception {
		_syncFiles = JSONUtil.readValue(
			response, new TypeReference<List<SyncFile>>() {});
	}

	private List<SyncFile> _syncFiles = new ArrayList<>();

}