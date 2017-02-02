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

package com.liferay.portal.search.elasticsearch.internal.stats;

import com.liferay.portal.search.elasticsearch.internal.ElasticsearchIndexingFixture;
import com.liferay.portal.search.unit.test.BaseIndexingTestCase;
import com.liferay.portal.search.unit.test.IndexingFixture;
import com.liferay.portal.search.unit.test.stats.BaseStatisticsTestCase;

import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class StatisticsTest extends BaseStatisticsTestCase {

	@Override
	@Test
	public void testGetStats() throws Exception {
		super.testGetStats();
	}

	@Override
	protected IndexingFixture createIndexingFixture() {
		return new ElasticsearchIndexingFixture(
			StatisticsTest.class.getSimpleName(),
			BaseIndexingTestCase.COMPANY_ID);
	}

}