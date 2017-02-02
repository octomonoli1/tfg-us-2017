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

package com.liferay.trash.web.internal.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.trash.kernel.model.TrashEntry;
import com.liferay.trash.kernel.util.TrashUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Sergio González
 */
public class EntrySearch extends SearchContainer<TrashEntry> {

	public static final String EMPTY_RESULTS_MESSAGE =
		"the-recycle-bin-is-empty";

	public static List<String> headerNames = new ArrayList<>();
	public static Map<String, String> orderableHeaders = new HashMap<>();

	static {
		headerNames.add("name");
		headerNames.add("type");
		headerNames.add("removed-date");
		headerNames.add("removed-by");

		orderableHeaders.put("name", "name");
		orderableHeaders.put("type", "type");
		orderableHeaders.put("removed-date", "removed-date");
		orderableHeaders.put("removed-by", "removed-by");
	}

	public EntrySearch(PortletRequest portletRequest, PortletURL iteratorURL) {
		super(
			portletRequest, new EntryDisplayTerms(portletRequest),
			new EntrySearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		try {
			PortalPreferences preferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					portletRequest);

			String portletId = PortletProviderUtil.getPortletId(
				User.class.getName(), PortletProvider.Action.VIEW);

			String orderByCol = ParamUtil.getString(
				portletRequest, "orderByCol");
			String orderByType = ParamUtil.getString(
				portletRequest, "orderByType");

			if (Validator.isNotNull(orderByCol) &&
				Validator.isNotNull(orderByType)) {

				preferences.setValue(
					portletId, "entries-order-by-col", orderByCol);
				preferences.setValue(
					portletId, "entries-order-by-type", orderByType);
			}
			else {
				orderByCol = preferences.getValue(
					portletId, "entries-order-by-col", "removed-date");
				orderByType = preferences.getValue(
					portletId, "entries-order-by-type", "asc");
			}

			OrderByComparator<TrashEntry> orderByComparator =
				TrashUtil.getEntryOrderByComparator(orderByCol, orderByType);

			setOrderableHeaders(orderableHeaders);
			setOrderByCol(orderByCol);
			setOrderByType(orderByType);
			setOrderByComparator(orderByComparator);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(EntrySearch.class);

}