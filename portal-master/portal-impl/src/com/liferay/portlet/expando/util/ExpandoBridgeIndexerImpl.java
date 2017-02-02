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

package com.liferay.portlet.expando.util;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.expando.kernel.util.ExpandoBridgeIndexer;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portlet.expando.model.impl.ExpandoValueImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raymond Augé
 */
@DoPrivileged
public class ExpandoBridgeIndexerImpl implements ExpandoBridgeIndexer {

	@Override
	public void addAttributes(Document document, ExpandoBridge expandoBridge) {
		if (expandoBridge == null) {
			return;
		}

		try {
			doAddAttributes(document, expandoBridge);
		}
		catch (SystemException se) {
			_log.error(se, se);
		}
	}

	@Override
	public String encodeFieldName(String columnName) {
		StringBundler sb = new StringBundler(5);

		sb.append(FIELD_NAMESPACE);
		sb.append(StringPool.DOUBLE_UNDERLINE);
		sb.append(
			StringUtil.toLowerCase(ExpandoTableConstants.DEFAULT_TABLE_NAME));
		sb.append(StringPool.DOUBLE_UNDERLINE);
		sb.append(columnName);

		return sb.toString();
	}

	protected void addAttribute(
			Document document, ExpandoColumn expandoColumn,
			List<ExpandoValue> expandoValues)
		throws PortalException {

		String fieldName = encodeFieldName(expandoColumn.getName());

		ExpandoValue expandoValue = new ExpandoValueImpl();

		expandoValue.setColumnId(expandoColumn.getColumnId());
		expandoValue.setData(expandoColumn.getDefaultData());

		boolean defaultValue = true;

		for (ExpandoValue curExpandoValue : expandoValues) {
			if (curExpandoValue.getColumnId() == expandoColumn.getColumnId()) {
				expandoValue = curExpandoValue;

				defaultValue = false;

				break;
			}
		}

		UnicodeProperties typeSettingsProperties =
			expandoColumn.getTypeSettingsProperties();

		int indexType = GetterUtil.getInteger(
			typeSettingsProperties.getProperty(
				ExpandoColumnConstants.INDEX_TYPE));

		int type = expandoColumn.getType();

		if (type == ExpandoColumnConstants.BOOLEAN) {
			document.addKeyword(fieldName, expandoValue.getBoolean());
		}
		else if (type == ExpandoColumnConstants.BOOLEAN_ARRAY) {
			if (!defaultValue) {
				document.addKeyword(fieldName, expandoValue.getBooleanArray());
			}
			else {
				document.addKeyword(fieldName, new boolean[0]);
			}
		}
		else if (type == ExpandoColumnConstants.DATE) {
			document.addDate(fieldName, expandoValue.getDate());
		}
		else if (type == ExpandoColumnConstants.DOUBLE) {
			document.addKeyword(fieldName, expandoValue.getDouble());
		}
		else if (type == ExpandoColumnConstants.DOUBLE_ARRAY) {
			if (!defaultValue) {
				document.addKeyword(fieldName, expandoValue.getDoubleArray());
			}
			else {
				document.addKeyword(fieldName, new double[0]);
			}
		}
		else if (type == ExpandoColumnConstants.FLOAT) {
			document.addKeyword(fieldName, expandoValue.getFloat());
		}
		else if (type == ExpandoColumnConstants.FLOAT_ARRAY) {
			if (!defaultValue) {
				document.addKeyword(fieldName, expandoValue.getFloatArray());
			}
			else {
				document.addKeyword(fieldName, new float[0]);
			}
		}
		else if (type == ExpandoColumnConstants.INTEGER) {
			document.addKeyword(fieldName, expandoValue.getInteger());
		}
		else if (type == ExpandoColumnConstants.INTEGER_ARRAY) {
			if (!defaultValue) {
				document.addKeyword(fieldName, expandoValue.getIntegerArray());
			}
			else {
				document.addKeyword(fieldName, new int[0]);
			}
		}
		else if (type == ExpandoColumnConstants.LONG) {
			document.addKeyword(fieldName, expandoValue.getLong());
		}
		else if (type == ExpandoColumnConstants.LONG_ARRAY) {
			if (!defaultValue) {
				document.addKeyword(fieldName, expandoValue.getLongArray());
			}
			else {
				document.addKeyword(fieldName, new long[0]);
			}
		}
		else if (type == ExpandoColumnConstants.NUMBER) {
			document.addKeyword(fieldName, expandoValue.getNumber().toString());
		}
		else if (type == ExpandoColumnConstants.NUMBER_ARRAY) {
			if (!defaultValue) {
				document.addKeyword(
					fieldName,
					ArrayUtil.toStringArray(expandoValue.getNumberArray()));
			}
			else {
				document.addKeyword(fieldName, new long[0]);
			}
		}
		else if (type == ExpandoColumnConstants.SHORT) {
			document.addKeyword(fieldName, expandoValue.getShort());
		}
		else if (type == ExpandoColumnConstants.SHORT_ARRAY) {
			if (!defaultValue) {
				document.addKeyword(fieldName, expandoValue.getShortArray());
			}
			else {
				document.addKeyword(fieldName, new short[0]);
			}
		}
		else if (type == ExpandoColumnConstants.STRING) {
			if (indexType == ExpandoColumnConstants.INDEX_TYPE_KEYWORD) {
				document.addKeyword(fieldName, expandoValue.getString());
			}
			else {
				document.addText(fieldName, expandoValue.getString());
			}
		}
		else if (type == ExpandoColumnConstants.STRING_ARRAY) {
			if (!defaultValue) {
				if (indexType == ExpandoColumnConstants.INDEX_TYPE_KEYWORD) {
					document.addKeyword(
						fieldName, expandoValue.getStringArray());
				}
				else {
					document.addText(
						fieldName,
						StringUtil.merge(
							expandoValue.getStringArray(), StringPool.SPACE));
				}
			}
			else {
				if (indexType == ExpandoColumnConstants.INDEX_TYPE_KEYWORD) {
					document.addKeyword(fieldName, StringPool.BLANK);
				}
				else {
					document.addText(fieldName, StringPool.BLANK);
				}
			}
		}
		else if (type == ExpandoColumnConstants.STRING_LOCALIZED) {
			if (!defaultValue) {
				if (indexType == ExpandoColumnConstants.INDEX_TYPE_KEYWORD) {
					document.addLocalizedKeyword(
						fieldName, expandoValue.getStringMap());
				}
				else {
					document.addLocalizedText(
						fieldName, expandoValue.getStringMap());
				}
			}
		}
	}

