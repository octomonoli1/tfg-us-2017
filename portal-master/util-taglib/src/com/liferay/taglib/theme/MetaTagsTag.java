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

package com.liferay.taglib.theme;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class MetaTagsTag extends com.liferay.taglib.util.IncludeTag {

	public static void doTag(
			ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		doTag(_PAGE, servletContext, request, response);
	}

	public static void doTag(
			String page, ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher(page);

		requestDispatcher.include(request, response);
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	private static final String _PAGE = "/html/taglib/theme/meta_tags/page.jsp";

}