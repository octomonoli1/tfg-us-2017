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

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.JavaDetector;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.test.rule.PACLTestRule;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class ReflectionTest {

	public static final String TEST_FIELD_1 = "TEST_FIELD_1";

	@ClassRule
	@Rule
	public static final PACLTestRule paclTestRule = new PACLTestRule();

	@BeforeClass
	public static void setUpClass() {
		Assume.assumeTrue(JavaDetector.isJDK7());
	}

	@Test
	public void testPlugin1() throws Exception {
		Class<?> clazz = getClass();

		clazz.getDeclaredField("_log");
	}

	@Test
	public void testPlugin2() throws Exception {
		Class<?> clazz = getClass();

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
		}
	}

	@Test
	public void testPlugin3() throws Exception {
		Class<?> clazz = getClass();

		Field[] fields = clazz.getFields();

		for (Field field : fields) {
			field.setAccessible(false);
		}
	}

	@Test
	public void testPlugin4() throws Exception {
		Class<?> clazz = getClass();

		clazz.getField("TEST_FIELD_1");
	}

	@Test
	public void testPlugin5() throws Exception {
		Class<?> clazz = getClass();

		Field field = clazz.getField("TEST_FIELD_1");

		field.setAccessible(false);
	}

	@Test
	public void testPlugin6() throws Exception {
		Class<?> clazz = getClass();

		Field field = clazz.getField("TEST_FIELD_1");

		boolean accessible = field.isAccessible();

		field.setAccessible(true);

		field.setAccessible(accessible);
	}

	@Test
	public void testPortal1() throws Exception {
		Class<?> clazz = PropsKeys.class;

		clazz.getField("ADMIN_DEFAULT_GROUP_NAMES");
	}

	@Test
	public void testPortal2() throws Exception {
		try {
			Class<?> clazz = PropsKeys.class;

			Field field = clazz.getField("ADMIN_DEFAULT_GROUP_NAMES");

			field.setAccessible(false);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testPortal3() throws Exception {
		try {
			Class<?> clazz = ReleaseInfo.class;

			Field[] fields = clazz.getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);
			}

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testPortal4() throws Exception {
		try {
			Class<?> clazz = ReleaseInfo.class;

			Field[] fields = clazz.getFields();

			for (Field field : fields) {
				field.setAccessible(false);
			}

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testPortal5() throws Exception {
		try {
			Class<?> clazz = UserLocalServiceUtil.class;

			clazz.getDeclaredField("_service");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	private static final String _TEST_FIELD_2 = "TEST_FIELD_2";

	private static final Log _log = LogFactoryUtil.getLog(ReflectionTest.class);

	static {

		// Prevent compiler from removing the unused fields

		if (_log.isDebugEnabled()) {
			_log.debug(_TEST_FIELD_2);
		}
	}

}