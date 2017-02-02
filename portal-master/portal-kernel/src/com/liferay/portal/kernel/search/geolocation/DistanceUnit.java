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

package com.liferay.portal.kernel.search.geolocation;

/**
 * @author Michael C. Han
 */
public enum DistanceUnit {

	CENTIMETERS("cm"), FEET("ft"), INCHES("in"), KILOMETERS("km"), METERS("m"),
	MILES("mi"), MILLIMETERS("mm"), YARDS("yd");

	public String getUnit() {
		return _unit;
	}

	@Override
	public String toString() {
		return _unit;
	}

	private DistanceUnit(String unit) {
		_unit = unit;
	}

	private final String _unit;

}