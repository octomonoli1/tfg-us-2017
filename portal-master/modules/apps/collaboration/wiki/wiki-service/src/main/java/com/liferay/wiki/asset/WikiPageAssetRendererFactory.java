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

package com.liferay.wiki.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.engine.impl.WikiEngineRenderer;
import com.liferay.wiki.exception.NoSuchPageException;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageLocalService;
import com.liferay.wiki.service.WikiPageResourceLocalService;
import com.liferay.wiki.service.permission.WikiPagePermissionChecker;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 * @author Juan Fernández
 * @author Jorge Ferrer
 * @author Raymond Augé
 * @author Sergio González
 */
@Component(
	immediate = true, property = {"javax.portlet.name=" + WikiPortletKeys.WIKI},
	service = AssetRendererFactory.class
)
public class WikiPageAssetRendererFactory
	extends BaseAssetRendererFactory<WikiPage> {

	public static final String TYPE = "wiki";

	public WikiPageAssetRendererFactory() {
		setClassName(WikiPage.class.getName());
		setLinkable(true);
		setPortletId(WikiPortletKeys.WIKI);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<WikiPage> getAssetRenderer(long classPK, int type)
		throws PortalException {

		WikiPage page = _wikiPageLocalService.fetchWikiPage(classPK);

		if (page == null) {
			if (type == TYPE_LATEST_APPROVED) {
				try {
					page = _wikiPageLocalService.getPage(classPK, Boolean.TRUE);
				}
				catch (NoSuchPageException nspe) {
					page = _wikiPageLocalService.getPage(
						classPK, (Boolean)null);
				}
			}
			else if (type == TYPE_LATEST) {
				page = _wikiPageLocalService.getPage(classPK, (Boolean)null);
			}
			else {
				throw new IllegalArgumentException(
					"Unknown asset renderer type " + type);
			}
		}

		WikiPageAssetRenderer wikiPageAssetRenderer = new WikiPageAssetRenderer(
			page, _wikiEngineRenderer);

		wikiPageAssetRenderer.setAssetRendererType(type);
		wikiPageAssetRenderer.setServletContext(_servletContext);

		return wikiPageAssetRenderer;
	}

	@Override
	public String getClassName() {
		return WikiPage.class.getName();
	}

	@Override
	public String getIconCssClass() {
		return "wiki";
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public PortletURL getURLView(
		LiferayPortletResponse liferayPortletResponse,
		WindowState windowState) {

		LiferayPortletURL liferayPortletURL =
			liferayPortletResponse.createLiferayPortletURL(
				WikiPortletKeys.WIKI, PortletRequest.RENDER_PHASE);

		try {
			liferayPortletURL.setWindowState(windowState);
		}
		catch (WindowStateException wse) {
		}

		return liferayPortletURL;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return WikiPagePermissionChecker.contains(
			permissionChecker, classPK, actionId);
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.wiki.web)", unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Reference(unbind = "-")
	protected void setWikiEngineRenderer(
		WikiEngineRenderer wikiEngineRenderer) {

		_wikiEngineRenderer = wikiEngineRenderer;
	}

	@Reference(unbind = "-")
	protected void setWikiPageLocalService(
		WikiPageLocalService wikiPageLocalService) {

		_wikiPageLocalService = wikiPageLocalService;
	}

	@Reference(unbind = "-")
	protected void setWikiPageResourceLocalService(
		WikiPageResourceLocalService wikiPageResourceLocalService) {

		_wikiPageResourceLocalService = wikiPageResourceLocalService;
	}

	private ServletContext _servletContext;
	private WikiEngineRenderer _wikiEngineRenderer;
	private WikiPageLocalService _wikiPageLocalService;
	private WikiPageResourceLocalService _wikiPageResourceLocalService;

}