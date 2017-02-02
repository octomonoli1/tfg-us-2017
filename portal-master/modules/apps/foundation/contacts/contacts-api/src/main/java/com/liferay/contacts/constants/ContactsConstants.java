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

package com.liferay.contacts.constants;

/**
 * @author Jonathan Lee
 */
public class ContactsConstants {

	public static final int DISPLAY_STYLE_BASIC = 1;

	public static final String DISPLAY_STYLE_BASIC_LABEL = "basic";

	public static final int DISPLAY_STYLE_DETAIL = 2;

	public static final String DISPLAY_STYLE_DETAIL_LABEL = "detail";

	public static final int DISPLAY_STYLE_FULL = 3;

	public static final String DISPLAY_STYLE_FULL_LABEL = "full";

	public static final String FILTER_BY_ADMINS = "admins";

	public static final String FILTER_BY_DEFAULT = "default";

	public static final String FILTER_BY_FOLLOWERS = "followers";

	public static final String FILTER_BY_GROUP = "group_";

	public static final String FILTER_BY_TYPE = "type_";

	public static final String FILTER_BY_TYPE_BI_CONNECTION =
		FILTER_BY_TYPE + SocialRelationConstants.TYPE_BI_CONNECTION;

	public static final String FILTER_BY_TYPE_MY_CONTACTS =
		FILTER_BY_TYPE + SocialRelationConstants.TYPE_MY_CONTACTS;

	public static final String FILTER_BY_TYPE_UNI_FOLLOWER =
		FILTER_BY_TYPE + SocialRelationConstants.TYPE_UNI_FOLLOWER;

	public static final int MAX_RESULT_COUNT = 100;

	public static String getDisplayStyleLabel(int displayStyle) {
		if (displayStyle == DISPLAY_STYLE_BASIC) {
			return DISPLAY_STYLE_BASIC_LABEL;
		}
		else if (displayStyle == DISPLAY_STYLE_DETAIL) {
			return DISPLAY_STYLE_DETAIL_LABEL;
		}
		else if (displayStyle == DISPLAY_STYLE_FULL) {
			return DISPLAY_STYLE_FULL_LABEL;
		}
		else {
			return null;
		}
	}

}