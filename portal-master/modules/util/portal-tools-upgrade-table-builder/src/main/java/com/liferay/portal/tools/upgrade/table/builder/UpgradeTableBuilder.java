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

package com.liferay.portal.tools.upgrade.table.builder;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.tools.ArgumentsUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeTableBuilder {

	public static void main(String[] args) throws Exception {
		Map<String, String> arguments = ArgumentsUtil.parseArguments(args);

		String baseDirName = GetterUtil.getString(
			arguments.get("upgrade.base.dir"),
			UpgradeTableBuilderArgs.BASE_DIR_NAME);
		boolean osgiModule = GetterUtil.getBoolean(
			arguments.get("upgrade.osgi.module"),
			UpgradeTableBuilderArgs.OSGI_MODULE);
		String releaseInfoFileName = arguments.get("upgrade.release.info.file");
		String upgradeTableDirName = arguments.get("upgrade.table.dir");

		try {
			new UpgradeTableBuilder(
				baseDirName, osgiModule, releaseInfoFileName,
				upgradeTableDirName);
		}
		catch (Exception e) {
			ArgumentsUtil.processMainException(arguments, e);
		}
	}

	public UpgradeTableBuilder(
			String baseDirName, boolean osgiModule, String releaseInfoFileName,
			String upgradeTableDirName)
		throws Exception {

		_baseDirName = baseDirName;
		_osgiModule = osgiModule;

		if (_osgiModule) {
			_releaseInfoVersion = null;
		}
		else {
			_releaseInfoVersion = _getReleaseInfoVersion(releaseInfoFileName);
		}

		_upgradeTableDirName = upgradeTableDirName;

		FileSystem fileSystem = FileSystems.getDefault();

		final PathMatcher pathMatcher = fileSystem.getPathMatcher(
			"glob:**/upgrade/v**/util/*Table.java");

		Files.walkFileTree(
			Paths.get(_baseDirName),
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(
						Path path, BasicFileAttributes basicFileAttributes)
					throws IOException {

					if (pathMatcher.matches(path)) {
						_buildUpgradeTable(path);
					}

					return FileVisitResult.CONTINUE;
				}

			});
	}

	private void _buildUpgradeTable(Path path) throws IOException {
		String pathString = path.toString();

		pathString = pathString.replace('\\', '/');

		String upgradeFileVersion = pathString.replaceFirst(
			".*/upgrade/v(.+)/util.*", "$1");

		upgradeFileVersion = upgradeFileVersion.replace('_', '.');

		if (upgradeFileVersion.contains("to")) {
			upgradeFileVersion = upgradeFileVersion.replaceFirst(
				".+\\.to\\.(.+)", "$1");
		}

		String fileName = String.valueOf(path.getFileName());

		String tableName = fileName.substring(0, fileName.length() - 10);

		String upgradeFileName = tableName + "ModelImpl.java";

		Path upgradeFilePath = Paths.get(
			_upgradeTableDirName, upgradeFileVersion, upgradeFileName);

		if (Files.notExists(upgradeFilePath)) {
			if (!upgradeFileVersion.equals(_getSchemaVersion())) {
				return;
			}

			upgradeFilePath = _getUpgradeFilePath(upgradeFileName);

			if (upgradeFilePath == null) {
				return;
			}
		}

		String content = _read(path);

		String packagePath = _getPackagePath(content);
		String className = fileName.substring(0, fileName.length() - 5);

		String upgradeFileContent = _read(upgradeFilePath);

		String author = _getAuthor(content);

		Path indexesFilePath = _getIndexesFilePath(upgradeFileVersion);

		String[] addIndexes = _getAddIndexes(indexesFilePath, tableName);

		content = _getContent(
			packagePath, className, upgradeFileContent, author, addIndexes);

		Files.write(path, content.getBytes(StandardCharsets.UTF_8));
	}

	private List<Path> _findFiles(
			String baseDirName, String pattern, final int limit)
		throws IOException {

		final List<Path> paths = new ArrayList<>();

		FileSystem fileSystem = FileSystems.getDefault();

		final PathMatcher pathMatcher = fileSystem.getPathMatcher(
			"glob:" + pattern);

		Files.walkFileTree(
			Paths.get(baseDirName),
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(
					Path filePath, BasicFileAttributes basicFileAttributes) {

					if (pathMatcher.matches(filePath)) {
						paths.add(filePath);

						if (paths.size() == limit) {
							return FileVisitResult.TERMINATE;
						}
					}

					return FileVisitResult.CONTINUE;
				}

			});

		return paths;
	}

	private String[] _getAddIndexes(Path indexesFilePath, String tableName)
		throws IOException {

		List<String> addIndexes = new ArrayList<>();

		try (BufferedReader bufferedReader = Files.newBufferedReader(
				indexesFilePath, StandardCharsets.UTF_8)) {

			String line = null;

			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains(" on " + tableName + " (") ||
					line.contains(" on " + tableName + "_ (")) {

					String sql = line.trim();

					if (sql.endsWith(";")) {
						sql = sql.substring(0, sql.length() - 1);
					}

					addIndexes.add(sql);
				}
			}
		}

		return addIndexes.toArray(new String[addIndexes.size()]);
	}

	private String _getAuthor(String content) {
		int x = content.indexOf("* @author ");

		if (x != -1) {
			int y = content.indexOf("*", x + 1);

			if (y != -1) {
				return content.substring(x + 10, y).trim();
			}
		}

		return _AUTHOR;
	}

	private String _getContent(
			String packagePath, String className, String content, String author,
			String[] addIndexes)
		throws IOException {

		int x = content.indexOf("public static final String TABLE_NAME =");

		if (x == -1) {
			x = content.indexOf("public static String TABLE_NAME =");
		}

		int y = content.indexOf("public static final String TABLE_SQL_DROP =");

		if (y == -1) {
			y = content.indexOf("public static String TABLE_SQL_DROP =");
		}

		y = content.indexOf(";", y);

		content = content.substring(x, y + 1);

		content = content.replace("\t", "");
		content = content.replace("{ \"", "{\"");
		content = content.replace("new Integer(Types.", "Types.");
		content = content.replace(") }", "}");
		content = content.replace(" }", "}");

		while (content.contains("\n\n")) {
			content = content.replace("\n\n", "\n");
		}

		StringBuilder sb = new StringBuilder();

		sb.append(_getCopyright());

		sb.append("\n\npackage ");
		sb.append(packagePath);
		sb.append(";\n\n");

		sb.append("import java.sql.Types;\n\n");

		if (content.contains("TABLE_COLUMNS_MAP")) {
			sb.append("import java.util.HashMap;\n");
			sb.append("import java.util.Map;\n\n");
		}

		sb.append("/**\n");
		sb.append(" * @author\t  ");
		sb.append(author);
		sb.append("\n");
		sb.append(" * @generated\n");
		sb.append(" */\n");
		sb.append("public class ");
		sb.append(className);
		sb.append(" {\n\n");

		String[] lines = content.split("\\n");

		for (String line : lines) {
			if (line.startsWith("public static") || line.startsWith("};")) {
				sb.append("\t");
			}
			else if (line.startsWith("{\"")) {
				sb.append("\t\t");
			}

			sb.append(line);
			sb.append("\n");

			if (line.endsWith(";")) {
				sb.append("\n");
			}
		}

		sb.append("\tpublic static final String[] TABLE_SQL_ADD_INDEXES = {\n");

		for (int i = 0; i < addIndexes.length; i++) {
			String addIndex = addIndexes[i];

			sb.append("\t\t\"");
			sb.append(addIndex);
			sb.append("\"");

			if ((i + 1) < addIndexes.length) {
				sb.append(",");
			}

			sb.append("\n");
		}

		sb.append("\t};\n\n");

		sb.append("}");

		return sb.toString();
	}

	private String _getCopyright() throws IOException {
		Path path = Paths.get(_baseDirName);

		path = path.toAbsolutePath();

		while (path != null) {
			Path copyrightFilePath = path.resolve("copyright.txt");

			if (Files.exists(copyrightFilePath)) {
				return _read(copyrightFilePath);
			}

			path = path.getParent();
		}

		return null;
	}

	private Path _getIndexesFilePath(String upgradeFileVersion)
		throws IOException {

		Path indexesFilePath = null;

		if (_osgiModule) {
			List<Path> paths = _findFiles(
				_baseDirName, "**/sql/indexes.sql", 1);

			if (!paths.isEmpty()) {
				indexesFilePath = paths.get(0);
			}
		}
		else {
			indexesFilePath = Paths.get(
				_upgradeTableDirName, upgradeFileVersion, "indexes.sql");

			if (Files.notExists(indexesFilePath)) {
				indexesFilePath = Paths.get(_baseDirName, "../sql/indexes.sql");
			}
		}

		return indexesFilePath;
	}

	private String _getPackagePath(String content) {
		Matcher matcher = _packagePathPattern.matcher(content);

		if (matcher.find()) {
			return matcher.group(1);
		}

		return null;
	}

	private String _getReleaseInfoVersion(String fileName) throws IOException {
		if (Validator.isNull(fileName)) {
			return ReleaseInfo.getVersion();
		}

		String releaseInfo = _read(Paths.get(fileName));

		Matcher matcher = _releaseInfoVersionPattern.matcher(releaseInfo);

		if (!matcher.find()) {
			throw new IOException(
				"Unable to get release info version from " + fileName);
		}

		return matcher.group(1);
	}

	private String _getSchemaVersion() throws IOException {
		if (!_osgiModule) {
			return _releaseInfoVersion;
		}

		Properties properties = new Properties();

		Path path = Paths.get(_baseDirName, "bnd.bnd");

		try (InputStream inputStream = Files.newInputStream(path)) {
			properties.load(inputStream);
		}

		return properties.getProperty("Liferay-Require-SchemaVersion");
	}

	private Path _getUpgradeFilePath(String fileName) throws IOException {
		List<Path> paths = _findFiles(_baseDirName, "**/" + fileName, 1);

		if (paths.isEmpty()) {
			return null;
		}

		return paths.get(0);
	}

	private String _read(Path path) throws IOException {
		String s = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

		return s.replace("\r\n", "\n");
	}

	private static final String _AUTHOR = "Brian Wing Shun Chan";

	private static final Pattern _packagePathPattern = Pattern.compile(
		"package (.+?);");
	private static final Pattern _releaseInfoVersionPattern = Pattern.compile(
		"private static final String _VERSION = \"(.+)\";");

	private final String _baseDirName;
	private final boolean _osgiModule;
	private final String _releaseInfoVersion;
	private final String _upgradeTableDirName;

}