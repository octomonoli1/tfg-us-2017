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

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.highlight.HighlightUtil;
import com.liferay.portal.kernel.security.RandomUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * The String utility class.
 *
 * @author Brian Wing Shun Chan
 * @author Sandeep Soni
 * @author Ganesh Ram
 * @author Shuyang Zhou
 * @author Hugo Huijser
 */
public class StringUtil {

	/**
	 * Adds string <code>add</code> to string <code>s</code> resulting in a
	 * comma delimited list of strings, disallowing duplicate strings in the
	 * list.
	 *
	 * <p>
	 * The resulting string ends with a comma even if the original string does
	 * not.
	 * </p>
	 *
	 * @param  s the original string, representing a comma delimited list of
	 *         strings
	 * @param  add the string to add to the original, representing the string to
	 *         add to the list
	 * @return a string that represents the original string and the added string
	 *         separated by a comma, or <code>null</code> if the string to add
	 *         is <code>null</code>
	 */
	public static String add(String s, String add) {
		return add(s, add, StringPool.COMMA);
	}

	/**
	 * Adds string <code>add</code> to string <code>s</code> that represents a
	 * delimited list of strings, using a specified delimiter and disallowing
	 * duplicate words.
	 *
	 * <p>
	 * The returned string ends with the delimiter even if the original string
	 * does not.
	 * </p>
	 *
	 * @param  s the original string, representing a delimited list of strings
	 * @param  add the string to add to the original, representing the string to
	 *         add to the list
	 * @param  delimiter the delimiter used to separate strings in the list
	 * @return a string that represents the original string and the added string
	 *         separated by the delimiter, or <code>null</code> if the string to
	 *         add or the delimiter string is <code>null</code>
	 */
	public static String add(String s, String add, String delimiter) {
		return add(s, add, delimiter, false);
	}

	/**
	 * Adds string <code>add</code> to string <code>s</code> that represents a
	 * delimited list of strings, using a specified delimiter and optionally
	 * allowing duplicate words.
	 *
	 * <p>
	 * The returned string ends with the delimiter even if the original string
	 * does not.
	 * </p>
	 *
	 * @param  s the original string, representing a delimited list of strings
	 * @param  add the string to add to the original, representing the string to
	 *         add to the list
	 * @param  delimiter the delimiter used to separate strings in the list
	 * @param  allowDuplicates whether to allow duplicate strings
	 * @return a string that represents the original string and the added string
	 *         separated by the delimiter, or <code>null</code> if the string to
	 *         add or the delimiter string is <code>null</code>
	 */
	public static String add(
		String s, String add, String delimiter, boolean allowDuplicates) {

		if ((add == null) || (delimiter == null)) {
			return null;
		}

		if (s == null) {
			s = StringPool.BLANK;
		}

		if (allowDuplicates || !contains(s, add, delimiter)) {
			StringBundler sb = new StringBundler(4);

			sb.append(s);

			if (Validator.isNull(s) || s.endsWith(delimiter)) {
				sb.append(add);
				sb.append(delimiter);
			}
			else {
				sb.append(delimiter);
				sb.append(add);
				sb.append(delimiter);
			}

			s = sb.toString();
		}

		return s;
	}

	/**
	 * Returns the original string with an appended space followed by the string
	 * value of the suffix surrounded by parentheses.
	 *
	 * <p>
	 * If the original string ends with a numerical parenthetical suffix having
	 * an integer value equal to <code>suffix - 1</code>, then the existing
	 * parenthetical suffix is replaced by the new one.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * appendParentheticalSuffix("file", 0) returns "file (0)"
	 * appendParentheticalSuffix("file (0)", 0) returns "file (0) (0)"
	 * appendParentheticalSuffix("file (0)", 1) returns "file (1)"
	 * appendParentheticalSuffix("file (0)", 2) returns "file (0) (2)"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the original string
	 * @param  suffix the suffix to be appended
	 * @return the resultant string whose characters equal those of the original
	 *         string, followed by a space, followed by the specified suffix
	 *         enclosed in parentheses, or, if the difference between the
	 *         provided suffix and the existing suffix is 1, the existing suffix
	 *         is incremented by 1
	 */
	public static String appendParentheticalSuffix(String s, int suffix) {
		if (Pattern.matches(".* \\(" + String.valueOf(suffix - 1) + "\\)", s)) {
			int pos = s.lastIndexOf(" (");

			s = s.substring(0, pos);
		}

		return appendParentheticalSuffix(s, String.valueOf(suffix));
	}

