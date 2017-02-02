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

package com.liferay.portal.kernel.search.bundle.opensearchregistryutil;

import com.liferay.portal.kernel.search.OpenSearch;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Peter Fellwock
 */
@Component(
	immediate = true,
	property = {"service.ranking:Integer=" + Integer.MAX_VALUE}
)
public class TestOpenSearch implements OpenSearch {

	@Override
	public String getClassName() {
		return TestOpenSearch.class.getName();
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public String search(
		HttpServletRequest request, long groupId, long userId, String keywords,
		int startPage, int itemsPerPage, String format) {

		return groupId + ":" + userId;
	}

	@Override
	public String search(
		HttpServletRequest request, long userId, String keywords, int startPage,
		int itemsPerPage, String format) {

		return userId + ":" + startPage;
	}

	@Override
	public String search(HttpServletRequest request, String url) {
		return url;
	}

}