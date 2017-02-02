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
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.NoSuchResourcePermissionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.ResourcePermissionPersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.impl.ResourcePermissionImpl;
import com.liferay.portal.model.impl.ResourcePermissionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the resource permission service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourcePermissionPersistence
 * @see com.liferay.portal.kernel.service.persistence.ResourcePermissionUtil
 * @generated
 */
@ProviderType
public class ResourcePermissionPersistenceImpl extends BasePersistenceImpl<ResourcePermission>
	implements ResourcePermissionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ResourcePermissionUtil} to access the resource permission persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ResourcePermissionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByName",
			new String[] { String.class.getName() },
			ResourcePermissionModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the resource permissions where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByName(String name) {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource permissions where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @return the range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByName(String name, int start, int end) {
		return findByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource permissions where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByName(String name, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return findByName(name, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource permissions where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByName(String name, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
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

		List<ResourcePermission> list = null;

		if (retrieveFromCache) {
			list = (List<ResourcePermission>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourcePermission resourcePermission : list) {
					if (!Objects.equals(name, resourcePermission.getName())) {
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

			query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

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
				query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
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
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourcePermission>)QueryUtil.list(q,
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
	 * Returns the first resource permission in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByName_First(String name,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByName_First(name,
				orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the first resource permission in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByName_First(String name,
		OrderByComparator<ResourcePermission> orderByComparator) {
		List<ResourcePermission> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource permission in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByName_Last(String name,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByName_Last(name,
				orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the last resource permission in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByName_Last(String name,
		OrderByComparator<ResourcePermission> orderByComparator) {
		int count = countByName(name);

		if (count == 0) {
			return null;
		}

		List<ResourcePermission> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource permissions before and after the current resource permission in the ordered set where name = &#63;.
	 *
	 * @param resourcePermissionId the primary key of the current resource permission
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource permission
	 * @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission[] findByName_PrevAndNext(
		long resourcePermissionId, String name,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = findByPrimaryKey(resourcePermissionId);

		Session session = null;

		try {
			session = openSession();

			ResourcePermission[] array = new ResourcePermissionImpl[3];

			array[0] = getByName_PrevAndNext(session, resourcePermission, name,
					orderByComparator, true);

			array[1] = resourcePermission;

			array[2] = getByName_PrevAndNext(session, resourcePermission, name,
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

	protected ResourcePermission getByName_PrevAndNext(Session session,
		ResourcePermission resourcePermission, String name,
		OrderByComparator<ResourcePermission> orderByComparator,
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

		query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

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
			query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(resourcePermission);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourcePermission> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the resource permissions where name = &#63; from the database.
	 *
	 * @param name the name
	 */
	@Override
	public void removeByName(String name) {
		for (ResourcePermission resourcePermission : findByName(name,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(resourcePermission);
		}
	}

	/**
	 * Returns the number of resource permissions where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching resource permissions
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RESOURCEPERMISSION_WHERE);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "resourcePermission.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "resourcePermission.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(resourcePermission.name IS NULL OR resourcePermission.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCOPE = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByScope",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCOPE = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByScope",
			new String[] { Integer.class.getName() },
			ResourcePermissionModelImpl.SCOPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCOPE = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByScope",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_SCOPE = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByScope",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the resource permissions where scope = &#63;.
	 *
	 * @param scope the scope
	 * @return the matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByScope(int scope) {
		return findByScope(scope, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource permissions where scope = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scope the scope
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @return the range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByScope(int scope, int start, int end) {
		return findByScope(scope, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource permissions where scope = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scope the scope
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByScope(int scope, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return findByScope(scope, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource permissions where scope = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scope the scope
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByScope(int scope, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCOPE;
			finderArgs = new Object[] { scope };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCOPE;
			finderArgs = new Object[] { scope, start, end, orderByComparator };
		}

		List<ResourcePermission> list = null;

		if (retrieveFromCache) {
			list = (List<ResourcePermission>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourcePermission resourcePermission : list) {
					if ((scope != resourcePermission.getScope())) {
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

			query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_SCOPE_SCOPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scope);

				if (!pagination) {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourcePermission>)QueryUtil.list(q,
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
	 * Returns the first resource permission in the ordered set where scope = &#63;.
	 *
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByScope_First(int scope,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByScope_First(scope,
				orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scope=");
		msg.append(scope);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the first resource permission in the ordered set where scope = &#63;.
	 *
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByScope_First(int scope,
		OrderByComparator<ResourcePermission> orderByComparator) {
		List<ResourcePermission> list = findByScope(scope, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource permission in the ordered set where scope = &#63;.
	 *
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByScope_Last(int scope,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByScope_Last(scope,
				orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scope=");
		msg.append(scope);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the last resource permission in the ordered set where scope = &#63;.
	 *
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByScope_Last(int scope,
		OrderByComparator<ResourcePermission> orderByComparator) {
		int count = countByScope(scope);

		if (count == 0) {
			return null;
		}

		List<ResourcePermission> list = findByScope(scope, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource permissions before and after the current resource permission in the ordered set where scope = &#63;.
	 *
	 * @param resourcePermissionId the primary key of the current resource permission
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource permission
	 * @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission[] findByScope_PrevAndNext(
		long resourcePermissionId, int scope,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = findByPrimaryKey(resourcePermissionId);

		Session session = null;

		try {
			session = openSession();

			ResourcePermission[] array = new ResourcePermissionImpl[3];

			array[0] = getByScope_PrevAndNext(session, resourcePermission,
					scope, orderByComparator, true);

			array[1] = resourcePermission;

			array[2] = getByScope_PrevAndNext(session, resourcePermission,
					scope, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ResourcePermission getByScope_PrevAndNext(Session session,
		ResourcePermission resourcePermission, int scope,
		OrderByComparator<ResourcePermission> orderByComparator,
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

		query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

		query.append(_FINDER_COLUMN_SCOPE_SCOPE_2);

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
			query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(scope);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resourcePermission);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourcePermission> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the resource permissions where scope = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scopes the scopes
	 * @return the matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByScope(int[] scopes) {
		return findByScope(scopes, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource permissions where scope = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scopes the scopes
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @return the range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByScope(int[] scopes, int start, int end) {
		return findByScope(scopes, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource permissions where scope = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scopes the scopes
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByScope(int[] scopes, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator) {
		return findByScope(scopes, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource permissions where scope = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scope the scope
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByScope(int[] scopes, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		if (scopes == null) {
			scopes = new int[0];
		}
		else if (scopes.length > 1) {
			scopes = ArrayUtil.unique(scopes);

			Arrays.sort(scopes);
		}

		if (scopes.length == 1) {
			return findByScope(scopes[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(scopes) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(scopes),
					
					start, end, orderByComparator
				};
		}

		List<ResourcePermission> list = null;

		if (retrieveFromCache) {
			list = (List<ResourcePermission>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_SCOPE,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourcePermission resourcePermission : list) {
					if (!ArrayUtil.contains(scopes,
								resourcePermission.getScope())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

			if (scopes.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_SCOPE_SCOPE_7);

				query.append(StringUtil.merge(scopes));

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
				query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_SCOPE,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_SCOPE,
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
	 * Removes all the resource permissions where scope = &#63; from the database.
	 *
	 * @param scope the scope
	 */
	@Override
	public void removeByScope(int scope) {
		for (ResourcePermission resourcePermission : findByScope(scope,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(resourcePermission);
		}
	}

	/**
	 * Returns the number of resource permissions where scope = &#63;.
	 *
	 * @param scope the scope
	 * @return the number of matching resource permissions
	 */
	@Override
	public int countByScope(int scope) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCOPE;

		Object[] finderArgs = new Object[] { scope };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_SCOPE_SCOPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scope);

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
	 * Returns the number of resource permissions where scope = any &#63;.
	 *
	 * @param scopes the scopes
	 * @return the number of matching resource permissions
	 */
	@Override
	public int countByScope(int[] scopes) {
		if (scopes == null) {
			scopes = new int[0];
		}
		else if (scopes.length > 1) {
			scopes = ArrayUtil.unique(scopes);

			Arrays.sort(scopes);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(scopes) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_SCOPE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_RESOURCEPERMISSION_WHERE);

			if (scopes.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_SCOPE_SCOPE_7);

				query.append(StringUtil.merge(scopes));

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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_SCOPE,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_SCOPE,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SCOPE_SCOPE_2 = "resourcePermission.scope = ?";
	private static final String _FINDER_COLUMN_SCOPE_SCOPE_7 = "resourcePermission.scope IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ROLEID = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRoleId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ROLEID =
		new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRoleId",
			new String[] { Long.class.getName() },
			ResourcePermissionModelImpl.ROLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ROLEID = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRoleId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the resource permissions where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @return the matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByRoleId(long roleId) {
		return findByRoleId(roleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource permissions where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @return the range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByRoleId(long roleId, int start, int end) {
		return findByRoleId(roleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource permissions where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByRoleId(long roleId, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator) {
		return findByRoleId(roleId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource permissions where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByRoleId(long roleId, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ROLEID;
			finderArgs = new Object[] { roleId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ROLEID;
			finderArgs = new Object[] { roleId, start, end, orderByComparator };
		}

		List<ResourcePermission> list = null;

		if (retrieveFromCache) {
			list = (List<ResourcePermission>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourcePermission resourcePermission : list) {
					if ((roleId != resourcePermission.getRoleId())) {
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

			query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_ROLEID_ROLEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(roleId);

				if (!pagination) {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourcePermission>)QueryUtil.list(q,
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
	 * Returns the first resource permission in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByRoleId_First(long roleId,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByRoleId_First(roleId,
				orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("roleId=");
		msg.append(roleId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the first resource permission in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByRoleId_First(long roleId,
		OrderByComparator<ResourcePermission> orderByComparator) {
		List<ResourcePermission> list = findByRoleId(roleId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource permission in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByRoleId_Last(long roleId,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByRoleId_Last(roleId,
				orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("roleId=");
		msg.append(roleId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the last resource permission in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByRoleId_Last(long roleId,
		OrderByComparator<ResourcePermission> orderByComparator) {
		int count = countByRoleId(roleId);

		if (count == 0) {
			return null;
		}

		List<ResourcePermission> list = findByRoleId(roleId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource permissions before and after the current resource permission in the ordered set where roleId = &#63;.
	 *
	 * @param resourcePermissionId the primary key of the current resource permission
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource permission
	 * @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission[] findByRoleId_PrevAndNext(
		long resourcePermissionId, long roleId,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = findByPrimaryKey(resourcePermissionId);

		Session session = null;

		try {
			session = openSession();

			ResourcePermission[] array = new ResourcePermissionImpl[3];

			array[0] = getByRoleId_PrevAndNext(session, resourcePermission,
					roleId, orderByComparator, true);

			array[1] = resourcePermission;

			array[2] = getByRoleId_PrevAndNext(session, resourcePermission,
					roleId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ResourcePermission getByRoleId_PrevAndNext(Session session,
		ResourcePermission resourcePermission, long roleId,
		OrderByComparator<ResourcePermission> orderByComparator,
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

		query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

		query.append(_FINDER_COLUMN_ROLEID_ROLEID_2);

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
			query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(roleId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resourcePermission);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourcePermission> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the resource permissions where roleId = &#63; from the database.
	 *
	 * @param roleId the role ID
	 */
	@Override
	public void removeByRoleId(long roleId) {
		for (ResourcePermission resourcePermission : findByRoleId(roleId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(resourcePermission);
		}
	}

	/**
	 * Returns the number of resource permissions where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @return the number of matching resource permissions
	 */
	@Override
	public int countByRoleId(long roleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ROLEID;

		Object[] finderArgs = new Object[] { roleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_ROLEID_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(roleId);

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

	private static final String _FINDER_COLUMN_ROLEID_ROLEID_2 = "resourcePermission.roleId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_LIKEP = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_LikeP",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_LIKEP = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_LikeP",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the resource permissions where companyId = &#63; and primKey LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param primKey the prim key
	 * @return the matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_LikeP(long companyId, String primKey) {
		return findByC_LikeP(companyId, primKey, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource permissions where companyId = &#63; and primKey LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param primKey the prim key
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @return the range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_LikeP(long companyId,
		String primKey, int start, int end) {
		return findByC_LikeP(companyId, primKey, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and primKey LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param primKey the prim key
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_LikeP(long companyId,
		String primKey, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return findByC_LikeP(companyId, primKey, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and primKey LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param primKey the prim key
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_LikeP(long companyId,
		String primKey, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_LIKEP;
		finderArgs = new Object[] {
				companyId, primKey,
				
				start, end, orderByComparator
			};

		List<ResourcePermission> list = null;

		if (retrieveFromCache) {
			list = (List<ResourcePermission>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourcePermission resourcePermission : list) {
					if ((companyId != resourcePermission.getCompanyId()) ||
							!StringUtil.wildcardMatches(
								resourcePermission.getPrimKey(), primKey,
								CharPool.UNDERLINE, CharPool.PERCENT,
								CharPool.BACK_SLASH, true)) {
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

			query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_LIKEP_COMPANYID_2);

			boolean bindPrimKey = false;

			if (primKey == null) {
				query.append(_FINDER_COLUMN_C_LIKEP_PRIMKEY_1);
			}
			else if (primKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_LIKEP_PRIMKEY_3);
			}
			else {
				bindPrimKey = true;

				query.append(_FINDER_COLUMN_C_LIKEP_PRIMKEY_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindPrimKey) {
					qPos.add(primKey);
				}

				if (!pagination) {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourcePermission>)QueryUtil.list(q,
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
	 * Returns the first resource permission in the ordered set where companyId = &#63; and primKey LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByC_LikeP_First(long companyId,
		String primKey, OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByC_LikeP_First(companyId,
				primKey, orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", primKey=");
		msg.append(primKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the first resource permission in the ordered set where companyId = &#63; and primKey LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByC_LikeP_First(long companyId,
		String primKey, OrderByComparator<ResourcePermission> orderByComparator) {
		List<ResourcePermission> list = findByC_LikeP(companyId, primKey, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource permission in the ordered set where companyId = &#63; and primKey LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByC_LikeP_Last(long companyId,
		String primKey, OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByC_LikeP_Last(companyId,
				primKey, orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", primKey=");
		msg.append(primKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the last resource permission in the ordered set where companyId = &#63; and primKey LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByC_LikeP_Last(long companyId,
		String primKey, OrderByComparator<ResourcePermission> orderByComparator) {
		int count = countByC_LikeP(companyId, primKey);

		if (count == 0) {
			return null;
		}

		List<ResourcePermission> list = findByC_LikeP(companyId, primKey,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource permissions before and after the current resource permission in the ordered set where companyId = &#63; and primKey LIKE &#63;.
	 *
	 * @param resourcePermissionId the primary key of the current resource permission
	 * @param companyId the company ID
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource permission
	 * @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission[] findByC_LikeP_PrevAndNext(
		long resourcePermissionId, long companyId, String primKey,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = findByPrimaryKey(resourcePermissionId);

		Session session = null;

		try {
			session = openSession();

			ResourcePermission[] array = new ResourcePermissionImpl[3];

			array[0] = getByC_LikeP_PrevAndNext(session, resourcePermission,
					companyId, primKey, orderByComparator, true);

			array[1] = resourcePermission;

			array[2] = getByC_LikeP_PrevAndNext(session, resourcePermission,
					companyId, primKey, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ResourcePermission getByC_LikeP_PrevAndNext(Session session,
		ResourcePermission resourcePermission, long companyId, String primKey,
		OrderByComparator<ResourcePermission> orderByComparator,
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

		query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

		query.append(_FINDER_COLUMN_C_LIKEP_COMPANYID_2);

		boolean bindPrimKey = false;

		if (primKey == null) {
			query.append(_FINDER_COLUMN_C_LIKEP_PRIMKEY_1);
		}
		else if (primKey.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_LIKEP_PRIMKEY_3);
		}
		else {
			bindPrimKey = true;

			query.append(_FINDER_COLUMN_C_LIKEP_PRIMKEY_2);
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
			query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindPrimKey) {
			qPos.add(primKey);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resourcePermission);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourcePermission> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the resource permissions where companyId = &#63; and primKey LIKE &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param primKey the prim key
	 */
	@Override
	public void removeByC_LikeP(long companyId, String primKey) {
		for (ResourcePermission resourcePermission : findByC_LikeP(companyId,
				primKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(resourcePermission);
		}
	}

	/**
	 * Returns the number of resource permissions where companyId = &#63; and primKey LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param primKey the prim key
	 * @return the number of matching resource permissions
	 */
	@Override
	public int countByC_LikeP(long companyId, String primKey) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_LIKEP;

		Object[] finderArgs = new Object[] { companyId, primKey };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_LIKEP_COMPANYID_2);

			boolean bindPrimKey = false;

			if (primKey == null) {
				query.append(_FINDER_COLUMN_C_LIKEP_PRIMKEY_1);
			}
			else if (primKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_LIKEP_PRIMKEY_3);
			}
			else {
				bindPrimKey = true;

				query.append(_FINDER_COLUMN_C_LIKEP_PRIMKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindPrimKey) {
					qPos.add(primKey);
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

	private static final String _FINDER_COLUMN_C_LIKEP_COMPANYID_2 = "resourcePermission.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_LIKEP_PRIMKEY_1 = "resourcePermission.primKey IS NULL";
	private static final String _FINDER_COLUMN_C_LIKEP_PRIMKEY_2 = "resourcePermission.primKey LIKE ?";
	private static final String _FINDER_COLUMN_C_LIKEP_PRIMKEY_3 = "(resourcePermission.primKey IS NULL OR resourcePermission.primKey LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_S = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_N_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_N_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			ResourcePermissionModelImpl.COMPANYID_COLUMN_BITMASK |
			ResourcePermissionModelImpl.NAME_COLUMN_BITMASK |
			ResourcePermissionModelImpl.SCOPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N_S = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @return the matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S(long companyId, String name,
		int scope) {
		return findByC_N_S(companyId, name, scope, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @return the range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S(long companyId, String name,
		int scope, int start, int end) {
		return findByC_N_S(companyId, name, scope, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S(long companyId, String name,
		int scope, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return findByC_N_S(companyId, name, scope, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S(long companyId, String name,
		int scope, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S;
			finderArgs = new Object[] { companyId, name, scope };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_S;
			finderArgs = new Object[] {
					companyId, name, scope,
					
					start, end, orderByComparator
				};
		}

		List<ResourcePermission> list = null;

		if (retrieveFromCache) {
			list = (List<ResourcePermission>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourcePermission resourcePermission : list) {
					if ((companyId != resourcePermission.getCompanyId()) ||
							!Objects.equals(name, resourcePermission.getName()) ||
							(scope != resourcePermission.getScope())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_N_S_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_S_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_S_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_SCOPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(scope);

				if (!pagination) {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourcePermission>)QueryUtil.list(q,
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
	 * Returns the first resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByC_N_S_First(long companyId, String name,
		int scope, OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByC_N_S_First(companyId,
				name, scope, orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", scope=");
		msg.append(scope);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the first resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByC_N_S_First(long companyId, String name,
		int scope, OrderByComparator<ResourcePermission> orderByComparator) {
		List<ResourcePermission> list = findByC_N_S(companyId, name, scope, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByC_N_S_Last(long companyId, String name,
		int scope, OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByC_N_S_Last(companyId,
				name, scope, orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", scope=");
		msg.append(scope);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the last resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByC_N_S_Last(long companyId, String name,
		int scope, OrderByComparator<ResourcePermission> orderByComparator) {
		int count = countByC_N_S(companyId, name, scope);

		if (count == 0) {
			return null;
		}

		List<ResourcePermission> list = findByC_N_S(companyId, name, scope,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource permissions before and after the current resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63;.
	 *
	 * @param resourcePermissionId the primary key of the current resource permission
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource permission
	 * @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission[] findByC_N_S_PrevAndNext(
		long resourcePermissionId, long companyId, String name, int scope,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = findByPrimaryKey(resourcePermissionId);

		Session session = null;

		try {
			session = openSession();

			ResourcePermission[] array = new ResourcePermissionImpl[3];

			array[0] = getByC_N_S_PrevAndNext(session, resourcePermission,
					companyId, name, scope, orderByComparator, true);

			array[1] = resourcePermission;

			array[2] = getByC_N_S_PrevAndNext(session, resourcePermission,
					companyId, name, scope, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ResourcePermission getByC_N_S_PrevAndNext(Session session,
		ResourcePermission resourcePermission, long companyId, String name,
		int scope, OrderByComparator<ResourcePermission> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

		query.append(_FINDER_COLUMN_C_N_S_COMPANYID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_C_N_S_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_N_S_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_C_N_S_NAME_2);
		}

		query.append(_FINDER_COLUMN_C_N_S_SCOPE_2);

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
			query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(scope);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resourcePermission);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourcePermission> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 */
	@Override
	public void removeByC_N_S(long companyId, String name, int scope) {
		for (ResourcePermission resourcePermission : findByC_N_S(companyId,
				name, scope, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(resourcePermission);
		}
	}

	/**
	 * Returns the number of resource permissions where companyId = &#63; and name = &#63; and scope = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @return the number of matching resource permissions
	 */
	@Override
	public int countByC_N_S(long companyId, String name, int scope) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_N_S;

		Object[] finderArgs = new Object[] { companyId, name, scope };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_N_S_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_S_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_S_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_SCOPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(scope);

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

	private static final String _FINDER_COLUMN_C_N_S_COMPANYID_2 = "resourcePermission.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_NAME_1 = "resourcePermission.name IS NULL AND ";
	private static final String _FINDER_COLUMN_C_N_S_NAME_2 = "resourcePermission.name = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_NAME_3 = "(resourcePermission.name IS NULL OR resourcePermission.name = '') AND ";
	private static final String _FINDER_COLUMN_C_N_S_SCOPE_2 = "resourcePermission.scope = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S_P = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_S_P",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_P = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_S_P",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName()
			},
			ResourcePermissionModelImpl.COMPANYID_COLUMN_BITMASK |
			ResourcePermissionModelImpl.SCOPE_COLUMN_BITMASK |
			ResourcePermissionModelImpl.PRIMKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_S_P = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S_P",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the resource permissions where companyId = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param primKey the prim key
	 * @return the matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_S_P(long companyId, int scope,
		String primKey) {
		return findByC_S_P(companyId, scope, primKey, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource permissions where companyId = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @return the range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_S_P(long companyId, int scope,
		String primKey, int start, int end) {
		return findByC_S_P(companyId, scope, primKey, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_S_P(long companyId, int scope,
		String primKey, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return findByC_S_P(companyId, scope, primKey, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_S_P(long companyId, int scope,
		String primKey, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_P;
			finderArgs = new Object[] { companyId, scope, primKey };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S_P;
			finderArgs = new Object[] {
					companyId, scope, primKey,
					
					start, end, orderByComparator
				};
		}

		List<ResourcePermission> list = null;

		if (retrieveFromCache) {
			list = (List<ResourcePermission>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourcePermission resourcePermission : list) {
					if ((companyId != resourcePermission.getCompanyId()) ||
							(scope != resourcePermission.getScope()) ||
							!Objects.equals(primKey,
								resourcePermission.getPrimKey())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_S_P_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_S_P_SCOPE_2);

			boolean bindPrimKey = false;

			if (primKey == null) {
				query.append(_FINDER_COLUMN_C_S_P_PRIMKEY_1);
			}
			else if (primKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_S_P_PRIMKEY_3);
			}
			else {
				bindPrimKey = true;

				query.append(_FINDER_COLUMN_C_S_P_PRIMKEY_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(scope);

				if (bindPrimKey) {
					qPos.add(primKey);
				}

				if (!pagination) {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourcePermission>)QueryUtil.list(q,
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
	 * Returns the first resource permission in the ordered set where companyId = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByC_S_P_First(long companyId, int scope,
		String primKey, OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByC_S_P_First(companyId,
				scope, primKey, orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", scope=");
		msg.append(scope);

		msg.append(", primKey=");
		msg.append(primKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the first resource permission in the ordered set where companyId = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByC_S_P_First(long companyId, int scope,
		String primKey, OrderByComparator<ResourcePermission> orderByComparator) {
		List<ResourcePermission> list = findByC_S_P(companyId, scope, primKey,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource permission in the ordered set where companyId = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByC_S_P_Last(long companyId, int scope,
		String primKey, OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByC_S_P_Last(companyId,
				scope, primKey, orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", scope=");
		msg.append(scope);

		msg.append(", primKey=");
		msg.append(primKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the last resource permission in the ordered set where companyId = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByC_S_P_Last(long companyId, int scope,
		String primKey, OrderByComparator<ResourcePermission> orderByComparator) {
		int count = countByC_S_P(companyId, scope, primKey);

		if (count == 0) {
			return null;
		}

		List<ResourcePermission> list = findByC_S_P(companyId, scope, primKey,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource permissions before and after the current resource permission in the ordered set where companyId = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param resourcePermissionId the primary key of the current resource permission
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource permission
	 * @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission[] findByC_S_P_PrevAndNext(
		long resourcePermissionId, long companyId, int scope, String primKey,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = findByPrimaryKey(resourcePermissionId);

		Session session = null;

		try {
			session = openSession();

			ResourcePermission[] array = new ResourcePermissionImpl[3];

			array[0] = getByC_S_P_PrevAndNext(session, resourcePermission,
					companyId, scope, primKey, orderByComparator, true);

			array[1] = resourcePermission;

			array[2] = getByC_S_P_PrevAndNext(session, resourcePermission,
					companyId, scope, primKey, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ResourcePermission getByC_S_P_PrevAndNext(Session session,
		ResourcePermission resourcePermission, long companyId, int scope,
		String primKey,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

		query.append(_FINDER_COLUMN_C_S_P_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_S_P_SCOPE_2);

		boolean bindPrimKey = false;

		if (primKey == null) {
			query.append(_FINDER_COLUMN_C_S_P_PRIMKEY_1);
		}
		else if (primKey.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_S_P_PRIMKEY_3);
		}
		else {
			bindPrimKey = true;

			query.append(_FINDER_COLUMN_C_S_P_PRIMKEY_2);
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
			query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(scope);

		if (bindPrimKey) {
			qPos.add(primKey);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resourcePermission);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourcePermission> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the resource permissions where companyId = &#63; and scope = &#63; and primKey = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param primKey the prim key
	 */
	@Override
	public void removeByC_S_P(long companyId, int scope, String primKey) {
		for (ResourcePermission resourcePermission : findByC_S_P(companyId,
				scope, primKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(resourcePermission);
		}
	}

	/**
	 * Returns the number of resource permissions where companyId = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param companyId the company ID
	 * @param scope the scope
	 * @param primKey the prim key
	 * @return the number of matching resource permissions
	 */
	@Override
	public int countByC_S_P(long companyId, int scope, String primKey) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_S_P;

		Object[] finderArgs = new Object[] { companyId, scope, primKey };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_S_P_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_S_P_SCOPE_2);

			boolean bindPrimKey = false;

			if (primKey == null) {
				query.append(_FINDER_COLUMN_C_S_P_PRIMKEY_1);
			}
			else if (primKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_S_P_PRIMKEY_3);
			}
			else {
				bindPrimKey = true;

				query.append(_FINDER_COLUMN_C_S_P_PRIMKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(scope);

				if (bindPrimKey) {
					qPos.add(primKey);
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

	private static final String _FINDER_COLUMN_C_S_P_COMPANYID_2 = "resourcePermission.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_S_P_SCOPE_2 = "resourcePermission.scope = ? AND ";
	private static final String _FINDER_COLUMN_C_S_P_PRIMKEY_1 = "resourcePermission.primKey IS NULL";
	private static final String _FINDER_COLUMN_C_S_P_PRIMKEY_2 = "resourcePermission.primKey = ?";
	private static final String _FINDER_COLUMN_C_S_P_PRIMKEY_3 = "(resourcePermission.primKey IS NULL OR resourcePermission.primKey = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_S_P = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_N_S_P",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P =
		new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_N_S_P",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName()
			},
			ResourcePermissionModelImpl.COMPANYID_COLUMN_BITMASK |
			ResourcePermissionModelImpl.NAME_COLUMN_BITMASK |
			ResourcePermissionModelImpl.SCOPE_COLUMN_BITMASK |
			ResourcePermissionModelImpl.PRIMKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N_S_P = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N_S_P",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName()
			});

	/**
	 * Returns all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @return the matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P(long companyId, String name,
		int scope, String primKey) {
		return findByC_N_S_P(companyId, name, scope, primKey,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @return the range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P(long companyId, String name,
		int scope, String primKey, int start, int end) {
		return findByC_N_S_P(companyId, name, scope, primKey, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P(long companyId, String name,
		int scope, String primKey, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return findByC_N_S_P(companyId, name, scope, primKey, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P(long companyId, String name,
		int scope, String primKey, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P;
			finderArgs = new Object[] { companyId, name, scope, primKey };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_S_P;
			finderArgs = new Object[] {
					companyId, name, scope, primKey,
					
					start, end, orderByComparator
				};
		}

		List<ResourcePermission> list = null;

		if (retrieveFromCache) {
			list = (List<ResourcePermission>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourcePermission resourcePermission : list) {
					if ((companyId != resourcePermission.getCompanyId()) ||
							!Objects.equals(name, resourcePermission.getName()) ||
							(scope != resourcePermission.getScope()) ||
							!Objects.equals(primKey,
								resourcePermission.getPrimKey())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_N_S_P_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_S_P_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_SCOPE_2);

			boolean bindPrimKey = false;

			if (primKey == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_PRIMKEY_1);
			}
			else if (primKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_PRIMKEY_3);
			}
			else {
				bindPrimKey = true;

				query.append(_FINDER_COLUMN_C_N_S_P_PRIMKEY_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(scope);

				if (bindPrimKey) {
					qPos.add(primKey);
				}

				if (!pagination) {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourcePermission>)QueryUtil.list(q,
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
	 * Returns the first resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByC_N_S_P_First(long companyId, String name,
		int scope, String primKey,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByC_N_S_P_First(companyId,
				name, scope, primKey, orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", scope=");
		msg.append(scope);

		msg.append(", primKey=");
		msg.append(primKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the first resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByC_N_S_P_First(long companyId, String name,
		int scope, String primKey,
		OrderByComparator<ResourcePermission> orderByComparator) {
		List<ResourcePermission> list = findByC_N_S_P(companyId, name, scope,
				primKey, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByC_N_S_P_Last(long companyId, String name,
		int scope, String primKey,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByC_N_S_P_Last(companyId,
				name, scope, primKey, orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", scope=");
		msg.append(scope);

		msg.append(", primKey=");
		msg.append(primKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the last resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByC_N_S_P_Last(long companyId, String name,
		int scope, String primKey,
		OrderByComparator<ResourcePermission> orderByComparator) {
		int count = countByC_N_S_P(companyId, name, scope, primKey);

		if (count == 0) {
			return null;
		}

		List<ResourcePermission> list = findByC_N_S_P(companyId, name, scope,
				primKey, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource permissions before and after the current resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param resourcePermissionId the primary key of the current resource permission
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource permission
	 * @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission[] findByC_N_S_P_PrevAndNext(
		long resourcePermissionId, long companyId, String name, int scope,
		String primKey, OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = findByPrimaryKey(resourcePermissionId);

		Session session = null;

		try {
			session = openSession();

			ResourcePermission[] array = new ResourcePermissionImpl[3];

			array[0] = getByC_N_S_P_PrevAndNext(session, resourcePermission,
					companyId, name, scope, primKey, orderByComparator, true);

			array[1] = resourcePermission;

			array[2] = getByC_N_S_P_PrevAndNext(session, resourcePermission,
					companyId, name, scope, primKey, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ResourcePermission getByC_N_S_P_PrevAndNext(Session session,
		ResourcePermission resourcePermission, long companyId, String name,
		int scope, String primKey,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

		query.append(_FINDER_COLUMN_C_N_S_P_COMPANYID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_C_N_S_P_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_N_S_P_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_C_N_S_P_NAME_2);
		}

		query.append(_FINDER_COLUMN_C_N_S_P_SCOPE_2);

		boolean bindPrimKey = false;

		if (primKey == null) {
			query.append(_FINDER_COLUMN_C_N_S_P_PRIMKEY_1);
		}
		else if (primKey.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_N_S_P_PRIMKEY_3);
		}
		else {
			bindPrimKey = true;

			query.append(_FINDER_COLUMN_C_N_S_P_PRIMKEY_2);
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
			query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(scope);

		if (bindPrimKey) {
			qPos.add(primKey);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resourcePermission);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourcePermission> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 */
	@Override
	public void removeByC_N_S_P(long companyId, String name, int scope,
		String primKey) {
		for (ResourcePermission resourcePermission : findByC_N_S_P(companyId,
				name, scope, primKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(resourcePermission);
		}
	}

	/**
	 * Returns the number of resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @return the number of matching resource permissions
	 */
	@Override
	public int countByC_N_S_P(long companyId, String name, int scope,
		String primKey) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_N_S_P;

		Object[] finderArgs = new Object[] { companyId, name, scope, primKey };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_N_S_P_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_S_P_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_SCOPE_2);

			boolean bindPrimKey = false;

			if (primKey == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_PRIMKEY_1);
			}
			else if (primKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_PRIMKEY_3);
			}
			else {
				bindPrimKey = true;

				query.append(_FINDER_COLUMN_C_N_S_P_PRIMKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(scope);

				if (bindPrimKey) {
					qPos.add(primKey);
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

	private static final String _FINDER_COLUMN_C_N_S_P_COMPANYID_2 = "resourcePermission.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_NAME_1 = "resourcePermission.name IS NULL AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_NAME_2 = "resourcePermission.name = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_NAME_3 = "(resourcePermission.name IS NULL OR resourcePermission.name = '') AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_SCOPE_2 = "resourcePermission.scope = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_PRIMKEY_1 = "resourcePermission.primKey IS NULL";
	private static final String _FINDER_COLUMN_C_N_S_P_PRIMKEY_2 = "resourcePermission.primKey = ?";
	private static final String _FINDER_COLUMN_C_N_S_P_PRIMKEY_3 = "(resourcePermission.primKey IS NULL OR resourcePermission.primKey = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_S_P_R =
		new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_N_S_P_R",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P_R =
		new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_N_S_P_R",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName(),
				Long.class.getName()
			},
			ResourcePermissionModelImpl.COMPANYID_COLUMN_BITMASK |
			ResourcePermissionModelImpl.NAME_COLUMN_BITMASK |
			ResourcePermissionModelImpl.SCOPE_COLUMN_BITMASK |
			ResourcePermissionModelImpl.PRIMKEY_COLUMN_BITMASK |
			ResourcePermissionModelImpl.ROLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_FETCH_BY_C_N_S_P_R = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_N_S_P_R",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName(),
				Long.class.getName()
			},
			ResourcePermissionModelImpl.COMPANYID_COLUMN_BITMASK |
			ResourcePermissionModelImpl.NAME_COLUMN_BITMASK |
			ResourcePermissionModelImpl.SCOPE_COLUMN_BITMASK |
			ResourcePermissionModelImpl.PRIMKEY_COLUMN_BITMASK |
			ResourcePermissionModelImpl.ROLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N_S_P_R = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N_S_P_R",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName(),
				Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_N_S_P_R =
		new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_N_S_P_R",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param roleIds the role IDs
	 * @return the matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P_R(long companyId,
		String name, int scope, String primKey, long[] roleIds) {
		return findByC_N_S_P_R(companyId, name, scope, primKey, roleIds,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param roleIds the role IDs
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @return the range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P_R(long companyId,
		String name, int scope, String primKey, long[] roleIds, int start,
		int end) {
		return findByC_N_S_P_R(companyId, name, scope, primKey, roleIds, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param roleIds the role IDs
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P_R(long companyId,
		String name, int scope, String primKey, long[] roleIds, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator) {
		return findByC_N_S_P_R(companyId, name, scope, primKey, roleIds, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param roleId the role ID
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P_R(long companyId,
		String name, int scope, String primKey, long[] roleIds, int start,
		int end, OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		if (roleIds == null) {
			roleIds = new long[0];
		}
		else if (roleIds.length > 1) {
			roleIds = ArrayUtil.unique(roleIds);

			Arrays.sort(roleIds);
		}

		if (roleIds.length == 1) {
			ResourcePermission resourcePermission = fetchByC_N_S_P_R(companyId,
					name, scope, primKey, roleIds[0]);

			if (resourcePermission == null) {
				return Collections.emptyList();
			}
			else {
				List<ResourcePermission> list = new ArrayList<ResourcePermission>(1);

				list.add(resourcePermission);

				return list;
			}
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					companyId, name, scope, primKey, StringUtil.merge(roleIds)
				};
		}
		else {
			finderArgs = new Object[] {
					companyId, name, scope, primKey, StringUtil.merge(roleIds),
					
					start, end, orderByComparator
				};
		}

		List<ResourcePermission> list = null;

		if (retrieveFromCache) {
			list = (List<ResourcePermission>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_S_P_R,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourcePermission resourcePermission : list) {
					if ((companyId != resourcePermission.getCompanyId()) ||
							!Objects.equals(name, resourcePermission.getName()) ||
							(scope != resourcePermission.getScope()) ||
							!Objects.equals(primKey,
								resourcePermission.getPrimKey()) ||
							!ArrayUtil.contains(roleIds,
								resourcePermission.getRoleId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_N_S_P_R_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_S_P_R_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_R_SCOPE_2);

			boolean bindPrimKey = false;

			if (primKey == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_PRIMKEY_1);
			}
			else if (primKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_PRIMKEY_3);
			}
			else {
				bindPrimKey = true;

				query.append(_FINDER_COLUMN_C_N_S_P_R_PRIMKEY_2);
			}

			if (roleIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_C_N_S_P_R_ROLEID_7);

				query.append(StringUtil.merge(roleIds));

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
				query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(scope);

				if (bindPrimKey) {
					qPos.add(primKey);
				}

				if (!pagination) {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_S_P_R,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_S_P_R,
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
	 * Returns the resource permission where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = &#63; or throws a {@link NoSuchResourcePermissionException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param roleId the role ID
	 * @return the matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByC_N_S_P_R(long companyId, String name,
		int scope, String primKey, long roleId)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByC_N_S_P_R(companyId,
				name, scope, primKey, roleId);

		if (resourcePermission == null) {
			StringBundler msg = new StringBundler(12);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", scope=");
			msg.append(scope);

			msg.append(", primKey=");
			msg.append(primKey);

			msg.append(", roleId=");
			msg.append(roleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchResourcePermissionException(msg.toString());
		}

		return resourcePermission;
	}

	/**
	 * Returns the resource permission where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param roleId the role ID
	 * @return the matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByC_N_S_P_R(long companyId, String name,
		int scope, String primKey, long roleId) {
		return fetchByC_N_S_P_R(companyId, name, scope, primKey, roleId, true);
	}

	/**
	 * Returns the resource permission where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param roleId the role ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByC_N_S_P_R(long companyId, String name,
		int scope, String primKey, long roleId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				companyId, name, scope, primKey, roleId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_N_S_P_R,
					finderArgs, this);
		}

		if (result instanceof ResourcePermission) {
			ResourcePermission resourcePermission = (ResourcePermission)result;

			if ((companyId != resourcePermission.getCompanyId()) ||
					!Objects.equals(name, resourcePermission.getName()) ||
					(scope != resourcePermission.getScope()) ||
					!Objects.equals(primKey, resourcePermission.getPrimKey()) ||
					(roleId != resourcePermission.getRoleId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_N_S_P_R_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_S_P_R_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_R_SCOPE_2);

			boolean bindPrimKey = false;

			if (primKey == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_PRIMKEY_1);
			}
			else if (primKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_PRIMKEY_3);
			}
			else {
				bindPrimKey = true;

				query.append(_FINDER_COLUMN_C_N_S_P_R_PRIMKEY_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_R_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(scope);

				if (bindPrimKey) {
					qPos.add(primKey);
				}

				qPos.add(roleId);

				List<ResourcePermission> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_N_S_P_R,
						finderArgs, list);
				}
				else {
					ResourcePermission resourcePermission = list.get(0);

					result = resourcePermission;

					cacheResult(resourcePermission);

					if ((resourcePermission.getCompanyId() != companyId) ||
							(resourcePermission.getName() == null) ||
							!resourcePermission.getName().equals(name) ||
							(resourcePermission.getScope() != scope) ||
							(resourcePermission.getPrimKey() == null) ||
							!resourcePermission.getPrimKey().equals(primKey) ||
							(resourcePermission.getRoleId() != roleId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_C_N_S_P_R,
							finderArgs, resourcePermission);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_N_S_P_R,
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
			return (ResourcePermission)result;
		}
	}

	/**
	 * Removes the resource permission where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param roleId the role ID
	 * @return the resource permission that was removed
	 */
	@Override
	public ResourcePermission removeByC_N_S_P_R(long companyId, String name,
		int scope, String primKey, long roleId)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = findByC_N_S_P_R(companyId,
				name, scope, primKey, roleId);

		return remove(resourcePermission);
	}

	/**
	 * Returns the number of resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param roleId the role ID
	 * @return the number of matching resource permissions
	 */
	@Override
	public int countByC_N_S_P_R(long companyId, String name, int scope,
		String primKey, long roleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_N_S_P_R;

		Object[] finderArgs = new Object[] {
				companyId, name, scope, primKey, roleId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_N_S_P_R_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_S_P_R_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_R_SCOPE_2);

			boolean bindPrimKey = false;

			if (primKey == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_PRIMKEY_1);
			}
			else if (primKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_PRIMKEY_3);
			}
			else {
				bindPrimKey = true;

				query.append(_FINDER_COLUMN_C_N_S_P_R_PRIMKEY_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_R_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(scope);

				if (bindPrimKey) {
					qPos.add(primKey);
				}

				qPos.add(roleId);

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
	 * Returns the number of resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKey = &#63; and roleId = any &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKey the prim key
	 * @param roleIds the role IDs
	 * @return the number of matching resource permissions
	 */
	@Override
	public int countByC_N_S_P_R(long companyId, String name, int scope,
		String primKey, long[] roleIds) {
		if (roleIds == null) {
			roleIds = new long[0];
		}
		else if (roleIds.length > 1) {
			roleIds = ArrayUtil.unique(roleIds);

			Arrays.sort(roleIds);
		}

		Object[] finderArgs = new Object[] {
				companyId, name, scope, primKey, StringUtil.merge(roleIds)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_N_S_P_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_N_S_P_R_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_S_P_R_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_R_SCOPE_2);

			boolean bindPrimKey = false;

			if (primKey == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_PRIMKEY_1);
			}
			else if (primKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_PRIMKEY_3);
			}
			else {
				bindPrimKey = true;

				query.append(_FINDER_COLUMN_C_N_S_P_R_PRIMKEY_2);
			}

			if (roleIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_C_N_S_P_R_ROLEID_7);

				query.append(StringUtil.merge(roleIds));

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

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(scope);

				if (bindPrimKey) {
					qPos.add(primKey);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_N_S_P_R,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_N_S_P_R,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_N_S_P_R_COMPANYID_2 = "resourcePermission.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_NAME_1 = "resourcePermission.name IS NULL AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_NAME_2 = "resourcePermission.name = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_NAME_3 = "(resourcePermission.name IS NULL OR resourcePermission.name = '') AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_SCOPE_2 = "resourcePermission.scope = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_PRIMKEY_1 = "resourcePermission.primKey IS NULL AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_PRIMKEY_2 = "resourcePermission.primKey = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_PRIMKEY_3 = "(resourcePermission.primKey IS NULL OR resourcePermission.primKey = '') AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_ROLEID_2 = "resourcePermission.roleId = ?";
	private static final String _FINDER_COLUMN_C_N_S_P_R_ROLEID_7 = "resourcePermission.roleId IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_S_P_R_V =
		new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_N_S_P_R_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Long.class.getName(),
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P_R_V =
		new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED,
			ResourcePermissionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_N_S_P_R_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Long.class.getName(),
				Long.class.getName(), Boolean.class.getName()
			},
			ResourcePermissionModelImpl.COMPANYID_COLUMN_BITMASK |
			ResourcePermissionModelImpl.NAME_COLUMN_BITMASK |
			ResourcePermissionModelImpl.SCOPE_COLUMN_BITMASK |
			ResourcePermissionModelImpl.PRIMKEYID_COLUMN_BITMASK |
			ResourcePermissionModelImpl.ROLEID_COLUMN_BITMASK |
			ResourcePermissionModelImpl.VIEWACTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N_S_P_R_V = new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N_S_P_R_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Long.class.getName(),
				Long.class.getName(), Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_N_S_P_R_V =
		new FinderPath(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_N_S_P_R_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Long.class.getName(),
				Long.class.getName(), Boolean.class.getName()
			});

	/**
	 * Returns all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleId the role ID
	 * @param viewActionId the view action ID
	 * @return the matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		String name, int scope, long primKeyId, long roleId,
		boolean viewActionId) {
		return findByC_N_S_P_R_V(companyId, name, scope, primKeyId, roleId,
			viewActionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleId the role ID
	 * @param viewActionId the view action ID
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @return the range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		String name, int scope, long primKeyId, long roleId,
		boolean viewActionId, int start, int end) {
		return findByC_N_S_P_R_V(companyId, name, scope, primKeyId, roleId,
			viewActionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleId the role ID
	 * @param viewActionId the view action ID
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		String name, int scope, long primKeyId, long roleId,
		boolean viewActionId, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return findByC_N_S_P_R_V(companyId, name, scope, primKeyId, roleId,
			viewActionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleId the role ID
	 * @param viewActionId the view action ID
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		String name, int scope, long primKeyId, long roleId,
		boolean viewActionId, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P_R_V;
			finderArgs = new Object[] {
					companyId, name, scope, primKeyId, roleId, viewActionId
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_S_P_R_V;
			finderArgs = new Object[] {
					companyId, name, scope, primKeyId, roleId, viewActionId,
					
					start, end, orderByComparator
				};
		}

		List<ResourcePermission> list = null;

		if (retrieveFromCache) {
			list = (List<ResourcePermission>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourcePermission resourcePermission : list) {
					if ((companyId != resourcePermission.getCompanyId()) ||
							!Objects.equals(name, resourcePermission.getName()) ||
							(scope != resourcePermission.getScope()) ||
							(primKeyId != resourcePermission.getPrimKeyId()) ||
							(roleId != resourcePermission.getRoleId()) ||
							(viewActionId != resourcePermission.getViewActionId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(8 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(8);
			}

			query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_SCOPE_2);

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_PRIMKEYID_2);

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_ROLEID_2);

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_VIEWACTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(scope);

				qPos.add(primKeyId);

				qPos.add(roleId);

				qPos.add(viewActionId);

				if (!pagination) {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourcePermission>)QueryUtil.list(q,
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
	 * Returns the first resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleId the role ID
	 * @param viewActionId the view action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByC_N_S_P_R_V_First(long companyId,
		String name, int scope, long primKeyId, long roleId,
		boolean viewActionId,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByC_N_S_P_R_V_First(companyId,
				name, scope, primKeyId, roleId, viewActionId, orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", scope=");
		msg.append(scope);

		msg.append(", primKeyId=");
		msg.append(primKeyId);

		msg.append(", roleId=");
		msg.append(roleId);

		msg.append(", viewActionId=");
		msg.append(viewActionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the first resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleId the role ID
	 * @param viewActionId the view action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByC_N_S_P_R_V_First(long companyId,
		String name, int scope, long primKeyId, long roleId,
		boolean viewActionId,
		OrderByComparator<ResourcePermission> orderByComparator) {
		List<ResourcePermission> list = findByC_N_S_P_R_V(companyId, name,
				scope, primKeyId, roleId, viewActionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleId the role ID
	 * @param viewActionId the view action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission
	 * @throws NoSuchResourcePermissionException if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission findByC_N_S_P_R_V_Last(long companyId,
		String name, int scope, long primKeyId, long roleId,
		boolean viewActionId,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByC_N_S_P_R_V_Last(companyId,
				name, scope, primKeyId, roleId, viewActionId, orderByComparator);

		if (resourcePermission != null) {
			return resourcePermission;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", scope=");
		msg.append(scope);

		msg.append(", primKeyId=");
		msg.append(primKeyId);

		msg.append(", roleId=");
		msg.append(roleId);

		msg.append(", viewActionId=");
		msg.append(viewActionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResourcePermissionException(msg.toString());
	}

	/**
	 * Returns the last resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleId the role ID
	 * @param viewActionId the view action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource permission, or <code>null</code> if a matching resource permission could not be found
	 */
	@Override
	public ResourcePermission fetchByC_N_S_P_R_V_Last(long companyId,
		String name, int scope, long primKeyId, long roleId,
		boolean viewActionId,
		OrderByComparator<ResourcePermission> orderByComparator) {
		int count = countByC_N_S_P_R_V(companyId, name, scope, primKeyId,
				roleId, viewActionId);

		if (count == 0) {
			return null;
		}

		List<ResourcePermission> list = findByC_N_S_P_R_V(companyId, name,
				scope, primKeyId, roleId, viewActionId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource permissions before and after the current resource permission in the ordered set where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	 *
	 * @param resourcePermissionId the primary key of the current resource permission
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleId the role ID
	 * @param viewActionId the view action ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource permission
	 * @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission[] findByC_N_S_P_R_V_PrevAndNext(
		long resourcePermissionId, long companyId, String name, int scope,
		long primKeyId, long roleId, boolean viewActionId,
		OrderByComparator<ResourcePermission> orderByComparator)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = findByPrimaryKey(resourcePermissionId);

		Session session = null;

		try {
			session = openSession();

			ResourcePermission[] array = new ResourcePermissionImpl[3];

			array[0] = getByC_N_S_P_R_V_PrevAndNext(session,
					resourcePermission, companyId, name, scope, primKeyId,
					roleId, viewActionId, orderByComparator, true);

			array[1] = resourcePermission;

			array[2] = getByC_N_S_P_R_V_PrevAndNext(session,
					resourcePermission, companyId, name, scope, primKeyId,
					roleId, viewActionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ResourcePermission getByC_N_S_P_R_V_PrevAndNext(Session session,
		ResourcePermission resourcePermission, long companyId, String name,
		int scope, long primKeyId, long roleId, boolean viewActionId,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(9 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(8);
		}

		query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

		query.append(_FINDER_COLUMN_C_N_S_P_R_V_COMPANYID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_2);
		}

		query.append(_FINDER_COLUMN_C_N_S_P_R_V_SCOPE_2);

		query.append(_FINDER_COLUMN_C_N_S_P_R_V_PRIMKEYID_2);

		query.append(_FINDER_COLUMN_C_N_S_P_R_V_ROLEID_2);

		query.append(_FINDER_COLUMN_C_N_S_P_R_V_VIEWACTIONID_2);

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
			query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(scope);

		qPos.add(primKeyId);

		qPos.add(roleId);

		qPos.add(viewActionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resourcePermission);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourcePermission> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = any &#63; and viewActionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleIds the role IDs
	 * @param viewActionId the view action ID
	 * @return the matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		String name, int scope, long primKeyId, long[] roleIds,
		boolean viewActionId) {
		return findByC_N_S_P_R_V(companyId, name, scope, primKeyId, roleIds,
			viewActionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = any &#63; and viewActionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleIds the role IDs
	 * @param viewActionId the view action ID
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @return the range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		String name, int scope, long primKeyId, long[] roleIds,
		boolean viewActionId, int start, int end) {
		return findByC_N_S_P_R_V(companyId, name, scope, primKeyId, roleIds,
			viewActionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = any &#63; and viewActionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleIds the role IDs
	 * @param viewActionId the view action ID
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		String name, int scope, long primKeyId, long[] roleIds,
		boolean viewActionId, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return findByC_N_S_P_R_V(companyId, name, scope, primKeyId, roleIds,
			viewActionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleId the role ID
	 * @param viewActionId the view action ID
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource permissions
	 */
	@Override
	public List<ResourcePermission> findByC_N_S_P_R_V(long companyId,
		String name, int scope, long primKeyId, long[] roleIds,
		boolean viewActionId, int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
		boolean retrieveFromCache) {
		if (roleIds == null) {
			roleIds = new long[0];
		}
		else if (roleIds.length > 1) {
			roleIds = ArrayUtil.unique(roleIds);

			Arrays.sort(roleIds);
		}

		if (roleIds.length == 1) {
			return findByC_N_S_P_R_V(companyId, name, scope, primKeyId,
				roleIds[0], viewActionId, start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					companyId, name, scope, primKeyId, StringUtil.merge(roleIds),
					viewActionId
				};
		}
		else {
			finderArgs = new Object[] {
					companyId, name, scope, primKeyId, StringUtil.merge(roleIds),
					viewActionId,
					
					start, end, orderByComparator
				};
		}

		List<ResourcePermission> list = null;

		if (retrieveFromCache) {
			list = (List<ResourcePermission>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_S_P_R_V,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourcePermission resourcePermission : list) {
					if ((companyId != resourcePermission.getCompanyId()) ||
							!Objects.equals(name, resourcePermission.getName()) ||
							(scope != resourcePermission.getScope()) ||
							(primKeyId != resourcePermission.getPrimKeyId()) ||
							!ArrayUtil.contains(roleIds,
								resourcePermission.getRoleId()) ||
							(viewActionId != resourcePermission.getViewActionId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_SCOPE_2);

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_PRIMKEYID_2);

			if (roleIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_C_N_S_P_R_V_ROLEID_7);

				query.append(StringUtil.merge(roleIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_VIEWACTIONID_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ResourcePermissionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(scope);

				qPos.add(primKeyId);

				qPos.add(viewActionId);

				if (!pagination) {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_S_P_R_V,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_S_P_R_V,
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
	 * Removes all the resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleId the role ID
	 * @param viewActionId the view action ID
	 */
	@Override
	public void removeByC_N_S_P_R_V(long companyId, String name, int scope,
		long primKeyId, long roleId, boolean viewActionId) {
		for (ResourcePermission resourcePermission : findByC_N_S_P_R_V(
				companyId, name, scope, primKeyId, roleId, viewActionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(resourcePermission);
		}
	}

	/**
	 * Returns the number of resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = &#63; and viewActionId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleId the role ID
	 * @param viewActionId the view action ID
	 * @return the number of matching resource permissions
	 */
	@Override
	public int countByC_N_S_P_R_V(long companyId, String name, int scope,
		long primKeyId, long roleId, boolean viewActionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_N_S_P_R_V;

		Object[] finderArgs = new Object[] {
				companyId, name, scope, primKeyId, roleId, viewActionId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_SCOPE_2);

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_PRIMKEYID_2);

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_ROLEID_2);

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_VIEWACTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(scope);

				qPos.add(primKeyId);

				qPos.add(roleId);

				qPos.add(viewActionId);

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
	 * Returns the number of resource permissions where companyId = &#63; and name = &#63; and scope = &#63; and primKeyId = &#63; and roleId = any &#63; and viewActionId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param scope the scope
	 * @param primKeyId the prim key ID
	 * @param roleIds the role IDs
	 * @param viewActionId the view action ID
	 * @return the number of matching resource permissions
	 */
	@Override
	public int countByC_N_S_P_R_V(long companyId, String name, int scope,
		long primKeyId, long[] roleIds, boolean viewActionId) {
		if (roleIds == null) {
			roleIds = new long[0];
		}
		else if (roleIds.length > 1) {
			roleIds = ArrayUtil.unique(roleIds);

			Arrays.sort(roleIds);
		}

		Object[] finderArgs = new Object[] {
				companyId, name, scope, primKeyId, StringUtil.merge(roleIds),
				viewActionId
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_N_S_P_R_V,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_RESOURCEPERMISSION_WHERE);

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_S_P_R_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_SCOPE_2);

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_PRIMKEYID_2);

			if (roleIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_C_N_S_P_R_V_ROLEID_7);

				query.append(StringUtil.merge(roleIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_N_S_P_R_V_VIEWACTIONID_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(scope);

				qPos.add(primKeyId);

				qPos.add(viewActionId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_N_S_P_R_V,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_N_S_P_R_V,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_N_S_P_R_V_COMPANYID_2 = "resourcePermission.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_V_NAME_1 = "resourcePermission.name IS NULL AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_V_NAME_2 = "resourcePermission.name = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_V_NAME_3 = "(resourcePermission.name IS NULL OR resourcePermission.name = '') AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_V_SCOPE_2 = "resourcePermission.scope = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_V_PRIMKEYID_2 = "resourcePermission.primKeyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_V_ROLEID_2 = "resourcePermission.roleId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_S_P_R_V_ROLEID_7 = "resourcePermission.roleId IN (";
	private static final String _FINDER_COLUMN_C_N_S_P_R_V_VIEWACTIONID_2 = "resourcePermission.viewActionId = ?";

	public ResourcePermissionPersistenceImpl() {
		setModelClass(ResourcePermission.class);
	}

	/**
	 * Caches the resource permission in the entity cache if it is enabled.
	 *
	 * @param resourcePermission the resource permission
	 */
	@Override
	public void cacheResult(ResourcePermission resourcePermission) {
		entityCache.putResult(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionImpl.class, resourcePermission.getPrimaryKey(),
			resourcePermission);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_N_S_P_R,
			new Object[] {
				resourcePermission.getCompanyId(), resourcePermission.getName(),
				resourcePermission.getScope(), resourcePermission.getPrimKey(),
				resourcePermission.getRoleId()
			}, resourcePermission);

		resourcePermission.resetOriginalValues();
	}

	/**
	 * Caches the resource permissions in the entity cache if it is enabled.
	 *
	 * @param resourcePermissions the resource permissions
	 */
	@Override
	public void cacheResult(List<ResourcePermission> resourcePermissions) {
		for (ResourcePermission resourcePermission : resourcePermissions) {
			if (entityCache.getResult(
						ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
						ResourcePermissionImpl.class,
						resourcePermission.getPrimaryKey()) == null) {
				cacheResult(resourcePermission);
			}
			else {
				resourcePermission.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all resource permissions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ResourcePermissionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the resource permission.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ResourcePermission resourcePermission) {
		entityCache.removeResult(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionImpl.class, resourcePermission.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ResourcePermissionModelImpl)resourcePermission);
	}

	@Override
	public void clearCache(List<ResourcePermission> resourcePermissions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ResourcePermission resourcePermission : resourcePermissions) {
			entityCache.removeResult(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
				ResourcePermissionImpl.class, resourcePermission.getPrimaryKey());

			clearUniqueFindersCache((ResourcePermissionModelImpl)resourcePermission);
		}
	}

	protected void cacheUniqueFindersCache(
		ResourcePermissionModelImpl resourcePermissionModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					resourcePermissionModelImpl.getCompanyId(),
					resourcePermissionModelImpl.getName(),
					resourcePermissionModelImpl.getScope(),
					resourcePermissionModelImpl.getPrimKey(),
					resourcePermissionModelImpl.getRoleId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_C_N_S_P_R, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_C_N_S_P_R, args,
				resourcePermissionModelImpl);
		}
		else {
			if ((resourcePermissionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_N_S_P_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourcePermissionModelImpl.getCompanyId(),
						resourcePermissionModelImpl.getName(),
						resourcePermissionModelImpl.getScope(),
						resourcePermissionModelImpl.getPrimKey(),
						resourcePermissionModelImpl.getRoleId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_C_N_S_P_R, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_C_N_S_P_R, args,
					resourcePermissionModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		ResourcePermissionModelImpl resourcePermissionModelImpl) {
		Object[] args = new Object[] {
				resourcePermissionModelImpl.getCompanyId(),
				resourcePermissionModelImpl.getName(),
				resourcePermissionModelImpl.getScope(),
				resourcePermissionModelImpl.getPrimKey(),
				resourcePermissionModelImpl.getRoleId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N_S_P_R, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_C_N_S_P_R, args);

		if ((resourcePermissionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_N_S_P_R.getColumnBitmask()) != 0) {
			args = new Object[] {
					resourcePermissionModelImpl.getOriginalCompanyId(),
					resourcePermissionModelImpl.getOriginalName(),
					resourcePermissionModelImpl.getOriginalScope(),
					resourcePermissionModelImpl.getOriginalPrimKey(),
					resourcePermissionModelImpl.getOriginalRoleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N_S_P_R, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_N_S_P_R, args);
		}
	}

	/**
	 * Creates a new resource permission with the primary key. Does not add the resource permission to the database.
	 *
	 * @param resourcePermissionId the primary key for the new resource permission
	 * @return the new resource permission
	 */
	@Override
	public ResourcePermission create(long resourcePermissionId) {
		ResourcePermission resourcePermission = new ResourcePermissionImpl();

		resourcePermission.setNew(true);
		resourcePermission.setPrimaryKey(resourcePermissionId);

		resourcePermission.setCompanyId(companyProvider.getCompanyId());

		return resourcePermission;
	}

	/**
	 * Removes the resource permission with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param resourcePermissionId the primary key of the resource permission
	 * @return the resource permission that was removed
	 * @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission remove(long resourcePermissionId)
		throws NoSuchResourcePermissionException {
		return remove((Serializable)resourcePermissionId);
	}

	/**
	 * Removes the resource permission with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the resource permission
	 * @return the resource permission that was removed
	 * @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission remove(Serializable primaryKey)
		throws NoSuchResourcePermissionException {
		Session session = null;

		try {
			session = openSession();

			ResourcePermission resourcePermission = (ResourcePermission)session.get(ResourcePermissionImpl.class,
					primaryKey);

			if (resourcePermission == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResourcePermissionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(resourcePermission);
		}
		catch (NoSuchResourcePermissionException nsee) {
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
	protected ResourcePermission removeImpl(
		ResourcePermission resourcePermission) {
		resourcePermission = toUnwrappedModel(resourcePermission);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(resourcePermission)) {
				resourcePermission = (ResourcePermission)session.get(ResourcePermissionImpl.class,
						resourcePermission.getPrimaryKeyObj());
			}

			if (resourcePermission != null) {
				session.delete(resourcePermission);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (resourcePermission != null) {
			clearCache(resourcePermission);
		}

		return resourcePermission;
	}

	@Override
	public ResourcePermission updateImpl(ResourcePermission resourcePermission) {
		resourcePermission = toUnwrappedModel(resourcePermission);

		boolean isNew = resourcePermission.isNew();

		ResourcePermissionModelImpl resourcePermissionModelImpl = (ResourcePermissionModelImpl)resourcePermission;

		Session session = null;

		try {
			session = openSession();

			if (resourcePermission.isNew()) {
				session.save(resourcePermission);

				resourcePermission.setNew(false);
			}
			else {
				resourcePermission = (ResourcePermission)session.merge(resourcePermission);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ResourcePermissionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((resourcePermissionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourcePermissionModelImpl.getOriginalName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);

				args = new Object[] { resourcePermissionModelImpl.getName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);
			}

			if ((resourcePermissionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCOPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourcePermissionModelImpl.getOriginalScope()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCOPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCOPE,
					args);

				args = new Object[] { resourcePermissionModelImpl.getScope() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCOPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCOPE,
					args);
			}

			if ((resourcePermissionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ROLEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourcePermissionModelImpl.getOriginalRoleId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ROLEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ROLEID,
					args);

				args = new Object[] { resourcePermissionModelImpl.getRoleId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ROLEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ROLEID,
					args);
			}

			if ((resourcePermissionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourcePermissionModelImpl.getOriginalCompanyId(),
						resourcePermissionModelImpl.getOriginalName(),
						resourcePermissionModelImpl.getOriginalScope()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S,
					args);

				args = new Object[] {
						resourcePermissionModelImpl.getCompanyId(),
						resourcePermissionModelImpl.getName(),
						resourcePermissionModelImpl.getScope()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S,
					args);
			}

			if ((resourcePermissionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourcePermissionModelImpl.getOriginalCompanyId(),
						resourcePermissionModelImpl.getOriginalScope(),
						resourcePermissionModelImpl.getOriginalPrimKey()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_S_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_P,
					args);

				args = new Object[] {
						resourcePermissionModelImpl.getCompanyId(),
						resourcePermissionModelImpl.getScope(),
						resourcePermissionModelImpl.getPrimKey()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_S_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_P,
					args);
			}

			if ((resourcePermissionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourcePermissionModelImpl.getOriginalCompanyId(),
						resourcePermissionModelImpl.getOriginalName(),
						resourcePermissionModelImpl.getOriginalScope(),
						resourcePermissionModelImpl.getOriginalPrimKey()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N_S_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P,
					args);

				args = new Object[] {
						resourcePermissionModelImpl.getCompanyId(),
						resourcePermissionModelImpl.getName(),
						resourcePermissionModelImpl.getScope(),
						resourcePermissionModelImpl.getPrimKey()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N_S_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P,
					args);
			}

			if ((resourcePermissionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourcePermissionModelImpl.getOriginalCompanyId(),
						resourcePermissionModelImpl.getOriginalName(),
						resourcePermissionModelImpl.getOriginalScope(),
						resourcePermissionModelImpl.getOriginalPrimKey(),
						resourcePermissionModelImpl.getOriginalRoleId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N_S_P_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P_R,
					args);

				args = new Object[] {
						resourcePermissionModelImpl.getCompanyId(),
						resourcePermissionModelImpl.getName(),
						resourcePermissionModelImpl.getScope(),
						resourcePermissionModelImpl.getPrimKey(),
						resourcePermissionModelImpl.getRoleId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N_S_P_R, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P_R,
					args);
			}

			if ((resourcePermissionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P_R_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourcePermissionModelImpl.getOriginalCompanyId(),
						resourcePermissionModelImpl.getOriginalName(),
						resourcePermissionModelImpl.getOriginalScope(),
						resourcePermissionModelImpl.getOriginalPrimKeyId(),
						resourcePermissionModelImpl.getOriginalRoleId(),
						resourcePermissionModelImpl.getOriginalViewActionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N_S_P_R_V, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P_R_V,
					args);

				args = new Object[] {
						resourcePermissionModelImpl.getCompanyId(),
						resourcePermissionModelImpl.getName(),
						resourcePermissionModelImpl.getScope(),
						resourcePermissionModelImpl.getPrimKeyId(),
						resourcePermissionModelImpl.getRoleId(),
						resourcePermissionModelImpl.getViewActionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N_S_P_R_V, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_S_P_R_V,
					args);
			}
		}

		entityCache.putResult(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
			ResourcePermissionImpl.class, resourcePermission.getPrimaryKey(),
			resourcePermission, false);

		clearUniqueFindersCache(resourcePermissionModelImpl);
		cacheUniqueFindersCache(resourcePermissionModelImpl, isNew);

		resourcePermission.resetOriginalValues();

		return resourcePermission;
	}

	protected ResourcePermission toUnwrappedModel(
		ResourcePermission resourcePermission) {
		if (resourcePermission instanceof ResourcePermissionImpl) {
			return resourcePermission;
		}

		ResourcePermissionImpl resourcePermissionImpl = new ResourcePermissionImpl();

		resourcePermissionImpl.setNew(resourcePermission.isNew());
		resourcePermissionImpl.setPrimaryKey(resourcePermission.getPrimaryKey());

		resourcePermissionImpl.setMvccVersion(resourcePermission.getMvccVersion());
		resourcePermissionImpl.setResourcePermissionId(resourcePermission.getResourcePermissionId());
		resourcePermissionImpl.setCompanyId(resourcePermission.getCompanyId());
		resourcePermissionImpl.setName(resourcePermission.getName());
		resourcePermissionImpl.setScope(resourcePermission.getScope());
		resourcePermissionImpl.setPrimKey(resourcePermission.getPrimKey());
		resourcePermissionImpl.setPrimKeyId(resourcePermission.getPrimKeyId());
		resourcePermissionImpl.setRoleId(resourcePermission.getRoleId());
		resourcePermissionImpl.setOwnerId(resourcePermission.getOwnerId());
		resourcePermissionImpl.setActionIds(resourcePermission.getActionIds());
		resourcePermissionImpl.setViewActionId(resourcePermission.isViewActionId());

		return resourcePermissionImpl;
	}

	/**
	 * Returns the resource permission with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the resource permission
	 * @return the resource permission
	 * @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission findByPrimaryKey(Serializable primaryKey)
		throws NoSuchResourcePermissionException {
		ResourcePermission resourcePermission = fetchByPrimaryKey(primaryKey);

		if (resourcePermission == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchResourcePermissionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return resourcePermission;
	}

	/**
	 * Returns the resource permission with the primary key or throws a {@link NoSuchResourcePermissionException} if it could not be found.
	 *
	 * @param resourcePermissionId the primary key of the resource permission
	 * @return the resource permission
	 * @throws NoSuchResourcePermissionException if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission findByPrimaryKey(long resourcePermissionId)
		throws NoSuchResourcePermissionException {
		return findByPrimaryKey((Serializable)resourcePermissionId);
	}

	/**
	 * Returns the resource permission with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the resource permission
	 * @return the resource permission, or <code>null</code> if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
				ResourcePermissionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ResourcePermission resourcePermission = (ResourcePermission)serializable;

		if (resourcePermission == null) {
			Session session = null;

			try {
				session = openSession();

				resourcePermission = (ResourcePermission)session.get(ResourcePermissionImpl.class,
						primaryKey);

				if (resourcePermission != null) {
					cacheResult(resourcePermission);
				}
				else {
					entityCache.putResult(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
						ResourcePermissionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
					ResourcePermissionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return resourcePermission;
	}

	/**
	 * Returns the resource permission with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param resourcePermissionId the primary key of the resource permission
	 * @return the resource permission, or <code>null</code> if a resource permission with the primary key could not be found
	 */
	@Override
	public ResourcePermission fetchByPrimaryKey(long resourcePermissionId) {
		return fetchByPrimaryKey((Serializable)resourcePermissionId);
	}

	@Override
	public Map<Serializable, ResourcePermission> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ResourcePermission> map = new HashMap<Serializable, ResourcePermission>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ResourcePermission resourcePermission = fetchByPrimaryKey(primaryKey);

			if (resourcePermission != null) {
				map.put(primaryKey, resourcePermission);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
					ResourcePermissionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ResourcePermission)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RESOURCEPERMISSION_WHERE_PKS_IN);

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

			for (ResourcePermission resourcePermission : (List<ResourcePermission>)q.list()) {
				map.put(resourcePermission.getPrimaryKeyObj(),
					resourcePermission);

				cacheResult(resourcePermission);

				uncachedPrimaryKeys.remove(resourcePermission.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ResourcePermissionModelImpl.ENTITY_CACHE_ENABLED,
					ResourcePermissionImpl.class, primaryKey, nullModel);
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
	 * Returns all the resource permissions.
	 *
	 * @return the resource permissions
	 */
	@Override
	public List<ResourcePermission> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource permissions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @return the range of resource permissions
	 */
	@Override
	public List<ResourcePermission> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource permissions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of resource permissions
	 */
	@Override
	public List<ResourcePermission> findAll(int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource permissions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourcePermissionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of resource permissions
	 * @param end the upper bound of the range of resource permissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of resource permissions
	 */
	@Override
	public List<ResourcePermission> findAll(int start, int end,
		OrderByComparator<ResourcePermission> orderByComparator,
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

		List<ResourcePermission> list = null;

		if (retrieveFromCache) {
			list = (List<ResourcePermission>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RESOURCEPERMISSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RESOURCEPERMISSION;

				if (pagination) {
					sql = sql.concat(ResourcePermissionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ResourcePermission>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourcePermission>)QueryUtil.list(q,
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
	 * Removes all the resource permissions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ResourcePermission resourcePermission : findAll()) {
			remove(resourcePermission);
		}
	}

	/**
	 * Returns the number of resource permissions.
	 *
	 * @return the number of resource permissions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RESOURCEPERMISSION);

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
		return ResourcePermissionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the resource permission persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ResourcePermissionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_RESOURCEPERMISSION = "SELECT resourcePermission FROM ResourcePermission resourcePermission";
	private static final String _SQL_SELECT_RESOURCEPERMISSION_WHERE_PKS_IN = "SELECT resourcePermission FROM ResourcePermission resourcePermission WHERE resourcePermissionId IN (";
	private static final String _SQL_SELECT_RESOURCEPERMISSION_WHERE = "SELECT resourcePermission FROM ResourcePermission resourcePermission WHERE ";
	private static final String _SQL_COUNT_RESOURCEPERMISSION = "SELECT COUNT(resourcePermission) FROM ResourcePermission resourcePermission";
	private static final String _SQL_COUNT_RESOURCEPERMISSION_WHERE = "SELECT COUNT(resourcePermission) FROM ResourcePermission resourcePermission WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "resourcePermission.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ResourcePermission exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ResourcePermission exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ResourcePermissionPersistenceImpl.class);
}