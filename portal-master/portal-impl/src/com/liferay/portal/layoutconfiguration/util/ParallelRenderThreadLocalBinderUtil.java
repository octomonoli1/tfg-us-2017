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

package com.liferay.portal.layoutconfiguration.util;

import com.liferay.portal.kernel.util.ThreadLocalBinder;

/**
 * @author Shuyang Zhou
 */
public class ParallelRenderThreadLocalBinderUtil {

	public static void bind() {
		_threadLocalBinder.bind();
	}

	public static void cleanUp() {
		_threadLocalBinder.cleanUp();
	}

	public static ThreadLocalBinder getThreadLocalBinder() {
		return _threadLocalBinder;
	}

	public static void record() {
		_threadLocalBinder.record();
	}

	public static void setThreadLocalBinder(
		ThreadLocalBinder threadLocalBinder) {

		_threadLocalBinder = threadLocalBinder;
	}

	private static ThreadLocalBinder _threadLocalBinder;

}