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

import com.liferay.asset.kernel.model.AssetTagStats;

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

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for AssetTagStats. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagStatsLocalServiceUtil
 * @see com.liferay.portlet.asset.service.base.AssetTagStatsLocalServiceBaseImpl
 * @see com.liferay.portlet.asset.service.impl.AssetTagStatsLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AssetTagStatsLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetTagStatsLocalServiceUtil} to access the asset tag stats local service. Add custom service methods to {@link com.liferay.portlet.asset.service.impl.AssetTagStatsLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the asset tag stats to the database. Also notifies the appropriate model listeners.
	*
	* @param assetTagStats the asset tag stats
	* @return the asset tag stats that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AssetTagStats addAssetTagStats(AssetTagStats assetTagStats);

	/**
	* Adds an asset tag statistics instance.
	*
	* @param tagId the primary key of the tag
	* @param classNameId the asset entry's class name ID
	* @return the asset tag statistics instance
	*/
	public AssetTagStats addTagStats(long tagId, long classNameId);

	/**
	* Creates a new asset tag stats with the primary key. Does not add the asset tag stats to the database.
	*
	* @param tagStatsId the primary key for the new asset tag stats
	* @return the new asset tag stats
	*/
	public AssetTagStats createAssetTagStats(long tagStatsId);

	/**
	* Deletes the asset tag stats from the database. Also notifies the appropriate model listeners.
	*
	* @param assetTagStats the asset tag stats
	* @return the asset tag stats that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public AssetTagStats deleteAssetTagStats(AssetTagStats assetTagStats);

	/**
	* Deletes the asset tag stats with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tagStatsId the primary key of the asset tag stats
	* @return the asset tag stats that was removed
	* @throws PortalException if a asset tag stats with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public AssetTagStats deleteAssetTagStats(long tagStatsId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetTagStats fetchAssetTagStats(long tagStatsId);

	/**
	* Returns the asset tag stats with the primary key.
	*
	* @param tagStatsId the primary key of the asset tag stats
	* @return the asset tag stats
	* @throws PortalException if a asset tag stats with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetTagStats getAssetTagStats(long tagStatsId)
		throws PortalException;

	/**
	* Returns the asset tag statistics instance with the tag and asset entry
	* matching the class name ID
	*
	* @param tagId the primary key of the tag
	* @param classNameId the asset entry's class name ID
	* @return Returns the asset tag statistics instance with the tag and asset
	entry  matching the class name ID
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetTagStats getTagStats(long tagId, long classNameId);

	/**
	* Updates the asset tag stats in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetTagStats the asset tag stats
	* @return the asset tag stats that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AssetTagStats updateAssetTagStats(AssetTagStats assetTagStats);

	/**
	* Updates the asset tag statistics instance.
	*
	* @param tagId the primary key of the tag
	* @param classNameId the asset entry's class name ID
	* @return the updated asset tag statistics instance
	*/
	public AssetTagStats updateTagStats(long tagId, long classNameId)
		throws PortalException;

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

	/**
	* Returns the number of asset tag statses.
	*
	* @return the number of asset tag statses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetTagStatsesCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetTagStats> getAssetTagStatses(int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetTagStats> getTagStats(long classNameId, int start, int end);

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

	/**
	* Deletes the asset tag statistics instance.
	*
	* @param tagStats the asset tag statistics instance
	*/
	public void deleteTagStats(AssetTagStats tagStats);

	/**
	* Deletes the asset tag statistics instance matching the tag statistics ID.
	*
	* @param tagStatsId the primary key of the asset tag statistics instance
	*/
	public void deleteTagStats(long tagStatsId) throws PortalException;

	/**
	* Deletes all asset tag statistics instances associated with the asset
	* entry matching the class name ID.
	*
	* @param classNameId the asset entry's class name ID
	*/
	public void deleteTagStatsByClassNameId(long classNameId);

	/**
	* Deletes all asset tag statistics instances associated with the tag.
	*
	* @param tagId the primary key of the tag
	*/
	public void deleteTagStatsByTagId(long tagId);
}