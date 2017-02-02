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
String portletId = portletDisplay.getRootPortletId();

String doAsUserId = themeDisplay.getDoAsUserId();

if (Validator.isNull(doAsUserId)) {
	doAsUserId = Encryptor.encrypt(company.getKeyObj(), String.valueOf(themeDisplay.getUserId()));
}

boolean autoCreate = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-editor:autoCreate"));
String contents = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-editor:contents"));
String contentsLanguageId = (String)request.getAttribute("liferay-ui:input-editor:contentsLanguageId");
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-editor:cssClass"));
Map<String, Object> data = (Map<String, Object>)request.getAttribute("liferay-ui:input-editor:data");
String editorName = (String)request.getAttribute("liferay-ui:input-editor:editorName");
String initMethod = (String)request.getAttribute("liferay-ui:input-editor:initMethod");
String name = namespace + GetterUtil.getString((String)request.getAttribute("liferay-ui:input-editor:name"));

String onBlurMethod = (String)request.getAttribute("liferay-ui:input-editor:onBlurMethod");

if (Validator.isNotNull(onBlurMethod)) {
	onBlurMethod = namespace + onBlurMethod;
}

String onChangeMethod = (String)request.getAttribute("liferay-ui:input-editor:onChangeMethod");

if (Validator.isNotNull(onChangeMethod)) {
	onChangeMethod = namespace + onChangeMethod;
}

String onFocusMethod = (String)request.getAttribute("liferay-ui:input-editor:onFocusMethod");

if (Validator.isNotNull(onFocusMethod)) {
	onFocusMethod = namespace + onFocusMethod;
}

String onInitMethod = (String)request.getAttribute("liferay-ui:input-editor:onInitMethod");

if (Validator.isNotNull(onInitMethod)) {
	onInitMethod = namespace + onInitMethod;
}

String placeholder = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-editor:placeholder"));
boolean showSource = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-editor:showSource"));
boolean skipEditorLoading = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-editor:skipEditorLoading"));

JSONObject editorConfigJSONObject = null;

if (data != null) {
	editorConfigJSONObject = (JSONObject)data.get("editorConfig");
}

EditorOptions editorOptions = null;

if (data != null) {
	editorOptions = (EditorOptions)data.get("editorOptions");
}

Map<String, Object> editorOptionsDynamicAttributes = null;

if (editorOptions != null) {
	editorOptionsDynamicAttributes = editorOptions.getDynamicAttributes();
}
%>

<c:if test="<%= !skipEditorLoading %>">
	<liferay-util:html-top outputKey="js_editor_alloyeditor_skip_editor_loading">
		<link href="<%= PortalUtil.getStaticResourceURL(request, themeDisplay.getCDNHost() + PortalWebResourcesUtil.getContextPath(PortalWebResourceConstants.RESOURCE_TYPE_EDITOR_ALLOYEDITOR) + "/alloyeditor/assets/alloy-editor-atlas.css") %>" rel="stylesheet" type="text/css" />

		<%
		long javaScriptLastModified = PortalWebResourcesUtil.getLastModified(PortalWebResourceConstants.RESOURCE_TYPE_EDITOR_ALLOYEDITOR);
		%>

		<script type="text/javascript">
			window.ALLOYEDITOR_BASEPATH = '<%= application.getContextPath() %>/alloyeditor/';
		</script>

		<script src="<%= HtmlUtil.escape(PortalUtil.getStaticResourceURL(request, themeDisplay.getCDNHost() + PortalWebResourcesUtil.getContextPath(PortalWebResourceConstants.RESOURCE_TYPE_EDITOR_CKEDITOR) + "/ckeditor/ckeditor.js", javaScriptLastModified)) %>" type="text/javascript"></script>

		<script src="<%= HtmlUtil.escape(PortalUtil.getStaticResourceURL(request, themeDisplay.getCDNHost() + PortalWebResourcesUtil.getContextPath(PortalWebResourceConstants.RESOURCE_TYPE_EDITOR_ALLOYEDITOR) + "/alloyeditor/liferay-alloy-editor-no-ckeditor-min.js", javaScriptLastModified)) %>" type="text/javascript"></script>

		<script type="text/javascript">
			AlloyEditor.regexBasePath = /(^|.*[\\\/])(?:liferay-alloy-editor[^/]+|liferay-alloy-editor)\.js(?:\?.*|;.*)?$/i;

			Liferay.namespace('EDITORS')['<%= editorName %>'] = true;

			CKEDITOR.scriptLoader.loadScripts = function(scripts, success, failure) {
				AUI().use(
					'aui-base',
					function(A) {
						scripts = scripts.filter(
							function(item) {
								return !A.one('script[src=' + item + ']');
							}
						);

						if (scripts.length) {
							CKEDITOR.scriptLoader.load(scripts, success, failure);
						}
						else {
							success();
						}
					}
				);
			};

			CKEDITOR.getNextZIndex = function() {
				return CKEDITOR.dialog._.currentZIndex ? CKEDITOR.dialog._.currentZIndex + 10 : Liferay.zIndex.WINDOW + 10;
			};
		</script>
	</liferay-util:html-top>
