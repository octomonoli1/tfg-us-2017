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

<portlet:actionURL name="installRemoteApp" var="installRemoteAppURL" />

<aui:form action="<%= installRemoteAppURL %>" method="post" name="fm">
	<aui:input name="mvcPath" type="hidden" value="/install_from_url.jsp" />

	<c:if test="<%= CompanyLocalServiceUtil.getCompaniesCount(false) > 1 %>">
		<div class="alert alert-info">
			<liferay-ui:message key="installed-apps-are-available-to-all-portal-instances.-go-to-plugins-configuration-within-each-portal-instance-to-enable-disable-each-app" />
		</div>
	</c:if>

	<liferay-ui:error key="invalidURL" message="please-enter-a-valid-url" />

	<liferay-ui:success key="pluginDownloaded" message="the-plugin-was-downloaded-successfully-and-is-now-being-installed" />

	<aui:fieldset>
		<aui:input cssClass="file-input" name="url" type="text" />

		<aui:button type="submit" value="install" />
	</aui:fieldset>
</aui:form>