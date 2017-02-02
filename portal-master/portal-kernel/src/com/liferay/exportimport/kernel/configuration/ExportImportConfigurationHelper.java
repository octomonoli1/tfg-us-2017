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

package com.liferay.exportimport.kernel.configuration;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.Serializable;

import java.util.Map;

import javax.portlet.PortletRequest;

/**
 * @author Levente Hudák
 */
@ProviderType
public class ExportImportConfigurationHelper {

	public static ExportImportConfiguration
			addExportLayoutExportImportConfiguration(
				PortletRequest portletRequest)
		throws PortalException {

		return addExportImportConfiguration(
			portletRequest,
			ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT);
	}

	public static ExportImportConfiguration
			addPublishLayoutLocalExportImportConfiguration(
				PortletRequest portletRequest)
		throws PortalException {

		return addExportImportConfiguration(
			portletRequest,
			ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_LOCAL);
	}

	public static ExportImportConfiguration
			addPublishLayoutRemoteExportImportConfiguration(
				PortletRequest portletRequest)
		throws PortalException {

		return addExportImportConfiguration(
			portletRequest,
			ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_REMOTE);
	}

	public static ExportImportConfiguration
			updateExportLayoutExportImportConfiguration(
				PortletRequest portletRequest)
		throws PortalException {

		return updateExportImportConfiguration(
			portletRequest,
			ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT);
	}

	public static ExportImportConfiguration
			updatePublishLayoutLocalExportImportConfiguration(
				PortletRequest portletRequest)
		throws PortalException {

		return updateExportImportConfiguration(
			portletRequest,
			ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_LOCAL);
	}

	public static ExportImportConfiguration
			updatePublishLayoutRemoteExportImportConfiguration(
				PortletRequest portletRequest)
		throws PortalException {

		return updateExportImportConfiguration(
			portletRequest,
			ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_REMOTE);
	}

	protected static ExportImportConfiguration addExportImportConfiguration(
			PortletRequest portletRequest, int type)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = ParamUtil.getLong(portletRequest, "groupId");

		if (type == ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT) {
			groupId = ParamUtil.getLong(portletRequest, "liveGroupId");
		}

		String name = ParamUtil.getString(portletRequest, "name");
		String description = ParamUtil.getString(portletRequest, "description");

		Map<String, Serializable> settingsMap =
			ExportImportConfigurationSettingsMapFactory.buildSettingsMap(
				portletRequest, groupId, type);

		Map<String, String[]> parameterMap =
			(Map<String, String[]>)settingsMap.get("parameterMap");

		if ((parameterMap != null) &&
			(type ==
				ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_LOCAL)) {

			parameterMap.put(
				PortletDataHandlerKeys.PERFORM_DIRECT_BINARY_IMPORT,
				new String[] {Boolean.TRUE.toString()});
		}

		return ExportImportConfigurationLocalServiceUtil.
			addExportImportConfiguration(
				themeDisplay.getUserId(), groupId, name, description, type,
				settingsMap, new ServiceContext());
	}

	protected static ExportImportConfiguration updateExportImportConfiguration(
			PortletRequest portletRequest, int type)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long exportImportConfigurationId = ParamUtil.getLong(
			portletRequest, "exportImportConfigurationId");

		long groupId = ParamUtil.getLong(portletRequest, "groupId");
		String name = ParamUtil.getString(portletRequest, "name");
		String description = ParamUtil.getString(portletRequest, "description");

		Map<String, Serializable> settingsMap =
			ExportImportConfigurationSettingsMapFactory.buildSettingsMap(
				portletRequest, groupId, type);

		return ExportImportConfigurationLocalServiceUtil.
			updateExportImportConfiguration(
				themeDisplay.getUserId(), exportImportConfigurationId, name,
				description, settingsMap, new ServiceContext());
	}

}