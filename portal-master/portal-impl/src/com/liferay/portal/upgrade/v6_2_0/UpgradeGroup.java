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

package com.liferay.portal.upgrade.v6_2_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.upgrade.v6_2_0.util.GroupTable;

/**
 * @author Hugo Huijser
 */
public class UpgradeGroup extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(
			GroupTable.class, new AlterColumnType("typeSettings", "TEXT null"),
			new AlterColumnType("friendlyURL", "VARCHAR(255) null"));

		upgradeGroup();
	}

	protected void upgradeGroup() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			long classNameId = PortalUtil.getClassNameId(
				"com.liferay.portal.model.Company");

			runSQL(
				"update Group_ set site = TRUE where classNameId = " +
					classNameId);
		}
	}

}