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
JournalArticle article = journalDisplayContext.getArticle();

long groupId = BeanParamUtil.getLong(article, request, "groupId", scopeGroupId);

long classNameId = ParamUtil.getLong(request, "classNameId");

String newArticleId = ParamUtil.getString(request, "newArticleId");

DDMStructure ddmStructure = (DDMStructure)request.getAttribute("edit_article.jsp-structure");

DDMTemplate ddmTemplate = (DDMTemplate)request.getAttribute("edit_article.jsp-template");

String defaultLanguageId = (String)request.getAttribute("edit_article.jsp-defaultLanguageId");

boolean changeStructure = GetterUtil.getBoolean(request.getAttribute("edit_article.jsp-changeStructure"));
%>

<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="content" />

<aui:model-context bean="<%= article %>" defaultLanguageId="<%= defaultLanguageId %>" model="<%= JournalArticle.class %>" />

<liferay-ui:error exception="<%= ArticleContentException.class %>" message="please-enter-valid-content" />
<liferay-ui:error exception="<%= ArticleIdException.class %>" message="please-enter-a-valid-id" />
<liferay-ui:error exception="<%= ArticleTitleException.class %>" message="please-enter-a-valid-name" />
<liferay-ui:error exception="<%= ArticleVersionException.class %>" message="another-user-has-made-changes-since-you-started-editing-please-copy-your-changes-and-try-again" />
<liferay-ui:error exception="<%= DuplicateArticleIdException.class %>" message="please-enter-a-unique-id" />
<liferay-ui:error exception="<%= InvalidDDMStructureException.class %>" message="the-structure-you-selected-is-not-valid-for-this-folder" />

<liferay-ui:error exception="<%= LocaleException.class %>">

	<%
	LocaleException le = (LocaleException)errorException;
	%>

	<c:if test="<%= le.getType() == LocaleException.TYPE_CONTENT %>">
		<liferay-ui:message arguments="<%= new String[] {StringUtil.merge(le.getSourceAvailableLocales(), StringPool.COMMA_AND_SPACE), StringUtil.merge(le.getTargetAvailableLocales(), StringPool.COMMA_AND_SPACE)} %>" key="the-default-language-x-does-not-match-the-portal's-available-languages-x" />
	</c:if>
</liferay-ui:error>

<liferay-ui:error exception="<%= NoSuchFileEntryException.class %>" message="the-content-references-a-missing-file-entry" />
<liferay-ui:error exception="<%= NoSuchImageException.class %>" message="please-select-an-existing-small-image" />

<liferay-ui:error exception="<%= NoSuchLayoutException.class %>">

	<%
	NoSuchLayoutException nsle = (NoSuchLayoutException)errorException;

	String message = nsle.getMessage();
	%>

	<c:when test="<%= message.equals(JournalArticleConstants.DISPLAY_PAGE) %>">
		<liferay-ui:message key="please-select-an-existing-display-page" />
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="the-content-references-a-missing-page" />
	</c:otherwise>
</liferay-ui:error>

<liferay-ui:error exception="<%= NoSuchStructureException.class %>" message="please-select-an-existing-structure" />
<liferay-ui:error exception="<%= NoSuchTemplateException.class %>" message="please-select-an-existing-template" />
<liferay-ui:error exception="<%= StorageFieldRequiredException.class %>" message="please-fill-out-all-required-fields" />

<aui:fieldset>
	<aui:input autoFocus="<%= true %>" ignoreRequestValue="<%= changeStructure %>" name="title" wrapperCssClass="article-content-title">
		<c:if test="<%= classNameId == JournalArticleConstants.CLASSNAME_ID_DEFAULT %>">
			<aui:validator name="required" />
		</c:if>
	</aui:input>

	<c:if test="<%= (article == null) || article.isNew() %>">
		<c:choose>
			<c:when test="<%= journalWebConfiguration.journalArticleForceAutogenerateId() || (classNameId != JournalArticleConstants.CLASSNAME_ID_DEFAULT) %>">
				<aui:input name="newArticleId" type="hidden" />
				<aui:input name="autoArticleId" type="hidden" value="<%= true %>" />
			</c:when>
			<c:otherwise>
				<aui:input field="articleId" fieldParam="newArticleId" label="id" name="newArticleId" value="<%= newArticleId %>" />

				<aui:input label="autogenerate-id" name="autoArticleId" type="checkbox" />
			</c:otherwise>
		</c:choose>
	</c:if>

	<aui:input ignoreRequestValue="<%= changeStructure %>" label="summary" name="description" />

	<liferay-ddm:html
		checkRequired="<%= classNameId == JournalArticleConstants.CLASSNAME_ID_DEFAULT %>"
		classNameId="<%= PortalUtil.getClassNameId(DDMStructure.class) %>"
		classPK="<%= ddmStructure.getStructureId() %>"
		ddmFormValues="<%= journalDisplayContext.getDDMFormValues(ddmStructure) %>"
		ignoreRequestValue="<%= changeStructure %>"
		requestedLocale="<%= LocaleUtil.fromLanguageId(defaultLanguageId) %>"
	/>

	<aui:input label="searchable" name="indexable" type="toggle-switch" value="<%= (article != null) ? article.isIndexable() : true %>" />
