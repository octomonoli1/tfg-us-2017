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

package com.liferay.portal.kernel.zip;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.File;

/**
 * @author Raymond Augé
 */
public class ZipWriterFactoryUtil {

	public static ZipWriter getZipWriter() {
		return getZipWriterFactory().getZipWriter();
	}

	public static ZipWriter getZipWriter(File file) {
		return getZipWriterFactory().getZipWriter(file);
	}

	public static ZipWriterFactory getZipWriterFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			ZipWriterFactoryUtil.class);

		return _zipWriterFactory;
	}

	public void setZipWriterFactory(ZipWriterFactory zipWriterFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_zipWriterFactory = zipWriterFactory;
	}

	private static ZipWriterFactory _zipWriterFactory;

}