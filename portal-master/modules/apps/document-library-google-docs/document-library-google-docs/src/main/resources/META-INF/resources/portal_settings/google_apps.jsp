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

<%@ include file="/portal_settings/init.jsp" %>

<h3><liferay-ui:message key="google-apps" /></h3>

<%
PortletPreferences companyPortletPreferences = PrefsPropsUtil.getPreferences(company.getCompanyId());

String googleAppsAPIKey = PrefsParamUtil.getString(companyPortletPreferences, request, "googleAppsAPIKey");
String googleClientId = PrefsParamUtil.getString(companyPortletPreferences, request, "googleClientId");
%>

<aui:fieldset>
	<aui:input label='<%= ResourceBundleUtil.getString(resourceBundle, "google-apps-api-key") %>' localizeLabel="<%= false %>" name="settings--googleAppsAPIKey--" type="text" value="<%= googleAppsAPIKey %>" />

	<aui:input label='<%= ResourceBundleUtil.getString(resourceBundle, "google-client-id") %>' localizeLabel="<%= false %>" name="settings--googleClientId--" type="text" value="<%= googleClientId %>" />
</aui:fieldset>