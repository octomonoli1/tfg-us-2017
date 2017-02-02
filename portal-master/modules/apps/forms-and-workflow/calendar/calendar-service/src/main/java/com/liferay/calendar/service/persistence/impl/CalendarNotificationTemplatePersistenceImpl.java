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

package com.liferay.calendar.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.calendar.exception.NoSuchNotificationTemplateException;
import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.model.impl.CalendarNotificationTemplateImpl;
import com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl;
import com.liferay.calendar.service.persistence.CalendarNotificationTemplatePersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the calendar notification template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarNotificationTemplatePersistence
 * @see com.liferay.calendar.service.persistence.CalendarNotificationTemplateUtil
 * @generated
 */
@ProviderType
public class CalendarNotificationTemplatePersistenceImpl
	extends BasePersistenceImpl<CalendarNotificationTemplate>
	implements CalendarNotificationTemplatePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CalendarNotificationTemplateUtil} to access the calendar notification template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CalendarNotificationTemplateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CalendarNotificationTemplateModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid", new String[] { String.class.getName() });

	/**
	 * Returns all the calendar notification templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching calendar notification templates
	 */
	@Override
	public List<CalendarNotificationTemplate> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<CalendarNotificationTemplate> findByUuid(String uuid,
		int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<CalendarNotificationTemplate> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<CalendarNotificationTemplate> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<CalendarNotificationTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<CalendarNotificationTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CalendarNotificationTemplate calendarNotificationTemplate : list) {
					if (!Objects.equals(uuid,
								calendarNotificationTemplate.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarNotificationTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<CalendarNotificationTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarNotificationTemplate>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first calendar notification template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar notification template
	 * @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate findByUuid_First(String uuid,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = fetchByUuid_First(uuid,
				orderByComparator);

		if (calendarNotificationTemplate != null) {
			return calendarNotificationTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationTemplateException(msg.toString());
	}

	/**
	 * Returns the first calendar notification template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate fetchByUuid_First(String uuid,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		List<CalendarNotificationTemplate> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar notification template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar notification template
	 * @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate findByUuid_Last(String uuid,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = fetchByUuid_Last(uuid,
				orderByComparator);

		if (calendarNotificationTemplate != null) {
			return calendarNotificationTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationTemplateException(msg.toString());
	}

	/**
	 * Returns the last calendar notification template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate fetchByUuid_Last(String uuid,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CalendarNotificationTemplate> list = findByUuid(uuid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CalendarNotificationTemplate[] findByUuid_PrevAndNext(
		long calendarNotificationTemplateId, String uuid,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = findByPrimaryKey(calendarNotificationTemplateId);

		Session session = null;

		try {
			session = openSession();

			CalendarNotificationTemplate[] array = new CalendarNotificationTemplateImpl[3];

			array[0] = getByUuid_PrevAndNext(session,
					calendarNotificationTemplate, uuid, orderByComparator, true);

			array[1] = calendarNotificationTemplate;

			array[2] = getByUuid_PrevAndNext(session,
					calendarNotificationTemplate, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarNotificationTemplate getByUuid_PrevAndNext(
		Session session,
		CalendarNotificationTemplate calendarNotificationTemplate, String uuid,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CalendarNotificationTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendarNotificationTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarNotificationTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar notification templates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CalendarNotificationTemplate calendarNotificationTemplate : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendarNotificationTemplate);
		}
	}

	/**
	 * Returns the number of calendar notification templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching calendar notification templates
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "calendarNotificationTemplate.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "calendarNotificationTemplate.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(calendarNotificationTemplate.uuid IS NULL OR calendarNotificationTemplate.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			CalendarNotificationTemplateModelImpl.UUID_COLUMN_BITMASK |
			CalendarNotificationTemplateModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the calendar notification template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchNotificationTemplateException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching calendar notification template
	 * @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate findByUUID_G(String uuid, long groupId)
		throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = fetchByUUID_G(uuid,
				groupId);

		if (calendarNotificationTemplate == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchNotificationTemplateException(msg.toString());
		}

		return calendarNotificationTemplate;
	}

	/**
	 * Returns the calendar notification template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the calendar notification template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof CalendarNotificationTemplate) {
			CalendarNotificationTemplate calendarNotificationTemplate = (CalendarNotificationTemplate)result;

			if (!Objects.equals(uuid, calendarNotificationTemplate.getUuid()) ||
					(groupId != calendarNotificationTemplate.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<CalendarNotificationTemplate> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					CalendarNotificationTemplate calendarNotificationTemplate = list.get(0);

					result = calendarNotificationTemplate;

					cacheResult(calendarNotificationTemplate);

					if ((calendarNotificationTemplate.getUuid() == null) ||
							!calendarNotificationTemplate.getUuid().equals(uuid) ||
							(calendarNotificationTemplate.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, calendarNotificationTemplate);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CalendarNotificationTemplate)result;
		}
	}

	/**
	 * Removes the calendar notification template where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the calendar notification template that was removed
	 */
	@Override
	public CalendarNotificationTemplate removeByUUID_G(String uuid, long groupId)
		throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = findByUUID_G(uuid,
				groupId);

		return remove(calendarNotificationTemplate);
	}

	/**
	 * Returns the number of calendar notification templates where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching calendar notification templates
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "calendarNotificationTemplate.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "calendarNotificationTemplate.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(calendarNotificationTemplate.uuid IS NULL OR calendarNotificationTemplate.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "calendarNotificationTemplate.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			CalendarNotificationTemplateModelImpl.UUID_COLUMN_BITMASK |
			CalendarNotificationTemplateModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the calendar notification templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching calendar notification templates
	 */
	@Override
	public List<CalendarNotificationTemplate> findByUuid_C(String uuid,
		long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<CalendarNotificationTemplate> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<CalendarNotificationTemplate> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<CalendarNotificationTemplate> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<CalendarNotificationTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<CalendarNotificationTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CalendarNotificationTemplate calendarNotificationTemplate : list) {
					if (!Objects.equals(uuid,
								calendarNotificationTemplate.getUuid()) ||
							(companyId != calendarNotificationTemplate.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarNotificationTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CalendarNotificationTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarNotificationTemplate>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public CalendarNotificationTemplate findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (calendarNotificationTemplate != null) {
			return calendarNotificationTemplate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationTemplateException(msg.toString());
	}

	/**
	 * Returns the first calendar notification template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		List<CalendarNotificationTemplate> list = findByUuid_C(uuid, companyId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CalendarNotificationTemplate findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (calendarNotificationTemplate != null) {
			return calendarNotificationTemplate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationTemplateException(msg.toString());
	}

	/**
	 * Returns the last calendar notification template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CalendarNotificationTemplate> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CalendarNotificationTemplate[] findByUuid_C_PrevAndNext(
		long calendarNotificationTemplateId, String uuid, long companyId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = findByPrimaryKey(calendarNotificationTemplateId);

		Session session = null;

		try {
			session = openSession();

			CalendarNotificationTemplate[] array = new CalendarNotificationTemplateImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session,
					calendarNotificationTemplate, uuid, companyId,
					orderByComparator, true);

			array[1] = calendarNotificationTemplate;

			array[2] = getByUuid_C_PrevAndNext(session,
					calendarNotificationTemplate, uuid, companyId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarNotificationTemplate getByUuid_C_PrevAndNext(
		Session session,
		CalendarNotificationTemplate calendarNotificationTemplate, String uuid,
		long companyId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CalendarNotificationTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendarNotificationTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarNotificationTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar notification templates where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CalendarNotificationTemplate calendarNotificationTemplate : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendarNotificationTemplate);
		}
	}

	/**
	 * Returns the number of calendar notification templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching calendar notification templates
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "calendarNotificationTemplate.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "calendarNotificationTemplate.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(calendarNotificationTemplate.uuid IS NULL OR calendarNotificationTemplate.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "calendarNotificationTemplate.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CALENDARID =
		new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCalendarId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID =
		new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCalendarId",
			new String[] { Long.class.getName() },
			CalendarNotificationTemplateModelImpl.CALENDARID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CALENDARID = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCalendarId", new String[] { Long.class.getName() });

	/**
	 * Returns all the calendar notification templates where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @return the matching calendar notification templates
	 */
	@Override
	public List<CalendarNotificationTemplate> findByCalendarId(long calendarId) {
		return findByCalendarId(calendarId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<CalendarNotificationTemplate> findByCalendarId(
		long calendarId, int start, int end) {
		return findByCalendarId(calendarId, start, end, null);
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
	@Override
	public List<CalendarNotificationTemplate> findByCalendarId(
		long calendarId, int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return findByCalendarId(calendarId, start, end, orderByComparator, true);
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
	@Override
	public List<CalendarNotificationTemplate> findByCalendarId(
		long calendarId, int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID;
			finderArgs = new Object[] { calendarId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CALENDARID;
			finderArgs = new Object[] { calendarId, start, end, orderByComparator };
		}

		List<CalendarNotificationTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<CalendarNotificationTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CalendarNotificationTemplate calendarNotificationTemplate : list) {
					if ((calendarId != calendarNotificationTemplate.getCalendarId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_CALENDARID_CALENDARID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarNotificationTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				if (!pagination) {
					list = (List<CalendarNotificationTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarNotificationTemplate>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first calendar notification template in the ordered set where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar notification template
	 * @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate findByCalendarId_First(
		long calendarId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = fetchByCalendarId_First(calendarId,
				orderByComparator);

		if (calendarNotificationTemplate != null) {
			return calendarNotificationTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("calendarId=");
		msg.append(calendarId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationTemplateException(msg.toString());
	}

	/**
	 * Returns the first calendar notification template in the ordered set where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate fetchByCalendarId_First(
		long calendarId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		List<CalendarNotificationTemplate> list = findByCalendarId(calendarId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar notification template in the ordered set where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar notification template
	 * @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate findByCalendarId_Last(long calendarId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = fetchByCalendarId_Last(calendarId,
				orderByComparator);

		if (calendarNotificationTemplate != null) {
			return calendarNotificationTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("calendarId=");
		msg.append(calendarId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationTemplateException(msg.toString());
	}

	/**
	 * Returns the last calendar notification template in the ordered set where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate fetchByCalendarId_Last(
		long calendarId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		int count = countByCalendarId(calendarId);

		if (count == 0) {
			return null;
		}

		List<CalendarNotificationTemplate> list = findByCalendarId(calendarId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CalendarNotificationTemplate[] findByCalendarId_PrevAndNext(
		long calendarNotificationTemplateId, long calendarId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = findByPrimaryKey(calendarNotificationTemplateId);

		Session session = null;

		try {
			session = openSession();

			CalendarNotificationTemplate[] array = new CalendarNotificationTemplateImpl[3];

			array[0] = getByCalendarId_PrevAndNext(session,
					calendarNotificationTemplate, calendarId,
					orderByComparator, true);

			array[1] = calendarNotificationTemplate;

			array[2] = getByCalendarId_PrevAndNext(session,
					calendarNotificationTemplate, calendarId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarNotificationTemplate getByCalendarId_PrevAndNext(
		Session session,
		CalendarNotificationTemplate calendarNotificationTemplate,
		long calendarId,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

		query.append(_FINDER_COLUMN_CALENDARID_CALENDARID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CalendarNotificationTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(calendarId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendarNotificationTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarNotificationTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar notification templates where calendarId = &#63; from the database.
	 *
	 * @param calendarId the calendar ID
	 */
	@Override
	public void removeByCalendarId(long calendarId) {
		for (CalendarNotificationTemplate calendarNotificationTemplate : findByCalendarId(
				calendarId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendarNotificationTemplate);
		}
	}

	/**
	 * Returns the number of calendar notification templates where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @return the number of matching calendar notification templates
	 */
	@Override
	public int countByCalendarId(long calendarId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CALENDARID;

		Object[] finderArgs = new Object[] { calendarId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_CALENDARID_CALENDARID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CALENDARID_CALENDARID_2 = "calendarNotificationTemplate.calendarId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_NT_NTT = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_NT_NTT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			CalendarNotificationTemplateModelImpl.CALENDARID_COLUMN_BITMASK |
			CalendarNotificationTemplateModelImpl.NOTIFICATIONTYPE_COLUMN_BITMASK |
			CalendarNotificationTemplateModelImpl.NOTIFICATIONTEMPLATETYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_NT_NTT = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByC_NT_NTT",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; or throws a {@link NoSuchNotificationTemplateException} if it could not be found.
	 *
	 * @param calendarId the calendar ID
	 * @param notificationType the notification type
	 * @param notificationTemplateType the notification template type
	 * @return the matching calendar notification template
	 * @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate findByC_NT_NTT(long calendarId,
		String notificationType, String notificationTemplateType)
		throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = fetchByC_NT_NTT(calendarId,
				notificationType, notificationTemplateType);

		if (calendarNotificationTemplate == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("calendarId=");
			msg.append(calendarId);

			msg.append(", notificationType=");
			msg.append(notificationType);

			msg.append(", notificationTemplateType=");
			msg.append(notificationTemplateType);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchNotificationTemplateException(msg.toString());
		}

		return calendarNotificationTemplate;
	}

	/**
	 * Returns the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param calendarId the calendar ID
	 * @param notificationType the notification type
	 * @param notificationTemplateType the notification template type
	 * @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 */
	@Override
	public CalendarNotificationTemplate fetchByC_NT_NTT(long calendarId,
		String notificationType, String notificationTemplateType) {
		return fetchByC_NT_NTT(calendarId, notificationType,
			notificationTemplateType, true);
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
	@Override
	public CalendarNotificationTemplate fetchByC_NT_NTT(long calendarId,
		String notificationType, String notificationTemplateType,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				calendarId, notificationType, notificationTemplateType
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_NT_NTT,
					finderArgs, this);
		}

		if (result instanceof CalendarNotificationTemplate) {
			CalendarNotificationTemplate calendarNotificationTemplate = (CalendarNotificationTemplate)result;

			if ((calendarId != calendarNotificationTemplate.getCalendarId()) ||
					!Objects.equals(notificationType,
						calendarNotificationTemplate.getNotificationType()) ||
					!Objects.equals(notificationTemplateType,
						calendarNotificationTemplate.getNotificationTemplateType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_C_NT_NTT_CALENDARID_2);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTYPE_2);
			}

			boolean bindNotificationTemplateType = false;

			if (notificationTemplateType == null) {
				query.append(_FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTEMPLATETYPE_1);
			}
			else if (notificationTemplateType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTEMPLATETYPE_3);
			}
			else {
				bindNotificationTemplateType = true;

				query.append(_FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTEMPLATETYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				if (bindNotificationType) {
					qPos.add(notificationType);
				}

				if (bindNotificationTemplateType) {
					qPos.add(notificationTemplateType);
				}

				List<CalendarNotificationTemplate> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_NT_NTT,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"CalendarNotificationTemplatePersistenceImpl.fetchByC_NT_NTT(long, String, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					CalendarNotificationTemplate calendarNotificationTemplate = list.get(0);

					result = calendarNotificationTemplate;

					cacheResult(calendarNotificationTemplate);

					if ((calendarNotificationTemplate.getCalendarId() != calendarId) ||
							(calendarNotificationTemplate.getNotificationType() == null) ||
							!calendarNotificationTemplate.getNotificationType()
															 .equals(notificationType) ||
							(calendarNotificationTemplate.getNotificationTemplateType() == null) ||
							!calendarNotificationTemplate.getNotificationTemplateType()
															 .equals(notificationTemplateType)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_C_NT_NTT,
							finderArgs, calendarNotificationTemplate);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_NT_NTT,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CalendarNotificationTemplate)result;
		}
	}

	/**
	 * Removes the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; from the database.
	 *
	 * @param calendarId the calendar ID
	 * @param notificationType the notification type
	 * @param notificationTemplateType the notification template type
	 * @return the calendar notification template that was removed
	 */
	@Override
	public CalendarNotificationTemplate removeByC_NT_NTT(long calendarId,
		String notificationType, String notificationTemplateType)
		throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = findByC_NT_NTT(calendarId,
				notificationType, notificationTemplateType);

		return remove(calendarNotificationTemplate);
	}

	/**
	 * Returns the number of calendar notification templates where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param notificationType the notification type
	 * @param notificationTemplateType the notification template type
	 * @return the number of matching calendar notification templates
	 */
	@Override
	public int countByC_NT_NTT(long calendarId, String notificationType,
		String notificationTemplateType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_NT_NTT;

		Object[] finderArgs = new Object[] {
				calendarId, notificationType, notificationTemplateType
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_C_NT_NTT_CALENDARID_2);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTYPE_2);
			}

			boolean bindNotificationTemplateType = false;

			if (notificationTemplateType == null) {
				query.append(_FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTEMPLATETYPE_1);
			}
			else if (notificationTemplateType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTEMPLATETYPE_3);
			}
			else {
				bindNotificationTemplateType = true;

				query.append(_FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTEMPLATETYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				if (bindNotificationType) {
					qPos.add(notificationType);
				}

				if (bindNotificationTemplateType) {
					qPos.add(notificationTemplateType);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_NT_NTT_CALENDARID_2 = "calendarNotificationTemplate.calendarId = ? AND ";
	private static final String _FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTYPE_1 = "calendarNotificationTemplate.notificationType IS NULL AND ";
	private static final String _FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTYPE_2 = "calendarNotificationTemplate.notificationType = ? AND ";
	private static final String _FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTYPE_3 = "(calendarNotificationTemplate.notificationType IS NULL OR calendarNotificationTemplate.notificationType = '') AND ";
	private static final String _FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTEMPLATETYPE_1 =
		"calendarNotificationTemplate.notificationTemplateType IS NULL";
	private static final String _FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTEMPLATETYPE_2 =
		"calendarNotificationTemplate.notificationTemplateType = ?";
	private static final String _FINDER_COLUMN_C_NT_NTT_NOTIFICATIONTEMPLATETYPE_3 =
		"(calendarNotificationTemplate.notificationTemplateType IS NULL OR calendarNotificationTemplate.notificationTemplateType = '')";

	public CalendarNotificationTemplatePersistenceImpl() {
		setModelClass(CalendarNotificationTemplate.class);
	}

	/**
	 * Caches the calendar notification template in the entity cache if it is enabled.
	 *
	 * @param calendarNotificationTemplate the calendar notification template
	 */
	@Override
	public void cacheResult(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		entityCache.putResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			calendarNotificationTemplate.getPrimaryKey(),
			calendarNotificationTemplate);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				calendarNotificationTemplate.getUuid(),
				calendarNotificationTemplate.getGroupId()
			}, calendarNotificationTemplate);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_NT_NTT,
			new Object[] {
				calendarNotificationTemplate.getCalendarId(),
				calendarNotificationTemplate.getNotificationType(),
				calendarNotificationTemplate.getNotificationTemplateType()
			}, calendarNotificationTemplate);

		calendarNotificationTemplate.resetOriginalValues();
	}

	/**
	 * Caches the calendar notification templates in the entity cache if it is enabled.
	 *
	 * @param calendarNotificationTemplates the calendar notification templates
	 */
	@Override
	public void cacheResult(
		List<CalendarNotificationTemplate> calendarNotificationTemplates) {
		for (CalendarNotificationTemplate calendarNotificationTemplate : calendarNotificationTemplates) {
			if (entityCache.getResult(
						CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
						CalendarNotificationTemplateImpl.class,
						calendarNotificationTemplate.getPrimaryKey()) == null) {
				cacheResult(calendarNotificationTemplate);
			}
			else {
				calendarNotificationTemplate.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all calendar notification templates.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CalendarNotificationTemplateImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the calendar notification template.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		entityCache.removeResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			calendarNotificationTemplate.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CalendarNotificationTemplateModelImpl)calendarNotificationTemplate);
	}

	@Override
	public void clearCache(
		List<CalendarNotificationTemplate> calendarNotificationTemplates) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CalendarNotificationTemplate calendarNotificationTemplate : calendarNotificationTemplates) {
			entityCache.removeResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
				CalendarNotificationTemplateImpl.class,
				calendarNotificationTemplate.getPrimaryKey());

			clearUniqueFindersCache((CalendarNotificationTemplateModelImpl)calendarNotificationTemplate);
		}
	}

	protected void cacheUniqueFindersCache(
		CalendarNotificationTemplateModelImpl calendarNotificationTemplateModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					calendarNotificationTemplateModelImpl.getUuid(),
					calendarNotificationTemplateModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				calendarNotificationTemplateModelImpl);

			args = new Object[] {
					calendarNotificationTemplateModelImpl.getCalendarId(),
					calendarNotificationTemplateModelImpl.getNotificationType(),
					calendarNotificationTemplateModelImpl.getNotificationTemplateType()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_C_NT_NTT, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_C_NT_NTT, args,
				calendarNotificationTemplateModelImpl);
		}
		else {
			if ((calendarNotificationTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarNotificationTemplateModelImpl.getUuid(),
						calendarNotificationTemplateModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					calendarNotificationTemplateModelImpl);
			}

			if ((calendarNotificationTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_NT_NTT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarNotificationTemplateModelImpl.getCalendarId(),
						calendarNotificationTemplateModelImpl.getNotificationType(),
						calendarNotificationTemplateModelImpl.getNotificationTemplateType()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_C_NT_NTT, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_C_NT_NTT, args,
					calendarNotificationTemplateModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		CalendarNotificationTemplateModelImpl calendarNotificationTemplateModelImpl) {
		Object[] args = new Object[] {
				calendarNotificationTemplateModelImpl.getUuid(),
				calendarNotificationTemplateModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((calendarNotificationTemplateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					calendarNotificationTemplateModelImpl.getOriginalUuid(),
					calendarNotificationTemplateModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] {
				calendarNotificationTemplateModelImpl.getCalendarId(),
				calendarNotificationTemplateModelImpl.getNotificationType(),
				calendarNotificationTemplateModelImpl.getNotificationTemplateType()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_C_NT_NTT, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_C_NT_NTT, args);

		if ((calendarNotificationTemplateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_NT_NTT.getColumnBitmask()) != 0) {
			args = new Object[] {
					calendarNotificationTemplateModelImpl.getOriginalCalendarId(),
					calendarNotificationTemplateModelImpl.getOriginalNotificationType(),
					calendarNotificationTemplateModelImpl.getOriginalNotificationTemplateType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_NT_NTT, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_NT_NTT, args);
		}
	}

	/**
	 * Creates a new calendar notification template with the primary key. Does not add the calendar notification template to the database.
	 *
	 * @param calendarNotificationTemplateId the primary key for the new calendar notification template
	 * @return the new calendar notification template
	 */
	@Override
	public CalendarNotificationTemplate create(
		long calendarNotificationTemplateId) {
		CalendarNotificationTemplate calendarNotificationTemplate = new CalendarNotificationTemplateImpl();

		calendarNotificationTemplate.setNew(true);
		calendarNotificationTemplate.setPrimaryKey(calendarNotificationTemplateId);

		String uuid = PortalUUIDUtil.generate();

		calendarNotificationTemplate.setUuid(uuid);

		calendarNotificationTemplate.setCompanyId(companyProvider.getCompanyId());

		return calendarNotificationTemplate;
	}

	/**
	 * Removes the calendar notification template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarNotificationTemplateId the primary key of the calendar notification template
	 * @return the calendar notification template that was removed
	 * @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	 */
	@Override
	public CalendarNotificationTemplate remove(
		long calendarNotificationTemplateId)
		throws NoSuchNotificationTemplateException {
		return remove((Serializable)calendarNotificationTemplateId);
	}

	/**
	 * Removes the calendar notification template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the calendar notification template
	 * @return the calendar notification template that was removed
	 * @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	 */
	@Override
	public CalendarNotificationTemplate remove(Serializable primaryKey)
		throws NoSuchNotificationTemplateException {
		Session session = null;

		try {
			session = openSession();

			CalendarNotificationTemplate calendarNotificationTemplate = (CalendarNotificationTemplate)session.get(CalendarNotificationTemplateImpl.class,
					primaryKey);

			if (calendarNotificationTemplate == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNotificationTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(calendarNotificationTemplate);
		}
		catch (NoSuchNotificationTemplateException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CalendarNotificationTemplate removeImpl(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		calendarNotificationTemplate = toUnwrappedModel(calendarNotificationTemplate);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(calendarNotificationTemplate)) {
				calendarNotificationTemplate = (CalendarNotificationTemplate)session.get(CalendarNotificationTemplateImpl.class,
						calendarNotificationTemplate.getPrimaryKeyObj());
			}

			if (calendarNotificationTemplate != null) {
				session.delete(calendarNotificationTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (calendarNotificationTemplate != null) {
			clearCache(calendarNotificationTemplate);
		}

		return calendarNotificationTemplate;
	}

	@Override
	public CalendarNotificationTemplate updateImpl(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		calendarNotificationTemplate = toUnwrappedModel(calendarNotificationTemplate);

		boolean isNew = calendarNotificationTemplate.isNew();

		CalendarNotificationTemplateModelImpl calendarNotificationTemplateModelImpl =
			(CalendarNotificationTemplateModelImpl)calendarNotificationTemplate;

		if (Validator.isNull(calendarNotificationTemplate.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			calendarNotificationTemplate.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (calendarNotificationTemplate.getCreateDate() == null)) {
			if (serviceContext == null) {
				calendarNotificationTemplate.setCreateDate(now);
			}
			else {
				calendarNotificationTemplate.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!calendarNotificationTemplateModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				calendarNotificationTemplate.setModifiedDate(now);
			}
			else {
				calendarNotificationTemplate.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (calendarNotificationTemplate.isNew()) {
				session.save(calendarNotificationTemplate);

				calendarNotificationTemplate.setNew(false);
			}
			else {
				calendarNotificationTemplate = (CalendarNotificationTemplate)session.merge(calendarNotificationTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew ||
				!CalendarNotificationTemplateModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((calendarNotificationTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarNotificationTemplateModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] {
						calendarNotificationTemplateModelImpl.getUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((calendarNotificationTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarNotificationTemplateModelImpl.getOriginalUuid(),
						calendarNotificationTemplateModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						calendarNotificationTemplateModelImpl.getUuid(),
						calendarNotificationTemplateModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((calendarNotificationTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarNotificationTemplateModelImpl.getOriginalCalendarId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CALENDARID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID,
					args);

				args = new Object[] {
						calendarNotificationTemplateModelImpl.getCalendarId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CALENDARID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID,
					args);
			}
		}

		entityCache.putResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			calendarNotificationTemplate.getPrimaryKey(),
			calendarNotificationTemplate, false);

		clearUniqueFindersCache(calendarNotificationTemplateModelImpl);
		cacheUniqueFindersCache(calendarNotificationTemplateModelImpl, isNew);

		calendarNotificationTemplate.resetOriginalValues();

		return calendarNotificationTemplate;
	}

	protected CalendarNotificationTemplate toUnwrappedModel(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		if (calendarNotificationTemplate instanceof CalendarNotificationTemplateImpl) {
			return calendarNotificationTemplate;
		}

		CalendarNotificationTemplateImpl calendarNotificationTemplateImpl = new CalendarNotificationTemplateImpl();

		calendarNotificationTemplateImpl.setNew(calendarNotificationTemplate.isNew());
		calendarNotificationTemplateImpl.setPrimaryKey(calendarNotificationTemplate.getPrimaryKey());

		calendarNotificationTemplateImpl.setUuid(calendarNotificationTemplate.getUuid());
		calendarNotificationTemplateImpl.setCalendarNotificationTemplateId(calendarNotificationTemplate.getCalendarNotificationTemplateId());
		calendarNotificationTemplateImpl.setGroupId(calendarNotificationTemplate.getGroupId());
		calendarNotificationTemplateImpl.setCompanyId(calendarNotificationTemplate.getCompanyId());
		calendarNotificationTemplateImpl.setUserId(calendarNotificationTemplate.getUserId());
		calendarNotificationTemplateImpl.setUserName(calendarNotificationTemplate.getUserName());
		calendarNotificationTemplateImpl.setCreateDate(calendarNotificationTemplate.getCreateDate());
		calendarNotificationTemplateImpl.setModifiedDate(calendarNotificationTemplate.getModifiedDate());
		calendarNotificationTemplateImpl.setCalendarId(calendarNotificationTemplate.getCalendarId());
		calendarNotificationTemplateImpl.setNotificationType(calendarNotificationTemplate.getNotificationType());
		calendarNotificationTemplateImpl.setNotificationTypeSettings(calendarNotificationTemplate.getNotificationTypeSettings());
		calendarNotificationTemplateImpl.setNotificationTemplateType(calendarNotificationTemplate.getNotificationTemplateType());
		calendarNotificationTemplateImpl.setSubject(calendarNotificationTemplate.getSubject());
		calendarNotificationTemplateImpl.setBody(calendarNotificationTemplate.getBody());
		calendarNotificationTemplateImpl.setLastPublishDate(calendarNotificationTemplate.getLastPublishDate());

		return calendarNotificationTemplateImpl;
	}

	/**
	 * Returns the calendar notification template with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar notification template
	 * @return the calendar notification template
	 * @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	 */
	@Override
	public CalendarNotificationTemplate findByPrimaryKey(
		Serializable primaryKey) throws NoSuchNotificationTemplateException {
		CalendarNotificationTemplate calendarNotificationTemplate = fetchByPrimaryKey(primaryKey);

		if (calendarNotificationTemplate == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNotificationTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return calendarNotificationTemplate;
	}

	/**
	 * Returns the calendar notification template with the primary key or throws a {@link NoSuchNotificationTemplateException} if it could not be found.
	 *
	 * @param calendarNotificationTemplateId the primary key of the calendar notification template
	 * @return the calendar notification template
	 * @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	 */
	@Override
	public CalendarNotificationTemplate findByPrimaryKey(
		long calendarNotificationTemplateId)
		throws NoSuchNotificationTemplateException {
		return findByPrimaryKey((Serializable)calendarNotificationTemplateId);
	}

	/**
	 * Returns the calendar notification template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar notification template
	 * @return the calendar notification template, or <code>null</code> if a calendar notification template with the primary key could not be found
	 */
	@Override
	public CalendarNotificationTemplate fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
				CalendarNotificationTemplateImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CalendarNotificationTemplate calendarNotificationTemplate = (CalendarNotificationTemplate)serializable;

		if (calendarNotificationTemplate == null) {
			Session session = null;

			try {
				session = openSession();

				calendarNotificationTemplate = (CalendarNotificationTemplate)session.get(CalendarNotificationTemplateImpl.class,
						primaryKey);

				if (calendarNotificationTemplate != null) {
					cacheResult(calendarNotificationTemplate);
				}
				else {
					entityCache.putResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
						CalendarNotificationTemplateImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
					CalendarNotificationTemplateImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return calendarNotificationTemplate;
	}

	/**
	 * Returns the calendar notification template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param calendarNotificationTemplateId the primary key of the calendar notification template
	 * @return the calendar notification template, or <code>null</code> if a calendar notification template with the primary key could not be found
	 */
	@Override
	public CalendarNotificationTemplate fetchByPrimaryKey(
		long calendarNotificationTemplateId) {
		return fetchByPrimaryKey((Serializable)calendarNotificationTemplateId);
	}

	@Override
	public Map<Serializable, CalendarNotificationTemplate> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CalendarNotificationTemplate> map = new HashMap<Serializable, CalendarNotificationTemplate>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CalendarNotificationTemplate calendarNotificationTemplate = fetchByPrimaryKey(primaryKey);

			if (calendarNotificationTemplate != null) {
				map.put(primaryKey, calendarNotificationTemplate);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
					CalendarNotificationTemplateImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(CalendarNotificationTemplate)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (CalendarNotificationTemplate calendarNotificationTemplate : (List<CalendarNotificationTemplate>)q.list()) {
				map.put(calendarNotificationTemplate.getPrimaryKeyObj(),
					calendarNotificationTemplate);

				cacheResult(calendarNotificationTemplate);

				uncachedPrimaryKeys.remove(calendarNotificationTemplate.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
					CalendarNotificationTemplateImpl.class, primaryKey,
					nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the calendar notification templates.
	 *
	 * @return the calendar notification templates
	 */
	@Override
	public List<CalendarNotificationTemplate> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<CalendarNotificationTemplate> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<CalendarNotificationTemplate> findAll(int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<CalendarNotificationTemplate> findAll(int start, int end,
		OrderByComparator<CalendarNotificationTemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<CalendarNotificationTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<CalendarNotificationTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE;

				if (pagination) {
					sql = sql.concat(CalendarNotificationTemplateModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CalendarNotificationTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarNotificationTemplate>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the calendar notification templates from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CalendarNotificationTemplate calendarNotificationTemplate : findAll()) {
			remove(calendarNotificationTemplate);
		}
	}

	/**
	 * Returns the number of calendar notification templates.
	 *
	 * @return the number of calendar notification templates
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CALENDARNOTIFICATIONTEMPLATE);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CalendarNotificationTemplateModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the calendar notification template persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CalendarNotificationTemplateImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE = "SELECT calendarNotificationTemplate FROM CalendarNotificationTemplate calendarNotificationTemplate";
	private static final String _SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE_PKS_IN =
		"SELECT calendarNotificationTemplate FROM CalendarNotificationTemplate calendarNotificationTemplate WHERE calendarNotificationTemplateId IN (";
	private static final String _SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE = "SELECT calendarNotificationTemplate FROM CalendarNotificationTemplate calendarNotificationTemplate WHERE ";
	private static final String _SQL_COUNT_CALENDARNOTIFICATIONTEMPLATE = "SELECT COUNT(calendarNotificationTemplate) FROM CalendarNotificationTemplate calendarNotificationTemplate";
	private static final String _SQL_COUNT_CALENDARNOTIFICATIONTEMPLATE_WHERE = "SELECT COUNT(calendarNotificationTemplate) FROM CalendarNotificationTemplate calendarNotificationTemplate WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "calendarNotificationTemplate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CalendarNotificationTemplate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CalendarNotificationTemplate exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CalendarNotificationTemplatePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}