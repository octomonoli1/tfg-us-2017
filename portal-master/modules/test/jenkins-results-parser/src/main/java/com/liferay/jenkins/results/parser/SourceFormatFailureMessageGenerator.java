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

package com.liferay.jenkins.results.parser;

import org.apache.tools.ant.Project;

/**
 * @author Peter Yoo
 */
public class SourceFormatFailureMessageGenerator
	extends BaseFailureMessageGenerator {

	@Override
	public String getMessage(
			String buildURL, String consoleOutput, Project project)
		throws Exception {

		if (!consoleOutput.contains(_MARKER)) {
			return null;
		}

		int end = consoleOutput.indexOf(_MARKER);

		end = consoleOutput.indexOf("[exec] :", end);

		return getConsoleOutputSnippet(consoleOutput, true, end);
	}

	private static final String _MARKER =
		"[exec] com.liferay.source.formatter.SourceFormatterTest > " +
			"testSourceFormatter FAILED";

}