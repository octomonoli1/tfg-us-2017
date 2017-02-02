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

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeProcessUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Sergio González
 */
public class UpgradeLayoutFriendlyURL extends UpgradeProcess {

	protected void addLayoutFriendlyURL() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"select plid, groupId, companyId, userId, userName, " +
					"createDate, modifiedDate, privateLayout, friendlyURL " +
						"from Layout");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long plid = rs.getLong("plid");
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				Timestamp createDate = rs.getTimestamp("createDate");
				Timestamp modifiedDate = rs.getTimestamp("modifiedDate");
				boolean privateLayout = rs.getBoolean("privateLayout");
				String friendlyURL = rs.getString("friendlyURL");

				addLayoutFriendlyURL(
					groupId, companyId, userId, userName, createDate,
					modifiedDate, plid, privateLayout, friendlyURL);
			}
		}
	}

	protected void addLayoutFriendlyURL(
			long groupId, long companyId, long userId, String userName,
			Timestamp createDate, Timestamp modifiedDate, long plid,
			boolean privateLayout, String friendlyURL)
		throws Exception {

		StringBundler sb = new StringBundler(5);

		sb.append("insert into LayoutFriendlyURL (uuid_, ");
		sb.append("layoutFriendlyURLId, groupId, companyId, userId, ");
		sb.append("userName, createDate, modifiedDate, plid, privateLayout, ");
		sb.append("friendlyURL, languageId) values (?, ?, ?, ?, ?, ?, ?, ?, ");
		sb.append("?, ?, ?, ?)");

		try (PreparedStatement ps = connection.prepareStatement(
				sb.toString())) {

			ps.setString(1, PortalUUIDUtil.generate());
			ps.setLong(2, increment());
			ps.setLong(3, groupId);
			ps.setLong(4, companyId);
			ps.setLong(5, userId);
			ps.setString(6, userName);
			ps.setTimestamp(7, createDate);
			ps.setTimestamp(8, modifiedDate);
			ps.setLong(9, plid);
			ps.setBoolean(10, privateLayout);
			ps.setString(11, friendlyURL);
			ps.setString(
				12, UpgradeProcessUtil.getDefaultLanguageId(companyId));

			ps.executeUpdate();
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		addLayoutFriendlyURL();
	}

}