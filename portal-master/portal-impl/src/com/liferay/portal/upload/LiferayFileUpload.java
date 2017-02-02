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

package com.liferay.portal.upload;

import com.liferay.portal.kernel.util.ProgressTracker;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author     Brian Myunghun Kim
 * @author     Brian Wing Shun Chan
 * @deprecated As of 7.0.0, with no direct replacement
 */
@Deprecated
public class LiferayFileUpload extends ServletFileUpload {

	public static final String FILE_NAME =
		LiferayFileUpload.class.getName() + "_FILE_NAME";

	public static final String PERCENT = ProgressTracker.PERCENT;

	public LiferayFileUpload(
		FileItemFactory fileItemFactory, HttpServletRequest request) {

		super(fileItemFactory);

		_session = request.getSession();
	}

	@Override
	public List<FileItem> parseRequest(HttpServletRequest request)
		throws FileUploadException {

		_session.removeAttribute(LiferayFileUpload.PERCENT);

		return super.parseRequest(request);
	}

	private final HttpSession _session;

}