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

package com.liferay.portal.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for UserGroupGroupRole. This utility wraps
 * {@link com.liferay.portal.service.impl.UserGroupGroupRoleServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupGroupRoleService
 * @see com.liferay.portal.service.base.UserGroupGroupRoleServiceBaseImpl
 * @see com.liferay.portal.service.impl.UserGroupGroupRoleServiceImpl
 * @generated
 */
@ProviderType
public class UserGroupGroupRoleServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.UserGroupGroupRoleServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void addUserGroupGroupRoles(long userGroupId, long groupId,
		long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().addUserGroupGroupRoles(userGroupId, groupId, roleIds);
	}

	public static void addUserGroupGroupRoles(long[] userGroupIds,
		long groupId, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().addUserGroupGroupRoles(userGroupIds, groupId, roleId);
	}

	public static void deleteUserGroupGroupRoles(long userGroupId,
		long groupId, long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteUserGroupGroupRoles(userGroupId, groupId, roleIds);
	}

	public static void deleteUserGroupGroupRoles(long[] userGroupIds,
		long groupId, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteUserGroupGroupRoles(userGroupIds, groupId, roleId);
	}

	public static UserGroupGroupRoleService getService() {
		if (_service == null) {
			_service = (UserGroupGroupRoleService)PortalBeanLocatorUtil.locate(UserGroupGroupRoleService.class.getName());

			ReferenceRegistry.registerReference(UserGroupGroupRoleServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static UserGroupGroupRoleService _service;
}