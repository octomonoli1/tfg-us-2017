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

package com.liferay.portal.util;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.test.LayoutTestUtil;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Carlos Sierra
 * @author Akos Thurzo
 */
public class PortalImplLayoutSetFriendlyURLTest
	extends PortalImplBaseURLTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testAccessFromVirtualHost() throws Exception {
		Field field = ReflectionUtil.getDeclaredField(
			PropsValues.class, "VIRTUAL_HOSTS_DEFAULT_SITE_NAME");

		Object value = field.get(null);

		Group defaultGroup = GroupTestUtil.addGroup();

		try {
			field.set(null, defaultGroup.getName());

			ThemeDisplay themeDisplay = initThemeDisplay(
				company, group, publicLayout, LOCALHOST, VIRTUAL_HOSTNAME);

			Layout layout = LayoutTestUtil.addLayout(defaultGroup);

			String friendlyURL = PortalUtil.getLayoutSetFriendlyURL(
				layout.getLayoutSet(), themeDisplay);

			Assert.assertFalse(friendlyURL.contains(LOCALHOST));
		}
		finally {
			field.set(null, value);

			GroupLocalServiceUtil.deleteGroup(defaultGroup);
		}
	}

	@Test
	public void testPreserveParameters() throws Exception {
		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, controlPanelLayout, VIRTUAL_HOSTNAME);

		themeDisplay.setDoAsUserId("impersonated");

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
			group.getGroupId(), false);

		String layoutSetFriendlyURL = PortalUtil.getLayoutSetFriendlyURL(
			layoutSet, themeDisplay);

		Assert.assertEquals(
			"impersonated",
			HttpUtil.getParameter(layoutSetFriendlyURL, "doAsUserId"));
	}

}