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

package com.liferay.calendar.model;

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
 * This class is a wrapper for {@link CalendarNotificationTemplate}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarNotificationTemplate
 * @generated
 */
@ProviderType
public class CalendarNotificationTemplateWrapper
	implements CalendarNotificationTemplate,
		ModelWrapper<CalendarNotificationTemplate> {
	public CalendarNotificationTemplateWrapper(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		_calendarNotificationTemplate = calendarNotificationTemplate;
	}

	@Override
	public Class<?> getModelClass() {
		return CalendarNotificationTemplate.class;
	}

	@Override
	public String getModelClassName() {
		return CalendarNotificationTemplate.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("calendarNotificationTemplateId",
			getCalendarNotificationTemplateId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("calendarId", getCalendarId());
		attributes.put("notificationType", getNotificationType());
		attributes.put("notificationTypeSettings", getNotificationTypeSettings());
		attributes.put("notificationTemplateType", getNotificationTemplateType());
		attributes.put("subject", getSubject());
		attributes.put("body", getBody());
		attributes.put("lastPublishDate", getLastPublishDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long calendarNotificationTemplateId = (Long)attributes.get(
				"calendarNotificationTemplateId");

		if (calendarNotificationTemplateId != null) {
			setCalendarNotificationTemplateId(calendarNotificationTemplateId);
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

		Long calendarId = (Long)attributes.get("calendarId");

		if (calendarId != null) {
			setCalendarId(calendarId);
		}

		String notificationType = (String)attributes.get("notificationType");

		if (notificationType != null) {
			setNotificationType(notificationType);
		}

		String notificationTypeSettings = (String)attributes.get(
				"notificationTypeSettings");

		if (notificationTypeSettings != null) {
			setNotificationTypeSettings(notificationTypeSettings);
		}

		String notificationTemplateType = (String)attributes.get(
				"notificationTemplateType");

		if (notificationTemplateType != null) {
			setNotificationTemplateType(notificationTemplateType);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		String body = (String)attributes.get("body");

		if (body != null) {
			setBody(body);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	@Override
	public CalendarNotificationTemplate toEscapedModel() {
		return new CalendarNotificationTemplateWrapper(_calendarNotificationTemplate.toEscapedModel());
	}

	@Override
	public CalendarNotificationTemplate toUnescapedModel() {
		return new CalendarNotificationTemplateWrapper(_calendarNotificationTemplate.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _calendarNotificationTemplate.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _calendarNotificationTemplate.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _calendarNotificationTemplate.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _calendarNotificationTemplate.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CalendarNotificationTemplate> toCacheModel() {
		return _calendarNotificationTemplate.toCacheModel();
	}

	@Override
	public com.liferay.portal.kernel.util.UnicodeProperties getNotificationTypeSettingsProperties() {
		return _calendarNotificationTemplate.getNotificationTypeSettingsProperties();
	}

	@Override
	public int compareTo(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		return _calendarNotificationTemplate.compareTo(calendarNotificationTemplate);
	}

	@Override
	public int hashCode() {
		return _calendarNotificationTemplate.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _calendarNotificationTemplate.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new CalendarNotificationTemplateWrapper((CalendarNotificationTemplate)_calendarNotificationTemplate.clone());
	}

	/**
	* Returns the body of this calendar notification template.
	*
	* @return the body of this calendar notification template
	*/
	@Override
	public java.lang.String getBody() {
		return _calendarNotificationTemplate.getBody();
	}

	/**
	* Returns the notification template type of this calendar notification template.
	*
	* @return the notification template type of this calendar notification template
	*/
	@Override
	public java.lang.String getNotificationTemplateType() {
		return _calendarNotificationTemplate.getNotificationTemplateType();
	}

	/**
	* Returns the notification type of this calendar notification template.
	*
	* @return the notification type of this calendar notification template
	*/
	@Override
	public java.lang.String getNotificationType() {
		return _calendarNotificationTemplate.getNotificationType();
	}

	/**
	* Returns the notification type settings of this calendar notification template.
	*
	* @return the notification type settings of this calendar notification template
	*/
	@Override
	public java.lang.String getNotificationTypeSettings() {
		return _calendarNotificationTemplate.getNotificationTypeSettings();
	}

	/**
	* Returns the subject of this calendar notification template.
	*
	* @return the subject of this calendar notification template
	*/
	@Override
	public java.lang.String getSubject() {
		return _calendarNotificationTemplate.getSubject();
	}

	/**
	* Returns the user name of this calendar notification template.
	*
	* @return the user name of this calendar notification template
	*/
	@Override
	public java.lang.String getUserName() {
		return _calendarNotificationTemplate.getUserName();
	}

	/**
	* Returns the user uuid of this calendar notification template.
	*
	* @return the user uuid of this calendar notification template
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _calendarNotificationTemplate.getUserUuid();
	}

	/**
	* Returns the uuid of this calendar notification template.
	*
	* @return the uuid of this calendar notification template
	*/
	@Override
	public java.lang.String getUuid() {
		return _calendarNotificationTemplate.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _calendarNotificationTemplate.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _calendarNotificationTemplate.toXmlString();
	}

	/**
	* Returns the create date of this calendar notification template.
	*
	* @return the create date of this calendar notification template
	*/
	@Override
	public Date getCreateDate() {
		return _calendarNotificationTemplate.getCreateDate();
	}

	/**
	* Returns the last publish date of this calendar notification template.
	*
	* @return the last publish date of this calendar notification template
	*/
	@Override
	public Date getLastPublishDate() {
		return _calendarNotificationTemplate.getLastPublishDate();
	}

	/**
	* Returns the modified date of this calendar notification template.
	*
	* @return the modified date of this calendar notification template
	*/
	@Override
	public Date getModifiedDate() {
		return _calendarNotificationTemplate.getModifiedDate();
	}

	/**
	* Returns the calendar ID of this calendar notification template.
	*
	* @return the calendar ID of this calendar notification template
	*/
	@Override
	public long getCalendarId() {
		return _calendarNotificationTemplate.getCalendarId();
	}

	/**
	* Returns the calendar notification template ID of this calendar notification template.
	*
	* @return the calendar notification template ID of this calendar notification template
	*/
	@Override
	public long getCalendarNotificationTemplateId() {
		return _calendarNotificationTemplate.getCalendarNotificationTemplateId();
	}

	/**
	* Returns the company ID of this calendar notification template.
	*
	* @return the company ID of this calendar notification template
	*/
	@Override
	public long getCompanyId() {
		return _calendarNotificationTemplate.getCompanyId();
	}

	/**
	* Returns the group ID of this calendar notification template.
	*
	* @return the group ID of this calendar notification template
	*/
	@Override
	public long getGroupId() {
		return _calendarNotificationTemplate.getGroupId();
	}

	/**
	* Returns the primary key of this calendar notification template.
	*
	* @return the primary key of this calendar notification template
	*/
	@Override
	public long getPrimaryKey() {
		return _calendarNotificationTemplate.getPrimaryKey();
	}

	/**
	* Returns the user ID of this calendar notification template.
	*
	* @return the user ID of this calendar notification template
	*/
	@Override
	public long getUserId() {
		return _calendarNotificationTemplate.getUserId();
	}

	@Override
	public void persist() {
		_calendarNotificationTemplate.persist();
	}

	/**
	* Sets the body of this calendar notification template.
	*
	* @param body the body of this calendar notification template
	*/
	@Override
	public void setBody(java.lang.String body) {
		_calendarNotificationTemplate.setBody(body);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_calendarNotificationTemplate.setCachedModel(cachedModel);
	}

	/**
	* Sets the calendar ID of this calendar notification template.
	*
	* @param calendarId the calendar ID of this calendar notification template
	*/
	@Override
	public void setCalendarId(long calendarId) {
		_calendarNotificationTemplate.setCalendarId(calendarId);
	}

	/**
	* Sets the calendar notification template ID of this calendar notification template.
	*
	* @param calendarNotificationTemplateId the calendar notification template ID of this calendar notification template
	*/
	@Override
	public void setCalendarNotificationTemplateId(
		long calendarNotificationTemplateId) {
		_calendarNotificationTemplate.setCalendarNotificationTemplateId(calendarNotificationTemplateId);
	}

	/**
	* Sets the company ID of this calendar notification template.
	*
	* @param companyId the company ID of this calendar notification template
	*/
	@Override
	public void setCompanyId(long companyId) {
		_calendarNotificationTemplate.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this calendar notification template.
	*
	* @param createDate the create date of this calendar notification template
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_calendarNotificationTemplate.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_calendarNotificationTemplate.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_calendarNotificationTemplate.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_calendarNotificationTemplate.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this calendar notification template.
	*
	* @param groupId the group ID of this calendar notification template
	*/
	@Override
	public void setGroupId(long groupId) {
		_calendarNotificationTemplate.setGroupId(groupId);
	}

	/**
	* Sets the last publish date of this calendar notification template.
	*
	* @param lastPublishDate the last publish date of this calendar notification template
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_calendarNotificationTemplate.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this calendar notification template.
	*
	* @param modifiedDate the modified date of this calendar notification template
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_calendarNotificationTemplate.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_calendarNotificationTemplate.setNew(n);
	}

	/**
	* Sets the notification template type of this calendar notification template.
	*
	* @param notificationTemplateType the notification template type of this calendar notification template
	*/
	@Override
	public void setNotificationTemplateType(
		java.lang.String notificationTemplateType) {
		_calendarNotificationTemplate.setNotificationTemplateType(notificationTemplateType);
	}

	/**
	* Sets the notification type of this calendar notification template.
	*
	* @param notificationType the notification type of this calendar notification template
	*/
	@Override
	public void setNotificationType(java.lang.String notificationType) {
		_calendarNotificationTemplate.setNotificationType(notificationType);
	}

	/**
	* Sets the notification type settings of this calendar notification template.
	*
	* @param notificationTypeSettings the notification type settings of this calendar notification template
	*/
	@Override
	public void setNotificationTypeSettings(
		java.lang.String notificationTypeSettings) {
		_calendarNotificationTemplate.setNotificationTypeSettings(notificationTypeSettings);
	}

	/**
	* Sets the primary key of this calendar notification template.
	*
	* @param primaryKey the primary key of this calendar notification template
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_calendarNotificationTemplate.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_calendarNotificationTemplate.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the subject of this calendar notification template.
	*
	* @param subject the subject of this calendar notification template
	*/
	@Override
	public void setSubject(java.lang.String subject) {
		_calendarNotificationTemplate.setSubject(subject);
	}

	@Override
	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties notificationTypeSettingsProperties) {
		_calendarNotificationTemplate.setTypeSettingsProperties(notificationTypeSettingsProperties);
	}

	/**
	* Sets the user ID of this calendar notification template.
	*
	* @param userId the user ID of this calendar notification template
	*/
	@Override
	public void setUserId(long userId) {
		_calendarNotificationTemplate.setUserId(userId);
	}

	/**
	* Sets the user name of this calendar notification template.
	*
	* @param userName the user name of this calendar notification template
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_calendarNotificationTemplate.setUserName(userName);
	}

	/**
	* Sets the user uuid of this calendar notification template.
	*
	* @param userUuid the user uuid of this calendar notification template
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_calendarNotificationTemplate.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this calendar notification template.
	*
	* @param uuid the uuid of this calendar notification template
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_calendarNotificationTemplate.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CalendarNotificationTemplateWrapper)) {
			return false;
		}

		CalendarNotificationTemplateWrapper calendarNotificationTemplateWrapper = (CalendarNotificationTemplateWrapper)obj;

		if (Objects.equals(_calendarNotificationTemplate,
					calendarNotificationTemplateWrapper._calendarNotificationTemplate)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _calendarNotificationTemplate.getStagedModelType();
	}

	@Override
	public CalendarNotificationTemplate getWrappedModel() {
		return _calendarNotificationTemplate;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _calendarNotificationTemplate.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _calendarNotificationTemplate.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_calendarNotificationTemplate.resetOriginalValues();
	}

	private final CalendarNotificationTemplate _calendarNotificationTemplate;
}