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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PermissionedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CalendarBooking service. Represents a row in the &quot;CalendarBooking&quot; database table, with each column mapped to a property of this class.
 *
 * @author Eduardo Lundgren
 * @see CalendarBookingModel
 * @see com.liferay.calendar.model.impl.CalendarBookingImpl
 * @see com.liferay.calendar.model.impl.CalendarBookingModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.calendar.model.impl.CalendarBookingImpl")
@ProviderType
public interface CalendarBooking extends CalendarBookingModel, PermissionedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.calendar.model.impl.CalendarBookingImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CalendarBooking, Long> CALENDAR_BOOKING_ID_ACCESSOR =
		new Accessor<CalendarBooking, Long>() {
			@Override
			public Long get(CalendarBooking calendarBooking) {
				return calendarBooking.getCalendarBookingId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CalendarBooking> getTypeClass() {
				return CalendarBooking.class;
			}
		};

	public Calendar getCalendar()
		throws com.liferay.portal.kernel.exception.PortalException;

	public CalendarResource getCalendarResource()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.List<CalendarBooking> getChildCalendarBookings();

	public long getDuration();

	public com.liferay.calendar.notification.NotificationType getFirstReminderNotificationType();

	@com.liferay.portal.kernel.json.JSON()
	public int getInstanceIndex();

	public CalendarBooking getParentCalendarBooking()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.calendar.recurrence.Recurrence getRecurrenceObj();

	public com.liferay.calendar.notification.NotificationType getSecondReminderNotificationType();

	public java.util.TimeZone getTimeZone();

	public boolean isMasterBooking();

	public boolean isRecurring();

	@com.liferay.portal.kernel.json.JSON()
	public void setInstanceIndex(int instanceIndex);
}