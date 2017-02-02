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

package com.liferay.portal.kernel.log;

/**
 * @author Tomas Polesovsky
 */
public class LogSanitizerException extends Exception {

	public LogSanitizerException() {
	}

	public LogSanitizerException(String message) {
		super(message);
	}

	public LogSanitizerException(
		String message, StackTraceElement[] stackTraceElements,
		Throwable throwable) {

		super(message, throwable);

		setStackTrace(stackTraceElements);
	}

	public LogSanitizerException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public LogSanitizerException(Throwable throwable) {
		super(throwable);
	}

	@Override
	public String toString() {
		Class<?> clazz = getClass();

		String className = clazz.getName();

		String localizedMessage = getLocalizedMessage();

		if (localizedMessage != null) {
			return localizedMessage;
		}

		return className;
	}

}