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

package com.liferay.exportimport.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.exportimport.kernel.staging.MergeLayoutPrototypesThreadLocal;
import com.liferay.journal.content.web.constants.JournalContentPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyUtil;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.util.test.LayoutTestUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eduardo Garcia
 */
@RunWith(Arquillian.class)
public abstract class BasePrototypePropagationTestCase {

	@Before
	public void setUp() throws Exception {
		ServiceContextThreadLocal.pushServiceContext(
			ServiceContextTestUtil.getServiceContext());

		ServiceTestUtil.setUser(TestPropsValues.getUser());

		// Group

		group = GroupTestUtil.addGroup();

		// Global scope article

		Company company = CompanyUtil.fetchByPrimaryKey(group.getCompanyId());

		globalGroupId = company.getGroupId();

		globalJournalArticle = JournalTestUtil.addArticle(
			globalGroupId, "Global Article", "Global Content");

		// Layout prototype

		layoutPrototype = LayoutTestUtil.addLayoutPrototype(
			RandomTestUtil.randomString());

		layoutPrototypeLayout = layoutPrototype.getLayout();

		LayoutTestUtil.updateLayoutTemplateId(
			layoutPrototypeLayout, initialLayoutTemplateId);

		doSetUp();
	}

	@Test
	public void testLayoutTypePropagationWithLinkDisabled() throws Exception {
		doTestLayoutTypePropagation(false);
	}

	@Test
	public void testLayoutTypePropagationWithLinkEnabled() throws Exception {
		doTestLayoutTypePropagation(true);
	}

	@Test
	public void testPortletPreferencesPropagationWithLinkDisabled()
		throws Exception {

		doTestPortletPreferencesPropagation(false);
	}

	@Test
	public void testPortletPreferencesPropagationWithLinkEnabled()
		throws Exception {

		doTestPortletPreferencesPropagation(true);
	}

	protected String addPortletToLayout(
			long userId, Layout layout, JournalArticle journalArticle,
			String columnId)
		throws Exception {

		Map<String, String[]> parameterMap = new HashMap<>();

		parameterMap.put(
			"articleId", new String[] {journalArticle.getArticleId()});
		parameterMap.put(
			"groupId",
			new String[] {String.valueOf(journalArticle.getGroupId())});
		parameterMap.put(
			"showAvailableLocales", new String[] {Boolean.TRUE.toString()});

		return LayoutTestUtil.addPortletToLayout(
			userId, layout, JournalContentPortletKeys.JOURNAL_CONTENT, columnId,
			parameterMap);
	}

	protected abstract void doSetUp() throws Exception;

	protected void doTestLayoutTypePropagation(boolean linkEnabled)
		throws Exception {

		setLinkEnabled(linkEnabled);

		int initialPortletCount = LayoutTestUtil.getPortlets(layout).size();

		prototypeLayout = LayoutTestUtil.updateLayoutTemplateId(
			prototypeLayout, "1_column");

		LayoutTestUtil.updateLayoutColumnCustomizable(
			prototypeLayout, "column-1", true);

		addPortletToLayout(
			TestPropsValues.getUserId(), prototypeLayout, globalJournalArticle,
			"column-1");

		if (linkEnabled) {
			Assert.assertEquals(
				initialLayoutTemplateId,
				LayoutTestUtil.getLayoutTemplateId(layout));

			Assert.assertFalse(
				LayoutTestUtil.isLayoutColumnCustomizable(layout, "column-1"));

			Assert.assertEquals(
				initialPortletCount, LayoutTestUtil.getPortlets(layout).size());
		}

		prototypeLayout = updateModifiedDate(
			prototypeLayout,
			new Date(System.currentTimeMillis() + Time.MINUTE));

		layout = propagateChanges(layout);

		if (linkEnabled) {
			Assert.assertEquals(
				"1_column", LayoutTestUtil.getLayoutTemplateId(layout));

			Assert.assertTrue(
				LayoutTestUtil.isLayoutColumnCustomizable(layout, "column-1"));

			Assert.assertEquals(
				initialPortletCount + 1,
				LayoutTestUtil.getPortlets(layout).size());
		}
		else {
			Assert.assertEquals(
				initialLayoutTemplateId,
				LayoutTestUtil.getLayoutTemplateId(layout));

			Assert.assertFalse(
				LayoutTestUtil.isLayoutColumnCustomizable(layout, "column-1"));

			Assert.assertEquals(
				initialPortletCount, LayoutTestUtil.getPortlets(layout).size());
		}
	}

	protected void doTestPortletPreferencesPropagation(boolean linkEnabled)
		throws Exception {

		doTestPortletPreferencesPropagation(linkEnabled, true);
	}

	protected void doTestPortletPreferencesPropagation(
			boolean linkEnabled, boolean globalScope)
		throws Exception {

		setLinkEnabled(linkEnabled);

		MergeLayoutPrototypesThreadLocal.clearMergeComplete();

		Map<String, String> portletPreferencesMap = new HashMap<>();

		portletPreferencesMap.put("articleId", StringPool.BLANK);
		portletPreferencesMap.put(
			"showAvailableLocales", Boolean.FALSE.toString());

		if (globalScope) {
			portletPreferencesMap.put("groupId", String.valueOf(globalGroupId));
			portletPreferencesMap.put("lfrScopeType", "company");
		}

		LayoutTestUtil.updateLayoutPortletPreferences(
			prototypeLayout, portletId, portletPreferencesMap);

		layout = propagateChanges(layout);

		PortletPreferences portletPreferences =
			LayoutTestUtil.getPortletPreferences(layout, portletId);

		if (linkEnabled) {
			if (globalScope) {
				Assert.assertEquals(
					StringPool.BLANK,
					portletPreferences.getValue("articleId", StringPool.BLANK));
			}
			else {

				// Changes in preferences of local ids are not propagated

				Assert.assertEquals(
					journalArticle.getArticleId(),
					portletPreferences.getValue("articleId", StringPool.BLANK));
			}

			Assert.assertEquals(
				Boolean.FALSE.toString(),
				portletPreferences.getValue(
					"showAvailableLocales", StringPool.BLANK));
		}
		else {
			Assert.assertEquals(
				journalArticle.getArticleId(),
				portletPreferences.getValue("articleId", StringPool.BLANK));
		}
	}

	protected Layout propagateChanges(Layout layout) throws Exception {
		MergeLayoutPrototypesThreadLocal.clearMergeComplete();

		return LayoutLocalServiceUtil.getLayout(layout.getPlid());
	}

	protected abstract void setLinkEnabled(boolean linkEnabled)
		throws Exception;

	protected Layout updateModifiedDate(Layout layout, Date date)
		throws Exception {

		layout = LayoutLocalServiceUtil.getLayout(layout.getPlid());

		layout.setModifiedDate(date);

		return LayoutLocalServiceUtil.updateLayout(layout);
	}

	protected long globalGroupId;

	@DeleteAfterTestRun
	protected JournalArticle globalJournalArticle;

	@DeleteAfterTestRun
	protected Group group;

	protected String initialLayoutTemplateId = "2_2_columns";
	protected JournalArticle journalArticle;
	protected Layout layout;

	@DeleteAfterTestRun
	protected LayoutPrototype layoutPrototype;

	protected Layout layoutPrototypeLayout;
	protected String portletId;
	protected Layout prototypeLayout;

}