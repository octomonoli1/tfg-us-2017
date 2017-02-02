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

package com.liferay.asset.kernel.model;

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
 * This class is a wrapper for {@link AssetLink}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetLink
 * @generated
 */
@ProviderType
public class AssetLinkWrapper implements AssetLink, ModelWrapper<AssetLink> {
	public AssetLinkWrapper(AssetLink assetLink) {
		_assetLink = assetLink;
	}

	@Override
	public Class<?> getModelClass() {
		return AssetLink.class;
	}

	@Override
	public String getModelClassName() {
		return AssetLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("linkId", getLinkId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("entryId1", getEntryId1());
		attributes.put("entryId2", getEntryId2());
		attributes.put("type", getType());
		attributes.put("weight", getWeight());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long linkId = (Long)attributes.get("linkId");

		if (linkId != null) {
			setLinkId(linkId);
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

		Long entryId1 = (Long)attributes.get("entryId1");

		if (entryId1 != null) {
			setEntryId1(entryId1);
		}

		Long entryId2 = (Long)attributes.get("entryId2");

		if (entryId2 != null) {
			setEntryId2(entryId2);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer weight = (Integer)attributes.get("weight");

		if (weight != null) {
			setWeight(weight);
		}
	}

	@Override
	public AssetLink toEscapedModel() {
		return new AssetLinkWrapper(_assetLink.toEscapedModel());
	}

	@Override
	public AssetLink toUnescapedModel() {
		return new AssetLinkWrapper(_assetLink.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _assetLink.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _assetLink.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _assetLink.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _assetLink.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AssetLink> toCacheModel() {
		return _assetLink.toCacheModel();
	}

	@Override
	public int compareTo(AssetLink assetLink) {
		return _assetLink.compareTo(assetLink);
	}

	/**
	* Returns the type of this asset link.
	*
	* @return the type of this asset link
	*/
	@Override
	public int getType() {
		return _assetLink.getType();
	}

	/**
	* Returns the weight of this asset link.
	*
	* @return the weight of this asset link
	*/
	@Override
	public int getWeight() {
		return _assetLink.getWeight();
	}

	@Override
	public int hashCode() {
		return _assetLink.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetLink.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AssetLinkWrapper((AssetLink)_assetLink.clone());
	}

	/**
	* Returns the user name of this asset link.
	*
	* @return the user name of this asset link
	*/
	@Override
	public java.lang.String getUserName() {
		return _assetLink.getUserName();
	}

	/**
	* Returns the user uuid of this asset link.
	*
	* @return the user uuid of this asset link
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _assetLink.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _assetLink.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _assetLink.toXmlString();
	}

	/**
	* Returns the create date of this asset link.
	*
	* @return the create date of this asset link
	*/
	@Override
	public Date getCreateDate() {
		return _assetLink.getCreateDate();
	}

	/**
	* Returns the company ID of this asset link.
	*
	* @return the company ID of this asset link
	*/
	@Override
	public long getCompanyId() {
		return _assetLink.getCompanyId();
	}

	/**
	* Returns the entry id1 of this asset link.
	*
	* @return the entry id1 of this asset link
	*/
	@Override
	public long getEntryId1() {
		return _assetLink.getEntryId1();
	}

	/**
	* Returns the entry id2 of this asset link.
	*
	* @return the entry id2 of this asset link
	*/
	@Override
	public long getEntryId2() {
		return _assetLink.getEntryId2();
	}

	/**
	* Returns the link ID of this asset link.
	*
	* @return the link ID of this asset link
	*/
	@Override
	public long getLinkId() {
		return _assetLink.getLinkId();
	}

	/**
	* Returns the primary key of this asset link.
	*
	* @return the primary key of this asset link
	*/
	@Override
	public long getPrimaryKey() {
		return _assetLink.getPrimaryKey();
	}

	/**
	* Returns the user ID of this asset link.
	*
	* @return the user ID of this asset link
	*/
	@Override
	public long getUserId() {
		return _assetLink.getUserId();
	}

	@Override
	public void persist() {
		_assetLink.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assetLink.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this asset link.
	*
	* @param companyId the company ID of this asset link
	*/
	@Override
	public void setCompanyId(long companyId) {
		_assetLink.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this asset link.
	*
	* @param createDate the create date of this asset link
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_assetLink.setCreateDate(createDate);
	}

	/**
	* Sets the entry id1 of this asset link.
	*
	* @param entryId1 the entry id1 of this asset link
	*/
	@Override
	public void setEntryId1(long entryId1) {
		_assetLink.setEntryId1(entryId1);
	}

	/**
	* Sets the entry id2 of this asset link.
	*
	* @param entryId2 the entry id2 of this asset link
	*/
	@Override
	public void setEntryId2(long entryId2) {
		_assetLink.setEntryId2(entryId2);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_assetLink.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_assetLink.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_assetLink.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the link ID of this asset link.
	*
	* @param linkId the link ID of this asset link
	*/
	@Override
	public void setLinkId(long linkId) {
		_assetLink.setLinkId(linkId);
	}

	@Override
	public void setNew(boolean n) {
		_assetLink.setNew(n);
	}

	/**
	* Sets the primary key of this asset link.
	*
	* @param primaryKey the primary key of this asset link
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_assetLink.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_assetLink.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type of this asset link.
	*
	* @param type the type of this asset link
	*/
	@Override
	public void setType(int type) {
		_assetLink.setType(type);
	}

	/**
	* Sets the user ID of this asset link.
	*
	* @param userId the user ID of this asset link
	*/
	@Override
	public void setUserId(long userId) {
		_assetLink.setUserId(userId);
	}

	/**
	* Sets the user name of this asset link.
	*
	* @param userName the user name of this asset link
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_assetLink.setUserName(userName);
	}

	/**
	* Sets the user uuid of this asset link.
	*
	* @param userUuid the user uuid of this asset link
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_assetLink.setUserUuid(userUuid);
	}

	/**
	* Sets the weight of this asset link.
	*
	* @param weight the weight of this asset link
	*/
	@Override
	public void setWeight(int weight) {
		_assetLink.setWeight(weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetLinkWrapper)) {
			return false;
		}

		AssetLinkWrapper assetLinkWrapper = (AssetLinkWrapper)obj;

		if (Objects.equals(_assetLink, assetLinkWrapper._assetLink)) {
			return true;
		}

		return false;
	}

	@Override
	public AssetLink getWrappedModel() {
		return _assetLink;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _assetLink.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _assetLink.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_assetLink.resetOriginalValues();
	}

	private final AssetLink _assetLink;
}