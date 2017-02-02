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
 * Provides a wrapper for {@link CalendarService}.
 *
 * @author Eduardo Lundgren
 * @see CalendarService
 * @generated
 */
@ProviderType
public class CalendarServiceWrapper implements CalendarService,
	ServiceWrapper<CalendarService> {
	public CalendarServiceWrapper(CalendarService calendarService) {
		_calendarService = calendarService;
	}

	@Override
	public boolean isManageableFromGroup(long calendarId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.isManageableFromGroup(calendarId, groupId);
	}

	@Override
	public com.liferay.calendar.model.Calendar addCalendar(long groupId,
		long calendarResourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String timeZoneId, int color, boolean defaultCalendar,
		boolean enableComments, boolean enableRatings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.addCalendar(groupId, calendarResourceId,
			nameMap, descriptionMap, timeZoneId, color, defaultCalendar,
			enableComments, enableRatings, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.Calendar deleteCalendar(long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.deleteCalendar(calendarId);
	}

	@Override
	public com.liferay.calendar.model.Calendar fetchCalendar(long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.fetchCalendar(calendarId);
	}

	@Override
	public com.liferay.calendar.model.Calendar getCalendar(long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.getCalendar(calendarId);
	}

	@Override
	public com.liferay.calendar.model.Calendar updateCalendar(long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.updateCalendar(calendarId, nameMap,
			descriptionMap, color, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.Calendar updateCalendar(long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String timeZoneId, int color, boolean defaultCalendar,
		boolean enableComments, boolean enableRatings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.updateCalendar(calendarId, nameMap,
			descriptionMap, timeZoneId, color, defaultCalendar, enableComments,
			enableRatings, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.Calendar updateColor(long calendarId,
		int color,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.updateColor(calendarId, color, serviceContext);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String keywords,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.searchCount(companyId, groupIds,
			calendarResourceIds, keywords, andOperator);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String keywords,
		boolean andOperator, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.searchCount(companyId, groupIds,
			calendarResourceIds, keywords, andOperator, actionId);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String name,
		java.lang.String description, boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.searchCount(companyId, groupIds,
			calendarResourceIds, name, description, andOperator);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String name,
		java.lang.String description, boolean andOperator,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.searchCount(companyId, groupIds,
			calendarResourceIds, name, description, andOperator, actionId);
	}

	@Override
	public java.lang.String exportCalendar(long calendarId,
		java.lang.String type) throws java.lang.Exception {
		return _calendarService.exportCalendar(calendarId, type);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _calendarService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> getCalendarResourceCalendars(
		long groupId, long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.getCalendarResourceCalendars(groupId,
			calendarResourceId);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> getCalendarResourceCalendars(
		long groupId, long calendarResourceId, boolean defaultCalendar)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.getCalendarResourceCalendars(groupId,
			calendarResourceId, defaultCalendar);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String keywords, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.Calendar> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.search(companyId, groupIds,
			calendarResourceIds, keywords, andOperator, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String keywords, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.Calendar> orderByComparator,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.search(companyId, groupIds,
			calendarResourceIds, keywords, andOperator, start, end,
			orderByComparator, actionId);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.Calendar> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.search(companyId, groupIds,
			calendarResourceIds, name, description, andOperator, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.Calendar> orderByComparator,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarService.search(companyId, groupIds,
			calendarResourceIds, name, description, andOperator, start, end,
			orderByComparator, actionId);
	}

	@Override
	public void importCalendar(long calendarId, java.lang.String data,
		java.lang.String type) throws java.lang.Exception {
		_calendarService.importCalendar(calendarId, data, type);
	}

	@Override
	public CalendarService getWrappedService() {
		return _calendarService;
	}

	@Override
	public void setWrappedService(CalendarService calendarService) {
		_calendarService = calendarService;
	}

	private CalendarService _calendarService;
}