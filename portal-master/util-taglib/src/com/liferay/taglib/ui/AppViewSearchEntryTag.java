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

import com.liferay.portal.kernel.comment.Comment;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.RelatedSearchResult;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.taglib.util.IncludeTag;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 * @author Tibor Lipusz
 */
public class AppViewSearchEntryTag extends IncludeTag {

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public void setActionJsp(String actionJsp) {
		_actionJsp = actionJsp;
	}

	public void setActionJspServletContext(
		ServletContext actionJspServletContext) {

		_actionJspServletContext = actionJspServletContext;
	}

	public void setCommentRelatedSearchResults(
		List<RelatedSearchResult<Comment>> commentRelatedSearchResults) {

		_commentRelatedSearchResults = commentRelatedSearchResults;
	}

	public void setContainerName(String containerName) {
		_containerName = containerName;
	}

	public void setContainerType(String containerType) {
		_containerType = containerType;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setEscape(boolean escape) {
		_escape = escape;
	}

	public void setFileEntryRelatedSearchResults(
		List<RelatedSearchResult<FileEntry>> fileEntryRelatedSearchResults) {

		_fileEntryRelatedSearchResults = fileEntryRelatedSearchResults;
	}

	public void setHighlightEnabled(boolean highlightEnabled) {
		_highlightEnabled = highlightEnabled;
	}

	public void setLocked(boolean locked) {
		_locked = locked;
	}

	public void setQueryTerms(String[] queryTerms) {
		_queryTerms = queryTerms;
	}

	public void setRowCheckerId(String rowCheckerId) {
		_rowCheckerId = rowCheckerId;
	}

	public void setRowCheckerName(String rowCheckerName) {
		_rowCheckerName = rowCheckerName;
	}

	public void setShowCheckbox(boolean showCheckbox) {
		_showCheckbox = showCheckbox;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public void setThumbnailSrc(String thumbnailSrc) {
		_thumbnailSrc = thumbnailSrc;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public void setVersions(List<String> versions) {
		_versions = versions;
	}

	@Override
	protected void cleanUp() {
		_actionJsp = null;
		_commentRelatedSearchResults = null;
		_containerName = null;
		_containerType = null;
		_cssClass = null;
		_description = null;
		_escape = true;
		_fileEntryRelatedSearchResults = null;
		_highlightEnabled = _HIGHLIGHT_ENABLED;
		_locked = false;
		_queryTerms = null;
		_rowCheckerId = null;
		_rowCheckerName = null;
		_showCheckbox = false;
		_status = 0;
		_thumbnailSrc = null;
		_title = null;
		_url = null;
		_versions = null;
	}

	protected ServletContext getActionJspServletContext() {
		if (_actionJspServletContext != null) {
			return _actionJspServletContext;
		}

		return servletContext;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:app-view-entry:actionJspServletContext",
			getActionJspServletContext());
		request.setAttribute(
			"liferay-ui:app-view-search-entry:actionJsp", _actionJsp);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:commentRelatedSearchResults",
			_commentRelatedSearchResults);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:containerName", _containerName);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:containerType", _containerType);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:cssClass", _cssClass);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:description", _description);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:escape", _escape);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:fileEntryRelatedSearchResults",
			_fileEntryRelatedSearchResults);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:highlightEnabled",
			_highlightEnabled);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:locked", _locked);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:queryTerms", _queryTerms);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:rowCheckerId", _rowCheckerId);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:rowCheckerName", _rowCheckerName);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:showCheckbox", _showCheckbox);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:status", _status);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:thumbnailSrc", _thumbnailSrc);
		request.setAttribute("liferay-ui:app-view-search-entry:title", _title);
		request.setAttribute("liferay-ui:app-view-search-entry:url", _url);
		request.setAttribute(
			"liferay-ui:app-view-search-entry:versions", _versions);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private static final boolean _HIGHLIGHT_ENABLED = GetterUtil.getBoolean(
		PropsUtil.get(PropsKeys.INDEX_SEARCH_HIGHLIGHT_ENABLED));

	private static final String _PAGE =
		"/html/taglib/ui/app_view_search_entry/page.jsp";

	private String _actionJsp;
	private ServletContext _actionJspServletContext;
	private List<RelatedSearchResult<Comment>> _commentRelatedSearchResults;
	private String _containerName;
	private String _containerType;
	private String _cssClass;
	private String _description;
	private boolean _escape = true;
	private List<RelatedSearchResult<FileEntry>> _fileEntryRelatedSearchResults;
	private boolean _highlightEnabled = _HIGHLIGHT_ENABLED;
	private boolean _locked;
	private String[] _queryTerms;
	private String _rowCheckerId;
	private String _rowCheckerName;
	private boolean _showCheckbox;
	private int _status;
	private String _thumbnailSrc;
	private String _title;
	private String _url;
	private List<String> _versions;

}