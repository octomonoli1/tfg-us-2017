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

package com.liferay.portal.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.LayoutSetBranchLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.LayoutSetBranchPermission;

/**
 * @author Brian Wing Shun Chan
 */
public class LayoutSetBranchPermissionImpl
	implements LayoutSetBranchPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			LayoutSetBranch layoutSetBranch, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, layoutSetBranch, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, LayoutSetBranch.class.getName(),
				layoutSetBranch.getLayoutSetBranchId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long layoutSetBranchId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, layoutSetBranchId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, LayoutSetBranch.class.getName(),
				layoutSetBranchId, actionId);
		}
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, LayoutSetBranch layoutSetBranch,
		String actionId) {

		return permissionChecker.hasPermission(
			layoutSetBranch.getGroupId(), LayoutSetBranch.class.getName(),
			layoutSetBranch.getLayoutSetBranchId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long layoutSetBranchId,
			String actionId)
		throws PortalException {

		LayoutSetBranch layoutSetBranch =
			LayoutSetBranchLocalServiceUtil.getLayoutSetBranch(
				layoutSetBranchId);

		return contains(permissionChecker, layoutSetBranch, actionId);
	}

}