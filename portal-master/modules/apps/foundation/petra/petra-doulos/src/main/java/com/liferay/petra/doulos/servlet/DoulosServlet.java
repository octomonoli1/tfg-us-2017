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

package com.liferay.petra.doulos.servlet;

import com.liferay.petra.doulos.processor.DoulosRequestProcessor;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class DoulosServlet extends HttpServlet {

	@Override
	public void destroy() {
		for (Map.Entry<String, DoulosRequestProcessor> entry :
				_doulosRequestProcessors.entrySet()) {

			DoulosRequestProcessor doulosRequestProcessor = entry.getValue();

			try {
				doulosRequestProcessor.destroy();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		super.destroy();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		String remoteAddr = request.getRemoteAddr();

		if (_log.isInfoEnabled()) {
			_log.info("Remote address: " + remoteAddr);
		}

		if (!isValidIP(remoteAddr)) {
			sendError(response, "IP " + remoteAddr + " is invalid.");

			return;
		}

		String pathInfo = request.getPathInfo();

		if (pathInfo.length() > 1) {
			if (pathInfo.endsWith("/")) {
				pathInfo = pathInfo.substring(0, pathInfo.length() - 1);
			}
		}

		for (Map.Entry<String, DoulosRequestProcessor> entry :
				_doulosRequestProcessors.entrySet()) {

			String doulosRequestProcessorKey = entry.getKey();

			if (!pathInfo.startsWith(doulosRequestProcessorKey)) {
				continue;
			}

			DoulosRequestProcessor doulosRequestProcessor = entry.getValue();

			if (_log.isInfoEnabled()) {
				_log.info(
					"Processing " + request.getRequestURL() + " with " +
						doulosRequestProcessor);
			}

			try {
				String payload = request.getParameter("payload");

				if (_log.isInfoEnabled()) {
					_log.info("Payload parameter: " + payload);
				}

				if (payload == null) {
					StringBuilder sb = new StringBuilder();

					String line = null;

					BufferedReader bufferedReader = request.getReader();

					while ((line = bufferedReader.readLine()) != null) {
						sb.append(line);
					}

					bufferedReader.close();

					payload = sb.toString();

					if (_log.isInfoEnabled()) {
						_log.info("Payload body: " + payload);
					}
				}

				JSONObject payloadJSONObject = null;

				if (payload.length() > 0) {
					payloadJSONObject = new JSONObject(payload);
				}
				else {
					payloadJSONObject = new JSONObject();
				}

				JSONObject responseJSONObject = new JSONObject();

				doulosRequestProcessor.process(
					request.getMethod(),
					pathInfo.substring(doulosRequestProcessorKey.length()),
					request.getParameterMap(), payloadJSONObject,
					responseJSONObject);

				String redirect = responseJSONObject.optString(
					"doulosRedirect");

				if (redirect != null) {
					response.sendRedirect(redirect);

					return;
				}

				String json = responseJSONObject.toString();

				write(
					response, new ByteArrayInputStream(json.getBytes("UTF-8")));
			}
			catch (Exception e) {
				StringWriter stringWriter = new StringWriter();

				PrintWriter printWriter = new PrintWriter(stringWriter);

				e.printStackTrace(printWriter);

				String output = stringWriter.toString();

				if (_log.isInfoEnabled()) {
					_log.info(output);
				}

				sendError(response, output);
			}

			return;
		}

		sendError(response, "Unregistered path " + request.getPathInfo() + ".");
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		try {
			registerDoulosRequestProcessors();
		}
		catch (Exception e) {
			throw new ServletException(e);
		}

		String validIpsString = servletConfig.getInitParameter("validIps");

		if (validIpsString != null) {
			_validIps = validIpsString.split(",");
		}
		else {
			_validIps = new String[0];
		}
	}

	protected boolean isValidIP(String remoteAddr) {
		if (_validIps.length == 0) {
			return true;
		}

		for (String validIp : _validIps) {
			if (remoteAddr.equals(validIp) ||
				remoteAddr.startsWith(validIp + ".")) {

				return true;
			}
		}

		return false;
	}

	protected void registerDoulosRequestProcessor(
		String doulosRequestProcessorKey,
		DoulosRequestProcessor doulosRequestProcessor) {

		_doulosRequestProcessors.put(
			doulosRequestProcessorKey, doulosRequestProcessor);
	}

	protected abstract void registerDoulosRequestProcessors() throws Exception;

	protected void sendError(HttpServletResponse response, String message)
		throws IOException {

		write(response, new ByteArrayInputStream(message.getBytes()));
	}

	protected void write(HttpServletResponse response, InputStream inputStream)
		throws IOException {

		OutputStream outputStream = null;

		try {
			response.setHeader("Cache-Control", "public");

			if (!response.isCommitted()) {
				outputStream = new BufferedOutputStream(
					response.getOutputStream());

				int c = inputStream.read();

				while (c != -1) {
					outputStream.write(c);

					c = inputStream.read();
				}
			}
		}
		finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}

			try {
				if (outputStream != null) {
					outputStream.close();
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}

			try {
				if (inputStream != null) {
					inputStream.close();
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}
		}
	}

	private static final Log _log = LogFactory.getLog(DoulosServlet.class);

	private final Map<String, DoulosRequestProcessor> _doulosRequestProcessors =
		new HashMap<>();
	private String[] _validIps;

}