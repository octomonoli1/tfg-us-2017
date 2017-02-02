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

package com.liferay.portal.kernel.dao.search;

import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.junit.After;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Roberto Díaz
 */
public class SearchContainerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@After
	public void tearDown() {
		_searchContainer = null;
	}

	@Test
	public void testCalculateCurWhenEmptyResultsPage() {
		buildSearchContainer(2);

		_searchContainer.setTotal(10);

		Assert.assertEquals(1, _searchContainer.getCur());
	}

	@Test
	public void testCalculateCurWhenFullResultsPage() {
		buildSearchContainer(2);

		_searchContainer.setTotal(20);

		Assert.assertEquals(1, _searchContainer.getCur());
	}

	@Test
	public void testCalculateCurWhenNoResults() {
		buildSearchContainer(2);

		_searchContainer.setTotal(0);

		Assert.assertEquals(1, _searchContainer.getCur());
	}

	@Test
	public void testCalculateCurWhenResultsPage() {
		buildSearchContainer(2);

		_searchContainer.setTotal(80);

		Assert.assertEquals(2, _searchContainer.getCur());
	}

	@Test
	public void testCalculateStartAndEndWhenEmptyResultsPage() {
		buildSearchContainer(2);

		_searchContainer.setTotal(10);

		Assert.assertEquals(0, _searchContainer.getStart());
		Assert.assertEquals(20, _searchContainer.getEnd());
	}

	@Test
	public void testCalculateStartAndEndWhenFullResultsPage() {
		buildSearchContainer(2);

		_searchContainer.setTotal(20);

		Assert.assertEquals(0, _searchContainer.getStart());
		Assert.assertEquals(20, _searchContainer.getEnd());
	}

	@Test
	public void testCalculateStartAndEndWhenNoResults() {
		buildSearchContainer(2);

		_searchContainer.setTotal(0);

		Assert.assertEquals(0, _searchContainer.getStart());
		Assert.assertEquals(20, _searchContainer.getEnd());
	}

	@Test
	public void testCalculateStartAndEndWhenResultsPage() {
		buildSearchContainer(2);

		_searchContainer.setTotal(80);

		Assert.assertEquals(20, _searchContainer.getStart());
		Assert.assertEquals(40, _searchContainer.getEnd());
	}

	@Test
	public void testNotCalculateCurWhenNoResultsAndInitialPage() {
		buildSearchContainer(1);

		_searchContainer.setTotal(0);

		Assert.assertEquals(false, _searchContainer.isRecalculateCur());
	}

	@Test
	public void testNotCalculateStartAndEndWhenNoResultsAndInitialPage() {
		buildSearchContainer(1);

		_searchContainer.setTotal(0);

		Assert.assertEquals(0, _searchContainer.getStart());
		Assert.assertEquals(20, _searchContainer.getEnd());
	}

	protected void buildSearchContainer(int cur) {
		PortletRequest portletRequest = PowerMockito.mock(PortletRequest.class);

		PortletURL portletURL = PowerMockito.mock(PortletURL.class);

		_searchContainer = new SearchContainer<Object>(
			portletRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, cur,
			_DEFAULT_DELTA, portletURL, null, null);
	}

	private static final int _DEFAULT_DELTA = 20;

	private SearchContainer<?> _searchContainer;

}