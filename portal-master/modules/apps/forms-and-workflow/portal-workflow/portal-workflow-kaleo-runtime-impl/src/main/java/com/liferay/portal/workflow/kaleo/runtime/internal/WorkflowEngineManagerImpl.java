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

package com.liferay.portal.workflow.kaleo.runtime.internal;

import com.liferay.portal.kernel.workflow.WorkflowEngineManager;

import java.util.Collections;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = {"proxy.bean=false"},
	service = WorkflowEngineManager.class
)
public class WorkflowEngineManagerImpl implements WorkflowEngineManager {

	@Override
	public String getKey() {
		return _KEY;
	}

	@Override
	public String getName() {
		return _NAME;
	}

	@Override
	public Map<String, Object> getOptionalAttributes() {
		return _optionalAttributes;
	}

	@Override
	public String getVersion() {
		return _VERSION;
	}

	@Override
	public boolean isDeployed() {
		return true;
	}

	private static final String _KEY = "liferay";

	private static final String _NAME = "Liferay Kaleo Workflow Engine";

	private static final String _VERSION = "6.0.0";

	private static final Map<String, Object> _optionalAttributes =
		Collections.emptyMap();

}