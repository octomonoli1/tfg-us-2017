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

<%@ include file="/admin/common/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute("info_panel.jsp-kbArticle");

int selStatus = KBArticlePermission.contains(permissionChecker, kbArticle, KBActionKeys.UPDATE) ? WorkflowConstants.STATUS_ANY : WorkflowConstants.STATUS_APPROVED;

String orderByCol = ParamUtil.getString(request, "orderByCol", "version");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");

OrderByComparator orderByComparator = KBUtil.getKBArticleOrderByComparator(orderByCol, orderByType);

List<KBArticle> kbArticles = KBArticleServiceUtil.getKBArticleVersions(scopeGroupId, kbArticle.getResourcePrimKey(), selStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS, orderByComparator);

for (KBArticle curKBArticle : kbArticles) {
%>

	<aui:row>
		<aui:col width="<%= 100 %>">
			<ul class="sidebar-header-actions">
				<li>
					<liferay-ui:icon-menu direction="left-side" icon="<%= StringPool.BLANK %>" markupView="lexicon" message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">
						<c:if test="<%= (kbArticle.getStatus() == WorkflowConstants.STATUS_APPROVED) && KBArticlePermission.contains(permissionChecker, kbArticle, KBActionKeys.UPDATE) %>">
							<liferay-portlet:actionURL name="updateKBArticle" varImpl="revertURL">
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
								<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.REVERT %>" />
								<portlet:param name="version" value="<%= String.valueOf(curKBArticle.getVersion()) %>" />
								<portlet:param name="workflowAction" value="<%= String.valueOf(WorkflowConstants.ACTION_PUBLISH) %>" />
							</liferay-portlet:actionURL>

							<%
							revertURL.setParameter("section", AdminUtil.unescapeSections(curKBArticle.getSections()));
							%>

							<liferay-ui:icon
								iconCssClass="icon-undo"
								label="<%= true %>"
								message="revert"
								url="<%= revertURL.toString() %>"
							/>
						</c:if>

						<portlet:renderURL var="compareVersionsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="mvcPath" value="/admin/common/select_version.jsp" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
							<portlet:param name="sourceVersion" value="<%= String.valueOf(curKBArticle.getVersion()) %>" />
						</portlet:renderURL>

						<%
						Map<String, Object> data = new HashMap<String, Object>();

						data.put("uri", compareVersionsURL);
						%>

						<liferay-ui:icon
							cssClass="compare-to-link"
							data="<%= data %>"
							label="<%= true %>"
							message="compare-to"
							url="javascript:;"
						/>

						<aui:script sandbox="<%= true %>">
							$('body').on(
								'click',
								'.compare-to-link a',
								function(event) {
									var currentTarget = $(event.currentTarget);

									Liferay.Util.selectEntity(
										{
											dialog: {
												constrain: true,
												destroyOnHide: true,
												modal: true
											},
											eventName: '<portlet:namespace />selectVersionFm',
											id: '<portlet:namespace />compareVersions' + currentTarget.attr('id'),
											title: '<liferay-ui:message key="compare-versions" />',
											uri: currentTarget.data('uri')
										},
										function(event) {
											<portlet:renderURL var="compareVersionURL">
												<portlet:param name="mvcPath" value="/admin/common/compare_versions.jsp" />
												<portlet:param name="backURL" value="<%= currentURL %>" />
												<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
											</portlet:renderURL>

											var uri = '<%= compareVersionURL %>';

											uri = Liferay.Util.addParams('<portlet:namespace />sourceVersion=' + event.sourceversion, uri);
											uri = Liferay.Util.addParams('<portlet:namespace />targetVersion=' + event.targetversion, uri);

											location.href = uri;
										}
									);
								}
							);
						</aui:script>
					</liferay-ui:icon-menu>
				</li>
			</ul>

			<dl>
				<dt class="h5">
					<h4><liferay-ui:message arguments="<%= curKBArticle.getVersion() %>" key="version-x" /></h4>
				</dt>
				<dd>
					<small class="text-muted">
						<liferay-ui:message arguments="<%= new Object[] {curKBArticle.getUserName(), dateFormatDateTime.format(curKBArticle.getModifiedDate())} %>" key="by-x-on-x" />
					</small>
				</dd>
			</dl>
		</aui:col>
	</aui:row>

<%
}
%>