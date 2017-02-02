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

package com.liferay.mobile.device.rules.model;

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
 * This class is a wrapper for {@link MDRRuleGroup}.
 * </p>
 *
 * @author Edward C. Han
 * @see MDRRuleGroup
 * @generated
 */
@ProviderType
public class MDRRuleGroupWrapper implements MDRRuleGroup,
	ModelWrapper<MDRRuleGroup> {
	public MDRRuleGroupWrapper(MDRRuleGroup mdrRuleGroup) {
		_mdrRuleGroup = mdrRuleGroup;
	}

	@Override
	public Class<?> getModelClass() {
		return MDRRuleGroup.class;
	}

	@Override
	public String getModelClassName() {
		return MDRRuleGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("ruleGroupId", getRuleGroupId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("lastPublishDate", getLastPublishDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long ruleGroupId = (Long)attributes.get("ruleGroupId");

		if (ruleGroupId != null) {
			setRuleGroupId(ruleGroupId);
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

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
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

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	@Override
	public MDRRuleGroup toEscapedModel() {
		return new MDRRuleGroupWrapper(_mdrRuleGroup.toEscapedModel());
	}

	@Override
	public MDRRuleGroup toUnescapedModel() {
		return new MDRRuleGroupWrapper(_mdrRuleGroup.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _mdrRuleGroup.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _mdrRuleGroup.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _mdrRuleGroup.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _mdrRuleGroup.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MDRRuleGroup> toCacheModel() {
		return _mdrRuleGroup.toCacheModel();
	}

	@Override
	public int compareTo(MDRRuleGroup mdrRuleGroup) {
		return _mdrRuleGroup.compareTo(mdrRuleGroup);
	}

	@Override
	public int hashCode() {
		return _mdrRuleGroup.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mdrRuleGroup.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new MDRRuleGroupWrapper((MDRRuleGroup)_mdrRuleGroup.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _mdrRuleGroup.getDefaultLanguageId();
	}

	/**
	* Returns the description of this m d r rule group.
	*
	* @return the description of this m d r rule group
	*/
	@Override
	public java.lang.String getDescription() {
		return _mdrRuleGroup.getDescription();
	}

	/**
	* Returns the localized description of this m d r rule group in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this m d r rule group
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _mdrRuleGroup.getDescription(languageId);
	}

	/**
	* Returns the localized description of this m d r rule group in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this m d r rule group
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _mdrRuleGroup.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this m d r rule group in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this m d r rule group
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _mdrRuleGroup.getDescription(locale);
	}

	/**
	* Returns the localized description of this m d r rule group in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this m d r rule group. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _mdrRuleGroup.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _mdrRuleGroup.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _mdrRuleGroup.getDescriptionCurrentValue();
	}

	/**
	* Returns the name of this m d r rule group.
	*
	* @return the name of this m d r rule group
	*/
	@Override
	public java.lang.String getName() {
		return _mdrRuleGroup.getName();
	}

	/**
	* Returns the localized name of this m d r rule group in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this m d r rule group
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _mdrRuleGroup.getName(languageId);
	}

	/**
	* Returns the localized name of this m d r rule group in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this m d r rule group
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _mdrRuleGroup.getName(languageId, useDefault);
	}

	/**
	* Returns the localized name of this m d r rule group in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this m d r rule group
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _mdrRuleGroup.getName(locale);
	}

	/**
	* Returns the localized name of this m d r rule group in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this m d r rule group. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _mdrRuleGroup.getName(locale, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _mdrRuleGroup.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _mdrRuleGroup.getNameCurrentValue();
	}

	/**
	* Returns the user name of this m d r rule group.
	*
	* @return the user name of this m d r rule group
	*/
	@Override
	public java.lang.String getUserName() {
		return _mdrRuleGroup.getUserName();
	}

	/**
	* Returns the user uuid of this m d r rule group.
	*
	* @return the user uuid of this m d r rule group
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _mdrRuleGroup.getUserUuid();
	}

	/**
	* Returns the uuid of this m d r rule group.
	*
	* @return the uuid of this m d r rule group
	*/
	@Override
	public java.lang.String getUuid() {
		return _mdrRuleGroup.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _mdrRuleGroup.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _mdrRuleGroup.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _mdrRuleGroup.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this m d r rule group.
	*
	* @return the create date of this m d r rule group
	*/
	@Override
	public Date getCreateDate() {
		return _mdrRuleGroup.getCreateDate();
	}

	/**
	* Returns the last publish date of this m d r rule group.
	*
	* @return the last publish date of this m d r rule group
	*/
	@Override
	public Date getLastPublishDate() {
		return _mdrRuleGroup.getLastPublishDate();
	}

	/**
	* Returns the modified date of this m d r rule group.
	*
	* @return the modified date of this m d r rule group
	*/
	@Override
	public Date getModifiedDate() {
		return _mdrRuleGroup.getModifiedDate();
	}

	@Override
	public java.util.List<MDRRule> getRules() {
		return _mdrRuleGroup.getRules();
	}

	/**
	* Returns a map of the locales and localized descriptions of this m d r rule group.
	*
	* @return the locales and localized descriptions of this m d r rule group
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _mdrRuleGroup.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized names of this m d r rule group.
	*
	* @return the locales and localized names of this m d r rule group
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _mdrRuleGroup.getNameMap();
	}

	/**
	* Returns the company ID of this m d r rule group.
	*
	* @return the company ID of this m d r rule group
	*/
	@Override
	public long getCompanyId() {
		return _mdrRuleGroup.getCompanyId();
	}

	/**
	* Returns the group ID of this m d r rule group.
	*
	* @return the group ID of this m d r rule group
	*/
	@Override
	public long getGroupId() {
		return _mdrRuleGroup.getGroupId();
	}

	/**
	* Returns the primary key of this m d r rule group.
	*
	* @return the primary key of this m d r rule group
	*/
	@Override
	public long getPrimaryKey() {
		return _mdrRuleGroup.getPrimaryKey();
	}

	/**
	* Returns the rule group ID of this m d r rule group.
	*
	* @return the rule group ID of this m d r rule group
	*/
	@Override
	public long getRuleGroupId() {
		return _mdrRuleGroup.getRuleGroupId();
	}

	/**
	* Returns the user ID of this m d r rule group.
	*
	* @return the user ID of this m d r rule group
	*/
	@Override
	public long getUserId() {
		return _mdrRuleGroup.getUserId();
	}

	@Override
	public void persist() {
		_mdrRuleGroup.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_mdrRuleGroup.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_mdrRuleGroup.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_mdrRuleGroup.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this m d r rule group.
	*
	* @param companyId the company ID of this m d r rule group
	*/
	@Override
	public void setCompanyId(long companyId) {
		_mdrRuleGroup.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this m d r rule group.
	*
	* @param createDate the create date of this m d r rule group
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_mdrRuleGroup.setCreateDate(createDate);
	}

	/**
	* Sets the description of this m d r rule group.
	*
	* @param description the description of this m d r rule group
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_mdrRuleGroup.setDescription(description);
	}

	/**
	* Sets the localized description of this m d r rule group in the language.
	*
	* @param description the localized description of this m d r rule group
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_mdrRuleGroup.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this m d r rule group in the language, and sets the default locale.
	*
	* @param description the localized description of this m d r rule group
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_mdrRuleGroup.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_mdrRuleGroup.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this m d r rule group from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this m d r rule group
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_mdrRuleGroup.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this m d r rule group from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this m d r rule group
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_mdrRuleGroup.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_mdrRuleGroup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_mdrRuleGroup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_mdrRuleGroup.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this m d r rule group.
	*
	* @param groupId the group ID of this m d r rule group
	*/
	@Override
	public void setGroupId(long groupId) {
		_mdrRuleGroup.setGroupId(groupId);
	}

	/**
	* Sets the last publish date of this m d r rule group.
	*
	* @param lastPublishDate the last publish date of this m d r rule group
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_mdrRuleGroup.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this m d r rule group.
	*
	* @param modifiedDate the modified date of this m d r rule group
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_mdrRuleGroup.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this m d r rule group.
	*
	* @param name the name of this m d r rule group
	*/
	@Override
	public void setName(java.lang.String name) {
		_mdrRuleGroup.setName(name);
	}

	/**
	* Sets the localized name of this m d r rule group in the language.
	*
	* @param name the localized name of this m d r rule group
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_mdrRuleGroup.setName(name, locale);
	}

	/**
	* Sets the localized name of this m d r rule group in the language, and sets the default locale.
	*
	* @param name the localized name of this m d r rule group
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_mdrRuleGroup.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_mdrRuleGroup.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this m d r rule group from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this m d r rule group
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_mdrRuleGroup.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this m d r rule group from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this m d r rule group
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_mdrRuleGroup.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_mdrRuleGroup.setNew(n);
	}

	/**
	* Sets the primary key of this m d r rule group.
	*
	* @param primaryKey the primary key of this m d r rule group
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_mdrRuleGroup.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_mdrRuleGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rule group ID of this m d r rule group.
	*
	* @param ruleGroupId the rule group ID of this m d r rule group
	*/
	@Override
	public void setRuleGroupId(long ruleGroupId) {
		_mdrRuleGroup.setRuleGroupId(ruleGroupId);
	}

	/**
	* Sets the user ID of this m d r rule group.
	*
	* @param userId the user ID of this m d r rule group
	*/
	@Override
	public void setUserId(long userId) {
		_mdrRuleGroup.setUserId(userId);
	}

	/**
	* Sets the user name of this m d r rule group.
	*
	* @param userName the user name of this m d r rule group
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_mdrRuleGroup.setUserName(userName);
	}

	/**
	* Sets the user uuid of this m d r rule group.
	*
	* @param userUuid the user uuid of this m d r rule group
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_mdrRuleGroup.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this m d r rule group.
	*
	* @param uuid the uuid of this m d r rule group
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_mdrRuleGroup.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MDRRuleGroupWrapper)) {
			return false;
		}

		MDRRuleGroupWrapper mdrRuleGroupWrapper = (MDRRuleGroupWrapper)obj;

		if (Objects.equals(_mdrRuleGroup, mdrRuleGroupWrapper._mdrRuleGroup)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _mdrRuleGroup.getStagedModelType();
	}

	@Override
	public MDRRuleGroup getWrappedModel() {
		return _mdrRuleGroup;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _mdrRuleGroup.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _mdrRuleGroup.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_mdrRuleGroup.resetOriginalValues();
	}

	private final MDRRuleGroup _mdrRuleGroup;
}