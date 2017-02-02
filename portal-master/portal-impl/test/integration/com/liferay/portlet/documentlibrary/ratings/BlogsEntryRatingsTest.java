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

package com.liferay.portlet.documentlibrary.ratings;

import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryLocalServiceUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.blogs.util.test.BlogsTestUtil;
import com.liferay.portlet.ratings.test.BaseRatingsTestCase;

import org.junit.ClassRule;
import org.junit.Rule;

/**
 * @author Cristina González
 */
public class BlogsEntryRatingsTest extends BaseRatingsTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Override
	protected BaseModel<?> addBaseModel(
			BaseModel<?> parentBaseModel, ServiceContext serviceContext)
		throws Exception {

		return BlogsTestUtil.addEntryWithWorkflow(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(), false,
			serviceContext);
	}

	@Override
	protected BaseModel<?> deleteBaseModel(
			BaseModel<?> baseModel, ServiceContext serviceContext)
		throws Exception {

		return BlogsEntryLocalServiceUtil.deleteEntry((BlogsEntry)baseModel);
	}

	@Override
	protected Class<?> getBaseModelClass() {
		return BlogsEntry.class;
	}

}