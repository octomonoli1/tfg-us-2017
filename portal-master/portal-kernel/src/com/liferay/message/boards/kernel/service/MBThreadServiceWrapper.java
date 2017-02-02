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
 * Provides a wrapper for {@link MBThreadService}.
 *
 * @author Brian Wing Shun Chan
 * @see MBThreadService
 * @generated
 */
@ProviderType
public class MBThreadServiceWrapper implements MBThreadService,
	ServiceWrapper<MBThreadService> {
	public MBThreadServiceWrapper(MBThreadService mbThreadService) {
		_mbThreadService = mbThreadService;
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBThread moveThread(
		long categoryId, long threadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbThreadService.moveThread(categoryId, threadId);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBThread moveThreadFromTrash(
		long categoryId, long threadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbThreadService.moveThreadFromTrash(categoryId, threadId);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBThread moveThreadToTrash(
		long threadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbThreadService.moveThreadToTrash(threadId);
	}

	@Override
	public com.liferay.message.boards.kernel.model.MBThread splitThread(
		long messageId, java.lang.String subject,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbThreadService.splitThread(messageId, subject, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.lock.Lock lockThread(long threadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbThreadService.lockThread(threadId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long groupId,
		long creatorUserId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbThreadService.search(groupId, creatorUserId, status, start,
			end);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long groupId,
		long creatorUserId, long startDate, long endDate, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbThreadService.search(groupId, creatorUserId, startDate,
			endDate, status, start, end);
	}

	@Override
	public int getGroupThreadsCount(long groupId, long userId, int status) {
		return _mbThreadService.getGroupThreadsCount(groupId, userId, status);
	}

	@Override
	public int getGroupThreadsCount(long groupId, long userId, int status,
		boolean subscribed) {
		return _mbThreadService.getGroupThreadsCount(groupId, userId, status,
			subscribed);
	}

	@Override
	public int getGroupThreadsCount(long groupId, long userId, int status,
		boolean subscribed, boolean includeAnonymous) {
		return _mbThreadService.getGroupThreadsCount(groupId, userId, status,
			subscribed, includeAnonymous);
	}

	@Override
	public int getGroupThreadsCount(long groupId, long userId,
		java.util.Date modifiedDate, int status) {
		return _mbThreadService.getGroupThreadsCount(groupId, userId,
			modifiedDate, status);
	}

	@Override
	public int getThreadsCount(long groupId, long categoryId, int status) {
		return _mbThreadService.getThreadsCount(groupId, categoryId, status);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _mbThreadService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBThread> getGroupThreads(
		long groupId, long userId, int status, boolean subscribed,
		boolean includeAnonymous, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbThreadService.getGroupThreads(groupId, userId, status,
			subscribed, includeAnonymous, start, end);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBThread> getGroupThreads(
		long groupId, long userId, int status, boolean subscribed, int start,
		int end) throws com.liferay.portal.kernel.exception.PortalException {
		return _mbThreadService.getGroupThreads(groupId, userId, status,
			subscribed, start, end);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBThread> getGroupThreads(
		long groupId, long userId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbThreadService.getGroupThreads(groupId, userId, status, start,
			end);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBThread> getGroupThreads(
		long groupId, long userId, java.util.Date modifiedDate, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mbThreadService.getGroupThreads(groupId, userId, modifiedDate,
			status, start, end);
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBThread> getThreads(
		long groupId, long categoryId, int status, int start, int end) {
		return _mbThreadService.getThreads(groupId, categoryId, status, start,
			end);
	}

	@Override
	public void deleteThread(long threadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbThreadService.deleteThread(threadId);
	}

	@Override
	public void restoreThreadFromTrash(long threadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbThreadService.restoreThreadFromTrash(threadId);
	}

	@Override
	public void unlockThread(long threadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_mbThreadService.unlockThread(threadId);
	}

	@Override
	public MBThreadService getWrappedService() {
		return _mbThreadService;
	}

	@Override
	public void setWrappedService(MBThreadService mbThreadService) {
		_mbThreadService = mbThreadService;
	}

	private MBThreadService _mbThreadService;
}