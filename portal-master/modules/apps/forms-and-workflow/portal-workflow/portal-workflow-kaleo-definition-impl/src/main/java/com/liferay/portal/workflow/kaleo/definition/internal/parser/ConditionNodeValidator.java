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

package com.liferay.portal.workflow.kaleo.definition.internal.parser;

import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.kaleo.definition.Condition;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.parser.NodeValidator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 * @author Marcellus Tavares
 */
@Component(
	immediate = true, property = {"node.type=CONDITION"},
	service = NodeValidator.class
)
public class ConditionNodeValidator extends BaseNodeValidator<Condition> {

	@Override
	protected void doValidate(Definition definition, Condition condition)
		throws WorkflowException {

		if (condition.getIncomingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No incoming transition found for condition " +
					condition.getName());
		}

		if (condition.getOutgoingTransitionsCount() < 2) {
			throw new WorkflowException(
				"Less than 2 outgoing transitions found for condition " +
					condition.getName());
		}
	}

}