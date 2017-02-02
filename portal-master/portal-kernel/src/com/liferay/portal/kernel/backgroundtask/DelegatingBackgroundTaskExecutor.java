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

package com.liferay.portal.kernel.backgroundtask;

import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;

/**
 * @author Michael C. Han
 */
public class DelegatingBackgroundTaskExecutor
	implements BackgroundTaskExecutor {

	public DelegatingBackgroundTaskExecutor(
		BackgroundTaskExecutor backgroundTaskExecutor) {

		_backgroundTaskExecutor = backgroundTaskExecutor;
	}

	@Override
	public BackgroundTaskExecutor clone() {
		BackgroundTaskExecutor backgroundTaskExecutor =
			new DelegatingBackgroundTaskExecutor(getBackgroundTaskExecutor());

		return backgroundTaskExecutor;
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		return _backgroundTaskExecutor.execute(backgroundTask);
	}

	@Override
	public String generateLockKey(BackgroundTask backgroundTask) {
		return _backgroundTaskExecutor.generateLockKey(backgroundTask);
	}

	@Override
	public BackgroundTaskDisplay getBackgroundTaskDisplay(
		BackgroundTask backgroundTask) {

		return _backgroundTaskExecutor.getBackgroundTaskDisplay(backgroundTask);
	}

	@Override
	public BackgroundTaskStatusMessageTranslator
		getBackgroundTaskStatusMessageTranslator() {

		return _backgroundTaskExecutor.
			getBackgroundTaskStatusMessageTranslator();
	}

	@Override
	public int getIsolationLevel() {
		return _backgroundTaskExecutor.getIsolationLevel();
	}

	@Override
	public String handleException(BackgroundTask backgroundTask, Exception e) {
		return _backgroundTaskExecutor.handleException(backgroundTask, e);
	}

	@Override
	public boolean isSerial() {
		return _backgroundTaskExecutor.isSerial();
	}

	protected BackgroundTaskExecutor getBackgroundTaskExecutor() {
		return _backgroundTaskExecutor;
	}

	private final BackgroundTaskExecutor _backgroundTaskExecutor;

}