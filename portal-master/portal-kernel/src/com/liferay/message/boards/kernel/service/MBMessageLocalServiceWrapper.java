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
 * Provides a wrapper for {@link MBMessageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MBMessageLocalService
 * @generated
 */
@ProviderType
public class MBMessageLocalServiceWrapper implements MBMessageLocalService,
	ServiceWrapper<MBMessageLocalService> {
	public MBMessageLocalServiceWrapper(
		MBMessageLocalService mbMessageLocalService) {
		_mbMessageLocalService = mbMessageLocalService;
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage addDiscussionMessage(
		long userId, java.lang.String userName, long groupId,
		java.lang.String className, long classPK, int workflowAction)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.addDiscussionMessage(userId, userName,
			groupId, className, classPK, workflowAction);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage addDiscussionMessage(
		long userId, java.lang.String userName, long groupId,
		java.lang.String className, long classPK, long threadId,
		long parentMessageId, java.lang.String subject, java.lang.String body,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.addDiscussionMessage(userId, userName,
			groupId, className, classPK, threadId, parentMessageId, subject,
			body, serviceContext);
	}

	/**
	* Adds the message-boards message to the database. Also notifies the appropriate model listeners.
	*
	* @param mbMessage the message-boards message
	* @return the message-boards message that was added
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBMessage addMBMessage(
		com.liferay.message.boards.kernel.model.MBMessage mbMessage) {
		return _mbMessageLocalService.addMBMessage(mbMessage);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #addMessage(long, String,
	long, long, String, String, ServiceContext)}
	*/
	@Deprecated
	@Override
	public com.liferay.message.boards.kernel.model.MBMessage addMessage(
		long userId, java.lang.String userName, long categoryId,
		java.lang.String subject, java.lang.String body,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.addMessage(userId, userName, categoryId,
			subject, body, serviceContext);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage addMessage(
		long userId, java.lang.String userName, long groupId, long categoryId,
		java.lang.String subject, java.lang.String body,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.addMessage(userId, userName, groupId,
			categoryId, subject, body, serviceContext);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage addMessage(
		long userId, java.lang.String userName, long groupId, long categoryId,
		java.lang.String subject, java.lang.String body,
		java.lang.String format, java.lang.String fileName, java.io.File file,
		boolean anonymous, double priority, boolean allowPingbacks,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			java.io.FileNotFoundException {
		return _mbMessageLocalService.addMessage(userId, userName, groupId,
			categoryId, subject, body, format, fileName, file, anonymous,
			priority, allowPingbacks, serviceContext);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage addMessage(
		long userId, java.lang.String userName, long groupId, long categoryId,
		java.lang.String subject, java.lang.String body,
		java.lang.String format,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.InputStream>> inputStreamOVPs,
		boolean anonymous, double priority, boolean allowPingbacks,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.addMessage(userId, userName, groupId,
			categoryId, subject, body, format, inputStreamOVPs, anonymous,
			priority, allowPingbacks, serviceContext);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage addMessage(
		long userId, java.lang.String userName, long groupId, long categoryId,
		long threadId, long parentMessageId, java.lang.String subject,
		java.lang.String body, java.lang.String format,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.InputStream>> inputStreamOVPs,
		boolean anonymous, double priority, boolean allowPingbacks,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.addMessage(userId, userName, groupId,
			categoryId, threadId, parentMessageId, subject, body, format,
			inputStreamOVPs, anonymous, priority, allowPingbacks, serviceContext);
	}

	/**
	* Creates a new message-boards message with the primary key. Does not add the message-boards message to the database.
	*
	* @param messageId the primary key for the new message-boards message
	* @return the new message-boards message
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBMessage createMBMessage(
		long messageId) {
		return _mbMessageLocalService.createMBMessage(messageId);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage deleteDiscussionMessage(
		long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.deleteDiscussionMessage(messageId);
	}

	/**
	* Deletes the message-boards message from the database. Also notifies the appropriate model listeners.
	*
	* @param mbMessage the message-boards message
	* @return the message-boards message that was removed
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBMessage deleteMBMessage(
		com.liferay.message.boards.kernel.model.MBMessage mbMessage) {
		return _mbMessageLocalService.deleteMBMessage(mbMessage);
	}

	/**
	* Deletes the message-boards message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param messageId the primary key of the message-boards message
	* @return the message-boards message that was removed
	* @throws PortalException if a message-boards message with the primary key could not be found
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBMessage deleteMBMessage(
		long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.deleteMBMessage(messageId);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage deleteMessage(
		com.liferay.message.boards.kernel.model.MBMessage message)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.deleteMessage(message);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage deleteMessage(
		long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.deleteMessage(messageId);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage fetchMBMessage(
		long messageId) {
		return _mbMessageLocalService.fetchMBMessage(messageId);
	}

	/**
	* Returns the message-boards message matching the UUID and group.
	*
	* @param uuid the message-boards message's UUID
	* @param groupId the primary key of the group
	* @return the matching message-boards message, or <code>null</code> if a matching message-boards message could not be found
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBMessage fetchMBMessageByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _mbMessageLocalService.fetchMBMessageByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the message-boards message with the primary key.
	*
	* @param messageId the primary key of the message-boards message
	* @return the message-boards message
	* @throws PortalException if a message-boards message with the primary key could not be found
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBMessage getMBMessage(
		long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getMBMessage(messageId);
	}

	/**
	* Returns the message-boards message matching the UUID and group.
	*
	* @param uuid the message-boards message's UUID
	* @param groupId the primary key of the group
	* @return the matching message-boards message
	* @throws PortalException if a matching message-boards message could not be found
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBMessage getMBMessageByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getMBMessageByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage getMessage(
		long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getMessage(messageId);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage updateDiscussionMessage(
		long userId, long messageId, java.lang.String className, long classPK,
		java.lang.String subject, java.lang.String body,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.updateDiscussionMessage(userId,
			messageId, className, classPK, subject, body, serviceContext);
	}

	/**
	* Updates the message-boards message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mbMessage the message-boards message
	* @return the message-boards message that was updated
	*/
	@Override
	public com.liferay.message.boards.kernel.model.MBMessage updateMBMessage(
		com.liferay.message.boards.kernel.model.MBMessage mbMessage) {
		return _mbMessageLocalService.updateMBMessage(mbMessage);
	}

	/**
	* @deprecated As of 7.0.0, with no direct replacement
	*/
	@Deprecated
	@Override
	public com.liferay.message.boards.kernel.model.MBMessage updateMessage(
		long messageId, java.lang.String body)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.updateMessage(messageId, body);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage updateMessage(
		long userId, long messageId, java.lang.String body,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.updateMessage(userId, messageId, body,
			serviceContext);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage updateMessage(
		long userId, long messageId, java.lang.String subject,
		java.lang.String body,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.InputStream>> inputStreamOVPs,
		java.util.List<java.lang.String> existingFiles, double priority,
		boolean allowPingbacks,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.updateMessage(userId, messageId, subject,
			body, inputStreamOVPs, existingFiles, priority, allowPingbacks,
			serviceContext);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #updateStatus(long, long,
	int, ServiceContext, Map)}
	*/
	@Deprecated
	@Override
	public com.liferay.message.boards.kernel.model.MBMessage updateStatus(
		long userId, long messageId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.updateStatus(userId, messageId, status,
			serviceContext);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessage updateStatus(
		long userId, long messageId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.updateStatus(userId, messageId, status,
			serviceContext, workflowContext);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessageDisplay getDiscussionMessageDisplay(
		long userId, long groupId, java.lang.String className, long classPK,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getDiscussionMessageDisplay(userId,
			groupId, className, classPK, status);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link
	#getDiscussionMessageDisplay(long, long, String, long, int)}
	*/
	@Deprecated
	@Override
	public com.liferay.message.boards.kernel.model.MBMessageDisplay getDiscussionMessageDisplay(
		long userId, long groupId, java.lang.String className, long classPK,
		int status, java.lang.String threadView)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getDiscussionMessageDisplay(userId,
			groupId, className, classPK, status, threadView);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessageDisplay getDiscussionMessageDisplay(
		long userId, long groupId, java.lang.String className, long classPK,
		int status,
		java.util.Comparator<com.liferay.message.boards.kernel.model.MBMessage> comparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getDiscussionMessageDisplay(userId,
			groupId, className, classPK, status, comparator);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessageDisplay getMessageDisplay(
		long userId, com.liferay.message.boards.kernel.model.MBMessage message,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getMessageDisplay(userId, message, status);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getMessageDisplay(long,
	MBMessage, int)}
	*/
	@Deprecated
	@Override
	public com.liferay.message.boards.kernel.model.MBMessageDisplay getMessageDisplay(
		long userId, com.liferay.message.boards.kernel.model.MBMessage message,
		int status, java.lang.String threadView, boolean includePrevAndNext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getMessageDisplay(userId, message,
			status, threadView, includePrevAndNext);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getMessageDisplay(long,
	MBMessage, int, Comparator)} (
	*/
	@Deprecated
	@Override
	public com.liferay.message.boards.kernel.model.MBMessageDisplay getMessageDisplay(
		long userId, com.liferay.message.boards.kernel.model.MBMessage message,
		int status, java.lang.String threadView, boolean includePrevAndNext,
		java.util.Comparator<com.liferay.message.boards.kernel.model.MBMessage> comparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getMessageDisplay(userId, message,
			status, threadView, includePrevAndNext, comparator);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessageDisplay getMessageDisplay(
		long userId, com.liferay.message.boards.kernel.model.MBMessage message,
		int status,
		java.util.Comparator<com.liferay.message.boards.kernel.model.MBMessage> comparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getMessageDisplay(userId, message,
			status, comparator);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBMessageDisplay getMessageDisplay(
		long userId, long messageId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getMessageDisplay(userId, messageId,
			status);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getMessageDisplay(long,
	long, int)}
	*/
	@Deprecated
	@Override
	public com.liferay.message.boards.kernel.model.MBMessageDisplay getMessageDisplay(
		long userId, long messageId, int status, java.lang.String threadView,
		boolean includePrevAndNext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getMessageDisplay(userId, messageId,
			status, threadView, includePrevAndNext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _mbMessageLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mbMessageLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _mbMessageLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _mbMessageLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getCategoryMessagesCount(long groupId, long categoryId,
		int status) {
		return _mbMessageLocalService.getCategoryMessagesCount(groupId,
			categoryId, status);
	}

	@Override
	public int getCompanyMessagesCount(long companyId, int status) {
		return _mbMessageLocalService.getCompanyMessagesCount(companyId, status);
	}

	@Override
	public int getDiscussionMessagesCount(java.lang.String className,
		long classPK, int status) {
		return _mbMessageLocalService.getDiscussionMessagesCount(className,
			classPK, status);
	}

	@Override
	public int getDiscussionMessagesCount(long classNameId, long classPK,
		int status) {
		return _mbMessageLocalService.getDiscussionMessagesCount(classNameId,
			classPK, status);
	}

	@Override
	public int getGroupMessagesCount(long groupId, int status) {
		return _mbMessageLocalService.getGroupMessagesCount(groupId, status);
	}

	@Override
	public int getGroupMessagesCount(long groupId, long userId, int status) {
		return _mbMessageLocalService.getGroupMessagesCount(groupId, userId,
			status);
	}

	/**
	* Returns the number of message-boards messages.
	*
	* @return the number of message-boards messages
	*/
	@Override
	public int getMBMessagesCount() {
		return _mbMessageLocalService.getMBMessagesCount();
	}

	@Override
	public int getPositionInThread(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.getPositionInThread(messageId);
	}

	@Override
	public int getThreadMessagesCount(long threadId, int status) {
		return _mbMessageLocalService.getThreadMessagesCount(threadId, status);
	}

	@Override
	public int getUserDiscussionMessagesCount(long userId,
		java.lang.String className, long classPK, int status) {
		return _mbMessageLocalService.getUserDiscussionMessagesCount(userId,
			className, classPK, status);
	}

	@Override
	public int getUserDiscussionMessagesCount(long userId, long classNameId,
		long classPK, int status) {
		return _mbMessageLocalService.getUserDiscussionMessagesCount(userId,
			classNameId, classPK, status);
	}

	@Override
	public int getUserDiscussionMessagesCount(long userId, long[] classNameIds,
		int status) {
		return _mbMessageLocalService.getUserDiscussionMessagesCount(userId,
			classNameIds, status);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _mbMessageLocalService.getOSGiServiceIdentifier();
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
		return _mbMessageLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.messageboards.model.impl.MBMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mbMessageLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.messageboards.model.impl.MBMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mbMessageLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getCategoryMessages(
		long groupId, long categoryId, int status, int start, int end) {
		return _mbMessageLocalService.getCategoryMessages(groupId, categoryId,
			status, start, end);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getCategoryMessages(
		long groupId, long categoryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.message.boards.kernel.model.MBMessage> obc) {
		return _mbMessageLocalService.getCategoryMessages(groupId, categoryId,
			status, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getCompanyMessages(
		long companyId, int status, int start, int end) {
		return _mbMessageLocalService.getCompanyMessages(companyId, status,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getCompanyMessages(
		long companyId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.message.boards.kernel.model.MBMessage> obc) {
		return _mbMessageLocalService.getCompanyMessages(companyId, status,
			start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBDiscussion> getDiscussions(
		java.lang.String className) {
		return _mbMessageLocalService.getDiscussions(className);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getGroupMessages(
		long groupId, int status, int start, int end) {
		return _mbMessageLocalService.getGroupMessages(groupId, status, start,
			end);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getGroupMessages(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.message.boards.kernel.model.MBMessage> obc) {
		return _mbMessageLocalService.getGroupMessages(groupId, status, start,
			end, obc);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getGroupMessages(
		long groupId, long userId, int status, int start, int end) {
		return _mbMessageLocalService.getGroupMessages(groupId, userId, status,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getGroupMessages(
		long groupId, long userId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.message.boards.kernel.model.MBMessage> obc) {
		return _mbMessageLocalService.getGroupMessages(groupId, userId, status,
			start, end, obc);
	}

	/**
	* Returns a range of all the message-boards messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.messageboards.model.impl.MBMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of message-boards messages
	* @param end the upper bound of the range of message-boards messages (not inclusive)
	* @return the range of message-boards messages
	*/
	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getMBMessages(
		int start, int end) {
		return _mbMessageLocalService.getMBMessages(start, end);
	}

	/**
	* Returns all the message-boards messages matching the UUID and company.
	*
	* @param uuid the UUID of the message-boards messages
	* @param companyId the primary key of the company
	* @return the matching message-boards messages, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getMBMessagesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _mbMessageLocalService.getMBMessagesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of message-boards messages matching the UUID and company.
	*
	* @param uuid the UUID of the message-boards messages
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of message-boards messages
	* @param end the upper bound of the range of message-boards messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching message-boards messages, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getMBMessagesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.message.boards.kernel.model.MBMessage> orderByComparator) {
		return _mbMessageLocalService.getMBMessagesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getMessages(
		java.lang.String className, long classPK, int status) {
		return _mbMessageLocalService.getMessages(className, classPK, status);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getNoAssetMessages() {
		return _mbMessageLocalService.getNoAssetMessages();
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getThreadMessages(
		long threadId, int status) {
		return _mbMessageLocalService.getThreadMessages(threadId, status);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getThreadMessages(
		long threadId, int status, int start, int end) {
		return _mbMessageLocalService.getThreadMessages(threadId, status,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getThreadMessages(
		long threadId, int status,
		java.util.Comparator<com.liferay.message.boards.kernel.model.MBMessage> comparator) {
		return _mbMessageLocalService.getThreadMessages(threadId, status,
			comparator);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getThreadRepliesMessages(
		long threadId, int status, int start, int end) {
		return _mbMessageLocalService.getThreadRepliesMessages(threadId,
			status, start, end);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getUserDiscussionMessages(
		long userId, java.lang.String className, long classPK, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.message.boards.kernel.model.MBMessage> obc) {
		return _mbMessageLocalService.getUserDiscussionMessages(userId,
			className, classPK, status, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getUserDiscussionMessages(
		long userId, long classNameId, long classPK, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.message.boards.kernel.model.MBMessage> obc) {
		return _mbMessageLocalService.getUserDiscussionMessages(userId,
			classNameId, classPK, status, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage> getUserDiscussionMessages(
		long userId, long[] classNameIds, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.message.boards.kernel.model.MBMessage> obc) {
		return _mbMessageLocalService.getUserDiscussionMessages(userId,
			classNameIds, status, start, end, obc);
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
		return _mbMessageLocalService.dynamicQueryCount(dynamicQuery);
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
		return _mbMessageLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public long moveMessageAttachmentToTrash(long userId, long messageId,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbMessageLocalService.moveMessageAttachmentToTrash(userId,
			messageId, fileName);
	}

	@Override
	public void addMessageAttachment(long userId, long messageId,
		java.lang.String fileName, java.io.File file, java.lang.String mimeType)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.addMessageAttachment(userId, messageId,
			fileName, file, mimeType);
	}

	@Override
	public void addMessageResources(
		com.liferay.message.boards.kernel.model.MBMessage message,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.addMessageResources(message,
			addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addMessageResources(
		com.liferay.message.boards.kernel.model.MBMessage message,
		com.liferay.portal.kernel.service.permission.ModelPermissions modelPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.addMessageResources(message, modelPermissions);
	}

	@Override
	public void addMessageResources(long messageId,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.addMessageResources(messageId,
			addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addMessageResources(long messageId,
		com.liferay.portal.kernel.service.permission.ModelPermissions modelPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.addMessageResources(messageId, modelPermissions);
	}

	@Override
	public void deleteDiscussionMessages(java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.deleteDiscussionMessages(className, classPK);
	}

	@Override
	public void deleteMessageAttachment(long messageId,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.deleteMessageAttachment(messageId, fileName);
	}

	@Override
	public void deleteMessageAttachments(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.deleteMessageAttachments(messageId);
	}

	@Override
	public void emptyMessageAttachments(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.emptyMessageAttachments(messageId);
	}

	@Override
	public void restoreMessageAttachmentFromTrash(long userId, long messageId,
		java.lang.String deletedFileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.restoreMessageAttachmentFromTrash(userId,
			messageId, deletedFileName);
	}

	@Override
	public void subscribeMessage(long userId, long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.subscribeMessage(userId, messageId);
	}

	@Override
	public void unsubscribeMessage(long userId, long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.unsubscribeMessage(userId, messageId);
	}

	@Override
	public void updateAnswer(
		com.liferay.message.boards.kernel.model.MBMessage message,
		boolean answer, boolean cascade)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.updateAnswer(message, answer, cascade);
	}

	@Override
	public void updateAnswer(long messageId, boolean answer, boolean cascade)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.updateAnswer(messageId, answer, cascade);
	}

	@Override
	public void updateAsset(long userId,
		com.liferay.message.boards.kernel.model.MBMessage message,
		long[] assetCategoryIds, java.lang.String[] assetTagNames,
		long[] assetLinkEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbMessageLocalService.updateAsset(userId, message, assetCategoryIds,
			assetTagNames, assetLinkEntryIds);
	}

	@Override
	public void updateUserName(long userId, java.lang.String userName) {
		_mbMessageLocalService.updateUserName(userId, userName);
	}

	@Override
	public MBMessageLocalService getWrappedService() {
		return _mbMessageLocalService;
	}

	@Override
	public void setWrappedService(MBMessageLocalService mbMessageLocalService) {
		_mbMessageLocalService = mbMessageLocalService;
	}

	private MBMessageLocalService _mbMessageLocalService;
}