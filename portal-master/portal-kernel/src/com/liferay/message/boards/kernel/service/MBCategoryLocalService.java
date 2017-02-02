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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.message.boards.kernel.model.MBCategory;

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
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for MBCategory. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see MBCategoryLocalServiceUtil
 * @see com.liferay.portlet.messageboards.service.base.MBCategoryLocalServiceBaseImpl
 * @see com.liferay.portlet.messageboards.service.impl.MBCategoryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface MBCategoryLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MBCategoryLocalServiceUtil} to access the message boards category local service. Add custom service methods to {@link com.liferay.portlet.messageboards.service.impl.MBCategoryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public MBCategory addCategory(long userId, long parentCategoryId,
		java.lang.String name, java.lang.String description,
		ServiceContext serviceContext) throws PortalException;

	public MBCategory addCategory(long userId, long parentCategoryId,
		java.lang.String name, java.lang.String description,
		java.lang.String displayStyle, java.lang.String emailAddress,
		java.lang.String inProtocol, java.lang.String inServerName,
		int inServerPort, boolean inUseSSL, java.lang.String inUserName,
		java.lang.String inPassword, int inReadInterval,
		java.lang.String outEmailAddress, boolean outCustom,
		java.lang.String outServerName, int outServerPort, boolean outUseSSL,
		java.lang.String outUserName, java.lang.String outPassword,
		boolean allowAnonymous, boolean mailingListActive,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Adds the message boards category to the database. Also notifies the appropriate model listeners.
	*
	* @param mbCategory the message boards category
	* @return the message boards category that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public MBCategory addMBCategory(MBCategory mbCategory);

	/**
	* Creates a new message boards category with the primary key. Does not add the message boards category to the database.
	*
	* @param categoryId the primary key for the new message boards category
	* @return the new message boards category
	*/
	public MBCategory createMBCategory(long categoryId);

	/**
	* Deletes the message boards category from the database. Also notifies the appropriate model listeners.
	*
	* @param mbCategory the message boards category
	* @return the message boards category that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public MBCategory deleteMBCategory(MBCategory mbCategory);

	/**
	* Deletes the message boards category with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param categoryId the primary key of the message boards category
	* @return the message boards category that was removed
	* @throws PortalException if a message boards category with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public MBCategory deleteMBCategory(long categoryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MBCategory fetchMBCategory(long categoryId);

	/**
	* Returns the message boards category matching the UUID and group.
	*
	* @param uuid the message boards category's UUID
	* @param groupId the primary key of the group
	* @return the matching message boards category, or <code>null</code> if a matching message boards category could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MBCategory fetchMBCategoryByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MBCategory getCategory(long categoryId) throws PortalException;

	/**
	* Returns the message boards category with the primary key.
	*
	* @param categoryId the primary key of the message boards category
	* @return the message boards category
	* @throws PortalException if a message boards category with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MBCategory getMBCategory(long categoryId) throws PortalException;

	/**
	* Returns the message boards category matching the UUID and group.
	*
	* @param uuid the message boards category's UUID
	* @param groupId the primary key of the group
	* @return the matching message boards category
	* @throws PortalException if a matching message boards category could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MBCategory getMBCategoryByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	public MBCategory moveCategory(long categoryId, long parentCategoryId,
		boolean mergeWithParentCategory) throws PortalException;

	public MBCategory moveCategoryFromTrash(long userId, long categoryId,
		long newCategoryId) throws PortalException;

	public MBCategory moveCategoryToTrash(long userId, long categoryId)
		throws PortalException;

	public MBCategory updateCategory(long categoryId, long parentCategoryId,
		java.lang.String name, java.lang.String description,
		java.lang.String displayStyle, java.lang.String emailAddress,
		java.lang.String inProtocol, java.lang.String inServerName,
		int inServerPort, boolean inUseSSL, java.lang.String inUserName,
		java.lang.String inPassword, int inReadInterval,
		java.lang.String outEmailAddress, boolean outCustom,
		java.lang.String outServerName, int outServerPort, boolean outUseSSL,
		java.lang.String outUserName, java.lang.String outPassword,
		boolean allowAnonymous, boolean mailingListActive,
		boolean mergeWithParentCategory, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Updates the message boards category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mbCategory the message boards category
	* @return the message boards category that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public MBCategory updateMBCategory(MBCategory mbCategory);

	public MBCategory updateMessageCount(long categoryId);

	public MBCategory updateStatistics(long categoryId);

	public MBCategory updateStatus(long userId, long categoryId, int status)
		throws PortalException;

	public MBCategory updateThreadCount(long categoryId);

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
	public int getCategoriesAndThreadsCount(long groupId, long categoryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesAndThreadsCount(long groupId, long categoryId,
		int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesCount(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesCount(long groupId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesCount(long groupId, long excludedCategoryId,
		long parentCategoryId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesCount(long groupId, long parentCategoryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesCount(long groupId, long parentCategoryId,
		int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesCount(long groupId, long[] excludedCategoryIds,
		long[] parentCategoryIds, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesCount(long groupId, long[] parentCategoryIds);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesCount(long groupId, long[] parentCategoryIds,
		int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCompanyCategoriesCount(long companyId);

	/**
	* Returns the number of message boards categories.
	*
	* @return the number of message boards categories
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMBCategoriesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSubscribedCategoriesCount(long groupId, long userId);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.messageboards.model.impl.MBCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.messageboards.model.impl.MBCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<MBCategory> getCategories(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MBCategory> getCategories(long groupId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MBCategory> getCategories(long groupId,
		long excludedCategoryId, long parentCategoryId, int status, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MBCategory> getCategories(long groupId, long parentCategoryId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MBCategory> getCategories(long groupId, long parentCategoryId,
		int status, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MBCategory> getCategories(long groupId,
		long[] excludedCategoryIds, long[] parentCategoryIds, int status,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MBCategory> getCategories(long groupId,
		long[] parentCategoryIds, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MBCategory> getCategories(long groupId,
		long[] parentCategoryIds, int status, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<java.lang.Object> getCategoriesAndThreads(long groupId,
		long categoryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<java.lang.Object> getCategoriesAndThreads(long groupId,
		long categoryId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<java.lang.Object> getCategoriesAndThreads(long groupId,
		long categoryId, int status, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MBCategory> getCompanyCategories(long companyId, int start,
		int end);

	/**
	* Returns a range of all the message boards categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.messageboards.model.impl.MBCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of message boards categories
	* @param end the upper bound of the range of message boards categories (not inclusive)
	* @return the range of message boards categories
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MBCategory> getMBCategories(int start, int end);

	/**
	* Returns all the message boards categories matching the UUID and company.
	*
	* @param uuid the UUID of the message boards categories
	* @param companyId the primary key of the company
	* @return the matching message boards categories, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MBCategory> getMBCategoriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of message boards categories matching the UUID and company.
	*
	* @param uuid the UUID of the message boards categories
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of message boards categories
	* @param end the upper bound of the range of message boards categories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching message boards categories, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MBCategory> getMBCategoriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<MBCategory> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<java.lang.Long> getSubcategoryIds(
		List<java.lang.Long> categoryIds, long groupId, long categoryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MBCategory> getSubscribedCategories(long groupId, long userId,
		int start, int end);

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

	public void addCategoryResources(MBCategory category,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws PortalException;

	public void addCategoryResources(MBCategory category,
		ModelPermissions modelPermissions) throws PortalException;

	public void addCategoryResources(long categoryId,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws PortalException;

	public void addCategoryResources(long categoryId,
		ModelPermissions modelPermissions) throws PortalException;

	public void deleteCategories(long groupId) throws PortalException;

	public void deleteCategory(MBCategory category) throws PortalException;

	@SystemEvent(action = SystemEventConstants.ACTION_SKIP, type = SystemEventConstants.TYPE_DELETE)
	public void deleteCategory(MBCategory category,
		boolean includeTrashedEntries) throws PortalException;

	public void deleteCategory(long categoryId) throws PortalException;

	public void moveCategoriesToTrash(long groupId, long userId)
		throws PortalException;

	public void restoreCategoryFromTrash(long userId, long categoryId)
		throws PortalException;

	public void subscribeCategory(long userId, long groupId, long categoryId)
		throws PortalException;

	public void unsubscribeCategory(long userId, long groupId, long categoryId)
		throws PortalException;
}