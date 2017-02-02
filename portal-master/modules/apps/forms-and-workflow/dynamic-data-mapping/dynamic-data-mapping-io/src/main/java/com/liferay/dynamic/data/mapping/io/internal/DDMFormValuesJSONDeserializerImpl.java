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

package com.liferay.dynamic.data.mapping.io.internal;

import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONDeserializer;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true)
public class DDMFormValuesJSONDeserializerImpl
	implements DDMFormValuesJSONDeserializer {

	@Override
	public DDMFormValues deserialize(
			DDMForm ddmForm, String serializedDDMFormValues)
		throws PortalException {

		try {
			JSONObject jsonObject = _jsonFactory.createJSONObject(
				serializedDDMFormValues);

			DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);

			setDDMFormValuesAvailableLocales(
				jsonObject.getJSONArray("availableLanguageIds"), ddmFormValues);
			setDDMFormValuesDefaultLocale(
				jsonObject.getString("defaultLanguageId"), ddmFormValues);
			setDDMFormFieldValues(
				jsonObject.getJSONArray("fieldValues"), ddmFormValues);
			setDDMFormLocalizedValuesDefaultLocale(ddmFormValues);

			return ddmFormValues;
		}
		catch (JSONException jsone) {
			throw new PortalException(jsone);
		}
	}

	protected Set<Locale> getAvailableLocales(JSONArray jsonArray) {
		Set<Locale> availableLocales = new HashSet<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			Locale availableLocale = LocaleUtil.fromLanguageId(
				jsonArray.getString(i));

			availableLocales.add(availableLocale);
		}

		return availableLocales;
	}

	protected DDMFormFieldValue getDDMFormFieldValue(JSONObject jsonObject) {
		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setInstanceId(jsonObject.getString("instanceId"));
		ddmFormFieldValue.setName(jsonObject.getString("name"));

		setDDMFormFieldValueValue(jsonObject, ddmFormFieldValue);

		setNestedDDMFormFieldValues(
			jsonObject.getJSONArray("nestedFieldValues"), ddmFormFieldValue);

		return ddmFormFieldValue;
	}

	protected List<DDMFormFieldValue> getDDMFormFieldValues(
		JSONArray jsonArray) {

		List<DDMFormFieldValue> ddmFormFieldValues = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDMFormFieldValue ddmFormFieldValue = getDDMFormFieldValue(
				jsonArray.getJSONObject(i));

			ddmFormFieldValues.add(ddmFormFieldValue);
		}

		return ddmFormFieldValues;
	}

	protected LocalizedValue getLocalizedValue(JSONObject jsonObject) {
		LocalizedValue localizedValue = new LocalizedValue();

		Iterator<String> itr = jsonObject.keys();

		while (itr.hasNext()) {
			String languageId = itr.next();

			localizedValue.addString(
				LocaleUtil.fromLanguageId(languageId),
				jsonObject.getString(languageId));
		}

		return localizedValue;
	}

	protected Value getValue(JSONObject jsonObject) {
		JSONObject valueJSONObject = jsonObject.getJSONObject("value");

		if (isLocalized(valueJSONObject)) {
			return getLocalizedValue(valueJSONObject);
		}

		return new UnlocalizedValue(jsonObject.getString("value"));
	}

	protected boolean isLocalized(JSONObject jsonObject) {
		if (jsonObject == null) {
			return false;
		}

		Iterator<String> keys = jsonObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			if (!LanguageUtil.isAvailableLocale(key)) {
				return false;
			}
		}

		return true;
	}

	protected void setDDMFormFieldValueLocalizedValueDefaultLocale(
		DDMFormFieldValue ddmFormFieldValue, Locale defaultLocale) {

		Value value = ddmFormFieldValue.getValue();

		if ((value != null) && value.isLocalized()) {
			value.setDefaultLocale(defaultLocale);
		}

		for (DDMFormFieldValue nestedDDMFormFieldValue :
				ddmFormFieldValue.getNestedDDMFormFieldValues()) {

			setDDMFormFieldValueLocalizedValueDefaultLocale(
				nestedDDMFormFieldValue, defaultLocale);
		}
	}

	protected void setDDMFormFieldValues(
		JSONArray jsonArray, DDMFormValues ddmFormValues) {

		List<DDMFormFieldValue> ddmFormFieldValues = getDDMFormFieldValues(
			jsonArray);

		ddmFormValues.setDDMFormFieldValues(ddmFormFieldValues);
	}

	protected void setDDMFormFieldValueValue(
		JSONObject jsonObject, DDMFormFieldValue ddmFormFieldValue) {

		String valueString = jsonObject.getString("value", null);

		if (valueString == null) {
			return;
		}

		Value value = getValue(jsonObject);

		ddmFormFieldValue.setValue(value);
	}

	protected void setDDMFormLocalizedValuesDefaultLocale(
		DDMFormValues ddmFormValues) {

		for (DDMFormFieldValue ddmFormFieldValue :
				ddmFormValues.getDDMFormFieldValues()) {

			setDDMFormFieldValueLocalizedValueDefaultLocale(
				ddmFormFieldValue, ddmFormValues.getDefaultLocale());
		}
	}

	protected void setDDMFormValuesAvailableLocales(
		JSONArray jsonArray, DDMFormValues ddmFormValues) {

		Set<Locale> availableLocales = getAvailableLocales(jsonArray);

		ddmFormValues.setAvailableLocales(availableLocales);
	}

	protected void setDDMFormValuesDefaultLocale(
		String defaultLanguageId, DDMFormValues ddmFormValues) {

		Locale defaultLocale = LocaleUtil.fromLanguageId(defaultLanguageId);

		ddmFormValues.setDefaultLocale(defaultLocale);

		Set<Locale> availableLocales = ddmFormValues.getAvailableLocales();

		if ((availableLocales != null) &&
			!availableLocales.contains(defaultLocale)) {

			availableLocales.add(defaultLocale);
		}
	}

	@Reference(unbind = "-")
	protected void setJSONFactory(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	protected void setNestedDDMFormFieldValues(
		JSONArray jsonArray, DDMFormFieldValue ddmFormFieldValue) {

		if ((jsonArray == null) || (jsonArray.length() == 0)) {
			return;
		}

		List<DDMFormFieldValue> nestedDDMFormFieldValues =
			getDDMFormFieldValues(jsonArray);

		ddmFormFieldValue.setNestedDDMFormFields(nestedDDMFormFieldValues);
	}

	private JSONFactory _jsonFactory;

}