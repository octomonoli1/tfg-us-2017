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

package com.liferay.calendar.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.calendar.exception.CalendarBookingDurationException;
import com.liferay.calendar.exception.CalendarBookingRecurrenceException;
import com.liferay.calendar.exporter.CalendarDataFormat;
import com.liferay.calendar.exporter.CalendarDataHandler;
import com.liferay.calendar.exporter.CalendarDataHandlerFactory;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.notification.NotificationTemplateType;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.notification.impl.NotificationUtil;
import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.recurrence.RecurrenceSerializer;
import com.liferay.calendar.service.base.CalendarBookingLocalServiceBaseImpl;
import com.liferay.calendar.service.configuration.CalendarServiceConfigurationValues;
import com.liferay.calendar.social.CalendarActivityKeys;
import com.liferay.calendar.util.JCalendarUtil;
import com.liferay.calendar.util.RecurrenceUtil;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.sanitizer.Sanitizer;
import com.liferay.portal.kernel.sanitizer.SanitizerUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.trash.kernel.exception.RestoreEntryException;
import com.liferay.trash.kernel.exception.TrashEntryException;
import com.liferay.trash.kernel.model.TrashEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Marcellus Tavares
 * @author Pier Paolo Ramon
 */
public class CalendarBookingLocalServiceImpl
	extends CalendarBookingLocalServiceBaseImpl {

	@Override
	public CalendarBooking addCalendarBooking(
			long userId, long calendarId, long[] childCalendarIds,
			long parentCalendarBookingId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, long startTime,
			long endTime, boolean allDay, String recurrence, long firstReminder,
			String firstReminderType, long secondReminder,
			String secondReminderType, ServiceContext serviceContext)
		throws PortalException {

		// Calendar booking

		User user = userPersistence.findByPrimaryKey(userId);
		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);

		long calendarBookingId = counterLocalService.increment();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			String sanitizedDescription = SanitizerUtil.sanitize(
				calendar.getCompanyId(), calendar.getGroupId(), userId,
				CalendarBooking.class.getName(), calendarBookingId,
				ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL, entry.getValue(),
				null);

			descriptionMap.put(entry.getKey(), sanitizedDescription);
		}

		java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			startTime);
		java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(
			endTime);

		if (allDay) {
			startTimeJCalendar = JCalendarUtil.toMidnightJCalendar(
				startTimeJCalendar);
			endTimeJCalendar = JCalendarUtil.toLastHourJCalendar(
				endTimeJCalendar);
		}

		if (firstReminder < secondReminder) {
			long originalSecondReminder = secondReminder;

			secondReminder = firstReminder;
			firstReminder = originalSecondReminder;
		}

		Date now = new Date();

		validate(startTimeJCalendar, endTimeJCalendar, recurrence);

		CalendarBooking calendarBooking = calendarBookingPersistence.create(
			calendarBookingId);

		calendarBooking.setGroupId(calendar.getGroupId());
		calendarBooking.setCompanyId(user.getCompanyId());
		calendarBooking.setUserId(user.getUserId());
		calendarBooking.setUserName(user.getFullName());
		calendarBooking.setCreateDate(serviceContext.getCreateDate(now));
		calendarBooking.setModifiedDate(serviceContext.getModifiedDate(now));
		calendarBooking.setCalendarId(calendarId);
		calendarBooking.setCalendarResourceId(calendar.getCalendarResourceId());

		if (parentCalendarBookingId > 0) {
			calendarBooking.setParentCalendarBookingId(parentCalendarBookingId);
		}
		else {
			calendarBooking.setParentCalendarBookingId(calendarBookingId);
		}

		String vEventUid = (String)serviceContext.getAttribute("vEventUid");

		if (vEventUid == null) {
			vEventUid = PortalUUIDUtil.generate();
		}

		calendarBooking.setVEventUid(vEventUid);
		calendarBooking.setTitleMap(titleMap, serviceContext.getLocale());
		calendarBooking.setDescriptionMap(
			descriptionMap, serviceContext.getLocale());
		calendarBooking.setLocation(location);
		calendarBooking.setStartTime(startTimeJCalendar.getTimeInMillis());
		calendarBooking.setEndTime(endTimeJCalendar.getTimeInMillis());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setFirstReminder(firstReminder);
		calendarBooking.setFirstReminderType(firstReminderType);
		calendarBooking.setSecondReminder(secondReminder);
		calendarBooking.setSecondReminderType(secondReminderType);
		calendarBooking.setExpandoBridgeAttributes(serviceContext);

		if (calendarBooking.isMasterBooking()) {
			calendarBooking.setStatus(
				CalendarBookingWorkflowConstants.STATUS_DRAFT);
		}
		else {
			calendarBooking.setStatus(
				CalendarBookingWorkflowConstants.STATUS_MASTER_PENDING);
		}

		calendarBooking.setStatusDate(serviceContext.getModifiedDate(now));

		calendarBookingPersistence.update(calendarBooking);

		addChildCalendarBookings(
			calendarBooking, childCalendarIds, serviceContext);

		// Resources

		resourceLocalService.addModelResources(calendarBooking, serviceContext);

		// Asset

		updateAsset(
			userId, calendarBooking, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds(),
			serviceContext.getAssetPriority());

		// Social

		socialActivityLocalService.addActivity(
			userId, calendarBooking.getGroupId(),
			CalendarBooking.class.getName(), calendarBookingId,
			CalendarActivityKeys.ADD_CALENDAR_BOOKING,
			getExtraDataJSON(calendarBooking), 0);

		// Notifications

		sendNotification(
			calendarBooking, NotificationTemplateType.INVITE, serviceContext);

		// Workflow

		if (calendarBooking.isMasterBooking()) {
			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				calendarBooking.getCompanyId(), calendarBooking.getGroupId(),
				userId, CalendarBooking.class.getName(),
				calendarBooking.getCalendarBookingId(), calendarBooking,
				serviceContext);
		}

		return calendarBooking;
	}

	@Override
	public void checkCalendarBookings() throws PortalException {
		Date now = new Date();

		List<CalendarBooking> calendarBookings =
			calendarBookingFinder.findByFutureReminders(now.getTime());

		long endTime = now.getTime() + Time.MONTH;

		calendarBookings = RecurrenceUtil.expandCalendarBookings(
			calendarBookings, now.getTime(), endTime, 1);

		for (CalendarBooking calendarBooking : calendarBookings) {
			try {
				Company company = companyPersistence.findByPrimaryKey(
					calendarBooking.getCompanyId());

				if (company.isActive()) {
					NotificationUtil.notifyCalendarBookingReminders(
						calendarBooking, now.getTime());
				}
			}
			catch (PortalException pe) {
				throw pe;
			}
			catch (SystemException se) {
				throw se;
			}
			catch (Exception e) {
				throw new SystemException(e);
			}
		}
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(
		action = SystemEventConstants.ACTION_SKIP,
		type = SystemEventConstants.TYPE_DELETE
	)
	public CalendarBooking deleteCalendarBooking(
			CalendarBooking calendarBooking)
		throws PortalException {

		// Calendar booking

		calendarBookingPersistence.remove(calendarBooking);

		// Calendar bookings

		List<CalendarBooking> childCalendarBookings = getChildCalendarBookings(
			calendarBooking.getCalendarBookingId());

		for (CalendarBooking childCalendarBooking : childCalendarBookings) {
			calendarBookingLocalService.deleteCalendarBooking(
				childCalendarBooking);
		}

		// Resources

		resourceLocalService.deleteResource(
			calendarBooking, ResourceConstants.SCOPE_INDIVIDUAL);

		// Subscriptions

		subscriptionLocalService.deleteSubscriptions(
			calendarBooking.getCompanyId(), CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

		// Asset

		assetEntryLocalService.deleteEntry(
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

		// Message boards

		mbMessageLocalService.deleteDiscussionMessages(
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

		// Ratings

		ratingsStatsLocalService.deleteStats(
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

		// Trash

		trashEntryLocalService.deleteEntry(
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

		// Workflow

		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			calendarBooking.getCompanyId(), calendarBooking.getGroupId(),
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

		return calendarBooking;
	}

	@Override
	public CalendarBooking deleteCalendarBooking(long calendarBookingId)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		calendarBookingLocalService.deleteCalendarBooking(calendarBooking);

		return calendarBooking;
	}

	@Override
	public void deleteCalendarBookingInstance(
			CalendarBooking calendarBooking, int instanceIndex,
			boolean allFollowing)
		throws PortalException {

		CalendarBooking calendarBookingInstance =
			RecurrenceUtil.getCalendarBookingInstance(
				calendarBooking, instanceIndex);

		deleteCalendarBookingInstance(
			calendarBooking, calendarBookingInstance.getStartTime(),
			allFollowing);
	}

	@Override
	public void deleteCalendarBookingInstance(
			CalendarBooking calendarBooking, long startTime,
			boolean allFollowing)
		throws PortalException {

		Date now = new Date();

		java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			startTime, calendarBooking.getTimeZone());

		Recurrence recurrenceObj = calendarBooking.getRecurrenceObj();

		if (allFollowing) {
			if (startTime == calendarBooking.getStartTime()) {
				calendarBookingLocalService.deleteCalendarBooking(
					calendarBooking);

				return;
			}

			if (recurrenceObj.getCount() > 0) {
				recurrenceObj.setCount(0);
			}

			startTimeJCalendar.add(java.util.Calendar.DATE, -1);

			recurrenceObj.setUntilJCalendar(startTimeJCalendar);
		}
		else {
			CalendarBooking calendarBookingInstance =
				RecurrenceUtil.getCalendarBookingInstance(calendarBooking, 1);

			if (calendarBookingInstance == null) {
				calendarBookingLocalService.deleteCalendarBooking(
					calendarBooking);

				return;
			}

			recurrenceObj.addExceptionDate(startTimeJCalendar);
		}

		String recurrence = RecurrenceSerializer.serialize(recurrenceObj);

		updateChildCalendarBookings(calendarBooking, now, recurrence);
	}

	@Override
	public void deleteCalendarBookingInstance(
			long calendarBookingId, long startTime, boolean allFollowing)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		deleteCalendarBookingInstance(calendarBooking, startTime, allFollowing);
	}

	@Override
	public void deleteCalendarBookings(long calendarId) throws PortalException {
		List<CalendarBooking> calendarBookings =
			calendarBookingPersistence.findByCalendarId(calendarId);

		for (CalendarBooking calendarBooking : calendarBookings) {
			calendarBookingLocalService.deleteCalendarBooking(calendarBooking);
		}
	}

	@Override
	public String exportCalendarBooking(long calendarBookingId, String type)
		throws Exception {

		CalendarDataFormat calendarDataFormat = CalendarDataFormat.parse(type);

		CalendarDataHandler calendarDataHandler =
			CalendarDataHandlerFactory.getCalendarDataHandler(
				calendarDataFormat);

		return calendarDataHandler.exportCalendarBooking(calendarBookingId);
	}

	@Override
	public CalendarBooking fetchCalendarBooking(
		long calendarId, String vEventUid) {

		return calendarBookingPersistence.fetchByC_V(calendarId, vEventUid);
	}

	@Override
	public CalendarBooking fetchCalendarBooking(String uuid, long groupId) {
		return calendarBookingPersistence.fetchByUUID_G(uuid, groupId);
	}

	@Override
	public CalendarBooking getCalendarBooking(long calendarBookingId)
		throws PortalException {

		return calendarBookingPersistence.findByPrimaryKey(calendarBookingId);
	}

	@Override
	public CalendarBooking getCalendarBooking(
			long calendarId, long parentCalendarBookingId)
		throws PortalException {

		return calendarBookingPersistence.findByC_P(
			calendarId, parentCalendarBookingId);
	}

	@Override
	public CalendarBooking getCalendarBookingInstance(
			long calendarBookingId, int instanceIndex)
		throws PortalException {

		CalendarBooking calendarBooking = getCalendarBooking(calendarBookingId);

		return RecurrenceUtil.getCalendarBookingInstance(
			calendarBooking, instanceIndex);
	}

	@Override
	public List<CalendarBooking> getCalendarBookings(long calendarId) {
		return calendarBookingPersistence.findByCalendarId(calendarId);
	}

	@Override
	public List<CalendarBooking> getCalendarBookings(
		long calendarId, int[] statuses) {

		return calendarBookingPersistence.findByC_S(calendarId, statuses);
	}

	@Override
	public List<CalendarBooking> getCalendarBookings(
		long calendarId, long startTime, long endTime) {

		return getCalendarBookings(
			calendarId, startTime, endTime, QueryUtil.ALL_POS);
	}

	@Override
	public List<CalendarBooking> getCalendarBookings(
		long calendarId, long startTime, long endTime, int max) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CalendarBooking.class, getClassLoader());

		Property property = PropertyFactoryUtil.forName("calendarId");

		dynamicQuery.add(property.eq(calendarId));

		if (startTime >= 0) {
			Property propertyStartTime = PropertyFactoryUtil.forName(
				"startTime");

			dynamicQuery.add(propertyStartTime.gt(startTime));
		}

		if (endTime >= 0) {
			Property propertyEndTime = PropertyFactoryUtil.forName("endTime");

			dynamicQuery.add(propertyEndTime.lt(endTime));
		}

		if (max > 0) {
			dynamicQuery.setLimit(0, max);
		}

		return dynamicQuery(dynamicQuery);
	}

	@Override
	public int getCalendarBookingsCount(
		long calendarId, long parentCalendarBookingId) {

		return calendarBookingPersistence.countByC_P(
			calendarId, parentCalendarBookingId);
	}

	@Override
	public List<CalendarBooking> getChildCalendarBookings(
		long calendarBookingId) {

		return calendarBookingPersistence.findByParentCalendarBookingId(
			calendarBookingId);
	}

	@Override
	public List<CalendarBooking> getChildCalendarBookings(
		long parentCalendarBookingId, int status) {

		return calendarBookingPersistence.findByP_S(
			parentCalendarBookingId, status);
	}

	@Override
	public long[] getChildCalendarIds(long calendarBookingId, long calendarId)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		List<CalendarBooking> childCalendarBookings =
			calendarBookingPersistence.findByParentCalendarBookingId(
				calendarBookingId);

		long[] childCalendarIds = new long[childCalendarBookings.size()];

		for (int i = 0; i < childCalendarIds.length; i++) {
			CalendarBooking childCalendarBooking = childCalendarBookings.get(i);

			if (childCalendarBooking.getCalendarId() ==
					calendarBooking.getCalendarId()) {

				childCalendarIds[i] = calendarId;
			}
			else {
				childCalendarIds[i] = childCalendarBooking.getCalendarId();
			}
		}

		return childCalendarIds;
	}

	@Override
	public CalendarBooking moveCalendarBookingToTrash(
			long userId, CalendarBooking calendarBooking)
		throws PortalException {

		if (calendarBooking.isInTrash()) {
			throw new TrashEntryException();
		}

		// Calendar booking

		if (!calendarBooking.isMasterBooking()) {
			return calendarBooking;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUserId(userId);

		calendarBookingLocalService.updateStatus(
			userId, calendarBooking,
			CalendarBookingWorkflowConstants.STATUS_IN_TRASH, serviceContext);

		// Social

		socialActivityCounterLocalService.disableActivityCounters(
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

		socialActivityLocalService.addActivity(
			userId, calendarBooking.getGroupId(),
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId(),
			SocialActivityConstants.TYPE_MOVE_TO_TRASH,
			getExtraDataJSON(calendarBooking), 0);

		// Workflow

		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			calendarBooking.getCompanyId(), calendarBooking.getGroupId(),
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

		return calendarBooking;
	}

	@Override
	public CalendarBooking moveCalendarBookingToTrash(
			long userId, long calendarBookingId)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		return moveCalendarBookingToTrash(userId, calendarBooking);
	}

	@Override
	public CalendarBooking restoreCalendarBookingFromTrash(
			long userId, long calendarBookingId)
		throws PortalException {

		// Calendar booking

		CalendarBooking calendarBooking = getCalendarBooking(calendarBookingId);

		if (!calendarBooking.isInTrash()) {
			throw new RestoreEntryException(
				RestoreEntryException.INVALID_STATUS);
		}

		if (!calendarBooking.isMasterBooking()) {
			return calendarBooking;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUserId(userId);

		TrashEntry trashEntry = trashEntryLocalService.getEntry(
			CalendarBooking.class.getName(), calendarBookingId);

		calendarBookingLocalService.updateStatus(
			userId, calendarBookingId, trashEntry.getStatus(), serviceContext);

		// Social

		socialActivityCounterLocalService.enableActivityCounters(
			CalendarBooking.class.getName(), calendarBookingId);

		socialActivityLocalService.addActivity(
			userId, calendarBooking.getGroupId(),
			CalendarBooking.class.getName(), calendarBookingId,
			SocialActivityConstants.TYPE_RESTORE_FROM_TRASH,
			getExtraDataJSON(calendarBooking), 0);

		// Workflow

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			calendarBooking.getCompanyId(), calendarBooking.getGroupId(),
			userId, CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId(), calendarBooking,
			serviceContext);

		return calendarBooking;
	}

	@Override
	public List<CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		String keywords, long startTime, long endTime, boolean recurring,
		int[] statuses, int start, int end,
		OrderByComparator<CalendarBooking> orderByComparator) {

		List<CalendarBooking> calendarBookings =
			calendarBookingFinder.findByKeywords(
				companyId, groupIds, calendarIds, calendarResourceIds,
				parentCalendarBookingId, keywords, startTime, endTime,
				recurring, statuses, start, end, orderByComparator);

		if (recurring) {
			calendarBookings = RecurrenceUtil.expandCalendarBookings(
				calendarBookings, startTime, endTime);
		}

		return calendarBookings;
	}

	@Override
	public List<CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId, String title,
		String description, String location, long startTime, long endTime,
		boolean recurring, int[] statuses, boolean andOperator, int start,
		int end, OrderByComparator<CalendarBooking> orderByComparator) {

		List<CalendarBooking> calendarBookings =
			calendarBookingFinder.findByC_G_C_C_P_T_D_L_S_E_S(
				companyId, groupIds, calendarIds, calendarResourceIds,
				parentCalendarBookingId, title, description, location,
				startTime, endTime, recurring, statuses, andOperator, start,
				end, orderByComparator);

		if (recurring) {
			calendarBookings = RecurrenceUtil.expandCalendarBookings(
				calendarBookings, startTime, endTime);
		}

		return calendarBookings;
	}

	@Override
	public int searchCount(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		String keywords, long startTime, long endTime, int[] statuses) {

		return calendarBookingFinder.countByKeywords(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, keywords, startTime, endTime, statuses);
	}

	@Override
	public int searchCount(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId, String title,
		String description, String location, long startTime, long endTime,
		int[] statuses, boolean andOperator) {

		return calendarBookingFinder.countByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, title, description, location, startTime,
			endTime, statuses, andOperator);
	}

	@Override
	public void updateAsset(
			long userId, CalendarBooking calendarBooking,
			long[] assetCategoryIds, String[] assetTagNames,
			long[] assetLinkEntryIds, Double priority)
		throws PortalException {

		boolean visible = false;

		Date publishDate = null;

		if (calendarBooking.isApproved()) {
			visible = true;

			publishDate = calendarBooking.getCreateDate();
		}

		String summary = HtmlUtil.extractText(
			StringUtil.shorten(calendarBooking.getDescription(), 500));

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, calendarBooking.getGroupId(),
			calendarBooking.getCreateDate(), calendarBooking.getModifiedDate(),
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId(), calendarBooking.getUuid(),
			0, assetCategoryIds, assetTagNames, true, visible, null, null,
			publishDate, null, ContentTypes.TEXT_HTML,
			calendarBooking.getTitle(), calendarBooking.getDescription(),
			summary, null, null, 0, 0, priority);

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);
	}

	@Override
	public CalendarBooking updateCalendarBooking(
			long userId, long calendarBookingId, long calendarId,
			long[] childCalendarIds, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, long startTime,
			long endTime, boolean allDay, String recurrence, long firstReminder,
			String firstReminderType, long secondReminder,
			String secondReminderType, ServiceContext serviceContext)
		throws PortalException {

		// Calendar booking

		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);
		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			String sanitizedDescription = SanitizerUtil.sanitize(
				calendar.getCompanyId(), calendar.getGroupId(), userId,
				CalendarBooking.class.getName(), calendarBookingId,
				ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL, entry.getValue(),
				null);

			descriptionMap.put(entry.getKey(), sanitizedDescription);
		}

		java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			startTime);
		java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(
			endTime);

		if (allDay) {
			startTimeJCalendar = JCalendarUtil.toMidnightJCalendar(
				startTimeJCalendar);
			endTimeJCalendar = JCalendarUtil.toLastHourJCalendar(
				endTimeJCalendar);
		}

		if (firstReminder < secondReminder) {
			long originalSecondReminder = secondReminder;

			secondReminder = firstReminder;
			firstReminder = originalSecondReminder;
		}

		validate(startTimeJCalendar, endTimeJCalendar, recurrence);

		calendarBooking.setGroupId(calendar.getGroupId());
		calendarBooking.setModifiedDate(serviceContext.getModifiedDate(null));
		calendarBooking.setCalendarId(calendarId);

		Map<Locale, String> updatedTitleMap = calendarBooking.getTitleMap();

		updatedTitleMap.putAll(titleMap);

		calendarBooking.setTitleMap(
			updatedTitleMap, serviceContext.getLocale());

		Map<Locale, String> updatedDescriptionMap =
			calendarBooking.getDescriptionMap();

		updatedDescriptionMap.putAll(descriptionMap);

		calendarBooking.setDescriptionMap(
			updatedDescriptionMap, serviceContext.getLocale());

		calendarBooking.setLocation(location);
		calendarBooking.setStartTime(startTimeJCalendar.getTimeInMillis());
		calendarBooking.setEndTime(endTimeJCalendar.getTimeInMillis());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setFirstReminder(firstReminder);
		calendarBooking.setFirstReminderType(firstReminderType);
		calendarBooking.setSecondReminder(secondReminder);
		calendarBooking.setSecondReminderType(secondReminderType);

		if (calendarBooking.isMasterBooking() && !calendarBooking.isDraft() &&
			!calendarBooking.isPending()) {

			calendarBooking.setStatus(WorkflowConstants.STATUS_DRAFT);
		}

		calendarBooking.setExpandoBridgeAttributes(serviceContext);

		calendarBookingPersistence.update(calendarBooking);

		addChildCalendarBookings(
			calendarBooking, childCalendarIds, serviceContext);

		// Asset

		updateAsset(
			userId, calendarBooking, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds(),
			serviceContext.getAssetPriority());

		// Social

		socialActivityLocalService.addActivity(
			userId, calendarBooking.getGroupId(),
			CalendarBooking.class.getName(), calendarBookingId,
			CalendarActivityKeys.UPDATE_CALENDAR_BOOKING,
			getExtraDataJSON(calendarBooking), 0);

		// Notifications

		sendNotification(
			calendarBooking, NotificationTemplateType.UPDATE, serviceContext);

		// Workflow

		if (calendarBooking.isMasterBooking()) {
			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				calendarBooking.getCompanyId(), calendarBooking.getGroupId(),
				userId, CalendarBooking.class.getName(),
				calendarBooking.getCalendarBookingId(), calendarBooking,
				serviceContext);
		}

		return calendarBooking;
	}

	@Override
	public CalendarBooking updateCalendarBooking(
			long userId, long calendarBookingId, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, long startTime, long endTime, boolean allDay,
			String recurrence, long firstReminder, String firstReminderType,
			long secondReminder, String secondReminderType,
			ServiceContext serviceContext)
		throws PortalException {

		long[] childCalendarIds = getChildCalendarIds(
			calendarBookingId, calendarId);

		return updateCalendarBooking(
			userId, calendarBookingId, calendarId, childCalendarIds, titleMap,
			descriptionMap, location, startTime, endTime, allDay, recurrence,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	@Override
	public CalendarBooking updateCalendarBookingInstance(
			long userId, long calendarBookingId, int instanceIndex,
			long calendarId, long[] childCalendarIds,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, long startTime, long endTime, boolean allDay,
			String recurrence, boolean allFollowing, long firstReminder,
			String firstReminderType, long secondReminder,
			String secondReminderType, ServiceContext serviceContext)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		if ((instanceIndex == 0) && allFollowing) {
			return updateCalendarBooking(
				userId, calendarBookingId, calendarId, childCalendarIds,
				titleMap, descriptionMap, location, startTime, endTime, allDay,
				recurrence, firstReminder, firstReminderType, secondReminder,
				secondReminderType, serviceContext);
		}

		String oldRecurrence = calendarBooking.getRecurrence();

		deleteCalendarBookingInstance(
			calendarBooking, instanceIndex, allFollowing);

		if (allFollowing) {
			Calendar calendar = calendarLocalService.getCalendar(calendarId);

			Recurrence recurrenceObj = RecurrenceSerializer.deserialize(
				recurrence, calendar.getTimeZone());

			if (oldRecurrence.equals(recurrence) &&
				(recurrenceObj.getCount() > 0)) {

				recurrenceObj.setCount(
					recurrenceObj.getCount() - instanceIndex);

				recurrence = RecurrenceSerializer.serialize(recurrenceObj);
			}
		}
		else {
			recurrence = StringPool.BLANK;
		}

		Map<Locale, String> updatedTitleMap = calendarBooking.getTitleMap();

		updatedTitleMap.putAll(titleMap);

		Map<Locale, String> updatedDescriptionMap =
			calendarBooking.getDescriptionMap();

		updatedDescriptionMap.putAll(descriptionMap);

		return addCalendarBooking(
			userId, calendarId, childCalendarIds,
			CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
			updatedTitleMap, updatedDescriptionMap, location, startTime,
			endTime, allDay, recurrence, firstReminder, firstReminderType,
			secondReminder, secondReminderType, serviceContext);
	}

	@Override
	public CalendarBooking updateCalendarBookingInstance(
			long userId, long calendarBookingId, int instanceIndex,
			long calendarId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, long startTime,
			long endTime, boolean allDay, String recurrence,
			boolean allFollowing, long firstReminder, String firstReminderType,
			long secondReminder, String secondReminderType,
			ServiceContext serviceContext)
		throws PortalException {

		long[] childCalendarIds = getChildCalendarIds(
			calendarBookingId, calendarId);

		return updateCalendarBookingInstance(
			userId, calendarBookingId, instanceIndex, calendarId,
			childCalendarIds, titleMap, descriptionMap, location, startTime,
			endTime, allDay, recurrence, allFollowing, firstReminder,
			firstReminderType, secondReminder, secondReminderType,
			serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CalendarBooking updateStatus(
			long userId, CalendarBooking calendarBooking, int status,
			ServiceContext serviceContext)
		throws PortalException {

		// Calendar booking

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		int oldStatus = calendarBooking.getStatus();

		calendarBooking.setModifiedDate(serviceContext.getModifiedDate(now));
		calendarBooking.setStatus(status);
		calendarBooking.setStatusByUserId(user.getUserId());
		calendarBooking.setStatusByUserName(user.getFullName());
		calendarBooking.setStatusDate(serviceContext.getModifiedDate(now));

		calendarBookingPersistence.update(calendarBooking);

		// Child calendar bookings

		if (status == CalendarBookingWorkflowConstants.STATUS_IN_TRASH) {
			List<CalendarBooking> childCalendarBookings =
				calendarBooking.getChildCalendarBookings();

			for (CalendarBooking childCalendarBooking : childCalendarBookings) {
				if (childCalendarBooking.equals(calendarBooking)) {
					continue;
				}

				updateStatus(
					userId, childCalendarBooking,
					CalendarBookingWorkflowConstants.STATUS_IN_TRASH,
					serviceContext);
			}
		}
		else if (oldStatus ==
					CalendarBookingWorkflowConstants.STATUS_IN_TRASH) {

			List<CalendarBooking> childCalendarBookings =
				calendarBooking.getChildCalendarBookings();

			for (CalendarBooking childCalendarBooking : childCalendarBookings) {
				if (childCalendarBooking.equals(calendarBooking)) {
					continue;
				}

				updateStatus(
					userId, childCalendarBooking,
					CalendarBookingWorkflowConstants.STATUS_PENDING,
					serviceContext);
			}
		}
		else if (status == CalendarBookingWorkflowConstants.STATUS_APPROVED) {
			List<CalendarBooking> childCalendarBookings =
				calendarBooking.getChildCalendarBookings();

			for (CalendarBooking childCalendarBooking : childCalendarBookings) {
				if (childCalendarBooking.equals(calendarBooking)) {
					continue;
				}

				if (childCalendarBooking.getStatus() ==
						CalendarBookingWorkflowConstants.
							STATUS_MASTER_PENDING) {

					updateStatus(
						userId, childCalendarBooking,
						CalendarBookingWorkflowConstants.STATUS_PENDING,
						serviceContext);
				}
			}
		}
		else {
			List<CalendarBooking> childCalendarBookings =
				calendarBooking.getChildCalendarBookings();

			for (CalendarBooking childCalendarBooking : childCalendarBookings) {
				if (childCalendarBooking.equals(calendarBooking)) {
					continue;
				}

				updateStatus(
					userId, childCalendarBooking,
					CalendarBookingWorkflowConstants.STATUS_MASTER_PENDING,
					serviceContext);
			}
		}

		// Asset

		if (status == CalendarBookingWorkflowConstants.STATUS_APPROVED) {
			assetEntryLocalService.updateVisible(
				CalendarBooking.class.getName(),
				calendarBooking.getCalendarBookingId(), true);
		}
		else if (status == CalendarBookingWorkflowConstants.STATUS_IN_TRASH) {
			assetEntryLocalService.updateVisible(
				CalendarBooking.class.getName(),
				calendarBooking.getCalendarBookingId(), false);
		}

		// Trash

		if (oldStatus == WorkflowConstants.STATUS_IN_TRASH) {
			trashEntryLocalService.deleteEntry(
				CalendarBooking.class.getName(),
				calendarBooking.getCalendarBookingId());
		}

		if (status == CalendarBookingWorkflowConstants.STATUS_IN_TRASH) {
			if (calendarBooking.isMasterBooking()) {
				trashEntryLocalService.addTrashEntry(
					userId, calendarBooking.getGroupId(),
					CalendarBooking.class.getName(),
					calendarBooking.getCalendarBookingId(),
					calendarBooking.getUuid(), null, oldStatus, null, null);
			}
			else {
				trashEntryLocalService.addTrashEntry(
					userId, calendarBooking.getGroupId(),
					CalendarBooking.class.getName(),
					calendarBooking.getCalendarBookingId(),
					calendarBooking.getUuid(), null,
					CalendarBookingWorkflowConstants.STATUS_PENDING, null,
					null);
			}

			sendNotification(
				calendarBooking, NotificationTemplateType.MOVED_TO_TRASH,
				serviceContext);
		}

		return calendarBooking;
	}

	@Override
	public CalendarBooking updateStatus(
			long userId, long calendarBookingId, int status,
			ServiceContext serviceContext)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		return calendarBookingLocalService.updateStatus(
			userId, calendarBooking, status, serviceContext);
	}

	protected void addChildCalendarBookings(
			CalendarBooking calendarBooking, long[] childCalendarIds,
			ServiceContext serviceContext)
		throws PortalException {

		if (!calendarBooking.isMasterBooking()) {
			return;
		}

		Map<Long, CalendarBooking> childCalendarBookingMap = new HashMap<>();

		List<CalendarBooking> childCalendarBookings =
			calendarBookingPersistence.findByParentCalendarBookingId(
				calendarBooking.getCalendarBookingId());

		for (CalendarBooking childCalendarBooking : childCalendarBookings) {
			if (childCalendarBooking.isMasterBooking() ||
				(childCalendarBooking.isDenied() &&
				 ArrayUtil.contains(
					 childCalendarIds, childCalendarBooking.getCalendarId()))) {

				continue;
			}

			deleteCalendarBooking(childCalendarBooking.getCalendarBookingId());

			childCalendarBookingMap.put(
				childCalendarBooking.getCalendarId(), childCalendarBooking);
		}

		for (long calendarId : childCalendarIds) {
			int count = calendarBookingPersistence.countByC_P(
				calendarId, calendarBooking.getCalendarBookingId());

			if (count > 0) {
				continue;
			}

			long firstReminder = calendarBooking.getFirstReminder();
			String firstReminderType = calendarBooking.getFirstReminderType();
			long secondReminder = calendarBooking.getSecondReminder();
			String secondReminderType = calendarBooking.getSecondReminderType();

			if (childCalendarBookingMap.containsKey(calendarId)) {
				CalendarBooking oldChildCalendarBooking =
					childCalendarBookingMap.get(calendarId);

				firstReminder = oldChildCalendarBooking.getFirstReminder();
				firstReminderType =
					oldChildCalendarBooking.getFirstReminderType();
				secondReminder = oldChildCalendarBooking.getSecondReminder();
				secondReminderType =
					oldChildCalendarBooking.getSecondReminderType();
			}

			serviceContext.setAttribute("sendNotification", false);

			CalendarBooking childCalendarBooking = addCalendarBooking(
				calendarBooking.getUserId(), calendarId, new long[0],
				calendarBooking.getCalendarBookingId(),
				calendarBooking.getTitleMap(),
				calendarBooking.getDescriptionMap(),
				calendarBooking.getLocation(), calendarBooking.getStartTime(),
				calendarBooking.getEndTime(), calendarBooking.getAllDay(),
				calendarBooking.getRecurrence(), firstReminder,
				firstReminderType, secondReminder, secondReminderType,
				serviceContext);

			serviceContext.setAttribute("sendNotification", true);

			int workflowAction = GetterUtil.getInteger(
				serviceContext.getAttribute("workflowAction"));

			if (childCalendarBookingMap.containsKey(calendarId)) {
				CalendarBooking oldChildCalendarBooking =
					childCalendarBookingMap.get(calendarId);

				if ((calendarBooking.getStartTime() ==
						oldChildCalendarBooking.getStartTime()) &&
					(calendarBooking.getEndTime() ==
						oldChildCalendarBooking.getEndTime()) &&
					(workflowAction != WorkflowConstants.ACTION_SAVE_DRAFT)) {

					updateStatus(
						childCalendarBooking.getUserId(), childCalendarBooking,
						oldChildCalendarBooking.getStatus(), serviceContext);
				}
			}

			NotificationTemplateType notificationTemplateType =
				NotificationTemplateType.INVITE;

			if (childCalendarBookingMap.containsKey(
					childCalendarBooking.getCalendarId())) {

				notificationTemplateType = NotificationTemplateType.UPDATE;
			}

			sendNotification(
				childCalendarBooking, notificationTemplateType, serviceContext);
		}
	}

	protected String getExtraDataJSON(CalendarBooking calendarBooking) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("title", calendarBooking.getTitle());

		return jsonObject.toString();
	}

	protected void sendNotification(
		CalendarBooking calendarBooking,
		NotificationTemplateType notificationTemplateType,
		ServiceContext serviceContext) {

		boolean sendNotification = ParamUtil.getBoolean(
			serviceContext, "sendNotification", true);

		if (!sendNotification) {
			return;
		}

		try {
			User sender = userLocalService.fetchUser(
				serviceContext.getUserId());

			NotificationType notificationType =
				CalendarServiceConfigurationValues.
					CALENDAR_NOTIFICATION_DEFAULT_TYPE;

			NotificationUtil.notifyCalendarBookingRecipients(
				calendarBooking, notificationType, notificationTemplateType,
				sender);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	protected void updateChildCalendarBookings(
		CalendarBooking calendarBooking, Date modifiedDate, String recurrence) {

		List<CalendarBooking> childCalendarBookings = new ArrayList<>();

		if (calendarBooking.isMasterBooking()) {
			childCalendarBookings = getChildCalendarBookings(
				calendarBooking.getCalendarBookingId());
		}
		else {
			childCalendarBookings.add(calendarBooking);
		}

		for (CalendarBooking childCalendarBooking : childCalendarBookings) {
			childCalendarBooking.setModifiedDate(modifiedDate);
			childCalendarBooking.setRecurrence(recurrence);

			calendarBookingPersistence.update(childCalendarBooking);
		}
	}

	protected void validate(
			java.util.Calendar startTimeJCalendar,
			java.util.Calendar endTimeJCalendar, String recurrence)
		throws PortalException {

		if (startTimeJCalendar.after(endTimeJCalendar)) {
			throw new CalendarBookingDurationException();
		}

		if (Validator.isNull(recurrence)) {
			return;
		}

		Recurrence recurrenceObj = RecurrenceSerializer.deserialize(
			recurrence, startTimeJCalendar.getTimeZone());

		if ((recurrenceObj.getUntilJCalendar() != null) &&
			startTimeJCalendar.after(recurrenceObj.getUntilJCalendar())) {

			throw new CalendarBookingRecurrenceException();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CalendarBookingLocalServiceImpl.class);

}