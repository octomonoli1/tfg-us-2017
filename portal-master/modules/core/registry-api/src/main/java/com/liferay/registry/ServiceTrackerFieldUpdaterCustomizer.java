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

package com.liferay.registry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shuyang Zhou
 */
public class ServiceTrackerFieldUpdaterCustomizer<S, T>
	implements ServiceTrackerCustomizer<S, T> {

	public ServiceTrackerFieldUpdaterCustomizer(
		Field serviceField, Object serviceHolder, T dummyTrackedService) {

		if (!Modifier.isVolatile(serviceField.getModifiers())) {
			throw new IllegalArgumentException(
				serviceField + " is not volatile");
		}

		_serviceField = serviceField;
		_serviceHolder = serviceHolder;
		_dummyTrackedService = dummyTrackedService;
	}

	@Override
	public final T addingService(ServiceReference<S> serviceReference) {
		T trackedService = doAddingService(serviceReference);

		if (trackedService != null) {
			_trackedServices.put(serviceReference, trackedService);

			_updateService();
		}

		return trackedService;
	}

	@Override
	public final void modifiedService(
		ServiceReference<S> serviceReference, T service) {

		doModifiedService(serviceReference, service);

		_updateService();
	}

	@Override
	public final void removedService(
		ServiceReference<S> serviceReference, T service) {

		if (_trackedServices.remove(serviceReference, service)) {
			_updateService();
		}

		doRemovedService(serviceReference, service);
	}

	protected void afterServiceUpdate(T oldService, T newService) {
	}

	protected void beforeServiceUpdate(T oldService, T newService) {
	}

	protected T doAddingService(ServiceReference<S> serviceReference) {
		Registry registry = RegistryUtil.getRegistry();

		return (T)registry.getService(serviceReference);
	}

	protected void doModifiedService(
		ServiceReference<S> serviceReference, T service) {
	}

	protected void doRemovedService(
		ServiceReference<S> serviceReference, T service) {

		Registry registry = RegistryUtil.getRegistry();

		registry.ungetService(serviceReference);
	}

	private void _updateService() {
		Optional<Entry<ServiceReference<S>, T>> optionalEntry =
			ServiceRankingUtil.getHighestRankingEntry(_trackedServices);

		Optional<T> optionalService = optionalEntry.map(Entry::getValue);

		T newService = optionalService.orElse(_dummyTrackedService);

		try {
			T oldService = (T)_serviceField.get(_serviceHolder);

			if (newService != oldService) {
				beforeServiceUpdate(oldService, newService);

				_serviceField.set(_serviceHolder, newService);

				afterServiceUpdate(oldService, newService);
			}
		}
		catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		}
	}

	private final T _dummyTrackedService;
	private final Field _serviceField;
	private final Object _serviceHolder;
	private final Map<ServiceReference<S>, T> _trackedServices =
		new ConcurrentHashMap<>();

}