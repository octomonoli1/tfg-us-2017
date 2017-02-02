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

package com.liferay.calendar.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CalendarBooking. This utility wraps
 * {@link com.liferay.calendar.service.impl.CalendarBookingServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Eduardo Lundgren
 * @see CalendarBookingService
 * @see com.liferay.calendar.service.base.CalendarBookingServiceBaseImpl
 * @see com.liferay.calendar.service.impl.CalendarBookingServiceImpl
 * @generated
 */
@ProviderType
public class CalendarBookingServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.calendar.service.impl.CalendarBookingServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasChildCalendarBookings(long parentCalendarBookingId) {
		return getService().hasChildCalendarBookings(parentCalendarBookingId);
	}

	public static com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		long calendarId, long[] childCalendarIds, long parentCalendarBookingId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, int startTimeYear, int startTimeMonth,
		int startTimeDay, int startTimeHour, int startTimeMinute,
		int endTimeYear, int endTimeMonth, int endTimeDay, int endTimeHour,
		int endTimeMinute, java.lang.String timeZoneId, boolean allDay,
		java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCalendarBooking(calendarId, childCalendarIds,
			parentCalendarBookingId, titleMap, descriptionMap, location,
			startTimeYear, startTimeMonth, startTimeDay, startTimeHour,
			startTimeMinute, endTimeYear, endTimeMonth, endTimeDay,
			endTimeHour, endTimeMinute, timeZoneId, allDay, recurrence,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	public static com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		long calendarId, long[] childCalendarIds, long parentCalendarBookingId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCalendarBooking(calendarId, childCalendarIds,
			parentCalendarBookingId, titleMap, descriptionMap, location,
			startTime, endTime, allDay, recurrence, firstReminder,
			firstReminderType, secondReminder, secondReminderType,
			serviceContext);
	}

	public static com.liferay.calendar.model.CalendarBooking deleteCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCalendarBooking(calendarBookingId);
	}

	public static com.liferay.calendar.model.CalendarBooking fetchCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchCalendarBooking(calendarBookingId);
	}

	public static com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCalendarBooking(calendarBookingId);
	}

	public static com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarId, long parentCalendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCalendarBooking(calendarId, parentCalendarBookingId);
	}

	public static com.liferay.calendar.model.CalendarBooking getCalendarBookingInstance(
		long calendarBookingId, int instanceIndex)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCalendarBookingInstance(calendarBookingId, instanceIndex);
	}

	public static com.liferay.calendar.model.CalendarBooking getNewStartTimeAndDurationCalendarBooking(
		long calendarBookingId, long offset, long duration)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getNewStartTimeAndDurationCalendarBooking(calendarBookingId,
			offset, duration);
	}

	public static com.liferay.calendar.model.CalendarBooking moveCalendarBookingToTrash(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveCalendarBookingToTrash(calendarBookingId);
	}

	public static com.liferay.calendar.model.CalendarBooking restoreCalendarBookingFromTrash(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().restoreCalendarBookingFromTrash(calendarBookingId);
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long calendarBookingId, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCalendarBooking(calendarBookingId, calendarId,
			titleMap, descriptionMap, location, startTime, endTime, allDay,
			recurrence, firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long calendarBookingId, long calendarId, long[] childCalendarIds,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCalendarBooking(calendarBookingId, calendarId,
			childCalendarIds, titleMap, descriptionMap, location, startTime,
			endTime, allDay, recurrence, firstReminder, firstReminderType,
			secondReminder, secondReminderType, serviceContext);
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBookingInstance(
		long calendarBookingId, int instanceIndex, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, int startTimeYear, int startTimeMonth,
		int startTimeDay, int startTimeHour, int startTimeMinute,
		int endTimeYear, int endTimeMonth, int endTimeDay, int endTimeHour,
		int endTimeMinute, java.lang.String timeZoneId, boolean allDay,
		java.lang.String recurrence, boolean allFollowing, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCalendarBookingInstance(calendarBookingId,
			instanceIndex, calendarId, titleMap, descriptionMap, location,
			startTimeYear, startTimeMonth, startTimeDay, startTimeHour,
			startTimeMinute, endTimeYear, endTimeMonth, endTimeDay,
			endTimeHour, endTimeMinute, timeZoneId, allDay, recurrence,
			allFollowing, firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBookingInstance(
		long calendarBookingId, int instanceIndex, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, boolean allFollowing,
		long firstReminder, java.lang.String firstReminderType,
		long secondReminder, java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCalendarBookingInstance(calendarBookingId,
			instanceIndex, calendarId, titleMap, descriptionMap, location,
			startTime, endTime, allDay, recurrence, allFollowing,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBookingInstance(
		long calendarBookingId, int instanceIndex, long calendarId,
		long[] childCalendarIds,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, boolean allFollowing,
		long firstReminder, java.lang.String firstReminderType,
		long secondReminder, java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCalendarBookingInstance(calendarBookingId,
			instanceIndex, calendarId, childCalendarIds, titleMap,
			descriptionMap, location, startTime, endTime, allDay, recurrence,
			allFollowing, firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	public static com.liferay.calendar.model.CalendarBooking updateOffsetAndDuration(
		long calendarBookingId, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long offset, long duration, boolean allDay,
		java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateOffsetAndDuration(calendarBookingId, calendarId,
			titleMap, descriptionMap, location, offset, duration, allDay,
			recurrence, firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	public static com.liferay.calendar.model.CalendarBooking updateOffsetAndDuration(
		long calendarBookingId, long calendarId, long[] childCalendarIds,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long offset, long duration, boolean allDay,
		java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateOffsetAndDuration(calendarBookingId, calendarId,
			childCalendarIds, titleMap, descriptionMap, location, offset,
			duration, allDay, recurrence, firstReminder, firstReminderType,
			secondReminder, secondReminderType, serviceContext);
	}

	public static int searchCount(long companyId, long[] groupIds,
		long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String keywords,
		long startTime, long endTime, boolean recurring, int[] statuses)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCount(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startTime,
			endTime, recurring, statuses);
	}

	public static int searchCount(long companyId, long[] groupIds,
		long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String title,
		java.lang.String description, java.lang.String location,
		long startTime, long endTime, boolean recurring, int[] statuses,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCount(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, title, description,
			location, startTime, endTime, recurring, statuses, andOperator);
	}

	public static java.lang.String exportCalendarBooking(
		long calendarBookingId, java.lang.String type)
		throws java.lang.Exception {
		return getService().exportCalendarBooking(calendarBookingId, type);
	}

	public static java.lang.String getCalendarBookingsRSS(long calendarId,
		long startTime, long endTime, int max, java.lang.String type,
		double version, java.lang.String displayStyle,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCalendarBookingsRSS(calendarId, startTime, endTime, max,
			type, version, displayStyle, themeDisplay);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, int[] statuses)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCalendarBookings(calendarId, statuses);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, long startTime, long endTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCalendarBookings(calendarId, startTime, endTime);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, long startTime, long endTime, int max)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCalendarBookings(calendarId, startTime, endTime, max);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getChildCalendarBookings(
		long parentCalendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getChildCalendarBookings(parentCalendarBookingId);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getChildCalendarBookings(
		long parentCalendarBookingId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getChildCalendarBookings(parentCalendarBookingId, status);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, long startTime, long endTime,
		boolean recurring, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarBooking> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .search(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startTime,
			endTime, recurring, statuses, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, long startTime, long endTime,
		boolean recurring, int[] statuses, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarBooking> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .search(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, title, description,
			location, startTime, endTime, recurring, statuses, andOperator,
			start, end, orderByComparator);
	}

	public static void deleteCalendarBookingInstance(long calendarBookingId,
		int instanceIndex, boolean allFollowing)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteCalendarBookingInstance(calendarBookingId, instanceIndex,
			allFollowing);
	}

	public static void deleteCalendarBookingInstance(long calendarBookingId,
		long startTime, boolean allFollowing)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteCalendarBookingInstance(calendarBookingId, startTime,
			allFollowing);
	}

	public static void invokeTransition(long calendarBookingId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().invokeTransition(calendarBookingId, status, serviceContext);
	}

	public static CalendarBookingService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CalendarBookingService, CalendarBookingService> _serviceTracker =
		ServiceTrackerFactory.open(CalendarBookingService.class);
}