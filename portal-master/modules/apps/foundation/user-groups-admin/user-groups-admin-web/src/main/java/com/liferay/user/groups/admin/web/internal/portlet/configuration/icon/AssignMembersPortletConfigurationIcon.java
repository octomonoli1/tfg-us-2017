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

package com.liferay.user.groups.admin.web.internal.portlet.configuration.icon;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.UserGroupPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.user.groups.admin.constants.UserGroupsAdminPortletKeys;
import com.liferay.user.groups.admin.web.internal.portlet.action.ActionUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pei-Jung Lan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + UserGroupsAdminPortletKeys.USER_GROUPS_ADMIN,
		"path=/edit_user_group.jsp"
	},
	service = PortletConfigurationIcon.class
)
public class AssignMembersPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	@Override
	public String getMessage(PortletRequest portletRequest) {
		return LanguageUtil.get(
			getResourceBundle(getLocale(portletRequest)), "assign-members");
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		try {
			PortletURL portletURL = PortletURLFactoryUtil.create(
				portletRequest, UserGroupsAdminPortletKeys.USER_GROUPS_ADMIN,
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter(
				"mvcPath", "/edit_user_group_assignments.jsp");
			portletURL.setParameter(
				"redirect", PortalUtil.getCurrentURL(portletRequest));

			UserGroup userGroup = ActionUtil.getUserGroup(portletRequest);

			portletURL.setParameter(
				"userGroupId", String.valueOf(userGroup.getUserGroupId()));

			return portletURL.toString();
		}
		catch (Exception e) {
		}

		return StringPool.BLANK;
	}

	@Override
	public double getWeight() {
		return 102;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)portletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			UserGroup userGroup = ActionUtil.getUserGroup(portletRequest);

			return UserGroupPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), userGroup.getUserGroupId(),
				ActionKeys.ASSIGN_MEMBERS);
		}
		catch (Exception e) {
		}

		return false;
	}

}