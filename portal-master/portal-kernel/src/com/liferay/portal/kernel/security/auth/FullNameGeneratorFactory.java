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

package com.liferay.portal.kernel.security.auth;

import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

/**
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public class FullNameGeneratorFactory {

	public static FullNameGenerator getInstance() {
		FullNameGenerator fullNameGenerator =
			_instance._serviceTracker.getService();

		if (fullNameGenerator != null) {
			return fullNameGenerator;
		}

		return _fullNameGenerator;
	}

	public void setFullNameGenerator(FullNameGenerator fullNameGenerator) {
		_fullNameGenerator = fullNameGenerator;
	}

	private FullNameGeneratorFactory() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(FullNameGenerator.class);

		_serviceTracker.open();
	}

	private static final FullNameGeneratorFactory _instance =
		new FullNameGeneratorFactory();

	private static FullNameGenerator _fullNameGenerator;

	private final ServiceTracker<FullNameGenerator, FullNameGenerator>
		_serviceTracker;

}