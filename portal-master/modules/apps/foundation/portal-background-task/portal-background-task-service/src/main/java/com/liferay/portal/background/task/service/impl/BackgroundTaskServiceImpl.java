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

package com.liferay.portal.background.task.service.impl;

import com.liferay.portal.background.task.service.base.BackgroundTaskServiceBaseImpl;

/**
 * @author Michael C. Han
 */
public class BackgroundTaskServiceImpl extends BackgroundTaskServiceBaseImpl {

	@Override
	public int getBackgroundTasksCount(
		long groupId, String taskExecutorClassName, String completed) {

		return backgroundTaskLocalService.getBackgroundTasksCount(
			groupId, taskExecutorClassName, completed);
	}

	@Override
	public String getBackgroundTaskStatusJSON(long backgroundTaskId) {
		return backgroundTaskLocalService.getBackgroundTaskStatusJSON(
			backgroundTaskId);
	}

}