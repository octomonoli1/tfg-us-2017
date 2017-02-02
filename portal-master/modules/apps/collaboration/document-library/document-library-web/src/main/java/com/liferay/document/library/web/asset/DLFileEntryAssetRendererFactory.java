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

package com.liferay.document.library.web.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.asset.kernel.model.ClassTypeReader;
import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileEntryTypeConstants;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService;
import com.liferay.document.library.web.constants.DLPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portlet.documentlibrary.asset.DLFileEntryClassTypeReader;
import com.liferay.portlet.documentlibrary.service.permission.DLFileEntryPermission;
import com.liferay.portlet.documentlibrary.service.permission.DLFileEntryTypePermission;
import com.liferay.portlet.documentlibrary.service.permission.DLPermission;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 * @author Juan Fernández
 * @author Raymond Augé
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY},
	service = AssetRendererFactory.class
)
public class DLFileEntryAssetRendererFactory
	extends BaseAssetRendererFactory<FileEntry> {

	public static final String TYPE = "document";

	public DLFileEntryAssetRendererFactory() {
		setLinkable(true);
		setPortletId(DLPortletKeys.DOCUMENT_LIBRARY);
		setSearchable(true);
		setSupportsClassTypes(true);
	}

	@Override
	public AssetRenderer<FileEntry> getAssetRenderer(long classPK, int type)
		throws PortalException {

		FileEntry fileEntry = null;
		FileVersion fileVersion = null;

		try {
			fileEntry = _dlAppLocalService.getFileEntry(classPK);

			if (type == TYPE_LATEST) {
				fileVersion = fileEntry.getLatestFileVersion();
			}
			else if (type == TYPE_LATEST_APPROVED) {
				fileVersion = fileEntry.getFileVersion();
			}
			else {
				throw new IllegalArgumentException(
					"Unknown asset renderer type " + type);
			}
		}
		catch (NoSuchFileEntryException nsfee) {
			fileVersion = _dlAppLocalService.getFileVersion(classPK);

			fileEntry = fileVersion.getFileEntry();
		}

		DLFileEntryAssetRenderer dlFileEntryAssetRenderer =
			new DLFileEntryAssetRenderer(fileEntry, fileVersion);

		dlFileEntryAssetRenderer.setAssetRendererType(type);

		return dlFileEntryAssetRenderer;
	}

	@Override
	public String getClassName() {
		return DLFileEntry.class.getName();
	}

	@Override
	public ClassTypeReader getClassTypeReader() {
		return new DLFileEntryClassTypeReader();
	}

	@Override
	public String getIconCssClass() {
		return "documents-and-media";
	}

	@Override
	public String getSubtypeTitle(Locale locale) {
		return LanguageUtil.get(locale, "type");
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String getTypeName(Locale locale, long subtypeId) {
		try {
			DLFileEntryType dlFileEntryType =
				_dlFileEntryTypeLocalService.getFileEntryType(subtypeId);

			return dlFileEntryType.getName(locale);
		}
		catch (Exception e) {
			return super.getTypeName(locale, subtypeId);
		}
	}

	@Override
	public PortletURL getURLAdd(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, long classTypeId) {

		PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
			liferayPortletRequest, getGroup(liferayPortletRequest),
			DLPortletKeys.DOCUMENT_LIBRARY, 0, 0, PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "/document_library/edit_file_entry");
		portletURL.setParameter(Constants.CMD, Constants.ADD);
		portletURL.setParameter(
			"folderId",
			String.valueOf(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID));

		long fileEntryTypeId =
			DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT;

		if (classTypeId >= 0) {
			fileEntryTypeId = classTypeId;
		}

		portletURL.setParameter(
			"fileEntryTypeId", String.valueOf(fileEntryTypeId));

		portletURL.setParameter("showMountFolder", Boolean.FALSE.toString());
		portletURL.setParameter("showSelectFolder", Boolean.TRUE.toString());

		return portletURL;
	}

	@Override
	public PortletURL getURLView(
		LiferayPortletResponse liferayPortletResponse,
		WindowState windowState) {

		LiferayPortletURL liferayPortletURL =
			liferayPortletResponse.createLiferayPortletURL(
				DLPortletKeys.DOCUMENT_LIBRARY, PortletRequest.RENDER_PHASE);

		try {
			liferayPortletURL.setWindowState(windowState);
		}
		catch (WindowStateException wse) {
		}

		return liferayPortletURL;
	}

	@Override
	public boolean hasAddPermission(
			PermissionChecker permissionChecker, long groupId, long classTypeId)
		throws Exception {

		if ((classTypeId > 0) &&
			!DLFileEntryTypePermission.contains(
				permissionChecker, classTypeId, ActionKeys.VIEW)) {

			return false;
		}

		return DLPermission.contains(
			permissionChecker, groupId, ActionKeys.ADD_DOCUMENT);
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return DLFileEntryPermission.contains(
			permissionChecker, classPK, actionId);
	}

	@Reference(unbind = "-")
	protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {
		_dlAppLocalService = dlAppLocalService;
	}

	@Reference(unbind = "-")
	protected void setDLFileEntryTypeLocalService(
		DLFileEntryTypeLocalService dlFileEntryTypeLocalService) {

		_dlFileEntryTypeLocalService = dlFileEntryTypeLocalService;
	}

	private DLAppLocalService _dlAppLocalService;
	private DLFileEntryTypeLocalService _dlFileEntryTypeLocalService;

}