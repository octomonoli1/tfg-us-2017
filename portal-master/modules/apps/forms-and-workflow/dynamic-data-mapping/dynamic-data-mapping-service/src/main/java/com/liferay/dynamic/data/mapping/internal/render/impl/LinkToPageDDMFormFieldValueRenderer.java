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

package com.liferay.dynamic.data.mapping.internal.render.impl;

import com.liferay.dynamic.data.mapping.model.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.render.BaseDDMFormFieldValueRenderer;
import com.liferay.dynamic.data.mapping.render.ValueAccessor;
import com.liferay.dynamic.data.mapping.render.ValueAccessorException;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.LayoutServiceUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Locale;

/**
 * @author Bruno Basto
 */
public class LinkToPageDDMFormFieldValueRenderer
	extends BaseDDMFormFieldValueRenderer {

	@Override
	public String getSupportedDDMFormFieldType() {
		return DDMFormFieldType.LINK_TO_PAGE;
	}

	@Override
	protected ValueAccessor getValueAcessor(Locale locale) {
		return new ValueAccessor(locale) {

			@Override
			public String get(DDMFormFieldValue ddmFormFieldValue) {
				Value value = ddmFormFieldValue.getValue();

				JSONObject jsonObject = createJSONObject(
					value.getString(locale));

				long groupId = jsonObject.getLong("groupId");
				boolean privateLayout = jsonObject.getBoolean("privateLayout");
				long layoutId = jsonObject.getLong("layoutId");

				if ((groupId == 0) && (layoutId == 0)) {
					return StringPool.BLANK;
				}

				try {
					return LayoutServiceUtil.getLayoutName(
						groupId, privateLayout, layoutId,
						LanguageUtil.getLanguageId(locale));
				}
				catch (PortalException pe) {
					if (_log.isWarnEnabled()) {
						_log.warn(pe, pe);
					}

					return LanguageUtil.format(
						locale, "is-temporarily-unavailable", "content");
				}
			}

			protected JSONObject createJSONObject(String json) {
				try {
					return JSONFactoryUtil.createJSONObject(json);
				}
				catch (JSONException jsone) {
					throw new ValueAccessorException(jsone);
				}
			}

		};
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LinkToPageDDMFormFieldValueRenderer.class);

}