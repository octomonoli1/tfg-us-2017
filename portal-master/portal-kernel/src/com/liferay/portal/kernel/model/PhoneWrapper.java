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
 * This class is a wrapper for {@link Phone}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Phone
 * @generated
 */
@ProviderType
public class PhoneWrapper implements Phone, ModelWrapper<Phone> {
	public PhoneWrapper(Phone phone) {
		_phone = phone;
	}

	@Override
	public Class<?> getModelClass() {
		return Phone.class;
	}

	@Override
	public String getModelClassName() {
		return Phone.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("phoneId", getPhoneId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("number", getNumber());
		attributes.put("extension", getExtension());
		attributes.put("typeId", getTypeId());
		attributes.put("primary", getPrimary());

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

		Long phoneId = (Long)attributes.get("phoneId");

		if (phoneId != null) {
			setPhoneId(phoneId);
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

		String number = (String)attributes.get("number");

		if (number != null) {
			setNumber(number);
		}

		String extension = (String)attributes.get("extension");

		if (extension != null) {
			setExtension(extension);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		Boolean primary = (Boolean)attributes.get("primary");

		if (primary != null) {
			setPrimary(primary);
		}
	}

	@Override
	public CacheModel<Phone> toCacheModel() {
		return _phone.toCacheModel();
	}

	@Override
	public ListType getType()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phone.getType();
	}

	@Override
	public Phone toEscapedModel() {
		return new PhoneWrapper(_phone.toEscapedModel());
	}

	@Override
	public Phone toUnescapedModel() {
		return new PhoneWrapper(_phone.toUnescapedModel());
	}

	/**
	* Returns the primary of this phone.
	*
	* @return the primary of this phone
	*/
	@Override
	public boolean getPrimary() {
		return _phone.getPrimary();
	}

	@Override
	public boolean isCachedModel() {
		return _phone.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _phone.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _phone.isNew();
	}

	/**
	* Returns <code>true</code> if this phone is primary.
	*
	* @return <code>true</code> if this phone is primary; <code>false</code> otherwise
	*/
	@Override
	public boolean isPrimary() {
		return _phone.isPrimary();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _phone.getExpandoBridge();
	}

	@Override
	public int compareTo(Phone phone) {
		return _phone.compareTo(phone);
	}

	@Override
	public int hashCode() {
		return _phone.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _phone.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PhoneWrapper((Phone)_phone.clone());
	}

	/**
	* Returns the fully qualified class name of this phone.
	*
	* @return the fully qualified class name of this phone
	*/
	@Override
	public java.lang.String getClassName() {
		return _phone.getClassName();
	}

	/**
	* Returns the extension of this phone.
	*
	* @return the extension of this phone
	*/
	@Override
	public java.lang.String getExtension() {
		return _phone.getExtension();
	}

	/**
	* Returns the number of this phone.
	*
	* @return the number of this phone
	*/
	@Override
	public java.lang.String getNumber() {
		return _phone.getNumber();
	}

	/**
	* Returns the user name of this phone.
	*
	* @return the user name of this phone
	*/
	@Override
	public java.lang.String getUserName() {
		return _phone.getUserName();
	}

	/**
	* Returns the user uuid of this phone.
	*
	* @return the user uuid of this phone
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _phone.getUserUuid();
	}

	/**
	* Returns the uuid of this phone.
	*
	* @return the uuid of this phone
	*/
	@Override
	public java.lang.String getUuid() {
		return _phone.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _phone.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _phone.toXmlString();
	}

	/**
	* Returns the create date of this phone.
	*
	* @return the create date of this phone
	*/
	@Override
	public Date getCreateDate() {
		return _phone.getCreateDate();
	}

	/**
	* Returns the modified date of this phone.
	*
	* @return the modified date of this phone
	*/
	@Override
	public Date getModifiedDate() {
		return _phone.getModifiedDate();
	}

	/**
	* Returns the class name ID of this phone.
	*
	* @return the class name ID of this phone
	*/
	@Override
	public long getClassNameId() {
		return _phone.getClassNameId();
	}

	/**
	* Returns the class p k of this phone.
	*
	* @return the class p k of this phone
	*/
	@Override
	public long getClassPK() {
		return _phone.getClassPK();
	}

	/**
	* Returns the company ID of this phone.
	*
	* @return the company ID of this phone
	*/
	@Override
	public long getCompanyId() {
		return _phone.getCompanyId();
	}

	/**
	* Returns the mvcc version of this phone.
	*
	* @return the mvcc version of this phone
	*/
	@Override
	public long getMvccVersion() {
		return _phone.getMvccVersion();
	}

	/**
	* Returns the phone ID of this phone.
	*
	* @return the phone ID of this phone
	*/
	@Override
	public long getPhoneId() {
		return _phone.getPhoneId();
	}

	/**
	* Returns the primary key of this phone.
	*
	* @return the primary key of this phone
	*/
	@Override
	public long getPrimaryKey() {
		return _phone.getPrimaryKey();
	}

	/**
	* Returns the type ID of this phone.
	*
	* @return the type ID of this phone
	*/
	@Override
	public long getTypeId() {
		return _phone.getTypeId();
	}

	/**
	* Returns the user ID of this phone.
	*
	* @return the user ID of this phone
	*/
	@Override
	public long getUserId() {
		return _phone.getUserId();
	}

	@Override
	public void persist() {
		_phone.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_phone.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_phone.setClassName(className);
	}

	/**
	* Sets the class name ID of this phone.
	*
	* @param classNameId the class name ID of this phone
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_phone.setClassNameId(classNameId);
	}

	/**
	* Sets the class p k of this phone.
	*
	* @param classPK the class p k of this phone
	*/
	@Override
	public void setClassPK(long classPK) {
		_phone.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this phone.
	*
	* @param companyId the company ID of this phone
	*/
	@Override
	public void setCompanyId(long companyId) {
		_phone.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this phone.
	*
	* @param createDate the create date of this phone
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_phone.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_phone.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_phone.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_phone.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extension of this phone.
	*
	* @param extension the extension of this phone
	*/
	@Override
	public void setExtension(java.lang.String extension) {
		_phone.setExtension(extension);
	}

	/**
	* Sets the modified date of this phone.
	*
	* @param modifiedDate the modified date of this phone
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_phone.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the mvcc version of this phone.
	*
	* @param mvccVersion the mvcc version of this phone
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_phone.setMvccVersion(mvccVersion);
	}

	@Override
	public void setNew(boolean n) {
		_phone.setNew(n);
	}

	/**
	* Sets the number of this phone.
	*
	* @param number the number of this phone
	*/
	@Override
	public void setNumber(java.lang.String number) {
		_phone.setNumber(number);
	}

	/**
	* Sets the phone ID of this phone.
	*
	* @param phoneId the phone ID of this phone
	*/
	@Override
	public void setPhoneId(long phoneId) {
		_phone.setPhoneId(phoneId);
	}

	/**
	* Sets whether this phone is primary.
	*
	* @param primary the primary of this phone
	*/
	@Override
	public void setPrimary(boolean primary) {
		_phone.setPrimary(primary);
	}

	/**
	* Sets the primary key of this phone.
	*
	* @param primaryKey the primary key of this phone
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_phone.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_phone.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type ID of this phone.
	*
	* @param typeId the type ID of this phone
	*/
	@Override
	public void setTypeId(long typeId) {
		_phone.setTypeId(typeId);
	}

	/**
	* Sets the user ID of this phone.
	*
	* @param userId the user ID of this phone
	*/
	@Override
	public void setUserId(long userId) {
		_phone.setUserId(userId);
	}

	/**
	* Sets the user name of this phone.
	*
	* @param userName the user name of this phone
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_phone.setUserName(userName);
	}

	/**
	* Sets the user uuid of this phone.
	*
	* @param userUuid the user uuid of this phone
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_phone.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this phone.
	*
	* @param uuid the uuid of this phone
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_phone.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PhoneWrapper)) {
			return false;
		}

		PhoneWrapper phoneWrapper = (PhoneWrapper)obj;

		if (Objects.equals(_phone, phoneWrapper._phone)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _phone.getStagedModelType();
	}

	@Override
	public Phone getWrappedModel() {
		return _phone;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _phone.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _phone.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_phone.resetOriginalValues();
	}

	private final Phone _phone;
}