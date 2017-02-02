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
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.workflow.kaleo.exception.NoSuchNotificationRecipientException;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.service.KaleoNotificationRecipientLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoNotificationRecipientPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoNotificationRecipientUtil;

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
public class KaleoNotificationRecipientPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = KaleoNotificationRecipientUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<KaleoNotificationRecipient> iterator = _kaleoNotificationRecipients.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoNotificationRecipient kaleoNotificationRecipient = _persistence.create(pk);

		Assert.assertNotNull(kaleoNotificationRecipient);

		Assert.assertEquals(kaleoNotificationRecipient.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		KaleoNotificationRecipient newKaleoNotificationRecipient = addKaleoNotificationRecipient();

		_persistence.remove(newKaleoNotificationRecipient);

		KaleoNotificationRecipient existingKaleoNotificationRecipient = _persistence.fetchByPrimaryKey(newKaleoNotificationRecipient.getPrimaryKey());

		Assert.assertNull(existingKaleoNotificationRecipient);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addKaleoNotificationRecipient();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoNotificationRecipient newKaleoNotificationRecipient = _persistence.create(pk);

		newKaleoNotificationRecipient.setGroupId(RandomTestUtil.nextLong());

		newKaleoNotificationRecipient.setCompanyId(RandomTestUtil.nextLong());

		newKaleoNotificationRecipient.setUserId(RandomTestUtil.nextLong());

		newKaleoNotificationRecipient.setUserName(RandomTestUtil.randomString());

		newKaleoNotificationRecipient.setCreateDate(RandomTestUtil.nextDate());

		newKaleoNotificationRecipient.setModifiedDate(RandomTestUtil.nextDate());

		newKaleoNotificationRecipient.setKaleoDefinitionId(RandomTestUtil.nextLong());

		newKaleoNotificationRecipient.setKaleoNotificationId(RandomTestUtil.nextLong());

		newKaleoNotificationRecipient.setRecipientClassName(RandomTestUtil.randomString());

		newKaleoNotificationRecipient.setRecipientClassPK(RandomTestUtil.nextLong());

		newKaleoNotificationRecipient.setRecipientRoleType(RandomTestUtil.nextInt());

		newKaleoNotificationRecipient.setRecipientScript(RandomTestUtil.randomString());

		newKaleoNotificationRecipient.setRecipientScriptLanguage(RandomTestUtil.randomString());

		newKaleoNotificationRecipient.setRecipientScriptContexts(RandomTestUtil.randomString());

		newKaleoNotificationRecipient.setAddress(RandomTestUtil.randomString());

		newKaleoNotificationRecipient.setNotificationReceptionType(RandomTestUtil.randomString(
				3));

		_kaleoNotificationRecipients.add(_persistence.update(
				newKaleoNotificationRecipient));

		KaleoNotificationRecipient existingKaleoNotificationRecipient = _persistence.findByPrimaryKey(newKaleoNotificationRecipient.getPrimaryKey());

		Assert.assertEquals(existingKaleoNotificationRecipient.getKaleoNotificationRecipientId(),
			newKaleoNotificationRecipient.getKaleoNotificationRecipientId());
		Assert.assertEquals(existingKaleoNotificationRecipient.getGroupId(),
			newKaleoNotificationRecipient.getGroupId());
		Assert.assertEquals(existingKaleoNotificationRecipient.getCompanyId(),
			newKaleoNotificationRecipient.getCompanyId());
		Assert.assertEquals(existingKaleoNotificationRecipient.getUserId(),
			newKaleoNotificationRecipient.getUserId());
		Assert.assertEquals(existingKaleoNotificationRecipient.getUserName(),
			newKaleoNotificationRecipient.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingKaleoNotificationRecipient.getCreateDate()),
			Time.getShortTimestamp(
				newKaleoNotificationRecipient.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingKaleoNotificationRecipient.getModifiedDate()),
			Time.getShortTimestamp(
				newKaleoNotificationRecipient.getModifiedDate()));
		Assert.assertEquals(existingKaleoNotificationRecipient.getKaleoDefinitionId(),
			newKaleoNotificationRecipient.getKaleoDefinitionId());
		Assert.assertEquals(existingKaleoNotificationRecipient.getKaleoNotificationId(),
			newKaleoNotificationRecipient.getKaleoNotificationId());
		Assert.assertEquals(existingKaleoNotificationRecipient.getRecipientClassName(),
			newKaleoNotificationRecipient.getRecipientClassName());
		Assert.assertEquals(existingKaleoNotificationRecipient.getRecipientClassPK(),
			newKaleoNotificationRecipient.getRecipientClassPK());
		Assert.assertEquals(existingKaleoNotificationRecipient.getRecipientRoleType(),
			newKaleoNotificationRecipient.getRecipientRoleType());
		Assert.assertEquals(existingKaleoNotificationRecipient.getRecipientScript(),
			newKaleoNotificationRecipient.getRecipientScript());
		Assert.assertEquals(existingKaleoNotificationRecipient.getRecipientScriptLanguage(),
			newKaleoNotificationRecipient.getRecipientScriptLanguage());
		Assert.assertEquals(existingKaleoNotificationRecipient.getRecipientScriptContexts(),
			newKaleoNotificationRecipient.getRecipientScriptContexts());
		Assert.assertEquals(existingKaleoNotificationRecipient.getAddress(),
			newKaleoNotificationRecipient.getAddress());
		Assert.assertEquals(existingKaleoNotificationRecipient.getNotificationReceptionType(),
			newKaleoNotificationRecipient.getNotificationReceptionType());
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
	public void testCountByKaleoNotificationId() throws Exception {
		_persistence.countByKaleoNotificationId(RandomTestUtil.nextLong());

		_persistence.countByKaleoNotificationId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		KaleoNotificationRecipient newKaleoNotificationRecipient = addKaleoNotificationRecipient();

		KaleoNotificationRecipient existingKaleoNotificationRecipient = _persistence.findByPrimaryKey(newKaleoNotificationRecipient.getPrimaryKey());

		Assert.assertEquals(existingKaleoNotificationRecipient,
			newKaleoNotificationRecipient);
	}

	@Test(expected = NoSuchNotificationRecipientException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<KaleoNotificationRecipient> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("KaleoNotificationRecipient",
			"kaleoNotificationRecipientId", true, "groupId", true, "companyId",
			true, "userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "kaleoDefinitionId", true,
			"kaleoNotificationId", true, "recipientClassName", true,
			"recipientClassPK", true, "recipientRoleType", true,
			"recipientScriptLanguage", true, "recipientScriptContexts", true,
			"address", true, "notificationReceptionType", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		KaleoNotificationRecipient newKaleoNotificationRecipient = addKaleoNotificationRecipient();

		KaleoNotificationRecipient existingKaleoNotificationRecipient = _persistence.fetchByPrimaryKey(newKaleoNotificationRecipient.getPrimaryKey());

		Assert.assertEquals(existingKaleoNotificationRecipient,
			newKaleoNotificationRecipient);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoNotificationRecipient missingKaleoNotificationRecipient = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingKaleoNotificationRecipient);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		KaleoNotificationRecipient newKaleoNotificationRecipient1 = addKaleoNotificationRecipient();
		KaleoNotificationRecipient newKaleoNotificationRecipient2 = addKaleoNotificationRecipient();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoNotificationRecipient1.getPrimaryKey());
		primaryKeys.add(newKaleoNotificationRecipient2.getPrimaryKey());

		Map<Serializable, KaleoNotificationRecipient> kaleoNotificationRecipients =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, kaleoNotificationRecipients.size());
		Assert.assertEquals(newKaleoNotificationRecipient1,
			kaleoNotificationRecipients.get(
				newKaleoNotificationRecipient1.getPrimaryKey()));
		Assert.assertEquals(newKaleoNotificationRecipient2,
			kaleoNotificationRecipients.get(
				newKaleoNotificationRecipient2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, KaleoNotificationRecipient> kaleoNotificationRecipients =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kaleoNotificationRecipients.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		KaleoNotificationRecipient newKaleoNotificationRecipient = addKaleoNotificationRecipient();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoNotificationRecipient.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, KaleoNotificationRecipient> kaleoNotificationRecipients =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kaleoNotificationRecipients.size());
		Assert.assertEquals(newKaleoNotificationRecipient,
			kaleoNotificationRecipients.get(
				newKaleoNotificationRecipient.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, KaleoNotificationRecipient> kaleoNotificationRecipients =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kaleoNotificationRecipients.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		KaleoNotificationRecipient newKaleoNotificationRecipient = addKaleoNotificationRecipient();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKaleoNotificationRecipient.getPrimaryKey());

		Map<Serializable, KaleoNotificationRecipient> kaleoNotificationRecipients =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kaleoNotificationRecipients.size());
		Assert.assertEquals(newKaleoNotificationRecipient,
			kaleoNotificationRecipients.get(
				newKaleoNotificationRecipient.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = KaleoNotificationRecipientLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<KaleoNotificationRecipient>() {
				@Override
				public void performAction(
					KaleoNotificationRecipient kaleoNotificationRecipient) {
					Assert.assertNotNull(kaleoNotificationRecipient);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		KaleoNotificationRecipient newKaleoNotificationRecipient = addKaleoNotificationRecipient();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoNotificationRecipient.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"kaleoNotificationRecipientId",
				newKaleoNotificationRecipient.getKaleoNotificationRecipientId()));

		List<KaleoNotificationRecipient> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		KaleoNotificationRecipient existingKaleoNotificationRecipient = result.get(0);

		Assert.assertEquals(existingKaleoNotificationRecipient,
			newKaleoNotificationRecipient);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoNotificationRecipient.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"kaleoNotificationRecipientId", RandomTestUtil.nextLong()));

		List<KaleoNotificationRecipient> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		KaleoNotificationRecipient newKaleoNotificationRecipient = addKaleoNotificationRecipient();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoNotificationRecipient.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"kaleoNotificationRecipientId"));

		Object newKaleoNotificationRecipientId = newKaleoNotificationRecipient.getKaleoNotificationRecipientId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"kaleoNotificationRecipientId",
				new Object[] { newKaleoNotificationRecipientId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingKaleoNotificationRecipientId = result.get(0);

		Assert.assertEquals(existingKaleoNotificationRecipientId,
			newKaleoNotificationRecipientId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KaleoNotificationRecipient.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"kaleoNotificationRecipientId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"kaleoNotificationRecipientId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected KaleoNotificationRecipient addKaleoNotificationRecipient()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		KaleoNotificationRecipient kaleoNotificationRecipient = _persistence.create(pk);

		kaleoNotificationRecipient.setGroupId(RandomTestUtil.nextLong());

		kaleoNotificationRecipient.setCompanyId(RandomTestUtil.nextLong());

		kaleoNotificationRecipient.setUserId(RandomTestUtil.nextLong());

		kaleoNotificationRecipient.setUserName(RandomTestUtil.randomString());

		kaleoNotificationRecipient.setCreateDate(RandomTestUtil.nextDate());

		kaleoNotificationRecipient.setModifiedDate(RandomTestUtil.nextDate());

		kaleoNotificationRecipient.setKaleoDefinitionId(RandomTestUtil.nextLong());

		kaleoNotificationRecipient.setKaleoNotificationId(RandomTestUtil.nextLong());

		kaleoNotificationRecipient.setRecipientClassName(RandomTestUtil.randomString());

		kaleoNotificationRecipient.setRecipientClassPK(RandomTestUtil.nextLong());

		kaleoNotificationRecipient.setRecipientRoleType(RandomTestUtil.nextInt());

		kaleoNotificationRecipient.setRecipientScript(RandomTestUtil.randomString());

		kaleoNotificationRecipient.setRecipientScriptLanguage(RandomTestUtil.randomString());

		kaleoNotificationRecipient.setRecipientScriptContexts(RandomTestUtil.randomString());

		kaleoNotificationRecipient.setAddress(RandomTestUtil.randomString());

		kaleoNotificationRecipient.setNotificationReceptionType(RandomTestUtil.randomString(
				3));

		_kaleoNotificationRecipients.add(_persistence.update(
				kaleoNotificationRecipient));

		return kaleoNotificationRecipient;
	}

	private List<KaleoNotificationRecipient> _kaleoNotificationRecipients = new ArrayList<KaleoNotificationRecipient>();
	private KaleoNotificationRecipientPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}