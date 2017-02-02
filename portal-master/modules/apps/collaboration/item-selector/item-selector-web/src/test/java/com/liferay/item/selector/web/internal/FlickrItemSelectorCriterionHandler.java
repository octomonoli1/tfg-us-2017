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

package com.liferay.item.selector.web.internal;

import com.liferay.item.selector.ItemSelectorCriterionHandler;
import com.liferay.item.selector.ItemSelectorView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Iván Zaera
 */
public class FlickrItemSelectorCriterionHandler
	implements ItemSelectorCriterionHandler<FlickrItemSelectorCriterion> {

	@Override
	public Class<FlickrItemSelectorCriterion> getItemSelectorCriterionClass() {
		return FlickrItemSelectorCriterion.class;
	}

	@Override
	public List<ItemSelectorView<FlickrItemSelectorCriterion>>
		getItemSelectorViews(
			FlickrItemSelectorCriterion flickrItemSelectorCriterion) {

		List<ItemSelectorView<FlickrItemSelectorCriterion>> itemSelectorViews =
			new ArrayList<>();

		itemSelectorViews.add(new FlickrItemSelectorView());

		return itemSelectorViews;
	}

}