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

package com.liferay.portal.template.freemarker.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.concurrent.ConcurrentHashSet;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.AggregateClassLoader;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ClassLoaderUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.template.freemarker.configuration.FreeMarkerEngineConfiguration;

import freemarker.core.Environment;
import freemarker.core.TemplateClassResolver;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.utility.Execute;
import freemarker.template.utility.ObjectConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.util.tracker.BundleTracker;
import org.osgi.util.tracker.BundleTrackerCustomizer;

/**
 * @author Raymond Augé
 */
@Component(
	configurationPid = "com.liferay.portal.template.freemarker.configuration.FreeMarkerEngineConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true
)
public class LiferayTemplateClassResolver implements TemplateClassResolver {

	@Override
	public Class<?> resolve(
			String className, Environment environment, Template template)
		throws TemplateException {

		if (className.equals(Execute.class.getName()) ||
			className.equals(ObjectConstructor.class.getName())) {

			throw new TemplateException(
				"Instantiating " + className + " is not allowed in the " +
					"template for security reasons",
				environment);
		}

		String[] restrictedClassNames = GetterUtil.getStringValues(
			_freemarkerEngineConfiguration.restrictedClasses());

		for (String restrictedClassName : restrictedClassNames) {
			if (match(restrictedClassName, className)) {
				throw new TemplateException(
					"Instantiating " + className + " is not allowed in the " +
						"template for security reasons",
					environment);
			}
		}

		boolean allowed = false;

		String[] allowedClasseNames = GetterUtil.getStringValues(
			_freemarkerEngineConfiguration.allowedClasses());

		for (String allowedClassName : allowedClasseNames) {
			if (match(allowedClassName, className)) {
				allowed = true;

				break;
			}
		}

		if (allowed) {
			try {
				ClassLoader[] wwhitelistedClassLoaders =
					_wwhitelistedClassLoaders.toArray(
						new ClassLoader[_wwhitelistedClassLoaders.size()]);

				ClassLoader[] classLoaders = ArrayUtil.append(
					wwhitelistedClassLoaders,
					ClassLoaderUtil.getContextClassLoader());

				ClassLoader wwhitelistedAggregateClassLoader =
					AggregateClassLoader.getAggregateClassLoader(classLoaders);

				return Class.forName(
					className, true, wwhitelistedAggregateClassLoader);
			}
			catch (Exception e) {
				throw new TemplateException(e, environment);
			}
		}

		throw new TemplateException(
			"Instantiating " + className + " is not allowed in the template " +
				"for security reasons",
			environment);
	}

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		_freemarkerEngineConfiguration = ConfigurableUtil.createConfigurable(
			FreeMarkerEngineConfiguration.class, properties);

		_classLoaderBundleTracker = new BundleTracker<>(
			bundleContext, Bundle.ACTIVE,
			new ClassLoaderBundleTrackerCustomizer());

		_classLoaderBundleTracker.open();

