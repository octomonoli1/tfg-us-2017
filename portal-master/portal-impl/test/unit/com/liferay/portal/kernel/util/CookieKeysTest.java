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

package com.liferay.portal.kernel.util;

import com.liferay.portal.util.PropsImpl;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Raymond Augé
 */
public class CookieKeysTest {

	@Before
	public void setUp() {
		PropsUtil.setProps(new PropsImpl());
	}

	@Test
	public void testDomain1() throws Exception {
		String domain = CookieKeys.getDomain("www.liferay.com");

		Assert.assertEquals(".liferay.com", domain);
	}

	@Test
	public void testDomain2() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setServerName("www.liferay.com");

		String domain = CookieKeys.getDomain(mockHttpServletRequest);

		Assert.assertEquals(".liferay.com", domain);
	}

	@Test
	public void testDomain3() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setServerName("www.liferay.com");

		Field field = ReflectionUtil.getDeclaredField(
			CookieKeys.class, "_SESSION_COOKIE_DOMAIN");

		Object value = field.get(null);

		try {
			field.set(null, "www.example.com");

			String domain = CookieKeys.getDomain(mockHttpServletRequest);

			Assert.assertEquals("www.example.com", domain);
		}
		finally {
			field.set(null, value);
		}
	}

	@Test
	public void testDomain4() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setServerName("www.liferay.com");

		Field field = ReflectionUtil.getDeclaredField(
			CookieKeys.class, "_SESSION_COOKIE_USE_FULL_HOSTNAME");

		Object value = field.get(null);

		try {
			field.set(null, Boolean.FALSE);

			String domain = CookieKeys.getDomain(mockHttpServletRequest);

			Assert.assertEquals(".liferay.com", domain);
		}
		finally {
			field.set(null, value);
		}
	}

	@Test
	public void testDomain5() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setServerName("www.liferay.com");

		Field field = ReflectionUtil.getDeclaredField(
			CookieKeys.class, "_SESSION_COOKIE_USE_FULL_HOSTNAME");

		Object value = field.get(null);

		try {
			field.set(null, Boolean.TRUE);

			String domain = CookieKeys.getDomain(mockHttpServletRequest);

			Assert.assertEquals(StringPool.BLANK, domain);
		}
		finally {
			field.set(null, value);
		}
	}

}