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
 * This class is a wrapper for {@link SocialActivity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivity
 * @generated
 */
@ProviderType
public class SocialActivityWrapper implements SocialActivity,
	ModelWrapper<SocialActivity> {
	public SocialActivityWrapper(SocialActivity socialActivity) {
		_socialActivity = socialActivity;
	}

	@Override
	public Class<?> getModelClass() {
		return SocialActivity.class;
	}

	@Override
	public String getModelClassName() {
		return SocialActivity.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("activityId", getActivityId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("activitySetId", getActivitySetId());
		attributes.put("mirrorActivityId", getMirrorActivityId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("parentClassNameId", getParentClassNameId());
		attributes.put("parentClassPK", getParentClassPK());
		attributes.put("type", getType());
		attributes.put("extraData", getExtraData());
		attributes.put("receiverUserId", getReceiverUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long activityId = (Long)attributes.get("activityId");

		if (activityId != null) {
			setActivityId(activityId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long createDate = (Long)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long activitySetId = (Long)attributes.get("activitySetId");

		if (activitySetId != null) {
			setActivitySetId(activitySetId);
		}

		Long mirrorActivityId = (Long)attributes.get("mirrorActivityId");

		if (mirrorActivityId != null) {
			setMirrorActivityId(mirrorActivityId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long parentClassNameId = (Long)attributes.get("parentClassNameId");

		if (parentClassNameId != null) {
			setParentClassNameId(parentClassNameId);
		}

		Long parentClassPK = (Long)attributes.get("parentClassPK");

		if (parentClassPK != null) {
			setParentClassPK(parentClassPK);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String extraData = (String)attributes.get("extraData");

		if (extraData != null) {
			setExtraData(extraData);
		}

		Long receiverUserId = (Long)attributes.get("receiverUserId");

		if (receiverUserId != null) {
			setReceiverUserId(receiverUserId);
		}
	}

	@Override
	public SocialActivity toEscapedModel() {
		return new SocialActivityWrapper(_socialActivity.toEscapedModel());
	}

	@Override
	public SocialActivity toUnescapedModel() {
		return new SocialActivityWrapper(_socialActivity.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _socialActivity.isCachedModel();
	}

	@Override
	public boolean isClassName(java.lang.String className) {
		return _socialActivity.isClassName(className);
	}

	@Override
	public boolean isEscapedModel() {
		return _socialActivity.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _socialActivity.isNew();
	}

	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _socialActivity.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _socialActivity.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SocialActivity> toCacheModel() {
		return _socialActivity.toCacheModel();
	}

	@Override
	public int compareTo(SocialActivity socialActivity) {
		return _socialActivity.compareTo(socialActivity);
	}

	/**
	* Returns the type of this social activity.
	*
	* @return the type of this social activity
	*/
	@Override
	public int getType() {
		return _socialActivity.getType();
	}

	@Override
	public int hashCode() {
		return _socialActivity.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _socialActivity.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SocialActivityWrapper((SocialActivity)_socialActivity.clone());
	}

	/**
	* Returns the fully qualified class name of this social activity.
	*
	* @return the fully qualified class name of this social activity
	*/
	@Override
	public java.lang.String getClassName() {
		return _socialActivity.getClassName();
	}

	/**
	* Returns the extra data of this social activity.
	*
	* @return the extra data of this social activity
	*/
	@Override
	public java.lang.String getExtraData() {
		return _socialActivity.getExtraData();
	}

	@Override
	public java.lang.String getExtraDataValue(java.lang.String key)
		throws com.liferay.portal.kernel.json.JSONException {
		return _socialActivity.getExtraDataValue(key);
	}

	@Override
	public java.lang.String getExtraDataValue(java.lang.String key,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.json.JSONException {
		return _socialActivity.getExtraDataValue(key, locale);
	}

	/**
	* Returns the receiver user uuid of this social activity.
	*
	* @return the receiver user uuid of this social activity
	*/
	@Override
	public java.lang.String getReceiverUserUuid() {
		return _socialActivity.getReceiverUserUuid();
	}

	/**
	* Returns the user uuid of this social activity.
	*
	* @return the user uuid of this social activity
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _socialActivity.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _socialActivity.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _socialActivity.toXmlString();
	}

	/**
	* Returns the activity ID of this social activity.
	*
	* @return the activity ID of this social activity
	*/
	@Override
	public long getActivityId() {
		return _socialActivity.getActivityId();
	}

	/**
	* Returns the activity set ID of this social activity.
	*
	* @return the activity set ID of this social activity
	*/
	@Override
	public long getActivitySetId() {
		return _socialActivity.getActivitySetId();
	}

	/**
	* Returns the class name ID of this social activity.
	*
	* @return the class name ID of this social activity
	*/
	@Override
	public long getClassNameId() {
		return _socialActivity.getClassNameId();
	}

	/**
	* Returns the class p k of this social activity.
	*
	* @return the class p k of this social activity
	*/
	@Override
	public long getClassPK() {
		return _socialActivity.getClassPK();
	}

	/**
	* Returns the company ID of this social activity.
	*
	* @return the company ID of this social activity
	*/
	@Override
	public long getCompanyId() {
		return _socialActivity.getCompanyId();
	}

	/**
	* Returns the create date of this social activity.
	*
	* @return the create date of this social activity
	*/
	@Override
	public long getCreateDate() {
		return _socialActivity.getCreateDate();
	}

	/**
	* Returns the group ID of this social activity.
	*
	* @return the group ID of this social activity
	*/
	@Override
	public long getGroupId() {
		return _socialActivity.getGroupId();
	}

	/**
	* Returns the mirror activity ID of this social activity.
	*
	* @return the mirror activity ID of this social activity
	*/
	@Override
	public long getMirrorActivityId() {
		return _socialActivity.getMirrorActivityId();
	}

	/**
	* Returns the parent class name ID of this social activity.
	*
	* @return the parent class name ID of this social activity
	*/
	@Override
	public long getParentClassNameId() {
		return _socialActivity.getParentClassNameId();
	}

	/**
	* Returns the parent class p k of this social activity.
	*
	* @return the parent class p k of this social activity
	*/
	@Override
	public long getParentClassPK() {
		return _socialActivity.getParentClassPK();
	}

	/**
	* Returns the primary key of this social activity.
	*
	* @return the primary key of this social activity
	*/
	@Override
	public long getPrimaryKey() {
		return _socialActivity.getPrimaryKey();
	}

	/**
	* Returns the receiver user ID of this social activity.
	*
	* @return the receiver user ID of this social activity
	*/
	@Override
	public long getReceiverUserId() {
		return _socialActivity.getReceiverUserId();
	}

	/**
	* Returns the user ID of this social activity.
	*
	* @return the user ID of this social activity
	*/
	@Override
	public long getUserId() {
		return _socialActivity.getUserId();
	}

	@Override
	public void persist() {
		_socialActivity.persist();
	}

	/**
	* Sets the activity ID of this social activity.
	*
	* @param activityId the activity ID of this social activity
	*/
	@Override
	public void setActivityId(long activityId) {
		_socialActivity.setActivityId(activityId);
	}

	/**
	* Sets the activity set ID of this social activity.
	*
	* @param activitySetId the activity set ID of this social activity
	*/
	@Override
	public void setActivitySetId(long activitySetId) {
		_socialActivity.setActivitySetId(activitySetId);
	}

	@Override
	public void setAssetEntry(
		com.liferay.asset.kernel.model.AssetEntry assetEntry) {
		_socialActivity.setAssetEntry(assetEntry);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_socialActivity.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_socialActivity.setClassName(className);
	}

	/**
	* Sets the class name ID of this social activity.
	*
	* @param classNameId the class name ID of this social activity
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_socialActivity.setClassNameId(classNameId);
	}

	/**
	* Sets the class p k of this social activity.
	*
	* @param classPK the class p k of this social activity
	*/
	@Override
	public void setClassPK(long classPK) {
		_socialActivity.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this social activity.
	*
	* @param companyId the company ID of this social activity
	*/
	@Override
	public void setCompanyId(long companyId) {
		_socialActivity.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this social activity.
	*
	* @param createDate the create date of this social activity
	*/
	@Override
	public void setCreateDate(long createDate) {
		_socialActivity.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_socialActivity.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_socialActivity.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_socialActivity.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extra data of this social activity.
	*
	* @param extraData the extra data of this social activity
	*/
	@Override
	public void setExtraData(java.lang.String extraData) {
		_socialActivity.setExtraData(extraData);
	}

	@Override
	public void setExtraDataValue(java.lang.String key, java.lang.String value)
		throws com.liferay.portal.kernel.json.JSONException {
		_socialActivity.setExtraDataValue(key, value);
	}

	/**
	* Sets the group ID of this social activity.
	*
	* @param groupId the group ID of this social activity
	*/
	@Override
	public void setGroupId(long groupId) {
		_socialActivity.setGroupId(groupId);
	}

	/**
	* Sets the mirror activity ID of this social activity.
	*
	* @param mirrorActivityId the mirror activity ID of this social activity
	*/
	@Override
	public void setMirrorActivityId(long mirrorActivityId) {
		_socialActivity.setMirrorActivityId(mirrorActivityId);
	}

	@Override
	public void setNew(boolean n) {
		_socialActivity.setNew(n);
	}

	/**
	* Sets the parent class name ID of this social activity.
	*
	* @param parentClassNameId the parent class name ID of this social activity
	*/
	@Override
	public void setParentClassNameId(long parentClassNameId) {
		_socialActivity.setParentClassNameId(parentClassNameId);
	}

	/**
	* Sets the parent class p k of this social activity.
	*
	* @param parentClassPK the parent class p k of this social activity
	*/
	@Override
	public void setParentClassPK(long parentClassPK) {
		_socialActivity.setParentClassPK(parentClassPK);
	}

	/**
	* Sets the primary key of this social activity.
	*
	* @param primaryKey the primary key of this social activity
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_socialActivity.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_socialActivity.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the receiver user ID of this social activity.
	*
	* @param receiverUserId the receiver user ID of this social activity
	*/
	@Override
	public void setReceiverUserId(long receiverUserId) {
		_socialActivity.setReceiverUserId(receiverUserId);
	}

	/**
	* Sets the receiver user uuid of this social activity.
	*
	* @param receiverUserUuid the receiver user uuid of this social activity
	*/
	@Override
	public void setReceiverUserUuid(java.lang.String receiverUserUuid) {
		_socialActivity.setReceiverUserUuid(receiverUserUuid);
	}

	/**
	* Sets the type of this social activity.
	*
	* @param type the type of this social activity
	*/
	@Override
	public void setType(int type) {
		_socialActivity.setType(type);
	}

	/**
	* Sets the user ID of this social activity.
	*
	* @param userId the user ID of this social activity
	*/
	@Override
	public void setUserId(long userId) {
		_socialActivity.setUserId(userId);
	}

	/**
	* Sets the user uuid of this social activity.
	*
	* @param userUuid the user uuid of this social activity
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_socialActivity.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SocialActivityWrapper)) {
			return false;
		}

		SocialActivityWrapper socialActivityWrapper = (SocialActivityWrapper)obj;

		if (Objects.equals(_socialActivity,
					socialActivityWrapper._socialActivity)) {
			return true;
		}

		return false;
	}

	@Override
	public SocialActivity getWrappedModel() {
		return _socialActivity;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _socialActivity.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _socialActivity.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_socialActivity.resetOriginalValues();
	}

	private final SocialActivity _socialActivity;
}