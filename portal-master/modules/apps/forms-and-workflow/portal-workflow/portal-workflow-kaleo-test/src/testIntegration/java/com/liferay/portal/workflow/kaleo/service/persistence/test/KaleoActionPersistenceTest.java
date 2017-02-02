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
import com.liferay.portal.workflow.kaleo.exception.NoSuchActionException;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.service.KaleoActionLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoActionPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoActionUtil;

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
public class KaleoActionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = KaleoActionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<KaleoAction> iterator = _kaleoActions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoAction kaleoAction = _persistence.create(pk);

		Assert.assertNotNull(kaleoAction);

		Assert.assertEquals(kaleoAction.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		KaleoAction newKaleoAction = addKaleoAction();

		_persistence.remove(newKaleoAction);

		KaleoAction existingKaleoAction = _persistence.fetchByPrimaryKey(newKaleoAction.getPrimaryKey());

		Assert.assertNull(existingKaleoAction);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addKaleoAction();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoAction newKaleoAction = _persistence.create(pk);

		newKaleoAction.setGroupId(RandomTestUtil.nextLong());

		newKaleoAction.setCompanyId(RandomTestUtil.nextLong());

		newKaleoAction.setUserId(RandomTestUtil.nextLong());

		newKaleoAction.setUserName(RandomTestUtil.randomString());

		newKaleoAction.setCreateDate(RandomTestUtil.nextDate());

		newKaleoAction.setModifiedDate(RandomTestUtil.nextDate());

		newKaleoAction.setKaleoClassName(RandomTestUtil.randomString());

		newKaleoAction.setKaleoClassPK(RandomTestUtil.nextLong());

		newKaleoAction.setKaleoDefinitionId(RandomTestUtil.nextLong());

		newKaleoAction.setKaleoNodeName(RandomTestUtil.randomString());

		newKaleoAction.setName(RandomTestUtil.randomString());

		newKaleoAction.setDescription(RandomTestUtil.randomString());

		newKaleoAction.setExecutionType(RandomTestUtil.randomString());

		newKaleoAction.setScript(RandomTestUtil.randomString());

		newKaleoAction.setScriptLanguage(RandomTestUtil.randomString());

		newKaleoAction.setScriptRequiredContexts(RandomTestUtil.randomString());

		newKaleoAction.setPriority(RandomTestUtil.nextInt());

		_kaleoActions.add(_persistence.update(newKaleoAction));

		KaleoAction existingKaleoAction = _persistence.findByPrimaryKey(newKaleoAction.getPrimaryKey());

		Assert.assertEquals(existingKaleoAction.getKaleoActionId(),
			newKaleoAction.getKaleoActionId());
		Assert.assertEquals(existingKaleoAction.getGroupId(),
			newKaleoAction.getGroupId());
		Assert.assertEquals(existingKaleoAction.getCompanyId(),
			newKaleoAction.getCompanyId());
		Assert.assertEquals(existingKaleoAction.getUserId(),
			newKaleoAction.getUserId());
		Assert.assertEquals(existingKaleoAction.getUserName(),
			newKaleoAction.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingKaleoAction.getCreateDate()),
			Time.getShortTimestamp(newKaleoAction.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingKaleoAction.getModifiedDate()),
			Time.getShortTimestamp(newKaleoAction.getModifiedDate()));
		Assert.assertEquals(existingKaleoAction.getKaleoClassName(),
			newKaleoAction.getKaleoClassName());
		Assert.assertEquals(existingKaleoAction.getKaleoClassPK(),
			newKaleoAction.getKaleoClassPK());
		Assert.assertEquals(existingKaleoAction.getKaleoDefinitionId(),
			newKaleoAction.getKaleoDefinitionId());
		Assert.assertEquals(existingKaleoAction.getKaleoNodeName(),
			newKaleoAction.getKaleoNodeName());
		Assert.assertEquals(existingKaleoAction.getName(),
			newKaleoAction.getName());
		Assert.assertEquals(existingKaleoAction.getDescription(),
			newKaleoAction.getDescription());
		Assert.assertEquals(existingKaleoAction.getExecutionType(),
			newKaleoAction.getExecutionType());
		Assert.assertEquals(existingKaleoAction.getScript(),
			newKaleoAction.getScript());
		Assert.assertEquals(existingKaleoAction.getScriptLanguage(),
			newKaleoAction.getScriptLanguage());
		Assert.assertEquals(existingKaleoAction.getScriptRequiredContexts(),
			newKaleoAction.getScriptRequiredContexts());
		Assert.assertEquals(existingKaleoAction.getPriority(),
			newKaleoAction.getPriority());
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
	public void testCountByKCN_KCPK() throws Exception {
		_persistence.countByKCN_KCPK(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByKCN_KCPK(StringPool.NULL, 0L);

		_persistence.countByKCN_KCPK((String)null, 0L);
	}

	@Test
	public void testCountByKCN_KCPK_ET() throws Exception {
		_persistence.countByKCN_KCPK_ET(StringPool.BLANK,
			RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByKCN_KCPK_ET(StringPool.NULL, 0L, StringPool.NULL);

		_persistence.countByKCN_KCPK_ET((String)null, 0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		KaleoAction newKaleoAction = addKaleoAction();

		KaleoAction existingKaleoAction = _persistence.findByPrimaryKey(newKaleoAction.getPrimaryKey());

		Assert.assertEquals(existingKaleoAction, newKaleoAction);
	}

	@Test(expected = NoSuchActionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<KaleoAction> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("KaleoAction",
			"kaleoActionId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "kaleoClassName", true, "kaleoClassPK", true,
			"kaleoDefinitionId", true, "kaleoNodeName", true, "name", true,
			"description", true, "executionType", true, "scriptLanguage", true,
			"scriptRequiredContexts", true, "priority", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		KaleoAction newKaleoAction = addKaleoAction();

		KaleoAction existingKaleoAction = _persistence.fetchByPrimaryKey(newKaleoAction.getPrimaryKey());

		Assert.assertEquals(existingKaleoAction, newKaleoAction);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoAction missingKaleoAction = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingKaleoAction);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		KaleoAction newKaleoAction1 = addKaleoAction();
		KaleoAction newKaleoAction2 = addKaleoAction();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoAction1.getPrimaryKey());
		primaryKeys.add(newKaleoAction2.getPrimaryKey());

		Map<Serializable, KaleoAction> kaleoActions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, kaleoActions.size());
		Assert.assertEquals(newKaleoAction1,
			kaleoActions.get(newKaleoAction1.getPrimaryKey()));
		Assert.assertEquals(newKaleoAction2,
			kaleoActions.get(newKaleoAction2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, KaleoAction> kaleoActions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kaleoActions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		KaleoAction newKaleoAction = addKaleoAction();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoAction.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, KaleoAction> kaleoActions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kaleoActions.size());
		Assert.assertEquals(newKaleoAction,
			kaleoActions.get(newKaleoAction.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, KaleoAction> kaleoActions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kaleoActions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		KaleoAction newKaleoAction = addKaleoAction();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoAction.getPrimaryKey());

		Map<Serializable, KaleoAction> kaleoActions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kaleoActions.size());
		Assert.assertEquals(newKaleoAction,
			kaleoActions.get(newKaleoAction.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = KaleoActionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<KaleoAction>() {
				@Override
				public void performAction(KaleoAction kaleoAction) {
					Assert.assertNotNull(kaleoAction);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		KaleoAction newKaleoAction = addKaleoAction();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoAction.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("kaleoActionId",
				newKaleoAction.getKaleoActionId()));

		List<KaleoAction> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		KaleoAction existingKaleoAction = result.get(0);

		Assert.assertEquals(existingKaleoAction, newKaleoAction);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoAction.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("kaleoActionId",
				RandomTestUtil.nextLong()));

		List<KaleoAction> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		KaleoAction newKaleoAction = addKaleoAction();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoAction.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"kaleoActionId"));

		Object newKaleoActionId = newKaleoAction.getKaleoActionId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("kaleoActionId",
				new Object[] { newKaleoActionId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingKaleoActionId = result.get(0);

		Assert.assertEquals(existingKaleoActionId, newKaleoActionId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoAction.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"kaleoActionId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("kaleoActionId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected KaleoAction addKaleoAction() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoAction kaleoAction = _persistence.create(pk);

		kaleoAction.setGroupId(RandomTestUtil.nextLong());

		kaleoAction.setCompanyId(RandomTestUtil.nextLong());

		kaleoAction.setUserId(RandomTestUtil.nextLong());

		kaleoAction.setUserName(RandomTestUtil.randomString());

		kaleoAction.setCreateDate(RandomTestUtil.nextDate());

		kaleoAction.setModifiedDate(RandomTestUtil.nextDate());

		kaleoAction.setKaleoClassName(RandomTestUtil.randomString());

		kaleoAction.setKaleoClassPK(RandomTestUtil.nextLong());

		kaleoAction.setKaleoDefinitionId(RandomTestUtil.nextLong());

		kaleoAction.setKaleoNodeName(RandomTestUtil.randomString());

		kaleoAction.setName(RandomTestUtil.randomString());

		kaleoAction.setDescription(RandomTestUtil.randomString());

		kaleoAction.setExecutionType(RandomTestUtil.randomString());

		kaleoAction.setScript(RandomTestUtil.randomString());

		kaleoAction.setScriptLanguage(RandomTestUtil.randomString());

		kaleoAction.setScriptRequiredContexts(RandomTestUtil.randomString());

		kaleoAction.setPriority(RandomTestUtil.nextInt());

		_kaleoActions.add(_persistence.update(kaleoAction));

		return kaleoAction;
	}

	private List<KaleoAction> _kaleoActions = new ArrayList<KaleoAction>();
	private KaleoActionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}