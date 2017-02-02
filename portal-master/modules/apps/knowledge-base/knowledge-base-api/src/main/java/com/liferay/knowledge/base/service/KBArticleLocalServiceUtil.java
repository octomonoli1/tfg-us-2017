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

package com.liferay.knowledge.base.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for KBArticle. This utility wraps
 * {@link com.liferay.knowledge.base.service.impl.KBArticleLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KBArticleLocalService
 * @see com.liferay.knowledge.base.service.base.KBArticleLocalServiceBaseImpl
 * @see com.liferay.knowledge.base.service.impl.KBArticleLocalServiceImpl
 * @generated
 */
@ProviderType
public class KBArticleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledge.base.service.impl.KBArticleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the k b article to the database. Also notifies the appropriate model listeners.
	*
	* @param kbArticle the k b article
	* @return the k b article that was added
	*/
	public static com.liferay.knowledge.base.model.KBArticle addKBArticle(
		com.liferay.knowledge.base.model.KBArticle kbArticle) {
		return getService().addKBArticle(kbArticle);
	}

	public static com.liferay.knowledge.base.model.KBArticle addKBArticle(
		long userId, long parentResourceClassNameId,
		long parentResourcePrimKey, java.lang.String title,
		java.lang.String urlTitle, java.lang.String content,
		java.lang.String description, java.lang.String sourceURL,
		java.lang.String[] sections, java.lang.String[] selectedFileNames,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addKBArticle(userId, parentResourceClassNameId,
			parentResourcePrimKey, title, urlTitle, content, description,
			sourceURL, sections, selectedFileNames, serviceContext);
	}

	/**
	* Creates a new k b article with the primary key. Does not add the k b article to the database.
	*
	* @param kbArticleId the primary key for the new k b article
	* @return the new k b article
	*/
	public static com.liferay.knowledge.base.model.KBArticle createKBArticle(
		long kbArticleId) {
		return getService().createKBArticle(kbArticleId);
	}

	/**
	* Deletes the k b article from the database. Also notifies the appropriate model listeners.
	*
	* @param kbArticle the k b article
	* @return the k b article that was removed
	* @throws PortalException
	*/
	public static com.liferay.knowledge.base.model.KBArticle deleteKBArticle(
		com.liferay.knowledge.base.model.KBArticle kbArticle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteKBArticle(kbArticle);
	}

	/**
	* Deletes the k b article with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbArticleId the primary key of the k b article
	* @return the k b article that was removed
	* @throws PortalException if a k b article with the primary key could not be found
	*/
	public static com.liferay.knowledge.base.model.KBArticle deleteKBArticle(
		long kbArticleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteKBArticle(kbArticleId);
	}

	public static com.liferay.knowledge.base.model.KBArticle fetchFirstChildKBArticle(
		long groupId, long parentResourcePrimKey) {
		return getService()
				   .fetchFirstChildKBArticle(groupId, parentResourcePrimKey);
	}

	public static com.liferay.knowledge.base.model.KBArticle fetchKBArticle(
		long kbArticleId) {
		return getService().fetchKBArticle(kbArticleId);
	}

	public static com.liferay.knowledge.base.model.KBArticle fetchKBArticleByUrlTitle(
		long groupId, java.lang.String kbFolderUrlTitle,
		java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .fetchKBArticleByUrlTitle(groupId, kbFolderUrlTitle, urlTitle);
	}

	public static com.liferay.knowledge.base.model.KBArticle fetchKBArticleByUrlTitle(
		long groupId, long kbFolderId, java.lang.String urlTitle) {
		return getService()
				   .fetchKBArticleByUrlTitle(groupId, kbFolderId, urlTitle);
	}

	/**
	* Returns the k b article matching the UUID and group.
	*
	* @param uuid the k b article's UUID
	* @param groupId the primary key of the group
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public static com.liferay.knowledge.base.model.KBArticle fetchKBArticleByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchKBArticleByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.knowledge.base.model.KBArticle fetchLatestKBArticle(
		long resourcePrimKey, int status) {
		return getService().fetchLatestKBArticle(resourcePrimKey, status);
	}

	public static com.liferay.knowledge.base.model.KBArticle fetchLatestKBArticleByUrlTitle(
		long groupId, long kbFolderId, java.lang.String urlTitle, int status) {
		return getService()
				   .fetchLatestKBArticleByUrlTitle(groupId, kbFolderId,
			urlTitle, status);
	}

	/**
	* Returns the k b article with the primary key.
	*
	* @param kbArticleId the primary key of the k b article
	* @return the k b article
	* @throws PortalException if a k b article with the primary key could not be found
	*/
	public static com.liferay.knowledge.base.model.KBArticle getKBArticle(
		long kbArticleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKBArticle(kbArticleId);
	}

	public static com.liferay.knowledge.base.model.KBArticle getKBArticle(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKBArticle(resourcePrimKey, version);
	}

	public static com.liferay.knowledge.base.model.KBArticle getKBArticleByUrlTitle(
		long groupId, java.lang.String kbFolderUrlTitle,
		java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getKBArticleByUrlTitle(groupId, kbFolderUrlTitle, urlTitle);
	}

	public static com.liferay.knowledge.base.model.KBArticle getKBArticleByUrlTitle(
		long groupId, long kbFolderId, java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKBArticleByUrlTitle(groupId, kbFolderId, urlTitle);
	}

	/**
	* Returns the k b article matching the UUID and group.
	*
	* @param uuid the k b article's UUID
	* @param groupId the primary key of the group
	* @return the matching k b article
	* @throws PortalException if a matching k b article could not be found
	*/
	public static com.liferay.knowledge.base.model.KBArticle getKBArticleByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKBArticleByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.knowledge.base.model.KBArticle getLatestKBArticle(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLatestKBArticle(resourcePrimKey, status);
	}

	public static com.liferay.knowledge.base.model.KBArticle getLatestKBArticleByUrlTitle(
		long groupId, long kbFolderId, java.lang.String urlTitle, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLatestKBArticleByUrlTitle(groupId, kbFolderId, urlTitle,
			status);
	}

	public static com.liferay.knowledge.base.model.KBArticle revertKBArticle(
		long userId, long resourcePrimKey, int version,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .revertKBArticle(userId, resourcePrimKey, version,
			serviceContext);
	}

	/**
	* Updates the k b article in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbArticle the k b article
	* @return the k b article that was updated
	*/
	public static com.liferay.knowledge.base.model.KBArticle updateKBArticle(
		com.liferay.knowledge.base.model.KBArticle kbArticle) {
		return getService().updateKBArticle(kbArticle);
	}

	public static com.liferay.knowledge.base.model.KBArticle updateKBArticle(
		long userId, long resourcePrimKey, java.lang.String title,
		java.lang.String content, java.lang.String description,
		java.lang.String sourceURL, java.lang.String[] sections,
		java.lang.String[] selectedFileNames, long[] removeFileEntryIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateKBArticle(userId, resourcePrimKey, title, content,
			description, sourceURL, sections, selectedFileNames,
			removeFileEntryIds, serviceContext);
	}

	public static com.liferay.knowledge.base.model.KBArticle updateStatus(
		long userId, long resourcePrimKey, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, resourcePrimKey, status, serviceContext);
	}

	public static com.liferay.knowledge.base.model.KBArticle[] getPreviousAndNextKBArticles(
		long kbArticleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPreviousAndNextKBArticles(kbArticleId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
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

	public static com.liferay.portal.kernel.repository.model.FileEntry addAttachment(
		long userId, long resourcePrimKey, java.lang.String fileName,
		java.io.InputStream inputStream, java.lang.String mimeType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addAttachment(userId, resourcePrimKey, fileName,
			inputStream, mimeType);
	}

	public static int addKBArticlesMarkdown(long userId, long groupId,
		long parentKbFolderId, java.lang.String fileName,
		boolean prioritizeByNumericalPrefix, java.io.InputStream inputStream,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addKBArticlesMarkdown(userId, groupId, parentKbFolderId,
			fileName, prioritizeByNumericalPrefix, inputStream, serviceContext);
	}

	public static int getCompanyKBArticlesCount(long companyId, int status) {
		return getService().getCompanyKBArticlesCount(companyId, status);
	}

	public static int getGroupKBArticlesCount(long groupId, int status) {
		return getService().getGroupKBArticlesCount(groupId, status);
	}

	public static int getKBArticleVersionsCount(long resourcePrimKey, int status) {
		return getService().getKBArticleVersionsCount(resourcePrimKey, status);
	}

	/**
	* Returns the number of k b articles.
	*
	* @return the number of k b articles
	*/
	public static int getKBArticlesCount() {
		return getService().getKBArticlesCount();
	}

	public static int getKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status) {
		return getService()
				   .getKBArticlesCount(groupId, parentResourcePrimKey, status);
	}

	public static int getKBFolderKBArticlesCount(long groupId, long kbFolderId,
		int status) {
		return getService()
				   .getKBFolderKBArticlesCount(groupId, kbFolderId, status);
	}

	public static int getSectionsKBArticlesCount(long groupId,
		java.lang.String[] sections, int status) {
		return getService().getSectionsKBArticlesCount(groupId, sections, status);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getKBArticlesCount(long,
	long, int)}
	*/
	@Deprecated
	public static int getSiblingKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status) {
		return getService()
				   .getSiblingKBArticlesCount(groupId, parentResourcePrimKey,
			status);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.lang.String[] getTempAttachmentNames(long groupId,
		long userId, java.lang.String tempFolderName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getTempAttachmentNames(groupId, userId, tempFolderName);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledge.base.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledge.base.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getAllDescendantKBArticles(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledge.base.model.KBArticle> orderByComparator) {
		return getService()
				   .getAllDescendantKBArticles(resourcePrimKey, status,
			orderByComparator);
	}

	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getCompanyKBArticles(
		long companyId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledge.base.model.KBArticle> orderByComparator) {
		return getService()
				   .getCompanyKBArticles(companyId, status, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getGroupKBArticles(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledge.base.model.KBArticle> orderByComparator) {
		return getService()
				   .getGroupKBArticles(groupId, status, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getKBArticleAndAllDescendantKBArticles(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledge.base.model.KBArticle> orderByComparator) {
		return getService()
				   .getKBArticleAndAllDescendantKBArticles(resourcePrimKey,
			status, orderByComparator);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link
	#getKBArticleAndAllDescendantKBArticles(long, int,
	OrderByComparator)}
	*/
	@Deprecated
	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getKBArticleAndAllDescendants(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledge.base.model.KBArticle> orderByComparator) {
		return getService()
				   .getKBArticleAndAllDescendants(resourcePrimKey, status,
			orderByComparator);
	}

	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getKBArticleVersions(
		long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledge.base.model.KBArticle> orderByComparator) {
		return getService()
				   .getKBArticleVersions(resourcePrimKey, status, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the k b articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledge.base.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of k b articles
	*/
	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getKBArticles(
		int start, int end) {
		return getService().getKBArticles(start, end);
	}

	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getKBArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledge.base.model.KBArticle> orderByComparator) {
		return getService()
				   .getKBArticles(groupId, parentResourcePrimKey, status,
			start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getKBArticles(
		long[] resourcePrimKeys, int status,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledge.base.model.KBArticle> orderByComparator) {
		return getService()
				   .getKBArticles(resourcePrimKeys, status, orderByComparator);
	}

	/**
	* Returns all the k b articles matching the UUID and company.
	*
	* @param uuid the UUID of the k b articles
	* @param companyId the primary key of the company
	* @return the matching k b articles, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getKBArticlesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getKBArticlesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of k b articles matching the UUID and company.
	*
	* @param uuid the UUID of the k b articles
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching k b articles, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getKBArticlesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledge.base.model.KBArticle> orderByComparator) {
		return getService()
				   .getKBArticlesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getKBFolderKBArticles(
		long groupId, long kbFolderId) {
		return getService().getKBFolderKBArticles(groupId, kbFolderId);
	}

	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getSectionsKBArticles(
		long groupId, java.lang.String[] sections, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledge.base.model.KBArticle> orderByComparator) {
		return getService()
				   .getSectionsKBArticles(groupId, sections, status, start,
			end, orderByComparator);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getKBArticles(long, long,
	int, int, int,
	OrderByComparator)}
	*/
	@Deprecated
	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> getSiblingKBArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledge.base.model.KBArticle> orderByComparator) {
		return getService()
				   .getSiblingKBArticles(groupId, parentResourcePrimKey,
			status, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.knowledge.base.model.KBArticle> search(
		long groupId, java.lang.String title, java.lang.String content,
		int status, java.util.Date startDate, java.util.Date endDate,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledge.base.model.KBArticle> orderByComparator) {
		return getService()
				   .search(groupId, title, content, status, startDate, endDate,
			andOperator, start, end, orderByComparator);
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

	public static void addKBArticleResources(
		com.liferay.knowledge.base.model.KBArticle kbArticle,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addKBArticleResources(kbArticle, addGroupPermissions,
			addGuestPermissions);
	}

	public static void addKBArticleResources(
		com.liferay.knowledge.base.model.KBArticle kbArticle,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addKBArticleResources(kbArticle, groupPermissions, guestPermissions);
	}

	public static void addKBArticleResources(long kbArticleId,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addKBArticleResources(kbArticleId, addGroupPermissions,
			addGuestPermissions);
	}

	public static void addKBArticleResources(long kbArticleId,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addKBArticleResources(kbArticleId, groupPermissions,
			guestPermissions);
	}

	public static void addTempAttachment(long groupId, long userId,
		java.lang.String fileName, java.lang.String tempFolderName,
		java.io.InputStream inputStream, java.lang.String mimeType)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addTempAttachment(groupId, userId, fileName, tempFolderName,
			inputStream, mimeType);
	}

	public static void deleteGroupKBArticles(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteGroupKBArticles(groupId);
	}

	public static void deleteKBArticles(long groupId, long parentResourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteKBArticles(groupId, parentResourcePrimKey);
	}

	public static void deleteKBArticles(long[] resourcePrimKeys)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteKBArticles(resourcePrimKeys);
	}

	public static void deleteTempAttachment(long groupId, long userId,
		java.lang.String fileName, java.lang.String tempFolderName)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteTempAttachment(groupId, userId, fileName, tempFolderName);
	}

	public static void moveKBArticle(long userId, long resourcePrimKey,
		long parentResourceClassNameId, long parentResourcePrimKey,
		double priority)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.moveKBArticle(userId, resourcePrimKey, parentResourceClassNameId,
			parentResourcePrimKey, priority);
	}

	public static void subscribeGroupKBArticles(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().subscribeGroupKBArticles(userId, groupId);
	}

	public static void subscribeKBArticle(long userId, long groupId,
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().subscribeKBArticle(userId, groupId, resourcePrimKey);
	}

	public static void unsubscribeGroupKBArticles(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().unsubscribeGroupKBArticles(userId, groupId);
	}

	public static void unsubscribeKBArticle(long userId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().unsubscribeKBArticle(userId, resourcePrimKey);
	}

	public static void updateKBArticleAsset(long userId,
		com.liferay.knowledge.base.model.KBArticle kbArticle,
		long[] assetCategoryIds, java.lang.String[] assetTagNames,
		long[] assetLinkEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateKBArticleAsset(userId, kbArticle, assetCategoryIds,
			assetTagNames, assetLinkEntryIds);
	}

	public static void updateKBArticleResources(
		com.liferay.knowledge.base.model.KBArticle kbArticle,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateKBArticleResources(kbArticle, groupPermissions,
			guestPermissions);
	}

	public static void updateKBArticlesPriorities(
		java.util.Map<java.lang.Long, java.lang.Double> resourcePrimKeyToPriorityMap)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateKBArticlesPriorities(resourcePrimKeyToPriorityMap);
	}

	public static void updatePriority(long resourcePrimKey, double priority) {
		getService().updatePriority(resourcePrimKey, priority);
	}

	public static void updateViewCount(long userId, long resourcePrimKey,
		int viewCount)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateViewCount(userId, resourcePrimKey, viewCount);
	}

	public static KBArticleLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<KBArticleLocalService, KBArticleLocalService> _serviceTracker =
		ServiceTrackerFactory.open(KBArticleLocalService.class);
}