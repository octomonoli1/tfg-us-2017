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
import com.liferay.portal.util.PropsValues;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import com.yahoo.platform.yui.mozilla.javascript.ErrorReporter;
import com.yahoo.platform.yui.mozilla.javascript.EvaluatorException;

/**
 * @author Carlos Sierra Andrés
 */
public class YahooJavaScriptMinifier implements JavaScriptMinifier {

	@Override
	public String compress(String resourceName, String content) {
		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		try {
			JavaScriptCompressor javaScriptCompressor =
				new JavaScriptCompressor(
					new UnsyncStringReader(content),
					new JavaScriptErrorReporter());

			javaScriptCompressor.compress(
				unsyncStringWriter, PropsValues.YUI_COMPRESSOR_JS_LINE_BREAK,
				PropsValues.YUI_COMPRESSOR_JS_MUNGE,
				PropsValues.YUI_COMPRESSOR_JS_VERBOSE,
				PropsValues.YUI_COMPRESSOR_JS_PRESERVE_ALL_SEMICOLONS,
				PropsValues.YUI_COMPRESSOR_JS_DISABLE_OPTIMIZATIONS);
		}
		catch (Exception e) {
			_log.error("Unable to minify JavaScript:\n" + content);

			unsyncStringWriter.append(content);
		}

		return unsyncStringWriter.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		YahooJavaScriptMinifier.class);

	private static class JavaScriptErrorReporter implements ErrorReporter {

		@Override
		public void error(
			String message, String sourceName, int line, String lineSource,
			int lineOffset) {

			if (line < 0) {
				_log.error(message);
			}
			else {
				_log.error(line + ": " + lineOffset + ": " + message);
			}
		}

		@Override
		public EvaluatorException runtimeError(
			String message, String sourceName, int line, String lineSource,
			int lineOffset) {

			error(message, sourceName, line, lineSource, lineOffset);

			return new EvaluatorException(message);
		}

		@Override
		public void warning(
			String message, String sourceName, int line, String lineSource,
			int lineOffset) {

			if (!_log.isWarnEnabled()) {
				return;
			}

			if (line < 0) {
				_log.warn(message);
			}
			else {
				_log.warn(line + ": " + lineOffset + ": " + message);
			}
		}

	}

}