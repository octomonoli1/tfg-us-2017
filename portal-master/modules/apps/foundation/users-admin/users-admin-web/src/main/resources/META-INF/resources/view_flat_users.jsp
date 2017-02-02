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
String backURL = GetterUtil.getString(request.getAttribute("view.jsp-backURL"));
int inactiveUsersCount = GetterUtil.getInteger(request.getAttribute("view.jsp-inactiveUsersCount"));
PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
int status = GetterUtil.getInteger(request.getAttribute("view.jsp-status"));
String usersListView = GetterUtil.getString(request.getAttribute("view.jsp-usersListView"));
String viewUsersRedirect = GetterUtil.getString(request.getAttribute("view.jsp-viewUsersRedirect"));

SearchContainer searchContainer = new UserSearch(renderRequest, "cur2", currentURLObj);

UserSearchTerms searchTerms = (UserSearchTerms)searchContainer.getSearchTerms();

boolean hasAddUserPermission = PortalPermissionUtil.contains(permissionChecker, ActionKeys.ADD_USER);

if (!searchTerms.isSearch() && hasAddUserPermission) {
	searchContainer.setEmptyResultsMessageCssClass("taglib-empty-result-message-header-has-plus-btn");
}

RowChecker rowChecker = new EmptyOnClickRowChecker(renderResponse);

rowChecker.setRowIds("rowIdsUser");

searchContainer.setRowChecker(rowChecker);

if (!ParamUtil.getBoolean(renderRequest, "advancedSearch")) {
	currentURLObj.setParameter("status", String.valueOf(status));
}

String displayStyle = ParamUtil.getString(request, "displayStyle", "list");
String navigation = ParamUtil.getString(request, "navigation", "active");
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "view-all-users");

if (navigation.equals("active")) {
	status = WorkflowConstants.STATUS_APPROVED;
	searchTerms.setStatus(WorkflowConstants.STATUS_APPROVED);
}
else if (navigation.equals("inactive")) {
	status = WorkflowConstants.STATUS_INACTIVE;
	searchTerms.setStatus(WorkflowConstants.STATUS_INACTIVE);
}

portletURL.setParameter("navigation", navigation);
portletURL.setParameter("status", String.valueOf(status));

