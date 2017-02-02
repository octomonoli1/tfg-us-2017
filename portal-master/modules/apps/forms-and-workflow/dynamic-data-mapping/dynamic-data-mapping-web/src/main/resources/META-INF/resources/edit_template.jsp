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
String closeRedirect = ParamUtil.getString(request, "closeRedirect");

String portletResource = ParamUtil.getString(request, "portletResource");

String portletResourceNamespace = ParamUtil.getString(request, "portletResourceNamespace");

DDMTemplate template = (DDMTemplate)request.getAttribute(DDMWebKeys.DYNAMIC_DATA_MAPPING_TEMPLATE);

long templateId = BeanParamUtil.getLong(template, request, "templateId");

long groupId = BeanParamUtil.getLong(template, request, "groupId", scopeGroupId);
long classNameId = BeanParamUtil.getLong(template, request, "classNameId");
long classPK = BeanParamUtil.getLong(template, request, "classPK");
long resourceClassNameId = BeanParamUtil.getLong(template, request, "resourceClassNameId");

boolean cacheable = BeanParamUtil.getBoolean(template, request, "cacheable", true);
boolean smallImage = BeanParamUtil.getBoolean(template, request, "smallImage");

DDMStructure structure = (DDMStructure)request.getAttribute(DDMWebKeys.DYNAMIC_DATA_MAPPING_STRUCTURE);

if ((structure == null) && (template != null)) {
	structure = ddmDisplayContext.fetchStructure(template);
}

String type = BeanParamUtil.getString(template, request, "type", DDMTemplateConstants.TEMPLATE_TYPE_FORM);
String mode = BeanParamUtil.getString(template, request, "mode", DDMTemplateConstants.TEMPLATE_MODE_CREATE);
String language = BeanParamUtil.getString(template, request, "language", ddmDisplay.getDefaultTemplateLanguage());
String script = BeanParamUtil.getString(template, request, "script");

if (Validator.isNull(script) && type.equals(DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY)) {
	TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(classNameId);

	if ((templateHandler == null) && (structure != null)) {
		templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(structure.getClassNameId());
	}

	if (templateHandler != null) {
		script = templateHandler.getTemplatesHelpContent(language);
	}
	else {
		script = ContentUtil.get(DDMWebConfigurationUtil.class.getClassLoader(), DDMWebConfigurationUtil.get(DDMWebConfigurationKeys.DYNAMIC_DATA_MAPPING_TEMPLATE_LANGUAGE_CONTENT, new Filter(language)));
	}
}

DDMTemplateVersion templateVersion = null;

if (template != null) {
	templateVersion = template.getLatestTemplateVersion();
}

String structureAvailableFields = ParamUtil.getString(request, "structureAvailableFields");

if (Validator.isNotNull(structureAvailableFields)) {
	scopeAvailableFields = structureAvailableFields;
}

boolean showBackURL = ParamUtil.getBoolean(request, "showBackURL", true);
boolean showCacheableInput = ParamUtil.getBoolean(request, "showCacheableInput");
boolean showHeader = ParamUtil.getBoolean(request, "showHeader", true);

DDMNavigationHelper ddmNavigationHelper = ddmDisplay.getDDMNavigationHelper();
%>

<portlet:actionURL name="addTemplate" var="addTemplateURL">
	<portlet:param name="mvcPath" value="/edit_template.jsp" />
</portlet:actionURL>

<portlet:actionURL name="updateTemplate" var="updateTemplateURL">
	<portlet:param name="mvcPath" value="/edit_template.jsp" />
</portlet:actionURL>

