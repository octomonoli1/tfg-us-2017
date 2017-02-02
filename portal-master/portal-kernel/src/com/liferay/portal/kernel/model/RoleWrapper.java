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

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Role}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Role
 * @generated
 */
@ProviderType
public class RoleWrapper implements Role, ModelWrapper<Role> {
	public RoleWrapper(Role role) {
		_role = role;
	}

	@Override
	public Class<?> getModelClass() {
		return Role.class;
	}

	@Override
	public String getModelClassName() {
		return Role.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("roleId", getRoleId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("name", getName());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("type", getType());
		attributes.put("subtype", getSubtype());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String subtype = (String)attributes.get("subtype");

		if (subtype != null) {
			setSubtype(subtype);
		}
	}

	@Override
	public CacheModel<Role> toCacheModel() {
		return _role.toCacheModel();
	}

	@Override
	public Role toEscapedModel() {
		return new RoleWrapper(_role.toEscapedModel());
	}

	@Override
	public Role toUnescapedModel() {
		return new RoleWrapper(_role.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _role.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _role.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _role.isNew();
	}

	@Override
	public boolean isSystem() {
		return _role.isSystem();
	}

	@Override
	public boolean isTeam() {
		return _role.isTeam();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _role.getExpandoBridge();
	}

	@Override
	public int compareTo(Role role) {
		return _role.compareTo(role);
	}

	/**
	* Returns the type of this role.
	*
	* @return the type of this role
	*/
	@Override
	public int getType() {
		return _role.getType();
	}

	@Override
	public int hashCode() {
		return _role.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _role.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new RoleWrapper((Role)_role.clone());
	}

	/**
	* Returns the fully qualified class name of this role.
	*
	* @return the fully qualified class name of this role
	*/
	@Override
	public java.lang.String getClassName() {
		return _role.getClassName();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _role.getDefaultLanguageId();
	}

	/**
	* Returns the description of this role.
	*
	* @return the description of this role
	*/
	@Override
	public java.lang.String getDescription() {
		return _role.getDescription();
	}

	/**
	* Returns the localized description of this role in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this role
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _role.getDescription(languageId);
	}

	/**
	* Returns the localized description of this role in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this role
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _role.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this role in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this role
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _role.getDescription(locale);
	}

	/**
	* Returns the localized description of this role in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this role. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _role.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _role.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _role.getDescriptionCurrentValue();
	}

	@Override
	public java.lang.String getDescriptiveName()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _role.getDescriptiveName();
	}

	/**
	* Returns the name of this role.
	*
	* @return the name of this role
	*/
	@Override
	public java.lang.String getName() {
		return _role.getName();
	}

	/**
	* Returns the subtype of this role.
	*
	* @return the subtype of this role
	*/
	@Override
	public java.lang.String getSubtype() {
		return _role.getSubtype();
	}

	/**
	* Returns the title of this role.
	*
	* @return the title of this role
	*/
	@Override
	public java.lang.String getTitle() {
		return _role.getTitle();
	}

	/**
	* Returns the localized title of this role in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this role
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _role.getTitle(languageId);
	}

	/**
	* Returns the localized title of this role in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this role
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _role.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this role in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this role
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _role.getTitle(locale);
	}

	/**
	* Returns the localized title of this role in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this role. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _role.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _role.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _role.getTitleCurrentValue();
	}

	@Override
	public java.lang.String getTypeLabel() {
		return _role.getTypeLabel();
	}

	/**
	* Returns the user name of this role.
	*
	* @return the user name of this role
	*/
	@Override
	public java.lang.String getUserName() {
		return _role.getUserName();
	}

	/**
	* Returns the user uuid of this role.
	*
	* @return the user uuid of this role
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _role.getUserUuid();
	}

	/**
	* Returns the uuid of this role.
	*
	* @return the uuid of this role
	*/
	@Override
	public java.lang.String getUuid() {
		return _role.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _role.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _role.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _role.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this role.
	*
	* @return the create date of this role
	*/
	@Override
	public Date getCreateDate() {
		return _role.getCreateDate();
	}

	/**
	* Returns the modified date of this role.
	*
	* @return the modified date of this role
	*/
	@Override
	public Date getModifiedDate() {
		return _role.getModifiedDate();
	}

	/**
	* Returns a map of the locales and localized descriptions of this role.
	*
	* @return the locales and localized descriptions of this role
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _role.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized titles of this role.
	*
	* @return the locales and localized titles of this role
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _role.getTitleMap();
	}

	/**
	* Returns the class name ID of this role.
	*
	* @return the class name ID of this role
	*/
	@Override
	public long getClassNameId() {
		return _role.getClassNameId();
	}

	/**
	* Returns the class p k of this role.
	*
	* @return the class p k of this role
	*/
	@Override
	public long getClassPK() {
		return _role.getClassPK();
	}

	/**
	* Returns the company ID of this role.
	*
	* @return the company ID of this role
	*/
	@Override
	public long getCompanyId() {
		return _role.getCompanyId();
	}

	/**
	* Returns the mvcc version of this role.
	*
	* @return the mvcc version of this role
	*/
	@Override
	public long getMvccVersion() {
		return _role.getMvccVersion();
	}

	/**
	* Returns the primary key of this role.
	*
	* @return the primary key of this role
	*/
	@Override
	public long getPrimaryKey() {
		return _role.getPrimaryKey();
	}

	/**
	* Returns the role ID of this role.
	*
	* @return the role ID of this role
	*/
	@Override
	public long getRoleId() {
		return _role.getRoleId();
	}

	/**
	* Returns the user ID of this role.
	*
	* @return the user ID of this role
	*/
	@Override
	public long getUserId() {
		return _role.getUserId();
	}

	@Override
	public void persist() {
		_role.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_role.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_role.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_role.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_role.setClassName(className);
	}

	/**
	* Sets the class name ID of this role.
	*
	* @param classNameId the class name ID of this role
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_role.setClassNameId(classNameId);
	}

	/**
	* Sets the class p k of this role.
	*
	* @param classPK the class p k of this role
	*/
	@Override
	public void setClassPK(long classPK) {
		_role.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this role.
	*
	* @param companyId the company ID of this role
	*/
	@Override
	public void setCompanyId(long companyId) {
		_role.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this role.
	*
	* @param createDate the create date of this role
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_role.setCreateDate(createDate);
	}

	/**
	* Sets the description of this role.
	*
	* @param description the description of this role
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_role.setDescription(description);
	}

	/**
	* Sets the localized description of this role in the language.
	*
	* @param description the localized description of this role
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_role.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this role in the language, and sets the default locale.
	*
	* @param description the localized description of this role
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_role.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_role.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this role from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this role
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_role.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this role from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this role
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_role.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_role.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_role.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_role.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this role.
	*
	* @param modifiedDate the modified date of this role
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_role.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the mvcc version of this role.
	*
	* @param mvccVersion the mvcc version of this role
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_role.setMvccVersion(mvccVersion);
	}

	/**
	* Sets the name of this role.
	*
	* @param name the name of this role
	*/
	@Override
	public void setName(java.lang.String name) {
		_role.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_role.setNew(n);
	}

	/**
	* Sets the primary key of this role.
	*
	* @param primaryKey the primary key of this role
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_role.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_role.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role ID of this role.
	*
	* @param roleId the role ID of this role
	*/
	@Override
	public void setRoleId(long roleId) {
		_role.setRoleId(roleId);
	}

	/**
	* Sets the subtype of this role.
	*
	* @param subtype the subtype of this role
	*/
	@Override
	public void setSubtype(java.lang.String subtype) {
		_role.setSubtype(subtype);
	}

	/**
	* Sets the title of this role.
	*
	* @param title the title of this role
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_role.setTitle(title);
	}

	/**
	* Sets the localized title of this role in the language.
	*
	* @param title the localized title of this role
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_role.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this role in the language, and sets the default locale.
	*
	* @param title the localized title of this role
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_role.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_role.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this role from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this role
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_role.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this role from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this role
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_role.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the type of this role.
	*
	* @param type the type of this role
	*/
	@Override
	public void setType(int type) {
		_role.setType(type);
	}

	/**
	* Sets the user ID of this role.
	*
	* @param userId the user ID of this role
	*/
	@Override
	public void setUserId(long userId) {
		_role.setUserId(userId);
	}

	/**
	* Sets the user name of this role.
	*
	* @param userName the user name of this role
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_role.setUserName(userName);
	}

	/**
	* Sets the user uuid of this role.
	*
	* @param userUuid the user uuid of this role
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_role.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this role.
	*
	* @param uuid the uuid of this role
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_role.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RoleWrapper)) {
			return false;
		}

		RoleWrapper roleWrapper = (RoleWrapper)obj;

		if (Objects.equals(_role, roleWrapper._role)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _role.getStagedModelType();
	}

	@Override
	public Role getWrappedModel() {
		return _role;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _role.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _role.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_role.resetOriginalValues();
	}

	private final Role _role;
}