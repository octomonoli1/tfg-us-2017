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

import com.liferay.calendar.constants.CalendarActionKeys;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.base.CalendarResourceServiceBaseImpl;
import com.liferay.calendar.service.permission.CalendarPortletPermission;
import com.liferay.calendar.service.permission.CalendarResourcePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Andrea Di Giorgi
 */
public class CalendarResourceServiceImpl
	extends CalendarResourceServiceBaseImpl {

	@Override
	public CalendarResource addCalendarResource(
			long groupId, long classNameId, long classPK, String classUuid,
			String code, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		CalendarPortletPermission.check(
			getPermissionChecker(), groupId, CalendarActionKeys.ADD_RESOURCE);

		return calendarResourceLocalService.addCalendarResource(
			getUserId(), groupId, classNameId, classPK, classUuid, code,
			nameMap, descriptionMap, active, serviceContext);
	}

	@Override
	public CalendarResource deleteCalendarResource(long calendarResourceId)
		throws PortalException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId, ActionKeys.DELETE);

		return calendarResourceLocalService.deleteCalendarResource(
			calendarResourceId);
	}

	@Override
	public CalendarResource fetchCalendarResource(
			long classNameId, long classPK)
		throws PortalException {

		CalendarResource calendarResource =
			calendarResourcePersistence.fetchByC_C(classNameId, classPK);

		if (calendarResource == null) {
			return null;
		}

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResource, ActionKeys.VIEW);

		return calendarResource;
	}

	@Override
	public CalendarResource getCalendarResource(long calendarResourceId)
		throws PortalException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId, ActionKeys.VIEW);

		return calendarResourcePersistence.findByPrimaryKey(calendarResourceId);
	}

	@Override
	public List<CalendarResource> search(
		long companyId, long[] groupIds, long[] classNameIds, String keywords,
		boolean active, boolean andOperator, int start, int end,
		OrderByComparator<CalendarResource> orderByComparator) {

		return calendarResourceFinder.filterFindByKeywords(
			companyId, groupIds, classNameIds, keywords, active, start, end,
			orderByComparator);
	}

	@Override
	public List<CalendarResource> search(
		long companyId, long[] groupIds, long[] classNameIds, String code,
		String name, String description, boolean active, boolean andOperator,
		int start, int end,
		OrderByComparator<CalendarResource> orderByComparator) {

		return calendarResourceFinder.filterFindByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, code, name, description, active,
			andOperator, start, end, orderByComparator);
	}

	@Override
	public int searchCount(
		long companyId, long[] groupIds, long[] classNameIds, String keywords,
		boolean active) {

		return calendarResourceFinder.filterCountByKeywords(
			companyId, groupIds, classNameIds, keywords, active);
	}

	@Override
	public int searchCount(
		long companyId, long[] groupIds, long[] classNameIds, String code,
		String name, String description, boolean active, boolean andOperator) {

		return calendarResourceFinder.filterCountByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, code, name, description, active,
			andOperator);
	}

	@Override
	public CalendarResource updateCalendarResource(
			long calendarResourceId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId, ActionKeys.UPDATE);

		return calendarResourceLocalService.updateCalendarResource(
			calendarResourceId, nameMap, descriptionMap, active,
			serviceContext);
	}

}