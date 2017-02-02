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

package com.liferay.portal.search.internal;

import com.liferay.portal.background.task.constants.BackgroundTaskContextMapConstants;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocal;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.search.SearchEngineHelper;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.SearchPermissionChecker;
import com.liferay.portal.kernel.search.background.task.ReindexBackgroundTaskConstants;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.configuration.IndexWriterHelperConfiguration;
import com.liferay.portal.search.index.IndexStatusManager;
import com.liferay.portal.search.internal.background.task.ReindexPortalBackgroundTaskExecutor;
import com.liferay.portal.search.internal.background.task.ReindexSingleIndexerBackgroundTaskExecutor;

import java.io.Serializable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.search.configuration.IndexWriterHelperConfiguration",
	immediate = true, service = IndexWriterHelper.class
)
public class IndexWriterHelperImpl implements IndexWriterHelper {

	@Override
	public void addDocument(
			String searchEngineId, long companyId, Document document,
			boolean commitImmediately)
		throws SearchException {

		if (isIndexReadOnly() || (document == null)) {
			return;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Add document " + document.toString());
		}

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		_searchPermissionChecker.addPermissionFields(companyId, document);

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);

		setCommitImmediately(searchContext, commitImmediately);

		indexWriter.addDocument(searchContext, document);
	}

	@Override
	public void addDocuments(
			String searchEngineId, long companyId,
			Collection<Document> documents, boolean commitImmediately)
		throws SearchException {

		if (isIndexReadOnly() || (documents == null) || documents.isEmpty()) {
			return;
		}

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		for (Document document : documents) {
			if (_log.isDebugEnabled()) {
				_log.debug("Add document " + document.toString());
			}

			_searchPermissionChecker.addPermissionFields(companyId, document);
		}

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);

		setCommitImmediately(searchContext, commitImmediately);

		indexWriter.addDocuments(searchContext, documents);
	}

	@Override
	public void commit(String searchEngineId) throws SearchException {
		for (long companyId : _searchEngineHelper.getCompanyIds()) {
			commit(searchEngineId, companyId);
		}
	}

	@Override
	public void commit(String searchEngineId, long companyId)
		throws SearchException {

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);

		indexWriter.commit(searchContext);
	}

	@Override
	public void deleteDocument(
			String searchEngineId, long companyId, String uid,
			boolean commitImmediately)
		throws SearchException {

		if (isIndexReadOnly()) {
			return;
		}

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);

		setCommitImmediately(searchContext, commitImmediately);

		indexWriter.deleteDocument(searchContext, uid);
	}

	@Override
	public void deleteDocuments(
			String searchEngineId, long companyId, Collection<String> uids,
			boolean commitImmediately)
		throws SearchException {

		if (isIndexReadOnly() || (uids == null) || uids.isEmpty()) {
			return;
		}

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);

		setCommitImmediately(searchContext, commitImmediately);

		indexWriter.deleteDocuments(searchContext, uids);
	}

	@Override
	public void deleteEntityDocuments(
			String searchEngineId, long companyId, String className,
			boolean commitImmediately)
		throws SearchException {

		if (isIndexReadOnly()) {
			return;
		}

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		if (searchEngine == null) {
			return;
		}

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);

		setCommitImmediately(searchContext, commitImmediately);

		indexWriter.deleteEntityDocuments(searchContext, className);
	}

	@Override
	public int getReindexTaskCount(long groupId, boolean completed)
		throws SearchException {

		return _backgroundTaskManager.getBackgroundTasksCount(
			groupId,
			new String[] {
				ReindexPortalBackgroundTaskExecutor.class.getName(),
				ReindexSingleIndexerBackgroundTaskExecutor.class.getName()
			},
			completed);
	}

	@Override
	public void indexKeyword(
			long companyId, String querySuggestion, float weight,
			String keywordType, Locale locale)
		throws SearchException {

		String searchEngineId = _searchEngineHelper.getDefaultSearchEngineId();

		indexKeyword(
			searchEngineId, companyId, querySuggestion, weight, keywordType,
			locale);
	}

	@Override
	public void indexKeyword(
			String searchEngineId, long companyId, String querySuggestion,
			float weight, String keywordType, Locale locale)
		throws SearchException {

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);
		searchContext.setKeywords(querySuggestion);
		searchContext.setLocale(locale);

		indexWriter.indexKeyword(searchContext, weight, keywordType);
	}

	@Override
	public void indexQuerySuggestionDictionaries(long companyId)
		throws SearchException {

		Set<String> searchEngineIds = _searchEngineHelper.getSearchEngineIds();

		for (String searchEngineId : searchEngineIds) {
			indexQuerySuggestionDictionaries(searchEngineId, companyId);
		}
	}

	@Override
	public void indexQuerySuggestionDictionaries(
			String searchEngineId, long companyId)
		throws SearchException {

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);

		indexWriter.indexQuerySuggestionDictionaries(searchContext);
	}

	@Override
	public void indexQuerySuggestionDictionary(long companyId, Locale locale)
		throws SearchException {

		String searchEngineId = _searchEngineHelper.getDefaultSearchEngineId();

		indexQuerySuggestionDictionary(searchEngineId, companyId, locale);
	}

	@Override
	public void indexQuerySuggestionDictionary(
			String searchEngineId, long companyId, Locale locale)
		throws SearchException {

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);
		searchContext.setLocale(locale);

		indexWriter.indexQuerySuggestionDictionary(searchContext);
	}

	@Override
	public void indexSpellCheckerDictionaries(long companyId)
		throws SearchException {

		String searchEngineId = _searchEngineHelper.getDefaultSearchEngineId();

		indexSpellCheckerDictionaries(searchEngineId, companyId);
	}

	@Override
	public void indexSpellCheckerDictionaries(
			String searchEngineId, long companyId)
		throws SearchException {

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);

		indexWriter.indexSpellCheckerDictionaries(searchContext);
	}

	@Override
	public void indexSpellCheckerDictionary(long companyId, Locale locale)
		throws SearchException {

		String searchEngineId = _searchEngineHelper.getDefaultSearchEngineId();

		indexSpellCheckerDictionary(searchEngineId, companyId, locale);
	}

	@Override
	public void indexSpellCheckerDictionary(
			String searchEngineId, long companyId, Locale locale)
		throws SearchException {

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);
		searchContext.setLocale(locale);

		indexWriter.indexSpellCheckerDictionary(searchContext);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             IndexStatusManager#
	 *             isIndexReadOnly}
	 */
	@Deprecated
	@Override
	public boolean isIndexReadOnly() {
		return _indexStatusManager.isIndexReadOnly();
	}

	@Override
	public void partiallyUpdateDocument(
			String searchEngineId, long companyId, Document document,
			boolean commitImmediately)
		throws SearchException {

		if (isIndexReadOnly() || (document == null)) {
			return;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Document " + document.toString());
		}

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		_searchPermissionChecker.addPermissionFields(companyId, document);

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);

		setCommitImmediately(searchContext, commitImmediately);

		indexWriter.partiallyUpdateDocument(searchContext, document);
	}

	@Override
	public void partiallyUpdateDocuments(
			String searchEngineId, long companyId,
			Collection<Document> documents, boolean commitImmediately)
		throws SearchException {

		if (isIndexReadOnly() || (documents == null) || documents.isEmpty()) {
			return;
		}

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		for (Document document : documents) {
			if (_log.isDebugEnabled()) {
				_log.debug("Document " + document.toString());
			}

			_searchPermissionChecker.addPermissionFields(companyId, document);
		}

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);

		setCommitImmediately(searchContext, commitImmediately);

		indexWriter.partiallyUpdateDocuments(searchContext, documents);
	}

	@Override
	public BackgroundTask reindex(
			long userId, String jobName, long[] companyIds,
			Map<String, Serializable> taskContextMap)
		throws SearchException {

		if (taskContextMap == null) {
			taskContextMap = new HashMap<>();
		}

		taskContextMap.put(
			ReindexBackgroundTaskConstants.COMPANY_IDS, companyIds);
		taskContextMap.put(
			BackgroundTaskContextMapConstants.DELETE_ON_SUCCESS, true);

		try {
			return _backgroundTaskManager.addBackgroundTask(
				userId, CompanyConstants.SYSTEM, jobName,
				ReindexPortalBackgroundTaskExecutor.class.getName(),
				taskContextMap, new ServiceContext());
		}
		catch (PortalException pe) {
			throw new SearchException("Unable to schedule portal reindex", pe);
		}
	}

	@Override
	public BackgroundTask reindex(
			long userId, String jobName, long[] companyIds, String className,
			Map<String, Serializable> taskContextMap)
		throws SearchException {

		if (Validator.isNull(className)) {
			return reindex(userId, jobName, companyIds, taskContextMap);
		}

		if (taskContextMap == null) {
			taskContextMap = new HashMap<>();
		}

		taskContextMap.put(
			ReindexBackgroundTaskConstants.CLASS_NAME, className);
		taskContextMap.put(
			ReindexBackgroundTaskConstants.COMPANY_IDS, companyIds);
		taskContextMap.put(
			BackgroundTaskContextMapConstants.DELETE_ON_SUCCESS, true);

		try {
			return _backgroundTaskManager.addBackgroundTask(
				userId, CompanyConstants.SYSTEM, jobName,
				ReindexSingleIndexerBackgroundTaskExecutor.class.getName(),
				taskContextMap, new ServiceContext());
		}
		catch (PortalException pe) {
			throw new SearchException("Unable to schedule portal reindex", pe);
		}
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             IndexStatusManager.
	 *             setIndexReadOnly(boolean)}
	 */
	@Deprecated
	@Override
	public void setIndexReadOnly(boolean indexReadOnly) {
		_indexStatusManager.setIndexReadOnly(indexReadOnly);
	}

	@Override
	public void updateDocument(
			String searchEngineId, long companyId, Document document,
			boolean commitImmediately)
		throws SearchException {

		if (isIndexReadOnly() || (document == null)) {
			return;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Document " + document.toString());
		}

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		_searchPermissionChecker.addPermissionFields(companyId, document);

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);

		setCommitImmediately(
			searchContext,
			commitImmediately || ProxyModeThreadLocal.isForceSync());

		indexWriter.updateDocument(searchContext, document);
	}

	@Override
	public void updateDocuments(
			String searchEngineId, long companyId,
			Collection<Document> documents, boolean commitImmediately)
		throws SearchException {

		if (isIndexReadOnly() || (documents == null) || documents.isEmpty()) {
			return;
		}

		SearchEngine searchEngine = _searchEngineHelper.getSearchEngine(
			searchEngineId);

		IndexWriter indexWriter = searchEngine.getIndexWriter();

		for (Document document : documents) {
			if (_log.isDebugEnabled()) {
				_log.debug("Document " + document.toString());
			}

			_searchPermissionChecker.addPermissionFields(companyId, document);
		}

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setSearchEngineId(searchEngineId);

		setCommitImmediately(searchContext, commitImmediately);

		indexWriter.updateDocuments(searchContext, documents);
	}

	@Override
	public void updatePermissionFields(String name, String primKey) {
		if (isIndexReadOnly()) {
			return;
		}

		if (PermissionThreadLocal.isFlushResourcePermissionEnabled(
				name, primKey)) {

			_searchPermissionChecker.updatePermissionFields(name, primKey);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		IndexWriterHelperConfiguration indexWriterHelperConfiguration =
			ConfigurableUtil.createConfigurable(
				IndexWriterHelperConfiguration.class, properties);

		_commitImmediately =
			indexWriterHelperConfiguration.indexCommitImmediately();
	}

	protected void setCommitImmediately(
		SearchContext searchContext, boolean commitImmediately) {

		if (!commitImmediately) {
			searchContext.setCommitImmediately(_commitImmediately);
		}
		else {
			searchContext.setCommitImmediately(true);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		IndexWriterHelperImpl.class);

	@Reference
	private BackgroundTaskManager _backgroundTaskManager;

	private volatile boolean _commitImmediately;

	@Reference
	private IndexStatusManager _indexStatusManager;

	@Reference
	private SearchEngineHelper _searchEngineHelper;

	@Reference
	private SearchPermissionChecker _searchPermissionChecker;

}