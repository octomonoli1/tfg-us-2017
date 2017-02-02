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

package com.liferay.portal.kernel.io.unsync;

import java.io.IOException;
import java.io.Writer;

/**
 * <p>
 * See https://issues.liferay.com/browse/LPS-6648.
 * </p>
 *
 * @author Shuyang Zhou
 */
public class UnsyncBufferedWriter extends Writer {

	public UnsyncBufferedWriter(Writer writer) {
		this(writer, _DEFAULT_BUFFER_SIZE);
	}

	public UnsyncBufferedWriter(Writer writer, int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Size is less than 0");
		}

		this.writer = writer;
		this.size = size;

		buffer = new char[size];
	}

	@Override
	public void close() throws IOException {
		if (writer == null) {
			return;
		}

		if (count > 0) {
			writer.write(buffer, 0, count);

			count = 0;
		}

		writer.flush();
		writer.close();

		writer = null;
		buffer = null;
	}

	@Override
	public void flush() throws IOException {
		if (writer == null) {
			throw new IOException("Writer is null");
		}

		if (count > 0) {
			writer.write(buffer, 0, count);

			count = 0;
		}

		writer.flush();
	}

	public void newLine() throws IOException {
		write(_LINE_SEPARATOR);
	}

	@Override
	public void write(char[] chars, int offset, int length) throws IOException {
		if (writer == null) {
			throw new IOException("Writer is null");
		}

		if (length >= size) {
			if (count > 0) {
				writer.write(buffer, 0, count);

				count = 0;
			}

			writer.write(chars, offset, length);

			return;
		}

		if ((count > 0) && (length > (size - count))) {
			writer.write(buffer, 0, count);

			count = 0;
		}

		System.arraycopy(chars, offset, buffer, count, length);

		count += length;
	}

	@Override
	public void write(int c) throws IOException {
		if (writer == null) {
			throw new IOException("Writer is null");
		}

		if (count >= size) {
			writer.write(buffer);

			count = 0;
		}

		buffer[count++] = (char)c;
	}

	@Override
	public void write(String string, int offset, int length)
		throws IOException {

		if (writer == null) {
			throw new IOException("Writer is null");
		}

		int x = offset;
		int y = offset + length;

		while (x < y) {
			if (count >= size) {
				writer.write(buffer);

				count = 0;
			}

			int leftFreeSpace = size - count;
			int leftDataSize = y - x;

			if (leftFreeSpace > leftDataSize) {
				string.getChars(x, y, buffer, count);

				count += leftDataSize;

				break;
			}
			else {
				int copyEnd = x + leftFreeSpace;

				string.getChars(x, copyEnd, buffer, count);

				count += leftFreeSpace;

				x = copyEnd;
			}
		}
	}

	protected char[] buffer;
	protected int count;
	protected int size;
	protected Writer writer;

	private static final int _DEFAULT_BUFFER_SIZE = 8192;

	private static final String _LINE_SEPARATOR = System.getProperty(
		"line.separator");

}