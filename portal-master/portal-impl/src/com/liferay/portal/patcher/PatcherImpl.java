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

package com.liferay.portal.patcher;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.patcher.PatchInconsistencyException;
import com.liferay.portal.kernel.patcher.Patcher;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Zsolt Balogh
 * @author Brian Wing Shun Chan
 * @author Igor Beslic
 * @author Zoltán Takács
 */
@DoPrivileged
public class PatcherImpl implements Patcher {

	@Override
	public boolean applyPatch(File patchFile) {
		File patchDirectory = getPatchDirectory();

		if (patchDirectory == null) {
			return false;
		}

		try {
			FileUtil.copyFile(
				patchFile,
				new File(
					patchDirectory + StringPool.SLASH + patchFile.getName()));

			return true;
		}
		catch (Exception e) {
			_log.error(
				"Unable to copy " + patchFile.getAbsolutePath() + " to " +
					patchDirectory.getAbsolutePath());

			return false;
		}
	}

	@Override
	public String[] getFixedIssues() {
		if (_fixedIssueKeys != null) {
			return _fixedIssueKeys;
		}

		Properties properties = getProperties();

		_fixedIssueKeys = StringUtil.split(
			properties.getProperty(PROPERTY_FIXED_ISSUES));

		return _fixedIssueKeys;
	}

	@Override
	public String[] getInstalledPatches() {
		if (_installedPatchNames != null) {
			return _installedPatchNames;
		}

		return _getInstalledPatches(null);
	}

	@Override
	public File getPatchDirectory() {
		if (_patchDirectory != null) {
			return _patchDirectory;
		}

		Properties properties = getProperties();

		String patchDirectoryName = properties.getProperty(
			PROPERTY_PATCH_DIRECTORY);

		if (Validator.isNotNull(patchDirectoryName)) {
			_patchDirectory = new File(patchDirectoryName);

			if (!_patchDirectory.exists()) {
				_log.error("The patch directory does not exist");
			}
		}
		else {
			_log.error("The patch directory is not specified");
		}

		return _patchDirectory;
	}

	@Override
	public int getPatchingToolVersion() {
		if (_patchingToolVersion != 0) {
			return _patchingToolVersion;
		}

		Properties properties = getProperties();

		if (properties.containsKey(PROPERTY_PATCHING_TOOL_VERSION)) {
			_patchingToolVersion = GetterUtil.getInteger(
				properties.getProperty(PROPERTY_PATCHING_TOOL_VERSION));
		}

		return _patchingToolVersion;
	}

	@Override
	public String getPatchingToolVersionDisplayName() {
		if (_patchingToolVersionDisplayName != null) {
			return _patchingToolVersionDisplayName;
		}

		Properties properties = getProperties();

		if (properties.containsKey(
				PROPERTY_PATCHING_TOOL_VERSION_DISPLAY_NAME)) {

			_patchingToolVersionDisplayName = properties.getProperty(
				PROPERTY_PATCHING_TOOL_VERSION_DISPLAY_NAME);
		}
		else {
			_patchingToolVersionDisplayName = "1.0." + getPatchingToolVersion();
		}

		return _patchingToolVersionDisplayName;
	}

	@Override
	public String[] getPatchLevels() {
		if (_patchLevels != null) {
			return _patchLevels;
		}

		Properties properties = getProperties();

		_patchLevels = StringUtil.split(
			properties.getProperty(PROPERTY_PATCH_LEVELS));

		return _patchLevels;
	}

	@Override
	public Properties getProperties() {
		if (_properties != null) {
			return _properties;
		}

		return _getProperties(PATCHER_PROPERTIES);
	}

	@Override
	public boolean hasInconsistentPatchLevels() {
		return _inconsistentPatchLevels;
	}

	@Override
	public boolean isConfigured() {
		return _configured;
	}

	@Override
	public void verifyPatchLevels() throws PatchInconsistencyException {
		Properties portalImplJARProperties = _getProperties(PATCHER_PROPERTIES);

		String[] portalImplJARPatches = _getInstalledPatches(
			portalImplJARProperties);

		Arrays.sort(portalImplJARPatches);

		Properties portalServiceJARProperties = _getProperties(
			PATCHER_SERVICE_PROPERTIES);

		String[] serviceJARPatches = _getInstalledPatches(
			portalServiceJARProperties);

		Arrays.sort(serviceJARPatches);

		if (!Arrays.equals(portalImplJARPatches, serviceJARPatches)) {
			_log.error("Inconsistent patch level detected");

			if (_log.isWarnEnabled()) {
				if (ArrayUtil.isEmpty(portalImplJARPatches)) {
					_log.warn(
						"There are no patches installed on portal-impl.jar");
				}
				else {
					_log.warn(
						"Patch level on portal-impl.jar: " +
							Arrays.toString(portalImplJARPatches));
				}

				if (ArrayUtil.isEmpty(serviceJARPatches)) {
					_log.warn(
						"There are no patches installed on portal-kernel.jar");
				}
				else {
					_log.warn(
						"Patch level on portal-kernel.jar: " +
							Arrays.toString(serviceJARPatches));
				}
			}

			_inconsistentPatchLevels = true;

			throw new PatchInconsistencyException();
		}
	}

	private String[] _getInstalledPatches(Properties properties) {
		if (properties == null) {
			properties = getProperties();
		}

		_installedPatchNames = StringUtil.split(
			properties.getProperty(PROPERTY_INSTALLED_PATCHES));

		return _installedPatchNames;
	}

	private Properties _getProperties(String fileName) {
		if (Validator.isNull(fileName)) {
			fileName = PATCHER_PROPERTIES;
		}

		Properties properties = new Properties();

		Class<?> clazz = getClass();

		if (Objects.equals(fileName, PATCHER_SERVICE_PROPERTIES)) {
			clazz = clazz.getInterfaces()[0];
		}

		ClassLoader classLoader = clazz.getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(fileName);

		if (inputStream == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to load " + fileName);
			}
		}
		else {
			try {
				properties.load(inputStream);

				_configured = true;
			}
			catch (IOException ioe) {
				_log.error(ioe, ioe);
			}
			finally {
				StreamUtil.cleanUp(inputStream);
			}
		}

		_properties = properties;

		return _properties;
	}

	private static final Log _log = LogFactoryUtil.getLog(PatcherImpl.class);

	private boolean _configured;
	private String[] _fixedIssueKeys;
	private boolean _inconsistentPatchLevels;
	private String[] _installedPatchNames;
	private File _patchDirectory;
	private int _patchingToolVersion;
	private String _patchingToolVersionDisplayName;
	private String[] _patchLevels;
	private Properties _properties;

}