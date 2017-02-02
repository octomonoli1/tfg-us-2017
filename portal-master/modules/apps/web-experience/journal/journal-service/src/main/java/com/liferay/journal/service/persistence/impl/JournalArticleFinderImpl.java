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

import com.liferay.journal.configuration.JournalServiceConfigurationValues;
import com.liferay.journal.exception.NoSuchArticleException;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.impl.JournalArticleImpl;
import com.liferay.journal.service.persistence.JournalArticleFinder;
import com.liferay.journal.service.persistence.JournalArticleUtil;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Connor McKay
 */
public class JournalArticleFinderImpl
	extends JournalArticleFinderBaseImpl implements JournalArticleFinder {

	public static final String COUNT_BY_G_F =
		JournalArticleFinder.class.getName() + ".countByG_F";

	public static final String COUNT_BY_G_C_S =
		JournalArticleFinder.class.getName() + ".countByG_C_S";

	public static final String COUNT_BY_G_U_F_C =
		JournalArticleFinder.class.getName() + ".countByG_U_F_C";

	public static final String COUNT_BY_C_G_F_C_A_V_T_D_C_S_T_D_R =
		JournalArticleFinder.class.getName() +
			".countByC_G_F_C_A_V_T_D_C_S_T_D_R";

	public static final String FIND_BY_EXPIRATION_DATE =
		JournalArticleFinder.class.getName() + ".findByExpirationDate";

	public static final String FIND_BY_NO_ASSETS =
		JournalArticleFinder.class.getName() + ".findByNoAssets";

	public static final String FIND_BY_NO_PERMISSIONS =
		JournalArticleFinder.class.getName() + ".findByNoPermissions";

	public static final String FIND_BY_REVIEW_DATE =
		JournalArticleFinder.class.getName() + ".findByReviewDate";

	public static final String FIND_BY_R_D =
		JournalArticleFinder.class.getName() + ".findByR_D";

	public static final String FIND_BY_G_F =
		JournalArticleFinder.class.getName() + ".findByG_F";

	public static final String FIND_BY_G_C_S =
		JournalArticleFinder.class.getName() + ".findByG_C_S";

	public static final String FIND_BY_G_U_F_C =
		JournalArticleFinder.class.getName() + ".findByG_U_F_C";

	public static final String FIND_BY_C_G_F_C_A_V_T_D_C_S_T_D_R =
		JournalArticleFinder.class.getName() +
			".findByC_G_F_C_A_V_T_D_C_S_T_D_R";

	@Override
	public int countByKeywords(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String keywords, Double version, String ddmStructureKey,
		String ddmTemplateKey, Date displayDateGT, Date displayDateLT,
		int status, Date reviewDate) {

		String[] articleIds = null;
		String[] titles = null;
		String[] descriptions = null;
		String[] contents = null;
		String[] ddmStructureKeys = CustomSQLUtil.keywords(
			ddmStructureKey, false);
		String[] ddmTemplateKeys = CustomSQLUtil.keywords(
			ddmTemplateKey, false);
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			articleIds = CustomSQLUtil.keywords(keywords, false);
			titles = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);

			if (JournalServiceConfigurationValues.
					JOURNAL_ARTICLE_DATABASE_KEYWORD_SEARCH_CONTENT) {

				contents = CustomSQLUtil.keywords(keywords, false);
			}
		}
		else {
			andOperator = true;
		}

		QueryDefinition<JournalArticle> queryDefinition = new QueryDefinition<>(
			status);

		return doCountByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleIds, version,
			titles, descriptions, contents, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition, false);
	}

	@Override
	public int countByG_F(
		long groupId, List<Long> folderIds,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doCountByG_F(groupId, folderIds, queryDefinition, false);
	}

	@Override
	public int countByG_F_C(
		long groupId, List<Long> folderIds, long classNameId,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doCountByG_F_C(
			groupId, folderIds, classNameId, queryDefinition, false);
	}

	@Override
	public int countByG_C_S(
		long groupId, long classNameId, String ddmStructureKey,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doCountByG_C_S(
			groupId, classNameId, new String[] {ddmStructureKey},
			queryDefinition, false);
	}

	@Override
	public int countByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String articleId, Double version, String title, String description,
		String content, String ddmStructureKey, String ddmTemplateKey,
		Date displayDateGT, Date displayDateLT, Date reviewDate,
		boolean andOperator, QueryDefinition<JournalArticle> queryDefinition) {

		String[] ddmStructureKeys = CustomSQLUtil.keywords(
			ddmStructureKey, false);
		String[] ddmTemplateKeys = CustomSQLUtil.keywords(
			ddmTemplateKey, false);

		return countByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleId, version,
			title, description, content, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition);
	}

	@Override
	public int countByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String articleId, Double version, String title, String description,
		String content, String[] ddmStructureKeys, String[] ddmTemplateKeys,
		Date displayDateGT, Date displayDateLT, Date reviewDate,
		boolean andOperator, QueryDefinition<JournalArticle> queryDefinition) {

		String[] articleIds = CustomSQLUtil.keywords(articleId, false);
		String[] titles = CustomSQLUtil.keywords(title);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] contents = CustomSQLUtil.keywords(content, false);

		return countByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleIds, version,
			titles, descriptions, contents, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition);
	}

	@Override
	public int countByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String[] articleIds, Double version, String[] titles,
		String[] descriptions, String[] contents, String[] ddmStructureKeys,
		String[] ddmTemplateKeys, Date displayDateGT, Date displayDateLT,
		Date reviewDate, boolean andOperator,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doCountByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleIds, version,
			titles, descriptions, contents, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition, false);
	}

	@Override
	public int filterCountByKeywords(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String keywords, Double version, String ddmStructureKey,
		String ddmTemplateKey, Date displayDateGT, Date displayDateLT,
		int status, Date reviewDate) {

		String[] articleIds = null;
		String[] titles = null;
		String[] descriptions = null;
		String[] contents = null;
		String[] ddmStructureKeys = CustomSQLUtil.keywords(
			ddmStructureKey, false);
		String[] ddmTemplateKeys = CustomSQLUtil.keywords(
			ddmTemplateKey, false);
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			articleIds = CustomSQLUtil.keywords(keywords, false);
			titles = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);

			if (JournalServiceConfigurationValues.
					JOURNAL_ARTICLE_DATABASE_KEYWORD_SEARCH_CONTENT) {

				contents = CustomSQLUtil.keywords(keywords, false);
			}
		}
		else {
			andOperator = true;
		}

		QueryDefinition<JournalArticle> queryDefinition = new QueryDefinition<>(
			status);

		return doCountByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleIds, version,
			titles, descriptions, contents, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition, true);
	}

	@Override
	public int filterCountByG_F(
		long groupId, List<Long> folderIds,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doCountByG_F(groupId, folderIds, queryDefinition, true);
	}

	@Override
	public int filterCountByG_F_C(
		long groupId, List<Long> folderIds, long classNameId,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doCountByG_F_C(
			groupId, folderIds, classNameId, queryDefinition, true);
	}

	@Override
	public int filterCountByG_C_S(
		long groupId, long classNameId, String ddmStructureKey,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doCountByG_C_S(
			groupId, classNameId, new String[] {ddmStructureKey},
			queryDefinition, true);
	}

	@Override
	public int filterCountByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String articleId, Double version, String title, String description,
		String content, String ddmStructureKey, String ddmTemplateKey,
		Date displayDateGT, Date displayDateLT, Date reviewDate,
		boolean andOperator, QueryDefinition<JournalArticle> queryDefinition) {

		String[] ddmStructureKeys = CustomSQLUtil.keywords(
			ddmStructureKey, false);
		String[] ddmTemplateKeys = CustomSQLUtil.keywords(
			ddmTemplateKey, false);

		return filterCountByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleId, version,
			title, description, content, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition);
	}

	@Override
	public int filterCountByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String articleId, Double version, String title, String description,
		String content, String[] ddmStructureKeys, String[] ddmTemplateKeys,
		Date displayDateGT, Date displayDateLT, Date reviewDate,
		boolean andOperator, QueryDefinition<JournalArticle> queryDefinition) {

		String[] articleIds = CustomSQLUtil.keywords(articleId, false);
		String[] titles = CustomSQLUtil.keywords(title);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] contents = CustomSQLUtil.keywords(content, false);

		return filterCountByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleIds, version,
			titles, descriptions, contents, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition);
	}

	@Override
	public int filterCountByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String[] articleIds, Double version, String[] titles,
		String[] descriptions, String[] contents, String[] ddmStructureKeys,
		String[] ddmTemplateKeys, Date displayDateGT, Date displayDateLT,
		Date reviewDate, boolean andOperator,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doCountByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleIds, version,
			titles, descriptions, contents, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition, true);
	}

	@Override
	public List<JournalArticle> filterFindByKeywords(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String keywords, Double version, String ddmStructureKey,
		String ddmTemplateKey, Date displayDateGT, Date displayDateLT,
		int status, Date reviewDate, int start, int end,
		OrderByComparator<JournalArticle> orderByComparator) {

		String[] articleIds = null;
		String[] titles = null;
		String[] descriptions = null;
		String[] contents = null;
		String[] ddmStructureKeys = CustomSQLUtil.keywords(
			ddmStructureKey, false);
		String[] ddmTemplateKeys = CustomSQLUtil.keywords(
			ddmTemplateKey, false);
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			articleIds = CustomSQLUtil.keywords(keywords, false);
			titles = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);

			if (JournalServiceConfigurationValues.
					JOURNAL_ARTICLE_DATABASE_KEYWORD_SEARCH_CONTENT) {

				contents = CustomSQLUtil.keywords(keywords, false);
			}
		}
		else {
			andOperator = true;
		}

		QueryDefinition<JournalArticle> queryDefinition = new QueryDefinition<>(
			status, start, end, orderByComparator);

		return doFindByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleIds, version,
			titles, descriptions, contents, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition, true);
	}

	@Override
	public List<JournalArticle> filterFindByG_F(
		long groupId, List<Long> folderIds,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doFindByG_F(groupId, folderIds, queryDefinition, true);
	}

	@Override
	public List<JournalArticle> filterFindByG_F_C(
		long groupId, List<Long> folderIds, long classNameId,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doFindByG_F_C(
			groupId, folderIds, classNameId, queryDefinition, true);
	}

	@Override
	public List<JournalArticle> filterFindByG_C_S(
		long groupId, long classNameId, String ddmStructureKey,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doFindByG_C_S(
			groupId, classNameId, new String[] {ddmStructureKey},
			queryDefinition, true);
	}

	@Override
	public List<JournalArticle> filterFindByG_C_S(
		long groupId, long classNameId, String[] ddmStructureKeys,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doFindByG_C_S(
			groupId, classNameId, ddmStructureKeys, queryDefinition, true);
	}

	@Override
	public List<JournalArticle> filterFindByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String articleId, Double version, String title, String description,
		String content, String ddmStructureKey, String ddmTemplateKey,
		Date displayDateGT, Date displayDateLT, Date reviewDate,
		boolean andOperator, QueryDefinition<JournalArticle> queryDefinition) {

		String[] ddmStructureKeys = CustomSQLUtil.keywords(
			ddmStructureKey, false);
		String[] ddmTemplateKeys = CustomSQLUtil.keywords(
			ddmTemplateKey, false);

		return filterFindByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleId, version,
			title, description, content, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition);
	}

	@Override
	public List<JournalArticle> filterFindByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String articleId, Double version, String title, String description,
		String content, String[] ddmStructureKeys, String[] ddmTemplateKeys,
		Date displayDateGT, Date displayDateLT, Date reviewDate,
		boolean andOperator, QueryDefinition<JournalArticle> queryDefinition) {

		String[] articleIds = CustomSQLUtil.keywords(articleId, false);
		String[] titles = CustomSQLUtil.keywords(title);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] contents = CustomSQLUtil.keywords(content, false);

		return filterFindByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleIds, version,
			titles, descriptions, contents, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition);
	}

	@Override
	public List<JournalArticle> filterFindByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String[] articleIds, Double version, String[] titles,
		String[] descriptions, String[] contents, String[] ddmStructureKeys,
		String[] ddmTemplateKeys, Date displayDateGT, Date displayDateLT,
		Date reviewDate, boolean andOperator,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doFindByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleIds, version,
			titles, descriptions, contents, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition, true);
	}

	@Override
	public List<JournalArticle> findByExpirationDate(
		long classNameId, Date expirationDateLT,
		QueryDefinition<JournalArticle> queryDefinition) {

		Timestamp expirationDateLT_TS = CalendarUtil.getTimestamp(
			expirationDateLT);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), FIND_BY_EXPIRATION_DATE, queryDefinition,
				"JournalArticle");

			sql = replaceStatusJoin(sql, queryDefinition);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				JournalArticleImpl.TABLE_NAME, JournalArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(classNameId);
			qPos.add(queryDefinition.getStatus());
			qPos.add(expirationDateLT_TS);

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
	public List<JournalArticle> findByKeywords(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String keywords, Double version, String ddmStructureKey,
		String ddmTemplateKey, Date displayDateGT, Date displayDateLT,
		int status, Date reviewDate, int start, int end,
		OrderByComparator<JournalArticle> orderByComparator) {

		String[] articleIds = null;
		String[] titles = null;
		String[] descriptions = null;
		String[] contents = null;
		String[] ddmStructureKeys = CustomSQLUtil.keywords(
			ddmStructureKey, false);
		String[] ddmTemplateKeys = CustomSQLUtil.keywords(
			ddmTemplateKey, false);
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			articleIds = CustomSQLUtil.keywords(keywords, false);
			titles = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);

			if (JournalServiceConfigurationValues.
					JOURNAL_ARTICLE_DATABASE_KEYWORD_SEARCH_CONTENT) {

				contents = CustomSQLUtil.keywords(keywords, false);
			}
		}
		else {
			andOperator = true;
		}

		QueryDefinition<JournalArticle> queryDefinition = new QueryDefinition<>(
			status, start, end, orderByComparator);

		return findByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleIds, version,
			titles, descriptions, contents, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition);
	}

	@Override
	public List<JournalArticle> findByNoAssets() {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_NO_ASSETS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("JournalArticle", JournalArticleImpl.class);

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
	public List<JournalArticle> findByNoPermissions() {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_NO_PERMISSIONS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("JournalArticle", JournalArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(ResourceConstants.SCOPE_INDIVIDUAL);

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
	public List<JournalArticle> findByReviewDate(
		long classNameId, Date reviewDateLT, Date reviewDateGT) {

		Timestamp reviewDateLT_TS = CalendarUtil.getTimestamp(reviewDateLT);
		Timestamp reviewDateGT_TS = CalendarUtil.getTimestamp(reviewDateGT);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_REVIEW_DATE);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				JournalArticleImpl.TABLE_NAME, JournalArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(classNameId);
			qPos.add(reviewDateGT_TS);
			qPos.add(reviewDateLT_TS);

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
	public JournalArticle findByR_D(long resourcePrimKey, Date displayDate)
		throws NoSuchArticleException {

		Timestamp displayDate_TS = CalendarUtil.getTimestamp(displayDate);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_R_D);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				JournalArticleImpl.TABLE_NAME, JournalArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);
			qPos.add(displayDate_TS);

			List<JournalArticle> articles = q.list();

			if (!articles.isEmpty()) {
				return articles.get(0);
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}

		StringBundler sb = new StringBundler(5);

		sb.append("No JournalArticle exists with the key {resourcePrimKey=");
		sb.append(resourcePrimKey);
		sb.append(", displayDate=");
		sb.append(displayDate);
		sb.append("}");

		throw new NoSuchArticleException(sb.toString());
	}

	@Override
	public List<JournalArticle> findByG_F(
		long groupId, List<Long> folderIds,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doFindByG_F(groupId, folderIds, queryDefinition, false);
	}

	@Override
	public List<JournalArticle> findByG_F_C(
		long groupId, List<Long> folderIds, long classNameId,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doFindByG_F_C(
			groupId, folderIds, classNameId, queryDefinition, false);
	}

	@Override
	public List<JournalArticle> findByG_C_S(
		long groupId, long classNameId, String ddmStructureKey,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doFindByG_C_S(
			groupId, classNameId, new String[] {ddmStructureKey},
			queryDefinition, false);
	}

	@Override
	public List<JournalArticle> findByG_C_S(
		long groupId, long classNameId, String[] ddmStructureKeys,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doFindByG_C_S(
			groupId, classNameId, ddmStructureKeys, queryDefinition, false);
	}

	@Override
	public List<JournalArticle> findByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String articleId, Double version, String title, String description,
		String content, String ddmStructureKey, String ddmTemplateKey,
		Date displayDateGT, Date displayDateLT, Date reviewDate,
		boolean andOperator, QueryDefinition<JournalArticle> queryDefinition) {

		String[] articleIds = CustomSQLUtil.keywords(articleId, false);
		String[] titles = CustomSQLUtil.keywords(title);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] contents = CustomSQLUtil.keywords(content, false);
		String[] ddmStructureKeys = CustomSQLUtil.keywords(
			ddmStructureKey, false);
		String[] ddmTemplateKeys = CustomSQLUtil.keywords(
			ddmTemplateKey, false);

		return findByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleIds, version,
			titles, descriptions, contents, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition);
	}

	@Override
	public List<JournalArticle> findByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String articleId, Double version, String title, String description,
		String content, String[] ddmStructureKeys, String[] ddmTemplateKeys,
		Date displayDateGT, Date displayDateLT, Date reviewDate,
		boolean andOperator, QueryDefinition<JournalArticle> queryDefinition) {

		String[] articleIds = CustomSQLUtil.keywords(articleId, false);
		String[] titles = CustomSQLUtil.keywords(title);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] contents = CustomSQLUtil.keywords(content, false);

		return findByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleIds, version,
			titles, descriptions, contents, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition);
	}

	@Override
	public List<JournalArticle> findByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String[] articleIds, Double version, String[] titles,
		String[] descriptions, String[] contents, String[] ddmStructureKeys,
		String[] ddmTemplateKeys, Date displayDateGT, Date displayDateLT,
		Date reviewDate, boolean andOperator,
		QueryDefinition<JournalArticle> queryDefinition) {

		return doFindByC_G_F_C_A_V_T_D_C_S_T_D_R(
			companyId, groupId, folderIds, classNameId, articleIds, version,
			titles, descriptions, contents, ddmStructureKeys, ddmTemplateKeys,
			displayDateGT, displayDateLT, reviewDate, andOperator,
			queryDefinition, false);
	}

	protected int doCountByG_F(
		long groupId, List<Long> folderIds,
		QueryDefinition<JournalArticle> queryDefinition,
		boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), COUNT_BY_G_F, queryDefinition, "JournalArticle");

			sql = replaceStatusJoin(sql, queryDefinition);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, JournalArticle.class.getName(),
					"JournalArticle.resourcePrimKey", groupId);
			}

			sql = StringUtil.replace(
				sql, "[$FOLDER_ID$]",
				getFolderIds(folderIds, JournalArticleImpl.TABLE_NAME));

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(queryDefinition.getStatus());

			for (int i = 0; i < folderIds.size(); i++) {
				Long folderId = folderIds.get(i);

				qPos.add(folderId);
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

	protected int doCountByG_F_C(
		long groupId, List<Long> folderIds, long classNameId,
		QueryDefinition<JournalArticle> queryDefinition,
		boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), COUNT_BY_G_U_F_C, queryDefinition,
				"JournalArticle");

			sql = replaceStatusJoin(sql, queryDefinition);

			if (folderIds.isEmpty()) {
				sql = StringUtil.replace(
					sql, "([$FOLDER_ID$]) AND", StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(
					sql, "[$FOLDER_ID$]",
					getFolderIds(folderIds, JournalArticleImpl.TABLE_NAME));
			}

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, JournalArticle.class.getName(),
					"JournalArticle.resourcePrimKey", groupId);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(classNameId);

			if (queryDefinition.getOwnerUserId() > 0) {
				qPos.add(queryDefinition.getOwnerUserId());

				if (queryDefinition.isIncludeOwner()) {
					qPos.add(WorkflowConstants.STATUS_IN_TRASH);
				}
			}

			for (long folderId : folderIds) {
				qPos.add(folderId);
			}

			qPos.add(queryDefinition.getStatus());

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

	protected int doCountByG_C_S(
		long groupId, long classNameId, String[] ddmStructureKeys,
		QueryDefinition<JournalArticle> queryDefinition,
		boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), COUNT_BY_G_C_S, queryDefinition, "JournalArticle");

			sql = replaceStatusJoin(sql, queryDefinition);

			if (groupId <= 0) {
				sql = StringUtil.replace(
					sql, "(JournalArticle.groupId = ?) AND", StringPool.BLANK);
			}

			sql = StringUtil.replace(
				sql, "[$DDM_STRUCTURE_KEY$]",
				getDDMStructureKeys(
					ddmStructureKeys, JournalArticleImpl.TABLE_NAME));

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, JournalArticle.class.getName(),
					"JournalArticle.resourcePrimKey", groupId);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(classNameId);
			qPos.add(ddmStructureKeys);
			qPos.add(queryDefinition.getStatus());

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

	protected int doCountByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String[] articleIds, Double version, String[] titles,
		String[] descriptions, String[] contents, String[] ddmStructureKeys,
		String[] ddmTemplateKeys, Date displayDateGT, Date displayDateLT,
		Date reviewDate, boolean andOperator,
		QueryDefinition<JournalArticle> queryDefinition,
		boolean inlineSQLHelper) {

		articleIds = CustomSQLUtil.keywords(articleIds, false);
		titles = CustomSQLUtil.keywords(titles);
		descriptions = CustomSQLUtil.keywords(descriptions, false);
		contents = CustomSQLUtil.keywords(contents, false);
		ddmStructureKeys = CustomSQLUtil.keywords(ddmStructureKeys, false);
		ddmTemplateKeys = CustomSQLUtil.keywords(ddmTemplateKeys, false);
		Timestamp displayDateGT_TS = CalendarUtil.getTimestamp(displayDateGT);
		Timestamp displayDateLT_TS = CalendarUtil.getTimestamp(displayDateLT);
		Timestamp reviewDate_TS = CalendarUtil.getTimestamp(reviewDate);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), COUNT_BY_C_G_F_C_A_V_T_D_C_S_T_D_R, queryDefinition,
				"JournalArticle");

			sql = replaceStatusJoin(sql, queryDefinition);

			if (groupId <= 0) {
				sql = StringUtil.replace(
					sql, "(JournalArticle.groupId = ?) AND", StringPool.BLANK);
			}

			if (folderIds.isEmpty()) {
				sql = StringUtil.replace(
					sql, "([$FOLDER_ID$]) AND", StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(
					sql, "[$FOLDER_ID$]",
					getFolderIds(folderIds, JournalArticleImpl.TABLE_NAME));
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "JournalArticle.articleId", StringPool.LIKE, false,
				articleIds);

			if ((version == null) || (version <= 0)) {
				sql = StringUtil.replace(
					sql, "(JournalArticle.version = ?) [$AND_OR_CONNECTOR$]",
					StringPool.BLANK);
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(JournalArticle.title)", StringPool.LIKE, false,
				titles);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "JournalArticle.description", StringPool.LIKE, false,
				descriptions);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "JournalArticle.content", StringPool.LIKE, false,
				contents);

			sql = replaceStructureTemplate(
				sql, ddmStructureKeys, ddmTemplateKeys);

			if (!isNullArray(ddmStructureKeys)) {
				sql = CustomSQLUtil.replaceKeywords(
					sql, "JournalArticle.DDMStructureKey", StringPool.LIKE,
					false, ddmStructureKeys);
			}

			if (!isNullArray(ddmTemplateKeys)) {
				sql = CustomSQLUtil.replaceKeywords(
					sql, "JournalArticle.DDMTemplateKey", StringPool.LIKE,
					false, ddmTemplateKeys);
			}

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, JournalArticle.class.getName(),
					"JournalArticle.resourcePrimKey", groupId);

				sql = StringUtil.replace(
					sql, "(companyId", "(JournalArticle.companyId");
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			for (long folderId : folderIds) {
				qPos.add(folderId);
			}

			qPos.add(classNameId);
			qPos.add(queryDefinition.getStatus());

			if (!isNullArray(ddmStructureKeys)) {
				qPos.add(ddmStructureKeys, 2);
			}

			if (!isNullArray(ddmTemplateKeys)) {
				qPos.add(ddmTemplateKeys, 2);
			}

			qPos.add(articleIds, 2);

			if ((version != null) && (version > 0)) {
				qPos.add(version);
			}

			qPos.add(titles, 2);
			qPos.add(descriptions, 2);
			qPos.add(contents, 2);
			qPos.add(displayDateGT_TS);
			qPos.add(displayDateGT_TS);
			qPos.add(displayDateLT_TS);
			qPos.add(displayDateLT_TS);
			qPos.add(reviewDate_TS);
			qPos.add(reviewDate_TS);

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

	protected List<JournalArticle> doFindByG_F(
		long groupId, List<Long> folderIds,
		QueryDefinition<JournalArticle> queryDefinition,
		boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), FIND_BY_G_F, queryDefinition, "JournalArticle");

			sql = replaceStatusJoin(sql, queryDefinition);

			sql = CustomSQLUtil.replaceOrderBy(
				sql, queryDefinition.getOrderByComparator("JournalArticle"));

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, JournalArticle.class.getName(),
					"JournalArticle.resourcePrimKey", groupId);
			}

			sql = StringUtil.replace(
				sql, "[$FOLDER_ID$]",
				getFolderIds(folderIds, JournalArticleImpl.TABLE_NAME));

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				JournalArticleImpl.TABLE_NAME, JournalArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(queryDefinition.getStatus());

			for (int i = 0; i < folderIds.size(); i++) {
				Long folderId = folderIds.get(i);

				qPos.add(folderId);
			}

			return (List<JournalArticle>)QueryUtil.list(
				q, getDialect(), queryDefinition.getStart(),
				queryDefinition.getEnd());
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<JournalArticle> doFindByG_F_C(
		long groupId, List<Long> folderIds, long classNameId,
		QueryDefinition<JournalArticle> queryDefinition,
		boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), FIND_BY_G_U_F_C, queryDefinition, "JournalArticle");

			sql = replaceStatusJoin(sql, queryDefinition);

			sql = CustomSQLUtil.replaceOrderBy(
				sql, queryDefinition.getOrderByComparator("JournalArticle"));

			if (folderIds.isEmpty()) {
				sql = StringUtil.replace(
					sql, "([$FOLDER_ID$]) AND", StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(
					sql, "[$FOLDER_ID$]",
					getFolderIds(folderIds, JournalArticleImpl.TABLE_NAME));
			}

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, JournalArticle.class.getName(),
					"JournalArticle.resourcePrimKey", groupId);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				JournalArticleImpl.TABLE_NAME, JournalArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(classNameId);

			if (queryDefinition.getOwnerUserId() > 0) {
				qPos.add(queryDefinition.getOwnerUserId());

				if (queryDefinition.isIncludeOwner()) {
					qPos.add(WorkflowConstants.STATUS_IN_TRASH);
				}
			}

			for (long folderId : folderIds) {
				qPos.add(folderId);
			}

			qPos.add(queryDefinition.getStatus());

			return (List<JournalArticle>)QueryUtil.list(
				q, getDialect(), queryDefinition.getStart(),
				queryDefinition.getEnd());
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<JournalArticle> doFindByG_C_S(
		long groupId, long classNameId, String[] ddmStructureKeys,
		QueryDefinition<JournalArticle> queryDefinition,
		boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), FIND_BY_G_C_S, queryDefinition, "JournalArticle");

			sql = replaceStatusJoin(sql, queryDefinition);

			sql = CustomSQLUtil.replaceOrderBy(
				sql, queryDefinition.getOrderByComparator("JournalArticle"));

			if (groupId <= 0) {
				sql = StringUtil.replace(
					sql, "(JournalArticle.groupId = ?) AND", StringPool.BLANK);
			}

			sql = StringUtil.replace(
				sql, "[$DDM_STRUCTURE_KEY$]",
				getDDMStructureKeys(
					ddmStructureKeys, JournalArticleImpl.TABLE_NAME));

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, JournalArticle.class.getName(),
					"JournalArticle.resourcePrimKey", groupId);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				JournalArticleImpl.TABLE_NAME, JournalArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(classNameId);
			qPos.add(ddmStructureKeys);
			qPos.add(queryDefinition.getStatus());

			return (List<JournalArticle>)QueryUtil.list(
				q, getDialect(), queryDefinition.getStart(),
				queryDefinition.getEnd());
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<JournalArticle> doFindByC_G_F_C_A_V_T_D_C_S_T_D_R(
		long companyId, long groupId, List<Long> folderIds, long classNameId,
		String[] articleIds, Double version, String[] titles,
		String[] descriptions, String[] contents, String[] ddmStructureKeys,
		String[] ddmTemplateKeys, Date displayDateGT, Date displayDateLT,
		Date reviewDate, boolean andOperator,
		QueryDefinition<JournalArticle> queryDefinition,
		boolean inlineSQLHelper) {

		articleIds = CustomSQLUtil.keywords(articleIds, false);
		titles = CustomSQLUtil.keywords(titles);
		descriptions = CustomSQLUtil.keywords(descriptions, false);
		contents = CustomSQLUtil.keywords(contents, false);
		ddmStructureKeys = CustomSQLUtil.keywords(ddmStructureKeys, false);
		ddmTemplateKeys = CustomSQLUtil.keywords(ddmTemplateKeys, false);
		Timestamp displayDateGT_TS = CalendarUtil.getTimestamp(displayDateGT);
		Timestamp displayDateLT_TS = CalendarUtil.getTimestamp(displayDateLT);
		Timestamp reviewDate_TS = CalendarUtil.getTimestamp(reviewDate);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), FIND_BY_C_G_F_C_A_V_T_D_C_S_T_D_R, queryDefinition,
				"JournalArticle");

			sql = replaceStatusJoin(sql, queryDefinition);

			if (groupId <= 0) {
				sql = StringUtil.replace(
					sql, "(JournalArticle.groupId = ?) AND", StringPool.BLANK);
			}

			if (folderIds.isEmpty()) {
				sql = StringUtil.replace(
					sql, "([$FOLDER_ID$]) AND", StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(
					sql, "[$FOLDER_ID$]",
					getFolderIds(folderIds, JournalArticleImpl.TABLE_NAME));
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "JournalArticle.articleId", StringPool.LIKE, false,
				articleIds);

			if ((version == null) || (version <= 0)) {
				sql = StringUtil.replace(
					sql, "(JournalArticle.version = ?) [$AND_OR_CONNECTOR$]",
					StringPool.BLANK);
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(JournalArticle.title)", StringPool.LIKE, false,
				titles);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "JournalArticle.description", StringPool.LIKE, false,
				descriptions);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "JournalArticle.content", StringPool.LIKE, false,
				contents);

			sql = replaceStructureTemplate(
				sql, ddmStructureKeys, ddmTemplateKeys);

			if (!isNullArray(ddmStructureKeys)) {
				sql = CustomSQLUtil.replaceKeywords(
					sql, "JournalArticle.DDMStructureKey", StringPool.LIKE,
					false, ddmStructureKeys);
			}

			if (!isNullArray(ddmTemplateKeys)) {
				sql = CustomSQLUtil.replaceKeywords(
					sql, "JournalArticle.DDMTemplateKey", StringPool.LIKE,
					false, ddmTemplateKeys);
			}

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(
				sql, queryDefinition.getOrderByComparator("JournalArticle"));

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, JournalArticle.class.getName(),
					"JournalArticle.resourcePrimKey", groupId);

				sql = StringUtil.replace(
					sql, "(companyId", "(JournalArticle.companyId");
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				JournalArticleImpl.TABLE_NAME, JournalArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			for (long folderId : folderIds) {
				qPos.add(folderId);
			}

			qPos.add(classNameId);
			qPos.add(queryDefinition.getStatus());

			if (!isNullArray(ddmStructureKeys)) {
				qPos.add(ddmStructureKeys, 2);
			}

			if (!isNullArray(ddmTemplateKeys)) {
				qPos.add(ddmTemplateKeys, 2);
			}

			qPos.add(articleIds, 2);

			if ((version != null) && (version > 0)) {
				qPos.add(version);
			}

			qPos.add(titles, 2);
			qPos.add(descriptions, 2);
			qPos.add(contents, 2);
			qPos.add(displayDateGT_TS);
			qPos.add(displayDateGT_TS);
			qPos.add(displayDateLT_TS);
			qPos.add(displayDateLT_TS);
			qPos.add(reviewDate_TS);
			qPos.add(reviewDate_TS);

			return (List<JournalArticle>)QueryUtil.list(
				q, getDialect(), queryDefinition.getStart(),
				queryDefinition.getEnd());
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getDDMStructureKeys(
		String[] ddmStructureKeys, String tableName) {

		if (ArrayUtil.isEmpty(ddmStructureKeys)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(ddmStructureKeys.length * 3 + 1);

		sb.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < ddmStructureKeys.length; i++) {
			sb.append(tableName);
			sb.append(".DDMStructureKey = ? ");
			sb.append(WHERE_OR);
		}

		sb.setIndex(sb.index() - 1);

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getFolderIds(List<Long> folderIds, String tableName) {
		if (folderIds.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(folderIds.size() * 3 + 1);

		sb.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < folderIds.size(); i++) {
			sb.append(tableName);
			sb.append(".folderId = ? ");

			if ((i + 1) != folderIds.size()) {
				sb.append(WHERE_OR);
			}
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected JournalArticle getLatestArticle(
		long groupId, String articleId, int status) {

		List<JournalArticle> articles = null;

		if (status == WorkflowConstants.STATUS_ANY) {
			articles = JournalArticleUtil.findByG_A(groupId, articleId, 0, 1);
		}
		else {
			articles = JournalArticleUtil.findByG_A_ST(
				groupId, articleId, status, 0, 1);
		}

		if (articles.isEmpty()) {
			return null;
		}

		return articles.get(0);
	}

	protected boolean isNullArray(Object[] array) {
		if (ArrayUtil.isEmpty(array)) {
			return true;
		}

		for (Object obj : array) {
			if (Validator.isNotNull(obj)) {
				return false;
			}
		}

		return true;
	}

	protected String replaceStatusJoin(
		String sql, QueryDefinition<JournalArticle> queryDefinition) {

		if (queryDefinition.getStatus() == WorkflowConstants.STATUS_ANY) {
			return StringUtil.replace(
				sql, "[$STATUS_JOIN$] AND", StringPool.BLANK);
		}

		if (queryDefinition.isExcludeStatus()) {
			StringBundler sb = new StringBundler(5);

			sb.append("(JournalArticle.status != ");
			sb.append(queryDefinition.getStatus());
			sb.append(") AND (tempJournalArticle.status != ");
			sb.append(queryDefinition.getStatus());
			sb.append(")");

			sql = StringUtil.replace(sql, "[$STATUS_JOIN$]", sb.toString());
		}
		else {
			StringBundler sb = new StringBundler(5);

			sb.append("(JournalArticle.status = ");
			sb.append(queryDefinition.getStatus());
			sb.append(") AND (tempJournalArticle.status = ");
			sb.append(queryDefinition.getStatus());
			sb.append(")");

			sql = StringUtil.replace(sql, "[$STATUS_JOIN$]", sb.toString());
		}

		return sql;
	}

	protected String replaceStructureTemplate(
		String sql, String[] ddmStructureKeys, String[] ddmTemplateKeys) {

		if (isNullArray(ddmStructureKeys) && isNullArray(ddmTemplateKeys)) {
			return StringUtil.replace(
				sql, "([$STRUCTURE_TEMPLATE$]) AND", StringPool.BLANK);
		}

		StringBundler sb = new StringBundler(3);

		if (!isNullArray(ddmStructureKeys)) {
			sb.append(_DDM_STRUCTURE_KEY_SQL);
		}

		if (!isNullArray(ddmTemplateKeys)) {
			if (!isNullArray(ddmStructureKeys)) {
				sb.append(_AND_OR_CONNECTOR);
			}

			sb.append(_DDM_TEMPLATE_KEY_SQL);
		}

		return StringUtil.replace(sql, "[$STRUCTURE_TEMPLATE$]", sb.toString());
	}

	private static final String _AND_OR_CONNECTOR = "[$AND_OR_CONNECTOR$] ";

	private static final String _DDM_STRUCTURE_KEY_SQL =
		"(JournalArticle.DDMStructureKey LIKE ? [$AND_OR_NULL_CHECK$]) ";

	private static final String _DDM_TEMPLATE_KEY_SQL =
		"(JournalArticle.DDMTemplateKey LIKE ? [$AND_OR_NULL_CHECK$]) ";

}