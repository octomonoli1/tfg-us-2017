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

package com.liferay.wiki.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.search.WikiPageTitleSearcher;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(
	property = {
		"javax.portlet.name=" + WikiPortletKeys.WIKI,
		"javax.portlet.name=" + WikiPortletKeys.WIKI_ADMIN,
		"javax.portlet.name=" + WikiPortletKeys.WIKI_DISPLAY,
		"mvc.command.name=/wiki/autocomplete_page_title"
	},
	service = MVCResourceCommand.class
)
public class AutocompletePageTitleMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	public void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			JSONArray jsonArray = getJSONArray(
				resourceRequest, resourceResponse);

			HttpServletResponse response = PortalUtil.getHttpServletResponse(
				resourceResponse);

			response.setContentType(ContentTypes.APPLICATION_JSON);

			ServletResponseUtil.write(response, jsonArray.toString());
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Reference(unbind = "-")
	public void setWikiPageTitleSearcher(
		WikiPageTitleSearcher wikiPageTitleSearcher) {

		_wikiPageTitleSearcher = wikiPageTitleSearcher;
	}

	protected JSONArray getJSONArray(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortalException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);

		SearchContext searchContext = SearchContextFactory.getInstance(request);

		searchContext.setEnd(20);

		String query = ParamUtil.getString(request, "query");

		searchContext.setKeywords(StringUtil.toLowerCase(query));

		long nodeId = ParamUtil.getLong(resourceRequest, "nodeId");

		searchContext.setNodeIds(new long[] {nodeId});

		searchContext.setStart(0);

		Hits hits = _wikiPageTitleSearcher.search(searchContext);

		for (Document document : hits.getDocs()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("title", document.get(Field.TITLE));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AutocompletePageTitleMVCResourceCommand.class);

	private WikiPageTitleSearcher _wikiPageTitleSearcher;

}