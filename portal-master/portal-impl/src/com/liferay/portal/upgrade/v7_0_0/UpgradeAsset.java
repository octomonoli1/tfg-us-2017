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

import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.upgrade.v7_0_0.util.AssetEntryTable;
import com.liferay.portlet.asset.util.AssetVocabularySettingsHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Arrays;

/**
 * @author Gergely Mathe
 */
public class UpgradeAsset extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(
			AssetEntryTable.class,
			new AlterColumnType("description", "TEXT null"),
			new AlterColumnType("summary", "TEXT null"));

		updateAssetEntries();
		updateAssetVocabularies();
	}

	protected long getDDMStructureId(String structureKey) throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select structureId from DDMStructure where structureKey = " +
					"?")) {

			ps.setString(1, structureKey);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getLong("structureId");
				}

				return 0;
			}
		}
	}

	protected void updateAssetEntries() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			long classNameId = PortalUtil.getClassNameId(
				"com.liferay.portlet.journal.model.JournalArticle");

			StringBundler sb = new StringBundler(10);

			sb.append("select JournalArticle.resourcePrimKey as ");
			sb.append("resourcePrimKey from (select ");
			sb.append("JournalArticle.resourcePrimKey as primKey, ");
			sb.append("max(JournalArticle.version) as maxVersion from ");
			sb.append("JournalArticle group by ");
			sb.append("JournalArticle.resourcePrimKey) temp_table inner join ");
			sb.append("JournalArticle on (JournalArticle.indexable = ?) and ");
			sb.append("(JournalArticle.status = 0) and ");
			sb.append("(JournalArticle.resourcePrimKey = temp_table.primKey) ");
			sb.append("and (JournalArticle.version = temp_table.maxVersion)");

			try (PreparedStatement ps1 = connection.prepareStatement(
					sb.toString())) {

				ps1.setBoolean(1, false);

				try (PreparedStatement ps2 =
						AutoBatchPreparedStatementUtil.concurrentAutoBatch(
							connection,
							"update AssetEntry set listable = ? where " +
								"classNameId = ? and classPK = ?");
					ResultSet rs = ps1.executeQuery()) {

					while (rs.next()) {
						long classPK = rs.getLong("resourcePrimKey");

						ps2.setBoolean(1, false);
						ps2.setLong(2, classNameId);
						ps2.setLong(3, classPK);

						ps2.addBatch();
					}

					ps2.executeBatch();
				}
			}
		}
	}

	protected void updateAssetVocabularies() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(
				"select vocabularyId, settings_ from AssetVocabulary");
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update AssetVocabulary set settings_ = ? where " +
						"vocabularyId = ?");
			ResultSet rs = ps1.executeQuery()) {

			while (rs.next()) {
				long vocabularyId = rs.getLong("vocabularyId");
				String settings = rs.getString("settings_");

				ps2.setString(1, upgradeVocabularySettings(settings));
				ps2.setLong(2, vocabularyId);

				ps2.addBatch();
			}

			ps2.executeBatch();
		}
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	protected void updateAssetVocabulary(long vocabularyId, String settings)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"update AssetVocabulary set settings_ = ? where vocabularyId " +
					"= ?")) {

			ps.setString(1, settings);
			ps.setLong(2, vocabularyId);

			ps.executeUpdate();
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to update vocabulary " + vocabularyId, e);
			}
		}
	}

	protected String upgradeVocabularySettings(String settings) {
		UnicodeProperties properties = new UnicodeProperties(true);

		properties.fastLoad(settings);

		AssetVocabularySettingsHelper vocabularySettingsHelper =
			new AssetVocabularySettingsHelper();

		vocabularySettingsHelper.setMultiValued(
			GetterUtil.getBoolean(properties.getProperty("multiValued"), true));

		long[] classNameIds = StringUtil.split(
			properties.getProperty("selectedClassNameIds"), 0L);

		long[] classTypePKs = new long[classNameIds.length];

		Arrays.fill(classTypePKs, AssetCategoryConstants.ALL_CLASS_TYPE_PK);

		long[] requiredClassNameIds = StringUtil.split(
			properties.getProperty("requiredClassNameIds"), 0L);

		boolean[] requireds = new boolean[classNameIds.length];

		for (int i = 0; i < classNameIds.length; i++) {
			requireds[i] = ArrayUtil.contains(
				requiredClassNameIds, classNameIds[i]);
		}

		vocabularySettingsHelper.setClassNameIdsAndClassTypePKs(
			classNameIds, classTypePKs, requireds);

		return vocabularySettingsHelper.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(UpgradeAsset.class);

}