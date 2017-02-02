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

package com.liferay.polls.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.polls.exception.NoSuchQuestionException;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.model.impl.PollsQuestionImpl;
import com.liferay.polls.model.impl.PollsQuestionModelImpl;
import com.liferay.polls.service.persistence.PollsQuestionPersistence;

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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
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
 * The persistence implementation for the polls question service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PollsQuestionPersistence
 * @see com.liferay.polls.service.persistence.PollsQuestionUtil
 * @generated
 */
@ProviderType
public class PollsQuestionPersistenceImpl extends BasePersistenceImpl<PollsQuestion>
	implements PollsQuestionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PollsQuestionUtil} to access the polls question persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PollsQuestionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED,
			PollsQuestionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED,
			PollsQuestionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED,
			PollsQuestionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED,
			PollsQuestionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			PollsQuestionModelImpl.UUID_COLUMN_BITMASK |
			PollsQuestionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the polls questions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching polls questions
	 */
	@Override
	public List<PollsQuestion> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls questions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @return the range of matching polls questions
	 */
	@Override
	public List<PollsQuestion> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls questions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching polls questions
	 */
	@Override
	public List<PollsQuestion> findByUuid(String uuid, int start, int end,
		OrderByComparator<PollsQuestion> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the polls questions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching polls questions
	 */
	@Override
	public List<PollsQuestion> findByUuid(String uuid, int start, int end,
		OrderByComparator<PollsQuestion> orderByComparator,
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

		List<PollsQuestion> list = null;

		if (retrieveFromCache) {
			list = (List<PollsQuestion>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PollsQuestion pollsQuestion : list) {
					if (!Objects.equals(uuid, pollsQuestion.getUuid())) {
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

			query.append(_SQL_SELECT_POLLSQUESTION_WHERE);

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
				query.append(PollsQuestionModelImpl.ORDER_BY_JPQL);
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
					list = (List<PollsQuestion>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PollsQuestion>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first polls question in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls question
	 * @throws NoSuchQuestionException if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion findByUuid_First(String uuid,
		OrderByComparator<PollsQuestion> orderByComparator)
		throws NoSuchQuestionException {
		PollsQuestion pollsQuestion = fetchByUuid_First(uuid, orderByComparator);

		if (pollsQuestion != null) {
			return pollsQuestion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the first polls question in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls question, or <code>null</code> if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion fetchByUuid_First(String uuid,
		OrderByComparator<PollsQuestion> orderByComparator) {
		List<PollsQuestion> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last polls question in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls question
	 * @throws NoSuchQuestionException if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion findByUuid_Last(String uuid,
		OrderByComparator<PollsQuestion> orderByComparator)
		throws NoSuchQuestionException {
		PollsQuestion pollsQuestion = fetchByUuid_Last(uuid, orderByComparator);

		if (pollsQuestion != null) {
			return pollsQuestion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the last polls question in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls question, or <code>null</code> if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion fetchByUuid_Last(String uuid,
		OrderByComparator<PollsQuestion> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PollsQuestion> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the polls questions before and after the current polls question in the ordered set where uuid = &#63;.
	 *
	 * @param questionId the primary key of the current polls question
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next polls question
	 * @throws NoSuchQuestionException if a polls question with the primary key could not be found
	 */
	@Override
	public PollsQuestion[] findByUuid_PrevAndNext(long questionId, String uuid,
		OrderByComparator<PollsQuestion> orderByComparator)
		throws NoSuchQuestionException {
		PollsQuestion pollsQuestion = findByPrimaryKey(questionId);

		Session session = null;

		try {
			session = openSession();

			PollsQuestion[] array = new PollsQuestionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, pollsQuestion, uuid,
					orderByComparator, true);

			array[1] = pollsQuestion;

			array[2] = getByUuid_PrevAndNext(session, pollsQuestion, uuid,
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

	protected PollsQuestion getByUuid_PrevAndNext(Session session,
		PollsQuestion pollsQuestion, String uuid,
		OrderByComparator<PollsQuestion> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POLLSQUESTION_WHERE);

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
			query.append(PollsQuestionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(pollsQuestion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PollsQuestion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the polls questions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PollsQuestion pollsQuestion : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(pollsQuestion);
		}
	}

	/**
	 * Returns the number of polls questions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching polls questions
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POLLSQUESTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "pollsQuestion.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "pollsQuestion.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(pollsQuestion.uuid IS NULL OR pollsQuestion.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED,
			PollsQuestionImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PollsQuestionModelImpl.UUID_COLUMN_BITMASK |
			PollsQuestionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the polls question where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchQuestionException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching polls question
	 * @throws NoSuchQuestionException if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion findByUUID_G(String uuid, long groupId)
		throws NoSuchQuestionException {
		PollsQuestion pollsQuestion = fetchByUUID_G(uuid, groupId);

		if (pollsQuestion == null) {
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

			throw new NoSuchQuestionException(msg.toString());
		}

		return pollsQuestion;
	}

	/**
	 * Returns the polls question where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching polls question, or <code>null</code> if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the polls question where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching polls question, or <code>null</code> if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PollsQuestion) {
			PollsQuestion pollsQuestion = (PollsQuestion)result;

			if (!Objects.equals(uuid, pollsQuestion.getUuid()) ||
					(groupId != pollsQuestion.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_POLLSQUESTION_WHERE);

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

				List<PollsQuestion> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					PollsQuestion pollsQuestion = list.get(0);

					result = pollsQuestion;

					cacheResult(pollsQuestion);

					if ((pollsQuestion.getUuid() == null) ||
							!pollsQuestion.getUuid().equals(uuid) ||
							(pollsQuestion.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, pollsQuestion);
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
			return (PollsQuestion)result;
		}
	}

	/**
	 * Removes the polls question where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the polls question that was removed
	 */
	@Override
	public PollsQuestion removeByUUID_G(String uuid, long groupId)
		throws NoSuchQuestionException {
		PollsQuestion pollsQuestion = findByUUID_G(uuid, groupId);

		return remove(pollsQuestion);
	}

	/**
	 * Returns the number of polls questions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching polls questions
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POLLSQUESTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "pollsQuestion.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "pollsQuestion.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(pollsQuestion.uuid IS NULL OR pollsQuestion.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "pollsQuestion.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED,
			PollsQuestionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED,
			PollsQuestionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PollsQuestionModelImpl.UUID_COLUMN_BITMASK |
			PollsQuestionModelImpl.COMPANYID_COLUMN_BITMASK |
			PollsQuestionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the polls questions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching polls questions
	 */
	@Override
	public List<PollsQuestion> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls questions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @return the range of matching polls questions
	 */
	@Override
	public List<PollsQuestion> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls questions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching polls questions
	 */
	@Override
	public List<PollsQuestion> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PollsQuestion> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the polls questions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching polls questions
	 */
	@Override
	public List<PollsQuestion> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PollsQuestion> orderByComparator,
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

		List<PollsQuestion> list = null;

		if (retrieveFromCache) {
			list = (List<PollsQuestion>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PollsQuestion pollsQuestion : list) {
					if (!Objects.equals(uuid, pollsQuestion.getUuid()) ||
							(companyId != pollsQuestion.getCompanyId())) {
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

			query.append(_SQL_SELECT_POLLSQUESTION_WHERE);

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
				query.append(PollsQuestionModelImpl.ORDER_BY_JPQL);
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
					list = (List<PollsQuestion>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PollsQuestion>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first polls question in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls question
	 * @throws NoSuchQuestionException if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PollsQuestion> orderByComparator)
		throws NoSuchQuestionException {
		PollsQuestion pollsQuestion = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (pollsQuestion != null) {
			return pollsQuestion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the first polls question in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls question, or <code>null</code> if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PollsQuestion> orderByComparator) {
		List<PollsQuestion> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last polls question in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls question
	 * @throws NoSuchQuestionException if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PollsQuestion> orderByComparator)
		throws NoSuchQuestionException {
		PollsQuestion pollsQuestion = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (pollsQuestion != null) {
			return pollsQuestion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the last polls question in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls question, or <code>null</code> if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PollsQuestion> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<PollsQuestion> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the polls questions before and after the current polls question in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param questionId the primary key of the current polls question
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next polls question
	 * @throws NoSuchQuestionException if a polls question with the primary key could not be found
	 */
	@Override
	public PollsQuestion[] findByUuid_C_PrevAndNext(long questionId,
		String uuid, long companyId,
		OrderByComparator<PollsQuestion> orderByComparator)
		throws NoSuchQuestionException {
		PollsQuestion pollsQuestion = findByPrimaryKey(questionId);

		Session session = null;

		try {
			session = openSession();

			PollsQuestion[] array = new PollsQuestionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, pollsQuestion, uuid,
					companyId, orderByComparator, true);

			array[1] = pollsQuestion;

			array[2] = getByUuid_C_PrevAndNext(session, pollsQuestion, uuid,
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

	protected PollsQuestion getByUuid_C_PrevAndNext(Session session,
		PollsQuestion pollsQuestion, String uuid, long companyId,
		OrderByComparator<PollsQuestion> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_POLLSQUESTION_WHERE);

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
			query.append(PollsQuestionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(pollsQuestion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PollsQuestion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the polls questions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (PollsQuestion pollsQuestion : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pollsQuestion);
		}
	}

	/**
	 * Returns the number of polls questions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching polls questions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POLLSQUESTION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "pollsQuestion.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "pollsQuestion.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(pollsQuestion.uuid IS NULL OR pollsQuestion.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "pollsQuestion.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED,
			PollsQuestionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED,
			PollsQuestionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupId", new String[] { Long.class.getName() },
			PollsQuestionModelImpl.GROUPID_COLUMN_BITMASK |
			PollsQuestionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the polls questions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching polls questions
	 */
	@Override
	public List<PollsQuestion> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls questions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @return the range of matching polls questions
	 */
	@Override
	public List<PollsQuestion> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls questions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching polls questions
	 */
	@Override
	public List<PollsQuestion> findByGroupId(long groupId, int start, int end,
		OrderByComparator<PollsQuestion> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the polls questions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching polls questions
	 */
	@Override
	public List<PollsQuestion> findByGroupId(long groupId, int start, int end,
		OrderByComparator<PollsQuestion> orderByComparator,
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

		List<PollsQuestion> list = null;

		if (retrieveFromCache) {
			list = (List<PollsQuestion>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PollsQuestion pollsQuestion : list) {
					if ((groupId != pollsQuestion.getGroupId())) {
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

			query.append(_SQL_SELECT_POLLSQUESTION_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PollsQuestionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<PollsQuestion>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PollsQuestion>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first polls question in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls question
	 * @throws NoSuchQuestionException if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion findByGroupId_First(long groupId,
		OrderByComparator<PollsQuestion> orderByComparator)
		throws NoSuchQuestionException {
		PollsQuestion pollsQuestion = fetchByGroupId_First(groupId,
				orderByComparator);

		if (pollsQuestion != null) {
			return pollsQuestion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the first polls question in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching polls question, or <code>null</code> if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion fetchByGroupId_First(long groupId,
		OrderByComparator<PollsQuestion> orderByComparator) {
		List<PollsQuestion> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last polls question in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls question
	 * @throws NoSuchQuestionException if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion findByGroupId_Last(long groupId,
		OrderByComparator<PollsQuestion> orderByComparator)
		throws NoSuchQuestionException {
		PollsQuestion pollsQuestion = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (pollsQuestion != null) {
			return pollsQuestion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQuestionException(msg.toString());
	}

	/**
	 * Returns the last polls question in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching polls question, or <code>null</code> if a matching polls question could not be found
	 */
	@Override
	public PollsQuestion fetchByGroupId_Last(long groupId,
		OrderByComparator<PollsQuestion> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<PollsQuestion> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the polls questions before and after the current polls question in the ordered set where groupId = &#63;.
	 *
	 * @param questionId the primary key of the current polls question
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next polls question
	 * @throws NoSuchQuestionException if a polls question with the primary key could not be found
	 */
	@Override
	public PollsQuestion[] findByGroupId_PrevAndNext(long questionId,
		long groupId, OrderByComparator<PollsQuestion> orderByComparator)
		throws NoSuchQuestionException {
		PollsQuestion pollsQuestion = findByPrimaryKey(questionId);

		Session session = null;

		try {
			session = openSession();

			PollsQuestion[] array = new PollsQuestionImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, pollsQuestion,
					groupId, orderByComparator, true);

			array[1] = pollsQuestion;

			array[2] = getByGroupId_PrevAndNext(session, pollsQuestion,
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

	protected PollsQuestion getByGroupId_PrevAndNext(Session session,
		PollsQuestion pollsQuestion, long groupId,
		OrderByComparator<PollsQuestion> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POLLSQUESTION_WHERE);

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
			query.append(PollsQuestionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(pollsQuestion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PollsQuestion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the polls questions that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching polls questions that the user has permission to view
	 */
	@Override
	public List<PollsQuestion> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls questions that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @return the range of matching polls questions that the user has permission to view
	 */
	@Override
	public List<PollsQuestion> filterFindByGroupId(long groupId, int start,
		int end) {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls questions that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching polls questions that the user has permission to view
	 */
	@Override
	public List<PollsQuestion> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<PollsQuestion> orderByComparator) {
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
			query.append(_FILTER_SQL_SELECT_POLLSQUESTION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_POLLSQUESTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_POLLSQUESTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(PollsQuestionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(PollsQuestionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PollsQuestion.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, PollsQuestionImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, PollsQuestionImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<PollsQuestion>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the polls questions before and after the current polls question in the ordered set of polls questions that the user has permission to view where groupId = &#63;.
	 *
	 * @param questionId the primary key of the current polls question
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next polls question
	 * @throws NoSuchQuestionException if a polls question with the primary key could not be found
	 */
	@Override
	public PollsQuestion[] filterFindByGroupId_PrevAndNext(long questionId,
		long groupId, OrderByComparator<PollsQuestion> orderByComparator)
		throws NoSuchQuestionException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(questionId, groupId,
				orderByComparator);
		}

		PollsQuestion pollsQuestion = findByPrimaryKey(questionId);

		Session session = null;

		try {
			session = openSession();

			PollsQuestion[] array = new PollsQuestionImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, pollsQuestion,
					groupId, orderByComparator, true);

			array[1] = pollsQuestion;

			array[2] = filterGetByGroupId_PrevAndNext(session, pollsQuestion,
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

	protected PollsQuestion filterGetByGroupId_PrevAndNext(Session session,
		PollsQuestion pollsQuestion, long groupId,
		OrderByComparator<PollsQuestion> orderByComparator, boolean previous) {
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
			query.append(_FILTER_SQL_SELECT_POLLSQUESTION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_POLLSQUESTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_POLLSQUESTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(PollsQuestionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(PollsQuestionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PollsQuestion.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, PollsQuestionImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, PollsQuestionImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(pollsQuestion);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PollsQuestion> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the polls questions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (PollsQuestion pollsQuestion : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pollsQuestion);
		}
	}

	/**
	 * Returns the number of polls questions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching polls questions
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POLLSQUESTION_WHERE);

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
	 * Returns the number of polls questions that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching polls questions that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_POLLSQUESTION_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PollsQuestion.class.getName(),
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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "pollsQuestion.groupId = ?";

	public PollsQuestionPersistenceImpl() {
		setModelClass(PollsQuestion.class);
	}

	/**
	 * Caches the polls question in the entity cache if it is enabled.
	 *
	 * @param pollsQuestion the polls question
	 */
	@Override
	public void cacheResult(PollsQuestion pollsQuestion) {
		entityCache.putResult(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionImpl.class, pollsQuestion.getPrimaryKey(),
			pollsQuestion);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { pollsQuestion.getUuid(), pollsQuestion.getGroupId() },
			pollsQuestion);

		pollsQuestion.resetOriginalValues();
	}

	/**
	 * Caches the polls questions in the entity cache if it is enabled.
	 *
	 * @param pollsQuestions the polls questions
	 */
	@Override
	public void cacheResult(List<PollsQuestion> pollsQuestions) {
		for (PollsQuestion pollsQuestion : pollsQuestions) {
			if (entityCache.getResult(
						PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
						PollsQuestionImpl.class, pollsQuestion.getPrimaryKey()) == null) {
				cacheResult(pollsQuestion);
			}
			else {
				pollsQuestion.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all polls questions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PollsQuestionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the polls question.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PollsQuestion pollsQuestion) {
		entityCache.removeResult(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionImpl.class, pollsQuestion.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PollsQuestionModelImpl)pollsQuestion);
	}

	@Override
	public void clearCache(List<PollsQuestion> pollsQuestions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PollsQuestion pollsQuestion : pollsQuestions) {
			entityCache.removeResult(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
				PollsQuestionImpl.class, pollsQuestion.getPrimaryKey());

			clearUniqueFindersCache((PollsQuestionModelImpl)pollsQuestion);
		}
	}

	protected void cacheUniqueFindersCache(
		PollsQuestionModelImpl pollsQuestionModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					pollsQuestionModelImpl.getUuid(),
					pollsQuestionModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				pollsQuestionModelImpl);
		}
		else {
			if ((pollsQuestionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsQuestionModelImpl.getUuid(),
						pollsQuestionModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					pollsQuestionModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		PollsQuestionModelImpl pollsQuestionModelImpl) {
		Object[] args = new Object[] {
				pollsQuestionModelImpl.getUuid(),
				pollsQuestionModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((pollsQuestionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					pollsQuestionModelImpl.getOriginalUuid(),
					pollsQuestionModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new polls question with the primary key. Does not add the polls question to the database.
	 *
	 * @param questionId the primary key for the new polls question
	 * @return the new polls question
	 */
	@Override
	public PollsQuestion create(long questionId) {
		PollsQuestion pollsQuestion = new PollsQuestionImpl();

		pollsQuestion.setNew(true);
		pollsQuestion.setPrimaryKey(questionId);

		String uuid = PortalUUIDUtil.generate();

		pollsQuestion.setUuid(uuid);

		pollsQuestion.setCompanyId(companyProvider.getCompanyId());

		return pollsQuestion;
	}

	/**
	 * Removes the polls question with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param questionId the primary key of the polls question
	 * @return the polls question that was removed
	 * @throws NoSuchQuestionException if a polls question with the primary key could not be found
	 */
	@Override
	public PollsQuestion remove(long questionId) throws NoSuchQuestionException {
		return remove((Serializable)questionId);
	}

	/**
	 * Removes the polls question with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the polls question
	 * @return the polls question that was removed
	 * @throws NoSuchQuestionException if a polls question with the primary key could not be found
	 */
	@Override
	public PollsQuestion remove(Serializable primaryKey)
		throws NoSuchQuestionException {
		Session session = null;

		try {
			session = openSession();

			PollsQuestion pollsQuestion = (PollsQuestion)session.get(PollsQuestionImpl.class,
					primaryKey);

			if (pollsQuestion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchQuestionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(pollsQuestion);
		}
		catch (NoSuchQuestionException nsee) {
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
	protected PollsQuestion removeImpl(PollsQuestion pollsQuestion) {
		pollsQuestion = toUnwrappedModel(pollsQuestion);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(pollsQuestion)) {
				pollsQuestion = (PollsQuestion)session.get(PollsQuestionImpl.class,
						pollsQuestion.getPrimaryKeyObj());
			}

			if (pollsQuestion != null) {
				session.delete(pollsQuestion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (pollsQuestion != null) {
			clearCache(pollsQuestion);
		}

		return pollsQuestion;
	}

	@Override
	public PollsQuestion updateImpl(PollsQuestion pollsQuestion) {
		pollsQuestion = toUnwrappedModel(pollsQuestion);

		boolean isNew = pollsQuestion.isNew();

		PollsQuestionModelImpl pollsQuestionModelImpl = (PollsQuestionModelImpl)pollsQuestion;

		if (Validator.isNull(pollsQuestion.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			pollsQuestion.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (pollsQuestion.getCreateDate() == null)) {
			if (serviceContext == null) {
				pollsQuestion.setCreateDate(now);
			}
			else {
				pollsQuestion.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!pollsQuestionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				pollsQuestion.setModifiedDate(now);
			}
			else {
				pollsQuestion.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (pollsQuestion.isNew()) {
				session.save(pollsQuestion);

				pollsQuestion.setNew(false);
			}
			else {
				pollsQuestion = (PollsQuestion)session.merge(pollsQuestion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PollsQuestionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((pollsQuestionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsQuestionModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { pollsQuestionModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((pollsQuestionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsQuestionModelImpl.getOriginalUuid(),
						pollsQuestionModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						pollsQuestionModelImpl.getUuid(),
						pollsQuestionModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((pollsQuestionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pollsQuestionModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { pollsQuestionModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
			PollsQuestionImpl.class, pollsQuestion.getPrimaryKey(),
			pollsQuestion, false);

		clearUniqueFindersCache(pollsQuestionModelImpl);
		cacheUniqueFindersCache(pollsQuestionModelImpl, isNew);

		pollsQuestion.resetOriginalValues();

		return pollsQuestion;
	}

	protected PollsQuestion toUnwrappedModel(PollsQuestion pollsQuestion) {
		if (pollsQuestion instanceof PollsQuestionImpl) {
			return pollsQuestion;
		}

		PollsQuestionImpl pollsQuestionImpl = new PollsQuestionImpl();

		pollsQuestionImpl.setNew(pollsQuestion.isNew());
		pollsQuestionImpl.setPrimaryKey(pollsQuestion.getPrimaryKey());

		pollsQuestionImpl.setUuid(pollsQuestion.getUuid());
		pollsQuestionImpl.setQuestionId(pollsQuestion.getQuestionId());
		pollsQuestionImpl.setGroupId(pollsQuestion.getGroupId());
		pollsQuestionImpl.setCompanyId(pollsQuestion.getCompanyId());
		pollsQuestionImpl.setUserId(pollsQuestion.getUserId());
		pollsQuestionImpl.setUserName(pollsQuestion.getUserName());
		pollsQuestionImpl.setCreateDate(pollsQuestion.getCreateDate());
		pollsQuestionImpl.setModifiedDate(pollsQuestion.getModifiedDate());
		pollsQuestionImpl.setTitle(pollsQuestion.getTitle());
		pollsQuestionImpl.setDescription(pollsQuestion.getDescription());
		pollsQuestionImpl.setExpirationDate(pollsQuestion.getExpirationDate());
		pollsQuestionImpl.setLastPublishDate(pollsQuestion.getLastPublishDate());
		pollsQuestionImpl.setLastVoteDate(pollsQuestion.getLastVoteDate());

		return pollsQuestionImpl;
	}

	/**
	 * Returns the polls question with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the polls question
	 * @return the polls question
	 * @throws NoSuchQuestionException if a polls question with the primary key could not be found
	 */
	@Override
	public PollsQuestion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchQuestionException {
		PollsQuestion pollsQuestion = fetchByPrimaryKey(primaryKey);

		if (pollsQuestion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchQuestionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return pollsQuestion;
	}

	/**
	 * Returns the polls question with the primary key or throws a {@link NoSuchQuestionException} if it could not be found.
	 *
	 * @param questionId the primary key of the polls question
	 * @return the polls question
	 * @throws NoSuchQuestionException if a polls question with the primary key could not be found
	 */
	@Override
	public PollsQuestion findByPrimaryKey(long questionId)
		throws NoSuchQuestionException {
		return findByPrimaryKey((Serializable)questionId);
	}

	/**
	 * Returns the polls question with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the polls question
	 * @return the polls question, or <code>null</code> if a polls question with the primary key could not be found
	 */
	@Override
	public PollsQuestion fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
				PollsQuestionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PollsQuestion pollsQuestion = (PollsQuestion)serializable;

		if (pollsQuestion == null) {
			Session session = null;

			try {
				session = openSession();

				pollsQuestion = (PollsQuestion)session.get(PollsQuestionImpl.class,
						primaryKey);

				if (pollsQuestion != null) {
					cacheResult(pollsQuestion);
				}
				else {
					entityCache.putResult(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
						PollsQuestionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
					PollsQuestionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return pollsQuestion;
	}

	/**
	 * Returns the polls question with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param questionId the primary key of the polls question
	 * @return the polls question, or <code>null</code> if a polls question with the primary key could not be found
	 */
	@Override
	public PollsQuestion fetchByPrimaryKey(long questionId) {
		return fetchByPrimaryKey((Serializable)questionId);
	}

	@Override
	public Map<Serializable, PollsQuestion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PollsQuestion> map = new HashMap<Serializable, PollsQuestion>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PollsQuestion pollsQuestion = fetchByPrimaryKey(primaryKey);

			if (pollsQuestion != null) {
				map.put(primaryKey, pollsQuestion);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
					PollsQuestionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PollsQuestion)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_POLLSQUESTION_WHERE_PKS_IN);

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

			for (PollsQuestion pollsQuestion : (List<PollsQuestion>)q.list()) {
				map.put(pollsQuestion.getPrimaryKeyObj(), pollsQuestion);

				cacheResult(pollsQuestion);

				uncachedPrimaryKeys.remove(pollsQuestion.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PollsQuestionModelImpl.ENTITY_CACHE_ENABLED,
					PollsQuestionImpl.class, primaryKey, nullModel);
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
	 * Returns all the polls questions.
	 *
	 * @return the polls questions
	 */
	@Override
	public List<PollsQuestion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the polls questions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @return the range of polls questions
	 */
	@Override
	public List<PollsQuestion> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the polls questions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of polls questions
	 */
	@Override
	public List<PollsQuestion> findAll(int start, int end,
		OrderByComparator<PollsQuestion> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the polls questions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of polls questions
	 * @param end the upper bound of the range of polls questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of polls questions
	 */
	@Override
	public List<PollsQuestion> findAll(int start, int end,
		OrderByComparator<PollsQuestion> orderByComparator,
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

		List<PollsQuestion> list = null;

		if (retrieveFromCache) {
			list = (List<PollsQuestion>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_POLLSQUESTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_POLLSQUESTION;

				if (pagination) {
					sql = sql.concat(PollsQuestionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PollsQuestion>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PollsQuestion>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the polls questions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PollsQuestion pollsQuestion : findAll()) {
			remove(pollsQuestion);
		}
	}

	/**
	 * Returns the number of polls questions.
	 *
	 * @return the number of polls questions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_POLLSQUESTION);

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
		return PollsQuestionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the polls question persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PollsQuestionImpl.class.getName());
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
	private static final String _SQL_SELECT_POLLSQUESTION = "SELECT pollsQuestion FROM PollsQuestion pollsQuestion";
	private static final String _SQL_SELECT_POLLSQUESTION_WHERE_PKS_IN = "SELECT pollsQuestion FROM PollsQuestion pollsQuestion WHERE questionId IN (";
	private static final String _SQL_SELECT_POLLSQUESTION_WHERE = "SELECT pollsQuestion FROM PollsQuestion pollsQuestion WHERE ";
	private static final String _SQL_COUNT_POLLSQUESTION = "SELECT COUNT(pollsQuestion) FROM PollsQuestion pollsQuestion";
	private static final String _SQL_COUNT_POLLSQUESTION_WHERE = "SELECT COUNT(pollsQuestion) FROM PollsQuestion pollsQuestion WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "pollsQuestion.questionId";
	private static final String _FILTER_SQL_SELECT_POLLSQUESTION_WHERE = "SELECT DISTINCT {pollsQuestion.*} FROM PollsQuestion pollsQuestion WHERE ";
	private static final String _FILTER_SQL_SELECT_POLLSQUESTION_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {PollsQuestion.*} FROM (SELECT DISTINCT pollsQuestion.questionId FROM PollsQuestion pollsQuestion WHERE ";
	private static final String _FILTER_SQL_SELECT_POLLSQUESTION_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN PollsQuestion ON TEMP_TABLE.questionId = PollsQuestion.questionId";
	private static final String _FILTER_SQL_COUNT_POLLSQUESTION_WHERE = "SELECT COUNT(DISTINCT pollsQuestion.questionId) AS COUNT_VALUE FROM PollsQuestion pollsQuestion WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "pollsQuestion";
	private static final String _FILTER_ENTITY_TABLE = "PollsQuestion";
	private static final String _ORDER_BY_ENTITY_ALIAS = "pollsQuestion.";
	private static final String _ORDER_BY_ENTITY_TABLE = "PollsQuestion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PollsQuestion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PollsQuestion exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PollsQuestionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}