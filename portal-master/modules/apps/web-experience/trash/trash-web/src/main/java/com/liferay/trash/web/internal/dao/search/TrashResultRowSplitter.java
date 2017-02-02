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

package com.liferay.trash.web.internal.dao.search;

import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.ResultRowSplitter;
import com.liferay.portal.kernel.dao.search.ResultRowSplitterEntry;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class TrashResultRowSplitter implements ResultRowSplitter {

	@Override
	public List<ResultRowSplitterEntry> split(List<ResultRow> resultRows) {
		List<ResultRowSplitterEntry> resultRowSplitterEntries =
			new ArrayList<>();

		List<ResultRow> trashContainedResultRows = new ArrayList<>();
		List<ResultRow> trashContainerResultRows = new ArrayList<>();

		String containerModelName = null;
		String containedModelName = null;

		for (ResultRow resultRow : resultRows) {
			TrashRenderer trashRenderer = (TrashRenderer)resultRow.getObject();

			TrashHandler trashHandler =
				TrashHandlerRegistryUtil.getTrashHandler(
					trashRenderer.getClassName());

			if (Validator.isNull(containerModelName) &&
				Validator.isNull(containedModelName)) {

				containerModelName = trashHandler.getTrashContainerModelName();
				containedModelName = trashHandler.getTrashContainedModelName();
			}

			if (trashHandler.isContainerModel()) {
				trashContainerResultRows.add(resultRow);
			}
			else {
				trashContainedResultRows.add(resultRow);
			}
		}

		if (!trashContainerResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry(
					containerModelName, trashContainerResultRows));
		}

		if (!trashContainedResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry(
					containedModelName, trashContainedResultRows));
		}

		return resultRowSplitterEntries;
	}

}