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
KBArticle kbArticle = (KBArticle)request.getAttribute(KBWebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

int status = (Integer)request.getAttribute(KBWebKeys.KNOWLEDGE_BASE_STATUS);
%>

<div class="kb-article-tools">
	<c:if test="<%= kbGroupServiceConfiguration.sourceURLEnabled() && Validator.isUrl(kbArticle.getSourceURL()) %>">
		<a href="<%= kbArticle.getSourceURL() %>" target="_blank">
			<span class="kb-article-source-url label label-success">
				<liferay-ui:message key="<%= kbGroupServiceConfiguration.sourceURLEditMessageKey() %>" />
			</span>
		</a>
	</c:if>

	<c:if test="<%= enableRSS && (kbArticle.isApproved() || !kbArticle.isFirstVersion()) && !Objects.equals(portletDisplay.getRootPortletId(), KBPortletKeys.KNOWLEDGE_BASE_ADMIN) %>">
		<liferay-portlet:resourceURL id="kbArticleRSS" varImpl="kbArticleRSSURL">
			<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
		</liferay-portlet:resourceURL>

		<liferay-ui:rss
			delta="<%= rssDelta %>"
			displayStyle="<%= rssDisplayStyle %>"
			feedType="<%= rssFeedType %>"
			resourceURL="<%= kbArticleRSSURL %>"
		/>
	</c:if>

	<c:if test="<%= !rootPortletId.equals(KBPortletKeys.KNOWLEDGE_BASE_ADMIN) %>">
		<c:if test="<%= enableKBArticleSubscriptions && (kbArticle.isApproved() || !kbArticle.isFirstVersion()) && KBArticlePermission.contains(permissionChecker, kbArticle, KBActionKeys.SUBSCRIBE) %>">
			<c:choose>
				<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), KBArticle.class.getName(), kbArticle.getResourcePrimKey()) %>">
					<liferay-portlet:actionURL name="unsubscribeKBArticle" var="unsubscribeKBArticleURL">
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
					</liferay-portlet:actionURL>

					<liferay-ui:icon
						iconCssClass="icon-remove-sign"
						label="<%= true %>"
						message="unsubscribe"
						url="<%= unsubscribeKBArticleURL %>"
					/>
				</c:when>
				<c:otherwise>
					<liferay-portlet:actionURL name="subscribeKBArticle" var="subscribeKBArticleURL">
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
					</liferay-portlet:actionURL>

					<liferay-ui:icon
						iconCssClass="icon-ok-sign"
						label="<%= true %>"
						message="subscribe"
						url="<%= subscribeKBArticleURL %>"
					/>
				</c:otherwise>
			</c:choose>
		</c:if>

		<c:if test="<%= enableKBArticleHistory && (kbArticle.isApproved() || !kbArticle.isFirstVersion()) %>">
			<liferay-portlet:renderURL var="historyURL">
				<portlet:param name="mvcPath" value='<%= templatePath + "history.jsp" %>' />
				<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
				<portlet:param name="status" value="<%= String.valueOf(status) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:icon
				iconCssClass="icon-file-alt"
				label="<%= true %>"
				message="history"
				url="<%= historyURL %>"
			/>
		</c:if>

		<c:if test="<%= enableKBArticlePrint %>">
			<liferay-portlet:renderURL var="printURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value='<%= templatePath + "print_article.jsp" %>' />
				<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
				<portlet:param name="status" value="<%= String.valueOf(status) %>" />
			</liferay-portlet:renderURL>

			<%
			String taglibURL = "javascript:var printKBArticleWindow = window.open('" + printURL + "', 'printKBArticle', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); printKBArticleWindow.focus();";
			%>

			<liferay-ui:icon
				iconCssClass="icon-print"
				label="<%= true %>"
				message="print"
				url="<%= taglibURL %>"
			/>
		</c:if>

		<c:if test="<%= (!rootPortletId.equals(KBPortletKeys.KNOWLEDGE_BASE_DISPLAY) || DisplayPermission.contains(permissionChecker, scopeGroupId, KBActionKeys.ADMINISTRATOR)) && KBArticlePermission.contains(permissionChecker, kbArticle, KBActionKeys.UPDATE) %>">
			<liferay-portlet:renderURL var="editURL">
				<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
				<portlet:param name="redirect" value="<%= redirect %>" />
				<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
				<portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_ANY) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:icon
				iconCssClass="icon-edit"
				label="<%= true %>"
				message="edit"
				method="get"
				url="<%= editURL %>"
			/>
		</c:if>

		<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, KBActionKeys.ADD_KB_ARTICLE) && rootPortletId.equals(KBPortletKeys.KNOWLEDGE_BASE_ADMIN)) || (DisplayPermission.contains(permissionChecker, scopeGroupId, KBActionKeys.ADD_KB_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, KBActionKeys.ADMINISTRATOR) && rootPortletId.equals(KBPortletKeys.KNOWLEDGE_BASE_DISPLAY)) %>">
			<liferay-portlet:renderURL var="addKBArticleURL">
				<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
				<portlet:param name="redirect" value="<%= redirect %>" />
				<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
				<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:icon
				iconCssClass="icon-plus"
				label="<%= true %>"
				message="add-child-article"
				method="get"
				url="<%= addKBArticleURL %>"
			/>
		</c:if>

		<c:if test="<%= kbArticle.isRoot() && KBArticlePermission.contains(permissionChecker, kbArticle, KBActionKeys.PERMISSIONS) %>">
			<liferay-security:permissionsURL
				modelResource="<%= KBArticle.class.getName() %>"
				modelResourceDescription="<%= kbArticle.getTitle() %>"
				resourcePrimKey="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>"
				var="permissionsURL"
				windowState="<%= LiferayWindowState.POP_UP.toString() %>"
			/>

			<liferay-ui:icon
				iconCssClass="icon-lock"
				label="<%= true %>"
				message="permissions"
				method="get"
				url="<%= permissionsURL %>"
				useDialog="<%= true %>"
			/>
		</c:if>

		<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, KBActionKeys.MOVE_KB_ARTICLE) %>">
			<liferay-portlet:renderURL var="moveKBArticleURL">
				<portlet:param name="mvcPath" value='<%= templatePath + "move_object.jsp" %>' />
				<portlet:param name="redirect" value="<%= redirect %>" />
				<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
				<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(kbArticle.getParentResourceClassNameId()) %>" />
				<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(kbArticle.getParentResourcePrimKey()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:icon
				iconCssClass="icon-move"
				label="<%= true %>"
				message="move"
				method="get"
				url="<%= moveKBArticleURL %>"
			/>
		</c:if>

		<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, KBActionKeys.DELETE) %>">
			<liferay-portlet:renderURL var="homeURL">
				<portlet:param name="mvcPath" value='<%= templatePath + "view.jsp" %>' />
			</liferay-portlet:renderURL>

			<liferay-portlet:actionURL name="deleteKBArticle" var="deleteURL">
				<portlet:param name="mvcPath" value='<%= templatePath + "view_article.jsp" %>' />
				<portlet:param name="redirect" value="<%= (kbArticle.getResourcePrimKey() == resourcePrimKey) ? homeURL : currentURL %>" />
				<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
				<portlet:param name="status" value="<%= String.valueOf(status) %>" />
			</liferay-portlet:actionURL>

			<liferay-ui:icon-delete
				label="<%= true %>"
				url="<%= deleteURL %>"
			/>
		</c:if>
	</c:if>
</div>