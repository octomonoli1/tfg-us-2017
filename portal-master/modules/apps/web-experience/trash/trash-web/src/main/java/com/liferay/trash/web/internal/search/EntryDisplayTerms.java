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

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Sergio González
 */
public class EntryDisplayTerms extends DisplayTerms {

	public static final String NAME = "name";

	public static final String REMOVED_BY = "removedBy";

	public static final String REMOVED_DATE = "removedDate";

	public static final String TYPE = "type";

	public EntryDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		name = ParamUtil.getString(portletRequest, NAME);
		removedDate = ParamUtil.getString(portletRequest, REMOVED_DATE);
		removedBy = ParamUtil.getString(portletRequest, REMOVED_BY);
		type = ParamUtil.getString(portletRequest, TYPE);
	}

	public String getName() {
		return name;
	}

	public String getRemovedBy() {
		return removedBy;
	}

	public String getRemovedDate() {
		return removedDate;
	}

	public String getType() {
		return type;
	}

	protected String name;
	protected String removedBy;
	protected String removedDate;
	protected String type;

}