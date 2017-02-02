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
import com.liferay.portal.kernel.exception.NoSuchRecentLayoutBranchException;
import com.liferay.portal.kernel.model.RecentLayoutBranch;
import com.liferay.portal.kernel.service.RecentLayoutBranchLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.RecentLayoutBranchPersistence;
import com.liferay.portal.kernel.service.persistence.RecentLayoutBranchUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.TransactionalTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
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
import java.util.Set;

/**
 * @generated
 */
public class RecentLayoutBranchPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = RecentLayoutBranchUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RecentLayoutBranch> iterator = _recentLayoutBranchs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RecentLayoutBranch recentLayoutBranch = _persistence.create(pk);

		Assert.assertNotNull(recentLayoutBranch);

		Assert.assertEquals(recentLayoutBranch.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RecentLayoutBranch newRecentLayoutBranch = addRecentLayoutBranch();

		_persistence.remove(newRecentLayoutBranch);

		RecentLayoutBranch existingRecentLayoutBranch = _persistence.fetchByPrimaryKey(newRecentLayoutBranch.getPrimaryKey());

		Assert.assertNull(existingRecentLayoutBranch);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRecentLayoutBranch();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RecentLayoutBranch newRecentLayoutBranch = _persistence.create(pk);

		newRecentLayoutBranch.setMvccVersion(RandomTestUtil.nextLong());

		newRecentLayoutBranch.setGroupId(RandomTestUtil.nextLong());

		newRecentLayoutBranch.setCompanyId(RandomTestUtil.nextLong());

		newRecentLayoutBranch.setUserId(RandomTestUtil.nextLong());

		newRecentLayoutBranch.setLayoutBranchId(RandomTestUtil.nextLong());

		newRecentLayoutBranch.setLayoutSetBranchId(RandomTestUtil.nextLong());

		newRecentLayoutBranch.setPlid(RandomTestUtil.nextLong());

		_recentLayoutBranchs.add(_persistence.update(newRecentLayoutBranch));

		RecentLayoutBranch existingRecentLayoutBranch = _persistence.findByPrimaryKey(newRecentLayoutBranch.getPrimaryKey());

		Assert.assertEquals(existingRecentLayoutBranch.getMvccVersion(),
			newRecentLayoutBranch.getMvccVersion());
		Assert.assertEquals(existingRecentLayoutBranch.getRecentLayoutBranchId(),
			newRecentLayoutBranch.getRecentLayoutBranchId());
		Assert.assertEquals(existingRecentLayoutBranch.getGroupId(),
			newRecentLayoutBranch.getGroupId());
		Assert.assertEquals(existingRecentLayoutBranch.getCompanyId(),
			newRecentLayoutBranch.getCompanyId());
		Assert.assertEquals(existingRecentLayoutBranch.getUserId(),
			newRecentLayoutBranch.getUserId());
		Assert.assertEquals(existingRecentLayoutBranch.getLayoutBranchId(),
			newRecentLayoutBranch.getLayoutBranchId());
		Assert.assertEquals(existingRecentLayoutBranch.getLayoutSetBranchId(),
			newRecentLayoutBranch.getLayoutSetBranchId());
		Assert.assertEquals(existingRecentLayoutBranch.getPlid(),
			newRecentLayoutBranch.getPlid());
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testCountByLayoutBranchId() throws Exception {
		_persistence.countByLayoutBranchId(RandomTestUtil.nextLong());

		_persistence.countByLayoutBranchId(0L);
	}

	@Test
	public void testCountByU_L_P() throws Exception {
		_persistence.countByU_L_P(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByU_L_P(0L, 0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RecentLayoutBranch newRecentLayoutBranch = addRecentLayoutBranch();

		RecentLayoutBranch existingRecentLayoutBranch = _persistence.findByPrimaryKey(newRecentLayoutBranch.getPrimaryKey());

		Assert.assertEquals(existingRecentLayoutBranch, newRecentLayoutBranch);
	}

	@Test(expected = NoSuchRecentLayoutBranchException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RecentLayoutBranch> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("RecentLayoutBranch",
			"mvccVersion", true, "recentLayoutBranchId", true, "groupId", true,
			"companyId", true, "userId", true, "layoutBranchId", true,
			"layoutSetBranchId", true, "plid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RecentLayoutBranch newRecentLayoutBranch = addRecentLayoutBranch();

		RecentLayoutBranch existingRecentLayoutBranch = _persistence.fetchByPrimaryKey(newRecentLayoutBranch.getPrimaryKey());

		Assert.assertEquals(existingRecentLayoutBranch, newRecentLayoutBranch);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RecentLayoutBranch missingRecentLayoutBranch = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRecentLayoutBranch);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RecentLayoutBranch newRecentLayoutBranch1 = addRecentLayoutBranch();
		RecentLayoutBranch newRecentLayoutBranch2 = addRecentLayoutBranch();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRecentLayoutBranch1.getPrimaryKey());
		primaryKeys.add(newRecentLayoutBranch2.getPrimaryKey());

		Map<Serializable, RecentLayoutBranch> recentLayoutBranchs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, recentLayoutBranchs.size());
		Assert.assertEquals(newRecentLayoutBranch1,
			recentLayoutBranchs.get(newRecentLayoutBranch1.getPrimaryKey()));
		Assert.assertEquals(newRecentLayoutBranch2,
			recentLayoutBranchs.get(newRecentLayoutBranch2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RecentLayoutBranch> recentLayoutBranchs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(recentLayoutBranchs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RecentLayoutBranch newRecentLayoutBranch = addRecentLayoutBranch();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRecentLayoutBranch.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RecentLayoutBranch> recentLayoutBranchs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, recentLayoutBranchs.size());
		Assert.assertEquals(newRecentLayoutBranch,
			recentLayoutBranchs.get(newRecentLayoutBranch.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RecentLayoutBranch> recentLayoutBranchs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(recentLayoutBranchs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RecentLayoutBranch newRecentLayoutBranch = addRecentLayoutBranch();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRecentLayoutBranch.getPrimaryKey());

		Map<Serializable, RecentLayoutBranch> recentLayoutBranchs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, recentLayoutBranchs.size());
		Assert.assertEquals(newRecentLayoutBranch,
			recentLayoutBranchs.get(newRecentLayoutBranch.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RecentLayoutBranchLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RecentLayoutBranch>() {
				@Override
				public void performAction(RecentLayoutBranch recentLayoutBranch) {
					Assert.assertNotNull(recentLayoutBranch);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RecentLayoutBranch newRecentLayoutBranch = addRecentLayoutBranch();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecentLayoutBranch.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("recentLayoutBranchId",
				newRecentLayoutBranch.getRecentLayoutBranchId()));

		List<RecentLayoutBranch> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RecentLayoutBranch existingRecentLayoutBranch = result.get(0);

		Assert.assertEquals(existingRecentLayoutBranch, newRecentLayoutBranch);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecentLayoutBranch.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("recentLayoutBranchId",
				RandomTestUtil.nextLong()));

		List<RecentLayoutBranch> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RecentLayoutBranch newRecentLayoutBranch = addRecentLayoutBranch();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecentLayoutBranch.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"recentLayoutBranchId"));

		Object newRecentLayoutBranchId = newRecentLayoutBranch.getRecentLayoutBranchId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("recentLayoutBranchId",
				new Object[] { newRecentLayoutBranchId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRecentLayoutBranchId = result.get(0);

		Assert.assertEquals(existingRecentLayoutBranchId,
			newRecentLayoutBranchId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecentLayoutBranch.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"recentLayoutBranchId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("recentLayoutBranchId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		RecentLayoutBranch newRecentLayoutBranch = addRecentLayoutBranch();

		_persistence.clearCache();

		RecentLayoutBranch existingRecentLayoutBranch = _persistence.findByPrimaryKey(newRecentLayoutBranch.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingRecentLayoutBranch.getUserId()),
			ReflectionTestUtil.<Long>invoke(existingRecentLayoutBranch,
				"getOriginalUserId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingRecentLayoutBranch.getLayoutSetBranchId()),
			ReflectionTestUtil.<Long>invoke(existingRecentLayoutBranch,
				"getOriginalLayoutSetBranchId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingRecentLayoutBranch.getPlid()),
			ReflectionTestUtil.<Long>invoke(existingRecentLayoutBranch,
				"getOriginalPlid", new Class<?>[0]));
	}

	protected RecentLayoutBranch addRecentLayoutBranch()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		RecentLayoutBranch recentLayoutBranch = _persistence.create(pk);

		recentLayoutBranch.setMvccVersion(RandomTestUtil.nextLong());

		recentLayoutBranch.setGroupId(RandomTestUtil.nextLong());

		recentLayoutBranch.setCompanyId(RandomTestUtil.nextLong());

		recentLayoutBranch.setUserId(RandomTestUtil.nextLong());

		recentLayoutBranch.setLayoutBranchId(RandomTestUtil.nextLong());

		recentLayoutBranch.setLayoutSetBranchId(RandomTestUtil.nextLong());

		recentLayoutBranch.setPlid(RandomTestUtil.nextLong());

		_recentLayoutBranchs.add(_persistence.update(recentLayoutBranch));

		return recentLayoutBranch;
	}

	private List<RecentLayoutBranch> _recentLayoutBranchs = new ArrayList<RecentLayoutBranch>();
	private RecentLayoutBranchPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}