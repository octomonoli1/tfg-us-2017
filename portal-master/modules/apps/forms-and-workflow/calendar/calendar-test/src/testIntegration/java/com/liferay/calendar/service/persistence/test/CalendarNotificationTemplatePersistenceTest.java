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

import com.liferay.calendar.exception.NoSuchNotificationTemplateException;
import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.service.CalendarNotificationTemplateLocalServiceUtil;
import com.liferay.calendar.service.persistence.CalendarNotificationTemplatePersistence;
import com.liferay.calendar.service.persistence.CalendarNotificationTemplateUtil;

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
public class CalendarNotificationTemplatePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = CalendarNotificationTemplateUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CalendarNotificationTemplate> iterator = _calendarNotificationTemplates.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CalendarNotificationTemplate calendarNotificationTemplate = _persistence.create(pk);

		Assert.assertNotNull(calendarNotificationTemplate);

		Assert.assertEquals(calendarNotificationTemplate.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CalendarNotificationTemplate newCalendarNotificationTemplate = addCalendarNotificationTemplate();

		_persistence.remove(newCalendarNotificationTemplate);

		CalendarNotificationTemplate existingCalendarNotificationTemplate = _persistence.fetchByPrimaryKey(newCalendarNotificationTemplate.getPrimaryKey());

		Assert.assertNull(existingCalendarNotificationTemplate);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCalendarNotificationTemplate();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CalendarNotificationTemplate newCalendarNotificationTemplate = _persistence.create(pk);

		newCalendarNotificationTemplate.setUuid(RandomTestUtil.randomString());

		newCalendarNotificationTemplate.setGroupId(RandomTestUtil.nextLong());

		newCalendarNotificationTemplate.setCompanyId(RandomTestUtil.nextLong());

		newCalendarNotificationTemplate.setUserId(RandomTestUtil.nextLong());

		newCalendarNotificationTemplate.setUserName(RandomTestUtil.randomString());

		newCalendarNotificationTemplate.setCreateDate(RandomTestUtil.nextDate());

		newCalendarNotificationTemplate.setModifiedDate(RandomTestUtil.nextDate());

		newCalendarNotificationTemplate.setCalendarId(RandomTestUtil.nextLong());

		newCalendarNotificationTemplate.setNotificationType(RandomTestUtil.randomString());

		newCalendarNotificationTemplate.setNotificationTypeSettings(RandomTestUtil.randomString());

		newCalendarNotificationTemplate.setNotificationTemplateType(RandomTestUtil.randomString());

		newCalendarNotificationTemplate.setSubject(RandomTestUtil.randomString());

		newCalendarNotificationTemplate.setBody(RandomTestUtil.randomString());

		newCalendarNotificationTemplate.setLastPublishDate(RandomTestUtil.nextDate());

		_calendarNotificationTemplates.add(_persistence.update(
				newCalendarNotificationTemplate));

		CalendarNotificationTemplate existingCalendarNotificationTemplate = _persistence.findByPrimaryKey(newCalendarNotificationTemplate.getPrimaryKey());

		Assert.assertEquals(existingCalendarNotificationTemplate.getUuid(),
			newCalendarNotificationTemplate.getUuid());
		Assert.assertEquals(existingCalendarNotificationTemplate.getCalendarNotificationTemplateId(),
			newCalendarNotificationTemplate.getCalendarNotificationTemplateId());
		Assert.assertEquals(existingCalendarNotificationTemplate.getGroupId(),
			newCalendarNotificationTemplate.getGroupId());
		Assert.assertEquals(existingCalendarNotificationTemplate.getCompanyId(),
			newCalendarNotificationTemplate.getCompanyId());
		Assert.assertEquals(existingCalendarNotificationTemplate.getUserId(),
			newCalendarNotificationTemplate.getUserId());
		Assert.assertEquals(existingCalendarNotificationTemplate.getUserName(),
			newCalendarNotificationTemplate.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCalendarNotificationTemplate.getCreateDate()),
			Time.getShortTimestamp(
				newCalendarNotificationTemplate.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCalendarNotificationTemplate.getModifiedDate()),
			Time.getShortTimestamp(
				newCalendarNotificationTemplate.getModifiedDate()));
		Assert.assertEquals(existingCalendarNotificationTemplate.getCalendarId(),
			newCalendarNotificationTemplate.getCalendarId());
		Assert.assertEquals(existingCalendarNotificationTemplate.getNotificationType(),
			newCalendarNotificationTemplate.getNotificationType());
		Assert.assertEquals(existingCalendarNotificationTemplate.getNotificationTypeSettings(),
			newCalendarNotificationTemplate.getNotificationTypeSettings());
		Assert.assertEquals(existingCalendarNotificationTemplate.getNotificationTemplateType(),
			newCalendarNotificationTemplate.getNotificationTemplateType());
		Assert.assertEquals(existingCalendarNotificationTemplate.getSubject(),
			newCalendarNotificationTemplate.getSubject());
		Assert.assertEquals(existingCalendarNotificationTemplate.getBody(),
			newCalendarNotificationTemplate.getBody());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCalendarNotificationTemplate.getLastPublishDate()),
			Time.getShortTimestamp(
				newCalendarNotificationTemplate.getLastPublishDate()));
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
	public void testCountByCalendarId() throws Exception {
		_persistence.countByCalendarId(RandomTestUtil.nextLong());

		_persistence.countByCalendarId(0L);
	}

	@Test
	public void testCountByC_NT_NTT() throws Exception {
		_persistence.countByC_NT_NTT(RandomTestUtil.nextLong(),
			StringPool.BLANK, StringPool.BLANK);

		_persistence.countByC_NT_NTT(0L, StringPool.NULL, StringPool.NULL);

		_persistence.countByC_NT_NTT(0L, (String)null, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CalendarNotificationTemplate newCalendarNotificationTemplate = addCalendarNotificationTemplate();

		CalendarNotificationTemplate existingCalendarNotificationTemplate = _persistence.findByPrimaryKey(newCalendarNotificationTemplate.getPrimaryKey());

		Assert.assertEquals(existingCalendarNotificationTemplate,
			newCalendarNotificationTemplate);
	}

	@Test(expected = NoSuchNotificationTemplateException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CalendarNotificationTemplate> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CalendarNotificationTemplate",
			"uuid", true, "calendarNotificationTemplateId", true, "groupId",
			true, "companyId", true, "userId", true, "userName", true,
			"createDate", true, "modifiedDate", true, "calendarId", true,
			"notificationType", true, "notificationTypeSettings", true,
			"notificationTemplateType", true, "subject", true,
			"lastPublishDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CalendarNotificationTemplate newCalendarNotificationTemplate = addCalendarNotificationTemplate();

		CalendarNotificationTemplate existingCalendarNotificationTemplate = _persistence.fetchByPrimaryKey(newCalendarNotificationTemplate.getPrimaryKey());

		Assert.assertEquals(existingCalendarNotificationTemplate,
			newCalendarNotificationTemplate);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CalendarNotificationTemplate missingCalendarNotificationTemplate = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCalendarNotificationTemplate);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CalendarNotificationTemplate newCalendarNotificationTemplate1 = addCalendarNotificationTemplate();
		CalendarNotificationTemplate newCalendarNotificationTemplate2 = addCalendarNotificationTemplate();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCalendarNotificationTemplate1.getPrimaryKey());
		primaryKeys.add(newCalendarNotificationTemplate2.getPrimaryKey());

		Map<Serializable, CalendarNotificationTemplate> calendarNotificationTemplates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, calendarNotificationTemplates.size());
		Assert.assertEquals(newCalendarNotificationTemplate1,
			calendarNotificationTemplates.get(
				newCalendarNotificationTemplate1.getPrimaryKey()));
		Assert.assertEquals(newCalendarNotificationTemplate2,
			calendarNotificationTemplates.get(
				newCalendarNotificationTemplate2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CalendarNotificationTemplate> calendarNotificationTemplates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(calendarNotificationTemplates.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CalendarNotificationTemplate newCalendarNotificationTemplate = addCalendarNotificationTemplate();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCalendarNotificationTemplate.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CalendarNotificationTemplate> calendarNotificationTemplates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, calendarNotificationTemplates.size());
		Assert.assertEquals(newCalendarNotificationTemplate,
			calendarNotificationTemplates.get(
				newCalendarNotificationTemplate.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CalendarNotificationTemplate> calendarNotificationTemplates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(calendarNotificationTemplates.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CalendarNotificationTemplate newCalendarNotificationTemplate = addCalendarNotificationTemplate();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCalendarNotificationTemplate.getPrimaryKey());

		Map<Serializable, CalendarNotificationTemplate> calendarNotificationTemplates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, calendarNotificationTemplates.size());
		Assert.assertEquals(newCalendarNotificationTemplate,
			calendarNotificationTemplates.get(
				newCalendarNotificationTemplate.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CalendarNotificationTemplateLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CalendarNotificationTemplate>() {
				@Override
				public void performAction(
					CalendarNotificationTemplate calendarNotificationTemplate) {
					Assert.assertNotNull(calendarNotificationTemplate);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CalendarNotificationTemplate newCalendarNotificationTemplate = addCalendarNotificationTemplate();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CalendarNotificationTemplate.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"calendarNotificationTemplateId",
				newCalendarNotificationTemplate.getCalendarNotificationTemplateId()));

		List<CalendarNotificationTemplate> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CalendarNotificationTemplate existingCalendarNotificationTemplate = result.get(0);

		Assert.assertEquals(existingCalendarNotificationTemplate,
			newCalendarNotificationTemplate);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CalendarNotificationTemplate.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"calendarNotificationTemplateId", RandomTestUtil.nextLong()));

		List<CalendarNotificationTemplate> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CalendarNotificationTemplate newCalendarNotificationTemplate = addCalendarNotificationTemplate();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CalendarNotificationTemplate.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"calendarNotificationTemplateId"));

		Object newCalendarNotificationTemplateId = newCalendarNotificationTemplate.getCalendarNotificationTemplateId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"calendarNotificationTemplateId",
				new Object[] { newCalendarNotificationTemplateId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCalendarNotificationTemplateId = result.get(0);

		Assert.assertEquals(existingCalendarNotificationTemplateId,
			newCalendarNotificationTemplateId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CalendarNotificationTemplate.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"calendarNotificationTemplateId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"calendarNotificationTemplateId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CalendarNotificationTemplate newCalendarNotificationTemplate = addCalendarNotificationTemplate();

		_persistence.clearCache();

		CalendarNotificationTemplate existingCalendarNotificationTemplate = _persistence.findByPrimaryKey(newCalendarNotificationTemplate.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingCalendarNotificationTemplate.getUuid(),
				ReflectionTestUtil.invoke(
					existingCalendarNotificationTemplate, "getOriginalUuid",
					new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(
				existingCalendarNotificationTemplate.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCalendarNotificationTemplate, "getOriginalGroupId",
				new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(
				existingCalendarNotificationTemplate.getCalendarId()),
			ReflectionTestUtil.<Long>invoke(
				existingCalendarNotificationTemplate, "getOriginalCalendarId",
				new Class<?>[0]));
		Assert.assertTrue(Objects.equals(
				existingCalendarNotificationTemplate.getNotificationType(),
				ReflectionTestUtil.invoke(
					existingCalendarNotificationTemplate,
					"getOriginalNotificationType", new Class<?>[0])));
		Assert.assertTrue(Objects.equals(
				existingCalendarNotificationTemplate.getNotificationTemplateType(),
				ReflectionTestUtil.invoke(
					existingCalendarNotificationTemplate,
					"getOriginalNotificationTemplateType", new Class<?>[0])));
	}

	protected CalendarNotificationTemplate addCalendarNotificationTemplate()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		CalendarNotificationTemplate calendarNotificationTemplate = _persistence.create(pk);

		calendarNotificationTemplate.setUuid(RandomTestUtil.randomString());

		calendarNotificationTemplate.setGroupId(RandomTestUtil.nextLong());

		calendarNotificationTemplate.setCompanyId(RandomTestUtil.nextLong());

		calendarNotificationTemplate.setUserId(RandomTestUtil.nextLong());

		calendarNotificationTemplate.setUserName(RandomTestUtil.randomString());

		calendarNotificationTemplate.setCreateDate(RandomTestUtil.nextDate());

		calendarNotificationTemplate.setModifiedDate(RandomTestUtil.nextDate());

		calendarNotificationTemplate.setCalendarId(RandomTestUtil.nextLong());

		calendarNotificationTemplate.setNotificationType(RandomTestUtil.randomString());

		calendarNotificationTemplate.setNotificationTypeSettings(RandomTestUtil.randomString());

		calendarNotificationTemplate.setNotificationTemplateType(RandomTestUtil.randomString());

		calendarNotificationTemplate.setSubject(RandomTestUtil.randomString());

		calendarNotificationTemplate.setBody(RandomTestUtil.randomString());

		calendarNotificationTemplate.setLastPublishDate(RandomTestUtil.nextDate());

		_calendarNotificationTemplates.add(_persistence.update(
				calendarNotificationTemplate));

		return calendarNotificationTemplate;
	}

	private List<CalendarNotificationTemplate> _calendarNotificationTemplates = new ArrayList<CalendarNotificationTemplate>();
	private CalendarNotificationTemplatePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}