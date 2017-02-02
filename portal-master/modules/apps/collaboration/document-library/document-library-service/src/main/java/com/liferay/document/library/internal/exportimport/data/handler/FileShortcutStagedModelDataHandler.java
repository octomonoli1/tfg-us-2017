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

package com.liferay.document.library.internal.exportimport.data.handler;

import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.document.library.kernel.model.DLFileShortcutConstants;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileShortcutLocalService;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.exportimport.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileShortcut;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mate Thurzo
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class FileShortcutStagedModelDataHandler
	extends BaseStagedModelDataHandler<FileShortcut> {

	public static final String[] CLASS_NAMES = {
		DLFileShortcutConstants.getClassName(), FileShortcut.class.getName(),
		LiferayFileShortcut.class.getName()
	};

	@Override
	public void deleteStagedModel(FileShortcut fileShortcut)
		throws PortalException {

		_dlFileShortcutLocalService.deleteFileShortcut(
			fileShortcut.getFileShortcutId());
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		FileShortcut fileShortcut = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (fileShortcut != null) {
			deleteStagedModel(fileShortcut);
		}
	}

	@Override
	public FileShortcut fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		try {
			DLFileShortcut dlFileShortcut =
				_dlFileShortcutLocalService.getDLFileShortcutByUuidAndGroupId(
					uuid, groupId);

			return new LiferayFileShortcut(dlFileShortcut);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			return null;
		}
	}

	@Override
	public List<FileShortcut> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		List<DLFileShortcut> dlFileShortcuts =
			_dlFileShortcutLocalService.getDLFileShortcutsByUuidAndCompanyId(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new StagedModelModifiedDateComparator<DLFileShortcut>());

		List<FileShortcut> fileShortcuts = new ArrayList<>();

		for (DLFileShortcut dlFileShortcut : dlFileShortcuts) {
			fileShortcuts.add(new LiferayFileShortcut(dlFileShortcut));
		}

		return fileShortcuts;
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(FileShortcut fileShortcut) {
		return fileShortcut.getUuid();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, FileShortcut fileShortcut)
		throws Exception {

		if (fileShortcut.getFolderId() !=
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, fileShortcut, fileShortcut.getFolder(),
				PortletDataContext.REFERENCE_TYPE_PARENT);
		}

		FileEntry fileEntry = _dlAppLocalService.getFileEntry(
			fileShortcut.getToFileEntryId());

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, fileShortcut, fileEntry,
			PortletDataContext.REFERENCE_TYPE_STRONG);

		Element fileShortcutElement = portletDataContext.getExportDataElement(
			fileShortcut);

		portletDataContext.addClassedModel(
			fileShortcutElement,
			ExportImportPathUtil.getModelPath(fileShortcut), fileShortcut);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, FileShortcut fileShortcut)
		throws Exception {

		long userId = portletDataContext.getUserId(fileShortcut.getUserUuid());

		Map<Long, Long> folderIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Folder.class);

		long folderId = MapUtil.getLong(
			folderIds, fileShortcut.getFolderId(), fileShortcut.getFolderId());

		long groupId = portletDataContext.getScopeGroupId();

		if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			Folder folder = FolderUtil.findByPrimaryKey(folderId);

			groupId = folder.getRepositoryId();
		}

		Map<Long, Long> fileEntryIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				FileEntry.class);

		long fileEntryId = MapUtil.getLong(
			fileEntryIds, fileShortcut.getToFileEntryId(),
			fileShortcut.getToFileEntryId());

		FileEntry importedFileEntry = null;

		try {
			importedFileEntry = _dlAppLocalService.getFileEntry(fileEntryId);
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to fetch file entry " + fileEntryId);
			}

			return;
		}

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			fileShortcut);

		FileShortcut importedFileShortcut = null;

		if (portletDataContext.isDataStrategyMirror()) {
			FileShortcut existingFileShortcut =
				fetchStagedModelByUuidAndGroupId(
					fileShortcut.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingFileShortcut == null) {
				serviceContext.setUuid(fileShortcut.getUuid());

				importedFileShortcut = _dlAppLocalService.addFileShortcut(
					userId, groupId, folderId,
					importedFileEntry.getFileEntryId(), serviceContext);
			}
			else {
				importedFileShortcut = _dlAppLocalService.updateFileShortcut(
					userId, existingFileShortcut.getFileShortcutId(), folderId,
					importedFileEntry.getFileEntryId(), serviceContext);
			}
		}
		else {
			importedFileShortcut = _dlAppLocalService.addFileShortcut(
				userId, groupId, folderId, importedFileEntry.getFileEntryId(),
				serviceContext);
		}

		portletDataContext.importClassedModel(
			fileShortcut, importedFileShortcut);
	}

	@Override
	protected void doRestoreStagedModel(
			PortletDataContext portletDataContext, FileShortcut fileShortcut)
		throws Exception {

		long userId = portletDataContext.getUserId(fileShortcut.getUserUuid());

		FileShortcut existingFileShortcut = fetchStagedModelByUuidAndGroupId(
			fileShortcut.getUuid(), portletDataContext.getScopeGroupId());

		if ((existingFileShortcut == null) ||
			!(existingFileShortcut.getModel() instanceof DLFileShortcut)) {

			return;
		}

		DLFileShortcut dlFileShortcut =
			(DLFileShortcut)existingFileShortcut.getModel();

		if (!dlFileShortcut.isInTrash()) {
			return;
		}

		TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(
			DLFileShortcut.class.getName());

		if (trashHandler.isRestorable(
				existingFileShortcut.getFileShortcutId())) {

			trashHandler.restoreTrashEntry(
				userId, existingFileShortcut.getFileShortcutId());
		}
	}

	@Reference(unbind = "-")
	protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {
		_dlAppLocalService = dlAppLocalService;
	}

	@Reference(unbind = "-")
	protected void setDLFileShortcutLocalService(
		DLFileShortcutLocalService dlFileShortcutLocalService) {

		_dlFileShortcutLocalService = dlFileShortcutLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FileShortcutStagedModelDataHandler.class);

	private DLAppLocalService _dlAppLocalService;
	private DLFileShortcutLocalService _dlFileShortcutLocalService;

}