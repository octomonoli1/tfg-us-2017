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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/trash" prefix="liferay-trash" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil" %><%@
page import="com.liferay.asset.kernel.model.AssetEntry" %><%@
page import="com.liferay.asset.kernel.model.AssetRenderer" %><%@
page import="com.liferay.asset.kernel.model.AssetRendererFactory" %><%@
page import="com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil" %><%@
page import="com.liferay.document.library.kernel.exception.DuplicateFileEntryException" %><%@
page import="com.liferay.document.library.kernel.exception.FileSizeException" %><%@
page import="com.liferay.document.library.kernel.exception.NoSuchFileEntryException" %><%@
page import="com.liferay.document.library.kernel.model.DLFileEntry" %><%@
page import="com.liferay.dynamic.data.mapping.exception.NoSuchStructureException" %><%@
page import="com.liferay.dynamic.data.mapping.exception.NoSuchTemplateException" %><%@
page import="com.liferay.dynamic.data.mapping.exception.StorageFieldRequiredException" %><%@
page import="com.liferay.dynamic.data.mapping.model.DDMForm" %><%@
page import="com.liferay.dynamic.data.mapping.model.DDMFormField" %><%@
page import="com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions" %><%@
page import="com.liferay.dynamic.data.mapping.model.DDMStructure" %><%@
page import="com.liferay.dynamic.data.mapping.model.DDMTemplate" %><%@
page import="com.liferay.dynamic.data.mapping.model.LocalizedValue" %><%@
page import="com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil" %><%@
page import="com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil" %><%@
page import="com.liferay.dynamic.data.mapping.service.DDMTemplateServiceUtil" %><%@
page import="com.liferay.dynamic.data.mapping.service.permission.DDMStructurePermission" %><%@
page import="com.liferay.dynamic.data.mapping.service.permission.DDMTemplatePermission" %><%@
page import="com.liferay.dynamic.data.mapping.storage.DDMFormValues" %><%@
page import="com.liferay.dynamic.data.mapping.util.DDMNavigationHelper" %><%@
page import="com.liferay.item.selector.ItemSelector" %><%@
page import="com.liferay.item.selector.ItemSelectorReturnType" %><%@
page import="com.liferay.item.selector.criteria.UUIDItemSelectorReturnType" %><%@
page import="com.liferay.journal.configuration.JournalGroupServiceConfiguration" %><%@
page import="com.liferay.journal.configuration.JournalServiceConfigurationValues" %><%@
page import="com.liferay.journal.constants.JournalConstants" %><%@
page import="com.liferay.journal.constants.JournalPortletKeys" %><%@
page import="com.liferay.journal.constants.JournalWebKeys" %><%@
page import="com.liferay.journal.exception.ArticleContentException" %><%@
page import="com.liferay.journal.exception.ArticleContentSizeException" %><%@
page import="com.liferay.journal.exception.ArticleDisplayDateException" %><%@
page import="com.liferay.journal.exception.ArticleExpirationDateException" %><%@
page import="com.liferay.journal.exception.ArticleIdException" %><%@
page import="com.liferay.journal.exception.ArticleSmallImageNameException" %><%@
page import="com.liferay.journal.exception.ArticleSmallImageSizeException" %><%@
page import="com.liferay.journal.exception.ArticleTitleException" %><%@
page import="com.liferay.journal.exception.ArticleVersionException" %><%@
page import="com.liferay.journal.exception.DuplicateArticleIdException" %><%@
page import="com.liferay.journal.exception.DuplicateFeedIdException" %><%@
page import="com.liferay.journal.exception.DuplicateFolderNameException" %><%@
page import="com.liferay.journal.exception.FeedContentFieldException" %><%@
page import="com.liferay.journal.exception.FeedIdException" %><%@
page import="com.liferay.journal.exception.FeedNameException" %><%@
page import="com.liferay.journal.exception.FeedTargetLayoutFriendlyUrlException" %><%@
page import="com.liferay.journal.exception.FeedTargetPortletIdException" %><%@
page import="com.liferay.journal.exception.FolderNameException" %><%@
page import="com.liferay.journal.exception.InvalidDDMStructureException" %><%@
page import="com.liferay.journal.exception.NoSuchArticleException" %><%@
page import="com.liferay.journal.exception.NoSuchFolderException" %><%@
page import="com.liferay.journal.model.JournalArticle" %><%@
page import="com.liferay.journal.model.JournalArticleConstants" %><%@
page import="com.liferay.journal.model.JournalArticleDisplay" %><%@
page import="com.liferay.journal.model.JournalFeed" %><%@
page import="com.liferay.journal.model.JournalFeedConstants" %><%@
page import="com.liferay.journal.model.JournalFolder" %><%@
page import="com.liferay.journal.model.JournalFolderConstants" %><%@
page import="com.liferay.journal.service.JournalArticleLocalServiceUtil" %><%@
page import="com.liferay.journal.service.JournalArticleServiceUtil" %><%@
page import="com.liferay.journal.service.JournalFeedLocalServiceUtil" %><%@
page import="com.liferay.journal.service.JournalFolderLocalServiceUtil" %><%@
page import="com.liferay.journal.service.JournalFolderServiceUtil" %><%@
page import="com.liferay.journal.service.permission.JournalArticlePermission" %><%@
page import="com.liferay.journal.service.permission.JournalFeedPermission" %><%@
page import="com.liferay.journal.service.permission.JournalFolderPermission" %><%@
page import="com.liferay.journal.service.permission.JournalPermission" %><%@
page import="com.liferay.journal.util.JournalContent" %><%@
page import="com.liferay.journal.util.comparator.ArticleVersionComparator" %><%@
page import="com.liferay.journal.util.impl.JournalUtil" %><%@
page import="com.liferay.journal.web.asset.JournalArticleAssetRenderer" %><%@
page import="com.liferay.journal.web.configuration.JournalWebConfiguration" %><%@
page import="com.liferay.journal.web.internal.dao.search.JournalResultRowSplitter" %><%@
page import="com.liferay.journal.web.internal.display.context.JournalDisplayContext" %><%@
page import="com.liferay.journal.web.internal.display.context.JournalMoveEntriesDisplayContext" %><%@
page import="com.liferay.journal.web.internal.display.context.util.JournalWebRequestHelper" %><%@
page import="com.liferay.journal.web.internal.portlet.JournalPortlet" %><%@
page import="com.liferay.journal.web.internal.portlet.action.ActionUtil" %><%@
page import="com.liferay.journal.web.internal.search.ArticleSearch" %><%@
page import="com.liferay.journal.web.internal.search.FeedSearch" %><%@
page import="com.liferay.journal.web.internal.search.FeedSearchTerms" %><%@
page import="com.liferay.journal.web.util.JournalPortletUtil" %><%@
page import="com.liferay.layout.item.selector.criterion.LayoutItemSelectorCriterion" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.exception.LocaleException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchImageException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchLayoutException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchWorkflowDefinitionLinkException" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.*" %><%@
page import="com.liferay.portal.kernel.model.impl.*" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProvider" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProviderUtil" %><%@
page import="com.liferay.portal.kernel.portlet.PortletRequestModel" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLUtil" %><%@
page import="com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.service.*" %><%@
page import="com.liferay.portal.kernel.servlet.SessionMessages" %><%@
page import="com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants" %><%@
page import="com.liferay.portal.kernel.upload.LiferayFileItemException" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.LocalizationUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsKeys" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.TextFormatter" %><%@
page import="com.liferay.portal.kernel.util.UnicodeFormatter" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.webdav.WebDAVUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowDefinition" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil" %><%@
page import="com.liferay.portal.model.impl.*" %><%@
page import="com.liferay.portal.service.*" %><%@
page import="com.liferay.portal.upload.LiferayFileItem" %><%@
page import="com.liferay.portal.util.PrefsPropsUtil" %><%@
page import="com.liferay.portlet.asset.util.AssetUtil" %><%@
page import="com.liferay.rss.util.RSSUtil" %><%@
page import="com.liferay.taglib.search.ResultRow" %><%@
page import="com.liferay.taglib.util.CustomAttributesUtil" %><%@
page import="com.liferay.trash.kernel.util.TrashUtil" %>

<%@ page import="java.text.Format" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Arrays" %><%@
page import="java.util.Collections" %><%@
page import="java.util.Date" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Locale" %><%@
page import="java.util.Map" %><%@
page import="java.util.Objects" %><%@
page import="java.util.Set" %>

<%@ page import="javax.portlet.ActionRequest" %><%@
page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.ResourceURL" %><%@
page import="javax.portlet.WindowState" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
JournalWebConfiguration journalWebConfiguration = (JournalWebConfiguration)request.getAttribute(JournalWebConfiguration.class.getName());

JournalDisplayContext journalDisplayContext = new JournalDisplayContext(request, liferayPortletRequest, liferayPortletResponse, portletPreferences);

JournalWebRequestHelper journalWebRequestHelper = new JournalWebRequestHelper(request);

JournalGroupServiceConfiguration journalGroupServiceConfiguration = journalWebRequestHelper.getJournalGroupServiceConfiguration();

Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
%>

<%@ include file="/init-ext.jsp" %>