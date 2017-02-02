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

package com.liferay.knowledge.base.internal.importer.util;

import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.knowledge.base.configuration.KBGroupServiceConfiguration;
import com.liferay.knowledge.base.constants.KBPortletKeys;
import com.liferay.knowledge.base.exception.KBArticleImportException;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipReader;

import java.io.InputStream;

import java.util.Map;

/**
 * @author James Hinkey
 * @author Sergio González
 */
public class KBArticleImporterUtil {

	public static FileEntry addImageFileEntry(
			String imageFileName, long userId, KBArticle kbArticle,
			ZipReader zipReader, Map<String, FileEntry> fileEntriesMap)
		throws PortalException {

		KBGroupServiceConfiguration kbGroupServiceConfiguration =
			ConfigurationProviderUtil.getGroupConfiguration(
				KBGroupServiceConfiguration.class, kbArticle.getGroupId());

		try {
			validateImageFileExtension(
				imageFileName,
				kbGroupServiceConfiguration.
					markdownImporterImageFileExtensions());
		}
		catch (KBArticleImportException kbaie) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unsupported image file suffix used in ZIP file " +
						imageFileName);
			}
		}

		try {
			String zipReaderFileName = getZipReaderFileName(
				kbGroupServiceConfiguration.markdownImporterImageFolder(),
				imageFileName);

			return addImageFileEntry(
				userId, kbArticle, imageFileName,
				zipReader.getEntryAsInputStream(zipReaderFileName),
				fileEntriesMap);
		}
		catch (Exception e) {
			StringBuilder sb = new StringBuilder(4);

			sb.append("Unable to import image file ");
			sb.append(imageFileName);
			sb.append(": ");
			sb.append(e.getLocalizedMessage());

			throw new KBArticleImportException(sb.toString());
		}
	}

	public static String extractImageFileName(String html) {
		String imageSrc = null;

		String[] lines = StringUtil.split(html, StringPool.QUOTE);

		for (int i = 0; i < lines.length; i++) {
			if (lines[i].endsWith("src=")) {
				if ((i + 1) < lines.length) {
					imageSrc = lines[i + 1];
				}

				break;
			}
		}

		if (Validator.isNull(imageSrc)) {
			if (_log.isWarnEnabled()) {
				_log.warn("Missing src attribute for image " + html);
			}

			return null;
		}

		String[] paths = StringUtil.split(imageSrc, StringPool.SLASH);

		if (paths.length < 1) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Expected image file path to contain a slash " + html);
			}

			return null;
		}

		return paths[paths.length - 1];
	}

	public static void validateImageFileExtension(
			String imageFileName, String[] fileExtensions)
		throws KBArticleImportException {

		boolean validImageFileExtension = false;

		for (String fileExtension : fileExtensions) {
			if (StringPool.STAR.equals(fileExtension) ||
				StringUtil.endsWith(imageFileName, fileExtension)) {

				validImageFileExtension = true;

				break;
			}
		}

		if (!validImageFileExtension) {
			throw new KBArticleImportException(imageFileName);
		}
	}

	protected static FileEntry addImageFileEntry(
			long userId, KBArticle kbArticle, String imageFileName,
			InputStream inputStream, Map<String, FileEntry> fileEntriesMap)
		throws PortalException {

		FileEntry fileEntry = fileEntriesMap.get(imageFileName);

		if (fileEntry != null) {
			return fileEntry;
		}

		String mimeType = MimeTypesUtil.getContentType(imageFileName);

		try {
			PortletFileRepositoryUtil.getPortletFileEntry(
				kbArticle.getGroupId(), kbArticle.getAttachmentsFolderId(),
				imageFileName);

			PortletFileRepositoryUtil.deletePortletFileEntry(
				kbArticle.getGroupId(), kbArticle.getAttachmentsFolderId(),
				imageFileName);
		}
		catch (NoSuchFileEntryException nsfee) {
		}

		fileEntry = PortletFileRepositoryUtil.addPortletFileEntry(
			kbArticle.getGroupId(), userId, KBArticle.class.getName(),
			kbArticle.getClassPK(), KBPortletKeys.KNOWLEDGE_BASE_ARTICLE,
			kbArticle.getAttachmentsFolderId(), inputStream, imageFileName,
			mimeType, false);

		fileEntriesMap.put(imageFileName, fileEntry);

		return fileEntry;
	}

	protected static String getZipReaderFileName(
		String dirName, String fileName) {

		if (dirName.endsWith(StringPool.SLASH)) {
			return dirName + fileName;
		}
		else {
			return dirName + StringPool.SLASH + fileName;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KBArticleImporterUtil.class);

}