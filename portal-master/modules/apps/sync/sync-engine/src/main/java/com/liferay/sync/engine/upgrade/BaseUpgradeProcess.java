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

package com.liferay.sync.engine.upgrade;

import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.persistence.SyncAccountPersistence;

/**
 * @author Shinn Lok
 */
public abstract class BaseUpgradeProcess extends UpgradeProcess {

	@Override
	public void upgrade() throws Exception {
	}

	@Override
	public void upgradeSchema() throws Exception {
	}

	protected void runSQL(String sql) throws Exception {
		SyncAccountPersistence syncAccountPersistence =
			SyncAccountService.getSyncAccountPersistence();

		syncAccountPersistence.executeRaw(sql);
	}

}