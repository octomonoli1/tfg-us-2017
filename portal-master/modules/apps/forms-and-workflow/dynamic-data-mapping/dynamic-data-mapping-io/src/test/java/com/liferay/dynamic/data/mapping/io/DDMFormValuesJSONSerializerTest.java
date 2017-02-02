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

package com.liferay.dynamic.data.mapping.io;

import com.liferay.dynamic.data.mapping.io.internal.DDMFormValuesJSONSerializerImpl;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.test.util.DDMFormValuesTestUtil;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONObjectUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.skyscreamer.jsonassert.JSONAssert;

/**
 * @author Marcellus Tavares
 */
public class DDMFormValuesJSONSerializerTest extends BaseDDMTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setUpDDMFormValuesJSONSerializer();
	}

	@Test
	public void testFormValuesSerialization() throws Exception {
		String expectedJSON = read(
			"ddm-form-values-json-serializer-test-data.json");

		DDMFormValues ddmFormValues = createDDMFormValues();

		String actualJSON = _ddmFormValuesJSONSerializer.serialize(
			ddmFormValues);

		JSONAssert.assertEquals(expectedJSON, actualJSON, false);
	}

	protected DDMFormFieldValue createBooleanDDMFormFieldValue() {
		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setInstanceId("njar");
		ddmFormFieldValue.setName("Boolean2282");
		ddmFormFieldValue.setNestedDDMFormFields(
			createBooleanNestedDDMFormFieldValues());
		ddmFormFieldValue.setValue(createBooleanValue());

		return ddmFormFieldValue;
	}

	protected List<DDMFormFieldValue> createBooleanNestedDDMFormFieldValues() {
		List<DDMFormFieldValue> ddmFormFieldValues = new ArrayList<>();

		ddmFormFieldValues.add(createHTMLDDMFormFieldValue(0, "nabr"));
		ddmFormFieldValues.add(createHTMLDDMFormFieldValue(1, "uwyg"));

		return ddmFormFieldValues;
	}

	protected Value createBooleanValue() {
		Value value = new LocalizedValue();

		value.addString(LocaleUtil.US, "false");
		value.addString(LocaleUtil.BRAZIL, "true");

		return value;
	}

	protected List<DDMFormFieldValue> createDDMFormFieldValues() {
		List<DDMFormFieldValue> ddmFormFieldValues = new ArrayList<>();

		ddmFormFieldValues.addAll(createSeparatorDDMFormFieldValues());
		ddmFormFieldValues.add(createTextDDMFormFieldValue());
		ddmFormFieldValues.addAll(createImageDDMFormFieldValues());
		ddmFormFieldValues.add(createBooleanDDMFormFieldValue());

		return ddmFormFieldValues;
	}

	protected DDMFormValues createDDMFormValues() {
		DDMFormValues ddmFormValues = new DDMFormValues(null);

		ddmFormValues.setAvailableLocales(
			DDMFormValuesTestUtil.createAvailableLocales(
				LocaleUtil.BRAZIL, LocaleUtil.US));
		ddmFormValues.setDDMFormFieldValues(createDDMFormFieldValues());
		ddmFormValues.setDefaultLocale(LocaleUtil.US);

		return ddmFormValues;
	}

	protected DDMFormFieldValue createHTMLDDMFormFieldValue(
		int index, String instanceId) {

		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setInstanceId(instanceId);
		ddmFormFieldValue.setName("HTML4512");
		ddmFormFieldValue.setValue(createHTMLValue(index));

		return ddmFormFieldValue;
	}

	protected Value createHTMLValue(int index) {
		Value value = new LocalizedValue();

		value.addString(LocaleUtil.US, "<p>This is a test. " + index + "</p>");
		value.addString(
			LocaleUtil.BRAZIL, "<p>Isto e um teste. " + index + "</p>");

		return value;
	}

	protected DDMFormFieldValue createImageDDMFormFieldValue(
		int index, String instanceId) {

		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setInstanceId(instanceId);
		ddmFormFieldValue.setName("Image4751");
		ddmFormFieldValue.setValue(createImageValue(index));

		return ddmFormFieldValue;
	}

	protected List<DDMFormFieldValue> createImageDDMFormFieldValues() {
		List<DDMFormFieldValue> imageDDMFormFieldValues = new ArrayList<>();

		imageDDMFormFieldValues.add(createImageDDMFormFieldValue(0, "uaht"));
		imageDDMFormFieldValues.add(createImageDDMFormFieldValue(1, "pppj"));
		imageDDMFormFieldValues.add(createImageDDMFormFieldValue(2, "nmab"));

		return imageDDMFormFieldValues;
	}

	protected Value createImageValue(int index) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("alt", "This is a image description. " + index);
		jsonObject.put("data", "base64Value" + index);

		return new UnlocalizedValue(
			JSONObjectUtil.toOrderedJSONString(jsonObject));
	}

	protected DDMFormFieldValue createSeparatorDDMFormFieldValue(
		int index, String instanceId) {

		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setInstanceId(instanceId);
		ddmFormFieldValue.setName("Separator7211");
		ddmFormFieldValue.setNestedDDMFormFields(
			createSeparatorNestedDDMFormFieldValues(index, "xyz" + index));

		return ddmFormFieldValue;
	}

	protected List<DDMFormFieldValue> createSeparatorDDMFormFieldValues() {
		List<DDMFormFieldValue> separatorDDMFormFieldValues = new ArrayList<>();

		separatorDDMFormFieldValues.add(
			createSeparatorDDMFormFieldValue(0, "uayx"));

		separatorDDMFormFieldValues.add(
			createSeparatorDDMFormFieldValue(1, "lahy"));

		return separatorDDMFormFieldValues;
	}

	protected List<DDMFormFieldValue> createSeparatorNestedDDMFormFieldValues(
		int index, String instanceId) {

		List<DDMFormFieldValue> ddmFormFieldValues = new ArrayList<>();

		ddmFormFieldValues.add(
			createTextBoxDDMFormFieldValue(index, instanceId));

		return ddmFormFieldValues;
	}

	protected DDMFormFieldValue createTextBoxDDMFormFieldValue(
		int index, String instanceId) {

		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setInstanceId(instanceId);
		ddmFormFieldValue.setName("Text_Box6748");
		ddmFormFieldValue.setValue(createTextBoxValue(index));

		return ddmFormFieldValue;
	}

	protected Value createTextBoxValue(int index) {
		Value value = new LocalizedValue();

		value.addString(LocaleUtil.US, "Content " + index);
		value.addString(LocaleUtil.BRAZIL, "Conteudo " + index);

		return value;
	}

	protected DDMFormFieldValue createTextDDMFormFieldValue() {
		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setInstanceId("baht");
		ddmFormFieldValue.setName("Text6513");
		ddmFormFieldValue.setValue(createTextValue());

		return ddmFormFieldValue;
	}

	protected Value createTextValue() {
		Value value = new LocalizedValue();

		value.addString(LocaleUtil.US, "Text");
		value.addString(LocaleUtil.BRAZIL, "Texto");

		return value;
	}

	protected void setUpDDMFormValuesJSONSerializer() throws Exception {
		field(
			DDMFormValuesJSONSerializerImpl.class, "_jsonFactory"
		).set(
			_ddmFormValuesJSONSerializer, new JSONFactoryImpl()
		);
	}

	private final DDMFormValuesJSONSerializer _ddmFormValuesJSONSerializer =
		new DDMFormValuesJSONSerializerImpl();

}