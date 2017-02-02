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

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public interface SortFactory {

	public Sort create(String fieldName, boolean reverse);

	public Sort create(String fieldName, int type, boolean reverse);

	public Sort[] getDefaultSorts();

	public Sort getSort(
		Class<?> clazz, int type, String orderByCol, boolean inferSortField,
		String orderByType);

	public Sort getSort(
		Class<?> clazz, int type, String orderByCol, String orderByType);

	public Sort getSort(Class<?> clazz, String orderByCol, String orderByType);

	public Sort[] toArray(List<Sort> sorts);

}