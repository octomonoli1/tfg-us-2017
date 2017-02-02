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

package com.liferay.portal.workflow.kaleo.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.TransactionalTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.workflow.kaleo.exception.NoSuchTimerException;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.service.KaleoTimerLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTimerPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTimerUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

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
@RunWith(Arquillian.class)
public class KaleoTimerPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = KaleoTimerUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<KaleoTimer> iterator = _kaleoTimers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTimer kaleoTimer = _persistence.create(pk);

		Assert.assertNotNull(kaleoTimer);

		Assert.assertEquals(kaleoTimer.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		KaleoTimer newKaleoTimer = addKaleoTimer();

		_persistence.remove(newKaleoTimer);

		KaleoTimer existingKaleoTimer = _persistence.fetchByPrimaryKey(newKaleoTimer.getPrimaryKey());

		Assert.assertNull(existingKaleoTimer);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addKaleoTimer();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTimer newKaleoTimer = _persistence.create(pk);

		newKaleoTimer.setGroupId(RandomTestUtil.nextLong());

		newKaleoTimer.setCompanyId(RandomTestUtil.nextLong());

		newKaleoTimer.setUserId(RandomTestUtil.nextLong());

		newKaleoTimer.setUserName(RandomTestUtil.randomString());

		newKaleoTimer.setCreateDate(RandomTestUtil.nextDate());

		newKaleoTimer.setModifiedDate(RandomTestUtil.nextDate());

		newKaleoTimer.setKaleoClassName(RandomTestUtil.randomString());

		newKaleoTimer.setKaleoClassPK(RandomTestUtil.nextLong());

		newKaleoTimer.setKaleoDefinitionId(RandomTestUtil.nextLong());

		newKaleoTimer.setName(RandomTestUtil.randomString());

		newKaleoTimer.setBlocking(RandomTestUtil.randomBoolean());

		newKaleoTimer.setDescription(RandomTestUtil.randomString());

		newKaleoTimer.setDuration(RandomTestUtil.nextDouble());

		newKaleoTimer.setScale(RandomTestUtil.randomString());

		newKaleoTimer.setRecurrenceDuration(RandomTestUtil.nextDouble());

		newKaleoTimer.setRecurrenceScale(RandomTestUtil.randomString());

		_kaleoTimers.add(_persistence.update(newKaleoTimer));

		KaleoTimer existingKaleoTimer = _persistence.findByPrimaryKey(newKaleoTimer.getPrimaryKey());

		Assert.assertEquals(existingKaleoTimer.getKaleoTimerId(),
			newKaleoTimer.getKaleoTimerId());
		Assert.assertEquals(existingKaleoTimer.getGroupId(),
			newKaleoTimer.getGroupId());
		Assert.assertEquals(existingKaleoTimer.getCompanyId(),
			newKaleoTimer.getCompanyId());
		Assert.assertEquals(existingKaleoTimer.getUserId(),
			newKaleoTimer.getUserId());
		Assert.assertEquals(existingKaleoTimer.getUserName(),
			newKaleoTimer.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingKaleoTimer.getCreateDate()),
			Time.getShortTimestamp(newKaleoTimer.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingKaleoTimer.getModifiedDate()),
			Time.getShortTimestamp(newKaleoTimer.getModifiedDate()));
		Assert.assertEquals(existingKaleoTimer.getKaleoClassName(),
			newKaleoTimer.getKaleoClassName());
		Assert.assertEquals(existingKaleoTimer.getKaleoClassPK(),
			newKaleoTimer.getKaleoClassPK());
		Assert.assertEquals(existingKaleoTimer.getKaleoDefinitionId(),
			newKaleoTimer.getKaleoDefinitionId());
		Assert.assertEquals(existingKaleoTimer.getName(),
			newKaleoTimer.getName());
		Assert.assertEquals(existingKaleoTimer.getBlocking(),
			newKaleoTimer.getBlocking());
		Assert.assertEquals(existingKaleoTimer.getDescription(),
			newKaleoTimer.getDescription());
		AssertUtils.assertEquals(existingKaleoTimer.getDuration(),
			newKaleoTimer.getDuration());
		Assert.assertEquals(existingKaleoTimer.getScale(),
			newKaleoTimer.getScale());
		AssertUtils.assertEquals(existingKaleoTimer.getRecurrenceDuration(),
			newKaleoTimer.getRecurrenceDuration());
		Assert.assertEquals(existingKaleoTimer.getRecurrenceScale(),
			newKaleoTimer.getRecurrenceScale());
	}

	@Test
	public void testCountByKCN_KCPK() throws Exception {
		_persistence.countByKCN_KCPK(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByKCN_KCPK(StringPool.NULL, 0L);

		_persistence.countByKCN_KCPK((String)null, 0L);
	}

	@Test
	public void testCountByKCN_KCPK_Blocking() throws Exception {
		_persistence.countByKCN_KCPK_Blocking(StringPool.BLANK,
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByKCN_KCPK_Blocking(StringPool.NULL, 0L,
			RandomTestUtil.randomBoolean());

		_persistence.countByKCN_KCPK_Blocking((String)null, 0L,
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		KaleoTimer newKaleoTimer = addKaleoTimer();

		KaleoTimer existingKaleoTimer = _persistence.findByPrimaryKey(newKaleoTimer.getPrimaryKey());

		Assert.assertEquals(existingKaleoTimer, newKaleoTimer);
	}

	@Test(expected = NoSuchTimerException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<KaleoTimer> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("KaleoTimer",
			"kaleoTimerId", true, "groupId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"kaleoClassName", true, "kaleoClassPK", true, "kaleoDefinitionId",
			true, "name", true, "blocking", true, "description", true,
			"duration", true, "scale", true, "recurrenceDuration", true,
			"recurrenceScale", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		KaleoTimer newKaleoTimer = addKaleoTimer();

		KaleoTimer existingKaleoTimer = _persistence.fetchByPrimaryKey(newKaleoTimer.getPrimaryKey());

		Assert.assertEquals(existingKaleoTimer, newKaleoTimer);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTimer missingKaleoTimer = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingKaleoTimer);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		KaleoTimer newKaleoTimer1 = addKaleoTimer();
		KaleoTimer newKaleoTimer2 = addKaleoTimer();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoTimer1.getPrimaryKey());
		primaryKeys.add(newKaleoTimer2.getPrimaryKey());

		Map<Serializable, KaleoTimer> kaleoTimers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, kaleoTimers.size());
		Assert.assertEquals(newKaleoTimer1,
			kaleoTimers.get(newKaleoTimer1.getPrimaryKey()));
		Assert.assertEquals(newKaleoTimer2,
			kaleoTimers.get(newKaleoTimer2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, KaleoTimer> kaleoTimers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kaleoTimers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		KaleoTimer newKaleoTimer = addKaleoTimer();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoTimer.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, KaleoTimer> kaleoTimers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kaleoTimers.size());
		Assert.assertEquals(newKaleoTimer,
			kaleoTimers.get(newKaleoTimer.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, KaleoTimer> kaleoTimers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kaleoTimers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		KaleoTimer newKaleoTimer = addKaleoTimer();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoTimer.getPrimaryKey());

		Map<Serializable, KaleoTimer> kaleoTimers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kaleoTimers.size());
		Assert.assertEquals(newKaleoTimer,
			kaleoTimers.get(newKaleoTimer.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = KaleoTimerLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<KaleoTimer>() {
				@Override
				public void performAction(KaleoTimer kaleoTimer) {
					Assert.assertNotNull(kaleoTimer);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		KaleoTimer newKaleoTimer = addKaleoTimer();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTimer.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("kaleoTimerId",
				newKaleoTimer.getKaleoTimerId()));

		List<KaleoTimer> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		KaleoTimer existingKaleoTimer = result.get(0);

		Assert.assertEquals(existingKaleoTimer, newKaleoTimer);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTimer.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("kaleoTimerId",
				RandomTestUtil.nextLong()));

		List<KaleoTimer> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		KaleoTimer newKaleoTimer = addKaleoTimer();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTimer.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"kaleoTimerId"));

		Object newKaleoTimerId = newKaleoTimer.getKaleoTimerId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("kaleoTimerId",
				new Object[] { newKaleoTimerId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingKaleoTimerId = result.get(0);

		Assert.assertEquals(existingKaleoTimerId, newKaleoTimerId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTimer.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"kaleoTimerId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("kaleoTimerId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected KaleoTimer addKaleoTimer() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTimer kaleoTimer = _persistence.create(pk);

		kaleoTimer.setGroupId(RandomTestUtil.nextLong());

		kaleoTimer.setCompanyId(RandomTestUtil.nextLong());

		kaleoTimer.setUserId(RandomTestUtil.nextLong());

		kaleoTimer.setUserName(RandomTestUtil.randomString());

		kaleoTimer.setCreateDate(RandomTestUtil.nextDate());

		kaleoTimer.setModifiedDate(RandomTestUtil.nextDate());

		kaleoTimer.setKaleoClassName(RandomTestUtil.randomString());

		kaleoTimer.setKaleoClassPK(RandomTestUtil.nextLong());

		kaleoTimer.setKaleoDefinitionId(RandomTestUtil.nextLong());

		kaleoTimer.setName(RandomTestUtil.randomString());

		kaleoTimer.setBlocking(RandomTestUtil.randomBoolean());

		kaleoTimer.setDescription(RandomTestUtil.randomString());

		kaleoTimer.setDuration(RandomTestUtil.nextDouble());

		kaleoTimer.setScale(RandomTestUtil.randomString());

		kaleoTimer.setRecurrenceDuration(RandomTestUtil.nextDouble());

		kaleoTimer.setRecurrenceScale(RandomTestUtil.randomString());

		_kaleoTimers.add(_persistence.update(kaleoTimer));

		return kaleoTimer;
	}

	private List<KaleoTimer> _kaleoTimers = new ArrayList<KaleoTimer>();
	private KaleoTimerPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}