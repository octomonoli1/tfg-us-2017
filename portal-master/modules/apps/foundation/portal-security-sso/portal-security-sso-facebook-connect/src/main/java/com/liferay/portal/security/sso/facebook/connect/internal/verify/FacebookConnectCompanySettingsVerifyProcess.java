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

package com.liferay.portal.security.sso.facebook.connect.internal.verify;

import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.PrefsProps;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.sso.facebook.connect.constants.FacebookConnectConfigurationKeys;
import com.liferay.portal.security.sso.facebook.connect.constants.FacebookConnectConstants;
import com.liferay.portal.security.sso.facebook.connect.constants.LegacyFacebookConnectPropsKeys;
import com.liferay.portal.verify.BaseCompanySettingsVerifyProcess;
import com.liferay.portal.verify.VerifyProcess;

import java.util.Dictionary;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Stian Sigvartsen
 */
@Component(
	immediate = true,
	property = {"verify.process.name=com.liferay.portal.security.sso.facebook.connect"},
	service = VerifyProcess.class
)
public class FacebookConnectCompanySettingsVerifyProcess
	extends BaseCompanySettingsVerifyProcess {

	@Override
	protected CompanyLocalService getCompanyLocalService() {
		return _companyLocalService;
	}

	@Override
	protected Set<String> getLegacyPropertyKeys() {
		return SetUtil.fromArray(
			LegacyFacebookConnectPropsKeys.FACEBOOK_CONNECT_KEYS);
	}

	@Override
	protected Dictionary<String, String> getPropertyValues(long companyId) {
		Dictionary<String, String> dictionary = new HashMapDictionary<>();

		dictionary.put(
			FacebookConnectConfigurationKeys.AUTH_ENABLED,
			_prefsProps.getString(
				companyId, LegacyFacebookConnectPropsKeys.AUTH_ENABLED,
				StringPool.FALSE));
		dictionary.put(
			FacebookConnectConfigurationKeys.APP_ID,
			_prefsProps.getString(
				companyId, LegacyFacebookConnectPropsKeys.APP_ID,
				StringPool.BLANK));
		dictionary.put(
			FacebookConnectConfigurationKeys.APP_SECRET,
			_prefsProps.getString(
				companyId, LegacyFacebookConnectPropsKeys.APP_SECRET,
				StringPool.BLANK));
		dictionary.put(
			FacebookConnectConfigurationKeys.GRAPH_URL,
			_prefsProps.getString(
				companyId, LegacyFacebookConnectPropsKeys.GRAPH_URL,
				StringPool.BLANK));
		dictionary.put(
			FacebookConnectConfigurationKeys.OAUTH_AUTH_URL,
			_prefsProps.getString(
				companyId, LegacyFacebookConnectPropsKeys.OAUTH_AUTH_URL,
				StringPool.BLANK));
		dictionary.put(
			FacebookConnectConfigurationKeys.OAUTH_REDIRECT_URL,
			upgradeLegacyRedirectURI(
				_prefsProps.getString(
					companyId,
					LegacyFacebookConnectPropsKeys.OAUTH_REDIRECT_URL,
					StringPool.BLANK)));
		dictionary.put(
			FacebookConnectConfigurationKeys.OAUTH_TOKEN_URL,
			_prefsProps.getString(
				companyId, LegacyFacebookConnectPropsKeys.OAUTH_TOKEN_URL,
				StringPool.BLANK));
		dictionary.put(
			FacebookConnectConfigurationKeys.VERIFIED_ACCOUNT_REQUIRED,
			_prefsProps.getString(
				companyId,
				LegacyFacebookConnectPropsKeys.VERIFIED_ACCOUNT_REQUIRED,
				StringPool.FALSE));

		return dictionary;
	}

	@Override
	protected SettingsFactory getSettingsFactory() {
		return _settingsFactory;
	}

	@Override
	protected String getSettingsId() {
		return FacebookConnectConstants.SERVICE_NAME;
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	@Reference(unbind = "-")
	protected void setPrefsProps(PrefsProps prefsProps) {
		_prefsProps = prefsProps;
	}

	@Reference(unbind = "-")
	protected void setSettingsFactory(SettingsFactory settingsFactory) {
		_settingsFactory = settingsFactory;
	}

	protected String upgradeLegacyRedirectURI(String legacyRedirectURI) {
		if (Validator.isNull(legacyRedirectURI)) {
			return legacyRedirectURI;
		}

		return legacyRedirectURI.replaceFirst(
			"/c/login/facebook_connect_oauth",
			"/c/portal/facebook_connect_oauth");
	}

	private CompanyLocalService _companyLocalService;
	private PrefsProps _prefsProps;
	private SettingsFactory _settingsFactory;

}