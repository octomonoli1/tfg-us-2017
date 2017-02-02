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

package com.liferay.exportimport.kernel.lifecycle;

import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceRegistration;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;
import com.liferay.registry.collections.ServiceRegistrationMap;
import com.liferay.registry.collections.ServiceRegistrationMapImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Daniel Kocsis
 */
public class ExportImportLifecycleEventListenerRegistryUtil {

	public static Set<ExportImportLifecycleListener>
		getAsyncExportImportLifecycleListeners() {

		return _instance._getAsyncExportImportLifecycleListeners();
	}

	public static Set<ExportImportLifecycleListener>
		getSyncExportImportLifecycleListeners() {

		return _instance._getSyncExportImportLifecycleListeners();
	}

	public static void register(
		ExportImportLifecycleListener exportImportLifecycleListener) {

		_instance._register(exportImportLifecycleListener);
	}

	public static void unregister(
		ExportImportLifecycleListener exportImportLifecycleListener) {

		_instance._unregister(exportImportLifecycleListener);
	}

	public static void unregister(
		List<ExportImportLifecycleListener> exportImportLifecycleListeners) {

		for (ExportImportLifecycleListener exportImportLifecycleListener :
				exportImportLifecycleListeners) {

			unregister(exportImportLifecycleListener);
		}
	}

	private ExportImportLifecycleEventListenerRegistryUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			ExportImportLifecycleListener.class,
			new ExportImportLifecycleListenerServiceTrackerCustomizer());

		_serviceTracker.open();
	}

	private Set<ExportImportLifecycleListener>
		_getAsyncExportImportLifecycleListeners() {

		return _asyncExportImportLifecycleListeners;
	}

	private Set<ExportImportLifecycleListener>
		_getSyncExportImportLifecycleListeners() {

		return _syncExportImportLifecycleListeners;
	}

	private void _register(
		ExportImportLifecycleListener exportImportLifecycleListener) {

		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<ExportImportLifecycleListener> serviceRegistration =
			registry.registerService(
				ExportImportLifecycleListener.class,
				exportImportLifecycleListener);

		_serviceRegistrations.put(
			exportImportLifecycleListener, serviceRegistration);
	}

	private void _unregister(
		ExportImportLifecycleListener exportImportLifecycleListener) {

		ServiceRegistration<ExportImportLifecycleListener> serviceRegistration =
			_serviceRegistrations.remove(exportImportLifecycleListener);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	private static final ExportImportLifecycleEventListenerRegistryUtil
		_instance = new ExportImportLifecycleEventListenerRegistryUtil();

	private final Set<ExportImportLifecycleListener>
		_asyncExportImportLifecycleListeners = new HashSet<>();
	private final ServiceRegistrationMap<ExportImportLifecycleListener>
		_serviceRegistrations = new ServiceRegistrationMapImpl<>();
	private final ServiceTracker
		<ExportImportLifecycleListener, ExportImportLifecycleListener>
			_serviceTracker;
	private final Set<ExportImportLifecycleListener>
		_syncExportImportLifecycleListeners = new HashSet<>();

	private class ExportImportLifecycleListenerServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<ExportImportLifecycleListener, ExportImportLifecycleListener> {

		@Override
		public ExportImportLifecycleListener addingService(
			ServiceReference<ExportImportLifecycleListener> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			ExportImportLifecycleListener exportImportLifecycleListener =
				registry.getService(serviceReference);

			if (exportImportLifecycleListener.isParallel()) {
				_asyncExportImportLifecycleListeners.add(
					exportImportLifecycleListener);
			}
			else {
				_syncExportImportLifecycleListeners.add(
					exportImportLifecycleListener);
			}

			return exportImportLifecycleListener;
		}

		@Override
		public void modifiedService(
			ServiceReference<ExportImportLifecycleListener> serviceReference,
			ExportImportLifecycleListener exportImportLifecycleListener) {
		}

		@Override
		public void removedService(
			ServiceReference<ExportImportLifecycleListener> serviceReference,
			ExportImportLifecycleListener exportImportLifecycleListener) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			if (exportImportLifecycleListener.isParallel()) {
				_asyncExportImportLifecycleListeners.remove(
					exportImportLifecycleListener);
			}
			else {
				_syncExportImportLifecycleListeners.remove(
					exportImportLifecycleListener);
			}
		}

	}

}