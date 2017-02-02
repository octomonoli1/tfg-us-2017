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

package com.liferay.source.formatter;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hugo Huijser
 */
public class JavaTerm {

	public static final int TYPE_CLASS_PRIVATE = 24;

	public static final int TYPE_CLASS_PRIVATE_STATIC = 23;

	public static final int TYPE_CLASS_PROTECTED = 16;

	public static final int TYPE_CLASS_PROTECTED_STATIC = 15;

	public static final int TYPE_CLASS_PUBLIC = 8;

	public static final int TYPE_CLASS_PUBLIC_STATIC = 7;

	public static final int TYPE_CONSTRUCTOR_PRIVATE = 18;

	public static final int TYPE_CONSTRUCTOR_PROTECTED = 10;

	public static final int TYPE_CONSTRUCTOR_PUBLIC = 4;

	public static final int TYPE_METHOD_PRIVATE = 19;

	public static final int TYPE_METHOD_PRIVATE_STATIC = 17;

	public static final int TYPE_METHOD_PROTECTED = 11;

	public static final int TYPE_METHOD_PROTECTED_STATIC = 9;

	public static final int TYPE_METHOD_PUBLIC = 5;

	public static final int TYPE_METHOD_PUBLIC_STATIC = 3;

	public static final int TYPE_STATIC_BLOCK = 21;

	public static final int TYPE_VARIABLE_PRIVATE = 22;

	public static final int TYPE_VARIABLE_PRIVATE_STATIC = 20;

	public static final int TYPE_VARIABLE_PROTECTED = 14;

	public static final int TYPE_VARIABLE_PROTECTED_STATIC = 12;

	public static final int TYPE_VARIABLE_PUBLIC = 6;

	public static final int TYPE_VARIABLE_PUBLIC_STATIC = 1;

	public JavaTerm(
		String name, int type, String content, int lineCount, String indent) {

		_name = name;
		_type = type;
		_content = content;
		_lineCount = lineCount;
		_indent = indent;
	}

	public String getContent() {
		return _content;
	}

	public String getIndent() {
		return _indent;
	}

	public int getLineCount() {
		return _lineCount;
	}

	public String getName() {
		return _name;
	}

	public List<String> getParameterNames() {
		if (_parameterNames == null) {
			_readReturnTypeAndParameters();
		}

		return _parameterNames;
	}

	public List<String> getParameterTypes() {
		if (_parameterTypes == null) {
			_readReturnTypeAndParameters();
		}

		return _parameterTypes;
	}

	public String getReturnType() {
		if (_returnType == null) {
			_readReturnTypeAndParameters();
		}

		return _returnType;
	}

	public int getType() {
		return _type;
	}

	public boolean hasAnnotation(String annotation) {
		if (_content.contains(_indent + StringPool.AT + annotation + "\n") ||
			_content.contains(
				_indent + StringPool.AT + annotation +
					StringPool.OPEN_PARENTHESIS)) {

			return true;
		}

		return false;
	}

	public boolean hasReturnType() {
		if (Validator.isNotNull(getReturnType())) {
			return true;
		}

		return false;
	}

