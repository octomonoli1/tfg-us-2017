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

package com.liferay.notifications.web.internal.util.comparator;

import com.liferay.portal.kernel.util.PortalUtil;

import java.text.Collator;

import java.util.Comparator;
import java.util.Locale;

/**
 * @author Matthew Kong
 */
public class PortletIdComparator implements Comparator<String> {

	public PortletIdComparator(Locale locale) {
		_locale = locale;

		_collator = Collator.getInstance(_locale);
	}

	@Override
	public int compare(String portletId1, String portletId2) {
		String portletTitle1 = PortalUtil.getPortletTitle(portletId1, _locale);
		String portletTitle2 = PortalUtil.getPortletTitle(portletId2, _locale);

		return _collator.compare(portletTitle1, portletTitle2);
	}

	private final Collator _collator;
	private final Locale _locale;

}