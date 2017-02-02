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

import com.liferay.dynamic.data.mapping.exception.NoSuchStructureVersionException;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureVersionPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureVersionUtil;

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
public class DDMStructureVersionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = DDMStructureVersionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DDMStructureVersion> iterator = _ddmStructureVersions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMStructureVersion ddmStructureVersion = _persistence.create(pk);

		Assert.assertNotNull(ddmStructureVersion);

		Assert.assertEquals(ddmStructureVersion.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DDMStructureVersion newDDMStructureVersion = addDDMStructureVersion();

		_persistence.remove(newDDMStructureVersion);

		DDMStructureVersion existingDDMStructureVersion = _persistence.fetchByPrimaryKey(newDDMStructureVersion.getPrimaryKey());

		Assert.assertNull(existingDDMStructureVersion);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDDMStructureVersion();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMStructureVersion newDDMStructureVersion = _persistence.create(pk);

		newDDMStructureVersion.setGroupId(RandomTestUtil.nextLong());

		newDDMStructureVersion.setCompanyId(RandomTestUtil.nextLong());

		newDDMStructureVersion.setUserId(RandomTestUtil.nextLong());

		newDDMStructureVersion.setUserName(RandomTestUtil.randomString());

		newDDMStructureVersion.setCreateDate(RandomTestUtil.nextDate());

		newDDMStructureVersion.setStructureId(RandomTestUtil.nextLong());

		newDDMStructureVersion.setVersion(RandomTestUtil.randomString());

		newDDMStructureVersion.setParentStructureId(RandomTestUtil.nextLong());

		newDDMStructureVersion.setName(RandomTestUtil.randomString());

		newDDMStructureVersion.setDescription(RandomTestUtil.randomString());

		newDDMStructureVersion.setDefinition(RandomTestUtil.randomString());

		newDDMStructureVersion.setStorageType(RandomTestUtil.randomString());

		newDDMStructureVersion.setType(RandomTestUtil.nextInt());

		newDDMStructureVersion.setStatus(RandomTestUtil.nextInt());

		newDDMStructureVersion.setStatusByUserId(RandomTestUtil.nextLong());

		newDDMStructureVersion.setStatusByUserName(RandomTestUtil.randomString());

		newDDMStructureVersion.setStatusDate(RandomTestUtil.nextDate());

		_ddmStructureVersions.add(_persistence.update(newDDMStructureVersion));

		DDMStructureVersion existingDDMStructureVersion = _persistence.findByPrimaryKey(newDDMStructureVersion.getPrimaryKey());

		Assert.assertEquals(existingDDMStructureVersion.getStructureVersionId(),
			newDDMStructureVersion.getStructureVersionId());
		Assert.assertEquals(existingDDMStructureVersion.getGroupId(),
			newDDMStructureVersion.getGroupId());
		Assert.assertEquals(existingDDMStructureVersion.getCompanyId(),
			newDDMStructureVersion.getCompanyId());
		Assert.assertEquals(existingDDMStructureVersion.getUserId(),
			newDDMStructureVersion.getUserId());
		Assert.assertEquals(existingDDMStructureVersion.getUserName(),
			newDDMStructureVersion.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDDMStructureVersion.getCreateDate()),
			Time.getShortTimestamp(newDDMStructureVersion.getCreateDate()));
		Assert.assertEquals(existingDDMStructureVersion.getStructureId(),
			newDDMStructureVersion.getStructureId());
		Assert.assertEquals(existingDDMStructureVersion.getVersion(),
			newDDMStructureVersion.getVersion());
		Assert.assertEquals(existingDDMStructureVersion.getParentStructureId(),
			newDDMStructureVersion.getParentStructureId());
		Assert.assertEquals(existingDDMStructureVersion.getName(),
			newDDMStructureVersion.getName());
		Assert.assertEquals(existingDDMStructureVersion.getDescription(),
			newDDMStructureVersion.getDescription());
		Assert.assertEquals(existingDDMStructureVersion.getDefinition(),
			newDDMStructureVersion.getDefinition());
		Assert.assertEquals(existingDDMStructureVersion.getStorageType(),
			newDDMStructureVersion.getStorageType());
		Assert.assertEquals(existingDDMStructureVersion.getType(),
			newDDMStructureVersion.getType());
		Assert.assertEquals(existingDDMStructureVersion.getStatus(),
			newDDMStructureVersion.getStatus());
		Assert.assertEquals(existingDDMStructureVersion.getStatusByUserId(),
			newDDMStructureVersion.getStatusByUserId());
		Assert.assertEquals(existingDDMStructureVersion.getStatusByUserName(),
			newDDMStructureVersion.getStatusByUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDDMStructureVersion.getStatusDate()),
			Time.getShortTimestamp(newDDMStructureVersion.getStatusDate()));
	}

	@Test
	public void testCountByStructureId() throws Exception {
		_persistence.countByStructureId(RandomTestUtil.nextLong());

		_persistence.countByStructureId(0L);
	}

	@Test
	public void testCountByS_V() throws Exception {
		_persistence.countByS_V(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByS_V(0L, StringPool.NULL);

		_persistence.countByS_V(0L, (String)null);
	}

	@Test
	public void testCountByS_S() throws Exception {
		_persistence.countByS_S(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByS_S(0L, 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DDMStructureVersion newDDMStructureVersion = addDDMStructureVersion();

		DDMStructureVersion existingDDMStructureVersion = _persistence.findByPrimaryKey(newDDMStructureVersion.getPrimaryKey());

		Assert.assertEquals(existingDDMStructureVersion, newDDMStructureVersion);
	}

	@Test(expected = NoSuchStructureVersionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<DDMStructureVersion> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("DDMStructureVersion",
			"structureVersionId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"structureId", true, "version", true, "parentStructureId", true,
			"name", true, "storageType", true, "type", true, "status", true,
			"statusByUserId", true, "statusByUserName", true, "statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DDMStructureVersion newDDMStructureVersion = addDDMStructureVersion();

		DDMStructureVersion existingDDMStructureVersion = _persistence.fetchByPrimaryKey(newDDMStructureVersion.getPrimaryKey());

		Assert.assertEquals(existingDDMStructureVersion, newDDMStructureVersion);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMStructureVersion missingDDMStructureVersion = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDDMStructureVersion);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		DDMStructureVersion newDDMStructureVersion1 = addDDMStructureVersion();
		DDMStructureVersion newDDMStructureVersion2 = addDDMStructureVersion();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMStructureVersion1.getPrimaryKey());
		primaryKeys.add(newDDMStructureVersion2.getPrimaryKey());

		Map<Serializable, DDMStructureVersion> ddmStructureVersions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ddmStructureVersions.size());
		Assert.assertEquals(newDDMStructureVersion1,
			ddmStructureVersions.get(newDDMStructureVersion1.getPrimaryKey()));
		Assert.assertEquals(newDDMStructureVersion2,
			ddmStructureVersions.get(newDDMStructureVersion2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DDMStructureVersion> ddmStructureVersions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ddmStructureVersions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		DDMStructureVersion newDDMStructureVersion = addDDMStructureVersion();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMStructureVersion.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DDMStructureVersion> ddmStructureVersions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ddmStructureVersions.size());
		Assert.assertEquals(newDDMStructureVersion,
			ddmStructureVersions.get(newDDMStructureVersion.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DDMStructureVersion> ddmStructureVersions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ddmStructureVersions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		DDMStructureVersion newDDMStructureVersion = addDDMStructureVersion();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMStructureVersion.getPrimaryKey());

		Map<Serializable, DDMStructureVersion> ddmStructureVersions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ddmStructureVersions.size());
		Assert.assertEquals(newDDMStructureVersion,
			ddmStructureVersions.get(newDDMStructureVersion.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DDMStructureVersionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DDMStructureVersion>() {
				@Override
				public void performAction(
					DDMStructureVersion ddmStructureVersion) {
					Assert.assertNotNull(ddmStructureVersion);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DDMStructureVersion newDDMStructureVersion = addDDMStructureVersion();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDMStructureVersion.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("structureVersionId",
				newDDMStructureVersion.getStructureVersionId()));

		List<DDMStructureVersion> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DDMStructureVersion existingDDMStructureVersion = result.get(0);

		Assert.assertEquals(existingDDMStructureVersion, newDDMStructureVersion);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDMStructureVersion.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("structureVersionId",
				RandomTestUtil.nextLong()));

		List<DDMStructureVersion> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DDMStructureVersion newDDMStructureVersion = addDDMStructureVersion();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDMStructureVersion.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"structureVersionId"));

		Object newStructureVersionId = newDDMStructureVersion.getStructureVersionId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("structureVersionId",
				new Object[] { newStructureVersionId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingStructureVersionId = result.get(0);

		Assert.assertEquals(existingStructureVersionId, newStructureVersionId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDMStructureVersion.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"structureVersionId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("structureVersionId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		DDMStructureVersion newDDMStructureVersion = addDDMStructureVersion();

		_persistence.clearCache();

		DDMStructureVersion existingDDMStructureVersion = _persistence.findByPrimaryKey(newDDMStructureVersion.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingDDMStructureVersion.getStructureId()),
			ReflectionTestUtil.<Long>invoke(existingDDMStructureVersion,
				"getOriginalStructureId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(
				existingDDMStructureVersion.getVersion(),
				ReflectionTestUtil.invoke(existingDDMStructureVersion,
					"getOriginalVersion", new Class<?>[0])));
	}

	protected DDMStructureVersion addDDMStructureVersion()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMStructureVersion ddmStructureVersion = _persistence.create(pk);

		ddmStructureVersion.setGroupId(RandomTestUtil.nextLong());

		ddmStructureVersion.setCompanyId(RandomTestUtil.nextLong());

		ddmStructureVersion.setUserId(RandomTestUtil.nextLong());

		ddmStructureVersion.setUserName(RandomTestUtil.randomString());

		ddmStructureVersion.setCreateDate(RandomTestUtil.nextDate());

		ddmStructureVersion.setStructureId(RandomTestUtil.nextLong());

		ddmStructureVersion.setVersion(RandomTestUtil.randomString());

		ddmStructureVersion.setParentStructureId(RandomTestUtil.nextLong());

		ddmStructureVersion.setName(RandomTestUtil.randomString());

		ddmStructureVersion.setDescription(RandomTestUtil.randomString());

		ddmStructureVersion.setDefinition(RandomTestUtil.randomString());

		ddmStructureVersion.setStorageType(RandomTestUtil.randomString());

		ddmStructureVersion.setType(RandomTestUtil.nextInt());

		ddmStructureVersion.setStatus(RandomTestUtil.nextInt());

		ddmStructureVersion.setStatusByUserId(RandomTestUtil.nextLong());

		ddmStructureVersion.setStatusByUserName(RandomTestUtil.randomString());

		ddmStructureVersion.setStatusDate(RandomTestUtil.nextDate());

		_ddmStructureVersions.add(_persistence.update(ddmStructureVersion));

		return ddmStructureVersion;
	}

	private List<DDMStructureVersion> _ddmStructureVersions = new ArrayList<DDMStructureVersion>();
	private DDMStructureVersionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}