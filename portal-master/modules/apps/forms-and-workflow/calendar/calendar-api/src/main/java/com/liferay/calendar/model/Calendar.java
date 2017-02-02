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
 * The extended model interface for the Calendar service. Represents a row in the &quot;Calendar&quot; database table, with each column mapped to a property of this class.
 *
 * @author Eduardo Lundgren
 * @see CalendarModel
 * @see com.liferay.calendar.model.impl.CalendarImpl
 * @see com.liferay.calendar.model.impl.CalendarModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.calendar.model.impl.CalendarImpl")
@ProviderType
public interface Calendar extends CalendarModel, PermissionedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.calendar.model.impl.CalendarImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Calendar, Long> CALENDAR_ID_ACCESSOR = new Accessor<Calendar, Long>() {
			@Override
			public Long get(Calendar calendar) {
				return calendar.getCalendarId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Calendar> getTypeClass() {
				return Calendar.class;
			}
		};

	public CalendarResource getCalendarResource()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.TimeZone getTimeZone();
}