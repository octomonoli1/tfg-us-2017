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

package com.liferay.dynamic.data.mapping.type.select.internal;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Renato Rego
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=select",
	service = DDMFormFieldRenderer.class
)
public class SelectDDMFormFieldRenderer extends BaseDDMFormFieldRenderer {

	@Override
	public String getTemplateLanguage() {
		return TemplateConstants.LANG_TYPE_SOY;
	}

	@Override
	public String getTemplateNamespace() {
		return "ddm.select";
	}

	@Override
	public TemplateResource getTemplateResource() {
		return _templateResource;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_templateResource = getTemplateResource(
			"/META-INF/resources/select.soy");
	}

	protected DDMFormFieldOptions getDDMFormFieldOptions(
		DDMFormField ddmFormField) {

		String dataSourceType = GetterUtil.getString(
			ddmFormField.getProperty("dataSourceType"), "manual");

		if (Objects.equals(dataSourceType, "manual")) {
			return ddmFormField.getDDMFormFieldOptions();
		}

		return new DDMFormFieldOptions();
	}

	protected List<Object> getOptions(
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		SelectDDMFormFieldContextHelper selectDDMFormFieldContextHelper =
			new SelectDDMFormFieldContextHelper(
				jsonFactory, getDDMFormFieldOptions(ddmFormField),
				ddmFormFieldRenderingContext.getValue(),
				ddmFormField.getPredefinedValue(),
				ddmFormFieldRenderingContext.getLocale());

		return selectDDMFormFieldContextHelper.getOptions();
	}

	@Override
	protected void populateRequiredContext(
		Template template, DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		super.populateRequiredContext(
			template, ddmFormField, ddmFormFieldRenderingContext);

		template.put(
			"multiple",
			ddmFormField.isMultiple() ? "multiple" : StringPool.BLANK);
		template.put(
			"options", getOptions(ddmFormField, ddmFormFieldRenderingContext));

		Map<String, String> stringsMap = new HashMap<>();

		stringsMap.put(
			"chooseAnOption",
			LanguageUtil.get(
				ddmFormFieldRenderingContext.getLocale(), "choose-an-option"));

		template.put("strings", stringsMap);
		template.put("value", ddmFormFieldRenderingContext.getValue());
	}

	@Reference
	protected JSONFactory jsonFactory;

	private TemplateResource _templateResource;

}