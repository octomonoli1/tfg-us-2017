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

package com.liferay.taglib.ui;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.Renderer;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

/**
 * @author Julio Camarero
 */
public class AssetDisplayTag extends IncludeTag {

	@Override
	public int doEndTag() throws JspException {
		try {
			callSetAttributes();

			doInclude(null, false);

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
		finally {
			clearDynamicAttributes();
			clearParams();
			clearProperties();

			cleanUpSetAttributes();

			if (!ServerDetector.isResin()) {
				setPage(null);
				setUseCustomPage(true);

				cleanUp();
			}
		}
	}

	public int getAbstractLength() {
		return _abstractLength;
	}

	public AssetEntry getAssetEntry() {
		return _assetEntry;
	}

	public AssetRendererFactory<?> getAssetRendererFactory() {
		return _assetRendererFactory;
	}

	public String getClassName() {
		return _className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public Renderer getRenderer() {
		return _renderer;
	}

	public String getTemplate() {
		return _template;
	}

	public String getViewURL() {
		return _viewURL;
	}

	public boolean isShowComments() {
		return _showComments;
	}

	public boolean isShowExtraInfo() {
		return _showExtraInfo;
	}

	public boolean isShowHeader() {
		return _showHeader;
	}

	public void setAbstractLength(int abstractLength) {
		_abstractLength = abstractLength;
	}

	public void setAssetEntry(AssetEntry assetEntry) {
		_assetEntry = assetEntry;
	}

	public void setAssetRenderer(AssetRenderer<?> assetRenderer) {
		_renderer = assetRenderer;
	}

	public void setAssetRendererFactory(
		AssetRendererFactory<?> assetRendererFactory) {

		_assetRendererFactory = assetRendererFactory;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setRenderer(Renderer renderer) {
		_renderer = renderer;
	}

	public void setShowComments(boolean showComments) {
		_showComments = showComments;
	}

	public void setShowExtraInfo(boolean showExtraInfo) {
		_showExtraInfo = showExtraInfo;
	}

	public void setShowHeader(boolean showHeader) {
		_showHeader = showHeader;
	}

	public void setTemplate(String template) {
		_template = template;
	}

	public void setViewURL(String viewURL) {
		_viewURL = viewURL;
	}

	@Override
	protected void cleanUp() {
		_abstractLength = 200;
		_assetEntry = null;
		_className = null;
		_classPK = 0;
		_page = null;
		_renderer = null;
		_showComments = false;
		_showExtraInfo = false;
		_showHeader = false;
		_template = AssetRenderer.TEMPLATE_FULL_CONTENT;
		_viewURL = null;
	}

	@Override
	protected String getPage() {
		return _page;
	}

	@Override
	protected void includePage(String page, HttpServletResponse response)
		throws IOException, ServletException {

		try {
			boolean include = _renderer.include(request, response, _template);

			if (include) {
				return;
			}
		}
		catch (Exception e) {
			_log.error(e);
		}

		super.includePage(
			"/html/taglib/ui/asset_display/" + _template + ".jsp", response);
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:asset-display:abstractLength", _abstractLength);

		AssetEntry assetEntry = _assetEntry;

		if (assetEntry == null) {
			if (Validator.isNotNull(_className) && (_classPK > 0)) {
				assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
					_className, _classPK);
			}
			else if (_renderer != null) {
				assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
					_renderer.getClassName(), _renderer.getClassPK());
			}
		}

		request.setAttribute("liferay-ui:asset-display:assetEntry", assetEntry);

		if ((_renderer == null) && (assetEntry != null)) {
			_renderer = assetEntry.getAssetRenderer();
		}

		if (_renderer instanceof AssetRenderer) {
			AssetRenderer<?> assetRenderer = (AssetRenderer<?>)_renderer;

			request.setAttribute(WebKeys.ASSET_RENDERER, assetRenderer);
		}
		else {
			request.setAttribute(
				"liferay-ui:asset-display:renderer", _renderer);
		}

		AssetRendererFactory<?> assetRendererFactory = _assetRendererFactory;

		if ((assetRendererFactory == null) && (assetEntry != null)) {
			assetRendererFactory = assetEntry.getAssetRendererFactory();
		}

		if (assetRendererFactory != null) {
			request.setAttribute(
				WebKeys.ASSET_RENDERER_FACTORY, assetRendererFactory);
		}

		request.setAttribute(WebKeys.ASSET_ENTRY_VIEW_URL, _viewURL);

		addParam("showComments", String.valueOf(_showComments));
		addParam("showExtraInfo", String.valueOf(_showExtraInfo));
		addParam("showHeader", String.valueOf(_showHeader));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetDisplayTag.class);

	private int _abstractLength = 200;
	private AssetEntry _assetEntry;
	private AssetRendererFactory<?> _assetRendererFactory;
	private String _className;
	private long _classPK;
	private String _page;
	private Renderer _renderer;
	private boolean _showComments;
	private boolean _showExtraInfo;
	private boolean _showHeader;
	private String _template = AssetRenderer.TEMPLATE_FULL_CONTENT;
	private String _viewURL;

}