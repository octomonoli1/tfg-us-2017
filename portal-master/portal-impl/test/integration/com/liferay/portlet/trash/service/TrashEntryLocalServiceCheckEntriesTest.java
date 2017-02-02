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

package com.liferay.portlet.trash.service;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLTrashLocalServiceUtil;
import com.liferay.exportimport.kernel.service.StagingLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.test.randomizerbumpers.TikaSafeRandomizerBumper;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.test.LayoutTestUtil;
import com.liferay.trash.kernel.model.TrashEntry;
import com.liferay.trash.kernel.service.TrashEntryLocalServiceUtil;
import com.liferay.trash.kernel.util.TrashUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Sampsa Sohlman
 * @author Shuyang Zhou
 */
@Sync(cleanTransaction = true)
public class TrashEntryLocalServiceCheckEntriesTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		deleteTrashEntries();
	}

	@After
	public void tearDown() throws Exception {
		deleteTrashEntries();
	}

	@Test
	public void testCompanies() throws Exception {
		Long companyId = CompanyThreadLocal.getCompanyId();

		for (int i = 0; i < _COMPANIES_COUNT; i++) {
			long newCompanyId = createCompany();

			CompanyThreadLocal.setCompanyId(newCompanyId);

			Group group = updateTrashEntriesMaxAge(
				createGroup(newCompanyId), _MAX_AGE);

			createTrashEntries(group);
		}

		TrashEntryLocalServiceUtil.checkEntries();

		Assert.assertEquals(
			_COMPANIES_COUNT * _NOT_EXPIRED_TRASH_ENTRIES_COUNT,
			TrashEntryLocalServiceUtil.getTrashEntriesCount());

		CompanyThreadLocal.setCompanyId(companyId);
	}

	@Test
	public void testCustomMaxAge() throws Exception {
		Group group = updateTrashEntriesMaxAge(
			createGroup(TestPropsValues.getCompanyId()), 2);

		deleteTrashEntries(group);
	}

	@Test
	public void testGroups() throws Exception {
		for (int i = 0; i < _GROUPS_COUNT; i++) {
			Group group = updateTrashEntriesMaxAge(
				createGroup(TestPropsValues.getCompanyId()), _MAX_AGE);

			createTrashEntries(group);
		}

		TrashEntryLocalServiceUtil.checkEntries();

		Assert.assertEquals(
			_GROUPS_COUNT * _NOT_EXPIRED_TRASH_ENTRIES_COUNT,
			TrashEntryLocalServiceUtil.getTrashEntriesCount());
	}

	@Test
	public void testGroupTrashDisabled() throws Exception {
		Group group = createGroup(TestPropsValues.getCompanyId());

		createFileEntryTrashEntry(group, false);

		TrashUtil.disableTrash(group);

		TrashEntryLocalServiceUtil.checkEntries();

		Assert.assertEquals(
			0, TrashEntryLocalServiceUtil.getTrashEntriesCount());
	}

	@Test
	public void testLayoutGroup() throws Exception {
		Group group = updateTrashEntriesMaxAge(
			createGroup(TestPropsValues.getCompanyId()), 2);

		deleteTrashEntries(createLayoutGroup(group));
	}

	@Test
	public void testRegularGroup() throws Exception {
		deleteTrashEntries(createGroup(TestPropsValues.getCompanyId()));
	}

	@Test
	public void testStagingGroup() throws Exception {
		long companyId = TestPropsValues.getCompanyId();

		Group group = updateTrashEntriesMaxAge(createGroup(companyId), 2);
		User user = UserTestUtil.getAdminUser(companyId);

		StagingLocalServiceUtil.enableLocalStaging(
			user.getUserId(), group, false, false,
			ServiceContextTestUtil.getServiceContext(group, user.getUserId()));

		deleteTrashEntries(group.getStagingGroup());
	}

	@Test
	public void testStagingLayoutScope() throws Exception {
		long companyId = TestPropsValues.getCompanyId();

		Group group = updateTrashEntriesMaxAge(createGroup(companyId), 2);
		User user = UserTestUtil.getAdminUser(companyId);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group, user.getUserId());

		StagingLocalServiceUtil.enableLocalStaging(
			user.getUserId(), group, false, false, serviceContext);

		group = createLayoutGroup(group.getStagingGroup());

		deleteTrashEntries(group);
	}

	@Test
	public void testStagingTrashDisabled() throws Exception {
		long companyId = TestPropsValues.getCompanyId();

		Group group = TrashUtil.disableTrash(createGroup(companyId));
		User user = UserTestUtil.getAdminUser(companyId);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group, user.getUserId());

		StagingLocalServiceUtil.enableLocalStaging(
			user.getUserId(), group, false, false, serviceContext);

		Group stagingGroup = group.getStagingGroup();

		createFileEntryTrashEntry(stagingGroup, false);

		TrashEntryLocalServiceUtil.checkEntries();

		Assert.assertEquals(
			0, TrashEntryLocalServiceUtil.getTrashEntriesCount());
	}

	protected long createCompany() throws Exception {
		Company company = CompanyTestUtil.addCompany(
			RandomTestUtil.randomString());

		_companies.add(company);

		return company.getCompanyId();
	}

	protected void createFileEntryTrashEntry(Group group, boolean expired)
		throws Exception {

		User user = UserTestUtil.getAdminUser(group.getCompanyId());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), user.getUserId());

		FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
			user.getUserId(), group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomBytes(TikaSafeRandomizerBumper.INSTANCE),
			serviceContext);

		DLTrashLocalServiceUtil.moveFileEntryToTrash(
			user.getUserId(), fileEntry.getRepositoryId(),
			fileEntry.getFileEntryId());

		if (expired) {
			int maxAge = TrashUtil.getMaxAge(group);

			TrashEntry trashEntry = TrashEntryLocalServiceUtil.getEntry(
				DLFileEntry.class.getName(), fileEntry.getFileEntryId());

			Date createDate = trashEntry.getCreateDate();

			trashEntry.setCreateDate(
				new Date(
					createDate.getTime() - maxAge * Time.MINUTE - Time.DAY));

			TrashEntryLocalServiceUtil.updateTrashEntry(trashEntry);
		}
	}

	protected Group createGroup(long companyId) throws Exception {
		User user = UserTestUtil.getAdminUser(companyId);

		Group group = GroupTestUtil.addGroup(
			companyId, user.getUserId(),
			GroupConstants.DEFAULT_PARENT_GROUP_ID);

		_groups.add(group);

		return group;
	}

	protected Group createLayoutGroup(Group group) throws Exception {
		User user = UserTestUtil.getAdminUser(group.getCompanyId());
		Layout layout = LayoutTestUtil.addLayout(group);

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(LocaleUtil.getDefault(), String.valueOf(layout.getPlid()));

		return GroupLocalServiceUtil.addGroup(
			user.getUserId(), GroupConstants.DEFAULT_PARENT_GROUP_ID,
			Layout.class.getName(), layout.getPlid(),
			GroupConstants.DEFAULT_LIVE_GROUP_ID, nameMap,
			(Map<Locale, String>)null, 0, true,
			GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, null, false, true,
			null);
	}

	protected void createTrashEntries(Group group) throws Exception {
		for (int i = 0; i < _EXPIRED_TRASH_ENTRIES_COUNT; i++) {
			createFileEntryTrashEntry(group, true);
		}

		for (int i = 0; i < _NOT_EXPIRED_TRASH_ENTRIES_COUNT; i++) {
			createFileEntryTrashEntry(group, false);
		}
	}

	protected void deleteTrashEntries() {
		List<TrashEntry> trashEntries =
			TrashEntryLocalServiceUtil.getTrashEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (TrashEntry trashEntry : trashEntries) {
			TrashEntryLocalServiceUtil.deleteEntry(trashEntry);
		}
	}

	protected void deleteTrashEntries(Group group) throws Exception {
		createTrashEntries(group);

		TrashEntryLocalServiceUtil.checkEntries();

		Assert.assertEquals(
			_NOT_EXPIRED_TRASH_ENTRIES_COUNT,
			TrashEntryLocalServiceUtil.getTrashEntriesCount());
	}

	protected Group updateTrashEntriesMaxAge(Group group, int days)
		throws Exception {

		UnicodeProperties typeSettingsProperties =
			group.getParentLiveGroupTypeSettingsProperties();

		int companyTrashEntriesMaxAge = PrefsPropsUtil.getInteger(
			group.getCompanyId(), PropsKeys.TRASH_ENTRIES_MAX_AGE);

		if (days > 0) {
			days *= 1440;
		}
		else {
			days = GetterUtil.getInteger(
				typeSettingsProperties.getProperty("trashEntriesMaxAge"),
				companyTrashEntriesMaxAge);
		}

		if (days != companyTrashEntriesMaxAge) {
			typeSettingsProperties.setProperty(
				"trashEntriesMaxAge", String.valueOf(days));
		}
		else {
			typeSettingsProperties.remove("trashEntriesMaxAge");
		}

		group.setTypeSettingsProperties(typeSettingsProperties);

		return GroupLocalServiceUtil.updateGroup(group);
	}

	private static final int _COMPANIES_COUNT = 2;

	private static final int _EXPIRED_TRASH_ENTRIES_COUNT = 3;

	private static final int _GROUPS_COUNT = 2;

	private static final int _MAX_AGE = 5;

	private static final int _NOT_EXPIRED_TRASH_ENTRIES_COUNT = 4;

	@DeleteAfterTestRun
	private final List<Company> _companies = new ArrayList<>();

	@DeleteAfterTestRun
	private final List<Group> _groups = new ArrayList<>();

}