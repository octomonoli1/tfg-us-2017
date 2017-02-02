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

package com.liferay.portal.tools.data.partitioning.sql.builder.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.exporter.ExportProcess;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Manuel de la Peña
 */
public abstract class BaseDataPartitioningExporter
	implements DataPartitioningExporter, DBExporter, DBProvider {

	public BaseDataPartitioningExporter() {
	}

	@Override
	public void export(ExportContext exportContext) {
		initializeDatabase(exportContext.getProperties());

		ExportProcess exportProcess = new ExportProcess(this);

		try {
			exportProcess.export(exportContext);
		}
		catch (IOException ioe) {
			_logger.error("Unable to export", ioe);
		}
	}

	@Override
	public List<String> getControlTableNames(ExportContext exportContext) {
		return getTableNames(getControlTableNamesSQL(exportContext));
	}

	@Override
	public DataSource getDataSource() {
		return _dataSource;
	}

	@Override
	public String getDateTimeFormat() {
		return "YYYY-MM-DD HH:MM:SS";
	}

	@Override
	public int getFetchSize() {
		return 0;
	}

	@Override
	public List<String> getPartitionedTableNames(ExportContext exportContext) {
		return getTableNames(getPartitionedTableNamesSQL(exportContext));
	}

	@Override
	public String serializeTableField(Object field) {
		StringBuilder sb = new StringBuilder();

		if (field == null) {
			sb.append("null");
		}
		else if ((field instanceof Date) || (field instanceof Timestamp)) {
			sb.append("'");
			sb.append(formatDateTime(field));
			sb.append("'");
		}
		else if (field instanceof String) {
			String value = (String)field;

			value = value.replace("'", "''");

			sb.append("'");
			sb.append(value);
			sb.append("'");
		}
		else {
			sb.append("'");
			sb.append(field);
			sb.append("'");
		}

		return sb.toString();
	}

	@Override
	public void write(
		long companyId, String tableName, OutputStream outputStream) {

		DataSource dataSource = getDataSource();

		String sql = "select * from " + tableName;

		if (companyId > 0) {
			sql += " where companyId = ?";
		}

		try (Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = buildPreparedStatement(
				connection, sql, companyId);
			ResultSet resultSet = preparedStatement.executeQuery()) {

			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			int columnCount = resultSetMetaData.getColumnCount();

			while (resultSet.next()) {
				String[] fields = new String[columnCount];

				for (int i = 0; i < columnCount; i++) {
					fields[i] = serializeTableField(resultSet.getObject(i + 1));
				}

				generateInsertSQL(outputStream, tableName, fields);
			}
		}
		catch (IOException | SQLException e) {
			_logger.error(
				"Unable to generate insert SQL statements for table " +
					tableName,
				e);
		}
	}

	@Override
	public void write(String tableName, OutputStream outputStream) {
		write(0, tableName, outputStream);
	}

	@Override
	public void writeDelete(
		long companyId, String tableName, OutputStream outputStream) {

		DataSource dataSource = getDataSource();

		try (Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = buildPreparedStatement(
				connection,
				"select count(1) from " + tableName +" where companyId = ?",
				companyId);
			ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next() && (resultSet.getInt(1) > 0)) {
				String deleteSQL =
					"delete from " + tableName + " where companyId = " +
						companyId + ";\n";

				outputStream.write(deleteSQL.getBytes());
			}
		}
		catch (IOException | SQLException e) {
			_logger.error(
				"Unable to generate delete SQL statements for table " +
					tableName,
				e);
		}
	}

	protected PreparedStatement buildPreparedStatement(
			Connection connection, String sql, long companyId)
		throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(
			sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

		preparedStatement.setFetchSize(getFetchSize());

		if (companyId > 0) {
			preparedStatement.setLong(1, companyId);
		}

		return preparedStatement;
	}

	protected String formatDateTime(Object date) {
		DateFormat dateFormat = new SimpleDateFormat(getDateTimeFormat());

		return dateFormat.format(date);
	}

	protected void generateInsertSQL(
			OutputStream outputStream, String tableName, String[] fields)
		throws IOException {

		if ((fields == null) || (fields.length == 0)) {
			throw new IllegalArgumentException("Fields are null");
		}

		StringBuilder sb = new StringBuilder();

		sb.append("insert into ");
		sb.append(tableName);
		sb.append(" values (");

		for (int i = 0; i < fields.length; i++) {
			String field = fields[i];

			sb.append(field);

			if (i != (fields.length - 1)) {
				sb.append(", ");
			}
		}

		sb.append(")");

		String sql = sb.toString() + ";\n";

		outputStream.write(sql.getBytes());
	}

	protected abstract String getControlTableNamesSQL(
		ExportContext exportContext);

	protected abstract String getPartitionedTableNamesSQL(
		ExportContext exportContext);

	protected List<String> getTableNames(String sql) {
		List<String> tableNames = new ArrayList<>();

		DataSource dataSource = getDataSource();

		try (Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sql);
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				tableNames.add(resultSet.getString(getTableNameFieldName()));
			}
		}
		catch (SQLException sqle) {
			_logger.error(
				"Unable to get table names using SQL query: " + sql, sqle);
		}

		return tableNames;
	}

	protected void initializeDatabase(Properties properties) {
		HikariConfig hikariConfig = new HikariConfig(properties);

		_dataSource = new HikariDataSource(hikariConfig);
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		BaseDataPartitioningExporter.class);

	private DataSource _dataSource;

}