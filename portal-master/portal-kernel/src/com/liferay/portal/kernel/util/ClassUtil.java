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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brian Wing Shun Chan
 * @author Sandeep Soni
 */
public class ClassUtil {

	public static Set<String> getClasses(File file) throws IOException {
		String fileName = file.getName();

		if (fileName.endsWith(".java")) {
			fileName = fileName.substring(0, fileName.length() - 5);
		}

		return getClasses(
			new UnsyncBufferedReader(new FileReader(file)), fileName);
	}

	public static Set<String> getClasses(Reader reader, String className)
		throws IOException {

		Set<String> classes = new HashSet<>();

		StreamTokenizer st = new StreamTokenizer(reader);

		_setupParseTableForAnnotationProcessing(st);

		while (st.nextToken() != StreamTokenizer.TT_EOF) {
			if (st.ttype == StreamTokenizer.TT_WORD) {
				if (st.sval.equals("class") || st.sval.equals("enum") ||
					st.sval.equals("interface") ||
					st.sval.equals("@interface")) {

					break;
				}
				else if (st.sval.startsWith("@")) {
					st.ordinaryChar(' ');
					st.wordChars('=', '=');
					st.wordChars('+', '+');

					String[] annotationClasses = _processAnnotation(
						st.sval, st);

					for (String annotationClass : annotationClasses) {
						classes.add(annotationClass);
					}

					_setupParseTableForAnnotationProcessing(st);
				}
			}
		}

		_setupParseTable(st);

		while (st.nextToken() != StreamTokenizer.TT_EOF) {
			if (st.ttype == StreamTokenizer.TT_WORD) {
				int firstIndex = st.sval.indexOf('.');

				if (firstIndex >= 0) {
					classes.add(st.sval.substring(0, firstIndex));
				}

				int lastIndex = st.sval.lastIndexOf('.');

				if (lastIndex >= 0) {
					classes.add(st.sval.substring(lastIndex + 1));
				}

				if ((firstIndex < 0) && (lastIndex < 0)) {
					classes.add(st.sval);
				}
			}
			else if ((st.ttype != StreamTokenizer.TT_NUMBER) &&
					 (st.ttype != StreamTokenizer.TT_EOL)) {

				if (Character.isUpperCase((char)st.ttype)) {
					classes.add(String.valueOf((char)st.ttype));
				}
			}
		}

		classes.remove(className);

		return classes;
	}

	public static String getClassName(Object object) {
		if (object == null) {
			return null;
		}

		Class<?> clazz = object.getClass();

		return clazz.getName();
	}

