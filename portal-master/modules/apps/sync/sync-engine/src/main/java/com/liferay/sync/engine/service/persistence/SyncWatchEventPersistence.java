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

package com.liferay.sync.engine.service.persistence;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.Where;

import com.liferay.sync.engine.model.SyncWatchEvent;

import java.sql.SQLException;

import java.util.List;

/**
 * @author Michael Young
 */
public class SyncWatchEventPersistence
	extends BasePersistenceImpl<SyncWatchEvent, Long> {

	public SyncWatchEventPersistence() throws SQLException {
		super(SyncWatchEvent.class);
	}

	public void deleteBySyncAccountId(long syncAccountId) throws SQLException {
		DeleteBuilder<SyncWatchEvent, Long> deleteBuilder = deleteBuilder();

		Where<SyncWatchEvent, Long> where = deleteBuilder.where();

		where.eq("syncAccountId", syncAccountId);

		deleteBuilder.delete();
	}

	public SyncWatchEvent fetchBySyncAccountId_First(long syncAccountId)
		throws SQLException {

		QueryBuilder<SyncWatchEvent, Long> queryBuilder = queryBuilder();

		queryBuilder.limit(1L);

		Where<SyncWatchEvent, Long> where = queryBuilder.where();

		where.eq("syncAccountId", syncAccountId);

		return where.queryForFirst();
	}

	public SyncWatchEvent fetchByE_F_NotT_First(
			String eventType, String filePathName, long timestamp)
		throws SQLException {

		QueryBuilder<SyncWatchEvent, Long> queryBuilder = queryBuilder();

		queryBuilder.limit(1L);

		Where<SyncWatchEvent, Long> where = queryBuilder.where();

		where.eq("eventType", eventType);
		where.eq("filePathName", new SelectArg(filePathName));
		where.ne("timestamp", timestamp);

		where.and(3);

		return where.queryForFirst();
	}

	public SyncWatchEvent fetchByE_F_T_First(
			String eventType, String filePathName, long timestamp)
		throws SQLException {

		QueryBuilder<SyncWatchEvent, Long> queryBuilder = queryBuilder();

		queryBuilder.limit(1L);

		Where<SyncWatchEvent, Long> where = queryBuilder.where();

		where.eq("eventType", eventType);
		where.eq("filePathName", new SelectArg(filePathName));
		where.between("timestamp", timestamp - 1000, timestamp + 1000);

		where.and(3);

		return where.queryForFirst();
	}

	public List<SyncWatchEvent> findBySyncAccountId(long syncAccountId)
		throws SQLException {

		QueryBuilder<SyncWatchEvent, Long> queryBuilder = queryBuilder();

		queryBuilder.orderBy("fileType", false);

		Where<SyncWatchEvent, Long> where = queryBuilder.where();

		where.eq("syncAccountId", syncAccountId);

		return where.query();
	}

	public List<SyncWatchEvent> findBySyncAccountId(
			long syncAccountId, String orderByColumn, boolean ascending)
		throws SQLException {

		QueryBuilder<SyncWatchEvent, Long> queryBuilder = queryBuilder();

		queryBuilder.orderBy(orderByColumn, ascending);
		queryBuilder.orderBy("fileType", false);

		Where<SyncWatchEvent, Long> where = queryBuilder.where();

		where.eq("syncAccountId", syncAccountId);

		return where.query();
	}

}