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

package com.liferay.dynamic.data.lists.web.internal.portlet;

import com.liferay.dynamic.data.lists.constants.DDLPortletKeys;
import com.liferay.dynamic.data.lists.constants.DDLWebKeys;
import com.liferay.dynamic.data.lists.exception.NoSuchRecordSetException;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordService;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService;
import com.liferay.dynamic.data.lists.service.DDLRecordSetService;
import com.liferay.dynamic.data.lists.util.DDL;
import com.liferay.dynamic.data.lists.web.configuration.DDLWebConfiguration;
import com.liferay.dynamic.data.lists.web.internal.display.context.DDLDisplayContext;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.dynamic.data.mapping.util.DDMDisplayRegistry;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.PortletPreferencesException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(
	configurationPid = "com.liferay.dynamic.data.lists.web.configuration.DDLWebConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-dynamic-data-lists-display",
		"com.liferay.portlet.display-category=category.collaboration",
		"com.liferay.portlet.header-portal-javascript=/o/dynamic-data-mapping-web/js/custom_fields.js",
		"com.liferay.portlet.header-portal-javascript=/o/dynamic-data-mapping-web/js/main.js",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.header-portlet-javascript=/js/main.js",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.scopeable=true",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Dynamic Data Lists Display",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view_selected_record_set.jsp",
		"javax.portlet.name=" + DDLPortletKeys.DYNAMIC_DATA_LISTS_DISPLAY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class DDLDisplayPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			setDDLRecordRequestAttribute(renderRequest);

			setDDLRecordSetRequestAttribute(renderRequest);

			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				renderRequest);

			DDLDisplayContext ddlDisplayContext = new DDLDisplayContext(
				request, _ddl, _ddlRecordSetLocalService, _ddlWebConfiguration,
				_ddmDisplayRegistry, _ddmTemplateLocalService, _storageEngine);

			renderRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT, ddlDisplayContext);
		}
		catch (Exception e) {
			if (isSessionErrorException(e)) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}

				hideDefaultErrorMessage(renderRequest);

				SessionErrors.add(renderRequest, e.getClass());
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	@Reference(unbind = "-")
	public void setDDL(DDL ddl) {
		_ddl = ddl;
	}

	@Reference(unbind = "-")
	public void setDDLRecordService(DDLRecordService ddlRecordService) {
		_ddlRecordService = ddlRecordService;
	}

	@Reference(unbind = "-")
	public void setDDLRecordSetLocalService(
		DDLRecordSetLocalService ddlRecordSetLocalService) {

		_ddlRecordSetLocalService = ddlRecordSetLocalService;
	}

	@Reference(unbind = "-")
	public void setDDMDisplayRegistry(DDMDisplayRegistry ddmDisplayRegistry) {
		_ddmDisplayRegistry = ddmDisplayRegistry;
	}

	@Reference(unbind = "-")
	public void setDDMTemplateLocalService(
		DDMTemplateLocalService ddmTemplateLocalService) {

		_ddmTemplateLocalService = ddmTemplateLocalService;
	}

	@Reference(unbind = "-")
	public void setStorageEngine(StorageEngine storageEngine) {
		_storageEngine = storageEngine;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_ddlWebConfiguration = ConfigurableUtil.createConfigurable(
			DDLWebConfiguration.class, properties);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (SessionErrors.contains(
				renderRequest, NoSuchRecordSetException.class.getName()) ||
			SessionErrors.contains(
				renderRequest,
				PortletPreferencesException.MustBeStrict.class.getName()) ||
			SessionErrors.contains(
				renderRequest, PrincipalException.getNestedClasses())) {

			include(templatePath + "error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof NoSuchRecordSetException ||
			cause instanceof PortletPreferencesException ||
			cause instanceof PrincipalException) {

			return true;
		}

		return false;
	}

	protected void setDDLRecordRequestAttribute(RenderRequest renderRequest)
		throws PortalException {

		long recordId = ParamUtil.getLong(renderRequest, "recordId");

		DDLRecord record = null;

		if (recordId > 0) {
			record = _ddlRecordService.getRecord(recordId);
		}

		renderRequest.setAttribute(
			DDLWebKeys.DYNAMIC_DATA_LISTS_RECORD, record);
	}

	protected void setDDLRecordSetRequestAttribute(RenderRequest renderRequest)
		throws PortalException {

		long recordSetId = PrefsParamUtil.getLong(
			renderRequest.getPreferences(), renderRequest, "recordSetId");

		DDLRecordSet recordSet = null;

		if (recordSetId > 0) {
			recordSet = _ddlRecordSetService.getRecordSet(recordSetId);
		}

		renderRequest.setAttribute(
			DDLWebKeys.DYNAMIC_DATA_LISTS_RECORD_SET, recordSet);
	}

	@Reference(unbind = "-")
	protected void setDDLRecordSetService(
		DDLRecordSetService ddlRecordSetService) {

		_ddlRecordSetService = ddlRecordSetService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDLDisplayPortlet.class);

	private DDL _ddl;
	private DDLRecordService _ddlRecordService;
	private DDLRecordSetLocalService _ddlRecordSetLocalService;
	private DDLRecordSetService _ddlRecordSetService;
	private volatile DDLWebConfiguration _ddlWebConfiguration;
	private DDMDisplayRegistry _ddmDisplayRegistry;
	private DDMTemplateLocalService _ddmTemplateLocalService;
	private StorageEngine _storageEngine;

}