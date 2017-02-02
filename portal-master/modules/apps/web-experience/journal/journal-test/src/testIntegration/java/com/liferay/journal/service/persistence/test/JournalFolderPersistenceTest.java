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

package com.liferay.journal.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.journal.exception.NoSuchFolderException;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.journal.service.persistence.JournalFolderPersistence;
import com.liferay.journal.service.persistence.JournalFolderUtil;

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
public class JournalFolderPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = JournalFolderUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<JournalFolder> iterator = _journalFolders.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		JournalFolder journalFolder = _persistence.create(pk);

		Assert.assertNotNull(journalFolder);

		Assert.assertEquals(journalFolder.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		JournalFolder newJournalFolder = addJournalFolder();

		_persistence.remove(newJournalFolder);

		JournalFolder existingJournalFolder = _persistence.fetchByPrimaryKey(newJournalFolder.getPrimaryKey());

		Assert.assertNull(existingJournalFolder);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addJournalFolder();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		JournalFolder newJournalFolder = _persistence.create(pk);

		newJournalFolder.setUuid(RandomTestUtil.randomString());

		newJournalFolder.setGroupId(RandomTestUtil.nextLong());

		newJournalFolder.setCompanyId(RandomTestUtil.nextLong());

		newJournalFolder.setUserId(RandomTestUtil.nextLong());

		newJournalFolder.setUserName(RandomTestUtil.randomString());

		newJournalFolder.setCreateDate(RandomTestUtil.nextDate());

		newJournalFolder.setModifiedDate(RandomTestUtil.nextDate());

		newJournalFolder.setParentFolderId(RandomTestUtil.nextLong());

		newJournalFolder.setTreePath(RandomTestUtil.randomString());

		newJournalFolder.setName(RandomTestUtil.randomString());

		newJournalFolder.setDescription(RandomTestUtil.randomString());

		newJournalFolder.setRestrictionType(RandomTestUtil.nextInt());

		newJournalFolder.setLastPublishDate(RandomTestUtil.nextDate());

		newJournalFolder.setStatus(RandomTestUtil.nextInt());

		newJournalFolder.setStatusByUserId(RandomTestUtil.nextLong());

		newJournalFolder.setStatusByUserName(RandomTestUtil.randomString());

		newJournalFolder.setStatusDate(RandomTestUtil.nextDate());

		_journalFolders.add(_persistence.update(newJournalFolder));

		JournalFolder existingJournalFolder = _persistence.findByPrimaryKey(newJournalFolder.getPrimaryKey());

		Assert.assertEquals(existingJournalFolder.getUuid(),
			newJournalFolder.getUuid());
		Assert.assertEquals(existingJournalFolder.getFolderId(),
			newJournalFolder.getFolderId());
		Assert.assertEquals(existingJournalFolder.getGroupId(),
			newJournalFolder.getGroupId());
		Assert.assertEquals(existingJournalFolder.getCompanyId(),
			newJournalFolder.getCompanyId());
		Assert.assertEquals(existingJournalFolder.getUserId(),
			newJournalFolder.getUserId());
		Assert.assertEquals(existingJournalFolder.getUserName(),
			newJournalFolder.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingJournalFolder.getCreateDate()),
			Time.getShortTimestamp(newJournalFolder.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingJournalFolder.getModifiedDate()),
			Time.getShortTimestamp(newJournalFolder.getModifiedDate()));
		Assert.assertEquals(existingJournalFolder.getParentFolderId(),
			newJournalFolder.getParentFolderId());
		Assert.assertEquals(existingJournalFolder.getTreePath(),
			newJournalFolder.getTreePath());
		Assert.assertEquals(existingJournalFolder.getName(),
			newJournalFolder.getName());
		Assert.assertEquals(existingJournalFolder.getDescription(),
			newJournalFolder.getDescription());
		Assert.assertEquals(existingJournalFolder.getRestrictionType(),
			newJournalFolder.getRestrictionType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingJournalFolder.getLastPublishDate()),
			Time.getShortTimestamp(newJournalFolder.getLastPublishDate()));
		Assert.assertEquals(existingJournalFolder.getStatus(),
			newJournalFolder.getStatus());
		Assert.assertEquals(existingJournalFolder.getStatusByUserId(),
			newJournalFolder.getStatusByUserId());
		Assert.assertEquals(existingJournalFolder.getStatusByUserName(),
			newJournalFolder.getStatusByUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingJournalFolder.getStatusDate()),
			Time.getShortTimestamp(newJournalFolder.getStatusDate()));
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
	public void testCountByG_P() throws Exception {
		_persistence.countByG_P(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_P(0L, 0L);
	}

	@Test
	public void testCountByG_N() throws Exception {
		_persistence.countByG_N(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByG_N(0L, StringPool.NULL);

		_persistence.countByG_N(0L, (String)null);
	}

	@Test
	public void testCountByC_NotS() throws Exception {
		_persistence.countByC_NotS(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByC_NotS(0L, 0);
	}

	@Test
	public void testCountByG_P_N() throws Exception {
		_persistence.countByG_P_N(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByG_P_N(0L, 0L, StringPool.NULL);

		_persistence.countByG_P_N(0L, 0L, (String)null);
	}

	@Test
	public void testCountByG_P_S() throws Exception {
		_persistence.countByG_P_S(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByG_P_S(0L, 0L, 0);
	}

	@Test
	public void testCountByG_P_NotS() throws Exception {
		_persistence.countByG_P_NotS(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByG_P_NotS(0L, 0L, 0);
	}

	@Test
	public void testCountByF_C_P_NotS() throws Exception {
		_persistence.countByF_C_P_NotS(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByF_C_P_NotS(0L, 0L, 0L, 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		JournalFolder newJournalFolder = addJournalFolder();

		JournalFolder existingJournalFolder = _persistence.findByPrimaryKey(newJournalFolder.getPrimaryKey());

		Assert.assertEquals(existingJournalFolder, newJournalFolder);
	}

	@Test(expected = NoSuchFolderException.class)
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

	protected OrderByComparator<JournalFolder> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("JournalFolder", "uuid",
			true, "folderId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "parentFolderId", true, "treePath", true,
			"name", true, "description", true, "restrictionType", true,
			"lastPublishDate", true, "status", true, "statusByUserId", true,
			"statusByUserName", true, "statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		JournalFolder newJournalFolder = addJournalFolder();

		JournalFolder existingJournalFolder = _persistence.fetchByPrimaryKey(newJournalFolder.getPrimaryKey());

		Assert.assertEquals(existingJournalFolder, newJournalFolder);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		JournalFolder missingJournalFolder = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingJournalFolder);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		JournalFolder newJournalFolder1 = addJournalFolder();
		JournalFolder newJournalFolder2 = addJournalFolder();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newJournalFolder1.getPrimaryKey());
		primaryKeys.add(newJournalFolder2.getPrimaryKey());

		Map<Serializable, JournalFolder> journalFolders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, journalFolders.size());
		Assert.assertEquals(newJournalFolder1,
			journalFolders.get(newJournalFolder1.getPrimaryKey()));
		Assert.assertEquals(newJournalFolder2,
			journalFolders.get(newJournalFolder2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, JournalFolder> journalFolders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(journalFolders.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		JournalFolder newJournalFolder = addJournalFolder();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newJournalFolder.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, JournalFolder> journalFolders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, journalFolders.size());
		Assert.assertEquals(newJournalFolder,
			journalFolders.get(newJournalFolder.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, JournalFolder> journalFolders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(journalFolders.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		JournalFolder newJournalFolder = addJournalFolder();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newJournalFolder.getPrimaryKey());

		Map<Serializable, JournalFolder> journalFolders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, journalFolders.size());
		Assert.assertEquals(newJournalFolder,
			journalFolders.get(newJournalFolder.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = JournalFolderLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<JournalFolder>() {
				@Override
				public void performAction(JournalFolder journalFolder) {
					Assert.assertNotNull(journalFolder);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		JournalFolder newJournalFolder = addJournalFolder();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalFolder.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("folderId",
				newJournalFolder.getFolderId()));

		List<JournalFolder> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		JournalFolder existingJournalFolder = result.get(0);

		Assert.assertEquals(existingJournalFolder, newJournalFolder);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalFolder.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("folderId",
				RandomTestUtil.nextLong()));

		List<JournalFolder> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		JournalFolder newJournalFolder = addJournalFolder();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalFolder.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("folderId"));

		Object newFolderId = newJournalFolder.getFolderId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("folderId",
				new Object[] { newFolderId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFolderId = result.get(0);

		Assert.assertEquals(existingFolderId, newFolderId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalFolder.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("folderId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("folderId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		JournalFolder newJournalFolder = addJournalFolder();

		_persistence.clearCache();

		JournalFolder existingJournalFolder = _persistence.findByPrimaryKey(newJournalFolder.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingJournalFolder.getUuid(),
				ReflectionTestUtil.invoke(existingJournalFolder,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingJournalFolder.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingJournalFolder,
				"getOriginalGroupId", new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(existingJournalFolder.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingJournalFolder,
				"getOriginalGroupId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingJournalFolder.getName(),
				ReflectionTestUtil.invoke(existingJournalFolder,
					"getOriginalName", new Class<?>[0])));

		Assert.assertEquals(Long.valueOf(existingJournalFolder.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingJournalFolder,
				"getOriginalGroupId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingJournalFolder.getParentFolderId()),
			ReflectionTestUtil.<Long>invoke(existingJournalFolder,
				"getOriginalParentFolderId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingJournalFolder.getName(),
				ReflectionTestUtil.invoke(existingJournalFolder,
					"getOriginalName", new Class<?>[0])));
	}

	protected JournalFolder addJournalFolder() throws Exception {
		long pk = RandomTestUtil.nextLong();

		JournalFolder journalFolder = _persistence.create(pk);

		journalFolder.setUuid(RandomTestUtil.randomString());

		journalFolder.setGroupId(RandomTestUtil.nextLong());

		journalFolder.setCompanyId(RandomTestUtil.nextLong());

		journalFolder.setUserId(RandomTestUtil.nextLong());

		journalFolder.setUserName(RandomTestUtil.randomString());

		journalFolder.setCreateDate(RandomTestUtil.nextDate());

		journalFolder.setModifiedDate(RandomTestUtil.nextDate());

		journalFolder.setParentFolderId(RandomTestUtil.nextLong());

		journalFolder.setTreePath(RandomTestUtil.randomString());

		journalFolder.setName(RandomTestUtil.randomString());

		journalFolder.setDescription(RandomTestUtil.randomString());

		journalFolder.setRestrictionType(RandomTestUtil.nextInt());

		journalFolder.setLastPublishDate(RandomTestUtil.nextDate());

		journalFolder.setStatus(RandomTestUtil.nextInt());

		journalFolder.setStatusByUserId(RandomTestUtil.nextLong());

		journalFolder.setStatusByUserName(RandomTestUtil.randomString());

		journalFolder.setStatusDate(RandomTestUtil.nextDate());

		_journalFolders.add(_persistence.update(journalFolder));

		return journalFolder;
	}

	private List<JournalFolder> _journalFolders = new ArrayList<JournalFolder>();
	private JournalFolderPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}