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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.search.filter.Filter;

/**
 * @author Bruno Farache
 */
public interface BooleanClauseFactory {

	public BooleanClause<Query> create(Query query, String occur);

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #create(Query, String)}
	 */
	@Deprecated
	public BooleanClause<Query> create(
		SearchContext searchContext, Query query, String occur);

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #create(String, String,
	 *             String)}}
	 */
	@Deprecated
	public BooleanClause<Query> create(
		SearchContext searchContext, String field, String value, String occur);

	public BooleanClause<Query> create(
		String field, String value, String occur);

	public BooleanClause<Filter> createFilter(
		Filter filter, BooleanClauseOccur booleanClauseOccur);

	public BooleanClause<Filter> createFilter(
		String field, String value, BooleanClauseOccur booleanClauseOccur);

}