</c:if>

<script type="text/javascript">
	CKEDITOR.disableAutoInline = true;

	CKEDITOR.env.isCompatible = true;
</script>

<liferay-util:buffer var="alloyEditor">
	<div class="alloy-editor alloy-editor-placeholder <%= cssClass %>" contenteditable="false" data-placeholder="<%= LanguageUtil.get(request, placeholder) %>" id="<%= name %>" name="<%= name %>"></div>

	<aui:icon cssClass="alloy-editor-icon" image="format" markupView="lexicon" />
</liferay-util:buffer>

<liferay-util:buffer var="editor">
	<c:choose>
		<c:when test="<%= showSource %>">
			<div class="alloy-editor-wrapper" id="<%= name %>Wrapper">
				<div class="wrapper">
					<%= alloyEditor %>

					<div id="<%= name %>Source">
						<div class="lfr-source-editor-code"></div>
					</div>
				</div>
			</div>

			<div class="alloy-editor-switch hide">
				<button class="btn btn-default btn-xs hide" id="<%= name %>Fullscreen" type="button">
					<aui:icon cssClass="icon-monospaced" image="expand" markupView="lexicon" />
				</button>

				<button class="btn btn-default btn-xs hide" id="<%= name %>SwitchTheme" type="button">
					<aui:icon cssClass="icon-monospaced" image="moon" markupView="lexicon" />
				</button>

				<button class="btn btn-default btn-xs" id="<%= name %>Switch" type="button">
					<aui:icon cssClass="icon-monospaced" image="code" markupView="lexicon" />
				</button>
			</div>
		</c:when>
		<c:otherwise>
			<%= alloyEditor %>
		</c:otherwise>
	</c:choose>
</liferay-util:buffer>

<div class="alloy-editor-container" id="<%= name %>Container">
	<c:if test="<%= autoCreate %>">
		<%= editor %>
	</c:if>
</div>

<%
String modules = "liferay-alloy-editor";

String uploadURL = StringPool.BLANK;

if (editorOptions != null) {
	uploadURL = editorOptions.getUploadURL();

	if (Validator.isNotNull(data) && Validator.isNotNull(uploadURL)) {
		modules += ",liferay-editor-image-uploader";
	}
}

if (showSource) {
	modules += ",liferay-alloy-editor-source";
}
%>

