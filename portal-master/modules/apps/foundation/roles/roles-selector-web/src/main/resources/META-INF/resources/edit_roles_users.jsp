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
String tabs1 = (String)request.getAttribute("edit_roles.jsp-tabs1");

int cur = (Integer)request.getAttribute("edit_roles.jsp-cur");

Group group = (Group)request.getAttribute("edit_roles.jsp-group");
String groupDescriptiveName = (String)request.getAttribute("edit_roles.jsp-groupDescriptiveName");
Role role = (Role)request.getAttribute("edit_roles.jsp-role");
long roleId = (Long)request.getAttribute("edit_roles.jsp-roleId");
Organization organization = (Organization)request.getAttribute("edit_roles.jsp-organization");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_roles.jsp-portletURL");
%>

<aui:input name="addUserIds" type="hidden" />
<aui:input name="removeUserIds" type="hidden" />

<div>
	<liferay-ui:message arguments='<%= new String[] {"2", "2"} %>' key="step-x-of-x" translateArguments="<%= false %>" />

	<liferay-ui:message arguments='<%= new String[] {HtmlUtil.escape(role.getTitle(locale)), HtmlUtil.escape(groupDescriptiveName), LanguageUtil.get(request, (group.isOrganization() ? "organization" : "site"))} %>' key="current-signifies-current-users-associated-with-the-x-role.-available-signifies-all-users-associated-with-the-x-x" translateArguments="<%= false %>" />
</div>

<br />

<liferay-ui:tabs
	names="current,available"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<liferay-ui:membership-policy-error />

<liferay-ui:search-container
	rowChecker="<%= (role.getType() == RoleConstants.TYPE_SITE) ? new UserGroupRoleUserChecker(renderResponse, group, role) : new OrganizationRoleUserChecker(renderResponse, organization, role) %>"
	searchContainer="<%= new UserSearch(renderRequest, portletURL) %>"
	var="userSearchContainer"
>
	<liferay-ui:user-search-form />

	<%
	UserSearchTerms searchTerms = (UserSearchTerms)userSearchContainer.getSearchTerms();

	LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

	userParams.put("inherit", Boolean.TRUE);
	userParams.put("usersGroups", Long.valueOf(group.getGroupId()));

	if (tabs1.equals("current")) {
		userParams.put("userGroupRole", new Long[] {Long.valueOf(group.getGroupId()), Long.valueOf(roleId)});
	}
	%>

	<liferay-ui:search-container-results>

		<%
		if (searchTerms.isAdvancedSearch()) {
			total = UserLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getFirstName(), searchTerms.getMiddleName(), searchTerms.getLastName(), searchTerms.getScreenName(), searchTerms.getEmailAddress(), searchTerms.getStatus(), userParams, searchTerms.isAndOperator());

			userSearchContainer.setTotal(total);

			results = UserLocalServiceUtil.search(company.getCompanyId(), searchTerms.getFirstName(), searchTerms.getMiddleName(), searchTerms.getLastName(), searchTerms.getScreenName(), searchTerms.getEmailAddress(), searchTerms.getStatus(), userParams, searchTerms.isAndOperator(), userSearchContainer.getStart(), userSearchContainer.getEnd(), userSearchContainer.getOrderByComparator());
		}
		else {
			total = UserLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getStatus(), userParams);

			userSearchContainer.setTotal(total);

			results = UserLocalServiceUtil.search(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getStatus(), userParams, userSearchContainer.getStart(), userSearchContainer.getEnd(), userSearchContainer.getOrderByComparator());
		}

		userSearchContainer.setResults(results);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.User"
		escapedModel="<%= true %>"
		keyProperty="userId"
		modelVar="user2"
		rowIdProperty="screenName"
	>
		<liferay-ui:search-container-column-text
			name="name"
			property="fullName"
		/>

		<liferay-ui:search-container-column-text
			name="screen-name"
			property="screenName"
		/>
	</liferay-ui:search-container-row>

	<div class="separator"><!-- --></div>

	<%
	portletURL.setParameter("cur", String.valueOf(cur));

	String taglibOnClick = renderResponse.getNamespace() + "updateUserGroupRoleUsers('" + portletURL.toString() + "');";
	%>

	<aui:button onClick="<%= taglibOnClick %>" primary="<%= true %>" value="update-associations" />

	<liferay-ui:search-iterator />
</liferay-ui:search-container>