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

package com.liferay.portal.workflow.kaleo.internal.model.upgrade;

import com.liferay.portal.kernel.model.ServiceComponent;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoTaskAssignmentUpgradeTableListener
	extends BaseKaleoUpgradeTableListener {

	@Override
	public void onAfterUpdateTable(
			ServiceComponent previousServiceComponent,
			UpgradeTable upgradeTable)
		throws Exception {

		if (_keyValueMap == null) {
			return;
		}

		Map<Long, Long> keyValueMap = _keyValueMap;

		_keyValueMap = null;

		updateKeyValueMap(
			keyValueMap, "com.liferay.portal.workflow.kaleo.model.KaleoTask",
			"KaleoTaskAssignment", "kaleoTaskAssignmentId");
	}

	@Override
	public void onBeforeUpdateTable(
			ServiceComponent previousServiceComponent,
			UpgradeTable upgradeTable)
		throws Exception {

		if (!isFixAutoUpgrade(previousServiceComponent)) {
			return;
		}

		_keyValueMap = getKeyValueMap(
			"KaleoTaskAssignment", "kaleoTaskAssignmentId", "kaleoTaskId");
	}

	private Map<Long, Long> _keyValueMap;

}