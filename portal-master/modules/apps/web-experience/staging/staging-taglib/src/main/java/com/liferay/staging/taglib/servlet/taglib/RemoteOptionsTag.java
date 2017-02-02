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

package com.liferay.staging.taglib.servlet.taglib;

import com.liferay.staging.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Levente Hudák
 */
public class RemoteOptionsTag extends IncludeTag {

	public void setDisableInputs(boolean disableInputs) {
		_disableInputs = disableInputs;
	}

	public void setExportImportConfigurationId(
		long exportImportConfigurationId) {

		_exportImportConfigurationId = exportImportConfigurationId;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setPrivateLayout(boolean privateLayout) {
		_privateLayout = privateLayout;
	}

	@Override
	protected void cleanUp() {
		_disableInputs = false;
		_exportImportConfigurationId = 0;
		_privateLayout = false;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-staging:remote-options:disableInputs", _disableInputs);
		request.setAttribute(
			"liferay-staging:remote-options:exportImportConfigurationId",
			_exportImportConfigurationId);
		request.setAttribute(
			"liferay-staging:remote-options:privateLayout", _privateLayout);
	}

	private static final String _PAGE = "/remote_options/page.jsp";

	private boolean _disableInputs;
	private long _exportImportConfigurationId;
	private boolean _privateLayout;

}