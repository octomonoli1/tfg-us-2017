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
Collection<DynamicInclude> dynamicIncludes = (Collection)request.getAttribute(PortalSettingsWebKeys.AUTHENTICATION_DYNAMIC_INCLUDES);

String[] tabsNames = new String[] {"general"};

tabsNames = ArrayUtil.append(tabsNames, PropsValues.COMPANY_SETTINGS_FORM_AUTHENTICATION);

tabsNames = ArrayUtil.append(tabsNames, (String)request.getAttribute(PortalSettingsWebKeys.AUTHENTICATION_TABS_NAMES));
%>

<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="authentication" />

<h3><liferay-ui:message key="authentication" /></h3>

<liferay-ui:tabs
	names="<%= StringUtil.merge(tabsNames) %>"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<liferay-util:include page='<%= "/authentication/general.jsp" %>' portletId="<%= portletDisplay.getRootPortletId() %>" />
	</liferay-ui:section>

	<%
	for (String section : PropsValues.COMPANY_SETTINGS_FORM_AUTHENTICATION) {
	%>

		<liferay-ui:section>
			<liferay-util:include page='<%= "/html/portlet/portal_settings/authentication/" + _getSectionJsp(section) + ".jsp" %>' portletId="<%= PortletKeys.PORTAL %>" />
		</liferay-ui:section>

	<%
	}

	for (DynamicInclude dynamicInclude : dynamicIncludes) {
	%>

		<liferay-ui:section>

			<%
			dynamicInclude.include(request, new PipingServletResponse(response, pageContext.getOut()), null);
			%>

		</liferay-ui:section>

	<%
	}
	%>

</liferay-ui:tabs>

<%!
private String _getSectionJsp(String name) {
	return TextFormatter.format(name, TextFormatter.N);
}
%>