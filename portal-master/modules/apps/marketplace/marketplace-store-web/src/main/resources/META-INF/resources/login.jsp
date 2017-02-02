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

<div class="authorize">
	<img src="<%= PortalUtil.getPathContext(request) %>/images/logo.svg" />

	<p>
		<liferay-ui:message key="liferay-marketplace-is-an-integral-part-of-the-liferay-platform-experience-for-all-users" />
	</p>

	<liferay-portlet:renderURL var="callbackURL" />

	<liferay-portlet:actionURL name="authorize" var="authorizeURL">
		<portlet:param name="callbackURL" value="<%= callbackURL %>" />
	</liferay-portlet:actionURL>

	<aui:button onClick="<%= authorizeURL %>" value="sign-in" />
</div>