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

package com.liferay.portal.tools.data.partitioning.sql.builder.internal.command.impl;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.DBExporter;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.command.BaseExportProcessCommand;

import java.io.OutputStream;

import java.util.List;

/**
 * @author Manuel de la Peña
 */
public class InsertPartitionedExportProcessCommand
	extends BaseExportProcessCommand {

	public InsertPartitionedExportProcessCommand(
		long companyId, DBExporter dbExporter, List<String> tableNames,
		ExportContext exportContext) {

		super(companyId, dbExporter, tableNames, exportContext);
	}

	protected String getOutputFileName() {
		return exportContext.getSchemaName() + "-" + companyId +
			"-partitioned.sql";
	}

	protected String getOutputFileName(String tableName) {
		return exportContext.getSchemaName() + "-" + companyId + "-table-" +
			tableName + ".sql";
	}

	protected void write(String tableName, OutputStream outputStream) {
		dbExporter.write(companyId, tableName, outputStream);
	}

}