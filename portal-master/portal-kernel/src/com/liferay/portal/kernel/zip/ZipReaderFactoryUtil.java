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
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Raymond Augé
 */
public class ZipReaderFactoryUtil {

	public static ZipReader getZipReader(File file) {
		return getZipReaderFactory().getZipReader(file);
	}

	public static ZipReader getZipReader(InputStream inputStream)
		throws IOException {

		return getZipReaderFactory().getZipReader(inputStream);
	}

	public static ZipReaderFactory getZipReaderFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			ZipReaderFactoryUtil.class);

		return _zipReaderFactory;
	}

	public void setZipReaderFactory(ZipReaderFactory zipReaderFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_zipReaderFactory = zipReaderFactory;
	}

	private static ZipReaderFactory _zipReaderFactory;

}