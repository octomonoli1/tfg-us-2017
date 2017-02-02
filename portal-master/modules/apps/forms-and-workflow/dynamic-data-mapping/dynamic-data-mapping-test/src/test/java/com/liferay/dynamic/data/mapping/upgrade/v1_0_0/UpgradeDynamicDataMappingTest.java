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

package com.liferay.dynamic.data.mapping.upgrade.v1_0_0;

import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONSerializer;
import com.liferay.dynamic.data.mapping.io.internal.DDMFormValuesJSONDeserializerImpl;
import com.liferay.dynamic.data.mapping.io.internal.DDMFormValuesJSONSerializerImpl;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.test.util.DDMFormTestUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMFormValuesTestUtil;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.exception.NoSuchResourceActionException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.security.xml.SecureXMLFactoryProviderUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.UnsecureSAXReaderUtil;
import com.liferay.portal.security.xml.SecureXMLFactoryProviderImpl;
import com.liferay.portal.util.LocalizationImpl;
import com.liferay.portal.xml.SAXReaderImpl;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.skyscreamer.jsonassert.JSONAssert;

/**
 * @author Marcellus Tavares
 */
@PowerMockIgnore("javax.xml.stream.*")
@PrepareForTest(LocaleUtil.class)
@RunWith(PowerMockRunner.class)
public class UpgradeDynamicDataMappingTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		setUpDDMFormValuesJSONDeserializer();
		setUpDDMFormValuesJSONSerializer();
		setUpLanguageUtil();
		setUpLocaleUtil();
		setUpLocalizationUtil();
		setUpPropsUtil();
		setUpSecureXMLFactoryProviderUtil();
		setUpSAXReaderUtil();
		setUpJSONFactoryUtil();

		_upgradeDynamicDataMapping = new UpgradeDynamicDataMapping(
			null, null, null, null, null, _ddmFormValuesJSONDeserializer,
			_ddmFormValuesJSONSerializer, null, null, null, null, null, null,
			null, new TestResourceActions(), null, null);
	}

	@Test(expected = UpgradeException.class)
	public void testCreateNewFieldNameWithConflictingNewFieldName()
		throws Exception {

		Set<String> existingFieldNames = new HashSet<>();

		existingFieldNames.add("myna");

		_upgradeDynamicDataMapping.createNewDDMFormFieldName(
			"?my/--na", existingFieldNames);
	}

	@Test
	public void testCreateNewFieldNameWithSupportedOldFieldName()
		throws Exception {

		Set<String> existingFieldNames = Collections.<String>emptySet();

		Assert.assertEquals(
			"name",
			_upgradeDynamicDataMapping.createNewDDMFormFieldName(
				"name/?--", existingFieldNames));
		Assert.assertEquals(
			"firstName",
			_upgradeDynamicDataMapping.createNewDDMFormFieldName(
				"first Name", existingFieldNames));
		Assert.assertEquals(
			"this_is_a_field_name",
			_upgradeDynamicDataMapping.createNewDDMFormFieldName(
				"this?*&_is///_{{a[[  [_]  ~'field'////>_<name",
				existingFieldNames));
	}

	@Test(expected = UpgradeException.class)
	public void testCreateNewFieldNameWithUnsupportedOldFieldName()
		throws Exception {

		Set<String> existingFieldNames = Collections.<String>emptySet();

		_upgradeDynamicDataMapping.createNewDDMFormFieldName(
			"??????", existingFieldNames);
	}

	@Test
	public void testIsInvalidFieldName() {
		Assert.assertTrue(
			_upgradeDynamicDataMapping.isInvalidFieldName("/name?"));
		Assert.assertTrue(
			_upgradeDynamicDataMapping.isInvalidFieldName("_name--"));
		Assert.assertTrue(
			_upgradeDynamicDataMapping.isInvalidFieldName("name^*"));
		Assert.assertTrue(
			_upgradeDynamicDataMapping.isInvalidFieldName("name^*"));
		Assert.assertTrue(
			_upgradeDynamicDataMapping.isInvalidFieldName("my name"));
	}

	@Test
	public void testIsValidFieldName() {
		Assert.assertFalse(
			_upgradeDynamicDataMapping.isInvalidFieldName("name"));
		Assert.assertFalse(
			_upgradeDynamicDataMapping.isInvalidFieldName("name_"));
		Assert.assertFalse(
			_upgradeDynamicDataMapping.isInvalidFieldName("转注字"));
	}

	@Test
	public void testRenameInvalidDDMFormFieldNamesInJSON() throws Exception {
		long structureId = RandomTestUtil.randomLong();

		DDMForm ddmForm = DDMFormTestUtil.createDDMForm();

		DDMFormField ddmFormField = DDMFormTestUtil.createTextDDMFormField(
			"name", false, false, false);

		ddmFormField.setProperty("oldName", "name<!**>");

		ddmForm.addDDMFormField(ddmFormField);

		_upgradeDynamicDataMapping.populateStructureInvalidDDMFormFieldNamesMap(
			structureId, ddmForm);

		DDMFormValues ddmFormValues = DDMFormValuesTestUtil.createDDMFormValues(
			ddmForm);

		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"name<!**>", "Joe Bloggs"));

		String serializedDDMFormValues = _ddmFormValuesJSONSerializer.serialize(
			ddmFormValues);

		String updatedSerializedDDMFormValues =
			_upgradeDynamicDataMapping.renameInvalidDDMFormFieldNames(
				structureId, serializedDDMFormValues);

		DDMFormValues updatedDDMFormValues =
			_ddmFormValuesJSONDeserializer.deserialize(
				ddmForm, updatedSerializedDDMFormValues);

		List<DDMFormFieldValue> updatedDDMFormFieldValues =
			updatedDDMFormValues.getDDMFormFieldValues();

		Assert.assertEquals(1, updatedDDMFormFieldValues.size());

		DDMFormFieldValue updatedDDMFormFieldValue =
			updatedDDMFormFieldValues.get(0);

		Value value = updatedDDMFormFieldValue.getValue();

		Assert.assertEquals("name", updatedDDMFormFieldValue.getName());
		Assert.assertEquals("Joe Bloggs", value.getString(Locale.US));
	}

	@Test
	public void testRenameInvalidDDMFormFieldNamesInVMTemplate() {
		long structureId = RandomTestUtil.randomLong();

		DDMForm ddmForm = DDMFormTestUtil.createDDMForm();

		DDMFormField ddmFormField = DDMFormTestUtil.createTextDDMFormField(
			"name", false, false, false);

		ddmFormField.setProperty("oldName", "name*");

		ddmForm.addDDMFormField(ddmFormField);

		_upgradeDynamicDataMapping.populateStructureInvalidDDMFormFieldNamesMap(
			structureId, ddmForm);

		String updatedScript =
			_upgradeDynamicDataMapping.renameInvalidDDMFormFieldNames(
				structureId, "Hello $name*!");

		Assert.assertEquals("Hello $name!", updatedScript);
	}

	@Test
	public void testToJSONWithLocalizedAndNestedData() throws Exception {
		DDMForm ddmForm = new DDMForm();

		ddmForm.setAvailableLocales(createAvailableLocales(LocaleUtil.US));
		ddmForm.setDefaultLocale(LocaleUtil.US);

		DDMFormField textDDMFormField = new DDMFormField("Text", "text");

		textDDMFormField.setDataType("string");
		textDDMFormField.setLocalizable(true);
		textDDMFormField.setRepeatable(true);

		DDMFormField textAreaDDMFormField = new DDMFormField(
			"TextArea", "textarea");

		textAreaDDMFormField.setDataType("string");
		textAreaDDMFormField.setLocalizable(true);
		textAreaDDMFormField.setRepeatable(true);

		textDDMFormField.addNestedDDMFormField(textAreaDDMFormField);

		ddmForm.addDDMFormField(textDDMFormField);

		// DDM form values

		DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);

		ddmFormValues.setAvailableLocales(
			createAvailableLocales(LocaleUtil.BRAZIL, LocaleUtil.US));
		ddmFormValues.setDefaultLocale(LocaleUtil.US);

		DDMFormFieldValue text1DDMFormFieldValue = createDDMFormFieldValue(
			"srfa", "Text",
			createLocalizedValue(
				"En Text Value 1", "Pt Text Value 1", LocaleUtil.US));

		text1DDMFormFieldValue.addNestedDDMFormFieldValue(
			createDDMFormFieldValue(
				"elcy", "TextArea",
				createLocalizedValue(
					"En Text Area Value 1", "Pt Text Area Value 1",
					LocaleUtil.US)));
		text1DDMFormFieldValue.addNestedDDMFormFieldValue(
			createDDMFormFieldValue(
				"uxyj", "TextArea",
				createLocalizedValue(
					"En Text Area Value 2", "Pt Text Area Value 2",
					LocaleUtil.US)));

		ddmFormValues.addDDMFormFieldValue(text1DDMFormFieldValue);

		DDMFormFieldValue text2DDMFormFieldValue = createDDMFormFieldValue(
			"ealq", "Text",
			createLocalizedValue(
				"En Text Value 2", "Pt Text Value 2", LocaleUtil.US));

		text2DDMFormFieldValue.addNestedDDMFormFieldValue(
			createDDMFormFieldValue(
				"eepy", "TextArea",
				createLocalizedValue(
					"En Text Area Value 3", "Pt Text Area Value 3",
					LocaleUtil.US)));

		ddmFormValues.addDDMFormFieldValue(text2DDMFormFieldValue);

		// XML

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("root");

		rootElement.addAttribute("default-locale", "en_US");
		rootElement.addAttribute("available-locales", "en_US,pt_BR");

		addDynamicElementElement(
			rootElement, "Text",
			new String[] {"En Text Value 1", "En Text Value 2"},
			new String[] {"Pt Text Value 1", "Pt Text Value 2"});
		addDynamicElementElement(
			rootElement, "TextArea",
			new String[] {
				"En Text Area Value 1", "En Text Area Value 2",
				"En Text Area Value 3"
			},
			new String[] {
				"Pt Text Area Value 1", "Pt Text Area Value 2",
				"Pt Text Area Value 3"
			});
		addDynamicElementElement(
			rootElement, "_fieldsDisplay",
			new String[] {
				"Text_INSTANCE_srfa,TextArea_INSTANCE_elcy," +
					"TextArea_INSTANCE_uxyj,Text_INSTANCE_ealq," +
						"TextArea_INSTANCE_eepy"
			});

		String expectedJSON = _ddmFormValuesJSONSerializer.serialize(
			ddmFormValues);

		DDMFormValues actualDDMFormValues =
			_upgradeDynamicDataMapping.getDDMFormValues(
				1L, ddmForm, document.asXML());

		String actualJSON = _upgradeDynamicDataMapping.toJSON(
			actualDDMFormValues);

		JSONAssert.assertEquals(expectedJSON, actualJSON, false);
	}

	@Test
	public void testToJSONWithLocalizedData() throws Exception {
		DDMForm ddmForm = new DDMForm();

		ddmForm.setAvailableLocales(createAvailableLocales(LocaleUtil.US));
		ddmForm.setDefaultLocale(LocaleUtil.US);

		DDMFormField textDDMFormField = new DDMFormField("Text", "text");

		textDDMFormField.setDataType("string");
		textDDMFormField.setLocalizable(true);
		textDDMFormField.setRepeatable(true);

		ddmForm.addDDMFormField(textDDMFormField);

		DDMFormField textAreaDDMFormField = new DDMFormField(
			"TextArea", "textarea");

		textAreaDDMFormField.setDataType("string");
		textAreaDDMFormField.setLocalizable(true);
		textAreaDDMFormField.setRepeatable(true);

		ddmForm.addDDMFormField(textAreaDDMFormField);

		DDMFormField integerDDMFormField = new DDMFormField(
			"Integer", "ddm-integer");

		integerDDMFormField.setDataType("integer");
		integerDDMFormField.setLocalizable(false);
		integerDDMFormField.setRepeatable(false);

		ddmForm.addDDMFormField(integerDDMFormField);

		// DDM form values

		DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);

		ddmFormValues.setAvailableLocales(
			createAvailableLocales(LocaleUtil.BRAZIL, LocaleUtil.US));
		ddmFormValues.setDefaultLocale(LocaleUtil.US);

		ddmFormValues.addDDMFormFieldValue(
			createDDMFormFieldValue(
				"srfa", "Text",
				createLocalizedValue(
					"En Text Value 1", "Pt Text Value 1", LocaleUtil.US)));
		ddmFormValues.addDDMFormFieldValue(
			createDDMFormFieldValue(
				"ealq", "Text",
				createLocalizedValue(
					"En Text Value 2", "Pt Text Value 2", LocaleUtil.US)));
		ddmFormValues.addDDMFormFieldValue(
			createDDMFormFieldValue(
				"elcy", "TextArea",
				createLocalizedValue(
					"En Text Area Value 1", "Pt Text Area Value 1",
					LocaleUtil.US)));
		ddmFormValues.addDDMFormFieldValue(
			createDDMFormFieldValue(
				"uxyj", "TextArea",
				createLocalizedValue(
					"En Text Area Value 2", "Pt Text Area Value 2",
					LocaleUtil.US)));
		ddmFormValues.addDDMFormFieldValue(
			createDDMFormFieldValue(
				"eepy", "TextArea",
				createLocalizedValue(
					"En Text Area Value 3", "Pt Text Area Value 3",
					LocaleUtil.US)));
		ddmFormValues.addDDMFormFieldValue(
			createDDMFormFieldValue(
				"ckkp", "Integer", new UnlocalizedValue("1")));

		// XML

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("root");

		rootElement.addAttribute("default-locale", "en_US");
		rootElement.addAttribute("available-locales", "en_US,pt_BR");

		addDynamicElementElement(
			rootElement, "Text",
			new String[] {"En Text Value 1", "En Text Value 2"},
			new String[] {"Pt Text Value 1", "Pt Text Value 2"});
		addDynamicElementElement(
			rootElement, "TextArea",
			new String[] {
				"En Text Area Value 1", "En Text Area Value 2",
				"En Text Area Value 3"
			},
			new String[] {
				"Pt Text Area Value 1", "Pt Text Area Value 2",
				"Pt Text Area Value 3"
			});
		addDynamicElementElement(rootElement, "Integer", new String[] {"1"});
		addDynamicElementElement(
			rootElement, "_fieldsDisplay",
			new String[] {
				"Text_INSTANCE_srfa,Text_INSTANCE_ealq," +
					"TextArea_INSTANCE_elcy,TextArea_INSTANCE_uxyj," +
						"TextArea_INSTANCE_eepy,Integer_INSTANCE_ckkp"
			});

		String expectedJSON = _ddmFormValuesJSONSerializer.serialize(
			ddmFormValues);

		DDMFormValues actualDDMFormValues =
			_upgradeDynamicDataMapping.getDDMFormValues(
				1L, ddmForm, document.asXML());

		String actualJSON = _upgradeDynamicDataMapping.toJSON(
			actualDDMFormValues);

		JSONAssert.assertEquals(expectedJSON, actualJSON, false);
	}

	@Test
	public void testToJSONWithoutLocalizedData() throws Exception {
		DDMForm ddmForm = new DDMForm();

		ddmForm.setAvailableLocales(createAvailableLocales(LocaleUtil.US));
		ddmForm.setDefaultLocale(LocaleUtil.US);

		DDMFormField textDDMFormField = new DDMFormField("Text", "text");

		textDDMFormField.setDataType("string");
		textDDMFormField.setLocalizable(false);
		textDDMFormField.setRepeatable(false);

		ddmForm.addDDMFormField(textDDMFormField);

		DDMFormField textAreaDDMFormField = new DDMFormField(
			"TextArea", "textarea");

		textAreaDDMFormField.setDataType("string");
		textAreaDDMFormField.setLocalizable(false);
		textAreaDDMFormField.setRepeatable(true);

		ddmForm.addDDMFormField(textAreaDDMFormField);

		// DDM form values

		DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);

		ddmFormValues.setAvailableLocales(
			createAvailableLocales(LocaleUtil.US));
		ddmFormValues.setDefaultLocale(LocaleUtil.US);

		ddmFormValues.addDDMFormFieldValue(
			createDDMFormFieldValue(
				"hcxo", "Text", new UnlocalizedValue("Text Value")));
		ddmFormValues.addDDMFormFieldValue(
			createDDMFormFieldValue(
				"vfqd", "TextArea", new UnlocalizedValue("Text Area Value 1")));
		ddmFormValues.addDDMFormFieldValue(
			createDDMFormFieldValue(
				"ycey", "TextArea", new UnlocalizedValue("Text Area Value 2")));
		ddmFormValues.addDDMFormFieldValue(
			createDDMFormFieldValue(
				"habt", "TextArea", new UnlocalizedValue("Text Area Value 3")));

		// XML

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("root");

		rootElement.addAttribute("default-locale", "en_US");
		rootElement.addAttribute("available-locales", "en_US");

		addDynamicElementElement(
			rootElement, "Text", new String[] {"Text Value"});
		addDynamicElementElement(
			rootElement, "TextArea",
			new String[] {
				"Text Area Value 1", "Text Area Value 2", "Text Area Value 3"
			});
		addDynamicElementElement(
			rootElement, "_fieldsDisplay",
			new String[] {
				"Text_INSTANCE_hcxo,TextArea_INSTANCE_vfqd," +
					"TextArea_INSTANCE_ycey,TextArea_INSTANCE_habt"
			});

		String expectedJSON = _ddmFormValuesJSONSerializer.serialize(
			ddmFormValues);

		DDMFormValues actualDDMFormValues =
			_upgradeDynamicDataMapping.getDDMFormValues(
				1L, ddmForm, document.asXML());

		String actualJSON = _upgradeDynamicDataMapping.toJSON(
			actualDDMFormValues);

		JSONAssert.assertEquals(expectedJSON, actualJSON, false);
	}

	@Test
	public void testToXMLWithoutLocalizedData() throws Exception {
		Map<String, String> expandoValuesMap = new HashMap<>();

		expandoValuesMap.put(
			"Text", createLocalizationXML(new String[] {"Joe Bloggs"}));

		String fieldsDisplay = "Text_INSTANCE_hcxo";

		expandoValuesMap.put(
			"_fieldsDisplay",
			createLocalizationXML(new String[] {fieldsDisplay}));

		String xml = _upgradeDynamicDataMapping.toXML(expandoValuesMap);

		Document document = SAXReaderUtil.read(xml);

		Map<String, Map<String, List<String>>> dataMap = toDataMap(document);

		Map<String, List<String>> actualTextData = dataMap.get("Text");

		assertEquals(
			ListUtil.toList(new String[] {"Joe Bloggs"}),
			actualTextData.get("en_US"));

		Map<String, List<String>> actualFieldsDisplayData = dataMap.get(
			"_fieldsDisplay");

		assertEquals(
			ListUtil.toList(new String[] {fieldsDisplay}),
			actualFieldsDisplayData.get("en_US"));
	}

	@Test
	public void testToXMLWithRepeatableAndLocalizedData() throws Exception {
		Map<String, String> expandoValuesMap = new HashMap<>();

		expandoValuesMap.put(
			"Text",
			createLocalizationXML(
				new String[] {"A", "B", "C"}, new String[] {"D", "E", "F"}));

		String fieldsDisplay =
			"Text_INSTANCE_hcxo,Text_INSTANCE_vfqd,Text_INSTANCE_ycey";

		expandoValuesMap.put(
			"_fieldsDisplay",
			createLocalizationXML(new String[] {fieldsDisplay}));

		String xml = _upgradeDynamicDataMapping.toXML(expandoValuesMap);

		Document document = SAXReaderUtil.read(xml);

		Map<String, Map<String, List<String>>> dataMap = toDataMap(document);

		Map<String, List<String>> actualTextData = dataMap.get("Text");

		assertEquals(
			ListUtil.toList(new String[] {"A", "B", "C"}),
			actualTextData.get("en_US"));

		assertEquals(
			ListUtil.toList(new String[] {"D", "E", "F"}),
			actualTextData.get("pt_BR"));

		Map<String, List<String>> actualFieldsDisplayData = dataMap.get(
			"_fieldsDisplay");

		assertEquals(
			ListUtil.toList(new String[] {fieldsDisplay}),
			actualFieldsDisplayData.get("en_US"));
	}

	protected void addDynamicContentElements(
		Element dynamicElementElement, String[] dynamicContentDataArray,
		Locale locale) {

		for (String dynamicContentData : dynamicContentDataArray) {
			Element dynamicContentElement = dynamicElementElement.addElement(
				"dynamic-content");

			dynamicContentElement.addAttribute(
				"language-id", LocaleUtil.toLanguageId(locale));
			dynamicContentElement.addCDATA(dynamicContentData);
		}
	}

	protected void addDynamicElementElement(
		Element rootElement, String fieldName,
		String[] enDynamicContentDataArray) {

		Element dynamicElementElement = createDynamicElementElement(
			rootElement, fieldName);

		addDynamicContentElements(
			dynamicElementElement, enDynamicContentDataArray, LocaleUtil.US);
	}

	protected void addDynamicElementElement(
		Element rootElement, String fieldName,
		String[] enDynamicContentDataArray,
		String[] ptDynamicContentDataArray) {

		Element dynamicElementElement = createDynamicElementElement(
			rootElement, fieldName);

		addDynamicContentElements(
			dynamicElementElement, enDynamicContentDataArray, LocaleUtil.US);
		addDynamicContentElements(
			dynamicElementElement, ptDynamicContentDataArray,
			LocaleUtil.BRAZIL);
	}

	protected void append(
		Map<String, List<String>> localizedDataMap, String languageId,
		String localizedData) {

		List<String> data = localizedDataMap.get(languageId);

		if (data == null) {
			data = new ArrayList<>();

			localizedDataMap.put(languageId, data);
		}

		data.add(localizedData);
	}

	protected void assertEquals(
		List<String> expectedDataValues, List<String> actualDataValues) {

		int expectedDataValuesSize = expectedDataValues.size();

		Assert.assertEquals(expectedDataValuesSize, actualDataValues.size());

		for (int i = 0; i < expectedDataValuesSize; i++) {
			Assert.assertEquals(
				expectedDataValues.get(i), actualDataValues.get(i));
		}
	}

	protected Set<Locale> createAvailableLocales(Locale... locales) {
		Set<Locale> availableLocales = new LinkedHashSet<>();

		for (Locale locale : locales) {
			availableLocales.add(locale);
		}

		return availableLocales;
	}

	protected DDMFormFieldValue createDDMFormFieldValue(
		String instanceId, String name, Value value) {

		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setInstanceId(instanceId);
		ddmFormFieldValue.setName(name);
		ddmFormFieldValue.setValue(value);

		return ddmFormFieldValue;
	}

	protected Element createDynamicElementElement(
		Element rootElement, String fieldName) {

		Element dynamicElementElement = rootElement.addElement(
			"dynamic-element");

		dynamicElementElement.addAttribute("default-language-id", "en_US");
		dynamicElementElement.addAttribute("name", fieldName);

		return dynamicElementElement;
	}

	protected String createLocalizationXML(String[] enData) {
		StringBundler sb = new StringBundler(6);

		sb.append("<?xml version=\"1.0\"?>");
		sb.append("<root available-locales='en_US' default-locale='en_US'>");
		sb.append("<Data language-id='en_US'>");
		sb.append(StringUtil.merge(enData));
		sb.append("</Data>");
		sb.append("</root>");

		return sb.toString();
	}

	protected String createLocalizationXML(String[] enData, String[] ptData) {
		StringBundler sb = new StringBundler(10);

		sb.append("<?xml version=\"1.0\"?>");
		sb.append("<root available-locales='en_US,pt_BR,' ");
		sb.append("default-locale='en_US'>");
		sb.append("<Data language-id='en_US'>");
		sb.append(StringUtil.merge(enData));
		sb.append("</Data>");
		sb.append("<Data language-id='pt_BR'>");
		sb.append(StringUtil.merge(ptData));
		sb.append("</Data>");
		sb.append("</root>");

		return sb.toString();
	}

	protected Value createLocalizedValue(
		String enValue, String ptValue, Locale defaultLocale) {

		Value value = new LocalizedValue(defaultLocale);

		value.addString(LocaleUtil.BRAZIL, ptValue);
		value.addString(LocaleUtil.US, enValue);

		return value;
	}

	protected Map<String, List<String>> getLocalizedDataMap(
		Element dynamicElementElement) {

		Map<String, List<String>> localizedDataMap = new HashMap<>();

		for (Element dynamicContentElement : dynamicElementElement.elements()) {
			String languageId = dynamicContentElement.attributeValue(
				"language-id");

			append(
				localizedDataMap, languageId, dynamicContentElement.getText());
		}

		return localizedDataMap;
	}

	protected void setUpDDMFormValuesJSONDeserializer() throws Exception {
		field(
			DDMFormValuesJSONDeserializerImpl.class, "_jsonFactory"
		).set(
			_ddmFormValuesJSONDeserializer, new JSONFactoryImpl()
		);
	}

	protected void setUpDDMFormValuesJSONSerializer() throws Exception {
		field(
			DDMFormValuesJSONSerializerImpl.class, "_jsonFactory"
		).set(
			_ddmFormValuesJSONSerializer, new JSONFactoryImpl()
		);
	}

	protected void setUpJSONFactoryUtil() {
		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());
	}

	protected void setUpLanguageUtil() {
		whenLanguageGetLanguageId(LocaleUtil.US, "en_US");
		whenLanguageGetLanguageId(LocaleUtil.BRAZIL, "pt_BR");

		whenLanguageGetAvailableLocalesThen(
			SetUtil.fromArray(new Locale[] {LocaleUtil.BRAZIL, LocaleUtil.US}));

		whenLanguageIsAvailableLocale(LocaleUtil.BRAZIL);
		whenLanguageIsAvailableLocale(LocaleUtil.US);

		LanguageUtil languageUtil = new LanguageUtil();

		languageUtil.setLanguage(_language);
	}

	protected void setUpLocaleUtil() {
		mockStatic(LocaleUtil.class);

		when(
			LocaleUtil.fromLanguageId("en_US")
		).thenReturn(
			LocaleUtil.US
		);

		when(
			LocaleUtil.fromLanguageId("pt_BR")
		).thenReturn(
			LocaleUtil.BRAZIL
		);

		when(
			LocaleUtil.toLanguageId(LocaleUtil.US)
		).thenReturn(
			"en_US"
		);

		when(
			LocaleUtil.toLanguageId(LocaleUtil.BRAZIL)
		).thenReturn(
			"pt_BR"
		);

		when(
			LocaleUtil.toLanguageIds((Locale[])Matchers.any())
		).then(
			new Answer<String[]>() {

				@Override
				public String[] answer(InvocationOnMock invocationOnMock)
					throws Throwable {

					Object[] args = invocationOnMock.getArguments();

					Locale[] locales = (Locale[])args[0];

					String[] languageIds = new String[locales.length];

					for (int i = 0; i < locales.length; i++) {
						languageIds[i] = LocaleUtil.toLanguageId(locales[i]);
					}

					return languageIds;
				}

			}
		);
	}

	protected void setUpLocalizationUtil() {
		LocalizationUtil localizationUtil = new LocalizationUtil();

		localizationUtil.setLocalization(new LocalizationImpl());
	}

	protected void setUpPropsUtil() {
		Props props = mock(Props.class);

		when(
			props.get(PropsKeys.XML_SECURITY_ENABLED)
		).thenReturn(
			Boolean.TRUE.toString()
		);

		PropsUtil.setProps(props);
	}

	protected void setUpSAXReaderUtil() {
		SAXReaderUtil saxReaderUtil = new SAXReaderUtil();

		SAXReaderImpl secureSAXReader = new SAXReaderImpl();

		secureSAXReader.setSecure(true);

		saxReaderUtil.setSAXReader(secureSAXReader);

		UnsecureSAXReaderUtil unsecureSAXReaderUtil =
			new UnsecureSAXReaderUtil();

		SAXReaderImpl unsecureSAXReader = new SAXReaderImpl();

		unsecureSAXReaderUtil.setSAXReader(unsecureSAXReader);
	}

	protected void setUpSecureXMLFactoryProviderUtil() {
		SecureXMLFactoryProviderUtil secureXMLFactoryProviderUtil =
			new SecureXMLFactoryProviderUtil();

		secureXMLFactoryProviderUtil.setSecureXMLFactoryProvider(
			new SecureXMLFactoryProviderImpl());
	}

	protected Map<String, Map<String, List<String>>> toDataMap(
		Document document) {

		Element rootElement = document.getRootElement();

		Map<String, Map<String, List<String>>> dataMap = new HashMap<>();

		for (Element dynamicElementElement :
				rootElement.elements("dynamic-element")) {

			String name = dynamicElementElement.attributeValue("name");

			Map<String, List<String>> localizedDataMap = getLocalizedDataMap(
				dynamicElementElement);

			dataMap.put(name, localizedDataMap);
		}

		return dataMap;
	}

	protected void whenLanguageGetAvailableLocalesThen(
		Set<Locale> availableLocales) {

		when(
			_language.getAvailableLocales()
		).thenReturn(
			availableLocales
		);
	}

	protected void whenLanguageGetLanguageId(Locale locale, String languageId) {
		when(
			_language.getLanguageId(Matchers.eq(locale))
		).thenReturn(
			languageId
		);
	}

	protected void whenLanguageIsAvailableLocale(Locale locale) {
		when(
			_language.isAvailableLocale(Matchers.eq(locale))
		).thenReturn(
			true
		);
	}

	private final DDMFormValuesJSONDeserializer _ddmFormValuesJSONDeserializer =
		new DDMFormValuesJSONDeserializerImpl();
	private final DDMFormValuesJSONSerializer _ddmFormValuesJSONSerializer =
		new DDMFormValuesJSONSerializerImpl();

	@Mock
	private Language _language;

	@Mock
	private ResourceActions _resourceActions;

	private UpgradeDynamicDataMapping _upgradeDynamicDataMapping;

	private static class TestResourceActions implements ResourceActions {

		public void checkAction(String name, String actionId)
			throws NoSuchResourceActionException {
		}

		public String getAction(HttpServletRequest request, String action) {
			return null;
		}

		public String getAction(Locale locale, String action) {
			return null;
		}

		public String getActionNamePrefix() {
			return null;
		}

		public List<String> getActionsNames(
			HttpServletRequest request, List<String> actions) {

			return null;
		}

		public List<String> getActionsNames(
			HttpServletRequest request, String name, long actionIds) {

			return null;
		}

		public String getCompositeModelName(String... classNames) {
			if (ArrayUtil.isEmpty(classNames)) {
				return StringPool.BLANK;
			}

			Arrays.sort(classNames);

			StringBundler sb = new StringBundler(classNames.length * 2);

			for (String className : classNames) {
				sb.append(className);
				sb.append(getCompositeModelNameSeparator());
			}

			sb.setIndex(sb.index() - 1);

			return sb.toString();
		}

		public String getCompositeModelNameSeparator() {
			return null;
		}

		public List<String> getModelNames() {
			return null;
		}

		public List<String> getModelPortletResources(String name) {
			return null;
		}

		public String getModelResource(
			HttpServletRequest request, String name) {

			return null;
		}

		public String getModelResource(Locale locale, String name) {
			return null;
		}

		public List<String> getModelResourceActions(String name) {
			return null;
		}

		public List<String> getModelResourceGroupDefaultActions(String name) {
			return null;
		}

		public List<String> getModelResourceGuestDefaultActions(String name) {
			return null;
		}

		public List<String> getModelResourceGuestUnsupportedActions(
			String name) {

			return null;
		}

		public String getModelResourceNamePrefix() {
			return null;
		}

		public List<String> getModelResourceOwnerDefaultActions(String name) {
			return null;
		}

		public Double getModelResourceWeight(String name) {
			return null;
		}

		public String[] getOrganizationModelResources() {
			return new String[0];
		}

		public String[] getPortalModelResources() {
			return new String[0];
		}

		public String getPortletBaseResource(String portletName) {
			return null;
		}

		public List<String> getPortletModelResources(String portletName) {
			return null;
		}

		public List<String> getPortletNames() {
			return null;
		}

		public List<String> getPortletResourceActions(Portlet portlet) {
			return null;
		}

		public List<String> getPortletResourceActions(String name) {
			return null;
		}

		public List<String> getPortletResourceGroupDefaultActions(String name) {
			return null;
		}

		public List<String> getPortletResourceGuestDefaultActions(String name) {
			return null;
		}

		public List<String> getPortletResourceGuestUnsupportedActions(
			String name) {

			return null;
		}

		public List<String> getPortletResourceLayoutManagerActions(
			String name) {

			return null;
		}

		public String getPortletRootModelResource(String portletName) {
			return null;
		}

		public List<String> getResourceActions(String name) {
			return null;
		}

		public List<String> getResourceActions(
			String portletResource, String modelResource) {

			return null;
		}

		public List<String> getResourceGroupDefaultActions(String name) {
			return null;
		}

		public List<String> getResourceGuestUnsupportedActions(
			String portletResource, String modelResource) {

			return null;
		}

		public List<Role> getRoles(
			long companyId, Group group, String modelResource,
			int[] roleTypes) {

			return null;
		}

		public String[] getRootModelResources() {
			return new String[0];
		}

		public boolean hasModelResourceActions(String name) {
			return false;
		}

		public boolean isOrganizationModelResource(String modelResource) {
			return false;
		}

		public boolean isPortalModelResource(String modelResource) {
			return false;
		}

		public boolean isRootModelResource(String modelResource) {
			return false;
		}

		public void read(
				String servletContextName, ClassLoader classLoader,
				String source)
			throws Exception {
		}

		public void read(String servletContextName, InputStream inputStream)
			throws Exception {
		}

	}

}