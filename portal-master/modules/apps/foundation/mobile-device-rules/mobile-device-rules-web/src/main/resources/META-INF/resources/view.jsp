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
String className = ParamUtil.getString(request, "className");
long classPK = ParamUtil.getLong(request, "classPK");

String displayStyle = ParamUtil.getString(request, "displayStyle", "list");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("groupId", String.valueOf(groupId));

RuleGroupSearch ruleGroupSearch = new RuleGroupSearch(liferayPortletRequest, PortletURLUtil.clone(portletURL, renderResponse));

RuleGroupSearchTerms searchTerms = (RuleGroupSearchTerms)ruleGroupSearch.getSearchTerms();

boolean hasAddRuleGroupPermission = MDRPermission.contains(permissionChecker, groupId, ActionKeys.ADD_RULE_GROUP);

if (!searchTerms.isSearch() && hasAddRuleGroupPermission) {
	ruleGroupSearch.setEmptyResultsMessageCssClass("taglib-empty-result-message-header-has-plus-btn");
}

ruleGroupSearch.setEmptyResultsMessageCssClass(searchTerms.isSearch() ? StringPool.BLANK : "taglib-empty-result-message-header-has-plus-btn");

LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

params.put("includeGlobalScope", Boolean.TRUE);

int mdrRuleGroupsCount = MDRRuleGroupLocalServiceUtil.searchByKeywordsCount(groupId, searchTerms.getKeywords(), params, searchTerms.isAndOperator());

ruleGroupSearch.setTotal(mdrRuleGroupsCount);

List<MDRRuleGroup> mdrRuleGroups = MDRRuleGroupLocalServiceUtil.searchByKeywords(groupId, searchTerms.getKeywords(), params, searchTerms.isAndOperator(), ruleGroupSearch.getStart(), ruleGroupSearch.getEnd(), ruleGroupSearch.getOrderByComparator());

ruleGroupSearch.setResults(mdrRuleGroups);
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<portlet:renderURL var="mainURL" />

	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="<%= mainURL.toString() %>" label="device-families" selected="<%= true %>" />
	</aui:nav>

	<c:if test="<%= (mdrRuleGroupsCount > 0) || searchTerms.isSearch() %>">
		<aui:nav-bar-search>
			<aui:form action="<%= portletURL.toString() %>" name="searchFm">
				<liferay-ui:input-search markupView="lexicon" />
			</aui:form>
		</aui:nav-bar-search>
	</c:if>
</aui:nav-bar>

<liferay-frontend:management-bar
	disabled="<%= mdrRuleGroupsCount <= 0 %>"
	includeCheckBox="<%= true %>"
	searchContainerId="deviceFamilies"
>

	<%
	PortletURL displayStyleURL = PortletURLUtil.clone(portletURL, renderResponse);
	%>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'
			portletURL="<%= displayStyleURL %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>

	<%
	PortletURL iteratorURL = PortletURLUtil.clone(portletURL, renderResponse);

	iteratorURL.setParameter("displayStyle", displayStyle);
	%>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= iteratorURL %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= ruleGroupSearch.getOrderByCol() %>"
			orderByType="<%= ruleGroupSearch.getOrderByType() %>"
			orderColumns='<%= new String[] {"create-date"} %>'
			portletURL="<%= iteratorURL %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-action-buttons>
		<liferay-frontend:management-bar-button href="javascript:;" icon="trash" id="deleteSelectedDeviceFamilies" label="delete" />
	</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<portlet:actionURL name="/mobile_device_rules/edit_rule_group" var="editRuleGroupURL">
	<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/edit_rule_group" />
</portlet:actionURL>

