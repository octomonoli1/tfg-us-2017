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

package com.liferay.document.library.web.internal.display.context;

import com.liferay.document.library.display.context.DLEditFileEntryDisplayContext;
import com.liferay.document.library.display.context.DLFilePicker;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.document.library.web.internal.display.context.logic.FileEntryDisplayContextHelper;
import com.liferay.document.library.web.internal.display.context.logic.FileVersionDisplayContextHelper;
import com.liferay.document.library.web.internal.display.context.util.DLRequestHelper;
import com.liferay.document.library.web.internal.settings.DLPortletInstanceSettings;
import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iván Zaera
 */
public class DefaultDLEditFileEntryDisplayContext
	implements DLEditFileEntryDisplayContext {

	public DefaultDLEditFileEntryDisplayContext(
		HttpServletRequest request, HttpServletResponse response,
		DLFileEntryType dlFileEntryType, StorageEngine storageEngine) {

		this(request, dlFileEntryType, null, storageEngine);
	}

	public DefaultDLEditFileEntryDisplayContext(
		HttpServletRequest request, HttpServletResponse response,
		FileEntry fileEntry, StorageEngine storageEngine) {

		this(request, (DLFileEntryType)null, fileEntry, storageEngine);
	}

	@Override
	public DDMFormValues getDDMFormValues(long classPK)
		throws StorageException {

		return _storageEngine.getDDMFormValues(classPK);
	}

	@Override
	public DLFilePicker getDLFilePicker(String onFilePickCallback) {
		return null;
	}

	@Override
	public long getMaximumUploadRequestSize() {
		return PrefsPropsUtil.getLong(
			PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE);
	}

	@Override
	public long getMaximumUploadSize() {
		long fileMaxSize = PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE);

		if (fileMaxSize == 0) {
			fileMaxSize = getMaximumUploadRequestSize();
		}

		return fileMaxSize;
	}

	@Override
	public String getPublishButtonLabel() {
		if (_hasFolderWorkflowDefinitionLink()) {
			return "submit-for-publication";
		}

		DLPortletInstanceSettings dlPortletInstanceSettings =
			_dlRequestHelper.getDLPortletInstanceSettings();

		if (dlPortletInstanceSettings.isEnableFileEntryDrafts() ||
			_fileEntryDisplayContextHelper.isCheckedOut()) {

			return "save";
		}

		return "publish";
	}

	@Override
	public String getSaveButtonLabel() {
		String saveButtonLabel = "save";

		if ((_fileVersion == null) ||
			_fileVersionDisplayContextHelper.isApproved() ||
			_fileVersionDisplayContextHelper.isDraft()) {

			saveButtonLabel = "save-as-draft";
		}

		return saveButtonLabel;
	}

	@Override
	public UUID getUuid() {
		return _UUID;
	}

	@Override
	public boolean isCancelCheckoutDocumentButtonDisabled() {
		return false;
	}

	@Override
	public boolean isCancelCheckoutDocumentButtonVisible()
		throws PortalException {

		return _fileEntryDisplayContextHelper.
			isCancelCheckoutDocumentActionAvailable();
	}

	@Override
	public boolean isCheckinButtonDisabled() {
		return false;
	}

	@Override
	public boolean isCheckinButtonVisible() throws PortalException {
		return _fileEntryDisplayContextHelper.isCheckinActionAvailable();
	}

	@Override
	public boolean isCheckoutDocumentButtonDisabled() {
		return false;
	}

	@Override
	public boolean isCheckoutDocumentButtonVisible() throws PortalException {
		return
			_fileEntryDisplayContextHelper.isCheckoutDocumentActionAvailable();
	}

	@Override
	public boolean isDDMStructureVisible(DDMStructure ddmStructure) {
		return true;
	}

	@Override
	public boolean isFolderSelectionVisible() {
		return _showSelectFolder;
	}

	@Override
	public boolean isPublishButtonDisabled() {
		DLPortletInstanceSettings dlPortletInstanceSettings =
			_dlRequestHelper.getDLPortletInstanceSettings();

		if (_fileEntryDisplayContextHelper.isCheckedOutByOther() ||
			(_fileVersionDisplayContextHelper.isPending() &&
			 dlPortletInstanceSettings.isEnableFileEntryDrafts())) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isPublishButtonVisible() {
		return true;
	}

	@Override
	public boolean isSaveButtonDisabled() {
		if (_fileEntryDisplayContextHelper.isCheckedOutByOther()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isSaveButtonVisible() {
		DLPortletInstanceSettings dlPortletInstanceSettings =
			_dlRequestHelper.getDLPortletInstanceSettings();

		return dlPortletInstanceSettings.isEnableFileEntryDrafts();
	}

	@Override
	public boolean isVersionInfoVisible() {
		return true;
	}

	private DefaultDLEditFileEntryDisplayContext(
		HttpServletRequest request, DLFileEntryType dlFileEntryType,
		FileEntry fileEntry, StorageEngine storageEngine) {

		try {
			_dlRequestHelper = new DLRequestHelper(request);
			_fileEntry = fileEntry;
			_storageEngine = storageEngine;

			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			_fileEntryDisplayContextHelper = new FileEntryDisplayContextHelper(
				themeDisplay.getPermissionChecker(), _fileEntry);

			if ((dlFileEntryType == null) && (fileEntry != null)) {
				_dlFileEntryType =
					_fileEntryDisplayContextHelper.getDLFileEntryType();
			}
			else {
				_dlFileEntryType = dlFileEntryType;
			}

			if (fileEntry != null) {
				_fileVersion = fileEntry.getFileVersion();
			}
			else {
				_fileVersion = null;
			}

			_fileVersionDisplayContextHelper =
				new FileVersionDisplayContextHelper(_fileVersion);

			_showSelectFolder = ParamUtil.getBoolean(
				request, "showSelectFolder");
		}
		catch (PortalException pe) {
			throw new SystemException(
				"Unable to build DefaultDLEditFileEntryDisplayContext for " +
					fileEntry,
				pe);
		}
	}

	private boolean _hasFolderWorkflowDefinitionLink() {
		try {
			if (_dlFileEntryType == null) {
				return false;
			}

			long folderId = BeanParamUtil.getLong(
				_fileEntry, _dlRequestHelper.getRequest(), "folderId");

			return DLUtil.hasWorkflowDefinitionLink(
				_dlRequestHelper.getCompanyId(),
				_dlRequestHelper.getScopeGroupId(), folderId,
				_dlFileEntryType.getFileEntryTypeId());
		}
		catch (Exception e) {
			throw new SystemException(
				"Unable to check if folder has workflow definition link", e);
		}
	}

	private static final UUID _UUID = UUID.fromString(
		"63326141-02F6-42B5-AE38-ABC73FA72BB5");

	private final DLFileEntryType _dlFileEntryType;
	private final DLRequestHelper _dlRequestHelper;
	private final FileEntry _fileEntry;
	private final FileEntryDisplayContextHelper _fileEntryDisplayContextHelper;
	private final FileVersion _fileVersion;
	private final FileVersionDisplayContextHelper
		_fileVersionDisplayContextHelper;
	private final boolean _showSelectFolder;
	private final StorageEngine _storageEngine;

}