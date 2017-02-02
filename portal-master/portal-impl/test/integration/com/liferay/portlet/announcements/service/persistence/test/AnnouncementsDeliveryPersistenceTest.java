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

package com.liferay.portlet.announcements.service.persistence.test;

import com.liferay.announcements.kernel.exception.NoSuchDeliveryException;
import com.liferay.announcements.kernel.model.AnnouncementsDelivery;
import com.liferay.announcements.kernel.service.AnnouncementsDeliveryLocalServiceUtil;
import com.liferay.announcements.kernel.service.persistence.AnnouncementsDeliveryPersistence;
import com.liferay.announcements.kernel.service.persistence.AnnouncementsDeliveryUtil;

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
public class AnnouncementsDeliveryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = AnnouncementsDeliveryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AnnouncementsDelivery> iterator = _announcementsDeliveries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AnnouncementsDelivery announcementsDelivery = _persistence.create(pk);

		Assert.assertNotNull(announcementsDelivery);

		Assert.assertEquals(announcementsDelivery.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AnnouncementsDelivery newAnnouncementsDelivery = addAnnouncementsDelivery();

		_persistence.remove(newAnnouncementsDelivery);

		AnnouncementsDelivery existingAnnouncementsDelivery = _persistence.fetchByPrimaryKey(newAnnouncementsDelivery.getPrimaryKey());

		Assert.assertNull(existingAnnouncementsDelivery);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAnnouncementsDelivery();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AnnouncementsDelivery newAnnouncementsDelivery = _persistence.create(pk);

		newAnnouncementsDelivery.setCompanyId(RandomTestUtil.nextLong());

		newAnnouncementsDelivery.setUserId(RandomTestUtil.nextLong());

		newAnnouncementsDelivery.setType(RandomTestUtil.randomString());

		newAnnouncementsDelivery.setEmail(RandomTestUtil.randomBoolean());

		newAnnouncementsDelivery.setSms(RandomTestUtil.randomBoolean());

		newAnnouncementsDelivery.setWebsite(RandomTestUtil.randomBoolean());

		_announcementsDeliveries.add(_persistence.update(
				newAnnouncementsDelivery));

		AnnouncementsDelivery existingAnnouncementsDelivery = _persistence.findByPrimaryKey(newAnnouncementsDelivery.getPrimaryKey());

		Assert.assertEquals(existingAnnouncementsDelivery.getDeliveryId(),
			newAnnouncementsDelivery.getDeliveryId());
		Assert.assertEquals(existingAnnouncementsDelivery.getCompanyId(),
			newAnnouncementsDelivery.getCompanyId());
		Assert.assertEquals(existingAnnouncementsDelivery.getUserId(),
			newAnnouncementsDelivery.getUserId());
		Assert.assertEquals(existingAnnouncementsDelivery.getType(),
			newAnnouncementsDelivery.getType());
		Assert.assertEquals(existingAnnouncementsDelivery.getEmail(),
			newAnnouncementsDelivery.getEmail());
		Assert.assertEquals(existingAnnouncementsDelivery.getSms(),
			newAnnouncementsDelivery.getSms());
		Assert.assertEquals(existingAnnouncementsDelivery.getWebsite(),
			newAnnouncementsDelivery.getWebsite());
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testCountByU_T() throws Exception {
		_persistence.countByU_T(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByU_T(0L, StringPool.NULL);

		_persistence.countByU_T(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AnnouncementsDelivery newAnnouncementsDelivery = addAnnouncementsDelivery();

		AnnouncementsDelivery existingAnnouncementsDelivery = _persistence.findByPrimaryKey(newAnnouncementsDelivery.getPrimaryKey());

		Assert.assertEquals(existingAnnouncementsDelivery,
			newAnnouncementsDelivery);
	}

	@Test(expected = NoSuchDeliveryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AnnouncementsDelivery> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("AnnouncementsDelivery",
			"deliveryId", true, "companyId", true, "userId", true, "type",
			true, "email", true, "sms", true, "website", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AnnouncementsDelivery newAnnouncementsDelivery = addAnnouncementsDelivery();

		AnnouncementsDelivery existingAnnouncementsDelivery = _persistence.fetchByPrimaryKey(newAnnouncementsDelivery.getPrimaryKey());

		Assert.assertEquals(existingAnnouncementsDelivery,
			newAnnouncementsDelivery);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AnnouncementsDelivery missingAnnouncementsDelivery = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAnnouncementsDelivery);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AnnouncementsDelivery newAnnouncementsDelivery1 = addAnnouncementsDelivery();
		AnnouncementsDelivery newAnnouncementsDelivery2 = addAnnouncementsDelivery();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAnnouncementsDelivery1.getPrimaryKey());
		primaryKeys.add(newAnnouncementsDelivery2.getPrimaryKey());

		Map<Serializable, AnnouncementsDelivery> announcementsDeliveries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, announcementsDeliveries.size());
		Assert.assertEquals(newAnnouncementsDelivery1,
			announcementsDeliveries.get(
				newAnnouncementsDelivery1.getPrimaryKey()));
		Assert.assertEquals(newAnnouncementsDelivery2,
			announcementsDeliveries.get(
				newAnnouncementsDelivery2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AnnouncementsDelivery> announcementsDeliveries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(announcementsDeliveries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AnnouncementsDelivery newAnnouncementsDelivery = addAnnouncementsDelivery();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAnnouncementsDelivery.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AnnouncementsDelivery> announcementsDeliveries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, announcementsDeliveries.size());
		Assert.assertEquals(newAnnouncementsDelivery,
			announcementsDeliveries.get(
				newAnnouncementsDelivery.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AnnouncementsDelivery> announcementsDeliveries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(announcementsDeliveries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AnnouncementsDelivery newAnnouncementsDelivery = addAnnouncementsDelivery();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAnnouncementsDelivery.getPrimaryKey());

		Map<Serializable, AnnouncementsDelivery> announcementsDeliveries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, announcementsDeliveries.size());
		Assert.assertEquals(newAnnouncementsDelivery,
			announcementsDeliveries.get(
				newAnnouncementsDelivery.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AnnouncementsDeliveryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AnnouncementsDelivery>() {
				@Override
				public void performAction(
					AnnouncementsDelivery announcementsDelivery) {
					Assert.assertNotNull(announcementsDelivery);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AnnouncementsDelivery newAnnouncementsDelivery = addAnnouncementsDelivery();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AnnouncementsDelivery.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("deliveryId",
				newAnnouncementsDelivery.getDeliveryId()));

		List<AnnouncementsDelivery> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AnnouncementsDelivery existingAnnouncementsDelivery = result.get(0);

		Assert.assertEquals(existingAnnouncementsDelivery,
			newAnnouncementsDelivery);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AnnouncementsDelivery.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("deliveryId",
				RandomTestUtil.nextLong()));

		List<AnnouncementsDelivery> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AnnouncementsDelivery newAnnouncementsDelivery = addAnnouncementsDelivery();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AnnouncementsDelivery.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("deliveryId"));

		Object newDeliveryId = newAnnouncementsDelivery.getDeliveryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("deliveryId",
				new Object[] { newDeliveryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDeliveryId = result.get(0);

		Assert.assertEquals(existingDeliveryId, newDeliveryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AnnouncementsDelivery.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("deliveryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("deliveryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		AnnouncementsDelivery newAnnouncementsDelivery = addAnnouncementsDelivery();

		_persistence.clearCache();

		AnnouncementsDelivery existingAnnouncementsDelivery = _persistence.findByPrimaryKey(newAnnouncementsDelivery.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingAnnouncementsDelivery.getUserId()),
			ReflectionTestUtil.<Long>invoke(existingAnnouncementsDelivery,
				"getOriginalUserId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(
				existingAnnouncementsDelivery.getType(),
				ReflectionTestUtil.invoke(existingAnnouncementsDelivery,
					"getOriginalType", new Class<?>[0])));
	}

	protected AnnouncementsDelivery addAnnouncementsDelivery()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		AnnouncementsDelivery announcementsDelivery = _persistence.create(pk);

		announcementsDelivery.setCompanyId(RandomTestUtil.nextLong());

		announcementsDelivery.setUserId(RandomTestUtil.nextLong());

		announcementsDelivery.setType(RandomTestUtil.randomString());

		announcementsDelivery.setEmail(RandomTestUtil.randomBoolean());

		announcementsDelivery.setSms(RandomTestUtil.randomBoolean());

		announcementsDelivery.setWebsite(RandomTestUtil.randomBoolean());

		_announcementsDeliveries.add(_persistence.update(announcementsDelivery));

		return announcementsDelivery;
	}

	private List<AnnouncementsDelivery> _announcementsDeliveries = new ArrayList<AnnouncementsDelivery>();
	private AnnouncementsDeliveryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}