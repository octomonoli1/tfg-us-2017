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

package com.liferay.mobile.device.rules.lar.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalServiceUtil;
import com.liferay.mobile.device.rules.service.MDRRuleGroupLocalServiceUtil;
import com.liferay.mobile.device.rules.util.test.MDRTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.rule.TransactionalTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.lar.test.BaseStagedModelDataHandlerTestCase;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.test.LayoutTestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Mate Thurzo
 */
@RunWith(Arquillian.class)
@Sync
public class MDRRuleGroupInstanceStagedModelDataHandlerTest
	extends BaseStagedModelDataHandlerTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE,
			TransactionalTestRule.INSTANCE);

	@Override
	public void setUp() throws Exception {
		super.setUp();

		layout = LayoutTestUtil.addLayout(stagingGroup);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUuid(layout.getUuid());

		LayoutLocalServiceUtil.addLayout(
			TestPropsValues.getUserId(), liveGroup.getGroupId(),
			layout.getPrivateLayout(), layout.getParentLayoutId(),
			layout.getName(), layout.getTitle(), layout.getDescription(),
			layout.getType(), layout.getHidden(), layout.getFriendlyURL(),
			serviceContext);
	}

	@Override
	protected Map<String, List<StagedModel>> addDependentStagedModelsMap(
			Group group)
		throws Exception {

		Map<String, List<StagedModel>> dependentStagedModelsMap =
			new HashMap<>();

		MDRRuleGroup ruleGroup = MDRTestUtil.addRuleGroup(group.getGroupId());

		addDependentStagedModel(
			dependentStagedModelsMap, MDRRuleGroup.class, ruleGroup);

		return dependentStagedModelsMap;
	}

	@Override
	protected StagedModel addStagedModel(
			Group group,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		List<StagedModel> dependentStagedModels = dependentStagedModelsMap.get(
			MDRRuleGroup.class.getSimpleName());

		MDRRuleGroup ruleGroup = (MDRRuleGroup)dependentStagedModels.get(0);

		return MDRTestUtil.addRuleGroupInstance(
			group.getGroupId(), Layout.class.getName(), layout.getPlid(),
			ruleGroup.getRuleGroupId());
	}

	@Override
	protected StagedModel getStagedModel(String uuid, Group group) {
		try {
			return MDRRuleGroupInstanceLocalServiceUtil.
				getMDRRuleGroupInstanceByUuidAndGroupId(
					uuid, group.getGroupId());
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	protected Class<? extends StagedModel> getStagedModelClass() {
		return MDRRuleGroupInstance.class;
	}

	@Override
	protected void validateImport(
			Map<String, List<StagedModel>> dependentStagedModelsMap,
			Group group)
		throws Exception {

		List<StagedModel> dependentStagedModels = dependentStagedModelsMap.get(
			MDRRuleGroup.class.getSimpleName());

		Assert.assertEquals(1, dependentStagedModels.size());

		MDRRuleGroup ruleGroup = (MDRRuleGroup)dependentStagedModels.get(0);

		MDRRuleGroupLocalServiceUtil.getMDRRuleGroupByUuidAndGroupId(
			ruleGroup.getUuid(), group.getGroupId());
	}

	@Override
	protected void validateImportedStagedModel(
			StagedModel stagedModel, StagedModel importedStagedModel)
		throws Exception {

		super.validateImportedStagedModel(stagedModel, importedStagedModel);

		MDRRuleGroupInstance ruleGroupInstance =
			(MDRRuleGroupInstance)stagedModel;
		MDRRuleGroupInstance importedRuleGroupInstance =
			(MDRRuleGroupInstance)importedStagedModel;

		Assert.assertEquals(
			ruleGroupInstance.getPriority(),
			importedRuleGroupInstance.getPriority());
	}

	protected Layout layout;

}