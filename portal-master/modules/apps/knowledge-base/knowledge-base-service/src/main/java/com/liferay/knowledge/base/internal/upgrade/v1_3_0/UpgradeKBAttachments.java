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

package com.liferay.knowledge.base.internal.upgrade.v1_3_0;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.upgrade.v6_2_0.BaseUpgradeAttachments;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Sergio González
 */
public class UpgradeKBAttachments extends BaseUpgradeAttachments {

	protected void deleteEmptyDirectories() throws Exception {
		for (long companyId : PortalUtil.getCompanyIds()) {
			DLStoreUtil.deleteDirectory(
				companyId, CompanyConstants.SYSTEM, "knowledgebase/kbarticles");
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateAttachments();
		deleteEmptyDirectories();
	}

	@Override
	protected String getClassName() {
		return "com.liferay.knowledgebase.model.KBArticle";
	}

	@Override
	protected long getContainerModelFolderId(
			long groupId, long companyId, long resourcePrimKey,
			long containerId, long userId, String userName,
			Timestamp createDate)
		throws Exception {

		long repositoryId = getRepositoryId(
			groupId, companyId, userId, userName, createDate, getClassNameId(),
			getPortletId());

		long repositoryFolderId = getFolderId(
			groupId, companyId, userId, userName, createDate, repositoryId,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, getPortletId(), false);

		long kbArticleFolderId = getFolderId(
			groupId, companyId, userId, userName, createDate, repositoryId,
			repositoryFolderId, String.valueOf(resourcePrimKey), false);

		return kbArticleFolderId;
	}

	@Override
	protected String getDirName(long containerModelId, long resourcePrimKey) {
		return "knowledgebase/kbarticles/" + resourcePrimKey;
	}

	@Override
	protected String getPortletId() {
		return "3_WAR_knowledgebaseportlet";
	}

	@Override
	protected void updateAttachments() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			StringBundler sb = new StringBundler(5);

			sb.append("select MIN(kbArticleId) as kbArticleId, ");
			sb.append("resourcePrimKey, groupId, companyId, ");
			sb.append("MIN(userId) as userId, MIN(userName) as userName, ");
			sb.append("MIN(status) as status from KBArticle ");
			sb.append("group by resourcePrimKey, groupId, companyId");

			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long kbArticleId = rs.getLong("kbArticleId");
				long resourcePrimKey = rs.getLong("resourcePrimKey");
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				int status = rs.getInt("status");

				long classPK = resourcePrimKey;

				if (status != WorkflowConstants.STATUS_APPROVED) {
					classPK = kbArticleId;
				}

				updateEntryAttachments(
					companyId, groupId, classPK, 0, userId, userName);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

}