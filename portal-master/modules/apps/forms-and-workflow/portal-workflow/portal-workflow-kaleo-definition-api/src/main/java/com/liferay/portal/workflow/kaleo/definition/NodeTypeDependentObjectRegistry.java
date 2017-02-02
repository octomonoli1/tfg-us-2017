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

package com.liferay.portal.workflow.kaleo.definition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class NodeTypeDependentObjectRegistry<T> {

	public void addNodeTypeDependentObject(
		String nodeTypeString, T nodeTypeDependentObject) {

		NodeType nodeType = NodeType.valueOf(nodeTypeString);

		_nodeTypeDependentObjects.put(nodeType, nodeTypeDependentObject);
	}

	public T getNodeTypeDependentObjects(NodeType nodeType) {
		T nodeTypeDependentObject = _nodeTypeDependentObjects.get(nodeType);

		if (nodeTypeDependentObject == null) {
			throw new IllegalArgumentException("Invalid node type " + nodeType);
		}

		return nodeTypeDependentObject;
	}

	public T getNodeTypeDependentObjects(String nodeTypeString) {
		NodeType nodeType = NodeType.valueOf(nodeTypeString);

		return getNodeTypeDependentObjects(nodeType);
	}

	public T removeNodeTypeDependentObjects(String nodeTypeString) {
		NodeType nodeType = NodeType.valueOf(nodeTypeString);

		return _nodeTypeDependentObjects.remove(nodeType);
	}

	public void setNodeTypeDependentObjects(
		Map<String, T> nodeTypeDependentObjects) {

		for (Map.Entry<String, T> entry : nodeTypeDependentObjects.entrySet()) {
			NodeType nodeType = NodeType.valueOf(entry.getKey());

			_nodeTypeDependentObjects.put(nodeType, entry.getValue());
		}
	}

	private final Map<NodeType, T> _nodeTypeDependentObjects = new HashMap<>();

}