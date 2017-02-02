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

package com.liferay.login.authentication.openid.web.internal.servlet.taglib;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.openid.OpenId;
import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * When Liferay's Sign In portlet is requested, this component checks if OpenID
 * authentication has been enabled for the portal instance being accessed and,
 * if so, adds an OpenID link to the Sign In portlet for triggering the
 * authentication process.
 *
 * @author Michael C. Han
 */
@Component(immediate = true, service = DynamicInclude.class)
public class OpenIdNavigationPreDynamicInclude extends BaseDynamicInclude {

	@Override
	public void include(
			HttpServletRequest request, HttpServletResponse response,
			String key)
		throws IOException {

		String mvcRenderCommandName = ParamUtil.getString(
			request, "mvcRenderCommandName");

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (mvcRenderCommandName.equals("/login/openid") ||
			!_openId.isEnabled(themeDisplay.getCompanyId())) {

			return;
		}

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher(_JSP_PATH);

		try {
			requestDispatcher.include(request, response);
		}
		catch (ServletException se) {
			_log.error("Unable to include JSP " + _JSP_PATH, se);

			throw new IOException("Unable to include JSP " + _JSP_PATH, se);
		}
	}

	@Override
	public void register(
		DynamicInclude.DynamicIncludeRegistry dynamicIncludeRegistry) {

		dynamicIncludeRegistry.register(
			"com.liferay.login.web#/navigation.jsp#pre");
	}

	@Reference(unbind = "-")
	protected void setOpenId(OpenId openId) {
		_openId = openId;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.login.authentication.openid.web)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private static final String _JSP_PATH =
		"/com.liferay.login.web/navigation/openid.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		OpenIdNavigationPreDynamicInclude.class);

	private OpenId _openId;
	private ServletContext _servletContext;

}