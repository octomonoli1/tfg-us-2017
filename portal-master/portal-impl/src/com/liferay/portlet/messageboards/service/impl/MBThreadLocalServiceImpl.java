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

package com.liferay.portlet.messageboards.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.message.boards.kernel.constants.MBConstants;
import com.liferay.message.boards.kernel.exception.NoSuchCategoryException;
import com.liferay.message.boards.kernel.exception.SplitThreadException;
import com.liferay.message.boards.kernel.model.MBCategory;
import com.liferay.message.boards.kernel.model.MBCategoryConstants;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.model.MBMessageDisplay;
import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.message.boards.kernel.model.MBThreadConstants;
import com.liferay.message.boards.kernel.model.MBTreeWalker;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.increment.BufferedIncrement;
import com.liferay.portal.kernel.increment.NumberIncrement;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.social.SocialActivityManagerUtil;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.messageboards.service.base.MBThreadLocalServiceBaseImpl;
import com.liferay.portlet.messageboards.util.MBUtil;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.trash.kernel.exception.RestoreEntryException;
import com.liferay.trash.kernel.exception.TrashEntryException;
import com.liferay.trash.kernel.model.TrashEntry;
import com.liferay.trash.kernel.model.TrashVersion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class MBThreadLocalServiceImpl extends MBThreadLocalServiceBaseImpl {

	@Override
	public MBThread addThread(
			long categoryId, MBMessage message, ServiceContext serviceContext)
		throws PortalException {

		// Thread

		long threadId = message.getThreadId();

		if (!message.isRoot() || (threadId <= 0)) {
			threadId = counterLocalService.increment();
		}

		MBThread thread = mbThreadPersistence.create(threadId);

		thread.setUuid(serviceContext.getUuid());
		thread.setGroupId(message.getGroupId());
		thread.setCompanyId(message.getCompanyId());
		thread.setUserId(message.getUserId());
		thread.setUserName(message.getUserName());
		thread.setCategoryId(categoryId);
		thread.setRootMessageId(message.getMessageId());
		thread.setRootMessageUserId(message.getUserId());

		if (message.isAnonymous()) {
			thread.setLastPostByUserId(0);
		}
		else {
			thread.setLastPostByUserId(message.getUserId());
		}

		thread.setLastPostDate(message.getModifiedDate());

		if (message.getPriority() != MBThreadConstants.PRIORITY_NOT_GIVEN) {
			thread.setPriority(message.getPriority());
		}

		thread.setStatus(message.getStatus());
		thread.setStatusByUserId(message.getStatusByUserId());
		thread.setStatusByUserName(message.getStatusByUserName());
		thread.setStatusDate(message.getStatusDate());

		mbThreadPersistence.update(thread);

		// Asset

		if (categoryId >= 0) {
			assetEntryLocalService.updateEntry(
				message.getUserId(), message.getGroupId(),
				thread.getStatusDate(), thread.getLastPostDate(),
				MBThread.class.getName(), thread.getThreadId(),
				thread.getUuid(), 0, new long[0], new String[0], true, false,
				null, null, thread.getStatusDate(), null, null,
				String.valueOf(thread.getRootMessageId()), null, null, null,
				null, 0, 0, serviceContext.getAssetPriority());
		}

		return thread;
	}

	@Override
	public void deleteThread(long threadId) throws PortalException {
		MBThread thread = mbThreadPersistence.findByPrimaryKey(threadId);

		mbThreadLocalService.deleteThread(thread);
	}

	@Override
	@SystemEvent(
		action = SystemEventConstants.ACTION_SKIP,
		type = SystemEventConstants.TYPE_DELETE
	)
	public void deleteThread(MBThread thread) throws PortalException {
		MBMessage rootMessage = mbMessagePersistence.findByPrimaryKey(
			thread.getRootMessageId());

		// Indexer

		Indexer<MBMessage> messageIndexer =
			IndexerRegistryUtil.nullSafeGetIndexer(MBMessage.class);

		// Attachments

		long folderId = thread.getAttachmentsFolderId();

		if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			PortletFileRepositoryUtil.deletePortletFolder(folderId);
		}

		// Subscriptions

		subscriptionLocalService.deleteSubscriptions(
			thread.getCompanyId(), MBThread.class.getName(),
			thread.getThreadId());

		// Thread flags

		mbThreadFlagLocalService.deleteThreadFlagsByThreadId(
			thread.getThreadId());

		// Messages

		List<MBMessage> messages = mbMessagePersistence.findByThreadId(
			thread.getThreadId());

		for (MBMessage message : messages) {

			// Ratings

			ratingsStatsLocalService.deleteStats(
				message.getWorkflowClassName(), message.getMessageId());

			// Asset

			assetEntryLocalService.deleteEntry(
				message.getWorkflowClassName(), message.getMessageId());

			// Resources

			if (!message.isDiscussion()) {
				resourceLocalService.deleteResource(
					message.getCompanyId(), message.getWorkflowClassName(),
					ResourceConstants.SCOPE_INDIVIDUAL, message.getMessageId());
			}

			// Message

			mbMessagePersistence.remove(message);

			// Indexer

			messageIndexer.delete(message);

			// Statistics

			if (!message.isDiscussion()) {
				mbStatsUserLocalService.updateStatsUser(
					message.getGroupId(), message.getUserId());
			}

			// Workflow

			workflowInstanceLinkLocalService.deleteWorkflowInstanceLink(
				message.getCompanyId(), message.getGroupId(),
				message.getWorkflowClassName(), message.getMessageId());
		}

		// Category

		if ((rootMessage.getCategoryId() !=
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) &&
			(rootMessage.getCategoryId() !=
				MBCategoryConstants.DISCUSSION_CATEGORY_ID)) {

			try {
				MBCategory category = mbCategoryPersistence.findByPrimaryKey(
					thread.getCategoryId());

				MBUtil.updateCategoryStatistics(category.getCategoryId());
			}
			catch (NoSuchCategoryException nsce) {
				if (!thread.isInTrash()) {
					throw nsce;
				}
			}
		}

		// Asset

		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
			MBThread.class.getName(), thread.getThreadId());

		if (assetEntry != null) {
			assetEntry.setTitle(rootMessage.getSubject());

			assetEntryLocalService.updateAssetEntry(assetEntry);
		}

		assetEntryLocalService.deleteEntry(
			MBThread.class.getName(), thread.getThreadId());

		// Trash

		if (thread.isInTrashExplicitly()) {
			trashEntryLocalService.deleteEntry(
				MBThread.class.getName(), thread.getThreadId());
		}
		else {
			trashVersionLocalService.deleteTrashVersion(
				MBThread.class.getName(), thread.getThreadId());
		}

		// Indexer

		Indexer<MBThread> threadIndexer =
			IndexerRegistryUtil.nullSafeGetIndexer(MBThread.class);

		threadIndexer.delete(thread);

		// Thread

		mbThreadPersistence.remove(thread);
	}

	@Override
	public void deleteThreads(long groupId, long categoryId)
		throws PortalException {

		deleteThreads(groupId, categoryId, true);
	}

	@Override
	public void deleteThreads(
			long groupId, long categoryId, boolean includeTrashedEntries)
		throws PortalException {

		List<MBThread> threads = mbThreadPersistence.findByG_C(
			groupId, categoryId);

		for (MBThread thread : threads) {
			if (includeTrashedEntries || !thread.isInTrashExplicitly()) {
				mbThreadLocalService.deleteThread(thread);
			}
		}

		if (mbThreadPersistence.countByGroupId(groupId) == 0) {
			PortletFileRepositoryUtil.deletePortletRepository(
				groupId, MBConstants.SERVICE_NAME);
		}
	}

	@Override
	public MBThread fetchThread(long threadId) {
		return mbThreadPersistence.fetchByPrimaryKey(threadId);
	}

	@Override
	public int getCategoryThreadsCount(
		long groupId, long categoryId, int status) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbThreadPersistence.countByG_C(groupId, categoryId);
		}
		else {
			return mbThreadPersistence.countByG_C_S(
				groupId, categoryId, status);
		}
	}

	@Override
	public List<MBThread> getGroupThreads(
		long groupId, long userId, boolean subscribed, boolean includeAnonymous,
		QueryDefinition<MBThread> queryDefinition) {

		if (userId <= 0) {
			return getGroupThreads(groupId, queryDefinition);
		}

		if (subscribed) {
			return mbThreadFinder.findByS_G_U_C(
				groupId, userId, null, queryDefinition);
		}
		else {
			if (includeAnonymous) {
				return mbThreadFinder.findByG_U_C(
					groupId, userId, null, queryDefinition);
			}
			else {
				return mbThreadFinder.findByG_U_C_A(
					groupId, userId, null, false, queryDefinition);
			}
		}
	}

	@Override
	public List<MBThread> getGroupThreads(
		long groupId, long userId, boolean subscribed,
		QueryDefinition<MBThread> queryDefinition) {

		return getGroupThreads(
			groupId, userId, subscribed, true, queryDefinition);
	}

	@Override
	public List<MBThread> getGroupThreads(
		long groupId, long userId, QueryDefinition<MBThread> queryDefinition) {

		return getGroupThreads(groupId, userId, false, queryDefinition);
	}

	@Override
	public List<MBThread> getGroupThreads(
		long groupId, QueryDefinition<MBThread> queryDefinition) {

		if (queryDefinition.isExcludeStatus()) {
			return mbThreadPersistence.findByG_NotC_NotS(
				groupId, MBCategoryConstants.DISCUSSION_CATEGORY_ID,
				queryDefinition.getStatus(), queryDefinition.getStart(),
				queryDefinition.getEnd());
		}
		else {
			return mbThreadPersistence.findByG_NotC_S(
				groupId, MBCategoryConstants.DISCUSSION_CATEGORY_ID,
				queryDefinition.getStatus(), queryDefinition.getStart(),
				queryDefinition.getEnd());
		}
	}

	@Override
	public int getGroupThreadsCount(
		long groupId, long userId, boolean subscribed, boolean includeAnonymous,
		QueryDefinition<MBThread> queryDefinition) {

		if (userId <= 0) {
			return getGroupThreadsCount(groupId, queryDefinition);
		}

		if (subscribed) {
			return mbThreadFinder.countByS_G_U_C(
				groupId, userId, null, queryDefinition);
		}
		else {
			if (includeAnonymous) {
				return mbThreadFinder.countByG_U_C(
					groupId, userId, null, queryDefinition);
			}
			else {
				return mbThreadFinder.countByG_U_C_A(
					groupId, userId, null, false, queryDefinition);
			}
		}
	}

	@Override
	public int getGroupThreadsCount(
		long groupId, long userId, boolean subscribed,
		QueryDefinition<MBThread> queryDefinition) {

		return getGroupThreadsCount(
			groupId, userId, subscribed, true, queryDefinition);
	}

	@Override
	public int getGroupThreadsCount(
		long groupId, long userId, QueryDefinition<MBThread> queryDefinition) {

		return getGroupThreadsCount(groupId, userId, false, queryDefinition);
	}

	@Override
	public int getGroupThreadsCount(
		long groupId, QueryDefinition<MBThread> queryDefinition) {

		if (queryDefinition.isExcludeStatus()) {
			return mbThreadPersistence.countByG_NotC_NotS(
				groupId, MBCategoryConstants.DISCUSSION_CATEGORY_ID,
				queryDefinition.getStatus());
		}
		else {
			return mbThreadPersistence.countByG_NotC_S(
				groupId, MBCategoryConstants.DISCUSSION_CATEGORY_ID,
				queryDefinition.getStatus());
		}
	}

	@Override
	public List<MBThread> getNoAssetThreads() {
		return mbThreadFinder.findByNoAssets();
	}

	@Override
	public List<MBThread> getPriorityThreads(long categoryId, double priority)
		throws PortalException {

		return getPriorityThreads(categoryId, priority, false);
	}

	@Override
	public List<MBThread> getPriorityThreads(
			long categoryId, double priority, boolean inherit)
		throws PortalException {

		if (!inherit) {
			return mbThreadPersistence.findByC_P(categoryId, priority);
		}

		List<MBThread> threads = new ArrayList<>();

		while ((categoryId != MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) &&
			   (categoryId != MBCategoryConstants.DISCUSSION_CATEGORY_ID)) {

			threads.addAll(
				0, mbThreadPersistence.findByC_P(categoryId, priority));

			MBCategory category = mbCategoryPersistence.findByPrimaryKey(
				categoryId);

			categoryId = category.getParentCategoryId();
		}

		return threads;
	}

	@Override
	public MBThread getThread(long threadId) throws PortalException {
		return mbThreadPersistence.findByPrimaryKey(threadId);
	}

	@Override
	public List<MBThread> getThreads(
		long groupId, long categoryId, int status, int start, int end) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbThreadPersistence.findByG_C(
				groupId, categoryId, start, end);
		}
		else {
			return mbThreadPersistence.findByG_C_S(
				groupId, categoryId, status, start, end);
		}
	}

	@Override
	public int getThreadsCount(long groupId, long categoryId, int status) {
		if (status == WorkflowConstants.STATUS_ANY) {
			return mbThreadPersistence.countByG_C(groupId, categoryId);
		}
		else {
			return mbThreadPersistence.countByG_C_S(
				groupId, categoryId, status);
		}
	}

	@Override
	public boolean hasAnswerMessage(long threadId) {
		int count = mbMessagePersistence.countByT_A(threadId, true);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@BufferedIncrement(
		configuration = "MBThread", incrementClass = NumberIncrement.class
	)
	@Override
	public void incrementViewCounter(long threadId, int increment)
		throws PortalException {

		if (ExportImportThreadLocal.isImportInProcess()) {
			return;
		}

		MBThread thread = mbThreadPersistence.findByPrimaryKey(threadId);

		thread.setModifiedDate(thread.getModifiedDate());
		thread.setViewCount(thread.getViewCount() + increment);

		mbThreadPersistence.update(thread);
	}

	@Override
	public void moveDependentsToTrash(
			long groupId, long threadId, long trashEntryId)
		throws PortalException {

		Set<Long> userIds = new HashSet<>();

		MBThread thread = mbThreadLocalService.getThread(threadId);

		List<MBMessage> messages = mbMessageLocalService.getThreadMessages(
			threadId, WorkflowConstants.STATUS_ANY);

		for (MBMessage message : messages) {

			// Message

			if (message.isDiscussion()) {
				continue;
			}

			int oldStatus = message.getStatus();

			message.setStatus(WorkflowConstants.STATUS_IN_TRASH);

			mbMessagePersistence.update(message);

			userIds.add(message.getUserId());

			// Trash

			int status = oldStatus;

			if (oldStatus == WorkflowConstants.STATUS_PENDING) {
				status = WorkflowConstants.STATUS_DRAFT;
			}

			if (oldStatus != WorkflowConstants.STATUS_APPROVED) {
				trashVersionLocalService.addTrashVersion(
					trashEntryId, MBMessage.class.getName(),
					message.getMessageId(), status, null);
			}

			// Asset

			if (oldStatus == WorkflowConstants.STATUS_APPROVED) {
				assetEntryLocalService.updateVisible(
					MBMessage.class.getName(), message.getMessageId(), false);
			}

			// Attachments

			for (FileEntry fileEntry : message.getAttachmentsFileEntries()) {
				PortletFileRepositoryUtil.movePortletFileEntryToTrash(
					thread.getStatusByUserId(), fileEntry.getFileEntryId());
			}

			// Indexer

			Indexer<MBMessage> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				MBMessage.class);

			indexer.reindex(message);

			// Workflow

			if (oldStatus == WorkflowConstants.STATUS_PENDING) {
				workflowInstanceLinkLocalService.deleteWorkflowInstanceLink(
					message.getCompanyId(), message.getGroupId(),
					MBMessage.class.getName(), message.getMessageId());
			}
		}

		// Statistics

		for (long userId : userIds) {
			mbStatsUserLocalService.updateStatsUser(groupId, userId);
		}
	}

	@Override
	public MBThread moveThread(long groupId, long categoryId, long threadId)
		throws PortalException {

		MBThread thread = mbThreadPersistence.findByPrimaryKey(threadId);

		long oldCategoryId = thread.getCategoryId();

		MBCategory oldCategory = null;

		if (oldCategoryId != MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {
			oldCategory = mbCategoryPersistence.fetchByPrimaryKey(
				oldCategoryId);
		}

		MBCategory category = null;

		if (categoryId != MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {
			category = mbCategoryPersistence.fetchByPrimaryKey(categoryId);
		}

		// Thread

		thread.setCategoryId(categoryId);

		mbThreadPersistence.update(thread);

		// Messages

		List<MBMessage> messages = mbMessagePersistence.findByG_C_T(
			groupId, oldCategoryId, thread.getThreadId());

		for (MBMessage message : messages) {
			message.setCategoryId(categoryId);

			mbMessagePersistence.update(message);

			// Indexer

			if (!message.isDiscussion()) {
				Indexer<MBMessage> indexer =
					IndexerRegistryUtil.nullSafeGetIndexer(MBMessage.class);

				indexer.reindex(message);
			}
		}

		// Category

		if ((oldCategory != null) && (categoryId != oldCategoryId)) {
			MBUtil.updateCategoryStatistics(oldCategory.getCategoryId());
		}

		if ((category != null) && (categoryId != oldCategoryId)) {
			MBUtil.updateCategoryStatistics(category.getCategoryId());
		}

		// Indexer

		Indexer<MBThread> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			MBThread.class);

		indexer.reindex(thread);

		return thread;
	}

	@Override
	public MBThread moveThreadFromTrash(
			long userId, long categoryId, long threadId)
		throws PortalException {

		MBThread thread = mbThreadPersistence.findByPrimaryKey(threadId);

		if (!thread.isInTrash()) {
			throw new RestoreEntryException(
				RestoreEntryException.INVALID_STATUS);
		}

		if (thread.isInTrashExplicitly()) {
			restoreThreadFromTrash(userId, threadId);
		}
		else {

			// Thread

			TrashVersion trashVersion = trashVersionLocalService.fetchVersion(
				MBThread.class.getName(), thread.getThreadId());

			int status = WorkflowConstants.STATUS_APPROVED;

			if (trashVersion != null) {
				status = trashVersion.getStatus();
			}

			updateStatus(userId, threadId, status);

			// Trash

			if (trashVersion != null) {
				trashVersionLocalService.deleteTrashVersion(trashVersion);
			}

			// Messages

			restoreDependentsFromTrash(thread.getGroupId(), threadId);
		}

		return moveThread(thread.getGroupId(), categoryId, threadId);
	}

	@Override
	public void moveThreadsToTrash(long groupId, long userId)
		throws PortalException {

		List<MBThread> threads = mbThreadPersistence.findByGroupId(groupId);

		for (MBThread thread : threads) {
			moveThreadToTrash(userId, thread);
		}
	}

	@Override
	public MBThread moveThreadToTrash(long userId, long threadId)
		throws PortalException {

		MBThread thread = mbThreadPersistence.findByPrimaryKey(threadId);

		return moveThreadToTrash(userId, thread);
	}

	@Override
	public MBThread moveThreadToTrash(long userId, MBThread thread)
		throws PortalException {

		// Thread

		if (thread.isInTrash()) {
			throw new TrashEntryException();
		}

		if (thread.getCategoryId() ==
				MBCategoryConstants.DISCUSSION_CATEGORY_ID) {

			return thread;
		}

		int oldStatus = thread.getStatus();

		if (oldStatus == WorkflowConstants.STATUS_PENDING) {
			thread.setStatus(WorkflowConstants.STATUS_DRAFT);

			mbThreadPersistence.update(thread);
		}

		thread = updateStatus(
			userId, thread.getThreadId(), WorkflowConstants.STATUS_IN_TRASH);

		// Trash

		TrashEntry trashEntry = trashEntryLocalService.addTrashEntry(
			userId, thread.getGroupId(), MBThread.class.getName(),
			thread.getThreadId(), thread.getUuid(), null, oldStatus, null,
			null);

		// Messages

		moveDependentsToTrash(
			thread.getGroupId(), thread.getThreadId(), trashEntry.getEntryId());

		// Social

		MBMessage message = mbMessageLocalService.getMBMessage(
			thread.getRootMessageId());

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("rootMessageId", thread.getRootMessageId());
		extraDataJSONObject.put("title", message.getSubject());

		SocialActivityManagerUtil.addActivity(
			userId, thread, SocialActivityConstants.TYPE_MOVE_TO_TRASH,
			extraDataJSONObject.toString(), 0);

		return thread;
	}

	@Override
	public void restoreDependentsFromTrash(long groupId, long threadId)
		throws PortalException {

		Set<Long> userIds = new HashSet<>();

		MBThread thread = mbThreadLocalService.getThread(threadId);

		List<MBMessage> messages = mbMessageLocalService.getThreadMessages(
			threadId, WorkflowConstants.STATUS_ANY);

		for (MBMessage message : messages) {

			// Message

			if (message.isDiscussion()) {
				continue;
			}

			TrashVersion trashVersion = trashVersionLocalService.fetchVersion(
				MBMessage.class.getName(), message.getMessageId());

			int oldStatus = WorkflowConstants.STATUS_APPROVED;

			if (trashVersion != null) {
				oldStatus = trashVersion.getStatus();
			}

			message.setStatus(oldStatus);

			mbMessagePersistence.update(message);

			userIds.add(message.getUserId());

			// Trash

			if (trashVersion != null) {
				trashVersionLocalService.deleteTrashVersion(trashVersion);
			}

			// Asset

			if (oldStatus == WorkflowConstants.STATUS_APPROVED) {
				assetEntryLocalService.updateVisible(
					MBMessage.class.getName(), message.getMessageId(), true);
			}

			// Attachments

			for (FileEntry fileEntry : message.getAttachmentsFileEntries()) {
				PortletFileRepositoryUtil.restorePortletFileEntryFromTrash(
					thread.getStatusByUserId(), fileEntry.getFileEntryId());
			}

			// Indexer

			Indexer<MBMessage> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				MBMessage.class);

			indexer.reindex(message);
		}

		// Statistics

		for (long userId : userIds) {
			mbStatsUserLocalService.updateStatsUser(groupId, userId);
		}
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #restoreDependentsFromTrash(long, long)}
	 */
	@Deprecated
	@Override
	public void restoreDependentsFromTrash(
			long groupId, long threadId, long trashEntryId)
		throws PortalException {

		restoreDependentsFromTrash(groupId, threadId);
	}

	@Override
	public void restoreThreadFromTrash(long userId, long threadId)
		throws PortalException {

		// Thread

		MBThread thread = getThread(threadId);

		if (!thread.isInTrash()) {
			throw new RestoreEntryException(
				RestoreEntryException.INVALID_STATUS);
		}

		if (thread.getCategoryId() ==
				MBCategoryConstants.DISCUSSION_CATEGORY_ID) {

			return;
		}

		TrashEntry trashEntry = trashEntryLocalService.getEntry(
			MBThread.class.getName(), threadId);

		updateStatus(userId, threadId, trashEntry.getStatus());

		// Messages

		restoreDependentsFromTrash(thread.getGroupId(), threadId);

		// Trash

		trashEntryLocalService.deleteEntry(trashEntry.getEntryId());

		// Social

		MBMessage message = mbMessageLocalService.getMBMessage(
			thread.getRootMessageId());

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("rootMessageId", thread.getRootMessageId());
		extraDataJSONObject.put("title", message.getSubject());

		SocialActivityManagerUtil.addActivity(
			userId, thread, SocialActivityConstants.TYPE_RESTORE_FROM_TRASH,
			extraDataJSONObject.toString(), 0);
	}

	@Override
	public Hits search(
			long groupId, long userId, long creatorUserId, int status,
			int start, int end)
		throws PortalException {

		return search(groupId, userId, creatorUserId, 0, 0, status, start, end);
	}

	@Override
	public Hits search(
			long groupId, long userId, long creatorUserId, long startDate,
			long endDate, int status, int start, int end)
		throws PortalException {

		Indexer<MBThread> indexer = IndexerRegistryUtil.getIndexer(
			MBThread.class.getName());

		SearchContext searchContext = new SearchContext();

		searchContext.setAttribute(Field.STATUS, status);

		if (endDate > 0) {
			searchContext.setAttribute("endDate", endDate);
		}

		searchContext.setAttribute("paginationType", "none");

		if (creatorUserId > 0) {
			searchContext.setAttribute(
				"participantUserId", String.valueOf(creatorUserId));
		}

		if (startDate > 0) {
			searchContext.setAttribute("startDate", startDate);
		}

		Group group = groupLocalService.getGroup(groupId);

		searchContext.setCompanyId(group.getCompanyId());

		searchContext.setEnd(end);
		searchContext.setGroupIds(new long[] {groupId});
		searchContext.setSorts(new Sort("lastPostDate", true));
		searchContext.setStart(start);
		searchContext.setUserId(userId);

		return indexer.search(searchContext);
	}

	@Override
	public MBThread splitThread(
			long userId, long messageId, String subject,
			ServiceContext serviceContext)
		throws PortalException {

		MBMessage message = mbMessagePersistence.findByPrimaryKey(messageId);

		if (message.isRoot()) {
			throw new SplitThreadException(
				"Unable to split message " + messageId +
					" because it is a root message");
		}

		MBCategory category = message.getCategory();

		MBThread oldThread = message.getThread();

		MBMessage rootMessage = mbMessagePersistence.findByPrimaryKey(
			oldThread.getRootMessageId());

		long oldAttachmentsFolderId = message.getAttachmentsFolderId();

		// Message flags

		mbMessageLocalService.updateAnswer(message, false, true);

		// Create new thread

		MBThread thread = addThread(
			message.getCategoryId(), message, serviceContext);

		mbThreadPersistence.update(oldThread);

		// Update messages

		if (Validator.isNotNull(subject)) {
			MBMessageDisplay messageDisplay =
				mbMessageLocalService.getMessageDisplay(
					userId, messageId, WorkflowConstants.STATUS_ANY);

			MBTreeWalker treeWalker = messageDisplay.getTreeWalker();

			List<MBMessage> messages = treeWalker.getMessages();

			int[] range = treeWalker.getChildrenRange(message);

			for (int i = range[0]; i < range[1]; i++) {
				MBMessage curMessage = messages.get(i);

				String oldSubject = message.getSubject();
				String curSubject = curMessage.getSubject();

				if (oldSubject.startsWith("RE: ")) {
					curSubject = StringUtil.replace(
						curSubject, rootMessage.getSubject(), subject);
				}
				else {
					curSubject = StringUtil.replace(
						curSubject, oldSubject, subject);
				}

				curMessage.setSubject(curSubject);

				mbMessagePersistence.update(curMessage);
			}

			message.setSubject(subject);
		}

		message.setThreadId(thread.getThreadId());
		message.setRootMessageId(thread.getRootMessageId());
		message.setParentMessageId(0);

		mbMessagePersistence.update(message);

		// Attachments

		moveAttachmentsFolders(
			message, oldAttachmentsFolderId, oldThread, thread, serviceContext);

		// Indexer

		if (!message.isDiscussion()) {
			Indexer<MBMessage> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				MBMessage.class);

			indexer.reindex(message);
		}

		// Update children

		moveChildrenMessages(message, category, oldThread.getThreadId());

		// Update new thread

		MBUtil.updateThreadMessageCount(thread.getThreadId());

		// Update old thread

		MBUtil.updateThreadMessageCount(oldThread.getThreadId());

		// Category

		if ((message.getCategoryId() !=
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) &&
			(message.getCategoryId() !=
				MBCategoryConstants.DISCUSSION_CATEGORY_ID)) {

			MBUtil.updateCategoryThreadCount(category.getCategoryId());
		}

		// Indexer

		Indexer<MBThread> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			MBThread.class);

		indexer.reindex(oldThread);

		indexer.reindex(message.getThread());

		return thread;
	}

	@Override
	public MBThread updateMessageCount(long threadId) {
		MBThread mbThread = mbThreadPersistence.fetchByPrimaryKey(threadId);

		if (mbThread == null) {
			return null;
		}

		int messageCount = mbMessageLocalService.getThreadMessagesCount(
			threadId, WorkflowConstants.STATUS_APPROVED);

		mbThread.setMessageCount(messageCount);

		return mbThreadPersistence.update(mbThread);
	}

	@Override
	public void updateQuestion(long threadId, boolean question)
		throws PortalException {

		MBThread thread = mbThreadPersistence.findByPrimaryKey(threadId);

		if (thread.isQuestion() == question) {
			return;
		}

		thread.setQuestion(question);

		mbThreadPersistence.update(thread);

		if (!question) {
			MBMessage message = mbMessagePersistence.findByPrimaryKey(
				thread.getRootMessageId());

			mbMessageLocalService.updateAnswer(message, false, true);
		}
	}

	@Override
	public MBThread updateStatus(long userId, long threadId, int status)
		throws PortalException {

		MBThread thread = mbThreadPersistence.findByPrimaryKey(threadId);

		// Thread

		User user = userPersistence.findByPrimaryKey(userId);

		thread.setStatus(status);
		thread.setStatusByUserId(user.getUserId());
		thread.setStatusByUserName(user.getFullName());
		thread.setStatusDate(new Date());

		mbThreadPersistence.update(thread);

		// Messages

		if (thread.getCategoryId() !=
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {

			// Category

			MBCategory category = mbCategoryPersistence.fetchByPrimaryKey(
				thread.getCategoryId());

			if (category != null) {
				MBUtil.updateCategoryStatistics(category.getCategoryId());
			}
		}

		// Indexer

		Indexer<MBThread> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			MBThread.class);

		indexer.reindex(thread);

		return thread;
	}

	protected void moveAttachmentsFolders(
			MBMessage message, long oldAttachmentsFolderId, MBThread oldThread,
			MBThread newThread, ServiceContext serviceContext)
		throws PortalException {

		if (oldAttachmentsFolderId !=
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			Folder newThreadFolder = newThread.addAttachmentsFolder();

			PortletFileRepositoryUtil.movePortletFolder(
				message.getGroupId(), message.getUserId(),
				oldAttachmentsFolderId, newThreadFolder.getFolderId(),
				serviceContext);
		}

		List<MBMessage> childMessages = mbMessagePersistence.findByT_P(
			oldThread.getThreadId(), message.getMessageId());

		for (MBMessage childMessage : childMessages) {
			moveAttachmentsFolders(
				childMessage, childMessage.getAttachmentsFolderId(), oldThread,
				newThread, serviceContext);
		}
	}

	protected void moveChildrenMessages(
			MBMessage parentMessage, MBCategory category, long oldThreadId)
		throws PortalException {

		List<MBMessage> messages = mbMessagePersistence.findByT_P(
			oldThreadId, parentMessage.getMessageId());

		for (MBMessage message : messages) {
			message.setCategoryId(parentMessage.getCategoryId());
			message.setThreadId(parentMessage.getThreadId());
			message.setRootMessageId(parentMessage.getRootMessageId());

			mbMessagePersistence.update(message);

			if (!message.isDiscussion()) {
				Indexer<MBMessage> indexer =
					IndexerRegistryUtil.nullSafeGetIndexer(MBMessage.class);

				indexer.reindex(message);
			}

			moveChildrenMessages(message, category, oldThreadId);
		}
	}

}