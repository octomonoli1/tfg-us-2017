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

package com.liferay.application.list;

import com.liferay.application.list.display.context.logic.PanelCategoryHelper;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Adolfo Pérez
 */
public class RootPanelCategory implements PanelCategory {

	public static PanelCategory getInstance() {
		return _instance;
	}

	@Override
	public String getKey() {
		return _ROOT_PANEL_CATEGORY_KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		return StringPool.BLANK;
	}

	@Override
	public int getNotificationsCount(
		PanelCategoryHelper panelCategoryHelper,
		PermissionChecker permissionChecker, Group group, User user) {

		return 0;
	}

	@Override
	public boolean include(
		HttpServletRequest request, HttpServletResponse response) {

		return false;
	}

	@Override
	public boolean includeHeader(
		HttpServletRequest request, HttpServletResponse response) {

		return false;
	}

	@Override
	public boolean isActive(
		HttpServletRequest request, PanelCategoryHelper panelCategoryHelper,
		Group group) {

		return false;
	}

	@Override
	public boolean isPersistState() {
		return false;
	}

	@Override
	public boolean isShow(PermissionChecker permissionChecker, Group group) {
		return true;
	}

	private RootPanelCategory() {
	}

	private static final String _ROOT_PANEL_CATEGORY_KEY = "root";

	private static final PanelCategory _instance = new RootPanelCategory();

}