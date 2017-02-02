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

package com.liferay.gradle.plugins.test.integration.tasks;

import java.util.concurrent.Callable;

import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import org.zeroturnaround.exec.ProcessExecutor;

/**
 * @author Andrea Di Giorgi
 */
public class StopAppServerTask extends BaseAppServerTask {

	@Input
	public long getAdditionalWaitTime() {
		return _additionalWaitTime;
	}

	public void setAdditionalWaitTime(long additionalWaitTime) {
		_additionalWaitTime = additionalWaitTime;
	}

	@TaskAction
	public void stopAppServer() throws Exception {
		if (!isReachable()) {
			return;
		}

		ProcessExecutor processExecutor = getProcessExecutor();

		processExecutor.executeNoTimeout();

		waitFor(
			new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return !isReachable();
				}

			});

		long additionalWaitTime = getAdditionalWaitTime();

		if (additionalWaitTime > 0) {
			Thread.sleep(additionalWaitTime);
		}
	}

	private long _additionalWaitTime = 2000;

}