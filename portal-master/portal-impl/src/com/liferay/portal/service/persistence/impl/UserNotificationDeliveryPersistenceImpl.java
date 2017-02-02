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
import com.liferay.portal.kernel.exception.NoSuchUserNotificationDeliveryException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserNotificationDelivery;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.UserNotificationDeliveryPersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.UserNotificationDeliveryImpl;
import com.liferay.portal.model.impl.UserNotificationDeliveryModelImpl;

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
 * The persistence implementation for the user notification delivery service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserNotificationDeliveryPersistence
 * @see com.liferay.portal.kernel.service.persistence.UserNotificationDeliveryUtil
 * @generated
 */
@ProviderType
public class UserNotificationDeliveryPersistenceImpl extends BasePersistenceImpl<UserNotificationDelivery>
	implements UserNotificationDeliveryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserNotificationDeliveryUtil} to access the user notification delivery persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UserNotificationDeliveryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationDeliveryModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationDeliveryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationDeliveryModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationDeliveryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationDeliveryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationDeliveryModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationDeliveryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationDeliveryModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationDeliveryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			UserNotificationDeliveryModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationDeliveryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the user notification deliveries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching user notification deliveries
	 */
	@Override
	public List<UserNotificationDelivery> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification deliveries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationDeliveryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user notification deliveries
	 * @param end the upper bound of the range of user notification deliveries (not inclusive)
	 * @return the range of matching user notification deliveries
	 */
	@Override
	public List<UserNotificationDelivery> findByUserId(long userId, int start,
		int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification deliveries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationDeliveryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user notification deliveries
	 * @param end the upper bound of the range of user notification deliveries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user notification deliveries
	 */
	@Override
	public List<UserNotificationDelivery> findByUserId(long userId, int start,
		int end, OrderByComparator<UserNotificationDelivery> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user notification deliveries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationDeliveryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user notification deliveries
	 * @param end the upper bound of the range of user notification deliveries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching user notification deliveries
	 */
	@Override
	public List<UserNotificationDelivery> findByUserId(long userId, int start,
		int end, OrderByComparator<UserNotificationDelivery> orderByComparator,
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

		List<UserNotificationDelivery> list = null;

		if (retrieveFromCache) {
			list = (List<UserNotificationDelivery>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserNotificationDelivery userNotificationDelivery : list) {
					if ((userId != userNotificationDelivery.getUserId())) {
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

			query.append(_SQL_SELECT_USERNOTIFICATIONDELIVERY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserNotificationDeliveryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<UserNotificationDelivery>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserNotificationDelivery>)QueryUtil.list(q,
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
	 * Returns the first user notification delivery in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification delivery
	 * @throws NoSuchUserNotificationDeliveryException if a matching user notification delivery could not be found
	 */
	@Override
	public UserNotificationDelivery findByUserId_First(long userId,
		OrderByComparator<UserNotificationDelivery> orderByComparator)
		throws NoSuchUserNotificationDeliveryException {
		UserNotificationDelivery userNotificationDelivery = fetchByUserId_First(userId,
				orderByComparator);

		if (userNotificationDelivery != null) {
			return userNotificationDelivery;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationDeliveryException(msg.toString());
	}

	/**
	 * Returns the first user notification delivery in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification delivery, or <code>null</code> if a matching user notification delivery could not be found
	 */
	@Override
	public UserNotificationDelivery fetchByUserId_First(long userId,
		OrderByComparator<UserNotificationDelivery> orderByComparator) {
		List<UserNotificationDelivery> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user notification delivery in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification delivery
	 * @throws NoSuchUserNotificationDeliveryException if a matching user notification delivery could not be found
	 */
	@Override
	public UserNotificationDelivery findByUserId_Last(long userId,
		OrderByComparator<UserNotificationDelivery> orderByComparator)
		throws NoSuchUserNotificationDeliveryException {
		UserNotificationDelivery userNotificationDelivery = fetchByUserId_Last(userId,
				orderByComparator);

		if (userNotificationDelivery != null) {
			return userNotificationDelivery;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationDeliveryException(msg.toString());
	}

	/**
	 * Returns the last user notification delivery in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification delivery, or <code>null</code> if a matching user notification delivery could not be found
	 */
	@Override
	public UserNotificationDelivery fetchByUserId_Last(long userId,
		OrderByComparator<UserNotificationDelivery> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<UserNotificationDelivery> list = findByUserId(userId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user notification deliveries before and after the current user notification delivery in the ordered set where userId = &#63;.
	 *
	 * @param userNotificationDeliveryId the primary key of the current user notification delivery
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user notification delivery
	 * @throws NoSuchUserNotificationDeliveryException if a user notification delivery with the primary key could not be found
	 */
	@Override
	public UserNotificationDelivery[] findByUserId_PrevAndNext(
		long userNotificationDeliveryId, long userId,
		OrderByComparator<UserNotificationDelivery> orderByComparator)
		throws NoSuchUserNotificationDeliveryException {
		UserNotificationDelivery userNotificationDelivery = findByPrimaryKey(userNotificationDeliveryId);

		Session session = null;

		try {
			session = openSession();

			UserNotificationDelivery[] array = new UserNotificationDeliveryImpl[3];

			array[0] = getByUserId_PrevAndNext(session,
					userNotificationDelivery, userId, orderByComparator, true);

			array[1] = userNotificationDelivery;

			array[2] = getByUserId_PrevAndNext(session,
					userNotificationDelivery, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserNotificationDelivery getByUserId_PrevAndNext(
		Session session, UserNotificationDelivery userNotificationDelivery,
		long userId,
		OrderByComparator<UserNotificationDelivery> orderByComparator,
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

		query.append(_SQL_SELECT_USERNOTIFICATIONDELIVERY_WHERE);

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
			query.append(UserNotificationDeliveryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userNotificationDelivery);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserNotificationDelivery> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user notification deliveries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (UserNotificationDelivery userNotificationDelivery : findByUserId(
				userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userNotificationDelivery);
		}
	}

	/**
	 * Returns the number of user notification deliveries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user notification deliveries
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERNOTIFICATIONDELIVERY_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "userNotificationDelivery.userId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_P_C_N_D = new FinderPath(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationDeliveryModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationDeliveryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByU_P_C_N_D",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			UserNotificationDeliveryModelImpl.USERID_COLUMN_BITMASK |
			UserNotificationDeliveryModelImpl.PORTLETID_COLUMN_BITMASK |
			UserNotificationDeliveryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			UserNotificationDeliveryModelImpl.NOTIFICATIONTYPE_COLUMN_BITMASK |
			UserNotificationDeliveryModelImpl.DELIVERYTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_P_C_N_D = new FinderPath(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationDeliveryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_P_C_N_D",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns the user notification delivery where userId = &#63; and portletId = &#63; and classNameId = &#63; and notificationType = &#63; and deliveryType = &#63; or throws a {@link NoSuchUserNotificationDeliveryException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param portletId the portlet ID
	 * @param classNameId the class name ID
	 * @param notificationType the notification type
	 * @param deliveryType the delivery type
	 * @return the matching user notification delivery
	 * @throws NoSuchUserNotificationDeliveryException if a matching user notification delivery could not be found
	 */
	@Override
	public UserNotificationDelivery findByU_P_C_N_D(long userId,
		String portletId, long classNameId, int notificationType,
		int deliveryType) throws NoSuchUserNotificationDeliveryException {
		UserNotificationDelivery userNotificationDelivery = fetchByU_P_C_N_D(userId,
				portletId, classNameId, notificationType, deliveryType);

		if (userNotificationDelivery == null) {
			StringBundler msg = new StringBundler(12);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", portletId=");
			msg.append(portletId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", notificationType=");
			msg.append(notificationType);

			msg.append(", deliveryType=");
			msg.append(deliveryType);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchUserNotificationDeliveryException(msg.toString());
		}

		return userNotificationDelivery;
	}

	/**
	 * Returns the user notification delivery where userId = &#63; and portletId = &#63; and classNameId = &#63; and notificationType = &#63; and deliveryType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param portletId the portlet ID
	 * @param classNameId the class name ID
	 * @param notificationType the notification type
	 * @param deliveryType the delivery type
	 * @return the matching user notification delivery, or <code>null</code> if a matching user notification delivery could not be found
	 */
	@Override
	public UserNotificationDelivery fetchByU_P_C_N_D(long userId,
		String portletId, long classNameId, int notificationType,
		int deliveryType) {
		return fetchByU_P_C_N_D(userId, portletId, classNameId,
			notificationType, deliveryType, true);
	}

	/**
	 * Returns the user notification delivery where userId = &#63; and portletId = &#63; and classNameId = &#63; and notificationType = &#63; and deliveryType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param portletId the portlet ID
	 * @param classNameId the class name ID
	 * @param notificationType the notification type
	 * @param deliveryType the delivery type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching user notification delivery, or <code>null</code> if a matching user notification delivery could not be found
	 */
	@Override
	public UserNotificationDelivery fetchByU_P_C_N_D(long userId,
		String portletId, long classNameId, int notificationType,
		int deliveryType, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				userId, portletId, classNameId, notificationType, deliveryType
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_P_C_N_D,
					finderArgs, this);
		}

		if (result instanceof UserNotificationDelivery) {
			UserNotificationDelivery userNotificationDelivery = (UserNotificationDelivery)result;

			if ((userId != userNotificationDelivery.getUserId()) ||
					!Objects.equals(portletId,
						userNotificationDelivery.getPortletId()) ||
					(classNameId != userNotificationDelivery.getClassNameId()) ||
					(notificationType != userNotificationDelivery.getNotificationType()) ||
					(deliveryType != userNotificationDelivery.getDeliveryType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_USERNOTIFICATIONDELIVERY_WHERE);

			query.append(_FINDER_COLUMN_U_P_C_N_D_USERID_2);

			boolean bindPortletId = false;

			if (portletId == null) {
				query.append(_FINDER_COLUMN_U_P_C_N_D_PORTLETID_1);
			}
			else if (portletId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_P_C_N_D_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				query.append(_FINDER_COLUMN_U_P_C_N_D_PORTLETID_2);
			}

			query.append(_FINDER_COLUMN_U_P_C_N_D_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_P_C_N_D_NOTIFICATIONTYPE_2);

			query.append(_FINDER_COLUMN_U_P_C_N_D_DELIVERYTYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindPortletId) {
					qPos.add(portletId);
				}

				qPos.add(classNameId);

				qPos.add(notificationType);

				qPos.add(deliveryType);

				List<UserNotificationDelivery> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_P_C_N_D,
						finderArgs, list);
				}
				else {
					UserNotificationDelivery userNotificationDelivery = list.get(0);

					result = userNotificationDelivery;

					cacheResult(userNotificationDelivery);

					if ((userNotificationDelivery.getUserId() != userId) ||
							(userNotificationDelivery.getPortletId() == null) ||
							!userNotificationDelivery.getPortletId()
														 .equals(portletId) ||
							(userNotificationDelivery.getClassNameId() != classNameId) ||
							(userNotificationDelivery.getNotificationType() != notificationType) ||
							(userNotificationDelivery.getDeliveryType() != deliveryType)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_P_C_N_D,
							finderArgs, userNotificationDelivery);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_P_C_N_D,
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
			return (UserNotificationDelivery)result;
		}
	}

	/**
	 * Removes the user notification delivery where userId = &#63; and portletId = &#63; and classNameId = &#63; and notificationType = &#63; and deliveryType = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param portletId the portlet ID
	 * @param classNameId the class name ID
	 * @param notificationType the notification type
	 * @param deliveryType the delivery type
	 * @return the user notification delivery that was removed
	 */
	@Override
	public UserNotificationDelivery removeByU_P_C_N_D(long userId,
		String portletId, long classNameId, int notificationType,
		int deliveryType) throws NoSuchUserNotificationDeliveryException {
		UserNotificationDelivery userNotificationDelivery = findByU_P_C_N_D(userId,
				portletId, classNameId, notificationType, deliveryType);

		return remove(userNotificationDelivery);
	}

	/**
	 * Returns the number of user notification deliveries where userId = &#63; and portletId = &#63; and classNameId = &#63; and notificationType = &#63; and deliveryType = &#63;.
	 *
	 * @param userId the user ID
	 * @param portletId the portlet ID
	 * @param classNameId the class name ID
	 * @param notificationType the notification type
	 * @param deliveryType the delivery type
	 * @return the number of matching user notification deliveries
	 */
	@Override
	public int countByU_P_C_N_D(long userId, String portletId,
		long classNameId, int notificationType, int deliveryType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_P_C_N_D;

		Object[] finderArgs = new Object[] {
				userId, portletId, classNameId, notificationType, deliveryType
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_USERNOTIFICATIONDELIVERY_WHERE);

			query.append(_FINDER_COLUMN_U_P_C_N_D_USERID_2);

			boolean bindPortletId = false;

			if (portletId == null) {
				query.append(_FINDER_COLUMN_U_P_C_N_D_PORTLETID_1);
			}
			else if (portletId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_P_C_N_D_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				query.append(_FINDER_COLUMN_U_P_C_N_D_PORTLETID_2);
			}

			query.append(_FINDER_COLUMN_U_P_C_N_D_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_P_C_N_D_NOTIFICATIONTYPE_2);

			query.append(_FINDER_COLUMN_U_P_C_N_D_DELIVERYTYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindPortletId) {
					qPos.add(portletId);
				}

				qPos.add(classNameId);

				qPos.add(notificationType);

				qPos.add(deliveryType);

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

	private static final String _FINDER_COLUMN_U_P_C_N_D_USERID_2 = "userNotificationDelivery.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_P_C_N_D_PORTLETID_1 = "userNotificationDelivery.portletId IS NULL AND ";
	private static final String _FINDER_COLUMN_U_P_C_N_D_PORTLETID_2 = "userNotificationDelivery.portletId = ? AND ";
	private static final String _FINDER_COLUMN_U_P_C_N_D_PORTLETID_3 = "(userNotificationDelivery.portletId IS NULL OR userNotificationDelivery.portletId = '') AND ";
	private static final String _FINDER_COLUMN_U_P_C_N_D_CLASSNAMEID_2 = "userNotificationDelivery.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_U_P_C_N_D_NOTIFICATIONTYPE_2 = "userNotificationDelivery.notificationType = ? AND ";
	private static final String _FINDER_COLUMN_U_P_C_N_D_DELIVERYTYPE_2 = "userNotificationDelivery.deliveryType = ?";

	public UserNotificationDeliveryPersistenceImpl() {
		setModelClass(UserNotificationDelivery.class);
	}

	/**
	 * Caches the user notification delivery in the entity cache if it is enabled.
	 *
	 * @param userNotificationDelivery the user notification delivery
	 */
	@Override
	public void cacheResult(UserNotificationDelivery userNotificationDelivery) {
		entityCache.putResult(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationDeliveryImpl.class,
			userNotificationDelivery.getPrimaryKey(), userNotificationDelivery);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_P_C_N_D,
			new Object[] {
				userNotificationDelivery.getUserId(),
				userNotificationDelivery.getPortletId(),
				userNotificationDelivery.getClassNameId(),
				userNotificationDelivery.getNotificationType(),
				userNotificationDelivery.getDeliveryType()
			}, userNotificationDelivery);

		userNotificationDelivery.resetOriginalValues();
	}

	/**
	 * Caches the user notification deliveries in the entity cache if it is enabled.
	 *
	 * @param userNotificationDeliveries the user notification deliveries
	 */
	@Override
	public void cacheResult(
		List<UserNotificationDelivery> userNotificationDeliveries) {
		for (UserNotificationDelivery userNotificationDelivery : userNotificationDeliveries) {
			if (entityCache.getResult(
						UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
						UserNotificationDeliveryImpl.class,
						userNotificationDelivery.getPrimaryKey()) == null) {
				cacheResult(userNotificationDelivery);
			}
			else {
				userNotificationDelivery.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all user notification deliveries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserNotificationDeliveryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the user notification delivery.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserNotificationDelivery userNotificationDelivery) {
		entityCache.removeResult(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationDeliveryImpl.class,
			userNotificationDelivery.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((UserNotificationDeliveryModelImpl)userNotificationDelivery);
	}

	@Override
	public void clearCache(
		List<UserNotificationDelivery> userNotificationDeliveries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserNotificationDelivery userNotificationDelivery : userNotificationDeliveries) {
			entityCache.removeResult(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
				UserNotificationDeliveryImpl.class,
				userNotificationDelivery.getPrimaryKey());

			clearUniqueFindersCache((UserNotificationDeliveryModelImpl)userNotificationDelivery);
		}
	}

	protected void cacheUniqueFindersCache(
		UserNotificationDeliveryModelImpl userNotificationDeliveryModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					userNotificationDeliveryModelImpl.getUserId(),
					userNotificationDeliveryModelImpl.getPortletId(),
					userNotificationDeliveryModelImpl.getClassNameId(),
					userNotificationDeliveryModelImpl.getNotificationType(),
					userNotificationDeliveryModelImpl.getDeliveryType()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_U_P_C_N_D, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_U_P_C_N_D, args,
				userNotificationDeliveryModelImpl);
		}
		else {
			if ((userNotificationDeliveryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_P_C_N_D.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userNotificationDeliveryModelImpl.getUserId(),
						userNotificationDeliveryModelImpl.getPortletId(),
						userNotificationDeliveryModelImpl.getClassNameId(),
						userNotificationDeliveryModelImpl.getNotificationType(),
						userNotificationDeliveryModelImpl.getDeliveryType()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_U_P_C_N_D, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_U_P_C_N_D, args,
					userNotificationDeliveryModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		UserNotificationDeliveryModelImpl userNotificationDeliveryModelImpl) {
		Object[] args = new Object[] {
				userNotificationDeliveryModelImpl.getUserId(),
				userNotificationDeliveryModelImpl.getPortletId(),
				userNotificationDeliveryModelImpl.getClassNameId(),
				userNotificationDeliveryModelImpl.getNotificationType(),
				userNotificationDeliveryModelImpl.getDeliveryType()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_U_P_C_N_D, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_U_P_C_N_D, args);

		if ((userNotificationDeliveryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_P_C_N_D.getColumnBitmask()) != 0) {
			args = new Object[] {
					userNotificationDeliveryModelImpl.getOriginalUserId(),
					userNotificationDeliveryModelImpl.getOriginalPortletId(),
					userNotificationDeliveryModelImpl.getOriginalClassNameId(),
					userNotificationDeliveryModelImpl.getOriginalNotificationType(),
					userNotificationDeliveryModelImpl.getOriginalDeliveryType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_P_C_N_D, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_P_C_N_D, args);
		}
	}

	/**
	 * Creates a new user notification delivery with the primary key. Does not add the user notification delivery to the database.
	 *
	 * @param userNotificationDeliveryId the primary key for the new user notification delivery
	 * @return the new user notification delivery
	 */
	@Override
	public UserNotificationDelivery create(long userNotificationDeliveryId) {
		UserNotificationDelivery userNotificationDelivery = new UserNotificationDeliveryImpl();

		userNotificationDelivery.setNew(true);
		userNotificationDelivery.setPrimaryKey(userNotificationDeliveryId);

		userNotificationDelivery.setCompanyId(companyProvider.getCompanyId());

		return userNotificationDelivery;
	}

	/**
	 * Removes the user notification delivery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationDeliveryId the primary key of the user notification delivery
	 * @return the user notification delivery that was removed
	 * @throws NoSuchUserNotificationDeliveryException if a user notification delivery with the primary key could not be found
	 */
	@Override
	public UserNotificationDelivery remove(long userNotificationDeliveryId)
		throws NoSuchUserNotificationDeliveryException {
		return remove((Serializable)userNotificationDeliveryId);
	}

	/**
	 * Removes the user notification delivery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user notification delivery
	 * @return the user notification delivery that was removed
	 * @throws NoSuchUserNotificationDeliveryException if a user notification delivery with the primary key could not be found
	 */
	@Override
	public UserNotificationDelivery remove(Serializable primaryKey)
		throws NoSuchUserNotificationDeliveryException {
		Session session = null;

		try {
			session = openSession();

			UserNotificationDelivery userNotificationDelivery = (UserNotificationDelivery)session.get(UserNotificationDeliveryImpl.class,
					primaryKey);

			if (userNotificationDelivery == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserNotificationDeliveryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(userNotificationDelivery);
		}
		catch (NoSuchUserNotificationDeliveryException nsee) {
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
	protected UserNotificationDelivery removeImpl(
		UserNotificationDelivery userNotificationDelivery) {
		userNotificationDelivery = toUnwrappedModel(userNotificationDelivery);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userNotificationDelivery)) {
				userNotificationDelivery = (UserNotificationDelivery)session.get(UserNotificationDeliveryImpl.class,
						userNotificationDelivery.getPrimaryKeyObj());
			}

			if (userNotificationDelivery != null) {
				session.delete(userNotificationDelivery);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (userNotificationDelivery != null) {
			clearCache(userNotificationDelivery);
		}

		return userNotificationDelivery;
	}

	@Override
	public UserNotificationDelivery updateImpl(
		UserNotificationDelivery userNotificationDelivery) {
		userNotificationDelivery = toUnwrappedModel(userNotificationDelivery);

		boolean isNew = userNotificationDelivery.isNew();

		UserNotificationDeliveryModelImpl userNotificationDeliveryModelImpl = (UserNotificationDeliveryModelImpl)userNotificationDelivery;

		Session session = null;

		try {
			session = openSession();

			if (userNotificationDelivery.isNew()) {
				session.save(userNotificationDelivery);

				userNotificationDelivery.setNew(false);
			}
			else {
				userNotificationDelivery = (UserNotificationDelivery)session.merge(userNotificationDelivery);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !UserNotificationDeliveryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((userNotificationDeliveryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userNotificationDeliveryModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						userNotificationDeliveryModelImpl.getUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}
		}

		entityCache.putResult(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationDeliveryImpl.class,
			userNotificationDelivery.getPrimaryKey(), userNotificationDelivery,
			false);

		clearUniqueFindersCache(userNotificationDeliveryModelImpl);
		cacheUniqueFindersCache(userNotificationDeliveryModelImpl, isNew);

		userNotificationDelivery.resetOriginalValues();

		return userNotificationDelivery;
	}

	protected UserNotificationDelivery toUnwrappedModel(
		UserNotificationDelivery userNotificationDelivery) {
		if (userNotificationDelivery instanceof UserNotificationDeliveryImpl) {
			return userNotificationDelivery;
		}

		UserNotificationDeliveryImpl userNotificationDeliveryImpl = new UserNotificationDeliveryImpl();

		userNotificationDeliveryImpl.setNew(userNotificationDelivery.isNew());
		userNotificationDeliveryImpl.setPrimaryKey(userNotificationDelivery.getPrimaryKey());

		userNotificationDeliveryImpl.setMvccVersion(userNotificationDelivery.getMvccVersion());
		userNotificationDeliveryImpl.setUserNotificationDeliveryId(userNotificationDelivery.getUserNotificationDeliveryId());
		userNotificationDeliveryImpl.setCompanyId(userNotificationDelivery.getCompanyId());
		userNotificationDeliveryImpl.setUserId(userNotificationDelivery.getUserId());
		userNotificationDeliveryImpl.setPortletId(userNotificationDelivery.getPortletId());
		userNotificationDeliveryImpl.setClassNameId(userNotificationDelivery.getClassNameId());
		userNotificationDeliveryImpl.setNotificationType(userNotificationDelivery.getNotificationType());
		userNotificationDeliveryImpl.setDeliveryType(userNotificationDelivery.getDeliveryType());
		userNotificationDeliveryImpl.setDeliver(userNotificationDelivery.isDeliver());

		return userNotificationDeliveryImpl;
	}

	/**
	 * Returns the user notification delivery with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user notification delivery
	 * @return the user notification delivery
	 * @throws NoSuchUserNotificationDeliveryException if a user notification delivery with the primary key could not be found
	 */
	@Override
	public UserNotificationDelivery findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserNotificationDeliveryException {
		UserNotificationDelivery userNotificationDelivery = fetchByPrimaryKey(primaryKey);

		if (userNotificationDelivery == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserNotificationDeliveryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return userNotificationDelivery;
	}

	/**
	 * Returns the user notification delivery with the primary key or throws a {@link NoSuchUserNotificationDeliveryException} if it could not be found.
	 *
	 * @param userNotificationDeliveryId the primary key of the user notification delivery
	 * @return the user notification delivery
	 * @throws NoSuchUserNotificationDeliveryException if a user notification delivery with the primary key could not be found
	 */
	@Override
	public UserNotificationDelivery findByPrimaryKey(
		long userNotificationDeliveryId)
		throws NoSuchUserNotificationDeliveryException {
		return findByPrimaryKey((Serializable)userNotificationDeliveryId);
	}

	/**
	 * Returns the user notification delivery with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user notification delivery
	 * @return the user notification delivery, or <code>null</code> if a user notification delivery with the primary key could not be found
	 */
	@Override
	public UserNotificationDelivery fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
				UserNotificationDeliveryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		UserNotificationDelivery userNotificationDelivery = (UserNotificationDelivery)serializable;

		if (userNotificationDelivery == null) {
			Session session = null;

			try {
				session = openSession();

				userNotificationDelivery = (UserNotificationDelivery)session.get(UserNotificationDeliveryImpl.class,
						primaryKey);

				if (userNotificationDelivery != null) {
					cacheResult(userNotificationDelivery);
				}
				else {
					entityCache.putResult(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
						UserNotificationDeliveryImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
					UserNotificationDeliveryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return userNotificationDelivery;
	}

	/**
	 * Returns the user notification delivery with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userNotificationDeliveryId the primary key of the user notification delivery
	 * @return the user notification delivery, or <code>null</code> if a user notification delivery with the primary key could not be found
	 */
	@Override
	public UserNotificationDelivery fetchByPrimaryKey(
		long userNotificationDeliveryId) {
		return fetchByPrimaryKey((Serializable)userNotificationDeliveryId);
	}

	@Override
	public Map<Serializable, UserNotificationDelivery> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, UserNotificationDelivery> map = new HashMap<Serializable, UserNotificationDelivery>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			UserNotificationDelivery userNotificationDelivery = fetchByPrimaryKey(primaryKey);

			if (userNotificationDelivery != null) {
				map.put(primaryKey, userNotificationDelivery);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
					UserNotificationDeliveryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (UserNotificationDelivery)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_USERNOTIFICATIONDELIVERY_WHERE_PKS_IN);

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

			for (UserNotificationDelivery userNotificationDelivery : (List<UserNotificationDelivery>)q.list()) {
				map.put(userNotificationDelivery.getPrimaryKeyObj(),
					userNotificationDelivery);

				cacheResult(userNotificationDelivery);

				uncachedPrimaryKeys.remove(userNotificationDelivery.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(UserNotificationDeliveryModelImpl.ENTITY_CACHE_ENABLED,
					UserNotificationDeliveryImpl.class, primaryKey, nullModel);
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
	 * Returns all the user notification deliveries.
	 *
	 * @return the user notification deliveries
	 */
	@Override
	public List<UserNotificationDelivery> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification deliveries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationDeliveryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification deliveries
	 * @param end the upper bound of the range of user notification deliveries (not inclusive)
	 * @return the range of user notification deliveries
	 */
	@Override
	public List<UserNotificationDelivery> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification deliveries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationDeliveryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification deliveries
	 * @param end the upper bound of the range of user notification deliveries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user notification deliveries
	 */
	@Override
	public List<UserNotificationDelivery> findAll(int start, int end,
		OrderByComparator<UserNotificationDelivery> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user notification deliveries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationDeliveryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification deliveries
	 * @param end the upper bound of the range of user notification deliveries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of user notification deliveries
	 */
	@Override
	public List<UserNotificationDelivery> findAll(int start, int end,
		OrderByComparator<UserNotificationDelivery> orderByComparator,
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

		List<UserNotificationDelivery> list = null;

		if (retrieveFromCache) {
			list = (List<UserNotificationDelivery>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_USERNOTIFICATIONDELIVERY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERNOTIFICATIONDELIVERY;

				if (pagination) {
					sql = sql.concat(UserNotificationDeliveryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UserNotificationDelivery>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserNotificationDelivery>)QueryUtil.list(q,
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
	 * Removes all the user notification deliveries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserNotificationDelivery userNotificationDelivery : findAll()) {
			remove(userNotificationDelivery);
		}
	}

	/**
	 * Returns the number of user notification deliveries.
	 *
	 * @return the number of user notification deliveries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERNOTIFICATIONDELIVERY);

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
		return UserNotificationDeliveryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user notification delivery persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(UserNotificationDeliveryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_USERNOTIFICATIONDELIVERY = "SELECT userNotificationDelivery FROM UserNotificationDelivery userNotificationDelivery";
	private static final String _SQL_SELECT_USERNOTIFICATIONDELIVERY_WHERE_PKS_IN =
		"SELECT userNotificationDelivery FROM UserNotificationDelivery userNotificationDelivery WHERE userNotificationDeliveryId IN (";
	private static final String _SQL_SELECT_USERNOTIFICATIONDELIVERY_WHERE = "SELECT userNotificationDelivery FROM UserNotificationDelivery userNotificationDelivery WHERE ";
	private static final String _SQL_COUNT_USERNOTIFICATIONDELIVERY = "SELECT COUNT(userNotificationDelivery) FROM UserNotificationDelivery userNotificationDelivery";
	private static final String _SQL_COUNT_USERNOTIFICATIONDELIVERY_WHERE = "SELECT COUNT(userNotificationDelivery) FROM UserNotificationDelivery userNotificationDelivery WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userNotificationDelivery.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserNotificationDelivery exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserNotificationDelivery exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(UserNotificationDeliveryPersistenceImpl.class);
}