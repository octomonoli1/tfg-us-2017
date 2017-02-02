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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.increment.BufferedIncrement;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for AssetEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryLocalServiceUtil
 * @see com.liferay.portlet.asset.service.base.AssetEntryLocalServiceBaseImpl
 * @see com.liferay.portlet.asset.service.impl.AssetEntryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AssetEntryLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetEntryLocalServiceUtil} to access the asset entry local service. Add custom service methods to {@link com.liferay.portlet.asset.service.impl.AssetEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasAssetCategoryAssetEntries(long categoryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasAssetCategoryAssetEntry(long categoryId, long entryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasAssetTagAssetEntries(long tagId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasAssetTagAssetEntry(long tagId, long entryId);

	/**
	* Adds the asset entry to the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntry the asset entry
	* @return the asset entry that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AssetEntry addAssetEntry(AssetEntry assetEntry);

	/**
	* Creates a new asset entry with the primary key. Does not add the asset entry to the database.
	*
	* @param entryId the primary key for the new asset entry
	* @return the new asset entry
	*/
	public AssetEntry createAssetEntry(long entryId);

	/**
	* Deletes the asset entry from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntry the asset entry
	* @return the asset entry that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public AssetEntry deleteAssetEntry(AssetEntry assetEntry);

	/**
	* Deletes the asset entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the asset entry
	* @return the asset entry that was removed
	* @throws PortalException if a asset entry with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public AssetEntry deleteAssetEntry(long entryId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetEntry fetchAssetEntry(long entryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetEntry fetchEntry(java.lang.String className, long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetEntry fetchEntry(long entryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetEntry fetchEntry(long groupId, java.lang.String classUuid);

	/**
	* Returns the asset entry with the primary key.
	*
	* @param entryId the primary key of the asset entry
	* @return the asset entry
	* @throws PortalException if a asset entry with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetEntry getAssetEntry(long entryId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetEntry getEntry(java.lang.String className, long classPK)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetEntry getEntry(long entryId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetEntry getEntry(long groupId, java.lang.String classUuid)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetEntry getNextEntry(long entryId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetEntry getParentEntry(long entryId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetEntry getPreviousEntry(long entryId) throws PortalException;

	public AssetEntry incrementViewCounter(long userId,
		java.lang.String className, long classPK) throws PortalException;

	/**
	* Updates the asset entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetEntry the asset entry
	* @return the asset entry that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AssetEntry updateAssetEntry(AssetEntry assetEntry);

	/**
	* @deprecated As of 7.0.0, replaced by {@link #updateEntry(String, long,
	Date, Date, boolean, boolean)}
	*/
	@java.lang.Deprecated
	public AssetEntry updateEntry(java.lang.String className, long classPK,
		Date publishDate, boolean visible) throws PortalException;

	public AssetEntry updateEntry(java.lang.String className, long classPK,
		Date publishDate, Date expirationDate, boolean listable, boolean visible)
		throws PortalException;

	/**
	* @deprecated As of 7.0.0, replaced by {@link #updateEntry(String, long,
	Date, Date, boolean, boolean)}
	*/
	@java.lang.Deprecated
	public AssetEntry updateEntry(java.lang.String className, long classPK,
		Date publishDate, Date expirationDate, boolean visible)
		throws PortalException;

	public AssetEntry updateEntry(long userId, long groupId,
		java.lang.String className, long classPK, long[] categoryIds,
		java.lang.String[] tagNames) throws PortalException;

	/**
	* @deprecated As of 7.0.0, replaced by {@link #updateEntry(long, long,
	Date, Date, String, long, String, long, long[], String[],
	boolean, boolean, Date, Date, Date, Date, String, String,
	String, String, String, String, int, int, Double)}
	*/
	@java.lang.Deprecated
	public AssetEntry updateEntry(long userId, long groupId, Date createDate,
		Date modifiedDate, java.lang.String className, long classPK,
		java.lang.String classUuid, long classTypeId, long[] categoryIds,
		java.lang.String[] tagNames, boolean listable, boolean visible,
		Date startDate, Date endDate, Date expirationDate,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String summary,
		java.lang.String url, java.lang.String layoutUuid, int height,
		int width, java.lang.Double priority) throws PortalException;

	public AssetEntry updateEntry(long userId, long groupId, Date createDate,
		Date modifiedDate, java.lang.String className, long classPK,
		java.lang.String classUuid, long classTypeId, long[] categoryIds,
		java.lang.String[] tagNames, boolean listable, boolean visible,
		Date startDate, Date endDate, Date publishDate, Date expirationDate,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String summary,
		java.lang.String url, java.lang.String layoutUuid, int height,
		int width, java.lang.Double priority) throws PortalException;

	/**
	* @deprecated As of 7.0.0, replaced by {@link #updateEntry(long, long,
	Date, Date, String, long, String, long, long[], String[],
	boolean, boolean, Date, Date, Date, Date, String, String,
	String, String, String, String, int, int, Double)}
	*/
	@java.lang.Deprecated
	public AssetEntry updateEntry(long userId, long groupId, Date createDate,
		Date modifiedDate, java.lang.String className, long classPK,
		java.lang.String classUuid, long classTypeId, long[] categoryIds,
		java.lang.String[] tagNames, boolean visible, Date startDate,
		Date endDate, Date expirationDate, java.lang.String mimeType,
		java.lang.String title, java.lang.String description,
		java.lang.String summary, java.lang.String url,
		java.lang.String layoutUuid, int height, int width,
		java.lang.Integer priority, boolean sync) throws PortalException;

	public AssetEntry updateVisible(AssetEntry entry, boolean visible)
		throws PortalException;

	public AssetEntry updateVisible(java.lang.String className, long classPK,
		boolean visible) throws PortalException;

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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, long[] groupIds, long userId,
		java.lang.String className, java.lang.String keywords, int status,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, long[] groupIds, long userId,
		java.lang.String className, java.lang.String userName,
		java.lang.String title, java.lang.String description,
		java.lang.String assetCategoryIds, java.lang.String assetTagNames,
		int status, boolean andSearch, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, long[] groupIds, long userId,
		java.lang.String className, long classTypeId,
		java.lang.String keywords, boolean showNonindexable, int status,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, long[] groupIds, long userId,
		java.lang.String className, long classTypeId,
		java.lang.String keywords, boolean showNonindexable, int[] statuses,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, long[] groupIds, long userId,
		java.lang.String className, long classTypeId,
		java.lang.String keywords, int status, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, long[] groupIds, long userId,
		java.lang.String className, long classTypeId,
		java.lang.String userName, java.lang.String title,
		java.lang.String description, java.lang.String assetCategoryIds,
		java.lang.String assetTagNames, boolean showNonindexable, int status,
		boolean andSearch, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, long[] groupIds, long userId,
		java.lang.String className, long classTypeId,
		java.lang.String userName, java.lang.String title,
		java.lang.String description, java.lang.String assetCategoryIds,
		java.lang.String assetTagNames, boolean showNonindexable,
		int[] statuses, boolean andSearch, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, long[] groupIds, long userId,
		java.lang.String className, long classTypeId,
		java.lang.String userName, java.lang.String title,
		java.lang.String description, java.lang.String assetCategoryIds,
		java.lang.String assetTagNames, int status, boolean andSearch,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetCategoryAssetEntriesCount(long categoryId);

	/**
	* Returns the number of asset entries.
	*
	* @return the number of asset entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetEntriesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetTagAssetEntriesCount(long tagId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCompanyEntriesCount(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getEntriesCount(AssetEntryQuery entryQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getEntriesCount(long[] groupIds, long[] classNameIds,
		java.lang.String keywords, java.lang.String userName,
		java.lang.String title, java.lang.String description,
		java.lang.Boolean listable, boolean advancedSearch, boolean andOperator);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<AssetEntry> getAncestorEntries(long entryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getAssetCategoryAssetEntries(long categoryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getAssetCategoryAssetEntries(long categoryId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getAssetCategoryAssetEntries(long categoryId,
		int start, int end, OrderByComparator<AssetEntry> orderByComparator);

	/**
	* Returns a range of all the asset entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.asset.model.impl.AssetEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entries
	* @param end the upper bound of the range of asset entries (not inclusive)
	* @return the range of asset entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getAssetEntries(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getAssetTagAssetEntries(long tagId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getAssetTagAssetEntries(long tagId, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getAssetTagAssetEntries(long tagId, int start,
		int end, OrderByComparator<AssetEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getChildEntries(long entryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getCompanyEntries(long companyId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getEntries(AssetEntryQuery entryQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getEntries(long[] groupIds, long[] classNameIds,
		java.lang.String keywords, java.lang.String userName,
		java.lang.String title, java.lang.String description,
		java.lang.Boolean listable, boolean advancedSearch,
		boolean andOperator, int start, int end, java.lang.String orderByCol1,
		java.lang.String orderByCol2, java.lang.String orderByType1,
		java.lang.String orderByType2);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getGroupEntries(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getTopViewedEntries(java.lang.String className,
		boolean asc, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetEntry> getTopViewedEntries(java.lang.String[] className,
		boolean asc, int start, int end);

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
	public long searchCount(long companyId, long[] groupIds, long userId,
		java.lang.String className, long classTypeId,
		java.lang.String keywords, boolean showNonindexable, int[] statuses);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long searchCount(long companyId, long[] groupIds, long userId,
		java.lang.String className, long classTypeId,
		java.lang.String userName, java.lang.String title,
		java.lang.String description, java.lang.String assetCategoryIds,
		java.lang.String assetTagNames, boolean showInvisible,
		boolean showNonindexable, int[] statuses, boolean andSearch);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long searchCount(long companyId, long[] groupIds, long userId,
		java.lang.String className, long classTypeId,
		java.lang.String userName, java.lang.String title,
		java.lang.String description, java.lang.String assetCategoryIds,
		java.lang.String assetTagNames, boolean showNonindexable,
		int[] statuses, boolean andSearch);

	/**
	* Returns the categoryIds of the asset categories associated with the asset entry.
	*
	* @param entryId the entryId of the asset entry
	* @return long[] the categoryIds of asset categories associated with the asset entry
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getAssetCategoryPrimaryKeys(long entryId);

	/**
	* Returns the tagIds of the asset tags associated with the asset entry.
	*
	* @param entryId the entryId of the asset entry
	* @return long[] the tagIds of asset tags associated with the asset entry
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getAssetTagPrimaryKeys(long entryId);

	public void addAssetCategoryAssetEntries(long categoryId,
		List<AssetEntry> assetEntries);

	public void addAssetCategoryAssetEntries(long categoryId, long[] entryIds);

	public void addAssetCategoryAssetEntry(long categoryId,
		AssetEntry assetEntry);

	public void addAssetCategoryAssetEntry(long categoryId, long entryId);

	public void addAssetTagAssetEntries(long tagId,
		List<AssetEntry> assetEntries);

	public void addAssetTagAssetEntries(long tagId, long[] entryIds);

	public void addAssetTagAssetEntry(long tagId, AssetEntry assetEntry);

	public void addAssetTagAssetEntry(long tagId, long entryId);

	public void clearAssetCategoryAssetEntries(long categoryId);

	public void clearAssetTagAssetEntries(long tagId);

	public void deleteAssetCategoryAssetEntries(long categoryId,
		List<AssetEntry> assetEntries);

	public void deleteAssetCategoryAssetEntries(long categoryId, long[] entryIds);

	public void deleteAssetCategoryAssetEntry(long categoryId,
		AssetEntry assetEntry);

	public void deleteAssetCategoryAssetEntry(long categoryId, long entryId);

	public void deleteAssetTagAssetEntries(long tagId,
		List<AssetEntry> assetEntries);

	public void deleteAssetTagAssetEntries(long tagId, long[] entryIds);

	public void deleteAssetTagAssetEntry(long tagId, AssetEntry assetEntry);

	public void deleteAssetTagAssetEntry(long tagId, long entryId);

	public void deleteEntry(AssetEntry entry) throws PortalException;

	public void deleteEntry(java.lang.String className, long classPK)
		throws PortalException;

	public void deleteEntry(long entryId) throws PortalException;

	public void deleteGroupEntries(long groupId) throws PortalException;

	@BufferedIncrement(configuration = "AssetEntry", incrementClass = com.liferay.portal.kernel.increment.NumberIncrement.class)
	public void incrementViewCounter(long userId, java.lang.String className,
		long classPK, int increment);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void reindex(List<AssetEntry> entries) throws PortalException;

	public void setAssetCategoryAssetEntries(long categoryId, long[] entryIds);

	public void setAssetTagAssetEntries(long tagId, long[] entryIds);

	public void validate(long groupId, java.lang.String className,
		long classTypePK, long[] categoryIds, java.lang.String[] tagNames)
		throws PortalException;

	/**
	* @deprecated As of 7.0.0, replaced by {@link #validate(long, String, long,
	long[], String[])}
	*/
	@java.lang.Deprecated
	public void validate(long groupId, java.lang.String className,
		long[] categoryIds, java.lang.String[] tagNames)
		throws PortalException;
}