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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for WikiPageResource. This utility wraps
 * {@link com.liferay.wiki.service.impl.WikiPageResourceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see WikiPageResourceLocalService
 * @see com.liferay.wiki.service.base.WikiPageResourceLocalServiceBaseImpl
 * @see com.liferay.wiki.service.impl.WikiPageResourceLocalServiceImpl
 * @generated
 */
@ProviderType
public class WikiPageResourceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.wiki.service.impl.WikiPageResourceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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

	public static com.liferay.wiki.model.WikiPageResource addPageResource(
		long groupId, long nodeId, java.lang.String title) {
		return getService().addPageResource(groupId, nodeId, title);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #addPageResource(long, long,
	String)}
	*/
	@Deprecated
	public static com.liferay.wiki.model.WikiPageResource addPageResource(
		long nodeId, java.lang.String title) {
		return getService().addPageResource(nodeId, title);
	}

	/**
	* Adds the wiki page resource to the database. Also notifies the appropriate model listeners.
	*
	* @param wikiPageResource the wiki page resource
	* @return the wiki page resource that was added
	*/
	public static com.liferay.wiki.model.WikiPageResource addWikiPageResource(
		com.liferay.wiki.model.WikiPageResource wikiPageResource) {
		return getService().addWikiPageResource(wikiPageResource);
	}

	/**
	* Creates a new wiki page resource with the primary key. Does not add the wiki page resource to the database.
	*
	* @param resourcePrimKey the primary key for the new wiki page resource
	* @return the new wiki page resource
	*/
	public static com.liferay.wiki.model.WikiPageResource createWikiPageResource(
		long resourcePrimKey) {
		return getService().createWikiPageResource(resourcePrimKey);
	}

	/**
	* Deletes the wiki page resource from the database. Also notifies the appropriate model listeners.
	*
	* @param wikiPageResource the wiki page resource
	* @return the wiki page resource that was removed
	*/
	public static com.liferay.wiki.model.WikiPageResource deleteWikiPageResource(
		com.liferay.wiki.model.WikiPageResource wikiPageResource) {
		return getService().deleteWikiPageResource(wikiPageResource);
	}

	/**
	* Deletes the wiki page resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourcePrimKey the primary key of the wiki page resource
	* @return the wiki page resource that was removed
	* @throws PortalException if a wiki page resource with the primary key could not be found
	*/
	public static com.liferay.wiki.model.WikiPageResource deleteWikiPageResource(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWikiPageResource(resourcePrimKey);
	}

	public static com.liferay.wiki.model.WikiPageResource fetchPageResource(
		java.lang.String uuid) {
		return getService().fetchPageResource(uuid);
	}

	public static com.liferay.wiki.model.WikiPageResource fetchPageResource(
		long nodeId, java.lang.String title) {
		return getService().fetchPageResource(nodeId, title);
	}

	public static com.liferay.wiki.model.WikiPageResource fetchWikiPageResource(
		long resourcePrimKey) {
		return getService().fetchWikiPageResource(resourcePrimKey);
	}

	/**
	* Returns the wiki page resource matching the UUID and group.
	*
	* @param uuid the wiki page resource's UUID
	* @param groupId the primary key of the group
	* @return the matching wiki page resource, or <code>null</code> if a matching wiki page resource could not be found
	*/
	public static com.liferay.wiki.model.WikiPageResource fetchWikiPageResourceByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchWikiPageResourceByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.wiki.model.WikiPageResource getPageResource(
		long nodeId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPageResource(nodeId, title);
	}

	public static com.liferay.wiki.model.WikiPageResource getPageResource(
		long pageResourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPageResource(pageResourcePrimKey);
	}

	/**
	* Returns the wiki page resource with the primary key.
	*
	* @param resourcePrimKey the primary key of the wiki page resource
	* @return the wiki page resource
	* @throws PortalException if a wiki page resource with the primary key could not be found
	*/
	public static com.liferay.wiki.model.WikiPageResource getWikiPageResource(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWikiPageResource(resourcePrimKey);
	}

	/**
	* Returns the wiki page resource matching the UUID and group.
	*
	* @param uuid the wiki page resource's UUID
	* @param groupId the primary key of the group
	* @return the matching wiki page resource
	* @throws PortalException if a matching wiki page resource could not be found
	*/
	public static com.liferay.wiki.model.WikiPageResource getWikiPageResourceByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWikiPageResourceByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the wiki page resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wikiPageResource the wiki page resource
	* @return the wiki page resource that was updated
	*/
	public static com.liferay.wiki.model.WikiPageResource updateWikiPageResource(
		com.liferay.wiki.model.WikiPageResource wikiPageResource) {
		return getService().updateWikiPageResource(wikiPageResource);
	}

	/**
	* Returns the number of wiki page resources.
	*
	* @return the number of wiki page resources
	*/
	public static int getWikiPageResourcesCount() {
		return getService().getWikiPageResourcesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wiki.model.impl.WikiPageResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wiki.model.impl.WikiPageResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<com.liferay.wiki.model.WikiPageResource> getWikiPageResources(
		int start, int end) {
		return getService().getWikiPageResources(start, end);
	}

	/**
	* Returns all the wiki page resources matching the UUID and company.
	*
	* @param uuid the UUID of the wiki page resources
	* @param companyId the primary key of the company
	* @return the matching wiki page resources, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.wiki.model.WikiPageResource> getWikiPageResourcesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getWikiPageResourcesByUuidAndCompanyId(uuid, companyId);
	}

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
	public static java.util.List<com.liferay.wiki.model.WikiPageResource> getWikiPageResourcesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.wiki.model.WikiPageResource> orderByComparator) {
		return getService()
				   .getWikiPageResourcesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
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

	public static long getPageResourcePrimKey(long groupId, long nodeId,
		java.lang.String title) {
		return getService().getPageResourcePrimKey(groupId, nodeId, title);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getPageResourcePrimKey(long,
	long, String)}
	*/
	@Deprecated
	public static long getPageResourcePrimKey(long nodeId,
		java.lang.String title) {
		return getService().getPageResourcePrimKey(nodeId, title);
	}

	public static void deletePageResource(long nodeId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deletePageResource(nodeId, title);
	}

	public static WikiPageResourceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WikiPageResourceLocalService, WikiPageResourceLocalService> _serviceTracker =
		ServiceTrackerFactory.open(WikiPageResourceLocalService.class);
}