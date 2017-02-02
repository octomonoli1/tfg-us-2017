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

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.upgrade.v7_0_0.util.GroupTable;

import java.sql.PreparedStatement;

import java.util.Locale;

/**
 * @author Eudaldo Alonso
 */
public class UpgradeGroup extends UpgradeProcess {

	protected void createIndex() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			runSQL("create index IX_8257E37B on Group_ (classNameId, classPK)");
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		alter(GroupTable.class, new AlterColumnType("name", "STRING null"));

		createIndex();

		updateGlobalGroupName();
	}

	protected void updateGlobalGroupName() throws Exception {
		LocalizedValuesMap localizedValuesMap = new LocalizedValuesMap();

		for (Locale locale : LanguageUtil.getAvailableLocales()) {
			localizedValuesMap.put(locale, LanguageUtil.get(locale, "global"));
		}

		String nameXML = LocalizationUtil.getXml(localizedValuesMap, "global");

		try (PreparedStatement ps = connection.prepareStatement(
				"update Group_ set name = ? where friendlyURL = '/global'")) {

			ps.setString(1, nameXML);

			ps.executeUpdate();
		}
	}

}