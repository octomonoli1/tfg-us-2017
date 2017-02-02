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

package com.liferay.portal.service;

import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.exception.RoleNameException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.comparator.RoleRoleIdComparator;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.test.LayoutTestUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author László Csontos
 */
public class RoleLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() {
		IndexerRegistryUtil.unregister(Organization.class.getName());
	}

	@Test(expected = RoleNameException.class)
	public void testAddRoleWithPlaceholderName() throws Exception {
		RoleTestUtil.addRole(
			RoleConstants.PLACEHOLDER_DEFAULT_GROUP_ROLE,
			RoleConstants.TYPE_REGULAR);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Test
	public void testGetGroupRelatedRoles() throws Exception {
		Object[] objects = getOrganizationAndTeam();

		Organization organization = (Organization)objects[0];

		long companyId = organization.getCompanyId();

		long groupId = organization.getGroupId();

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		List<Role> actualRoles = RoleLocalServiceUtil.getGroupRelatedRoles(
			groupId);

		List<Role> allRoles = RoleLocalServiceUtil.getRoles(companyId);

		List<Role> expectedRoles = new ArrayList<>();

		for (Role role : allRoles) {
			int type = role.getType();

			if ((type == RoleConstants.TYPE_REGULAR) ||
				((type == RoleConstants.TYPE_ORGANIZATION) &&
				 group.isOrganization()) ||
				((type == RoleConstants.TYPE_SITE) &&
				 (group.isLayout() || group.isLayoutSetPrototype() ||
				  group.isSite()))) {

				expectedRoles.add(role);
			}
			else if ((type == RoleConstants.TYPE_PROVIDER) && role.isTeam()) {
				Team team = TeamLocalServiceUtil.getTeam(role.getClassPK());

				if (team.getGroupId() == groupId) {
					expectedRoles.add(role);
				}
			}
		}

		Comparator roleIdComparator = new RoleRoleIdComparator();

		Collections.sort(actualRoles, roleIdComparator);
		Collections.sort(expectedRoles, roleIdComparator);

		Assert.assertEquals(expectedRoles, actualRoles);
	}

	@Test
	public void testGetTeamRoleMapWithExclusion() throws Exception {
		Object[] organizationAndTeam = getOrganizationAndTeam();

		Organization organization = (Organization)organizationAndTeam[0];
		Team team = (Team)organizationAndTeam[1];

		Map<Team, Role> teamRoleMap = RoleLocalServiceUtil.getTeamRoleMap(
			organization.getGroupId());

		Role role = teamRoleMap.get(team);

		Assert.assertNotNull(role);

		long[] excludedRoleIds = new long[] {role.getRoleId()};

		List<Role> roles = RoleLocalServiceUtil.getTeamRoles(
			organization.getGroupId(), excludedRoleIds);

		Assert.assertNotNull(roles);
		Assert.assertTrue(roles.isEmpty());
	}

	@Test(expected = NoSuchGroupException.class)
	public void testGetTeamRoleMapWithInvalidGroupId() throws Exception {
		RoleLocalServiceUtil.getTeamRoleMap(0L);
	}

	@Test
	public void testGetTeamRoleMapWithOtherGroupId() throws Exception {
		Object[] organizationAndTeam1 = getOrganizationAndTeam();
		Object[] organizationAndTeam2 = getOrganizationAndTeam();

		Organization organization = (Organization)organizationAndTeam1[0];
		Team team = (Team)organizationAndTeam2[1];

		Map<Team, Role> teamRoleMap = RoleLocalServiceUtil.getTeamRoleMap(
			organization.getGroupId());

		testGetTeamRoleMap(teamRoleMap, team, false);
	}

	@Test
	public void testGetTeamRoleMapWithOwnGroupId() throws Exception {
		Object[] organizationAndTeam = getOrganizationAndTeam();

		Organization organization = (Organization)organizationAndTeam[0];
		Team team = (Team)organizationAndTeam[1];

		Map<Team, Role> teamRoleMap = RoleLocalServiceUtil.getTeamRoleMap(
			organization.getGroupId());

		testGetTeamRoleMap(teamRoleMap, team, true);
	}

	@Test
	public void testGetTeamRoleMapWithParentGroupId() throws Exception {
		Object[] organizationAndTeam = getOrganizationAndTeam();

		Organization organization = (Organization)organizationAndTeam[0];
		Team team = (Team)organizationAndTeam[1];

		Layout layout = LayoutTestUtil.addLayout(organization.getGroupId());

		Group group = GroupTestUtil.addGroup(
			TestPropsValues.getUserId(), organization.getGroupId(), layout);

		Map<Team, Role> teamRoleMap = RoleLocalServiceUtil.getTeamRoleMap(
			group.getGroupId());

		testGetTeamRoleMap(teamRoleMap, team, true);
	}

	protected Object[] getOrganizationAndTeam() throws Exception {
		User user = TestPropsValues.getUser();

		Organization organization =
			OrganizationLocalServiceUtil.addOrganization(
				user.getUserId(),
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
				RandomTestUtil.randomString(), false);

		_organizations.add(organization);

		Team team = TeamLocalServiceUtil.addTeam(
			user.getUserId(), organization.getGroupId(),
			RandomTestUtil.randomString(), null, new ServiceContext());

		return new Object[] {organization, team};
	}

	protected void testGetTeamRoleMap(
		Map<Team, Role> teamRoleMap, Team team, boolean hasTeam) {

		Assert.assertNotNull(teamRoleMap);
		Assert.assertFalse(teamRoleMap.isEmpty());

		if (hasTeam) {
			Assert.assertTrue(teamRoleMap.containsKey(team));

			Role role = teamRoleMap.get(team);

			Assert.assertEquals(role.getType(), RoleConstants.TYPE_PROVIDER);
		}
		else {
			Assert.assertFalse(teamRoleMap.containsKey(team));
		}
	}

	@DeleteAfterTestRun
	private final List<Organization> _organizations = new ArrayList<>();

}