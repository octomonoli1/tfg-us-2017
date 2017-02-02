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

package com.liferay.exportimport.background.task;

import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportLocalServiceUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.File;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Daniel Kocsis
 * @author Akos Thurzo
 */
public class PortletImportBackgroundTaskExecutor
	extends BaseExportImportBackgroundTaskExecutor {

	public PortletImportBackgroundTaskExecutor() {
		setBackgroundTaskStatusMessageTranslator(
			new PortletExportImportBackgroundTaskStatusMessageTranslator());

		// Isolation level guarantees this will be serial in a group

		setIsolationLevel(BackgroundTaskConstants.ISOLATION_LEVEL_GROUP);
	}

	@Override
	public BackgroundTaskExecutor clone() {
		PortletImportBackgroundTaskExecutor
			portletImportBackgroundTaskExecutor =
				new PortletImportBackgroundTaskExecutor();

		portletImportBackgroundTaskExecutor.
			setBackgroundTaskStatusMessageTranslator(
				getBackgroundTaskStatusMessageTranslator());
		portletImportBackgroundTaskExecutor.setIsolationLevel(
			getIsolationLevel());

		return portletImportBackgroundTaskExecutor;
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		ExportImportConfiguration exportImportConfiguration =
			getExportImportConfiguration(backgroundTask);

		List<FileEntry> attachmentsFileEntries =
			backgroundTask.getAttachmentsFileEntries();

		File file = null;

		for (FileEntry attachmentsFileEntry : attachmentsFileEntries) {
			try {
				file = FileUtil.createTempFile("lar");

				FileUtil.write(file, attachmentsFileEntry.getContentStream());

				TransactionInvokerUtil.invoke(
					transactionConfig,
					new PortletImportCallable(exportImportConfiguration, file));
			}
			catch (Throwable t) {
				if (_log.isDebugEnabled()) {
					_log.debug(t, t);
				}
				else if (_log.isWarnEnabled()) {
					_log.warn("Unable to import portlet: " + t.getMessage());
				}

				throw new SystemException(t);
			}
			finally {
				FileUtil.delete(file);
			}
		}

		return BackgroundTaskResult.SUCCESS;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortletImportBackgroundTaskExecutor.class);

	private static class PortletImportCallable implements Callable<Void> {

		public PortletImportCallable(
			ExportImportConfiguration exportImportConfiguration, File file) {

			_exportImportConfiguration = exportImportConfiguration;
			_file = file;
		}

		@Override
		public Void call() throws PortalException {
			ExportImportLocalServiceUtil.importPortletDataDeletions(
				_exportImportConfiguration, _file);

			ExportImportLocalServiceUtil.importPortletInfo(
				_exportImportConfiguration, _file);

			return null;
		}

		private final ExportImportConfiguration _exportImportConfiguration;
		private final File _file;

	}

}