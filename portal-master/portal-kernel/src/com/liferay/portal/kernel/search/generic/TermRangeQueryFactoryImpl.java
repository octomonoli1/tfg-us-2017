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

import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.kernel.search.TermRangeQueryFactory;

/**
 * @author     Raymond Augé
 * @deprecated As of 7.0.0
 */
@Deprecated
public class TermRangeQueryFactoryImpl implements TermRangeQueryFactory {

	@Override
	public TermRangeQuery create(
		String field, String lowerTerm, String upperTerm, boolean includesLower,
		boolean includesUpper) {

		return new TermRangeQueryImpl(
			field, lowerTerm, upperTerm, includesLower, includesUpper);
	}

}