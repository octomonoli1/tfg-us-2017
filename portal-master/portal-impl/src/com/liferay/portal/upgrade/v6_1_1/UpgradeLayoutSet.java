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

package com.liferay.portal.upgrade.v6_1_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jorge Ferrer
 * @author Julio Camarero
 */
public class UpgradeLayoutSet extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateLayoutSets();
	}

	protected void updateLayoutSets() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			StringBundler sb = new StringBundler(4);

			sb.append("select Group_.groupId, Group_.liveGroupId, ");
			sb.append(
				"LayoutSet.layoutSetId from LayoutSet inner join Group_ ");
			sb.append("on (LayoutSet.groupId = Group_.groupId and ");
			sb.append("Group_.liveGroupId > 0 and LayoutSet.logo = ?)");

			try (PreparedStatement ps = connection.prepareStatement(
					sb.toString())) {

				ps.setBoolean(1, true);

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						long groupId = rs.getLong("Group_.groupId");
						long layoutSetId = rs.getLong("LayoutSet.layoutSetId");

						runSQL(
							"update LayoutSet set logoId = 0 where groupId = " +
								groupId + " and layoutSetId = " + layoutSetId);
					}
				}
			}
		}
	}

}