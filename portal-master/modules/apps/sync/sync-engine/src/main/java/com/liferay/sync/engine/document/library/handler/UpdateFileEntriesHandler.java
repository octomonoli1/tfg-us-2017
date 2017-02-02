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

import com.fasterxml.jackson.databind.JsonNode;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.JSONUtil;

import java.nio.file.Path;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class UpdateFileEntriesHandler extends BaseJSONHandler {

	public UpdateFileEntriesHandler(Event event) {
		super(event);
	}

	@Override
	public void processResponse(String response) throws Exception {
		Map<String, Handler> handlers = (Map<String, Handler>)getParameterValue(
			"handlers");

		JsonNode rootJsonNode = JSONUtil.readTree(response);

		Iterator<Map.Entry<String, JsonNode>> fields = rootJsonNode.fields();

		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> field = fields.next();

			Handler handler = handlers.remove(field.getKey());

			try {
				JsonNode fieldValue = field.getValue();

				String exception = handler.getException(fieldValue.textValue());

				if (handler.handlePortalException(exception)) {
					continue;
				}

				if (_logger.isTraceEnabled()) {
					Class<?> clazz = handler.getClass();

					_logger.trace(
						"Handling response {} {}", clazz.getSimpleName(),
						fieldValue.toString());
				}

				handler.processResponse(fieldValue.toString());
			}
			catch (Exception e) {
				if (!isEventCancelled()) {
					_logger.error(e.getMessage(), e);
				}
			}
			finally {
				handler.removeEvent();
			}
		}

		Path filePath = (Path)getParameterValue("zipFilePath");

		FileUtil.deleteFile(filePath);
	}

	@Override
	public void removeEvent() {
		Map<String, Handler> handlers = (Map<String, Handler>)getParameterValue(
			"handlers");

		for (Handler handler : handlers.values()) {
			handler.removeEvent();
		}

		super.removeEvent();
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		UpdateFileEntriesHandler.class);

}