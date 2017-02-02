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
GroupDisplayContextHelper groupDisplayContextHelper = new GroupDisplayContextHelper(request);

liveGroup = groupDisplayContextHelper.getLiveGroup();
liveGroupId = groupDisplayContextHelper.getLiveGroupId();
UnicodeProperties liveGroupTypeSettings = liveGroup.getTypeSettingsProperties();

LayoutSet privateLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(liveGroup.getGroupId(), true);
LayoutSet publicLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(liveGroup.getGroupId(), false);

boolean liveGroupRemoteStaging = liveGroup.hasRemoteStagingGroup() && PropsValues.STAGING_LIVE_GROUP_REMOTE_STAGING_ENABLED;
boolean stagedLocally = liveGroup.isStaged() && !liveGroup.isStagedRemotely();
boolean stagedRemotely = liveGroup.isStaged() && !stagedLocally;

if (stagedLocally) {
	stagingGroup = liveGroup.getStagingGroup();
	stagingGroupId = stagingGroup.getGroupId();
}

BackgroundTask lastCompletedInitialPublicationBackgroundTask = BackgroundTaskManagerUtil.fetchFirstBackgroundTask(liveGroupId, BackgroundTaskExecutorNames.LAYOUT_STAGING_BACKGROUND_TASK_EXECUTOR, true, new BackgroundTaskCreateDateComparator(false));
%>

