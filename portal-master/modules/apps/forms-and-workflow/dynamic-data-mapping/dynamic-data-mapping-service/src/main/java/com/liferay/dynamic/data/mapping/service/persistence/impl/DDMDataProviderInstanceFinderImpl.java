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

package com.liferay.dynamic.data.mapping.service.persistence.impl;

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance;
import com.liferay.dynamic.data.mapping.model.impl.DDMDataProviderInstanceImpl;
import com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstanceFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;
import java.util.List;

/**
 * @author Leonardo Barros
 */
public class DDMDataProviderInstanceFinderImpl
	extends DDMDataProviderInstanceFinderBaseImpl
	implements DDMDataProviderInstanceFinder {

	public static final String COUNT_BY_C_G_N_D =
		DDMDataProviderInstanceFinder.class.getName() + ".countByC_G_N_D";

	public static final String FIND_BY_C_G_N_D =
		DDMDataProviderInstanceFinder.class.getName() + ".findByC_G_N_D";

	@Override
	public int countByKeywords(
		long companyId, long[] groupIds, String keywords) {

		return doCountByKeywords(companyId, groupIds, keywords, false);
	}

	@Override
	public int filterCountByC_G_N_D(
		long companyId, long[] groupIds, String name, String description,
		boolean andOperator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return doCountByC_G_N_D(
			companyId, groupIds, names, descriptions, andOperator, true);
	}

	@Override
	public int countByC_G_N_D(
		long companyId, long[] groupIds, String name, String description,
		boolean andOperator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return doCountByC_G_N_D(
			companyId, groupIds, names, descriptions, andOperator, false);
	}

	@Override
	public int filterCountByKeywords(
		long companyId, long[] groupIds, String keywords) {

		return doCountByKeywords(companyId, groupIds, keywords, true);
	}

	@Override
	public List<DDMDataProviderInstance> filterByKeywords(
		long companyId, long[] groupIds, String keywords, int start, int end,
		OrderByComparator<DDMDataProviderInstance> orderByComparator) {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return doFindByC_G_N_D(
			companyId, groupIds, names, descriptions, andOperator, start, end,
			orderByComparator, true);
	}

	@Override
	public List<DDMDataProviderInstance> findByKeywords(
		long companyId, long[] groupIds, String keywords, int start, int end,
		OrderByComparator<DDMDataProviderInstance> orderByComparator) {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return doFindByC_G_N_D(
			companyId, groupIds, names, descriptions, andOperator, start, end,
			orderByComparator, false);
	}

	@Override
	public List<DDMDataProviderInstance> filterFindByC_G_N_D(
		long companyId, long[] groupIds, String name, String description,
		boolean andOperator, int start, int end,
		OrderByComparator<DDMDataProviderInstance> orderByComparator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return doFindByC_G_N_D(
			companyId, groupIds, names, descriptions, andOperator, start, end,
			orderByComparator, true);
	}

	@Override
	public List<DDMDataProviderInstance> findByC_G_N_D(
		long companyId, long[] groupIds, String name, String description,
		boolean andOperator, int start, int end,
		OrderByComparator<DDMDataProviderInstance> orderByComparator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return doFindByC_G_N_D(
			companyId, groupIds, names, descriptions, andOperator, start, end,
			orderByComparator, false);
	}

	protected int doCountByKeywords(
		long companyId, long[] groupIds, String keywords,
		boolean inlineSQLHelper) {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return doCountByC_G_N_D(
			companyId, groupIds, names, descriptions, andOperator,
			inlineSQLHelper);
	}

	protected List<DDMDataProviderInstance> doFindByC_G_N_D(
		long companyId, long[] groupIds, String[] names, String[] descriptions,
		boolean andOperator, int start, int end,
		OrderByComparator<DDMDataProviderInstance> orderByComparator,
		boolean inlineSQLHelper) {

		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_C_G_N_D);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, DDMDataProviderInstance.class.getName(),
					"DDMDataProviderInstance.dataProviderInstanceId", groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(DDMDataProviderInstance.name)", StringPool.LIKE,
				false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "DDMDataProviderInstance.description", StringPool.LIKE,
				true, descriptions);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				"DDMDataProviderInstance", DDMDataProviderInstanceImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupIds != null) {
				qPos.add(groupIds);
			}

			qPos.add(names, 2);
			qPos.add(descriptions, 2);

			return (List<DDMDataProviderInstance>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected int doCountByC_G_N_D(
		long companyId, long[] groupIds, String[] names, String[] descriptions,
		boolean andOperator, boolean inlineSQLHelper) {

		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), COUNT_BY_C_G_N_D);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, DDMDataProviderInstance.class.getName(),
					"DDMDataProviderInstance.dataProviderInstanceId", groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(DDMDataProviderInstance.name)", StringPool.LIKE,
				false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "DDMDataProviderInstance.description", StringPool.LIKE,
				true, descriptions);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupIds != null) {
				qPos.add(groupIds);
			}

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

	protected String getGroupIds(long[] groupIds) {
		if (ArrayUtil.isEmpty(groupIds)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(groupIds.length + 1);

		sb.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < groupIds.length - 1; i++) {
			sb.append("DDMDataProviderInstance.groupId = ? OR ");
		}

		sb.append("DDMDataProviderInstance.groupId = ?) AND");

		return sb.toString();
	}

}