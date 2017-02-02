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
 * Provides the remote service utility for UserGroupRole. This utility wraps
 * {@link com.liferay.portal.service.impl.UserGroupRoleServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupRoleService
 * @see com.liferay.portal.service.base.UserGroupRoleServiceBaseImpl
 * @see com.liferay.portal.service.impl.UserGroupRoleServiceImpl
 * @generated
 */
@ProviderType
public class UserGroupRoleServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.UserGroupRoleServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void addUserGroupRoles(long userId, long groupId,
		long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().addUserGroupRoles(userId, groupId, roleIds);
	}

	public static void addUserGroupRoles(long[] userIds, long groupId,
		long roleId) throws com.liferay.portal.kernel.exception.PortalException {
		getService().addUserGroupRoles(userIds, groupId, roleId);
	}

	public static void deleteUserGroupRoles(long userId, long groupId,
		long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteUserGroupRoles(userId, groupId, roleIds);
	}

	public static void deleteUserGroupRoles(long[] userIds, long groupId,
		long roleId) throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteUserGroupRoles(userIds, groupId, roleId);
	}

	public static void updateUserGroupRoles(long userId, long groupId,
		long[] addedRoleIds, long[] deletedRoleIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateUserGroupRoles(userId, groupId, addedRoleIds, deletedRoleIds);
	}

	public static UserGroupRoleService getService() {
		if (_service == null) {
			_service = (UserGroupRoleService)PortalBeanLocatorUtil.locate(UserGroupRoleService.class.getName());

			ReferenceRegistry.registerReference(UserGroupRoleServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static UserGroupRoleService _service;
}