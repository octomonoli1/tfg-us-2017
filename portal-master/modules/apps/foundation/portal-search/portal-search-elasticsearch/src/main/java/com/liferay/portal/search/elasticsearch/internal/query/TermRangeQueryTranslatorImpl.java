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

package com.liferay.portal.search.elasticsearch.internal.query;

import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.search.elasticsearch.query.TermRangeQueryTranslator;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;

import org.osgi.service.component.annotations.Component;

/**
 * @author Miguel Angelo Caldas Gallindo
 */
@Component(immediate = true, service = TermRangeQueryTranslator.class)
public class TermRangeQueryTranslatorImpl implements TermRangeQueryTranslator {

	@Override
	public QueryBuilder translate(TermRangeQuery termRangeQuery) {
		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(
			termRangeQuery.getField());

		rangeQueryBuilder.from(termRangeQuery.getLowerTerm());
		rangeQueryBuilder.includeLower(termRangeQuery.includesLower());
		rangeQueryBuilder.includeUpper(termRangeQuery.includesUpper());
		rangeQueryBuilder.to(termRangeQuery.getUpperTerm());

		if (!termRangeQuery.isDefaultBoost()) {
			rangeQueryBuilder.boost(termRangeQuery.getBoost());
		}

		return rangeQueryBuilder;
	}

}