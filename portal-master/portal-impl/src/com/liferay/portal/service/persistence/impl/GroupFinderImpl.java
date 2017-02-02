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

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.security.permission.RolePermissions;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourceBlockLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.GroupFinder;
import com.liferay.portal.kernel.service.persistence.GroupUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.comparator.GroupNameComparator;
import com.liferay.portal.model.impl.GroupImpl;
import com.liferay.portal.service.impl.GroupLocalServiceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class GroupFinderImpl
	extends GroupFinderBaseImpl implements GroupFinder {

	public static final String COUNT_BY_LAYOUTS =
		GroupFinder.class.getName() + ".countByLayouts";

	public static final String COUNT_BY_GROUP_ID =
		GroupFinder.class.getName() + ".countByGroupId";

	public static final String COUNT_BY_C_PG_N_D =
		GroupFinder.class.getName() + ".countByC_PG_N_D";

	public static final String FIND_BY_COMPANY_ID =
		GroupFinder.class.getName() + ".findByCompanyId";

	public static final String FIND_BY_LAYOUTS =
		GroupFinder.class.getName() + ".findByLayouts";

	public static final String FIND_BY_LIVE_GROUPS =
		GroupFinder.class.getName() + ".findByLiveGroups";

	public static final String FIND_BY_NO_LAYOUTS =
		GroupFinder.class.getName() + ".findByNoLayouts";

	public static final String FIND_BY_NULL_FRIENDLY_URL =
		GroupFinder.class.getName() + ".findByNullFriendlyURL";

	public static final String FIND_BY_SYSTEM =
		GroupFinder.class.getName() + ".findBySystem";

	public static final String FIND_BY_C_P =
		GroupFinder.class.getName() + ".findByC_P";

	public static final String FIND_BY_C_GK =
		GroupFinder.class.getName() + ".findByC_GK";

	public static final String FIND_BY_L_TS_S_RSGC =
		GroupFinder.class.getName() + ".findByL_TS_S_RSGC";

	public static final String FIND_BY_C_PG_N_D =
		GroupFinder.class.getName() + ".findByC_PG_N_D";

	public static final String JOIN_BY_ACTIVE =
		GroupFinder.class.getName() + ".joinByActive";

	public static final String JOIN_BY_CREATOR_USER_ID =
		GroupFinder.class.getName() + ".joinByCreatorUserId";

	public static final String JOIN_BY_GROUP_ORG =
		GroupFinder.class.getName() + ".joinByGroupOrg";

	public static final String JOIN_BY_GROUPS_ORGS =
		GroupFinder.class.getName() + ".joinByGroupsOrgs";

	public static final String JOIN_BY_GROUPS_ROLES =
		GroupFinder.class.getName() + ".joinByGroupsRoles";

	public static final String JOIN_BY_GROUPS_USER_GROUPS =
		GroupFinder.class.getName() + ".joinByGroupsUserGroups";

	public static final String JOIN_BY_LAYOUT_SET =
		GroupFinder.class.getName() + ".joinByLayoutSet";

	public static final String JOIN_BY_MANUAL_MEMBERSHIP =
		GroupFinder.class.getName() + ".joinByManualMembership";

	public static final String JOIN_BY_MEMBERSHIP_RESTRICTION =
		GroupFinder.class.getName() + ".joinByMembershipRestriction";

	public static final String JOIN_BY_PAGE_COUNT =
		GroupFinder.class.getName() + ".joinByPageCount";

	public static final String JOIN_BY_ROLE_RESOURCE_PERMISSIONS =
		GroupFinder.class.getName() + ".joinByRoleResourcePermissions";

	public static final String JOIN_BY_ROLE_RESOURCE_TYPE_PERMISSIONS =
		GroupFinder.class.getName() + ".joinByRoleResourceTypePermissions";

	public static final String JOIN_BY_SITE =
		GroupFinder.class.getName() + ".joinBySite";

	public static final String JOIN_BY_TYPE =
		GroupFinder.class.getName() + ".joinByType";

	public static final String JOIN_BY_USER_GROUP_ROLE =
		GroupFinder.class.getName() + ".joinByUserGroupRole";

	public static final String JOIN_BY_USERS_GROUPS =
		GroupFinder.class.getName() + ".joinByUsersGroups";

	@Override
	public int countByLayouts(
		long companyId, long parentGroupId, boolean site) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_LAYOUTS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(parentGroupId);
			qPos.add(site);

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
	public int countByG_U(long groupId, long userId, boolean inherit) {
		LinkedHashMap<String, Object> params1 = new LinkedHashMap<>();

		params1.put("usersGroups", userId);

		LinkedHashMap<String, Object> params2 = new LinkedHashMap<>();

		params2.put("groupOrg", userId);

		LinkedHashMap<String, Object> params3 = new LinkedHashMap<>();

		params3.put("groupsOrgs", userId);

		LinkedHashMap<String, Object> params4 = new LinkedHashMap<>();

		params4.put("groupsUserGroups", userId);

		Session session = null;

		try {
			session = openSession();

			int count = countByGroupId(session, groupId, params1);

			if (inherit) {
				count += countByGroupId(session, groupId, params2);
				count += countByGroupId(session, groupId, params3);
				count += countByGroupId(session, groupId, params4);
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
	public int countByC_C_PG_N_D(
		long companyId, long[] classNameIds, long parentGroupId, String[] names,
		String[] descriptions, LinkedHashMap<String, Object> params,
		boolean andOperator) {

		String parentGroupIdComparator = StringPool.EQUAL;

		if (parentGroupId == GroupConstants.ANY_PARENT_GROUP_ID) {
			parentGroupIdComparator = StringPool.NOT_EQUAL;
		}

		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions);

		if (params == null) {
			params = _emptyLinkedHashMap;
		}

		LinkedHashMap<String, Object> params1 = params;

		LinkedHashMap<String, Object> params2 = null;

		LinkedHashMap<String, Object> params3 = null;

		LinkedHashMap<String, Object> params4 = null;

		Long userId = (Long)params.get("usersGroups");

		boolean doUnion = Validator.isNotNull(userId);

		if (doUnion) {
			params2 = new LinkedHashMap<>(params1);
			params3 = new LinkedHashMap<>(params1);
			params4 = new LinkedHashMap<>(params1);

			_populateUnionParams(
				userId, classNameIds, params1, params2, params3, params4);
		}
		else if (classNameIds != null) {
			params1.put("classNameIds", classNameIds);
		}

		Session session = null;

		try {
			session = openSession();

			Set<Long> groupIds = new HashSet<>();

			groupIds.addAll(
				countByC_PG_N_D(
					session, companyId, parentGroupId, parentGroupIdComparator,
					names, descriptions, params1, andOperator));

			if (doUnion) {
				if (params2.containsKey("classNameIds")) {
					groupIds.addAll(
						countByC_PG_N_D(
							session, companyId, parentGroupId,
							parentGroupIdComparator, names, descriptions,
							params2, andOperator));
				}

				if (params3.containsKey("classNameIds")) {
					groupIds.addAll(
						countByC_PG_N_D(
							session, companyId, parentGroupId,
							parentGroupIdComparator, names, descriptions,
							params3, andOperator));
				}

				if (params4.containsKey("classNameIds")) {
					groupIds.addAll(
						countByC_PG_N_D(
							session, companyId, parentGroupId,
							parentGroupIdComparator, names, descriptions,
							params4, andOperator));
				}
			}

			return groupIds.size();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Deprecated
	@Override
	public List<Group> findByLayouts(
		long companyId, long parentGroupId, boolean site, int start, int end) {

		return findByLayouts(companyId, parentGroupId, site, start, end, null);
	}

	@Override
	public List<Group> findByLayouts(
		long companyId, long parentGroupId, boolean site, int start, int end,
		OrderByComparator<Group> obc) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_LAYOUTS);

			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Group_", GroupImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(parentGroupId);
			qPos.add(site);

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
	public List<Group> findByLiveGroups() {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_LIVE_GROUPS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Group_", GroupImpl.class);

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
	public List<Group> findByNoLayouts(
		long classNameId, boolean privateLayout, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_NO_LAYOUTS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Group_", GroupImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(classNameId);
			qPos.add(privateLayout);

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
	public List<Group> findByNullFriendlyURL() {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_NULL_FRIENDLY_URL);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Group_", GroupImpl.class);

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
	public List<Group> findBySystem(long companyId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_SYSTEM);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Group_", GroupImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

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
	public List<Group> findByCompanyId(
		long companyId, LinkedHashMap<String, Object> params, int start,
		int end, OrderByComparator<Group> obc) {

		if (params == null) {
			params = _emptyLinkedHashMap;
		}

		LinkedHashMap<String, Object> params1 = params;

		LinkedHashMap<String, Object> params2 = null;

		LinkedHashMap<String, Object> params3 = null;

		Long userId = (Long)params.get("usersGroups");
		boolean inherit = GetterUtil.getBoolean(params.get("inherit"), true);

		boolean doUnion = false;

		if (Validator.isNotNull(userId) && inherit) {
			doUnion = true;
		}

		long[] groupOrganizationClassNameIds =
			_getGroupOrganizationClassNameIds();

		if (doUnion) {
			params2 = new LinkedHashMap<>(params1);

			params2.remove("usersGroups");
			params2.put("groupOrg", userId);

			params3 = new LinkedHashMap<>(params1);

			params3.remove("usersGroups");
			params3.put("groupsOrgs", userId);
			params2.put("classNameIds", groupOrganizationClassNameIds[1]);
			params3.put("classNameIds", groupOrganizationClassNameIds[0]);
		}

		params1.put("classNameIds", _getGroupOrganizationClassNameIds());

		String sqlKey = _buildSQLCacheKey(obc, params1, params2, params3);

		String sql = _findByCompanyIdSQLCache.get(sqlKey);

		if (sql == null) {
			String findByCompanyIdSQL = CustomSQLUtil.get(FIND_BY_COMPANY_ID);

			if (params.get("active") == Boolean.TRUE) {
				findByCompanyIdSQL = StringUtil.replace(
					findByCompanyIdSQL, "(Group_.liveGroupId = 0) AND",
					StringPool.BLANK);
			}

			findByCompanyIdSQL = replaceOrderBy(findByCompanyIdSQL, obc);

			StringBundler sb = new StringBundler(9);

			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(replaceJoinAndWhere(findByCompanyIdSQL, params1));

			if (doUnion) {
				sb.append(") UNION (");
				sb.append(replaceJoinAndWhere(findByCompanyIdSQL, params2));
				sb.append(") UNION (");
				sb.append(replaceJoinAndWhere(findByCompanyIdSQL, params3));
			}

			sb.append(StringPool.CLOSE_PARENTHESIS);

			if (obc != null) {
				sb.append(" ORDER BY ");
				sb.append(obc.toString());
			}

			sql = sb.toString();

			_findByCompanyIdSQLCache.put(sqlKey, sql);
		}

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("groupId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params1);

			qPos.add(companyId);

			if (doUnion) {
				setJoin(qPos, params2);

				qPos.add(companyId);

				setJoin(qPos, params3);

				qPos.add(companyId);
			}

			List<Long> groupIds = (List<Long>)QueryUtil.list(
				q, getDialect(), start, end);

			List<Group> groups = new ArrayList<>(groupIds.size());

			for (Long groupId : groupIds) {
				Group group = GroupUtil.findByPrimaryKey(groupId);

				groups.add(group);
			}

			return groups;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<Long> findByC_P(
		long companyId, long parentGroupId, long previousGroupId, int size) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_P);

			if (previousGroupId <= 0) {
				sql = StringUtil.replace(
					sql, "(groupId > ?) AND", StringPool.BLANK);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("groupId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (previousGroupId > 0) {
				qPos.add(previousGroupId);
			}

			qPos.add(companyId);
			qPos.add(parentGroupId);

			return (List<Long>)QueryUtil.list(q, getDialect(), 0, size);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public Group findByC_GK(long companyId, String groupKey)
		throws NoSuchGroupException {

		groupKey = StringUtil.lowerCase(groupKey);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_GK);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Group_", GroupImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupKey);

			List<Group> groups = q.list();

			if (!groups.isEmpty()) {
				return groups.get(0);
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}

		StringBundler sb = new StringBundler(5);

		sb.append("No Group exists with the key {companyId=");
		sb.append(companyId);
		sb.append(", groupKey=");
		sb.append(groupKey);
		sb.append("}");

		throw new NoSuchGroupException(sb.toString());
	}

	@Override
	public List<Group> findByL_TS_S_RSGC(
		long liveGroupId, String typeSettings, boolean site,
		int remoteStagingGroupCount) {

		String sql = CustomSQLUtil.get(FIND_BY_L_TS_S_RSGC);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Group_", GroupImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(liveGroupId);
			qPos.add(StringUtil.quote(typeSettings, StringPool.PERCENT));
			qPos.add(site);
			qPos.add(remoteStagingGroupCount);

			List<Group> groups = (List<Group>)QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			return groups;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<Group> findByC_C_PG_N_D(
		long companyId, long[] classNameIds, long parentGroupId, String[] names,
		String[] descriptions, LinkedHashMap<String, Object> params,
		boolean andOperator, int start, int end, OrderByComparator<Group> obc) {

		String parentGroupIdComparator = StringPool.EQUAL;

		if (parentGroupId == GroupConstants.ANY_PARENT_GROUP_ID) {
			parentGroupIdComparator = StringPool.NOT_EQUAL;
		}

		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions);

		if (params == null) {
			params = _emptyLinkedHashMap;
		}

		LinkedHashMap<String, Object> params1 = params;

		LinkedHashMap<String, Object> params2 = null;

		LinkedHashMap<String, Object> params3 = null;

		LinkedHashMap<String, Object> params4 = null;

		Long userId = (Long)params.get("usersGroups");
		boolean inherit = GetterUtil.getBoolean(params.get("inherit"), true);

		boolean doUnion = false;

		if (Validator.isNotNull(userId) && inherit) {
			doUnion = true;
		}

		if (doUnion) {
			params2 = new LinkedHashMap<>(params1);
			params3 = new LinkedHashMap<>(params1);
			params4 = new LinkedHashMap<>(params1);

			_populateUnionParams(
				userId, classNameIds, params1, params2, params3, params4);
		}
		else if (classNameIds != null) {
			params1.put("classNameIds", classNameIds);
		}

		if (obc == null) {
			obc = new GroupNameComparator(true);
		}

		String sql = null;
		String sqlKey = null;

		if (_isCacheableSQL(classNameIds)) {
			sqlKey = _buildSQLCacheKey(obc, params1, params2, params3, params4);

			sql = _findByC_C_PG_N_DSQLCache.get(sqlKey);
		}

		if (sql == null) {
			String findByC_PG_N_D_SQL = CustomSQLUtil.get(FIND_BY_C_PG_N_D);

			findByC_PG_N_D_SQL = replaceOrderBy(findByC_PG_N_D_SQL, obc);

			StringBundler sb = new StringBundler(10);

			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(replaceJoinAndWhere(findByC_PG_N_D_SQL, params1));

			if (doUnion) {
				if (params2.containsKey("classNameIds")) {
					sb.append(") UNION (");
					sb.append(replaceJoinAndWhere(findByC_PG_N_D_SQL, params2));
				}

				if (params3.containsKey("classNameIds")) {
					sb.append(") UNION (");
					sb.append(replaceJoinAndWhere(findByC_PG_N_D_SQL, params3));
				}

				if (params4.containsKey("classNameIds")) {
					sb.append(") UNION (");
					sb.append(replaceJoinAndWhere(findByC_PG_N_D_SQL, params4));
				}
			}

			sb.append(") ORDER BY ");
			sb.append(obc.toString());

			sql = sb.toString();

			if (sqlKey != null) {
				_findByC_C_PG_N_DSQLCache.put(sqlKey, sql);
			}
		}

		sql = StringUtil.replace(
			sql, "[$PARENT_GROUP_ID_COMPARATOR$]",
			parentGroupIdComparator.equals(StringPool.EQUAL) ?
				StringPool.EQUAL : StringPool.NOT_EQUAL);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Group_.name)", StringPool.LIKE, false, names);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Group_.description)", StringPool.LIKE, true,
			descriptions);
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("groupId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params1);

			qPos.add(companyId);
			qPos.add(parentGroupId);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);

			if (doUnion) {
				setJoin(qPos, params2);

				qPos.add(companyId);
				qPos.add(parentGroupId);
				qPos.add(names, 2);
				qPos.add(descriptions, 2);

				setJoin(qPos, params3);

				qPos.add(companyId);
				qPos.add(parentGroupId);
				qPos.add(names, 2);
				qPos.add(descriptions, 2);

				setJoin(qPos, params4);

				qPos.add(companyId);
				qPos.add(parentGroupId);
				qPos.add(names, 2);
				qPos.add(descriptions, 2);
			}

			List<Long> groupIds = (List<Long>)QueryUtil.list(
				q, getDialect(), start, end);

			List<Group> groups = new ArrayList<>(groupIds.size());

			for (Long groupId : groupIds) {
				Group group = GroupUtil.findByPrimaryKey(groupId);

				groups.add(group);
			}

			return groups;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected int countByGroupId(
			Session session, long groupId, Map<String, Object> params)
		throws Exception {

		String sql = CustomSQLUtil.get(COUNT_BY_GROUP_ID);

		sql = replaceJoinAndWhere(sql, params);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

		QueryPos qPos = QueryPos.getInstance(q);

		setJoin(qPos, params);

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

	protected List<Long> countByC_PG_N_D(
			Session session, long companyId, long parentGroupId,
			String parentGroupIdComparator, String[] names,
			String[] descriptions, Map<String, Object> params,
			boolean andOperator)
		throws Exception {

		String sql = CustomSQLUtil.get(COUNT_BY_C_PG_N_D);

		sql = StringUtil.replace(
			sql, "[$PARENT_GROUP_ID_COMPARATOR$]",
			parentGroupIdComparator.equals(StringPool.EQUAL) ?
				StringPool.EQUAL : StringPool.NOT_EQUAL);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Group_.name)", StringPool.LIKE, false, names);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Group_.description)", StringPool.LIKE, true,
			descriptions);

		sql = replaceJoinAndWhere(sql, params);
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.addScalar("groupId", Type.LONG);

		QueryPos qPos = QueryPos.getInstance(q);

		setJoin(qPos, params);

		qPos.add(companyId);
		qPos.add(parentGroupId);
		qPos.add(names, 2);
		qPos.add(descriptions, 2);

		return q.list(true);
	}

	protected String getJoin(Map<String, Object> params) {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (Validator.isNull(value)) {
				continue;
			}

			if (key.equals("rolePermissions")) {
				RolePermissions rolePermissions = (RolePermissions)value;

				if (ResourceBlockLocalServiceUtil.isSupported(
						rolePermissions.getName())) {

					key = "rolePermissions_6_block";
				}
				else {
					key = "rolePermissions_6";
				}
			}

			Map<String, String> joinMap = _getJoinMap();

			String joinValue = joinMap.get(key);

			if (Validator.isNotNull(joinValue)) {
				sb.append(joinValue);
			}
		}

		return sb.toString();
	}

	protected String getWhere(Map<String, Object> params) {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();

			if (key.equals("classNameIds")) {
				if (entry.getValue() instanceof Long) {
					sb.append("(Group_.classNameId = ?) AND ");
				}
				else {
					sb.append(StringPool.OPEN_PARENTHESIS);

					long[] classNameIds = (long[])entry.getValue();

					for (int i = 0; i < classNameIds.length; i++) {
						sb.append("(Group_.classNameId = ?) OR ");
					}

					sb.setStringAt(
						"(Group_.classNameId = ?)) AND ", sb.index() - 1);
				}
			}
			else if (key.equals("excludedGroupIds")) {
				List<Long> excludedGroupIds = (List<Long>)entry.getValue();

				if (!excludedGroupIds.isEmpty()) {
					sb.append(StringPool.OPEN_PARENTHESIS);

					for (int i = 0; i < excludedGroupIds.size(); i++) {
						sb.append("(Group_.groupId != ?) AND ");
					}

					sb.setStringAt(
						"(Group_.groupId != ?)) AND ", sb.index() - 1);
				}
			}
			else if (key.equals("groupsTree")) {
				List<Group> groupsTree = (List<Group>)entry.getValue();

				if (!groupsTree.isEmpty()) {
					sb.append(StringPool.OPEN_PARENTHESIS);

					for (int i = 0; i < groupsTree.size(); i++) {
						sb.append("(Group_.treePath LIKE ?) OR ");
					}

					sb.setStringAt(
						"(Group_.treePath LIKE ?)) AND ", sb.index() - 1);
				}
			}
			else if (key.equals("types")) {
				List<Integer> types = (List<Integer>)entry.getValue();

				if (!types.isEmpty()) {
					sb.append(StringPool.OPEN_PARENTHESIS);

					for (int i = 0; i < types.size(); i++) {
						sb.append("(Group_.type_ = ?) OR ");
					}

					sb.setStringAt("(Group_.type_ = ?)) AND ", sb.index() - 1);
				}
			}
			else {
				if (key.equals("rolePermissions")) {
					RolePermissions rolePermissions =
						(RolePermissions)entry.getValue();

					if (ResourceBlockLocalServiceUtil.isSupported(
							rolePermissions.getName())) {

						key = "rolePermissions_6_block";
					}
					else {
						key = "rolePermissions_6";
					}
				}

				Map<String, String> whereMap = _getWhereMap();

				String whereValue = whereMap.get(key);

				if (Validator.isNotNull(whereValue)) {
					sb.append(whereValue);
				}
			}
		}

		return sb.toString();
	}

	protected String replaceJoinAndWhere(
		String sql, Map<String, Object> params) {

		if (params.isEmpty()) {
			return StringUtil.removeSubstrings(sql, "[$JOIN$]", "[$WHERE$]");
		}

		String cacheKey = _buildSQLCacheKey(sql, params);

		String resultSQL = _replaceJoinAndWhereSQLCache.get(cacheKey);

		if (resultSQL == null) {
			sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));

			resultSQL = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));

			_replaceJoinAndWhereSQLCache.put(cacheKey, resultSQL);
		}

		return resultSQL;
	}

	protected String replaceOrderBy(String sql, OrderByComparator<Group> obc) {
		if (obc instanceof GroupNameComparator) {
			sql = StringUtil.replace(
				sql, "Group_.name AS groupName",
				"REPLACE(Group_.name, '" +
					GroupLocalServiceImpl.ORGANIZATION_NAME_SUFFIX +
						"', '') AS groupName");
		}

		return sql;
	}

	protected void setJoin(QueryPos qPos, Map<String, Object> params)
		throws Exception {

		if (params == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();

			if (key.equals("active") || key.equals("layoutSet") ||
				key.equals("manualMembership") || key.equals("site")) {

				Boolean value = (Boolean)entry.getValue();

				qPos.add(value);
			}
			else if (key.equals("classNameIds")) {
				if (entry.getValue() instanceof Long) {
					qPos.add((long)entry.getValue());
				}
				else {
					for (long classNameId : (long[])entry.getValue()) {
						qPos.add(classNameId);
					}
				}
			}
			else if (key.equals("excludedGroupIds")) {
				List<Long> excludedGroupIds = (List<Long>)entry.getValue();

				if (!excludedGroupIds.isEmpty()) {
					for (long excludedGroupId : excludedGroupIds) {
						qPos.add(excludedGroupId);
					}
				}
			}
			else if (key.equals("groupsTree")) {
				List<Group> groupsTree = (List<Group>)entry.getValue();

				if (!groupsTree.isEmpty()) {
					for (Group group : groupsTree) {
						StringBundler sb = new StringBundler(5);

						sb.append(StringPool.PERCENT);
						sb.append(StringPool.SLASH);
						sb.append(group.getGroupId());
						sb.append(StringPool.SLASH);
						sb.append(StringPool.PERCENT);

						qPos.add(sb.toString());
					}
				}
			}
			else if (key.equals("pageCount")) {
			}
			else if (key.equals("rolePermissions")) {
				RolePermissions rolePermissions =
					(RolePermissions)entry.getValue();

				ResourceAction resourceAction =
					ResourceActionLocalServiceUtil.getResourceAction(
						rolePermissions.getName(),
						rolePermissions.getActionId());

				if (ResourceBlockLocalServiceUtil.isSupported(
						rolePermissions.getName())) {

					// Scope is assumed to always be group

					qPos.add(rolePermissions.getName());
					qPos.add(rolePermissions.getRoleId());
					qPos.add(resourceAction.getBitwiseValue());
				}
				else {
					qPos.add(rolePermissions.getName());
					qPos.add(rolePermissions.getScope());
					qPos.add(rolePermissions.getRoleId());
					qPos.add(resourceAction.getBitwiseValue());
				}
			}
			else if (key.equals("types")) {
				List<Integer> values = (List<Integer>)entry.getValue();

				for (int i = 0; i < values.size(); i++) {
					Integer value = values.get(i);

					qPos.add(value);
				}
			}
			else if (key.equals("userGroupRole")) {
				List<Long> values = (List<Long>)entry.getValue();

				Long userId = values.get(0);
				Long roleId = values.get(1);

				qPos.add(userId);
				qPos.add(roleId);
			}
			else {
				Object value = entry.getValue();

				if (value instanceof Integer) {
					Integer valueInteger = (Integer)value;

					if (Validator.isNotNull(valueInteger)) {
						qPos.add(valueInteger);
					}
				}
				else if (value instanceof Long) {
					Long valueLong = (Long)value;

					if (Validator.isNotNull(valueLong)) {
						qPos.add(valueLong);
					}
				}
				else if (value instanceof String) {
					String valueString = (String)value;

					if (Validator.isNotNull(valueString)) {
						qPos.add(valueString);
					}
				}
			}
		}
	}

	@SafeVarargs
	private final String _buildSQLCacheKey(
		OrderByComparator<Group> obc, Map<String, Object>... params) {

		if (obc == null) {
			return _buildSQLCacheKey(StringPool.BLANK, params);
		}

		return _buildSQLCacheKey(obc.getOrderBy(), params);
	}

	@SafeVarargs
	private final String _buildSQLCacheKey(
		String sql, Map<String, Object>... params) {

		int size = 1;

		for (Map<String, Object> param : params) {
			if (param != null) {
				size += param.size() * 5;
			}
		}

		StringBundler sb = new StringBundler(size);

		sb.append(sql);

		for (Map<String, Object> param : params) {
			if (param == null) {
				continue;
			}

			for (Map.Entry<String, Object> entry : param.entrySet()) {
				sb.append(StringPool.COMMA);

				String key = entry.getKey();

				if (key.equals("rolePermissions")) {
					RolePermissions rolePermissions =
						(RolePermissions)entry.getValue();

					if (ResourceBlockLocalServiceUtil.isSupported(
							rolePermissions.getName())) {

						key = "rolePermissions_6_block";
					}
					else {
						key = "rolePermissions_6";
					}
				}

				sb.append(key);
				sb.append(StringPool.DASH);

				Object value = entry.getValue();

				if (value instanceof long[]) {
					long[] values = (long[])value;

					sb.append(values.length);
				}
				else if (value instanceof Collection<?>) {
					Collection<?> values = (Collection<?>)value;

					sb.append(values.size());
				}

				sb.append(StringPool.COMMA);
			}
		}

		return sb.toString();
	}

	private String _getCondition(String join) {
		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(pos + 5, join.length()).concat(" AND ");
			}
			else {
				join = StringPool.BLANK;
			}
		}

		return join;
	}

	private long[] _getGroupOrganizationClassNameIds() {
		if (_groupOrganizationClassNameIds == null) {
			_groupOrganizationClassNameIds = new long[] {
				ClassNameLocalServiceUtil.getClassNameId(Group.class),
				ClassNameLocalServiceUtil.getClassNameId(Organization.class)
			};
		}

		return _groupOrganizationClassNameIds;
	}

	private Map<String, String> _getJoinMap() {
		if (_joinMap != null) {
			return _joinMap;
		}

		Map<String, String> joinMap = new HashMap<>();

		joinMap.put("active", _removeWhere(CustomSQLUtil.get(JOIN_BY_ACTIVE)));
		joinMap.put(
			"groupOrg", _removeWhere(CustomSQLUtil.get(JOIN_BY_GROUP_ORG)));
		joinMap.put(
			"groupsOrgs", _removeWhere(CustomSQLUtil.get(JOIN_BY_GROUPS_ORGS)));
		joinMap.put(
			"groupsRoles",
			_removeWhere(CustomSQLUtil.get(JOIN_BY_GROUPS_ROLES)));
		joinMap.put(
			"groupsUserGroups",
			_removeWhere(CustomSQLUtil.get(JOIN_BY_GROUPS_USER_GROUPS)));
		joinMap.put(
			"layoutSet", _removeWhere(CustomSQLUtil.get(JOIN_BY_LAYOUT_SET)));
		joinMap.put(
			"pageCount", _removeWhere(CustomSQLUtil.get(JOIN_BY_PAGE_COUNT)));
		joinMap.put(
			"membershipRestriction",
			_removeWhere(CustomSQLUtil.get(JOIN_BY_MEMBERSHIP_RESTRICTION)));
		joinMap.put(
			"rolePermissions_6",
			_removeWhere(CustomSQLUtil.get(JOIN_BY_ROLE_RESOURCE_PERMISSIONS)));
		joinMap.put(
			"rolePermissions_6_block",
			_removeWhere(
				CustomSQLUtil.get(JOIN_BY_ROLE_RESOURCE_TYPE_PERMISSIONS)));
		joinMap.put("site", _removeWhere(CustomSQLUtil.get(JOIN_BY_SITE)));
		joinMap.put("type", _removeWhere(CustomSQLUtil.get(JOIN_BY_TYPE)));
		joinMap.put(
			"userGroupRole",
			_removeWhere(CustomSQLUtil.get(JOIN_BY_USER_GROUP_ROLE)));
		joinMap.put(
			"usersGroups",
			_removeWhere(CustomSQLUtil.get(JOIN_BY_USERS_GROUPS)));

		_joinMap = joinMap;

		return _joinMap;
	}

	private long _getUserGroupClassNameId() {
		if (_userGroupClassNameId == null) {
			_userGroupClassNameId = ClassNameLocalServiceUtil.getClassNameId(
				UserGroup.class);
		}

		return _userGroupClassNameId;
	}

	private Map<String, String> _getWhereMap() {
		if (_whereMap != null) {
			return _whereMap;
		}

		Map<String, String> whereMap = new HashMap<>();

		whereMap.put(
			"active", _getCondition(CustomSQLUtil.get(JOIN_BY_ACTIVE)));
		whereMap.put(
			"creatorUserId",
			_getCondition(CustomSQLUtil.get(JOIN_BY_CREATOR_USER_ID)));
		whereMap.put(
			"groupOrg", _getCondition(CustomSQLUtil.get(JOIN_BY_GROUP_ORG)));
		whereMap.put(
			"groupsOrgs",
			_getCondition(CustomSQLUtil.get(JOIN_BY_GROUPS_ORGS)));
		whereMap.put(
			"groupsRoles",
			_getCondition(CustomSQLUtil.get(JOIN_BY_GROUPS_ROLES)));
		whereMap.put(
			"groupsUserGroups",
			_getCondition(CustomSQLUtil.get(JOIN_BY_GROUPS_USER_GROUPS)));
		whereMap.put(
			"layoutSet", _getCondition(CustomSQLUtil.get(JOIN_BY_LAYOUT_SET)));
		whereMap.put(
			"manualMembership",
			_getCondition(CustomSQLUtil.get(JOIN_BY_MANUAL_MEMBERSHIP)));
		whereMap.put(
			"membershipRestriction",
			_getCondition(CustomSQLUtil.get(JOIN_BY_MEMBERSHIP_RESTRICTION)));
		whereMap.put(
			"pageCount", _getCondition(CustomSQLUtil.get(JOIN_BY_PAGE_COUNT)));
		whereMap.put(
			"rolePermissions_6",
			_getCondition(
				CustomSQLUtil.get(JOIN_BY_ROLE_RESOURCE_PERMISSIONS)));
		whereMap.put(
			"rolePermissions_6_block",
			_getCondition(
				CustomSQLUtil.get(JOIN_BY_ROLE_RESOURCE_TYPE_PERMISSIONS)));
		whereMap.put("site", _getCondition(CustomSQLUtil.get(JOIN_BY_SITE)));
		whereMap.put("type", _getCondition(CustomSQLUtil.get(JOIN_BY_TYPE)));
		whereMap.put(
			"userGroupRole",
			_getCondition(CustomSQLUtil.get(JOIN_BY_USER_GROUP_ROLE)));
		whereMap.put(
			"usersGroups",
			_getCondition(CustomSQLUtil.get(JOIN_BY_USERS_GROUPS)));

		_whereMap = whereMap;

		return _whereMap;
	}

	private boolean _isCacheableSQL(long[] classNameIds) {
		if (classNameIds == null) {
			return true;
		}

		if (classNameIds.length > 3) {
			return false;
		}

		long[] groupOrganizationClassNameIds =
			_getGroupOrganizationClassNameIds();

		long groupClassNameId = groupOrganizationClassNameIds[0];
		long organizationClassNameId = groupOrganizationClassNameIds[1];

		long userGroupClassNameId = _getUserGroupClassNameId();

		for (long classNameId : classNameIds) {
			if ((classNameId != groupClassNameId) &&
				(classNameId != organizationClassNameId) &&
				(classNameId != userGroupClassNameId)) {

				return false;
			}
		}

		return true;
	}

	private void _populateUnionParams(
		long userId, long[] classNameIds, Map<String, Object> params1,
		Map<String, Object> params2, Map<String, Object> params3,
		Map<String, Object> params4) {

		params2.remove("usersGroups");
		params2.put("groupOrg", userId);

		params3.remove("usersGroups");
		params3.put("groupsOrgs", userId);

		params4.remove("usersGroups");
		params4.put("groupsUserGroups", userId);

		long[] groupOrganizationClassNameIds =
			_getGroupOrganizationClassNameIds();

		long groupClassNameId = groupOrganizationClassNameIds[0];
		long organizationClassNameId = groupOrganizationClassNameIds[1];
		long userGroupClassNameId = _getUserGroupClassNameId();

		if (classNameIds == null) {
			params1.put("classNameIds", groupOrganizationClassNameIds);
			params2.put("classNameIds", organizationClassNameId);
			params3.put("classNameIds", groupClassNameId);
			params4.put("classNameIds", userGroupClassNameId);
		}
		else {
			params1.put("classNameIds", classNameIds);

			if (ArrayUtil.contains(classNameIds, organizationClassNameId)) {
				params2.put("classNameIds", organizationClassNameId);
			}

			if (ArrayUtil.contains(classNameIds, groupClassNameId)) {
				params3.put("classNameIds", groupClassNameId);
			}

			if (ArrayUtil.contains(classNameIds, userGroupClassNameId)) {
				params4.put("classNameIds", userGroupClassNameId);
			}
		}
	}

	private String _removeWhere(String join) {
		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(0, pos);
			}
		}

		return join;
	}

	private final LinkedHashMap<String, Object> _emptyLinkedHashMap =
		new LinkedHashMap<>(0);
	private final Map<String, String> _findByC_C_PG_N_DSQLCache =
		new ConcurrentHashMap<>();
	private final Map<String, String> _findByCompanyIdSQLCache =
		new ConcurrentHashMap<>();
	private volatile long[] _groupOrganizationClassNameIds;
	private volatile Map<String, String> _joinMap;
	private final Map<String, String> _replaceJoinAndWhereSQLCache =
		new ConcurrentHashMap<>();
	private volatile Long _userGroupClassNameId;
	private volatile Map<String, String> _whereMap;

}