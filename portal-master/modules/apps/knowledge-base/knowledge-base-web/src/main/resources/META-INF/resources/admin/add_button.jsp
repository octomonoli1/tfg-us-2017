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
long kbFolderClassNameId = PortalUtil.getClassNameId(KBFolderConstants.getClassName());

long parentResourceClassNameId = ParamUtil.getLong(request, "parentResourceClassNameId", kbFolderClassNameId);
long parentResourcePrimKey = ParamUtil.getLong(request, "parentResourcePrimKey", KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);

boolean hasAddKBArticlePermission = false;
boolean hasAddKBFolderPermission = false;

if (parentResourceClassNameId == kbFolderClassNameId) {
	hasAddKBArticlePermission = KBFolderPermission.contains(permissionChecker, scopeGroupId, parentResourcePrimKey, KBActionKeys.ADD_KB_ARTICLE);
	hasAddKBFolderPermission = KBFolderPermission.contains(permissionChecker, scopeGroupId, parentResourcePrimKey, KBActionKeys.ADD_KB_FOLDER);
}
else {
	hasAddKBArticlePermission = AdminPermission.contains(permissionChecker, scopeGroupId, KBActionKeys.ADD_KB_ARTICLE);
}
%>

<liferay-frontend:add-menu>
	<c:if test="<%= hasAddKBArticlePermission %>">

		<%
		OrderByComparator<KBTemplate> obc = OrderByComparatorFactoryUtil.create("KBTemplate", "title", false);

		List<KBTemplate> kbTemplates = KBTemplateServiceUtil.getGroupKBTemplates(scopeGroupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);
		%>

		<liferay-portlet:renderURL var="addBasicKBArticleURL">
			<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(parentResourceClassNameId) %>" />
			<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
		</liferay-portlet:renderURL>

		<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(resourceBundle, "basic-article") %>' url="<%= addBasicKBArticleURL.toString() %>" />

		<c:if test="<%= !kbTemplates.isEmpty() %>">

			<%
			for (KBTemplate kbTemplate : kbTemplates) {
			%>

				<liferay-portlet:renderURL var="addKBArticleURL">
					<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(parentResourceClassNameId) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
					<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" />
				</liferay-portlet:renderURL>

				<liferay-frontend:add-menu-item title="<%= LanguageUtil.get(resourceBundle, HtmlUtil.escape(kbTemplate.getTitle())) %>" url="<%= addKBArticleURL.toString() %>" />

			<%
			}
			%>

		</c:if>
	</c:if>

	<c:if test="<%= hasAddKBFolderPermission %>">
		<portlet:renderURL var="addFolderURL">
			<portlet:param name="mvcPath" value="/admin/common/edit_folder.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(KBFolderConstants.getClassName())) %>" />
			<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
		</portlet:renderURL>

		<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(resourceBundle, "folder") %>' url="<%= addFolderURL.toString() %>" />
	</c:if>

	<c:if test="<%= (parentResourceClassNameId == kbFolderClassNameId) && AdminPermission.contains(permissionChecker, scopeGroupId, KBActionKeys.ADD_KB_ARTICLE) %>">
		<portlet:renderURL var="importURL">
			<portlet:param name="mvcPath" value="/admin/import.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="parentKBFolderId" value="<%= String.valueOf(parentResourcePrimKey) %>" />
		</portlet:renderURL>

		<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(resourceBundle, "import") %>' url="<%= importURL.toString() %>" />
	</c:if>
</liferay-frontend:add-menu>