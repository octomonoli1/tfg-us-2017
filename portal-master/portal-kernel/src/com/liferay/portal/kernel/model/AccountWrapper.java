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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Account}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Account
 * @generated
 */
@ProviderType
public class AccountWrapper implements Account, ModelWrapper<Account> {
	public AccountWrapper(Account account) {
		_account = account;
	}

	@Override
	public Class<?> getModelClass() {
		return Account.class;
	}

	@Override
	public String getModelClassName() {
		return Account.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("accountId", getAccountId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentAccountId", getParentAccountId());
		attributes.put("name", getName());
		attributes.put("legalName", getLegalName());
		attributes.put("legalId", getLegalId());
		attributes.put("legalType", getLegalType());
		attributes.put("sicCode", getSicCode());
		attributes.put("tickerSymbol", getTickerSymbol());
		attributes.put("industry", getIndustry());
		attributes.put("type", getType());
		attributes.put("size", getSize());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long accountId = (Long)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
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

		Long parentAccountId = (Long)attributes.get("parentAccountId");

		if (parentAccountId != null) {
			setParentAccountId(parentAccountId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String legalName = (String)attributes.get("legalName");

		if (legalName != null) {
			setLegalName(legalName);
		}

		String legalId = (String)attributes.get("legalId");

		if (legalId != null) {
			setLegalId(legalId);
		}

		String legalType = (String)attributes.get("legalType");

		if (legalType != null) {
			setLegalType(legalType);
		}

		String sicCode = (String)attributes.get("sicCode");

		if (sicCode != null) {
			setSicCode(sicCode);
		}

		String tickerSymbol = (String)attributes.get("tickerSymbol");

		if (tickerSymbol != null) {
			setTickerSymbol(tickerSymbol);
		}

		String industry = (String)attributes.get("industry");

		if (industry != null) {
			setIndustry(industry);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String size = (String)attributes.get("size");

		if (size != null) {
			setSize(size);
		}
	}

	@Override
	public Account toEscapedModel() {
		return new AccountWrapper(_account.toEscapedModel());
	}

	@Override
	public Account toUnescapedModel() {
		return new AccountWrapper(_account.toUnescapedModel());
	}

	@Override
	public CacheModel<Account> toCacheModel() {
		return _account.toCacheModel();
	}

	@Override
	public boolean isCachedModel() {
		return _account.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _account.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _account.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _account.getExpandoBridge();
	}

	@Override
	public int compareTo(Account account) {
		return _account.compareTo(account);
	}

	@Override
	public int hashCode() {
		return _account.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _account.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AccountWrapper((Account)_account.clone());
	}

	/**
	* Returns the industry of this account.
	*
	* @return the industry of this account
	*/
	@Override
	public java.lang.String getIndustry() {
		return _account.getIndustry();
	}

	/**
	* Returns the legal ID of this account.
	*
	* @return the legal ID of this account
	*/
	@Override
	public java.lang.String getLegalId() {
		return _account.getLegalId();
	}

	/**
	* Returns the legal name of this account.
	*
	* @return the legal name of this account
	*/
	@Override
	public java.lang.String getLegalName() {
		return _account.getLegalName();
	}

	/**
	* Returns the legal type of this account.
	*
	* @return the legal type of this account
	*/
	@Override
	public java.lang.String getLegalType() {
		return _account.getLegalType();
	}

	/**
	* Returns the name of this account.
	*
	* @return the name of this account
	*/
	@Override
	public java.lang.String getName() {
		return _account.getName();
	}

	/**
	* Returns the sic code of this account.
	*
	* @return the sic code of this account
	*/
	@Override
	public java.lang.String getSicCode() {
		return _account.getSicCode();
	}

	/**
	* Returns the size of this account.
	*
	* @return the size of this account
	*/
	@Override
	public java.lang.String getSize() {
		return _account.getSize();
	}

	/**
	* Returns the ticker symbol of this account.
	*
	* @return the ticker symbol of this account
	*/
	@Override
	public java.lang.String getTickerSymbol() {
		return _account.getTickerSymbol();
	}

	/**
	* Returns the type of this account.
	*
	* @return the type of this account
	*/
	@Override
	public java.lang.String getType() {
		return _account.getType();
	}

	/**
	* Returns the user name of this account.
	*
	* @return the user name of this account
	*/
	@Override
	public java.lang.String getUserName() {
		return _account.getUserName();
	}

	/**
	* Returns the user uuid of this account.
	*
	* @return the user uuid of this account
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _account.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _account.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _account.toXmlString();
	}

	/**
	* Returns the create date of this account.
	*
	* @return the create date of this account
	*/
	@Override
	public Date getCreateDate() {
		return _account.getCreateDate();
	}

	/**
	* Returns the modified date of this account.
	*
	* @return the modified date of this account
	*/
	@Override
	public Date getModifiedDate() {
		return _account.getModifiedDate();
	}

	/**
	* Returns the account ID of this account.
	*
	* @return the account ID of this account
	*/
	@Override
	public long getAccountId() {
		return _account.getAccountId();
	}

	/**
	* Returns the company ID of this account.
	*
	* @return the company ID of this account
	*/
	@Override
	public long getCompanyId() {
		return _account.getCompanyId();
	}

	/**
	* Returns the mvcc version of this account.
	*
	* @return the mvcc version of this account
	*/
	@Override
	public long getMvccVersion() {
		return _account.getMvccVersion();
	}

	/**
	* Returns the parent account ID of this account.
	*
	* @return the parent account ID of this account
	*/
	@Override
	public long getParentAccountId() {
		return _account.getParentAccountId();
	}

	/**
	* Returns the primary key of this account.
	*
	* @return the primary key of this account
	*/
	@Override
	public long getPrimaryKey() {
		return _account.getPrimaryKey();
	}

	/**
	* Returns the user ID of this account.
	*
	* @return the user ID of this account
	*/
	@Override
	public long getUserId() {
		return _account.getUserId();
	}

	@Override
	public void persist() {
		_account.persist();
	}

	/**
	* Sets the account ID of this account.
	*
	* @param accountId the account ID of this account
	*/
	@Override
	public void setAccountId(long accountId) {
		_account.setAccountId(accountId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_account.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this account.
	*
	* @param companyId the company ID of this account
	*/
	@Override
	public void setCompanyId(long companyId) {
		_account.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this account.
	*
	* @param createDate the create date of this account
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_account.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_account.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_account.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_account.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the industry of this account.
	*
	* @param industry the industry of this account
	*/
	@Override
	public void setIndustry(java.lang.String industry) {
		_account.setIndustry(industry);
	}

	/**
	* Sets the legal ID of this account.
	*
	* @param legalId the legal ID of this account
	*/
	@Override
	public void setLegalId(java.lang.String legalId) {
		_account.setLegalId(legalId);
	}

	/**
	* Sets the legal name of this account.
	*
	* @param legalName the legal name of this account
	*/
	@Override
	public void setLegalName(java.lang.String legalName) {
		_account.setLegalName(legalName);
	}

	/**
	* Sets the legal type of this account.
	*
	* @param legalType the legal type of this account
	*/
	@Override
	public void setLegalType(java.lang.String legalType) {
		_account.setLegalType(legalType);
	}

	/**
	* Sets the modified date of this account.
	*
	* @param modifiedDate the modified date of this account
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_account.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the mvcc version of this account.
	*
	* @param mvccVersion the mvcc version of this account
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_account.setMvccVersion(mvccVersion);
	}

	/**
	* Sets the name of this account.
	*
	* @param name the name of this account
	*/
	@Override
	public void setName(java.lang.String name) {
		_account.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_account.setNew(n);
	}

	/**
	* Sets the parent account ID of this account.
	*
	* @param parentAccountId the parent account ID of this account
	*/
	@Override
	public void setParentAccountId(long parentAccountId) {
		_account.setParentAccountId(parentAccountId);
	}

	/**
	* Sets the primary key of this account.
	*
	* @param primaryKey the primary key of this account
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_account.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_account.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sic code of this account.
	*
	* @param sicCode the sic code of this account
	*/
	@Override
	public void setSicCode(java.lang.String sicCode) {
		_account.setSicCode(sicCode);
	}

	/**
	* Sets the size of this account.
	*
	* @param size the size of this account
	*/
	@Override
	public void setSize(java.lang.String size) {
		_account.setSize(size);
	}

	/**
	* Sets the ticker symbol of this account.
	*
	* @param tickerSymbol the ticker symbol of this account
	*/
	@Override
	public void setTickerSymbol(java.lang.String tickerSymbol) {
		_account.setTickerSymbol(tickerSymbol);
	}

	/**
	* Sets the type of this account.
	*
	* @param type the type of this account
	*/
	@Override
	public void setType(java.lang.String type) {
		_account.setType(type);
	}

	/**
	* Sets the user ID of this account.
	*
	* @param userId the user ID of this account
	*/
	@Override
	public void setUserId(long userId) {
		_account.setUserId(userId);
	}

	/**
	* Sets the user name of this account.
	*
	* @param userName the user name of this account
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_account.setUserName(userName);
	}

	/**
	* Sets the user uuid of this account.
	*
	* @param userUuid the user uuid of this account
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_account.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountWrapper)) {
			return false;
		}

		AccountWrapper accountWrapper = (AccountWrapper)obj;

		if (Objects.equals(_account, accountWrapper._account)) {
			return true;
		}

		return false;
	}

	@Override
	public Account getWrappedModel() {
		return _account;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _account.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _account.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_account.resetOriginalValues();
	}

	private final Account _account;
}