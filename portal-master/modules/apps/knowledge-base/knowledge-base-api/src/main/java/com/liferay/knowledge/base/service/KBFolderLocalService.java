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

import com.liferay.knowledge.base.model.KBFolder;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for KBFolder. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see KBFolderLocalServiceUtil
 * @see com.liferay.knowledge.base.service.base.KBFolderLocalServiceBaseImpl
 * @see com.liferay.knowledge.base.service.impl.KBFolderLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KBFolderLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KBFolderLocalServiceUtil} to access the k b folder local service. Add custom service methods to {@link com.liferay.knowledge.base.service.impl.KBFolderLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the k b folder to the database. Also notifies the appropriate model listeners.
	*
	* @param kbFolder the k b folder
	* @return the k b folder that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public KBFolder addKBFolder(KBFolder kbFolder);

	public KBFolder addKBFolder(long userId, long groupId,
		long parentResourceClassNameId, long parentResourcePrimKey,
		java.lang.String name, java.lang.String description,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Creates a new k b folder with the primary key. Does not add the k b folder to the database.
	*
	* @param kbFolderId the primary key for the new k b folder
	* @return the new k b folder
	*/
	public KBFolder createKBFolder(long kbFolderId);

	/**
	* Deletes the k b folder from the database. Also notifies the appropriate model listeners.
	*
	* @param kbFolder the k b folder
	* @return the k b folder that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public KBFolder deleteKBFolder(KBFolder kbFolder);

	/**
	* Deletes the k b folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbFolderId the primary key of the k b folder
	* @return the k b folder that was removed
	* @throws PortalException if a k b folder with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public KBFolder deleteKBFolder(long kbFolderId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBFolder fetchFirstChildKBFolder(long groupId, long kbFolderId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBFolder fetchKBFolder(long kbFolderId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBFolder fetchKBFolderByUrlTitle(long groupId,
		long parentKbFolderId, java.lang.String urlTitle)
		throws PortalException;

	/**
	* Returns the k b folder matching the UUID and group.
	*
	* @param uuid the k b folder's UUID
	* @param groupId the primary key of the group
	* @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBFolder fetchKBFolderByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Returns the k b folder with the primary key.
	*
	* @param kbFolderId the primary key of the k b folder
	* @return the k b folder
	* @throws PortalException if a k b folder with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBFolder getKBFolder(long kbFolderId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBFolder getKBFolderByUrlTitle(long groupId, long parentKbFolderId,
		java.lang.String urlTitle) throws PortalException;

	/**
	* Returns the k b folder matching the UUID and group.
	*
	* @param uuid the k b folder's UUID
	* @param groupId the primary key of the group
	* @return the matching k b folder
	* @throws PortalException if a matching k b folder could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBFolder getKBFolderByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the k b folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbFolder the k b folder
	* @return the k b folder that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public KBFolder updateKBFolder(KBFolder kbFolder);

	public KBFolder updateKBFolder(long parentResourceClassNameId,
		long parentResourcePrimKey, long kbFolderId, java.lang.String name,
		java.lang.String description) throws PortalException;

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
	public int getKBFoldersAndKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status);

	/**
	* Returns the number of k b folders.
	*
	* @return the number of k b folders
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBFoldersCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBFoldersCount(long groupId, long parentKBFolderId)
		throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledge.base.model.impl.KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledge.base.model.impl.KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Returns a range of all the k b folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledge.base.model.impl.KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @return the range of k b folders
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBFolder> getKBFolders(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBFolder> getKBFolders(long groupId, long parentKBFolderId,
		int start, int end) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<java.lang.Object> getKBFoldersAndKBArticles(long groupId,
		long parentResourcePrimKey, int status, int start, int end,
		OrderByComparator<?> orderByComparator);

	/**
	* Returns all the k b folders matching the UUID and company.
	*
	* @param uuid the UUID of the k b folders
	* @param companyId the primary key of the company
	* @return the matching k b folders, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBFolder> getKBFoldersByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of k b folders matching the UUID and company.
	*
	* @param uuid the UUID of the k b folders
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of k b folders
	* @param end the upper bound of the range of k b folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching k b folders, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBFolder> getKBFoldersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<KBFolder> orderByComparator);

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

	public void moveKBFolder(long kbFolderId, long parentKBFolderId)
		throws PortalException;
}