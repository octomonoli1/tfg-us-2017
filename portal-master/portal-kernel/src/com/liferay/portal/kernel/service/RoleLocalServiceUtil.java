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
 * Provides the local service utility for Role. This utility wraps
 * {@link com.liferay.portal.service.impl.RoleLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RoleLocalService
 * @see com.liferay.portal.service.base.RoleLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.RoleLocalServiceImpl
 * @generated
 */
@ProviderType
public class RoleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.RoleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasGroupRole(long groupId, long roleId) {
		return getService().hasGroupRole(groupId, roleId);
	}

	public static boolean hasGroupRoles(long groupId) {
		return getService().hasGroupRoles(groupId);
	}

	/**
	* Returns <code>true</code> if the user is associated with the named
	* regular role.
	*
	* @param userId the primary key of the user
	* @param companyId the primary key of the company
	* @param name the name of the role
	* @param inherited whether to include the user's inherited roles in the
	search
	* @return <code>true</code> if the user is associated with the regular
	role; <code>false</code> otherwise
	*/
	public static boolean hasUserRole(long userId, long companyId,
		java.lang.String name, boolean inherited)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().hasUserRole(userId, companyId, name, inherited);
	}

	public static boolean hasUserRole(long userId, long roleId) {
		return getService().hasUserRole(userId, roleId);
	}

	public static boolean hasUserRoles(long userId) {
		return getService().hasUserRoles(userId);
	}

	/**
	* Returns <code>true</code> if the user has any one of the named regular
	* roles.
	*
	* @param userId the primary key of the user
	* @param companyId the primary key of the company
	* @param names the names of the roles
	* @param inherited whether to include the user's inherited roles in the
	search
	* @return <code>true</code> if the user has any one of the regular roles;
	<code>false</code> otherwise
	*/
	public static boolean hasUserRoles(long userId, long companyId,
		java.lang.String[] names, boolean inherited)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().hasUserRoles(userId, companyId, names, inherited);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
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
	* Adds the role to the database. Also notifies the appropriate model listeners.
	*
	* @param role the role
	* @return the role that was added
	*/
	public static com.liferay.portal.kernel.model.Role addRole(
		com.liferay.portal.kernel.model.Role role) {
		return getService().addRole(role);
	}

	/**
	* Adds a role with additional parameters. The user is reindexed after role
	* is added.
	*
	* @param userId the primary key of the user
	* @param className the name of the class for which the role is created
	(optionally <code>null</code>)
	* @param classPK the primary key of the class for which the role is
	created (optionally <code>0</code>)
	* @param name the role's name
	* @param titleMap the role's localized titles (optionally
	<code>null</code>)
	* @param descriptionMap the role's localized descriptions (optionally
	<code>null</code>)
	* @param type the role's type (optionally <code>0</code>)
	* @param subtype the role's subtype (optionally <code>null</code>)
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set expando bridge attributes for the
	role.
	* @return the role
	*/
	public static com.liferay.portal.kernel.model.Role addRole(long userId,
		java.lang.String className, long classPK, java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int type, java.lang.String subtype, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addRole(userId, className, classPK, name, titleMap,
			descriptionMap, type, subtype, serviceContext);
	}

	/**
	* Creates a new role with the primary key. Does not add the role to the database.
	*
	* @param roleId the primary key for the new role
	* @return the new role
	*/
	public static com.liferay.portal.kernel.model.Role createRole(long roleId) {
		return getService().createRole(roleId);
	}

	/**
	* Deletes the role from the database. Also notifies the appropriate model listeners.
	*
	* @param role the role
	* @return the role that was removed
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.Role deleteRole(
		com.liferay.portal.kernel.model.Role role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRole(role);
	}

	/**
	* Deletes the role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param roleId the primary key of the role
	* @return the role that was removed
	* @throws PortalException if a role with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.model.Role deleteRole(long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRole(roleId);
	}

	/**
	* Returns the role with the name in the company.
	*
	* <p>
	* The method searches the system roles map first for default roles. If a
	* role with the name is not found, then the method will query the database.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the role's name
	* @return Returns the role with the name or <code>null</code> if a role
	with the name could not be found in the company
	*/
	public static com.liferay.portal.kernel.model.Role fetchRole(
		long companyId, java.lang.String name) {
		return getService().fetchRole(companyId, name);
	}

	public static com.liferay.portal.kernel.model.Role fetchRole(long roleId) {
		return getService().fetchRole(roleId);
	}

	/**
	* Returns the role with the matching UUID and company.
	*
	* @param uuid the role's UUID
	* @param companyId the primary key of the company
	* @return the matching role, or <code>null</code> if a matching role could not be found
	*/
	public static com.liferay.portal.kernel.model.Role fetchRoleByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().fetchRoleByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the default role for the group with the primary key.
	*
	* <p>
	* If the group is a site, then the default role is {@link
	* RoleConstants#SITE_MEMBER}. If the group is an organization, then the
	* default role is {@link RoleConstants#ORGANIZATION_USER}. If the group is
	* a user or user group, then the default role is {@link
	* RoleConstants#POWER_USER}. For all other group types, the default role is
	* {@link RoleConstants#USER}.
	* </p>
	*
	* @param groupId the primary key of the group
	* @return the default role for the group with the primary key
	*/
	public static com.liferay.portal.kernel.model.Role getDefaultGroupRole(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDefaultGroupRole(groupId);
	}

	/**
	* Returns the role with the name in the company.
	*
	* <p>
	* The method searches the system roles map first for default roles. If a
	* role with the name is not found, then the method will query the database.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the role's name
	* @return the role with the name
	*/
	public static com.liferay.portal.kernel.model.Role getRole(long companyId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRole(companyId, name);
	}

	/**
	* Returns the role with the primary key.
	*
	* @param roleId the primary key of the role
	* @return the role
	* @throws PortalException if a role with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.model.Role getRole(long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRole(roleId);
	}

	/**
	* Returns the role with the matching UUID and company.
	*
	* @param uuid the role's UUID
	* @param companyId the primary key of the company
	* @return the matching role
	* @throws PortalException if a matching role could not be found
	*/
	public static com.liferay.portal.kernel.model.Role getRoleByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRoleByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the team role in the company.
	*
	* @param companyId the primary key of the company
	* @param teamId the primary key of the team
	* @return the team role in the company
	*/
	public static com.liferay.portal.kernel.model.Role getTeamRole(
		long companyId, long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTeamRole(companyId, teamId);
	}

	/**
	* Returns a role with the name in the company.
	*
	* @param companyId the primary key of the company
	* @param name the role's name (optionally <code>null</code>)
	* @return the role with the name, or <code>null</code> if a role with the
	name could not be found in the company
	*/
	public static com.liferay.portal.kernel.model.Role loadFetchRole(
		long companyId, java.lang.String name) {
		return getService().loadFetchRole(companyId, name);
	}

	/**
	* Returns a role with the name in the company.
	*
	* @param companyId the primary key of the company
	* @param name the role's name
	* @return the role with the name in the company
	*/
	public static com.liferay.portal.kernel.model.Role loadGetRole(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().loadGetRole(companyId, name);
	}

	/**
	* Updates the role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param role the role
	* @return the role that was updated
	*/
	public static com.liferay.portal.kernel.model.Role updateRole(
		com.liferay.portal.kernel.model.Role role) {
		return getService().updateRole(role);
	}

	/**
	* Updates the role with the primary key.
	*
	* @param roleId the primary key of the role
	* @param name the role's new name
	* @param titleMap the new localized titles (optionally <code>null</code>)
	to replace those existing for the role
	* @param descriptionMap the new localized descriptions (optionally
	<code>null</code>) to replace those existing for the role
	* @param subtype the role's new subtype (optionally <code>null</code>)
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set expando bridge attributes for the
	role.
	* @return the role with the primary key
	*/
	public static com.liferay.portal.kernel.model.Role updateRole(long roleId,
		java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String subtype, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateRole(roleId, name, titleMap, descriptionMap, subtype,
			serviceContext);
	}

	public static int getGroupRolesCount(long groupId) {
		return getService().getGroupRolesCount(groupId);
	}

	/**
	* Returns the number of roles.
	*
	* @return the number of roles
	*/
	public static int getRolesCount() {
		return getService().getRolesCount();
	}

	/**
	* Returns the number of roles of the subtype.
	*
	* @param subtype the role's subtype (optionally <code>null</code>)
	* @return the number of roles of the subtype
	*/
	public static int getSubtypeRolesCount(java.lang.String subtype) {
		return getService().getSubtypeRolesCount(subtype);
	}

	/**
	* Returns the number of roles of the type.
	*
	* @param type the role's type (optionally <code>0</code>)
	* @return the number of roles of the type
	*/
	public static int getTypeRolesCount(int type) {
		return getService().getTypeRolesCount(type);
	}

	public static int getUserGroupGroupRolesCount(long userId, long groupId) {
		return getService().getUserGroupGroupRolesCount(userId, groupId);
	}

	public static int getUserRolesCount(long userId) {
		return getService().getUserRolesCount(userId);
	}

	/**
	* Returns the number of roles that match the keywords and types.
	*
	* @param companyId the primary key of the company
	* @param keywords the keywords (space separated), which may occur in the
	role's name or description (optionally <code>null</code>)
	* @param types the role types (optionally <code>null</code>)
	* @return the number of matching roles
	*/
	public static int searchCount(long companyId, java.lang.String keywords,
		java.lang.Integer[] types) {
		return getService().searchCount(companyId, keywords, types);
	}

	/**
	* Returns the number of roles that match the keywords, types and params.
	*
	* @param companyId the primary key of the company
	* @param keywords the keywords (space separated), which may occur in the
	role's name or description (optionally <code>null</code>)
	* @param types the role types (optionally <code>null</code>)
	* @param params the finder parameters. For more information, see {@link
	com.liferay.portal.kernel.service.persistence.RoleFinder}
	* @return the number of matching roles
	*/
	public static int searchCount(long companyId, java.lang.String keywords,
		java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getService().searchCount(companyId, keywords, types, params);
	}

	/**
	* Returns the number of roles that match the name, description, and types.
	*
	* @param companyId the primary key of the company
	* @param name the role's name (optionally <code>null</code>)
	* @param description the role's description (optionally <code>null</code>)
	* @param types the role types (optionally <code>null</code>)
	* @return the number of matching roles
	*/
	public static int searchCount(long companyId, java.lang.String name,
		java.lang.String description, java.lang.Integer[] types) {
		return getService().searchCount(companyId, name, description, types);
	}

	/**
	* Returns the number of roles that match the name, description, types, and
	* params.
	*
	* @param companyId the primary key of the company
	* @param name the role's name (optionally <code>null</code>)
	* @param description the role's description (optionally <code>null</code>)
	* @param types the role types (optionally <code>null</code>)
	* @param params the finder parameters. Can specify values for the
	"usersRoles" key. For more information, see {@link
	com.liferay.portal.kernel.service.persistence.RoleFinder}
	* @return the number of matching roles
	*/
	public static int searchCount(long companyId, java.lang.String name,
		java.lang.String description, java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getService()
				   .searchCount(companyId, name, description, types, params);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.portal.kernel.model.Role> getGroupRelatedRoles(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGroupRelatedRoles(groupId);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Role> getGroupRoles(
		long groupId) {
		return getService().getGroupRoles(groupId);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Role> getGroupRoles(
		long groupId, int start, int end) {
		return getService().getGroupRoles(groupId, start, end);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Role> getGroupRoles(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role> orderByComparator) {
		return getService().getGroupRoles(groupId, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Role> getResourceBlockRoles(
		long resourceBlockId, java.lang.String className,
		java.lang.String actionId) {
		return getService()
				   .getResourceBlockRoles(resourceBlockId, className, actionId);
	}

	/**
	* Returns all the roles associated with the action ID in the company within
	* the permission scope.
	*
	* @param companyId the primary key of the company
	* @param name the resource name
	* @param scope the permission scope
	* @param primKey the primary key of the resource's class
	* @param actionId the name of the resource action
	* @return the roles
	* @see com.liferay.portal.kernel.service.persistence.RoleFinder#findByC_N_S_P_A(
	long, String, int, String, String)
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getResourceRoles(
		long companyId, java.lang.String name, int scope,
		java.lang.String primKey, java.lang.String actionId) {
		return getService()
				   .getResourceRoles(companyId, name, scope, primKey, actionId);
	}

	/**
	* Returns a range of all the roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of roles
	* @param end the upper bound of the range of roles (not inclusive)
	* @return the range of roles
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getRoles(
		int start, int end) {
		return getService().getRoles(start, end);
	}

	/**
	* Returns all the roles of the type and subtype.
	*
	* @param type the role's type (optionally <code>0</code>)
	* @param subtype the role's subtype (optionally <code>null</code>)
	* @return the roles of the type and subtype
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getRoles(
		int type, java.lang.String subtype) {
		return getService().getRoles(type, subtype);
	}

	/**
	* Returns all the roles in the company.
	*
	* @param companyId the primary key of the company
	* @return the roles in the company
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getRoles(
		long companyId) {
		return getService().getRoles(companyId);
	}

	/**
	* Returns all the roles with the types.
	*
	* @param companyId the primary key of the company
	* @param types the role types (optionally <code>null</code>)
	* @return the roles with the types
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getRoles(
		long companyId, int[] types) {
		return getService().getRoles(companyId, types);
	}

	/**
	* Returns all the roles with the primary keys.
	*
	* @param roleIds the primary keys of the roles
	* @return the roles with the primary keys
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getRoles(
		long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRoles(roleIds);
	}

	/**
	* Returns all the roles of the subtype.
	*
	* @param subtype the role's subtype (optionally <code>null</code>)
	* @return the roles of the subtype
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getSubtypeRoles(
		java.lang.String subtype) {
		return getService().getSubtypeRoles(subtype);
	}

	/**
	* Returns the team roles in the group.
	*
	* @param groupId the primary key of the group
	* @return the team roles in the group
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getTeamRoles(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTeamRoles(groupId);
	}

	/**
	* Returns the team roles in the group, excluding the specified role IDs.
	*
	* @param groupId the primary key of the group
	* @param excludedRoleIds the primary keys of the roles to exclude
	(optionally <code>null</code>)
	* @return the team roles in the group, excluding the specified role IDs
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getTeamRoles(
		long groupId, long[] excludedRoleIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTeamRoles(groupId, excludedRoleIds);
	}

	/**
	* Returns all the roles of the type.
	*
	* @param type the role's type (optionally <code>0</code>)
	* @return the range of the roles of the type
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getTypeRoles(
		int type) {
		return getService().getTypeRoles(type);
	}

	/**
	* Returns a range of all the roles of the type.
	*
	* @param type the role's type (optionally <code>0</code>)
	* @param start the lower bound of the range of roles to return
	* @param end the upper bound of the range of roles to return (not
	inclusive)
	* @return the range of the roles of the type
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getTypeRoles(
		int type, int start, int end) {
		return getService().getTypeRoles(type, start, end);
	}

	/**
	* Returns all the user's roles within the user group.
	*
	* @param userId the primary key of the user
	* @param groupId the primary key of the group
	* @return the user's roles within the user group
	* @see com.liferay.portal.kernel.service.persistence.RoleFinder#findByUserGroupGroupRole(
	long, long)
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getUserGroupGroupRoles(
		long userId, long groupId) {
		return getService().getUserGroupGroupRoles(userId, groupId);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Role> getUserGroupGroupRoles(
		long userId, long groupId, int start, int end) {
		return getService().getUserGroupGroupRoles(userId, groupId, start, end);
	}

	/**
	* Returns all the user's roles within the user group.
	*
	* @param userId the primary key of the user
	* @param groupId the primary key of the group
	* @return the user's roles within the user group
	* @see com.liferay.portal.kernel.service.persistence.RoleFinder#findByUserGroupRole(
	long, long)
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getUserGroupRoles(
		long userId, long groupId) {
		return getService().getUserGroupRoles(userId, groupId);
	}

	/**
	* Returns the union of all the user's roles within the groups.
	*
	* @param userId the primary key of the user
	* @param groups the groups (optionally <code>null</code>)
	* @return the union of all the user's roles within the groups
	* @see com.liferay.portal.kernel.service.persistence.RoleFinder#findByU_G(
	long, List)
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getUserRelatedRoles(
		long userId,
		java.util.List<com.liferay.portal.kernel.model.Group> groups) {
		return getService().getUserRelatedRoles(userId, groups);
	}

	/**
	* Returns all the user's roles within the group.
	*
	* @param userId the primary key of the user
	* @param groupId the primary key of the group
	* @return the user's roles within the group
	* @see com.liferay.portal.kernel.service.persistence.RoleFinder#findByU_G(
	long, long)
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getUserRelatedRoles(
		long userId, long groupId) {
		return getService().getUserRelatedRoles(userId, groupId);
	}

	/**
	* Returns the union of all the user's roles within the groups.
	*
	* @param userId the primary key of the user
	* @param groupIds the primary keys of the groups
	* @return the union of all the user's roles within the groups
	* @see com.liferay.portal.kernel.service.persistence.RoleFinder#findByU_G(
	long, long[])
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> getUserRelatedRoles(
		long userId, long[] groupIds) {
		return getService().getUserRelatedRoles(userId, groupIds);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Role> getUserRoles(
		long userId) {
		return getService().getUserRoles(userId);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Role> getUserRoles(
		long userId, int start, int end) {
		return getService().getUserRoles(userId, start, end);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Role> getUserRoles(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role> orderByComparator) {
		return getService().getUserRoles(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the roles that match the keywords and
	* types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param keywords the keywords (space separated), which may occur in the
	role's name or description (optionally <code>null</code>)
	* @param types the role types (optionally <code>null</code>)
	* @param start the lower bound of the range of roles to return
	* @param end the upper bound of the range of roles to return (not
	inclusive)
	* @param obc the comparator to order the roles (optionally
	<code>null</code>)
	* @return the ordered range of the matching roles, ordered by
	<code>obc</code>
	* @see com.liferay.portal.kernel.service.persistence.RoleFinder
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> search(
		long companyId, java.lang.String keywords, java.lang.Integer[] types,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role> obc) {
		return getService().search(companyId, keywords, types, start, end, obc);
	}

	/**
	* Returns an ordered range of all the roles that match the keywords, types,
	* and params.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param keywords the keywords (space separated), which may occur in the
	role's name or description (optionally <code>null</code>)
	* @param types the role types (optionally <code>null</code>)
	* @param params the finder parameters. Can specify values for the
	"usersRoles" key. For more information, see {@link
	com.liferay.portal.kernel.service.persistence.RoleFinder}
	* @param start the lower bound of the range of roles to return
	* @param end the upper bound of the range of roles to return (not
	inclusive)
	* @param obc the comparator to order the roles (optionally
	<code>null</code>)
	* @return the ordered range of the matching roles, ordered by
	<code>obc</code>
	* @see com.liferay.portal.kernel.service.persistence.RoleFinder
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> search(
		long companyId, java.lang.String keywords, java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role> obc) {
		return getService()
				   .search(companyId, keywords, types, params, start, end, obc);
	}

	/**
	* Returns an ordered range of all the roles that match the name,
	* description, and types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the role's name (optionally <code>null</code>)
	* @param description the role's description (optionally <code>null</code>)
	* @param types the role types (optionally <code>null</code>)
	* @param start the lower bound of the range of the roles to return
	* @param end the upper bound of the range of the roles to return (not
	inclusive)
	* @param obc the comparator to order the roles (optionally
	<code>null</code>)
	* @return the ordered range of the matching roles, ordered by
	<code>obc</code>
	* @see com.liferay.portal.kernel.service.persistence.RoleFinder
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> search(
		long companyId, java.lang.String name, java.lang.String description,
		java.lang.Integer[] types, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role> obc) {
		return getService()
				   .search(companyId, name, description, types, start, end, obc);
	}

	/**
	* Returns an ordered range of all the roles that match the name,
	* description, types, and params.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the role's name (optionally <code>null</code>)
	* @param description the role's description (optionally <code>null</code>)
	* @param types the role types (optionally <code>null</code>)
	* @param params the finder's parameters. Can specify values for the
	"usersRoles" key. For more information, see {@link
	com.liferay.portal.kernel.service.persistence.RoleFinder}
	* @param start the lower bound of the range of the roles to return
	* @param end the upper bound of the range of the roles to return (not
	inclusive)
	* @param obc the comparator to order the roles (optionally
	<code>null</code>)
	* @return the ordered range of the matching roles, ordered by
	<code>obc</code>
	* @see com.liferay.portal.kernel.service.persistence.RoleFinder
	*/
	public static java.util.List<com.liferay.portal.kernel.model.Role> search(
		long companyId, java.lang.String name, java.lang.String description,
		java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role> obc) {
		return getService()
				   .search(companyId, name, description, types, params, start,
			end, obc);
	}

	/**
	* Returns a map of role names to associated action IDs for the named
	* resource in the company within the permission scope.
	*
	* @param companyId the primary key of the company
	* @param name the resource name
	* @param scope the permission scope
	* @param primKey the primary key of the resource's class
	* @return the role names and action IDs
	* @see com.liferay.portal.kernel.service.persistence.RoleFinder#findByC_N_S_P(
	long, String, int, String)
	*/
	public static java.util.Map<java.lang.String, java.util.List<java.lang.String>> getResourceRoles(
		long companyId, java.lang.String name, int scope,
		java.lang.String primKey) {
		return getService().getResourceRoles(companyId, name, scope, primKey);
	}

	/**
	* Returns the team role map for the group.
	*
	* @param groupId the primary key of the group
	* @return the team role map for the group
	*/
	public static java.util.Map<com.liferay.portal.kernel.model.Team, com.liferay.portal.kernel.model.Role> getTeamRoleMap(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTeamRoleMap(groupId);
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
	* Returns the groupIds of the groups associated with the role.
	*
	* @param roleId the roleId of the role
	* @return long[] the groupIds of groups associated with the role
	*/
	public static long[] getGroupPrimaryKeys(long roleId) {
		return getService().getGroupPrimaryKeys(roleId);
	}

	/**
	* Returns the userIds of the users associated with the role.
	*
	* @param roleId the roleId of the role
	* @return long[] the userIds of users associated with the role
	*/
	public static long[] getUserPrimaryKeys(long roleId) {
		return getService().getUserPrimaryKeys(roleId);
	}

	public static void addGroupRole(long groupId,
		com.liferay.portal.kernel.model.Role role) {
		getService().addGroupRole(groupId, role);
	}

	public static void addGroupRole(long groupId, long roleId) {
		getService().addGroupRole(groupId, roleId);
	}

	public static void addGroupRoles(long groupId,
		java.util.List<com.liferay.portal.kernel.model.Role> roles) {
		getService().addGroupRoles(groupId, roles);
	}

	public static void addGroupRoles(long groupId, long[] roleIds) {
		getService().addGroupRoles(groupId, roleIds);
	}

	/**
	* @throws PortalException
	*/
	public static void addUserRole(long userId,
		com.liferay.portal.kernel.model.Role role)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().addUserRole(userId, role);
	}

	/**
	* @throws PortalException
	*/
	public static void addUserRole(long userId, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().addUserRole(userId, roleId);
	}

	/**
	* @throws PortalException
	*/
	public static void addUserRoles(long userId,
		java.util.List<com.liferay.portal.kernel.model.Role> roles)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().addUserRoles(userId, roles);
	}

	/**
	* @throws PortalException
	*/
	public static void addUserRoles(long userId, long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().addUserRoles(userId, roleIds);
	}

	/**
	* Checks to ensure that the system roles map has appropriate default roles
	* in each company.
	*/
	public static void checkSystemRoles()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().checkSystemRoles();
	}

	/**
	* Checks to ensure that the system roles map has appropriate default roles
	* in the company.
	*
	* @param companyId the primary key of the company
	*/
	public static void checkSystemRoles(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().checkSystemRoles(companyId);
	}

	public static void clearGroupRoles(long groupId) {
		getService().clearGroupRoles(groupId);
	}

	/**
	* @throws PortalException
	*/
	public static void clearUserRoles(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().clearUserRoles(userId);
	}

	public static void deleteGroupRole(long groupId,
		com.liferay.portal.kernel.model.Role role) {
		getService().deleteGroupRole(groupId, role);
	}

	public static void deleteGroupRole(long groupId, long roleId) {
		getService().deleteGroupRole(groupId, roleId);
	}

	public static void deleteGroupRoles(long groupId,
		java.util.List<com.liferay.portal.kernel.model.Role> roles) {
		getService().deleteGroupRoles(groupId, roles);
	}

	public static void deleteGroupRoles(long groupId, long[] roleIds) {
		getService().deleteGroupRoles(groupId, roleIds);
	}

	/**
	* @throws PortalException
	*/
	public static void deleteUserRole(long userId,
		com.liferay.portal.kernel.model.Role role)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteUserRole(userId, role);
	}

	/**
	* @throws PortalException
	*/
	public static void deleteUserRole(long userId, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteUserRole(userId, roleId);
	}

	/**
	* @throws PortalException
	*/
	public static void deleteUserRoles(long userId,
		java.util.List<com.liferay.portal.kernel.model.Role> roles)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteUserRoles(userId, roles);
	}

	/**
	* @throws PortalException
	*/
	public static void deleteUserRoles(long userId, long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteUserRoles(userId, roleIds);
	}

	public static void setGroupRoles(long groupId, long[] roleIds) {
		getService().setGroupRoles(groupId, roleIds);
	}

	/**
	* @throws PortalException
	*/
	public static void setUserRoles(long userId, long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().setUserRoles(userId, roleIds);
	}

	/**
	* Removes the matching roles associated with the user. The user is
	* reindexed after the roles are removed.
	*
	* @param userId the primary key of the user
	* @param roleIds the primary keys of the roles
	*/
	public static void unsetUserRoles(long userId, long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().unsetUserRoles(userId, roleIds);
	}

	public static RoleLocalService getService() {
		if (_service == null) {
			_service = (RoleLocalService)PortalBeanLocatorUtil.locate(RoleLocalService.class.getName());

			ReferenceRegistry.registerReference(RoleLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static RoleLocalService _service;
}