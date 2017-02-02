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

package com.liferay.calendar.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.calendar.exception.NoSuchResourceException;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.service.persistence.CalendarResourcePersistence;
import com.liferay.calendar.service.persistence.CalendarResourceUtil;

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
public class CalendarResourcePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = CalendarResourceUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CalendarResource> iterator = _calendarResources.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CalendarResource calendarResource = _persistence.create(pk);

		Assert.assertNotNull(calendarResource);

		Assert.assertEquals(calendarResource.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CalendarResource newCalendarResource = addCalendarResource();

		_persistence.remove(newCalendarResource);

		CalendarResource existingCalendarResource = _persistence.fetchByPrimaryKey(newCalendarResource.getPrimaryKey());

		Assert.assertNull(existingCalendarResource);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCalendarResource();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CalendarResource newCalendarResource = _persistence.create(pk);

		newCalendarResource.setUuid(RandomTestUtil.randomString());

		newCalendarResource.setGroupId(RandomTestUtil.nextLong());

		newCalendarResource.setCompanyId(RandomTestUtil.nextLong());

		newCalendarResource.setUserId(RandomTestUtil.nextLong());

		newCalendarResource.setUserName(RandomTestUtil.randomString());

		newCalendarResource.setCreateDate(RandomTestUtil.nextDate());

		newCalendarResource.setModifiedDate(RandomTestUtil.nextDate());

		newCalendarResource.setResourceBlockId(RandomTestUtil.nextLong());

		newCalendarResource.setClassNameId(RandomTestUtil.nextLong());

		newCalendarResource.setClassPK(RandomTestUtil.nextLong());

		newCalendarResource.setClassUuid(RandomTestUtil.randomString());

		newCalendarResource.setCode(RandomTestUtil.randomString());

		newCalendarResource.setName(RandomTestUtil.randomString());

		newCalendarResource.setDescription(RandomTestUtil.randomString());

		newCalendarResource.setActive(RandomTestUtil.randomBoolean());

		newCalendarResource.setLastPublishDate(RandomTestUtil.nextDate());

		_calendarResources.add(_persistence.update(newCalendarResource));

		CalendarResource existingCalendarResource = _persistence.findByPrimaryKey(newCalendarResource.getPrimaryKey());

		Assert.assertEquals(existingCalendarResource.getUuid(),
			newCalendarResource.getUuid());
		Assert.assertEquals(existingCalendarResource.getCalendarResourceId(),
			newCalendarResource.getCalendarResourceId());
		Assert.assertEquals(existingCalendarResource.getGroupId(),
			newCalendarResource.getGroupId());
		Assert.assertEquals(existingCalendarResource.getCompanyId(),
			newCalendarResource.getCompanyId());
		Assert.assertEquals(existingCalendarResource.getUserId(),
			newCalendarResource.getUserId());
		Assert.assertEquals(existingCalendarResource.getUserName(),
			newCalendarResource.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCalendarResource.getCreateDate()),
			Time.getShortTimestamp(newCalendarResource.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCalendarResource.getModifiedDate()),
			Time.getShortTimestamp(newCalendarResource.getModifiedDate()));
		Assert.assertEquals(existingCalendarResource.getResourceBlockId(),
			newCalendarResource.getResourceBlockId());
		Assert.assertEquals(existingCalendarResource.getClassNameId(),
			newCalendarResource.getClassNameId());
		Assert.assertEquals(existingCalendarResource.getClassPK(),
			newCalendarResource.getClassPK());
		Assert.assertEquals(existingCalendarResource.getClassUuid(),
			newCalendarResource.getClassUuid());
		Assert.assertEquals(existingCalendarResource.getCode(),
			newCalendarResource.getCode());
		Assert.assertEquals(existingCalendarResource.getName(),
			newCalendarResource.getName());
		Assert.assertEquals(existingCalendarResource.getDescription(),
			newCalendarResource.getDescription());
		Assert.assertEquals(existingCalendarResource.getActive(),
			newCalendarResource.getActive());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCalendarResource.getLastPublishDate()),
			Time.getShortTimestamp(newCalendarResource.getLastPublishDate()));
	}

	@Test
	public void testCountByResourceBlockId() throws Exception {
		_persistence.countByResourceBlockId(RandomTestUtil.nextLong());

		_persistence.countByResourceBlockId(0L);
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
	public void testCountByActive() throws Exception {
		_persistence.countByActive(RandomTestUtil.randomBoolean());

		_persistence.countByActive(RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByG_C() throws Exception {
		_persistence.countByG_C(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByG_C(0L, StringPool.NULL);

		_persistence.countByG_C(0L, (String)null);
	}

	@Test
	public void testCountByG_CArrayable() throws Exception {
		_persistence.countByG_C(new long[] { RandomTestUtil.nextLong(), 0L },
			RandomTestUtil.randomString());
	}

	@Test
	public void testCountByG_A() throws Exception {
		_persistence.countByG_A(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByG_A(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testCountByC_C_A() throws Exception {
		_persistence.countByC_C_A(RandomTestUtil.nextLong(), StringPool.BLANK,
			RandomTestUtil.randomBoolean());

		_persistence.countByC_C_A(0L, StringPool.NULL,
			RandomTestUtil.randomBoolean());

		_persistence.countByC_C_A(0L, (String)null,
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CalendarResource newCalendarResource = addCalendarResource();

		CalendarResource existingCalendarResource = _persistence.findByPrimaryKey(newCalendarResource.getPrimaryKey());

		Assert.assertEquals(existingCalendarResource, newCalendarResource);
	}

	@Test(expected = NoSuchResourceException.class)
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

	protected OrderByComparator<CalendarResource> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CalendarResource", "uuid",
			true, "calendarResourceId", true, "groupId", true, "companyId",
			true, "userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "resourceBlockId", true, "classNameId", true,
			"classPK", true, "classUuid", true, "code", true, "name", true,
			"description", true, "active", true, "lastPublishDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CalendarResource newCalendarResource = addCalendarResource();

		CalendarResource existingCalendarResource = _persistence.fetchByPrimaryKey(newCalendarResource.getPrimaryKey());

		Assert.assertEquals(existingCalendarResource, newCalendarResource);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CalendarResource missingCalendarResource = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCalendarResource);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CalendarResource newCalendarResource1 = addCalendarResource();
		CalendarResource newCalendarResource2 = addCalendarResource();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCalendarResource1.getPrimaryKey());
		primaryKeys.add(newCalendarResource2.getPrimaryKey());

		Map<Serializable, CalendarResource> calendarResources = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, calendarResources.size());
		Assert.assertEquals(newCalendarResource1,
			calendarResources.get(newCalendarResource1.getPrimaryKey()));
		Assert.assertEquals(newCalendarResource2,
			calendarResources.get(newCalendarResource2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CalendarResource> calendarResources = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(calendarResources.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CalendarResource newCalendarResource = addCalendarResource();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCalendarResource.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CalendarResource> calendarResources = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, calendarResources.size());
		Assert.assertEquals(newCalendarResource,
			calendarResources.get(newCalendarResource.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CalendarResource> calendarResources = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(calendarResources.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CalendarResource newCalendarResource = addCalendarResource();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCalendarResource.getPrimaryKey());

		Map<Serializable, CalendarResource> calendarResources = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, calendarResources.size());
		Assert.assertEquals(newCalendarResource,
			calendarResources.get(newCalendarResource.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CalendarResourceLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CalendarResource>() {
				@Override
				public void performAction(CalendarResource calendarResource) {
					Assert.assertNotNull(calendarResource);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CalendarResource newCalendarResource = addCalendarResource();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CalendarResource.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("calendarResourceId",
				newCalendarResource.getCalendarResourceId()));

		List<CalendarResource> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CalendarResource existingCalendarResource = result.get(0);

		Assert.assertEquals(existingCalendarResource, newCalendarResource);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CalendarResource.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("calendarResourceId",
				RandomTestUtil.nextLong()));

		List<CalendarResource> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CalendarResource newCalendarResource = addCalendarResource();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CalendarResource.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"calendarResourceId"));

		Object newCalendarResourceId = newCalendarResource.getCalendarResourceId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("calendarResourceId",
				new Object[] { newCalendarResourceId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCalendarResourceId = result.get(0);

		Assert.assertEquals(existingCalendarResourceId, newCalendarResourceId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CalendarResource.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"calendarResourceId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("calendarResourceId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CalendarResource newCalendarResource = addCalendarResource();

		_persistence.clearCache();

		CalendarResource existingCalendarResource = _persistence.findByPrimaryKey(newCalendarResource.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingCalendarResource.getUuid(),
				ReflectionTestUtil.invoke(existingCalendarResource,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingCalendarResource.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingCalendarResource,
				"getOriginalGroupId", new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(
				existingCalendarResource.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingCalendarResource,
				"getOriginalClassNameId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingCalendarResource.getClassPK()),
			ReflectionTestUtil.<Long>invoke(existingCalendarResource,
				"getOriginalClassPK", new Class<?>[0]));
	}

	protected CalendarResource addCalendarResource() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CalendarResource calendarResource = _persistence.create(pk);

		calendarResource.setUuid(RandomTestUtil.randomString());

		calendarResource.setGroupId(RandomTestUtil.nextLong());

		calendarResource.setCompanyId(RandomTestUtil.nextLong());

		calendarResource.setUserId(RandomTestUtil.nextLong());

		calendarResource.setUserName(RandomTestUtil.randomString());

		calendarResource.setCreateDate(RandomTestUtil.nextDate());

		calendarResource.setModifiedDate(RandomTestUtil.nextDate());

		calendarResource.setResourceBlockId(RandomTestUtil.nextLong());

		calendarResource.setClassNameId(RandomTestUtil.nextLong());

		calendarResource.setClassPK(RandomTestUtil.nextLong());

		calendarResource.setClassUuid(RandomTestUtil.randomString());

		calendarResource.setCode(RandomTestUtil.randomString());

		calendarResource.setName(RandomTestUtil.randomString());

		calendarResource.setDescription(RandomTestUtil.randomString());

		calendarResource.setActive(RandomTestUtil.randomBoolean());

		calendarResource.setLastPublishDate(RandomTestUtil.nextDate());

		_calendarResources.add(_persistence.update(calendarResource));

		return calendarResource;
	}

	private List<CalendarResource> _calendarResources = new ArrayList<CalendarResource>();
	private CalendarResourcePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}