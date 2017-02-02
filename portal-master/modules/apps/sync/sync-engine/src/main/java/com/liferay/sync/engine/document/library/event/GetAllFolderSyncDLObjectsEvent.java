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
import com.liferay.sync.engine.document.library.handler.GetAllFolderSyncDLObjectsHandler;
import com.liferay.sync.engine.document.library.handler.Handler;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class GetAllFolderSyncDLObjectsEvent extends BaseEvent {

	public GetAllFolderSyncDLObjectsEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(
			syncAccountId, EventURLPaths.GET_ALL_FOLDER_SYNC_DL_OBJECTS,
			parameters);

		_handler = new GetAllFolderSyncDLObjectsHandler(this);
	}

	@Override
	public Handler<Void> getHandler() {
		return _handler;
	}

	private final Handler<Void> _handler;

}