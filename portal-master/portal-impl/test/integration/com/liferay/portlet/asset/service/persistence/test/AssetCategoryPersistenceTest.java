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

package com.liferay.portlet.asset.service.persistence.test;

import com.liferay.asset.kernel.exception.NoSuchCategoryException;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetCategoryPersistence;
import com.liferay.asset.kernel.service.persistence.AssetCategoryUtil;

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
public class AssetCategoryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = AssetCategoryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AssetCategory> iterator = _assetCategories.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetCategory assetCategory = _persistence.create(pk);

		Assert.assertNotNull(assetCategory);

		Assert.assertEquals(assetCategory.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AssetCategory newAssetCategory = addAssetCategory();

		_persistence.remove(newAssetCategory);

		AssetCategory existingAssetCategory = _persistence.fetchByPrimaryKey(newAssetCategory.getPrimaryKey());

		Assert.assertNull(existingAssetCategory);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAssetCategory();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetCategory newAssetCategory = _persistence.create(pk);

		newAssetCategory.setUuid(RandomTestUtil.randomString());

		newAssetCategory.setGroupId(RandomTestUtil.nextLong());

		newAssetCategory.setCompanyId(RandomTestUtil.nextLong());

		newAssetCategory.setUserId(RandomTestUtil.nextLong());

		newAssetCategory.setUserName(RandomTestUtil.randomString());

		newAssetCategory.setCreateDate(RandomTestUtil.nextDate());

		newAssetCategory.setModifiedDate(RandomTestUtil.nextDate());

		newAssetCategory.setLeftCategoryId(RandomTestUtil.nextLong());

		newAssetCategory.setRightCategoryId(RandomTestUtil.nextLong());

		newAssetCategory.setName(RandomTestUtil.randomString());

		newAssetCategory.setTitle(RandomTestUtil.randomString());

		newAssetCategory.setDescription(RandomTestUtil.randomString());

		newAssetCategory.setVocabularyId(RandomTestUtil.nextLong());

		newAssetCategory.setLastPublishDate(RandomTestUtil.nextDate());

		_assetCategories.add(_persistence.update(newAssetCategory));

		AssetCategory existingAssetCategory = _persistence.findByPrimaryKey(newAssetCategory.getPrimaryKey());

		Assert.assertEquals(existingAssetCategory.getUuid(),
			newAssetCategory.getUuid());
		Assert.assertEquals(existingAssetCategory.getCategoryId(),
			newAssetCategory.getCategoryId());
		Assert.assertEquals(existingAssetCategory.getGroupId(),
			newAssetCategory.getGroupId());
		Assert.assertEquals(existingAssetCategory.getCompanyId(),
			newAssetCategory.getCompanyId());
		Assert.assertEquals(existingAssetCategory.getUserId(),
			newAssetCategory.getUserId());
		Assert.assertEquals(existingAssetCategory.getUserName(),
			newAssetCategory.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAssetCategory.getCreateDate()),
			Time.getShortTimestamp(newAssetCategory.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingAssetCategory.getModifiedDate()),
			Time.getShortTimestamp(newAssetCategory.getModifiedDate()));
		Assert.assertEquals(existingAssetCategory.getParentCategoryId(),
			newAssetCategory.getParentCategoryId());
		Assert.assertEquals(existingAssetCategory.getLeftCategoryId(),
			newAssetCategory.getLeftCategoryId());
		Assert.assertEquals(existingAssetCategory.getRightCategoryId(),
			newAssetCategory.getRightCategoryId());
		Assert.assertEquals(existingAssetCategory.getName(),
			newAssetCategory.getName());
		Assert.assertEquals(existingAssetCategory.getTitle(),
			newAssetCategory.getTitle());
		Assert.assertEquals(existingAssetCategory.getDescription(),
			newAssetCategory.getDescription());
		Assert.assertEquals(existingAssetCategory.getVocabularyId(),
			newAssetCategory.getVocabularyId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAssetCategory.getLastPublishDate()),
			Time.getShortTimestamp(newAssetCategory.getLastPublishDate()));
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid(StringPool.BLANK);

		_persistence.countByUuid(StringPool.NULL);

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByUUID_G(StringPool.NULL, 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByUuid_C(StringPool.NULL, 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByParentCategoryId() throws Exception {
		_persistence.countByParentCategoryId(RandomTestUtil.nextLong());

		_persistence.countByParentCategoryId(0L);
	}

	@Test
	public void testCountByVocabularyId() throws Exception {
		_persistence.countByVocabularyId(RandomTestUtil.nextLong());

		_persistence.countByVocabularyId(0L);
	}

	@Test
	public void testCountByG_V() throws Exception {
		_persistence.countByG_V(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_V(0L, 0L);
	}

	@Test
	public void testCountByG_VArrayable() throws Exception {
		_persistence.countByG_V(RandomTestUtil.nextLong(),
			new long[] { RandomTestUtil.nextLong(), 0L });
	}

	@Test
	public void testCountByP_N() throws Exception {
		_persistence.countByP_N(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByP_N(0L, StringPool.NULL);

		_persistence.countByP_N(0L, (String)null);
	}

	@Test
	public void testCountByP_V() throws Exception {
		_persistence.countByP_V(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByP_V(0L, 0L);
	}

	@Test
	public void testCountByN_V() throws Exception {
		_persistence.countByN_V(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByN_V(StringPool.NULL, 0L);

		_persistence.countByN_V((String)null, 0L);
	}

	@Test
	public void testCountByG_P_V() throws Exception {
		_persistence.countByG_P_V(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByG_P_V(0L, 0L, 0L);
	}

	@Test
	public void testCountByG_LikeN_V() throws Exception {
		_persistence.countByG_LikeN_V(RandomTestUtil.nextLong(),
			StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByG_LikeN_V(0L, StringPool.NULL, 0L);

		_persistence.countByG_LikeN_V(0L, (String)null, 0L);
	}

	@Test
	public void testCountByG_LikeN_VArrayable() throws Exception {
		_persistence.countByG_LikeN_V(RandomTestUtil.nextLong(),
			RandomTestUtil.randomString(),
			new long[] { RandomTestUtil.nextLong(), 0L });
	}

	@Test
	public void testCountByP_N_V() throws Exception {
		_persistence.countByP_N_V(RandomTestUtil.nextLong(), StringPool.BLANK,
			RandomTestUtil.nextLong());

		_persistence.countByP_N_V(0L, StringPool.NULL, 0L);

		_persistence.countByP_N_V(0L, (String)null, 0L);
	}

	@Test
	public void testCountByG_P_N_V() throws Exception {
		_persistence.countByG_P_N_V(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), StringPool.BLANK,
			RandomTestUtil.nextLong());

		_persistence.countByG_P_N_V(0L, 0L, StringPool.NULL, 0L);

		_persistence.countByG_P_N_V(0L, 0L, (String)null, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AssetCategory newAssetCategory = addAssetCategory();

		AssetCategory existingAssetCategory = _persistence.findByPrimaryKey(newAssetCategory.getPrimaryKey());

		Assert.assertEquals(existingAssetCategory, newAssetCategory);
	}

	@Test(expected = NoSuchCategoryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	@Test
	public void testFilterFindByGroupId() throws Exception {
		_persistence.filterFindByGroupId(0, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<AssetCategory> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("AssetCategory", "uuid",
			true, "categoryId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "parentCategoryId", true, "leftCategoryId",
			true, "rightCategoryId", true, "name", true, "title", true,
			"description", true, "vocabularyId", true, "lastPublishDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AssetCategory newAssetCategory = addAssetCategory();

		AssetCategory existingAssetCategory = _persistence.fetchByPrimaryKey(newAssetCategory.getPrimaryKey());

		Assert.assertEquals(existingAssetCategory, newAssetCategory);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetCategory missingAssetCategory = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAssetCategory);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AssetCategory newAssetCategory1 = addAssetCategory();
		AssetCategory newAssetCategory2 = addAssetCategory();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetCategory1.getPrimaryKey());
		primaryKeys.add(newAssetCategory2.getPrimaryKey());

		Map<Serializable, AssetCategory> assetCategories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, assetCategories.size());
		Assert.assertEquals(newAssetCategory1,
			assetCategories.get(newAssetCategory1.getPrimaryKey()));
		Assert.assertEquals(newAssetCategory2,
			assetCategories.get(newAssetCategory2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AssetCategory> assetCategories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(assetCategories.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AssetCategory newAssetCategory = addAssetCategory();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetCategory.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AssetCategory> assetCategories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, assetCategories.size());
		Assert.assertEquals(newAssetCategory,
			assetCategories.get(newAssetCategory.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AssetCategory> assetCategories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(assetCategories.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AssetCategory newAssetCategory = addAssetCategory();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetCategory.getPrimaryKey());

		Map<Serializable, AssetCategory> assetCategories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, assetCategories.size());
		Assert.assertEquals(newAssetCategory,
			assetCategories.get(newAssetCategory.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AssetCategoryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AssetCategory>() {
				@Override
				public void performAction(AssetCategory assetCategory) {
					Assert.assertNotNull(assetCategory);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AssetCategory newAssetCategory = addAssetCategory();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategory.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("categoryId",
				newAssetCategory.getCategoryId()));

		List<AssetCategory> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AssetCategory existingAssetCategory = result.get(0);

		Assert.assertEquals(existingAssetCategory, newAssetCategory);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategory.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("categoryId",
				RandomTestUtil.nextLong()));

		List<AssetCategory> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AssetCategory newAssetCategory = addAssetCategory();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategory.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("categoryId"));

		Object newCategoryId = newAssetCategory.getCategoryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("categoryId",
				new Object[] { newCategoryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCategoryId = result.get(0);

		Assert.assertEquals(existingCategoryId, newCategoryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategory.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("categoryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("categoryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		AssetCategory newAssetCategory = addAssetCategory();

		_persistence.clearCache();

		AssetCategory existingAssetCategory = _persistence.findByPrimaryKey(newAssetCategory.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingAssetCategory.getUuid(),
				ReflectionTestUtil.invoke(existingAssetCategory,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingAssetCategory.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingAssetCategory,
				"getOriginalGroupId", new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(
				existingAssetCategory.getParentCategoryId()),
			ReflectionTestUtil.<Long>invoke(existingAssetCategory,
				"getOriginalParentCategoryId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingAssetCategory.getName(),
				ReflectionTestUtil.invoke(existingAssetCategory,
					"getOriginalName", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(
				existingAssetCategory.getVocabularyId()),
			ReflectionTestUtil.<Long>invoke(existingAssetCategory,
				"getOriginalVocabularyId", new Class<?>[0]));
	}

	protected AssetCategory addAssetCategory() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetCategory assetCategory = _persistence.create(pk);

		assetCategory.setUuid(RandomTestUtil.randomString());

		assetCategory.setGroupId(RandomTestUtil.nextLong());

		assetCategory.setCompanyId(RandomTestUtil.nextLong());

		assetCategory.setUserId(RandomTestUtil.nextLong());

		assetCategory.setUserName(RandomTestUtil.randomString());

		assetCategory.setCreateDate(RandomTestUtil.nextDate());

		assetCategory.setModifiedDate(RandomTestUtil.nextDate());

		assetCategory.setLeftCategoryId(RandomTestUtil.nextLong());

		assetCategory.setRightCategoryId(RandomTestUtil.nextLong());

		assetCategory.setName(RandomTestUtil.randomString());

		assetCategory.setTitle(RandomTestUtil.randomString());

		assetCategory.setDescription(RandomTestUtil.randomString());

		assetCategory.setVocabularyId(RandomTestUtil.nextLong());

		assetCategory.setLastPublishDate(RandomTestUtil.nextDate());

		_assetCategories.add(_persistence.update(assetCategory));

		return assetCategory;
	}

	@Test
	public void testMoveTree() throws Exception {
		long groupId = RandomTestUtil.nextLong();

		AssetCategory rootAssetCategory = addAssetCategory(groupId, null);

		long previousRootLeftCategoryId = rootAssetCategory.getLeftCategoryId();
		long previousRootRightCategoryId = rootAssetCategory.getRightCategoryId();

		AssetCategory childAssetCategory = addAssetCategory(groupId,
				rootAssetCategory.getCategoryId());

		rootAssetCategory = _persistence.fetchByPrimaryKey(rootAssetCategory.getPrimaryKey());

		Assert.assertEquals(previousRootLeftCategoryId,
			rootAssetCategory.getLeftCategoryId());
		Assert.assertEquals(previousRootRightCategoryId + 2,
			rootAssetCategory.getRightCategoryId());
		Assert.assertEquals(rootAssetCategory.getLeftCategoryId() + 1,
			childAssetCategory.getLeftCategoryId());
		Assert.assertEquals(rootAssetCategory.getRightCategoryId() - 1,
			childAssetCategory.getRightCategoryId());
	}

	@Test
	public void testMoveTreeFromLeft() throws Exception {
		long groupId = RandomTestUtil.nextLong();

		AssetCategory parentAssetCategory = addAssetCategory(groupId, null);

		AssetCategory childAssetCategory = addAssetCategory(groupId,
				parentAssetCategory.getCategoryId());

		parentAssetCategory = _persistence.fetchByPrimaryKey(parentAssetCategory.getPrimaryKey());

		AssetCategory rootAssetCategory = addAssetCategory(groupId, null);

		long previousRootLeftCategoryId = rootAssetCategory.getLeftCategoryId();
		long previousRootRightCategoryId = rootAssetCategory.getRightCategoryId();

		parentAssetCategory.setParentCategoryId(rootAssetCategory.getCategoryId());

		_persistence.update(parentAssetCategory);

		rootAssetCategory = _persistence.fetchByPrimaryKey(rootAssetCategory.getPrimaryKey());
		childAssetCategory = _persistence.fetchByPrimaryKey(childAssetCategory.getPrimaryKey());

		Assert.assertEquals(previousRootLeftCategoryId - 4,
			rootAssetCategory.getLeftCategoryId());
		Assert.assertEquals(previousRootRightCategoryId,
			rootAssetCategory.getRightCategoryId());
		Assert.assertEquals(rootAssetCategory.getLeftCategoryId() + 1,
			parentAssetCategory.getLeftCategoryId());
		Assert.assertEquals(rootAssetCategory.getRightCategoryId() - 1,
			parentAssetCategory.getRightCategoryId());
		Assert.assertEquals(parentAssetCategory.getLeftCategoryId() + 1,
			childAssetCategory.getLeftCategoryId());
		Assert.assertEquals(parentAssetCategory.getRightCategoryId() - 1,
			childAssetCategory.getRightCategoryId());
	}

	@Test
	public void testMoveTreeFromRight() throws Exception {
		long groupId = RandomTestUtil.nextLong();

		AssetCategory rootAssetCategory = addAssetCategory(groupId, null);

		long previousRootLeftCategoryId = rootAssetCategory.getLeftCategoryId();
		long previousRootRightCategoryId = rootAssetCategory.getRightCategoryId();

		AssetCategory parentAssetCategory = addAssetCategory(groupId, null);

		AssetCategory childAssetCategory = addAssetCategory(groupId,
				parentAssetCategory.getCategoryId());

		parentAssetCategory = _persistence.fetchByPrimaryKey(parentAssetCategory.getPrimaryKey());

		parentAssetCategory.setParentCategoryId(rootAssetCategory.getCategoryId());

		_persistence.update(parentAssetCategory);

		rootAssetCategory = _persistence.fetchByPrimaryKey(rootAssetCategory.getPrimaryKey());
		childAssetCategory = _persistence.fetchByPrimaryKey(childAssetCategory.getPrimaryKey());

		Assert.assertEquals(previousRootLeftCategoryId,
			rootAssetCategory.getLeftCategoryId());
		Assert.assertEquals(previousRootRightCategoryId + 4,
			rootAssetCategory.getRightCategoryId());
		Assert.assertEquals(rootAssetCategory.getLeftCategoryId() + 1,
			parentAssetCategory.getLeftCategoryId());
		Assert.assertEquals(rootAssetCategory.getRightCategoryId() - 1,
			parentAssetCategory.getRightCategoryId());
		Assert.assertEquals(parentAssetCategory.getLeftCategoryId() + 1,
			childAssetCategory.getLeftCategoryId());
		Assert.assertEquals(parentAssetCategory.getRightCategoryId() - 1,
			childAssetCategory.getRightCategoryId());
	}

	@Test
	public void testMoveTreeIntoTreeFromLeft() throws Exception {
		long groupId = RandomTestUtil.nextLong();

		AssetCategory parentAssetCategory = addAssetCategory(groupId, null);

		AssetCategory parentChildAssetCategory = addAssetCategory(groupId,
				parentAssetCategory.getCategoryId());

		parentAssetCategory = _persistence.fetchByPrimaryKey(parentAssetCategory.getPrimaryKey());

		AssetCategory rootAssetCategory = addAssetCategory(groupId, null);

		AssetCategory leftRootChildAssetCategory = addAssetCategory(groupId,
				rootAssetCategory.getCategoryId());

		rootAssetCategory = _persistence.fetchByPrimaryKey(rootAssetCategory.getPrimaryKey());

		AssetCategory rightRootChildAssetCategory = addAssetCategory(groupId,
				rootAssetCategory.getCategoryId());

		rootAssetCategory = _persistence.fetchByPrimaryKey(rootAssetCategory.getPrimaryKey());

		long previousRootLeftCategoryId = rootAssetCategory.getLeftCategoryId();
		long previousRootRightCategoryId = rootAssetCategory.getRightCategoryId();

		parentAssetCategory.setParentCategoryId(rightRootChildAssetCategory.getCategoryId());

		_persistence.update(parentAssetCategory);

		rootAssetCategory = _persistence.fetchByPrimaryKey(rootAssetCategory.getPrimaryKey());
		leftRootChildAssetCategory = _persistence.fetchByPrimaryKey(leftRootChildAssetCategory.getPrimaryKey());
		rightRootChildAssetCategory = _persistence.fetchByPrimaryKey(rightRootChildAssetCategory.getPrimaryKey());
		parentChildAssetCategory = _persistence.fetchByPrimaryKey(parentChildAssetCategory.getPrimaryKey());

		Assert.assertEquals(previousRootLeftCategoryId - 4,
			rootAssetCategory.getLeftCategoryId());
		Assert.assertEquals(previousRootRightCategoryId,
			rootAssetCategory.getRightCategoryId());
		Assert.assertEquals(rootAssetCategory.getLeftCategoryId() + 1,
			leftRootChildAssetCategory.getLeftCategoryId());
		Assert.assertEquals(rootAssetCategory.getRightCategoryId() - 7,
			leftRootChildAssetCategory.getRightCategoryId());
		Assert.assertEquals(rootAssetCategory.getLeftCategoryId() + 3,
			rightRootChildAssetCategory.getLeftCategoryId());
		Assert.assertEquals(rootAssetCategory.getRightCategoryId() - 1,
			rightRootChildAssetCategory.getRightCategoryId());
		Assert.assertEquals(rightRootChildAssetCategory.getLeftCategoryId() +
			1, parentAssetCategory.getLeftCategoryId());
		Assert.assertEquals(rightRootChildAssetCategory.getRightCategoryId() -
			1, parentAssetCategory.getRightCategoryId());
		Assert.assertEquals(parentAssetCategory.getLeftCategoryId() + 1,
			parentChildAssetCategory.getLeftCategoryId());
		Assert.assertEquals(parentAssetCategory.getRightCategoryId() - 1,
			parentChildAssetCategory.getRightCategoryId());
	}

	@Test
	public void testMoveTreeIntoTreeFromRight() throws Exception {
		long groupId = RandomTestUtil.nextLong();

		AssetCategory rootAssetCategory = addAssetCategory(groupId, null);

		AssetCategory leftRootChildAssetCategory = addAssetCategory(groupId,
				rootAssetCategory.getCategoryId());

		rootAssetCategory = _persistence.fetchByPrimaryKey(rootAssetCategory.getPrimaryKey());

		AssetCategory rightRootChildAssetCategory = addAssetCategory(groupId,
				rootAssetCategory.getCategoryId());

		rootAssetCategory = _persistence.fetchByPrimaryKey(rootAssetCategory.getPrimaryKey());

		long previousRootLeftCategoryId = rootAssetCategory.getLeftCategoryId();
		long previousRootRightCategoryId = rootAssetCategory.getRightCategoryId();

		AssetCategory parentAssetCategory = addAssetCategory(groupId, null);

		AssetCategory parentChildAssetCategory = addAssetCategory(groupId,
				parentAssetCategory.getCategoryId());

		parentAssetCategory = _persistence.fetchByPrimaryKey(parentAssetCategory.getPrimaryKey());

		parentAssetCategory.setParentCategoryId(leftRootChildAssetCategory.getCategoryId());

		_persistence.update(parentAssetCategory);

		rootAssetCategory = _persistence.fetchByPrimaryKey(rootAssetCategory.getPrimaryKey());
		leftRootChildAssetCategory = _persistence.fetchByPrimaryKey(leftRootChildAssetCategory.getPrimaryKey());
		rightRootChildAssetCategory = _persistence.fetchByPrimaryKey(rightRootChildAssetCategory.getPrimaryKey());
		parentChildAssetCategory = _persistence.fetchByPrimaryKey(parentChildAssetCategory.getPrimaryKey());

		Assert.assertEquals(previousRootLeftCategoryId,
			rootAssetCategory.getLeftCategoryId());
		Assert.assertEquals(previousRootRightCategoryId + 4,
			rootAssetCategory.getRightCategoryId());
		Assert.assertEquals(rootAssetCategory.getLeftCategoryId() + 1,
			leftRootChildAssetCategory.getLeftCategoryId());
		Assert.assertEquals(rootAssetCategory.getRightCategoryId() - 3,
			leftRootChildAssetCategory.getRightCategoryId());
		Assert.assertEquals(rootAssetCategory.getLeftCategoryId() + 7,
			rightRootChildAssetCategory.getLeftCategoryId());
		Assert.assertEquals(rootAssetCategory.getRightCategoryId() - 1,
			rightRootChildAssetCategory.getRightCategoryId());
		Assert.assertEquals(leftRootChildAssetCategory.getLeftCategoryId() + 1,
			parentAssetCategory.getLeftCategoryId());
		Assert.assertEquals(leftRootChildAssetCategory.getRightCategoryId() -
			1, parentAssetCategory.getRightCategoryId());
		Assert.assertEquals(parentAssetCategory.getLeftCategoryId() + 1,
			parentChildAssetCategory.getLeftCategoryId());
		Assert.assertEquals(parentAssetCategory.getRightCategoryId() - 1,
			parentChildAssetCategory.getRightCategoryId());
	}

	protected AssetCategory addAssetCategory(long groupId, Long parentCategoryId)
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetCategory assetCategory = _persistence.create(pk);

		assetCategory.setUuid(RandomTestUtil.randomString());
		assetCategory.setGroupId(groupId);

		assetCategory.setCompanyId(RandomTestUtil.nextLong());

		assetCategory.setUserId(RandomTestUtil.nextLong());

		assetCategory.setUserName(RandomTestUtil.randomString());

		assetCategory.setCreateDate(RandomTestUtil.nextDate());

		assetCategory.setModifiedDate(RandomTestUtil.nextDate());

		assetCategory.setLeftCategoryId(RandomTestUtil.nextLong());

		assetCategory.setRightCategoryId(RandomTestUtil.nextLong());

		assetCategory.setName(RandomTestUtil.randomString());

		assetCategory.setTitle(RandomTestUtil.randomString());

		assetCategory.setDescription(RandomTestUtil.randomString());

		assetCategory.setVocabularyId(RandomTestUtil.nextLong());

		assetCategory.setLastPublishDate(RandomTestUtil.nextDate());

		if (parentCategoryId != null) {
			assetCategory.setParentCategoryId(parentCategoryId);
		}

		_persistence.update(assetCategory);

		return assetCategory;
	}

	private List<AssetCategory> _assetCategories = new ArrayList<AssetCategory>();
	private AssetCategoryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}