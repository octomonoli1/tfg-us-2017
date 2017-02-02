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

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.v7_0_0.util.UserNotificationEventTable;

/**
 * @author Adolfo Pérez
 */
public class UpgradeUserNotificationEvent extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {

		// Check the column type because this class is also used in
		// UpgradeProcess_7_0_1

		if (hasColumnType(
				UserNotificationEventTable.class, "type_",
				"VARCHAR(200) null")) {

			return;
		}

		alter(
			UserNotificationEventTable.class,
			new AlterColumnType("type_", "VARCHAR(200) null"));
	}

}