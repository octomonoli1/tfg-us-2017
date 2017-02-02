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

package com.liferay.portal.configuration;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class ConfigurationImplTest {

	@BeforeClass
	public static void setUpClass() {
		URL.setURLStreamHandlerFactory(
			new URLStreamHandlerFactory() {

				@Override
				public URLStreamHandler createURLStreamHandler(
					String protocol) {

					if (!protocol.equals("test")) {
						return null;
					}

					return new URLStreamHandler() {

						@Override
						protected URLConnection openConnection(URL url) {
							return new URLConnection(url) {

								@Override
								public void connect() {
								}

								@Override
								public InputStream getInputStream()
									throws IOException {

									byte[] data = _testURLResources.get(url);

									if (data == null) {
										throw new IOException(
											"Unable to open " + url);
									}

									return new UnsyncByteArrayInputStream(data);
								}

							};
						}

					};
				}

			});
	}

	@Test
	public void testLoadEmptyProperties() throws Exception {
		TestResourceClassLoader testResourceClassLoader =
			new TestResourceClassLoader();

		testResourceClassLoader.addPropertiesResource(
			ConfigurationImplTest.class.getName(), StringPool.BLANK);

		ConfigurationImpl configurationImpl = new ConfigurationImpl(
			testResourceClassLoader, ConfigurationImplTest.class.getName(),
			CompanyConstants.SYSTEM, null);

		Properties properties = configurationImpl.getProperties();

		Assert.assertTrue(properties.isEmpty());
	}

	@Test
	public void testMultiValueProperty() throws IOException {
		TestResourceClassLoader testResourceClassLoader =
			new TestResourceClassLoader();

		testResourceClassLoader.addPropertiesResource(
			ConfigurationImplTest.class.getName(),
			"key1=value1,value2\nkey2=value3\nkey2=value4");

		ConfigurationImpl configurationImpl = new ConfigurationImpl(
			testResourceClassLoader, ConfigurationImplTest.class.getName(),
			CompanyConstants.SYSTEM, null);

		Assert.assertEquals("value1,value2", configurationImpl.get("key1"));
		Assert.assertEquals("value3,value4", configurationImpl.get("key2"));
		Assert.assertArrayEquals(
			new String[] {"value1", "value2"},
			configurationImpl.getArray("key1"));
		Assert.assertArrayEquals(
			new String[] {"value3", "value4"},
			configurationImpl.getArray("key2"));
	}

	@Test
	public void testMultiValuePropertyVariableInterpolation()
		throws IOException {

		TestResourceClassLoader testResourceClassLoader =
			new TestResourceClassLoader();

		testResourceClassLoader.addPropertiesResource(
			ConfigurationImplTest.class.getName(),
			"key1=value1\nkey2=${key1},value2");

		ConfigurationImpl configurationImpl = new ConfigurationImpl(
			testResourceClassLoader, ConfigurationImplTest.class.getName(),
			CompanyConstants.SYSTEM, null);

		Assert.assertEquals("value1", configurationImpl.get("key1"));

		// This is a limitation of Commons Configuration where it does not
		// resolve variables for multivalue properties with variable
		// interpolation when you get the value as a single line although
		// variables resolve when you get the values as an array

		Assert.assertEquals("${key1},value2", configurationImpl.get("key2"));
		Assert.assertArrayEquals(
			new String[] {"value1", "value2"},
			configurationImpl.getArray("key2"));
	}

	@Test
	public void testPropertyVariableInterpolation() throws IOException {
		TestResourceClassLoader testResourceClassLoader =
			new TestResourceClassLoader();

		testResourceClassLoader.addPropertiesResource(
			ConfigurationImplTest.class.getName(),
			"key1=value1\nkey2=${key1}value2");

		ConfigurationImpl configurationImpl = new ConfigurationImpl(
			testResourceClassLoader, ConfigurationImplTest.class.getName(),
			CompanyConstants.SYSTEM, null);

		Assert.assertEquals("value1", configurationImpl.get("key1"));
		Assert.assertEquals("value1value2", configurationImpl.get("key2"));
	}

	@Test
	public void testSystemPropertyOverrideProperties() throws IOException {
		TestResourceClassLoader testResourceClassLoader =
			new TestResourceClassLoader();

		testResourceClassLoader.addPropertiesResource(
			ConfigurationImplTest.class.getName(),
			"namespace.key1=value1\nnamespace.key2=value2");

		ConfigurationImpl configurationImpl = new ConfigurationImpl(
			testResourceClassLoader, ConfigurationImplTest.class.getName(),
			CompanyConstants.SYSTEM, null);

		Properties properties = configurationImpl.getProperties(
			"namespace.", false);

		Assert.assertEquals(2, properties.size());
		Assert.assertEquals("value1", properties.get("namespace.key1"));
		Assert.assertEquals("value2", properties.get("namespace.key2"));

		properties = configurationImpl.getProperties("namespace.", true);

		Assert.assertEquals(2, properties.size());
		Assert.assertEquals("value1", properties.get("key1"));
		Assert.assertEquals("value2", properties.get("key2"));

		configurationImpl.clearCache();

		System.setProperty(
			ConfigurationImplTest.class.getName() + ":namespace.key2",
			"valuex");

		try {
			properties = configurationImpl.getProperties("namespace.", false);

			Assert.assertEquals(2, properties.size());
			Assert.assertEquals("value1", properties.get("namespace.key1"));
			Assert.assertEquals("valuex", properties.get("namespace.key2"));

			properties = configurationImpl.getProperties("namespace.", true);

			Assert.assertEquals(2, properties.size());
			Assert.assertEquals("value1", properties.get("key1"));
			Assert.assertEquals("valuex", properties.get("key2"));

			configurationImpl.clearCache();
		}
		finally {
			System.clearProperty(
				ConfigurationImplTest.class.getName() + ":namespace.key2");

			Assert.assertEquals(
				"value1", configurationImpl.get("namespace.key1"));
			Assert.assertEquals(
				"value2", configurationImpl.get("namespace.key2"));
		}
	}

	@Test
	public void testSystemPropertyOverrideSingleValue() throws IOException {
		TestResourceClassLoader testResourceClassLoader =
			new TestResourceClassLoader();

		testResourceClassLoader.addPropertiesResource(
			ConfigurationImplTest.class.getName(), "key1=value1\nkey2=value2");

		ConfigurationImpl configurationImpl = new ConfigurationImpl(
			testResourceClassLoader, ConfigurationImplTest.class.getName(),
			CompanyConstants.SYSTEM, null);

		Assert.assertEquals("value1", configurationImpl.get("key1"));
		Assert.assertEquals("value2", configurationImpl.get("key2"));

		configurationImpl.clearCache();

		System.setProperty(
			ConfigurationImplTest.class.getName() + ":key2", "valuex");

		try {
			Assert.assertEquals("value1", configurationImpl.get("key1"));
			Assert.assertEquals("valuex", configurationImpl.get("key2"));

			configurationImpl.clearCache();
		}
		finally {
			System.clearProperty(
				ConfigurationImplTest.class.getName() + ":key2");

			Assert.assertEquals("value1", configurationImpl.get("key1"));
			Assert.assertEquals("value2", configurationImpl.get("key2"));
		}
	}

	private static final Map<URL, byte[]> _testURLResources = new HashMap<>();

	private static class TestResourceClassLoader extends ClassLoader {

		public void addPropertiesResource(String name, String content)
			throws IOException {

			UUID uuid = UUID.randomUUID();

			URL url = new URL("test://" + uuid.toString());

			_testURLResources.put(url, content.getBytes(StringPool.UTF8));

			_resources.put(name.concat(".properties"), url);
		}

		@Override
		public URL getResource(String name) {
			URL url = _resources.get(name);

			if (url == null) {
				url = super.getResource(name);
			}

			return url;
		}

		private TestResourceClassLoader() {
			super(ConfigurationImplTest.class.getClassLoader());
		}

		private final Map<String, URL> _resources = new HashMap<>();

	}

}