	public boolean isClass() {
		if ((_type == TYPE_CLASS_PRIVATE) ||
			(_type == TYPE_CLASS_PRIVATE_STATIC) ||
			(_type == TYPE_CLASS_PROTECTED) ||
			(_type == TYPE_CLASS_PROTECTED_STATIC) ||
			(_type == TYPE_CLASS_PUBLIC) ||
			(_type == TYPE_CLASS_PUBLIC_STATIC)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isConstructor() {
		if ((_type == TYPE_CONSTRUCTOR_PRIVATE) ||
			(_type == TYPE_CONSTRUCTOR_PROTECTED) ||
			(_type == TYPE_CONSTRUCTOR_PUBLIC)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isMethod() {
		if ((_type == TYPE_METHOD_PRIVATE) ||
			(_type == TYPE_METHOD_PRIVATE_STATIC) ||
			(_type == TYPE_METHOD_PROTECTED) ||
			(_type == TYPE_METHOD_PROTECTED_STATIC) ||
			(_type == TYPE_METHOD_PUBLIC) ||
			(_type == TYPE_METHOD_PUBLIC_STATIC)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPrivate() {
		if ((_type == TYPE_CLASS_PRIVATE) ||
			(_type == TYPE_CLASS_PRIVATE_STATIC) ||
			(_type == TYPE_CONSTRUCTOR_PRIVATE) ||
			(_type == TYPE_METHOD_PRIVATE) ||
			(_type == TYPE_METHOD_PRIVATE_STATIC) ||
			(_type == TYPE_VARIABLE_PRIVATE) ||
			(_type == TYPE_VARIABLE_PRIVATE_STATIC)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isProtected() {
		if ((_type == TYPE_CLASS_PROTECTED) ||
			(_type == TYPE_CLASS_PROTECTED_STATIC) ||
			(_type == TYPE_CONSTRUCTOR_PROTECTED) ||
			(_type == TYPE_METHOD_PROTECTED) ||
			(_type == TYPE_METHOD_PROTECTED_STATIC) ||
			(_type == TYPE_VARIABLE_PROTECTED) ||
			(_type == TYPE_VARIABLE_PROTECTED_STATIC)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPublic() {
		if ((_type == TYPE_CLASS_PUBLIC) ||
			(_type == TYPE_CLASS_PUBLIC_STATIC) ||
			(_type == TYPE_CONSTRUCTOR_PUBLIC) ||
			(_type == TYPE_METHOD_PUBLIC) ||
			(_type == TYPE_METHOD_PUBLIC_STATIC) ||
			(_type == TYPE_VARIABLE_PUBLIC) ||
			(_type == TYPE_VARIABLE_PUBLIC_STATIC)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isStatic() {
		if ((_type == TYPE_CLASS_PRIVATE_STATIC) ||
			(_type == TYPE_CLASS_PROTECTED_STATIC) ||
			(_type == TYPE_CLASS_PUBLIC_STATIC) ||
			(_type == TYPE_METHOD_PRIVATE_STATIC) ||
			(_type == TYPE_METHOD_PROTECTED_STATIC) ||
			(_type == TYPE_METHOD_PUBLIC_STATIC) ||
			(_type == TYPE_VARIABLE_PRIVATE_STATIC) ||
			(_type == TYPE_VARIABLE_PROTECTED_STATIC) ||
			(_type == TYPE_VARIABLE_PUBLIC_STATIC)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isVariable() {
		if ((_type == TYPE_VARIABLE_PRIVATE) ||
			(_type == TYPE_VARIABLE_PRIVATE_STATIC) ||
			(_type == TYPE_VARIABLE_PROTECTED) ||
			(_type == TYPE_VARIABLE_PROTECTED_STATIC) ||
			(_type == TYPE_VARIABLE_PUBLIC) ||
			(_type == TYPE_VARIABLE_PUBLIC_STATIC)) {

			return true;
		}
		else {
			return false;
		}
	}

	public void setType(int type) {
		_type = type;
	}

	private void _readReturnTypeAndParameters() {
		_parameterNames = new ArrayList<>();
		_parameterTypes = new ArrayList<>();
		_returnType = StringPool.BLANK;

		if (!isConstructor() && !isMethod()) {
			return;
		}

		int x = -1;

		if (isPrivate()) {
			x = _content.indexOf("\tprivate ");
		}
		else if (isProtected()) {
			x = _content.indexOf("\tprotected ");
		}
		else if (isPublic()) {
			x = _content.indexOf("\tpublic ");
		}

		if (x == -1) {
			return;
		}

		int y = _content.indexOf(CharPool.OPEN_PARENTHESIS, x);

		if (isMethod()) {
			String linePart = _content.substring(x, y);

			linePart = StringUtil.removeChar(linePart, CharPool.TAB);
			linePart = StringUtil.replace(
				linePart, StringPool.PERIOD + StringPool.NEW_LINE,
				StringPool.PERIOD);
			linePart = StringUtil.replace(
				linePart, CharPool.NEW_LINE, StringPool.SPACE);

			int z = linePart.lastIndexOf(CharPool.SPACE);

			linePart = linePart.substring(0, z);

			while (true) {
				z = linePart.lastIndexOf(CharPool.SPACE, z - 1);

				_returnType = linePart.substring(z + 1);

				if (_javaSourceProcessor.getLevel(_returnType, "<", ">") == 0) {
					break;
				}
			}

			if (_returnType.equals("void")) {
				_returnType = StringPool.BLANK;
			}
		}

		x = y;

		String parameters = StringPool.BLANK;

		while (true) {
			y = _content.indexOf(CharPool.CLOSE_PARENTHESIS, y + 1);

			if (y == -1) {
				return;
			}

			parameters = _content.substring(x + 1, y);

			if (_javaSourceProcessor.getLevel(parameters) == 0) {
				break;
			}
		}

		parameters = StringUtil.removeChar(parameters, CharPool.TAB);
		parameters = StringUtil.replace(
			parameters, StringPool.PERIOD + StringPool.NEW_LINE,
			StringPool.PERIOD);
		parameters = StringUtil.replace(
			parameters, CharPool.NEW_LINE, StringPool.SPACE);

		for (x = 0;;) {
			parameters = StringUtil.trim(parameters);

			if (parameters.startsWith(StringPool.AT)) {
				parameters = _stripAnnotation(parameters);
			}

			if (parameters.startsWith("final ")) {
				parameters = parameters.substring(6);
			}

			x = parameters.indexOf(CharPool.SPACE, x + 1);

			if (x == -1) {
				return;
			}

			String parameterType = parameters.substring(0, x);

			if (_javaSourceProcessor.getLevel(parameterType, "<", ">") != 0) {
				continue;
			}

			_parameterTypes.add(parameterType);

			y = parameters.indexOf(CharPool.COMMA, x);

			if (y == -1) {
				_parameterNames.add(parameters.substring(x + 1));

				return;
			}

			_parameterNames.add(parameters.substring(x + 1, y));

			parameters = parameters.substring(y + 1);

			x = 0;
		}
	}

	private String _stripAnnotation(String parameters) {
		int pos = -1;

		while (true) {
			pos = parameters.indexOf(CharPool.SPACE, pos + 1);

			if (pos == -1) {
				return parameters;
			}

			String annotation = parameters.substring(0, pos);

			if ((_javaSourceProcessor.getLevel(annotation) == 0) &&
				(_javaSourceProcessor.getLevel(annotation, "<", ">") == 0)) {

				return parameters.substring(pos + 1);
			}
		}
	}

	private final String _content;
	private final String _indent;
	private final JavaSourceProcessor _javaSourceProcessor =
		new JavaSourceProcessor();
	private final int _lineCount;
	private final String _name;
	private List<String> _parameterNames;
	private List<String> _parameterTypes;
	private String _returnType;
	private int _type;

}