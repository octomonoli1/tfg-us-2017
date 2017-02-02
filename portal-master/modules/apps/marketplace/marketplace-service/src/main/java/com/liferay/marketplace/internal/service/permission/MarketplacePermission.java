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

package com.liferay.marketplace.internal.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Ryan Park
 */
public class MarketplacePermission {

	public static void check(PermissionChecker permissionChecker)
		throws PortalException {

		if (!contains(permissionChecker)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(PermissionChecker permissionChecker)
		throws PortalException {

		if (!permissionChecker.isOmniadmin()) {
			return false;
		}

		return true;
	}

}