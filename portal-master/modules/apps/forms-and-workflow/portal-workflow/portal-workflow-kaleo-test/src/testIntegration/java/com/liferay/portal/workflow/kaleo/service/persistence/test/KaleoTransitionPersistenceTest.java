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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.workflow.kaleo.exception.NoSuchTransitionException;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.service.KaleoTransitionLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTransitionPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTransitionUtil;

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
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class KaleoTransitionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = KaleoTransitionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<KaleoTransition> iterator = _kaleoTransitions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTransition kaleoTransition = _persistence.create(pk);

		Assert.assertNotNull(kaleoTransition);

		Assert.assertEquals(kaleoTransition.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		KaleoTransition newKaleoTransition = addKaleoTransition();

		_persistence.remove(newKaleoTransition);

		KaleoTransition existingKaleoTransition = _persistence.fetchByPrimaryKey(newKaleoTransition.getPrimaryKey());

		Assert.assertNull(existingKaleoTransition);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addKaleoTransition();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTransition newKaleoTransition = _persistence.create(pk);

		newKaleoTransition.setGroupId(RandomTestUtil.nextLong());

		newKaleoTransition.setCompanyId(RandomTestUtil.nextLong());

		newKaleoTransition.setUserId(RandomTestUtil.nextLong());

		newKaleoTransition.setUserName(RandomTestUtil.randomString());

		newKaleoTransition.setCreateDate(RandomTestUtil.nextDate());

		newKaleoTransition.setModifiedDate(RandomTestUtil.nextDate());

		newKaleoTransition.setKaleoDefinitionId(RandomTestUtil.nextLong());

		newKaleoTransition.setKaleoNodeId(RandomTestUtil.nextLong());

		newKaleoTransition.setName(RandomTestUtil.randomString());

		newKaleoTransition.setDescription(RandomTestUtil.randomString());

		newKaleoTransition.setSourceKaleoNodeId(RandomTestUtil.nextLong());

		newKaleoTransition.setSourceKaleoNodeName(RandomTestUtil.randomString());

		newKaleoTransition.setTargetKaleoNodeId(RandomTestUtil.nextLong());

		newKaleoTransition.setTargetKaleoNodeName(RandomTestUtil.randomString());

		newKaleoTransition.setDefaultTransition(RandomTestUtil.randomBoolean());

		_kaleoTransitions.add(_persistence.update(newKaleoTransition));

		KaleoTransition existingKaleoTransition = _persistence.findByPrimaryKey(newKaleoTransition.getPrimaryKey());

		Assert.assertEquals(existingKaleoTransition.getKaleoTransitionId(),
			newKaleoTransition.getKaleoTransitionId());
		Assert.assertEquals(existingKaleoTransition.getGroupId(),
			newKaleoTransition.getGroupId());
		Assert.assertEquals(existingKaleoTransition.getCompanyId(),
			newKaleoTransition.getCompanyId());
		Assert.assertEquals(existingKaleoTransition.getUserId(),
			newKaleoTransition.getUserId());
		Assert.assertEquals(existingKaleoTransition.getUserName(),
			newKaleoTransition.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingKaleoTransition.getCreateDate()),
			Time.getShortTimestamp(newKaleoTransition.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingKaleoTransition.getModifiedDate()),
			Time.getShortTimestamp(newKaleoTransition.getModifiedDate()));
		Assert.assertEquals(existingKaleoTransition.getKaleoDefinitionId(),
			newKaleoTransition.getKaleoDefinitionId());
		Assert.assertEquals(existingKaleoTransition.getKaleoNodeId(),
			newKaleoTransition.getKaleoNodeId());
		Assert.assertEquals(existingKaleoTransition.getName(),
			newKaleoTransition.getName());
		Assert.assertEquals(existingKaleoTransition.getDescription(),
			newKaleoTransition.getDescription());
		Assert.assertEquals(existingKaleoTransition.getSourceKaleoNodeId(),
			newKaleoTransition.getSourceKaleoNodeId());
		Assert.assertEquals(existingKaleoTransition.getSourceKaleoNodeName(),
			newKaleoTransition.getSourceKaleoNodeName());
		Assert.assertEquals(existingKaleoTransition.getTargetKaleoNodeId(),
			newKaleoTransition.getTargetKaleoNodeId());
		Assert.assertEquals(existingKaleoTransition.getTargetKaleoNodeName(),
			newKaleoTransition.getTargetKaleoNodeName());
		Assert.assertEquals(existingKaleoTransition.getDefaultTransition(),
			newKaleoTransition.getDefaultTransition());
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
	public void testCountByKNI_N() throws Exception {
		_persistence.countByKNI_N(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByKNI_N(0L, StringPool.NULL);

		_persistence.countByKNI_N(0L, (String)null);
	}

	@Test
	public void testCountByKNI_DT() throws Exception {
		_persistence.countByKNI_DT(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByKNI_DT(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		KaleoTransition newKaleoTransition = addKaleoTransition();

		KaleoTransition existingKaleoTransition = _persistence.findByPrimaryKey(newKaleoTransition.getPrimaryKey());

		Assert.assertEquals(existingKaleoTransition, newKaleoTransition);
	}

	@Test(expected = NoSuchTransitionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<KaleoTransition> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("KaleoTransition",
			"kaleoTransitionId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "kaleoDefinitionId", true, "kaleoNodeId",
			true, "name", true, "description", true, "sourceKaleoNodeId", true,
			"sourceKaleoNodeName", true, "targetKaleoNodeId", true,
			"targetKaleoNodeName", true, "defaultTransition", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		KaleoTransition newKaleoTransition = addKaleoTransition();

		KaleoTransition existingKaleoTransition = _persistence.fetchByPrimaryKey(newKaleoTransition.getPrimaryKey());

		Assert.assertEquals(existingKaleoTransition, newKaleoTransition);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTransition missingKaleoTransition = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingKaleoTransition);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		KaleoTransition newKaleoTransition1 = addKaleoTransition();
		KaleoTransition newKaleoTransition2 = addKaleoTransition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoTransition1.getPrimaryKey());
		primaryKeys.add(newKaleoTransition2.getPrimaryKey());

		Map<Serializable, KaleoTransition> kaleoTransitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, kaleoTransitions.size());
		Assert.assertEquals(newKaleoTransition1,
			kaleoTransitions.get(newKaleoTransition1.getPrimaryKey()));
		Assert.assertEquals(newKaleoTransition2,
			kaleoTransitions.get(newKaleoTransition2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, KaleoTransition> kaleoTransitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kaleoTransitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		KaleoTransition newKaleoTransition = addKaleoTransition();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoTransition.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, KaleoTransition> kaleoTransitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kaleoTransitions.size());
		Assert.assertEquals(newKaleoTransition,
			kaleoTransitions.get(newKaleoTransition.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, KaleoTransition> kaleoTransitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kaleoTransitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		KaleoTransition newKaleoTransition = addKaleoTransition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoTransition.getPrimaryKey());

		Map<Serializable, KaleoTransition> kaleoTransitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kaleoTransitions.size());
		Assert.assertEquals(newKaleoTransition,
			kaleoTransitions.get(newKaleoTransition.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = KaleoTransitionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<KaleoTransition>() {
				@Override
				public void performAction(KaleoTransition kaleoTransition) {
					Assert.assertNotNull(kaleoTransition);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		KaleoTransition newKaleoTransition = addKaleoTransition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTransition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("kaleoTransitionId",
				newKaleoTransition.getKaleoTransitionId()));

		List<KaleoTransition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		KaleoTransition existingKaleoTransition = result.get(0);

		Assert.assertEquals(existingKaleoTransition, newKaleoTransition);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTransition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("kaleoTransitionId",
				RandomTestUtil.nextLong()));

		List<KaleoTransition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		KaleoTransition newKaleoTransition = addKaleoTransition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTransition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"kaleoTransitionId"));

		Object newKaleoTransitionId = newKaleoTransition.getKaleoTransitionId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("kaleoTransitionId",
				new Object[] { newKaleoTransitionId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingKaleoTransitionId = result.get(0);

		Assert.assertEquals(existingKaleoTransitionId, newKaleoTransitionId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoTransition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"kaleoTransitionId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("kaleoTransitionId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		KaleoTransition newKaleoTransition = addKaleoTransition();

		_persistence.clearCache();

		KaleoTransition existingKaleoTransition = _persistence.findByPrimaryKey(newKaleoTransition.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingKaleoTransition.getKaleoNodeId()),
			ReflectionTestUtil.<Long>invoke(existingKaleoTransition,
				"getOriginalKaleoNodeId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingKaleoTransition.getName(),
				ReflectionTestUtil.invoke(existingKaleoTransition,
					"getOriginalName", new Class<?>[0])));

		Assert.assertEquals(Long.valueOf(
				existingKaleoTransition.getKaleoNodeId()),
			ReflectionTestUtil.<Long>invoke(existingKaleoTransition,
				"getOriginalKaleoNodeId", new Class<?>[0]));
		Assert.assertEquals(Boolean.valueOf(
				existingKaleoTransition.getDefaultTransition()),
			ReflectionTestUtil.<Boolean>invoke(existingKaleoTransition,
				"getOriginalDefaultTransition", new Class<?>[0]));
	}

	protected KaleoTransition addKaleoTransition() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoTransition kaleoTransition = _persistence.create(pk);

		kaleoTransition.setGroupId(RandomTestUtil.nextLong());

		kaleoTransition.setCompanyId(RandomTestUtil.nextLong());

		kaleoTransition.setUserId(RandomTestUtil.nextLong());

		kaleoTransition.setUserName(RandomTestUtil.randomString());

		kaleoTransition.setCreateDate(RandomTestUtil.nextDate());

		kaleoTransition.setModifiedDate(RandomTestUtil.nextDate());

		kaleoTransition.setKaleoDefinitionId(RandomTestUtil.nextLong());

		kaleoTransition.setKaleoNodeId(RandomTestUtil.nextLong());

		kaleoTransition.setName(RandomTestUtil.randomString());

		kaleoTransition.setDescription(RandomTestUtil.randomString());

		kaleoTransition.setSourceKaleoNodeId(RandomTestUtil.nextLong());

		kaleoTransition.setSourceKaleoNodeName(RandomTestUtil.randomString());

		kaleoTransition.setTargetKaleoNodeId(RandomTestUtil.nextLong());

		kaleoTransition.setTargetKaleoNodeName(RandomTestUtil.randomString());

		kaleoTransition.setDefaultTransition(RandomTestUtil.randomBoolean());

		_kaleoTransitions.add(_persistence.update(kaleoTransition));

		return kaleoTransition;
	}

	private List<KaleoTransition> _kaleoTransitions = new ArrayList<KaleoTransition>();
	private KaleoTransitionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}