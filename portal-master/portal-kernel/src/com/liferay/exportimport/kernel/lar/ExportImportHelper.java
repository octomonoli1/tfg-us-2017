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

package com.liferay.exportimport.kernel.lar;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateRange;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.zip.ZipWriter;

import java.io.File;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

/**
 * @author Zsolt Berentey
 */
@ProviderType
public interface ExportImportHelper {

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public static final String DATA_HANDLER_COMPANY_SECURE_URL =
		"@data_handler_company_secure_url@";

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public static final String DATA_HANDLER_COMPANY_URL =
		"@data_handler_company_url@";

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public static final String DATA_HANDLER_GROUP_FRIENDLY_URL =
		"@data_handler_group_friendly_url@";

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public static final String DATA_HANDLER_PATH_CONTEXT =
		"@data_handler_path_context@";

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public static final String DATA_HANDLER_PRIVATE_GROUP_SERVLET_MAPPING =
		"@data_handler_private_group_servlet_mapping@";

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public static final String DATA_HANDLER_PRIVATE_LAYOUT_SET_SECURE_URL =
		"@data_handler_private_layout_set_secure_url@";

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public static final String DATA_HANDLER_PRIVATE_LAYOUT_SET_URL =
		"@data_handler_private_layout_set_url@";

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public static final String DATA_HANDLER_PRIVATE_USER_SERVLET_MAPPING =
		"@data_handler_private_user_servlet_mapping@";

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public static final String DATA_HANDLER_PUBLIC_LAYOUT_SET_SECURE_URL =
		"@data_handler_public_layout_set_secure_url@";

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public static final String DATA_HANDLER_PUBLIC_LAYOUT_SET_URL =
		"@data_handler_public_layout_set_url@";

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public static final String DATA_HANDLER_PUBLIC_SERVLET_MAPPING =
		"@data_handler_public_servlet_mapping@";

	public static final String TEMP_FOLDER_NAME =
		ExportImportHelper.class.getName();

	public long[] getAllLayoutIds(long groupId, boolean privateLayout);

	public Map<Long, Boolean> getAllLayoutIdsMap(
		long groupId, boolean privateLayout);

	/**
	 * @deprecated As of 7.0.0, moved to {@link
	 *             ExportImportDateUtil#getCalendar(PortletRequest, String,
	 *             boolean)}
	 */
	@Deprecated
	public Calendar getCalendar(
		PortletRequest portletRequest, String paramPrefix,
		boolean timeZoneSensitive);

	public List<Portlet> getDataSiteLevelPortlets(long companyId)
		throws Exception;