<c:choose>
	<c:when test="<%= GroupPermissionUtil.contains(permissionChecker, liveGroup, ActionKeys.MANAGE_STAGING) && GroupPermissionUtil.contains(permissionChecker, liveGroup, ActionKeys.VIEW_STAGING) %>">
		<div class="container-fluid-1280">
			<liferay-ui:success key="stagingDisabled" message="staging-is-successfully-disabled" />

			<c:if test="<%= liveGroupRemoteStaging %>">
				<div class="alert alert-info">
					<liferay-ui:message key="live-group-remote-staging-alert" />
					<liferay-ui:message arguments='<%= "javascript:" + renderResponse.getNamespace() + "saveGroup(true);" %>' key="you-can-also-forcibly-disable-remote-staging" />
				</div>
			</c:if>

			<portlet:actionURL name="editStagingConfiguration" var="editStagingConfigurationURL">
				<portlet:param name="mvcPath" value="/view.jsp" />
			</portlet:actionURL>

			<portlet:renderURL var="redirectURL">
				<portlet:param name="mvcRenderCommandName" value="staging" />
			</portlet:renderURL>

			<aui:form action="<%= editStagingConfigurationURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveGroup();" %>'>
				<aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />
				<aui:input name="groupId" type="hidden" value="<%= liveGroupId %>" />
				<aui:input name="liveGroupId" type="hidden" value="<%= liveGroupId %>" />
				<aui:input name="stagingGroupId" type="hidden" value="<%= stagingGroupId %>" />
				<aui:input name="forceDisable" type="hidden" value="<%= false %>" />

				<c:if test="<%= (lastCompletedInitialPublicationBackgroundTask != null) && (lastCompletedInitialPublicationBackgroundTask.getStatus() == BackgroundTaskConstants.STATUS_FAILED) %>">
					<div class="alert alert-danger">
						<liferay-ui:message key="an-unexpected-error-occurred-with-the-initial-staging-publication" />

						<portlet:actionURL name="deleteBackgroundTask" var="deleteBackgroundTaskURL">
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="backgroundTaskId" value="<%= String.valueOf(lastCompletedInitialPublicationBackgroundTask.getBackgroundTaskId()) %>" />
						</portlet:actionURL>

						<liferay-ui:icon-delete
							confirmation="are-you-sure-you-want-to-remove-the-initial-staging-publication"
							label="<%= true %>"
							message="clear"
							url="<%= deleteBackgroundTaskURL %>"
						/>
					</div>

					<liferay-util:include page="/publish_process_message_task_details.jsp" servletContext="<%= application %>">
						<liferay-util:param name="backgroundTaskId" value="<%= String.valueOf(lastCompletedInitialPublicationBackgroundTask.getBackgroundTaskId()) %>" />
					</liferay-util:include>
				</c:if>

				<c:if test="<%= stagedLocally && (BackgroundTaskManagerUtil.getBackgroundTasksCount(liveGroupId, BackgroundTaskExecutorNames.LAYOUT_STAGING_BACKGROUND_TASK_EXECUTOR, false) > 0) %>">
					<div class="alert alert-warning">
						<liferay-ui:message key="an-inital-staging-publication-is-in-progress" />

						<a id="<portlet:namespace />publishProcessesLink"><liferay-ui:message key="the-status-of-the-publication-can-be-checked-on-the-publish-screen" /></a>
					</div>

					<aui:script>
						AUI.$('#<portlet:namespace />publishProcessesLink').on(
							'click',
							function(event) {
								Liferay.Util.openWindow(
									{
										id: 'publishProcesses',
										title: '<liferay-ui:message key="initial-publication" />',

										<liferay-portlet:renderURL portletName="<%= PortletKeys.EXPORT_IMPORT %>" var="publishProcessesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
											<portlet:param name="mvcRenderCommandName" value="publishLayouts" />
											<portlet:param name="<%= Constants.CMD %>" value="view_processes" />
											<portlet:param name="<%= SearchContainer.DEFAULT_CUR_PARAM %>" value="<%= ParamUtil.getString(request, SearchContainer.DEFAULT_CUR_PARAM) %>" />
											<portlet:param name="<%= SearchContainer.DEFAULT_DELTA_PARAM %>" value="<%= ParamUtil.getString(request, SearchContainer.DEFAULT_DELTA_PARAM) %>" />
											<portlet:param name="groupId" value="<%= String.valueOf(stagingGroupId) %>" />
											<portlet:param name="liveGroupId" value="<%= String.valueOf(liveGroupId) %>" />
											<portlet:param name="localPublishing" value="<%= String.valueOf(stagedLocally) %>" />
										</liferay-portlet:renderURL>

										uri: '<%= HtmlUtil.escapeJS(publishProcessesURL.toString()) %>'
									}
								);
							}
						);
					</aui:script>
				</c:if>

				<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="staging" />

				<c:if test="<%= privateLayoutSet.isLayoutSetPrototypeLinkActive() || publicLayoutSet.isLayoutSetPrototypeLinkActive() %>">
					<div class="alert alert-info">
						<liferay-ui:message key="staging-cannot-be-used-for-this-site-because-the-propagation-of-changes-from-the-site-template-is-enabled" />
						<c:choose>
							<c:when test="<%= PortalPermissionUtil.contains(permissionChecker, ActionKeys.UNLINK_LAYOUT_SET_PROTOTYPE) %>">
								<liferay-ui:message key="change-the-configuration-in-the-details-section" />
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="contact-your-administrator-to-change-the-configuration" />
							</c:otherwise>
						</c:choose>
					</div>
				</c:if>

				<liferay-ui:error exception="<%= LocaleException.class %>">

					<%
					LocaleException le = (LocaleException)errorException;
					%>

					<c:if test="<%= le.getType() == LocaleException.TYPE_EXPORT_IMPORT %>">
						<liferay-ui:message arguments="<%= new String[] {StringUtil.merge(le.getSourceAvailableLocales(), StringPool.COMMA_AND_SPACE), StringUtil.merge(le.getTargetAvailableLocales(), StringPool.COMMA_AND_SPACE)} %>" key="the-default-language-x-does-not-match-the-portal's-available-languages-x" translateArguments="<%= false %>" />
					</c:if>
				</liferay-ui:error>

				<liferay-ui:error exception="<%= SystemException.class %>">

					<%
					SystemException se = (SystemException)errorException;
					%>

					<liferay-ui:message key="<%= se.getMessage() %>" />
				</liferay-ui:error>

				<c:if test="<%= !privateLayoutSet.isLayoutSetPrototypeLinkActive() && !publicLayoutSet.isLayoutSetPrototypeLinkActive() %>">
					<aui:fieldset-group markupView="lexicon">
						<aui:fieldset>
							<div id="<portlet:namespace />stagingTypes">
								<aui:input checked="<%= !liveGroup.isStaged() %>" id="none" label="none" name="stagingType" type="radio" value="<%= StagingConstants.TYPE_NOT_STAGED %>" />

								<c:if test="<%= !liveGroupRemoteStaging && !stagedRemotely %>">
									<aui:input checked="<%= stagedLocally %>" helpMessage="staging-type-local" id="local" label="local-live" name="stagingType" type="radio" value="<%= StagingConstants.TYPE_LOCAL_STAGING %>" />
								</c:if>

								<c:if test="<%= !stagedLocally %>">
									<aui:input checked="<%= stagedRemotely %>" helpMessage="staging-type-remote" id="remote" label="remote-live" name="stagingType" type="radio" value="<%= StagingConstants.TYPE_REMOTE_STAGING %>" />
								</c:if>
							</div>
						</aui:fieldset>

						<%
						boolean showRemoteOptions = stagedRemotely;

						int stagingType = ParamUtil.getInteger(request, "stagingType");

						if (stagingType == StagingConstants.TYPE_REMOTE_STAGING) {
							showRemoteOptions = true;
						}
						%>

						<div class="<%= showRemoteOptions ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />remoteStagingOptions">
							<%@ include file="/error_auth_exception.jspf" %>

							<%@ include file="/error_remote_export_exception.jspf" %>

							<aui:fieldset collapsible="<%= true %>" label="remote-live-connection-settings">
								<%@ include file="/error_remote_options_exception.jspf" %>

								<div class="alert alert-info">
									<liferay-ui:message key="remote-publish-help" />
								</div>

								<aui:input label="remote-host-ip" name="remoteAddress" size="20" type="text" value='<%= liveGroupTypeSettings.getProperty("remoteAddress") %>' />

								<aui:input label="remote-port" name="remotePort" size="10" type="text" value='<%= liveGroupTypeSettings.getProperty("remotePort") %>' />

								<aui:input label="remote-path-context" name="remotePathContext" size="10" type="text" value='<%= liveGroupTypeSettings.getProperty("remotePathContext") %>' />

								<aui:input label='<%= LanguageUtil.get(request, "remote-site-id") %>' name="remoteGroupId" size="10" type="text" value='<%= liveGroupTypeSettings.getProperty("remoteGroupId") %>' />

								<div class="flex-container">

									<%
									boolean secureConnection = GetterUtil.getBoolean(liveGroupTypeSettings.getProperty("secureConnection"));
									%>

									<span class="flex-item-center staging-configuration-control-label <%= secureConnection ? "staging-configuration-control-label-bold" : StringPool.BLANK %>">
										<liferay-ui:message key="use-a-secure-network-connection" />
									</span>
									<span class="staging-configuration-control-toggle">
										<aui:input label="<%= StringPool.BLANK %>" name="secureConnection" type="toggle-switch" value="<%= secureConnection %>" />
									</span>
								</div>
							</aui:fieldset>
						</div>

						<div class="<%= ((liveGroup.isStaged() || (stagingType != StagingConstants.TYPE_NOT_STAGED)) ? StringPool.BLANK : "hide") %>" id="<portlet:namespace />stagedPortlets">
							<c:if test="<%= !liveGroup.isCompany() && !liveGroupRemoteStaging %>">
								<aui:fieldset collapsible="<%= true %>" label="page-versioning">
									<p class="staging-configuration-help-label">
										<liferay-ui:message key="page-versioning-help" />
									</p>

									<ul class="list-group staging-configuration-list">

										<%
										boolean branchingPublic = GetterUtil.getBoolean(liveGroupTypeSettings.getProperty("branchingPublic"));
										%>

										<li class="flex-container list-group-item">
											<span class="flex-item-center staging-configuration-control-label <%= branchingPublic ? "staging-configuration-control-label-bold" : StringPool.BLANK %>">
												<liferay-ui:message key="enabled-on-public-pages" />
											</span>
											<span class="staging-configuration-control-toggle">
												<aui:input label="<%= StringPool.BLANK %>" name="branchingPublic" type="toggle-switch" value="<%= branchingPublic %>" />
											</span>
										</li>

										<%
										boolean branchingPrivate = GetterUtil.getBoolean(liveGroupTypeSettings.getProperty("branchingPrivate"));
										%>

										<li class="flex-container list-group-item">
											<span class="flex-item-center staging-configuration-control-label <%= branchingPrivate ? "staging-configuration-control-label-bold" : StringPool.BLANK %>">
												<liferay-ui:message key="enabled-on-private-pages" />
											</span>
											<span class="staging-configuration-control-toggle">
												<aui:input label="<%= StringPool.BLANK %>" name="branchingPrivate" type="toggle-switch" value="<%= branchingPrivate %>" />
											</span>
										</li>
									</ul>
								</aui:fieldset>
							</c:if>

							<aui:fieldset collapsible="<%= true %>" helpMessage="staged-portlets-help" label="staged-content">
								<p class="staging-configuration-help-label">
									<liferay-ui:message key="staged-portlets-alert" />
								</p>

								<aui:input disabled="<%= (liveGroupRemoteStaging && liveGroup.isStagedRemotely()) || liveGroup.isStaged() %>" id="selectAllCheckbox" label="select-all" name="selectAll" type="checkbox" />

								<ul class="list-group staging-configuration-list" id="stagingConfigurationControls">

									<%
									Set<String> portletDataHandlerClassNames = new HashSet<String>();

									List<Portlet> dataSiteLevelPortlets = ExportImportHelperUtil.getDataSiteLevelPortlets(company.getCompanyId(), true);

									dataSiteLevelPortlets = ListUtil.sort(dataSiteLevelPortlets, new PortletTitleComparator(application, locale));

									for (Portlet curPortlet : dataSiteLevelPortlets) {
										PortletDataHandler portletDataHandler = curPortlet.getPortletDataHandlerInstance();

										Class<?> portletDataHandlerClass = portletDataHandler.getClass();

										String portletDataHandlerClassName = portletDataHandlerClass.getName();

										if (!portletDataHandlerClassNames.contains(portletDataHandlerClassName)) {
											portletDataHandlerClassNames.add(portletDataHandlerClassName);
										}
										else {
											continue;
										}

										boolean staged = portletDataHandler.isPublishToLiveByDefault();

										if (stagingGroup != null) {
											staged = stagingGroup.isStagedPortlet(StagingUtil.getStagedPortletId(curPortlet.getRootPortletId()));
										}
									%>

										<li class="flex-container list-group-item">
											<span class="flex-item-center staging-configuration-control-label <%= staged ? "staging-configuration-control-label-bold" : StringPool.BLANK %>">
												<liferay-ui:message key="<%= PortalUtil.getPortletTitle(curPortlet, application, locale) %>" />
											</span>
											<span class="staging-configuration-control-toggle">
												<aui:input disabled="<%= (liveGroupRemoteStaging && liveGroup.isStagedRemotely()) || liveGroup.isStaged() %>" label="<%= StringPool.BLANK %>" name="<%= StagingConstants.STAGED_PREFIX + StagingUtil.getStagedPortletId(curPortlet.getRootPortletId()) + StringPool.DOUBLE_DASH %>" type="toggle-switch" value="<%= staged %>" />
											</span>
										</li>

									<%
									}
									%>

								</ul>
							</aui:fieldset>
						</div>
					</aui:fieldset-group>

					<aui:script sandbox="<%= true %>">
						var remoteStagingOptions = $('#<portlet:namespace />remoteStagingOptions');
						var stagedPortlets = $('#<portlet:namespace />stagedPortlets');

						var stagingTypes = $('#<portlet:namespace />stagingTypes');

						stagingTypes.on(
							'click',
							'input',
							function(event) {
								var value = $(event.currentTarget).val();

								stagedPortlets.toggleClass('hide', value == '<%= StagingConstants.TYPE_NOT_STAGED %>');

								remoteStagingOptions.toggleClass('hide', value != '<%= StagingConstants.TYPE_REMOTE_STAGING %>');
							}
						);
					</aui:script>

					<aui:button-row>
						<aui:button cssClass="btn-lg" type="submit" />
					</aui:button-row>
				</c:if>
			</aui:form>
		</div>
	</c:when>
	<c:otherwise>
		<div class="alert alert-info">
			<liferay-ui:message key="you-do-not-have-permission-to-manage-settings-related-to-staging" />
		</div>
	</c:otherwise>
