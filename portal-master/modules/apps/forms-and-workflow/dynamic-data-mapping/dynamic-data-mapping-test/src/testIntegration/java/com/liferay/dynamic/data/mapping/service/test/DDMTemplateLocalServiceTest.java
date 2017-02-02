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

package com.liferay.dynamic.data.mapping.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.exception.TemplateDuplicateTemplateKeyException;
import com.liferay.dynamic.data.mapping.exception.TemplateNameException;
import com.liferay.dynamic.data.mapping.exception.TemplateScriptException;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.dynamic.data.mapping.util.comparator.TemplateIdComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eduardo Garcia
 */
@RunWith(Arquillian.class)
@Sync
public class DDMTemplateLocalServiceTest extends BaseDDMServiceTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@BeforeClass
	public static void setUpClass() {
		_classNameId = PortalUtil.getClassNameId(DDL_RECORD_CLASS_NAME);
		_resourceClassNameId = PortalUtil.getClassNameId(
			DDL_RECORD_SET_CLASS_NAME);
	}

	@Test
	public void testAddTemplateWithDuplicateKey() throws Exception {
		String templateKey = RandomTestUtil.randomString();
		String language = TemplateConstants.LANG_TYPE_VM;

		try {
			addTemplate(
				_classNameId, 0, templateKey, "Test Template 1",
				DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
				DDMTemplateConstants.TEMPLATE_MODE_CREATE, language,
				getTestTemplateScript(language),
				WorkflowConstants.STATUS_APPROVED);
			addTemplate(
				_classNameId, 0, templateKey, "Test Template 2",
				DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
				DDMTemplateConstants.TEMPLATE_MODE_CREATE, language,
				getTestTemplateScript(language),
				WorkflowConstants.STATUS_APPROVED);

			Assert.fail();
		}
		catch (TemplateDuplicateTemplateKeyException tdtke) {
		}
	}

	@Test
	public void testAddTemplateWithoutName() throws Exception {
		String language = TemplateConstants.LANG_TYPE_VM;

		try {
			addTemplate(
				_classNameId, 0, null, StringPool.BLANK,
				DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
				DDMTemplateConstants.TEMPLATE_MODE_CREATE, language,
				getTestTemplateScript(language),
				WorkflowConstants.STATUS_APPROVED);

			Assert.fail();
		}
		catch (TemplateNameException tne) {
		}
	}

	@Test
	public void testAddTemplateWithoutScript() throws Exception {
		try {
			addTemplate(
				_classNameId, 0, null, "Test Template",
				DDMTemplateConstants.TEMPLATE_TYPE_FORM,
				DDMTemplateConstants.TEMPLATE_MODE_CREATE,
				TemplateConstants.LANG_TYPE_VM, StringPool.BLANK,
				WorkflowConstants.STATUS_APPROVED);

			Assert.fail();
		}
		catch (TemplateScriptException tse) {
		}
	}

	@Test
	public void testCopyTemplates() throws Exception {
		int initialCount = DDMTemplateLocalServiceUtil.getTemplatesCount(
			group.getGroupId(), _classNameId, 0);

		DDMTemplate template = addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Test Template",
			"Test Template", WorkflowConstants.STATUS_APPROVED);

		copyTemplate(template);

		int count = DDMTemplateLocalServiceUtil.getTemplatesCount(
			group.getGroupId(), _classNameId, 0);

		Assert.assertEquals(initialCount + 2, count);
	}

	@Test
	public void testDeleteTemplate() throws Exception {
		DDMTemplate template = addDisplayTemplate(
			_classNameId, 0, "Test Template",
			WorkflowConstants.STATUS_APPROVED);

		DDMTemplateLocalServiceUtil.deleteTemplate(template.getTemplateId());

		Assert.assertNull(
			DDMTemplateLocalServiceUtil.fetchDDMTemplate(
				template.getTemplateId()));
	}

	@Test
	public void testFetchTemplate() throws Exception {
		DDMTemplate template = addDisplayTemplate(
			_classNameId, _resourceClassNameId, "Test Template",
			WorkflowConstants.STATUS_APPROVED);

		Assert.assertNotNull(
			DDMTemplateLocalServiceUtil.fetchTemplate(
				template.getGroupId(), _classNameId,
				template.getTemplateKey()));
	}

	@Test
	public void testGetTemplates() throws Exception {
		DDMTemplate template = addDisplayTemplate(
			_classNameId, _resourceClassNameId, "Test Template",
			WorkflowConstants.STATUS_APPROVED);

		List<DDMTemplate> templates = DDMTemplateLocalServiceUtil.getTemplates(
			template.getGroupId(), template.getClassNameId());

		Assert.assertTrue(templates.contains(template));
	}

	@Test
	public void testSearchByAnyStatus() throws Exception {
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Event", null,
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Contact", null,
			WorkflowConstants.STATUS_DRAFT);

		List<DDMTemplate> templates = DDMTemplateLocalServiceUtil.search(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			null, null, _resourceClassNameId, null, null, null, null, null,
			WorkflowConstants.STATUS_ANY, true, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		Assert.assertEquals(2, templates.size());
	}

	@Test
	public void testSearchByDescription() throws Exception {
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Event", "Event",
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Contact", "Contact",
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Meeting", "Meeting",
			WorkflowConstants.STATUS_APPROVED);

		List<DDMTemplate> templates = DDMTemplateLocalServiceUtil.search(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			null, null, _resourceClassNameId, null, "Meeting", null, null, null,
			WorkflowConstants.STATUS_APPROVED, true, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		Assert.assertEquals(1, templates.size());

		DDMTemplate template = templates.get(0);

		Assert.assertEquals(
			"Meeting", template.getDescription(group.getDefaultLanguageId()));
	}

	@Test
	public void testSearchByDraftStatus() throws Exception {
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Event", null,
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Contact", null,
			WorkflowConstants.STATUS_DRAFT);

		List<DDMTemplate> templates = DDMTemplateLocalServiceUtil.search(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			null, null, _resourceClassNameId, null, null, null, null, null,
			WorkflowConstants.STATUS_DRAFT, true, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		Assert.assertEquals(1, templates.size());
	}

	@Test
	public void testSearchByKeywords() throws Exception {
		addDisplayTemplate(
			_classNameId, _resourceClassNameId, "Event Template",
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			_classNameId, _resourceClassNameId, "Contact Template",
			WorkflowConstants.STATUS_APPROVED);

		List<DDMTemplate> templates = DDMTemplateLocalServiceUtil.search(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			null, null, _resourceClassNameId, "Event", null, null,
			WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new TemplateIdComparator(true));

		Assert.assertEquals(1, templates.size());
		Assert.assertEquals(
			"Event Template", getTemplateName(templates.get(0)));

		templates = DDMTemplateLocalServiceUtil.search(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			null, null, _resourceClassNameId, "Template", null, null,
			WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new TemplateIdComparator(true));

		Assert.assertEquals(
			"Event Template", getTemplateName(templates.get(0)));
		Assert.assertEquals(
			"Contact Template", getTemplateName(templates.get(1)));
	}

	@Test
	public void testSearchByName() throws Exception {
		addDisplayTemplate(
			_classNameId, _resourceClassNameId, "Event",
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			_classNameId, _resourceClassNameId, "Contact",
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			_classNameId, _resourceClassNameId, "Meeting",
			WorkflowConstants.STATUS_APPROVED);

		List<DDMTemplate> templates = DDMTemplateLocalServiceUtil.search(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			null, null, _resourceClassNameId, "Event", null, null, null, null,
			WorkflowConstants.STATUS_APPROVED, true, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		Assert.assertEquals(1, templates.size());
		Assert.assertEquals("Event", getTemplateName(templates.get(0)));
	}

	@Test
	public void testSearchByNameAndDescription() throws Exception {
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Event", "Event",
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Contact", "Contact",
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Meeting", "Meeting",
			WorkflowConstants.STATUS_APPROVED);

		List<DDMTemplate> templates = DDMTemplateLocalServiceUtil.search(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			null, null, 0, "Event", "Meeting", null, null, null,
			WorkflowConstants.STATUS_APPROVED, true, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		Assert.assertEquals(0, templates.size());
	}

	@Test
	public void testSearchByNameOrDescription() throws Exception {
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Event", "Event",
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Contact", "Contact",
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Meeting", "Meeting",
			WorkflowConstants.STATUS_APPROVED);

		List<DDMTemplate> templates = DDMTemplateLocalServiceUtil.search(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			null, null, _resourceClassNameId, "Event", "Meeting", null, null,
			null, WorkflowConstants.STATUS_APPROVED, false, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new TemplateIdComparator(true));

		Assert.assertEquals("Event", getTemplateName(templates.get(0)));
		Assert.assertEquals("Meeting", getTemplateName(templates.get(1)));
	}

	@Test
	public void testSearchCount() throws Exception {
		int initialCount = DDMTemplateLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), group.getGroupId(), _classNameId, 0,
			0, "Test Template", null, null, null, null,
			WorkflowConstants.STATUS_APPROVED, false);

		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Test Template",
			"Test Template", WorkflowConstants.STATUS_APPROVED);

		int count = DDMTemplateLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), group.getGroupId(), _classNameId, 0,
			_resourceClassNameId, "Test Template", null, null, null, null,
			WorkflowConstants.STATUS_APPROVED, false);

		Assert.assertEquals(initialCount + 1, count);
	}

	@Test
	public void testSearchCountByClassNameIdAndClassPK() throws Exception {
		long classNameId1 = RandomTestUtil.randomLong();
		long classPK1 = RandomTestUtil.randomLong();

		addDisplayTemplate(
			classNameId1, classPK1, _resourceClassNameId,
			StringUtil.randomString(), StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			classNameId1, RandomTestUtil.randomLong(), _resourceClassNameId,
			StringUtil.randomString(), StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);

		long classNameId2 = RandomTestUtil.randomLong();

		addDisplayTemplate(
			classNameId2, RandomTestUtil.randomLong(), _resourceClassNameId,
			StringUtil.randomString(), StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);

		int count = DDMTemplateLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			new long[] {classNameId1}, new long[] {classPK1},
			_resourceClassNameId, null, null, null,
			WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(1, count);

		count = DDMTemplateLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			new long[] {classNameId2}, new long[] {classPK1},
			_resourceClassNameId, null, null, null,
			WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(0, count);
	}

	@Test
	public void testSearchCountByClassNameIds() throws Exception {
		long classNameId1 = RandomTestUtil.randomLong();

		addDisplayTemplate(
			classNameId1, _resourceClassNameId, StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			classNameId1, _resourceClassNameId, StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			classNameId1, _resourceClassNameId, StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);

		long classNameId2 = RandomTestUtil.randomLong();

		addDisplayTemplate(
			classNameId2, _resourceClassNameId, StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			classNameId2, _resourceClassNameId, StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);

		int count = DDMTemplateLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			new long[] {classNameId1}, null, _resourceClassNameId, null, null,
			null, WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(3, count);

		count = DDMTemplateLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			new long[] {classNameId1, classNameId2}, null, _resourceClassNameId,
			null, null, null, WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(5, count);
	}

	@Test
	public void testSearchCountByClassPKs() throws Exception {
		long classPK1 = RandomTestUtil.randomLong();

		addDisplayTemplate(
			_classNameId, classPK1, _resourceClassNameId,
			StringUtil.randomString(), StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);

		long classPK2 = RandomTestUtil.randomLong();

		addDisplayTemplate(
			_classNameId, classPK2, _resourceClassNameId,
			StringUtil.randomString(), StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);

		long classPK3 = RandomTestUtil.randomLong();

		addDisplayTemplate(
			_classNameId, classPK3, _resourceClassNameId,
			StringUtil.randomString(), StringUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);

		int count = DDMTemplateLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			null, new long[] {classPK1, classPK2, classPK3},
			_resourceClassNameId, null, null, null,
			WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(3, count);
	}

	@Test
	public void testSearchCountByKeywords() throws Exception {
		int initialCount = DDMTemplateLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), group.getGroupId(), _classNameId, 0,
			0, null, null, null, WorkflowConstants.STATUS_APPROVED);

		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, "Test Template",
			"Test Template", WorkflowConstants.STATUS_APPROVED);

		int count = DDMTemplateLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), group.getGroupId(), _classNameId, 0,
			_resourceClassNameId, "Test", null, null,
			WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(initialCount + 1, count);
	}

	@Test
	public void testSearchCountByLanguage() throws Exception {
		String velocityLanguage = TemplateConstants.LANG_TYPE_VM;

		addTemplate(
			RandomTestUtil.randomLong(), RandomTestUtil.randomLong(),
			StringUtil.randomString(), StringPool.BLANK, StringPool.BLANK,
			velocityLanguage, getTestTemplateScript(velocityLanguage),
			WorkflowConstants.STATUS_APPROVED);
		addTemplate(
			RandomTestUtil.randomLong(), RandomTestUtil.randomLong(),
			StringUtil.randomString(), StringPool.BLANK, StringPool.BLANK,
			velocityLanguage, getTestTemplateScript(velocityLanguage),
			WorkflowConstants.STATUS_APPROVED);

		String freeMarkerLanguage = TemplateConstants.LANG_TYPE_FTL;

		addTemplate(
			RandomTestUtil.randomLong(), RandomTestUtil.randomLong(),
			StringUtil.randomString(), StringPool.BLANK, StringPool.BLANK,
			freeMarkerLanguage, getTestTemplateScript(freeMarkerLanguage),
			WorkflowConstants.STATUS_APPROVED);

		int count = DDMTemplateLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			null, null, _resourceClassNameId, null, null, null, null,
			TemplateConstants.LANG_TYPE_VM, WorkflowConstants.STATUS_APPROVED,
			true);

		Assert.assertEquals(2, count);
	}

	@Test
	public void testSearchCountByResourceClassNameId() throws Exception {
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, StringUtil.randomString(),
			StringUtil.randomString(), WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, StringUtil.randomString(),
			StringUtil.randomString(), WorkflowConstants.STATUS_APPROVED);
		addDisplayTemplate(
			_classNameId, 0, _resourceClassNameId, StringUtil.randomString(),
			StringUtil.randomString(), WorkflowConstants.STATUS_APPROVED);

		int count = DDMTemplateLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			null, null, _resourceClassNameId, null, null, null,
			WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(3, count);
	}

	protected DDMTemplate copyTemplate(DDMTemplate template) throws Exception {
		return DDMTemplateLocalServiceUtil.copyTemplate(
			template.getUserId(), template.getTemplateId(),
			ServiceContextTestUtil.getServiceContext(group.getGroupId()));
	}

	protected String getTemplateName(DDMTemplate template) {
		return template.getName(group.getDefaultLanguageId());
	}

	protected DDMTemplate updateTemplate(DDMTemplate template)
		throws Exception {

		return DDMTemplateLocalServiceUtil.updateTemplate(
			template.getUserId(), template.getTemplateId(),
			template.getClassPK(), template.getNameMap(),
			template.getDescriptionMap(), template.getType(),
			template.getMode(), template.getLanguage(), template.getScript(),
			template.isCacheable(), template.isSmallImage(),
			template.getSmallImageURL(), null,
			ServiceContextTestUtil.getServiceContext());
	}

	private static long _classNameId;
	private static long _resourceClassNameId;

}