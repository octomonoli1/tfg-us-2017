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

package com.liferay.portal.search.elasticsearch.internal.index;

import com.liferay.portal.kernel.test.IdempotentRetryAssert;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.admin.indices.mapping.get.GetFieldMappingsRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.get.GetFieldMappingsResponse;
import org.elasticsearch.action.admin.indices.mapping.get.GetFieldMappingsResponse.FieldMappingMetaData;
import org.elasticsearch.client.IndicesAdminClient;

import org.junit.Assert;

/**
 * @author Artur Aquino
 * @author André de Oliveira
 */
public class FieldMappingAssert {

	public static void assertAnalyzer(
			final String analyzer, final String field, final String type,
			final String index, final IndicesAdminClient indicesAdminClient)
		throws Exception {

		IdempotentRetryAssert.retryAssert(
			10, TimeUnit.SECONDS,
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					FieldMappingMetaData fieldMappingMetaData = getFieldMapping(
						field, type, index, indicesAdminClient);

					Assert.assertEquals(
						analyzer, getAnalyzer(fieldMappingMetaData, field));

					return null;
				}

			});
	}

	@SuppressWarnings("unchecked")
	public static String getAnalyzer(
		FieldMappingMetaData fieldMappingMetaData, String field) {

		Map<String, Object> mappings = fieldMappingMetaData.sourceAsMap();

		Map<String, Object> mapping = (Map<String, Object>)mappings.get(field);

		return (String)mapping.get("analyzer");
	}

	public static FieldMappingMetaData getFieldMapping(
		String field, String type, String index,
		IndicesAdminClient indicesAdminClient) {

		GetFieldMappingsRequestBuilder getFieldMappingsRequestBuilder =
			indicesAdminClient.prepareGetFieldMappings(index);

		getFieldMappingsRequestBuilder.setFields(field);
		getFieldMappingsRequestBuilder.setTypes(type);

		GetFieldMappingsResponse getFieldMappingsResponse =
			getFieldMappingsRequestBuilder.get();

		return getFieldMappingsResponse.fieldMappings(index, type, field);
	}

}