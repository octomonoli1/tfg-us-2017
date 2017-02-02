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

package com.liferay.bookmarks.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BookmarksEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BookmarksEntryLocalService
 * @generated
 */
@ProviderType
public class BookmarksEntryLocalServiceWrapper
	implements BookmarksEntryLocalService,
		ServiceWrapper<BookmarksEntryLocalService> {
	public BookmarksEntryLocalServiceWrapper(
		BookmarksEntryLocalService bookmarksEntryLocalService) {
		_bookmarksEntryLocalService = bookmarksEntryLocalService;
	}

	/**
	* Adds the bookmarks entry to the database. Also notifies the appropriate model listeners.
	*
	* @param bookmarksEntry the bookmarks entry
	* @return the bookmarks entry that was added
	*/
	@Override
	public com.liferay.bookmarks.model.BookmarksEntry addBookmarksEntry(
		com.liferay.bookmarks.model.BookmarksEntry bookmarksEntry) {
		return _bookmarksEntryLocalService.addBookmarksEntry(bookmarksEntry);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry addEntry(long userId,
		long groupId, long folderId, java.lang.String name,
		java.lang.String url, java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.addEntry(userId, groupId, folderId,
			name, url, description, serviceContext);
	}

	/**
	* Creates a new bookmarks entry with the primary key. Does not add the bookmarks entry to the database.
	*
	* @param entryId the primary key for the new bookmarks entry
	* @return the new bookmarks entry
	*/
	@Override
	public com.liferay.bookmarks.model.BookmarksEntry createBookmarksEntry(
		long entryId) {
		return _bookmarksEntryLocalService.createBookmarksEntry(entryId);
	}

	/**
	* Deletes the bookmarks entry from the database. Also notifies the appropriate model listeners.
	*
	* @param bookmarksEntry the bookmarks entry
	* @return the bookmarks entry that was removed
	*/
	@Override
	public com.liferay.bookmarks.model.BookmarksEntry deleteBookmarksEntry(
		com.liferay.bookmarks.model.BookmarksEntry bookmarksEntry) {
		return _bookmarksEntryLocalService.deleteBookmarksEntry(bookmarksEntry);
	}

	/**
	* Deletes the bookmarks entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the bookmarks entry
	* @return the bookmarks entry that was removed
	* @throws PortalException if a bookmarks entry with the primary key could not be found
	*/
	@Override
	public com.liferay.bookmarks.model.BookmarksEntry deleteBookmarksEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.deleteBookmarksEntry(entryId);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry deleteEntry(
		com.liferay.bookmarks.model.BookmarksEntry entry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.deleteEntry(entry);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry deleteEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.deleteEntry(entryId);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry fetchBookmarksEntry(
		long entryId) {
		return _bookmarksEntryLocalService.fetchBookmarksEntry(entryId);
	}

	/**
	* Returns the bookmarks entry matching the UUID and group.
	*
	* @param uuid the bookmarks entry's UUID
	* @param groupId the primary key of the group
	* @return the matching bookmarks entry, or <code>null</code> if a matching bookmarks entry could not be found
	*/
	@Override
	public com.liferay.bookmarks.model.BookmarksEntry fetchBookmarksEntryByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _bookmarksEntryLocalService.fetchBookmarksEntryByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the bookmarks entry with the primary key.
	*
	* @param entryId the primary key of the bookmarks entry
	* @return the bookmarks entry
	* @throws PortalException if a bookmarks entry with the primary key could not be found
	*/
	@Override
	public com.liferay.bookmarks.model.BookmarksEntry getBookmarksEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.getBookmarksEntry(entryId);
	}

	/**
	* Returns the bookmarks entry matching the UUID and group.
	*
	* @param uuid the bookmarks entry's UUID
	* @param groupId the primary key of the group
	* @return the matching bookmarks entry
	* @throws PortalException if a matching bookmarks entry could not be found
	*/
	@Override
	public com.liferay.bookmarks.model.BookmarksEntry getBookmarksEntryByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.getBookmarksEntryByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry getEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.getEntry(entryId);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry moveEntry(long entryId,
		long parentFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.moveEntry(entryId, parentFolderId);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry moveEntryFromTrash(
		long userId, long entryId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.moveEntryFromTrash(userId, entryId,
			parentFolderId);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry moveEntryToTrash(
		long userId, com.liferay.bookmarks.model.BookmarksEntry entry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.moveEntryToTrash(userId, entry);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry moveEntryToTrash(
		long userId, long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.moveEntryToTrash(userId, entryId);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry openEntry(long userId,
		com.liferay.bookmarks.model.BookmarksEntry entry) {
		return _bookmarksEntryLocalService.openEntry(userId, entry);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry openEntry(long userId,
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.openEntry(userId, entryId);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry restoreEntryFromTrash(
		long userId, long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.restoreEntryFromTrash(userId, entryId);
	}

	/**
	* Updates the bookmarks entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param bookmarksEntry the bookmarks entry
	* @return the bookmarks entry that was updated
	*/
	@Override
	public com.liferay.bookmarks.model.BookmarksEntry updateBookmarksEntry(
		com.liferay.bookmarks.model.BookmarksEntry bookmarksEntry) {
		return _bookmarksEntryLocalService.updateBookmarksEntry(bookmarksEntry);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry updateEntry(long userId,
		long entryId, long groupId, long folderId, java.lang.String name,
		java.lang.String url, java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.updateEntry(userId, entryId,
			groupId, folderId, name, url, description, serviceContext);
	}

	@Override
	public com.liferay.bookmarks.model.BookmarksEntry updateStatus(
		long userId, com.liferay.bookmarks.model.BookmarksEntry entry,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.updateStatus(userId, entry, status);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _bookmarksEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _bookmarksEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _bookmarksEntryLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _bookmarksEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long groupId,
		long userId, long creatorUserId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bookmarksEntryLocalService.search(groupId, userId,
			creatorUserId, status, start, end);
	}

	/**
	* Returns the number of bookmarks entries.
	*
	* @return the number of bookmarks entries
	*/
	@Override
	public int getBookmarksEntriesCount() {
		return _bookmarksEntryLocalService.getBookmarksEntriesCount();
	}

	@Override
	public int getEntriesCount(long groupId, long folderId) {
		return _bookmarksEntryLocalService.getEntriesCount(groupId, folderId);
	}

	@Override
	public int getEntriesCount(long groupId, long folderId, int status) {
		return _bookmarksEntryLocalService.getEntriesCount(groupId, folderId,
			status);
	}

	@Override
	public int getFoldersEntriesCount(long groupId,
		java.util.List<java.lang.Long> folderIds) {
		return _bookmarksEntryLocalService.getFoldersEntriesCount(groupId,
			folderIds);
	}

	@Override
	public int getGroupEntriesCount(long groupId) {
		return _bookmarksEntryLocalService.getGroupEntriesCount(groupId);
	}

	@Override
	public int getGroupEntriesCount(long groupId, long userId) {
		return _bookmarksEntryLocalService.getGroupEntriesCount(groupId, userId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _bookmarksEntryLocalService.getOSGiServiceIdentifier();
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
		return _bookmarksEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bookmarks.model.impl.BookmarksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _bookmarksEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bookmarks.model.impl.BookmarksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _bookmarksEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the bookmarks entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bookmarks.model.impl.BookmarksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bookmarks entries
	* @param end the upper bound of the range of bookmarks entries (not inclusive)
	* @return the range of bookmarks entries
	*/
	@Override
	public java.util.List<com.liferay.bookmarks.model.BookmarksEntry> getBookmarksEntries(
		int start, int end) {
		return _bookmarksEntryLocalService.getBookmarksEntries(start, end);
	}

	/**
	* Returns all the bookmarks entries matching the UUID and company.
	*
	* @param uuid the UUID of the bookmarks entries
	* @param companyId the primary key of the company
	* @return the matching bookmarks entries, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.bookmarks.model.BookmarksEntry> getBookmarksEntriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _bookmarksEntryLocalService.getBookmarksEntriesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of bookmarks entries matching the UUID and company.
	*
	* @param uuid the UUID of the bookmarks entries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of bookmarks entries
	* @param end the upper bound of the range of bookmarks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching bookmarks entries, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.bookmarks.model.BookmarksEntry> getBookmarksEntriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.bookmarks.model.BookmarksEntry> orderByComparator) {
		return _bookmarksEntryLocalService.getBookmarksEntriesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.bookmarks.model.BookmarksEntry> getEntries(
		long groupId, long folderId, int start, int end) {
		return _bookmarksEntryLocalService.getEntries(groupId, folderId, start,
			end);
	}

	@Override
	public java.util.List<com.liferay.bookmarks.model.BookmarksEntry> getEntries(
		long groupId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.bookmarks.model.BookmarksEntry> orderByComparator) {
		return _bookmarksEntryLocalService.getEntries(groupId, folderId, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.bookmarks.model.BookmarksEntry> getEntries(
		long groupId, long folderId, int status, int start, int end) {
		return _bookmarksEntryLocalService.getEntries(groupId, folderId,
			status, start, end);
	}

	@Override
	public java.util.List<com.liferay.bookmarks.model.BookmarksEntry> getEntries(
		long groupId, long folderId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.bookmarks.model.BookmarksEntry> orderByComparator) {
		return _bookmarksEntryLocalService.getEntries(groupId, folderId,
			status, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.bookmarks.model.BookmarksEntry> getGroupEntries(
		long groupId, int start, int end) {
		return _bookmarksEntryLocalService.getGroupEntries(groupId, start, end);
	}

	@Override
	public java.util.List<com.liferay.bookmarks.model.BookmarksEntry> getGroupEntries(
		long groupId, long userId, int start, int end) {
		return _bookmarksEntryLocalService.getGroupEntries(groupId, userId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.bookmarks.model.BookmarksEntry> getNoAssetEntries() {
		return _bookmarksEntryLocalService.getNoAssetEntries();
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
		return _bookmarksEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _bookmarksEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteEntries(long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_bookmarksEntryLocalService.deleteEntries(groupId, folderId);
	}

	@Override
	public void deleteEntries(long groupId, long folderId,
		boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		_bookmarksEntryLocalService.deleteEntries(groupId, folderId,
			includeTrashedEntries);
	}

	@Override
	public void rebuildTree(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_bookmarksEntryLocalService.rebuildTree(companyId);
	}

	@Override
	public void setTreePaths(long folderId, java.lang.String treePath,
		boolean reindex)
		throws com.liferay.portal.kernel.exception.PortalException {
		_bookmarksEntryLocalService.setTreePaths(folderId, treePath, reindex);
	}

	@Override
	public void subscribeEntry(long userId, long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_bookmarksEntryLocalService.subscribeEntry(userId, entryId);
	}

	@Override
	public void unsubscribeEntry(long userId, long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_bookmarksEntryLocalService.unsubscribeEntry(userId, entryId);
	}

	@Override
	public void updateAsset(long userId,
		com.liferay.bookmarks.model.BookmarksEntry entry,
		long[] assetCategoryIds, java.lang.String[] assetTagNames,
		long[] assetLinkEntryIds, java.lang.Double priority)
		throws com.liferay.portal.kernel.exception.PortalException {
		_bookmarksEntryLocalService.updateAsset(userId, entry,
			assetCategoryIds, assetTagNames, assetLinkEntryIds, priority);
	}

	@Override
	public BookmarksEntryLocalService getWrappedService() {
		return _bookmarksEntryLocalService;
	}

	@Override
	public void setWrappedService(
		BookmarksEntryLocalService bookmarksEntryLocalService) {
		_bookmarksEntryLocalService = bookmarksEntryLocalService;
	}

	private BookmarksEntryLocalService _bookmarksEntryLocalService;
}