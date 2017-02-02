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
String redirect = ParamUtil.getString(request, "redirect");
String returnToFullPageURL = ParamUtil.getString(request, "returnToFullPageURL");

Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletResource);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/edit_sharing.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("returnToFullPageURL", returnToFullPageURL);
portletURL.setParameter("portletResource", portletResource);

String widgetURL = PortalUtil.getWidgetURL(portlet, themeDisplay);
%>

<portlet:actionURL name="editSharing" var="editSharingURL">
	<portlet:param name="mvcPath" value="/edit_sharing.jsp" />
	<portlet:param name="portletConfiguration" value="<%= Boolean.TRUE.toString() %>" />
</portlet:actionURL>

<div class="portlet-configuration-edit-sharing">
	<aui:form action="<%= editSharingURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.SAVE %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="returnToFullPageURL" type="hidden" value="<%= returnToFullPageURL %>" />
		<aui:input name="portletResource" type="hidden" value="<%= portletResource %>" />

		<div class="portlet-configuration-body-content">
			<liferay-util:include page="/tabs1.jsp" servletContext="<%= application %>">
				<liferay-util:param name="tabs1" value="sharing" />
			</liferay-util:include>

			<div class="container-fluid-1280">
				<aui:fieldset-group markupView="lexicon">
					<aui:fieldset collapsed="<%= false %>" collapsible="<%= true %>" label="any-website">

						<%
						boolean widgetShowAddAppLink = GetterUtil.getBoolean(portletPreferences.getValue("lfrWidgetShowAddAppLink", null), PropsValues.THEME_PORTLET_SHARING_DEFAULT);
						%>

						<div class="alert alert-info">
							<liferay-ui:message key="share-this-application-on-any-website" />
						</div>

						<liferay-util:buffer var="textAreaContent">
			<script src="<%= themeDisplay.getPortalURL() %><%= PortalWebResourcesUtil.getContextPath(PortalWebResourceConstants.RESOURCE_TYPE_JS) %>/liferay/widget.js" type="text/javascript"></script>
			<script type="text/javascript">
			Liferay.Widget({url: '<%= widgetURL %>'});
			</script></liferay-util:buffer>

						<aui:field-wrapper label="code">
							<textarea class="field form-control lfr-textarea" id="<portlet:namespace />widgetScript" onClick="this.select();" readonly="true"><%= HtmlUtil.escape(textAreaContent) %></textarea>
						</aui:field-wrapper>

						<aui:input label='<%= LanguageUtil.format(request, "allow-users-to-add-x-to-any-website", HtmlUtil.escape(portletDisplay.getTitle()), false) %>' name="widgetShowAddAppLink" type="toggle-switch" value="<%= widgetShowAddAppLink %>" />
					</aui:fieldset>

					<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="facebook">

						<%
						String facebookAPIKey = GetterUtil.getString(portletPreferences.getValue("lfrFacebookApiKey", null));
						String facebookCanvasPageURL = GetterUtil.getString(portletPreferences.getValue("lfrFacebookCanvasPageUrl", null));
						boolean facebookShowAddAppLink = GetterUtil.getBoolean(portletPreferences.getValue("lfrFacebookShowAddAppLink", null), true);

						String callbackURL = widgetURL;

						if (portlet.getFacebookIntegration().equals(PortletConstants.FACEBOOK_INTEGRATION_FBML)) {
							callbackURL = PortalUtil.getFacebookURL(portlet, facebookCanvasPageURL, themeDisplay);
						}
						%>

						<div class="alert alert-info">
							<aui:a href="http://developers.facebook.com" target="_blank"><liferay-ui:message key="get-the-api-key-and-canvas-page-url-from-facebook" /></aui:a>
						</div>

						<aui:input cssClass="lfr-input-text-container" label="api-key" name="facebookAPIKey" value="<%= HtmlUtil.toInputSafe(facebookAPIKey) %>" />

						<aui:input cssClass="flexible lfr-input-text-container" label="canvas-page-url" name="facebookCanvasPageURL" prefix="http://apps.facebook.com/" suffix="/" value="<%= HtmlUtil.toInputSafe(facebookCanvasPageURL) %>" />

						<c:if test="<%= Validator.isNotNull(facebookCanvasPageURL) %>">
							<br />

							<div class="alert alert-info">
								<liferay-ui:message key="copy-the-callback-url-and-specify-it-in-facebook" />

								<c:choose>
									<c:when test="<%= portlet.getFacebookIntegration().equals(PortletConstants.FACEBOOK_INTEGRATION_FBML) %>">
										<liferay-ui:message key="this-application-is-exposed-to-facebook-via-fbml" />
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="this-application-is-exposed-to-facebook-via-an-iframe" />
									</c:otherwise>
								</c:choose>
							</div>

							<aui:input name="callbackURL" type="resource" value="<%= callbackURL %>" />

							<aui:input label='<%= LanguageUtil.format(request, "allow-users-to-add-x-to-facebook", HtmlUtil.escape(portletDisplay.getTitle()), false) %>' name="facebookShowAddAppLink" type="toggle-switch" value="<%= facebookShowAddAppLink %>" />
						</c:if>
					</aui:fieldset>

					<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="opensocial-gadget">

						<%
						boolean iGoogleShowAddAppLink = PrefsParamUtil.getBoolean(portletPreferences, request, "lfrIgoogleShowAddAppLink");
						%>

						<div class="alert alert-info">
							<liferay-ui:message key="use-the-opensocial-gadget-url-to-create-an-opensocial-gadget" />
						</div>

						<aui:input name="opensocialGadgetURL" type="resource" value="<%= PortalUtil.getGoogleGadgetURL(portlet, themeDisplay) %>" />

						<aui:input label='<%= LanguageUtil.format(request, "allow-users-to-add-x-to-an-open-social-platform", HtmlUtil.escape(portletDisplay.getTitle()), false) %>' name="iGoogleShowAddAppLink" type="toggle-switch" value="<%= iGoogleShowAddAppLink %>" />
					</aui:fieldset>

					<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="netvibes">

						<%
						boolean netvibesShowAddAppLink = PrefsParamUtil.getBoolean(portletPreferences, request, "lfrNetvibesShowAddAppLink");
						%>

						<div class="alert alert-info">
							<liferay-ui:message key="use-the-netvibes-widget-url-to-create-a-netvibes-widget" />
						</div>

						<aui:input name="netvibesWidgetURL" type="resource" value="<%= PortalUtil.getNetvibesURL(portlet, themeDisplay) %>" />

						<aui:input label='<%= LanguageUtil.format(request, "allow-users-to-add-x-to-netvibes-pages", HtmlUtil.escape(portletDisplay.getTitle()), false) %>' name="netvibesShowAddAppLink" type="toggle-switch" value="<%= netvibesShowAddAppLink %>" />
					</aui:fieldset>
				</aui:fieldset-group>
			</div>
		</div>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" />
		</aui:button-row>
	</aui:form>
</div>