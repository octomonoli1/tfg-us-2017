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
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.Resource;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the local service interface for Resource. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ResourceLocalServiceUtil
 * @see com.liferay.portal.service.base.ResourceLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.ResourceLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ResourceLocalService extends BaseLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ResourceLocalServiceUtil} to access the resource local service. Add custom service methods to {@link com.liferay.portal.service.impl.ResourceLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns <code>true</code> if the roles have permission to perform the
	* action on the resources.
	*
	* @param userId the primary key of the user performing the permission
	check
	* @param resourceId the primary key of the resource, typically the scope
	group ID representing the scope in which the permission check is
	being performed
	* @param resources the resources for which permissions are to be checked
	* @param actionId the primary key of the action to be performed on the
	resources
	* @param roleIds the primary keys of the roles
	* @return <code>true</code> if the roles have permission to perform the
	action on the resources;<code>false</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserPermissions(long userId, long resourceId,
		List<Resource> resources, java.lang.String actionId, long[] roleIds)
		throws PortalException;

	/**
	* Returns a new resource with the name and primary key at the scope.
	*
	* @param companyId the primary key of the portal instance
	* @param name a name for the resource, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param scope the scope of the resource. For more information see {@link
	ResourceConstants}.
	* @param primKey the primary key string of the resource
	* @return the new resource
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Resource getResource(long companyId, java.lang.String name,
		int scope, java.lang.String primKey);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Adds resources for the model, always creating a resource at the
	* individual scope and only creating resources at the group, group
	* template, and company scope if such resources don't already exist.
	*
	* <ol>
	* <li>
	* If the service context specifies that default group or default guest
	* permissions are to be added, then only default permissions are added. See
	* {@link ServiceContext#setAddGroupPermissions(
	* boolean)} and {@link
	* ServiceContext#setAddGuestPermissions(
	* boolean)}.
	* </li>
	* <li>
	* Else ...
	* <ol>
	* <li>
	* If the service context specifies to derive default permissions, then
	* default group and guest permissions are derived from the model and
	* added. See {@link
	* ServiceContext#setDeriveDefaultPermissions(
	* boolean)}.
	* </li>
	* <li>
	* Lastly group and guest permissions from the service
	* context are applied. See {@link
	* ServiceContext#setGroupPermissions(String[])}
	* and {@link
	* ServiceContext#setGuestPermissions(String[])}.
	* </li>
	* </ol>
	*
	* </li>
	* </ol>
	*
	* @param auditedModel the model to associate with the resources
	* @param serviceContext the service context to apply. Can set whether to
	add the model's default group and guest permissions, set whether
	to derive default group and guest permissions from the model, set
	group permissions to apply, and set guest permissions to apply.
	*/
	public void addModelResources(AuditedModel auditedModel,
		ServiceContext serviceContext) throws PortalException;

	public void addModelResources(long companyId, long groupId, long userId,
		java.lang.String name, java.lang.String primKey,
		ModelPermissions modelPermissions) throws PortalException;

	/**
	* Adds resources for the model with the name and primary key string, always
	* creating a resource at the individual scope and only creating resources
	* at the group, group template, and company scope if such resources don't
	* already exist.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param userId the primary key of the user adding the resources
	* @param name a name for the resource, typically the model's class name
	* @param primKey the primary key string of the model instance, optionally
	an empty string if no instance exists
	* @param groupPermissions the group permissions to be applied
	* @param guestPermissions the guest permissions to be applied
	*/
	public void addModelResources(long companyId, long groupId, long userId,
		java.lang.String name, java.lang.String primKey,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws PortalException;

	public void addModelResources(long companyId, long groupId, long userId,
		java.lang.String name, long primKey, ModelPermissions modelPermissions)
		throws PortalException;

	/**
	* Adds resources for the model with the name and primary key, always
	* creating a resource at the individual scope and only creating resources
	* at the group, group template, and company scope if such resources don't
	* already exist.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param userId the primary key of the user adding the resources
	* @param name a name for the resource, typically the model's class name
	* @param primKey the primary key of the model instance, optionally
	<code>0</code> if no instance exists
	* @param groupPermissions the group permissions to be applied
	* @param guestPermissions the guest permissions to be applied
	*/
	public void addModelResources(long companyId, long groupId, long userId,
		java.lang.String name, long primKey,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws PortalException;

	/**
	* Adds resources for the entity with the name. Use this method if the user
	* is unknown or irrelevant and there is no current entity instance.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param name a name for the resource, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param portletActions whether to associate portlet actions with the
	resource
	*/
	public void addResources(long companyId, long groupId,
		java.lang.String name, boolean portletActions)
		throws PortalException;

	/**
	* Adds resources for the entity with the name and primary key string,
	* always creating a resource at the individual scope and only creating
	* resources at the group, group template, and company scope if such
	* resources don't already exist.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param userId the primary key of the user adding the resources
	* @param name a name for the resource, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param primKey the primary key string of the resource instance,
	optionally an empty string if no instance exists
	* @param portletActions whether to associate portlet actions with the
	resource
	* @param addGroupPermissions whether to add group permissions
	* @param addGuestPermissions whether to add guest permissions
	*/
	public void addResources(long companyId, long groupId, long userId,
		java.lang.String name, java.lang.String primKey,
		boolean portletActions, boolean addGroupPermissions,
		boolean addGuestPermissions) throws PortalException;

	/**
	* Adds resources for the entity with the name and primary key, always
	* creating a resource at the individual scope and only creating resources
	* at the group, group template, and company scope if such resources don't
	* already exist.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param userId the primary key of the user adding the resources
	* @param name a name for the resource, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param primKey the primary key of the resource instance, optionally
	<code>0</code> if no instance exists
	* @param portletActions whether to associate portlet actions with the
	resource
	* @param addGroupPermissions whether to add group permissions
	* @param addGuestPermissions whether to add guest permissions
	*/
	public void addResources(long companyId, long groupId, long userId,
		java.lang.String name, long primKey, boolean portletActions,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws PortalException;

	/**
	* Deletes the resource associated with the model at the scope.
	*
	* @param auditedModel the model associated with the resource
	* @param scope the scope of the resource. For more information see {@link
	ResourceConstants}.
	*/
	public void deleteResource(AuditedModel auditedModel, int scope)
		throws PortalException;

	/**
	* Deletes the resource matching the primary key at the scope.
	*
	* @param companyId the primary key of the portal instance
	* @param name the resource's name, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param scope the scope of the resource. For more information see {@link
	ResourceConstants}.
	* @param primKey the primary key string of the resource instance
	*/
	public void deleteResource(long companyId, java.lang.String name,
		int scope, java.lang.String primKey) throws PortalException;

	/**
	* Deletes the resource matching the primary key at the scope.
	*
	* @param companyId the primary key of the portal instance
	* @param name the resource's name, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param scope the scope of the resource. For more information see {@link
	ResourceConstants}.
	* @param primKey the primary key of the resource instance
	*/
	public void deleteResource(long companyId, java.lang.String name,
		int scope, long primKey) throws PortalException;

	/**
	* Updates the resources for the model, replacing their group and guest
	* permissions with new ones from the service context.
	*
	* @param auditedModel the model associated with the resources
	* @param serviceContext the service context to be applied. Can set group
	and guest permissions.
	*/
	public void updateModelResources(AuditedModel auditedModel,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Updates resources matching the name, primary key string and scope,
	* replacing the primary key of their resource permissions with the new
	* primary key.
	*
	* @param companyId the primary key of the portal instance
	* @param name the resource's name, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param scope the scope of the resource. For more information see {@link
	ResourceConstants}.
	* @param primKey the primary key string of the resource instance
	* @param newPrimKey the new primary key string of the resource
	*/
	public void updateResources(long companyId, java.lang.String name,
		int scope, java.lang.String primKey, java.lang.String newPrimKey);

	/**
	* Updates resources matching the group, name, and primary key string at the
	* individual scope, setting new permissions.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param name the resource's name, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param primKey the primary key string of the resource instance
	* @param modelPermissions the model permissions to be applied
	*/
	public void updateResources(long companyId, long groupId,
		java.lang.String name, java.lang.String primKey,
		ModelPermissions modelPermissions) throws PortalException;

	/**
	* Updates resources matching the group, name, and primary key string at the
	* individual scope, setting new group and guest permissions.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param name the resource's name, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param primKey the primary key string of the resource instance
	* @param groupPermissions the group permissions to be applied
	* @param guestPermissions the guest permissions to be applied
	*/
	public void updateResources(long companyId, long groupId,
		java.lang.String name, java.lang.String primKey,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws PortalException;

	/**
	* Updates resources matching the group, name, and primary key at the
	* individual scope, setting new permissions.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param name the resource's name, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param primKey the primary key of the resource instance
	* @param modelPermissions the model permissions to be applied
	*/
	public void updateResources(long companyId, long groupId,
		java.lang.String name, long primKey, ModelPermissions modelPermissions)
		throws PortalException;

	/**
	* Updates resources matching the group, name, and primary key at the
	* individual scope, setting new group and guest permissions.
	*
	* @param companyId the primary key of the portal instance
	* @param groupId the primary key of the group
	* @param name the resource's name, which should be a portlet ID if the
	resource is a portlet or the resource's class name otherwise
	* @param primKey the primary key of the resource instance
	* @param groupPermissions the group permissions to be applied
	* @param guestPermissions the guest permissions to be applied
	*/
	public void updateResources(long companyId, long groupId,
		java.lang.String name, long primKey,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws PortalException;
}