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

package com.liferay.wiki.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.wiki.model.WikiPageResource;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for WikiPageResource. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see WikiPageResourceLocalServiceUtil
 * @see com.liferay.wiki.service.base.WikiPageResourceLocalServiceBaseImpl
 * @see com.liferay.wiki.service.impl.WikiPageResourceLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WikiPageResourceLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WikiPageResourceLocalServiceUtil} to access the wiki page resource local service. Add custom service methods to {@link com.liferay.wiki.service.impl.WikiPageResourceLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

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

	public WikiPageResource addPageResource(long groupId, long nodeId,
		java.lang.String title);

	/**
	* @deprecated As of 7.0.0, replaced by {@link #addPageResource(long, long,
	String)}
	*/
	@java.lang.Deprecated
	public WikiPageResource addPageResource(long nodeId, java.lang.String title);

	/**
	* Adds the wiki page resource to the database. Also notifies the appropriate model listeners.
	*
	* @param wikiPageResource the wiki page resource
	* @return the wiki page resource that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public WikiPageResource addWikiPageResource(
		WikiPageResource wikiPageResource);

	/**
	* Creates a new wiki page resource with the primary key. Does not add the wiki page resource to the database.
	*
	* @param resourcePrimKey the primary key for the new wiki page resource
	* @return the new wiki page resource
	*/
	public WikiPageResource createWikiPageResource(long resourcePrimKey);

	/**
	* Deletes the wiki page resource from the database. Also notifies the appropriate model listeners.
	*
	* @param wikiPageResource the wiki page resource
	* @return the wiki page resource that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public WikiPageResource deleteWikiPageResource(
		WikiPageResource wikiPageResource);

	/**
	* Deletes the wiki page resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourcePrimKey the primary key of the wiki page resource
	* @return the wiki page resource that was removed
	* @throws PortalException if a wiki page resource with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public WikiPageResource deleteWikiPageResource(long resourcePrimKey)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WikiPageResource fetchPageResource(java.lang.String uuid);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WikiPageResource fetchPageResource(long nodeId,
		java.lang.String title);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WikiPageResource fetchWikiPageResource(long resourcePrimKey);

	/**
	* Returns the wiki page resource matching the UUID and group.
	*
	* @param uuid the wiki page resource's UUID
	* @param groupId the primary key of the group
	* @return the matching wiki page resource, or <code>null</code> if a matching wiki page resource could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WikiPageResource fetchWikiPageResourceByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WikiPageResource getPageResource(long nodeId, java.lang.String title)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WikiPageResource getPageResource(long pageResourcePrimKey)
		throws PortalException;

	/**
	* Returns the wiki page resource with the primary key.
	*
	* @param resourcePrimKey the primary key of the wiki page resource
	* @return the wiki page resource
	* @throws PortalException if a wiki page resource with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WikiPageResource getWikiPageResource(long resourcePrimKey)
		throws PortalException;

	/**
	* Returns the wiki page resource matching the UUID and group.
	*
	* @param uuid the wiki page resource's UUID
	* @param groupId the primary key of the group
	* @return the matching wiki page resource
	* @throws PortalException if a matching wiki page resource could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WikiPageResource getWikiPageResourceByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Updates the wiki page resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wikiPageResource the wiki page resource
	* @return the wiki page resource that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public WikiPageResource updateWikiPageResource(
		WikiPageResource wikiPageResource);

	/**
	* Returns the number of wiki page resources.
	*
	* @return the number of wiki page resources
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWikiPageResourcesCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wiki.model.impl.WikiPageResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wiki.model.impl.WikiPageResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the wiki page resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wiki.model.impl.WikiPageResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of wiki page resources
	* @param end the upper bound of the range of wiki page resources (not inclusive)
	* @return the range of wiki page resources
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WikiPageResource> getWikiPageResources(int start, int end);

	/**
	* Returns all the wiki page resources matching the UUID and company.
	*
	* @param uuid the UUID of the wiki page resources
	* @param companyId the primary key of the company
	* @return the matching wiki page resources, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WikiPageResource> getWikiPageResourcesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of wiki page resources matching the UUID and company.
	*
	* @param uuid the UUID of the wiki page resources
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of wiki page resources
	* @param end the upper bound of the range of wiki page resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching wiki page resources, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WikiPageResource> getWikiPageResourcesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<WikiPageResource> orderByComparator);

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
	public long getPageResourcePrimKey(long groupId, long nodeId,
		java.lang.String title);

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getPageResourcePrimKey(long,
	long, String)}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getPageResourcePrimKey(long nodeId, java.lang.String title);

	public void deletePageResource(long nodeId, java.lang.String title)
		throws PortalException;
}