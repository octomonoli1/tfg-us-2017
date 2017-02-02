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

package com.liferay.portal.search.test;

import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ClassedModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.IdempotentRetryAssert;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.SearchContextTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowThreadLocal;
import com.liferay.portal.test.randomizerbumpers.BBCodeRandomizerBumper;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Eudaldo Alonso
 * @author Tibor Lipusz
 */
public abstract class BaseSearchTestCase {

	@Before
	public void setUp() throws Exception {
		group = GroupTestUtil.addGroup();
	}

	@Test
	public void testBaseModelUserPermissions() throws Exception {
		testUserPermissions(false, true);
	}

	@Test
	public void testLocalizedSearch() throws Exception {
		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		int initialBaseModelsSearchCount = 0;

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		Map<Locale, String> keywordsMap = new HashMap<>();

		keywordsMap.put(LocaleUtil.getDefault(), "entity title");
		keywordsMap.put(LocaleUtil.HUNGARY, "entitas neve");

		baseModel = addBaseModelWithWorkflow(
			parentBaseModel, true, keywordsMap, serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);

		searchContext.setAttribute(Field.TITLE, "nev");
		searchContext.setKeywords("nev");
		searchContext.setLocale(LocaleUtil.HUNGARY);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);
	}

	@Test
	public void testParentBaseModelUserPermissions() throws Exception {
		testUserPermissions(true, false);
	}

	@Test
	public void testSearchAttachments() throws Exception {
		searchAttachments();
	}

	@Test
	public void testSearchBaseModel() throws Exception {
		searchBaseModel();
	}

	@Test
	public void testSearchBaseModelWithDelete() throws Exception {
		searchBaseModelWithDelete();
	}

	@Test
	public void testSearchBaseModelWithTrash() throws Exception {
		searchBaseModelWithTrash();
	}

	@Test
	public void testSearchBaseModelWithUpdate() throws Exception {
		searchBaseModelWithUpdate();
	}

	@Test
	public void testSearchByDDMStructureField() throws Exception {
		searchByDDMStructureField();
	}

	@Test
	public void testSearchByKeywords() throws Exception {
		searchByKeywords();
	}

	@Test
	public void testSearchByKeywordsInsideParentBaseModel() throws Exception {
		searchByKeywordsInsideParentBaseModel();
	}

	@Test
	public void testSearchComments() throws Exception {
		searchComments();
	}

	@Test
	public void testSearchExpireAllVersions() throws Exception {
		searchExpireVersions(false);
	}

	@Test
	public void testSearchExpireLatestVersion() throws Exception {
		searchExpireVersions(true);
	}

	@Test
	public void testSearchMixedPhraseKeywords() throws Exception {
		searchByMixedPhraseKeywords();
	}

	@Test
	public void testSearchMyEntries() throws Exception {
		searchMyEntries();
	}

	@Test
	public void testSearchRecentEntries() throws Exception {
		searchRecentEntries();
	}

	@Test
	public void testSearchStatus() throws Exception {
		searchStatus();
	}

	@Test
	public void testSearchVersions() throws Exception {
		searchVersions();
	}

	@Test
	public void testSearchWithinDDMStructure() throws Exception {
		searchWithinDDMStructure();
	}

	protected void addAttachment(ClassedModel classedModel) throws Exception {
	}

	protected BaseModel<?> addBaseModel(
			BaseModel<?> parentBaseModel, boolean approved, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		boolean workflowEnabled = WorkflowThreadLocal.isEnabled();

		try {
			WorkflowThreadLocal.setEnabled(true);

			BaseModel<?> baseModel = addBaseModelWithWorkflow(
				parentBaseModel, approved, keywords, serviceContext);

			return baseModel;
		}
		finally {
			WorkflowThreadLocal.setEnabled(workflowEnabled);
		}
	}

	protected BaseModel<?> addBaseModelWithDDMStructure(
			BaseModel<?> parentBaseModel, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		return addBaseModel(parentBaseModel, true, keywords, serviceContext);
	}

	protected BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved,
			Map<Locale, String> keywordsMap, ServiceContext serviceContext)
		throws Exception {

		return addBaseModelWithWorkflow(
			parentBaseModel, approved, keywordsMap.get(LocaleUtil.getDefault()),
			serviceContext);
	}

	protected abstract BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved, String keywords,
			ServiceContext serviceContext)
		throws Exception;

	protected void addComment(
			ClassedModel classedModel, String body,
			ServiceContext serviceContext)
		throws Exception {

		User user = TestPropsValues.getUser();

		List<MBMessage> messages = MBMessageLocalServiceUtil.getMessages(
			getBaseModelClassName(), getBaseModelClassPK(classedModel),
			WorkflowConstants.STATUS_ANY);

		MBMessage message = messages.get(0);

		MBMessageLocalServiceUtil.addDiscussionMessage(
			user.getUserId(), user.getFullName(),
			serviceContext.getScopeGroupId(), getBaseModelClassName(),
			getBaseModelClassPK(classedModel), message.getThreadId(),
			message.getMessageId(), message.getSubject(), body, serviceContext);
	}

	protected void assertBaseModelsCount(
			final int expectedCount, final SearchContext searchContext)
		throws Exception {

		IdempotentRetryAssert.retryAssert(
			10, TimeUnit.SECONDS,
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					int actualCount = searchBaseModelsCount(searchContext);

					Assert.assertEquals(expectedCount, actualCount);

					return null;
				}

			});
	}

	protected void assertBaseModelsCount(
			int expectedCount, String keywords, SearchContext searchContext)
		throws Exception {

		searchContext.setKeywords(keywords);

		assertBaseModelsCount(expectedCount, searchContext);
	}

	protected void assertGroupEntriesCount(long expectedCount)
		throws Exception {

		assertGroupEntriesCount(expectedCount, 0);
	}

	protected void assertGroupEntriesCount(
			final long expectedCount, final long userId)
		throws Exception {

		IdempotentRetryAssert.retryAssert(
			3, TimeUnit.SECONDS,
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					long actualCount = searchGroupEntriesCount(
						group.getGroupId(), userId);

					Assert.assertEquals(expectedCount, actualCount);

					return null;
				}

			});
	}

	protected void assertGroupEntriesCount(long expectedCount, User user)
		throws Exception {

		assertGroupEntriesCount(expectedCount, user.getUserId());
	}

	protected void deleteBaseModel(BaseModel<?> baseModel) throws Exception {
		deleteBaseModel((Long)baseModel.getPrimaryKeyObj());
	}

	protected void deleteBaseModel(long primaryKey) throws Exception {
	}

	protected void expireBaseModelVersions(
			BaseModel<?> baseModel, boolean expireAllVersions,
			ServiceContext serviceContext)
		throws Exception {
	}

	protected abstract Class<?> getBaseModelClass();

	protected String getBaseModelClassName() {
		Class<?> clazz = getBaseModelClass();

		return clazz.getName();
	}

	protected Long getBaseModelClassPK(ClassedModel classedModel) {
		return (Long)classedModel.getPrimaryKeyObj();
	}

	protected String getDDMStructureFieldName() {
		return StringPool.BLANK;
	}

	protected BaseModel<?> getParentBaseModel(
			BaseModel<?> parentBaseModel, ServiceContext serviceContext)
		throws Exception {

		return parentBaseModel;
	}

	protected BaseModel<?> getParentBaseModel(
			Group group, ServiceContext serviceContext)
		throws Exception {

		return group;
	}

	protected String getParentBaseModelClassName() {
		return StringPool.BLANK;
	}

	protected abstract String getSearchKeywords();

	protected boolean isCheckBaseModelPermission() {
		return CHECK_BASE_MODEL_PERMISSION;
	}

	protected boolean isExpirableAllVersions() {
		return false;
	}

	protected void moveBaseModelToTrash(long primaryKey) throws Exception {
	}

	protected void moveParentBaseModelToTrash(long primaryKey)
		throws Exception {
	}

	protected void searchAttachments() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setIncludeAttachments(true);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		int initialBaseModelsSearchCount = 0;

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		baseModel = addBaseModel(
			parentBaseModel, true, RandomTestUtil.randomString(),
			serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);

		addAttachment(baseModel);

		assertBaseModelsCount(initialBaseModelsSearchCount + 2, searchContext);

		moveBaseModelToTrash((Long)baseModel.getPrimaryKeyObj());

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);
	}

	protected void searchBaseModel() throws Exception {
		searchBaseModel(0);
	}

	protected void searchBaseModel(int initialBaseModelsSearchCount)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		baseModel = addBaseModel(
			parentBaseModel, true, RandomTestUtil.randomString(),
			serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);
	}

	protected int searchBaseModelsCount(
			Class<?> clazz, long groupId, SearchContext searchContext)
		throws Exception {

		Indexer<?> indexer = IndexerRegistryUtil.getIndexer(clazz);

		searchContext.setGroupIds(new long[] {groupId});

		Hits results = indexer.search(searchContext);

		return results.getLength();
	}

	protected int searchBaseModelsCount(SearchContext searchContext)
		throws Exception {

		return searchBaseModelsCount(
			getBaseModelClass(), group.getGroupId(), searchContext);
	}

	protected void searchBaseModelWithDelete() throws Exception {
		searchBaseModelWithDelete(0);
	}

	protected void searchBaseModelWithDelete(int initialBaseModelsSearchCount)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords(), serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);

		deleteBaseModel(baseModel);

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);
	}

	protected void searchBaseModelWithTrash() throws Exception {
		searchBaseModelWithTrash(0);
	}

	protected void searchBaseModelWithTrash(int initialBaseModelsSearchCount)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords(), serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);

		moveBaseModelToTrash((Long)baseModel.getPrimaryKeyObj());

		searchContext.setKeywords(getSearchKeywords());

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);
	}

	protected void searchBaseModelWithUpdate() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		searchContext.setKeywords(getSearchKeywords());

		int initialBaseModelsSearchCount = 0;

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords(), serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);

		String updatedKeywords = RandomTestUtil.randomString(
			BBCodeRandomizerBumper.INSTANCE);

		baseModel = updateBaseModel(baseModel, updatedKeywords, serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		searchContext.setKeywords(updatedKeywords);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);
	}

	protected void searchByDDMStructureField() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		int initialBaseModelsSearchCount = 0;

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		baseModel = addBaseModelWithDDMStructure(
			parentBaseModel, getSearchKeywords(), serviceContext);

		searchContext.setAttribute(
			"ddmStructureFieldName", getDDMStructureFieldName());
		searchContext.setAttribute(
			"ddmStructureFieldValue", getSearchKeywords());

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);

		updateDDMStructure(serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);
	}

	protected void searchByKeywords() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setKeywords(getSearchKeywords());

		int initialBaseModelsSearchCount = 0;

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords(), serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);
	}

	protected void searchByKeywordsInsideParentBaseModel() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		baseModel = addBaseModel(
			null, true, getSearchKeywords(), serviceContext);

		BaseModel<?> parentBaseModel1 = getParentBaseModel(
			group, serviceContext);

		searchContext.setFolderIds(
			new long[] {(Long)parentBaseModel1.getPrimaryKeyObj()});
		searchContext.setKeywords(getSearchKeywords());

		int initialBaseModelsSearchCount = 0;

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		baseModel = addBaseModel(
			parentBaseModel1, true, getSearchKeywords(), serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);

		BaseModel<?> parentBaseModel2 = getParentBaseModel(
			parentBaseModel1, serviceContext);

		baseModel = addBaseModel(
			parentBaseModel2, true, getSearchKeywords(), serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount + 2, searchContext);
	}

	protected void searchByMixedPhraseKeywords() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		String keyword1 = getSearchKeywords() + 1;
		String keyword2 = getSearchKeywords() + 2;
		String keyword3 = getSearchKeywords() + 3;
		String keyword4 = getSearchKeywords() + 4;
		String keyword5 = getSearchKeywords() + 5;
		String keyword6 = getSearchKeywords() + 6;
		String keyword7 = getSearchKeywords() + 7;

		String combinedKeywords =
			keyword1 + " " + keyword2 + " " + keyword3 + " " + keyword4 + " " +
				keyword5 + " " + keyword6 + " " + keyword7;

		searchContext.setKeywords(combinedKeywords);

		int initialBaseModelsSearchCount = 0;

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		baseModel = addBaseModel(
			parentBaseModel, true, combinedKeywords, serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);

		searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setKeywords("\"" + keyword1 + " " + keyword2 + "\"");

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);

		searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setKeywords("\"" + keyword2 + " " + keyword1 + "\"");

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setKeywords("\"" + keyword2 + " " + keyword4 + "\"");

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setKeywords(
			keyword1 + " \"" + keyword2 + " " + keyword3 + "\"");

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);

		searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setKeywords(
			RandomTestUtil.randomString() + " \"" + keyword2 + " " + keyword3 +
				"\"" + " " + keyword5);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);

		searchContext.setKeywords(
			RandomTestUtil.randomString() + " \"" + keyword2 + " " + keyword5 +
				"\"" + " " + RandomTestUtil.randomString());

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);
	}

	protected void searchComments() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setIncludeDiscussions(true);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		int initialBaseModelsSearchCount = 0;

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		baseModel = addBaseModel(
			parentBaseModel, true, RandomTestUtil.randomString(),
			serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);

		addComment(baseModel, getSearchKeywords(), serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount + 2, searchContext);

		moveBaseModelToTrash((Long)baseModel.getPrimaryKeyObj());

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);
	}

	protected void searchExpireVersions(boolean expireAllVersions)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setKeywords(getSearchKeywords());

		int initialBaseModelsCount = 0;

		assertBaseModelsCount(initialBaseModelsCount, searchContext);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords(), serviceContext);

		assertBaseModelsCount(initialBaseModelsCount + 1, searchContext);

		baseModel = updateBaseModel(baseModel, "liferay", serviceContext);

		assertBaseModelsCount(initialBaseModelsCount, searchContext);

		expireBaseModelVersions(baseModel, expireAllVersions, serviceContext);

		if (expireAllVersions && isExpirableAllVersions()) {
			assertBaseModelsCount(initialBaseModelsCount, searchContext);
		}
		else {
			assertBaseModelsCount(initialBaseModelsCount + 1, searchContext);
		}
	}

	protected long searchGroupEntriesCount(long groupId, long userId)
		throws Exception {

		return -1;
	}

	protected void searchMyEntries() throws Exception {
		User user1 = UserTestUtil.addUser(null, 0);

		long initialUser1SearchGroupEntriesCount = 0;

		assertGroupEntriesCount(initialUser1SearchGroupEntriesCount, user1);

		User user2 = UserTestUtil.addUser(null, 0);

		long initialUser2SearchGroupEntriesCount = 0;

		assertGroupEntriesCount(initialUser2SearchGroupEntriesCount, user2);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		BaseModel<?> parentBaseModel1 = getParentBaseModel(
			group, serviceContext);
		BaseModel<?> parentBaseModel2 = getParentBaseModel(
			group, serviceContext);

		String name = PrincipalThreadLocal.getName();

		long userId = serviceContext.getUserId();

		try {
			PrincipalThreadLocal.setName(user1.getUserId());

			serviceContext.setUserId(user1.getUserId());

			baseModel = addBaseModel(
				parentBaseModel1, true, RandomTestUtil.randomString(),
				serviceContext);
			baseModel = addBaseModel(
				parentBaseModel1, true, RandomTestUtil.randomString(),
				serviceContext);
			baseModel = addBaseModel(
				parentBaseModel2, true, RandomTestUtil.randomString(),
				serviceContext);

			PrincipalThreadLocal.setName(user2.getUserId());

			serviceContext.setUserId(user2.getUserId());

			baseModel = addBaseModel(
				parentBaseModel1, true, RandomTestUtil.randomString(),
				serviceContext);
			baseModel = addBaseModel(
				parentBaseModel2, true, RandomTestUtil.randomString(),
				serviceContext);
		}
		finally {
			PrincipalThreadLocal.setName(name);

			serviceContext.setUserId(userId);
		}

		assertGroupEntriesCount(initialUser1SearchGroupEntriesCount + 3, user1);
		assertGroupEntriesCount(initialUser2SearchGroupEntriesCount + 2, user2);

		moveParentBaseModelToTrash((Long)parentBaseModel2.getPrimaryKeyObj());

		assertGroupEntriesCount(initialUser1SearchGroupEntriesCount + 2, user1);
		assertGroupEntriesCount(initialUser2SearchGroupEntriesCount + 1, user2);

		TrashHandler parentTrashHandler =
			TrashHandlerRegistryUtil.getTrashHandler(
				getParentBaseModelClassName());

		parentTrashHandler.restoreTrashEntry(
			user1.getUserId(), (Long)parentBaseModel2.getPrimaryKeyObj());

		assertGroupEntriesCount(initialUser1SearchGroupEntriesCount + 3, user1);
		assertGroupEntriesCount(initialUser2SearchGroupEntriesCount + 2, user2);
	}

	protected void searchRecentEntries() throws Exception {
		long initialSearchGroupEntriesCount = 0;

		assertGroupEntriesCount(initialSearchGroupEntriesCount);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		BaseModel<?> parentBaseModel1 = getParentBaseModel(
			group, serviceContext);
		BaseModel<?> parentBaseModel2 = getParentBaseModel(
			group, serviceContext);

		String name = PrincipalThreadLocal.getName();

		try {
			User user1 = UserTestUtil.addUser(null, 0);

			PrincipalThreadLocal.setName(user1.getUserId());

			baseModel = addBaseModel(
				parentBaseModel1, true, RandomTestUtil.randomString(),
				serviceContext);
			baseModel = addBaseModel(
				parentBaseModel1, true, RandomTestUtil.randomString(),
				serviceContext);
			baseModel = addBaseModel(
				parentBaseModel2, true, RandomTestUtil.randomString(),
				serviceContext);

			User user2 = UserTestUtil.addUser(null, 0);

			PrincipalThreadLocal.setName(user2.getUserId());

			baseModel = addBaseModel(
				parentBaseModel1, true, RandomTestUtil.randomString(),
				serviceContext);
			baseModel = addBaseModel(
				parentBaseModel2, true, RandomTestUtil.randomString(),
				serviceContext);
		}
		finally {
			PrincipalThreadLocal.setName(name);
		}

		assertGroupEntriesCount(initialSearchGroupEntriesCount + 5);

		moveParentBaseModelToTrash((Long)parentBaseModel2.getPrimaryKeyObj());

		assertGroupEntriesCount(initialSearchGroupEntriesCount + 3);

		TrashHandler parentTrashHandler =
			TrashHandlerRegistryUtil.getTrashHandler(
				getParentBaseModelClassName());

		parentTrashHandler.restoreTrashEntry(
			TestPropsValues.getUserId(),
			(Long)parentBaseModel2.getPrimaryKeyObj());

		assertGroupEntriesCount(initialSearchGroupEntriesCount + 5);
	}

	protected void searchStatus() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		int initialBaseModelsCount = 0;

		assertBaseModelsCount(initialBaseModelsCount, "1.0", searchContext);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		baseModel = addBaseModel(
			parentBaseModel, false, "Version 1.0", serviceContext);

		assertBaseModelsCount(initialBaseModelsCount, searchContext);

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		baseModel = updateBaseModel(baseModel, "Version 1.1", serviceContext);

		assertBaseModelsCount(initialBaseModelsCount, "1.0", searchContext);
		assertBaseModelsCount(initialBaseModelsCount + 1, "1.1", searchContext);

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		baseModel = updateBaseModel(baseModel, "Version 1.2", serviceContext);

		assertBaseModelsCount(initialBaseModelsCount + 1, "1.1", searchContext);
		assertBaseModelsCount(initialBaseModelsCount, "1.2", searchContext);

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		baseModel = updateBaseModel(baseModel, "Version 1.3", serviceContext);

		assertBaseModelsCount(initialBaseModelsCount, "1.2", searchContext);
		assertBaseModelsCount(initialBaseModelsCount + 1, "1.3", searchContext);
	}

	protected void searchVersions() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setKeywords(getSearchKeywords());

		int initialBaseModelsCount = 0;

		assertBaseModelsCount(initialBaseModelsCount, searchContext);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords(), serviceContext);

		assertBaseModelsCount(initialBaseModelsCount + 1, searchContext);

		baseModel = updateBaseModel(baseModel, "liferay", serviceContext);

		assertBaseModelsCount(initialBaseModelsCount, searchContext);

		baseModel = updateBaseModel(baseModel, "portal", serviceContext);

		searchContext.setKeywords("portal");

		assertBaseModelsCount(initialBaseModelsCount + 1, searchContext);
	}

	protected void searchWithinDDMStructure() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setKeywords(getSearchKeywords());

		int initialBaseModelsSearchCount = 0;

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		baseModel = addBaseModelWithDDMStructure(
			parentBaseModel, getSearchKeywords(), serviceContext);

		assertBaseModelsCount(initialBaseModelsSearchCount + 1, searchContext);
	}

	protected void testUserPermissions(
			boolean addBaseModelPermission,
			boolean addParentBaseModelPermission)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setKeywords(getSearchKeywords());

		int initialBaseModelsSearchCount = 0;

		assertBaseModelsCount(initialBaseModelsSearchCount, searchContext);

		serviceContext.setAddGroupPermissions(addParentBaseModelPermission);
		serviceContext.setAddGuestPermissions(addParentBaseModelPermission);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		serviceContext.setAddGroupPermissions(addBaseModelPermission);
		serviceContext.setAddGuestPermissions(addBaseModelPermission);

		baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords(), serviceContext);

		User user = UserTestUtil.addUser(null, 0);

		PermissionChecker originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			searchContext.setUserId(user.getUserId());

			int baseModelsCount = initialBaseModelsSearchCount;

			if (addBaseModelPermission && !isCheckBaseModelPermission()) {
				baseModelsCount++;
			}

			assertBaseModelsCount(baseModelsCount, searchContext);
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(
				originalPermissionChecker);
		}
	}

	protected BaseModel<?> updateBaseModel(
			BaseModel<?> baseModel, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		return baseModel;
	}

	protected void updateDDMStructure(ServiceContext serviceContext)
		throws Exception {
	}

	protected final boolean CHECK_BASE_MODEL_PERMISSION = true;

	protected BaseModel<?> baseModel;

	@DeleteAfterTestRun
	protected Group group;

}