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

package com.liferay.portlet.documentlibrary.service;

import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileShortcutLocalServiceUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.test.randomizerbumpers.TikaSafeRandomizerBumper;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class DLFileShortcutLocalServiceTreeTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testFileShortcutTreePathWhenMovingSubfolderWithFileShortcut()
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		Folder folderA = DLAppServiceUtil.addFolder(
			_group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			"Folder A", RandomTestUtil.randomString(), serviceContext);

		Folder folderAA = DLAppServiceUtil.addFolder(
			_group.getGroupId(), folderA.getFolderId(), "Folder AA",
			RandomTestUtil.randomString(), serviceContext);

		FileEntry fileEntry = addFileEntry(folderA.getFolderId(), "Entry.txt");

		FileShortcut fileShortcut = addFileShortcut(
			fileEntry, TestPropsValues.getGroupId(), folderAA.getFolderId());

		DLAppLocalServiceUtil.moveFolder(
			TestPropsValues.getUserId(), folderAA.getFolderId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, serviceContext);

		DLFileShortcut dlFileShortcut =
			DLFileShortcutLocalServiceUtil.getDLFileShortcut(
				fileShortcut.getFileShortcutId());

		Assert.assertEquals(
			dlFileShortcut.buildTreePath(), dlFileShortcut.getTreePath());
	}

	@Test
	public void testRebuildTree() throws Exception {
		createTree();

		for (FileShortcut fileShortcut : _fileShortcuts) {
			DLFileShortcut dlFileShortcut =
				DLFileShortcutLocalServiceUtil.getDLFileShortcut(
					fileShortcut.getFileShortcutId());

			dlFileShortcut.setTreePath(null);

			DLFileShortcutLocalServiceUtil.updateDLFileShortcut(dlFileShortcut);
		}

		DLFileShortcutLocalServiceUtil.rebuildTree(
			TestPropsValues.getCompanyId());

		for (FileShortcut fileShortcut : _fileShortcuts) {
			DLFileShortcut dlFileShortcut =
				DLFileShortcutLocalServiceUtil.getDLFileShortcut(
					fileShortcut.getFileShortcutId());

			Assert.assertEquals(
				dlFileShortcut.buildTreePath(), dlFileShortcut.getTreePath());
		}
	}

	protected FileEntry addFileEntry(long folderId, String sourceFileName)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		return DLAppLocalServiceUtil.addFileEntry(
			TestPropsValues.getUserId(), _group.getGroupId(), folderId,
			sourceFileName, ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomBytes(TikaSafeRandomizerBumper.INSTANCE),
			serviceContext);
	}

	protected FileShortcut addFileShortcut(
			FileEntry fileEntry, long groupId, long folderId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				groupId, TestPropsValues.getUserId());

		return DLAppLocalServiceUtil.addFileShortcut(
			TestPropsValues.getUserId(), groupId, folderId,
			fileEntry.getFileEntryId(), serviceContext);
	}

	protected void createTree() throws Exception {
		_fileEntry = addFileEntry(
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Entry A.txt");

		FileShortcut fileShortcutA = addFileShortcut(
			_fileEntry, TestPropsValues.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		_fileShortcuts.add(fileShortcutA);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		_folder = DLAppServiceUtil.addFolder(
			_group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			"Folder A", RandomTestUtil.randomString(), serviceContext);

		FileShortcut fileShortcutAA = addFileShortcut(
			_fileEntry, TestPropsValues.getGroupId(), _folder.getFolderId());

		_fileShortcuts.add(fileShortcutAA);
	}

	private FileEntry _fileEntry;
	private final List<FileShortcut> _fileShortcuts = new ArrayList<>();
	private Folder _folder;

	@DeleteAfterTestRun
	private Group _group;

}