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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for MBCategory. This utility wraps
 * {@link com.liferay.portlet.messageboards.service.impl.MBCategoryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MBCategoryService
 * @see com.liferay.portlet.messageboards.service.base.MBCategoryServiceBaseImpl
 * @see com.liferay.portlet.messageboards.service.impl.MBCategoryServiceImpl
 * @generated
 */
@ProviderType
public class MBCategoryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.messageboards.service.impl.MBCategoryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.message.boards.kernel.model.MBCategory addCategory(
		long parentCategoryId, java.lang.String name,
		java.lang.String description, java.lang.String displayStyle,
		java.lang.String emailAddress, java.lang.String inProtocol,
		java.lang.String inServerName, int inServerPort, boolean inUseSSL,
		java.lang.String inUserName, java.lang.String inPassword,
		int inReadInterval, java.lang.String outEmailAddress,
		boolean outCustom, java.lang.String outServerName, int outServerPort,
		boolean outUseSSL, java.lang.String outUserName,
		java.lang.String outPassword, boolean mailingListActive,
		boolean allowAnonymousEmail,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCategory(parentCategoryId, name, description,
			displayStyle, emailAddress, inProtocol, inServerName, inServerPort,
			inUseSSL, inUserName, inPassword, inReadInterval, outEmailAddress,
			outCustom, outServerName, outServerPort, outUseSSL, outUserName,
			outPassword, mailingListActive, allowAnonymousEmail, serviceContext);
	}

	public static com.liferay.message.boards.kernel.model.MBCategory addCategory(
		long userId, long parentCategoryId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCategory(userId, parentCategoryId, name, description,
			serviceContext);
	}

	public static com.liferay.message.boards.kernel.model.MBCategory getCategory(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCategory(categoryId);
	}

	public static com.liferay.message.boards.kernel.model.MBCategory moveCategory(
		long categoryId, long parentCategoryId, boolean mergeWithParentCategory)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .moveCategory(categoryId, parentCategoryId,
			mergeWithParentCategory);
	}

	public static com.liferay.message.boards.kernel.model.MBCategory moveCategoryFromTrash(
		long categoryId, long newCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveCategoryFromTrash(categoryId, newCategoryId);
	}

	public static com.liferay.message.boards.kernel.model.MBCategory moveCategoryToTrash(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveCategoryToTrash(categoryId);
	}

	public static com.liferay.message.boards.kernel.model.MBCategory updateCategory(
		long categoryId, long parentCategoryId, java.lang.String name,
		java.lang.String description, java.lang.String displayStyle,
		java.lang.String emailAddress, java.lang.String inProtocol,
		java.lang.String inServerName, int inServerPort, boolean inUseSSL,
		java.lang.String inUserName, java.lang.String inPassword,
		int inReadInterval, java.lang.String outEmailAddress,
		boolean outCustom, java.lang.String outServerName, int outServerPort,
		boolean outUseSSL, java.lang.String outUserName,
		java.lang.String outPassword, boolean mailingListActive,
		boolean allowAnonymousEmail, boolean mergeWithParentCategory,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCategory(categoryId, parentCategoryId, name,
			description, displayStyle, emailAddress, inProtocol, inServerName,
			inServerPort, inUseSSL, inUserName, inPassword, inReadInterval,
			outEmailAddress, outCustom, outServerName, outServerPort,
			outUseSSL, outUserName, outPassword, mailingListActive,
			allowAnonymousEmail, mergeWithParentCategory, serviceContext);
	}

	public static int getCategoriesAndThreadsCount(long groupId, long categoryId) {
		return getService().getCategoriesAndThreadsCount(groupId, categoryId);
	}

	public static int getCategoriesAndThreadsCount(long groupId,
		long categoryId, int status) {
		return getService()
				   .getCategoriesAndThreadsCount(groupId, categoryId, status);
	}

	public static int getCategoriesCount(long groupId, long excludedCategoryId,
		long parentCategoryId, int status) {
		return getService()
				   .getCategoriesCount(groupId, excludedCategoryId,
			parentCategoryId, status);
	}

	public static int getCategoriesCount(long groupId, long parentCategoryId) {
		return getService().getCategoriesCount(groupId, parentCategoryId);
	}

	public static int getCategoriesCount(long groupId, long parentCategoryId,
		int status) {
		return getService().getCategoriesCount(groupId, parentCategoryId, status);
	}

	public static int getCategoriesCount(long groupId,
		long[] excludedCategoryIds, long[] parentCategoryIds, int status) {
		return getService()
				   .getCategoriesCount(groupId, excludedCategoryIds,
			parentCategoryIds, status);
	}

	public static int getCategoriesCount(long groupId, long[] parentCategoryIds) {
		return getService().getCategoriesCount(groupId, parentCategoryIds);
	}

	public static int getCategoriesCount(long groupId,
		long[] parentCategoryIds, int status) {
		return getService()
				   .getCategoriesCount(groupId, parentCategoryIds, status);
	}

	public static int getSubscribedCategoriesCount(long groupId, long userId) {
		return getService().getSubscribedCategoriesCount(groupId, userId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.message.boards.kernel.model.MBCategory> getCategories(
		long groupId) {
		return getService().getCategories(groupId);
	}

	public static java.util.List<com.liferay.message.boards.kernel.model.MBCategory> getCategories(
		long groupId, int status) {
		return getService().getCategories(groupId, status);
	}

	public static java.util.List<com.liferay.message.boards.kernel.model.MBCategory> getCategories(
		long groupId, long excludedCategoryId, long parentCategoryId,
		int status, int start, int end) {
		return getService()
				   .getCategories(groupId, excludedCategoryId,
			parentCategoryId, status, start, end);
	}

	public static java.util.List<com.liferay.message.boards.kernel.model.MBCategory> getCategories(
		long groupId, long parentCategoryId, int start, int end) {
		return getService().getCategories(groupId, parentCategoryId, start, end);
	}

	public static java.util.List<com.liferay.message.boards.kernel.model.MBCategory> getCategories(
		long groupId, long parentCategoryId, int status, int start, int end) {
		return getService()
				   .getCategories(groupId, parentCategoryId, status, start, end);
	}

	public static java.util.List<com.liferay.message.boards.kernel.model.MBCategory> getCategories(
		long groupId, long[] excludedCategoryIds, long[] parentCategoryIds,
		int status, int start, int end) {
		return getService()
				   .getCategories(groupId, excludedCategoryIds,
			parentCategoryIds, status, start, end);
	}

	public static java.util.List<com.liferay.message.boards.kernel.model.MBCategory> getCategories(
		long groupId, long[] parentCategoryIds, int start, int end) {
		return getService().getCategories(groupId, parentCategoryIds, start, end);
	}

	public static java.util.List<com.liferay.message.boards.kernel.model.MBCategory> getCategories(
		long groupId, long[] parentCategoryIds, int status, int start, int end) {
		return getService()
				   .getCategories(groupId, parentCategoryIds, status, start, end);
	}

	public static java.util.List<java.lang.Object> getCategoriesAndThreads(
		long groupId, long categoryId) {
		return getService().getCategoriesAndThreads(groupId, categoryId);
	}

	public static java.util.List<java.lang.Object> getCategoriesAndThreads(
		long groupId, long categoryId, int status) {
		return getService().getCategoriesAndThreads(groupId, categoryId, status);
	}

	public static java.util.List<java.lang.Object> getCategoriesAndThreads(
		long groupId, long categoryId, int status, int start, int end) {
		return getService()
				   .getCategoriesAndThreads(groupId, categoryId, status, start,
			end);
	}

	public static java.util.List<java.lang.Long> getSubcategoryIds(
		java.util.List<java.lang.Long> categoryIds, long groupId,
		long categoryId) {
		return getService().getSubcategoryIds(categoryIds, groupId, categoryId);
	}

	public static java.util.List<com.liferay.message.boards.kernel.model.MBCategory> getSubscribedCategories(
		long groupId, long userId, int start, int end) {
		return getService().getSubscribedCategories(groupId, userId, start, end);
	}

	public static long[] getCategoryIds(long groupId, long categoryId) {
		return getService().getCategoryIds(groupId, categoryId);
	}

	public static void deleteCategory(long categoryId,
		boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCategory(categoryId, includeTrashedEntries);
	}

	public static void deleteCategory(long groupId, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCategory(groupId, categoryId);
	}

	public static void restoreCategoryFromTrash(long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().restoreCategoryFromTrash(categoryId);
	}

	public static void subscribeCategory(long groupId, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().subscribeCategory(groupId, categoryId);
	}

	public static void unsubscribeCategory(long groupId, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().unsubscribeCategory(groupId, categoryId);
	}

	public static MBCategoryService getService() {
		if (_service == null) {
			_service = (MBCategoryService)PortalBeanLocatorUtil.locate(MBCategoryService.class.getName());

			ReferenceRegistry.registerReference(MBCategoryServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static MBCategoryService _service;
}