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

package com.liferay.frontend.css.rtl.converter;

import com.helger.commons.charset.CCharset;
import com.helger.css.ECSSVersion;
import com.helger.css.decl.CSSDeclaration;
import com.helger.css.decl.CSSExpression;
import com.helger.css.decl.CSSExpressionMemberFunction;
import com.helger.css.decl.CSSExpressionMemberTermSimple;
import com.helger.css.decl.CSSExpressionMemberTermURI;
import com.helger.css.decl.CSSMediaRule;
import com.helger.css.decl.CSSStyleRule;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.decl.ICSSExpressionMember;
import com.helger.css.decl.ICSSTopLevelRule;
import com.helger.css.reader.CSSReader;
import com.helger.css.reader.errorhandler.DoNothingCSSParseErrorHandler;
import com.helger.css.writer.CSSWriter;
import com.helger.css.writer.CSSWriterSettings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author David Truong
 */
public class CSSRTLConverter {

	public CSSRTLConverter() {
		this(true);
	}

	public CSSRTLConverter(boolean compress) {
		_cssWriterSettings = new CSSWriterSettings(ECSSVersion.CSS30, compress);

		_cssWriterSettings.setOptimizedOutput(compress);
		_cssWriterSettings.setRemoveUnnecessaryCode(Boolean.TRUE);

		_cssWriter = new CSSWriter(_cssWriterSettings);

		_cssWriter.setWriteFooterText(false);
		_cssWriter.setWriteHeaderText(false);
	}

	public String process(String css) {
		CascadingStyleSheet cascadingStyleSheet = CSSReader.readFromString(
			css, CCharset.CHARSET_UTF_8_OBJ, ECSSVersion.CSS30,
			new DoNothingCSSParseErrorHandler());

		_processRules(cascadingStyleSheet.getAllRules());

		return _cssWriter.getCSSAsString(cascadingStyleSheet);
	}

	private void _convertBackgroundProperties(CSSDeclaration cssDeclaration) {
		CSSExpression cssExpression = cssDeclaration.getExpression();

		List<ICSSExpressionMember> icssExpressionMembers =
			cssExpression.getAllMembers();

		for (ICSSExpressionMember icssExpressionMember :
				icssExpressionMembers) {

			if (icssExpressionMember instanceof CSSExpressionMemberFunction) {
				CSSExpressionMemberFunction cssExpressionMemberFunction =
					(CSSExpressionMemberFunction)icssExpressionMember;

				_reverseValue(cssExpressionMemberFunction.getExpression());
			}
			else if (icssExpressionMember instanceof
						CSSExpressionMemberTermSimple) {

				CSSExpressionMemberTermSimple cssExpressionMemberTermSimple =
					(CSSExpressionMemberTermSimple)icssExpressionMember;

				cssExpressionMemberTermSimple.setValue(
					_reverse(cssExpressionMemberTermSimple.getValue()));
			}
			else if (icssExpressionMember instanceof
						CSSExpressionMemberTermURI) {

				CSSExpressionMemberTermURI cssExpressionMemberTermURI =
					(CSSExpressionMemberTermURI)icssExpressionMember;

				String uri = cssExpressionMemberTermURI.getURIString();

				int index = uri.lastIndexOf("/") + 1;

				String fileName = _reverse(uri.substring(index));

				cssExpressionMemberTermURI.setURIString(
					uri.substring(0, index) + fileName);
			}
		}

		List<CSSExpressionMemberTermSimple> cssExpressionMemberTermSimples =
			cssExpression.getAllSimpleMembers();

		if (cssExpressionMemberTermSimples.size() == 1) {
			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple =
				cssExpressionMemberTermSimples.get(0);

			String value = cssExpressionMemberTermSimple.getValue();

			Matcher matcher = _percentOrLengthPattern.matcher(value);

			if (matcher.matches()) {
				cssExpression.addTermSimple(value);

				cssExpressionMemberTermSimple.setValue("right");
			}
		}
		else if (cssExpressionMemberTermSimples.size() == 2) {
			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple =
				cssExpressionMemberTermSimples.get(0);

			String value = cssExpressionMemberTermSimple.getValue();

			Matcher matcher = _percentPattern.matcher(value);

			if (matcher.matches()) {
				int delta = Integer.valueOf(value.replaceAll("[^\\d]", ""), 10);

				value = (100 - delta) + "%";

				cssExpressionMemberTermSimple.setValue(value);
			}
		}
	}

