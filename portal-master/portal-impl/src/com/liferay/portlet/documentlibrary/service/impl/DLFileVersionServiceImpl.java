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

package com.liferay.portlet.documentlibrary.service.impl;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portlet.documentlibrary.service.base.DLFileVersionServiceBaseImpl;
import com.liferay.portlet.documentlibrary.service.permission.DLFileEntryPermission;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class DLFileVersionServiceImpl extends DLFileVersionServiceBaseImpl {

	@Override
	public DLFileVersion getFileVersion(long fileVersionId)
		throws PortalException {

		DLFileVersion fileVersion = dlFileVersionLocalService.getFileVersion(
			fileVersionId);

		DLFileEntryPermission.check(
			getPermissionChecker(), fileVersion.getFileEntryId(),
			ActionKeys.VIEW);

		return fileVersion;
	}

	@Override
	public List<DLFileVersion> getFileVersions(long fileEntryId, int status)
		throws PortalException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.VIEW);

		return dlFileVersionLocalService.getFileVersions(fileEntryId, status);
	}

	@Override
	public int getFileVersionsCount(long fileEntryId, int status)
		throws PortalException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.VIEW);

		return dlFileVersionPersistence.countByF_S(fileEntryId, status);
	}

	@Override
	public DLFileVersion getLatestFileVersion(long fileEntryId)
		throws PortalException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.VIEW);

		return dlFileVersionLocalService.getLatestFileVersion(
			getGuestOrUserId(), fileEntryId);
	}

	@Override
	public DLFileVersion getLatestFileVersion(
			long fileEntryId, boolean excludeWorkingCopy)
		throws PortalException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.VIEW);

		return dlFileVersionLocalService.getLatestFileVersion(
			fileEntryId, excludeWorkingCopy);
	}

}