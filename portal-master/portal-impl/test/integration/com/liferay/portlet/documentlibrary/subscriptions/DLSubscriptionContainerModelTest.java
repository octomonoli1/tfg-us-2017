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

package com.liferay.portlet.documentlibrary.subscriptions;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.test.randomizerbumpers.TikaSafeRandomizerBumper;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.SynchronousMailTestRule;
import com.liferay.portlet.documentlibrary.util.test.DLAppTestUtil;
import com.liferay.portlet.subscriptions.test.BaseSubscriptionContainerModelTestCase;

import org.junit.ClassRule;
import org.junit.Rule;

/**
 * @author Sergio González
 * @author Roberto Díaz
 */
@Sync
public class DLSubscriptionContainerModelTest
	extends BaseSubscriptionContainerModelTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), SynchronousMailTestRule.INSTANCE);

	@Override
	protected long addBaseModel(long userId, long containerModelId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), userId);

		DLAppTestUtil.populateNotificationsServiceContext(
			serviceContext, Constants.ADD);

		FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
			userId, group.getGroupId(), containerModelId,
			RandomTestUtil.randomString() + ".txt", ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomBytes(TikaSafeRandomizerBumper.INSTANCE),
			serviceContext);

		return fileEntry.getFileEntryId();
	}

	@Override
	protected long addContainerModel(long userId, long containerModelId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), userId);

		Folder folder = DLAppLocalServiceUtil.addFolder(
			userId, group.getGroupId(), containerModelId,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			serviceContext);

		return folder.getFolderId();
	}

	@Override
	protected void addSubscriptionContainerModel(long containerModelId)
		throws Exception {

		DLAppLocalServiceUtil.subscribeFolder(
			user.getUserId(), group.getGroupId(), containerModelId);
	}

	@Override
	protected void updateBaseModel(long userId, long baseModelId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), userId);

		DLAppTestUtil.populateNotificationsServiceContext(
			serviceContext, Constants.UPDATE);

		DLAppLocalServiceUtil.updateFileEntry(
			userId, baseModelId, RandomTestUtil.randomString(),
			ContentTypes.TEXT_PLAIN, RandomTestUtil.randomString(),
			StringPool.BLANK, StringPool.BLANK, false,
			RandomTestUtil.randomBytes(TikaSafeRandomizerBumper.INSTANCE),
			serviceContext);
	}

}