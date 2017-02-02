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

import com.liferay.portal.kernel.search.generic.FuzzyQuery;
import com.liferay.portal.search.elasticsearch.query.FuzzyQueryTranslator;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = FuzzyQueryTranslator.class)
public class FuzzyQueryTranslatorImpl implements FuzzyQueryTranslator {

	@Override
	public QueryBuilder translate(FuzzyQuery fuzzyQuery) {
		FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery(
			fuzzyQuery.getField(), fuzzyQuery.getValue());

		if (fuzzyQuery.getFuzziness() != null) {
			fuzzyQueryBuilder.fuzziness(
				Fuzziness.build(fuzzyQuery.getFuzziness()));
		}

		if (fuzzyQuery.getMaxExpansions() != null) {
			fuzzyQueryBuilder.maxExpansions(fuzzyQuery.getMaxExpansions());
		}

		if (fuzzyQuery.getPrefixLength() != null) {
			fuzzyQueryBuilder.prefixLength(fuzzyQuery.getPrefixLength());
		}

		if (!fuzzyQuery.isDefaultBoost()) {
			fuzzyQueryBuilder.boost(fuzzyQuery.getBoost());
		}

		return fuzzyQueryBuilder;
	}

}