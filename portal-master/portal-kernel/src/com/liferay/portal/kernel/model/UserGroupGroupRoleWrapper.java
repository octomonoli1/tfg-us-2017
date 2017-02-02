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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link UserGroupGroupRole}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupGroupRole
 * @generated
 */
@ProviderType
public class UserGroupGroupRoleWrapper implements UserGroupGroupRole,
	ModelWrapper<UserGroupGroupRole> {
	public UserGroupGroupRoleWrapper(UserGroupGroupRole userGroupGroupRole) {
		_userGroupGroupRole = userGroupGroupRole;
	}

	@Override
	public Class<?> getModelClass() {
		return UserGroupGroupRole.class;
	}

	@Override
	public String getModelClassName() {
		return UserGroupGroupRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("userGroupId", getUserGroupId());
		attributes.put("groupId", getGroupId());
		attributes.put("roleId", getRoleId());
		attributes.put("companyId", getCompanyId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long userGroupId = (Long)attributes.get("userGroupId");

		if (userGroupId != null) {
			setUserGroupId(userGroupId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}
	}

	@Override
	public CacheModel<UserGroupGroupRole> toCacheModel() {
		return _userGroupGroupRole.toCacheModel();
	}

	@Override
	public Group getGroup()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userGroupGroupRole.getGroup();
	}

	@Override
	public Role getRole()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userGroupGroupRole.getRole();
	}

	@Override
	public UserGroup getUserGroup()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userGroupGroupRole.getUserGroup();
	}

	@Override
	public UserGroupGroupRole toEscapedModel() {
		return new UserGroupGroupRoleWrapper(_userGroupGroupRole.toEscapedModel());
	}

	@Override
	public UserGroupGroupRole toUnescapedModel() {
		return new UserGroupGroupRoleWrapper(_userGroupGroupRole.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _userGroupGroupRole.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _userGroupGroupRole.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _userGroupGroupRole.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _userGroupGroupRole.getExpandoBridge();
	}

	/**
	* Returns the primary key of this user group group role.
	*
	* @return the primary key of this user group group role
	*/
	@Override
	public com.liferay.portal.kernel.service.persistence.UserGroupGroupRolePK getPrimaryKey() {
		return _userGroupGroupRole.getPrimaryKey();
	}

	@Override
	public int compareTo(UserGroupGroupRole userGroupGroupRole) {
		return _userGroupGroupRole.compareTo(userGroupGroupRole);
	}

	@Override
	public int hashCode() {
		return _userGroupGroupRole.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userGroupGroupRole.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new UserGroupGroupRoleWrapper((UserGroupGroupRole)_userGroupGroupRole.clone());
	}

	@Override
	public java.lang.String toString() {
		return _userGroupGroupRole.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _userGroupGroupRole.toXmlString();
	}

	/**
	* Returns the company ID of this user group group role.
	*
	* @return the company ID of this user group group role
	*/
	@Override
	public long getCompanyId() {
		return _userGroupGroupRole.getCompanyId();
	}

	/**
	* Returns the group ID of this user group group role.
	*
	* @return the group ID of this user group group role
	*/
	@Override
	public long getGroupId() {
		return _userGroupGroupRole.getGroupId();
	}

	/**
	* Returns the mvcc version of this user group group role.
	*
	* @return the mvcc version of this user group group role
	*/
	@Override
	public long getMvccVersion() {
		return _userGroupGroupRole.getMvccVersion();
	}

	/**
	* Returns the role ID of this user group group role.
	*
	* @return the role ID of this user group group role
	*/
	@Override
	public long getRoleId() {
		return _userGroupGroupRole.getRoleId();
	}

	/**
	* Returns the user group ID of this user group group role.
	*
	* @return the user group ID of this user group group role
	*/
	@Override
	public long getUserGroupId() {
		return _userGroupGroupRole.getUserGroupId();
	}

	@Override
	public void persist() {
		_userGroupGroupRole.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_userGroupGroupRole.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this user group group role.
	*
	* @param companyId the company ID of this user group group role
	*/
	@Override
	public void setCompanyId(long companyId) {
		_userGroupGroupRole.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_userGroupGroupRole.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_userGroupGroupRole.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_userGroupGroupRole.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this user group group role.
	*
	* @param groupId the group ID of this user group group role
	*/
	@Override
	public void setGroupId(long groupId) {
		_userGroupGroupRole.setGroupId(groupId);
	}

	/**
	* Sets the mvcc version of this user group group role.
	*
	* @param mvccVersion the mvcc version of this user group group role
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_userGroupGroupRole.setMvccVersion(mvccVersion);
	}

	@Override
	public void setNew(boolean n) {
		_userGroupGroupRole.setNew(n);
	}

	/**
	* Sets the primary key of this user group group role.
	*
	* @param primaryKey the primary key of this user group group role
	*/
	@Override
	public void setPrimaryKey(
		com.liferay.portal.kernel.service.persistence.UserGroupGroupRolePK primaryKey) {
		_userGroupGroupRole.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_userGroupGroupRole.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role ID of this user group group role.
	*
	* @param roleId the role ID of this user group group role
	*/
	@Override
	public void setRoleId(long roleId) {
		_userGroupGroupRole.setRoleId(roleId);
	}

	/**
	* Sets the user group ID of this user group group role.
	*
	* @param userGroupId the user group ID of this user group group role
	*/
	@Override
	public void setUserGroupId(long userGroupId) {
		_userGroupGroupRole.setUserGroupId(userGroupId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserGroupGroupRoleWrapper)) {
			return false;
		}

		UserGroupGroupRoleWrapper userGroupGroupRoleWrapper = (UserGroupGroupRoleWrapper)obj;

		if (Objects.equals(_userGroupGroupRole,
					userGroupGroupRoleWrapper._userGroupGroupRole)) {
			return true;
		}

		return false;
	}

	@Override
	public UserGroupGroupRole getWrappedModel() {
		return _userGroupGroupRole;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _userGroupGroupRole.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _userGroupGroupRole.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_userGroupGroupRole.resetOriginalValues();
	}

	private final UserGroupGroupRole _userGroupGroupRole;
}