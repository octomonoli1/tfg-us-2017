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

package com.liferay.wiki.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.service.base.WikiNodeServiceBaseImpl;
import com.liferay.wiki.service.permission.WikiNodePermissionChecker;
import com.liferay.wiki.service.permission.WikiResourcePermissionChecker;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Provides the remote service for accessing, adding, deleting, importing,
 * subscription handling of, trash handling of, and updating wiki nodes. Its
 * methods include permission checks.
 *
 * @author Brian Wing Shun Chan
 * @author Charles May
 */
public class WikiNodeServiceImpl extends WikiNodeServiceBaseImpl {

	@Override
	public WikiNode addNode(
			String name, String description, ServiceContext serviceContext)
		throws PortalException {

		WikiResourcePermissionChecker.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_NODE);

		return wikiNodeLocalService.addNode(
			getUserId(), name, description, serviceContext);
	}

	@Override
	public void deleteNode(long nodeId) throws PortalException {
		WikiNodePermissionChecker.check(
			getPermissionChecker(), nodeId, ActionKeys.DELETE);

		wikiNodeLocalService.deleteNode(nodeId);
	}

	@Override
	public WikiNode getNode(long nodeId) throws PortalException {
		WikiNodePermissionChecker.check(
			getPermissionChecker(), nodeId, ActionKeys.VIEW);

		return wikiNodeLocalService.getNode(nodeId);
	}

	@Override
	public WikiNode getNode(long groupId, String name) throws PortalException {
		WikiNodePermissionChecker.check(
			getPermissionChecker(), groupId, name, ActionKeys.VIEW);

		return wikiNodeLocalService.getNode(groupId, name);
	}

	@Override
	public List<WikiNode> getNodes(long groupId) throws PortalException {
		return getNodes(groupId, WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public List<WikiNode> getNodes(long groupId, int status)
		throws PortalException {

		List<WikiNode> nodes = wikiNodePersistence.filterFindByG_S(
			groupId, status);

		if (nodes.isEmpty()) {
			nodes = new ArrayList<>();

			List<WikiNode> allNodes = wikiNodeLocalService.getNodes(
				groupId, status);

			for (WikiNode node : allNodes) {
				if (WikiNodePermissionChecker.contains(
						getPermissionChecker(), node, ActionKeys.VIEW)) {

					nodes.add(node);
				}
			}
		}

		return nodes;
	}

	@Override
	public List<WikiNode> getNodes(long groupId, int start, int end) {
		return getNodes(groupId, WorkflowConstants.STATUS_APPROVED, start, end);
	}

	@Override
	public List<WikiNode> getNodes(
		long groupId, int status, int start, int end) {

		return wikiNodePersistence.filterFindByG_S(groupId, status, start, end);
	}

	@Override
	public List<WikiNode> getNodes(
		long groupId, int status, int start, int end,
		OrderByComparator<WikiNode> obc) {

		return wikiNodePersistence.filterFindByG_S(
			groupId, status, start, end, obc);
	}

	@Override
	public int getNodesCount(long groupId) {
		return getNodesCount(groupId, WorkflowConstants.STATUS_APPROVED);
	}

	@Override
	public int getNodesCount(long groupId, int status) {
		return wikiNodePersistence.filterCountByG_S(groupId, status);
	}

	@Override
	public void importPages(
			long nodeId, String importer, InputStream[] inputStreams,
			Map<String, String[]> options)
		throws PortalException {

		WikiNodePermissionChecker.check(
			getPermissionChecker(), nodeId, ActionKeys.IMPORT);

		wikiNodeLocalService.importPages(
			getUserId(), nodeId, importer, inputStreams, options);
	}

	@Override
	public WikiNode moveNodeToTrash(long nodeId) throws PortalException {
		WikiNodePermissionChecker.check(
			getPermissionChecker(), nodeId, ActionKeys.DELETE);

		return wikiNodeLocalService.moveNodeToTrash(getUserId(), nodeId);
	}

	@Override
	public void restoreNodeFromTrash(long nodeId) throws PortalException {
		WikiNode node = wikiNodeLocalService.getNode(nodeId);

		WikiNodePermissionChecker.check(
			getPermissionChecker(), nodeId, ActionKeys.DELETE);

		wikiNodeLocalService.restoreNodeFromTrash(getUserId(), node);
	}

	@Override
	public void subscribeNode(long nodeId) throws PortalException {
		WikiNodePermissionChecker.check(
			getPermissionChecker(), nodeId, ActionKeys.SUBSCRIBE);

		wikiNodeLocalService.subscribeNode(getUserId(), nodeId);
	}

	@Override
	public void unsubscribeNode(long nodeId) throws PortalException {
		WikiNodePermissionChecker.check(
			getPermissionChecker(), nodeId, ActionKeys.SUBSCRIBE);

		wikiNodeLocalService.unsubscribeNode(getUserId(), nodeId);
	}

	@Override
	public WikiNode updateNode(
			long nodeId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		WikiNodePermissionChecker.check(
			getPermissionChecker(), nodeId, ActionKeys.UPDATE);

		return wikiNodeLocalService.updateNode(
			nodeId, name, description, serviceContext);
	}

}