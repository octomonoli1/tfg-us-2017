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

package com.liferay.portal.servlet.filters.dynamiccss;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.service.ThemeLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.net.URLDecoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Raymond Augé
 * @author Sergio Sánchez
 * @author David Truong
 */
public class DynamicCSSUtil {

	public static String replaceToken(
			ServletContext servletContext, HttpServletRequest request,
			String content)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Theme theme = _getTheme(request);

		if (theme == null) {
			return content;
		}

		return replaceToken(
			servletContext, request, themeDisplay, theme, content);
	}

	public static String replaceToken(
			ServletContext servletContext, HttpServletRequest request,
			ThemeDisplay themeDisplay, Theme theme, String parsedContent)
		throws Exception {

		String portalContextPath = PortalUtil.getPathContext();

		String baseURL = servletContext.getContextPath();

		if (baseURL.endsWith(StringPool.SLASH)) {
			baseURL = baseURL.substring(0, baseURL.length() - 1);
		}

		parsedContent = StringUtil.replace(
			parsedContent,
			new String[] {"@base_url@", "@portal_ctx@", "@theme_image_path@"},
			new String[] {
				baseURL, portalContextPath,
				_getThemeImagesPath(request, themeDisplay, theme)
			});

		return parsedContent;
	}

	/**
	 * @see com.liferay.portal.servlet.filters.aggregate.AggregateFilter#aggregateCss(
	 *      com.liferay.portal.servlet.filters.aggregate.ServletPaths, String)
	 */
	protected static String propagateQueryString(
		String content, String queryString) {

		StringBuilder sb = new StringBuilder(content.length());

		int pos = 0;

		while (true) {
			int importX = content.indexOf(_CSS_IMPORT_BEGIN, pos);
			int importY = content.indexOf(
				_CSS_IMPORT_END, importX + _CSS_IMPORT_BEGIN.length());

			if ((importX == -1) || (importY == -1)) {
				sb.append(content.substring(pos));

				break;
			}

			sb.append(content.substring(pos, importX));
			sb.append(_CSS_IMPORT_BEGIN);

			String url = content.substring(
				importX + _CSS_IMPORT_BEGIN.length(), importY);

			char firstChar = url.charAt(0);

			if (firstChar == CharPool.APOSTROPHE) {
				sb.append(CharPool.APOSTROPHE);
			}
			else if (firstChar == CharPool.QUOTE) {
				sb.append(CharPool.QUOTE);
			}

			url = StringUtil.unquote(url);

			sb.append(url);

			if (url.indexOf(CharPool.QUESTION) != -1) {
				sb.append(CharPool.AMPERSAND);
			}
			else {
				sb.append(CharPool.QUESTION);
			}

			sb.append(queryString);

			if (firstChar == CharPool.APOSTROPHE) {
				sb.append(CharPool.APOSTROPHE);
			}
			else if (firstChar == CharPool.QUOTE) {
				sb.append(CharPool.QUOTE);
			}

			sb.append(_CSS_IMPORT_END);

			pos = importY + _CSS_IMPORT_END.length();
		}

		return sb.toString();
	}

	private static Theme _getTheme(HttpServletRequest request)
		throws Exception {

		long companyId = PortalUtil.getCompanyId(request);

		String themeId = ParamUtil.getString(request, "themeId");

		if (Validator.isNotNull(themeId)) {
			try {
				Theme theme = ThemeLocalServiceUtil.getTheme(
					companyId, themeId);

				return theme;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		String requestURI = URLDecoder.decode(
			request.getRequestURI(), StringPool.UTF8);

		Matcher portalThemeMatcher = _portalThemePattern.matcher(requestURI);

		if (portalThemeMatcher.find()) {
			String themePathId = portalThemeMatcher.group(1);

			themePathId = StringUtil.replace(
				themePathId, CharPool.UNDERLINE, StringPool.BLANK);

			themeId = PortalUtil.getJsSafePortletId(themePathId);
		}
		else {
			Matcher pluginThemeMatcher = _pluginThemePattern.matcher(
				requestURI);

			if (pluginThemeMatcher.find()) {
				String themePathId = pluginThemeMatcher.group(1);

				themePathId = StringUtil.replace(
					themePathId, CharPool.UNDERLINE, StringPool.BLANK);

				StringBundler sb = new StringBundler(4);

				sb.append(themePathId);
				sb.append(PortletConstants.WAR_SEPARATOR);
				sb.append(themePathId);
				sb.append("theme");

				themePathId = sb.toString();

				themeId = PortalUtil.getJsSafePortletId(themePathId);
			}
		}

		if (Validator.isNull(themeId)) {
			return null;
		}

		try {
			Theme theme = ThemeLocalServiceUtil.getTheme(companyId, themeId);

			return theme;
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	private static String _getThemeImagesPath(
			HttpServletRequest request, ThemeDisplay themeDisplay, Theme theme)
		throws Exception {

		String themeImagesPath = null;

		if (themeDisplay != null) {
			themeImagesPath = themeDisplay.getPathThemeImages();
		}
		else {
			String cdnHost = PortalUtil.getCDNHost(request);
			String themeStaticResourcePath = theme.getStaticResourcePath();

			themeImagesPath =
				cdnHost + themeStaticResourcePath + theme.getImagesPath();
		}

		return themeImagesPath;
	}

	private static final String _CSS_IMPORT_BEGIN = "@import url(";

	private static final String _CSS_IMPORT_END = ");";

	private static final Log _log = LogFactoryUtil.getLog(DynamicCSSUtil.class);

	private static final Pattern _pluginThemePattern = Pattern.compile(
		"\\/([^\\/]+)-theme\\/", Pattern.CASE_INSENSITIVE);
	private static final Pattern _portalThemePattern = Pattern.compile(
		"themes\\/([^\\/]+)\\/css", Pattern.CASE_INSENSITIVE);

}