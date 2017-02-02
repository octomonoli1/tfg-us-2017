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
 * Provides a wrapper for {@link GroupService}.
 *
 * @author Brian Wing Shun Chan
 * @see GroupService
 * @generated
 */
@ProviderType
public class GroupServiceWrapper implements GroupService,
	ServiceWrapper<GroupService> {
	public GroupServiceWrapper(GroupService groupService) {
		_groupService = groupService;
	}

	/**
	* Returns <code>true</code> if the user is associated with the group,
	* including the user's inherited organizations and user groups. System and
	* staged groups are not included.
	*
	* @param userId the primary key of the user
	* @param groupId the primary key of the group
	* @return <code>true</code> if the user is associated with the group;
	<code>false</code> otherwise
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public boolean hasUserGroup(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.hasUserGroup(userId, groupId);
	}

	/**
	* Adds a group.
	*
	* @param parentGroupId the primary key of the parent group
	* @param liveGroupId the primary key of the live group
	* @param name the entity's name
	* @param description the group's description (optionally
	<code>null</code>)
	* @param type the group's type. For more information see {@link
	GroupConstants}.
	* @param manualMembership whether manual membership is allowed for the
	group
	* @param membershipRestriction the group's membership restriction. For
	more information see {@link GroupConstants}.
	* @param friendlyURL the group's friendlyURL (optionally
	<code>null</code>)
	* @param site whether the group is to be associated with a main site
	* @param active whether the group is active
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set the asset category IDs and asset
	tag names for the group, and can set whether the group is for
	staging
	* @return the group
	* @throws PortalException if a portal exception occurred
	* @deprecated As of 7.0.0, replaced by {@link #addGroup(long, long, Map,
	Map, int, boolean, int, String, boolean, boolean,
	ServiceContext)}
	*/
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.Group addGroup(long parentGroupId,
		long liveGroupId, java.lang.String name, java.lang.String description,
		int type, boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean site, boolean active,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.addGroup(parentGroupId, liveGroupId, name,
			description, type, manualMembership, membershipRestriction,
			friendlyURL, site, active, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.model.Group addGroup(long parentGroupId,
		long liveGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int type, boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean site, boolean active,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.addGroup(parentGroupId, liveGroupId, nameMap,
			descriptionMap, type, manualMembership, membershipRestriction,
			friendlyURL, site, active, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.model.Group addGroup(long parentGroupId,
		long liveGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int type, boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean site, boolean inheritContent,
		boolean active, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.addGroup(parentGroupId, liveGroupId, nameMap,
			descriptionMap, type, manualMembership, membershipRestriction,
			friendlyURL, site, inheritContent, active, serviceContext);
	}

	/**
	* Returns the company group.
	*
	* @param companyId the primary key of the company
	* @return the group associated with the company
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.model.Group getCompanyGroup(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getCompanyGroup(companyId);
	}

	/**
	* Returns the group with the name.
	*
	* @param companyId the primary key of the company
	* @param groupKey the group key
	* @return the group with the group key
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.model.Group getGroup(long companyId,
		java.lang.String groupKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getGroup(companyId, groupKey);
	}

	/**
	* Returns the group with the primary key.
	*
	* @param groupId the primary key of the group
	* @return the group with the primary key
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.model.Group getGroup(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getGroup(groupId);
	}

	/**
	* Returns the group directly associated with the user.
	*
	* @param companyId the primary key of the company
	* @param userId the primary key of the user
	* @return the group directly associated with the user
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.model.Group getUserGroup(long companyId,
		long userId) throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getUserGroup(companyId, userId);
	}

	/**
	* Updates the group's friendly URL.
	*
	* @param groupId the primary key of the group
	* @param friendlyURL the group's new friendlyURL (optionally
	<code>null</code>)
	* @return the group
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.model.Group updateFriendlyURL(
		long groupId, java.lang.String friendlyURL)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.updateFriendlyURL(groupId, friendlyURL);
	}

	/**
	* Updates the group's type settings.
	*
	* @param groupId the primary key of the group
	* @param typeSettings the group's new type settings (optionally
	<code>null</code>)
	* @return the group
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public com.liferay.portal.kernel.model.Group updateGroup(long groupId,
		java.lang.String typeSettings)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.updateGroup(groupId, typeSettings);
	}

	/**
	* Updates the group.
	*
	* @param groupId the primary key of the group
	* @param parentGroupId the primary key of the parent group
	* @param name the group's name
	* @param description the group's new description (optionally
	<code>null</code>)
	* @param type the group's new type. For more information see {@link
	GroupConstants}.
	* @param manualMembership whether manual membership is allowed for the
	group
	* @param membershipRestriction the group's membership restriction. For
	more information see {@link GroupConstants}.
	* @param friendlyURL the group's new friendlyURL (optionally
	<code>null</code>)
	* @param inheritContent whether to inherit content from the parent
	group
	* @param active whether the group is active
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set the asset category IDs and asset
	tag names for the group.
	* @return the group
	* @throws PortalException if a portal exception occurred
	* @deprecated As of 7.0.0, replaced by {@link #updateGroup(long, long, Map,
	Map, int, boolean, int, String, boolean, boolean,
	ServiceContext)}
	*/
	@Deprecated
	@Override
	public com.liferay.portal.kernel.model.Group updateGroup(long groupId,
		long parentGroupId, java.lang.String name,
		java.lang.String description, int type, boolean manualMembership,
		int membershipRestriction, java.lang.String friendlyURL,
		boolean inheritContent, boolean active, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.updateGroup(groupId, parentGroupId, name,
			description, type, manualMembership, membershipRestriction,
			friendlyURL, inheritContent, active, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.model.Group updateGroup(long groupId,
		long parentGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int type, boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean inheritContent, boolean active,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.updateGroup(groupId, parentGroupId, nameMap,
			descriptionMap, type, manualMembership, membershipRestriction,
			friendlyURL, inheritContent, active, serviceContext);
	}

	/**
	* Returns the number of the guest or current user's groups
	* &quot;sites&quot; associated with the group entity class names, including
	* the Control Panel group if the user is permitted to view the Control
	* Panel.
	*
	* @return the number of user's groups &quot;sites&quot;
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public int getUserSitesGroupsCount()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getUserSitesGroupsCount();
	}

	/**
	* Returns the number of groups and organization groups that match the name
	* and description, optionally including the user's inherited organizations
	* and user groups. System and staged groups are not included.
	*
	* @param companyId the primary key of the company
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @return the number of matching groups
	*/
	@Override
	public int searchCount(long companyId, java.lang.String name,
		java.lang.String description, java.lang.String[] params) {
		return _groupService.searchCount(companyId, name, description, params);
	}

	/**
	* Returns the group's display URL.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout set is private to the group
	* @param secureConnection whether the generated URL uses a secure
	connection
	* @return the group's display URL
	* @throws PortalException if a group with the primary key could not be
	found or if a portal exception occurred
	*/
	@Override
	public java.lang.String getGroupDisplayURL(long groupId,
		boolean privateLayout, boolean secureConnection)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getGroupDisplayURL(groupId, privateLayout,
			secureConnection);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _groupService.getOSGiServiceIdentifier();
	}

	/**
	* Returns all the groups that are direct children of the parent group.
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param site whether the group is to be associated with a main site
	* @return the matching groups, or <code>null</code> if no matches were
	found
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> getGroups(
		long companyId, long parentGroupId, boolean site)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getGroups(companyId, parentGroupId, site);
	}

	/**
	* Returns a range of all the site groups for which the user has control
	* panel access.
	*
	* @param portlets the portlets to manage
	* @param max the upper bound of the range of groups to consider (not
	inclusive)
	* @return the range of site groups for which the user has Control Panel
	access
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> getManageableSiteGroups(
		java.util.Collection<com.liferay.portal.kernel.model.Portlet> portlets,
		int max) throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getManageableSiteGroups(portlets, max);
	}

	/**
	* Returns the groups associated with the organizations.
	*
	* @param organizations the organizations
	* @return the groups associated with the organizations
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> getOrganizationsGroups(
		java.util.List<com.liferay.portal.kernel.model.Organization> organizations)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getOrganizationsGroups(organizations);
	}

	/**
	* Returns the groups associated with the user groups.
	*
	* @param userGroups the user groups
	* @return the groups associated with the user groups
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> getUserGroupsGroups(
		java.util.List<com.liferay.portal.kernel.model.UserGroup> userGroups)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getUserGroupsGroups(userGroups);
	}

	/**
	* Returns the range of all groups associated with the user's organization
	* groups, including the ancestors of the organization groups, unless portal
	* property <code>organizations.membership.strict</code> is set to
	* <code>true</code>.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param userId the primary key of the user
	* @param start the lower bound of the range of groups to consider
	* @param end the upper bound of the range of groups to consider (not
	inclusive)
	* @return the range of groups associated with the user's organizations
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> getUserOrganizationsGroups(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getUserOrganizationsGroups(userId, start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> getUserSitesGroups()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getUserSitesGroups();
	}

	/**
	* Returns the guest or current user's groups &quot;sites&quot; associated
	* with the group entity class names, including the Control Panel group if
	* the user is permitted to view the Control Panel.
	*
	* <ul>
	* <li>
	* Class name &quot;User&quot; includes the user's layout set
	* group.
	* </li>
	* <li>
	* Class name &quot;Organization&quot; includes the user's
	* immediate organization groups and inherited organization groups.
	* </li>
	* <li>
	* Class name &quot;Group&quot; includes the user's immediate
	* organization groups and site groups.
	* </li>
	* <li>
	* A <code>classNames</code>
	* value of <code>null</code> includes the user's layout set group,
	* organization groups, inherited organization groups, and site groups.
	* </li>
	* </ul>
	*
	* @param classNames the group entity class names (optionally
	<code>null</code>). For more information see {@link
	#getUserSitesGroups(long, String[], int)}.
	* @param max the maximum number of groups to return
	* @return the user's groups &quot;sites&quot;
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> getUserSitesGroups(
		java.lang.String[] classNames, int max)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getUserSitesGroups(classNames, max);
	}

	/**
	* Returns the user's groups &quot;sites&quot; associated with the group
	* entity class names, including the Control Panel group if the user is
	* permitted to view the Control Panel.
	*
	* <ul>
	* <li>
	* Class name &quot;User&quot; includes the user's layout set
	* group.
	* </li>
	* <li>
	* Class name &quot;Organization&quot; includes the user's
	* immediate organization groups and inherited organization groups.
	* </li>
	* <li>
	* Class name &quot;Group&quot; includes the user's immediate
	* organization groups and site groups.
	* </li>
	* <li>
	* A <code>classNames</code>
	* value of <code>null</code> includes the user's layout set group,
	* organization groups, inherited organization groups, and site groups.
	* </li>
	* </ul>
	*
	* @param userId the primary key of the user
	* @param classNames the group entity class names (optionally
	<code>null</code>). For more information see {@link
	#getUserSitesGroups(long, String[], int)}.
	* @param max the maximum number of groups to return
	* @return the user's groups &quot;sites&quot;
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> getUserSitesGroups(
		long userId, java.lang.String[] classNames, int max)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.getUserSitesGroups(userId, classNames, max);
	}

	/**
	* Returns an ordered range of all the site groups and organization groups
	* that match the name and description, optionally including the user's
	* inherited organization groups and user groups. System and staged groups
	* are not included.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, java.lang.String name, java.lang.String description,
		java.lang.String[] params, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.search(companyId, name, description, params,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long[] classNameIds, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.search(companyId, classNameIds, keywords, params,
			start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long[] classNameIds, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _groupService.search(companyId, classNameIds, name, description,
			params, andOperator, start, end, obc);
	}

	/**
	* Adds the groups to the role.
	*
	* @param roleId the primary key of the role
	* @param groupIds the primary keys of the groups
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public void addRoleGroups(long roleId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_groupService.addRoleGroups(roleId, groupIds);
	}

	/**
	* Checks that the current user is permitted to use the group for Remote
	* Staging.
	*
	* @param groupId the primary key of the group
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public void checkRemoteStagingGroup(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_groupService.checkRemoteStagingGroup(groupId);
	}

	/**
	* Deletes the group.
	*
	* <p>
	* The group is unstaged and its assets and resources including layouts,
	* membership requests, subscriptions, teams, blogs, bookmarks, calendar
	* events, image gallery, journals, message boards, polls, shopping related
	* entities, and wikis are also deleted.
	* </p>
	*
	* @param groupId the primary key of the group
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public void deleteGroup(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_groupService.deleteGroup(groupId);
	}

	@Override
	public void disableStaging(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_groupService.disableStaging(groupId);
	}

	@Override
	public void enableStaging(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_groupService.enableStaging(groupId);
	}

	/**
	* Sets the groups associated with the role, removing and adding
	* associations as necessary.
	*
	* @param roleId the primary key of the role
	* @param groupIds the primary keys of the groups
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public void setRoleGroups(long roleId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_groupService.setRoleGroups(roleId, groupIds);
	}

	/**
	* Removes the groups from the role.
	*
	* @param roleId the primary key of the role
	* @param groupIds the primary keys of the groups
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public void unsetRoleGroups(long roleId, long[] groupIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_groupService.unsetRoleGroups(roleId, groupIds);
	}

	@Override
	public void updateStagedPortlets(long groupId,
		java.util.Map<java.lang.String, java.lang.String> stagedPortletIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_groupService.updateStagedPortlets(groupId, stagedPortletIds);
	}

	@Override
	public GroupService getWrappedService() {
		return _groupService;
	}

	@Override
	public void setWrappedService(GroupService groupService) {
		_groupService = groupService;
	}

	private GroupService _groupService;
}