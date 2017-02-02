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

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class ClearRenderParametersAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) {

		// Some users are confused by the behavior stated in the JSR 168 spec
		// that render parameters are saved across requests. Set this class to
		// always clear render parameters to please those users. You can also
		// modify the "layout.remember.request.window.state.maximized" property
		// in portal.properties to disable the remembering of window states
		// across requests.

		HttpSession session = request.getSession();

		Map<Long, Map<String, Map<String, String[]>>> renderParametersPool =
			(Map<Long, Map<String, Map<String, String[]>>>)session.getAttribute(
				WebKeys.PORTLET_RENDER_PARAMETERS);

		if (renderParametersPool != null) {
			renderParametersPool.clear();
		}
	}

}