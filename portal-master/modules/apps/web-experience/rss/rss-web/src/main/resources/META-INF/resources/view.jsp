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
List<RSSFeed> rssFeeds = rssDisplayContext.getRSSFeeds();

Map<String, Object> contextObjects = new HashMap<String, Object>();

contextObjects.put("rssDisplayContext", rssDisplayContext);
%>

<c:choose>
	<c:when test="<%= rssFeeds.isEmpty() %>">
		<div class="alert alert-info portlet-configuration">
			<liferay-ui:message key="please-configure-this-portlet-and-select-at-least-one-valid-rss-feed" />
		</div>
	</c:when>
	<c:otherwise>
		<liferay-ddm:template-renderer className="<%= RSSFeed.class.getName() %>" contextObjects="<%= contextObjects %>" displayStyle="<%= rssPortletInstanceConfiguration.displayStyle() %>" displayStyleGroupId="<%= rssDisplayContext.getDisplayStyleGroupId() %>" entries="<%= rssFeeds %>">

			<%
			for (int i = 0; i < rssFeeds.size(); i++) {
				RSSFeed rssFeed = rssFeeds.get(i);

				boolean last = false;

				if (i == (rssFeeds.size() - 1)) {
					last = true;
				}

				SyndFeed syndFeed = rssFeed.getSyndFeed();
			%>

				<%@ include file="/feed.jspf" %>

			<%
			}
			%>

		</liferay-ddm:template-renderer>
	</c:otherwise>
</c:choose>