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

package com.liferay.portal.upgrade.util;

import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.upgrade.util.UpgradeColumn;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactory;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class DefaultUpgradeTableFactoryImpl implements UpgradeTableFactory {

	@Override
	public UpgradeTable getUpgradeTable(
		String tableName, Object[][] columns, UpgradeColumn... upgradeColumns) {

		return new DefaultUpgradeTableImpl(tableName, columns, upgradeColumns);
	}

}