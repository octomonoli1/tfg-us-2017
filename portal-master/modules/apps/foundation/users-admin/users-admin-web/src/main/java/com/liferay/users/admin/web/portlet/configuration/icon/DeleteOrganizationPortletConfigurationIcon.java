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

package com.liferay.users.admin.web.portlet.configuration.icon;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.users.admin.constants.UsersAdminPortletKeys;
import com.liferay.users.admin.web.portlet.action.ActionUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pei-Jung Lan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + UsersAdminPortletKeys.USERS_ADMIN,
		"path=/users_admin/view"
	},
	service = PortletConfigurationIcon.class
)
public class DeleteOrganizationPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	@Override
	public String getMessage(PortletRequest portletRequest) {
		return LanguageUtil.get(
			getResourceBundle(getLocale(portletRequest)), "delete");
	}

	@Override
	public String getOnClick(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		StringBundler sb = new StringBundler(4);

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			sb.append(portletDisplay.getNamespace());

			sb.append("deleteOrganization('");

			Organization organization = ActionUtil.getOrganization(
				portletRequest);

			sb.append(organization.getOrganizationId());

			sb.append("');");
		}
		catch (Exception e) {
		}

		return sb.toString();
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return "javascript:;";
	}

	@Override
	public double getWeight() {
		return 102;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			PermissionChecker permissionChecker =
				themeDisplay.getPermissionChecker();

			Organization organization = ActionUtil.getOrganization(
				portletRequest);

			if (OrganizationPermissionUtil.contains(
					permissionChecker, organization, ActionKeys.DELETE)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

}