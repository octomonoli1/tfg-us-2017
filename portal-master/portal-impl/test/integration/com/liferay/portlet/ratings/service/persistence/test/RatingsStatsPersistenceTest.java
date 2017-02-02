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

package com.liferay.portlet.ratings.service.persistence.test;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
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

import com.liferay.ratings.kernel.exception.NoSuchStatsException;
import com.liferay.ratings.kernel.model.RatingsStats;
import com.liferay.ratings.kernel.service.RatingsStatsLocalServiceUtil;
import com.liferay.ratings.kernel.service.persistence.RatingsStatsPersistence;
import com.liferay.ratings.kernel.service.persistence.RatingsStatsUtil;

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
public class RatingsStatsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = RatingsStatsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RatingsStats> iterator = _ratingsStatses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RatingsStats ratingsStats = _persistence.create(pk);

		Assert.assertNotNull(ratingsStats);

		Assert.assertEquals(ratingsStats.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RatingsStats newRatingsStats = addRatingsStats();

		_persistence.remove(newRatingsStats);

		RatingsStats existingRatingsStats = _persistence.fetchByPrimaryKey(newRatingsStats.getPrimaryKey());

		Assert.assertNull(existingRatingsStats);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRatingsStats();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RatingsStats newRatingsStats = _persistence.create(pk);

		newRatingsStats.setCompanyId(RandomTestUtil.nextLong());

		newRatingsStats.setClassNameId(RandomTestUtil.nextLong());

		newRatingsStats.setClassPK(RandomTestUtil.nextLong());

		newRatingsStats.setTotalEntries(RandomTestUtil.nextInt());

		newRatingsStats.setTotalScore(RandomTestUtil.nextDouble());

		newRatingsStats.setAverageScore(RandomTestUtil.nextDouble());

		_ratingsStatses.add(_persistence.update(newRatingsStats));

		RatingsStats existingRatingsStats = _persistence.findByPrimaryKey(newRatingsStats.getPrimaryKey());

		Assert.assertEquals(existingRatingsStats.getStatsId(),
			newRatingsStats.getStatsId());
		Assert.assertEquals(existingRatingsStats.getCompanyId(),
			newRatingsStats.getCompanyId());
		Assert.assertEquals(existingRatingsStats.getClassNameId(),
			newRatingsStats.getClassNameId());
		Assert.assertEquals(existingRatingsStats.getClassPK(),
			newRatingsStats.getClassPK());
		Assert.assertEquals(existingRatingsStats.getTotalEntries(),
			newRatingsStats.getTotalEntries());
		AssertUtils.assertEquals(existingRatingsStats.getTotalScore(),
			newRatingsStats.getTotalScore());
		AssertUtils.assertEquals(existingRatingsStats.getAverageScore(),
			newRatingsStats.getAverageScore());
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RatingsStats newRatingsStats = addRatingsStats();

		RatingsStats existingRatingsStats = _persistence.findByPrimaryKey(newRatingsStats.getPrimaryKey());

		Assert.assertEquals(existingRatingsStats, newRatingsStats);
	}

	@Test(expected = NoSuchStatsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RatingsStats> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("RatingsStats", "statsId",
			true, "companyId", true, "classNameId", true, "classPK", true,
			"totalEntries", true, "totalScore", true, "averageScore", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RatingsStats newRatingsStats = addRatingsStats();

		RatingsStats existingRatingsStats = _persistence.fetchByPrimaryKey(newRatingsStats.getPrimaryKey());

		Assert.assertEquals(existingRatingsStats, newRatingsStats);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RatingsStats missingRatingsStats = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRatingsStats);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RatingsStats newRatingsStats1 = addRatingsStats();
		RatingsStats newRatingsStats2 = addRatingsStats();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRatingsStats1.getPrimaryKey());
		primaryKeys.add(newRatingsStats2.getPrimaryKey());

		Map<Serializable, RatingsStats> ratingsStatses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ratingsStatses.size());
		Assert.assertEquals(newRatingsStats1,
			ratingsStatses.get(newRatingsStats1.getPrimaryKey()));
		Assert.assertEquals(newRatingsStats2,
			ratingsStatses.get(newRatingsStats2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RatingsStats> ratingsStatses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ratingsStatses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RatingsStats newRatingsStats = addRatingsStats();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRatingsStats.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RatingsStats> ratingsStatses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ratingsStatses.size());
		Assert.assertEquals(newRatingsStats,
			ratingsStatses.get(newRatingsStats.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RatingsStats> ratingsStatses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ratingsStatses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RatingsStats newRatingsStats = addRatingsStats();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRatingsStats.getPrimaryKey());

		Map<Serializable, RatingsStats> ratingsStatses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ratingsStatses.size());
		Assert.assertEquals(newRatingsStats,
			ratingsStatses.get(newRatingsStats.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RatingsStatsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RatingsStats>() {
				@Override
				public void performAction(RatingsStats ratingsStats) {
					Assert.assertNotNull(ratingsStats);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RatingsStats newRatingsStats = addRatingsStats();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RatingsStats.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("statsId",
				newRatingsStats.getStatsId()));

		List<RatingsStats> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RatingsStats existingRatingsStats = result.get(0);

		Assert.assertEquals(existingRatingsStats, newRatingsStats);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RatingsStats.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("statsId",
				RandomTestUtil.nextLong()));

		List<RatingsStats> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RatingsStats newRatingsStats = addRatingsStats();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RatingsStats.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("statsId"));

		Object newStatsId = newRatingsStats.getStatsId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("statsId",
				new Object[] { newStatsId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingStatsId = result.get(0);

		Assert.assertEquals(existingStatsId, newStatsId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RatingsStats.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("statsId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("statsId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		RatingsStats newRatingsStats = addRatingsStats();

		_persistence.clearCache();

		RatingsStats existingRatingsStats = _persistence.findByPrimaryKey(newRatingsStats.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingRatingsStats.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingRatingsStats,
				"getOriginalClassNameId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingRatingsStats.getClassPK()),
			ReflectionTestUtil.<Long>invoke(existingRatingsStats,
				"getOriginalClassPK", new Class<?>[0]));
	}

	protected RatingsStats addRatingsStats() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RatingsStats ratingsStats = _persistence.create(pk);

		ratingsStats.setCompanyId(RandomTestUtil.nextLong());

		ratingsStats.setClassNameId(RandomTestUtil.nextLong());

		ratingsStats.setClassPK(RandomTestUtil.nextLong());

		ratingsStats.setTotalEntries(RandomTestUtil.nextInt());

		ratingsStats.setTotalScore(RandomTestUtil.nextDouble());

		ratingsStats.setAverageScore(RandomTestUtil.nextDouble());

		_ratingsStatses.add(_persistence.update(ratingsStats));

		return ratingsStats;
	}

	private List<RatingsStats> _ratingsStatses = new ArrayList<RatingsStats>();
	private RatingsStatsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}