	protected void doAddAttributes(
		Document document, ExpandoBridge expandoBridge) {

		List<ExpandoColumn> expandoColumns =
			ExpandoColumnLocalServiceUtil.getDefaultTableColumns(
				expandoBridge.getCompanyId(), expandoBridge.getClassName());

		if ((expandoColumns == null) || expandoColumns.isEmpty()) {
			return;
		}

		List<ExpandoColumn> indexedColumns = new ArrayList<>();

		for (ExpandoColumn expandoColumn : expandoColumns) {
			UnicodeProperties properties =
				expandoColumn.getTypeSettingsProperties();

			int indexType = GetterUtil.getInteger(
				properties.get(ExpandoColumnConstants.INDEX_TYPE));

			if (indexType != ExpandoColumnConstants.INDEX_TYPE_NONE) {
				indexedColumns.add(expandoColumn);
			}
		}

		if (indexedColumns.isEmpty()) {
			return;
		}

		List<ExpandoValue> expandoValues =
			ExpandoValueLocalServiceUtil.getRowValues(
				expandoBridge.getCompanyId(), expandoBridge.getClassName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME,
				expandoBridge.getClassPK(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (ExpandoColumn expandoColumn : indexedColumns) {
			try {
				addAttribute(document, expandoColumn, expandoValues);
			}
			catch (Exception e) {
				_log.error("Indexing " + expandoColumn.getName(), e);
			}
		}
	}

	protected static final String FIELD_NAMESPACE = "expando";

	private static final Log _log = LogFactoryUtil.getLog(
		ExpandoBridgeIndexerImpl.class);

}