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
import com.liferay.portal.kernel.exception.NoSuchWorkflowDefinitionLinkException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.WorkflowDefinitionLinkPersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.impl.WorkflowDefinitionLinkImpl;
import com.liferay.portal.model.impl.WorkflowDefinitionLinkModelImpl;

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
 * The persistence implementation for the workflow definition link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowDefinitionLinkPersistence
 * @see com.liferay.portal.kernel.service.persistence.WorkflowDefinitionLinkUtil
 * @generated
 */
@ProviderType
public class WorkflowDefinitionLinkPersistenceImpl extends BasePersistenceImpl<WorkflowDefinitionLink>
	implements WorkflowDefinitionLinkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WorkflowDefinitionLinkUtil} to access the workflow definition link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WorkflowDefinitionLinkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			WorkflowDefinitionLinkModelImpl.COMPANYID_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.WORKFLOWDEFINITIONNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the workflow definition links where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the workflow definition links where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @return the range of matching workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findByCompanyId(long companyId,
		int start, int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow definition links where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the workflow definition links where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator,
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

		List<WorkflowDefinitionLink> list = null;

		if (retrieveFromCache) {
			list = (List<WorkflowDefinitionLink>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WorkflowDefinitionLink workflowDefinitionLink : list) {
					if ((companyId != workflowDefinitionLink.getCompanyId())) {
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

			query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkflowDefinitionLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<WorkflowDefinitionLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkflowDefinitionLink>)QueryUtil.list(q,
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
	 * Returns the first workflow definition link in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow definition link
	 * @throws NoSuchWorkflowDefinitionLinkException if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink findByCompanyId_First(long companyId,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException {
		WorkflowDefinitionLink workflowDefinitionLink = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (workflowDefinitionLink != null) {
			return workflowDefinitionLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the first workflow definition link in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow definition link, or <code>null</code> if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink fetchByCompanyId_First(long companyId,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator) {
		List<WorkflowDefinitionLink> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workflow definition link in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow definition link
	 * @throws NoSuchWorkflowDefinitionLinkException if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink findByCompanyId_Last(long companyId,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException {
		WorkflowDefinitionLink workflowDefinitionLink = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (workflowDefinitionLink != null) {
			return workflowDefinitionLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the last workflow definition link in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow definition link, or <code>null</code> if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink fetchByCompanyId_Last(long companyId,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<WorkflowDefinitionLink> list = findByCompanyId(companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workflow definition links before and after the current workflow definition link in the ordered set where companyId = &#63;.
	 *
	 * @param workflowDefinitionLinkId the primary key of the current workflow definition link
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow definition link
	 * @throws NoSuchWorkflowDefinitionLinkException if a workflow definition link with the primary key could not be found
	 */
	@Override
	public WorkflowDefinitionLink[] findByCompanyId_PrevAndNext(
		long workflowDefinitionLinkId, long companyId,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException {
		WorkflowDefinitionLink workflowDefinitionLink = findByPrimaryKey(workflowDefinitionLinkId);

		Session session = null;

		try {
			session = openSession();

			WorkflowDefinitionLink[] array = new WorkflowDefinitionLinkImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session,
					workflowDefinitionLink, companyId, orderByComparator, true);

			array[1] = workflowDefinitionLink;

			array[2] = getByCompanyId_PrevAndNext(session,
					workflowDefinitionLink, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkflowDefinitionLink getByCompanyId_PrevAndNext(
		Session session, WorkflowDefinitionLink workflowDefinitionLink,
		long companyId,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator,
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

		query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE);

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
			query.append(WorkflowDefinitionLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowDefinitionLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowDefinitionLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workflow definition links where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (WorkflowDefinitionLink workflowDefinitionLink : findByCompanyId(
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workflowDefinitionLink);
		}
	}

	/**
	 * Returns the number of workflow definition links where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching workflow definition links
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WORKFLOWDEFINITIONLINK_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "workflowDefinitionLink.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C_C = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			WorkflowDefinitionLinkModelImpl.GROUPID_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.COMPANYID_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.WORKFLOWDEFINITIONNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C_C = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the workflow definition links where groupId = &#63; and companyId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @return the matching workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findByG_C_C(long groupId,
		long companyId, long classNameId) {
		return findByG_C_C(groupId, companyId, classNameId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow definition links where groupId = &#63; and companyId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @return the range of matching workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findByG_C_C(long groupId,
		long companyId, long classNameId, int start, int end) {
		return findByG_C_C(groupId, companyId, classNameId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow definition links where groupId = &#63; and companyId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findByG_C_C(long groupId,
		long companyId, long classNameId, int start, int end,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator) {
		return findByG_C_C(groupId, companyId, classNameId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the workflow definition links where groupId = &#63; and companyId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findByG_C_C(long groupId,
		long companyId, long classNameId, int start, int end,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C;
			finderArgs = new Object[] { groupId, companyId, classNameId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C_C;
			finderArgs = new Object[] {
					groupId, companyId, classNameId,
					
					start, end, orderByComparator
				};
		}

		List<WorkflowDefinitionLink> list = null;

		if (retrieveFromCache) {
			list = (List<WorkflowDefinitionLink>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WorkflowDefinitionLink workflowDefinitionLink : list) {
					if ((groupId != workflowDefinitionLink.getGroupId()) ||
							(companyId != workflowDefinitionLink.getCompanyId()) ||
							(classNameId != workflowDefinitionLink.getClassNameId())) {
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

			query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_C_COMPANYID_2);

			query.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkflowDefinitionLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(classNameId);

				if (!pagination) {
					list = (List<WorkflowDefinitionLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkflowDefinitionLink>)QueryUtil.list(q,
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
	 * Returns the first workflow definition link in the ordered set where groupId = &#63; and companyId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow definition link
	 * @throws NoSuchWorkflowDefinitionLinkException if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink findByG_C_C_First(long groupId,
		long companyId, long classNameId,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException {
		WorkflowDefinitionLink workflowDefinitionLink = fetchByG_C_C_First(groupId,
				companyId, classNameId, orderByComparator);

		if (workflowDefinitionLink != null) {
			return workflowDefinitionLink;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the first workflow definition link in the ordered set where groupId = &#63; and companyId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow definition link, or <code>null</code> if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink fetchByG_C_C_First(long groupId,
		long companyId, long classNameId,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator) {
		List<WorkflowDefinitionLink> list = findByG_C_C(groupId, companyId,
				classNameId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workflow definition link in the ordered set where groupId = &#63; and companyId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow definition link
	 * @throws NoSuchWorkflowDefinitionLinkException if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink findByG_C_C_Last(long groupId,
		long companyId, long classNameId,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException {
		WorkflowDefinitionLink workflowDefinitionLink = fetchByG_C_C_Last(groupId,
				companyId, classNameId, orderByComparator);

		if (workflowDefinitionLink != null) {
			return workflowDefinitionLink;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the last workflow definition link in the ordered set where groupId = &#63; and companyId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow definition link, or <code>null</code> if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink fetchByG_C_C_Last(long groupId,
		long companyId, long classNameId,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator) {
		int count = countByG_C_C(groupId, companyId, classNameId);

		if (count == 0) {
			return null;
		}

		List<WorkflowDefinitionLink> list = findByG_C_C(groupId, companyId,
				classNameId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workflow definition links before and after the current workflow definition link in the ordered set where groupId = &#63; and companyId = &#63; and classNameId = &#63;.
	 *
	 * @param workflowDefinitionLinkId the primary key of the current workflow definition link
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow definition link
	 * @throws NoSuchWorkflowDefinitionLinkException if a workflow definition link with the primary key could not be found
	 */
	@Override
	public WorkflowDefinitionLink[] findByG_C_C_PrevAndNext(
		long workflowDefinitionLinkId, long groupId, long companyId,
		long classNameId,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException {
		WorkflowDefinitionLink workflowDefinitionLink = findByPrimaryKey(workflowDefinitionLinkId);

		Session session = null;

		try {
			session = openSession();

			WorkflowDefinitionLink[] array = new WorkflowDefinitionLinkImpl[3];

			array[0] = getByG_C_C_PrevAndNext(session, workflowDefinitionLink,
					groupId, companyId, classNameId, orderByComparator, true);

			array[1] = workflowDefinitionLink;

			array[2] = getByG_C_C_PrevAndNext(session, workflowDefinitionLink,
					groupId, companyId, classNameId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkflowDefinitionLink getByG_C_C_PrevAndNext(Session session,
		WorkflowDefinitionLink workflowDefinitionLink, long groupId,
		long companyId, long classNameId,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator,
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

		query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE);

		query.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_C_COMPANYID_2);

		query.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

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
			query.append(WorkflowDefinitionLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(companyId);

		qPos.add(classNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowDefinitionLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowDefinitionLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workflow definition links where groupId = &#63; and companyId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 */
	@Override
	public void removeByG_C_C(long groupId, long companyId, long classNameId) {
		for (WorkflowDefinitionLink workflowDefinitionLink : findByG_C_C(
				groupId, companyId, classNameId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(workflowDefinitionLink);
		}
	}

	/**
	 * Returns the number of workflow definition links where groupId = &#63; and companyId = &#63; and classNameId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @return the number of matching workflow definition links
	 */
	@Override
	public int countByG_C_C(long groupId, long companyId, long classNameId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_C_C;

		Object[] finderArgs = new Object[] { groupId, companyId, classNameId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_WORKFLOWDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_C_COMPANYID_2);

			query.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(classNameId);

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

	private static final String _FINDER_COLUMN_G_C_C_GROUPID_2 = "workflowDefinitionLink.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_COMPANYID_2 = "workflowDefinitionLink.companyId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_CLASSNAMEID_2 = "workflowDefinitionLink.classNameId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_W_W = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_W_W",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_W_W = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_W_W",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			WorkflowDefinitionLinkModelImpl.COMPANYID_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.WORKFLOWDEFINITIONNAME_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.WORKFLOWDEFINITIONVERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_W_W = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_W_W",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the workflow definition links where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @return the matching workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findByC_W_W(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion) {
		return findByC_W_W(companyId, workflowDefinitionName,
			workflowDefinitionVersion, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the workflow definition links where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @return the range of matching workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findByC_W_W(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		int start, int end) {
		return findByC_W_W(companyId, workflowDefinitionName,
			workflowDefinitionVersion, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow definition links where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findByC_W_W(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		int start, int end,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator) {
		return findByC_W_W(companyId, workflowDefinitionName,
			workflowDefinitionVersion, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the workflow definition links where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findByC_W_W(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		int start, int end,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_W_W;
			finderArgs = new Object[] {
					companyId, workflowDefinitionName, workflowDefinitionVersion
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_W_W;
			finderArgs = new Object[] {
					companyId, workflowDefinitionName, workflowDefinitionVersion,
					
					start, end, orderByComparator
				};
		}

		List<WorkflowDefinitionLink> list = null;

		if (retrieveFromCache) {
			list = (List<WorkflowDefinitionLink>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WorkflowDefinitionLink workflowDefinitionLink : list) {
					if ((companyId != workflowDefinitionLink.getCompanyId()) ||
							!Objects.equals(workflowDefinitionName,
								workflowDefinitionLink.getWorkflowDefinitionName()) ||
							(workflowDefinitionVersion != workflowDefinitionLink.getWorkflowDefinitionVersion())) {
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

			query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_C_W_W_COMPANYID_2);

			boolean bindWorkflowDefinitionName = false;

			if (workflowDefinitionName == null) {
				query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_1);
			}
			else if (workflowDefinitionName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_3);
			}
			else {
				bindWorkflowDefinitionName = true;

				query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_2);
			}

			query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONVERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkflowDefinitionLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindWorkflowDefinitionName) {
					qPos.add(workflowDefinitionName);
				}

				qPos.add(workflowDefinitionVersion);

				if (!pagination) {
					list = (List<WorkflowDefinitionLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkflowDefinitionLink>)QueryUtil.list(q,
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
	 * Returns the first workflow definition link in the ordered set where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow definition link
	 * @throws NoSuchWorkflowDefinitionLinkException if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink findByC_W_W_First(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException {
		WorkflowDefinitionLink workflowDefinitionLink = fetchByC_W_W_First(companyId,
				workflowDefinitionName, workflowDefinitionVersion,
				orderByComparator);

		if (workflowDefinitionLink != null) {
			return workflowDefinitionLink;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", workflowDefinitionName=");
		msg.append(workflowDefinitionName);

		msg.append(", workflowDefinitionVersion=");
		msg.append(workflowDefinitionVersion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the first workflow definition link in the ordered set where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow definition link, or <code>null</code> if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink fetchByC_W_W_First(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator) {
		List<WorkflowDefinitionLink> list = findByC_W_W(companyId,
				workflowDefinitionName, workflowDefinitionVersion, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workflow definition link in the ordered set where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow definition link
	 * @throws NoSuchWorkflowDefinitionLinkException if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink findByC_W_W_Last(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException {
		WorkflowDefinitionLink workflowDefinitionLink = fetchByC_W_W_Last(companyId,
				workflowDefinitionName, workflowDefinitionVersion,
				orderByComparator);

		if (workflowDefinitionLink != null) {
			return workflowDefinitionLink;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", workflowDefinitionName=");
		msg.append(workflowDefinitionName);

		msg.append(", workflowDefinitionVersion=");
		msg.append(workflowDefinitionVersion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkflowDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the last workflow definition link in the ordered set where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow definition link, or <code>null</code> if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink fetchByC_W_W_Last(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator) {
		int count = countByC_W_W(companyId, workflowDefinitionName,
				workflowDefinitionVersion);

		if (count == 0) {
			return null;
		}

		List<WorkflowDefinitionLink> list = findByC_W_W(companyId,
				workflowDefinitionName, workflowDefinitionVersion, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workflow definition links before and after the current workflow definition link in the ordered set where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * @param workflowDefinitionLinkId the primary key of the current workflow definition link
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow definition link
	 * @throws NoSuchWorkflowDefinitionLinkException if a workflow definition link with the primary key could not be found
	 */
	@Override
	public WorkflowDefinitionLink[] findByC_W_W_PrevAndNext(
		long workflowDefinitionLinkId, long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException {
		WorkflowDefinitionLink workflowDefinitionLink = findByPrimaryKey(workflowDefinitionLinkId);

		Session session = null;

		try {
			session = openSession();

			WorkflowDefinitionLink[] array = new WorkflowDefinitionLinkImpl[3];

			array[0] = getByC_W_W_PrevAndNext(session, workflowDefinitionLink,
					companyId, workflowDefinitionName,
					workflowDefinitionVersion, orderByComparator, true);

			array[1] = workflowDefinitionLink;

			array[2] = getByC_W_W_PrevAndNext(session, workflowDefinitionLink,
					companyId, workflowDefinitionName,
					workflowDefinitionVersion, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkflowDefinitionLink getByC_W_W_PrevAndNext(Session session,
		WorkflowDefinitionLink workflowDefinitionLink, long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator,
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

		query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE);

		query.append(_FINDER_COLUMN_C_W_W_COMPANYID_2);

		boolean bindWorkflowDefinitionName = false;

		if (workflowDefinitionName == null) {
			query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_1);
		}
		else if (workflowDefinitionName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_3);
		}
		else {
			bindWorkflowDefinitionName = true;

			query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_2);
		}

		query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONVERSION_2);

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
			query.append(WorkflowDefinitionLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindWorkflowDefinitionName) {
			qPos.add(workflowDefinitionName);
		}

		qPos.add(workflowDefinitionVersion);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowDefinitionLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowDefinitionLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workflow definition links where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 */
	@Override
	public void removeByC_W_W(long companyId, String workflowDefinitionName,
		int workflowDefinitionVersion) {
		for (WorkflowDefinitionLink workflowDefinitionLink : findByC_W_W(
				companyId, workflowDefinitionName, workflowDefinitionVersion,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workflowDefinitionLink);
		}
	}

	/**
	 * Returns the number of workflow definition links where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @return the number of matching workflow definition links
	 */
	@Override
	public int countByC_W_W(long companyId, String workflowDefinitionName,
		int workflowDefinitionVersion) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_W_W;

		Object[] finderArgs = new Object[] {
				companyId, workflowDefinitionName, workflowDefinitionVersion
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_WORKFLOWDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_C_W_W_COMPANYID_2);

			boolean bindWorkflowDefinitionName = false;

			if (workflowDefinitionName == null) {
				query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_1);
			}
			else if (workflowDefinitionName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_3);
			}
			else {
				bindWorkflowDefinitionName = true;

				query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_2);
			}

			query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONVERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindWorkflowDefinitionName) {
					qPos.add(workflowDefinitionName);
				}

				qPos.add(workflowDefinitionVersion);

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

	private static final String _FINDER_COLUMN_C_W_W_COMPANYID_2 = "workflowDefinitionLink.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_1 = "workflowDefinitionLink.workflowDefinitionName IS NULL AND ";
	private static final String _FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_2 = "workflowDefinitionLink.workflowDefinitionName = ? AND ";
	private static final String _FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_3 = "(workflowDefinitionLink.workflowDefinitionName IS NULL OR workflowDefinitionLink.workflowDefinitionName = '') AND ";
	private static final String _FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONVERSION_2 =
		"workflowDefinitionLink.workflowDefinitionVersion = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_C_C_C_T = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_C_C_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName()
			},
			WorkflowDefinitionLinkModelImpl.GROUPID_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.COMPANYID_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.CLASSPK_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.TYPEPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C_C_C_T = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C_C_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the workflow definition link where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63; and typePK = &#63; or throws a {@link NoSuchWorkflowDefinitionLinkException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param typePK the type p k
	 * @return the matching workflow definition link
	 * @throws NoSuchWorkflowDefinitionLinkException if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink findByG_C_C_C_T(long groupId, long companyId,
		long classNameId, long classPK, long typePK)
		throws NoSuchWorkflowDefinitionLinkException {
		WorkflowDefinitionLink workflowDefinitionLink = fetchByG_C_C_C_T(groupId,
				companyId, classNameId, classPK, typePK);

		if (workflowDefinitionLink == null) {
			StringBundler msg = new StringBundler(12);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", companyId=");
			msg.append(companyId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", typePK=");
			msg.append(typePK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchWorkflowDefinitionLinkException(msg.toString());
		}

		return workflowDefinitionLink;
	}

	/**
	 * Returns the workflow definition link where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63; and typePK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param typePK the type p k
	 * @return the matching workflow definition link, or <code>null</code> if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink fetchByG_C_C_C_T(long groupId,
		long companyId, long classNameId, long classPK, long typePK) {
		return fetchByG_C_C_C_T(groupId, companyId, classNameId, classPK,
			typePK, true);
	}

	/**
	 * Returns the workflow definition link where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63; and typePK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param typePK the type p k
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching workflow definition link, or <code>null</code> if a matching workflow definition link could not be found
	 */
	@Override
	public WorkflowDefinitionLink fetchByG_C_C_C_T(long groupId,
		long companyId, long classNameId, long classPK, long typePK,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, companyId, classNameId, classPK, typePK
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
					finderArgs, this);
		}

		if (result instanceof WorkflowDefinitionLink) {
			WorkflowDefinitionLink workflowDefinitionLink = (WorkflowDefinitionLink)result;

			if ((groupId != workflowDefinitionLink.getGroupId()) ||
					(companyId != workflowDefinitionLink.getCompanyId()) ||
					(classNameId != workflowDefinitionLink.getClassNameId()) ||
					(classPK != workflowDefinitionLink.getClassPK()) ||
					(typePK != workflowDefinitionLink.getTypePK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_G_C_C_C_T_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_TYPEPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(typePK);

				List<WorkflowDefinitionLink> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"WorkflowDefinitionLinkPersistenceImpl.fetchByG_C_C_C_T(long, long, long, long, long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					WorkflowDefinitionLink workflowDefinitionLink = list.get(0);

					result = workflowDefinitionLink;

					cacheResult(workflowDefinitionLink);

					if ((workflowDefinitionLink.getGroupId() != groupId) ||
							(workflowDefinitionLink.getCompanyId() != companyId) ||
							(workflowDefinitionLink.getClassNameId() != classNameId) ||
							(workflowDefinitionLink.getClassPK() != classPK) ||
							(workflowDefinitionLink.getTypePK() != typePK)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
							finderArgs, workflowDefinitionLink);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
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
			return (WorkflowDefinitionLink)result;
		}
	}

	/**
	 * Removes the workflow definition link where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63; and typePK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param typePK the type p k
	 * @return the workflow definition link that was removed
	 */
	@Override
	public WorkflowDefinitionLink removeByG_C_C_C_T(long groupId,
		long companyId, long classNameId, long classPK, long typePK)
		throws NoSuchWorkflowDefinitionLinkException {
		WorkflowDefinitionLink workflowDefinitionLink = findByG_C_C_C_T(groupId,
				companyId, classNameId, classPK, typePK);

		return remove(workflowDefinitionLink);
	}

	/**
	 * Returns the number of workflow definition links where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63; and typePK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param typePK the type p k
	 * @return the number of matching workflow definition links
	 */
	@Override
	public int countByG_C_C_C_T(long groupId, long companyId, long classNameId,
		long classPK, long typePK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_C_C_C_T;

		Object[] finderArgs = new Object[] {
				groupId, companyId, classNameId, classPK, typePK
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_WORKFLOWDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_G_C_C_C_T_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_TYPEPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(typePK);

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

	private static final String _FINDER_COLUMN_G_C_C_C_T_GROUPID_2 = "workflowDefinitionLink.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_C_T_COMPANYID_2 = "workflowDefinitionLink.companyId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_C_T_CLASSNAMEID_2 = "workflowDefinitionLink.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_C_T_CLASSPK_2 = "workflowDefinitionLink.classPK = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_C_T_TYPEPK_2 = "workflowDefinitionLink.typePK = ?";

	public WorkflowDefinitionLinkPersistenceImpl() {
		setModelClass(WorkflowDefinitionLink.class);
	}

	/**
	 * Caches the workflow definition link in the entity cache if it is enabled.
	 *
	 * @param workflowDefinitionLink the workflow definition link
	 */
	@Override
	public void cacheResult(WorkflowDefinitionLink workflowDefinitionLink) {
		entityCache.putResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			workflowDefinitionLink.getPrimaryKey(), workflowDefinitionLink);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
			new Object[] {
				workflowDefinitionLink.getGroupId(),
				workflowDefinitionLink.getCompanyId(),
				workflowDefinitionLink.getClassNameId(),
				workflowDefinitionLink.getClassPK(),
				workflowDefinitionLink.getTypePK()
			}, workflowDefinitionLink);

		workflowDefinitionLink.resetOriginalValues();
	}

	/**
	 * Caches the workflow definition links in the entity cache if it is enabled.
	 *
	 * @param workflowDefinitionLinks the workflow definition links
	 */
	@Override
	public void cacheResult(
		List<WorkflowDefinitionLink> workflowDefinitionLinks) {
		for (WorkflowDefinitionLink workflowDefinitionLink : workflowDefinitionLinks) {
			if (entityCache.getResult(
						WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowDefinitionLinkImpl.class,
						workflowDefinitionLink.getPrimaryKey()) == null) {
				cacheResult(workflowDefinitionLink);
			}
			else {
				workflowDefinitionLink.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all workflow definition links.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WorkflowDefinitionLinkImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the workflow definition link.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WorkflowDefinitionLink workflowDefinitionLink) {
		entityCache.removeResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			workflowDefinitionLink.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((WorkflowDefinitionLinkModelImpl)workflowDefinitionLink);
	}

	@Override
	public void clearCache(List<WorkflowDefinitionLink> workflowDefinitionLinks) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WorkflowDefinitionLink workflowDefinitionLink : workflowDefinitionLinks) {
			entityCache.removeResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowDefinitionLinkImpl.class,
				workflowDefinitionLink.getPrimaryKey());

			clearUniqueFindersCache((WorkflowDefinitionLinkModelImpl)workflowDefinitionLink);
		}
	}

	protected void cacheUniqueFindersCache(
		WorkflowDefinitionLinkModelImpl workflowDefinitionLinkModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					workflowDefinitionLinkModelImpl.getGroupId(),
					workflowDefinitionLinkModelImpl.getCompanyId(),
					workflowDefinitionLinkModelImpl.getClassNameId(),
					workflowDefinitionLinkModelImpl.getClassPK(),
					workflowDefinitionLinkModelImpl.getTypePK()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_G_C_C_C_T, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_G_C_C_C_T, args,
				workflowDefinitionLinkModelImpl);
		}
		else {
			if ((workflowDefinitionLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_G_C_C_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workflowDefinitionLinkModelImpl.getGroupId(),
						workflowDefinitionLinkModelImpl.getCompanyId(),
						workflowDefinitionLinkModelImpl.getClassNameId(),
						workflowDefinitionLinkModelImpl.getClassPK(),
						workflowDefinitionLinkModelImpl.getTypePK()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_G_C_C_C_T, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_G_C_C_C_T, args,
					workflowDefinitionLinkModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		WorkflowDefinitionLinkModelImpl workflowDefinitionLinkModelImpl) {
		Object[] args = new Object[] {
				workflowDefinitionLinkModelImpl.getGroupId(),
				workflowDefinitionLinkModelImpl.getCompanyId(),
				workflowDefinitionLinkModelImpl.getClassNameId(),
				workflowDefinitionLinkModelImpl.getClassPK(),
				workflowDefinitionLinkModelImpl.getTypePK()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_G_C_C_C_T, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_G_C_C_C_T, args);

		if ((workflowDefinitionLinkModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_C_C_C_T.getColumnBitmask()) != 0) {
			args = new Object[] {
					workflowDefinitionLinkModelImpl.getOriginalGroupId(),
					workflowDefinitionLinkModelImpl.getOriginalCompanyId(),
					workflowDefinitionLinkModelImpl.getOriginalClassNameId(),
					workflowDefinitionLinkModelImpl.getOriginalClassPK(),
					workflowDefinitionLinkModelImpl.getOriginalTypePK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_C_C_C_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_C_C_C_T, args);
		}
	}

	/**
	 * Creates a new workflow definition link with the primary key. Does not add the workflow definition link to the database.
	 *
	 * @param workflowDefinitionLinkId the primary key for the new workflow definition link
	 * @return the new workflow definition link
	 */
	@Override
	public WorkflowDefinitionLink create(long workflowDefinitionLinkId) {
		WorkflowDefinitionLink workflowDefinitionLink = new WorkflowDefinitionLinkImpl();

		workflowDefinitionLink.setNew(true);
		workflowDefinitionLink.setPrimaryKey(workflowDefinitionLinkId);

		workflowDefinitionLink.setCompanyId(companyProvider.getCompanyId());

		return workflowDefinitionLink;
	}

	/**
	 * Removes the workflow definition link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param workflowDefinitionLinkId the primary key of the workflow definition link
	 * @return the workflow definition link that was removed
	 * @throws NoSuchWorkflowDefinitionLinkException if a workflow definition link with the primary key could not be found
	 */
	@Override
	public WorkflowDefinitionLink remove(long workflowDefinitionLinkId)
		throws NoSuchWorkflowDefinitionLinkException {
		return remove((Serializable)workflowDefinitionLinkId);
	}

	/**
	 * Removes the workflow definition link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the workflow definition link
	 * @return the workflow definition link that was removed
	 * @throws NoSuchWorkflowDefinitionLinkException if a workflow definition link with the primary key could not be found
	 */
	@Override
	public WorkflowDefinitionLink remove(Serializable primaryKey)
		throws NoSuchWorkflowDefinitionLinkException {
		Session session = null;

		try {
			session = openSession();

			WorkflowDefinitionLink workflowDefinitionLink = (WorkflowDefinitionLink)session.get(WorkflowDefinitionLinkImpl.class,
					primaryKey);

			if (workflowDefinitionLink == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWorkflowDefinitionLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(workflowDefinitionLink);
		}
		catch (NoSuchWorkflowDefinitionLinkException nsee) {
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
	protected WorkflowDefinitionLink removeImpl(
		WorkflowDefinitionLink workflowDefinitionLink) {
		workflowDefinitionLink = toUnwrappedModel(workflowDefinitionLink);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(workflowDefinitionLink)) {
				workflowDefinitionLink = (WorkflowDefinitionLink)session.get(WorkflowDefinitionLinkImpl.class,
						workflowDefinitionLink.getPrimaryKeyObj());
			}

			if (workflowDefinitionLink != null) {
				session.delete(workflowDefinitionLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (workflowDefinitionLink != null) {
			clearCache(workflowDefinitionLink);
		}

		return workflowDefinitionLink;
	}

	@Override
	public WorkflowDefinitionLink updateImpl(
		WorkflowDefinitionLink workflowDefinitionLink) {
		workflowDefinitionLink = toUnwrappedModel(workflowDefinitionLink);

		boolean isNew = workflowDefinitionLink.isNew();

		WorkflowDefinitionLinkModelImpl workflowDefinitionLinkModelImpl = (WorkflowDefinitionLinkModelImpl)workflowDefinitionLink;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (workflowDefinitionLink.getCreateDate() == null)) {
			if (serviceContext == null) {
				workflowDefinitionLink.setCreateDate(now);
			}
			else {
				workflowDefinitionLink.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!workflowDefinitionLinkModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				workflowDefinitionLink.setModifiedDate(now);
			}
			else {
				workflowDefinitionLink.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (workflowDefinitionLink.isNew()) {
				session.save(workflowDefinitionLink);

				workflowDefinitionLink.setNew(false);
			}
			else {
				workflowDefinitionLink = (WorkflowDefinitionLink)session.merge(workflowDefinitionLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WorkflowDefinitionLinkModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((workflowDefinitionLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workflowDefinitionLinkModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						workflowDefinitionLinkModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((workflowDefinitionLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workflowDefinitionLinkModelImpl.getOriginalGroupId(),
						workflowDefinitionLinkModelImpl.getOriginalCompanyId(),
						workflowDefinitionLinkModelImpl.getOriginalClassNameId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C,
					args);

				args = new Object[] {
						workflowDefinitionLinkModelImpl.getGroupId(),
						workflowDefinitionLinkModelImpl.getCompanyId(),
						workflowDefinitionLinkModelImpl.getClassNameId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C,
					args);
			}

			if ((workflowDefinitionLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_W_W.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workflowDefinitionLinkModelImpl.getOriginalCompanyId(),
						workflowDefinitionLinkModelImpl.getOriginalWorkflowDefinitionName(),
						workflowDefinitionLinkModelImpl.getOriginalWorkflowDefinitionVersion()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_W_W, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_W_W,
					args);

				args = new Object[] {
						workflowDefinitionLinkModelImpl.getCompanyId(),
						workflowDefinitionLinkModelImpl.getWorkflowDefinitionName(),
						workflowDefinitionLinkModelImpl.getWorkflowDefinitionVersion()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_W_W, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_W_W,
					args);
			}
		}

		entityCache.putResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			workflowDefinitionLink.getPrimaryKey(), workflowDefinitionLink,
			false);

		clearUniqueFindersCache(workflowDefinitionLinkModelImpl);
		cacheUniqueFindersCache(workflowDefinitionLinkModelImpl, isNew);

		workflowDefinitionLink.resetOriginalValues();

		return workflowDefinitionLink;
	}

	protected WorkflowDefinitionLink toUnwrappedModel(
		WorkflowDefinitionLink workflowDefinitionLink) {
		if (workflowDefinitionLink instanceof WorkflowDefinitionLinkImpl) {
			return workflowDefinitionLink;
		}

		WorkflowDefinitionLinkImpl workflowDefinitionLinkImpl = new WorkflowDefinitionLinkImpl();

		workflowDefinitionLinkImpl.setNew(workflowDefinitionLink.isNew());
		workflowDefinitionLinkImpl.setPrimaryKey(workflowDefinitionLink.getPrimaryKey());

		workflowDefinitionLinkImpl.setMvccVersion(workflowDefinitionLink.getMvccVersion());
		workflowDefinitionLinkImpl.setWorkflowDefinitionLinkId(workflowDefinitionLink.getWorkflowDefinitionLinkId());
		workflowDefinitionLinkImpl.setGroupId(workflowDefinitionLink.getGroupId());
		workflowDefinitionLinkImpl.setCompanyId(workflowDefinitionLink.getCompanyId());
		workflowDefinitionLinkImpl.setUserId(workflowDefinitionLink.getUserId());
		workflowDefinitionLinkImpl.setUserName(workflowDefinitionLink.getUserName());
		workflowDefinitionLinkImpl.setCreateDate(workflowDefinitionLink.getCreateDate());
		workflowDefinitionLinkImpl.setModifiedDate(workflowDefinitionLink.getModifiedDate());
		workflowDefinitionLinkImpl.setClassNameId(workflowDefinitionLink.getClassNameId());
		workflowDefinitionLinkImpl.setClassPK(workflowDefinitionLink.getClassPK());
		workflowDefinitionLinkImpl.setTypePK(workflowDefinitionLink.getTypePK());
		workflowDefinitionLinkImpl.setWorkflowDefinitionName(workflowDefinitionLink.getWorkflowDefinitionName());
		workflowDefinitionLinkImpl.setWorkflowDefinitionVersion(workflowDefinitionLink.getWorkflowDefinitionVersion());

		return workflowDefinitionLinkImpl;
	}

	/**
	 * Returns the workflow definition link with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow definition link
	 * @return the workflow definition link
	 * @throws NoSuchWorkflowDefinitionLinkException if a workflow definition link with the primary key could not be found
	 */
	@Override
	public WorkflowDefinitionLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWorkflowDefinitionLinkException {
		WorkflowDefinitionLink workflowDefinitionLink = fetchByPrimaryKey(primaryKey);

		if (workflowDefinitionLink == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWorkflowDefinitionLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return workflowDefinitionLink;
	}

	/**
	 * Returns the workflow definition link with the primary key or throws a {@link NoSuchWorkflowDefinitionLinkException} if it could not be found.
	 *
	 * @param workflowDefinitionLinkId the primary key of the workflow definition link
	 * @return the workflow definition link
	 * @throws NoSuchWorkflowDefinitionLinkException if a workflow definition link with the primary key could not be found
	 */
	@Override
	public WorkflowDefinitionLink findByPrimaryKey(
		long workflowDefinitionLinkId)
		throws NoSuchWorkflowDefinitionLinkException {
		return findByPrimaryKey((Serializable)workflowDefinitionLinkId);
	}

	/**
	 * Returns the workflow definition link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow definition link
	 * @return the workflow definition link, or <code>null</code> if a workflow definition link with the primary key could not be found
	 */
	@Override
	public WorkflowDefinitionLink fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowDefinitionLinkImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WorkflowDefinitionLink workflowDefinitionLink = (WorkflowDefinitionLink)serializable;

		if (workflowDefinitionLink == null) {
			Session session = null;

			try {
				session = openSession();

				workflowDefinitionLink = (WorkflowDefinitionLink)session.get(WorkflowDefinitionLinkImpl.class,
						primaryKey);

				if (workflowDefinitionLink != null) {
					cacheResult(workflowDefinitionLink);
				}
				else {
					entityCache.putResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowDefinitionLinkImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowDefinitionLinkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return workflowDefinitionLink;
	}

	/**
	 * Returns the workflow definition link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param workflowDefinitionLinkId the primary key of the workflow definition link
	 * @return the workflow definition link, or <code>null</code> if a workflow definition link with the primary key could not be found
	 */
	@Override
	public WorkflowDefinitionLink fetchByPrimaryKey(
		long workflowDefinitionLinkId) {
		return fetchByPrimaryKey((Serializable)workflowDefinitionLinkId);
	}

	@Override
	public Map<Serializable, WorkflowDefinitionLink> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WorkflowDefinitionLink> map = new HashMap<Serializable, WorkflowDefinitionLink>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WorkflowDefinitionLink workflowDefinitionLink = fetchByPrimaryKey(primaryKey);

			if (workflowDefinitionLink != null) {
				map.put(primaryKey, workflowDefinitionLink);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowDefinitionLinkImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WorkflowDefinitionLink)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE_PKS_IN);

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

			for (WorkflowDefinitionLink workflowDefinitionLink : (List<WorkflowDefinitionLink>)q.list()) {
				map.put(workflowDefinitionLink.getPrimaryKeyObj(),
					workflowDefinitionLink);

				cacheResult(workflowDefinitionLink);

				uncachedPrimaryKeys.remove(workflowDefinitionLink.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowDefinitionLinkImpl.class, primaryKey, nullModel);
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
	 * Returns all the workflow definition links.
	 *
	 * @return the workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow definition links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @return the range of workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow definition links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findAll(int start, int end,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the workflow definition links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of workflow definition links
	 */
	@Override
	public List<WorkflowDefinitionLink> findAll(int start, int end,
		OrderByComparator<WorkflowDefinitionLink> orderByComparator,
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

		List<WorkflowDefinitionLink> list = null;

		if (retrieveFromCache) {
			list = (List<WorkflowDefinitionLink>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WORKFLOWDEFINITIONLINK;

				if (pagination) {
					sql = sql.concat(WorkflowDefinitionLinkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WorkflowDefinitionLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkflowDefinitionLink>)QueryUtil.list(q,
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
	 * Removes all the workflow definition links from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WorkflowDefinitionLink workflowDefinitionLink : findAll()) {
			remove(workflowDefinitionLink);
		}
	}

	/**
	 * Returns the number of workflow definition links.
	 *
	 * @return the number of workflow definition links
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WORKFLOWDEFINITIONLINK);

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
		return WorkflowDefinitionLinkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the workflow definition link persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WorkflowDefinitionLinkImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_WORKFLOWDEFINITIONLINK = "SELECT workflowDefinitionLink FROM WorkflowDefinitionLink workflowDefinitionLink";
	private static final String _SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE_PKS_IN = "SELECT workflowDefinitionLink FROM WorkflowDefinitionLink workflowDefinitionLink WHERE workflowDefinitionLinkId IN (";
	private static final String _SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE = "SELECT workflowDefinitionLink FROM WorkflowDefinitionLink workflowDefinitionLink WHERE ";
	private static final String _SQL_COUNT_WORKFLOWDEFINITIONLINK = "SELECT COUNT(workflowDefinitionLink) FROM WorkflowDefinitionLink workflowDefinitionLink";
	private static final String _SQL_COUNT_WORKFLOWDEFINITIONLINK_WHERE = "SELECT COUNT(workflowDefinitionLink) FROM WorkflowDefinitionLink workflowDefinitionLink WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "workflowDefinitionLink.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkflowDefinitionLink exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WorkflowDefinitionLink exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(WorkflowDefinitionLinkPersistenceImpl.class);
}