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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.staging.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Mate Thurzo
 */
public class SelectPagesTag extends IncludeTag {

	public void setAction(String action) {
		if (action == null) {
			_action = StringPool.BLANK;
		}
		else {
			_action = action;
		}
	}

	public void setDisableInputs(boolean disableInputs) {
		_disableInputs = disableInputs;
	}

	public void setExportImportConfigurationId(
		long exportImportConfigurationId) {

		_exportImportConfigurationId = exportImportConfigurationId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setLayoutSetBranchId(long layoutSetBranchId) {
		_layoutSetBranchId = layoutSetBranchId;
	}

	public void setLayoutSetSettings(boolean layoutSetSettings) {
		_layoutSetSettings = layoutSetSettings;
	}

	public void setLogo(boolean logo) {
		_logo = logo;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setPrivateLayout(boolean privateLayout) {
		_privateLayout = privateLayout;
	}

	public void setSelectedLayoutIds(String selectedLayoutIds) {
		_selectedLayoutIds = selectedLayoutIds;
	}

	public void setShowDeleteMissingLayouts(boolean showDeleteMissingLayouts) {
		_showDeleteMissingLayouts = showDeleteMissingLayouts;
	}

	public void setThemeReference(boolean themeReference) {
		_themeReference = themeReference;
	}

	public void setTreeId(String treeId) {
		_treeId = treeId;
	}

	@Override
	protected void cleanUp() {
		_action = StringPool.BLANK;
		_disableInputs = false;
		_exportImportConfigurationId = 0;
		_groupId = 0;
		_layoutSetBranchId = 0;
		_layoutSetSettings = true;
		_logo = true;
		_privateLayout = false;
		_selectedLayoutIds = StringPool.BLANK;
		_showDeleteMissingLayouts = false;
		_themeReference = true;
		_treeId = StringPool.BLANK;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-staging:select-pages:action", _action);
		request.setAttribute(
			"liferay-staging:select-pages:disableInputs", _disableInputs);
		request.setAttribute(
			"liferay-staging:select-pages:exportImportConfigurationId",
			_exportImportConfigurationId);
		request.setAttribute("liferay-staging:select-pages:groupId", _groupId);
		request.setAttribute(
			"liferay-staging:select-pages:layoutSetBranchId",
			_layoutSetBranchId);
		request.setAttribute(
			"liferay-staging:select-pages:layoutSetSettings",
			_layoutSetSettings);
		request.setAttribute("liferay-staging:select-pages:logo", _logo);
		request.setAttribute(
			"liferay-staging:select-pages:privateLayout", _privateLayout);
		request.setAttribute(
			"liferay-staging:select-pages:selectedLayoutIds",
			_selectedLayoutIds);
		request.setAttribute(
			"liferay-staging:select-pages:showDeleteMissingLayouts",
			_showDeleteMissingLayouts);
		request.setAttribute(
			"liferay-staging:select-pages:themeReference", _themeReference);
		request.setAttribute("liferay-staging:select-pages:treeId", _treeId);
	}

	private static final String _PAGE = "/select_pages/page.jsp";

	private String _action = StringPool.BLANK;
	private boolean _disableInputs;
	private long _exportImportConfigurationId;
	private long _groupId;
	private long _layoutSetBranchId;
	private boolean _layoutSetSettings = true;
	private boolean _logo = true;
	private boolean _privateLayout;
	private String _selectedLayoutIds = StringPool.BLANK;
	private boolean _showDeleteMissingLayouts;
	private boolean _themeReference = true;
	private String _treeId = StringPool.BLANK;

}