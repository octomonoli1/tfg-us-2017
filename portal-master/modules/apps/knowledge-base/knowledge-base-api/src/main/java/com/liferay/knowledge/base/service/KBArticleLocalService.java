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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.knowledge.base.model.KBArticle;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for KBArticle. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see KBArticleLocalServiceUtil
 * @see com.liferay.knowledge.base.service.base.KBArticleLocalServiceBaseImpl
 * @see com.liferay.knowledge.base.service.impl.KBArticleLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KBArticleLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KBArticleLocalServiceUtil} to access the k b article local service. Add custom service methods to {@link com.liferay.knowledge.base.service.impl.KBArticleLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the k b article to the database. Also notifies the appropriate model listeners.
	*
	* @param kbArticle the k b article
	* @return the k b article that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public KBArticle addKBArticle(KBArticle kbArticle);

	public KBArticle addKBArticle(long userId, long parentResourceClassNameId,
		long parentResourcePrimKey, java.lang.String title,
		java.lang.String urlTitle, java.lang.String content,
		java.lang.String description, java.lang.String sourceURL,
		java.lang.String[] sections, java.lang.String[] selectedFileNames,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Creates a new k b article with the primary key. Does not add the k b article to the database.
	*
	* @param kbArticleId the primary key for the new k b article
	* @return the new k b article
	*/
	public KBArticle createKBArticle(long kbArticleId);

	/**
	* Deletes the k b article from the database. Also notifies the appropriate model listeners.
	*
	* @param kbArticle the k b article
	* @return the k b article that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	@SystemEvent(action = SystemEventConstants.ACTION_SKIP, type = SystemEventConstants.TYPE_DELETE)
	public KBArticle deleteKBArticle(KBArticle kbArticle)
		throws PortalException;

	/**
	* Deletes the k b article with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbArticleId the primary key of the k b article
	* @return the k b article that was removed
	* @throws PortalException if a k b article with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public KBArticle deleteKBArticle(long kbArticleId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle fetchFirstChildKBArticle(long groupId,
		long parentResourcePrimKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle fetchKBArticle(long kbArticleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle fetchKBArticleByUrlTitle(long groupId,
		java.lang.String kbFolderUrlTitle, java.lang.String urlTitle)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle fetchKBArticleByUrlTitle(long groupId, long kbFolderId,
		java.lang.String urlTitle);

	/**
	* Returns the k b article matching the UUID and group.
	*
	* @param uuid the k b article's UUID
	* @param groupId the primary key of the group
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle fetchKBArticleByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle fetchLatestKBArticle(long resourcePrimKey, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle fetchLatestKBArticleByUrlTitle(long groupId,
		long kbFolderId, java.lang.String urlTitle, int status);

	/**
	* Returns the k b article with the primary key.
	*
	* @param kbArticleId the primary key of the k b article
	* @return the k b article
	* @throws PortalException if a k b article with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle getKBArticle(long kbArticleId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle getKBArticle(long resourcePrimKey, int version)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle getKBArticleByUrlTitle(long groupId,
		java.lang.String kbFolderUrlTitle, java.lang.String urlTitle)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle getKBArticleByUrlTitle(long groupId, long kbFolderId,
		java.lang.String urlTitle) throws PortalException;

	/**
	* Returns the k b article matching the UUID and group.
	*
	* @param uuid the k b article's UUID
	* @param groupId the primary key of the group
	* @return the matching k b article
	* @throws PortalException if a matching k b article could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle getKBArticleByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle getLatestKBArticle(long resourcePrimKey, int status)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle getLatestKBArticleByUrlTitle(long groupId,
		long kbFolderId, java.lang.String urlTitle, int status)
		throws PortalException;

	public KBArticle revertKBArticle(long userId, long resourcePrimKey,
		int version, ServiceContext serviceContext) throws PortalException;

	/**
	* Updates the k b article in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbArticle the k b article
	* @return the k b article that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public KBArticle updateKBArticle(KBArticle kbArticle);

	public KBArticle updateKBArticle(long userId, long resourcePrimKey,
		java.lang.String title, java.lang.String content,
		java.lang.String description, java.lang.String sourceURL,
		java.lang.String[] sections, java.lang.String[] selectedFileNames,
		long[] removeFileEntryIds, ServiceContext serviceContext)
		throws PortalException;

	public KBArticle updateStatus(long userId, long resourcePrimKey,
		int status, ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle[] getPreviousAndNextKBArticles(long kbArticleId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	public FileEntry addAttachment(long userId, long resourcePrimKey,
		java.lang.String fileName, InputStream inputStream,
		java.lang.String mimeType) throws PortalException;

	public int addKBArticlesMarkdown(long userId, long groupId,
		long parentKbFolderId, java.lang.String fileName,
		boolean prioritizeByNumericalPrefix, InputStream inputStream,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCompanyKBArticlesCount(long companyId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupKBArticlesCount(long groupId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBArticleVersionsCount(long resourcePrimKey, int status);

	/**
	* Returns the number of k b articles.
	*
	* @return the number of k b articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBArticlesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBArticlesCount(long groupId, long parentResourcePrimKey,
		int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBFolderKBArticlesCount(long groupId, long kbFolderId,
		int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSectionsKBArticlesCount(long groupId,
		java.lang.String[] sections, int status);

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getKBArticlesCount(long,
	long, int)}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSiblingKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String[] getTempAttachmentNames(long groupId, long userId,
		java.lang.String tempFolderName) throws PortalException;

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getAllDescendantKBArticles(long resourcePrimKey,
		int status, OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getCompanyKBArticles(long companyId, int status,
		int start, int end, OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getGroupKBArticles(long groupId, int status,
		int start, int end, OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticleAndAllDescendantKBArticles(
		long resourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator);

	/**
	* @deprecated As of 7.0.0, replaced by {@link
	#getKBArticleAndAllDescendantKBArticles(long, int,
	OrderByComparator)}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticleAndAllDescendants(long resourcePrimKey,
		int status, OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticleVersions(long resourcePrimKey,
		int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticles(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticles(long groupId,
		long parentResourcePrimKey, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticles(long[] resourcePrimKeys, int status,
		OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns all the k b articles matching the UUID and company.
	*
	* @param uuid the UUID of the k b articles
	* @param companyId the primary key of the company
	* @return the matching k b articles, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticlesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticlesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBFolderKBArticles(long groupId, long kbFolderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getSectionsKBArticles(long groupId,
		java.lang.String[] sections, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator);

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getKBArticles(long, long,
	int, int, int,
	OrderByComparator)}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getSiblingKBArticles(long groupId,
		long parentResourcePrimKey, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> search(long groupId, java.lang.String title,
		java.lang.String content, int status, Date startDate, Date endDate,
		boolean andOperator, int start, int end,
		OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void addKBArticleResources(KBArticle kbArticle,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws PortalException;

	public void addKBArticleResources(KBArticle kbArticle,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws PortalException;

	public void addKBArticleResources(long kbArticleId,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws PortalException;

	public void addKBArticleResources(long kbArticleId,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws PortalException;

	public void addTempAttachment(long groupId, long userId,
		java.lang.String fileName, java.lang.String tempFolderName,
		InputStream inputStream, java.lang.String mimeType)
		throws PortalException;

	public void deleteGroupKBArticles(long groupId) throws PortalException;

	public void deleteKBArticles(long groupId, long parentResourcePrimKey)
		throws PortalException;

	public void deleteKBArticles(long[] resourcePrimKeys)
		throws PortalException;

	public void deleteTempAttachment(long groupId, long userId,
		java.lang.String fileName, java.lang.String tempFolderName)
		throws PortalException;

	public void moveKBArticle(long userId, long resourcePrimKey,
		long parentResourceClassNameId, long parentResourcePrimKey,
		double priority) throws PortalException;

	public void subscribeGroupKBArticles(long userId, long groupId)
		throws PortalException;

	public void subscribeKBArticle(long userId, long groupId,
		long resourcePrimKey) throws PortalException;

	public void unsubscribeGroupKBArticles(long userId, long groupId)
		throws PortalException;

	public void unsubscribeKBArticle(long userId, long resourcePrimKey)
		throws PortalException;

	public void updateKBArticleAsset(long userId, KBArticle kbArticle,
		long[] assetCategoryIds, java.lang.String[] assetTagNames,
		long[] assetLinkEntryIds) throws PortalException;

	public void updateKBArticleResources(KBArticle kbArticle,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws PortalException;

	public void updateKBArticlesPriorities(
		Map<java.lang.Long, java.lang.Double> resourcePrimKeyToPriorityMap)
		throws PortalException;

	public void updatePriority(long resourcePrimKey, double priority);

	public void updateViewCount(long userId, long resourcePrimKey, int viewCount)
		throws PortalException;
}