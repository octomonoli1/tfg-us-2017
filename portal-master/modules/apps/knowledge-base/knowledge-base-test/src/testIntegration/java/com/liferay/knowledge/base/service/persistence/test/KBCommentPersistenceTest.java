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

package com.liferay.knowledge.base.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.knowledge.base.exception.NoSuchCommentException;
import com.liferay.knowledge.base.model.KBComment;
import com.liferay.knowledge.base.service.KBCommentLocalServiceUtil;
import com.liferay.knowledge.base.service.persistence.KBCommentPersistence;
import com.liferay.knowledge.base.service.persistence.KBCommentUtil;

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
public class KBCommentPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = KBCommentUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<KBComment> iterator = _kbComments.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KBComment kbComment = _persistence.create(pk);

		Assert.assertNotNull(kbComment);

		Assert.assertEquals(kbComment.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		KBComment newKBComment = addKBComment();

		_persistence.remove(newKBComment);

		KBComment existingKBComment = _persistence.fetchByPrimaryKey(newKBComment.getPrimaryKey());

		Assert.assertNull(existingKBComment);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addKBComment();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KBComment newKBComment = _persistence.create(pk);

		newKBComment.setUuid(RandomTestUtil.randomString());

		newKBComment.setGroupId(RandomTestUtil.nextLong());

		newKBComment.setCompanyId(RandomTestUtil.nextLong());

		newKBComment.setUserId(RandomTestUtil.nextLong());

		newKBComment.setUserName(RandomTestUtil.randomString());

		newKBComment.setCreateDate(RandomTestUtil.nextDate());

		newKBComment.setModifiedDate(RandomTestUtil.nextDate());

		newKBComment.setClassNameId(RandomTestUtil.nextLong());

		newKBComment.setClassPK(RandomTestUtil.nextLong());

		newKBComment.setContent(RandomTestUtil.randomString());

		newKBComment.setUserRating(RandomTestUtil.nextInt());

		newKBComment.setLastPublishDate(RandomTestUtil.nextDate());

		newKBComment.setStatus(RandomTestUtil.nextInt());

		_kbComments.add(_persistence.update(newKBComment));

		KBComment existingKBComment = _persistence.findByPrimaryKey(newKBComment.getPrimaryKey());

		Assert.assertEquals(existingKBComment.getUuid(), newKBComment.getUuid());
		Assert.assertEquals(existingKBComment.getKbCommentId(),
			newKBComment.getKbCommentId());
		Assert.assertEquals(existingKBComment.getGroupId(),
			newKBComment.getGroupId());
		Assert.assertEquals(existingKBComment.getCompanyId(),
			newKBComment.getCompanyId());
		Assert.assertEquals(existingKBComment.getUserId(),
			newKBComment.getUserId());
		Assert.assertEquals(existingKBComment.getUserName(),
			newKBComment.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingKBComment.getCreateDate()),
			Time.getShortTimestamp(newKBComment.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingKBComment.getModifiedDate()),
			Time.getShortTimestamp(newKBComment.getModifiedDate()));
		Assert.assertEquals(existingKBComment.getClassNameId(),
			newKBComment.getClassNameId());
		Assert.assertEquals(existingKBComment.getClassPK(),
			newKBComment.getClassPK());
		Assert.assertEquals(existingKBComment.getContent(),
			newKBComment.getContent());
		Assert.assertEquals(existingKBComment.getUserRating(),
			newKBComment.getUserRating());
		Assert.assertEquals(Time.getShortTimestamp(
				existingKBComment.getLastPublishDate()),
			Time.getShortTimestamp(newKBComment.getLastPublishDate()));
		Assert.assertEquals(existingKBComment.getStatus(),
			newKBComment.getStatus());
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
	public void testCountByG_C() throws Exception {
		_persistence.countByG_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_C(0L, 0L);
	}

	@Test
	public void testCountByG_S() throws Exception {
		_persistence.countByG_S(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByG_S(0L, 0);
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testCountByU_C_C() throws Exception {
		_persistence.countByU_C_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByU_C_C(0L, 0L, 0L);
	}

	@Test
	public void testCountByC_C_S() throws Exception {
		_persistence.countByC_C_S(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByC_C_S(0L, 0L, 0);
	}

	@Test
	public void testCountByC_C_SArrayable() throws Exception {
		_persistence.countByC_C_S(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), new int[] { RandomTestUtil.nextInt(), 0 });
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		KBComment newKBComment = addKBComment();

		KBComment existingKBComment = _persistence.findByPrimaryKey(newKBComment.getPrimaryKey());

		Assert.assertEquals(existingKBComment, newKBComment);
	}

	@Test(expected = NoSuchCommentException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<KBComment> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("KBComment", "uuid", true,
			"kbCommentId", true, "groupId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"classNameId", true, "classPK", true, "content", true,
			"userRating", true, "lastPublishDate", true, "status", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		KBComment newKBComment = addKBComment();

		KBComment existingKBComment = _persistence.fetchByPrimaryKey(newKBComment.getPrimaryKey());

		Assert.assertEquals(existingKBComment, newKBComment);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KBComment missingKBComment = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingKBComment);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		KBComment newKBComment1 = addKBComment();
		KBComment newKBComment2 = addKBComment();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKBComment1.getPrimaryKey());
		primaryKeys.add(newKBComment2.getPrimaryKey());

		Map<Serializable, KBComment> kbComments = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, kbComments.size());
		Assert.assertEquals(newKBComment1,
			kbComments.get(newKBComment1.getPrimaryKey()));
		Assert.assertEquals(newKBComment2,
			kbComments.get(newKBComment2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, KBComment> kbComments = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kbComments.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		KBComment newKBComment = addKBComment();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKBComment.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, KBComment> kbComments = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kbComments.size());
		Assert.assertEquals(newKBComment,
			kbComments.get(newKBComment.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, KBComment> kbComments = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(kbComments.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		KBComment newKBComment = addKBComment();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newKBComment.getPrimaryKey());

		Map<Serializable, KBComment> kbComments = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, kbComments.size());
		Assert.assertEquals(newKBComment,
			kbComments.get(newKBComment.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = KBCommentLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<KBComment>() {
				@Override
				public void performAction(KBComment kbComment) {
					Assert.assertNotNull(kbComment);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		KBComment newKBComment = addKBComment();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KBComment.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("kbCommentId",
				newKBComment.getKbCommentId()));

		List<KBComment> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		KBComment existingKBComment = result.get(0);

		Assert.assertEquals(existingKBComment, newKBComment);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KBComment.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("kbCommentId",
				RandomTestUtil.nextLong()));

		List<KBComment> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		KBComment newKBComment = addKBComment();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KBComment.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("kbCommentId"));

		Object newKbCommentId = newKBComment.getKbCommentId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("kbCommentId",
				new Object[] { newKbCommentId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingKbCommentId = result.get(0);

		Assert.assertEquals(existingKbCommentId, newKbCommentId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(KBComment.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("kbCommentId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("kbCommentId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		KBComment newKBComment = addKBComment();

		_persistence.clearCache();

		KBComment existingKBComment = _persistence.findByPrimaryKey(newKBComment.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingKBComment.getUuid(),
				ReflectionTestUtil.invoke(existingKBComment, "getOriginalUuid",
					new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingKBComment.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingKBComment,
				"getOriginalGroupId", new Class<?>[0]));
	}

	protected KBComment addKBComment() throws Exception {
		long pk = RandomTestUtil.nextLong();

		KBComment kbComment = _persistence.create(pk);

		kbComment.setUuid(RandomTestUtil.randomString());

		kbComment.setGroupId(RandomTestUtil.nextLong());

		kbComment.setCompanyId(RandomTestUtil.nextLong());

		kbComment.setUserId(RandomTestUtil.nextLong());

		kbComment.setUserName(RandomTestUtil.randomString());

		kbComment.setCreateDate(RandomTestUtil.nextDate());

		kbComment.setModifiedDate(RandomTestUtil.nextDate());

		kbComment.setClassNameId(RandomTestUtil.nextLong());

		kbComment.setClassPK(RandomTestUtil.nextLong());

		kbComment.setContent(RandomTestUtil.randomString());

		kbComment.setUserRating(RandomTestUtil.nextInt());

		kbComment.setLastPublishDate(RandomTestUtil.nextDate());

		kbComment.setStatus(RandomTestUtil.nextInt());

		_kbComments.add(_persistence.update(kbComment));

		return kbComment;
	}

	private List<KBComment> _kbComments = new ArrayList<KBComment>();
	private KBCommentPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}