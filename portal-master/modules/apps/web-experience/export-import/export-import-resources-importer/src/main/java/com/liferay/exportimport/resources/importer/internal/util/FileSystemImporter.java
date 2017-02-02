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

package com.liferay.exportimport.resources.importer.internal.util;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.mapping.io.DDMFormJSONDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormXSDDeserializer;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.dynamic.data.mapping.util.DDMXML;
import com.liferay.exportimport.resources.importer.portlet.preferences.PortletPreferencesTranslator;
import com.liferay.journal.configuration.JournalServiceConfigurationValues;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalFolderLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.LayoutTypePortletConstants;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactory;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.RepositoryLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.ThemeLocalService;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.MimeTypes;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReader;
import com.liferay.portal.search.index.IndexStatusManager;
import com.liferay.portlet.display.template.PortletDisplayTemplate;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletPreferences;

/**
 * @author Ryan Park
 * @author Raymond Augé
 */
public class FileSystemImporter extends BaseImporter {

	public FileSystemImporter(
		AssetTagLocalService assetTagLocalService,
		DDMFormJSONDeserializer ddmFormJSONDeserializer,
		DDMFormXSDDeserializer ddmFormXSDDeserializer,
		DDMStructureLocalService ddmStructureLocalService,
		DDMTemplateLocalService ddmTemplateLocalService, DDMXML ddmxml,
		DLAppLocalService dlAppLocalService,
		DLFileEntryLocalService dlFileEntryLocalService,
		DLFolderLocalService dlFolderLocalService,
		IndexStatusManager indexStatusManager, IndexerRegistry indexerRegistry,
		JournalArticleLocalService journalArticleLocalService,
		JournalFolderLocalService journalFolderLocalService,
		LayoutLocalService layoutLocalService,
		LayoutPrototypeLocalService layoutPrototypeLocalService,
		LayoutSetLocalService layoutSetLocalService,
		LayoutSetPrototypeLocalService layoutSetPrototypeLocalService,
		MimeTypes mimeTypes, Portal portal,
		PortletPreferencesFactory portletPreferencesFactory,
		PortletPreferencesLocalService portletPreferencesLocalService,
		PortletPreferencesTranslator portletPreferencesTranslator,
		Map<String, PortletPreferencesTranslator> portletPreferencesTranslators,
		RepositoryLocalService repositoryLocalService, SAXReader saxReader,
		ThemeLocalService themeLocalService) {

		this.assetTagLocalService = assetTagLocalService;
		this.ddmFormJSONDeserializer = ddmFormJSONDeserializer;
		this.ddmFormXSDDeserializer = ddmFormXSDDeserializer;
		this.ddmStructureLocalService = ddmStructureLocalService;
		this.ddmTemplateLocalService = ddmTemplateLocalService;
		this.ddmxml = ddmxml;
		this.dlAppLocalService = dlAppLocalService;
		this.dlFileEntryLocalService = dlFileEntryLocalService;
		this.dlFolderLocalService = dlFolderLocalService;
		this.indexStatusManager = indexStatusManager;
		this.indexerRegistry = indexerRegistry;
		this.journalArticleLocalService = journalArticleLocalService;
		this.journalFolderLocalService = journalFolderLocalService;
		this.layoutLocalService = layoutLocalService;
		this.layoutPrototypeLocalService = layoutPrototypeLocalService;
		this.layoutSetLocalService = layoutSetLocalService;
		this.layoutSetPrototypeLocalService = layoutSetPrototypeLocalService;
		this.mimeTypes = mimeTypes;
		this.portal = portal;
		this.portletPreferencesFactory = portletPreferencesFactory;
		this.portletPreferencesLocalService = portletPreferencesLocalService;
		this.portletPreferencesTranslators =
			new DefaultedPortletPreferencesTranslatorMap(
				portletPreferencesTranslators, portletPreferencesTranslator);
		this.repositoryLocalService = repositoryLocalService;
		this.saxReader = saxReader;
		this.themeLocalService = themeLocalService;
	}

	@Override
	public void importResources() throws Exception {
		_resourcesDir = new File(resourcesDir);

		if (!_resourcesDir.isDirectory() || !_resourcesDir.canRead()) {
			throw new IllegalArgumentException(
				"Unaccessible resource directory " + resourcesDir);
		}

		doImportResources();
	}

	protected void addApplicationDisplayTemplate(
			String script, File file, long classNameId)
		throws PortalException {

		String fileName = FileUtil.stripExtension(file.getName());

		String name = getName(fileName);

		DDMTemplate ddmTemplate = ddmTemplateLocalService.fetchTemplate(
			groupId, classNameId, getKey(fileName));

		if (ddmTemplate != null) {
			if (!developerModeEnabled) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"DDM template with name " + name + " and version " +
							version + " already exists");
				}

