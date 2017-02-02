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

<%@ include file="/html/portal/init.jsp" %>

<style>
	<%@ include file="/html/portal/setup_wizard_css.jspf" %>
</style>

<div id="wrapper">
	<header id="banner" role="banner">
		<div id="heading">
			<h1 class="site-title">
				<span class="logo" title="<liferay-ui:message key="welcome-to-liferay" />">

					<%
					Group group = layout.getGroup();
					%>

					<img alt="<%= HtmlUtil.escapeAttribute(group.getDescriptiveName(locale)) %>" height="<%= themeDisplay.getCompanyLogoHeight() %>" src="<%= HtmlUtil.escape(themeDisplay.getCompanyLogo()) %>" width="<%= themeDisplay.getCompanyLogoWidth() %>" />

					<span class="site-name">
						<%= PropsValues.COMPANY_DEFAULT_NAME %>
					</span>
				</span>
				<span class="configuration-title" title="<liferay-ui:message key="basic-configuration" />">
					<i class="icon-cog"></i>

					<liferay-ui:message key="basic-configuration" />
				</span>
			</h1>
		</div>
	</header>

	<div id="content">
		<div id="main-content">

			<%
			UnicodeProperties unicodeProperties = (UnicodeProperties)session.getAttribute(WebKeys.SETUP_WIZARD_PROPERTIES);
			%>

			<c:choose>
				<c:when test="<%= unicodeProperties == null %>">

					<%
					boolean defaultDatabase = SetupWizardUtil.isDefaultDatabase(request);
					%>

					<aui:form action='<%= themeDisplay.getPathMain() + "/portal/setup_wizard" %>' method="post" name="fm" onSubmit="event.preventDefault();">
						<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

						<div class="row">
							<aui:fieldset cssClass="col-md-6" label="portal">
								<aui:input helpTextCssClass="help-inline" label="portal-name" name="companyName" suffix='<%= LanguageUtil.format(request, "for-example-x", "Liferay", false) %>' value="<%= PropsValues.COMPANY_DEFAULT_NAME %>" />

								<aui:field-wrapper inlineLabel="default-language" label="default-language" name="companyLocale">
									<aui:select label="" name="companyLocale">

										<%
										String languageId = GetterUtil.getString((String)session.getAttribute(WebKeys.SETUP_WIZARD_DEFAULT_LOCALE), SetupWizardUtil.getDefaultLanguageId());

										for (Locale curLocale : LanguageUtil.getAvailableLocales()) {
										%>

											<aui:option label="<%= curLocale.getDisplayName(curLocale) %>" selected="<%= languageId.equals(LocaleUtil.toLanguageId(curLocale)) %>" value="<%= LocaleUtil.toLanguageId(curLocale) %>" />

										<%
										}
										%>

									</aui:select>

									<aui:button cssClass="change-language" name="changeLanguageButton" value="change" />
								</aui:field-wrapper>

								<aui:input label="add-sample-data" name='<%= "properties--" + PropsKeys.SETUP_WIZARD_ADD_SAMPLE_DATA + "--" %>' type="checkbox" value="<%= true %>" />
							</aui:fieldset>

							<aui:fieldset cssClass="col-md-6 column-last" label="administrator-user">
								<%@ include file="/html/portal/setup_wizard_user_name.jspf" %>

								<aui:input label="email" name="adminEmailAddress" value="<%= PropsValues.ADMIN_EMAIL_FROM_ADDRESS %>">
									<aui:validator name="email" />
									<aui:validator name="required" />
								</aui:input>
							</aui:fieldset>
						</div>

						<div class="row">
							<aui:fieldset cssClass="col-md-12" label="database">
								<aui:input name="defaultDatabase" type="hidden" value="<%= defaultDatabase %>" />

								<div id="defaultDatabaseOptions">
									<c:choose>
										<c:when test="<%= defaultDatabase %>">
											<p>
												<strong><liferay-ui:message key="default-database" /> (<liferay-ui:message key="database.hypersonic" />)</strong>
											</p>

											<liferay-ui:message key="this-database-is-useful-for-development-and-demo'ing-purposes" />
										</c:when>
										<c:otherwise>
											<p>
												<strong><liferay-ui:message key="configured-database" /></strong>
											</p>

											<dl class="database-values dl-horizontal">
												<c:choose>
													<c:when test="<%= Validator.isNotNull(PropsValues.JDBC_DEFAULT_JNDI_NAME) %>">
														<dt title="<liferay-ui:message key="jdbc-default-jndi-name" />">
															<liferay-ui:message key="jdbc-default-jndi-name" />
														</dt>
														<dd>
															<%= PropsValues.JDBC_DEFAULT_JNDI_NAME %>
														</dd>
													</c:when>
													<c:otherwise>
														<dt title="<liferay-ui:message key="jdbc-url" />">
															<liferay-ui:message key="jdbc-url" />
														</dt>
														<dd>
															<%= PropsValues.JDBC_DEFAULT_URL %>
														</dd>
														<dt title="<liferay-ui:message key="jdbc-driver-class-name" />">
															<liferay-ui:message key="jdbc-driver-class-name" />
														</dt>
														<dd>
															<%= PropsValues.JDBC_DEFAULT_DRIVER_CLASS_NAME %>
														</dd>
														<dt title="<liferay-ui:message key="user-name" />">
															<liferay-ui:message key="user-name" />
														</dt>
														<dd>
															<%= PropsValues.JDBC_DEFAULT_USERNAME %>
														</dd>
														<dt title="<liferay-ui:message key="password" />">
															<liferay-ui:message key="password" />
														</dt>
														<dd>
															<%= StringPool.EIGHT_STARS %>
														</dd>
													</c:otherwise>
												</c:choose>
											</dl>
										</c:otherwise>
									</c:choose>

									<c:if test="<%= Validator.isNull(PropsValues.JDBC_DEFAULT_JNDI_NAME) %>">
										<a href="<%= HttpUtil.addParameter(themeDisplay.getPathMain() + "/portal/setup_wizard", "defaultDatabase", false) %>" id="customDatabaseOptionsLink">
											(<liferay-ui:message key="change" />)
										</a>
									</c:if>
								</div>

								<div class="hide" id="customDatabaseOptions">
									<div class="connection-messages" id="connectionMessages"></div>

									<a class="database-options" href="<%= HttpUtil.addParameter(themeDisplay.getPathMain() + "/portal/setup_wizard", "defaultDatabase", true) %>" id="defaultDatabaseOptionsLink">
										&laquo; <liferay-ui:message key='<%= defaultDatabase ? "use-default-database" : "use-configured-database" %>' />
									</a>

									<aui:select cssClass="database-type" name="databaseType">

										<%
										for (int i = 0; i < PropsValues.SETUP_DATABASE_TYPES.length; i++) {
											String databaseType = PropsValues.SETUP_DATABASE_TYPES[i];

											Map<String, Object> data = new HashMap<String, Object>();

											String driverClassName = PropsUtil.get(PropsKeys.SETUP_DATABASE_DRIVER_CLASS_NAME, new Filter(databaseType));

											data.put("driverClassName", driverClassName);

											String url = PropsUtil.get(PropsKeys.SETUP_DATABASE_URL, new Filter(databaseType));

											data.put("url", url);
										%>

											<aui:option data="<%= data %>" label='<%= "database." + databaseType %>' selected="<%= PropsValues.JDBC_DEFAULT_URL.contains(databaseType) %>" value="<%= databaseType %>" />

										<%
										}
										%>

									</aui:select>

									<aui:input id="jdbcDefaultURL" label="jdbc-url" name='<%= "properties--" + PropsKeys.JDBC_DEFAULT_URL + "--" %>' value="<%= PropsValues.JDBC_DEFAULT_URL %>">
										<aui:validator name="required" />
									</aui:input>

									<aui:input id="jdbcDefaultDriverName" label="jdbc-driver-class-name" name='<%= "properties--" + PropsKeys.JDBC_DEFAULT_DRIVER_CLASS_NAME + "--" %>' value="<%= PropsValues.JDBC_DEFAULT_DRIVER_CLASS_NAME %>">
										<aui:validator name="required" />
									</aui:input>

									<aui:input id="jdbcDefaultUserName" label="user-name" name='<%= "properties--" + PropsKeys.JDBC_DEFAULT_USERNAME + "--" %>' value="<%= PropsValues.JDBC_DEFAULT_USERNAME %>" />

									<aui:input id="jdbcDefaultPassword" label="password" name='<%= "properties--" + PropsKeys.JDBC_DEFAULT_PASSWORD + "--" %>' type="password" value="<%= PropsValues.JDBC_DEFAULT_PASSWORD %>" />
								</div>
							</aui:fieldset>
						</div>

						<aui:button-row>
							<aui:button cssClass="btn-lg" name="finishButton" type="submit" value="finish-configuration" />
						</aui:button-row>
					</aui:form>

					<aui:script use="aui-base,aui-io-request,aui-loading-mask-deprecated">
						var customDatabaseOptions = A.one('#customDatabaseOptions');
						var customDatabaseOptionsLink = A.one('#customDatabaseOptionsLink');
						var databaseSelector = A.one('#databaseType');
						var defaultDatabase = A.one('#defaultDatabase');
						var defaultDatabaseOptions = A.one('#defaultDatabaseOptions');
						var defaultDatabaseOptionsLink = A.one('#defaultDatabaseOptionsLink');

						var jdbcDefaultDriverClassName = A.one('#jdbcDefaultDriverName');
						var jdbcDefaultURL = A.one('#jdbcDefaultURL');

						var command = A.one('#<%= Constants.CMD %>');
						var setupForm = A.one('#fm');

						var connectionMessages = A.one('#connectionMessages');

						var toggleDatabaseOptions = function(showDefault, event) {
							if (event) {
								event.preventDefault();
							}

							defaultDatabaseOptions.toggle(showDefault);

							customDatabaseOptions.toggle(!showDefault);

							defaultDatabase.val(showDefault);
						};

						if (customDatabaseOptionsLink) {
							customDatabaseOptionsLink.on('click', A.bind(toggleDatabaseOptions, null, false));
						}

						if (defaultDatabaseOptionsLink) {
							defaultDatabaseOptionsLink.on('click', A.bind(toggleDatabaseOptions, null, true));
						}

						var onChangeDatabaseSelector = function() {
							var index = databaseSelector.get('selectedIndex');

							var selectedOption = databaseSelector.get('options').item(index);

							var databaseURL = selectedOption.attr('data-url');
							var driverClassName = selectedOption.attr('data-driverClassName');

							jdbcDefaultDriverClassName.val(driverClassName);
							jdbcDefaultURL.val(databaseURL);
						};

						databaseSelector.on('change', onChangeDatabaseSelector);

						A.one('#changeLanguageButton').on(
							'click',
							function(event) {
								command.val('<%= Constants.TRANSLATE %>');

								setupForm.submit();
							}
						);

						var loadingMask = new A.LoadingMask(
							{
								'strings.loading': '<%= UnicodeLanguageUtil.get(request, "liferay-is-being-installed") %>',
								target: A.getBody()
							}
						);

						var updateMessage = function(message, type) {
							connectionMessages.html('<span class="alert alert-' + type + '">' + message + '</span>');
						};

						var startInstall = function() {
							connectionMessages.empty();

							loadingMask.show();
						};

						A.one('#fm').on(
							'submit',
							function(event) {
								if (defaultDatabase.val() == 'true') {
									startInstall();

									command.val('<%= Constants.UPDATE %>');

									submitForm(document.fm);
								}
								else {
									command.val('<%= Constants.TEST %>');

									A.io.request(
										setupForm.get('action'),
										{
											after: {
												failure: function(event, id, obj) {
													loadingMask.hide();

													updateMessage('<%= UnicodeLanguageUtil.get(request, "an-unexpected-error-occurred-while-connecting-to-the-database") %>', 'error');
												},
												success: function(event, id, obj) {
													command.val('<%= Constants.UPDATE %>');

													var responseData = this.get('responseData');

													if (!responseData.success) {
														updateMessage(responseData.message, 'error');

														loadingMask.hide();
													}
													else {
														submitForm(document.fm);
													}
												}
											},
											dataType: 'JSON',
											form: {
												id: document.fm
											},
											on: {
												start: startInstall
											}
										}
									);
								}
							}
						);
					</aui:script>
				</c:when>
				<c:otherwise>

					<%
					boolean propertiesFileCreated = GetterUtil.getBoolean((Boolean)session.getAttribute(WebKeys.SETUP_WIZARD_PROPERTIES_FILE_CREATED));
					%>

					<c:choose>
						<c:when test="<%= propertiesFileCreated %>">
							<div class="alert alert-success">
								<liferay-ui:message key="your-configuration-was-saved-sucessfully" />
							</div>

							<p class="lfr-setup-notice">

								<%
								String taglibArguments = "<span class=\"lfr-inline-code\">" + PropsValues.LIFERAY_HOME + StringPool.SLASH + SetupWizardUtil.PROPERTIES_FILE_NAME + "</span>";
								%>

								<liferay-ui:message arguments="<%= taglibArguments %>" key="the-configuration-was-saved-in" translateArguments="<%= false %>" />
							</p>

							<%
							boolean passwordUpdated = GetterUtil.getBoolean((Boolean)session.getAttribute(WebKeys.SETUP_WIZARD_PASSWORD_UPDATED));
							%>

							<c:if test="<%= !passwordUpdated %>">
								<p class="lfr-setup-notice">
									<liferay-ui:message arguments="<%= PropsValues.DEFAULT_ADMIN_PASSWORD %>" key="your-password-is-x.-you-will-be-required-to-change-your-password-the-next-time-you-log-into-the-portal" translateArguments="<%= false %>" />
								</p>
							</c:if>

							<div class="alert alert-info">
								<liferay-ui:message key="changes-will-take-effect-once-the-portal-is-restarted-please-restart-the-portal-now" />
							</div>
						</c:when>
						<c:otherwise>
							<p>
								<div class="alert alert-warning">

									<%
									String taglibArguments = "<span class=\"lfr-inline-code\">" + PropsValues.LIFERAY_HOME + "</span>";
									%>

									<liferay-ui:message arguments="<%= taglibArguments %>" key="sorry,-we-were-not-able-to-save-the-configuration-file-in-x" translateArguments="<%= false %>" />
								</div>
							</p>

							<aui:input cssClass="properties-text" label="" name="portal-ext" type="textarea" value="<%= unicodeProperties.toString() %>" wrap="soft" />
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<footer id="footer" role="contentinfo">
		<p class="powered-by">
			<liferay-ui:message key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
		</p>
	</footer>
</div>