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

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.util.SessionClicks" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.product.navigation.product.menu.web.constants.ProductNavigationProductMenuPortletKeys" %>
<%@ page import="com.liferay.product.navigation.product.menu.web.constants.ProductNavigationProductMenuWebKeys" %>

<%@ page import="java.util.Objects" %>

<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.RenderRequest" %>

<liferay-theme:defineObjects />

<%
String portletNamespace = PortalUtil.getPortletNamespace(ProductNavigationProductMenuPortletKeys.PRODUCT_NAVIGATION_PRODUCT_MENU);

String productMenuState = SessionClicks.get(request, ProductNavigationProductMenuWebKeys.PRODUCT_NAVIGATION_PRODUCT_MENU_STATE, "closed");

PortletURL portletURL = PortletURLFactoryUtil.create(request, ProductNavigationProductMenuPortletKeys.PRODUCT_NAVIGATION_PRODUCT_MENU, RenderRequest.RENDER_PHASE);

portletURL.setParameter("mvcPath", "/portlet/product_menu.jsp");
portletURL.setParameter("selPpid", portletDisplay.getId());
portletURL.setWindowState(LiferayWindowState.EXCLUSIVE);
%>

<li class="control-menu-nav-item <%= Objects.equals(productMenuState, "open") ? "active" : StringPool.BLANK %>">
	<a class="control-menu-icon lfr-portal-tooltip product-menu-toggle sidenav-toggler" data-content="body" data-qa-id="productMenu" data-target="#<%= portletNamespace %>sidenavSliderId" data-title="<%= HtmlUtil.escape(LanguageUtil.get(request, "menu")) %>" data-toggle="sidenav" data-type="fixed-push" data-type-mobile="fixed" <%= Objects.equals(productMenuState, "open") ? StringPool.BLANK : "data-url='" + portletURL.toString() + "'" %> href="javascript:;" id="<%= portletNamespace %>sidenavToggleId">
		<div class="toast-animation">
			<div class="pm"></div>

			<div class="cn"></div>
		</div>
	</a>
</li>