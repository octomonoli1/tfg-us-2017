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
import com.liferay.portal.kernel.exception.NoSuchLayoutBranchException;
import com.liferay.portal.kernel.model.LayoutBranch;
import com.liferay.portal.kernel.service.LayoutBranchLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.LayoutBranchPersistence;
import com.liferay.portal.kernel.service.persistence.LayoutBranchUtil;
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
public class LayoutBranchPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = LayoutBranchUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LayoutBranch> iterator = _layoutBranchs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutBranch layoutBranch = _persistence.create(pk);

		Assert.assertNotNull(layoutBranch);

		Assert.assertEquals(layoutBranch.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LayoutBranch newLayoutBranch = addLayoutBranch();

		_persistence.remove(newLayoutBranch);

		LayoutBranch existingLayoutBranch = _persistence.fetchByPrimaryKey(newLayoutBranch.getPrimaryKey());

		Assert.assertNull(existingLayoutBranch);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLayoutBranch();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutBranch newLayoutBranch = _persistence.create(pk);

		newLayoutBranch.setMvccVersion(RandomTestUtil.nextLong());

		newLayoutBranch.setGroupId(RandomTestUtil.nextLong());

		newLayoutBranch.setCompanyId(RandomTestUtil.nextLong());

		newLayoutBranch.setUserId(RandomTestUtil.nextLong());

		newLayoutBranch.setUserName(RandomTestUtil.randomString());

		newLayoutBranch.setLayoutSetBranchId(RandomTestUtil.nextLong());

		newLayoutBranch.setPlid(RandomTestUtil.nextLong());

		newLayoutBranch.setName(RandomTestUtil.randomString());

		newLayoutBranch.setDescription(RandomTestUtil.randomString());

		newLayoutBranch.setMaster(RandomTestUtil.randomBoolean());

		_layoutBranchs.add(_persistence.update(newLayoutBranch));

		LayoutBranch existingLayoutBranch = _persistence.findByPrimaryKey(newLayoutBranch.getPrimaryKey());

		Assert.assertEquals(existingLayoutBranch.getMvccVersion(),
			newLayoutBranch.getMvccVersion());
		Assert.assertEquals(existingLayoutBranch.getLayoutBranchId(),
			newLayoutBranch.getLayoutBranchId());
		Assert.assertEquals(existingLayoutBranch.getGroupId(),
			newLayoutBranch.getGroupId());
		Assert.assertEquals(existingLayoutBranch.getCompanyId(),
			newLayoutBranch.getCompanyId());
		Assert.assertEquals(existingLayoutBranch.getUserId(),
			newLayoutBranch.getUserId());
		Assert.assertEquals(existingLayoutBranch.getUserName(),
			newLayoutBranch.getUserName());
		Assert.assertEquals(existingLayoutBranch.getLayoutSetBranchId(),
			newLayoutBranch.getLayoutSetBranchId());
		Assert.assertEquals(existingLayoutBranch.getPlid(),
			newLayoutBranch.getPlid());
		Assert.assertEquals(existingLayoutBranch.getName(),
			newLayoutBranch.getName());
		Assert.assertEquals(existingLayoutBranch.getDescription(),
			newLayoutBranch.getDescription());
		Assert.assertEquals(existingLayoutBranch.getMaster(),
			newLayoutBranch.getMaster());
	}

	@Test
	public void testCountByLayoutSetBranchId() throws Exception {
		_persistence.countByLayoutSetBranchId(RandomTestUtil.nextLong());

		_persistence.countByLayoutSetBranchId(0L);
	}

	@Test
	public void testCountByL_P() throws Exception {
		_persistence.countByL_P(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByL_P(0L, 0L);
	}

	@Test
	public void testCountByL_P_N() throws Exception {
		_persistence.countByL_P_N(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByL_P_N(0L, 0L, StringPool.NULL);

		_persistence.countByL_P_N(0L, 0L, (String)null);
	}

	@Test
	public void testCountByL_P_M() throws Exception {
		_persistence.countByL_P_M(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByL_P_M(0L, 0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LayoutBranch newLayoutBranch = addLayoutBranch();

		LayoutBranch existingLayoutBranch = _persistence.findByPrimaryKey(newLayoutBranch.getPrimaryKey());

		Assert.assertEquals(existingLayoutBranch, newLayoutBranch);
	}

	@Test(expected = NoSuchLayoutBranchException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LayoutBranch> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("LayoutBranch",
			"mvccVersion", true, "layoutBranchId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true,
			"layoutSetBranchId", true, "plid", true, "name", true,
			"description", true, "master", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LayoutBranch newLayoutBranch = addLayoutBranch();

		LayoutBranch existingLayoutBranch = _persistence.fetchByPrimaryKey(newLayoutBranch.getPrimaryKey());

		Assert.assertEquals(existingLayoutBranch, newLayoutBranch);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutBranch missingLayoutBranch = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLayoutBranch);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LayoutBranch newLayoutBranch1 = addLayoutBranch();
		LayoutBranch newLayoutBranch2 = addLayoutBranch();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLayoutBranch1.getPrimaryKey());
		primaryKeys.add(newLayoutBranch2.getPrimaryKey());

		Map<Serializable, LayoutBranch> layoutBranchs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, layoutBranchs.size());
		Assert.assertEquals(newLayoutBranch1,
			layoutBranchs.get(newLayoutBranch1.getPrimaryKey()));
		Assert.assertEquals(newLayoutBranch2,
			layoutBranchs.get(newLayoutBranch2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LayoutBranch> layoutBranchs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(layoutBranchs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LayoutBranch newLayoutBranch = addLayoutBranch();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLayoutBranch.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LayoutBranch> layoutBranchs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, layoutBranchs.size());
		Assert.assertEquals(newLayoutBranch,
			layoutBranchs.get(newLayoutBranch.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LayoutBranch> layoutBranchs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(layoutBranchs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LayoutBranch newLayoutBranch = addLayoutBranch();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLayoutBranch.getPrimaryKey());

		Map<Serializable, LayoutBranch> layoutBranchs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, layoutBranchs.size());
		Assert.assertEquals(newLayoutBranch,
			layoutBranchs.get(newLayoutBranch.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LayoutBranchLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LayoutBranch>() {
				@Override
				public void performAction(LayoutBranch layoutBranch) {
					Assert.assertNotNull(layoutBranch);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LayoutBranch newLayoutBranch = addLayoutBranch();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutBranch.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("layoutBranchId",
				newLayoutBranch.getLayoutBranchId()));

		List<LayoutBranch> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LayoutBranch existingLayoutBranch = result.get(0);

		Assert.assertEquals(existingLayoutBranch, newLayoutBranch);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutBranch.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("layoutBranchId",
				RandomTestUtil.nextLong()));

		List<LayoutBranch> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LayoutBranch newLayoutBranch = addLayoutBranch();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutBranch.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"layoutBranchId"));

		Object newLayoutBranchId = newLayoutBranch.getLayoutBranchId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("layoutBranchId",
				new Object[] { newLayoutBranchId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLayoutBranchId = result.get(0);

		Assert.assertEquals(existingLayoutBranchId, newLayoutBranchId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutBranch.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"layoutBranchId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("layoutBranchId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		LayoutBranch newLayoutBranch = addLayoutBranch();

		_persistence.clearCache();

		LayoutBranch existingLayoutBranch = _persistence.findByPrimaryKey(newLayoutBranch.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingLayoutBranch.getLayoutSetBranchId()),
			ReflectionTestUtil.<Long>invoke(existingLayoutBranch,
				"getOriginalLayoutSetBranchId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingLayoutBranch.getPlid()),
			ReflectionTestUtil.<Long>invoke(existingLayoutBranch,
				"getOriginalPlid", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingLayoutBranch.getName(),
				ReflectionTestUtil.invoke(existingLayoutBranch,
					"getOriginalName", new Class<?>[0])));
	}

	protected LayoutBranch addLayoutBranch() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutBranch layoutBranch = _persistence.create(pk);

		layoutBranch.setMvccVersion(RandomTestUtil.nextLong());

		layoutBranch.setGroupId(RandomTestUtil.nextLong());

		layoutBranch.setCompanyId(RandomTestUtil.nextLong());

		layoutBranch.setUserId(RandomTestUtil.nextLong());

		layoutBranch.setUserName(RandomTestUtil.randomString());

		layoutBranch.setLayoutSetBranchId(RandomTestUtil.nextLong());

		layoutBranch.setPlid(RandomTestUtil.nextLong());

		layoutBranch.setName(RandomTestUtil.randomString());

		layoutBranch.setDescription(RandomTestUtil.randomString());

		layoutBranch.setMaster(RandomTestUtil.randomBoolean());

		_layoutBranchs.add(_persistence.update(layoutBranch));

		return layoutBranch;
	}

	private List<LayoutBranch> _layoutBranchs = new ArrayList<LayoutBranch>();
	private LayoutBranchPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}