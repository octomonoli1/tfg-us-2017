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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;

import com.liferay.social.kernel.exception.NoSuchActivityException;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.service.SocialActivityLocalServiceUtil;
import com.liferay.social.kernel.service.persistence.SocialActivityPersistence;
import com.liferay.social.kernel.service.persistence.SocialActivityUtil;

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
public class SocialActivityPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = SocialActivityUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<SocialActivity> iterator = _socialActivities.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SocialActivity socialActivity = _persistence.create(pk);

		Assert.assertNotNull(socialActivity);

		Assert.assertEquals(socialActivity.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		SocialActivity newSocialActivity = addSocialActivity();

		_persistence.remove(newSocialActivity);

		SocialActivity existingSocialActivity = _persistence.fetchByPrimaryKey(newSocialActivity.getPrimaryKey());

		Assert.assertNull(existingSocialActivity);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addSocialActivity();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SocialActivity newSocialActivity = _persistence.create(pk);

		newSocialActivity.setGroupId(RandomTestUtil.nextLong());

		newSocialActivity.setCompanyId(RandomTestUtil.nextLong());

		newSocialActivity.setUserId(RandomTestUtil.nextLong());

		newSocialActivity.setCreateDate(RandomTestUtil.nextLong());

		newSocialActivity.setActivitySetId(RandomTestUtil.nextLong());

		newSocialActivity.setMirrorActivityId(RandomTestUtil.nextLong());

		newSocialActivity.setClassNameId(RandomTestUtil.nextLong());

		newSocialActivity.setClassPK(RandomTestUtil.nextLong());

		newSocialActivity.setParentClassNameId(RandomTestUtil.nextLong());

		newSocialActivity.setParentClassPK(RandomTestUtil.nextLong());

		newSocialActivity.setType(RandomTestUtil.nextInt());

		newSocialActivity.setExtraData(RandomTestUtil.randomString());

		newSocialActivity.setReceiverUserId(RandomTestUtil.nextLong());

		_socialActivities.add(_persistence.update(newSocialActivity));

		SocialActivity existingSocialActivity = _persistence.findByPrimaryKey(newSocialActivity.getPrimaryKey());

		Assert.assertEquals(existingSocialActivity.getActivityId(),
			newSocialActivity.getActivityId());
		Assert.assertEquals(existingSocialActivity.getGroupId(),
			newSocialActivity.getGroupId());
		Assert.assertEquals(existingSocialActivity.getCompanyId(),
			newSocialActivity.getCompanyId());
		Assert.assertEquals(existingSocialActivity.getUserId(),
			newSocialActivity.getUserId());
		Assert.assertEquals(existingSocialActivity.getCreateDate(),
			newSocialActivity.getCreateDate());
		Assert.assertEquals(existingSocialActivity.getActivitySetId(),
			newSocialActivity.getActivitySetId());
		Assert.assertEquals(existingSocialActivity.getMirrorActivityId(),
			newSocialActivity.getMirrorActivityId());
		Assert.assertEquals(existingSocialActivity.getClassNameId(),
			newSocialActivity.getClassNameId());
		Assert.assertEquals(existingSocialActivity.getClassPK(),
			newSocialActivity.getClassPK());
		Assert.assertEquals(existingSocialActivity.getParentClassNameId(),
			newSocialActivity.getParentClassNameId());
		Assert.assertEquals(existingSocialActivity.getParentClassPK(),
			newSocialActivity.getParentClassPK());
		Assert.assertEquals(existingSocialActivity.getType(),
			newSocialActivity.getType());
		Assert.assertEquals(existingSocialActivity.getExtraData(),
			newSocialActivity.getExtraData());
		Assert.assertEquals(existingSocialActivity.getReceiverUserId(),
			newSocialActivity.getReceiverUserId());
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(RandomTestUtil.nextLong());

		_persistence.countByCompanyId(0L);
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testCountByActivitySetId() throws Exception {
		_persistence.countByActivitySetId(RandomTestUtil.nextLong());

		_persistence.countByActivitySetId(0L);
	}

	@Test
	public void testCountByMirrorActivityId() throws Exception {
		_persistence.countByMirrorActivityId(RandomTestUtil.nextLong());

		_persistence.countByMirrorActivityId(0L);
	}

	@Test
	public void testCountByClassNameId() throws Exception {
		_persistence.countByClassNameId(RandomTestUtil.nextLong());

		_persistence.countByClassNameId(0L);
	}

	@Test
	public void testCountByReceiverUserId() throws Exception {
		_persistence.countByReceiverUserId(RandomTestUtil.nextLong());

		_persistence.countByReceiverUserId(0L);
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testCountByM_C_C() throws Exception {
		_persistence.countByM_C_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByM_C_C(0L, 0L, 0L);
	}

	@Test
	public void testCountByC_C_T() throws Exception {
		_persistence.countByC_C_T(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByC_C_T(0L, 0L, 0);
	}

	@Test
	public void testCountByG_U_C_C_T_R() throws Exception {
		_persistence.countByG_U_C_C_T_R(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt(),
			RandomTestUtil.nextLong());

		_persistence.countByG_U_C_C_T_R(0L, 0L, 0L, 0L, 0, 0L);
	}

	@Test
	public void testCountByG_U_CD_C_C_T_R() throws Exception {
		_persistence.countByG_U_CD_C_C_T_R(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt(), RandomTestUtil.nextLong());

		_persistence.countByG_U_CD_C_C_T_R(0L, 0L, 0L, 0L, 0L, 0, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		SocialActivity newSocialActivity = addSocialActivity();

		SocialActivity existingSocialActivity = _persistence.findByPrimaryKey(newSocialActivity.getPrimaryKey());

		Assert.assertEquals(existingSocialActivity, newSocialActivity);
	}

	@Test(expected = NoSuchActivityException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<SocialActivity> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("SocialActivity",
			"activityId", true, "groupId", true, "companyId", true, "userId",
			true, "createDate", true, "activitySetId", true,
			"mirrorActivityId", true, "classNameId", true, "classPK", true,
			"parentClassNameId", true, "parentClassPK", true, "type", true,
			"extraData", true, "receiverUserId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		SocialActivity newSocialActivity = addSocialActivity();

		SocialActivity existingSocialActivity = _persistence.fetchByPrimaryKey(newSocialActivity.getPrimaryKey());

		Assert.assertEquals(existingSocialActivity, newSocialActivity);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SocialActivity missingSocialActivity = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingSocialActivity);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		SocialActivity newSocialActivity1 = addSocialActivity();
		SocialActivity newSocialActivity2 = addSocialActivity();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSocialActivity1.getPrimaryKey());
		primaryKeys.add(newSocialActivity2.getPrimaryKey());

		Map<Serializable, SocialActivity> socialActivities = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, socialActivities.size());
		Assert.assertEquals(newSocialActivity1,
			socialActivities.get(newSocialActivity1.getPrimaryKey()));
		Assert.assertEquals(newSocialActivity2,
			socialActivities.get(newSocialActivity2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, SocialActivity> socialActivities = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(socialActivities.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		SocialActivity newSocialActivity = addSocialActivity();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSocialActivity.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, SocialActivity> socialActivities = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, socialActivities.size());
		Assert.assertEquals(newSocialActivity,
			socialActivities.get(newSocialActivity.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, SocialActivity> socialActivities = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(socialActivities.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		SocialActivity newSocialActivity = addSocialActivity();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSocialActivity.getPrimaryKey());

		Map<Serializable, SocialActivity> socialActivities = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, socialActivities.size());
		Assert.assertEquals(newSocialActivity,
			socialActivities.get(newSocialActivity.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = SocialActivityLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<SocialActivity>() {
				@Override
				public void performAction(SocialActivity socialActivity) {
					Assert.assertNotNull(socialActivity);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		SocialActivity newSocialActivity = addSocialActivity();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SocialActivity.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("activityId",
				newSocialActivity.getActivityId()));

		List<SocialActivity> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		SocialActivity existingSocialActivity = result.get(0);

		Assert.assertEquals(existingSocialActivity, newSocialActivity);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SocialActivity.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("activityId",
				RandomTestUtil.nextLong()));

		List<SocialActivity> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		SocialActivity newSocialActivity = addSocialActivity();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SocialActivity.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("activityId"));

		Object newActivityId = newSocialActivity.getActivityId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("activityId",
				new Object[] { newActivityId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingActivityId = result.get(0);

		Assert.assertEquals(existingActivityId, newActivityId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SocialActivity.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("activityId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("activityId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		SocialActivity newSocialActivity = addSocialActivity();

		_persistence.clearCache();

		SocialActivity existingSocialActivity = _persistence.findByPrimaryKey(newSocialActivity.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingSocialActivity.getMirrorActivityId()),
			ReflectionTestUtil.<Long>invoke(existingSocialActivity,
				"getOriginalMirrorActivityId", new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(existingSocialActivity.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingSocialActivity,
				"getOriginalGroupId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingSocialActivity.getUserId()),
			ReflectionTestUtil.<Long>invoke(existingSocialActivity,
				"getOriginalUserId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingSocialActivity.getCreateDate()),
			ReflectionTestUtil.<Long>invoke(existingSocialActivity,
				"getOriginalCreateDate", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingSocialActivity.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingSocialActivity,
				"getOriginalClassNameId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingSocialActivity.getClassPK()),
			ReflectionTestUtil.<Long>invoke(existingSocialActivity,
				"getOriginalClassPK", new Class<?>[0]));
		Assert.assertEquals(Integer.valueOf(existingSocialActivity.getType()),
			ReflectionTestUtil.<Integer>invoke(existingSocialActivity,
				"getOriginalType", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingSocialActivity.getReceiverUserId()),
			ReflectionTestUtil.<Long>invoke(existingSocialActivity,
				"getOriginalReceiverUserId", new Class<?>[0]));
	}

	protected SocialActivity addSocialActivity() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SocialActivity socialActivity = _persistence.create(pk);

		socialActivity.setGroupId(RandomTestUtil.nextLong());

		socialActivity.setCompanyId(RandomTestUtil.nextLong());

		socialActivity.setUserId(RandomTestUtil.nextLong());

		socialActivity.setCreateDate(RandomTestUtil.nextLong());

		socialActivity.setActivitySetId(RandomTestUtil.nextLong());

		socialActivity.setMirrorActivityId(RandomTestUtil.nextLong());

		socialActivity.setClassNameId(RandomTestUtil.nextLong());

		socialActivity.setClassPK(RandomTestUtil.nextLong());

		socialActivity.setParentClassNameId(RandomTestUtil.nextLong());

		socialActivity.setParentClassPK(RandomTestUtil.nextLong());

		socialActivity.setType(RandomTestUtil.nextInt());

		socialActivity.setExtraData(RandomTestUtil.randomString());

		socialActivity.setReceiverUserId(RandomTestUtil.nextLong());

		_socialActivities.add(_persistence.update(socialActivity));

		return socialActivity;
	}

	private List<SocialActivity> _socialActivities = new ArrayList<SocialActivity>();
	private SocialActivityPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}