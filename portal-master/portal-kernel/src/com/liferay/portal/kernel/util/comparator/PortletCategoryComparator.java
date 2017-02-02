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

package com.liferay.portal.kernel.util.comparator;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.PortletCategory;

import java.io.Serializable;

import java.text.Collator;

import java.util.Comparator;
import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletCategoryComparator
	implements Comparator<PortletCategory>, Serializable {

	public PortletCategoryComparator(Locale locale) {
		_locale = locale;

		_collator = Collator.getInstance(_locale);
	}

	@Override
	public int compare(
		PortletCategory portletCategory1, PortletCategory portletCategory2) {

		String name1 = portletCategory1.getName();

		if (name1.equals("category.highlighted")) {
			return -1;
		}

		String name2 = portletCategory2.getName();

		if (name2.equals("category.highlighted")) {
			return 1;
		}

		name1 = LanguageUtil.get(_locale, name1);
		name2 = LanguageUtil.get(_locale, name2);

		return _collator.compare(name1, name2);
	}

	private final Collator _collator;
	private final Locale _locale;

}