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
 * Provides the local service utility for LayoutSet. This utility wraps
 * {@link com.liferay.portal.service.impl.LayoutSetLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetLocalService
 * @see com.liferay.portal.service.base.LayoutSetLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.LayoutSetLocalServiceImpl
 * @generated
 */
@ProviderType
public class LayoutSetLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.LayoutSetLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the layout set to the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSet the layout set
	* @return the layout set that was added
	*/
	public static com.liferay.portal.kernel.model.LayoutSet addLayoutSet(
		com.liferay.portal.kernel.model.LayoutSet layoutSet) {
		return getService().addLayoutSet(layoutSet);
	}

	public static com.liferay.portal.kernel.model.LayoutSet addLayoutSet(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addLayoutSet(groupId, privateLayout);
	}

	/**
	* Creates a new layout set with the primary key. Does not add the layout set to the database.
	*
	* @param layoutSetId the primary key for the new layout set
	* @return the new layout set
	*/
	public static com.liferay.portal.kernel.model.LayoutSet createLayoutSet(
		long layoutSetId) {
		return getService().createLayoutSet(layoutSetId);
	}

	/**
	* Deletes the layout set from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSet the layout set
	* @return the layout set that was removed
	*/
	public static com.liferay.portal.kernel.model.LayoutSet deleteLayoutSet(
		com.liferay.portal.kernel.model.LayoutSet layoutSet) {
		return getService().deleteLayoutSet(layoutSet);
	}

	/**
	* Deletes the layout set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetId the primary key of the layout set
	* @return the layout set that was removed
	* @throws PortalException if a layout set with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.model.LayoutSet deleteLayoutSet(
		long layoutSetId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLayoutSet(layoutSetId);
	}

	public static com.liferay.portal.kernel.model.LayoutSet fetchLayoutSet(
		java.lang.String virtualHostname) {
		return getService().fetchLayoutSet(virtualHostname);
	}

	public static com.liferay.portal.kernel.model.LayoutSet fetchLayoutSet(
		long layoutSetId) {
		return getService().fetchLayoutSet(layoutSetId);
	}

	public static com.liferay.portal.kernel.model.LayoutSet getLayoutSet(
		java.lang.String virtualHostname)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLayoutSet(virtualHostname);
	}

	public static com.liferay.portal.kernel.model.LayoutSet getLayoutSet(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLayoutSet(groupId, privateLayout);
	}

	/**
	* Returns the layout set with the primary key.
	*
	* @param layoutSetId the primary key of the layout set
	* @return the layout set
	* @throws PortalException if a layout set with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.model.LayoutSet getLayoutSet(
		long layoutSetId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLayoutSet(layoutSetId);
	}

	/**
	* Updates the layout set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param layoutSet the layout set
	* @return the layout set that was updated
	*/
	public static com.liferay.portal.kernel.model.LayoutSet updateLayoutSet(
		com.liferay.portal.kernel.model.LayoutSet layoutSet) {
		return getService().updateLayoutSet(layoutSet);
	}

	public static com.liferay.portal.kernel.model.LayoutSet updateLogo(
		long groupId, boolean privateLayout, boolean logo, byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateLogo(groupId, privateLayout, logo, bytes);
	}

	public static com.liferay.portal.kernel.model.LayoutSet updateLogo(
		long groupId, boolean privateLayout, boolean logo, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateLogo(groupId, privateLayout, logo, file);
	}

	public static com.liferay.portal.kernel.model.LayoutSet updateLogo(
		long groupId, boolean privateLayout, boolean logo,
		java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateLogo(groupId, privateLayout, logo, is);
	}

	public static com.liferay.portal.kernel.model.LayoutSet updateLogo(
		long groupId, boolean privateLayout, boolean logo,
		java.io.InputStream is, boolean cleanUpStream)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateLogo(groupId, privateLayout, logo, is, cleanUpStream);
	}

	public static com.liferay.portal.kernel.model.LayoutSet updateLookAndFeel(
		long groupId, boolean privateLayout, java.lang.String themeId,
		java.lang.String colorSchemeId, java.lang.String css)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateLookAndFeel(groupId, privateLayout, themeId,
			colorSchemeId, css);
	}

	public static com.liferay.portal.kernel.model.LayoutSet updatePageCount(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updatePageCount(groupId, privateLayout);
	}

	public static com.liferay.portal.kernel.model.LayoutSet updateSettings(
		long groupId, boolean privateLayout, java.lang.String settings)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateSettings(groupId, privateLayout, settings);
	}

	public static com.liferay.portal.kernel.model.LayoutSet updateVirtualHost(
		long groupId, boolean privateLayout, java.lang.String virtualHostname)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateVirtualHost(groupId, privateLayout, virtualHostname);
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
	* Returns the number of layout sets.
	*
	* @return the number of layout sets
	*/
	public static int getLayoutSetsCount() {
		return getService().getLayoutSetsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the layout sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout sets
	* @param end the upper bound of the range of layout sets (not inclusive)
	* @return the range of layout sets
	*/
	public static java.util.List<com.liferay.portal.kernel.model.LayoutSet> getLayoutSets(
		int start, int end) {
		return getService().getLayoutSets(start, end);
	}

	public static java.util.List<com.liferay.portal.kernel.model.LayoutSet> getLayoutSetsByLayoutSetPrototypeUuid(
		java.lang.String layoutSetPrototypeUuid) {
		return getService()
				   .getLayoutSetsByLayoutSetPrototypeUuid(layoutSetPrototypeUuid);
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

	public static void deleteLayoutSet(long groupId, boolean privateLayout,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteLayoutSet(groupId, privateLayout, serviceContext);
	}

	/**
	* Updates the state of the layout set prototype link.
	*
	* @param groupId the primary key of the group
	* @param privateLayout whether the layout set is private to the group
	* @param layoutSetPrototypeLinkEnabled whether the layout set prototype is
	link enabled
	* @param layoutSetPrototypeUuid the uuid of the layout set prototype to
	link with
	*/
	public static void updateLayoutSetPrototypeLinkEnabled(long groupId,
		boolean privateLayout, boolean layoutSetPrototypeLinkEnabled,
		java.lang.String layoutSetPrototypeUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateLayoutSetPrototypeLinkEnabled(groupId, privateLayout,
			layoutSetPrototypeLinkEnabled, layoutSetPrototypeUuid);
	}

	public static void updateLookAndFeel(long groupId,
		java.lang.String themeId, java.lang.String colorSchemeId,
		java.lang.String css)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateLookAndFeel(groupId, themeId, colorSchemeId, css);
	}

	public static LayoutSetLocalService getService() {
		if (_service == null) {
			_service = (LayoutSetLocalService)PortalBeanLocatorUtil.locate(LayoutSetLocalService.class.getName());

			ReferenceRegistry.registerReference(LayoutSetLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static LayoutSetLocalService _service;
}