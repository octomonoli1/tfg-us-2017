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

import com.liferay.portal.kernel.search.generic.StringQuery;
import com.liferay.portal.search.elasticsearch.query.StringQueryTranslator;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = StringQueryTranslator.class)
public class StringQueryTranslatorImpl implements StringQueryTranslator {

	@Override
	public QueryBuilder translate(StringQuery stringQuery) {
		QueryStringQueryBuilder queryStringQueryBuilder =
			QueryBuilders.queryStringQuery(stringQuery.getQuery());

		if (!stringQuery.isDefaultBoost()) {
			queryStringQueryBuilder.boost(stringQuery.getBoost());
		}

		return queryStringQueryBuilder;
	}

}