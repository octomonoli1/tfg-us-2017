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

package com.liferay.portal.workflow.kaleo.runtime.scripting.internal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.workflow.WorkflowTaskAssignee;
import com.liferay.portal.workflow.kaleo.KaleoWorkflowModelConverter;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.util.ScriptingContextBuilder;
import com.liferay.portal.workflow.kaleo.runtime.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = ScriptingContextBuilder.class)
public class ScriptingContextBuilderImpl implements ScriptingContextBuilder {

	@Override
	public Map<String, Object> buildScriptingContext(
			ExecutionContext executionContext)
		throws PortalException {

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();

		if (workflowContext == null) {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

			workflowContext = WorkflowContextUtil.convert(
				kaleoInstance.getWorkflowContext());
		}

		Map<String, Object> inputObjects = new HashMap<>();

		inputObjects.putAll(workflowContext);
		inputObjects.put(
			"kaleoInstanceToken", executionContext.getKaleoInstanceToken());
		inputObjects.put("workflowContext", workflowContext);

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			executionContext.getKaleoTaskInstanceToken();

		if (kaleoTaskInstanceToken != null) {
			inputObjects.put("kaleoTaskInstanceToken", kaleoTaskInstanceToken);

			KaleoTask kaleoTask = kaleoTaskInstanceToken.getKaleoTask();

			inputObjects.put("taskName", kaleoTask.getName());

			if (kaleoTaskInstanceToken.getCompletionUserId() != 0) {
				inputObjects.put(
					"userId", kaleoTaskInstanceToken.getCompletionUserId());
			}
			else {
				inputObjects.put("userId", kaleoTaskInstanceToken.getUserId());
			}

			List<WorkflowTaskAssignee> workflowTaskAssignees =
				_kaleoWorkflowModelConverter.getWorkflowTaskAssignees(
					kaleoTaskInstanceToken);

			inputObjects.put("workflowTaskAssignees", workflowTaskAssignees);
		}
		else {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			inputObjects.put("userId", kaleoInstanceToken.getUserId());
		}

		if (executionContext.getKaleoTimerInstanceToken() != null) {
			inputObjects.put(
				"kaleoTimerInstanceToken",
				executionContext.getKaleoTimerInstanceToken());
		}

		return inputObjects;
	}

	@Reference
	private KaleoWorkflowModelConverter _kaleoWorkflowModelConverter;

}