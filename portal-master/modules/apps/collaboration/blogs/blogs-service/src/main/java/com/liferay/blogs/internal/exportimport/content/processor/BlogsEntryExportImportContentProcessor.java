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

package com.liferay.blogs.internal.exportimport.content.processor;

import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.content.processor.base.BaseTextExportImportContentProcessor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;

/**
 * @author Gergely Mathe
 */
@Component(
	property = {"model.class.name=com.liferay.blogs.kernel.model.BlogsEntry"},
	service = {
		BlogsEntryExportImportContentProcessor.class,
		ExportImportContentProcessor.class
	}
)
public class BlogsEntryExportImportContentProcessor
	extends BaseTextExportImportContentProcessor {

	private static final Log _log = LogFactoryUtil.getLog(
		BlogsEntryExportImportContentProcessor.class);

}