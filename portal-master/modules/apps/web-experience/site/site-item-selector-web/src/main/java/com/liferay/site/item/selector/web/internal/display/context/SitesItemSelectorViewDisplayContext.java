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

package com.liferay.site.item.selector.web.internal.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portlet.usersadmin.search.GroupSearch;
import com.liferay.site.item.selector.criterion.SiteItemSelectorCriterion;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

/**
 * @author Julio Camarero
 */
public interface SitesItemSelectorViewDisplayContext {

	public String getDisplayStyle();

	public String getGroupName(Group group) throws PortalException;

	public GroupSearch getGroupSearch() throws Exception;

	public String getItemSelectedEventName();

	public PortletRequest getPortletRequest();

	public PortletResponse getPortletResponse();

	public PortletURL getPortletURL() throws PortletException;

	public SiteItemSelectorCriterion getSiteItemSelectorCriterion();

	public boolean isShowChildSitesLink();

	public boolean isShowSortFilter();

}