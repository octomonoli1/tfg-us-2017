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
String redirect = ParamUtil.getString(request, "redirect");

DDLRecordVersion recordVersion = ddlFormAdminDisplayContext.getRecordVersion();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(LanguageUtil.get(request, "view-form"));
%>

<div class="container-fluid-1280">
	<c:if test="<%= recordVersion != null %>">
		<aui:model-context bean="<%= recordVersion %>" model="<%= DDLRecordVersion.class %>" />

		<div class="panel text-center">
			<aui:workflow-status markupView="lexicon" model="<%= DDLRecord.class %>" showHelpMessage="<%= false %>" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= recordVersion.getStatus() %>" version="<%= recordVersion.getVersion() %>" />
		</div>
	</c:if>

	<%= ddlFormAdminDisplayContext.getDDMFormHTML(renderRequest) %>
</div>