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

package com.liferay.dynamic.data.mapping.type.radio.internal;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueRenderer;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Renato Rego
 */
@Component(immediate = true, property = {"ddm.form.field.type.name=radio"})
public class RadioDDMFormFieldValueRenderer
	implements DDMFormFieldValueRenderer {

	@Override
	public String render(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		String optionValue = radioDDMFormFieldValueAccessor.getValue(
			ddmFormFieldValue, locale);

		DDMFormFieldOptions ddmFormFieldOptions = getDDMFormFieldOptions(
			ddmFormFieldValue);

		LocalizedValue optionLabel = ddmFormFieldOptions.getOptionLabels(
			optionValue);

		if (optionLabel == null) {
			return StringPool.BLANK;
		}

		return optionLabel.getString(locale);
	}

	protected DDMFormFieldOptions getDDMFormFieldOptions(
		DDMFormFieldValue ddmFormFieldValue) {

		DDMFormField ddmFormField = ddmFormFieldValue.getDDMFormField();

		return ddmFormField.getDDMFormFieldOptions();
	}

	@Reference
	protected RadioDDMFormFieldValueAccessor radioDDMFormFieldValueAccessor;

}