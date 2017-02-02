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

package com.liferay.portal.verify;

import com.liferay.portal.events.test.TestServicePreAction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourceLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.verify.test.BaseVerifyProcessTestCase;
import com.liferay.portlet.util.test.PortletKeys;

import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 * @author Preston Crary
 */
public class VerifyPermissionTest extends BaseVerifyProcessTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testFixUserDefaultRolePermissions() throws Exception {
		Layout layout = addPersonalLayoutWithDefaultResource();

		Role powerUserRole = RoleLocalServiceUtil.getRole(
			TestPropsValues.getCompanyId(), RoleConstants.POWER_USER);
		String primKey = PortletPermissionUtil.getPrimaryKey(
			layout.getPlid(), PortletKeys.TEST);

		ResourcePermission resourcePermission =
			ResourcePermissionLocalServiceUtil.fetchResourcePermission(
				TestPropsValues.getCompanyId(), PortletKeys.TEST,
				ResourceConstants.SCOPE_INDIVIDUAL, primKey,
				powerUserRole.getRoleId());

		Assert.assertNull(resourcePermission);

		Role userRole = RoleLocalServiceUtil.getRole(
			TestPropsValues.getCompanyId(), RoleConstants.USER);

		resourcePermission =
			ResourcePermissionLocalServiceUtil.getResourcePermission(
				TestPropsValues.getCompanyId(), PortletKeys.TEST,
				ResourceConstants.SCOPE_INDIVIDUAL, primKey,
				userRole.getRoleId());

		Assert.assertTrue(resourcePermission.hasActionId(ActionKeys.VIEW));

		resourcePermission.setRoleId(powerUserRole.getRoleId());

		ResourcePermissionLocalServiceUtil.updateResourcePermission(
			resourcePermission);

		doVerify();

		resourcePermission =
			ResourcePermissionLocalServiceUtil.fetchResourcePermission(
				TestPropsValues.getCompanyId(), PortletKeys.TEST,
				ResourceConstants.SCOPE_INDIVIDUAL, primKey,
				powerUserRole.getRoleId());

		Assert.assertNull(resourcePermission);

		resourcePermission =
			ResourcePermissionLocalServiceUtil.getResourcePermission(
				TestPropsValues.getCompanyId(), PortletKeys.TEST,
				ResourceConstants.SCOPE_INDIVIDUAL, primKey,
				userRole.getRoleId());

		Assert.assertTrue(resourcePermission.hasActionId(ActionKeys.VIEW));
	}

	protected Layout addPersonalLayoutWithDefaultResource() throws Exception {
		ThemeDisplay themeDisplay = new ThemeDisplay();

		User user = UserTestUtil.addUser();

		themeDisplay.setUser(user);

		Company company = CompanyLocalServiceUtil.getCompany(
			user.getCompanyId());

		themeDisplay.setCompany(company);

		TestServicePreAction.INSTANCE.addDefaultUserPublicLayouts(user);

		Group userGroup = user.getGroup();

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			userGroup.getGroupId(), true,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		if (layouts.isEmpty()) {
			layouts = LayoutLocalServiceUtil.getLayouts(
				userGroup.getGroupId(), false,
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);
		}

		Layout layout = layouts.get(0);

		String primKey = PortletPermissionUtil.getPrimaryKey(
			layout.getPlid(), PortletKeys.TEST);

		ResourceLocalServiceUtil.addResources(
			company.getCompanyId(), layout.getGroupId(), 0, PortletKeys.TEST,
			primKey, true, true, true);

		return layout;
	}

	@Override
	protected VerifyProcess getVerifyProcess() {
		return new VerifyPermission();
	}

}