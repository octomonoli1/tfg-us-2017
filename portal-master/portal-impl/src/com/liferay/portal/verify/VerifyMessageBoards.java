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

package com.liferay.portal.verify;

import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.message.boards.kernel.model.MBCategory;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.message.boards.kernel.service.MBCategoryLocalServiceUtil;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceUtil;
import com.liferay.message.boards.kernel.service.MBThreadLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Zsolt Berentey
 */
public class VerifyMessageBoards extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		verifyStatisticsForCategories();
		verifyStatisticsForThreads();
		verifyAssetsForMessages();
		verifyAssetsForThreads();
	}

	protected void verifyAssetsForMessages() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<MBMessage> messages =
				MBMessageLocalServiceUtil.getNoAssetMessages();

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Processing " + messages.size() +
						" messages with no asset");
			}

			for (MBMessage message : messages) {
				try {
					MBMessageLocalServiceUtil.updateAsset(
						message.getUserId(), message, null, null, null);

					if (message.getStatus() == WorkflowConstants.STATUS_DRAFT) {
						boolean visible = false;

						if (message.isApproved() &&
							((message.getClassNameId() == 0) ||
							 (message.getParentMessageId() != 0))) {

							visible = true;
						}

						AssetEntryLocalServiceUtil.updateEntry(
							message.getWorkflowClassName(),
							message.getMessageId(), null, null, true, visible);
					}
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to update asset for message " +
								message.getMessageId() + ": " + e.getMessage());
					}
				}
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Assets verified for messages");
			}
		}
	}

	protected void verifyAssetsForThreads() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<MBThread> threads =
				MBThreadLocalServiceUtil.getNoAssetThreads();

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Processing " + threads.size() + " threads with no asset");
			}

			for (MBThread thread : threads) {
				try {
					AssetEntryLocalServiceUtil.updateEntry(
						thread.getRootMessageUserId(), thread.getGroupId(),
						thread.getStatusDate(), thread.getLastPostDate(),
						MBThread.class.getName(), thread.getThreadId(), null, 0,
						new long[0], new String[0], true, false, null, null,
						null, null, null,
						String.valueOf(thread.getRootMessageId()), null, null,
						null, null, 0, 0, null);
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to update asset for thread " +
								thread.getThreadId() + ": " + e.getMessage());
					}
				}
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Assets verified for threads");
			}
		}
	}

	protected void verifyStatisticsForCategories() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Processing categories for statistics accuracy");
			}

			ActionableDynamicQuery actionableDynamicQuery =
				MBCategoryLocalServiceUtil.getActionableDynamicQuery();

			actionableDynamicQuery.setParallel(true);
			actionableDynamicQuery.setPerformActionMethod(
				new ActionableDynamicQuery.PerformActionMethod<MBCategory>() {

					@Override
					public void performAction(MBCategory category) {
						int threadCount =
							MBThreadLocalServiceUtil.getCategoryThreadsCount(
								category.getGroupId(), category.getCategoryId(),
								WorkflowConstants.STATUS_APPROVED);
						int messageCount =
							MBMessageLocalServiceUtil.getCategoryMessagesCount(
								category.getGroupId(), category.getCategoryId(),
								WorkflowConstants.STATUS_APPROVED);

						if ((category.getThreadCount() != threadCount) ||
							(category.getMessageCount() != messageCount)) {

							category.setThreadCount(threadCount);
							category.setMessageCount(messageCount);

							MBCategoryLocalServiceUtil.updateMBCategory(
								category);
						}
					}

				});

			actionableDynamicQuery.performActions();

			if (_log.isDebugEnabled()) {
				_log.debug("Statistics verified for categories");
			}
		}
	}

	protected void verifyStatisticsForThreads() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Processing threads for statistics accuracy");
			}

			ActionableDynamicQuery actionableDynamicQuery =
				MBThreadLocalServiceUtil.getActionableDynamicQuery();

			actionableDynamicQuery.setParallel(true);
			actionableDynamicQuery.setPerformActionMethod(
				new ActionableDynamicQuery.PerformActionMethod<MBThread>() {

					@Override
					public void performAction(MBThread thread) {
						int messageCount =
							MBMessageLocalServiceUtil.getThreadMessagesCount(
								thread.getThreadId(),
								WorkflowConstants.STATUS_APPROVED);

						if (thread.getMessageCount() != messageCount) {
							thread.setMessageCount(messageCount);

							MBThreadLocalServiceUtil.updateMBThread(thread);
						}
					}

				});

			actionableDynamicQuery.performActions();

			if (_log.isDebugEnabled()) {
				_log.debug("Statistics verified for threads");
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		VerifyMessageBoards.class);

}