	public static String getParentPath(
		ClassLoader classLoader, String className) {

		if (_log.isDebugEnabled()) {
			_log.debug("Class name " + className);
		}

		if (!className.endsWith(_CLASS_EXTENSION)) {
			className += _CLASS_EXTENSION;
		}

		className = StringUtil.replace(
			className, CharPool.PERIOD, CharPool.SLASH);

		className = StringUtil.replace(className, "/class", _CLASS_EXTENSION);

		URL url = classLoader.getResource(className);

		String path = null;

		try {
			path = url.getPath();

			URI uri = new URI(path);

			String scheme = uri.getScheme();

			if (path.contains(StringPool.EXCLAMATION) &&
				((scheme == null) || (scheme.length() <= 1))) {

				if (!path.startsWith(StringPool.SLASH)) {
					path = StringPool.SLASH + path;
				}
			}
			else {
				path = uri.getPath();

				if (path == null) {
					path = url.getFile();
				}
			}
		}
		catch (URISyntaxException urise) {
			path = url.getFile();
		}

		if (ServerDetector.isJBoss() || ServerDetector.isWildfly()) {
			if (path.startsWith("file:") && !path.startsWith("file:/")) {
				path = path.substring(5);

				path = "file:/".concat(path);

				path = StringUtil.replace(path, "%5C", StringPool.SLASH);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Path " + path);
		}

		int pos = path.indexOf(className);

		String parentPath = path.substring(0, pos);

		if (parentPath.startsWith("jar:")) {
			parentPath = parentPath.substring(4);
		}

		if (parentPath.startsWith("file:/")) {
			parentPath = parentPath.substring(6);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Parent path " + parentPath);
		}

		return parentPath;
	}

	public static boolean isSubclass(Class<?> a, Class<?> b) {
		if (a == b) {
			return true;
		}

		if ((a == null) || (b == null)) {
			return false;
		}

		for (Class<?> x = a; x != null; x = x.getSuperclass()) {
			if (x == b) {
				return true;
			}

			if (b.isInterface()) {
				Class<?>[] interfaceClasses = x.getInterfaces();

				for (Class<?> interfaceClass : interfaceClasses) {
					if (isSubclass(interfaceClass, b)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean isSubclass(Class<?> a, String s) {
		if ((a == null) || (s == null)) {
			return false;
		}

		if (a.getName().equals(s)) {
			return true;
		}

		for (Class<?> x = a; x != null; x = x.getSuperclass()) {
			if (x.getName().equals(s)) {
				return true;
			}

			Class<?>[] interfaceClasses = x.getInterfaces();

			for (Class<?> interfaceClass : interfaceClasses) {
				if (isSubclass(interfaceClass, s)) {
					return true;
				}
			}
		}

		return false;
	}

	private static String[] _processAnnotation(String s, StreamTokenizer st)
		throws IOException {

		s = s.trim();

		List<String> tokens = new ArrayList<>();

		Matcher annotationNameMatcher = _ANNOTATION_NAME_REGEXP.matcher(s);
		Matcher annotationParametersMatcher =
			_ANNOTATION_PARAMETERS_REGEXP.matcher(s);

		if (annotationNameMatcher.matches()) {
			tokens.add(annotationNameMatcher.group(1));
		}
		else if (annotationParametersMatcher.matches()) {
			tokens.add(annotationParametersMatcher.group(1));

			String annotationParameters = StringPool.BLANK;

			if (s.trim().endsWith(")")) {
				annotationParameters = annotationParametersMatcher.group(3);
			}
			else {
				int pos = s.indexOf('{');

				if (pos != -1) {
					annotationParameters += s.substring(pos + 1);
				}

				while (st.nextToken() != StreamTokenizer.TT_EOF) {
					if (st.ttype != StreamTokenizer.TT_WORD) {
						continue;
					}

					annotationParameters += st.sval;

					String trimmedValue = StringUtil.trim(st.sval);

					if (!trimmedValue.endsWith(")")) {
						continue;
					}

					int closeParenthesesCount = StringUtil.count(
						annotationParameters, ')');
					int openParenthesesCount = StringUtil.count(
						annotationParameters, '(');

					if (closeParenthesesCount > openParenthesesCount) {
						break;
					}
				}
			}

			tokens = _processAnnotationParameters(annotationParameters, tokens);
		}

		return tokens.toArray(new String[tokens.size()]);
	}

	private static List<String> _processAnnotationParameters(
			String s, List<String> tokens)
		throws IOException {

		StreamTokenizer st = new StreamTokenizer(new UnsyncStringReader(s));

		_setupParseTable(st);

		while (st.nextToken() != StreamTokenizer.TT_EOF) {
			if (st.ttype == StreamTokenizer.TT_WORD) {
				if (st.sval.indexOf('.') >= 0) {
					tokens.add(st.sval.substring(0, st.sval.indexOf('.')));
				}
				else {
					tokens.add(st.sval);
				}
			}
			else if ((st.ttype != StreamTokenizer.TT_NUMBER) &&
					 (st.ttype != StreamTokenizer.TT_EOL)) {

				if (Character.isUpperCase((char)st.ttype)) {
					tokens.add(String.valueOf((char)st.ttype));
				}
			}
		}

		return tokens;
	}

	private static void _setupParseTable(StreamTokenizer st) {
		st.resetSyntax();
		st.slashSlashComments(true);
		st.slashStarComments(true);
		st.wordChars('a', 'z');
		st.wordChars('A', 'Z');
		st.wordChars('.', '.');
		st.wordChars('0', '9');
		st.wordChars('_', '_');
		st.lowerCaseMode(false);
		st.eolIsSignificant(false);
		st.quoteChar('"');
		st.quoteChar('\'');
		st.parseNumbers();
	}

	private static void _setupParseTableForAnnotationProcessing(
		StreamTokenizer st) {

		_setupParseTable(st);

		st.wordChars('@', '@');
		st.wordChars('(', '(');
		st.wordChars(')', ')');
		st.wordChars('{', '{');
		st.wordChars('}', '}');
		st.wordChars(',',',');
	}

	private static final Pattern _ANNOTATION_NAME_REGEXP = Pattern.compile(
		"@(\\w+)\\.?(\\w*)$");

	private static final Pattern _ANNOTATION_PARAMETERS_REGEXP =
		Pattern.compile(
			"@(\\w+)\\.?(\\w*)\\({0,1}\\{{0,1}([^)}]+)\\}{0,1}\\){0,1}");

	private static final String _CLASS_EXTENSION = ".class";

	private static final Log _log = LogFactoryUtil.getLog(ClassUtil.class);

}