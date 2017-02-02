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

package com.liferay.portal.template.freemarker.internal;

import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.PortalWebResourceConstants;
import com.liferay.portal.kernel.servlet.PortalWebResourcesUtil;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.template.TemplateResourceParser;
import com.liferay.portal.template.URLResourceParser;

import java.io.IOException;

import java.net.URL;

import javax.servlet.ServletContext;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Mika Koivisto
 */
@Component(
	immediate = true,
	property = {"lang.type=" + TemplateConstants.LANG_TYPE_FTL},
	service = TemplateResourceParser.class
)
public class FreeMarkerServletResourceParser extends URLResourceParser {

	@Override
	public URL getURL(String name) throws IOException {
		int pos = name.indexOf(TemplateConstants.SERVLET_SEPARATOR);

		if (pos == -1) {
			return null;
		}

		String servletContextName = name.substring(0, pos);

		if (servletContextName.equals(PortalUtil.getPathContext())) {
			servletContextName = PortalUtil.getServletContextName();
		}

		ServletContext servletContext = _serviceTrackerMap.getService(
			servletContextName);

		if (servletContext == null) {
			_log.error(
				name + " is invalid because " + servletContextName +
					" does not map to a servlet context");

			return null;
		}

		String templateName = name.substring(
			pos + TemplateConstants.SERVLET_SEPARATOR.length());

		if (_log.isDebugEnabled()) {
			_log.debug(
				name + " is associated with the servlet context " +
					servletContextName + " " + servletContext);
		}

		URL url = servletContext.getResource(templateName);

		if (url == null) {
			url = PortalWebResourcesUtil.getResource(templateName);
		}

		if ((url == null) && templateName.endsWith("/init_custom.ftl")) {
			if (_log.isWarnEnabled()) {
				_log.warn("The template " + name + " should be created");
			}

			ServletContext themeClassicServletContext =
				PortalWebResourcesUtil.getServletContext(
					PortalWebResourceConstants.RESOURCE_TYPE_THEME_CLASSIC);

			url = themeClassicServletContext.getResource(
				"/classic/templates/init_custom.ftl");
		}

		return url;
	}

	@Activate
	protected void activate(final BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, ServletContext.class, null,
			new ServiceReferenceMapper<String, ServletContext>() {

				@Override
				public void map(
					ServiceReference<ServletContext> serviceReference,
					ServiceReferenceMapper.Emitter<String> emitter) {

					try {
						ServletContext servletContext =
							bundleContext.getService(serviceReference);

						String servletContextName = GetterUtil.getString(
							servletContext.getServletContextName());

						emitter.emit(servletContextName);
					}
					finally {
						bundleContext.ungetService(serviceReference);
					}
				}

			});
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FreeMarkerServletResourceParser.class);

	private ServiceTrackerMap<String, ServletContext> _serviceTrackerMap;

}