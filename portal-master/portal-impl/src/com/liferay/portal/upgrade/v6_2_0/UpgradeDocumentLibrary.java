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

package com.liferay.portal.upgrade.v6_2_0;

import com.liferay.document.library.kernel.model.DLFileEntryTypeConstants;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.portal.kernel.security.auth.FullNameGenerator;
import com.liferay.portal.kernel.security.auth.FullNameGeneratorFactory;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeProcessUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.upgrade.v6_2_0.util.DLFileEntryTypeTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Dennis Ju
 * @author Mate Thurzo
 * @author Alexander Chow
 * @author Roberto Díaz
 */
public class UpgradeDocumentLibrary extends UpgradeProcess {

	protected void deleteChecksumDirectory() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"select distinct companyId from DLFileEntry");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long companyId = rs.getLong("companyId");

				DLStoreUtil.deleteDirectory(companyId, 0, "checksum");
			}
		}
	}

	protected void deleteTempDirectory() {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			DLStoreUtil.deleteDirectory(0, 0, "liferay_temp/");
		}
	}

	@Override
	protected void doUpgrade() throws Exception {

		// DLFileEntryType

		alter(
			DLFileEntryTypeTable.class,
			new AlterTableAddColumn("fileEntryTypeKey STRING"),
			new AlterColumnType("name", "STRING null"));

		updateFileEntryTypes();

		// Checksum directory

		deleteChecksumDirectory();

		// Temp directory

		deleteTempDirectory();
	}

	protected String getUserName(long userId) throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select firstName, middleName, lastName from User_ where " +
					"userId = ?")) {

			ps.setLong(1, userId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String firstName = rs.getString("firstName");
					String middleName = rs.getString("middleName");
					String lastName = rs.getString("lastName");

					FullNameGenerator fullNameGenerator =
						FullNameGeneratorFactory.getInstance();

					return fullNameGenerator.getFullName(
						firstName, middleName, lastName);
				}

				return StringPool.BLANK;
			}
		}
	}

	protected String localize(long companyId, String content, String key)
		throws Exception {

		String languageId = UpgradeProcessUtil.getDefaultLanguageId(companyId);

		Locale locale = LocaleUtil.fromLanguageId(languageId);

		Map<Locale, String> localizationMap = new HashMap<>();

		localizationMap.put(locale, content);

		return LocalizationUtil.updateLocalization(
			localizationMap, StringPool.BLANK, key, languageId);
	}

	protected void updateFileEntryType(
			long fileEntryTypeId, long companyId, String fileEntryTypeKey,
			String name, String description)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"update DLFileEntryType set fileEntryTypeKey = ?, name = ?, " +
					"description = ? where fileEntryTypeId = ?")) {

			ps.setString(1, fileEntryTypeKey);
			ps.setString(2, localize(companyId, name, "Name"));
			ps.setString(3, localize(companyId, description, "Description"));
			ps.setLong(4, fileEntryTypeId);

			ps.executeUpdate();
		}
	}

	protected void updateFileEntryTypes() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"select fileEntryTypeId, companyId, name, description from " +
					"DLFileEntryType");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long fileEntryTypeId = rs.getLong("fileEntryTypeId");
				long companyId = rs.getLong("companyId");
				String name = GetterUtil.getString(rs.getString("name"));
				String description = rs.getString("description");

				if (fileEntryTypeId ==
						DLFileEntryTypeConstants.
							FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT) {

					name = DLFileEntryTypeConstants.NAME_BASIC_DOCUMENT;
				}

				updateFileEntryType(
					fileEntryTypeId, companyId, StringUtil.toUpperCase(name),
					name, description);
			}
		}
	}

}