	private void _convertShorthandProperties(CSSDeclaration cssDeclaration) {
		CSSExpression cssExpression = cssDeclaration.getExpression();

		List<CSSExpressionMemberTermSimple> cssExpressionMemberTermSimples =
			cssExpression.getAllSimpleMembers();

		if (cssExpressionMemberTermSimples.size() == 4) {
			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple2 =
				cssExpressionMemberTermSimples.get(1);

			String value = cssExpressionMemberTermSimple2.getValue();

			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple4 =
				cssExpressionMemberTermSimples.get(3);

			cssExpressionMemberTermSimple2.setValue(
				cssExpressionMemberTermSimple4.getValue());

			cssExpressionMemberTermSimple4.setValue(value);
		}
	}

	private void _convertShorthandRadiusProperties(
		CSSDeclaration cssDeclaration) {

		CSSExpression cssExpression = cssDeclaration.getExpression();

		List<CSSExpressionMemberTermSimple> cssExpressionMemberTermSimples =
			cssExpression.getAllSimpleMembers();

		if (cssExpressionMemberTermSimples.size() == 4) {
			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple1 =
				cssExpressionMemberTermSimples.get(0);

			String value = cssExpressionMemberTermSimple1.getValue();

			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple2 =
				cssExpressionMemberTermSimples.get(1);

			cssExpressionMemberTermSimple1.setValue(
				cssExpressionMemberTermSimple2.getValue());

			cssExpressionMemberTermSimple2.setValue(value);

			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple3 =
				cssExpressionMemberTermSimples.get(2);

			value = cssExpressionMemberTermSimple3.getValue();

			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple4 =
				cssExpressionMemberTermSimples.get(3);

			cssExpressionMemberTermSimple3.setValue(
				cssExpressionMemberTermSimple4.getValue());

			cssExpressionMemberTermSimple4.setValue(value);
		}
		else if (cssExpressionMemberTermSimples.size() == 3) {
			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple1 =
				cssExpressionMemberTermSimples.get(0);

			String value = cssExpressionMemberTermSimple1.getValue();

			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple2 =
				cssExpressionMemberTermSimples.get(1);

			cssExpressionMemberTermSimple1.setValue(
				cssExpressionMemberTermSimple2.getValue());

			cssExpressionMemberTermSimple2.setValue(value);

			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple3 =
				cssExpressionMemberTermSimples.get(2);

			value = cssExpressionMemberTermSimple3.getValue();

			cssExpressionMemberTermSimple3.setValue(
				cssExpressionMemberTermSimple1.getValue());

			cssExpression.addTermSimple(value);
		}
	}

	private void _processRule(CSSStyleRule cssStyleRule) {
		for (CSSDeclaration cssDeclaration :
				cssStyleRule.getAllDeclarations()) {

			String property = _stripAsterisk(cssDeclaration.getProperty());

			if (_backgroundProperties.contains(property)) {
				_resizeYui3BackgroundPosition(cssStyleRule, cssDeclaration);

				_convertBackgroundProperties(cssDeclaration);
			}
			else if (_cursorProperties.contains(property)) {
				_replaceYui3Cursors(cssStyleRule, cssDeclaration);
			}
			else if (_iconProperties.contains(property)) {
				_replaceIcons(cssDeclaration);
			}
			else if (_reverseProperties.contains(property)) {
				_resizeYui3Offset(cssStyleRule, cssDeclaration);

				_reverseProperty(cssDeclaration);
			}
			else if (_reverseValueProperties.contains(property)) {
				_reverseValue(cssDeclaration);
			}
			else if (_shorthandRadiusProperties.contains(property)) {
				_convertShorthandRadiusProperties(cssDeclaration);
			}
			else if (_shorthandProperties.contains(property)) {
				_convertShorthandProperties(cssDeclaration);
			}
		}
	}

	private void _processRules(List<ICSSTopLevelRule> icssTopLevelRules) {
		for (ICSSTopLevelRule icssTopLevelRule : icssTopLevelRules) {
			if (icssTopLevelRule instanceof CSSMediaRule) {
				CSSMediaRule cssMediaRule = (CSSMediaRule)icssTopLevelRule;

				_processRules(cssMediaRule.getAllRules());
			}
			else if (icssTopLevelRule instanceof CSSStyleRule) {
				_processRule((CSSStyleRule)icssTopLevelRule);
			}
		}
	}

