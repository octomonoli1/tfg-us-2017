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
long layoutSetBranchId = ParamUtil.getLong(request, "layoutSetBranchId");

LayoutRevision recentLayoutRevision = null;

long currentLayoutRevisionId = StagingUtil.getRecentLayoutRevisionId(request, layoutSetBranchId, plid);

if (currentLayoutRevisionId > 0) {
	recentLayoutRevision = LayoutRevisionLocalServiceUtil.getLayoutRevision(currentLayoutRevisionId);
}
else {
	recentLayoutRevision = LayoutStagingUtil.getLayoutRevision(layout);

	currentLayoutRevisionId = recentLayoutRevision.getLayoutRevisionId();
}

List<LayoutRevision> rootLayoutRevisions = LayoutRevisionLocalServiceUtil.getChildLayoutRevisions(layoutSetBranchId, LayoutRevisionConstants.DEFAULT_PARENT_LAYOUT_REVISION_ID, plid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new LayoutRevisionIdComparator(true));
%>

<liferay-util:include page="/navigation.jsp" servletContext="<%= application %>">
	<liferay-util:param name="navigationName" value="history" />
</liferay-util:include>

<div class="container-fluid-1280">
	<c:if test="<%= !rootLayoutRevisions.isEmpty() %>">
		<c:if test="<%= rootLayoutRevisions.size() > 1 %>">
			<aui:select cssClass="variation-selector" inlineLabel="left" label="" name="variationsSelector">

				<%
				for (LayoutRevision rootLayoutRevision : rootLayoutRevisions) {
					LayoutBranch layoutBranch = rootLayoutRevision.getLayoutBranch();
				%>

					<aui:option label="<%= HtmlUtil.escape(layoutBranch.getName()) %>" selected="<%= recentLayoutRevision.getLayoutBranchId() == rootLayoutRevision.getLayoutBranchId() %>" value="<%= rootLayoutRevision.getLayoutRevisionId() %>" />

				<%
				}
				%>

				<aui:option label="all-page-variations" value="all" />
			</aui:select>
		</c:if>

		<div class="layout-revision-container" id="<portlet:namespace/>layoutRevisionsContainer">

			<%
			for (LayoutRevision rootLayoutRevision : rootLayoutRevisions) {
			%>

				<div class="layout-variation-container <%= (recentLayoutRevision.getLayoutBranchId() == rootLayoutRevision.getLayoutBranchId()) ? StringPool.BLANK : "hide" %>" id="<portlet:namespace/><%= rootLayoutRevision.getLayoutRevisionId() %>">
					<c:if test="<%= rootLayoutRevisions.size() > 1 %>">

						<%
						LayoutBranch layoutBranch = rootLayoutRevision.getLayoutBranch();
						%>

						<h3 class="layout-variation-name"><liferay-ui:message key="<%= HtmlUtil.escape(layoutBranch.getName()) %>" /></h3>
					</c:if>

					<liferay-ui:search-container
						total="<%= LayoutRevisionLocalServiceUtil.getLayoutRevisionsCount(rootLayoutRevision.getLayoutSetBranchId(), rootLayoutRevision.getLayoutBranchId(), rootLayoutRevision.getPlid()) %>"
					>
						<liferay-ui:search-container-results
							results="<%= LayoutRevisionLocalServiceUtil.getLayoutRevisions(rootLayoutRevision.getLayoutSetBranchId(), rootLayoutRevision.getLayoutBranchId(), rootLayoutRevision.getPlid(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, new LayoutRevisionIdComparator(false)) %>"
						/>

						<liferay-ui:search-container-row
							className="com.liferay.portal.kernel.model.LayoutRevision"
							escapedModel="<%= true %>"
							keyProperty="layoutRevisionId"
							modelVar="curLayoutRevision"
						>
							<liferay-ui:search-container-column-date
								name="date"
								value="<%= curLayoutRevision.getCreateDate() %>"
							/>

							<liferay-ui:search-container-column-text
								name="status"
							>
								<aui:model-context bean="<%= curLayoutRevision %>" model="<%= LayoutRevision.class %>" />

								<%
								int status = curLayoutRevision.getStatus();
								%>

								<c:choose>
									<c:when test="<%= curLayoutRevision.isHead() %>">
										<aui:workflow-status showIcon="<%= false %>" showLabel="<%= false %>" status="<%= status %>" statusMessage="ready-for-publication" />
									</c:when>
									<c:otherwise>
										<aui:workflow-status showIcon="<%= false %>" showLabel="<%= false %>" status="<%= status %>" />
									</c:otherwise>
								</c:choose>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								name="version"
							>
								<c:choose>
									<c:when test="<%= curLayoutRevision.getLayoutRevisionId() == currentLayoutRevisionId %>">
										<span class="layout-revision-current"><%= curLayoutRevision.getLayoutRevisionId() %></span>
										<span class="current-version"><liferay-ui:message key="current-version" /></span>
									</c:when>
									<c:otherwise>
										<a class="layout-revision selection-handle" data-layoutRevisionId="<%= curLayoutRevision.getLayoutRevisionId() %>" data-layoutSetBranchId="<%= curLayoutRevision.getLayoutSetBranchId() %>" href="javascript:;" onclick="<%= renderResponse.getNamespace() + "selectRevision(" + curLayoutRevision.getLayoutRevisionId() + ", " + curLayoutRevision.getLayoutSetBranchId() + ");" %>" title="<liferay-ui:message key="go-to-this-version" />">
											<%= curLayoutRevision.getLayoutRevisionId() %>
										</a>
									</c:otherwise>
								</c:choose>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								name="user"
							>

								<%
								User curUser = UserLocalServiceUtil.fetchUserById(curLayoutRevision.getUserId());
								%>

								<c:choose>
									<c:when test="<%= curUser != null %>">
										<a class="user-handle" href="<%= curUser.getDisplayURL(themeDisplay) %>">
											<%= HtmlUtil.escape(curUser.getFullName()) %>
										</a>
									</c:when>
									<c:otherwise>
										<%= curLayoutRevision.getUserName() %>
									</c:otherwise>
								</c:choose>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-jsp
								align="right"
								cssClass="entry-action"
								path="/layout_revision_action.jsp"
							/>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator markupView="lexicon" paginate="<%= false %>" searchContainer="<%= searchContainer %>" />
					</liferay-ui:search-container>
				</div>

			<%
			}
			%>

		</div>
	</c:if>
