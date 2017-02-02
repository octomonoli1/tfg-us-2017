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
boolean portalUser = ParamUtil.getBoolean(request, "portalUser");
%>

<div>
	<c:choose>
		<c:when test="<%= !portalUser %>">

			<%
			long entryId = ParamUtil.getLong(request, "entryId");
			%>

			<c:if test="<%= entryId > 0 %>">

				<%
				Entry entry = EntryLocalServiceUtil.getEntry(entryId);

				String redirect = ParamUtil.getString(request, "redirect");
				%>

				<div id="<portlet:namespace />contactSummary">
					<liferay-util:include page="/contacts_center/view_entry.jsp" servletContext="<%= application %>" />
				</div>

				<span id="<portlet:namespace />contactsToolbar">
					<div class="lfr-button-column">
						<div class="lfr-button-column-content">
							<aui:button-row cssClass="edit-toolbar" id='<%= renderResponse.getNamespace() + "entryToolbar" %>' />
						</div>
					</div>

					<aui:script position="inline" use="aui-io-request-deprecated,aui-toolbar">
						var buttonRow = A.one('#<portlet:namespace />entryToolbar');

						var contactsToolbarChildren = [];

						<portlet:renderURL var="viewEntryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
							<portlet:param name="mvcPath" value="/contacts_center/edit_entry.jsp" />
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="entryId" value="<%= String.valueOf(entryId) %>" />
						</portlet:renderURL>

						contactsToolbarChildren.push(
							{
								on: {
									click: function(event) {
										Liferay.component('contactsCenter').showPopup('<%= UnicodeLanguageUtil.get(request, "update-contact") %>', '<%= viewEntryURL %>');
									}
								},
								icon: 'icon-edit',
								id: '<portlet:namespace />edit',
								label: '<%= UnicodeLanguageUtil.get(request, "edit") %>'
							}
						);

						contactsToolbarChildren.push(
							{
								on: {
									click: function(event) {
										var confirmMessage = '<%= UnicodeLanguageUtil.format(request, "are-you-sure-you-want-to-delete-x-from-your-contacts", entry.getFullName(), false) %>';

										if (confirm(confirmMessage)) {
											A.io.request(
												'<portlet:actionURL name="deleteEntry" />',
												{
													after: {
														failure: function(event, id, obj) {
															Liferay.component('contactsCenter').showMessage(false);
														},
														success: function(event, id, obj) {
															location.href = '<%= HtmlUtil.escape(redirect) %>';
														}
													},
													data: {
														<portlet:namespace />entryId: <%= entryId %>
													}
												}
											);
										}
									}
								},
								icon: 'icon-remove',
								id: '<portlet:namespace />delete',
								label: '<%= UnicodeLanguageUtil.get(request, "delete") %>'
							}
						);

						new A.Toolbar(
							{
								activeState: false,
								boundingBox: buttonRow,
								children: contactsToolbarChildren
							}
						).render();
					</aui:script>
				</span>
			</c:if>
		</c:when>
		<c:otherwise>

			<%
			long userId = ParamUtil.getLong(request, "userId");

			User user2 = null;

			if (userId > 0) {
				user2 = UserLocalServiceUtil.getUser(userId);
			}
			%>

			<c:if test="<%= user2 != null %>">
				<div id="<portlet:namespace />contactSummary">
					<liferay-util:include page="/view_user.jsp" servletContext="<%= application %>" />
				</div>
			</c:if>

			<span id="<portlet:namespace />contactsToolbar">

				<%
				boolean showDetailView = ParamUtil.getBoolean(request, "showDetailView");
				%>

				<c:choose>
					<c:when test="<%= showDetailView %>">
						<div class="lfr-button-column">
							<div class="lfr-button-column-content">
								<aui:button-row cssClass="edit-toolbar" id='<%= renderResponse.getNamespace() + "userToolbar" %>' />
							</div>
						</div>

						<aui:script position="inline" use="aui-base,aui-toolbar,liferay-contacts-center">
							var buttonRow = A.one('#<portlet:namespace />userToolbar');

							var contactsToolbarChildren = [];

							contactsToolbarChildren.push(
								{
									icon: 'icon-chevron-sign-left',
									id: '<portlet:namespace />backSelection',
									label: '<%= UnicodeLanguageUtil.get(request, "back-to-selection") %>',
									on: {
										click: function(event) {
											Liferay.component('contactsCenter')._setVisibleSelectedUsersView();
										}
									}
								}
							);

							new A.Toolbar(
								{
									activeState: false,
									boundingBox: buttonRow,
									children: contactsToolbarChildren
								}
							).render();
						</aui:script>
					</c:when>
					<c:otherwise>
						<liferay-util:include page="/contacts_center/contacts_center_toolbar.jsp" servletContext="<%= application %>" />
					</c:otherwise>
				</c:choose>
			</span>
		</c:otherwise>
	</c:choose>
</div>