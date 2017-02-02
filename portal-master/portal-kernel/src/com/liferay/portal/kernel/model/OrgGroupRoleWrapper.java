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
 * This class is a wrapper for {@link OrgGroupRole}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrgGroupRole
 * @generated
 */
@ProviderType
public class OrgGroupRoleWrapper implements OrgGroupRole,
	ModelWrapper<OrgGroupRole> {
	public OrgGroupRoleWrapper(OrgGroupRole orgGroupRole) {
		_orgGroupRole = orgGroupRole;
	}

	@Override
	public Class<?> getModelClass() {
		return OrgGroupRole.class;
	}

	@Override
	public String getModelClassName() {
		return OrgGroupRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("organizationId", getOrganizationId());
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

		Long organizationId = (Long)attributes.get("organizationId");

		if (organizationId != null) {
			setOrganizationId(organizationId);
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
	public CacheModel<OrgGroupRole> toCacheModel() {
		return _orgGroupRole.toCacheModel();
	}

	@Override
	public OrgGroupRole toEscapedModel() {
		return new OrgGroupRoleWrapper(_orgGroupRole.toEscapedModel());
	}

	@Override
	public OrgGroupRole toUnescapedModel() {
		return new OrgGroupRoleWrapper(_orgGroupRole.toUnescapedModel());
	}

	@Override
	public boolean containsGroup(java.util.List<Group> groups) {
		return _orgGroupRole.containsGroup(groups);
	}

	@Override
	public boolean containsOrganization(
		java.util.List<Organization> organizations) {
		return _orgGroupRole.containsOrganization(organizations);
	}

	@Override
	public boolean isCachedModel() {
		return _orgGroupRole.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _orgGroupRole.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _orgGroupRole.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _orgGroupRole.getExpandoBridge();
	}

	/**
	* Returns the primary key of this org group role.
	*
	* @return the primary key of this org group role
	*/
	@Override
	public com.liferay.portal.kernel.service.persistence.OrgGroupRolePK getPrimaryKey() {
		return _orgGroupRole.getPrimaryKey();
	}

	@Override
	public int compareTo(OrgGroupRole orgGroupRole) {
		return _orgGroupRole.compareTo(orgGroupRole);
	}

	@Override
	public int hashCode() {
		return _orgGroupRole.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _orgGroupRole.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new OrgGroupRoleWrapper((OrgGroupRole)_orgGroupRole.clone());
	}

	@Override
	public java.lang.String toString() {
		return _orgGroupRole.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _orgGroupRole.toXmlString();
	}

	/**
	* Returns the company ID of this org group role.
	*
	* @return the company ID of this org group role
	*/
	@Override
	public long getCompanyId() {
		return _orgGroupRole.getCompanyId();
	}

	/**
	* Returns the group ID of this org group role.
	*
	* @return the group ID of this org group role
	*/
	@Override
	public long getGroupId() {
		return _orgGroupRole.getGroupId();
	}

	/**
	* Returns the mvcc version of this org group role.
	*
	* @return the mvcc version of this org group role
	*/
	@Override
	public long getMvccVersion() {
		return _orgGroupRole.getMvccVersion();
	}

	/**
	* Returns the organization ID of this org group role.
	*
	* @return the organization ID of this org group role
	*/
	@Override
	public long getOrganizationId() {
		return _orgGroupRole.getOrganizationId();
	}

	/**
	* Returns the role ID of this org group role.
	*
	* @return the role ID of this org group role
	*/
	@Override
	public long getRoleId() {
		return _orgGroupRole.getRoleId();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_orgGroupRole.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this org group role.
	*
	* @param companyId the company ID of this org group role
	*/
	@Override
	public void setCompanyId(long companyId) {
		_orgGroupRole.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_orgGroupRole.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_orgGroupRole.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_orgGroupRole.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this org group role.
	*
	* @param groupId the group ID of this org group role
	*/
	@Override
	public void setGroupId(long groupId) {
		_orgGroupRole.setGroupId(groupId);
	}

	/**
	* Sets the mvcc version of this org group role.
	*
	* @param mvccVersion the mvcc version of this org group role
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_orgGroupRole.setMvccVersion(mvccVersion);
	}

	@Override
	public void setNew(boolean n) {
		_orgGroupRole.setNew(n);
	}

	/**
	* Sets the organization ID of this org group role.
	*
	* @param organizationId the organization ID of this org group role
	*/
	@Override
	public void setOrganizationId(long organizationId) {
		_orgGroupRole.setOrganizationId(organizationId);
	}

	/**
	* Sets the primary key of this org group role.
	*
	* @param primaryKey the primary key of this org group role
	*/
	@Override
	public void setPrimaryKey(
		com.liferay.portal.kernel.service.persistence.OrgGroupRolePK primaryKey) {
		_orgGroupRole.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_orgGroupRole.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role ID of this org group role.
	*
	* @param roleId the role ID of this org group role
	*/
	@Override
	public void setRoleId(long roleId) {
		_orgGroupRole.setRoleId(roleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OrgGroupRoleWrapper)) {
			return false;
		}

		OrgGroupRoleWrapper orgGroupRoleWrapper = (OrgGroupRoleWrapper)obj;

		if (Objects.equals(_orgGroupRole, orgGroupRoleWrapper._orgGroupRole)) {
			return true;
		}

		return false;
	}

	@Override
	public OrgGroupRole getWrappedModel() {
		return _orgGroupRole;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _orgGroupRole.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _orgGroupRole.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_orgGroupRole.resetOriginalValues();
	}

	private final OrgGroupRole _orgGroupRole;
}