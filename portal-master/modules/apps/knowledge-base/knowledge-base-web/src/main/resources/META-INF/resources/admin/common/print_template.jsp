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

<%@ include file="/admin/common/init.jsp" %>

<%
KBTemplate kbTemplate = (KBTemplate)request.getAttribute(KBWebKeys.KNOWLEDGE_BASE_KB_TEMPLATE);
%>

<div class="float-container kb-entity-header">
	<div class="kb-title">
		<%= kbTemplate.getTitle() %>
	</div>

	<div class="kb-tools">
		<liferay-ui:icon
			iconCssClass="icon-print"
			label="<%= true %>"
			message="print"
			url="javascript:print();"
		/>
	</div>
</div>

<div class="kb-entity-body">
	<%= kbTemplate.getContent() %>
</div>