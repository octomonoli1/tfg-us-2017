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

package com.liferay.marketplace.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AppLocalService}.
 *
 * @author Ryan Park
 * @see AppLocalService
 * @generated
 */
@ProviderType
public class AppLocalServiceWrapper implements AppLocalService,
	ServiceWrapper<AppLocalService> {
	public AppLocalServiceWrapper(AppLocalService appLocalService) {
		_appLocalService = appLocalService;
	}

	/**
	* Adds the app to the database. Also notifies the appropriate model listeners.
	*
	* @param app the app
	* @return the app that was added
	*/
	@Override
	public com.liferay.marketplace.model.App addApp(
		com.liferay.marketplace.model.App app) {
		return _appLocalService.addApp(app);
	}

	/**
	* Creates a new app with the primary key. Does not add the app to the database.
	*
	* @param appId the primary key for the new app
	* @return the new app
	*/
	@Override
	public com.liferay.marketplace.model.App createApp(long appId) {
		return _appLocalService.createApp(appId);
	}

	/**
	* Deletes the app from the database. Also notifies the appropriate model listeners.
	*
	* @param app the app
	* @return the app that was removed
	*/
	@Override
	public com.liferay.marketplace.model.App deleteApp(
		com.liferay.marketplace.model.App app) {
		return _appLocalService.deleteApp(app);
	}

	/**
	* Deletes the app with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appId the primary key of the app
	* @return the app that was removed
	* @throws PortalException if a app with the primary key could not be found
	*/
	@Override
	public com.liferay.marketplace.model.App deleteApp(long appId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _appLocalService.deleteApp(appId);
	}

	@Override
	public com.liferay.marketplace.model.App fetchApp(long appId) {
		return _appLocalService.fetchApp(appId);
	}

	/**
	* Returns the app with the matching UUID and company.
	*
	* @param uuid the app's UUID
	* @param companyId the primary key of the company
	* @return the matching app, or <code>null</code> if a matching app could not be found
	*/
	@Override
	public com.liferay.marketplace.model.App fetchAppByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _appLocalService.fetchAppByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public com.liferay.marketplace.model.App fetchRemoteApp(long remoteAppId) {
		return _appLocalService.fetchRemoteApp(remoteAppId);
	}

	/**
	* Returns the app with the primary key.
	*
	* @param appId the primary key of the app
	* @return the app
	* @throws PortalException if a app with the primary key could not be found
	*/
	@Override
	public com.liferay.marketplace.model.App getApp(long appId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _appLocalService.getApp(appId);
	}

	/**
	* Returns the app with the matching UUID and company.
	*
	* @param uuid the app's UUID
	* @param companyId the primary key of the company
	* @return the matching app
	* @throws PortalException if a matching app could not be found
	*/
	@Override
	public com.liferay.marketplace.model.App getAppByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _appLocalService.getAppByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Updates the app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param app the app
	* @return the app that was updated
	*/
	@Override
	public com.liferay.marketplace.model.App updateApp(
		com.liferay.marketplace.model.App app) {
		return _appLocalService.updateApp(app);
	}

	@Override
	public com.liferay.marketplace.model.App updateApp(long userId,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _appLocalService.updateApp(userId, file);
	}

	@Override
	public com.liferay.marketplace.model.App updateApp(long userId,
		long remoteAppId, java.lang.String title, java.lang.String description,
		java.lang.String category, java.lang.String iconURL,
		java.lang.String version, boolean required, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _appLocalService.updateApp(userId, remoteAppId, title,
			description, category, iconURL, version, required, file);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _appLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _appLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _appLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _appLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _appLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _appLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of apps.
	*
	* @return the number of apps
	*/
	@Override
	public int getAppsCount() {
		return _appLocalService.getAppsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _appLocalService.getOSGiServiceIdentifier();
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
		return _appLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _appLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _appLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the apps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @return the range of apps
	*/
	@Override
	public java.util.List<com.liferay.marketplace.model.App> getApps(
		int start, int end) {
		return _appLocalService.getApps(start, end);
	}

	@Override
	public java.util.List<com.liferay.marketplace.model.App> getApps(
		java.lang.String category) {
		return _appLocalService.getApps(category);
	}

	@Override
	public java.util.List<com.liferay.marketplace.model.App> getInstalledApps() {
		return _appLocalService.getInstalledApps();
	}

	@Override
	public java.util.List<com.liferay.marketplace.model.App> getInstalledApps(
		java.lang.String category) {
		return _appLocalService.getInstalledApps(category);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.String> getPrepackagedApps() {
		return _appLocalService.getPrepackagedApps();
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
		return _appLocalService.dynamicQueryCount(dynamicQuery);
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
		return _appLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public void clearInstalledAppsCache() {
		_appLocalService.clearInstalledAppsCache();
	}

	@Override
	public void installApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_appLocalService.installApp(remoteAppId);
	}

	@Override
	public void uninstallApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_appLocalService.uninstallApp(remoteAppId);
	}

	@Override
	public AppLocalService getWrappedService() {
		return _appLocalService;
	}

	@Override
	public void setWrappedService(AppLocalService appLocalService) {
		_appLocalService = appLocalService;
	}

	private AppLocalService _appLocalService;
}