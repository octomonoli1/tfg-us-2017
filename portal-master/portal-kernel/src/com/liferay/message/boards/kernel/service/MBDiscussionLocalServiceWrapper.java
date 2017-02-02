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

package com.liferay.message.boards.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MBDiscussionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MBDiscussionLocalService
 * @generated
 */
@ProviderType
public class MBDiscussionLocalServiceWrapper implements MBDiscussionLocalService,
	ServiceWrapper<MBDiscussionLocalService> {
	public MBDiscussionLocalServiceWrapper(
		MBDiscussionLocalService mbDiscussionLocalService) {
		_mbDiscussionLocalService = mbDiscussionLocalService;
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #addDiscussion(long, long,
	long, long, long, ServiceContext)}
	*/
	@Deprecated
	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion addDiscussion(
		long userId, long classNameId, long classPK, long threadId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbDiscussionLocalService.addDiscussion(userId, classNameId,
			classPK, threadId, serviceContext);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion addDiscussion(
		long userId, long groupId, long classNameId, long classPK,
		long threadId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbDiscussionLocalService.addDiscussion(userId, groupId,
			classNameId, classPK, threadId, serviceContext);
	}

	/**
	* Adds the message boards discussion to the database. Also notifies the appropriate model listeners.
	*
	* @param mbDiscussion the message boards discussion
	* @return the message boards discussion that was added
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion addMBDiscussion(
		com.liferay.message.boards.kernel.model.MBDiscussion mbDiscussion) {
		return _mbDiscussionLocalService.addMBDiscussion(mbDiscussion);
	}

	/**
	* Creates a new message boards discussion with the primary key. Does not add the message boards discussion to the database.
	*
	* @param discussionId the primary key for the new message boards discussion
	* @return the new message boards discussion
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion createMBDiscussion(
		long discussionId) {
		return _mbDiscussionLocalService.createMBDiscussion(discussionId);
	}

	/**
	* Deletes the message boards discussion from the database. Also notifies the appropriate model listeners.
	*
	* @param mbDiscussion the message boards discussion
	* @return the message boards discussion that was removed
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion deleteMBDiscussion(
		com.liferay.message.boards.kernel.model.MBDiscussion mbDiscussion) {
		return _mbDiscussionLocalService.deleteMBDiscussion(mbDiscussion);
	}

	/**
	* Deletes the message boards discussion with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param discussionId the primary key of the message boards discussion
	* @return the message boards discussion that was removed
	* @throws PortalException if a message boards discussion with the primary key could not be found
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion deleteMBDiscussion(
		long discussionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbDiscussionLocalService.deleteMBDiscussion(discussionId);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion fetchDiscussion(
		java.lang.String className, long classPK) {
		return _mbDiscussionLocalService.fetchDiscussion(className, classPK);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion fetchDiscussion(
		long discussionId) {
		return _mbDiscussionLocalService.fetchDiscussion(discussionId);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion fetchMBDiscussion(
		long discussionId) {
		return _mbDiscussionLocalService.fetchMBDiscussion(discussionId);
	}

	/**
	* Returns the message boards discussion matching the UUID and group.
	*
	* @param uuid the message boards discussion's UUID
	* @param groupId the primary key of the group
	* @return the matching message boards discussion, or <code>null</code> if a matching message boards discussion could not be found
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion fetchMBDiscussionByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _mbDiscussionLocalService.fetchMBDiscussionByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion fetchThreadDiscussion(
		long threadId) {
		return _mbDiscussionLocalService.fetchThreadDiscussion(threadId);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion getDiscussion(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbDiscussionLocalService.getDiscussion(className, classPK);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion getDiscussion(
		long discussionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbDiscussionLocalService.getDiscussion(discussionId);
	}

	/**
	* Returns the message boards discussion with the primary key.
	*
	* @param discussionId the primary key of the message boards discussion
	* @return the message boards discussion
	* @throws PortalException if a message boards discussion with the primary key could not be found
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion getMBDiscussion(
		long discussionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbDiscussionLocalService.getMBDiscussion(discussionId);
	}

	/**
	* Returns the message boards discussion matching the UUID and group.
	*
	* @param uuid the message boards discussion's UUID
	* @param groupId the primary key of the group
	* @return the matching message boards discussion
	* @throws PortalException if a matching message boards discussion could not be found
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion getMBDiscussionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbDiscussionLocalService.getMBDiscussionByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion getThreadDiscussion(
		long threadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbDiscussionLocalService.getThreadDiscussion(threadId);
	}

	/**
	* Updates the message boards discussion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mbDiscussion the message boards discussion
	* @return the message boards discussion that was updated
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBDiscussion updateMBDiscussion(
		com.liferay.message.boards.kernel.model.MBDiscussion mbDiscussion) {
		return _mbDiscussionLocalService.updateMBDiscussion(mbDiscussion);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _mbDiscussionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mbDiscussionLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _mbDiscussionLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _mbDiscussionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbDiscussionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbDiscussionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of message boards discussions.
	*
	* @return the number of message boards discussions
	*/
	@Override
	public int getMBDiscussionsCount() {
		return _mbDiscussionLocalService.getMBDiscussionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _mbDiscussionLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _mbDiscussionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.messageboards.model.impl.MBDiscussionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _mbDiscussionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.messageboards.model.impl.MBDiscussionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _mbDiscussionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the message boards discussions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.messageboards.model.impl.MBDiscussionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of message boards discussions
	* @param end the upper bound of the range of message boards discussions (not inclusive)
	* @return the range of message boards discussions
	*/
	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBDiscussion> getMBDiscussions(
		int start, int end) {
		return _mbDiscussionLocalService.getMBDiscussions(start, end);
	}

	/**
	* Returns all the message boards discussions matching the UUID and company.
	*
	* @param uuid the UUID of the message boards discussions
	* @param companyId the primary key of the company
	* @return the matching message boards discussions, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBDiscussion> getMBDiscussionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _mbDiscussionLocalService.getMBDiscussionsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of message boards discussions matching the UUID and company.
	*
	* @param uuid the UUID of the message boards discussions
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of message boards discussions
	* @param end the upper bound of the range of message boards discussions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching message boards discussions, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBDiscussion> getMBDiscussionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.message.boards.kernel.model.MBDiscussion> orderByComparator) {
		return _mbDiscussionLocalService.getMBDiscussionsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _mbDiscussionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _mbDiscussionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void subscribeDiscussion(long userId, long groupId,
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbDiscussionLocalService.subscribeDiscussion(userId, groupId,
			className, classPK);
	}

	@Override
	public void unsubscribeDiscussion(long userId, java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbDiscussionLocalService.unsubscribeDiscussion(userId, className,
			classPK);
	}

	@Override
	public MBDiscussionLocalService getWrappedService() {
		return _mbDiscussionLocalService;
	}

	@Override
	public void setWrappedService(
		MBDiscussionLocalService mbDiscussionLocalService) {
		_mbDiscussionLocalService = mbDiscussionLocalService;
	}

	private MBDiscussionLocalService _mbDiscussionLocalService;
}