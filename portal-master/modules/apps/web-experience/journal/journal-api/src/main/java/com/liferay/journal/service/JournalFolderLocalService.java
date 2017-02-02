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

package com.liferay.journal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.mapping.model.DDMStructure;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.journal.exception.NoSuchFolderException;
import com.liferay.journal.model.JournalFolder;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
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

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for JournalFolder. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see JournalFolderLocalServiceUtil
 * @see com.liferay.journal.service.base.JournalFolderLocalServiceBaseImpl
 * @see com.liferay.journal.service.impl.JournalFolderLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface JournalFolderLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JournalFolderLocalServiceUtil} to access the journal folder local service. Add custom service methods to {@link com.liferay.journal.service.impl.JournalFolderLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public JournalFolder addFolder(long userId, long groupId,
		long parentFolderId, java.lang.String name,
		java.lang.String description, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Adds the journal folder to the database. Also notifies the appropriate model listeners.
	*
	* @param journalFolder the journal folder
	* @return the journal folder that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public JournalFolder addJournalFolder(JournalFolder journalFolder);

	/**
	* Creates a new journal folder with the primary key. Does not add the journal folder to the database.
	*
	* @param folderId the primary key for the new journal folder
	* @return the new journal folder
	*/
	public JournalFolder createJournalFolder(long folderId);

	@Indexable(type = IndexableType.DELETE)
	@SystemEvent(action = SystemEventConstants.ACTION_SKIP, type = SystemEventConstants.TYPE_DELETE)
	public JournalFolder deleteFolder(JournalFolder folder)
		throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	@SystemEvent(action = SystemEventConstants.ACTION_SKIP, type = SystemEventConstants.TYPE_DELETE)
	public JournalFolder deleteFolder(JournalFolder folder,
		boolean includeTrashedEntries) throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	public JournalFolder deleteFolder(long folderId) throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	public JournalFolder deleteFolder(long folderId,
		boolean includeTrashedEntries) throws PortalException;

	/**
	* Deletes the journal folder from the database. Also notifies the appropriate model listeners.
	*
	* @param journalFolder the journal folder
	* @return the journal folder that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public JournalFolder deleteJournalFolder(JournalFolder journalFolder);

	/**
	* Deletes the journal folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param folderId the primary key of the journal folder
	* @return the journal folder that was removed
	* @throws PortalException if a journal folder with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public JournalFolder deleteJournalFolder(long folderId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalFolder fetchFolder(long folderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalFolder fetchFolder(long groupId, java.lang.String name);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalFolder fetchFolder(long groupId, long parentFolderId,
		java.lang.String name);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalFolder fetchJournalFolder(long folderId);

	/**
	* Returns the journal folder matching the UUID and group.
	*
	* @param uuid the journal folder's UUID
	* @param groupId the primary key of the group
	* @return the matching journal folder, or <code>null</code> if a matching journal folder could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalFolder fetchJournalFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalFolder getFolder(long folderId) throws PortalException;

	/**
	* Returns the journal folder with the primary key.
	*
	* @param folderId the primary key of the journal folder
	* @return the journal folder
	* @throws PortalException if a journal folder with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalFolder getJournalFolder(long folderId)
		throws PortalException;

	/**
	* Returns the journal folder matching the UUID and group.
	*
	* @param uuid the journal folder's UUID
	* @param groupId the primary key of the group
	* @return the matching journal folder
	* @throws PortalException if a matching journal folder could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalFolder getJournalFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public JournalFolder moveFolder(long folderId, long parentFolderId,
		ServiceContext serviceContext) throws PortalException;

	public JournalFolder moveFolderFromTrash(long userId, long folderId,
		long parentFolderId, ServiceContext serviceContext)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public JournalFolder moveFolderToTrash(long userId, long folderId)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public JournalFolder updateFolder(long userId, long groupId, long folderId,
		long parentFolderId, java.lang.String name,
		java.lang.String description, boolean mergeWithParentFolder,
		ServiceContext serviceContext) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public JournalFolder updateFolder(long userId, long groupId, long folderId,
		long parentFolderId, java.lang.String name,
		java.lang.String description, long[] ddmStructureIds,
		int restrictionType, boolean mergeWithParentFolder,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Updates the journal folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param journalFolder the journal folder
	* @return the journal folder that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public JournalFolder updateJournalFolder(JournalFolder journalFolder);

	public JournalFolder updateStatus(long userId, JournalFolder folder,
		int status) throws PortalException;

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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCompanyFoldersCount(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFoldersAndArticlesCount(long groupId,
		List<java.lang.Long> folderIds, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFoldersAndArticlesCount(long groupId, long folderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFoldersAndArticlesCount(long groupId, long folderId,
		int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFoldersCount(long groupId, long parentFolderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFoldersCount(long groupId, long parentFolderId, int status);

	/**
	* Returns the number of journal folders.
	*
	* @return the number of journal folders
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getJournalFoldersCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.journal.model.impl.JournalFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.journal.model.impl.JournalFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<JournalFolder> getCompanyFolders(long companyId, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMStructure> getDDMStructures(long[] groupIds, long folderId,
		int restrictionType) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalFolder> getFolders(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalFolder> getFolders(long groupId, long parentFolderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalFolder> getFolders(long groupId, long parentFolderId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalFolder> getFolders(long groupId, long parentFolderId,
		int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalFolder> getFolders(long groupId, long parentFolderId,
		int status, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<java.lang.Object> getFoldersAndArticles(long groupId,
		long folderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<java.lang.Object> getFoldersAndArticles(long groupId,
		long folderId, int start, int end, OrderByComparator<?> obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<java.lang.Object> getFoldersAndArticles(long groupId,
		long folderId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<java.lang.Object> getFoldersAndArticles(long groupId,
		long folderId, int status, int start, int end, OrderByComparator<?> obc);

	/**
	* Returns a range of all the journal folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.journal.model.impl.JournalFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of journal folders
	* @param end the upper bound of the range of journal folders (not inclusive)
	* @return the range of journal folders
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalFolder> getJournalFolders(int start, int end);

	/**
	* Returns all the journal folders matching the UUID and company.
	*
	* @param uuid the UUID of the journal folders
	* @param companyId the primary key of the company
	* @return the matching journal folders, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalFolder> getJournalFoldersByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of journal folders matching the UUID and company.
	*
	* @param uuid the UUID of the journal folders
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of journal folders
	* @param end the upper bound of the range of journal folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching journal folders, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalFolder> getJournalFoldersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<JournalFolder> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalFolder> getNoAssetFolders();

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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getInheritedWorkflowFolderId(long folderId)
		throws NoSuchFolderException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getOverridedDDMStructuresFolderId(long folderId)
		throws NoSuchFolderException;

	public void deleteFolders(long groupId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void getSubfolderIds(List<java.lang.Long> folderIds, long groupId,
		long folderId);

	public void rebuildTree(long companyId) throws PortalException;

	public void rebuildTree(long companyId, long parentFolderId,
		java.lang.String parentTreePath, boolean reindex)
		throws PortalException;

	public void restoreFolderFromTrash(long userId, long folderId)
		throws PortalException;

	public void subscribe(long userId, long groupId, long folderId)
		throws PortalException;

	public void unsubscribe(long userId, long groupId, long folderId)
		throws PortalException;

	public void updateAsset(long userId, JournalFolder folder,
		long[] assetCategoryIds, java.lang.String[] assetTagNames,
		long[] assetLinkEntryIds, java.lang.Double priority)
		throws PortalException;

	public void updateFolderDDMStructures(JournalFolder folder,
		long[] ddmStructureIdsArray) throws PortalException;

	public void validateFolderDDMStructures(long folderId, long parentFolderId)
		throws PortalException;
}