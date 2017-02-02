<#include "../init.ftl">

<@liferay_aui["field-wrapper"] cssClass="form-builder-field" data=data>
	<div class="form-group">
		<@liferay_aui.input
			cssClass=cssClass
			dir=requestedLanguageDir
			helpMessage=escape(fieldStructure.tip)
			label=escape(label)
			name=namespacedFieldName
			required=required
			type="text"
			value=fieldValue
		/>
	</div>

	${fieldStructure.children}
</@>