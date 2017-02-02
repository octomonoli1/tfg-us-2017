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
 * This class is a wrapper for {@link JournalArticleImage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleImage
 * @generated
 */
@ProviderType
public class JournalArticleImageWrapper implements JournalArticleImage,
	ModelWrapper<JournalArticleImage> {
	public JournalArticleImageWrapper(JournalArticleImage journalArticleImage) {
		_journalArticleImage = journalArticleImage;
	}

	@Override
	public Class<?> getModelClass() {
		return JournalArticleImage.class;
	}

	@Override
	public String getModelClassName() {
		return JournalArticleImage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("articleImageId", getArticleImageId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("articleId", getArticleId());
		attributes.put("version", getVersion());
		attributes.put("elInstanceId", getElInstanceId());
		attributes.put("elName", getElName());
		attributes.put("languageId", getLanguageId());
		attributes.put("tempImage", getTempImage());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long articleImageId = (Long)attributes.get("articleImageId");

		if (articleImageId != null) {
			setArticleImageId(articleImageId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String articleId = (String)attributes.get("articleId");

		if (articleId != null) {
			setArticleId(articleId);
		}

		Double version = (Double)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String elInstanceId = (String)attributes.get("elInstanceId");

		if (elInstanceId != null) {
			setElInstanceId(elInstanceId);
		}

		String elName = (String)attributes.get("elName");

		if (elName != null) {
			setElName(elName);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}

		Boolean tempImage = (Boolean)attributes.get("tempImage");

		if (tempImage != null) {
			setTempImage(tempImage);
		}
	}

	@Override
	public JournalArticleImage toEscapedModel() {
		return new JournalArticleImageWrapper(_journalArticleImage.toEscapedModel());
	}

	@Override
	public JournalArticleImage toUnescapedModel() {
		return new JournalArticleImageWrapper(_journalArticleImage.toUnescapedModel());
	}

	/**
	* Returns the temp image of this journal article image.
	*
	* @return the temp image of this journal article image
	*/
	@Override
	public boolean getTempImage() {
		return _journalArticleImage.getTempImage();
	}

	@Override
	public boolean isCachedModel() {
		return _journalArticleImage.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _journalArticleImage.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _journalArticleImage.isNew();
	}

	/**
	* Returns <code>true</code> if this journal article image is temp image.
	*
	* @return <code>true</code> if this journal article image is temp image; <code>false</code> otherwise
	*/
	@Override
	public boolean isTempImage() {
		return _journalArticleImage.isTempImage();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _journalArticleImage.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<JournalArticleImage> toCacheModel() {
		return _journalArticleImage.toCacheModel();
	}

	/**
	* Returns the version of this journal article image.
	*
	* @return the version of this journal article image
	*/
	@Override
	public double getVersion() {
		return _journalArticleImage.getVersion();
	}

	@Override
	public int compareTo(JournalArticleImage journalArticleImage) {
		return _journalArticleImage.compareTo(journalArticleImage);
	}

	@Override
	public int hashCode() {
		return _journalArticleImage.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _journalArticleImage.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new JournalArticleImageWrapper((JournalArticleImage)_journalArticleImage.clone());
	}

	/**
	* Returns the article ID of this journal article image.
	*
	* @return the article ID of this journal article image
	*/
	@Override
	public java.lang.String getArticleId() {
		return _journalArticleImage.getArticleId();
	}

	/**
	* Returns the el instance ID of this journal article image.
	*
	* @return the el instance ID of this journal article image
	*/
	@Override
	public java.lang.String getElInstanceId() {
		return _journalArticleImage.getElInstanceId();
	}

	/**
	* Returns the el name of this journal article image.
	*
	* @return the el name of this journal article image
	*/
	@Override
	public java.lang.String getElName() {
		return _journalArticleImage.getElName();
	}

	/**
	* Returns the language ID of this journal article image.
	*
	* @return the language ID of this journal article image
	*/
	@Override
	public java.lang.String getLanguageId() {
		return _journalArticleImage.getLanguageId();
	}

	@Override
	public java.lang.String toString() {
		return _journalArticleImage.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _journalArticleImage.toXmlString();
	}

	/**
	* Returns the article image ID of this journal article image.
	*
	* @return the article image ID of this journal article image
	*/
	@Override
	public long getArticleImageId() {
		return _journalArticleImage.getArticleImageId();
	}

	/**
	* Returns the company ID of this journal article image.
	*
	* @return the company ID of this journal article image
	*/
	@Override
	public long getCompanyId() {
		return _journalArticleImage.getCompanyId();
	}

	/**
	* Returns the group ID of this journal article image.
	*
	* @return the group ID of this journal article image
	*/
	@Override
	public long getGroupId() {
		return _journalArticleImage.getGroupId();
	}

	/**
	* Returns the primary key of this journal article image.
	*
	* @return the primary key of this journal article image
	*/
	@Override
	public long getPrimaryKey() {
		return _journalArticleImage.getPrimaryKey();
	}

	@Override
	public void persist() {
		_journalArticleImage.persist();
	}

	/**
	* Sets the article ID of this journal article image.
	*
	* @param articleId the article ID of this journal article image
	*/
	@Override
	public void setArticleId(java.lang.String articleId) {
		_journalArticleImage.setArticleId(articleId);
	}

	/**
	* Sets the article image ID of this journal article image.
	*
	* @param articleImageId the article image ID of this journal article image
	*/
	@Override
	public void setArticleImageId(long articleImageId) {
		_journalArticleImage.setArticleImageId(articleImageId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_journalArticleImage.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this journal article image.
	*
	* @param companyId the company ID of this journal article image
	*/
	@Override
	public void setCompanyId(long companyId) {
		_journalArticleImage.setCompanyId(companyId);
	}

	/**
	* Sets the el instance ID of this journal article image.
	*
	* @param elInstanceId the el instance ID of this journal article image
	*/
	@Override
	public void setElInstanceId(java.lang.String elInstanceId) {
		_journalArticleImage.setElInstanceId(elInstanceId);
	}

	/**
	* Sets the el name of this journal article image.
	*
	* @param elName the el name of this journal article image
	*/
	@Override
	public void setElName(java.lang.String elName) {
		_journalArticleImage.setElName(elName);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_journalArticleImage.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_journalArticleImage.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_journalArticleImage.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this journal article image.
	*
	* @param groupId the group ID of this journal article image
	*/
	@Override
	public void setGroupId(long groupId) {
		_journalArticleImage.setGroupId(groupId);
	}

	/**
	* Sets the language ID of this journal article image.
	*
	* @param languageId the language ID of this journal article image
	*/
	@Override
	public void setLanguageId(java.lang.String languageId) {
		_journalArticleImage.setLanguageId(languageId);
	}

	@Override
	public void setNew(boolean n) {
		_journalArticleImage.setNew(n);
	}

	/**
	* Sets the primary key of this journal article image.
	*
	* @param primaryKey the primary key of this journal article image
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_journalArticleImage.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_journalArticleImage.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this journal article image is temp image.
	*
	* @param tempImage the temp image of this journal article image
	*/
	@Override
	public void setTempImage(boolean tempImage) {
		_journalArticleImage.setTempImage(tempImage);
	}

	/**
	* Sets the version of this journal article image.
	*
	* @param version the version of this journal article image
	*/
	@Override
	public void setVersion(double version) {
		_journalArticleImage.setVersion(version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JournalArticleImageWrapper)) {
			return false;
		}

		JournalArticleImageWrapper journalArticleImageWrapper = (JournalArticleImageWrapper)obj;

		if (Objects.equals(_journalArticleImage,
					journalArticleImageWrapper._journalArticleImage)) {
			return true;
		}

		return false;
	}

	@Override
	public JournalArticleImage getWrappedModel() {
		return _journalArticleImage;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _journalArticleImage.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _journalArticleImage.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_journalArticleImage.resetOriginalValues();
	}

	private final JournalArticleImage _journalArticleImage;
}