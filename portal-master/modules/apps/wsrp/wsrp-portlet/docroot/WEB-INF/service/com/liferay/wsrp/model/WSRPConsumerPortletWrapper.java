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

package com.liferay.wsrp.model;

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
 * This class is a wrapper for {@link WSRPConsumerPortlet}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPortlet
 * @generated
 */
@ProviderType
public class WSRPConsumerPortletWrapper implements WSRPConsumerPortlet,
	ModelWrapper<WSRPConsumerPortlet> {
	public WSRPConsumerPortletWrapper(WSRPConsumerPortlet wsrpConsumerPortlet) {
		_wsrpConsumerPortlet = wsrpConsumerPortlet;
	}

	@Override
	public Class<?> getModelClass() {
		return WSRPConsumerPortlet.class;
	}

	@Override
	public String getModelClassName() {
		return WSRPConsumerPortlet.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("wsrpConsumerPortletId", getWsrpConsumerPortletId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("wsrpConsumerId", getWsrpConsumerId());
		attributes.put("name", getName());
		attributes.put("portletHandle", getPortletHandle());
		attributes.put("lastPublishDate", getLastPublishDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long wsrpConsumerPortletId = (Long)attributes.get(
				"wsrpConsumerPortletId");

		if (wsrpConsumerPortletId != null) {
			setWsrpConsumerPortletId(wsrpConsumerPortletId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long wsrpConsumerId = (Long)attributes.get("wsrpConsumerId");

		if (wsrpConsumerId != null) {
			setWsrpConsumerId(wsrpConsumerId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String portletHandle = (String)attributes.get("portletHandle");

		if (portletHandle != null) {
			setPortletHandle(portletHandle);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	@Override
	public WSRPConsumerPortlet toEscapedModel() {
		return new WSRPConsumerPortletWrapper(_wsrpConsumerPortlet.toEscapedModel());
	}

	@Override
	public WSRPConsumerPortlet toUnescapedModel() {
		return new WSRPConsumerPortletWrapper(_wsrpConsumerPortlet.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _wsrpConsumerPortlet.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _wsrpConsumerPortlet.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _wsrpConsumerPortlet.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _wsrpConsumerPortlet.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WSRPConsumerPortlet> toCacheModel() {
		return _wsrpConsumerPortlet.toCacheModel();
	}

	@Override
	public int compareTo(WSRPConsumerPortlet wsrpConsumerPortlet) {
		return _wsrpConsumerPortlet.compareTo(wsrpConsumerPortlet);
	}

	@Override
	public int hashCode() {
		return _wsrpConsumerPortlet.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _wsrpConsumerPortlet.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new WSRPConsumerPortletWrapper((WSRPConsumerPortlet)_wsrpConsumerPortlet.clone());
	}

	/**
	* Returns the name of this w s r p consumer portlet.
	*
	* @return the name of this w s r p consumer portlet
	*/
	@Override
	public java.lang.String getName() {
		return _wsrpConsumerPortlet.getName();
	}

	/**
	* Returns the portlet handle of this w s r p consumer portlet.
	*
	* @return the portlet handle of this w s r p consumer portlet
	*/
	@Override
	public java.lang.String getPortletHandle() {
		return _wsrpConsumerPortlet.getPortletHandle();
	}

	/**
	* Returns the uuid of this w s r p consumer portlet.
	*
	* @return the uuid of this w s r p consumer portlet
	*/
	@Override
	public java.lang.String getUuid() {
		return _wsrpConsumerPortlet.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _wsrpConsumerPortlet.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _wsrpConsumerPortlet.toXmlString();
	}

	/**
	* Returns the create date of this w s r p consumer portlet.
	*
	* @return the create date of this w s r p consumer portlet
	*/
	@Override
	public Date getCreateDate() {
		return _wsrpConsumerPortlet.getCreateDate();
	}

	/**
	* Returns the last publish date of this w s r p consumer portlet.
	*
	* @return the last publish date of this w s r p consumer portlet
	*/
	@Override
	public Date getLastPublishDate() {
		return _wsrpConsumerPortlet.getLastPublishDate();
	}

	/**
	* Returns the modified date of this w s r p consumer portlet.
	*
	* @return the modified date of this w s r p consumer portlet
	*/
	@Override
	public Date getModifiedDate() {
		return _wsrpConsumerPortlet.getModifiedDate();
	}

	/**
	* Returns the company ID of this w s r p consumer portlet.
	*
	* @return the company ID of this w s r p consumer portlet
	*/
	@Override
	public long getCompanyId() {
		return _wsrpConsumerPortlet.getCompanyId();
	}

	/**
	* Returns the primary key of this w s r p consumer portlet.
	*
	* @return the primary key of this w s r p consumer portlet
	*/
	@Override
	public long getPrimaryKey() {
		return _wsrpConsumerPortlet.getPrimaryKey();
	}

	/**
	* Returns the wsrp consumer ID of this w s r p consumer portlet.
	*
	* @return the wsrp consumer ID of this w s r p consumer portlet
	*/
	@Override
	public long getWsrpConsumerId() {
		return _wsrpConsumerPortlet.getWsrpConsumerId();
	}

	/**
	* Returns the wsrp consumer portlet ID of this w s r p consumer portlet.
	*
	* @return the wsrp consumer portlet ID of this w s r p consumer portlet
	*/
	@Override
	public long getWsrpConsumerPortletId() {
		return _wsrpConsumerPortlet.getWsrpConsumerPortletId();
	}

	@Override
	public void persist() {
		_wsrpConsumerPortlet.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_wsrpConsumerPortlet.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this w s r p consumer portlet.
	*
	* @param companyId the company ID of this w s r p consumer portlet
	*/
	@Override
	public void setCompanyId(long companyId) {
		_wsrpConsumerPortlet.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this w s r p consumer portlet.
	*
	* @param createDate the create date of this w s r p consumer portlet
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_wsrpConsumerPortlet.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_wsrpConsumerPortlet.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_wsrpConsumerPortlet.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_wsrpConsumerPortlet.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last publish date of this w s r p consumer portlet.
	*
	* @param lastPublishDate the last publish date of this w s r p consumer portlet
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_wsrpConsumerPortlet.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this w s r p consumer portlet.
	*
	* @param modifiedDate the modified date of this w s r p consumer portlet
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_wsrpConsumerPortlet.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this w s r p consumer portlet.
	*
	* @param name the name of this w s r p consumer portlet
	*/
	@Override
	public void setName(java.lang.String name) {
		_wsrpConsumerPortlet.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_wsrpConsumerPortlet.setNew(n);
	}

	/**
	* Sets the portlet handle of this w s r p consumer portlet.
	*
	* @param portletHandle the portlet handle of this w s r p consumer portlet
	*/
	@Override
	public void setPortletHandle(java.lang.String portletHandle) {
		_wsrpConsumerPortlet.setPortletHandle(portletHandle);
	}

	/**
	* Sets the primary key of this w s r p consumer portlet.
	*
	* @param primaryKey the primary key of this w s r p consumer portlet
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_wsrpConsumerPortlet.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_wsrpConsumerPortlet.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the uuid of this w s r p consumer portlet.
	*
	* @param uuid the uuid of this w s r p consumer portlet
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_wsrpConsumerPortlet.setUuid(uuid);
	}

	/**
	* Sets the wsrp consumer ID of this w s r p consumer portlet.
	*
	* @param wsrpConsumerId the wsrp consumer ID of this w s r p consumer portlet
	*/
	@Override
	public void setWsrpConsumerId(long wsrpConsumerId) {
		_wsrpConsumerPortlet.setWsrpConsumerId(wsrpConsumerId);
	}

	/**
	* Sets the wsrp consumer portlet ID of this w s r p consumer portlet.
	*
	* @param wsrpConsumerPortletId the wsrp consumer portlet ID of this w s r p consumer portlet
	*/
	@Override
	public void setWsrpConsumerPortletId(long wsrpConsumerPortletId) {
		_wsrpConsumerPortlet.setWsrpConsumerPortletId(wsrpConsumerPortletId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WSRPConsumerPortletWrapper)) {
			return false;
		}

		WSRPConsumerPortletWrapper wsrpConsumerPortletWrapper = (WSRPConsumerPortletWrapper)obj;

		if (Objects.equals(_wsrpConsumerPortlet,
					wsrpConsumerPortletWrapper._wsrpConsumerPortlet)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _wsrpConsumerPortlet.getStagedModelType();
	}

	@Override
	public WSRPConsumerPortlet getWrappedModel() {
		return _wsrpConsumerPortlet;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _wsrpConsumerPortlet.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _wsrpConsumerPortlet.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_wsrpConsumerPortlet.resetOriginalValues();
	}

	private final WSRPConsumerPortlet _wsrpConsumerPortlet;
}