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
String htmlAttributes =
	"alt=" + iFramePortletInstanceConfiguration.alt() + "\n" +
	"border=" + iFramePortletInstanceConfiguration.border() + "\n" +
	"bordercolor=" + iFramePortletInstanceConfiguration.bordercolor() + "\n" +
	"frameborder=" + iFramePortletInstanceConfiguration.frameborder() + "\n" +
	"hspace=" + iFramePortletInstanceConfiguration.hspace() + "\n" +
	"longdesc=" + iFramePortletInstanceConfiguration.longdesc() + "\n" +
	"scrolling=" + iFramePortletInstanceConfiguration.scrolling() + "\n" +
	"title=" + iFramePortletInstanceConfiguration.title() + "\n" +
	"vspace=" + iFramePortletInstanceConfiguration.vspace() + "\n";
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) || windowState.equals(LiferayWindowState.POP_UP) %>" cssClass="lfr-input-text-container" label="source-url" name="preferences--src--" prefix="<%= iFramePortletInstanceConfiguration.relative() ? StringPool.TRIPLE_PERIOD : StringPool.BLANK %>" type="text" value="<%= iFramePortletInstanceConfiguration.src() %>" />

					<aui:input label="relative-to-context-path" name="preferences--relative--" type="toggle-switch" value="<%= iFramePortletInstanceConfiguration.relative() %>" />
				</aui:fieldset>

				<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="authenticate">
					<aui:input label="authenticate" name="preferences--auth--" type="toggle-switch" value="<%= iFramePortletInstanceConfiguration.auth() %>" />

					<div id="<portlet:namespace />authenticationOptions">
						<div class="alert alert-info" id="<portlet:namespace />currentLoginMsg">
							<c:choose>
								<c:when test="<%= IFrameUtil.isPasswordTokenEnabled(renderRequest) %>">
									<liferay-ui:message key="you-may-use-the-tokens-email-address-screen-name-userid-and-password" />
								</c:when>
								<c:otherwise>
									<liferay-ui:message key="you-may-use-the-tokens-email-address-screen-name-userid" />
								</c:otherwise>
							</c:choose>
						</div>

						<aui:select label="authentication-type" name="preferences--authType--" value="<%= iFrameDisplayContext.getAuthType() %>">
							<aui:option label="basic" />
							<aui:option label="form" />
						</aui:select>

						<div id="<portlet:namespace />formAuthOptions">
							<aui:select name="preferences--formMethod--" value="<%= iFrameDisplayContext.getFormMethod() %>">
								<aui:option label="get" />
								<aui:option label="post" />
							</aui:select>

							<aui:field-wrapper label="user-name">
								<table class="lfr-table">
									<tr>
										<td>
											<aui:input cssClass="lfr-input-text-container" label="field-name" name="preferences--userNameField--" type="text" value="<%= iFramePortletInstanceConfiguration.userNameField() %>" />
										</td>
										<td>
											<aui:input cssClass="lfr-input-text-container" label="value" name="preferences--formUserName--" type="text" value="<%= iFramePortletInstanceConfiguration.formUserName() %>" />
										</td>
									</tr>
								</table>
							</aui:field-wrapper>

							<aui:field-wrapper name="password">
								<table class="lfr-table">
									<tr>
										<td>
											<aui:input cssClass="lfr-input-text-container" label="field-name" name="preferences--passwordField--" type="text" value="<%= iFramePortletInstanceConfiguration.passwordField() %>" />
										</td>
										<td>
											<aui:input cssClass="lfr-input-text-container" label="value" name="preferences--formPassword--" type="text" value="<%= iFramePortletInstanceConfiguration.formPassword() %>" />
										</td>
									</tr>
								</table>

								<aui:input cssClass="lfr-input-text-container" name="preferences--hiddenVariables--" type="text" value="<%= iFrameDisplayContext.getHiddenVariables() %>" />
							</aui:field-wrapper>
						</div>

						<div id="<portlet:namespace />basicAuthOptions">
							<aui:input cssClass="lfr-input-text-container" label="user-name" name="preferences--basicUserName--" type="text" value="<%= iFramePortletInstanceConfiguration.basicUserName() %>" />

							<aui:input cssClass="lfr-input-text-container" label="password" name="preferences--basicPassword--" type="text" value="<%= iFramePortletInstanceConfiguration.basicPassword() %>" />
						</div>
					</div>
				</aui:fieldset>

				<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="display-settings">
					<aui:input helpMessage="resize-automatically-help" label="resize-automatically" name="preferences--resizeAutomatically--" type="toggle-switch" value="<%= iFramePortletInstanceConfiguration.resizeAutomatically() %>" />

					<div id="<portlet:namespace />displaySettings">
						<aui:input name="preferences--heightMaximized--" type="text" value="<%= iFramePortletInstanceConfiguration.heightMaximized() %>">
							<aui:validator name="digits" />
							<aui:validator name="required" />
						</aui:input>

						<aui:input name="preferences--heightNormal--" type="text" value="<%= iFramePortletInstanceConfiguration.heightNormal() %>">
							<aui:validator name="digits" />
							<aui:validator name="required" />
						</aui:input>

						<aui:input name="preferences--width--" type="text" value="<%= iFramePortletInstanceConfiguration.width() %>" />
					</div>

					<aui:input cssClass="lfr-textarea-container" name="preferences--htmlAttributes--" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" type="textarea" value="<%= htmlAttributes %>" wrap="soft" />
				</aui:fieldset>
			</aui:fieldset-group>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.Util.toggleBoxes('<portlet:namespace />auth', '<portlet:namespace />authenticationOptions');
	Liferay.Util.toggleBoxes('<portlet:namespace />resizeAutomatically', '<portlet:namespace />displaySettings', true);
	Liferay.Util.toggleSelectBox('<portlet:namespace />authType', 'form', '<portlet:namespace />formAuthOptions');
	Liferay.Util.toggleSelectBox('<portlet:namespace />authType', 'basic', '<portlet:namespace />basicAuthOptions');
</aui:script>