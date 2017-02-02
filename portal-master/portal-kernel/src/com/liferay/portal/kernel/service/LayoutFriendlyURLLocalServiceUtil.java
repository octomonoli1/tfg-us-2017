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

package com.liferay.portal.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for LayoutFriendlyURL. This utility wraps
 * {@link com.liferay.portal.service.impl.LayoutFriendlyURLLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutFriendlyURLLocalService
 * @see com.liferay.portal.service.base.LayoutFriendlyURLLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.LayoutFriendlyURLLocalServiceImpl
 * @generated
 */
@ProviderType
public class LayoutFriendlyURLLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.LayoutFriendlyURLLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
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
	* Adds the layout friendly u r l to the database. Also notifies the appropriate model listeners.
	*
	* @param layoutFriendlyURL the layout friendly u r l
	* @return the layout friendly u r l that was added
	*/
	public static com.liferay.portal.kernel.model.LayoutFriendlyURL addLayoutFriendlyURL(
		com.liferay.portal.kernel.model.LayoutFriendlyURL layoutFriendlyURL) {
		return getService().addLayoutFriendlyURL(layoutFriendlyURL);
	}

	public static com.liferay.portal.kernel.model.LayoutFriendlyURL addLayoutFriendlyURL(
		long userId, long companyId, long groupId, long plid,
		boolean privateLayout, java.lang.String friendlyURL,
		java.lang.String languageId, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLayoutFriendlyURL(userId, companyId, groupId, plid,
			privateLayout, friendlyURL, languageId, serviceContext);
	}

	/**
	* Creates a new layout friendly u r l with the primary key. Does not add the layout friendly u r l to the database.
	*
	* @param layoutFriendlyURLId the primary key for the new layout friendly u r l
	* @return the new layout friendly u r l
	*/
	public static com.liferay.portal.kernel.model.LayoutFriendlyURL createLayoutFriendlyURL(
		long layoutFriendlyURLId) {
		return getService().createLayoutFriendlyURL(layoutFriendlyURLId);
	}

	/**
	* Deletes the layout friendly u r l from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutFriendlyURL the layout friendly u r l
	* @return the layout friendly u r l that was removed
	*/
	public static com.liferay.portal.kernel.model.LayoutFriendlyURL deleteLayoutFriendlyURL(
		com.liferay.portal.kernel.model.LayoutFriendlyURL layoutFriendlyURL) {
		return getService().deleteLayoutFriendlyURL(layoutFriendlyURL);
	}

	/**
	* Deletes the layout friendly u r l with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutFriendlyURLId the primary key of the layout friendly u r l
	* @return the layout friendly u r l that was removed
	* @throws PortalException if a layout friendly u r l with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.model.LayoutFriendlyURL deleteLayoutFriendlyURL(
		long layoutFriendlyURLId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLayoutFriendlyURL(layoutFriendlyURLId);
	}

	public static com.liferay.portal.kernel.model.LayoutFriendlyURL fetchFirstLayoutFriendlyURL(
		long groupId, boolean privateLayout, java.lang.String friendlyURL) {
		return getService()
				   .fetchFirstLayoutFriendlyURL(groupId, privateLayout,
			friendlyURL);
	}

	public static com.liferay.portal.kernel.model.LayoutFriendlyURL fetchLayoutFriendlyURL(
		long groupId, boolean privateLayout, java.lang.String friendlyURL,
		java.lang.String languageId) {
		return getService()
				   .fetchLayoutFriendlyURL(groupId, privateLayout, friendlyURL,
			languageId);
	}

	public static com.liferay.portal.kernel.model.LayoutFriendlyURL fetchLayoutFriendlyURL(
		long layoutFriendlyURLId) {
		return getService().fetchLayoutFriendlyURL(layoutFriendlyURLId);
	}

	public static com.liferay.portal.kernel.model.LayoutFriendlyURL fetchLayoutFriendlyURL(
		long plid, java.lang.String languageId) {
		return getService().fetchLayoutFriendlyURL(plid, languageId);
	}

	public static com.liferay.portal.kernel.model.LayoutFriendlyURL fetchLayoutFriendlyURL(
		long plid, java.lang.String languageId, boolean useDefault) {
		return getService().fetchLayoutFriendlyURL(plid, languageId, useDefault);
	}

	/**
	* Returns the layout friendly u r l matching the UUID and group.
	*
	* @param uuid the layout friendly u r l's UUID
	* @param groupId the primary key of the group
	* @return the matching layout friendly u r l, or <code>null</code> if a matching layout friendly u r l could not be found
	*/
	public static com.liferay.portal.kernel.model.LayoutFriendlyURL fetchLayoutFriendlyURLByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchLayoutFriendlyURLByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the layout friendly u r l with the primary key.
	*
	* @param layoutFriendlyURLId the primary key of the layout friendly u r l
	* @return the layout friendly u r l
	* @throws PortalException if a layout friendly u r l with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.model.LayoutFriendlyURL getLayoutFriendlyURL(
		long layoutFriendlyURLId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLayoutFriendlyURL(layoutFriendlyURLId);
	}

	public static com.liferay.portal.kernel.model.LayoutFriendlyURL getLayoutFriendlyURL(
		long plid, java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLayoutFriendlyURL(plid, languageId);
	}

	public static com.liferay.portal.kernel.model.LayoutFriendlyURL getLayoutFriendlyURL(
		long plid, java.lang.String languageId, boolean useDefault)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLayoutFriendlyURL(plid, languageId, useDefault);
	}

	/**
	* Returns the layout friendly u r l matching the UUID and group.
	*
	* @param uuid the layout friendly u r l's UUID
	* @param groupId the primary key of the group
	* @return the matching layout friendly u r l
	* @throws PortalException if a matching layout friendly u r l could not be found
	*/
	public static com.liferay.portal.kernel.model.LayoutFriendlyURL getLayoutFriendlyURLByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLayoutFriendlyURLByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the layout friendly u r l in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param layoutFriendlyURL the layout friendly u r l
	* @return the layout friendly u r l that was updated
	*/
	public static com.liferay.portal.kernel.model.LayoutFriendlyURL updateLayoutFriendlyURL(
		com.liferay.portal.kernel.model.LayoutFriendlyURL layoutFriendlyURL) {
		return getService().updateLayoutFriendlyURL(layoutFriendlyURL);
	}

	public static com.liferay.portal.kernel.model.LayoutFriendlyURL updateLayoutFriendlyURL(
		long userId, long companyId, long groupId, long plid,
		boolean privateLayout, java.lang.String friendlyURL,
		java.lang.String languageId, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateLayoutFriendlyURL(userId, companyId, groupId, plid,
			privateLayout, friendlyURL, languageId, serviceContext);
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

	/**
	* Returns the number of layout friendly u r ls.
	*
	* @return the number of layout friendly u r ls
	*/
	public static int getLayoutFriendlyURLsCount() {
		return getService().getLayoutFriendlyURLsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.portal.kernel.model.LayoutFriendlyURL> addLayoutFriendlyURLs(
		long userId, long companyId, long groupId, long plid,
		boolean privateLayout,
		java.util.Map<java.util.Locale, java.lang.String> friendlyURLMap,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLayoutFriendlyURLs(userId, companyId, groupId, plid,
			privateLayout, friendlyURLMap, serviceContext);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the layout friendly u r ls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutFriendlyURLModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @return the range of layout friendly u r ls
	*/
	public static java.util.List<com.liferay.portal.kernel.model.LayoutFriendlyURL> getLayoutFriendlyURLs(
		int start, int end) {
		return getService().getLayoutFriendlyURLs(start, end);
	}

	public static java.util.List<com.liferay.portal.kernel.model.LayoutFriendlyURL> getLayoutFriendlyURLs(
		long plid) {
		return getService().getLayoutFriendlyURLs(plid);
	}

	public static java.util.List<com.liferay.portal.kernel.model.LayoutFriendlyURL> getLayoutFriendlyURLs(
		long plid, java.lang.String friendlyURL, int start, int end) {
		return getService().getLayoutFriendlyURLs(plid, friendlyURL, start, end);
	}

	/**
	* Returns all the layout friendly u r ls matching the UUID and company.
	*
	* @param uuid the UUID of the layout friendly u r ls
	* @param companyId the primary key of the company
	* @return the matching layout friendly u r ls, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.portal.kernel.model.LayoutFriendlyURL> getLayoutFriendlyURLsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getLayoutFriendlyURLsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of layout friendly u r ls matching the UUID and company.
	*
	* @param uuid the UUID of the layout friendly u r ls
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of layout friendly u r ls
	* @param end the upper bound of the range of layout friendly u r ls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching layout friendly u r ls, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.portal.kernel.model.LayoutFriendlyURL> getLayoutFriendlyURLsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.LayoutFriendlyURL> orderByComparator) {
		return getService()
				   .getLayoutFriendlyURLsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.kernel.model.LayoutFriendlyURL> updateLayoutFriendlyURLs(
		long userId, long companyId, long groupId, long plid,
		boolean privateLayout,
		java.util.Map<java.util.Locale, java.lang.String> friendlyURLMap,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateLayoutFriendlyURLs(userId, companyId, groupId, plid,
			privateLayout, friendlyURLMap, serviceContext);
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

	public static void deleteLayoutFriendlyURL(long plid,
		java.lang.String languageId) {
		getService().deleteLayoutFriendlyURL(plid, languageId);
	}

	public static void deleteLayoutFriendlyURLs(long plid) {
		getService().deleteLayoutFriendlyURLs(plid);
	}

	public static LayoutFriendlyURLLocalService getService() {
		if (_service == null) {
			_service = (LayoutFriendlyURLLocalService)PortalBeanLocatorUtil.locate(LayoutFriendlyURLLocalService.class.getName());

			ReferenceRegistry.registerReference(LayoutFriendlyURLLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static LayoutFriendlyURLLocalService _service;
}