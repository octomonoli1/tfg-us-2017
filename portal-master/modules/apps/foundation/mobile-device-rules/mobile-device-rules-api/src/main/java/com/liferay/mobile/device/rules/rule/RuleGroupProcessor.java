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

package com.liferay.mobile.device.rules.rule;

import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Collection;

/**
 * @author Edward Han
 */
public interface RuleGroupProcessor {

	public MDRRuleGroupInstance evaluateRuleGroups(ThemeDisplay themeDisplay);

	public RuleHandler getRuleHandler(String ruleType);

	public Collection<RuleHandler> getRuleHandlers();

	public Collection<String> getRuleHandlerTypes();

	public void registerRuleHandler(RuleHandler ruleHandler);

	public RuleHandler unregisterRuleHandler(String ruleType);

}