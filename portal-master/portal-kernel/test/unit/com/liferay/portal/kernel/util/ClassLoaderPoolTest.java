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

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;

import java.net.URL;
import java.net.URLClassLoader;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class ClassLoaderPoolTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		CodeCoverageAssertor.INSTANCE;

	@Before
	public void setUp() {
		Class<?> clazz = getClass();

		PortalClassLoaderUtil.setClassLoader(clazz.getClassLoader());

		_classLoaders = ReflectionTestUtil.getFieldValue(
			ClassLoaderPool.class, "_classLoaders");

		_classLoaders.clear();

		_contextNames = ReflectionTestUtil.getFieldValue(
			ClassLoaderPool.class, "_contextNames");

		_contextNames.clear();
	}

	@Test
	public void testConstructor() {
		new ClassLoaderPool();
	}

	@Test
	public void testGetClassLoaderWithInvalidContextName() {
		ClassLoader classLoader = new URLClassLoader(new URL[0]);

		ClassLoaderPool.register(_CONTEXT_NAME, classLoader);

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		Assert.assertSame(
			contextClassLoader,
			ClassLoaderPool.getClassLoader(StringPool.NULL));
		Assert.assertSame(
			contextClassLoader, ClassLoaderPool.getClassLoader(null));
	}

	@Test
	public void testGetClassLoaderWithValidContextName() {
		ClassLoader classLoader = new URLClassLoader(new URL[0]);

		ClassLoaderPool.register(_CONTEXT_NAME, classLoader);

		Assert.assertSame(
			classLoader, ClassLoaderPool.getClassLoader(_CONTEXT_NAME));
	}

	@Test
	public void testGetContextNameWithInvalidClassLoader() {
		ClassLoader classLoader = new URLClassLoader(new URL[0]);

		ClassLoaderPool.register(_CONTEXT_NAME, classLoader);

		Assert.assertEquals(
			StringPool.NULL,
			ClassLoaderPool.getContextName(new URLClassLoader(new URL[0])));
		Assert.assertEquals(
			StringPool.NULL, ClassLoaderPool.getContextName(null));
	}

	@Test
	public void testGetContextNameWithValidClassLoader() {
		ClassLoader classLoader = new URLClassLoader(new URL[0]);

		ClassLoaderPool.register(_CONTEXT_NAME, classLoader);

		Assert.assertEquals(
			_CONTEXT_NAME, ClassLoaderPool.getContextName(classLoader));
	}

	@Test
	public void testRegister() {
		ClassLoader classLoader = new URLClassLoader(new URL[0]);

		ClassLoaderPool.register(_CONTEXT_NAME, classLoader);

		Assert.assertEquals(1, _contextNames.size());
		Assert.assertEquals(1, _classLoaders.size());
		Assert.assertSame(classLoader, _classLoaders.get(_CONTEXT_NAME));
		Assert.assertEquals(_CONTEXT_NAME, _contextNames.get(classLoader));
	}

	@Test
	public void testRegisterWithNullClassLoader() {
		try {
			ClassLoaderPool.register(StringPool.BLANK, null);

			Assert.fail();
		}
		catch (NullPointerException npe) {
		}
	}

	@Test
	public void testRegisterWithNullContextName() {
		try {
			ClassLoaderPool.register(null, null);

			Assert.fail();
		}
		catch (NullPointerException npe) {
		}
	}

	@Test
	public void testUnregisterWithInvalidClassLoader() {
		ClassLoaderPool.unregister(new URLClassLoader(new URL[0]));

		assertEmptyMaps();
	}

	@Test
	public void testUnregisterWithInvalidContextName() {
		ClassLoaderPool.unregister(_CONTEXT_NAME);

		assertEmptyMaps();
	}

	@Test
	public void testUnregisterWithValidClassLoader() {
		ClassLoader classLoader = new URLClassLoader(new URL[0]);

		ClassLoaderPool.register(_CONTEXT_NAME, classLoader);

		ClassLoaderPool.unregister(classLoader);

		assertEmptyMaps();
	}

	@Test
	public void testUnregisterWithValidContextName() {
		ClassLoader classLoader = new URLClassLoader(new URL[0]);

		ClassLoaderPool.register(_CONTEXT_NAME, classLoader);

		ClassLoaderPool.unregister(_CONTEXT_NAME);

		assertEmptyMaps();
	}

	protected void assertEmptyMaps() {
		Assert.assertTrue(_contextNames.isEmpty());
		Assert.assertTrue(_classLoaders.isEmpty());
	}

	private static final String _CONTEXT_NAME = "contextName";

	private static Map<String, ClassLoader> _classLoaders;
	private static Map<ClassLoader, String> _contextNames;

}