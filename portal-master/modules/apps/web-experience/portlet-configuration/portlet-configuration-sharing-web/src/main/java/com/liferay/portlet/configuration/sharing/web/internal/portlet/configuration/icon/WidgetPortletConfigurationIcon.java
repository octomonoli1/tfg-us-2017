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

package com.liferay.portlet.configuration.sharing.web.internal.portlet.configuration.icon;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.configuration.sharing.web.internal.constants.PortletConfigurationSharingPortletKeys;

import java.util.ResourceBundle;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = PortletConfigurationIcon.class)
public class WidgetPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	@Override
	public String getMessage(PortletRequest portletRequest) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", getLocale(portletRequest), getClass());

		return LanguageUtil.get(resourceBundle, "add-to-any-website");
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		try {
			LiferayPortletURL portletURL = PortletURLFactoryUtil.create(
				portletRequest,
				PortletConfigurationSharingPortletKeys.
					PORTLET_CONFIGURATION_SHARING,
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter("widgetURL", getWidgetURL(portletRequest));
			portletURL.setWindowState(LiferayWindowState.POP_UP);

			return portletURL.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			return StringPool.BLANK;
		}
	}

	@Override
	public double getWeight() {
		return 5.0;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		PortletPreferences portletSetup = portletDisplay.getPortletSetup();

		boolean lfrWidgetShowAddAppLink = GetterUtil.getBoolean(
			portletSetup.getValue("lfrWidgetShowAddAppLink", null),
			PropsValues.THEME_PORTLET_SHARING_DEFAULT);

		if (lfrWidgetShowAddAppLink) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isUseDialog() {
		return true;
	}

	protected String getWidgetURL(PortletRequest portletRequest)
		throws PortalException {

		Portlet portlet = (Portlet)portletRequest.getAttribute(
			WebKeys.RENDER_PORTLET);

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return PortalUtil.getWidgetURL(portlet, themeDisplay);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WidgetPortletConfigurationIcon.class);

}