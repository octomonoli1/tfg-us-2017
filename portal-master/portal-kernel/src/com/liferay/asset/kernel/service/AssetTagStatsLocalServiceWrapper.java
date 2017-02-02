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
 * Provides a wrapper for {@link AssetTagStatsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagStatsLocalService
 * @generated
 */
@ProviderType
public class AssetTagStatsLocalServiceWrapper
	implements AssetTagStatsLocalService,
		ServiceWrapper<AssetTagStatsLocalService> {
	public AssetTagStatsLocalServiceWrapper(
		AssetTagStatsLocalService assetTagStatsLocalService) {
		_assetTagStatsLocalService = assetTagStatsLocalService;
	}

	/**
	* Adds the asset tag stats to the database. Also notifies the appropriate model listeners.
	*
	* @param assetTagStats the asset tag stats
	* @return the asset tag stats that was added
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetTagStats addAssetTagStats(
		com.liferay.asset.kernel.model.AssetTagStats assetTagStats) {
		return _assetTagStatsLocalService.addAssetTagStats(assetTagStats);
	}

	/**
	* Adds an asset tag statistics instance.
	*
	* @param tagId the primary key of the tag
	* @param classNameId the asset entry's class name ID
	* @return the asset tag statistics instance
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetTagStats addTagStats(
		long tagId, long classNameId) {
		return _assetTagStatsLocalService.addTagStats(tagId, classNameId);
	}

	/**
	* Creates a new asset tag stats with the primary key. Does not add the asset tag stats to the database.
	*
	* @param tagStatsId the primary key for the new asset tag stats
	* @return the new asset tag stats
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetTagStats createAssetTagStats(
		long tagStatsId) {
		return _assetTagStatsLocalService.createAssetTagStats(tagStatsId);
	}

	/**
	* Deletes the asset tag stats from the database. Also notifies the appropriate model listeners.
	*
	* @param assetTagStats the asset tag stats
	* @return the asset tag stats that was removed
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetTagStats deleteAssetTagStats(
		com.liferay.asset.kernel.model.AssetTagStats assetTagStats) {
		return _assetTagStatsLocalService.deleteAssetTagStats(assetTagStats);
	}

	/**
	* Deletes the asset tag stats with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tagStatsId the primary key of the asset tag stats
	* @return the asset tag stats that was removed
	* @throws PortalException if a asset tag stats with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetTagStats deleteAssetTagStats(
		long tagStatsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetTagStatsLocalService.deleteAssetTagStats(tagStatsId);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetTagStats fetchAssetTagStats(
		long tagStatsId) {
		return _assetTagStatsLocalService.fetchAssetTagStats(tagStatsId);
	}

	/**
	* Returns the asset tag stats with the primary key.
	*
	* @param tagStatsId the primary key of the asset tag stats
	* @return the asset tag stats
	* @throws PortalException if a asset tag stats with the primary key could not be found
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetTagStats getAssetTagStats(
		long tagStatsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetTagStatsLocalService.getAssetTagStats(tagStatsId);
	}

	/**
	* Returns the asset tag statistics instance with the tag and asset entry
	* matching the class name ID
	*
	* @param tagId the primary key of the tag
	* @param classNameId the asset entry's class name ID
	* @return Returns the asset tag statistics instance with the tag and asset
	entry  matching the class name ID
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetTagStats getTagStats(
		long tagId, long classNameId) {
		return _assetTagStatsLocalService.getTagStats(tagId, classNameId);
	}

	/**
	* Updates the asset tag stats in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetTagStats the asset tag stats
	* @return the asset tag stats that was updated
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetTagStats updateAssetTagStats(
		com.liferay.asset.kernel.model.AssetTagStats assetTagStats) {
		return _assetTagStatsLocalService.updateAssetTagStats(assetTagStats);
	}

	/**
	* Updates the asset tag statistics instance.
	*
	* @param tagId the primary key of the tag
	* @param classNameId the asset entry's class name ID
	* @return the updated asset tag statistics instance
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetTagStats updateTagStats(
		long tagId, long classNameId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetTagStatsLocalService.updateTagStats(tagId, classNameId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _assetTagStatsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetTagStatsLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _assetTagStatsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetTagStatsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetTagStatsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of asset tag statses.
	*
	* @return the number of asset tag statses
	*/
	@Override
	public int getAssetTagStatsesCount() {
		return _assetTagStatsLocalService.getAssetTagStatsesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _assetTagStatsLocalService.getOSGiServiceIdentifier();
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
		return _assetTagStatsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetTagStatsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetTagStatsLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the asset tag statses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset tag statses
	* @param end the upper bound of the range of asset tag statses (not inclusive)
	* @return the range of asset tag statses
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetTagStats> getAssetTagStatses(
		int start, int end) {
		return _assetTagStatsLocalService.getAssetTagStatses(start, end);
	}

	/**
	* Returns a range of all the asset tag statistics instances associated with
	* the asset entry matching the class name ID.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param classNameId the asset entry's class name ID
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of asset tag statistics associated with the asset entry
	matching the class name ID
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetTagStats> getTagStats(
		long classNameId, int start, int end) {
		return _assetTagStatsLocalService.getTagStats(classNameId, start, end);
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
		return _assetTagStatsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _assetTagStatsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Deletes the asset tag statistics instance.
	*
	* @param tagStats the asset tag statistics instance
	*/
	@Override
	public void deleteTagStats(
		com.liferay.asset.kernel.model.AssetTagStats tagStats) {
		_assetTagStatsLocalService.deleteTagStats(tagStats);
	}

	/**
	* Deletes the asset tag statistics instance matching the tag statistics ID.
	*
	* @param tagStatsId the primary key of the asset tag statistics instance
	*/
	@Override
	public void deleteTagStats(long tagStatsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_assetTagStatsLocalService.deleteTagStats(tagStatsId);
	}

	/**
	* Deletes all asset tag statistics instances associated with the asset
	* entry matching the class name ID.
	*
	* @param classNameId the asset entry's class name ID
	*/
	@Override
	public void deleteTagStatsByClassNameId(long classNameId) {
		_assetTagStatsLocalService.deleteTagStatsByClassNameId(classNameId);
	}

	/**
	* Deletes all asset tag statistics instances associated with the tag.
	*
	* @param tagId the primary key of the tag
	*/
	@Override
	public void deleteTagStatsByTagId(long tagId) {
		_assetTagStatsLocalService.deleteTagStatsByTagId(tagId);
	}

	@Override
	public AssetTagStatsLocalService getWrappedService() {
		return _assetTagStatsLocalService;
	}

	@Override
	public void setWrappedService(
		AssetTagStatsLocalService assetTagStatsLocalService) {
		_assetTagStatsLocalService = assetTagStatsLocalService;
	}

	private AssetTagStatsLocalService _assetTagStatsLocalService;
}