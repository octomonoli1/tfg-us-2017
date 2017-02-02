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

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Keith R. Davis
 * @author Iliyan Peychev
 */
public class UploadProgressTag extends IncludeTag {

	public void setHeight(int height) {
		_height = height;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setMessage(String message) {
		_message = message;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public void setRedirect(String redirect) {
	}

	public void setUpdatePeriod(Integer updatePeriod) {
		_updatePeriod = updatePeriod;
	}

	@Override
	protected void cleanUp() {
		_height = 25;
		_id = null;
		_message = null;
		_updatePeriod = 1000;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:progress:height", _height);
		request.setAttribute("liferay-ui:progress:id", _id);
		request.setAttribute("liferay-ui:progress:message", _message);
		request.setAttribute("liferay-ui:progress:updatePeriod", _updatePeriod);
	}

	private static final String _PAGE = "/html/taglib/ui/progress/page.jsp";

	private Integer _height = 25;
	private String _id;
	private String _message;
	private Integer _updatePeriod = 1000;

}