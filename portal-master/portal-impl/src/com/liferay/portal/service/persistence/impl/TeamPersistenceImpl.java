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

package com.liferay.portal.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.NoSuchTeamException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.TeamPersistence;
import com.liferay.portal.kernel.service.persistence.UserGroupPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.impl.TeamImpl;
import com.liferay.portal.model.impl.TeamModelImpl;

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
 * The persistence implementation for the team service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamPersistence
 * @see com.liferay.portal.kernel.service.persistence.TeamUtil
 * @generated
 */
@ProviderType
public class TeamPersistenceImpl extends BasePersistenceImpl<Team>
	implements TeamPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TeamUtil} to access the team persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TeamImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, TeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, TeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, TeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, TeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			TeamModelImpl.UUID_COLUMN_BITMASK |
			TeamModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the teams where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching teams
	 */
	@Override
	public List<Team> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the teams where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of matching teams
	 */
	@Override
	public List<Team> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the teams where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teams
	 */
	@Override
	public List<Team> findByUuid(String uuid, int start, int end,
		OrderByComparator<Team> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the teams where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching teams
	 */
	@Override
	public List<Team> findByUuid(String uuid, int start, int end,
		OrderByComparator<Team> orderByComparator, boolean retrieveFromCache) {
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

		List<Team> list = null;

		if (retrieveFromCache) {
			list = (List<Team>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Team team : list) {
					if (!Objects.equals(uuid, team.getUuid())) {
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

			query.append(_SQL_SELECT_TEAM_WHERE);

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
				query.append(TeamModelImpl.ORDER_BY_JPQL);
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
					list = (List<Team>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Team>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first team in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	@Override
	public Team findByUuid_First(String uuid,
		OrderByComparator<Team> orderByComparator) throws NoSuchTeamException {
		Team team = fetchByUuid_First(uuid, orderByComparator);

		if (team != null) {
			return team;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTeamException(msg.toString());
	}

	/**
	 * Returns the first team in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team, or <code>null</code> if a matching team could not be found
	 */
	@Override
	public Team fetchByUuid_First(String uuid,
		OrderByComparator<Team> orderByComparator) {
		List<Team> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last team in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	@Override
	public Team findByUuid_Last(String uuid,
		OrderByComparator<Team> orderByComparator) throws NoSuchTeamException {
		Team team = fetchByUuid_Last(uuid, orderByComparator);

		if (team != null) {
			return team;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTeamException(msg.toString());
	}

	/**
	 * Returns the last team in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team, or <code>null</code> if a matching team could not be found
	 */
	@Override
	public Team fetchByUuid_Last(String uuid,
		OrderByComparator<Team> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Team> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the teams before and after the current team in the ordered set where uuid = &#63;.
	 *
	 * @param teamId the primary key of the current team
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	@Override
	public Team[] findByUuid_PrevAndNext(long teamId, String uuid,
		OrderByComparator<Team> orderByComparator) throws NoSuchTeamException {
		Team team = findByPrimaryKey(teamId);

		Session session = null;

		try {
			session = openSession();

			Team[] array = new TeamImpl[3];

			array[0] = getByUuid_PrevAndNext(session, team, uuid,
					orderByComparator, true);

			array[1] = team;

			array[2] = getByUuid_PrevAndNext(session, team, uuid,
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

	protected Team getByUuid_PrevAndNext(Session session, Team team,
		String uuid, OrderByComparator<Team> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEAM_WHERE);

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
			query.append(TeamModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(team);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Team> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the teams where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Team team : findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(team);
		}
	}

	/**
	 * Returns the number of teams where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching teams
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEAM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "team.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "team.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(team.uuid IS NULL OR team.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, TeamImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			TeamModelImpl.UUID_COLUMN_BITMASK |
			TeamModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the team where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchTeamException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	@Override
	public Team findByUUID_G(String uuid, long groupId)
		throws NoSuchTeamException {
		Team team = fetchByUUID_G(uuid, groupId);

		if (team == null) {
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

			throw new NoSuchTeamException(msg.toString());
		}

		return team;
	}

	/**
	 * Returns the team where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching team, or <code>null</code> if a matching team could not be found
	 */
	@Override
	public Team fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the team where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching team, or <code>null</code> if a matching team could not be found
	 */
	@Override
	public Team fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Team) {
			Team team = (Team)result;

			if (!Objects.equals(uuid, team.getUuid()) ||
					(groupId != team.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TEAM_WHERE);

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

				List<Team> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Team team = list.get(0);

					result = team;

					cacheResult(team);

					if ((team.getUuid() == null) ||
							!team.getUuid().equals(uuid) ||
							(team.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, team);
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
			return (Team)result;
		}
	}

	/**
	 * Removes the team where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the team that was removed
	 */
	@Override
	public Team removeByUUID_G(String uuid, long groupId)
		throws NoSuchTeamException {
		Team team = findByUUID_G(uuid, groupId);

		return remove(team);
	}

	/**
	 * Returns the number of teams where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching teams
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEAM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "team.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "team.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(team.uuid IS NULL OR team.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "team.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, TeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, TeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			TeamModelImpl.UUID_COLUMN_BITMASK |
			TeamModelImpl.COMPANYID_COLUMN_BITMASK |
			TeamModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the teams where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching teams
	 */
	@Override
	public List<Team> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the teams where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of matching teams
	 */
	@Override
	public List<Team> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the teams where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teams
	 */
	@Override
	public List<Team> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Team> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the teams where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching teams
	 */
	@Override
	public List<Team> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Team> orderByComparator,
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

		List<Team> list = null;

		if (retrieveFromCache) {
			list = (List<Team>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Team team : list) {
					if (!Objects.equals(uuid, team.getUuid()) ||
							(companyId != team.getCompanyId())) {
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

			query.append(_SQL_SELECT_TEAM_WHERE);

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
				query.append(TeamModelImpl.ORDER_BY_JPQL);
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
					list = (List<Team>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Team>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	@Override
	public Team findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Team> orderByComparator) throws NoSuchTeamException {
		Team team = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (team != null) {
			return team;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTeamException(msg.toString());
	}

	/**
	 * Returns the first team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team, or <code>null</code> if a matching team could not be found
	 */
	@Override
	public Team fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Team> orderByComparator) {
		List<Team> list = findByUuid_C(uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	@Override
	public Team findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Team> orderByComparator) throws NoSuchTeamException {
		Team team = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (team != null) {
			return team;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTeamException(msg.toString());
	}

	/**
	 * Returns the last team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team, or <code>null</code> if a matching team could not be found
	 */
	@Override
	public Team fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Team> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Team> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the teams before and after the current team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param teamId the primary key of the current team
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	@Override
	public Team[] findByUuid_C_PrevAndNext(long teamId, String uuid,
		long companyId, OrderByComparator<Team> orderByComparator)
		throws NoSuchTeamException {
		Team team = findByPrimaryKey(teamId);

		Session session = null;

		try {
			session = openSession();

			Team[] array = new TeamImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, team, uuid, companyId,
					orderByComparator, true);

			array[1] = team;

			array[2] = getByUuid_C_PrevAndNext(session, team, uuid, companyId,
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

	protected Team getByUuid_C_PrevAndNext(Session session, Team team,
		String uuid, long companyId, OrderByComparator<Team> orderByComparator,
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

		query.append(_SQL_SELECT_TEAM_WHERE);

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
			query.append(TeamModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(team);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Team> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the teams where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Team team : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(team);
		}
	}

	/**
	 * Returns the number of teams where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching teams
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEAM_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "team.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "team.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(team.uuid IS NULL OR team.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "team.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, TeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, TeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			TeamModelImpl.GROUPID_COLUMN_BITMASK |
			TeamModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the teams where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching teams
	 */
	@Override
	public List<Team> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the teams where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of matching teams
	 */
	@Override
	public List<Team> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the teams where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teams
	 */
	@Override
	public List<Team> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Team> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the teams where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching teams
	 */
	@Override
	public List<Team> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Team> orderByComparator, boolean retrieveFromCache) {
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

		List<Team> list = null;

		if (retrieveFromCache) {
			list = (List<Team>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Team team : list) {
					if ((groupId != team.getGroupId())) {
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

			query.append(_SQL_SELECT_TEAM_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TeamModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Team>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Team>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first team in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	@Override
	public Team findByGroupId_First(long groupId,
		OrderByComparator<Team> orderByComparator) throws NoSuchTeamException {
		Team team = fetchByGroupId_First(groupId, orderByComparator);

		if (team != null) {
			return team;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTeamException(msg.toString());
	}

	/**
	 * Returns the first team in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team, or <code>null</code> if a matching team could not be found
	 */
	@Override
	public Team fetchByGroupId_First(long groupId,
		OrderByComparator<Team> orderByComparator) {
		List<Team> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last team in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	@Override
	public Team findByGroupId_Last(long groupId,
		OrderByComparator<Team> orderByComparator) throws NoSuchTeamException {
		Team team = fetchByGroupId_Last(groupId, orderByComparator);

		if (team != null) {
			return team;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTeamException(msg.toString());
	}

	/**
	 * Returns the last team in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team, or <code>null</code> if a matching team could not be found
	 */
	@Override
	public Team fetchByGroupId_Last(long groupId,
		OrderByComparator<Team> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Team> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the teams before and after the current team in the ordered set where groupId = &#63;.
	 *
	 * @param teamId the primary key of the current team
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	@Override
	public Team[] findByGroupId_PrevAndNext(long teamId, long groupId,
		OrderByComparator<Team> orderByComparator) throws NoSuchTeamException {
		Team team = findByPrimaryKey(teamId);

		Session session = null;

		try {
			session = openSession();

			Team[] array = new TeamImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, team, groupId,
					orderByComparator, true);

			array[1] = team;

			array[2] = getByGroupId_PrevAndNext(session, team, groupId,
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

	protected Team getByGroupId_PrevAndNext(Session session, Team team,
		long groupId, OrderByComparator<Team> orderByComparator,
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

		query.append(_SQL_SELECT_TEAM_WHERE);

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
			query.append(TeamModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(team);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Team> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the teams that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching teams that the user has permission to view
	 */
	@Override
	public List<Team> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the teams that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of matching teams that the user has permission to view
	 */
	@Override
	public List<Team> filterFindByGroupId(long groupId, int start, int end) {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the teams that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teams that the user has permission to view
	 */
	@Override
	public List<Team> filterFindByGroupId(long groupId, int start, int end,
		OrderByComparator<Team> orderByComparator) {
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
			query.append(_FILTER_SQL_SELECT_TEAM_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TEAM_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TEAM_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TeamModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TeamModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Team.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TeamImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TeamImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<Team>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the teams before and after the current team in the ordered set of teams that the user has permission to view where groupId = &#63;.
	 *
	 * @param teamId the primary key of the current team
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	@Override
	public Team[] filterFindByGroupId_PrevAndNext(long teamId, long groupId,
		OrderByComparator<Team> orderByComparator) throws NoSuchTeamException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(teamId, groupId, orderByComparator);
		}

		Team team = findByPrimaryKey(teamId);

		Session session = null;

		try {
			session = openSession();

			Team[] array = new TeamImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, team, groupId,
					orderByComparator, true);

			array[1] = team;

			array[2] = filterGetByGroupId_PrevAndNext(session, team, groupId,
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

	protected Team filterGetByGroupId_PrevAndNext(Session session, Team team,
		long groupId, OrderByComparator<Team> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TEAM_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TEAM_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TEAM_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TeamModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TeamModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Team.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TeamImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TeamImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(team);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Team> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the teams where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (Team team : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(team);
		}
	}

	/**
	 * Returns the number of teams where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching teams
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEAM_WHERE);

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
	 * Returns the number of teams that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching teams that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_TEAM_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Team.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "team.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_N = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, TeamImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_N",
			new String[] { Long.class.getName(), String.class.getName() },
			TeamModelImpl.GROUPID_COLUMN_BITMASK |
			TeamModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_N = new FinderPath(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_N",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the team where groupId = &#63; and name = &#63; or throws a {@link NoSuchTeamException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	@Override
	public Team findByG_N(long groupId, String name) throws NoSuchTeamException {
		Team team = fetchByG_N(groupId, name);

		if (team == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTeamException(msg.toString());
		}

		return team;
	}

	/**
	 * Returns the team where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching team, or <code>null</code> if a matching team could not be found
	 */
	@Override
	public Team fetchByG_N(long groupId, String name) {
		return fetchByG_N(groupId, name, true);
	}

	/**
	 * Returns the team where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching team, or <code>null</code> if a matching team could not be found
	 */
	@Override
	public Team fetchByG_N(long groupId, String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_N,
					finderArgs, this);
		}

		if (result instanceof Team) {
			Team team = (Team)result;

			if ((groupId != team.getGroupId()) ||
					!Objects.equals(name, team.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TEAM_WHERE);

			query.append(_FINDER_COLUMN_G_N_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				List<Team> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_N, finderArgs,
						list);
				}
				else {
					Team team = list.get(0);

					result = team;

					cacheResult(team);

					if ((team.getGroupId() != groupId) ||
							(team.getName() == null) ||
							!team.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_G_N,
							finderArgs, team);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_N, finderArgs);

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
			return (Team)result;
		}
	}

	/**
	 * Removes the team where groupId = &#63; and name = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the team that was removed
	 */
	@Override
	public Team removeByG_N(long groupId, String name)
		throws NoSuchTeamException {
		Team team = findByG_N(groupId, name);

		return remove(team);
	}

	/**
	 * Returns the number of teams where groupId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the number of matching teams
	 */
	@Override
	public int countByG_N(long groupId, String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_N;

		Object[] finderArgs = new Object[] { groupId, name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEAM_WHERE);

			query.append(_FINDER_COLUMN_G_N_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
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

	private static final String _FINDER_COLUMN_G_N_GROUPID_2 = "team.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_N_NAME_1 = "team.name IS NULL";
	private static final String _FINDER_COLUMN_G_N_NAME_2 = "team.name = ?";
	private static final String _FINDER_COLUMN_G_N_NAME_3 = "(team.name IS NULL OR team.name = '')";

	public TeamPersistenceImpl() {
		setModelClass(Team.class);
	}

	/**
	 * Caches the team in the entity cache if it is enabled.
	 *
	 * @param team the team
	 */
	@Override
	public void cacheResult(Team team) {
		entityCache.putResult(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamImpl.class, team.getPrimaryKey(), team);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { team.getUuid(), team.getGroupId() }, team);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_N,
			new Object[] { team.getGroupId(), team.getName() }, team);

		team.resetOriginalValues();
	}

	/**
	 * Caches the teams in the entity cache if it is enabled.
	 *
	 * @param teams the teams
	 */
	@Override
	public void cacheResult(List<Team> teams) {
		for (Team team : teams) {
			if (entityCache.getResult(TeamModelImpl.ENTITY_CACHE_ENABLED,
						TeamImpl.class, team.getPrimaryKey()) == null) {
				cacheResult(team);
			}
			else {
				team.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all teams.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TeamImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the team.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Team team) {
		entityCache.removeResult(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamImpl.class, team.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((TeamModelImpl)team);
	}

	@Override
	public void clearCache(List<Team> teams) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Team team : teams) {
			entityCache.removeResult(TeamModelImpl.ENTITY_CACHE_ENABLED,
				TeamImpl.class, team.getPrimaryKey());

			clearUniqueFindersCache((TeamModelImpl)team);
		}
	}

	protected void cacheUniqueFindersCache(TeamModelImpl teamModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					teamModelImpl.getUuid(), teamModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				teamModelImpl);

			args = new Object[] {
					teamModelImpl.getGroupId(), teamModelImpl.getName()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_G_N, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_G_N, args, teamModelImpl);
		}
		else {
			if ((teamModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						teamModelImpl.getUuid(), teamModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					teamModelImpl);
			}

			if ((teamModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_G_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						teamModelImpl.getGroupId(), teamModelImpl.getName()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_G_N, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_G_N, args,
					teamModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(TeamModelImpl teamModelImpl) {
		Object[] args = new Object[] {
				teamModelImpl.getUuid(), teamModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((teamModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					teamModelImpl.getOriginalUuid(),
					teamModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] { teamModelImpl.getGroupId(), teamModelImpl.getName() };

		finderCache.removeResult(FINDER_PATH_COUNT_BY_G_N, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_G_N, args);

		if ((teamModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_N.getColumnBitmask()) != 0) {
			args = new Object[] {
					teamModelImpl.getOriginalGroupId(),
					teamModelImpl.getOriginalName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_N, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_N, args);
		}
	}

	/**
	 * Creates a new team with the primary key. Does not add the team to the database.
	 *
	 * @param teamId the primary key for the new team
	 * @return the new team
	 */
	@Override
	public Team create(long teamId) {
		Team team = new TeamImpl();

		team.setNew(true);
		team.setPrimaryKey(teamId);

		String uuid = PortalUUIDUtil.generate();

		team.setUuid(uuid);

		team.setCompanyId(companyProvider.getCompanyId());

		return team;
	}

	/**
	 * Removes the team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param teamId the primary key of the team
	 * @return the team that was removed
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	@Override
	public Team remove(long teamId) throws NoSuchTeamException {
		return remove((Serializable)teamId);
	}

	/**
	 * Removes the team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the team
	 * @return the team that was removed
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	@Override
	public Team remove(Serializable primaryKey) throws NoSuchTeamException {
		Session session = null;

		try {
			session = openSession();

			Team team = (Team)session.get(TeamImpl.class, primaryKey);

			if (team == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTeamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(team);
		}
		catch (NoSuchTeamException nsee) {
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
	protected Team removeImpl(Team team) {
		team = toUnwrappedModel(team);

		teamToUserTableMapper.deleteLeftPrimaryKeyTableMappings(team.getPrimaryKey());

		teamToUserGroupTableMapper.deleteLeftPrimaryKeyTableMappings(team.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(team)) {
				team = (Team)session.get(TeamImpl.class, team.getPrimaryKeyObj());
			}

			if (team != null) {
				session.delete(team);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (team != null) {
			clearCache(team);
		}

		return team;
	}

	@Override
	public Team updateImpl(Team team) {
		team = toUnwrappedModel(team);

		boolean isNew = team.isNew();

		TeamModelImpl teamModelImpl = (TeamModelImpl)team;

		if (Validator.isNull(team.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			team.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (team.getCreateDate() == null)) {
			if (serviceContext == null) {
				team.setCreateDate(now);
			}
			else {
				team.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!teamModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				team.setModifiedDate(now);
			}
			else {
				team.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (team.isNew()) {
				session.save(team);

				team.setNew(false);
			}
			else {
				team = (Team)session.merge(team);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TeamModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((teamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { teamModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { teamModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((teamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						teamModelImpl.getOriginalUuid(),
						teamModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						teamModelImpl.getUuid(), teamModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((teamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { teamModelImpl.getOriginalGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { teamModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(TeamModelImpl.ENTITY_CACHE_ENABLED,
			TeamImpl.class, team.getPrimaryKey(), team, false);

		clearUniqueFindersCache(teamModelImpl);
		cacheUniqueFindersCache(teamModelImpl, isNew);

		team.resetOriginalValues();

		return team;
	}

	protected Team toUnwrappedModel(Team team) {
		if (team instanceof TeamImpl) {
			return team;
		}

		TeamImpl teamImpl = new TeamImpl();

		teamImpl.setNew(team.isNew());
		teamImpl.setPrimaryKey(team.getPrimaryKey());

		teamImpl.setMvccVersion(team.getMvccVersion());
		teamImpl.setUuid(team.getUuid());
		teamImpl.setTeamId(team.getTeamId());
		teamImpl.setCompanyId(team.getCompanyId());
		teamImpl.setUserId(team.getUserId());
		teamImpl.setUserName(team.getUserName());
		teamImpl.setCreateDate(team.getCreateDate());
		teamImpl.setModifiedDate(team.getModifiedDate());
		teamImpl.setGroupId(team.getGroupId());
		teamImpl.setName(team.getName());
		teamImpl.setDescription(team.getDescription());
		teamImpl.setLastPublishDate(team.getLastPublishDate());

		return teamImpl;
	}

	/**
	 * Returns the team with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the team
	 * @return the team
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	@Override
	public Team findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTeamException {
		Team team = fetchByPrimaryKey(primaryKey);

		if (team == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTeamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return team;
	}

	/**
	 * Returns the team with the primary key or throws a {@link NoSuchTeamException} if it could not be found.
	 *
	 * @param teamId the primary key of the team
	 * @return the team
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	@Override
	public Team findByPrimaryKey(long teamId) throws NoSuchTeamException {
		return findByPrimaryKey((Serializable)teamId);
	}

	/**
	 * Returns the team with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the team
	 * @return the team, or <code>null</code> if a team with the primary key could not be found
	 */
	@Override
	public Team fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TeamModelImpl.ENTITY_CACHE_ENABLED,
				TeamImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Team team = (Team)serializable;

		if (team == null) {
			Session session = null;

			try {
				session = openSession();

				team = (Team)session.get(TeamImpl.class, primaryKey);

				if (team != null) {
					cacheResult(team);
				}
				else {
					entityCache.putResult(TeamModelImpl.ENTITY_CACHE_ENABLED,
						TeamImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TeamModelImpl.ENTITY_CACHE_ENABLED,
					TeamImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return team;
	}

	/**
	 * Returns the team with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param teamId the primary key of the team
	 * @return the team, or <code>null</code> if a team with the primary key could not be found
	 */
	@Override
	public Team fetchByPrimaryKey(long teamId) {
		return fetchByPrimaryKey((Serializable)teamId);
	}

	@Override
	public Map<Serializable, Team> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Team> map = new HashMap<Serializable, Team>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Team team = fetchByPrimaryKey(primaryKey);

			if (team != null) {
				map.put(primaryKey, team);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TeamModelImpl.ENTITY_CACHE_ENABLED,
					TeamImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Team)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TEAM_WHERE_PKS_IN);

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

			for (Team team : (List<Team>)q.list()) {
				map.put(team.getPrimaryKeyObj(), team);

				cacheResult(team);

				uncachedPrimaryKeys.remove(team.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TeamModelImpl.ENTITY_CACHE_ENABLED,
					TeamImpl.class, primaryKey, nullModel);
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
	 * Returns all the teams.
	 *
	 * @return the teams
	 */
	@Override
	public List<Team> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of teams
	 */
	@Override
	public List<Team> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of teams
	 */
	@Override
	public List<Team> findAll(int start, int end,
		OrderByComparator<Team> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of teams
	 */
	@Override
	public List<Team> findAll(int start, int end,
		OrderByComparator<Team> orderByComparator, boolean retrieveFromCache) {
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

		List<Team> list = null;

		if (retrieveFromCache) {
			list = (List<Team>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TEAM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEAM;

				if (pagination) {
					sql = sql.concat(TeamModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Team>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Team>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the teams from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Team team : findAll()) {
			remove(team);
		}
	}

	/**
	 * Returns the number of teams.
	 *
	 * @return the number of teams
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TEAM);

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

	/**
	 * Returns the primaryKeys of users associated with the team.
	 *
	 * @param pk the primary key of the team
	 * @return long[] of the primaryKeys of users associated with the team
	 */
	@Override
	public long[] getUserPrimaryKeys(long pk) {
		long[] pks = teamToUserTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the users associated with the team.
	 *
	 * @param pk the primary key of the team
	 * @return the users associated with the team
	 */
	@Override
	public List<com.liferay.portal.kernel.model.User> getUsers(long pk) {
		return getUsers(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the users associated with the team.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the team
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of users associated with the team
	 */
	@Override
	public List<com.liferay.portal.kernel.model.User> getUsers(long pk,
		int start, int end) {
		return getUsers(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the users associated with the team.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the team
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of users associated with the team
	 */
	@Override
	public List<com.liferay.portal.kernel.model.User> getUsers(long pk,
		int start, int end,
		OrderByComparator<com.liferay.portal.kernel.model.User> orderByComparator) {
		return teamToUserTableMapper.getRightBaseModels(pk, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of users associated with the team.
	 *
	 * @param pk the primary key of the team
	 * @return the number of users associated with the team
	 */
	@Override
	public int getUsersSize(long pk) {
		long[] pks = teamToUserTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the user is associated with the team.
	 *
	 * @param pk the primary key of the team
	 * @param userPK the primary key of the user
	 * @return <code>true</code> if the user is associated with the team; <code>false</code> otherwise
	 */
	@Override
	public boolean containsUser(long pk, long userPK) {
		return teamToUserTableMapper.containsTableMapping(pk, userPK);
	}

	/**
	 * Returns <code>true</code> if the team has any users associated with it.
	 *
	 * @param pk the primary key of the team to check for associations with users
	 * @return <code>true</code> if the team has any users associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsUsers(long pk) {
		if (getUsersSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the team and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userPK the primary key of the user
	 */
	@Override
	public void addUser(long pk, long userPK) {
		Team team = fetchByPrimaryKey(pk);

		if (team == null) {
			teamToUserTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, userPK);
		}
		else {
			teamToUserTableMapper.addTableMapping(team.getCompanyId(), pk,
				userPK);
		}
	}

	/**
	 * Adds an association between the team and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param user the user
	 */
	@Override
	public void addUser(long pk, com.liferay.portal.kernel.model.User user) {
		Team team = fetchByPrimaryKey(pk);

		if (team == null) {
			teamToUserTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, user.getPrimaryKey());
		}
		else {
			teamToUserTableMapper.addTableMapping(team.getCompanyId(), pk,
				user.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the team and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userPKs the primary keys of the users
	 */
	@Override
	public void addUsers(long pk, long[] userPKs) {
		long companyId = 0;

		Team team = fetchByPrimaryKey(pk);

		if (team == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = team.getCompanyId();
		}

		teamToUserTableMapper.addTableMappings(companyId, pk, userPKs);
	}

	/**
	 * Adds an association between the team and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param users the users
	 */
	@Override
	public void addUsers(long pk,
		List<com.liferay.portal.kernel.model.User> users) {
		addUsers(pk,
			ListUtil.toLongArray(users,
				com.liferay.portal.kernel.model.User.USER_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the team and its users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team to clear the associated users from
	 */
	@Override
	public void clearUsers(long pk) {
		teamToUserTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the team and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userPK the primary key of the user
	 */
	@Override
	public void removeUser(long pk, long userPK) {
		teamToUserTableMapper.deleteTableMapping(pk, userPK);
	}

	/**
	 * Removes the association between the team and the user. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param user the user
	 */
	@Override
	public void removeUser(long pk, com.liferay.portal.kernel.model.User user) {
		teamToUserTableMapper.deleteTableMapping(pk, user.getPrimaryKey());
	}

	/**
	 * Removes the association between the team and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userPKs the primary keys of the users
	 */
	@Override
	public void removeUsers(long pk, long[] userPKs) {
		teamToUserTableMapper.deleteTableMappings(pk, userPKs);
	}

	/**
	 * Removes the association between the team and the users. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param users the users
	 */
	@Override
	public void removeUsers(long pk,
		List<com.liferay.portal.kernel.model.User> users) {
		removeUsers(pk,
			ListUtil.toLongArray(users,
				com.liferay.portal.kernel.model.User.USER_ID_ACCESSOR));
	}

	/**
	 * Sets the users associated with the team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userPKs the primary keys of the users to be associated with the team
	 */
	@Override
	public void setUsers(long pk, long[] userPKs) {
		Set<Long> newUserPKsSet = SetUtil.fromArray(userPKs);
		Set<Long> oldUserPKsSet = SetUtil.fromArray(teamToUserTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeUserPKsSet = new HashSet<Long>(oldUserPKsSet);

		removeUserPKsSet.removeAll(newUserPKsSet);

		teamToUserTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeUserPKsSet));

		newUserPKsSet.removeAll(oldUserPKsSet);

		long companyId = 0;

		Team team = fetchByPrimaryKey(pk);

		if (team == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = team.getCompanyId();
		}

		teamToUserTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newUserPKsSet));
	}

	/**
	 * Sets the users associated with the team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param users the users to be associated with the team
	 */
	@Override
	public void setUsers(long pk,
		List<com.liferay.portal.kernel.model.User> users) {
		try {
			long[] userPKs = new long[users.size()];

			for (int i = 0; i < users.size(); i++) {
				com.liferay.portal.kernel.model.User user = users.get(i);

				userPKs[i] = user.getPrimaryKey();
			}

			setUsers(pk, userPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	/**
	 * Returns the primaryKeys of user groups associated with the team.
	 *
	 * @param pk the primary key of the team
	 * @return long[] of the primaryKeys of user groups associated with the team
	 */
	@Override
	public long[] getUserGroupPrimaryKeys(long pk) {
		long[] pks = teamToUserGroupTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the user groups associated with the team.
	 *
	 * @param pk the primary key of the team
	 * @return the user groups associated with the team
	 */
	@Override
	public List<com.liferay.portal.kernel.model.UserGroup> getUserGroups(
		long pk) {
		return getUserGroups(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the user groups associated with the team.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the team
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of user groups associated with the team
	 */
	@Override
	public List<com.liferay.portal.kernel.model.UserGroup> getUserGroups(
		long pk, int start, int end) {
		return getUserGroups(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user groups associated with the team.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the team
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user groups associated with the team
	 */
	@Override
	public List<com.liferay.portal.kernel.model.UserGroup> getUserGroups(
		long pk, int start, int end,
		OrderByComparator<com.liferay.portal.kernel.model.UserGroup> orderByComparator) {
		return teamToUserGroupTableMapper.getRightBaseModels(pk, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of user groups associated with the team.
	 *
	 * @param pk the primary key of the team
	 * @return the number of user groups associated with the team
	 */
	@Override
	public int getUserGroupsSize(long pk) {
		long[] pks = teamToUserGroupTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the user group is associated with the team.
	 *
	 * @param pk the primary key of the team
	 * @param userGroupPK the primary key of the user group
	 * @return <code>true</code> if the user group is associated with the team; <code>false</code> otherwise
	 */
	@Override
	public boolean containsUserGroup(long pk, long userGroupPK) {
		return teamToUserGroupTableMapper.containsTableMapping(pk, userGroupPK);
	}

	/**
	 * Returns <code>true</code> if the team has any user groups associated with it.
	 *
	 * @param pk the primary key of the team to check for associations with user groups
	 * @return <code>true</code> if the team has any user groups associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsUserGroups(long pk) {
		if (getUserGroupsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the team and the user group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userGroupPK the primary key of the user group
	 */
	@Override
	public void addUserGroup(long pk, long userGroupPK) {
		Team team = fetchByPrimaryKey(pk);

		if (team == null) {
			teamToUserGroupTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, userGroupPK);
		}
		else {
			teamToUserGroupTableMapper.addTableMapping(team.getCompanyId(), pk,
				userGroupPK);
		}
	}

	/**
	 * Adds an association between the team and the user group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userGroup the user group
	 */
	@Override
	public void addUserGroup(long pk,
		com.liferay.portal.kernel.model.UserGroup userGroup) {
		Team team = fetchByPrimaryKey(pk);

		if (team == null) {
			teamToUserGroupTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, userGroup.getPrimaryKey());
		}
		else {
			teamToUserGroupTableMapper.addTableMapping(team.getCompanyId(), pk,
				userGroup.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the team and the user groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userGroupPKs the primary keys of the user groups
	 */
	@Override
	public void addUserGroups(long pk, long[] userGroupPKs) {
		long companyId = 0;

		Team team = fetchByPrimaryKey(pk);

		if (team == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = team.getCompanyId();
		}

		teamToUserGroupTableMapper.addTableMappings(companyId, pk, userGroupPKs);
	}

	/**
	 * Adds an association between the team and the user groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userGroups the user groups
	 */
	@Override
	public void addUserGroups(long pk,
		List<com.liferay.portal.kernel.model.UserGroup> userGroups) {
		addUserGroups(pk,
			ListUtil.toLongArray(userGroups,
				com.liferay.portal.kernel.model.UserGroup.USER_GROUP_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the team and its user groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team to clear the associated user groups from
	 */
	@Override
	public void clearUserGroups(long pk) {
		teamToUserGroupTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the team and the user group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userGroupPK the primary key of the user group
	 */
	@Override
	public void removeUserGroup(long pk, long userGroupPK) {
		teamToUserGroupTableMapper.deleteTableMapping(pk, userGroupPK);
	}

	/**
	 * Removes the association between the team and the user group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userGroup the user group
	 */
	@Override
	public void removeUserGroup(long pk,
		com.liferay.portal.kernel.model.UserGroup userGroup) {
		teamToUserGroupTableMapper.deleteTableMapping(pk,
			userGroup.getPrimaryKey());
	}

	/**
	 * Removes the association between the team and the user groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userGroupPKs the primary keys of the user groups
	 */
	@Override
	public void removeUserGroups(long pk, long[] userGroupPKs) {
		teamToUserGroupTableMapper.deleteTableMappings(pk, userGroupPKs);
	}

	/**
	 * Removes the association between the team and the user groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userGroups the user groups
	 */
	@Override
	public void removeUserGroups(long pk,
		List<com.liferay.portal.kernel.model.UserGroup> userGroups) {
		removeUserGroups(pk,
			ListUtil.toLongArray(userGroups,
				com.liferay.portal.kernel.model.UserGroup.USER_GROUP_ID_ACCESSOR));
	}

	/**
	 * Sets the user groups associated with the team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userGroupPKs the primary keys of the user groups to be associated with the team
	 */
	@Override
	public void setUserGroups(long pk, long[] userGroupPKs) {
		Set<Long> newUserGroupPKsSet = SetUtil.fromArray(userGroupPKs);
		Set<Long> oldUserGroupPKsSet = SetUtil.fromArray(teamToUserGroupTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeUserGroupPKsSet = new HashSet<Long>(oldUserGroupPKsSet);

		removeUserGroupPKsSet.removeAll(newUserGroupPKsSet);

		teamToUserGroupTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeUserGroupPKsSet));

		newUserGroupPKsSet.removeAll(oldUserGroupPKsSet);

		long companyId = 0;

		Team team = fetchByPrimaryKey(pk);

		if (team == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = team.getCompanyId();
		}

		teamToUserGroupTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newUserGroupPKsSet));
	}

	/**
	 * Sets the user groups associated with the team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the team
	 * @param userGroups the user groups to be associated with the team
	 */
	@Override
	public void setUserGroups(long pk,
		List<com.liferay.portal.kernel.model.UserGroup> userGroups) {
		try {
			long[] userGroupPKs = new long[userGroups.size()];

			for (int i = 0; i < userGroups.size(); i++) {
				com.liferay.portal.kernel.model.UserGroup userGroup = userGroups.get(i);

				userGroupPKs[i] = userGroup.getPrimaryKey();
			}

			setUserGroups(pk, userGroupPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TeamModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the team persistence.
	 */
	public void afterPropertiesSet() {
		teamToUserTableMapper = TableMapperFactory.getTableMapper("Users_Teams",
				"companyId", "teamId", "userId", this, userPersistence);

		teamToUserGroupTableMapper = TableMapperFactory.getTableMapper("UserGroups_Teams",
				"companyId", "teamId", "userGroupId", this, userGroupPersistence);
	}

	public void destroy() {
		entityCache.removeCache(TeamImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("Users_Teams");
		TableMapperFactory.removeTableMapper("UserGroups_Teams");
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	protected TableMapper<Team, com.liferay.portal.kernel.model.User> teamToUserTableMapper;
	@BeanReference(type = UserGroupPersistence.class)
	protected UserGroupPersistence userGroupPersistence;
	protected TableMapper<Team, com.liferay.portal.kernel.model.UserGroup> teamToUserGroupTableMapper;
	private static final String _SQL_SELECT_TEAM = "SELECT team FROM Team team";
	private static final String _SQL_SELECT_TEAM_WHERE_PKS_IN = "SELECT team FROM Team team WHERE teamId IN (";
	private static final String _SQL_SELECT_TEAM_WHERE = "SELECT team FROM Team team WHERE ";
	private static final String _SQL_COUNT_TEAM = "SELECT COUNT(team) FROM Team team";
	private static final String _SQL_COUNT_TEAM_WHERE = "SELECT COUNT(team) FROM Team team WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "team.teamId";
	private static final String _FILTER_SQL_SELECT_TEAM_WHERE = "SELECT DISTINCT {team.*} FROM Team team WHERE ";
	private static final String _FILTER_SQL_SELECT_TEAM_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {Team.*} FROM (SELECT DISTINCT team.teamId FROM Team team WHERE ";
	private static final String _FILTER_SQL_SELECT_TEAM_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN Team ON TEMP_TABLE.teamId = Team.teamId";
	private static final String _FILTER_SQL_COUNT_TEAM_WHERE = "SELECT COUNT(DISTINCT team.teamId) AS COUNT_VALUE FROM Team team WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "team";
	private static final String _FILTER_ENTITY_TABLE = "Team";
	private static final String _ORDER_BY_ENTITY_ALIAS = "team.";
	private static final String _ORDER_BY_ENTITY_TABLE = "Team.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Team exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Team exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TeamPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}