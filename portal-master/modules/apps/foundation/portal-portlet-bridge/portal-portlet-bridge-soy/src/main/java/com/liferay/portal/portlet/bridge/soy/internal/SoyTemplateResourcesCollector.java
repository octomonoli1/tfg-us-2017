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

package com.liferay.portal.portlet.bridge.soy.internal;

import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateException;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.TemplateResourceLoaderUtil;
import com.liferay.portal.kernel.template.URLTemplateResource;
import com.liferay.portal.kernel.util.StringPool;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.framework.wiring.BundleWire;
import org.osgi.framework.wiring.BundleWiring;

/**
 * @author Marcellus Tavares
 */
public class SoyTemplateResourcesCollector {

	public SoyTemplateResourcesCollector(Bundle bundle, String templatePath) {
		_bundle = bundle;
		_templatePath = templatePath;
	}

	public List<TemplateResource> getTemplateResources()
		throws TemplateException {

		List<TemplateResource> templateResources = new ArrayList<>();

		collectBundleTemplateResources(templateResources);
		collectProviderBundlesTemplateResources(templateResources);

		return templateResources;
	}

	protected void collectBundleTemplateResources(
		List<TemplateResource> templateResources) {

		List<URL> urls = getSoyResourceURLs(_bundle, _templatePath);

		for (URL url : urls) {
			templateResources.add(new URLTemplateResource(url.getPath(), url));
		}
	}

	protected void collectProviderBundlesTemplateResources(
			List<TemplateResource> templateResources)
		throws TemplateException {

		BundleWiring bundleWiring = _bundle.adapt(BundleWiring.class);

		for (BundleWire bundleWire : bundleWiring.getRequiredWires("soy")) {
			String capabilityPrefix = getCapabilityPrefix(
				bundleWire.getCapability());

			List<URL> urls = getSoyResourceURLs(
				getProviderBundle(bundleWire), StringPool.SLASH);

			for (URL url : urls) {
				String templateId = getTemplateId(capabilityPrefix, url);

				TemplateResource templateResource =
					TemplateResourceLoaderUtil.getTemplateResource(
						TemplateConstants.LANG_TYPE_SOY, templateId);

				templateResources.add(templateResource);
			}
		}
	}

	protected String getCapabilityPrefix(BundleCapability bundleCapability) {
		Map<String, Object> attributes = bundleCapability.getAttributes();

		return attributes.get("type") + StringPool.UNDERLINE +
			attributes.get("version");
	}

	protected Bundle getProviderBundle(BundleWire bundleWire) {
		BundleRevision bundleRevision = bundleWire.getProvider();

		return bundleRevision.getBundle();
	}

	protected List<URL> getSoyResourceURLs(Bundle bundle, String templatePath) {
		Enumeration<URL> urls = bundle.findEntries(
			"META-INF/resources" + templatePath, _SOY_FILE_EXTENSION, true);

		if (urls == null) {
			return Collections.emptyList();
		}

		return Collections.list(urls);
	}

	protected String getTemplateId(String capabilityPrefix, URL url) {
		return capabilityPrefix.concat(
			TemplateConstants.BUNDLE_SEPARATOR).concat(url.getPath());
	}

	private static final String _SOY_FILE_EXTENSION = "*.soy";

	private final Bundle _bundle;
	private final String _templatePath;

}