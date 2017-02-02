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

package com.liferay.portal.fabric.server;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.concurrent.Future;

/**
 * @author Shuyang Zhou
 */
public class FabricServerUtil {

	public static FabricServer getFabricServer() {
		PortalRuntimePermission.checkGetBeanProperty(FabricServerUtil.class);

		return _fabricServer;
	}

	public static void start() throws Exception {
		getFabricServer().start();
	}

	public static Future<?> stop() throws Exception {
		return getFabricServer().stop();
	}

	public void setFabricServer(FabricServer fabricServer) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_fabricServer = fabricServer;
	}

	private static FabricServer _fabricServer;

}