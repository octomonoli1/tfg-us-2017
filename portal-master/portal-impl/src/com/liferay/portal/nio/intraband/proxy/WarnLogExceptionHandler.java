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

package com.liferay.portal.nio.intraband.proxy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.nio.intraband.proxy.ExceptionHandler;

/**
 * @author Shuyang Zhou
 */
public class WarnLogExceptionHandler implements ExceptionHandler {

	public static final WarnLogExceptionHandler INSTANCE =
		new WarnLogExceptionHandler();

	@Override
	public void onException(Exception e) {
		if (_log.isWarnEnabled()) {
			_log.warn(e, e);
		}
	}

	private WarnLogExceptionHandler() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WarnLogExceptionHandler.class);

}