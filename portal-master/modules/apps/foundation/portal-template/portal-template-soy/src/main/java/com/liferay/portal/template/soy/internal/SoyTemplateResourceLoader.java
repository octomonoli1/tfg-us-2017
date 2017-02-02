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

package com.liferay.portal.template.soy.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.SingleVMPool;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.TemplateResourceLoader;
import com.liferay.portal.template.DefaultTemplateResourceLoader;
import com.liferay.portal.template.soy.configuration.SoyTemplateEngineConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Miroslav Ligas
 */
@Component(
	configurationPid = "com.liferay.portal.template.soy.configuration.SoyTemplateEngineConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	service = {SoyTemplateResourceLoader.class, TemplateResourceLoader.class}
)
public class SoyTemplateResourceLoader implements TemplateResourceLoader {

	@Override
	public void clearCache() {
		_defaultTemplateResourceLoader.clearCache();
	}

	@Override
	public void clearCache(String templateId) {
		_defaultTemplateResourceLoader.clearCache(templateId);
	}

	@Deactivate
	@Override
	public void destroy() {
		_defaultTemplateResourceLoader.destroy();
	}

	@Override
	public String getName() {
		return _defaultTemplateResourceLoader.getName();
	}

	@Override
	public TemplateResource getTemplateResource(String templateId) {
		return _defaultTemplateResourceLoader.getTemplateResource(templateId);
	}

	@Override
	public boolean hasTemplateResource(String templateId) {
		return _defaultTemplateResourceLoader.hasTemplateResource(templateId);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_soyTemplateEngineConfiguration = ConfigurableUtil.createConfigurable(
			SoyTemplateEngineConfiguration.class, properties);

		_defaultTemplateResourceLoader = new DefaultTemplateResourceLoader(
			TemplateConstants.LANG_TYPE_SOY, null,
			_soyTemplateEngineConfiguration.resourceModificationCheck(),
			_multiVMPool, _singleVMPool);
	}

	@Reference(unbind = "-")
	protected void setMultiVMPool(MultiVMPool multiVMPool) {
		_multiVMPool = multiVMPool;
	}

	@Reference(unbind = "-")
	protected void setSingleVMPool(SingleVMPool singleVMPool) {
		_singleVMPool = singleVMPool;
	}

	private static volatile DefaultTemplateResourceLoader
		_defaultTemplateResourceLoader;
	private static volatile SoyTemplateEngineConfiguration
		_soyTemplateEngineConfiguration;

	private MultiVMPool _multiVMPool;
	private SingleVMPool _singleVMPool;

}