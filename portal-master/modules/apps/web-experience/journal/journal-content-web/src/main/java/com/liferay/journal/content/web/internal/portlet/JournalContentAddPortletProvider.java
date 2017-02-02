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

package com.liferay.journal.content.web.internal.portlet;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.journal.content.web.constants.JournalContentPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalContentSearchLocalService;
import com.liferay.journal.web.asset.JournalArticleAssetRenderer;
import com.liferay.journal.web.asset.JournalArticleAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
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
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {"model.class.name=com.liferay.journal.model.JournalArticle"},
	service = AddPortletProvider.class
)
public class JournalContentAddPortletProvider
	extends BasePortletProvider implements AddPortletProvider {

	@Override
	public String getPortletName() {
		return JournalContentPortletKeys.JOURNAL_CONTENT;
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

		JournalArticleAssetRendererFactory articleAssetRendererFactory =
			(JournalArticleAssetRendererFactory)
				AssetRendererFactoryRegistryUtil.
					getAssetRendererFactoryByClassName(
						JournalArticle.class.getName());

		JournalArticleAssetRenderer articleAssetRenderer =
			(JournalArticleAssetRenderer)articleAssetRendererFactory.
				getAssetRenderer(assetEntry.getClassPK());

		JournalArticle article = articleAssetRenderer.getArticle();

		portletPreferences.setValue("articleId", article.getArticleId());
		portletPreferences.setValue(
			"groupId", String.valueOf(article.getGroupId()));

		Layout layout = themeDisplay.getLayout();

		_journalContentSearchLocal.updateContentSearch(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			portletId, article.getArticleId(), true);
	}

	/**
	 * @deprecated As of 7.0.0
	 */
	@Deprecated
	@Override
	protected long getPlid(ThemeDisplay themeDisplay) {
		return themeDisplay.getPlid();
	}

	@Reference
	protected void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {

		_assetEntryLocalService = assetEntryLocalService;
	}

	@Reference
	protected void setJournalContentSearchLocal(
		JournalContentSearchLocalService journalContentSearchLocal) {

		_journalContentSearchLocal = journalContentSearchLocal;
	}

	protected void unsetAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {

		_assetEntryLocalService = null;
	}

	protected void unsetJournalContentSearchLocal(
		JournalContentSearchLocalService journalContentSearchLocal) {

		_journalContentSearchLocal = null;
	}

	private AssetEntryLocalService _assetEntryLocalService;
	private JournalContentSearchLocalService _journalContentSearchLocal;

}