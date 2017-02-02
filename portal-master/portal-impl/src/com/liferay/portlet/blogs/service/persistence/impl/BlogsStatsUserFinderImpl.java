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

package com.liferay.portlet.blogs.service.persistence.impl;

import com.liferay.blogs.kernel.model.BlogsStatsUser;
import com.liferay.blogs.kernel.service.persistence.BlogsStatsUserFinder;
import com.liferay.blogs.kernel.service.persistence.BlogsStatsUserUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.blogs.model.impl.BlogsStatsUserImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class BlogsStatsUserFinderImpl
	extends BlogsStatsUserFinderBaseImpl implements BlogsStatsUserFinder {

	public static final String COUNT_BY_ORGANIZATION_IDS =
		BlogsStatsUserFinder.class.getName() + ".countByOrganizationIds";

	public static final String FIND_BY_GROUP_IDS =
		BlogsStatsUserFinder.class.getName() + ".findByGroupIds";

	public static final String FIND_BY_ORGANIZATION_IDS =
		BlogsStatsUserFinder.class.getName() + ".findByOrganizationIds";

	@Override
	public int countByOrganizationId(long organizationId) {
		List<Long> organizationIds = new ArrayList<>();

		organizationIds.add(organizationId);

		return countByOrganizationIds(organizationIds);
	}

	@Override
	public int countByOrganizationIds(List<Long> organizationIds) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_ORGANIZATION_IDS);

			sql = StringUtil.replace(
				sql, "[$ORGANIZATION_ID$]",
				getOrganizationIds(organizationIds));

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			for (int i = 0; i < organizationIds.size(); i++) {
				Long organizationId = organizationIds.get(i);

				qPos.add(organizationId);
			}

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
	public List<BlogsStatsUser> findByGroupIds(
		long companyId, long groupId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_GROUP_IDS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("userId", Type.LONG);
			q.addScalar("lastPostDate", Type.TIMESTAMP);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupId);
			qPos.add(groupId);
			qPos.add(groupId);

			List<BlogsStatsUser> statsUsers = new ArrayList<>();

			Iterator<Object[]> itr = (Iterator<Object[]>)QueryUtil.iterate(
				q, getDialect(), start, end);

			while (itr.hasNext()) {
				Object[] array = itr.next();

				long userId = (Long)array[0];
				Date lastPostDate = (Date)array[1];

				List<BlogsStatsUser> curStatsUsers =
					BlogsStatsUserUtil.findByU_L(userId, lastPostDate);

				if (!curStatsUsers.isEmpty()) {
					BlogsStatsUser statsUser = curStatsUsers.get(0);

					statsUsers.add(statsUser);
				}
			}

			return statsUsers;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<BlogsStatsUser> findByOrganizationId(
		long organizationId, int start, int end,
		OrderByComparator<BlogsStatsUser> obc) {

		List<Long> organizationIds = new ArrayList<>();

		organizationIds.add(organizationId);

		return findByOrganizationIds(organizationIds, start, end, obc);
	}

	@Override
	public List<BlogsStatsUser> findByOrganizationIds(
		List<Long> organizationIds, int start, int end,
		OrderByComparator<BlogsStatsUser> obc) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_ORGANIZATION_IDS);

			sql = StringUtil.replace(
				sql, "[$ORGANIZATION_ID$]",
				getOrganizationIds(organizationIds));
			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("BlogsStatsUser", BlogsStatsUserImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			for (int i = 0; i < organizationIds.size(); i++) {
				Long organizationId = organizationIds.get(i);

				qPos.add(organizationId);
			}

			return (List<BlogsStatsUser>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getOrganizationIds(List<Long> organizationIds) {
		if (organizationIds.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(organizationIds.size() * 2 - 1);

		for (int i = 0; i < organizationIds.size(); i++) {
			sb.append("Users_Orgs.organizationId = ? ");

			if ((i + 1) != organizationIds.size()) {
				sb.append("OR ");
			}
		}

		return sb.toString();
	}

}