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

package com.liferay.mobile.device.rules.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.mobile.device.rules.exception.NoSuchRuleGroupException;
import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.mobile.device.rules.model.impl.MDRRuleGroupImpl;
import com.liferay.mobile.device.rules.model.impl.MDRRuleGroupModelImpl;
import com.liferay.mobile.device.rules.service.persistence.MDRRuleGroupPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Arrays;
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
 * The persistence implementation for the m d r rule group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Edward C. Han
 * @see MDRRuleGroupPersistence
 * @see com.liferay.mobile.device.rules.service.persistence.MDRRuleGroupUtil
 * @generated
 */
@ProviderType
public class MDRRuleGroupPersistenceImpl extends BasePersistenceImpl<MDRRuleGroup>
	implements MDRRuleGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MDRRuleGroupUtil} to access the m d r rule group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MDRRuleGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, MDRRuleGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, MDRRuleGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, MDRRuleGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, MDRRuleGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			MDRRuleGroupModelImpl.UUID_COLUMN_BITMASK |
			MDRRuleGroupModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the m d r rule groups where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m d r rule groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @return the range of matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the m d r rule groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByUuid(String uuid, int start, int end,
		OrderByComparator<MDRRuleGroup> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the m d r rule groups where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByUuid(String uuid, int start, int end,
		OrderByComparator<MDRRuleGroup> orderByComparator,
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

		List<MDRRuleGroup> list = null;

		if (retrieveFromCache) {
			list = (List<MDRRuleGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MDRRuleGroup mdrRuleGroup : list) {
					if (!Objects.equals(uuid, mdrRuleGroup.getUuid())) {
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

			query.append(_SQL_SELECT_MDRRULEGROUP_WHERE);

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
				query.append(MDRRuleGroupModelImpl.ORDER_BY_JPQL);
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
					list = (List<MDRRuleGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MDRRuleGroup>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first m d r rule group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching m d r rule group
	 * @throws NoSuchRuleGroupException if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup findByUuid_First(String uuid,
		OrderByComparator<MDRRuleGroup> orderByComparator)
		throws NoSuchRuleGroupException {
		MDRRuleGroup mdrRuleGroup = fetchByUuid_First(uuid, orderByComparator);

		if (mdrRuleGroup != null) {
			return mdrRuleGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRuleGroupException(msg.toString());
	}

	/**
	 * Returns the first m d r rule group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching m d r rule group, or <code>null</code> if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup fetchByUuid_First(String uuid,
		OrderByComparator<MDRRuleGroup> orderByComparator) {
		List<MDRRuleGroup> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last m d r rule group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching m d r rule group
	 * @throws NoSuchRuleGroupException if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup findByUuid_Last(String uuid,
		OrderByComparator<MDRRuleGroup> orderByComparator)
		throws NoSuchRuleGroupException {
		MDRRuleGroup mdrRuleGroup = fetchByUuid_Last(uuid, orderByComparator);

		if (mdrRuleGroup != null) {
			return mdrRuleGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRuleGroupException(msg.toString());
	}

	/**
	 * Returns the last m d r rule group in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching m d r rule group, or <code>null</code> if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup fetchByUuid_Last(String uuid,
		OrderByComparator<MDRRuleGroup> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<MDRRuleGroup> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the m d r rule groups before and after the current m d r rule group in the ordered set where uuid = &#63;.
	 *
	 * @param ruleGroupId the primary key of the current m d r rule group
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next m d r rule group
	 * @throws NoSuchRuleGroupException if a m d r rule group with the primary key could not be found
	 */
	@Override
	public MDRRuleGroup[] findByUuid_PrevAndNext(long ruleGroupId, String uuid,
		OrderByComparator<MDRRuleGroup> orderByComparator)
		throws NoSuchRuleGroupException {
		MDRRuleGroup mdrRuleGroup = findByPrimaryKey(ruleGroupId);

		Session session = null;

		try {
			session = openSession();

			MDRRuleGroup[] array = new MDRRuleGroupImpl[3];

			array[0] = getByUuid_PrevAndNext(session, mdrRuleGroup, uuid,
					orderByComparator, true);

			array[1] = mdrRuleGroup;

			array[2] = getByUuid_PrevAndNext(session, mdrRuleGroup, uuid,
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

	protected MDRRuleGroup getByUuid_PrevAndNext(Session session,
		MDRRuleGroup mdrRuleGroup, String uuid,
		OrderByComparator<MDRRuleGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MDRRULEGROUP_WHERE);

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
			query.append(MDRRuleGroupModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(mdrRuleGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MDRRuleGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the m d r rule groups where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (MDRRuleGroup mdrRuleGroup : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(mdrRuleGroup);
		}
	}

	/**
	 * Returns the number of m d r rule groups where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching m d r rule groups
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MDRRULEGROUP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "mdrRuleGroup.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "mdrRuleGroup.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(mdrRuleGroup.uuid IS NULL OR mdrRuleGroup.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, MDRRuleGroupImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			MDRRuleGroupModelImpl.UUID_COLUMN_BITMASK |
			MDRRuleGroupModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the m d r rule group where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRuleGroupException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching m d r rule group
	 * @throws NoSuchRuleGroupException if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup findByUUID_G(String uuid, long groupId)
		throws NoSuchRuleGroupException {
		MDRRuleGroup mdrRuleGroup = fetchByUUID_G(uuid, groupId);

		if (mdrRuleGroup == null) {
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

			throw new NoSuchRuleGroupException(msg.toString());
		}

		return mdrRuleGroup;
	}

	/**
	 * Returns the m d r rule group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching m d r rule group, or <code>null</code> if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the m d r rule group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching m d r rule group, or <code>null</code> if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof MDRRuleGroup) {
			MDRRuleGroup mdrRuleGroup = (MDRRuleGroup)result;

			if (!Objects.equals(uuid, mdrRuleGroup.getUuid()) ||
					(groupId != mdrRuleGroup.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MDRRULEGROUP_WHERE);

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

				List<MDRRuleGroup> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					MDRRuleGroup mdrRuleGroup = list.get(0);

					result = mdrRuleGroup;

					cacheResult(mdrRuleGroup);

					if ((mdrRuleGroup.getUuid() == null) ||
							!mdrRuleGroup.getUuid().equals(uuid) ||
							(mdrRuleGroup.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, mdrRuleGroup);
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
			return (MDRRuleGroup)result;
		}
	}

	/**
	 * Removes the m d r rule group where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the m d r rule group that was removed
	 */
	@Override
	public MDRRuleGroup removeByUUID_G(String uuid, long groupId)
		throws NoSuchRuleGroupException {
		MDRRuleGroup mdrRuleGroup = findByUUID_G(uuid, groupId);

		return remove(mdrRuleGroup);
	}

	/**
	 * Returns the number of m d r rule groups where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching m d r rule groups
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MDRRULEGROUP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "mdrRuleGroup.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "mdrRuleGroup.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(mdrRuleGroup.uuid IS NULL OR mdrRuleGroup.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "mdrRuleGroup.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, MDRRuleGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, MDRRuleGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			MDRRuleGroupModelImpl.UUID_COLUMN_BITMASK |
			MDRRuleGroupModelImpl.COMPANYID_COLUMN_BITMASK |
			MDRRuleGroupModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the m d r rule groups where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m d r rule groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @return the range of matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the m d r rule groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<MDRRuleGroup> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the m d r rule groups where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<MDRRuleGroup> orderByComparator,
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

		List<MDRRuleGroup> list = null;

		if (retrieveFromCache) {
			list = (List<MDRRuleGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MDRRuleGroup mdrRuleGroup : list) {
					if (!Objects.equals(uuid, mdrRuleGroup.getUuid()) ||
							(companyId != mdrRuleGroup.getCompanyId())) {
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

			query.append(_SQL_SELECT_MDRRULEGROUP_WHERE);

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
				query.append(MDRRuleGroupModelImpl.ORDER_BY_JPQL);
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
					list = (List<MDRRuleGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MDRRuleGroup>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first m d r rule group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching m d r rule group
	 * @throws NoSuchRuleGroupException if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<MDRRuleGroup> orderByComparator)
		throws NoSuchRuleGroupException {
		MDRRuleGroup mdrRuleGroup = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (mdrRuleGroup != null) {
			return mdrRuleGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRuleGroupException(msg.toString());
	}

	/**
	 * Returns the first m d r rule group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching m d r rule group, or <code>null</code> if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<MDRRuleGroup> orderByComparator) {
		List<MDRRuleGroup> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last m d r rule group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching m d r rule group
	 * @throws NoSuchRuleGroupException if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<MDRRuleGroup> orderByComparator)
		throws NoSuchRuleGroupException {
		MDRRuleGroup mdrRuleGroup = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (mdrRuleGroup != null) {
			return mdrRuleGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRuleGroupException(msg.toString());
	}

	/**
	 * Returns the last m d r rule group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching m d r rule group, or <code>null</code> if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<MDRRuleGroup> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<MDRRuleGroup> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the m d r rule groups before and after the current m d r rule group in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param ruleGroupId the primary key of the current m d r rule group
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next m d r rule group
	 * @throws NoSuchRuleGroupException if a m d r rule group with the primary key could not be found
	 */
	@Override
	public MDRRuleGroup[] findByUuid_C_PrevAndNext(long ruleGroupId,
		String uuid, long companyId,
		OrderByComparator<MDRRuleGroup> orderByComparator)
		throws NoSuchRuleGroupException {
		MDRRuleGroup mdrRuleGroup = findByPrimaryKey(ruleGroupId);

		Session session = null;

		try {
			session = openSession();

			MDRRuleGroup[] array = new MDRRuleGroupImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, mdrRuleGroup, uuid,
					companyId, orderByComparator, true);

			array[1] = mdrRuleGroup;

			array[2] = getByUuid_C_PrevAndNext(session, mdrRuleGroup, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MDRRuleGroup getByUuid_C_PrevAndNext(Session session,
		MDRRuleGroup mdrRuleGroup, String uuid, long companyId,
		OrderByComparator<MDRRuleGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MDRRULEGROUP_WHERE);

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
			query.append(MDRRuleGroupModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(mdrRuleGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MDRRuleGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the m d r rule groups where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (MDRRuleGroup mdrRuleGroup : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(mdrRuleGroup);
		}
	}

	/**
	 * Returns the number of m d r rule groups where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching m d r rule groups
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MDRRULEGROUP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "mdrRuleGroup.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "mdrRuleGroup.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(mdrRuleGroup.uuid IS NULL OR mdrRuleGroup.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "mdrRuleGroup.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, MDRRuleGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, MDRRuleGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			MDRRuleGroupModelImpl.GROUPID_COLUMN_BITMASK |
			MDRRuleGroupModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPID = new FinderPath(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the m d r rule groups where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m d r rule groups where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @return the range of matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the m d r rule groups where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByGroupId(long groupId, int start, int end,
		OrderByComparator<MDRRuleGroup> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the m d r rule groups where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByGroupId(long groupId, int start, int end,
		OrderByComparator<MDRRuleGroup> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<MDRRuleGroup> list = null;

		if (retrieveFromCache) {
			list = (List<MDRRuleGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MDRRuleGroup mdrRuleGroup : list) {
					if ((groupId != mdrRuleGroup.getGroupId())) {
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

			query.append(_SQL_SELECT_MDRRULEGROUP_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MDRRuleGroupModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<MDRRuleGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MDRRuleGroup>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first m d r rule group in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching m d r rule group
	 * @throws NoSuchRuleGroupException if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup findByGroupId_First(long groupId,
		OrderByComparator<MDRRuleGroup> orderByComparator)
		throws NoSuchRuleGroupException {
		MDRRuleGroup mdrRuleGroup = fetchByGroupId_First(groupId,
				orderByComparator);

		if (mdrRuleGroup != null) {
			return mdrRuleGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRuleGroupException(msg.toString());
	}

	/**
	 * Returns the first m d r rule group in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching m d r rule group, or <code>null</code> if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup fetchByGroupId_First(long groupId,
		OrderByComparator<MDRRuleGroup> orderByComparator) {
		List<MDRRuleGroup> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last m d r rule group in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching m d r rule group
	 * @throws NoSuchRuleGroupException if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup findByGroupId_Last(long groupId,
		OrderByComparator<MDRRuleGroup> orderByComparator)
		throws NoSuchRuleGroupException {
		MDRRuleGroup mdrRuleGroup = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (mdrRuleGroup != null) {
			return mdrRuleGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRuleGroupException(msg.toString());
	}

	/**
	 * Returns the last m d r rule group in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching m d r rule group, or <code>null</code> if a matching m d r rule group could not be found
	 */
	@Override
	public MDRRuleGroup fetchByGroupId_Last(long groupId,
		OrderByComparator<MDRRuleGroup> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<MDRRuleGroup> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the m d r rule groups before and after the current m d r rule group in the ordered set where groupId = &#63;.
	 *
	 * @param ruleGroupId the primary key of the current m d r rule group
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next m d r rule group
	 * @throws NoSuchRuleGroupException if a m d r rule group with the primary key could not be found
	 */
	@Override
	public MDRRuleGroup[] findByGroupId_PrevAndNext(long ruleGroupId,
		long groupId, OrderByComparator<MDRRuleGroup> orderByComparator)
		throws NoSuchRuleGroupException {
		MDRRuleGroup mdrRuleGroup = findByPrimaryKey(ruleGroupId);

		Session session = null;

		try {
			session = openSession();

			MDRRuleGroup[] array = new MDRRuleGroupImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, mdrRuleGroup, groupId,
					orderByComparator, true);

			array[1] = mdrRuleGroup;

			array[2] = getByGroupId_PrevAndNext(session, mdrRuleGroup, groupId,
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

	protected MDRRuleGroup getByGroupId_PrevAndNext(Session session,
		MDRRuleGroup mdrRuleGroup, long groupId,
		OrderByComparator<MDRRuleGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MDRRULEGROUP_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(MDRRuleGroupModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mdrRuleGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MDRRuleGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the m d r rule groups that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching m d r rule groups that the user has permission to view
	 */
	@Override
	public List<MDRRuleGroup> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m d r rule groups that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @return the range of matching m d r rule groups that the user has permission to view
	 */
	@Override
	public List<MDRRuleGroup> filterFindByGroupId(long groupId, int start,
		int end) {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the m d r rule groups that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching m d r rule groups that the user has permission to view
	 */
	@Override
	public List<MDRRuleGroup> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<MDRRuleGroup> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUP_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUP_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUP_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MDRRuleGroupModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MDRRuleGroupModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MDRRuleGroup.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MDRRuleGroupImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MDRRuleGroupImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<MDRRuleGroup>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the m d r rule groups before and after the current m d r rule group in the ordered set of m d r rule groups that the user has permission to view where groupId = &#63;.
	 *
	 * @param ruleGroupId the primary key of the current m d r rule group
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next m d r rule group
	 * @throws NoSuchRuleGroupException if a m d r rule group with the primary key could not be found
	 */
	@Override
	public MDRRuleGroup[] filterFindByGroupId_PrevAndNext(long ruleGroupId,
		long groupId, OrderByComparator<MDRRuleGroup> orderByComparator)
		throws NoSuchRuleGroupException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(ruleGroupId, groupId,
				orderByComparator);
		}

		MDRRuleGroup mdrRuleGroup = findByPrimaryKey(ruleGroupId);

		Session session = null;

		try {
			session = openSession();

			MDRRuleGroup[] array = new MDRRuleGroupImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, mdrRuleGroup,
					groupId, orderByComparator, true);

			array[1] = mdrRuleGroup;

			array[2] = filterGetByGroupId_PrevAndNext(session, mdrRuleGroup,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MDRRuleGroup filterGetByGroupId_PrevAndNext(Session session,
		MDRRuleGroup mdrRuleGroup, long groupId,
		OrderByComparator<MDRRuleGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUP_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUP_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUP_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MDRRuleGroupModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MDRRuleGroupModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MDRRuleGroup.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MDRRuleGroupImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MDRRuleGroupImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mdrRuleGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MDRRuleGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the m d r rule groups that the user has permission to view where groupId = any &#63;.
	 *
	 * @param groupIds the group IDs
	 * @return the matching m d r rule groups that the user has permission to view
	 */
	@Override
	public List<MDRRuleGroup> filterFindByGroupId(long[] groupIds) {
		return filterFindByGroupId(groupIds, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m d r rule groups that the user has permission to view where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @return the range of matching m d r rule groups that the user has permission to view
	 */
	@Override
	public List<MDRRuleGroup> filterFindByGroupId(long[] groupIds, int start,
		int end) {
		return filterFindByGroupId(groupIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the m d r rule groups that the user has permission to view where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching m d r rule groups that the user has permission to view
	 */
	@Override
	public List<MDRRuleGroup> filterFindByGroupId(long[] groupIds, int start,
		int end, OrderByComparator<MDRRuleGroup> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupIds)) {
			return findByGroupId(groupIds, start, end, orderByComparator);
		}

		if (groupIds == null) {
			groupIds = new long[0];
		}
		else if (groupIds.length > 1) {
			groupIds = ArrayUtil.unique(groupIds);

			Arrays.sort(groupIds);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUP_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUP_NO_INLINE_DISTINCT_WHERE_1);
		}

		if (groupIds.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_7);

			query.append(StringUtil.merge(groupIds));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUP_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MDRRuleGroupModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MDRRuleGroupModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MDRRuleGroup.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupIds);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MDRRuleGroupImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MDRRuleGroupImpl.class);
			}

			return (List<MDRRuleGroup>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the m d r rule groups where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @return the matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByGroupId(long[] groupIds) {
		return findByGroupId(groupIds, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the m d r rule groups where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @return the range of matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByGroupId(long[] groupIds, int start, int end) {
		return findByGroupId(groupIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the m d r rule groups where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByGroupId(long[] groupIds, int start,
		int end, OrderByComparator<MDRRuleGroup> orderByComparator) {
		return findByGroupId(groupIds, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the m d r rule groups where groupId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findByGroupId(long[] groupIds, int start,
		int end, OrderByComparator<MDRRuleGroup> orderByComparator,
		boolean retrieveFromCache) {
		if (groupIds == null) {
			groupIds = new long[0];
		}
		else if (groupIds.length > 1) {
			groupIds = ArrayUtil.unique(groupIds);

			Arrays.sort(groupIds);
		}

		if (groupIds.length == 1) {
			return findByGroupId(groupIds[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(groupIds) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(groupIds),
					
					start, end, orderByComparator
				};
		}

		List<MDRRuleGroup> list = null;

		if (retrieveFromCache) {
			list = (List<MDRRuleGroup>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MDRRuleGroup mdrRuleGroup : list) {
					if (!ArrayUtil.contains(groupIds, mdrRuleGroup.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_MDRRULEGROUP_WHERE);

			if (groupIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_GROUPID_GROUPID_7);

				query.append(StringUtil.merge(groupIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MDRRuleGroupModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MDRRuleGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MDRRuleGroup>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the m d r rule groups where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (MDRRuleGroup mdrRuleGroup : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(mdrRuleGroup);
		}
	}

	/**
	 * Returns the number of m d r rule groups where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching m d r rule groups
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MDRRULEGROUP_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	/**
	 * Returns the number of m d r rule groups where groupId = any &#63;.
	 *
	 * @param groupIds the group IDs
	 * @return the number of matching m d r rule groups
	 */
	@Override
	public int countByGroupId(long[] groupIds) {
		if (groupIds == null) {
			groupIds = new long[0];
		}
		else if (groupIds.length > 1) {
			groupIds = ArrayUtil.unique(groupIds);

			Arrays.sort(groupIds);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(groupIds) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_MDRRULEGROUP_WHERE);

			if (groupIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_GROUPID_GROUPID_7);

				query.append(StringUtil.merge(groupIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPID,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of m d r rule groups that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching m d r rule groups that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_MDRRULEGROUP_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MDRRuleGroup.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of m d r rule groups that the user has permission to view where groupId = any &#63;.
	 *
	 * @param groupIds the group IDs
	 * @return the number of matching m d r rule groups that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long[] groupIds) {
		if (!InlineSQLHelperUtil.isEnabled(groupIds)) {
			return countByGroupId(groupIds);
		}

		if (groupIds == null) {
			groupIds = new long[0];
		}
		else if (groupIds.length > 1) {
			groupIds = ArrayUtil.unique(groupIds);

			Arrays.sort(groupIds);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_MDRRULEGROUP_WHERE);

		if (groupIds.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_7);

			query.append(StringUtil.merge(groupIds));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MDRRuleGroup.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupIds);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "mdrRuleGroup.groupId = ?";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_7 = "mdrRuleGroup.groupId IN (";

	public MDRRuleGroupPersistenceImpl() {
		setModelClass(MDRRuleGroup.class);
	}

	/**
	 * Caches the m d r rule group in the entity cache if it is enabled.
	 *
	 * @param mdrRuleGroup the m d r rule group
	 */
	@Override
	public void cacheResult(MDRRuleGroup mdrRuleGroup) {
		entityCache.putResult(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupImpl.class, mdrRuleGroup.getPrimaryKey(), mdrRuleGroup);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { mdrRuleGroup.getUuid(), mdrRuleGroup.getGroupId() },
			mdrRuleGroup);

		mdrRuleGroup.resetOriginalValues();
	}

	/**
	 * Caches the m d r rule groups in the entity cache if it is enabled.
	 *
	 * @param mdrRuleGroups the m d r rule groups
	 */
	@Override
	public void cacheResult(List<MDRRuleGroup> mdrRuleGroups) {
		for (MDRRuleGroup mdrRuleGroup : mdrRuleGroups) {
			if (entityCache.getResult(
						MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
						MDRRuleGroupImpl.class, mdrRuleGroup.getPrimaryKey()) == null) {
				cacheResult(mdrRuleGroup);
			}
			else {
				mdrRuleGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all m d r rule groups.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MDRRuleGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the m d r rule group.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MDRRuleGroup mdrRuleGroup) {
		entityCache.removeResult(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupImpl.class, mdrRuleGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((MDRRuleGroupModelImpl)mdrRuleGroup);
	}

	@Override
	public void clearCache(List<MDRRuleGroup> mdrRuleGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MDRRuleGroup mdrRuleGroup : mdrRuleGroups) {
			entityCache.removeResult(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
				MDRRuleGroupImpl.class, mdrRuleGroup.getPrimaryKey());

			clearUniqueFindersCache((MDRRuleGroupModelImpl)mdrRuleGroup);
		}
	}

	protected void cacheUniqueFindersCache(
		MDRRuleGroupModelImpl mdrRuleGroupModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					mdrRuleGroupModelImpl.getUuid(),
					mdrRuleGroupModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				mdrRuleGroupModelImpl);
		}
		else {
			if ((mdrRuleGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						mdrRuleGroupModelImpl.getUuid(),
						mdrRuleGroupModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					mdrRuleGroupModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		MDRRuleGroupModelImpl mdrRuleGroupModelImpl) {
		Object[] args = new Object[] {
				mdrRuleGroupModelImpl.getUuid(),
				mdrRuleGroupModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((mdrRuleGroupModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					mdrRuleGroupModelImpl.getOriginalUuid(),
					mdrRuleGroupModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new m d r rule group with the primary key. Does not add the m d r rule group to the database.
	 *
	 * @param ruleGroupId the primary key for the new m d r rule group
	 * @return the new m d r rule group
	 */
	@Override
	public MDRRuleGroup create(long ruleGroupId) {
		MDRRuleGroup mdrRuleGroup = new MDRRuleGroupImpl();

		mdrRuleGroup.setNew(true);
		mdrRuleGroup.setPrimaryKey(ruleGroupId);

		String uuid = PortalUUIDUtil.generate();

		mdrRuleGroup.setUuid(uuid);

		mdrRuleGroup.setCompanyId(companyProvider.getCompanyId());

		return mdrRuleGroup;
	}

	/**
	 * Removes the m d r rule group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ruleGroupId the primary key of the m d r rule group
	 * @return the m d r rule group that was removed
	 * @throws NoSuchRuleGroupException if a m d r rule group with the primary key could not be found
	 */
	@Override
	public MDRRuleGroup remove(long ruleGroupId)
		throws NoSuchRuleGroupException {
		return remove((Serializable)ruleGroupId);
	}

	/**
	 * Removes the m d r rule group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the m d r rule group
	 * @return the m d r rule group that was removed
	 * @throws NoSuchRuleGroupException if a m d r rule group with the primary key could not be found
	 */
	@Override
	public MDRRuleGroup remove(Serializable primaryKey)
		throws NoSuchRuleGroupException {
		Session session = null;

		try {
			session = openSession();

			MDRRuleGroup mdrRuleGroup = (MDRRuleGroup)session.get(MDRRuleGroupImpl.class,
					primaryKey);

			if (mdrRuleGroup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRuleGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mdrRuleGroup);
		}
		catch (NoSuchRuleGroupException nsee) {
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
	protected MDRRuleGroup removeImpl(MDRRuleGroup mdrRuleGroup) {
		mdrRuleGroup = toUnwrappedModel(mdrRuleGroup);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mdrRuleGroup)) {
				mdrRuleGroup = (MDRRuleGroup)session.get(MDRRuleGroupImpl.class,
						mdrRuleGroup.getPrimaryKeyObj());
			}

			if (mdrRuleGroup != null) {
				session.delete(mdrRuleGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mdrRuleGroup != null) {
			clearCache(mdrRuleGroup);
		}

		return mdrRuleGroup;
	}

	@Override
	public MDRRuleGroup updateImpl(MDRRuleGroup mdrRuleGroup) {
		mdrRuleGroup = toUnwrappedModel(mdrRuleGroup);

		boolean isNew = mdrRuleGroup.isNew();

		MDRRuleGroupModelImpl mdrRuleGroupModelImpl = (MDRRuleGroupModelImpl)mdrRuleGroup;

		if (Validator.isNull(mdrRuleGroup.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			mdrRuleGroup.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (mdrRuleGroup.getCreateDate() == null)) {
			if (serviceContext == null) {
				mdrRuleGroup.setCreateDate(now);
			}
			else {
				mdrRuleGroup.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!mdrRuleGroupModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				mdrRuleGroup.setModifiedDate(now);
			}
			else {
				mdrRuleGroup.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (mdrRuleGroup.isNew()) {
				session.save(mdrRuleGroup);

				mdrRuleGroup.setNew(false);
			}
			else {
				mdrRuleGroup = (MDRRuleGroup)session.merge(mdrRuleGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MDRRuleGroupModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((mdrRuleGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						mdrRuleGroupModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { mdrRuleGroupModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((mdrRuleGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						mdrRuleGroupModelImpl.getOriginalUuid(),
						mdrRuleGroupModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						mdrRuleGroupModelImpl.getUuid(),
						mdrRuleGroupModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((mdrRuleGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						mdrRuleGroupModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { mdrRuleGroupModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupImpl.class, mdrRuleGroup.getPrimaryKey(), mdrRuleGroup,
			false);

		clearUniqueFindersCache(mdrRuleGroupModelImpl);
		cacheUniqueFindersCache(mdrRuleGroupModelImpl, isNew);

		mdrRuleGroup.resetOriginalValues();

		return mdrRuleGroup;
	}

	protected MDRRuleGroup toUnwrappedModel(MDRRuleGroup mdrRuleGroup) {
		if (mdrRuleGroup instanceof MDRRuleGroupImpl) {
			return mdrRuleGroup;
		}

		MDRRuleGroupImpl mdrRuleGroupImpl = new MDRRuleGroupImpl();

		mdrRuleGroupImpl.setNew(mdrRuleGroup.isNew());
		mdrRuleGroupImpl.setPrimaryKey(mdrRuleGroup.getPrimaryKey());

		mdrRuleGroupImpl.setUuid(mdrRuleGroup.getUuid());
		mdrRuleGroupImpl.setRuleGroupId(mdrRuleGroup.getRuleGroupId());
		mdrRuleGroupImpl.setGroupId(mdrRuleGroup.getGroupId());
		mdrRuleGroupImpl.setCompanyId(mdrRuleGroup.getCompanyId());
		mdrRuleGroupImpl.setUserId(mdrRuleGroup.getUserId());
		mdrRuleGroupImpl.setUserName(mdrRuleGroup.getUserName());
		mdrRuleGroupImpl.setCreateDate(mdrRuleGroup.getCreateDate());
		mdrRuleGroupImpl.setModifiedDate(mdrRuleGroup.getModifiedDate());
		mdrRuleGroupImpl.setName(mdrRuleGroup.getName());
		mdrRuleGroupImpl.setDescription(mdrRuleGroup.getDescription());
		mdrRuleGroupImpl.setLastPublishDate(mdrRuleGroup.getLastPublishDate());

		return mdrRuleGroupImpl;
	}

	/**
	 * Returns the m d r rule group with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the m d r rule group
	 * @return the m d r rule group
	 * @throws NoSuchRuleGroupException if a m d r rule group with the primary key could not be found
	 */
	@Override
	public MDRRuleGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRuleGroupException {
		MDRRuleGroup mdrRuleGroup = fetchByPrimaryKey(primaryKey);

		if (mdrRuleGroup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRuleGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return mdrRuleGroup;
	}

	/**
	 * Returns the m d r rule group with the primary key or throws a {@link NoSuchRuleGroupException} if it could not be found.
	 *
	 * @param ruleGroupId the primary key of the m d r rule group
	 * @return the m d r rule group
	 * @throws NoSuchRuleGroupException if a m d r rule group with the primary key could not be found
	 */
	@Override
	public MDRRuleGroup findByPrimaryKey(long ruleGroupId)
		throws NoSuchRuleGroupException {
		return findByPrimaryKey((Serializable)ruleGroupId);
	}

	/**
	 * Returns the m d r rule group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the m d r rule group
	 * @return the m d r rule group, or <code>null</code> if a m d r rule group with the primary key could not be found
	 */
	@Override
	public MDRRuleGroup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
				MDRRuleGroupImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MDRRuleGroup mdrRuleGroup = (MDRRuleGroup)serializable;

		if (mdrRuleGroup == null) {
			Session session = null;

			try {
				session = openSession();

				mdrRuleGroup = (MDRRuleGroup)session.get(MDRRuleGroupImpl.class,
						primaryKey);

				if (mdrRuleGroup != null) {
					cacheResult(mdrRuleGroup);
				}
				else {
					entityCache.putResult(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
						MDRRuleGroupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
					MDRRuleGroupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return mdrRuleGroup;
	}

	/**
	 * Returns the m d r rule group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ruleGroupId the primary key of the m d r rule group
	 * @return the m d r rule group, or <code>null</code> if a m d r rule group with the primary key could not be found
	 */
	@Override
	public MDRRuleGroup fetchByPrimaryKey(long ruleGroupId) {
		return fetchByPrimaryKey((Serializable)ruleGroupId);
	}

	@Override
	public Map<Serializable, MDRRuleGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MDRRuleGroup> map = new HashMap<Serializable, MDRRuleGroup>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MDRRuleGroup mdrRuleGroup = fetchByPrimaryKey(primaryKey);

			if (mdrRuleGroup != null) {
				map.put(primaryKey, mdrRuleGroup);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
					MDRRuleGroupImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MDRRuleGroup)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MDRRULEGROUP_WHERE_PKS_IN);

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

			for (MDRRuleGroup mdrRuleGroup : (List<MDRRuleGroup>)q.list()) {
				map.put(mdrRuleGroup.getPrimaryKeyObj(), mdrRuleGroup);

				cacheResult(mdrRuleGroup);

				uncachedPrimaryKeys.remove(mdrRuleGroup.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MDRRuleGroupModelImpl.ENTITY_CACHE_ENABLED,
					MDRRuleGroupImpl.class, primaryKey, nullModel);
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
	 * Returns all the m d r rule groups.
	 *
	 * @return the m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m d r rule groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @return the range of m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the m d r rule groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findAll(int start, int end,
		OrderByComparator<MDRRuleGroup> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the m d r rule groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MDRRuleGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m d r rule groups
	 * @param end the upper bound of the range of m d r rule groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of m d r rule groups
	 */
	@Override
	public List<MDRRuleGroup> findAll(int start, int end,
		OrderByComparator<MDRRuleGroup> orderByComparator,
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

		List<MDRRuleGroup> list = null;

		if (retrieveFromCache) {
			list = (List<MDRRuleGroup>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MDRRULEGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MDRRULEGROUP;

				if (pagination) {
					sql = sql.concat(MDRRuleGroupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MDRRuleGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MDRRuleGroup>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the m d r rule groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MDRRuleGroup mdrRuleGroup : findAll()) {
			remove(mdrRuleGroup);
		}
	}

	/**
	 * Returns the number of m d r rule groups.
	 *
	 * @return the number of m d r rule groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MDRRULEGROUP);

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
		return MDRRuleGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the m d r rule group persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MDRRuleGroupImpl.class.getName());
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
	private static final String _SQL_SELECT_MDRRULEGROUP = "SELECT mdrRuleGroup FROM MDRRuleGroup mdrRuleGroup";
	private static final String _SQL_SELECT_MDRRULEGROUP_WHERE_PKS_IN = "SELECT mdrRuleGroup FROM MDRRuleGroup mdrRuleGroup WHERE ruleGroupId IN (";
	private static final String _SQL_SELECT_MDRRULEGROUP_WHERE = "SELECT mdrRuleGroup FROM MDRRuleGroup mdrRuleGroup WHERE ";
	private static final String _SQL_COUNT_MDRRULEGROUP = "SELECT COUNT(mdrRuleGroup) FROM MDRRuleGroup mdrRuleGroup";
	private static final String _SQL_COUNT_MDRRULEGROUP_WHERE = "SELECT COUNT(mdrRuleGroup) FROM MDRRuleGroup mdrRuleGroup WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "mdrRuleGroup.ruleGroupId";
	private static final String _FILTER_SQL_SELECT_MDRRULEGROUP_WHERE = "SELECT DISTINCT {mdrRuleGroup.*} FROM MDRRuleGroup mdrRuleGroup WHERE ";
	private static final String _FILTER_SQL_SELECT_MDRRULEGROUP_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {MDRRuleGroup.*} FROM (SELECT DISTINCT mdrRuleGroup.ruleGroupId FROM MDRRuleGroup mdrRuleGroup WHERE ";
	private static final String _FILTER_SQL_SELECT_MDRRULEGROUP_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN MDRRuleGroup ON TEMP_TABLE.ruleGroupId = MDRRuleGroup.ruleGroupId";
	private static final String _FILTER_SQL_COUNT_MDRRULEGROUP_WHERE = "SELECT COUNT(DISTINCT mdrRuleGroup.ruleGroupId) AS COUNT_VALUE FROM MDRRuleGroup mdrRuleGroup WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "mdrRuleGroup";
	private static final String _FILTER_ENTITY_TABLE = "MDRRuleGroup";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mdrRuleGroup.";
	private static final String _ORDER_BY_ENTITY_TABLE = "MDRRuleGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MDRRuleGroup exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MDRRuleGroup exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(MDRRuleGroupPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}