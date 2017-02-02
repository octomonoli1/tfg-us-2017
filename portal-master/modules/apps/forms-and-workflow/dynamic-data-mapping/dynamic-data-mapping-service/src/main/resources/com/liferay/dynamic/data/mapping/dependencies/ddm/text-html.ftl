<#include "../init.ftl">

<#assign cssClass = "">
<#assign editorName = propsUtil.get("editor.wysiwyg.portal-impl.portlet.ddm.text_html.ftl")>

<#assign inputEditorName = "${namespacedFieldName}Editor">

<#assign fieldValue = paramUtil.getString(request, "${inputEditorName}", fieldValue)>

<#if editorName?starts_with("alloyeditor")>
	<#assign cssClass = "form-control">
</#if>

<@liferay_aui["field-wrapper"] cssClass="field-wrapper-html form-builder-field" data=data helpMessage=escape(fieldStructure.tip) label=escape(label) name=inputEditorName required=required>
	<#assign skipEditorLoading = paramUtil.getBoolean(request, "p_p_isolated")>

	<div class="form-group">
		<@liferay_ui["input-editor"]
			contents="${fieldValue}"
			contentsLanguageId="${requestedLocale}"
			cssClass="${cssClass}"
			editorName="${editorName}"
			initMethod=""
			name="${namespacedFieldName}Editor"
			onChangeMethod="${namespacedFieldName}OnChangeEditor"
			skipEditorLoading=skipEditorLoading
		/>

		<@liferay_aui.input name=namespacedFieldName type="hidden" value=fieldValue>
			<#if required>
				<@liferay_aui.validator name="required" />
			</#if>
		</@>

		<@liferay_aui.script>
			Liferay.provide(
				window,
				'${portletNamespace}${namespacedFieldName}OnChangeEditor',
				function() {
					var A = AUI();

					var field = A.one('#${portletNamespace}${namespacedFieldName}');

					field.val(window['${portletNamespace}${namespacedFieldName}Editor'].getHTML());

					var form = field.get('form');

					if (form) {
						var formName = form.get('name');

						var formValidator = Liferay.Form.get(formName).formValidator;

						if (formValidator) {
							formValidator.validateField(field);
						}
					}
				},
				['liferay-form']
			);
		</@>
	</div>

	${fieldStructure.children}
</@>