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

package com.liferay.social.networking.model;

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
 * This class is a wrapper for {@link WallEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WallEntry
 * @generated
 */
@ProviderType
public class WallEntryWrapper implements WallEntry, ModelWrapper<WallEntry> {
	public WallEntryWrapper(WallEntry wallEntry) {
		_wallEntry = wallEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return WallEntry.class;
	}

	@Override
	public String getModelClassName() {
		return WallEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("wallEntryId", getWallEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("comments", getComments());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long wallEntryId = (Long)attributes.get("wallEntryId");

		if (wallEntryId != null) {
			setWallEntryId(wallEntryId);
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

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}
	}

	@Override
	public WallEntry toEscapedModel() {
		return new WallEntryWrapper(_wallEntry.toEscapedModel());
	}

	@Override
	public WallEntry toUnescapedModel() {
		return new WallEntryWrapper(_wallEntry.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _wallEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _wallEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _wallEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _wallEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WallEntry> toCacheModel() {
		return _wallEntry.toCacheModel();
	}

	@Override
	public int compareTo(WallEntry wallEntry) {
		return _wallEntry.compareTo(wallEntry);
	}

	@Override
	public int hashCode() {
		return _wallEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _wallEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new WallEntryWrapper((WallEntry)_wallEntry.clone());
	}

	/**
	* Returns the comments of this wall entry.
	*
	* @return the comments of this wall entry
	*/
	@Override
	public java.lang.String getComments() {
		return _wallEntry.getComments();
	}

	/**
	* Returns the user name of this wall entry.
	*
	* @return the user name of this wall entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _wallEntry.getUserName();
	}

	/**
	* Returns the user uuid of this wall entry.
	*
	* @return the user uuid of this wall entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _wallEntry.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _wallEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _wallEntry.toXmlString();
	}

	/**
	* Returns the create date of this wall entry.
	*
	* @return the create date of this wall entry
	*/
	@Override
	public Date getCreateDate() {
		return _wallEntry.getCreateDate();
	}

	/**
	* Returns the modified date of this wall entry.
	*
	* @return the modified date of this wall entry
	*/
	@Override
	public Date getModifiedDate() {
		return _wallEntry.getModifiedDate();
	}

	/**
	* Returns the company ID of this wall entry.
	*
	* @return the company ID of this wall entry
	*/
	@Override
	public long getCompanyId() {
		return _wallEntry.getCompanyId();
	}

	/**
	* Returns the group ID of this wall entry.
	*
	* @return the group ID of this wall entry
	*/
	@Override
	public long getGroupId() {
		return _wallEntry.getGroupId();
	}

	/**
	* Returns the primary key of this wall entry.
	*
	* @return the primary key of this wall entry
	*/
	@Override
	public long getPrimaryKey() {
		return _wallEntry.getPrimaryKey();
	}

	/**
	* Returns the user ID of this wall entry.
	*
	* @return the user ID of this wall entry
	*/
	@Override
	public long getUserId() {
		return _wallEntry.getUserId();
	}

	/**
	* Returns the wall entry ID of this wall entry.
	*
	* @return the wall entry ID of this wall entry
	*/
	@Override
	public long getWallEntryId() {
		return _wallEntry.getWallEntryId();
	}

	@Override
	public void persist() {
		_wallEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_wallEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the comments of this wall entry.
	*
	* @param comments the comments of this wall entry
	*/
	@Override
	public void setComments(java.lang.String comments) {
		_wallEntry.setComments(comments);
	}

	/**
	* Sets the company ID of this wall entry.
	*
	* @param companyId the company ID of this wall entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_wallEntry.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this wall entry.
	*
	* @param createDate the create date of this wall entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_wallEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_wallEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_wallEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_wallEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this wall entry.
	*
	* @param groupId the group ID of this wall entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_wallEntry.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this wall entry.
	*
	* @param modifiedDate the modified date of this wall entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_wallEntry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_wallEntry.setNew(n);
	}

	/**
	* Sets the primary key of this wall entry.
	*
	* @param primaryKey the primary key of this wall entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_wallEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_wallEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this wall entry.
	*
	* @param userId the user ID of this wall entry
	*/
	@Override
	public void setUserId(long userId) {
		_wallEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this wall entry.
	*
	* @param userName the user name of this wall entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_wallEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this wall entry.
	*
	* @param userUuid the user uuid of this wall entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_wallEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the wall entry ID of this wall entry.
	*
	* @param wallEntryId the wall entry ID of this wall entry
	*/
	@Override
	public void setWallEntryId(long wallEntryId) {
		_wallEntry.setWallEntryId(wallEntryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WallEntryWrapper)) {
			return false;
		}

		WallEntryWrapper wallEntryWrapper = (WallEntryWrapper)obj;

		if (Objects.equals(_wallEntry, wallEntryWrapper._wallEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public WallEntry getWrappedModel() {
		return _wallEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _wallEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _wallEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_wallEntry.resetOriginalValues();
	}

	private final WallEntry _wallEntry;
}