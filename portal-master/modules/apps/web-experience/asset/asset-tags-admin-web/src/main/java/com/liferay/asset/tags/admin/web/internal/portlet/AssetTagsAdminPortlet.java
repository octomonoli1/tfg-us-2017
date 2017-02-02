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

package com.liferay.asset.tags.admin.web.internal.portlet;

import com.liferay.asset.kernel.exception.AssetTagException;
import com.liferay.asset.kernel.exception.DuplicateTagException;
import com.liferay.asset.kernel.exception.NoSuchTagException;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.asset.kernel.service.AssetTagService;
import com.liferay.asset.tags.admin.web.internal.constants.AssetTagsAdminPortletKeys;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-asset-tag-admin",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.icon=/icons/asset_tag_admin.png",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Asset Tag Admin",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AssetTagsAdminPortletKeys.ASSET_TAGS_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class AssetTagsAdminPortlet extends MVCPortlet {

	public void changeDisplayStyle(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		hideDefaultSuccessMessage(actionRequest);

		String displayStyle = ParamUtil.getString(
			actionRequest, "displayStyle");

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(actionRequest);

		portalPreferences.setValue(
			AssetTagsAdminPortletKeys.ASSET_TAGS_ADMIN, "display-style",
			displayStyle);
	}

	public void deleteTag(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] deleteTagIds = null;

		long tagId = ParamUtil.getLong(actionRequest, "tagId");

		if (tagId > 0) {
			deleteTagIds = new long[] {tagId};
		}
		else {
			deleteTagIds = ParamUtil.getLongValues(actionRequest, "rowIds");
		}

		for (long deleteTagId : deleteTagIds) {
			_assetTagService.deleteTag(deleteTagId);
		}
	}

	public void editTag(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long tagId = ParamUtil.getLong(actionRequest, "tagId");

		String name = ParamUtil.getString(actionRequest, "name");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			AssetTag.class.getName(), actionRequest);

		if (tagId <= 0) {

			// Add tag

			_assetTagService.addTag(
				serviceContext.getScopeGroupId(), name, serviceContext);
		}
		else {

			// Update tag

			_assetTagService.updateTag(tagId, name, serviceContext);
		}
	}

	public void mergeTag(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		String targetTagName = ParamUtil.getString(
			actionRequest, "targetTagName");

		AssetTag targetTag = _assetTagLocalService.fetchTag(
			groupId, targetTagName);

		if (targetTag == null) {
			return;
		}

		String[] mergeTagNames = StringUtil.split(
			ParamUtil.getString(actionRequest, "mergeTagNames"));

		for (String mergeTagName : mergeTagNames) {
			if (targetTagName.equals(mergeTagName)) {
				continue;
			}

			AssetTag mergeTag = _assetTagLocalService.fetchTag(
				groupId, mergeTagName);

			if (mergeTag == null) {
				continue;
			}

			_assetTagService.mergeTags(
				mergeTag.getTagId(), targetTag.getTagId());
		}
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (SessionErrors.contains(
				renderRequest, NoSuchTagException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, PrincipalException.getNestedClasses())) {

			include("/error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof AssetTagException ||
			cause instanceof DuplicateTagException ||
			cause instanceof NoSuchTagException ||
			cause instanceof PrincipalException) {

			return true;
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setAssetTagLocalService(
		AssetTagLocalService assetTagLocalService) {

		_assetTagLocalService = assetTagLocalService;
	}

	@Reference(unbind = "-")
	protected void setAssetTagService(AssetTagService assetTagService) {
		_assetTagService = assetTagService;
	}

	private AssetTagLocalService _assetTagLocalService;
	private AssetTagService _assetTagService;

}