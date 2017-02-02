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

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.lang.reflect.Array;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Brian Wing Shun Chan
 */
public class ArrayUtil {

	public static boolean[] append(boolean[]... arrays) {
		int length = 0;

		for (boolean[] array : arrays) {
			length += array.length;
		}

		boolean[] newArray = new boolean[length];

		int previousLength = 0;

		for (boolean[] array : arrays) {
			System.arraycopy(array, 0, newArray, previousLength, array.length);

			previousLength += array.length;
		}

		return newArray;
	}

	public static boolean[] append(boolean[] array, boolean value) {
		boolean[] newArray = new boolean[array.length + 1];

		System.arraycopy(array, 0, newArray, 0, array.length);

		newArray[newArray.length - 1] = value;

		return newArray;
	}

	public static byte[] append(byte[]... arrays) {
		int length = 0;

		for (byte[] array : arrays) {
			length += array.length;
		}

		byte[] newArray = new byte[length];

		int previousLength = 0;

		for (byte[] array : arrays) {
			System.arraycopy(array, 0, newArray, previousLength, array.length);

			previousLength += array.length;
		}

		return newArray;
	}

	public static byte[] append(byte[] array, byte value) {
		byte[] newArray = new byte[array.length + 1];

		System.arraycopy(array, 0, newArray, 0, array.length);

		newArray[newArray.length - 1] = value;

		return newArray;
	}

	public static char[] append(char[]... arrays) {
		int length = 0;

		for (char[] array : arrays) {
			length += array.length;
		}

		char[] newArray = new char[length];

		int previousLength = 0;

		for (char[] array : arrays) {
			System.arraycopy(array, 0, newArray, previousLength, array.length);

			previousLength += array.length;
		}

		return newArray;
	}

	public static char[] append(char[] array, char value) {
		char[] newArray = new char[array.length + 1];

		System.arraycopy(array, 0, newArray, 0, array.length);

		newArray[newArray.length - 1] = value;

		return newArray;
	}

	public static double[] append(double[]... arrays) {
		int length = 0;

		for (double[] array : arrays) {
			length += array.length;
		}

		double[] newArray = new double[length];

		int previousLength = 0;

		for (double[] array : arrays) {
			System.arraycopy(array, 0, newArray, previousLength, array.length);

			previousLength += array.length;
		}

		return newArray;
	}

	public static double[] append(double[] array, double value) {
		double[] newArray = new double[array.length + 1];

		System.arraycopy(array, 0, newArray, 0, array.length);

		newArray[newArray.length - 1] = value;

		return newArray;
	}

	public static float[] append(float[]... arrays) {
		int length = 0;

		for (float[] array : arrays) {
			length += array.length;
		}

		float[] newArray = new float[length];

		int previousLength = 0;

		for (float[] array : arrays) {
			System.arraycopy(array, 0, newArray, previousLength, array.length);

			previousLength += array.length;
		}

		return newArray;
	}

	public static float[] append(float[] array, float value) {
		float[] newArray = new float[array.length + 1];

		System.arraycopy(array, 0, newArray, 0, array.length);

		newArray[newArray.length - 1] = value;

		return newArray;
	}

	public static int[] append(int[]... arrays) {
		int length = 0;

		for (int[] array : arrays) {
			length += array.length;
		}

		int[] newArray = new int[length];

		int previousLength = 0;

		for (int[] array : arrays) {
			System.arraycopy(array, 0, newArray, previousLength, array.length);

			previousLength += array.length;
		}

		return newArray;
	}

	public static int[] append(int[] array, int value) {
		int[] newArray = new int[array.length + 1];

		System.arraycopy(array, 0, newArray, 0, array.length);

		newArray[newArray.length - 1] = value;

		return newArray;
	}

	public static long[] append(long[]... arrays) {
		int length = 0;

		for (long[] array : arrays) {
			length += array.length;
		}

		long[] newArray = new long[length];

		int previousLength = 0;

		for (long[] array : arrays) {
			System.arraycopy(array, 0, newArray, previousLength, array.length);

			previousLength += array.length;
		}

		return newArray;
	}

	public static long[] append(long[] array, long value) {
		long[] newArray = new long[array.length + 1];

		System.arraycopy(array, 0, newArray, 0, array.length);

		newArray[newArray.length - 1] = value;

		return newArray;
	}

	public static short[] append(short[]... arrays) {
		int length = 0;

		for (short[] array : arrays) {
			length += array.length;
		}

		short[] newArray = new short[length];

		int previousLength = 0;

		for (short[] array : arrays) {
			System.arraycopy(array, 0, newArray, previousLength, array.length);

			previousLength += array.length;
		}

		return newArray;
	}

	public static short[] append(short[] array, short value) {
		short[] newArray = new short[array.length + 1];

		System.arraycopy(array, 0, newArray, 0, array.length);

		newArray[newArray.length - 1] = value;

		return newArray;
	}

