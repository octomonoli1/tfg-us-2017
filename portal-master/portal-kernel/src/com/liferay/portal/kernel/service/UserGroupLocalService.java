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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for UserGroup. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupLocalServiceUtil
 * @see com.liferay.portal.service.base.UserGroupLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.UserGroupLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface UserGroupLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserGroupLocalServiceUtil} to access the user group local service. Add custom service methods to {@link com.liferay.portal.service.impl.UserGroupLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasGroupUserGroup(long groupId, long userGroupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasGroupUserGroups(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTeamUserGroup(long teamId, long userGroupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTeamUserGroups(long teamId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserUserGroup(long userId, long userGroupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserUserGroups(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Adds the user group to the database. Also notifies the appropriate model listeners.
	*
	* @param userGroup the user group
	* @return the user group that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public UserGroup addUserGroup(UserGroup userGroup);

	/**
	* Adds a user group.
	*
	* <p>
	* This method handles the creation and bookkeeping of the user group,
	* including its resources, metadata, and internal data structures. It is
	* not necessary to make subsequent calls to setup default groups and
	* resources for the user group.
	* </p>
	*
	* @param userId the primary key of the user
	* @param companyId the primary key of the user group's company
	* @param name the user group's name
	* @param description the user group's description
	* @return the user group
	* @deprecated As of 6.2.0, replaced by {@link #addUserGroup(long, long,
	String, String, ServiceContext)}
	*/
	@java.lang.Deprecated
	public UserGroup addUserGroup(long userId, long companyId,
		java.lang.String name, java.lang.String description)
		throws PortalException;

	/**
	* Adds a user group.
	*
	* <p>
	* This method handles the creation and bookkeeping of the user group,
	* including its resources, metadata, and internal data structures. It is
	* not necessary to make subsequent calls to setup default groups and
	* resources for the user group.
	* </p>
	*
	* @param userId the primary key of the user
	* @param companyId the primary key of the user group's company
	* @param name the user group's name
	* @param description the user group's description
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set expando bridge attributes for the
	user group.
	* @return the user group
	*/
	public UserGroup addUserGroup(long userId, long companyId,
		java.lang.String name, java.lang.String description,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Creates a new user group with the primary key. Does not add the user group to the database.
	*
	* @param userGroupId the primary key for the new user group
	* @return the new user group
	*/
	public UserGroup createUserGroup(long userGroupId);

	/**
	* Deletes the user group from the database. Also notifies the appropriate model listeners.
	*
	* @param userGroup the user group
	* @return the user group that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	@SystemEvent(action = SystemEventConstants.ACTION_SKIP, type = SystemEventConstants.TYPE_DELETE)
	public UserGroup deleteUserGroup(UserGroup userGroup)
		throws PortalException;

	/**
	* Deletes the user group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userGroupId the primary key of the user group
	* @return the user group that was removed
	* @throws PortalException if a user group with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public UserGroup deleteUserGroup(long userGroupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserGroup fetchUserGroup(long companyId, java.lang.String name);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserGroup fetchUserGroup(long userGroupId);

	/**
	* Returns the user group with the matching UUID and company.
	*
	* @param uuid the user group's UUID
	* @param companyId the primary key of the company
	* @return the matching user group, or <code>null</code> if a matching user group could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserGroup fetchUserGroupByUuidAndCompanyId(java.lang.String uuid,
		long companyId);

	/**
	* Returns the user group with the name.
	*
	* @param companyId the primary key of the user group's company
	* @param name the user group's name
	* @return Returns the user group with the name
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserGroup getUserGroup(long companyId, java.lang.String name)
		throws PortalException;

	/**
	* Returns the user group with the primary key.
	*
	* @param userGroupId the primary key of the user group
	* @return the user group
	* @throws PortalException if a user group with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserGroup getUserGroup(long userGroupId) throws PortalException;

	/**
	* Returns the user group with the matching UUID and company.
	*
	* @param uuid the user group's UUID
	* @param companyId the primary key of the company
	* @return the matching user group
	* @throws PortalException if a matching user group could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserGroup getUserGroupByUuidAndCompanyId(java.lang.String uuid,
		long companyId) throws PortalException;

	/**
	* Updates the user group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userGroup the user group
	* @return the user group that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public UserGroup updateUserGroup(UserGroup userGroup);

	/**
	* Updates the user group.
	*
	* @param companyId the primary key of the user group's company
	* @param userGroupId the primary key of the user group
	* @param name the user group's name
	* @param description the user group's description
	* @return the user group
	* @deprecated As of 6.2.0, replaced by {@link #updateUserGroup(long, long,
	String, String, ServiceContext)}
	*/
	@java.lang.Deprecated
	public UserGroup updateUserGroup(long companyId, long userGroupId,
		java.lang.String name, java.lang.String description)
		throws PortalException;

	/**
	* Updates the user group.
	*
	* @param companyId the primary key of the user group's company
	* @param userGroupId the primary key of the user group
	* @param name the user group's name
	* @param description the user group's description
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set expando bridge attributes for the
	user group.
	* @return the user group
	*/
	public UserGroup updateUserGroup(long companyId, long userGroupId,
		java.lang.String name, java.lang.String description,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<UserGroup> searchUserGroups(long companyId,
		java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, Sort sort) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<UserGroup> searchUserGroups(long companyId,
		java.lang.String name, java.lang.String description,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, Sort sort)
		throws PortalException;

	/**
	* Returns an ordered range of all the user groups that match the keywords,
	* using the indexer. It is preferable to use this method instead of the
	* non-indexed version whenever possible for performance reasons.
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
	* @param companyId the primary key of the user group's company
	* @param keywords the keywords (space separated), which may occur in the
	user group's name or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). For more
	information see {@link
	com.liferay.user.groups.admin.web.search.UserGroupIndexer}
	* @param start the lower bound of the range of user groups to return
	* @param end the upper bound of the range of user groups to return (not
	inclusive)
	* @param sort the field and direction by which to sort (optionally
	<code>null</code>)
	* @return the matching user groups ordered by sort
	* @see com.liferay.user.groups.admin.web.search.UserGroupIndexer
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, Sort sort);

	/**
	* Returns an ordered range of all the user groups that match the name and
	* description. It is preferable to use this method instead of the
	* non-indexed version whenever possible for performance reasons.
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
	* @param companyId the primary key of the user group's company
	* @param name the user group's name (optionally <code>null</code>)
	* @param description the user group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). For more
	information see {@link
	com.liferay.user.groups.admin.web.search.UserGroupIndexer}
	* @param andSearch whether every field must match its keywords or just one
	field
	* @param start the lower bound of the range of user groups to return
	* @param end the upper bound of the range of user groups to return (not
	inclusive)
	* @param sort the field and direction by which to sort (optionally
	<code>null</code>)
	* @return the matching user groups ordered by sort
	* @see com.liferay.portal.kernel.service.persistence.UserGroupFinder
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, java.lang.String name,
		java.lang.String description,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, Sort sort);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupUserGroupsCount(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTeamUserGroupsCount(long teamId);

	/**
	* Returns the number of user groups.
	*
	* @return the number of user groups
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserGroupsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserUserGroupsCount(long userId);

	/**
	* Returns the number of user groups that match the keywords
	*
	* @param companyId the primary key of the user group's company
	* @param keywords the keywords (space separated), which may occur in the
	user group's name or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.UserGroupFinder}
	* @return the number of matching user groups
	* @see com.liferay.portal.kernel.service.persistence.UserGroupFinder
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params);

	/**
	* Returns the number of user groups that match the name and description.
	*
	* @param companyId the primary key of the user group's company
	* @param name the user group's name (optionally <code>null</code>)
	* @param description the user group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.UserGroupFinder}
	* @param andOperator whether every field must match its keywords or just
	one field
	* @return the number of matching user groups
	* @see com.liferay.portal.kernel.service.persistence.UserGroupFinder
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, java.lang.String name,
		java.lang.String description,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> getGroupUserGroups(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> getGroupUserGroups(long groupId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> getGroupUserGroups(long groupId, int start, int end,
		OrderByComparator<UserGroup> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> getGroupUserUserGroups(long groupId, long userId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> getTeamUserGroups(long teamId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> getTeamUserGroups(long teamId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> getTeamUserGroups(long teamId, int start, int end,
		OrderByComparator<UserGroup> orderByComparator);

	/**
	* Returns a range of all the user groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user groups
	* @param end the upper bound of the range of user groups (not inclusive)
	* @return the range of user groups
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> getUserGroups(int start, int end);

	/**
	* Returns all the user groups belonging to the company.
	*
	* @param companyId the primary key of the user groups' company
	* @return the user groups belonging to the company
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> getUserGroups(long companyId);

	/**
	* Returns all the user groups with the primary keys.
	*
	* @param userGroupIds the primary keys of the user groups
	* @return the user groups with the primary keys
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> getUserGroups(long[] userGroupIds)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> getUserUserGroups(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> getUserUserGroups(long userId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> getUserUserGroups(long userId, int start, int end,
		OrderByComparator<UserGroup> orderByComparator);

	/**
	* Returns an ordered range of all the user groups that match the keywords.
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
	* @param companyId the primary key of the user group's company
	* @param keywords the keywords (space separated), which may occur in the
	user group's name or description (optionally <code>null</code>)
	* @param params the finder params (optionally <code>null</code>). For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.UserGroupFinder}
	* @param start the lower bound of the range of user groups to return
	* @param end the upper bound of the range of user groups to return (not
	inclusive)
	* @param obc the comparator to order the user groups (optionally
	<code>null</code>)
	* @return the matching user groups ordered by comparator <code>obc</code>
	* @see com.liferay.portal.kernel.service.persistence.UserGroupFinder
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> search(long companyId, java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, OrderByComparator<UserGroup> obc);

	/**
	* Returns an ordered range of all the user groups that match the name and
	* description.
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
	* @param companyId the primary key of the user group's company
	* @param name the user group's name (optionally <code>null</code>)
	* @param description the user group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). For more
	information see {@link
	com.liferay.portal.kernel.service.persistence.UserGroupFinder}
	* @param andOperator whether every field must match its keywords or just
	one field
	* @param start the lower bound of the range of user groups to return
	* @param end the upper bound of the range of user groups to return (not
	inclusive)
	* @param obc the comparator to order the user groups (optionally
	<code>null</code>)
	* @return the matching user groups ordered by comparator <code>obc</code>
	* @see com.liferay.portal.kernel.service.persistence.UserGroupFinder
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroup> search(long companyId, java.lang.String name,
		java.lang.String description,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		OrderByComparator<UserGroup> obc);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* Returns the groupIds of the groups associated with the user group.
	*
	* @param userGroupId the userGroupId of the user group
	* @return long[] the groupIds of groups associated with the user group
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getGroupPrimaryKeys(long userGroupId);

	/**
	* Returns the teamIds of the teams associated with the user group.
	*
	* @param userGroupId the userGroupId of the user group
	* @return long[] the teamIds of teams associated with the user group
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTeamPrimaryKeys(long userGroupId);

	/**
	* Returns the userIds of the users associated with the user group.
	*
	* @param userGroupId the userGroupId of the user group
	* @return long[] the userIds of users associated with the user group
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getUserPrimaryKeys(long userGroupId);

	public void addGroupUserGroup(long groupId, UserGroup userGroup);

	public void addGroupUserGroup(long groupId, long userGroupId);

	public void addGroupUserGroups(long groupId, List<UserGroup> userGroups);

	public void addGroupUserGroups(long groupId, long[] userGroupIds);

	public void addTeamUserGroup(long teamId, UserGroup userGroup);

	public void addTeamUserGroup(long teamId, long userGroupId);

	public void addTeamUserGroups(long teamId, List<UserGroup> userGroups);

	public void addTeamUserGroups(long teamId, long[] userGroupIds);

	public void addUserUserGroup(long userId, UserGroup userGroup);

	public void addUserUserGroup(long userId, long userGroupId);

	public void addUserUserGroups(long userId, List<UserGroup> userGroups);

	public void addUserUserGroups(long userId, long[] userGroupIds);

	public void clearGroupUserGroups(long groupId);

	public void clearTeamUserGroups(long teamId);

	public void clearUserUserGroups(long userId);

	/**
	* Copies the user group's layout to the user.
	*
	* @param userGroupId the primary key of the user group
	* @param userId the primary key of the user
	* @deprecated As of 6.2.0
	*/
	@java.lang.Deprecated
	public void copyUserGroupLayouts(long userGroupId, long userId)
		throws PortalException;

	/**
	* Copies the user group's layouts to the users who are not already members
	* of the user group.
	*
	* @param userGroupId the primary key of the user group
	* @param userIds the primary keys of the users
	* @deprecated As of 6.1.0
	*/
	@java.lang.Deprecated
	public void copyUserGroupLayouts(long userGroupId, long[] userIds)
		throws PortalException;

	/**
	* Copies the user groups' layouts to the user.
	*
	* @param userGroupIds the primary keys of the user groups
	* @param userId the primary key of the user
	* @deprecated As of 6.1.0
	*/
	@java.lang.Deprecated
	public void copyUserGroupLayouts(long[] userGroupIds, long userId)
		throws PortalException;

	public void deleteGroupUserGroup(long groupId, UserGroup userGroup);

	public void deleteGroupUserGroup(long groupId, long userGroupId);

	public void deleteGroupUserGroups(long groupId, List<UserGroup> userGroups);

	public void deleteGroupUserGroups(long groupId, long[] userGroupIds);

	public void deleteTeamUserGroup(long teamId, UserGroup userGroup);

	public void deleteTeamUserGroup(long teamId, long userGroupId);

	public void deleteTeamUserGroups(long teamId, List<UserGroup> userGroups);

	public void deleteTeamUserGroups(long teamId, long[] userGroupIds);

	public void deleteUserGroups(long companyId) throws PortalException;

	public void deleteUserUserGroup(long userId, UserGroup userGroup);

	public void deleteUserUserGroup(long userId, long userGroupId);

	public void deleteUserUserGroups(long userId, List<UserGroup> userGroups);

	public void deleteUserUserGroups(long userId, long[] userGroupIds);

	public void setGroupUserGroups(long groupId, long[] userGroupIds);

	public void setTeamUserGroups(long teamId, long[] userGroupIds);

	/**
	* @throws PortalException
	*/
	public void setUserUserGroups(long userId, long[] userGroupIds)
		throws PortalException;

	/**
	* Removes the user groups from the group.
	*
	* @param groupId the primary key of the group
	* @param userGroupIds the primary keys of the user groups
	*/
	public void unsetGroupUserGroups(long groupId, long[] userGroupIds);

	/**
	* Removes the user groups from the team.
	*
	* @param teamId the primary key of the team
	* @param userGroupIds the primary keys of the user groups
	*/
	public void unsetTeamUserGroups(long teamId, long[] userGroupIds);
}