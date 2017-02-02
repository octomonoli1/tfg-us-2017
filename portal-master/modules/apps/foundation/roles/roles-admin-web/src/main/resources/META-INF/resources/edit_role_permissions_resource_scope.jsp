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

Object[] objArray = (Object[])row.getObject();

Role role = (Role)objArray[0];
String target = (String)objArray[3];
Boolean supportsFilterByGroup = (Boolean)objArray[5];
List groups = (List)objArray[6];
long[] groupIdsArray = (long[])objArray[7];
List groupNames = (List)objArray[8];
%>

<aui:input name='<%= "groupIds" + HtmlUtil.escapeAttribute(target) %>' type="hidden" value="<%= StringUtil.merge(groupIdsArray) %>" />
<aui:input name='<%= "groupNames" + HtmlUtil.escapeAttribute(target) %>' type="hidden" value='<%= StringUtil.merge(groupNames, "@@") %>' />

<div id="<portlet:namespace />groupDiv<%= HtmlUtil.escapeAttribute(target) %>">
	<span class="permission-scopes" id="<portlet:namespace />groupHTML<%= HtmlUtil.escapeAttribute(target) %>">

		<%
		if (supportsFilterByGroup && !groups.isEmpty()) {
			for (int i = 0; i < groups.size(); i++) {
				Group group = (Group)groups.get(i);

				String taglibHREF = "javascript:" + liferayPortletResponse.getNamespace() + "removeGroup(" + i + ", '" + HtmlUtil.escapeJS(target) + "');";
		%>

				<span class="lfr-token">
					<span class="lfr-token-text"><%= HtmlUtil.escape(group.getDescriptiveName(locale)) %></span>

					<aui:a cssClass="icon icon-remove lfr-token-close" href="<%= taglibHREF %>" />
				</span>

		<%
			}
		}
		else if (role.getType() == RoleConstants.TYPE_REGULAR) {
		%>

			<liferay-ui:message key="all-sites" />

		<%
		}
		%>

	</span>

	<%
	String targetId = target.replace(".", "");
	%>

	<c:if test="<%= supportsFilterByGroup %>">
		<liferay-ui:icon
			iconCssClass="icon-cog"
			id="<%= HtmlUtil.escapeAttribute(targetId) %>"
			label="<%= true %>"
			message="change"
			url="javascript:;"
		/>

		<aui:script>
			AUI.$('#<portlet:namespace /><%= HtmlUtil.escapeJS(targetId) %>').on(
				'click',
				function(event) {
					Liferay.Util.selectEntity(
						{
							dialog: {
								constrain: true,
								modal: true,
								width: 600
							},
							id: '<portlet:namespace />selectGroup<%= HtmlUtil.escapeJS(targetId) %>',
							title: '<liferay-ui:message arguments="site" key="select-x" />',

							<%
							PortletURL groupSelectorURL = PortletProviderUtil.getPortletURL(request, Group.class.getName(), PortletProvider.Action.BROWSE);

							groupSelectorURL.setParameter("includeCompany", Boolean.TRUE.toString());
							groupSelectorURL.setParameter("includeCurrentGroup", Boolean.FALSE.toString());
							groupSelectorURL.setParameter("includeUserPersonalSite", Boolean.TRUE.toString());
							groupSelectorURL.setParameter("eventName", liferayPortletResponse.getNamespace() + "selectGroup");
							groupSelectorURL.setParameter("target", target);
							groupSelectorURL.setWindowState(LiferayWindowState.POP_UP);
							%>

							uri: '<%= groupSelectorURL.toString() %>'
						}
					);
				}
			);
		</aui:script>
	</c:if>
</div>