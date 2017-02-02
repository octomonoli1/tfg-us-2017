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

package com.liferay.asset.kernel.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AssetEntryFinderUtil {
	public static int countEntries(AssetEntryQuery entryQuery) {
		return getFinder().countEntries(entryQuery);
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetEntry> findByDLFileEntryC_T(
		long classNameId, java.lang.String treePath) {
		return getFinder().findByDLFileEntryC_T(classNameId, treePath);
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetEntry> findByDLFolderC_T(
		long classNameId, java.lang.String treePath) {
		return getFinder().findByDLFolderC_T(classNameId, treePath);
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetEntry> findEntries(
		AssetEntryQuery entryQuery) {
		return getFinder().findEntries(entryQuery);
	}

	public static AssetEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (AssetEntryFinder)PortalBeanLocatorUtil.locate(AssetEntryFinder.class.getName());

			ReferenceRegistry.registerReference(AssetEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AssetEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AssetEntryFinderUtil.class,
			"_finder");
	}

	private static AssetEntryFinder _finder;
}