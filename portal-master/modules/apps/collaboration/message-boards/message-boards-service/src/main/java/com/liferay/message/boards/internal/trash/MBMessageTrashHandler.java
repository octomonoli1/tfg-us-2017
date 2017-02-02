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

package com.liferay.message.boards.internal.trash;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.message.boards.kernel.service.MBMessageLocalService;
import com.liferay.message.boards.kernel.service.MBMessageService;
import com.liferay.message.boards.kernel.service.MBThreadLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ContainerModel;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.trash.BaseTrashHandler;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portlet.messageboards.service.permission.MBMessagePermission;
import com.liferay.portlet.messageboards.util.MBMessageAttachmentsUtil;
import com.liferay.trash.kernel.model.TrashEntry;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Implements trash handling for message boards message entity.
 *
 * @author Zsolt Berentey
 */
@Component(
	property = {"model.class.name=com.liferay.message.boards.kernel.model.MBMessage"},
	service = TrashHandler.class
)
public class MBMessageTrashHandler extends BaseTrashHandler {

	@Override
	public void deleteTrashEntry(long classPK) {
	}

	@Override
	public String getClassName() {
		return MBMessage.class.getName();
	}

	@Override
	public ContainerModel getContainerModel(long containerModelId)
		throws PortalException {

		return _mbThreadLocalService.getThread(containerModelId);
	}

	@Override
	public String getContainerModelClassName(long classPK) {
		return MBThread.class.getName();
	}

	@Override
	public ContainerModel getParentContainerModel(TrashedModel trashedModel)
		throws PortalException {

		MBMessage message = (MBMessage)trashedModel;

		return getContainerModel(message.getThreadId());
	}

	@Override
	public TrashEntry getTrashEntry(long classPK) throws PortalException {
		MBMessage message = _mbMessageLocalService.getMessage(classPK);

		return message.getTrashEntry();
	}

	@Override
	public boolean isDeletable() {
		return false;
	}

	@Override
	public boolean isInTrash(long classPK) throws PortalException {
		MBMessage message = _mbMessageLocalService.getMessage(classPK);

		return message.isInTrash();
	}

	@Override
	public boolean isInTrashContainer(long classPK) throws PortalException {
		MBMessage message = _mbMessageLocalService.getMessage(classPK);

		return message.isInTrashContainer();
	}

	@Override
	public void restoreRelatedTrashEntry(String className, long classPK)
		throws PortalException {

		if (!className.equals(DLFileEntry.class.getName())) {
			return;
		}

		FileEntry fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(
			classPK);

		MBMessage message = MBMessageAttachmentsUtil.getMessage(classPK);

		_mbMessageService.restoreMessageAttachmentFromTrash(
			message.getMessageId(), fileEntry.getTitle());
	}

	@Override
	public void restoreTrashEntry(long userId, long classPK) {
	}

	@Override
	protected boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException {

		return MBMessagePermission.contains(
			permissionChecker, classPK, actionId);
	}

	@Reference(unbind = "-")
	protected void setMBMessageLocalService(
		MBMessageLocalService mbMessageLocalService) {

		_mbMessageLocalService = mbMessageLocalService;
	}

	@Reference(unbind = "-")
	protected void setMBMessageService(MBMessageService mbMessageService) {
		_mbMessageService = mbMessageService;
	}

	@Reference(unbind = "-")
	protected void setMBThreadLocalService(
		MBThreadLocalService mbThreadLocalService) {

		_mbThreadLocalService = mbThreadLocalService;
	}

	private MBMessageLocalService _mbMessageLocalService;
	private MBMessageService _mbMessageService;
	private MBThreadLocalService _mbThreadLocalService;

}