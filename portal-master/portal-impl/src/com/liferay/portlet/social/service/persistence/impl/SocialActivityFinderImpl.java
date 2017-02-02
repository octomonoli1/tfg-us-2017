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
import com.liferay.portlet.social.model.impl.SocialActivityImpl;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.service.persistence.SocialActivityFinder;
import com.liferay.social.kernel.service.persistence.SocialActivityUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class SocialActivityFinderImpl
	extends SocialActivityFinderBaseImpl implements SocialActivityFinder {

	public static final String COUNT_BY_GROUP_ID =
		SocialActivityFinder.class.getName() + ".countByGroupId";

	public static final String COUNT_BY_GROUP_USERS =
		SocialActivityFinder.class.getName() + ".countByGroupUsers";

	public static final String COUNT_BY_ORGANIZATION_ID =
		SocialActivityFinder.class.getName() + ".countByOrganizationId";

	public static final String COUNT_BY_ORGANIZATION_USERS =
		SocialActivityFinder.class.getName() + ".countByOrganizationUsers";

	public static final String COUNT_BY_RELATION =
		SocialActivityFinder.class.getName() + ".countByRelation";

	public static final String COUNT_BY_RELATION_TYPE =
		SocialActivityFinder.class.getName() + ".countByRelationType";

	public static final String COUNT_BY_USER_GROUPS =
		SocialActivityFinder.class.getName() + ".countByUserGroups";

	public static final String COUNT_BY_USER_GROUPS_AND_ORGANIZATIONS =
		SocialActivityFinder.class.getName() +
			".countByUserGroupsAndOrganizations";

	public static final String COUNT_BY_USER_ORGANIZATIONS =
		SocialActivityFinder.class.getName() + ".countByUserOrganizations";

	public static final String FIND_BY_GROUP_ID =
		SocialActivityFinder.class.getName() + ".findByGroupId";

	public static final String FIND_BY_GROUP_USERS =
		SocialActivityFinder.class.getName() + ".findByGroupUsers";

	public static final String FIND_BY_ORGANIZATION_ID =
		SocialActivityFinder.class.getName() + ".findByOrganizationId";

	public static final String FIND_BY_ORGANIZATION_USERS =
		SocialActivityFinder.class.getName() + ".findByOrganizationUsers";

	public static final String FIND_BY_RELATION =
		SocialActivityFinder.class.getName() + ".findByRelation";

	public static final String FIND_BY_RELATION_TYPE =
		SocialActivityFinder.class.getName() + ".findByRelationType";

	public static final String FIND_BY_USER_GROUPS =
		SocialActivityFinder.class.getName() + ".findByUserGroups";

	public static final String FIND_BY_USER_GROUPS_AND_ORGANIZATIONS =
		SocialActivityFinder.class.getName() +
			".findByUserGroupsAndOrganizations";

	public static final String FIND_BY_USER_ORGANIZATIONS =
		SocialActivityFinder.class.getName() + ".findByUserOrganizations";

	@Override
	public int countByGroupId(long groupId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_GROUP_ID);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

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
	public int countByGroupUsers(long groupId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_GROUP_USERS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

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
	public int countByOrganizationId(long organizationId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_ORGANIZATION_ID);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(organizationId);

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
	public int countByOrganizationUsers(long organizationId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_ORGANIZATION_USERS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(organizationId);

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
	public int countByUserGroupsAndOrganizations(long userId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				COUNT_BY_USER_GROUPS_AND_ORGANIZATIONS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(userId);

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

	@Override
	public int countByUserOrganizations(long userId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_USER_ORGANIZATIONS);

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
	public List<SocialActivity> findByGroupId(
		long groupId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_GROUP_ID);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<SocialActivity>)QueryUtil.list(
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
	public List<SocialActivity> findByGroupUsers(
		long groupId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_GROUP_USERS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<SocialActivity>)QueryUtil.list(
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
	public List<SocialActivity> findByOrganizationId(
		long organizationId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_ORGANIZATION_ID);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(organizationId);

			return (List<SocialActivity>)QueryUtil.list(
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
	public List<SocialActivity> findByOrganizationUsers(
		long organizationId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_ORGANIZATION_USERS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(organizationId);

			return (List<SocialActivity>)QueryUtil.list(
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
	public List<SocialActivity> findByRelation(
		long userId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_RELATION);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			return (List<SocialActivity>)QueryUtil.list(
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
	public List<SocialActivity> findByRelationType(
		long userId, int type, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_RELATION_TYPE);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(type);

			return (List<SocialActivity>)QueryUtil.list(
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
	public List<SocialActivity> findByUserGroups(
		long userId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_USER_GROUPS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(userId);
			qPos.add(userId);

			return (List<SocialActivity>)QueryUtil.list(
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
	public List<SocialActivity> findByUserGroupsAndOrganizations(
		long userId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				FIND_BY_USER_GROUPS_AND_ORGANIZATIONS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("activityId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(userId);

			List<SocialActivity> socialActivities = new ArrayList<>();

			Iterator<Long> itr = (Iterator<Long>)QueryUtil.iterate(
				q, getDialect(), start, end);

			while (itr.hasNext()) {
				Long activityId = itr.next();

				SocialActivity socialActivity =
					SocialActivityUtil.findByPrimaryKey(activityId);

				socialActivities.add(socialActivity);
			}

			return socialActivities;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<SocialActivity> findByUserOrganizations(
		long userId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_USER_ORGANIZATIONS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			return (List<SocialActivity>)QueryUtil.list(
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