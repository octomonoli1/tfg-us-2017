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
 * This class is a wrapper for {@link MeetupsRegistration}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistration
 * @generated
 */
@ProviderType
public class MeetupsRegistrationWrapper implements MeetupsRegistration,
	ModelWrapper<MeetupsRegistration> {
	public MeetupsRegistrationWrapper(MeetupsRegistration meetupsRegistration) {
		_meetupsRegistration = meetupsRegistration;
	}

	@Override
	public Class<?> getModelClass() {
		return MeetupsRegistration.class;
	}

	@Override
	public String getModelClassName() {
		return MeetupsRegistration.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("meetupsRegistrationId", getMeetupsRegistrationId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("meetupsEntryId", getMeetupsEntryId());
		attributes.put("status", getStatus());
		attributes.put("comments", getComments());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long meetupsRegistrationId = (Long)attributes.get(
				"meetupsRegistrationId");

		if (meetupsRegistrationId != null) {
			setMeetupsRegistrationId(meetupsRegistrationId);
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

		Long meetupsEntryId = (Long)attributes.get("meetupsEntryId");

		if (meetupsEntryId != null) {
			setMeetupsEntryId(meetupsEntryId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}
	}

	@Override
	public MeetupsRegistration toEscapedModel() {
		return new MeetupsRegistrationWrapper(_meetupsRegistration.toEscapedModel());
	}

	@Override
	public MeetupsRegistration toUnescapedModel() {
		return new MeetupsRegistrationWrapper(_meetupsRegistration.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _meetupsRegistration.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _meetupsRegistration.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _meetupsRegistration.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _meetupsRegistration.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MeetupsRegistration> toCacheModel() {
		return _meetupsRegistration.toCacheModel();
	}

	@Override
	public int compareTo(MeetupsRegistration meetupsRegistration) {
		return _meetupsRegistration.compareTo(meetupsRegistration);
	}

	/**
	* Returns the status of this meetups registration.
	*
	* @return the status of this meetups registration
	*/
	@Override
	public int getStatus() {
		return _meetupsRegistration.getStatus();
	}

	@Override
	public int hashCode() {
		return _meetupsRegistration.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _meetupsRegistration.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new MeetupsRegistrationWrapper((MeetupsRegistration)_meetupsRegistration.clone());
	}

	/**
	* Returns the comments of this meetups registration.
	*
	* @return the comments of this meetups registration
	*/
	@Override
	public java.lang.String getComments() {
		return _meetupsRegistration.getComments();
	}

	/**
	* Returns the user name of this meetups registration.
	*
	* @return the user name of this meetups registration
	*/
	@Override
	public java.lang.String getUserName() {
		return _meetupsRegistration.getUserName();
	}

	/**
	* Returns the user uuid of this meetups registration.
	*
	* @return the user uuid of this meetups registration
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _meetupsRegistration.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _meetupsRegistration.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _meetupsRegistration.toXmlString();
	}

	/**
	* Returns the create date of this meetups registration.
	*
	* @return the create date of this meetups registration
	*/
	@Override
	public Date getCreateDate() {
		return _meetupsRegistration.getCreateDate();
	}

	/**
	* Returns the modified date of this meetups registration.
	*
	* @return the modified date of this meetups registration
	*/
	@Override
	public Date getModifiedDate() {
		return _meetupsRegistration.getModifiedDate();
	}

	/**
	* Returns the company ID of this meetups registration.
	*
	* @return the company ID of this meetups registration
	*/
	@Override
	public long getCompanyId() {
		return _meetupsRegistration.getCompanyId();
	}

	/**
	* Returns the meetups entry ID of this meetups registration.
	*
	* @return the meetups entry ID of this meetups registration
	*/
	@Override
	public long getMeetupsEntryId() {
		return _meetupsRegistration.getMeetupsEntryId();
	}

	/**
	* Returns the meetups registration ID of this meetups registration.
	*
	* @return the meetups registration ID of this meetups registration
	*/
	@Override
	public long getMeetupsRegistrationId() {
		return _meetupsRegistration.getMeetupsRegistrationId();
	}

	/**
	* Returns the primary key of this meetups registration.
	*
	* @return the primary key of this meetups registration
	*/
	@Override
	public long getPrimaryKey() {
		return _meetupsRegistration.getPrimaryKey();
	}

	/**
	* Returns the user ID of this meetups registration.
	*
	* @return the user ID of this meetups registration
	*/
	@Override
	public long getUserId() {
		return _meetupsRegistration.getUserId();
	}

	@Override
	public void persist() {
		_meetupsRegistration.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_meetupsRegistration.setCachedModel(cachedModel);
	}

	/**
	* Sets the comments of this meetups registration.
	*
	* @param comments the comments of this meetups registration
	*/
	@Override
	public void setComments(java.lang.String comments) {
		_meetupsRegistration.setComments(comments);
	}

	/**
	* Sets the company ID of this meetups registration.
	*
	* @param companyId the company ID of this meetups registration
	*/
	@Override
	public void setCompanyId(long companyId) {
		_meetupsRegistration.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this meetups registration.
	*
	* @param createDate the create date of this meetups registration
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_meetupsRegistration.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_meetupsRegistration.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_meetupsRegistration.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_meetupsRegistration.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the meetups entry ID of this meetups registration.
	*
	* @param meetupsEntryId the meetups entry ID of this meetups registration
	*/
	@Override
	public void setMeetupsEntryId(long meetupsEntryId) {
		_meetupsRegistration.setMeetupsEntryId(meetupsEntryId);
	}

	/**
	* Sets the meetups registration ID of this meetups registration.
	*
	* @param meetupsRegistrationId the meetups registration ID of this meetups registration
	*/
	@Override
	public void setMeetupsRegistrationId(long meetupsRegistrationId) {
		_meetupsRegistration.setMeetupsRegistrationId(meetupsRegistrationId);
	}

	/**
	* Sets the modified date of this meetups registration.
	*
	* @param modifiedDate the modified date of this meetups registration
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_meetupsRegistration.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_meetupsRegistration.setNew(n);
	}

	/**
	* Sets the primary key of this meetups registration.
	*
	* @param primaryKey the primary key of this meetups registration
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_meetupsRegistration.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_meetupsRegistration.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this meetups registration.
	*
	* @param status the status of this meetups registration
	*/
	@Override
	public void setStatus(int status) {
		_meetupsRegistration.setStatus(status);
	}

	/**
	* Sets the user ID of this meetups registration.
	*
	* @param userId the user ID of this meetups registration
	*/
	@Override
	public void setUserId(long userId) {
		_meetupsRegistration.setUserId(userId);
	}

	/**
	* Sets the user name of this meetups registration.
	*
	* @param userName the user name of this meetups registration
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_meetupsRegistration.setUserName(userName);
	}

	/**
	* Sets the user uuid of this meetups registration.
	*
	* @param userUuid the user uuid of this meetups registration
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_meetupsRegistration.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MeetupsRegistrationWrapper)) {
			return false;
		}

		MeetupsRegistrationWrapper meetupsRegistrationWrapper = (MeetupsRegistrationWrapper)obj;

		if (Objects.equals(_meetupsRegistration,
					meetupsRegistrationWrapper._meetupsRegistration)) {
			return true;
		}

		return false;
	}

	@Override
	public MeetupsRegistration getWrappedModel() {
		return _meetupsRegistration;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _meetupsRegistration.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _meetupsRegistration.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_meetupsRegistration.resetOriginalValues();
	}

	private final MeetupsRegistration _meetupsRegistration;
}