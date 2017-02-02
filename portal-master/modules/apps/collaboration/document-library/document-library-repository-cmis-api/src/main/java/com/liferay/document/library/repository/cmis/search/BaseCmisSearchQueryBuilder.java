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

package com.liferay.document.library.repository.cmis.search;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.RepositoryEntry;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.QueryTerm;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.kernel.service.RepositoryEntryLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Mika Koivisto
 */
public class BaseCmisSearchQueryBuilder implements CMISSearchQueryBuilder {

	@Override
	public String buildQuery(SearchContext searchContext, Query query)
		throws SearchException {

		StringBundler sb = new StringBundler();

		sb.append("SELECT cmis:objectId");

		QueryConfig queryConfig = searchContext.getQueryConfig();

		if (queryConfig.isScoreEnabled()) {
			sb.append(", SCORE() AS HITS");
		}

		sb.append(" FROM cmis:document");

		CMISDisjunction cmisDisjunction = new CMISDisjunction();

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Repository query support " +
					queryConfig.getAttribute("capabilityQuery"));
		}

		if (!isSupportsOnlyFullText(queryConfig)) {
			traversePropertiesQuery(cmisDisjunction, query, queryConfig);
		}

		if (isSupportsFullText(queryConfig)) {
			CMISContainsExpression cmisContainsExpression =
				new CMISContainsExpression();

			traverseContentQuery(cmisContainsExpression, query, queryConfig);

			if (!cmisContainsExpression.isEmpty()) {
				cmisDisjunction.add(cmisContainsExpression);
			}
		}

		if (!cmisDisjunction.isEmpty()) {
			sb.append(" WHERE ");
			sb.append(cmisDisjunction.toQueryFragment());
		}

		Sort[] sorts = searchContext.getSorts();

		if (queryConfig.isScoreEnabled() || ArrayUtil.isNotEmpty(sorts)) {
			sb.append(" ORDER BY ");
		}

		if (ArrayUtil.isNotEmpty(sorts)) {
			int i = 0;

			for (Sort sort : sorts) {
				String fieldName = sort.getFieldName();

				if (!isSupportedField(fieldName)) {
					continue;
				}

				if (i > 0) {
					sb.append(", ");
				}

				sb.append(getCmisField(fieldName));

				if (sort.isReverse()) {
					sb.append(" DESC");
				}
				else {
					sb.append(" ASC");
				}

				i++;
			}
		}
		else if (queryConfig.isScoreEnabled()) {
			sb.append("HITS DESC");
		}

		if (_log.isDebugEnabled()) {
			_log.debug("CMIS query " + sb);
		}

		return sb.toString();
	}

	protected CMISCriterion buildFieldExpression(
			String field, String value,
			CMISSimpleExpressionOperator cmisSimpleExpressionOperator,
			QueryConfig queryConfig)
		throws SearchException {

		CMISCriterion cmisCriterion = null;

		boolean wildcard = false;

		if (CMISSimpleExpressionOperator.LIKE == cmisSimpleExpressionOperator) {
			wildcard = true;
		}

		if (field.equals(Field.FOLDER_ID)) {
			long folderId = GetterUtil.getLong(value);

			try {
				RepositoryEntry repositoryEntry =
					RepositoryEntryLocalServiceUtil.fetchRepositoryEntry(
						folderId);

				if (repositoryEntry != null) {
					String objectId = repositoryEntry.getMappedId();

					objectId = CMISParameterValueUtil.formatParameterValue(
						field, objectId, wildcard, queryConfig);

					if (queryConfig.isSearchSubfolders()) {
						cmisCriterion = new CMISInTreeExpression(objectId);
					}
					else {
						cmisCriterion = new CMISInFolderExpression(objectId);
					}
				}
			}
			catch (SystemException se) {
				throw new SearchException(
					"Unable to determine folder {folderId=" + folderId + "}",
					se);
			}
		}
		else if (field.equals(Field.USER_ID)) {
			try {
				long userId = GetterUtil.getLong(value);

				User user = UserLocalServiceUtil.getUserById(userId);

				String screenName = CMISParameterValueUtil.formatParameterValue(
					field, user.getScreenName(), wildcard, queryConfig);

				cmisCriterion = new CMISSimpleExpression(
					getCmisField(field), screenName,
					cmisSimpleExpressionOperator);
			}
			catch (Exception e) {
				if (e instanceof SearchException) {
					throw (SearchException)e;
				}

				throw new SearchException(
					"Unable to determine user {" + field + "=" + value + "}",
					e);
			}
		}
		else {
			value = CMISParameterValueUtil.formatParameterValue(
				field, value, wildcard, queryConfig);

			cmisCriterion = new CMISSimpleExpression(
				getCmisField(field), value, cmisSimpleExpressionOperator);
		}

		return cmisCriterion;
	}

	protected String getCmisField(String field) {
		return _cmisFields.get(field);
	}

	protected boolean isSupportedField(String field) {
		return _supportedFields.contains(field);
	}

	protected boolean isSupportsFullText(QueryConfig queryConfig) {
		String capabilityQuery = (String)queryConfig.getAttribute(
			"capabilityQuery");

		if (Validator.isNull(capabilityQuery)) {
			return false;
		}

		if (capabilityQuery.equals("bothcombined") ||
			capabilityQuery.equals("fulltextonly")) {

			return true;
		}

		return false;
	}

	protected boolean isSupportsOnlyFullText(QueryConfig queryConfig) {
		String capabilityQuery = (String)queryConfig.getAttribute(
			"capabilityQuery");

		if (Validator.isNull(capabilityQuery)) {
			return false;
		}

		if (capabilityQuery.equals("fulltextonly")) {
			return true;
		}

		return false;
	}

	protected void traverseContentQuery(
			CMISJunction cmisJunction, Query query, QueryConfig queryConfig)
		throws SearchException {

		if (query instanceof BooleanQuery) {
			BooleanQuery booleanQuery = (BooleanQuery)query;

			List<BooleanClause<Query>> booleanClauses = booleanQuery.clauses();

			CMISFullTextConjunction anyCMISConjunction =
				new CMISFullTextConjunction();
			CMISDisjunction cmisDisjunction = new CMISDisjunction();
			CMISFullTextConjunction notCMISConjunction =
				new CMISFullTextConjunction();

			for (BooleanClause<Query> booleanClause : booleanClauses) {
				CMISJunction currentCMISJunction = cmisDisjunction;

				BooleanClauseOccur booleanClauseOccur =
					booleanClause.getBooleanClauseOccur();

				if (booleanClauseOccur.equals(BooleanClauseOccur.MUST)) {
					currentCMISJunction = anyCMISConjunction;
				}
				else if (booleanClauseOccur.equals(
							BooleanClauseOccur.MUST_NOT)) {

					currentCMISJunction = notCMISConjunction;
				}

				Query booleanClauseQuery = booleanClause.getClause();

				traverseContentQuery(
					currentCMISJunction, booleanClauseQuery, queryConfig);
			}

			if (!anyCMISConjunction.isEmpty()) {
				cmisJunction.add(anyCMISConjunction);
			}

			if (!cmisDisjunction.isEmpty()) {
				cmisJunction.add(cmisDisjunction);
			}

			if (!notCMISConjunction.isEmpty()) {
				CMISContainsNotExpression cmisContainsNotExpression =
					new CMISContainsNotExpression(notCMISConjunction);

				cmisJunction.add(cmisContainsNotExpression);
			}
		}
		else if (query instanceof TermQuery) {
			TermQuery termQuery = (TermQuery)query;

			QueryTerm queryTerm = termQuery.getQueryTerm();

			if (!_isContentFieldQueryTerm(queryTerm)) {
				return;
			}

			String field = queryTerm.getField();
			String value = queryTerm.getValue();

			value = CMISParameterValueUtil.formatParameterValue(
				field, value, false, queryConfig);

			CMISContainsValueExpression cmisContainsValueExpression =
				new CMISContainsValueExpression(value);

			cmisJunction.add(cmisContainsValueExpression);
		}
		else if (query instanceof WildcardQuery) {
			WildcardQuery wildcardQuery = (WildcardQuery)query;

			QueryTerm queryTerm = wildcardQuery.getQueryTerm();

			if (!_isContentFieldQueryTerm(queryTerm)) {
				return;
			}

			String value = queryTerm.getValue();

			String[] terms = value.split(_STAR_PATTERN);

			CMISConjunction cmisConjunction = new CMISConjunction();

			for (String term : terms) {
				if (Validator.isNotNull(term)) {
					CMISContainsValueExpression containsValueExpression =
						new CMISContainsValueExpression(term);

					cmisConjunction.add(containsValueExpression);
				}
			}

			cmisJunction.add(cmisConjunction);
		}
		else if (query instanceof TermRangeQuery) {
			return;
		}
	}

	protected void traversePropertiesQuery(
			CMISJunction cmisJunction, Query query, QueryConfig queryConfig)
		throws SearchException {

		if (query instanceof BooleanQuery) {
			BooleanQuery booleanQuery = (BooleanQuery)query;

			List<BooleanClause<Query>> booleanClauses = booleanQuery.clauses();

			CMISConjunction anyCMISConjunction = new CMISConjunction();
			CMISDisjunction cmisDisjunction = new CMISDisjunction();
			CMISConjunction notCMISConjunction = new CMISConjunction();

			for (BooleanClause<Query> booleanClause : booleanClauses) {
				CMISJunction currentCMISJunction = cmisDisjunction;

				BooleanClauseOccur booleanClauseOccur =
					booleanClause.getBooleanClauseOccur();

				if (booleanClauseOccur.equals(BooleanClauseOccur.MUST)) {
					currentCMISJunction = anyCMISConjunction;
				}
				else if (booleanClauseOccur.equals(
							BooleanClauseOccur.MUST_NOT)) {

					currentCMISJunction = notCMISConjunction;
				}

				Query booleanClauseQuery = booleanClause.getClause();

				traversePropertiesQuery(
					currentCMISJunction, booleanClauseQuery, queryConfig);
			}

			if (!anyCMISConjunction.isEmpty()) {
				cmisJunction.add(anyCMISConjunction);
			}

			if (!cmisDisjunction.isEmpty()) {
				cmisJunction.add(cmisDisjunction);
			}

			if (!notCMISConjunction.isEmpty()) {
				cmisJunction.add(new CMISNotExpression(notCMISConjunction));
			}
		}
		else if (query instanceof MatchQuery) {
			MatchQuery matchQuery = (MatchQuery)query;

			if (!isSupportedField(matchQuery.getField())) {
				return;
			}

			CMISCriterion cmisCriterion = buildFieldExpression(
				matchQuery.getField(), matchQuery.getValue(),
				CMISSimpleExpressionOperator.EQ, queryConfig);

			if (cmisCriterion != null) {
				cmisJunction.add(cmisCriterion);
			}
		}
		else if (query instanceof TermQuery) {
			TermQuery termQuery = (TermQuery)query;

			QueryTerm queryTerm = termQuery.getQueryTerm();

			if (!isSupportedField(queryTerm.getField())) {
				return;
			}

			CMISCriterion cmisCriterion = buildFieldExpression(
				queryTerm.getField(), queryTerm.getValue(),
				CMISSimpleExpressionOperator.EQ, queryConfig);

			if (cmisCriterion != null) {
				cmisJunction.add(cmisCriterion);
			}
		}
		else if (query instanceof TermRangeQuery) {
			TermRangeQuery termRangeQuery = (TermRangeQuery)query;

			if (!isSupportedField(termRangeQuery.getField())) {
				return;
			}

			String fieldName = termRangeQuery.getField();

			String cmisField = getCmisField(fieldName);
			String cmisLowerTerm = CMISParameterValueUtil.formatParameterValue(
				fieldName, termRangeQuery.getLowerTerm(), false, queryConfig);
			String cmisUpperTerm = CMISParameterValueUtil.formatParameterValue(
				fieldName, termRangeQuery.getUpperTerm(), false, queryConfig);

			CMISCriterion cmisCriterion = new CMISBetweenExpression(
				cmisField, cmisLowerTerm, cmisUpperTerm,
				termRangeQuery.includesLower(), termRangeQuery.includesUpper());

			cmisJunction.add(cmisCriterion);
		}
		else if (query instanceof WildcardQuery) {
			WildcardQuery wildcardQuery = (WildcardQuery)query;

			QueryTerm queryTerm = wildcardQuery.getQueryTerm();

			if (!isSupportedField(queryTerm.getField())) {
				return;
			}

			CMISCriterion cmisCriterion = buildFieldExpression(
				queryTerm.getField(), queryTerm.getValue(),
				CMISSimpleExpressionOperator.LIKE, queryConfig);

			if (cmisCriterion != null) {
				cmisJunction.add(cmisCriterion);
			}
		}
	}

	private boolean _isContentFieldQueryTerm(QueryTerm queryTerm) {
		String fieldName = queryTerm.getField();

		return fieldName.equals(Field.CONTENT);
	}

	private static final String _STAR_PATTERN = Pattern.quote(StringPool.STAR);

	private static final Log _log = LogFactoryUtil.getLog(
		BaseCmisSearchQueryBuilder.class);

	private static final Map<String, String> _cmisFields;
	private static final Set<String> _supportedFields;

	static {
		_cmisFields = new HashMap<>();

		_cmisFields.put(Field.CREATE_DATE, "cmis:creationDate");
		_cmisFields.put(Field.MODIFIED_DATE, "cmis:lastModificationDate");
		_cmisFields.put(Field.NAME, "cmis:name");
		_cmisFields.put(Field.TITLE, "cmis:name");
		_cmisFields.put(Field.USER_ID, "cmis:createdBy");
		_cmisFields.put(Field.USER_NAME, "cmis:createdBy");

		_supportedFields = new HashSet<>();

		_supportedFields.add(Field.CREATE_DATE);
		_supportedFields.add(Field.FOLDER_ID);
		_supportedFields.add(Field.MODIFIED_DATE);
		_supportedFields.add(Field.NAME);
		_supportedFields.add(Field.TITLE);
		_supportedFields.add(Field.USER_ID);
		_supportedFields.add(Field.USER_NAME);
	}

}