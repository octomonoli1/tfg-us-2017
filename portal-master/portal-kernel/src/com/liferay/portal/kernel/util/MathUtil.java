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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.text.NumberFormat;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class MathUtil {

	public static int base2Log(long x) {
		return _base2LogValues.get(x);
	}

	public static long base2Pow(int x) {
		if (x == 0) {
			return 1;
		}
		else {
			return 2L << (x - 1);
		}
	}

	public static int difference(Integer value1, Integer value2) {
		return value1 - value2;
	}

	public static int factorial(int x) {
		if (x < 0) {
			return 0;
		}

		int factorial = 1;

		while (x > 1) {
			factorial = factorial * x;
			x = x - 1;
		}

		return factorial;
	}

	public static double format(double x, int max, int min) {
		NumberFormat nf = NumberFormat.getInstance();

		nf.setMaximumFractionDigits(max);
		nf.setMinimumFractionDigits(min);

		try {
			Number number = nf.parse(nf.format(x));

			x = number.doubleValue();
		}
		catch (Exception e) {
			_log.error(e.getMessage());
		}

		return x;
	}

	public static int[] generatePrimes(int max) {
		if (max < 2) {
			return new int[0];
		}

		boolean[] crossedOut = new boolean[max + 1];

		for (int i = 2; i < crossedOut.length; i++) {
			crossedOut[i] = false;
		}

		int limit = (int)Math.sqrt(crossedOut.length);

		for (int i = 2; i <= limit; i++) {
			if (!crossedOut[i]) {
				for (int multiple = 2 * i; multiple < crossedOut.length;
					multiple += i) {

					crossedOut[multiple] = true;
				}
			}
		}

		int uncrossedCount = 0;

		for (int i = 2; i < crossedOut.length; i++) {
			if (!crossedOut[i]) {
				uncrossedCount++;
			}
		}

		int[] result = new int[uncrossedCount];

		for (int i = 2, j = 0; i < crossedOut.length; i++) {
			if (!crossedOut[i]) {
				result[j++] = i;
			}
		}

		return result;
	}

	public static boolean isEven(int x) {
		if ((x % 2) == 0) {
			return true;
		}

		return false;
	}

	public static boolean isOdd(int x) {
		return !isEven(x);
	}

	public static double product(Double... values) {
		double product = 1.0;

		for (double value : values) {
			product *= value;
		}

		return product;
	}

	public static int product(Integer... values) {
		int product = 1;

		for (int value : values) {
			product *= value;
		}

		return product;
	}

	public static long product(Long... values) {
		long product = 1;

		for (long value : values) {
			product *= value;
		}

		return product;
	}

	public static int quotient(Integer value1, Integer value2) {
		return value1 / value2;
	}

	public static double sum(Double... values) {
		double sum = 0.0;

		for (double value : values) {
			sum += value;
		}

		return sum;
	}

	public static int sum(Integer... values) {
		int sum = 0;

		for (int value : values) {
			sum += value;
		}

		return sum;
	}

	public static long sum(Long... values) {
		long sum = 0;

		for (long value : values) {
			sum += value;
		}

		return sum;
	}

	private static final Log _log = LogFactoryUtil.getLog(MathUtil.class);

	private static final Map<Long, Integer> _base2LogValues = new HashMap<>();

	static {
		_base2LogValues.put(0L, Integer.MIN_VALUE);

		for (int i = 0; i < 63; i++) {
			_base2LogValues.put(base2Pow(i), i);
		}
	}

}