boolean showDeleteButton = (searchTerms.getStatus() != WorkflowConstants.STATUS_ANY) && (searchTerms.isActive() || (!searchTerms.isActive() && PropsValues.USERS_DELETE));
boolean showRestoreButton = (searchTerms.getStatus() != WorkflowConstants.STATUS_ANY) && !searchTerms.isActive();
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="users"
>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"active", "inactive"} %>'
			portletURL="<%= renderResponse.createRenderURL() %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= searchContainer.getOrderByCol() %>"
			orderByType="<%= searchContainer.getOrderByType() %>"
			orderColumns='<%= new String[] {"first-name", "last-name", "screen-name"} %>'
			portletURL="<%= portletURL %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= renderResponse.createRenderURL() %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-action-buttons>
		<c:if test="<%= showRestoreButton %>">

			<%
			String taglibOnClick = "javascript:" + renderResponse.getNamespace() + "deleteUsers('" + Constants.RESTORE + "');";
			%>

			<liferay-frontend:management-bar-button href="<%= taglibOnClick %>" iconCssClass="icon-undo" id="restoreUsers" label="restore" />
		</c:if>

		<c:if test="<%= showDeleteButton %>">

			<%
			String taglibOnClick = "javascript:" + renderResponse.getNamespace() + "deleteUsers('" + (searchTerms.isActive() ? Constants.DEACTIVATE : Constants.DELETE) + "');";
			%>

			<liferay-frontend:management-bar-button href="<%= taglibOnClick %>" icon="trash" id="deleteUsers" label="<%= searchTerms.isActive() ? Constants.DEACTIVATE : Constants.DELETE %>" />
		</c:if>
	</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<aui:form action="<%= portletURL.toString() %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "search();" %>'>
	<liferay-portlet:renderURLParams varImpl="portletURL" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="toolbarItem" type="hidden" value="<%= toolbarItem %>" />
	<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />

	<c:if test="<%= Validator.isNotNull(viewUsersRedirect) %>">
		<aui:input name="viewUsersRedirect" type="hidden" value="<%= viewUsersRedirect %>" />
	</c:if>

	<liferay-ui:search-container
		cssClass="users-search-container"
		id="users"
		searchContainer="<%= searchContainer %>"
		var="userSearchContainer"
	>
		<aui:input disabled="<%= true %>" name="usersRedirect" type="hidden" value="<%= currentURLObj.toString() %>" />
		<aui:input name="deleteUserIds" type="hidden" />
		<aui:input name="status" type="hidden" value="<%= status %>" />

		<%
		if ((searchTerms.getOrganizationId() > 0) && !OrganizationPermissionUtil.contains(permissionChecker, searchTerms.getOrganizationId(), ActionKeys.MANAGE_USERS)) {
			inactiveUsersCount = 0;

			status = WorkflowConstants.STATUS_APPROVED;
		}

		UserDisplayTerms displayTerms = (UserDisplayTerms)userSearchContainer.getDisplayTerms();

		if (!searchTerms.isAdvancedSearch()) {
			if (status == WorkflowConstants.STATUS_APPROVED) {
				displayTerms.setStatus(WorkflowConstants.STATUS_APPROVED);
				searchTerms.setStatus(WorkflowConstants.STATUS_APPROVED);
			}
			else {
				displayTerms.setStatus(WorkflowConstants.STATUS_INACTIVE);
				searchTerms.setStatus(WorkflowConstants.STATUS_INACTIVE);
			}
		}

		long userOrganizationId = searchTerms.getOrganizationId();
		long roleId = searchTerms.getRoleId();
		long userGroupId = searchTerms.getUserGroupId();

		Organization userOrganization = null;

		if (userOrganizationId > 0) {
			try {
				userOrganization = OrganizationLocalServiceUtil.getOrganization(userOrganizationId);

				userSearchContainer.setEmptyResultsMessage("this-organization-does-not-have-any-users");
			}
			catch (NoSuchOrganizationException nsoe) {
			}
		}

		Role role = null;

		if (roleId > 0) {
			try {
				role = RoleLocalServiceUtil.getRole(roleId);
			}
			catch (NoSuchRoleException nsre) {
			}
		}

		UserGroup userGroup = null;

		if (userGroupId > 0) {
			try {
				userGroup = UserGroupLocalServiceUtil.getUserGroup(userGroupId);
			}
			catch (NoSuchUserGroupException nsuge) {
			}
		}

		if (role != null) {
			PortalUtil.addPortletBreadcrumbEntry(request, role.getName(), null);
			PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "all-users"), currentURL);
		}

		if (userGroup != null) {
			PortalUtil.addPortletBreadcrumbEntry(request, userGroup.getName(), null);
			PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "all-users"), currentURL);
		}
		%>

		<c:if test="<%= usersListView.equals(UserConstants.LIST_VIEW_FLAT_USERS) && (role == null) && (userGroup == null) %>">

			<%
			if (userOrganization != null) {
				UsersAdminUtil.addPortletBreadcrumbEntries(userOrganization, request, renderResponse);

				PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "all-users"), currentURL);
			}
			%>

			<c:if test="<%= layout.isTypeControlPanel() %>">
				<div id="breadcrumb">
					<liferay-ui:breadcrumb showCurrentGroup="<%= false %>" showGuestGroup="<%= false %>" showLayout="<%= false %>" showPortletBreadcrumb="<%= true %>" />
				</div>
			</c:if>
		</c:if>

		<c:if test="<%= userOrganization != null %>">
			<aui:input name="<%= UserDisplayTerms.ORGANIZATION_ID %>" type="hidden" value="<%= userOrganization.getOrganizationId() %>" />

			<c:if test="<%= usersListView.equals(UserConstants.LIST_VIEW_FLAT_USERS) %>">

				<%
				portletDisplay.setShowBackIcon(true);
				portletDisplay.setURLBack(backURL);

				renderResponse.setTitle(userOrganization.getName());
				%>

			</c:if>
		</c:if>

		<c:if test="<%= role != null %>">
			<aui:input name="<%= UserDisplayTerms.ROLE_ID %>" type="hidden" value="<%= role.getRoleId() %>" />

			<%
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(backURL);

			renderResponse.setTitle(role.getTitle(locale));
			%>

		</c:if>

		<c:if test="<%= userGroup != null %>">
			<aui:input name="<%= UserDisplayTerms.USER_GROUP_ID %>" type="hidden" value="<%= userGroup.getUserGroupId() %>" />

			<%
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(backURL);

			renderResponse.setTitle(userGroup.getName());
			%>

		</c:if>

		<%
		LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

		if (userOrganizationId > 0) {
			userParams.put("usersOrgs", Long.valueOf(userOrganizationId));
		}
		else if (usersListView.equals(UserConstants.LIST_VIEW_TREE) && Validator.isNull(searchTerms.getKeywords())) {
			userParams.put("noOrganizations", Boolean.TRUE);
			userParams.put("usersOrgsCount", 0);
		}
		else {
			if (filterManageableOrganizations && !UserPermissionUtil.contains(permissionChecker, ResourceConstants.PRIMKEY_DNE, ActionKeys.VIEW)) {
				long[] organizationIds = user.getOrganizationIds();

				if (ArrayUtil.isEmpty(organizationIds)) {
					organizationIds = new long[] {0};
				}

				userParams.put("usersOrgs", ArrayUtil.toLongArray(organizationIds));
			}
		}

		if (roleId > 0) {
			userParams.put("usersRoles", Long.valueOf(roleId));
		}

		if (userGroupId > 0) {
			userParams.put("usersUserGroups", Long.valueOf(userGroupId));
		}
		%>

		<liferay-ui:user-search-container-results userParams="<%= userParams %>" />

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.User"
			escapedModel="<%= true %>"
			keyProperty="userId"
			modelVar="user2"
			rowIdProperty="screenName"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/users_admin/edit_user" />
				<portlet:param name="redirect" value="<%= userSearchContainer.getIteratorURL().toString() %>" />
				<portlet:param name="p_u_i_d" value="<%= String.valueOf(user2.getUserId()) %>" />
			</liferay-portlet:renderURL>

			<%
			if (!UserPermissionUtil.contains(permissionChecker, user2.getUserId(), ActionKeys.UPDATE)) {
				rowURL = null;
			}

			boolean organizationContextView = false;
			long organizationGroupId = 0;
			%>

			<%@ include file="/user/search_columns.jspf" %>

			<liferay-ui:search-container-column-jsp
				cssClass="entry-action-column"
				path="/user_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<%
		List<User> results = searchContainer.getResults();

		showDeleteButton = !results.isEmpty() && showDeleteButton;
		showRestoreButton = !results.isEmpty() && showRestoreButton;
		%>

		<%
		if (!showDeleteButton && !showRestoreButton) {
			userSearchContainer.setRowChecker(null);
		}
		%>

		<liferay-ui:search-iterator markupView="lexicon" />
	</liferay-ui:search-container>
</aui:form>

<c:if test="<%= hasAddUserPermission %>">
	<liferay-frontend:add-menu>
		<portlet:renderURL var="viewUsersURL">
			<portlet:param name="toolbarItem" value="<%= toolbarItem %>" />
			<portlet:param name="usersListView" value="<%= usersListView %>" />
		</portlet:renderURL>

		<portlet:renderURL var="addUserURL">
			<portlet:param name="mvcRenderCommandName" value="/users_admin/edit_user" />
			<portlet:param name="redirect" value="<%= viewUsersURL %>" />
		</portlet:renderURL>

		<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, "user") %>' url="<%= addUserURL.toString() %>" />
	</liferay-frontend:add-menu>
</c:if>

<aui:script>
	Liferay.Util.toggleSearchContainerButton('#<portlet:namespace />deactivate', '#<portlet:namespace /><%= searchContainerReference.getId(request, "userSearchContainer") %>SearchContainer', document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');
	Liferay.Util.toggleSearchContainerButton('#<portlet:namespace />restore', '#<portlet:namespace /><%= searchContainerReference.getId(request, "userSearchContainer") %>SearchContainer', document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');
</aui:script>