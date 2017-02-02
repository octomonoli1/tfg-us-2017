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

package com.liferay.portal.monitoring.internal.statistics.service;

import com.liferay.portal.kernel.monitoring.MethodSignature;
import com.liferay.portal.monitoring.MonitorNames;
import com.liferay.portal.monitoring.internal.BaseDataSample;

/**
 * @author Michael C. Han
 */
public class ServiceRequestDataSample extends BaseDataSample {

	public ServiceRequestDataSample(MethodSignature methodSignature) {
		_methodSignature = methodSignature;

		setDescription(_methodSignature.toString());
		setNamespace(MonitorNames.SERVICE);
	}

	public MethodSignature getMethodSignature() {
		return _methodSignature;
	}

	private final MethodSignature _methodSignature;

}