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
import com.liferay.portal.workflow.kaleo.exception.NoSuchTaskAssignmentInstanceException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentInstanceLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentInstancePersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentInstanceUtil;

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
public class KaleoTaskAssignmentInstancePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = KaleoTaskAssignmentInstanceUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<KaleoTaskAssignmentInstance> iterator = _kaleoTaskAssignmentInstances.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = _persistence.create(pk);

		Assert.assertNotNull(kaleoTaskAssignmentInstance);

		Assert.assertEquals(kaleoTaskAssignmentInstance.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		KaleoTaskAssignmentInstance newKaleoTaskAssignmentInstance = addKaleoTaskAssignmentInstance();

		_persistence.remove(newKaleoTaskAssignmentInstance);

		KaleoTaskAssignmentInstance existingKaleoTaskAssignmentInstance = _persistence.fetchByPrimaryKey(newKaleoTaskAssignmentInstance.getPrimaryKey());

		Assert.assertNull(existingKaleoTaskAssignmentInstance);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addKaleoTaskAssignmentInstance();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTaskAssignmentInstance newKaleoTaskAssignmentInstance = _persistence.create(pk);

		newKaleoTaskAssignmentInstance.setGroupId(RandomTestUtil.nextLong());

		newKaleoTaskAssignmentInstance.setCompanyId(RandomTestUtil.nextLong());

		newKaleoTaskAssignmentInstance.setUserId(RandomTestUtil.nextLong());

		newKaleoTaskAssignmentInstance.setUserName(RandomTestUtil.randomString());

		newKaleoTaskAssignmentInstance.setCreateDate(RandomTestUtil.nextDate());

		newKaleoTaskAssignmentInstance.setModifiedDate(RandomTestUtil.nextDate());

		newKaleoTaskAssignmentInstance.setKaleoDefinitionId(RandomTestUtil.nextLong());

		newKaleoTaskAssignmentInstance.setKaleoInstanceId(RandomTestUtil.nextLong());

		newKaleoTaskAssignmentInstance.setKaleoInstanceTokenId(RandomTestUtil.nextLong());

		newKaleoTaskAssignmentInstance.setKaleoTaskInstanceTokenId(RandomTestUtil.nextLong());

		newKaleoTaskAssignmentInstance.setKaleoTaskId(RandomTestUtil.nextLong());

		newKaleoTaskAssignmentInstance.setKaleoTaskName(RandomTestUtil.randomString());

		newKaleoTaskAssignmentInstance.setAssigneeClassName(RandomTestUtil.randomString());

		newKaleoTaskAssignmentInstance.setAssigneeClassPK(RandomTestUtil.nextLong());

		newKaleoTaskAssignmentInstance.setCompleted(RandomTestUtil.randomBoolean());

		newKaleoTaskAssignmentInstance.setCompletionDate(RandomTestUtil.nextDate());

		_kaleoTaskAssignmentInstances.add(_persistence.update(
				newKaleoTaskAssignmentInstance));

		KaleoTaskAssignmentInstance existingKaleoTaskAssignmentInstance = _persistence.findByPrimaryKey(newKaleoTaskAssignmentInstance.getPrimaryKey());

		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getKaleoTaskAssignmentInstanceId(),
			newKaleoTaskAssignmentInstance.getKaleoTaskAssignmentInstanceId());
		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getGroupId(),
			newKaleoTaskAssignmentInstance.getGroupId());
		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getCompanyId(),
			newKaleoTaskAssignmentInstance.getCompanyId());
		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getUserId(),
			newKaleoTaskAssignmentInstance.getUserId());
		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getUserName(),
			newKaleoTaskAssignmentInstance.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingKaleoTaskAssignmentInstance.getCreateDate()),
			Time.getShortTimestamp(
				newKaleoTaskAssignmentInstance.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingKaleoTaskAssignmentInstance.getModifiedDate()),
			Time.getShortTimestamp(
				newKaleoTaskAssignmentInstance.getModifiedDate()));
		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getKaleoDefinitionId(),
			newKaleoTaskAssignmentInstance.getKaleoDefinitionId());
		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getKaleoInstanceId(),
			newKaleoTaskAssignmentInstance.getKaleoInstanceId());
		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getKaleoInstanceTokenId(),
			newKaleoTaskAssignmentInstance.getKaleoInstanceTokenId());
		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getKaleoTaskInstanceTokenId(),
			newKaleoTaskAssignmentInstance.getKaleoTaskInstanceTokenId());
		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getKaleoTaskId(),
			newKaleoTaskAssignmentInstance.getKaleoTaskId());
		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getKaleoTaskName(),
			newKaleoTaskAssignmentInstance.getKaleoTaskName());
		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getAssigneeClassName(),
			newKaleoTaskAssignmentInstance.getAssigneeClassName());
		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getAssigneeClassPK(),
			newKaleoTaskAssignmentInstance.getAssigneeClassPK());
		Assert.assertEquals(existingKaleoTaskAssignmentInstance.getCompleted(),
			newKaleoTaskAssignmentInstance.getCompleted());
		Assert.assertEquals(Time.getShortTimestamp(
				existingKaleoTaskAssignmentInstance.getCompletionDate()),
			Time.getShortTimestamp(
				newKaleoTaskAssignmentInstance.getCompletionDate()));
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
	public void testCountByKaleoInstanceId() throws Exception {
		_persistence.countByKaleoInstanceId(RandomTestUtil.nextLong());

		_persistence.countByKaleoInstanceId(0L);
	}

	@Test
	public void testCountBykaleoTaskInstanceTokenId() throws Exception {
		_persistence.countBykaleoTaskInstanceTokenId(RandomTestUtil.nextLong());

		_persistence.countBykaleoTaskInstanceTokenId(0L);
	}

	@Test
	public void testCountByassigneeClassName() throws Exception {
		_persistence.countByassigneeClassName(StringPool.BLANK);

		_persistence.countByassigneeClassName(StringPool.NULL);

		_persistence.countByassigneeClassName((String)null);
	}

	@Test
	public void testCountByG_ACPK() throws Exception {
		_persistence.countByG_ACPK(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_ACPK(0L, 0L);
	}

	@Test
	public void testCountByACN_ACPK() throws Exception {
		_persistence.countByACN_ACPK(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByACN_ACPK(StringPool.NULL, 0L);

		_persistence.countByACN_ACPK((String)null, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		KaleoTaskAssignmentInstance newKaleoTaskAssignmentInstance = addKaleoTaskAssignmentInstance();

		KaleoTaskAssignmentInstance existingKaleoTaskAssignmentInstance = _persistence.findByPrimaryKey(newKaleoTaskAssignmentInstance.getPrimaryKey());

		Assert.assertEquals(existingKaleoTaskAssignmentInstance,
			newKaleoTaskAssignmentInstance);
	}

	@Test(expected = NoSuchTaskAssignmentInstanceException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<KaleoTaskAssignmentInstance> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("KaleoTaskAssignmentInstance",
			"kaleoTaskAssignmentInstanceId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "kaleoDefinitionId", true,
			"kaleoInstanceId", true, "kaleoInstanceTokenId", true,
			"kaleoTaskInstanceTokenId", true, "kaleoTaskId", true,
			"kaleoTaskName", true, "assigneeClassName", true,
			"assigneeClassPK", true, "completed", true, "completionDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		KaleoTaskAssignmentInstance newKaleoTaskAssignmentInstance = addKaleoTaskAssignmentInstance();

		KaleoTaskAssignmentInstance existingKaleoTaskAssignmentInstance = _persistence.fetchByPrimaryKey(newKaleoTaskAssignmentInstance.getPrimaryKey());

		Assert.assertEquals(existingKaleoTaskAssignmentInstance,
			newKaleoTaskAssignmentInstance);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTaskAssignmentInstance missingKaleoTaskAssignmentInstance = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingKaleoTaskAssignmentInstance);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		KaleoTaskAssignmentInstance newKaleoTaskAssignmentInstance1 = addKaleoTaskAssignmentInstance();
		KaleoTaskAssignmentInstance newKaleoTaskAssignmentInstance2 = addKaleoTaskAssignmentInstance();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoTaskAssignmentInstance1.getPrimaryKey());
		primaryKeys.add(newKaleoTaskAssignmentInstance2.getPrimaryKey());

		Map<Serializable, KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, kaleoTaskAssignmentInstances.size());
		Assert.assertEquals(newKaleoTaskAssignmentInstance1,
			kaleoTaskAssignmentInstances.get(
				newKaleoTaskAssignmentInstance1.getPrimaryKey()));
		Assert.assertEquals(newKaleoTaskAssignmentInstance2,
			kaleoTaskAssignmentInstances.get(
				newKaleoTaskAssignmentInstance2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kaleoTaskAssignmentInstances.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		KaleoTaskAssignmentInstance newKaleoTaskAssignmentInstance = addKaleoTaskAssignmentInstance();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoTaskAssignmentInstance.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kaleoTaskAssignmentInstances.size());
		Assert.assertEquals(newKaleoTaskAssignmentInstance,
			kaleoTaskAssignmentInstances.get(
				newKaleoTaskAssignmentInstance.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kaleoTaskAssignmentInstances.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		KaleoTaskAssignmentInstance newKaleoTaskAssignmentInstance = addKaleoTaskAssignmentInstance();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoTaskAssignmentInstance.getPrimaryKey());

		Map<Serializable, KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kaleoTaskAssignmentInstances.size());
		Assert.assertEquals(newKaleoTaskAssignmentInstance,
			kaleoTaskAssignmentInstances.get(
				newKaleoTaskAssignmentInstance.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = KaleoTaskAssignmentInstanceLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<KaleoTaskAssignmentInstance>() {
				@Override
				public void performAction(
					KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
					Assert.assertNotNull(kaleoTaskAssignmentInstance);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		KaleoTaskAssignmentInstance newKaleoTaskAssignmentInstance = addKaleoTaskAssignmentInstance();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTaskAssignmentInstance.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"kaleoTaskAssignmentInstanceId",
				newKaleoTaskAssignmentInstance.getKaleoTaskAssignmentInstanceId()));

		List<KaleoTaskAssignmentInstance> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		KaleoTaskAssignmentInstance existingKaleoTaskAssignmentInstance = result.get(0);

		Assert.assertEquals(existingKaleoTaskAssignmentInstance,
			newKaleoTaskAssignmentInstance);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTaskAssignmentInstance.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"kaleoTaskAssignmentInstanceId", RandomTestUtil.nextLong()));

		List<KaleoTaskAssignmentInstance> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		KaleoTaskAssignmentInstance newKaleoTaskAssignmentInstance = addKaleoTaskAssignmentInstance();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTaskAssignmentInstance.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"kaleoTaskAssignmentInstanceId"));

		Object newKaleoTaskAssignmentInstanceId = newKaleoTaskAssignmentInstance.getKaleoTaskAssignmentInstanceId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"kaleoTaskAssignmentInstanceId",
				new Object[] { newKaleoTaskAssignmentInstanceId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingKaleoTaskAssignmentInstanceId = result.get(0);

		Assert.assertEquals(existingKaleoTaskAssignmentInstanceId,
			newKaleoTaskAssignmentInstanceId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTaskAssignmentInstance.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"kaleoTaskAssignmentInstanceId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"kaleoTaskAssignmentInstanceId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected KaleoTaskAssignmentInstance addKaleoTaskAssignmentInstance()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = _persistence.create(pk);

		kaleoTaskAssignmentInstance.setGroupId(RandomTestUtil.nextLong());

		kaleoTaskAssignmentInstance.setCompanyId(RandomTestUtil.nextLong());

		kaleoTaskAssignmentInstance.setUserId(RandomTestUtil.nextLong());

		kaleoTaskAssignmentInstance.setUserName(RandomTestUtil.randomString());

		kaleoTaskAssignmentInstance.setCreateDate(RandomTestUtil.nextDate());

		kaleoTaskAssignmentInstance.setModifiedDate(RandomTestUtil.nextDate());

		kaleoTaskAssignmentInstance.setKaleoDefinitionId(RandomTestUtil.nextLong());

		kaleoTaskAssignmentInstance.setKaleoInstanceId(RandomTestUtil.nextLong());

		kaleoTaskAssignmentInstance.setKaleoInstanceTokenId(RandomTestUtil.nextLong());

		kaleoTaskAssignmentInstance.setKaleoTaskInstanceTokenId(RandomTestUtil.nextLong());

		kaleoTaskAssignmentInstance.setKaleoTaskId(RandomTestUtil.nextLong());

		kaleoTaskAssignmentInstance.setKaleoTaskName(RandomTestUtil.randomString());

		kaleoTaskAssignmentInstance.setAssigneeClassName(RandomTestUtil.randomString());

		kaleoTaskAssignmentInstance.setAssigneeClassPK(RandomTestUtil.nextLong());

		kaleoTaskAssignmentInstance.setCompleted(RandomTestUtil.randomBoolean());

		kaleoTaskAssignmentInstance.setCompletionDate(RandomTestUtil.nextDate());

		_kaleoTaskAssignmentInstances.add(_persistence.update(
				kaleoTaskAssignmentInstance));

		return kaleoTaskAssignmentInstance;
	}

	private List<KaleoTaskAssignmentInstance> _kaleoTaskAssignmentInstances = new ArrayList<KaleoTaskAssignmentInstance>();
	private KaleoTaskAssignmentInstancePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}