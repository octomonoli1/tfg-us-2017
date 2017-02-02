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

package com.liferay.product.navigation.control.menu.web.internal.portlet;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.product.navigation.control.menu.web.internal.constants.ProductNavigationControlMenuPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-control-menu",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.system=true",
		"com.liferay.portlet.use-default-template=false",
		"javax.portlet.display-name=Control Menu",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ProductNavigationControlMenuPortletKeys.PRODUCT_NAVIGATION_CONTROL_MENU,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class ProductNavigationControlMenuPortlet extends MVCPortlet {

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof SystemException ||
			super.isSessionErrorException(cause)) {

			return true;
		}

		return false;
	}

}