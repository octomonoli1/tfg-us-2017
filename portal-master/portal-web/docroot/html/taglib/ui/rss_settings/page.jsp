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

<%@ include file="/html/taglib/ui/rss_settings/init.jsp" %>

<%
int delta = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:rss-settings:delta"));
String displayStyle = (String)request.getAttribute("liferay-ui:rss-settings:displayStyle");
String[] displayStyles = (String[])request.getAttribute("liferay-ui:rss-settings:displayStyles");
boolean enabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:rss-settings:enabled"));
String feedType = (String)request.getAttribute("liferay-ui:rss-settings:feedType");
String name = (String)request.getAttribute("liferay-ui:rss-settings:name");
boolean nameEnabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:rss-settings:nameEnabled"));
%>

<div class="taglib-rss-settings">
	<aui:input label="enable-rss-subscription" name="preferences--enableRss--" type="toggle-switch" value="<%= enabled %>" />

	<div class="rss-settings-options" id="<portlet:namespace />rssOptions">
		<c:if test="<%= nameEnabled %>">
			<aui:input label="rss-feed-name" name="preferences--rssName--" type="text" value="<%= name %>" />
		</c:if>

		<aui:select label="maximum-items-to-display" name="preferences--rssDelta--" value="<%= delta %>">
			<aui:option label="1" />
			<aui:option label="2" />
			<aui:option label="3" />
			<aui:option label="4" />
			<aui:option label="5" />
			<aui:option label="10" />
			<aui:option label="15" />
			<aui:option label="20" />
			<aui:option label="25" />
			<aui:option label="30" />
			<aui:option label="40" />
			<aui:option label="50" />
			<aui:option label="60" />
			<aui:option label="70" />
			<aui:option label="80" />
			<aui:option label="90" />
			<aui:option label="100" />
		</aui:select>

		<aui:select label="display-style" name="preferences--rssDisplayStyle--">

			<%
			for (String curDisplayStyle : displayStyles) {
			%>

				<aui:option label="<%= curDisplayStyle %>" selected="<%= displayStyle.equals(curDisplayStyle) %>" />

			<%
			}
			%>

		</aui:select>

		<aui:select label="format" name="preferences--rssFeedType--">

			<%
			for (String type : RSSUtil.FEED_TYPES) {
			%>

				<aui:option label="<%= RSSUtil.getFeedTypeName(type) %>" selected="<%= feedType.equals(type) %>" value="<%= type %>" />

			<%
			}
			%>

		</aui:select>
	</div>
</div>

<aui:script>
	Liferay.Util.toggleBoxes('<portlet:namespace />enableRss','<portlet:namespace />rssOptions');
</aui:script>