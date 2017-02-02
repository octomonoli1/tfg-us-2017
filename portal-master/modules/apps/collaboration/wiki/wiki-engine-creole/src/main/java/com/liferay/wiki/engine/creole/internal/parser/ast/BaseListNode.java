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

package com.liferay.wiki.engine.creole.internal.parser.ast;

/**
 * @author Miguel Pastor
 */
public abstract class BaseListNode extends BaseParentableNode {

	public BaseListNode() {
	}

	public BaseListNode(
		BaseParentableNode baseParentableNode, CollectionNode collectionNode) {

		super(collectionNode);

		_baseParentableNode = baseParentableNode;
	}

	public BaseListNode(int token) {
		super(token);
	}

	public BaseParentableNode getBaseParentableNode() {
		return _baseParentableNode;
	}

	public void setParent(BaseParentableNode baseParentableNode) {
		_baseParentableNode = baseParentableNode;
	}

	private BaseParentableNode _baseParentableNode;

}