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

package com.liferay.sync.engine.document.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.sync.engine.model.SyncUser;

import java.util.Map;

/**
 * @author Dennis Ju
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncContext {

	public static final String AUTH_TYPE_EMAIL_ADDRESS = "emailAddress";

	public static final String AUTH_TYPE_SCREEN_NAME = "screenName";

	public static final String AUTH_TYPE_USER_ID = "userId";

	public static final String PREFERENCE_KEY_AUTHENTICATION_RETRY_INTERVAL =
		"sync.client.authentication.retry.interval";

	public static final String PREFERENCE_KEY_BATCH_FILE_MAX_SIZE =
		"sync.client.batch.file.max.size";

	public static final String PREFERENCE_KEY_MAX_CONNECTIONS =
		"sync.client.max.connections";

	public static final String PREFERENCE_KEY_MAX_DOWNLOAD_RATE =
		"sync.client.max.download.rate";

	public static final String PREFERENCE_KEY_MAX_UPLOAD_RATE =
		"sync.client.max.upload.rate";

	public static final String PREFERENCE_KEY_POLL_INTERVAL =
		"sync.client.poll.interval";

	public String getAuthType() {
		return authType;
	}

	public String getOAuthConsumerKey() {
		return oAuthConsumerKey;
	}

	public String getOAuthConsumerSecret() {
		return oAuthConsumerSecret;
	}

	public boolean getOAuthEnabled() {
		return oAuthEnabled;
	}

	public String getPluginVersion() {
		return pluginVersion;
	}

	public int getPortalBuildNumber() {
		return portalBuildNumber;
	}

	public Map<String, String> getPortletPreferencesMap() {
		return portletPreferencesMap;
	}

	public SyncUser getSyncUser() {
		return syncUser;
	}

	public boolean isOAuthEnabled() {
		return getOAuthEnabled();
	}

	public boolean isSocialOfficeInstalled() {
		return socialOfficeInstalled;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public void setOAuthConsumerKey(String oAuthConsumerKey) {
		this.oAuthConsumerKey = oAuthConsumerKey;
	}

	public void setOAuthConsumerSecret(String oAuthConsumerSecret) {
		this.oAuthConsumerSecret = oAuthConsumerSecret;
	}

	public void setOAuthEnabled(boolean oAuthEnabled) {
		this.oAuthEnabled = oAuthEnabled;
	}

	public void setPluginVersion(String pluginVersion) {
		this.pluginVersion = pluginVersion;
	}

	public void setPortalBuildNumber(int portalBuildNumber) {
		this.portalBuildNumber = portalBuildNumber;
	}

	public void setPortletPreferencesMap(
		Map<String, String> portletPreferencesMap) {

		this.portletPreferencesMap = portletPreferencesMap;
	}

	public void setSocialOfficeInstalled(boolean socialOfficeInstalled) {
		this.socialOfficeInstalled = socialOfficeInstalled;
	}

	public void setSyncUser(SyncUser syncUser) {
		this.syncUser = syncUser;
	}

	protected String authType;

	@JsonProperty("OAuthConsumerKey")
	protected String oAuthConsumerKey;

	@JsonProperty("OAuthConsumerSecret")
	protected String oAuthConsumerSecret;

	@JsonProperty("OAuthEnabled")
	protected boolean oAuthEnabled;

	protected String pluginVersion;
	protected int portalBuildNumber;
	protected Map<String, String> portletPreferencesMap;
	protected boolean socialOfficeInstalled;

	@JsonProperty("user")
	protected SyncUser syncUser;

}