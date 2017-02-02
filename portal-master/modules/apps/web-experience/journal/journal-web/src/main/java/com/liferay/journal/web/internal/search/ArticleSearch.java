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

package com.liferay.journal.web.internal.search;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.web.util.JournalPortletUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class ArticleSearch extends SearchContainer<JournalArticle> {

	public static List<String> headerNames = new ArrayList<>();
	public static Map<String, String> orderableHeaders = new HashMap<>();

	static {
		headerNames.add("id");
		headerNames.add("title");
		headerNames.add("status");
		//headerNames.add("version");
		headerNames.add("modified-date");
		headerNames.add("display-date");
		headerNames.add("author");
		headerNames.add("structure");
		headerNames.add(StringPool.BLANK);

		//orderableHeaders.put("id", "id");
		//orderableHeaders.put("title", "title");
		//orderableHeaders.put("version", "version");
		orderableHeaders.put("modified-date", "modified-date");
		orderableHeaders.put("display-date", "display-date");
	}

	public ArticleSearch(
		PortletRequest portletRequest, int cur, int delta,
		PortletURL iteratorURL) {

		super(
			portletRequest, new ArticleDisplayTerms(portletRequest),
			new ArticleSearchTerms(portletRequest), "curEntry", cur, delta,
			iteratorURL, headerNames, null);

		PortletConfig portletConfig =
			(PortletConfig)portletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_CONFIG);

		ArticleDisplayTerms displayTerms =
			(ArticleDisplayTerms)getDisplayTerms();
		ArticleSearchTerms searchTerms = (ArticleSearchTerms)getSearchTerms();

		String portletName = portletConfig.getPortletName();

		if (!portletName.equals(JournalPortletKeys.JOURNAL)) {
			displayTerms.setStatus(WorkflowConstants.STATUS_APPROVED);
			searchTerms.setStatus(WorkflowConstants.STATUS_APPROVED);
		}

		iteratorURL.setParameter(
			ArticleDisplayTerms.ARTICLE_ID, displayTerms.getArticleId());
		iteratorURL.setParameter(
			ArticleDisplayTerms.CONTENT, displayTerms.getContent());
		iteratorURL.setParameter(
			ArticleDisplayTerms.DESCRIPTION, displayTerms.getDescription());
		iteratorURL.setParameter(
			ArticleDisplayTerms.DDM_STRUCTURE_KEY,
			displayTerms.getDDMStructureKey());
		iteratorURL.setParameter(
			ArticleDisplayTerms.DDM_TEMPLATE_KEY,
			displayTerms.getDDMTemplateKey());
		iteratorURL.setParameter(
			ArticleDisplayTerms.FOLDER_ID,
			String.valueOf(displayTerms.getFolderId()));
		iteratorURL.setParameter(
			ArticleDisplayTerms.GROUP_ID,
			String.valueOf(displayTerms.getGroupId()));
		iteratorURL.setParameter(
			ArticleDisplayTerms.NAVIGATION, displayTerms.getNavigation());
		iteratorURL.setParameter(
			ArticleDisplayTerms.STATUS,
			String.valueOf(displayTerms.getStatus()));
		iteratorURL.setParameter(
			ArticleDisplayTerms.TITLE, displayTerms.getTitle());
		iteratorURL.setParameter(
			ArticleDisplayTerms.VERSION,
			String.valueOf(displayTerms.getVersion()));

		try {
			PortalPreferences preferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					portletRequest);

			String orderByCol = ParamUtil.getString(
				portletRequest, "orderByCol");
			String orderByType = ParamUtil.getString(
				portletRequest, "orderByType");

			if (Validator.isNotNull(orderByCol) &&
				Validator.isNotNull(orderByType)) {

				preferences.setValue(
					JournalPortletKeys.JOURNAL, "articles-order-by-col",
					orderByCol);
				preferences.setValue(
					JournalPortletKeys.JOURNAL, "articles-order-by-type",
					orderByType);
			}
			else {
				orderByCol = preferences.getValue(
					JournalPortletKeys.JOURNAL, "articles-order-by-col", "id");
				orderByType = preferences.getValue(
					JournalPortletKeys.JOURNAL, "articles-order-by-type",
					"asc");
			}

			OrderByComparator<JournalArticle> orderByComparator =
				JournalPortletUtil.getArticleOrderByComparator(
					orderByCol, orderByType);

			setOrderableHeaders(orderableHeaders);
			setOrderByCol(orderByCol);
			setOrderByType(orderByType);
			setOrderByComparator(orderByComparator);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	public ArticleSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		this(portletRequest, 0, DEFAULT_DELTA, iteratorURL);
	}

	private static final Log _log = LogFactoryUtil.getLog(ArticleSearch.class);

}