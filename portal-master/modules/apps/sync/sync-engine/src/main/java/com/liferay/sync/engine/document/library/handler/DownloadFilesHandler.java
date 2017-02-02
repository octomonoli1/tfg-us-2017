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
import com.liferay.sync.engine.document.library.util.FileEventUtil;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.session.Session;
import com.liferay.sync.engine.session.SessionManager;
import com.liferay.sync.engine.session.rate.limiter.RateLimitedInputStream;
import com.liferay.sync.engine.util.JSONUtil;
import com.liferay.sync.engine.util.StreamUtil;

import java.io.InputStream;

import java.nio.file.Paths;

import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.input.CloseShieldInputStream;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class DownloadFilesHandler extends BaseHandler {

	public DownloadFilesHandler(Event event) {
		super(event);
	}

	@Override
	public void removeEvent() {
		Map<String, DownloadFileHandler> handlers =
			(Map<String, DownloadFileHandler>)getParameterValue("handlers");

		for (DownloadFileHandler downloadFileHandler : handlers.values()) {
			if (!downloadFileHandler.isEventCancelled()) {
				downloadFileHandler.removeEvent();
			}
		}

		super.removeEvent();
	}

	@Override
	protected void doHandleResponse(HttpResponse httpResponse)
		throws Exception {

		long syncAccountId = getSyncAccountId();

		final Session session = SessionManager.getSession(syncAccountId);

		Header header = httpResponse.getFirstHeader("Sync-JWT");

		if (header != null) {
			session.addHeader("Sync-JWT", header.getValue());
		}

		Map<String, DownloadFileHandler> handlers =
			(Map<String, DownloadFileHandler>)getParameterValue("handlers");

		InputStream inputStream = null;

		try {
			HttpEntity httpEntity = httpResponse.getEntity();

			inputStream = new CountingInputStream(httpEntity.getContent()) {

				@Override
				protected synchronized void afterRead(int n) {
					session.incrementDownloadedBytes(n);

					super.afterRead(n);
				}

			};

			inputStream = new RateLimitedInputStream(
				inputStream, syncAccountId);

			ZipInputStream zipInputStream = new ZipInputStream(inputStream);

			ZipEntry zipEntry = null;

			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				String zipEntryName = zipEntry.getName();

				if (zipEntryName.equals("errors.json")) {
					JsonNode rootJsonNode = JSONUtil.readTree(
						new CloseShieldInputStream(zipInputStream));

					Iterator<Map.Entry<String, JsonNode>> fields =
						rootJsonNode.fields();

					while (fields.hasNext()) {
						Map.Entry<String, JsonNode> field = fields.next();

						Handler<Void> handler = handlers.remove(field.getKey());

						JsonNode valueJsonNode = field.getValue();

						JsonNode exceptionJsonNode = valueJsonNode.get(
							"exception");

						handler.handlePortalException(
							exceptionJsonNode.textValue());
					}

					break;
				}

				DownloadFileHandler downloadFileHandler = handlers.remove(
					zipEntryName);

				SyncFile syncFile =
					(SyncFile)downloadFileHandler.getParameterValue("syncFile");

				if (downloadFileHandler.isUnsynced(syncFile)) {
					continue;
				}

				if (_logger.isTraceEnabled()) {
					_logger.trace(
						"Handling response {} file path {}",
						DownloadFileHandler.class.getSimpleName(),
						syncFile.getFilePathName());
				}

				try {
					downloadFileHandler.copyFile(
						syncFile, Paths.get(syncFile.getFilePathName()),
						new CloseShieldInputStream(zipInputStream), false);
				}
				catch (Exception e) {
					if (!isEventCancelled()) {
						_logger.error(e.getMessage(), e);

						downloadFileHandler.removeEvent();

						FileEventUtil.downloadFile(
							getSyncAccountId(), syncFile, false);
					}
				}
				finally {
					downloadFileHandler.removeEvent();
				}
			}
		}
		catch (Exception e) {
			if (!isEventCancelled()) {
				_logger.error(e.getMessage(), e);

				retryEvent();
			}
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		DownloadFilesHandler.class);

}