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

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.FieldConstants;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.dynamic.data.mapping.util.DDMFormValuesToFieldsConverter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true)
public class DDMFormValuesToFieldsConverterImpl
	implements DDMFormValuesToFieldsConverter {

	@Override
	public Fields convert(
			DDMStructure ddmStructure, DDMFormValues ddmFormValues)
		throws PortalException {

		Fields ddmFields = createDDMFields(ddmStructure);

		Locale defaultLocale = ddmFormValues.getDefaultLocale();

		for (DDMFormFieldValue ddmFormFieldValue :
				ddmFormValues.getDDMFormFieldValues()) {

			addDDMFields(
				ddmStructure, ddmFormFieldValue, ddmFields, defaultLocale);
		}

		return ddmFields;
	}

	protected void addDDMField(
			DDMStructure ddmStructure, DDMFormFieldValue ddmFormFieldValue,
			Fields ddmFields, Locale defaultLocale)
		throws PortalException {

		String fieldName = ddmFormFieldValue.getName();

		if (!ddmStructure.hasField(fieldName) ||
			ddmStructure.isFieldTransient(fieldName)) {

			return;
		}

		Field ddmField = createDDMField(
			ddmStructure, ddmFormFieldValue, defaultLocale);

		Field existingDDMField = ddmFields.get(ddmField.getName());

		if (existingDDMField == null) {
			ddmFields.put(ddmField);
		}
		else {
			addDDMFieldValues(existingDDMField, ddmField);
		}
	}

	protected void addDDMFields(
			DDMStructure ddmStructure, DDMFormFieldValue ddmFormFieldValue,
			Fields ddmFields, Locale defaultLocale)
		throws PortalException {

		addDDMField(ddmStructure, ddmFormFieldValue, ddmFields, defaultLocale);

		addFieldDisplayValue(
			ddmFields.get(DDMImpl.FIELDS_DISPLAY_NAME),
			getFieldDisplayValue(ddmFormFieldValue));

		for (DDMFormFieldValue nestedDDMFormFieldValue :
				ddmFormFieldValue.getNestedDDMFormFieldValues()) {

			addDDMFields(
				ddmStructure, nestedDDMFormFieldValue, ddmFields,
				defaultLocale);
		}
	}

	protected void addDDMFieldValues(
		Field existingDDMField, Field newDDMField) {

		for (Locale availableLocale : newDDMField.getAvailableLocales()) {
			existingDDMField.addValues(
				availableLocale, newDDMField.getValues(availableLocale));
		}
	}

	protected void addFieldDisplayValue(
		Field ddmFieldsDisplayField, String fieldDisplayValue) {

		String[] fieldsDisplayValues = StringUtil.split(
			(String)ddmFieldsDisplayField.getValue());

		fieldsDisplayValues = ArrayUtil.append(
			fieldsDisplayValues, fieldDisplayValue);

		ddmFieldsDisplayField.setValue(StringUtil.merge(fieldsDisplayValues));
	}

	protected Field createDDMField(
			DDMStructure ddmStructure, DDMFormFieldValue ddmFormFieldValue,
			Locale defaultLocale)
		throws PortalException {

		Field ddmField = new Field();

		ddmField.setDDMStructureId(ddmStructure.getStructureId());
		ddmField.setDefaultLocale(defaultLocale);
		ddmField.setName(ddmFormFieldValue.getName());

		String type = ddmStructure.getFieldDataType(
			ddmFormFieldValue.getName());

		setDDMFieldValue(
			ddmField, type, ddmFormFieldValue.getValue(), defaultLocale);

		return ddmField;
	}

	protected Fields createDDMFields(DDMStructure ddmStructure) {
		Fields ddmFields = new Fields();

		Field fieldsDisplayField = new Field(
			ddmStructure.getStructureId(), DDMImpl.FIELDS_DISPLAY_NAME,
			StringPool.BLANK);

		ddmFields.put(fieldsDisplayField);

		return ddmFields;
	}

	protected String getFieldDisplayValue(DDMFormFieldValue ddmFormFieldValue) {
		String fieldName = ddmFormFieldValue.getName();

		String instanceId = ddmFormFieldValue.getInstanceId();

		return fieldName.concat(DDMImpl.INSTANCE_SEPARATOR).concat(instanceId);
	}

	protected void setDDMFieldLocalizedValue(
		Field ddmField, String type, Value value) {

		for (Locale availableLocales : value.getAvailableLocales()) {
			Serializable serializable = FieldConstants.getSerializable(
				type, value.getString(availableLocales));

			ddmField.addValue(availableLocales, serializable);
		}
	}

	protected void setDDMFieldUnlocalizedValue(
		Field ddmField, String type, Value value, Locale defaultLocale) {

		Serializable serializable = FieldConstants.getSerializable(
			type, value.getString(LocaleUtil.ROOT));

		ddmField.addValue(defaultLocale, serializable);
	}

	protected void setDDMFieldValue(
		Field ddmField, String type, Value value, Locale defaultLocale) {

		if (value.isLocalized()) {
			setDDMFieldLocalizedValue(ddmField, type, value);
		}
		else {
			setDDMFieldUnlocalizedValue(ddmField, type, value, defaultLocale);
		}
	}

}