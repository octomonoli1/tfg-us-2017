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

package com.liferay.dynamic.data.mapping.util.impl;

import com.liferay.dynamic.data.mapping.io.DDMFormJSONDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormJSONSerializer;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author Marcellus Tavares
 */
public class DDMFormTemplateSynchonizer {

	public DDMFormTemplateSynchonizer(
		DDMForm structureDDMForm,
		DDMFormJSONDeserializer ddmFormJSONDeserializer,
		DDMFormJSONSerializer ddmFormJSONSerializer,
		DDMTemplateLocalService ddmTemplateLocalService) {

		_structureDDMForm = structureDDMForm;
		_ddmFormJSONDeserializer = ddmFormJSONDeserializer;
		_ddmFormJSONSerializer = ddmFormJSONSerializer;
		_ddmTemplateLocalService = ddmTemplateLocalService;
	}

	public void setDDMFormTemplates(List<DDMTemplate> ddmFormTemplates) {
		_ddmFormTemplates = ddmFormTemplates;
	}

	public void synchronize() throws PortalException {
		for (DDMTemplate ddmTemplate : getDDMFormTemplates()) {
			DDMForm templateDDMForm = _ddmFormJSONDeserializer.deserialize(
				ddmTemplate.getScript());

			synchronizeDDMFormFields(
				_structureDDMForm.getDDMFormFieldsMap(true),
				templateDDMForm.getDDMFormFields(), ddmTemplate.getMode());

			String mode = ddmTemplate.getMode();

			if (mode.equals(DDMTemplateConstants.TEMPLATE_MODE_CREATE)) {
				addRequiredDDMFormFields(
					_structureDDMForm.getDDMFormFields(),
					templateDDMForm.getDDMFormFields());
			}

			updateDDMTemplate(ddmTemplate, templateDDMForm);
		}
	}

	protected void addRequiredDDMFormFields(
		List<DDMFormField> structureDDMFormFields,
		List<DDMFormField> templateDDMFormFields) {

		for (int i = 0; i < structureDDMFormFields.size(); i++) {
			DDMFormField structureDDMFormField = structureDDMFormFields.get(i);

			DDMFormField templateDDMFormField = getDDMFormField(
				templateDDMFormFields, structureDDMFormField.getName());

			if (templateDDMFormField == null) {
				if (structureDDMFormField.isRequired()) {
					templateDDMFormFields.add(structureDDMFormField);
				}
			}
			else {
				addRequiredDDMFormFields(
					structureDDMFormField.getNestedDDMFormFields(),
					templateDDMFormField.getNestedDDMFormFields());
			}
		}
	}

	protected DDMFormField getDDMFormField(
		List<DDMFormField> ddmFormFields, String name) {

		Queue<DDMFormField> queue = new LinkedList<>(ddmFormFields);

		DDMFormField ddmFormField = null;

		while ((ddmFormField = queue.poll()) != null) {
			if (name.equals(ddmFormField.getName())) {
				return ddmFormField;
			}

			queue.addAll(ddmFormField.getNestedDDMFormFields());
		}

		return null;
	}

	protected List<DDMTemplate> getDDMFormTemplates() {
		return _ddmFormTemplates;
	}

	protected void synchronizeDDMFormFieldRequiredProperty(
		DDMFormField structureDDMFormField, DDMFormField templateDDMFormField,
		String templateMode) {

		if (structureDDMFormField == null) {
			return;
		}

		if (!templateMode.equals(DDMTemplateConstants.TEMPLATE_MODE_CREATE)) {
			return;
		}

		templateDDMFormField.setRequired(structureDDMFormField.isRequired());
	}

	protected void synchronizeDDMFormFields(
		Map<String, DDMFormField> structureDDMFormFieldsMap,
		List<DDMFormField> templateDDMFormFields, String templateMode) {

		Iterator<DDMFormField> itr = templateDDMFormFields.iterator();

		while (itr.hasNext()) {
			DDMFormField templateDDMFormField = itr.next();

			String dataType = templateDDMFormField.getDataType();
			String name = templateDDMFormField.getName();

			if (Validator.isNotNull(dataType) &&
				!structureDDMFormFieldsMap.containsKey(name)) {

				itr.remove();

				continue;
			}

			synchronizeDDMFormFieldRequiredProperty(
				structureDDMFormFieldsMap.get(name), templateDDMFormField,
				templateMode);

			synchronizeDDMFormFields(
				structureDDMFormFieldsMap,
				templateDDMFormField.getNestedDDMFormFields(), templateMode);
		}
	}

	protected void updateDDMTemplate(
		DDMTemplate ddmTemplate, DDMForm templateDDMForm) {

		String script = _ddmFormJSONSerializer.serialize(templateDDMForm);

		ddmTemplate.setScript(script);

		_ddmTemplateLocalService.updateDDMTemplate(ddmTemplate);
	}

	private final DDMFormJSONDeserializer _ddmFormJSONDeserializer;
	private final DDMFormJSONSerializer _ddmFormJSONSerializer;
	private List<DDMTemplate> _ddmFormTemplates = new ArrayList<>();
	private final DDMTemplateLocalService _ddmTemplateLocalService;
	private final DDMForm _structureDDMForm;

}