</aui:fieldset>

<liferay-portlet:renderURL portletName="<%= PortletProviderUtil.getPortletId(DDMStructure.class.getName(), PortletProvider.Action.EDIT) %>" var="editStructureURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcPath" value="/edit_structure.jsp" />
	<portlet:param name="navigationStartsOn" value="<%= DDMNavigationHelper.EDIT_STRUCTURE %>" />
	<portlet:param name="closeRedirect" value="<%= currentURL %>" />
	<portlet:param name="showBackURL" value="<%= Boolean.FALSE.toString() %>" />
	<portlet:param name="refererPortletName" value="<%= JournalPortletKeys.JOURNAL %>" />
	<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
	<portlet:param name="classNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(DDMStructure.class)) %>" />
	<portlet:param name="classPK" value="<%= String.valueOf(ddmStructure.getStructureId()) %>" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL portletName="<%= PortletProviderUtil.getPortletId(DDMTemplate.class.getName(), PortletProvider.Action.EDIT) %>" var="editTemplateURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcPath" value="/edit_template.jsp" />
	<portlet:param name="navigationStartsOn" value="<%= DDMNavigationHelper.EDIT_TEMPLATE %>" />
	<portlet:param name="closeRedirect" value="<%= currentURL %>" />
	<portlet:param name="showBackURL" value="<%= Boolean.FALSE.toString() %>" />
	<portlet:param name="refererPortletName" value="<%= JournalPortletKeys.JOURNAL %>" />
	<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
	<portlet:param name="classNameId" value="<%= String.valueOf(classNameId) %>" />
	<portlet:param name="templateId" value="<%= (ddmTemplate != null) ? String.valueOf(ddmTemplate.getTemplateId()) : StringPool.BLANK %>" />
	<portlet:param name="showCacheableInput" value="<%= Boolean.TRUE.toString() %>" />
</liferay-portlet:renderURL>

<aui:script use="liferay-journal-content">
	var journalContent = new Liferay.Portlet.JournalContent(
		{
			'ddm.basePortletURL': '<%= PortletURLFactoryUtil.create(request, PortletProviderUtil.getPortletId(DDMStructure.class.getName(), PortletProvider.Action.VIEW), PortletRequest.RENDER_PHASE) %>',
			'ddm.classNameId': '<%= PortalUtil.getClassNameId(DDMStructure.class) %>',
			'ddm.classPK': <%= ddmStructure.getPrimaryKey() %>,
			'ddm.groupId': <%= groupId %>,
			'ddm.refererPortletName': '<%= JournalPortletKeys.JOURNAL + ".selectStructure" %>',
			'ddm.resourceClassNameId': '<%= ddmStructure.getClassNameId() %>',
			'ddm.templateId': <%= (ddmTemplate != null) ? ddmTemplate.getTemplateId() : 0 %>,
			descriptionInputLocalized: Liferay.component('<portlet:namespace />description'),
			editStructure: '#<portlet:namespace />editDDMStructure',
			editTemplate: '#<portlet:namespace />editDDMTemplate',
			namespace: '<portlet:namespace />',
			selectStructure: '#<portlet:namespace />selectStructure',
			selectTemplate: '#<portlet:namespace />selectTemplate',
			'strings.draft': '<liferay-ui:message key="draft" />',
			'strings.editStructure': '<liferay-ui:message key="editing-the-current-structure-deletes-all-unsaved-content" />',
			'strings.editTemplate': '<liferay-ui:message key="editing-the-current-template-deletes-all-unsaved-content" />',
			titleInputLocalized: Liferay.component('<portlet:namespace />title'),
			translationManager: Liferay.component('<portlet:namespace />translationManager'),
			'urls.editStructure': '<%= editStructureURL %>',
			'urls.editTemplate': '<%= editTemplateURL %>'
		}
	);

	Liferay.Util.disableToggleBoxes('<portlet:namespace />autoArticleId', '<portlet:namespace />newArticleId', true);
</aui:script>