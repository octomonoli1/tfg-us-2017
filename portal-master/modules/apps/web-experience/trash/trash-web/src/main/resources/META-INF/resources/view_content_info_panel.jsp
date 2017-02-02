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
long classPK = trashDisplayContext.getClassPK();

TrashRenderer trashRenderer = trashDisplayContext.getTrashRenderer();

TrashHandler trashHandler = trashDisplayContext.getTrashHandler();
%>

<c:if test="<%= trashRenderer != null %>">
	<div class="sidebar-header">
		<ul class="sidebar-header-actions">
			<li>
				<liferay-util:include page="/container_action.jsp" servletContext="<%= application %>" />
			</li>
		</ul>

		<h4><%= HtmlUtil.escape(trashRenderer.getTitle(locale)) %></h4>
	</div>

	<aui:nav-bar>
		<aui:nav cssClass="navbar-nav">
			<aui:nav-item label="details" selected="<%= true %>" />
		</aui:nav>
	</aui:nav-bar>

	<div class="sidebar-body">
		<h5><liferay-ui:message key="num-of-items" /></h5>

		<p>
			<%= trashHandler.getTrashModelsCount(classPK) %>
		</p>
	</div>
</c:if>