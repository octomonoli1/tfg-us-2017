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

<%@ include file="/html/taglib/init.jsp" %>

<%
boolean addDisplayPageParameter = GetterUtil.getBoolean(request.getAttribute("liferay-ui:asset-add-button:addDisplayPageParameter"));
long[] allAssetCategoryIds = GetterUtil.getLongValues(request.getAttribute("liferay-ui:asset-add-button:allAssetCategoryIds"), null);
String[] allAssetTagNames = GetterUtil.getStringValues(request.getAttribute("liferay-ui:asset-add-button:allAssetTagNames"), null);
long[] classNameIds = GetterUtil.getLongValues(request.getAttribute("liferay-ui:asset-add-button:classNameIds"));
long[] classTypeIds = GetterUtil.getLongValues(request.getAttribute("liferay-ui:asset-add-button:classTypeIds"));
long[] groupIds = GetterUtil.getLongValues(request.getAttribute("liferay-ui:asset-add-button:groupIds"));
String redirect = (String)request.getAttribute("liferay-ui:asset-add-button:redirect");
boolean useDialog = GetterUtil.getBoolean(request.getAttribute("liferay-ui:asset-add-button:useDialog"), true);

boolean hasAddPortletURLs = false;

for (long groupId : groupIds) {
	Map<String, PortletURL> addPortletURLs = AssetUtil.getAddPortletURLs((LiferayPortletRequest)portletRequest, (LiferayPortletResponse)portletResponse, groupId, classNameIds, classTypeIds, allAssetCategoryIds, allAssetTagNames, redirect);

	if ((addPortletURLs != null) && !addPortletURLs.isEmpty()) {
		hasAddPortletURLs = true;
	}
%>

	<c:if test="<%= hasAddPortletURLs %>">
		<aui:nav>
			<c:choose>
				<c:when test="<%= addPortletURLs.size() == 1 %>">

					<%
					Set<Map.Entry<String, PortletURL>> addPortletURLsSet = addPortletURLs.entrySet();

					Iterator<Map.Entry<String, PortletURL>> iterator = addPortletURLsSet.iterator();

					Map.Entry<String, PortletURL> entry = iterator.next();

					AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(AssetUtil.getClassName(entry.getKey()));

					String message = AssetUtil.getClassNameMessage(entry.getKey(), locale);

					long curGroupId = groupId;

					Group group = GroupLocalServiceUtil.fetchGroup(groupId);

					if (!group.isStagedPortlet(assetRendererFactory.getPortletId()) && !group.isStagedRemotely()) {
						curGroupId = group.getLiveGroupId();
					}
					%>

					<aui:nav-item
						href="<%= _getURL(curGroupId, plid, entry.getValue(), assetRendererFactory.getPortletId(), message, addDisplayPageParameter, layout, pageContext, portletResponse, useDialog) %>"
						label='<%= LanguageUtil.format(request, (groupIds.length == 1) ? "add-x" : "add-x-in-x", new Object[] {HtmlUtil.escape(message), HtmlUtil.escape((GroupLocalServiceUtil.getGroup(groupId)).getDescriptiveName(locale))}, false) %>'
					/>
				</c:when>
				<c:otherwise>
					<aui:nav-item
						dropdown="<%= true %>"
						label='<%= LanguageUtil.format(request, (groupIds.length == 1) ? "add-new" : "add-new-in-x", HtmlUtil.escape((GroupLocalServiceUtil.getGroup(groupId)).getDescriptiveName(locale)), false) %>'
					>

						<%
						for (Map.Entry<String, PortletURL> entry : addPortletURLs.entrySet()) {
							AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(AssetUtil.getClassName(entry.getKey()));

							String message = AssetUtil.getClassNameMessage(entry.getKey(), locale);

							long curGroupId = groupId;

							Group group = GroupLocalServiceUtil.fetchGroup(groupId);

							if (!group.isStagedPortlet(assetRendererFactory.getPortletId()) && !group.isStagedRemotely()) {
								curGroupId = group.getLiveGroupId();
							}
						%>

							<aui:nav-item
								href="<%= _getURL(curGroupId, plid, entry.getValue(), assetRendererFactory.getPortletId(), message, addDisplayPageParameter, layout, pageContext, portletResponse, useDialog) %>"
								label="<%= HtmlUtil.escape(message) %>"
							/>

						<%
						}
						%>

					</aui:nav-item>
				</c:otherwise>
			</c:choose>
		</aui:nav>
	</c:if>

<%
}

request.setAttribute("liferay-ui:asset-add-button:hasAddPortletURLs", hasAddPortletURLs);
%>

<%!
private String _getURL(long groupId, long plid, PortletURL addPortletURL, String portletId, String message, boolean addDisplayPageParameter, Layout layout, PageContext pageContext, PortletResponse portletResponse, boolean useDialog) {
	String addPortletURLString = AssetUtil.getAddURLPopUp(groupId, plid, addPortletURL, portletId, addDisplayPageParameter, layout);

	if (useDialog) {
		return "javascript:Liferay.Util.openWindow({dialog: {destroyOnHide: true}, dialogIframe: {bodyCssClass: 'dialog-with-footer'}, id: '" + portletResponse.getNamespace() + "editAsset', title: '" + HtmlUtil.escapeJS(LanguageUtil.format((HttpServletRequest) pageContext.getRequest(), "new-x", HtmlUtil.escape(message), false)) + "', uri: '" + HtmlUtil.escapeJS(addPortletURLString) + "'});";
	}

	return addPortletURLString;
}
%>