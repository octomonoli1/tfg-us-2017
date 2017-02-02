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

package com.liferay.calendar.notification.impl;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.notification.NotificationField;
import com.liferay.calendar.notification.NotificationTemplateType;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.service.configuration.CalendarServiceConfigurationKeys;
import com.liferay.calendar.service.configuration.CalendarServiceConfigurationUtil;
import com.liferay.calendar.service.configuration.CalendarServiceConfigurationValues;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Eduardo Lundgren
 * @author Marcellus Tavares
 */
public class NotificationUtil {

	public static User getDefaultSenderUser(Calendar calendar)
		throws Exception {

		CalendarResource calendarResource = calendar.getCalendarResource();

		User user = UserLocalServiceUtil.getUser(calendarResource.getUserId());

		if (calendarResource.isGroup()) {
			Group group = GroupLocalServiceUtil.getGroup(
				calendarResource.getClassPK());

			user = UserLocalServiceUtil.getUser(group.getCreatorUserId());
		}
		else if (calendarResource.isUser()) {
			user = UserLocalServiceUtil.getUser(calendarResource.getClassPK());
		}

		return user;
	}

	public static String getDefaultTemplate(
			NotificationType notificationType,
			NotificationTemplateType notificationTemplateType,
			NotificationField notificationField)
		throws Exception {

		Filter filter = new Filter(
			notificationType.toString(), notificationTemplateType.toString());

		String propertyName =
			CalendarServiceConfigurationKeys.CALENDAR_NOTIFICATION_PREFIX +
				StringPool.PERIOD + notificationField.toString();

		String templatePath = CalendarServiceConfigurationUtil.get(
			propertyName, filter);

		return ContentUtil.get(
			NotificationUtil.class.getClassLoader(), templatePath);
	}

	public static String getTemplate(
			CalendarNotificationTemplate calendarNotificationTemplate,
			NotificationType notificationType,
			NotificationTemplateType notificationTemplateType,
			NotificationField notificationField)
		throws Exception {

		String defaultTemplate = getDefaultTemplate(
			notificationType, notificationTemplateType, notificationField);

		return BeanPropertiesUtil.getString(
			calendarNotificationTemplate, notificationField.toString(),
			defaultTemplate);
	}

	public static String getTemplatePropertyValue(
		CalendarNotificationTemplate calendarNotificationTemplate,
		String propertyName) {

		return getTemplatePropertyValue(
			calendarNotificationTemplate, propertyName, StringPool.BLANK);
	}

	public static String getTemplatePropertyValue(
		CalendarNotificationTemplate calendarNotificationTemplate,
		String propertyName, String defaultValue) {

		if (calendarNotificationTemplate == null) {
			return defaultValue;
		}

		UnicodeProperties notificationTypeSettingsProperties =
			calendarNotificationTemplate.
				getNotificationTypeSettingsProperties();

		return notificationTypeSettingsProperties.get(propertyName);
	}

	public static void notifyCalendarBookingRecipients(
			CalendarBooking calendarBooking, NotificationType notificationType,
			NotificationTemplateType notificationTemplateType, User sender)
		throws Exception {

		NotificationSender notificationSender =
			NotificationSenderFactory.getNotificationSender(
				notificationType.toString());

		List<NotificationRecipient> notificationRecipients =
			_getNotificationRecipients(calendarBooking);

		for (NotificationRecipient notificationRecipient :
				notificationRecipients) {

			User user = notificationRecipient.getUser();

			if (user.equals(sender)) {
				continue;
			}

			NotificationTemplateContext notificationTemplateContext =
				NotificationTemplateContextFactory.getInstance(
					notificationType, notificationTemplateType, calendarBooking,
					user);

			notificationSender.sendNotification(
				sender.getEmailAddress(), sender.getFullName(),
				notificationRecipient, notificationTemplateContext);
		}
	}

	public static void notifyCalendarBookingReminders(
			CalendarBooking calendarBooking, long nowTime)
		throws Exception {

		List<NotificationRecipient> notificationRecipients =
			_getNotificationRecipients(calendarBooking);

		for (NotificationRecipient notificationRecipient :
				notificationRecipients) {

			User user = notificationRecipient.getUser();

			long startTime = calendarBooking.getStartTime();

			if (nowTime > startTime) {
				return;
			}

			NotificationType notificationType = null;

			long deltaTime = startTime - nowTime;

			if (_isInCheckInterval(
					deltaTime, calendarBooking.getFirstReminder())) {

				notificationType =
					calendarBooking.getFirstReminderNotificationType();
			}
			else if (_isInCheckInterval(
						deltaTime, calendarBooking.getSecondReminder())) {

				notificationType =
					calendarBooking.getSecondReminderNotificationType();
			}

			if (notificationType == null) {
				continue;
			}

			NotificationSender notificationSender =
				NotificationSenderFactory.getNotificationSender(
					notificationType.toString());

			NotificationTemplateContext notificationTemplateContext =
				NotificationTemplateContextFactory.getInstance(
					notificationType, NotificationTemplateType.REMINDER,
					calendarBooking, user);

			notificationSender.sendNotification(
				user.getEmailAddress(), user.getFullName(),
				notificationRecipient, notificationTemplateContext);
		}
	}

	private static List<NotificationRecipient> _getNotificationRecipients(
			CalendarBooking calendarBooking)
		throws Exception {

		List<NotificationRecipient> notificationRecipients = new ArrayList<>();

		CalendarResource calendarResource =
			calendarBooking.getCalendarResource();

		Set<User> users = new HashSet<>();

		users.add(UserLocalServiceUtil.fetchUser(calendarBooking.getUserId()));
		users.add(UserLocalServiceUtil.fetchUser(calendarResource.getUserId()));

		for (User user : users) {
			if (user == null) {
				continue;
			}

			notificationRecipients.add(new NotificationRecipient(user));
		}

		return notificationRecipients;
	}

	private static boolean _isInCheckInterval(
		long deltaTime, long intervalStart) {

		long intervalEnd = intervalStart + _CHECK_INTERVAL;

		if ((intervalStart <= deltaTime) && (deltaTime < intervalEnd)) {
			return true;
		}

		return false;
	}

	private static final long _CHECK_INTERVAL =
		CalendarServiceConfigurationValues.
			CALENDAR_NOTIFICATION_CHECK_INTERVAL * Time.MINUTE;

}