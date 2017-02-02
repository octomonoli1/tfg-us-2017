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

package com.liferay.portlet.sites.action;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletPreferencesIds;
import com.liferay.portal.kernel.portlet.PortletLayoutListener;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.sites.kernel.util.SitesUtil;

import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class ActionUtil {

	public static void copyPreferences(
			HttpServletRequest request, Layout targetLayout,
			Layout sourceLayout)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		LayoutTypePortlet sourceLayoutTypePortlet =
			(LayoutTypePortlet)sourceLayout.getLayoutType();

		List<String> sourcePortletIds = sourceLayoutTypePortlet.getPortletIds();

		for (String sourcePortletId : sourcePortletIds) {

			// Copy preference

			PortletPreferencesIds portletPreferencesIds =
				PortletPreferencesFactoryUtil.getPortletPreferencesIds(
					request, targetLayout, sourcePortletId);

			PortletPreferencesLocalServiceUtil.getPreferences(
				portletPreferencesIds);

			PortletPreferencesIds sourcePortletPreferencesIds =
				PortletPreferencesFactoryUtil.getPortletPreferencesIds(
					request, sourceLayout, sourcePortletId);

			PortletPreferences sourcePreferences =
				PortletPreferencesLocalServiceUtil.getPreferences(
					sourcePortletPreferencesIds);

			PortletPreferencesLocalServiceUtil.updatePreferences(
				portletPreferencesIds.getOwnerId(),
				portletPreferencesIds.getOwnerType(),
				portletPreferencesIds.getPlid(),
				portletPreferencesIds.getPortletId(), sourcePreferences);

			// Copy portlet setup

			PortletPreferences targetPreferences =
				PortletPreferencesLocalServiceUtil.getPreferences(
					themeDisplay.getCompanyId(),
					PortletKeys.PREFS_OWNER_ID_DEFAULT,
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT, targetLayout.getPlid(),
					sourcePortletId);

			sourcePreferences =
				PortletPreferencesLocalServiceUtil.getPreferences(
					themeDisplay.getCompanyId(),
					PortletKeys.PREFS_OWNER_ID_DEFAULT,
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT, sourceLayout.getPlid(),
					sourcePortletId);

			PortletPreferencesLocalServiceUtil.updatePreferences(
				PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, targetLayout.getPlid(),
				sourcePortletId, sourcePreferences);

			SitesUtil.updateLayoutScopes(
				themeDisplay.getUserId(), sourceLayout, targetLayout,
				sourcePreferences, targetPreferences, sourcePortletId,
				themeDisplay.getLanguageId());

			Portlet sourcePortlet = PortletLocalServiceUtil.getPortletById(
				sourceLayout.getCompanyId(), sourcePortletId);

			PortletLayoutListener sourcePortletLayoutListener =
				sourcePortlet.getPortletLayoutListenerInstance();

			if (sourcePortletLayoutListener != null) {
				sourcePortletLayoutListener.onAddToLayout(
					sourcePortletId, targetLayout.getPlid());
			}
		}
	}

	public static void copyPreferences(
			PortletRequest portletRequest, Layout targetLayout,
			Layout sourceLayout)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		copyPreferences(request, targetLayout, sourceLayout);
	}

	public static Group getGroup(HttpServletRequest request) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(request, Constants.CMD);

		long groupId = ParamUtil.getLong(request, "groupId");

		Group group = null;

		if (groupId > 0) {
			group = GroupLocalServiceUtil.getGroup(groupId);
		}
		else if (!cmd.equals(Constants.ADD)) {
			group = themeDisplay.getSiteGroup();
		}

		request.setAttribute(WebKeys.GROUP, group);

		return group;
	}

	public static Group getGroup(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		return getGroup(request);
	}

	public static void removePortletIds(
			HttpServletRequest request, Layout layout)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		List<String> portletIds = layoutTypePortlet.getPortletIds();

		for (String portletId : portletIds) {
			layoutTypePortlet.removePortletId(
				themeDisplay.getUserId(), portletId);
		}

		LayoutServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());
	}

	public static void removePortletIds(
			PortletRequest portletRequest, Layout layout)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		removePortletIds(request, layout);
	}

}