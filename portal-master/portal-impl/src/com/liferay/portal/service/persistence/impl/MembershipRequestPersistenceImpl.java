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
import com.liferay.portal.kernel.exception.NoSuchMembershipRequestException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.MembershipRequest;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.MembershipRequestPersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.MembershipRequestImpl;
import com.liferay.portal.model.impl.MembershipRequestModelImpl;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the membership request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MembershipRequestPersistence
 * @see com.liferay.portal.kernel.service.persistence.MembershipRequestUtil
 * @generated
 */
@ProviderType
public class MembershipRequestPersistenceImpl extends BasePersistenceImpl<MembershipRequest>
	implements MembershipRequestPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MembershipRequestUtil} to access the membership request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MembershipRequestImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			MembershipRequestModelImpl.GROUPID_COLUMN_BITMASK |
			MembershipRequestModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the membership requests where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the membership requests where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @return the range of matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByGroupId(long groupId, int start,
		int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the membership requests where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByGroupId(long groupId, int start,
		int end, OrderByComparator<MembershipRequest> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the membership requests where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByGroupId(long groupId, int start,
		int end, OrderByComparator<MembershipRequest> orderByComparator,
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

		List<MembershipRequest> list = null;

		if (retrieveFromCache) {
			list = (List<MembershipRequest>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MembershipRequest membershipRequest : list) {
					if ((groupId != membershipRequest.getGroupId())) {
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

			query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<MembershipRequest>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MembershipRequest>)QueryUtil.list(q,
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
	 * Returns the first membership request in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request
	 * @throws NoSuchMembershipRequestException if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest findByGroupId_First(long groupId,
		OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException {
		MembershipRequest membershipRequest = fetchByGroupId_First(groupId,
				orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the first membership request in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request, or <code>null</code> if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest fetchByGroupId_First(long groupId,
		OrderByComparator<MembershipRequest> orderByComparator) {
		List<MembershipRequest> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last membership request in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request
	 * @throws NoSuchMembershipRequestException if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest findByGroupId_Last(long groupId,
		OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException {
		MembershipRequest membershipRequest = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the last membership request in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request, or <code>null</code> if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest fetchByGroupId_Last(long groupId,
		OrderByComparator<MembershipRequest> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<MembershipRequest> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the membership requests before and after the current membership request in the ordered set where groupId = &#63;.
	 *
	 * @param membershipRequestId the primary key of the current membership request
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next membership request
	 * @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 */
	@Override
	public MembershipRequest[] findByGroupId_PrevAndNext(
		long membershipRequestId, long groupId,
		OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException {
		MembershipRequest membershipRequest = findByPrimaryKey(membershipRequestId);

		Session session = null;

		try {
			session = openSession();

			MembershipRequest[] array = new MembershipRequestImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, membershipRequest,
					groupId, orderByComparator, true);

			array[1] = membershipRequest;

			array[2] = getByGroupId_PrevAndNext(session, membershipRequest,
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

	protected MembershipRequest getByGroupId_PrevAndNext(Session session,
		MembershipRequest membershipRequest, long groupId,
		OrderByComparator<MembershipRequest> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

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
			query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(membershipRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MembershipRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the membership requests where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (MembershipRequest membershipRequest : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(membershipRequest);
		}
	}

	/**
	 * Returns the number of membership requests where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching membership requests
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MEMBERSHIPREQUEST_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "membershipRequest.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			MembershipRequestModelImpl.USERID_COLUMN_BITMASK |
			MembershipRequestModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the membership requests where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the membership requests where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @return the range of matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the membership requests where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByUserId(long userId, int start,
		int end, OrderByComparator<MembershipRequest> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the membership requests where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByUserId(long userId, int start,
		int end, OrderByComparator<MembershipRequest> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<MembershipRequest> list = null;

		if (retrieveFromCache) {
			list = (List<MembershipRequest>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MembershipRequest membershipRequest : list) {
					if ((userId != membershipRequest.getUserId())) {
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

			query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<MembershipRequest>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MembershipRequest>)QueryUtil.list(q,
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
	 * Returns the first membership request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request
	 * @throws NoSuchMembershipRequestException if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest findByUserId_First(long userId,
		OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException {
		MembershipRequest membershipRequest = fetchByUserId_First(userId,
				orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the first membership request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request, or <code>null</code> if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest fetchByUserId_First(long userId,
		OrderByComparator<MembershipRequest> orderByComparator) {
		List<MembershipRequest> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last membership request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request
	 * @throws NoSuchMembershipRequestException if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest findByUserId_Last(long userId,
		OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException {
		MembershipRequest membershipRequest = fetchByUserId_Last(userId,
				orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the last membership request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request, or <code>null</code> if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest fetchByUserId_Last(long userId,
		OrderByComparator<MembershipRequest> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<MembershipRequest> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the membership requests before and after the current membership request in the ordered set where userId = &#63;.
	 *
	 * @param membershipRequestId the primary key of the current membership request
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next membership request
	 * @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 */
	@Override
	public MembershipRequest[] findByUserId_PrevAndNext(
		long membershipRequestId, long userId,
		OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException {
		MembershipRequest membershipRequest = findByPrimaryKey(membershipRequestId);

		Session session = null;

		try {
			session = openSession();

			MembershipRequest[] array = new MembershipRequestImpl[3];

			array[0] = getByUserId_PrevAndNext(session, membershipRequest,
					userId, orderByComparator, true);

			array[1] = membershipRequest;

			array[2] = getByUserId_PrevAndNext(session, membershipRequest,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MembershipRequest getByUserId_PrevAndNext(Session session,
		MembershipRequest membershipRequest, long userId,
		OrderByComparator<MembershipRequest> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(membershipRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MembershipRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the membership requests where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (MembershipRequest membershipRequest : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(membershipRequest);
		}
	}

	/**
	 * Returns the number of membership requests where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching membership requests
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "membershipRequest.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_S",
			new String[] { Long.class.getName(), Long.class.getName() },
			MembershipRequestModelImpl.GROUPID_COLUMN_BITMASK |
			MembershipRequestModelImpl.STATUSID_COLUMN_BITMASK |
			MembershipRequestModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_S = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_S",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the membership requests where groupId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @return the matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByG_S(long groupId, long statusId) {
		return findByG_S(groupId, statusId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the membership requests where groupId = &#63; and statusId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @return the range of matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByG_S(long groupId, long statusId,
		int start, int end) {
		return findByG_S(groupId, statusId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the membership requests where groupId = &#63; and statusId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByG_S(long groupId, long statusId,
		int start, int end,
		OrderByComparator<MembershipRequest> orderByComparator) {
		return findByG_S(groupId, statusId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the membership requests where groupId = &#63; and statusId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByG_S(long groupId, long statusId,
		int start, int end,
		OrderByComparator<MembershipRequest> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] { groupId, statusId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] {
					groupId, statusId,
					
					start, end, orderByComparator
				};
		}

		List<MembershipRequest> list = null;

		if (retrieveFromCache) {
			list = (List<MembershipRequest>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MembershipRequest membershipRequest : list) {
					if ((groupId != membershipRequest.getGroupId()) ||
							(statusId != membershipRequest.getStatusId())) {
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

			query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_S_STATUSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(statusId);

				if (!pagination) {
					list = (List<MembershipRequest>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MembershipRequest>)QueryUtil.list(q,
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
	 * Returns the first membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request
	 * @throws NoSuchMembershipRequestException if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest findByG_S_First(long groupId, long statusId,
		OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException {
		MembershipRequest membershipRequest = fetchByG_S_First(groupId,
				statusId, orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", statusId=");
		msg.append(statusId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the first membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request, or <code>null</code> if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest fetchByG_S_First(long groupId, long statusId,
		OrderByComparator<MembershipRequest> orderByComparator) {
		List<MembershipRequest> list = findByG_S(groupId, statusId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request
	 * @throws NoSuchMembershipRequestException if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest findByG_S_Last(long groupId, long statusId,
		OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException {
		MembershipRequest membershipRequest = fetchByG_S_Last(groupId,
				statusId, orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", statusId=");
		msg.append(statusId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the last membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request, or <code>null</code> if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest fetchByG_S_Last(long groupId, long statusId,
		OrderByComparator<MembershipRequest> orderByComparator) {
		int count = countByG_S(groupId, statusId);

		if (count == 0) {
			return null;
		}

		List<MembershipRequest> list = findByG_S(groupId, statusId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the membership requests before and after the current membership request in the ordered set where groupId = &#63; and statusId = &#63;.
	 *
	 * @param membershipRequestId the primary key of the current membership request
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next membership request
	 * @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 */
	@Override
	public MembershipRequest[] findByG_S_PrevAndNext(long membershipRequestId,
		long groupId, long statusId,
		OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException {
		MembershipRequest membershipRequest = findByPrimaryKey(membershipRequestId);

		Session session = null;

		try {
			session = openSession();

			MembershipRequest[] array = new MembershipRequestImpl[3];

			array[0] = getByG_S_PrevAndNext(session, membershipRequest,
					groupId, statusId, orderByComparator, true);

			array[1] = membershipRequest;

			array[2] = getByG_S_PrevAndNext(session, membershipRequest,
					groupId, statusId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MembershipRequest getByG_S_PrevAndNext(Session session,
		MembershipRequest membershipRequest, long groupId, long statusId,
		OrderByComparator<MembershipRequest> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUSID_2);

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
			query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(statusId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(membershipRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MembershipRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the membership requests where groupId = &#63; and statusId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 */
	@Override
	public void removeByG_S(long groupId, long statusId) {
		for (MembershipRequest membershipRequest : findByG_S(groupId, statusId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(membershipRequest);
		}
	}

	/**
	 * Returns the number of membership requests where groupId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param statusId the status ID
	 * @return the number of matching membership requests
	 */
	@Override
	public int countByG_S(long groupId, long statusId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_S;

		Object[] finderArgs = new Object[] { groupId, statusId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_S_STATUSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(statusId);

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

	private static final String _FINDER_COLUMN_G_S_GROUPID_2 = "membershipRequest.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_S_STATUSID_2 = "membershipRequest.statusId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_S = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			MembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			MembershipRequestModelImpl.GROUPID_COLUMN_BITMASK |
			MembershipRequestModelImpl.USERID_COLUMN_BITMASK |
			MembershipRequestModelImpl.STATUSID_COLUMN_BITMASK |
			MembershipRequestModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U_S = new FinderPath(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @return the matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByG_U_S(long groupId, long userId,
		long statusId) {
		return findByG_U_S(groupId, userId, statusId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @return the range of matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByG_U_S(long groupId, long userId,
		long statusId, int start, int end) {
		return findByG_U_S(groupId, userId, statusId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByG_U_S(long groupId, long userId,
		long statusId, int start, int end,
		OrderByComparator<MembershipRequest> orderByComparator) {
		return findByG_U_S(groupId, userId, statusId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching membership requests
	 */
	@Override
	public List<MembershipRequest> findByG_U_S(long groupId, long userId,
		long statusId, int start, int end,
		OrderByComparator<MembershipRequest> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S;
			finderArgs = new Object[] { groupId, userId, statusId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_S;
			finderArgs = new Object[] {
					groupId, userId, statusId,
					
					start, end, orderByComparator
				};
		}

		List<MembershipRequest> list = null;

		if (retrieveFromCache) {
			list = (List<MembershipRequest>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MembershipRequest membershipRequest : list) {
					if ((groupId != membershipRequest.getGroupId()) ||
							(userId != membershipRequest.getUserId()) ||
							(statusId != membershipRequest.getStatusId())) {
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

			query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			query.append(_FINDER_COLUMN_G_U_S_STATUSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(statusId);

				if (!pagination) {
					list = (List<MembershipRequest>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MembershipRequest>)QueryUtil.list(q,
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
	 * Returns the first membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request
	 * @throws NoSuchMembershipRequestException if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest findByG_U_S_First(long groupId, long userId,
		long statusId, OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException {
		MembershipRequest membershipRequest = fetchByG_U_S_First(groupId,
				userId, statusId, orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", statusId=");
		msg.append(statusId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the first membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching membership request, or <code>null</code> if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest fetchByG_U_S_First(long groupId, long userId,
		long statusId, OrderByComparator<MembershipRequest> orderByComparator) {
		List<MembershipRequest> list = findByG_U_S(groupId, userId, statusId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request
	 * @throws NoSuchMembershipRequestException if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest findByG_U_S_Last(long groupId, long userId,
		long statusId, OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException {
		MembershipRequest membershipRequest = fetchByG_U_S_Last(groupId,
				userId, statusId, orderByComparator);

		if (membershipRequest != null) {
			return membershipRequest;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", statusId=");
		msg.append(statusId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the last membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching membership request, or <code>null</code> if a matching membership request could not be found
	 */
	@Override
	public MembershipRequest fetchByG_U_S_Last(long groupId, long userId,
		long statusId, OrderByComparator<MembershipRequest> orderByComparator) {
		int count = countByG_U_S(groupId, userId, statusId);

		if (count == 0) {
			return null;
		}

		List<MembershipRequest> list = findByG_U_S(groupId, userId, statusId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the membership requests before and after the current membership request in the ordered set where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param membershipRequestId the primary key of the current membership request
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next membership request
	 * @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 */
	@Override
	public MembershipRequest[] findByG_U_S_PrevAndNext(
		long membershipRequestId, long groupId, long userId, long statusId,
		OrderByComparator<MembershipRequest> orderByComparator)
		throws NoSuchMembershipRequestException {
		MembershipRequest membershipRequest = findByPrimaryKey(membershipRequestId);

		Session session = null;

		try {
			session = openSession();

			MembershipRequest[] array = new MembershipRequestImpl[3];

			array[0] = getByG_U_S_PrevAndNext(session, membershipRequest,
					groupId, userId, statusId, orderByComparator, true);

			array[1] = membershipRequest;

			array[2] = getByG_U_S_PrevAndNext(session, membershipRequest,
					groupId, userId, statusId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MembershipRequest getByG_U_S_PrevAndNext(Session session,
		MembershipRequest membershipRequest, long groupId, long userId,
		long statusId, OrderByComparator<MembershipRequest> orderByComparator,
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

		query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE);

		query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_S_USERID_2);

		query.append(_FINDER_COLUMN_G_U_S_STATUSID_2);

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
			query.append(MembershipRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(statusId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(membershipRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MembershipRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the membership requests where groupId = &#63; and userId = &#63; and statusId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 */
	@Override
	public void removeByG_U_S(long groupId, long userId, long statusId) {
		for (MembershipRequest membershipRequest : findByG_U_S(groupId, userId,
				statusId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(membershipRequest);
		}
	}

	/**
	 * Returns the number of membership requests where groupId = &#63; and userId = &#63; and statusId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statusId the status ID
	 * @return the number of matching membership requests
	 */
	@Override
	public int countByG_U_S(long groupId, long userId, long statusId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_U_S;

		Object[] finderArgs = new Object[] { groupId, userId, statusId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_MEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			query.append(_FINDER_COLUMN_G_U_S_STATUSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(statusId);

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

	private static final String _FINDER_COLUMN_G_U_S_GROUPID_2 = "membershipRequest.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_S_USERID_2 = "membershipRequest.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_S_STATUSID_2 = "membershipRequest.statusId = ?";

	public MembershipRequestPersistenceImpl() {
		setModelClass(MembershipRequest.class);
	}

	/**
	 * Caches the membership request in the entity cache if it is enabled.
	 *
	 * @param membershipRequest the membership request
	 */
	@Override
	public void cacheResult(MembershipRequest membershipRequest) {
		entityCache.putResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestImpl.class, membershipRequest.getPrimaryKey(),
			membershipRequest);

		membershipRequest.resetOriginalValues();
	}

	/**
	 * Caches the membership requests in the entity cache if it is enabled.
	 *
	 * @param membershipRequests the membership requests
	 */
	@Override
	public void cacheResult(List<MembershipRequest> membershipRequests) {
		for (MembershipRequest membershipRequest : membershipRequests) {
			if (entityCache.getResult(
						MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
						MembershipRequestImpl.class,
						membershipRequest.getPrimaryKey()) == null) {
				cacheResult(membershipRequest);
			}
			else {
				membershipRequest.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all membership requests.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MembershipRequestImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the membership request.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MembershipRequest membershipRequest) {
		entityCache.removeResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestImpl.class, membershipRequest.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MembershipRequest> membershipRequests) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MembershipRequest membershipRequest : membershipRequests) {
			entityCache.removeResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
				MembershipRequestImpl.class, membershipRequest.getPrimaryKey());
		}
	}

	/**
	 * Creates a new membership request with the primary key. Does not add the membership request to the database.
	 *
	 * @param membershipRequestId the primary key for the new membership request
	 * @return the new membership request
	 */
	@Override
	public MembershipRequest create(long membershipRequestId) {
		MembershipRequest membershipRequest = new MembershipRequestImpl();

		membershipRequest.setNew(true);
		membershipRequest.setPrimaryKey(membershipRequestId);

		membershipRequest.setCompanyId(companyProvider.getCompanyId());

		return membershipRequest;
	}

	/**
	 * Removes the membership request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param membershipRequestId the primary key of the membership request
	 * @return the membership request that was removed
	 * @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 */
	@Override
	public MembershipRequest remove(long membershipRequestId)
		throws NoSuchMembershipRequestException {
		return remove((Serializable)membershipRequestId);
	}

	/**
	 * Removes the membership request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the membership request
	 * @return the membership request that was removed
	 * @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 */
	@Override
	public MembershipRequest remove(Serializable primaryKey)
		throws NoSuchMembershipRequestException {
		Session session = null;

		try {
			session = openSession();

			MembershipRequest membershipRequest = (MembershipRequest)session.get(MembershipRequestImpl.class,
					primaryKey);

			if (membershipRequest == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMembershipRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(membershipRequest);
		}
		catch (NoSuchMembershipRequestException nsee) {
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
	protected MembershipRequest removeImpl(MembershipRequest membershipRequest) {
		membershipRequest = toUnwrappedModel(membershipRequest);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(membershipRequest)) {
				membershipRequest = (MembershipRequest)session.get(MembershipRequestImpl.class,
						membershipRequest.getPrimaryKeyObj());
			}

			if (membershipRequest != null) {
				session.delete(membershipRequest);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (membershipRequest != null) {
			clearCache(membershipRequest);
		}

		return membershipRequest;
	}

	@Override
	public MembershipRequest updateImpl(MembershipRequest membershipRequest) {
		membershipRequest = toUnwrappedModel(membershipRequest);

		boolean isNew = membershipRequest.isNew();

		MembershipRequestModelImpl membershipRequestModelImpl = (MembershipRequestModelImpl)membershipRequest;

		Session session = null;

		try {
			session = openSession();

			if (membershipRequest.isNew()) {
				session.save(membershipRequest);

				membershipRequest.setNew(false);
			}
			else {
				membershipRequest = (MembershipRequest)session.merge(membershipRequest);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MembershipRequestModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((membershipRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						membershipRequestModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { membershipRequestModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((membershipRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						membershipRequestModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { membershipRequestModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((membershipRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						membershipRequestModelImpl.getOriginalGroupId(),
						membershipRequestModelImpl.getOriginalStatusId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);

				args = new Object[] {
						membershipRequestModelImpl.getGroupId(),
						membershipRequestModelImpl.getStatusId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);
			}

			if ((membershipRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						membershipRequestModelImpl.getOriginalGroupId(),
						membershipRequestModelImpl.getOriginalUserId(),
						membershipRequestModelImpl.getOriginalStatusId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S,
					args);

				args = new Object[] {
						membershipRequestModelImpl.getGroupId(),
						membershipRequestModelImpl.getUserId(),
						membershipRequestModelImpl.getStatusId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S,
					args);
			}
		}

		entityCache.putResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			MembershipRequestImpl.class, membershipRequest.getPrimaryKey(),
			membershipRequest, false);

		membershipRequest.resetOriginalValues();

		return membershipRequest;
	}

	protected MembershipRequest toUnwrappedModel(
		MembershipRequest membershipRequest) {
		if (membershipRequest instanceof MembershipRequestImpl) {
			return membershipRequest;
		}

		MembershipRequestImpl membershipRequestImpl = new MembershipRequestImpl();

		membershipRequestImpl.setNew(membershipRequest.isNew());
		membershipRequestImpl.setPrimaryKey(membershipRequest.getPrimaryKey());

		membershipRequestImpl.setMvccVersion(membershipRequest.getMvccVersion());
		membershipRequestImpl.setMembershipRequestId(membershipRequest.getMembershipRequestId());
		membershipRequestImpl.setGroupId(membershipRequest.getGroupId());
		membershipRequestImpl.setCompanyId(membershipRequest.getCompanyId());
		membershipRequestImpl.setUserId(membershipRequest.getUserId());
		membershipRequestImpl.setCreateDate(membershipRequest.getCreateDate());
		membershipRequestImpl.setComments(membershipRequest.getComments());
		membershipRequestImpl.setReplyComments(membershipRequest.getReplyComments());
		membershipRequestImpl.setReplyDate(membershipRequest.getReplyDate());
		membershipRequestImpl.setReplierUserId(membershipRequest.getReplierUserId());
		membershipRequestImpl.setStatusId(membershipRequest.getStatusId());

		return membershipRequestImpl;
	}

	/**
	 * Returns the membership request with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the membership request
	 * @return the membership request
	 * @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 */
	@Override
	public MembershipRequest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMembershipRequestException {
		MembershipRequest membershipRequest = fetchByPrimaryKey(primaryKey);

		if (membershipRequest == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMembershipRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return membershipRequest;
	}

	/**
	 * Returns the membership request with the primary key or throws a {@link NoSuchMembershipRequestException} if it could not be found.
	 *
	 * @param membershipRequestId the primary key of the membership request
	 * @return the membership request
	 * @throws NoSuchMembershipRequestException if a membership request with the primary key could not be found
	 */
	@Override
	public MembershipRequest findByPrimaryKey(long membershipRequestId)
		throws NoSuchMembershipRequestException {
		return findByPrimaryKey((Serializable)membershipRequestId);
	}

	/**
	 * Returns the membership request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the membership request
	 * @return the membership request, or <code>null</code> if a membership request with the primary key could not be found
	 */
	@Override
	public MembershipRequest fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
				MembershipRequestImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MembershipRequest membershipRequest = (MembershipRequest)serializable;

		if (membershipRequest == null) {
			Session session = null;

			try {
				session = openSession();

				membershipRequest = (MembershipRequest)session.get(MembershipRequestImpl.class,
						primaryKey);

				if (membershipRequest != null) {
					cacheResult(membershipRequest);
				}
				else {
					entityCache.putResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
						MembershipRequestImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
					MembershipRequestImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return membershipRequest;
	}

	/**
	 * Returns the membership request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param membershipRequestId the primary key of the membership request
	 * @return the membership request, or <code>null</code> if a membership request with the primary key could not be found
	 */
	@Override
	public MembershipRequest fetchByPrimaryKey(long membershipRequestId) {
		return fetchByPrimaryKey((Serializable)membershipRequestId);
	}

	@Override
	public Map<Serializable, MembershipRequest> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MembershipRequest> map = new HashMap<Serializable, MembershipRequest>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MembershipRequest membershipRequest = fetchByPrimaryKey(primaryKey);

			if (membershipRequest != null) {
				map.put(primaryKey, membershipRequest);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
					MembershipRequestImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MembershipRequest)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MEMBERSHIPREQUEST_WHERE_PKS_IN);

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

			for (MembershipRequest membershipRequest : (List<MembershipRequest>)q.list()) {
				map.put(membershipRequest.getPrimaryKeyObj(), membershipRequest);

				cacheResult(membershipRequest);

				uncachedPrimaryKeys.remove(membershipRequest.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
					MembershipRequestImpl.class, primaryKey, nullModel);
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
	 * Returns all the membership requests.
	 *
	 * @return the membership requests
	 */
	@Override
	public List<MembershipRequest> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the membership requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @return the range of membership requests
	 */
	@Override
	public List<MembershipRequest> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the membership requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of membership requests
	 */
	@Override
	public List<MembershipRequest> findAll(int start, int end,
		OrderByComparator<MembershipRequest> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the membership requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MembershipRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of membership requests
	 * @param end the upper bound of the range of membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of membership requests
	 */
	@Override
	public List<MembershipRequest> findAll(int start, int end,
		OrderByComparator<MembershipRequest> orderByComparator,
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

		List<MembershipRequest> list = null;

		if (retrieveFromCache) {
			list = (List<MembershipRequest>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MEMBERSHIPREQUEST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MEMBERSHIPREQUEST;

				if (pagination) {
					sql = sql.concat(MembershipRequestModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MembershipRequest>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MembershipRequest>)QueryUtil.list(q,
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
	 * Removes all the membership requests from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MembershipRequest membershipRequest : findAll()) {
			remove(membershipRequest);
		}
	}

	/**
	 * Returns the number of membership requests.
	 *
	 * @return the number of membership requests
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MEMBERSHIPREQUEST);

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
		return MembershipRequestModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the membership request persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MembershipRequestImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_MEMBERSHIPREQUEST = "SELECT membershipRequest FROM MembershipRequest membershipRequest";
	private static final String _SQL_SELECT_MEMBERSHIPREQUEST_WHERE_PKS_IN = "SELECT membershipRequest FROM MembershipRequest membershipRequest WHERE membershipRequestId IN (";
	private static final String _SQL_SELECT_MEMBERSHIPREQUEST_WHERE = "SELECT membershipRequest FROM MembershipRequest membershipRequest WHERE ";
	private static final String _SQL_COUNT_MEMBERSHIPREQUEST = "SELECT COUNT(membershipRequest) FROM MembershipRequest membershipRequest";
	private static final String _SQL_COUNT_MEMBERSHIPREQUEST_WHERE = "SELECT COUNT(membershipRequest) FROM MembershipRequest membershipRequest WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "membershipRequest.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MembershipRequest exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MembershipRequest exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(MembershipRequestPersistenceImpl.class);
}