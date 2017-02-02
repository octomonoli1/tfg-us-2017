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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

/**
 * @author Kevin Yen
 */
public abstract class BaseBuild implements Build {

	@Override
	public int getBuildNumber() {
		return buildNumber;
	}

	@Override
	public String getBuildURL() {
		String jobURL = getJobURL();

		if ((jobURL == null) || (buildNumber == -1)) {
			return null;
		}

		return jobURL + buildNumber + "/";
	}

	@Override
	public String getJobName() {
		return jobName;
	}

	@Override
	public String getJobURL() {
		if ((master == null) || (jobName == null)) {
			return null;
		}

		return "http://" + master + "/job/" + jobName + "/";
	}

	@Override
	public String getMaster() {
		return master;
	}

	@Override
	public String getResult() {
		if (!_status.equals("completed")) {
			throw new IllegalStateException("Build not completed");
		}

		String buildURL = getBuildURL();

		if ((result == null) && (buildURL != null)) {
			try {
				JSONObject resultJSONObject =
					JenkinsResultsParserUtil.toJSONObject(
						buildURL + "api/json?tree=result");

				result = resultJSONObject.optString("result");

				if (result.equals("")) {
					result = null;
				}
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return result;
	}

	@Override
	public String getStatus() {
		return _status;
	}

	@Override
	public long getStatusAge() {
		return System.currentTimeMillis() - statusModifiedTime;
	}

	protected static String decodeURL(String url) {
		url = url.replace("%28", "(");
		url = url.replace("%29", ")");
		url = url.replace("%5B", "[");
		url = url.replace("%5D", "]");

		return url;
	}

	protected BaseBuild() {
		setStatus("starting");
	}

	protected BaseBuild(String buildURL) throws Exception {
		setBuildURL(buildURL);
	}

	protected void setBuildURL(String buildURL) throws Exception {
		buildURL = decodeURL(buildURL);

		Matcher matcher = _buildURLPattern.matcher(buildURL);

		if (!matcher.find()) {
			throw new IllegalArgumentException("Invalid build URL " + buildURL);
		}

		buildNumber = Integer.parseInt(matcher.group("buildNumber"));
		jobName = matcher.group("jobName");
		master = matcher.group("master");

		update();
	}

	protected void setStatus(String status) {
		if (((status == null) && (_status != null)) ||
			!status.equals(_status)) {

			_status = status;

			statusModifiedTime = System.currentTimeMillis();
		}
	}

	protected int buildNumber = -1;
	protected String jobName;
	protected String master;
	protected String result;
	protected long statusModifiedTime;

	private static final Pattern _buildURLPattern = Pattern.compile(
		"\\w+://(?<master>[^/]+)/+job/+(?<jobName>[^/]+).*/(?<buildNumber>" +
			"\\d+)/?");

	private String _status;

}