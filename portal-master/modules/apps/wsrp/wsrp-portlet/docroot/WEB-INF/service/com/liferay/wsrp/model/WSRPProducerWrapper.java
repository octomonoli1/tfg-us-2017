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
 * This class is a wrapper for {@link WSRPProducer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPProducer
 * @generated
 */
@ProviderType
public class WSRPProducerWrapper implements WSRPProducer,
	ModelWrapper<WSRPProducer> {
	public WSRPProducerWrapper(WSRPProducer wsrpProducer) {
		_wsrpProducer = wsrpProducer;
	}

	@Override
	public Class<?> getModelClass() {
		return WSRPProducer.class;
	}

	@Override
	public String getModelClassName() {
		return WSRPProducer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("wsrpProducerId", getWsrpProducerId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("version", getVersion());
		attributes.put("portletIds", getPortletIds());
		attributes.put("lastPublishDate", getLastPublishDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long wsrpProducerId = (Long)attributes.get("wsrpProducerId");

		if (wsrpProducerId != null) {
			setWsrpProducerId(wsrpProducerId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String portletIds = (String)attributes.get("portletIds");

		if (portletIds != null) {
			setPortletIds(portletIds);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	@Override
	public WSRPProducer toEscapedModel() {
		return new WSRPProducerWrapper(_wsrpProducer.toEscapedModel());
	}

	@Override
	public WSRPProducer toUnescapedModel() {
		return new WSRPProducerWrapper(_wsrpProducer.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _wsrpProducer.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _wsrpProducer.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _wsrpProducer.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _wsrpProducer.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WSRPProducer> toCacheModel() {
		return _wsrpProducer.toCacheModel();
	}

	@Override
	public int compareTo(WSRPProducer wsrpProducer) {
		return _wsrpProducer.compareTo(wsrpProducer);
	}

	@Override
	public int hashCode() {
		return _wsrpProducer.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _wsrpProducer.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new WSRPProducerWrapper((WSRPProducer)_wsrpProducer.clone());
	}

	/**
	* Returns the name of this w s r p producer.
	*
	* @return the name of this w s r p producer
	*/
	@Override
	public java.lang.String getName() {
		return _wsrpProducer.getName();
	}

	/**
	* Returns the portlet IDs of this w s r p producer.
	*
	* @return the portlet IDs of this w s r p producer
	*/
	@Override
	public java.lang.String getPortletIds() {
		return _wsrpProducer.getPortletIds();
	}

	@Override
	public java.lang.String getURL(java.lang.String prefixURL) {
		return _wsrpProducer.getURL(prefixURL);
	}

	/**
	* Returns the uuid of this w s r p producer.
	*
	* @return the uuid of this w s r p producer
	*/
	@Override
	public java.lang.String getUuid() {
		return _wsrpProducer.getUuid();
	}

	/**
	* Returns the version of this w s r p producer.
	*
	* @return the version of this w s r p producer
	*/
	@Override
	public java.lang.String getVersion() {
		return _wsrpProducer.getVersion();
	}

	@Override
	public java.lang.String toString() {
		return _wsrpProducer.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _wsrpProducer.toXmlString();
	}

	/**
	* Returns the create date of this w s r p producer.
	*
	* @return the create date of this w s r p producer
	*/
	@Override
	public Date getCreateDate() {
		return _wsrpProducer.getCreateDate();
	}

	/**
	* Returns the last publish date of this w s r p producer.
	*
	* @return the last publish date of this w s r p producer
	*/
	@Override
	public Date getLastPublishDate() {
		return _wsrpProducer.getLastPublishDate();
	}

	/**
	* Returns the modified date of this w s r p producer.
	*
	* @return the modified date of this w s r p producer
	*/
	@Override
	public Date getModifiedDate() {
		return _wsrpProducer.getModifiedDate();
	}

	/**
	* Returns the company ID of this w s r p producer.
	*
	* @return the company ID of this w s r p producer
	*/
	@Override
	public long getCompanyId() {
		return _wsrpProducer.getCompanyId();
	}

	/**
	* Returns the group ID of this w s r p producer.
	*
	* @return the group ID of this w s r p producer
	*/
	@Override
	public long getGroupId() {
		return _wsrpProducer.getGroupId();
	}

	/**
	* Returns the primary key of this w s r p producer.
	*
	* @return the primary key of this w s r p producer
	*/
	@Override
	public long getPrimaryKey() {
		return _wsrpProducer.getPrimaryKey();
	}

	/**
	* Returns the wsrp producer ID of this w s r p producer.
	*
	* @return the wsrp producer ID of this w s r p producer
	*/
	@Override
	public long getWsrpProducerId() {
		return _wsrpProducer.getWsrpProducerId();
	}

	@Override
	public void persist() {
		_wsrpProducer.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_wsrpProducer.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this w s r p producer.
	*
	* @param companyId the company ID of this w s r p producer
	*/
	@Override
	public void setCompanyId(long companyId) {
		_wsrpProducer.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this w s r p producer.
	*
	* @param createDate the create date of this w s r p producer
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_wsrpProducer.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_wsrpProducer.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_wsrpProducer.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_wsrpProducer.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this w s r p producer.
	*
	* @param groupId the group ID of this w s r p producer
	*/
	@Override
	public void setGroupId(long groupId) {
		_wsrpProducer.setGroupId(groupId);
	}

	/**
	* Sets the last publish date of this w s r p producer.
	*
	* @param lastPublishDate the last publish date of this w s r p producer
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_wsrpProducer.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this w s r p producer.
	*
	* @param modifiedDate the modified date of this w s r p producer
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_wsrpProducer.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this w s r p producer.
	*
	* @param name the name of this w s r p producer
	*/
	@Override
	public void setName(java.lang.String name) {
		_wsrpProducer.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_wsrpProducer.setNew(n);
	}

	/**
	* Sets the portlet IDs of this w s r p producer.
	*
	* @param portletIds the portlet IDs of this w s r p producer
	*/
	@Override
	public void setPortletIds(java.lang.String portletIds) {
		_wsrpProducer.setPortletIds(portletIds);
	}

	/**
	* Sets the primary key of this w s r p producer.
	*
	* @param primaryKey the primary key of this w s r p producer
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_wsrpProducer.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_wsrpProducer.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the uuid of this w s r p producer.
	*
	* @param uuid the uuid of this w s r p producer
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_wsrpProducer.setUuid(uuid);
	}

	/**
	* Sets the version of this w s r p producer.
	*
	* @param version the version of this w s r p producer
	*/
	@Override
	public void setVersion(java.lang.String version) {
		_wsrpProducer.setVersion(version);
	}

	/**
	* Sets the wsrp producer ID of this w s r p producer.
	*
	* @param wsrpProducerId the wsrp producer ID of this w s r p producer
	*/
	@Override
	public void setWsrpProducerId(long wsrpProducerId) {
		_wsrpProducer.setWsrpProducerId(wsrpProducerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WSRPProducerWrapper)) {
			return false;
		}

		WSRPProducerWrapper wsrpProducerWrapper = (WSRPProducerWrapper)obj;

		if (Objects.equals(_wsrpProducer, wsrpProducerWrapper._wsrpProducer)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _wsrpProducer.getStagedModelType();
	}

	@Override
	public WSRPProducer getWrappedModel() {
		return _wsrpProducer;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _wsrpProducer.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _wsrpProducer.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_wsrpProducer.resetOriginalValues();
	}

	private final WSRPProducer _wsrpProducer;
}