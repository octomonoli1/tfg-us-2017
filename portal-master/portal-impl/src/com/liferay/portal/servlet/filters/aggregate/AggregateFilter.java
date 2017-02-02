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

package com.liferay.portal.servlet.filters.aggregate;

import com.liferay.portal.kernel.cache.key.CacheKeyGenerator;
import com.liferay.portal.kernel.cache.key.CacheKeyGeneratorUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BrowserSniffer;
import com.liferay.portal.kernel.servlet.BufferCacheServletResponse;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.PortalWebResourceConstants;
import com.liferay.portal.kernel.servlet.PortalWebResourcesUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.URLUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.minifier.MinifierUtil;
import com.liferay.portal.servlet.filters.IgnoreModuleRequestFilter;
import com.liferay.portal.servlet.filters.dynamiccss.DynamicCSSUtil;
import com.liferay.portal.util.AggregateUtil;
import com.liferay.portal.util.JavaScriptBundleUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Eduardo Lundgren
 */
public class AggregateFilter extends IgnoreModuleRequestFilter {

	/**
	 * @see DynamicCSSUtil#propagateQueryString(String, String)
	 */
	public static String aggregateCss(ServletPaths servletPaths, String content)
		throws IOException {

		StringBundler sb = new StringBundler();

		int pos = 0;

		while (true) {
			int commentX = content.indexOf(_CSS_COMMENT_BEGIN, pos);
			int commentY = content.indexOf(
				_CSS_COMMENT_END, commentX + _CSS_COMMENT_BEGIN.length());

			int importX = content.indexOf(_CSS_IMPORT_BEGIN, pos);
			int importY = content.indexOf(
				_CSS_IMPORT_END, importX + _CSS_IMPORT_BEGIN.length());

			if ((importX == -1) || (importY == -1)) {
				sb.append(content.substring(pos));

				break;
			}
			else if ((commentX != -1) && (commentY != -1) &&
					 (commentX < importX) && (commentY > importX)) {

				commentY += _CSS_COMMENT_END.length();

				sb.append(content.substring(pos, commentY));

				pos = commentY;
			}
			else {
				sb.append(content.substring(pos, importX));

				String mediaQuery = StringPool.BLANK;

				int mediaQueryImportX = content.indexOf(
					CharPool.CLOSE_PARENTHESIS,
					importX + _CSS_IMPORT_BEGIN.length());
				int mediaQueryImportY = content.indexOf(
					CharPool.SEMICOLON, importX + _CSS_IMPORT_BEGIN.length());

				String importFileName = null;

				if (importY != mediaQueryImportX) {
					mediaQuery = content.substring(
						mediaQueryImportX + 1, mediaQueryImportY);

					importFileName = content.substring(
						importX + _CSS_IMPORT_BEGIN.length(),
						mediaQueryImportX);
				}
				else {
					importFileName = content.substring(
						importX + _CSS_IMPORT_BEGIN.length(), importY);
				}

				String importContent = null;

				if (Validator.isUrl(importFileName)) {
					URL url = new URL(importFileName);

					URLConnection urlConnection = url.openConnection();

					importContent = StringUtil.read(
						urlConnection.getInputStream());
				}
				else {
					ServletPaths importFileServletPaths = servletPaths.down(
						importFileName);

					importContent = importFileServletPaths.getContent();

					if (importContent == null) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"File " +
									importFileServletPaths.getResourcePath() +
										" does not exist");
						}

						importContent = StringPool.BLANK;
					}

					String importDirName = StringPool.BLANK;

					int slashPos = importFileName.lastIndexOf(CharPool.SLASH);

					if (slashPos != -1) {
						importDirName = importFileName.substring(
							0, slashPos + 1);
					}

					ServletPaths importDirServletPaths = servletPaths.down(
						importDirName);

					importContent = aggregateCss(
						importDirServletPaths, importContent);

					// LEP-7540

					String baseURL = _BASE_URL.concat(
						importDirServletPaths.getResourcePath());

					if (!baseURL.endsWith(StringPool.SLASH)) {
						baseURL += StringPool.SLASH;
					}

					importContent = AggregateUtil.updateRelativeURLs(
						importContent, baseURL);
				}

