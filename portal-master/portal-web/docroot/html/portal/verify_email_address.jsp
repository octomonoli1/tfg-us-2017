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

<%@ include file="/html/portal/init.jsp" %>

<%
String referer = ParamUtil.getString(request, WebKeys.REFERER, themeDisplay.getPathMain());

if (referer.equals(themeDisplay.getPathMain() + "/portal/update_email_address")) {
	referer = themeDisplay.getPathMain() + "?doAsUserId=" + themeDisplay.getDoAsUserId();
}

PasswordPolicy passwordPolicy = user.getPasswordPolicy();

String ticketKey = ParamUtil.getString(request, "ticketKey");
%>

<aui:form action='<%= themeDisplay.getPathMain() + "/portal/verify_email_address" %>' method="post" name="fm">
	<aui:input name="p_l_id" type="hidden" value="<%= layout.getPlid() %>" />
	<aui:input name="p_auth" type="hidden" value="<%= AuthTokenUtil.getToken(request) %>" />
	<aui:input name="doAsUserId" type="hidden" value="<%= themeDisplay.getDoAsUserId() %>" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="<%= WebKeys.REFERER %>" type="hidden" value="<%= referer %>" />

	<div class="alert alert-info">
		<liferay-ui:message key="please-enter-your-verification-code" />
	</div>

	<c:if test="<%= !SessionErrors.isEmpty(request) %>">
		<div class="alert alert-danger">
			<liferay-ui:message key="please-enter-a-valid-verification-code" />
		</div>
	</c:if>

	<aui:input autoFocus="<%= true %>" class="lfr-input-text-container" label="email-verification-code" name="ticketKey" size="36" type="text" value="<%= ticketKey %>" />

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" value="verify" />

		<c:if test="<%= themeDisplay.isSignedIn() && !user.isEmailAddressVerified() %>">
			<aui:button cssClass="btn-lg" href='<%= themeDisplay.getPathMain() + "/portal/verify_email_address?p_l_id=" + layout.getPlid() + "&cmd=" + Constants.SEND + "&referer=" + HttpUtil.encodeURL(referer) %>' value="send-new-verification-code" />

			<aui:button cssClass="btn-lg" href='<%= themeDisplay.getPathMain() + "/portal/update_email_address?p_l_id=" + layout.getPlid() + "&referer=" + HttpUtil.encodeURL(referer) %>' value="change-email-address" />
		</c:if>
	</aui:button-row>
</aui:form>