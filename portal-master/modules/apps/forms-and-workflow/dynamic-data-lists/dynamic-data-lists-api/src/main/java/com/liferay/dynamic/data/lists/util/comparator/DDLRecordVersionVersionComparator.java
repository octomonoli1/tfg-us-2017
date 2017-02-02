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

package com.liferay.dynamic.data.lists.util.comparator;

import com.liferay.dynamic.data.lists.model.DDLRecordVersion;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Comparator;

/**
 * Used to order a list of record versions by version number. The order can be
 * ascending or descending and is defined by the value specified in the class
 * constructor.
 *
 * @author Marcellus Tavares
 */
public class DDLRecordVersionVersionComparator
	implements Comparator<DDLRecordVersion> {

	public DDLRecordVersionVersionComparator() {
		this(false);
	}

	public DDLRecordVersionVersionComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		DDLRecordVersion recordVersion1, DDLRecordVersion recordVersion2) {

		int value = 0;

		String version1 = recordVersion1.getVersion();
		String version2 = recordVersion2.getVersion();

		int[] versionParts1 = StringUtil.split(version1, StringPool.PERIOD, 0);
		int[] versionParts2 = StringUtil.split(version2, StringPool.PERIOD, 0);

		if ((versionParts1.length != 2) && (versionParts2.length != 2)) {
			value = 0;
		}
		else if (versionParts1.length != 2) {
			value = -1;
		}
		else if (versionParts2.length != 2) {
			value = 1;
		}
		else if (versionParts1[0] > versionParts2[0]) {
			value = 1;
		}
		else if (versionParts1[0] < versionParts2[0]) {
			value = -1;
		}
		else if (versionParts1[1] > versionParts2[1]) {
			value = 1;
		}
		else if (versionParts1[1] < versionParts2[1]) {
			value = -1;
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}