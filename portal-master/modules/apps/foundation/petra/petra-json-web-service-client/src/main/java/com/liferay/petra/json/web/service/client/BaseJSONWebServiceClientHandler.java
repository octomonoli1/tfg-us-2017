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

package com.liferay.petra.json.web.service.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Igor Beslic
 */
public abstract class BaseJSONWebServiceClientHandler {

	public abstract JSONWebServiceClient getJSONWebServiceClient();

	protected BaseJSONWebServiceClientHandler() {
		objectMapper.configure(
			DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		objectMapper.enableDefaultTypingAsProperty(
			ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, "class");
	}

	protected String doDelete(
		String url, Map<String, String> parameters,
		Map<String, String> headers) {

		JSONWebServiceClient jsonWebServiceClient = getJSONWebServiceClient();

		return jsonWebServiceClient.doDelete(url, parameters, headers);
	}

	protected String doDelete(String url, String... parametersArray) {
		JSONWebServiceClient jsonWebServiceClient = getJSONWebServiceClient();

		Map<String, String> parameters = new HashMap<>();

		for (int i = 0; i < parametersArray.length; i += 2) {
			parameters.put(parametersArray[i], parametersArray[i + 1]);
		}

		return jsonWebServiceClient.doDelete(url, parameters);
	}

	protected String doGet(
		String url, Map<String, String> parameters,
		Map<String, String> headers) {

		JSONWebServiceClient jsonWebServiceClient = getJSONWebServiceClient();

		return jsonWebServiceClient.doGet(url, parameters, headers);
	}

	protected String doGet(String url, String... parametersArray) {
		JSONWebServiceClient jsonWebServiceClient = getJSONWebServiceClient();

		Map<String, String> parameters = new HashMap<>();

		for (int i = 0; i < parametersArray.length; i += 2) {
			parameters.put(parametersArray[i], parametersArray[i + 1]);
		}

		return jsonWebServiceClient.doGet(url, parameters);
	}

	protected <T> List<T> doGetToList(
			Class<T> clazz, String url, Map<String, String> parameters,
			Map<String, String> headers)
		throws JSONWebServiceInvocationException {

		String json = doGet(url, parameters, headers);

		if ((json == null) || json.equals("") || json.equals("{}") ||
			json.equals("[]")) {

			return Collections.emptyList();
		}

		if (json.contains("exception\":\"")) {
			throw new JSONWebServiceInvocationException(
				getExceptionMessage(json), getStatus(json));
		}

		try {
			TypeFactory typeFactory = objectMapper.getTypeFactory();

			JavaType javaType = typeFactory.constructCollectionType(
				List.class, clazz);

			return objectMapper.readValue(json, javaType);
		}
		catch (IOException ioe) {
			throw new JSONWebServiceInvocationException(ioe);
		}
	}

	protected <T> List<T> doGetToList(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException {

		Map<String, String> parameters = new HashMap<>();

		for (int i = 0; i < parametersArray.length; i += 2) {
			parameters.put(parametersArray[i], parametersArray[i + 1]);
		}

		return doGetToList(
			clazz, url, parameters, Collections.<String, String>emptyMap());
	}

	protected <T> T doGetToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException {

		String json = doGet(url, parametersArray);

		if ((json == null) || json.equals("") || json.equals("{}")) {
			return null;
		}

		if (json.contains("exception\":\"")) {
			throw new JSONWebServiceInvocationException(
				getExceptionMessage(json), getStatus(json));
		}

		try {
			return objectMapper.readValue(json, clazz);
		}
		catch (IOException ioe) {
			throw new JSONWebServiceInvocationException(ioe);
		}
	}

	protected String doPost(
		String url, Map<String, String> parameters,
		Map<String, String> headers) {

		JSONWebServiceClient jsonWebServiceClient = getJSONWebServiceClient();

		return jsonWebServiceClient.doPost(url, parameters, headers);
	}

	protected String doPost(String url, String... parametersArray) {
		JSONWebServiceClient jsonWebServiceClient = getJSONWebServiceClient();

		Map<String, String> parameters = new HashMap<>();

		for (int i = 0; i < parametersArray.length; i += 2) {
			parameters.put(parametersArray[i], parametersArray[i + 1]);
		}

		return jsonWebServiceClient.doPost(url, parameters);
	}

	protected String doPostAsJSON(String url, Object object)
		throws JSONWebServiceInvocationException {

		try {
			JSONWebServiceClient jsonWebServiceClient =
				getJSONWebServiceClient();

			String json = objectMapper.writeValueAsString(object);

			return jsonWebServiceClient.doPostAsJSON(url, json);
		}
		catch (IOException ioe) {
			throw new JSONWebServiceInvocationException(ioe);
		}
	}

	protected <T> T doPostToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException {

		String json = doPost(url, parametersArray);

		if ((json == null) || json.equals("") || json.equals("{}")) {
			return null;
		}

		if (json.contains("exception\":\"")) {
			throw new JSONWebServiceInvocationException(
				getExceptionMessage(json), getStatus(json));
		}

		try {
			return objectMapper.readValue(json, clazz);
		}
		catch (IOException ioe) {
			throw new JSONWebServiceInvocationException(ioe);
		}
	}

	protected String doPut(
		String url, Map<String, String> parameters,
		Map<String, String> headers) {

		JSONWebServiceClient jsonWebServiceClient = getJSONWebServiceClient();

		return jsonWebServiceClient.doPut(url, parameters, headers);
	}

	protected String doPut(String url, String... parametersArray) {
		JSONWebServiceClient jsonWebServiceClient = getJSONWebServiceClient();

		Map<String, String> parameters = new HashMap<>();

		for (int i = 0; i < parametersArray.length; i += 2) {
			parameters.put(parametersArray[i], parametersArray[i + 1]);
		}

		return jsonWebServiceClient.doPut(url, parameters);
	}

	protected String getExceptionMessage(String json) {
		int exceptionMessageStart = json.indexOf("exception\":\"") + 12;

		int exceptionMessageEnd = json.indexOf("\"", exceptionMessageStart);

		return json.substring(exceptionMessageStart, exceptionMessageEnd);
	}

	protected int getStatus(String json) {
		Matcher statusMatcher = _statusPattern.matcher(json);

		if (!statusMatcher.find()) {
			return 0;
		}

		return Integer.parseInt(statusMatcher.group(1));
	}

	protected ObjectMapper objectMapper = new ObjectMapper();

	private final Pattern _statusPattern = Pattern.compile("status\":(\\d+)");

}