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

package com.liferay.exportimport.kernel.lar;

import static com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT;
import static com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants.TYPE_EXPORT_PORTLET;
import static com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants.TYPE_IMPORT_LAYOUT;
import static com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants.TYPE_IMPORT_PORTLET;
import static com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_LOCAL;
import static com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_REMOTE;
import static com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants.TYPE_PUBLISH_PORTLET;
import static com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants.TYPE_SCHEDULED_PUBLISH_LAYOUT_LOCAL;
import static com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants.TYPE_SCHEDULED_PUBLISH_LAYOUT_REMOTE;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.DateRange;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

/**
 * @author Mate Thurzo
 */
@ProviderType
public class ExportImportDateUtil {

	public static final String RANGE = "range";

	public static final String RANGE_ALL = "all";

	public static final String RANGE_DATE_RANGE = "dateRange";

	public static final String RANGE_FROM_LAST_PUBLISH_DATE =
		"fromLastPublishDate";

	public static final String RANGE_LAST = "last";

	public static void clearLastPublishDate(long groupId, boolean privateLayout)
		throws PortalException {

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
			groupId, privateLayout);

		UnicodeProperties settingsProperties =
			layoutSet.getSettingsProperties();

		settingsProperties.remove(_LAST_PUBLISH_DATE);

