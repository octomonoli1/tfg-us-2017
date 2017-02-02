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

package com.liferay.social.networking.internal.upgrade.v1_0_0.util;

import java.sql.Types;

/**
 * @author Brian Wing Shun Chan
 */
public class WallEntryTable {

	public static final Object[][] TABLE_COLUMNS = {
		{"wallEntryId", Integer.valueOf(Types.BIGINT)},
		{"groupId", Integer.valueOf(Types.BIGINT)},
		{"companyId", Integer.valueOf(Types.BIGINT)},
		{"userId", Integer.valueOf(Types.BIGINT)},
		{"userName", Integer.valueOf(Types.VARCHAR)},
		{"createDate", Integer.valueOf(Types.TIMESTAMP)},
		{"modifiedDate", Integer.valueOf(Types.TIMESTAMP)},
		{"comments", Integer.valueOf(Types.VARCHAR)}
	};

	public static final String TABLE_NAME = "SN_WallEntry";

	public static final String TABLE_SQL_CREATE = "create table SN_WallEntry (wallEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,comments STRING null)";

	public static final String TABLE_SQL_DROP = "drop table SN_WallEntry";

}