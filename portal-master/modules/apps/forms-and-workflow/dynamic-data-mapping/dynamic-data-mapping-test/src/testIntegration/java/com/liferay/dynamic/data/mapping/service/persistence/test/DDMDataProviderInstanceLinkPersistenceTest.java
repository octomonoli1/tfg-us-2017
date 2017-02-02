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

import com.liferay.dynamic.data.mapping.exception.NoSuchDataProviderInstanceLinkException;
import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceLink;
import com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLinkLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstanceLinkPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstanceLinkUtil;

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
public class DDMDataProviderInstanceLinkPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = DDMDataProviderInstanceLinkUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DDMDataProviderInstanceLink> iterator = _ddmDataProviderInstanceLinks.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMDataProviderInstanceLink ddmDataProviderInstanceLink = _persistence.create(pk);

		Assert.assertNotNull(ddmDataProviderInstanceLink);

		Assert.assertEquals(ddmDataProviderInstanceLink.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DDMDataProviderInstanceLink newDDMDataProviderInstanceLink = addDDMDataProviderInstanceLink();

		_persistence.remove(newDDMDataProviderInstanceLink);

		DDMDataProviderInstanceLink existingDDMDataProviderInstanceLink = _persistence.fetchByPrimaryKey(newDDMDataProviderInstanceLink.getPrimaryKey());

		Assert.assertNull(existingDDMDataProviderInstanceLink);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDDMDataProviderInstanceLink();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMDataProviderInstanceLink newDDMDataProviderInstanceLink = _persistence.create(pk);

		newDDMDataProviderInstanceLink.setCompanyId(RandomTestUtil.nextLong());

		newDDMDataProviderInstanceLink.setDataProviderInstanceId(RandomTestUtil.nextLong());

		newDDMDataProviderInstanceLink.setStructureId(RandomTestUtil.nextLong());

		_ddmDataProviderInstanceLinks.add(_persistence.update(
				newDDMDataProviderInstanceLink));

		DDMDataProviderInstanceLink existingDDMDataProviderInstanceLink = _persistence.findByPrimaryKey(newDDMDataProviderInstanceLink.getPrimaryKey());

		Assert.assertEquals(existingDDMDataProviderInstanceLink.getDataProviderInstanceLinkId(),
			newDDMDataProviderInstanceLink.getDataProviderInstanceLinkId());
		Assert.assertEquals(existingDDMDataProviderInstanceLink.getCompanyId(),
			newDDMDataProviderInstanceLink.getCompanyId());
		Assert.assertEquals(existingDDMDataProviderInstanceLink.getDataProviderInstanceId(),
			newDDMDataProviderInstanceLink.getDataProviderInstanceId());
		Assert.assertEquals(existingDDMDataProviderInstanceLink.getStructureId(),
			newDDMDataProviderInstanceLink.getStructureId());
	}

	@Test
	public void testCountByDataProviderInstanceId() throws Exception {
		_persistence.countByDataProviderInstanceId(RandomTestUtil.nextLong());

		_persistence.countByDataProviderInstanceId(0L);
	}

	@Test
	public void testCountByStructureId() throws Exception {
		_persistence.countByStructureId(RandomTestUtil.nextLong());

		_persistence.countByStructureId(0L);
	}

	@Test
	public void testCountByD_S() throws Exception {
		_persistence.countByD_S(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByD_S(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DDMDataProviderInstanceLink newDDMDataProviderInstanceLink = addDDMDataProviderInstanceLink();

		DDMDataProviderInstanceLink existingDDMDataProviderInstanceLink = _persistence.findByPrimaryKey(newDDMDataProviderInstanceLink.getPrimaryKey());

		Assert.assertEquals(existingDDMDataProviderInstanceLink,
			newDDMDataProviderInstanceLink);
	}

	@Test(expected = NoSuchDataProviderInstanceLinkException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<DDMDataProviderInstanceLink> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("DDMDataProviderInstanceLink",
			"dataProviderInstanceLinkId", true, "companyId", true,
			"dataProviderInstanceId", true, "structureId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DDMDataProviderInstanceLink newDDMDataProviderInstanceLink = addDDMDataProviderInstanceLink();

		DDMDataProviderInstanceLink existingDDMDataProviderInstanceLink = _persistence.fetchByPrimaryKey(newDDMDataProviderInstanceLink.getPrimaryKey());

		Assert.assertEquals(existingDDMDataProviderInstanceLink,
			newDDMDataProviderInstanceLink);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMDataProviderInstanceLink missingDDMDataProviderInstanceLink = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDDMDataProviderInstanceLink);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		DDMDataProviderInstanceLink newDDMDataProviderInstanceLink1 = addDDMDataProviderInstanceLink();
		DDMDataProviderInstanceLink newDDMDataProviderInstanceLink2 = addDDMDataProviderInstanceLink();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMDataProviderInstanceLink1.getPrimaryKey());
		primaryKeys.add(newDDMDataProviderInstanceLink2.getPrimaryKey());

		Map<Serializable, DDMDataProviderInstanceLink> ddmDataProviderInstanceLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ddmDataProviderInstanceLinks.size());
		Assert.assertEquals(newDDMDataProviderInstanceLink1,
			ddmDataProviderInstanceLinks.get(
				newDDMDataProviderInstanceLink1.getPrimaryKey()));
		Assert.assertEquals(newDDMDataProviderInstanceLink2,
			ddmDataProviderInstanceLinks.get(
				newDDMDataProviderInstanceLink2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DDMDataProviderInstanceLink> ddmDataProviderInstanceLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ddmDataProviderInstanceLinks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		DDMDataProviderInstanceLink newDDMDataProviderInstanceLink = addDDMDataProviderInstanceLink();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMDataProviderInstanceLink.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DDMDataProviderInstanceLink> ddmDataProviderInstanceLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ddmDataProviderInstanceLinks.size());
		Assert.assertEquals(newDDMDataProviderInstanceLink,
			ddmDataProviderInstanceLinks.get(
				newDDMDataProviderInstanceLink.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DDMDataProviderInstanceLink> ddmDataProviderInstanceLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ddmDataProviderInstanceLinks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		DDMDataProviderInstanceLink newDDMDataProviderInstanceLink = addDDMDataProviderInstanceLink();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMDataProviderInstanceLink.getPrimaryKey());

		Map<Serializable, DDMDataProviderInstanceLink> ddmDataProviderInstanceLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ddmDataProviderInstanceLinks.size());
		Assert.assertEquals(newDDMDataProviderInstanceLink,
			ddmDataProviderInstanceLinks.get(
				newDDMDataProviderInstanceLink.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DDMDataProviderInstanceLinkLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DDMDataProviderInstanceLink>() {
				@Override
				public void performAction(
					DDMDataProviderInstanceLink ddmDataProviderInstanceLink) {
					Assert.assertNotNull(ddmDataProviderInstanceLink);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DDMDataProviderInstanceLink newDDMDataProviderInstanceLink = addDDMDataProviderInstanceLink();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDMDataProviderInstanceLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"dataProviderInstanceLinkId",
				newDDMDataProviderInstanceLink.getDataProviderInstanceLinkId()));

		List<DDMDataProviderInstanceLink> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DDMDataProviderInstanceLink existingDDMDataProviderInstanceLink = result.get(0);

		Assert.assertEquals(existingDDMDataProviderInstanceLink,
			newDDMDataProviderInstanceLink);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDMDataProviderInstanceLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"dataProviderInstanceLinkId", RandomTestUtil.nextLong()));

		List<DDMDataProviderInstanceLink> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DDMDataProviderInstanceLink newDDMDataProviderInstanceLink = addDDMDataProviderInstanceLink();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDMDataProviderInstanceLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"dataProviderInstanceLinkId"));

		Object newDataProviderInstanceLinkId = newDDMDataProviderInstanceLink.getDataProviderInstanceLinkId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"dataProviderInstanceLinkId",
				new Object[] { newDataProviderInstanceLinkId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDataProviderInstanceLinkId = result.get(0);

		Assert.assertEquals(existingDataProviderInstanceLinkId,
			newDataProviderInstanceLinkId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDMDataProviderInstanceLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"dataProviderInstanceLinkId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"dataProviderInstanceLinkId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		DDMDataProviderInstanceLink newDDMDataProviderInstanceLink = addDDMDataProviderInstanceLink();

		_persistence.clearCache();

		DDMDataProviderInstanceLink existingDDMDataProviderInstanceLink = _persistence.findByPrimaryKey(newDDMDataProviderInstanceLink.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingDDMDataProviderInstanceLink.getDataProviderInstanceId()),
			ReflectionTestUtil.<Long>invoke(
				existingDDMDataProviderInstanceLink,
				"getOriginalDataProviderInstanceId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingDDMDataProviderInstanceLink.getStructureId()),
			ReflectionTestUtil.<Long>invoke(
				existingDDMDataProviderInstanceLink, "getOriginalStructureId",
				new Class<?>[0]));
	}

	protected DDMDataProviderInstanceLink addDDMDataProviderInstanceLink()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMDataProviderInstanceLink ddmDataProviderInstanceLink = _persistence.create(pk);

		ddmDataProviderInstanceLink.setCompanyId(RandomTestUtil.nextLong());

		ddmDataProviderInstanceLink.setDataProviderInstanceId(RandomTestUtil.nextLong());

		ddmDataProviderInstanceLink.setStructureId(RandomTestUtil.nextLong());

		_ddmDataProviderInstanceLinks.add(_persistence.update(
				ddmDataProviderInstanceLink));

		return ddmDataProviderInstanceLink;
	}

	private List<DDMDataProviderInstanceLink> _ddmDataProviderInstanceLinks = new ArrayList<DDMDataProviderInstanceLink>();
	private DDMDataProviderInstanceLinkPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}