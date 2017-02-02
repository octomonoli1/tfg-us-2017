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

package com.liferay.dynamic.data.mapping.util.comparator;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Eduardo Garcia
 */
public class StructureIdComparator extends OrderByComparator<DDMStructure> {

	public static final String ORDER_BY_ASC = "DDMStructure.structureId ASC";

	public static final String ORDER_BY_DESC = "DDMStructure.structureId DESC";

	public static final String[] ORDER_BY_FIELDS = {"structureId"};

	public StructureIdComparator() {
		this(false);
	}

	public StructureIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(DDMStructure structure1, DDMStructure structure2) {
		long structureId1 = structure1.getStructureId();
		long structureId2 = structure2.getStructureId();

		int value = 0;

		if (structureId1 < structureId2) {
			value = -1;
		}
		else if (structureId1 > structureId2) {
			value = 1;
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}