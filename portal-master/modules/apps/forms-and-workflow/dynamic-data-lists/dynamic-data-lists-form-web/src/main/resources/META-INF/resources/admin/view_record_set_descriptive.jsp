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

<%@ include file="/admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

DDLRecordSet ddlRecordSet = (DDLRecordSet)row.getObject();

DateSearchEntry dateSearchEntry = new DateSearchEntry();

dateSearchEntry.setDate(ddlRecordSet.getModifiedDate());

String href = (String)request.getAttribute(WebKeys.SEARCH_ENTRY_HREF);
%>

<div class="clamp-container">
	<h4 class="truncate-text">
		<aui:a cssClass="record-set-name" href="<%= href %>">
			<%= HtmlUtil.escape(ddlRecordSet.getName(locale)) %>
		</aui:a>
	</h4>

	<h5 class="text-default">
		<div class="record-set-description truncate-text">
			<%= HtmlUtil.escape(ddlRecordSet.getDescription(locale)) %>
		</div>
	</h5>

	<h5 class="text-default">
		<span class="record-set-id">
			<liferay-ui:message key="id" />: <%= ddlRecordSet.getRecordSetId() %>
		</span>
		<span class="record-set-modified-date">
			<liferay-ui:message key="modified-date" />: <%= dateSearchEntry.getName(request) %>
		</span>
	</h5>
</div>