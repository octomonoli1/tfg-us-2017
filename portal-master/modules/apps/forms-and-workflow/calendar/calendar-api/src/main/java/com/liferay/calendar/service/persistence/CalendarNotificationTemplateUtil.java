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

package com.liferay.calendar.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.calendar.model.CalendarNotificationTemplate;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the calendar notification template service. This utility wraps {@link com.liferay.calendar.service.persistence.impl.CalendarNotificationTemplatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarNotificationTemplatePersistence
 * @see com.liferay.calendar.service.persistence.impl.CalendarNotificationTemplatePersistenceImpl
 * @generated
 */
@ProviderType
public class CalendarNotificationTemplateUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		getPersistence().clearCache(calendarNotificationTemplate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CalendarNotificationTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CalendarNotificationTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CalendarNotificationTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CalendarNotificationTemplate update(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		return getPersistence().update(calendarNotificationTemplate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CalendarNotificationTemplate update(
		CalendarNotificationTemplate calendarNotificationTemplate,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(calendarNotificationTemplate, serviceContext);
	}

	/**
	* Returns all the calendar notification templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findByUuid(
		java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the calendar notification templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @return the range of matching calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findByUuid(
		java.lang.String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the calendar notification templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findByUuid(
		java.lang.String uuid, int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the calendar notification templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findByUuid(
		java.lang.String uuid, int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first calendar notification template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate findByUuid_First(
		java.lang.String uuid,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first calendar notification template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate fetchByUuid_First(
		java.lang.String uuid,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last calendar notification template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate findByUuid_Last(
		java.lang.String uuid,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last calendar notification template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate fetchByUuid_Last(
		java.lang.String uuid,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the calendar notification templates before and after the current calendar notification template in the ordered set where uuid = &#63;.
	*
	* @param calendarNotificationTemplateId the primary key of the current calendar notification template
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar notification template
	* @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	*/
	public static CalendarNotificationTemplate[] findByUuid_PrevAndNext(
		long calendarNotificationTemplateId, java.lang.String uuid,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence()
				   .findByUuid_PrevAndNext(calendarNotificationTemplateId,
			uuid, orderByComparator);
	}

	/**
	* Removes all the calendar notification templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of calendar notification templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching calendar notification templates
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the calendar notification template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchNotificationTemplateException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the calendar notification template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate fetchByUUID_G(
		java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the calendar notification template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the calendar notification template where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the calendar notification template that was removed
	*/
	public static CalendarNotificationTemplate removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of calendar notification templates where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching calendar notification templates
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the calendar notification templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findByUuid_C(
		java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the calendar notification templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @return the range of matching calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the calendar notification templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the calendar notification templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first calendar notification template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate findByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first calendar notification template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last calendar notification template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last calendar notification template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the calendar notification templates before and after the current calendar notification template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param calendarNotificationTemplateId the primary key of the current calendar notification template
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar notification template
	* @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	*/
	public static CalendarNotificationTemplate[] findByUuid_C_PrevAndNext(
		long calendarNotificationTemplateId, java.lang.String uuid,
		long companyId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(calendarNotificationTemplateId,
			uuid, companyId, orderByComparator);
	}

	/**
	* Removes all the calendar notification templates where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of calendar notification templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching calendar notification templates
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the calendar notification templates where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @return the matching calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findByCalendarId(
		long calendarId) {
		return getPersistence().findByCalendarId(calendarId);
	}

	/**
	* Returns a range of all the calendar notification templates where calendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @return the range of matching calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findByCalendarId(
		long calendarId, int start, int end) {
		return getPersistence().findByCalendarId(calendarId, start, end);
	}

	/**
	* Returns an ordered range of all the calendar notification templates where calendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findByCalendarId(
		long calendarId, int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return getPersistence()
				   .findByCalendarId(calendarId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the calendar notification templates where calendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findByCalendarId(
		long calendarId, int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCalendarId(calendarId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first calendar notification template in the ordered set where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate findByCalendarId_First(
		long calendarId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence()
				   .findByCalendarId_First(calendarId, orderByComparator);
	}

	/**
	* Returns the first calendar notification template in the ordered set where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate fetchByCalendarId_First(
		long calendarId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByCalendarId_First(calendarId, orderByComparator);
	}

	/**
	* Returns the last calendar notification template in the ordered set where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate findByCalendarId_Last(
		long calendarId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence()
				   .findByCalendarId_Last(calendarId, orderByComparator);
	}

	/**
	* Returns the last calendar notification template in the ordered set where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate fetchByCalendarId_Last(
		long calendarId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByCalendarId_Last(calendarId, orderByComparator);
	}

	/**
	* Returns the calendar notification templates before and after the current calendar notification template in the ordered set where calendarId = &#63;.
	*
	* @param calendarNotificationTemplateId the primary key of the current calendar notification template
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar notification template
	* @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	*/
	public static CalendarNotificationTemplate[] findByCalendarId_PrevAndNext(
		long calendarNotificationTemplateId, long calendarId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence()
				   .findByCalendarId_PrevAndNext(calendarNotificationTemplateId,
			calendarId, orderByComparator);
	}

	/**
	* Removes all the calendar notification templates where calendarId = &#63; from the database.
	*
	* @param calendarId the calendar ID
	*/
	public static void removeByCalendarId(long calendarId) {
		getPersistence().removeByCalendarId(calendarId);
	}

	/**
	* Returns the number of calendar notification templates where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @return the number of matching calendar notification templates
	*/
	public static int countByCalendarId(long calendarId) {
		return getPersistence().countByCalendarId(calendarId);
	}

	/**
	* Returns the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; or throws a {@link NoSuchNotificationTemplateException} if it could not be found.
	*
	* @param calendarId the calendar ID
	* @param notificationType the notification type
	* @param notificationTemplateType the notification template type
	* @return the matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate findByC_NT_NTT(long calendarId,
		java.lang.String notificationType,
		java.lang.String notificationTemplateType)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence()
				   .findByC_NT_NTT(calendarId, notificationType,
			notificationTemplateType);
	}

	/**
	* Returns the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param calendarId the calendar ID
	* @param notificationType the notification type
	* @param notificationTemplateType the notification template type
	* @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate fetchByC_NT_NTT(
		long calendarId, java.lang.String notificationType,
		java.lang.String notificationTemplateType) {
		return getPersistence()
				   .fetchByC_NT_NTT(calendarId, notificationType,
			notificationTemplateType);
	}

	/**
	* Returns the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param calendarId the calendar ID
	* @param notificationType the notification type
	* @param notificationTemplateType the notification template type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public static CalendarNotificationTemplate fetchByC_NT_NTT(
		long calendarId, java.lang.String notificationType,
		java.lang.String notificationTemplateType, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByC_NT_NTT(calendarId, notificationType,
			notificationTemplateType, retrieveFromCache);
	}

	/**
	* Removes the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; from the database.
	*
	* @param calendarId the calendar ID
	* @param notificationType the notification type
	* @param notificationTemplateType the notification template type
	* @return the calendar notification template that was removed
	*/
	public static CalendarNotificationTemplate removeByC_NT_NTT(
		long calendarId, java.lang.String notificationType,
		java.lang.String notificationTemplateType)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence()
				   .removeByC_NT_NTT(calendarId, notificationType,
			notificationTemplateType);
	}

	/**
	* Returns the number of calendar notification templates where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63;.
	*
	* @param calendarId the calendar ID
	* @param notificationType the notification type
	* @param notificationTemplateType the notification template type
	* @return the number of matching calendar notification templates
	*/
	public static int countByC_NT_NTT(long calendarId,
		java.lang.String notificationType,
		java.lang.String notificationTemplateType) {
		return getPersistence()
				   .countByC_NT_NTT(calendarId, notificationType,
			notificationTemplateType);
	}

	/**
	* Caches the calendar notification template in the entity cache if it is enabled.
	*
	* @param calendarNotificationTemplate the calendar notification template
	*/
	public static void cacheResult(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		getPersistence().cacheResult(calendarNotificationTemplate);
	}

	/**
	* Caches the calendar notification templates in the entity cache if it is enabled.
	*
	* @param calendarNotificationTemplates the calendar notification templates
	*/
	public static void cacheResult(
		List<CalendarNotificationTemplate> calendarNotificationTemplates) {
		getPersistence().cacheResult(calendarNotificationTemplates);
	}

	/**
	* Creates a new calendar notification template with the primary key. Does not add the calendar notification template to the database.
	*
	* @param calendarNotificationTemplateId the primary key for the new calendar notification template
	* @return the new calendar notification template
	*/
	public static CalendarNotificationTemplate create(
		long calendarNotificationTemplateId) {
		return getPersistence().create(calendarNotificationTemplateId);
	}

	/**
	* Removes the calendar notification template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplateId the primary key of the calendar notification template
	* @return the calendar notification template that was removed
	* @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	*/
	public static CalendarNotificationTemplate remove(
		long calendarNotificationTemplateId)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence().remove(calendarNotificationTemplateId);
	}

	public static CalendarNotificationTemplate updateImpl(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		return getPersistence().updateImpl(calendarNotificationTemplate);
	}

	/**
	* Returns the calendar notification template with the primary key or throws a {@link NoSuchNotificationTemplateException} if it could not be found.
	*
	* @param calendarNotificationTemplateId the primary key of the calendar notification template
	* @return the calendar notification template
	* @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	*/
	public static CalendarNotificationTemplate findByPrimaryKey(
		long calendarNotificationTemplateId)
		throws com.liferay.calendar.exception.NoSuchNotificationTemplateException {
		return getPersistence().findByPrimaryKey(calendarNotificationTemplateId);
	}

	/**
	* Returns the calendar notification template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param calendarNotificationTemplateId the primary key of the calendar notification template
	* @return the calendar notification template, or <code>null</code> if a calendar notification template with the primary key could not be found
	*/
	public static CalendarNotificationTemplate fetchByPrimaryKey(
		long calendarNotificationTemplateId) {
		return getPersistence().fetchByPrimaryKey(calendarNotificationTemplateId);
	}

	public static java.util.Map<java.io.Serializable, CalendarNotificationTemplate> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the calendar notification templates.
	*
	* @return the calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the calendar notification templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @return the range of calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the calendar notification templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findAll(int start,
		int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the calendar notification templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of calendar notification templates
	*/
	public static List<CalendarNotificationTemplate> findAll(int start,
		int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the calendar notification templates from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of calendar notification templates.
	*
	* @return the number of calendar notification templates
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CalendarNotificationTemplatePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CalendarNotificationTemplatePersistence, CalendarNotificationTemplatePersistence> _serviceTracker =
		ServiceTrackerFactory.open(CalendarNotificationTemplatePersistence.class);
}