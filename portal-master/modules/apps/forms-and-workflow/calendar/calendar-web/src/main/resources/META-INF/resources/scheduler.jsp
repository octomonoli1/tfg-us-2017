<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String activeView = ParamUtil.getString(request, "activeView", sessionClicksDefaultView);
long date = ParamUtil.getLong(request, "date", System.currentTimeMillis());
String editCalendarBookingURL = ParamUtil.getString(request, "editCalendarBookingURL");
String filterCalendarBookings = ParamUtil.getString(request, "filterCalendarBookings", null);
boolean hideAgendaView = ParamUtil.getBoolean(request, "hideAgendaView");
boolean hideDayView = ParamUtil.getBoolean(request, "hideDayView");
boolean hideMonthView = ParamUtil.getBoolean(request, "hideMonthView");
boolean hideWeekView = ParamUtil.getBoolean(request, "hideWeekView");
String permissionsCalendarBookingURL = ParamUtil.getString(request, "permissionsCalendarBookingURL");
boolean preventPersistence = ParamUtil.getBoolean(request, "preventPersistence");
boolean readOnly = ParamUtil.getBoolean(request, "readOnly");
boolean showAddEventBtn = ParamUtil.getBoolean(request, "showAddEventBtn");
String viewCalendarBookingURL = ParamUtil.getString(request, "viewCalendarBookingURL");
%>

<div class="calendar-portlet-wrapper" id="<portlet:namespace />scheduler"></div>

<%@ include file="/event_recorder.jspf" %>

