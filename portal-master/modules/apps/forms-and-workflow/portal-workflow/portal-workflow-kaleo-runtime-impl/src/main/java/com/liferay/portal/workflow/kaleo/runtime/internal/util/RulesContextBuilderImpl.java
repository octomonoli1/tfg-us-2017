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

package com.liferay.portal.workflow.kaleo.runtime.internal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.workflow.WorkflowTaskAssignee;
import com.liferay.portal.rules.engine.Fact;
import com.liferay.portal.workflow.kaleo.KaleoWorkflowModelConverter;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.util.RulesContextBuilder;
import com.liferay.portal.workflow.kaleo.runtime.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = RulesContextBuilder.class)
public class RulesContextBuilderImpl implements RulesContextBuilder {

	public List<Fact<?>> buildRulesContext(ExecutionContext executionContext)
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

		List<Fact<?>> facts = new ArrayList<>(workflowContext.size() + 4);

		facts.add(
			new Fact<KaleoInstanceToken>(
				"kaleoInstanceToken",
				executionContext.getKaleoInstanceToken()));
		facts.add(
			new Fact<Map<String, Serializable>>(
				"workflowContext", workflowContext));

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			executionContext.getKaleoTaskInstanceToken();

		if (kaleoTaskInstanceToken != null) {
			facts.add(
				new Fact<KaleoTaskInstanceToken>(
					"kaleoTaskInstanceToken", kaleoTaskInstanceToken));

			KaleoTask kaleoTask = kaleoTaskInstanceToken.getKaleoTask();

			facts.add(new Fact<String>("taskName", kaleoTask.getName()));

			if (kaleoTaskInstanceToken.getCompletionUserId() != 0) {
				facts.add(
					new Fact<Long>(
						"userId",
						kaleoTaskInstanceToken.getCompletionUserId()));
			}
			else {
				facts.add(
					new Fact<Long>(
						"userId", kaleoTaskInstanceToken.getUserId()));
			}

			List<WorkflowTaskAssignee> workflowTaskAssignees =
				_kaleoWorkflowModelConverter.getWorkflowTaskAssignees(
					kaleoTaskInstanceToken);

			facts.add(
				new Fact<List<WorkflowTaskAssignee>>(
					"workflowTaskAssignees", workflowTaskAssignees));
		}
		else {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			facts.add(new Fact<Long>("userId", kaleoInstanceToken.getUserId()));
		}

		if (executionContext.getKaleoTimerInstanceToken() != null) {
			facts.add(
				new Fact<KaleoTimerInstanceToken>(
					"kaleoTimerInstanceToken",
					executionContext.getKaleoTimerInstanceToken()));
		}

		return facts;
	}

	@Reference
	private KaleoWorkflowModelConverter _kaleoWorkflowModelConverter;

}