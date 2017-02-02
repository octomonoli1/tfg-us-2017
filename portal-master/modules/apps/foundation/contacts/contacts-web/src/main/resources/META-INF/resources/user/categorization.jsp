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
User selUser = (User)request.getAttribute("user.selUser");
%>

<aui:model-context bean="<%= selUser %>" model="<%= User.class %>" />

<liferay-ui:asset-categories-error />

<liferay-ui:asset-tags-error />

<h3><liferay-ui:message key="categorization" /></h3>

<aui:fieldset>
	<aui:input name="categories" type="assetCategories" />

	<aui:input name="tags" type="assetTags" />
</aui:fieldset>

<aui:script>
	function <portlet:namespace />getSuggestionsContent() {

		<%
		StringBundler sb = new StringBundler();

		if (selUser.getComments() != null) {
			sb.append(selUser.getComments());
		}

		if (selUser.getJobTitle() != null) {
			sb.append(StringPool.SPACE);
			sb.append(selUser.getJobTitle());
		}
		%>

		return '<%= HtmlUtil.escape(HtmlUtil.replaceNewLine(sb.toString())) %>'
	}
</aui:script>