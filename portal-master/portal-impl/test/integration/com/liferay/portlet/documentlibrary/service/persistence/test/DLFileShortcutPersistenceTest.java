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

package com.liferay.portlet.documentlibrary.service.persistence.test;

import com.liferay.document.library.kernel.exception.NoSuchFileShortcutException;
import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.document.library.kernel.service.DLFileShortcutLocalServiceUtil;
import com.liferay.document.library.kernel.service.persistence.DLFileShortcutPersistence;
import com.liferay.document.library.kernel.service.persistence.DLFileShortcutUtil;

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
public class DLFileShortcutPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = DLFileShortcutUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DLFileShortcut> iterator = _dlFileShortcuts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DLFileShortcut dlFileShortcut = _persistence.create(pk);

		Assert.assertNotNull(dlFileShortcut);

		Assert.assertEquals(dlFileShortcut.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DLFileShortcut newDLFileShortcut = addDLFileShortcut();

		_persistence.remove(newDLFileShortcut);

		DLFileShortcut existingDLFileShortcut = _persistence.fetchByPrimaryKey(newDLFileShortcut.getPrimaryKey());

		Assert.assertNull(existingDLFileShortcut);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDLFileShortcut();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DLFileShortcut newDLFileShortcut = _persistence.create(pk);

		newDLFileShortcut.setUuid(RandomTestUtil.randomString());

		newDLFileShortcut.setGroupId(RandomTestUtil.nextLong());

		newDLFileShortcut.setCompanyId(RandomTestUtil.nextLong());

		newDLFileShortcut.setUserId(RandomTestUtil.nextLong());

		newDLFileShortcut.setUserName(RandomTestUtil.randomString());

		newDLFileShortcut.setCreateDate(RandomTestUtil.nextDate());

		newDLFileShortcut.setModifiedDate(RandomTestUtil.nextDate());

		newDLFileShortcut.setRepositoryId(RandomTestUtil.nextLong());

		newDLFileShortcut.setFolderId(RandomTestUtil.nextLong());

		newDLFileShortcut.setToFileEntryId(RandomTestUtil.nextLong());

		newDLFileShortcut.setTreePath(RandomTestUtil.randomString());

		newDLFileShortcut.setActive(RandomTestUtil.randomBoolean());

		newDLFileShortcut.setLastPublishDate(RandomTestUtil.nextDate());

		newDLFileShortcut.setStatus(RandomTestUtil.nextInt());

		newDLFileShortcut.setStatusByUserId(RandomTestUtil.nextLong());

		newDLFileShortcut.setStatusByUserName(RandomTestUtil.randomString());

		newDLFileShortcut.setStatusDate(RandomTestUtil.nextDate());

		_dlFileShortcuts.add(_persistence.update(newDLFileShortcut));

		DLFileShortcut existingDLFileShortcut = _persistence.findByPrimaryKey(newDLFileShortcut.getPrimaryKey());

		Assert.assertEquals(existingDLFileShortcut.getUuid(),
			newDLFileShortcut.getUuid());
		Assert.assertEquals(existingDLFileShortcut.getFileShortcutId(),
			newDLFileShortcut.getFileShortcutId());
		Assert.assertEquals(existingDLFileShortcut.getGroupId(),
			newDLFileShortcut.getGroupId());
		Assert.assertEquals(existingDLFileShortcut.getCompanyId(),
			newDLFileShortcut.getCompanyId());
		Assert.assertEquals(existingDLFileShortcut.getUserId(),
			newDLFileShortcut.getUserId());
		Assert.assertEquals(existingDLFileShortcut.getUserName(),
			newDLFileShortcut.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDLFileShortcut.getCreateDate()),
			Time.getShortTimestamp(newDLFileShortcut.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingDLFileShortcut.getModifiedDate()),
			Time.getShortTimestamp(newDLFileShortcut.getModifiedDate()));
		Assert.assertEquals(existingDLFileShortcut.getRepositoryId(),
			newDLFileShortcut.getRepositoryId());
		Assert.assertEquals(existingDLFileShortcut.getFolderId(),
			newDLFileShortcut.getFolderId());
		Assert.assertEquals(existingDLFileShortcut.getToFileEntryId(),
			newDLFileShortcut.getToFileEntryId());
		Assert.assertEquals(existingDLFileShortcut.getTreePath(),
			newDLFileShortcut.getTreePath());
		Assert.assertEquals(existingDLFileShortcut.getActive(),
			newDLFileShortcut.getActive());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDLFileShortcut.getLastPublishDate()),
			Time.getShortTimestamp(newDLFileShortcut.getLastPublishDate()));
		Assert.assertEquals(existingDLFileShortcut.getStatus(),
			newDLFileShortcut.getStatus());
		Assert.assertEquals(existingDLFileShortcut.getStatusByUserId(),
			newDLFileShortcut.getStatusByUserId());
		Assert.assertEquals(existingDLFileShortcut.getStatusByUserName(),
			newDLFileShortcut.getStatusByUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDLFileShortcut.getStatusDate()),
			Time.getShortTimestamp(newDLFileShortcut.getStatusDate()));
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
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(RandomTestUtil.nextLong());

		_persistence.countByCompanyId(0L);
	}

	@Test
	public void testCountByToFileEntryId() throws Exception {
		_persistence.countByToFileEntryId(RandomTestUtil.nextLong());

		_persistence.countByToFileEntryId(0L);
	}

	@Test
	public void testCountByG_F() throws Exception {
		_persistence.countByG_F(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_F(0L, 0L);
	}

	@Test
	public void testCountByC_NotS() throws Exception {
		_persistence.countByC_NotS(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByC_NotS(0L, 0);
	}

	@Test
	public void testCountByG_F_A() throws Exception {
		_persistence.countByG_F_A(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByG_F_A(0L, 0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByG_F_A_S() throws Exception {
		_persistence.countByG_F_A_S(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean(),
			RandomTestUtil.nextInt());

		_persistence.countByG_F_A_S(0L, 0L, RandomTestUtil.randomBoolean(), 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DLFileShortcut newDLFileShortcut = addDLFileShortcut();

		DLFileShortcut existingDLFileShortcut = _persistence.findByPrimaryKey(newDLFileShortcut.getPrimaryKey());

		Assert.assertEquals(existingDLFileShortcut, newDLFileShortcut);
	}

	@Test(expected = NoSuchFileShortcutException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<DLFileShortcut> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("DLFileShortcut", "uuid",
			true, "fileShortcutId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "repositoryId", true, "folderId", true,
			"toFileEntryId", true, "treePath", true, "active", true,
			"lastPublishDate", true, "status", true, "statusByUserId", true,
			"statusByUserName", true, "statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DLFileShortcut newDLFileShortcut = addDLFileShortcut();

		DLFileShortcut existingDLFileShortcut = _persistence.fetchByPrimaryKey(newDLFileShortcut.getPrimaryKey());

		Assert.assertEquals(existingDLFileShortcut, newDLFileShortcut);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DLFileShortcut missingDLFileShortcut = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDLFileShortcut);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		DLFileShortcut newDLFileShortcut1 = addDLFileShortcut();
		DLFileShortcut newDLFileShortcut2 = addDLFileShortcut();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDLFileShortcut1.getPrimaryKey());
		primaryKeys.add(newDLFileShortcut2.getPrimaryKey());

		Map<Serializable, DLFileShortcut> dlFileShortcuts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, dlFileShortcuts.size());
		Assert.assertEquals(newDLFileShortcut1,
			dlFileShortcuts.get(newDLFileShortcut1.getPrimaryKey()));
		Assert.assertEquals(newDLFileShortcut2,
			dlFileShortcuts.get(newDLFileShortcut2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DLFileShortcut> dlFileShortcuts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(dlFileShortcuts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		DLFileShortcut newDLFileShortcut = addDLFileShortcut();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDLFileShortcut.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DLFileShortcut> dlFileShortcuts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, dlFileShortcuts.size());
		Assert.assertEquals(newDLFileShortcut,
			dlFileShortcuts.get(newDLFileShortcut.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DLFileShortcut> dlFileShortcuts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(dlFileShortcuts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		DLFileShortcut newDLFileShortcut = addDLFileShortcut();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDLFileShortcut.getPrimaryKey());

		Map<Serializable, DLFileShortcut> dlFileShortcuts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, dlFileShortcuts.size());
		Assert.assertEquals(newDLFileShortcut,
			dlFileShortcuts.get(newDLFileShortcut.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DLFileShortcutLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DLFileShortcut>() {
				@Override
				public void performAction(DLFileShortcut dlFileShortcut) {
					Assert.assertNotNull(dlFileShortcut);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DLFileShortcut newDLFileShortcut = addDLFileShortcut();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DLFileShortcut.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("fileShortcutId",
				newDLFileShortcut.getFileShortcutId()));

		List<DLFileShortcut> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DLFileShortcut existingDLFileShortcut = result.get(0);

		Assert.assertEquals(existingDLFileShortcut, newDLFileShortcut);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DLFileShortcut.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("fileShortcutId",
				RandomTestUtil.nextLong()));

		List<DLFileShortcut> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DLFileShortcut newDLFileShortcut = addDLFileShortcut();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DLFileShortcut.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"fileShortcutId"));

		Object newFileShortcutId = newDLFileShortcut.getFileShortcutId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("fileShortcutId",
				new Object[] { newFileShortcutId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFileShortcutId = result.get(0);

		Assert.assertEquals(existingFileShortcutId, newFileShortcutId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DLFileShortcut.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"fileShortcutId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("fileShortcutId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		DLFileShortcut newDLFileShortcut = addDLFileShortcut();

		_persistence.clearCache();

		DLFileShortcut existingDLFileShortcut = _persistence.findByPrimaryKey(newDLFileShortcut.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingDLFileShortcut.getUuid(),
				ReflectionTestUtil.invoke(existingDLFileShortcut,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingDLFileShortcut.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingDLFileShortcut,
				"getOriginalGroupId", new Class<?>[0]));
	}

	protected DLFileShortcut addDLFileShortcut() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DLFileShortcut dlFileShortcut = _persistence.create(pk);

		dlFileShortcut.setUuid(RandomTestUtil.randomString());

		dlFileShortcut.setGroupId(RandomTestUtil.nextLong());

		dlFileShortcut.setCompanyId(RandomTestUtil.nextLong());

		dlFileShortcut.setUserId(RandomTestUtil.nextLong());

		dlFileShortcut.setUserName(RandomTestUtil.randomString());

		dlFileShortcut.setCreateDate(RandomTestUtil.nextDate());

		dlFileShortcut.setModifiedDate(RandomTestUtil.nextDate());

		dlFileShortcut.setRepositoryId(RandomTestUtil.nextLong());

		dlFileShortcut.setFolderId(RandomTestUtil.nextLong());

		dlFileShortcut.setToFileEntryId(RandomTestUtil.nextLong());

		dlFileShortcut.setTreePath(RandomTestUtil.randomString());

		dlFileShortcut.setActive(RandomTestUtil.randomBoolean());

		dlFileShortcut.setLastPublishDate(RandomTestUtil.nextDate());

		dlFileShortcut.setStatus(RandomTestUtil.nextInt());

		dlFileShortcut.setStatusByUserId(RandomTestUtil.nextLong());

		dlFileShortcut.setStatusByUserName(RandomTestUtil.randomString());

		dlFileShortcut.setStatusDate(RandomTestUtil.nextDate());

		_dlFileShortcuts.add(_persistence.update(dlFileShortcut));

		return dlFileShortcut;
	}

	private List<DLFileShortcut> _dlFileShortcuts = new ArrayList<DLFileShortcut>();
	private DLFileShortcutPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}