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
 * This class is a wrapper for {@link PasswordPolicy}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PasswordPolicy
 * @generated
 */
@ProviderType
public class PasswordPolicyWrapper implements PasswordPolicy,
	ModelWrapper<PasswordPolicy> {
	public PasswordPolicyWrapper(PasswordPolicy passwordPolicy) {
		_passwordPolicy = passwordPolicy;
	}

	@Override
	public Class<?> getModelClass() {
		return PasswordPolicy.class;
	}

	@Override
	public String getModelClassName() {
		return PasswordPolicy.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("passwordPolicyId", getPasswordPolicyId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("defaultPolicy", getDefaultPolicy());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("changeable", getChangeable());
		attributes.put("changeRequired", getChangeRequired());
		attributes.put("minAge", getMinAge());
		attributes.put("checkSyntax", getCheckSyntax());
		attributes.put("allowDictionaryWords", getAllowDictionaryWords());
		attributes.put("minAlphanumeric", getMinAlphanumeric());
		attributes.put("minLength", getMinLength());
		attributes.put("minLowerCase", getMinLowerCase());
		attributes.put("minNumbers", getMinNumbers());
		attributes.put("minSymbols", getMinSymbols());
		attributes.put("minUpperCase", getMinUpperCase());
		attributes.put("regex", getRegex());
		attributes.put("history", getHistory());
		attributes.put("historyCount", getHistoryCount());
		attributes.put("expireable", getExpireable());
		attributes.put("maxAge", getMaxAge());
		attributes.put("warningTime", getWarningTime());
		attributes.put("graceLimit", getGraceLimit());
		attributes.put("lockout", getLockout());
		attributes.put("maxFailure", getMaxFailure());
		attributes.put("lockoutDuration", getLockoutDuration());
		attributes.put("requireUnlock", getRequireUnlock());
		attributes.put("resetFailureCount", getResetFailureCount());
		attributes.put("resetTicketMaxAge", getResetTicketMaxAge());

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

		Long passwordPolicyId = (Long)attributes.get("passwordPolicyId");

		if (passwordPolicyId != null) {
			setPasswordPolicyId(passwordPolicyId);
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

		Boolean defaultPolicy = (Boolean)attributes.get("defaultPolicy");

		if (defaultPolicy != null) {
			setDefaultPolicy(defaultPolicy);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Boolean changeable = (Boolean)attributes.get("changeable");

		if (changeable != null) {
			setChangeable(changeable);
		}

		Boolean changeRequired = (Boolean)attributes.get("changeRequired");

		if (changeRequired != null) {
			setChangeRequired(changeRequired);
		}

		Long minAge = (Long)attributes.get("minAge");

		if (minAge != null) {
			setMinAge(minAge);
		}

		Boolean checkSyntax = (Boolean)attributes.get("checkSyntax");

		if (checkSyntax != null) {
			setCheckSyntax(checkSyntax);
		}

		Boolean allowDictionaryWords = (Boolean)attributes.get(
				"allowDictionaryWords");

		if (allowDictionaryWords != null) {
			setAllowDictionaryWords(allowDictionaryWords);
		}

		Integer minAlphanumeric = (Integer)attributes.get("minAlphanumeric");

		if (minAlphanumeric != null) {
			setMinAlphanumeric(minAlphanumeric);
		}

		Integer minLength = (Integer)attributes.get("minLength");

		if (minLength != null) {
			setMinLength(minLength);
		}

		Integer minLowerCase = (Integer)attributes.get("minLowerCase");

		if (minLowerCase != null) {
			setMinLowerCase(minLowerCase);
		}

		Integer minNumbers = (Integer)attributes.get("minNumbers");

		if (minNumbers != null) {
			setMinNumbers(minNumbers);
		}

		Integer minSymbols = (Integer)attributes.get("minSymbols");

		if (minSymbols != null) {
			setMinSymbols(minSymbols);
		}

		Integer minUpperCase = (Integer)attributes.get("minUpperCase");

		if (minUpperCase != null) {
			setMinUpperCase(minUpperCase);
		}

		String regex = (String)attributes.get("regex");

		if (regex != null) {
			setRegex(regex);
		}

		Boolean history = (Boolean)attributes.get("history");

		if (history != null) {
			setHistory(history);
		}

		Integer historyCount = (Integer)attributes.get("historyCount");

		if (historyCount != null) {
			setHistoryCount(historyCount);
		}

		Boolean expireable = (Boolean)attributes.get("expireable");

		if (expireable != null) {
			setExpireable(expireable);
		}

		Long maxAge = (Long)attributes.get("maxAge");

		if (maxAge != null) {
			setMaxAge(maxAge);
		}

		Long warningTime = (Long)attributes.get("warningTime");

		if (warningTime != null) {
			setWarningTime(warningTime);
		}

		Integer graceLimit = (Integer)attributes.get("graceLimit");

		if (graceLimit != null) {
			setGraceLimit(graceLimit);
		}

		Boolean lockout = (Boolean)attributes.get("lockout");

		if (lockout != null) {
			setLockout(lockout);
		}

		Integer maxFailure = (Integer)attributes.get("maxFailure");

		if (maxFailure != null) {
			setMaxFailure(maxFailure);
		}

		Long lockoutDuration = (Long)attributes.get("lockoutDuration");

		if (lockoutDuration != null) {
			setLockoutDuration(lockoutDuration);
		}

		Boolean requireUnlock = (Boolean)attributes.get("requireUnlock");

		if (requireUnlock != null) {
			setRequireUnlock(requireUnlock);
		}

		Long resetFailureCount = (Long)attributes.get("resetFailureCount");

		if (resetFailureCount != null) {
			setResetFailureCount(resetFailureCount);
		}

		Long resetTicketMaxAge = (Long)attributes.get("resetTicketMaxAge");

		if (resetTicketMaxAge != null) {
			setResetTicketMaxAge(resetTicketMaxAge);
		}
	}

	@Override
	public CacheModel<PasswordPolicy> toCacheModel() {
		return _passwordPolicy.toCacheModel();
	}

	@Override
	public PasswordPolicy toEscapedModel() {
		return new PasswordPolicyWrapper(_passwordPolicy.toEscapedModel());
	}

	@Override
	public PasswordPolicy toUnescapedModel() {
		return new PasswordPolicyWrapper(_passwordPolicy.toUnescapedModel());
	}

	/**
	* Returns the allow dictionary words of this password policy.
	*
	* @return the allow dictionary words of this password policy
	*/
	@Override
	public boolean getAllowDictionaryWords() {
		return _passwordPolicy.getAllowDictionaryWords();
	}

	/**
	* Returns the change required of this password policy.
	*
	* @return the change required of this password policy
	*/
	@Override
	public boolean getChangeRequired() {
		return _passwordPolicy.getChangeRequired();
	}

	/**
	* Returns the changeable of this password policy.
	*
	* @return the changeable of this password policy
	*/
	@Override
	public boolean getChangeable() {
		return _passwordPolicy.getChangeable();
	}

	/**
	* Returns the check syntax of this password policy.
	*
	* @return the check syntax of this password policy
	*/
	@Override
	public boolean getCheckSyntax() {
		return _passwordPolicy.getCheckSyntax();
	}

	/**
	* Returns the default policy of this password policy.
	*
	* @return the default policy of this password policy
	*/
	@Override
	public boolean getDefaultPolicy() {
		return _passwordPolicy.getDefaultPolicy();
	}

	/**
	* Returns the expireable of this password policy.
	*
	* @return the expireable of this password policy
	*/
	@Override
	public boolean getExpireable() {
		return _passwordPolicy.getExpireable();
	}

	/**
	* Returns the history of this password policy.
	*
	* @return the history of this password policy
	*/
	@Override
	public boolean getHistory() {
		return _passwordPolicy.getHistory();
	}

	/**
	* Returns the lockout of this password policy.
	*
	* @return the lockout of this password policy
	*/
	@Override
	public boolean getLockout() {
		return _passwordPolicy.getLockout();
	}

	/**
	* Returns the require unlock of this password policy.
	*
	* @return the require unlock of this password policy
	*/
	@Override
	public boolean getRequireUnlock() {
		return _passwordPolicy.getRequireUnlock();
	}

	/**
	* Returns <code>true</code> if this password policy is allow dictionary words.
	*
	* @return <code>true</code> if this password policy is allow dictionary words; <code>false</code> otherwise
	*/
	@Override
	public boolean isAllowDictionaryWords() {
		return _passwordPolicy.isAllowDictionaryWords();
	}

	@Override
	public boolean isCachedModel() {
		return _passwordPolicy.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this password policy is change required.
	*
	* @return <code>true</code> if this password policy is change required; <code>false</code> otherwise
	*/
	@Override
	public boolean isChangeRequired() {
		return _passwordPolicy.isChangeRequired();
	}

	/**
	* Returns <code>true</code> if this password policy is changeable.
	*
	* @return <code>true</code> if this password policy is changeable; <code>false</code> otherwise
	*/
	@Override
	public boolean isChangeable() {
		return _passwordPolicy.isChangeable();
	}

	/**
	* Returns <code>true</code> if this password policy is check syntax.
	*
	* @return <code>true</code> if this password policy is check syntax; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckSyntax() {
		return _passwordPolicy.isCheckSyntax();
	}

	/**
	* Returns <code>true</code> if this password policy is default policy.
	*
	* @return <code>true</code> if this password policy is default policy; <code>false</code> otherwise
	*/
	@Override
	public boolean isDefaultPolicy() {
		return _passwordPolicy.isDefaultPolicy();
	}

	@Override
	public boolean isEscapedModel() {
		return _passwordPolicy.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this password policy is expireable.
	*
	* @return <code>true</code> if this password policy is expireable; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpireable() {
		return _passwordPolicy.isExpireable();
	}

	/**
	* Returns <code>true</code> if this password policy is history.
	*
	* @return <code>true</code> if this password policy is history; <code>false</code> otherwise
	*/
	@Override
	public boolean isHistory() {
		return _passwordPolicy.isHistory();
	}

	/**
	* Returns <code>true</code> if this password policy is lockout.
	*
	* @return <code>true</code> if this password policy is lockout; <code>false</code> otherwise
	*/
	@Override
	public boolean isLockout() {
		return _passwordPolicy.isLockout();
	}

	@Override
	public boolean isNew() {
		return _passwordPolicy.isNew();
	}

	/**
	* Returns <code>true</code> if this password policy is require unlock.
	*
	* @return <code>true</code> if this password policy is require unlock; <code>false</code> otherwise
	*/
	@Override
	public boolean isRequireUnlock() {
		return _passwordPolicy.isRequireUnlock();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _passwordPolicy.getExpandoBridge();
	}

	@Override
	public int compareTo(PasswordPolicy passwordPolicy) {
		return _passwordPolicy.compareTo(passwordPolicy);
	}

	/**
	* Returns the grace limit of this password policy.
	*
	* @return the grace limit of this password policy
	*/
	@Override
	public int getGraceLimit() {
		return _passwordPolicy.getGraceLimit();
	}

	/**
	* Returns the history count of this password policy.
	*
	* @return the history count of this password policy
	*/
	@Override
	public int getHistoryCount() {
		return _passwordPolicy.getHistoryCount();
	}

	/**
	* Returns the max failure of this password policy.
	*
	* @return the max failure of this password policy
	*/
	@Override
	public int getMaxFailure() {
		return _passwordPolicy.getMaxFailure();
	}

	/**
	* Returns the min alphanumeric of this password policy.
	*
	* @return the min alphanumeric of this password policy
	*/
	@Override
	public int getMinAlphanumeric() {
		return _passwordPolicy.getMinAlphanumeric();
	}

	/**
	* Returns the min length of this password policy.
	*
	* @return the min length of this password policy
	*/
	@Override
	public int getMinLength() {
		return _passwordPolicy.getMinLength();
	}

	/**
	* Returns the min lower case of this password policy.
	*
	* @return the min lower case of this password policy
	*/
	@Override
	public int getMinLowerCase() {
		return _passwordPolicy.getMinLowerCase();
	}

	/**
	* Returns the min numbers of this password policy.
	*
	* @return the min numbers of this password policy
	*/
	@Override
	public int getMinNumbers() {
		return _passwordPolicy.getMinNumbers();
	}

	/**
	* Returns the min symbols of this password policy.
	*
	* @return the min symbols of this password policy
	*/
	@Override
	public int getMinSymbols() {
		return _passwordPolicy.getMinSymbols();
	}

	/**
	* Returns the min upper case of this password policy.
	*
	* @return the min upper case of this password policy
	*/
	@Override
	public int getMinUpperCase() {
		return _passwordPolicy.getMinUpperCase();
	}

	@Override
	public int hashCode() {
		return _passwordPolicy.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _passwordPolicy.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PasswordPolicyWrapper((PasswordPolicy)_passwordPolicy.clone());
	}

	/**
	* Returns the description of this password policy.
	*
	* @return the description of this password policy
	*/
	@Override
	public java.lang.String getDescription() {
		return _passwordPolicy.getDescription();
	}

	/**
	* Returns the name of this password policy.
	*
	* @return the name of this password policy
	*/
	@Override
	public java.lang.String getName() {
		return _passwordPolicy.getName();
	}

	/**
	* Returns the regex of this password policy.
	*
	* @return the regex of this password policy
	*/
	@Override
	public java.lang.String getRegex() {
		return _passwordPolicy.getRegex();
	}

	/**
	* Returns the user name of this password policy.
	*
	* @return the user name of this password policy
	*/
	@Override
	public java.lang.String getUserName() {
		return _passwordPolicy.getUserName();
	}

	/**
	* Returns the user uuid of this password policy.
	*
	* @return the user uuid of this password policy
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _passwordPolicy.getUserUuid();
	}

	/**
	* Returns the uuid of this password policy.
	*
	* @return the uuid of this password policy
	*/
	@Override
	public java.lang.String getUuid() {
		return _passwordPolicy.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _passwordPolicy.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _passwordPolicy.toXmlString();
	}

	/**
	* Returns the create date of this password policy.
	*
	* @return the create date of this password policy
	*/
	@Override
	public Date getCreateDate() {
		return _passwordPolicy.getCreateDate();
	}

	/**
	* Returns the modified date of this password policy.
	*
	* @return the modified date of this password policy
	*/
	@Override
	public Date getModifiedDate() {
		return _passwordPolicy.getModifiedDate();
	}

	/**
	* Returns the company ID of this password policy.
	*
	* @return the company ID of this password policy
	*/
	@Override
	public long getCompanyId() {
		return _passwordPolicy.getCompanyId();
	}

	/**
	* Returns the lockout duration of this password policy.
	*
	* @return the lockout duration of this password policy
	*/
	@Override
	public long getLockoutDuration() {
		return _passwordPolicy.getLockoutDuration();
	}

	/**
	* Returns the max age of this password policy.
	*
	* @return the max age of this password policy
	*/
	@Override
	public long getMaxAge() {
		return _passwordPolicy.getMaxAge();
	}

	/**
	* Returns the min age of this password policy.
	*
	* @return the min age of this password policy
	*/
	@Override
	public long getMinAge() {
		return _passwordPolicy.getMinAge();
	}

	/**
	* Returns the mvcc version of this password policy.
	*
	* @return the mvcc version of this password policy
	*/
	@Override
	public long getMvccVersion() {
		return _passwordPolicy.getMvccVersion();
	}

	/**
	* Returns the password policy ID of this password policy.
	*
	* @return the password policy ID of this password policy
	*/
	@Override
	public long getPasswordPolicyId() {
		return _passwordPolicy.getPasswordPolicyId();
	}

	/**
	* Returns the primary key of this password policy.
	*
	* @return the primary key of this password policy
	*/
	@Override
	public long getPrimaryKey() {
		return _passwordPolicy.getPrimaryKey();
	}

	/**
	* Returns the reset failure count of this password policy.
	*
	* @return the reset failure count of this password policy
	*/
	@Override
	public long getResetFailureCount() {
		return _passwordPolicy.getResetFailureCount();
	}

	/**
	* Returns the reset ticket max age of this password policy.
	*
	* @return the reset ticket max age of this password policy
	*/
	@Override
	public long getResetTicketMaxAge() {
		return _passwordPolicy.getResetTicketMaxAge();
	}

	/**
	* Returns the user ID of this password policy.
	*
	* @return the user ID of this password policy
	*/
	@Override
	public long getUserId() {
		return _passwordPolicy.getUserId();
	}

	/**
	* Returns the warning time of this password policy.
	*
	* @return the warning time of this password policy
	*/
	@Override
	public long getWarningTime() {
		return _passwordPolicy.getWarningTime();
	}

	@Override
	public void persist() {
		_passwordPolicy.persist();
	}

	/**
	* Sets whether this password policy is allow dictionary words.
	*
	* @param allowDictionaryWords the allow dictionary words of this password policy
	*/
	@Override
	public void setAllowDictionaryWords(boolean allowDictionaryWords) {
		_passwordPolicy.setAllowDictionaryWords(allowDictionaryWords);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_passwordPolicy.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this password policy is change required.
	*
	* @param changeRequired the change required of this password policy
	*/
	@Override
	public void setChangeRequired(boolean changeRequired) {
		_passwordPolicy.setChangeRequired(changeRequired);
	}

	/**
	* Sets whether this password policy is changeable.
	*
	* @param changeable the changeable of this password policy
	*/
	@Override
	public void setChangeable(boolean changeable) {
		_passwordPolicy.setChangeable(changeable);
	}

	/**
	* Sets whether this password policy is check syntax.
	*
	* @param checkSyntax the check syntax of this password policy
	*/
	@Override
	public void setCheckSyntax(boolean checkSyntax) {
		_passwordPolicy.setCheckSyntax(checkSyntax);
	}

	/**
	* Sets the company ID of this password policy.
	*
	* @param companyId the company ID of this password policy
	*/
	@Override
	public void setCompanyId(long companyId) {
		_passwordPolicy.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this password policy.
	*
	* @param createDate the create date of this password policy
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_passwordPolicy.setCreateDate(createDate);
	}

	/**
	* Sets whether this password policy is default policy.
	*
	* @param defaultPolicy the default policy of this password policy
	*/
	@Override
	public void setDefaultPolicy(boolean defaultPolicy) {
		_passwordPolicy.setDefaultPolicy(defaultPolicy);
	}

	/**
	* Sets the description of this password policy.
	*
	* @param description the description of this password policy
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_passwordPolicy.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_passwordPolicy.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_passwordPolicy.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_passwordPolicy.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this password policy is expireable.
	*
	* @param expireable the expireable of this password policy
	*/
	@Override
	public void setExpireable(boolean expireable) {
		_passwordPolicy.setExpireable(expireable);
	}

	/**
	* Sets the grace limit of this password policy.
	*
	* @param graceLimit the grace limit of this password policy
	*/
	@Override
	public void setGraceLimit(int graceLimit) {
		_passwordPolicy.setGraceLimit(graceLimit);
	}

	/**
	* Sets whether this password policy is history.
	*
	* @param history the history of this password policy
	*/
	@Override
	public void setHistory(boolean history) {
		_passwordPolicy.setHistory(history);
	}

	/**
	* Sets the history count of this password policy.
	*
	* @param historyCount the history count of this password policy
	*/
	@Override
	public void setHistoryCount(int historyCount) {
		_passwordPolicy.setHistoryCount(historyCount);
	}

	/**
	* Sets whether this password policy is lockout.
	*
	* @param lockout the lockout of this password policy
	*/
	@Override
	public void setLockout(boolean lockout) {
		_passwordPolicy.setLockout(lockout);
	}

	/**
	* Sets the lockout duration of this password policy.
	*
	* @param lockoutDuration the lockout duration of this password policy
	*/
	@Override
	public void setLockoutDuration(long lockoutDuration) {
		_passwordPolicy.setLockoutDuration(lockoutDuration);
	}

	/**
	* Sets the max age of this password policy.
	*
	* @param maxAge the max age of this password policy
	*/
	@Override
	public void setMaxAge(long maxAge) {
		_passwordPolicy.setMaxAge(maxAge);
	}

	/**
	* Sets the max failure of this password policy.
	*
	* @param maxFailure the max failure of this password policy
	*/
	@Override
	public void setMaxFailure(int maxFailure) {
		_passwordPolicy.setMaxFailure(maxFailure);
	}

	/**
	* Sets the min age of this password policy.
	*
	* @param minAge the min age of this password policy
	*/
	@Override
	public void setMinAge(long minAge) {
		_passwordPolicy.setMinAge(minAge);
	}

	/**
	* Sets the min alphanumeric of this password policy.
	*
	* @param minAlphanumeric the min alphanumeric of this password policy
	*/
	@Override
	public void setMinAlphanumeric(int minAlphanumeric) {
		_passwordPolicy.setMinAlphanumeric(minAlphanumeric);
	}

	/**
	* Sets the min length of this password policy.
	*
	* @param minLength the min length of this password policy
	*/
	@Override
	public void setMinLength(int minLength) {
		_passwordPolicy.setMinLength(minLength);
	}

	/**
	* Sets the min lower case of this password policy.
	*
	* @param minLowerCase the min lower case of this password policy
	*/
	@Override
	public void setMinLowerCase(int minLowerCase) {
		_passwordPolicy.setMinLowerCase(minLowerCase);
	}

	/**
	* Sets the min numbers of this password policy.
	*
	* @param minNumbers the min numbers of this password policy
	*/
	@Override
	public void setMinNumbers(int minNumbers) {
		_passwordPolicy.setMinNumbers(minNumbers);
	}

	/**
	* Sets the min symbols of this password policy.
	*
	* @param minSymbols the min symbols of this password policy
	*/
	@Override
	public void setMinSymbols(int minSymbols) {
		_passwordPolicy.setMinSymbols(minSymbols);
	}

	/**
	* Sets the min upper case of this password policy.
	*
	* @param minUpperCase the min upper case of this password policy
	*/
	@Override
	public void setMinUpperCase(int minUpperCase) {
		_passwordPolicy.setMinUpperCase(minUpperCase);
	}

	/**
	* Sets the modified date of this password policy.
	*
	* @param modifiedDate the modified date of this password policy
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_passwordPolicy.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the mvcc version of this password policy.
	*
	* @param mvccVersion the mvcc version of this password policy
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_passwordPolicy.setMvccVersion(mvccVersion);
	}

	/**
	* Sets the name of this password policy.
	*
	* @param name the name of this password policy
	*/
	@Override
	public void setName(java.lang.String name) {
		_passwordPolicy.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_passwordPolicy.setNew(n);
	}

	/**
	* Sets the password policy ID of this password policy.
	*
	* @param passwordPolicyId the password policy ID of this password policy
	*/
	@Override
	public void setPasswordPolicyId(long passwordPolicyId) {
		_passwordPolicy.setPasswordPolicyId(passwordPolicyId);
	}

	/**
	* Sets the primary key of this password policy.
	*
	* @param primaryKey the primary key of this password policy
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_passwordPolicy.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_passwordPolicy.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the regex of this password policy.
	*
	* @param regex the regex of this password policy
	*/
	@Override
	public void setRegex(java.lang.String regex) {
		_passwordPolicy.setRegex(regex);
	}

	/**
	* Sets whether this password policy is require unlock.
	*
	* @param requireUnlock the require unlock of this password policy
	*/
	@Override
	public void setRequireUnlock(boolean requireUnlock) {
		_passwordPolicy.setRequireUnlock(requireUnlock);
	}

	/**
	* Sets the reset failure count of this password policy.
	*
	* @param resetFailureCount the reset failure count of this password policy
	*/
	@Override
	public void setResetFailureCount(long resetFailureCount) {
		_passwordPolicy.setResetFailureCount(resetFailureCount);
	}

	/**
	* Sets the reset ticket max age of this password policy.
	*
	* @param resetTicketMaxAge the reset ticket max age of this password policy
	*/
	@Override
	public void setResetTicketMaxAge(long resetTicketMaxAge) {
		_passwordPolicy.setResetTicketMaxAge(resetTicketMaxAge);
	}

	/**
	* Sets the user ID of this password policy.
	*
	* @param userId the user ID of this password policy
	*/
	@Override
	public void setUserId(long userId) {
		_passwordPolicy.setUserId(userId);
	}

	/**
	* Sets the user name of this password policy.
	*
	* @param userName the user name of this password policy
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_passwordPolicy.setUserName(userName);
	}

	/**
	* Sets the user uuid of this password policy.
	*
	* @param userUuid the user uuid of this password policy
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_passwordPolicy.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this password policy.
	*
	* @param uuid the uuid of this password policy
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_passwordPolicy.setUuid(uuid);
	}

	/**
	* Sets the warning time of this password policy.
	*
	* @param warningTime the warning time of this password policy
	*/
	@Override
	public void setWarningTime(long warningTime) {
		_passwordPolicy.setWarningTime(warningTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PasswordPolicyWrapper)) {
			return false;
		}

		PasswordPolicyWrapper passwordPolicyWrapper = (PasswordPolicyWrapper)obj;

		if (Objects.equals(_passwordPolicy,
					passwordPolicyWrapper._passwordPolicy)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _passwordPolicy.getStagedModelType();
	}

	@Override
	public PasswordPolicy getWrappedModel() {
		return _passwordPolicy;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _passwordPolicy.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _passwordPolicy.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_passwordPolicy.resetOriginalValues();
	}

	private final PasswordPolicy _passwordPolicy;
}