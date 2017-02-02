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
 * This class is a wrapper for {@link ResourcePermission}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourcePermission
 * @generated
 */
@ProviderType
public class ResourcePermissionWrapper implements ResourcePermission,
	ModelWrapper<ResourcePermission> {
	public ResourcePermissionWrapper(ResourcePermission resourcePermission) {
		_resourcePermission = resourcePermission;
	}

	@Override
	public Class<?> getModelClass() {
		return ResourcePermission.class;
	}

	@Override
	public String getModelClassName() {
		return ResourcePermission.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("resourcePermissionId", getResourcePermissionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("name", getName());
		attributes.put("scope", getScope());
		attributes.put("primKey", getPrimKey());
		attributes.put("primKeyId", getPrimKeyId());
		attributes.put("roleId", getRoleId());
		attributes.put("ownerId", getOwnerId());
		attributes.put("actionIds", getActionIds());
		attributes.put("viewActionId", getViewActionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long resourcePermissionId = (Long)attributes.get("resourcePermissionId");

		if (resourcePermissionId != null) {
			setResourcePermissionId(resourcePermissionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Integer scope = (Integer)attributes.get("scope");

		if (scope != null) {
			setScope(scope);
		}

		String primKey = (String)attributes.get("primKey");

		if (primKey != null) {
			setPrimKey(primKey);
		}

		Long primKeyId = (Long)attributes.get("primKeyId");

		if (primKeyId != null) {
			setPrimKeyId(primKeyId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		Long ownerId = (Long)attributes.get("ownerId");

		if (ownerId != null) {
			setOwnerId(ownerId);
		}

		Long actionIds = (Long)attributes.get("actionIds");

		if (actionIds != null) {
			setActionIds(actionIds);
		}

		Boolean viewActionId = (Boolean)attributes.get("viewActionId");

		if (viewActionId != null) {
			setViewActionId(viewActionId);
		}
	}

	@Override
	public CacheModel<ResourcePermission> toCacheModel() {
		return _resourcePermission.toCacheModel();
	}

	@Override
	public ResourcePermission toEscapedModel() {
		return new ResourcePermissionWrapper(_resourcePermission.toEscapedModel());
	}

	@Override
	public ResourcePermission toUnescapedModel() {
		return new ResourcePermissionWrapper(_resourcePermission.toUnescapedModel());
	}

	/**
	* Returns the view action ID of this resource permission.
	*
	* @return the view action ID of this resource permission
	*/
	@Override
	public boolean getViewActionId() {
		return _resourcePermission.getViewActionId();
	}

	@Override
	public boolean hasAction(ResourceAction resourceAction) {
		return _resourcePermission.hasAction(resourceAction);
	}

	@Override
	public boolean hasActionId(java.lang.String actionId) {
		return _resourcePermission.hasActionId(actionId);
	}

	@Override
	public boolean isCachedModel() {
		return _resourcePermission.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _resourcePermission.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _resourcePermission.isNew();
	}

	/**
	* Returns <code>true</code> if this resource permission is view action ID.
	*
	* @return <code>true</code> if this resource permission is view action ID; <code>false</code> otherwise
	*/
	@Override
	public boolean isViewActionId() {
		return _resourcePermission.isViewActionId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _resourcePermission.getExpandoBridge();
	}

	@Override
	public int compareTo(ResourcePermission resourcePermission) {
		return _resourcePermission.compareTo(resourcePermission);
	}

	/**
	* Returns the scope of this resource permission.
	*
	* @return the scope of this resource permission
	*/
	@Override
	public int getScope() {
		return _resourcePermission.getScope();
	}

	@Override
	public int hashCode() {
		return _resourcePermission.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _resourcePermission.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ResourcePermissionWrapper((ResourcePermission)_resourcePermission.clone());
	}

	/**
	* Returns the name of this resource permission.
	*
	* @return the name of this resource permission
	*/
	@Override
	public java.lang.String getName() {
		return _resourcePermission.getName();
	}

	/**
	* Returns the prim key of this resource permission.
	*
	* @return the prim key of this resource permission
	*/
	@Override
	public java.lang.String getPrimKey() {
		return _resourcePermission.getPrimKey();
	}

	@Override
	public java.lang.String toString() {
		return _resourcePermission.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _resourcePermission.toXmlString();
	}

	/**
	* Returns the action IDs of this resource permission.
	*
	* @return the action IDs of this resource permission
	*/
	@Override
	public long getActionIds() {
		return _resourcePermission.getActionIds();
	}

	/**
	* Returns the company ID of this resource permission.
	*
	* @return the company ID of this resource permission
	*/
	@Override
	public long getCompanyId() {
		return _resourcePermission.getCompanyId();
	}

	/**
	* Returns the mvcc version of this resource permission.
	*
	* @return the mvcc version of this resource permission
	*/
	@Override
	public long getMvccVersion() {
		return _resourcePermission.getMvccVersion();
	}

	/**
	* Returns the owner ID of this resource permission.
	*
	* @return the owner ID of this resource permission
	*/
	@Override
	public long getOwnerId() {
		return _resourcePermission.getOwnerId();
	}

	/**
	* Returns the prim key ID of this resource permission.
	*
	* @return the prim key ID of this resource permission
	*/
	@Override
	public long getPrimKeyId() {
		return _resourcePermission.getPrimKeyId();
	}

	/**
	* Returns the primary key of this resource permission.
	*
	* @return the primary key of this resource permission
	*/
	@Override
	public long getPrimaryKey() {
		return _resourcePermission.getPrimaryKey();
	}

	/**
	* Returns the resource permission ID of this resource permission.
	*
	* @return the resource permission ID of this resource permission
	*/
	@Override
	public long getResourcePermissionId() {
		return _resourcePermission.getResourcePermissionId();
	}

	/**
	* Returns the role ID of this resource permission.
	*
	* @return the role ID of this resource permission
	*/
	@Override
	public long getRoleId() {
		return _resourcePermission.getRoleId();
	}

	@Override
	public void addResourceAction(java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourcePermission.addResourceAction(actionId);
	}

	@Override
	public void persist() {
		_resourcePermission.persist();
	}

	@Override
	public void removeResourceAction(java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_resourcePermission.removeResourceAction(actionId);
	}

	/**
	* Sets the action IDs of this resource permission.
	*
	* @param actionIds the action IDs of this resource permission
	*/
	@Override
	public void setActionIds(long actionIds) {
		_resourcePermission.setActionIds(actionIds);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_resourcePermission.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this resource permission.
	*
	* @param companyId the company ID of this resource permission
	*/
	@Override
	public void setCompanyId(long companyId) {
		_resourcePermission.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_resourcePermission.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_resourcePermission.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_resourcePermission.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the mvcc version of this resource permission.
	*
	* @param mvccVersion the mvcc version of this resource permission
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_resourcePermission.setMvccVersion(mvccVersion);
	}

	/**
	* Sets the name of this resource permission.
	*
	* @param name the name of this resource permission
	*/
	@Override
	public void setName(java.lang.String name) {
		_resourcePermission.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_resourcePermission.setNew(n);
	}

	/**
	* Sets the owner ID of this resource permission.
	*
	* @param ownerId the owner ID of this resource permission
	*/
	@Override
	public void setOwnerId(long ownerId) {
		_resourcePermission.setOwnerId(ownerId);
	}

	/**
	* Sets the prim key of this resource permission.
	*
	* @param primKey the prim key of this resource permission
	*/
	@Override
	public void setPrimKey(java.lang.String primKey) {
		_resourcePermission.setPrimKey(primKey);
	}

	/**
	* Sets the prim key ID of this resource permission.
	*
	* @param primKeyId the prim key ID of this resource permission
	*/
	@Override
	public void setPrimKeyId(long primKeyId) {
		_resourcePermission.setPrimKeyId(primKeyId);
	}

	/**
	* Sets the primary key of this resource permission.
	*
	* @param primaryKey the primary key of this resource permission
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_resourcePermission.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_resourcePermission.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the resource permission ID of this resource permission.
	*
	* @param resourcePermissionId the resource permission ID of this resource permission
	*/
	@Override
	public void setResourcePermissionId(long resourcePermissionId) {
		_resourcePermission.setResourcePermissionId(resourcePermissionId);
	}

	/**
	* Sets the role ID of this resource permission.
	*
	* @param roleId the role ID of this resource permission
	*/
	@Override
	public void setRoleId(long roleId) {
		_resourcePermission.setRoleId(roleId);
	}

	/**
	* Sets the scope of this resource permission.
	*
	* @param scope the scope of this resource permission
	*/
	@Override
	public void setScope(int scope) {
		_resourcePermission.setScope(scope);
	}

	/**
	* Sets whether this resource permission is view action ID.
	*
	* @param viewActionId the view action ID of this resource permission
	*/
	@Override
	public void setViewActionId(boolean viewActionId) {
		_resourcePermission.setViewActionId(viewActionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ResourcePermissionWrapper)) {
			return false;
		}

		ResourcePermissionWrapper resourcePermissionWrapper = (ResourcePermissionWrapper)obj;

		if (Objects.equals(_resourcePermission,
					resourcePermissionWrapper._resourcePermission)) {
			return true;
		}

		return false;
	}

	@Override
	public ResourcePermission getWrappedModel() {
		return _resourcePermission;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _resourcePermission.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _resourcePermission.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_resourcePermission.resetOriginalValues();
	}

	private final ResourcePermission _resourcePermission;
}