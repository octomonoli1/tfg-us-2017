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

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeServicesTracker;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeSettings;
import com.liferay.dynamic.data.mapping.io.internal.DDMFormJSONDeserializerImpl;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldRule;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldRuleType;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
import com.liferay.dynamic.data.mapping.test.util.DDMFormFieldTypeSettingsTestUtil;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ReflectionUtil;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * @author Marcellus Tavares
 */
public class DDMFormJSONDeserializerTest
	extends BaseDDMFormDeserializerTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setUpDDMFormJSONDeserializer();
	}

	@Override
	protected DDMForm deserialize(String serializedDDMForm)
		throws PortalException {

		return _ddmFormJSONDeserializer.deserialize(serializedDDMForm);
	}

	@Override
	protected String getDeserializerType() {
		return "json";
	}

	protected DDMFormFieldTypeServicesTracker
		getMockedDDMFormFieldTypeServicesTracker() {

		setUpDefaultDDMFormFieldType();

		DDMFormFieldTypeServicesTracker ddmFormFieldTypeServicesTracker = mock(
			DDMFormFieldTypeServicesTracker.class);

		DDMFormFieldRenderer ddmFormFieldRenderer = mock(
			DDMFormFieldRenderer.class);

		when(
			ddmFormFieldTypeServicesTracker.getDDMFormFieldRenderer(
				Matchers.anyString())
		).thenReturn(
			ddmFormFieldRenderer
		);

		when(
			ddmFormFieldTypeServicesTracker.getDDMFormFieldType(
				Matchers.anyString())
		).thenReturn(
			_defaultDDMFormFieldType
		);

		Map<String, Object> properties = new HashMap<>();

		properties.put("ddm.form.field.type.icon", "my-icon");
		properties.put(
			"ddm.form.field.type.js.class.name", "myJavaScriptClass");
		properties.put("ddm.form.field.type.js.module", "myJavaScriptModule");

		when(
			ddmFormFieldTypeServicesTracker.getDDMFormFieldTypeProperties(
				Matchers.anyString())
		).thenReturn(
			properties
		);

		return ddmFormFieldTypeServicesTracker;
	}

	@Override
	protected String getTestFileExtension() {
		return ".json";
	}

	protected void setUpDDMFormJSONDeserializer() throws Exception {

		// DDM form field type services tracker

		Field field = ReflectionUtil.getDeclaredField(
			DDMFormJSONDeserializerImpl.class,
			"_ddmFormFieldTypeServicesTracker");

		field.set(
			_ddmFormJSONDeserializer,
			getMockedDDMFormFieldTypeServicesTracker());

		// JSON factory

		field = ReflectionUtil.getDeclaredField(
			DDMFormJSONDeserializerImpl.class, "_jsonFactory");

		field.set(_ddmFormJSONDeserializer, new JSONFactoryImpl());
	}

	protected void setUpDefaultDDMFormFieldType() {
		when(
			_defaultDDMFormFieldType.getDDMFormFieldTypeSettings()
		).then(
			new Answer<Class<? extends DDMFormFieldTypeSettings>>() {

				@Override
				public Class<? extends DDMFormFieldTypeSettings> answer(
						InvocationOnMock invocationOnMock)
					throws Throwable {

					return DDMFormFieldTypeSettingsTestUtil.getSettings();
				}

			}
		);
	}

	@Override
	protected void testBooleanDDMFormField(DDMFormField ddmFormField) {
		super.testBooleanDDMFormField(ddmFormField);

		DDMFormFieldValidation ddmFormFieldValidation =
			ddmFormField.getDDMFormFieldValidation();

		Assert.assertNotNull(ddmFormFieldValidation);
		Assert.assertEquals(
			"Boolean2282", ddmFormFieldValidation.getExpression());
		Assert.assertEquals(
			"You must check this box to continue.",
			ddmFormFieldValidation.getErrorMessage());
		Assert.assertEquals("true", ddmFormField.getVisibilityExpression());

		List<DDMFormFieldRule> ddmFormFieldRules =
			ddmFormField.getDDMFormFieldRules();

		Assert.assertEquals(2, ddmFormFieldRules.size());

		DDMFormFieldRule ddmFormFieldRule = ddmFormFieldRules.get(0);

		Assert.assertEquals("1 + 2 > 3", ddmFormFieldRule.getExpression());
		Assert.assertEquals(
			DDMFormFieldRuleType.VISIBILITY,
			ddmFormFieldRule.getDDMFormFieldRuleType());

		ddmFormFieldRule = ddmFormFieldRules.get(1);

		Assert.assertEquals(
			"isReadOnly(Date2510) && isVisible(Decimal3479)",
			ddmFormFieldRule.getExpression());
		Assert.assertEquals(
			DDMFormFieldRuleType.READ_ONLY,
			ddmFormFieldRule.getDDMFormFieldRuleType());
	}

	@Override
	protected void testDecimalDDMFormField(DDMFormField ddmFormField) {
		super.testDecimalDDMFormField(ddmFormField);

		Assert.assertEquals("false", ddmFormField.getVisibilityExpression());
	}

	private final DDMFormJSONDeserializer _ddmFormJSONDeserializer =
		new DDMFormJSONDeserializerImpl();

	@Mock
	private DDMFormFieldType _defaultDDMFormFieldType;

}