		_wwhitelistedClassLoaders.add(
			LiferayTemplateClassResolver.class.getClassLoader());
	}

	@Deactivate
	protected void deactivate() {
		_classLoaderBundleTracker.close();
	}

	protected ClassLoader findClassLoader(
		String clazz, BundleContext bundleContext) {

		Bundle bundle = bundleContext.getBundle();

		BundleWiring bundleWiring = bundle.adapt(BundleWiring.class);

		List<BundleCapability> bundleCapabilities =
			bundleWiring.getCapabilities(BundleRevision.PACKAGE_NAMESPACE);

		for (BundleCapability bundleCapability : bundleCapabilities) {
			Map<String, Object> attributes = bundleCapability.getAttributes();

			String exportPackage = (String)attributes.get(
				BundleRevision.PACKAGE_NAMESPACE);

			if (clazz.equals(StringPool.STAR)) {
				continue;
			}
			else if (clazz.endsWith(StringPool.STAR)) {
				clazz = clazz.substring(0, clazz.length() - 1);

				if (exportPackage.startsWith(clazz)) {
					BundleRevision bundleRevision =
						bundleCapability.getRevision();

					Bundle bundleRevisionBundle = bundleRevision.getBundle();

					BundleWiring bundleRevisionBundleWiring =
						bundleRevisionBundle.adapt(BundleWiring.class);

					return bundleRevisionBundleWiring.getClassLoader();
				}
			}
			else if (clazz.equals(exportPackage)) {
				BundleRevision bundleRevision = bundleCapability.getRevision();

				Bundle bundleRevisionBundle = bundleRevision.getBundle();

				BundleWiring bundleRevisionBundleWiring =
					bundleRevisionBundle.adapt(BundleWiring.class);

				return bundleRevisionBundleWiring.getClassLoader();
			}
			else {
				String allowedClassPackage = clazz.substring(
					0, clazz.lastIndexOf("."));

				if (allowedClassPackage.equals(exportPackage)) {
					BundleRevision bundleRevision =
						bundleCapability.getRevision();

					Bundle bundleRevisionBundle = bundleRevision.getBundle();

					BundleWiring bundleRevisionBundleWiring =
						bundleRevisionBundle.adapt(BundleWiring.class);

					return bundleRevisionBundleWiring.getClassLoader();
				}
			}
		}

		return null;
	}

	protected ClassLoader findClassLoader(
		String[] allowedClassNames, BundleContext bundleContext) {

		if (allowedClassNames == null) {
			allowedClassNames = new String[0];
		}

		for (String allowedClassName : allowedClassNames) {
			if (Validator.isBlank(allowedClassName)) {
				continue;
			}

			ClassLoader classLoader = findClassLoader(
				allowedClassName, bundleContext);

			if (classLoader != null) {
				return classLoader;
			}

			if (_log.isWarnEnabled()) {
				Bundle bundle = bundleContext.getBundle();

				_log.warn(
					"Bundle " + bundle.getSymbolicName() + " does not export " +
						allowedClassName);
			}
		}

		return null;
	}

	protected boolean match(String className, String matchedClassName) {
		if (className.equals(StringPool.STAR)) {
			return true;
		}
		else if (className.endsWith(StringPool.STAR)) {
			className = className.substring(0, className.length() - 1);

			if (matchedClassName.startsWith(className)) {
				return true;
			}
		}
		else if (className.equals(matchedClassName)) {
			return true;
		}
		else {
			String packageName = matchedClassName.substring(
				0, matchedClassName.lastIndexOf("."));

			if (packageName.equals(className)) {
				return true;
			}
		}

		return false;
	}

	@Modified
	protected void modified(
		BundleContext bundleContext, Map<String, Object> properties) {

		_freemarkerEngineConfiguration = ConfigurableUtil.createConfigurable(
			FreeMarkerEngineConfiguration.class, properties);

		for (Bundle bundle : _bundles) {
			ClassLoader classLoader = findClassLoader(
				_freemarkerEngineConfiguration.allowedClasses(),
				bundle.getBundleContext());

			if (classLoader != null) {
				_wwhitelistedClassLoaders.add(classLoader);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LiferayTemplateClassResolver.class);

	private final Set<Bundle> _bundles = new ConcurrentHashSet<>();
	private BundleTracker<ClassLoader> _classLoaderBundleTracker;
	private volatile FreeMarkerEngineConfiguration
		_freemarkerEngineConfiguration;
	private final Set<ClassLoader> _wwhitelistedClassLoaders =
		new ConcurrentHashSet<>();

	private class ClassLoaderBundleTrackerCustomizer
		implements BundleTrackerCustomizer<ClassLoader> {

		@Override
		public ClassLoader addingBundle(
			Bundle bundle, BundleEvent bundleEvent) {

			ClassLoader classLoader = findClassLoader(
				_freemarkerEngineConfiguration.allowedClasses(),
				bundle.getBundleContext());

			if (classLoader != null) {
				_wwhitelistedClassLoaders.add(classLoader);
			}

			_bundles.add(bundle);

			BundleWiring bundleWiring = bundle.adapt(BundleWiring.class);

			return bundleWiring.getClassLoader();
		}

		@Override
		public void modifiedBundle(
			Bundle bundle, BundleEvent bundleEvent, ClassLoader classLoader) {
		}

		@Override
		public void removedBundle(
			Bundle bundle, BundleEvent bundleEvent, ClassLoader classLoader) {

			_wwhitelistedClassLoaders.remove(classLoader);

			_bundles.remove(bundle);
		}

	}

}