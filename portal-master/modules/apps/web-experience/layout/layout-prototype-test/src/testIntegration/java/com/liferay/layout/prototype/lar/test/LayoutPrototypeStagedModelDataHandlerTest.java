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

package com.liferay.layout.prototype.lar.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutFriendlyURL;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.LayoutFriendlyURLLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.rule.TransactionalTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.lar.test.BaseStagedModelDataHandlerTestCase;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.test.LayoutTestUtil;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Daniela Zapata Riesco
 */
@RunWith(Arquillian.class)
@Sync
public class LayoutPrototypeStagedModelDataHandlerTest
	extends BaseStagedModelDataHandlerTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE,
			TransactionalTestRule.INSTANCE);

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		if (_layoutPrototype != null) {
			_layoutPrototype =
				LayoutPrototypeLocalServiceUtil.
					fetchLayoutPrototypeByUuidAndCompanyId(
						_layoutPrototype.getUuid(),
						_layoutPrototype.getCompanyId());

			LayoutPrototypeLocalServiceUtil.deleteLayoutPrototype(
				_layoutPrototype);
		}
	}

	@Override
	protected StagedModel addStagedModel(
			Group group,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		_layoutPrototype = LayoutTestUtil.addLayoutPrototype(
			RandomTestUtil.randomString());

		Layout layout = _layoutPrototype.getLayout();

		UnicodeProperties typeSettings = layout.getTypeSettingsProperties();

		typeSettings.setProperty(
			LayoutPrototypeStagedModelDataHandlerTest.class.getName(),
			Boolean.TRUE.toString());

		LayoutLocalServiceUtil.updateLayout(layout);

		addDependentStagedModel(dependentStagedModelsMap, Layout.class, layout);

		List<LayoutFriendlyURL> layoutFriendlyURLs =
			LayoutFriendlyURLLocalServiceUtil.getLayoutFriendlyURLs(
				layout.getPlid());

		Assert.assertEquals(1, layoutFriendlyURLs.size());

		addDependentStagedModel(
			dependentStagedModelsMap, LayoutFriendlyURL.class,
			layoutFriendlyURLs.get(0));

		return _layoutPrototype;
	}

	@Override
	protected void deleteStagedModel(
			StagedModel stagedModel,
			Map<String, List<StagedModel>> dependentStagedModelsMap,
			Group group)
		throws Exception {

		LayoutPrototypeLocalServiceUtil.deleteLayoutPrototype(
			(LayoutPrototype)stagedModel);
	}

	@Override
	protected StagedModel getStagedModel(String uuid, Group group) {
		try {
			return LayoutPrototypeLocalServiceUtil.
				fetchLayoutPrototypeByUuidAndCompanyId(
					uuid, group.getCompanyId());
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	protected Class<? extends StagedModel> getStagedModelClass() {
		return LayoutPrototype.class;
	}

	@Override
	protected void validateImport(
			StagedModel stagedModel, StagedModelAssets stagedModelAssets,
			Map<String, List<StagedModel>> dependentStagedModelsMap,
			Group group)
		throws Exception {

		LayoutPrototype importedLayoutPrototype =
			(LayoutPrototype)getStagedModel(stagedModel.getUuid(), group);

		Assert.assertNotNull(importedLayoutPrototype);

		List<StagedModel> layoutDependentStagedModels =
			dependentStagedModelsMap.get(Layout.class.getSimpleName());

		Assert.assertEquals(1, layoutDependentStagedModels.size());

		Layout layout = (Layout)layoutDependentStagedModels.get(0);

		Layout importedLayout =
			LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(
				layout.getUuid(), importedLayoutPrototype.getGroupId(),
				layout.isPrivateLayout());

		Assert.assertNotNull(importedLayout);
		Assert.assertEquals(
			layout.getTypeSettingsProperty(
				LayoutPrototypeStagedModelDataHandlerTest.class.getName()),
			importedLayout.getTypeSettingsProperty(
				LayoutPrototypeStagedModelDataHandlerTest.class.getName()));

		List<StagedModel> layoutFriendlyURLDependentStagedModels =
			dependentStagedModelsMap.get(
				LayoutFriendlyURL.class.getSimpleName());

		LayoutFriendlyURL layoutFriendlyURL =
			(LayoutFriendlyURL)layoutFriendlyURLDependentStagedModels.get(0);

		LayoutFriendlyURL importedLayoutFriendlyURL =
			LayoutFriendlyURLLocalServiceUtil.
				fetchLayoutFriendlyURLByUuidAndGroupId(
					layoutFriendlyURL.getUuid(), importedLayout.getGroupId());

		Assert.assertNotNull(importedLayoutFriendlyURL);
		Assert.assertEquals(
			layoutFriendlyURL.getFriendlyURL(),
			importedLayoutFriendlyURL.getFriendlyURL());
	}

	@Override
	protected void validateImportedStagedModel(
			StagedModel stagedModel, StagedModel importedStagedModel)
		throws Exception {

		super.validateImportedStagedModel(stagedModel, importedStagedModel);

		LayoutPrototype layoutPrototype = (LayoutPrototype)stagedModel;
		LayoutPrototype importedLayoutPrototype =
			(LayoutPrototype)importedStagedModel;

		Assert.assertEquals(
			layoutPrototype.getName(), importedLayoutPrototype.getName());
		Assert.assertEquals(
			layoutPrototype.getDescription(),
			importedLayoutPrototype.getDescription());
		Assert.assertEquals(
			layoutPrototype.isActive(), importedLayoutPrototype.isActive());
	}

	private LayoutPrototype _layoutPrototype;

}