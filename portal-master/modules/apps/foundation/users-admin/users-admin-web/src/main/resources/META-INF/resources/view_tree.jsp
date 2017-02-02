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
Organization organization = (Organization)request.getAttribute("view.jsp-organization");
long organizationId = GetterUtil.getLong(request.getAttribute("view.jsp-organizationId"));
long organizationGroupId = GetterUtil.getLong(request.getAttribute("view.jsp-organizationGroupId"));
PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
int status = GetterUtil.getInteger(request.getAttribute("view.jsp-status"));
String toolbarItem = GetterUtil.getString(request.getAttribute("view.jsp-toolbarItem"));
int usersCount = GetterUtil.getInteger(request.getAttribute("view.jsp-usersCount"));
String usersListView = GetterUtil.getString(request.getAttribute("view.jsp-usersListView"));
String viewUsersRedirect = GetterUtil.getString(request.getAttribute("view.jsp-viewUsersRedirect"));

String keywords = ParamUtil.getString(request, "keywords");

if (organization != null) {
	organizationGroupId = organization.getGroupId();
}

LinkedHashMap<String, Object> organizationParams = new LinkedHashMap<String, Object>();

List<Organization> organizations = new ArrayList<Organization>();

if (filterManageableOrganizations) {
	organizations = user.getOrganizations(true);
}

if (organizationId != OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) {
	organizations.clear();

	organizations.add(OrganizationLocalServiceUtil.getOrganization(organizationId));
}

boolean showList = true;

if (filterManageableOrganizations && organizations.isEmpty()) {
	showList = false;
}

if (organization != null) {
	UsersAdminUtil.addPortletBreadcrumbEntries(organization, request, renderResponse);
}
%>

