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

package com.liferay.contacts.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Entry. This utility wraps
 * {@link com.liferay.contacts.service.impl.EntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see EntryLocalService
 * @see com.liferay.contacts.service.base.EntryLocalServiceBaseImpl
 * @see com.liferay.contacts.service.impl.EntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class EntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.contacts.service.impl.EntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the entry to the database. Also notifies the appropriate model listeners.
	*
	* @param entry the entry
	* @return the entry that was added
	*/
	public static com.liferay.contacts.model.Entry addEntry(
		com.liferay.contacts.model.Entry entry) {
		return getService().addEntry(entry);
	}

	public static com.liferay.contacts.model.Entry addEntry(long userId,
		java.lang.String fullName, java.lang.String emailAddress,
		java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addEntry(userId, fullName, emailAddress, comments);
	}

	/**
	* Creates a new entry with the primary key. Does not add the entry to the database.
	*
	* @param entryId the primary key for the new entry
	* @return the new entry
	*/
	public static com.liferay.contacts.model.Entry createEntry(long entryId) {
		return getService().createEntry(entryId);
	}

	/**
	* Deletes the entry from the database. Also notifies the appropriate model listeners.
	*
	* @param entry the entry
	* @return the entry that was removed
	*/
	public static com.liferay.contacts.model.Entry deleteEntry(
		com.liferay.contacts.model.Entry entry) {
		return getService().deleteEntry(entry);
	}

	/**
	* Deletes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the entry
	* @return the entry that was removed
	* @throws PortalException if a entry with the primary key could not be found
	*/
	public static com.liferay.contacts.model.Entry deleteEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteEntry(entryId);
	}

	public static com.liferay.contacts.model.Entry fetchEntry(long entryId) {
		return getService().fetchEntry(entryId);
	}

	/**
	* Returns the entry with the primary key.
	*
	* @param entryId the primary key of the entry
	* @return the entry
	* @throws PortalException if a entry with the primary key could not be found
	*/
	public static com.liferay.contacts.model.Entry getEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEntry(entryId);
	}

	/**
	* Updates the entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param entry the entry
	* @return the entry that was updated
	*/
	public static com.liferay.contacts.model.Entry updateEntry(
		com.liferay.contacts.model.Entry entry) {
		return getService().updateEntry(entry);
	}

	public static com.liferay.contacts.model.Entry updateEntry(long entryId,
		java.lang.String fullName, java.lang.String emailAddress,
		java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateEntry(entryId, fullName, emailAddress, comments);
	}

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

	/**
	* Returns the number of entries.
	*
	* @return the number of entries
	*/
	public static int getEntriesCount() {
		return getService().getEntriesCount();
	}

	public static int getEntriesCount(long userId) {
		return getService().getEntriesCount(userId);
	}

	public static int searchCount(long userId, java.lang.String keywords) {
		return getService().searchCount(userId, keywords);
	}

	public static int searchUsersAndContactsCount(long companyId, long userId,
		java.lang.String keywords) {
		return getService()
				   .searchUsersAndContactsCount(companyId, userId, keywords);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.contacts.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.contacts.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.contacts.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of entries
	*/
	public static java.util.List<com.liferay.contacts.model.Entry> getEntries(
		int start, int end) {
		return getService().getEntries(start, end);
	}

	public static java.util.List<com.liferay.contacts.model.Entry> getEntries(
		long userId, int start, int end) {
		return getService().getEntries(userId, start, end);
	}

	public static java.util.List<com.liferay.contacts.model.Entry> search(
		long userId, java.lang.String keywords, int start, int end) {
		return getService().search(userId, keywords, start, end);
	}

	public static java.util.List<com.liferay.portal.kernel.model.BaseModel<?>> searchUsersAndContacts(
		long companyId, long userId, java.lang.String keywords, int start,
		int end) {
		return getService()
				   .searchUsersAndContacts(companyId, userId, keywords, start,
			end);
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

	public static EntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EntryLocalService, EntryLocalService> _serviceTracker =
		ServiceTrackerFactory.open(EntryLocalService.class);
}