				return;
			}

			if (!updateModeEnabled) {
				ddmTemplateLocalService.deleteTemplate(ddmTemplate);
			}
		}

		try {
			if (!updateModeEnabled || (ddmTemplate == null)) {
				ddmTemplateLocalService.addTemplate(
					userId, groupId, classNameId, 0,
					portal.getClassNameId(PortletDisplayTemplate.class),
					getKey(fileName), getMap(name), null,
					DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
					StringPool.BLANK, getDDMTemplateLanguage(file.getName()),
					script, true, false, StringPool.BLANK, null,
					serviceContext);
			}
			else {
				ddmTemplateLocalService.updateTemplate(
					userId, ddmTemplate.getTemplateId(),
					ddmTemplate.getClassPK(), getMap(name), null,
					DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
					StringPool.BLANK, getDDMTemplateLanguage(file.getName()),
					script, ddmTemplate.getCacheable(), serviceContext);
			}
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to import application display template " +
						file.getName(),
					pe);
			}

			throw pe;
		}
	}

	protected void addApplicationDisplayTemplate(
			String parentDirName, String dirName, long classNameId)
		throws Exception {

		File dir = new File(
			_resourcesDir, parentDirName + StringPool.SLASH + dirName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			String script = StringUtil.read(getInputStream(file));

			if (Validator.isNull(script)) {
				continue;
			}

			addApplicationDisplayTemplate(script, file, classNameId);
		}
	}

	protected void addApplicationDisplayTemplates(String dirName)
		throws Exception {

		for (Object[] applicationDisplayTemplateType :
				_APPLICATION_DISPLAY_TEMPLATE_TYPES) {

			String className = (String)applicationDisplayTemplateType[1];

			addApplicationDisplayTemplate(
				dirName, (String)applicationDisplayTemplateType[0],
				portal.getClassNameId(className));
		}
	}

	protected void addDDLDisplayTemplates(
			String ddmStructureKey, String dirName, String fileName)
		throws Exception {

		DDMStructure ddmStructure = ddmStructureLocalService.getStructure(
			groupId, portal.getClassNameId(DDLRecordSet.class),
			ddmStructureKey);

		File dir = new File(
			_resourcesDir, dirName + StringPool.SLASH + fileName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			String script = StringUtil.read(getInputStream(file));

			if (Validator.isNull(script)) {
				return;
			}

			addDDMTemplate(
				groupId, ddmStructure.getStructureId(), file.getName(),
				getDDMTemplateLanguage(file.getName()), script,
				DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null);
		}
	}

	protected void addDDLFormTemplates(
			String ddmStructureKey, String dirName, String fileName)
		throws Exception {

		DDMStructure ddmStructure = ddmStructureLocalService.getStructure(
			groupId, portal.getClassNameId(DDLRecordSet.class),
			ddmStructureKey);

		File dir = new File(
			_resourcesDir, dirName + StringPool.SLASH + fileName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			String script = StringUtil.read(getInputStream(file));

			if (Validator.isNull(script)) {
				return;
			}

			addDDMTemplate(
				groupId, ddmStructure.getStructureId(), file.getName(), "xsd",
				script, DDMTemplateConstants.TEMPLATE_TYPE_FORM,
				DDMTemplateConstants.TEMPLATE_MODE_CREATE);
		}
	}

	protected void addDDLStructures(String dirName) throws Exception {
		File dir = new File(_resourcesDir, dirName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			String fileName = FileUtil.stripExtension(file.getName());

			addDDMStructures(fileName, getInputStream(file));
		}
	}

	protected void addDDMStructures(String fileName, InputStream inputStream)
		throws Exception {

		fileName = FileUtil.stripExtension(fileName);

		String name = getName(fileName);

		DDMStructure ddmStructure = ddmStructureLocalService.fetchStructure(
			groupId, portal.getClassNameId(DDLRecordSet.class),
			getKey(fileName));

		if (ddmStructure != null) {
			if (!developerModeEnabled) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"DDM structure with name " + name + " and version " +
							version + " already exists");
				}

				return;
			}

			if (!updateModeEnabled) {
				ddmStructureLocalService.deleteDDMStructure(ddmStructure);
			}
		}

		try {
			String definition = StringUtil.read(inputStream);

			ddmxml.validateXML(definition);

			DDMForm ddmForm = ddmFormXSDDeserializer.deserialize(definition);

			DDMFormLayout ddmFormLayout = DDMUtil.getDefaultDDMFormLayout(
				ddmForm);

			if (!updateModeEnabled || (ddmStructure == null)) {
				ddmStructure = ddmStructureLocalService.addStructure(
					userId, groupId,
					DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID,
					portal.getClassNameId(DDLRecordSet.class), getKey(fileName),
					getMap(name), null, ddmForm, ddmFormLayout,
					StorageType.JSON.toString(),
					DDMStructureConstants.TYPE_DEFAULT, serviceContext);
			}
			else {
				ddmStructure = ddmStructureLocalService.updateStructure(
					userId, ddmStructure.getStructureId(),
					DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID,
					getMap(name), null, ddmForm, ddmFormLayout, serviceContext);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import DDM structure " + fileName, e);
			}

			throw e;
		}

		addDDLDisplayTemplates(
			ddmStructure.getStructureKey(),
			_DDL_STRUCTURE_DISPLAY_TEMPLATE_DIR_NAME, fileName);

		addDDLFormTemplates(
			ddmStructure.getStructureKey(),
			_DDL_STRUCTURE_FORM_TEMPLATE_DIR_NAME, fileName);
	}

	protected void addDDMStructures(
			String parentDDMStructureKey, String dirName)
		throws Exception {

		File dir = new File(_resourcesDir, dirName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			InputStream inputStream = null;

			try {
				inputStream = new BufferedInputStream(
					new FileInputStream(file));

				addDDMStructures(
					parentDDMStructureKey, file.getName(), inputStream);
			}
			finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
	}

	protected void addDDMStructures(
			String parentDDMStructureKey, String fileName,
			InputStream inputStream)
		throws Exception {

		String language = getDDMStructureLanguage(fileName);

		fileName = FileUtil.stripExtension(fileName);

		String name = getName(fileName);

		DDMStructure ddmStructure = ddmStructureLocalService.fetchStructure(
			groupId, portal.getClassNameId(JournalArticle.class),
			getKey(fileName));

		if (ddmStructure != null) {
			if (!developerModeEnabled) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"DDM structure with name " + name + " and version " +
							version + " already exists");
				}

				return;
			}

			if (!updateModeEnabled) {
				ddmStructureLocalService.deleteDDMStructure(ddmStructure);
			}
		}

		String content = StringUtil.read(inputStream);

		DDMForm ddmForm = null;

		if (language.equals(TemplateConstants.LANG_TYPE_XML)) {
			if (isJournalStructureXSD(content)) {
				content = journalConverter.getDDMXSD(content);
			}

			ddmxml.validateXML(content);

			ddmForm = ddmFormXSDDeserializer.deserialize(content);
		}
		else {
			ddmForm = ddmFormJSONDeserializer.deserialize(content);
		}

		DDMFormLayout ddmFormLayout = DDMUtil.getDefaultDDMFormLayout(ddmForm);

		setServiceContext(fileName);

		try {
			if (!updateModeEnabled || (ddmStructure == null)) {
				ddmStructure = ddmStructureLocalService.addStructure(
					userId, groupId, parentDDMStructureKey,
					portal.getClassNameId(JournalArticle.class),
					getKey(fileName), getMap(name), null, ddmForm,
					ddmFormLayout,
					JournalServiceConfigurationValues.
						JOURNAL_ARTICLE_STORAGE_TYPE,
					DDMStructureConstants.TYPE_DEFAULT, serviceContext);
			}
			else {
				DDMStructure parentStructure =
					ddmStructureLocalService.fetchStructure(
						groupId, portal.getClassNameId(JournalArticle.class),
						parentDDMStructureKey);

				long parentDDMStructureId =
					DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID;

				if (parentStructure != null) {
					parentDDMStructureId = parentStructure.getStructureId();
				}

				ddmStructure = ddmStructureLocalService.updateStructure(
					userId, ddmStructure.getStructureId(), parentDDMStructureId,
					getMap(name), null, ddmForm, ddmFormLayout, serviceContext);
			}
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import DDM structure " + fileName, pe);
			}

			throw pe;
		}

		_ddmStructureKeys.add(ddmStructure.getStructureKey());

		addDDMTemplates(
			ddmStructure.getStructureKey(),
			_JOURNAL_DDM_TEMPLATES_DIR_NAME + fileName);

		if (Validator.isNull(parentDDMStructureKey)) {
			addDDMStructures(
				ddmStructure.getStructureKey(),
				_JOURNAL_DDM_STRUCTURES_DIR_NAME + fileName);
		}
	}

	protected void addDDMTemplate(
			long templateGroupId, long ddmStructureId, String fileName,
			String language, String script, String type, String mode)
		throws Exception {

		fileName = FileUtil.getShortFileName(fileName);

		fileName = FileUtil.stripExtension(fileName);

		String name = getName(fileName);

		DDMTemplate ddmTemplate = ddmTemplateLocalService.fetchTemplate(
			groupId, portal.getClassNameId(DDMStructure.class),
			getKey(fileName));

		if (ddmTemplate != null) {
			if (!developerModeEnabled) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"DDM template with name " + name + " and version " +
							version + " already exists");
				}

				return;
			}

			if (!updateModeEnabled) {
				ddmTemplateLocalService.deleteTemplate(ddmTemplate);
			}
		}

		try {
			if (!updateModeEnabled || (ddmTemplate == null)) {
				ddmTemplateLocalService.addTemplate(
					userId, templateGroupId,
					portal.getClassNameId(DDMStructure.class), ddmStructureId,
					portal.getClassNameId(JournalArticle.class),
					getKey(fileName), getMap(name), null, type, mode, language,
					script, true, false, StringPool.BLANK, null,
					serviceContext);
			}
			else {
				ddmTemplateLocalService.updateTemplate(
					userId, ddmTemplate.getTemplateId(),
					portal.getClassNameId(DDMStructure.class), getMap(name),
					null, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null,
					language, script, ddmTemplate.getCacheable(),
					serviceContext);
			}
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import DDM template " + fileName, pe);
			}

			throw pe;
		}
	}

	protected void addDDMTemplates(String ddmStructureKey, String dirName)
		throws Exception {

		File dir = new File(_resourcesDir, dirName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			InputStream inputStream = null;

			try {
				inputStream = new BufferedInputStream(
					new FileInputStream(file));

				addDDMTemplates(ddmStructureKey, file.getName(), inputStream);
			}
			finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
	}

	protected void addDDMTemplates(
			String ddmStructureKey, String fileName, InputStream inputStream)
		throws Exception {

		String language = getDDMTemplateLanguage(fileName);

		fileName = FileUtil.stripExtension(fileName);

		String name = getName(fileName);

		String script = StringUtil.read(inputStream);

		setServiceContext(fileName);

		DDMStructure ddmStructure = ddmStructureLocalService.getStructure(
			groupId, portal.getClassNameId(JournalArticle.class),
			ddmStructureKey);

		DDMTemplate ddmTemplate = ddmTemplateLocalService.fetchTemplate(
			groupId, portal.getClassNameId(DDMStructure.class),
			getKey(fileName));

		if (ddmTemplate != null) {
			if (!developerModeEnabled) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"DDM template with name " + name + " and version " +
							version + " already exists");
				}

				return;
			}

			if (!updateModeEnabled) {
				ddmTemplateLocalService.deleteTemplate(ddmTemplate);
			}
		}

		try {
			if (!updateModeEnabled || (ddmTemplate == null)) {
				ddmTemplate = ddmTemplateLocalService.addTemplate(
					userId, groupId, portal.getClassNameId(DDMStructure.class),
					ddmStructure.getStructureId(),
					portal.getClassNameId(JournalArticle.class),
					getKey(fileName), getMap(name), null,
					DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null, language,
					replaceFileEntryURL(script), true, false, null, null,
					serviceContext);
			}
			else {
				ddmTemplate = ddmTemplateLocalService.updateTemplate(
					userId, ddmTemplate.getTemplateId(),
					portal.getClassNameId(DDMStructure.class), getMap(name),
					null, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null,
					language, replaceFileEntryURL(script),
					ddmTemplate.getCacheable(), serviceContext);
			}
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import DDM template " + fileName, pe);
			}

			throw pe;
		}

		addJournalArticles(
			ddmStructureKey, ddmTemplate.getTemplateKey(),
			_JOURNAL_ARTICLES_DIR_NAME + fileName,
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);
	}

	protected void addDLFileEntries(String dirName) throws Exception {
		File dir = new File(_resourcesDir, dirName);

		if (!dir.isDirectory()|| !dir.canRead()) {
			return;
		}

		File[] files = dir.listFiles();

		if (ArrayUtil.isEmpty(files)) {
			return;
		}

		for (File file : files) {
			if (file.isDirectory()) {
				addDLFolder(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, file);
			}
			else {
				addDLFileEntry(
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, file);
			}
		}
	}

	protected void addDLFileEntry(long parentFolderId, File file)
		throws Exception {

		InputStream inputStream = null;

		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));

			addDLFileEntry(
				parentFolderId, file.getName(), inputStream, file.length());
		}
		finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	protected void addDLFileEntry(
			long parentFolderId, String fileName, InputStream inputStream,
			long length)
		throws Exception {

		String title = FileUtil.stripExtension(fileName);

		setServiceContext(fileName);

		FileEntry fileEntry = null;

		try {
			try {
				fileEntry = dlAppLocalService.addFileEntry(
					userId, groupId, parentFolderId, fileName,
					mimeTypes.getContentType(fileName), title, StringPool.BLANK,
					StringPool.BLANK, inputStream, length, serviceContext);
			}
			catch (DuplicateFileEntryException dfee) {
				fileEntry = dlAppLocalService.getFileEntry(
					groupId, parentFolderId, title);

				String previousVersion = fileEntry.getVersion();

				fileEntry = dlAppLocalService.updateFileEntry(
					userId, fileEntry.getFileEntryId(), fileName,
					mimeTypes.getContentType(fileName), title, StringPool.BLANK,
					StringPool.BLANK, true, inputStream, length,
					serviceContext);

				dlFileEntryLocalService.deleteFileVersion(
					fileEntry.getUserId(), fileEntry.getFileEntryId(),
					previousVersion);
			}
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import DL file entry " + fileName, pe);
			}

			throw pe;
		}

		addPrimaryKey(DLFileEntry.class.getName(), fileEntry.getPrimaryKey());

		_fileEntries.put(fileName, fileEntry);
	}

	protected long addDLFolder(long parentFolderId, File folder)
		throws Exception {

		long folderId = addDLFolder(parentFolderId, folder.getName());

		File[] files = folder.listFiles();

		if (ArrayUtil.isEmpty(files)) {
			return folderId;
		}

		for (File file : files) {
			if (file.isDirectory()) {
				addDLFolder(folderId, file);
			}
			else {
				addDLFileEntry(folderId, file);
			}
		}

		return folderId;
	}

	protected long addDLFolder(long parentFolderId, String folderName)
		throws Exception {

		DLFolder dlFolder = dlFolderLocalService.fetchFolder(
			groupId, parentFolderId, folderName);

		if (dlFolder == null) {
			dlFolder = dlFolderLocalService.addFolder(
				userId, groupId, groupId, false, parentFolderId, folderName,
				null, false, serviceContext);
		}

		addPrimaryKey(DLFolder.class.getName(), dlFolder.getPrimaryKey());

		return dlFolder.getFolderId();
	}

	protected void addJournalArticles(
			String ddmStructureKey, String ddmTemplateKey, String dirName,
			long folderId)
		throws Exception {

		File dir = new File(_resourcesDir, dirName);

		if (!dir.isDirectory() || !dir.canRead()) {
			return;
		}

		File[] files = listFiles(dir);

		for (File file : files) {
			InputStream inputStream = null;

			try {
				inputStream = new BufferedInputStream(
					new FileInputStream(file));

				addJournalArticles(
					ddmStructureKey, ddmTemplateKey, file.getName(), folderId,
					inputStream);
			}
			finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
	}

	protected void addJournalArticles(
			String ddmStructureKey, String ddmTemplateKey, String fileName,
			long folderId, InputStream inputStream)
		throws Exception {

		String title = FileUtil.stripExtension(fileName);

		JSONObject assetJSONObject = _assetJSONObjectMap.get(fileName);

		Map<Locale, String> descriptionMap = null;

		boolean indexable = true;

		if (assetJSONObject != null) {
			String abstractSummary = assetJSONObject.getString(
				"abstractSummary");

			descriptionMap = getMap(abstractSummary);

			indexable = GetterUtil.getBoolean(
				assetJSONObject.getString("indexable"), true);
		}

		String content = StringUtil.read(inputStream);

		content = replaceFileEntryURL(content);

		Locale articleDefaultLocale = LocaleUtil.fromLanguageId(
			LocalizationUtil.getDefaultLanguageId(content));

		boolean smallImage = false;
		String smallImageURL = StringPool.BLANK;

		if (assetJSONObject != null) {
			String smallImageFileName = assetJSONObject.getString("smallImage");

			if (Validator.isNotNull(smallImageFileName)) {
				smallImage = true;

				FileEntry fileEntry = _fileEntries.get(smallImageFileName);

				if (fileEntry != null) {
					smallImageURL = DLUtil.getPreviewURL(
						fileEntry, fileEntry.getFileVersion(), null,
						StringPool.BLANK);
				}
			}
		}

		setServiceContext(fileName);

		String journalArticleId = getJournalId(fileName);

		JournalArticle journalArticle =
			journalArticleLocalService.fetchLatestArticle(
				groupId, journalArticleId, WorkflowConstants.STATUS_ANY);

		try {
			if (journalArticle == null) {
				journalArticle = journalArticleLocalService.addArticle(
					userId, groupId, folderId, 0, 0, journalArticleId, false,
					JournalArticleConstants.VERSION_DEFAULT,
					getMap(articleDefaultLocale, title), descriptionMap,
					content, ddmStructureKey, ddmTemplateKey, StringPool.BLANK,
					1, 1, 2010, 0, 0, 0, 0, 0, 0, 0, true, 0, 0, 0, 0, 0, true,
					indexable, smallImage, smallImageURL, null,
					new HashMap<String, byte[]>(), StringPool.BLANK,
					serviceContext);
			}
			else {
				journalArticle = journalArticleLocalService.updateArticle(
					userId, groupId, folderId, journalArticleId,
					journalArticle.getVersion(),
					getMap(articleDefaultLocale, title), descriptionMap,
					content, ddmStructureKey, ddmTemplateKey, StringPool.BLANK,
					1, 1, 2010, 0, 0, 0, 0, 0, 0, 0, true, 0, 0, 0, 0, 0, true,
					indexable, smallImage, smallImageURL, null,
					new HashMap<String, byte[]>(), StringPool.BLANK,
					serviceContext);
			}

			journalArticleLocalService.updateStatus(
				userId, groupId, journalArticle.getArticleId(),
				journalArticle.getVersion(), WorkflowConstants.STATUS_APPROVED,
				StringPool.BLANK, new HashMap<String, Serializable>(),
				serviceContext);
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import journal article " + fileName, pe);
			}

			throw pe;
		}

		addPrimaryKey(
			JournalArticle.class.getName(), journalArticle.getPrimaryKey());
	}

	protected void addLayout(
			boolean privateLayout, long parentLayoutId,
			JSONObject layoutJSONObject)
		throws Exception {

		if (targetClassName.equals(LayoutSetPrototype.class.getName())) {
			privateLayout = true;
		}

		Map<Locale, String> nameMap = getMap(layoutJSONObject, "name");
		Map<Locale, String> titleMap = getMap(layoutJSONObject, "title");

		String type = layoutJSONObject.getString("type");

		if (Validator.isNull(type)) {
			type = LayoutConstants.TYPE_PORTLET;
		}

		String typeSettings = layoutJSONObject.getString("typeSettings");

		boolean hidden = layoutJSONObject.getBoolean("hidden");

		String themeId = layoutJSONObject.getString("themeId");

		String layoutCss = layoutJSONObject.getString("layoutCss");

		String colorSchemeId = layoutJSONObject.getString("colorSchemeId");

		Map<Locale, String> friendlyURLMap = new HashMap<>();

		String friendlyURL = layoutJSONObject.getString("friendlyURL");

		if (Validator.isNotNull(friendlyURL) &&
			!friendlyURL.startsWith(StringPool.SLASH)) {

			friendlyURL = StringPool.SLASH + friendlyURL;
		}

		friendlyURLMap.put(LocaleUtil.getDefault(), friendlyURL);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(userId);

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		try {
			String layoutPrototypeName = layoutJSONObject.getString(
				"layoutPrototypeName");

			String layoutPrototypeUuid = null;

			if (Validator.isNotNull(layoutPrototypeName)) {
				LayoutPrototype layoutPrototype = getLayoutPrototype(
					companyId, layoutPrototypeName);

				layoutPrototypeUuid = layoutPrototype.getUuid();
			}
			else {
				layoutPrototypeUuid = layoutJSONObject.getString(
					"layoutPrototypeUuid");
			}

			if (Validator.isNotNull(layoutPrototypeUuid)) {
				boolean layoutPrototypeLinkEnabled = GetterUtil.getBoolean(
					layoutJSONObject.getString("layoutPrototypeLinkEnabled"));

				serviceContext.setAttribute(
					"layoutPrototypeLinkEnabled", layoutPrototypeLinkEnabled);

				serviceContext.setAttribute(
					"layoutPrototypeUuid", layoutPrototypeUuid);
			}

			Layout layout = layoutLocalService.fetchLayoutByFriendlyURL(
				groupId, privateLayout, friendlyURL);

			if (layout != null) {
				if (!developerModeEnabled) {
					if (_log.isInfoEnabled()) {
						_log.info(
							"Layout with friendly URL " + friendlyURL +
								" already exists");
					}

					return;
				}

				if (!updateModeEnabled) {
					layoutLocalService.deleteLayout(layout);
				}
			}

			if (!updateModeEnabled || (layout == null)) {
				layout = layoutLocalService.addLayout(
					userId, groupId, privateLayout, parentLayoutId, nameMap,
					titleMap, null, null, null, type, typeSettings, hidden,
					friendlyURLMap, serviceContext);
			}
			else {
				resetLayoutColumns(layout);

				layout = layoutLocalService.updateLayout(
					groupId, privateLayout, layout.getLayoutId(),
					parentLayoutId, nameMap, titleMap,
					layout.getDescriptionMap(), layout.getKeywordsMap(),
					layout.getRobotsMap(), type, hidden, friendlyURLMap,
					layout.getIconImage(), null, serviceContext);
			}

			if (Validator.isNotNull(themeId) ||
				Validator.isNotNull(colorSchemeId)) {

				// If the theme ID or the color scheme ID are not null, then the
				// layout has a custom look and feel and should be updated in
				// the database

				layoutLocalService.updateLookAndFeel(
					groupId, privateLayout, layout.getLayoutId(), themeId,
					colorSchemeId, layoutCss);
			}

			LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

			String layoutTemplateId = layoutJSONObject.getString(
				"layoutTemplateId", _defaultLayoutTemplateId);

			if (Validator.isNotNull(layoutTemplateId)) {
				layoutTypePortlet.setLayoutTemplateId(
					userId, layoutTemplateId, false);
			}

			JSONArray columnsJSONArray = layoutJSONObject.getJSONArray(
				"columns");

			addLayoutColumns(
				layout, LayoutTypePortletConstants.COLUMN_PREFIX,
				columnsJSONArray);

			layoutLocalService.updateLayout(
				groupId, layout.isPrivateLayout(), layout.getLayoutId(),
				layout.getTypeSettings());

			JSONArray layoutsJSONArray = layoutJSONObject.getJSONArray(
				"layouts");

			addLayouts(privateLayout, layout.getLayoutId(), layoutsJSONArray);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import layout " + layoutJSONObject, e);
			}

			throw e;
		}
		finally {
			ServiceContextThreadLocal.popServiceContext();
		}
	}

	protected void addLayoutColumn(
			Layout layout, String columnId, JSONArray columnJSONArray)
		throws Exception {

		if (columnJSONArray == null) {
			return;
		}

		for (int i = 0; i < columnJSONArray.length(); i++) {
			JSONObject portletJSONObject = columnJSONArray.getJSONObject(i);

			if (portletJSONObject == null) {
				String journalArticleId = getJournalId(
					columnJSONArray.getString(i));

				portletJSONObject = getDefaultPortletJSONObject(
					journalArticleId);
			}

			addLayoutColumnPortlet(layout, columnId, portletJSONObject);
		}
	}

	protected void addLayoutColumnPortlet(
			Layout layout, String columnId, JSONObject portletJSONObject)
		throws Exception {

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		String rootPortletId = portletJSONObject.getString("portletId");

		if (Validator.isNull(rootPortletId)) {
			throw new ImporterException("portletId is not specified");
		}

		PortletPreferencesTranslator portletPreferencesTranslator =
			portletPreferencesTranslators.get(rootPortletId);

		String portletId = layoutTypePortlet.addPortletId(
			userId, rootPortletId, columnId, -1, false);

		if (portletId == null) {
			return;
		}

		JSONObject portletPreferencesJSONObject =
			portletJSONObject.getJSONObject("portletPreferences");

		if ((portletPreferencesJSONObject == null) ||
			(portletPreferencesJSONObject.length() == 0)) {

			return;
		}

		if (portletPreferencesTranslator != null) {
			PortletPreferences portletSetup =
				portletPreferencesFactory.getLayoutPortletSetup(
					layout, portletId);

			Iterator<String> iterator = portletPreferencesJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				portletPreferencesTranslator.translate(
					portletPreferencesJSONObject, key, portletSetup);
			}

			portletSetup.store();
		}

		if (rootPortletId.equals(PortletKeys.NESTED_PORTLETS)) {
			JSONArray columnsJSONArray =
				portletPreferencesJSONObject.getJSONArray("columns");

			StringBundler sb = new StringBundler(4);

			sb.append(StringPool.UNDERLINE);
			sb.append(portletId);
			sb.append(StringPool.DOUBLE_UNDERLINE);
			sb.append(LayoutTypePortletConstants.COLUMN_PREFIX);

			addLayoutColumns(layout, sb.toString(), columnsJSONArray);
		}
	}

	protected void addLayoutColumns(
			Layout layout, String columnPrefix, JSONArray columnsJSONArray)
		throws Exception {

		if (columnsJSONArray == null) {
			return;
		}

		for (int i = 0; i < columnsJSONArray.length(); i++) {
			JSONArray columnJSONArray = columnsJSONArray.getJSONArray(i);

			addLayoutColumn(layout, columnPrefix + (i + 1), columnJSONArray);
		}
	}

	protected void addLayoutPrototype(InputStream inputStream)
		throws Exception {

		String content = StringUtil.read(inputStream);

		if (Validator.isNull(content)) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(content);

		JSONObject layoutTemplateJSONObject = jsonObject.getJSONObject(
			"layoutTemplate");

		Map<Locale, String> nameMap = getMap(
			layoutTemplateJSONObject.getString("name"));

		String name = nameMap.get(Locale.getDefault());

		Map<Locale, String> descriptionMap = getMap(
			layoutTemplateJSONObject, "description");

		String uuid = layoutTemplateJSONObject.getString("uuid");

		LayoutPrototype layoutPrototype = getLayoutPrototype(companyId, name);

		if (layoutPrototype != null) {
			if (!developerModeEnabled) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Layout prototype with name " + name +
							" already exists for company " + companyId);
				}

				return;
			}

			if (!updateModeEnabled) {
				layoutPrototypeLocalService.deleteLayoutPrototype(
					layoutPrototype);
			}
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userId);

		if (Validator.isNotNull(uuid)) {
			serviceContext.setUuid(uuid);
		}

		try {
			if (!updateModeEnabled || (layoutPrototype == null)) {
				layoutPrototype =
					layoutPrototypeLocalService.addLayoutPrototype(
						userId, companyId, getMap(name), descriptionMap, true,
						serviceContext);
			}
			else {
				layoutPrototype =
					layoutPrototypeLocalService.updateLayoutPrototype(
						layoutPrototype.getLayoutPrototypeId(), getMap(name),
						descriptionMap, layoutPrototype.isActive(),
						serviceContext);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to import layout prototype " + name, e);
			}

			throw e;
		}

		JSONArray columnsJSONArray = layoutTemplateJSONObject.getJSONArray(
			"columns");

		Layout layout = layoutPrototype.getLayout();

		addLayoutColumns(
			layout, LayoutTypePortletConstants.COLUMN_PREFIX, columnsJSONArray);

		layoutLocalService.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());
	}

	protected void addLayoutPrototype(String dirName) throws Exception {
		File layoutTemplatesDir = new File(_resourcesDir, dirName);

		if (!layoutTemplatesDir.isDirectory() ||
			!layoutTemplatesDir.canRead()) {

			return;
		}

		File[] files = listFiles(layoutTemplatesDir);

		for (File file : files) {
			addLayoutPrototype(getInputStream(file));
		}
	}

	protected void addLayouts(
			boolean privateLayout, long parentLayoutId,
			JSONArray layoutsJSONArray)
		throws Exception {

		if (layoutsJSONArray == null) {
			return;
		}

		for (int i = 0; i < layoutsJSONArray.length(); i++) {
			JSONObject layoutJSONObject = layoutsJSONArray.getJSONObject(i);

			addLayout(privateLayout, parentLayoutId, layoutJSONObject);
		}
	}

	protected void addPrimaryKey(String className, long primaryKey) {
		Set<Long> primaryKeys = _primaryKeys.get(className);

		if (primaryKeys == null) {
			primaryKeys = new HashSet<>();

			_primaryKeys.put(className, primaryKeys);
		}

		primaryKeys.add(primaryKey);
	}

	protected void doImportResources() throws Exception {
		serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(groupId);

		boolean indexReadOnly = indexStatusManager.isIndexReadOnly();

		try {
			indexStatusManager.setIndexReadOnly(true);

			setUpAssets("assets.json");
			setUpSettings("settings.json");
			setUpSitemap("sitemap.json");

			indexStatusManager.setIndexReadOnly(false);

			long startTime = System.currentTimeMillis();

			if (_log.isDebugEnabled()) {
				_log.debug("Commence indexing");
			}

			if (isIndexAfterImport()) {
				index();
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Indexing completed in " +
						(System.currentTimeMillis() - startTime) + "ms");
			}
		}
		finally {
			indexStatusManager.setIndexReadOnly(indexReadOnly);

			_ddmStructureKeys.clear();
			_primaryKeys.clear();
		}
	}

	protected String getDDMStructureLanguage(String fileName) {
		String extension = FileUtil.getExtension(fileName);

		if (extension.equals(TemplateConstants.LANG_TYPE_JSON) ||
			extension.equals(TemplateConstants.LANG_TYPE_XML)) {

			return extension;
		}

		return TemplateConstants.LANG_TYPE_XML;
	}

	protected String getDDMTemplateLanguage(String fileName) {
		String extension = FileUtil.getExtension(fileName);

		if (extension.equals(TemplateConstants.LANG_TYPE_CSS) ||
			extension.equals(TemplateConstants.LANG_TYPE_FTL) ||
			extension.equals(TemplateConstants.LANG_TYPE_VM) ||
			extension.equals(TemplateConstants.LANG_TYPE_XSL)) {

			return extension;
		}

		return TemplateConstants.LANG_TYPE_VM;
	}

	protected JSONObject getDefaultPortletJSONObject(String journalArticleId) {
		JSONObject portletJSONObject = JSONFactoryUtil.createJSONObject();

		portletJSONObject.put("portletId", _JOURNAL_CONTENT_PORTLET_ID);

		JSONObject portletPreferencesJSONObject =
			JSONFactoryUtil.createJSONObject();

		portletPreferencesJSONObject.put("articleId", journalArticleId);
		portletPreferencesJSONObject.put("groupId", groupId);
		portletPreferencesJSONObject.put(
			"portletSetupPortletDecoratorId", "borderless");

		portletJSONObject.put(
			"portletPreferences", portletPreferencesJSONObject);

		return portletJSONObject;
	}

	protected InputStream getInputStream(File file) throws Exception {
		if (!file.exists() || file.isDirectory() || !file.canRead()) {
			return null;
		}

		return new BufferedInputStream(new FileInputStream(file));
	}

	protected InputStream getInputStream(String fileName) throws Exception {
		File file = new File(_resourcesDir, fileName);

		return getInputStream(file);
	}

	protected String getJournalId(String fileName) {
		String id = FileUtil.stripExtension(fileName);

		id = StringUtil.toUpperCase(id);

		return StringUtil.replace(id, CharPool.SPACE, CharPool.DASH);
	}

	protected String[] getJSONArrayAsStringArray(
		JSONObject jsonObject, String key) {

		JSONArray jsonArray = jsonObject.getJSONArray(key);

		if (jsonArray != null) {
			return ArrayUtil.toStringArray(jsonArray);
		}

		return new String[0];
	}

	protected JSONObject getJSONObject(String fileName) throws Exception {
		String json = null;

		InputStream inputStream = getInputStream(fileName);

		if (inputStream == null) {
			return null;
		}

		try {
			json = StringUtil.read(inputStream);
		}
		finally {
			inputStream.close();
		}

		json = StringUtil.replace(
			json, new String[] {"${companyId}", "${groupId}", "${userId}"},
			new String[] {
				String.valueOf(companyId), String.valueOf(groupId),
				String.valueOf(userId)
			});

		return JSONFactoryUtil.createJSONObject(json);
	}

	protected String getKey(String name) {
		name = StringUtil.replace(name, CharPool.SPACE, CharPool.DASH);

		name = StringUtil.toUpperCase(name);

		if (appendVersion) {
			name = name + StringPool.DASH + version;
		}

		return name;
	}

	protected Map<Locale, String> getMap(
		JSONObject layoutJSONObject, String name) {

		Map<Locale, String> map = new HashMap<>();

		JSONObject jsonObject = layoutJSONObject.getJSONObject(
			name.concat("Map"));

		if (jsonObject != null) {
			map = (Map<Locale, String>)LocalizationUtil.deserialize(jsonObject);

			if (!map.containsKey(LocaleUtil.getDefault())) {
				Collection<String> values = map.values();

				Iterator<String> iterator = values.iterator();

				map.put(LocaleUtil.getDefault(), iterator.next());
			}
		}
		else {
			String value = layoutJSONObject.getString(name);

			map.put(LocaleUtil.getDefault(), value);
		}

		return map;
	}

	protected String getName(String name) {
		if (!appendVersion) {
			return name;
		}

		return name + " - " + version;
	}

	protected void index() {
		for (Map.Entry<String, Set<Long>> primaryKeysEntry :
				_primaryKeys.entrySet()) {

			String className = primaryKeysEntry.getKey();

			Set<Long> primaryKeys = primaryKeysEntry.getValue();

			Indexer<?> indexer = indexerRegistry.getIndexer(className);

			if (indexer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No indexer for " + className);
				}

				continue;
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Indexing " + className);
			}

			for (long primaryKey : primaryKeys) {
				try {
					indexer.reindex(className, primaryKey);
				}
				catch (SearchException se) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to index entry for class name " +
								className + " and primary key " + primaryKey,
							se);
					}
				}
			}
		}

		if (_ddmStructureKeys.isEmpty()) {
			return;
		}

		Set<Long> primaryKeys = _primaryKeys.get(
			JournalArticle.class.getName());

		Indexer<?> indexer = indexerRegistry.getIndexer(
			JournalArticle.class.getName());

		for (String ddmStructureKey : _ddmStructureKeys) {
			List<JournalArticle> journalArticles =
				journalArticleLocalService.getArticlesByStructureId(
					getGroupId(), ddmStructureKey, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);

			for (JournalArticle journalArticle : journalArticles) {
				if ((primaryKeys != null) &&
					primaryKeys.contains(journalArticle.getPrimaryKey())) {

					continue;
				}

				try {
					indexer.reindex(
						JournalArticle.class.getName(),
						journalArticle.getPrimaryKey());
				}
				catch (SearchException se) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Cannot index entry: className=" +
								JournalArticle.class.getName() +
								", primaryKey=" +
								journalArticle.getPrimaryKey(),
							se);
					}
				}
			}
		}
	}

	protected boolean isJournalStructureXSD(String xsd) throws Exception {
		Document document = saxReader.read(xsd);

		Element rootElement = document.getRootElement();

		Attribute availableLocalesAttribute = rootElement.attribute(
			"available-locales");

		if (availableLocalesAttribute == null) {
			return true;
		}

		return false;
	}

	protected File[] listFiles(File dir) {
		File[] files = dir.listFiles();

		if (files == null) {
			return new File[0];
		}

		List<File> filesList = new ArrayList<>();

		for (File file : files) {
			if (file.isFile()) {
				filesList.add(file);
			}
		}

		return filesList.toArray(new File[filesList.size()]);
	}

	protected String replaceFileEntryURL(String content) throws Exception {
		Matcher matcher = _fileEntryPattern.matcher(content);

		while (matcher.find()) {
			String fileName = matcher.group(1);

			FileEntry fileEntry = _fileEntries.get(fileName);

			String fileEntryURL = StringPool.BLANK;

			if (fileEntry != null) {
				fileEntryURL = DLUtil.getPreviewURL(
					fileEntry, fileEntry.getFileVersion(), null,
					StringPool.BLANK);
			}

			content = matcher.replaceFirst(fileEntryURL);

			matcher.reset(content);
		}

		return content;
	}

	protected void resetLayoutColumns(Layout layout) {
		UnicodeProperties typeSettings = layout.getTypeSettingsProperties();

		Set<Map.Entry<String, String>> set = typeSettings.entrySet();

		Iterator<Map.Entry<String, String>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();

			String key = entry.getKey();

			if (!key.startsWith("column-")) {
				continue;
			}

			String[] portletIds = StringUtil.split(entry.getValue());

			for (String portletId : portletIds) {
				try {
					portletPreferencesLocalService.deletePortletPreferences(
						PortletKeys.PREFS_OWNER_ID_DEFAULT,
						PortletKeys.PREFS_OWNER_TYPE_LAYOUT, layout.getPlid(),
						portletId);
				}
				catch (PortalException pe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to delete portlet preferences for " +
								"portlet " + portletId,
							pe);
					}
				}
			}

			iterator.remove();
		}

		layout.setTypeSettingsProperties(typeSettings);

		layoutLocalService.updateLayout(layout);
	}

	protected void setServiceContext(String name) {
		JSONObject assetJSONObject = _assetJSONObjectMap.get(name);

		String[] assetTagNames = null;

		if (assetJSONObject != null) {
			assetTagNames = getJSONArrayAsStringArray(assetJSONObject, "tags");
		}

		serviceContext.setAssetTagNames(assetTagNames);
	}

	protected void setUpAssets(JSONArray assetsJSONArray) {
		if (assetsJSONArray == null) {
			return;
		}

		for (int i = 0; i < assetsJSONArray.length(); i++) {
			JSONObject assetJSONObject = assetsJSONArray.getJSONObject(i);

			String name = assetJSONObject.getString("name");

			_assetJSONObjectMap.put(name, assetJSONObject);
		}
	}

	protected void setUpAssets(String fileName) throws Exception {
		if (!updateModeEnabled && !isCompanyGroup()) {
			List<AssetTag> assetTags = assetTagLocalService.getGroupTags(
				groupId);

			for (AssetTag assetTag : assetTags) {
				assetTagLocalService.deleteAssetTag(assetTag);
			}

			repositoryLocalService.deleteRepositories(groupId);

			journalArticleLocalService.deleteArticles(groupId);

			ddmTemplateLocalService.deleteTemplates(groupId);

			ddmStructureLocalService.deleteStructures(groupId);
		}

		JSONObject jsonObject = getJSONObject(fileName);

		if (jsonObject != null) {
			JSONArray assetsJSONArray = jsonObject.getJSONArray("assets");

			setUpAssets(assetsJSONArray);
		}

		addDLFileEntries(_DL_DOCUMENTS_DIR_NAME);

		addApplicationDisplayTemplates(_APPLICATION_DISPLAY_TEMPLATE_DIR_NAME);

		addDDLStructures(_DDL_STRUCTURE_DIR_NAME);

		addDDMStructures(StringPool.BLANK, _JOURNAL_DDM_STRUCTURES_DIR_NAME);

		addDDMTemplates(StringPool.BLANK, _JOURNAL_DDM_TEMPLATES_DIR_NAME);

		addLayoutPrototype(_LAYOUT_PROTOTYPE_DIR_NAME);
	}

	protected void setUpSettings(String fileName) throws Exception {
		if (targetClassName.equals(Group.class.getName())) {
			return;
		}

		JSONObject jsonObject = getJSONObject(fileName);

		if (jsonObject == null) {
			return;
		}

		LayoutSetPrototype layoutSetPrototype =
			layoutSetPrototypeLocalService.getLayoutSetPrototype(
				getTargetClassPK());

		String layoutSetPrototypeSettings = jsonObject.getString(
			"layoutSetPrototypeSettings", StringPool.BLANK);

		layoutSetPrototype.setSettings(layoutSetPrototypeSettings);

		layoutSetPrototypeLocalService.updateLayoutSetPrototype(
			layoutSetPrototype);
	}

	protected void setUpSitemap(String fileName) throws Exception {
		if (!updateModeEnabled) {
			layoutLocalService.deleteLayouts(
				groupId, true, new ServiceContext());

			layoutLocalService.deleteLayouts(
				groupId, false, new ServiceContext());
		}

		JSONObject jsonObject = getJSONObject(fileName);

		if (jsonObject == null) {
			return;
		}

		_defaultLayoutTemplateId = jsonObject.getString(
			"layoutTemplateId", StringPool.BLANK);

		updateLayoutSetThemeId(jsonObject);

		JSONArray layoutsJSONArray = jsonObject.getJSONArray("layouts");

		if (layoutsJSONArray != null) {
			addLayouts(
				false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
				layoutsJSONArray);
		}
		else {
			JSONArray publicPagesJSONArray = jsonObject.getJSONArray(
				"publicPages");

			if (publicPagesJSONArray != null) {
				addLayouts(
					false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
					publicPagesJSONArray);
			}

			JSONArray privatePagesJSONArray = jsonObject.getJSONArray(
				"privatePages");

			if (privatePagesJSONArray != null) {
				addLayouts(
					true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
					privatePagesJSONArray);
			}
		}
	}

	protected void updateLayoutSetThemeId(JSONObject sitemapJSONObject)
		throws Exception {

		String themeId = sitemapJSONObject.getString("themeId");

		if (Validator.isNotNull(themeId)) {
			Theme theme = themeLocalService.fetchTheme(companyId, themeId);

			if (theme == null) {
				themeId = null;
			}
		}

		if (Validator.isNull(themeId)) {
			int pos = servletContextName.indexOf("-theme");

			if (pos != -1) {
				themeId =
					servletContextName.substring(0, pos) +
						PortletConstants.WAR_SEPARATOR + servletContextName;

				themeId = portal.getJsSafePortletId(themeId);

				Theme theme = themeLocalService.fetchTheme(companyId, themeId);

				if (theme == null) {
					themeId = null;
				}
			}
		}

		if (Validator.isNotNull(themeId)) {
			layoutSetLocalService.updateLookAndFeel(
				groupId, themeId, null, null);
		}
	}

	protected final AssetTagLocalService assetTagLocalService;
	protected final DDMFormJSONDeserializer ddmFormJSONDeserializer;
	protected final DDMFormXSDDeserializer ddmFormXSDDeserializer;
	protected final DDMStructureLocalService ddmStructureLocalService;
	protected final DDMTemplateLocalService ddmTemplateLocalService;
	protected final DDMXML ddmxml;
	protected final DLAppLocalService dlAppLocalService;
	protected final DLFileEntryLocalService dlFileEntryLocalService;
	protected final DLFolderLocalService dlFolderLocalService;
	protected final IndexerRegistry indexerRegistry;
	protected final IndexStatusManager indexStatusManager;
	protected final JournalArticleLocalService journalArticleLocalService;
	protected final JournalFolderLocalService journalFolderLocalService;
	protected final LayoutLocalService layoutLocalService;
	protected final LayoutPrototypeLocalService layoutPrototypeLocalService;
	protected final LayoutSetLocalService layoutSetLocalService;
	protected final LayoutSetPrototypeLocalService
		layoutSetPrototypeLocalService;
	protected final MimeTypes mimeTypes;
	protected final Portal portal;
	protected final PortletPreferencesFactory portletPreferencesFactory;
	protected final PortletPreferencesLocalService
		portletPreferencesLocalService;
	protected final Map<String, PortletPreferencesTranslator>
		portletPreferencesTranslators;
	protected final RepositoryLocalService repositoryLocalService;
	protected final SAXReader saxReader;
	protected ServiceContext serviceContext;
	protected final ThemeLocalService themeLocalService;

	private static final String _APPLICATION_DISPLAY_TEMPLATE_DIR_NAME =
		"/templates/application_display";

	private static final Object[][] _APPLICATION_DISPLAY_TEMPLATE_TYPES =
		new Object[][] {
			{"asset_category", "com.liferay.asset.kernel.model.AssetCategory"},
			{"asset_entry", "com.liferay.asset.kernel.model.AssetEntry"},
			{"asset_tag", "com.liferay.asset.kernel.model.AssetTag"},
			{"blogs_entry", "com.liferay.blogs.kernel.model.BlogsEntry"},
			{
				"bread_crumb",
				"com.liferay.portal.kernel.servlet.taglib.ui.BreadcrumbEntry"
			},
			{
				"document_library",
				"com.liferay.portal.kernel.repository.model.FileEntry"
			},
			{
				"language_entry",
				"com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry"
			},
			{"rss_feed", "com.liferay.rss.web.util.RSSFeed"},
			{"site_map", "com.liferay.portal.kernel.model.LayoutSet"},
			{"site_navigation", "com.liferay.portal.kernel.theme.NavItem"},
			{"wiki_page", "com.liferay.wiki.model.WikiPage"}
		};

	private static final String _DDL_STRUCTURE_DIR_NAME =
		"/templates/dynamic_data_list/structure";

	private static final String _DDL_STRUCTURE_DISPLAY_TEMPLATE_DIR_NAME =
		"/templates/dynamic_data_list/display_template";

	private static final String _DDL_STRUCTURE_FORM_TEMPLATE_DIR_NAME =
		"/templates/dynamic_data_list/form_template";

	private static final String _DL_DOCUMENTS_DIR_NAME =
		"/document_library/documents/";

	private static final String _JOURNAL_ARTICLES_DIR_NAME =
		"/journal/articles/";

	private static final String _JOURNAL_CONTENT_PORTLET_ID =
		"com_liferay_journal_content_web_portlet_JournalContentPortlet";

	private static final String _JOURNAL_DDM_STRUCTURES_DIR_NAME =
		"/journal/structures/";

	private static final String _JOURNAL_DDM_TEMPLATES_DIR_NAME =
		"/journal/templates/";

	private static final String _LAYOUT_PROTOTYPE_DIR_NAME = "/templates/page";

	private static final Log _log = LogFactoryUtil.getLog(
		FileSystemImporter.class);

	private final Map<String, JSONObject> _assetJSONObjectMap = new HashMap<>();
	private final Set<String> _ddmStructureKeys = new HashSet<>();
	private String _defaultLayoutTemplateId;
	private final Map<String, FileEntry> _fileEntries = new HashMap<>();
	private final Pattern _fileEntryPattern = Pattern.compile(
		"\\[\\$FILE=([^\\$]+)\\$\\]");
	private final Map<String, Set<Long>> _primaryKeys = new HashMap<>();
	private File _resourcesDir;

	private class DefaultedPortletPreferencesTranslatorMap
		extends HashMap<String, PortletPreferencesTranslator> {

		public DefaultedPortletPreferencesTranslatorMap(
			Map<String, PortletPreferencesTranslator>
				portletPreferencesTranslators,
			PortletPreferencesTranslator portletPreferencesTranslator) {

			super(portletPreferencesTranslators);

			_portletPreferencesTranslator = portletPreferencesTranslator;
		}

		@Override
		public PortletPreferencesTranslator get(Object key) {
			PortletPreferencesTranslator value = super.get(key);

			if (value == null) {
				value = _portletPreferencesTranslator;
			}

			return value;
		}

		private final PortletPreferencesTranslator
			_portletPreferencesTranslator;

	}

}