		LayoutSetLocalServiceUtil.updateSettings(
			groupId, privateLayout, settingsProperties.toString());
	}

	public static Calendar getCalendar(
		PortletRequest portletRequest, String paramPrefix,
		boolean timeZoneSensitive) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		int dateMonth = ParamUtil.getInteger(
			portletRequest, paramPrefix + "Month");
		int dateDay = ParamUtil.getInteger(portletRequest, paramPrefix + "Day");
		int dateYear = ParamUtil.getInteger(
			portletRequest, paramPrefix + "Year");
		int dateHour = ParamUtil.getInteger(
			portletRequest, paramPrefix + "Hour");
		int dateMinute = ParamUtil.getInteger(
			portletRequest, paramPrefix + "Minute");
		int dateAmPm = ParamUtil.getInteger(
			portletRequest, paramPrefix + "AmPm");

		return getCalendar(
			dateAmPm, dateYear, dateMonth, dateDay, dateHour, dateMinute,
			themeDisplay.getLocale(), themeDisplay.getTimeZone(),
			timeZoneSensitive);
	}

	public static DateRange getDateRange(
			ExportImportConfiguration exportImportConfiguration)
		throws PortalException {

		Map<String, Serializable> settingsMap =
			exportImportConfiguration.getSettingsMap();

		String portletId = (String)settingsMap.get("portletId");

		return getDateRange(exportImportConfiguration, portletId);
	}

	public static DateRange getDateRange(
			ExportImportConfiguration exportImportConfiguration,
			String portletId)
		throws PortalException {

		Map<String, Serializable> settingsMap =
			exportImportConfiguration.getSettingsMap();

		Date startDate = (Date)settingsMap.get("startDate");
		Date endDate = (Date)settingsMap.get("endDate");

		if ((startDate != null) && (endDate != null)) {
			return new DateRange(startDate, endDate);
		}

		Map<String, String[]> parameterMap =
			(Map<String, String[]>)settingsMap.get("parameterMap");

		String range = MapUtil.getString(
			parameterMap, RANGE,
			getDefaultDateRange(exportImportConfiguration));
		int rangeLast = MapUtil.getInteger(parameterMap, "last");
		int startDateAmPm = MapUtil.getInteger(parameterMap, "startDateAmPm");
		int startDateYear = MapUtil.getInteger(parameterMap, "startDateYear");
		int startDateMonth = MapUtil.getInteger(parameterMap, "startDateMonth");
		int startDateDay = MapUtil.getInteger(parameterMap, "startDateDay");
		int startDateHour = MapUtil.getInteger(parameterMap, "startDateHour");
		int startDateMinute = MapUtil.getInteger(
			parameterMap, "startDateMinute");
		int endDateAmPm = MapUtil.getInteger(parameterMap, "endDateAmPm");
		int endDateYear = MapUtil.getInteger(parameterMap, "endDateYear");
		int endDateMonth = MapUtil.getInteger(parameterMap, "endDateMonth");
		int endDateDay = MapUtil.getInteger(parameterMap, "endDateDay");
		int endDateHour = MapUtil.getInteger(parameterMap, "endDateHour");
		int endDateMinute = MapUtil.getInteger(parameterMap, "endDateMinute");

		long groupId = MapUtil.getLong(settingsMap, "sourceGroupId");
		long plid = MapUtil.getLong(settingsMap, "sourcePlid");
		boolean privateLayout = MapUtil.getBoolean(
			settingsMap, "privateLayout");
		Locale locale = (Locale)settingsMap.get("locale");
		TimeZone timeZone = (TimeZone)settingsMap.get("timezone");

		return getDateRange(
			range, rangeLast, startDateAmPm, startDateYear, startDateMonth,
			startDateDay, startDateHour, startDateMinute, endDateAmPm,
			endDateYear, endDateMonth, endDateDay, endDateHour, endDateMinute,
			portletId, groupId, plid, privateLayout, locale, timeZone);
	}

	public static DateRange getDateRange(long exportImportConfigurationId)
		throws PortalException {

		ExportImportConfiguration exportImportConfiguration =
			ExportImportConfigurationLocalServiceUtil.
				getExportImportConfiguration(exportImportConfigurationId);

		return getDateRange(exportImportConfiguration);
	}

	public static DateRange getDateRange(
			PortletRequest portletRequest, long groupId, boolean privateLayout,
			long plid, String portletId, String defaultRange)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String range = ParamUtil.getString(portletRequest, RANGE, defaultRange);
		int rangeLast = ParamUtil.getInteger(portletRequest, "last");
		int startDateAmPm = ParamUtil.getInteger(
			portletRequest, "startDateAmPm");
		int startDateYear = ParamUtil.getInteger(
			portletRequest, "startDateYear");
		int startDateMonth = ParamUtil.getInteger(
			portletRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(portletRequest, "startDateDay");
		int startDateHour = ParamUtil.getInteger(
			portletRequest, "startDateHour");
		int startDateMinute = ParamUtil.getInteger(
			portletRequest, "startDateMinute");
		int endDateAmPm = ParamUtil.getInteger(portletRequest, "endDateAmPm");
		int endDateYear = ParamUtil.getInteger(portletRequest, "endDateYear");
		int endDateMonth = ParamUtil.getInteger(portletRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(portletRequest, "endDateDay");
		int endDateHour = ParamUtil.getInteger(portletRequest, "endDateHour");
		int endDateMinute = ParamUtil.getInteger(
			portletRequest, "endDateMinute");

		return getDateRange(
			range, rangeLast, startDateAmPm, startDateYear, startDateMonth,
			startDateDay, startDateHour, startDateMinute, endDateAmPm,
			endDateYear, endDateMonth, endDateDay, endDateHour, endDateMinute,
			portletId, groupId, plid, privateLayout, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());
	}

	public static Date getLastPublishDate(LayoutSet layoutSet) {
		long lastPublishDate = GetterUtil.getLong(
			layoutSet.getSettingsProperty(_LAST_PUBLISH_DATE));

		if (lastPublishDate == 0) {
			return null;
		}

		return new Date(lastPublishDate);
	}

	public static Date getLastPublishDate(
			PortletDataContext portletDataContext,
			PortletPreferences jxPortletPreferences)
		throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(
			portletDataContext.getGroupId());

		String range = MapUtil.getString(
			portletDataContext.getParameterMap(), RANGE);

		if (!group.isStagedRemotely() &&
			range.equals(RANGE_FROM_LAST_PUBLISH_DATE)) {

			Date portletLastPublishDate = getLastPublishDate(
				jxPortletPreferences);

			if (portletLastPublishDate == null) {
				return null;
			}

			// This is a valid scenario in case of group level portlets

			if (portletDataContext.getStartDate() == null) {
				return portletLastPublishDate;
			}

			if (portletLastPublishDate.before(
					portletDataContext.getStartDate())) {

				return portletLastPublishDate;
			}
		}

		return portletDataContext.getStartDate();
	}

	public static Date getLastPublishDate(
		PortletPreferences jxPortletPreferences) {

		long lastPublishDate = GetterUtil.getLong(
			jxPortletPreferences.getValue(
				_LAST_PUBLISH_DATE, StringPool.BLANK));

		if (lastPublishDate == 0) {
			return null;
		}

		return new Date(lastPublishDate);
	}

	public static void updateLastPublishDate(
			long groupId, boolean privateLayout, DateRange dateRange,
			Date lastPublishDate)
		throws PortalException {

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
			groupId, privateLayout);

		Date originalLastPublishDate = getLastPublishDate(layoutSet);

		if (!isValidDateRange(dateRange, originalLastPublishDate)) {
			return;
		}

		if (lastPublishDate == null) {
			lastPublishDate = new Date();
		}

		UnicodeProperties settingsProperties =
			layoutSet.getSettingsProperties();

		settingsProperties.setProperty(
			_LAST_PUBLISH_DATE, String.valueOf(lastPublishDate.getTime()));

		LayoutSetLocalServiceUtil.updateSettings(
			layoutSet.getGroupId(), layoutSet.isPrivateLayout(),
			settingsProperties.toString());
	}

	public static void updateLastPublishDate(
		StagedGroupedModel stagedGroupedModel, DateRange dateRange,
		Date lastPublishDate) {

		Date originalLastPublishDate = stagedGroupedModel.getLastPublishDate();

		if (!isValidDateRange(dateRange, originalLastPublishDate)) {
			return;
		}

		if (lastPublishDate == null) {
			lastPublishDate = new Date();
		}

		stagedGroupedModel.setLastPublishDate(lastPublishDate);
	}

	public static void updateLastPublishDate(
		String portletId, PortletPreferences portletPreferences,
		DateRange dateRange, Date lastPublishDate) {

		Date originalLastPublishDate = getLastPublishDate(portletPreferences);

		if (!isValidDateRange(dateRange, originalLastPublishDate)) {
			return;
		}

		if (lastPublishDate == null) {
			lastPublishDate = new Date();
		}

		try {
			portletPreferences.setValue(
				_LAST_PUBLISH_DATE, String.valueOf(lastPublishDate.getTime()));

			portletPreferences.store();
		}
		catch (UnsupportedOperationException uoe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Not updating the portlet setup for " + portletId +
						" because no setup was returned for the current " +
							"page");
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected static Calendar getCalendar(
		int dateAmPm, int dateYear, int dateMonth, int dateDay, int dateHour,
		int dateMinute, Locale locale, TimeZone timeZone,
		boolean timeZoneSensitive) {

		if (dateAmPm == Calendar.PM) {
			dateHour += 12;
		}

		if (!timeZoneSensitive) {
			locale = LocaleUtil.getDefault();
			timeZone = TimeZoneUtil.getTimeZone(StringPool.UTC);
		}

		Calendar calendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

		calendar.set(Calendar.MONTH, dateMonth);
		calendar.set(Calendar.DATE, dateDay);
		calendar.set(Calendar.YEAR, dateYear);
		calendar.set(Calendar.HOUR_OF_DAY, dateHour);
		calendar.set(Calendar.MINUTE, dateMinute);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar;
	}

	protected static DateRange getDateRange(
			String range, int rangeLast, int startDateAmPm, int startDateYear,
			int startDateMonth, int startDateDay, int startDateHour,
			int startDateMinute, int endDateAmPm, int endDateYear,
			int endDateMonth, int endDateDay, int endDateHour,
			int endDateMinute, String portletId, long groupId, long plid,
			boolean privateLayout, Locale locale, TimeZone timeZone)
		throws PortalException {

		Date startDate = null;
		Date endDate = null;

		if (range.equals(RANGE_DATE_RANGE)) {
			Calendar startCalendar = getCalendar(
				startDateAmPm, startDateYear, startDateMonth, startDateDay,
				startDateHour, startDateMinute, locale, timeZone, true);

			startDate = startCalendar.getTime();

			Calendar endCalendar = getCalendar(
				endDateAmPm, endDateYear, endDateMonth, endDateDay, endDateHour,
				endDateMinute, locale, timeZone, true);

			endDate = endCalendar.getTime();
		}
		else if (range.equals(RANGE_FROM_LAST_PUBLISH_DATE)) {
			Date lastPublishDate = null;

			if (Validator.isNotNull(portletId)) {
				Layout layout = LayoutLocalServiceUtil.fetchLayout(plid);

				PortletPreferences portletPreferences = null;

				if (layout == null) {
					Group group = GroupLocalServiceUtil.getGroup(groupId);

					portletPreferences =
						PortletPreferencesFactoryUtil.getStrictPortletSetup(
							group.getCompanyId(), groupId, portletId);
				}
				else {
					portletPreferences =
						PortletPreferencesFactoryUtil.getStrictPortletSetup(
							layout, portletId);
				}

				lastPublishDate = getLastPublishDate(portletPreferences);
			}
			else {
				LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
					groupId, privateLayout);

				lastPublishDate = getLastPublishDate(layoutSet);
			}

			if (lastPublishDate != null) {
				endDate = new Date();

				startDate = lastPublishDate;
			}
		}
		else if (range.equals(RANGE_LAST)) {
			Date now = new Date();

			startDate = new Date(now.getTime() - (rangeLast * Time.HOUR));

			endDate = now;
		}

		return new DateRange(startDate, endDate);
	}

	protected static String getDefaultDateRange(
		ExportImportConfiguration exportImportConfiguration) {

		if (exportImportConfiguration.getType() == TYPE_EXPORT_LAYOUT) {
			return RANGE_ALL;
		}
		else if (exportImportConfiguration.getType() == TYPE_EXPORT_PORTLET) {
			return RANGE_ALL;
		}
		else if (exportImportConfiguration.getType() == TYPE_IMPORT_LAYOUT) {
			return RANGE_ALL;
		}
		else if (exportImportConfiguration.getType() == TYPE_IMPORT_PORTLET) {
			return RANGE_ALL;
		}
		else if (exportImportConfiguration.getType() ==
					TYPE_PUBLISH_LAYOUT_LOCAL) {

			return RANGE_FROM_LAST_PUBLISH_DATE;
		}
		else if (exportImportConfiguration.getType() ==
					TYPE_PUBLISH_LAYOUT_REMOTE) {

			return RANGE_FROM_LAST_PUBLISH_DATE;
		}
		else if (exportImportConfiguration.getType() == TYPE_PUBLISH_PORTLET) {
			return RANGE_FROM_LAST_PUBLISH_DATE;
		}
		else if (exportImportConfiguration.getType() ==
					TYPE_SCHEDULED_PUBLISH_LAYOUT_LOCAL) {

			return RANGE_FROM_LAST_PUBLISH_DATE;
		}
		else if (exportImportConfiguration.getType() ==
					TYPE_SCHEDULED_PUBLISH_LAYOUT_REMOTE) {

			return RANGE_FROM_LAST_PUBLISH_DATE;
		}

		return RANGE_ALL;
	}

	protected static boolean isValidDateRange(
		DateRange dateRange, Date originalLastPublishDate) {

		if (dateRange == null) {

			// This is a valid scenario when publishing all

			return true;
		}

		Date startDate = dateRange.getStartDate();
		Date endDate = dateRange.getEndDate();

		if (originalLastPublishDate != null) {
			if ((startDate != null) &&
				startDate.after(originalLastPublishDate)) {

				return false;
			}

			if ((endDate != null) && endDate.before(originalLastPublishDate)) {
				return false;
			}
		}
		else if ((startDate != null) || (endDate != null)) {
			return false;
		}

		return true;
	}

	private static final String _LAST_PUBLISH_DATE = "last-publish-date";

	private static final Log _log = LogFactoryUtil.getLog(
		ExportImportDateUtil.class);

}