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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.ratings.kernel.model.RatingsStats;
import com.liferay.ratings.kernel.service.RatingsStatsLocalServiceUtil;

import java.util.Date;
import java.util.Locale;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Charles May
 * @author Brian Wing Shun Chan
 */
public abstract class HitsOpenSearchImpl extends BaseOpenSearchImpl {

	public Indexer<?> getIndexer() {
		if (_log.isWarnEnabled()) {
			_log.warn(getClass() + " does not implement getIndexer()");
		}

		return null;
	}

	public abstract String getSearchPath();

	public Summary getSummary(
			Indexer<?> indexer, Document document, Locale locale,
			String snippet)
		throws SearchException {

		return indexer.getSummary(document, snippet, null, null);
	}

	public abstract String getTitle(String keywords);

	@Override
	public String search(
			HttpServletRequest request, long groupId, long userId,
			String keywords, int startPage, int itemsPerPage, String format)
		throws SearchException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			SearchContext searchContext = SearchContextFactory.getInstance(
				request);

			searchContext.setAttribute("paginationType", "more");

			if (groupId == 0) {
				searchContext.setGroupIds(null);
			}
			else {
				searchContext.setGroupIds(new long[] {groupId});
			}

			int end = startPage * itemsPerPage;

			searchContext.setEnd(end);

			Layout layout = themeDisplay.getLayout();

			Group layoutGroup = layout.getGroup();

			if (!layoutGroup.isStagingGroup() &&
				!layoutGroup.isControlPanel()) {

				searchContext.setIncludeStagingGroups(false);
			}

			searchContext.setKeywords(keywords);
			searchContext.setScopeStrict(false);

			int start = (startPage * itemsPerPage) - itemsPerPage;

			searchContext.setStart(start);

			searchContext.setUserId(userId);

			Indexer<?> indexer = getIndexer();

			Hits results = indexer.search(searchContext);

			String[] queryTerms = results.getQueryTerms();

			int total = results.getLength();

			Object[] values = addSearchResults(
				queryTerms, keywords, startPage, itemsPerPage, total, start,
				getTitle(keywords), getSearchPath(), format, themeDisplay);

			com.liferay.portal.kernel.xml.Document doc =
				(com.liferay.portal.kernel.xml.Document)values[0];
			Element root = (Element)values[1];

			for (int i = 0; i < results.getDocs().length; i++) {
				Document result = results.doc(i);

				String snippet = result.get(Field.SNIPPET);

				long resultGroupId = GetterUtil.getLong(
					result.get(Field.GROUP_ID));

				if (resultGroupId == 0) {
					resultGroupId = themeDisplay.getScopeGroupId();
				}

				long resultScopeGroupId = GetterUtil.getLong(
					result.get(Field.SCOPE_GROUP_ID));

				if (resultScopeGroupId == 0) {
					resultScopeGroupId = themeDisplay.getScopeGroupId();
				}

				String className = indexer.getClassName();

				if (Validator.isNull(className)) {
					className = result.get(Field.ENTRY_CLASS_NAME);
				}

				PortletURL portletURL = getPortletURL(
					request, className, PortletProvider.Action.VIEW,
					resultScopeGroupId);

				Summary summary = getSummary(
					indexer, result, themeDisplay.getLocale(), snippet);

				String title = summary.getTitle();
				String url = getURL(
					themeDisplay, resultScopeGroupId, result, portletURL);
				Date modifiedDate = result.getDate(Field.MODIFIED_DATE);
				String content = summary.getContent();

				String[] tags = new String[0];

				Field assetTagNamesField = result.getFields().get(
					Field.ASSET_TAG_NAMES);

				if (assetTagNamesField != null) {
					tags = assetTagNamesField.getValues();
				}

				double ratings = 0.0;

				String entryClassName = result.get(Field.ENTRY_CLASS_NAME);
				long entryClassPK = GetterUtil.getLong(
					result.get(Field.ENTRY_CLASS_PK));

				if (Validator.isNotNull(entryClassName) && (entryClassPK > 0)) {
					RatingsStats stats = RatingsStatsLocalServiceUtil.getStats(
						entryClassName, entryClassPK);

					ratings = stats.getTotalScore();
				}

				double score = results.score(i);

				addSearchResult(
					root, resultGroupId, resultScopeGroupId, entryClassName,
					entryClassPK, title, url, modifiedDate, content, tags,
					ratings, score, format);
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Return\n" + doc.asXML());
			}

			return doc.asXML();
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	protected String getURL(
			ThemeDisplay themeDisplay, long groupId, Document result,
			PortletURL portletURL)
		throws Exception {

		return portletURL.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		HitsOpenSearchImpl.class);

}