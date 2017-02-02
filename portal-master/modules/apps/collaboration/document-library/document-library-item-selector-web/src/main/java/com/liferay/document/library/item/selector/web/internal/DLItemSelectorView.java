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

package com.liferay.document.library.item.selector.web.internal;

import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.item.selector.ItemSelectorView;

/**
 * @author Roberto Díaz
 */
public interface DLItemSelectorView<T extends ItemSelectorCriterion>
	extends ItemSelectorView<T> {

	public static final String DL_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT =
		"DL_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT";

	public String[] getMimeTypes();

}