	/**
	 * Returns the original string with an appended space followed by the suffix
	 * surrounded by parentheses.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * appendParentheticalSuffix("Java", "EE") returns "Java (EE)"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the original string
	 * @param  suffix the suffix to be appended
	 * @return a string that represents the original string, followed by a
	 *         space, followed by the suffix enclosed in parentheses
	 */
	public static String appendParentheticalSuffix(String s, String suffix) {
		StringBundler sb = new StringBundler(5);

		sb.append(s);
		sb.append(StringPool.SPACE);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(suffix);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	/**
	 * Converts an array of bytes to a string representing the bytes in
	 * hexadecimal form.
	 *
	 * @param  bytes the array of bytes to be converted
	 * @return the string representing the bytes in hexadecimal form
	 */
	public static String bytesToHexString(byte[] bytes) {
		char[] chars = new char[bytes.length * 2];

		for (int i = 0; i < bytes.length; i++) {
			chars[i * 2] = HEX_DIGITS[(bytes[i] & 0xFF) >> 4];
			chars[i * 2 + 1] = HEX_DIGITS[bytes[i] & 0x0F];
		}

		return new String(chars);
	}

	/**
	 * Returns <code>true</code> if the string contains the text as one or more
	 * consecutive comma delimited list entries.
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * contains("one,two,three", "two") returns true
	 * contains("one,two,three", "thr") returns false
	 * contains("one,two,three", "one,two") returns true
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string in which to search
	 * @param  text the text to search for in the string
	 * @return <code>true</code> if the string contains the text as one or more
	 *         consecutive comma delimited list entries; <code>false</code>
	 *         otherwise
	 */
	public static boolean contains(String s, String text) {
		return contains(s, text, StringPool.COMMA);
	}

	/**
	 * Returns <code>true</code> if the string contains the text as one or more
	 * consecutive delimited list entries.
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * contains("three...two...one", "two", "...") returns true
	 * contains("three...two...one", "thr", "...") returns false
	 * contains("three...two...one", "two...one", "...") returns true
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string in which to search
	 * @param  text the text to search for in the string
	 * @param  delimiter the delimiter
	 * @return <code>true</code> if the string contains the text as one or more
	 *         consecutive delimited list entries; <code>false</code> otherwise
	 */
	public static boolean contains(String s, String text, String delimiter) {
		if ((s == null) || (text == null) || (delimiter == null)) {
			return false;
		}

		if (!s.endsWith(delimiter)) {
			s = s.concat(delimiter);
		}

		String dtd = delimiter.concat(text).concat(delimiter);

		int pos = s.indexOf(dtd);

		if (pos == -1) {
			String td = text.concat(delimiter);

			if (s.startsWith(td)) {
				return true;
			}

			return false;
		}

		return true;
	}

	/**
	 * Returns <code>true</code> if the string contains the text as one or more
	 * consecutive comma delimited list entries, ignoring case.
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * containsIgnoreCase("one,two,three", "Two") returns true
	 * containsIgnoreCase("one,two,three", "thr") returns false
	 * containsIgnoreCase("one,two,three", "one,two") returns true
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string in which to search
	 * @param  text the text to search for in the string
	 * @return <code>true</code> if the string contains the text as one or more
	 *         consecutive comma delimited list entries; <code>false</code>
	 *         otherwise
	 */
	public static boolean containsIgnoreCase(String s, String text) {
		return containsIgnoreCase(s, text, StringPool.COMMA);
	}

	/**
	 * Returns <code>true</code> if the string contains the text as one or more
	 * consecutive delimited list entries, ignoring case.
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * containsIgnoreCase("three...two...one", "Two", "...") returns true
	 * containsIgnoreCase("three...two...one", "thr", "...") returns false
	 * containsIgnoreCase("three...two...one", "two...one", "...") returns true
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string in which to search
	 * @param  text the text to search for in the string
	 * @param  delimiter the delimiter
	 * @return <code>true</code> if the string contains the text as one or more
	 *         consecutive delimited list entries; <code>false</code> otherwise
	 */
	public static boolean containsIgnoreCase(
		String s, String text, String delimiter) {

		if ((s == null) || (text == null) || (delimiter == null)) {
			return false;
		}

		return contains(toLowerCase(s), toLowerCase(text), delimiter);
	}

	public static int count(String s, char c) {
		return count(s, 0, s.length(), c);
	}

	public static int count(String s, int start, int end, char c) {
		if ((s == null) || s.isEmpty() || ((end - start) < 1)) {
			return 0;
		}

		int count = 0;

		int pos = start;

		while ((pos < end) && ((pos = s.indexOf(c, pos)) != -1)) {
			if (pos < end) {
				count++;
			}

			pos++;
		}

		return count;
	}

	public static int count(String s, int start, int end, String text) {
		if ((s == null) || s.isEmpty() || ((end - start) < 1) ||
			(text == null) || text.isEmpty()) {

			return 0;
		}

		int count = 0;

		int pos = start;

		while ((pos < end) && (pos = s.indexOf(text, pos)) != -1) {
			if (pos < end) {
				count++;
			}

			pos += text.length();
		}

		return count;
	}

	/**
	 * Returns the number of times the text appears in the string.
	 *
	 * @param  s the string in which to search
	 * @param  text the text to search for in the string
	 * @return the number of times the text appears in the string
	 */
	public static int count(String s, String text) {
		return count(s, 0, s.length(), text);
	}

	/**
	 * Returns <code>true</code> if the string ends with the specified
	 * character, ignoring case.
	 *
	 * @param  s the string in which to search
	 * @param  end the character to search for at the end of the string
	 * @return <code>true</code> if the string ends with the specified
	 *         character, ignoring case; <code>false</code> otherwise
	 */
	public static boolean endsWith(String s, char end) {
		if ((s == null) || s.isEmpty()) {
			return false;
		}

		return equalsIgnoreCase(s.charAt(s.length() - 1), end);
	}

	/**
	 * Returns <code>true</code> if the string ends with the string
	 * <code>end</code>, ignoring case.
	 *
	 * @param  s the string in which to search
	 * @param  end the string to check for at the end of the string
	 * @return <code>true</code> if the string ends with the string
	 *         <code>end</code>, ignoring case; <code>false</code> otherwise
	 */
	public static boolean endsWith(String s, String end) {
		if ((s == null) || (end == null)) {
			return false;
		}

		if (end.length() > s.length()) {
			return false;
		}

		String temp = s.substring(s.length() - end.length());

		if (equalsIgnoreCase(temp, end)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns <code>true</code> if the strings are equal, ignoring new line
	 * characters.
	 *
	 * @param  s1 the first string to compare
	 * @param  s2 the second string to compare
	 * @return <code>true</code> if the strings are equal, ignoring new line
	 *         characters; <code>false</code> otherwise
	 */
	public static boolean equalsIgnoreBreakLine(String s1, String s2) {
		if (s1 == s2) {
			return true;
		}

		if ((s1 == null) || (s2 == null)) {
			return false;
		}

		s1 = removeChars(s1, CharPool.RETURN, CharPool.NEW_LINE);
		s2 = removeChars(s2, CharPool.RETURN, CharPool.NEW_LINE);

		if (s1.length() != s2.length()) {
			return false;
		}

		return s1.equals(s2);
	}

	public static boolean equalsIgnoreCase(char c1, char c2) {
		if (c1 == c2) {
			return true;
		}

		// Fast fallback for non-acsii code.

		if ((c1 > 127) || (c2 > 127)) {

			// Georgian alphabet needs to check both upper and lower case

			if ((Character.toLowerCase(c1) == Character.toLowerCase(c2)) ||
				(Character.toUpperCase(c1) == Character.toUpperCase(c2))) {

				return true;
			}

			return false;
		}

		// Fast fallback for non-letter ascii code

		if ((c1 < CharPool.UPPER_CASE_A) || (c1 > CharPool.LOWER_CASE_Z) ||
			(c2 < CharPool.UPPER_CASE_A) || (c2 > CharPool.LOWER_CASE_Z)) {

			return false;
		}

		int delta = c1 - c2;

		if ((delta != 32) && (delta != -32)) {
			return false;
		}

		return true;
	}

	/**
	 * Returns <code>true</code> if the strings are equal, ignoring case.
	 *
	 * @param  s1 the first string to compare
	 * @param  s2 the second string to compare
	 * @return <code>true</code> if the strings are equal, ignoring case;
	 *         <code>false</code> otherwise
	 */
	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (s1 == s2) {
			return true;
		}

		if ((s1 == null) || (s2 == null)) {
			return false;
		}

		if (s1.length() != s2.length()) {
			return false;
		}

		for (int i = 0; i < s1.length(); i++) {
			if (!equalsIgnoreCase(s1.charAt(i), s2.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the substring of each character instance in string <code>s</code>
	 * that is found in the character array <code>chars</code>. The substring of
	 * characters returned maintain their original order.
	 *
	 * @param  s the string from which to extract characters
	 * @param  chars the characters to extract from the string
	 * @return the substring of each character instance in string <code>s</code>
	 *         that is found in the character array <code>chars</code>, or an
	 *         empty string if the given string is <code>null</code>
	 */
	public static String extract(String s, char[] chars) {
		if (s == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler();

		for (char c1 : s.toCharArray()) {
			for (char c2 : chars) {
				if (c1 == c2) {
					sb.append(c1);

					break;
				}
			}
		}

		return sb.toString();
	}

	/**
	 * Returns the substring of English characters from the string.
	 *
	 * @param  s the string from which to extract characters
	 * @return the substring of English characters from the string, or an empty
	 *         string if the given string is <code>null</code>
	 */
	public static String extractChars(String s) {
		if (s == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler();

		char[] chars = s.toCharArray();

		for (char c : chars) {
			if (Validator.isChar(c)) {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * Returns a string consisting of all of the digits extracted from the
	 * string.
	 *
	 * @param  s the string from which to extract digits
	 * @return a string consisting of all of the digits extracted from the
	 *         string
	 */
	public static String extractDigits(String s) {
		if (s == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler();

		char[] chars = s.toCharArray();

		for (char c : chars) {
			if (Validator.isDigit(c)) {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * Returns the substring of <code>s</code> up to but not including the first
	 * occurrence of the delimiter.
	 *
	 * @param  s the string from which to extract a substring
	 * @param  delimiter the character whose index in the string marks where to
	 *         end the substring
	 * @return the substring of <code>s</code> up to but not including the first
	 *         occurrence of the delimiter, <code>null</code> if the string is
	 *         <code>null</code> or the delimiter does not occur in the string
	 */
	public static String extractFirst(String s, char delimiter) {
		if (s == null) {
			return null;
		}

		int index = s.indexOf(delimiter);

		if (index < 0) {
			return null;
		}
		else {
			return s.substring(0, index);
		}
	}

	/**
	 * Returns the substring of <code>s</code> up to but not including the first
	 * occurrence of the delimiter.
	 *
	 * @param  s the string from which to extract a substring
	 * @param  delimiter the smaller string whose index in the larger string
	 *         marks where to end the substring
	 * @return the substring of <code>s</code> up to but not including the first
	 *         occurrence of the delimiter, <code>null</code> if the string is
	 *         <code>null</code> or the delimiter does not occur in the string
	 */
	public static String extractFirst(String s, String delimiter) {
		if (s == null) {
			return null;
		}

		int index = s.indexOf(delimiter);

		if (index < 0) {
			return null;
		}
		else {
			return s.substring(0, index);
		}
	}

	/**
	 * Returns the substring of <code>s</code> after but not including the last
	 * occurrence of the delimiter.
	 *
	 * @param  s the string from which to extract the substring
	 * @param  delimiter the character whose last index in the string marks
	 *         where to begin the substring
	 * @return the substring of <code>s</code> after but not including the last
	 *         occurrence of the delimiter, <code>null</code> if the string is
	 *         <code>null</code> or the delimiter does not occur in the string
	 */
	public static String extractLast(String s, char delimiter) {
		if (s == null) {
			return null;
		}

		int index = s.lastIndexOf(delimiter);

		if (index < 0) {
			return null;
		}
		else {
			return s.substring(index + 1);
		}
	}

	/**
	 * Returns the substring of <code>s</code> after but not including the last
	 * occurrence of the delimiter.
	 *
	 * @param  s the string from which to extract the substring
	 * @param  delimiter the string whose last index in the string marks where
	 *         to begin the substring
	 * @return the substring of <code>s</code> after but not including the last
	 *         occurrence of the delimiter, <code>null</code> if the string is
	 *         <code>null</code> or the delimiter does not occur in the string
	 */
	public static String extractLast(String s, String delimiter) {
		if (s == null) {
			return null;
		}

		int index = s.lastIndexOf(delimiter);

		if (index < 0) {
			return null;
		}
		else {
			return s.substring(index + delimiter.length());
		}
	}

	/**
	 * Returns the substring of all leading digits of string <code>s</code>, or
	 * an empty string if it has no leading digits.
	 *
	 * @param  s the string from which to extract the substring
	 * @return the substring of all leading digits of string <code>s</code>, or
	 *         an empty string if it has no leading digits
	 */
	public static String extractLeadingDigits(String s) {
		if (s == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler();

		char[] chars = s.toCharArray();

		for (char c : chars) {
			if (Validator.isDigit(c)) {
				sb.append(c);
			}
			else {
				return sb.toString();
			}
		}

		return sb.toString();
	}

	/**
	 * @deprecated As of 7.0.0, moved to {@link HighlightUtil#highlight(String,
	 *             String[])}}
	 */
	@Deprecated
	public static String highlight(String s, String[] queryTerms) {
		return HighlightUtil.highlight(s, queryTerms);
	}

	/**
	 * @deprecated As of 7.0.0, moved to {@link HighlightUtil#highlight(String,
	 *             String[], String, String)}}
	 */
	@Deprecated
	public static String highlight(
		String s, String[] queryTerms, String highlight1, String highlight2) {

		return HighlightUtil.highlight(s, queryTerms, highlight1, highlight2);
	}

	/**
	 * Returns the index within the string of the first occurrence of any
	 * character from the array.
	 *
	 * <p>
	 * A <code>null</code> string returns <code>-1</code>. A <code>null</code>
	 * or empty array returns <code>-1</code>.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * indexOfAny(null, *) returns -1
	 * indexOfAny(*, null) returns -1
	 * indexOfAny(*, []) returns -1
	 * indexOfAny("zzabyycdxx", ['a','c']) returns 2
	 * indexOfAny("zzabyycdxx", ['c','a']) returns 2
	 * indexOfAny("zzabyycdxx", ['m','n']) returns -1
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to search (optionally <code>null</code>)
	 * @param  chars the characters to search for (optionally <code>null</code>)
	 * @return the index within the string of the first occurrence of any
	 *         character from the array, or <code>-1</code> if none of the
	 *         characters occur
	 */
	public static int indexOfAny(String s, char[] chars) {
		if (s == null) {
			return -1;
		}

		return indexOfAny(s, chars, 0, s.length() - 1);
	}

	/**
	 * Returns the index within the string of the first occurrence of any
	 * character from the array, starting the search at the specified index
	 * within the string.
	 *
	 * <p>
	 * A <code>null</code> string returns <code>-1</code>. A <code>null</code>
	 * or empty array returns <code>-1</code>.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * indexOfAny(null, *, *) returns -1
	 * indexOfAny(*, null, *) returns -1
	 * indexOfAny(*, [], *) returns -1
	 * indexOfAny("zzabyycdxx", ['a','c'], 3) returns 6
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to search (optionally <code>null</code>)
	 * @param  chars the characters to search for (optionally <code>null</code>)
	 * @param  fromIndex the start index within the string
	 * @return the index within the string of the first occurrence of any
	 *         character from the array, starting the search at the specified
	 *         index within the string, or <code>-1</code> if none of the
	 *         characters occur
	 */
	public static int indexOfAny(String s, char[] chars, int fromIndex) {
		if (s == null) {
			return -1;
		}

		return indexOfAny(s, chars, fromIndex, s.length() - 1);
	}

	/**
	 * Returns the index within the string of the first occurrence of any
	 * character from the array, up to and including the specified end index
	 * within the string, starting the search at the specified start index
	 * within the string.
	 *
	 * <p>
	 * A <code>null</code> string returns <code>-1</code>. A <code>null</code>
	 * or empty array returns <code>-1</code>.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * indexOfAny(null, *, *, *) returns -1
	 * indexOfAny(*, null, *, *) returns -1
	 * indexOfAny(*, [], *, *) returns -1
	 * indexOfAny("zzabyycdxx", ['a','c'], 3, 7) returns 6
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to search (optionally <code>null</code>)
	 * @param  chars the characters to search for (optionally <code>null</code>)
	 * @param  fromIndex the start index within the string
	 * @param  toIndex the end index within the string
	 * @return the index within the string of the first occurrence of any
	 *         character from the array, up to and including the specified end
	 *         index within the string, starting the search at the specified
	 *         start index within the string, or <code>-1</code> if none of the
	 *         characters occur
	 */
	public static int indexOfAny(
		String s, char[] chars, int fromIndex, int toIndex) {

		if ((s == null) || (toIndex < fromIndex)) {
			return -1;
		}

		if (ArrayUtil.isEmpty(chars)) {
			return -1;
		}

		if (fromIndex >= s.length()) {
			return -1;
		}

		if (fromIndex < 0) {
			fromIndex = 0;
		}

		if (toIndex >= s.length()) {
			toIndex = s.length() - 1;
		}

		for (int i = fromIndex; i <= toIndex; i++) {
			char c = s.charAt(i);

			for (int j = 0; j < chars.length; j++) {
				if (c == chars[j]) {
					return i;
				}
			}
		}

		return -1;
	}

	/**
	 * Returns the index within the string of the first occurrence of any string
	 * from the array.
	 *
	 * <p>
	 * A <code>null</code> string returns <code>-1</code>. A <code>null</code>
	 * or empty array returns <code>-1</code>, but an array containing
	 * <code>""</code> returns <code>0</code> if the string is not
	 * <code>null</code>.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * indexOfAny(null, *) returns -1
	 * indexOfAny(*, null) returns -1
	 * indexOfAny(*, [null]) returns -1
	 * indexOfAny(*, []) returns -1
	 * indexOfAny("zzabyycdxx", ["ab","cd"]) returns 2
	 * indexOfAny("zzabyycdxx", ["cd","ab"]) returns 2
	 * indexOfAny("zzabyycdxx", ["mn","op"]) returns -1
	 * indexOfAny("zzabyycdxx", ["mn",""]) returns 0
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string (optionally <code>null</code>)
	 * @param  texts the strings to search for (optionally <code>null</code>)
	 * @return the index within the string of the first occurrence of any string
	 *         from the array, <code>0</code> if the search array contains
	 *         <code>""</code>, or <code>-1</code> if none of the strings occur
	 */
	public static int indexOfAny(String s, String[] texts) {
		if (s == null) {
			return -1;
		}

		return indexOfAny(s, texts, 0, s.length() - 1);
	}

	/**
	 * Returns the index within the string of the first occurrence of any string
	 * from the array, starting the search at the specified index within the
	 * string.
	 *
	 * <p>
	 * A <code>null</code> string returns <code>-1</code>. A <code>null</code>
	 * or empty array returns <code>-1</code>, but an array containing
	 * <code>""</code> returns the specified start index if the string is not
	 * <code>null</code>.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * indexOfAny(null, *, *) returns -1
	 * indexOfAny(*, null, *) returns -1
	 * indexOfAny(*, [null], *) returns -1
	 * indexOfAny(*, [], *) returns -1
	 * indexOfAny("zzabyycdxx", ["ab","cd"], 3) returns 6
	 * indexOfAny("zzabyycdxx", ["cd","ab"], 3) returns 6
	 * indexOfAny("zzabyycdxx", ["mn","op"], *) returns -1
	 * indexOfAny("zzabyycdxx", ["mn",""], 3) returns 3
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to search (optionally <code>null</code>)
	 * @param  texts the strings to search for (optionally <code>null</code>)
	 * @param  fromIndex the start index within the string
	 * @return the index within the string of the first occurrence of any string
	 *         from the array, starting the search at the specified index within
	 *         the string, the start index if the search array contains
	 *         <code>""</code>, or <code>-1</code> if none of the strings occur
	 */
	public static int indexOfAny(String s, String[] texts, int fromIndex) {
		if (s == null) {
			return -1;
		}

		return indexOfAny(s, texts, fromIndex, s.length() - 1);
	}

	/**
	 * Returns the index within the string of the first occurrence of any string
	 * from the array, up to and including the specified end index within the
	 * string, starting the search at the specified start index within the
	 * string.
	 *
	 * <p>
	 * A <code>null</code> string returns <code>-1</code>. A <code>null</code>
	 * or empty array returns <code>-1</code>, but an array containing
	 * <code>""</code> returns the specified start index if the string is not
	 * <code>null</code>.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * indexOfAny(null, *, *, *) returns -1
	 * indexOfAny(*, null, *, *) returns -1
	 * indexOfAny(*, [null], *, *) returns -1
	 * indexOfAny(*, [], *, *) returns -1
	 * indexOfAny("zzabyycdxx", ["ab","cd"], 3, 7) returns 6
	 * indexOfAny("zzabyycdxx", ["cd","ab"], 2, 7) returns 2
	 * indexOfAny("zzabyycdxx", ["mn","op"], *, *) returns -1
	 * indexOfAny("zzabyycdxx", ["mn",""], 3, *) returns 3
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to search (optionally <code>null</code>)
	 * @param  texts the strings to search for (optionally <code>null</code>)
	 * @param  fromIndex the start index within the string
	 * @param  toIndex the end index within the string
	 * @return the index within the string of the first occurrence of any string
	 *         from the array, up to and including the specified end index
	 *         within the string, starting the search at the specified start
	 *         index within the string, the start index if the search array
	 *         contains <code>""</code>, or <code>-1</code> if none of the
	 *         strings occur
	 */
	public static int indexOfAny(
		String s, String[] texts, int fromIndex, int toIndex) {

		if ((s == null) || (toIndex < fromIndex)) {
			return -1;
		}

		if (ArrayUtil.isEmpty(texts)) {
			return -1;
		}

		if (fromIndex >= s.length()) {
			return -1;
		}

		if (fromIndex < 0) {
			fromIndex = 0;
		}

		if (toIndex >= s.length()) {
			toIndex = s.length() - 1;
		}

		for (int i = fromIndex; i <= toIndex; i++) {
			for (int j = 0; j < texts.length; j++) {
				if (texts[j] == null) {
					continue;
				}

				if ((i + texts[j].length() <= toIndex + 1) &&
					s.startsWith(texts[j], i)) {

					return i;
				}
			}
		}

		return -1;
	}

	/**
	 * Inserts one string into the other at the specified offset index.
	 *
	 * @param  s the original string
	 * @param  insert the string to be inserted into the original string
	 * @param  offset the index of the original string where the insertion
	 *         should take place
	 * @return a string representing the original string with the other string
	 *         inserted at the specified offset index, or <code>null</code> if
	 *         the original string is <code>null</code>
	 */
	public static String insert(String s, String insert, int offset) {
		if (s == null) {
			return null;
		}

		if (insert == null) {
			return s;
		}

		if (offset > s.length()) {
			return s.concat(insert);
		}

		String prefix = s.substring(0, offset);
		String postfix = s.substring(offset);

		return prefix.concat(insert).concat(postfix);
	}

	/**
	 * Returns <code>true</code> if all the characters in string <code>s</code>
	 * are lower case, ignoring any non-alphabetic characters.
	 *
	 * @param  s the string in which to search
	 * @return <code>true</code> if all the characters in string <code>s</code>
	 *         are lower case, ignoring any non-alphabetic characters;
	 *         <code>false</code> otherwise
	 */
	public static boolean isLowerCase(String s) {
		if (s == null) {
			return false;
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// Fast path for ascii code, fallback to the slow unicode detection

			if (c <= 127) {
				if ((c >= CharPool.UPPER_CASE_A) &&
					(c <= CharPool.UPPER_CASE_Z)) {

					return false;
				}

				continue;
			}

			if (Character.isLetter(c) && Character.isUpperCase(c)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns <code>true</code> if all the characters in string <code>s</code>
	 * are upper case, ignoring any non-alphabetic characters.
	 *
	 * @param  s the string in which to search
	 * @return <code>true</code> if all the characters in string <code>s</code>
	 *         are upper case, ignoring any non-alphabetic characters;
	 *         <code>false</code> otherwise
	 */
	public static boolean isUpperCase(String s) {
		if (s == null) {
			return false;
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// Fast path for ascii code, fallback to the slow unicode detection

			if (c <= 127) {
				if ((c >= CharPool.LOWER_CASE_A) &&
					(c <= CharPool.LOWER_CASE_Z)) {

					return false;
				}

				continue;
			}

			if (Character.isLetter(c) && Character.isLowerCase(c)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the index within the string of the last occurrence of any
	 * character from the array.
	 *
	 * <p>
	 * A <code>null</code> string returns <code>-1</code>. A <code>null</code>
	 * or empty array returns <code>-1</code>.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * lastIndexOfAny(null, *) returns -1
	 * lastIndexOfAny(*, null) returns -1
	 * lastIndexOfAny(*, []) returns -1
	 * lastIndexOfAny("zzabyycdxx", ['a','c']) returns 6
	 * lastIndexOfAny("zzabyycdxx", ['c','a']) returns 6
	 * lastIndexOfAny("zzabyycdxx", ['m','n']) returns -1
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to search (optionally <code>null</code>)
	 * @param  chars the characters to search for (optionally <code>null</code>)
	 * @return the index within the string of the last occurrence of any
	 *         character from the array, or <code>-1</code> if none of the
	 *         characters occur
	 */
	public static int lastIndexOfAny(String s, char[] chars) {
		if (s == null) {
			return -1;
		}

		return lastIndexOfAny(s, chars, 0, s.length() - 1);
	}

	/**
	 * Returns the index within the string of the last occurrence of any
	 * character from the array, starting the search at the specified index
	 * within the string.
	 *
	 * <p>
	 * A <code>null</code> string returns <code>-1</code>. A <code>null</code>
	 * or empty array returns <code>-1</code>.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * lastIndexOfAny(null, *, *) returns -1
	 * lastIndexOfAny(*, null, *) returns -1
	 * lastIndexOfAny(*, [], *) returns -1
	 * lastIndexOfAny("zzabyycdxx", ['a','c'], 5) returns 2
	 * lastIndexOfAny("zzabyycdxx", ['m','n'], *) returns -1
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to search (optionally <code>null</code>)
	 * @param  chars the characters to search for (optionally <code>null</code>)
	 * @param  toIndex the end index within the string
	 * @return the index within the string of the last occurrence of any
	 *         character from the array, starting the search at the specified
	 *         index within the string, or <code>-1</code> if none of the
	 *         characters occur
	 */
	public static int lastIndexOfAny(String s, char[] chars, int toIndex) {
		if (s == null) {
			return -1;
		}

		return lastIndexOfAny(s, chars, 0, toIndex);
	}

	/**
	 * Returns the index within the string of the last occurrence of any
	 * character from the array, up to and including the specified end index
	 * within the string, starting the search at the specified start index
	 * within the string.
	 *
	 * <p>
	 * A <code>null</code> string returns <code>-1</code>. A <code>null</code>
	 * or empty array returns <code>-1</code>.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * lastIndexOfAny(null</code>, *, *, *) returns -1
	 * lastIndexOfAny(*, null</code>, *, *) returns -1
	 * lastIndexOfAny(*, [], *, *) returns -1
	 * lastIndexOfAny("zzabyycdxx", ['a','c'], 5, 7) returns 6
	 * lastIndexOfAny("zzabyycdxx", ['m','n'], *, *) returns -1
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to search (optionally <code>null</code>)
	 * @param  chars the characters to search for (optionally <code>null</code>)
	 * @param  fromIndex the start index within the string
	 * @param  toIndex the end index within the string
	 * @return the index within the string of the last occurrence of any
	 *         character from the array, up to and including the specified end
	 *         index within the string, starting the search at the specified
	 *         start index within the string, or <code>-1</code> if none of the
	 *         characters occur
	 */
	public static int lastIndexOfAny(
		String s, char[] chars, int fromIndex, int toIndex) {

		if ((s == null) || (toIndex < fromIndex)) {
			return -1;
		}

		if (ArrayUtil.isEmpty(chars)) {
			return -1;
		}

		if (fromIndex >= s.length()) {
			return -1;
		}

		if (fromIndex < 0) {
			fromIndex = 0;
		}

		if (toIndex >= s.length()) {
			toIndex = s.length() - 1;
		}

		for (int i = toIndex; i >= fromIndex; i--) {
			char c = s.charAt(i);

			for (int j = 0; j < chars.length; j++) {
				if (c == chars[j]) {
					return i;
				}
			}
		}

		return -1;
	}

	/**
	 * Returns the index within the string of the last occurrence of any string
	 * from the array.
	 *
	 * <p>
	 * A <code>null</code> string returns <code>-1</code>. A <code>null</code>
	 * or empty array returns <code>-1</code>, but an array containing
	 * <code>""</code> returns <code>0</code> if the string is not
	 * <code>null</code>.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * lastIndexOfAny(null</code>, *) returns -1
	 * lastIndexOfAny(*, null</code>) returns -1
	 * lastIndexOfAny(*, []) returns -1
	 * lastIndexOfAny(*, [null</code>]) returns -1
	 * lastIndexOfAny("zzabyycdxx", ["ab","cd"]) returns 6
	 * lastIndexOfAny("zzabyycdxx", ["cd","ab"]) returns 6
	 * lastIndexOfAny("zzabyycdxx", ["mn","op"]) returns -1
	 * lastIndexOfAny("zzabyycdxx", ["mn",""]) returns 10
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to search (optionally <code>null</code>)
	 * @param  texts the strings to search for (optionally <code>null</code>)
	 * @return the index within the string of the last occurrence of any string
	 *         from the array, <code>0</code> if the search array contains
	 *         <code>""</code>, or <code>-1</code> if none of the strings occur
	 */
	public static int lastIndexOfAny(String s, String[] texts) {
		if (s == null) {
			return -1;
		}

		return lastIndexOfAny(s, texts, 0, s.length() - 1);
	}

	/**
	 * Returns the index within the string of the last occurrence of any string
	 * from the array, starting the search at the specified index within the
	 * string.
	 *
	 * <p>
	 * A <code>null</code> string returns <code>-1</code>. A <code>null</code>
	 * or empty array returns <code>-1</code>, but an array containing
	 * <code>""</code> returns the specified start index if the string is not
	 * <code>null</code>.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * lastIndexOfAny(null, *, *) returns -1
	 * lastIndexOfAny(*, null, *) returns -1
	 * lastIndexOfAny(*, [], *) returns -1
	 * lastIndexOfAny(*, [null], *) returns -1
	 * lastIndexOfAny("zzabyycdxx", ["ab","cd"], 5) returns 2
	 * lastIndexOfAny("zzabyycdxx", ["cd","ab"], 5) returns 2
	 * lastIndexOfAny("zzabyycdxx", ["mn","op"], *) returns -1
	 * lastIndexOfAny("zzabyycdxx", ["mn",""], 5) returns 5
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to search (optionally <code>null</code>)
	 * @param  texts the strings to search for (optionally <code>null</code>)
	 * @param  toIndex the end index within the string
	 * @return the index within the string of the last occurrence of any string
	 *         from the array, starting the search at the specified index within
	 *         the string, the start index if the search array contains
	 *         <code>""</code>, or <code>-1</code> if none of the strings occur
	 */
	public static int lastIndexOfAny(String s, String[] texts, int toIndex) {
		if (s == null) {
			return -1;
		}

		return lastIndexOfAny(s, texts, 0, toIndex);
	}

	/**
	 * Returns the index within the string of the last occurrence of any string
	 * from the array, up to and including the specified end index within the
	 * string, starting the search at the specified start index within the
	 * string.
	 *
	 * <p>
	 * A <code>null</code> string returns <code>-1</code>. A <code>null</code>
	 * or empty array returns <code>-1</code>, but an array containing
	 * <code>""</code> returns the specified end index if the string is not
	 * <code>null</code>.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * lastIndexOfAny(null, *, *, *) returns -1
	 * lastIndexOfAny(*, null, *, *) returns -1
	 * lastIndexOfAny(*, [], *, *) returns -1
	 * lastIndexOfAny(*, [null], *, *) returns -1
	 * lastIndexOfAny("zzabyycdxx", ["ab","cd"], 2, 5) returns 2
	 * lastIndexOfAny("zzabyycdxx", ["mn","op"], *, *) returns -1
	 * lastIndexOfAny("zzabyycdxx", ["mn",""], 2, 5) returns 5
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to search (optionally <code>null</code>)
	 * @param  texts the strings to search for (optionally <code>null</code>)
	 * @param  fromIndex the start index within the string
	 * @param  toIndex the end index within the string
	 * @return the index within the string of the last occurrence of any string
	 *         from the array, up to and including the specified end index
	 *         within the string, starting the search at the specified start
	 *         index within the string, the end index if the search array
	 *         contains <code>""</code>, or <code>-1</code> if none of the
	 *         strings occur
	 */
	public static int lastIndexOfAny(
		String s, String[] texts, int fromIndex, int toIndex) {

		if ((s == null) || (toIndex < fromIndex)) {
			return -1;
		}

		if (ArrayUtil.isEmpty(texts)) {
			return -1;
		}

		if (fromIndex >= s.length()) {
			return -1;
		}

		if (fromIndex < 0) {
			fromIndex = 0;
		}

		if (toIndex >= s.length()) {
			toIndex = s.length() - 1;
		}

		for (int i = toIndex; i >= fromIndex; i--) {
			for (int j = 0; j < texts.length; j++) {
				if (texts[j] == null) {
					continue;
				}

				if ((i + texts[j].length() <= toIndex + 1) &&
					s.startsWith(texts[j], i)) {

					return i;
				}
			}
		}

		return -1;
	}

	/**
	 * Converts all of the characters in the string to lower case.
	 *
	 * @param  s the string to convert
	 * @return the string, converted to lower case, or <code>null</code> if the
	 *         string is <code>null</code>
	 * @see    String#toLowerCase()
	 */
	public static String lowerCase(String s) {
		return toLowerCase(s);
	}

	/**
	 * Converts all of the characters in the arbitrary number of strings to
	 * lower case.
	 *
	 * @param array the array or sequence of string arguments
	 */
	public static void lowerCase(String... array) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				array[i] = toLowerCase(array[i]);
			}
		}
	}

	/**
	 * Converts the first character of the string to lower case.
	 *
	 * @param  s the string whose first character is to be converted
	 * @return the string, with its first character converted to lower-case
	 */
	public static String lowerCaseFirstLetter(String s) {
		char[] chars = s.toCharArray();

		if ((chars[0] >= 65) && (chars[0] <= 90)) {
			chars[0] = (char)(chars[0] + 32);
		}

		return new String(chars);
	}

	/**
	 * Returns <code>true</code> if the specified pattern occurs at any position
	 * in the string.
	 *
	 * @param  s the string
	 * @param  pattern the pattern to search for in the string
	 * @return <code>true</code> if the specified pattern occurs at any position
	 *         in the string
	 */
	public static boolean matches(String s, String pattern) {
		String[] array = pattern.split("\\*");

		for (String element : array) {
			int pos = s.indexOf(element);

			if (pos == -1) {
				return false;
			}

			s = s.substring(pos + element.length());
		}

		return true;
	}

	/**
	 * Returns <code>true</code> if the specified pattern occurs at any position
	 * in the string, ignoring case.
	 *
	 * @param  s the string
	 * @param  pattern the pattern to search for in the string
	 * @return <code>true</code> if the specified pattern occurs at any position
	 *         in the string
	 */
	public static boolean matchesIgnoreCase(String s, String pattern) {
		return matches(lowerCase(s), lowerCase(pattern));
	}

	/**
	 * Merges the elements of the boolean array into a string representing a
	 * comma delimited list of its values.
	 *
	 * @param  array the boolean values to merge
	 * @return a string representing a comma delimited list of the values of the
	 *         boolean array, an empty string if the array is empty, or
	 *         <code>null</code> if the array is <code>null</code>
	 */
	public static String merge(boolean[] array) {
		return merge(array, StringPool.COMMA);
	}

	/**
	 * Merges the elements of the boolean array into a string representing a
	 * delimited list of its values.
	 *
	 * @param  array the boolean values to merge
	 * @param  delimiter the delimiter
	 * @return a string representing a comma delimited list of the values of the
	 *         boolean array, an empty string if the array is empty, or
	 *         <code>null</code> if the array is <code>null</code>
	 */
	public static String merge(boolean[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2 * array.length - 1);

		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				sb.append(delimiter);
			}

			sb.append(String.valueOf(array[i]));
		}

		return sb.toString();
	}

	/**
	 * Merges the elements of the character array into a string representing a
	 * comma delimited list of its values.
	 *
	 * @param  array the characters to merge
	 * @return a string representing a comma delimited list of the values of the
	 *         character array, an empty string if the array is empty, or
	 *         <code>null</code> if the array is <code>null</code>
	 */
	public static String merge(char[] array) {
		return merge(array, StringPool.COMMA);
	}

	/**
	 * Merges the elements of the character array into a string representing a
	 * delimited list of its values.
	 *
	 * @param  array the characters to merge
	 * @param  delimiter the delimiter
	 * @return a string representing a delimited list of the values of the
	 *         character array, an empty string if the array is empty, or
	 *         <code>null</code> if the array is <code>null</code>
	 */
	public static String merge(char[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2 * array.length - 1);

		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				sb.append(delimiter);
			}

			sb.append(String.valueOf(array[i]));
		}

		return sb.toString();
	}

	/**
	 * Merges the elements of the collection by returning a string representing
	 * a comma delimited list of its values.
	 *
	 * @param  col the collection of objects
	 * @return the merged collection elements, or <code>null</code> if the
	 *         collection is <code>null</code>
	 */
	public static String merge(Collection<?> col) {
		return merge(col, StringPool.COMMA);
	}

	/**
	 * Merges the elements of the collection by returning a string representing
	 * a delimited list of its values.
	 *
	 * @param  col the collection of objects
	 * @param  delimiter the string whose last index in the string marks where
	 *         to begin the substring
	 * @return the merged collection elements, or <code>null</code> if the
	 *         collection is <code>null</code>
	 */
	public static String merge(Collection<?> col, String delimiter) {
		if (col == null) {
			return null;
		}

		return merge(col.toArray(new Object[col.size()]), delimiter);
	}

	/**
	 * Merges the elements of an array of double-precision decimal numbers by
	 * returning a string representing a comma delimited list of its values.
	 *
	 * @param  array the doubles to merge
	 * @return a string representing a comma delimited list of the values of the
	 *         array of double-precision decimal numbers, an empty string if the
	 *         array is empty, or <code>null</code> if the array is
	 *         <code>null</code>
	 */
	public static String merge(double[] array) {
		return merge(array, StringPool.COMMA);
	}

	/**
	 * Merges the elements of an array of double-precision decimal numbers by
	 * returning a string representing a delimited list of its values.
	 *
	 * @param  array the doubles to merge
	 * @param  delimiter the delimiter
	 * @return a string representing a delimited list of the values of the array
	 *         of double-precision decimal numbers, an empty string if the array
	 *         is empty, or <code>null</code> if the array is <code>null</code>
	 */
	public static String merge(double[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2 * array.length - 1);

		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				sb.append(delimiter);
			}

			sb.append(String.valueOf(array[i]));
		}

		return sb.toString();
	}

	/**
	 * Merges the elements of an array of decimal numbers into a string
	 * representing a comma delimited list of its values.
	 *
	 * @param  array the floats to merge
	 * @return a string representing a comma delimited list of the values of the
	 *         array of decimal numbers, an empty string if the array is empty,
	 *         or <code>null</code> if the array is <code>null</code>
	 */
	public static String merge(float[] array) {
		return merge(array, StringPool.COMMA);
	}

	/**
	 * Merges the elements of an array of decimal numbers into a string
	 * representing a delimited list of its values.
	 *
	 * @param  array the floats to merge
	 * @param  delimiter the delimiter
	 * @return a string representing a delimited list of the values of the array
	 *         of decimal numbers, an empty string if the array is empty, or
	 *         <code>null</code> if the array is <code>null</code>
	 */
	public static String merge(float[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2 * array.length - 1);

		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				sb.append(delimiter);
			}

			sb.append(String.valueOf(array[i]));
		}

		return sb.toString();
	}

	/**
	 * Merges the elements of an array of integers into a string representing a
	 * comma delimited list of its values.
	 *
	 * @param  array the integers to merge
	 * @return a string representing a comma delimited list of the values of the
	 *         array of integers, an empty string if the array is empty, or
	 *         <code>null</code> if the array is <code>null</code>
	 */
	public static String merge(int[] array) {
		return merge(array, StringPool.COMMA);
	}

	/**
	 * Merges the elements of an array of integers into a string representing a
	 * delimited list of its values.
	 *
	 * @param  array the integers to merge
	 * @param  delimiter the delimiter
	 * @return a string representing a delimited list of the values of the array
	 *         of integers, an empty string if the array is empty, or
	 *         <code>null</code> if the array is <code>null</code>
	 */
	public static String merge(int[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2 * array.length - 1);

		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				sb.append(delimiter);
			}

			sb.append(String.valueOf(array[i]));
		}

		return sb.toString();
	}

	/**
	 * Merges the elements of an array of long integers by returning a string
	 * representing a comma delimited list of its values.
	 *
	 * @param  array the long integers to merge
	 * @return a string representing a comma delimited list of the values of the
	 *         array of long integers, an empty string if the array is empty, or
	 *         <code>null</code> if the array is <code>null</code>
	 */
	public static String merge(long[] array) {
		return merge(array, StringPool.COMMA);
	}

	/**
	 * Merges the elements of an array of long integers by returning a string
	 * representing a delimited list of its values.
	 *
	 * @param  array the long integers to merge
	 * @param  delimiter the delimiter
	 * @return a string representing a delimited list of the values of the array
	 *         of long integers, an empty string if the array is empty, or
	 *         <code>null</code> if the array is <code>null</code>
	 */
	public static String merge(long[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2 * array.length - 1);

		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				sb.append(delimiter);
			}

			sb.append(String.valueOf(array[i]));
		}

		return sb.toString();
	}

	/**
	 * Merges the elements of an array of objects into a string representing a
	 * comma delimited list of the objects.
	 *
	 * @param  array the objects to merge
	 * @return a string representing a comma delimited list of the objects, an
	 *         empty string if the array is empty, or <code>null</code> if the
	 *         array is <code>null</code>
	 */
	public static String merge(Object[] array) {
		return merge(array, StringPool.COMMA);
	}

	/**
	 * Merges the elements of an array of objects into a string representing a
	 * delimited list of the objects.
	 *
	 * @param  array the objects to merge
	 * @param  delimiter the delimiter
	 * @return a string representing a delimited list of the objects, an empty
	 *         string if the array is empty, or <code>null</code> if the array
	 *         is <code>null</code>
	 */
	public static String merge(Object[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2 * array.length - 1);

		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				sb.append(delimiter);
			}

			sb.append(String.valueOf(array[i]).trim());
		}

		return sb.toString();
	}

	/**
	 * Merges the elements of an array of short integers by returning a string
	 * representing a comma delimited list of its values.
	 *
	 * @param  array the short integers to merge
	 * @return a string representing a comma delimited list of the values of the
	 *         array of short integers, an empty string if the array is empty,
	 *         or <code>null</code> if the array is <code>null</code>
	 */
	public static String merge(short[] array) {
		return merge(array, StringPool.COMMA);
	}

	/**
	 * Merges the elements of an array of short integers by returning a string
	 * representing a delimited list of its values.
	 *
	 * @param  array the short integers to merge
	 * @param  delimiter the delimiter
	 * @return a string representing a delimited list of the values of the array
	 *         of short integers, an empty string if the array is empty, or
	 *         <code>null</code> if the array is <code>null</code>
	 */
	public static String merge(short[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2 * array.length - 1);

		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				sb.append(delimiter);
			}

			sb.append(String.valueOf(array[i]));
		}

		return sb.toString();
	}

	/**
	 * Returns the string enclosed by apostrophes.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * quote("Hello, World!") returns "'Hello, World!'"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to enclose in apostrophes
	 * @return the string enclosed by apostrophes, or <code>null</code> if the
	 *         string is <code>null</code>
	 */
	public static String quote(String s) {
		return quote(s, CharPool.APOSTROPHE);
	}

	/**
	 * Returns the string enclosed by the quote character.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * quote("PATH", '%') returns "%PATH%"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to enclose in quotes
	 * @param  quote the character to insert to insert to the beginning of and
	 *         append to the end of the string
	 * @return the string enclosed in the quote characters, or <code>null</code>
	 *         if the string is <code>null</code>
	 */
	public static String quote(String s, char quote) {
		if (s == null) {
			return null;
		}

		return quote(s, String.valueOf(quote));
	}

	/**
	 * Returns the string enclosed by the quote strings.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * quote("WARNING", "!!!") returns "!!!WARNING!!!"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to enclose in quotes
	 * @param  quote the quote string to insert to insert to the beginning of
	 *         and append to the end of the string
	 * @return the string enclosed in the quote strings, or <code>null</code> if
	 *         the string is <code>null</code>
	 */
	public static String quote(String s, String quote) {
		if (s == null) {
			return null;
		}

		return quote.concat(s).concat(quote);
	}

	/**
	 * Returns a randomized string of four lower case, alphabetic characters.
	 *
	 * @return a randomized string of four lower case, alphabetic characters
	 */
	public static String randomId() {
		Random random = new Random();

		char[] chars = new char[4];

		for (int i = 0; i < 4; i++) {
			chars[i] = (char)(CharPool.LOWER_CASE_A + random.nextInt(26));
		}

		return new String(chars);
	}

	/**
	 * Pseudorandomly permutes the characters of the string.
	 *
	 * @param  s the string whose characters are to be randomized
	 * @return a string of the same length as the string whose characters
	 *         represent a pseudorandom permutation of the characters of the
	 *         string
	 */
	public static String randomize(String s) {
		return RandomUtil.shuffle(s);
	}

	/**
	 * Returns a randomized string of eight characters consisting of lower case
	 * letters, upper case letters, and single-digit whole numbers.
	 *
	 * @return a randomized string of eight characters consisting of lower case
	 *         letters, upper case letters, and single-digit whole numbers
	 */
	public static String randomString() {
		return randomString(8);
	}

	/**
	 * Returns a randomized string of the specified length consisting of lower
	 * case letters, upper case letters, and single-digit whole numbers.
	 *
	 * @param  length the character length of the randomized string
	 * @return a randomized string of the specified length consisting of lower
	 *         case letters, upper case letters, and single-digit whole numbers
	 */
	public static String randomString(int length) {
		Random random = new Random();

		char[] chars = new char[length];

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(_RANDOM_STRING_CHAR_TABLE.length);

			chars[i] = _RANDOM_STRING_CHAR_TABLE[index];
		}

		return new String(chars);
	}

	public static String read(ClassLoader classLoader, String name)
		throws IOException {

		return read(classLoader, name, false);
	}

	public static String read(ClassLoader classLoader, String name, boolean all)
		throws IOException {

		if (all) {
			StringBundler sb = new StringBundler();

			Enumeration<URL> enu = classLoader.getResources(name);

			while (enu.hasMoreElements()) {
				URL url = enu.nextElement();

				InputStream is = url.openStream();

				if (is == null) {
					throw new IOException(
						"Unable to open resource at " + url.toString());
				}

				try {
					String s = read(is);

					if (s != null) {
						sb.append(s);
						sb.append(StringPool.NEW_LINE);
					}
				}
				finally {
					StreamUtil.cleanUp(is);
				}
			}

			return sb.toString().trim();
		}

		InputStream is = classLoader.getResourceAsStream(name);

		if (is == null) {
			throw new IOException(
				"Unable to open resource in class loader " + name);
		}

		try {
			String s = read(is);

			return s;
		}
		finally {
			StreamUtil.cleanUp(is);
		}
	}

	public static String read(InputStream is) throws IOException {
		StringBundler sb = new StringBundler();

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new InputStreamReader(is))) {

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				sb.append(line);
				sb.append(CharPool.NEW_LINE);
			}
		}

