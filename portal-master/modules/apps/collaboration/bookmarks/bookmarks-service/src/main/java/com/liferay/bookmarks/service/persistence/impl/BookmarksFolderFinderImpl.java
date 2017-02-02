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

import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.model.impl.BookmarksFolderImpl;
import com.liferay.bookmarks.service.persistence.BookmarksEntryUtil;
import com.liferay.bookmarks.service.persistence.BookmarksFolderFinder;
import com.liferay.bookmarks.service.persistence.BookmarksFolderUtil;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Eudaldo Alonso
 * @author Alexander Chow
 */
public class BookmarksFolderFinderImpl
	extends BookmarksFolderFinderBaseImpl implements BookmarksFolderFinder {

	public static final String COUNT_F_BY_G_P =
		BookmarksFolderFinder.class.getName() + ".countF_ByG_P";

	public static final String COUNT_F_BY_G_P_S =
		BookmarksFolderFinder.class.getName() + ".countF_ByG_P_S";

	public static final String COUNT_E_BY_G_F =
		BookmarksFolderFinder.class.getName() + ".countE_ByG_F";

	public static final String COUNT_E_BY_G_F_S =
		BookmarksFolderFinder.class.getName() + ".countE_ByG_F_S";

	public static final String FIND_BY_NO_ASSETS =
		BookmarksFolderFinder.class.getName() + ".findByNoAssets";

	public static final String FIND_F_BY_G_P =
		BookmarksFolderFinder.class.getName() + ".findF_ByG_P";

	public static final String FIND_F_BY_G_P_S =
		BookmarksFolderFinder.class.getName() + ".findF_ByG_P_S";

	public static final String FIND_E_BY_G_F =
		BookmarksFolderFinder.class.getName() + ".findE_ByG_F";

	public static final String FIND_E_BY_G_F_S =
		BookmarksFolderFinder.class.getName() + ".findE_ByG_F_S";

	@Override
	public int countF_E_ByG_F(
		long groupId, long folderId, QueryDefinition<?> queryDefinition) {

		return doCountF_E_ByG_F(groupId, folderId, queryDefinition, false);
	}

	@Override
	public int filterCountF_E_ByG_F(
		long groupId, long folderId, QueryDefinition<?> queryDefinition) {

		return doCountF_E_ByG_F(groupId, folderId, queryDefinition, true);
	}

	@Override
	public List<Object> filterFindBF_E_ByG_F(
		long groupId, long folderId, QueryDefinition<?> queryDefinition) {

		return doFindF_E_ByG_F(groupId, folderId, queryDefinition, true);
	}

	@Override
	public List<BookmarksFolder> findByNoAssets() {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_NO_ASSETS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("BookmarksFolder", BookmarksFolderImpl.class);

			return q.list(true);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<Object> findF_E_ByG_F(
		long groupId, long folderId, QueryDefinition<?> queryDefinition) {

		return doFindF_E_ByG_F(groupId, folderId, queryDefinition, false);
	}

	protected int doCountF_E_ByG_F(
		long groupId, long folderId, QueryDefinition<?> queryDefinition,
		boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			StringBundler sb = new StringBundler(5);

			sb.append(StringPool.OPEN_PARENTHESIS);

			String sql = null;

			if (queryDefinition.getStatus() == WorkflowConstants.STATUS_ANY) {
				sql = CustomSQLUtil.get(getClass(), COUNT_F_BY_G_P);
			}
			else {
				sql = CustomSQLUtil.get(getClass(), COUNT_F_BY_G_P_S);

				sql = replaceExcludeStatus(sql, queryDefinition);
			}

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, BookmarksFolder.class.getName(),
					"BookmarksFolder.folderId", groupId);
			}

			sb.append(sql);
			sb.append(") UNION ALL (");

			if (queryDefinition.getStatus() == WorkflowConstants.STATUS_ANY) {
				sql = CustomSQLUtil.get(getClass(), COUNT_E_BY_G_F);
			}
			else {
				sql = CustomSQLUtil.get(getClass(), COUNT_E_BY_G_F_S);

				sql = replaceExcludeStatus(sql, queryDefinition);
			}

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, BookmarksEntry.class.getName(),
					"BookmarksEntry.fileEntryId", groupId);
			}

			sb.append(sql);
			sb.append(StringPool.CLOSE_PARENTHESIS);

			sql = sb.toString();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(folderId);

			if (queryDefinition.getStatus() != WorkflowConstants.STATUS_ANY) {
				qPos.add(queryDefinition.getStatus());
			}

			qPos.add(groupId);
			qPos.add(folderId);

			if (queryDefinition.getStatus() != WorkflowConstants.STATUS_ANY) {
				qPos.add(queryDefinition.getStatus());
			}

			int count = 0;

			Iterator<Long> itr = q.iterate();

			while (itr.hasNext()) {
				Long l = itr.next();

				if (l != null) {
					count += l.intValue();
				}
			}

			return count;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<Object> doFindF_E_ByG_F(
		long groupId, long folderId, QueryDefinition<?> queryDefinition,
		boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			StringBundler sb = new StringBundler(5);

			sb.append("SELECT * FROM (");

			String sql = null;

			if (queryDefinition.getStatus() == WorkflowConstants.STATUS_ANY) {
				sql = CustomSQLUtil.get(getClass(), FIND_F_BY_G_P);
			}
			else {
				sql = CustomSQLUtil.get(getClass(), FIND_F_BY_G_P_S);

				sql = replaceExcludeStatus(sql, queryDefinition);
			}

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, BookmarksFolder.class.getName(),
					"BookmarksFolder.folderId", groupId);
			}

			sb.append(sql);
			sb.append(" UNION ALL ");

			if (queryDefinition.getStatus() == WorkflowConstants.STATUS_ANY) {
				sql = CustomSQLUtil.get(getClass(), FIND_E_BY_G_F);
			}
			else {
				sql = CustomSQLUtil.get(getClass(), FIND_E_BY_G_F_S);

				sql = replaceExcludeStatus(sql, queryDefinition);
			}

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, BookmarksEntry.class.getName(),
					"BookmarksEntry.fileEntryId", groupId);
			}

			sb.append(sql);
			sb.append(") TEMP_TABLE ORDER BY modelName ASC");

			sql = sb.toString();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("modelId", Type.LONG);
			q.addScalar("modelName", Type.STRING);
			q.addScalar("modelFolder", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(folderId);

			if (queryDefinition.getStatus() != WorkflowConstants.STATUS_ANY) {
				qPos.add(queryDefinition.getStatus());
			}

			qPos.add(groupId);
			qPos.add(folderId);

			if (queryDefinition.getStatus() != WorkflowConstants.STATUS_ANY) {
				qPos.add(queryDefinition.getStatus());
			}

			List<Object> models = new ArrayList<>();

			Iterator<Object[]> itr = (Iterator<Object[]>)QueryUtil.iterate(
				q, getDialect(), queryDefinition.getStart(),
				queryDefinition.getEnd());

			while (itr.hasNext()) {
				Object[] array = itr.next();

				long modelId = (Long)array[0];
				//String name = (String)array[1];
				long modelFolder = (Long)array[2];

				Object obj = null;

				if (modelFolder == 0) {
					obj = BookmarksFolderUtil.findByPrimaryKey(modelId);
				}
				else {
					obj = BookmarksEntryUtil.findByPrimaryKey(modelId);
				}

				models.add(obj);
			}

			return models;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String replaceExcludeStatus(
		String sql, QueryDefinition<?> queryDefinition) {

		if (queryDefinition.isExcludeStatus()) {
			sql = StringUtil.replace(sql, ".status = ?)", ".status != ?)");
		}

		return sql;
	}

}