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

package com.liferay.portal.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link UserTracker}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserTracker
 * @generated
 */
@ProviderType
public class UserTrackerWrapper implements UserTracker,
	ModelWrapper<UserTracker> {
	public UserTrackerWrapper(UserTracker userTracker) {
		_userTracker = userTracker;
	}

	@Override
	public Class<?> getModelClass() {
		return UserTracker.class;
	}

	@Override
	public String getModelClassName() {
		return UserTracker.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("userTrackerId", getUserTrackerId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("sessionId", getSessionId());
		attributes.put("remoteAddr", getRemoteAddr());
		attributes.put("remoteHost", getRemoteHost());
		attributes.put("userAgent", getUserAgent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long userTrackerId = (Long)attributes.get("userTrackerId");

		if (userTrackerId != null) {
			setUserTrackerId(userTrackerId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		String remoteAddr = (String)attributes.get("remoteAddr");

		if (remoteAddr != null) {
			setRemoteAddr(remoteAddr);
		}

		String remoteHost = (String)attributes.get("remoteHost");

		if (remoteHost != null) {
			setRemoteHost(remoteHost);
		}

		String userAgent = (String)attributes.get("userAgent");

		if (userAgent != null) {
			setUserAgent(userAgent);
		}
	}

	@Override
	public CacheModel<UserTracker> toCacheModel() {
		return _userTracker.toCacheModel();
	}

	@Override
	public UserTracker toEscapedModel() {
		return new UserTrackerWrapper(_userTracker.toEscapedModel());
	}

	@Override
	public UserTracker toUnescapedModel() {
		return new UserTrackerWrapper(_userTracker.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _userTracker.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _userTracker.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _userTracker.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _userTracker.getExpandoBridge();
	}

	@Override
	public int compareTo(UserTracker userTracker) {
		return _userTracker.compareTo(userTracker);
	}

	@Override
	public int getHits() {
		return _userTracker.getHits();
	}

	@Override
	public int hashCode() {
		return _userTracker.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userTracker.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new UserTrackerWrapper((UserTracker)_userTracker.clone());
	}

	@Override
	public java.lang.String getEmailAddress() {
		return _userTracker.getEmailAddress();
	}

	@Override
	public java.lang.String getFullName() {
		return _userTracker.getFullName();
	}

	/**
	* Returns the remote addr of this user tracker.
	*
	* @return the remote addr of this user tracker
	*/
	@Override
	public java.lang.String getRemoteAddr() {
		return _userTracker.getRemoteAddr();
	}

	/**
	* Returns the remote host of this user tracker.
	*
	* @return the remote host of this user tracker
	*/
	@Override
	public java.lang.String getRemoteHost() {
		return _userTracker.getRemoteHost();
	}

	/**
	* Returns the session ID of this user tracker.
	*
	* @return the session ID of this user tracker
	*/
	@Override
	public java.lang.String getSessionId() {
		return _userTracker.getSessionId();
	}

	/**
	* Returns the user agent of this user tracker.
	*
	* @return the user agent of this user tracker
	*/
	@Override
	public java.lang.String getUserAgent() {
		return _userTracker.getUserAgent();
	}

	/**
	* Returns the user uuid of this user tracker.
	*
	* @return the user uuid of this user tracker
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _userTracker.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _userTracker.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _userTracker.toXmlString();
	}

	/**
	* Returns the modified date of this user tracker.
	*
	* @return the modified date of this user tracker
	*/
	@Override
	public Date getModifiedDate() {
		return _userTracker.getModifiedDate();
	}

	@Override
	public java.util.List<UserTrackerPath> getPaths() {
		return _userTracker.getPaths();
	}

	/**
	* Returns the company ID of this user tracker.
	*
	* @return the company ID of this user tracker
	*/
	@Override
	public long getCompanyId() {
		return _userTracker.getCompanyId();
	}

	/**
	* Returns the mvcc version of this user tracker.
	*
	* @return the mvcc version of this user tracker
	*/
	@Override
	public long getMvccVersion() {
		return _userTracker.getMvccVersion();
	}

	/**
	* Returns the primary key of this user tracker.
	*
	* @return the primary key of this user tracker
	*/
	@Override
	public long getPrimaryKey() {
		return _userTracker.getPrimaryKey();
	}

	/**
	* Returns the user ID of this user tracker.
	*
	* @return the user ID of this user tracker
	*/
	@Override
	public long getUserId() {
		return _userTracker.getUserId();
	}

	/**
	* Returns the user tracker ID of this user tracker.
	*
	* @return the user tracker ID of this user tracker
	*/
	@Override
	public long getUserTrackerId() {
		return _userTracker.getUserTrackerId();
	}

	@Override
	public void addPath(UserTrackerPath path) {
		_userTracker.addPath(path);
	}

	@Override
	public void persist() {
		_userTracker.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_userTracker.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this user tracker.
	*
	* @param companyId the company ID of this user tracker
	*/
	@Override
	public void setCompanyId(long companyId) {
		_userTracker.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_userTracker.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_userTracker.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_userTracker.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this user tracker.
	*
	* @param modifiedDate the modified date of this user tracker
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_userTracker.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the mvcc version of this user tracker.
	*
	* @param mvccVersion the mvcc version of this user tracker
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_userTracker.setMvccVersion(mvccVersion);
	}

	@Override
	public void setNew(boolean n) {
		_userTracker.setNew(n);
	}

	/**
	* Sets the primary key of this user tracker.
	*
	* @param primaryKey the primary key of this user tracker
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_userTracker.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_userTracker.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the remote addr of this user tracker.
	*
	* @param remoteAddr the remote addr of this user tracker
	*/
	@Override
	public void setRemoteAddr(java.lang.String remoteAddr) {
		_userTracker.setRemoteAddr(remoteAddr);
	}

	/**
	* Sets the remote host of this user tracker.
	*
	* @param remoteHost the remote host of this user tracker
	*/
	@Override
	public void setRemoteHost(java.lang.String remoteHost) {
		_userTracker.setRemoteHost(remoteHost);
	}

	/**
	* Sets the session ID of this user tracker.
	*
	* @param sessionId the session ID of this user tracker
	*/
	@Override
	public void setSessionId(java.lang.String sessionId) {
		_userTracker.setSessionId(sessionId);
	}

	/**
	* Sets the user agent of this user tracker.
	*
	* @param userAgent the user agent of this user tracker
	*/
	@Override
	public void setUserAgent(java.lang.String userAgent) {
		_userTracker.setUserAgent(userAgent);
	}

	/**
	* Sets the user ID of this user tracker.
	*
	* @param userId the user ID of this user tracker
	*/
	@Override
	public void setUserId(long userId) {
		_userTracker.setUserId(userId);
	}

	/**
	* Sets the user tracker ID of this user tracker.
	*
	* @param userTrackerId the user tracker ID of this user tracker
	*/
	@Override
	public void setUserTrackerId(long userTrackerId) {
		_userTracker.setUserTrackerId(userTrackerId);
	}

	/**
	* Sets the user uuid of this user tracker.
	*
	* @param userUuid the user uuid of this user tracker
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_userTracker.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserTrackerWrapper)) {
			return false;
		}

		UserTrackerWrapper userTrackerWrapper = (UserTrackerWrapper)obj;

		if (Objects.equals(_userTracker, userTrackerWrapper._userTracker)) {
			return true;
		}

		return false;
	}

	@Override
	public UserTracker getWrappedModel() {
		return _userTracker;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _userTracker.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _userTracker.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_userTracker.resetOriginalValues();
	}

	private final UserTracker _userTracker;
}