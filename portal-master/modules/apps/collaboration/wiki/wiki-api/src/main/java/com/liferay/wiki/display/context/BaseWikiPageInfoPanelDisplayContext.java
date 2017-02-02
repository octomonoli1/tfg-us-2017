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

package com.liferay.wiki.display.context;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Roberto Díaz
 */
public abstract class BaseWikiPageInfoPanelDisplayContext
	extends BaseWikiDisplayContext<WikiPageInfoPanelDisplayContext>
	implements WikiPageInfoPanelDisplayContext {

	public BaseWikiPageInfoPanelDisplayContext(
		UUID uuid,
		WikiPageInfoPanelDisplayContext wikiPageInfoPanelDisplayContext,
		HttpServletRequest request, HttpServletResponse response) {

		super(uuid, wikiPageInfoPanelDisplayContext, request, response);
	}

}