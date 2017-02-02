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

package com.liferay.portal.search.solr.internal.filter;

import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.search.solr.filter.TermsFilterTranslator;

import java.util.ArrayList;

import org.apache.lucene.index.Term;
import org.apache.lucene.queries.TermsQuery;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = TermsFilterTranslator.class)
public class TermsFilterTranslatorImpl implements TermsFilterTranslator {

	@Override
	public org.apache.lucene.search.Query translate(TermsFilter termsFilter) {
		String field = termsFilter.getField();

		ArrayList<Term> terms = new ArrayList<>();

		for (String value : termsFilter.getValues()) {
			terms.add(new Term(field, value));
		}

		TermsQuery termsQuery = new TermsQuery(terms);

		if (terms.size() == 1) {
			return termsQuery;
		}

		BooleanQuery booleanQuery = new BooleanQuery();

		booleanQuery.add(termsQuery, BooleanClause.Occur.SHOULD);

		return booleanQuery;
	}

}