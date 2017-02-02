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

package com.liferay.portal.service.persistence.test;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.GroupPersistence;
import com.liferay.portal.kernel.service.persistence.GroupUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.TransactionalTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
public class GroupPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = GroupUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Group> iterator = _groups.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Group group = _persistence.create(pk);

		Assert.assertNotNull(group);

		Assert.assertEquals(group.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Group newGroup = addGroup();

		_persistence.remove(newGroup);

		Group existingGroup = _persistence.fetchByPrimaryKey(newGroup.getPrimaryKey());

		Assert.assertNull(existingGroup);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addGroup();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Group newGroup = _persistence.create(pk);

		newGroup.setMvccVersion(RandomTestUtil.nextLong());

		newGroup.setUuid(RandomTestUtil.randomString());

		newGroup.setCompanyId(RandomTestUtil.nextLong());

		newGroup.setCreatorUserId(RandomTestUtil.nextLong());

		newGroup.setClassNameId(RandomTestUtil.nextLong());

		newGroup.setClassPK(RandomTestUtil.nextLong());

		newGroup.setParentGroupId(RandomTestUtil.nextLong());

		newGroup.setLiveGroupId(RandomTestUtil.nextLong());

		newGroup.setTreePath(RandomTestUtil.randomString());

		newGroup.setGroupKey(RandomTestUtil.randomString());

		newGroup.setName(RandomTestUtil.randomString());

		newGroup.setDescription(RandomTestUtil.randomString());

		newGroup.setType(RandomTestUtil.nextInt());

		newGroup.setTypeSettings(RandomTestUtil.randomString());

		newGroup.setManualMembership(RandomTestUtil.randomBoolean());

		newGroup.setMembershipRestriction(RandomTestUtil.nextInt());

		newGroup.setFriendlyURL(RandomTestUtil.randomString());

		newGroup.setSite(RandomTestUtil.randomBoolean());

		newGroup.setRemoteStagingGroupCount(RandomTestUtil.nextInt());

		newGroup.setInheritContent(RandomTestUtil.randomBoolean());

		newGroup.setActive(RandomTestUtil.randomBoolean());

		_groups.add(_persistence.update(newGroup));

		Group existingGroup = _persistence.findByPrimaryKey(newGroup.getPrimaryKey());

		Assert.assertEquals(existingGroup.getMvccVersion(),
			newGroup.getMvccVersion());
		Assert.assertEquals(existingGroup.getUuid(), newGroup.getUuid());
		Assert.assertEquals(existingGroup.getGroupId(), newGroup.getGroupId());
		Assert.assertEquals(existingGroup.getCompanyId(),
			newGroup.getCompanyId());
		Assert.assertEquals(existingGroup.getCreatorUserId(),
			newGroup.getCreatorUserId());
		Assert.assertEquals(existingGroup.getClassNameId(),
			newGroup.getClassNameId());
		Assert.assertEquals(existingGroup.getClassPK(), newGroup.getClassPK());
		Assert.assertEquals(existingGroup.getParentGroupId(),
			newGroup.getParentGroupId());
		Assert.assertEquals(existingGroup.getLiveGroupId(),
			newGroup.getLiveGroupId());
		Assert.assertEquals(existingGroup.getTreePath(), newGroup.getTreePath());
		Assert.assertEquals(existingGroup.getGroupKey(), newGroup.getGroupKey());
		Assert.assertEquals(existingGroup.getName(), newGroup.getName());
		Assert.assertEquals(existingGroup.getDescription(),
			newGroup.getDescription());
		Assert.assertEquals(existingGroup.getType(), newGroup.getType());
		Assert.assertEquals(existingGroup.getTypeSettings(),
			newGroup.getTypeSettings());
		Assert.assertEquals(existingGroup.getManualMembership(),
			newGroup.getManualMembership());
		Assert.assertEquals(existingGroup.getMembershipRestriction(),
			newGroup.getMembershipRestriction());
		Assert.assertEquals(existingGroup.getFriendlyURL(),
			newGroup.getFriendlyURL());
		Assert.assertEquals(existingGroup.getSite(), newGroup.getSite());
		Assert.assertEquals(existingGroup.getRemoteStagingGroupCount(),
			newGroup.getRemoteStagingGroupCount());
		Assert.assertEquals(existingGroup.getInheritContent(),
			newGroup.getInheritContent());
		Assert.assertEquals(existingGroup.getActive(), newGroup.getActive());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid(StringPool.BLANK);

		_persistence.countByUuid(StringPool.NULL);

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByUUID_G(StringPool.NULL, 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByUuid_C(StringPool.NULL, 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(RandomTestUtil.nextLong());

		_persistence.countByCompanyId(0L);
	}

	@Test
	public void testCountByLiveGroupId() throws Exception {
		_persistence.countByLiveGroupId(RandomTestUtil.nextLong());

		_persistence.countByLiveGroupId(0L);
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testCountByC_P() throws Exception {
		_persistence.countByC_P(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByC_P(0L, 0L);
	}

	@Test
	public void testCountByC_GK() throws Exception {
		_persistence.countByC_GK(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByC_GK(0L, StringPool.NULL);

		_persistence.countByC_GK(0L, (String)null);
	}

	@Test
	public void testCountByC_F() throws Exception {
		_persistence.countByC_F(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByC_F(0L, StringPool.NULL);

		_persistence.countByC_F(0L, (String)null);
	}

	@Test
	public void testCountByC_S() throws Exception {
		_persistence.countByC_S(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByC_S(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByC_A() throws Exception {
		_persistence.countByC_A(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByC_A(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByC_CPK() throws Exception {
		_persistence.countByC_CPK(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByC_CPK(0L, 0L);
	}

	@Test
	public void testCountByT_A() throws Exception {
		_persistence.countByT_A(RandomTestUtil.nextInt(),
			RandomTestUtil.randomBoolean());

		_persistence.countByT_A(0, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByG_C_P() throws Exception {
		_persistence.countByG_C_P(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByG_C_P(0L, 0L, 0L);
	}

	@Test
	public void testCountByC_C_C() throws Exception {
		_persistence.countByC_C_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByC_C_C(0L, 0L, 0L);
	}

	@Test
	public void testCountByC_C_P() throws Exception {
		_persistence.countByC_C_P(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByC_C_P(0L, 0L, 0L);
	}

	@Test
	public void testCountByC_P_S() throws Exception {
		_persistence.countByC_P_S(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByC_P_S(0L, 0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByC_L_GK() throws Exception {
		_persistence.countByC_L_GK(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByC_L_GK(0L, 0L, StringPool.NULL);

		_persistence.countByC_L_GK(0L, 0L, (String)null);
	}

	@Test
	public void testCountByG_C_C_P() throws Exception {
		_persistence.countByG_C_C_P(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_C_C_P(0L, 0L, 0L, 0L);
	}

	@Test
	public void testCountByC_C_L_GK() throws Exception {
		_persistence.countByC_C_L_GK(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			StringPool.BLANK);

		_persistence.countByC_C_L_GK(0L, 0L, 0L, StringPool.NULL);

		_persistence.countByC_C_L_GK(0L, 0L, 0L, (String)null);
	}

	@Test
	public void testCountByC_P_S_I() throws Exception {
		_persistence.countByC_P_S_I(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean(),
			RandomTestUtil.randomBoolean());

		_persistence.countByC_P_S_I(0L, 0L, RandomTestUtil.randomBoolean(),
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Group newGroup = addGroup();

		Group existingGroup = _persistence.findByPrimaryKey(newGroup.getPrimaryKey());

		Assert.assertEquals(existingGroup, newGroup);
	}

	@Test(expected = NoSuchGroupException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Group> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("Group_", "mvccVersion",
			true, "uuid", true, "groupId", true, "companyId", true,
			"creatorUserId", true, "classNameId", true, "classPK", true,
			"parentGroupId", true, "liveGroupId", true, "treePath", true,
			"groupKey", true, "name", true, "description", true, "type", true,
			"manualMembership", true, "membershipRestriction", true,
			"friendlyURL", true, "site", true, "remoteStagingGroupCount", true,
			"inheritContent", true, "active", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Group newGroup = addGroup();

		Group existingGroup = _persistence.fetchByPrimaryKey(newGroup.getPrimaryKey());

		Assert.assertEquals(existingGroup, newGroup);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Group missingGroup = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingGroup);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Group newGroup1 = addGroup();
		Group newGroup2 = addGroup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGroup1.getPrimaryKey());
		primaryKeys.add(newGroup2.getPrimaryKey());

		Map<Serializable, Group> groups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, groups.size());
		Assert.assertEquals(newGroup1, groups.get(newGroup1.getPrimaryKey()));
		Assert.assertEquals(newGroup2, groups.get(newGroup2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Group> groups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(groups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Group newGroup = addGroup();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGroup.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Group> groups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, groups.size());
		Assert.assertEquals(newGroup, groups.get(newGroup.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Group> groups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(groups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Group newGroup = addGroup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGroup.getPrimaryKey());

		Map<Serializable, Group> groups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, groups.size());
		Assert.assertEquals(newGroup, groups.get(newGroup.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = GroupLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Group>() {
				@Override
				public void performAction(Group group) {
					Assert.assertNotNull(group);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Group newGroup = addGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Group.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId",
				newGroup.getGroupId()));

		List<Group> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Group existingGroup = result.get(0);

		Assert.assertEquals(existingGroup, newGroup);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Group.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId",
				RandomTestUtil.nextLong()));

		List<Group> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Group newGroup = addGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Group.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("groupId"));

		Object newGroupId = newGroup.getGroupId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("groupId",
				new Object[] { newGroupId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingGroupId = result.get(0);

		Assert.assertEquals(existingGroupId, newGroupId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Group.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("groupId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("groupId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Group newGroup = addGroup();

		_persistence.clearCache();

		Group existingGroup = _persistence.findByPrimaryKey(newGroup.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingGroup.getUuid(),
				ReflectionTestUtil.invoke(existingGroup, "getOriginalUuid",
					new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingGroup.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingGroup,
				"getOriginalGroupId", new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(existingGroup.getLiveGroupId()),
			ReflectionTestUtil.<Long>invoke(existingGroup,
				"getOriginalLiveGroupId", new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(existingGroup.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(existingGroup,
				"getOriginalCompanyId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingGroup.getGroupKey(),
				ReflectionTestUtil.invoke(existingGroup, "getOriginalGroupKey",
					new Class<?>[0])));

		Assert.assertEquals(Long.valueOf(existingGroup.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(existingGroup,
				"getOriginalCompanyId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingGroup.getFriendlyURL(),
				ReflectionTestUtil.invoke(existingGroup,
					"getOriginalFriendlyURL", new Class<?>[0])));

		Assert.assertEquals(Long.valueOf(existingGroup.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(existingGroup,
				"getOriginalCompanyId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingGroup.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingGroup,
				"getOriginalClassNameId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingGroup.getClassPK()),
			ReflectionTestUtil.<Long>invoke(existingGroup,
				"getOriginalClassPK", new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(existingGroup.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(existingGroup,
				"getOriginalCompanyId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingGroup.getLiveGroupId()),
			ReflectionTestUtil.<Long>invoke(existingGroup,
				"getOriginalLiveGroupId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingGroup.getGroupKey(),
				ReflectionTestUtil.invoke(existingGroup, "getOriginalGroupKey",
					new Class<?>[0])));

		Assert.assertEquals(Long.valueOf(existingGroup.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(existingGroup,
				"getOriginalCompanyId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingGroup.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingGroup,
				"getOriginalClassNameId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingGroup.getLiveGroupId()),
			ReflectionTestUtil.<Long>invoke(existingGroup,
				"getOriginalLiveGroupId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingGroup.getGroupKey(),
				ReflectionTestUtil.invoke(existingGroup, "getOriginalGroupKey",
					new Class<?>[0])));
	}

	protected Group addGroup() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Group group = _persistence.create(pk);

		group.setMvccVersion(RandomTestUtil.nextLong());

		group.setUuid(RandomTestUtil.randomString());

		group.setCompanyId(RandomTestUtil.nextLong());

		group.setCreatorUserId(RandomTestUtil.nextLong());

		group.setClassNameId(RandomTestUtil.nextLong());

		group.setClassPK(RandomTestUtil.nextLong());

		group.setParentGroupId(RandomTestUtil.nextLong());

		group.setLiveGroupId(RandomTestUtil.nextLong());

		group.setTreePath(RandomTestUtil.randomString());

		group.setGroupKey(RandomTestUtil.randomString());

		group.setName(RandomTestUtil.randomString());

		group.setDescription(RandomTestUtil.randomString());

		group.setType(RandomTestUtil.nextInt());

		group.setTypeSettings(RandomTestUtil.randomString());

		group.setManualMembership(RandomTestUtil.randomBoolean());

		group.setMembershipRestriction(RandomTestUtil.nextInt());

		group.setFriendlyURL(RandomTestUtil.randomString());

		group.setSite(RandomTestUtil.randomBoolean());

		group.setRemoteStagingGroupCount(RandomTestUtil.nextInt());

		group.setInheritContent(RandomTestUtil.randomBoolean());

		group.setActive(RandomTestUtil.randomBoolean());

		_groups.add(_persistence.update(group));

		return group;
	}

	private List<Group> _groups = new ArrayList<Group>();
	private GroupPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}