	private void _replace(
		CSSDeclaration cssDeclaration, Map<String, String> replacementValues) {

		CSSExpression cssExpression = cssDeclaration.getExpression();

		List<CSSExpressionMemberTermSimple> cssExpressionMemberTermSimples =
			cssExpression.getAllSimpleMembers();

		for (CSSExpressionMemberTermSimple cssExpressionMemberTermSimple :
				cssExpressionMemberTermSimples) {

			String replacementValue = replacementValues.get(
				cssExpressionMemberTermSimple.getValue());

			if (replacementValue != null) {
				cssExpressionMemberTermSimple.setValue(replacementValue);
			}
		}
	}

	private void _replaceIcons(CSSDeclaration cssDeclaration) {
		_replace(cssDeclaration, _replacementIcons);
	}

	private void _replaceYui3Cursors(
		CSSStyleRule cssStyleRule, CSSDeclaration cssDeclaration) {

		String selector = cssStyleRule.getSelectorsAsCSSString(
			_cssWriterSettings, 0);

		if (selector.contains(_YUI3_RESIZE_HANDLE)) {
			_replace(cssDeclaration, _yui3ReplacementCursors);
		}
	}

	private void _resizeYui3BackgroundPosition(
		CSSStyleRule cssStyleRule, CSSDeclaration cssDeclaration) {

		String selector = cssStyleRule.getSelectorsAsCSSString(
			_cssWriterSettings, 0);

		String property = cssDeclaration.getProperty();

		Matcher matcher = _yui3ResizeHandleInnerPattern.matcher(selector);

		if (!property.equals("background-position")) {
			return;
		}

		if (!matcher.find()) {
			return;
		}

		CSSExpression cssExpression = cssDeclaration.getExpression();

		List<CSSExpressionMemberTermSimple> cssExpressionMemberTermSimples =
			cssExpression.getAllSimpleMembers();

		if (selector.endsWith("bl")) {
			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple1 =
				cssExpressionMemberTermSimples.get(0);

			cssExpressionMemberTermSimple1.setValue("-30px");
		}
		else if (selector.endsWith("br")) {
			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple1 =
				cssExpressionMemberTermSimples.get(0);

			cssExpressionMemberTermSimple1.setValue("-75px");
		}
		else if (selector.endsWith("tl")) {
			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple1 =
				cssExpressionMemberTermSimples.get(0);

			cssExpressionMemberTermSimple1.setValue("-58px");
		}
		else if (selector.endsWith("tr")) {
			CSSExpressionMemberTermSimple cssExpressionMemberTermSimple1 =
				cssExpressionMemberTermSimples.get(0);

			cssExpressionMemberTermSimple1.setValue("-47px");
		}

		CSSExpressionMemberTermSimple cssExpressionMemberTermSimple2 =
			cssExpressionMemberTermSimples.get(1);

		cssExpressionMemberTermSimple2.setValue("0");
	}

	private void _resizeYui3Offset(
		CSSStyleRule cssStyleRule, CSSDeclaration cssDeclaration) {

		String selector = cssStyleRule.getSelectorsAsCSSString(
			_cssWriterSettings, 0);

		Matcher matcher = _yui3ResizeHandleInnerPattern.matcher(selector);

		if (!matcher.find()) {
			return;
		}

		CSSExpression cssExpression = cssDeclaration.getExpression();

		List<CSSExpressionMemberTermSimple> cssExpressionMemberTermSimples =
			cssExpression.getAllSimpleMembers();

		for (CSSExpressionMemberTermSimple cssExpressionMemberTermSimple :
				cssExpressionMemberTermSimples) {

			cssExpressionMemberTermSimple.setValue("2px");
		}
	}

	private String _reverse(String s) {
		if (s.contains("right")) {
			return s.replace("right", "left");
		}
		else if (s.contains("left")) {
			return s.replace("left", "right");
		}

		return s;
	}

	private void _reverseProperty(CSSDeclaration cssDeclaration) {
		String property = cssDeclaration.getProperty();

		String asterisk = "";

		if (property.startsWith("*")) {
			asterisk = "*";

			property = _stripAsterisk(property);
		}

		property = _reverse(property);

		cssDeclaration.setProperty(asterisk + property);
	}

	private void _reverseValue(CSSDeclaration cssDeclaration) {
		_reverseValue(cssDeclaration.getExpression());
	}

	private void _reverseValue(CSSExpression cssExpression) {
		List<CSSExpressionMemberTermSimple> cssExpressionMemberTermSimples =
			cssExpression.getAllSimpleMembers();

		for (CSSExpressionMemberTermSimple cssExpressionMemberTermSimple :
				cssExpressionMemberTermSimples) {

			String value = cssExpressionMemberTermSimple.getValue();

			value = _reverse(value);

			if (value.contains("rtl")) {
				value = value.replace("rtl", "ltr");
			}
			else if (value.contains("ltr")) {
				value = value.replace("ltr", "rtl");
			}

			cssExpressionMemberTermSimple.setValue(value);
		}
	}

