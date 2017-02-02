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

package com.liferay.portal.kernel.servlet.taglib.aui;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Mergeable;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ScriptData implements Mergeable<ScriptData>, Serializable {

	public void append(
		String portletId, String content, String modules,
		ModulesType modulesType) {

		PortletData portletData = _getPortletData(portletId);

		portletData.append(content, modules, modulesType);
	}

	public void append(
		String portletId, StringBundler contentSB, String modules,
		ModulesType modulesType) {

		PortletData portletData = _getPortletData(portletId);

		portletData.append(contentSB, modules, modulesType);
	}

	public void mark() {
		for (PortletData portletData : _portletDataMap.values()) {
			_addToSBIndexList(portletData._auiCallbackSB);
			_addToSBIndexList(portletData._es6CallbackSB);
			_addToSBIndexList(portletData._rawSB);
		}
	}

	@Override
	public ScriptData merge(ScriptData scriptData) {
		if ((scriptData != null) && (scriptData != this)) {
			_portletDataMap.putAll(scriptData._portletDataMap);
		}

		return this;
	}

	public void reset() {
		for (ObjectValuePair<StringBundler, Integer> ovp : _sbIndexList) {
			StringBundler sb = ovp.getKey();

			sb.setIndex(ovp.getValue());
		}
	}

	@Override
	public ScriptData split() {
		return new ScriptData();
	}

	public void writeTo(Writer writer) throws IOException {
		writer.write("<script type=\"text/javascript\">\n// <![CDATA[\n");

		StringBundler auiModulesSB = new StringBundler(_portletDataMap.size());
		Set<String> auiModulesSet = new HashSet<>();
		StringBundler es6ModulesSB = new StringBundler(_portletDataMap.size());
		Set<String> es6ModulesSet = new HashSet<>();

		for (PortletData portletData : _portletDataMap.values()) {
			portletData._rawSB.writeTo(writer);

			if (!portletData._auiModulesSet.isEmpty()) {
				auiModulesSB.append(portletData._auiCallbackSB);
			}

			if (!portletData._es6ModulesSet.isEmpty()) {
				es6ModulesSB.append(portletData._es6CallbackSB);
			}

			auiModulesSet.addAll(portletData._auiModulesSet);
			es6ModulesSet.addAll(portletData._es6ModulesSet);
		}

		if ((auiModulesSB.index() == 0) && (es6ModulesSB.index() == 0)) {
			writer.write("\n// ]]>\n</script>");

			return;
		}

		if (!es6ModulesSet.isEmpty()) {
			writer.write("require(");

			Iterator<String> iterator = es6ModulesSet.iterator();

			while (iterator.hasNext()) {
				writer.write(StringPool.APOSTROPHE);
				writer.write(iterator.next());
				writer.write(StringPool.APOSTROPHE);

				if (iterator.hasNext()) {
					writer.write(StringPool.COMMA_AND_SPACE);
				}
			}

			writer.write(StringPool.COMMA_AND_SPACE);
			writer.write("function(");

			iterator = es6ModulesSet.iterator();

			Set<String> variableNames = new HashSet<>(es6ModulesSet.size());

			while (iterator.hasNext()) {
				writer.write(_generateVariable(iterator.next(), variableNames));

				if (iterator.hasNext()) {
					writer.write(StringPool.COMMA_AND_SPACE);
				}
			}

			writer.write(") {\n");

			es6ModulesSB.writeTo(writer);

			writer.write("},\nfunction(error) {\nconsole.error(error);\n});");
		}

		if (!auiModulesSet.isEmpty()) {
			writer.write("AUI().use(");

			for (String use : auiModulesSet) {
				writer.write(StringPool.APOSTROPHE);
				writer.write(use);
				writer.write(StringPool.APOSTROPHE);
				writer.write(StringPool.COMMA_AND_SPACE);
			}

			writer.write("function(A) {");

			auiModulesSB.writeTo(writer);

			writer.write("});");
		}

		writer.write("\n// ]]>\n</script>");
	}

	public static enum ModulesType {

		AUI, ES6

	}

	private void _addToSBIndexList(StringBundler sb) {
		ObjectValuePair<StringBundler, Integer> ovp = new ObjectValuePair<>(
			sb, sb.index());

		int index = _sbIndexList.indexOf(ovp);

		if (index == -1) {
			_sbIndexList.add(ovp);
		}
		else {
			ovp = _sbIndexList.get(index);

			ovp.setValue(sb.index());
		}
	}

	private String _generateVariable(String name, Set<String> names) {
		StringBuilder sb = new StringBuilder(name.length());

		char c = name.charAt(0);

		boolean modified = true;

		if ((CharPool.LOWER_CASE_A <= c) && (c <= CharPool.LOWER_CASE_Z) ||
			(c == CharPool.UNDERLINE)) {

			sb.append(c);

			modified = false;
		}
		else if ((CharPool.UPPER_CASE_A <= c) && (c <= CharPool.UPPER_CASE_Z)) {
			sb.append((char)(c + 32));
		}
		else {
			sb.append(CharPool.UNDERLINE);
		}

		boolean startNewWord = false;

		for (int i = 1; i < name.length(); i++) {
			c = name.charAt(i);

			if ((CharPool.LOWER_CASE_A <= c) && (c <= CharPool.LOWER_CASE_Z)) {
				if (startNewWord) {
					sb.append((char)(c - 32));

					startNewWord = false;
				}
				else {
					sb.append(c);
				}
			}
			else if ((CharPool.UPPER_CASE_A <= c) &&
					 (c <= CharPool.UPPER_CASE_Z) ||
					 ((CharPool.NUMBER_0 <= c) && (c <= CharPool.NUMBER_9)) ||
					 (c == CharPool.UNDERLINE)) {

				sb.append(c);

				startNewWord = false;
			}
			else {
				modified = true;

				startNewWord = true;
			}
		}

		String safeName = name;

		if (modified) {
			safeName = sb.toString();

			name = safeName;
		}

		int i = 1;

		while (!names.add(name)) {
			name = safeName.concat(String.valueOf(i++));
		}

		return name;
	}

	private PortletData _getPortletData(String portletId) {
		if (Validator.isNull(portletId)) {
			portletId = StringPool.BLANK;
		}

		PortletData portletData = _portletDataMap.get(portletId);

		if (portletData == null) {
			portletData = new PortletData();

			PortletData oldPortletData = _portletDataMap.putIfAbsent(
				portletId, portletData);

			if (oldPortletData != null) {
				portletData = oldPortletData;
			}
		}

		return portletData;
	}

	private static final long serialVersionUID = 1L;

	private final ConcurrentMap<String, PortletData> _portletDataMap =
		new ConcurrentHashMap<>();
	private final List<ObjectValuePair<StringBundler, Integer>> _sbIndexList =
		new ArrayList<>();

	private static class PortletData implements Serializable {

		public void append(
			String content, String modules, ModulesType modulesType) {

			if (Validator.isNull(modules)) {
				_rawSB.append(content);
			}
			else {
				String[] modulesArray = StringUtil.split(modules);

				if (modulesType == ModulesType.AUI) {
					_auiCallbackSB.append("(function() {");
					_auiCallbackSB.append(content);
					_auiCallbackSB.append("})();");

					for (String module : modulesArray) {
						_auiModulesSet.add(StringUtil.trim(module));
					}
				}
				else {
					_es6CallbackSB.append("(function() {");
					_es6CallbackSB.append(content);
					_es6CallbackSB.append("})();");

					for (String module : modulesArray) {
						_es6ModulesSet.add(StringUtil.trim(module));
					}
				}
			}
		}

		public void append(
			StringBundler contentSB, String modules, ModulesType modulesType) {

			if (Validator.isNull(modules)) {
				_rawSB.append(contentSB);
			}
			else {
				String[] modulesArray = StringUtil.split(modules);

				if (modulesType == ModulesType.AUI) {
					_auiCallbackSB.append("(function() {");
					_auiCallbackSB.append(contentSB);
					_auiCallbackSB.append("})();");

					for (String module : modulesArray) {
						_auiModulesSet.add(StringUtil.trim(module));
					}
				}
				else {
					_es6CallbackSB.append("(function() {");
					_es6CallbackSB.append(contentSB);
					_es6CallbackSB.append("})();");

					for (String module : modulesArray) {
						_es6ModulesSet.add(StringUtil.trim(module));
					}
				}
			}
		}

		private static final long serialVersionUID = 1L;

		private final StringBundler _auiCallbackSB = new StringBundler();
		private final Set<String> _auiModulesSet = new HashSet<>();
		private final StringBundler _es6CallbackSB = new StringBundler();
		private final Set<String> _es6ModulesSet = new HashSet<>();
		private final StringBundler _rawSB = new StringBundler();

	}

}