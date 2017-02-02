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

package com.liferay.portal.tools;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.UnsecureSAXReaderUtil;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.Type;
import com.thoughtworks.qdox.model.TypeVariable;

import java.io.File;
import java.io.IOException;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class InstanceWrapperBuilder {

	public static void main(String[] args) {
		ToolDependencies.wireBasic();

		if (args.length == 1) {
			new InstanceWrapperBuilder(args[0]);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public InstanceWrapperBuilder(String xml) {
		try {
			File file = new File(xml);

			Document document = UnsecureSAXReaderUtil.read(file);

			Element rootElement = document.getRootElement();

			List<Element> instanceWrapperElements = rootElement.elements(
				"instance-wrapper");

			for (Element instanceWrapperElement : instanceWrapperElements) {
				String parentDir = instanceWrapperElement.attributeValue(
					"parent-dir");
				String srcFile = instanceWrapperElement.attributeValue(
					"src-file");

				_createIW(parentDir, srcFile);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void _createIW(String parentDir, String srcFile)
		throws IOException {

		JavaClass javaClass = _getJavaClass(parentDir, srcFile);

		JavaMethod[] javaMethods = javaClass.getMethods();

		StringBundler sb = new StringBundler();

		// Package

		sb.append("package ");
		sb.append(javaClass.getPackage().getName());
		sb.append(";");

		// Class declaration

		sb.append("public class ");
		sb.append(javaClass.getName());
		sb.append("_IW {");

		// Methods

		sb.append("public static ");
		sb.append(javaClass.getName());
		sb.append("_IW getInstance() {return _instance;}\n");

		for (JavaMethod javaMethod : javaMethods) {
			String methodName = javaMethod.getName();

			if (!javaMethod.isPublic() || !javaMethod.isStatic()) {
				continue;
			}

			if (methodName.equals("getInstance")) {
				methodName = "getWrappedInstance";
			}

			DocletTag[] docletTags = javaMethod.getTagsByName("deprecated");

			if (ArrayUtil.isNotEmpty(docletTags)) {
				sb.append("\t/**\n");
				sb.append("\t * @deprecated\n");
				sb.append("\t */\n");
				sb.append("\t@Deprecated\n");
			}

			sb.append("public ");

			TypeVariable[] typeParameters = javaMethod.getTypeParameters();

			if (typeParameters.length > 0) {
				sb.append(" <");

				for (int i = 0; i < typeParameters.length; i++) {
					TypeVariable typeParameter = typeParameters[i];

					sb.append(typeParameter.getName());
					sb.append(", ");
				}

				sb.setIndex(sb.index() - 1);

				sb.append("> ");
			}

			sb.append(_getTypeGenericsName(javaMethod.getReturnType()));
			sb.append(" ");
			sb.append(methodName);
			sb.append(StringPool.OPEN_PARENTHESIS);

			JavaParameter[] javaParameters = javaMethod.getParameters();

			for (int i = 0; i < javaParameters.length; i++) {
				JavaParameter javaParameter = javaParameters[i];

				sb.append(_getTypeGenericsName(javaParameter.getType()));

				if (javaParameter.isVarArgs()) {
					sb.append("...");
				}

				sb.append(" ");
				sb.append(javaParameter.getName());
				sb.append(", ");
			}

			if (javaParameters.length > 0) {
				sb.setIndex(sb.index() - 1);
			}

			sb.append(StringPool.CLOSE_PARENTHESIS);

			Type[] thrownExceptions = javaMethod.getExceptions();

			Set<String> newExceptions = new LinkedHashSet<>();

			for (int j = 0; j < thrownExceptions.length; j++) {
				Type thrownException = thrownExceptions[j];

				newExceptions.add(thrownException.getValue());
			}

			if (!newExceptions.isEmpty()) {
				sb.append(" throws ");

				for (String newException : newExceptions) {
					sb.append(newException);
					sb.append(", ");
				}

				sb.setIndex(sb.index() - 1);
			}

			sb.append("{\n");

			if (!javaMethod.getReturnType().getValue().equals("void")) {
				sb.append("return ");
			}

			sb.append(javaClass.getName());
			sb.append(".");
			sb.append(javaMethod.getName());
			sb.append("(");

			for (int j = 0; j < javaParameters.length; j++) {
				JavaParameter javaParameter = javaParameters[j];

				sb.append(javaParameter.getName());
				sb.append(", ");
			}

			if (javaParameters.length > 0) {
				sb.setIndex(sb.index() - 1);
			}

			sb.append(");}\n");
		}

		// Private constructor

		sb.append("private ");
		sb.append(javaClass.getName());
		sb.append("_IW() {}");

		// Fields

		sb.append("private static ");
		sb.append(javaClass.getName());
		sb.append("_IW _instance = new ");
		sb.append(javaClass.getName());
		sb.append("_IW();");

		// Class close brace

		sb.append("}");

		// Write file

		File file = new File(
			parentDir + "/" +
				StringUtil.replace(javaClass.getPackage().getName(), '.', '/') +
					"/" + javaClass.getName() + "_IW.java");

		ToolsUtil.writeFile(file, sb.toString(), null);
	}

	private String _getDimensions(Type type) {
		String dimensions = "";

		for (int i = 0; i < type.getDimensions(); i++) {
			dimensions += "[]";
		}

		return dimensions;
	}

	private JavaClass _getJavaClass(String parentDir, String srcFile)
		throws IOException {

		String className = StringUtil.replace(
			srcFile.substring(0, srcFile.length() - 5), '/', '.');

		JavaDocBuilder builder = new JavaDocBuilder();

		builder.addSource(new File(parentDir + "/" + srcFile));

		return builder.getClassByName(className);
	}

	private String _getTypeGenericsName(Type type) {
		Type[] actualTypeArguments = type.getActualTypeArguments();

		if (actualTypeArguments == null) {
			String value = type.getValue();

			return value.concat(_getDimensions(type));
		}

		StringBundler sb = new StringBundler(
			actualTypeArguments.length * 2 + 3);

		sb.append(type.getValue());
		sb.append("<");

		for (int i = 0; i < actualTypeArguments.length; i++) {
			sb.append(_getTypeGenericsName(actualTypeArguments[i]));
			sb.append(", ");
		}

		if (actualTypeArguments.length > 0) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append(">");
		sb.append(_getDimensions(type));

		return sb.toString();
	}

}