				if (Validator.isNotNull(mediaQuery)) {
					sb.append(_CSS_MEDIA_QUERY);
					sb.append(CharPool.SPACE);
					sb.append(mediaQuery);
					sb.append(CharPool.OPEN_CURLY_BRACE);
					sb.append(importContent);
					sb.append(CharPool.CLOSE_CURLY_BRACE);

					pos = mediaQueryImportY + 1;
				}
				else {
					sb.append(importContent);

					pos = importY + _CSS_IMPORT_END.length();
				}
			}
		}

		return sb.toString();
	}

	public static String aggregateJavaScript(
		ServletPaths servletPaths, String[] fileNames) {

		StringBundler sb = new StringBundler(fileNames.length * 2);

		for (String fileName : fileNames) {
			ServletPaths fileServletPaths = servletPaths.down(fileName);

			String content = fileServletPaths.getContent();

			if (Validator.isNull(content)) {
				continue;
			}

			sb.append(content);
			sb.append(StringPool.NEW_LINE);
		}

		return getJavaScriptContent(
			StringUtil.merge(fileNames, "+"), sb.toString());
	}

	@Override
	public void init(FilterConfig filterConfig) {
		super.init(filterConfig);

		_servletContext = filterConfig.getServletContext();

		File tempDir = (File)_servletContext.getAttribute(
			JavaConstants.JAVAX_SERVLET_CONTEXT_TEMPDIR);

		_tempDir = new File(tempDir, _TEMP_DIR);

		_tempDir.mkdirs();
	}

	protected static String getJavaScriptContent(
		String resourceName, String content) {

		return MinifierUtil.minifyJavaScript(resourceName, content);
	}

	protected Object getBundleContent(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		String minifierType = ParamUtil.getString(request, "minifierType");
		String bundleId = ParamUtil.getString(
			request, "bundleId",
			ParamUtil.getString(request, "minifierBundleId"));

		if (Validator.isNull(minifierType) || Validator.isNull(bundleId) ||
			!ArrayUtil.contains(PropsValues.JAVASCRIPT_BUNDLE_IDS, bundleId)) {

			return null;
		}

		String bundleDirName = PropsUtil.get(
			PropsKeys.JAVASCRIPT_BUNDLE_DIR, new Filter(bundleId));

		ServletContext jsServletContext =
			PortalWebResourcesUtil.getServletContext(
				PortalWebResourceConstants.RESOURCE_TYPE_JS);

		URL bundleDirURL = jsServletContext.getResource(bundleDirName);

		if (bundleDirURL == null) {
			return null;
		}

		String cacheFileName = bundleId;

		String[] fileNames = JavaScriptBundleUtil.getFileNames(bundleId);

		File cacheFile = new File(_tempDir, cacheFileName);

		if (cacheFile.exists()) {
			long lastModified = PortalWebResourcesUtil.getLastModified(
				PortalWebResourceConstants.RESOURCE_TYPE_JS);

			if (lastModified <= cacheFile.lastModified()) {
				response.setContentType(ContentTypes.TEXT_JAVASCRIPT);

				return cacheFile;
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info("Aggregating JavaScript bundle " + bundleId);
		}

		String content = null;

		if (fileNames.length == 0) {
			content = StringPool.BLANK;
		}
		else {
			content = aggregateJavaScript(
				new ServletPaths(jsServletContext, bundleDirName), fileNames);
		}

		response.setContentType(ContentTypes.TEXT_JAVASCRIPT);

		FileUtil.write(cacheFile, content);

		return content;
	}

	protected String getCacheFileName(HttpServletRequest request) {
		CacheKeyGenerator cacheKeyGenerator =
			CacheKeyGeneratorUtil.getCacheKeyGenerator(
				AggregateFilter.class.getName());

		cacheKeyGenerator.append(HttpUtil.getProtocol(request.isSecure()));
		cacheKeyGenerator.append(StringPool.UNDERLINE);
		cacheKeyGenerator.append(request.getRequestURI());

		String requestURL = String.valueOf(request.getRequestURL());

		if (requestURL != null) {
			requestURL = HttpUtil.removeParameter(requestURL, "zx");

			String queryString = HttpUtil.getQueryString(requestURL);

			if (queryString != null) {
				cacheKeyGenerator.append(sterilizeQueryString(queryString));
			}
		}

		return String.valueOf(cacheKeyGenerator.finish());
	}

	protected Object getContent(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		String minifierType = ParamUtil.getString(request, "minifierType");
		String minifierBundleId = ParamUtil.getString(
			request, "minifierBundleId");
		String minifierBundleDirName = ParamUtil.getString(
			request, "minifierBundleDir");

		if (Validator.isNull(minifierType) ||
			Validator.isNotNull(minifierBundleId) ||
			Validator.isNotNull(minifierBundleDirName)) {

			return null;
		}

		String requestURI = request.getRequestURI();

		String resourcePath = requestURI;

		String contextPath = request.getContextPath();

		if (!contextPath.equals(StringPool.SLASH)) {
			resourcePath = resourcePath.substring(contextPath.length());
		}

		if (resourcePath.endsWith(_CSS_EXTENSION) &&
			PortalUtil.isRightToLeft(request)) {

			int pos = resourcePath.lastIndexOf(StringPool.PERIOD);

			resourcePath =
				resourcePath.substring(0, pos) + "_rtl" +
					resourcePath.substring(pos);
		}

		URL resourceURL = _servletContext.getResource(resourcePath);

		if (resourceURL == null) {
			resourceURL = PortalWebResourcesUtil.getResource(resourcePath);

			if (resourceURL == null) {
				return null;
			}
		}

		String cacheCommonFileName = getCacheFileName(request);

		File cacheContentTypeFile = new File(
			_tempDir, cacheCommonFileName + "_E_CONTENT_TYPE");
		File cacheDataFile = new File(
			_tempDir, cacheCommonFileName + "_E_DATA");

		if (cacheDataFile.exists() &&
			(cacheDataFile.lastModified() >=
				URLUtil.getLastModifiedTime(resourceURL))) {

			if (cacheContentTypeFile.exists()) {
				String contentType = FileUtil.read(cacheContentTypeFile);

				response.setContentType(contentType);
			}

			return cacheDataFile;
		}

		String content = null;

		if (resourcePath.endsWith(_CSS_EXTENSION)) {
			if (_log.isInfoEnabled()) {
				_log.info("Minifying CSS " + resourcePath);
			}

			content = getCssContent(
				request, response, resourceURL, resourcePath);

			response.setContentType(ContentTypes.TEXT_CSS);

			FileUtil.write(cacheContentTypeFile, ContentTypes.TEXT_CSS);
		}
		else if (resourcePath.endsWith(_JAVASCRIPT_EXTENSION)) {
			if (_log.isInfoEnabled()) {
				_log.info("Minifying JavaScript " + resourcePath);
			}

			content = getJavaScriptContent(resourceURL);

			response.setContentType(ContentTypes.TEXT_JAVASCRIPT);

			FileUtil.write(cacheContentTypeFile, ContentTypes.TEXT_JAVASCRIPT);
		}
		else if (resourcePath.endsWith(_JSP_EXTENSION)) {
			if (_log.isInfoEnabled()) {
				_log.info("Minifying JSP " + resourcePath);
			}

			BufferCacheServletResponse bufferCacheServletResponse =
				new BufferCacheServletResponse(response);

			processFilter(
				AggregateFilter.class.getName(), request,
				bufferCacheServletResponse, filterChain);

			bufferCacheServletResponse.finishResponse(false);

			content = bufferCacheServletResponse.getString();

			if (minifierType.equals("css")) {
				content = getCssContent(
					request, response, resourcePath, content);
			}
			else if (minifierType.equals("js")) {
				content = getJavaScriptContent(resourcePath, content);
			}

			FileUtil.write(
				cacheContentTypeFile,
				bufferCacheServletResponse.getContentType());
		}
		else {
			return null;
		}

		FileUtil.write(cacheDataFile, content);

		return content;
	}

	protected String getCssContent(
		HttpServletRequest request, HttpServletResponse response,
		String resourcePath, String content) {

		try {
			ServletContext cssServletContext = null;

			String requestURI = request.getRequestURI();

			if (PortalWebResourcesUtil.hasContextPath(requestURI)) {
				cssServletContext =
					PortalWebResourcesUtil.getPathServletContext(requestURI);
			}

			if (cssServletContext == null) {
				cssServletContext = _servletContext;
			}

			content = DynamicCSSUtil.replaceToken(
				cssServletContext, request, content);
		}
		catch (Exception e) {
			_log.error("Unable to replace tokens in CSS " + resourcePath, e);

			if (_log.isDebugEnabled()) {
				_log.debug(content);
			}

			response.setHeader(
				HttpHeaders.CACHE_CONTROL,
				HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);
		}

		String browserId = ParamUtil.getString(request, "browserId");

		if (!browserId.equals(BrowserSniffer.BROWSER_ID_IE)) {
			Matcher matcher = _pattern.matcher(content);

			content = matcher.replaceAll(StringPool.BLANK);
		}

		return MinifierUtil.minifyCss(content);
	}

	protected String getCssContent(
			HttpServletRequest request, HttpServletResponse response,
			URL resourceURL, String resourcePath)
		throws IOException {

		ServletContext cssServletContext = null;
		String resourcePathRoot = null;

		String requestURI = request.getRequestURI();

		if (PortalWebResourcesUtil.hasContextPath(requestURI)) {
			cssServletContext = PortalWebResourcesUtil.getPathServletContext(
				requestURI);
			resourcePathRoot = "/";
		}

		if (cssServletContext == null) {
			cssServletContext = _servletContext;
			resourcePathRoot = ServletPaths.getParentPath(resourcePath);
		}

		URLConnection urlConnection = resourceURL.openConnection();

		String content = StringUtil.read(urlConnection.getInputStream());

		content = aggregateCss(
			new ServletPaths(cssServletContext, resourcePathRoot), content);

		return getCssContent(request, response, resourcePath, content);
	}

	protected String getJavaScriptContent(URL resourceURL) throws IOException {
		URLConnection urlConnection = resourceURL.openConnection();

		String content = StringUtil.read(urlConnection.getInputStream());

		return getJavaScriptContent(resourceURL.toString(), content);
	}

	@Override
	protected boolean isModuleRequest(HttpServletRequest request) {
		String requestURI = request.getRequestURI();

		if (PortalWebResourcesUtil.hasContextPath(requestURI)) {
			return false;
		}

		return super.isModuleRequest(request);
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		Object minifiedContent = getContent(request, response, filterChain);

		if (minifiedContent == null) {
			minifiedContent = getBundleContent(request, response);
		}

		if (minifiedContent == null) {
			processFilter(
				AggregateFilter.class.getName(), request, response,
				filterChain);
		}
		else {
			if (minifiedContent instanceof File) {
				ServletResponseUtil.write(response, (File)minifiedContent);
			}
			else if (minifiedContent instanceof String) {
				ServletResponseUtil.write(response, (String)minifiedContent);
			}
		}
	}

	protected String sterilizeQueryString(String queryString) {
		return StringUtil.replace(
			queryString, new char[] {CharPool.SLASH, CharPool.BACK_SLASH},
			new char[] {CharPool.UNDERLINE, CharPool.UNDERLINE});
	}

	private static final String _BASE_URL = "@base_url@";

	private static final String _CSS_COMMENT_BEGIN = "/*";

	private static final String _CSS_COMMENT_END = "*/";

	private static final String _CSS_EXTENSION = ".css";

	private static final String _CSS_IMPORT_BEGIN = "@import url(";

	private static final String _CSS_IMPORT_END = ");";

	private static final String _CSS_MEDIA_QUERY = "@media";

	private static final String _JAVASCRIPT_EXTENSION = ".js";

	private static final String _JSP_EXTENSION = ".jsp";

	private static final String _TEMP_DIR = "aggregate";

	private static final Log _log = LogFactoryUtil.getLog(
		AggregateFilter.class);

	private static final Pattern _pattern = Pattern.compile(
		"^(\\.ie|\\.js\\.ie)([^}]*)}", Pattern.MULTILINE);

	private ServletContext _servletContext;
	private File _tempDir;

}