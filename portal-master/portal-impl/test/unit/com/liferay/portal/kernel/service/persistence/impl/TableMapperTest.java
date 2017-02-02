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

package com.liferay.portal.kernel.service.persistence.impl;

import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactory;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.ParamSetter;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactory;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.tools.ToolDependencies;
import com.liferay.portal.util.PropsImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class TableMapperTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor() {

			@Override
			public void appendAssertClasses(List<Class<?>> assertClasses) {
				assertClasses.clear();

				assertClasses.add(ReverseTableMapper.class);
				assertClasses.add(TableMapperFactory.class);
				assertClasses.add(TableMapperImpl.class);
			}

		};

	@BeforeClass
	public static void setUpClass() {
		ToolDependencies.wireCaches();
	}

	@Before
	public void setUp() {
		MultiVMPoolUtil.clear();

		MappingSqlQueryFactoryUtil mappingSqlQueryFactoryUtil =
			new MappingSqlQueryFactoryUtil();

		mappingSqlQueryFactoryUtil.setMappingSqlQueryFactory(
			new MockMappingSqlQueryFactory());

		PropsUtil.setProps(new PropsImpl());

		SqlUpdateFactoryUtil sqlUpdateFactoryUtil = new SqlUpdateFactoryUtil();

		sqlUpdateFactoryUtil.setSqlUpdateFactory(new MockSqlUpdateFactory());

		Class<?> clazz = TableMapperTest.class;

		ClassLoader classLoader = clazz.getClassLoader();

		_dataSource = (DataSource)ProxyUtil.newProxyInstance(
			classLoader, new Class<?>[] {DataSource.class},
			new InvocationHandler() {

				@Override
				public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {

					throw new UnsupportedOperationException();
				}

			});

		_leftBasePersistence = new MockBasePersistence<>(Left.class);

		_leftBasePersistence.setDataSource(_dataSource);

		_rightBasePersistence = new MockBasePersistence<>(Right.class);

		_rightBasePersistence.setDataSource(_dataSource);

		_tableMapperImpl = new TableMapperImpl<Left, Right>(
			_TABLE_NAME, _COMPANY_COLUMN_NAME, _LEFT_COLUMN_NAME,
			_RIGHT_COLUMN_NAME, _leftBasePersistence, _rightBasePersistence);
	}

	@Test
	public void testAddTableMapping() {

		// Success, no model listener

		long companyId = 0;
		long leftPrimaryKey = 1;
		long rightPrimaryKey = 2;

		Assert.assertTrue(
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey, rightPrimaryKey));

		// Fail, no model listener

		Assert.assertFalse(
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey, rightPrimaryKey));

		// Error, no model listener

		PortalCache<Long, long[]> leftToRightPortalCache =
			_tableMapperImpl.leftToRightPortalCache;

		leftToRightPortalCache.put(leftPrimaryKey, new long[0]);

		try {
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey, rightPrimaryKey);

			Assert.fail();
		}
		catch (SystemException se) {
			Throwable cause = se.getCause();

			Assert.assertSame(RuntimeException.class, cause.getClass());
			Assert.assertEquals(
				"Unique key violation for left primary key " + leftPrimaryKey +
					" and right primary key " + rightPrimaryKey,
				cause.getMessage());
		}

		// Auto recover after error

		Assert.assertFalse(
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey, rightPrimaryKey));

		// Success, with model listener

		leftToRightPortalCache.remove(leftPrimaryKey);

		_mappingStore.remove(leftPrimaryKey);

		RecorderModelListener<Left> leftModelListener =
			new RecorderModelListener<>();

		_leftBasePersistence.registerListener(leftModelListener);

		RecorderModelListener<Right> rightModelListener =
			new RecorderModelListener<>();

		_rightBasePersistence.registerListener(rightModelListener);

		Assert.assertTrue(
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey, rightPrimaryKey));

		leftModelListener.assertOnBeforeAddAssociation(
			true, leftPrimaryKey, Right.class.getName(), rightPrimaryKey);

		rightModelListener.assertOnBeforeAddAssociation(
			true, rightPrimaryKey, Left.class.getName(), leftPrimaryKey);

		leftModelListener.assertOnAfterAddAssociation(
			true, leftPrimaryKey, Right.class.getName(), rightPrimaryKey);

		rightModelListener.assertOnAfterAddAssociation(
			true, rightPrimaryKey, Left.class.getName(), leftPrimaryKey);

		_leftBasePersistence.unregisterListener(leftModelListener);
		_rightBasePersistence.unregisterListener(rightModelListener);

		// Error, no model listener

		leftToRightPortalCache.put(leftPrimaryKey, new long[0]);

		leftModelListener = new RecorderModelListener<>();

		_leftBasePersistence.registerListener(leftModelListener);

		rightModelListener = new RecorderModelListener<>();

		_rightBasePersistence.registerListener(rightModelListener);

		try {
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey, rightPrimaryKey);

			Assert.fail();
		}
		catch (SystemException se) {
			Throwable cause = se.getCause();

			Assert.assertSame(RuntimeException.class, cause.getClass());
			Assert.assertEquals(
				"Unique key violation for left primary key " + leftPrimaryKey +
					" and right primary key " + rightPrimaryKey,
				cause.getMessage());
		}

		leftModelListener.assertOnBeforeAddAssociation(
			true, leftPrimaryKey, Right.class.getName(), rightPrimaryKey);

		rightModelListener.assertOnBeforeAddAssociation(
			true, rightPrimaryKey, Left.class.getName(), leftPrimaryKey);

		leftModelListener.assertOnAfterAddAssociation(false, null, null, null);

		rightModelListener.assertOnAfterAddAssociation(false, null, null, null);
	}

	@Test
	public void testAddTableMappings() {
		long companyId = 0;
		long leftPrimaryKey1 = 1;
		long leftPrimaryKey2 = 2;
		long rightPrimaryKey1 = 3;
		long rightPrimaryKey2 = 4;

		Assert.assertArrayEquals(
			new long[] {rightPrimaryKey1},
			_tableMapperImpl.addTableMappings(
				companyId, leftPrimaryKey1, new long[] {rightPrimaryKey1}));
		Assert.assertArrayEquals(
			new long[0],
			_tableMapperImpl.addTableMappings(
				companyId, leftPrimaryKey1, new long[] {rightPrimaryKey1}));
		Assert.assertArrayEquals(
			new long[] {rightPrimaryKey2},
			_tableMapperImpl.addTableMappings(
				companyId, leftPrimaryKey1,
				new long[] {rightPrimaryKey1, rightPrimaryKey2}));
		Assert.assertEquals(
			2,
			_tableMapperImpl.deleteLeftPrimaryKeyTableMappings(
				leftPrimaryKey1));
		Assert.assertArrayEquals(
			new long[] {rightPrimaryKey1, rightPrimaryKey2},
			_tableMapperImpl.addTableMappings(
				companyId, leftPrimaryKey1,
				new long[] {rightPrimaryKey1, rightPrimaryKey2}));
		Assert.assertEquals(
			2,
			_tableMapperImpl.deleteLeftPrimaryKeyTableMappings(
				leftPrimaryKey1));
		Assert.assertArrayEquals(
			new long[] {leftPrimaryKey1},
			_tableMapperImpl.addTableMappings(
				companyId, new long[] {leftPrimaryKey1}, rightPrimaryKey1));
		Assert.assertArrayEquals(
			new long[0],
			_tableMapperImpl.addTableMappings(
				companyId, new long[] {leftPrimaryKey1}, rightPrimaryKey1));
		Assert.assertArrayEquals(
			new long[] {leftPrimaryKey2},
			_tableMapperImpl.addTableMappings(
				companyId, new long[] {leftPrimaryKey1, leftPrimaryKey2},
				rightPrimaryKey1));
		Assert.assertEquals(
			2,
			_tableMapperImpl.deleteRightPrimaryKeyTableMappings(
				rightPrimaryKey1));
		Assert.assertArrayEquals(
			new long[] {leftPrimaryKey1, leftPrimaryKey2},
			_tableMapperImpl.addTableMappings(
				companyId, new long[] {leftPrimaryKey1, leftPrimaryKey2},
				rightPrimaryKey1));
		Assert.assertEquals(
			2,
			_tableMapperImpl.deleteRightPrimaryKeyTableMappings(
				rightPrimaryKey1));
	}

	@Test
	public void testConstructor() {
		new TableMapperFactory();

		Assert.assertTrue(
			_tableMapperImpl.addTableMappingSqlUpdate
				instanceof MockAddMappingSqlUpdate);
		Assert.assertTrue(
			_tableMapperImpl.deleteLeftPrimaryKeyTableMappingsSqlUpdate
				instanceof MockDeleteLeftPrimaryKeyTableMappingsSqlUpdate);
		Assert.assertTrue(
			_tableMapperImpl.deleteRightPrimaryKeyTableMappingsSqlUpdate
				instanceof MockDeleteRightPrimaryKeyTableMappingsSqlUpdate);
		Assert.assertTrue(
			_tableMapperImpl.deleteTableMappingSqlUpdate
				instanceof MockDeleteMappingSqlUpdate);
		Assert.assertTrue(
			_tableMapperImpl.getLeftPrimaryKeysSqlQuery
				instanceof MockGetLeftPrimaryKeysSqlQuery);
		Assert.assertTrue(
			_tableMapperImpl.getRightPrimaryKeysSqlQuery
				instanceof MockGetRightPrimaryKeysSqlQuery);
		Assert.assertSame(
			_leftBasePersistence, _tableMapperImpl.leftBasePersistence);
		Assert.assertEquals(_LEFT_COLUMN_NAME, _tableMapperImpl.leftColumnName);

		PortalCache<Long, long[]> leftToRightPortalCache =
			_tableMapperImpl.leftToRightPortalCache;

		Class<?> clazz = leftToRightPortalCache.getClass();

		Assert.assertEquals(
			"com.liferay.portal.tools.ToolDependencies$TestPortalCache",
			clazz.getName());

		Assert.assertEquals(
			TableMapper.class.getName() + "-" + _TABLE_NAME + "-LeftToRight",
			leftToRightPortalCache.getPortalCacheName());

		Assert.assertSame(
			_rightBasePersistence, _tableMapperImpl.rightBasePersistence);
		Assert.assertEquals(
			_RIGHT_COLUMN_NAME, _tableMapperImpl.rightColumnName);

		PortalCache<Long, long[]> rightToLeftPortalCache =
			_tableMapperImpl.rightToLeftPortalCache;

		clazz = rightToLeftPortalCache.getClass();

		Assert.assertEquals(
			"com.liferay.portal.tools.ToolDependencies$TestPortalCache",
			clazz.getName());

		Assert.assertEquals(
			TableMapper.class.getName() + "-" + _TABLE_NAME + "-RightToLeft",
			rightToLeftPortalCache.getPortalCacheName());
	}

	@Test
	public void testContainsTableMapping() {

		// Does not contain table mapping

		long leftPrimaryKey = 1;
		long rightPrimaryKey = 2;

		Assert.assertFalse(
			_tableMapperImpl.containsTableMapping(
				leftPrimaryKey, rightPrimaryKey));

		// Contains table mapping

		PortalCache<Long, long[]> leftToRightPortalCache =
			_tableMapperImpl.leftToRightPortalCache;

		leftToRightPortalCache.remove(leftPrimaryKey);

		_mappingStore.put(leftPrimaryKey, new long[] {rightPrimaryKey});

		Assert.assertTrue(
			_tableMapperImpl.containsTableMapping(
				leftPrimaryKey, rightPrimaryKey));
	}

	@Test
	public void testDeleteLeftPrimaryKeyTableMappings() {

		// Delete 0 entry

		long leftPrimaryKey = 1;

		Assert.assertEquals(
			0,
			_tableMapperImpl.deleteLeftPrimaryKeyTableMappings(leftPrimaryKey));

		// Delete 1 entry

		long rightPrimaryKey1 = 2;

		_mappingStore.put(leftPrimaryKey, new long[] {rightPrimaryKey1});

		Assert.assertEquals(
			1,
			_tableMapperImpl.deleteLeftPrimaryKeyTableMappings(leftPrimaryKey));

		// Delete 2 entries

		long rightPrimaryKey2 = 3;

		_mappingStore.put(
			leftPrimaryKey, new long[] {rightPrimaryKey1, rightPrimaryKey2});

		Assert.assertEquals(
			2,
			_tableMapperImpl.deleteLeftPrimaryKeyTableMappings(leftPrimaryKey));

		// Delete 0 entry, with left model listener

		RecorderModelListener<Left> leftModelListener =
			new RecorderModelListener<>();

		_leftBasePersistence.registerListener(leftModelListener);

		Assert.assertEquals(
			0,
			_tableMapperImpl.deleteLeftPrimaryKeyTableMappings(leftPrimaryKey));

		leftModelListener.assertOnBeforeRemoveAssociation(
			false, null, null, null);

		leftModelListener.assertOnAfterRemoveAssociation(
			false, null, null, null);

		_leftBasePersistence.unregisterListener(leftModelListener);

		// Delete 0 entry, with right model listener

		RecorderModelListener<Right> rightModelListener =
			new RecorderModelListener<>();

		_rightBasePersistence.registerListener(rightModelListener);

		Assert.assertEquals(
			0,
			_tableMapperImpl.deleteLeftPrimaryKeyTableMappings(leftPrimaryKey));

		rightModelListener.assertOnBeforeRemoveAssociation(
			false, null, null, null);

		rightModelListener.assertOnAfterRemoveAssociation(
			false, null, null, null);

		_rightBasePersistence.unregisterListener(rightModelListener);

		// Delete 1 entry, with left model listener

		leftModelListener = new RecorderModelListener<>();

		_leftBasePersistence.registerListener(leftModelListener);

		_mappingStore.put(leftPrimaryKey, new long[] {rightPrimaryKey1});

		Assert.assertEquals(
			1,
			_tableMapperImpl.deleteLeftPrimaryKeyTableMappings(leftPrimaryKey));

		leftModelListener.assertOnBeforeRemoveAssociation(
			true, leftPrimaryKey, Right.class.getName(), rightPrimaryKey1);

		leftModelListener.assertOnAfterRemoveAssociation(
			true, leftPrimaryKey, Right.class.getName(), rightPrimaryKey1);

		_leftBasePersistence.unregisterListener(leftModelListener);

		// Delete 1 entry, with right model listener

		rightModelListener = new RecorderModelListener<>();

		_rightBasePersistence.registerListener(rightModelListener);

		_mappingStore.put(leftPrimaryKey, new long[] {rightPrimaryKey1});

		Assert.assertEquals(
			1,
			_tableMapperImpl.deleteLeftPrimaryKeyTableMappings(leftPrimaryKey));

		rightModelListener.assertOnBeforeRemoveAssociation(
			true, rightPrimaryKey1, Left.class.getName(), leftPrimaryKey);

		rightModelListener.assertOnAfterRemoveAssociation(
			true, rightPrimaryKey1, Left.class.getName(), leftPrimaryKey);

		_rightBasePersistence.unregisterListener(rightModelListener);

		// Database error, with both left and right model listeners

		leftModelListener = new RecorderModelListener<>();

		_leftBasePersistence.registerListener(leftModelListener);

		rightModelListener = new RecorderModelListener<>();

		_rightBasePersistence.registerListener(rightModelListener);

		_mappingStore.put(leftPrimaryKey, new long[] {rightPrimaryKey1});

		MockDeleteLeftPrimaryKeyTableMappingsSqlUpdate
			mockDeleteLeftPrimaryKeyTableMappingsSqlUpdate =
				(MockDeleteLeftPrimaryKeyTableMappingsSqlUpdate)
					_tableMapperImpl.deleteLeftPrimaryKeyTableMappingsSqlUpdate;

		mockDeleteLeftPrimaryKeyTableMappingsSqlUpdate.setDatabaseError(true);

		try {
			_tableMapperImpl.deleteLeftPrimaryKeyTableMappings(leftPrimaryKey);

			Assert.fail();
		}
		catch (SystemException se) {
			Throwable cause = se.getCause();

			Assert.assertSame(RuntimeException.class, cause.getClass());

			Assert.assertEquals("Database error", cause.getMessage());
		}
		finally {
			mockDeleteLeftPrimaryKeyTableMappingsSqlUpdate.setDatabaseError(
				false);

			_mappingStore.remove(leftPrimaryKey);
		}

		leftModelListener.assertOnBeforeRemoveAssociation(
			true, leftPrimaryKey, Right.class.getName(), rightPrimaryKey1);

		rightModelListener.assertOnBeforeRemoveAssociation(
			true, rightPrimaryKey1, Left.class.getName(), leftPrimaryKey);

		leftModelListener.assertOnAfterRemoveAssociation(
			false, null, null, null);

		rightModelListener.assertOnAfterRemoveAssociation(
			false, null, null, null);
	}

	@Test
	public void testDeleteRightPrimaryKeyTableMappings() {

		// Delete 0 entry

		long rightPrimaryKey = 1;

		Assert.assertEquals(
			0,
			_tableMapperImpl.deleteRightPrimaryKeyTableMappings(
				rightPrimaryKey));

		// Delete 1 entry

		long leftPrimaryKey1 = 2;

		_mappingStore.put(leftPrimaryKey1, new long[] {rightPrimaryKey});

		Assert.assertEquals(
			1,
			_tableMapperImpl.deleteRightPrimaryKeyTableMappings(
				rightPrimaryKey));

		// Delete 2 entries

		long leftPrimaryKey2 = 3;

		_mappingStore.put(leftPrimaryKey1, new long[] {rightPrimaryKey});
		_mappingStore.put(leftPrimaryKey2, new long[] {rightPrimaryKey});

		Assert.assertEquals(
			2,
			_tableMapperImpl.deleteRightPrimaryKeyTableMappings(
				rightPrimaryKey));

		// Delete 0 entry, with left model listener

		RecorderModelListener<Left> leftModelListener =
			new RecorderModelListener<>();

		_leftBasePersistence.registerListener(leftModelListener);

		Assert.assertEquals(
			0,
			_tableMapperImpl.deleteRightPrimaryKeyTableMappings(
				rightPrimaryKey));

		leftModelListener.assertOnBeforeRemoveAssociation(
			false, null, null, null);

		leftModelListener.assertOnAfterRemoveAssociation(
			false, null, null, null);

		_leftBasePersistence.unregisterListener(leftModelListener);

		// Delete 0 entry, with right model listener

		RecorderModelListener<Right> rightModelListener =
			new RecorderModelListener<>();

		_rightBasePersistence.registerListener(rightModelListener);

		Assert.assertEquals(
			0,
			_tableMapperImpl.deleteRightPrimaryKeyTableMappings(
				rightPrimaryKey));

		rightModelListener.assertOnBeforeRemoveAssociation(
			false, null, null, null);

		rightModelListener.assertOnAfterRemoveAssociation(
			false, null, null, null);

		_rightBasePersistence.unregisterListener(rightModelListener);

		// Delete 1 entry, with left model listener

		leftModelListener = new RecorderModelListener<>();

		_leftBasePersistence.registerListener(leftModelListener);

		_mappingStore.put(leftPrimaryKey1, new long[] {rightPrimaryKey});

		Assert.assertEquals(
			1,
			_tableMapperImpl.deleteRightPrimaryKeyTableMappings(
				rightPrimaryKey));

		leftModelListener.assertOnBeforeRemoveAssociation(
			true, leftPrimaryKey1, Right.class.getName(), rightPrimaryKey);

		leftModelListener.assertOnAfterRemoveAssociation(
			true, leftPrimaryKey1, Right.class.getName(), rightPrimaryKey);

		_leftBasePersistence.unregisterListener(leftModelListener);

		// Delete 1 entry, with right model listener

		rightModelListener = new RecorderModelListener<>();

		_rightBasePersistence.registerListener(rightModelListener);

		_mappingStore.put(leftPrimaryKey1, new long[] {rightPrimaryKey});

		Assert.assertEquals(
			1,
			_tableMapperImpl.deleteRightPrimaryKeyTableMappings(
				rightPrimaryKey));

		rightModelListener.assertOnBeforeRemoveAssociation(
			true, rightPrimaryKey, Left.class.getName(), leftPrimaryKey1);

		rightModelListener.assertOnAfterRemoveAssociation(
			true, rightPrimaryKey, Left.class.getName(), leftPrimaryKey1);

		_rightBasePersistence.unregisterListener(rightModelListener);

		// Database error, with both left and right model listeners

		leftModelListener = new RecorderModelListener<>();

		_leftBasePersistence.registerListener(leftModelListener);

		rightModelListener = new RecorderModelListener<>();

		_rightBasePersistence.registerListener(rightModelListener);

		_mappingStore.put(leftPrimaryKey1, new long[] {rightPrimaryKey});

		MockDeleteRightPrimaryKeyTableMappingsSqlUpdate
			mockDeleteRightPrimaryKeyTableMappingsSqlUpdate =
				(MockDeleteRightPrimaryKeyTableMappingsSqlUpdate)
					_tableMapperImpl.
						deleteRightPrimaryKeyTableMappingsSqlUpdate;

		mockDeleteRightPrimaryKeyTableMappingsSqlUpdate.setDatabaseError(true);

		try {
			_tableMapperImpl.deleteRightPrimaryKeyTableMappings(
				rightPrimaryKey);

			Assert.fail();
		}
		catch (SystemException se) {
			Throwable cause = se.getCause();

			Assert.assertSame(RuntimeException.class, cause.getClass());

			Assert.assertEquals("Database error", cause.getMessage());
		}
		finally {
			mockDeleteRightPrimaryKeyTableMappingsSqlUpdate.setDatabaseError(
				false);

			_mappingStore.remove(rightPrimaryKey);
		}

		leftModelListener.assertOnBeforeRemoveAssociation(
			true, leftPrimaryKey1, Right.class.getName(), rightPrimaryKey);

		rightModelListener.assertOnBeforeRemoveAssociation(
			true, rightPrimaryKey, Left.class.getName(), leftPrimaryKey1);

		leftModelListener.assertOnAfterRemoveAssociation(
			false, null, null, null);

		rightModelListener.assertOnAfterRemoveAssociation(
			false, null, null, null);
	}

	@Test
	public void testDeleteTableMapping() {

		// No such table mapping

		long leftPrimaryKey = 1;
		long rightPrimaryKey = 2;

		Assert.assertFalse(
			_tableMapperImpl.deleteTableMapping(
				leftPrimaryKey, rightPrimaryKey));

		// Success, without model listener

		_mappingStore.put(leftPrimaryKey, new long[] {rightPrimaryKey});

		Assert.assertTrue(
			_tableMapperImpl.deleteTableMapping(
				leftPrimaryKey, rightPrimaryKey));

		// Success, with model listener

		RecorderModelListener<Left> leftModelListener =
			new RecorderModelListener<>();

		_leftBasePersistence.registerListener(leftModelListener);

		RecorderModelListener<Right> rightModelListener =
			new RecorderModelListener<>();

		_rightBasePersistence.registerListener(rightModelListener);

		_mappingStore.put(leftPrimaryKey, new long[] {rightPrimaryKey});

		Assert.assertTrue(
			_tableMapperImpl.deleteTableMapping(
				leftPrimaryKey, rightPrimaryKey));

		leftModelListener.assertOnBeforeRemoveAssociation(
			true, leftPrimaryKey, Right.class.getName(), rightPrimaryKey);

		rightModelListener.assertOnBeforeRemoveAssociation(
			true, rightPrimaryKey, Left.class.getName(), leftPrimaryKey);

		leftModelListener.assertOnAfterRemoveAssociation(
			true, leftPrimaryKey, Right.class.getName(), rightPrimaryKey);

		rightModelListener.assertOnAfterRemoveAssociation(
			true, rightPrimaryKey, Left.class.getName(), leftPrimaryKey);

		_leftBasePersistence.unregisterListener(leftModelListener);

		_rightBasePersistence.unregisterListener(rightModelListener);

		// Database error, with model listener

		leftModelListener = new RecorderModelListener<>();

		_leftBasePersistence.registerListener(leftModelListener);

		rightModelListener = new RecorderModelListener<>();

		_rightBasePersistence.registerListener(rightModelListener);

		_mappingStore.put(leftPrimaryKey, new long[] {rightPrimaryKey});

		MockDeleteMappingSqlUpdate mockDeleteSqlUpdate =
			(MockDeleteMappingSqlUpdate)
				_tableMapperImpl.deleteTableMappingSqlUpdate;

		mockDeleteSqlUpdate.setDatabaseError(true);

		try {
			_tableMapperImpl.deleteTableMapping(
				leftPrimaryKey, rightPrimaryKey);

			Assert.fail();
		}
		catch (SystemException se) {
			Throwable cause = se.getCause();

			Assert.assertSame(RuntimeException.class, cause.getClass());

			Assert.assertEquals("Database error", cause.getMessage());
		}
		finally {
			mockDeleteSqlUpdate.setDatabaseError(false);
			_mappingStore.remove(leftPrimaryKey);
		}

		leftModelListener.assertOnBeforeRemoveAssociation(
			true, leftPrimaryKey, Right.class.getName(), rightPrimaryKey);

		rightModelListener.assertOnBeforeRemoveAssociation(
			true, rightPrimaryKey, Left.class.getName(), leftPrimaryKey);

		leftModelListener.assertOnAfterRemoveAssociation(
			false, null, null, null);

		rightModelListener.assertOnAfterRemoveAssociation(
			false, null, null, null);

		_leftBasePersistence.unregisterListener(leftModelListener);

		_rightBasePersistence.unregisterListener(rightModelListener);

		// Phantom delete, with model listener

		leftModelListener = new RecorderModelListener<>();

		_leftBasePersistence.registerListener(leftModelListener);

		rightModelListener = new RecorderModelListener<>();

		_rightBasePersistence.registerListener(rightModelListener);

		PortalCache<Long, long[]> leftToRightPortalCache =
			_tableMapperImpl.leftToRightPortalCache;

		leftToRightPortalCache.put(
			leftPrimaryKey, new long[] {rightPrimaryKey});

		Assert.assertFalse(
			_tableMapperImpl.deleteTableMapping(
				leftPrimaryKey, rightPrimaryKey));

		leftModelListener.assertOnBeforeRemoveAssociation(
			true, leftPrimaryKey, Right.class.getName(), rightPrimaryKey);

		rightModelListener.assertOnBeforeRemoveAssociation(
			true, rightPrimaryKey, Left.class.getName(), leftPrimaryKey);

		leftModelListener.assertOnAfterRemoveAssociation(
			false, null, null, null);

		rightModelListener.assertOnAfterRemoveAssociation(
			false, null, null, null);

		_leftBasePersistence.unregisterListener(leftModelListener);

		_rightBasePersistence.unregisterListener(rightModelListener);
	}

	@Test
	public void testDeleteTableMappings() {
		long companyId = 0;
		long leftPrimaryKey1 = 1;
		long leftPrimaryKey2 = 2;
		long rightPrimaryKey1 = 3;
		long rightPrimaryKey2 = 4;

		Assert.assertArrayEquals(
			new long[0],
			_tableMapperImpl.deleteTableMappings(
				leftPrimaryKey1, new long[] {rightPrimaryKey1}));
		Assert.assertTrue(
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey1, rightPrimaryKey1));
		Assert.assertArrayEquals(
			new long[] {rightPrimaryKey1},
			_tableMapperImpl.deleteTableMappings(
				leftPrimaryKey1,
				new long[] {rightPrimaryKey1, rightPrimaryKey2}));
		Assert.assertTrue(
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey1, rightPrimaryKey1));
		Assert.assertTrue(
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey1, rightPrimaryKey2));
		Assert.assertArrayEquals(
			new long[] {rightPrimaryKey1, rightPrimaryKey2},
			_tableMapperImpl.deleteTableMappings(
				leftPrimaryKey1,
				new long[] {rightPrimaryKey1, rightPrimaryKey2}));
		Assert.assertArrayEquals(
			new long[0],
			_tableMapperImpl.deleteTableMappings(
				new long[] {leftPrimaryKey1}, rightPrimaryKey1));
		Assert.assertTrue(
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey1, rightPrimaryKey1));
		Assert.assertArrayEquals(
			new long[] {leftPrimaryKey1},
			_tableMapperImpl.deleteTableMappings(
				new long[] {leftPrimaryKey1, leftPrimaryKey2},
				rightPrimaryKey1));
		Assert.assertTrue(
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey1, rightPrimaryKey1));
		Assert.assertTrue(
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey2, rightPrimaryKey1));
		Assert.assertArrayEquals(
			new long[] {leftPrimaryKey1, leftPrimaryKey2},
			_tableMapperImpl.deleteTableMappings(
				new long[] {leftPrimaryKey1, leftPrimaryKey2},
				rightPrimaryKey1));

		PortalCache<Long, long[]> leftToRightPortalCache =
			_tableMapperImpl.leftToRightPortalCache;

		leftToRightPortalCache.put(
			leftPrimaryKey1, new long[] {rightPrimaryKey1});

		Assert.assertArrayEquals(
			new long[0],
			_tableMapperImpl.deleteTableMappings(
				leftPrimaryKey1, new long[] {rightPrimaryKey1}));
		Assert.assertTrue(
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey1, rightPrimaryKey1));
		Assert.assertArrayEquals(
			new long[] {rightPrimaryKey1},
			_tableMapperImpl.deleteTableMappings(
				leftPrimaryKey1,
				new long[] {rightPrimaryKey1, rightPrimaryKey2}));

		PortalCache<Long, long[]> rightToLeftPortalCache =
			_tableMapperImpl.rightToLeftPortalCache;

		rightToLeftPortalCache.put(
			rightPrimaryKey1, new long[] {leftPrimaryKey1});

		Assert.assertArrayEquals(
			new long[0],
			_tableMapperImpl.deleteTableMappings(
				new long[] {leftPrimaryKey1}, rightPrimaryKey1));
		Assert.assertTrue(
			_tableMapperImpl.addTableMapping(
				companyId, leftPrimaryKey1, rightPrimaryKey1));
		Assert.assertArrayEquals(
			new long[] {leftPrimaryKey1},
			_tableMapperImpl.deleteTableMappings(
				new long[] {leftPrimaryKey1, leftPrimaryKey2},
				rightPrimaryKey1));
	}

	@Test
	public void testDestroy() {
		testDestroy(_tableMapperImpl);
	}

	@Test
	public void testDestroyReverse() {
		testDestroy(new ReverseTableMapper<Right, Left>(_tableMapperImpl));
	}

	@Test
	public void testGetLeftBaseModels() {

		// Get 0 result

		long rightPrimaryKey = 1;

		List<Left> lefts = _tableMapperImpl.getLeftBaseModels(
			rightPrimaryKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Assert.assertSame(Collections.emptyList(), lefts);

		PortalCache<Long, long[]> rightToLeftPortalCache =
			_tableMapperImpl.rightToLeftPortalCache;

		rightToLeftPortalCache.remove(rightPrimaryKey);

		// Get 1 result

		long leftPrimaryKey1 = 2;

		_mappingStore.put(leftPrimaryKey1, new long[] {rightPrimaryKey});

		lefts = _tableMapperImpl.getLeftBaseModels(
			rightPrimaryKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Assert.assertEquals(1, lefts.size());

		Left left1 = lefts.get(0);

		Assert.assertEquals(leftPrimaryKey1, left1.getPrimaryKeyObj());

		rightToLeftPortalCache.remove(rightPrimaryKey);

		// Get 2 results, unsorted

		long leftPrimaryKey2 = 3;

		_mappingStore.put(leftPrimaryKey1, new long[] {rightPrimaryKey});
		_mappingStore.put(leftPrimaryKey2, new long[] {rightPrimaryKey});

		lefts = _tableMapperImpl.getLeftBaseModels(
			rightPrimaryKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Assert.assertEquals(2, lefts.size());

		left1 = lefts.get(0);
		Left left2 = lefts.get(1);

		Assert.assertEquals(leftPrimaryKey1, left1.getPrimaryKeyObj());
		Assert.assertEquals(leftPrimaryKey2, left2.getPrimaryKeyObj());

		rightToLeftPortalCache.remove(rightPrimaryKey);

		// Get 2 results, sorted

		_mappingStore.put(leftPrimaryKey1, new long[] {rightPrimaryKey});
		_mappingStore.put(leftPrimaryKey2, new long[] {rightPrimaryKey});

		lefts = _tableMapperImpl.getLeftBaseModels(
			rightPrimaryKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new OrderByComparator<Left>() {

				@Override
				public int compare(Left left1, Left left2) {
					Long leftPrimaryKey1 = (Long)left1.getPrimaryKeyObj();
					Long leftPrimaryKey2 = (Long)left2.getPrimaryKeyObj();

					return leftPrimaryKey2.compareTo(leftPrimaryKey1);
				}

			});

		Assert.assertEquals(2, lefts.size());

		left1 = lefts.get(0);
		left2 = lefts.get(1);

		Assert.assertEquals(leftPrimaryKey2, left1.getPrimaryKeyObj());
		Assert.assertEquals(leftPrimaryKey1, left2.getPrimaryKeyObj());

		rightToLeftPortalCache.remove(rightPrimaryKey);

		// Get 3 results, paginated

		long leftPrimaryKey3 = 4;

		_mappingStore.put(leftPrimaryKey1, new long[] {rightPrimaryKey});
		_mappingStore.put(leftPrimaryKey2, new long[] {rightPrimaryKey});
		_mappingStore.put(leftPrimaryKey3, new long[] {rightPrimaryKey});

		lefts = _tableMapperImpl.getLeftBaseModels(rightPrimaryKey, 1, 2, null);

		Assert.assertEquals(1, lefts.size());

		Left left = lefts.get(0);

		Assert.assertEquals(leftPrimaryKey2, left.getPrimaryKeyObj());

		rightToLeftPortalCache.remove(rightPrimaryKey);

		// No such model exception

		_leftBasePersistence.setNoSuchModelException(true);

		try {
			_tableMapperImpl.getLeftBaseModels(
				rightPrimaryKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		}
		catch (SystemException se) {
			Throwable cause = se.getCause();

			Assert.assertSame(NoSuchModelException.class, cause.getClass());

			Assert.assertEquals(
				String.valueOf(leftPrimaryKey1), cause.getMessage());
		}
		finally {
			_leftBasePersistence.setNoSuchModelException(false);
		}
	}

	@Test
	public void testGetLeftPrimaryKeys() {

		// Get 0 result

		long rightPrimaryKey = 1;

		long[] leftPrimaryKeys = _tableMapperImpl.getLeftPrimaryKeys(
			rightPrimaryKey);

		Assert.assertEquals(0, leftPrimaryKeys.length);

		// Hit cache

		Assert.assertSame(
			leftPrimaryKeys,
			_tableMapperImpl.getLeftPrimaryKeys(rightPrimaryKey));

		// Get 2 results, ensure ordered

		long leftPrimaryKey1 = 3;
		long leftPrimaryKey2 = 2;

		PortalCache<Long, long[]> rightToLeftPortalCache =
			_tableMapperImpl.rightToLeftPortalCache;

		rightToLeftPortalCache.remove(rightPrimaryKey);

		_mappingStore.put(leftPrimaryKey1, new long[] {rightPrimaryKey});
		_mappingStore.put(leftPrimaryKey2, new long[] {rightPrimaryKey});

		leftPrimaryKeys = _tableMapperImpl.getLeftPrimaryKeys(rightPrimaryKey);

		Assert.assertArrayEquals(
			new long[] {leftPrimaryKey2, leftPrimaryKey1}, leftPrimaryKeys);

		// Database error

		rightToLeftPortalCache.remove(rightPrimaryKey);

		MockGetLeftPrimaryKeysSqlQuery
			mockGetLeftPrimaryKeysByRightPrimaryKeyMappingSqlQuery =
				(MockGetLeftPrimaryKeysSqlQuery)
					_tableMapperImpl.getLeftPrimaryKeysSqlQuery;

		mockGetLeftPrimaryKeysByRightPrimaryKeyMappingSqlQuery.setDatabaseError(
			true);

		try {
			_tableMapperImpl.getLeftPrimaryKeys(rightPrimaryKey);
		}
		catch (SystemException se) {
			Throwable cause = se.getCause();

			Assert.assertSame(RuntimeException.class, cause.getClass());

			Assert.assertEquals("Database error", cause.getMessage());
		}
		finally {
			mockGetLeftPrimaryKeysByRightPrimaryKeyMappingSqlQuery.
				setDatabaseError(false);
		}
	}

	@Test
	public void testGetRightBaseModels() {

		// Get 0 result

		long leftPrimaryKey = 1;

		List<Right> rights = _tableMapperImpl.getRightBaseModels(
			leftPrimaryKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Assert.assertSame(Collections.emptyList(), rights);

		PortalCache<Long, long[]> leftToRightPortalCache =
			_tableMapperImpl.leftToRightPortalCache;

		leftToRightPortalCache.remove(leftPrimaryKey);

		// Get 1 result

		long rightPrimaryKey1 = 2;

		_mappingStore.put(leftPrimaryKey, new long[] {rightPrimaryKey1});

		rights = _tableMapperImpl.getRightBaseModels(
			leftPrimaryKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Assert.assertEquals(1, rights.size());

		Right right1 = rights.get(0);

		Assert.assertEquals(rightPrimaryKey1, right1.getPrimaryKeyObj());

		leftToRightPortalCache.remove(leftPrimaryKey);

		// Get 2 results, unsorted

		long rightPrimaryKey2 = 3;

		_mappingStore.put(
			leftPrimaryKey, new long[] {rightPrimaryKey2, rightPrimaryKey1});

		rights = _tableMapperImpl.getRightBaseModels(
			leftPrimaryKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Assert.assertEquals(2, rights.size());

		right1 = rights.get(0);
		Right right2 = rights.get(1);

		Assert.assertEquals(rightPrimaryKey1, right1.getPrimaryKeyObj());
		Assert.assertEquals(rightPrimaryKey2, right2.getPrimaryKeyObj());

		leftToRightPortalCache.remove(leftPrimaryKey);

		// Get 2 results, sorted

		_mappingStore.put(
			leftPrimaryKey, new long[] {rightPrimaryKey2, rightPrimaryKey1});

		rights = _tableMapperImpl.getRightBaseModels(
			leftPrimaryKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new OrderByComparator<Right>() {

				@Override
				public int compare(Right right1, Right right2) {
					Long rightPrimaryKey1 = (Long)right1.getPrimaryKeyObj();
					Long rightPrimaryKey2 = (Long)right2.getPrimaryKeyObj();

					return rightPrimaryKey2.compareTo(rightPrimaryKey1);
				}

			});

		Assert.assertEquals(2, rights.size());

		right1 = rights.get(0);
		right2 = rights.get(1);

		Assert.assertEquals(rightPrimaryKey2, right1.getPrimaryKeyObj());
		Assert.assertEquals(rightPrimaryKey1, right2.getPrimaryKeyObj());

		leftToRightPortalCache.remove(leftPrimaryKey);

		// Get 3 results, paginated

		long rightPrimaryKey3 = 4;

		_mappingStore.put(
			leftPrimaryKey,
			new long[] {rightPrimaryKey3, rightPrimaryKey2, rightPrimaryKey1});

		rights = _tableMapperImpl.getRightBaseModels(
			leftPrimaryKey, 1, 2, null);

		Assert.assertEquals(1, rights.size());

		Right right = rights.get(0);

		Assert.assertEquals(rightPrimaryKey2, right.getPrimaryKeyObj());

		leftToRightPortalCache.remove(leftPrimaryKey);

		// No such model exception

		_rightBasePersistence.setNoSuchModelException(true);

		try {
			_tableMapperImpl.getRightBaseModels(
				leftPrimaryKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		}
		catch (SystemException se) {
			Throwable cause = se.getCause();

			Assert.assertSame(NoSuchModelException.class, cause.getClass());

			Assert.assertEquals(
				String.valueOf(rightPrimaryKey1), cause.getMessage());
		}
		finally {
			_rightBasePersistence.setNoSuchModelException(false);
		}
	}

	@Test
	public void testGetRightPrimaryKeys() {

		// Get 0 result

		long leftPrimaryKey = 1;

		long[] rightPrimaryKeys = _tableMapperImpl.getRightPrimaryKeys(
			leftPrimaryKey);

		Assert.assertEquals(0, rightPrimaryKeys.length);

		// Hit cache

		Assert.assertSame(
			rightPrimaryKeys,
			_tableMapperImpl.getRightPrimaryKeys(leftPrimaryKey));

		// Get 2 results, ensure ordered

		long rightPrimaryKey1 = 3;
		long rightPrimaryKey2 = 2;

		PortalCache<Long, long[]> leftToRightPortalCache =
			_tableMapperImpl.leftToRightPortalCache;

		leftToRightPortalCache.remove(leftPrimaryKey);

		_mappingStore.put(
			leftPrimaryKey, new long[] {rightPrimaryKey1, rightPrimaryKey2});

		rightPrimaryKeys = _tableMapperImpl.getRightPrimaryKeys(leftPrimaryKey);

		Assert.assertArrayEquals(
			new long[] {rightPrimaryKey2, rightPrimaryKey1}, rightPrimaryKeys);

		// Database error

		leftToRightPortalCache.remove(leftPrimaryKey);

		MockGetRightPrimaryKeysSqlQuery
			mockGetRightPrimaryKeysByLeftPrimaryKeyMappingSqlQuery =
				(MockGetRightPrimaryKeysSqlQuery)
					_tableMapperImpl.getRightPrimaryKeysSqlQuery;

		mockGetRightPrimaryKeysByLeftPrimaryKeyMappingSqlQuery.setDatabaseError(
			true);

		try {
			_tableMapperImpl.getRightPrimaryKeys(leftPrimaryKey);
		}
		catch (SystemException se) {
			Throwable cause = se.getCause();

			Assert.assertSame(RuntimeException.class, cause.getClass());

			Assert.assertEquals("Database error", cause.getMessage());
		}
		finally {
			mockGetRightPrimaryKeysByLeftPrimaryKeyMappingSqlQuery.
				setDatabaseError(false);
		}
	}

	@Test
	public void testGetSetReverseTableMapper() {
		TableMapper<Right, Left> tableMapper = new ReverseTableMapper<>(
			_tableMapperImpl);

		_tableMapperImpl.setReverseTableMapper(tableMapper);

		Assert.assertSame(
			tableMapper, _tableMapperImpl.getReverseTableMapper());
	}

	@Test
	public void testMatches() {
		Assert.assertTrue(
			_tableMapperImpl.matches(_LEFT_COLUMN_NAME, _RIGHT_COLUMN_NAME));
		Assert.assertFalse(
			_tableMapperImpl.matches(_LEFT_COLUMN_NAME, _LEFT_COLUMN_NAME));
		Assert.assertFalse(
			_tableMapperImpl.matches(_RIGHT_COLUMN_NAME, _LEFT_COLUMN_NAME));
	}

	@Test
	public void testReverseTableMapper() {
		Class<?> clazz = TableMapper.class;

		ClassLoader classLoader = clazz.getClassLoader();

		RecordInvocationHandler recordInvocationHandler =
			new RecordInvocationHandler();

		TableMapper<Left, Right> tableMapper =
			(TableMapper<Left, Right>)ProxyUtil.newProxyInstance(
				classLoader, new Class<?>[] {TableMapper.class},
				recordInvocationHandler);

		ReverseTableMapper<Right, Left> reverseTableMapper =
			new ReverseTableMapper<>(tableMapper);

		recordInvocationHandler.setTableMapper(reverseTableMapper);

		reverseTableMapper.addTableMapping(0, 1, 2);

		recordInvocationHandler.assertCall("addTableMapping", 0L, 2L, 1L);

		reverseTableMapper.addTableMappings(0, 1, new long[] {2});

		recordInvocationHandler.assertCall(
			"addTableMappings", 0L, new long[] {2}, 1L);

		reverseTableMapper.addTableMappings(0, new long[] {1}, 2L);

		recordInvocationHandler.assertCall(
			"addTableMappings", 0L, 2L, new long[] {1});

		reverseTableMapper.containsTableMapping(1, 2);

		recordInvocationHandler.assertCall("containsTableMapping", 2L, 1L);

		reverseTableMapper.deleteRightPrimaryKeyTableMappings(2);

		recordInvocationHandler.assertCall(
			"deleteLeftPrimaryKeyTableMappings", 2L);

		reverseTableMapper.deleteLeftPrimaryKeyTableMappings(1);

		recordInvocationHandler.assertCall(
			"deleteRightPrimaryKeyTableMappings", 1L);

		reverseTableMapper.deleteTableMapping(1, 2);

		recordInvocationHandler.assertCall("deleteTableMapping", 2L, 1L);

		reverseTableMapper.deleteTableMappings(1, new long[] {2});

		recordInvocationHandler.assertCall(
			"deleteTableMappings", new long[] {2}, 1L);

		reverseTableMapper.deleteTableMappings(new long[] {1}, 2);

		recordInvocationHandler.assertCall(
			"deleteTableMappings", 2L, new long[] {1});

		reverseTableMapper.getRightBaseModels(1, 2, 3, null);

		recordInvocationHandler.assertCall("getLeftBaseModels", 1L, 2, 3, null);

		reverseTableMapper.getRightPrimaryKeys(1);

		recordInvocationHandler.assertCall("getLeftPrimaryKeys", 1L);

		reverseTableMapper.getLeftBaseModels(2, 2, 3, null);

		recordInvocationHandler.assertCall(
			"getRightBaseModels", 2L, 2, 3, null);

		reverseTableMapper.getLeftPrimaryKeys(2);

		recordInvocationHandler.assertCall("getRightPrimaryKeys", 2L);

		Assert.assertSame(
			tableMapper, reverseTableMapper.getReverseTableMapper());

		reverseTableMapper.matches("left", "right");

		recordInvocationHandler.assertCall("matches", "right", "left");
	}

	@Test
	public void testTableMapperFactory() {

		// Initial empty

		Map<String, TableMapper<?, ?>> tableMappers =
			TableMapperFactory.tableMappers;

		Assert.assertTrue(tableMappers.isEmpty());

		// Create

		TableMapper<Left, Right> tableMapper =
			TableMapperFactory.getTableMapper(
				_TABLE_NAME, _COMPANY_COLUMN_NAME, _LEFT_COLUMN_NAME,
				_RIGHT_COLUMN_NAME, _leftBasePersistence,
				_rightBasePersistence);

		Assert.assertEquals(1, tableMappers.size());
		Assert.assertSame(tableMapper, tableMappers.get(_TABLE_NAME));

		TableMapper<Right, Left> reverseTableMapper =
			tableMapper.getReverseTableMapper();

		Assert.assertNotNull(reverseTableMapper);

		// Hit cache

		Assert.assertSame(
			tableMapper,
			TableMapperFactory.getTableMapper(
				_TABLE_NAME, _COMPANY_COLUMN_NAME, _LEFT_COLUMN_NAME,
				_RIGHT_COLUMN_NAME, _leftBasePersistence,
				_rightBasePersistence));

		// Reverse mapping table

		Assert.assertSame(
			reverseTableMapper,
			TableMapperFactory.getTableMapper(
				_TABLE_NAME, _COMPANY_COLUMN_NAME, _RIGHT_COLUMN_NAME,
				_LEFT_COLUMN_NAME, _rightBasePersistence,
				_leftBasePersistence));

		// Remove

		TableMapperFactory.removeTableMapper(_TABLE_NAME);

		Assert.assertTrue(tableMappers.isEmpty());

		TableMapperFactory.removeTableMapper(_TABLE_NAME);

		Assert.assertTrue(tableMappers.isEmpty());
	}

	@Test
	public void testTableMapperFactoryCache() {
		Set<String> cacheMappingTableNames =
			ReflectionTestUtil.getAndSetFieldValue(
				TableMapperFactory.class, "_cachelessMappingTableNames",
				new HashSet<String>() {

					@Override
					public boolean contains(Object o) {
						return true;
					}

				});

		try {
			testTableMapperFactory();
		}
		finally {
			ReflectionTestUtil.setFieldValue(
				TableMapperFactory.class, "_cachelessMappingTableNames",
				cacheMappingTableNames);
		}
	}

	protected void testDestroy(TableMapper<?, ?> tableMapper) {
		Registry registry = RegistryUtil.getRegistry();

		MultiVMPool multiVMPool = registry.getService(MultiVMPool.class);

		Map<String, PortalCache<?, ?>> portalCaches =
			ReflectionTestUtil.getFieldValue(multiVMPool, "_portalCaches");

		Assert.assertEquals(2, portalCaches.size());

		if (tableMapper instanceof ReverseTableMapper) {
			Assert.assertSame(
				ReflectionTestUtil.getFieldValue(
					tableMapper.getReverseTableMapper(),
					"leftToRightPortalCache"),
				portalCaches.get(
					TableMapper.class.getName() + "-" + _TABLE_NAME +
						"-LeftToRight"));
			Assert.assertSame(
				ReflectionTestUtil.getFieldValue(
					tableMapper.getReverseTableMapper(),
					"rightToLeftPortalCache"),
				portalCaches.get(
					TableMapper.class.getName() + "-" + _TABLE_NAME +
						"-RightToLeft"));
		}
		else {
			Assert.assertSame(
				ReflectionTestUtil.getFieldValue(
					tableMapper, "leftToRightPortalCache"),
				portalCaches.get(
					TableMapper.class.getName() + "-" + _TABLE_NAME +
						"-LeftToRight"));
			Assert.assertSame(
				ReflectionTestUtil.getFieldValue(
					tableMapper, "rightToLeftPortalCache"),
				portalCaches.get(
					TableMapper.class.getName() + "-" + _TABLE_NAME +
						"-RightToLeft"));
		}

		tableMapper.destroy();

		Assert.assertTrue(portalCaches.isEmpty());
	}

	private static final String _COMPANY_COLUMN_NAME = "companyId";

	private static final String _LEFT_COLUMN_NAME = "leftId";

	private static final String _RIGHT_COLUMN_NAME = "rightId";

	private static final String _TABLE_NAME = "Lefts_Rights";

	private DataSource _dataSource;
	private MockBasePersistence<Left> _leftBasePersistence;
	private final Map<Long, long[]> _mappingStore = new HashMap<>();
	private MockBasePersistence<Right> _rightBasePersistence;
	private TableMapperImpl<Left, Right> _tableMapperImpl;

	private static class RecorderModelListener<T extends BaseModel<T>>
		extends BaseModelListener<T> {

		public void assertOnAfterAddAssociation(
			boolean called, Object classPK, String associationClassName,
			Object associationClassPK) {

			_assertCall(
				0, called, classPK, associationClassName, associationClassPK);
		}

		public void assertOnAfterRemoveAssociation(
			boolean called, Object classPK, String associationClassName,
			Object associationClassPK) {

			_assertCall(
				1, called, classPK, associationClassName, associationClassPK);
		}

		public void assertOnBeforeAddAssociation(
			boolean called, Object classPK, String associationClassName,
			Object associationClassPK) {

			_assertCall(
				2, called, classPK, associationClassName, associationClassPK);
		}

		public void assertOnBeforeRemoveAssociation(
			boolean called, Object classPK, String associationClassName,
			Object associationClassPK) {

			_assertCall(
				3, called, classPK, associationClassName, associationClassPK);
		}

		@Override
		public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK) {

			_record(0, classPK, associationClassName, associationClassPK);
		}

		@Override
		public void onAfterRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK) {

			_record(1, classPK, associationClassName, associationClassPK);
		}

		@Override
		public void onBeforeAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK) {

			_record(2, classPK, associationClassName, associationClassPK);
		}

		@Override
		public void onBeforeRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK) {

			_record(3, classPK, associationClassName, associationClassPK);
		}

		private void _assertCall(
			int index, boolean called, Object classPK,
			String associationClassName, Object associationClassPK) {

			if (called) {
				Assert.assertSame(_classPKs[index], classPK);
				Assert.assertEquals(
					_associationClassNames[index], associationClassName);
				Assert.assertSame(
					_associationClassPKs[index], associationClassPK);
			}
			else {
				Assert.assertFalse(
					"Called onAfterAddAssociation", _markers[index]);
			}
		}

		private void _record(
			int index, Object classPK, String associationClassName,
			Object associationClassPK) {

			_markers[index] = true;
			_classPKs[index] = classPK;
			_associationClassNames[index] = associationClassName;
			_associationClassPKs[index] = associationClassPK;
		}

		private final String[] _associationClassNames = new String[4];
		private final Object[] _associationClassPKs = new Object[4];
		private final Object[] _classPKs = new Object[4];
		private final boolean[] _markers = new boolean[4];

	}

	private static class RecordInvocationHandler implements InvocationHandler {

		public void assertCall(String methodName, Object... args) {
			Object[] record = _records.get(methodName);

			Assert.assertArrayEquals(record, args);
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) {
			_records.put(method.getName(), args);

			Class<?> returnType = method.getReturnType();

			if (returnType == boolean.class) {
				return false;
			}
			else if (returnType == int.class) {
				return 0;
			}
			else if (returnType == List.class) {
				return Collections.emptyList();
			}
			else if (returnType == long[].class) {
				return new long[0];
			}
			else if (returnType == TableMapper.class) {
				return _tableMapper;
			}

			return null;
		}

		public void setTableMapper(TableMapper<?, ?> tableMapper) {
			_tableMapper = tableMapper;
		}

		private final Map<String, Object[]> _records = new HashMap<>();
		private TableMapper<?, ?> _tableMapper;

	}

	private class GetPrimaryKeyObjInvocationHandler
		implements InvocationHandler {

		public GetPrimaryKeyObjInvocationHandler(Serializable primaryKey) {
			_primaryKey = primaryKey;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) {
			String methodName = method.getName();

			if (methodName.equals("getPrimaryKeyObj")) {
				return _primaryKey;
			}

			throw new UnsupportedOperationException();
		}

		private final Serializable _primaryKey;

	}

	private interface Left extends LeftModel {
	};

	private interface LeftModel extends BaseModel<Left> {
	};

	private class MockAddMappingSqlUpdate implements SqlUpdate {

		public MockAddMappingSqlUpdate(
			DataSource dataSource, String sql, ParamSetter... paramSetters) {

			Assert.assertSame(_dataSource, dataSource);
			Assert.assertEquals(
				"INSERT INTO " + _TABLE_NAME + " (" + _COMPANY_COLUMN_NAME +
					", " + _LEFT_COLUMN_NAME + ", " + _RIGHT_COLUMN_NAME+ ") " +
						"VALUES (?, ?, ?)",
				sql);
			Assert.assertArrayEquals(
				new ParamSetter[] {
					ParamSetter.BIGINT, ParamSetter.BIGINT, ParamSetter.BIGINT
				},
				paramSetters);
		}

		@Override
		public int update(Object... params) {
			Assert.assertEquals(3, params.length);
			Assert.assertSame(Long.class, params[0].getClass());
			Assert.assertSame(Long.class, params[1].getClass());
			Assert.assertSame(Long.class, params[2].getClass());

			Long leftPrimaryKey = (Long)params[1];
			Long rightPrimaryKey = (Long)params[2];

			long[] rightPrimaryKeys = _mappingStore.get(leftPrimaryKey);

			if (rightPrimaryKeys == null) {
				rightPrimaryKeys = new long[1];

				rightPrimaryKeys[0] = rightPrimaryKey;

				_mappingStore.put(leftPrimaryKey, rightPrimaryKeys);
			}
			else if (ArrayUtil.contains(rightPrimaryKeys, rightPrimaryKey)) {
				throw new RuntimeException(
					"Unique key violation for left primary key " +
						leftPrimaryKey + " and right primary key " +
							rightPrimaryKey);
			}
			else {
				rightPrimaryKeys = ArrayUtil.append(
					rightPrimaryKeys, rightPrimaryKey);

				_mappingStore.put(leftPrimaryKey, rightPrimaryKeys);
			}

			return 1;
		}

	}

	private class MockBasePersistence<T extends BaseModel<T>>
		extends BasePersistenceImpl<T> {

		public MockBasePersistence(Class<T> clazz) {
			setModelClass(clazz);
		}

		@Override
		public T findByPrimaryKey(Serializable primaryKey)
			throws NoSuchModelException {

			if (_noSuchModelException) {
				throw new NoSuchModelException(primaryKey.toString());
			}

			Class<T> modelClass = getModelClass();

			ClassLoader classLoader = modelClass.getClassLoader();

			return (T)ProxyUtil.newProxyInstance(
				classLoader, new Class<?>[] {modelClass},
				new GetPrimaryKeyObjInvocationHandler(primaryKey));
		}

		@Override
		public ModelListener<T>[] getListeners() {
			return _listeners.toArray(new ModelListener[_listeners.size()]);
		}

		@Override
		public void registerListener(ModelListener<T> listener) {
			_listeners.add(listener);
		}

		public void setNoSuchModelException(boolean noSuchModelException) {
			_noSuchModelException = noSuchModelException;
		}

		@Override
		public void unregisterListener(ModelListener<T> listener) {
			_listeners.remove(listener);
		}

		private final List<ModelListener<T>> _listeners = new ArrayList<>();
		private boolean _noSuchModelException;

	}

	private class MockDeleteLeftPrimaryKeyTableMappingsSqlUpdate
		implements SqlUpdate {

		public MockDeleteLeftPrimaryKeyTableMappingsSqlUpdate(
			DataSource dataSource, String sql, ParamSetter... paramSetters) {

			Assert.assertSame(_dataSource, dataSource);
			Assert.assertEquals(
				"DELETE FROM " + _TABLE_NAME + " WHERE " + _LEFT_COLUMN_NAME +
					" = ?",
				sql);
			Assert.assertArrayEquals(
				new ParamSetter[] {ParamSetter.BIGINT}, paramSetters);
		}

		public void setDatabaseError(boolean databaseError) {
			_databaseError = databaseError;
		}

		@Override
		public int update(Object... params) {
			Assert.assertEquals(1, params.length);
			Assert.assertSame(Long.class, params[0].getClass());

			if (_databaseError) {
				throw new RuntimeException("Database error");
			}

			Long leftPrimaryKey = (Long)params[0];

			long[] rightPrimaryKeys = _mappingStore.remove(leftPrimaryKey);

			if (rightPrimaryKeys == null) {
				return 0;
			}

			return rightPrimaryKeys.length;
		}

		private boolean _databaseError;

	}

	private class MockDeleteMappingSqlUpdate implements SqlUpdate {

		public MockDeleteMappingSqlUpdate(
			DataSource dataSource, String sql, ParamSetter... paramSetters) {

			Assert.assertSame(_dataSource, dataSource);
			Assert.assertEquals(
				"DELETE FROM " + _TABLE_NAME + " WHERE " + _LEFT_COLUMN_NAME +
					" = ? AND " + _RIGHT_COLUMN_NAME + " = ?",
				sql);
			Assert.assertArrayEquals(
				new ParamSetter[] {ParamSetter.BIGINT, ParamSetter.BIGINT},
				paramSetters);
		}

		public void setDatabaseError(boolean databaseError) {
			_databaseError = databaseError;
		}

		@Override
		public int update(Object... params) {
			Assert.assertEquals(2, params.length);
			Assert.assertSame(Long.class, params[0].getClass());
			Assert.assertSame(Long.class, params[1].getClass());

			if (_databaseError) {
				throw new RuntimeException("Database error");
			}

			Long leftPrimaryKey = (Long)params[0];
			Long rightPrimaryKey = (Long)params[1];

			long[] rightPrimaryKeys = _mappingStore.get(leftPrimaryKey);

			if (rightPrimaryKeys == null) {
				return 0;
			}

			if (ArrayUtil.contains(rightPrimaryKeys, rightPrimaryKey)) {
				rightPrimaryKeys = ArrayUtil.remove(
					rightPrimaryKeys, rightPrimaryKey);

				_mappingStore.put(leftPrimaryKey, rightPrimaryKeys);

				return 1;
			}

			return 0;
		}

		private boolean _databaseError;

	}

	private class MockDeleteRightPrimaryKeyTableMappingsSqlUpdate
		implements SqlUpdate {

		public MockDeleteRightPrimaryKeyTableMappingsSqlUpdate(
			DataSource dataSource, String sql, ParamSetter... paramSetters) {

			Assert.assertSame(_dataSource, dataSource);
			Assert.assertEquals(
				"DELETE FROM " + _TABLE_NAME + " WHERE " + _RIGHT_COLUMN_NAME +
					" = ?",
				sql);
			Assert.assertArrayEquals(
				new ParamSetter[] {ParamSetter.BIGINT}, paramSetters);
		}

		public void setDatabaseError(boolean databaseError) {
			_databaseError = databaseError;
		}

		@Override
		public int update(Object... params) {
			Assert.assertEquals(1, params.length);
			Assert.assertSame(Long.class, params[0].getClass());

			if (_databaseError) {
				throw new RuntimeException("Database error");
			}

			int count = 0;

			Long rightPrimaryKey = (Long)params[0];

			for (Map.Entry<Long, long[]> entry : _mappingStore.entrySet()) {
				long[] rightPrimaryKeys = entry.getValue();

				if (ArrayUtil.contains(rightPrimaryKeys, rightPrimaryKey)) {
					count++;

					rightPrimaryKeys = ArrayUtil.remove(
						rightPrimaryKeys, rightPrimaryKey);

					entry.setValue(rightPrimaryKeys);
				}
			}

			return count;
		}

		private boolean _databaseError;

	}

	private class MockGetLeftPrimaryKeysSqlQuery
		implements MappingSqlQuery<Long> {

		public MockGetLeftPrimaryKeysSqlQuery(
			DataSource dataSource, String sql, RowMapper<Long> rowMapper,
			ParamSetter... paramSetters) {

			Assert.assertSame(_dataSource, dataSource);
			Assert.assertEquals(
				"SELECT " + _LEFT_COLUMN_NAME + " FROM " + _TABLE_NAME +
					" WHERE " + _RIGHT_COLUMN_NAME + " = ?",
				sql);
			Assert.assertArrayEquals(
				new ParamSetter[] {ParamSetter.BIGINT}, paramSetters);
			Assert.assertSame(RowMapper.PRIMARY_KEY, rowMapper);
		}

		@Override
		public List<Long> execute(Object... params) {
			Assert.assertEquals(1, params.length);
			Assert.assertSame(Long.class, params[0].getClass());

			if (_databaseError) {
				throw new RuntimeException("Database error");
			}

			Long rightPrimaryKey = (Long)params[0];

			List<Long> leftPrimaryKeysList = new ArrayList<>();

			for (Map.Entry<Long, long[]> entry : _mappingStore.entrySet()) {
				long[] rightPrimaryKeys = entry.getValue();

				if (ArrayUtil.contains(rightPrimaryKeys, rightPrimaryKey)) {
					leftPrimaryKeysList.add(entry.getKey());
				}
			}

			return leftPrimaryKeysList;
		}

		public void setDatabaseError(boolean databaseError) {
			_databaseError = databaseError;
		}

		private boolean _databaseError;

	}

	private class MockGetRightPrimaryKeysSqlQuery
		implements MappingSqlQuery<Long> {

		public MockGetRightPrimaryKeysSqlQuery(
			DataSource dataSource, String sql, RowMapper<Long> rowMapper,
			ParamSetter... paramSetters) {

			Assert.assertSame(_dataSource, dataSource);
			Assert.assertEquals(
				"SELECT " + _RIGHT_COLUMN_NAME + " FROM " + _TABLE_NAME +
					" WHERE " + _LEFT_COLUMN_NAME + " = ?",
				sql);
			Assert.assertArrayEquals(
				new ParamSetter[] {ParamSetter.BIGINT}, paramSetters);
			Assert.assertSame(RowMapper.PRIMARY_KEY, rowMapper);
		}

		@Override
		public List<Long> execute(Object... params) {
			Assert.assertEquals(1, params.length);
			Assert.assertSame(Long.class, params[0].getClass());

			if (_databaseError) {
				throw new RuntimeException("Database error");
			}

			Long leftPrimaryKey = (Long)params[0];

			long[] rightPrimaryKeys = _mappingStore.get(leftPrimaryKey);

			if (rightPrimaryKeys == null) {
				return Collections.emptyList();
			}

			List<Long> rightPrimaryKeysList = new ArrayList<>(
				rightPrimaryKeys.length);

			for (long rightPrimaryKey : rightPrimaryKeys) {
				rightPrimaryKeysList.add(rightPrimaryKey);
			}

			return rightPrimaryKeysList;
		}

		public void setDatabaseError(boolean databaseError) {
			_databaseError = databaseError;
		}

		private boolean _databaseError;

	}

	private class MockMappingSqlQueryFactory implements MappingSqlQueryFactory {

		@Override
		public <T> MappingSqlQuery<T> getMappingSqlQuery(
			DataSource dataSource, String sql, RowMapper<T> rowMapper,
			ParamSetter... paramSetters) {

			int count = _counter++;

			if (count == 0) {
				return (MappingSqlQuery<T>)
					new MockGetLeftPrimaryKeysSqlQuery(
						dataSource, sql, RowMapper.PRIMARY_KEY, paramSetters);
			}

			if (count == 1) {
				return (MappingSqlQuery<T>)
					new MockGetRightPrimaryKeysSqlQuery(
						dataSource, sql, RowMapper.PRIMARY_KEY, paramSetters);
			}

			return null;
		}

		private int _counter;

	}

	private class MockSqlUpdateFactory implements SqlUpdateFactory {

		@Override
		public SqlUpdate getSqlUpdate(
			DataSource dataSource, String sql, ParamSetter... paramSetters) {

			int count = _count++;

			if (count == 0) {
				return new MockAddMappingSqlUpdate(
					dataSource, sql, paramSetters);
			}

			if (count == 1) {
				return new MockDeleteLeftPrimaryKeyTableMappingsSqlUpdate(
					dataSource, sql, paramSetters);
			}

			if (count == 2) {
				return new MockDeleteRightPrimaryKeyTableMappingsSqlUpdate(
					dataSource, sql, paramSetters);
			}

			if (count == 3) {
				return new MockDeleteMappingSqlUpdate(
					dataSource, sql, paramSetters);
			}

			return null;
		}

		private int _count;

	}

	private interface Right extends RightModel {};

	private interface RightModel extends BaseModel<Right> {};

}