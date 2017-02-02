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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.taglib.util.IncludeTag;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class AssetCategoriesSummaryTag<R> extends IncludeTag {

	public PortletURL getPortletURL() {
		return _portletURL;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public void setParamName(String paramName) {
		_paramName = paramName;
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	@Override
	protected void cleanUp() {
		_className = null;
		_classPK = 0;
		_message = null;
		_paramName = null;
		_portletURL = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		List<AssetCategory> assetCategories = new ArrayList<>();

		AssetCategoriesAvailableTag<R> assetCategoriesAvailableTag =
			(AssetCategoriesAvailableTag<R>)findAncestorWithClass(
				this, AssetCategoriesAvailableTag.class);

		if (assetCategoriesAvailableTag != null) {
			assetCategories = assetCategoriesAvailableTag.getAssetCategories();
		}

		request.setAttribute(
			"liferay-ui:asset-categories-summary:assetCategories",
			assetCategories);

		request.setAttribute(
			"liferay-ui:asset-categories-summary:className", _className);
		request.setAttribute(
			"liferay-ui:asset-categories-summary:classPK",
			String.valueOf(_classPK));
		request.setAttribute(
			"liferay-ui:asset-categories-summary:message", _message);
		request.setAttribute(
			"liferay-ui:asset-categories-summary:paramName", _paramName);
		request.setAttribute(
			"liferay-ui:asset-categories-summary:portletURL", _portletURL);
	}

	private static final String _PAGE =
		"/html/taglib/ui/asset_categories_summary/page.jsp";

	private String _className;
	private long _classPK;
	private String _message;
	private String _paramName;
	private PortletURL _portletURL;

}