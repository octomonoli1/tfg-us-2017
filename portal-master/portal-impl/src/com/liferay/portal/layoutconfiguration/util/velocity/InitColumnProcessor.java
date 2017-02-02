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

package com.liferay.portal.layoutconfiguration.util.velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 */
public class InitColumnProcessor {

	public InitColumnProcessor() {
		_columns = new ArrayList<>();
	}

	public List<String> getColumns() {
		return _columns;
	}

	public void processColumn(String columnId) {
		_columns.add(columnId);
	}

	public void processColumn(String columnId, String classNames) {
		_columns.add(columnId);
	}

	private final List<String> _columns;

}