	private String _stripAsterisk(String property) {
		return property.replaceAll("\\**\\b", "");
	}

	private static final String _YUI3_RESIZE_HANDLE = ".yui3-resize-handle";

	private static final List<String> _backgroundProperties = Arrays.asList(
		"background", "background-image", "background-position");
	private static final List<String> _cursorProperties = Arrays.asList(
		"cursor");
	private static final List<String> _iconProperties = Arrays.asList(
		"content");
	private static final Pattern _percentOrLengthPattern = Pattern.compile(
		"(\\d+)([a-z]{2}|%)");
	private static final Pattern _percentPattern = Pattern.compile("\\d+%");
	private static final Map<String, String> _replacementIcons =
		new HashMap<>();
	private static final List<String> _reverseProperties = Arrays.asList(
		"-moz-border-radius-bottomleft", "-moz-border-radius-bottomright",
		"-moz-border-radius-topleft", "-moz-border-radius-topright",
		"-webkit-border-bottom-left-radius",
		"-webkit-border-bottom-right-radius", "-webkit-border-top-left-radius",
		"-webkit-border-top-right-radius", "border-bottom-left-radius",
		"border-bottom-right-radius", "border-left", "border-left-color",
		"border-left-width", "border-radius-bottomleft",
		"border-radius-bottomright", "border-radius-topleft",
		"border-radius-topright", "border-right", "border-right-color",
		"border-right-width", "border-top-left-radius",
		"border-top-right-radius", "left", "margin-left", "margin-right",
		"padding-left", "padding-right", "right");
	private static final List<String> _reverseValueProperties = Arrays.asList(
		"clear", "direction", "float", "text-align");
	private static final List<String> _shorthandProperties = Arrays.asList(
		"border-color", "border-style", "border-width", "margin", "padding");
	private static final List<String> _shorthandRadiusProperties =
		Arrays.asList(
			"-moz-border-radius", "-webkit-border-radius", "border-radius");
	private static final Map<String, String> _yui3ReplacementCursors =
		new HashMap<>();
	private static final Pattern _yui3ResizeHandleInnerPattern =
		Pattern.compile("\\.yui3-resize-handle-inner-(tr|tl|br|bl)");

	static {
		_replacementIcons.put("\"\\f053\"", "\"\\f054\"");
		_replacementIcons.put("\"\\f054\"", "\"\\f053\"");
		_replacementIcons.put("\"\\f060\"", "\"\\f061\"");
		_replacementIcons.put("\"\\f061\"", "\"\\f060\"");
		_replacementIcons.put("\"\\f0a4\"", "\"\\f0a5\"");
		_replacementIcons.put("\"\\f0a5\"", "\"\\f0a4\"");
		_replacementIcons.put("\"\\f0a8\"", "\"\\f0a9\"");
		_replacementIcons.put("\"\\f0a9\"", "\"\\f0a8\"");
		_replacementIcons.put("\"\\f0d9\"", "\"\\f0da\"");
		_replacementIcons.put("\"\\f0da\"", "\"\\f0d9\"");
		_replacementIcons.put("\"\\f100\"", "\"\\f101\"");
		_replacementIcons.put("\"\\f101\"", "\"\\f100\"");
		_replacementIcons.put("\"\\f104\"", "\"\\f105\"");
		_replacementIcons.put("\"\\f105\"", "\"\\f104\"");
		_replacementIcons.put("\"\\f137\"", "\"\\f138\"");
		_replacementIcons.put("\"\\f138\"", "\"\\f137\"");
		_replacementIcons.put("\"\\f177\"", "\"\\f178\"");
		_replacementIcons.put("\"\\f178\"", "\"\\f177\"");

		_yui3ReplacementCursors.put("e-resize", "w-resize");
		_yui3ReplacementCursors.put("w-resize", "e-resize");
		_yui3ReplacementCursors.put("ne-resize", "nw-resize");
		_yui3ReplacementCursors.put("nw-resize", "ne-resize");
		_yui3ReplacementCursors.put("se-resize", "sw-resize");
		_yui3ReplacementCursors.put("sw-resize", "se-resize");
	}

	private final CSSWriter _cssWriter;
	private final CSSWriterSettings _cssWriterSettings;

}