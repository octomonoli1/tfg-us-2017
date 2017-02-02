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

package com.liferay.portal.service.persistence.test;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.NoSuchRepositoryException;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.service.RepositoryLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.RepositoryPersistence;
import com.liferay.portal.kernel.service.persistence.RepositoryUtil;
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
public class RepositoryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = RepositoryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Repository> iterator = _repositories.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Repository repository = _persistence.create(pk);

		Assert.assertNotNull(repository);

		Assert.assertEquals(repository.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Repository newRepository = addRepository();

		_persistence.remove(newRepository);

		Repository existingRepository = _persistence.fetchByPrimaryKey(newRepository.getPrimaryKey());

		Assert.assertNull(existingRepository);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRepository();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Repository newRepository = _persistence.create(pk);

		newRepository.setMvccVersion(RandomTestUtil.nextLong());

		newRepository.setUuid(RandomTestUtil.randomString());

		newRepository.setGroupId(RandomTestUtil.nextLong());

		newRepository.setCompanyId(RandomTestUtil.nextLong());

		newRepository.setUserId(RandomTestUtil.nextLong());

		newRepository.setUserName(RandomTestUtil.randomString());

		newRepository.setCreateDate(RandomTestUtil.nextDate());

		newRepository.setModifiedDate(RandomTestUtil.nextDate());

		newRepository.setClassNameId(RandomTestUtil.nextLong());

		newRepository.setName(RandomTestUtil.randomString());

		newRepository.setDescription(RandomTestUtil.randomString());

		newRepository.setPortletId(RandomTestUtil.randomString());

		newRepository.setTypeSettings(RandomTestUtil.randomString());

		newRepository.setDlFolderId(RandomTestUtil.nextLong());

		newRepository.setLastPublishDate(RandomTestUtil.nextDate());

		_repositories.add(_persistence.update(newRepository));

		Repository existingRepository = _persistence.findByPrimaryKey(newRepository.getPrimaryKey());

		Assert.assertEquals(existingRepository.getMvccVersion(),
			newRepository.getMvccVersion());
		Assert.assertEquals(existingRepository.getUuid(),
			newRepository.getUuid());
		Assert.assertEquals(existingRepository.getRepositoryId(),
			newRepository.getRepositoryId());
		Assert.assertEquals(existingRepository.getGroupId(),
			newRepository.getGroupId());
		Assert.assertEquals(existingRepository.getCompanyId(),
			newRepository.getCompanyId());
		Assert.assertEquals(existingRepository.getUserId(),
			newRepository.getUserId());
		Assert.assertEquals(existingRepository.getUserName(),
			newRepository.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRepository.getCreateDate()),
			Time.getShortTimestamp(newRepository.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingRepository.getModifiedDate()),
			Time.getShortTimestamp(newRepository.getModifiedDate()));
		Assert.assertEquals(existingRepository.getClassNameId(),
			newRepository.getClassNameId());
		Assert.assertEquals(existingRepository.getName(),
			newRepository.getName());
		Assert.assertEquals(existingRepository.getDescription(),
			newRepository.getDescription());
		Assert.assertEquals(existingRepository.getPortletId(),
			newRepository.getPortletId());
		Assert.assertEquals(existingRepository.getTypeSettings(),
			newRepository.getTypeSettings());
		Assert.assertEquals(existingRepository.getDlFolderId(),
			newRepository.getDlFolderId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRepository.getLastPublishDate()),
			Time.getShortTimestamp(newRepository.getLastPublishDate()));
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
	public void testCountByG_N_P() throws Exception {
		_persistence.countByG_N_P(RandomTestUtil.nextLong(), StringPool.BLANK,
			StringPool.BLANK);

		_persistence.countByG_N_P(0L, StringPool.NULL, StringPool.NULL);

		_persistence.countByG_N_P(0L, (String)null, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Repository newRepository = addRepository();

		Repository existingRepository = _persistence.findByPrimaryKey(newRepository.getPrimaryKey());

		Assert.assertEquals(existingRepository, newRepository);
	}

	@Test(expected = NoSuchRepositoryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Repository> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("Repository", "mvccVersion",
			true, "uuid", true, "repositoryId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "classNameId", true, "name", true,
			"description", true, "portletId", true, "dlFolderId", true,
			"lastPublishDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Repository newRepository = addRepository();

		Repository existingRepository = _persistence.fetchByPrimaryKey(newRepository.getPrimaryKey());

		Assert.assertEquals(existingRepository, newRepository);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Repository missingRepository = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRepository);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Repository newRepository1 = addRepository();
		Repository newRepository2 = addRepository();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRepository1.getPrimaryKey());
		primaryKeys.add(newRepository2.getPrimaryKey());

		Map<Serializable, Repository> repositories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, repositories.size());
		Assert.assertEquals(newRepository1,
			repositories.get(newRepository1.getPrimaryKey()));
		Assert.assertEquals(newRepository2,
			repositories.get(newRepository2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Repository> repositories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(repositories.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Repository newRepository = addRepository();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRepository.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Repository> repositories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, repositories.size());
		Assert.assertEquals(newRepository,
			repositories.get(newRepository.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Repository> repositories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(repositories.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Repository newRepository = addRepository();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRepository.getPrimaryKey());

		Map<Serializable, Repository> repositories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, repositories.size());
		Assert.assertEquals(newRepository,
			repositories.get(newRepository.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RepositoryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Repository>() {
				@Override
				public void performAction(Repository repository) {
					Assert.assertNotNull(repository);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Repository newRepository = addRepository();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Repository.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("repositoryId",
				newRepository.getRepositoryId()));

		List<Repository> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Repository existingRepository = result.get(0);

		Assert.assertEquals(existingRepository, newRepository);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Repository.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("repositoryId",
				RandomTestUtil.nextLong()));

		List<Repository> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Repository newRepository = addRepository();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Repository.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"repositoryId"));

		Object newRepositoryId = newRepository.getRepositoryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("repositoryId",
				new Object[] { newRepositoryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRepositoryId = result.get(0);

		Assert.assertEquals(existingRepositoryId, newRepositoryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Repository.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"repositoryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("repositoryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Repository newRepository = addRepository();

		_persistence.clearCache();

		Repository existingRepository = _persistence.findByPrimaryKey(newRepository.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingRepository.getUuid(),
				ReflectionTestUtil.invoke(existingRepository,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingRepository.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingRepository,
				"getOriginalGroupId", new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(existingRepository.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingRepository,
				"getOriginalGroupId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingRepository.getName(),
				ReflectionTestUtil.invoke(existingRepository,
					"getOriginalName", new Class<?>[0])));
		Assert.assertTrue(Objects.equals(existingRepository.getPortletId(),
				ReflectionTestUtil.invoke(existingRepository,
					"getOriginalPortletId", new Class<?>[0])));
	}

	protected Repository addRepository() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Repository repository = _persistence.create(pk);

		repository.setMvccVersion(RandomTestUtil.nextLong());

		repository.setUuid(RandomTestUtil.randomString());

		repository.setGroupId(RandomTestUtil.nextLong());

		repository.setCompanyId(RandomTestUtil.nextLong());

		repository.setUserId(RandomTestUtil.nextLong());

		repository.setUserName(RandomTestUtil.randomString());

		repository.setCreateDate(RandomTestUtil.nextDate());

		repository.setModifiedDate(RandomTestUtil.nextDate());

		repository.setClassNameId(RandomTestUtil.nextLong());

		repository.setName(RandomTestUtil.randomString());

		repository.setDescription(RandomTestUtil.randomString());

		repository.setPortletId(RandomTestUtil.randomString());

		repository.setTypeSettings(RandomTestUtil.randomString());

		repository.setDlFolderId(RandomTestUtil.nextLong());

		repository.setLastPublishDate(RandomTestUtil.nextDate());

		_repositories.add(_persistence.update(repository));

		return repository;
	}

	private List<Repository> _repositories = new ArrayList<Repository>();
	private RepositoryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}