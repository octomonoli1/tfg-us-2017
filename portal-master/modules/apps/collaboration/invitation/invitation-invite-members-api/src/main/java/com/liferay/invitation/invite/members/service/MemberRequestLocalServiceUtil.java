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

package com.liferay.invitation.invite.members.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for MemberRequest. This utility wraps
 * {@link com.liferay.invitation.invite.members.service.impl.MemberRequestLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MemberRequestLocalService
 * @see com.liferay.invitation.invite.members.service.base.MemberRequestLocalServiceBaseImpl
 * @see com.liferay.invitation.invite.members.service.impl.MemberRequestLocalServiceImpl
 * @generated
 */
@ProviderType
public class MemberRequestLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.invitation.invite.members.service.impl.MemberRequestLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasPendingMemberRequest(long groupId,
		long receiverUserId) {
		return getService().hasPendingMemberRequest(groupId, receiverUserId);
	}

	/**
	* Adds the member request to the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequest the member request
	* @return the member request that was added
	*/
	public static com.liferay.invitation.invite.members.model.MemberRequest addMemberRequest(
		com.liferay.invitation.invite.members.model.MemberRequest memberRequest) {
		return getService().addMemberRequest(memberRequest);
	}

	public static com.liferay.invitation.invite.members.model.MemberRequest addMemberRequest(
		long userId, long groupId, long receiverUserId,
		java.lang.String receiverEmailAddress, long invitedRoleId,
		long invitedTeamId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addMemberRequest(userId, groupId, receiverUserId,
			receiverEmailAddress, invitedRoleId, invitedTeamId, serviceContext);
	}

	/**
	* Creates a new member request with the primary key. Does not add the member request to the database.
	*
	* @param memberRequestId the primary key for the new member request
	* @return the new member request
	*/
	public static com.liferay.invitation.invite.members.model.MemberRequest createMemberRequest(
		long memberRequestId) {
		return getService().createMemberRequest(memberRequestId);
	}

	/**
	* Deletes the member request from the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequest the member request
	* @return the member request that was removed
	*/
	public static com.liferay.invitation.invite.members.model.MemberRequest deleteMemberRequest(
		com.liferay.invitation.invite.members.model.MemberRequest memberRequest) {
		return getService().deleteMemberRequest(memberRequest);
	}

	/**
	* Deletes the member request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request that was removed
	* @throws PortalException if a member request with the primary key could not be found
	*/
	public static com.liferay.invitation.invite.members.model.MemberRequest deleteMemberRequest(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMemberRequest(memberRequestId);
	}

	public static com.liferay.invitation.invite.members.model.MemberRequest fetchMemberRequest(
		long memberRequestId) {
		return getService().fetchMemberRequest(memberRequestId);
	}

	public static com.liferay.invitation.invite.members.model.MemberRequest getMemberRequest(
		long groupId, long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMemberRequest(groupId, receiverUserId, status);
	}

	/**
	* Returns the member request with the primary key.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request
	* @throws PortalException if a member request with the primary key could not be found
	*/
	public static com.liferay.invitation.invite.members.model.MemberRequest getMemberRequest(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMemberRequest(memberRequestId);
	}

	/**
	* Updates the member request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param memberRequest the member request
	* @return the member request that was updated
	*/
	public static com.liferay.invitation.invite.members.model.MemberRequest updateMemberRequest(
		com.liferay.invitation.invite.members.model.MemberRequest memberRequest) {
		return getService().updateMemberRequest(memberRequest);
	}

	public static com.liferay.invitation.invite.members.model.MemberRequest updateMemberRequest(
		java.lang.String key, long receiverUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateMemberRequest(key, receiverUserId);
	}

	public static com.liferay.invitation.invite.members.model.MemberRequest updateMemberRequest(
		long userId, long memberRequestId, int status)
		throws java.lang.Exception {
		return getService().updateMemberRequest(userId, memberRequestId, status);
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
	* Returns the number of member requests.
	*
	* @return the number of member requests
	*/
	public static int getMemberRequestsCount() {
		return getService().getMemberRequestsCount();
	}

	public static int getReceiverMemberRequestCount(long receiverUserId) {
		return getService().getReceiverMemberRequestCount(receiverUserId);
	}

	public static int getReceiverStatusMemberRequestCount(long receiverUserId,
		int status) {
		return getService()
				   .getReceiverStatusMemberRequestCount(receiverUserId, status);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.invitation.invite.members.model.impl.MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.invitation.invite.members.model.impl.MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the member requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.invitation.invite.members.model.impl.MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @return the range of member requests
	*/
	public static java.util.List<com.liferay.invitation.invite.members.model.MemberRequest> getMemberRequests(
		int start, int end) {
		return getService().getMemberRequests(start, end);
	}

	public static java.util.List<com.liferay.invitation.invite.members.model.MemberRequest> getReceiverMemberRequest(
		long receiverUserId, int start, int end) {
		return getService().getReceiverMemberRequest(receiverUserId, start, end);
	}

	public static java.util.List<com.liferay.invitation.invite.members.model.MemberRequest> getReceiverStatusMemberRequest(
		long receiverUserId, int status, int start, int end) {
		return getService()
				   .getReceiverStatusMemberRequest(receiverUserId, status,
			start, end);
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

	public static void addMemberRequests(long userId, long groupId,
		java.lang.String[] emailAddresses, long invitedRoleId,
		long invitedTeamId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addMemberRequests(userId, groupId, emailAddresses, invitedRoleId,
			invitedTeamId, serviceContext);
	}

	public static void addMemberRequests(long userId, long groupId,
		long[] receiverUserIds, long invitedRoleId, long invitedTeamId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addMemberRequests(userId, groupId, receiverUserIds, invitedRoleId,
			invitedTeamId, serviceContext);
	}

	public static MemberRequestLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MemberRequestLocalService, MemberRequestLocalService> _serviceTracker =
		ServiceTrackerFactory.open(MemberRequestLocalService.class);
}