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

package com.liferay.product.navigation.user.internal.display.context;

import com.liferay.application.list.constants.ApplicationListWebKeys;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.application.list.display.context.logic.PanelCategoryHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

/**
 * @author Julio Camarero
 */
public class MyAccountPanelCategoryDisplayContext {

	public MyAccountPanelCategoryDisplayContext(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortalException {

		_portletRequest = portletRequest;
		_portletResponse = portletResponse;

		_panelCategoryHelper =
			(PanelCategoryHelper)_portletRequest.getAttribute(
				ApplicationListWebKeys.PANEL_CATEGORY_HELPER);
		_themeDisplay = (ThemeDisplay)_portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public Group getMySiteGroup() throws PortalException {
		if (_mySiteGroup != null) {
			return _mySiteGroup;
		}

		User user = _themeDisplay.getUser();

		List<Group> mySiteGroups = user.getMySiteGroups(
			new String[] {User.class.getName()}, 1);

		if (mySiteGroups.isEmpty()) {
			return null;
		}

		_mySiteGroup = mySiteGroups.get(0);

		return _mySiteGroup;
	}

	public String getMySiteGroupURL(boolean privateLayout)
		throws PortalException {

		Group mySiteGroup = getMySiteGroup();

		return getMySiteGroupURL(mySiteGroup, privateLayout);
	}

	public boolean isMySiteGroupActive(boolean privateLayout)
		throws PortalException {

		Group mySiteGroup = getMySiteGroup();

		Group scopeGroup = _themeDisplay.getScopeGroup();

		if (mySiteGroup.getGroupId() != scopeGroup.getGroupId()) {
			return false;
		}

		Layout layout = _themeDisplay.getLayout();

		if (layout.isTypeControlPanel()) {
			return false;
		}

		if ((privateLayout && !layout.isPrivateLayout()) ||
			(!privateLayout && layout.isPrivateLayout())) {

			return false;
		}

		return true;
	}

	public boolean isShowMySiteGroup(boolean privateLayout)
		throws PortalException {

		Group mySiteGroup = getMySiteGroup();

		if (mySiteGroup == null) {
			return false;
		}

		return mySiteGroup.isShowSite(
			_themeDisplay.getPermissionChecker(), privateLayout);
	}

	protected String getGroupAdministrationURL(Group group) {
		PortletURL groupAdministrationURL = null;

		if (_panelCategoryHelper == null) {
			return null;
		}

		String portletId = _panelCategoryHelper.getFirstPortletId(
			PanelCategoryKeys.SITE_ADMINISTRATION,
			_themeDisplay.getPermissionChecker(), group);

		if (Validator.isNotNull(portletId)) {
			groupAdministrationURL = PortalUtil.getControlPanelPortletURL(
				_portletRequest, group, portletId, 0, 0,
				PortletRequest.RENDER_PHASE);

			if (groupAdministrationURL != null) {
				return groupAdministrationURL.toString();
			}
		}

		return null;
	}

	protected String getMySiteGroupURL(Group group, boolean privateLayout) {
		String groupDisplayURL = group.getDisplayURL(
			_themeDisplay, privateLayout);

		if (Validator.isNotNull(groupDisplayURL)) {
			return groupDisplayURL;
		}

		return getGroupAdministrationURL(group);
	}

	private Group _mySiteGroup;
	private final PanelCategoryHelper _panelCategoryHelper;
	private final PortletRequest _portletRequest;
	private final PortletResponse _portletResponse;
	private final ThemeDisplay _themeDisplay;

}