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

package com.liferay.message.boards.display.context;

import com.liferay.portal.kernel.display.context.BaseDisplayContext;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iván Zaera
 */
public abstract class BaseMBDisplayContext<T extends MBDisplayContext>
	extends BaseDisplayContext<T> implements MBDisplayContext {

	public BaseMBDisplayContext(
		UUID uuid, T parentDisplayContext, HttpServletRequest request,
		HttpServletResponse response) {

		super(uuid, parentDisplayContext, request, response);
	}

}