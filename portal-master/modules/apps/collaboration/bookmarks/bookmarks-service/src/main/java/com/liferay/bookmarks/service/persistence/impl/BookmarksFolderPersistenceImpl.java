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

package com.liferay.bookmarks.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.bookmarks.exception.NoSuchFolderException;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.model.impl.BookmarksFolderImpl;
import com.liferay.bookmarks.model.impl.BookmarksFolderModelImpl;
import com.liferay.bookmarks.service.persistence.BookmarksFolderPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
 * The persistence implementation for the bookmarks folder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BookmarksFolderPersistence
 * @see com.liferay.bookmarks.service.persistence.BookmarksFolderUtil
 * @generated
 */
@ProviderType
public class BookmarksFolderPersistenceImpl extends BasePersistenceImpl<BookmarksFolder>
	implements BookmarksFolderPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BookmarksFolderUtil} to access the bookmarks folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BookmarksFolderImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOURCEBLOCKID =
		new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByResourceBlockId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID =
		new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByResourceBlockId",
			new String[] { Long.class.getName() },
			BookmarksFolderModelImpl.RESOURCEBLOCKID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.PARENTFOLDERID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RESOURCEBLOCKID = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByResourceBlockId", new String[] { Long.class.getName() });

	/**
	 * Returns all the bookmarks folders where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @return the matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByResourceBlockId(long resourceBlockId) {
		return findByResourceBlockId(resourceBlockId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders where resourceBlockId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourceBlockId the resource block ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByResourceBlockId(long resourceBlockId,
		int start, int end) {
		return findByResourceBlockId(resourceBlockId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where resourceBlockId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourceBlockId the resource block ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByResourceBlockId(long resourceBlockId,
		int start, int end, OrderByComparator<BookmarksFolder> orderByComparator) {
		return findByResourceBlockId(resourceBlockId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where resourceBlockId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourceBlockId the resource block ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByResourceBlockId(long resourceBlockId,
		int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID;
			finderArgs = new Object[] { resourceBlockId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOURCEBLOCKID;
			finderArgs = new Object[] {
					resourceBlockId,
					
					start, end, orderByComparator
				};
		}

		List<BookmarksFolder> list = null;

		if (retrieveFromCache) {
			list = (List<BookmarksFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BookmarksFolder bookmarksFolder : list) {
					if ((resourceBlockId != bookmarksFolder.getResourceBlockId())) {
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

			query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_RESOURCEBLOCKID_RESOURCEBLOCKID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourceBlockId);

				if (!pagination) {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
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
	 * Returns the first bookmarks folder in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByResourceBlockId_First(long resourceBlockId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByResourceBlockId_First(resourceBlockId,
				orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourceBlockId=");
		msg.append(resourceBlockId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first bookmarks folder in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByResourceBlockId_First(long resourceBlockId,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		List<BookmarksFolder> list = findByResourceBlockId(resourceBlockId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByResourceBlockId_Last(long resourceBlockId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByResourceBlockId_Last(resourceBlockId,
				orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourceBlockId=");
		msg.append(resourceBlockId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByResourceBlockId_Last(long resourceBlockId,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		int count = countByResourceBlockId(resourceBlockId);

		if (count == 0) {
			return null;
		}

		List<BookmarksFolder> list = findByResourceBlockId(resourceBlockId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the bookmarks folders before and after the current bookmarks folder in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param folderId the primary key of the current bookmarks folder
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder[] findByResourceBlockId_PrevAndNext(long folderId,
		long resourceBlockId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			BookmarksFolder[] array = new BookmarksFolderImpl[3];

			array[0] = getByResourceBlockId_PrevAndNext(session,
					bookmarksFolder, resourceBlockId, orderByComparator, true);

			array[1] = bookmarksFolder;

			array[2] = getByResourceBlockId_PrevAndNext(session,
					bookmarksFolder, resourceBlockId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BookmarksFolder getByResourceBlockId_PrevAndNext(
		Session session, BookmarksFolder bookmarksFolder, long resourceBlockId,
		OrderByComparator<BookmarksFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_RESOURCEBLOCKID_RESOURCEBLOCKID_2);

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
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourceBlockId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bookmarksFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BookmarksFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the bookmarks folders where resourceBlockId = &#63; from the database.
	 *
	 * @param resourceBlockId the resource block ID
	 */
	@Override
	public void removeByResourceBlockId(long resourceBlockId) {
		for (BookmarksFolder bookmarksFolder : findByResourceBlockId(
				resourceBlockId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(bookmarksFolder);
		}
	}

	/**
	 * Returns the number of bookmarks folders where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @return the number of matching bookmarks folders
	 */
	@Override
	public int countByResourceBlockId(long resourceBlockId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RESOURCEBLOCKID;

		Object[] finderArgs = new Object[] { resourceBlockId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_RESOURCEBLOCKID_RESOURCEBLOCKID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourceBlockId);

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

	private static final String _FINDER_COLUMN_RESOURCEBLOCKID_RESOURCEBLOCKID_2 =
		"bookmarksFolder.resourceBlockId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			BookmarksFolderModelImpl.UUID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.PARENTFOLDERID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the bookmarks folders where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByUuid(String uuid, int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByUuid(String uuid, int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator,
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

		List<BookmarksFolder> list = null;

		if (retrieveFromCache) {
			list = (List<BookmarksFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BookmarksFolder bookmarksFolder : list) {
					if (!Objects.equals(uuid, bookmarksFolder.getUuid())) {
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

			query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

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
				query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
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
					list = (List<BookmarksFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
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
	 * Returns the first bookmarks folder in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByUuid_First(String uuid,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByUuid_First(uuid,
				orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first bookmarks folder in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByUuid_First(String uuid,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		List<BookmarksFolder> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByUuid_Last(String uuid,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByUuid_Last(uuid,
				orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByUuid_Last(String uuid,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<BookmarksFolder> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the bookmarks folders before and after the current bookmarks folder in the ordered set where uuid = &#63;.
	 *
	 * @param folderId the primary key of the current bookmarks folder
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder[] findByUuid_PrevAndNext(long folderId, String uuid,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			BookmarksFolder[] array = new BookmarksFolderImpl[3];

			array[0] = getByUuid_PrevAndNext(session, bookmarksFolder, uuid,
					orderByComparator, true);

			array[1] = bookmarksFolder;

			array[2] = getByUuid_PrevAndNext(session, bookmarksFolder, uuid,
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

	protected BookmarksFolder getByUuid_PrevAndNext(Session session,
		BookmarksFolder bookmarksFolder, String uuid,
		OrderByComparator<BookmarksFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

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
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(bookmarksFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BookmarksFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the bookmarks folders where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (BookmarksFolder bookmarksFolder : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(bookmarksFolder);
		}
	}

	/**
	 * Returns the number of bookmarks folders where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching bookmarks folders
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "bookmarksFolder.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "bookmarksFolder.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(bookmarksFolder.uuid IS NULL OR bookmarksFolder.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			BookmarksFolderModelImpl.UUID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the bookmarks folder where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchFolderException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByUUID_G(String uuid, long groupId)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByUUID_G(uuid, groupId);

		if (bookmarksFolder == null) {
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

			throw new NoSuchFolderException(msg.toString());
		}

		return bookmarksFolder;
	}

	/**
	 * Returns the bookmarks folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the bookmarks folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof BookmarksFolder) {
			BookmarksFolder bookmarksFolder = (BookmarksFolder)result;

			if (!Objects.equals(uuid, bookmarksFolder.getUuid()) ||
					(groupId != bookmarksFolder.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

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

				List<BookmarksFolder> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					BookmarksFolder bookmarksFolder = list.get(0);

					result = bookmarksFolder;

					cacheResult(bookmarksFolder);

					if ((bookmarksFolder.getUuid() == null) ||
							!bookmarksFolder.getUuid().equals(uuid) ||
							(bookmarksFolder.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, bookmarksFolder);
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
			return (BookmarksFolder)result;
		}
	}

	/**
	 * Removes the bookmarks folder where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the bookmarks folder that was removed
	 */
	@Override
	public BookmarksFolder removeByUUID_G(String uuid, long groupId)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = findByUUID_G(uuid, groupId);

		return remove(bookmarksFolder);
	}

	/**
	 * Returns the number of bookmarks folders where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching bookmarks folders
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "bookmarksFolder.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "bookmarksFolder.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(bookmarksFolder.uuid IS NULL OR bookmarksFolder.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "bookmarksFolder.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			BookmarksFolderModelImpl.UUID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.COMPANYID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.PARENTFOLDERID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the bookmarks folders where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<BookmarksFolder> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator,
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

		List<BookmarksFolder> list = null;

		if (retrieveFromCache) {
			list = (List<BookmarksFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BookmarksFolder bookmarksFolder : list) {
					if (!Objects.equals(uuid, bookmarksFolder.getUuid()) ||
							(companyId != bookmarksFolder.getCompanyId())) {
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

			query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

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
				query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
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
					list = (List<BookmarksFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
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
	 * Returns the first bookmarks folder in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first bookmarks folder in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		List<BookmarksFolder> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<BookmarksFolder> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the bookmarks folders before and after the current bookmarks folder in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param folderId the primary key of the current bookmarks folder
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder[] findByUuid_C_PrevAndNext(long folderId,
		String uuid, long companyId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			BookmarksFolder[] array = new BookmarksFolderImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, bookmarksFolder, uuid,
					companyId, orderByComparator, true);

			array[1] = bookmarksFolder;

			array[2] = getByUuid_C_PrevAndNext(session, bookmarksFolder, uuid,
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

	protected BookmarksFolder getByUuid_C_PrevAndNext(Session session,
		BookmarksFolder bookmarksFolder, String uuid, long companyId,
		OrderByComparator<BookmarksFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

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
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(bookmarksFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BookmarksFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the bookmarks folders where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (BookmarksFolder bookmarksFolder : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(bookmarksFolder);
		}
	}

	/**
	 * Returns the number of bookmarks folders where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching bookmarks folders
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "bookmarksFolder.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "bookmarksFolder.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(bookmarksFolder.uuid IS NULL OR bookmarksFolder.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "bookmarksFolder.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			BookmarksFolderModelImpl.GROUPID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.PARENTFOLDERID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the bookmarks folders where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByGroupId(long groupId, int start,
		int end, OrderByComparator<BookmarksFolder> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByGroupId(long groupId, int start,
		int end, OrderByComparator<BookmarksFolder> orderByComparator,
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

		List<BookmarksFolder> list = null;

		if (retrieveFromCache) {
			list = (List<BookmarksFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BookmarksFolder bookmarksFolder : list) {
					if ((groupId != bookmarksFolder.getGroupId())) {
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

			query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
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
	 * Returns the first bookmarks folder in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByGroupId_First(long groupId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByGroupId_First(groupId,
				orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first bookmarks folder in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByGroupId_First(long groupId,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		List<BookmarksFolder> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByGroupId_Last(long groupId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByGroupId_Last(long groupId,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<BookmarksFolder> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the bookmarks folders before and after the current bookmarks folder in the ordered set where groupId = &#63;.
	 *
	 * @param folderId the primary key of the current bookmarks folder
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder[] findByGroupId_PrevAndNext(long folderId,
		long groupId, OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			BookmarksFolder[] array = new BookmarksFolderImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, bookmarksFolder,
					groupId, orderByComparator, true);

			array[1] = bookmarksFolder;

			array[2] = getByGroupId_PrevAndNext(session, bookmarksFolder,
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

	protected BookmarksFolder getByGroupId_PrevAndNext(Session session,
		BookmarksFolder bookmarksFolder, long groupId,
		OrderByComparator<BookmarksFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

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
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bookmarksFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BookmarksFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the bookmarks folders that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching bookmarks folders that the user has permission to view
	 */
	@Override
	public List<BookmarksFolder> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders that the user has permission to view
	 */
	@Override
	public List<BookmarksFolder> filterFindByGroupId(long groupId, int start,
		int end) {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders that the user has permission to view
	 */
	@Override
	public List<BookmarksFolder> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<BookmarksFolder> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
				orderByComparator);
		}
		else {
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BookmarksFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<BookmarksFolder>)QueryUtil.list(q, getDialect(),
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
	 * Returns the bookmarks folders before and after the current bookmarks folder in the ordered set of bookmarks folders that the user has permission to view where groupId = &#63;.
	 *
	 * @param folderId the primary key of the current bookmarks folder
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder[] filterFindByGroupId_PrevAndNext(long folderId,
		long groupId, OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(folderId, groupId,
				orderByComparator);
		}

		BookmarksFolder bookmarksFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			BookmarksFolder[] array = new BookmarksFolderImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, bookmarksFolder,
					groupId, orderByComparator, true);

			array[1] = bookmarksFolder;

			array[2] = filterGetByGroupId_PrevAndNext(session, bookmarksFolder,
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

	protected BookmarksFolder filterGetByGroupId_PrevAndNext(Session session,
		BookmarksFolder bookmarksFolder, long groupId,
		OrderByComparator<BookmarksFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

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
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BookmarksFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bookmarksFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BookmarksFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the bookmarks folders where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (BookmarksFolder bookmarksFolder : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(bookmarksFolder);
		}
	}

	/**
	 * Returns the number of bookmarks folders where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching bookmarks folders
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

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
	 * Returns the number of bookmarks folders that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching bookmarks folders that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BookmarksFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "bookmarksFolder.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			BookmarksFolderModelImpl.COMPANYID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.PARENTFOLDERID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the bookmarks folders where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the bookmarks folders where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<BookmarksFolder> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<BookmarksFolder> orderByComparator,
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

		List<BookmarksFolder> list = null;

		if (retrieveFromCache) {
			list = (List<BookmarksFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BookmarksFolder bookmarksFolder : list) {
					if ((companyId != bookmarksFolder.getCompanyId())) {
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

			query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
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
	 * Returns the first bookmarks folder in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByCompanyId_First(long companyId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first bookmarks folder in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByCompanyId_First(long companyId,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		List<BookmarksFolder> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByCompanyId_Last(long companyId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByCompanyId_Last(long companyId,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<BookmarksFolder> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the bookmarks folders before and after the current bookmarks folder in the ordered set where companyId = &#63;.
	 *
	 * @param folderId the primary key of the current bookmarks folder
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder[] findByCompanyId_PrevAndNext(long folderId,
		long companyId, OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			BookmarksFolder[] array = new BookmarksFolderImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, bookmarksFolder,
					companyId, orderByComparator, true);

			array[1] = bookmarksFolder;

			array[2] = getByCompanyId_PrevAndNext(session, bookmarksFolder,
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

	protected BookmarksFolder getByCompanyId_PrevAndNext(Session session,
		BookmarksFolder bookmarksFolder, long companyId,
		OrderByComparator<BookmarksFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

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
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bookmarksFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BookmarksFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the bookmarks folders where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (BookmarksFolder bookmarksFolder : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(bookmarksFolder);
		}
	}

	/**
	 * Returns the number of bookmarks folders where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching bookmarks folders
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "bookmarksFolder.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_P",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P",
			new String[] { Long.class.getName(), Long.class.getName() },
			BookmarksFolderModelImpl.GROUPID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.PARENTFOLDERID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the bookmarks folders where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @return the matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByG_P(long groupId, long parentFolderId) {
		return findByG_P(groupId, parentFolderId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByG_P(long groupId, long parentFolderId,
		int start, int end) {
		return findByG_P(groupId, parentFolderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByG_P(long groupId, long parentFolderId,
		int start, int end, OrderByComparator<BookmarksFolder> orderByComparator) {
		return findByG_P(groupId, parentFolderId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByG_P(long groupId, long parentFolderId,
		int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P;
			finderArgs = new Object[] { groupId, parentFolderId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P;
			finderArgs = new Object[] {
					groupId, parentFolderId,
					
					start, end, orderByComparator
				};
		}

		List<BookmarksFolder> list = null;

		if (retrieveFromCache) {
			list = (List<BookmarksFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BookmarksFolder bookmarksFolder : list) {
					if ((groupId != bookmarksFolder.getGroupId()) ||
							(parentFolderId != bookmarksFolder.getParentFolderId())) {
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

			query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PARENTFOLDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentFolderId);

				if (!pagination) {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
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
	 * Returns the first bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByG_P_First(long groupId, long parentFolderId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByG_P_First(groupId,
				parentFolderId, orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentFolderId=");
		msg.append(parentFolderId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByG_P_First(long groupId, long parentFolderId,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		List<BookmarksFolder> list = findByG_P(groupId, parentFolderId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByG_P_Last(long groupId, long parentFolderId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByG_P_Last(groupId,
				parentFolderId, orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentFolderId=");
		msg.append(parentFolderId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByG_P_Last(long groupId, long parentFolderId,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		int count = countByG_P(groupId, parentFolderId);

		if (count == 0) {
			return null;
		}

		List<BookmarksFolder> list = findByG_P(groupId, parentFolderId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the bookmarks folders before and after the current bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * @param folderId the primary key of the current bookmarks folder
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder[] findByG_P_PrevAndNext(long folderId, long groupId,
		long parentFolderId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			BookmarksFolder[] array = new BookmarksFolderImpl[3];

			array[0] = getByG_P_PrevAndNext(session, bookmarksFolder, groupId,
					parentFolderId, orderByComparator, true);

			array[1] = bookmarksFolder;

			array[2] = getByG_P_PrevAndNext(session, bookmarksFolder, groupId,
					parentFolderId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BookmarksFolder getByG_P_PrevAndNext(Session session,
		BookmarksFolder bookmarksFolder, long groupId, long parentFolderId,
		OrderByComparator<BookmarksFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PARENTFOLDERID_2);

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
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentFolderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bookmarksFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BookmarksFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the bookmarks folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @return the matching bookmarks folders that the user has permission to view
	 */
	@Override
	public List<BookmarksFolder> filterFindByG_P(long groupId,
		long parentFolderId) {
		return filterFindByG_P(groupId, parentFolderId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders that the user has permission to view
	 */
	@Override
	public List<BookmarksFolder> filterFindByG_P(long groupId,
		long parentFolderId, int start, int end) {
		return filterFindByG_P(groupId, parentFolderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders that the user has permissions to view where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders that the user has permission to view
	 */
	@Override
	public List<BookmarksFolder> filterFindByG_P(long groupId,
		long parentFolderId, int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P(groupId, parentFolderId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PARENTFOLDERID_2);

		if (orderByComparator != null) {
			appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
				orderByComparator);
		}
		else {
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BookmarksFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentFolderId);

			return (List<BookmarksFolder>)QueryUtil.list(q, getDialect(),
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
	 * Returns the bookmarks folders before and after the current bookmarks folder in the ordered set of bookmarks folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * @param folderId the primary key of the current bookmarks folder
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder[] filterFindByG_P_PrevAndNext(long folderId,
		long groupId, long parentFolderId,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_PrevAndNext(folderId, groupId, parentFolderId,
				orderByComparator);
		}

		BookmarksFolder bookmarksFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			BookmarksFolder[] array = new BookmarksFolderImpl[3];

			array[0] = filterGetByG_P_PrevAndNext(session, bookmarksFolder,
					groupId, parentFolderId, orderByComparator, true);

			array[1] = bookmarksFolder;

			array[2] = filterGetByG_P_PrevAndNext(session, bookmarksFolder,
					groupId, parentFolderId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BookmarksFolder filterGetByG_P_PrevAndNext(Session session,
		BookmarksFolder bookmarksFolder, long groupId, long parentFolderId,
		OrderByComparator<BookmarksFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PARENTFOLDERID_2);

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
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BookmarksFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentFolderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bookmarksFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BookmarksFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the bookmarks folders where groupId = &#63; and parentFolderId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 */
	@Override
	public void removeByG_P(long groupId, long parentFolderId) {
		for (BookmarksFolder bookmarksFolder : findByG_P(groupId,
				parentFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(bookmarksFolder);
		}
	}

	/**
	 * Returns the number of bookmarks folders where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @return the number of matching bookmarks folders
	 */
	@Override
	public int countByG_P(long groupId, long parentFolderId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_P;

		Object[] finderArgs = new Object[] { groupId, parentFolderId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PARENTFOLDERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentFolderId);

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
	 * Returns the number of bookmarks folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @return the number of matching bookmarks folders that the user has permission to view
	 */
	@Override
	public int filterCountByG_P(long groupId, long parentFolderId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P(groupId, parentFolderId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PARENTFOLDERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BookmarksFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentFolderId);

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

	private static final String _FINDER_COLUMN_G_P_GROUPID_2 = "bookmarksFolder.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_PARENTFOLDERID_2 = "bookmarksFolder.parentFolderId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_NOTS = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_NotS",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_NOTS = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_NotS",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the bookmarks folders where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @return the matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByC_NotS(long companyId, int status) {
		return findByC_NotS(companyId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders where companyId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByC_NotS(long companyId, int status,
		int start, int end) {
		return findByC_NotS(companyId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where companyId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByC_NotS(long companyId, int status,
		int start, int end, OrderByComparator<BookmarksFolder> orderByComparator) {
		return findByC_NotS(companyId, status, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where companyId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByC_NotS(long companyId, int status,
		int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_NOTS;
		finderArgs = new Object[] {
				companyId, status,
				
				start, end, orderByComparator
			};

		List<BookmarksFolder> list = null;

		if (retrieveFromCache) {
			list = (List<BookmarksFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BookmarksFolder bookmarksFolder : list) {
					if ((companyId != bookmarksFolder.getCompanyId()) ||
							(status == bookmarksFolder.getStatus())) {
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

			query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_C_NOTS_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_NOTS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(status);

				if (!pagination) {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
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
	 * Returns the first bookmarks folder in the ordered set where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByC_NotS_First(long companyId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByC_NotS_First(companyId,
				status, orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first bookmarks folder in the ordered set where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByC_NotS_First(long companyId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		List<BookmarksFolder> list = findByC_NotS(companyId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByC_NotS_Last(long companyId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByC_NotS_Last(companyId, status,
				orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByC_NotS_Last(long companyId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		int count = countByC_NotS(companyId, status);

		if (count == 0) {
			return null;
		}

		List<BookmarksFolder> list = findByC_NotS(companyId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the bookmarks folders before and after the current bookmarks folder in the ordered set where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param folderId the primary key of the current bookmarks folder
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder[] findByC_NotS_PrevAndNext(long folderId,
		long companyId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			BookmarksFolder[] array = new BookmarksFolderImpl[3];

			array[0] = getByC_NotS_PrevAndNext(session, bookmarksFolder,
					companyId, status, orderByComparator, true);

			array[1] = bookmarksFolder;

			array[2] = getByC_NotS_PrevAndNext(session, bookmarksFolder,
					companyId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BookmarksFolder getByC_NotS_PrevAndNext(Session session,
		BookmarksFolder bookmarksFolder, long companyId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_C_NOTS_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_NOTS_STATUS_2);

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
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bookmarksFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BookmarksFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the bookmarks folders where companyId = &#63; and status &ne; &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 */
	@Override
	public void removeByC_NotS(long companyId, int status) {
		for (BookmarksFolder bookmarksFolder : findByC_NotS(companyId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(bookmarksFolder);
		}
	}

	/**
	 * Returns the number of bookmarks folders where companyId = &#63; and status &ne; &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @return the number of matching bookmarks folders
	 */
	@Override
	public int countByC_NotS(long companyId, int status) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_NOTS;

		Object[] finderArgs = new Object[] { companyId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_C_NOTS_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_NOTS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_C_NOTS_COMPANYID_2 = "bookmarksFolder.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_NOTS_STATUS_2 = "bookmarksFolder.status != ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_P_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			BookmarksFolderModelImpl.GROUPID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.PARENTFOLDERID_COLUMN_BITMASK |
			BookmarksFolderModelImpl.STATUS_COLUMN_BITMASK |
			BookmarksFolderModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P_S = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the bookmarks folders where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @return the matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByG_P_S(long groupId, long parentFolderId,
		int status) {
		return findByG_P_S(groupId, parentFolderId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByG_P_S(long groupId, long parentFolderId,
		int status, int start, int end) {
		return findByG_P_S(groupId, parentFolderId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByG_P_S(long groupId, long parentFolderId,
		int status, int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		return findByG_P_S(groupId, parentFolderId, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByG_P_S(long groupId, long parentFolderId,
		int status, int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S;
			finderArgs = new Object[] { groupId, parentFolderId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S;
			finderArgs = new Object[] {
					groupId, parentFolderId, status,
					
					start, end, orderByComparator
				};
		}

		List<BookmarksFolder> list = null;

		if (retrieveFromCache) {
			list = (List<BookmarksFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BookmarksFolder bookmarksFolder : list) {
					if ((groupId != bookmarksFolder.getGroupId()) ||
							(parentFolderId != bookmarksFolder.getParentFolderId()) ||
							(status != bookmarksFolder.getStatus())) {
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

			query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_S_PARENTFOLDERID_2);

			query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentFolderId);

				qPos.add(status);

				if (!pagination) {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
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
	 * Returns the first bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByG_P_S_First(long groupId, long parentFolderId,
		int status, OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByG_P_S_First(groupId,
				parentFolderId, status, orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentFolderId=");
		msg.append(parentFolderId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByG_P_S_First(long groupId,
		long parentFolderId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		List<BookmarksFolder> list = findByG_P_S(groupId, parentFolderId,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByG_P_S_Last(long groupId, long parentFolderId,
		int status, OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByG_P_S_Last(groupId,
				parentFolderId, status, orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentFolderId=");
		msg.append(parentFolderId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByG_P_S_Last(long groupId, long parentFolderId,
		int status, OrderByComparator<BookmarksFolder> orderByComparator) {
		int count = countByG_P_S(groupId, parentFolderId, status);

		if (count == 0) {
			return null;
		}

		List<BookmarksFolder> list = findByG_P_S(groupId, parentFolderId,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the bookmarks folders before and after the current bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * @param folderId the primary key of the current bookmarks folder
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder[] findByG_P_S_PrevAndNext(long folderId,
		long groupId, long parentFolderId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			BookmarksFolder[] array = new BookmarksFolderImpl[3];

			array[0] = getByG_P_S_PrevAndNext(session, bookmarksFolder,
					groupId, parentFolderId, status, orderByComparator, true);

			array[1] = bookmarksFolder;

			array[2] = getByG_P_S_PrevAndNext(session, bookmarksFolder,
					groupId, parentFolderId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BookmarksFolder getByG_P_S_PrevAndNext(Session session,
		BookmarksFolder bookmarksFolder, long groupId, long parentFolderId,
		int status, OrderByComparator<BookmarksFolder> orderByComparator,
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

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_PARENTFOLDERID_2);

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

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
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentFolderId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bookmarksFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BookmarksFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the bookmarks folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @return the matching bookmarks folders that the user has permission to view
	 */
	@Override
	public List<BookmarksFolder> filterFindByG_P_S(long groupId,
		long parentFolderId, int status) {
		return filterFindByG_P_S(groupId, parentFolderId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders that the user has permission to view
	 */
	@Override
	public List<BookmarksFolder> filterFindByG_P_S(long groupId,
		long parentFolderId, int status, int start, int end) {
		return filterFindByG_P_S(groupId, parentFolderId, status, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders that the user has permissions to view where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders that the user has permission to view
	 */
	@Override
	public List<BookmarksFolder> filterFindByG_P_S(long groupId,
		long parentFolderId, int status, int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S(groupId, parentFolderId, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_PARENTFOLDERID_2);

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

		if (orderByComparator != null) {
			appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
				orderByComparator);
		}
		else {
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BookmarksFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentFolderId);

			qPos.add(status);

			return (List<BookmarksFolder>)QueryUtil.list(q, getDialect(),
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
	 * Returns the bookmarks folders before and after the current bookmarks folder in the ordered set of bookmarks folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * @param folderId the primary key of the current bookmarks folder
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder[] filterFindByG_P_S_PrevAndNext(long folderId,
		long groupId, long parentFolderId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S_PrevAndNext(folderId, groupId, parentFolderId,
				status, orderByComparator);
		}

		BookmarksFolder bookmarksFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			BookmarksFolder[] array = new BookmarksFolderImpl[3];

			array[0] = filterGetByG_P_S_PrevAndNext(session, bookmarksFolder,
					groupId, parentFolderId, status, orderByComparator, true);

			array[1] = bookmarksFolder;

			array[2] = filterGetByG_P_S_PrevAndNext(session, bookmarksFolder,
					groupId, parentFolderId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BookmarksFolder filterGetByG_P_S_PrevAndNext(Session session,
		BookmarksFolder bookmarksFolder, long groupId, long parentFolderId,
		int status, OrderByComparator<BookmarksFolder> orderByComparator,
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

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_PARENTFOLDERID_2);

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

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
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BookmarksFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentFolderId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bookmarksFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BookmarksFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the bookmarks folders where groupId = &#63; and parentFolderId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 */
	@Override
	public void removeByG_P_S(long groupId, long parentFolderId, int status) {
		for (BookmarksFolder bookmarksFolder : findByG_P_S(groupId,
				parentFolderId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(bookmarksFolder);
		}
	}

	/**
	 * Returns the number of bookmarks folders where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @return the number of matching bookmarks folders
	 */
	@Override
	public int countByG_P_S(long groupId, long parentFolderId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_P_S;

		Object[] finderArgs = new Object[] { groupId, parentFolderId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_S_PARENTFOLDERID_2);

			query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentFolderId);

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

	/**
	 * Returns the number of bookmarks folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @return the number of matching bookmarks folders that the user has permission to view
	 */
	@Override
	public int filterCountByG_P_S(long groupId, long parentFolderId, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_S(groupId, parentFolderId, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_PARENTFOLDERID_2);

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BookmarksFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentFolderId);

			qPos.add(status);

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

	private static final String _FINDER_COLUMN_G_P_S_GROUPID_2 = "bookmarksFolder.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_PARENTFOLDERID_2 = "bookmarksFolder.parentFolderId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_STATUS_2 = "bookmarksFolder.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_NOTS = new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_P_NotS",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_NOTS =
		new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_P_NotS",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the bookmarks folders where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @return the matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByG_P_NotS(long groupId,
		long parentFolderId, int status) {
		return findByG_P_NotS(groupId, parentFolderId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByG_P_NotS(long groupId,
		long parentFolderId, int status, int start, int end) {
		return findByG_P_NotS(groupId, parentFolderId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByG_P_NotS(long groupId,
		long parentFolderId, int status, int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		return findByG_P_NotS(groupId, parentFolderId, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByG_P_NotS(long groupId,
		long parentFolderId, int status, int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_NOTS;
		finderArgs = new Object[] {
				groupId, parentFolderId, status,
				
				start, end, orderByComparator
			};

		List<BookmarksFolder> list = null;

		if (retrieveFromCache) {
			list = (List<BookmarksFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BookmarksFolder bookmarksFolder : list) {
					if ((groupId != bookmarksFolder.getGroupId()) ||
							(parentFolderId != bookmarksFolder.getParentFolderId()) ||
							(status == bookmarksFolder.getStatus())) {
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

			query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_P_NOTS_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_NOTS_PARENTFOLDERID_2);

			query.append(_FINDER_COLUMN_G_P_NOTS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentFolderId);

				qPos.add(status);

				if (!pagination) {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
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
	 * Returns the first bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByG_P_NotS_First(long groupId,
		long parentFolderId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByG_P_NotS_First(groupId,
				parentFolderId, status, orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentFolderId=");
		msg.append(parentFolderId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByG_P_NotS_First(long groupId,
		long parentFolderId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		List<BookmarksFolder> list = findByG_P_NotS(groupId, parentFolderId,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByG_P_NotS_Last(long groupId,
		long parentFolderId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByG_P_NotS_Last(groupId,
				parentFolderId, status, orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentFolderId=");
		msg.append(parentFolderId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByG_P_NotS_Last(long groupId,
		long parentFolderId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		int count = countByG_P_NotS(groupId, parentFolderId, status);

		if (count == 0) {
			return null;
		}

		List<BookmarksFolder> list = findByG_P_NotS(groupId, parentFolderId,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the bookmarks folders before and after the current bookmarks folder in the ordered set where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param folderId the primary key of the current bookmarks folder
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder[] findByG_P_NotS_PrevAndNext(long folderId,
		long groupId, long parentFolderId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			BookmarksFolder[] array = new BookmarksFolderImpl[3];

			array[0] = getByG_P_NotS_PrevAndNext(session, bookmarksFolder,
					groupId, parentFolderId, status, orderByComparator, true);

			array[1] = bookmarksFolder;

			array[2] = getByG_P_NotS_PrevAndNext(session, bookmarksFolder,
					groupId, parentFolderId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BookmarksFolder getByG_P_NotS_PrevAndNext(Session session,
		BookmarksFolder bookmarksFolder, long groupId, long parentFolderId,
		int status, OrderByComparator<BookmarksFolder> orderByComparator,
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

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_NOTS_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_NOTS_PARENTFOLDERID_2);

		query.append(_FINDER_COLUMN_G_P_NOTS_STATUS_2);

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
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentFolderId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bookmarksFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BookmarksFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the bookmarks folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @return the matching bookmarks folders that the user has permission to view
	 */
	@Override
	public List<BookmarksFolder> filterFindByG_P_NotS(long groupId,
		long parentFolderId, int status) {
		return filterFindByG_P_NotS(groupId, parentFolderId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders that the user has permission to view
	 */
	@Override
	public List<BookmarksFolder> filterFindByG_P_NotS(long groupId,
		long parentFolderId, int status, int start, int end) {
		return filterFindByG_P_NotS(groupId, parentFolderId, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders that the user has permissions to view where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders that the user has permission to view
	 */
	@Override
	public List<BookmarksFolder> filterFindByG_P_NotS(long groupId,
		long parentFolderId, int status, int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_NotS(groupId, parentFolderId, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_NOTS_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_NOTS_PARENTFOLDERID_2);

		query.append(_FINDER_COLUMN_G_P_NOTS_STATUS_2);

		if (orderByComparator != null) {
			appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
				orderByComparator);
		}
		else {
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BookmarksFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentFolderId);

			qPos.add(status);

			return (List<BookmarksFolder>)QueryUtil.list(q, getDialect(),
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
	 * Returns the bookmarks folders before and after the current bookmarks folder in the ordered set of bookmarks folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param folderId the primary key of the current bookmarks folder
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder[] filterFindByG_P_NotS_PrevAndNext(long folderId,
		long groupId, long parentFolderId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_NotS_PrevAndNext(folderId, groupId,
				parentFolderId, status, orderByComparator);
		}

		BookmarksFolder bookmarksFolder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			BookmarksFolder[] array = new BookmarksFolderImpl[3];

			array[0] = filterGetByG_P_NotS_PrevAndNext(session,
					bookmarksFolder, groupId, parentFolderId, status,
					orderByComparator, true);

			array[1] = bookmarksFolder;

			array[2] = filterGetByG_P_NotS_PrevAndNext(session,
					bookmarksFolder, groupId, parentFolderId, status,
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

	protected BookmarksFolder filterGetByG_P_NotS_PrevAndNext(Session session,
		BookmarksFolder bookmarksFolder, long groupId, long parentFolderId,
		int status, OrderByComparator<BookmarksFolder> orderByComparator,
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

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_NOTS_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_NOTS_PARENTFOLDERID_2);

		query.append(_FINDER_COLUMN_G_P_NOTS_STATUS_2);

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
			query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BookmarksFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentFolderId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bookmarksFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BookmarksFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the bookmarks folders where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 */
	@Override
	public void removeByG_P_NotS(long groupId, long parentFolderId, int status) {
		for (BookmarksFolder bookmarksFolder : findByG_P_NotS(groupId,
				parentFolderId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(bookmarksFolder);
		}
	}

	/**
	 * Returns the number of bookmarks folders where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @return the number of matching bookmarks folders
	 */
	@Override
	public int countByG_P_NotS(long groupId, long parentFolderId, int status) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_NOTS;

		Object[] finderArgs = new Object[] { groupId, parentFolderId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_P_NOTS_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_NOTS_PARENTFOLDERID_2);

			query.append(_FINDER_COLUMN_G_P_NOTS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentFolderId);

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

	/**
	 * Returns the number of bookmarks folders that the user has permission to view where groupId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @return the number of matching bookmarks folders that the user has permission to view
	 */
	@Override
	public int filterCountByG_P_NotS(long groupId, long parentFolderId,
		int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_NotS(groupId, parentFolderId, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_NOTS_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_NOTS_PARENTFOLDERID_2);

		query.append(_FINDER_COLUMN_G_P_NOTS_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BookmarksFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentFolderId);

			qPos.add(status);

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

	private static final String _FINDER_COLUMN_G_P_NOTS_GROUPID_2 = "bookmarksFolder.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_NOTS_PARENTFOLDERID_2 = "bookmarksFolder.parentFolderId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_NOTS_STATUS_2 = "bookmarksFolder.status != ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_C_P_NOTS =
		new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED,
			BookmarksFolderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_C_P_NotS",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_C_P_NOTS =
		new FinderPath(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByF_C_P_NotS",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the bookmarks folders where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param folderId the folder ID
	 * @param companyId the company ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @return the matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByF_C_P_NotS(long folderId,
		long companyId, long parentFolderId, int status) {
		return findByF_C_P_NotS(folderId, companyId, parentFolderId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param companyId the company ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByF_C_P_NotS(long folderId,
		long companyId, long parentFolderId, int status, int start, int end) {
		return findByF_C_P_NotS(folderId, companyId, parentFolderId, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param companyId the company ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByF_C_P_NotS(long folderId,
		long companyId, long parentFolderId, int status, int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		return findByF_C_P_NotS(folderId, companyId, parentFolderId, status,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param folderId the folder ID
	 * @param companyId the company ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findByF_C_P_NotS(long folderId,
		long companyId, long parentFolderId, int status, int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_C_P_NOTS;
		finderArgs = new Object[] {
				folderId, companyId, parentFolderId, status,
				
				start, end, orderByComparator
			};

		List<BookmarksFolder> list = null;

		if (retrieveFromCache) {
			list = (List<BookmarksFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BookmarksFolder bookmarksFolder : list) {
					if ((folderId >= bookmarksFolder.getFolderId()) ||
							(companyId != bookmarksFolder.getCompanyId()) ||
							(parentFolderId != bookmarksFolder.getParentFolderId()) ||
							(status == bookmarksFolder.getStatus())) {
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

			query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_F_C_P_NOTS_FOLDERID_2);

			query.append(_FINDER_COLUMN_F_C_P_NOTS_COMPANYID_2);

			query.append(_FINDER_COLUMN_F_C_P_NOTS_PARENTFOLDERID_2);

			query.append(_FINDER_COLUMN_F_C_P_NOTS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BookmarksFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(folderId);

				qPos.add(companyId);

				qPos.add(parentFolderId);

				qPos.add(status);

				if (!pagination) {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
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
	 * Returns the first bookmarks folder in the ordered set where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param folderId the folder ID
	 * @param companyId the company ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByF_C_P_NotS_First(long folderId,
		long companyId, long parentFolderId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByF_C_P_NotS_First(folderId,
				companyId, parentFolderId, status, orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("folderId=");
		msg.append(folderId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", parentFolderId=");
		msg.append(parentFolderId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first bookmarks folder in the ordered set where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param folderId the folder ID
	 * @param companyId the company ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByF_C_P_NotS_First(long folderId,
		long companyId, long parentFolderId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		List<BookmarksFolder> list = findByF_C_P_NotS(folderId, companyId,
				parentFolderId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param folderId the folder ID
	 * @param companyId the company ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder
	 * @throws NoSuchFolderException if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder findByF_C_P_NotS_Last(long folderId, long companyId,
		long parentFolderId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByF_C_P_NotS_Last(folderId,
				companyId, parentFolderId, status, orderByComparator);

		if (bookmarksFolder != null) {
			return bookmarksFolder;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("folderId=");
		msg.append(folderId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", parentFolderId=");
		msg.append(parentFolderId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last bookmarks folder in the ordered set where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param folderId the folder ID
	 * @param companyId the company ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bookmarks folder, or <code>null</code> if a matching bookmarks folder could not be found
	 */
	@Override
	public BookmarksFolder fetchByF_C_P_NotS_Last(long folderId,
		long companyId, long parentFolderId, int status,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		int count = countByF_C_P_NotS(folderId, companyId, parentFolderId,
				status);

		if (count == 0) {
			return null;
		}

		List<BookmarksFolder> list = findByF_C_P_NotS(folderId, companyId,
				parentFolderId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the bookmarks folders where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63; from the database.
	 *
	 * @param folderId the folder ID
	 * @param companyId the company ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 */
	@Override
	public void removeByF_C_P_NotS(long folderId, long companyId,
		long parentFolderId, int status) {
		for (BookmarksFolder bookmarksFolder : findByF_C_P_NotS(folderId,
				companyId, parentFolderId, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(bookmarksFolder);
		}
	}

	/**
	 * Returns the number of bookmarks folders where folderId &gt; &#63; and companyId = &#63; and parentFolderId = &#63; and status &ne; &#63;.
	 *
	 * @param folderId the folder ID
	 * @param companyId the company ID
	 * @param parentFolderId the parent folder ID
	 * @param status the status
	 * @return the number of matching bookmarks folders
	 */
	@Override
	public int countByF_C_P_NotS(long folderId, long companyId,
		long parentFolderId, int status) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_C_P_NOTS;

		Object[] finderArgs = new Object[] {
				folderId, companyId, parentFolderId, status
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_BOOKMARKSFOLDER_WHERE);

			query.append(_FINDER_COLUMN_F_C_P_NOTS_FOLDERID_2);

			query.append(_FINDER_COLUMN_F_C_P_NOTS_COMPANYID_2);

			query.append(_FINDER_COLUMN_F_C_P_NOTS_PARENTFOLDERID_2);

			query.append(_FINDER_COLUMN_F_C_P_NOTS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(folderId);

				qPos.add(companyId);

				qPos.add(parentFolderId);

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

	private static final String _FINDER_COLUMN_F_C_P_NOTS_FOLDERID_2 = "bookmarksFolder.folderId > ? AND ";
	private static final String _FINDER_COLUMN_F_C_P_NOTS_COMPANYID_2 = "bookmarksFolder.companyId = ? AND ";
	private static final String _FINDER_COLUMN_F_C_P_NOTS_PARENTFOLDERID_2 = "bookmarksFolder.parentFolderId = ? AND ";
	private static final String _FINDER_COLUMN_F_C_P_NOTS_STATUS_2 = "bookmarksFolder.status != ?";

	public BookmarksFolderPersistenceImpl() {
		setModelClass(BookmarksFolder.class);
	}

	/**
	 * Caches the bookmarks folder in the entity cache if it is enabled.
	 *
	 * @param bookmarksFolder the bookmarks folder
	 */
	@Override
	public void cacheResult(BookmarksFolder bookmarksFolder) {
		entityCache.putResult(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderImpl.class, bookmarksFolder.getPrimaryKey(),
			bookmarksFolder);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { bookmarksFolder.getUuid(), bookmarksFolder.getGroupId() },
			bookmarksFolder);

		bookmarksFolder.resetOriginalValues();
	}

	/**
	 * Caches the bookmarks folders in the entity cache if it is enabled.
	 *
	 * @param bookmarksFolders the bookmarks folders
	 */
	@Override
	public void cacheResult(List<BookmarksFolder> bookmarksFolders) {
		for (BookmarksFolder bookmarksFolder : bookmarksFolders) {
			if (entityCache.getResult(
						BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
						BookmarksFolderImpl.class,
						bookmarksFolder.getPrimaryKey()) == null) {
				cacheResult(bookmarksFolder);
			}
			else {
				bookmarksFolder.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all bookmarks folders.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BookmarksFolderImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the bookmarks folder.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BookmarksFolder bookmarksFolder) {
		entityCache.removeResult(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderImpl.class, bookmarksFolder.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((BookmarksFolderModelImpl)bookmarksFolder);
	}

	@Override
	public void clearCache(List<BookmarksFolder> bookmarksFolders) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BookmarksFolder bookmarksFolder : bookmarksFolders) {
			entityCache.removeResult(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
				BookmarksFolderImpl.class, bookmarksFolder.getPrimaryKey());

			clearUniqueFindersCache((BookmarksFolderModelImpl)bookmarksFolder);
		}
	}

	protected void cacheUniqueFindersCache(
		BookmarksFolderModelImpl bookmarksFolderModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					bookmarksFolderModelImpl.getUuid(),
					bookmarksFolderModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				bookmarksFolderModelImpl);
		}
		else {
			if ((bookmarksFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bookmarksFolderModelImpl.getUuid(),
						bookmarksFolderModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					bookmarksFolderModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		BookmarksFolderModelImpl bookmarksFolderModelImpl) {
		Object[] args = new Object[] {
				bookmarksFolderModelImpl.getUuid(),
				bookmarksFolderModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((bookmarksFolderModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					bookmarksFolderModelImpl.getOriginalUuid(),
					bookmarksFolderModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new bookmarks folder with the primary key. Does not add the bookmarks folder to the database.
	 *
	 * @param folderId the primary key for the new bookmarks folder
	 * @return the new bookmarks folder
	 */
	@Override
	public BookmarksFolder create(long folderId) {
		BookmarksFolder bookmarksFolder = new BookmarksFolderImpl();

		bookmarksFolder.setNew(true);
		bookmarksFolder.setPrimaryKey(folderId);

		String uuid = PortalUUIDUtil.generate();

		bookmarksFolder.setUuid(uuid);

		bookmarksFolder.setCompanyId(companyProvider.getCompanyId());

		return bookmarksFolder;
	}

	/**
	 * Removes the bookmarks folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param folderId the primary key of the bookmarks folder
	 * @return the bookmarks folder that was removed
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder remove(long folderId) throws NoSuchFolderException {
		return remove((Serializable)folderId);
	}

	/**
	 * Removes the bookmarks folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the bookmarks folder
	 * @return the bookmarks folder that was removed
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder remove(Serializable primaryKey)
		throws NoSuchFolderException {
		Session session = null;

		try {
			session = openSession();

			BookmarksFolder bookmarksFolder = (BookmarksFolder)session.get(BookmarksFolderImpl.class,
					primaryKey);

			if (bookmarksFolder == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(bookmarksFolder);
		}
		catch (NoSuchFolderException nsee) {
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
	protected BookmarksFolder removeImpl(BookmarksFolder bookmarksFolder) {
		bookmarksFolder = toUnwrappedModel(bookmarksFolder);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(bookmarksFolder)) {
				bookmarksFolder = (BookmarksFolder)session.get(BookmarksFolderImpl.class,
						bookmarksFolder.getPrimaryKeyObj());
			}

			if (bookmarksFolder != null) {
				session.delete(bookmarksFolder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (bookmarksFolder != null) {
			clearCache(bookmarksFolder);
		}

		return bookmarksFolder;
	}

	@Override
	public BookmarksFolder updateImpl(BookmarksFolder bookmarksFolder) {
		bookmarksFolder = toUnwrappedModel(bookmarksFolder);

		boolean isNew = bookmarksFolder.isNew();

		BookmarksFolderModelImpl bookmarksFolderModelImpl = (BookmarksFolderModelImpl)bookmarksFolder;

		if (Validator.isNull(bookmarksFolder.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			bookmarksFolder.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (bookmarksFolder.getCreateDate() == null)) {
			if (serviceContext == null) {
				bookmarksFolder.setCreateDate(now);
			}
			else {
				bookmarksFolder.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!bookmarksFolderModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				bookmarksFolder.setModifiedDate(now);
			}
			else {
				bookmarksFolder.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (bookmarksFolder.isNew()) {
				session.save(bookmarksFolder);

				bookmarksFolder.setNew(false);
			}
			else {
				bookmarksFolder = (BookmarksFolder)session.merge(bookmarksFolder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !BookmarksFolderModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((bookmarksFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bookmarksFolderModelImpl.getOriginalResourceBlockId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RESOURCEBLOCKID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID,
					args);

				args = new Object[] {
						bookmarksFolderModelImpl.getResourceBlockId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RESOURCEBLOCKID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID,
					args);
			}

			if ((bookmarksFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bookmarksFolderModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { bookmarksFolderModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((bookmarksFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bookmarksFolderModelImpl.getOriginalUuid(),
						bookmarksFolderModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						bookmarksFolderModelImpl.getUuid(),
						bookmarksFolderModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((bookmarksFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bookmarksFolderModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { bookmarksFolderModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((bookmarksFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bookmarksFolderModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { bookmarksFolderModelImpl.getCompanyId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((bookmarksFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bookmarksFolderModelImpl.getOriginalGroupId(),
						bookmarksFolderModelImpl.getOriginalParentFolderId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P,
					args);

				args = new Object[] {
						bookmarksFolderModelImpl.getGroupId(),
						bookmarksFolderModelImpl.getParentFolderId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P,
					args);
			}

			if ((bookmarksFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bookmarksFolderModelImpl.getOriginalGroupId(),
						bookmarksFolderModelImpl.getOriginalParentFolderId(),
						bookmarksFolderModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S,
					args);

				args = new Object[] {
						bookmarksFolderModelImpl.getGroupId(),
						bookmarksFolderModelImpl.getParentFolderId(),
						bookmarksFolderModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S,
					args);
			}
		}

		entityCache.putResult(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
			BookmarksFolderImpl.class, bookmarksFolder.getPrimaryKey(),
			bookmarksFolder, false);

		clearUniqueFindersCache(bookmarksFolderModelImpl);
		cacheUniqueFindersCache(bookmarksFolderModelImpl, isNew);

		bookmarksFolder.resetOriginalValues();

		return bookmarksFolder;
	}

	protected BookmarksFolder toUnwrappedModel(BookmarksFolder bookmarksFolder) {
		if (bookmarksFolder instanceof BookmarksFolderImpl) {
			return bookmarksFolder;
		}

		BookmarksFolderImpl bookmarksFolderImpl = new BookmarksFolderImpl();

		bookmarksFolderImpl.setNew(bookmarksFolder.isNew());
		bookmarksFolderImpl.setPrimaryKey(bookmarksFolder.getPrimaryKey());

		bookmarksFolderImpl.setUuid(bookmarksFolder.getUuid());
		bookmarksFolderImpl.setFolderId(bookmarksFolder.getFolderId());
		bookmarksFolderImpl.setGroupId(bookmarksFolder.getGroupId());
		bookmarksFolderImpl.setCompanyId(bookmarksFolder.getCompanyId());
		bookmarksFolderImpl.setUserId(bookmarksFolder.getUserId());
		bookmarksFolderImpl.setUserName(bookmarksFolder.getUserName());
		bookmarksFolderImpl.setCreateDate(bookmarksFolder.getCreateDate());
		bookmarksFolderImpl.setModifiedDate(bookmarksFolder.getModifiedDate());
		bookmarksFolderImpl.setResourceBlockId(bookmarksFolder.getResourceBlockId());
		bookmarksFolderImpl.setParentFolderId(bookmarksFolder.getParentFolderId());
		bookmarksFolderImpl.setTreePath(bookmarksFolder.getTreePath());
		bookmarksFolderImpl.setName(bookmarksFolder.getName());
		bookmarksFolderImpl.setDescription(bookmarksFolder.getDescription());
		bookmarksFolderImpl.setLastPublishDate(bookmarksFolder.getLastPublishDate());
		bookmarksFolderImpl.setStatus(bookmarksFolder.getStatus());
		bookmarksFolderImpl.setStatusByUserId(bookmarksFolder.getStatusByUserId());
		bookmarksFolderImpl.setStatusByUserName(bookmarksFolder.getStatusByUserName());
		bookmarksFolderImpl.setStatusDate(bookmarksFolder.getStatusDate());

		return bookmarksFolderImpl;
	}

	/**
	 * Returns the bookmarks folder with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the bookmarks folder
	 * @return the bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFolderException {
		BookmarksFolder bookmarksFolder = fetchByPrimaryKey(primaryKey);

		if (bookmarksFolder == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return bookmarksFolder;
	}

	/**
	 * Returns the bookmarks folder with the primary key or throws a {@link NoSuchFolderException} if it could not be found.
	 *
	 * @param folderId the primary key of the bookmarks folder
	 * @return the bookmarks folder
	 * @throws NoSuchFolderException if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder findByPrimaryKey(long folderId)
		throws NoSuchFolderException {
		return findByPrimaryKey((Serializable)folderId);
	}

	/**
	 * Returns the bookmarks folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the bookmarks folder
	 * @return the bookmarks folder, or <code>null</code> if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
				BookmarksFolderImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		BookmarksFolder bookmarksFolder = (BookmarksFolder)serializable;

		if (bookmarksFolder == null) {
			Session session = null;

			try {
				session = openSession();

				bookmarksFolder = (BookmarksFolder)session.get(BookmarksFolderImpl.class,
						primaryKey);

				if (bookmarksFolder != null) {
					cacheResult(bookmarksFolder);
				}
				else {
					entityCache.putResult(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
						BookmarksFolderImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
					BookmarksFolderImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return bookmarksFolder;
	}

	/**
	 * Returns the bookmarks folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param folderId the primary key of the bookmarks folder
	 * @return the bookmarks folder, or <code>null</code> if a bookmarks folder with the primary key could not be found
	 */
	@Override
	public BookmarksFolder fetchByPrimaryKey(long folderId) {
		return fetchByPrimaryKey((Serializable)folderId);
	}

	@Override
	public Map<Serializable, BookmarksFolder> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, BookmarksFolder> map = new HashMap<Serializable, BookmarksFolder>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			BookmarksFolder bookmarksFolder = fetchByPrimaryKey(primaryKey);

			if (bookmarksFolder != null) {
				map.put(primaryKey, bookmarksFolder);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
					BookmarksFolderImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (BookmarksFolder)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_BOOKMARKSFOLDER_WHERE_PKS_IN);

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

			for (BookmarksFolder bookmarksFolder : (List<BookmarksFolder>)q.list()) {
				map.put(bookmarksFolder.getPrimaryKeyObj(), bookmarksFolder);

				cacheResult(bookmarksFolder);

				uncachedPrimaryKeys.remove(bookmarksFolder.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(BookmarksFolderModelImpl.ENTITY_CACHE_ENABLED,
					BookmarksFolderImpl.class, primaryKey, nullModel);
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
	 * Returns all the bookmarks folders.
	 *
	 * @return the bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bookmarks folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @return the range of bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findAll(int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bookmarks folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BookmarksFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bookmarks folders
	 * @param end the upper bound of the range of bookmarks folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of bookmarks folders
	 */
	@Override
	public List<BookmarksFolder> findAll(int start, int end,
		OrderByComparator<BookmarksFolder> orderByComparator,
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

		List<BookmarksFolder> list = null;

		if (retrieveFromCache) {
			list = (List<BookmarksFolder>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_BOOKMARKSFOLDER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BOOKMARKSFOLDER;

				if (pagination) {
					sql = sql.concat(BookmarksFolderModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BookmarksFolder>)QueryUtil.list(q,
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
	 * Removes all the bookmarks folders from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BookmarksFolder bookmarksFolder : findAll()) {
			remove(bookmarksFolder);
		}
	}

	/**
	 * Returns the number of bookmarks folders.
	 *
	 * @return the number of bookmarks folders
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BOOKMARKSFOLDER);

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
		return BookmarksFolderModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the bookmarks folder persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(BookmarksFolderImpl.class.getName());
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
	private static final String _SQL_SELECT_BOOKMARKSFOLDER = "SELECT bookmarksFolder FROM BookmarksFolder bookmarksFolder";
	private static final String _SQL_SELECT_BOOKMARKSFOLDER_WHERE_PKS_IN = "SELECT bookmarksFolder FROM BookmarksFolder bookmarksFolder WHERE folderId IN (";
	private static final String _SQL_SELECT_BOOKMARKSFOLDER_WHERE = "SELECT bookmarksFolder FROM BookmarksFolder bookmarksFolder WHERE ";
	private static final String _SQL_COUNT_BOOKMARKSFOLDER = "SELECT COUNT(bookmarksFolder) FROM BookmarksFolder bookmarksFolder";
	private static final String _SQL_COUNT_BOOKMARKSFOLDER_WHERE = "SELECT COUNT(bookmarksFolder) FROM BookmarksFolder bookmarksFolder WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "bookmarksFolder.folderId";
	private static final String _FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN = "bookmarksFolder.userId";
	private static final String _ORDER_BY_ENTITY_ALIAS = "bookmarksFolder.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BookmarksFolder exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BookmarksFolder exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(BookmarksFolderPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}