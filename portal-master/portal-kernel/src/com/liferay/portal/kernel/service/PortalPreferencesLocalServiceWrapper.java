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

/**
 * Provides a wrapper for {@link PortalPreferencesLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PortalPreferencesLocalService
 * @generated
 */
@ProviderType
public class PortalPreferencesLocalServiceWrapper
	implements PortalPreferencesLocalService,
		ServiceWrapper<PortalPreferencesLocalService> {
	public PortalPreferencesLocalServiceWrapper(
		PortalPreferencesLocalService portalPreferencesLocalService) {
		_portalPreferencesLocalService = portalPreferencesLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _portalPreferencesLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _portalPreferencesLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _portalPreferencesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _portalPreferencesLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _portalPreferencesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the portal preferences to the database. Also notifies the appropriate model listeners.
	*
	* @param portalPreferences the portal preferences
	* @return the portal preferences that was added
	*/
	@Override
	public com.liferay.portal.kernel.model.PortalPreferences addPortalPreferences(
		com.liferay.portal.kernel.model.PortalPreferences portalPreferences) {
		return _portalPreferencesLocalService.addPortalPreferences(portalPreferences);
	}

	@Override
	public com.liferay.portal.kernel.model.PortalPreferences addPortalPreferences(
		long ownerId, int ownerType, java.lang.String defaultPreferences) {
		return _portalPreferencesLocalService.addPortalPreferences(ownerId,
			ownerType, defaultPreferences);
	}

	/**
	* Creates a new portal preferences with the primary key. Does not add the portal preferences to the database.
	*
	* @param portalPreferencesId the primary key for the new portal preferences
	* @return the new portal preferences
	*/
	@Override
	public com.liferay.portal.kernel.model.PortalPreferences createPortalPreferences(
		long portalPreferencesId) {
		return _portalPreferencesLocalService.createPortalPreferences(portalPreferencesId);
	}

	/**
	* Deletes the portal preferences from the database. Also notifies the appropriate model listeners.
	*
	* @param portalPreferences the portal preferences
	* @return the portal preferences that was removed
	*/
	@Override
	public com.liferay.portal.kernel.model.PortalPreferences deletePortalPreferences(
		com.liferay.portal.kernel.model.PortalPreferences portalPreferences) {
		return _portalPreferencesLocalService.deletePortalPreferences(portalPreferences);
	}

	/**
	* Deletes the portal preferences with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param portalPreferencesId the primary key of the portal preferences
	* @return the portal preferences that was removed
	* @throws PortalException if a portal preferences with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.PortalPreferences deletePortalPreferences(
		long portalPreferencesId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _portalPreferencesLocalService.deletePortalPreferences(portalPreferencesId);
	}

	@Override
	public com.liferay.portal.kernel.model.PortalPreferences fetchPortalPreferences(
		long portalPreferencesId) {
		return _portalPreferencesLocalService.fetchPortalPreferences(portalPreferencesId);
	}

	/**
	* Returns the portal preferences with the primary key.
	*
	* @param portalPreferencesId the primary key of the portal preferences
	* @return the portal preferences
	* @throws PortalException if a portal preferences with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.PortalPreferences getPortalPreferences(
		long portalPreferencesId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _portalPreferencesLocalService.getPortalPreferences(portalPreferencesId);
	}

	/**
	* Updates the portal preferences in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param portalPreferences the portal preferences
	* @return the portal preferences that was updated
	*/
	@Override
	public com.liferay.portal.kernel.model.PortalPreferences updatePortalPreferences(
		com.liferay.portal.kernel.model.PortalPreferences portalPreferences) {
		return _portalPreferencesLocalService.updatePortalPreferences(portalPreferences);
	}

	@Override
	public com.liferay.portal.kernel.model.PortalPreferences updatePreferences(
		long ownerId, int ownerType,
		com.liferay.portal.kernel.portlet.PortalPreferences portalPreferences) {
		return _portalPreferencesLocalService.updatePreferences(ownerId,
			ownerType, portalPreferences);
	}

	@Override
	public com.liferay.portal.kernel.model.PortalPreferences updatePreferences(
		long ownerId, int ownerType, java.lang.String xml) {
		return _portalPreferencesLocalService.updatePreferences(ownerId,
			ownerType, xml);
	}

	/**
	* Returns the number of portal preferenceses.
	*
	* @return the number of portal preferenceses
	*/
	@Override
	public int getPortalPreferencesesCount() {
		return _portalPreferencesLocalService.getPortalPreferencesesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _portalPreferencesLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _portalPreferencesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PortalPreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _portalPreferencesLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PortalPreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _portalPreferencesLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the portal preferenceses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PortalPreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of portal preferenceses
	* @param end the upper bound of the range of portal preferenceses (not inclusive)
	* @return the range of portal preferenceses
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.PortalPreferences> getPortalPreferenceses(
		int start, int end) {
		return _portalPreferencesLocalService.getPortalPreferenceses(start, end);
	}

	@Override
	public javax.portlet.PortletPreferences getPreferences(long ownerId,
		int ownerType) {
		return _portalPreferencesLocalService.getPreferences(ownerId, ownerType);
	}

	@Override
	public javax.portlet.PortletPreferences getPreferences(long ownerId,
		int ownerType, java.lang.String defaultPreferences) {
		return _portalPreferencesLocalService.getPreferences(ownerId,
			ownerType, defaultPreferences);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _portalPreferencesLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _portalPreferencesLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public PortalPreferencesLocalService getWrappedService() {
		return _portalPreferencesLocalService;
	}

	@Override
	public void setWrappedService(
		PortalPreferencesLocalService portalPreferencesLocalService) {
		_portalPreferencesLocalService = portalPreferencesLocalService;
	}

	private PortalPreferencesLocalService _portalPreferencesLocalService;
}