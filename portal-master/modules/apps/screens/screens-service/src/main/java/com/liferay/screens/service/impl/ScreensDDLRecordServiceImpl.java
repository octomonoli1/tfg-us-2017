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

package com.liferay.screens.service.impl;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.service.permission.DDLRecordPermission;
import com.liferay.dynamic.data.lists.service.permission.DDLRecordSetPermission;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.FieldConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.screens.service.base.ScreensDDLRecordServiceBaseImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author José Manuel Navarro
 */
public class ScreensDDLRecordServiceImpl
	extends ScreensDDLRecordServiceBaseImpl {

	@Override
	public JSONObject getDDLRecord(long ddlRecordId, Locale locale)
		throws PortalException {

		DDLRecord ddlRecord = ddlRecordLocalService.getRecord(ddlRecordId);

		DDLRecordPermission.check(
			getPermissionChecker(), ddlRecord, ActionKeys.VIEW);

		DDMFormValues ddmFormValues = ddlRecord.getDDMFormValues();

		Set<Locale> availableLocales = ddmFormValues.getAvailableLocales();

		if ((locale == null) || !availableLocales.contains(locale)) {
			locale = ddlRecord.getDDMFormValues().getDefaultLocale();
		}

		return getDDLRecordJSONObject(ddlRecord, locale);
	}

	@Override
	public JSONArray getDDLRecords(
			long ddlRecordSetId, Locale locale, int start, int end,
			OrderByComparator<DDLRecord> obc)
		throws PortalException {

		DDLRecordSetPermission.check(
			getPermissionChecker(), ddlRecordSetId, ActionKeys.VIEW);

		List<DDLRecord> ddlRecords = ddlRecordPersistence.findByRecordSetId(
			ddlRecordSetId, start, end, obc);

		return getDDLRecordsJSONArray(ddlRecords, locale);
	}

	@Override
	public JSONArray getDDLRecords(
			long ddlRecordSetId, long userId, Locale locale, int start, int end,
			OrderByComparator<DDLRecord> obc)
		throws PortalException {

		DDLRecordSetPermission.check(
			getPermissionChecker(), ddlRecordSetId, ActionKeys.VIEW);

		List<DDLRecord> ddlRecords = ddlRecordPersistence.findByR_U(
			ddlRecordSetId, userId, start, end, obc);

		return getDDLRecordsJSONArray(ddlRecords, locale);
	}

	@Override
	public int getDDLRecordsCount(long ddlRecordSetId) throws PortalException {
		DDLRecordSetPermission.check(
			getPermissionChecker(), ddlRecordSetId, ActionKeys.VIEW);

		return ddlRecordPersistence.countByRecordSetId(ddlRecordSetId);
	}

	@Override
	public int getDDLRecordsCount(long ddlRecordSetId, long userId)
		throws PortalException {

		DDLRecordSetPermission.check(
			getPermissionChecker(), ddlRecordSetId, ActionKeys.VIEW);

		return ddlRecordPersistence.countByR_U(ddlRecordSetId, userId);
	}

	protected JSONObject getDDLRecordJSONObject(
			DDLRecord ddlRecord, Locale locale)
		throws PortalException {

		JSONObject ddlRecordJSONObject = JSONFactoryUtil.createJSONObject();

		ddlRecordJSONObject.put(
			"modelAttributes",
			JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(
					ddlRecord.getModelAttributes())));

		Map<String, Object> ddlRecordMap = new HashMap<>();

		DDMFormValues ddmFormValues = ddlRecord.getDDMFormValues();

		Set<Locale> availableLocales = ddmFormValues.getAvailableLocales();

		if ((locale == null) || !availableLocales.contains(locale)) {
			locale = ddmFormValues.getDefaultLocale();
		}

		for (DDMFormFieldValue ddmFormFieldValue :
				ddmFormValues.getDDMFormFieldValues()) {

			Object fieldValue = getFieldValue(ddmFormFieldValue, locale);

			if (fieldValue != null) {
				ddlRecordMap.put(ddmFormFieldValue.getName(), fieldValue);
			}
			else {
				for (Locale availableLocale : availableLocales) {
					fieldValue = getFieldValue(
						ddmFormFieldValue, availableLocale);

					if (fieldValue != null) {
						ddlRecordMap.put(
							ddmFormFieldValue.getName(), fieldValue);

						break;
					}
				}
			}
		}

		ddlRecordJSONObject.put(
			"modelValues",
			JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(ddlRecordMap)));

		return ddlRecordJSONObject;
	}

	protected JSONArray getDDLRecordsJSONArray(
			List<DDLRecord> ddlRecords, Locale locale)
		throws PortalException {

		JSONArray ddlRecordsJSONArray = JSONFactoryUtil.createJSONArray();

		for (DDLRecord ddlRecord : ddlRecords) {
			JSONObject ddlRecordJSONObject = getDDLRecordJSONObject(
				ddlRecord, locale);

			ddlRecordsJSONArray.put(ddlRecordJSONObject);
		}

		return ddlRecordsJSONArray;
	}

	protected Object getFieldValue(
			DDMFormFieldValue ddmFormFieldValue, Locale locale)
		throws PortalException {

		Value value = ddmFormFieldValue.getValue();

		String fieldValueString = value.getString(locale);

		if (fieldValueString == null) {
			return null;
		}

		if (fieldValueString.isEmpty()) {
			return null;
		}

		String dataType = ddmFormFieldValue.getType();

		if (dataType.equals(FieldConstants.BOOLEAN)) {
			return Boolean.valueOf(fieldValueString);
		}
		else if (dataType.equals(FieldConstants.DATE)) {
			return fieldValueString;
		}
		else if (dataType.equals(FieldConstants.DOCUMENT_LIBRARY)) {
			return JSONFactoryUtil.looseSerialize(
				JSONFactoryUtil.looseDeserialize(fieldValueString));
		}
		else if (dataType.equals(FieldConstants.FLOAT) ||
				 dataType.equals(FieldConstants.NUMBER)) {

			if (Validator.isNull(fieldValueString)) {
				return null;
			}

			return Float.valueOf(fieldValueString);
		}
		else if (dataType.equals(FieldConstants.INTEGER)) {
			if (Validator.isNull(fieldValueString)) {
				return null;
			}

			return Integer.valueOf(fieldValueString);
		}
		else if (dataType.equals(FieldConstants.LONG)) {
			if (Validator.isNull(fieldValueString)) {
				return null;
			}

			return Long.valueOf(fieldValueString);
		}
		else if (dataType.equals(FieldConstants.SHORT)) {
			if (Validator.isNull(fieldValueString)) {
				return null;
			}

			return Short.valueOf(fieldValueString);
		}

		return fieldValueString;
	}

}