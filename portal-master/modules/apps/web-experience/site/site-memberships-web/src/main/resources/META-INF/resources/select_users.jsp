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
Group group = siteMembershipsDisplayContext.getGroup();

String displayStyle = portalPreferences.getValue(SiteMembershipsPortletKeys.SITE_MEMBERSHIPS_ADMIN, "display-style", "icon");
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectUsers");
String orderByCol = ParamUtil.getString(request, "orderByCol", "first-name");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

PortletURL viewUsersURL = renderResponse.createRenderURL();

viewUsersURL.setParameter("mvcPath", "/select_users.jsp");
viewUsersURL.setParameter("groupId", String.valueOf(group.getGroupId()));
viewUsersURL.setParameter("eventName", eventName);

UserSiteMembershipChecker rowChecker = new UserSiteMembershipChecker(renderResponse, group);

UserSearch userSearch = new UserSearch(renderRequest, PortletURLUtil.clone(viewUsersURL, renderResponse));

UserSearchTerms searchTerms = (UserSearchTerms)userSearch.getSearchTerms();

LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

if (group.isLimitedToParentSiteMembers()) {
	userParams.put("inherit", Boolean.TRUE);
	userParams.put("usersGroups", Long.valueOf(group.getParentGroupId()));
}

int usersCount = UserLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getStatus(), userParams);

userSearch.setTotal(usersCount);

List<User> users = UserLocalServiceUtil.search(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getStatus(), userParams, userSearch.getStart(), userSearch.getEnd(), userSearch.getOrderByComparator());

userSearch.setResults(users);
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="users" selected="<%= true %>" />
	</aui:nav>

	<c:if test="<%= (usersCount > 0) || searchTerms.isSearch() %>">
		<aui:nav-bar-search>
			<aui:form action="<%= viewUsersURL.toString() %>" name="searchFm">
				<liferay-ui:input-search autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" markupView="lexicon" />
			</aui:form>
		</aui:nav-bar-search>
	</c:if>
</aui:nav-bar>

<liferay-frontend:management-bar
	disabled="<%= usersCount <= 0 %>"
	includeCheckBox="<%= true %>"
	searchContainerId="users"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-portlet:actionURL name="changeDisplayStyle" varImpl="changeDisplayStyleURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</liferay-portlet:actionURL>

		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'
			portletURL="<%= changeDisplayStyleURL %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= PortletURLUtil.clone(viewUsersURL, renderResponse) %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= orderByCol %>"
			orderByType="<%= orderByType %>"
			orderColumns='<%= new String[] {"first-name", "screen-name"} %>'
			portletURL="<%= PortletURLUtil.clone(viewUsersURL, renderResponse) %>"
		/>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<aui:form cssClass="container-fluid-1280 portlet-site-memberships-select-users" name="fm">
	<liferay-ui:membership-policy-error />

	<liferay-ui:search-container
		id="users"
		rowChecker="<%= rowChecker %>"
		searchContainer="<%= userSearch %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.User"
			escapedModel="<%= true %>"
			keyProperty="userId"
			modelVar="user2"
			rowIdProperty="screenName"
		>

			<%
			boolean selectUsers = true;
			%>

			<%@ include file="/user_columns.jspf" %>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator displayStyle="<%= displayStyle %>" markupView="lexicon" />
	</liferay-ui:search-container>
</aui:form>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />users');

	searchContainer.on(
		'rowToggled',
		function(event) {
			Liferay.Util.getOpener().Liferay.fire(
				'<%= HtmlUtil.escapeJS(eventName) %>',
				{
					data: event.elements.allSelectedElements.getDOMNodes()
				}
			);
		}
	);
</aui:script>