<div class="container-fluid-1280">
	<aui:form action="<%= (template == null) ? addTemplateURL : updateTemplateURL %>" cssClass="container-fluid-1280" enctype="multipart/form-data" method="post" name="fm" onSubmit='<%= "event.preventDefault();" %>'>
		<aui:input name="redirect" type="hidden" value="<%= ddmDisplay.getEditTemplateBackURL(liferayPortletRequest, liferayPortletResponse, classNameId, classPK, resourceClassNameId, portletResource) %>" />
		<aui:input name="closeRedirect" type="hidden" value="<%= closeRedirect %>" />
		<aui:input name="portletResource" type="hidden" value="<%= portletResource %>" />
		<aui:input name="portletResourceNamespace" type="hidden" value="<%= portletResourceNamespace %>" />
		<aui:input name="templateId" type="hidden" value="<%= templateId %>" />
		<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
		<aui:input name="classNameId" type="hidden" value="<%= classNameId %>" />
		<aui:input name="classPK" type="hidden" value="<%= classPK %>" />
		<aui:input name="resourceClassNameId" type="hidden" value="<%= resourceClassNameId %>" />
		<aui:input name="type" type="hidden" value="<%= type %>" />
		<aui:input name="status" type="hidden" value="<%= String.valueOf(WorkflowConstants.STATUS_APPROVED) %>" />
		<aui:input name="structureAvailableFields" type="hidden" value="<%= structureAvailableFields %>" />
		<aui:input name="saveAndContinue" type="hidden" value="<%= false %>" />

		<liferay-ui:error exception="<%= TemplateNameException.class %>" message="please-enter-a-valid-name" />
		<liferay-ui:error exception="<%= TemplateScriptException.class %>" message="please-enter-a-valid-script" />

		<liferay-ui:error exception="<%= TemplateSmallImageNameException.class %>">

			<%
			String[] imageExtensions = ddmDisplayContext.smallImageExtensions();
			%>

			<liferay-ui:message key="image-names-must-end-with-one-of-the-following-extensions" /> <%= StringUtil.merge(imageExtensions, StringPool.COMMA) %>.
		</liferay-ui:error>

		<liferay-ui:error exception="<%= TemplateSmallImageSizeException.class %>">

			<%
			long imageMaxSize = ddmDisplayContext.smallImageMaxSize();
			%>

			<liferay-ui:message arguments="<%= TextFormatter.formatStorageSize(imageMaxSize, locale) %>" key="please-enter-a-small-image-with-a-valid-file-size-no-larger-than-x" translateArguments="<%= false %>" />
		</liferay-ui:error>

		<c:if test="<%= showHeader %>">

			<%
			String title = StringPool.BLANK;

			if ((structure != null) || (template != null)) {
				title = ddmDisplay.getEditTemplateTitle(structure, template, locale);
			}
			else {
				title = ddmDisplay.getEditTemplateTitle(classNameId, locale);
			}
			%>

			<c:choose>
				<c:when test="<%= ddmDisplay.isShowBackURLInTitleBar() %>">

					<%
					portletDisplay.setShowBackIcon(true);
					portletDisplay.setURLBack(ddmDisplay.getEditTemplateBackURL(liferayPortletRequest, liferayPortletResponse, classNameId, classPK, resourceClassNameId, portletResource));

					renderResponse.setTitle(title);
					%>

				</c:when>
				<c:otherwise>
					<liferay-ui:header
						backURL="<%= ddmDisplay.getEditTemplateBackURL(liferayPortletRequest, liferayPortletResponse, classNameId, classPK, resourceClassNameId, portletResource) %>"
						localizeTitle="<%= false %>"
						showBackURL="<%= showBackURL %>"
						title="<%= title %>"
					/>
				</c:otherwise>
			</c:choose>
		</c:if>

		<aui:model-context bean="<%= template %>" model="<%= DDMTemplate.class %>" />

		<c:if test="<%= (templateVersion != null) && ddmDisplay.isVersioningEnabled() %>">
			<aui:workflow-status model="<%= DDMTemplate.class %>" status="<%= templateVersion.getStatus() %>" version="<%= templateVersion.getVersion() %>" />

			<div class="template-history-toolbar" id="<portlet:namespace />templateHistoryToolbar"></div>

			<aui:script use="aui-toolbar,aui-dialog-iframe-deprecated,liferay-util-window">
				var toolbarChildren = [
					<portlet:renderURL var="viewHistoryURL">
						<portlet:param name="mvcPath" value="/view_template_history.jsp" />
						<portlet:param name="redirect" value="<%= ddmDisplay.getEditTemplateBackURL(liferayPortletRequest, liferayPortletResponse, classNameId, classPK, resourceClassNameId, portletResource) %>" />
						<portlet:param name="templateId" value="<%= String.valueOf(template.getTemplateId()) %>" />
					</portlet:renderURL>

					{
						icon: 'icon-time',
						label: '<%= UnicodeLanguageUtil.get(request, "view-history") %>',
						on: {
							click: function(event) {
								event.domEvent.preventDefault();

								window.location.href = '<%= viewHistoryURL %>';
							}
						}
					}
				];

				new A.Toolbar(
					{
						boundingBox: '#<portlet:namespace />templateHistoryToolbar',
						children: toolbarChildren
					}
				).render();
			</aui:script>
		</c:if>

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) || windowState.equals(LiferayWindowState.POP_UP) %>" name="name" />

				<liferay-ui:panel-container cssClass="lfr-structure-entry-details-container" extended="<%= false %>" id="templateDetailsPanelContainer" persistState="<%= true %>">
					<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= false %>" id="templateDetailsSectionPanel" markupView="lexicon" persistState="<%= true %>" title="details">
						<c:if test="<%= ddmDisplay.isShowStructureSelector() %>">
							<div class="form-group">
								<aui:input helpMessage="structure-help" name="structure" type="resource" value="<%= (structure != null) ? structure.getName(locale) : StringPool.BLANK %>" />

								<c:if test="<%= ddmNavigationHelper.isNavigationStartsOnViewTemplates(liferayPortletRequest) && ((template == null) || (template.getClassPK() == 0)) %>">
									<liferay-ui:icon
										iconCssClass="icon-search"
										label="<%= true %>"
										linkCssClass="btn btn-default"
										message="select"
										url='<%= "javascript:" + renderResponse.getNamespace() + "openDDMStructureSelector();" %>'
									/>
								</c:if>
							</div>
						</c:if>

						<c:if test="<%= type.equals(DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY) %>">
							<aui:select changesContext="<%= true %>" helpMessage='<%= (template == null) ? StringPool.BLANK : "changing-the-language-does-not-automatically-translate-the-existing-template-script" %>' label="language" name="language">

								<%
								for (String curLangType : ddmDisplay.getTemplateLanguageTypes()) {
									StringBundler sb = new StringBundler(6);

									sb.append(LanguageUtil.get(request, curLangType + "[stands-for]"));
									sb.append(StringPool.SPACE);
									sb.append(StringPool.OPEN_PARENTHESIS);
									sb.append(StringPool.PERIOD);
									sb.append(curLangType);
									sb.append(StringPool.CLOSE_PARENTHESIS);
								%>

									<aui:option label="<%= sb.toString() %>" selected="<%= language.equals(curLangType) %>" value="<%= curLangType %>" />

								<%
								}
								%>

							</aui:select>
						</c:if>

						<c:if test="<%= !ddmDisplayContext.autogenerateTemplateKey() %>">
							<aui:input disabled="<%= (template != null) ? true : false %>" name="templateKey" />
						</c:if>

						<aui:input name="description" />

						<c:if test="<%= template != null %>">
							<aui:input helpMessage="template-key-help" name="templateKey" type="resource" value="<%= template.getTemplateKey() %>" />

							<portlet:resourceURL id="getTemplate" var="getTemplateURL">
								<portlet:param name="templateId" value="<%= String.valueOf(templateId) %>" />
							</portlet:resourceURL>

							<aui:input name="url" type="resource" value="<%= getTemplateURL.toString() %>" />

							<c:if test="<%= Validator.isNotNull(refererWebDAVToken) %>">
								<aui:input name="webDavURL" type="resource" value="<%= template.getWebDavURL(themeDisplay, refererWebDAVToken) %>" />
							</c:if>
						</c:if>

						<c:choose>
							<c:when test="<%= type.equals(DDMTemplateConstants.TEMPLATE_TYPE_FORM) %>">
								<aui:select helpMessage="only-allow-deleting-required-fields-in-edit-mode" label="mode" name="mode">
									<aui:option label="create" />
									<aui:option label="edit" />
								</aui:select>
							</c:when>
							<c:otherwise>
								<c:if test="<%= showCacheableInput %>">
									<aui:input helpMessage="journal-template-cacheable-help" name="cacheable" value="<%= cacheable %>" />
								</c:if>

								<div id="<portlet:namespace />smallImageContainer">
									<div class="lfr-ddm-small-image-header">
										<aui:input name="smallImage" />
									</div>

									<div class="lfr-ddm-small-image-content toggler-content-collapsed">
										<aui:row>
											<c:if test="<%= smallImage && (template != null) %>">
												<aui:col width="<%= 50 %>">
													<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="preview" />" class="lfr-ddm-small-image-preview" src="<%= HtmlUtil.escapeAttribute(template.getTemplateImageURL(themeDisplay)) %>" />
												</aui:col>
											</c:if>

											<aui:col width="<%= (smallImage && (template != null)) ? 50 : 100 %>">
												<aui:fieldset>
													<aui:input cssClass="lfr-ddm-small-image-type" inlineField="<%= true %>" label="small-image-url" name="type" type="radio" />

													<aui:input cssClass="lfr-ddm-small-image-value" inlineField="<%= true %>" label="" name="smallImageURL" title="small-image-url" />
												</aui:fieldset>

												<aui:fieldset>
													<aui:input cssClass="lfr-ddm-small-image-type" inlineField="<%= true %>" label="small-image" name="type" type="radio" />

													<aui:input cssClass="lfr-ddm-small-image-value" inlineField="<%= true %>" label="" name="smallImageFile" type="file" />
												</aui:fieldset>
											</aui:col>
										</aui:row>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</liferay-ui:panel>
				</liferay-ui:panel-container>

				<c:choose>
					<c:when test="<%= type.equals(DDMTemplateConstants.TEMPLATE_TYPE_FORM) %>">
						<%@ include file="/edit_template_form.jspf" %>
					</c:when>
					<c:otherwise>
						<%@ include file="/edit_template_display.jspf" %>
					</c:otherwise>
				</c:choose>
			</aui:fieldset>
		</aui:fieldset-group>
	</aui:form>

	<c:if test="<%= type.equals(DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY) %>">
		<aui:script use="aui-toggler">
			var container = A.one('#<portlet:namespace />smallImageContainer');

			var types = container.all('.lfr-ddm-small-image-type');
			var values = container.all('.lfr-ddm-small-image-value');

			var selectSmallImageType = function(index) {
				types.attr('checked', false);

				types.item(index).attr('checked', true);

				values.attr('disabled', true);

				values.item(index).attr('disabled', false);
			};

			container.delegate(
				'change',
				function(event) {
					var index = types.indexOf(event.currentTarget);

					selectSmallImageType(index);
				},
				'.lfr-ddm-small-image-type'
			);

			new A.Toggler(
				{
					animated: true,
					content: '#<portlet:namespace />smallImageContainer .lfr-ddm-small-image-content',
					expanded: <%= smallImage %>,
					header: '#<portlet:namespace />smallImageContainer .lfr-ddm-small-image-header',
					on: {
						animatingChange: function(event) {
							var instance = this;

							var expanded = !instance.get('expanded');

							A.one('#<portlet:namespace />smallImage').attr('checked', expanded);

							if (expanded) {
								types.each(
									function(item, index) {
										if (item.get('checked')) {
											values.item(index).attr('disabled', false);
										}
									}
								);
							}
							else {
								values.attr('disabled', true);
							}
						}
					}
				}
			);

			selectSmallImageType('<%= (template != null) && Validator.isNotNull(template.getSmallImageURL()) ? 0 : 1 %>');
		</aui:script>
	</c:if>

	<c:if test="<%= ddmDisplay.isShowStructureSelector() && ((template == null) || (template.getClassPK() == 0)) %>">
		<aui:script>
			function <portlet:namespace />openDDMStructureSelector() {
				Liferay.Util.openDDMPortlet(
					{
						basePortletURL: '<%= PortletURLFactoryUtil.create(request, DDMPortletKeys.DYNAMIC_DATA_MAPPING, PortletRequest.RENDER_PHASE) %>',
						classNameId: '<%= PortalUtil.getClassNameId(DDMStructure.class) %>',
						classPK: 0,
						eventName: '<portlet:namespace />selectStructure',
						groupId: <%= groupId %>,
						mvcPath: '/select_structure.jsp',
						navigationStartsOn: '<%= DDMNavigationHelper.SELECT_STRUCTURE %>',
						showAncestorScopes: true,
						title: '<%= UnicodeLanguageUtil.get(request, "structures") %>'
					},
					function(event) {
						if (document.<portlet:namespace />fm.<portlet:namespace />classPK.value != event.ddmstructureid) {
							document.<portlet:namespace />fm.<portlet:namespace />classPK.value = event.ddmstructureid;

							Liferay.fire('<portlet:namespace />refreshEditor');
						}
					}
				);
			}
		</aui:script>
	</c:if>

	<aui:button-row>
		<aui:script>
			Liferay.after(
				'<portlet:namespace />saveTemplate',
				function() {
					submitForm(document.<portlet:namespace />fm);
				}
			);

			function <portlet:namespace />saveDraftTemplate() {
				var form = AUI.$('#<portlet:namespace />fm');

				form.fm('status').val(<%= String.valueOf(WorkflowConstants.STATUS_DRAFT) %>);

				Liferay.fire('<%= renderResponse.getNamespace() + "saveTemplate" %>');
			}

			function <portlet:namespace />saveAndContinueTemplate() {
				document.<portlet:namespace />fm.<portlet:namespace />saveAndContinue.value = '1';

				Liferay.fire('<portlet:namespace />saveTemplate');
			}
		</aui:script>

		<%
		String taglibOnClick = "Liferay.fire('" + liferayPortletResponse.getNamespace() + "saveTemplate');";
		%>

		<aui:button cssClass="btn-lg" onClick="<%= taglibOnClick %>" primary="<%= true %>" value='<%= LanguageUtil.get(request, "save") %>' />

		<aui:button cssClass="btn-lg" onClick='<%= renderResponse.getNamespace() + "saveAndContinueTemplate();" %>' value='<%= LanguageUtil.get(resourceBundle, "save-and-continue") %>' />

		<c:if test="<%= ddmDisplay.isVersioningEnabled() %>">
			<aui:button cssClass="btn-lg" onClick='<%= renderResponse.getNamespace() + "saveDraftTemplate();" %>' value='<%= LanguageUtil.get(request, "save-draft") %>' />
		</c:if>

		<aui:button cssClass="btn-lg" href="<%= ddmDisplay.getEditTemplateBackURL(liferayPortletRequest, liferayPortletResponse, classNameId, classPK, resourceClassNameId, portletResource) %>" type="cancel" />
	</aui:button-row>
</div>