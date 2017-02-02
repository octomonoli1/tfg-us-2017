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

package com.liferay.portlet;

import com.liferay.portal.kernel.model.PortletInfo;
import com.liferay.portal.kernel.util.JavaConstants;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 * @author Shuyang Zhou
 */
public class PortletResourceBundle extends ResourceBundle {

	public PortletResourceBundle(PortletInfo portletInfo) {
		this(null, portletInfo);
	}

	public PortletResourceBundle(
		ResourceBundle parentResourceBundle, PortletInfo portletInfo) {

		parent = parentResourceBundle;

		String description = portletInfo.getDescription();

		if (description != null) {
			_portletInfos.put(
				JavaConstants.JAVAX_PORTLET_DESCRIPTION, description);
		}

		String keywords = portletInfo.getKeywords();

		if (keywords != null) {
			_portletInfos.put(JavaConstants.JAVAX_PORTLET_KEYWORDS, keywords);
		}

		String shortTitle = portletInfo.getShortTitle();

		if (shortTitle != null) {
			_portletInfos.put(
				JavaConstants.JAVAX_PORTLET_SHORT_TITLE, shortTitle);
		}

		String title = portletInfo.getTitle();

		if (title != null) {
			_portletInfos.put(JavaConstants.JAVAX_PORTLET_TITLE, title);
		}
	}

	@Override
	public Enumeration<String> getKeys() {
		if (parent == null) {
			return Collections.enumeration(_portletInfos.keySet());
		}

		Set<String> keys = new HashSet<>(parent.keySet());

		keys.addAll(_portletInfos.keySet());

		return Collections.enumeration(keys);
	}

	@Override
	public Locale getLocale() {
		if (parent == null) {
			return null;
		}

		return parent.getLocale();
	}

	@Override
	protected Object handleGetObject(String key) {
		if (key == null) {
			throw new NullPointerException();
		}

		if ((parent != null) && parent.containsKey(key)) {
			return parent.getString(key);
		}

		return _portletInfos.get(key);
	}

	@Override
	protected Set<String> handleKeySet() {
		return _portletInfos.keySet();
	}

	private final Map<String, String> _portletInfos = new HashMap<>();

}