	public static <T> T[] append(T[]... arrays) {
		int length = 0;

		for (T[] array : arrays) {
			length += array.length;
		}

		Class<?> arraysClass = arrays[0].getClass();

		T[] newArray = (T[])Array.newInstance(
			arraysClass.getComponentType(), length);

		int previousLength = 0;

		for (T[] array : arrays) {
			System.arraycopy(array, 0, newArray, previousLength, array.length);

			previousLength += array.length;
		}

		return newArray;
	}

	public static <T> T[] append(T[] array, T value) {
		Class<?> arrayClass = array.getClass();

		T[] newArray = (T[])Array.newInstance(
			arrayClass.getComponentType(), array.length + 1);

		System.arraycopy(array, 0, newArray, 0, array.length);

		newArray[array.length] = value;

		return newArray;
	}

	public static <T> T[] append(T[] array1, T[] array2) {
		Class<?> array1Class = array1.getClass();

		T[] newArray = (T[])Array.newInstance(
			array1Class.getComponentType(), array1.length + array2.length);

		System.arraycopy(array1, 0, newArray, 0, array1.length);

		System.arraycopy(array2, 0, newArray, array1.length, array2.length);

		return newArray;
	}

	public static <T> T[][] append(T[][] array1, T[] value) {
		Class<?> array1Class = array1.getClass();

		T[][] newArray = (T[][])Array.newInstance(
			array1Class.getComponentType(), array1.length + 1);

		System.arraycopy(array1, 0, newArray, 0, array1.length);

		newArray[array1.length] = value;

		return newArray;
	}

	public static <T> T[][] append(T[][] array1, T[][] array2) {
		Class<?> array1Class = array1.getClass();

		T[][] newArray = (T[][])Array.newInstance(
			array1Class.getComponentType(), array1.length + array2.length);

		System.arraycopy(array1, 0, newArray, 0, array1.length);
		System.arraycopy(array2, 0, newArray, array1.length, array2.length);

		return newArray;
	}

	public static boolean[] clone(boolean[] array) {
		boolean[] newArray = new boolean[array.length];

		System.arraycopy(array, 0, newArray, 0, array.length);

		return newArray;
	}

	public static boolean[] clone(boolean[] array, int from, int to) {
		boolean[] newArray = new boolean[to - from];

		System.arraycopy(
			array, from, newArray, 0,
			Math.min(array.length - from, newArray.length));

		return newArray;
	}

	public static byte[] clone(byte[] array) {
		byte[] newArray = new byte[array.length];

		System.arraycopy(array, 0, newArray, 0, array.length);

		return newArray;
	}

	public static byte[] clone(byte[] array, int from, int to) {
		byte[] newArray = new byte[to - from];

		System.arraycopy(
			array, from, newArray, 0,
			Math.min(array.length - from, newArray.length));

		return newArray;
	}

	public static char[] clone(char[] array) {
		char[] newArray = new char[array.length];

		System.arraycopy(array, 0, newArray, 0, array.length);

		return newArray;
	}

	public static char[] clone(char[] array, int from, int to) {
		char[] newArray = new char[to - from];

		System.arraycopy(
			array, from, newArray, 0,
			Math.min(array.length - from, newArray.length));

		return newArray;
	}

	public static double[] clone(double[] array) {
		double[] newArray = new double[array.length];

		System.arraycopy(array, 0, newArray, 0, array.length);

		return newArray;
	}

	public static double[] clone(double[] array, int from, int to) {
		double[] newArray = new double[to - from];

		System.arraycopy(
			array, from, newArray, 0,
			Math.min(array.length - from, newArray.length));

		return newArray;
	}

	public static float[] clone(float[] array) {
		float[] newArray = new float[array.length];

		System.arraycopy(array, 0, newArray, 0, array.length);

		return newArray;
	}

	public static float[] clone(float[] array, int from, int to) {
		float[] newArray = new float[to - from];

		System.arraycopy(
			array, from, newArray, 0,
			Math.min(array.length - from, newArray.length));

		return newArray;
	}

	public static int[] clone(int[] array) {
		int[] newArray = new int[array.length];

		System.arraycopy(array, 0, newArray, 0, array.length);

		return newArray;
	}

	public static int[] clone(int[] array, int from, int to) {
		int[] newArray = new int[to - from];

		System.arraycopy(
			array, from, newArray, 0,
			Math.min(array.length - from, newArray.length));

		return newArray;
	}

	public static long[] clone(long[] array) {
		long[] newArray = new long[array.length];

		System.arraycopy(array, 0, newArray, 0, array.length);

		return newArray;
	}

	public static long[] clone(long[] array, int from, int to) {
		long[] newArray = new long[to - from];

		System.arraycopy(
			array, from, newArray, 0,
			Math.min(array.length - from, newArray.length));

		return newArray;
	}

	public static short[] clone(short[] array) {
		short[] newArray = new short[array.length];

		System.arraycopy(array, 0, newArray, 0, array.length);

		return newArray;
	}

	public static short[] clone(short[] array, int from, int to) {
		short[] newArray = new short[to - from];

		System.arraycopy(
			array, from, newArray, 0,
			Math.min(array.length - from, newArray.length));

		return newArray;
	}

	public static <T> T[] clone(T[] array) {
		Class<?> arrayClass = array.getClass();

		T[] newArray = (T[])Array.newInstance(
			arrayClass.getComponentType(), array.length);

		System.arraycopy(array, 0, newArray, 0, array.length);

		return newArray;
	}

