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

package com.liferay.taglib.search;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.Format;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
public class DateSearchEntry extends TextSearchEntry {

	public Date getDate() {
		return _date;
	}

	@Override
	public String getName(HttpServletRequest request) {
		if (_date != null) {
			Object[] localeAndTimeZone = getLocaleAndTimeZone(request);

			Locale locale = (Locale)localeAndTimeZone[0];

			Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
				locale, (TimeZone)localeAndTimeZone[1]);

			StringBundler sb = new StringBundler(5);

			sb.append(
				"<span onmouseover=\"Liferay.Portal.ToolTip.show(this, '");
			sb.append(dateFormatDateTime.format(_date));
			sb.append("')\">");

			if (_date.before(new Date())) {
				sb.append(
					LanguageUtil.format(
						locale, "x-ago",
						LanguageUtil.getTimeDescription(
							locale,
							System.currentTimeMillis() - _date.getTime(), true),
						false));
			}
			else {
				sb.append(
					LanguageUtil.format(
						locale, "within-x",
						LanguageUtil.getTimeDescription(
							locale,
							_date.getTime() - System.currentTimeMillis(), true),
						false));
			}

			sb.append("</span>");

			return sb.toString();
		}
		else {
			return StringPool.BLANK;
		}
	}

	public void setDate(Date date) {
		_date = date;
	}

	protected Object[] getLocaleAndTimeZone(HttpServletRequest request) {
		if ((_locale != null) && (_timeZone != null)) {
			return new Object[] {_locale, _timeZone};
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_locale = themeDisplay.getLocale();
		_timeZone = themeDisplay.getTimeZone();

		return new Object[] {_locale, _timeZone};
	}

	private Date _date;
	private Locale _locale;
	private TimeZone _timeZone;

}