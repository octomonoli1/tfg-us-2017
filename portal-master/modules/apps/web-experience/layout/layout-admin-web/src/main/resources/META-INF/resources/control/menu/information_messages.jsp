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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.layout.admin.web.internal.constants.LayoutAdminPortletKeys" %>
<%@ page import="com.liferay.layout.admin.web.internal.product.navigation.control.menu.InformationMessagesProductNavigationControlMenuEntry" %>
<%@ page import="com.liferay.portal.kernel.model.Group" %>
<%@ page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.sites.kernel.util.SitesUtil" %>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<%@ page import="javax.portlet.ActionRequest" %>
<%@ page import="javax.portlet.PortletRequest" %>
<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<%
String portletNamespace = PortalUtil.getPortletNamespace(LayoutAdminPortletKeys.LAYOUT_ADMIN);

Map<String, Object> data = new HashMap<>();

data.put("qa-id", "info");
%>

<liferay-ui:icon
	data="<%= data %>"
	icon="information-live"
	id='<%= portletNamespace + "infoButton" %>'
	label="<%= false %>"
	linkCssClass="control-menu-icon"
	markupView="lexicon"
	message="additional-information"
	url="javascript:;"
/>

<div class="hide">
	<div id="<%= portletNamespace %>infoContainer">

		<%
		boolean modifiedLayout = GetterUtil.getBoolean(request.getAttribute(InformationMessagesProductNavigationControlMenuEntry.INFORMATION_MESSAGES_MODIFIED_LAYOUT));
		%>

		<c:if test="<%= modifiedLayout %>">
			<div class="modified-layout">
				<aui:icon image="information-live" markupView="lexicon" />

				<span class="message-info">
					<liferay-ui:message key="this-page-has-been-changed-since-the-last-update-from-the-site-template-excerpt" />

					<liferay-ui:icon-help message="this-page-has-been-changed-since-the-last-update-from-the-site-template" />
				</span>

				<%
				PortletURL resetPrototypeURL = PortletURLFactoryUtil.create(request, LayoutAdminPortletKeys.LAYOUT_ADMIN, PortletRequest.ACTION_PHASE);

				resetPrototypeURL.setParameter(ActionRequest.ACTION_NAME, "resetPrototype");
				resetPrototypeURL.setParameter("redirect", PortalUtil.getLayoutURL(themeDisplay));
				resetPrototypeURL.setParameter("groupId", String.valueOf(themeDisplay.getSiteGroupId()));

				String taglibURL = "submitForm(document.hrefFm, '" + HtmlUtil.escapeJS(resetPrototypeURL.toString()) + "');";
				%>

				<span class="button-info">
					<aui:button cssClass="btn-link" name="submit" onClick="<%= taglibURL %>" type="submit" value="reset-changes" />
				</span>
			</div>
		</c:if>

		<%
		boolean linkedLayout = GetterUtil.getBoolean(request.getAttribute(InformationMessagesProductNavigationControlMenuEntry.INFORMATION_MESSAGES_LINKED_LAYOUT));
		%>

		<c:if test="<%= linkedLayout %>">
			<div class="linked-layout">
				<aui:icon image="information-live" markupView="lexicon" />

				<span class="message-info">

					<%
					Group group = themeDisplay.getScopeGroup();
					%>

					<c:choose>
						<c:when test="<%= layout.isLayoutPrototypeLinkActive() && !group.hasStagingGroup() %>">
							<liferay-ui:message key="this-page-is-linked-to-a-page-template" />
						</c:when>
						<c:when test="<%= SitesUtil.isUserGroupLayout(layout) %>">
							<liferay-ui:message key="this-page-belongs-to-a-user-group" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="this-page-is-linked-to-a-site-template-which-does-not-allow-modifications-to-it" />
						</c:otherwise>
					</c:choose>
				</span>
			</div>
		</c:if>
	</div>
</div>

<aui:script position="auto" use="aui-popover,event-outside">
	var trigger = A.one('#<%= portletNamespace %>infoButton');

	var DOC = A.getDoc();

	new A.Popover(
		{
			align: {
					node: trigger,
					points: [A.WidgetPositionAlign.TC, A.WidgetPositionAlign.BC]
				},
			bodyContent: A.one('#<%= portletNamespace %>infoContainer'),
			constrain: true,
			hideOn: [
				{
					eventName: 'key',
					keyCode: 'esc',
					node: DOC
				},
				{
					eventName: 'clickoutside',
					node: DOC
				}
			],
			position: 'bottom',
			trigger: trigger,
			visible: false,
			width: 300,
			zIndex: Liferay.zIndex.OVERLAY
		}
	).render();
</aui:script>