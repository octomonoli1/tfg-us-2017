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

package com.liferay.asset.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetCategoryPropertyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategoryPropertyLocalService
 * @generated
 */
@ProviderType
public class AssetCategoryPropertyLocalServiceWrapper
	implements AssetCategoryPropertyLocalService,
		ServiceWrapper<AssetCategoryPropertyLocalService> {
	public AssetCategoryPropertyLocalServiceWrapper(
		AssetCategoryPropertyLocalService assetCategoryPropertyLocalService) {
		_assetCategoryPropertyLocalService = assetCategoryPropertyLocalService;
	}

	/**
	* Adds the asset category property to the database. Also notifies the appropriate model listeners.
	*
	* @param assetCategoryProperty the asset category property
	* @return the asset category property that was added
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategoryProperty addAssetCategoryProperty(
		com.liferay.asset.kernel.model.AssetCategoryProperty assetCategoryProperty) {
		return _assetCategoryPropertyLocalService.addAssetCategoryProperty(assetCategoryProperty);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetCategoryProperty addCategoryProperty(
		long userId, long categoryId, java.lang.String key,
		java.lang.String value)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryPropertyLocalService.addCategoryProperty(userId,
			categoryId, key, value);
	}

	/**
	* Creates a new asset category property with the primary key. Does not add the asset category property to the database.
	*
	* @param categoryPropertyId the primary key for the new asset category property
	* @return the new asset category property
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategoryProperty createAssetCategoryProperty(
		long categoryPropertyId) {
		return _assetCategoryPropertyLocalService.createAssetCategoryProperty(categoryPropertyId);
	}

	/**
	* Deletes the asset category property from the database. Also notifies the appropriate model listeners.
	*
	* @param assetCategoryProperty the asset category property
	* @return the asset category property that was removed
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategoryProperty deleteAssetCategoryProperty(
		com.liferay.asset.kernel.model.AssetCategoryProperty assetCategoryProperty) {
		return _assetCategoryPropertyLocalService.deleteAssetCategoryProperty(assetCategoryProperty);
	}

	/**
	* Deletes the asset category property with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param categoryPropertyId the primary key of the asset category property
	* @return the asset category property that was removed
	* @throws PortalException if a asset category property with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategoryProperty deleteAssetCategoryProperty(
		long categoryPropertyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryPropertyLocalService.deleteAssetCategoryProperty(categoryPropertyId);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetCategoryProperty fetchAssetCategoryProperty(
		long categoryPropertyId) {
		return _assetCategoryPropertyLocalService.fetchAssetCategoryProperty(categoryPropertyId);
	}

	/**
	* Returns the asset category property with the primary key.
	*
	* @param categoryPropertyId the primary key of the asset category property
	* @return the asset category property
	* @throws PortalException if a asset category property with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategoryProperty getAssetCategoryProperty(
		long categoryPropertyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryPropertyLocalService.getAssetCategoryProperty(categoryPropertyId);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetCategoryProperty getCategoryProperty(
		long categoryId, java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryPropertyLocalService.getCategoryProperty(categoryId,
			key);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetCategoryProperty getCategoryProperty(
		long categoryPropertyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryPropertyLocalService.getCategoryProperty(categoryPropertyId);
	}

	/**
	* Updates the asset category property in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetCategoryProperty the asset category property
	* @return the asset category property that was updated
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategoryProperty updateAssetCategoryProperty(
		com.liferay.asset.kernel.model.AssetCategoryProperty assetCategoryProperty) {
		return _assetCategoryPropertyLocalService.updateAssetCategoryProperty(assetCategoryProperty);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetCategoryProperty updateCategoryProperty(
		long categoryPropertyId, java.lang.String key, java.lang.String value)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryPropertyLocalService.updateCategoryProperty(categoryPropertyId,
			key, value);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetCategoryProperty updateCategoryProperty(
		long userId, long categoryPropertyId, java.lang.String key,
		java.lang.String value)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryPropertyLocalService.updateCategoryProperty(userId,
			categoryPropertyId, key, value);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _assetCategoryPropertyLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetCategoryPropertyLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _assetCategoryPropertyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryPropertyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetCategoryPropertyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of asset category properties.
	*
	* @return the number of asset category properties
	*/
	@Override
	public int getAssetCategoryPropertiesCount() {
		return _assetCategoryPropertyLocalService.getAssetCategoryPropertiesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _assetCategoryPropertyLocalService.getOSGiServiceIdentifier();
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
		return _assetCategoryPropertyLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetCategoryPropertyLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetCategoryPropertyLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the asset category properties.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset category properties
	* @param end the upper bound of the range of asset category properties (not inclusive)
	* @return the range of asset category properties
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategoryProperty> getAssetCategoryProperties(
		int start, int end) {
		return _assetCategoryPropertyLocalService.getAssetCategoryProperties(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategoryProperty> getCategoryProperties() {
		return _assetCategoryPropertyLocalService.getCategoryProperties();
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategoryProperty> getCategoryProperties(
		long entryId) {
		return _assetCategoryPropertyLocalService.getCategoryProperties(entryId);
	}

	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategoryProperty> getCategoryPropertyValues(
		long groupId, java.lang.String key) {
		return _assetCategoryPropertyLocalService.getCategoryPropertyValues(groupId,
			key);
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
		return _assetCategoryPropertyLocalService.dynamicQueryCount(dynamicQuery);
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
		return _assetCategoryPropertyLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteCategoryProperties(long entryId) {
		_assetCategoryPropertyLocalService.deleteCategoryProperties(entryId);
	}

	@Override
	public void deleteCategoryProperty(
		com.liferay.asset.kernel.model.AssetCategoryProperty categoryProperty) {
		_assetCategoryPropertyLocalService.deleteCategoryProperty(categoryProperty);
	}

	@Override
	public void deleteCategoryProperty(long categoryPropertyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_assetCategoryPropertyLocalService.deleteCategoryProperty(categoryPropertyId);
	}

	@Override
	public AssetCategoryPropertyLocalService getWrappedService() {
		return _assetCategoryPropertyLocalService;
	}

	@Override
	public void setWrappedService(
		AssetCategoryPropertyLocalService assetCategoryPropertyLocalService) {
		_assetCategoryPropertyLocalService = assetCategoryPropertyLocalService;
	}

	private AssetCategoryPropertyLocalService _assetCategoryPropertyLocalService;
}