	public static <T> T[] clone(T[] array, int from, int to) {
		Class<?> arrayClass = array.getClass();

		T[] newArray = (T[])Array.newInstance(
			arrayClass.getComponentType(), to - from);

		System.arraycopy(
			array, from, newArray, 0,
			Math.min(array.length - from, newArray.length));

		return newArray;
	}

	public static <T> T[][] clone(T[][] array) {
		Class<?> arrayClass = array.getClass();

		T[][] newArray = (T[][])Array.newInstance(
			arrayClass.getComponentType(), array.length);

		System.arraycopy(array, 0, newArray, 0, array.length);

		return newArray;
	}

	public static <T> T[][] clone(T[][] array, int from, int to) {
		Class<?> arrayClass = array.getClass();

		T[][] newArray = (T[][])Array.newInstance(
			arrayClass.getComponentType(), to - from);

		System.arraycopy(
			array, from, newArray, 0,
			Math.min(array.length - from, newArray.length));

		return newArray;
	}

	public static void combine(
		Object[] array1, Object[] array2, Object[] combinedArray) {

		System.arraycopy(array1, 0, combinedArray, 0, array1.length);

		System.arraycopy(
			array2, 0, combinedArray, array1.length, array2.length);
	}

	public static boolean contains(boolean[] array, boolean value) {
		if (isEmpty(array)) {
			return false;
		}

		for (int i = 0; i < array.length; i++) {
			if (value == array[i]) {
				return true;
			}
		}

		return false;
	}

	public static boolean contains(byte[] array, byte value) {
		if (isEmpty(array)) {
			return false;
		}

		for (int i = 0; i < array.length; i++) {
			if (value == array[i]) {
				return true;
			}
		}

		return false;
	}

	public static boolean contains(char[] array, char value) {
		if (isEmpty(array)) {
			return false;
		}

		for (int i = 0; i < array.length; i++) {
			if (value == array[i]) {
				return true;
			}
		}

		return false;
	}

	public static boolean contains(double[] array, double value) {
		if (isEmpty(array)) {
			return false;
		}

		for (int i = 0; i < array.length; i++) {
			if (value == array[i]) {
				return true;
			}
		}

		return false;
	}

	public static boolean contains(float[] array, float value) {
		if (isEmpty(array)) {
			return false;
		}

		for (int i = 0; i < array.length; i++) {
			if (value == array[i]) {
				return true;
			}
		}

		return false;
	}

	public static boolean contains(int[] array, int value) {
		if (isEmpty(array)) {
			return false;
		}

		for (int i = 0; i < array.length; i++) {
			if (value == array[i]) {
				return true;
			}
		}

		return false;
	}

	public static boolean contains(long[] array, long value) {
		if (isEmpty(array)) {
			return false;
		}

		for (int i = 0; i < array.length; i++) {
			if (value == array[i]) {
				return true;
			}
		}

		return false;
	}

	public static boolean contains(Object[] array, Object value) {
		if (isEmpty(array)) {
			return false;
		}

		for (int i = 0; i < array.length; i++) {
			if (Objects.equals(value, array[i])) {
				return true;
			}
		}

		return false;
	}

	public static boolean contains(short[] array, short value) {
		if (isEmpty(array)) {
			return false;
		}

		for (int i = 0; i < array.length; i++) {
			if (value == array[i]) {
				return true;
			}
		}

		return false;
	}

