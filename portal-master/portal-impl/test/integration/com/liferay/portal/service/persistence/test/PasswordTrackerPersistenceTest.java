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
import com.liferay.portal.kernel.exception.NoSuchPasswordTrackerException;
import com.liferay.portal.kernel.model.PasswordTracker;
import com.liferay.portal.kernel.service.PasswordTrackerLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.PasswordTrackerPersistence;
import com.liferay.portal.kernel.service.persistence.PasswordTrackerUtil;
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
public class PasswordTrackerPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = PasswordTrackerUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<PasswordTracker> iterator = _passwordTrackers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		PasswordTracker passwordTracker = _persistence.create(pk);

		Assert.assertNotNull(passwordTracker);

		Assert.assertEquals(passwordTracker.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		PasswordTracker newPasswordTracker = addPasswordTracker();

		_persistence.remove(newPasswordTracker);

		PasswordTracker existingPasswordTracker = _persistence.fetchByPrimaryKey(newPasswordTracker.getPrimaryKey());

		Assert.assertNull(existingPasswordTracker);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPasswordTracker();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		PasswordTracker newPasswordTracker = _persistence.create(pk);

		newPasswordTracker.setMvccVersion(RandomTestUtil.nextLong());

		newPasswordTracker.setCompanyId(RandomTestUtil.nextLong());

		newPasswordTracker.setUserId(RandomTestUtil.nextLong());

		newPasswordTracker.setCreateDate(RandomTestUtil.nextDate());

		newPasswordTracker.setPassword(RandomTestUtil.randomString());

		_passwordTrackers.add(_persistence.update(newPasswordTracker));

		PasswordTracker existingPasswordTracker = _persistence.findByPrimaryKey(newPasswordTracker.getPrimaryKey());

		Assert.assertEquals(existingPasswordTracker.getMvccVersion(),
			newPasswordTracker.getMvccVersion());
		Assert.assertEquals(existingPasswordTracker.getPasswordTrackerId(),
			newPasswordTracker.getPasswordTrackerId());
		Assert.assertEquals(existingPasswordTracker.getCompanyId(),
			newPasswordTracker.getCompanyId());
		Assert.assertEquals(existingPasswordTracker.getUserId(),
			newPasswordTracker.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPasswordTracker.getCreateDate()),
			Time.getShortTimestamp(newPasswordTracker.getCreateDate()));
		Assert.assertEquals(existingPasswordTracker.getPassword(),
			newPasswordTracker.getPassword());
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		PasswordTracker newPasswordTracker = addPasswordTracker();

		PasswordTracker existingPasswordTracker = _persistence.findByPrimaryKey(newPasswordTracker.getPrimaryKey());

		Assert.assertEquals(existingPasswordTracker, newPasswordTracker);
	}

	@Test(expected = NoSuchPasswordTrackerException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<PasswordTracker> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PasswordTracker",
			"mvccVersion", true, "passwordTrackerId", true, "companyId", true,
			"userId", true, "createDate", true, "password", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		PasswordTracker newPasswordTracker = addPasswordTracker();

		PasswordTracker existingPasswordTracker = _persistence.fetchByPrimaryKey(newPasswordTracker.getPrimaryKey());

		Assert.assertEquals(existingPasswordTracker, newPasswordTracker);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		PasswordTracker missingPasswordTracker = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPasswordTracker);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		PasswordTracker newPasswordTracker1 = addPasswordTracker();
		PasswordTracker newPasswordTracker2 = addPasswordTracker();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPasswordTracker1.getPrimaryKey());
		primaryKeys.add(newPasswordTracker2.getPrimaryKey());

		Map<Serializable, PasswordTracker> passwordTrackers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, passwordTrackers.size());
		Assert.assertEquals(newPasswordTracker1,
			passwordTrackers.get(newPasswordTracker1.getPrimaryKey()));
		Assert.assertEquals(newPasswordTracker2,
			passwordTrackers.get(newPasswordTracker2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, PasswordTracker> passwordTrackers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(passwordTrackers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		PasswordTracker newPasswordTracker = addPasswordTracker();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPasswordTracker.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, PasswordTracker> passwordTrackers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, passwordTrackers.size());
		Assert.assertEquals(newPasswordTracker,
			passwordTrackers.get(newPasswordTracker.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, PasswordTracker> passwordTrackers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(passwordTrackers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		PasswordTracker newPasswordTracker = addPasswordTracker();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPasswordTracker.getPrimaryKey());

		Map<Serializable, PasswordTracker> passwordTrackers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, passwordTrackers.size());
		Assert.assertEquals(newPasswordTracker,
			passwordTrackers.get(newPasswordTracker.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = PasswordTrackerLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<PasswordTracker>() {
				@Override
				public void performAction(PasswordTracker passwordTracker) {
					Assert.assertNotNull(passwordTracker);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		PasswordTracker newPasswordTracker = addPasswordTracker();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PasswordTracker.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("passwordTrackerId",
				newPasswordTracker.getPasswordTrackerId()));

		List<PasswordTracker> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		PasswordTracker existingPasswordTracker = result.get(0);

		Assert.assertEquals(existingPasswordTracker, newPasswordTracker);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PasswordTracker.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("passwordTrackerId",
				RandomTestUtil.nextLong()));

		List<PasswordTracker> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		PasswordTracker newPasswordTracker = addPasswordTracker();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PasswordTracker.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"passwordTrackerId"));

		Object newPasswordTrackerId = newPasswordTracker.getPasswordTrackerId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("passwordTrackerId",
				new Object[] { newPasswordTrackerId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPasswordTrackerId = result.get(0);

		Assert.assertEquals(existingPasswordTrackerId, newPasswordTrackerId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PasswordTracker.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"passwordTrackerId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("passwordTrackerId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected PasswordTracker addPasswordTracker() throws Exception {
		long pk = RandomTestUtil.nextLong();

		PasswordTracker passwordTracker = _persistence.create(pk);

		passwordTracker.setMvccVersion(RandomTestUtil.nextLong());

		passwordTracker.setCompanyId(RandomTestUtil.nextLong());

		passwordTracker.setUserId(RandomTestUtil.nextLong());

		passwordTracker.setCreateDate(RandomTestUtil.nextDate());

		passwordTracker.setPassword(RandomTestUtil.randomString());

		_passwordTrackers.add(_persistence.update(passwordTracker));

		return passwordTracker;
	}

	private List<PasswordTracker> _passwordTrackers = new ArrayList<PasswordTracker>();
	private PasswordTrackerPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}