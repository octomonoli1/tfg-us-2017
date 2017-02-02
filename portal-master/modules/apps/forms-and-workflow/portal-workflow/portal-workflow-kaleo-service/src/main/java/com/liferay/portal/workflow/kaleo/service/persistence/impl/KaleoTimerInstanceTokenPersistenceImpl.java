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

package com.liferay.portal.workflow.kaleo.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.workflow.kaleo.exception.NoSuchTimerInstanceTokenException;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTimerInstanceTokenPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the kaleo timer instance token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerInstanceTokenPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.KaleoTimerInstanceTokenUtil
 * @generated
 */
@ProviderType
public class KaleoTimerInstanceTokenPersistenceImpl extends BasePersistenceImpl<KaleoTimerInstanceToken>
	implements KaleoTimerInstanceTokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoTimerInstanceTokenUtil} to access the kaleo timer instance token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTimerInstanceTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOINSTANCEID =
		new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoInstanceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID =
		new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKaleoInstanceId",
			new String[] { Long.class.getName() },
			KaleoTimerInstanceTokenModelImpl.KALEOINSTANCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOINSTANCEID = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoInstanceId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo timer instance tokens where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the matching kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId) {
		return findByKaleoInstanceId(kaleoInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timer instance tokens where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @return the range of matching kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end) {
		return findByKaleoInstanceId(kaleoInstanceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timer instance tokens where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator) {
		return findByKaleoInstanceId(kaleoInstanceId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo timer instance tokens where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID;
			finderArgs = new Object[] { kaleoInstanceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOINSTANCEID;
			finderArgs = new Object[] {
					kaleoInstanceId,
					
					start, end, orderByComparator
				};
		}

		List<KaleoTimerInstanceToken> list = null;

		if (retrieveFromCache) {
			list = (List<KaleoTimerInstanceToken>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (KaleoTimerInstanceToken kaleoTimerInstanceToken : list) {
					if ((kaleoInstanceId != kaleoTimerInstanceToken.getKaleoInstanceId())) {
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

			query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				if (!pagination) {
					list = (List<KaleoTimerInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTimerInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo timer instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer instance token
	 * @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws NoSuchTimerInstanceTokenException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = fetchByKaleoInstanceId_First(kaleoInstanceId,
				orderByComparator);

		if (kaleoTimerInstanceToken != null) {
			return kaleoTimerInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceId=");
		msg.append(kaleoInstanceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimerInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo timer instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken fetchByKaleoInstanceId_First(
		long kaleoInstanceId,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator) {
		List<KaleoTimerInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo timer instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer instance token
	 * @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken findByKaleoInstanceId_Last(
		long kaleoInstanceId,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws NoSuchTimerInstanceTokenException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = fetchByKaleoInstanceId_Last(kaleoInstanceId,
				orderByComparator);

		if (kaleoTimerInstanceToken != null) {
			return kaleoTimerInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceId=");
		msg.append(kaleoInstanceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimerInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo timer instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken fetchByKaleoInstanceId_Last(
		long kaleoInstanceId,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator) {
		int count = countByKaleoInstanceId(kaleoInstanceId);

		if (count == 0) {
			return null;
		}

		List<KaleoTimerInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo timer instance tokens before and after the current kaleo timer instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the current kaleo timer instance token
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo timer instance token
	 * @throws NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	 */
	@Override
	public KaleoTimerInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTimerInstanceTokenId, long kaleoInstanceId,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws NoSuchTimerInstanceTokenException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = findByPrimaryKey(kaleoTimerInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTimerInstanceToken[] array = new KaleoTimerInstanceTokenImpl[3];

			array[0] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTimerInstanceToken, kaleoInstanceId,
					orderByComparator, true);

			array[1] = kaleoTimerInstanceToken;

			array[2] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTimerInstanceToken, kaleoInstanceId,
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

	protected KaleoTimerInstanceToken getByKaleoInstanceId_PrevAndNext(
		Session session, KaleoTimerInstanceToken kaleoTimerInstanceToken,
		long kaleoInstanceId,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator,
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

		query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

		query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

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
			query.append(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTimerInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTimerInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo timer instance tokens where kaleoInstanceId = &#63; from the database.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 */
	@Override
	public void removeByKaleoInstanceId(long kaleoInstanceId) {
		for (KaleoTimerInstanceToken kaleoTimerInstanceToken : findByKaleoInstanceId(
				kaleoInstanceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoTimerInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo timer instance tokens where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the number of matching kaleo timer instance tokens
	 */
	@Override
	public int countByKaleoInstanceId(long kaleoInstanceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEOINSTANCEID;

		Object[] finderArgs = new Object[] { kaleoInstanceId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

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

	private static final String _FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2 =
		"kaleoTimerInstanceToken.kaleoInstanceId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_KITI_KTI = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByKITI_KTI",
			new String[] { Long.class.getName(), Long.class.getName() },
			KaleoTimerInstanceTokenModelImpl.KALEOINSTANCETOKENID_COLUMN_BITMASK |
			KaleoTimerInstanceTokenModelImpl.KALEOTIMERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KITI_KTI = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKITI_KTI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the kaleo timer instance token where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63; or throws a {@link NoSuchTimerInstanceTokenException} if it could not be found.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param kaleoTimerId the kaleo timer ID
	 * @return the matching kaleo timer instance token
	 * @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken findByKITI_KTI(long kaleoInstanceTokenId,
		long kaleoTimerId) throws NoSuchTimerInstanceTokenException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = fetchByKITI_KTI(kaleoInstanceTokenId,
				kaleoTimerId);

		if (kaleoTimerInstanceToken == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", kaleoTimerId=");
			msg.append(kaleoTimerId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTimerInstanceTokenException(msg.toString());
		}

		return kaleoTimerInstanceToken;
	}

	/**
	 * Returns the kaleo timer instance token where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param kaleoTimerId the kaleo timer ID
	 * @return the matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken fetchByKITI_KTI(long kaleoInstanceTokenId,
		long kaleoTimerId) {
		return fetchByKITI_KTI(kaleoInstanceTokenId, kaleoTimerId, true);
	}

	/**
	 * Returns the kaleo timer instance token where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param kaleoTimerId the kaleo timer ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken fetchByKITI_KTI(long kaleoInstanceTokenId,
		long kaleoTimerId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { kaleoInstanceTokenId, kaleoTimerId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_KITI_KTI,
					finderArgs, this);
		}

		if (result instanceof KaleoTimerInstanceToken) {
			KaleoTimerInstanceToken kaleoTimerInstanceToken = (KaleoTimerInstanceToken)result;

			if ((kaleoInstanceTokenId != kaleoTimerInstanceToken.getKaleoInstanceTokenId()) ||
					(kaleoTimerId != kaleoTimerInstanceToken.getKaleoTimerId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KITI_KTI_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_KTI_KALEOTIMERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(kaleoTimerId);

				List<KaleoTimerInstanceToken> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_KITI_KTI,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"KaleoTimerInstanceTokenPersistenceImpl.fetchByKITI_KTI(long, long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					KaleoTimerInstanceToken kaleoTimerInstanceToken = list.get(0);

					result = kaleoTimerInstanceToken;

					cacheResult(kaleoTimerInstanceToken);

					if ((kaleoTimerInstanceToken.getKaleoInstanceTokenId() != kaleoInstanceTokenId) ||
							(kaleoTimerInstanceToken.getKaleoTimerId() != kaleoTimerId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_KITI_KTI,
							finderArgs, kaleoTimerInstanceToken);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_KITI_KTI,
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
			return (KaleoTimerInstanceToken)result;
		}
	}

	/**
	 * Removes the kaleo timer instance token where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63; from the database.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param kaleoTimerId the kaleo timer ID
	 * @return the kaleo timer instance token that was removed
	 */
	@Override
	public KaleoTimerInstanceToken removeByKITI_KTI(long kaleoInstanceTokenId,
		long kaleoTimerId) throws NoSuchTimerInstanceTokenException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = findByKITI_KTI(kaleoInstanceTokenId,
				kaleoTimerId);

		return remove(kaleoTimerInstanceToken);
	}

	/**
	 * Returns the number of kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param kaleoTimerId the kaleo timer ID
	 * @return the number of matching kaleo timer instance tokens
	 */
	@Override
	public int countByKITI_KTI(long kaleoInstanceTokenId, long kaleoTimerId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KITI_KTI;

		Object[] finderArgs = new Object[] { kaleoInstanceTokenId, kaleoTimerId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KITI_KTI_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_KTI_KALEOTIMERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(kaleoTimerId);

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

	private static final String _FINDER_COLUMN_KITI_KTI_KALEOINSTANCETOKENID_2 = "kaleoTimerInstanceToken.kaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_KITI_KTI_KALEOTIMERID_2 = "kaleoTimerInstanceToken.kaleoTimerId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KITI_C = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKITI_C",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_C =
		new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKITI_C",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KaleoTimerInstanceTokenModelImpl.KALEOINSTANCETOKENID_COLUMN_BITMASK |
			KaleoTimerInstanceTokenModelImpl.COMPLETED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KITI_C = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKITI_C",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @return the matching kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findByKITI_C(
		long kaleoInstanceTokenId, boolean completed) {
		return findByKITI_C(kaleoInstanceTokenId, completed, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @return the range of matching kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findByKITI_C(
		long kaleoInstanceTokenId, boolean completed, int start, int end) {
		return findByKITI_C(kaleoInstanceTokenId, completed, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findByKITI_C(
		long kaleoInstanceTokenId, boolean completed, int start, int end,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator) {
		return findByKITI_C(kaleoInstanceTokenId, completed, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findByKITI_C(
		long kaleoInstanceTokenId, boolean completed, int start, int end,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_C;
			finderArgs = new Object[] { kaleoInstanceTokenId, completed };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KITI_C;
			finderArgs = new Object[] {
					kaleoInstanceTokenId, completed,
					
					start, end, orderByComparator
				};
		}

		List<KaleoTimerInstanceToken> list = null;

		if (retrieveFromCache) {
			list = (List<KaleoTimerInstanceToken>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (KaleoTimerInstanceToken kaleoTimerInstanceToken : list) {
					if ((kaleoInstanceTokenId != kaleoTimerInstanceToken.getKaleoInstanceTokenId()) ||
							(completed != kaleoTimerInstanceToken.getCompleted())) {
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

			query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KITI_C_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_C_COMPLETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(completed);

				if (!pagination) {
					list = (List<KaleoTimerInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTimerInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer instance token
	 * @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken findByKITI_C_First(
		long kaleoInstanceTokenId, boolean completed,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws NoSuchTimerInstanceTokenException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = fetchByKITI_C_First(kaleoInstanceTokenId,
				completed, orderByComparator);

		if (kaleoTimerInstanceToken != null) {
			return kaleoTimerInstanceToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceTokenId=");
		msg.append(kaleoInstanceTokenId);

		msg.append(", completed=");
		msg.append(completed);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimerInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken fetchByKITI_C_First(
		long kaleoInstanceTokenId, boolean completed,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator) {
		List<KaleoTimerInstanceToken> list = findByKITI_C(kaleoInstanceTokenId,
				completed, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer instance token
	 * @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken findByKITI_C_Last(
		long kaleoInstanceTokenId, boolean completed,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws NoSuchTimerInstanceTokenException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = fetchByKITI_C_Last(kaleoInstanceTokenId,
				completed, orderByComparator);

		if (kaleoTimerInstanceToken != null) {
			return kaleoTimerInstanceToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceTokenId=");
		msg.append(kaleoInstanceTokenId);

		msg.append(", completed=");
		msg.append(completed);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimerInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken fetchByKITI_C_Last(
		long kaleoInstanceTokenId, boolean completed,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator) {
		int count = countByKITI_C(kaleoInstanceTokenId, completed);

		if (count == 0) {
			return null;
		}

		List<KaleoTimerInstanceToken> list = findByKITI_C(kaleoInstanceTokenId,
				completed, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo timer instance tokens before and after the current kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the current kaleo timer instance token
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo timer instance token
	 * @throws NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	 */
	@Override
	public KaleoTimerInstanceToken[] findByKITI_C_PrevAndNext(
		long kaleoTimerInstanceTokenId, long kaleoInstanceTokenId,
		boolean completed,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws NoSuchTimerInstanceTokenException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = findByPrimaryKey(kaleoTimerInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTimerInstanceToken[] array = new KaleoTimerInstanceTokenImpl[3];

			array[0] = getByKITI_C_PrevAndNext(session,
					kaleoTimerInstanceToken, kaleoInstanceTokenId, completed,
					orderByComparator, true);

			array[1] = kaleoTimerInstanceToken;

			array[2] = getByKITI_C_PrevAndNext(session,
					kaleoTimerInstanceToken, kaleoInstanceTokenId, completed,
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

	protected KaleoTimerInstanceToken getByKITI_C_PrevAndNext(Session session,
		KaleoTimerInstanceToken kaleoTimerInstanceToken,
		long kaleoInstanceTokenId, boolean completed,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator,
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

		query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

		query.append(_FINDER_COLUMN_KITI_C_KALEOINSTANCETOKENID_2);

		query.append(_FINDER_COLUMN_KITI_C_COMPLETED_2);

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
			query.append(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceTokenId);

		qPos.add(completed);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTimerInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTimerInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; from the database.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 */
	@Override
	public void removeByKITI_C(long kaleoInstanceTokenId, boolean completed) {
		for (KaleoTimerInstanceToken kaleoTimerInstanceToken : findByKITI_C(
				kaleoInstanceTokenId, completed, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kaleoTimerInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @return the number of matching kaleo timer instance tokens
	 */
	@Override
	public int countByKITI_C(long kaleoInstanceTokenId, boolean completed) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KITI_C;

		Object[] finderArgs = new Object[] { kaleoInstanceTokenId, completed };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KITI_C_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_C_COMPLETED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(completed);

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

	private static final String _FINDER_COLUMN_KITI_C_KALEOINSTANCETOKENID_2 = "kaleoTimerInstanceToken.kaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_KITI_C_COMPLETED_2 = "kaleoTimerInstanceToken.completed = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KITI_C_B = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKITI_C_B",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_C_B =
		new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKITI_C_B",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			KaleoTimerInstanceTokenModelImpl.KALEOINSTANCETOKENID_COLUMN_BITMASK |
			KaleoTimerInstanceTokenModelImpl.COMPLETED_COLUMN_BITMASK |
			KaleoTimerInstanceTokenModelImpl.BLOCKING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KITI_C_B = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKITI_C_B",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @return the matching kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findByKITI_C_B(
		long kaleoInstanceTokenId, boolean completed, boolean blocking) {
		return findByKITI_C_B(kaleoInstanceTokenId, completed, blocking,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @return the range of matching kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findByKITI_C_B(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		int start, int end) {
		return findByKITI_C_B(kaleoInstanceTokenId, completed, blocking, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findByKITI_C_B(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		int start, int end,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator) {
		return findByKITI_C_B(kaleoInstanceTokenId, completed, blocking, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findByKITI_C_B(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		int start, int end,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_C_B;
			finderArgs = new Object[] { kaleoInstanceTokenId, completed, blocking };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KITI_C_B;
			finderArgs = new Object[] {
					kaleoInstanceTokenId, completed, blocking,
					
					start, end, orderByComparator
				};
		}

		List<KaleoTimerInstanceToken> list = null;

		if (retrieveFromCache) {
			list = (List<KaleoTimerInstanceToken>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (KaleoTimerInstanceToken kaleoTimerInstanceToken : list) {
					if ((kaleoInstanceTokenId != kaleoTimerInstanceToken.getKaleoInstanceTokenId()) ||
							(completed != kaleoTimerInstanceToken.getCompleted()) ||
							(blocking != kaleoTimerInstanceToken.getBlocking())) {
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

			query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KITI_C_B_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_C_B_COMPLETED_2);

			query.append(_FINDER_COLUMN_KITI_C_B_BLOCKING_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(completed);

				qPos.add(blocking);

				if (!pagination) {
					list = (List<KaleoTimerInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTimerInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer instance token
	 * @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken findByKITI_C_B_First(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws NoSuchTimerInstanceTokenException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = fetchByKITI_C_B_First(kaleoInstanceTokenId,
				completed, blocking, orderByComparator);

		if (kaleoTimerInstanceToken != null) {
			return kaleoTimerInstanceToken;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceTokenId=");
		msg.append(kaleoInstanceTokenId);

		msg.append(", completed=");
		msg.append(completed);

		msg.append(", blocking=");
		msg.append(blocking);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimerInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken fetchByKITI_C_B_First(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator) {
		List<KaleoTimerInstanceToken> list = findByKITI_C_B(kaleoInstanceTokenId,
				completed, blocking, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer instance token
	 * @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken findByKITI_C_B_Last(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws NoSuchTimerInstanceTokenException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = fetchByKITI_C_B_Last(kaleoInstanceTokenId,
				completed, blocking, orderByComparator);

		if (kaleoTimerInstanceToken != null) {
			return kaleoTimerInstanceToken;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceTokenId=");
		msg.append(kaleoInstanceTokenId);

		msg.append(", completed=");
		msg.append(completed);

		msg.append(", blocking=");
		msg.append(blocking);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimerInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	 */
	@Override
	public KaleoTimerInstanceToken fetchByKITI_C_B_Last(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator) {
		int count = countByKITI_C_B(kaleoInstanceTokenId, completed, blocking);

		if (count == 0) {
			return null;
		}

		List<KaleoTimerInstanceToken> list = findByKITI_C_B(kaleoInstanceTokenId,
				completed, blocking, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo timer instance tokens before and after the current kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the current kaleo timer instance token
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo timer instance token
	 * @throws NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	 */
	@Override
	public KaleoTimerInstanceToken[] findByKITI_C_B_PrevAndNext(
		long kaleoTimerInstanceTokenId, long kaleoInstanceTokenId,
		boolean completed, boolean blocking,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws NoSuchTimerInstanceTokenException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = findByPrimaryKey(kaleoTimerInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTimerInstanceToken[] array = new KaleoTimerInstanceTokenImpl[3];

			array[0] = getByKITI_C_B_PrevAndNext(session,
					kaleoTimerInstanceToken, kaleoInstanceTokenId, completed,
					blocking, orderByComparator, true);

			array[1] = kaleoTimerInstanceToken;

			array[2] = getByKITI_C_B_PrevAndNext(session,
					kaleoTimerInstanceToken, kaleoInstanceTokenId, completed,
					blocking, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTimerInstanceToken getByKITI_C_B_PrevAndNext(
		Session session, KaleoTimerInstanceToken kaleoTimerInstanceToken,
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator,
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

		query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

		query.append(_FINDER_COLUMN_KITI_C_B_KALEOINSTANCETOKENID_2);

		query.append(_FINDER_COLUMN_KITI_C_B_COMPLETED_2);

		query.append(_FINDER_COLUMN_KITI_C_B_BLOCKING_2);

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
			query.append(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceTokenId);

		qPos.add(completed);

		qPos.add(blocking);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTimerInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTimerInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63; from the database.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 */
	@Override
	public void removeByKITI_C_B(long kaleoInstanceTokenId, boolean completed,
		boolean blocking) {
		for (KaleoTimerInstanceToken kaleoTimerInstanceToken : findByKITI_C_B(
				kaleoInstanceTokenId, completed, blocking, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kaleoTimerInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @return the number of matching kaleo timer instance tokens
	 */
	@Override
	public int countByKITI_C_B(long kaleoInstanceTokenId, boolean completed,
		boolean blocking) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KITI_C_B;

		Object[] finderArgs = new Object[] {
				kaleoInstanceTokenId, completed, blocking
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KITI_C_B_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_C_B_COMPLETED_2);

			query.append(_FINDER_COLUMN_KITI_C_B_BLOCKING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(completed);

				qPos.add(blocking);

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

	private static final String _FINDER_COLUMN_KITI_C_B_KALEOINSTANCETOKENID_2 = "kaleoTimerInstanceToken.kaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_KITI_C_B_COMPLETED_2 = "kaleoTimerInstanceToken.completed = ? AND ";
	private static final String _FINDER_COLUMN_KITI_C_B_BLOCKING_2 = "kaleoTimerInstanceToken.blocking = ?";

	public KaleoTimerInstanceTokenPersistenceImpl() {
		setModelClass(KaleoTimerInstanceToken.class);
	}

	/**
	 * Caches the kaleo timer instance token in the entity cache if it is enabled.
	 *
	 * @param kaleoTimerInstanceToken the kaleo timer instance token
	 */
	@Override
	public void cacheResult(KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		entityCache.putResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			kaleoTimerInstanceToken.getPrimaryKey(), kaleoTimerInstanceToken);

		finderCache.putResult(FINDER_PATH_FETCH_BY_KITI_KTI,
			new Object[] {
				kaleoTimerInstanceToken.getKaleoInstanceTokenId(),
				kaleoTimerInstanceToken.getKaleoTimerId()
			}, kaleoTimerInstanceToken);

		kaleoTimerInstanceToken.resetOriginalValues();
	}

	/**
	 * Caches the kaleo timer instance tokens in the entity cache if it is enabled.
	 *
	 * @param kaleoTimerInstanceTokens the kaleo timer instance tokens
	 */
	@Override
	public void cacheResult(
		List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens) {
		for (KaleoTimerInstanceToken kaleoTimerInstanceToken : kaleoTimerInstanceTokens) {
			if (entityCache.getResult(
						KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTimerInstanceTokenImpl.class,
						kaleoTimerInstanceToken.getPrimaryKey()) == null) {
				cacheResult(kaleoTimerInstanceToken);
			}
			else {
				kaleoTimerInstanceToken.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo timer instance tokens.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(KaleoTimerInstanceTokenImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo timer instance token.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		entityCache.removeResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			kaleoTimerInstanceToken.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((KaleoTimerInstanceTokenModelImpl)kaleoTimerInstanceToken);
	}

	@Override
	public void clearCache(
		List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoTimerInstanceToken kaleoTimerInstanceToken : kaleoTimerInstanceTokens) {
			entityCache.removeResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTimerInstanceTokenImpl.class,
				kaleoTimerInstanceToken.getPrimaryKey());

			clearUniqueFindersCache((KaleoTimerInstanceTokenModelImpl)kaleoTimerInstanceToken);
		}
	}

	protected void cacheUniqueFindersCache(
		KaleoTimerInstanceTokenModelImpl kaleoTimerInstanceTokenModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					kaleoTimerInstanceTokenModelImpl.getKaleoInstanceTokenId(),
					kaleoTimerInstanceTokenModelImpl.getKaleoTimerId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_KITI_KTI, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_KITI_KTI, args,
				kaleoTimerInstanceTokenModelImpl);
		}
		else {
			if ((kaleoTimerInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_KITI_KTI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTimerInstanceTokenModelImpl.getKaleoInstanceTokenId(),
						kaleoTimerInstanceTokenModelImpl.getKaleoTimerId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_KITI_KTI, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_KITI_KTI, args,
					kaleoTimerInstanceTokenModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		KaleoTimerInstanceTokenModelImpl kaleoTimerInstanceTokenModelImpl) {
		Object[] args = new Object[] {
				kaleoTimerInstanceTokenModelImpl.getKaleoInstanceTokenId(),
				kaleoTimerInstanceTokenModelImpl.getKaleoTimerId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_KITI_KTI, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_KITI_KTI, args);

		if ((kaleoTimerInstanceTokenModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_KITI_KTI.getColumnBitmask()) != 0) {
			args = new Object[] {
					kaleoTimerInstanceTokenModelImpl.getOriginalKaleoInstanceTokenId(),
					kaleoTimerInstanceTokenModelImpl.getOriginalKaleoTimerId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_KITI_KTI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_KITI_KTI, args);
		}
	}

	/**
	 * Creates a new kaleo timer instance token with the primary key. Does not add the kaleo timer instance token to the database.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key for the new kaleo timer instance token
	 * @return the new kaleo timer instance token
	 */
	@Override
	public KaleoTimerInstanceToken create(long kaleoTimerInstanceTokenId) {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = new KaleoTimerInstanceTokenImpl();

		kaleoTimerInstanceToken.setNew(true);
		kaleoTimerInstanceToken.setPrimaryKey(kaleoTimerInstanceTokenId);

		kaleoTimerInstanceToken.setCompanyId(companyProvider.getCompanyId());

		return kaleoTimerInstanceToken;
	}

	/**
	 * Removes the kaleo timer instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token that was removed
	 * @throws NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	 */
	@Override
	public KaleoTimerInstanceToken remove(long kaleoTimerInstanceTokenId)
		throws NoSuchTimerInstanceTokenException {
		return remove((Serializable)kaleoTimerInstanceTokenId);
	}

	/**
	 * Removes the kaleo timer instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token that was removed
	 * @throws NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	 */
	@Override
	public KaleoTimerInstanceToken remove(Serializable primaryKey)
		throws NoSuchTimerInstanceTokenException {
		Session session = null;

		try {
			session = openSession();

			KaleoTimerInstanceToken kaleoTimerInstanceToken = (KaleoTimerInstanceToken)session.get(KaleoTimerInstanceTokenImpl.class,
					primaryKey);

			if (kaleoTimerInstanceToken == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTimerInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoTimerInstanceToken);
		}
		catch (NoSuchTimerInstanceTokenException nsee) {
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
	protected KaleoTimerInstanceToken removeImpl(
		KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		kaleoTimerInstanceToken = toUnwrappedModel(kaleoTimerInstanceToken);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoTimerInstanceToken)) {
				kaleoTimerInstanceToken = (KaleoTimerInstanceToken)session.get(KaleoTimerInstanceTokenImpl.class,
						kaleoTimerInstanceToken.getPrimaryKeyObj());
			}

			if (kaleoTimerInstanceToken != null) {
				session.delete(kaleoTimerInstanceToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoTimerInstanceToken != null) {
			clearCache(kaleoTimerInstanceToken);
		}

		return kaleoTimerInstanceToken;
	}

	@Override
	public KaleoTimerInstanceToken updateImpl(
		KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		kaleoTimerInstanceToken = toUnwrappedModel(kaleoTimerInstanceToken);

		boolean isNew = kaleoTimerInstanceToken.isNew();

		KaleoTimerInstanceTokenModelImpl kaleoTimerInstanceTokenModelImpl = (KaleoTimerInstanceTokenModelImpl)kaleoTimerInstanceToken;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (kaleoTimerInstanceToken.getCreateDate() == null)) {
			if (serviceContext == null) {
				kaleoTimerInstanceToken.setCreateDate(now);
			}
			else {
				kaleoTimerInstanceToken.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!kaleoTimerInstanceTokenModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kaleoTimerInstanceToken.setModifiedDate(now);
			}
			else {
				kaleoTimerInstanceToken.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (kaleoTimerInstanceToken.isNew()) {
				session.save(kaleoTimerInstanceToken);

				kaleoTimerInstanceToken.setNew(false);
			}
			else {
				kaleoTimerInstanceToken = (KaleoTimerInstanceToken)session.merge(kaleoTimerInstanceToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoTimerInstanceTokenModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoTimerInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTimerInstanceTokenModelImpl.getOriginalKaleoInstanceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID,
					args);

				args = new Object[] {
						kaleoTimerInstanceTokenModelImpl.getKaleoInstanceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID,
					args);
			}

			if ((kaleoTimerInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTimerInstanceTokenModelImpl.getOriginalKaleoInstanceTokenId(),
						kaleoTimerInstanceTokenModelImpl.getOriginalCompleted()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_KITI_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_C,
					args);

				args = new Object[] {
						kaleoTimerInstanceTokenModelImpl.getKaleoInstanceTokenId(),
						kaleoTimerInstanceTokenModelImpl.getCompleted()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_KITI_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_C,
					args);
			}

			if ((kaleoTimerInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_C_B.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTimerInstanceTokenModelImpl.getOriginalKaleoInstanceTokenId(),
						kaleoTimerInstanceTokenModelImpl.getOriginalCompleted(),
						kaleoTimerInstanceTokenModelImpl.getOriginalBlocking()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_KITI_C_B, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_C_B,
					args);

				args = new Object[] {
						kaleoTimerInstanceTokenModelImpl.getKaleoInstanceTokenId(),
						kaleoTimerInstanceTokenModelImpl.getCompleted(),
						kaleoTimerInstanceTokenModelImpl.getBlocking()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_KITI_C_B, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_C_B,
					args);
			}
		}

		entityCache.putResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			kaleoTimerInstanceToken.getPrimaryKey(), kaleoTimerInstanceToken,
			false);

		clearUniqueFindersCache(kaleoTimerInstanceTokenModelImpl);
		cacheUniqueFindersCache(kaleoTimerInstanceTokenModelImpl, isNew);

		kaleoTimerInstanceToken.resetOriginalValues();

		return kaleoTimerInstanceToken;
	}

	protected KaleoTimerInstanceToken toUnwrappedModel(
		KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		if (kaleoTimerInstanceToken instanceof KaleoTimerInstanceTokenImpl) {
			return kaleoTimerInstanceToken;
		}

		KaleoTimerInstanceTokenImpl kaleoTimerInstanceTokenImpl = new KaleoTimerInstanceTokenImpl();

		kaleoTimerInstanceTokenImpl.setNew(kaleoTimerInstanceToken.isNew());
		kaleoTimerInstanceTokenImpl.setPrimaryKey(kaleoTimerInstanceToken.getPrimaryKey());

		kaleoTimerInstanceTokenImpl.setKaleoTimerInstanceTokenId(kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId());
		kaleoTimerInstanceTokenImpl.setGroupId(kaleoTimerInstanceToken.getGroupId());
		kaleoTimerInstanceTokenImpl.setCompanyId(kaleoTimerInstanceToken.getCompanyId());
		kaleoTimerInstanceTokenImpl.setUserId(kaleoTimerInstanceToken.getUserId());
		kaleoTimerInstanceTokenImpl.setUserName(kaleoTimerInstanceToken.getUserName());
		kaleoTimerInstanceTokenImpl.setCreateDate(kaleoTimerInstanceToken.getCreateDate());
		kaleoTimerInstanceTokenImpl.setModifiedDate(kaleoTimerInstanceToken.getModifiedDate());
		kaleoTimerInstanceTokenImpl.setKaleoClassName(kaleoTimerInstanceToken.getKaleoClassName());
		kaleoTimerInstanceTokenImpl.setKaleoClassPK(kaleoTimerInstanceToken.getKaleoClassPK());
		kaleoTimerInstanceTokenImpl.setKaleoDefinitionId(kaleoTimerInstanceToken.getKaleoDefinitionId());
		kaleoTimerInstanceTokenImpl.setKaleoInstanceId(kaleoTimerInstanceToken.getKaleoInstanceId());
		kaleoTimerInstanceTokenImpl.setKaleoInstanceTokenId(kaleoTimerInstanceToken.getKaleoInstanceTokenId());
		kaleoTimerInstanceTokenImpl.setKaleoTaskInstanceTokenId(kaleoTimerInstanceToken.getKaleoTaskInstanceTokenId());
		kaleoTimerInstanceTokenImpl.setKaleoTimerId(kaleoTimerInstanceToken.getKaleoTimerId());
		kaleoTimerInstanceTokenImpl.setKaleoTimerName(kaleoTimerInstanceToken.getKaleoTimerName());
		kaleoTimerInstanceTokenImpl.setBlocking(kaleoTimerInstanceToken.isBlocking());
		kaleoTimerInstanceTokenImpl.setCompletionUserId(kaleoTimerInstanceToken.getCompletionUserId());
		kaleoTimerInstanceTokenImpl.setCompleted(kaleoTimerInstanceToken.isCompleted());
		kaleoTimerInstanceTokenImpl.setCompletionDate(kaleoTimerInstanceToken.getCompletionDate());
		kaleoTimerInstanceTokenImpl.setWorkflowContext(kaleoTimerInstanceToken.getWorkflowContext());

		return kaleoTimerInstanceTokenImpl;
	}

	/**
	 * Returns the kaleo timer instance token with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token
	 * @throws NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	 */
	@Override
	public KaleoTimerInstanceToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTimerInstanceTokenException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = fetchByPrimaryKey(primaryKey);

		if (kaleoTimerInstanceToken == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTimerInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoTimerInstanceToken;
	}

	/**
	 * Returns the kaleo timer instance token with the primary key or throws a {@link NoSuchTimerInstanceTokenException} if it could not be found.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token
	 * @throws NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	 */
	@Override
	public KaleoTimerInstanceToken findByPrimaryKey(
		long kaleoTimerInstanceTokenId)
		throws NoSuchTimerInstanceTokenException {
		return findByPrimaryKey((Serializable)kaleoTimerInstanceTokenId);
	}

	/**
	 * Returns the kaleo timer instance token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token, or <code>null</code> if a kaleo timer instance token with the primary key could not be found
	 */
	@Override
	public KaleoTimerInstanceToken fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTimerInstanceTokenImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		KaleoTimerInstanceToken kaleoTimerInstanceToken = (KaleoTimerInstanceToken)serializable;

		if (kaleoTimerInstanceToken == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoTimerInstanceToken = (KaleoTimerInstanceToken)session.get(KaleoTimerInstanceTokenImpl.class,
						primaryKey);

				if (kaleoTimerInstanceToken != null) {
					cacheResult(kaleoTimerInstanceToken);
				}
				else {
					entityCache.putResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTimerInstanceTokenImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTimerInstanceTokenImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoTimerInstanceToken;
	}

	/**
	 * Returns the kaleo timer instance token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token, or <code>null</code> if a kaleo timer instance token with the primary key could not be found
	 */
	@Override
	public KaleoTimerInstanceToken fetchByPrimaryKey(
		long kaleoTimerInstanceTokenId) {
		return fetchByPrimaryKey((Serializable)kaleoTimerInstanceTokenId);
	}

	@Override
	public Map<Serializable, KaleoTimerInstanceToken> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoTimerInstanceToken> map = new HashMap<Serializable, KaleoTimerInstanceToken>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoTimerInstanceToken kaleoTimerInstanceToken = fetchByPrimaryKey(primaryKey);

			if (kaleoTimerInstanceToken != null) {
				map.put(primaryKey, kaleoTimerInstanceToken);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTimerInstanceTokenImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (KaleoTimerInstanceToken)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE_PKS_IN);

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

			for (KaleoTimerInstanceToken kaleoTimerInstanceToken : (List<KaleoTimerInstanceToken>)q.list()) {
				map.put(kaleoTimerInstanceToken.getPrimaryKeyObj(),
					kaleoTimerInstanceToken);

				cacheResult(kaleoTimerInstanceToken);

				uncachedPrimaryKeys.remove(kaleoTimerInstanceToken.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTimerInstanceTokenImpl.class, primaryKey, nullModel);
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
	 * Returns all the kaleo timer instance tokens.
	 *
	 * @return the kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timer instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @return the range of kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timer instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findAll(int start, int end,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo timer instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of kaleo timer instance tokens
	 */
	@Override
	public List<KaleoTimerInstanceToken> findAll(int start, int end,
		OrderByComparator<KaleoTimerInstanceToken> orderByComparator,
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

		List<KaleoTimerInstanceToken> list = null;

		if (retrieveFromCache) {
			list = (List<KaleoTimerInstanceToken>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTIMERINSTANCETOKEN;

				if (pagination) {
					sql = sql.concat(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoTimerInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTimerInstanceToken>)QueryUtil.list(q,
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
	 * Removes all the kaleo timer instance tokens from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoTimerInstanceToken kaleoTimerInstanceToken : findAll()) {
			remove(kaleoTimerInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo timer instance tokens.
	 *
	 * @return the number of kaleo timer instance tokens
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOTIMERINSTANCETOKEN);

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
		return KaleoTimerInstanceTokenModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the kaleo timer instance token persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(KaleoTimerInstanceTokenImpl.class.getName());
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
	private static final String _SQL_SELECT_KALEOTIMERINSTANCETOKEN = "SELECT kaleoTimerInstanceToken FROM KaleoTimerInstanceToken kaleoTimerInstanceToken";
	private static final String _SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE_PKS_IN =
		"SELECT kaleoTimerInstanceToken FROM KaleoTimerInstanceToken kaleoTimerInstanceToken WHERE kaleoTimerInstanceTokenId IN (";
	private static final String _SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE = "SELECT kaleoTimerInstanceToken FROM KaleoTimerInstanceToken kaleoTimerInstanceToken WHERE ";
	private static final String _SQL_COUNT_KALEOTIMERINSTANCETOKEN = "SELECT COUNT(kaleoTimerInstanceToken) FROM KaleoTimerInstanceToken kaleoTimerInstanceToken";
	private static final String _SQL_COUNT_KALEOTIMERINSTANCETOKEN_WHERE = "SELECT COUNT(kaleoTimerInstanceToken) FROM KaleoTimerInstanceToken kaleoTimerInstanceToken WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTimerInstanceToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTimerInstanceToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTimerInstanceToken exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KaleoTimerInstanceTokenPersistenceImpl.class);
}