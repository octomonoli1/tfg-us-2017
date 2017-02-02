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

package com.liferay.blogs.trackback.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryLocalServiceUtil;
import com.liferay.portal.kernel.comment.CommentManagerUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.IdentityServiceContextFunction;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.blogs.linkback.LinkbackConsumerUtil;
import com.liferay.portlet.blogs.trackback.Trackback;
import com.liferay.portlet.blogs.trackback.TrackbackImpl;

import java.util.Date;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adolfo Pérez
 */
@RunWith(Arquillian.class)
@Sync
public class TrackbackImplTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@Test
	public void testAddTrackback() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		_blogsEntry = BlogsEntryLocalServiceUtil.addEntry(
			TestPropsValues.getUserId(), StringUtil.randomString(),
			StringUtil.randomString(), new Date(), serviceContext);

		IdentityServiceContextFunction serviceContextFunction =
			new IdentityServiceContextFunction(serviceContext);

		CommentManagerUtil.addComment(
			TestPropsValues.getUserId(), TestPropsValues.getGroupId(),
			BlogsEntry.class.getName(), _blogsEntry.getEntryId(),
			StringUtil.randomString(), serviceContextFunction);

		int initialCommentsCount = CommentManagerUtil.getCommentsCount(
			BlogsEntry.class.getName(), _blogsEntry.getEntryId());

		Trackback trackback = new TrackbackImpl();

		ThemeDisplay themeDisplay = new ThemeDisplay();

		Company company = CompanyLocalServiceUtil.getCompany(
			TestPropsValues.getCompanyId());

		themeDisplay.setCompany(company);

		trackback.addTrackback(
			_blogsEntry, themeDisplay, StringUtil.randomString(),
			StringUtil.randomString(), StringUtil.randomString(),
			StringUtil.randomString(), serviceContextFunction);

		Assert.assertEquals(
			initialCommentsCount + 1,
			CommentManagerUtil.getCommentsCount(
				BlogsEntry.class.getName(), _blogsEntry.getEntryId()));

		LinkbackConsumerUtil.verifyNewTrackbacks();
	}

	@DeleteAfterTestRun
	private BlogsEntry _blogsEntry;

}