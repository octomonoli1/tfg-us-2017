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

package com.liferay.dynamic.data.mapping.type.select;

import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.test.util.DDMFormValuesTestUtil;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.util.LocaleUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Renato Rego
 */
public class SelectDDMFormFieldValueAccessorTest {

	@Test
	public void testGetSelectValue() throws Exception {
		JSONArray expectedJSONArray = createJSONArray("value 1");

		DDMFormFieldValue ddmFormFieldValue =
			DDMFormValuesTestUtil.createDDMFormFieldValue(
				"Select", new UnlocalizedValue(expectedJSONArray.toString()));

		SelectDDMFormFieldValueAccessor selectDDMFormFieldValueAccessor =
			new SelectDDMFormFieldValueAccessor();

		selectDDMFormFieldValueAccessor.jsonFactory = _jsonFactory;

		JSONArray actualJSONArray = selectDDMFormFieldValueAccessor.getValue(
			ddmFormFieldValue, LocaleUtil.US);

		Assert.assertEquals(
			expectedJSONArray.toString(), actualJSONArray.toString());
	}

	protected JSONArray createJSONArray(String... strings) {
		JSONArray jsonArray = _jsonFactory.createJSONArray();

		for (String string : strings) {
			jsonArray.put(string);
		}

		return jsonArray;
	}

	private final JSONFactory _jsonFactory = new JSONFactoryImpl();

}