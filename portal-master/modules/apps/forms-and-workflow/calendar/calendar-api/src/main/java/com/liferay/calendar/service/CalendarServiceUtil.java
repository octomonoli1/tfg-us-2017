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
 * Provides the remote service utility for Calendar. This utility wraps
 * {@link com.liferay.calendar.service.impl.CalendarServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Eduardo Lundgren
 * @see CalendarService
 * @see com.liferay.calendar.service.base.CalendarServiceBaseImpl
 * @see com.liferay.calendar.service.impl.CalendarServiceImpl
 * @generated
 */
@ProviderType
public class CalendarServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.calendar.service.impl.CalendarServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean isManageableFromGroup(long calendarId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().isManageableFromGroup(calendarId, groupId);
	}

	public static com.liferay.calendar.model.Calendar addCalendar(
		long groupId, long calendarResourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String timeZoneId, int color, boolean defaultCalendar,
		boolean enableComments, boolean enableRatings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCalendar(groupId, calendarResourceId, nameMap,
			descriptionMap, timeZoneId, color, defaultCalendar, enableComments,
			enableRatings, serviceContext);
	}

	public static com.liferay.calendar.model.Calendar deleteCalendar(
		long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCalendar(calendarId);
	}

	public static com.liferay.calendar.model.Calendar fetchCalendar(
		long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchCalendar(calendarId);
	}

	public static com.liferay.calendar.model.Calendar getCalendar(
		long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCalendar(calendarId);
	}

	public static com.liferay.calendar.model.Calendar updateCalendar(
		long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCalendar(calendarId, nameMap, descriptionMap, color,
			serviceContext);
	}

	public static com.liferay.calendar.model.Calendar updateCalendar(
		long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String timeZoneId, int color, boolean defaultCalendar,
		boolean enableComments, boolean enableRatings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCalendar(calendarId, nameMap, descriptionMap,
			timeZoneId, color, defaultCalendar, enableComments, enableRatings,
			serviceContext);
	}

	public static com.liferay.calendar.model.Calendar updateColor(
		long calendarId, int color,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateColor(calendarId, color, serviceContext);
	}

	public static int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String keywords,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCount(companyId, groupIds, calendarResourceIds,
			keywords, andOperator);
	}

	public static int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String keywords,
		boolean andOperator, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCount(companyId, groupIds, calendarResourceIds,
			keywords, andOperator, actionId);
	}

	public static int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String name,
		java.lang.String description, boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCount(companyId, groupIds, calendarResourceIds, name,
			description, andOperator);
	}

	public static int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String name,
		java.lang.String description, boolean andOperator,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCount(companyId, groupIds, calendarResourceIds, name,
			description, andOperator, actionId);
	}

	public static java.lang.String exportCalendar(long calendarId,
		java.lang.String type) throws java.lang.Exception {
		return getService().exportCalendar(calendarId, type);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> getCalendarResourceCalendars(
		long groupId, long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCalendarResourceCalendars(groupId, calendarResourceId);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> getCalendarResourceCalendars(
		long groupId, long calendarResourceId, boolean defaultCalendar)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCalendarResourceCalendars(groupId, calendarResourceId,
			defaultCalendar);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String keywords, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.Calendar> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .search(companyId, groupIds, calendarResourceIds, keywords,
			andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String keywords, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.Calendar> orderByComparator,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .search(companyId, groupIds, calendarResourceIds, keywords,
			andOperator, start, end, orderByComparator, actionId);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.Calendar> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .search(companyId, groupIds, calendarResourceIds, name,
			description, andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.Calendar> orderByComparator,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .search(companyId, groupIds, calendarResourceIds, name,
			description, andOperator, start, end, orderByComparator, actionId);
	}

	public static void importCalendar(long calendarId, java.lang.String data,
		java.lang.String type) throws java.lang.Exception {
		getService().importCalendar(calendarId, data, type);
	}

	public static CalendarService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CalendarService, CalendarService> _serviceTracker =
		ServiceTrackerFactory.open(CalendarService.class);
}