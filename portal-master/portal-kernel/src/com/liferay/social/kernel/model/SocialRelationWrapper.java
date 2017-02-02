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

package com.liferay.social.kernel.model;

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
 * This class is a wrapper for {@link SocialRelation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialRelation
 * @generated
 */
@ProviderType
public class SocialRelationWrapper implements SocialRelation,
	ModelWrapper<SocialRelation> {
	public SocialRelationWrapper(SocialRelation socialRelation) {
		_socialRelation = socialRelation;
	}

	@Override
	public Class<?> getModelClass() {
		return SocialRelation.class;
	}

	@Override
	public String getModelClassName() {
		return SocialRelation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("relationId", getRelationId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("userId1", getUserId1());
		attributes.put("userId2", getUserId2());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long relationId = (Long)attributes.get("relationId");

		if (relationId != null) {
			setRelationId(relationId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long createDate = (Long)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long userId1 = (Long)attributes.get("userId1");

		if (userId1 != null) {
			setUserId1(userId1);
		}

		Long userId2 = (Long)attributes.get("userId2");

		if (userId2 != null) {
			setUserId2(userId2);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	@Override
	public SocialRelation toEscapedModel() {
		return new SocialRelationWrapper(_socialRelation.toEscapedModel());
	}

	@Override
	public SocialRelation toUnescapedModel() {
		return new SocialRelationWrapper(_socialRelation.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _socialRelation.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _socialRelation.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _socialRelation.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _socialRelation.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SocialRelation> toCacheModel() {
		return _socialRelation.toCacheModel();
	}

	@Override
	public int compareTo(SocialRelation socialRelation) {
		return _socialRelation.compareTo(socialRelation);
	}

	/**
	* Returns the type of this social relation.
	*
	* @return the type of this social relation
	*/
	@Override
	public int getType() {
		return _socialRelation.getType();
	}

	@Override
	public int hashCode() {
		return _socialRelation.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _socialRelation.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SocialRelationWrapper((SocialRelation)_socialRelation.clone());
	}

	/**
	* Returns the uuid of this social relation.
	*
	* @return the uuid of this social relation
	*/
	@Override
	public java.lang.String getUuid() {
		return _socialRelation.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _socialRelation.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _socialRelation.toXmlString();
	}

	/**
	* Returns the company ID of this social relation.
	*
	* @return the company ID of this social relation
	*/
	@Override
	public long getCompanyId() {
		return _socialRelation.getCompanyId();
	}

	/**
	* Returns the create date of this social relation.
	*
	* @return the create date of this social relation
	*/
	@Override
	public long getCreateDate() {
		return _socialRelation.getCreateDate();
	}

	/**
	* Returns the primary key of this social relation.
	*
	* @return the primary key of this social relation
	*/
	@Override
	public long getPrimaryKey() {
		return _socialRelation.getPrimaryKey();
	}

	/**
	* Returns the relation ID of this social relation.
	*
	* @return the relation ID of this social relation
	*/
	@Override
	public long getRelationId() {
		return _socialRelation.getRelationId();
	}

	/**
	* Returns the user id1 of this social relation.
	*
	* @return the user id1 of this social relation
	*/
	@Override
	public long getUserId1() {
		return _socialRelation.getUserId1();
	}

	/**
	* Returns the user id2 of this social relation.
	*
	* @return the user id2 of this social relation
	*/
	@Override
	public long getUserId2() {
		return _socialRelation.getUserId2();
	}

	@Override
	public void persist() {
		_socialRelation.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_socialRelation.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this social relation.
	*
	* @param companyId the company ID of this social relation
	*/
	@Override
	public void setCompanyId(long companyId) {
		_socialRelation.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this social relation.
	*
	* @param createDate the create date of this social relation
	*/
	@Override
	public void setCreateDate(long createDate) {
		_socialRelation.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_socialRelation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_socialRelation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_socialRelation.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_socialRelation.setNew(n);
	}

	/**
	* Sets the primary key of this social relation.
	*
	* @param primaryKey the primary key of this social relation
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_socialRelation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_socialRelation.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the relation ID of this social relation.
	*
	* @param relationId the relation ID of this social relation
	*/
	@Override
	public void setRelationId(long relationId) {
		_socialRelation.setRelationId(relationId);
	}

	/**
	* Sets the type of this social relation.
	*
	* @param type the type of this social relation
	*/
	@Override
	public void setType(int type) {
		_socialRelation.setType(type);
	}

	/**
	* Sets the user id1 of this social relation.
	*
	* @param userId1 the user id1 of this social relation
	*/
	@Override
	public void setUserId1(long userId1) {
		_socialRelation.setUserId1(userId1);
	}

	/**
	* Sets the user id2 of this social relation.
	*
	* @param userId2 the user id2 of this social relation
	*/
	@Override
	public void setUserId2(long userId2) {
		_socialRelation.setUserId2(userId2);
	}

	/**
	* Sets the uuid of this social relation.
	*
	* @param uuid the uuid of this social relation
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_socialRelation.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SocialRelationWrapper)) {
			return false;
		}

		SocialRelationWrapper socialRelationWrapper = (SocialRelationWrapper)obj;

		if (Objects.equals(_socialRelation,
					socialRelationWrapper._socialRelation)) {
			return true;
		}

		return false;
	}

	@Override
	public SocialRelation getWrappedModel() {
		return _socialRelation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _socialRelation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _socialRelation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_socialRelation.resetOriginalValues();
	}

	private final SocialRelation _socialRelation;
}