<aui:form action="<%= editRuleGroupURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.DELETE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="ruleGroupIds" type="hidden" />

	<liferay-ui:search-container
		id="deviceFamilies"
		rowChecker="<%= new RuleGroupChecker(renderResponse) %>"
		searchContainer="<%= ruleGroupSearch %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.mobile.device.rules.model.MDRRuleGroup"
			escapedModel="<%= true %>"
			keyProperty="ruleGroupId"
			modelVar="ruleGroup"
		>

			<%
			Group group = GroupLocalServiceUtil.getGroup(ruleGroup.getGroupId());

			String rowHREF = null;

			if (MDRRuleGroupPermission.contains(permissionChecker, ruleGroup.getRuleGroupId(), ActionKeys.VIEW) && MDRPermission.contains(permissionChecker, groupId, ActionKeys.ADD_RULE_GROUP)) {
			%>

				<portlet:renderURL var="editRulesURL">
					<portlet:param name="mvcPath" value="/view_rules.jsp" />
					<portlet:param name="ruleGroupId" value="<%= String.valueOf(ruleGroup.getRuleGroupId()) %>" />
					<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
				</portlet:renderURL>

			<%
				rowHREF = editRulesURL;
			}
			%>

			<c:choose>
				<c:when test='<%= displayStyle.equals("descriptive") %>'>
					<liferay-ui:search-container-column-icon
						icon="mobile-portrait"
						toggleRowChecker="<%= true %>"
					/>

					<liferay-ui:search-container-column-text
						colspan="<%= 2 %>"
					>
						<h6 class="text-default">
							<liferay-ui:message arguments="<%= LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - ruleGroup.getModifiedDate().getTime(), true) %>" key="x-ago" translateArguments="<%= false %>" />
						</h6>

						<h5>
							<aui:a href="<%= rowHREF %>"><%= ruleGroup.getName(locale) %></aui:a>
						</h5>

						<h6 class="text-default">
							<%= HtmlUtil.escape(ruleGroup.getDescription(locale)) %>
						</h6>

						<h6 class="text-default">
							<strong><liferay-ui:message key="scope" /></strong>: <%= LanguageUtil.get(resourceBundle, group.getScopeLabel(themeDisplay)) %>
						</h6>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-jsp
						path="/rule_group_actions.jsp"
					/>
				</c:when>
				<c:when test='<%= displayStyle.equals("icon") %>'>

					<%
					row.setCssClass("entry-card lfr-asset-item");
					%>

					<liferay-ui:search-container-column-text>
						<liferay-frontend:icon-vertical-card
							actionJsp="/rule_group_actions.jsp"
							actionJspServletContext="<%= application %>"
							icon="mobile-portrait"
							resultRow="<%= row %>"
							rowChecker="<%= searchContainer.getRowChecker() %>"
							subtitle="<%= HtmlUtil.escape(ruleGroup.getDescription(locale)) %>"
							title="<%= ruleGroup.getName(locale) %>"
							url="<%= rowHREF %>"
						>
							<liferay-frontend:vertical-card-header>
								<liferay-ui:message arguments="<%= LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - ruleGroup.getModifiedDate().getTime(), true) %>" key="x-ago" translateArguments="<%= false %>" />
							</liferay-frontend:vertical-card-header>
						</liferay-frontend:icon-vertical-card>
					</liferay-ui:search-container-column-text>
				</c:when>
				<c:when test='<%= displayStyle.equals("list") %>'>
					<%@ include file="/rule_group_columns.jspf" %>
				</c:when>
			</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator displayStyle="<%= displayStyle %>" markupView="lexicon" type="more" />
	</liferay-ui:search-container>
</aui:form>

<c:if test="<%= hasAddRuleGroupPermission %>">
	<portlet:renderURL var="viewRulesURL">
		<portlet:param name="mvcRenderCommandName" value="/view.jsp" />
		<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
		<portlet:param name="className" value="<%= className %>" />
		<portlet:param name="classPK" value="<%= String.valueOf(classPK) %>" />
	</portlet:renderURL>

	<liferay-portlet:renderURL var="addRuleGroupURL">
		<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/edit_rule_group" />
		<portlet:param name="redirect" value="<%= viewRulesURL %>" />
		<portlet:param name="backURL" value="<%= viewRulesURL %>" />
		<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
	</liferay-portlet:renderURL>

	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(resourceBundle, "add-device-family") %>' url="<%= addRuleGroupURL %>" />
	</liferay-frontend:add-menu>
</c:if>

<aui:script>
	$('#<portlet:namespace />deleteSelectedDeviceFamilies').on(
		'click',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(resourceBundle, "are-you-sure-you-want-to-delete-this") %>')) {
				submitForm($(document.<portlet:namespace />fm));
			}
		}
	);
</aui:script>