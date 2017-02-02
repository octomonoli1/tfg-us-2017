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

package com.liferay.blogs.web.internal.exportimport.portlet.preferences.processor;

import com.liferay.blogs.web.constants.BlogsPortletKeys;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.portlet.preferences.processor.Capability;
import com.liferay.exportimport.portlet.preferences.processor.ExportImportPortletPreferencesProcessor;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mate Thurzo
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=" + BlogsPortletKeys.BLOGS},
	service = ExportImportPortletPreferencesProcessor.class
)
public class BlogsExportImportPortletPreferencesProcessor
	implements ExportImportPortletPreferencesProcessor {

	@Override
	public List<Capability> getExportCapabilities() {
		return ListUtil.toList(
			new Capability[] {_blogsPortletDisplayTemplateExportCapability});
	}

	@Override
	public List<Capability> getImportCapabilities() {
		return ListUtil.toList(
			new Capability[] {_blogsPortletDisplayTemplateImportCapability});
	}

	@Override
	public PortletPreferences processExportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		return null;
	}

	@Override
	public PortletPreferences processImportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		return null;
	}

	@Reference(unbind = "-")
	protected void setBlogsPortletDisplayTemplateExportCapability(
		BlogsPortletDisplayTemplateExportCapability
			blogsPortletDisplayTemplateExportCapability) {

		_blogsPortletDisplayTemplateExportCapability =
			blogsPortletDisplayTemplateExportCapability;
	}

	@Reference(unbind = "-")
	protected void setBlogsPortletDisplayTemplateImportCapability(
		BlogsPortletDisplayTemplateImportCapability
			blogsPortletDisplayTemplateImportCapability) {

		_blogsPortletDisplayTemplateImportCapability =
			blogsPortletDisplayTemplateImportCapability;
	}

	private BlogsPortletDisplayTemplateExportCapability
		_blogsPortletDisplayTemplateExportCapability;
	private BlogsPortletDisplayTemplateImportCapability
		_blogsPortletDisplayTemplateImportCapability;

}