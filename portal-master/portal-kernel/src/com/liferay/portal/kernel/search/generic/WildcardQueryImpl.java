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

package com.liferay.portal.kernel.search.generic;

import com.liferay.portal.kernel.search.BaseQueryImpl;
import com.liferay.portal.kernel.search.QueryTerm;
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.query.QueryVisitor;

/**
 * @author Michael C. Han
 */
public class WildcardQueryImpl extends BaseQueryImpl implements WildcardQuery {

	public WildcardQueryImpl(QueryTerm queryTerm) {
		_queryTerm = queryTerm;
	}

	public WildcardQueryImpl(String field, String value) {
		this(new QueryTermImpl(field, value));
	}

	@Override
	public <T> T accept(QueryVisitor<T> queryVisitor) {
		return queryVisitor.visitQuery(this);
	}

	@Override
	public QueryTerm getQueryTerm() {
		return _queryTerm;
	}

	private final QueryTerm _queryTerm;

}