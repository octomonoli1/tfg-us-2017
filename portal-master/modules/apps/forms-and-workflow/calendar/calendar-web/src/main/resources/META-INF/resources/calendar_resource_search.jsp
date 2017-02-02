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
CalendarResourceDisplayTerms displayTerms = new CalendarResourceDisplayTerms(renderRequest);
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_calendar_resource_search"
	markupView="lexicon"
>
	<aui:fieldset>
		<aui:input name="<%= CalendarResourceDisplayTerms.CODE %>" value="<%= displayTerms.getCode() %>" />

		<aui:input name="<%= CalendarResourceDisplayTerms.NAME %>" value="<%= displayTerms.getName() %>" />

		<aui:input name="<%= CalendarResourceDisplayTerms.DESCRIPTION %>" value="<%= displayTerms.getDescription() %>" />

		<aui:select name="<%= CalendarResourceDisplayTerms.ACTIVE %>">
			<aui:option label="yes" selected="<%= displayTerms.isActive() %>" value="<%= true %>" />
			<aui:option label="no" selected="<%= !displayTerms.isActive() %>" value="<%= false %>" />
		</aui:select>

		<aui:select name="<%= CalendarResourceDisplayTerms.SCOPE %>">
			<aui:option label="current" selected="<%= (displayTerms.getScope() == themeDisplay.getScopeGroupId()) %>" value="<%= themeDisplay.getScopeGroupId() %>" />
			<aui:option label="global" selected="<%= (displayTerms.getScope() == themeDisplay.getCompanyGroupId()) %>" value="<%= themeDisplay.getCompanyGroupId() %>" />
		</aui:select>
	</aui:fieldset>
</liferay-ui:search-toggle>