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

package com.liferay.gradle.plugins.wsdl.builder;

import com.liferay.gradle.util.GradleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.gradle.api.tasks.Input;

/**
 * @author Andrea Di Giorgi
 */
public class GenerateOptions {

	@Input
	public Map<?, ?> getMappings() {
		return _mappings;
	}

	@Input
	public boolean isNoWrapped() {
		return _noWrapped;
	}

	@Input
	public boolean isServerSide() {
		return _serverSide;
	}

	public boolean isVerbose() {
		return _verbose;
	}

	public GenerateOptions mapping(Object namespace, Object packageName) {
		_mappings.put(namespace, packageName);

		return this;
	}

	public GenerateOptions mappings(Map<?, ?> mappings) {
		_mappings.putAll(mappings);

		return this;
	}

	public void setMappings(Map<?, ?> mappings) {
		_mappings.clear();

		mappings(mappings);
	}

	public void setNoWrapped(boolean noWrapped) {
		_noWrapped = noWrapped;
	}

	public void setServerSide(boolean serverSide) {
		_serverSide = serverSide;
	}

	public void setVerbose(boolean verbose) {
		_verbose = verbose;
	}

	protected List<String> getArgs() {
		List<String> args = new ArrayList<>();

		if (isNoWrapped()) {
			args.add("--noWrapped");
		}

		Map<?, ?> mappings = getMappings();

		if (!mappings.isEmpty()) {
			for (Map.Entry<?, ?> entry : mappings.entrySet()) {
				args.add("--NStoPkg");

				String namespace = GradleUtil.toString(entry.getKey());
				String packageName = GradleUtil.toString(entry.getValue());

				args.add(namespace + "=" + packageName);
			}
		}

		if (isServerSide()) {
			args.add("--server-side");
		}

		if (isVerbose()) {
			args.add("--verbose");
		}

		return args;
	}

	private final Map<Object, Object> _mappings = new TreeMap<>();
	private boolean _noWrapped;
	private boolean _serverSide;
	private boolean _verbose;

}