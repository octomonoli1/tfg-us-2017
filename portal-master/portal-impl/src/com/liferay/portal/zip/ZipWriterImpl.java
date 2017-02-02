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

package com.liferay.portal.zip;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.memory.DeleteFileFinalizeAction;
import com.liferay.portal.kernel.memory.FinalizeManager;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.zip.ZipWriter;

import de.schlichtherle.io.ArchiveDetector;
import de.schlichtherle.io.ArchiveException;
import de.schlichtherle.io.DefaultArchiveDetector;
import de.schlichtherle.io.File;
import de.schlichtherle.io.FileOutputStream;
import de.schlichtherle.io.archive.zip.ZipDriver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Raymond Augé
 */
public class ZipWriterImpl implements ZipWriter {

	public ZipWriterImpl() {
		_file = new File(
			SystemProperties.get(SystemProperties.TMP_DIR) + StringPool.SLASH +
				PortalUUIDUtil.generate() + ".zip");

		_file.mkdir();

		FinalizeManager.register(
			_file.getDelegate(),
			new DeleteFileFinalizeAction(_file.getAbsolutePath()),
			FinalizeManager.PHANTOM_REFERENCE_FACTORY);
	}

	public ZipWriterImpl(java.io.File file) {
		_file = new File(file.getAbsolutePath());

		_file.mkdir();
	}

	@Override
	public void addEntry(String name, byte[] bytes) throws IOException {
		try (UnsyncByteArrayInputStream unsyncByteArrayInputStream =
				new UnsyncByteArrayInputStream(bytes)) {

			addEntry(name, unsyncByteArrayInputStream);
		}
	}

	@Override
	public void addEntry(String name, InputStream inputStream)
		throws IOException {

		if (name.startsWith(StringPool.SLASH)) {
			name = name.substring(1);
		}

		if (inputStream == null) {
			return;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Adding " + name);
		}

		try (OutputStream outputStream = new FileOutputStream(
				new File(getPath() + StringPool.SLASH + name))) {

			File.cat(inputStream, outputStream);
		}
	}

	@Override
	public void addEntry(String name, String s) throws IOException {
		if (s == null) {
			return;
		}

		addEntry(name, s.getBytes(StringPool.UTF8));
	}

	@Override
	public void addEntry(String name, StringBuilder sb) throws IOException {
		if (sb == null) {
			return;
		}

		addEntry(name, sb.toString());
	}

	@Override
	public byte[] finish() throws IOException {
		java.io.File file = getFile();

		return FileUtil.getBytes(file);
	}

	@Override
	public java.io.File getFile() {
		try {
			File.umount(_file);
		}
		catch (ArchiveException ae) {
			_log.error(ae, ae);
		}

		return _file.getDelegate();
	}

	@Override
	public String getPath() {
		return _file.getPath();
	}

	private static final Log _log = LogFactoryUtil.getLog(ZipWriterImpl.class);

	static {
		File.setDefaultArchiveDetector(
			new DefaultArchiveDetector(
				ArchiveDetector.ALL, "lar|" + ArchiveDetector.ALL.getSuffixes(),
				new ZipDriver()));

		TrueZIPHelperUtil.initialize();
	}

	private final File _file;

}