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

package com.liferay.portal.kernel.portlet.bridges.mvc.bundle.mvcactioncommand;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Manuel de la Peña
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + TestPortlet.PORTLET_NAME,
		"mvc.command.name=" + TestMVCActionCommand2.TEST_MVC_ACTION_COMMAND_NAME
	},
	service = MVCActionCommand.class
)
public class TestMVCActionCommand2 implements MVCActionCommand {

	public static final String TEST_MVC_ACTION_COMMAND_ATTRIBUTE =
		"TEST_MVC_ACTION_COMMAND_ATTRIBUTE";

	public static final String TEST_MVC_ACTION_COMMAND_NAME =
		"TEST_MVC_ACTION_COMMAND_NAME";

	@Override
	public boolean processAction(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		actionRequest.setAttribute(
			TEST_MVC_ACTION_COMMAND_ATTRIBUTE,
			TEST_MVC_ACTION_COMMAND_ATTRIBUTE);

		return true;
	}

}