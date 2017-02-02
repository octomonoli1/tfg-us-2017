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

import com.liferay.portal.kernel.io.unsync.UnsyncFilterInputStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.zip.ZipFile;

/**
 * @author Shuyang Zhou
 */
public class ZipFileUtil {

	public static InputStream openInputStream(File file, String entryName)
		throws IOException {

		ZipFile zipFile = new ZipFile(file);

		return new ZipFileInputStream(
			zipFile.getInputStream(zipFile.getEntry(entryName)), zipFile);
	}

	private static class ZipFileInputStream extends UnsyncFilterInputStream {

		@Override
		public void close() throws IOException {
			try {
				inputStream.close();
			}
			finally {
				_zipFile.close();
			}
		}

		private ZipFileInputStream(InputStream inputStream, ZipFile zipFile) {
			super(inputStream);

			_zipFile = zipFile;
		}

		private final ZipFile _zipFile;

	}

}