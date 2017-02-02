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

package com.liferay.portlet.social.service.persistence.test;

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

import com.liferay.social.kernel.exception.NoSuchActivitySettingException;
import com.liferay.social.kernel.model.SocialActivitySetting;
import com.liferay.social.kernel.service.SocialActivitySettingLocalServiceUtil;
import com.liferay.social.kernel.service.persistence.SocialActivitySettingPersistence;
import com.liferay.social.kernel.service.persistence.SocialActivitySettingUtil;

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
public class SocialActivitySettingPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = SocialActivitySettingUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<SocialActivitySetting> iterator = _socialActivitySettings.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SocialActivitySetting socialActivitySetting = _persistence.create(pk);

		Assert.assertNotNull(socialActivitySetting);

		Assert.assertEquals(socialActivitySetting.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		SocialActivitySetting newSocialActivitySetting = addSocialActivitySetting();

		_persistence.remove(newSocialActivitySetting);

		SocialActivitySetting existingSocialActivitySetting = _persistence.fetchByPrimaryKey(newSocialActivitySetting.getPrimaryKey());

		Assert.assertNull(existingSocialActivitySetting);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addSocialActivitySetting();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SocialActivitySetting newSocialActivitySetting = _persistence.create(pk);

		newSocialActivitySetting.setGroupId(RandomTestUtil.nextLong());

		newSocialActivitySetting.setCompanyId(RandomTestUtil.nextLong());

		newSocialActivitySetting.setClassNameId(RandomTestUtil.nextLong());

		newSocialActivitySetting.setActivityType(RandomTestUtil.nextInt());

		newSocialActivitySetting.setName(RandomTestUtil.randomString());

		newSocialActivitySetting.setValue(RandomTestUtil.randomString());

		_socialActivitySettings.add(_persistence.update(
				newSocialActivitySetting));

		SocialActivitySetting existingSocialActivitySetting = _persistence.findByPrimaryKey(newSocialActivitySetting.getPrimaryKey());

		Assert.assertEquals(existingSocialActivitySetting.getActivitySettingId(),
			newSocialActivitySetting.getActivitySettingId());
		Assert.assertEquals(existingSocialActivitySetting.getGroupId(),
			newSocialActivitySetting.getGroupId());
		Assert.assertEquals(existingSocialActivitySetting.getCompanyId(),
			newSocialActivitySetting.getCompanyId());
		Assert.assertEquals(existingSocialActivitySetting.getClassNameId(),
			newSocialActivitySetting.getClassNameId());
		Assert.assertEquals(existingSocialActivitySetting.getActivityType(),
			newSocialActivitySetting.getActivityType());
		Assert.assertEquals(existingSocialActivitySetting.getName(),
			newSocialActivitySetting.getName());
		Assert.assertEquals(existingSocialActivitySetting.getValue(),
			newSocialActivitySetting.getValue());
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByG_C() throws Exception {
		_persistence.countByG_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_C(0L, 0L);
	}

	@Test
	public void testCountByG_A() throws Exception {
		_persistence.countByG_A(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByG_A(0L, 0);
	}

	@Test
	public void testCountByG_C_A() throws Exception {
		_persistence.countByG_C_A(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByG_C_A(0L, 0L, 0);
	}

	@Test
	public void testCountByG_C_A_N() throws Exception {
		_persistence.countByG_C_A_N(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt(),
			StringPool.BLANK);

		_persistence.countByG_C_A_N(0L, 0L, 0, StringPool.NULL);

		_persistence.countByG_C_A_N(0L, 0L, 0, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		SocialActivitySetting newSocialActivitySetting = addSocialActivitySetting();

		SocialActivitySetting existingSocialActivitySetting = _persistence.findByPrimaryKey(newSocialActivitySetting.getPrimaryKey());

		Assert.assertEquals(existingSocialActivitySetting,
			newSocialActivitySetting);
	}

	@Test(expected = NoSuchActivitySettingException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<SocialActivitySetting> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("SocialActivitySetting",
			"activitySettingId", true, "groupId", true, "companyId", true,
			"classNameId", true, "activityType", true, "name", true, "value",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		SocialActivitySetting newSocialActivitySetting = addSocialActivitySetting();

		SocialActivitySetting existingSocialActivitySetting = _persistence.fetchByPrimaryKey(newSocialActivitySetting.getPrimaryKey());

		Assert.assertEquals(existingSocialActivitySetting,
			newSocialActivitySetting);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SocialActivitySetting missingSocialActivitySetting = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingSocialActivitySetting);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		SocialActivitySetting newSocialActivitySetting1 = addSocialActivitySetting();
		SocialActivitySetting newSocialActivitySetting2 = addSocialActivitySetting();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSocialActivitySetting1.getPrimaryKey());
		primaryKeys.add(newSocialActivitySetting2.getPrimaryKey());

		Map<Serializable, SocialActivitySetting> socialActivitySettings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, socialActivitySettings.size());
		Assert.assertEquals(newSocialActivitySetting1,
			socialActivitySettings.get(
				newSocialActivitySetting1.getPrimaryKey()));
		Assert.assertEquals(newSocialActivitySetting2,
			socialActivitySettings.get(
				newSocialActivitySetting2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, SocialActivitySetting> socialActivitySettings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(socialActivitySettings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		SocialActivitySetting newSocialActivitySetting = addSocialActivitySetting();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSocialActivitySetting.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, SocialActivitySetting> socialActivitySettings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, socialActivitySettings.size());
		Assert.assertEquals(newSocialActivitySetting,
			socialActivitySettings.get(newSocialActivitySetting.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, SocialActivitySetting> socialActivitySettings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(socialActivitySettings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		SocialActivitySetting newSocialActivitySetting = addSocialActivitySetting();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSocialActivitySetting.getPrimaryKey());

		Map<Serializable, SocialActivitySetting> socialActivitySettings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, socialActivitySettings.size());
		Assert.assertEquals(newSocialActivitySetting,
			socialActivitySettings.get(newSocialActivitySetting.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = SocialActivitySettingLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<SocialActivitySetting>() {
				@Override
				public void performAction(
					SocialActivitySetting socialActivitySetting) {
					Assert.assertNotNull(socialActivitySetting);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		SocialActivitySetting newSocialActivitySetting = addSocialActivitySetting();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SocialActivitySetting.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("activitySettingId",
				newSocialActivitySetting.getActivitySettingId()));

		List<SocialActivitySetting> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		SocialActivitySetting existingSocialActivitySetting = result.get(0);

		Assert.assertEquals(existingSocialActivitySetting,
			newSocialActivitySetting);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SocialActivitySetting.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("activitySettingId",
				RandomTestUtil.nextLong()));

		List<SocialActivitySetting> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		SocialActivitySetting newSocialActivitySetting = addSocialActivitySetting();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SocialActivitySetting.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"activitySettingId"));

		Object newActivitySettingId = newSocialActivitySetting.getActivitySettingId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("activitySettingId",
				new Object[] { newActivitySettingId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingActivitySettingId = result.get(0);

		Assert.assertEquals(existingActivitySettingId, newActivitySettingId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SocialActivitySetting.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"activitySettingId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("activitySettingId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		SocialActivitySetting newSocialActivitySetting = addSocialActivitySetting();

		_persistence.clearCache();

		SocialActivitySetting existingSocialActivitySetting = _persistence.findByPrimaryKey(newSocialActivitySetting.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingSocialActivitySetting.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingSocialActivitySetting,
				"getOriginalGroupId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingSocialActivitySetting.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingSocialActivitySetting,
				"getOriginalClassNameId", new Class<?>[0]));
		Assert.assertEquals(Integer.valueOf(
				existingSocialActivitySetting.getActivityType()),
			ReflectionTestUtil.<Integer>invoke(existingSocialActivitySetting,
				"getOriginalActivityType", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(
				existingSocialActivitySetting.getName(),
				ReflectionTestUtil.invoke(existingSocialActivitySetting,
					"getOriginalName", new Class<?>[0])));
	}

	protected SocialActivitySetting addSocialActivitySetting()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		SocialActivitySetting socialActivitySetting = _persistence.create(pk);

		socialActivitySetting.setGroupId(RandomTestUtil.nextLong());

		socialActivitySetting.setCompanyId(RandomTestUtil.nextLong());

		socialActivitySetting.setClassNameId(RandomTestUtil.nextLong());

		socialActivitySetting.setActivityType(RandomTestUtil.nextInt());

		socialActivitySetting.setName(RandomTestUtil.randomString());

		socialActivitySetting.setValue(RandomTestUtil.randomString());

		_socialActivitySettings.add(_persistence.update(socialActivitySetting));

		return socialActivitySetting;
	}

	private List<SocialActivitySetting> _socialActivitySettings = new ArrayList<SocialActivitySetting>();
	private SocialActivitySettingPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}