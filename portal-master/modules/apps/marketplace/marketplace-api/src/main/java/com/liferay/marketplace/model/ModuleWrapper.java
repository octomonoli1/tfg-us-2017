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

package com.liferay.marketplace.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Module}.
 * </p>
 *
 * @author Ryan Park
 * @see Module
 * @generated
 */
@ProviderType
public class ModuleWrapper implements Module, ModelWrapper<Module> {
	public ModuleWrapper(Module module) {
		_module = module;
	}

	@Override
	public Class<?> getModelClass() {
		return Module.class;
	}

	@Override
	public String getModelClassName() {
		return Module.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("moduleId", getModuleId());
		attributes.put("appId", getAppId());
		attributes.put("bundleSymbolicName", getBundleSymbolicName());
		attributes.put("bundleVersion", getBundleVersion());
		attributes.put("contextName", getContextName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long moduleId = (Long)attributes.get("moduleId");

		if (moduleId != null) {
			setModuleId(moduleId);
		}

		Long appId = (Long)attributes.get("appId");

		if (appId != null) {
			setAppId(appId);
		}

		String bundleSymbolicName = (String)attributes.get("bundleSymbolicName");

		if (bundleSymbolicName != null) {
			setBundleSymbolicName(bundleSymbolicName);
		}

		String bundleVersion = (String)attributes.get("bundleVersion");

		if (bundleVersion != null) {
			setBundleVersion(bundleVersion);
		}

		String contextName = (String)attributes.get("contextName");

		if (contextName != null) {
			setContextName(contextName);
		}
	}

	@Override
	public Module toEscapedModel() {
		return new ModuleWrapper(_module.toEscapedModel());
	}

	@Override
	public Module toUnescapedModel() {
		return new ModuleWrapper(_module.toUnescapedModel());
	}

	@Override
	public boolean isBundle() {
		return _module.isBundle();
	}

	@Override
	public boolean isCachedModel() {
		return _module.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _module.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _module.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _module.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Module> toCacheModel() {
		return _module.toCacheModel();
	}

	@Override
	public int compareTo(Module module) {
		return _module.compareTo(module);
	}

	@Override
	public int hashCode() {
		return _module.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _module.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ModuleWrapper((Module)_module.clone());
	}

	/**
	* Returns the bundle symbolic name of this module.
	*
	* @return the bundle symbolic name of this module
	*/
	@Override
	public java.lang.String getBundleSymbolicName() {
		return _module.getBundleSymbolicName();
	}

	/**
	* Returns the bundle version of this module.
	*
	* @return the bundle version of this module
	*/
	@Override
	public java.lang.String getBundleVersion() {
		return _module.getBundleVersion();
	}

	/**
	* Returns the context name of this module.
	*
	* @return the context name of this module
	*/
	@Override
	public java.lang.String getContextName() {
		return _module.getContextName();
	}

	/**
	* Returns the uuid of this module.
	*
	* @return the uuid of this module
	*/
	@Override
	public java.lang.String getUuid() {
		return _module.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _module.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _module.toXmlString();
	}

	/**
	* Returns the app ID of this module.
	*
	* @return the app ID of this module
	*/
	@Override
	public long getAppId() {
		return _module.getAppId();
	}

	/**
	* Returns the module ID of this module.
	*
	* @return the module ID of this module
	*/
	@Override
	public long getModuleId() {
		return _module.getModuleId();
	}

	/**
	* Returns the primary key of this module.
	*
	* @return the primary key of this module
	*/
	@Override
	public long getPrimaryKey() {
		return _module.getPrimaryKey();
	}

	@Override
	public void persist() {
		_module.persist();
	}

	/**
	* Sets the app ID of this module.
	*
	* @param appId the app ID of this module
	*/
	@Override
	public void setAppId(long appId) {
		_module.setAppId(appId);
	}

	/**
	* Sets the bundle symbolic name of this module.
	*
	* @param bundleSymbolicName the bundle symbolic name of this module
	*/
	@Override
	public void setBundleSymbolicName(java.lang.String bundleSymbolicName) {
		_module.setBundleSymbolicName(bundleSymbolicName);
	}

	/**
	* Sets the bundle version of this module.
	*
	* @param bundleVersion the bundle version of this module
	*/
	@Override
	public void setBundleVersion(java.lang.String bundleVersion) {
		_module.setBundleVersion(bundleVersion);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_module.setCachedModel(cachedModel);
	}

	/**
	* Sets the context name of this module.
	*
	* @param contextName the context name of this module
	*/
	@Override
	public void setContextName(java.lang.String contextName) {
		_module.setContextName(contextName);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_module.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_module.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_module.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the module ID of this module.
	*
	* @param moduleId the module ID of this module
	*/
	@Override
	public void setModuleId(long moduleId) {
		_module.setModuleId(moduleId);
	}

	@Override
	public void setNew(boolean n) {
		_module.setNew(n);
	}

	/**
	* Sets the primary key of this module.
	*
	* @param primaryKey the primary key of this module
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_module.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_module.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the uuid of this module.
	*
	* @param uuid the uuid of this module
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_module.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ModuleWrapper)) {
			return false;
		}

		ModuleWrapper moduleWrapper = (ModuleWrapper)obj;

		if (Objects.equals(_module, moduleWrapper._module)) {
			return true;
		}

		return false;
	}

	@Override
	public Module getWrappedModel() {
		return _module;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _module.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _module.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_module.resetOriginalValues();
	}

	private final Module _module;
}