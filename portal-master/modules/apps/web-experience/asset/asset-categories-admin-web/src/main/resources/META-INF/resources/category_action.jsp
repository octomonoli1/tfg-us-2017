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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

AssetCategory category = (AssetCategory)row.getObject();
%>

<liferay-ui:icon-menu direction="left-side" icon="<%= StringPool.BLANK %>" markupView="lexicon" message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">
	<c:if test="<%= AssetCategoryPermission.contains(permissionChecker, category, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editCategoryURL">
			<portlet:param name="mvcPath" value="/edit_category.jsp" />
			<portlet:param name="categoryId" value="<%= String.valueOf(category.getCategoryId()) %>" />
			<portlet:param name="vocabularyId" value="<%= String.valueOf(category.getVocabularyId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editCategoryURL %>"
		/>
	</c:if>

	<c:if test="<%= AssetCategoryPermission.contains(permissionChecker, category, ActionKeys.ADD_CATEGORY) %>">
		<portlet:renderURL var="addSubcategoryCategoryURL">
			<portlet:param name="mvcPath" value="/edit_category.jsp" />
			<portlet:param name="vocabularyId" value="<%= String.valueOf(category.getVocabularyId()) %>" />
			<portlet:param name="parentCategoryId" value="<%= String.valueOf(category.getCategoryId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="add-subcategory"
			url="<%= addSubcategoryCategoryURL %>"
		/>
	</c:if>

	<c:if test="<%= AssetCategoryPermission.contains(permissionChecker, category, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="moveCategoryURL">
			<portlet:param name="mvcPath" value="/move_category.jsp" />
			<portlet:param name="categoryId" value="<%= String.valueOf(category.getCategoryId()) %>" />
			<portlet:param name="vocabularyId" value="<%= String.valueOf(category.getVocabularyId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="move"
			url="<%= moveCategoryURL %>"
		/>
	</c:if>

	<c:if test="<%= AssetCategoryPermission.contains(permissionChecker, category, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= AssetCategory.class.getName() %>"
			modelResourceDescription="<%= category.getTitle(locale) %>"
			resourcePrimKey="<%= String.valueOf(category.getCategoryId()) %>"
			var="permissionsCategoryURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%= permissionsCategoryURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= AssetCategoryPermission.contains(permissionChecker, category, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteCategory" var="deleteCategoryURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="categoryId" value="<%= String.valueOf(category.getCategoryId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteCategoryURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>