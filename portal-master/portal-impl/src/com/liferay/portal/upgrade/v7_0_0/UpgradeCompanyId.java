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

package com.liferay.portal.upgrade.v7_0_0;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Brian Wing Shun Chan
 */
@SuppressWarnings("deprecation")
public class UpgradeCompanyId
	extends com.liferay.portal.upgrade.util.UpgradeCompanyId {

	@Override
	protected TableUpdater[] getTableUpdaters() {
		return new TableUpdater[] {
			new TableUpdater("AnnouncementsFlag", "User_", "userId"),
			new TableUpdater(
				"AssetEntries_AssetCategories", "AssetCategory", "categoryId"),
			new TableUpdater("AssetEntries_AssetTags", "AssetTag", "tagId"),
			new TableUpdater("AssetTagStats", "AssetTag", "tagId"),
			new TableUpdater("BrowserTracker", "User_", "userId"),
			new TableUpdater(
				"DLFileEntryMetadata", "DLFileEntry", "fileEntryId"),
			new TableUpdater(
				"DLFileEntryTypes_DLFolders", "DLFolder", "folderId"),
			new DLSyncEventTableUpdater("DLSyncEvent"),
			new TableUpdater("Groups_Orgs", "Group_", "groupId"),
			new TableUpdater("Groups_Roles", "Group_", "groupId"),
			new TableUpdater("Groups_UserGroups", "Group_", "groupId"),
			new TableUpdater(
				"Image", "imageId",
				new String[][] {
					{"BlogsEntry", "smallImageId"}, {"Company", "logoId"},
					{"DDMTemplate", "smallImageId"},
					{"DLFileEntry", "largeImageId"},
					{"JournalArticle", "smallImageId"},
					{"Layout", "iconImageId"},
					{"LayoutRevision", "iconImageId"},
					{"LayoutSetBranch", "logoId"}, {"Organization_", "logoId"},
					{"User_", "portraitId"}
				}),
			new TableUpdater("MBStatsUser", "Group_", "groupId"),
			new TableUpdater("OrgGroupRole", "Organization_", "organizationId"),
			new TableUpdater("OrgLabor", "Organization_", "organizationId"),
			new TableUpdater(
				"PasswordPolicyRel", "PasswordPolicy", "passwordPolicyId"),
			new TableUpdater("PasswordTracker", "User_", "userId"),
			new PortletPreferencesTableUpdater("PortletPreferences"),
			new TableUpdater(
				"RatingsStats", "classPK",
				new String[][] {
					{"BookmarksEntry", "entryId"},
					{"BookmarksFolder", "folderId"}, {"BlogsEntry", "entryId"},
					{"DDLRecord", "recordId"}, {"DLFileEntry", "fileEntryId"},
					{"DLFolder", "folderId"},
					{"JournalArticle", "resourcePrimKey"},
					{"JournalFolder", "folderId"},
					{"MBDiscussion", "discussionId"},
					{"MBMessage", "messageId"}, {"WikiPage", "pageId"}
				}),
			new TableUpdater(
				"ResourceBlockPermission", "ResourceBlock", "resourceBlockId"),
			new TableUpdater("TrashVersion", "TrashEntry", "entryId"),
			new TableUpdater("UserGroupGroupRole", "UserGroup", "userGroupId"),
			new TableUpdater("UserGroupRole", "User_", "userId"),
			new TableUpdater("UserGroups_Teams", "UserGroup", "userGroupId"),
			new TableUpdater("UserIdMapper", "User_", "userId"),
			new TableUpdater("Users_Groups", "User_", "userId"),
			new TableUpdater("Users_Orgs", "User_", "userId"),
			new TableUpdater("Users_Roles", "User_", "userId"),
			new TableUpdater("Users_Teams", "User_", "userId"),
			new TableUpdater("Users_UserGroups", "User_", "userId"),
			new TableUpdater("UserTrackerPath", "UserTracker", "userTrackerId")
		};
	}

	protected class DLSyncEventTableUpdater extends TableUpdater {

		public DLSyncEventTableUpdater(String tableName) {
			super(tableName, "", "");
		}

		@Override
		public void update(Connection connection)
			throws IOException, SQLException {

			// DLFileEntry

			String selectSQL =
				"select companyId from DLFileEntry where DLSyncEvent.type_ = " +
					"'file' and DLFileEntry.fileEntryId = DLSyncEvent.typePK";

			runSQL(connection, getUpdateSQL(selectSQL));

			// DLFolder

			selectSQL =
				"select companyId from DLFolder where DLSyncEvent.type_ = " +
					"'folder' and DLFolder.folderId = DLSyncEvent.typePK";

			runSQL(connection, getUpdateSQL(selectSQL));
		}

	}

	protected class PortletPreferencesTableUpdater extends TableUpdater {

		public PortletPreferencesTableUpdater(String tableName) {
			super(tableName, "", "");
		}

		@Override
		public void update(Connection connection)
			throws IOException, SQLException {

			// Company

			String selectSQL =
				"select companyId from Company where Company.companyId = " +
					"PortletPreferences.ownerId";

			runSQL(connection, getUpdateSQL(selectSQL));

			// Group

			selectSQL =
				"select companyId from Group_ where Group_.groupId = " +
					"PortletPreferences.ownerId";

			runSQL(connection, getUpdateSQL(selectSQL));

			// Layout

			selectSQL =
				"select companyId from Layout where Layout.plid = " +
					"PortletPreferences.ownerId";

			runSQL(connection, getUpdateSQL(selectSQL));

			// Organization

			selectSQL =
				"select companyId from Organization_ where " +
					"Organization_.organizationId = PortletPreferences.ownerId";

			runSQL(connection, getUpdateSQL(selectSQL));

			// User_

			selectSQL =
				"select companyId from User_ where User_.userId = " +
					"PortletPreferences.ownerId";

			runSQL(connection, getUpdateSQL(selectSQL));
		}

	}

}