<aui:script use="<%= modules %>">

	<%
	Locale contentsLocale = LocaleUtil.fromLanguageId(contentsLanguageId);

	contentsLanguageId = LocaleUtil.toLanguageId(contentsLocale);
	%>

	var alloyEditor;

	var getInitialContent = function() {
		var data;

		if (window['<%= HtmlUtil.escape(namespace + initMethod) %>']) {
			data = <%= HtmlUtil.escape(namespace + initMethod) %>();
		}
		else {
			data = '<%= contents != null ? HtmlUtil.escapeJS(contents) : StringPool.BLANK %>';
		}

		return data;
	};

	var createInstance = function() {
		document.getElementById('<%= name %>').setAttribute('contenteditable', true);

		var editorConfig = <%= Validator.isNotNull(editorConfigJSONObject) %> ? <%= editorConfigJSONObject %> : {};

		if (editorConfig.extraPlugins) {
			editorConfig.extraPlugins = A.Array.filter(
				editorConfig.extraPlugins.split(','),
				function(item) {
					return item !== 'ae_embed';
				}
			).join(',');
		}

		editorConfig.removePlugins = editorConfig.removePlugins ? editorConfig.removePlugins + ',ae_embed' : 'ae_embed';

		editorConfig = A.merge(
			{
				title: '<%= LanguageUtil.get(resourceBundle, "rich-text-editor") %>'
			},
			editorConfig
		);

		var plugins = [];

		<c:if test="<%= Validator.isNotNull(data) && Validator.isNotNull(uploadURL) %>">
			plugins.push(
				{
					cfg: {
						uploadUrl: '<%= uploadURL %>'
					},
					fn: A.Plugin.LiferayBlogsUploader
				}
			);
		</c:if>

		<c:if test="<%= showSource %>">
			plugins.push(A.Plugin.LiferayAlloyEditorSource);
		</c:if>

		alloyEditor = new A.LiferayAlloyEditor(
			{
				contents: '<%= HtmlUtil.escapeJS(contents) %>',
				editorConfig: editorConfig,
				namespace: '<%= name %>',
				onBlurMethod: window['<%= HtmlUtil.escapeJS(onBlurMethod) %>'],
				onChangeMethod: window['<%= HtmlUtil.escapeJS(onChangeMethod) %>'],
				onFocusMethod: window['<%= HtmlUtil.escapeJS(onFocusMethod) %>'],
				onInitMethod: window['<%= HtmlUtil.escapeJS(onInitMethod) %>'],
				plugins: plugins,
				textMode: <%= (editorOptions != null) ? editorOptions.isTextMode() : Boolean.FALSE.toString() %>
			}
		).render();

		<%
		boolean useCustomDataProcessor = (editorOptionsDynamicAttributes != null) && GetterUtil.getBoolean(editorOptionsDynamicAttributes.get("useCustomDataProcessor"));
		%>

		<c:if test="<%= useCustomDataProcessor %>">
			alloyEditor.getNativeEditor().on(
				'customDataProcessorLoaded',
				function() {
					alloyEditor.setHTML(getInitialContent());
				}
			);
		</c:if>

		<liferay-util:dynamic-include key='<%= "com.liferay.frontend.editor.alloyeditor.web#" + editorName + "#onEditorCreate" %>' />
	};

	window['<%= name %>'] = {
		create: function() {
			if (!alloyEditor) {
				var editorNode = A.Node.create('<%= HtmlUtil.escapeJS(editor) %>');

				var editorContainer = A.one('#<%= name %>Container');

				editorContainer.appendChild(editorNode);

				window['<%= name %>'].initEditor();
			}
		},

		destroy: function() {
			window['<%= name %>'].dispose();

			window['<%= name %>'] = null;
		},

		dispose: function() {
			if (alloyEditor) {
				alloyEditor.destroy();

				alloyEditor = null;
			}

			var editorNode = document.getElementById('<%= name %>');

			if (editorNode) {
				editorNode.parentNode.removeChild(editorNode);
			}
		},

		focus: function() {
			if (alloyEditor) {
				alloyEditor.focus();
			}
		},

		getHTML: function() {
			var data = '';

			if (alloyEditor && alloyEditor.instanceReady) {
				data = alloyEditor.getHTML();
			}
			else {
				data = getInitialContent();
			}

			return data;
		},

		getNativeEditor: function() {
			var nativeEditor;

			if (alloyEditor) {
				nativeEditor = alloyEditor.getEditor();
			}

			return nativeEditor;
		},

		getText: function() {
			var data = '';

			if (alloyEditor && alloyEditor.instanceReady) {
				data = alloyEditor.getText();
			}
			else {
				data = getInitialContent();
			}

			return data;
		},

		initEditor: function() {
			createInstance();
		},

		instanceReady: false,

		setHTML: function(value) {
			if (alloyEditor) {
				alloyEditor.setHTML(value);
			}
		}

	};

	Liferay.fire(
		'editorAPIReady',
		{
			editor: window['<%= name %>'],
			editorName: '<%= name %>'
		}
	);

	<c:if test="<%= autoCreate %>">
		window['<%= name %>'].initEditor();
	</c:if>

	var destroyInstance = function(event) {
		if (event.portletId === '<%= portletId %>') {
			window['<%= name %>'].destroy();

			Liferay.detach('destroyPortlet', destroyInstance);
		}
	};

	Liferay.on('destroyPortlet', destroyInstance);
</aui:script>