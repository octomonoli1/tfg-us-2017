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
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.security.membershippolicy.MembershipPolicyException;
import com.liferay.portal.kernel.service.OrganizationServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ArrayUtil;
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
public class OrganizationMembershipPolicyMembershipsTest
	extends BaseOrganizationMembershipPolicyTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		ExpandoTableLocalServiceUtil.deleteTables(
			TestPropsValues.getCompanyId(), Organization.class.getName());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAddUserToForbiddenOrganization() throws Exception {
		MembershipPolicyTestUtil.addUser(
			addForbiddenOrganizations(), null, null, null);
	}

	@Test
	public void testAddUserToRequiredOrganizations() throws Exception {
		long[] requiredOrganizationIds = addRequiredOrganizations();

		int initialOrganizationUsersCount =
			UserLocalServiceUtil.getOrganizationUsersCount(
				requiredOrganizationIds[0]);

		MembershipPolicyTestUtil.addUser(
			new long[] {requiredOrganizationIds[0]}, null, null, null);

		Assert.assertEquals(
			initialOrganizationUsersCount + 1,
			UserLocalServiceUtil.getOrganizationUsersCount(
				requiredOrganizationIds[0]));
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUsersToForbiddenOrganization() throws Exception {
		long[] forbiddenOrganizationIds = addForbiddenOrganizations();

		UserServiceUtil.addOrganizationUsers(
			forbiddenOrganizationIds[0], addUsers());
	}

	@Test
	public void testAssignUsersToRequiredOrganization() throws Exception {
		long[] requiredOrganizationIds = addRequiredOrganizations();

		int initialOrganizationUsersCount =
			UserLocalServiceUtil.getOrganizationUsersCount(
				requiredOrganizationIds[0]);

		UserServiceUtil.addOrganizationUsers(
			requiredOrganizationIds[0], addUsers());

		Assert.assertEquals(
			initialOrganizationUsersCount + 2,
			UserLocalServiceUtil.getOrganizationUsersCount(
				requiredOrganizationIds[0]));
		Assert.assertTrue(isPropagateMembership());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUserToForbiddenOrganizations() throws Exception {
		long[] userIds = addUsers();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, addForbiddenOrganizations(), null, null, null,
			Collections.<UserGroupRole>emptyList());
	}

	@Test
	public void testAssignUserToRequiredOrganizations() throws Exception {
		long[] userIds = addUsers();
		long[] requiredOrganizationIds = addRequiredOrganizations();

		int initialOrganizationUsersCount =
			UserLocalServiceUtil.getOrganizationUsersCount(
				requiredOrganizationIds[0]);

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, new long[] {requiredOrganizationIds[0]}, null, null, null,
			Collections.<UserGroupRole>emptyList());

		Assert.assertEquals(
			initialOrganizationUsersCount + 1,
			UserLocalServiceUtil.getOrganizationUsersCount(
				requiredOrganizationIds[0]));
		Assert.assertTrue(isPropagateMembership());
	}

	@Test
	public void testPropagateWhenAddingUserToRequiredOrganizations()
		throws Exception {

		MembershipPolicyTestUtil.addUser(
			addRequiredOrganizations(), null, null, null);

		Assert.assertTrue(isPropagateMembership());
	}

	@Test
	public void testPropagateWhenAssigningUsersToRequiredOrganization()
		throws Exception {

		long[] requiredOrganizationIds = addRequiredOrganizations();

		UserServiceUtil.addOrganizationUsers(
			requiredOrganizationIds[0], addUsers());

		Assert.assertTrue(isPropagateMembership());
	}

	@Test
	public void testPropagateWhenAssigningUserToRequiredOrganizations()
		throws Exception {

		long[] userIds = addUsers();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, addRequiredOrganizations(), null, null, null,
			Collections.<UserGroupRole>emptyList());

		Assert.assertTrue(isPropagateMembership());
	}

	@Test
	public void testUnassignUserFromOrganizations() throws Exception {
		long[] userIds = addUsers();
		long[] standardOrganizationIds = addStandardOrganizations();
		long[] requiredOrganizationIds = addRequiredOrganizations();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		List<Organization> organizations = user.getOrganizations();

		Assert.assertEquals(0, organizations.size());

		long[] userOrganizationIds = ArrayUtil.append(
			standardOrganizationIds, requiredOrganizationIds);

		MembershipPolicyTestUtil.updateUser(
			user, userOrganizationIds, null, null, null,
			Collections.<UserGroupRole>emptyList());

		organizations = user.getOrganizations();

		Assert.assertEquals(userOrganizationIds.length, organizations.size());

		MembershipPolicyTestUtil.updateUser(
			user, standardOrganizationIds, null, null, null,
			Collections.<UserGroupRole>emptyList());

		organizations = user.getOrganizations();

		Assert.assertEquals(userOrganizationIds.length, organizations.size());
	}

	@Test
	public void testUnassignUserFromRequiredOrganizations() throws Exception {
		long[] userIds = addUsers();
		long[] standardOrganizationIds = addStandardOrganizations();
		long[] requiredOrganizationIds = addRequiredOrganizations();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		List<Organization> organizations = user.getOrganizations();

		Assert.assertEquals(0, organizations.size());

		long[] userOrganizationIds = ArrayUtil.append(
			standardOrganizationIds, requiredOrganizationIds);

		MembershipPolicyTestUtil.updateUser(
			user, userOrganizationIds, null, null, null,
			Collections.<UserGroupRole>emptyList());

		organizations = user.getOrganizations();

		Assert.assertEquals(userOrganizationIds.length, organizations.size());

		MembershipPolicyTestUtil.updateUser(
			user, requiredOrganizationIds, null, null, null,
			Collections.<UserGroupRole>emptyList());

		organizations = user.getOrganizations();

		Assert.assertEquals(
			requiredOrganizationIds.length, organizations.size());
	}

	@Test
	public void testUnassignUsersFromOrganization() throws Exception {
		long[] standardOrganizationIds = addStandardOrganizations();

		User user = MembershipPolicyTestUtil.addUser(
			standardOrganizationIds, null, null, null);

		int initialUserOrganizationCount =
			UserLocalServiceUtil.getOrganizationUsersCount(
				standardOrganizationIds[0]);

		UserServiceUtil.unsetOrganizationUsers(
			standardOrganizationIds[0], new long[] {user.getUserId()});

		Assert.assertEquals(
			initialUserOrganizationCount - 1,
			UserLocalServiceUtil.getOrganizationUsersCount(
				standardOrganizationIds[0]));
		Assert.assertTrue(isPropagateMembership());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testUnassignUsersFromRequiredOrganization() throws Exception {
		long[] requiredOrganizationIds = addRequiredOrganizations();

		User user = MembershipPolicyTestUtil.addUser(
			requiredOrganizationIds, null, null, null);

		UserServiceUtil.unsetOrganizationUsers(
			requiredOrganizationIds[0], new long[] {user.getUserId()});
	}

	@Test
	public void testVerifyWhenAddingOrganization() throws Exception {
		MembershipPolicyTestUtil.addOrganization();

		Assert.assertTrue(isVerify());
	}

	@Test
	public void testVerifyWhenUpdatingOrganization() throws Exception {
		Organization organization = MembershipPolicyTestUtil.addOrganization();

		OrganizationServiceUtil.updateOrganization(
			organization.getOrganizationId(),
			organization.getParentOrganizationId(), organization.getName(),
			organization.getType(), 0, 0, organization.getStatusId(),
			organization.getComments(), false,
			ServiceContextTestUtil.getServiceContext());

		Assert.assertTrue(isVerify());
	}

}