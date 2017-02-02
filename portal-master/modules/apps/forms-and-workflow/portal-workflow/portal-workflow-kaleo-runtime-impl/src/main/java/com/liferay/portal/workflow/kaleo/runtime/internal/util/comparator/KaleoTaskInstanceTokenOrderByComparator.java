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

package com.liferay.portal.workflow.kaleo.runtime.internal.util.comparator;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorAdapter;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.workflow.kaleo.KaleoWorkflowModelConverter;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.util.WorkflowContextUtil;

/**
 * @author William Newbury
 */
public class KaleoTaskInstanceTokenOrderByComparator
	extends OrderByComparatorAdapter<KaleoTaskInstanceToken, WorkflowTask> {

	public static OrderByComparator<KaleoTaskInstanceToken>
		getOrderByComparator(
			OrderByComparator<WorkflowTask> orderByComparator,
			KaleoWorkflowModelConverter kaleoWorkflowModelConverter) {

		if (orderByComparator == null) {
			return null;
		}

		return new KaleoTaskInstanceTokenOrderByComparator(
			orderByComparator, kaleoWorkflowModelConverter);
	}

	@Override
	public WorkflowTask adapt(KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		try {
			return _kaleoWorkflowModelConverter.toWorkflowTask(
				kaleoTaskInstanceToken,
				WorkflowContextUtil.convert(
					kaleoTaskInstanceToken.getWorkflowContext()));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	private KaleoTaskInstanceTokenOrderByComparator(
		OrderByComparator<WorkflowTask> orderByComparator,
		KaleoWorkflowModelConverter kaleoWorkflowModelConverter) {

		super(orderByComparator);

		_kaleoWorkflowModelConverter = kaleoWorkflowModelConverter;
	}

	private final KaleoWorkflowModelConverter _kaleoWorkflowModelConverter;

}