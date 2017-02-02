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

package com.liferay.portlet.usersadmin.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;

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
public class UserSearch extends SearchContainer<User> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-users-were-found";

	public static List<String> headerNames = new ArrayList<>();
	public static Map<String, String> orderableHeaders = new HashMap<>();

	static {
		headerNames.add("first-name");
		headerNames.add("last-name");
		headerNames.add("screen-name");
		//headerNames.add("email-address");
		headerNames.add("job-title");
		headerNames.add("organizations");

		orderableHeaders.put("first-name", "first-name");
		orderableHeaders.put("last-name", "last-name");
		orderableHeaders.put("screen-name", "screen-name");
		//orderableHeaders.put("email-address", "email-address");
		orderableHeaders.put("job-title", "job-title");
	}

	public UserSearch(PortletRequest portletRequest, PortletURL iteratorURL) {
		this(portletRequest, DEFAULT_CUR_PARAM, iteratorURL);
	}

	public UserSearch(
		PortletRequest portletRequest, String curParam,
		PortletURL iteratorURL) {

		super(
			portletRequest, new UserDisplayTerms(portletRequest),
			new UserSearchTerms(portletRequest), curParam, DEFAULT_DELTA,
			iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		PortletConfig portletConfig =
			(PortletConfig)portletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_CONFIG);

		UserDisplayTerms displayTerms = (UserDisplayTerms)getDisplayTerms();
		UserSearchTerms searchTerms = (UserSearchTerms)getSearchTerms();

		String portletId = PortletProviderUtil.getPortletId(
			User.class.getName(), PortletProvider.Action.VIEW);
		String portletName = portletConfig.getPortletName();

		if (!portletId.equals(portletName)) {
			displayTerms.setStatus(WorkflowConstants.STATUS_APPROVED);
			searchTerms.setStatus(WorkflowConstants.STATUS_APPROVED);
		}

		iteratorURL.setParameter(
			UserDisplayTerms.STATUS, String.valueOf(displayTerms.getStatus()));

		iteratorURL.setParameter(
			UserDisplayTerms.EMAIL_ADDRESS, displayTerms.getEmailAddress());
		iteratorURL.setParameter(
			UserDisplayTerms.FIRST_NAME, displayTerms.getFirstName());
		iteratorURL.setParameter(
			UserDisplayTerms.LAST_NAME, displayTerms.getLastName());
		iteratorURL.setParameter(
			UserDisplayTerms.MIDDLE_NAME, displayTerms.getMiddleName());
		iteratorURL.setParameter(
			UserDisplayTerms.ORGANIZATION_ID,
			String.valueOf(displayTerms.getOrganizationId()));
		iteratorURL.setParameter(
			UserDisplayTerms.ROLE_ID, String.valueOf(displayTerms.getRoleId()));
		iteratorURL.setParameter(
			UserDisplayTerms.SCREEN_NAME, displayTerms.getScreenName());
		iteratorURL.setParameter(
			UserDisplayTerms.USER_GROUP_ID,
			String.valueOf(displayTerms.getUserGroupId()));

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
					portletId, "users-order-by-col", orderByCol);
				preferences.setValue(
					portletId, "users-order-by-type", orderByType);
			}
			else {
				orderByCol = preferences.getValue(
					portletId, "users-order-by-col", "last-name");
				orderByType = preferences.getValue(
					portletId, "users-order-by-type", "asc");
			}

			OrderByComparator<User> orderByComparator =
				UsersAdminUtil.getUserOrderByComparator(
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

	private static final Log _log = LogFactoryUtil.getLog(UserSearch.class);

}