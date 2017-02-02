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

package com.liferay.message.boards.kernel.model;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Alexander Chow
 * @author Juan Fernández
 */
public class MBMessageConstants {

	public static final String DEFAULT_FORMAT = PropsUtil.get(
		PropsKeys.MESSAGE_BOARDS_MESSAGE_FORMATS_DEFAULT);

	public static final long DEFAULT_PARENT_MESSAGE_ID = 0;

	public static final String[] FORMATS = PropsUtil.getArray(
		PropsKeys.MESSAGE_BOARDS_MESSAGE_FORMATS);

}