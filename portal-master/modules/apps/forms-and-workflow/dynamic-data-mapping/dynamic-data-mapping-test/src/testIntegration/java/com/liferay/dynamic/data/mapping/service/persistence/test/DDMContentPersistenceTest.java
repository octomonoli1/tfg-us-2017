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

package com.liferay.dynamic.data.mapping.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.dynamic.data.mapping.exception.NoSuchContentException;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.persistence.DDMContentPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMContentUtil;

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
public class DDMContentPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = DDMContentUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DDMContent> iterator = _ddmContents.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMContent ddmContent = _persistence.create(pk);

		Assert.assertNotNull(ddmContent);

		Assert.assertEquals(ddmContent.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DDMContent newDDMContent = addDDMContent();

		_persistence.remove(newDDMContent);

		DDMContent existingDDMContent = _persistence.fetchByPrimaryKey(newDDMContent.getPrimaryKey());

		Assert.assertNull(existingDDMContent);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDDMContent();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMContent newDDMContent = _persistence.create(pk);

		newDDMContent.setUuid(RandomTestUtil.randomString());

		newDDMContent.setGroupId(RandomTestUtil.nextLong());

		newDDMContent.setCompanyId(RandomTestUtil.nextLong());

		newDDMContent.setUserId(RandomTestUtil.nextLong());

		newDDMContent.setUserName(RandomTestUtil.randomString());

		newDDMContent.setCreateDate(RandomTestUtil.nextDate());

		newDDMContent.setModifiedDate(RandomTestUtil.nextDate());

		newDDMContent.setName(RandomTestUtil.randomString());

		newDDMContent.setDescription(RandomTestUtil.randomString());

		newDDMContent.setData(RandomTestUtil.randomString());

		_ddmContents.add(_persistence.update(newDDMContent));

		DDMContent existingDDMContent = _persistence.findByPrimaryKey(newDDMContent.getPrimaryKey());

		Assert.assertEquals(existingDDMContent.getUuid(),
			newDDMContent.getUuid());
		Assert.assertEquals(existingDDMContent.getContentId(),
			newDDMContent.getContentId());
		Assert.assertEquals(existingDDMContent.getGroupId(),
			newDDMContent.getGroupId());
		Assert.assertEquals(existingDDMContent.getCompanyId(),
			newDDMContent.getCompanyId());
		Assert.assertEquals(existingDDMContent.getUserId(),
			newDDMContent.getUserId());
		Assert.assertEquals(existingDDMContent.getUserName(),
			newDDMContent.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDDMContent.getCreateDate()),
			Time.getShortTimestamp(newDDMContent.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingDDMContent.getModifiedDate()),
			Time.getShortTimestamp(newDDMContent.getModifiedDate()));
		Assert.assertEquals(existingDDMContent.getName(),
			newDDMContent.getName());
		Assert.assertEquals(existingDDMContent.getDescription(),
			newDDMContent.getDescription());
		Assert.assertEquals(existingDDMContent.getData(),
			newDDMContent.getData());
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
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(RandomTestUtil.nextLong());

		_persistence.countByCompanyId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DDMContent newDDMContent = addDDMContent();

		DDMContent existingDDMContent = _persistence.findByPrimaryKey(newDDMContent.getPrimaryKey());

		Assert.assertEquals(existingDDMContent, newDDMContent);
	}

	@Test(expected = NoSuchContentException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<DDMContent> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("DDMContent", "uuid", true,
			"contentId", true, "groupId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"name", true, "description", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DDMContent newDDMContent = addDDMContent();

		DDMContent existingDDMContent = _persistence.fetchByPrimaryKey(newDDMContent.getPrimaryKey());

		Assert.assertEquals(existingDDMContent, newDDMContent);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMContent missingDDMContent = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDDMContent);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		DDMContent newDDMContent1 = addDDMContent();
		DDMContent newDDMContent2 = addDDMContent();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMContent1.getPrimaryKey());
		primaryKeys.add(newDDMContent2.getPrimaryKey());

		Map<Serializable, DDMContent> ddmContents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ddmContents.size());
		Assert.assertEquals(newDDMContent1,
			ddmContents.get(newDDMContent1.getPrimaryKey()));
		Assert.assertEquals(newDDMContent2,
			ddmContents.get(newDDMContent2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DDMContent> ddmContents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ddmContents.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		DDMContent newDDMContent = addDDMContent();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMContent.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DDMContent> ddmContents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ddmContents.size());
		Assert.assertEquals(newDDMContent,
			ddmContents.get(newDDMContent.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DDMContent> ddmContents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ddmContents.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		DDMContent newDDMContent = addDDMContent();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMContent.getPrimaryKey());

		Map<Serializable, DDMContent> ddmContents = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ddmContents.size());
		Assert.assertEquals(newDDMContent,
			ddmContents.get(newDDMContent.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DDMContentLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DDMContent>() {
				@Override
				public void performAction(DDMContent ddmContent) {
					Assert.assertNotNull(ddmContent);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DDMContent newDDMContent = addDDMContent();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDMContent.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("contentId",
				newDDMContent.getContentId()));

		List<DDMContent> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DDMContent existingDDMContent = result.get(0);

		Assert.assertEquals(existingDDMContent, newDDMContent);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDMContent.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("contentId",
				RandomTestUtil.nextLong()));

		List<DDMContent> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DDMContent newDDMContent = addDDMContent();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDMContent.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("contentId"));

		Object newContentId = newDDMContent.getContentId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("contentId",
				new Object[] { newContentId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingContentId = result.get(0);

		Assert.assertEquals(existingContentId, newContentId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDMContent.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("contentId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("contentId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		DDMContent newDDMContent = addDDMContent();

		_persistence.clearCache();

		DDMContent existingDDMContent = _persistence.findByPrimaryKey(newDDMContent.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingDDMContent.getUuid(),
				ReflectionTestUtil.invoke(existingDDMContent,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingDDMContent.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingDDMContent,
				"getOriginalGroupId", new Class<?>[0]));
	}

	protected DDMContent addDDMContent() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMContent ddmContent = _persistence.create(pk);

		ddmContent.setUuid(RandomTestUtil.randomString());

		ddmContent.setGroupId(RandomTestUtil.nextLong());

		ddmContent.setCompanyId(RandomTestUtil.nextLong());

		ddmContent.setUserId(RandomTestUtil.nextLong());

		ddmContent.setUserName(RandomTestUtil.randomString());

		ddmContent.setCreateDate(RandomTestUtil.nextDate());

		ddmContent.setModifiedDate(RandomTestUtil.nextDate());

		ddmContent.setName(RandomTestUtil.randomString());

		ddmContent.setDescription(RandomTestUtil.randomString());

		ddmContent.setData(RandomTestUtil.randomString());

		_ddmContents.add(_persistence.update(ddmContent));

		return ddmContent;
	}

	private List<DDMContent> _ddmContents = new ArrayList<DDMContent>();
	private DDMContentPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}