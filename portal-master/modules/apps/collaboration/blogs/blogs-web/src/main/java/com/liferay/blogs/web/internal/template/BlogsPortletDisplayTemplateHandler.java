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

package com.liferay.blogs.web.internal.template;

import com.liferay.blogs.configuration.BlogsConfiguration;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryLocalService;
import com.liferay.blogs.kernel.service.BlogsEntryService;
import com.liferay.blogs.web.constants.BlogsPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Juan Fernández
 */
@Component(
	configurationPid = "com.liferay.blogs.configuration.BlogsConfiguration",
	immediate = true,
	property = {"javax.portlet.name=" + BlogsPortletKeys.BLOGS},
	service = TemplateHandler.class
)
public class BlogsPortletDisplayTemplateHandler
	extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return BlogsEntry.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		String portletTitle = PortalUtil.getPortletTitle(
			BlogsPortletKeys.BLOGS, locale);

		return portletTitle.concat(StringPool.SPACE).concat(
			LanguageUtil.get(locale, "template"));
	}

	@Override
	public String getResourceName() {
		return BlogsPortletKeys.BLOGS;
	}

	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
			long classPK, String language, Locale locale)
		throws Exception {

		Map<String, TemplateVariableGroup> templateVariableGroups =
			super.getTemplateVariableGroups(classPK, language, locale);

		TemplateVariableGroup templateVariableGroup =
			templateVariableGroups.get("fields");

		templateVariableGroup.empty();

		templateVariableGroup.addCollectionVariable(
			"blog-entries", List.class, PortletDisplayTemplateManager.ENTRIES,
			"blog-entry", BlogsEntry.class, "curBlogEntry", "title");

		String[] restrictedVariables = getRestrictedVariables(language);

		TemplateVariableGroup blogServicesTemplateVariableGroup =
			new TemplateVariableGroup("blog-services", restrictedVariables);

		blogServicesTemplateVariableGroup.setAutocompleteEnabled(false);

		blogServicesTemplateVariableGroup.addServiceLocatorVariables(
			BlogsEntryLocalService.class, BlogsEntryService.class);

		templateVariableGroups.put(
			blogServicesTemplateVariableGroup.getLabel(),
			blogServicesTemplateVariableGroup);

		return templateVariableGroups;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_blogsConfiguration = ConfigurableUtil.createConfigurable(
			BlogsConfiguration.class, properties);
	}

	@Override
	protected String getTemplatesConfigPath() {
		return _blogsConfiguration.displayTemplatesConfig();
	}

	private volatile BlogsConfiguration _blogsConfiguration;

}