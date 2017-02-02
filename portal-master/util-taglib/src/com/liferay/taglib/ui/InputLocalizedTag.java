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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.ModelHintsConstants;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
public class InputLocalizedTag extends IncludeTag {

	public Set<Locale> getAvailableLocales() {
		return _availableLocales;
	}

	public void setAutoFocus(boolean autoFocus) {
		_autoFocus = autoFocus;
	}

	public void setAutoSize(boolean autoSize) {
		_autoSize = autoSize;
	}

	public void setAvailableLocales(Set<Locale> availableLocales) {
		_availableLocales = availableLocales;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setDefaultLanguageId(String defaultLanguageId) {
		_defaultLanguageId = defaultLanguageId;
	}

	public void setDisabled(boolean disabled) {
		_disabled = disabled;
	}

	public void setDisplayWidth(String displayWidth) {
		_displayWidth = displayWidth;
	}

	public void setEditorName(String editorName) {
		_editorName = editorName;
	}

	public void setFieldPrefix(String fieldPrefix) {
		_fieldPrefix = fieldPrefix;
	}

	public void setFieldPrefixSeparator(String fieldPrefixSeparator) {
		_fieldPrefixSeparator = fieldPrefixSeparator;
	}

	public void setFormName(String formName) {
		_formName = formName;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIgnoreRequestValue(boolean ignoreRequestValue) {
		_ignoreRequestValue = ignoreRequestValue;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	public void setMaxLength(String maxLength) {
		_maxLength = maxLength;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPlaceholder(String placeholder) {
		_placeholder = placeholder;
	}

	public void setToolbarSet(String toolbarSet) {
		_toolbarSet = toolbarSet;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setXml(String xml) {
		_xml = xml;
	}

	@Override
	protected void cleanUp() {
		_autoFocus = false;
		_autoSize = false;
		_cssClass = null;
		_disabled = false;
		_displayWidth = ModelHintsConstants.TEXT_DISPLAY_WIDTH;
		_editorName = _EDITOR_WYSIWYG_DEFAULT;
		_fieldPrefix = null;
		_fieldPrefixSeparator = null;
		_formName = null;
		_id = null;
		_ignoreRequestValue = false;
		_languageId = null;
		_maxLength = null;
		_name = null;
		_placeholder = null;
		_toolbarSet = "simple";
		_type = "input";
		_xml = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		Set<Locale> availableLocales = _availableLocales;

		if (availableLocales == null) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			availableLocales = LanguageUtil.getAvailableLocales(
				themeDisplay.getSiteGroupId());
		}

		String formName = _formName;

		if (Validator.isNull(formName)) {
			formName = "fm";
		}

		String id = _id;

		if (Validator.isNull(id)) {
			id = _name;
		}

		request.setAttribute(
			"liferay-ui:input-localized:autoFocus", String.valueOf(_autoFocus));
		request.setAttribute(
			"liferay-ui:input-localized:autoSize", String.valueOf(_autoSize));
		request.setAttribute(
			"liferay-ui:input-localized:availableLocales", availableLocales);
		request.setAttribute("liferay-ui:input-localized:cssClass", _cssClass);
		request.setAttribute(
			"liferay-ui:input-localized:defaultLanguageId", _defaultLanguageId);
		request.setAttribute(
			"liferay-ui:input-localized:disabled", String.valueOf(_disabled));
		request.setAttribute(
			"liferay-ui:input-localized:displayWidth", _displayWidth);
		request.setAttribute(
			"liferay-ui:input-localized:dynamicAttributes",
			getDynamicAttributes());
		request.setAttribute(
			"liferay-ui:input-localized:editorName", _editorName);
		request.setAttribute(
			"liferay-ui:input-localized:fieldPrefix", _fieldPrefix);
		request.setAttribute(
			"liferay-ui:input-localized:fieldPrefixSeparator",
			_fieldPrefixSeparator);
		request.setAttribute("liferay-ui:input-localized:formName", formName);
		request.setAttribute("liferay-ui:input-localized:id", id);
		request.setAttribute(
			"liferay-ui:input-localized:ignoreRequestValue",
			String.valueOf(_ignoreRequestValue));
		request.setAttribute(
			"liferay-ui:input-localized:languageId", _languageId);
		request.setAttribute(
			"liferay-ui:input-localized:maxLength", _maxLength);
		request.setAttribute("liferay-ui:input-localized:name", _name);
		request.setAttribute(
			"liferay-ui:input-localized:placeholder", _placeholder);
		request.setAttribute(
			"liferay-ui:input-localized:toolbarSet", _toolbarSet);
		request.setAttribute("liferay-ui:input-localized:type", _type);
		request.setAttribute("liferay-ui:input-localized:xml", _xml);
	}

	private static final String _EDITOR_WYSIWYG_DEFAULT = PropsUtil.get(
		PropsKeys.EDITOR_WYSIWYG_DEFAULT);

	private static final String _PAGE =
		"/html/taglib/ui/input_localized/page.jsp";

	private boolean _autoFocus;
	private boolean _autoSize;
	private Set<Locale> _availableLocales;
	private String _cssClass;
	private String _defaultLanguageId;
	private boolean _disabled;
	private String _displayWidth = ModelHintsConstants.TEXT_DISPLAY_WIDTH;
	private String _editorName = _EDITOR_WYSIWYG_DEFAULT;
	private String _fieldPrefix;
	private String _fieldPrefixSeparator;
	private String _formName;
	private String _id;
	private boolean _ignoreRequestValue;
	private String _languageId;
	private String _maxLength;
	private String _name;
	private String _placeholder;
	private String _toolbarSet = "simple";
	private String _type = "input";
	private String _xml;

}