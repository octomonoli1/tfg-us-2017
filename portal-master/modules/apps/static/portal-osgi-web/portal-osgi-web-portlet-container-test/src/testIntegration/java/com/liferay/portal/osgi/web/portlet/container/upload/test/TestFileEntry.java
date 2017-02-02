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

package com.liferay.portal.osgi.web.portlet.container.upload.test;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.lock.Lock;
import com.liferay.portal.kernel.repository.capabilities.Capability;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.repository.model.RepositoryModelOperation;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.InputStream;
import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Manuel de la Peña
 */
public class TestFileEntry implements FileEntry {

	public TestFileEntry(
		String fileName, long folderId, long groupId, InputStream inputStream) {

		_date = new Date();
		_fileName = fileName;
		_folderId = folderId;
		_groupId = groupId;
		_inputStream = inputStream;
	}

	@Override
	public Object clone() {
		return null;
	}

	@Override
	public boolean containsPermission(
		PermissionChecker permissionChecker, String actionId) {

		return true;
	}

	@Override
	public void execute(RepositoryModelOperation repositoryModelOperation) {
	}

	@Override
	public Map<String, Serializable> getAttributes() {
		return Collections.emptyMap();
	}

	@Override
	public long getCompanyId() {
		return 0;
	}

	@Override
	public InputStream getContentStream() {
		return _inputStream;
	}

	@Override
	public InputStream getContentStream(String version) {
		return _inputStream;
	}

	@Override
	public Date getCreateDate() {
		return _date;
	}

	@Override
	public String getDescription() {
		return RandomTestUtil.randomString();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return null;
	}

	@Override
	public String getExtension() {
		return RandomTestUtil.randomString(3);
	}

	@Override
	public long getFileEntryId() {
		return RandomTestUtil.randomLong();
	}

	@Override
	public String getFileName() {
		return _fileName;
	}

	@Override
	public List<FileShortcut> getFileShortcuts() {
		return Collections.emptyList();
	}

	@Override
	public FileVersion getFileVersion() {
		return null;
	}

	@Override
	public FileVersion getFileVersion(String version) {
		return null;
	}

	@Override
	public List<FileVersion> getFileVersions(int status) {
		return Collections.emptyList();
	}

	@Override
	public int getFileVersionsCount(int status) {
		return 0;
	}

	@Override
	public Folder getFolder() {
		try {
			return DLAppLocalServiceUtil.getFolder(_folderId);
		}
		catch (PortalException pe) {
			return null;
		}
	}

	@Override
	public long getFolderId() {
		return _folderId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public String getIcon() {
		return RandomTestUtil.randomString();
	}

	@Override
	public String getIconCssClass() {
		return RandomTestUtil.randomString();
	}

	public InputStream getInputStream() {
		return _inputStream;
	}

	@Override
	public Date getLastPublishDate() {
		return _date;
	}

	@Override
	public FileVersion getLatestFileVersion() {
		return null;
	}

	@Override
	public FileVersion getLatestFileVersion(boolean trusted) {
		return null;
	}

	@Override
	public Lock getLock() {
		return null;
	}

	@Override
	public String getMimeType() {
		return RandomTestUtil.randomString();
	}

	@Override
	public String getMimeType(String version) {
		return RandomTestUtil.randomString();
	}

	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public Class<?> getModelClass() {
		return this.getClass();
	}

	@Override
	public String getModelClassName() {
		return getModelClass().getName();
	}

	@Override
	public Date getModifiedDate() {
		return _date;
	}

	@Override
	public long getPrimaryKey() {
		return 0;
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return null;
	}

	@Override
	public int getReadCount() {
		return 0;
	}

	@Override
	public <T extends Capability> T getRepositoryCapability(
		Class<T> capabilityClass) {

		return null;
	}

	@Override
	public long getRepositoryId() {
		return DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	}

	@Override
	public long getSize() {
		return 0;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return null;
	}

	@Override
	public String getTitle() {
		return RandomTestUtil.randomString();
	}

	@Override
	public long getUserId() {
		return 0;
	}

	@Override
	public String getUserName() {
		return RandomTestUtil.randomString();
	}

	@Override
	public String getUserUuid() {
		return RandomTestUtil.randomString();
	}

	@Override
	public String getUuid() {
		return RandomTestUtil.randomString();
	}

	@Override
	public String getVersion() {
		return RandomTestUtil.randomString();
	}

	@Override
	public long getVersionUserId() {
		return 0;
	}

	@Override
	public String getVersionUserName() {
		return RandomTestUtil.randomString();
	}

	@Override
	public String getVersionUserUuid() {
		return RandomTestUtil.randomString();
	}

	@Override
	public boolean hasLock() {
		return false;
	}

	@Override
	public boolean isCheckedOut() {
		return false;
	}

	@Override
	public boolean isDefaultRepository() {
		return false;
	}

	@Override
	public boolean isEscapedModel() {
		return false;
	}

	@Override
	public boolean isInTrash() {
		return false;
	}

	@Override
	public boolean isInTrashContainer() {
		return false;
	}

	@Override
	public boolean isManualCheckInRequired() {
		return false;
	}

	@Override
	public <T extends Capability> boolean isRepositoryCapabilityProvided(
		Class<T> capabilityClass) {

		return false;
	}

	@Override
	public boolean isSupportsLocking() {
		return false;
	}

	@Override
	public boolean isSupportsMetadata() {
		return false;
	}

	@Override
	public boolean isSupportsSocial() {
		return false;
	}

	@Override
	public void setCompanyId(long companyId) {
	}

	@Override
	public void setCreateDate(Date date) {
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public void setLastPublishDate(Date date) {
	}

	@Override
	public void setModifiedDate(Date date) {
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
	}

	@Override
	public void setUserId(long userId) {
	}

	@Override
	public void setUserName(String userName) {
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public void setUuid(String uuid) {
	}

	@Override
	public FileEntry toEscapedModel() {
		return this;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append(_groupId);
		sb.append("_");
		sb.append(_folderId);
		sb.append("_");
		sb.append(_fileName);

		return sb.toString();
	}

	@Override
	public FileEntry toUnescapedModel() {
		return this;
	}

	private final Date _date;
	private final String _fileName;
	private final long _folderId;
	private long _groupId;
	private final InputStream _inputStream;

}