</div>

<aui:script>
	function <portlet:namespace/>selectRevision(layoutRevisionId, layoutSetBranchId) {
		AUI.$.ajax(
			themeDisplay.getPathMain() + '/portal/update_layout',
			{
				data: {
					cmd: 'select_layout_revision',
					doAsUserId: themeDisplay.getDoAsUserIdEncoded(),
					layoutRevisionId: layoutRevisionId,
					layoutSetBranchId: layoutSetBranchId,
					p_auth: Liferay.authToken,
					p_l_id: themeDisplay.getPlid(),
					p_v_l_s_g_id: themeDisplay.getSiteGroupId()
				},
				success: function(event, id, obj) {
					var parentWindow = Liferay.Util.getOpener();

					parentWindow.location.reload();
				}
			}
		);
	}
</aui:script>

<aui:script sandbox="<%= true %>">
	var variationsSelector = $('#<portlet:namespace/>variationsSelector');

	var layoutBranchesContainer = $('.layout-variation-container');

	variationsSelector.on(
		'change',
		function() {
			var variation = variationsSelector.val();

			if (variation == 'all') {
				layoutBranchesContainer.removeClass('hide');
			}
			else {
				layoutBranchesContainer.addClass('hide');

				$('#<portlet:namespace/>' + variation).removeClass('hide');
			}
		}
	);
</aui:script>