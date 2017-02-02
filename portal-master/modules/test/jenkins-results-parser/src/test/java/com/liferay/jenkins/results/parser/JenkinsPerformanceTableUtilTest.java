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

import java.io.File;

import java.net.URL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Peter Yoo
 */
public class JenkinsPerformanceTableUtilTest
	extends BaseJenkinsResultsParserTestCase {

	@Before
	public void setUp() throws Exception {
		downloadSample(
			"master-success-1", "1682",
			"test-portal-acceptance-pullrequest(master)", "test-1-1");
		downloadSample(
			"master-failure-1", "1697",
			"test-portal-acceptance-pullrequest(master)", "test-1-1");
		downloadSample(
			"6.2.x-success-1", "317",
			"test-portal-acceptance-pullrequest(ee-6.2.x)", "test-1-1");
		downloadSample(
			"6.2.x-failure-1", "313",
			"test-portal-acceptance-pullrequest(ee-6.2.x)", "test-1-1");
	}

	@Test
	public void testGenerateHTML() throws Exception {
		assertSamples();
	}

	@Override
	protected void downloadSample(File sampleDir, URL url) throws Exception {
		downloadSampleJobMessages(
			url.toString() + "/logText/progressiveText", sampleDir);
	}

	protected void downloadSample(
			String sampleKey, String buildNumber, String jobName,
			String hostName)
		throws Exception {

		String urlString =
			"https://${hostName}.liferay.com/job/${jobName}/${buildNumber}/";

		urlString = replaceToken(urlString, "buildNumber", buildNumber);
		urlString = replaceToken(urlString, "hostName", hostName);
		urlString = replaceToken(urlString, "jobName", jobName);

		URL url = JenkinsResultsParserUtil.createURL(urlString);

		downloadSample(sampleKey, url);
	}

	protected void downloadSampleJobMessages(
			String progressiveTextURL, File sampleDir)
		throws Exception {

		StringBuilder sb = new StringBuilder();

		int count = 0;

		String content = JenkinsResultsParserUtil.toString(
			JenkinsResultsParserUtil.getLocalURL(progressiveTextURL));

		Matcher progressiveTextMatcher = _progressiveTextPattern.matcher(
			content);

		while (progressiveTextMatcher.find()) {
			String fileSuffix = null;
			String url = progressiveTextMatcher.group("url");
			String urlSuffix = null;

			if (url.contains("-source")) {
				fileSuffix = "source-" + count;
				urlSuffix = "/api/json";
			}
			else {
				fileSuffix = Integer.toString(count);
				urlSuffix = "/testReport/api/json";
			}

			JenkinsResultsParserUtil.write(
				new File(sampleDir, "job-" + fileSuffix + urlSuffix),
				JenkinsResultsParserUtil.toString(
					JenkinsResultsParserUtil.getLocalURL(
						url + urlSuffix + "?pretty")));

			if (sb.length() > 0) {
				sb.append("|");
			}

			sb.append(toURLString(new File(sampleDir, "job-" + fileSuffix)));

			count++;
		}

		JenkinsResultsParserUtil.write(
			new File(sampleDir, "urls.txt"), sb.toString());
	}

	@Override
	protected String getMessage(String urlString) throws Exception {
		Class<?> clazz = getClass();

		while (urlString.endsWith("/")) {
			urlString = urlString.substring(0, urlString.length() - 1);
		}

		String sampleName = urlString.substring(urlString.lastIndexOf("/") + 1);

		String content = JenkinsResultsParserUtil.toString(
			JenkinsResultsParserUtil.getLocalURL(
				"${dependencies.url}" + clazz.getSimpleName() + "/" +
					sampleName + "/urls.txt"));

		if (content.length() == 0) {
			return "";
		}

		for (String url : content.split("\\|")) {
			JenkinsPerformanceDataUtil.processPerformanceData(
				"build", url.trim(), 100);
		}

		return JenkinsPerformanceTableUtil.generateHTML();
	}

	private static final Pattern _progressiveTextPattern = Pattern.compile(
		"\\'.*\\' completed at (?<url>.+)\\.");

}