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

package com.liferay.invitation.invite.members.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.invitation.invite.members.exception.NoSuchMemberRequestException;
import com.liferay.invitation.invite.members.model.MemberRequest;
import com.liferay.invitation.invite.members.service.MemberRequestLocalServiceUtil;
import com.liferay.invitation.invite.members.service.persistence.MemberRequestPersistence;
import com.liferay.invitation.invite.members.service.persistence.MemberRequestUtil;

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
public class MemberRequestPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = MemberRequestUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MemberRequest> iterator = _memberRequests.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MemberRequest memberRequest = _persistence.create(pk);

		Assert.assertNotNull(memberRequest);

		Assert.assertEquals(memberRequest.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MemberRequest newMemberRequest = addMemberRequest();

		_persistence.remove(newMemberRequest);

		MemberRequest existingMemberRequest = _persistence.fetchByPrimaryKey(newMemberRequest.getPrimaryKey());

		Assert.assertNull(existingMemberRequest);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMemberRequest();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MemberRequest newMemberRequest = _persistence.create(pk);

		newMemberRequest.setGroupId(RandomTestUtil.nextLong());

		newMemberRequest.setCompanyId(RandomTestUtil.nextLong());

		newMemberRequest.setUserId(RandomTestUtil.nextLong());

		newMemberRequest.setUserName(RandomTestUtil.randomString());

		newMemberRequest.setCreateDate(RandomTestUtil.nextDate());

		newMemberRequest.setModifiedDate(RandomTestUtil.nextDate());

		newMemberRequest.setKey(RandomTestUtil.randomString());

		newMemberRequest.setReceiverUserId(RandomTestUtil.nextLong());

		newMemberRequest.setInvitedRoleId(RandomTestUtil.nextLong());

		newMemberRequest.setInvitedTeamId(RandomTestUtil.nextLong());

		newMemberRequest.setStatus(RandomTestUtil.nextInt());

		_memberRequests.add(_persistence.update(newMemberRequest));

		MemberRequest existingMemberRequest = _persistence.findByPrimaryKey(newMemberRequest.getPrimaryKey());

		Assert.assertEquals(existingMemberRequest.getMemberRequestId(),
			newMemberRequest.getMemberRequestId());
		Assert.assertEquals(existingMemberRequest.getGroupId(),
			newMemberRequest.getGroupId());
		Assert.assertEquals(existingMemberRequest.getCompanyId(),
			newMemberRequest.getCompanyId());
		Assert.assertEquals(existingMemberRequest.getUserId(),
			newMemberRequest.getUserId());
		Assert.assertEquals(existingMemberRequest.getUserName(),
			newMemberRequest.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingMemberRequest.getCreateDate()),
			Time.getShortTimestamp(newMemberRequest.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingMemberRequest.getModifiedDate()),
			Time.getShortTimestamp(newMemberRequest.getModifiedDate()));
		Assert.assertEquals(existingMemberRequest.getKey(),
			newMemberRequest.getKey());
		Assert.assertEquals(existingMemberRequest.getReceiverUserId(),
			newMemberRequest.getReceiverUserId());
		Assert.assertEquals(existingMemberRequest.getInvitedRoleId(),
			newMemberRequest.getInvitedRoleId());
		Assert.assertEquals(existingMemberRequest.getInvitedTeamId(),
			newMemberRequest.getInvitedTeamId());
		Assert.assertEquals(existingMemberRequest.getStatus(),
			newMemberRequest.getStatus());
	}

	@Test
	public void testCountByKey() throws Exception {
		_persistence.countByKey(StringPool.BLANK);

		_persistence.countByKey(StringPool.NULL);

		_persistence.countByKey((String)null);
	}

	@Test
	public void testCountByReceiverUserId() throws Exception {
		_persistence.countByReceiverUserId(RandomTestUtil.nextLong());

		_persistence.countByReceiverUserId(0L);
	}

	@Test
	public void testCountByR_S() throws Exception {
		_persistence.countByR_S(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByR_S(0L, 0);
	}

	@Test
	public void testCountByG_R_S() throws Exception {
		_persistence.countByG_R_S(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByG_R_S(0L, 0L, 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MemberRequest newMemberRequest = addMemberRequest();

		MemberRequest existingMemberRequest = _persistence.findByPrimaryKey(newMemberRequest.getPrimaryKey());

		Assert.assertEquals(existingMemberRequest, newMemberRequest);
	}

	@Test(expected = NoSuchMemberRequestException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<MemberRequest> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("SO_MemberRequest",
			"memberRequestId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "key", true, "receiverUserId", true,
			"invitedRoleId", true, "invitedTeamId", true, "status", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MemberRequest newMemberRequest = addMemberRequest();

		MemberRequest existingMemberRequest = _persistence.fetchByPrimaryKey(newMemberRequest.getPrimaryKey());

		Assert.assertEquals(existingMemberRequest, newMemberRequest);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MemberRequest missingMemberRequest = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMemberRequest);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MemberRequest newMemberRequest1 = addMemberRequest();
		MemberRequest newMemberRequest2 = addMemberRequest();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMemberRequest1.getPrimaryKey());
		primaryKeys.add(newMemberRequest2.getPrimaryKey());

		Map<Serializable, MemberRequest> memberRequests = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, memberRequests.size());
		Assert.assertEquals(newMemberRequest1,
			memberRequests.get(newMemberRequest1.getPrimaryKey()));
		Assert.assertEquals(newMemberRequest2,
			memberRequests.get(newMemberRequest2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MemberRequest> memberRequests = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(memberRequests.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MemberRequest newMemberRequest = addMemberRequest();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMemberRequest.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MemberRequest> memberRequests = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, memberRequests.size());
		Assert.assertEquals(newMemberRequest,
			memberRequests.get(newMemberRequest.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MemberRequest> memberRequests = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(memberRequests.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MemberRequest newMemberRequest = addMemberRequest();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMemberRequest.getPrimaryKey());

		Map<Serializable, MemberRequest> memberRequests = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, memberRequests.size());
		Assert.assertEquals(newMemberRequest,
			memberRequests.get(newMemberRequest.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MemberRequestLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MemberRequest>() {
				@Override
				public void performAction(MemberRequest memberRequest) {
					Assert.assertNotNull(memberRequest);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MemberRequest newMemberRequest = addMemberRequest();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MemberRequest.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("memberRequestId",
				newMemberRequest.getMemberRequestId()));

		List<MemberRequest> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MemberRequest existingMemberRequest = result.get(0);

		Assert.assertEquals(existingMemberRequest, newMemberRequest);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MemberRequest.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("memberRequestId",
				RandomTestUtil.nextLong()));

		List<MemberRequest> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MemberRequest newMemberRequest = addMemberRequest();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MemberRequest.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"memberRequestId"));

		Object newMemberRequestId = newMemberRequest.getMemberRequestId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("memberRequestId",
				new Object[] { newMemberRequestId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingMemberRequestId = result.get(0);

		Assert.assertEquals(existingMemberRequestId, newMemberRequestId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MemberRequest.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"memberRequestId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("memberRequestId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		MemberRequest newMemberRequest = addMemberRequest();

		_persistence.clearCache();

		MemberRequest existingMemberRequest = _persistence.findByPrimaryKey(newMemberRequest.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingMemberRequest.getKey(),
				ReflectionTestUtil.invoke(existingMemberRequest,
					"getOriginalKey", new Class<?>[0])));

		Assert.assertEquals(Long.valueOf(existingMemberRequest.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingMemberRequest,
				"getOriginalGroupId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingMemberRequest.getReceiverUserId()),
			ReflectionTestUtil.<Long>invoke(existingMemberRequest,
				"getOriginalReceiverUserId", new Class<?>[0]));
		Assert.assertEquals(Integer.valueOf(existingMemberRequest.getStatus()),
			ReflectionTestUtil.<Integer>invoke(existingMemberRequest,
				"getOriginalStatus", new Class<?>[0]));
	}

	protected MemberRequest addMemberRequest() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MemberRequest memberRequest = _persistence.create(pk);

		memberRequest.setGroupId(RandomTestUtil.nextLong());

		memberRequest.setCompanyId(RandomTestUtil.nextLong());

		memberRequest.setUserId(RandomTestUtil.nextLong());

		memberRequest.setUserName(RandomTestUtil.randomString());

		memberRequest.setCreateDate(RandomTestUtil.nextDate());

		memberRequest.setModifiedDate(RandomTestUtil.nextDate());

		memberRequest.setKey(RandomTestUtil.randomString());

		memberRequest.setReceiverUserId(RandomTestUtil.nextLong());

		memberRequest.setInvitedRoleId(RandomTestUtil.nextLong());

		memberRequest.setInvitedTeamId(RandomTestUtil.nextLong());

		memberRequest.setStatus(RandomTestUtil.nextInt());

		_memberRequests.add(_persistence.update(memberRequest));

		return memberRequest;
	}

	private List<MemberRequest> _memberRequests = new ArrayList<MemberRequest>();
	private MemberRequestPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}