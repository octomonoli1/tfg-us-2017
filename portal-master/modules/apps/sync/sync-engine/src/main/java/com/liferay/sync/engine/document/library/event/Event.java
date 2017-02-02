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

package com.liferay.sync.engine.document.library.event;

import com.liferay.sync.engine.document.library.handler.Handler;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public interface Event extends Runnable {

	public void cancel();

	public Handler<Void> getHandler();

	public Map<String, Object> getParameters();

	public Object getParameterValue(String key);

	public long getSyncAccountId();

	public String getURLPath();

	public boolean isCancelled();

}