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

package com.liferay.portal.kernel.repository.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.lock.Lock;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.repository.capabilities.Capability;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Kyle Stiemann
 */
public class FileEntryWrapper implements FileEntry, ModelWrapper<FileEntry> {

	public FileEntryWrapper(FileEntry fileEntry) {
		_fileEntry = fileEntry;
	}

	@Override
	public Object clone() {
		return new FileEntryWrapper((FileEntry)_fileEntry.clone());
	}

	@Override
	public boolean containsPermission(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		return _fileEntry.containsPermission(permissionChecker, actionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FileEntryWrapper)) {
			return false;
		}

		FileEntryWrapper fileEntryWrapper = (FileEntryWrapper)obj;

		if (Objects.equals(_fileEntry, fileEntryWrapper._fileEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public void execute(RepositoryModelOperation repositoryModelOperation)
		throws PortalException {

		repositoryModelOperation.execute(this);
	}

	@Override
	public Map<String, Serializable> getAttributes() {
		return _fileEntry.getAttributes();
	}

	@Override
	public long getCompanyId() {
		return _fileEntry.getCompanyId();
	}

	@Override
	public InputStream getContentStream() throws PortalException {
		return _fileEntry.getContentStream();
	}

	@Override
	public InputStream getContentStream(String version) throws PortalException {
		return _fileEntry.getContentStream();
	}

	@Override
	public Date getCreateDate() {
		return _fileEntry.getCreateDate();
	}

	@Override
	public String getDescription() {
		return _fileEntry.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _fileEntry.getExpandoBridge();
	}

	@Override
	public String getExtension() {
		return _fileEntry.getExtension();
	}

	@Override
	public long getFileEntryId() {
		return _fileEntry.getFileEntryId();
	}

	@Override
	public String getFileName() {
		return _fileEntry.getFileName();
	}

	@Override
	public List<FileShortcut> getFileShortcuts() {
		return _fileEntry.getFileShortcuts();
	}

	@Override
	public FileVersion getFileVersion() throws PortalException {
		return _fileEntry.getFileVersion();
	}

	@Override
	public FileVersion getFileVersion(String version) throws PortalException {
		return _fileEntry.getFileVersion();
	}

	@Override
	public List<FileVersion> getFileVersions(int status) {
		return _fileEntry.getFileVersions(status);
	}

	@Override
	public int getFileVersionsCount(int status) {
		return _fileEntry.getFileVersionsCount(status);
	}

	@Override
	public Folder getFolder() {
		return _fileEntry.getFolder();
	}

	@Override
	public long getFolderId() {
		return _fileEntry.getFolderId();
	}

	@Override
	public long getGroupId() {
		return _fileEntry.getGroupId();
	}

	@Override
	public String getIcon() {
		return _fileEntry.getIcon();
	}

	@Override
	public String getIconCssClass() {
		return _fileEntry.getIconCssClass();
	}

	@Override
	public Date getLastPublishDate() {
		return _fileEntry.getLastPublishDate();
	}

	@Override
	public FileVersion getLatestFileVersion() throws PortalException {
		return _fileEntry.getLatestFileVersion();
	}

	@Override
	public FileVersion getLatestFileVersion(boolean trusted)
		throws PortalException {

		return _fileEntry.getLatestFileVersion(trusted);
	}

	@Override
	public Lock getLock() {
		return _fileEntry.getLock();
	}

	@Override
	public String getMimeType() {
		return _fileEntry.getMimeType();
	}

	@Override
	public String getMimeType(String version) {
		return _fileEntry.getMimeType(version);
	}

	@Override
	public Object getModel() {
		return _fileEntry.getModel();
	}

	@Override
	public Class<?> getModelClass() {
		return FileEntry.class;
	}

	@Override
	public String getModelClassName() {
		return FileEntry.class.getName();
	}

	@Override
	public Date getModifiedDate() {
		return _fileEntry.getModifiedDate();
	}

	@Override
	public long getPrimaryKey() {
		return _fileEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fileEntry.getPrimaryKeyObj();
	}

	@Override
	public int getReadCount() {
		return _fileEntry.getReadCount();
	}

	@Override
	public <T extends Capability> T getRepositoryCapability(
		Class<T> capabilityClass) {

		return _fileEntry.getRepositoryCapability(capabilityClass);
	}

	@Override
	public long getRepositoryId() {
		return _fileEntry.getRepositoryId();
	}

	@Override
	public long getSize() {
		return _fileEntry.getSize();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _fileEntry.getStagedModelType();
	}

	@Override
	public String getTitle() {
		return _fileEntry.getTitle();
	}

	@Override
	public long getUserId() {
		return _fileEntry.getUserId();
	}

	@Override
	public String getUserName() {
		return _fileEntry.getUserName();
	}

	@Override
	public String getUserUuid() {
		return _fileEntry.getUserUuid();
	}

	@Override
	public String getUuid() {
		return _fileEntry.getUuid();
	}

	@Override
	public String getVersion() {
		return _fileEntry.getVersion();
	}

	@Override
	public long getVersionUserId() {
		return _fileEntry.getVersionUserId();
	}

	@Override
	public String getVersionUserName() {
		return _fileEntry.getVersionUserName();
	}

	@Override
	public String getVersionUserUuid() {
		return _fileEntry.getVersionUserUuid();
	}

	@Override
	public FileEntry getWrappedModel() {
		return _fileEntry;
	}

	@Override
	public int hashCode() {
		return _fileEntry.hashCode();
	}

	@Override
	public boolean hasLock() {
		return _fileEntry.hasLock();
	}

	@Override
	public boolean isCheckedOut() {
		return _fileEntry.isCheckedOut();
	}

	@Override
	public boolean isDefaultRepository() {
		return _fileEntry.isDefaultRepository();
	}

	@Override
	public boolean isEscapedModel() {
		return _fileEntry.isEscapedModel();
	}

	@Override
	public boolean isInTrash() {
		return _fileEntry.isInTrash();
	}

	@Override
	public boolean isInTrashContainer() {
		return _fileEntry.isInTrashContainer();
	}

	@Override
	public boolean isManualCheckInRequired() {
		return _fileEntry.isManualCheckInRequired();
	}

	@Override
	public <T extends Capability> boolean isRepositoryCapabilityProvided(
		Class<T> capabilityClass) {

		return _fileEntry.isRepositoryCapabilityProvided(capabilityClass);
	}

	@Override
	public boolean isSupportsLocking() {
		return _fileEntry.isSupportsLocking();
	}

	@Override
	public boolean isSupportsMetadata() {
		return _fileEntry.isSupportsMetadata();
	}

	@Override
	public boolean isSupportsSocial() {
		return _fileEntry.isSupportsSocial();
	}

	@Override
	public void setCompanyId(long companyId) {
		_fileEntry.setCompanyId(companyId);
	}

	@Override
	public void setCreateDate(Date createDate) {
		_fileEntry.setCreateDate(createDate);
	}

	@Override
	public void setGroupId(long groupId) {
		_fileEntry.setGroupId(groupId);
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_fileEntry.setLastPublishDate(lastPublishDate);
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_fileEntry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_fileEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public void setUserId(long userId) {
		_fileEntry.setUserId(userId);
	}

	@Override
	public void setUserName(String userName) {
		_fileEntry.setUserName(userName);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_fileEntry.setUserUuid(userUuid);
	}

	@Override
	public void setUuid(String uuid) {
		_fileEntry.setUuid(uuid);
	}

	@Override
	public FileEntry toEscapedModel() {
		return new FileEntryWrapper(_fileEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _fileEntry.toString();
	}

	@Override
	public FileEntry toUnescapedModel() {
		return new FileEntryWrapper(_fileEntry.toUnescapedModel());
	}

	private final FileEntry _fileEntry;

}