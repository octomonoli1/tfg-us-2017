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
import com.liferay.portal.kernel.exception.NoSuchPhoneException;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.service.PhoneLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.PhonePersistence;
import com.liferay.portal.kernel.service.persistence.PhoneUtil;
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
import java.util.Set;

/**
 * @generated
 */
public class PhonePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = PhoneUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Phone> iterator = _phones.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Phone phone = _persistence.create(pk);

		Assert.assertNotNull(phone);

		Assert.assertEquals(phone.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Phone newPhone = addPhone();

		_persistence.remove(newPhone);

		Phone existingPhone = _persistence.fetchByPrimaryKey(newPhone.getPrimaryKey());

		Assert.assertNull(existingPhone);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPhone();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Phone newPhone = _persistence.create(pk);

		newPhone.setMvccVersion(RandomTestUtil.nextLong());

		newPhone.setUuid(RandomTestUtil.randomString());

		newPhone.setCompanyId(RandomTestUtil.nextLong());

		newPhone.setUserId(RandomTestUtil.nextLong());

		newPhone.setUserName(RandomTestUtil.randomString());

		newPhone.setCreateDate(RandomTestUtil.nextDate());

		newPhone.setModifiedDate(RandomTestUtil.nextDate());

		newPhone.setClassNameId(RandomTestUtil.nextLong());

		newPhone.setClassPK(RandomTestUtil.nextLong());

		newPhone.setNumber(RandomTestUtil.randomString());

		newPhone.setExtension(RandomTestUtil.randomString());

		newPhone.setTypeId(RandomTestUtil.nextLong());

		newPhone.setPrimary(RandomTestUtil.randomBoolean());

		_phones.add(_persistence.update(newPhone));

		Phone existingPhone = _persistence.findByPrimaryKey(newPhone.getPrimaryKey());

		Assert.assertEquals(existingPhone.getMvccVersion(),
			newPhone.getMvccVersion());
		Assert.assertEquals(existingPhone.getUuid(), newPhone.getUuid());
		Assert.assertEquals(existingPhone.getPhoneId(), newPhone.getPhoneId());
		Assert.assertEquals(existingPhone.getCompanyId(),
			newPhone.getCompanyId());
		Assert.assertEquals(existingPhone.getUserId(), newPhone.getUserId());
		Assert.assertEquals(existingPhone.getUserName(), newPhone.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPhone.getCreateDate()),
			Time.getShortTimestamp(newPhone.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingPhone.getModifiedDate()),
			Time.getShortTimestamp(newPhone.getModifiedDate()));
		Assert.assertEquals(existingPhone.getClassNameId(),
			newPhone.getClassNameId());
		Assert.assertEquals(existingPhone.getClassPK(), newPhone.getClassPK());
		Assert.assertEquals(existingPhone.getNumber(), newPhone.getNumber());
		Assert.assertEquals(existingPhone.getExtension(),
			newPhone.getExtension());
		Assert.assertEquals(existingPhone.getTypeId(), newPhone.getTypeId());
		Assert.assertEquals(existingPhone.getPrimary(), newPhone.getPrimary());
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
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testCountByC_C_C() throws Exception {
		_persistence.countByC_C_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByC_C_C(0L, 0L, 0L);
	}

	@Test
	public void testCountByC_C_C_P() throws Exception {
		_persistence.countByC_C_C_P(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByC_C_C_P(0L, 0L, 0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Phone newPhone = addPhone();

		Phone existingPhone = _persistence.findByPrimaryKey(newPhone.getPrimaryKey());

		Assert.assertEquals(existingPhone, newPhone);
	}

	@Test(expected = NoSuchPhoneException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Phone> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("Phone", "mvccVersion",
			true, "uuid", true, "phoneId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"classNameId", true, "classPK", true, "number", true, "extension",
			true, "typeId", true, "primary", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Phone newPhone = addPhone();

		Phone existingPhone = _persistence.fetchByPrimaryKey(newPhone.getPrimaryKey());

		Assert.assertEquals(existingPhone, newPhone);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Phone missingPhone = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPhone);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Phone newPhone1 = addPhone();
		Phone newPhone2 = addPhone();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPhone1.getPrimaryKey());
		primaryKeys.add(newPhone2.getPrimaryKey());

		Map<Serializable, Phone> phones = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, phones.size());
		Assert.assertEquals(newPhone1, phones.get(newPhone1.getPrimaryKey()));
		Assert.assertEquals(newPhone2, phones.get(newPhone2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Phone> phones = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(phones.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Phone newPhone = addPhone();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPhone.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Phone> phones = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, phones.size());
		Assert.assertEquals(newPhone, phones.get(newPhone.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Phone> phones = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(phones.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Phone newPhone = addPhone();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPhone.getPrimaryKey());

		Map<Serializable, Phone> phones = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, phones.size());
		Assert.assertEquals(newPhone, phones.get(newPhone.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = PhoneLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Phone>() {
				@Override
				public void performAction(Phone phone) {
					Assert.assertNotNull(phone);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Phone newPhone = addPhone();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Phone.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("phoneId",
				newPhone.getPhoneId()));

		List<Phone> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Phone existingPhone = result.get(0);

		Assert.assertEquals(existingPhone, newPhone);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Phone.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("phoneId",
				RandomTestUtil.nextLong()));

		List<Phone> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Phone newPhone = addPhone();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Phone.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("phoneId"));

		Object newPhoneId = newPhone.getPhoneId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("phoneId",
				new Object[] { newPhoneId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPhoneId = result.get(0);

		Assert.assertEquals(existingPhoneId, newPhoneId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Phone.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("phoneId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("phoneId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected Phone addPhone() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Phone phone = _persistence.create(pk);

		phone.setMvccVersion(RandomTestUtil.nextLong());

		phone.setUuid(RandomTestUtil.randomString());

		phone.setCompanyId(RandomTestUtil.nextLong());

		phone.setUserId(RandomTestUtil.nextLong());

		phone.setUserName(RandomTestUtil.randomString());

		phone.setCreateDate(RandomTestUtil.nextDate());

		phone.setModifiedDate(RandomTestUtil.nextDate());

		phone.setClassNameId(RandomTestUtil.nextLong());

		phone.setClassPK(RandomTestUtil.nextLong());

		phone.setNumber(RandomTestUtil.randomString());

		phone.setExtension(RandomTestUtil.randomString());

		phone.setTypeId(RandomTestUtil.nextLong());

		phone.setPrimary(RandomTestUtil.randomBoolean());

		_phones.add(_persistence.update(phone));

		return phone;
	}

	private List<Phone> _phones = new ArrayList<Phone>();
	private PhonePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}