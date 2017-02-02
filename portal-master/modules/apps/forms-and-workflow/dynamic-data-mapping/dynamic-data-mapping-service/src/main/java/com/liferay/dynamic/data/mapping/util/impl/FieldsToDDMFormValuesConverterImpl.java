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

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.dynamic.data.mapping.util.FieldsToDDMFormValuesConverter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true)
public class FieldsToDDMFormValuesConverterImpl
	implements FieldsToDDMFormValuesConverter {

	@Override
	public DDMFormValues convert(DDMStructure ddmStructure, Fields fields)
		throws PortalException {

		DDMFormValues ddmFormValues = createDDMFormValues(
			ddmStructure.getFullHierarchyDDMForm(),
			fields.getAvailableLocales(), fields.getDefaultLocale());

		DDMFieldsCounter ddmFieldsCounter = new DDMFieldsCounter();

		for (String fieldName : ddmStructure.getRootFieldNames()) {
			int repetitions = countDDMFieldRepetitions(
				fields, fieldName, null, -1);

			for (int i = 0; i < repetitions; i++) {
				DDMFormFieldValue ddmFormFieldValue = createDDMFormFieldValue(
					fieldName);

				setDDMFormFieldValueProperties(
					ddmFormFieldValue, ddmStructure, fields, ddmFieldsCounter);

				ddmFormValues.addDDMFormFieldValue(ddmFormFieldValue);
			}
		}

		return ddmFormValues;
	}

	protected int countDDMFieldRepetitions(
			Fields ddmFields, String fieldName, String parentFieldName,
			int parentOffset)
		throws PortalException {

		Field ddmFieldsDisplayField = ddmFields.get(
			DDMImpl.FIELDS_DISPLAY_NAME);

		if (ddmFieldsDisplayField == null) {
			if (ddmFields.contains(fieldName)) {
				return 1;
			}

			return 0;
		}

		String[] ddmFieldsDisplayValues = getDDMFieldsDisplayValues(
			ddmFieldsDisplayField);

		int offset = -1;

		int repetitions = 0;

		for (int i = 0; i < ddmFieldsDisplayValues.length; i++) {
			String fieldDisplayName = ddmFieldsDisplayValues[i];

			if (offset > parentOffset) {
				break;
			}

			if (fieldDisplayName.equals(parentFieldName)) {
				offset++;
			}

			if (fieldDisplayName.equals(fieldName) &&
				(offset == parentOffset)) {

				repetitions++;
			}
		}

		return repetitions;
	}

	protected DDMFormFieldValue createDDMFormFieldValue(String name) {
		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setName(name);

		return ddmFormFieldValue;
	}

	protected DDMFormValues createDDMFormValues(
		DDMForm ddmForm, Set<Locale> availableLocales, Locale defaultLocale) {

		DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);

		ddmFormValues.setAvailableLocales(availableLocales);
		ddmFormValues.setDefaultLocale(defaultLocale);

		return ddmFormValues;
	}

	protected String getDDMFieldInstanceId(
		Fields ddmFields, String fieldName, int index) {

		Field ddmFieldsDisplayField = ddmFields.get(
			DDMImpl.FIELDS_DISPLAY_NAME);

		if (ddmFieldsDisplayField == null) {
			return StringUtil.randomString();
		}

		String prefix = fieldName.concat(DDMImpl.INSTANCE_SEPARATOR);

		String[] ddmFieldsDisplayValues = StringUtil.split(
			(String)ddmFieldsDisplayField.getValue());

		for (String ddmFieldsDisplayValue : ddmFieldsDisplayValues) {
			if (ddmFieldsDisplayValue.startsWith(prefix)) {
				index--;

				if (index < 0) {
					return StringUtil.extractLast(
						ddmFieldsDisplayValue, DDMImpl.INSTANCE_SEPARATOR);
				}
			}
		}

		return null;
	}

	protected String[] getDDMFieldsDisplayValues(Field ddmFieldsDisplayField)
		throws PortalException {

		try {
			DDMStructure ddmStructure = ddmFieldsDisplayField.getDDMStructure();

			List<String> fieldsDisplayValues = new ArrayList<>();

			String[] values = splitFieldsDisplayValue(ddmFieldsDisplayField);

			for (String value : values) {
				String fieldName = StringUtil.extractFirst(
					value, DDMImpl.INSTANCE_SEPARATOR);

				if (ddmStructure.hasField(fieldName)) {
					fieldsDisplayValues.add(fieldName);
				}
			}

			return fieldsDisplayValues.toArray(
				new String[fieldsDisplayValues.size()]);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	protected String getDDMFieldValueString(
		Field ddmField, Locale locale, int index) {

		Serializable fieldValue = ddmField.getValue(locale, index);

		if (fieldValue instanceof Date) {
			Date valueDate = (Date)fieldValue;

			fieldValue = valueDate.getTime();
		}

		return String.valueOf(fieldValue);
	}

	protected void setDDMFormFieldValueInstanceId(
		DDMFormFieldValue ddmFormFieldValue, Fields ddmFields,
		DDMFieldsCounter ddmFieldsCounter) {

		String name = ddmFormFieldValue.getName();

		String instanceId = getDDMFieldInstanceId(
			ddmFields, name, ddmFieldsCounter.get(name));

		ddmFormFieldValue.setInstanceId(instanceId);
	}

	protected void setDDMFormFieldValueLocalizedValue(
		DDMFormFieldValue ddmFormFieldValue, Field ddmField, int index) {

		Value value = new LocalizedValue(ddmField.getDefaultLocale());

		for (Locale availableLocale : ddmField.getAvailableLocales()) {
			String valueString = getDDMFieldValueString(
				ddmField, availableLocale, index);

			value.addString(availableLocale, valueString);
		}

		ddmFormFieldValue.setValue(value);
	}

	protected void setDDMFormFieldValueProperties(
			DDMFormFieldValue ddmFormFieldValue, DDMStructure ddmStructure,
			Fields ddmFields, DDMFieldsCounter ddmFieldsCounter)
		throws PortalException {

		setDDMFormFieldValueInstanceId(
			ddmFormFieldValue, ddmFields, ddmFieldsCounter);

		setNestedDDMFormFieldValues(
			ddmFormFieldValue, ddmStructure, ddmFields, ddmFieldsCounter);

		setDDMFormFieldValueValues(
			ddmFormFieldValue, ddmStructure, ddmFields, ddmFieldsCounter);
	}

	protected void setDDMFormFieldValueUnlocalizedValue(
		DDMFormFieldValue ddmFormFieldValue, Field ddmField, int index) {

		String valueString = getDDMFieldValueString(
			ddmField, ddmField.getDefaultLocale(), index);

		Value value = new UnlocalizedValue(valueString);

		ddmFormFieldValue.setValue(value);
	}

	protected void setDDMFormFieldValueValues(
			DDMFormFieldValue ddmFormFieldValue, DDMStructure ddmStructure,
			Fields ddmFields, DDMFieldsCounter ddmFieldsCounter)
		throws PortalException {

		String fieldName = ddmFormFieldValue.getName();

		if (!ddmStructure.isFieldTransient(fieldName)) {
			DDMFormField ddmFormField = ddmStructure.getDDMFormField(fieldName);

			if (ddmFormField.isLocalizable()) {
				setDDMFormFieldValueLocalizedValue(
					ddmFormFieldValue, ddmFields.get(fieldName),
					ddmFieldsCounter.get(fieldName));
			}
			else {
				setDDMFormFieldValueUnlocalizedValue(
					ddmFormFieldValue, ddmFields.get(fieldName),
					ddmFieldsCounter.get(fieldName));
			}
		}

		ddmFieldsCounter.incrementKey(fieldName);
	}

	protected void setNestedDDMFormFieldValues(
			DDMFormFieldValue ddmFormFieldValue, DDMStructure ddmStructure,
			Fields ddmFields, DDMFieldsCounter ddmFieldsCounter)
		throws PortalException {

		String fieldName = ddmFormFieldValue.getName();

		int parentOffset = ddmFieldsCounter.get(fieldName);

		List<String> nestedFieldNames = ddmStructure.getChildrenFieldNames(
			fieldName);

		for (String nestedFieldName : nestedFieldNames) {
			int repetitions = countDDMFieldRepetitions(
				ddmFields, nestedFieldName, fieldName, parentOffset);

			for (int i = 0; i < repetitions; i++) {
				DDMFormFieldValue nestedDDMFormFieldValue =
					createDDMFormFieldValue(nestedFieldName);

				setDDMFormFieldValueProperties(
					nestedDDMFormFieldValue, ddmStructure, ddmFields,
					ddmFieldsCounter);

				ddmFormFieldValue.addNestedDDMFormFieldValue(
					nestedDDMFormFieldValue);
			}
		}
	}

	protected String[] splitFieldsDisplayValue(Field fieldsDisplayField) {
		String value = (String)fieldsDisplayField.getValue();

		return StringUtil.split(value);
	}

}