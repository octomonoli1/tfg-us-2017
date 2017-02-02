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
import com.liferay.portal.kernel.exception.NoSuchUserNotificationEventException;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.UserNotificationEventPersistence;
import com.liferay.portal.kernel.service.persistence.UserNotificationEventUtil;
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
import java.util.Set;

/**
 * @generated
 */
public class UserNotificationEventPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = UserNotificationEventUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<UserNotificationEvent> iterator = _userNotificationEvents.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserNotificationEvent userNotificationEvent = _persistence.create(pk);

		Assert.assertNotNull(userNotificationEvent);

		Assert.assertEquals(userNotificationEvent.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		UserNotificationEvent newUserNotificationEvent = addUserNotificationEvent();

		_persistence.remove(newUserNotificationEvent);

		UserNotificationEvent existingUserNotificationEvent = _persistence.fetchByPrimaryKey(newUserNotificationEvent.getPrimaryKey());

		Assert.assertNull(existingUserNotificationEvent);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addUserNotificationEvent();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserNotificationEvent newUserNotificationEvent = _persistence.create(pk);

		newUserNotificationEvent.setMvccVersion(RandomTestUtil.nextLong());

		newUserNotificationEvent.setUuid(RandomTestUtil.randomString());

		newUserNotificationEvent.setCompanyId(RandomTestUtil.nextLong());

		newUserNotificationEvent.setUserId(RandomTestUtil.nextLong());

		newUserNotificationEvent.setType(RandomTestUtil.randomString());

		newUserNotificationEvent.setTimestamp(RandomTestUtil.nextLong());

		newUserNotificationEvent.setDeliveryType(RandomTestUtil.nextInt());

		newUserNotificationEvent.setDeliverBy(RandomTestUtil.nextLong());

		newUserNotificationEvent.setDelivered(RandomTestUtil.randomBoolean());

		newUserNotificationEvent.setPayload(RandomTestUtil.randomString());

		newUserNotificationEvent.setActionRequired(RandomTestUtil.randomBoolean());

		newUserNotificationEvent.setArchived(RandomTestUtil.randomBoolean());

		_userNotificationEvents.add(_persistence.update(
				newUserNotificationEvent));

		UserNotificationEvent existingUserNotificationEvent = _persistence.findByPrimaryKey(newUserNotificationEvent.getPrimaryKey());

		Assert.assertEquals(existingUserNotificationEvent.getMvccVersion(),
			newUserNotificationEvent.getMvccVersion());
		Assert.assertEquals(existingUserNotificationEvent.getUuid(),
			newUserNotificationEvent.getUuid());
		Assert.assertEquals(existingUserNotificationEvent.getUserNotificationEventId(),
			newUserNotificationEvent.getUserNotificationEventId());
		Assert.assertEquals(existingUserNotificationEvent.getCompanyId(),
			newUserNotificationEvent.getCompanyId());
		Assert.assertEquals(existingUserNotificationEvent.getUserId(),
			newUserNotificationEvent.getUserId());
		Assert.assertEquals(existingUserNotificationEvent.getType(),
			newUserNotificationEvent.getType());
		Assert.assertEquals(existingUserNotificationEvent.getTimestamp(),
			newUserNotificationEvent.getTimestamp());
		Assert.assertEquals(existingUserNotificationEvent.getDeliveryType(),
			newUserNotificationEvent.getDeliveryType());
		Assert.assertEquals(existingUserNotificationEvent.getDeliverBy(),
			newUserNotificationEvent.getDeliverBy());
		Assert.assertEquals(existingUserNotificationEvent.getDelivered(),
			newUserNotificationEvent.getDelivered());
		Assert.assertEquals(existingUserNotificationEvent.getPayload(),
			newUserNotificationEvent.getPayload());
		Assert.assertEquals(existingUserNotificationEvent.getActionRequired(),
			newUserNotificationEvent.getActionRequired());
		Assert.assertEquals(existingUserNotificationEvent.getArchived(),
			newUserNotificationEvent.getArchived());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid(StringPool.BLANK);

		_persistence.countByUuid(StringPool.NULL);

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByUuid_C(StringPool.NULL, 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testCountByType() throws Exception {
		_persistence.countByType(StringPool.BLANK);

		_persistence.countByType(StringPool.NULL);

		_persistence.countByType((String)null);
	}

	@Test
	public void testCountByU_DT() throws Exception {
		_persistence.countByU_DT(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByU_DT(0L, 0);
	}

	@Test
	public void testCountByU_D() throws Exception {
		_persistence.countByU_D(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByU_D(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByU_A() throws Exception {
		_persistence.countByU_A(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByU_A(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByU_DT_D() throws Exception {
		_persistence.countByU_DT_D(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt(), RandomTestUtil.randomBoolean());

		_persistence.countByU_DT_D(0L, 0, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByU_DT_A() throws Exception {
		_persistence.countByU_DT_A(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt(), RandomTestUtil.randomBoolean());

		_persistence.countByU_DT_A(0L, 0, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByU_D_A() throws Exception {
		_persistence.countByU_D_A(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean(), RandomTestUtil.randomBoolean());

		_persistence.countByU_D_A(0L, RandomTestUtil.randomBoolean(),
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByU_A_A() throws Exception {
		_persistence.countByU_A_A(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean(), RandomTestUtil.randomBoolean());

		_persistence.countByU_A_A(0L, RandomTestUtil.randomBoolean(),
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByU_T_DT_D() throws Exception {
		_persistence.countByU_T_DT_D(RandomTestUtil.nextLong(),
			StringPool.BLANK, RandomTestUtil.nextInt(),
			RandomTestUtil.randomBoolean());

		_persistence.countByU_T_DT_D(0L, StringPool.NULL, 0,
			RandomTestUtil.randomBoolean());

		_persistence.countByU_T_DT_D(0L, (String)null, 0,
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByU_DT_D_A() throws Exception {
		_persistence.countByU_DT_D_A(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt(), RandomTestUtil.randomBoolean(),
			RandomTestUtil.randomBoolean());

		_persistence.countByU_DT_D_A(0L, 0, RandomTestUtil.randomBoolean(),
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByU_DT_A_A() throws Exception {
		_persistence.countByU_DT_A_A(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt(), RandomTestUtil.randomBoolean(),
			RandomTestUtil.randomBoolean());

		_persistence.countByU_DT_A_A(0L, 0, RandomTestUtil.randomBoolean(),
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		UserNotificationEvent newUserNotificationEvent = addUserNotificationEvent();

		UserNotificationEvent existingUserNotificationEvent = _persistence.findByPrimaryKey(newUserNotificationEvent.getPrimaryKey());

		Assert.assertEquals(existingUserNotificationEvent,
			newUserNotificationEvent);
	}

	@Test(expected = NoSuchUserNotificationEventException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<UserNotificationEvent> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("UserNotificationEvent",
			"mvccVersion", true, "uuid", true, "userNotificationEventId", true,
			"companyId", true, "userId", true, "type", true, "timestamp", true,
			"deliveryType", true, "deliverBy", true, "delivered", true,
			"actionRequired", true, "archived", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		UserNotificationEvent newUserNotificationEvent = addUserNotificationEvent();

		UserNotificationEvent existingUserNotificationEvent = _persistence.fetchByPrimaryKey(newUserNotificationEvent.getPrimaryKey());

		Assert.assertEquals(existingUserNotificationEvent,
			newUserNotificationEvent);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserNotificationEvent missingUserNotificationEvent = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingUserNotificationEvent);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		UserNotificationEvent newUserNotificationEvent1 = addUserNotificationEvent();
		UserNotificationEvent newUserNotificationEvent2 = addUserNotificationEvent();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserNotificationEvent1.getPrimaryKey());
		primaryKeys.add(newUserNotificationEvent2.getPrimaryKey());

		Map<Serializable, UserNotificationEvent> userNotificationEvents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, userNotificationEvents.size());
		Assert.assertEquals(newUserNotificationEvent1,
			userNotificationEvents.get(
				newUserNotificationEvent1.getPrimaryKey()));
		Assert.assertEquals(newUserNotificationEvent2,
			userNotificationEvents.get(
				newUserNotificationEvent2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, UserNotificationEvent> userNotificationEvents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(userNotificationEvents.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		UserNotificationEvent newUserNotificationEvent = addUserNotificationEvent();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserNotificationEvent.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, UserNotificationEvent> userNotificationEvents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, userNotificationEvents.size());
		Assert.assertEquals(newUserNotificationEvent,
			userNotificationEvents.get(newUserNotificationEvent.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, UserNotificationEvent> userNotificationEvents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(userNotificationEvents.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		UserNotificationEvent newUserNotificationEvent = addUserNotificationEvent();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserNotificationEvent.getPrimaryKey());

		Map<Serializable, UserNotificationEvent> userNotificationEvents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, userNotificationEvents.size());
		Assert.assertEquals(newUserNotificationEvent,
			userNotificationEvents.get(newUserNotificationEvent.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = UserNotificationEventLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<UserNotificationEvent>() {
				@Override
				public void performAction(
					UserNotificationEvent userNotificationEvent) {
					Assert.assertNotNull(userNotificationEvent);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		UserNotificationEvent newUserNotificationEvent = addUserNotificationEvent();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserNotificationEvent.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("userNotificationEventId",
				newUserNotificationEvent.getUserNotificationEventId()));

		List<UserNotificationEvent> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		UserNotificationEvent existingUserNotificationEvent = result.get(0);

		Assert.assertEquals(existingUserNotificationEvent,
			newUserNotificationEvent);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserNotificationEvent.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("userNotificationEventId",
				RandomTestUtil.nextLong()));

		List<UserNotificationEvent> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		UserNotificationEvent newUserNotificationEvent = addUserNotificationEvent();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserNotificationEvent.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"userNotificationEventId"));

		Object newUserNotificationEventId = newUserNotificationEvent.getUserNotificationEventId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("userNotificationEventId",
				new Object[] { newUserNotificationEventId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUserNotificationEventId = result.get(0);

		Assert.assertEquals(existingUserNotificationEventId,
			newUserNotificationEventId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserNotificationEvent.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"userNotificationEventId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("userNotificationEventId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected UserNotificationEvent addUserNotificationEvent()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserNotificationEvent userNotificationEvent = _persistence.create(pk);

		userNotificationEvent.setMvccVersion(RandomTestUtil.nextLong());

		userNotificationEvent.setUuid(RandomTestUtil.randomString());

		userNotificationEvent.setCompanyId(RandomTestUtil.nextLong());

		userNotificationEvent.setUserId(RandomTestUtil.nextLong());

		userNotificationEvent.setType(RandomTestUtil.randomString());

		userNotificationEvent.setTimestamp(RandomTestUtil.nextLong());

		userNotificationEvent.setDeliveryType(RandomTestUtil.nextInt());

		userNotificationEvent.setDeliverBy(RandomTestUtil.nextLong());

		userNotificationEvent.setDelivered(RandomTestUtil.randomBoolean());

		userNotificationEvent.setPayload(RandomTestUtil.randomString());

		userNotificationEvent.setActionRequired(RandomTestUtil.randomBoolean());

		userNotificationEvent.setArchived(RandomTestUtil.randomBoolean());

		_userNotificationEvents.add(_persistence.update(userNotificationEvent));

		return userNotificationEvent;
	}

	private List<UserNotificationEvent> _userNotificationEvents = new ArrayList<UserNotificationEvent>();
	private UserNotificationEventPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}