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

package com.liferay.portal.minifier;

import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.util.PropsValues;

import com.yahoo.platform.yui.compressor.CssCompressor;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class MinifierUtil {

	public static String minifyCss(String content) {
		if (!PropsValues.MINIFIER_ENABLED) {
			return content;
		}

		return _instance._minifyCss(content);
	}

	public static String minifyJavaScript(String resourceName, String content) {
		if (!PropsValues.MINIFIER_ENABLED) {
			return content;
		}

		return _instance._minifyJavaScript(resourceName, content);
	}

	private static JavaScriptMinifier _getJavaScriptMinifier() {
		try {
			return (JavaScriptMinifier)InstanceFactory.newInstance(
				PropsValues.MINIFIER_JAVASCRIPT_IMPL);
		}
		catch (Exception e) {
			_log.error(
				"Unable to instantiate "+ PropsValues.MINIFIER_JAVASCRIPT_IMPL);

			return new GoogleJavaScriptMinifier();
		}
	}

	private MinifierUtil() {
		_javaScriptMinifierInstance = _getJavaScriptMinifier();
	}

	private String _minifyCss(String content) {
		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		try {
			CssCompressor cssCompressor = new CssCompressor(
				new UnsyncStringReader(content));

			cssCompressor.compress(
				unsyncStringWriter, PropsValues.YUI_COMPRESSOR_CSS_LINE_BREAK);
		}
		catch (Exception e) {
			_log.error("Unable to minfiy CSS:\n" + content);

			unsyncStringWriter.append(content);
		}

		return unsyncStringWriter.toString();
	}

	private String _minifyJavaScript(String resourceName, String content) {
		return _javaScriptMinifierInstance.compress(resourceName, content);
	}

	private static final Log _log = LogFactoryUtil.getLog(MinifierUtil.class);

	private static final MinifierUtil _instance = new MinifierUtil();

	private final JavaScriptMinifier _javaScriptMinifierInstance;

}