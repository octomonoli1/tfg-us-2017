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

package com.liferay.mail.reader.attachment;

import java.io.InputStream;

import javax.mail.Folder;

/**
 * @author Scott Lee
 */
public class DefaultAttachmentHandler implements AttachmentHandler {

	public DefaultAttachmentHandler(InputStream inputStream, Folder folder) {
		_inputStream = inputStream;
		_folder = folder;
	}

	public void cleanUp() {
	}

	public Folder getFolder() {
		return _folder;
	}

	public InputStream getInputStream() {
		return _inputStream;
	}

	private final Folder _folder;
	private final InputStream _inputStream;

}