		return sb.toString().trim();
	}

	public static void readLines(InputStream is, Collection<String> lines)
		throws IOException {

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new InputStreamReader(is))) {

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				lines.add(line);
			}
		}
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #removeFromList(String,
	 *             String)}
	 */
	@Deprecated
	public static String remove(String s, String element) {
		return removeFromList(s, element, StringPool.COMMA);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #removeFromList(String,
	 *             String, String)}
	 */
	@Deprecated
	public static String remove(String s, String element, String delimiter) {
		return removeFromList(s, element, delimiter);
	}

	public static String removeChar(String s, char oldSub) {
		if (s == null) {
			return null;
		}

		int y = s.indexOf(oldSub);

		if (y >= 0) {
			StringBundler sb = new StringBundler();

			int x = 0;

			while (x <= y) {
				sb.append(s.substring(x, y));

				x = y + 1;
				y = s.indexOf(oldSub, x);
			}

			sb.append(s.substring(x));

			return sb.toString();
		}
		else {
			return s;
		}
	}

	public static String removeChars(String s, char... oldSubs) {
		if (s == null) {
			return null;
		}

		if (oldSubs == null) {
			return s;
		}

		StringBuilder sb = new StringBuilder(s.length());

		iterate:
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			for (int j = 0; j < oldSubs.length; j++) {
				if (c == oldSubs[j]) {
					continue iterate;
				}
			}

			sb.append(c);
		}

		if (s.length() == sb.length()) {
			return s;
		}

		return sb.toString();
	}

	/**
	 * Removes the <code>remove</code> string from string <code>s</code> that
	 * represents a list of comma delimited strings.
	 *
	 * <p>
	 * The resulting string ends with a comma even if the original string does
	 * not.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * remove("red,blue,green,yellow", "blue") returns "red,green,yellow,"
	 * remove("blue", "blue") returns ""
	 * remove("blue,", "blue") returns ""
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string representing the list of comma delimited strings
	 * @param  element the string to remove
	 * @return a string representing the list of comma delimited strings with
	 *         the <code>remove</code> string removed, or <code>null</code> if
	 *         the original string, the string to remove, or the delimiter is
	 *         <code>null</code>
	 */
	public static String removeFromList(String s, String element) {
		return removeFromList(s, element, StringPool.COMMA);
	}

	/**
	 * Removes the <code>remove</code> string from string <code>s</code> that
	 * represents a list of delimited strings.
	 *
	 * <p>
	 * The resulting string ends with the delimiter even if the original string
	 * does not.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * remove("red;blue;green;yellow", "blue", ";") returns "red;green;yellow;"
	 * remove("blue", "blue", ";") returns ""
	 * remove("blue;", "blue", ";") returns ""
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string representing the list of delimited strings
	 * @param  element the string to remove
	 * @param  delimiter the delimiter
	 * @return a string representing the list of delimited strings with the
	 *         <code>remove</code> string removed, or <code>null</code> if the
	 *         original string, the string to remove, or the delimiter is
	 *         <code>null</code>
	 */
	public static String removeFromList(
		String s, String element, String delimiter) {

		if ((s == null) || (element == null) || (delimiter == null)) {
			return null;
		}

		if (Validator.isNotNull(s) && !s.endsWith(delimiter)) {
			s += delimiter;
		}

		String drd = delimiter.concat(element).concat(delimiter);

		String rd = element.concat(delimiter);

		while (contains(s, element, delimiter)) {
			int pos = s.indexOf(drd);

			if (pos == -1) {
				if (s.startsWith(rd)) {
					int x = element.length() + delimiter.length();
					int y = s.length();

					s = s.substring(x, y);
				}
			}
			else {
				int x = pos + element.length() + delimiter.length();
				int y = s.length();

				String temp = s.substring(0, pos);

				s = temp.concat(s.substring(x, y));
			}
		}

		return s;
	}

	public static String removeSubstring(String s, String oldSub) {
		if (s == null) {
			return null;
		}

		if (oldSub == null) {
			return s;
		}

		int y = s.indexOf(oldSub);

		if (y >= 0) {
			StringBundler sb = new StringBundler();

			int length = oldSub.length();
			int x = 0;

			while (x <= y) {
				sb.append(s.substring(x, y));

				x = y + length;
				y = s.indexOf(oldSub, x);
			}

			sb.append(s.substring(x));

			return sb.toString();
		}
		else {
			return s;
		}
	}

	public static String removeSubstrings(String s, String... oldSubs) {
		if (s == null) {
			return null;
		}

		if (ArrayUtil.isEmpty(oldSubs)) {
			return s;
		}

		for (String oldSub : oldSubs) {
			s = removeSubstring(s, oldSub);
		}

		return s;
	}

	/**
	 * Replaces all occurrences of the character with the new character.
	 *
	 * @param  s the original string
	 * @param  oldSub the character to be searched for and replaced in the
	 *         original string
	 * @param  newSub the character with which to replace the
	 *         <code>oldSub</code> character
	 * @return a string representing the original string with all occurrences of
	 *         the <code>oldSub</code> character replaced with the
	 *         <code>newSub</code> character, or <code>null</code> if the
	 *         original string is <code>null</code>
	 */
	public static String replace(String s, char oldSub, char newSub) {
		if (s == null) {
			return null;
		}

		return s.replace(oldSub, newSub);
	}

	/**
	 * Replaces all occurrences of the character with the new string.
	 *
	 * @param  s the original string
	 * @param  oldSub the character to be searched for and replaced in the
	 *         original string
	 * @param  newSub the string with which to replace the <code>oldSub</code>
	 *         character
	 * @return a string representing the original string with all occurrences of
	 *         the <code>oldSub</code> character replaced with the string
	 *         <code>newSub</code>, or <code>null</code> if the original string
	 *         is <code>null</code>
	 */
	public static String replace(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}

		int index = s.indexOf(oldSub);

		if (index == -1) {
			return s;
		}

		int previousIndex = index;

		StringBundler sb = new StringBundler();

		if (previousIndex != 0) {
			sb.append(s.substring(0, previousIndex));
		}

		sb.append(newSub);

		while ((index = s.indexOf(oldSub, previousIndex + 1)) != -1) {
			sb.append(s.substring(previousIndex + 1, index));
			sb.append(newSub);

			previousIndex = index;
		}

		index = previousIndex + 1;

		if (index < s.length()) {
			sb.append(s.substring(index));
		}

		return sb.toString();
	}

	public static String replace(String s, char[] oldSubs, char[] newSubs) {
		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		StringBuilder sb = new StringBuilder(s.length());

		sb.append(s);

		boolean modified = false;

		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);

			for (int j = 0; j < oldSubs.length; j++) {
				if (c == oldSubs[j]) {
					sb.setCharAt(i, newSubs[j]);

					modified = true;

					break;
				}
			}
		}

		if (modified) {
			return sb.toString();
		}

		return s;
	}

	public static String replace(String s, char[] oldSubs, String[] newSubs) {
		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		StringBundler sb = null;

		int lastReplacementIndex = 0;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			for (int j = 0; j < oldSubs.length; j++) {
				if (c == oldSubs[j]) {
					if (sb == null) {
						sb = new StringBundler();
					}

					if (i > lastReplacementIndex) {
						sb.append(s.substring(lastReplacementIndex, i));
					}

					sb.append(newSubs[j]);

					lastReplacementIndex = i + 1;

					break;
				}
			}
		}

		if (sb == null) {
			return s;
		}

		if (lastReplacementIndex < s.length()) {
			sb.append(s.substring(lastReplacementIndex));
		}

		return sb.toString();
	}

	/**
	 * Replaces all occurrences of the string with the new string.
	 *
	 * @param  s the original string
	 * @param  oldSub the string to be searched for and replaced in the original
	 *         string
	 * @param  newSub the string with which to replace the <code>oldSub</code>
	 *         string
	 * @return a string representing the original string with all occurrences of
	 *         the <code>oldSub</code> string replaced with the string
	 *         <code>newSub</code>, or <code>null</code> if the original string
	 *         is <code>null</code>
	 */
	public static String replace(String s, String oldSub, String newSub) {
		return replace(s, oldSub, newSub, 0);
	}

	/**
	 * Replaces all occurrences of the string with the new string, starting from
	 * the specified index.
	 *
	 * @param  s the original string
	 * @param  oldSub the string to be searched for and replaced in the original
	 *         string
	 * @param  newSub the string with which to replace the <code>oldSub</code>
	 *         string
	 * @param  fromIndex the index of the original string from which to begin
	 *         searching
	 * @return a string representing the original string with all occurrences of
	 *         the <code>oldSub</code> string occurring after the specified
	 *         index replaced with the string <code>newSub</code>, or
	 *         <code>null</code> if the original string is <code>null</code>
	 */
	public static String replace(
		String s, String oldSub, String newSub, int fromIndex) {

		if (s == null) {
			return null;
		}

		if ((oldSub == null) || oldSub.equals(StringPool.BLANK)) {
			return s;
		}

		if (newSub == null) {
			newSub = StringPool.BLANK;
		}

		int y = s.indexOf(oldSub, fromIndex);

		if (y >= 0) {
			StringBundler sb = new StringBundler();

			int length = oldSub.length();
			int x = 0;

			while (x <= y) {
				sb.append(s.substring(x, y));
				sb.append(newSub);

				x = y + length;
				y = s.indexOf(oldSub, x);
			}

			sb.append(s.substring(x));

			return sb.toString();
		}
		else {
			return s;
		}
	}

	/**
	 * Replaces all occurrences of the keywords found in the substring, defined
	 * by the beginning and ending strings, with the new values.
	 *
	 * <p>
	 * For example, with the following initialized variables:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * String s = "http://www.example-url/${userId}";
	 * String begin = "${";
	 * String end = "}";
	 * Map<String, String> values =  new HashMap&#60;String, String&#62;();
	 * values.put("userId", "jbloggs");
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * <p>
	 * <code>replace(s, begin, end, values)</code> returns
	 * <code>"http://www.example-url/jbloggs"</code>
	 * </p>
	 *
	 * @param  s the original string
	 * @param  begin the string preceding the substring to be modified. This
	 *         string is excluded from the result.
	 * @param  end the string following the substring to be modified. This
	 *         string is excluded from the result.
	 * @param  values the key-value map values
	 * @return a string representing the original string with all occurrences of
	 *         the of the keywords found in the substring, replaced with the new
	 *         values. <code>null</code> is returned if the original string, the
	 *         beginning string, the ending string, or the key-map values are
	 *         <code>null</code>.
	 */
	public static String replace(
		String s, String begin, String end, Map<String, String> values) {

		StringBundler sb = replaceToStringBundler(s, begin, end, values);

		return sb.toString();
	}

	/**
	 * Replaces all occurrences of the elements of the string array with the
	 * corresponding elements of the new string array.
	 *
	 * @param  s the original string
	 * @param  oldSubs the strings to be searched for and replaced in the
	 *         original string
	 * @param  newSubs the strings with which to replace the
	 *         <code>oldSubs</code> strings
	 * @return a string representing the original string with all occurrences of
	 *         the <code>oldSubs</code> strings replaced with the corresponding
	 *         <code>newSubs</code> strings, or <code>null</code> if the
	 *         original string, the <code>oldSubs</code> array, or the
	 *         <code>newSubs</code> is <code>null</code>
	 */
	public static String replace(String s, String[] oldSubs, String[] newSubs) {
		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = replace(s, oldSubs[i], newSubs[i]);
		}

		return s;
	}

	/**
	 * Replaces all occurrences of the elements of the string array with the
	 * corresponding elements of the new string array, optionally replacing only
	 * substrings that are surrounded by word boundaries.
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * replace("redorangeyellow", {"red", "orange", "yellow"}, {"RED","ORANGE", "YELLOW"}, false) returns "REDORANGEYELLOW"
	 * replace("redorangeyellow", {"red", "orange", "yellow"}, {"RED","ORANGE", "YELLOW"}, true) returns "redorangeyellow"
	 * replace("redorange yellow", {"red", "orange", "yellow"}, {"RED","ORANGE", "YELLOW"}, false) returns "REDORANGE YELLOW"
	 * replace("redorange yellow", {"red", "orange", "yellow"}, {"RED","ORANGE", "YELLOW"}, true) returns "redorange YELLOW"
	 * replace("red orange yellow", {"red", "orange", "yellow"}, {"RED","ORANGE", "YELLOW"}, false) returns "RED ORANGE YELLOW"
	 * replace("redorange.yellow", {"red", "orange", "yellow"}, {"RED","ORANGE", * "YELLOW"}, true) returns "redorange.YELLOW"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the original string
	 * @param  oldSubs the strings to be searched for and replaced in the
	 *         original string
	 * @param  newSubs the strings with which to replace the
	 *         <code>oldSubs</code> strings
	 * @param  exactMatch whether or not to replace only substrings of
	 *         <code>s</code> that are surrounded by word boundaries
	 * @return if <code>exactMatch</code> is <code>true</code>, a string
	 *         representing the original string with all occurrences of the
	 *         <code>oldSubs</code> strings that are surrounded by word
	 *         boundaries replaced with the corresponding <code>newSubs</code>
	 *         strings, or else a string representing the original string with
	 *         all occurrences of the <code>oldSubs</code> strings replaced with
	 *         the corresponding <code>newSubs</code> strings, or
	 *         <code>null</code> if the original string, the
	 *         <code>oldSubs</code> array, or the <code>newSubs</code> is
	 *         <code>null</code>
	 */
	public static String replace(
		String s, String[] oldSubs, String[] newSubs, boolean exactMatch) {

		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		if (!exactMatch) {
			return replace(s, oldSubs, newSubs);
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = s.replaceAll("\\b" + oldSubs[i] + "\\b", newSubs[i]);
		}

		return s;
	}

	/**
	 * Replaces the first occurrence of the character with the new character.
	 *
	 * @param  s the original string
	 * @param  oldSub the character whose first occurrence in the original
	 *         string is to be searched for and replaced
	 * @param  newSub the character with which to replace the first occurrence
	 *         of the <code>oldSub</code> character
	 * @return a string representing the original string except with the first
	 *         occurrence of the character <code>oldSub</code> replaced with the
	 *         character <code>newSub</code>
	 */
	public static String replaceFirst(String s, char oldSub, char newSub) {
		if (s == null) {
			return null;
		}

		return replaceFirst(s, String.valueOf(oldSub), String.valueOf(newSub));
	}

	/**
	 * Replaces the first occurrence of the character with the new string.
	 *
	 * @param  s the original string
	 * @param  oldSub the character whose first occurrence in the original
	 *         string is to be searched for and replaced
	 * @param  newSub the string with which to replace the first occurrence of
	 *         the <code>oldSub</code> character
	 * @return a string representing the original string except with the first
	 *         occurrence of the character <code>oldSub</code> replaced with the
	 *         string <code>newSub</code>
	 */
	public static String replaceFirst(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}

		return replaceFirst(s, String.valueOf(oldSub), newSub);
	}

	/**
	 * Replaces the first occurrence of the string with the new string.
	 *
	 * @param  s the original string
	 * @param  oldSub the string whose first occurrence in the original string
	 *         is to be searched for and replaced
	 * @param  newSub the string with which to replace the first occurrence of
	 *         the <code>oldSub</code> string
	 * @return a string representing the original string except with the first
	 *         occurrence of the string <code>oldSub</code> replaced with the
	 *         string <code>newSub</code>
	 */
	public static String replaceFirst(String s, String oldSub, String newSub) {
		return replaceFirst(s, oldSub, newSub, 0);
	}

	/**
	 * Replaces the first occurrences of the elements of the string array with
	 * the corresponding elements of the new string array, beginning the element
	 * search from the index position.
	 *
	 * @param  s the original string
	 * @param  oldSub the strings whose first occurrences are to be searched for
	 *         and replaced in the original string
	 * @param  newSub the strings with which to replace the first occurrences of
	 *         the <code>oldSubs</code> strings
	 * @param  fromIndex the start index within the string
	 * @return a string representing the original string with the first
	 *         occurrences of the <code>oldSubs</code> strings replaced with the
	 *         corresponding <code>newSubs</code> strings, or <code>null</code>
	 *         if the original string, the <code>oldSubs</code> string, or the
	 *         <code>newSubs</code> string is <code>null</code>
	 */
	public static String replaceFirst(
		String s, String oldSub, String newSub, int fromIndex) {

		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		if (oldSub.equals(newSub)) {
			return s;
		}

		int y = s.indexOf(oldSub, fromIndex);

		if (y >= 0) {
			return s.substring(0, y).concat(newSub).concat(
				s.substring(y + oldSub.length()));
		}
		else {
			return s;
		}
	}

	/**
	 * Replaces the first occurrences of the elements of the string array with
	 * the corresponding elements of the new string array.
	 *
	 * @param  s the original string
	 * @param  oldSubs the strings whose first occurrences are to be searched
	 *         for and replaced in the original string
	 * @param  newSubs the strings with which to replace the first occurrences
	 *         of the <code>oldSubs</code> strings
	 * @return a string representing the original string with the first
	 *         occurrences of the <code>oldSubs</code> strings replaced with the
	 *         corresponding <code>newSubs</code> strings, or <code>null</code>
	 *         if the original string, the <code>oldSubs</code> array, or the
	 *         <code>newSubs</code> is <code>null</code>
	 */
	public static String replaceFirst(
		String s, String[] oldSubs, String[] newSubs) {

		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = replaceFirst(s, oldSubs[i], newSubs[i]);
		}

		return s;
	}

	/**
	 * Replaces the last occurrence of the character with the new character.
	 *
	 * @param  s the original string
	 * @param  oldSub the character whose last occurrence in the original string
	 *         is to be searched for and replaced
	 * @param  newSub the character with which to replace the last occurrence of
	 *         the <code>oldSub</code> character
	 * @return a string representing the original string except with the first
	 *         occurrence of the character <code>oldSub</code> replaced with the
	 *         character <code>newSub</code>
	 */
	public static String replaceLast(String s, char oldSub, char newSub) {
		if (s == null) {
			return null;
		}

		return replaceLast(s, String.valueOf(oldSub), String.valueOf(newSub));
	}

	/**
	 * Replaces the last occurrence of the character with the new string.
	 *
	 * @param  s the original string
	 * @param  oldSub the character whose last occurrence in the original string
	 *         is to be searched for and replaced
	 * @param  newSub the string with which to replace the last occurrence of
	 *         the <code>oldSub</code> character
	 * @return a string representing the original string except with the last
	 *         occurrence of the character <code>oldSub</code> replaced with the
	 *         string <code>newSub</code>
	 */
	public static String replaceLast(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}

		return replaceLast(s, String.valueOf(oldSub), newSub);
	}

	/**
	 * Replaces the last occurrence of the string <code>oldSub</code> in the
	 * string <code>s</code> with the string <code>newSub</code>.
	 *
	 * @param  s the original string
	 * @param  oldSub the string whose last occurrence in the original string is
	 *         to be searched for and replaced
	 * @param  newSub the string with which to replace the last occurrence of
	 *         the <code>oldSub</code> string
	 * @return a string representing the original string except with the last
	 *         occurrence of the string <code>oldSub</code> replaced with the
	 *         string <code>newSub</code>
	 */
	public static String replaceLast(String s, String oldSub, String newSub) {
		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		if (oldSub.equals(newSub)) {
			return s;
		}

		int y = s.lastIndexOf(oldSub);

		if (y >= 0) {
			return s.substring(0, y).concat(newSub).concat(
				s.substring(y + oldSub.length()));
		}
		else {
			return s;
		}
	}

	/**
	 * Replaces the last occurrences of the elements of the string array with
	 * the corresponding elements of the new string array.
	 *
	 * @param  s the original string
	 * @param  oldSubs the strings whose last occurrences are to be searched for
	 *         and replaced in the original string
	 * @param  newSubs the strings with which to replace the last occurrences of
	 *         the <code>oldSubs</code> strings
	 * @return a string representing the original string with the last
	 *         occurrences of the <code>oldSubs</code> strings replaced with the
	 *         corresponding <code>newSubs</code> strings, or <code>null</code>
	 *         if the original string, the <code>oldSubs</code> array, or the
	 *         <code>newSubs</code> is <code>null</code>
	 */
	public static String replaceLast(
		String s, String[] oldSubs, String[] newSubs) {

		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = replaceLast(s, oldSubs[i], newSubs[i]);
		}

		return s;
	}

	/**
	 * Replaces all occurrences of the keywords found in the substring, defined
	 * by the beginning and ending strings, with the new values. The result is
	 * returned as a {@link StringBundler}.
	 *
	 * <p>
	 * For example, with the following initialized variables:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * String s = "http://www.example-url/${userId}";
	 * String begin = "${";
	 * String end = "}";
	 * Map<String, String> values =  new HashMap&#60;String, String&#62;();
	 * values.put("userId", "jbloggs");
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * <p>
	 * <code>StringBundler sb = replaceToStringBundler(s, begin, end,
	 * values)</code> <code>sb.toString()</code> returns
	 * <code>"http://www.example-url/jbloggs"</code>
	 * </p>
	 *
	 * @param  s the original string
	 * @param  begin the string preceding the substring to be modified. This
	 *         string is excluded from the result.
	 * @param  end the string following the substring to be modified. This
	 *         string is excluded from the result.
	 * @param  values the key-value map values
	 * @return a string bundler representing the original string with all
	 *         occurrences of the keywords found in the substring, replaced with
	 *         the new values. <code>null</code> is returned if the original
	 *         string, the beginning string, the ending string, or the key-map
	 *         values are <code>null</code>.
	 * @see    #replace(String, String, String, Map)
	 */
	public static StringBundler replaceToStringBundler(
		String s, String begin, String end, Map<String, String> values) {

		if (Validator.isBlank(s) || Validator.isBlank(begin) ||
			Validator.isBlank(end) || MapUtil.isEmpty(values)) {

			return new StringBundler(s);
		}

		StringBundler sb = new StringBundler(values.size() * 2 + 1);

		int pos = 0;

		while (true) {
			int x = s.indexOf(begin, pos);
			int y = s.indexOf(end, x + begin.length());

			if ((x == -1) || (y == -1)) {
				sb.append(s.substring(pos));

				break;
			}
			else {
				sb.append(s.substring(pos, x));

				String oldValue = s.substring(x + begin.length(), y);

				String newValue = values.get(oldValue);

				if (newValue == null) {
					newValue = oldValue;
				}

				sb.append(newValue);

				pos = y + end.length();
			}
		}

		return sb;
	}

	/**
	 * Replaces all occurrences of the keywords found in the substring, defined
	 * by the beginning and ending strings, with the new values. The result is
	 * returned as a {@link StringBundler}.
	 *
	 * @param  s the original string
	 * @param  begin the string preceding the substring to be modified. This
	 *         string is removed from the result.
	 * @param  end the string following the substring to be modified. This
	 *         string is removed from the result.
	 * @param  values the key-value map values, which has string keys and {@link
	 *         StringBundler} values
	 * @return a string bundler representing the original string with all
	 *         occurrences of the keywords found in the substring, replaced with
	 *         the new values. <code>null</code> is returned if the original
	 *         string, the beginning string, the ending string, or the key-map
	 *         values are <code>null</code>.
	 */
	public static StringBundler replaceWithStringBundler(
		String s, String begin, String end, Map<String, StringBundler> values) {

		if (Validator.isBlank(s) || Validator.isBlank(begin) ||
			Validator.isBlank(end) || MapUtil.isEmpty(values)) {

			return new StringBundler(s);
		}

		int size = values.size() + 1;

		for (StringBundler valueSB : values.values()) {
			size += valueSB.index();
		}

		StringBundler sb = new StringBundler(size);

		int pos = 0;

		while (true) {
			int x = s.indexOf(begin, pos);
			int y = s.indexOf(end, x + begin.length());

			if ((x == -1) || (y == -1)) {
				sb.append(s.substring(pos));

				break;
			}
			else {
				sb.append(s.substring(pos, x));

				String oldValue = s.substring(x + begin.length(), y);

				StringBundler newValue = values.get(oldValue);

				if (newValue == null) {
					sb.append(oldValue);
				}
				else {
					sb.append(newValue);
				}

				pos = y + end.length();
			}
		}

		return sb;
	}

	/**
	 * Reverses the order of the characters of the string.
	 *
	 * @param  s the original string
	 * @return a string representing the original string with characters in
	 *         reverse order
	 */
	public static String reverse(String s) {
		if (s == null) {
			return null;
		}

		char[] chars = s.toCharArray();
		char[] reverse = new char[chars.length];

		for (int i = 0; i < chars.length; i++) {
			reverse[i] = chars[chars.length - i - 1];
		}

		return new String(reverse);
	}

	/**
	 * Replaces all double slashes of the string with single slashes.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * safePath("http://www.liferay.com") returns "http:/www.liferay.com"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  path the original string
	 * @return a string representing the original string with all double slashes
	 *         replaced with single slashes
	 */
	public static String safePath(String path) {
		return replace(path, StringPool.DOUBLE_SLASH, StringPool.SLASH);
	}

	/**
	 * Returns a string representing the original string appended with suffix
	 * "..." and then shortened to 20 characters.
	 *
	 * <p>
	 * The suffix is only added if the original string exceeds 20 characters. If
	 * the original string exceeds 20 characters and it contains whitespace, the
	 * string is shortened at the first whitespace character.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * shorten("12345678901234567890xyz") returns "12345678901234567..."
	 * shorten("1 345678901234567890xyz") returns "1..."
	 * shorten(" 2345678901234567890xyz") returns "..."
	 * shorten("12345678901234567890") returns "12345678901234567890"
	 * shorten(" 2345678901234567890") returns " 2345678901234567890"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the original string
	 * @return a string representing the original string shortened to 20
	 *         characters, with suffix "..." appended to it
	 */
	public static String shorten(String s) {
		return shorten(s, 20);
	}

	/**
	 * Returns a string representing the original string appended with suffix
	 * "..." and then shortened to the specified length.
	 *
	 * <p>
	 * The suffix is only added if the original string exceeds the specified
	 * length. If the original string exceeds the specified length and it
	 * contains whitespace, the string is shortened at the first whitespace
	 * character.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * shorten("123456789", 8) returns "12345..."
	 * shorten("1 3456789", 8) returns "1..."
	 * shorten(" 23456789", 8) returns "..."
	 * shorten("12345678", 8) returns "12345678"
	 * shorten(" 1234567", 8) returns " 1234567"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the original string
	 * @param  length the number of characters to limit from the original string
	 * @return a string representing the original string shortened to the
	 *         specified length, with suffix "..." appended to it
	 */
	public static String shorten(String s, int length) {
		return shorten(s, length, "...");
	}

	/**
	 * Returns a string representing the original string appended with the
	 * specified suffix and then shortened to the specified length.
	 *
	 * <p>
	 * The suffix is only added if the original string exceeds the specified
	 * length. If the original string exceeds the specified length and it
	 * contains whitespace, the string is shortened at the first whitespace
	 * character.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * shorten("12345678901234", 13, "... etc.") returns "12345... etc."
	 * shorten("1 345678901234", 13, "... etc.") returns "1... etc."
	 * shorten(" 2345678901234", 13, "... etc.") returns "... etc."
	 * shorten("1234567890123", 13, "... etc.") returns "1234567890123"
	 * shorten(" 123456789012", 13, "... etc.") returns " 123456789012"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the original string
	 * @param  length the number of characters to limit from the original string
	 * @param  suffix the suffix to append
	 * @return a string representing the original string shortened to the
	 *         specified length, with the specified suffix appended to it
	 */
	public static String shorten(String s, int length, String suffix) {
		if ((s == null) || (suffix == null)) {
			return null;
		}

		if (s.length() <= length) {
			return s;
		}

		if (length < suffix.length()) {
			return s.substring(0, length);
		}

		int curLength = length;

		for (int j = (curLength - suffix.length()); j >= 0; j--) {
			if (Character.isWhitespace(s.charAt(j))) {
				curLength = j;

				break;
			}
		}

		if (curLength == length) {
			curLength = length - suffix.length();
		}

		String temp = s.substring(0, curLength);

		return temp.concat(suffix);
	}

	/**
	 * Returns a string representing the original string appended with the
	 * specified suffix and then shortened to 20 characters.
	 *
	 * <p>
	 * The suffix is only added if the original string exceeds 20 characters. If
	 * the original string exceeds 20 characters and it contains whitespace, the
	 * string is shortened at the first whitespace character.
	 * </p>
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * shorten("12345678901234567890xyz", "... etc.") returns "123456789012... etc."
	 * shorten("1 345678901234567890xyz", "... etc.") returns "1... etc."
	 * shorten(" 2345678901234567890xyz", "... etc.") returns "... etc."
	 * shorten("12345678901234567890", "... etc.") returns "12345678901234567890"
	 * shorten(" 2345678901234567890", "... etc.") returns " 2345678901234567890"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the original string
	 * @param  suffix the suffix to append
	 * @return a string representing the original string shortened to 20
	 *         characters, with the specified suffix appended to it
	 */
	public static String shorten(String s, String suffix) {
		return shorten(s, 20, suffix);
	}

	/**
	 * Splits string <code>s</code> around comma characters.
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * split("Alice,Bob,Charlie") returns {"Alice", "Bob", "Charlie"}
	 * split("Alice, Bob, Charlie") returns {"Alice", " Bob", " Charlie"}
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to split
	 * @return the array of strings resulting from splitting string
	 *         <code>s</code> around comma characters, or an empty string array
	 *         if <code>s</code> is <code>null</code> or <code>s</code> is empty
	 */
	public static String[] split(String s) {
		return split(s, CharPool.COMMA);
	}

	/**
	 * Splits the string <code>s</code> around comma characters returning the
	 * boolean values of the substrings.
	 *
	 * @param  s the string to split
	 * @param  x the default value to use for a substring in case an exception
	 *         occurs in getting the boolean value for that substring
	 * @return the array of boolean values resulting from splitting string
	 *         <code>s</code> around comma characters, or an empty array if
	 *         <code>s</code> is <code>null</code>
	 */
	public static boolean[] split(String s, boolean x) {
		return split(s, StringPool.COMMA, x);
	}

	/**
	 * Splits the string <code>s</code> around the specified delimiter.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * splitLines("First;Second;Third", ';') returns {"First","Second","Third"}
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to split
	 * @param  delimiter the delimiter
	 * @return the array of strings resulting from splitting string
	 *         <code>s</code> around the specified delimiter character, or an
	 *         empty string array if <code>s</code> is <code>null</code> or if
	 *         <code>s</code> is empty
	 */
	public static String[] split(String s, char delimiter) {
		if (Validator.isNull(s)) {
			return _emptyStringArray;
		}

		s = s.trim();

		if (s.length() == 0) {
			return _emptyStringArray;
		}

		if ((delimiter == CharPool.RETURN) ||
			(delimiter == CharPool.NEW_LINE)) {

			return splitLines(s);
		}

		List<String> nodeValues = new ArrayList<>();

		int offset = 0;
		int pos = s.indexOf(delimiter, offset);

		while (pos != -1) {
			nodeValues.add(s.substring(offset, pos));

			offset = pos + 1;
			pos = s.indexOf(delimiter, offset);
		}

		if (offset < s.length()) {
			nodeValues.add(s.substring(offset));
		}

		return nodeValues.toArray(new String[nodeValues.size()]);
	}

	/**
	 * Splits the string <code>s</code> around comma characters returning the
	 * double-precision decimal values of the substrings.
	 *
	 * @param  s the string to split
	 * @param  x the default value to use for a substring in case an exception
	 *         occurs in getting the double-precision decimal value for that
	 *         substring
	 * @return the array of double-precision decimal values resulting from
	 *         splitting string <code>s</code> around comma characters, or an
	 *         empty array if <code>s</code> is <code>null</code>
	 */
	public static double[] split(String s, double x) {
		return split(s, StringPool.COMMA, x);
	}

	/**
	 * Splits the string <code>s</code> around comma characters returning the
	 * decimal values of the substrings.
	 *
	 * @param  s the string to split
	 * @param  x the default value to use for a substring in case an exception
	 *         occurs in getting the decimal value for that substring
	 * @return the array of decimal values resulting from splitting string
	 *         <code>s</code> around comma characters, or an empty array if
	 *         <code>s</code> is <code>null</code>
	 */
	public static float[] split(String s, float x) {
		return split(s, StringPool.COMMA, x);
	}

	/**
	 * Splits the string <code>s</code> around comma characters returning the
	 * integer values of the substrings.
	 *
	 * @param  s the string to split
	 * @param  x the default value to use for a substring in case an exception
	 *         occurs in getting the integer value for that substring
	 * @return the array of integer values resulting from splitting string
	 *         <code>s</code> around comma characters, or an empty array if
	 *         <code>s</code> is <code>null</code>
	 */
	public static int[] split(String s, int x) {
		return split(s, StringPool.COMMA, x);
	}

	/**
	 * Splits the string <code>s</code> around comma characters returning the
	 * long integer values of the substrings.
	 *
	 * @param  s the string to split
	 * @param  x the default value to use for a substring in case an exception
	 *         occurs in getting the long integer value for that substring
	 * @return the array of long integer values resulting from splitting string
	 *         <code>s</code> around comma characters, or an empty array if
	 *         <code>s</code> is <code>null</code>
	 */
	public static long[] split(String s, long x) {
		return split(s, StringPool.COMMA, x);
	}

	/**
	 * Splits the string <code>s</code> around comma characters returning the
	 * short integer values of the substrings.
	 *
	 * @param  s the string to split
	 * @param  x the default value to use for a substring in case an exception
	 *         occurs in getting the short integer value for that substring
	 * @return the array of short integer values resulting from splitting string
	 *         <code>s</code> around comma characters, or an empty array if
	 *         <code>s</code> is <code>null</code>
	 */
	public static short[] split(String s, short x) {
		return split(s, StringPool.COMMA, x);
	}

	/**
	 * Splits the string <code>s</code> around the specified delimiter string.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * splitLines("oneandtwoandthreeandfour", "and") returns {"one","two","three","four"}
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to split
	 * @param  delimiter the delimiter
	 * @return the array of strings resulting from splitting string
	 *         <code>s</code> around the specified delimiter string, or an empty
	 *         string array if <code>s</code> is <code>null</code> or equals the
	 *         delimiter
	 */
	public static String[] split(String s, String delimiter) {
		if (Validator.isNull(s) || (delimiter == null) ||
			delimiter.equals(StringPool.BLANK)) {

			return _emptyStringArray;
		}

		s = s.trim();

		if (s.equals(delimiter)) {
			return _emptyStringArray;
		}

		if (delimiter.length() == 1) {
			return split(s, delimiter.charAt(0));
		}

		List<String> nodeValues = new ArrayList<>();

		int offset = 0;
		int pos = s.indexOf(delimiter, offset);

		while (pos != -1) {
			nodeValues.add(s.substring(offset, pos));

			offset = pos + delimiter.length();
			pos = s.indexOf(delimiter, offset);
		}

		if (offset < s.length()) {
			nodeValues.add(s.substring(offset));
		}

		return nodeValues.toArray(new String[nodeValues.size()]);
	}

	/**
	 * Splits the string <code>s</code> around the specified delimiter returning
	 * the boolean values of the substrings.
	 *
	 * @param  s the string to split
	 * @param  delimiter the delimiter
	 * @param  x the default value to use for a substring in case an exception
	 *         occurs in getting the boolean value for that substring
	 * @return the array of booleans resulting from splitting string
	 *         <code>s</code> around the specified delimiter string, or an empty
	 *         array if <code>s</code> is <code>null</code>
	 */
	public static boolean[] split(String s, String delimiter, boolean x) {
		String[] array = split(s, delimiter);
		boolean[] newArray = new boolean[array.length];

		for (int i = 0; i < array.length; i++) {
			boolean value = x;

			try {
				value = Boolean.valueOf(array[i]).booleanValue();
			}
			catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	/**
	 * Splits the string <code>s</code> around the specified delimiter returning
	 * the double-precision decimal values of the substrings.
	 *
	 * @param  s the string to split
	 * @param  delimiter the delimiter
	 * @param  x the default value to use for a substring in case an exception
	 *         occurs in getting the double-precision decimal value for that
	 *         substring
	 * @return the array of double-precision decimal values resulting from
	 *         splitting string <code>s</code> around the specified delimiter
	 *         string, or an empty array if <code>s</code> is <code>null</code>
	 */
	public static double[] split(String s, String delimiter, double x) {
		String[] array = split(s, delimiter);
		double[] newArray = new double[array.length];

		for (int i = 0; i < array.length; i++) {
			double value = x;

			try {
				value = Double.parseDouble(array[i]);
			}
			catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	/**
	 * Splits the string <code>s</code> around the specified delimiter returning
	 * the decimal values of the substrings.
	 *
	 * @param  s the string to split
	 * @param  delimiter the delimiter
	 * @param  x the default value to use for a substring in case an exception
	 *         occurs in getting the decimal value for that substring
	 * @return the array of decimal values resulting from splitting string
	 *         <code>s</code> around the specified delimiter string, or an empty
	 *         array if <code>s</code> is <code>null</code>
	 */
	public static float[] split(String s, String delimiter, float x) {
		String[] array = split(s, delimiter);
		float[] newArray = new float[array.length];

		for (int i = 0; i < array.length; i++) {
			float value = x;

			try {
				value = Float.parseFloat(array[i]);
			}
			catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	/**
	 * Splits the string <code>s</code> around the specified delimiter returning
	 * the integer values of the substrings.
	 *
	 * @param  s the string to split
	 * @param  delimiter the delimiter
	 * @param  x the default value to use for a substring in case an exception
	 *         occurs in getting the integer value for that substring
	 * @return the array of integer values resulting from splitting string
	 *         <code>s</code> around the specified delimiter string, or an empty
	 *         array if <code>s</code> is <code>null</code>
	 */
	public static int[] split(String s, String delimiter, int x) {
		String[] array = split(s, delimiter);
		int[] newArray = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			int value = x;

			try {
				value = Integer.parseInt(array[i]);
			}
			catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	/**
	 * Splits the string <code>s</code> around the specified delimiter returning
	 * the long integer values of the substrings.
	 *
	 * @param  s the string to split
	 * @param  delimiter the delimiter
	 * @param  x the default value to use for a substring in case an exception
	 *         occurs in getting the long integer value for that substring
	 * @return the array of long integer values resulting from splitting string
	 *         <code>s</code> around the specified delimiter string, or an empty
	 *         array if <code>s</code> is <code>null</code>
	 */
	public static long[] split(String s, String delimiter, long x) {
		String[] array = split(s, delimiter);
		long[] newArray = new long[array.length];

		for (int i = 0; i < array.length; i++) {
			long value = x;

			try {
				value = Long.parseLong(array[i]);
			}
			catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	/**
	 * Splits the string <code>s</code> around the specified delimiter returning
	 * the short integer values of the substrings.
	 *
	 * @param  s the string to split
	 * @param  delimiter the delimiter
	 * @param  x the default value to use for a substring in case an exception
	 *         occurs in getting the short integer value for that substring
	 * @return the array of short integer values resulting from splitting string
	 *         <code>s</code> around the specified delimiter string, or an empty
	 *         array if <code>s</code> is <code>null</code>
	 */
	public static short[] split(String s, String delimiter, short x) {
		String[] array = split(s, delimiter);
		short[] newArray = new short[array.length];

		for (int i = 0; i < array.length; i++) {
			short value = x;

			try {
				value = Short.parseShort(array[i]);
			}
			catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	/**
	 * Splits string <code>s</code> around return and newline characters.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * splitLines("Red\rBlue\nGreen") returns {"Red","Blue","Green"}
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string to split
	 * @return the array of strings resulting from splitting string
	 *         <code>s</code> around return and newline characters, or an empty
	 *         string array if string <code>s</code> is <code>null</code>
	 */
	public static String[] splitLines(String s) {
		if (Validator.isNull(s)) {
			return _emptyStringArray;
		}

		s = s.trim();

		List<String> lines = new ArrayList<>();

		int lastIndex = 0;

		while (true) {
			int returnIndex = s.indexOf(CharPool.RETURN, lastIndex);
			int newLineIndex = s.indexOf(CharPool.NEW_LINE, lastIndex);

			if ((returnIndex == -1) && (newLineIndex == -1)) {
				break;
			}

			if (returnIndex == -1) {
				lines.add(s.substring(lastIndex, newLineIndex));

				lastIndex = newLineIndex + 1;
			}
			else if (newLineIndex == -1) {
				lines.add(s.substring(lastIndex, returnIndex));

				lastIndex = returnIndex + 1;
			}
			else if (newLineIndex < returnIndex) {
				lines.add(s.substring(lastIndex, newLineIndex));

				lastIndex = newLineIndex + 1;
			}
			else {
				lines.add(s.substring(lastIndex, returnIndex));

				lastIndex = returnIndex + 1;

				if (lastIndex == newLineIndex) {
					lastIndex++;
				}
			}
		}

		if (lastIndex < s.length()) {
			lines.add(s.substring(lastIndex));
		}

		return lines.toArray(new String[lines.size()]);
	}

	/**
	 * Returns <code>true</code> if, ignoring case, the string starts with the
	 * specified character.
	 *
	 * @param  s the string
	 * @param  begin the character against which the initial character of the
	 *         string is to be compared
	 * @return <code>true</code> if, ignoring case, the string starts with the
	 *         specified character; <code>false</code> otherwise
	 */
	public static boolean startsWith(String s, char begin) {
		if ((s == null) || s.isEmpty()) {
			return false;
		}

		return equalsIgnoreCase(s.charAt(0), begin);
	}

	/**
	 * Returns <code>true</code> if, ignoring case, the string starts with the
	 * specified start string.
	 *
	 * @param  s the original string
	 * @param  start the string against which the beginning of string
	 *         <code>s</code> are to be compared
	 * @return <code>true</code> if, ignoring case, the string starts with the
	 *         specified start string; <code>false</code> otherwise
	 */
	public static boolean startsWith(String s, String start) {
		if ((s == null) || (start == null)) {
			return false;
		}

		if (start.length() > s.length()) {
			return false;
		}

		String temp = s.substring(0, start.length());

		if (equalsIgnoreCase(temp, start)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns the number of starting characters that <code>s1</code> and
	 * <code>s2</code> have in common before their characters deviate.
	 *
	 * @param  s1 string 1
	 * @param  s2 string 2
	 * @return the number of starting characters that <code>s1</code> and
	 *         <code>s2</code> have in common before their characters deviate
	 */
	public static int startsWithWeight(String s1, String s2) {
		if ((s1 == null) || (s2 == null)) {
			return 0;
		}

		char[] chars1 = s1.toCharArray();
		char[] chars2 = s2.toCharArray();

		int i = 0;

		for (; (i < chars1.length) && (i < chars2.length); i++) {
			if (chars1[i] != chars2[i]) {
				break;
			}
		}

		return i;
	}

	/**
	 * Returns a string representing the string <code>s</code> with all
	 * occurrences of the specified character removed.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * strip("Mississipi", 'i') returns "Mssssp"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string from which to strip all occurrences of the character
	 * @param  remove the character to strip from the string
	 * @return a string representing the string <code>s</code> with all
	 *         occurrences of the specified character removed, or
	 *         <code>null</code> if <code>s</code> is <code>null</code>
	 */
	public static String strip(String s, char remove) {
		if (s == null) {
			return null;
		}

		int x = s.indexOf(remove);

		if (x < 0) {
			return s;
		}

		int y = 0;

		StringBundler sb = new StringBundler(s.length());

		while (x >= 0) {
			sb.append(s.subSequence(y, x));

			y = x + 1;

			x = s.indexOf(remove, y);
		}

		sb.append(s.substring(y));

		return sb.toString();
	}

	/**
	 * Returns a string representing the string <code>s</code> with all
	 * occurrences of the specified characters removed.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * strip("Hello World", {' ', 'l', 'd'}) returns "HeoWor"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string from which to strip all occurrences the characters
	 * @param  remove the characters to strip from the string
	 * @return a string representing the string <code>s</code> with all
	 *         occurrences of the specified characters removed, or
	 *         <code>null</code> if <code>s</code> is <code>null</code>
	 */
	public static String strip(String s, char[] remove) {
		for (char c : remove) {
			s = strip(s, c);
		}

		return s;
	}

	/**
	 * Returns a string representing the combination of the substring of
	 * <code>s</code> up to but not including the string <code>begin</code>
	 * concatenated with the substring of <code>s</code> after but not including
	 * the string <code>end</code>.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * stripBetween("One small step for man, one giant leap for mankind", "step", "giant ") returns "One small leap for mankind"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string from which to strip a substring
	 * @param  begin the beginning characters of the substring to be removed
	 * @param  end the ending characters of the substring to be removed
	 * @return a string representing the combination of the substring of
	 *         <code>s</code> up to but not including the string
	 *         <code>begin</code> concatenated with the substring of
	 *         <code>s</code> after but not including the string
	 *         <code>end</code>, or the original string if the value of
	 *         <code>s</code>, <code>begin</code>, or <code>end</code> are
	 *         <code>null</code>
	 */
	public static String stripBetween(String s, String begin, String end) {
		if (Validator.isBlank(s) || Validator.isBlank(begin) ||
			Validator.isBlank(end)) {

			return s;
		}

		StringBundler sb = new StringBundler(s.length());

		int pos = 0;

		while (true) {
			int x = s.indexOf(begin, pos);
			int y = s.indexOf(end, x + begin.length());

			if ((x == -1) || (y == -1)) {
				sb.append(s.substring(pos));

				break;
			}
			else {
				sb.append(s.substring(pos, x));

				pos = y + end.length();
			}
		}

		return sb.toString();
	}

	/**
	 * Returns a string representing the string <code>s</code> with its
	 * <code>&lt;![CDATA[]]&gt;</code> wrapper removed.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * stripCDATA("&lt;![CDATA[One small step for man]]&gt;") returns "One small step for man"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string from which to strip its CDATA wrapper
	 * @return a string representing the string <code>s</code> with its
	 *         <code>&lt;![CDATA[]]&gt;</code> wrapper removed, or
	 *         <code>null</code> if <code>s</code> is <code>null</code>
	 */
	public static String stripCDATA(String s) {
		if (s == null) {
			return s;
		}

		if (s.startsWith(StringPool.CDATA_OPEN) &&
			s.endsWith(StringPool.CDATA_CLOSE)) {

			s = s.substring(
				StringPool.CDATA_OPEN.length(),
				s.length() - StringPool.CDATA_CLOSE.length());
		}

		return s;
	}

	/**
	 * Returns a string representing the string <code>s</code> without an
	 * appended parenthetical suffix. If there is not a space directly before
	 * the opening parenthesis, the parenthetical suffix is not stripped.
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * stripParentheticalSuffix("file") returns "file"
	 * stripParentheticalSuffix("file (0)") returns "file"
	 * stripParentheticalSuffix("file (0 0)") returns "file"
	 * stripParentheticalSuffix("file(0)") returns "file(0)"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string from which to strip its parenthetical suffix
	 * @return a string representing the string <code>s</code> without an
	 *         appended parenthetical suffix
	 */
	public static String stripParentheticalSuffix(String s) {
		int x = s.lastIndexOf(StringPool.OPEN_PARENTHESIS);
		int y = s.lastIndexOf(StringPool.CLOSE_PARENTHESIS);

		if ((x == -1) || (y == -1)) {
			return s;
		}

		if ((x > y) || !s.endsWith(StringPool.CLOSE_PARENTHESIS)) {
			return s;
		}

		if (s.charAt(x - 1) != CharPool.SPACE) {
			return s;
		}

		return s.substring(0, x - 1).concat(s.substring(y + 1, s.length()));
	}

	/**
	 * Returns a string representing the Unicode character codes of the
	 * characters comprising the string <code>s</code>.
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * toCharCode("a") returns "97"
	 * toCharCode("b") returns "98"
	 * toCharCode("c") returns "99"
	 * toCharCode("What's for lunch?") returns "87104971163911532102111114321081171109910463"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the string whose character codes are to be represented
	 * @return a string representing the Unicode character codes of the
	 *         characters comprising the string <code>s</code>
	 */
	public static String toCharCode(String s) {
		StringBundler sb = new StringBundler(s.length());

		for (int i = 0; i < s.length(); i++) {
			sb.append(s.codePointAt(i));
		}

		return sb.toString();
	}

	/**
	 * Returns a string representing the hexidecimal character code of the
	 * integer.
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * toHexString(10) returns "a"
	 * toHexString(15) returns "f"
	 * toHexString(10995) returns "2af3"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  i the integer to convert
	 * @return a string representing the hexidecimal character code of the
	 *         integer
	 */
	public static String toHexString(int i) {
		char[] buffer = new char[8];

		int index = 8;

		do {
			buffer[--index] = HEX_DIGITS[i & 15];

			i >>>= 4;
		}
		while (i != 0);

		return new String(buffer, index, 8 - index);
	}

	/**
	 * Returns a string representing the hexidecimal character code of the long
	 * integer.
	 *
	 * <p>
	 * Example:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * toHexString(12345678910L) returns "2dfdc1c3e"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  l the long integer to convert
	 * @return a string representing the hexidecimal character code of the long
	 *         integer
	 */
	public static String toHexString(long l) {
		char[] buffer = new char[16];

		int index = 16;

		do {
			buffer[--index] = HEX_DIGITS[(int) (l & 15)];

			l >>>= 4;
		}
		while (l != 0);

		return new String(buffer, index, 16 - index);
	}

	/**
	 * Returns a string representing the hexidecimal character code of the
	 * <code>Integer</code> or <code>Long</code> object type. If the object is
	 * not an instance of these types, the object's original value is returned.
	 *
	 * @param  obj the object to convert
	 * @return a string representing the hexidecimal character code of the
	 *         object
	 */
	public static String toHexString(Object obj) {
		if (obj instanceof Integer) {
			return toHexString(((Integer)obj).intValue());
		}
		else if (obj instanceof Long) {
			return toHexString(((Long)obj).longValue());
		}
		else {
			return String.valueOf(obj);
		}
	}

	/**
	 * Converts all of the characters in the string to lower case, based on the
	 * portal instance's default locale.
	 *
	 * @param  s the string to convert
	 * @return the string, converted to lower case, or <code>null</code> if the
	 *         string is <code>null</code>
	 */
	public static String toLowerCase(String s) {
		return toLowerCase(s, null);
	}

	/**
	 * Converts all of the characters in the string to lower case, based on the
	 * locale.
	 *
	 * @param  s the string to convert
	 * @param  locale apply this locale's rules
	 * @return the string, converted to lower case, or <code>null</code> if the
	 *         string is <code>null</code>
	 * @see    GetterUtil#_toLowerCase
	 */
	public static String toLowerCase(String s, Locale locale) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = null;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c > 127) {

				// Found non-ascii char, fallback to the slow unicode detection

				if (locale == null) {
					locale = LocaleUtil.getDefault();
				}

				return s.toLowerCase(locale);
			}

			if ((c >= 'A') && (c <= 'Z')) {
				if (sb == null) {
					sb = new StringBuilder(s);
				}

				sb.setCharAt(i, (char)(c + 32));
			}
		}

		if (sb == null) {
			return s;
		}

		return sb.toString();
	}

	/**
	 * Converts all of the characters in the string to upper case, based on the
	 * portal instance's default locale.
	 *
	 * @param  s the string to convert
	 * @return the string, converted to upper case, or <code>null</code> if the
	 *         string is <code>null</code>
	 */
	public static String toUpperCase(String s) {
		return toUpperCase(s, null);
	}

	/**
	 * Converts all of the characters in the string to upper case, based on the
	 * locale.
	 *
	 * @param  s the string to convert
	 * @param  locale apply this locale's rules
	 * @return the string, converted to upper case, or <code>null</code> if the
	 *         string is <code>null</code>
	 */
	public static String toUpperCase(String s, Locale locale) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = null;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c > 127) {

				// Found non-ascii char, fallback to the slow unicode detection

				if (locale == null) {
					locale = LocaleUtil.getDefault();
				}

				return s.toUpperCase(locale);
			}

			if ((c >= 'a') && (c <= 'z')) {
				if (sb == null) {
					sb = new StringBuilder(s);
				}

				sb.setCharAt(i, (char)(c - 32));
			}
		}

		if (sb == null) {
			return s;
		}

		return sb.toString();
	}

	/**
	 * Trims all leading and trailing whitespace from the string.
	 *
	 * @param  s the original string
	 * @return a string representing the original string with all leading and
	 *         trailing whitespace removed
	 */
	public static String trim(String s) {
		if (s == null) {
			return null;
		}

		if (s.length() == 0) {
			return s;
		}

		int len = s.length();

		int x = len;

		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);

			if (!Character.isWhitespace(c)) {
				x = i;

				break;
			}
		}

		if (x == len) {
			return StringPool.BLANK;
		}

		int y = x + 1;

		for (int i = len - 1; i > x; i--) {
			char c = s.charAt(i);

			if (!Character.isWhitespace(c)) {
				y = i + 1;

				break;
			}
		}

		if ((x == 0) && (y == len)) {
			return s;
		}

		return s.substring(x, y);
	}

	/**
	 * Trims leading and trailing whitespace from the string, up to but not
	 * including the whitespace character specified by <code>c</code>.
	 *
	 * <p>
	 * Examples:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * trim(" \tHey\t ", '\t') returns "\tHey\t"
	 * trim(" \t Hey \t ", '\t') returns "\t Hey \t"
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  s the original string
	 * @param  c the whitespace character to limit trimming
	 * @return a string representing the original string with leading and
	 *         trailing whitespace removed, up to but not including the
	 *         whitespace character specified by <code>c</code>
	 */
	public static String trim(String s, char c) {
		return trim(s, new char[] {c});
	}

	/**
	 * Trims leading and trailing whitespace from the string, up to but not
	 * including the whitespace characters specified by <code>exceptions</code>.
	 *
	 * @param  s the original string
	 * @param  exceptions the whitespace characters to limit trimming
	 * @return a string representing the original string with leading and
	 *         trailing whitespace removed, up to but not including the
	 *         whitespace characters specified by <code>exceptions</code>
	 */
	public static String trim(String s, char[] exceptions) {
		if (s == null) {
			return null;
		}

		if (s.length() == 0) {
			return s;
		}

		if (ArrayUtil.isEmpty(exceptions)) {
			return trim(s);
		}

		int len = s.length();
		int x = len;

		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);

			if (!_isTrimable(c, exceptions)) {
				x = i;

				break;
			}
		}

		if (x == len) {
			return StringPool.BLANK;
		}

		int y = x + 1;

		for (int i = len - 1; i > x; i--) {
			char c = s.charAt(i);

			if (!_isTrimable(c, exceptions)) {
				y = i + 1;

				break;
			}
		}

		if ((x == 0) && (y == len)) {
			return s;
		}
		else {
			return s.substring(x, y);
		}
	}

	/**
	 * Trims all leading whitespace from the string.
	 *
	 * @param  s the original string
	 * @return a string representing the original string with all leading
	 *         whitespace removed
	 */
	public static String trimLeading(String s) {
		if (s == null) {
			return null;
		}

		if (s.length() == 0) {
			return s;
		}

		int len = s.length();
		int x = len;

		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);

			if (!Character.isWhitespace(c)) {
				x = i;

				break;
			}
		}

		if (x == len) {
			return StringPool.BLANK;
		}
		else if (x == 0) {
			return s;
		}
		else {
			return s.substring(x);
		}
	}

	/**
	 * Trims leading whitespace from the string, up to but not including the
	 * whitespace character specified by <code>c</code>.
	 *
	 * @param  s the original string
	 * @param  c the whitespace character to limit trimming
	 * @return a string representing the original string with leading whitespace
	 *         removed, up to but not including the whitespace character
	 *         specified by <code>c</code>
	 */
	public static String trimLeading(String s, char c) {
		return trimLeading(s, new char[] {c});
	}

	/**
	 * Trims leading whitespace from the string, up to but not including the
	 * whitespace characters specified by <code>exceptions</code>.
	 *
	 * @param  s the original string
	 * @param  exceptions the whitespace characters to limit trimming
	 * @return a string representing the original string with leading whitespace
	 *         removed, up to but not including the whitespace characters
	 *         specified by <code>exceptions</code>
	 */
	public static String trimLeading(String s, char[] exceptions) {
		if (s == null) {
			return null;
		}

		if (s.length() == 0) {
			return s;
		}

		if (ArrayUtil.isEmpty(exceptions)) {
			return trimLeading(s);
		}

		int len = s.length();
		int x = len;

		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);

			if (!_isTrimable(c, exceptions)) {
				x = i;

				break;
			}
		}

		if (x == len) {
			return StringPool.BLANK;
		}
		else if (x == 0) {
			return s;
		}
		else {
			return s.substring(x);
		}
	}

	/**
	 * Trims all trailing whitespace from the string.
	 *
	 * @param  s the original string
	 * @return a string representing the original string with all trailing
	 *         whitespace removed
	 */
	public static String trimTrailing(String s) {
		if (s == null) {
			return null;
		}

		if (s.length() == 0) {
			return s;
		}

		int len = s.length();
		int x = 0;

		for (int i = len - 1; i >= 0; i--) {
			char c = s.charAt(i);

			if (!Character.isWhitespace(c)) {
				x = i + 1;

				break;
			}
		}

		if (x == 0) {
			return StringPool.BLANK;
		}
		else if (x == len) {
			return s;
		}
		else {
			return s.substring(0, x);
		}
	}

	/**
	 * Trims trailing whitespace from the string, up to but not including the
	 * whitespace character specified by <code>c</code>.
	 *
	 * @param  s the original string
	 * @param  c the whitespace character to limit trimming
	 * @return a string representing the original string with trailing
	 *         whitespace removed, up to but not including the whitespace
	 *         character specified by <code>c</code>
	 */
	public static String trimTrailing(String s, char c) {
		return trimTrailing(s, new char[] {c});
	}

	/**
	 * Trims trailing whitespace from the string, up to but not including the
	 * whitespace characters specified by <code>exceptions</code>.
	 *
	 * @param  s the original string
	 * @param  exceptions the whitespace characters to limit trimming
	 * @return a string representing the original string with trailing
	 *         whitespace removed, up to but not including the whitespace
	 *         characters specified by <code>exceptions</code>
	 */
	public static String trimTrailing(String s, char[] exceptions) {
		if (s == null) {
			return null;
		}

		if (s.length() == 0) {
			return s;
		}

		if (ArrayUtil.isEmpty(exceptions)) {
			return trimTrailing(s);
		}

		int len = s.length();
		int x = 0;

		for (int i = len - 1; i >= 0; i--) {
			char c = s.charAt(i);

			if (!_isTrimable(c, exceptions)) {
				x = i + 1;

				break;
			}
		}

		if (x == 0) {
			return StringPool.BLANK;
		}
		else if (x == len) {
			return s;
		}
		else {
			return s.substring(0, x);
		}
	}

	/**
	 * Removes leading and trailing double and single quotation marks from the
	 * string.
	 *
	 * @param  s the original string
	 * @return a string representing the original string with leading and
	 *         trailing double and single quotation marks removed, or the
	 *         original string if the original string is a <code>null</code> or
	 *         empty
	 */
	public static String unquote(String s) {
		if (Validator.isNull(s)) {
			return s;
		}

		if ((s.charAt(0) == CharPool.APOSTROPHE) &&
			(s.charAt(s.length() - 1) == CharPool.APOSTROPHE)) {

			return s.substring(1, s.length() - 1);
		}
		else if ((s.charAt(0) == CharPool.QUOTE) &&
				 (s.charAt(s.length() - 1) == CharPool.QUOTE)) {

			return s.substring(1, s.length() - 1);
		}

		return s;
	}

	/**
	 * Converts all of the characters in the string to upper case.
	 *
	 * @param  s the string to convert
	 * @return the string, converted to upper-case, or <code>null</code> if the
	 *         string is <code>null</code>
	 * @see    String#toUpperCase()
	 */
	public static String upperCase(String s) {
		return toUpperCase(s);
	}

	/**
	 * Converts the first character of the string to upper case.
	 *
	 * @param  s the string whose first character is to be converted
	 * @return the string, with its first character converted to upper-case
	 */
	public static String upperCaseFirstLetter(String s) {
		char[] chars = s.toCharArray();

		if ((chars[0] >= 97) && (chars[0] <= 122)) {
			chars[0] = (char)(chars[0] - 32);
		}

		return new String(chars);
	}

	/**
	 * Returns the string value of the object.
	 *
	 * @param  obj the object whose string value is to be returned
	 * @return the string value of the object
	 * @see    String#valueOf(Object obj)
	 */
	public static String valueOf(Object obj) {
		return String.valueOf(obj);
	}

	/**
	 * Returns <code>true</code> if the string matches the wildcard pattern.
	 *
	 * <p>
	 * For example, with the following initialized variables:
	 * </p>
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * String s = "*master";
	 * String wildcard = "/*m?st*";
	 * char singleWildcardCharacter = '?';
	 * char multipleWildcardCharacter = '*';
	 * char escapeWildcardCharacter = '/';
	 * boolean caseSensitive = false;
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * <p>
	 * <code>wildcardMatches(s, wildcard, singleWildcardCharacter,
	 * multipleWildcardCharacter, escapeWildcardCharacter, caseSensitive)</code>
	 * returns <code>true</code>
	 * </p>
	 *
	 * @param  s the string to be checked
	 * @param  wildcard the wildcard pattern to match
	 * @param  singleWildcardCharacter the char used to match exactly one
	 *         character
	 * @param  multipleWildcardCharacter the char used to match <code>0</code>
	 *         or more characters
	 * @param  escapeWildcardCharacter the char placed in front of a wildcard
	 *         character to indicate that it should be interpreted as a regular
	 *         character
	 * @param  caseSensitive whether to use case sensitivity
	 * @return <code>true</code> if the string matches the wildcard pattern;
	 *         <code>false</code> otherwise
	 */
	public static boolean wildcardMatches(
		String s, String wildcard, char singleWildcardCharacter,
		char multipleWildcardCharacter, char escapeWildcardCharacter,
		boolean caseSensitive) {

		if (!caseSensitive) {
			s = toLowerCase(s);
			wildcard = toLowerCase(wildcard);
		}

		// Update the wildcard, single whildcard character, and multiple
		// wildcard character so that they no longer have escaped wildcard
		// characters

		int index = wildcard.indexOf(escapeWildcardCharacter);

		if (index != -1) {

			// Search for safe wildcard replacement

			char newSingleWildcardCharacter = 0;

			while (wildcard.indexOf(newSingleWildcardCharacter) != -1) {
				newSingleWildcardCharacter++;
			}

			char newMultipleWildcardCharacter =
				(char)(newSingleWildcardCharacter + 1);

			while (wildcard.indexOf(newMultipleWildcardCharacter) != -1) {
				newMultipleWildcardCharacter++;
			}

			// Purify

			StringBuilder sb = new StringBuilder(wildcard);

			for (int i = 0; i < sb.length(); i++) {
				char c = sb.charAt(i);

				if (c == escapeWildcardCharacter) {
					sb.deleteCharAt(i);
				}
				else if (c == singleWildcardCharacter) {
					sb.setCharAt(i, newSingleWildcardCharacter);
				}
				else if (c == multipleWildcardCharacter) {
					sb.setCharAt(i, newMultipleWildcardCharacter);
				}
			}

			wildcard = sb.toString();

			singleWildcardCharacter = newSingleWildcardCharacter;
			multipleWildcardCharacter = newMultipleWildcardCharacter;
		}

		// Align head

		for (index = 0; index < s.length(); index++) {
			if (index >= wildcard.length()) {
				return false;
			}

			char c = wildcard.charAt(index);

			if (c == multipleWildcardCharacter) {
				break;
			}

			if ((s.charAt(index) != c) && (c != singleWildcardCharacter)) {
				return false;
			}
		}

		// Match body

		int sIndex = index;
		int wildcardIndex = index;

		int matchPoint = 0;
		int comparePoint = 0;

		while (sIndex < s.length()) {
			if (wildcardIndex == wildcard.length()) {

				// Wildcard exhausted before s

				return false;
			}

			char c = wildcard.charAt(wildcardIndex);

			if (c == multipleWildcardCharacter) {
				if (++wildcardIndex == wildcard.length()) {
					return true;
				}

				matchPoint = wildcardIndex;
				comparePoint = sIndex + 1;
			}
			else if ((c == s.charAt(sIndex)) ||
					 (c == singleWildcardCharacter)) {

				sIndex++;
				wildcardIndex++;
			}
			else {
				wildcardIndex = matchPoint;
				sIndex = comparePoint++;
			}
		}

		// Match tail

		while (wildcardIndex < wildcard.length()) {
			if (wildcard.charAt(wildcardIndex) != multipleWildcardCharacter) {
				break;
			}

			wildcardIndex++;
		}

		if (wildcardIndex == wildcard.length()) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Wraps the text when it exceeds the <code>80</code> column width limit,
	 * using a {@link StringPool#NEW_LINE} to break each wrapped line.
	 *
	 * @param  text the text to wrap
	 * @return the wrapped text following the column width limit, or
	 *         <code>null</code> if the text is <code>null</code>
	 */
	public static String wrap(String text) {
		return wrap(text, 80, StringPool.NEW_LINE);
	}

	/**
	 * Wraps the text when it exceeds the column width limit, using the line
	 * separator to break each wrapped line.
	 *
	 * @param  text the text to wrap
	 * @param  width the column width limit for the text
	 * @param  lineSeparator the string to use in breaking each wrapped line
	 * @return the wrapped text and line separators, following the column width
	 *         limit, or <code>null</code> if the text is <code>null</code>
	 */
	public static String wrap(String text, int width, String lineSeparator) {
		try {
			return _wrap(text, width, lineSeparator);
		}
		catch (IOException ioe) {
			_log.error(ioe.getMessage());

			return text;
		}
	}

	protected static final char[] HEX_DIGITS = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
		'e', 'f'
	};

	/**
	 * Returns <code>false</code> if the character is not whitespace or is equal
	 * to any of the exception characters.
	 *
	 * @param  c the character whose trim-ability is to be determined
	 * @param  exceptions the whitespace characters to exclude from trimming
	 * @return <code>false</code> if the character is not whitespace or is equal
	 *         to any of the exception characters; <code>true</code> otherwise
	 */
	private static boolean _isTrimable(char c, char[] exceptions) {
		for (char exception : exceptions) {
			if (c == exception) {
				return false;
			}
		}

		return Character.isWhitespace(c);
	}

	private static String _wrap(String text, int width, String lineSeparator)
		throws IOException {

		if (text == null) {
			return null;
		}

		StringBundler sb = new StringBundler();

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(text));

		String s = StringPool.BLANK;

		while ((s = unsyncBufferedReader.readLine()) != null) {
			if (s.length() == 0) {
				sb.append(lineSeparator);

				continue;
			}

			int lineLength = 0;

			String[] tokens = s.split(StringPool.SPACE);

			for (String token : tokens) {
				if ((lineLength + token.length() + 1) > width) {
					if (lineLength > 0) {
						sb.append(lineSeparator);
					}

					if (token.length() > width) {
						int pos = token.indexOf(CharPool.OPEN_PARENTHESIS);

						if (pos != -1) {
							sb.append(token.substring(0, pos + 1));
							sb.append(lineSeparator);

							token = token.substring(pos + 1);

							sb.append(token);

							lineLength = token.length();
						}
						else {
							sb.append(token);

							lineLength = token.length();
						}
					}
					else {
						sb.append(token);

						lineLength = token.length();
					}
				}
				else {
					if (lineLength > 0) {
						sb.append(StringPool.SPACE);

						lineLength++;
					}

					sb.append(token);

					lineLength += token.length();
				}
			}

			sb.append(lineSeparator);
		}

		return sb.toString();
	}

	private static final char[] _RANDOM_STRING_CHAR_TABLE = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
		'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
		'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
		'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
		'u', 'v', 'w', 'x', 'y', 'z'
	};

	private static final Log _log = LogFactoryUtil.getLog(StringUtil.class);

	private static final String[] _emptyStringArray = new String[0];

}