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

package com.liferay.document.library.repository.cmis.internal.model;

import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.document.library.kernel.exception.NoSuchFileVersionException;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.service.DLAppHelperLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.document.library.repository.cmis.internal.CMISRepository;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lock.Lock;
import com.liferay.portal.kernel.lock.LockManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.RepositoryEntry;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.RepositoryProviderUtil;
import com.liferay.portal.kernel.repository.capabilities.Capability;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.repository.model.RepositoryModelOperation;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RepositoryEntryLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.commons.data.AllowableActions;
import org.apache.chemistry.opencmis.commons.data.CmisExtensionElement;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.Action;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;

/**
 * @author Alexander Chow
 */
public class CMISFileEntry extends CMISModel implements FileEntry {

	public CMISFileEntry(
		CMISRepository cmisRepository, String uuid, long fileEntryId,
		Document document, LockManager lockManager) {

		_cmisRepository = cmisRepository;
		_uuid = uuid;
		_fileEntryId = fileEntryId;
		_document = document;
		_lockManager = lockManager;
	}

	@Override
	public Object clone() {
		CMISFileEntry cmisFileEntry = new CMISFileEntry(
			_cmisRepository, _uuid, _fileEntryId, _document, _lockManager);

		cmisFileEntry.setCompanyId(getCompanyId());
		cmisFileEntry.setFileEntryId(getFileEntryId());
		cmisFileEntry.setGroupId(getGroupId());

		try {
			cmisFileEntry.setParentFolder(getParentFolder());
		}
		catch (Exception e) {
		}

		cmisFileEntry.setPrimaryKey(getPrimaryKey());

		return cmisFileEntry;
	}

	@Override
	public boolean containsPermission(
		PermissionChecker permissionChecker, String actionId) {

		return containsPermission(_document, actionId);
	}

	@Override
	public void execute(RepositoryModelOperation repositoryModelOperation)
		throws PortalException {

		repositoryModelOperation.execute(this);
	}

	@Override
	public Map<String, Serializable> getAttributes() {
		return new HashMap<>();
	}

	@Override
	public long getCompanyId() {
		return _cmisRepository.getCompanyId();
	}

