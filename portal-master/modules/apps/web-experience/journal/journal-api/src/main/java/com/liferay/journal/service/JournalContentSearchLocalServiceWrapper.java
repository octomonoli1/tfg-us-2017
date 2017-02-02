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

package com.liferay.journal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link JournalContentSearchLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see JournalContentSearchLocalService
 * @generated
 */
@ProviderType
public class JournalContentSearchLocalServiceWrapper
	implements JournalContentSearchLocalService,
		ServiceWrapper<JournalContentSearchLocalService> {
	public JournalContentSearchLocalServiceWrapper(
		JournalContentSearchLocalService journalContentSearchLocalService) {
		_journalContentSearchLocalService = journalContentSearchLocalService;
	}

	/**
	* Adds the journal content search to the database. Also notifies the appropriate model listeners.
	*
	* @param journalContentSearch the journal content search
	* @return the journal content search that was added
	*/
	@Override
	public com.liferay.journal.model.JournalContentSearch addJournalContentSearch(
		com.liferay.journal.model.JournalContentSearch journalContentSearch) {
		return _journalContentSearchLocalService.addJournalContentSearch(journalContentSearch);
	}

	/**
	* Creates a new journal content search with the primary key. Does not add the journal content search to the database.
	*
	* @param contentSearchId the primary key for the new journal content search
	* @return the new journal content search
	*/
	@Override
	public com.liferay.journal.model.JournalContentSearch createJournalContentSearch(
		long contentSearchId) {
		return _journalContentSearchLocalService.createJournalContentSearch(contentSearchId);
	}

	/**
	* Deletes the journal content search from the database. Also notifies the appropriate model listeners.
	*
	* @param journalContentSearch the journal content search
	* @return the journal content search that was removed
	*/
	@Override
	public com.liferay.journal.model.JournalContentSearch deleteJournalContentSearch(
		com.liferay.journal.model.JournalContentSearch journalContentSearch) {
		return _journalContentSearchLocalService.deleteJournalContentSearch(journalContentSearch);
	}

	/**
	* Deletes the journal content search with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentSearchId the primary key of the journal content search
	* @return the journal content search that was removed
	* @throws PortalException if a journal content search with the primary key could not be found
	*/
	@Override
	public com.liferay.journal.model.JournalContentSearch deleteJournalContentSearch(
		long contentSearchId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalContentSearchLocalService.deleteJournalContentSearch(contentSearchId);
	}

	@Override
	public com.liferay.journal.model.JournalContentSearch fetchJournalContentSearch(
		long contentSearchId) {
		return _journalContentSearchLocalService.fetchJournalContentSearch(contentSearchId);
	}

	/**
	* Returns the journal content search with the primary key.
	*
	* @param contentSearchId the primary key of the journal content search
	* @return the journal content search
	* @throws PortalException if a journal content search with the primary key could not be found
	*/
	@Override
	public com.liferay.journal.model.JournalContentSearch getJournalContentSearch(
		long contentSearchId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalContentSearchLocalService.getJournalContentSearch(contentSearchId);
	}

	@Override
	public com.liferay.journal.model.JournalContentSearch updateContentSearch(
		long groupId, boolean privateLayout, long layoutId,
		java.lang.String portletId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalContentSearchLocalService.updateContentSearch(groupId,
			privateLayout, layoutId, portletId, articleId);
	}

	@Override
	public com.liferay.journal.model.JournalContentSearch updateContentSearch(
		long groupId, boolean privateLayout, long layoutId,
		java.lang.String portletId, java.lang.String articleId, boolean purge)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalContentSearchLocalService.updateContentSearch(groupId,
			privateLayout, layoutId, portletId, articleId, purge);
	}

	/**
	* Updates the journal content search in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param journalContentSearch the journal content search
	* @return the journal content search that was updated
	*/
	@Override
	public com.liferay.journal.model.JournalContentSearch updateJournalContentSearch(
		com.liferay.journal.model.JournalContentSearch journalContentSearch) {
		return _journalContentSearchLocalService.updateJournalContentSearch(journalContentSearch);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _journalContentSearchLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _journalContentSearchLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _journalContentSearchLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalContentSearchLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalContentSearchLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of journal content searchs.
	*
	* @return the number of journal content searchs
	*/
	@Override
	public int getJournalContentSearchsCount() {
		return _journalContentSearchLocalService.getJournalContentSearchsCount();
	}

	@Override
	public int getLayoutIdsCount(java.lang.String articleId) {
		return _journalContentSearchLocalService.getLayoutIdsCount(articleId);
	}

	@Override
	public int getLayoutIdsCount(long groupId, boolean privateLayout,
		java.lang.String articleId) {
		return _journalContentSearchLocalService.getLayoutIdsCount(groupId,
			privateLayout, articleId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _journalContentSearchLocalService.getOSGiServiceIdentifier();
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
		return _journalContentSearchLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.journal.model.impl.JournalContentSearchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _journalContentSearchLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.journal.model.impl.JournalContentSearchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _journalContentSearchLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalContentSearch> getArticleContentSearches() {
		return _journalContentSearchLocalService.getArticleContentSearches();
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalContentSearch> getArticleContentSearches(
		java.lang.String articleId) {
		return _journalContentSearchLocalService.getArticleContentSearches(articleId);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalContentSearch> getArticleContentSearches(
		long groupId, java.lang.String articleId) {
		return _journalContentSearchLocalService.getArticleContentSearches(groupId,
			articleId);
	}

	/**
	* Returns a range of all the journal content searchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.journal.model.impl.JournalContentSearchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @return the range of journal content searchs
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalContentSearch> getJournalContentSearchs(
		int start, int end) {
		return _journalContentSearchLocalService.getJournalContentSearchs(start,
			end);
	}

	@Override
	public java.util.List<java.lang.Long> getLayoutIds(long groupId,
		boolean privateLayout, java.lang.String articleId) {
		return _journalContentSearchLocalService.getLayoutIds(groupId,
			privateLayout, articleId);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalContentSearch> getPortletContentSearches(
		java.lang.String portletId) {
		return _journalContentSearchLocalService.getPortletContentSearches(portletId);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalContentSearch> updateContentSearch(
		long groupId, boolean privateLayout, long layoutId,
		java.lang.String portletId, java.lang.String[] articleIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalContentSearchLocalService.updateContentSearch(groupId,
			privateLayout, layoutId, portletId, articleIds);
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
		return _journalContentSearchLocalService.dynamicQueryCount(dynamicQuery);
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
		return _journalContentSearchLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void checkContentSearches(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalContentSearchLocalService.checkContentSearches(companyId);
	}

	@Override
	public void deleteArticleContentSearch(long groupId, boolean privateLayout,
		long layoutId, java.lang.String portletId) {
		_journalContentSearchLocalService.deleteArticleContentSearch(groupId,
			privateLayout, layoutId, portletId);
	}

	@Override
	public void deleteArticleContentSearch(long groupId, boolean privateLayout,
		long layoutId, java.lang.String portletId, java.lang.String articleId) {
		_journalContentSearchLocalService.deleteArticleContentSearch(groupId,
			privateLayout, layoutId, portletId, articleId);
	}

	@Override
	public void deleteArticleContentSearches(long groupId,
		java.lang.String articleId) {
		_journalContentSearchLocalService.deleteArticleContentSearches(groupId,
			articleId);
	}

	@Override
	public void deleteLayoutContentSearches(long groupId,
		boolean privateLayout, long layoutId) {
		_journalContentSearchLocalService.deleteLayoutContentSearches(groupId,
			privateLayout, layoutId);
	}

	@Override
	public void deleteOwnerContentSearches(long groupId, boolean privateLayout) {
		_journalContentSearchLocalService.deleteOwnerContentSearches(groupId,
			privateLayout);
	}

	@Override
	public JournalContentSearchLocalService getWrappedService() {
		return _journalContentSearchLocalService;
	}

	@Override
	public void setWrappedService(
		JournalContentSearchLocalService journalContentSearchLocalService) {
		_journalContentSearchLocalService = journalContentSearchLocalService;
	}

	private JournalContentSearchLocalService _journalContentSearchLocalService;
}