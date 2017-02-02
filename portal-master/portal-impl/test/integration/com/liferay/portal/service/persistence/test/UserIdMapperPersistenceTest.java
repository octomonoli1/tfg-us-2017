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
import com.liferay.portal.kernel.exception.NoSuchUserIdMapperException;
import com.liferay.portal.kernel.model.UserIdMapper;
import com.liferay.portal.kernel.service.UserIdMapperLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.UserIdMapperPersistence;
import com.liferay.portal.kernel.service.persistence.UserIdMapperUtil;
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
public class UserIdMapperPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = UserIdMapperUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<UserIdMapper> iterator = _userIdMappers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserIdMapper userIdMapper = _persistence.create(pk);

		Assert.assertNotNull(userIdMapper);

		Assert.assertEquals(userIdMapper.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		UserIdMapper newUserIdMapper = addUserIdMapper();

		_persistence.remove(newUserIdMapper);

		UserIdMapper existingUserIdMapper = _persistence.fetchByPrimaryKey(newUserIdMapper.getPrimaryKey());

		Assert.assertNull(existingUserIdMapper);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addUserIdMapper();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserIdMapper newUserIdMapper = _persistence.create(pk);

		newUserIdMapper.setMvccVersion(RandomTestUtil.nextLong());

		newUserIdMapper.setCompanyId(RandomTestUtil.nextLong());

		newUserIdMapper.setUserId(RandomTestUtil.nextLong());

		newUserIdMapper.setType(RandomTestUtil.randomString());

		newUserIdMapper.setDescription(RandomTestUtil.randomString());

		newUserIdMapper.setExternalUserId(RandomTestUtil.randomString());

		_userIdMappers.add(_persistence.update(newUserIdMapper));

		UserIdMapper existingUserIdMapper = _persistence.findByPrimaryKey(newUserIdMapper.getPrimaryKey());

		Assert.assertEquals(existingUserIdMapper.getMvccVersion(),
			newUserIdMapper.getMvccVersion());
		Assert.assertEquals(existingUserIdMapper.getUserIdMapperId(),
			newUserIdMapper.getUserIdMapperId());
		Assert.assertEquals(existingUserIdMapper.getCompanyId(),
			newUserIdMapper.getCompanyId());
		Assert.assertEquals(existingUserIdMapper.getUserId(),
			newUserIdMapper.getUserId());
		Assert.assertEquals(existingUserIdMapper.getType(),
			newUserIdMapper.getType());
		Assert.assertEquals(existingUserIdMapper.getDescription(),
			newUserIdMapper.getDescription());
		Assert.assertEquals(existingUserIdMapper.getExternalUserId(),
			newUserIdMapper.getExternalUserId());
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
	public void testCountByT_E() throws Exception {
		_persistence.countByT_E(StringPool.BLANK, StringPool.BLANK);

		_persistence.countByT_E(StringPool.NULL, StringPool.NULL);

		_persistence.countByT_E((String)null, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		UserIdMapper newUserIdMapper = addUserIdMapper();

		UserIdMapper existingUserIdMapper = _persistence.findByPrimaryKey(newUserIdMapper.getPrimaryKey());

		Assert.assertEquals(existingUserIdMapper, newUserIdMapper);
	}

	@Test(expected = NoSuchUserIdMapperException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<UserIdMapper> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("UserIdMapper",
			"mvccVersion", true, "userIdMapperId", true, "companyId", true,
			"userId", true, "type", true, "description", true,
			"externalUserId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		UserIdMapper newUserIdMapper = addUserIdMapper();

		UserIdMapper existingUserIdMapper = _persistence.fetchByPrimaryKey(newUserIdMapper.getPrimaryKey());

		Assert.assertEquals(existingUserIdMapper, newUserIdMapper);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserIdMapper missingUserIdMapper = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingUserIdMapper);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		UserIdMapper newUserIdMapper1 = addUserIdMapper();
		UserIdMapper newUserIdMapper2 = addUserIdMapper();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserIdMapper1.getPrimaryKey());
		primaryKeys.add(newUserIdMapper2.getPrimaryKey());

		Map<Serializable, UserIdMapper> userIdMappers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, userIdMappers.size());
		Assert.assertEquals(newUserIdMapper1,
			userIdMappers.get(newUserIdMapper1.getPrimaryKey()));
		Assert.assertEquals(newUserIdMapper2,
			userIdMappers.get(newUserIdMapper2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, UserIdMapper> userIdMappers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(userIdMappers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		UserIdMapper newUserIdMapper = addUserIdMapper();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserIdMapper.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, UserIdMapper> userIdMappers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, userIdMappers.size());
		Assert.assertEquals(newUserIdMapper,
			userIdMappers.get(newUserIdMapper.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, UserIdMapper> userIdMappers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(userIdMappers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		UserIdMapper newUserIdMapper = addUserIdMapper();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserIdMapper.getPrimaryKey());

		Map<Serializable, UserIdMapper> userIdMappers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, userIdMappers.size());
		Assert.assertEquals(newUserIdMapper,
			userIdMappers.get(newUserIdMapper.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = UserIdMapperLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<UserIdMapper>() {
				@Override
				public void performAction(UserIdMapper userIdMapper) {
					Assert.assertNotNull(userIdMapper);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		UserIdMapper newUserIdMapper = addUserIdMapper();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserIdMapper.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("userIdMapperId",
				newUserIdMapper.getUserIdMapperId()));

		List<UserIdMapper> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		UserIdMapper existingUserIdMapper = result.get(0);

		Assert.assertEquals(existingUserIdMapper, newUserIdMapper);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserIdMapper.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("userIdMapperId",
				RandomTestUtil.nextLong()));

		List<UserIdMapper> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		UserIdMapper newUserIdMapper = addUserIdMapper();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserIdMapper.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"userIdMapperId"));

		Object newUserIdMapperId = newUserIdMapper.getUserIdMapperId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("userIdMapperId",
				new Object[] { newUserIdMapperId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUserIdMapperId = result.get(0);

		Assert.assertEquals(existingUserIdMapperId, newUserIdMapperId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserIdMapper.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"userIdMapperId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("userIdMapperId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		UserIdMapper newUserIdMapper = addUserIdMapper();

		_persistence.clearCache();

		UserIdMapper existingUserIdMapper = _persistence.findByPrimaryKey(newUserIdMapper.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingUserIdMapper.getUserId()),
			ReflectionTestUtil.<Long>invoke(existingUserIdMapper,
				"getOriginalUserId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingUserIdMapper.getType(),
				ReflectionTestUtil.invoke(existingUserIdMapper,
					"getOriginalType", new Class<?>[0])));

		Assert.assertTrue(Objects.equals(existingUserIdMapper.getType(),
				ReflectionTestUtil.invoke(existingUserIdMapper,
					"getOriginalType", new Class<?>[0])));
		Assert.assertTrue(Objects.equals(
				existingUserIdMapper.getExternalUserId(),
				ReflectionTestUtil.invoke(existingUserIdMapper,
					"getOriginalExternalUserId", new Class<?>[0])));
	}

	protected UserIdMapper addUserIdMapper() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserIdMapper userIdMapper = _persistence.create(pk);

		userIdMapper.setMvccVersion(RandomTestUtil.nextLong());

		userIdMapper.setCompanyId(RandomTestUtil.nextLong());

		userIdMapper.setUserId(RandomTestUtil.nextLong());

		userIdMapper.setType(RandomTestUtil.randomString());

		userIdMapper.setDescription(RandomTestUtil.randomString());

		userIdMapper.setExternalUserId(RandomTestUtil.randomString());

		_userIdMappers.add(_persistence.update(userIdMapper));

		return userIdMapper;
	}

	private List<UserIdMapper> _userIdMappers = new ArrayList<UserIdMapper>();
	private UserIdMapperPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}