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

package com.liferay.document.library.display.context;

import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;
import com.liferay.portal.kernel.servlet.taglib.ui.ToolbarItem;

import java.io.IOException;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iván Zaera
 */
public class BaseDLViewFileVersionDisplayContext
	extends BaseDLDisplayContext<DLViewFileVersionDisplayContext>
	implements DLViewFileVersionDisplayContext {

	public BaseDLViewFileVersionDisplayContext(
		UUID uuid, DLViewFileVersionDisplayContext parentDLDisplayContext,
		HttpServletRequest request, HttpServletResponse response,
		FileVersion fileVersion) {

		super(uuid, parentDLDisplayContext, request, response);

		this.fileVersion = fileVersion;
	}

	@Override
	public String getCssClassFileMimeType() {
		return parentDisplayContext.getCssClassFileMimeType();
	}

	@Override
	public DDMFormValues getDDMFormValues(DDMStructure ddmStructure)
		throws PortalException {

		return parentDisplayContext.getDDMFormValues(ddmStructure);
	}

	@Override
	public DDMFormValues getDDMFormValues(long ddmStorageId)
		throws PortalException {

		return parentDisplayContext.getDDMFormValues(ddmStorageId);
	}

	@Override
	public List<DDMStructure> getDDMStructures() throws PortalException {
		return parentDisplayContext.getDDMStructures();
	}

	@Override
	public int getDDMStructuresCount() throws PortalException {
		return parentDisplayContext.getDDMStructuresCount();
	}

	@Override
	public String getDiscussionClassName() {
		return parentDisplayContext.getDiscussionClassName();
	}

	@Override
	public long getDiscussionClassPK() {
		return parentDisplayContext.getDiscussionClassPK();
	}

	@Override
	public String getDiscussionLabel(Locale locale) {
		return parentDisplayContext.getDiscussionLabel(locale);
	}

	@Override
	public Menu getMenu() throws PortalException {
		return parentDisplayContext.getMenu();
	}

	@Override
	public List<ToolbarItem> getToolbarItems() throws PortalException {
		return parentDisplayContext.getToolbarItems();
	}

	@Override
	public boolean hasPreview() {
		return parentDisplayContext.hasPreview();
	}

	@Override
	public boolean isDownloadLinkVisible() throws PortalException {
		return parentDisplayContext.isDownloadLinkVisible();
	}

	@Override
	public boolean isVersionInfoVisible() throws PortalException {
		return parentDisplayContext.isVersionInfoVisible();
	}

	@Override
	public void renderPreview(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		parentDisplayContext.renderPreview(request, response);
	}

	protected FileVersion fileVersion;

}