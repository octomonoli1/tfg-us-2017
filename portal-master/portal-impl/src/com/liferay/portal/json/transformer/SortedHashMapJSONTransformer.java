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

package com.liferay.portal.json.transformer;

import com.liferay.portal.json.JoddJSONContext;
import com.liferay.portal.kernel.json.JSONContext;
import com.liferay.portal.kernel.json.JSONTransformer;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import jodd.json.impl.MapJsonSerializer;

/**
 * @author Igor Spasic
 */
public class SortedHashMapJSONTransformer
	extends MapJsonSerializer implements JSONTransformer {

	@Override
	public void transform(JSONContext jsonContext, Object map) {
		if (map instanceof HashMap) {
			TreeMap<Object, Object> treeMap = new TreeMap<>();

			treeMap.putAll((HashMap<Object, Object>)map);

			map = treeMap;
		}

		JoddJSONContext joddJSONContext = (JoddJSONContext)jsonContext;

		super.serialize(joddJSONContext.getImplementation(), (Map<?, ?>)map);
	}

}