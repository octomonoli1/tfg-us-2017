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

import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.message.boards.kernel.model.MBCategory;
import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.model.WorkflowInstanceLink;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Eduardo Garcia
 * @author Roberto Díaz
 * @author Iván Zaera
 */
public class UpgradeSubscription extends UpgradeProcess {

	protected void addClassName(long classNameId, String className)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"insert into ClassName_ (mvccVersion, classNameId, value) " +
					"values (?, ?, ?)")) {

			ps.setLong(1, 0);
			ps.setLong(2, classNameId);
			ps.setString(3, className);

			ps.executeUpdate();
		}
	}

	protected void deleteOrphanedSubscriptions() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			long classNameId = PortalUtil.getClassNameId(
				PortletPreferences.class.getName());

			runSQL(
				"delete from Subscription where classNameId = " + classNameId +
					" and classPK not in (select portletPreferencesId from " +
						" PortletPreferences)");
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		deleteOrphanedSubscriptions();

		updateSubscriptionClassNames(
			Folder.class.getName(), DLFolder.class.getName());
		updateSubscriptionClassNames(
			"com.liferay.portlet.journal.model.JournalArticle",
			"com.liferay.portlet.journal.model.JournalFolder");

		updateSubscriptionGroupIds();
	}

	protected long getClassNameId(String className) throws Exception {
		long classNameId = PortalUtil.getClassNameId(className);

		if (classNameId != 0) {
			return classNameId;
		}

		classNameId = increment();

		addClassName(classNameId, className);

		return classNameId;
	}

	protected long getGroupId(long classNameId, long classPK) throws Exception {
		String className = PortalUtil.getClassName(classNameId);

		String[] groupIdSQLParts = StringUtil.split(
			_getGroupIdSQLPartsMap.get(className));

		if (ArrayUtil.isEmpty(groupIdSQLParts)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to determine the group ID for the class name " +
						className);
			}

			return 0;
		}

		String sql =
			"select " + groupIdSQLParts[1] + " from " + groupIdSQLParts[0] +
				" where " + groupIdSQLParts[2] + " = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, classPK);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getLong("groupId");
				}
			}
		}

		return 0;
	}

	protected boolean hasGroup(long groupId) throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select count(*) from Group_ where groupId = ?")) {

			ps.setLong(1, groupId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);

					if (count > 0) {
						return true;
					}
				}
			}

			return false;
		}
	}

	protected void updateSubscriptionClassNames(
			String oldClassName, String newClassName)
		throws Exception {

		try (LoggingTimer loggingTimer = new LoggingTimer(oldClassName)) {
			StringBundler sb = new StringBundler(4);

			sb.append("update Subscription set classNameId = ");
			sb.append(getClassNameId(newClassName));
			sb.append(" where classNameId = ");
			sb.append(PortalUtil.getClassNameId(oldClassName));

			runSQL(sb.toString());
		}
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	protected void updateSubscriptionGroupId(
			long subscriptionId, long classNameId, long classPK)
		throws Exception {
	}

	protected void updateSubscriptionGroupIds() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(
				"select subscriptionId, classNameId, classPK from " +
					"Subscription");
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update Subscription set groupId = ? where " +
						"subscriptionId = ?");
			ResultSet rs = ps1.executeQuery()) {

			while (rs.next()) {
				long subscriptionId = rs.getLong("subscriptionId");
				long classNameId = rs.getLong("classNameId");
				long classPK = rs.getLong("classPK");

				long groupId = getGroupId(classNameId, classPK);

				if ((groupId == 0) && hasGroup(classPK)) {
					groupId = classPK;
				}

				if (groupId != 0) {
					ps2.setLong(1, groupId);
					ps2.setLong(2, subscriptionId);

					ps2.addBatch();
				}
			}

			ps2.executeBatch();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeSubscription.class);

	private static final Map<String, String> _getGroupIdSQLPartsMap =
		new HashMap<>();

	static {
		_getGroupIdSQLPartsMap.put(
			BlogsEntry.class.getName(), "BlogsEntry,groupId,entryId");
		_getGroupIdSQLPartsMap.put(
			DLFileEntry.class.getName(), "DLFileEntry,groupId,fileEntryId");
		_getGroupIdSQLPartsMap.put(
			DLFileEntryType.class.getName(),
			"DLFileEntryType,groupId,fileEntryTypeId");
		_getGroupIdSQLPartsMap.put(
			DLFolder.class.getName(), "DLFolder,groupId,folderId");
		_getGroupIdSQLPartsMap.put(
			Layout.class.getName(), "Layout,groupId,plid");
		_getGroupIdSQLPartsMap.put(
			MBCategory.class.getName(), "MBCategory,groupId,categoryId");
		_getGroupIdSQLPartsMap.put(
			MBThread.class.getName(), "MBThread,groupId,threadId");
		_getGroupIdSQLPartsMap.put(
			WorkflowInstanceLink.class.getName(),
			"WorkflowInstanceLink,groupId,workflowInstanceId");
		_getGroupIdSQLPartsMap.put(
			"com.liferay.bookmarks.model.BookmarksEntry",
			"BookmarksEntry,groupId,entryId");
		_getGroupIdSQLPartsMap.put(
			"com.liferay.bookmarks.model.BookmarksFolder",
			"BookmarksFolder,groupId,folderId");
		_getGroupIdSQLPartsMap.put(
			"com.liferay.dynamic.data.mapping.kernel.DDMStructure",
			"DDMStructure,groupId,structureId");
		_getGroupIdSQLPartsMap.put(
			"com.liferay.journal.model.JournalFolder",
			"JournalFolder,groupId,folderId");
		_getGroupIdSQLPartsMap.put(
			"com.liferay.wiki.model.WikiNode", "WikiNode,groupId,nodeId");
		_getGroupIdSQLPartsMap.put(
			"com.liferay.wiki.model.WikiPage",
			"WikiPage,groupId,resourcePrimKey");
	}

}