	@Override
	public InputStream getContentStream() {
		ContentStream contentStream = _document.getContentStream();

		try {
			DLAppHelperLocalServiceUtil.getFileAsStream(
				PrincipalThreadLocal.getUserId(), this, true);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return contentStream.getStream();
	}

	@Override
	public InputStream getContentStream(String version) throws PortalException {
		if (Validator.isNull(version)) {
			return getContentStream();
		}

		for (Document document : getAllVersions()) {
			if (version.equals(document.getVersionLabel())) {
				ContentStream contentStream = document.getContentStream();

				try {
					DLAppHelperLocalServiceUtil.getFileAsStream(
						PrincipalThreadLocal.getUserId(), this, true);
				}
				catch (Exception e) {
					_log.error(e, e);
				}

				return contentStream.getStream();
			}
		}

		throw new NoSuchFileVersionException(
			"No CMIS file version with {fileEntryId=" + getFileEntryId() +
				", version=" + version + "}");
	}

	@Override
	public Date getCreateDate() {
		return _document.getCreationDate().getTime();
	}

	@Override
	public String getExtension() {
		return FileUtil.getExtension(getTitle());
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public String getFileName() {
		return DLUtil.getSanitizedFileName(getTitle(), getExtension());
	}

	@Override
	public List<FileShortcut> getFileShortcuts() {
		return Collections.emptyList();
	}

	@Override
	public FileVersion getFileVersion() throws PortalException {
		return getLatestFileVersion();
	}

	@Override
	public FileVersion getFileVersion(String version) throws PortalException {
		if (Validator.isNull(version)) {
			return getFileVersion();
		}

		for (Document document : getAllVersions()) {
			if (version.equals(document.getVersionLabel())) {
				return _cmisRepository.toFileVersion(document);
			}
		}

		throw new NoSuchFileVersionException(
			"No CMIS file version with {fileEntryId=" + getFileEntryId() +
				", version=" + version + "}");
	}

	@Override
	public List<FileVersion> getFileVersions(int status) {
		try {
			List<Document> documents = getAllVersions();

			List<FileVersion> fileVersions = new ArrayList<>(documents.size());

			for (Document document : documents) {
				FileVersion fileVersion = _cmisRepository.toFileVersion(
					document);

				fileVersions.add(fileVersion);
			}

			return fileVersions;
		}
		catch (PortalException pe) {
			throw new RepositoryException(pe);
		}
	}

	@Override
	public int getFileVersionsCount(int status) {
		try {
			List<Document> documents = getAllVersions();

			return documents.size();
		}
		catch (PortalException pe) {
			throw new RepositoryException(pe);
		}
	}

	@Override
	public Folder getFolder() {
		Folder parentFolder = null;

		try {
			parentFolder = super.getParentFolder();

			if (parentFolder != null) {
				return parentFolder;
			}
		}
		catch (Exception e) {
		}

		try {
			List<org.apache.chemistry.opencmis.client.api.Folder>
				cmisParentFolders = _document.getParents();

			if (cmisParentFolders.isEmpty()) {
				_document = _document.getObjectOfLatestVersion(false);

				cmisParentFolders = _document.getParents();
			}

			parentFolder = _cmisRepository.toFolder(cmisParentFolders.get(0));

			setParentFolder(parentFolder);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return parentFolder;
	}

	@Override
	public long getFolderId() {
		Folder folder = getFolder();

		return folder.getFolderId();
	}

	@Override
	public long getGroupId() {
		return _cmisRepository.getGroupId();
	}

	@Override
	public String getIcon() {
		return DLUtil.getFileIcon(getExtension());
	}

	@Override
	public String getIconCssClass() {
		return DLUtil.getFileIconCssClass(getExtension());
	}

	@Override
	public Date getLastPublishDate() {
		return null;
	}

	@Override
	public FileVersion getLatestFileVersion() throws PortalException {
		if (_latestFileVersion != null) {
			return _latestFileVersion;
		}

		List<Document> documents = getAllVersions();

		if (!documents.isEmpty()) {
			Document latestDocumentVersion = documents.get(0);

			_latestFileVersion = _cmisRepository.toFileVersion(
				latestDocumentVersion);
		}
		else {
			_latestFileVersion = _cmisRepository.toFileVersion(_document);
		}

		return _latestFileVersion;
	}

	@Override
	public FileVersion getLatestFileVersion(boolean trusted)
		throws PortalException {

		return getLatestFileVersion();
	}

	@Override
	public Lock getLock() {
		if (!isCheckedOut()) {
			return null;
		}

		String checkedOutBy = _document.getVersionSeriesCheckedOutBy();

		User user = getUser(checkedOutBy);

		long userId = 0;
		String userName = null;

		if (user != null) {
			userId = user.getUserId();
			userName = user.getFullName();
		}

		return _lockManager.createLock(0, getCompanyId(), userId, userName);
	}

	@Override
	public String getMimeType() {
		String mimeType = _document.getContentStreamMimeType();

		if (Validator.isNotNull(mimeType)) {
			return mimeType;
		}

		return MimeTypesUtil.getContentType(getTitle());
	}

	@Override
	public String getMimeType(String version) {
		if (Validator.isNull(version)) {
			return getMimeType();
		}

		try {
			for (Document document : getAllVersions()) {
				if (!version.equals(document.getVersionLabel())) {
					continue;
				}

				String mimeType = document.getContentStreamMimeType();

				if (Validator.isNotNull(mimeType)) {
					return mimeType;
				}

				return MimeTypesUtil.getContentType(document.getName());
			}
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		return ContentTypes.APPLICATION_OCTET_STREAM;
	}

	@Override
	public Object getModel() {
		return _document;
	}

	@Override
	public Class<?> getModelClass() {
		return CMISFileEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CMISFileEntry.class.getName();
	}

	@Override
	public Date getModifiedDate() {
		return _document.getLastModificationDate().getTime();
	}

	@Override
	public long getPrimaryKey() {
		return _fileEntryId;
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return getPrimaryKey();
	}

	@Override
	public int getReadCount() {
		return 0;
	}

	@Override
	public <T extends Capability> T getRepositoryCapability(
		Class<T> capabilityClass) {

		try {
			Repository repository = RepositoryProviderUtil.getRepository(
				getRepositoryId());

			return repository.getCapability(capabilityClass);
		}
		catch (PortalException pe) {
			throw new SystemException(
				"Unable to access repository " + getRepositoryId(), pe);
		}
	}

	@Override
	public long getRepositoryId() {
		return _cmisRepository.getRepositoryId();
	}

	@Override
	public long getSize() {
		return _document.getContentStreamLength();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(DLFileEntryConstants.getClassName());
	}

	@Override
	public String getTitle() {
		return _document.getName();
	}

	@Override
	public long getUserId() {
		User user = getUser(_document.getCreatedBy());

		if (user == null) {
			return 0;
		}
		else {
			return user.getUserId();
		}
	}

	@Override
	public String getUserName() {
		User user = getUser(_document.getCreatedBy());

		if (user == null) {
			return StringPool.BLANK;
		}
		else {
			return user.getFullName();
		}
	}

	@Override
	public String getUserUuid() {
		User user = getUser(_document.getCreatedBy());

		try {
			return user.getUserUuid();
		}
		catch (Exception e) {
		}

		return StringPool.BLANK;
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public String getVersion() {
		return GetterUtil.getString(_document.getVersionLabel(), null);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link CMISFileVersion#getUserId()}
	 */
	@Deprecated
	@Override
	public long getVersionUserId() {
		long versionUserId = 0;

		try {
			FileVersion fileVersion = getFileVersion();

			versionUserId = fileVersion.getUserId();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return versionUserId;
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             CMISFileVersion#getUserName()}
	 */
	@Deprecated
	@Override
	public String getVersionUserName() {
		String versionUserName = StringPool.BLANK;

		try {
			FileVersion fileVersion = getFileVersion();

			versionUserName = fileVersion.getUserName();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return versionUserName;
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             CMISFileVersion#getUserUuid()}
	 */
	@Deprecated
	@Override
	public String getVersionUserUuid() {
		String versionUserUuid = StringPool.BLANK;

		try {
			FileVersion fileVersion = getFileVersion();

			versionUserUuid = fileVersion.getUserUuid();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return versionUserUuid;
	}

	@Override
	public boolean hasLock() {
		if (!isCheckedOut()) {
			return false;
		}

		AllowableActions allowableActions = _document.getAllowableActions();

		Set<Action> allowableActionsSet =
			allowableActions.getAllowableActions();

		if (allowableActionsSet.contains(Action.CAN_CHECK_IN)) {
			return true;
		}

		List<CmisExtensionElement> cmisExtensionElements =
			allowableActions.getExtensions();

		if (cmisExtensionElements == null) {
			return false;
		}

		for (CmisExtensionElement cmisExtensionElement :
				cmisExtensionElements) {

			String name = cmisExtensionElement.getName();

			if ((name != null) && name.equals("canCheckInSpecified")) {
				return GetterUtil.getBoolean(cmisExtensionElement.getValue());
			}
		}

		return false;
	}

	@Override
	public boolean isCheckedOut() {
		return _document.isVersionSeriesCheckedOut();
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
		try {
			RepositoryEntry repositoryEntry =
				RepositoryEntryLocalServiceUtil.getRepositoryEntry(
					_fileEntryId);

			return repositoryEntry.isManualCheckInRequired();
		}
		catch (Exception e) {
			if (_log.isInfoEnabled()) {
				_log.info("Unable to retrieve repository entry", e);
			}

			return false;
		}
	}

	@Override
	public <T extends Capability> boolean isRepositoryCapabilityProvided(
		Class<T> capabilityClass) {

		return false;
	}

	@Override
	public boolean isSupportsLocking() {
		return true;
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
		_cmisRepository.setCompanyId(companyId);
	}

	@Override
	public void setCreateDate(Date createDate) {
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	@Override
	public void setGroupId(long groupId) {
		_cmisRepository.setGroupId(groupId);
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
	}

	public void setPrimaryKey(long primaryKey) {
		setFileEntryId(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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
	public FileEntry toUnescapedModel() {
		return this;
	}

	protected List<Document> getAllVersions() throws PortalException {
		if (_allVersions == null) {
			try {
				_document.refresh();

				_allVersions = _document.getAllVersions();
			}
			catch (CmisObjectNotFoundException confe) {
				throw new NoSuchFileEntryException(confe);
			}
		}

		return _allVersions;
	}

	@Override
	protected CMISRepository getCmisRepository() {
		return _cmisRepository;
	}

	private static final Log _log = LogFactoryUtil.getLog(CMISFileEntry.class);

	private List<Document> _allVersions;
	private final CMISRepository _cmisRepository;
	private Document _document;
	private long _fileEntryId;
	private FileVersion _latestFileVersion;
	private final LockManager _lockManager;
	private final String _uuid;

}