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

package com.liferay.document.library.webdav.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.webdav.methods.Method;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <p>
 * Based on <a href="http://www.webdav.org/neon/litmus/">litmus</a> 0.12.1
 * "basic" test.
 * </p>
 *
 * @author Alexander Chow
 */
@RunWith(Arquillian.class)
public class WebDAVLitmusBasicTest extends BaseWebDAVTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			WebDAVEnvironmentConfigTestRule.INSTANCE);

	@Test
	public void test02Options() {
		Tuple tuple = service(Method.OPTIONS, StringPool.BLANK, null, null);

		assertCode(HttpServletResponse.SC_OK, tuple);

		Map<String, String> headers = getHeaders(tuple);

		String allowMethodNames = headers.get("Allow");

		for (String methodName : Method.SUPPORTED_METHOD_NAMES) {
			Assert.assertTrue(
				"Does not allow " + methodName,
				allowMethodNames.contains(methodName));
		}
	}

	@Test
	public void test03PutGet() {
		putGet("res");
	}

	@Test
	public void test04UTF8() {

		// Create

		putGet("res-\u20AC");

		// Modify

		assertCode(
			HttpServletResponse.SC_METHOD_NOT_ALLOWED,
			service(Method.MKCOL, "res-\u20AC", null, null));

		// Delete

		assertCode(
			HttpServletResponse.SC_NO_CONTENT, serviceDelete("res-\u20AC"));
	}

	@Test
	public void test05PutNoParent() {
		assertCode(
			HttpServletResponse.SC_CONFLICT,
			service(Method.MKCOL, "409me/noparent", null, null));
		assertCode(
			HttpServletResponse.SC_CONFLICT,
			servicePut("409me/noparent.txt", _TEST_CONTENT.getBytes()));
	}

	@Test
	public void test06DeleteNull() {
		assertCode(HttpServletResponse.SC_NOT_FOUND, serviceDelete("404me"));
	}

	@Test
	public void test07DeleteFragment() {
		assertCode(
			HttpServletResponse.SC_CREATED,
			service(Method.MKCOL, "frag", null, null));
		assertCode(
			HttpServletResponse.SC_NOT_FOUND, serviceDelete("frag/#ment"));
		assertCode(HttpServletResponse.SC_NO_CONTENT, serviceDelete("frag"));
	}

	@Test
	public void test08Col() {

		// Create

		assertCode(
			HttpServletResponse.SC_CREATED,
			service(Method.MKCOL, "col", null, null));

		// Create duplicate

		assertCode(
			HttpServletResponse.SC_METHOD_NOT_ALLOWED,
			service(Method.MKCOL, "col", null, null));

		// Delete

		assertCode(HttpServletResponse.SC_NO_CONTENT, serviceDelete("col"));
	}

	@Test
	public void test09MkcolNoParent() {
		assertCode(
			HttpServletResponse.SC_CONFLICT,
			service(Method.MKCOL, "409me/col", null, null));
	}

	@Test
	public void test10MkcolWithBody() {
		Map<String, String> headers = new HashMap<>();

		headers.put(HttpHeaders.CONTENT_TYPE, "xyz-foo/bar-512");

		assertCode(
			HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
			service(
				Method.MKCOL, "mkcolbody", headers, _TEST_CONTENT.getBytes()));
	}

	protected void putGet(String fileName) {
		assertCode(
			HttpServletResponse.SC_CREATED,
			servicePut(fileName, _TEST_CONTENT.getBytes()));

		Tuple tuple = serviceGet(fileName);

		assertCode(HttpServletResponse.SC_OK, tuple);
		Assert.assertArrayEquals(
			_TEST_CONTENT.getBytes(), getResponseBody(tuple));
	}

	private static final String _TEST_CONTENT =
		"LIFERAY\nEnterprise. Open Source. For Life.";

}