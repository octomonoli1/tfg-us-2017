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

package com.liferay.social.kernel.util;

import com.liferay.social.kernel.model.SocialActivityDefinition;

import java.util.List;

/**
 * @author Zsolt Berentey
 */
public interface SocialConfiguration {

	public List<String> getActivityCounterNames();

	public List<String> getActivityCounterNames(boolean transientCounter);

	public List<String> getActivityCounterNames(int ownerType);

	public List<String> getActivityCounterNames(
		int ownerType, boolean transientCounter);

	public SocialActivityDefinition getActivityDefinition(
		String modelName, int activityType);

	public List<SocialActivityDefinition> getActivityDefinitions(
		String modelName);

	public String[] getActivityModelNames();

	public List<Object> read(ClassLoader classLoader, String[] xmls)
		throws Exception;

	public void removeActivityDefinition(
		SocialActivityDefinition activityDefinition);

}