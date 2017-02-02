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

package com.liferay.document.library.internal.service.permission;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.portal.kernel.security.permission.PermissionUpdateHandler;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gergely Mathe
 */
@Component(
	property = {"model.class.name=com.liferay.document.library.kernel.model.DLFolder"},
	service = PermissionUpdateHandler.class
)
public class DLFolderPermissionUpdateHandler
	implements PermissionUpdateHandler {

	@Override
	public void updatedPermission(String primKey) {
		DLFolder dlFolder = _dLFolderLocalService.fetchDLFolder(
			GetterUtil.getLong(primKey));

		if (dlFolder == null) {
			return;
		}

		dlFolder.setModifiedDate(new Date());

		_dLFolderLocalService.updateDLFolder(dlFolder);
	}

	@Reference(unbind = "-")
	protected void setDLFolderLocalService(
		DLFolderLocalService dLFolderLocalService) {

		_dLFolderLocalService = dLFolderLocalService;
	}

	private DLFolderLocalService _dLFolderLocalService;

}