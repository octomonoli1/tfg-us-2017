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
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.TransactionalTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.workflow.kaleo.exception.NoSuchTaskException;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskUtil;

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
public class KaleoTaskPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = KaleoTaskUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<KaleoTask> iterator = _kaleoTasks.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTask kaleoTask = _persistence.create(pk);

		Assert.assertNotNull(kaleoTask);

		Assert.assertEquals(kaleoTask.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		KaleoTask newKaleoTask = addKaleoTask();

		_persistence.remove(newKaleoTask);

		KaleoTask existingKaleoTask = _persistence.fetchByPrimaryKey(newKaleoTask.getPrimaryKey());

		Assert.assertNull(existingKaleoTask);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addKaleoTask();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTask newKaleoTask = _persistence.create(pk);

		newKaleoTask.setGroupId(RandomTestUtil.nextLong());

		newKaleoTask.setCompanyId(RandomTestUtil.nextLong());

		newKaleoTask.setUserId(RandomTestUtil.nextLong());

		newKaleoTask.setUserName(RandomTestUtil.randomString());

		newKaleoTask.setCreateDate(RandomTestUtil.nextDate());

		newKaleoTask.setModifiedDate(RandomTestUtil.nextDate());

		newKaleoTask.setKaleoDefinitionId(RandomTestUtil.nextLong());

		newKaleoTask.setKaleoNodeId(RandomTestUtil.nextLong());

		newKaleoTask.setName(RandomTestUtil.randomString());

		newKaleoTask.setDescription(RandomTestUtil.randomString());

		_kaleoTasks.add(_persistence.update(newKaleoTask));

		KaleoTask existingKaleoTask = _persistence.findByPrimaryKey(newKaleoTask.getPrimaryKey());

		Assert.assertEquals(existingKaleoTask.getKaleoTaskId(),
			newKaleoTask.getKaleoTaskId());
		Assert.assertEquals(existingKaleoTask.getGroupId(),
			newKaleoTask.getGroupId());
		Assert.assertEquals(existingKaleoTask.getCompanyId(),
			newKaleoTask.getCompanyId());
		Assert.assertEquals(existingKaleoTask.getUserId(),
			newKaleoTask.getUserId());
		Assert.assertEquals(existingKaleoTask.getUserName(),
			newKaleoTask.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingKaleoTask.getCreateDate()),
			Time.getShortTimestamp(newKaleoTask.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingKaleoTask.getModifiedDate()),
			Time.getShortTimestamp(newKaleoTask.getModifiedDate()));
		Assert.assertEquals(existingKaleoTask.getKaleoDefinitionId(),
			newKaleoTask.getKaleoDefinitionId());
		Assert.assertEquals(existingKaleoTask.getKaleoNodeId(),
			newKaleoTask.getKaleoNodeId());
		Assert.assertEquals(existingKaleoTask.getName(), newKaleoTask.getName());
		Assert.assertEquals(existingKaleoTask.getDescription(),
			newKaleoTask.getDescription());
	}

	@Test
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(RandomTestUtil.nextLong());

		_persistence.countByCompanyId(0L);
	}

	@Test
	public void testCountByKaleoDefinitionId() throws Exception {
		_persistence.countByKaleoDefinitionId(RandomTestUtil.nextLong());

		_persistence.countByKaleoDefinitionId(0L);
	}

	@Test
	public void testCountByKaleoNodeId() throws Exception {
		_persistence.countByKaleoNodeId(RandomTestUtil.nextLong());

		_persistence.countByKaleoNodeId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		KaleoTask newKaleoTask = addKaleoTask();

		KaleoTask existingKaleoTask = _persistence.findByPrimaryKey(newKaleoTask.getPrimaryKey());

		Assert.assertEquals(existingKaleoTask, newKaleoTask);
	}

	@Test(expected = NoSuchTaskException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<KaleoTask> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("KaleoTask", "kaleoTaskId",
			true, "groupId", true, "companyId", true, "userId", true,
			"userName", true, "createDate", true, "modifiedDate", true,
			"kaleoDefinitionId", true, "kaleoNodeId", true, "name", true,
			"description", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		KaleoTask newKaleoTask = addKaleoTask();

		KaleoTask existingKaleoTask = _persistence.fetchByPrimaryKey(newKaleoTask.getPrimaryKey());

		Assert.assertEquals(existingKaleoTask, newKaleoTask);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTask missingKaleoTask = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingKaleoTask);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		KaleoTask newKaleoTask1 = addKaleoTask();
		KaleoTask newKaleoTask2 = addKaleoTask();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoTask1.getPrimaryKey());
		primaryKeys.add(newKaleoTask2.getPrimaryKey());

		Map<Serializable, KaleoTask> kaleoTasks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, kaleoTasks.size());
		Assert.assertEquals(newKaleoTask1,
			kaleoTasks.get(newKaleoTask1.getPrimaryKey()));
		Assert.assertEquals(newKaleoTask2,
			kaleoTasks.get(newKaleoTask2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, KaleoTask> kaleoTasks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kaleoTasks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		KaleoTask newKaleoTask = addKaleoTask();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoTask.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, KaleoTask> kaleoTasks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kaleoTasks.size());
		Assert.assertEquals(newKaleoTask,
			kaleoTasks.get(newKaleoTask.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, KaleoTask> kaleoTasks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kaleoTasks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		KaleoTask newKaleoTask = addKaleoTask();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoTask.getPrimaryKey());

		Map<Serializable, KaleoTask> kaleoTasks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kaleoTasks.size());
		Assert.assertEquals(newKaleoTask,
			kaleoTasks.get(newKaleoTask.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = KaleoTaskLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<KaleoTask>() {
				@Override
				public void performAction(KaleoTask kaleoTask) {
					Assert.assertNotNull(kaleoTask);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		KaleoTask newKaleoTask = addKaleoTask();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTask.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("kaleoTaskId",
				newKaleoTask.getKaleoTaskId()));

		List<KaleoTask> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		KaleoTask existingKaleoTask = result.get(0);

		Assert.assertEquals(existingKaleoTask, newKaleoTask);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTask.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("kaleoTaskId",
				RandomTestUtil.nextLong()));

		List<KaleoTask> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		KaleoTask newKaleoTask = addKaleoTask();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTask.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("kaleoTaskId"));

		Object newKaleoTaskId = newKaleoTask.getKaleoTaskId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("kaleoTaskId",
				new Object[] { newKaleoTaskId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingKaleoTaskId = result.get(0);

		Assert.assertEquals(existingKaleoTaskId, newKaleoTaskId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTask.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("kaleoTaskId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("kaleoTaskId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		KaleoTask newKaleoTask = addKaleoTask();

		_persistence.clearCache();

		KaleoTask existingKaleoTask = _persistence.findByPrimaryKey(newKaleoTask.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingKaleoTask.getKaleoNodeId()),
			ReflectionTestUtil.<Long>invoke(existingKaleoTask,
				"getOriginalKaleoNodeId", new Class<?>[0]));
	}

	protected KaleoTask addKaleoTask() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTask kaleoTask = _persistence.create(pk);

		kaleoTask.setGroupId(RandomTestUtil.nextLong());

		kaleoTask.setCompanyId(RandomTestUtil.nextLong());

		kaleoTask.setUserId(RandomTestUtil.nextLong());

		kaleoTask.setUserName(RandomTestUtil.randomString());

		kaleoTask.setCreateDate(RandomTestUtil.nextDate());

		kaleoTask.setModifiedDate(RandomTestUtil.nextDate());

		kaleoTask.setKaleoDefinitionId(RandomTestUtil.nextLong());

		kaleoTask.setKaleoNodeId(RandomTestUtil.nextLong());

		kaleoTask.setName(RandomTestUtil.randomString());

		kaleoTask.setDescription(RandomTestUtil.randomString());

		_kaleoTasks.add(_persistence.update(kaleoTask));

		return kaleoTask;
	}

	private List<KaleoTask> _kaleoTasks = new ArrayList<KaleoTask>();
	private KaleoTaskPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}