	public static boolean contains(
		String[] array, String value, boolean ignoreCase) {

		if (isEmpty(array)) {
			return false;
		}

		for (int i = 0; i < array.length; i++) {
			if (ignoreCase) {
				if (StringUtil.equalsIgnoreCase(array[i], value)) {
					return true;
				}
			}
			else {
				if (Objects.equals(array[i], value)) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean containsAll(boolean[] array1, boolean[] array2) {
		if (isEmpty(array1) || isEmpty(array2)) {
			return false;
		}

		for (int i = 0; i < array2.length; i++) {
			if (!contains(array1, array2[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean containsAll(byte[] array1, byte[] array2) {
		if (isEmpty(array1) || isEmpty(array2)) {
			return false;
		}

		for (int i = 0; i < array2.length; i++) {
			if (!contains(array1, array2[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean containsAll(char[] array1, char[] array2) {
		if (isEmpty(array1) || isEmpty(array2)) {
			return false;
		}

		for (int i = 0; i < array2.length; i++) {
			if (!contains(array1, array2[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean containsAll(double[] array1, double[] array2) {
		if (isEmpty(array1) || isEmpty(array2)) {
			return false;
		}

		for (int i = 0; i < array2.length; i++) {
			if (!contains(array1, array2[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean containsAll(float[] array1, float[] array2) {
		if (isEmpty(array1) || isEmpty(array2)) {
			return false;
		}

		for (int i = 0; i < array2.length; i++) {
			if (!contains(array1, array2[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean containsAll(int[] array1, int[] array2) {
		if (isEmpty(array1) || isEmpty(array2)) {
			return false;
		}

		for (int i = 0; i < array2.length; i++) {
			if (!contains(array1, array2[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean containsAll(long[] array1, long[] array2) {
		if (isEmpty(array1) || isEmpty(array2)) {
			return false;
		}

		for (int i = 0; i < array2.length; i++) {
			if (!contains(array1, array2[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean containsAll(Object[] array1, Object[] array2) {
		if (isEmpty(array1) || isEmpty(array2)) {
			return false;
		}

		for (int i = 0; i < array2.length; i++) {
			if (!contains(array1, array2[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean containsAll(short[] array1, short[] array2) {
		if (isEmpty(array1) || isEmpty(array2)) {
			return false;
		}

		for (int i = 0; i < array2.length; i++) {
			if (!contains(array1, array2[i])) {
				return false;
			}
		}

		return true;
	}

	public static <T> int count(T[] array, PredicateFilter<T> predicateFilter) {
		if (isEmpty(array)) {
			return 0;
		}

		int count = 0;

		for (T t : array) {
			if (predicateFilter.filter(t)) {
				count++;
			}
		}

		return count;
	}

	public static String[] distinct(String[] array) {
		return distinct(array, null);
	}

	public static String[] distinct(
		String[] array, Comparator<String> comparator) {

		if (isEmpty(array)) {
			return array;
		}

		Set<String> set = null;

		if (comparator == null) {
			set = new TreeSet<>();
		}
		else {
			set = new TreeSet<>(comparator);
		}

		for (String s : array) {
			set.add(s);
		}

		return set.toArray(new String[set.size()]);
	}

	public static <T> boolean exists(
		T[] array, PredicateFilter<T> predicateFilter) {

		if (isEmpty(array)) {
			return false;
		}

		for (T t : array) {
			if (predicateFilter.filter(t)) {
				return true;
			}
		}

		return false;
	}

	public static boolean[] filter(
		boolean[] array, PredicateFilter<Boolean> predicateFilter) {

		if (isEmpty(array)) {
			return array;
		}

		List<Boolean> filteredList = new ArrayList<>();

		for (boolean b : array) {
			if (predicateFilter.filter(b)) {
				filteredList.add(b);
			}
		}

		return toArray(filteredList.toArray(new Boolean[filteredList.size()]));
	}

	public static byte[] filter(
		byte[] array, PredicateFilter<Byte> predicateFilter) {

		if (isEmpty(array)) {
			return array;
		}

		List<Byte> filteredList = new ArrayList<>();

		for (byte b : array) {
			if (predicateFilter.filter(b)) {
				filteredList.add(b);
			}
		}

		return toArray(filteredList.toArray(new Byte[filteredList.size()]));
	}

	public static char[] filter(
		char[] array, PredicateFilter<Character> predicateFilter) {

		if (isEmpty(array)) {
			return array;
		}

		List<Character> filteredList = new ArrayList<>();

		for (char c : array) {
			if (predicateFilter.filter(c)) {
				filteredList.add(c);
			}
		}

		return toArray(
			filteredList.toArray(new Character[filteredList.size()]));
	}

	public static double[] filter(
		double[] array, PredicateFilter<Double> predicateFilter) {

		if (isEmpty(array)) {
			return array;
		}

		List<Double> filteredList = new ArrayList<>();

		for (double d : array) {
			if (predicateFilter.filter(d)) {
				filteredList.add(d);
			}
		}

		return toArray(filteredList.toArray(new Double[filteredList.size()]));
	}

	public static float[] filter(
		float[] array, PredicateFilter<Float> predicateFilter) {

		if (isEmpty(array)) {
			return array;
		}

		List<Float> filteredList = new ArrayList<>();

		for (float f : array) {
			if (predicateFilter.filter(f)) {
				filteredList.add(f);
			}
		}

		return toArray(filteredList.toArray(new Float[filteredList.size()]));
	}

	public static int[] filter(
		int[] array, PredicateFilter<Integer> predicateFilter) {

		if (isEmpty(array)) {
			return array;
		}

		List<Integer> filteredList = new ArrayList<>();

		for (int i : array) {
			if (predicateFilter.filter(i)) {
				filteredList.add(i);
			}
		}

		return toArray(filteredList.toArray(new Integer[filteredList.size()]));
	}

	public static long[] filter(
		long[] array, PredicateFilter<Long> predicateFilter) {

		if (isEmpty(array)) {
			return array;
		}

		List<Long> filteredList = new ArrayList<>();

		for (long l : array) {
			if (predicateFilter.filter(l)) {
				filteredList.add(l);
			}
		}

		return toArray(filteredList.toArray(new Long[filteredList.size()]));
	}

	public static short[] filter(
		short[] array, PredicateFilter<Short> predicateFilter) {

		if (isEmpty(array)) {
			return array;
		}

		List<Short> filteredList = new ArrayList<>();

		for (short s : array) {
			if (predicateFilter.filter(s)) {
				filteredList.add(s);
			}
		}

		return toArray(filteredList.toArray(new Short[filteredList.size()]));
	}

	public static <T> T[] filter(
		T[] array, PredicateFilter<T> filterPredicate) {

		if (isEmpty(array)) {
			return array;
		}

		List<T> filteredList = new ArrayList<>();

		for (T t : array) {
			if (filterPredicate.filter(t)) {
				filteredList.add(t);
			}
		}

		Object[] filteredArray = filteredList.toArray();

		return (T[])Arrays.copyOf(
			filteredArray, filteredArray.length, array.getClass());
	}

	public static int getLength(Object[] array) {
		if (array == null) {
			return 0;
		}
		else {
			return array.length;
		}
	}

	public static Object getValue(Object[] array, int pos) {
		if ((array == null) || (array.length <= pos)) {
			return null;
		}
		else {
			return array[pos];
		}
	}

	public static boolean isEmpty(boolean[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(byte[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(char[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(double[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(float[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(int[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(long[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(Object[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(short[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		}

		return false;
	}

	public static boolean isNotEmpty(boolean[] array) {
		return !isEmpty(array);
	}

	public static boolean isNotEmpty(byte[] array) {
		return !isEmpty(array);
	}

	public static boolean isNotEmpty(char[] array) {
		return !isEmpty(array);
	}

	public static boolean isNotEmpty(double[] array) {
		return !isEmpty(array);
	}

	public static boolean isNotEmpty(float[] array) {
		return !isEmpty(array);
	}

	public static boolean isNotEmpty(int[] array) {
		return !isEmpty(array);
	}

	public static boolean isNotEmpty(long[] array) {
		return !isEmpty(array);
	}

	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}

	public static boolean isNotEmpty(short[] array) {
		return !isEmpty(array);
	}

	public static boolean[] remove(boolean[] array, boolean value) {
		if (isEmpty(array)) {
			return array;
		}

		List<Boolean> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			if (value != array[i]) {
				list.add(array[i]);
			}
		}

		return toArray(list.toArray(new Boolean[list.size()]));
	}

	public static byte[] remove(byte[] array, byte value) {
		if (isEmpty(array)) {
			return array;
		}

		List<Byte> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			if (value != array[i]) {
				list.add(Byte.valueOf(array[i]));
			}
		}

		return toArray(list.toArray(new Byte[list.size()]));
	}

	public static char[] remove(char[] array, char value) {
		if (isEmpty(array)) {
			return array;
		}

		List<Character> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			if (value != array[i]) {
				list.add(Character.valueOf(array[i]));
			}
		}

		return toArray(list.toArray(new Character[list.size()]));
	}

	public static double[] remove(double[] array, double value) {
		if (isEmpty(array)) {
			return array;
		}

		List<Double> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			if (value != array[i]) {
				list.add(Double.valueOf(array[i]));
			}
		}

		return toArray(list.toArray(new Double[list.size()]));
	}

	public static float[] remove(float[] array, float value) {
		if (isEmpty(array)) {
			return array;
		}

		List<Float> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			if (value != array[i]) {
				list.add(Float.valueOf(array[i]));
			}
		}

		return toArray(list.toArray(new Float[list.size()]));
	}

	public static int[] remove(int[] array, int value) {
		if (isEmpty(array)) {
			return array;
		}

		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			if (value != array[i]) {
				list.add(Integer.valueOf(array[i]));
			}
		}

		return toArray(list.toArray(new Integer[list.size()]));
	}

	public static long[] remove(long[] array, long value) {
		if (isEmpty(array)) {
			return array;
		}

		List<Long> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			if (value != array[i]) {
				list.add(Long.valueOf(array[i]));
			}
		}

		return toArray(list.toArray(new Long[list.size()]));
	}

	public static short[] remove(short[] array, short value) {
		if (isEmpty(array)) {
			return array;
		}

		List<Short> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			if (value != array[i]) {
				list.add(Short.valueOf(array[i]));
			}
		}

		return toArray(list.toArray(new Short[list.size()]));
	}

	public static String[] remove(String[] array, String value) {
		if (isEmpty(array)) {
			return array;
		}

		List<String> list = new ArrayList<>();

		for (String s : array) {
			if (!s.equals(value)) {
				list.add(s);
			}
		}

		return list.toArray(new String[list.size()]);
	}

	public static <T> T[] remove(T[] array, T value) {
		if (isEmpty(array)) {
			return array;
		}

		List<T> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			if (value != array[i]) {
				list.add(array[i]);
			}
		}

		if (array.length == list.size()) {
			return array;
		}

		Class<?> arrayClass = array.getClass();

		return list.toArray(
			(T[])Array.newInstance(arrayClass.getComponentType(), list.size()));
	}

	public static String[] removeByPrefix(String[] array, String prefix) {
		List<String> list = new ArrayList<>();

		for (String s : array) {
			if (!s.startsWith(prefix)) {
				list.add(s);
			}
		}

		return list.toArray(new String[list.size()]);
	}

	public static void replace(
		String[] values, String oldValue, String newValue) {

		for (int i = 0; i < values.length; i++) {
			if (values[i].equals(oldValue)) {
				values[i] = newValue;
			}
		}
	}

	public static void reverse(boolean[] array) {
		for (int left = 0, right = array.length - 1; left < right;
			left++, right--) {

			boolean value = array[left];

			array[left] = array[right];
			array[right] = value;
		}
	}

	public static void reverse(char[] array) {
		for (int left = 0, right = array.length - 1; left < right;
			left++, right--) {

			char value = array[left];

			array[left] = array[right];
			array[right] = value;
		}
	}

	public static void reverse(double[] array) {
		for (int left = 0, right = array.length - 1; left < right;
			left++, right--) {

			double value = array[left];

			array[left] = array[right];
			array[right] = value;
		}
	}

	public static void reverse(int[] array) {
		for (int left = 0, right = array.length - 1; left < right;
			left++, right--) {

			int value = array[left];

			array[left] = array[right];
			array[right] = value;
		}
	}

	public static void reverse(long[] array) {
		for (int left = 0, right = array.length - 1; left < right;
			left++, right--) {

			long value = array[left];

			array[left] = array[right];
			array[right] = value;
		}
	}

	public static void reverse(short[] array) {
		for (int left = 0, right = array.length - 1; left < right;
			left++, right--) {

			short value = array[left];

			array[left] = array[right];
			array[right] = value;
		}
	}

	public static <T> void reverse(T[] array) {
		for (int left = 0, right = array.length - 1; left < right;
			left++, right--) {

			T value = array[left];

			array[left] = array[right];
			array[right] = value;
		}
	}

	public static boolean[] subset(boolean[] array, int start, int end) {
		if ((start < 0) || (end < 0) || ((end - start) < 0)) {
			return array;
		}

		boolean[] newArray = new boolean[end - start];

		System.arraycopy(array, start, newArray, 0, end - start);

		return newArray;
	}

	public static byte[] subset(byte[] array, int start, int end) {
		if ((start < 0) || (end < 0) || ((end - start) < 0)) {
			return array;
		}

		byte[] newArray = new byte[end - start];

		System.arraycopy(array, start, newArray, 0, end - start);

		return newArray;
	}

	public static char[] subset(char[] array, int start, int end) {
		if ((start < 0) || (end < 0) || ((end - start) < 0)) {
			return array;
		}

		char[] newArray = new char[end - start];

		System.arraycopy(array, start, newArray, 0, end - start);

		return newArray;
	}

	public static double[] subset(double[] array, int start, int end) {
		if ((start < 0) || (end < 0) || ((end - start) < 0)) {
			return array;
		}

		double[] newArray = new double[end - start];

		System.arraycopy(array, start, newArray, 0, end - start);

		return newArray;
	}

	public static float[] subset(float[] array, int start, int end) {
		if ((start < 0) || (end < 0) || ((end - start) < 0)) {
			return array;
		}

		float[] newArray = new float[end - start];

		System.arraycopy(array, start, newArray, 0, end - start);

		return newArray;
	}

	public static int[] subset(int[] array, int start, int end) {
		if ((start < 0) || (end < 0) || ((end - start) < 0)) {
			return array;
		}

		int[] newArray = new int[end - start];

		System.arraycopy(array, start, newArray, 0, end - start);

		return newArray;
	}

	public static long[] subset(long[] array, int start, int end) {
		if ((start < 0) || (end < 0) || ((end - start) < 0)) {
			return array;
		}

		long[] newArray = new long[end - start];

		System.arraycopy(array, start, newArray, 0, end - start);

		return newArray;
	}

	public static short[] subset(short[] array, int start, int end) {
		if ((start < 0) || (end < 0) || ((end - start) < 0)) {
			return array;
		}

		short[] newArray = new short[end - start];

		System.arraycopy(array, start, newArray, 0, end - start);

		return newArray;
	}

	public static <T> T[] subset(T[] array, int start, int end) {
		if ((start < 0) || (end < 0) || ((end - start) < 0)) {
			return array;
		}

		Class<?> arrayClass = array.getClass();

		T[] newArray = (T[])Array.newInstance(
			arrayClass.getComponentType(), end - start);

		System.arraycopy(array, start, newArray, 0, end - start);

		return newArray;
	}

	public static Boolean[] toArray(boolean[] array) {
		Boolean[] newArray = new Boolean[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = Boolean.valueOf(array[i]);
		}

		return newArray;
	}

	public static boolean[] toArray(Boolean[] array) {
		boolean[] newArray = new boolean[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i].booleanValue();
		}

		return newArray;
	}

	public static Byte[] toArray(byte[] array) {
		Byte[] newArray = new Byte[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = Byte.valueOf(array[i]);
		}

		return newArray;
	}

	public static byte[] toArray(Byte[] array) {
		byte[] newArray = new byte[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i].byteValue();
		}

		return newArray;
	}

	public static Character[] toArray(char[] array) {
		Character[] newArray = new Character[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = Character.valueOf(array[i]);
		}

		return newArray;
	}

	public static char[] toArray(Character[] array) {
		char[] newArray = new char[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i].charValue();
		}

		return newArray;
	}

	public static Double[] toArray(double[] array) {
		Double[] newArray = new Double[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = Double.valueOf(array[i]);
		}

		return newArray;
	}

	public static double[] toArray(Double[] array) {
		double[] newArray = new double[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i].doubleValue();
		}

		return newArray;
	}

	public static Float[] toArray(float[] array) {
		Float[] newArray = new Float[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = Float.valueOf(array[i]);
		}

		return newArray;
	}

	public static float[] toArray(Float[] array) {
		float[] newArray = new float[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i].floatValue();
		}

		return newArray;
	}

	public static Integer[] toArray(int[] array) {
		Integer[] newArray = new Integer[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = Integer.valueOf(array[i]);
		}

		return newArray;
	}

	public static int[] toArray(Integer[] array) {
		int[] newArray = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i].intValue();
		}

		return newArray;
	}

	public static Long[] toArray(long[] array) {
		Long[] newArray = new Long[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = Long.valueOf(array[i]);
		}

		return newArray;
	}

	public static long[] toArray(Long[] array) {
		long[] newArray = new long[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i].longValue();
		}

		return newArray;
	}

	public static Short[] toArray(short[] array) {
		Short[] newArray = new Short[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = Short.valueOf(array[i]);
		}

		return newArray;
	}

	public static short[] toArray(Short[] array) {
		short[] newArray = new short[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i].shortValue();
		}

		return newArray;
	}

	public static String[] toArray(String[] array) {
		String[] newArray = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i].toString();
		}

		return newArray;
	}

	public static <T, A> A[] toArray(T[] list, Accessor<T, A> accessor) {
		A[] aArray = (A[])Array.newInstance(
			accessor.getAttributeClass(), list.length);

		for (int i = 0; i < list.length; i++) {
			aArray[i] = accessor.get(list[i]);
		}

		return aArray;
	}

	public static double[] toDoubleArray(
		Collection<? extends Number> collection) {

		double[] newArray = new double[collection.size()];

		if (collection instanceof List) {
			List<Number> list = (List<Number>)collection;

			for (int i = 0; i < list.size(); i++) {
				Number value = list.get(i);

				newArray[i] = value.doubleValue();
			}
		}
		else {
			int i = 0;

			Iterator<? extends Number> iterator = collection.iterator();

			while (iterator.hasNext()) {
				Number value = iterator.next();

				newArray[i++] = value.doubleValue();
			}
		}

		return newArray;
	}

	public static float[] toFloatArray(
		Collection<? extends Number> collection) {

		float[] newArray = new float[collection.size()];

		if (collection instanceof List) {
			List<Number> list = (List<Number>)collection;

			for (int i = 0; i < list.size(); i++) {
				Number value = list.get(i);

				newArray[i] = value.floatValue();
			}
		}
		else {
			int i = 0;

			Iterator<? extends Number> iterator = collection.iterator();

			while (iterator.hasNext()) {
				Number value = iterator.next();

				newArray[i++] = value.floatValue();
			}
		}

		return newArray;
	}

	public static int[] toIntArray(Collection<? extends Number> collection) {
		int[] newArray = new int[collection.size()];

		if (collection instanceof List) {
			List<Number> list = (List<Number>)collection;

			for (int i = 0; i < list.size(); i++) {
				Number value = list.get(i);

				newArray[i] = value.intValue();
			}
		}
		else {
			int i = 0;

			Iterator<? extends Number> iterator = collection.iterator();

			while (iterator.hasNext()) {
				Number value = iterator.next();

				newArray[i++] = value.intValue();
			}
		}

		return newArray;
	}

	public static long[] toLongArray(Collection<? extends Number> collection) {
		long[] newArray = new long[collection.size()];

		if (collection instanceof List) {
			List<Number> list = (List<Number>)collection;

			for (int i = 0; i < list.size(); i++) {
				Number value = list.get(i);

				newArray[i] = value.longValue();
			}
		}
		else {
			int i = 0;

			Iterator<? extends Number> iterator = collection.iterator();

			while (iterator.hasNext()) {
				Number value = iterator.next();

				newArray[i++] = value.longValue();
			}
		}

		return newArray;
	}

	public static Long[] toLongArray(int[] array) {
		Long[] newArray = new Long[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = (long)array[i];
		}

		return newArray;
	}

	public static Long[] toLongArray(long[] array) {
		Long[] newArray = new Long[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}

		return newArray;
	}

	public static Long[] toLongArray(Object[] array) {
		Long[] newArray = new Long[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = (Long)array[i];
		}

		return newArray;
	}

	public static short[] toShortArray(Collection<Short> collection) {
		short[] newArray = new short[collection.size()];

		if (collection instanceof List) {
			List<Short> list = (List<Short>)collection;

			for (int i = 0; i < list.size(); i++) {
				Short value = list.get(i);

				newArray[i] = value.shortValue();
			}
		}
		else {
			int i = 0;

			Iterator<Short> iterator = collection.iterator();

			while (iterator.hasNext()) {
				Short value = iterator.next();

				newArray[i++] = value.shortValue();
			}
		}

		return newArray;
	}

	/**
	 * @see ListUtil#toString(List, String)
	 */
	public static String toString(Object[] array, String param) {
		return toString(array, param, StringPool.COMMA);
	}

	/**
	 * @see ListUtil#toString(List, String, String)
	 */
	public static String toString(
		Object[] array, String param, String delimiter) {

		return toString(array, param, delimiter, null);
	}

	public static String toString(
		Object[] array, String param, String delimiter, Locale locale) {

		if (isEmpty(array)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2 * array.length - 1);

		for (int i = 0; i < array.length; i++) {
			Object bean = array[i];

			Object value = BeanPropertiesUtil.getObject(bean, param);

			if (value != null) {
				if (locale != null) {
					sb.append(LanguageUtil.get(locale, value.toString()));
				}
				else {
					sb.append(value);
				}
			}

			if ((i + 1) != array.length) {
				sb.append(delimiter);
			}
		}

		return sb.toString();
	}

	/**
	 * @see ListUtil#toString(List, Accessor)
	 */
	public static <T, A> String toString(T[] list, Accessor<T, A> accessor) {
		return toString(list, accessor, StringPool.COMMA);
	}

	/**
	 * @see ListUtil#toString(List, Accessor, String)
	 */
	public static <T, A> String toString(
		T[] list, Accessor<T, A> accessor, String delimiter) {

		return toString(list, accessor, delimiter, null);
	}

	public static <T, A> String toString(
		T[] list, Accessor<T, A> accessor, String delimiter, Locale locale) {

		if (isEmpty(list)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2 * list.length - 1);

		for (int i = 0; i < list.length; i++) {
			T bean = list[i];

			A attribute = accessor.get(bean);

			if (attribute != null) {
				if (locale != null) {
					sb.append(LanguageUtil.get(locale, attribute.toString()));
				}
				else {
					sb.append(attribute);
				}
			}

			if ((i + 1) != list.length) {
				sb.append(delimiter);
			}
		}

		return sb.toString();
	}

	public static String[] toStringArray(boolean[] array) {
		String[] newArray = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = String.valueOf(array[i]);
		}

		return newArray;
	}

	public static String[] toStringArray(byte[] array) {
		String[] newArray = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = String.valueOf(array[i]);
		}

		return newArray;
	}

	public static String[] toStringArray(char[] array) {
		String[] newArray = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = String.valueOf(array[i]);
		}

		return newArray;
	}

	public static String[] toStringArray(Collection<String> collection) {
		String[] newArray = new String[collection.size()];

		if (collection instanceof List) {
			List<String> list = (List<String>)collection;

			for (int i = 0; i < list.size(); i++) {
				String value = list.get(i);

				newArray[i] = String.valueOf(value);
			}
		}
		else {
			int i = 0;

			Iterator<String> iterator = collection.iterator();

			while (iterator.hasNext()) {
				String value = iterator.next();

				newArray[i++] = String.valueOf(value);
			}
		}

		return newArray;
	}

	public static String[] toStringArray(Date[] array, DateFormat dateFormat) {
		String[] newArray = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = dateFormat.format(array[i]);
		}

		return newArray;
	}

	public static String[] toStringArray(double[] array) {
		String[] newArray = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = String.valueOf(array[i]);
		}

		return newArray;
	}

	public static String[] toStringArray(float[] array) {
		String[] newArray = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = String.valueOf(array[i]);
		}

		return newArray;
	}

	public static String[] toStringArray(int[] array) {
		String[] newArray = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = String.valueOf(array[i]);
		}

		return newArray;
	}

	public static String[] toStringArray(JSONArray array) {
		String[] newArray = new String[array.length()];

		for (int i = 0; i < array.length(); i++) {
			newArray[i] = array.getString(i);
		}

		return newArray;
	}

	public static String[] toStringArray(long[] array) {
		String[] newArray = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = String.valueOf(array[i]);
		}

		return newArray;
	}

	public static String[] toStringArray(Object[] array) {
		String[] newArray = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = String.valueOf(array[i]);
		}

		return newArray;
	}

	public static String[] toStringArray(short[] array) {
		String[] newArray = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = String.valueOf(array[i]);
		}

		return newArray;
	}

	public static byte[] unique(byte[] array) {
		Set<Byte> set = new LinkedHashSet<>();

		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}

		return toArray(set.toArray(new Byte[set.size()]));
	}

	public static double[] unique(double[] array) {
		Set<Double> set = new LinkedHashSet<>();

		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}

		return toArray(set.toArray(new Double[set.size()]));
	}

	public static float[] unique(float[] array) {
		Set<Float> set = new LinkedHashSet<>();

		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}

		return toArray(set.toArray(new Float[set.size()]));
	}

	public static int[] unique(int[] array) {
		Set<Integer> set = new LinkedHashSet<>();

		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}

		return toArray(set.toArray(new Integer[set.size()]));
	}

	public static long[] unique(long[] array) {
		Set<Long> set = new LinkedHashSet<>();

		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}

		return toArray(set.toArray(new Long[set.size()]));
	}

	public static short[] unique(short[] array) {
		Set<Short> set = new LinkedHashSet<>();

		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}

		return toArray(set.toArray(new Short[set.size()]));
	}

	public static String[] unique(String[] array) {
		Set<String> set = new LinkedHashSet<>();

		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}

		return toArray(set.toArray(new String[set.size()]));
	}

}