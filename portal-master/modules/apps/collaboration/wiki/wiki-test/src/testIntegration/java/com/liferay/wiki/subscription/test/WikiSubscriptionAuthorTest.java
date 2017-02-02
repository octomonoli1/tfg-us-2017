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

package com.liferay.wiki.subscription.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.SynchronousMailTestRule;
import com.liferay.portlet.subscriptions.test.BaseSubscriptionAuthorTestCase;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiNodeLocalServiceUtil;
import com.liferay.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.wiki.util.test.WikiTestUtil;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author José Ángel Jiménez
 */
@RunWith(Arquillian.class)
@Sync
public class WikiSubscriptionAuthorTest extends BaseSubscriptionAuthorTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), SynchronousMailTestRule.INSTANCE);

	@Override
	protected long addBaseModel(long userId, long containerModelId)
		throws Exception {

		WikiPage page = WikiTestUtil.addPage(
			userId, group.getGroupId(), containerModelId,
			RandomTestUtil.randomString(), true);

		return page.getResourcePrimKey();
	}

	@Override
	protected long addContainerModel(long userId, long containerModelId)
		throws Exception {

		WikiNode node = WikiTestUtil.addNode(
			userId, group.getGroupId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString());

		return node.getNodeId();
	}

	@Override
	protected void addSubscription(long userId, long containerModelId)
		throws Exception {

		WikiNodeLocalServiceUtil.subscribeNode(userId, containerModelId);
	}

	@Override
	protected void updateBaseModel(long userId, long baseModelId)
		throws Exception {

		WikiPage page = WikiPageLocalServiceUtil.getPage(baseModelId, true);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(page.getGroupId(), userId);

		WikiTestUtil.populateNotificationsServiceContext(
			serviceContext, Constants.UPDATE);

		WikiTestUtil.updatePage(
			page, userId, page.getTitle(), RandomTestUtil.randomString(), true,
			serviceContext);
	}

}