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

package com.liferay.portlet.social.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.social.model.impl.SocialActivitySetImpl;
import com.liferay.social.kernel.model.SocialActivitySet;
import com.liferay.social.kernel.service.persistence.SocialActivitySetFinder;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Jonathan Lee
 */
public class SocialActivitySetFinderImpl
	extends SocialActivitySetFinderBaseImpl implements SocialActivitySetFinder {

	public static final String COUNT_BY_RELATION =
		SocialActivitySetFinder.class.getName() + ".countByRelation";

	public static final String COUNT_BY_RELATION_TYPE =
		SocialActivitySetFinder.class.getName() + ".countByRelationType";

	public static final String COUNT_BY_USER =
		SocialActivitySetFinder.class.getName() + ".countByUser";

	public static final String COUNT_BY_USER_GROUPS =
		SocialActivitySetFinder.class.getName() + ".countByUserGroups";

	public static final String FIND_BY_ORGANIZATION_ID =
		SocialActivitySetFinder.class.getName() + ".findByOrganizationId";

	public static final String FIND_BY_RELATION =
		SocialActivitySetFinder.class.getName() + ".findByRelation";

	public static final String FIND_BY_RELATION_TYPE =
		SocialActivitySetFinder.class.getName() + ".findByRelationType";

	public static final String FIND_BY_USER =
		SocialActivitySetFinder.class.getName() + ".findByUser";

	public static final String FIND_BY_USER_GROUPS =
		SocialActivitySetFinder.class.getName() + ".findByUserGroups";

	@Override
	public int countByRelation(long userId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_RELATION);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

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
	public int countByRelationType(long userId, int type) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_RELATION_TYPE);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(type);

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
	public int countByUser(long userId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_USER);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(userId);
			qPos.add(userId);
			qPos.add(userId);
			qPos.add(userId);

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
	public int countByUserGroups(long userId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_USER_GROUPS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(userId);
			qPos.add(userId);

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
	public List<SocialActivitySet> findByOrganizationId(
		long organizationId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_ORGANIZATION_ID);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SocialActivitySet", SocialActivitySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(organizationId);

			return (List<SocialActivitySet>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<SocialActivitySet> findByRelation(
		long userId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_RELATION);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SocialActivitySet", SocialActivitySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			return (List<SocialActivitySet>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<SocialActivitySet> findByRelationType(
		long userId, int type, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_RELATION_TYPE);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SocialActivitySet", SocialActivitySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(type);

			return (List<SocialActivitySet>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<SocialActivitySet> findByUser(long userId, int start, int end) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_USER);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SocialActivitySet", SocialActivitySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(userId);
			qPos.add(userId);
			qPos.add(userId);
			qPos.add(userId);

			return (List<SocialActivitySet>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<SocialActivitySet> findByUserGroups(
		long userId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_USER_GROUPS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SocialActivitySet", SocialActivitySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(userId);
			qPos.add(userId);

			return (List<SocialActivitySet>)QueryUtil.list(
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