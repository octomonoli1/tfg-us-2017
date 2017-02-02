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

package com.liferay.exportimport.web.internal.portlet.action;

import com.liferay.exportimport.constants.ExportImportPortletKeys;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationSettingsMapFactory;
import com.liferay.exportimport.kernel.exception.LARFileNameException;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService;
import com.liferay.exportimport.kernel.service.ExportImportService;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.ui.util.SessionTreeJSClicks;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniel Kocsis
 * @author Mate Thurzo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ExportImportPortletKeys.EXPORT,
		"mvc.command.name=exportLayouts"
	},
	service = MVCActionCommand.class
)
public class ExportLayoutsMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		hideDefaultSuccessMessage(actionRequest);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (Validator.isNull(cmd)) {
			SessionMessages.add(
				actionRequest,
				PortalUtil.getPortletId(actionRequest) +
					SessionMessages.KEY_SUFFIX_FORCE_SEND_REDIRECT);

			hideDefaultSuccessMessage(actionRequest);

			return;
		}

		setLayoutIdMap(actionRequest);

		try {
			ExportImportConfiguration exportImportConfiguration =
				getExportImportConfiguration(actionRequest);

			_exportImportService.exportLayoutsAsFileInBackground(
				exportImportConfiguration);

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass());

			if (!(e instanceof LARFileNameException)) {
				_log.error(e, e);
			}
		}
	}

	protected ExportImportConfiguration getExportImportConfiguration(
			ActionRequest actionRequest)
		throws Exception {

		Map<String, Serializable> exportLayoutSettingsMap = null;

		long exportImportConfigurationId = ParamUtil.getLong(
			actionRequest, "exportImportConfigurationId");

		if (exportImportConfigurationId > 0) {
			ExportImportConfiguration exportImportConfiguration =
				_exportImportConfigurationLocalService.
					fetchExportImportConfiguration(exportImportConfigurationId);

			if (exportImportConfiguration != null) {
				exportLayoutSettingsMap =
					exportImportConfiguration.getSettingsMap();
			}
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		boolean privateLayout = ParamUtil.getBoolean(
			actionRequest, "privateLayout");

		if (exportLayoutSettingsMap == null) {
			long groupId = ParamUtil.getLong(actionRequest, "liveGroupId");
			long[] layoutIds = getLayoutIds(actionRequest);

			exportLayoutSettingsMap =
				ExportImportConfigurationSettingsMapFactory.
					buildExportLayoutSettingsMap(
						themeDisplay.getUserId(), groupId, privateLayout,
						layoutIds, actionRequest.getParameterMap(),
						themeDisplay.getLocale(), themeDisplay.getTimeZone());
		}

		String taskName = ParamUtil.getString(actionRequest, "name");

		if (Validator.isNull(taskName)) {
			if (privateLayout) {
				taskName = LanguageUtil.get(
					actionRequest.getLocale(), "private-pages");
			}
			else {
				taskName = LanguageUtil.get(
					actionRequest.getLocale(), "public-pages");
			}
		}

		return _exportImportConfigurationLocalService.
			addDraftExportImportConfiguration(
				themeDisplay.getUserId(), taskName,
				ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT,
				exportLayoutSettingsMap);
	}

	protected long[] getLayoutIds(PortletRequest portletRequest)
		throws Exception {

		Set<Layout> layouts = new LinkedHashSet<>();

		Map<Long, Boolean> layoutIdMap = ExportImportHelperUtil.getLayoutIdMap(
			portletRequest);

		for (Map.Entry<Long, Boolean> entry : layoutIdMap.entrySet()) {
			long plid = GetterUtil.getLong(String.valueOf(entry.getKey()));
			boolean includeChildren = entry.getValue();

			Layout layout = _layoutLocalService.getLayout(plid);

			if (!layouts.contains(layout)) {
				layouts.add(layout);
			}

			if (includeChildren) {
				layouts.addAll(layout.getAllChildren());
			}
		}

		return ExportImportHelperUtil.getLayoutIds(
			new ArrayList<Layout>(layouts));
	}

	@Reference(unbind = "-")
	protected void setExportImportConfigurationLocalService(
		ExportImportConfigurationLocalService
			exportImportConfigurationLocalService) {

		_exportImportConfigurationLocalService =
			exportImportConfigurationLocalService;
	}

	@Reference(unbind = "-")
	protected void setExportImportService(
		ExportImportService exportImportService) {

		_exportImportService = exportImportService;
	}

	protected void setLayoutIdMap(ActionRequest actionRequest) {
		HttpServletRequest portletRequest = PortalUtil.getHttpServletRequest(
			actionRequest);

		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		boolean privateLayout = ParamUtil.getBoolean(
			actionRequest, "privateLayout");

		String treeId = ParamUtil.getString(actionRequest, "treeId");

		String openNodes = SessionTreeJSClicks.getOpenNodes(
			portletRequest, treeId + "SelectedNode");

		String selectedLayoutsJSON =
			ExportImportHelperUtil.getSelectedLayoutsJSON(
				groupId, privateLayout, openNodes);

		actionRequest.setAttribute("layoutIdMap", selectedLayoutsJSON);
	}

	@Reference(unbind = "-")
	protected void setLayoutLocalService(
		LayoutLocalService layoutLocalService) {

		_layoutLocalService = layoutLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ExportLayoutsMVCActionCommand.class);

	private ExportImportConfigurationLocalService
		_exportImportConfigurationLocalService;
	private ExportImportService _exportImportService;
	private LayoutLocalService _layoutLocalService;

}