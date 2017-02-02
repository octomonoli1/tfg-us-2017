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

package com.liferay.portal.security.sso.token.internal.verify;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.PrefsProps;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.sso.token.constants.LegacyTokenPropsKeys;
import com.liferay.portal.security.sso.token.constants.TokenConfigurationKeys;
import com.liferay.portal.security.sso.token.constants.TokenConstants;
import com.liferay.portal.verify.BaseCompanySettingsVerifyProcess;
import com.liferay.portal.verify.VerifyProcess;

import java.util.Dictionary;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Greenwald
 */
@Component(
	immediate = true,
	property = {"verify.process.name=com.liferay.portal.security.sso.token.siteminder"},
	service = VerifyProcess.class
)
public class SiteMinderCompanySettingsVerifyProcess
	extends BaseCompanySettingsVerifyProcess {

	@Override
	protected CompanyLocalService getCompanyLocalService() {
		return _companyLocalService;
	}

	@Override
	protected Set<String> getLegacyPropertyKeys() {
		return SetUtil.fromArray(LegacyTokenPropsKeys.SITEMINDER_KEYS);
	}

	@Override
	protected Dictionary<String, String> getPropertyValues(long companyId) {
		Dictionary<String, String> dictionary = new HashMapDictionary<>();

		boolean siteMinderEnabled = _prefsProps.getBoolean(
			companyId, LegacyTokenPropsKeys.SITEMINDER_AUTH_ENABLED);

		if (!siteMinderEnabled) {
			return dictionary;
		}

		dictionary.put(
			TokenConfigurationKeys.AUTH_ENABLED,
			_prefsProps.getString(
				companyId, LegacyTokenPropsKeys.SITEMINDER_AUTH_ENABLED,
				StringPool.FALSE));
		dictionary.put(
			TokenConfigurationKeys.IMPORT_FROM_LDAP,
			_prefsProps.getString(
				companyId, LegacyTokenPropsKeys.SITEMINDER_IMPORT_FROM_LDAP,
				StringPool.FALSE));
		dictionary.put(
			TokenConfigurationKeys.USER_HEADER,
			_prefsProps.getString(
				companyId, LegacyTokenPropsKeys.SITEMINDER_USER_HEADER,
				"SM_USER"));

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Adding SiteMinder token configuration for company " +
					companyId + " with properties: " + dictionary);
		}

		return dictionary;
	}

	@Override
	protected SettingsFactory getSettingsFactory() {
		return _settingsFactory;
	}

	@Override
	protected String getSettingsId() {
		return TokenConstants.SERVICE_NAME;
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

	private static final Log _log = LogFactoryUtil.getLog(
		SiteMinderCompanySettingsVerifyProcess.class);

	private CompanyLocalService _companyLocalService;
	private PrefsProps _prefsProps;
	private SettingsFactory _settingsFactory;

}