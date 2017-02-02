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

package com.liferay.dynamic.data.mapping.util;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.AggregateResourceBundle;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public class DDMFormFieldFactoryHelper {

	public DDMFormFieldFactoryHelper(Method method) {
		_method = method;
		_ddmFormField = method.getAnnotation(DDMFormField.class);
	}

	public com.liferay.dynamic.data.mapping.model.DDMFormField
		createDDMFormField() {

		String name = getDDMFormFieldName();
		String type = getDDMFormFieldType();

		com.liferay.dynamic.data.mapping.model.DDMFormField ddmFormField =
			new com.liferay.dynamic.data.mapping.model.DDMFormField(name, type);

		Map<String, Object> properties = getProperties();

		for (Map.Entry<String, Object> entry : properties.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (isLocalizableValue((String)value)) {
				value = getPropertyValue(value);
			}

			ddmFormField.setProperty(key, value);
		}

		ddmFormField.setDataType(getDDMFormFieldDataType());
		ddmFormField.setDDMFormFieldOptions(getDDMFormFieldOptions());
		ddmFormField.setDDMFormFieldValidation(getDDMFormFieldValidation());
		ddmFormField.setLabel(getDDMFormFieldLabel());
		ddmFormField.setLocalizable(isDDMFormFieldLocalizable());
		ddmFormField.setPredefinedValue(getDDMFormFieldPredefinedValue());
		ddmFormField.setRepeatable(isDDMFormFieldRepeatable());
		ddmFormField.setRequired(isDDMFormFieldRequired());
		ddmFormField.setTip(getDDMFormFieldTip());
		ddmFormField.setVisibilityExpression(
			getDDMFormFieldVisibilityExpression());

		return ddmFormField;
	}

	protected void collectResourceBundles(
		Class<?> clazz, List<ResourceBundle> resourceBundles, Locale locale) {

		for (Class<?> interfaceClass : clazz.getInterfaces()) {
			collectResourceBundles(interfaceClass, resourceBundles, locale);
		}

		String resourceBundleBaseName = getResourceBundleBaseName(clazz);

		if (Validator.isNull(resourceBundleBaseName)) {
			return;
		}

		try {
			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				resourceBundleBaseName, locale, clazz.getClassLoader());

			if (resourceBundle != null) {
				resourceBundles.add(resourceBundle);
			}
		}
		catch (MissingResourceException mre) {
		}
	}

	protected LocalizedValue createLocalizedValue(String property) {
		LocalizedValue localizedValue = new LocalizedValue(_defaultLocale);

		if (Validator.isNull(property)) {
			return localizedValue;
		}

		if (isLocalizableValue(property)) {
			String languageKey = extractLanguageKey(property);

			for (Locale availableLocale : _availableLocales) {
				localizedValue.addString(
					availableLocale,
					getLocalizedValue(availableLocale, languageKey));
			}
		}
		else {
			localizedValue.addString(_defaultLocale, property);
		}

		return localizedValue;
	}

	protected String extractLanguageKey(String value) {
		return StringUtil.extractLast(value, StringPool.PERCENT);
	}

	protected String getDDMFormFieldDataType() {
		if (Validator.isNotNull(_ddmFormField.dataType())) {
			return _ddmFormField.dataType();
		}

		Class<?> returnType = _method.getReturnType();

		if (returnType.isArray()) {
			returnType = returnType.getComponentType();
		}

		if (returnType.isAssignableFrom(boolean.class) ||
			returnType.isAssignableFrom(Boolean.class)) {

			return "boolean";
		}
		else if (returnType.isAssignableFrom(double.class) ||
				 returnType.isAssignableFrom(Double.class)) {

			return "double";
		}
		else if (returnType.isAssignableFrom(float.class) ||
				 returnType.isAssignableFrom(Float.class)) {

			return "float";
		}
		else if (returnType.isAssignableFrom(int.class) ||
				 returnType.isAssignableFrom(Integer.class)) {

			return "integer";
		}
		else if (returnType.isAssignableFrom(long.class) ||
				 returnType.isAssignableFrom(Long.class)) {

			return "long";
		}
		else if (returnType.isAssignableFrom(short.class) ||
				 returnType.isAssignableFrom(Short.class)) {

			return "short";
		}
		else {
			return "string";
		}
	}

	protected LocalizedValue getDDMFormFieldLabel() {
		return createLocalizedValue(_ddmFormField.label());
	}

	protected String getDDMFormFieldName() {
		if (Validator.isNotNull(_ddmFormField.name())) {
			return _ddmFormField.name();
		}

		return _method.getName();
	}

	protected DDMFormFieldOptions getDDMFormFieldOptions() {
		DDMFormFieldOptions ddmFormFieldOptions = new DDMFormFieldOptions();

		ddmFormFieldOptions.setDefaultLocale(_defaultLocale);

		String[] optionLabels = _ddmFormField.optionLabels();
		String[] optionValues = _ddmFormField.optionValues();

		if (ArrayUtil.isEmpty(optionLabels) ||
			ArrayUtil.isEmpty(optionValues)) {

			return ddmFormFieldOptions;
		}

		for (int i = 0; i < optionLabels.length; i++) {
			String optionLabel = optionLabels[i];

			if (isLocalizableValue(optionLabel)) {
				String languageKey = extractLanguageKey(optionLabel);

				ddmFormFieldOptions.addOptionLabel(
					optionValues[i], _defaultLocale,
					getLocalizedValue(_defaultLocale, languageKey));
			}
			else {
				ddmFormFieldOptions.addOptionLabel(
					optionValues[i], _defaultLocale, optionLabel);
			}
		}

		return ddmFormFieldOptions;
	}

	protected LocalizedValue getDDMFormFieldPredefinedValue() {
		LocalizedValue localizedValue = new LocalizedValue(_defaultLocale);

		String predefinedValue = _ddmFormField.predefinedValue();

		String fieldType = getDDMFormFieldType();

		if (Validator.isNotNull(predefinedValue)) {
			localizedValue.addString(_defaultLocale, predefinedValue);
		}
		else if (fieldType.equals("checkbox")) {
			localizedValue.addString(_defaultLocale, Boolean.FALSE.toString());
		}

		return localizedValue;
	}

	protected LocalizedValue getDDMFormFieldTip() {
		return createLocalizedValue(_ddmFormField.tip());
	}

	protected String getDDMFormFieldType() {
		if (Validator.isNotNull(_ddmFormField.type())) {
			return _ddmFormField.type();
		}

		Class<?> returnType = _method.getReturnType();

		if (returnType.isAssignableFrom(boolean.class) ||
			returnType.isAssignableFrom(Boolean.class)) {

			return "checkbox";
		}

		return "text";
	}

	protected DDMFormFieldValidation getDDMFormFieldValidation() {
		DDMFormFieldValidation ddmFormFieldValidation =
			new DDMFormFieldValidation();

		if (Validator.isNotNull(_ddmFormField.validationExpression())) {
			ddmFormFieldValidation.setExpression(
				_ddmFormField.validationExpression());
		}

		if (Validator.isNotNull(_ddmFormField.validationErrorMessage())) {
			String validationErrorMessage =
				_ddmFormField.validationErrorMessage();

			if (isLocalizableValue(validationErrorMessage)) {
				String languageKey = extractLanguageKey(validationErrorMessage);

				validationErrorMessage = getLocalizedValue(
					_defaultLocale, languageKey);
			}

			ddmFormFieldValidation.setErrorMessage(validationErrorMessage);
		}

		return ddmFormFieldValidation;
	}

	protected String getDDMFormFieldVisibilityExpression() {
		if (Validator.isNotNull(_ddmFormField.visibilityExpression())) {
			return _ddmFormField.visibilityExpression();
		}

		return StringUtil.toUpperCase(StringPool.TRUE);
	}

	protected String getLocalizedValue(Locale locale, String value) {
		ResourceBundle resourceBundle = getResourceBundle(locale);

		return LanguageUtil.get(resourceBundle, value);
	}

	protected Map<String, Object> getProperties() {
		Map<String, Object> propertiesMap = new HashMap<>();

		for (String property : _ddmFormField.properties()) {
			String key = StringUtil.extractFirst(property, StringPool.EQUAL);
			String value = StringUtil.extractLast(property, StringPool.EQUAL);

			propertiesMap.put(key, value);
		}

		return propertiesMap;
	}

	protected LocalizedValue getPropertyValue(Object value) {
		LocalizedValue localizedValue = new LocalizedValue(_defaultLocale);

		if (Validator.isNull(value)) {
			return localizedValue;
		}

		String valueString = (String)value;

		if (isLocalizableValue(valueString)) {
			String languageKey = extractLanguageKey(valueString);

			localizedValue.addString(
				_defaultLocale, getLocalizedValue(_defaultLocale, languageKey));
		}
		else {
			localizedValue.addString(_defaultLocale, valueString);
		}

		return localizedValue;
	}

	protected ResourceBundle getResourceBundle(Locale locale) {
		List<ResourceBundle> resourceBundles = new ArrayList<>();

		ResourceBundle portalResourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, PortalClassLoaderUtil.getClassLoader());

		resourceBundles.add(portalResourceBundle);

		collectResourceBundles(
			_method.getDeclaringClass(), resourceBundles, locale);

		ResourceBundle[] resourceBundlesArray = resourceBundles.toArray(
			new ResourceBundle[resourceBundles.size()]);

		return new AggregateResourceBundle(resourceBundlesArray);
	}

	protected String getResourceBundleBaseName(Class<?> clazz) {
		if (!clazz.isAnnotationPresent(DDMForm.class)) {
			return null;
		}

		DDMForm ddmForm = clazz.getAnnotation(DDMForm.class);

		if (Validator.isNotNull(ddmForm.localization())) {
			return ddmForm.localization();
		}

		return "content.Language";
	}

	protected boolean isDDMFormFieldLocalizable() {
		Class<?> returnType = _method.getReturnType();

		if (returnType.isAssignableFrom(LocalizedValue.class)) {
			return true;
		}

		return false;
	}

	protected boolean isDDMFormFieldRepeatable() {
		Class<?> returnType = _method.getReturnType();

		if (returnType.isArray()) {
			return true;
		}

		return false;
	}

	protected boolean isDDMFormFieldRequired() {
		return _ddmFormField.required();
	}

	protected boolean isLocalizableValue(String value) {
		if (StringUtil.startsWith(value, StringPool.PERCENT)) {
			return true;
		}

		return false;
	}

	protected void setAvailableLocales(Set<Locale> availableLocales) {
		_availableLocales = availableLocales;
	}

	protected void setDefaultLocale(Locale defaultLocale) {
		_defaultLocale = defaultLocale;
	}

	private Set<Locale> _availableLocales;
	private final DDMFormField _ddmFormField;
	private Locale _defaultLocale;
	private final Method _method;

}