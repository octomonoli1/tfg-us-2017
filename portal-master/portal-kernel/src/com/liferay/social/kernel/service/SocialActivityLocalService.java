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

package com.liferay.social.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.model.AssetEntry;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.async.Async;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.social.kernel.model.SocialActivity;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for SocialActivity. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityLocalServiceUtil
 * @see com.liferay.portlet.social.service.base.SocialActivityLocalServiceBaseImpl
 * @see com.liferay.portlet.social.service.impl.SocialActivityLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SocialActivityLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SocialActivityLocalServiceUtil} to access the social activity local service. Add custom service methods to {@link com.liferay.portlet.social.service.impl.SocialActivityLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

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
	* Adds the social activity to the database. Also notifies the appropriate model listeners.
	*
	* @param socialActivity the social activity
	* @return the social activity that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SocialActivity addSocialActivity(SocialActivity socialActivity);

	/**
	* Creates a new social activity with the primary key. Does not add the social activity to the database.
	*
	* @param activityId the primary key for the new social activity
	* @return the new social activity
	*/
	public SocialActivity createSocialActivity(long activityId);

	/**
	* Deletes the social activity from the database. Also notifies the appropriate model listeners.
	*
	* @param socialActivity the social activity
	* @return the social activity that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public SocialActivity deleteSocialActivity(SocialActivity socialActivity);

	/**
	* Deletes the social activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityId the primary key of the social activity
	* @return the social activity that was removed
	* @throws PortalException if a social activity with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public SocialActivity deleteSocialActivity(long activityId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SocialActivity fetchFirstActivity(java.lang.String className,
		long classPK, int type);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SocialActivity fetchSocialActivity(long activityId);

	/**
	* Returns the activity identified by its primary key.
	*
	* @param activityId the primary key of the activity
	* @return Returns the activity
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SocialActivity getActivity(long activityId)
		throws PortalException;

	/**
	* Returns the activity that has the mirror activity.
	*
	* @param mirrorActivityId the primary key of the mirror activity
	* @return Returns the mirror activity
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SocialActivity getMirrorActivity(long mirrorActivityId)
		throws PortalException;

	/**
	* Returns the social activity with the primary key.
	*
	* @param activityId the primary key of the social activity
	* @return the social activity
	* @throws PortalException if a social activity with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SocialActivity getSocialActivity(long activityId)
		throws PortalException;

	/**
	* Updates the social activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param socialActivity the social activity
	* @return the social activity that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SocialActivity updateSocialActivity(SocialActivity socialActivity);

	/**
	* Returns the number of activities done on assets identified by class name.
	*
	* @param className the target asset's class name
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getActivitiesCount(java.lang.String className);

	/**
	* Returns the number of activities done on assets identified by the class
	* name ID.
	*
	* @param classNameId the target asset's class name ID
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getActivitiesCount(long classNameId);

	/**
	* Returns the number of activities done on the asset identified by the
	* class name and class primary key that are mirrors of the activity
	* identified by the mirror activity ID.
	*
	* @param mirrorActivityId the primary key of the mirror activity
	* @param className the target asset's class name
	* @param classPK the primary key of the target asset
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getActivitiesCount(long mirrorActivityId,
		java.lang.String className, long classPK);

	/**
	* Returns the number of activities done on the asset identified by the
	* class name ID and class primary key that are mirrors of the activity
	* identified by the mirror activity ID.
	*
	* @param mirrorActivityId the primary key of the mirror activity
	* @param classNameId the target asset's class name ID
	* @param classPK the primary key of the target asset
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getActivitiesCount(long mirrorActivityId, long classNameId,
		long classPK);

	/**
	* Returns the number of activities done in the group.
	*
	* <p>
	* This method only counts activities without mirrors.
	* </p>
	*
	* @param groupId the primary key of the group
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupActivitiesCount(long groupId);

	/**
	* Returns the number of activities done by users that are members of the
	* group.
	*
	* <p>
	* This method only counts activities without mirrors.
	* </p>
	*
	* @param groupId the primary key of the group
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupUsersActivitiesCount(long groupId);

	/**
	* Returns the number of activities done in the organization. This method
	* only counts activities without mirrors.
	*
	* @param organizationId the primary key of the organization
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOrganizationActivitiesCount(long organizationId);

	/**
	* Returns the number of activities done by users of the organization. This
	* method only counts activities without mirrors.
	*
	* @param organizationId the primary key of the organization
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOrganizationUsersActivitiesCount(long organizationId);

	/**
	* Returns the number of activities done by users in a relationship with the
	* user identified by userId.
	*
	* @param userId the primary key of the user
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRelationActivitiesCount(long userId);

	/**
	* Returns the number of activities done by users in a relationship of type
	* <code>type</code> with the user identified by <code>userId</code>. This
	* method only counts activities without mirrors.
	*
	* @param userId the primary key of the user
	* @param type the relationship type
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRelationActivitiesCount(long userId, int type);

	/**
	* Returns the number of social activities.
	*
	* @return the number of social activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSocialActivitiesCount();

	/**
	* Returns the number of activities done by the user.
	*
	* @param userId the primary key of the user
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserActivitiesCount(long userId);

	/**
	* Returns the number of activities done in user's groups. This method only
	* counts activities without mirrors.
	*
	* @param userId the primary key of the user
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserGroupsActivitiesCount(long userId);

	/**
	* Returns the number of activities done in user's groups and organizations.
	* This method only counts activities without mirrors.
	*
	* @param userId the primary key of the user
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserGroupsAndOrganizationsActivitiesCount(long userId);

	/**
	* Returns the number of activities done in the user's organizations. This
	* method only counts activities without mirrors.
	*
	* @param userId the primary key of the user
	* @return the number of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserOrganizationsActivitiesCount(long userId);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Returns a range of all the activities done on assets identified by the
	* class name.
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
	* @param className the target asset's class name
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getActivities(java.lang.String className,
		int start, int end);

	/**
	* Returns a range of all the activities done on assets identified by the
	* class name ID.
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
	* @param classNameId the target asset's class name ID
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getActivities(long classNameId, int start,
		int end);

	/**
	* Returns a range of all the activities done on the asset identified by the
	* class name and the class primary key that are mirrors of the activity
	* identified by the mirror activity ID.
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
	* @param mirrorActivityId the primary key of the mirror activity
	* @param className the target asset's class name
	* @param classPK the primary key of the target asset
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getActivities(long mirrorActivityId,
		java.lang.String className, long classPK, int start, int end);

	/**
	* Returns a range of all the activities done on the asset identified by the
	* class name ID and class primary key that are mirrors of the activity
	* identified by the mirror activity ID.
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
	* @param mirrorActivityId the primary key of the mirror activity
	* @param classNameId the target asset's class name ID
	* @param classPK the primary key of the target asset
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getActivities(long mirrorActivityId,
		long classNameId, long classPK, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getActivitySetActivities(long activitySetId,
		int start, int end);

	/**
	* Returns a range of all the activities done in the group.
	*
	* <p>
	* This method only finds activities without mirrors.
	* </p>
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
	* @param groupId the primary key of the group
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getGroupActivities(long groupId, int start,
		int end);

	/**
	* Returns a range of activities done by users that are members of the
	* group.
	*
	* <p>
	* This method only finds activities without mirrors.
	* </p>
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
	* @param groupId the primary key of the group
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getGroupUsersActivities(long groupId,
		int start, int end);

	/**
	* Returns a range of all the activities done in the organization. This
	* method only finds activities without mirrors.
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
	* @param organizationId the primary key of the organization
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getOrganizationActivities(long organizationId,
		int start, int end);

	/**
	* Returns a range of all the activities done by users of the organization.
	* This method only finds activities without mirrors.
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
	* @param organizationId the primary key of the organization
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getOrganizationUsersActivities(
		long organizationId, int start, int end);

	/**
	* Returns a range of all the activities done by users in a relationship
	* with the user identified by the user ID.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <>0</code> refers
	* to the first result in the set. Setting both <code>start</code> and
	* <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result
	* set.
	* </p>
	*
	* @param userId the primary key of the user
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getRelationActivities(long userId, int start,
		int end);

	/**
	* Returns a range of all the activities done by users in a relationship of
	* type <code>type</code> with the user identified by <code>userId</code>.
	* This method only finds activities without mirrors.
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
	* @param type the relationship type
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getRelationActivities(long userId, int type,
		int start, int end);

	/**
	* Returns a range of all the social activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @return the range of social activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getSocialActivities(int start, int end);

	/**
	* Returns a range of all the activities done by the user.
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
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getUserActivities(long userId, int start,
		int end);

	/**
	* Returns a range of all the activities done in the user's groups. This
	* method only finds activities without mirrors.
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
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getUserGroupsActivities(long userId, int start,
		int end);

	/**
	* Returns a range of all the activities done in the user's groups and
	* organizations. This method only finds activities without mirrors.
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
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getUserGroupsAndOrganizationsActivities(
		long userId, int start, int end);

	/**
	* Returns a range of all activities done in the user's organizations. This
	* method only finds activities without mirrors.
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
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SocialActivity> getUserOrganizationsActivities(long userId,
		int start, int end);

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

	@Async
	public void addActivity(SocialActivity activity,
		SocialActivity mirrorActivity) throws PortalException;

	/**
	* Records an activity in the database, using a time based on the current
	* time in an attempt to make the activity's time unique.
	*
	* @param userId the primary key of the acting user
	* @param groupId the primary key of the group
	* @param className the target asset's class name
	* @param classPK the primary key of the target asset
	* @param type the activity's type
	* @param extraData any extra data regarding the activity
	* @param receiverUserId the primary key of the receiving user
	*/
	public void addActivity(long userId, long groupId,
		java.lang.String className, long classPK, int type,
		java.lang.String extraData, long receiverUserId)
		throws PortalException;

	/**
	* Records an activity with the given time in the database.
	*
	* <p>
	* This method records a social activity done on an asset, identified by its
	* class name and class primary key, in the database. Additional information
	* (such as the original message ID for a reply to a forum post) is passed
	* in via the <code>extraData</code> in JSON format. For activities
	* affecting another user, a mirror activity is generated that describes the
	* action from the user's point of view. The target user's ID is passed in
	* via the <code>receiverUserId</code>.
	* </p>
	*
	* <p>
	* Example for a mirrored activity:<br> When a user replies to a message
	* boards post, the reply action is stored in the database with the
	* <code>receiverUserId</code> being the ID of the author of the original
	* message. The <code>extraData</code> contains the ID of the original
	* message in JSON format. A mirror activity is generated with the values of
	* the <code>userId</code> and the <code>receiverUserId</code> swapped. This
	* mirror activity basically describes a "replied to" event.
	* </p>
	*
	* <p>
	* Mirror activities are most often used in relation to friend requests and
	* activities.
	* </p>
	*
	* @param userId the primary key of the acting user
	* @param groupId the primary key of the group
	* @param createDate the activity's date
	* @param className the target asset's class name
	* @param classPK the primary key of the target asset
	* @param type the activity's type
	* @param extraData any extra data regarding the activity
	* @param receiverUserId the primary key of the receiving user
	*/
	public void addActivity(long userId, long groupId, Date createDate,
		java.lang.String className, long classPK, int type,
		java.lang.String extraData, long receiverUserId)
		throws PortalException;

	/**
	* Records an activity with the current time in the database, but only if
	* there isn't one with the same parameters.
	*
	* <p>
	* For the main functionality see {@link #addActivity(long, long, Date,
	* String, long, int, String, long)}
	* </p>
	*
	* @param userId the primary key of the acting user
	* @param groupId the primary key of the group
	* @param className the target asset's class name
	* @param classPK the primary key of the target asset
	* @param type the activity's type
	* @param extraData any extra data regarding the activity
	* @param receiverUserId the primary key of the receiving user
	*/
	public void addUniqueActivity(long userId, long groupId,
		java.lang.String className, long classPK, int type,
		java.lang.String extraData, long receiverUserId)
		throws PortalException;

	/**
	* Records an activity in the database, but only if there isn't already an
	* activity with the same parameters.
	*
	* <p>
	* For the main functionality see {@link #addActivity(long, long, Date,
	* String, long, int, String, long)}
	* </p>
	*
	* @param userId the primary key of the acting user
	* @param groupId the primary key of the group
	* @param createDate the activity's date
	* @param className the target asset's class name
	* @param classPK the primary key of the target asset
	* @param type the activity's type
	* @param extraData any extra data regarding the activity
	* @param receiverUserId the primary key of the receiving user
	*/
	public void addUniqueActivity(long userId, long groupId, Date createDate,
		java.lang.String className, long classPK, int type,
		java.lang.String extraData, long receiverUserId)
		throws PortalException;

	/**
	* Removes stored activities for the asset.
	*
	* @param assetEntry the asset from which to remove stored activities
	*/
	public void deleteActivities(AssetEntry assetEntry)
		throws PortalException;

	/**
	* Removes stored activities for the asset identified by the class name and
	* class primary key.
	*
	* @param className the target asset's class name
	* @param classPK the primary key of the target asset
	*/
	public void deleteActivities(java.lang.String className, long classPK)
		throws PortalException;

	public void deleteActivities(long groupId);

	/**
	* Removes the stored activity and its mirror activity from the database.
	*
	* @param activity the activity to be removed
	*/
	public void deleteActivity(SocialActivity activity)
		throws PortalException;

	/**
	* Removes the stored activity from the database.
	*
	* @param activityId the primary key of the stored activity
	*/
	public void deleteActivity(long activityId) throws PortalException;

	/**
	* Removes the user's stored activities from the database.
	*
	* <p>
	* This method removes all activities where the user is either the actor or
	* the receiver.
	* </p>
	*
	* @param userId the primary key of the user
	*/
	public void deleteUserActivities(long userId) throws PortalException;
}