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

<%@ include file="/portlet/init.jsp" %>

<%
String portletNamespace = PortalUtil.getPortletNamespace(ProductNavigationSimulationPortletKeys.PRODUCT_NAVIGATION_SIMULATION);

PortletURL simulationPanelURL = PortletURLFactoryUtil.create(request, ProductNavigationSimulationPortletKeys.PRODUCT_NAVIGATION_SIMULATION, PortletRequest.RENDER_PHASE);

simulationPanelURL.setWindowState(LiferayWindowState.EXCLUSIVE);
%>

<li class="control-menu-nav-item hidden-xs simulation-icon">
	<a class="control-menu-icon lfr-portal-tooltip product-menu-toggle sidenav-toggler" data-content="body" data-open-class="lfr-has-simulation-panel open-admin-panel" data-qa-id="simulation" data-target="#<%= portletNamespace %>simulationPanelId" data-title="<%= HtmlUtil.escape(LanguageUtil.get(request, "simulation")) %>" data-toggle="sidenav" data-type="fixed-push" data-type-mobile="fixed" data-url="<%= simulationPanelURL.toString() %>" href="javascript:;" id="<%= portletNamespace %>simulationToggleId">
		<aui:icon image="simulation-menu-closed" markupView="lexicon" />
	</a>
</li>