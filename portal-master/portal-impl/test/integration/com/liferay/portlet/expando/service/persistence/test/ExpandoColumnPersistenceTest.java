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

package com.liferay.portlet.expando.service.persistence.test;

import com.liferay.expando.kernel.exception.NoSuchColumnException;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.persistence.ExpandoColumnPersistence;
import com.liferay.expando.kernel.service.persistence.ExpandoColumnUtil;

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
public class ExpandoColumnPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = ExpandoColumnUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ExpandoColumn> iterator = _expandoColumns.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ExpandoColumn expandoColumn = _persistence.create(pk);

		Assert.assertNotNull(expandoColumn);

		Assert.assertEquals(expandoColumn.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ExpandoColumn newExpandoColumn = addExpandoColumn();

		_persistence.remove(newExpandoColumn);

		ExpandoColumn existingExpandoColumn = _persistence.fetchByPrimaryKey(newExpandoColumn.getPrimaryKey());

		Assert.assertNull(existingExpandoColumn);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addExpandoColumn();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ExpandoColumn newExpandoColumn = _persistence.create(pk);

		newExpandoColumn.setCompanyId(RandomTestUtil.nextLong());

		newExpandoColumn.setTableId(RandomTestUtil.nextLong());

		newExpandoColumn.setName(RandomTestUtil.randomString());

		newExpandoColumn.setType(RandomTestUtil.nextInt());

		newExpandoColumn.setDefaultData(RandomTestUtil.randomString());

		newExpandoColumn.setTypeSettings(RandomTestUtil.randomString());

		_expandoColumns.add(_persistence.update(newExpandoColumn));

		ExpandoColumn existingExpandoColumn = _persistence.findByPrimaryKey(newExpandoColumn.getPrimaryKey());

		Assert.assertEquals(existingExpandoColumn.getColumnId(),
			newExpandoColumn.getColumnId());
		Assert.assertEquals(existingExpandoColumn.getCompanyId(),
			newExpandoColumn.getCompanyId());
		Assert.assertEquals(existingExpandoColumn.getTableId(),
			newExpandoColumn.getTableId());
		Assert.assertEquals(existingExpandoColumn.getName(),
			newExpandoColumn.getName());
		Assert.assertEquals(existingExpandoColumn.getType(),
			newExpandoColumn.getType());
		Assert.assertEquals(existingExpandoColumn.getDefaultData(),
			newExpandoColumn.getDefaultData());
		Assert.assertEquals(existingExpandoColumn.getTypeSettings(),
			newExpandoColumn.getTypeSettings());
	}

	@Test
	public void testCountByTableId() throws Exception {
		_persistence.countByTableId(RandomTestUtil.nextLong());

		_persistence.countByTableId(0L);
	}

	@Test
	public void testCountByT_N() throws Exception {
		_persistence.countByT_N(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByT_N(0L, StringPool.NULL);

		_persistence.countByT_N(0L, (String)null);
	}

	@Test
	public void testCountByT_NArrayable() throws Exception {
		_persistence.countByT_N(RandomTestUtil.nextLong(),
			new String[] {
				RandomTestUtil.randomString(), StringPool.BLANK, StringPool.NULL,
				null, null
			});
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ExpandoColumn newExpandoColumn = addExpandoColumn();

		ExpandoColumn existingExpandoColumn = _persistence.findByPrimaryKey(newExpandoColumn.getPrimaryKey());

		Assert.assertEquals(existingExpandoColumn, newExpandoColumn);
	}

	@Test(expected = NoSuchColumnException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ExpandoColumn> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ExpandoColumn", "columnId",
			true, "companyId", true, "tableId", true, "name", true, "type", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ExpandoColumn newExpandoColumn = addExpandoColumn();

		ExpandoColumn existingExpandoColumn = _persistence.fetchByPrimaryKey(newExpandoColumn.getPrimaryKey());

		Assert.assertEquals(existingExpandoColumn, newExpandoColumn);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ExpandoColumn missingExpandoColumn = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingExpandoColumn);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ExpandoColumn newExpandoColumn1 = addExpandoColumn();
		ExpandoColumn newExpandoColumn2 = addExpandoColumn();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newExpandoColumn1.getPrimaryKey());
		primaryKeys.add(newExpandoColumn2.getPrimaryKey());

		Map<Serializable, ExpandoColumn> expandoColumns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, expandoColumns.size());
		Assert.assertEquals(newExpandoColumn1,
			expandoColumns.get(newExpandoColumn1.getPrimaryKey()));
		Assert.assertEquals(newExpandoColumn2,
			expandoColumns.get(newExpandoColumn2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ExpandoColumn> expandoColumns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(expandoColumns.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ExpandoColumn newExpandoColumn = addExpandoColumn();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newExpandoColumn.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ExpandoColumn> expandoColumns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, expandoColumns.size());
		Assert.assertEquals(newExpandoColumn,
			expandoColumns.get(newExpandoColumn.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ExpandoColumn> expandoColumns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(expandoColumns.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ExpandoColumn newExpandoColumn = addExpandoColumn();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newExpandoColumn.getPrimaryKey());

		Map<Serializable, ExpandoColumn> expandoColumns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, expandoColumns.size());
		Assert.assertEquals(newExpandoColumn,
			expandoColumns.get(newExpandoColumn.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ExpandoColumnLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ExpandoColumn>() {
				@Override
				public void performAction(ExpandoColumn expandoColumn) {
					Assert.assertNotNull(expandoColumn);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ExpandoColumn newExpandoColumn = addExpandoColumn();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpandoColumn.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("columnId",
				newExpandoColumn.getColumnId()));

		List<ExpandoColumn> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ExpandoColumn existingExpandoColumn = result.get(0);

		Assert.assertEquals(existingExpandoColumn, newExpandoColumn);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpandoColumn.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("columnId",
				RandomTestUtil.nextLong()));

		List<ExpandoColumn> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ExpandoColumn newExpandoColumn = addExpandoColumn();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpandoColumn.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("columnId"));

		Object newColumnId = newExpandoColumn.getColumnId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("columnId",
				new Object[] { newColumnId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingColumnId = result.get(0);

		Assert.assertEquals(existingColumnId, newColumnId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpandoColumn.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("columnId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("columnId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		ExpandoColumn newExpandoColumn = addExpandoColumn();

		_persistence.clearCache();

		ExpandoColumn existingExpandoColumn = _persistence.findByPrimaryKey(newExpandoColumn.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingExpandoColumn.getTableId()),
			ReflectionTestUtil.<Long>invoke(existingExpandoColumn,
				"getOriginalTableId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingExpandoColumn.getName(),
				ReflectionTestUtil.invoke(existingExpandoColumn,
					"getOriginalName", new Class<?>[0])));
	}

	protected ExpandoColumn addExpandoColumn() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ExpandoColumn expandoColumn = _persistence.create(pk);

		expandoColumn.setCompanyId(RandomTestUtil.nextLong());

		expandoColumn.setTableId(RandomTestUtil.nextLong());

		expandoColumn.setName(RandomTestUtil.randomString());

		expandoColumn.setType(RandomTestUtil.nextInt());

		expandoColumn.setDefaultData(RandomTestUtil.randomString());

		expandoColumn.setTypeSettings(RandomTestUtil.randomString());

		_expandoColumns.add(_persistence.update(expandoColumn));

		return expandoColumn;
	}

	private List<ExpandoColumn> _expandoColumns = new ArrayList<ExpandoColumn>();
	private ExpandoColumnPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}