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

package com.liferay.journal.content.asset.addon.entry.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Julio Camarero
 */
@Component(
	immediate = true, service = ContentMetadataAssetAddonEntryTracker.class
)
public class ContentMetadataAssetAddonEntryTracker {

	public static List<ContentMetadataAssetAddonEntry>
		getContentMetadataAssetAddonEntries() {

		return new ArrayList<>(_contentMetadataAssetAddonEntries.values());
	}

	public static ContentMetadataAssetAddonEntry
		getContentMetadataAssetAddonEntry(String key) {

		return _contentMetadataAssetAddonEntries.get(key);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		unbind = "removeContentMetadataAssetAddonEntry"
	)
	protected void addContentMetadataAssetAddonEntry(
		ContentMetadataAssetAddonEntry contentMetadataAssetAddonEntry) {

		_contentMetadataAssetAddonEntries.put(
			contentMetadataAssetAddonEntry.getKey(),
			contentMetadataAssetAddonEntry);
	}

	protected void removeContentMetadataAssetAddonEntry(
		ContentMetadataAssetAddonEntry contentMetadataAssetAddonEntry) {

		_contentMetadataAssetAddonEntries.remove(
			contentMetadataAssetAddonEntry.getKey());
	}

	private static final Map<String, ContentMetadataAssetAddonEntry>
		_contentMetadataAssetAddonEntries = new ConcurrentHashMap<>();

}