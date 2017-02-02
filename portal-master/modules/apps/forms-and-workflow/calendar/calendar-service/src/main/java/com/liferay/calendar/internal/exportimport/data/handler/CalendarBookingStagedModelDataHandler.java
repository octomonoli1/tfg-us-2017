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

package com.liferay.calendar.internal.exportimport.data.handler;

import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.service.CalendarBookingLocalService;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.exportimport.lar.BaseStagedModelDataHandler;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.service.MBMessageLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 * @author Daniel Kocsis
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=" + CalendarPortletKeys.CALENDAR},
	service = StagedModelDataHandler.class
)
public class CalendarBookingStagedModelDataHandler
	extends BaseStagedModelDataHandler<CalendarBooking> {

	public static final String[] CLASS_NAMES =
		{CalendarBooking.class.getName()};

	@Override
	public void deleteStagedModel(CalendarBooking calendarBooking)
		throws PortalException {

		_calendarBookingLocalService.deleteCalendarBooking(calendarBooking);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		CalendarBooking calendarBooking = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (calendarBooking != null) {
			deleteStagedModel(calendarBooking);
		}
	}

	@Override
	public CalendarBooking fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _calendarBookingLocalService.
			fetchCalendarBookingByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public List<CalendarBooking> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _calendarBookingLocalService.
			getCalendarBookingsByUuidAndCompanyId(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new StagedModelModifiedDateComparator<CalendarBooking>());
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(CalendarBooking calendarBooking) {
		return calendarBooking.getTitleCurrentValue();
	}

	@Override
	public int[] getExportableStatuses() {
		return _EXPORTABLE_STATUSES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			CalendarBooking calendarBooking)
		throws Exception {

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, calendarBooking, calendarBooking.getCalendar(),
			PortletDataContext.REFERENCE_TYPE_STRONG);

		if (!calendarBooking.isMasterBooking()) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, calendarBooking,
				calendarBooking.getParentCalendarBooking(),
				PortletDataContext.REFERENCE_TYPE_STRONG);
		}

		Element calendarBookingElement =
			portletDataContext.getExportDataElement(calendarBooking);

		portletDataContext.addClassedModel(
			calendarBookingElement,
			ExportImportPathUtil.getModelPath(calendarBooking),
			calendarBooking);
	}

	@Override
	protected void doImportMissingReference(
			PortletDataContext portletDataContext, String uuid, long groupId,
			long calendarBookingId)
		throws Exception {

		CalendarBooking existingCalendarBooking = fetchMissingReference(
			uuid, groupId);

		if (existingCalendarBooking == null) {
			return;
		}

		Map<Long, Long> calendarBookingIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				CalendarBooking.class);

		calendarBookingIds.put(
			calendarBookingId, existingCalendarBooking.getCalendarBookingId());
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			CalendarBooking calendarBooking)
		throws Exception {

		long userId = portletDataContext.getUserId(
			calendarBooking.getUserUuid());

		Map<Long, Long> calendarIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Calendar.class);

		long calendarId = MapUtil.getLong(
			calendarIds, calendarBooking.getCalendarId(),
			calendarBooking.getCalendarId());

		long parentCalendarBookingId =
			CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT;

		if (!calendarBooking.isMasterBooking()) {
			Map<Long, Long> calendarBookingIds =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					CalendarBooking.class);

			parentCalendarBookingId = MapUtil.getLong(
				calendarBookingIds,
				calendarBooking.getParentCalendarBookingId(),
				calendarBooking.getParentCalendarBookingId());
		}

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			calendarBooking);

		CalendarBooking importedCalendarBooking = null;

		if (portletDataContext.isDataStrategyMirror()) {
			CalendarBooking existingCalendarBooking =
				fetchStagedModelByUuidAndGroupId(
					calendarBooking.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingCalendarBooking == null) {
				serviceContext.setUuid(calendarBooking.getUuid());

				importedCalendarBooking =
					_calendarBookingLocalService.addCalendarBooking(
						userId, calendarId, new long[0],
						parentCalendarBookingId, calendarBooking.getTitleMap(),
						calendarBooking.getDescriptionMap(),
						calendarBooking.getLocation(),
						calendarBooking.getStartTime(),
						calendarBooking.getEndTime(),
						calendarBooking.isAllDay(),
						calendarBooking.getRecurrence(),
						calendarBooking.getFirstReminder(),
						calendarBooking.getFirstReminderType(),
						calendarBooking.getSecondReminder(),
						calendarBooking.getSecondReminderType(),
						serviceContext);
			}
			else {
				importedCalendarBooking =
					_calendarBookingLocalService.updateCalendarBooking(
						userId, existingCalendarBooking.getCalendarBookingId(),
						calendarId, calendarBooking.getTitleMap(),
						calendarBooking.getDescriptionMap(),
						calendarBooking.getLocation(),
						calendarBooking.getStartTime(),
						calendarBooking.getEndTime(),
						calendarBooking.isAllDay(),
						calendarBooking.getRecurrence(),
						calendarBooking.getFirstReminder(),
						calendarBooking.getFirstReminderType(),
						calendarBooking.getSecondReminder(),
						calendarBooking.getSecondReminderType(),
						serviceContext);
			}
		}
		else {
			importedCalendarBooking =
				_calendarBookingLocalService.addCalendarBooking(
					userId, calendarId, new long[0], parentCalendarBookingId,
					calendarBooking.getTitleMap(),
					calendarBooking.getDescriptionMap(),
					calendarBooking.getLocation(),
					calendarBooking.getStartTime(),
					calendarBooking.getEndTime(), calendarBooking.isAllDay(),
					calendarBooking.getRecurrence(),
					calendarBooking.getFirstReminder(),
					calendarBooking.getFirstReminderType(),
					calendarBooking.getSecondReminder(),
					calendarBooking.getSecondReminderType(), serviceContext);
		}

		// The root discussion message is not automatically imported when
		// importing a calendar booking

		List<Element> mbMessageElements =
			portletDataContext.getReferenceElements(
				calendarBooking, MBMessage.class);

		if (ListUtil.isNotEmpty(mbMessageElements)) {
			_mbMessageLocalService.addDiscussionMessage(
				userId, importedCalendarBooking.getUserName(),
				importedCalendarBooking.getGroupId(),
				CalendarBooking.class.getName(),
				importedCalendarBooking.getCalendarBookingId(),
				WorkflowConstants.ACTION_PUBLISH);
		}

		portletDataContext.importClassedModel(
			calendarBooking, importedCalendarBooking);
	}

	@Override
	protected void doRestoreStagedModel(
			PortletDataContext portletDataContext,
			CalendarBooking calendarBooking)
		throws Exception {

		long userId = portletDataContext.getUserId(
			calendarBooking.getUserUuid());

		CalendarBooking existingBooking = fetchStagedModelByUuidAndGroupId(
			calendarBooking.getUuid(), portletDataContext.getScopeGroupId());

		if ((existingBooking == null) || !existingBooking.isInTrash()) {
			return;
		}

		TrashHandler trashHandler = existingBooking.getTrashHandler();

		if (trashHandler.isRestorable(existingBooking.getCalendarBookingId())) {
			trashHandler.restoreTrashEntry(
				userId, existingBooking.getCalendarBookingId());
		}
	}

	@Reference(unbind = "-")
	protected void setCalendarBookingLocalService(
		CalendarBookingLocalService calendarBookingLocalService) {

		_calendarBookingLocalService = calendarBookingLocalService;
	}

	@Reference(unbind = "-")
	protected void setMBMessageLocalService(
		MBMessageLocalService mbMessageLocalService) {

		_mbMessageLocalService = mbMessageLocalService;
	}

	private static final int[] _EXPORTABLE_STATUSES = {
		CalendarBookingWorkflowConstants.STATUS_APPROVED,
		CalendarBookingWorkflowConstants.STATUS_DENIED,
		CalendarBookingWorkflowConstants.STATUS_MAYBE,
		CalendarBookingWorkflowConstants.STATUS_PENDING
	};

	private CalendarBookingLocalService _calendarBookingLocalService;
	private MBMessageLocalService _mbMessageLocalService;

}