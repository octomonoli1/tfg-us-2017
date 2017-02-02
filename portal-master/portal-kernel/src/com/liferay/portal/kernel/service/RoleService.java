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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service interface for Role. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see RoleServiceUtil
 * @see com.liferay.portal.service.base.RoleServiceBaseImpl
 * @see com.liferay.portal.service.impl.RoleServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface RoleService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RoleServiceUtil} to access the role remote service. Add custom service methods to {@link com.liferay.portal.service.impl.RoleServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserRole(long userId, long companyId,
		java.lang.String name, boolean inherited) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserRoles(long userId, long companyId,
		java.lang.String[] names, boolean inherited) throws PortalException;

	/**
	* Adds a role. The user is reindexed after role is added.
	*
	* @param className the name of the class for which the role is created
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
	<code>null</code>). Can set the expando bridge attributes for the
	role.
	* @return the role
	*/
	public Role addRole(java.lang.String className, long classPK,
		java.lang.String name, Map<Locale, java.lang.String> titleMap,
		Map<Locale, java.lang.String> descriptionMap, int type,
		java.lang.String subtype, ServiceContext serviceContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role fetchRole(long roleId) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getRole(long companyId, java.lang.String name)
		throws PortalException;

	/**
	* Returns the role with the primary key.
	*
	* @param roleId the primary key of the role
	* @return the role with the primary key
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getRole(long roleId) throws PortalException;

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
	<code>null</code>). Can set the expando bridge attributes for the
	role.
	* @return the role with the primary key
	*/
	public Role updateRole(long roleId, java.lang.String name,
		Map<Locale, java.lang.String> titleMap,
		Map<Locale, java.lang.String> descriptionMap, java.lang.String subtype,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, java.lang.String keywords,
		java.lang.Integer[] types,
		LinkedHashMap<java.lang.String, java.lang.Object> params);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Returns all the roles associated with the group.
	*
	* @param groupId the primary key of the group
	* @return the roles associated with the group
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> getGroupRoles(long groupId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> getRoles(int type, java.lang.String subtype)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> getRoles(long companyId, int[] types)
		throws PortalException;

	/**
	* Returns all the user's roles within the user group.
	*
	* @param userId the primary key of the user
	* @param groupId the primary key of the group
	* @return the user's roles within the user group
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> getUserGroupGroupRoles(long userId, long groupId)
		throws PortalException;

	/**
	* Returns all the user's roles within the user group.
	*
	* @param userId the primary key of the user
	* @param groupId the primary key of the group
	* @return the user's roles within the user group
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> getUserGroupRoles(long userId, long groupId)
		throws PortalException;

	/**
	* Returns the union of all the user's roles within the groups.
	*
	* @param userId the primary key of the user
	* @param groups the groups (optionally <code>null</code>)
	* @return the union of all the user's roles within the groups
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> getUserRelatedRoles(long userId, List<Group> groups)
		throws PortalException;

	/**
	* Returns all the roles associated with the user.
	*
	* @param userId the primary key of the user
	* @return the roles associated with the user
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> getUserRoles(long userId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> search(long companyId, java.lang.String keywords,
		java.lang.Integer[] types,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, OrderByComparator<Role> obc);

	/**
	* Adds the roles to the user. The user is reindexed after the roles are
	* added.
	*
	* @param userId the primary key of the user
	* @param roleIds the primary keys of the roles
	*/
	public void addUserRoles(long userId, long[] roleIds)
		throws PortalException;

	/**
	* Deletes the role with the primary key and its associated permissions.
	*
	* @param roleId the primary key of the role
	*/
	public void deleteRole(long roleId) throws PortalException;

	/**
	* Removes the matching roles associated with the user. The user is
	* reindexed after the roles are removed.
	*
	* @param userId the primary key of the user
	* @param roleIds the primary keys of the roles
	*/
	public void unsetUserRoles(long userId, long[] roleIds)
		throws PortalException;
}