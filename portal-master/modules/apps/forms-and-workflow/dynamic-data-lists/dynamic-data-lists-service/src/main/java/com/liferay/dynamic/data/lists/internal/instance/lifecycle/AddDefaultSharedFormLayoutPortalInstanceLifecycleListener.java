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

package com.liferay.dynamic.data.lists.internal.instance.lifecycle;

import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Leonardo Barros
 */
@Component(immediate = true, service = PortalInstanceLifecycleListener.class)
public class AddDefaultSharedFormLayoutPortalInstanceLifecycleListener
	extends BasePortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		Group group = _groupLocalService.fetchFriendlyURLGroup(
			company.getCompanyId(), "/forms");

		if (group == null) {
			group = addFormsGroup(company.getCompanyId());
		}

		Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
			group.getGroupId(), false, "/shared");

		if (layout == null) {
			addSharedLayout(company.getCompanyId(), group.getGroupId());
		}
	}

	protected Group addFormsGroup(long companyId) throws PortalException {
		long defaultUserId = _userLocalService.getDefaultUserId(companyId);

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(LocaleUtil.getDefault(), GroupConstants.FORMS);

		return _groupLocalService.addGroup(
			defaultUserId, GroupConstants.DEFAULT_PARENT_GROUP_ID, null, 0,
			GroupConstants.DEFAULT_LIVE_GROUP_ID, nameMap, null,
			GroupConstants.TYPE_SITE_PRIVATE, true,
			GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION,
			GroupConstants.FORMS_FRIENDLY_URL, false, false, true, null);
	}

	protected void addSharedLayout(long companyId, long groupId)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGuestPermissions(true);
		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAttribute(
			"layout.instanceable.allowed", Boolean.TRUE);
		serviceContext.setAttribute("layoutUpdateable", Boolean.FALSE);

		serviceContext.setScopeGroupId(groupId);

		long defaultUserId = _userLocalService.getDefaultUserId(companyId);

		serviceContext.setUserId(defaultUserId);

		_layoutLocalService.addLayout(
			defaultUserId, groupId, false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Shared",
			StringPool.BLANK, StringPool.BLANK,
			LayoutConstants.TYPE_SHARED_PORTLET, true, "/shared",
			serviceContext);
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutLocalService(
		LayoutLocalService layoutLocalService) {

		_layoutLocalService = layoutLocalService;
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private GroupLocalService _groupLocalService;
	private LayoutLocalService _layoutLocalService;
	private UserLocalService _userLocalService;

}