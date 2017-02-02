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

import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.security.membershippolicy.MembershipPolicyException;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.security.membershippolicy.util.test.MembershipPolicyTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Roberto Díaz
 */
public class RoleMembershipPolicyRolesTest
	extends BaseRoleMembershipPolicyTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		ExpandoTableLocalServiceUtil.deleteTables(
			TestPropsValues.getCompanyId(), Role.class.getName());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUsersToForbiddenRole() throws Exception {
		long[] forbiddenRoleIds = addForbiddenRoles();

		UserServiceUtil.addRoleUsers(forbiddenRoleIds[0], addUsers());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUserToForbiddenRole() throws Exception {
		long[] userIds = addUsers();
		long[] forbiddenRoleIds = addForbiddenRoles();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, null, new long[] {forbiddenRoleIds[0]}, null, null,
			Collections.<UserGroupRole>emptyList());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUserToForbiddenRoles() throws Exception {
		long[] userIds = addUsers();

		RoleServiceUtil.addUserRoles(userIds[0], addForbiddenRoles());
	}

	@Test
	public void testPropagateWhenAssigningRolesToUser() throws Exception {
		long[] userIds = addUsers();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, null, addStandardRoles(), null, null,
			Collections.<UserGroupRole>emptyList());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenAssigningRoleToUsers() throws Exception {
		long[] standardRoleId = addStandardRoles();

		UserServiceUtil.setRoleUsers(standardRoleId[0], addUsers());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenAssigningUsersToRole() throws Exception {
		long[] standardRoleId = addStandardRoles();

		UserServiceUtil.addRoleUsers(standardRoleId[0], addUsers());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenAssigningUserToRoles() throws Exception {
		long[] userIds = addUsers();

		RoleServiceUtil.addUserRoles(userIds[0], addStandardRoles());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenUnassigningRolesFromUser() throws Exception {
		long[] userIds = addUsers();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		RoleServiceUtil.addUserRoles(user.getUserId(), addStandardRoles());

		MembershipPolicyTestUtil.updateUser(
			user, null, null, null, null,
			Collections.<UserGroupRole>emptyList());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenUnassigningUserFromRoles() throws Exception {
		long[] userIds = addUsers();

		RoleServiceUtil.unsetUserRoles(userIds[0], addStandardRoles());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenUnassigningUsersFromRole() throws Exception {
		long[] standardRoles = addStandardRoles();

		UserServiceUtil.unsetRoleUsers(standardRoles[0], addUsers());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testSetForbbidenRoleToUsers() throws Exception {
		long[] forbiddenRoleIds = addForbiddenRoles();

		UserServiceUtil.setRoleUsers(forbiddenRoleIds[0], addUsers());
	}

	@Test
	public void testUnassignRequiredRolesFromUser() throws Exception {
		long[] userIds = addUsers();

		RoleServiceUtil.addUserRoles(userIds[0], addRequiredRoles());

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		List<Role> initialUserRoles = RoleLocalServiceUtil.getUserRoles(
			user.getUserId());

		MembershipPolicyTestUtil.updateUser(
			user, null, null, null, null,
			Collections.<UserGroupRole>emptyList());

		List<Role> currentUserRoles = RoleLocalServiceUtil.getUserRoles(
			user.getUserId());

		Assert.assertEquals(initialUserRoles.size(), currentUserRoles.size());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testUnassignUserFromRequiredRoles() throws Exception {
		long[] userIds = addUsers();

		RoleServiceUtil.unsetUserRoles(userIds[0], addRequiredRoles());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testUnassignUsersFromRequiredRole() throws Exception {
		long[] requiredRoleIds = addRequiredRoles();

		UserServiceUtil.unsetRoleUsers(requiredRoleIds[0], addUsers());
	}

	@Test
	public void testUnassignUsersFromRole() throws Exception {
		long[] standardRoleIds = addStandardRoles();

		UserServiceUtil.unsetRoleUsers(standardRoleIds[0], addUsers());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testVerifyWhenAddingRole() throws Exception {
		MembershipPolicyTestUtil.addRole(RoleConstants.TYPE_REGULAR);

		Assert.assertTrue(isVerify());
	}

	@Test
	public void testVerifyWhenUpdatingRole() throws Exception {
		Role role = MembershipPolicyTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		RoleServiceUtil.updateRole(
			role.getRoleId(), RandomTestUtil.randomString(), role.getTitleMap(),
			role.getDescriptionMap(), role.getSubtype(), new ServiceContext());

		Assert.assertTrue(isVerify());
	}

}