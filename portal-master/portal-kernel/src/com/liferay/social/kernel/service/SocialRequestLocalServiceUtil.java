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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for SocialRequest. This utility wraps
 * {@link com.liferay.portlet.social.service.impl.SocialRequestLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SocialRequestLocalService
 * @see com.liferay.portlet.social.service.base.SocialRequestLocalServiceBaseImpl
 * @see com.liferay.portlet.social.service.impl.SocialRequestLocalServiceImpl
 * @generated
 */
@ProviderType
public class SocialRequestLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.social.service.impl.SocialRequestLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns <code>true</code> if a matching social requests exists in the
	* database.
	*
	* @param userId the primary key of the requesting user
	* @param className the class name of the asset that is the subject of the
	request
	* @param classPK the primary key of the asset that is the subject of the
	request
	* @param type the request's type
	* @param status the social request's status
	* @return <code>true</code> if the request exists; <code>false</code>
	otherwise
	*/
	public static boolean hasRequest(long userId, java.lang.String className,
		long classPK, int type, int status) {
		return getService().hasRequest(userId, className, classPK, type, status);
	}

	/**
	* Returns <code>true</code> if a matching social request exists in the
	* database.
	*
	* @param userId the primary key of the requesting user
	* @param className the class name of the asset that is the subject of the
	request
	* @param classPK the primary key of the asset that is the subject of the
	request
	* @param type the request's type
	* @param receiverUserId the primary key of the receiving user
	* @param status the social request's status
	* @return <code>true</code> if the social request exists;
	<code>false</code> otherwise
	*/
	public static boolean hasRequest(long userId, java.lang.String className,
		long classPK, int type, long receiverUserId, int status) {
		return getService()
				   .hasRequest(userId, className, classPK, type,
			receiverUserId, status);
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
	* Adds a social request to the database.
	*
	* <p>
	* In order to add a social request, both the requesting user and the
	* receiving user must be from the same company and neither of them can be
	* the default user.
	* </p>
	*
	* @param userId the primary key of the requesting user
	* @param groupId the primary key of the group
	* @param className the class name of the asset that is the subject of the
	request
	* @param classPK the primary key of the asset that is the subject of the
	request
	* @param type the request's type
	* @param extraData the extra data regarding the request
	* @param receiverUserId the primary key of the user receiving the request
	* @return the social request
	*/
	public static com.liferay.social.kernel.model.SocialRequest addRequest(
		long userId, long groupId, java.lang.String className, long classPK,
		int type, java.lang.String extraData, long receiverUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addRequest(userId, groupId, className, classPK, type,
			extraData, receiverUserId);
	}

	/**
	* Adds the social request to the database. Also notifies the appropriate model listeners.
	*
	* @param socialRequest the social request
	* @return the social request that was added
	*/
	public static com.liferay.social.kernel.model.SocialRequest addSocialRequest(
		com.liferay.social.kernel.model.SocialRequest socialRequest) {
		return getService().addSocialRequest(socialRequest);
	}

	/**
	* Creates a new social request with the primary key. Does not add the social request to the database.
	*
	* @param requestId the primary key for the new social request
	* @return the new social request
	*/
	public static com.liferay.social.kernel.model.SocialRequest createSocialRequest(
		long requestId) {
		return getService().createSocialRequest(requestId);
	}

	/**
	* Deletes the social request from the database. Also notifies the appropriate model listeners.
	*
	* @param socialRequest the social request
	* @return the social request that was removed
	*/
	public static com.liferay.social.kernel.model.SocialRequest deleteSocialRequest(
		com.liferay.social.kernel.model.SocialRequest socialRequest) {
		return getService().deleteSocialRequest(socialRequest);
	}

	/**
	* Deletes the social request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param requestId the primary key of the social request
	* @return the social request that was removed
	* @throws PortalException if a social request with the primary key could not be found
	*/
	public static com.liferay.social.kernel.model.SocialRequest deleteSocialRequest(
		long requestId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSocialRequest(requestId);
	}

	public static com.liferay.social.kernel.model.SocialRequest fetchSocialRequest(
		long requestId) {
		return getService().fetchSocialRequest(requestId);
	}

	/**
	* Returns the social request matching the UUID and group.
	*
	* @param uuid the social request's UUID
	* @param groupId the primary key of the group
	* @return the matching social request, or <code>null</code> if a matching social request could not be found
	*/
	public static com.liferay.social.kernel.model.SocialRequest fetchSocialRequestByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchSocialRequestByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the social request with the primary key.
	*
	* @param requestId the primary key of the social request
	* @return the social request
	* @throws PortalException if a social request with the primary key could not be found
	*/
	public static com.liferay.social.kernel.model.SocialRequest getSocialRequest(
		long requestId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSocialRequest(requestId);
	}

	/**
	* Returns the social request matching the UUID and group.
	*
	* @param uuid the social request's UUID
	* @param groupId the primary key of the group
	* @return the matching social request
	* @throws PortalException if a matching social request could not be found
	*/
	public static com.liferay.social.kernel.model.SocialRequest getSocialRequestByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSocialRequestByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the social request replacing its status.
	*
	* <p>
	* If the status is updated to {@link SocialRequestConstants#STATUS_CONFIRM}
	* then {@link
	* SocialRequestInterpreterLocalService#processConfirmation(
	* SocialRequest, ThemeDisplay)} is called. If the status is updated to
	* {@link SocialRequestConstants#STATUS_IGNORE} then {@link
	* SocialRequestInterpreterLocalService#processRejection(
	* SocialRequest, ThemeDisplay)} is called.
	* </p>
	*
	* @param requestId the primary key of the social request
	* @param status the new status
	* @param themeDisplay the theme display
	* @return the updated social request
	*/
	public static com.liferay.social.kernel.model.SocialRequest updateRequest(
		long requestId, int status,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateRequest(requestId, status, themeDisplay);
	}

	/**
	* Updates the social request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param socialRequest the social request
	* @return the social request that was updated
	*/
	public static com.liferay.social.kernel.model.SocialRequest updateSocialRequest(
		com.liferay.social.kernel.model.SocialRequest socialRequest) {
		return getService().updateSocialRequest(socialRequest);
	}

	/**
	* Returns the number of social requests for the receiving user.
	*
	* @param receiverUserId the primary key of the receiving user
	* @return the number of matching social requests
	*/
	public static int getReceiverUserRequestsCount(long receiverUserId) {
		return getService().getReceiverUserRequestsCount(receiverUserId);
	}

	/**
	* Returns the number of social requests with the given status for the
	* receiving user.
	*
	* @param receiverUserId the primary key of the receiving user
	* @param status the social request's status
	* @return the number of matching social requests
	*/
	public static int getReceiverUserRequestsCount(long receiverUserId,
		int status) {
		return getService().getReceiverUserRequestsCount(receiverUserId, status);
	}

	/**
	* Returns the number of social requests.
	*
	* @return the number of social requests
	*/
	public static int getSocialRequestsCount() {
		return getService().getSocialRequestsCount();
	}

	/**
	* Returns the number of social requests for the requesting user.
	*
	* @param userId the primary key of the requesting user
	* @return the number of matching social requests
	*/
	public static int getUserRequestsCount(long userId) {
		return getService().getUserRequestsCount(userId);
	}

	/**
	* Returns the number of social requests with the given status for the
	* requesting user.
	*
	* @param userId the primary key of the requesting user
	* @param status the social request's status
	* @return the number of matching social request
	*/
	public static int getUserRequestsCount(long userId, int status) {
		return getService().getUserRequestsCount(userId, status);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the social requests for the receiving user.
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
	* @param receiverUserId the primary key of the receiving user
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching social requests
	*/
	public static java.util.List<com.liferay.social.kernel.model.SocialRequest> getReceiverUserRequests(
		long receiverUserId, int start, int end) {
		return getService().getReceiverUserRequests(receiverUserId, start, end);
	}

	/**
	* Returns a range of all the social requests with the given status for the
	* receiving user.
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
	* @param receiverUserId the primary key of the receiving user
	* @param status the social request's status
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching social requests
	*/
	public static java.util.List<com.liferay.social.kernel.model.SocialRequest> getReceiverUserRequests(
		long receiverUserId, int status, int start, int end) {
		return getService()
				   .getReceiverUserRequests(receiverUserId, status, start, end);
	}

	/**
	* Returns a range of all the social requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.social.model.impl.SocialRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of social requests
	* @param end the upper bound of the range of social requests (not inclusive)
	* @return the range of social requests
	*/
	public static java.util.List<com.liferay.social.kernel.model.SocialRequest> getSocialRequests(
		int start, int end) {
		return getService().getSocialRequests(start, end);
	}

	/**
	* Returns all the social requests matching the UUID and company.
	*
	* @param uuid the UUID of the social requests
	* @param companyId the primary key of the company
	* @return the matching social requests, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.social.kernel.model.SocialRequest> getSocialRequestsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getSocialRequestsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of social requests matching the UUID and company.
	*
	* @param uuid the UUID of the social requests
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of social requests
	* @param end the upper bound of the range of social requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching social requests, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.social.kernel.model.SocialRequest> getSocialRequestsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.social.kernel.model.SocialRequest> orderByComparator) {
		return getService()
				   .getSocialRequestsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the social requests for the requesting user.
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
	* @param userId the primary key of the requesting user
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching social requests
	*/
	public static java.util.List<com.liferay.social.kernel.model.SocialRequest> getUserRequests(
		long userId, int start, int end) {
		return getService().getUserRequests(userId, start, end);
	}

	/**
	* Returns a range of all the social requests with the given status for the
	* requesting user.
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
	* @param userId the primary key of the requesting user
	* @param status the social request's status
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of matching social requests
	*/
	public static java.util.List<com.liferay.social.kernel.model.SocialRequest> getUserRequests(
		long userId, int status, int start, int end) {
		return getService().getUserRequests(userId, status, start, end);
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
	* Removes all the social requests for the receiving user.
	*
	* @param receiverUserId the primary key of the receiving user
	*/
	public static void deleteReceiverUserRequests(long receiverUserId) {
		getService().deleteReceiverUserRequests(receiverUserId);
	}

	/**
	* Removes the social request from the database.
	*
	* @param request the social request to be removed
	*/
	public static void deleteRequest(
		com.liferay.social.kernel.model.SocialRequest request) {
		getService().deleteRequest(request);
	}

	/**
	* Removes the social request identified by its primary key from the
	* database.
	*
	* @param requestId the primary key of the social request
	*/
	public static void deleteRequest(long requestId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteRequest(requestId);
	}

	public static void deleteRequests(long className, long classPK) {
		getService().deleteRequests(className, classPK);
	}

	/**
	* Removes all the social requests for the requesting user.
	*
	* @param userId the primary key of the requesting user
	*/
	public static void deleteUserRequests(long userId) {
		getService().deleteUserRequests(userId);
	}

	public static SocialRequestLocalService getService() {
		if (_service == null) {
			_service = (SocialRequestLocalService)PortalBeanLocatorUtil.locate(SocialRequestLocalService.class.getName());

			ReferenceRegistry.registerReference(SocialRequestLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static SocialRequestLocalService _service;
}