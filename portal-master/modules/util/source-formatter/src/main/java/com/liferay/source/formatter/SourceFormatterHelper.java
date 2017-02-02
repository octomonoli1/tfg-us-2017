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

package com.liferay.source.formatter;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.source.formatter.util.FileUtil;

import java.io.File;
import java.io.IOException;

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
import java.util.Properties;

/**
 * @author Igor Spasic
 * @author Brian Wing Shun Chan
 * @author Hugo Huijser
 */
public class SourceFormatterHelper {

	public SourceFormatterHelper(boolean useProperties) {
		_useProperties = useProperties;
	}

	public void close() throws IOException {
		if (!_useProperties) {
			return;
		}

		String newPropertiesContent = PropertiesUtil.toString(_properties);

		if (!_propertiesContent.equals(newPropertiesContent)) {
			FileUtil.write(_propertiesFile, newPropertiesContent);
		}
	}

	public List<String> getFileNames(
			String baseDir, List<String> recentChangesFileNames,
			String[] excludes, String[] includes)
		throws Exception {

		List<PathMatcher> excludeDirPathMatchers = new ArrayList<>();
		List<PathMatcher> excludeFilePathMatchers = new ArrayList<>();
		List<PathMatcher> includeFilePathMatchers = new ArrayList<>();

		FileSystem fileSystem = FileSystems.getDefault();

		for (String exclude : excludes) {
			if (exclude.endsWith("/**")) {
				exclude = exclude.substring(0, exclude.length() - 3);

				excludeDirPathMatchers.add(
					fileSystem.getPathMatcher("glob:" + exclude));
			}
			else {
				excludeFilePathMatchers.add(
					fileSystem.getPathMatcher("glob:" + exclude));
			}
		}

		for (String include : includes) {
			includeFilePathMatchers.add(
				fileSystem.getPathMatcher("glob:" + include));
		}

		if (recentChangesFileNames == null) {
			return scanForFiles(
				baseDir, excludeDirPathMatchers, excludeFilePathMatchers,
				includeFilePathMatchers);
		}

		return getFileNames(
			baseDir, recentChangesFileNames, excludeDirPathMatchers,
			excludeFilePathMatchers, includeFilePathMatchers);
	}

	public void init() throws IOException {
		if (!_useProperties) {
			return;
		}

		File basedirFile = new File("./");

		String basedirAbsolutePath = StringUtil.replace(
			basedirFile.getAbsolutePath(), new char[] {'.', ':', '/', '\\'},
			new char[] {'_', '_', '_', '_'});

		String propertiesFileName =
			System.getProperty("java.io.tmpdir") + "/SourceFormatter." +
				basedirAbsolutePath;

		_propertiesFile = new File(propertiesFileName);

		if (_propertiesFile.exists()) {
			_propertiesContent = FileUtil.read(_propertiesFile);

			PropertiesUtil.load(_properties, _propertiesContent);
		}
	}

	public void printError(String fileName, File file) {
		printError(fileName, file.toString());
	}

	public void printError(String fileName, String message) {
		if (_useProperties) {
			String encodedFileName = StringUtil.replace(
				fileName, StringPool.BACK_SLASH, StringPool.SLASH);

			_properties.remove(encodedFileName);
		}

		System.out.println(message);
	}

	protected Path getCanonicalPath(Path path) {
		try {
			File file = path.toFile();

			File canonicalFile = file.getCanonicalFile();

			return canonicalFile.toPath();
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	protected List<String> getFileNames(
			String baseDir, List<String> recentChangesFileNames,
			List<PathMatcher> excludeDirPathMatchers,
			List<PathMatcher> excludeFilePathMatchers,
			List<PathMatcher> includeFilePathMatchers)
		throws Exception {

		List<String> fileNames = new ArrayList<>();

		recentChangesFileNamesLoop:
		for (String fileName : recentChangesFileNames) {
			fileName = baseDir.concat(fileName);

			File file = new File(fileName);

			File canonicalFile = file.getCanonicalFile();

			Path filePath = canonicalFile.toPath();

			for (PathMatcher pathMatcher : excludeFilePathMatchers) {
				if (pathMatcher.matches(filePath)) {
					continue recentChangesFileNamesLoop;
				}
			}

			File dir = file.getParentFile();

			while (true) {
				File canonicalDir = dir.getCanonicalFile();

				Path dirPath = canonicalDir.toPath();

				for (PathMatcher pathMatcher : excludeDirPathMatchers) {
					if (pathMatcher.matches(dirPath)) {
						continue recentChangesFileNamesLoop;
					}
				}

				if (Files.exists(dirPath.resolve("source_formatter.ignore"))) {
					continue recentChangesFileNamesLoop;
				}

				dir = dir.getParentFile();

				if (dir == null) {
					break;
				}
			}

			for (PathMatcher pathMatcher : includeFilePathMatchers) {
				if (pathMatcher.matches(filePath)) {
					fileName = StringUtil.replace(
						fileName, StringPool.SLASH, StringPool.BACK_SLASH);

					fileNames.add(fileName);

					updateProperties(fileName);

					continue recentChangesFileNamesLoop;
				}
			}
		}

		return fileNames;
	}

	protected List<String> scanForFiles(
			String baseDir, final List<PathMatcher> excludeDirPathMatchers,
			final List<PathMatcher> excludeFilePathMatchers,
			final List<PathMatcher> includeFilePathMatchers)
		throws Exception {

		final List<String> fileNames = new ArrayList<>();

		Files.walkFileTree(
			Paths.get(baseDir),
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(
					Path dirPath, BasicFileAttributes basicFileAttributes) {

					if (Files.exists(
							dirPath.resolve("source_formatter.ignore"))) {

						return FileVisitResult.SKIP_SUBTREE;
					}

					dirPath = getCanonicalPath(dirPath);

					for (PathMatcher pathMatcher : excludeDirPathMatchers) {
						if (pathMatcher.matches(dirPath)) {
							return FileVisitResult.SKIP_SUBTREE;
						}
					}

					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(
					Path filePath, BasicFileAttributes basicFileAttributes) {

					Path canonicalPath = getCanonicalPath(filePath);

					for (PathMatcher pathMatcher : excludeFilePathMatchers) {
						if (pathMatcher.matches(canonicalPath)) {
							return FileVisitResult.CONTINUE;
						}
					}

					for (PathMatcher pathMatcher : includeFilePathMatchers) {
						if (!pathMatcher.matches(canonicalPath)) {
							continue;
						}

						String fileName = filePath.toString();

						fileNames.add(fileName);

						updateProperties(fileName);

						return FileVisitResult.CONTINUE;
					}

					return FileVisitResult.CONTINUE;
				}

			});

		return fileNames;
	}

	protected void updateProperties(String fileName) {
		if (!_useProperties) {
			return;
		}

		File file = new File(fileName);

		String encodedFileName = StringUtil.replace(
			fileName, StringPool.BACK_SLASH, StringPool.SLASH);

		long timestamp = GetterUtil.getLong(
			_properties.getProperty(encodedFileName));

		if (timestamp < file.lastModified()) {
			_properties.setProperty(
				encodedFileName, String.valueOf(file.lastModified()));
		}
	}

	private final Properties _properties = new Properties();
	private String _propertiesContent = StringPool.BLANK;
	private File _propertiesFile;
	private final boolean _useProperties;

}