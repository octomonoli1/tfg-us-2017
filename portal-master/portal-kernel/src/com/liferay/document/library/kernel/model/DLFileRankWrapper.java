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

package com.liferay.document.library.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link DLFileRank}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileRank
 * @generated
 */
@ProviderType
public class DLFileRankWrapper implements DLFileRank, ModelWrapper<DLFileRank> {
	public DLFileRankWrapper(DLFileRank dlFileRank) {
		_dlFileRank = dlFileRank;
	}

	@Override
	public Class<?> getModelClass() {
		return DLFileRank.class;
	}

	@Override
	public String getModelClassName() {
		return DLFileRank.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fileRankId", getFileRankId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("active", getActive());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long fileRankId = (Long)attributes.get("fileRankId");

		if (fileRankId != null) {
			setFileRankId(fileRankId);
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

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	@Override
	public DLFileRank toEscapedModel() {
		return new DLFileRankWrapper(_dlFileRank.toEscapedModel());
	}

	@Override
	public DLFileRank toUnescapedModel() {
		return new DLFileRankWrapper(_dlFileRank.toUnescapedModel());
	}

	/**
	* Returns the active of this document library file rank.
	*
	* @return the active of this document library file rank
	*/
	@Override
	public boolean getActive() {
		return _dlFileRank.getActive();
	}

	/**
	* Returns <code>true</code> if this document library file rank is active.
	*
	* @return <code>true</code> if this document library file rank is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _dlFileRank.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _dlFileRank.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dlFileRank.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dlFileRank.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dlFileRank.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DLFileRank> toCacheModel() {
		return _dlFileRank.toCacheModel();
	}

	@Override
	public int compareTo(DLFileRank dlFileRank) {
		return _dlFileRank.compareTo(dlFileRank);
	}

	@Override
	public int hashCode() {
		return _dlFileRank.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dlFileRank.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DLFileRankWrapper((DLFileRank)_dlFileRank.clone());
	}

	/**
	* Returns the user uuid of this document library file rank.
	*
	* @return the user uuid of this document library file rank
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _dlFileRank.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _dlFileRank.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dlFileRank.toXmlString();
	}

	/**
	* Returns the create date of this document library file rank.
	*
	* @return the create date of this document library file rank
	*/
	@Override
	public Date getCreateDate() {
		return _dlFileRank.getCreateDate();
	}

	/**
	* Returns the company ID of this document library file rank.
	*
	* @return the company ID of this document library file rank
	*/
	@Override
	public long getCompanyId() {
		return _dlFileRank.getCompanyId();
	}

	/**
	* Returns the file entry ID of this document library file rank.
	*
	* @return the file entry ID of this document library file rank
	*/
	@Override
	public long getFileEntryId() {
		return _dlFileRank.getFileEntryId();
	}

	/**
	* Returns the file rank ID of this document library file rank.
	*
	* @return the file rank ID of this document library file rank
	*/
	@Override
	public long getFileRankId() {
		return _dlFileRank.getFileRankId();
	}

	/**
	* Returns the group ID of this document library file rank.
	*
	* @return the group ID of this document library file rank
	*/
	@Override
	public long getGroupId() {
		return _dlFileRank.getGroupId();
	}

	/**
	* Returns the primary key of this document library file rank.
	*
	* @return the primary key of this document library file rank
	*/
	@Override
	public long getPrimaryKey() {
		return _dlFileRank.getPrimaryKey();
	}

	/**
	* Returns the user ID of this document library file rank.
	*
	* @return the user ID of this document library file rank
	*/
	@Override
	public long getUserId() {
		return _dlFileRank.getUserId();
	}

	@Override
	public void persist() {
		_dlFileRank.persist();
	}

	/**
	* Sets whether this document library file rank is active.
	*
	* @param active the active of this document library file rank
	*/
	@Override
	public void setActive(boolean active) {
		_dlFileRank.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dlFileRank.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this document library file rank.
	*
	* @param companyId the company ID of this document library file rank
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dlFileRank.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this document library file rank.
	*
	* @param createDate the create date of this document library file rank
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dlFileRank.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dlFileRank.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dlFileRank.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dlFileRank.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file entry ID of this document library file rank.
	*
	* @param fileEntryId the file entry ID of this document library file rank
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_dlFileRank.setFileEntryId(fileEntryId);
	}

	/**
	* Sets the file rank ID of this document library file rank.
	*
	* @param fileRankId the file rank ID of this document library file rank
	*/
	@Override
	public void setFileRankId(long fileRankId) {
		_dlFileRank.setFileRankId(fileRankId);
	}

	/**
	* Sets the group ID of this document library file rank.
	*
	* @param groupId the group ID of this document library file rank
	*/
	@Override
	public void setGroupId(long groupId) {
		_dlFileRank.setGroupId(groupId);
	}

	@Override
	public void setNew(boolean n) {
		_dlFileRank.setNew(n);
	}

	/**
	* Sets the primary key of this document library file rank.
	*
	* @param primaryKey the primary key of this document library file rank
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dlFileRank.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dlFileRank.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this document library file rank.
	*
	* @param userId the user ID of this document library file rank
	*/
	@Override
	public void setUserId(long userId) {
		_dlFileRank.setUserId(userId);
	}

	/**
	* Sets the user uuid of this document library file rank.
	*
	* @param userUuid the user uuid of this document library file rank
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_dlFileRank.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DLFileRankWrapper)) {
			return false;
		}

		DLFileRankWrapper dlFileRankWrapper = (DLFileRankWrapper)obj;

		if (Objects.equals(_dlFileRank, dlFileRankWrapper._dlFileRank)) {
			return true;
		}

		return false;
	}

	@Override
	public DLFileRank getWrappedModel() {
		return _dlFileRank;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dlFileRank.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dlFileRank.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dlFileRank.resetOriginalValues();
	}

	private final DLFileRank _dlFileRank;
}