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

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<aui:fieldset>
				<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) || windowState.equals(LiferayWindowState.POP_UP) %>" cssClass="lfr-input-text-container" label="url" name="preferences--initUrl--" value="<%= initUrl %>" />

				<aui:input cssClass="lfr-input-text-container" label='<%= LanguageUtil.get(request, "scope") + " (" + LanguageUtil.get(request, "regex") + ")" %>' name="preferences--scope--" value="<%= scope %>" />

				<aui:input cssClass="lfr-input-text-container" name="preferences--proxyHost--" value="<%= proxyHost %>" />

				<aui:input cssClass="lfr-input-text-container" name="preferences--proxyPort--" value="<%= proxyPort %>" />

				<aui:select name="preferences--proxyAuthentication--" value="<%= proxyAuthentication %>">
					<aui:option label="none" />
					<aui:option label="basic" />
					<aui:option label="ntlm" />
				</aui:select>

				<aui:input cssClass="lfr-input-text-container" name="preferences--proxyAuthenticationUsername--" value="<%= proxyAuthenticationUsername %>" />

				<aui:input cssClass="lfr-input-text-container" name="preferences--proxyAuthenticationPassword--" value="<%= proxyAuthenticationPassword %>" />

				<aui:input cssClass="lfr-input-text-container" name="preferences--proxyAuthenticationHost--" value="<%= proxyAuthenticationHost %>" />

				<aui:input cssClass="lfr-input-text-container" name="preferences--proxyAuthenticationDomain--" value="<%= proxyAuthenticationDomain %>" />

				<aui:input cssClass="lfr-textarea-container" name="preferences--stylesheet--" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" type="textarea" value="<%= stylesheet %>" wrap="soft" />
			</aui:fieldset>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>