</c:choose>

<aui:script>
	function <portlet:namespace />saveGroup(forceDisable) {
		var $ = AUI.$;

		var form = $(document.<portlet:namespace />fm);

		var ok = true;

		<c:if test="<%= liveGroup != null %>">
			var stagingTypeEl = $('input[name=<portlet:namespace />stagingType]:checked');

			var oldValue;

			<c:choose>
				<c:when test="<%= liveGroup.isStaged() && !liveGroup.isStagedRemotely() %>">
					oldValue = <%= StagingConstants.TYPE_LOCAL_STAGING %>;
				</c:when>
				<c:when test="<%= liveGroup.isStaged() && liveGroup.isStagedRemotely() %>">
					oldValue = <%= StagingConstants.TYPE_REMOTE_STAGING %>;
				</c:when>
				<c:otherwise>
					oldValue = <%= StagingConstants.TYPE_NOT_STAGED %>;
				</c:otherwise>
			</c:choose>

			var currentValue = stagingTypeEl.val();

			if (stagingTypeEl.length && (currentValue != oldValue)) {
				ok = false;

				if (currentValue == <%= StagingConstants.TYPE_NOT_STAGED %>) {
					ok = confirm('<%= UnicodeLanguageUtil.format(request, "are-you-sure-you-want-to-deactivate-staging-for-x", liveGroup.getDescriptiveName(locale), false) %>');
				}
				else if (currentValue == <%= StagingConstants.TYPE_LOCAL_STAGING %>) {
					ok = confirm('<%= UnicodeLanguageUtil.format(request, "are-you-sure-you-want-to-activate-local-staging-for-x", liveGroup.getDescriptiveName(locale), false) %>');
				}
				else if (currentValue == <%= StagingConstants.TYPE_REMOTE_STAGING %>) {
					ok = confirm('<%= UnicodeLanguageUtil.format(request, "are-you-sure-you-want-to-activate-remote-staging-for-x", liveGroup.getDescriptiveName(locale), false) %>');
				}
			}
		</c:if>

		if (ok) {
			if (forceDisable) {
				form.fm('forceDisable').val(true);
				form.fm('local').prop('checked', false);
				form.fm('none').prop('checked', true);
				form.fm('redirect').val('<portlet:renderURL><portlet:param name="mvcPath" value="/view.jsp" /><portlet:param name="historyKey" value='<%= renderResponse.getNamespace() + "staging" %>' /></portlet:renderURL>');
				form.fm('remote').prop('checked', false);
			}

			submitForm(form);
		}
	}
</aui:script>

<aui:script sandbox="<%= true %>">
	var stagingConfigurationControls = $('#stagingConfigurationControls');

	var allCheckboxes = stagingConfigurationControls.find('input[type=checkbox]');

	$('#<portlet:namespace />selectAllCheckbox').on(
		'change',
		function() {
			allCheckboxes.prop('checked', this.checked);
		}
	);
</aui:script>