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

package com.liferay.journal.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureLink;
import com.liferay.dynamic.data.mapping.service.DDMStructureLinkLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.journal.exception.DuplicateFolderNameException;
import com.liferay.journal.exception.InvalidDDMStructureException;
import com.liferay.journal.exception.NoSuchFolderException;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.service.base.JournalFolderLocalServiceBaseImpl;
import com.liferay.journal.util.JournalValidator;
import com.liferay.journal.util.comparator.FolderIdComparator;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.social.SocialActivityManagerUtil;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.tree.TreeModelTasksAdapter;
import com.liferay.portal.kernel.tree.TreePathUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.util.PropsValues;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.trash.kernel.exception.RestoreEntryException;
import com.liferay.trash.kernel.exception.TrashEntryException;
import com.liferay.trash.kernel.model.TrashEntry;
import com.liferay.trash.kernel.model.TrashVersion;
import com.liferay.trash.kernel.util.TrashUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Juan Fernández
 */
public class JournalFolderLocalServiceImpl
	extends JournalFolderLocalServiceBaseImpl {

	@Override
	public JournalFolder addFolder(
			long userId, long groupId, long parentFolderId, String name,
			String description, ServiceContext serviceContext)
		throws PortalException {

		// Folder

		User user = userPersistence.findByPrimaryKey(userId);
		parentFolderId = getParentFolderId(groupId, parentFolderId);

		validateFolder(0, groupId, parentFolderId, name);

		long folderId = counterLocalService.increment();

		JournalFolder folder = journalFolderPersistence.create(folderId);

		folder.setUuid(serviceContext.getUuid());
		folder.setGroupId(groupId);
		folder.setCompanyId(user.getCompanyId());
		folder.setUserId(user.getUserId());
		folder.setUserName(user.getFullName());
		folder.setParentFolderId(parentFolderId);
		folder.setTreePath(folder.buildTreePath());
		folder.setName(name);
		folder.setDescription(description);
		folder.setExpandoBridgeAttributes(serviceContext);

		journalFolderPersistence.update(folder);

		// Resources

		resourceLocalService.addModelResources(folder, serviceContext);

		// Asset

		updateAsset(
			userId, folder, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds(),
			serviceContext.getAssetPriority());

		return folder;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(
		action = SystemEventConstants.ACTION_SKIP,
		type = SystemEventConstants.TYPE_DELETE
	)
	public JournalFolder deleteFolder(JournalFolder folder)
		throws PortalException {

		return deleteFolder(folder, true);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(
		action = SystemEventConstants.ACTION_SKIP,
		type = SystemEventConstants.TYPE_DELETE
	)
	public JournalFolder deleteFolder(
			JournalFolder folder, boolean includeTrashedEntries)
		throws PortalException {

		// Folders

		List<JournalFolder> folders = journalFolderPersistence.findByG_P(
			folder.getGroupId(), folder.getFolderId());

		for (JournalFolder curFolder : folders) {
			if (includeTrashedEntries || !curFolder.isInTrashExplicitly()) {
				journalFolderLocalService.deleteFolder(
					curFolder, includeTrashedEntries);
			}
		}

		// Folder

		journalFolderPersistence.remove(folder);

		// Resources

		resourceLocalService.deleteResource(
			folder, ResourceConstants.SCOPE_INDIVIDUAL);

		// Entries

		journalArticleLocalService.deleteArticles(
			folder.getGroupId(), folder.getFolderId(), includeTrashedEntries);

		// Asset

		assetEntryLocalService.deleteEntry(
			JournalFolder.class.getName(), folder.getFolderId());

		// Expando

		expandoValueLocalService.deleteValues(
			JournalFolder.class.getName(), folder.getFolderId());

		// Ratings

		ratingsStatsLocalService.deleteStats(
			JournalFolder.class.getName(), folder.getFolderId());

		// Trash

		if (folder.isInTrashExplicitly()) {
			trashEntryLocalService.deleteEntry(
				JournalFolder.class.getName(), folder.getFolderId());
		}
		else {
			trashVersionLocalService.deleteTrashVersion(
				JournalFolder.class.getName(), folder.getFolderId());
		}

		// Workflow

		List<DDMStructureLink> ddmStructureLinks =
			ddmStructureLinkLocalService.getStructureLinks(
				classNameLocalService.getClassNameId(JournalFolder.class),
				folder.getFolderId());

		if (ddmStructureLinks.isEmpty()) {
			WorkflowDefinitionLink workflowDefinitionLink =
				workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(
					folder.getCompanyId(), folder.getGroupId(),
					JournalFolder.class.getName(), folder.getFolderId(),
					JournalArticleConstants.DDM_STRUCTURE_ID_ALL);

			if (workflowDefinitionLink != null) {
				workflowDefinitionLinkLocalService.deleteWorkflowDefinitionLink(
					workflowDefinitionLink);
			}
		}

		for (DDMStructureLink ddmStructureLink : ddmStructureLinks) {
			ddmStructureLinkLocalService.deleteStructureLink(
				ddmStructureLink.getStructureLinkId());

			WorkflowDefinitionLink workflowDefinitionLink =
				workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(
					folder.getCompanyId(), folder.getGroupId(),
					JournalFolder.class.getName(), folder.getFolderId(),
					ddmStructureLink.getStructureId());

			if (workflowDefinitionLink != null) {
				workflowDefinitionLinkLocalService.deleteWorkflowDefinitionLink(
					workflowDefinitionLink);
			}
		}

		return folder;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public JournalFolder deleteFolder(long folderId) throws PortalException {
		JournalFolder folder = journalFolderPersistence.findByPrimaryKey(
			folderId);

		return journalFolderLocalService.deleteFolder(folder, true);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public JournalFolder deleteFolder(
			long folderId, boolean includeTrashedEntries)
		throws PortalException {

		JournalFolder folder = journalFolderPersistence.findByPrimaryKey(
			folderId);

		return journalFolderLocalService.deleteFolder(
			folder, includeTrashedEntries);
	}

	@Override
	public void deleteFolders(long groupId) throws PortalException {
		List<JournalFolder> folders = journalFolderPersistence.findByGroupId(
			groupId);

		for (JournalFolder folder : folders) {
			journalFolderLocalService.deleteFolder(folder);
		}
	}

	@Override
	public JournalFolder fetchFolder(long folderId) {
		return journalFolderPersistence.fetchByPrimaryKey(folderId);
	}

	@Override
	public JournalFolder fetchFolder(
		long groupId, long parentFolderId, String name) {

		return journalFolderPersistence.fetchByG_P_N(
			groupId, parentFolderId, name);
	}

	@Override
	public JournalFolder fetchFolder(long groupId, String name) {
		return journalFolderPersistence.fetchByG_N(groupId, name);
	}

	@Override
	public List<JournalFolder> getCompanyFolders(
		long companyId, int start, int end) {

		return journalFolderPersistence.findByCompanyId(companyId, start, end);
	}

	@Override
	public int getCompanyFoldersCount(long companyId) {
		return journalFolderPersistence.countByCompanyId(companyId);
	}

	@Override
	public List<DDMStructure> getDDMStructures(
			long[] groupIds, long folderId, int restrictionType)
		throws PortalException {

		if (restrictionType ==
				JournalFolderConstants.
					RESTRICTION_TYPE_DDM_STRUCTURES_AND_WORKFLOW) {

			return ddmStructureLinkLocalService.getStructureLinkStructures(
				classNameLocalService.getClassNameId(JournalFolder.class),
				folderId);
		}

		folderId = getOverridedDDMStructuresFolderId(folderId);

		if (folderId != JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return ddmStructureLinkLocalService.getStructureLinkStructures(
				classNameLocalService.getClassNameId(JournalFolder.class),
				folderId);
		}

		long classNameId = classNameLocalService.getClassNameId(
			JournalArticle.class);

		return ddmStructureLocalService.getStructures(groupIds, classNameId);
	}

	@Override
	public JournalFolder getFolder(long folderId) throws PortalException {
		return journalFolderPersistence.findByPrimaryKey(folderId);
	}

	@Override
	public List<JournalFolder> getFolders(long groupId) {
		return journalFolderPersistence.findByGroupId(groupId);
	}

	@Override
	public List<JournalFolder> getFolders(long groupId, long parentFolderId) {
		return getFolders(
			groupId, parentFolderId, WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public List<JournalFolder> getFolders(
		long groupId, long parentFolderId, int status) {

		return journalFolderPersistence.findByG_P_S(
			groupId, parentFolderId, status);
	}

	@Override
	public List<JournalFolder> getFolders(
		long groupId, long parentFolderId, int start, int end) {

		return getFolders(
			groupId, parentFolderId, WorkflowConstants.STATUS_APPROVED, start,
			end);
	}

	@Override
	public List<JournalFolder> getFolders(
		long groupId, long parentFolderId, int status, int start, int end) {

		return journalFolderPersistence.findByG_P_S(
			groupId, parentFolderId, status, start, end);
	}

	@Override
	public List<Object> getFoldersAndArticles(long groupId, long folderId) {
		QueryDefinition<?> queryDefinition = new QueryDefinition<>(
			WorkflowConstants.STATUS_ANY);

		return journalFolderFinder.findF_A_ByG_F(
			groupId, folderId, queryDefinition);
	}

	@Override
	public List<Object> getFoldersAndArticles(
		long groupId, long folderId, int status) {

		QueryDefinition<?> queryDefinition = new QueryDefinition<>(status);

		return journalFolderFinder.findF_A_ByG_F(
			groupId, folderId, queryDefinition);
	}

	@Override
	public List<Object> getFoldersAndArticles(
		long groupId, long folderId, int status, int start, int end,
		OrderByComparator<?> obc) {

		QueryDefinition<?> queryDefinition = new QueryDefinition<>(
			status, start, end, (OrderByComparator<Object>)obc);

		return journalFolderFinder.findF_A_ByG_F(
			groupId, folderId, queryDefinition);
	}

	@Override
	public List<Object> getFoldersAndArticles(
		long groupId, long folderId, int start, int end,
		OrderByComparator<?> obc) {

		return getFoldersAndArticles(
			groupId, folderId, WorkflowConstants.STATUS_ANY, start, end, obc);
	}

	@Override
	public int getFoldersAndArticlesCount(
		long groupId, List<Long> folderIds, int status) {

		QueryDefinition<JournalArticle> queryDefinition = new QueryDefinition<>(
			status);

		if (folderIds.size() <= PropsValues.SQL_DATA_MAX_PARAMETERS) {
			return journalArticleFinder.countByG_F(
				groupId, folderIds, queryDefinition);
		}
		else {
			int start = 0;
			int end = PropsValues.SQL_DATA_MAX_PARAMETERS;

			int articlesCount = journalArticleFinder.countByG_F(
				groupId, folderIds.subList(start, end), queryDefinition);

			folderIds.subList(start, end).clear();

			articlesCount += getFoldersAndArticlesCount(
				groupId, folderIds, status);

			return articlesCount;
		}
	}

	@Override
	public int getFoldersAndArticlesCount(long groupId, long folderId) {
		QueryDefinition<?> queryDefinition = new QueryDefinition<>(
			WorkflowConstants.STATUS_ANY);

		return journalFolderFinder.countF_A_ByG_F(
			groupId, folderId, queryDefinition);
	}

	@Override
	public int getFoldersAndArticlesCount(
		long groupId, long folderId, int status) {

		QueryDefinition<?> queryDefinition = new QueryDefinition<>(
			status, 0, false);

		return journalFolderFinder.countF_A_ByG_F(
			groupId, folderId, queryDefinition);
	}

	@Override
	public int getFoldersCount(long groupId, long parentFolderId) {
		return getFoldersCount(
			groupId, parentFolderId, WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public int getFoldersCount(long groupId, long parentFolderId, int status) {
		return journalFolderPersistence.countByG_P_S(
			groupId, parentFolderId, status);
	}

	@Override
	public long getInheritedWorkflowFolderId(long folderId)
		throws NoSuchFolderException {

		while (folderId != JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			JournalFolder folder = journalFolderPersistence.findByPrimaryKey(
				folderId);

			if (folder.getRestrictionType() !=
					JournalFolderConstants.RESTRICTION_TYPE_INHERIT) {

				break;
			}

			folderId = folder.getParentFolderId();
		}

		return folderId;
	}

	@Override
	public List<JournalFolder> getNoAssetFolders() {
		return journalFolderFinder.findF_ByNoAssets();
	}

	@Override
	public long getOverridedDDMStructuresFolderId(long folderId)
		throws NoSuchFolderException {

		while (folderId != JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			JournalFolder folder = journalFolderPersistence.findByPrimaryKey(
				folderId);

			if (folder.getRestrictionType() ==
					JournalFolderConstants.
						RESTRICTION_TYPE_DDM_STRUCTURES_AND_WORKFLOW) {

				break;
			}

			folderId = folder.getParentFolderId();
		}

		return folderId;
	}

	@Override
	public void getSubfolderIds(
		List<Long> folderIds, long groupId, long folderId) {

		List<JournalFolder> folders = journalFolderPersistence.findByG_P(
			groupId, folderId);

		for (JournalFolder folder : folders) {
			folderIds.add(folder.getFolderId());

			getSubfolderIds(
				folderIds, folder.getGroupId(), folder.getFolderId());
		}
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JournalFolder moveFolder(
			long folderId, long parentFolderId, ServiceContext serviceContext)
		throws PortalException {

		JournalFolder folder = journalFolderPersistence.findByPrimaryKey(
			folderId);

		parentFolderId = getParentFolderId(folder, parentFolderId);

		if (folder.getParentFolderId() == parentFolderId) {
			return folder;
		}

		validateFolderDDMStructures(folder.getFolderId(), parentFolderId);

		validateFolder(
			folder.getFolderId(), folder.getGroupId(), parentFolderId,
			folder.getName());

		folder.setParentFolderId(parentFolderId);
		folder.setTreePath(folder.buildTreePath());
		folder.setExpandoBridgeAttributes(serviceContext);

		journalFolderPersistence.update(folder);

		rebuildTree(
			folder.getCompanyId(), folderId, folder.getTreePath(), true);

		return folder;
	}

	@Override
	public JournalFolder moveFolderFromTrash(
			long userId, long folderId, long parentFolderId,
			ServiceContext serviceContext)
		throws PortalException {

		JournalFolder folder = journalFolderPersistence.findByPrimaryKey(
			folderId);

		if (!folder.isInTrash()) {
			throw new RestoreEntryException(
				RestoreEntryException.INVALID_STATUS);
		}

		if (folder.isInTrashExplicitly()) {
			restoreFolderFromTrash(userId, folderId);
		}
		else {

			// Folder

			TrashVersion trashVersion = trashVersionLocalService.fetchVersion(
				JournalFolder.class.getName(), folderId);

			int status = WorkflowConstants.STATUS_APPROVED;

			if (trashVersion != null) {
				status = trashVersion.getStatus();
			}

			updateStatus(userId, folder, status);

			// Trash

			if (trashVersion != null) {
				trashVersionLocalService.deleteTrashVersion(trashVersion);
			}

			// Folders and articles

			List<Object> foldersAndArticles =
				journalFolderLocalService.getFoldersAndArticles(
					folder.getGroupId(), folder.getFolderId(),
					WorkflowConstants.STATUS_IN_TRASH);

			restoreDependentsFromTrash(foldersAndArticles);
		}

		return journalFolderLocalService.moveFolder(
			folderId, parentFolderId, serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JournalFolder moveFolderToTrash(long userId, long folderId)
		throws PortalException {

		// Folder

		JournalFolder folder = journalFolderPersistence.findByPrimaryKey(
			folderId);

		if (folder.isInTrash()) {
			throw new TrashEntryException();
		}

		String title = folder.getName();

		folder = updateStatus(
			userId, folder, WorkflowConstants.STATUS_IN_TRASH);

		// Trash

		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		typeSettingsProperties.put("title", folder.getName());

		TrashEntry trashEntry = trashEntryLocalService.addTrashEntry(
			userId, folder.getGroupId(), JournalFolder.class.getName(),
			folder.getFolderId(), folder.getUuid(), null,
			WorkflowConstants.STATUS_APPROVED, null, typeSettingsProperties);

		folder.setName(TrashUtil.getTrashTitle(trashEntry.getEntryId()));

		journalFolderPersistence.update(folder);

		// Folders and articles

		List<Object> foldersAndArticles =
			journalFolderLocalService.getFoldersAndArticles(
				folder.getGroupId(), folder.getFolderId());

		moveDependentsToTrash(foldersAndArticles, trashEntry.getEntryId());

		// Social

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", title);

		SocialActivityManagerUtil.addActivity(
			userId, folder, SocialActivityConstants.TYPE_MOVE_TO_TRASH,
			extraDataJSONObject.toString(), 0);

		return folder;
	}

	@Override
	public void rebuildTree(long companyId) throws PortalException {
		rebuildTree(
			companyId, JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			StringPool.SLASH, false);
	}

	@Override
	public void rebuildTree(
			long companyId, long parentFolderId, String parentTreePath,
			final boolean reindex)
		throws PortalException {

		TreePathUtil.rebuildTree(
			companyId, parentFolderId, parentTreePath,
			new TreeModelTasksAdapter<JournalFolder>() {

				@Override
				public List<JournalFolder> findTreeModels(
					long previousId, long companyId, long parentPrimaryKey,
					int size) {

					return journalFolderPersistence.findByF_C_P_NotS(
						previousId, companyId, parentPrimaryKey,
						WorkflowConstants.STATUS_IN_TRASH, QueryUtil.ALL_POS,
						size, new FolderIdComparator(true));
				}

				@Override
				public void rebuildDependentModelsTreePaths(
						long parentPrimaryKey, String treePath)
					throws PortalException {

					journalArticleLocalService.setTreePaths(
						parentPrimaryKey, treePath, false);
				}

			});
	}

	@Override
	public void restoreFolderFromTrash(long userId, long folderId)
		throws PortalException {

		// Folder

		JournalFolder folder = journalFolderPersistence.findByPrimaryKey(
			folderId);

		if (!folder.isInTrash()) {
			throw new RestoreEntryException(
				RestoreEntryException.INVALID_STATUS);
		}

		folder.setName(TrashUtil.getOriginalTitle(folder.getName()));

		journalFolderPersistence.update(folder);

		TrashEntry trashEntry = trashEntryLocalService.getEntry(
			JournalFolder.class.getName(), folderId);

		updateStatus(userId, folder, trashEntry.getStatus());

		// Folders and articles

		List<Object> foldersAndArticles =
			journalFolderLocalService.getFoldersAndArticles(
				folder.getGroupId(), folder.getFolderId(),
				WorkflowConstants.STATUS_IN_TRASH);

		restoreDependentsFromTrash(foldersAndArticles);

		// Trash

		trashEntryLocalService.deleteEntry(
			JournalFolder.class.getName(), folder.getFolderId());

		// Social

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", folder.getName());

		SocialActivityManagerUtil.addActivity(
			userId, folder, SocialActivityConstants.TYPE_RESTORE_FROM_TRASH,
			extraDataJSONObject.toString(), 0);
	}

	@Override
	public void subscribe(long userId, long groupId, long folderId)
		throws PortalException {

		if (folderId == JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			folderId = groupId;
		}

		subscriptionLocalService.addSubscription(
			userId, groupId, JournalFolder.class.getName(), folderId);
	}

	@Override
	public void unsubscribe(long userId, long groupId, long folderId)
		throws PortalException {

		if (folderId == JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			folderId = groupId;
		}

		subscriptionLocalService.deleteSubscription(
			userId, JournalFolder.class.getName(), folderId);
	}

	@Override
	public void updateAsset(
			long userId, JournalFolder folder, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds, Double priority)
		throws PortalException {

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, folder.getGroupId(), folder.getCreateDate(),
			folder.getModifiedDate(), JournalFolder.class.getName(),
			folder.getFolderId(), folder.getUuid(), 0, assetCategoryIds,
			assetTagNames, true, true, null, null, folder.getCreateDate(), null,
			ContentTypes.TEXT_PLAIN, folder.getName(), folder.getDescription(),
			null, null, null, 0, 0, priority);

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JournalFolder updateFolder(
			long userId, long groupId, long folderId, long parentFolderId,
			String name, String description, boolean mergeWithParentFolder,
			ServiceContext serviceContext)
		throws PortalException {

		return updateFolder(
			userId, groupId, folderId, parentFolderId, name, description,
			new long[0], JournalFolderConstants.RESTRICTION_TYPE_INHERIT,
			mergeWithParentFolder, serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JournalFolder updateFolder(
			long userId, long groupId, long folderId, long parentFolderId,
			String name, String description, long[] ddmStructureIds,
			int restrictionType, boolean mergeWithParentFolder,
			ServiceContext serviceContext)
		throws PortalException {

		JournalFolder folder = null;

		Set<Long> originalDDMStructureIds = new HashSet<>();

		if (folderId > JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			originalDDMStructureIds = getDDMStructureIds(
				ddmStructureLinkLocalService.getStructureLinks(
					classNameLocalService.getClassNameId(JournalFolder.class),
					folderId));

			folder = doUpdateFolder(
				userId, folderId, parentFolderId, name, description,
				ddmStructureIds, restrictionType, mergeWithParentFolder,
				serviceContext);
		}

		List<ObjectValuePair<Long, String>> workflowDefinitionOVPs =
			new ArrayList<>();

		if (restrictionType ==
				JournalFolderConstants.
					RESTRICTION_TYPE_DDM_STRUCTURES_AND_WORKFLOW) {

			workflowDefinitionOVPs.add(
				new ObjectValuePair<Long, String>(
					JournalArticleConstants.DDM_STRUCTURE_ID_ALL,
					StringPool.BLANK));

			for (long ddmStructureId : ddmStructureIds) {
				String workflowDefinition = ParamUtil.getString(
					serviceContext, "workflowDefinition" + ddmStructureId);

				workflowDefinitionOVPs.add(
					new ObjectValuePair<Long, String>(
						ddmStructureId, workflowDefinition));
			}
		}
		else if (restrictionType ==
					JournalFolderConstants.RESTRICTION_TYPE_INHERIT) {

			if (originalDDMStructureIds.isEmpty()) {
				originalDDMStructureIds.add(
					JournalArticleConstants.DDM_STRUCTURE_ID_ALL);
			}

			for (long originalDDMStructureId : originalDDMStructureIds) {
				workflowDefinitionOVPs.add(
					new ObjectValuePair<Long, String>(
						originalDDMStructureId, StringPool.BLANK));
			}
		}
		else if (restrictionType ==
					JournalFolderConstants.RESTRICTION_TYPE_WORKFLOW) {

			String workflowDefinition = ParamUtil.getString(
				serviceContext,
				"workflowDefinition" +
					JournalArticleConstants.DDM_STRUCTURE_ID_ALL);

			workflowDefinitionOVPs.add(
				new ObjectValuePair<Long, String>(
					JournalArticleConstants.DDM_STRUCTURE_ID_ALL,
					workflowDefinition));

			for (long originalDDMStructureId : originalDDMStructureIds) {
				workflowDefinitionOVPs.add(
					new ObjectValuePair<Long, String>(
						originalDDMStructureId, StringPool.BLANK));
			}
		}

		workflowDefinitionLinkLocalService.updateWorkflowDefinitionLinks(
			userId, serviceContext.getCompanyId(), groupId,
			JournalFolder.class.getName(), folderId, workflowDefinitionOVPs);

		return folder;
	}

	@Override
	public void updateFolderDDMStructures(
			JournalFolder folder, long[] ddmStructureIdsArray)
		throws PortalException {

		Set<Long> ddmStructureIds = SetUtil.fromArray(ddmStructureIdsArray);

		List<DDMStructureLink> ddmStructureLinks =
			ddmStructureLinkLocalService.getStructureLinks(
				classNameLocalService.getClassNameId(JournalFolder.class),
				folder.getFolderId());

		Set<Long> originalDDMStructureIds = getDDMStructureIds(
			ddmStructureLinks);

		if (ddmStructureIds.equals(originalDDMStructureIds)) {
			return;
		}

		for (Long ddmStructureId : ddmStructureIds) {
			if (!originalDDMStructureIds.contains(ddmStructureId)) {
				ddmStructureLinkLocalService.addStructureLink(
					classNameLocalService.getClassNameId(JournalFolder.class),
					folder.getFolderId(), ddmStructureId);
			}
		}

		for (Long originalDDMStructureId : originalDDMStructureIds) {
			if (!ddmStructureIds.contains(originalDDMStructureId)) {
				ddmStructureLinkLocalService.deleteStructureLink(
					classNameLocalService.getClassNameId(JournalFolder.class),
					folder.getFolderId(), originalDDMStructureId);
			}
		}
	}

	@Override
	public JournalFolder updateStatus(
			long userId, JournalFolder folder, int status)
		throws PortalException {

		// Folder

		User user = userPersistence.findByPrimaryKey(userId);

		folder.setStatus(status);
		folder.setStatusByUserId(userId);
		folder.setStatusByUserName(user.getFullName());
		folder.setStatusDate(new Date());

		journalFolderPersistence.update(folder);

		// Asset

		if (status == WorkflowConstants.STATUS_APPROVED) {
			assetEntryLocalService.updateVisible(
				JournalFolder.class.getName(), folder.getFolderId(), true);
		}
		else if (status == WorkflowConstants.STATUS_IN_TRASH) {
			assetEntryLocalService.updateVisible(
				JournalFolder.class.getName(), folder.getFolderId(), false);
		}

		// Indexer

		Indexer<JournalFolder> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			JournalFolder.class);

		indexer.reindex(folder);

		return folder;
	}

	@Override
	public void validateFolderDDMStructures(long folderId, long parentFolderId)
		throws PortalException {

		JournalFolder folder = journalFolderLocalService.fetchFolder(folderId);

		int restrictionType =
			JournalFolderConstants.RESTRICTION_TYPE_DDM_STRUCTURES_AND_WORKFLOW;

		JournalFolder parentFolder = journalFolderLocalService.fetchFolder(
			parentFolderId);

		if (parentFolder != null) {
			restrictionType = parentFolder.getRestrictionType();
		}

		List<DDMStructure> folderDDMStructures = getDDMStructures(
			PortalUtil.getCurrentAndAncestorSiteGroupIds(folder.getGroupId()),
			parentFolderId, restrictionType);

		long[] ddmStructureIds = new long[folderDDMStructures.size()];

		for (int i = 0; i < folderDDMStructures.size(); i++) {
			DDMStructure folderDDMStructure = folderDDMStructures.get(i);

			ddmStructureIds[i] = folderDDMStructure.getStructureId();
		}

		validateArticleDDMStructures(folderId, ddmStructureIds);
	}

	protected JournalFolder doUpdateFolder(
			long userId, long folderId, long parentFolderId, String name,
			String description, long[] ddmStructureIds, int restrictionType,
			boolean mergeWithParentFolder, ServiceContext serviceContext)
		throws PortalException {

		// Merge folders

		if (restrictionType !=
				JournalFolderConstants.
					RESTRICTION_TYPE_DDM_STRUCTURES_AND_WORKFLOW) {

			ddmStructureIds = new long[0];
		}

		validateArticleDDMStructures(folderId, ddmStructureIds);

		JournalFolder folder = journalFolderPersistence.findByPrimaryKey(
			folderId);

		parentFolderId = getParentFolderId(folder, parentFolderId);

		if (mergeWithParentFolder && (folderId != parentFolderId)) {
			mergeFolders(folder, parentFolderId);

			return folder;
		}

		// Folder

		validateFolder(folderId, folder.getGroupId(), parentFolderId, name);

		long oldParentFolderId = folder.getParentFolderId();

		if (oldParentFolderId != parentFolderId) {
			folder.setParentFolderId(parentFolderId);
			folder.setTreePath(folder.buildTreePath());
		}

		folder.setName(name);
		folder.setDescription(description);
		folder.setRestrictionType(restrictionType);
		folder.setExpandoBridgeAttributes(serviceContext);

		journalFolderPersistence.update(folder);

		// Asset

		updateAsset(
			userId, folder, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds(),
			serviceContext.getAssetPriority());

		// Dynamic data mapping

		if (ddmStructureIds != null) {
			updateFolderDDMStructures(folder, ddmStructureIds);
		}

		if (oldParentFolderId != parentFolderId) {
			rebuildTree(
				folder.getCompanyId(), folderId, folder.getTreePath(), true);
		}

		return folder;
	}

	protected Set<Long> getDDMStructureIds(
		List<DDMStructureLink> ddmStructureLinks) {

		Set<Long> ddmStructureIds = new HashSet<>();

		for (DDMStructureLink ddmStructureLink : ddmStructureLinks) {
			ddmStructureIds.add(ddmStructureLink.getStructureId());
		}

		return ddmStructureIds;
	}

	protected long getParentFolderId(
		JournalFolder folder, long parentFolderId) {

		if (parentFolderId == JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return parentFolderId;
		}

		if (folder.getFolderId() == parentFolderId) {
			return folder.getParentFolderId();
		}

		JournalFolder parentFolder = journalFolderPersistence.fetchByPrimaryKey(
			parentFolderId);

		if ((parentFolder == null) ||
			(folder.getGroupId() != parentFolder.getGroupId())) {

			return folder.getParentFolderId();
		}

		List<Long> subfolderIds = new ArrayList<>();

		getSubfolderIds(
			subfolderIds, folder.getGroupId(), folder.getFolderId());

		if (subfolderIds.contains(parentFolderId)) {
			return folder.getParentFolderId();
		}

		return parentFolderId;
	}

	protected long getParentFolderId(long groupId, long parentFolderId) {
		if (parentFolderId != JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			JournalFolder parentFolder =
				journalFolderPersistence.fetchByPrimaryKey(parentFolderId);

			if ((parentFolder == null) ||
				(groupId != parentFolder.getGroupId())) {

				parentFolderId =
					JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID;
			}
		}

		return parentFolderId;
	}

	protected void mergeFolders(JournalFolder fromFolder, long toFolderId)
		throws PortalException {

		List<JournalFolder> folders = journalFolderPersistence.findByG_P(
			fromFolder.getGroupId(), fromFolder.getFolderId());

		for (JournalFolder folder : folders) {
			mergeFolders(folder, toFolderId);
		}

		List<JournalArticle> articles = journalArticlePersistence.findByG_F(
			fromFolder.getGroupId(), fromFolder.getFolderId());

		for (JournalArticle article : articles) {
			article.setFolderId(toFolderId);
			article.setTreePath(article.buildTreePath());

			journalArticlePersistence.update(article);

			Indexer<JournalArticle> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(JournalArticle.class);

			indexer.reindex(article);
		}

		journalFolderLocalService.deleteFolder(fromFolder);
	}

	protected void moveDependentsToTrash(
			List<Object> foldersAndArticles, long trashEntryId)
		throws PortalException {

		for (Object object : foldersAndArticles) {
			if (object instanceof JournalArticle) {

				// Article

				JournalArticle article = (JournalArticle)object;

				if (article.getStatus() == WorkflowConstants.STATUS_IN_TRASH) {
					continue;
				}

				// Articles

				List<JournalArticle> articles =
					journalArticlePersistence.findByG_A(
						article.getGroupId(), article.getArticleId());

				for (JournalArticle curArticle : articles) {

					// Article

					int curArticleOldStatus = curArticle.getStatus();

					curArticle.setStatus(WorkflowConstants.STATUS_IN_TRASH);

					journalArticlePersistence.update(curArticle);

					// Trash

					int status = curArticleOldStatus;

					if (curArticleOldStatus ==
							WorkflowConstants.STATUS_PENDING) {

						status = WorkflowConstants.STATUS_DRAFT;
					}

					if (curArticleOldStatus !=
							WorkflowConstants.STATUS_APPROVED) {

						trashVersionLocalService.addTrashVersion(
							trashEntryId, JournalArticle.class.getName(),
							curArticle.getId(), status, null);
					}

					// Workflow

					if (curArticleOldStatus ==
							WorkflowConstants.STATUS_PENDING) {

						workflowInstanceLinkLocalService.
							deleteWorkflowInstanceLink(
								curArticle.getCompanyId(),
								curArticle.getGroupId(),
								JournalArticle.class.getName(),
								curArticle.getId());
					}
				}

				// Asset

				assetEntryLocalService.updateVisible(
					JournalArticle.class.getName(),
					article.getResourcePrimKey(), false);

				// Indexer

				Indexer<JournalArticle> indexer =
					IndexerRegistryUtil.nullSafeGetIndexer(
						JournalArticle.class);

				indexer.reindex(article);
			}
			else if (object instanceof JournalFolder) {

				// Folder

				JournalFolder folder = (JournalFolder)object;

				if (folder.isInTrashExplicitly()) {
					continue;
				}

				int oldStatus = folder.getStatus();

				folder.setStatus(WorkflowConstants.STATUS_IN_TRASH);

				journalFolderPersistence.update(folder);

				// Trash

				if (oldStatus != WorkflowConstants.STATUS_APPROVED) {
					trashVersionLocalService.addTrashVersion(
						trashEntryId, JournalFolder.class.getName(),
						folder.getFolderId(), oldStatus, null);
				}

				// Folders and articles

				List<Object> curFoldersAndArticles = getFoldersAndArticles(
					folder.getGroupId(), folder.getFolderId());

				moveDependentsToTrash(curFoldersAndArticles, trashEntryId);

				// Asset

				assetEntryLocalService.updateVisible(
					JournalFolder.class.getName(), folder.getFolderId(), false);

				// Indexer

				Indexer<JournalFolder> indexer =
					IndexerRegistryUtil.nullSafeGetIndexer(JournalFolder.class);

				indexer.reindex(folder);
			}
		}
	}

	protected void restoreDependentsFromTrash(List<Object> foldersAndArticles)
		throws PortalException {

		for (Object object : foldersAndArticles) {
			if (object instanceof JournalArticle) {

				// Article

				JournalArticle article = (JournalArticle)object;

				if (!article.isInTrashImplicitly()) {
					continue;
				}

				TrashVersion trashVersion =
					trashVersionLocalService.fetchVersion(
						JournalArticle.class.getName(), article.getId());

				int oldStatus = WorkflowConstants.STATUS_APPROVED;

				if (trashVersion != null) {
					oldStatus = trashVersion.getStatus();
				}

				// Articles

				List<JournalArticle> articles =
					journalArticlePersistence.findByG_A(
						article.getGroupId(), article.getArticleId());

				for (JournalArticle curArticle : articles) {

					// Article

					trashVersion = trashVersionLocalService.fetchVersion(
						JournalArticle.class.getName(), curArticle.getId());

					int curArticleOldStatus = WorkflowConstants.STATUS_APPROVED;

					if (trashVersion != null) {
						curArticleOldStatus = trashVersion.getStatus();
					}

					curArticle.setStatus(curArticleOldStatus);

					journalArticlePersistence.update(curArticle);

					// Trash

					if (trashVersion != null) {
						trashVersionLocalService.deleteTrashVersion(
							trashVersion);
					}
				}

				// Asset

				if (oldStatus == WorkflowConstants.STATUS_APPROVED) {
					assetEntryLocalService.updateVisible(
						JournalArticle.class.getName(),
						article.getResourcePrimKey(), true);
				}

				// Indexer

				Indexer<JournalArticle> indexer =
					IndexerRegistryUtil.nullSafeGetIndexer(
						JournalArticle.class);

				indexer.reindex(article);
			}
			else if (object instanceof JournalFolder) {

				// Folder

				JournalFolder folder = (JournalFolder)object;

				if (!folder.isInTrashImplicitly()) {
					continue;
				}

				TrashVersion trashVersion =
					trashVersionLocalService.fetchVersion(
						JournalFolder.class.getName(), folder.getFolderId());

				int oldStatus = WorkflowConstants.STATUS_APPROVED;

				if (trashVersion != null) {
					oldStatus = trashVersion.getStatus();
				}

				folder.setStatus(oldStatus);

				journalFolderPersistence.update(folder);

				// Folders and articles

				List<Object> curFoldersAndArticles = getFoldersAndArticles(
					folder.getGroupId(), folder.getFolderId(),
					WorkflowConstants.STATUS_IN_TRASH);

				restoreDependentsFromTrash(curFoldersAndArticles);

				// Trash

				if (trashVersion != null) {
					trashVersionLocalService.deleteTrashVersion(trashVersion);
				}

				// Asset

				assetEntryLocalService.updateVisible(
					JournalFolder.class.getName(), folder.getFolderId(), true);

				// Indexer

				Indexer<JournalFolder> indexer =
					IndexerRegistryUtil.nullSafeGetIndexer(JournalFolder.class);

				indexer.reindex(folder);
			}
		}
	}

	protected void validateArticleDDMStructures(
			long folderId, long[] ddmStructureIds)
		throws PortalException {

		if (ArrayUtil.isEmpty(ddmStructureIds)) {
			return;
		}

		JournalFolder folder = journalFolderPersistence.findByPrimaryKey(
			folderId);

		List<JournalArticle> articles = journalArticleLocalService.getArticles(
			folder.getGroupId(), folderId);

		if (!articles.isEmpty()) {
			long classNameId = classNameLocalService.getClassNameId(
				JournalArticle.class);

			for (JournalArticle article : articles) {
				DDMStructure ddmStructure =
					ddmStructureLocalService.fetchStructure(
						article.getGroupId(), classNameId,
						article.getDDMStructureKey(), true);

				if (ddmStructure == null) {
					StringBundler sb = new StringBundler(7);

					sb.append("No DDM structure exists for group ");
					sb.append(article.getGroupId());
					sb.append(", class name ");
					sb.append(classNameId);
					sb.append(", and structure key ");
					sb.append(article.getDDMStructureKey());
					sb.append(" that includes ancestor structures");

					throw new InvalidDDMStructureException(sb.toString());
				}

				if (!ArrayUtil.contains(
						ddmStructureIds, ddmStructure.getStructureId())) {

					throw new InvalidDDMStructureException(
						"Invalid DDM structure " +
							ddmStructure.getStructureId());
				}
			}
		}

		List<JournalFolder> folders = journalFolderPersistence.findByG_P(
			folder.getGroupId(), folder.getFolderId());

		if (folders.isEmpty()) {
			return;
		}

		for (JournalFolder curFolder : folders) {
			validateArticleDDMStructures(
				curFolder.getFolderId(), ddmStructureIds);
		}
	}

	protected void validateFolder(
			long folderId, long groupId, long parentFolderId, String name)
		throws PortalException {

		journalValidator.validateFolderName(name);

		JournalFolder folder = journalFolderPersistence.fetchByG_P_N(
			groupId, parentFolderId, name);

		if ((folder != null) && (folder.getFolderId() != folderId)) {
			throw new DuplicateFolderNameException(name);
		}
	}

	@ServiceReference(type = DDMStructureLinkLocalService.class)
	protected DDMStructureLinkLocalService ddmStructureLinkLocalService;

	@ServiceReference(type = DDMStructureLocalService.class)
	protected DDMStructureLocalService ddmStructureLocalService;

	@ServiceReference(type = JournalValidator.class)
	protected JournalValidator journalValidator;

}