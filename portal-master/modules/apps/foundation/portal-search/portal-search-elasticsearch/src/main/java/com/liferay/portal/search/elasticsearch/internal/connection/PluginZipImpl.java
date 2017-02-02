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

package com.liferay.portal.search.elasticsearch.internal.connection;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * @author André de Oliveira
 */
public class PluginZipImpl implements PluginZip {

	public PluginZipImpl(File file) {
		_file = file;
	}

	@Override
	public void delete() {
		_file.delete();
	}

	@Override
	public URL getURL() {
		URI uri = _file.toURI();

		try {
			return uri.toURL();
		}
		catch (MalformedURLException murle) {
			throw new RuntimeException("Invalid file " + _file, murle);
		}
	}

	private final File _file;

}