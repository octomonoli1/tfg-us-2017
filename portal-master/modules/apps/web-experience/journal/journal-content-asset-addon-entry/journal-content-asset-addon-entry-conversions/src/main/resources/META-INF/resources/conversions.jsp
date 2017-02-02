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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.document.library.kernel.util.DLUtil" %>
<%@ page import="com.liferay.journal.model.JournalArticleDisplay" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
JournalArticleDisplay articleDisplay = (JournalArticleDisplay)request.getAttribute(WebKeys.JOURNAL_ARTICLE_DISPLAY);

String extension = (String)request.getAttribute("extension");
String viewMode = ParamUtil.getString(request, "viewMode");
%>

<c:if test="<%= !viewMode.equals(Constants.PRINT) %>">
	<div class="export-action user-tool-asset-addon-entry">
		<portlet:resourceURL id="exportArticle" var="exportArticleURL">
			<portlet:param name="groupId" value="<%= String.valueOf(articleDisplay.getGroupId()) %>" />
			<portlet:param name="articleId" value="<%= articleDisplay.getArticleId() %>" />
			<portlet:param name="targetExtension" value="<%= extension %>" />
		</portlet:resourceURL>

		<liferay-ui:icon
			iconCssClass="<%= DLUtil.getFileIconCssClass(extension) %>"
			label="<%= true %>"
			message='<%= LanguageUtil.format(resourceBundle, "x-download-x-as-x", new Object[] {"hide-accessible", HtmlUtil.escape(articleDisplay.getTitle()), StringUtil.toUpperCase(HtmlUtil.escape(extension))}) %>'
			method="get"
			url="<%= exportArticleURL %>"
		/>
	</div>
</c:if>