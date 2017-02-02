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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Igor Beslic
 */
public class VerifyDB2 extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		DB db = DBManagerUtil.getDB();

		if (db.getDBType() != DBType.DB2) {
			return;
		}

		verifyDB2();
	}

	protected void verifyDB2() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			StringBundler sb = new StringBundler(4);

			sb.append("select tbname, name, coltype, length from ");
			sb.append("sysibm.syscolumns where tbcreator = (select distinct ");
			sb.append("current schema from sysibm.sysschemata) AND coltype = ");
			sb.append("'VARCHAR' and length = 500");

			try (PreparedStatement ps = connection.prepareStatement(
					sb.toString());
				ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					String tableName = rs.getString(1);

					if (!isPortalTableName(tableName)) {
						continue;
					}

					String columnName = rs.getString(2);

					runSQL(
						"alter table " + tableName + " alter column " +
							columnName + " set data type varchar(600)");
				}
			}
		}
	}

}