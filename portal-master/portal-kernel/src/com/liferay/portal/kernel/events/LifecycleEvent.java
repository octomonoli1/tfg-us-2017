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

package com.liferay.portal.kernel.events;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Raymond Augé
 */
public class LifecycleEvent {

	public LifecycleEvent() {
		this(null, null, null, null);
	}

	public LifecycleEvent(
		HttpServletRequest request, HttpServletResponse response) {

		this(null, request, response, null);
	}

	public LifecycleEvent(HttpSession session) {
		this(null, null, null, session);
	}

	public LifecycleEvent(String[] ids) {
		this(ids, null, null, null);
	}

	public LifecycleEvent(
		String[] ids, HttpServletRequest request, HttpServletResponse response,
		HttpSession session) {

		_ids = ids;
		_request = request;
		_response = response;
		_session = session;
	}

	public String[] getIds() {
		return _ids;
	}

	public HttpServletRequest getRequest() {
		return _request;
	}

	public HttpServletResponse getResponse() {
		return _response;
	}

	public HttpSession getSession() {
		return _session;
	}

	private final String[] _ids;
	private final HttpServletRequest _request;
	private final HttpServletResponse _response;
	private final HttpSession _session;

}