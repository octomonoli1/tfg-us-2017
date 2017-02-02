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
 * Provides the local service utility for Group. This utility wraps
 * {@link com.liferay.portal.service.impl.GroupLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see GroupLocalService
 * @see com.liferay.portal.service.base.GroupLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.GroupLocalServiceImpl
 * @generated
 */
@ProviderType
public class GroupLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.GroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasOrganizationGroup(long organizationId, long groupId) {
		return getService().hasOrganizationGroup(organizationId, groupId);
	}

	public static boolean hasOrganizationGroups(long organizationId) {
		return getService().hasOrganizationGroups(organizationId);
	}

	public static boolean hasRoleGroup(long roleId, long groupId) {
		return getService().hasRoleGroup(roleId, groupId);
	}

	public static boolean hasRoleGroups(long roleId) {
		return getService().hasRoleGroups(roleId);
	}

	/**
	* Returns <code>true</code> if the live group has a staging group.
	*
	* @param liveGroupId the primary key of the live group
	* @return <code>true</code> if the live group has a staging group;
	<code>false</code> otherwise
	*/
	public static boolean hasStagingGroup(long liveGroupId) {
		return getService().hasStagingGroup(liveGroupId);
	}

	public static boolean hasUserGroup(long userId, long groupId) {
		return getService().hasUserGroup(userId, groupId);
	}

	/**
	* Returns <code>true</code> if the user is immediately associated with the
	* group, or optionally if the user is associated with the group via the
	* user's organizations, inherited organizations, or user groups.
	*
	* @param userId the primary key of the user
	* @param groupId the primary key of the group
	* @param inherit whether to include organization groups and user groups to
	which the user belongs in the determination
	* @return <code>true</code> if the user is associated with the group;
	<code>false</code> otherwise
	*/
	public static boolean hasUserGroup(long userId, long groupId,
		boolean inherit) {
		return getService().hasUserGroup(userId, groupId, inherit);
	}

	public static boolean hasUserGroupGroup(long userGroupId, long groupId) {
		return getService().hasUserGroupGroup(userGroupId, groupId);
	}

	public static boolean hasUserGroupGroups(long userGroupId) {
		return getService().hasUserGroupGroups(userGroupId);
	}

	public static boolean hasUserGroups(long userId) {
		return getService().hasUserGroups(userId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Adds the group to the database. Also notifies the appropriate model listeners.
	*
	* @param group the group
	* @return the group that was added
	*/
	public static com.liferay.portal.kernel.model.Group addGroup(
		com.liferay.portal.kernel.model.Group group) {
		return getService().addGroup(group);
	}

	/**
	* Adds a group.
	*
	* @param userId the primary key of the group's creator/owner
	* @param parentGroupId the primary key of the parent group
	* @param className the entity's class name
	* @param classPK the primary key of the entity's instance
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
	<code>null</code>). Can set asset category IDs and asset tag
	names for the group, and whether the group is for staging.
	* @return the group
	* @throws PortalException if a portal exception occured
	* @deprecated As of 7.0.0, replaced by {@link #addGroup(long, long, String,
	long, long, Map, Map, int, boolean, int, String, boolean,
	boolean, ServiceContext)}
	*/
	@Deprecated
	public static com.liferay.portal.kernel.model.Group addGroup(long userId,
		long parentGroupId, java.lang.String className, long classPK,
		long liveGroupId, java.lang.String name, java.lang.String description,
		int type, boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean site, boolean active,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addGroup(userId, parentGroupId, className, classPK,
			liveGroupId, name, description, type, manualMembership,
			membershipRestriction, friendlyURL, site, active, serviceContext);
	}

	public static com.liferay.portal.kernel.model.Group addGroup(long userId,
		long parentGroupId, java.lang.String className, long classPK,
		long liveGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int type, boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean site, boolean active,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addGroup(userId, parentGroupId, className, classPK,
			liveGroupId, nameMap, descriptionMap, type, manualMembership,
			membershipRestriction, friendlyURL, site, active, serviceContext);
	}

	public static com.liferay.portal.kernel.model.Group addGroup(long userId,
		long parentGroupId, java.lang.String className, long classPK,
		long liveGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int type, boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean site, boolean inheritContent,
		boolean active, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addGroup(userId, parentGroupId, className, classPK,
			liveGroupId, nameMap, descriptionMap, type, manualMembership,
			membershipRestriction, friendlyURL, site, inheritContent, active,
			serviceContext);
	}

	public static com.liferay.portal.kernel.model.Group checkScopeGroup(
		com.liferay.portal.kernel.model.Layout layout, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().checkScopeGroup(layout, userId);
	}

	/**
	* Creates a new group with the primary key. Does not add the group to the database.
	*
	* @param groupId the primary key for the new group
	* @return the new group
	*/
	public static com.liferay.portal.kernel.model.Group createGroup(
		long groupId) {
		return getService().createGroup(groupId);
	}

	/**
	* Deletes the group from the database. Also notifies the appropriate model listeners.
	*
	* @param group the group
	* @return the group that was removed
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.Group deleteGroup(
		com.liferay.portal.kernel.model.Group group)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteGroup(group);
	}

	/**
	* Deletes the group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param groupId the primary key of the group
	* @return the group that was removed
	* @throws PortalException if a group with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.model.Group deleteGroup(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteGroup(groupId);
	}

	/**
	* Returns the company's group.
	*
	* @param companyId the primary key of the company
	* @return the company's group, or <code>null</code> if a matching group
	could not be found
	*/
	public static com.liferay.portal.kernel.model.Group fetchCompanyGroup(
		long companyId) {
		return getService().fetchCompanyGroup(companyId);
	}

	/**
	* Returns the group with the matching friendly URL.
	*
	* @param companyId the primary key of the company
	* @param friendlyURL the friendly URL
	* @return the group with the friendly URL, or <code>null</code> if a
	matching group could not be found
	*/
	public static com.liferay.portal.kernel.model.Group fetchFriendlyURLGroup(
		long companyId, java.lang.String friendlyURL) {
		return getService().fetchFriendlyURLGroup(companyId, friendlyURL);
	}

	/**
	* Returns the group with the matching group key by first searching the
	* system groups and then using the finder cache.
	*
	* @param companyId the primary key of the company
	* @param groupKey the group key
	* @return the group with the group key and associated company, or
	<code>null</code> if a matching group could not be found
	*/
	public static com.liferay.portal.kernel.model.Group fetchGroup(
		long companyId, java.lang.String groupKey) {
		return getService().fetchGroup(companyId, groupKey);
	}

	public static com.liferay.portal.kernel.model.Group fetchGroup(long groupId) {
		return getService().fetchGroup(groupId);
	}

	/**
	* Returns the group with the matching UUID and company.
	*
	* @param uuid the group's UUID
	* @param companyId the primary key of the company
	* @return the matching group, or <code>null</code> if a matching group could not be found
	*/
	public static com.liferay.portal.kernel.model.Group fetchGroupByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().fetchGroupByUuidAndCompanyId(uuid, companyId);
	}

	public static com.liferay.portal.kernel.model.Group fetchUserGroup(
		long companyId, long userId) {
		return getService().fetchUserGroup(companyId, userId);
	}

	/**
	* Returns the default user's personal site group.
	*
	* @param companyId the primary key of the company
	* @return the default user's personal site group, or <code>null</code> if a
	matching group could not be found
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group fetchUserPersonalSiteGroup(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchUserPersonalSiteGroup(companyId);
	}

	/**
	* Returns the company group.
	*
	* @param companyId the primary key of the company
	* @return the group associated with the company
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group getCompanyGroup(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCompanyGroup(companyId);
	}

	/**
	* Returns the group with the matching friendly URL.
	*
	* @param companyId the primary key of the company
	* @param friendlyURL the group's friendlyURL
	* @return the group with the friendly URL
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group getFriendlyURLGroup(
		long companyId, java.lang.String friendlyURL)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFriendlyURLGroup(companyId, friendlyURL);
	}

	/**
	* Returns the group with the matching group key.
	*
	* @param companyId the primary key of the company
	* @param groupKey the group key
	* @return the group with the group key
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group getGroup(
		long companyId, java.lang.String groupKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGroup(companyId, groupKey);
	}

	/**
	* Returns the group with the primary key.
	*
	* @param groupId the primary key of the group
	* @return the group
	* @throws PortalException if a group with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.model.Group getGroup(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGroup(groupId);
	}

	/**
	* Returns the group with the matching UUID and company.
	*
	* @param uuid the group's UUID
	* @param companyId the primary key of the company
	* @return the matching group
	* @throws PortalException if a matching group could not be found
	*/
	public static com.liferay.portal.kernel.model.Group getGroupByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGroupByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the group associated with the layout.
	*
	* @param companyId the primary key of the company
	* @param plid the primary key of the layout
	* @return the group associated with the layout
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group getLayoutGroup(
		long companyId, long plid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLayoutGroup(companyId, plid);
	}

	/**
	* Returns the group associated with the layout prototype.
	*
	* @param companyId the primary key of the company
	* @param layoutPrototypeId the primary key of the layout prototype
	* @return the group associated with the layout prototype
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group getLayoutPrototypeGroup(
		long companyId, long layoutPrototypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLayoutPrototypeGroup(companyId, layoutPrototypeId);
	}

	/**
	* Returns the group associated with the layout set prototype.
	*
	* @param companyId the primary key of the company
	* @param layoutSetPrototypeId the primary key of the layout set prototype
	* @return the group associated with the layout set prototype
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group getLayoutSetPrototypeGroup(
		long companyId, long layoutSetPrototypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLayoutSetPrototypeGroup(companyId, layoutSetPrototypeId);
	}

	/**
	* Returns the specified organization group.
	*
	* @param companyId the primary key of the company
	* @param organizationId the primary key of the organization
	* @return the group associated with the organization
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group getOrganizationGroup(
		long companyId, long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOrganizationGroup(companyId, organizationId);
	}

	/**
	* Returns the staging group.
	*
	* @param liveGroupId the primary key of the live group
	* @return the staging group
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group getStagingGroup(
		long liveGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStagingGroup(liveGroupId);
	}

	/**
	* Returns the group directly associated with the user.
	*
	* @param companyId the primary key of the company
	* @param userId the primary key of the user
	* @return the group directly associated with the user
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group getUserGroup(
		long companyId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserGroup(companyId, userId);
	}

	/**
	* Returns the specified "user group" group. That is, the group that
	* represents the {@link UserGroup} entity.
	*
	* @param companyId the primary key of the company
	* @param userGroupId the primary key of the user group
	* @return the group associated with the user group
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group getUserGroupGroup(
		long companyId, long userGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserGroupGroup(companyId, userGroupId);
	}

	/**
	* Returns the default user's personal site group.
	*
	* @param companyId the primary key of the company
	* @return the default user's personal site group
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group getUserPersonalSiteGroup(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserPersonalSiteGroup(companyId);
	}

	/**
	* Returns the group with the matching group key by first searching the
	* system groups and then using the finder cache.
	*
	* @param companyId the primary key of the company
	* @param groupKey the group key
	* @return the group with the group key and associated company, or
	<code>null</code> if a matching group could not be found
	*/
	public static com.liferay.portal.kernel.model.Group loadFetchGroup(
		long companyId, java.lang.String groupKey) {
		return getService().loadFetchGroup(companyId, groupKey);
	}

	/**
	* Returns the group with the matching group key.
	*
	* @param companyId the primary key of the company
	* @param groupKey the group key
	* @return the group with the group key and associated company
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group loadGetGroup(
		long companyId, java.lang.String groupKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().loadGetGroup(companyId, groupKey);
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
	public static com.liferay.portal.kernel.model.Group updateFriendlyURL(
		long groupId, java.lang.String friendlyURL)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateFriendlyURL(groupId, friendlyURL);
	}

	/**
	* Updates the group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param group the group
	* @return the group that was updated
	*/
	public static com.liferay.portal.kernel.model.Group updateGroup(
		com.liferay.portal.kernel.model.Group group) {
		return getService().updateGroup(group);
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
	public static com.liferay.portal.kernel.model.Group updateGroup(
		long groupId, java.lang.String typeSettings)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateGroup(groupId, typeSettings);
	}

	/**
	* Updates the group.
	*
	* @param groupId the primary key of the group
	* @param parentGroupId the primary key of the parent group
	* @param name the name's key
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
	<code>null</code>). Can set asset category IDs and asset tag
	names for the group.
	* @return the group
	* @throws PortalException if a portal exception occurred
	* @deprecated As of 7.0.0, replaced by {@link #updateGroup(long, long, Map,
	Map, int, boolean, int, String, boolean, boolean,
	ServiceContext)}
	*/
	@Deprecated
	public static com.liferay.portal.kernel.model.Group updateGroup(
		long groupId, long parentGroupId, java.lang.String name,
		java.lang.String description, int type, boolean manualMembership,
		int membershipRestriction, java.lang.String friendlyURL,
		boolean inheritContent, boolean active, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateGroup(groupId, parentGroupId, name, description,
			type, manualMembership, membershipRestriction, friendlyURL,
			inheritContent, active, serviceContext);
	}

	public static com.liferay.portal.kernel.model.Group updateGroup(
		long groupId, long parentGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int type, boolean manualMembership, int membershipRestriction,
		java.lang.String friendlyURL, boolean inheritContent, boolean active,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateGroup(groupId, parentGroupId, nameMap,
			descriptionMap, type, manualMembership, membershipRestriction,
			friendlyURL, inheritContent, active, serviceContext);
	}

	/**
	* Associates the group with a main site if the group is an organization.
	*
	* @param groupId the primary key of the group
	* @param site whether the group is to be associated with a main site
	* @return the group
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.portal.kernel.model.Group updateSite(
		long groupId, boolean site)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateSite(groupId, site);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of groups associated with the company.
	*
	* @param companyId the primary key of the company
	* @return the number of groups associated with the company
	*/
	public static int getCompanyGroupsCount(long companyId) {
		return getService().getCompanyGroupsCount(companyId);
	}

	/**
	* Returns the number of groups.
	*
	* @return the number of groups
	*/
	public static int getGroupsCount() {
		return getService().getGroupsCount();
	}

	/**
	* Returns the number of groups that are direct children of the parent group
	* with the matching className.
	*
	* @param companyId the primary key of the company
	* @param className the class name of the group
	* @param parentGroupId the primary key of the parent group
	* @return the number of matching groups
	*/
	public static int getGroupsCount(long companyId,
		java.lang.String className, long parentGroupId) {
		return getService().getGroupsCount(companyId, className, parentGroupId);
	}

	/**
	* Returns the number of groups that are direct children of the parent
	* group.
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param site whether the group is to be associated with a main site
	* @return the number of matching groups
	*/
	public static int getGroupsCount(long companyId, long parentGroupId,
		boolean site) {
		return getService().getGroupsCount(companyId, parentGroupId, site);
	}

	/**
	* Returns the number of groups that are children or the parent group and
	* that have at least one layout
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param site whether the group is to be associated with a main site
	* @return the number of matching groups
	*/
	public static int getLayoutsGroupsCount(long companyId, long parentGroupId,
		boolean site) {
		return getService().getLayoutsGroupsCount(companyId, parentGroupId, site);
	}

	public static int getOrganizationGroupsCount(long organizationId) {
		return getService().getOrganizationGroupsCount(organizationId);
	}

	public static int getRoleGroupsCount(long roleId) {
		return getService().getRoleGroupsCount(roleId);
	}

	public static int getUserGroupGroupsCount(long userGroupId) {
		return getService().getUserGroupGroupsCount(userGroupId);
	}

	public static int getUserGroupsCount(long userId) {
		return getService().getUserGroupsCount(userId);
	}

	/**
	* Returns the number of groups that match the keywords, optionally
	* including the user's inherited organization groups and user groups.
	* System and staged groups are not included.
	*
	* @param companyId the primary key of the company
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @return the number of matching groups
	*/
	public static int searchCount(long companyId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getService().searchCount(companyId, keywords, params);
	}

	/**
	* Returns the number of groups and immediate organization groups that match
	* the name and description, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
	*
	* @param companyId the primary key of the company
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @return the number of matching groups
	*/
	public static int searchCount(long companyId, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return getService()
				   .searchCount(companyId, name, description, params,
			andOperator);
	}

	/**
	* Returns the number of groups belonging to the parent group that match the
	* keywords, optionally including the user's inherited organization groups
	* and user groups. System and staged groups are not included.
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @return the number of matching groups
	*/
	public static int searchCount(long companyId, long parentGroupId,
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getService()
				   .searchCount(companyId, parentGroupId, keywords, params);
	}

	/**
	* Returns the number of groups belonging to the parent group and immediate
	* organization groups that match the name and description, optionally
	* including the user's inherited organization groups and user groups.
	* System and staged groups are not included.
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @return the number of matching groups
	*/
	public static int searchCount(long companyId, long parentGroupId,
		java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return getService()
				   .searchCount(companyId, parentGroupId, name, description,
			params, andOperator);
	}

	/**
	* Returns the number of groups that match the class name IDs, and keywords,
	* optionally including the user's inherited organization groups and user
	* groups. System and staged groups are not included.
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @return the number of matching groups
	*/
	public static int searchCount(long companyId, long[] classNameIds,
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getService()
				   .searchCount(companyId, classNameIds, keywords, params);
	}

	/**
	* Returns the number of groups that match the class name IDs, name, and
	* description, optionally including the user's inherited organization
	* groups and user groups. System and staged groups are not included.
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @return the number of matching groups
	*/
	public static int searchCount(long companyId, long[] classNameIds,
		java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return getService()
				   .searchCount(companyId, classNameIds, name, description,
			params, andOperator);
	}

	/**
	* Returns the number of groups belonging to the parent group that match the
	* class name IDs, and keywords, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param parentGroupId the primary key of the parent group
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @return the number of matching groups
	*/
	public static int searchCount(long companyId, long[] classNameIds,
		long parentGroupId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getService()
				   .searchCount(companyId, classNameIds, parentGroupId,
			keywords, params);
	}

	/**
	* Returns the number of groups belonging to the parent group that match the
	* class name IDs, name, and description, optionally including the user's
	* inherited organization groups and user groups. System and staged groups
	* are not included.
	*
	* @param companyId the primary key of the company
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param parentGroupId the primary key of the parent group
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organization groups and user groups
	in the search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @return the number of matching groups
	*/
	public static int searchCount(long companyId, long[] classNameIds,
		long parentGroupId, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return getService()
				   .searchCount(companyId, classNameIds, parentGroupId, name,
			description, params, andOperator);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link
	Group#getDescriptiveName(Locale)}
	*/
	@Deprecated
	public static java.lang.String getGroupDescriptiveName(
		com.liferay.portal.kernel.model.Group group, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGroupDescriptiveName(group, locale);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link
	Group#getDescriptiveName(Locale)}
	*/
	@Deprecated
	public static java.lang.String getGroupDescriptiveName(long groupId,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGroupDescriptiveName(groupId, locale);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.GroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.GroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns all the active or inactive groups associated with the company.
	*
	* @param companyId the primary key of the company
	* @param active whether to return only active groups, or only inactive
	groups
	* @return the active or inactive groups associated with the company
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getActiveGroups(
		long companyId, boolean active) {
		return getService().getActiveGroups(companyId, active);
	}

	/**
	* Returns a range of all the groups associated with the company.
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
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the range of groups associated with the company
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getCompanyGroups(
		long companyId, int start, int end) {
		return getService().getCompanyGroups(companyId, start, end);
	}

	/**
	* Returns a range of all the groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.GroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of groups
	* @param end the upper bound of the range of groups (not inclusive)
	* @return the range of groups
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getGroups(
		int start, int end) {
		return getService().getGroups(start, end);
	}

	/**
	* Returns all the groups that are direct children of the parent group with
	* the matching className.
	*
	* @param companyId the primary key of the company
	* @param className the class name of the group
	* @param parentGroupId the primary key of the parent group
	* @return the matching groups, or <code>null</code> if no matches were
	found
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getGroups(
		long companyId, java.lang.String className, long parentGroupId) {
		return getService().getGroups(companyId, className, parentGroupId);
	}

	/**
	* Returns a range of all the groups that are direct children of the parent
	* group with the matching className.
	*
	* @param companyId the primary key of the company
	* @param className the class name of the group
	* @param parentGroupId the primary key of the parent group
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching groups
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getGroups(
		long companyId, java.lang.String className, long parentGroupId,
		int start, int end) {
		return getService()
				   .getGroups(companyId, className, parentGroupId, start, end);
	}

	/**
	* Returns all the groups that are direct children of the parent group.
	*
	* @param companyId the primary key of the company
	* @param parentGroupId the primary key of the parent group
	* @param site whether the group is to be associated with a main site
	* @return the matching groups, or <code>null</code> if no matches were
	found
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getGroups(
		long companyId, long parentGroupId, boolean site) {
		return getService().getGroups(companyId, parentGroupId, site);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getGroups(
		long companyId, long parentGroupId, boolean site, boolean inheritContent) {
		return getService()
				   .getGroups(companyId, parentGroupId, site, inheritContent);
	}

	/**
	* Returns the groups with the matching primary keys.
	*
	* @param groupIds the primary keys of the groups
	* @return the groups with the primary keys
	* @throws PortalException if a portal exception occurred
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getGroups(
		long[] groupIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGroups(groupIds);
	}

	/**
	* Returns a range of all groups that are children of the parent group and
	* that have at least one layout.
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
	* @param parentGroupId the primary key of the parent group
	* @param site whether the group is to be associated with a main site
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the range of matching groups ordered by comparator
	<code>obc</code>
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getLayoutsGroups(
		long companyId, long parentGroupId, boolean site, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> obc) {
		return getService()
				   .getLayoutsGroups(companyId, parentGroupId, site, start,
			end, obc);
	}

	/**
	* Returns all live groups.
	*
	* @return all live groups
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getLiveGroups() {
		return getService().getLiveGroups();
	}

	/**
	* Returns a range of all non-system groups of a specified type (className)
	* that have no layouts.
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
	* @param className the entity's class name
	* @param privateLayout whether to include groups with private layout sets
	or non-private layout sets
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the range of matching groups
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getNoLayoutsGroups(
		java.lang.String className, boolean privateLayout, int start, int end) {
		return getService()
				   .getNoLayoutsGroups(className, privateLayout, start, end);
	}

	/**
	* Returns all non-system groups having <code>null</code> or empty friendly
	* URLs.
	*
	* @return the non-system groups having <code>null</code> or empty friendly
	URLs
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getNullFriendlyURLGroups() {
		return getService().getNullFriendlyURLGroups();
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getOrganizationGroups(
		long organizationId) {
		return getService().getOrganizationGroups(organizationId);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getOrganizationGroups(
		long organizationId, int start, int end) {
		return getService().getOrganizationGroups(organizationId, start, end);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getOrganizationGroups(
		long organizationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> orderByComparator) {
		return getService()
				   .getOrganizationGroups(organizationId, start, end,
			orderByComparator);
	}

	/**
	* Returns the specified organization groups.
	*
	* @param organizations the organizations
	* @return the groups associated with the organizations
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getOrganizationsGroups(
		java.util.List<com.liferay.portal.kernel.model.Organization> organizations) {
		return getService().getOrganizationsGroups(organizations);
	}

	/**
	* Returns all the groups related to the organizations.
	*
	* @param organizations the organizations
	* @return the groups related to the organizations
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getOrganizationsRelatedGroups(
		java.util.List<com.liferay.portal.kernel.model.Organization> organizations) {
		return getService().getOrganizationsRelatedGroups(organizations);
	}

	/**
	* Returns the group followed by all its parent groups ordered by closest
	* ancestor.
	*
	* @param groupId the primary key of the group
	* @return the group followed by all its parent groups ordered by closest
	ancestor
	* @throws PortalException if a portal exception occurred
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getParentGroups(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getParentGroups(groupId);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getRoleGroups(
		long roleId) {
		return getService().getRoleGroups(roleId);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getRoleGroups(
		long roleId, int start, int end) {
		return getService().getRoleGroups(roleId, start, end);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getRoleGroups(
		long roleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> orderByComparator) {
		return getService().getRoleGroups(roleId, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getStagedSites() {
		return getService().getStagedSites();
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserGroupGroups(
		long userGroupId) {
		return getService().getUserGroupGroups(userGroupId);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserGroupGroups(
		long userGroupId, int start, int end) {
		return getService().getUserGroupGroups(userGroupId, start, end);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserGroupGroups(
		long userGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> orderByComparator) {
		return getService()
				   .getUserGroupGroups(userGroupId, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserGroups(
		long userId) {
		return getService().getUserGroups(userId);
	}

	/**
	* Returns all the user's site groups and immediate organization groups,
	* optionally including the user's inherited organization groups and user
	* groups. System and staged groups are not included.
	*
	* @param userId the primary key of the user
	* @param inherit whether to include the user's inherited organization
	groups and user groups
	* @return the user's groups and immediate organization groups
	* @throws PortalException if a portal exception occurred
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserGroups(
		long userId, boolean inherit)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserGroups(userId, inherit);
	}

	/**
	* Returns an ordered range of all the user's site groups and immediate
	* organization groups, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
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
	* @param inherit whether to include the user's inherited organization
	groups and user groups
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the range of the user's groups and immediate organization groups
	ordered by name
	* @throws PortalException if a portal exception occurred
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserGroups(
		long userId, boolean inherit, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserGroups(userId, inherit, start, end);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserGroups(
		long userId, int start, int end) {
		return getService().getUserGroups(userId, start, end);
	}

	/**
	* @throws PortalException
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserGroups(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserGroups(userId, start, end, orderByComparator);
	}

	/**
	* Returns the groups associated with the user groups.
	*
	* @param userGroups the user groups
	* @return the groups associated with the user groups
	* @throws PortalException if a portal exception occurred
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserGroupsGroups(
		java.util.List<com.liferay.portal.kernel.model.UserGroup> userGroups)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserGroupsGroups(userGroups);
	}

	/**
	* Returns all the groups related to the user groups.
	*
	* @param userGroups the user groups
	* @return the groups related to the user groups
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserGroupsRelatedGroups(
		java.util.List<com.liferay.portal.kernel.model.UserGroup> userGroups) {
		return getService().getUserGroupsRelatedGroups(userGroups);
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
	* @return the range of groups associated with the user's organization
	groups
	* @throws PortalException if a portal exception occurred
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserOrganizationsGroups(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserOrganizationsGroups(userId, start, end);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserSitesGroups(
		long userId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserSitesGroups(userId);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getUserSitesGroups(
		long userId, boolean includeAdministrative)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserSitesGroups(userId, includeAdministrative);
	}

	/**
	* Returns an ordered range of all the groups that match the keywords,
	* optionally including the user's inherited organization groups and user
	* groups. System and staged groups are not included.
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
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
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
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end) {
		return getService().search(companyId, keywords, params, start, end);
	}

	/**
	* Returns an ordered range of all the groups that match the keywords,
	* optionally including the user's inherited organization groups and user
	* groups. System and staged groups are not included.
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
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> obc) {
		return getService().search(companyId, keywords, params, start, end, obc);
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
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end) {
		return getService()
				   .search(companyId, name, description, params, andOperator,
			start, end);
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
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> obc) {
		return getService()
				   .search(companyId, name, description, params, andOperator,
			start, end, obc);
	}

	/**
	* Returns an ordered range of all the company's groups, optionally
	* including the user's inherited organization groups and user groups.
	* System and staged groups are not included.
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
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end) {
		return getService().search(companyId, params, start, end);
	}

	/**
	* Returns an ordered range of all the groups belonging to the parent group
	* that match the keywords, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
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
	* @param parentGroupId the primary key of the parent group
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
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
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long parentGroupId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end) {
		return getService()
				   .search(companyId, parentGroupId, keywords, params, start,
			end);
	}

	/**
	* Returns an ordered range of all the groups belonging to the parent group
	* that match the keywords, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
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
	* @param parentGroupId the primary key of the parent group
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long parentGroupId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> obc) {
		return getService()
				   .search(companyId, parentGroupId, keywords, params, start,
			end, obc);
	}

	/**
	* Returns an ordered range of all the site groups belonging to the parent
	* group and organization groups that match the name and description,
	* optionally including the user's inherited organization groups and user
	* groups. System and staged groups are not included.
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
	* @param parentGroupId the primary key of the parent group
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long parentGroupId, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end) {
		return getService()
				   .search(companyId, parentGroupId, name, description, params,
			andOperator, start, end);
	}

	/**
	* Returns an ordered range of all the site groups belonging to the parent
	* group and organization groups that match the name and description,
	* optionally including the user's inherited organization groups and user
	* groups. System and staged groups are not included.
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
	* @param parentGroupId the primary key of the parent group
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include the user's inherited organizations and user groups in the
	search, add entries having &quot;usersGroups&quot; and
	&quot;inherit&quot; as keys mapped to the the user's ID. For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long parentGroupId, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> obc) {
		return getService()
				   .search(companyId, parentGroupId, name, description, params,
			andOperator, start, end, obc);
	}

	/**
	* Returns an ordered range of all the groups that match the class name IDs
	* and keywords, optionally including the user's inherited organization
	* groups and user groups. System and staged groups are not included.
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
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long[] classNameIds, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end) {
		return getService()
				   .search(companyId, classNameIds, keywords, params, start, end);
	}

	/**
	* Returns an ordered range of all the groups that match the class name IDs
	* and keywords, optionally including the user's inherited organization
	* groups and user groups. System and staged groups are not included.
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
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long[] classNameIds, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> obc) {
		return getService()
				   .search(companyId, classNameIds, keywords, params, start,
			end, obc);
	}

	/**
	* Returns an ordered range of all the groups that match the class name IDs,
	* name, and description, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
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
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long[] classNameIds, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end) {
		return getService()
				   .search(companyId, classNameIds, name, description, params,
			andOperator, start, end);
	}

	/**
	* Returns an ordered range of all the groups that match the class name IDs,
	* name, and description, optionally including the user's inherited
	* organization groups and user groups. System and staged groups are not
	* included.
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
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long[] classNameIds, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> obc) {
		return getService()
				   .search(companyId, classNameIds, name, description, params,
			andOperator, start, end, obc);
	}

	/**
	* Returns an ordered range of all the groups belonging to the parent group
	* that match the class name IDs and keywords, optionally including the
	* user's inherited organization groups and user groups. System and staged
	* groups are not included.
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
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param parentGroupId the primary key of the parent group
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long[] classNameIds, long parentGroupId,
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end) {
		return getService()
				   .search(companyId, classNameIds, parentGroupId, keywords,
			params, start, end);
	}

	/**
	* Returns an ordered range of all the groups belonging to the parent group
	* that match the class name IDs and keywords, optionally including the
	* user's inherited organization groups and user groups. System and staged
	* groups are not included.
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
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param parentGroupId the primary key of the parent group
	* @param keywords the keywords (space separated), which may occur in the
	sites's name, or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long[] classNameIds, long parentGroupId,
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> obc) {
		return getService()
				   .search(companyId, classNameIds, parentGroupId, keywords,
			params, start, end, obc);
	}

	/**
	* Returns an ordered range of all the groups belonging to the parent group
	* that match the class name IDs, name, and description, optionally
	* including the user's inherited organization groups and user groups.
	* System and staged groups are not included.
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
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param parentGroupId the primary key of the parent group
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @return the matching groups ordered by name
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long[] classNameIds, long parentGroupId,
		java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end) {
		return getService()
				   .search(companyId, classNameIds, parentGroupId, name,
			description, params, andOperator, start, end);
	}

	/**
	* Returns an ordered range of all the groups belonging to the parent group
	* that match the class name IDs, name, and description, optionally
	* including the user's inherited organization groups and user groups.
	* System and staged groups are not included.
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
	* @param classNameIds the primary keys of the class names of the entities
	the groups are related to (optionally <code>null</code>)
	* @param parentGroupId the primary key of the parent group
	* @param name the group's name (optionally <code>null</code>)
	* @param description the group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). To
	include a user's organizations, inherited organizations, and user
	groups in the search, add an entry with key
	&quot;usersGroups&quot; mapped to the user's ID and an entry with
	key &quot;inherit&quot; mapped to a non-<code>null</code> object.
	For more information see {@link
	com.liferay.portal.kernel.service.persistence.GroupFinder}.
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of groups to return
	* @param end the upper bound of the range of groups to return (not
	inclusive)
	* @param obc the comparator to order the groups (optionally
	<code>null</code>)
	* @return the matching groups ordered by comparator <code>obc</code>
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Group> search(
		long companyId, long[] classNameIds, long parentGroupId,
		java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group> obc) {
		return getService()
				   .search(companyId, classNameIds, parentGroupId, name,
			description, params, andOperator, start, end, obc);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Returns the organizationIds of the organizations associated with the group.
	*
	* @param groupId the groupId of the group
	* @return long[] the organizationIds of organizations associated with the group
	*/
	public static long[] getOrganizationPrimaryKeys(long groupId) {
		return getService().getOrganizationPrimaryKeys(groupId);
	}

	/**
	* Returns the roleIds of the roles associated with the group.
	*
	* @param groupId the groupId of the group
	* @return long[] the roleIds of roles associated with the group
	*/
	public static long[] getRolePrimaryKeys(long groupId) {
		return getService().getRolePrimaryKeys(groupId);
	}

	/**
	* Returns the userGroupIds of the user groups associated with the group.
	*
	* @param groupId the groupId of the group
	* @return long[] the userGroupIds of user groups associated with the group
	*/
	public static long[] getUserGroupPrimaryKeys(long groupId) {
		return getService().getUserGroupPrimaryKeys(groupId);
	}

	/**
	* Returns the userIds of the users associated with the group.
	*
	* @param groupId the groupId of the group
	* @return long[] the userIds of users associated with the group
	*/
	public static long[] getUserPrimaryKeys(long groupId) {
		return getService().getUserPrimaryKeys(groupId);
	}

	public static void addOrganizationGroup(long organizationId,
		com.liferay.portal.kernel.model.Group group) {
		getService().addOrganizationGroup(organizationId, group);
	}

	public static void addOrganizationGroup(long organizationId, long groupId) {
		getService().addOrganizationGroup(organizationId, groupId);
	}

	public static void addOrganizationGroups(long organizationId,
		java.util.List<com.liferay.portal.kernel.model.Group> groups) {
		getService().addOrganizationGroups(organizationId, groups);
	}

	public static void addOrganizationGroups(long organizationId,
		long[] groupIds) {
		getService().addOrganizationGroups(organizationId, groupIds);
	}

	public static void addRoleGroup(long roleId,
		com.liferay.portal.kernel.model.Group group) {
		getService().addRoleGroup(roleId, group);
	}

	public static void addRoleGroup(long roleId, long groupId) {
		getService().addRoleGroup(roleId, groupId);
	}

	public static void addRoleGroups(long roleId,
		java.util.List<com.liferay.portal.kernel.model.Group> groups) {
		getService().addRoleGroups(roleId, groups);
	}

	public static void addRoleGroups(long roleId, long[] groupIds) {
		getService().addRoleGroups(roleId, groupIds);
	}

	public static void addUserGroup(long userId,
		com.liferay.portal.kernel.model.Group group) {
		getService().addUserGroup(userId, group);
	}

	public static void addUserGroup(long userId, long groupId) {
		getService().addUserGroup(userId, groupId);
	}

	public static void addUserGroupGroup(long userGroupId,
		com.liferay.portal.kernel.model.Group group) {
		getService().addUserGroupGroup(userGroupId, group);
	}

	public static void addUserGroupGroup(long userGroupId, long groupId) {
		getService().addUserGroupGroup(userGroupId, groupId);
	}

	public static void addUserGroupGroups(long userGroupId,
		java.util.List<com.liferay.portal.kernel.model.Group> groups) {
		getService().addUserGroupGroups(userGroupId, groups);
	}

	public static void addUserGroupGroups(long userGroupId, long[] groupIds) {
		getService().addUserGroupGroups(userGroupId, groupIds);
	}

	public static void addUserGroups(long userId,
		java.util.List<com.liferay.portal.kernel.model.Group> groups) {
		getService().addUserGroups(userId, groups);
	}

	public static void addUserGroups(long userId, long[] groupIds) {
		getService().addUserGroups(userId, groupIds);
	}

	/**
	* Adds a company group if it does not exist. This method is typically used
	* when a virtual host is added.
	*
	* @param companyId the primary key of the company
	* @throws PortalException if a portal exception occurred
	*/
	public static void checkCompanyGroup(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().checkCompanyGroup(companyId);
	}

	/**
	* Creates systems groups and other related data needed by the system on the
	* very first startup. Also takes care of creating the Control Panel groups
	* and layouts.
	*
	* @param companyId the primary key of the company
	* @throws PortalException if a portal exception occurred
	*/
	public static void checkSystemGroups(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().checkSystemGroups(companyId);
	}

	public static void clearOrganizationGroups(long organizationId) {
		getService().clearOrganizationGroups(organizationId);
	}

	public static void clearRoleGroups(long roleId) {
		getService().clearRoleGroups(roleId);
	}

	public static void clearUserGroupGroups(long userGroupId) {
		getService().clearUserGroupGroups(userGroupId);
	}

	public static void clearUserGroups(long userId) {
		getService().clearUserGroups(userId);
	}

	public static void deleteOrganizationGroup(long organizationId,
		com.liferay.portal.kernel.model.Group group) {
		getService().deleteOrganizationGroup(organizationId, group);
	}

	public static void deleteOrganizationGroup(long organizationId, long groupId) {
		getService().deleteOrganizationGroup(organizationId, groupId);
	}

	public static void deleteOrganizationGroups(long organizationId,
		java.util.List<com.liferay.portal.kernel.model.Group> groups) {
		getService().deleteOrganizationGroups(organizationId, groups);
	}

	public static void deleteOrganizationGroups(long organizationId,
		long[] groupIds) {
		getService().deleteOrganizationGroups(organizationId, groupIds);
	}

	public static void deleteRoleGroup(long roleId,
		com.liferay.portal.kernel.model.Group group) {
		getService().deleteRoleGroup(roleId, group);
	}

	public static void deleteRoleGroup(long roleId, long groupId) {
		getService().deleteRoleGroup(roleId, groupId);
	}

	public static void deleteRoleGroups(long roleId,
		java.util.List<com.liferay.portal.kernel.model.Group> groups) {
		getService().deleteRoleGroups(roleId, groups);
	}

	public static void deleteRoleGroups(long roleId, long[] groupIds) {
		getService().deleteRoleGroups(roleId, groupIds);
	}

	public static void deleteUserGroup(long userId,
		com.liferay.portal.kernel.model.Group group) {
		getService().deleteUserGroup(userId, group);
	}

	public static void deleteUserGroup(long userId, long groupId) {
		getService().deleteUserGroup(userId, groupId);
	}

	public static void deleteUserGroupGroup(long userGroupId,
		com.liferay.portal.kernel.model.Group group) {
		getService().deleteUserGroupGroup(userGroupId, group);
	}

	public static void deleteUserGroupGroup(long userGroupId, long groupId) {
		getService().deleteUserGroupGroup(userGroupId, groupId);
	}

	public static void deleteUserGroupGroups(long userGroupId,
		java.util.List<com.liferay.portal.kernel.model.Group> groups) {
		getService().deleteUserGroupGroups(userGroupId, groups);
	}

	public static void deleteUserGroupGroups(long userGroupId, long[] groupIds) {
		getService().deleteUserGroupGroups(userGroupId, groupIds);
	}

	public static void deleteUserGroups(long userId,
		java.util.List<com.liferay.portal.kernel.model.Group> groups) {
		getService().deleteUserGroups(userId, groups);
	}

	public static void deleteUserGroups(long userId, long[] groupIds) {
		getService().deleteUserGroups(userId, groupIds);
	}

	public static void disableStaging(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().disableStaging(groupId);
	}

	public static void enableStaging(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().enableStaging(groupId);
	}

	/**
	* Rebuilds the group tree.
	*
	* <p>
	* Only call this method if the tree has become stale through operations
	* other than normal CRUD. Under normal circumstances the tree is
	* automatically rebuilt whenever necessary.
	* </p>
	*
	* @param companyId the primary key of the group's company
	* @throws PortalException if a portal exception occurred
	*/
	public static void rebuildTree(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().rebuildTree(companyId);
	}

	public static void setOrganizationGroups(long organizationId,
		long[] groupIds) {
		getService().setOrganizationGroups(organizationId, groupIds);
	}

	public static void setRoleGroups(long roleId, long[] groupIds) {
		getService().setRoleGroups(roleId, groupIds);
	}

	public static void setUserGroupGroups(long userGroupId, long[] groupIds) {
		getService().setUserGroupGroups(userGroupId, groupIds);
	}

	public static void setUserGroups(long userId, long[] groupIds) {
		getService().setUserGroups(userId, groupIds);
	}

	/**
	* Removes the groups from the role.
	*
	* @param roleId the primary key of the role
	* @param groupIds the primary keys of the groups
	*/
	public static void unsetRoleGroups(long roleId, long[] groupIds) {
		getService().unsetRoleGroups(roleId, groupIds);
	}

	/**
	* Removes the user from the groups.
	*
	* @param userId the primary key of the user
	* @param groupIds the primary keys of the groups
	*/
	public static void unsetUserGroups(long userId, long[] groupIds) {
		getService().unsetUserGroups(userId, groupIds);
	}

	/**
	* Updates the group's asset replacing categories and tag names.
	*
	* @param userId the primary key of the user
	* @param group the group
	* @param assetCategoryIds the primary keys of the asset categories
	(optionally <code>null</code>)
	* @param assetTagNames the asset tag names (optionally <code>null</code>)
	* @throws PortalException if a portal exception occurred
	*/
	public static void updateAsset(long userId,
		com.liferay.portal.kernel.model.Group group, long[] assetCategoryIds,
		java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateAsset(userId, group, assetCategoryIds, assetTagNames);
	}

	public static GroupLocalService getService() {
		if (_service == null) {
			_service = (GroupLocalService)PortalBeanLocatorUtil.locate(GroupLocalService.class.getName());

			ReferenceRegistry.registerReference(GroupLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static GroupLocalService _service;
}