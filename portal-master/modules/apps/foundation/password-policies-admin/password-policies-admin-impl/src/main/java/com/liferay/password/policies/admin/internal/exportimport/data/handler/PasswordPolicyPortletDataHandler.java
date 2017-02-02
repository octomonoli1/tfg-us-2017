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

package com.liferay.password.policies.admin.internal.exportimport.data.handler;

import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.DataLevel;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.password.policies.admin.constants.PasswordPoliciesAdminPortletKeys;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.PasswordPolicyLocalService;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniela Zapata Riesco
 */
@Component(
	property = {
		"javax.portlet.name=" + PasswordPoliciesAdminPortletKeys.PASSWORD_POLICIES_ADMIN
	},
	service = PortletDataHandler.class
)
public class PasswordPolicyPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "password_policies_admin";

	public static final String SCHEMA_VERSION = "1.0.0";

	@Override
	public String getSchemaVersion() {
		return SCHEMA_VERSION;
	}

	@Activate
	protected void activate() {
		setDataLevel(DataLevel.PORTAL);
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(PasswordPolicy.class));
		setExportControls(
			new PortletDataHandlerBoolean(
				NAMESPACE, "password-policies", true, true, null,
				PasswordPolicy.class.getName()));
		setSupportsDataStrategyCopyAsNew(false);
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletDataContext.addPrimaryKey(
				PasswordPolicyPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		_passwordPolicyLocalService.deleteNondefaultPasswordPolicies(
			portletDataContext.getCompanyId());

		return portletPreferences;
	}

	@Override
	protected String doExportData(
			final PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.addPortletPermissions(RESOURCE_NAME);

		Element rootElement = addExportDataRootElement(portletDataContext);

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		ActionableDynamicQuery actionableDynamicQuery =
			getPasswordPolicyActionableDynamicQuery(portletDataContext, true);

		actionableDynamicQuery.performActions();

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		portletDataContext.importPortletPermissions(RESOURCE_NAME);

		Element passwordPoliciesElement =
			portletDataContext.getImportDataGroupElement(PasswordPolicy.class);

		List<Element> passwordPolicyElements =
			passwordPoliciesElement.elements();

		for (Element passwordPolicyElement : passwordPolicyElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, passwordPolicyElement);
		}

		return null;
	}

	@Override
	protected void doPrepareManifestSummary(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws Exception {

		ActionableDynamicQuery actionableDynamicQuery =
			getPasswordPolicyActionableDynamicQuery(portletDataContext, false);

		actionableDynamicQuery.performCount();
	}

	protected ActionableDynamicQuery getPasswordPolicyActionableDynamicQuery(
		final PortletDataContext portletDataContext, final boolean export) {

		ActionableDynamicQuery actionableDynamicQuery =
			_passwordPolicyLocalService.getExportActionableDynamicQuery(
				portletDataContext);

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<PasswordPolicy>() {

				@Override
				public void performAction(PasswordPolicy passwordPolicy)
					throws PortalException {

					if (!export) {
						return;
					}

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, passwordPolicy);
				}

			});

		return actionableDynamicQuery;
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setPasswordPolicyLocalService(
		PasswordPolicyLocalService passwordPolicyLocalService) {

		_passwordPolicyLocalService = passwordPolicyLocalService;
	}

	protected static final String RESOURCE_NAME =
		"com.liferay.portlet.passwordpoliciesadmin";

	private PasswordPolicyLocalService _passwordPolicyLocalService;

}