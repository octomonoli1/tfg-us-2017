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

package com.liferay.portlet.exportimport.service.impl;

import com.liferay.exportimport.kernel.lar.MissingReferences;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portlet.exportimport.service.base.StagingServiceBaseImpl;

import java.util.Map;

/**
 * @author Michael C. Han
 */
public class StagingServiceImpl extends StagingServiceBaseImpl {

	@Override
	public void cleanUpStagingRequest(long stagingRequestId)
		throws PortalException {

		checkPermission(stagingRequestId);

		stagingLocalService.cleanUpStagingRequest(stagingRequestId);
	}

	@Override
	public long createStagingRequest(long groupId, String checksum)
		throws PortalException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.EXPORT_IMPORT_LAYOUTS);

		return stagingLocalService.createStagingRequest(
			getUserId(), groupId, checksum);
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public MissingReferences publishStagingRequest(
			long stagingRequestId, boolean privateLayout,
			Map<String, String[]> parameterMap)
		throws PortalException {

		checkPermission(stagingRequestId);

		return stagingLocalService.publishStagingRequest(
			getUserId(), stagingRequestId, privateLayout, parameterMap);
	}

	@Override
	public MissingReferences publishStagingRequest(
			long stagingRequestId,
			ExportImportConfiguration exportImportConfiguration)
		throws PortalException {

		checkPermission(stagingRequestId);

		return stagingLocalService.publishStagingRequest(
			getUserId(), stagingRequestId, exportImportConfiguration);
	}

	@Override
	public void updateStagingRequest(
			long stagingRequestId, String fileName, byte[] bytes)
		throws PortalException {

		checkPermission(stagingRequestId);

		stagingLocalService.updateStagingRequest(
			getUserId(), stagingRequestId, fileName, bytes);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #publishStagingRequest(long,
	 *             boolean, Map)}
	 */
	@Deprecated
	@Override
	public MissingReferences validateStagingRequest(
			long stagingRequestId, boolean privateLayout,
			Map<String, String[]> parameterMap)
		throws PortalException {

		checkPermission(stagingRequestId);

		return stagingLocalService.validateStagingRequest(
			getUserId(), stagingRequestId, privateLayout, parameterMap);
	}

	protected void checkPermission(long stagingRequestId)
		throws PortalException {

		Folder folder = PortletFileRepositoryUtil.getPortletFolder(
			stagingRequestId);

		GroupPermissionUtil.check(
			getPermissionChecker(), folder.getGroupId(),
			ActionKeys.EXPORT_IMPORT_LAYOUTS);
	}

}