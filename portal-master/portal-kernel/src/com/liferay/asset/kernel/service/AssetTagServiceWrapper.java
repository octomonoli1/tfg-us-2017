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

package com.liferay.asset.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetTagService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagService
 * @generated
 */
@ProviderType
public class AssetTagServiceWrapper implements AssetTagService,
	ServiceWrapper<AssetTagService> {
	public AssetTagServiceWrapper(AssetTagService assetTagService) {
		_assetTagService = assetTagService;
	}

	@Override
	public com.liferay.asset.kernel.model.AssetTag addTag(long groupId,
		java.lang.String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetTagService.addTag(groupId, name, serviceContext);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetTag getTag(long tagId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetTagService.getTag(tagId);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetTag updateTag(long tagId,
		java.lang.String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetTagService.updateTag(tagId, name, serviceContext);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetTagDisplay getGroupTagsDisplay(
		long groupId, java.lang.String name, int start, int end) {
		return _assetTagService.getGroupTagsDisplay(groupId, name, start, end);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray search(long groupId,
		java.lang.String name, int start, int end) {
		return _assetTagService.search(groupId, name, start, end);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray search(long[] groupIds,
		java.lang.String name, int start, int end) {
		return _assetTagService.search(groupIds, name, start, end);
	}

	@Override
	public int getGroupTagsCount(long groupId) {
		return _assetTagService.getGroupTagsCount(groupId);
	}

	@Override
	public int getTagsCount(long groupId, java.lang.String name) {
		return _assetTagService.getTagsCount(groupId, name);
	}

	@Override
	public int getVisibleAssetsTagsCount(long groupId, java.lang.String name) {
		return _assetTagService.getVisibleAssetsTagsCount(groupId, name);
	}

	@Override
	public int getVisibleAssetsTagsCount(long groupId, long classNameId,
		java.lang.String name) {
		return _assetTagService.getVisibleAssetsTagsCount(groupId, classNameId,
			name);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _assetTagService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetTag> getGroupTags(
		long groupId) {
		return _assetTagService.getGroupTags(groupId);
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetTag> getGroupTags(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetTag> obc) {
		return _assetTagService.getGroupTags(groupId, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetTag> getGroupsTags(
		long[] groupIds) {
		return _assetTagService.getGroupsTags(groupIds);
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetTag> getTags(
		java.lang.String className, long classPK) {
		return _assetTagService.getTags(className, classPK);
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetTag> getTags(
		long groupId, java.lang.String name, int start, int end) {
		return _assetTagService.getTags(groupId, name, start, end);
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetTag> getTags(
		long groupId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetTag> obc) {
		return _assetTagService.getTags(groupId, name, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetTag> getTags(
		long groupId, long classNameId, java.lang.String name) {
		return _assetTagService.getTags(groupId, classNameId, name);
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetTag> getTags(
		long groupId, long classNameId, java.lang.String name, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetTag> obc) {
		return _assetTagService.getTags(groupId, classNameId, name, start, end,
			obc);
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetTag> getTags(
		long[] groupIds, java.lang.String name, int start, int end) {
		return _assetTagService.getTags(groupIds, name, start, end);
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetTag> getTags(
		long[] groupIds, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetTag> obc) {
		return _assetTagService.getTags(groupIds, name, start, end, obc);
	}

	@Override
	public void deleteTag(long tagId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_assetTagService.deleteTag(tagId);
	}

	@Override
	public void deleteTags(long[] tagIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_assetTagService.deleteTags(tagIds);
	}

	@Override
	public void mergeTags(long fromTagId, long toTagId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_assetTagService.mergeTags(fromTagId, toTagId);
	}

	@Override
	public void mergeTags(long[] fromTagIds, long toTagId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_assetTagService.mergeTags(fromTagIds, toTagId);
	}

	@Override
	public AssetTagService getWrappedService() {
		return _assetTagService;
	}

	@Override
	public void setWrappedService(AssetTagService assetTagService) {
		_assetTagService = assetTagService;
	}

	private AssetTagService _assetTagService;
}