<c:choose>
	<c:when test="<%= showList %>">
		<aui:form action="<%= portletURL.toString() %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "search();" %>'>
			<liferay-portlet:renderURLParams varImpl="portletURL" />
			<aui:input name="<%= Constants.CMD %>" type="hidden" />
			<aui:input name="toolbarItem" type="hidden" value="<%= toolbarItem %>" />
			<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />

			<%
			int organizationsCount = OrganizationLocalServiceUtil.searchCount(company.getCompanyId(), _getParentOrganizationId(request, organization, filterManageableOrganizations), null, null, null, null, organizationParams);
			%>

			<c:if test="<%= organization != null %>">

				<%
				long parentOrganizationId = OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID;

				if (!organization.isRoot()) {
					Organization parentOrganization = organization.getParentOrganization();

					if (OrganizationPermissionUtil.contains(permissionChecker, parentOrganization, ActionKeys.VIEW)) {
						parentOrganizationId = parentOrganization.getOrganizationId();
					}
				}
				%>

				<portlet:renderURL var="headerBackURL">
					<portlet:param name="toolbarItem" value="view-all-organizations" />
					<portlet:param name="organizationId" value="<%= String.valueOf(parentOrganizationId) %>" />
					<portlet:param name="usersListView" value="<%= (parentOrganizationId == OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) ? UserConstants.LIST_VIEW_FLAT_ORGANIZATIONS : UserConstants.LIST_VIEW_TREE %>" />
				</portlet:renderURL>

				<%
				portletDisplay.setShowBackIcon(true);
				portletDisplay.setURLBack(Validator.isNotNull(backURL) ? backURL : headerBackURL.toString());

				renderResponse.setTitle(organization.getName());
				%>

			</c:if>

			<c:if test="<%= (portletName.equals(UsersAdminPortletKeys.USERS_ADMIN) && usersListView.equals(UserConstants.LIST_VIEW_TREE)) || portletName.equals(UsersAdminPortletKeys.MY_ORGANIZATIONS) %>">
				<div id="breadcrumb">
					<liferay-ui:breadcrumb showCurrentGroup="<%= false %>" showGuestGroup="<%= false %>" showLayout="<%= false %>" showPortletBreadcrumb="<%= true %>" />
				</div>
			</c:if>

			<liferay-ui:panel-container extended="<%= false %>" id="usersAdminOrganizationPanelContainer" persistState="<%= true %>">

				<%
				boolean showOrganizations = false;
				boolean showUsers = true;

				if ((organization == null) && Validator.isNull(keywords) && !PortalPermissionUtil.contains(permissionChecker, ActionKeys.ADD_USER) && !PortalPermissionUtil.contains(permissionChecker, ActionKeys.IMPERSONATE)) {
					showOrganizations = true;
					showUsers = false;
				}

				if (organizationsCount > 0) {
					showOrganizations = true;
				}

				if ((status == WorkflowConstants.STATUS_APPROVED) && (usersCount == 0) && (inactiveUsersCount > 0)) {
					status = WorkflowConstants.STATUS_INACTIVE;
				}
				else if ((status == WorkflowConstants.STATUS_INACTIVE) && (usersCount > 0) && (inactiveUsersCount == 0)) {
					status = WorkflowConstants.STATUS_APPROVED;
				}

				if ((organization != null) && !OrganizationPermissionUtil.contains(permissionChecker, organization, ActionKeys.MANAGE_USERS)) {
					inactiveUsersCount = 0;

					status = WorkflowConstants.STATUS_APPROVED;
				}
				%>

				<aui:input disabled="<%= true %>" name="organizationsRedirect" type="hidden" value="<%= backURL %>" />
				<aui:input name="deleteOrganizationIds" type="hidden" />

				<c:if test="<%= showOrganizations %>">
					<liferay-util:buffer var="organizationsPanelTitle">

						<%
						String organizationsTitle = null;

						if (Validator.isNotNull(keywords)) {
							organizationsTitle = LanguageUtil.get(request, "organizations");
						}
						else if (organization == null) {
							organizationsTitle = LanguageUtil.get(request, filterManageableOrganizations ? "my-organizations" : "top-level-organizations");
						}
						else if (organizationsCount == 1) {
							organizationsTitle = LanguageUtil.format(request, "x-suborganization", String.valueOf(organizationsCount), false);
						}
						else {
							organizationsTitle = LanguageUtil.format(request, "x-suborganizations", String.valueOf(organizationsCount), false);
						}
						%>

						<%= organizationsTitle %>
					</liferay-util:buffer>

					<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="usersAdminOrganizationsPanel" markupView="lexicon" persistState="<%= true %>" title="<%= organizationsPanelTitle %>">

						<%
						SearchContainer searchContainer = new OrganizationSearch(renderRequest, "cur1", currentURLObj);

						RowChecker rowChecker = new EmptyOnClickRowChecker(renderResponse);

						rowChecker.setRowIds("rowIdsOrganization");

						searchContainer.setRowChecker(rowChecker);
						%>

						<liferay-ui:search-container
							id="organizations"
							searchContainer="<%= searchContainer %>"
							var="organizationSearchContainer"
						>

							<%
							OrganizationSearchTerms searchTerms = (OrganizationSearchTerms)organizationSearchContainer.getSearchTerms();

							long parentOrganizationId = _getParentOrganizationId(request, organization, filterManageableOrganizations);

							if (organization != null) {
								parentOrganizationId = organization.getOrganizationId();
							}

							List<Long> excludedOrganizationIds = new ArrayList<Long>();

							excludedOrganizationIds.add(parentOrganizationId);

							organizationParams.put("excludedOrganizationIds", excludedOrganizationIds);
							%>

							<c:choose>
								<c:when test="<%= !searchTerms.hasSearchTerms() && (parentOrganizationId <= 0) && (filterManageableOrganizations) %>">
									<liferay-ui:search-container-results>

										<%
										total = organizations.size();

										searchContainer.setTotal(total);

										results = ListUtil.subList(organizations, searchContainer.getStart(), searchContainer.getEnd());

										searchContainer.setResults(results);
										%>

									</liferay-ui:search-container-results>
								</c:when>
								<c:otherwise>

									<%
									if (searchTerms.hasSearchTerms()) {
										if (filterManageableOrganizations) {
											organizationParams.put("organizationsTree", organizations);
										}
										else if (parentOrganizationId > 0) {
											List<Organization> organizationsTree = new ArrayList<Organization>();

											Organization parentOrganization = OrganizationLocalServiceUtil.getOrganization(parentOrganizationId);

											organizationsTree.add(parentOrganization);

											organizationParams.put("organizationsTree", organizationsTree);
										}

										parentOrganizationId = OrganizationConstants.ANY_PARENT_ORGANIZATION_ID;
									}
									%>

									<liferay-ui:organization-search-container-results organizationParams="<%= organizationParams %>" parentOrganizationId="<%= parentOrganizationId %>" />
								</c:otherwise>
							</c:choose>

							<%
							List<Organization> results = searchContainer.getResults();
							%>

							<c:if test="<%= !results.isEmpty() %>">
								<aui:button cssClass="delete-organizations" disabled="<%= true %>" name="delete" onClick='<%= renderResponse.getNamespace() + "deleteOrganizations();" %>' value="delete" />
							</c:if>

							<liferay-ui:search-container-row
								className="com.liferay.portal.kernel.model.Organization"
								escapedModel="<%= true %>"
								keyProperty="organizationId"
								modelVar="curOrganization"
							>
								<liferay-portlet:renderURL varImpl="rowURL">
									<portlet:param name="mvcRenderCommandName" value="/users_admin/view" />
									<portlet:param name="toolbarItem" value="<%= toolbarItem %>" />
									<portlet:param name="organizationId" value="<%= String.valueOf(curOrganization.getOrganizationId()) %>" />
									<portlet:param name="usersListView" value="<%= UserConstants.LIST_VIEW_TREE %>" />
								</liferay-portlet:renderURL>

								<%
								if (!OrganizationPermissionUtil.contains(permissionChecker, curOrganization, ActionKeys.VIEW)) {
									rowURL = null;
								}
								%>

								<%@ include file="/organization/organization_columns.jspf" %>
							</liferay-ui:search-container-row>

							<liferay-ui:search-iterator markupView="lexicon" />
						</liferay-ui:search-container>
					</liferay-ui:panel>
				</c:if>

				<c:if test="<%= showUsers %>">
					<liferay-util:buffer var="usersPanelTitle">

						<%
						boolean active = false;

						if (status == WorkflowConstants.STATUS_APPROVED) {
							active = true;
						}

						String usersTitle = null;

						if (Validator.isNotNull(keywords) || ((organization == null) && (organizationsCount == 0))) {
							usersTitle = LanguageUtil.get(request, (active ? "users" : "inactive-users"));
						}
						else if (organization == null) {
							usersTitle = LanguageUtil.get(request, (active ? "users-without-an-organization" : "inactive-users-without-an-organization"));
						}
						else if ((usersCount == 0) && (inactiveUsersCount == 0)) {
							usersTitle = LanguageUtil.format(request, (active ? "x-users" : "x-inactive-users"), "0");
						}
						else {
							if ((active && (usersCount == 1)) || (!active && (inactiveUsersCount == 1))) {
								usersTitle = LanguageUtil.format(request, (active ? "x-user" : "x-inactive-user"), String.valueOf((active ? usersCount : inactiveUsersCount)), false);
							}
							else {
								usersTitle = LanguageUtil.format(request, (active ? "x-users" : "x-inactive-users"), String.valueOf((active ? usersCount : inactiveUsersCount)), false);
							}
						}
						%>

						<%= usersTitle %>
					</liferay-util:buffer>

					<c:if test="<%= (organization != null) || (usersCount != 0) || (inactiveUsersCount == 0) %>">

						<%
						boolean organizationContextView = true;
						%>

						<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="usersAdminUsersPanel" markupView="lexicon" persistState="<%= true %>" title="<%= usersPanelTitle %>">
							<liferay-util:include page="/view_flat_users.jsp" servletContext="<%= application %>" />
						</liferay-ui:panel>
					</c:if>
				</c:if>
			</liferay-ui:panel-container>
		</aui:form>
	</c:when>
	<c:otherwise>
		<div class="alert alert-info">
			<liferay-ui:message key="you-do-not-belong-to-an-organization-and-are-not-allowed-to-view-other-organizations" />
		</div>
	</c:otherwise>
</c:choose>

<%@ include file="/add_menu.jspf" %>

<aui:script>
	Liferay.Util.toggleSearchContainerButton('#<portlet:namespace />delete', '#<portlet:namespace /><%= searchContainerReference.getId(request, "organizationSearchContainer") %>SearchContainer', document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');
</aui:script>

<%!
private long _getParentOrganizationId(HttpServletRequest request, Organization organization, boolean filterManageableOrganizations) {
	if (organization != null) {
		return organization.getOrganizationId();
	}

	if (filterManageableOrganizations) {
		return OrganizationConstants.ANY_PARENT_ORGANIZATION_ID;
	}

	return ParamUtil.getLong(request, "parentOrganizationId", OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID);
}
%>