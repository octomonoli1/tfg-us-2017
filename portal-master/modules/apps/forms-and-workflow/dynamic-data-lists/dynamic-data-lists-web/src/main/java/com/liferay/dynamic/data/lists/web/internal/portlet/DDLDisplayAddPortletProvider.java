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

package com.liferay.dynamic.data.lists.web.internal.portlet;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.dynamic.data.lists.constants.DDLPortletKeys;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.AddPortletProvider;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(
	immediate = true,
	property = {"model.class.name=com.liferay.dynamic.data.lists.model.DDLRecord"},
	service = AddPortletProvider.class
)
public class DDLDisplayAddPortletProvider
	extends BasePortletProvider implements AddPortletProvider {

	@Override
	public String getPortletName() {
		return DDLPortletKeys.DYNAMIC_DATA_LISTS_DISPLAY;
	}

	@Override
	public PortletURL getPortletURL(HttpServletRequest request, Group group)
		throws PortalException {

		return PortletURLFactoryUtil.create(
			request, getPortletName(), PortletRequest.RENDER_PHASE);
	}

	@Override
	public void updatePortletPreferences(
			PortletPreferences portletPreferences, String portletId,
			String className, long classPK, ThemeDisplay themeDisplay)
		throws Exception {

		AssetEntry assetEntry = _assetEntryLocalService.getEntry(
			className, classPK);

		AssetRendererFactory<DDLRecord> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClass(
				DDLRecord.class);

		AssetRenderer<DDLRecord> assetRenderer =
			assetRendererFactory.getAssetRenderer(assetEntry.getClassPK());

		DDLRecord record = assetRenderer.getAssetObject();

		portletPreferences.setValue(
			"recordSetId", String.valueOf(record.getRecordSetId()));
	}

	/**
	 * @deprecated As of 7.0.0
	 */
	@Deprecated
	@Override
	protected long getPlid(ThemeDisplay themeDisplay) {
		return themeDisplay.getPlid();
	}

	@Reference(unbind = "-")
	protected void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {

		_assetEntryLocalService = assetEntryLocalService;
	}

	private AssetEntryLocalService _assetEntryLocalService;

}