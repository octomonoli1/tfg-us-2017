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

package com.liferay.portlet.configuration.web.internal.portlet.configuration.icon;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.configuration.kernel.util.PortletConfigurationApplicationType;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = PortletConfigurationIcon.class)
public class PermissionsPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	@Override
	public String getMessage(PortletRequest portletRequest) {
		return LanguageUtil.get(
			getResourceBundle(getLocale(portletRequest)), "permissions");
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		try {
			String returnToFullPageURL = ParamUtil.getString(
				portletRequest, "returnToFullPageURL");

			PortletURL portletURL = PortletProviderUtil.getPortletURL(
				portletRequest,
				PortletConfigurationApplicationType.
					PortletConfiguration.CLASS_NAME,
				PortletProvider.Action.VIEW);

			portletURL.setParameter("mvcPath", "/edit_permissions.jsp");
			portletURL.setParameter("returnToFullPageURL", returnToFullPageURL);
			portletURL.setParameter(
				"portletConfiguration", Boolean.TRUE.toString());

			ThemeDisplay themeDisplay =
				(ThemeDisplay)portletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			portletURL.setParameter("portletResource", portletDisplay.getId());
			portletURL.setParameter(
				"resourcePrimKey",
				PortletPermissionUtil.getPrimaryKey(
					themeDisplay.getPlid(), portletDisplay.getId()));
			portletURL.setWindowState(LiferayWindowState.POP_UP);

			return portletURL.toString();
		}
		catch (Exception e) {
		}

		return StringPool.BLANK;
	}

	@Override
	public double getWeight() {
		return 13.0;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String rootPortletId = portletDisplay.getRootPortletId();

		if (Validator.isNotNull(portletDisplay.getPortletResource())) {
			String portletResource = portletDisplay.getPortletResource();

			Portlet portlet = _portletLocalService.getPortletById(
				themeDisplay.getCompanyId(), portletResource);

			rootPortletId = portlet.getRootPortletId();
		}

		try {
			if (!PortletPermissionUtil.contains(
					themeDisplay.getPermissionChecker(),
					themeDisplay.getLayout(), rootPortletId,
					ActionKeys.PERMISSIONS)) {

				return false;
			}
		}
		catch (PortalException pe) {
			return false;
		}

		return portletDisplay.isShowConfigurationIcon();
	}

	@Override
	public boolean isUseDialog() {
		return true;
	}

	@Reference(unbind = "-")
	protected void setPortletLocalService(
		PortletLocalService portletLocalService) {

		_portletLocalService = portletLocalService;
	}

	private PortletLocalService _portletLocalService;

}