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
KBArticle kbArticle = (KBArticle)request.getAttribute(KBWebKeys.KNOWLEDGE_BASE_KB_ARTICLE);
%>

<liferay-util:buffer var="html">
	<liferay-ui:asset-categories-summary
		className="<%= KBArticle.class.getName() %>"
		classPK="<%= kbArticle.getClassPK() %>"
		portletURL="<%= renderResponse.createRenderURL() %>"
	/>

	<liferay-ui:asset-tags-available
		className="<%= KBArticle.class.getName() %>"
		classPK="<%= kbArticle.getClassPK() %>"
	>
		<h5><liferay-ui:message key="tags" /></h5>

		<liferay-ui:asset-tags-summary
			className="<%= KBArticle.class.getName() %>"
			classPK="<%= kbArticle.getClassPK() %>"
			portletURL="<%= renderResponse.createRenderURL() %>"
		/>
	</liferay-ui:asset-tags-available>
</liferay-util:buffer>

<c:if test="<%= Validator.isNotNull(html.trim()) %>">
	<div class="kb-article-assets">
		<%= html %>
	</div>
</c:if>