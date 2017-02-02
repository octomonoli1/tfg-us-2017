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

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class AssetAddButtonTag extends IncludeTag {

	public long[] getAllAssetCategoryIds() {
		return _allAssetCategoryIds;
	}

	public String[] getAllAssetTagNames() {
		return _allAssetTagNames;
	}

	public long[] getClassNameIds() {
		return _classNameIds;
	}

	public long[] getClassTypeIds() {
		return _classTypeIds;
	}

	public long[] getGroupIds() {
		return _groupIds;
	}

	public String getRedirect() {
		return _redirect;
	}

	public boolean isAddDisplayPageParameter() {
		return _addDisplayPageParameter;
	}

	public boolean isUseDialog() {
		return _useDialog;
	}

	public void setAddDisplayPageParameter(boolean addDisplayPageParameter) {
		_addDisplayPageParameter = addDisplayPageParameter;
	}

	public void setAllAssetCategoryIds(long[] allAssetCategoryIds) {
		_allAssetCategoryIds = allAssetCategoryIds;
	}

	public void setAllAssetTagNames(String[] allAssetTagNames) {
		_allAssetTagNames = allAssetTagNames;
	}

	public void setClassNameIds(long[] classNameIds) {
		_classNameIds = classNameIds;
	}

	public void setClassTypeIds(long[] classTypeIds) {
		_classTypeIds = classTypeIds;
	}

	public void setGroupIds(long[] groupIds) {
		_groupIds = groupIds;
	}

	public void setRedirect(String redirect) {
		_redirect = redirect;
	}

	public void setUseDialog(boolean useDialog) {
		_useDialog = useDialog;
	}

	@Override
	protected void cleanUp() {
		_addDisplayPageParameter = false;
		_allAssetCategoryIds = null;
		_allAssetTagNames = null;
		_classNameIds = null;
		_classTypeIds = null;
		_groupIds = null;
		_redirect = null;
		_useDialog = true;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		request.setAttribute(
			"liferay-ui:asset-add-button:addDisplayPageParameter",
			_addDisplayPageParameter);
		request.setAttribute(
			"liferay-ui:asset-add-button:allAssetCategoryIds",
			_allAssetCategoryIds);
		request.setAttribute(
			"liferay-ui:asset-add-button:allAssetTagNames", _allAssetTagNames);

		long[] classNameIds = _classNameIds;

		if (classNameIds == null) {
			classNameIds = AssetRendererFactoryRegistryUtil.getClassNameIds(
				themeDisplay.getCompanyId());
		}

		request.setAttribute(
			"liferay-ui:asset-add-button:classNameIds", classNameIds);

		request.setAttribute(
			"liferay-ui:asset-add-button:classTypeIds", _classTypeIds);

		long[] groupIds = _groupIds;

		if (groupIds == null) {
			groupIds = new long[] {themeDisplay.getScopeGroupId()};
		}

		request.setAttribute("liferay-ui:asset-add-button:groupIds", groupIds);

		request.setAttribute("liferay-ui:asset-add-button:redirect", _redirect);
		request.setAttribute(
			"liferay-ui:asset-add-button:useDialog", _useDialog);
	}

	private static final String _PAGE =
		"/html/taglib/ui/asset_add_button/page.jsp";

	private boolean _addDisplayPageParameter;
	private long[] _allAssetCategoryIds;
	private String[] _allAssetTagNames;
	private long[] _classNameIds;
	private long[] _classTypeIds;
	private long[] _groupIds;
	private String _redirect;
	private boolean _useDialog = true;

}