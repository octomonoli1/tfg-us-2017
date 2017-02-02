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

package com.liferay.portal.app.license.test;

import com.liferay.portal.app.license.AppLicenseVerifier;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Amos Fong
 */
@RunWith(Arquillian.class)
public class AppLicenseVerifierTest {

	@Before
	public void setUp() throws BundleException {
		bundle.start();

		_bundleContext = bundle.getBundleContext();

		_serviceTracker = new ServiceTracker<>(
			_bundleContext, AppLicenseVerifier.class, null);

		_serviceTracker.open();

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("version", "1.0.0");

		_bundleContext.registerService(
			AppLicenseVerifier.class, new FailAppLicenseVerifier(), properties);

		properties = new Hashtable<>();

		properties.put("version", "1.0.1");

		_bundleContext.registerService(
			AppLicenseVerifier.class, new PassAppLicenseVerifier(), properties);
	}

	@After
	public void tearDown() throws BundleException {
		_serviceTracker.close();

		bundle.stop();
	}

	@Test(expected = Exception.class)
	public void testVerifyFailure() throws Exception {
		Filter filter = FrameworkUtil.createFilter("(version=1.0.0)");

		Map<ServiceReference<AppLicenseVerifier>, AppLicenseVerifier>
			serviceReferences = _serviceTracker.getTracked();

		for (Map.Entry<ServiceReference<AppLicenseVerifier>, AppLicenseVerifier>
				entry : serviceReferences.entrySet()) {

			if (!filter.match(entry.getKey())) {
				continue;
			}

			AppLicenseVerifier appLicenseVerifier = entry.getValue();

			appLicenseVerifier.verify(bundle, "", "", "");

			break;
		}
	}

	@Test
	public void testVerifyPass() throws Exception {
		Filter filter = FrameworkUtil.createFilter("(version=1.0.1)");

		Map<ServiceReference<AppLicenseVerifier>, AppLicenseVerifier>
			serviceReferences = _serviceTracker.getTracked();

		for (Map.Entry<ServiceReference<AppLicenseVerifier>, AppLicenseVerifier>
				entry : serviceReferences.entrySet()) {

			ServiceReference serviceReference = entry.getKey();

			if (!filter.match(serviceReference)) {
				continue;
			}

			AppLicenseVerifier appLicenseVerifier = entry.getValue();

			appLicenseVerifier.verify(bundle, "", "", "");

			break;
		}
	}

	@ArquillianResource
	public Bundle bundle;

	private static ServiceTracker<AppLicenseVerifier, AppLicenseVerifier>
		_serviceTracker;

	private BundleContext _bundleContext;

}