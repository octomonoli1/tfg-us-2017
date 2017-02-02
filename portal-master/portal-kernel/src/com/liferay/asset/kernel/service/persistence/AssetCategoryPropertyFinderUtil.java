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
public class AssetCategoryPropertyFinderUtil {
	public static int countByG_K(long groupId, java.lang.String key) {
		return getFinder().countByG_K(groupId, key);
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetCategoryProperty> findByG_K(
		long groupId, java.lang.String key) {
		return getFinder().findByG_K(groupId, key);
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetCategoryProperty> findByG_K(
		long groupId, java.lang.String key, int start, int end) {
		return getFinder().findByG_K(groupId, key, start, end);
	}

	public static AssetCategoryPropertyFinder getFinder() {
		if (_finder == null) {
			_finder = (AssetCategoryPropertyFinder)PortalBeanLocatorUtil.locate(AssetCategoryPropertyFinder.class.getName());

			ReferenceRegistry.registerReference(AssetCategoryPropertyFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AssetCategoryPropertyFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AssetCategoryPropertyFinderUtil.class,
			"_finder");
	}

	private static AssetCategoryPropertyFinder _finder;
}