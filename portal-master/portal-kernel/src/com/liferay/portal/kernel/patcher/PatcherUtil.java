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

package com.liferay.portal.kernel.patcher;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.File;

import java.util.Properties;

/**
 * @author Zsolt Balogh
 * @author Brian Wing Shun Chan
 */
public class PatcherUtil {

	public static boolean applyPatch(File patchFile) {
		return getPatcher().applyPatch(patchFile);
	}

	public static String[] getFixedIssues() {
		return getPatcher().getFixedIssues();
	}

	public static String[] getInstalledPatches() {
		return getPatcher().getInstalledPatches();
	}

	public static File getPatchDirectory() {
		return getPatcher().getPatchDirectory();
	}

	public static Patcher getPatcher() {
		PortalRuntimePermission.checkGetBeanProperty(Patcher.class);

		return _patcher;
	}

	public static int getPatchingToolVersion() {
		return getPatcher().getPatchingToolVersion();
	}

	public static String getPatchingToolVersionDisplayName() {
		return getPatcher().getPatchingToolVersionDisplayName();
	}

	public static String[] getPatchLevels() {
		return getPatcher().getPatchLevels();
	}

	public static Properties getProperties() {
		return getPatcher().getProperties();
	}

	public static boolean hasInconsistentPatchLevels() {
		return getPatcher().hasInconsistentPatchLevels();
	}

	public static boolean isConfigured() {
		return getPatcher().isConfigured();
	}

	public static void verifyPatchLevels() throws PatchInconsistencyException {
		getPatcher().verifyPatchLevels();
	}

	public void setPatcher(Patcher patcher) {
		PortalRuntimePermission.checkGetBeanProperty(Patcher.class);

		_patcher = patcher;
	}

	private static Patcher _patcher;

}