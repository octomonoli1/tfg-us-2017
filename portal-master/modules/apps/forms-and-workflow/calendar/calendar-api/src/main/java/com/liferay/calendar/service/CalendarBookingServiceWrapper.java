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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CalendarBookingService}.
 *
 * @author Eduardo Lundgren
 * @see CalendarBookingService
 * @generated
 */
@ProviderType
public class CalendarBookingServiceWrapper implements CalendarBookingService,
	ServiceWrapper<CalendarBookingService> {
	public CalendarBookingServiceWrapper(
		CalendarBookingService calendarBookingService) {
		_calendarBookingService = calendarBookingService;
	}

	@Override
	public boolean hasChildCalendarBookings(long parentCalendarBookingId) {
		return _calendarBookingService.hasChildCalendarBookings(parentCalendarBookingId);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
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
		return _calendarBookingService.addCalendarBooking(calendarId,
			childCalendarIds, parentCalendarBookingId, titleMap,
			descriptionMap, location, startTimeYear, startTimeMonth,
			startTimeDay, startTimeHour, startTimeMinute, endTimeYear,
			endTimeMonth, endTimeDay, endTimeHour, endTimeMinute, timeZoneId,
			allDay, recurrence, firstReminder, firstReminderType,
			secondReminder, secondReminderType, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		long calendarId, long[] childCalendarIds, long parentCalendarBookingId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.addCalendarBooking(calendarId,
			childCalendarIds, parentCalendarBookingId, titleMap,
			descriptionMap, location, startTime, endTime, allDay, recurrence,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking deleteCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.deleteCalendarBooking(calendarBookingId);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking fetchCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.fetchCalendarBooking(calendarBookingId);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.getCalendarBooking(calendarBookingId);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarId, long parentCalendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.getCalendarBooking(calendarId,
			parentCalendarBookingId);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking getCalendarBookingInstance(
		long calendarBookingId, int instanceIndex)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.getCalendarBookingInstance(calendarBookingId,
			instanceIndex);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking getNewStartTimeAndDurationCalendarBooking(
		long calendarBookingId, long offset, long duration)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.getNewStartTimeAndDurationCalendarBooking(calendarBookingId,
			offset, duration);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking moveCalendarBookingToTrash(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.moveCalendarBookingToTrash(calendarBookingId);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking restoreCalendarBookingFromTrash(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.restoreCalendarBookingFromTrash(calendarBookingId);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long calendarBookingId, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.updateCalendarBooking(calendarBookingId,
			calendarId, titleMap, descriptionMap, location, startTime, endTime,
			allDay, recurrence, firstReminder, firstReminderType,
			secondReminder, secondReminderType, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long calendarBookingId, long calendarId, long[] childCalendarIds,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.updateCalendarBooking(calendarBookingId,
			calendarId, childCalendarIds, titleMap, descriptionMap, location,
			startTime, endTime, allDay, recurrence, firstReminder,
			firstReminderType, secondReminder, secondReminderType,
			serviceContext);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking updateCalendarBookingInstance(
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
		return _calendarBookingService.updateCalendarBookingInstance(calendarBookingId,
			instanceIndex, calendarId, titleMap, descriptionMap, location,
			startTimeYear, startTimeMonth, startTimeDay, startTimeHour,
			startTimeMinute, endTimeYear, endTimeMonth, endTimeDay,
			endTimeHour, endTimeMinute, timeZoneId, allDay, recurrence,
			allFollowing, firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking updateCalendarBookingInstance(
		long calendarBookingId, int instanceIndex, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, boolean allFollowing,
		long firstReminder, java.lang.String firstReminderType,
		long secondReminder, java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.updateCalendarBookingInstance(calendarBookingId,
			instanceIndex, calendarId, titleMap, descriptionMap, location,
			startTime, endTime, allDay, recurrence, allFollowing,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking updateCalendarBookingInstance(
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
		return _calendarBookingService.updateCalendarBookingInstance(calendarBookingId,
			instanceIndex, calendarId, childCalendarIds, titleMap,
			descriptionMap, location, startTime, endTime, allDay, recurrence,
			allFollowing, firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking updateOffsetAndDuration(
		long calendarBookingId, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long offset, long duration, boolean allDay,
		java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.updateOffsetAndDuration(calendarBookingId,
			calendarId, titleMap, descriptionMap, location, offset, duration,
			allDay, recurrence, firstReminder, firstReminderType,
			secondReminder, secondReminderType, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking updateOffsetAndDuration(
		long calendarBookingId, long calendarId, long[] childCalendarIds,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long offset, long duration, boolean allDay,
		java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.updateOffsetAndDuration(calendarBookingId,
			calendarId, childCalendarIds, titleMap, descriptionMap, location,
			offset, duration, allDay, recurrence, firstReminder,
			firstReminderType, secondReminder, secondReminderType,
			serviceContext);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, long startTime, long endTime,
		boolean recurring, int[] statuses)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.searchCount(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId,
			keywords, startTime, endTime, recurring, statuses);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, long startTime, long endTime,
		boolean recurring, int[] statuses, boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.searchCount(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, title,
			description, location, startTime, endTime, recurring, statuses,
			andOperator);
	}

	@Override
	public java.lang.String exportCalendarBooking(long calendarBookingId,
		java.lang.String type) throws java.lang.Exception {
		return _calendarBookingService.exportCalendarBooking(calendarBookingId,
			type);
	}

	@Override
	public java.lang.String getCalendarBookingsRSS(long calendarId,
		long startTime, long endTime, int max, java.lang.String type,
		double version, java.lang.String displayStyle,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.getCalendarBookingsRSS(calendarId,
			startTime, endTime, max, type, version, displayStyle, themeDisplay);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _calendarBookingService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, int[] statuses)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.getCalendarBookings(calendarId, statuses);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, long startTime, long endTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.getCalendarBookings(calendarId,
			startTime, endTime);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, long startTime, long endTime, int max)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.getCalendarBookings(calendarId,
			startTime, endTime, max);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getChildCalendarBookings(
		long parentCalendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.getChildCalendarBookings(parentCalendarBookingId);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getChildCalendarBookings(
		long parentCalendarBookingId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.getChildCalendarBookings(parentCalendarBookingId,
			status);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, long startTime, long endTime,
		boolean recurring, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarBooking> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.search(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startTime,
			endTime, recurring, statuses, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, long startTime, long endTime,
		boolean recurring, int[] statuses, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarBooking> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingService.search(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, title, description,
			location, startTime, endTime, recurring, statuses, andOperator,
			start, end, orderByComparator);
	}

	@Override
	public void deleteCalendarBookingInstance(long calendarBookingId,
		int instanceIndex, boolean allFollowing)
		throws com.liferay.portal.kernel.exception.PortalException {
		_calendarBookingService.deleteCalendarBookingInstance(calendarBookingId,
			instanceIndex, allFollowing);
	}

	@Override
	public void deleteCalendarBookingInstance(long calendarBookingId,
		long startTime, boolean allFollowing)
		throws com.liferay.portal.kernel.exception.PortalException {
		_calendarBookingService.deleteCalendarBookingInstance(calendarBookingId,
			startTime, allFollowing);
	}

	@Override
	public void invokeTransition(long calendarBookingId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_calendarBookingService.invokeTransition(calendarBookingId, status,
			serviceContext);
	}

	@Override
	public CalendarBookingService getWrappedService() {
		return _calendarBookingService;
	}

	@Override
	public void setWrappedService(CalendarBookingService calendarBookingService) {
		_calendarBookingService = calendarBookingService;
	}

	private CalendarBookingService _calendarBookingService;
}