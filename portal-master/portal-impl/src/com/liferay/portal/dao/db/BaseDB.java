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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.dao.orm.common.SQLTransformer;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.dao.db.Index;
import com.liferay.portal.kernel.dao.db.IndexMetadata;
import com.liferay.portal.kernel.dao.db.IndexMetadataFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.template.StringTemplateResource;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.util.ClassLoaderUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.util.SimpleCounter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;

/**
 * @author Alexander Chow
 * @author Ganesh Ram
 * @author Brian Wing Shun Chan
 * @author Daniel Kocsis
 */
public abstract class BaseDB implements DB {

	@Override
	public void addIndexes(
			Connection con, String indexesSQL, Set<String> validIndexNames)
		throws IOException {

		if (_log.isInfoEnabled()) {
			_log.info("Adding indexes");
		}

		UnsyncBufferedReader bufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(indexesSQL));

		String sql = null;

		while ((sql = bufferedReader.readLine()) != null) {
			if (Validator.isNull(sql)) {
				continue;
			}

			int y = sql.indexOf(" on ");
			int x = sql.lastIndexOf(" ", y - 1);

			String indexName = sql.substring(x + 1, y);

			if (validIndexNames.contains(indexName)) {
				continue;
			}

			if (_log.isInfoEnabled()) {
				_log.info(sql);
			}

			try {
				runSQL(con, sql);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e.getMessage() + ": " + sql);
				}
			}
		}
	}

	@Override
	public void buildCreateFile(String sqlDir, String databaseName)
		throws IOException {

		buildCreateFile(sqlDir, databaseName, BARE);
		buildCreateFile(sqlDir, databaseName, DEFAULT);
	}

	@Override
	public void buildCreateFile(
			String sqlDir, String databaseName, int population)
		throws IOException {

		String suffix = getSuffix(population);

		File file = new File(
			sqlDir + "/create" + suffix + "/create" + suffix + "-" +
				getServerName() + ".sql");

		String content = buildCreateFileContent(
			sqlDir, databaseName, population);

		if (content != null) {
			FileUtil.write(file, content);
		}
	}

	@Override
	public abstract String buildSQL(String template) throws IOException;

	@Override
	public void buildSQLFile(String sqlDir, String fileName)
		throws IOException {

		String template = buildTemplate(sqlDir, fileName);

		if (Validator.isNull(template)) {
			return;
		}

		template = buildSQL(template);

		FileUtil.write(
			sqlDir + "/" + fileName + "/" + fileName + "-" + getServerName() +
				".sql",
			template);
	}

	@Override
	public DBType getDBType() {
		return _dbType;
	}

	@Override
	@SuppressWarnings("unused")
	public List<Index> getIndexes(Connection con) throws SQLException {
		return Collections.emptyList();
	}

	@Override
	public int getMajorVersion() {
		return _majorVersion;
	}

	@Override
	public int getMinorVersion() {
		return _minorVersion;
	}

	@Override
	public String getTemplateBlob() {
		return getTemplate()[5];
	}

	@Override
	public String getTemplateFalse() {
		return getTemplate()[2];
	}

	@Override
	public String getTemplateTrue() {
		return getTemplate()[1];
	}

	@Override
	public String getVersionString() {
		return _majorVersion + StringPool.PERIOD + _minorVersion;
	}

	@Override
	public long increment() {
		return CounterLocalServiceUtil.increment();
	}

	@Override
	public long increment(String name) {
		return CounterLocalServiceUtil.increment(name);
	}

	@Override
	public long increment(String name, int size) {
		return CounterLocalServiceUtil.increment(name, size);
	}

	@Override
	public boolean isSupportsAlterColumnName() {
		return _SUPPORTS_ALTER_COLUMN_NAME;
	}

	@Override
	public boolean isSupportsAlterColumnType() {
		return _SUPPORTS_ALTER_COLUMN_TYPE;
	}

	@Override
	public boolean isSupportsInlineDistinct() {
		return _SUPPORTS_INLINE_DISTINCT;
	}

	@Override
	public boolean isSupportsQueryingAfterException() {
		return _SUPPORTS_QUERYING_AFTER_EXCEPTION;
	}

	@Override
	public boolean isSupportsScrollableResults() {
		return _SUPPORTS_SCROLLABLE_RESULTS;
	}

	@Override
	public boolean isSupportsStringCaseSensitiveQuery() {
		return _supportsStringCaseSensitiveQuery;
	}

	@Override
	public boolean isSupportsUpdateWithInnerJoin() {
		return _SUPPORTS_UPDATE_WITH_INNER_JOIN;
	}

	@Override
	public void runSQL(Connection con, String sql)
		throws IOException, SQLException {

		runSQL(con, new String[] {sql});
	}

	@Override
	public void runSQL(Connection con, String[] sqls)
		throws IOException, SQLException {

		Statement s = null;

		try {
			s = con.createStatement();

			for (int i = 0; i < sqls.length; i++) {
				String sql = buildSQL(sqls[i]);

				sql = SQLTransformer.transform(sql.trim());

				if (sql.endsWith(";")) {
					sql = sql.substring(0, sql.length() - 1);
				}

				if (sql.endsWith("\ngo")) {
					sql = sql.substring(0, sql.length() - 3);
				}

				if (_log.isDebugEnabled()) {
					_log.debug(sql);
				}

				try {
					s.executeUpdate(sql);
				}
				catch (SQLException sqle) {
					handleSQLException(sql, sqle);
				}
			}
		}
		finally {
			DataAccess.cleanUp(s);
		}
	}

	@Override
	public void runSQL(String sql) throws IOException, SQLException {
		runSQL(new String[] {sql});
	}

	@Override
	public void runSQL(String[] sqls) throws IOException, SQLException {
		Connection con = DataAccess.getConnection();

		try {
			runSQL(con, sqls);
		}
		finally {
			DataAccess.cleanUp(con);
		}
	}

	@Override
	public void runSQLTemplate(String path)
		throws IOException, NamingException, SQLException {

		runSQLTemplate(path, true);
	}

	@Override
	public void runSQLTemplate(String path, boolean failOnError)
		throws IOException, NamingException, SQLException {

		ClassLoader classLoader = ClassLoaderUtil.getContextClassLoader();

		InputStream is = classLoader.getResourceAsStream(
			"com/liferay/portal/tools/sql/dependencies/" + path);

		if (is == null) {
			is = classLoader.getResourceAsStream(path);
		}

		if (is == null) {
			_log.error("Invalid path " + path);

			if (failOnError) {
				throw new IOException("Invalid path " + path);
			}
			else {
				return;
			}
		}

		String template = StringUtil.read(is);

		boolean evaluate = path.endsWith(".vm");

		runSQLTemplateString(template, evaluate, failOnError);
	}

	@Override
	public void runSQLTemplateString(
			Connection connection, String template, boolean evaluate,
			boolean failOnError)
		throws IOException, NamingException, SQLException {

		template = applyMaxStringIndexLengthLimitation(
			_columnLengthPattern.matcher(template));

		if (evaluate) {
			try {
				template = evaluateVM(template.hashCode() + "", template);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new UnsyncStringReader(template))) {

			StringBundler sb = new StringBundler();

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				if (line.isEmpty() || line.startsWith("##")) {
					continue;
				}

				if (line.startsWith("@include ")) {
					int pos = line.indexOf(" ");

					String includeFileName = line.substring(pos + 1);

					ClassLoader classLoader =
						ClassLoaderUtil.getContextClassLoader();

					InputStream is = classLoader.getResourceAsStream(
						"com/liferay/portal/tools/sql/dependencies/" +
							includeFileName);

					if (is == null) {
						is = classLoader.getResourceAsStream(includeFileName);
					}

					String include = StringUtil.read(is);

					if (includeFileName.endsWith(".vm")) {
						try {
							include = evaluateVM(includeFileName, include);
						}
						catch (Exception e) {
							_log.error(e, e);
						}
					}

					include = convertTimestamp(include);
					include = replaceTemplate(include, getTemplate());

					runSQLTemplateString(include, false, true);
				}
				else {
					sb.append(line);
					sb.append(StringPool.NEW_LINE);

					if (line.endsWith(";")) {
						String sql = sb.toString();

						sb.setIndex(0);

						try {
							if (!sql.equals("COMMIT_TRANSACTION;\n")) {
								runSQL(connection, sql);
							}
							else {
								if (_log.isDebugEnabled()) {
									_log.debug("Skip commit sql");
								}
							}
						}
						catch (IOException ioe) {
							if (failOnError) {
								throw ioe;
							}
							else if (_log.isWarnEnabled()) {
								_log.warn(ioe.getMessage());
							}
						}
						catch (SecurityException se) {
							if (failOnError) {
								throw se;
							}
							else if (_log.isWarnEnabled()) {
								_log.warn(se.getMessage());
							}
						}
						catch (SQLException sqle) {
							if (failOnError) {
								throw sqle;
							}

							String message = GetterUtil.getString(
								sqle.getMessage());

							if (!message.startsWith("Duplicate key name") &&
								_log.isWarnEnabled()) {

								_log.warn(message + ": " + buildSQL(sql));
							}

							if (message.startsWith("Duplicate entry") ||
								message.startsWith(
									"Specified key was too long")) {

								_log.error(line);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void runSQLTemplateString(
			String template, boolean evaluate, boolean failOnError)
		throws IOException, NamingException, SQLException {

		try (Connection connection = DataAccess.getConnection()) {
			runSQLTemplateString(connection, template, evaluate, failOnError);
		}
	}

	@Override
	public void setSupportsStringCaseSensitiveQuery(
		boolean supportsStringCaseSensitiveQuery) {

		if (_log.isInfoEnabled()) {
			if (supportsStringCaseSensitiveQuery) {
				_log.info("Database supports case sensitive queries");
			}
			else {
				_log.info("Database does not support case sensitive queries");
			}
		}

		_supportsStringCaseSensitiveQuery = supportsStringCaseSensitiveQuery;
	}

	@Override
	public void updateIndexes(
			Connection con, String tablesSQL, String indexesSQL,
			boolean dropIndexes)
		throws IOException, SQLException {

		List<Index> indexes = getIndexes(con);

		Set<String> validIndexNames = null;

		if (dropIndexes) {
			validIndexNames = dropIndexes(con, tablesSQL, indexesSQL, indexes);
		}
		else {
			validIndexNames = new HashSet<>();

			for (Index index : indexes) {
				String indexName = StringUtil.toUpperCase(index.getIndexName());

				validIndexNames.add(indexName);
			}
		}

		indexesSQL = applyMaxStringIndexLengthLimitation(
			_columnLengthPattern.matcher(indexesSQL));

		addIndexes(con, indexesSQL, validIndexNames);
	}

	protected BaseDB(DBType dbType, int majorVersion, int minorVersion) {
		_dbType = dbType;
		_majorVersion = majorVersion;
		_minorVersion = minorVersion;

		String[] actual = getTemplate();

		for (int i = 0; i < TEMPLATE.length; i++) {
			_templateMap.put(TEMPLATE[i], actual[i]);
		}
	}

	protected String applyMaxStringIndexLengthLimitation(Matcher matcher) {
		DBType dbType = getDBType();

		int stringIndexMaxLength = GetterUtil.getInteger(
			PropsUtil.get(
				PropsKeys.DATABASE_STRING_INDEX_MAX_LENGTH,
				new Filter(dbType.getName())),
			-1);

		if (stringIndexMaxLength < 0) {
			return matcher.replaceAll(StringPool.BLANK);
		}

		StringBuffer sb = new StringBuffer();

		String replacement = "\\(" + stringIndexMaxLength + "\\)";

		while (matcher.find()) {
			int length = Integer.valueOf(matcher.group(1));

			if (length > stringIndexMaxLength) {
				matcher.appendReplacement(sb, replacement);
			}
			else {
				matcher.appendReplacement(sb, StringPool.BLANK);
			}
		}

		matcher.appendTail(sb);

		return sb.toString();
	}

	protected String[] buildColumnNameTokens(String line) {
		String[] words = StringUtil.split(line, ' ');

		String nullable = "";

		if (words.length == 7) {
			nullable = "not null;";
		}

		String[] template = {words[1], words[2], words[3], words[4], nullable};

		return template;
	}

	protected String[] buildColumnTypeTokens(String line) {
		String[] words = StringUtil.split(line, ' ');

		String nullable = "";

		if (words.length == 6) {
			nullable = "not null;";
		}
		else if (words.length == 5) {
			nullable = words[4];
		}
		else if (words.length == 4) {
			nullable = "not null;";

			if (words[3].endsWith(";")) {
				words[3] = words[3].substring(0, words[3].length() - 1);
			}
		}

		String[] template = {words[1], words[2], "", words[3], nullable};

		return template;
	}

	protected abstract String buildCreateFileContent(
			String sqlDir, String databaseName, int population)
		throws IOException;

	protected String[] buildTableNameTokens(String line) {
		String[] words = StringUtil.split(line, StringPool.SPACE);

		return new String[] {words[1], words[2]};
	}

	protected String buildTemplate(String sqlDir, String fileName)
		throws IOException {

		String template = readFile(sqlDir + "/" + fileName + ".sql");

		if (fileName.equals("portal") ||
			fileName.equals("update-5.0.1-5.1.0")) {

			StringBundler sb = new StringBundler();

			try (UnsyncBufferedReader unsyncBufferedReader =
					new UnsyncBufferedReader(
						new UnsyncStringReader(template))) {

				String line = null;

				while ((line = unsyncBufferedReader.readLine()) != null) {
					if (line.startsWith("@include ")) {
						int pos = line.indexOf(" ");

						String includeFileName = line.substring(pos + 1);

						File includeFile = new File(
							sqlDir + "/" + includeFileName);

						if (!includeFile.exists()) {
							continue;
						}

						String include = FileUtil.read(includeFile);

						if (includeFileName.endsWith(".vm")) {
							try {
								include = evaluateVM(includeFileName, include);
							}
							catch (Exception e) {
								_log.error(e, e);
							}
						}

						include = convertTimestamp(include);
						include = replaceTemplate(include, getTemplate());

						sb.append(include);
						sb.append("\n\n");
					}
					else {
						sb.append(line);
						sb.append("\n");
					}
				}
			}

			template = sb.toString();
		}

		if (fileName.equals("indexes")) {
			template = applyMaxStringIndexLengthLimitation(
				_columnLengthPattern.matcher(template));

			if (getDBType() == DBType.SYBASE) {
				template = removeBooleanIndexes(sqlDir, template);
			}
		}

		return template;
	}

	protected String convertTimestamp(String data) {
		String s = null;

		if (this instanceof MySQLDB) {
			s = StringUtil.replace(data, "SPECIFIC_TIMESTAMP_", "");
		}
		else {
			Matcher matcher = _timestampPattern.matcher(data);

			s = matcher.replaceAll("CURRENT_TIMESTAMP");
		}

		return s;
	}

	protected Set<String> dropIndexes(
			Connection con, String tablesSQL, String indexesSQL,
			List<Index> indexes)
		throws IOException, SQLException {

		if (_log.isInfoEnabled()) {
			_log.info("Dropping stale indexes");
		}

		Set<String> validIndexNames = new HashSet<>();

		if (indexes.isEmpty()) {
			return validIndexNames;
		}

		String tablesSQLLowerCase = StringUtil.toLowerCase(tablesSQL);
		String indexesSQLLowerCase = StringUtil.toLowerCase(indexesSQL);

		String[] lines = StringUtil.splitLines(indexesSQL);

		Set<String> indexNames = new HashSet<>();

		for (String line : lines) {
			if (Validator.isNull(line)) {
				continue;
			}

			IndexMetadata indexMetadata =
				IndexMetadataFactoryUtil.createIndexMetadata(line);

			indexNames.add(
				StringUtil.toLowerCase(indexMetadata.getIndexName()));
		}

		for (Index index : indexes) {
			String indexNameUpperCase = StringUtil.toUpperCase(
				index.getIndexName());
			String indexNameLowerCase = StringUtil.toLowerCase(
				indexNameUpperCase);
			String tableName = index.getTableName();
			String tableNameLowerCase = StringUtil.toLowerCase(tableName);
			boolean unique = index.isUnique();

			validIndexNames.add(indexNameUpperCase);

			if (indexNames.contains(indexNameLowerCase)) {
				if (unique &&
					indexesSQLLowerCase.contains(
						"create unique index " + indexNameLowerCase + " ")) {

					continue;
				}

				if (!unique &&
					indexesSQLLowerCase.contains(
						"create index " + indexNameLowerCase + " ")) {

					continue;
				}
			}
			else if (!tablesSQLLowerCase.contains(
						CREATE_TABLE + tableNameLowerCase + " (")) {

				continue;
			}

			validIndexNames.remove(indexNameUpperCase);

			String sql =
				"drop index " + indexNameUpperCase + " on " + tableName;

			if (_log.isInfoEnabled()) {
				_log.info(sql);
			}

			runSQL(con, sql);
		}

		return validIndexNames;
	}

	protected String evaluateVM(String templateId, String templateContent)
		throws Exception {

		if (Validator.isNull(templateContent)) {
			return StringPool.BLANK;
		}

		ClassLoader classLoader = ClassLoaderUtil.getContextClassLoader();

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		try {
			ClassLoaderUtil.setContextClassLoader(
				ClassLoaderUtil.getPortalClassLoader());

			StringTemplateResource stringTemplateResource =
				new StringTemplateResource(templateId, templateContent);

			Template template = TemplateManagerUtil.getTemplate(
				TemplateConstants.LANG_TYPE_VM, stringTemplateResource, false);

			template.put("counter", new SimpleCounter());
			template.put("portalUUIDUtil", PortalUUIDUtil.class);

			template.processTemplate(unsyncStringWriter);
		}
		finally {
			ClassLoaderUtil.setContextClassLoader(classLoader);
		}

		// Trim insert statements because it breaks MySQL Query Browser

		StringBundler sb = new StringBundler();

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(
					new UnsyncStringReader(unsyncStringWriter.toString()))) {

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				line = line.trim();

				sb.append(line);
				sb.append("\n");
			}
		}

		templateContent = sb.toString();
		templateContent = StringUtil.replace(templateContent, "\n\n\n", "\n\n");

		return templateContent;
	}

	protected String getCreateTablesContent(String sqlDir, String suffix)
		throws IOException {

		StringBundler sb = new StringBundler(8);

		sb.append(sqlDir);

		if (!sqlDir.endsWith("/WEB-INF/sql")) {
			sb.append("/portal");
			sb.append(suffix);
			sb.append("/portal");
		}
		else {
			sb.append("/tables");
			sb.append(suffix);
			sb.append("/tables");
		}

		sb.append(suffix);
		sb.append(StringPool.DASH);
		sb.append(getServerName());
		sb.append(".sql");

		return readFile(sb.toString());
	}

	protected abstract String getServerName();

	protected String getSuffix(int type) {
		if (type == BARE) {
			return "-bare";
		}
		else {
			return StringPool.BLANK;
		}
	}

	protected abstract String[] getTemplate();

	protected void handleSQLException(String sql, SQLException sqle)
		throws SQLException {

		if (_log.isDebugEnabled()) {
			StringBundler sb = new StringBundler(10);

			sb.append("SQL: ");
			sb.append(sql);
			sb.append("\nSQL state: ");
			sb.append(sqle.getSQLState());
			sb.append("\nVendor: ");
			sb.append(getDBType());
			sb.append("\nVendor error code: ");
			sb.append(sqle.getErrorCode());
			sb.append("\nVendor error message: ");
			sb.append(sqle.getMessage());

			_log.debug(sb.toString());
		}

		throw sqle;
	}

	protected String readFile(String fileName) throws IOException {
		if (FileUtil.exists(fileName)) {
			return FileUtil.read(fileName);
		}
		else {
			return StringPool.BLANK;
		}
	}

	protected String readSQL(String fileName, String comments, String eol)
		throws IOException {

		if (!FileUtil.exists(fileName)) {
			return StringPool.BLANK;
		}

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new FileReader(new File(fileName)))) {

			StringBundler sb = new StringBundler();

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				if (!line.startsWith(comments)) {
					line = StringUtil.removeChars(line, '\n', '\t');

					if (line.endsWith(";")) {
						sb.append(line.substring(0, line.length() - 1));
						sb.append(eol);
					}
					else {
						sb.append(line);
					}
				}
			}

			return sb.toString();
		}
	}

	protected String removeBooleanIndexes(String sqlDir, String data)
		throws IOException {

		String portalData = readFile(sqlDir + "/portal-tables.sql");

		if (Validator.isNull(portalData)) {
			return StringPool.BLANK;
		}

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new UnsyncStringReader(data))) {

			StringBundler sb = new StringBundler();

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				boolean append = true;

				int x = line.indexOf(" on ");

				if (x != -1) {
					int y = line.indexOf(" (", x);

					String table = line.substring(x + 4, y);

					x = y + 2;
					y = line.indexOf(")", x);

					String[] columns = StringUtil.split(line.substring(x, y));

					x = portalData.indexOf(CREATE_TABLE + table + " (");
					y = portalData.indexOf(");", x);

					String portalTableData = portalData.substring(x, y);

					for (int i = 0; i < columns.length; i++) {
						if (portalTableData.contains(
								columns[i].trim() + " BOOLEAN")) {

							append = false;

							break;
						}
					}
				}

				if (append) {
					sb.append(line);
					sb.append("\n");
				}
			}

			return sb.toString();
		}
	}

	protected String removeInserts(String data) throws IOException {
		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new UnsyncStringReader(data))) {

			StringBundler sb = new StringBundler();

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				if (!line.startsWith("insert into ") &&
					!line.startsWith("update ")) {

					sb.append(line);
					sb.append("\n");
				}
			}

			return sb.toString();
		}
	}

	protected String removeLongInserts(String data) throws IOException {
		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new UnsyncStringReader(data))) {

			StringBundler sb = new StringBundler();

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				if (!line.startsWith("insert into Image (") &&
					!line.startsWith("insert into JournalArticle (")) {

					sb.append(line);
					sb.append("\n");
				}
			}

			return sb.toString();
		}
	}

	protected String removeNull(String content) {
		content = StringUtil.replace(content, " = null", " = NULL");
		content = StringUtil.replace(content, " is null", " IS NULL");
		content = StringUtil.replace(content, " not null", " not_null");
		content = StringUtil.replace(content, " null", "");
		content = StringUtil.replace(content, " not_null", " not null");

		return content;
	}

	protected String replaceTemplate(String template, String[] actual) {
		if ((template == null) || (TEMPLATE == null) || (actual == null)) {
			return null;
		}

		if (TEMPLATE.length != actual.length) {
			return template;
		}

		StringBundler sb = null;

		int endIndex = 0;

		Matcher matcher = _templatePattern.matcher(template);

		while (matcher.find()) {
			int startIndex = matcher.start();

			if (sb == null) {
				sb = new StringBundler();
			}

			sb.append(template.substring(endIndex, startIndex));

			endIndex = matcher.end();

			String matched = template.substring(startIndex, endIndex);

			sb.append(_templateMap.get(matched));
		}

		if (sb == null) {
			return template;
		}

		if (template.length() > endIndex) {
			sb.append(template.substring(endIndex));
		}

		return sb.toString();
	}

	protected abstract String reword(String data) throws IOException;

	protected static final String ALTER_COLUMN_NAME = "alter_column_name ";

	protected static final String ALTER_COLUMN_TYPE = "alter_column_type ";

	protected static final String ALTER_TABLE_NAME = "alter_table_name ";

	protected static final String CREATE_TABLE = "create table ";

	protected static final String DROP_INDEX = "drop index";

	protected static final String DROP_PRIMARY_KEY = "drop primary key";

	protected static final String[] RENAME_TABLE_TEMPLATE = {
		"@old-table@", "@new-table@"
	};

	protected static final String[] REWORD_TEMPLATE = {
		"@table@", "@old-column@", "@new-column@", "@type@", "@nullable@"
	};

	protected static final String[] TEMPLATE = {
		"##", "TRUE", "FALSE", "'01/01/1970'", "CURRENT_TIMESTAMP", " BLOB",
		" SBLOB", " BOOLEAN", " DATE", " DOUBLE", " INTEGER", " LONG",
		" STRING", " TEXT", " VARCHAR", " IDENTITY", "COMMIT_TRANSACTION"
	};

	private static final boolean _SUPPORTS_ALTER_COLUMN_NAME = true;

	private static final boolean _SUPPORTS_ALTER_COLUMN_TYPE = true;

	private static final boolean _SUPPORTS_INLINE_DISTINCT = true;

	private static final boolean _SUPPORTS_QUERYING_AFTER_EXCEPTION = true;

	private static final boolean _SUPPORTS_SCROLLABLE_RESULTS = true;

	private static final boolean _SUPPORTS_UPDATE_WITH_INNER_JOIN = true;

	private static final Log _log = LogFactoryUtil.getLog(BaseDB.class);

	private static final Pattern _columnLengthPattern = Pattern.compile(
		"\\[\\$COLUMN_LENGTH:(\\d+)\\$\\]");
	private static final Pattern _templatePattern;
	private static final Pattern _timestampPattern = Pattern.compile(
		"SPECIFIC_TIMESTAMP_\\d+");

	static {
		StringBundler sb = new StringBundler(TEMPLATE.length * 5 - 6);

		for (int i = 0; i < TEMPLATE.length; i++) {
			String variable = TEMPLATE[i];

			if (variable.equals("##") || variable.equals("'01/01/1970'")) {
				sb.append(variable);
			}
			else {
				sb.append("(?<!\\[\\$)");
				sb.append(variable);
				sb.append("(?!\\$\\])");

				sb.append("\\b");
			}

			sb.append(StringPool.PIPE);
		}

		sb.setIndex(sb.index() - 1);

		_templatePattern = Pattern.compile(sb.toString());
	}

	private final DBType _dbType;
	private final int _majorVersion;
	private final int _minorVersion;
	private boolean _supportsStringCaseSensitiveQuery;
	private final Map<String, String> _templateMap = new HashMap<>();

}