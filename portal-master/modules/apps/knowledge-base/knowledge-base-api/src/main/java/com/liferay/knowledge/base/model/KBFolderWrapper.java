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

package com.liferay.knowledge.base.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link KBFolder}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBFolder
 * @generated
 */
@ProviderType
public class KBFolderWrapper implements KBFolder, ModelWrapper<KBFolder> {
	public KBFolderWrapper(KBFolder kbFolder) {
		_kbFolder = kbFolder;
	}

	@Override
	public Class<?> getModelClass() {
		return KBFolder.class;
	}

	@Override
	public String getModelClassName() {
		return KBFolder.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("kbFolderId", getKbFolderId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentKBFolderId", getParentKBFolderId());
		attributes.put("name", getName());
		attributes.put("urlTitle", getUrlTitle());
		attributes.put("description", getDescription());
		attributes.put("lastPublishDate", getLastPublishDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long kbFolderId = (Long)attributes.get("kbFolderId");

		if (kbFolderId != null) {
			setKbFolderId(kbFolderId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long parentKBFolderId = (Long)attributes.get("parentKBFolderId");

		if (parentKBFolderId != null) {
			setParentKBFolderId(parentKBFolderId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String urlTitle = (String)attributes.get("urlTitle");

		if (urlTitle != null) {
			setUrlTitle(urlTitle);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	@Override
	public KBFolder toEscapedModel() {
		return new KBFolderWrapper(_kbFolder.toEscapedModel());
	}

	@Override
	public KBFolder toUnescapedModel() {
		return new KBFolderWrapper(_kbFolder.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _kbFolder.isCachedModel();
	}

	@Override
	public boolean isEmpty()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbFolder.isEmpty();
	}

	@Override
	public boolean isEscapedModel() {
		return _kbFolder.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _kbFolder.isNew();
	}

	@Override
	public boolean isRoot() {
		return _kbFolder.isRoot();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _kbFolder.getExpandoBridge();
	}

	@Override
	public KBFolder getParentKBFolder()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbFolder.getParentKBFolder();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<KBFolder> toCacheModel() {
		return _kbFolder.toCacheModel();
	}

	@Override
	public int compareTo(KBFolder kbFolder) {
		return _kbFolder.compareTo(kbFolder);
	}

	@Override
	public int hashCode() {
		return _kbFolder.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kbFolder.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new KBFolderWrapper((KBFolder)_kbFolder.clone());
	}

	/**
	* Returns the description of this k b folder.
	*
	* @return the description of this k b folder
	*/
	@Override
	public java.lang.String getDescription() {
		return _kbFolder.getDescription();
	}

	/**
	* Returns the name of this k b folder.
	*
	* @return the name of this k b folder
	*/
	@Override
	public java.lang.String getName() {
		return _kbFolder.getName();
	}

	@Override
	public java.lang.String getParentTitle(java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbFolder.getParentTitle(locale);
	}

	/**
	* Returns the url title of this k b folder.
	*
	* @return the url title of this k b folder
	*/
	@Override
	public java.lang.String getUrlTitle() {
		return _kbFolder.getUrlTitle();
	}

	/**
	* Returns the user name of this k b folder.
	*
	* @return the user name of this k b folder
	*/
	@Override
	public java.lang.String getUserName() {
		return _kbFolder.getUserName();
	}

	/**
	* Returns the user uuid of this k b folder.
	*
	* @return the user uuid of this k b folder
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _kbFolder.getUserUuid();
	}

	/**
	* Returns the uuid of this k b folder.
	*
	* @return the uuid of this k b folder
	*/
	@Override
	public java.lang.String getUuid() {
		return _kbFolder.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _kbFolder.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kbFolder.toXmlString();
	}

	/**
	* Returns the create date of this k b folder.
	*
	* @return the create date of this k b folder
	*/
	@Override
	public Date getCreateDate() {
		return _kbFolder.getCreateDate();
	}

	/**
	* Returns the last publish date of this k b folder.
	*
	* @return the last publish date of this k b folder
	*/
	@Override
	public Date getLastPublishDate() {
		return _kbFolder.getLastPublishDate();
	}

	/**
	* Returns the modified date of this k b folder.
	*
	* @return the modified date of this k b folder
	*/
	@Override
	public Date getModifiedDate() {
		return _kbFolder.getModifiedDate();
	}

	@Override
	public java.util.List<java.lang.Long> getAncestorKBFolderIds()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbFolder.getAncestorKBFolderIds();
	}

	@Override
	public long getClassNameId() {
		return _kbFolder.getClassNameId();
	}

	/**
	* Returns the company ID of this k b folder.
	*
	* @return the company ID of this k b folder
	*/
	@Override
	public long getCompanyId() {
		return _kbFolder.getCompanyId();
	}

	/**
	* Returns the group ID of this k b folder.
	*
	* @return the group ID of this k b folder
	*/
	@Override
	public long getGroupId() {
		return _kbFolder.getGroupId();
	}

	/**
	* Returns the kb folder ID of this k b folder.
	*
	* @return the kb folder ID of this k b folder
	*/
	@Override
	public long getKbFolderId() {
		return _kbFolder.getKbFolderId();
	}

	/**
	* Returns the parent k b folder ID of this k b folder.
	*
	* @return the parent k b folder ID of this k b folder
	*/
	@Override
	public long getParentKBFolderId() {
		return _kbFolder.getParentKBFolderId();
	}

	/**
	* Returns the primary key of this k b folder.
	*
	* @return the primary key of this k b folder
	*/
	@Override
	public long getPrimaryKey() {
		return _kbFolder.getPrimaryKey();
	}

	/**
	* Returns the user ID of this k b folder.
	*
	* @return the user ID of this k b folder
	*/
	@Override
	public long getUserId() {
		return _kbFolder.getUserId();
	}

	@Override
	public void persist() {
		_kbFolder.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kbFolder.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this k b folder.
	*
	* @param companyId the company ID of this k b folder
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kbFolder.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this k b folder.
	*
	* @param createDate the create date of this k b folder
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_kbFolder.setCreateDate(createDate);
	}

	/**
	* Sets the description of this k b folder.
	*
	* @param description the description of this k b folder
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_kbFolder.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_kbFolder.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_kbFolder.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_kbFolder.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this k b folder.
	*
	* @param groupId the group ID of this k b folder
	*/
	@Override
	public void setGroupId(long groupId) {
		_kbFolder.setGroupId(groupId);
	}

	/**
	* Sets the kb folder ID of this k b folder.
	*
	* @param kbFolderId the kb folder ID of this k b folder
	*/
	@Override
	public void setKbFolderId(long kbFolderId) {
		_kbFolder.setKbFolderId(kbFolderId);
	}

	/**
	* Sets the last publish date of this k b folder.
	*
	* @param lastPublishDate the last publish date of this k b folder
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_kbFolder.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this k b folder.
	*
	* @param modifiedDate the modified date of this k b folder
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_kbFolder.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this k b folder.
	*
	* @param name the name of this k b folder
	*/
	@Override
	public void setName(java.lang.String name) {
		_kbFolder.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_kbFolder.setNew(n);
	}

	/**
	* Sets the parent k b folder ID of this k b folder.
	*
	* @param parentKBFolderId the parent k b folder ID of this k b folder
	*/
	@Override
	public void setParentKBFolderId(long parentKBFolderId) {
		_kbFolder.setParentKBFolderId(parentKBFolderId);
	}

	/**
	* Sets the primary key of this k b folder.
	*
	* @param primaryKey the primary key of this k b folder
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kbFolder.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_kbFolder.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the url title of this k b folder.
	*
	* @param urlTitle the url title of this k b folder
	*/
	@Override
	public void setUrlTitle(java.lang.String urlTitle) {
		_kbFolder.setUrlTitle(urlTitle);
	}

	/**
	* Sets the user ID of this k b folder.
	*
	* @param userId the user ID of this k b folder
	*/
	@Override
	public void setUserId(long userId) {
		_kbFolder.setUserId(userId);
	}

	/**
	* Sets the user name of this k b folder.
	*
	* @param userName the user name of this k b folder
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kbFolder.setUserName(userName);
	}

	/**
	* Sets the user uuid of this k b folder.
	*
	* @param userUuid the user uuid of this k b folder
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kbFolder.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this k b folder.
	*
	* @param uuid the uuid of this k b folder
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_kbFolder.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KBFolderWrapper)) {
			return false;
		}

		KBFolderWrapper kbFolderWrapper = (KBFolderWrapper)obj;

		if (Objects.equals(_kbFolder, kbFolderWrapper._kbFolder)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _kbFolder.getStagedModelType();
	}

	@Override
	public KBFolder getWrappedModel() {
		return _kbFolder;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kbFolder.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kbFolder.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kbFolder.resetOriginalValues();
	}

	private final KBFolder _kbFolder;
}