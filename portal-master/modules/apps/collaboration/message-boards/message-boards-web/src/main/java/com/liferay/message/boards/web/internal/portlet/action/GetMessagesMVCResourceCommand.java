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

package com.liferay.message.boards.web.internal.portlet.action;

import com.liferay.message.boards.kernel.model.MBMessageDisplay;
import com.liferay.message.boards.web.constants.MBPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sergio González
 */
@Component(
	property = {
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS,
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS_ADMIN,
		"mvc.command.name=/message_boards/get_messages"
	},
	service = MVCResourceCommand.class
)
public class GetMessagesMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		MBMessageDisplay messageDisplay = ActionUtil.getMessageDisplay(
			resourceRequest);

		resourceRequest.setAttribute(
			WebKeys.MESSAGE_BOARDS_MESSAGE_DISPLAY, messageDisplay);

		int index = ParamUtil.getInteger(resourceRequest, "index");

		resourceRequest.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_INDEX, index);

		PortletRequestDispatcher portletRequestDispatcher =
			getPortletRequestDispatcher(
				resourceRequest,
				"/message_boards/view_message_content_resources.jsp");

		portletRequestDispatcher.include(resourceRequest, resourceResponse);
	}

}