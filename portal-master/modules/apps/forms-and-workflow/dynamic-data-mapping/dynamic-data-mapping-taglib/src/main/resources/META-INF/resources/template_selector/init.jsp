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

@generated
--%>

<%@ include file="/init.jsp" %>

<%
java.lang.String className = GetterUtil.getString((java.lang.String)request.getAttribute("liferay-ddm:template-selector:className"));
java.lang.String defaultDisplayStyle = GetterUtil.getString((java.lang.String)request.getAttribute("liferay-ddm:template-selector:defaultDisplayStyle"), com.liferay.portal.kernel.util.StringPool.BLANK);
java.lang.String displayStyle = GetterUtil.getString((java.lang.String)request.getAttribute("liferay-ddm:template-selector:displayStyle"));
long displayStyleGroupId = GetterUtil.getLong(String.valueOf(request.getAttribute("liferay-ddm:template-selector:displayStyleGroupId")));
java.util.List<java.lang.String> displayStyles = (java.util.List<java.lang.String>)request.getAttribute("liferay-ddm:template-selector:displayStyles");
java.lang.String icon = GetterUtil.getString((java.lang.String)request.getAttribute("liferay-ddm:template-selector:icon"), "icon-cog");
java.lang.String label = GetterUtil.getString((java.lang.String)request.getAttribute("liferay-ddm:template-selector:label"), "display-template");
java.lang.String refreshURL = GetterUtil.getString((java.lang.String)request.getAttribute("liferay-ddm:template-selector:refreshURL"));
boolean showEmptyOption = GetterUtil.getBoolean(String.valueOf(request.getAttribute("liferay-ddm:template-selector:showEmptyOption")));
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("liferay-ddm:template-selector:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("liferay-ddm:template-selector:scopedAttributes");
%>

<%@ include file="/template_selector/init-ext.jspf" %>