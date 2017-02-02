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

package com.liferay.journal.model;

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
 * This class is a wrapper for {@link JournalContentSearch}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalContentSearch
 * @generated
 */
@ProviderType
public class JournalContentSearchWrapper implements JournalContentSearch,
	ModelWrapper<JournalContentSearch> {
	public JournalContentSearchWrapper(
		JournalContentSearch journalContentSearch) {
		_journalContentSearch = journalContentSearch;
	}

	@Override
	public Class<?> getModelClass() {
		return JournalContentSearch.class;
	}

	@Override
	public String getModelClassName() {
		return JournalContentSearch.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contentSearchId", getContentSearchId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("privateLayout", getPrivateLayout());
		attributes.put("layoutId", getLayoutId());
		attributes.put("portletId", getPortletId());
		attributes.put("articleId", getArticleId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long contentSearchId = (Long)attributes.get("contentSearchId");

		if (contentSearchId != null) {
			setContentSearchId(contentSearchId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Boolean privateLayout = (Boolean)attributes.get("privateLayout");

		if (privateLayout != null) {
			setPrivateLayout(privateLayout);
		}

		Long layoutId = (Long)attributes.get("layoutId");

		if (layoutId != null) {
			setLayoutId(layoutId);
		}

		String portletId = (String)attributes.get("portletId");

		if (portletId != null) {
			setPortletId(portletId);
		}

		String articleId = (String)attributes.get("articleId");

		if (articleId != null) {
			setArticleId(articleId);
		}
	}

	@Override
	public JournalContentSearch toEscapedModel() {
		return new JournalContentSearchWrapper(_journalContentSearch.toEscapedModel());
	}

	@Override
	public JournalContentSearch toUnescapedModel() {
		return new JournalContentSearchWrapper(_journalContentSearch.toUnescapedModel());
	}

	/**
	* Returns the private layout of this journal content search.
	*
	* @return the private layout of this journal content search
	*/
	@Override
	public boolean getPrivateLayout() {
		return _journalContentSearch.getPrivateLayout();
	}

	@Override
	public boolean isCachedModel() {
		return _journalContentSearch.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _journalContentSearch.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _journalContentSearch.isNew();
	}

	/**
	* Returns <code>true</code> if this journal content search is private layout.
	*
	* @return <code>true</code> if this journal content search is private layout; <code>false</code> otherwise
	*/
	@Override
	public boolean isPrivateLayout() {
		return _journalContentSearch.isPrivateLayout();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _journalContentSearch.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<JournalContentSearch> toCacheModel() {
		return _journalContentSearch.toCacheModel();
	}

	@Override
	public int compareTo(JournalContentSearch journalContentSearch) {
		return _journalContentSearch.compareTo(journalContentSearch);
	}

	@Override
	public int hashCode() {
		return _journalContentSearch.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _journalContentSearch.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new JournalContentSearchWrapper((JournalContentSearch)_journalContentSearch.clone());
	}

	/**
	* Returns the article ID of this journal content search.
	*
	* @return the article ID of this journal content search
	*/
	@Override
	public java.lang.String getArticleId() {
		return _journalContentSearch.getArticleId();
	}

	/**
	* Returns the portlet ID of this journal content search.
	*
	* @return the portlet ID of this journal content search
	*/
	@Override
	public java.lang.String getPortletId() {
		return _journalContentSearch.getPortletId();
	}

	@Override
	public java.lang.String toString() {
		return _journalContentSearch.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _journalContentSearch.toXmlString();
	}

	/**
	* Returns the company ID of this journal content search.
	*
	* @return the company ID of this journal content search
	*/
	@Override
	public long getCompanyId() {
		return _journalContentSearch.getCompanyId();
	}

	/**
	* Returns the content search ID of this journal content search.
	*
	* @return the content search ID of this journal content search
	*/
	@Override
	public long getContentSearchId() {
		return _journalContentSearch.getContentSearchId();
	}

	/**
	* Returns the group ID of this journal content search.
	*
	* @return the group ID of this journal content search
	*/
	@Override
	public long getGroupId() {
		return _journalContentSearch.getGroupId();
	}

	/**
	* Returns the layout ID of this journal content search.
	*
	* @return the layout ID of this journal content search
	*/
	@Override
	public long getLayoutId() {
		return _journalContentSearch.getLayoutId();
	}

	/**
	* Returns the primary key of this journal content search.
	*
	* @return the primary key of this journal content search
	*/
	@Override
	public long getPrimaryKey() {
		return _journalContentSearch.getPrimaryKey();
	}

	@Override
	public void persist() {
		_journalContentSearch.persist();
	}

	/**
	* Sets the article ID of this journal content search.
	*
	* @param articleId the article ID of this journal content search
	*/
	@Override
	public void setArticleId(java.lang.String articleId) {
		_journalContentSearch.setArticleId(articleId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_journalContentSearch.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this journal content search.
	*
	* @param companyId the company ID of this journal content search
	*/
	@Override
	public void setCompanyId(long companyId) {
		_journalContentSearch.setCompanyId(companyId);
	}

	/**
	* Sets the content search ID of this journal content search.
	*
	* @param contentSearchId the content search ID of this journal content search
	*/
	@Override
	public void setContentSearchId(long contentSearchId) {
		_journalContentSearch.setContentSearchId(contentSearchId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_journalContentSearch.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_journalContentSearch.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_journalContentSearch.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this journal content search.
	*
	* @param groupId the group ID of this journal content search
	*/
	@Override
	public void setGroupId(long groupId) {
		_journalContentSearch.setGroupId(groupId);
	}

	/**
	* Sets the layout ID of this journal content search.
	*
	* @param layoutId the layout ID of this journal content search
	*/
	@Override
	public void setLayoutId(long layoutId) {
		_journalContentSearch.setLayoutId(layoutId);
	}

	@Override
	public void setNew(boolean n) {
		_journalContentSearch.setNew(n);
	}

	/**
	* Sets the portlet ID of this journal content search.
	*
	* @param portletId the portlet ID of this journal content search
	*/
	@Override
	public void setPortletId(java.lang.String portletId) {
		_journalContentSearch.setPortletId(portletId);
	}

	/**
	* Sets the primary key of this journal content search.
	*
	* @param primaryKey the primary key of this journal content search
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_journalContentSearch.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_journalContentSearch.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this journal content search is private layout.
	*
	* @param privateLayout the private layout of this journal content search
	*/
	@Override
	public void setPrivateLayout(boolean privateLayout) {
		_journalContentSearch.setPrivateLayout(privateLayout);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JournalContentSearchWrapper)) {
			return false;
		}

		JournalContentSearchWrapper journalContentSearchWrapper = (JournalContentSearchWrapper)obj;

		if (Objects.equals(_journalContentSearch,
					journalContentSearchWrapper._journalContentSearch)) {
			return true;
		}

		return false;
	}

	@Override
	public JournalContentSearch getWrappedModel() {
		return _journalContentSearch;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _journalContentSearch.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _journalContentSearch.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_journalContentSearch.resetOriginalValues();
	}

	private final JournalContentSearch _journalContentSearch;
}