<aui:script use="aui-toggler,liferay-calendar-list,liferay-scheduler,liferay-store,json">
	Liferay.CalendarUtil.PORTLET_NAMESPACE = '<portlet:namespace />';
	Liferay.CalendarUtil.USER_TIME_ZONE = '<%= HtmlUtil.escapeJS(userTimeZone.getID()) %>';

	var showMoreStrings = {
		close: '<liferay-ui:message key="close" />',
		more: '<%= StringUtil.toLowerCase(LanguageUtil.get(request, "more")) %>',
		show: '<liferay-ui:message key="show" />'
	};

	<c:if test="<%= !hideDayView %>">
		window.<portlet:namespace />dayView = new Liferay.SchedulerDayView(
			{
				headerViewConfig: {
					eventsOverlayConstrain: '#p_p_id<portlet:namespace />',
					strings: showMoreStrings
				},
				height: 700,
				isoTime: <%= useIsoTimeFormat %>,
				readOnly: <%= readOnly %>,
				strings: {
					allDay: '<liferay-ui:message key="all-day" />'
				}
			}
		);
	</c:if>

	<c:if test="<%= !hideWeekView %>">
		window.<portlet:namespace />weekView = new Liferay.SchedulerWeekView(
			{
				headerViewConfig: {
					displayDaysInterval: A.DataType.DateMath.WEEK_LENGTH,
					eventsOverlayConstrain: '#p_p_id<portlet:namespace />',
					strings: showMoreStrings
				},
				height: 700,
				isoTime: <%= useIsoTimeFormat %>,
				readOnly: <%= readOnly %>,
				strings: {
					allDay: '<liferay-ui:message key="all-day" />'
				}
			}
		);
	</c:if>

	<c:if test="<%= !hideMonthView %>">
		window.<portlet:namespace />monthView = new Liferay.SchedulerMonthView(
			{
				eventsOverlayConstrain: '#p_p_id<portlet:namespace />',
				height: 'auto',
				isoTime: <%= useIsoTimeFormat %>,
				readOnly: <%= readOnly %>,
				strings: showMoreStrings
			}
		);
	</c:if>

	<c:if test="<%= !hideAgendaView %>">
		window.<portlet:namespace />agendaView = new Liferay.SchedulerAgendaView(
			{
				height: 700,
				isoTime: <%= useIsoTimeFormat %>,
				readOnly: <%= readOnly %>,
				strings: {
					noEvents: '<liferay-ui:message key="no-events" />'
				}
			}
		);
	</c:if>

	<c:if test="<%= !readOnly && (defaultCalendar != null) %>">
		var width = Math.min(Liferay.Util.getWindowWidth(), 550);

		window.<portlet:namespace />eventRecorder = new Liferay.SchedulerEventRecorder(
			{
				bodyTemplate: new A.Template(A.one('#<portlet:namespace />eventRecorderBodyTpl').html()),
				calendarId: <%= defaultCalendar.getCalendarId() %>,
				color: '<%= ColorUtil.toHexString(defaultCalendar.getColor()) %>',
				duration: <%= defaultDuration %>,
				editCalendarBookingURL: '<%= HtmlUtil.escapeJS(editCalendarBookingURL) %>',
				headerTemplate: new A.Template(A.one('#<portlet:namespace />eventRecorderHeaderTpl').html()),
				permissionsCalendarBookingURL: '<%= HtmlUtil.escapeJS(permissionsCalendarBookingURL) %>',
				popover: {
					width: width
				},
				portletNamespace: '<portlet:namespace />',
				strings: {
					'description-hint': '<liferay-ui:message key="description-hint" />'
				},
				viewCalendarBookingURL: '<%= HtmlUtil.escapeJS(viewCalendarBookingURL) %>'
			}
		);
	</c:if>

	var views = [];

	<c:if test="<%= !hideDayView %>">
		views.push(window.<portlet:namespace />dayView);
	</c:if>

	<c:if test="<%= !hideWeekView %>">
		views.push(window.<portlet:namespace />weekView);
	</c:if>

	<c:if test="<%= !hideMonthView %>">
		views.push(window.<portlet:namespace />monthView);
	</c:if>

	<c:if test="<%= !hideAgendaView %>">
		views.push(window.<portlet:namespace />agendaView);
	</c:if>

	window.<portlet:namespace />scheduler = new Liferay.Scheduler(
		{
			activeView: window['<portlet:namespace /><%= HtmlUtil.escapeJS(activeView) %>View'],
			boundingBox: '#<portlet:namespace />scheduler',

			<%
			java.util.Calendar dateJCalendar = CalendarFactoryUtil.getCalendar(userTimeZone);

			dateJCalendar.setTimeInMillis(date);

			int dateYear = dateJCalendar.get(java.util.Calendar.YEAR);
			int dateMonth = dateJCalendar.get(java.util.Calendar.MONTH);
			int dateDay = dateJCalendar.get(java.util.Calendar.DAY_OF_MONTH);
			%>

			date: new Date(<%= dateYear %>, <%= dateMonth %>, <%= dateDay %>),

			<c:if test="<%= !themeDisplay.isSignedIn() %>">
				disabled: true,
			</c:if>

			eventRecorder: window.<portlet:namespace />eventRecorder,
			filterCalendarBookings: window['<%= HtmlUtil.escapeJS(filterCalendarBookings) %>'],
			firstDayOfWeek: <%= weekStartsOn %>,
			items: A.Object.values(Liferay.CalendarUtil.availableCalendars),
			portletNamespace: '<portlet:namespace />',
			preventPersistence: <%= preventPersistence %>,
			render: true,
			showAddEventBtn: <%= showAddEventBtn %>,
			strings: {
				agenda: '<liferay-ui:message key="agenda" />',
				day: '<liferay-ui:message key="day" />',
				month: '<liferay-ui:message key="month" />',
				today: '<liferay-ui:message key="today" />',
				week: '<liferay-ui:message key="week" />',
				year: '<liferay-ui:message key="year" />'
			},

			<%
			java.util.Calendar todayJCalendar = CalendarFactoryUtil.getCalendar(userTimeZone);

			int todayYear = todayJCalendar.get(java.util.Calendar.YEAR);
			int todayMonth = todayJCalendar.get(java.util.Calendar.MONTH);
			int todayDay = todayJCalendar.get(java.util.Calendar.DAY_OF_MONTH);
			%>

			todayDate: new Date(<%= todayYear %>, <%= todayMonth %>, <%= todayDay %>),
			views: views
		}
	);
</aui:script>