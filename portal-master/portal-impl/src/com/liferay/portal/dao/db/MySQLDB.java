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

package com.liferay.portal.dao.db;

import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.dao.db.Index;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PropsValues;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Chow
 * @author Sandeep Soni
 * @author Ganesh Ram
 */
public class MySQLDB extends BaseDB {

	public MySQLDB(int majorVersion, int minorVersion) {
		super(DBType.MYSQL, majorVersion, minorVersion);
	}

	@Override
	public String buildSQL(String template) throws IOException {
		template = convertTimestamp(template);
		template = replaceTemplate(template, getTemplate());

		template = reword(template);
		template = StringUtil.replace(template, "\\'", "''");

		return template;
	}

	@Override
	public List<Index> getIndexes(Connection con) throws SQLException {
		List<Index> indexes = new ArrayList<>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			StringBundler sb = new StringBundler(4);

			sb.append("select distinct(index_name), table_name, non_unique ");
			sb.append("from information_schema.statistics where ");
			sb.append("index_schema = database() and (index_name like ");
			sb.append("'LIFERAY_%' or index_name like 'IX_%')");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				String indexName = rs.getString("index_name");
				String tableName = rs.getString("table_name");
				boolean unique = !rs.getBoolean("non_unique");

				indexes.add(new Index(indexName, tableName, unique));
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}

		return indexes;
	}

	@Override
	public boolean isSupportsUpdateWithInnerJoin() {
		return _SUPPORTS_UPDATE_WITH_INNER_JOIN;
	}

	@Override
	protected String buildCreateFileContent(
			String sqlDir, String databaseName, int population)
		throws IOException {

		StringBundler sb = new StringBundler(14);

		sb.append("drop database if exists ");
		sb.append(databaseName);
		sb.append(";\n");
		sb.append("create database ");
		sb.append(databaseName);
		sb.append(" character set utf8;\n");

		if (population != BARE) {
			sb.append("use ");
			sb.append(databaseName);
			sb.append(";\n\n");

			String suffix = getSuffix(population);

			sb.append(getCreateTablesContent(sqlDir, suffix));
			sb.append("\n\n");
			sb.append(readFile(sqlDir + "/indexes/indexes-mysql.sql"));
			sb.append("\n\n");
			sb.append(readFile(sqlDir + "/sequences/sequences-mysql.sql"));
		}

		return sb.toString();
	}

	@Override
	protected String getServerName() {
		return "mysql";
	}

	@Override
	protected String[] getTemplate() {
		if (GetterUtil.getFloat(getVersionString()) >= 5.6F) {
			_MYSQL[8] = " datetime(6)";
		}

		return _MYSQL;
	}

	@Override
	protected String reword(String data) throws IOException {
		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new UnsyncStringReader(data))) {

			StringBundler sb = new StringBundler();

			boolean createTable = false;

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				if (StringUtil.startsWith(line, "create table")) {
					createTable = true;
				}
				else if (line.startsWith(ALTER_COLUMN_NAME)) {
					String[] template = buildColumnNameTokens(line);

					line = StringUtil.replace(
						"alter table @table@ change column @old-column@ " +
							"@new-column@ @type@;",
						REWORD_TEMPLATE, template);
				}
				else if (line.startsWith(ALTER_COLUMN_TYPE)) {
					String[] template = buildColumnTypeTokens(line);

					line = StringUtil.replace(
						"alter table @table@ modify @old-column@ @type@;",
						REWORD_TEMPLATE, template);
				}
				else if (line.startsWith(ALTER_TABLE_NAME)) {
					String[] template = buildTableNameTokens(line);

					line = StringUtil.replace(
						"rename table @old-table@ to @new-table@;",
						RENAME_TABLE_TEMPLATE, template);
				}

				int pos = line.indexOf(";");

				if (createTable && (pos != -1)) {
					createTable = false;

					line =
						line.substring(0, pos) + " engine " +
							PropsValues.DATABASE_MYSQL_ENGINE +
								line.substring(pos);
				}

				sb.append(line);
				sb.append("\n");
			}

			return sb.toString();
		}
	}

	private static final String[] _MYSQL = {
		"##", "1", "0", "'1970-01-01'", "now()", " longblob", " longblob",
		" tinyint", " datetime", " double", " integer", " bigint", " longtext",
		" longtext", " varchar", "  auto_increment", "commit"
	};

	private static final boolean _SUPPORTS_UPDATE_WITH_INNER_JOIN = true;

}