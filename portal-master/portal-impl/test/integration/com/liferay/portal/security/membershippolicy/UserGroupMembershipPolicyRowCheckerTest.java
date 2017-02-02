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

package com.liferay.portal.security.membershippolicy;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.usergroupsadmin.search.SetUserUserGroupChecker;
import com.liferay.portlet.usergroupsadmin.search.UnsetUserUserGroupChecker;

import javax.portlet.RenderResponse;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Roberto Díaz
 */
public class UserGroupMembershipPolicyRowCheckerTest
	extends BaseUserGroupMembershipPolicyTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testIsCheckerDisabledWhenSettingForbiddenUserGroupToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long forbiddenUserGroupId = addForbiddenUserGroups()[0];

		UserGroup forbiddenUserGroup = UserGroupLocalServiceUtil.getUserGroup(
			forbiddenUserGroupId);

		SetUserUserGroupChecker setUserUserGroupChecker =
			new SetUserUserGroupChecker(renderResponse, forbiddenUserGroup);

		User user = UserTestUtil.addUser();

		Assert.assertTrue(setUserUserGroupChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenSettingRequiredUserGroupToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long requiredUserGroupId = addRequiredUserGroups()[0];

		UserGroup requiredUserGroup = UserGroupLocalServiceUtil.getUserGroup(
			requiredUserGroupId);

		SetUserUserGroupChecker setUserUserGroupChecker =
			new SetUserUserGroupChecker(renderResponse, requiredUserGroup);

		User user = UserTestUtil.addUser();

		Assert.assertFalse(setUserUserGroupChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingForbiddenUserGroupToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long forbiddenUserGroupId = addForbiddenUserGroups()[0];

		UserGroup forbiddenUserGroup = UserGroupLocalServiceUtil.getUserGroup(
			forbiddenUserGroupId);

		UnsetUserUserGroupChecker setUserUserGroupChecker =
			new UnsetUserUserGroupChecker(renderResponse, forbiddenUserGroup);

		User user = UserTestUtil.addUser();

		UserGroupLocalServiceUtil.addUserUserGroup(
			user.getUserId(), forbiddenUserGroupId);

		Assert.assertFalse(setUserUserGroupChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingRequiredUserGroupToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long requiredUserGroupId = addRequiredUserGroups()[0];

		UserGroup requiredUserGroup = UserGroupLocalServiceUtil.getUserGroup(
			requiredUserGroupId);

		UnsetUserUserGroupChecker setUserUserGroupChecker =
			new UnsetUserUserGroupChecker(renderResponse, requiredUserGroup);

		User user = UserTestUtil.addUser();

		UserGroupLocalServiceUtil.addUserUserGroup(
			user.getUserId(), requiredUserGroupId);

		Assert.assertTrue(setUserUserGroupChecker.isDisabled(user));
	}

}