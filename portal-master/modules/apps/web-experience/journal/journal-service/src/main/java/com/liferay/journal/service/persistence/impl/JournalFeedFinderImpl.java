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

package com.liferay.journal.service.persistence.impl;

import com.liferay.journal.model.JournalFeed;
import com.liferay.journal.model.impl.JournalFeedImpl;
import com.liferay.journal.service.persistence.JournalFeedFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;
import java.util.List;

/**
 * @author Raymond Augé
 * @author Connor McKay
 */
public class JournalFeedFinderImpl
	extends JournalFeedFinderBaseImpl implements JournalFeedFinder {

	public static final String COUNT_BY_C_G_F_N_D =
		JournalFeedFinder.class.getName() + ".countByC_G_F_N_D";

	public static final String FIND_BY_C_G_F_N_D =
		JournalFeedFinder.class.getName() + ".findByC_G_F_N_D";

	@Override
	public int countByKeywords(long companyId, long groupId, String keywords) {
		String[] feedIds = null;
		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			feedIds = CustomSQLUtil.keywords(keywords, false);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByC_G_F_N_D(
			companyId, groupId, feedIds, names, descriptions, andOperator);
	}

	@Override
	public int countByC_G_F_N_D(
		long companyId, long groupId, String feedId, String name,
		String description, boolean andOperator) {

		String[] feedIds = CustomSQLUtil.keywords(feedId, false);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description);

		return countByC_G_F_N_D(
			companyId, groupId, feedIds, names, descriptions, andOperator);
	}

	@Override
	public int countByC_G_F_N_D(
		long companyId, long groupId, String[] feedIds, String[] names,
		String[] descriptions, boolean andOperator) {

		feedIds = CustomSQLUtil.keywords(feedIds, false);
		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), COUNT_BY_C_G_F_N_D);

			if (groupId <= 0) {
				sql = StringUtil.replace(
					sql, "(groupId = ?) AND", StringPool.BLANK);
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "feedId", StringPool.LIKE, false, feedIds);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(description)", StringPool.LIKE, true, descriptions);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(feedIds, 2);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<JournalFeed> findByKeywords(
		long companyId, long groupId, String keywords, int start, int end,
		OrderByComparator<JournalFeed> obc) {

		String[] feedIds = null;
		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			feedIds = CustomSQLUtil.keywords(keywords, false);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByC_G_F_N_D(
			companyId, groupId, feedIds, names, descriptions, andOperator,
			start, end, obc);
	}

	@Override
	public List<JournalFeed> findByC_G_F_N_D(
		long companyId, long groupId, String feedId, String name,
		String description, boolean andOperator, int start, int end,
		OrderByComparator<JournalFeed> obc) {

		String[] feedIds = CustomSQLUtil.keywords(feedId, false);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description);

		return findByC_G_F_N_D(
			companyId, groupId, feedIds, names, descriptions, andOperator,
			start, end, obc);
	}

	@Override
	public List<JournalFeed> findByC_G_F_N_D(
		long companyId, long groupId, String[] feedIds, String[] names,
		String[] descriptions, boolean andOperator, int start, int end,
		OrderByComparator<JournalFeed> obc) {

		feedIds = CustomSQLUtil.keywords(feedIds, false);
		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_C_G_F_N_D);

			if (groupId <= 0) {
				sql = StringUtil.replace(
					sql, "(groupId = ?) AND", StringPool.BLANK);
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "feedId", StringPool.LIKE, false, feedIds);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(description)", StringPool.LIKE, true, descriptions);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("JournalFeed", JournalFeedImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(feedIds, 2);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);

			return (List<JournalFeed>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}