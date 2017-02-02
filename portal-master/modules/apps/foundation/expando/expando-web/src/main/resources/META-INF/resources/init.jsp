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
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.expando.kernel.exception.ColumnNameException" %><%@
page import="com.liferay.expando.kernel.exception.ColumnTypeException" %><%@
page import="com.liferay.expando.kernel.exception.DuplicateColumnNameException" %><%@
page import="com.liferay.expando.kernel.exception.NoSuchColumnException" %><%@
page import="com.liferay.expando.kernel.exception.ValueDataException" %><%@
page import="com.liferay.expando.kernel.model.CustomAttributesDisplay" %><%@
page import="com.liferay.expando.kernel.model.ExpandoBridge" %><%@
page import="com.liferay.expando.kernel.model.ExpandoColumn" %><%@
page import="com.liferay.expando.kernel.model.ExpandoColumnConstants" %><%@
page import="com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil" %><%@
page import="com.liferay.expando.kernel.service.ExpandoColumnServiceUtil" %><%@
page import="com.liferay.expando.kernel.service.permission.ExpandoColumnPermissionUtil" %><%@
page import="com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil" %><%@
page import="com.liferay.expando.web.internal.constants.ExpandoPortletKeys" %><%@
page import="com.liferay.expando.web.internal.search.CustomFieldChecker" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLUtil" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil" %><%@
page import="com.liferay.portal.kernel.service.PortletLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.permission.PortletPermissionUtil" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.LocalizationUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.TextFormatter" %><%@
page import="com.liferay.portal.kernel.util.UnicodeProperties" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portlet.expando.util.comparator.CustomAttributesDisplayComparator" %><%@
page import="com.liferay.taglib.search.ResultRow" %>

<%@ page import="java.io.Serializable" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.Collections" %><%@
page import="java.util.Date" %><%@
page import="java.util.List" %><%@
page import="java.util.Locale" %><%@
page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%@ include file="/init-ext.jsp" %>