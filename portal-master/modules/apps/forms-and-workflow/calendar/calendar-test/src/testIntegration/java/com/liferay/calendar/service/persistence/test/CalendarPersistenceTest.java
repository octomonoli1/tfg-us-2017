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

import com.liferay.calendar.exception.NoSuchCalendarException;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.service.persistence.CalendarPersistence;
import com.liferay.calendar.service.persistence.CalendarUtil;

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
public class CalendarPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = CalendarUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Calendar> iterator = _calendars.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Calendar calendar = _persistence.create(pk);

		Assert.assertNotNull(calendar);

		Assert.assertEquals(calendar.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Calendar newCalendar = addCalendar();

		_persistence.remove(newCalendar);

		Calendar existingCalendar = _persistence.fetchByPrimaryKey(newCalendar.getPrimaryKey());

		Assert.assertNull(existingCalendar);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCalendar();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Calendar newCalendar = _persistence.create(pk);

		newCalendar.setUuid(RandomTestUtil.randomString());

		newCalendar.setGroupId(RandomTestUtil.nextLong());

		newCalendar.setCompanyId(RandomTestUtil.nextLong());

		newCalendar.setUserId(RandomTestUtil.nextLong());

		newCalendar.setUserName(RandomTestUtil.randomString());

		newCalendar.setCreateDate(RandomTestUtil.nextDate());

		newCalendar.setModifiedDate(RandomTestUtil.nextDate());

		newCalendar.setResourceBlockId(RandomTestUtil.nextLong());

		newCalendar.setCalendarResourceId(RandomTestUtil.nextLong());

		newCalendar.setName(RandomTestUtil.randomString());

		newCalendar.setDescription(RandomTestUtil.randomString());

		newCalendar.setTimeZoneId(RandomTestUtil.randomString());

		newCalendar.setColor(RandomTestUtil.nextInt());

		newCalendar.setDefaultCalendar(RandomTestUtil.randomBoolean());

		newCalendar.setEnableComments(RandomTestUtil.randomBoolean());

		newCalendar.setEnableRatings(RandomTestUtil.randomBoolean());

		newCalendar.setLastPublishDate(RandomTestUtil.nextDate());

		_calendars.add(_persistence.update(newCalendar));

		Calendar existingCalendar = _persistence.findByPrimaryKey(newCalendar.getPrimaryKey());

		Assert.assertEquals(existingCalendar.getUuid(), newCalendar.getUuid());
		Assert.assertEquals(existingCalendar.getCalendarId(),
			newCalendar.getCalendarId());
		Assert.assertEquals(existingCalendar.getGroupId(),
			newCalendar.getGroupId());
		Assert.assertEquals(existingCalendar.getCompanyId(),
			newCalendar.getCompanyId());
		Assert.assertEquals(existingCalendar.getUserId(),
			newCalendar.getUserId());
		Assert.assertEquals(existingCalendar.getUserName(),
			newCalendar.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCalendar.getCreateDate()),
			Time.getShortTimestamp(newCalendar.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCalendar.getModifiedDate()),
			Time.getShortTimestamp(newCalendar.getModifiedDate()));
		Assert.assertEquals(existingCalendar.getResourceBlockId(),
			newCalendar.getResourceBlockId());
		Assert.assertEquals(existingCalendar.getCalendarResourceId(),
			newCalendar.getCalendarResourceId());
		Assert.assertEquals(existingCalendar.getName(), newCalendar.getName());
		Assert.assertEquals(existingCalendar.getDescription(),
			newCalendar.getDescription());
		Assert.assertEquals(existingCalendar.getTimeZoneId(),
			newCalendar.getTimeZoneId());
		Assert.assertEquals(existingCalendar.getColor(), newCalendar.getColor());
		Assert.assertEquals(existingCalendar.getDefaultCalendar(),
			newCalendar.getDefaultCalendar());
		Assert.assertEquals(existingCalendar.getEnableComments(),
			newCalendar.getEnableComments());
		Assert.assertEquals(existingCalendar.getEnableRatings(),
			newCalendar.getEnableRatings());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCalendar.getLastPublishDate()),
			Time.getShortTimestamp(newCalendar.getLastPublishDate()));
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
	public void testCountByG_C() throws Exception {
		_persistence.countByG_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_C(0L, 0L);
	}

	@Test
	public void testCountByG_C_D() throws Exception {
		_persistence.countByG_C_D(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByG_C_D(0L, 0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Calendar newCalendar = addCalendar();

		Calendar existingCalendar = _persistence.findByPrimaryKey(newCalendar.getPrimaryKey());

		Assert.assertEquals(existingCalendar, newCalendar);
	}

	@Test(expected = NoSuchCalendarException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Calendar> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("Calendar", "uuid", true,
			"calendarId", true, "groupId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"resourceBlockId", true, "calendarResourceId", true, "name", true,
			"description", true, "timeZoneId", true, "color", true,
			"defaultCalendar", true, "enableComments", true, "enableRatings",
			true, "lastPublishDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Calendar newCalendar = addCalendar();

		Calendar existingCalendar = _persistence.fetchByPrimaryKey(newCalendar.getPrimaryKey());

		Assert.assertEquals(existingCalendar, newCalendar);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Calendar missingCalendar = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCalendar);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Calendar newCalendar1 = addCalendar();
		Calendar newCalendar2 = addCalendar();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCalendar1.getPrimaryKey());
		primaryKeys.add(newCalendar2.getPrimaryKey());

		Map<Serializable, Calendar> calendars = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, calendars.size());
		Assert.assertEquals(newCalendar1,
			calendars.get(newCalendar1.getPrimaryKey()));
		Assert.assertEquals(newCalendar2,
			calendars.get(newCalendar2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Calendar> calendars = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(calendars.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Calendar newCalendar = addCalendar();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCalendar.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Calendar> calendars = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, calendars.size());
		Assert.assertEquals(newCalendar,
			calendars.get(newCalendar.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Calendar> calendars = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(calendars.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Calendar newCalendar = addCalendar();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCalendar.getPrimaryKey());

		Map<Serializable, Calendar> calendars = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, calendars.size());
		Assert.assertEquals(newCalendar,
			calendars.get(newCalendar.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CalendarLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Calendar>() {
				@Override
				public void performAction(Calendar calendar) {
					Assert.assertNotNull(calendar);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Calendar newCalendar = addCalendar();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Calendar.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("calendarId",
				newCalendar.getCalendarId()));

		List<Calendar> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Calendar existingCalendar = result.get(0);

		Assert.assertEquals(existingCalendar, newCalendar);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Calendar.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("calendarId",
				RandomTestUtil.nextLong()));

		List<Calendar> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Calendar newCalendar = addCalendar();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Calendar.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("calendarId"));

		Object newCalendarId = newCalendar.getCalendarId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("calendarId",
				new Object[] { newCalendarId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCalendarId = result.get(0);

		Assert.assertEquals(existingCalendarId, newCalendarId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Calendar.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("calendarId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("calendarId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Calendar newCalendar = addCalendar();

		_persistence.clearCache();

		Calendar existingCalendar = _persistence.findByPrimaryKey(newCalendar.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingCalendar.getUuid(),
				ReflectionTestUtil.invoke(existingCalendar, "getOriginalUuid",
					new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingCalendar.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingCalendar,
				"getOriginalGroupId", new Class<?>[0]));
	}

	protected Calendar addCalendar() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Calendar calendar = _persistence.create(pk);

		calendar.setUuid(RandomTestUtil.randomString());

		calendar.setGroupId(RandomTestUtil.nextLong());

		calendar.setCompanyId(RandomTestUtil.nextLong());

		calendar.setUserId(RandomTestUtil.nextLong());

		calendar.setUserName(RandomTestUtil.randomString());

		calendar.setCreateDate(RandomTestUtil.nextDate());

		calendar.setModifiedDate(RandomTestUtil.nextDate());

		calendar.setResourceBlockId(RandomTestUtil.nextLong());

		calendar.setCalendarResourceId(RandomTestUtil.nextLong());

		calendar.setName(RandomTestUtil.randomString());

		calendar.setDescription(RandomTestUtil.randomString());

		calendar.setTimeZoneId(RandomTestUtil.randomString());

		calendar.setColor(RandomTestUtil.nextInt());

		calendar.setDefaultCalendar(RandomTestUtil.randomBoolean());

		calendar.setEnableComments(RandomTestUtil.randomBoolean());

		calendar.setEnableRatings(RandomTestUtil.randomBoolean());

		calendar.setLastPublishDate(RandomTestUtil.nextDate());

		_calendars.add(_persistence.update(calendar));

		return calendar;
	}

	private List<Calendar> _calendars = new ArrayList<Calendar>();
	private CalendarPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}