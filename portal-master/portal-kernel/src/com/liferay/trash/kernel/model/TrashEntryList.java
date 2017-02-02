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

package com.liferay.trash.kernel.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class TrashEntryList implements Serializable {

	public TrashEntryList() {
	}

	public TrashEntrySoap[] getArray() {
		return _array;
	}

	public int getCount() {
		return _count;
	}

	public boolean isApproximate() {
		return _approximate;
	}

	public void setApproximate(boolean approximate) {
		_approximate = approximate;
	}

	public void setArray(TrashEntrySoap[] array) {
		_array = array;
	}

	public void setCount(int count) {
		_count = count;
	}

	private boolean _approximate;
	private TrashEntrySoap[] _array;
	private int _count;

}