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

<%@ page import="com.liferay.portal.events.ServicePreAction" %>
<%@ page import="com.liferay.portal.kernel.model.Layout" %>
<%@ page import="com.liferay.portal.kernel.model.LayoutConstants" %>
<%@ page import="com.liferay.portal.kernel.model.LayoutSet" %>
<%@ page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.servlet.HttpHeaders" %>
<%@ page import="com.liferay.portal.kernel.theme.ThemeDisplay" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.InstancePool" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>

<%

// According to http://www.webmasterworld.com/forum91/3087.htm a semicolon in
// the URL for a meta-refresh tag does not work in IE 6.

// To work around this issue, we use a URL without a session id for meta-refresh
// and rely on the load event on the body element to properly rewrite the URL.

String redirect = null;

LayoutSet layoutSet = (LayoutSet)request.getAttribute(WebKeys.VIRTUAL_HOST_LAYOUT_SET);

if (layoutSet != null) {
	long defaultPlid = LayoutLocalServiceUtil.getDefaultPlid(layoutSet.getGroupId(), layoutSet.isPrivateLayout());

	if (defaultPlid != LayoutConstants.DEFAULT_PLID) {
		Layout layout = LayoutLocalServiceUtil.getLayout(defaultPlid);

		ServicePreAction servicePreAction = (ServicePreAction)InstancePool.get(ServicePreAction.class.getName());

		ThemeDisplay themeDisplay = servicePreAction.initThemeDisplay(request, response);

		redirect = PortalUtil.getLayoutURL(layout, themeDisplay);
	}
	else {
		redirect = PortalUtil.getPathMain();
	}
}
else {
	redirect = PortalUtil.getHomeURL(request);
}

if (!request.isRequestedSessionIdFromCookie()) {
	redirect = PortalUtil.getURLWithSessionId(redirect, session.getId());
}

response.setHeader(HttpHeaders.LOCATION, redirect);

response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
%>

<html>
	<head>
		<title></title>
		<meta content="1; url=<%= HtmlUtil.escapeAttribute(redirect) %>" http-equiv="refresh" />
	</head>

	<body onload="javascript:location.replace('<%= HtmlUtil.escapeJS(redirect) %>')">

	</body>

</html>