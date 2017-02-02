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

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.NoSuchResourceActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.service.persistence.ResourceActionPersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.ResourceActionImpl;
import com.liferay.portal.model.impl.ResourceActionModelImpl;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the resource action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceActionPersistence
 * @see com.liferay.portal.kernel.service.persistence.ResourceActionUtil
 * @generated
 */
@ProviderType
public class ResourceActionPersistenceImpl extends BasePersistenceImpl<ResourceAction>
	implements ResourceActionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ResourceActionUtil} to access the resource action persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ResourceActionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
			ResourceActionModelImpl.FINDER_CACHE_ENABLED,
			ResourceActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
			ResourceActionModelImpl.FINDER_CACHE_ENABLED,
			ResourceActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
			ResourceActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
			ResourceActionModelImpl.FINDER_CACHE_ENABLED,
			ResourceActionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
			ResourceActionModelImpl.FINDER_CACHE_ENABLED,
			ResourceActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByName",
			new String[] { String.class.getName() },
			ResourceActionModelImpl.NAME_COLUMN_BITMASK |
			ResourceActionModelImpl.BITWISEVALUE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
			ResourceActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the resource actions where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching resource actions
	 */
	@Override
	public List<ResourceAction> findByName(String name) {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource actions where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of resource actions
	 * @param end the upper bound of the range of resource actions (not inclusive)
	 * @return the range of matching resource actions
	 */
	@Override
	public List<ResourceAction> findByName(String name, int start, int end) {
		return findByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource actions where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of resource actions
	 * @param end the upper bound of the range of resource actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource actions
	 */
	@Override
	public List<ResourceAction> findByName(String name, int start, int end,
		OrderByComparator<ResourceAction> orderByComparator) {
		return findByName(name, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource actions where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of resource actions
	 * @param end the upper bound of the range of resource actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource actions
	 */
	@Override
	public List<ResourceAction> findByName(String name, int start, int end,
		OrderByComparator<ResourceAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name, start, end, orderByComparator };
		}

		List<ResourceAction> list = null;

		if (retrieveFromCache) {
			list = (List<ResourceAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourceAction resourceAction : list) {
					if (!Objects.equals(name, resourceAction.getName())) {
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

			query.append(_SQL_SELECT_RESOURCEACTION_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ResourceActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<ResourceAction>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourceAction>)QueryUtil.list(q,
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
	 * Returns the first resource action in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource action
	 * @throws NoSuchResourceActionException if a matching resource action could not be found
	 */
	@Override
	public ResourceAction findByName_First(String name,
		OrderByComparator<ResourceAction> orderByComparator)
		throws NoSuchResourceActionException {
		ResourceAction resourceAction = fetchByName_First(name,
				orderByComparator);

		if (resourceAction != null) {
			return resourceAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourceActionException(msg.toString());
	}

	/**
	 * Returns the first resource action in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource action, or <code>null</code> if a matching resource action could not be found
	 */
	@Override
	public ResourceAction fetchByName_First(String name,
		OrderByComparator<ResourceAction> orderByComparator) {
		List<ResourceAction> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource action in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource action
	 * @throws NoSuchResourceActionException if a matching resource action could not be found
	 */
	@Override
	public ResourceAction findByName_Last(String name,
		OrderByComparator<ResourceAction> orderByComparator)
		throws NoSuchResourceActionException {
		ResourceAction resourceAction = fetchByName_Last(name, orderByComparator);

		if (resourceAction != null) {
			return resourceAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourceActionException(msg.toString());
	}

	/**
	 * Returns the last resource action in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource action, or <code>null</code> if a matching resource action could not be found
	 */
	@Override
	public ResourceAction fetchByName_Last(String name,
		OrderByComparator<ResourceAction> orderByComparator) {
		int count = countByName(name);

		if (count == 0) {
			return null;
		}

		List<ResourceAction> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource actions before and after the current resource action in the ordered set where name = &#63;.
	 *
	 * @param resourceActionId the primary key of the current resource action
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource action
	 * @throws NoSuchResourceActionException if a resource action with the primary key could not be found
	 */
	@Override
	public ResourceAction[] findByName_PrevAndNext(long resourceActionId,
		String name, OrderByComparator<ResourceAction> orderByComparator)
		throws NoSuchResourceActionException {
		ResourceAction resourceAction = findByPrimaryKey(resourceActionId);

		Session session = null;

		try {
			session = openSession();

			ResourceAction[] array = new ResourceActionImpl[3];

			array[0] = getByName_PrevAndNext(session, resourceAction, name,
					orderByComparator, true);

			array[1] = resourceAction;

			array[2] = getByName_PrevAndNext(session, resourceAction, name,
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

	protected ResourceAction getByName_PrevAndNext(Session session,
		ResourceAction resourceAction, String name,
		OrderByComparator<ResourceAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESOURCEACTION_WHERE);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_NAME_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_NAME_NAME_2);
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
			query.append(ResourceActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resourceAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourceAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the resource actions where name = &#63; from the database.
	 *
	 * @param name the name
	 */
	@Override
	public void removeByName(String name) {
		for (ResourceAction resourceAction : findByName(name,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(resourceAction);
		}
	}

	/**
	 * Returns the number of resource actions where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching resource actions
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RESOURCEACTION_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "resourceAction.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "resourceAction.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(resourceAction.name IS NULL OR resourceAction.name = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_N_A = new FinderPath(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
			ResourceActionModelImpl.FINDER_CACHE_ENABLED,
			ResourceActionImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByN_A",
			new String[] { String.class.getName(), String.class.getName() },
			ResourceActionModelImpl.NAME_COLUMN_BITMASK |
			ResourceActionModelImpl.ACTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_N_A = new FinderPath(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
			ResourceActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByN_A",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns the resource action where name = &#63; and actionId = &#63; or throws a {@link NoSuchResourceActionException} if it could not be found.
	 *
	 * @param name the name
	 * @param actionId the action ID
	 * @return the matching resource action
	 * @throws NoSuchResourceActionException if a matching resource action could not be found
	 */
	@Override
	public ResourceAction findByN_A(String name, String actionId)
		throws NoSuchResourceActionException {
		ResourceAction resourceAction = fetchByN_A(name, actionId);

		if (resourceAction == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(", actionId=");
			msg.append(actionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchResourceActionException(msg.toString());
		}

		return resourceAction;
	}

	/**
	 * Returns the resource action where name = &#63; and actionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @param actionId the action ID
	 * @return the matching resource action, or <code>null</code> if a matching resource action could not be found
	 */
	@Override
	public ResourceAction fetchByN_A(String name, String actionId) {
		return fetchByN_A(name, actionId, true);
	}

	/**
	 * Returns the resource action where name = &#63; and actionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param actionId the action ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching resource action, or <code>null</code> if a matching resource action could not be found
	 */
	@Override
	public ResourceAction fetchByN_A(String name, String actionId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name, actionId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_N_A,
					finderArgs, this);
		}

		if (result instanceof ResourceAction) {
			ResourceAction resourceAction = (ResourceAction)result;

			if (!Objects.equals(name, resourceAction.getName()) ||
					!Objects.equals(actionId, resourceAction.getActionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_RESOURCEACTION_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_N_A_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_N_A_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_N_A_NAME_2);
			}

			boolean bindActionId = false;

			if (actionId == null) {
				query.append(_FINDER_COLUMN_N_A_ACTIONID_1);
			}
			else if (actionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_N_A_ACTIONID_3);
			}
			else {
				bindActionId = true;

				query.append(_FINDER_COLUMN_N_A_ACTIONID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				if (bindActionId) {
					qPos.add(actionId);
				}

				List<ResourceAction> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_N_A, finderArgs,
						list);
				}
				else {
					ResourceAction resourceAction = list.get(0);

					result = resourceAction;

					cacheResult(resourceAction);

					if ((resourceAction.getName() == null) ||
							!resourceAction.getName().equals(name) ||
							(resourceAction.getActionId() == null) ||
							!resourceAction.getActionId().equals(actionId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_N_A,
							finderArgs, resourceAction);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_N_A, finderArgs);

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
			return (ResourceAction)result;
		}
	}

	/**
	 * Removes the resource action where name = &#63; and actionId = &#63; from the database.
	 *
	 * @param name the name
	 * @param actionId the action ID
	 * @return the resource action that was removed
	 */
	@Override
	public ResourceAction removeByN_A(String name, String actionId)
		throws NoSuchResourceActionException {
		ResourceAction resourceAction = findByN_A(name, actionId);

		return remove(resourceAction);
	}

	/**
	 * Returns the number of resource actions where name = &#63; and actionId = &#63;.
	 *
	 * @param name the name
	 * @param actionId the action ID
	 * @return the number of matching resource actions
	 */
	@Override
	public int countByN_A(String name, String actionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_N_A;

		Object[] finderArgs = new Object[] { name, actionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RESOURCEACTION_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_N_A_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_N_A_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_N_A_NAME_2);
			}

			boolean bindActionId = false;

			if (actionId == null) {
				query.append(_FINDER_COLUMN_N_A_ACTIONID_1);
			}
			else if (actionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_N_A_ACTIONID_3);
			}
			else {
				bindActionId = true;

				query.append(_FINDER_COLUMN_N_A_ACTIONID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				if (bindActionId) {
					qPos.add(actionId);
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

	private static final String _FINDER_COLUMN_N_A_NAME_1 = "resourceAction.name IS NULL AND ";
	private static final String _FINDER_COLUMN_N_A_NAME_2 = "resourceAction.name = ? AND ";
	private static final String _FINDER_COLUMN_N_A_NAME_3 = "(resourceAction.name IS NULL OR resourceAction.name = '') AND ";
	private static final String _FINDER_COLUMN_N_A_ACTIONID_1 = "resourceAction.actionId IS NULL";
	private static final String _FINDER_COLUMN_N_A_ACTIONID_2 = "resourceAction.actionId = ?";
	private static final String _FINDER_COLUMN_N_A_ACTIONID_3 = "(resourceAction.actionId IS NULL OR resourceAction.actionId = '')";

	public ResourceActionPersistenceImpl() {
		setModelClass(ResourceAction.class);
	}

	/**
	 * Caches the resource action in the entity cache if it is enabled.
	 *
	 * @param resourceAction the resource action
	 */
	@Override
	public void cacheResult(ResourceAction resourceAction) {
		entityCache.putResult(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
			ResourceActionImpl.class, resourceAction.getPrimaryKey(),
			resourceAction);

		finderCache.putResult(FINDER_PATH_FETCH_BY_N_A,
			new Object[] { resourceAction.getName(), resourceAction.getActionId() },
			resourceAction);

		resourceAction.resetOriginalValues();
	}

	/**
	 * Caches the resource actions in the entity cache if it is enabled.
	 *
	 * @param resourceActions the resource actions
	 */
	@Override
	public void cacheResult(List<ResourceAction> resourceActions) {
		for (ResourceAction resourceAction : resourceActions) {
			if (entityCache.getResult(
						ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
						ResourceActionImpl.class, resourceAction.getPrimaryKey()) == null) {
				cacheResult(resourceAction);
			}
			else {
				resourceAction.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all resource actions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ResourceActionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the resource action.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ResourceAction resourceAction) {
		entityCache.removeResult(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
			ResourceActionImpl.class, resourceAction.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ResourceActionModelImpl)resourceAction);
	}

	@Override
	public void clearCache(List<ResourceAction> resourceActions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ResourceAction resourceAction : resourceActions) {
			entityCache.removeResult(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
				ResourceActionImpl.class, resourceAction.getPrimaryKey());

			clearUniqueFindersCache((ResourceActionModelImpl)resourceAction);
		}
	}

	protected void cacheUniqueFindersCache(
		ResourceActionModelImpl resourceActionModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					resourceActionModelImpl.getName(),
					resourceActionModelImpl.getActionId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_N_A, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_N_A, args,
				resourceActionModelImpl);
		}
		else {
			if ((resourceActionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_N_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourceActionModelImpl.getName(),
						resourceActionModelImpl.getActionId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_N_A, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_N_A, args,
					resourceActionModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		ResourceActionModelImpl resourceActionModelImpl) {
		Object[] args = new Object[] {
				resourceActionModelImpl.getName(),
				resourceActionModelImpl.getActionId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_N_A, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_N_A, args);

		if ((resourceActionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_N_A.getColumnBitmask()) != 0) {
			args = new Object[] {
					resourceActionModelImpl.getOriginalName(),
					resourceActionModelImpl.getOriginalActionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_N_A, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_N_A, args);
		}
	}

	/**
	 * Creates a new resource action with the primary key. Does not add the resource action to the database.
	 *
	 * @param resourceActionId the primary key for the new resource action
	 * @return the new resource action
	 */
	@Override
	public ResourceAction create(long resourceActionId) {
		ResourceAction resourceAction = new ResourceActionImpl();

		resourceAction.setNew(true);
		resourceAction.setPrimaryKey(resourceActionId);

		return resourceAction;
	}

	/**
	 * Removes the resource action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param resourceActionId the primary key of the resource action
	 * @return the resource action that was removed
	 * @throws NoSuchResourceActionException if a resource action with the primary key could not be found
	 */
	@Override
	public ResourceAction remove(long resourceActionId)
		throws NoSuchResourceActionException {
		return remove((Serializable)resourceActionId);
	}

	/**
	 * Removes the resource action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the resource action
	 * @return the resource action that was removed
	 * @throws NoSuchResourceActionException if a resource action with the primary key could not be found
	 */
	@Override
	public ResourceAction remove(Serializable primaryKey)
		throws NoSuchResourceActionException {
		Session session = null;

		try {
			session = openSession();

			ResourceAction resourceAction = (ResourceAction)session.get(ResourceActionImpl.class,
					primaryKey);

			if (resourceAction == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResourceActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(resourceAction);
		}
		catch (NoSuchResourceActionException nsee) {
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
	protected ResourceAction removeImpl(ResourceAction resourceAction) {
		resourceAction = toUnwrappedModel(resourceAction);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(resourceAction)) {
				resourceAction = (ResourceAction)session.get(ResourceActionImpl.class,
						resourceAction.getPrimaryKeyObj());
			}

			if (resourceAction != null) {
				session.delete(resourceAction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (resourceAction != null) {
			clearCache(resourceAction);
		}

		return resourceAction;
	}

	@Override
	public ResourceAction updateImpl(ResourceAction resourceAction) {
		resourceAction = toUnwrappedModel(resourceAction);

		boolean isNew = resourceAction.isNew();

		ResourceActionModelImpl resourceActionModelImpl = (ResourceActionModelImpl)resourceAction;

		Session session = null;

		try {
			session = openSession();

			if (resourceAction.isNew()) {
				session.save(resourceAction);

				resourceAction.setNew(false);
			}
			else {
				resourceAction = (ResourceAction)session.merge(resourceAction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ResourceActionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((resourceActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourceActionModelImpl.getOriginalName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);

				args = new Object[] { resourceActionModelImpl.getName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);
			}
		}

		entityCache.putResult(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
			ResourceActionImpl.class, resourceAction.getPrimaryKey(),
			resourceAction, false);

		clearUniqueFindersCache(resourceActionModelImpl);
		cacheUniqueFindersCache(resourceActionModelImpl, isNew);

		resourceAction.resetOriginalValues();

		return resourceAction;
	}

	protected ResourceAction toUnwrappedModel(ResourceAction resourceAction) {
		if (resourceAction instanceof ResourceActionImpl) {
			return resourceAction;
		}

		ResourceActionImpl resourceActionImpl = new ResourceActionImpl();

		resourceActionImpl.setNew(resourceAction.isNew());
		resourceActionImpl.setPrimaryKey(resourceAction.getPrimaryKey());

		resourceActionImpl.setMvccVersion(resourceAction.getMvccVersion());
		resourceActionImpl.setResourceActionId(resourceAction.getResourceActionId());
		resourceActionImpl.setName(resourceAction.getName());
		resourceActionImpl.setActionId(resourceAction.getActionId());
		resourceActionImpl.setBitwiseValue(resourceAction.getBitwiseValue());

		return resourceActionImpl;
	}

	/**
	 * Returns the resource action with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the resource action
	 * @return the resource action
	 * @throws NoSuchResourceActionException if a resource action with the primary key could not be found
	 */
	@Override
	public ResourceAction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchResourceActionException {
		ResourceAction resourceAction = fetchByPrimaryKey(primaryKey);

		if (resourceAction == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchResourceActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return resourceAction;
	}

	/**
	 * Returns the resource action with the primary key or throws a {@link NoSuchResourceActionException} if it could not be found.
	 *
	 * @param resourceActionId the primary key of the resource action
	 * @return the resource action
	 * @throws NoSuchResourceActionException if a resource action with the primary key could not be found
	 */
	@Override
	public ResourceAction findByPrimaryKey(long resourceActionId)
		throws NoSuchResourceActionException {
		return findByPrimaryKey((Serializable)resourceActionId);
	}

	/**
	 * Returns the resource action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the resource action
	 * @return the resource action, or <code>null</code> if a resource action with the primary key could not be found
	 */
	@Override
	public ResourceAction fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
				ResourceActionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ResourceAction resourceAction = (ResourceAction)serializable;

		if (resourceAction == null) {
			Session session = null;

			try {
				session = openSession();

				resourceAction = (ResourceAction)session.get(ResourceActionImpl.class,
						primaryKey);

				if (resourceAction != null) {
					cacheResult(resourceAction);
				}
				else {
					entityCache.putResult(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
						ResourceActionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
					ResourceActionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return resourceAction;
	}

	/**
	 * Returns the resource action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param resourceActionId the primary key of the resource action
	 * @return the resource action, or <code>null</code> if a resource action with the primary key could not be found
	 */
	@Override
	public ResourceAction fetchByPrimaryKey(long resourceActionId) {
		return fetchByPrimaryKey((Serializable)resourceActionId);
	}

	@Override
	public Map<Serializable, ResourceAction> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ResourceAction> map = new HashMap<Serializable, ResourceAction>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ResourceAction resourceAction = fetchByPrimaryKey(primaryKey);

			if (resourceAction != null) {
				map.put(primaryKey, resourceAction);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
					ResourceActionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ResourceAction)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RESOURCEACTION_WHERE_PKS_IN);

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

			for (ResourceAction resourceAction : (List<ResourceAction>)q.list()) {
				map.put(resourceAction.getPrimaryKeyObj(), resourceAction);

				cacheResult(resourceAction);

				uncachedPrimaryKeys.remove(resourceAction.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ResourceActionModelImpl.ENTITY_CACHE_ENABLED,
					ResourceActionImpl.class, primaryKey, nullModel);
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
	 * Returns all the resource actions.
	 *
	 * @return the resource actions
	 */
	@Override
	public List<ResourceAction> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of resource actions
	 * @param end the upper bound of the range of resource actions (not inclusive)
	 * @return the range of resource actions
	 */
	@Override
	public List<ResourceAction> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of resource actions
	 * @param end the upper bound of the range of resource actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of resource actions
	 */
	@Override
	public List<ResourceAction> findAll(int start, int end,
		OrderByComparator<ResourceAction> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of resource actions
	 * @param end the upper bound of the range of resource actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of resource actions
	 */
	@Override
	public List<ResourceAction> findAll(int start, int end,
		OrderByComparator<ResourceAction> orderByComparator,
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

		List<ResourceAction> list = null;

		if (retrieveFromCache) {
			list = (List<ResourceAction>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RESOURCEACTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RESOURCEACTION;

				if (pagination) {
					sql = sql.concat(ResourceActionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ResourceAction>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourceAction>)QueryUtil.list(q,
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
	 * Removes all the resource actions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ResourceAction resourceAction : findAll()) {
			remove(resourceAction);
		}
	}

	/**
	 * Returns the number of resource actions.
	 *
	 * @return the number of resource actions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RESOURCEACTION);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return ResourceActionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the resource action persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ResourceActionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_RESOURCEACTION = "SELECT resourceAction FROM ResourceAction resourceAction";
	private static final String _SQL_SELECT_RESOURCEACTION_WHERE_PKS_IN = "SELECT resourceAction FROM ResourceAction resourceAction WHERE resourceActionId IN (";
	private static final String _SQL_SELECT_RESOURCEACTION_WHERE = "SELECT resourceAction FROM ResourceAction resourceAction WHERE ";
	private static final String _SQL_COUNT_RESOURCEACTION = "SELECT COUNT(resourceAction) FROM ResourceAction resourceAction";
	private static final String _SQL_COUNT_RESOURCEACTION_WHERE = "SELECT COUNT(resourceAction) FROM ResourceAction resourceAction WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "resourceAction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ResourceAction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ResourceAction exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ResourceActionPersistenceImpl.class);
}