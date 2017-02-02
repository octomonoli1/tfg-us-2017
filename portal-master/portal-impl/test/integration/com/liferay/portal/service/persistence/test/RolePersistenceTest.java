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
import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.RolePersistence;
import com.liferay.portal.kernel.service.persistence.RoleUtil;
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
public class RolePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = RoleUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Role> iterator = _roles.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Role role = _persistence.create(pk);

		Assert.assertNotNull(role);

		Assert.assertEquals(role.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Role newRole = addRole();

		_persistence.remove(newRole);

		Role existingRole = _persistence.fetchByPrimaryKey(newRole.getPrimaryKey());

		Assert.assertNull(existingRole);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRole();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Role newRole = _persistence.create(pk);

		newRole.setMvccVersion(RandomTestUtil.nextLong());

		newRole.setUuid(RandomTestUtil.randomString());

		newRole.setCompanyId(RandomTestUtil.nextLong());

		newRole.setUserId(RandomTestUtil.nextLong());

		newRole.setUserName(RandomTestUtil.randomString());

		newRole.setCreateDate(RandomTestUtil.nextDate());

		newRole.setModifiedDate(RandomTestUtil.nextDate());

		newRole.setClassNameId(RandomTestUtil.nextLong());

		newRole.setClassPK(RandomTestUtil.nextLong());

		newRole.setName(RandomTestUtil.randomString());

		newRole.setTitle(RandomTestUtil.randomString());

		newRole.setDescription(RandomTestUtil.randomString());

		newRole.setType(RandomTestUtil.nextInt());

		newRole.setSubtype(RandomTestUtil.randomString());

		_roles.add(_persistence.update(newRole));

		Role existingRole = _persistence.findByPrimaryKey(newRole.getPrimaryKey());

		Assert.assertEquals(existingRole.getMvccVersion(),
			newRole.getMvccVersion());
		Assert.assertEquals(existingRole.getUuid(), newRole.getUuid());
		Assert.assertEquals(existingRole.getRoleId(), newRole.getRoleId());
		Assert.assertEquals(existingRole.getCompanyId(), newRole.getCompanyId());
		Assert.assertEquals(existingRole.getUserId(), newRole.getUserId());
		Assert.assertEquals(existingRole.getUserName(), newRole.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(existingRole.getCreateDate()),
			Time.getShortTimestamp(newRole.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingRole.getModifiedDate()),
			Time.getShortTimestamp(newRole.getModifiedDate()));
		Assert.assertEquals(existingRole.getClassNameId(),
			newRole.getClassNameId());
		Assert.assertEquals(existingRole.getClassPK(), newRole.getClassPK());
		Assert.assertEquals(existingRole.getName(), newRole.getName());
		Assert.assertEquals(existingRole.getTitle(), newRole.getTitle());
		Assert.assertEquals(existingRole.getDescription(),
			newRole.getDescription());
		Assert.assertEquals(existingRole.getType(), newRole.getType());
		Assert.assertEquals(existingRole.getSubtype(), newRole.getSubtype());
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
	public void testCountByName() throws Exception {
		_persistence.countByName(StringPool.BLANK);

		_persistence.countByName(StringPool.NULL);

		_persistence.countByName((String)null);
	}

	@Test
	public void testCountByType() throws Exception {
		_persistence.countByType(RandomTestUtil.nextInt());

		_persistence.countByType(0);
	}

	@Test
	public void testCountBySubtype() throws Exception {
		_persistence.countBySubtype(StringPool.BLANK);

		_persistence.countBySubtype(StringPool.NULL);

		_persistence.countBySubtype((String)null);
	}

	@Test
	public void testCountByC_N() throws Exception {
		_persistence.countByC_N(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByC_N(0L, StringPool.NULL);

		_persistence.countByC_N(0L, (String)null);
	}

	@Test
	public void testCountByC_T() throws Exception {
		_persistence.countByC_T(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByC_T(0L, 0);
	}

	@Test
	public void testCountByC_TArrayable() throws Exception {
		_persistence.countByC_T(RandomTestUtil.nextLong(),
			new int[] { RandomTestUtil.nextInt(), 0 });
	}

	@Test
	public void testCountByT_S() throws Exception {
		_persistence.countByT_S(RandomTestUtil.nextInt(), StringPool.BLANK);

		_persistence.countByT_S(0, StringPool.NULL);

		_persistence.countByT_S(0, (String)null);
	}

	@Test
	public void testCountByC_C_C() throws Exception {
		_persistence.countByC_C_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByC_C_C(0L, 0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Role newRole = addRole();

		Role existingRole = _persistence.findByPrimaryKey(newRole.getPrimaryKey());

		Assert.assertEquals(existingRole, newRole);
	}

	@Test(expected = NoSuchRoleException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Role> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("Role_", "mvccVersion",
			true, "uuid", true, "roleId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"classNameId", true, "classPK", true, "name", true, "title", true,
			"description", true, "type", true, "subtype", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Role newRole = addRole();

		Role existingRole = _persistence.fetchByPrimaryKey(newRole.getPrimaryKey());

		Assert.assertEquals(existingRole, newRole);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Role missingRole = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRole);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Role newRole1 = addRole();
		Role newRole2 = addRole();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRole1.getPrimaryKey());
		primaryKeys.add(newRole2.getPrimaryKey());

		Map<Serializable, Role> roles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, roles.size());
		Assert.assertEquals(newRole1, roles.get(newRole1.getPrimaryKey()));
		Assert.assertEquals(newRole2, roles.get(newRole2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Role> roles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(roles.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Role newRole = addRole();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRole.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Role> roles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, roles.size());
		Assert.assertEquals(newRole, roles.get(newRole.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Role> roles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(roles.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Role newRole = addRole();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRole.getPrimaryKey());

		Map<Serializable, Role> roles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, roles.size());
		Assert.assertEquals(newRole, roles.get(newRole.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RoleLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Role>() {
				@Override
				public void performAction(Role role) {
					Assert.assertNotNull(role);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Role newRole = addRole();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Role.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("roleId",
				newRole.getRoleId()));

		List<Role> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Role existingRole = result.get(0);

		Assert.assertEquals(existingRole, newRole);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Role.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("roleId",
				RandomTestUtil.nextLong()));

		List<Role> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Role newRole = addRole();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Role.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("roleId"));

		Object newRoleId = newRole.getRoleId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("roleId",
				new Object[] { newRoleId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRoleId = result.get(0);

		Assert.assertEquals(existingRoleId, newRoleId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Role.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("roleId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("roleId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Role newRole = addRole();

		_persistence.clearCache();

		Role existingRole = _persistence.findByPrimaryKey(newRole.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingRole.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(existingRole,
				"getOriginalCompanyId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingRole.getName(),
				ReflectionTestUtil.invoke(existingRole, "getOriginalName",
					new Class<?>[0])));

		Assert.assertEquals(Long.valueOf(existingRole.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(existingRole,
				"getOriginalCompanyId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingRole.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingRole,
				"getOriginalClassNameId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingRole.getClassPK()),
			ReflectionTestUtil.<Long>invoke(existingRole, "getOriginalClassPK",
				new Class<?>[0]));
	}

	protected Role addRole() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Role role = _persistence.create(pk);

		role.setMvccVersion(RandomTestUtil.nextLong());

		role.setUuid(RandomTestUtil.randomString());

		role.setCompanyId(RandomTestUtil.nextLong());

		role.setUserId(RandomTestUtil.nextLong());

		role.setUserName(RandomTestUtil.randomString());

		role.setCreateDate(RandomTestUtil.nextDate());

		role.setModifiedDate(RandomTestUtil.nextDate());

		role.setClassNameId(RandomTestUtil.nextLong());

		role.setClassPK(RandomTestUtil.nextLong());

		role.setName(RandomTestUtil.randomString());

		role.setTitle(RandomTestUtil.randomString());

		role.setDescription(RandomTestUtil.randomString());

		role.setType(RandomTestUtil.nextInt());

		role.setSubtype(RandomTestUtil.randomString());

		_roles.add(_persistence.update(role));

		return role;
	}

	private List<Role> _roles = new ArrayList<Role>();
	private RolePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}