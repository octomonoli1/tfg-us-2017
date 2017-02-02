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

package com.liferay.dynamic.data.mapping.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.mapping.exception.NoSuchStructureVersionException;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.dynamic.data.mapping.model.impl.DDMStructureVersionImpl;
import com.liferay.dynamic.data.mapping.model.impl.DDMStructureVersionModelImpl;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureVersionPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

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
 * The persistence implementation for the d d m structure version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructureVersionPersistence
 * @see com.liferay.dynamic.data.mapping.service.persistence.DDMStructureVersionUtil
 * @generated
 */
@ProviderType
public class DDMStructureVersionPersistenceImpl extends BasePersistenceImpl<DDMStructureVersion>
	implements DDMStructureVersionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DDMStructureVersionUtil} to access the d d m structure version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DDMStructureVersionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionModelImpl.FINDER_CACHE_ENABLED,
			DDMStructureVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionModelImpl.FINDER_CACHE_ENABLED,
			DDMStructureVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STRUCTUREID =
		new FinderPath(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionModelImpl.FINDER_CACHE_ENABLED,
			DDMStructureVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStructureId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STRUCTUREID =
		new FinderPath(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionModelImpl.FINDER_CACHE_ENABLED,
			DDMStructureVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStructureId",
			new String[] { Long.class.getName() },
			DDMStructureVersionModelImpl.STRUCTUREID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STRUCTUREID = new FinderPath(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStructureId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the d d m structure versions where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @return the matching d d m structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByStructureId(long structureId) {
		return findByStructureId(structureId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the d d m structure versions where structureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDMStructureVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param start the lower bound of the range of d d m structure versions
	 * @param end the upper bound of the range of d d m structure versions (not inclusive)
	 * @return the range of matching d d m structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByStructureId(long structureId,
		int start, int end) {
		return findByStructureId(structureId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the d d m structure versions where structureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDMStructureVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param start the lower bound of the range of d d m structure versions
	 * @param end the upper bound of the range of d d m structure versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching d d m structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByStructureId(long structureId,
		int start, int end,
		OrderByComparator<DDMStructureVersion> orderByComparator) {
		return findByStructureId(structureId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the d d m structure versions where structureId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDMStructureVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param start the lower bound of the range of d d m structure versions
	 * @param end the upper bound of the range of d d m structure versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching d d m structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByStructureId(long structureId,
		int start, int end,
		OrderByComparator<DDMStructureVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STRUCTUREID;
			finderArgs = new Object[] { structureId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STRUCTUREID;
			finderArgs = new Object[] { structureId, start, end, orderByComparator };
		}

		List<DDMStructureVersion> list = null;

		if (retrieveFromCache) {
			list = (List<DDMStructureVersion>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMStructureVersion ddmStructureVersion : list) {
					if ((structureId != ddmStructureVersion.getStructureId())) {
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

			query.append(_SQL_SELECT_DDMSTRUCTUREVERSION_WHERE);

			query.append(_FINDER_COLUMN_STRUCTUREID_STRUCTUREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DDMStructureVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(structureId);

				if (!pagination) {
					list = (List<DDMStructureVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DDMStructureVersion>)QueryUtil.list(q,
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
	 * Returns the first d d m structure version in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d m structure version
	 * @throws NoSuchStructureVersionException if a matching d d m structure version could not be found
	 */
	@Override
	public DDMStructureVersion findByStructureId_First(long structureId,
		OrderByComparator<DDMStructureVersion> orderByComparator)
		throws NoSuchStructureVersionException {
		DDMStructureVersion ddmStructureVersion = fetchByStructureId_First(structureId,
				orderByComparator);

		if (ddmStructureVersion != null) {
			return ddmStructureVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("structureId=");
		msg.append(structureId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStructureVersionException(msg.toString());
	}

	/**
	 * Returns the first d d m structure version in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d m structure version, or <code>null</code> if a matching d d m structure version could not be found
	 */
	@Override
	public DDMStructureVersion fetchByStructureId_First(long structureId,
		OrderByComparator<DDMStructureVersion> orderByComparator) {
		List<DDMStructureVersion> list = findByStructureId(structureId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last d d m structure version in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d m structure version
	 * @throws NoSuchStructureVersionException if a matching d d m structure version could not be found
	 */
	@Override
	public DDMStructureVersion findByStructureId_Last(long structureId,
		OrderByComparator<DDMStructureVersion> orderByComparator)
		throws NoSuchStructureVersionException {
		DDMStructureVersion ddmStructureVersion = fetchByStructureId_Last(structureId,
				orderByComparator);

		if (ddmStructureVersion != null) {
			return ddmStructureVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("structureId=");
		msg.append(structureId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStructureVersionException(msg.toString());
	}

	/**
	 * Returns the last d d m structure version in the ordered set where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d m structure version, or <code>null</code> if a matching d d m structure version could not be found
	 */
	@Override
	public DDMStructureVersion fetchByStructureId_Last(long structureId,
		OrderByComparator<DDMStructureVersion> orderByComparator) {
		int count = countByStructureId(structureId);

		if (count == 0) {
			return null;
		}

		List<DDMStructureVersion> list = findByStructureId(structureId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the d d m structure versions before and after the current d d m structure version in the ordered set where structureId = &#63;.
	 *
	 * @param structureVersionId the primary key of the current d d m structure version
	 * @param structureId the structure ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next d d m structure version
	 * @throws NoSuchStructureVersionException if a d d m structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion[] findByStructureId_PrevAndNext(
		long structureVersionId, long structureId,
		OrderByComparator<DDMStructureVersion> orderByComparator)
		throws NoSuchStructureVersionException {
		DDMStructureVersion ddmStructureVersion = findByPrimaryKey(structureVersionId);

		Session session = null;

		try {
			session = openSession();

			DDMStructureVersion[] array = new DDMStructureVersionImpl[3];

			array[0] = getByStructureId_PrevAndNext(session,
					ddmStructureVersion, structureId, orderByComparator, true);

			array[1] = ddmStructureVersion;

			array[2] = getByStructureId_PrevAndNext(session,
					ddmStructureVersion, structureId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDMStructureVersion getByStructureId_PrevAndNext(
		Session session, DDMStructureVersion ddmStructureVersion,
		long structureId,
		OrderByComparator<DDMStructureVersion> orderByComparator,
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

		query.append(_SQL_SELECT_DDMSTRUCTUREVERSION_WHERE);

		query.append(_FINDER_COLUMN_STRUCTUREID_STRUCTUREID_2);

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
			query.append(DDMStructureVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(structureId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ddmStructureVersion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DDMStructureVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the d d m structure versions where structureId = &#63; from the database.
	 *
	 * @param structureId the structure ID
	 */
	@Override
	public void removeByStructureId(long structureId) {
		for (DDMStructureVersion ddmStructureVersion : findByStructureId(
				structureId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ddmStructureVersion);
		}
	}

	/**
	 * Returns the number of d d m structure versions where structureId = &#63;.
	 *
	 * @param structureId the structure ID
	 * @return the number of matching d d m structure versions
	 */
	@Override
	public int countByStructureId(long structureId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STRUCTUREID;

		Object[] finderArgs = new Object[] { structureId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DDMSTRUCTUREVERSION_WHERE);

			query.append(_FINDER_COLUMN_STRUCTUREID_STRUCTUREID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(structureId);

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

	private static final String _FINDER_COLUMN_STRUCTUREID_STRUCTUREID_2 = "ddmStructureVersion.structureId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_S_V = new FinderPath(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionModelImpl.FINDER_CACHE_ENABLED,
			DDMStructureVersionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByS_V",
			new String[] { Long.class.getName(), String.class.getName() },
			DDMStructureVersionModelImpl.STRUCTUREID_COLUMN_BITMASK |
			DDMStructureVersionModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_S_V = new FinderPath(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_V",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the d d m structure version where structureId = &#63; and version = &#63; or throws a {@link NoSuchStructureVersionException} if it could not be found.
	 *
	 * @param structureId the structure ID
	 * @param version the version
	 * @return the matching d d m structure version
	 * @throws NoSuchStructureVersionException if a matching d d m structure version could not be found
	 */
	@Override
	public DDMStructureVersion findByS_V(long structureId, String version)
		throws NoSuchStructureVersionException {
		DDMStructureVersion ddmStructureVersion = fetchByS_V(structureId,
				version);

		if (ddmStructureVersion == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("structureId=");
			msg.append(structureId);

			msg.append(", version=");
			msg.append(version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchStructureVersionException(msg.toString());
		}

		return ddmStructureVersion;
	}

	/**
	 * Returns the d d m structure version where structureId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param structureId the structure ID
	 * @param version the version
	 * @return the matching d d m structure version, or <code>null</code> if a matching d d m structure version could not be found
	 */
	@Override
	public DDMStructureVersion fetchByS_V(long structureId, String version) {
		return fetchByS_V(structureId, version, true);
	}

	/**
	 * Returns the d d m structure version where structureId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param structureId the structure ID
	 * @param version the version
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching d d m structure version, or <code>null</code> if a matching d d m structure version could not be found
	 */
	@Override
	public DDMStructureVersion fetchByS_V(long structureId, String version,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { structureId, version };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_S_V,
					finderArgs, this);
		}

		if (result instanceof DDMStructureVersion) {
			DDMStructureVersion ddmStructureVersion = (DDMStructureVersion)result;

			if ((structureId != ddmStructureVersion.getStructureId()) ||
					!Objects.equals(version, ddmStructureVersion.getVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DDMSTRUCTUREVERSION_WHERE);

			query.append(_FINDER_COLUMN_S_V_STRUCTUREID_2);

			boolean bindVersion = false;

			if (version == null) {
				query.append(_FINDER_COLUMN_S_V_VERSION_1);
			}
			else if (version.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_S_V_VERSION_3);
			}
			else {
				bindVersion = true;

				query.append(_FINDER_COLUMN_S_V_VERSION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(structureId);

				if (bindVersion) {
					qPos.add(version);
				}

				List<DDMStructureVersion> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_S_V, finderArgs,
						list);
				}
				else {
					DDMStructureVersion ddmStructureVersion = list.get(0);

					result = ddmStructureVersion;

					cacheResult(ddmStructureVersion);

					if ((ddmStructureVersion.getStructureId() != structureId) ||
							(ddmStructureVersion.getVersion() == null) ||
							!ddmStructureVersion.getVersion().equals(version)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_S_V,
							finderArgs, ddmStructureVersion);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_S_V, finderArgs);

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
			return (DDMStructureVersion)result;
		}
	}

	/**
	 * Removes the d d m structure version where structureId = &#63; and version = &#63; from the database.
	 *
	 * @param structureId the structure ID
	 * @param version the version
	 * @return the d d m structure version that was removed
	 */
	@Override
	public DDMStructureVersion removeByS_V(long structureId, String version)
		throws NoSuchStructureVersionException {
		DDMStructureVersion ddmStructureVersion = findByS_V(structureId, version);

		return remove(ddmStructureVersion);
	}

	/**
	 * Returns the number of d d m structure versions where structureId = &#63; and version = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param version the version
	 * @return the number of matching d d m structure versions
	 */
	@Override
	public int countByS_V(long structureId, String version) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_S_V;

		Object[] finderArgs = new Object[] { structureId, version };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DDMSTRUCTUREVERSION_WHERE);

			query.append(_FINDER_COLUMN_S_V_STRUCTUREID_2);

			boolean bindVersion = false;

			if (version == null) {
				query.append(_FINDER_COLUMN_S_V_VERSION_1);
			}
			else if (version.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_S_V_VERSION_3);
			}
			else {
				bindVersion = true;

				query.append(_FINDER_COLUMN_S_V_VERSION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(structureId);

				if (bindVersion) {
					qPos.add(version);
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

	private static final String _FINDER_COLUMN_S_V_STRUCTUREID_2 = "ddmStructureVersion.structureId = ? AND ";
	private static final String _FINDER_COLUMN_S_V_VERSION_1 = "ddmStructureVersion.version IS NULL";
	private static final String _FINDER_COLUMN_S_V_VERSION_2 = "ddmStructureVersion.version = ?";
	private static final String _FINDER_COLUMN_S_V_VERSION_3 = "(ddmStructureVersion.version IS NULL OR ddmStructureVersion.version = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_S_S = new FinderPath(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionModelImpl.FINDER_CACHE_ENABLED,
			DDMStructureVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S = new FinderPath(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionModelImpl.FINDER_CACHE_ENABLED,
			DDMStructureVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByS_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			DDMStructureVersionModelImpl.STRUCTUREID_COLUMN_BITMASK |
			DDMStructureVersionModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_S_S = new FinderPath(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the d d m structure versions where structureId = &#63; and status = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @return the matching d d m structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByS_S(long structureId, int status) {
		return findByS_S(structureId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the d d m structure versions where structureId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDMStructureVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param start the lower bound of the range of d d m structure versions
	 * @param end the upper bound of the range of d d m structure versions (not inclusive)
	 * @return the range of matching d d m structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByS_S(long structureId, int status,
		int start, int end) {
		return findByS_S(structureId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the d d m structure versions where structureId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDMStructureVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param start the lower bound of the range of d d m structure versions
	 * @param end the upper bound of the range of d d m structure versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching d d m structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByS_S(long structureId, int status,
		int start, int end,
		OrderByComparator<DDMStructureVersion> orderByComparator) {
		return findByS_S(structureId, status, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the d d m structure versions where structureId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDMStructureVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param start the lower bound of the range of d d m structure versions
	 * @param end the upper bound of the range of d d m structure versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching d d m structure versions
	 */
	@Override
	public List<DDMStructureVersion> findByS_S(long structureId, int status,
		int start, int end,
		OrderByComparator<DDMStructureVersion> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S;
			finderArgs = new Object[] { structureId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_S_S;
			finderArgs = new Object[] {
					structureId, status,
					
					start, end, orderByComparator
				};
		}

		List<DDMStructureVersion> list = null;

		if (retrieveFromCache) {
			list = (List<DDMStructureVersion>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DDMStructureVersion ddmStructureVersion : list) {
					if ((structureId != ddmStructureVersion.getStructureId()) ||
							(status != ddmStructureVersion.getStatus())) {
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

			query.append(_SQL_SELECT_DDMSTRUCTUREVERSION_WHERE);

			query.append(_FINDER_COLUMN_S_S_STRUCTUREID_2);

			query.append(_FINDER_COLUMN_S_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DDMStructureVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(structureId);

				qPos.add(status);

				if (!pagination) {
					list = (List<DDMStructureVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DDMStructureVersion>)QueryUtil.list(q,
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
	 * Returns the first d d m structure version in the ordered set where structureId = &#63; and status = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d m structure version
	 * @throws NoSuchStructureVersionException if a matching d d m structure version could not be found
	 */
	@Override
	public DDMStructureVersion findByS_S_First(long structureId, int status,
		OrderByComparator<DDMStructureVersion> orderByComparator)
		throws NoSuchStructureVersionException {
		DDMStructureVersion ddmStructureVersion = fetchByS_S_First(structureId,
				status, orderByComparator);

		if (ddmStructureVersion != null) {
			return ddmStructureVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("structureId=");
		msg.append(structureId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStructureVersionException(msg.toString());
	}

	/**
	 * Returns the first d d m structure version in the ordered set where structureId = &#63; and status = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d m structure version, or <code>null</code> if a matching d d m structure version could not be found
	 */
	@Override
	public DDMStructureVersion fetchByS_S_First(long structureId, int status,
		OrderByComparator<DDMStructureVersion> orderByComparator) {
		List<DDMStructureVersion> list = findByS_S(structureId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last d d m structure version in the ordered set where structureId = &#63; and status = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d m structure version
	 * @throws NoSuchStructureVersionException if a matching d d m structure version could not be found
	 */
	@Override
	public DDMStructureVersion findByS_S_Last(long structureId, int status,
		OrderByComparator<DDMStructureVersion> orderByComparator)
		throws NoSuchStructureVersionException {
		DDMStructureVersion ddmStructureVersion = fetchByS_S_Last(structureId,
				status, orderByComparator);

		if (ddmStructureVersion != null) {
			return ddmStructureVersion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("structureId=");
		msg.append(structureId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStructureVersionException(msg.toString());
	}

	/**
	 * Returns the last d d m structure version in the ordered set where structureId = &#63; and status = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d m structure version, or <code>null</code> if a matching d d m structure version could not be found
	 */
	@Override
	public DDMStructureVersion fetchByS_S_Last(long structureId, int status,
		OrderByComparator<DDMStructureVersion> orderByComparator) {
		int count = countByS_S(structureId, status);

		if (count == 0) {
			return null;
		}

		List<DDMStructureVersion> list = findByS_S(structureId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the d d m structure versions before and after the current d d m structure version in the ordered set where structureId = &#63; and status = &#63;.
	 *
	 * @param structureVersionId the primary key of the current d d m structure version
	 * @param structureId the structure ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next d d m structure version
	 * @throws NoSuchStructureVersionException if a d d m structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion[] findByS_S_PrevAndNext(
		long structureVersionId, long structureId, int status,
		OrderByComparator<DDMStructureVersion> orderByComparator)
		throws NoSuchStructureVersionException {
		DDMStructureVersion ddmStructureVersion = findByPrimaryKey(structureVersionId);

		Session session = null;

		try {
			session = openSession();

			DDMStructureVersion[] array = new DDMStructureVersionImpl[3];

			array[0] = getByS_S_PrevAndNext(session, ddmStructureVersion,
					structureId, status, orderByComparator, true);

			array[1] = ddmStructureVersion;

			array[2] = getByS_S_PrevAndNext(session, ddmStructureVersion,
					structureId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDMStructureVersion getByS_S_PrevAndNext(Session session,
		DDMStructureVersion ddmStructureVersion, long structureId, int status,
		OrderByComparator<DDMStructureVersion> orderByComparator,
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

		query.append(_SQL_SELECT_DDMSTRUCTUREVERSION_WHERE);

		query.append(_FINDER_COLUMN_S_S_STRUCTUREID_2);

		query.append(_FINDER_COLUMN_S_S_STATUS_2);

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
			query.append(DDMStructureVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(structureId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ddmStructureVersion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DDMStructureVersion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the d d m structure versions where structureId = &#63; and status = &#63; from the database.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 */
	@Override
	public void removeByS_S(long structureId, int status) {
		for (DDMStructureVersion ddmStructureVersion : findByS_S(structureId,
				status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ddmStructureVersion);
		}
	}

	/**
	 * Returns the number of d d m structure versions where structureId = &#63; and status = &#63;.
	 *
	 * @param structureId the structure ID
	 * @param status the status
	 * @return the number of matching d d m structure versions
	 */
	@Override
	public int countByS_S(long structureId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_S_S;

		Object[] finderArgs = new Object[] { structureId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DDMSTRUCTUREVERSION_WHERE);

			query.append(_FINDER_COLUMN_S_S_STRUCTUREID_2);

			query.append(_FINDER_COLUMN_S_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(structureId);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_S_S_STRUCTUREID_2 = "ddmStructureVersion.structureId = ? AND ";
	private static final String _FINDER_COLUMN_S_S_STATUS_2 = "ddmStructureVersion.status = ?";

	public DDMStructureVersionPersistenceImpl() {
		setModelClass(DDMStructureVersion.class);
	}

	/**
	 * Caches the d d m structure version in the entity cache if it is enabled.
	 *
	 * @param ddmStructureVersion the d d m structure version
	 */
	@Override
	public void cacheResult(DDMStructureVersion ddmStructureVersion) {
		entityCache.putResult(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionImpl.class, ddmStructureVersion.getPrimaryKey(),
			ddmStructureVersion);

		finderCache.putResult(FINDER_PATH_FETCH_BY_S_V,
			new Object[] {
				ddmStructureVersion.getStructureId(),
				ddmStructureVersion.getVersion()
			}, ddmStructureVersion);

		ddmStructureVersion.resetOriginalValues();
	}

	/**
	 * Caches the d d m structure versions in the entity cache if it is enabled.
	 *
	 * @param ddmStructureVersions the d d m structure versions
	 */
	@Override
	public void cacheResult(List<DDMStructureVersion> ddmStructureVersions) {
		for (DDMStructureVersion ddmStructureVersion : ddmStructureVersions) {
			if (entityCache.getResult(
						DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
						DDMStructureVersionImpl.class,
						ddmStructureVersion.getPrimaryKey()) == null) {
				cacheResult(ddmStructureVersion);
			}
			else {
				ddmStructureVersion.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all d d m structure versions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DDMStructureVersionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the d d m structure version.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DDMStructureVersion ddmStructureVersion) {
		entityCache.removeResult(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionImpl.class, ddmStructureVersion.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DDMStructureVersionModelImpl)ddmStructureVersion);
	}

	@Override
	public void clearCache(List<DDMStructureVersion> ddmStructureVersions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DDMStructureVersion ddmStructureVersion : ddmStructureVersions) {
			entityCache.removeResult(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
				DDMStructureVersionImpl.class,
				ddmStructureVersion.getPrimaryKey());

			clearUniqueFindersCache((DDMStructureVersionModelImpl)ddmStructureVersion);
		}
	}

	protected void cacheUniqueFindersCache(
		DDMStructureVersionModelImpl ddmStructureVersionModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					ddmStructureVersionModelImpl.getStructureId(),
					ddmStructureVersionModelImpl.getVersion()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_S_V, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_S_V, args,
				ddmStructureVersionModelImpl);
		}
		else {
			if ((ddmStructureVersionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_S_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ddmStructureVersionModelImpl.getStructureId(),
						ddmStructureVersionModelImpl.getVersion()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_S_V, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_S_V, args,
					ddmStructureVersionModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		DDMStructureVersionModelImpl ddmStructureVersionModelImpl) {
		Object[] args = new Object[] {
				ddmStructureVersionModelImpl.getStructureId(),
				ddmStructureVersionModelImpl.getVersion()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_S_V, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_S_V, args);

		if ((ddmStructureVersionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_S_V.getColumnBitmask()) != 0) {
			args = new Object[] {
					ddmStructureVersionModelImpl.getOriginalStructureId(),
					ddmStructureVersionModelImpl.getOriginalVersion()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_S_V, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_S_V, args);
		}
	}

	/**
	 * Creates a new d d m structure version with the primary key. Does not add the d d m structure version to the database.
	 *
	 * @param structureVersionId the primary key for the new d d m structure version
	 * @return the new d d m structure version
	 */
	@Override
	public DDMStructureVersion create(long structureVersionId) {
		DDMStructureVersion ddmStructureVersion = new DDMStructureVersionImpl();

		ddmStructureVersion.setNew(true);
		ddmStructureVersion.setPrimaryKey(structureVersionId);

		ddmStructureVersion.setCompanyId(companyProvider.getCompanyId());

		return ddmStructureVersion;
	}

	/**
	 * Removes the d d m structure version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param structureVersionId the primary key of the d d m structure version
	 * @return the d d m structure version that was removed
	 * @throws NoSuchStructureVersionException if a d d m structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion remove(long structureVersionId)
		throws NoSuchStructureVersionException {
		return remove((Serializable)structureVersionId);
	}

	/**
	 * Removes the d d m structure version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the d d m structure version
	 * @return the d d m structure version that was removed
	 * @throws NoSuchStructureVersionException if a d d m structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion remove(Serializable primaryKey)
		throws NoSuchStructureVersionException {
		Session session = null;

		try {
			session = openSession();

			DDMStructureVersion ddmStructureVersion = (DDMStructureVersion)session.get(DDMStructureVersionImpl.class,
					primaryKey);

			if (ddmStructureVersion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStructureVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ddmStructureVersion);
		}
		catch (NoSuchStructureVersionException nsee) {
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
	protected DDMStructureVersion removeImpl(
		DDMStructureVersion ddmStructureVersion) {
		ddmStructureVersion = toUnwrappedModel(ddmStructureVersion);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ddmStructureVersion)) {
				ddmStructureVersion = (DDMStructureVersion)session.get(DDMStructureVersionImpl.class,
						ddmStructureVersion.getPrimaryKeyObj());
			}

			if (ddmStructureVersion != null) {
				session.delete(ddmStructureVersion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ddmStructureVersion != null) {
			clearCache(ddmStructureVersion);
		}

		return ddmStructureVersion;
	}

	@Override
	public DDMStructureVersion updateImpl(
		DDMStructureVersion ddmStructureVersion) {
		ddmStructureVersion = toUnwrappedModel(ddmStructureVersion);

		boolean isNew = ddmStructureVersion.isNew();

		DDMStructureVersionModelImpl ddmStructureVersionModelImpl = (DDMStructureVersionModelImpl)ddmStructureVersion;

		Session session = null;

		try {
			session = openSession();

			if (ddmStructureVersion.isNew()) {
				session.save(ddmStructureVersion);

				ddmStructureVersion.setNew(false);
			}
			else {
				ddmStructureVersion = (DDMStructureVersion)session.merge(ddmStructureVersion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DDMStructureVersionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ddmStructureVersionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STRUCTUREID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ddmStructureVersionModelImpl.getOriginalStructureId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STRUCTUREID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STRUCTUREID,
					args);

				args = new Object[] {
						ddmStructureVersionModelImpl.getStructureId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STRUCTUREID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STRUCTUREID,
					args);
			}

			if ((ddmStructureVersionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ddmStructureVersionModelImpl.getOriginalStructureId(),
						ddmStructureVersionModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S,
					args);

				args = new Object[] {
						ddmStructureVersionModelImpl.getStructureId(),
						ddmStructureVersionModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S,
					args);
			}
		}

		entityCache.putResult(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
			DDMStructureVersionImpl.class, ddmStructureVersion.getPrimaryKey(),
			ddmStructureVersion, false);

		clearUniqueFindersCache(ddmStructureVersionModelImpl);
		cacheUniqueFindersCache(ddmStructureVersionModelImpl, isNew);

		ddmStructureVersion.resetOriginalValues();

		return ddmStructureVersion;
	}

	protected DDMStructureVersion toUnwrappedModel(
		DDMStructureVersion ddmStructureVersion) {
		if (ddmStructureVersion instanceof DDMStructureVersionImpl) {
			return ddmStructureVersion;
		}

		DDMStructureVersionImpl ddmStructureVersionImpl = new DDMStructureVersionImpl();

		ddmStructureVersionImpl.setNew(ddmStructureVersion.isNew());
		ddmStructureVersionImpl.setPrimaryKey(ddmStructureVersion.getPrimaryKey());

		ddmStructureVersionImpl.setStructureVersionId(ddmStructureVersion.getStructureVersionId());
		ddmStructureVersionImpl.setGroupId(ddmStructureVersion.getGroupId());
		ddmStructureVersionImpl.setCompanyId(ddmStructureVersion.getCompanyId());
		ddmStructureVersionImpl.setUserId(ddmStructureVersion.getUserId());
		ddmStructureVersionImpl.setUserName(ddmStructureVersion.getUserName());
		ddmStructureVersionImpl.setCreateDate(ddmStructureVersion.getCreateDate());
		ddmStructureVersionImpl.setStructureId(ddmStructureVersion.getStructureId());
		ddmStructureVersionImpl.setVersion(ddmStructureVersion.getVersion());
		ddmStructureVersionImpl.setParentStructureId(ddmStructureVersion.getParentStructureId());
		ddmStructureVersionImpl.setName(ddmStructureVersion.getName());
		ddmStructureVersionImpl.setDescription(ddmStructureVersion.getDescription());
		ddmStructureVersionImpl.setDefinition(ddmStructureVersion.getDefinition());
		ddmStructureVersionImpl.setStorageType(ddmStructureVersion.getStorageType());
		ddmStructureVersionImpl.setType(ddmStructureVersion.getType());
		ddmStructureVersionImpl.setStatus(ddmStructureVersion.getStatus());
		ddmStructureVersionImpl.setStatusByUserId(ddmStructureVersion.getStatusByUserId());
		ddmStructureVersionImpl.setStatusByUserName(ddmStructureVersion.getStatusByUserName());
		ddmStructureVersionImpl.setStatusDate(ddmStructureVersion.getStatusDate());

		return ddmStructureVersionImpl;
	}

	/**
	 * Returns the d d m structure version with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the d d m structure version
	 * @return the d d m structure version
	 * @throws NoSuchStructureVersionException if a d d m structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStructureVersionException {
		DDMStructureVersion ddmStructureVersion = fetchByPrimaryKey(primaryKey);

		if (ddmStructureVersion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStructureVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ddmStructureVersion;
	}

	/**
	 * Returns the d d m structure version with the primary key or throws a {@link NoSuchStructureVersionException} if it could not be found.
	 *
	 * @param structureVersionId the primary key of the d d m structure version
	 * @return the d d m structure version
	 * @throws NoSuchStructureVersionException if a d d m structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion findByPrimaryKey(long structureVersionId)
		throws NoSuchStructureVersionException {
		return findByPrimaryKey((Serializable)structureVersionId);
	}

	/**
	 * Returns the d d m structure version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the d d m structure version
	 * @return the d d m structure version, or <code>null</code> if a d d m structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
				DDMStructureVersionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DDMStructureVersion ddmStructureVersion = (DDMStructureVersion)serializable;

		if (ddmStructureVersion == null) {
			Session session = null;

			try {
				session = openSession();

				ddmStructureVersion = (DDMStructureVersion)session.get(DDMStructureVersionImpl.class,
						primaryKey);

				if (ddmStructureVersion != null) {
					cacheResult(ddmStructureVersion);
				}
				else {
					entityCache.putResult(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
						DDMStructureVersionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
					DDMStructureVersionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ddmStructureVersion;
	}

	/**
	 * Returns the d d m structure version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param structureVersionId the primary key of the d d m structure version
	 * @return the d d m structure version, or <code>null</code> if a d d m structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion fetchByPrimaryKey(long structureVersionId) {
		return fetchByPrimaryKey((Serializable)structureVersionId);
	}

	@Override
	public Map<Serializable, DDMStructureVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DDMStructureVersion> map = new HashMap<Serializable, DDMStructureVersion>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DDMStructureVersion ddmStructureVersion = fetchByPrimaryKey(primaryKey);

			if (ddmStructureVersion != null) {
				map.put(primaryKey, ddmStructureVersion);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
					DDMStructureVersionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DDMStructureVersion)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DDMSTRUCTUREVERSION_WHERE_PKS_IN);

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

			for (DDMStructureVersion ddmStructureVersion : (List<DDMStructureVersion>)q.list()) {
				map.put(ddmStructureVersion.getPrimaryKeyObj(),
					ddmStructureVersion);

				cacheResult(ddmStructureVersion);

				uncachedPrimaryKeys.remove(ddmStructureVersion.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DDMStructureVersionModelImpl.ENTITY_CACHE_ENABLED,
					DDMStructureVersionImpl.class, primaryKey, nullModel);
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
	 * Returns all the d d m structure versions.
	 *
	 * @return the d d m structure versions
	 */
	@Override
	public List<DDMStructureVersion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the d d m structure versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDMStructureVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d m structure versions
	 * @param end the upper bound of the range of d d m structure versions (not inclusive)
	 * @return the range of d d m structure versions
	 */
	@Override
	public List<DDMStructureVersion> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the d d m structure versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDMStructureVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d m structure versions
	 * @param end the upper bound of the range of d d m structure versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of d d m structure versions
	 */
	@Override
	public List<DDMStructureVersion> findAll(int start, int end,
		OrderByComparator<DDMStructureVersion> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the d d m structure versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DDMStructureVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d m structure versions
	 * @param end the upper bound of the range of d d m structure versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of d d m structure versions
	 */
	@Override
	public List<DDMStructureVersion> findAll(int start, int end,
		OrderByComparator<DDMStructureVersion> orderByComparator,
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

		List<DDMStructureVersion> list = null;

		if (retrieveFromCache) {
			list = (List<DDMStructureVersion>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DDMSTRUCTUREVERSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DDMSTRUCTUREVERSION;

				if (pagination) {
					sql = sql.concat(DDMStructureVersionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DDMStructureVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DDMStructureVersion>)QueryUtil.list(q,
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
	 * Removes all the d d m structure versions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DDMStructureVersion ddmStructureVersion : findAll()) {
			remove(ddmStructureVersion);
		}
	}

	/**
	 * Returns the number of d d m structure versions.
	 *
	 * @return the number of d d m structure versions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DDMSTRUCTUREVERSION);

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
		return DDMStructureVersionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the d d m structure version persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DDMStructureVersionImpl.class.getName());
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
	private static final String _SQL_SELECT_DDMSTRUCTUREVERSION = "SELECT ddmStructureVersion FROM DDMStructureVersion ddmStructureVersion";
	private static final String _SQL_SELECT_DDMSTRUCTUREVERSION_WHERE_PKS_IN = "SELECT ddmStructureVersion FROM DDMStructureVersion ddmStructureVersion WHERE structureVersionId IN (";
	private static final String _SQL_SELECT_DDMSTRUCTUREVERSION_WHERE = "SELECT ddmStructureVersion FROM DDMStructureVersion ddmStructureVersion WHERE ";
	private static final String _SQL_COUNT_DDMSTRUCTUREVERSION = "SELECT COUNT(ddmStructureVersion) FROM DDMStructureVersion ddmStructureVersion";
	private static final String _SQL_COUNT_DDMSTRUCTUREVERSION_WHERE = "SELECT COUNT(ddmStructureVersion) FROM DDMStructureVersion ddmStructureVersion WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ddmStructureVersion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DDMStructureVersion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DDMStructureVersion exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DDMStructureVersionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}