	public List<Portlet> getDataSiteLevelPortlets(
			long companyId, boolean excludeDataAlwaysStaged)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, moved to {@link
	 *             ExportImportDateUtil#getDateRange(PortletRequest, long,
	 *             boolean, long, String, String)}
	 */
	@Deprecated
	public DateRange getDateRange(
			PortletRequest portletRequest, long groupId, boolean privateLayout,
			long plid, String portletId, String defaultRange)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public Layout getExportableLayout(ThemeDisplay themeDisplay)
		throws PortalException;

	public String getExportableRootPortletId(long companyId, String portletId)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #getExportPortletControlsMap(long, String, Map)}
	 */
	@Deprecated
	public boolean[] getExportPortletControls(
			long companyId, String portletId,
			Map<String, String[]> parameterMap)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #getExportPortletControlsMap(long, String, Map, String)}
	 */
	@Deprecated
	public boolean[] getExportPortletControls(
			long companyId, String portletId,
			Map<String, String[]> parameterMap, String type)
		throws Exception;

	public Map<String, Boolean> getExportPortletControlsMap(
			long companyId, String portletId,
			Map<String, String[]> parameterMap)
		throws Exception;

	public Map<String, Boolean> getExportPortletControlsMap(
			long companyId, String portletId,
			Map<String, String[]> parameterMap, String type)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #getImportPortletControlsMap(long, String, Map, Element,
	 *             ManifestSummary)}
	 */
	@Deprecated
	public boolean[] getImportPortletControls(
			long companyId, String portletId,
			Map<String, String[]> parameterMap, Element portletDataElement)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #getImportPortletControlsMap(long, String, Map, Element,
	 *             ManifestSummary)}
	 */
	@Deprecated
	public boolean[] getImportPortletControls(
			long companyId, String portletId,
			Map<String, String[]> parameterMap, Element portletDataElement,
			ManifestSummary manifestSummary)
		throws Exception;

	public Map<String, Boolean> getImportPortletControlsMap(
			long companyId, String portletId,
			Map<String, String[]> parameterMap, Element portletDataElement,
			ManifestSummary manifestSummary)
		throws Exception;

	public Map<Long, Boolean> getLayoutIdMap(PortletRequest portletRequest)
		throws PortalException;

	public long[] getLayoutIds(List<Layout> layouts);

	public long[] getLayoutIds(Map<Long, Boolean> layoutIdMap)
		throws PortalException;

	public long[] getLayoutIds(
			Map<Long, Boolean> layoutIdMap, long targetGroupId)
		throws PortalException;

	public long[] getLayoutIds(PortletRequest portletRequest)
		throws PortalException;

	public long[] getLayoutIds(
			PortletRequest portletRequest, long targetGroupId)
		throws PortalException;

	public ZipWriter getLayoutSetZipWriter(long groupId);

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #getManifestSummary(PortletDataContext)}
	 */
	@Deprecated
	public ManifestSummary getManifestSummary(
			long userId, long groupId, Map<String, String[]> parameterMap,
			File file)
		throws Exception;

	public ManifestSummary getManifestSummary(
			long userId, long groupId, Map<String, String[]> parameterMap,
			FileEntry fileEntry)
		throws Exception;

	public ManifestSummary getManifestSummary(
			PortletDataContext portletDataContext)
		throws Exception;

	public List<Layout> getMissingParentLayouts(Layout layout, long liveGroupId)
		throws PortalException;

	public long getModelDeletionCount(
			final PortletDataContext portletDataContext,
			final StagedModelType stagedModelType)
		throws PortalException;

	public ZipWriter getPortletZipWriter(String portletId);

	public String getSelectedLayoutsJSON(
		long groupId, boolean privateLayout, String selectedNodes);

	public FileEntry getTempFileEntry(
			long groupId, long userId, String folderName)
		throws PortalException;

	public UserIdStrategy getUserIdStrategy(long userId, String userIdStrategy)
		throws PortalException;

	public boolean isReferenceWithinExportScope(
		PortletDataContext portletDataContext, StagedModel stagedModel);

	/**
	 * @deprecated As of 7.0.0, replaced by
	 *             com.liferay.exportimport.content.processor.ExportImportContentProcessor#replaceExportContentReferences(
	 *             PortletDataContext, StagedModel, String, boolean, boolean) in
	 *             the export-import-api module
	 */
	@Deprecated
	public String replaceExportContentReferences(
			PortletDataContext portletDataContext,
			StagedModel entityStagedModel, Element entityElement,
			String content, boolean exportReferencedContent)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, replaced by
	 *             com.liferay.exportimport.content.processor.ExportImportContentProcessor#replaceExportContentReferences(
	 *             PortletDataContext, StagedModel, String, boolean, boolean) in
	 *             the export-import-api module
	 */
	@Deprecated
	public String replaceExportContentReferences(
			PortletDataContext portletDataContext,
			StagedModel entityStagedModel, String content,
			boolean exportReferencedContent)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, replaced by
	 *             com.liferay.exportimport.content.processor.ExportImportContentProcessorr#replaceExportContentReferences(
	 *             PortletDataContext, StagedModel, String, boolean, boolean) in
	 *             the export-import-api module
	 */
	@Deprecated
	public String replaceExportContentReferences(
			PortletDataContext portletDataContext,
			StagedModel entityStagedModel, String content,
			boolean exportReferencedContent, boolean escapeContent)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public String replaceExportDLReferences(
			PortletDataContext portletDataContext,
			StagedModel entityStagedModel, Element entityElement,
			String content, boolean exportReferencedContent)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public String replaceExportDLReferences(
			PortletDataContext portletDataContext,
			StagedModel entityStagedModel, String content,
			boolean exportReferencedContent)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public String replaceExportLayoutReferences(
			PortletDataContext portletDataContext, String content)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public String replaceExportLayoutReferences(
			PortletDataContext portletDataContext, String content,
			boolean exportReferencedContent)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public String replaceExportLinksToLayouts(
			PortletDataContext portletDataContext,
			StagedModel entityStagedModel, Element entityElement,
			String content, boolean exportReferencedContent)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public String replaceExportLinksToLayouts(
			PortletDataContext portletDataContext,
			StagedModel entityStagedModel, String content)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, replaced by
	 *             com.liferay.exportimport.content.processor.ExportImportContentProcessor#replaceImportContentReferences(
	 *             PortletDataContext, StagedModel, String) in the
	 *             export-import-api module
	 */
	@Deprecated
	public String replaceImportContentReferences(
			PortletDataContext portletDataContext, Element entityElement,
			String content, boolean importReferencedContent)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, replaced by
	 *             com.liferay.exportimport.content.processor.ExportImportContentProcessor#replaceImportContentReferences(
	 *             PortletDataContext, StagedModel, String) in the
	 *             export-import-api module
	 */
	@Deprecated
	public String replaceImportContentReferences(
			PortletDataContext portletDataContext,
			StagedModel entityStagedModel, String content)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public String replaceImportDLReferences(
			PortletDataContext portletDataContext, Element entityElement,
			String content, boolean importReferencedContent)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public String replaceImportDLReferences(
			PortletDataContext portletDataContext,
			StagedModel entityStagedModel, String content)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public String replaceImportLayoutReferences(
			PortletDataContext portletDataContext, String content)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public String replaceImportLayoutReferences(
			PortletDataContext portletDataContext, String content,
			boolean importReferencedContent)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public String replaceImportLinksToLayouts(
			PortletDataContext portletDataContext, String content)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public String replaceImportLinksToLayouts(
			PortletDataContext portletDataContext, String content,
			boolean importReferencedContent)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, see {@link
	 *             DefaultConfigurationPortletDataHandler#updateExportPortletPreferencesClassPKs(
	 *             PortletDataContext, Portlet, PortletPreferences, String,
	 *             String)}
	 */
	@Deprecated
	public void updateExportPortletPreferencesClassPKs(
			PortletDataContext portletDataContext, Portlet portlet,
			PortletPreferences portletPreferences, String key, String className)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #updateExportPortletPreferencesClassPKs(PortletDataContext,
	 *             Portlet, PortletPreferences, String, String)}
	 */
	@Deprecated
	public void updateExportPortletPreferencesClassPKs(
			PortletDataContext portletDataContext, Portlet portlet,
			PortletPreferences portletPreferences, String key, String className,
			Element rootElement)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, see {@link
	 *             DefaultConfigurationPortletDataHandler#updateImportPortletPreferencesClassPKs(
	 *             PortletDataContext, PortletPreferences, String, Class, long)}
	 */
	@Deprecated
	public void updateImportPortletPreferencesClassPKs(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences, String key, Class<?> clazz,
			long companyGroupId)
		throws Exception;

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #validateMissingReferences(PortletDataContext)}
	 */
	@Deprecated
	public MissingReferences validateMissingReferences(
			long userId, long groupId, Map<String, String[]> parameterMap,
			File file)
		throws Exception;

	public MissingReferences validateMissingReferences(
			final PortletDataContext portletDataContext)
		throws Exception;

	public void writeManifestSummary(
		Document document, ManifestSummary manifestSummary);

}