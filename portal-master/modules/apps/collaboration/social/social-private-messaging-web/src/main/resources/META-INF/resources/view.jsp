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
Group group = themeDisplay.getScopeGroup();
LayoutSet layoutSet = themeDisplay.getLayoutSet();
%>

<c:choose>
	<c:when test="<%= group.isUser() && layoutSet.isPrivateLayout() %>">

		<%
		long mbThreadId = ParamUtil.getLong(request, "mbThreadId");
		%>

		<div class="private-messaging-container" id="<portlet:namespace />privateMessagingContainer">
			<c:choose>
				<c:when test="<%= !themeDisplay.isSignedIn() %>">
					<liferay-ui:message key="please-sign-in-to-use-the-private-messaging-portlet" />
				</c:when>
				<c:when test="<%= (mbThreadId != 0) && PrivateMessagingUtil.isUserPartOfThread(user.getUserId(), mbThreadId) %>">
					<div class="thread">
						<%@ include file="/view_thread.jspf" %>
					</div>
				</c:when>
				<c:otherwise>
					<div class="messages">
						<%@ include file="/view_messages.jspf" %>
					</div>
				</c:otherwise>
			</c:choose>
		</div>

		<aui:script use="liferay-plugin-privatemessaging">
			new Liferay.PrivateMessaging(
				{
					baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), PortletRequest.ACTION_PHASE) %>',
					baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), PortletRequest.RENDER_PHASE) %>',
					namespace: '<portlet:namespace />',
					portletId: '<%= portletDisplay.getId() %>'
				}
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<div class="alert alert-danger">
			<liferay-ui:message key="this-application-only-functions-when-placed-on-a-user's-private-page" />
		</div>
	</c:otherwise>
</c:choose>