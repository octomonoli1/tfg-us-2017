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

/**
 * Provides a wrapper for {@link UserGroupService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupService
 * @generated
 */
@ProviderType
public class UserGroupServiceWrapper implements UserGroupService,
	ServiceWrapper<UserGroupService> {
	public UserGroupServiceWrapper(UserGroupService userGroupService) {
		_userGroupService = userGroupService;
	}

	/**
	* Adds a user group.
	*
	* <p>
	* This method handles the creation and bookkeeping of the user group,
	* including its resources, metadata, and internal data structures.
	* </p>
	*
	* @param name the user group's name
	* @param description the user group's description
	* @return the user group
	* @deprecated As of 6.2.0, replaced by {@link #addUserGroup(String, String,
	ServiceContext)}
	*/
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.UserGroup addUserGroup(
		java.lang.String name, java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userGroupService.addUserGroup(name, description);
	}

	/**
	* Adds a user group.
	*
	* <p>
	* This method handles the creation and bookkeeping of the user group,
	* including its resources, metadata, and internal data structures.
	* </p>
	*
	* @param name the user group's name
	* @param description the user group's description
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set expando bridge attributes for the
	user group.
	* @return the user group
	*/
	@Override
	public com.liferay.portal.kernel.model.UserGroup addUserGroup(
		java.lang.String name, java.lang.String description,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userGroupService.addUserGroup(name, description, serviceContext);
	}

	/**
	* Fetches the user group with the primary key.
	*
	* @param userGroupId the primary key of the user group
	* @return the user group with the primary key
	*/
	@Override
	public com.liferay.portal.kernel.model.UserGroup fetchUserGroup(
		long userGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userGroupService.fetchUserGroup(userGroupId);
	}

	/**
	* Returns the user group with the name.
	*
	* @param name the user group's name
	* @return the user group with the name
	*/
	@Override
	public com.liferay.portal.kernel.model.UserGroup getUserGroup(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userGroupService.getUserGroup(name);
	}

	/**
	* Returns the user group with the primary key.
	*
	* @param userGroupId the primary key of the user group
	* @return the user group with the primary key
	*/
	@Override
	public com.liferay.portal.kernel.model.UserGroup getUserGroup(
		long userGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userGroupService.getUserGroup(userGroupId);
	}

	/**
	* Updates the user group.
	*
	* @param userGroupId the primary key of the user group
	* @param name the user group's name
	* @param description the the user group's description
	* @return the user group
	* @deprecated As of 6.2.0, replaced by {@link #updateUserGroup(long,
	String, String, ServiceContext)}
	*/
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.UserGroup updateUserGroup(
		long userGroupId, java.lang.String name, java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userGroupService.updateUserGroup(userGroupId, name, description);
	}

	/**
	* Updates the user group.
	*
	* @param userGroupId the primary key of the user group
	* @param name the user group's name
	* @param description the the user group's description
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set expando bridge attributes for the
	user group.
	* @return the user group
	*/
	@Override
	public com.liferay.portal.kernel.model.UserGroup updateUserGroup(
		long userGroupId, java.lang.String name, java.lang.String description,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userGroupService.updateUserGroup(userGroupId, name,
			description, serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _userGroupService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.UserGroup> getUserGroups(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userGroupService.getUserGroups(companyId);
	}

	/**
	* Returns all the user groups to which the user belongs.
	*
	* @param userId the primary key of the user
	* @return the user groups to which the user belongs
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.UserGroup> getUserUserGroups(
		long userId) throws com.liferay.portal.kernel.exception.PortalException {
		return _userGroupService.getUserUserGroups(userId);
	}

	/**
	* Adds the user groups to the group.
	*
	* @param groupId the primary key of the group
	* @param userGroupIds the primary keys of the user groups
	*/
	@Override
	public void addGroupUserGroups(long groupId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_userGroupService.addGroupUserGroups(groupId, userGroupIds);
	}

	/**
	* Adds the user groups to the team
	*
	* @param teamId the primary key of the team
	* @param userGroupIds the primary keys of the user groups
	*/
	@Override
	public void addTeamUserGroups(long teamId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_userGroupService.addTeamUserGroups(teamId, userGroupIds);
	}

	/**
	* Deletes the user group.
	*
	* @param userGroupId the primary key of the user group
	*/
	@Override
	public void deleteUserGroup(long userGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_userGroupService.deleteUserGroup(userGroupId);
	}

	/**
	* Removes the user groups from the group.
	*
	* @param groupId the primary key of the group
	* @param userGroupIds the primary keys of the user groups
	*/
	@Override
	public void unsetGroupUserGroups(long groupId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_userGroupService.unsetGroupUserGroups(groupId, userGroupIds);
	}

	/**
	* Removes the user groups from the team.
	*
	* @param teamId the primary key of the team
	* @param userGroupIds the primary keys of the user groups
	*/
	@Override
	public void unsetTeamUserGroups(long teamId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_userGroupService.unsetTeamUserGroups(teamId, userGroupIds);
	}

	@Override
	public UserGroupService getWrappedService() {
		return _userGroupService;
	}

	@Override
	public void setWrappedService(UserGroupService userGroupService) {
		_userGroupService = userGroupService;
	}

	private UserGroupService _userGroupService;
}