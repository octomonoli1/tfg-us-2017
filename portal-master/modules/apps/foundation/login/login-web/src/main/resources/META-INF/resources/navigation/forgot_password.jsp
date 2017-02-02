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
String mvcRenderCommandName = ParamUtil.getString(request, "mvcRenderCommandName");

boolean showForgotPasswordIcon = false;

if (!mvcRenderCommandName.equals("/login/forgot_password") && (company.isSendPassword() || company.isSendPasswordResetLink())) {
	showForgotPasswordIcon = true;
}
%>

<c:if test="<%= showForgotPasswordIcon %>">
	<portlet:renderURL var="forgotPasswordURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcRenderCommandName" value="/login/forgot_password" />
	</portlet:renderURL>

	<liferay-ui:icon
		iconCssClass="icon-question-sign"
		message="forgot-password"
		url="<%= forgotPasswordURL %>"
	/>
</c:if>