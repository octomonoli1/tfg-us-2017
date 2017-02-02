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

String backURL = ParamUtil.getString(request, "backURL", redirect);

long passwordPolicyId = ParamUtil.getLong(request, "passwordPolicyId");

PasswordPolicy passwordPolicy = PasswordPolicyLocalServiceUtil.fetchPasswordPolicy(passwordPolicyId);

if (passwordPolicy == null) {
	passwordPolicy = new PasswordPolicyImpl();

	passwordPolicy.setNew(true);
}

boolean defaultPolicy = BeanParamUtil.getBoolean(passwordPolicy, request, "defaultPolicy");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL.toString());

renderResponse.setTitle(passwordPolicy.isNew() ? LanguageUtil.get(request, "new-password-policy") : passwordPolicy.getName());
%>

<portlet:actionURL name="editPasswordPolicy" var="editPasswordPolicyURL" />

<aui:form action="<%= editPasswordPolicyURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="passwordPolicyId" type="hidden" value="<%= passwordPolicyId %>" />

	<liferay-ui:error exception="<%= DuplicatePasswordPolicyException.class %>" message="please-enter-a-unique-name" />
	<liferay-ui:error exception="<%= PasswordPolicyNameException.class %>" message="please-enter-a-valid-name" />

	<aui:model-context bean="<%= passwordPolicy %>" model="<%= PasswordPolicy.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input autoFocus="<%= (!defaultPolicy && windowState.equals(WindowState.MAXIMIZED)) %>" disabled="<%= defaultPolicy %>" name="name" required="<%= true %>" />

			<aui:input autoFocus="<%= (defaultPolicy && windowState.equals(WindowState.MAXIMIZED)) %>" name="description" />
		</aui:fieldset>

		<liferay-ui:panel-container extended="<%= true %>" id="passwordPoliciesAdminPasswordPolicyPanelContainer" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="passwordPoliciesAdminPasswordPolicyPasswordPanel" markupView="lexicon" persistState="<%= true %>" title="password-changes">
				<aui:fieldset>
					<aui:input helpMessage="changeable-help" name="changeable" type="toggle-switch" value="<%= passwordPolicy.isChangeable() %>" />

					<div class="password-policy-options" id="<portlet:namespace />changeableSettings">
						<aui:input helpMessage="change-required-help" name="changeRequired" type="toggle-switch" value="<%= passwordPolicy.isChangeRequired() %>" />

						<aui:select helpMessage="minimum-age-help" label="minimum-age" name="minAge">
							<aui:option label="none" value="0" />

							<%
							for (int i = 0; i < 15; i++) {
							%>

								<aui:option label="<%= LanguageUtil.getTimeDescription(request, _DURATIONS[i] * 1000) %>" value="<%= _DURATIONS[i] %>" />

							<%
							}
							%>

						</aui:select>
					</div>

					<aui:select helpMessage="reset-ticket-max-age-help" name="resetTicketMaxAge">
						<aui:option label="eternal" value="0" />

						<%
						for (int i = 0; i < 15; i++) {
						%>

							<aui:option label="<%= LanguageUtil.getTimeDescription(request, _DURATIONS[i] * 1000) %>" value="<%= _DURATIONS[i] %>" />

						<%
						}
						%>

					</aui:select>
				</aui:fieldset>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="passwordPoliciesAdminPasswordPolicySyntaxPanel" markupView="lexicon" persistState="<%= true %>" title="password-syntax-checking">
				<aui:fieldset>
					<aui:input helpMessage="enable-syntax-checking-help" label="enable-syntax-checking" name="checkSyntax" type="toggle-switch" value="<%= passwordPolicy.isCheckSyntax() %>" />

					<div class="password-policy-options" id="<portlet:namespace />syntaxSettings">
						<aui:input helpMessage="allow-dictionary-words-help" name="allowDictionaryWords" type="toggle-switch" value="<%= passwordPolicy.isAllowDictionaryWords() %>" />

						<aui:input helpMessage="minimum-alpha-numeric-help" label="minimum-alpha-numeric" name="minAlphanumeric" />

						<aui:input helpMessage="minimum-length-help" label="minimum-length" name="minLength" />

						<aui:input helpMessage="minimum-lower-case-help" label="minimum-lower-case" name="minLowerCase" />

						<aui:input helpMessage="minimum-numbers-help" label="minimum-numbers" name="minNumbers" />

						<aui:input helpMessage="minimum-symbols-help" label="minimum-symbols" name="minSymbols" />

						<aui:input helpMessage="minimum-upper-case-help" label="minimum-upper-case" name="minUpperCase" />

						<%
						String taglinbHelpMessage = LanguageUtil.format(request, "regular-expression-help", new Object[] {"<a href=\"http://docs.oracle.com/javase/tutorial/essential/regex\" target=\"_blank\">", "</a>"}, false);
						%>

						<aui:input helpMessage="<%= taglinbHelpMessage %>" label="regular-expression" name="regex" />
					</div>
				</aui:fieldset>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="passwordPoliciesAdminPasswordPolicyHistoryPanel" markupView="lexicon" persistState="<%= true %>" title="password-history">
				<aui:fieldset>
					<aui:input helpMessage="enable-history-help" label="enable-history" name="history" type="toggle-switch" value="<%= passwordPolicy.isHistory() %>" />

					<div class="password-policy-options" id="<portlet:namespace />historySettings">
						<aui:select helpMessage="history-count-help" name="historyCount">

							<%
							for (int i = 2; i < 25; i++) {
							%>

								<aui:option label="<%= i %>" />

							<%
							}
							%>

						</aui:select>
					</div>
				</aui:fieldset>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="passwordPoliciesAdminPasswordPolicyExpirationPanel" markupView="lexicon" persistState="<%= true %>" title="password-expiration">
				<aui:fieldset>
					<aui:input helpMessage="enable-expiration-help" label="enable-expiration" name="expireable" type="toggle-switch" value="<%= passwordPolicy.isExpireable() %>" />

					<div class="password-policy-options" id="<portlet:namespace />expirationSettings">
						<aui:select helpMessage="maximum-age-help" label="maximum-age" name="maxAge">

							<%
							for (int i = 15; i < _DURATIONS.length; i++) {
							%>

								<aui:option label="<%= LanguageUtil.getTimeDescription(request, _DURATIONS[i] * 1000) %>" value="<%= _DURATIONS[i] %>" />

							<%
							}
							%>

						</aui:select>

						<aui:select helpMessage="warning-time-help" name="warningTime">

							<%
							for (int i = 7; i < 16; i++) {
							%>

								<aui:option label="<%= LanguageUtil.getTimeDescription(request, _DURATIONS[i] * 1000) %>" value="<%= _DURATIONS[i] %>" />

							<%
							}
							%>

							<aui:option label="do-not-warn" value="<%= 0 %>" />
						</aui:select>

						<aui:input helpMessage="grace-limit-help" name="graceLimit" />
					</div>
				</aui:fieldset>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="passwordPoliciesAdminPasswordPolicyLockoutPanel" markupView="lexicon" persistState="<%= true %>" title="lockout">
				<aui:fieldset>
					<aui:input helpMessage="enable-lockout-help" label="enable-lockout" name="lockout" type="toggle-switch" value="<%= passwordPolicy.isLockout() %>" />

					<div class="password-policy-options" id="<portlet:namespace />lockoutSettings">
						<aui:input helpMessage="maximum-failure-help" label="maximum-failure" name="maxFailure" />

						<aui:select helpMessage="reset-failure-count-help" name="resetFailureCount">

							<%
							for (int i = 0; i < 15; i++) {
							%>

								<aui:option label="<%= LanguageUtil.getTimeDescription(request, _DURATIONS[i] * 1000) %>" value="<%= _DURATIONS[i] %>" />

							<%
							}
							%>

						</aui:select>

						<aui:select helpMessage="lockout-duration-help" name="lockoutDuration">
							<aui:option label="until-unlocked-by-an-administrator" value="0" />

							<%
							for (int i = 0; i < 15; i++) {
							%>

								<aui:option label="<%= LanguageUtil.getTimeDescription(request, _DURATIONS[i] * 1000) %>" value="<%= _DURATIONS[i] %>" />

							<%
							}
							%>

						</aui:select>
					</div>
				</aui:fieldset>
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.Util.toggleBoxes('<portlet:namespace />changeable', '<portlet:namespace />changeableSettings');
	Liferay.Util.toggleBoxes('<portlet:namespace />checkSyntax', '<portlet:namespace />syntaxSettings');
	Liferay.Util.toggleBoxes('<portlet:namespace />history', '<portlet:namespace />historySettings');
	Liferay.Util.toggleBoxes('<portlet:namespace />expireable', '<portlet:namespace />expirationSettings');
	Liferay.Util.toggleBoxes('<portlet:namespace />lockout', '<portlet:namespace />lockoutSettings');
</aui:script>

<%
if (passwordPolicy != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, passwordPolicy.getName(), null);
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "add-user"), currentURL);
}
%>

<%!
private static final long[] _DURATIONS = {300, 600, 1800, 3600, 7200, 10800, 21600, 43200, 86400, 172800, 259200, 345600, 432000, 518400, 604800, 1209600, 1814400, 2419200, 4838400, 7862400, 15724800, 31449600};
%>