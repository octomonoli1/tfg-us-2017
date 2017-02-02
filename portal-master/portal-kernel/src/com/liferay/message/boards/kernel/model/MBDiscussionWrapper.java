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

package com.liferay.message.boards.kernel.model;

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
 * This class is a wrapper for {@link MBDiscussion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MBDiscussion
 * @generated
 */
@ProviderType
public class MBDiscussionWrapper implements MBDiscussion,
	ModelWrapper<MBDiscussion> {
	public MBDiscussionWrapper(MBDiscussion mbDiscussion) {
		_mbDiscussion = mbDiscussion;
	}

	@Override
	public Class<?> getModelClass() {
		return MBDiscussion.class;
	}

	@Override
	public String getModelClassName() {
		return MBDiscussion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("discussionId", getDiscussionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("threadId", getThreadId());
		attributes.put("lastPublishDate", getLastPublishDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long discussionId = (Long)attributes.get("discussionId");

		if (discussionId != null) {
			setDiscussionId(discussionId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long threadId = (Long)attributes.get("threadId");

		if (threadId != null) {
			setThreadId(threadId);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	@Override
	public MBDiscussion toEscapedModel() {
		return new MBDiscussionWrapper(_mbDiscussion.toEscapedModel());
	}

	@Override
	public MBDiscussion toUnescapedModel() {
		return new MBDiscussionWrapper(_mbDiscussion.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _mbDiscussion.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _mbDiscussion.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _mbDiscussion.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _mbDiscussion.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MBDiscussion> toCacheModel() {
		return _mbDiscussion.toCacheModel();
	}

	@Override
	public int compareTo(MBDiscussion mbDiscussion) {
		return _mbDiscussion.compareTo(mbDiscussion);
	}

	@Override
	public int hashCode() {
		return _mbDiscussion.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mbDiscussion.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new MBDiscussionWrapper((MBDiscussion)_mbDiscussion.clone());
	}

	/**
	* Returns the fully qualified class name of this message boards discussion.
	*
	* @return the fully qualified class name of this message boards discussion
	*/
	@Override
	public java.lang.String getClassName() {
		return _mbDiscussion.getClassName();
	}

	/**
	* Returns the user name of this message boards discussion.
	*
	* @return the user name of this message boards discussion
	*/
	@Override
	public java.lang.String getUserName() {
		return _mbDiscussion.getUserName();
	}

	/**
	* Returns the user uuid of this message boards discussion.
	*
	* @return the user uuid of this message boards discussion
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _mbDiscussion.getUserUuid();
	}

	/**
	* Returns the uuid of this message boards discussion.
	*
	* @return the uuid of this message boards discussion
	*/
	@Override
	public java.lang.String getUuid() {
		return _mbDiscussion.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _mbDiscussion.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _mbDiscussion.toXmlString();
	}

	/**
	* Returns the create date of this message boards discussion.
	*
	* @return the create date of this message boards discussion
	*/
	@Override
	public Date getCreateDate() {
		return _mbDiscussion.getCreateDate();
	}

	/**
	* Returns the last publish date of this message boards discussion.
	*
	* @return the last publish date of this message boards discussion
	*/
	@Override
	public Date getLastPublishDate() {
		return _mbDiscussion.getLastPublishDate();
	}

	/**
	* Returns the modified date of this message boards discussion.
	*
	* @return the modified date of this message boards discussion
	*/
	@Override
	public Date getModifiedDate() {
		return _mbDiscussion.getModifiedDate();
	}

	/**
	* Returns the class name ID of this message boards discussion.
	*
	* @return the class name ID of this message boards discussion
	*/
	@Override
	public long getClassNameId() {
		return _mbDiscussion.getClassNameId();
	}

	/**
	* Returns the class p k of this message boards discussion.
	*
	* @return the class p k of this message boards discussion
	*/
	@Override
	public long getClassPK() {
		return _mbDiscussion.getClassPK();
	}

	/**
	* Returns the company ID of this message boards discussion.
	*
	* @return the company ID of this message boards discussion
	*/
	@Override
	public long getCompanyId() {
		return _mbDiscussion.getCompanyId();
	}

	/**
	* Returns the discussion ID of this message boards discussion.
	*
	* @return the discussion ID of this message boards discussion
	*/
	@Override
	public long getDiscussionId() {
		return _mbDiscussion.getDiscussionId();
	}

	/**
	* Returns the group ID of this message boards discussion.
	*
	* @return the group ID of this message boards discussion
	*/
	@Override
	public long getGroupId() {
		return _mbDiscussion.getGroupId();
	}

	/**
	* Returns the primary key of this message boards discussion.
	*
	* @return the primary key of this message boards discussion
	*/
	@Override
	public long getPrimaryKey() {
		return _mbDiscussion.getPrimaryKey();
	}

	/**
	* Returns the thread ID of this message boards discussion.
	*
	* @return the thread ID of this message boards discussion
	*/
	@Override
	public long getThreadId() {
		return _mbDiscussion.getThreadId();
	}

	/**
	* Returns the user ID of this message boards discussion.
	*
	* @return the user ID of this message boards discussion
	*/
	@Override
	public long getUserId() {
		return _mbDiscussion.getUserId();
	}

	@Override
	public void persist() {
		_mbDiscussion.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_mbDiscussion.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_mbDiscussion.setClassName(className);
	}

	/**
	* Sets the class name ID of this message boards discussion.
	*
	* @param classNameId the class name ID of this message boards discussion
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_mbDiscussion.setClassNameId(classNameId);
	}

	/**
	* Sets the class p k of this message boards discussion.
	*
	* @param classPK the class p k of this message boards discussion
	*/
	@Override
	public void setClassPK(long classPK) {
		_mbDiscussion.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this message boards discussion.
	*
	* @param companyId the company ID of this message boards discussion
	*/
	@Override
	public void setCompanyId(long companyId) {
		_mbDiscussion.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this message boards discussion.
	*
	* @param createDate the create date of this message boards discussion
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_mbDiscussion.setCreateDate(createDate);
	}

	/**
	* Sets the discussion ID of this message boards discussion.
	*
	* @param discussionId the discussion ID of this message boards discussion
	*/
	@Override
	public void setDiscussionId(long discussionId) {
		_mbDiscussion.setDiscussionId(discussionId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_mbDiscussion.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_mbDiscussion.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_mbDiscussion.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this message boards discussion.
	*
	* @param groupId the group ID of this message boards discussion
	*/
	@Override
	public void setGroupId(long groupId) {
		_mbDiscussion.setGroupId(groupId);
	}

	/**
	* Sets the last publish date of this message boards discussion.
	*
	* @param lastPublishDate the last publish date of this message boards discussion
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_mbDiscussion.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this message boards discussion.
	*
	* @param modifiedDate the modified date of this message boards discussion
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_mbDiscussion.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_mbDiscussion.setNew(n);
	}

	/**
	* Sets the primary key of this message boards discussion.
	*
	* @param primaryKey the primary key of this message boards discussion
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_mbDiscussion.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_mbDiscussion.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the thread ID of this message boards discussion.
	*
	* @param threadId the thread ID of this message boards discussion
	*/
	@Override
	public void setThreadId(long threadId) {
		_mbDiscussion.setThreadId(threadId);
	}

	/**
	* Sets the user ID of this message boards discussion.
	*
	* @param userId the user ID of this message boards discussion
	*/
	@Override
	public void setUserId(long userId) {
		_mbDiscussion.setUserId(userId);
	}

	/**
	* Sets the user name of this message boards discussion.
	*
	* @param userName the user name of this message boards discussion
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_mbDiscussion.setUserName(userName);
	}

	/**
	* Sets the user uuid of this message boards discussion.
	*
	* @param userUuid the user uuid of this message boards discussion
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_mbDiscussion.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this message boards discussion.
	*
	* @param uuid the uuid of this message boards discussion
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_mbDiscussion.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MBDiscussionWrapper)) {
			return false;
		}

		MBDiscussionWrapper mbDiscussionWrapper = (MBDiscussionWrapper)obj;

		if (Objects.equals(_mbDiscussion, mbDiscussionWrapper._mbDiscussion)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _mbDiscussion.getStagedModelType();
	}

	@Override
	public MBDiscussion getWrappedModel() {
		return _mbDiscussion;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _mbDiscussion.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _mbDiscussion.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_mbDiscussion.resetOriginalValues();
	}

	private final MBDiscussion _mbDiscussion;
}