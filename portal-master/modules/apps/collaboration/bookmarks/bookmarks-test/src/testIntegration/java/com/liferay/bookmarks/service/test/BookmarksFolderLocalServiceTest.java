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

package com.liferay.bookmarks.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.model.BookmarksFolderConstants;
import com.liferay.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.bookmarks.service.permission.BookmarksFolderPermissionChecker;
import com.liferay.bookmarks.util.test.BookmarksTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.Subscription;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.ResourceBlockServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Brian Wing Shun Chan
 * @author Roberto Díaz
 * @author Sergio González
 */
@RunWith(Arquillian.class)
@Sync
public class BookmarksFolderLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testAddSubfolderPermission() throws Exception {
		BookmarksFolder folder = BookmarksTestUtil.addFolder(
			_group.getGroupId(), RandomTestUtil.randomString());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		Role role = RoleLocalServiceUtil.addRole(
			TestPropsValues.getUserId(), null, 0, StringUtil.randomString(),
			null, null, RoleConstants.TYPE_SITE, null, serviceContext);

		ResourceBlockServiceUtil.addCompanyScopePermission(
			_group.getGroupId(), _group.getCompanyId(),
			BookmarksFolder.class.getName(), role.getRoleId(),
			ActionKeys.ADD_SUBFOLDER);

		User user = UserTestUtil.addGroupUser(_group, role.getName());

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(user);

		Assert.assertTrue(
			BookmarksFolderPermissionChecker.contains(
				permissionChecker, _group.getGroupId(), folder.getFolderId(),
				ActionKeys.ADD_FOLDER));
	}

	@Test
	public void testGetNoAssetFolders() throws Exception {
		BookmarksFolder folder = BookmarksTestUtil.addFolder(
			_group.getGroupId(), RandomTestUtil.randomString());

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
			BookmarksFolder.class.getName(), folder.getFolderId());

		Assert.assertNotNull(assetEntry);

		AssetEntryLocalServiceUtil.deleteAssetEntry(assetEntry);

		List<BookmarksFolder> folders =
			BookmarksFolderLocalServiceUtil.getNoAssetFolders();

		Assert.assertEquals(1, folders.size());
		Assert.assertEquals(folder, folders.get(0));
	}

	@Test
	public void testMoveFolder() throws Exception {
		BookmarksFolder folder = BookmarksTestUtil.addFolder(
			_group.getGroupId(), RandomTestUtil.randomString());

		long initialParentFolderId = folder.getParentFolderId();

		BookmarksFolder destinationFolder = BookmarksTestUtil.addFolder(
			_group.getGroupId(), RandomTestUtil.randomString());

		folder = BookmarksFolderLocalServiceUtil.moveFolder(
			folder.getFolderId(), destinationFolder.getFolderId());

		Assert.assertNotEquals(
			initialParentFolderId, folder.getParentFolderId());
		Assert.assertEquals(
			destinationFolder.getFolderId(), folder.getParentFolderId());
	}

	@Test
	public void testSubscribeFolder() throws Exception {
		BookmarksFolder folder = BookmarksTestUtil.addFolder(
			_group.getGroupId(), RandomTestUtil.randomString());

		testSubscribeFolder(folder.getFolderId(), folder.getFolderId());
	}

	@Test
	public void testSubscribeRootFolder() throws Exception {
		testSubscribeFolder(
			BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			_group.getGroupId());
	}

	@Test
	public void testUnsubscribeFolder() throws Exception {
		BookmarksFolder folder = BookmarksTestUtil.addFolder(
			_group.getGroupId(), RandomTestUtil.randomString());

		testUnsubscribeFolder(folder.getFolderId(), folder.getFolderId());
	}

	@Test
	public void testUnsubscribeRootFolder() throws Exception {
		testUnsubscribeFolder(
			BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			_group.getGroupId());
	}

	protected void testSubscribeFolder(
			long folderId, long expectedSubscriptionClassPK)
		throws Exception {

		int initialUserSubscriptionsCount =
			SubscriptionLocalServiceUtil.getUserSubscriptionsCount(
				TestPropsValues.getUserId());

		BookmarksFolderLocalServiceUtil.subscribeFolder(
			TestPropsValues.getUserId(), _group.getGroupId(), folderId);

		Assert.assertEquals(
			initialUserSubscriptionsCount + 1,
			SubscriptionLocalServiceUtil.getUserSubscriptionsCount(
				TestPropsValues.getUserId()));

		boolean exists = false;

		List<Subscription> subscriptions =
			SubscriptionLocalServiceUtil.getUserSubscriptions(
				TestPropsValues.getUserId(), BookmarksFolder.class.getName());

		for (Subscription subscription : subscriptions) {
			if (subscription.getClassName().equals(
					BookmarksFolder.class.getName()) &&
				(subscription.getClassPK() == expectedSubscriptionClassPK)) {

				exists = true;

				break;
			}
		}

		Assert.assertTrue("Subscription does not exist", exists);
	}

	protected void testUnsubscribeFolder(
			long folderId, long expectedSubscriptionClassPK)
		throws Exception {

		int initialUserSubscriptionsCount =
			SubscriptionLocalServiceUtil.getUserSubscriptionsCount(
				TestPropsValues.getUserId());

		BookmarksFolderLocalServiceUtil.subscribeFolder(
			TestPropsValues.getUserId(), _group.getGroupId(), folderId);

		BookmarksFolderLocalServiceUtil.unsubscribeFolder(
			TestPropsValues.getUserId(), _group.getGroupId(), folderId);

		Assert.assertEquals(
			initialUserSubscriptionsCount,
			SubscriptionLocalServiceUtil.getUserSubscriptionsCount(
				TestPropsValues.getUserId()));

		List<Subscription> subscriptions =
			SubscriptionLocalServiceUtil.getUserSubscriptions(
				TestPropsValues.getUserId(), BookmarksFolder.class.getName());

		for (Subscription subscription : subscriptions) {
			Assert.assertFalse(
				"Subscription exists",
				subscription.getClassName().equals(
					BookmarksFolder.class.getName()) &&
				(subscription.getClassPK() == expectedSubscriptionClassPK));
		}
	}

	@DeleteAfterTestRun
	private Group _group;

}