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

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.UserBag;
import com.liferay.portal.kernel.security.permission.UserBagFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.OrganizationTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Preston Crary
 */
public class UserBagFactoryTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_parentGroup = GroupTestUtil.addGroup();

		_childGroup = GroupTestUtil.addGroup(_parentGroup.getGroupId());

		_parentOrganization = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			RandomTestUtil.randomString(), true);

		_childOrganization = OrganizationTestUtil.addOrganization(
			_parentOrganization.getOrganizationId(),
			RandomTestUtil.randomString(), true);

		_user = UserTestUtil.addUser();
	}

	@Test
	public void testGetGroups() throws Exception {
		UserBag userBag = getUserBag();

		Collection<Group> groups = userBag.getGroups();

		Collection<Group> userGroups = getUserGroups();
		Collection<Group> userOrgGroups = getUserOrgGroups();

		Assert.assertTrue(groups.containsAll(userGroups));
		Assert.assertTrue(groups.containsAll(userOrgGroups));
	}

	@Test
	public void testGetRoles() throws Exception {
		Role regularRole = RoleTestUtil.addRole(RoleConstants.TYPE_REGULAR);

		UserLocalServiceUtil.addRoleUser(regularRole.getRoleId(), _user);

		long groupRoleId = RoleTestUtil.addGroupRole(_childGroup.getGroupId());

		UserLocalServiceUtil.addRoleUser(groupRoleId, _user);

		long organizationRoleId = RoleTestUtil.addOrganizationRole(
			_childOrganization.getGroupId());

		UserLocalServiceUtil.addRoleUser(organizationRoleId, _user);

		UserBag userBag = getUserBag();

		List<Role> roles = new ArrayList<>(userBag.getRoles());

		long[] roleIds = ListUtil.toLongArray(roles, Role.ROLE_ID_ACCESSOR);

		Assert.assertTrue(ArrayUtil.contains(roleIds, regularRole.getRoleId()));
		Assert.assertTrue(ArrayUtil.contains(roleIds, groupRoleId));
		Assert.assertTrue(ArrayUtil.contains(roleIds, organizationRoleId));
	}

	@Test
	public void testGetUserGroups() throws Exception {
		Collection<Group> userGroups = getUserGroups();

		Assert.assertTrue(userGroups.contains(_childGroup));
		Assert.assertFalse(userGroups.contains(_parentGroup));
	}

	@Test
	public void testGetUserId() throws Exception {
		UserBag userBag = getUserBag();

		Assert.assertEquals(_user.getUserId(), userBag.getUserId());
	}

	@Test
	public void testGetUserOrgGroups() throws Exception {
		Collection<Group> groups = getUserOrgGroups();

		Assert.assertTrue(groups.contains(_childOrganization.getGroup()));
		Assert.assertTrue(groups.contains(_parentOrganization.getGroup()));
	}

	@Test
	public void testGetUserOrgs() throws Exception {
		Collection<Organization> organizations = getUserOrgs();

		Assert.assertTrue(organizations.contains(_childOrganization));
		Assert.assertTrue(organizations.contains(_parentOrganization));
	}

	protected UserBag getUserBag() throws Exception {
		return UserBagFactoryUtil.create(_user.getUserId());
	}

	protected Collection<Group> getUserGroups() throws Exception {
		UserLocalServiceUtil.addGroupUser(_childGroup.getGroupId(), _user);

		UserBag userBag = getUserBag();

		return userBag.getUserGroups();
	}

	protected Collection<Group> getUserOrgGroups() throws Exception {
		UserLocalServiceUtil.addOrganizationUser(
			_childOrganization.getOrganizationId(), _user.getUserId());

		UserBag userBag = getUserBag();

		return userBag.getUserOrgGroups();
	}

	protected Collection<Organization> getUserOrgs() throws Exception {
		UserLocalServiceUtil.addOrganizationUser(
			_childOrganization.getOrganizationId(), _user.getUserId());

		UserBag userBag = getUserBag();

		return userBag.getUserOrgs();
	}

	@DeleteAfterTestRun
	private Group _childGroup;

	@DeleteAfterTestRun
	private Organization _childOrganization;

	@DeleteAfterTestRun
	private Group _parentGroup;

	@DeleteAfterTestRun
	private Organization _parentOrganization;

	@DeleteAfterTestRun
	private User _user;

}