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

package com.liferay.taglib.aui;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shuyang Zhou
 */
public class AUIUtil {

	public static final String BUTTON_PREFIX = "btn";

	public static final String FIELD_PREFIX = "field";

	public static String buildControlGroupCss(
		boolean inlineField, String inlineLabel, String wrapperCssClass,
		String baseType) {

		StringBundler sb = new StringBundler(9);

		sb.append("form-group");

		if (inlineField) {
			sb.append(" form-group-inline");
		}

		if (Validator.isNotNull(inlineLabel)) {
			sb.append(" form-inline");
		}

		if (Validator.isNotNull(wrapperCssClass)) {
			sb.append(StringPool.SPACE);
			sb.append(wrapperCssClass);
		}

		if (Validator.isNotNull(baseType)) {
			sb.append(StringPool.SPACE);
			sb.append("input-");
			sb.append(baseType);
			sb.append("-wrapper");
		}

		return sb.toString();
	}

	public static String buildCss(
		String prefix, boolean disabled, boolean first, boolean last,
		String cssClass) {

		StringBundler sb = new StringBundler(8);

		sb.append(prefix);

		if (disabled) {
			sb.append(StringPool.SPACE);
			sb.append("disabled");
		}

		if (first) {
			sb.append(StringPool.SPACE);
			sb.append(prefix);
			sb.append("-first");
		}
		else if (last) {
			sb.append(StringPool.SPACE);
			sb.append(prefix);
			sb.append("-last");
		}

		if (Validator.isNotNull(cssClass)) {
			sb.append(StringPool.SPACE);
			sb.append(cssClass);
		}

		return sb.toString();
	}

	public static String buildData(Map<String, Object> data) {
		return HtmlUtil.buildData(data);
	}

	public static String buildLabel(
		String baseType, boolean inlineField, boolean showForLabel,
		String forLabel) {

		StringBundler sb = new StringBundler(7);

		if (baseType.equals("boolean")) {
			baseType = "checkbox";
		}

		if (baseType.equals("checkbox") || baseType.equals("radio")) {
			if (inlineField) {
				sb.append("class=\"");
				sb.append(baseType);
				sb.append("-inline\" ");
			}
		}
		else {
			sb.append("class=\"control-label\" ");
		}

		if (showForLabel) {
			sb.append("for=\"");
			sb.append(HtmlUtil.escapeAttribute(forLabel));
			sb.append("\"");
		}

		return sb.toString();
	}

	public static Object getAttribute(
		HttpServletRequest request, String namespace, String key) {

		Map<String, Object> dynamicAttributes =
			(Map<String, Object>)request.getAttribute(
				namespace.concat("dynamicAttributes"));
		Map<String, Object> scopedAttributes =
			(Map<String, Object>)request.getAttribute(
				namespace.concat("scopedAttributes"));

		if (((dynamicAttributes != null) &&
			 dynamicAttributes.containsKey(key)) ||
			((scopedAttributes != null) && scopedAttributes.containsKey(key))) {

			return request.getAttribute(namespace.concat(key));
		}

		return null;
	}

	public static String getNamespace(HttpServletRequest request) {
		return GetterUtil.getString(
			request.getAttribute("aui:form:portletNamespace"));
	}

	public static String getNamespace(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		String namespace = StringPool.BLANK;

		if (portletRequest == null) {
			return namespace;
		}

		boolean auiFormUseNamespace = GetterUtil.getBoolean(
			(String)portletRequest.getAttribute("aui:form:useNamespace"), true);

		if ((portletResponse != null) && auiFormUseNamespace) {
			namespace = GetterUtil.getString(
				portletRequest.getAttribute("aui:form:portletNamespace"),
				portletResponse.getNamespace());
		}

		return namespace;
	}

	public static boolean isOpensNewWindow(String target) {
		if ((target != null) &&
			(target.equals("_blank") || target.equals("_new"))) {

			return true;
		}

		return false;
	}

	public static String normalizeId(String name) {
		Matcher matcher = _friendlyURLPattern.matcher(name);

		return matcher.replaceAll(StringPool.DASH);
	}

	private static final Pattern _friendlyURLPattern = Pattern.compile(
		"[^A-Za-z0-9/_-]");

}