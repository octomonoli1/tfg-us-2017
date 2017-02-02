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

package com.liferay.portal.tools;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Carlos Sierra Andrés
 * @author André de Oliveira
 * @author Raymond Augé
 * @author Hugo Huijser
 */
public class JavaImportsFormatter extends BaseImportsFormatter {

	public static String getImports(String content) {
		Matcher matcher = _importsPattern.matcher(content);

		if (matcher.find()) {
			return matcher.group();
		}

		return null;
	}

	@Override
	protected ImportPackage createImportPackage(String line) {
		return createJavaImportPackage(line);
	}

	@Override
	protected String doFormat(
			String content, Pattern importPattern, String packagePath,
			String className)
		throws IOException {

		String imports = getImports(content);

		if (Validator.isNull(imports)) {
			return content;
		}

		String newImports = stripUnusedImports(
			imports, content, packagePath, className, "\\*");

		newImports = sortAndGroupImports(newImports);

		if (!imports.equals(newImports)) {
			content = StringUtil.replaceFirst(content, imports, newImports);
		}

		content = content.replaceFirst(
			"(?m)^[ \t]*(package .*;)\\s*^[ \t]*import", "$1\n\nimport");

		content = content.replaceFirst(
			"(?m)^[ \t]*((?:package|import) .*;)\\s*^[ \t]*/\\*\\*",
			"$1\n\n/**");

		return ToolsUtil.stripFullyQualifiedClassNames(
			content, newImports, packagePath);
	}

	private static final Pattern _importsPattern = Pattern.compile(
		"(^[ \t]*import\\s+.*;\n+)+", Pattern.MULTILINE);

}