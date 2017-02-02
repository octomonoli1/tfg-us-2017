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

package com.liferay.calendar.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.calendar.service.CalendarBookingServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CalendarBookingServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarBookingServiceSoap
 * @see HttpPrincipal
 * @see CalendarBookingServiceUtil
 * @generated
 */
@ProviderType
public class CalendarBookingServiceHttp {
	public static com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		HttpPrincipal httpPrincipal, long calendarId, long[] childCalendarIds,
		long parentCalendarBookingId,
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
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"addCalendarBooking", _addCalendarBookingParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarId, childCalendarIds, parentCalendarBookingId,
					titleMap, descriptionMap, location, startTimeYear,
					startTimeMonth, startTimeDay, startTimeHour,
					startTimeMinute, endTimeYear, endTimeMonth, endTimeDay,
					endTimeHour, endTimeMinute, timeZoneId, allDay, recurrence,
					firstReminder, firstReminderType, secondReminder,
					secondReminderType, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		HttpPrincipal httpPrincipal, long calendarId, long[] childCalendarIds,
		long parentCalendarBookingId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"addCalendarBooking", _addCalendarBookingParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarId, childCalendarIds, parentCalendarBookingId,
					titleMap, descriptionMap, location, startTime, endTime,
					allDay, recurrence, firstReminder, firstReminderType,
					secondReminder, secondReminderType, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking deleteCalendarBooking(
		HttpPrincipal httpPrincipal, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"deleteCalendarBooking",
					_deleteCalendarBookingParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCalendarBookingInstance(
		HttpPrincipal httpPrincipal, long calendarBookingId, int instanceIndex,
		boolean allFollowing)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"deleteCalendarBookingInstance",
					_deleteCalendarBookingInstanceParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId, instanceIndex, allFollowing);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCalendarBookingInstance(
		HttpPrincipal httpPrincipal, long calendarBookingId, long startTime,
		boolean allFollowing)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"deleteCalendarBookingInstance",
					_deleteCalendarBookingInstanceParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId, startTime, allFollowing);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String exportCalendarBooking(
		HttpPrincipal httpPrincipal, long calendarBookingId,
		java.lang.String type) throws java.lang.Exception {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"exportCalendarBooking",
					_exportCalendarBookingParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId, type);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof java.lang.Exception) {
					throw (java.lang.Exception)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking fetchCalendarBooking(
		HttpPrincipal httpPrincipal, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"fetchCalendarBooking", _fetchCalendarBookingParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		HttpPrincipal httpPrincipal, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"getCalendarBooking", _getCalendarBookingParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		HttpPrincipal httpPrincipal, long calendarId,
		long parentCalendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"getCalendarBooking", _getCalendarBookingParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarId, parentCalendarBookingId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking getCalendarBookingInstance(
		HttpPrincipal httpPrincipal, long calendarBookingId, int instanceIndex)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"getCalendarBookingInstance",
					_getCalendarBookingInstanceParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId, instanceIndex);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		HttpPrincipal httpPrincipal, long calendarId, int[] statuses)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"getCalendarBookings", _getCalendarBookingsParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarId, statuses);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.calendar.model.CalendarBooking>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		HttpPrincipal httpPrincipal, long calendarId, long startTime,
		long endTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"getCalendarBookings", _getCalendarBookingsParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarId, startTime, endTime);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.calendar.model.CalendarBooking>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		HttpPrincipal httpPrincipal, long calendarId, long startTime,
		long endTime, int max)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"getCalendarBookings", _getCalendarBookingsParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarId, startTime, endTime, max);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.calendar.model.CalendarBooking>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getCalendarBookingsRSS(
		HttpPrincipal httpPrincipal, long calendarId, long startTime,
		long endTime, int max, java.lang.String type, double version,
		java.lang.String displayStyle,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"getCalendarBookingsRSS",
					_getCalendarBookingsRSSParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarId, startTime, endTime, max, type, version,
					displayStyle, themeDisplay);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getChildCalendarBookings(
		HttpPrincipal httpPrincipal, long parentCalendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"getChildCalendarBookings",
					_getChildCalendarBookingsParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentCalendarBookingId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.calendar.model.CalendarBooking>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getChildCalendarBookings(
		HttpPrincipal httpPrincipal, long parentCalendarBookingId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"getChildCalendarBookings",
					_getChildCalendarBookingsParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentCalendarBookingId, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.calendar.model.CalendarBooking>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking getNewStartTimeAndDurationCalendarBooking(
		HttpPrincipal httpPrincipal, long calendarBookingId, long offset,
		long duration)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"getNewStartTimeAndDurationCalendarBooking",
					_getNewStartTimeAndDurationCalendarBookingParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId, offset, duration);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean hasChildCalendarBookings(
		HttpPrincipal httpPrincipal, long parentCalendarBookingId) {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"hasChildCalendarBookings",
					_hasChildCalendarBookingsParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentCalendarBookingId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void invokeTransition(HttpPrincipal httpPrincipal,
		long calendarBookingId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"invokeTransition", _invokeTransitionParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId, status, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking moveCalendarBookingToTrash(
		HttpPrincipal httpPrincipal, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"moveCalendarBookingToTrash",
					_moveCalendarBookingToTrashParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking restoreCalendarBookingFromTrash(
		HttpPrincipal httpPrincipal, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"restoreCalendarBookingFromTrash",
					_restoreCalendarBookingFromTrashParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		HttpPrincipal httpPrincipal, long companyId, long[] groupIds,
		long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String keywords,
		long startTime, long endTime, boolean recurring, int[] statuses,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarBooking> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"search", _searchParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupIds, calendarIds, calendarResourceIds,
					parentCalendarBookingId, keywords, startTime, endTime,
					recurring, statuses, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.calendar.model.CalendarBooking>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		HttpPrincipal httpPrincipal, long companyId, long[] groupIds,
		long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String title,
		java.lang.String description, java.lang.String location,
		long startTime, long endTime, boolean recurring, int[] statuses,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarBooking> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"search", _searchParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupIds, calendarIds, calendarResourceIds,
					parentCalendarBookingId, title, description, location,
					startTime, endTime, recurring, statuses, andOperator,
					start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.calendar.model.CalendarBooking>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCount(HttpPrincipal httpPrincipal, long companyId,
		long[] groupIds, long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String keywords,
		long startTime, long endTime, boolean recurring, int[] statuses)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"searchCount", _searchCountParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupIds, calendarIds, calendarResourceIds,
					parentCalendarBookingId, keywords, startTime, endTime,
					recurring, statuses);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCount(HttpPrincipal httpPrincipal, long companyId,
		long[] groupIds, long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String title,
		java.lang.String description, java.lang.String location,
		long startTime, long endTime, boolean recurring, int[] statuses,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"searchCount", _searchCountParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupIds, calendarIds, calendarResourceIds,
					parentCalendarBookingId, title, description, location,
					startTime, endTime, recurring, statuses, andOperator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		HttpPrincipal httpPrincipal, long calendarBookingId, long calendarId,
		long[] childCalendarIds,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"updateCalendarBooking",
					_updateCalendarBookingParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId, calendarId, childCalendarIds, titleMap,
					descriptionMap, location, startTime, endTime, allDay,
					recurrence, firstReminder, firstReminderType,
					secondReminder, secondReminderType, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		HttpPrincipal httpPrincipal, long calendarBookingId, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"updateCalendarBooking",
					_updateCalendarBookingParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId, calendarId, titleMap, descriptionMap,
					location, startTime, endTime, allDay, recurrence,
					firstReminder, firstReminderType, secondReminder,
					secondReminderType, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBookingInstance(
		HttpPrincipal httpPrincipal, long calendarBookingId, int instanceIndex,
		long calendarId, long[] childCalendarIds,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, boolean allFollowing,
		long firstReminder, java.lang.String firstReminderType,
		long secondReminder, java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"updateCalendarBookingInstance",
					_updateCalendarBookingInstanceParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId, instanceIndex, calendarId,
					childCalendarIds, titleMap, descriptionMap, location,
					startTime, endTime, allDay, recurrence, allFollowing,
					firstReminder, firstReminderType, secondReminder,
					secondReminderType, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBookingInstance(
		HttpPrincipal httpPrincipal, long calendarBookingId, int instanceIndex,
		long calendarId,
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
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"updateCalendarBookingInstance",
					_updateCalendarBookingInstanceParameterTypes28);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId, instanceIndex, calendarId, titleMap,
					descriptionMap, location, startTimeYear, startTimeMonth,
					startTimeDay, startTimeHour, startTimeMinute, endTimeYear,
					endTimeMonth, endTimeDay, endTimeHour, endTimeMinute,
					timeZoneId, allDay, recurrence, allFollowing,
					firstReminder, firstReminderType, secondReminder,
					secondReminderType, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBookingInstance(
		HttpPrincipal httpPrincipal, long calendarBookingId, int instanceIndex,
		long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, boolean allFollowing,
		long firstReminder, java.lang.String firstReminderType,
		long secondReminder, java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"updateCalendarBookingInstance",
					_updateCalendarBookingInstanceParameterTypes29);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId, instanceIndex, calendarId, titleMap,
					descriptionMap, location, startTime, endTime, allDay,
					recurrence, allFollowing, firstReminder, firstReminderType,
					secondReminder, secondReminderType, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking updateOffsetAndDuration(
		HttpPrincipal httpPrincipal, long calendarBookingId, long calendarId,
		long[] childCalendarIds,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long offset, long duration, boolean allDay,
		java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"updateOffsetAndDuration",
					_updateOffsetAndDurationParameterTypes30);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId, calendarId, childCalendarIds, titleMap,
					descriptionMap, location, offset, duration, allDay,
					recurrence, firstReminder, firstReminderType,
					secondReminder, secondReminderType, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.calendar.model.CalendarBooking updateOffsetAndDuration(
		HttpPrincipal httpPrincipal, long calendarBookingId, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long offset, long duration, boolean allDay,
		java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CalendarBookingServiceUtil.class,
					"updateOffsetAndDuration",
					_updateOffsetAndDurationParameterTypes31);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					calendarBookingId, calendarId, titleMap, descriptionMap,
					location, offset, duration, allDay, recurrence,
					firstReminder, firstReminderType, secondReminder,
					secondReminderType, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.calendar.model.CalendarBooking)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CalendarBookingServiceHttp.class);
	private static final Class<?>[] _addCalendarBookingParameterTypes0 = new Class[] {
			long.class, long[].class, long.class, java.util.Map.class,
			java.util.Map.class, java.lang.String.class, int.class, int.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			int.class, int.class, java.lang.String.class, boolean.class,
			java.lang.String.class, long.class, java.lang.String.class,
			long.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addCalendarBookingParameterTypes1 = new Class[] {
			long.class, long[].class, long.class, java.util.Map.class,
			java.util.Map.class, java.lang.String.class, long.class, long.class,
			boolean.class, java.lang.String.class, long.class,
			java.lang.String.class, long.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCalendarBookingParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteCalendarBookingInstanceParameterTypes3 =
		new Class[] { long.class, int.class, boolean.class };
	private static final Class<?>[] _deleteCalendarBookingInstanceParameterTypes4 =
		new Class[] { long.class, long.class, boolean.class };
	private static final Class<?>[] _exportCalendarBookingParameterTypes5 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _fetchCalendarBookingParameterTypes6 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCalendarBookingParameterTypes7 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCalendarBookingParameterTypes8 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getCalendarBookingInstanceParameterTypes9 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _getCalendarBookingsParameterTypes10 = new Class[] {
			long.class, int[].class
		};
	private static final Class<?>[] _getCalendarBookingsParameterTypes11 = new Class[] {
			long.class, long.class, long.class
		};
	private static final Class<?>[] _getCalendarBookingsParameterTypes12 = new Class[] {
			long.class, long.class, long.class, int.class
		};
	private static final Class<?>[] _getCalendarBookingsRSSParameterTypes13 = new Class[] {
			long.class, long.class, long.class, int.class,
			java.lang.String.class, double.class, java.lang.String.class,
			com.liferay.portal.kernel.theme.ThemeDisplay.class
		};
	private static final Class<?>[] _getChildCalendarBookingsParameterTypes14 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getChildCalendarBookingsParameterTypes15 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _getNewStartTimeAndDurationCalendarBookingParameterTypes16 =
		new Class[] { long.class, long.class, long.class };
	private static final Class<?>[] _hasChildCalendarBookingsParameterTypes17 = new Class[] {
			long.class
		};
	private static final Class<?>[] _invokeTransitionParameterTypes18 = new Class[] {
			long.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveCalendarBookingToTrashParameterTypes19 = new Class[] {
			long.class
		};
	private static final Class<?>[] _restoreCalendarBookingFromTrashParameterTypes20 =
		new Class[] { long.class };
	private static final Class<?>[] _searchParameterTypes21 = new Class[] {
			long.class, long[].class, long[].class, long[].class, long.class,
			java.lang.String.class, long.class, long.class, boolean.class,
			int[].class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchParameterTypes22 = new Class[] {
			long.class, long[].class, long[].class, long[].class, long.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, long.class, long.class, boolean.class,
			int[].class, boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchCountParameterTypes23 = new Class[] {
			long.class, long[].class, long[].class, long[].class, long.class,
			java.lang.String.class, long.class, long.class, boolean.class,
			int[].class
		};
	private static final Class<?>[] _searchCountParameterTypes24 = new Class[] {
			long.class, long[].class, long[].class, long[].class, long.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, long.class, long.class, boolean.class,
			int[].class, boolean.class
		};
	private static final Class<?>[] _updateCalendarBookingParameterTypes25 = new Class[] {
			long.class, long.class, long[].class, java.util.Map.class,
			java.util.Map.class, java.lang.String.class, long.class, long.class,
			boolean.class, java.lang.String.class, long.class,
			java.lang.String.class, long.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateCalendarBookingParameterTypes26 = new Class[] {
			long.class, long.class, java.util.Map.class, java.util.Map.class,
			java.lang.String.class, long.class, long.class, boolean.class,
			java.lang.String.class, long.class, java.lang.String.class,
			long.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateCalendarBookingInstanceParameterTypes27 =
		new Class[] {
			long.class, int.class, long.class, long[].class, java.util.Map.class,
			java.util.Map.class, java.lang.String.class, long.class, long.class,
			boolean.class, java.lang.String.class, boolean.class, long.class,
			java.lang.String.class, long.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateCalendarBookingInstanceParameterTypes28 =
		new Class[] {
			long.class, int.class, long.class, java.util.Map.class,
			java.util.Map.class, java.lang.String.class, int.class, int.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			int.class, int.class, java.lang.String.class, boolean.class,
			java.lang.String.class, boolean.class, long.class,
			java.lang.String.class, long.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateCalendarBookingInstanceParameterTypes29 =
		new Class[] {
			long.class, int.class, long.class, java.util.Map.class,
			java.util.Map.class, java.lang.String.class, long.class, long.class,
			boolean.class, java.lang.String.class, boolean.class, long.class,
			java.lang.String.class, long.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateOffsetAndDurationParameterTypes30 = new Class[] {
			long.class, long.class, long[].class, java.util.Map.class,
			java.util.Map.class, java.lang.String.class, long.class, long.class,
			boolean.class, java.lang.String.class, long.class,
			java.lang.String.class, long.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateOffsetAndDurationParameterTypes31 = new Class[] {
			long.class, long.class, java.util.Map.class, java.util.Map.class,
			java.lang.String.class, long.class, long.class, boolean.class,
			java.lang.String.class, long.class, java.lang.String.class,
			long.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}