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

package com.liferay.portal.kernel.bean;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Locale;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class BeanParamUtil {

	public static boolean getBoolean(
		Object bean, HttpServletRequest request, String param) {

		return getBoolean(bean, request, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public static boolean getBoolean(
		Object bean, HttpServletRequest request, String param,
		boolean defaultValue) {

		defaultValue = BeanPropertiesUtil.getBoolean(bean, param, defaultValue);

		return ParamUtil.get(request, param, defaultValue);
	}

	public static boolean getBoolean(
		Object bean, PortletRequest portletRequest, String param) {

		return getBoolean(
			bean, portletRequest, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public static boolean getBoolean(
		Object bean, PortletRequest portletRequest, String param,
		boolean defaultValue) {

		defaultValue = BeanPropertiesUtil.getBoolean(bean, param, defaultValue);

		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public static boolean getBooleanSilent(
		Object bean, HttpServletRequest request, String param) {

		return getBooleanSilent(
			bean, request, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public static boolean getBooleanSilent(
		Object bean, HttpServletRequest request, String param,
		boolean defaultValue) {

		defaultValue = BeanPropertiesUtil.getBooleanSilent(
			bean, param, defaultValue);

		return ParamUtil.get(request, param, defaultValue);
	}

	public static boolean getBooleanSilent(
		Object bean, PortletRequest portletRequest, String param) {

		return getBooleanSilent(
			bean, portletRequest, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public static boolean getBooleanSilent(
		Object bean, PortletRequest portletRequest, String param,
		boolean defaultValue) {

		defaultValue = BeanPropertiesUtil.getBooleanSilent(
			bean, param, defaultValue);

		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public static double getDouble(
		Object bean, HttpServletRequest request, String param) {

		return getDouble(bean, request, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public static double getDouble(
		Object bean, HttpServletRequest request, String param,
		double defaultValue) {

		defaultValue = BeanPropertiesUtil.getDouble(bean, param, defaultValue);

		return ParamUtil.get(request, param, defaultValue);
	}

	public static double getDouble(
		Object bean, HttpServletRequest request, String param,
		double defaultValue, Locale locale) {

		defaultValue = BeanPropertiesUtil.getDouble(bean, param, defaultValue);

		return ParamUtil.getDouble(request, param, defaultValue, locale);
	}

	public static double getDouble(
		Object bean, HttpServletRequest request, String param, Locale locale) {

		return getDouble(
			bean, request, param, GetterUtil.DEFAULT_DOUBLE, locale);
	}

	public static double getDouble(
		Object bean, PortletRequest portletRequest, String param) {

		return getDouble(
			bean, portletRequest, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public static double getDouble(
		Object bean, PortletRequest portletRequest, String param,
		double defaultValue) {

		defaultValue = BeanPropertiesUtil.getDouble(bean, param, defaultValue);

		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public static double getDouble(
		Object bean, PortletRequest portletRequest, String param,
		double defaultValue, Locale locale) {

		defaultValue = BeanPropertiesUtil.getDouble(bean, param, defaultValue);

		return ParamUtil.getDouble(portletRequest, param, defaultValue, locale);
	}

	public static double getDouble(
		Object bean, PortletRequest portletRequest, String param,
		Locale locale) {

		return getDouble(
			bean, portletRequest, param, GetterUtil.DEFAULT_DOUBLE, locale);
	}

	public static double getDoubleSilent(
		Object bean, HttpServletRequest request, String param) {

		return getDoubleSilent(bean, request, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public static double getDoubleSilent(
		Object bean, HttpServletRequest request, String param,
		double defaultValue) {

		defaultValue = BeanPropertiesUtil.getDoubleSilent(
			bean, param, defaultValue);

		return ParamUtil.get(request, param, defaultValue);
	}

	public static double getDoubleSilent(
		Object bean, HttpServletRequest request, String param,
		double defaultValue, Locale locale) {

		defaultValue = BeanPropertiesUtil.getDoubleSilent(
			bean, param, defaultValue);

		return ParamUtil.getDouble(request, param, defaultValue, locale);
	}

	public static double getDoubleSilent(
		Object bean, HttpServletRequest request, String param, Locale locale) {

		return getDoubleSilent(
			bean, request, param, GetterUtil.DEFAULT_DOUBLE, locale);
	}

	public static double getDoubleSilent(
		Object bean, PortletRequest portletRequest, String param) {

		return getDoubleSilent(
			bean, portletRequest, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public static double getDoubleSilent(
		Object bean, PortletRequest portletRequest, String param,
		double defaultValue) {

		defaultValue = BeanPropertiesUtil.getDoubleSilent(
			bean, param, defaultValue);

		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public static double getDoubleSilent(
		Object bean, PortletRequest portletRequest, String param,
		double defaultValue, Locale locale) {

		defaultValue = BeanPropertiesUtil.getDoubleSilent(
			bean, param, defaultValue);

		return ParamUtil.getDouble(portletRequest, param, defaultValue, locale);
	}

	public static double getDoubleSilent(
		Object bean, PortletRequest portletRequest, String param,
		Locale locale) {

		return getDoubleSilent(
			bean, portletRequest, param, GetterUtil.DEFAULT_DOUBLE, locale);
	}

	public static int getInteger(
		Object bean, HttpServletRequest request, String param) {

		return getInteger(bean, request, param, GetterUtil.DEFAULT_INTEGER);
	}

	public static int getInteger(
		Object bean, HttpServletRequest request, String param,
		int defaultValue) {

		defaultValue = BeanPropertiesUtil.getInteger(bean, param, defaultValue);

		return ParamUtil.get(request, param, defaultValue);
	}

	public static int getInteger(
		Object bean, PortletRequest portletRequest, String param) {

		return getInteger(
			bean, portletRequest, param, GetterUtil.DEFAULT_INTEGER);
	}

	public static int getInteger(
		Object bean, PortletRequest portletRequest, String param,
		int defaultValue) {

		defaultValue = BeanPropertiesUtil.getInteger(bean, param, defaultValue);

		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public static int getIntegerSilent(
		Object bean, HttpServletRequest request, String param) {

		return getIntegerSilent(
			bean, request, param, GetterUtil.DEFAULT_INTEGER);
	}

	public static int getIntegerSilent(
		Object bean, HttpServletRequest request, String param,
		int defaultValue) {

		defaultValue = BeanPropertiesUtil.getIntegerSilent(
			bean, param, defaultValue);

		return ParamUtil.get(request, param, defaultValue);
	}

	public static int getIntegerSilent(
		Object bean, PortletRequest portletRequest, String param) {

		return getIntegerSilent(
			bean, portletRequest, param, GetterUtil.DEFAULT_INTEGER);
	}

	public static int getIntegerSilent(
		Object bean, PortletRequest portletRequest, String param,
		int defaultValue) {

		defaultValue = BeanPropertiesUtil.getIntegerSilent(
			bean, param, defaultValue);

		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public static long getLong(
		Object bean, HttpServletRequest request, String param) {

		return getLong(bean, request, param, GetterUtil.DEFAULT_LONG);
	}

	public static long getLong(
		Object bean, HttpServletRequest request, String param,
		long defaultValue) {

		defaultValue = BeanPropertiesUtil.getLong(bean, param, defaultValue);

		return ParamUtil.get(request, param, defaultValue);
	}

	public static long getLong(
		Object bean, PortletRequest portletRequest, String param) {

		return getLong(bean, portletRequest, param, GetterUtil.DEFAULT_LONG);
	}

	public static long getLong(
		Object bean, PortletRequest portletRequest, String param,
		long defaultValue) {

		defaultValue = BeanPropertiesUtil.getLong(bean, param, defaultValue);

		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public static long getLongSilent(
		Object bean, HttpServletRequest request, String param) {

		return getLongSilent(bean, request, param, GetterUtil.DEFAULT_LONG);
	}

	public static long getLongSilent(
		Object bean, HttpServletRequest request, String param,
		long defaultValue) {

		defaultValue = BeanPropertiesUtil.getLongSilent(
			bean, param, defaultValue);

		return ParamUtil.get(request, param, defaultValue);
	}

	public static long getLongSilent(
		Object bean, PortletRequest portletRequest, String param) {

		return getLongSilent(
			bean, portletRequest, param, GetterUtil.DEFAULT_LONG);
	}

	public static long getLongSilent(
		Object bean, PortletRequest portletRequest, String param,
		long defaultValue) {

		defaultValue = BeanPropertiesUtil.getLongSilent(
			bean, param, defaultValue);

		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public static String getString(
		Object bean, HttpServletRequest request, String param) {

		return getString(bean, request, param, GetterUtil.DEFAULT_STRING);
	}

	public static String getString(
		Object bean, HttpServletRequest request, String param,
		String defaultValue) {

		defaultValue = BeanPropertiesUtil.getString(bean, param, defaultValue);

		return ParamUtil.get(request, param, defaultValue);
	}

	public static String getString(
		Object bean, PortletRequest portletRequest, String param) {

		return getString(
			bean, portletRequest, param, GetterUtil.DEFAULT_STRING);
	}

	public static String getString(
		Object bean, PortletRequest portletRequest, String param,
		String defaultValue) {

		defaultValue = BeanPropertiesUtil.getString(bean, param, defaultValue);

		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public static String getStringSilent(
		Object bean, HttpServletRequest request, String param) {

		return getStringSilent(bean, request, param, GetterUtil.DEFAULT_STRING);
	}

	public static String getStringSilent(
		Object bean, HttpServletRequest request, String param,
		String defaultValue) {

		defaultValue = BeanPropertiesUtil.getStringSilent(
			bean, param, defaultValue);

		return ParamUtil.get(request, param, defaultValue);
	}

	public static String getStringSilent(
		Object bean, PortletRequest portletRequest, String param) {

		return getStringSilent(
			bean, portletRequest, param, GetterUtil.DEFAULT_STRING);
	}

	public static String getStringSilent(
		Object bean, PortletRequest portletRequest, String param,
		String defaultValue) {

		defaultValue = BeanPropertiesUtil.getStringSilent(
			bean, param, defaultValue);

		return ParamUtil.get(portletRequest, param, defaultValue);
	}

}