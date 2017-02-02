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
 * This class is a wrapper for {@link RecentLayoutSetBranch}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutSetBranch
 * @generated
 */
@ProviderType
public class RecentLayoutSetBranchWrapper implements RecentLayoutSetBranch,
	ModelWrapper<RecentLayoutSetBranch> {
	public RecentLayoutSetBranchWrapper(
		RecentLayoutSetBranch recentLayoutSetBranch) {
		_recentLayoutSetBranch = recentLayoutSetBranch;
	}

	@Override
	public Class<?> getModelClass() {
		return RecentLayoutSetBranch.class;
	}

	@Override
	public String getModelClassName() {
		return RecentLayoutSetBranch.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("recentLayoutSetBranchId", getRecentLayoutSetBranchId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("layoutSetBranchId", getLayoutSetBranchId());
		attributes.put("layoutSetId", getLayoutSetId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long recentLayoutSetBranchId = (Long)attributes.get(
				"recentLayoutSetBranchId");

		if (recentLayoutSetBranchId != null) {
			setRecentLayoutSetBranchId(recentLayoutSetBranchId);
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

		Long layoutSetBranchId = (Long)attributes.get("layoutSetBranchId");

		if (layoutSetBranchId != null) {
			setLayoutSetBranchId(layoutSetBranchId);
		}

		Long layoutSetId = (Long)attributes.get("layoutSetId");

		if (layoutSetId != null) {
			setLayoutSetId(layoutSetId);
		}
	}

	@Override
	public CacheModel<RecentLayoutSetBranch> toCacheModel() {
		return _recentLayoutSetBranch.toCacheModel();
	}

	@Override
	public RecentLayoutSetBranch toEscapedModel() {
		return new RecentLayoutSetBranchWrapper(_recentLayoutSetBranch.toEscapedModel());
	}

	@Override
	public RecentLayoutSetBranch toUnescapedModel() {
		return new RecentLayoutSetBranchWrapper(_recentLayoutSetBranch.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _recentLayoutSetBranch.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _recentLayoutSetBranch.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _recentLayoutSetBranch.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _recentLayoutSetBranch.getExpandoBridge();
	}

	@Override
	public int compareTo(RecentLayoutSetBranch recentLayoutSetBranch) {
		return _recentLayoutSetBranch.compareTo(recentLayoutSetBranch);
	}

	@Override
	public int hashCode() {
		return _recentLayoutSetBranch.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _recentLayoutSetBranch.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new RecentLayoutSetBranchWrapper((RecentLayoutSetBranch)_recentLayoutSetBranch.clone());
	}

	/**
	* Returns the user uuid of this recent layout set branch.
	*
	* @return the user uuid of this recent layout set branch
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _recentLayoutSetBranch.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _recentLayoutSetBranch.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _recentLayoutSetBranch.toXmlString();
	}

	/**
	* Returns the company ID of this recent layout set branch.
	*
	* @return the company ID of this recent layout set branch
	*/
	@Override
	public long getCompanyId() {
		return _recentLayoutSetBranch.getCompanyId();
	}

	/**
	* Returns the group ID of this recent layout set branch.
	*
	* @return the group ID of this recent layout set branch
	*/
	@Override
	public long getGroupId() {
		return _recentLayoutSetBranch.getGroupId();
	}

	/**
	* Returns the layout set branch ID of this recent layout set branch.
	*
	* @return the layout set branch ID of this recent layout set branch
	*/
	@Override
	public long getLayoutSetBranchId() {
		return _recentLayoutSetBranch.getLayoutSetBranchId();
	}

	/**
	* Returns the layout set ID of this recent layout set branch.
	*
	* @return the layout set ID of this recent layout set branch
	*/
	@Override
	public long getLayoutSetId() {
		return _recentLayoutSetBranch.getLayoutSetId();
	}

	/**
	* Returns the mvcc version of this recent layout set branch.
	*
	* @return the mvcc version of this recent layout set branch
	*/
	@Override
	public long getMvccVersion() {
		return _recentLayoutSetBranch.getMvccVersion();
	}

	/**
	* Returns the primary key of this recent layout set branch.
	*
	* @return the primary key of this recent layout set branch
	*/
	@Override
	public long getPrimaryKey() {
		return _recentLayoutSetBranch.getPrimaryKey();
	}

	/**
	* Returns the recent layout set branch ID of this recent layout set branch.
	*
	* @return the recent layout set branch ID of this recent layout set branch
	*/
	@Override
	public long getRecentLayoutSetBranchId() {
		return _recentLayoutSetBranch.getRecentLayoutSetBranchId();
	}

	/**
	* Returns the user ID of this recent layout set branch.
	*
	* @return the user ID of this recent layout set branch
	*/
	@Override
	public long getUserId() {
		return _recentLayoutSetBranch.getUserId();
	}

	@Override
	public void persist() {
		_recentLayoutSetBranch.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_recentLayoutSetBranch.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this recent layout set branch.
	*
	* @param companyId the company ID of this recent layout set branch
	*/
	@Override
	public void setCompanyId(long companyId) {
		_recentLayoutSetBranch.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_recentLayoutSetBranch.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_recentLayoutSetBranch.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_recentLayoutSetBranch.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this recent layout set branch.
	*
	* @param groupId the group ID of this recent layout set branch
	*/
	@Override
	public void setGroupId(long groupId) {
		_recentLayoutSetBranch.setGroupId(groupId);
	}

	/**
	* Sets the layout set branch ID of this recent layout set branch.
	*
	* @param layoutSetBranchId the layout set branch ID of this recent layout set branch
	*/
	@Override
	public void setLayoutSetBranchId(long layoutSetBranchId) {
		_recentLayoutSetBranch.setLayoutSetBranchId(layoutSetBranchId);
	}

	/**
	* Sets the layout set ID of this recent layout set branch.
	*
	* @param layoutSetId the layout set ID of this recent layout set branch
	*/
	@Override
	public void setLayoutSetId(long layoutSetId) {
		_recentLayoutSetBranch.setLayoutSetId(layoutSetId);
	}

	/**
	* Sets the mvcc version of this recent layout set branch.
	*
	* @param mvccVersion the mvcc version of this recent layout set branch
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_recentLayoutSetBranch.setMvccVersion(mvccVersion);
	}

	@Override
	public void setNew(boolean n) {
		_recentLayoutSetBranch.setNew(n);
	}

	/**
	* Sets the primary key of this recent layout set branch.
	*
	* @param primaryKey the primary key of this recent layout set branch
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_recentLayoutSetBranch.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_recentLayoutSetBranch.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the recent layout set branch ID of this recent layout set branch.
	*
	* @param recentLayoutSetBranchId the recent layout set branch ID of this recent layout set branch
	*/
	@Override
	public void setRecentLayoutSetBranchId(long recentLayoutSetBranchId) {
		_recentLayoutSetBranch.setRecentLayoutSetBranchId(recentLayoutSetBranchId);
	}

	/**
	* Sets the user ID of this recent layout set branch.
	*
	* @param userId the user ID of this recent layout set branch
	*/
	@Override
	public void setUserId(long userId) {
		_recentLayoutSetBranch.setUserId(userId);
	}

	/**
	* Sets the user uuid of this recent layout set branch.
	*
	* @param userUuid the user uuid of this recent layout set branch
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_recentLayoutSetBranch.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RecentLayoutSetBranchWrapper)) {
			return false;
		}

		RecentLayoutSetBranchWrapper recentLayoutSetBranchWrapper = (RecentLayoutSetBranchWrapper)obj;

		if (Objects.equals(_recentLayoutSetBranch,
					recentLayoutSetBranchWrapper._recentLayoutSetBranch)) {
			return true;
		}

		return false;
	}

	@Override
	public RecentLayoutSetBranch getWrappedModel() {
		return _recentLayoutSetBranch;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _recentLayoutSetBranch.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _recentLayoutSetBranch.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_recentLayoutSetBranch.resetOriginalValues();
	}

	private final RecentLayoutSetBranch _recentLayoutSetBranch;
}