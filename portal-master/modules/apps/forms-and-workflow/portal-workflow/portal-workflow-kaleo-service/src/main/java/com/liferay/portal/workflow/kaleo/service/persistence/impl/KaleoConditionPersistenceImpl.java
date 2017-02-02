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
import com.liferay.portal.workflow.kaleo.exception.NoSuchConditionException;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoConditionPersistence;

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
 * The persistence implementation for the kaleo condition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoConditionPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.KaleoConditionUtil
 * @generated
 */
@ProviderType
public class KaleoConditionPersistenceImpl extends BasePersistenceImpl<KaleoCondition>
	implements KaleoConditionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoConditionUtil} to access the kaleo condition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoConditionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED,
			KaleoConditionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED,
			KaleoConditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED,
			KaleoConditionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED,
			KaleoConditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoConditionModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo conditions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo conditions
	 */
	@Override
	public List<KaleoCondition> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo conditions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo conditions
	 * @param end the upper bound of the range of kaleo conditions (not inclusive)
	 * @return the range of matching kaleo conditions
	 */
	@Override
	public List<KaleoCondition> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo conditions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo conditions
	 * @param end the upper bound of the range of kaleo conditions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo conditions
	 */
	@Override
	public List<KaleoCondition> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<KaleoCondition> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo conditions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo conditions
	 * @param end the upper bound of the range of kaleo conditions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching kaleo conditions
	 */
	@Override
	public List<KaleoCondition> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<KaleoCondition> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<KaleoCondition> list = null;

		if (retrieveFromCache) {
			list = (List<KaleoCondition>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (KaleoCondition kaleoCondition : list) {
					if ((companyId != kaleoCondition.getCompanyId())) {
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

			query.append(_SQL_SELECT_KALEOCONDITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoConditionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<KaleoCondition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoCondition>)QueryUtil.list(q,
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
	 * Returns the first kaleo condition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo condition
	 * @throws NoSuchConditionException if a matching kaleo condition could not be found
	 */
	@Override
	public KaleoCondition findByCompanyId_First(long companyId,
		OrderByComparator<KaleoCondition> orderByComparator)
		throws NoSuchConditionException {
		KaleoCondition kaleoCondition = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (kaleoCondition != null) {
			return kaleoCondition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConditionException(msg.toString());
	}

	/**
	 * Returns the first kaleo condition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	 */
	@Override
	public KaleoCondition fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoCondition> orderByComparator) {
		List<KaleoCondition> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo condition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo condition
	 * @throws NoSuchConditionException if a matching kaleo condition could not be found
	 */
	@Override
	public KaleoCondition findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoCondition> orderByComparator)
		throws NoSuchConditionException {
		KaleoCondition kaleoCondition = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (kaleoCondition != null) {
			return kaleoCondition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConditionException(msg.toString());
	}

	/**
	 * Returns the last kaleo condition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	 */
	@Override
	public KaleoCondition fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoCondition> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoCondition> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo conditions before and after the current kaleo condition in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoConditionId the primary key of the current kaleo condition
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo condition
	 * @throws NoSuchConditionException if a kaleo condition with the primary key could not be found
	 */
	@Override
	public KaleoCondition[] findByCompanyId_PrevAndNext(long kaleoConditionId,
		long companyId, OrderByComparator<KaleoCondition> orderByComparator)
		throws NoSuchConditionException {
		KaleoCondition kaleoCondition = findByPrimaryKey(kaleoConditionId);

		Session session = null;

		try {
			session = openSession();

			KaleoCondition[] array = new KaleoConditionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoCondition,
					companyId, orderByComparator, true);

			array[1] = kaleoCondition;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoCondition,
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

	protected KaleoCondition getByCompanyId_PrevAndNext(Session session,
		KaleoCondition kaleoCondition, long companyId,
		OrderByComparator<KaleoCondition> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOCONDITION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(KaleoConditionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoCondition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoCondition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo conditions where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (KaleoCondition kaleoCondition : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoCondition);
		}
	}

	/**
	 * Returns the number of kaleo conditions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo conditions
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOCONDITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoCondition.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED,
			KaleoConditionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED,
			KaleoConditionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] { Long.class.getName() },
			KaleoConditionModelImpl.KALEODEFINITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo conditions where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo conditions
	 */
	@Override
	public List<KaleoCondition> findByKaleoDefinitionId(long kaleoDefinitionId) {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo conditions where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo conditions
	 * @param end the upper bound of the range of kaleo conditions (not inclusive)
	 * @return the range of matching kaleo conditions
	 */
	@Override
	public List<KaleoCondition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo conditions where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo conditions
	 * @param end the upper bound of the range of kaleo conditions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo conditions
	 */
	@Override
	public List<KaleoCondition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoCondition> orderByComparator) {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo conditions where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo conditions
	 * @param end the upper bound of the range of kaleo conditions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching kaleo conditions
	 */
	@Override
	public List<KaleoCondition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoCondition> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID;
			finderArgs = new Object[] { kaleoDefinitionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID;
			finderArgs = new Object[] {
					kaleoDefinitionId,
					
					start, end, orderByComparator
				};
		}

		List<KaleoCondition> list = null;

		if (retrieveFromCache) {
			list = (List<KaleoCondition>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (KaleoCondition kaleoCondition : list) {
					if ((kaleoDefinitionId != kaleoCondition.getKaleoDefinitionId())) {
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

			query.append(_SQL_SELECT_KALEOCONDITION_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoConditionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				if (!pagination) {
					list = (List<KaleoCondition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoCondition>)QueryUtil.list(q,
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
	 * Returns the first kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo condition
	 * @throws NoSuchConditionException if a matching kaleo condition could not be found
	 */
	@Override
	public KaleoCondition findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoCondition> orderByComparator)
		throws NoSuchConditionException {
		KaleoCondition kaleoCondition = fetchByKaleoDefinitionId_First(kaleoDefinitionId,
				orderByComparator);

		if (kaleoCondition != null) {
			return kaleoCondition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConditionException(msg.toString());
	}

	/**
	 * Returns the first kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	 */
	@Override
	public KaleoCondition fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoCondition> orderByComparator) {
		List<KaleoCondition> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo condition
	 * @throws NoSuchConditionException if a matching kaleo condition could not be found
	 */
	@Override
	public KaleoCondition findByKaleoDefinitionId_Last(long kaleoDefinitionId,
		OrderByComparator<KaleoCondition> orderByComparator)
		throws NoSuchConditionException {
		KaleoCondition kaleoCondition = fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
				orderByComparator);

		if (kaleoCondition != null) {
			return kaleoCondition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConditionException(msg.toString());
	}

	/**
	 * Returns the last kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	 */
	@Override
	public KaleoCondition fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoCondition> orderByComparator) {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		if (count == 0) {
			return null;
		}

		List<KaleoCondition> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo conditions before and after the current kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoConditionId the primary key of the current kaleo condition
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo condition
	 * @throws NoSuchConditionException if a kaleo condition with the primary key could not be found
	 */
	@Override
	public KaleoCondition[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoConditionId, long kaleoDefinitionId,
		OrderByComparator<KaleoCondition> orderByComparator)
		throws NoSuchConditionException {
		KaleoCondition kaleoCondition = findByPrimaryKey(kaleoConditionId);

		Session session = null;

		try {
			session = openSession();

			KaleoCondition[] array = new KaleoConditionImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoCondition, kaleoDefinitionId, orderByComparator, true);

			array[1] = kaleoCondition;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoCondition, kaleoDefinitionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoCondition getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoCondition kaleoCondition, long kaleoDefinitionId,
		OrderByComparator<KaleoCondition> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOCONDITION_WHERE);

		query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

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
			query.append(KaleoConditionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoCondition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoCondition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo conditions where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 */
	@Override
	public void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		for (KaleoCondition kaleoCondition : findByKaleoDefinitionId(
				kaleoDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoCondition);
		}
	}

	/**
	 * Returns the number of kaleo conditions where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo conditions
	 */
	@Override
	public int countByKaleoDefinitionId(long kaleoDefinitionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEODEFINITIONID;

		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOCONDITION_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

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

	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoCondition.kaleoDefinitionId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_KALEONODEID = new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED,
			KaleoConditionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByKaleoNodeId", new String[] { Long.class.getName() },
			KaleoConditionModelImpl.KALEONODEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEONODEID = new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKaleoNodeId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the kaleo condition where kaleoNodeId = &#63; or throws a {@link NoSuchConditionException} if it could not be found.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @return the matching kaleo condition
	 * @throws NoSuchConditionException if a matching kaleo condition could not be found
	 */
	@Override
	public KaleoCondition findByKaleoNodeId(long kaleoNodeId)
		throws NoSuchConditionException {
		KaleoCondition kaleoCondition = fetchByKaleoNodeId(kaleoNodeId);

		if (kaleoCondition == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchConditionException(msg.toString());
		}

		return kaleoCondition;
	}

	/**
	 * Returns the kaleo condition where kaleoNodeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @return the matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	 */
	@Override
	public KaleoCondition fetchByKaleoNodeId(long kaleoNodeId) {
		return fetchByKaleoNodeId(kaleoNodeId, true);
	}

	/**
	 * Returns the kaleo condition where kaleoNodeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	 */
	@Override
	public KaleoCondition fetchByKaleoNodeId(long kaleoNodeId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { kaleoNodeId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_KALEONODEID,
					finderArgs, this);
		}

		if (result instanceof KaleoCondition) {
			KaleoCondition kaleoCondition = (KaleoCondition)result;

			if ((kaleoNodeId != kaleoCondition.getKaleoNodeId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_KALEOCONDITION_WHERE);

			query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				List<KaleoCondition> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_KALEONODEID,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"KaleoConditionPersistenceImpl.fetchByKaleoNodeId(long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					KaleoCondition kaleoCondition = list.get(0);

					result = kaleoCondition;

					cacheResult(kaleoCondition);

					if ((kaleoCondition.getKaleoNodeId() != kaleoNodeId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_KALEONODEID,
							finderArgs, kaleoCondition);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_KALEONODEID,
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
			return (KaleoCondition)result;
		}
	}

	/**
	 * Removes the kaleo condition where kaleoNodeId = &#63; from the database.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @return the kaleo condition that was removed
	 */
	@Override
	public KaleoCondition removeByKaleoNodeId(long kaleoNodeId)
		throws NoSuchConditionException {
		KaleoCondition kaleoCondition = findByKaleoNodeId(kaleoNodeId);

		return remove(kaleoCondition);
	}

	/**
	 * Returns the number of kaleo conditions where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @return the number of matching kaleo conditions
	 */
	@Override
	public int countByKaleoNodeId(long kaleoNodeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEONODEID;

		Object[] finderArgs = new Object[] { kaleoNodeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOCONDITION_WHERE);

			query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

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

	private static final String _FINDER_COLUMN_KALEONODEID_KALEONODEID_2 = "kaleoCondition.kaleoNodeId = ?";

	public KaleoConditionPersistenceImpl() {
		setModelClass(KaleoCondition.class);
	}

	/**
	 * Caches the kaleo condition in the entity cache if it is enabled.
	 *
	 * @param kaleoCondition the kaleo condition
	 */
	@Override
	public void cacheResult(KaleoCondition kaleoCondition) {
		entityCache.putResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionImpl.class, kaleoCondition.getPrimaryKey(),
			kaleoCondition);

		finderCache.putResult(FINDER_PATH_FETCH_BY_KALEONODEID,
			new Object[] { kaleoCondition.getKaleoNodeId() }, kaleoCondition);

		kaleoCondition.resetOriginalValues();
	}

	/**
	 * Caches the kaleo conditions in the entity cache if it is enabled.
	 *
	 * @param kaleoConditions the kaleo conditions
	 */
	@Override
	public void cacheResult(List<KaleoCondition> kaleoConditions) {
		for (KaleoCondition kaleoCondition : kaleoConditions) {
			if (entityCache.getResult(
						KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoConditionImpl.class, kaleoCondition.getPrimaryKey()) == null) {
				cacheResult(kaleoCondition);
			}
			else {
				kaleoCondition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo conditions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(KaleoConditionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo condition.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoCondition kaleoCondition) {
		entityCache.removeResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionImpl.class, kaleoCondition.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((KaleoConditionModelImpl)kaleoCondition);
	}

	@Override
	public void clearCache(List<KaleoCondition> kaleoConditions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoCondition kaleoCondition : kaleoConditions) {
			entityCache.removeResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoConditionImpl.class, kaleoCondition.getPrimaryKey());

			clearUniqueFindersCache((KaleoConditionModelImpl)kaleoCondition);
		}
	}

	protected void cacheUniqueFindersCache(
		KaleoConditionModelImpl kaleoConditionModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					kaleoConditionModelImpl.getKaleoNodeId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_KALEONODEID, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_KALEONODEID, args,
				kaleoConditionModelImpl);
		}
		else {
			if ((kaleoConditionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_KALEONODEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoConditionModelImpl.getKaleoNodeId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_KALEONODEID, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_KALEONODEID, args,
					kaleoConditionModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		KaleoConditionModelImpl kaleoConditionModelImpl) {
		Object[] args = new Object[] { kaleoConditionModelImpl.getKaleoNodeId() };

		finderCache.removeResult(FINDER_PATH_COUNT_BY_KALEONODEID, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_KALEONODEID, args);

		if ((kaleoConditionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_KALEONODEID.getColumnBitmask()) != 0) {
			args = new Object[] { kaleoConditionModelImpl.getOriginalKaleoNodeId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_KALEONODEID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_KALEONODEID, args);
		}
	}

	/**
	 * Creates a new kaleo condition with the primary key. Does not add the kaleo condition to the database.
	 *
	 * @param kaleoConditionId the primary key for the new kaleo condition
	 * @return the new kaleo condition
	 */
	@Override
	public KaleoCondition create(long kaleoConditionId) {
		KaleoCondition kaleoCondition = new KaleoConditionImpl();

		kaleoCondition.setNew(true);
		kaleoCondition.setPrimaryKey(kaleoConditionId);

		kaleoCondition.setCompanyId(companyProvider.getCompanyId());

		return kaleoCondition;
	}

	/**
	 * Removes the kaleo condition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoConditionId the primary key of the kaleo condition
	 * @return the kaleo condition that was removed
	 * @throws NoSuchConditionException if a kaleo condition with the primary key could not be found
	 */
	@Override
	public KaleoCondition remove(long kaleoConditionId)
		throws NoSuchConditionException {
		return remove((Serializable)kaleoConditionId);
	}

	/**
	 * Removes the kaleo condition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo condition
	 * @return the kaleo condition that was removed
	 * @throws NoSuchConditionException if a kaleo condition with the primary key could not be found
	 */
	@Override
	public KaleoCondition remove(Serializable primaryKey)
		throws NoSuchConditionException {
		Session session = null;

		try {
			session = openSession();

			KaleoCondition kaleoCondition = (KaleoCondition)session.get(KaleoConditionImpl.class,
					primaryKey);

			if (kaleoCondition == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchConditionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoCondition);
		}
		catch (NoSuchConditionException nsee) {
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
	protected KaleoCondition removeImpl(KaleoCondition kaleoCondition) {
		kaleoCondition = toUnwrappedModel(kaleoCondition);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoCondition)) {
				kaleoCondition = (KaleoCondition)session.get(KaleoConditionImpl.class,
						kaleoCondition.getPrimaryKeyObj());
			}

			if (kaleoCondition != null) {
				session.delete(kaleoCondition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoCondition != null) {
			clearCache(kaleoCondition);
		}

		return kaleoCondition;
	}

	@Override
	public KaleoCondition updateImpl(KaleoCondition kaleoCondition) {
		kaleoCondition = toUnwrappedModel(kaleoCondition);

		boolean isNew = kaleoCondition.isNew();

		KaleoConditionModelImpl kaleoConditionModelImpl = (KaleoConditionModelImpl)kaleoCondition;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (kaleoCondition.getCreateDate() == null)) {
			if (serviceContext == null) {
				kaleoCondition.setCreateDate(now);
			}
			else {
				kaleoCondition.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!kaleoConditionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kaleoCondition.setModifiedDate(now);
			}
			else {
				kaleoCondition.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (kaleoCondition.isNew()) {
				session.save(kaleoCondition);

				kaleoCondition.setNew(false);
			}
			else {
				kaleoCondition = (KaleoCondition)session.merge(kaleoCondition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoConditionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoConditionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoConditionModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { kaleoConditionModelImpl.getCompanyId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoConditionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoConditionModelImpl.getOriginalKaleoDefinitionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] {
						kaleoConditionModelImpl.getKaleoDefinitionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}
		}

		entityCache.putResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionImpl.class, kaleoCondition.getPrimaryKey(),
			kaleoCondition, false);

		clearUniqueFindersCache(kaleoConditionModelImpl);
		cacheUniqueFindersCache(kaleoConditionModelImpl, isNew);

		kaleoCondition.resetOriginalValues();

		return kaleoCondition;
	}

	protected KaleoCondition toUnwrappedModel(KaleoCondition kaleoCondition) {
		if (kaleoCondition instanceof KaleoConditionImpl) {
			return kaleoCondition;
		}

		KaleoConditionImpl kaleoConditionImpl = new KaleoConditionImpl();

		kaleoConditionImpl.setNew(kaleoCondition.isNew());
		kaleoConditionImpl.setPrimaryKey(kaleoCondition.getPrimaryKey());

		kaleoConditionImpl.setKaleoConditionId(kaleoCondition.getKaleoConditionId());
		kaleoConditionImpl.setGroupId(kaleoCondition.getGroupId());
		kaleoConditionImpl.setCompanyId(kaleoCondition.getCompanyId());
		kaleoConditionImpl.setUserId(kaleoCondition.getUserId());
		kaleoConditionImpl.setUserName(kaleoCondition.getUserName());
		kaleoConditionImpl.setCreateDate(kaleoCondition.getCreateDate());
		kaleoConditionImpl.setModifiedDate(kaleoCondition.getModifiedDate());
		kaleoConditionImpl.setKaleoDefinitionId(kaleoCondition.getKaleoDefinitionId());
		kaleoConditionImpl.setKaleoNodeId(kaleoCondition.getKaleoNodeId());
		kaleoConditionImpl.setScript(kaleoCondition.getScript());
		kaleoConditionImpl.setScriptLanguage(kaleoCondition.getScriptLanguage());
		kaleoConditionImpl.setScriptRequiredContexts(kaleoCondition.getScriptRequiredContexts());

		return kaleoConditionImpl;
	}

	/**
	 * Returns the kaleo condition with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo condition
	 * @return the kaleo condition
	 * @throws NoSuchConditionException if a kaleo condition with the primary key could not be found
	 */
	@Override
	public KaleoCondition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchConditionException {
		KaleoCondition kaleoCondition = fetchByPrimaryKey(primaryKey);

		if (kaleoCondition == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchConditionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoCondition;
	}

	/**
	 * Returns the kaleo condition with the primary key or throws a {@link NoSuchConditionException} if it could not be found.
	 *
	 * @param kaleoConditionId the primary key of the kaleo condition
	 * @return the kaleo condition
	 * @throws NoSuchConditionException if a kaleo condition with the primary key could not be found
	 */
	@Override
	public KaleoCondition findByPrimaryKey(long kaleoConditionId)
		throws NoSuchConditionException {
		return findByPrimaryKey((Serializable)kaleoConditionId);
	}

	/**
	 * Returns the kaleo condition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo condition
	 * @return the kaleo condition, or <code>null</code> if a kaleo condition with the primary key could not be found
	 */
	@Override
	public KaleoCondition fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoConditionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		KaleoCondition kaleoCondition = (KaleoCondition)serializable;

		if (kaleoCondition == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoCondition = (KaleoCondition)session.get(KaleoConditionImpl.class,
						primaryKey);

				if (kaleoCondition != null) {
					cacheResult(kaleoCondition);
				}
				else {
					entityCache.putResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoConditionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoConditionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoCondition;
	}

	/**
	 * Returns the kaleo condition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoConditionId the primary key of the kaleo condition
	 * @return the kaleo condition, or <code>null</code> if a kaleo condition with the primary key could not be found
	 */
	@Override
	public KaleoCondition fetchByPrimaryKey(long kaleoConditionId) {
		return fetchByPrimaryKey((Serializable)kaleoConditionId);
	}

	@Override
	public Map<Serializable, KaleoCondition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoCondition> map = new HashMap<Serializable, KaleoCondition>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoCondition kaleoCondition = fetchByPrimaryKey(primaryKey);

			if (kaleoCondition != null) {
				map.put(primaryKey, kaleoCondition);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoConditionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (KaleoCondition)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KALEOCONDITION_WHERE_PKS_IN);

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

			for (KaleoCondition kaleoCondition : (List<KaleoCondition>)q.list()) {
				map.put(kaleoCondition.getPrimaryKeyObj(), kaleoCondition);

				cacheResult(kaleoCondition);

				uncachedPrimaryKeys.remove(kaleoCondition.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoConditionImpl.class, primaryKey, nullModel);
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
	 * Returns all the kaleo conditions.
	 *
	 * @return the kaleo conditions
	 */
	@Override
	public List<KaleoCondition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo conditions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo conditions
	 * @param end the upper bound of the range of kaleo conditions (not inclusive)
	 * @return the range of kaleo conditions
	 */
	@Override
	public List<KaleoCondition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo conditions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo conditions
	 * @param end the upper bound of the range of kaleo conditions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo conditions
	 */
	@Override
	public List<KaleoCondition> findAll(int start, int end,
		OrderByComparator<KaleoCondition> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo conditions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo conditions
	 * @param end the upper bound of the range of kaleo conditions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of kaleo conditions
	 */
	@Override
	public List<KaleoCondition> findAll(int start, int end,
		OrderByComparator<KaleoCondition> orderByComparator,
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

		List<KaleoCondition> list = null;

		if (retrieveFromCache) {
			list = (List<KaleoCondition>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_KALEOCONDITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOCONDITION;

				if (pagination) {
					sql = sql.concat(KaleoConditionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoCondition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoCondition>)QueryUtil.list(q,
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
	 * Removes all the kaleo conditions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoCondition kaleoCondition : findAll()) {
			remove(kaleoCondition);
		}
	}

	/**
	 * Returns the number of kaleo conditions.
	 *
	 * @return the number of kaleo conditions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOCONDITION);

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
		return KaleoConditionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the kaleo condition persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(KaleoConditionImpl.class.getName());
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
	private static final String _SQL_SELECT_KALEOCONDITION = "SELECT kaleoCondition FROM KaleoCondition kaleoCondition";
	private static final String _SQL_SELECT_KALEOCONDITION_WHERE_PKS_IN = "SELECT kaleoCondition FROM KaleoCondition kaleoCondition WHERE kaleoConditionId IN (";
	private static final String _SQL_SELECT_KALEOCONDITION_WHERE = "SELECT kaleoCondition FROM KaleoCondition kaleoCondition WHERE ";
	private static final String _SQL_COUNT_KALEOCONDITION = "SELECT COUNT(kaleoCondition) FROM KaleoCondition kaleoCondition";
	private static final String _SQL_COUNT_KALEOCONDITION_WHERE = "SELECT COUNT(kaleoCondition) FROM KaleoCondition kaleoCondition WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoCondition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoCondition exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoCondition exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KaleoConditionPersistenceImpl.class);
}