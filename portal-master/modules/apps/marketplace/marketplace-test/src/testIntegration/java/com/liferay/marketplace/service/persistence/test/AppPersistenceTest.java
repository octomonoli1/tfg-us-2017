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

package com.liferay.marketplace.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.marketplace.exception.NoSuchAppException;
import com.liferay.marketplace.model.App;
import com.liferay.marketplace.service.AppLocalServiceUtil;
import com.liferay.marketplace.service.persistence.AppPersistence;
import com.liferay.marketplace.service.persistence.AppUtil;

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
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class AppPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = AppUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<App> iterator = _apps.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		App app = _persistence.create(pk);

		Assert.assertNotNull(app);

		Assert.assertEquals(app.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		App newApp = addApp();

		_persistence.remove(newApp);

		App existingApp = _persistence.fetchByPrimaryKey(newApp.getPrimaryKey());

		Assert.assertNull(existingApp);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addApp();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		App newApp = _persistence.create(pk);

		newApp.setUuid(RandomTestUtil.randomString());

		newApp.setCompanyId(RandomTestUtil.nextLong());

		newApp.setUserId(RandomTestUtil.nextLong());

		newApp.setUserName(RandomTestUtil.randomString());

		newApp.setCreateDate(RandomTestUtil.nextDate());

		newApp.setModifiedDate(RandomTestUtil.nextDate());

		newApp.setRemoteAppId(RandomTestUtil.nextLong());

		newApp.setTitle(RandomTestUtil.randomString());

		newApp.setDescription(RandomTestUtil.randomString());

		newApp.setCategory(RandomTestUtil.randomString());

		newApp.setIconURL(RandomTestUtil.randomString());

		newApp.setVersion(RandomTestUtil.randomString());

		newApp.setRequired(RandomTestUtil.randomBoolean());

		_apps.add(_persistence.update(newApp));

		App existingApp = _persistence.findByPrimaryKey(newApp.getPrimaryKey());

		Assert.assertEquals(existingApp.getUuid(), newApp.getUuid());
		Assert.assertEquals(existingApp.getAppId(), newApp.getAppId());
		Assert.assertEquals(existingApp.getCompanyId(), newApp.getCompanyId());
		Assert.assertEquals(existingApp.getUserId(), newApp.getUserId());
		Assert.assertEquals(existingApp.getUserName(), newApp.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(existingApp.getCreateDate()),
			Time.getShortTimestamp(newApp.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingApp.getModifiedDate()),
			Time.getShortTimestamp(newApp.getModifiedDate()));
		Assert.assertEquals(existingApp.getRemoteAppId(),
			newApp.getRemoteAppId());
		Assert.assertEquals(existingApp.getTitle(), newApp.getTitle());
		Assert.assertEquals(existingApp.getDescription(),
			newApp.getDescription());
		Assert.assertEquals(existingApp.getCategory(), newApp.getCategory());
		Assert.assertEquals(existingApp.getIconURL(), newApp.getIconURL());
		Assert.assertEquals(existingApp.getVersion(), newApp.getVersion());
		Assert.assertEquals(existingApp.getRequired(), newApp.getRequired());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid(StringPool.BLANK);

		_persistence.countByUuid(StringPool.NULL);

		_persistence.countByUuid((String)null);
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
	public void testCountByRemoteAppId() throws Exception {
		_persistence.countByRemoteAppId(RandomTestUtil.nextLong());

		_persistence.countByRemoteAppId(0L);
	}

	@Test
	public void testCountByCategory() throws Exception {
		_persistence.countByCategory(StringPool.BLANK);

		_persistence.countByCategory(StringPool.NULL);

		_persistence.countByCategory((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		App newApp = addApp();

		App existingApp = _persistence.findByPrimaryKey(newApp.getPrimaryKey());

		Assert.assertEquals(existingApp, newApp);
	}

	@Test(expected = NoSuchAppException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<App> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("Marketplace_App", "uuid",
			true, "appId", true, "companyId", true, "userId", true, "userName",
			true, "createDate", true, "modifiedDate", true, "remoteAppId",
			true, "title", true, "description", true, "category", true,
			"iconURL", true, "version", true, "required", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		App newApp = addApp();

		App existingApp = _persistence.fetchByPrimaryKey(newApp.getPrimaryKey());

		Assert.assertEquals(existingApp, newApp);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		App missingApp = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingApp);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		App newApp1 = addApp();
		App newApp2 = addApp();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newApp1.getPrimaryKey());
		primaryKeys.add(newApp2.getPrimaryKey());

		Map<Serializable, App> apps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, apps.size());
		Assert.assertEquals(newApp1, apps.get(newApp1.getPrimaryKey()));
		Assert.assertEquals(newApp2, apps.get(newApp2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, App> apps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(apps.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		App newApp = addApp();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newApp.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, App> apps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, apps.size());
		Assert.assertEquals(newApp, apps.get(newApp.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, App> apps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(apps.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		App newApp = addApp();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newApp.getPrimaryKey());

		Map<Serializable, App> apps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, apps.size());
		Assert.assertEquals(newApp, apps.get(newApp.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AppLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<App>() {
				@Override
				public void performAction(App app) {
					Assert.assertNotNull(app);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		App newApp = addApp();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(App.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("appId", newApp.getAppId()));

		List<App> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		App existingApp = result.get(0);

		Assert.assertEquals(existingApp, newApp);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(App.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("appId",
				RandomTestUtil.nextLong()));

		List<App> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		App newApp = addApp();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(App.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("appId"));

		Object newAppId = newApp.getAppId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("appId",
				new Object[] { newAppId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAppId = result.get(0);

		Assert.assertEquals(existingAppId, newAppId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(App.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("appId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("appId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		App newApp = addApp();

		_persistence.clearCache();

		App existingApp = _persistence.findByPrimaryKey(newApp.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingApp.getRemoteAppId()),
			ReflectionTestUtil.<Long>invoke(existingApp,
				"getOriginalRemoteAppId", new Class<?>[0]));
	}

	protected App addApp() throws Exception {
		long pk = RandomTestUtil.nextLong();

		App app = _persistence.create(pk);

		app.setUuid(RandomTestUtil.randomString());

		app.setCompanyId(RandomTestUtil.nextLong());

		app.setUserId(RandomTestUtil.nextLong());

		app.setUserName(RandomTestUtil.randomString());

		app.setCreateDate(RandomTestUtil.nextDate());

		app.setModifiedDate(RandomTestUtil.nextDate());

		app.setRemoteAppId(RandomTestUtil.nextLong());

		app.setTitle(RandomTestUtil.randomString());

		app.setDescription(RandomTestUtil.randomString());

		app.setCategory(RandomTestUtil.randomString());

		app.setIconURL(RandomTestUtil.randomString());

		app.setVersion(RandomTestUtil.randomString());

		app.setRequired(RandomTestUtil.randomBoolean());

		_apps.add(_persistence.update(app));

		return app;
	}

	private List<App> _apps = new ArrayList<App>();
	private AppPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}