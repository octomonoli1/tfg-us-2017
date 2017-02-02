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

package com.liferay.document.library.repository.external.search;

import com.liferay.portal.kernel.search.SearchException;

import java.util.Date;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepositoryQueryMapper {

	public Date formatDateParameterValue(String fieldName, String fieldValue)
		throws SearchException;

	public String formatParameterValue(String fieldName, String fieldValue)
		throws SearchException;

}