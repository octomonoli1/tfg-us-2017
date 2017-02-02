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
 * The extended model interface for the CalendarResource service. Represents a row in the &quot;CalendarResource&quot; database table, with each column mapped to a property of this class.
 *
 * @author Eduardo Lundgren
 * @see CalendarResourceModel
 * @see com.liferay.calendar.model.impl.CalendarResourceImpl
 * @see com.liferay.calendar.model.impl.CalendarResourceModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.calendar.model.impl.CalendarResourceImpl")
@ProviderType
public interface CalendarResource extends CalendarResourceModel,
	PermissionedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.calendar.model.impl.CalendarResourceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CalendarResource, Long> CALENDAR_RESOURCE_ID_ACCESSOR =
		new Accessor<CalendarResource, Long>() {
			@Override
			public Long get(CalendarResource calendarResource) {
				return calendarResource.getCalendarResourceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CalendarResource> getTypeClass() {
				return CalendarResource.class;
			}
		};

	public java.util.List<Calendar> getCalendars();

	public Calendar getDefaultCalendar();

	public long getDefaultCalendarId();

	public java.util.TimeZone getTimeZone()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.lang.String getTimeZoneId()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isGroup();

	public boolean isUser();
}