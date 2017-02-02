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

package com.liferay.calendar.search;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarBookingLocalService;
import com.liferay.calendar.service.CalendarLocalService;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adam Brandizzi
 */
@Component(immediate = true, service = Indexer.class)
public class CalendarIndexer extends BaseIndexer<Calendar> {

	public static final String CLASS_NAME = Calendar.class.getName();

	public CalendarIndexer() {
		setDefaultSelectedFieldNames(
			Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
			Field.UID);
		setDefaultSelectedLocalizedFieldNames(
			Field.DESCRIPTION, Field.NAME, "resourceName");
		setFilterSearch(true);
		setPermissionAware(true);
		setSelectAllLocales(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, String entryClassName,
			long entryClassPK, String actionId)
		throws Exception {

		return CalendarPermission.contains(
			permissionChecker, entryClassPK, ActionKeys.VIEW);
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		addSearchLocalizedTerm(
			searchQuery, searchContext, Field.DESCRIPTION, true);
		addSearchLocalizedTerm(searchQuery, searchContext, Field.NAME, true);
		addSearchLocalizedTerm(
			searchQuery, searchContext, "resourceName", true);
	}

	@Override
	protected void doDelete(Calendar calendar) throws Exception {
		deleteDocument(calendar.getCompanyId(), calendar.getCalendarId());
	}

	@Override
	protected Document doGetDocument(Calendar calendar) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, calendar);

		document.addLocalizedKeyword(
			Field.DESCRIPTION, calendar.getDescriptionMap(), true);
		document.addLocalizedKeyword(Field.NAME, calendar.getNameMap(), true);
		document.addKeyword("calendarId", calendar.getCalendarId());

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		document.addText("defaultLanguageId", defaultLanguageId);

		CalendarResource calendarResource = calendar.getCalendarResource();

		document.addLocalizedKeyword(
			"resourceName", calendarResource.getNameMap(), true);

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Summary summary = createSummary(
			document, Field.NAME, Field.DESCRIPTION);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(Calendar calendar) throws Exception {
		Document document = getDocument(calendar);

		IndexWriterHelperUtil.updateDocument(
			getSearchEngineId(), calendar.getCompanyId(), document,
			isCommitImmediately());

		reindexCalendarBookings(calendar);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Calendar calendar = _calendarLocalService.getCalendar(classPK);

		doReindex(calendar);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexCalendars(companyId);
	}

	protected void reindexCalendarBookings(Calendar calendar)
		throws SearchException {

		Indexer<CalendarBooking> indexer = _indexerRegistry.nullSafeGetIndexer(
			CalendarBooking.class);

		List<CalendarBooking> calendarBookings =
			_calendarBookingLocalService.getCalendarBookings(
				calendar.getCalendarId());

		indexer.reindex(calendarBookings);
	}

	protected void reindexCalendars(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			_calendarLocalService.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Calendar>() {

				@Override
				public void performAction(Calendar calendar) {
					try {
						Document document = getDocument(calendar);

						indexableActionableDynamicQuery.addDocuments(document);
					}
					catch (PortalException pe) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to index calendar " +
									calendar.getCalendarId(),
								pe);
						}
					}
				}

			});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	@Reference(unbind = "-")
	protected void setCalendarBookingLocalService(
		CalendarBookingLocalService calendarBookingLocalService) {

		_calendarBookingLocalService = calendarBookingLocalService;
	}

	@Reference(unbind = "-")
	protected void setCalendarLocalService(
		CalendarLocalService calendarLocalService) {

		_calendarLocalService = calendarLocalService;
	}

	@Reference(unbind = "-")
	protected void setIndexerRegistry(IndexerRegistry indexerRegistry) {
		_indexerRegistry = indexerRegistry;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CalendarIndexer.class);

	private CalendarBookingLocalService _calendarBookingLocalService;
	private CalendarLocalService _calendarLocalService;
	private IndexerRegistry _indexerRegistry;

}