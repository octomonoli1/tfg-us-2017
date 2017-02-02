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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletApp;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.service.base.PortletServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class PortletServiceImpl extends PortletServiceBaseImpl {

	@Override
	public JSONArray getWARPortlets() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<Portlet> portlets = portletLocalService.getPortlets();

		for (Portlet portlet : portlets) {
			PortletApp portletApp = portlet.getPortletApp();

			if (portletApp.isWARFile()) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("portlet_name", portlet.getPortletName());
				jsonObject.put(
					"servlet_context_name", portletApp.getServletContextName());

				jsonArray.put(jsonObject);
			}
		}

		return jsonArray;
	}

	@Override
	public Portlet updatePortlet(
			long companyId, String portletId, String roles, boolean active)
		throws PortalException {

		if (!roleLocalService.hasUserRole(
				getUserId(), companyId, RoleConstants.ADMINISTRATOR, true)) {

			throw new PrincipalException();
		}

		return portletLocalService.updatePortlet(
			companyId, portletId, roles, active);
	}

}