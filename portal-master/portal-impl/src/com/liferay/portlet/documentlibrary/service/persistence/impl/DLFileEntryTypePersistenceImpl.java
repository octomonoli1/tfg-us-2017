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

package com.liferay.portlet.documentlibrary.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.document.library.kernel.exception.NoSuchFileEntryTypeException;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryTypePersistence;
import com.liferay.document.library.kernel.service.persistence.DLFolderPersistence;

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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import com.liferay.portlet.documentlibrary.model.impl.DLFileEntryTypeImpl;
import com.liferay.portlet.documentlibrary.model.impl.DLFileEntryTypeModelImpl;

import java.io.Serializable;

import java.util.Arrays;
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
 * The persistence implementation for the document library file entry type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryTypePersistence
 * @see com.liferay.document.library.kernel.service.persistence.DLFileEntryTypeUtil
 * @generated
 */
@ProviderType
public class DLFileEntryTypePersistenceImpl extends BasePersistenceImpl<DLFileEntryType>
	implements DLFileEntryTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DLFileEntryTypeUtil} to access the document library file entry type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DLFileEntryTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED,
			DLFileEntryTypeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED,
			DLFileEntryTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED,
			DLFileEntryTypeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED,
			DLFileEntryTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DLFileEntryTypeModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the document library file entry types where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file entry types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @return the range of matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file entry types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByUuid(String uuid, int start, int end,
		OrderByComparator<DLFileEntryType> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file entry types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByUuid(String uuid, int start, int end,
		OrderByComparator<DLFileEntryType> orderByComparator,
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

		List<DLFileEntryType> list = null;

		if (retrieveFromCache) {
			list = (List<DLFileEntryType>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileEntryType dlFileEntryType : list) {
					if (!Objects.equals(uuid, dlFileEntryType.getUuid())) {
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

			query.append(_SQL_SELECT_DLFILEENTRYTYPE_WHERE);

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
				query.append(DLFileEntryTypeModelImpl.ORDER_BY_JPQL);
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
					list = (List<DLFileEntryType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DLFileEntryType>)QueryUtil.list(q,
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
	 * Returns the first document library file entry type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file entry type
	 * @throws NoSuchFileEntryTypeException if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType findByUuid_First(String uuid,
		OrderByComparator<DLFileEntryType> orderByComparator)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = fetchByUuid_First(uuid,
				orderByComparator);

		if (dlFileEntryType != null) {
			return dlFileEntryType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFileEntryTypeException(msg.toString());
	}

	/**
	 * Returns the first document library file entry type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file entry type, or <code>null</code> if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType fetchByUuid_First(String uuid,
		OrderByComparator<DLFileEntryType> orderByComparator) {
		List<DLFileEntryType> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file entry type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file entry type
	 * @throws NoSuchFileEntryTypeException if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType findByUuid_Last(String uuid,
		OrderByComparator<DLFileEntryType> orderByComparator)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = fetchByUuid_Last(uuid,
				orderByComparator);

		if (dlFileEntryType != null) {
			return dlFileEntryType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFileEntryTypeException(msg.toString());
	}

	/**
	 * Returns the last document library file entry type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file entry type, or <code>null</code> if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType fetchByUuid_Last(String uuid,
		OrderByComparator<DLFileEntryType> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DLFileEntryType> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file entry types before and after the current document library file entry type in the ordered set where uuid = &#63;.
	 *
	 * @param fileEntryTypeId the primary key of the current document library file entry type
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file entry type
	 * @throws NoSuchFileEntryTypeException if a document library file entry type with the primary key could not be found
	 */
	@Override
	public DLFileEntryType[] findByUuid_PrevAndNext(long fileEntryTypeId,
		String uuid, OrderByComparator<DLFileEntryType> orderByComparator)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = findByPrimaryKey(fileEntryTypeId);

		Session session = null;

		try {
			session = openSession();

			DLFileEntryType[] array = new DLFileEntryTypeImpl[3];

			array[0] = getByUuid_PrevAndNext(session, dlFileEntryType, uuid,
					orderByComparator, true);

			array[1] = dlFileEntryType;

			array[2] = getByUuid_PrevAndNext(session, dlFileEntryType, uuid,
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

	protected DLFileEntryType getByUuid_PrevAndNext(Session session,
		DLFileEntryType dlFileEntryType, String uuid,
		OrderByComparator<DLFileEntryType> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DLFILEENTRYTYPE_WHERE);

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
			query.append(DLFileEntryTypeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dlFileEntryType);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DLFileEntryType> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the document library file entry types where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DLFileEntryType dlFileEntryType : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dlFileEntryType);
		}
	}

	/**
	 * Returns the number of document library file entry types where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching document library file entry types
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DLFILEENTRYTYPE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "dlFileEntryType.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "dlFileEntryType.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(dlFileEntryType.uuid IS NULL OR dlFileEntryType.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED,
			DLFileEntryTypeImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DLFileEntryTypeModelImpl.UUID_COLUMN_BITMASK |
			DLFileEntryTypeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the document library file entry type where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchFileEntryTypeException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching document library file entry type
	 * @throws NoSuchFileEntryTypeException if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType findByUUID_G(String uuid, long groupId)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = fetchByUUID_G(uuid, groupId);

		if (dlFileEntryType == null) {
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

			throw new NoSuchFileEntryTypeException(msg.toString());
		}

		return dlFileEntryType;
	}

	/**
	 * Returns the document library file entry type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching document library file entry type, or <code>null</code> if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the document library file entry type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching document library file entry type, or <code>null</code> if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DLFileEntryType) {
			DLFileEntryType dlFileEntryType = (DLFileEntryType)result;

			if (!Objects.equals(uuid, dlFileEntryType.getUuid()) ||
					(groupId != dlFileEntryType.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DLFILEENTRYTYPE_WHERE);

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

				List<DLFileEntryType> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DLFileEntryType dlFileEntryType = list.get(0);

					result = dlFileEntryType;

					cacheResult(dlFileEntryType);

					if ((dlFileEntryType.getUuid() == null) ||
							!dlFileEntryType.getUuid().equals(uuid) ||
							(dlFileEntryType.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, dlFileEntryType);
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
			return (DLFileEntryType)result;
		}
	}

	/**
	 * Removes the document library file entry type where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the document library file entry type that was removed
	 */
	@Override
	public DLFileEntryType removeByUUID_G(String uuid, long groupId)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = findByUUID_G(uuid, groupId);

		return remove(dlFileEntryType);
	}

	/**
	 * Returns the number of document library file entry types where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching document library file entry types
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DLFILEENTRYTYPE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "dlFileEntryType.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "dlFileEntryType.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(dlFileEntryType.uuid IS NULL OR dlFileEntryType.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "dlFileEntryType.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED,
			DLFileEntryTypeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED,
			DLFileEntryTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DLFileEntryTypeModelImpl.UUID_COLUMN_BITMASK |
			DLFileEntryTypeModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the document library file entry types where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file entry types where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @return the range of matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file entry types where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DLFileEntryType> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file entry types where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DLFileEntryType> orderByComparator,
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

		List<DLFileEntryType> list = null;

		if (retrieveFromCache) {
			list = (List<DLFileEntryType>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileEntryType dlFileEntryType : list) {
					if (!Objects.equals(uuid, dlFileEntryType.getUuid()) ||
							(companyId != dlFileEntryType.getCompanyId())) {
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

			query.append(_SQL_SELECT_DLFILEENTRYTYPE_WHERE);

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
				query.append(DLFileEntryTypeModelImpl.ORDER_BY_JPQL);
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
					list = (List<DLFileEntryType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DLFileEntryType>)QueryUtil.list(q,
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
	 * Returns the first document library file entry type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file entry type
	 * @throws NoSuchFileEntryTypeException if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DLFileEntryType> orderByComparator)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (dlFileEntryType != null) {
			return dlFileEntryType;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFileEntryTypeException(msg.toString());
	}

	/**
	 * Returns the first document library file entry type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file entry type, or <code>null</code> if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DLFileEntryType> orderByComparator) {
		List<DLFileEntryType> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file entry type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file entry type
	 * @throws NoSuchFileEntryTypeException if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DLFileEntryType> orderByComparator)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (dlFileEntryType != null) {
			return dlFileEntryType;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFileEntryTypeException(msg.toString());
	}

	/**
	 * Returns the last document library file entry type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file entry type, or <code>null</code> if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DLFileEntryType> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DLFileEntryType> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file entry types before and after the current document library file entry type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param fileEntryTypeId the primary key of the current document library file entry type
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file entry type
	 * @throws NoSuchFileEntryTypeException if a document library file entry type with the primary key could not be found
	 */
	@Override
	public DLFileEntryType[] findByUuid_C_PrevAndNext(long fileEntryTypeId,
		String uuid, long companyId,
		OrderByComparator<DLFileEntryType> orderByComparator)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = findByPrimaryKey(fileEntryTypeId);

		Session session = null;

		try {
			session = openSession();

			DLFileEntryType[] array = new DLFileEntryTypeImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, dlFileEntryType, uuid,
					companyId, orderByComparator, true);

			array[1] = dlFileEntryType;

			array[2] = getByUuid_C_PrevAndNext(session, dlFileEntryType, uuid,
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

	protected DLFileEntryType getByUuid_C_PrevAndNext(Session session,
		DLFileEntryType dlFileEntryType, String uuid, long companyId,
		OrderByComparator<DLFileEntryType> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DLFILEENTRYTYPE_WHERE);

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
			query.append(DLFileEntryTypeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(dlFileEntryType);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DLFileEntryType> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the document library file entry types where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DLFileEntryType dlFileEntryType : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dlFileEntryType);
		}
	}

	/**
	 * Returns the number of document library file entry types where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching document library file entry types
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DLFILEENTRYTYPE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "dlFileEntryType.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "dlFileEntryType.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(dlFileEntryType.uuid IS NULL OR dlFileEntryType.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "dlFileEntryType.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED,
			DLFileEntryTypeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED,
			DLFileEntryTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			DLFileEntryTypeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPID = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the document library file entry types where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file entry types where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @return the range of matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file entry types where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByGroupId(long groupId, int start,
		int end, OrderByComparator<DLFileEntryType> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file entry types where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByGroupId(long groupId, int start,
		int end, OrderByComparator<DLFileEntryType> orderByComparator,
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

		List<DLFileEntryType> list = null;

		if (retrieveFromCache) {
			list = (List<DLFileEntryType>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileEntryType dlFileEntryType : list) {
					if ((groupId != dlFileEntryType.getGroupId())) {
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

			query.append(_SQL_SELECT_DLFILEENTRYTYPE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DLFileEntryTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<DLFileEntryType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DLFileEntryType>)QueryUtil.list(q,
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
	 * Returns the first document library file entry type in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file entry type
	 * @throws NoSuchFileEntryTypeException if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType findByGroupId_First(long groupId,
		OrderByComparator<DLFileEntryType> orderByComparator)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = fetchByGroupId_First(groupId,
				orderByComparator);

		if (dlFileEntryType != null) {
			return dlFileEntryType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFileEntryTypeException(msg.toString());
	}

	/**
	 * Returns the first document library file entry type in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file entry type, or <code>null</code> if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType fetchByGroupId_First(long groupId,
		OrderByComparator<DLFileEntryType> orderByComparator) {
		List<DLFileEntryType> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last document library file entry type in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file entry type
	 * @throws NoSuchFileEntryTypeException if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType findByGroupId_Last(long groupId,
		OrderByComparator<DLFileEntryType> orderByComparator)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (dlFileEntryType != null) {
			return dlFileEntryType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFileEntryTypeException(msg.toString());
	}

	/**
	 * Returns the last document library file entry type in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file entry type, or <code>null</code> if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType fetchByGroupId_Last(long groupId,
		OrderByComparator<DLFileEntryType> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<DLFileEntryType> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the document library file entry types before and after the current document library file entry type in the ordered set where groupId = &#63;.
	 *
	 * @param fileEntryTypeId the primary key of the current document library file entry type
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file entry type
	 * @throws NoSuchFileEntryTypeException if a document library file entry type with the primary key could not be found
	 */
	@Override
	public DLFileEntryType[] findByGroupId_PrevAndNext(long fileEntryTypeId,
		long groupId, OrderByComparator<DLFileEntryType> orderByComparator)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = findByPrimaryKey(fileEntryTypeId);

		Session session = null;

		try {
			session = openSession();

			DLFileEntryType[] array = new DLFileEntryTypeImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, dlFileEntryType,
					groupId, orderByComparator, true);

			array[1] = dlFileEntryType;

			array[2] = getByGroupId_PrevAndNext(session, dlFileEntryType,
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

	protected DLFileEntryType getByGroupId_PrevAndNext(Session session,
		DLFileEntryType dlFileEntryType, long groupId,
		OrderByComparator<DLFileEntryType> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DLFILEENTRYTYPE_WHERE);

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
			query.append(DLFileEntryTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dlFileEntryType);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DLFileEntryType> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the document library file entry types that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching document library file entry types that the user has permission to view
	 */
	@Override
	public List<DLFileEntryType> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file entry types that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @return the range of matching document library file entry types that the user has permission to view
	 */
	@Override
	public List<DLFileEntryType> filterFindByGroupId(long groupId, int start,
		int end) {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file entry types that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file entry types that the user has permission to view
	 */
	@Override
	public List<DLFileEntryType> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<DLFileEntryType> orderByComparator) {
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
			query.append(_FILTER_SQL_SELECT_DLFILEENTRYTYPE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_DLFILEENTRYTYPE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_DLFILEENTRYTYPE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(DLFileEntryTypeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(DLFileEntryTypeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				DLFileEntryType.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, DLFileEntryTypeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, DLFileEntryTypeImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<DLFileEntryType>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the document library file entry types before and after the current document library file entry type in the ordered set of document library file entry types that the user has permission to view where groupId = &#63;.
	 *
	 * @param fileEntryTypeId the primary key of the current document library file entry type
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file entry type
	 * @throws NoSuchFileEntryTypeException if a document library file entry type with the primary key could not be found
	 */
	@Override
	public DLFileEntryType[] filterFindByGroupId_PrevAndNext(
		long fileEntryTypeId, long groupId,
		OrderByComparator<DLFileEntryType> orderByComparator)
		throws NoSuchFileEntryTypeException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(fileEntryTypeId, groupId,
				orderByComparator);
		}

		DLFileEntryType dlFileEntryType = findByPrimaryKey(fileEntryTypeId);

		Session session = null;

		try {
			session = openSession();

			DLFileEntryType[] array = new DLFileEntryTypeImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, dlFileEntryType,
					groupId, orderByComparator, true);

			array[1] = dlFileEntryType;

			array[2] = filterGetByGroupId_PrevAndNext(session, dlFileEntryType,
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

	protected DLFileEntryType filterGetByGroupId_PrevAndNext(Session session,
		DLFileEntryType dlFileEntryType, long groupId,
		OrderByComparator<DLFileEntryType> orderByComparator, boolean previous) {
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
			query.append(_FILTER_SQL_SELECT_DLFILEENTRYTYPE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_DLFILEENTRYTYPE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_DLFILEENTRYTYPE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(DLFileEntryTypeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(DLFileEntryTypeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				DLFileEntryType.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, DLFileEntryTypeImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, DLFileEntryTypeImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dlFileEntryType);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DLFileEntryType> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the document library file entry types that the user has permission to view where groupId = any &#63;.
	 *
	 * @param groupIds the group IDs
	 * @return the matching document library file entry types that the user has permission to view
	 */
	@Override
	public List<DLFileEntryType> filterFindByGroupId(long[] groupIds) {
		return filterFindByGroupId(groupIds, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file entry types that the user has permission to view where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @return the range of matching document library file entry types that the user has permission to view
	 */
	@Override
	public List<DLFileEntryType> filterFindByGroupId(long[] groupIds,
		int start, int end) {
		return filterFindByGroupId(groupIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file entry types that the user has permission to view where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file entry types that the user has permission to view
	 */
	@Override
	public List<DLFileEntryType> filterFindByGroupId(long[] groupIds,
		int start, int end, OrderByComparator<DLFileEntryType> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupIds)) {
			return findByGroupId(groupIds, start, end, orderByComparator);
		}

		if (groupIds == null) {
			groupIds = new long[0];
		}
		else if (groupIds.length > 1) {
			groupIds = ArrayUtil.unique(groupIds);

			Arrays.sort(groupIds);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_DLFILEENTRYTYPE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_DLFILEENTRYTYPE_NO_INLINE_DISTINCT_WHERE_1);
		}

		if (groupIds.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_7);

			query.append(StringUtil.merge(groupIds));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_DLFILEENTRYTYPE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(DLFileEntryTypeModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(DLFileEntryTypeModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				DLFileEntryType.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupIds);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, DLFileEntryTypeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, DLFileEntryTypeImpl.class);
			}

			return (List<DLFileEntryType>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the document library file entry types where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @return the matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByGroupId(long[] groupIds) {
		return findByGroupId(groupIds, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the document library file entry types where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @return the range of matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByGroupId(long[] groupIds, int start,
		int end) {
		return findByGroupId(groupIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file entry types where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByGroupId(long[] groupIds, int start,
		int end, OrderByComparator<DLFileEntryType> orderByComparator) {
		return findByGroupId(groupIds, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file entry types where groupId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findByGroupId(long[] groupIds, int start,
		int end, OrderByComparator<DLFileEntryType> orderByComparator,
		boolean retrieveFromCache) {
		if (groupIds == null) {
			groupIds = new long[0];
		}
		else if (groupIds.length > 1) {
			groupIds = ArrayUtil.unique(groupIds);

			Arrays.sort(groupIds);
		}

		if (groupIds.length == 1) {
			return findByGroupId(groupIds[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(groupIds) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(groupIds),
					
					start, end, orderByComparator
				};
		}

		List<DLFileEntryType> list = null;

		if (retrieveFromCache) {
			list = (List<DLFileEntryType>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileEntryType dlFileEntryType : list) {
					if (!ArrayUtil.contains(groupIds,
								dlFileEntryType.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_DLFILEENTRYTYPE_WHERE);

			if (groupIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_GROUPID_GROUPID_7);

				query.append(StringUtil.merge(groupIds));

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
				query.append(DLFileEntryTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DLFileEntryType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DLFileEntryType>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID,
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
	 * Removes all the document library file entry types where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (DLFileEntryType dlFileEntryType : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dlFileEntryType);
		}
	}

	/**
	 * Returns the number of document library file entry types where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching document library file entry types
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DLFILEENTRYTYPE_WHERE);

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
	 * Returns the number of document library file entry types where groupId = any &#63;.
	 *
	 * @param groupIds the group IDs
	 * @return the number of matching document library file entry types
	 */
	@Override
	public int countByGroupId(long[] groupIds) {
		if (groupIds == null) {
			groupIds = new long[0];
		}
		else if (groupIds.length > 1) {
			groupIds = ArrayUtil.unique(groupIds);

			Arrays.sort(groupIds);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(groupIds) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_DLFILEENTRYTYPE_WHERE);

			if (groupIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_GROUPID_GROUPID_7);

				query.append(StringUtil.merge(groupIds));

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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPID,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of document library file entry types that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching document library file entry types that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_DLFILEENTRYTYPE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				DLFileEntryType.class.getName(),
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

	/**
	 * Returns the number of document library file entry types that the user has permission to view where groupId = any &#63;.
	 *
	 * @param groupIds the group IDs
	 * @return the number of matching document library file entry types that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long[] groupIds) {
		if (!InlineSQLHelperUtil.isEnabled(groupIds)) {
			return countByGroupId(groupIds);
		}

		if (groupIds == null) {
			groupIds = new long[0];
		}
		else if (groupIds.length > 1) {
			groupIds = ArrayUtil.unique(groupIds);

			Arrays.sort(groupIds);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_DLFILEENTRYTYPE_WHERE);

		if (groupIds.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_7);

			query.append(StringUtil.merge(groupIds));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				DLFileEntryType.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupIds);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "dlFileEntryType.groupId = ?";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_7 = "dlFileEntryType.groupId IN (";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_F = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED,
			DLFileEntryTypeImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByG_F",
			new String[] { Long.class.getName(), String.class.getName() },
			DLFileEntryTypeModelImpl.GROUPID_COLUMN_BITMASK |
			DLFileEntryTypeModelImpl.FILEENTRYTYPEKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_F = new FinderPath(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_F",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the document library file entry type where groupId = &#63; and fileEntryTypeKey = &#63; or throws a {@link NoSuchFileEntryTypeException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param fileEntryTypeKey the file entry type key
	 * @return the matching document library file entry type
	 * @throws NoSuchFileEntryTypeException if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType findByG_F(long groupId, String fileEntryTypeKey)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = fetchByG_F(groupId, fileEntryTypeKey);

		if (dlFileEntryType == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", fileEntryTypeKey=");
			msg.append(fileEntryTypeKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchFileEntryTypeException(msg.toString());
		}

		return dlFileEntryType;
	}

	/**
	 * Returns the document library file entry type where groupId = &#63; and fileEntryTypeKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param fileEntryTypeKey the file entry type key
	 * @return the matching document library file entry type, or <code>null</code> if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType fetchByG_F(long groupId, String fileEntryTypeKey) {
		return fetchByG_F(groupId, fileEntryTypeKey, true);
	}

	/**
	 * Returns the document library file entry type where groupId = &#63; and fileEntryTypeKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param fileEntryTypeKey the file entry type key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching document library file entry type, or <code>null</code> if a matching document library file entry type could not be found
	 */
	@Override
	public DLFileEntryType fetchByG_F(long groupId, String fileEntryTypeKey,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, fileEntryTypeKey };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_F,
					finderArgs, this);
		}

		if (result instanceof DLFileEntryType) {
			DLFileEntryType dlFileEntryType = (DLFileEntryType)result;

			if ((groupId != dlFileEntryType.getGroupId()) ||
					!Objects.equals(fileEntryTypeKey,
						dlFileEntryType.getFileEntryTypeKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DLFILEENTRYTYPE_WHERE);

			query.append(_FINDER_COLUMN_G_F_GROUPID_2);

			boolean bindFileEntryTypeKey = false;

			if (fileEntryTypeKey == null) {
				query.append(_FINDER_COLUMN_G_F_FILEENTRYTYPEKEY_1);
			}
			else if (fileEntryTypeKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_F_FILEENTRYTYPEKEY_3);
			}
			else {
				bindFileEntryTypeKey = true;

				query.append(_FINDER_COLUMN_G_F_FILEENTRYTYPEKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindFileEntryTypeKey) {
					qPos.add(fileEntryTypeKey);
				}

				List<DLFileEntryType> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_F, finderArgs,
						list);
				}
				else {
					DLFileEntryType dlFileEntryType = list.get(0);

					result = dlFileEntryType;

					cacheResult(dlFileEntryType);

					if ((dlFileEntryType.getGroupId() != groupId) ||
							(dlFileEntryType.getFileEntryTypeKey() == null) ||
							!dlFileEntryType.getFileEntryTypeKey()
												.equals(fileEntryTypeKey)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_G_F,
							finderArgs, dlFileEntryType);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_F, finderArgs);

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
			return (DLFileEntryType)result;
		}
	}

	/**
	 * Removes the document library file entry type where groupId = &#63; and fileEntryTypeKey = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fileEntryTypeKey the file entry type key
	 * @return the document library file entry type that was removed
	 */
	@Override
	public DLFileEntryType removeByG_F(long groupId, String fileEntryTypeKey)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = findByG_F(groupId, fileEntryTypeKey);

		return remove(dlFileEntryType);
	}

	/**
	 * Returns the number of document library file entry types where groupId = &#63; and fileEntryTypeKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fileEntryTypeKey the file entry type key
	 * @return the number of matching document library file entry types
	 */
	@Override
	public int countByG_F(long groupId, String fileEntryTypeKey) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_F;

		Object[] finderArgs = new Object[] { groupId, fileEntryTypeKey };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DLFILEENTRYTYPE_WHERE);

			query.append(_FINDER_COLUMN_G_F_GROUPID_2);

			boolean bindFileEntryTypeKey = false;

			if (fileEntryTypeKey == null) {
				query.append(_FINDER_COLUMN_G_F_FILEENTRYTYPEKEY_1);
			}
			else if (fileEntryTypeKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_F_FILEENTRYTYPEKEY_3);
			}
			else {
				bindFileEntryTypeKey = true;

				query.append(_FINDER_COLUMN_G_F_FILEENTRYTYPEKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindFileEntryTypeKey) {
					qPos.add(fileEntryTypeKey);
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

	private static final String _FINDER_COLUMN_G_F_GROUPID_2 = "dlFileEntryType.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_F_FILEENTRYTYPEKEY_1 = "dlFileEntryType.fileEntryTypeKey IS NULL";
	private static final String _FINDER_COLUMN_G_F_FILEENTRYTYPEKEY_2 = "dlFileEntryType.fileEntryTypeKey = ?";
	private static final String _FINDER_COLUMN_G_F_FILEENTRYTYPEKEY_3 = "(dlFileEntryType.fileEntryTypeKey IS NULL OR dlFileEntryType.fileEntryTypeKey = '')";

	public DLFileEntryTypePersistenceImpl() {
		setModelClass(DLFileEntryType.class);
	}

	/**
	 * Caches the document library file entry type in the entity cache if it is enabled.
	 *
	 * @param dlFileEntryType the document library file entry type
	 */
	@Override
	public void cacheResult(DLFileEntryType dlFileEntryType) {
		entityCache.putResult(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeImpl.class, dlFileEntryType.getPrimaryKey(),
			dlFileEntryType);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { dlFileEntryType.getUuid(), dlFileEntryType.getGroupId() },
			dlFileEntryType);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_F,
			new Object[] {
				dlFileEntryType.getGroupId(),
				dlFileEntryType.getFileEntryTypeKey()
			}, dlFileEntryType);

		dlFileEntryType.resetOriginalValues();
	}

	/**
	 * Caches the document library file entry types in the entity cache if it is enabled.
	 *
	 * @param dlFileEntryTypes the document library file entry types
	 */
	@Override
	public void cacheResult(List<DLFileEntryType> dlFileEntryTypes) {
		for (DLFileEntryType dlFileEntryType : dlFileEntryTypes) {
			if (entityCache.getResult(
						DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
						DLFileEntryTypeImpl.class,
						dlFileEntryType.getPrimaryKey()) == null) {
				cacheResult(dlFileEntryType);
			}
			else {
				dlFileEntryType.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all document library file entry types.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DLFileEntryTypeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the document library file entry type.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DLFileEntryType dlFileEntryType) {
		entityCache.removeResult(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeImpl.class, dlFileEntryType.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DLFileEntryTypeModelImpl)dlFileEntryType);
	}

	@Override
	public void clearCache(List<DLFileEntryType> dlFileEntryTypes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DLFileEntryType dlFileEntryType : dlFileEntryTypes) {
			entityCache.removeResult(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
				DLFileEntryTypeImpl.class, dlFileEntryType.getPrimaryKey());

			clearUniqueFindersCache((DLFileEntryTypeModelImpl)dlFileEntryType);
		}
	}

	protected void cacheUniqueFindersCache(
		DLFileEntryTypeModelImpl dlFileEntryTypeModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					dlFileEntryTypeModelImpl.getUuid(),
					dlFileEntryTypeModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				dlFileEntryTypeModelImpl);

			args = new Object[] {
					dlFileEntryTypeModelImpl.getGroupId(),
					dlFileEntryTypeModelImpl.getFileEntryTypeKey()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_G_F, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_G_F, args,
				dlFileEntryTypeModelImpl);
		}
		else {
			if ((dlFileEntryTypeModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dlFileEntryTypeModelImpl.getUuid(),
						dlFileEntryTypeModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					dlFileEntryTypeModelImpl);
			}

			if ((dlFileEntryTypeModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_G_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dlFileEntryTypeModelImpl.getGroupId(),
						dlFileEntryTypeModelImpl.getFileEntryTypeKey()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_G_F, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_G_F, args,
					dlFileEntryTypeModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		DLFileEntryTypeModelImpl dlFileEntryTypeModelImpl) {
		Object[] args = new Object[] {
				dlFileEntryTypeModelImpl.getUuid(),
				dlFileEntryTypeModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((dlFileEntryTypeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					dlFileEntryTypeModelImpl.getOriginalUuid(),
					dlFileEntryTypeModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] {
				dlFileEntryTypeModelImpl.getGroupId(),
				dlFileEntryTypeModelImpl.getFileEntryTypeKey()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_G_F, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_G_F, args);

		if ((dlFileEntryTypeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_F.getColumnBitmask()) != 0) {
			args = new Object[] {
					dlFileEntryTypeModelImpl.getOriginalGroupId(),
					dlFileEntryTypeModelImpl.getOriginalFileEntryTypeKey()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_F, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_F, args);
		}
	}

	/**
	 * Creates a new document library file entry type with the primary key. Does not add the document library file entry type to the database.
	 *
	 * @param fileEntryTypeId the primary key for the new document library file entry type
	 * @return the new document library file entry type
	 */
	@Override
	public DLFileEntryType create(long fileEntryTypeId) {
		DLFileEntryType dlFileEntryType = new DLFileEntryTypeImpl();

		dlFileEntryType.setNew(true);
		dlFileEntryType.setPrimaryKey(fileEntryTypeId);

		String uuid = PortalUUIDUtil.generate();

		dlFileEntryType.setUuid(uuid);

		dlFileEntryType.setCompanyId(companyProvider.getCompanyId());

		return dlFileEntryType;
	}

	/**
	 * Removes the document library file entry type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileEntryTypeId the primary key of the document library file entry type
	 * @return the document library file entry type that was removed
	 * @throws NoSuchFileEntryTypeException if a document library file entry type with the primary key could not be found
	 */
	@Override
	public DLFileEntryType remove(long fileEntryTypeId)
		throws NoSuchFileEntryTypeException {
		return remove((Serializable)fileEntryTypeId);
	}

	/**
	 * Removes the document library file entry type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the document library file entry type
	 * @return the document library file entry type that was removed
	 * @throws NoSuchFileEntryTypeException if a document library file entry type with the primary key could not be found
	 */
	@Override
	public DLFileEntryType remove(Serializable primaryKey)
		throws NoSuchFileEntryTypeException {
		Session session = null;

		try {
			session = openSession();

			DLFileEntryType dlFileEntryType = (DLFileEntryType)session.get(DLFileEntryTypeImpl.class,
					primaryKey);

			if (dlFileEntryType == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFileEntryTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dlFileEntryType);
		}
		catch (NoSuchFileEntryTypeException nsee) {
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
	protected DLFileEntryType removeImpl(DLFileEntryType dlFileEntryType) {
		dlFileEntryType = toUnwrappedModel(dlFileEntryType);

		dlFileEntryTypeToDLFolderTableMapper.deleteLeftPrimaryKeyTableMappings(dlFileEntryType.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dlFileEntryType)) {
				dlFileEntryType = (DLFileEntryType)session.get(DLFileEntryTypeImpl.class,
						dlFileEntryType.getPrimaryKeyObj());
			}

			if (dlFileEntryType != null) {
				session.delete(dlFileEntryType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dlFileEntryType != null) {
			clearCache(dlFileEntryType);
		}

		return dlFileEntryType;
	}

	@Override
	public DLFileEntryType updateImpl(DLFileEntryType dlFileEntryType) {
		dlFileEntryType = toUnwrappedModel(dlFileEntryType);

		boolean isNew = dlFileEntryType.isNew();

		DLFileEntryTypeModelImpl dlFileEntryTypeModelImpl = (DLFileEntryTypeModelImpl)dlFileEntryType;

		if (Validator.isNull(dlFileEntryType.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dlFileEntryType.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (dlFileEntryType.getCreateDate() == null)) {
			if (serviceContext == null) {
				dlFileEntryType.setCreateDate(now);
			}
			else {
				dlFileEntryType.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!dlFileEntryTypeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dlFileEntryType.setModifiedDate(now);
			}
			else {
				dlFileEntryType.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (dlFileEntryType.isNew()) {
				session.save(dlFileEntryType);

				dlFileEntryType.setNew(false);
			}
			else {
				dlFileEntryType = (DLFileEntryType)session.merge(dlFileEntryType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DLFileEntryTypeModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((dlFileEntryTypeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dlFileEntryTypeModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { dlFileEntryTypeModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((dlFileEntryTypeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dlFileEntryTypeModelImpl.getOriginalUuid(),
						dlFileEntryTypeModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						dlFileEntryTypeModelImpl.getUuid(),
						dlFileEntryTypeModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((dlFileEntryTypeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dlFileEntryTypeModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { dlFileEntryTypeModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
			DLFileEntryTypeImpl.class, dlFileEntryType.getPrimaryKey(),
			dlFileEntryType, false);

		clearUniqueFindersCache(dlFileEntryTypeModelImpl);
		cacheUniqueFindersCache(dlFileEntryTypeModelImpl, isNew);

		dlFileEntryType.resetOriginalValues();

		return dlFileEntryType;
	}

	protected DLFileEntryType toUnwrappedModel(DLFileEntryType dlFileEntryType) {
		if (dlFileEntryType instanceof DLFileEntryTypeImpl) {
			return dlFileEntryType;
		}

		DLFileEntryTypeImpl dlFileEntryTypeImpl = new DLFileEntryTypeImpl();

		dlFileEntryTypeImpl.setNew(dlFileEntryType.isNew());
		dlFileEntryTypeImpl.setPrimaryKey(dlFileEntryType.getPrimaryKey());

		dlFileEntryTypeImpl.setUuid(dlFileEntryType.getUuid());
		dlFileEntryTypeImpl.setFileEntryTypeId(dlFileEntryType.getFileEntryTypeId());
		dlFileEntryTypeImpl.setGroupId(dlFileEntryType.getGroupId());
		dlFileEntryTypeImpl.setCompanyId(dlFileEntryType.getCompanyId());
		dlFileEntryTypeImpl.setUserId(dlFileEntryType.getUserId());
		dlFileEntryTypeImpl.setUserName(dlFileEntryType.getUserName());
		dlFileEntryTypeImpl.setCreateDate(dlFileEntryType.getCreateDate());
		dlFileEntryTypeImpl.setModifiedDate(dlFileEntryType.getModifiedDate());
		dlFileEntryTypeImpl.setFileEntryTypeKey(dlFileEntryType.getFileEntryTypeKey());
		dlFileEntryTypeImpl.setName(dlFileEntryType.getName());
		dlFileEntryTypeImpl.setDescription(dlFileEntryType.getDescription());
		dlFileEntryTypeImpl.setLastPublishDate(dlFileEntryType.getLastPublishDate());

		return dlFileEntryTypeImpl;
	}

	/**
	 * Returns the document library file entry type with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the document library file entry type
	 * @return the document library file entry type
	 * @throws NoSuchFileEntryTypeException if a document library file entry type with the primary key could not be found
	 */
	@Override
	public DLFileEntryType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFileEntryTypeException {
		DLFileEntryType dlFileEntryType = fetchByPrimaryKey(primaryKey);

		if (dlFileEntryType == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFileEntryTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dlFileEntryType;
	}

	/**
	 * Returns the document library file entry type with the primary key or throws a {@link NoSuchFileEntryTypeException} if it could not be found.
	 *
	 * @param fileEntryTypeId the primary key of the document library file entry type
	 * @return the document library file entry type
	 * @throws NoSuchFileEntryTypeException if a document library file entry type with the primary key could not be found
	 */
	@Override
	public DLFileEntryType findByPrimaryKey(long fileEntryTypeId)
		throws NoSuchFileEntryTypeException {
		return findByPrimaryKey((Serializable)fileEntryTypeId);
	}

	/**
	 * Returns the document library file entry type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the document library file entry type
	 * @return the document library file entry type, or <code>null</code> if a document library file entry type with the primary key could not be found
	 */
	@Override
	public DLFileEntryType fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
				DLFileEntryTypeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DLFileEntryType dlFileEntryType = (DLFileEntryType)serializable;

		if (dlFileEntryType == null) {
			Session session = null;

			try {
				session = openSession();

				dlFileEntryType = (DLFileEntryType)session.get(DLFileEntryTypeImpl.class,
						primaryKey);

				if (dlFileEntryType != null) {
					cacheResult(dlFileEntryType);
				}
				else {
					entityCache.putResult(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
						DLFileEntryTypeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
					DLFileEntryTypeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dlFileEntryType;
	}

	/**
	 * Returns the document library file entry type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileEntryTypeId the primary key of the document library file entry type
	 * @return the document library file entry type, or <code>null</code> if a document library file entry type with the primary key could not be found
	 */
	@Override
	public DLFileEntryType fetchByPrimaryKey(long fileEntryTypeId) {
		return fetchByPrimaryKey((Serializable)fileEntryTypeId);
	}

	@Override
	public Map<Serializable, DLFileEntryType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DLFileEntryType> map = new HashMap<Serializable, DLFileEntryType>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DLFileEntryType dlFileEntryType = fetchByPrimaryKey(primaryKey);

			if (dlFileEntryType != null) {
				map.put(primaryKey, dlFileEntryType);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
					DLFileEntryTypeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DLFileEntryType)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DLFILEENTRYTYPE_WHERE_PKS_IN);

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

			for (DLFileEntryType dlFileEntryType : (List<DLFileEntryType>)q.list()) {
				map.put(dlFileEntryType.getPrimaryKeyObj(), dlFileEntryType);

				cacheResult(dlFileEntryType);

				uncachedPrimaryKeys.remove(dlFileEntryType.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DLFileEntryTypeModelImpl.ENTITY_CACHE_ENABLED,
					DLFileEntryTypeImpl.class, primaryKey, nullModel);
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
	 * Returns all the document library file entry types.
	 *
	 * @return the document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file entry types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @return the range of document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file entry types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findAll(int start, int end,
		OrderByComparator<DLFileEntryType> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the document library file entry types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of document library file entry types
	 */
	@Override
	public List<DLFileEntryType> findAll(int start, int end,
		OrderByComparator<DLFileEntryType> orderByComparator,
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

		List<DLFileEntryType> list = null;

		if (retrieveFromCache) {
			list = (List<DLFileEntryType>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DLFILEENTRYTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DLFILEENTRYTYPE;

				if (pagination) {
					sql = sql.concat(DLFileEntryTypeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DLFileEntryType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DLFileEntryType>)QueryUtil.list(q,
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
	 * Removes all the document library file entry types from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DLFileEntryType dlFileEntryType : findAll()) {
			remove(dlFileEntryType);
		}
	}

	/**
	 * Returns the number of document library file entry types.
	 *
	 * @return the number of document library file entry types
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DLFILEENTRYTYPE);

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
	 * Returns the primaryKeys of document library folders associated with the document library file entry type.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @return long[] of the primaryKeys of document library folders associated with the document library file entry type
	 */
	@Override
	public long[] getDLFolderPrimaryKeys(long pk) {
		long[] pks = dlFileEntryTypeToDLFolderTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the document library folders associated with the document library file entry type.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @return the document library folders associated with the document library file entry type
	 */
	@Override
	public List<com.liferay.document.library.kernel.model.DLFolder> getDLFolders(
		long pk) {
		return getDLFolders(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the document library folders associated with the document library file entry type.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the document library file entry type
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @return the range of document library folders associated with the document library file entry type
	 */
	@Override
	public List<com.liferay.document.library.kernel.model.DLFolder> getDLFolders(
		long pk, int start, int end) {
		return getDLFolders(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library folders associated with the document library file entry type.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DLFileEntryTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the document library file entry type
	 * @param start the lower bound of the range of document library file entry types
	 * @param end the upper bound of the range of document library file entry types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of document library folders associated with the document library file entry type
	 */
	@Override
	public List<com.liferay.document.library.kernel.model.DLFolder> getDLFolders(
		long pk, int start, int end,
		OrderByComparator<com.liferay.document.library.kernel.model.DLFolder> orderByComparator) {
		return dlFileEntryTypeToDLFolderTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of document library folders associated with the document library file entry type.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @return the number of document library folders associated with the document library file entry type
	 */
	@Override
	public int getDLFoldersSize(long pk) {
		long[] pks = dlFileEntryTypeToDLFolderTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the document library folder is associated with the document library file entry type.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @param dlFolderPK the primary key of the document library folder
	 * @return <code>true</code> if the document library folder is associated with the document library file entry type; <code>false</code> otherwise
	 */
	@Override
	public boolean containsDLFolder(long pk, long dlFolderPK) {
		return dlFileEntryTypeToDLFolderTableMapper.containsTableMapping(pk,
			dlFolderPK);
	}

	/**
	 * Returns <code>true</code> if the document library file entry type has any document library folders associated with it.
	 *
	 * @param pk the primary key of the document library file entry type to check for associations with document library folders
	 * @return <code>true</code> if the document library file entry type has any document library folders associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsDLFolders(long pk) {
		if (getDLFoldersSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the document library file entry type and the document library folder. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @param dlFolderPK the primary key of the document library folder
	 */
	@Override
	public void addDLFolder(long pk, long dlFolderPK) {
		DLFileEntryType dlFileEntryType = fetchByPrimaryKey(pk);

		if (dlFileEntryType == null) {
			dlFileEntryTypeToDLFolderTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, dlFolderPK);
		}
		else {
			dlFileEntryTypeToDLFolderTableMapper.addTableMapping(dlFileEntryType.getCompanyId(),
				pk, dlFolderPK);
		}
	}

	/**
	 * Adds an association between the document library file entry type and the document library folder. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @param dlFolder the document library folder
	 */
	@Override
	public void addDLFolder(long pk,
		com.liferay.document.library.kernel.model.DLFolder dlFolder) {
		DLFileEntryType dlFileEntryType = fetchByPrimaryKey(pk);

		if (dlFileEntryType == null) {
			dlFileEntryTypeToDLFolderTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, dlFolder.getPrimaryKey());
		}
		else {
			dlFileEntryTypeToDLFolderTableMapper.addTableMapping(dlFileEntryType.getCompanyId(),
				pk, dlFolder.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the document library file entry type and the document library folders. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @param dlFolderPKs the primary keys of the document library folders
	 */
	@Override
	public void addDLFolders(long pk, long[] dlFolderPKs) {
		long companyId = 0;

		DLFileEntryType dlFileEntryType = fetchByPrimaryKey(pk);

		if (dlFileEntryType == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = dlFileEntryType.getCompanyId();
		}

		dlFileEntryTypeToDLFolderTableMapper.addTableMappings(companyId, pk,
			dlFolderPKs);
	}

	/**
	 * Adds an association between the document library file entry type and the document library folders. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @param dlFolders the document library folders
	 */
	@Override
	public void addDLFolders(long pk,
		List<com.liferay.document.library.kernel.model.DLFolder> dlFolders) {
		addDLFolders(pk,
			ListUtil.toLongArray(dlFolders,
				com.liferay.document.library.kernel.model.DLFolder.FOLDER_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the document library file entry type and its document library folders. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the document library file entry type to clear the associated document library folders from
	 */
	@Override
	public void clearDLFolders(long pk) {
		dlFileEntryTypeToDLFolderTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the document library file entry type and the document library folder. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @param dlFolderPK the primary key of the document library folder
	 */
	@Override
	public void removeDLFolder(long pk, long dlFolderPK) {
		dlFileEntryTypeToDLFolderTableMapper.deleteTableMapping(pk, dlFolderPK);
	}

	/**
	 * Removes the association between the document library file entry type and the document library folder. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @param dlFolder the document library folder
	 */
	@Override
	public void removeDLFolder(long pk,
		com.liferay.document.library.kernel.model.DLFolder dlFolder) {
		dlFileEntryTypeToDLFolderTableMapper.deleteTableMapping(pk,
			dlFolder.getPrimaryKey());
	}

	/**
	 * Removes the association between the document library file entry type and the document library folders. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @param dlFolderPKs the primary keys of the document library folders
	 */
	@Override
	public void removeDLFolders(long pk, long[] dlFolderPKs) {
		dlFileEntryTypeToDLFolderTableMapper.deleteTableMappings(pk, dlFolderPKs);
	}

	/**
	 * Removes the association between the document library file entry type and the document library folders. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @param dlFolders the document library folders
	 */
	@Override
	public void removeDLFolders(long pk,
		List<com.liferay.document.library.kernel.model.DLFolder> dlFolders) {
		removeDLFolders(pk,
			ListUtil.toLongArray(dlFolders,
				com.liferay.document.library.kernel.model.DLFolder.FOLDER_ID_ACCESSOR));
	}

	/**
	 * Sets the document library folders associated with the document library file entry type, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @param dlFolderPKs the primary keys of the document library folders to be associated with the document library file entry type
	 */
	@Override
	public void setDLFolders(long pk, long[] dlFolderPKs) {
		Set<Long> newDLFolderPKsSet = SetUtil.fromArray(dlFolderPKs);
		Set<Long> oldDLFolderPKsSet = SetUtil.fromArray(dlFileEntryTypeToDLFolderTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeDLFolderPKsSet = new HashSet<Long>(oldDLFolderPKsSet);

		removeDLFolderPKsSet.removeAll(newDLFolderPKsSet);

		dlFileEntryTypeToDLFolderTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeDLFolderPKsSet));

		newDLFolderPKsSet.removeAll(oldDLFolderPKsSet);

		long companyId = 0;

		DLFileEntryType dlFileEntryType = fetchByPrimaryKey(pk);

		if (dlFileEntryType == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = dlFileEntryType.getCompanyId();
		}

		dlFileEntryTypeToDLFolderTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newDLFolderPKsSet));
	}

	/**
	 * Sets the document library folders associated with the document library file entry type, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the document library file entry type
	 * @param dlFolders the document library folders to be associated with the document library file entry type
	 */
	@Override
	public void setDLFolders(long pk,
		List<com.liferay.document.library.kernel.model.DLFolder> dlFolders) {
		try {
			long[] dlFolderPKs = new long[dlFolders.size()];

			for (int i = 0; i < dlFolders.size(); i++) {
				com.liferay.document.library.kernel.model.DLFolder dlFolder = dlFolders.get(i);

				dlFolderPKs[i] = dlFolder.getPrimaryKey();
			}

			setDLFolders(pk, dlFolderPKs);
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
		return DLFileEntryTypeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the document library file entry type persistence.
	 */
	public void afterPropertiesSet() {
		dlFileEntryTypeToDLFolderTableMapper = TableMapperFactory.getTableMapper("DLFileEntryTypes_DLFolders",
				"companyId", "fileEntryTypeId", "folderId", this,
				dlFolderPersistence);
	}

	public void destroy() {
		entityCache.removeCache(DLFileEntryTypeImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("DLFileEntryTypes_DLFolders");
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	@BeanReference(type = DLFolderPersistence.class)
	protected DLFolderPersistence dlFolderPersistence;
	protected TableMapper<DLFileEntryType, com.liferay.document.library.kernel.model.DLFolder> dlFileEntryTypeToDLFolderTableMapper;
	private static final String _SQL_SELECT_DLFILEENTRYTYPE = "SELECT dlFileEntryType FROM DLFileEntryType dlFileEntryType";
	private static final String _SQL_SELECT_DLFILEENTRYTYPE_WHERE_PKS_IN = "SELECT dlFileEntryType FROM DLFileEntryType dlFileEntryType WHERE fileEntryTypeId IN (";
	private static final String _SQL_SELECT_DLFILEENTRYTYPE_WHERE = "SELECT dlFileEntryType FROM DLFileEntryType dlFileEntryType WHERE ";
	private static final String _SQL_COUNT_DLFILEENTRYTYPE = "SELECT COUNT(dlFileEntryType) FROM DLFileEntryType dlFileEntryType";
	private static final String _SQL_COUNT_DLFILEENTRYTYPE_WHERE = "SELECT COUNT(dlFileEntryType) FROM DLFileEntryType dlFileEntryType WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "dlFileEntryType.fileEntryTypeId";
	private static final String _FILTER_SQL_SELECT_DLFILEENTRYTYPE_WHERE = "SELECT DISTINCT {dlFileEntryType.*} FROM DLFileEntryType dlFileEntryType WHERE ";
	private static final String _FILTER_SQL_SELECT_DLFILEENTRYTYPE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {DLFileEntryType.*} FROM (SELECT DISTINCT dlFileEntryType.fileEntryTypeId FROM DLFileEntryType dlFileEntryType WHERE ";
	private static final String _FILTER_SQL_SELECT_DLFILEENTRYTYPE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN DLFileEntryType ON TEMP_TABLE.fileEntryTypeId = DLFileEntryType.fileEntryTypeId";
	private static final String _FILTER_SQL_COUNT_DLFILEENTRYTYPE_WHERE = "SELECT COUNT(DISTINCT dlFileEntryType.fileEntryTypeId) AS COUNT_VALUE FROM DLFileEntryType dlFileEntryType WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "dlFileEntryType";
	private static final String _FILTER_ENTITY_TABLE = "DLFileEntryType";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dlFileEntryType.";
	private static final String _ORDER_BY_ENTITY_TABLE = "DLFileEntryType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DLFileEntryType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DLFileEntryType exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DLFileEntryTypePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}