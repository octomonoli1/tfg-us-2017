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

package com.liferay.staging.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationParameterMapFactory;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationSettingsMapFactory;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalServiceUtil;
import com.liferay.exportimport.kernel.service.ExportImportLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleResource;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleResourceLocalServiceUtil;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.File;
import java.io.Serializable;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Daniel Kocsis
 */
@RunWith(Arquillian.class)
@Sync(cleanTransaction = true)
public class StagingLocalizationTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_availableLocales = LanguageUtil.getAvailableLocales(
			TestPropsValues.getCompanyId());
		_defaultLocale = LocaleThreadLocal.getDefaultLocale();

		CompanyTestUtil.resetCompanyLocales(
			TestPropsValues.getCompanyId(), _locales, Locale.US);

		_sourceGroup = GroupTestUtil.addGroup();
		_targetGroup = GroupTestUtil.addGroup();
	}

	@After
	public void tearDown() throws Exception {
		CompanyTestUtil.resetCompanyLocales(
			TestPropsValues.getCompanyId(), _availableLocales, _defaultLocale);
	}

	@Test(expected = LocaleException.class)
	public void testRemoveSupportedLocale() throws Exception {
		testUpdateLocales("es_ES", "de_DE,es_ES", "en_US");
	}

	@Test
	public void testUpdateDefaultLocale() throws Exception {
		testUpdateLocales("es_ES", "de_DE,en_US,es_ES", "en_US");
	}

	@Test
	public void testUpdateDefaultLocaleAndDefaultContentLocale()
		throws Exception {

		testUpdateLocales("es_ES", "de_DE,en_US,es_ES", "de_DE");
	}

	@Test
	public void testUpdateToTheSameLocale() throws Exception {
		testUpdateLocales("en_US", "de_DE,en_US,es_ES", "en_US");
	}

	protected void testUpdateLocales(
			String defaultLanguageId, String languageIds,
			String defaultContentLanguageId)
		throws Exception {

		GroupTestUtil.enableLocalStaging(_sourceGroup);

		JournalArticle article = JournalTestUtil.addArticle(
			_sourceGroup.getGroupId(), "Title", "content",
			LocaleUtil.fromLanguageId(defaultContentLanguageId));

		User user = TestPropsValues.getUser();

		Map<String, String[]> parameterMap =
			ExportImportConfigurationParameterMapFactory.buildParameterMap();

		Map<String, Serializable> publishLayoutLocalSettingsMap =
			ExportImportConfigurationSettingsMapFactory.
				buildPublishLayoutLocalSettingsMap(
					user, _sourceGroup.getGroupId(), _targetGroup.getGroupId(),
					false,
					ExportImportHelperUtil.getAllLayoutIds(
						_sourceGroup.getGroupId(), false),
					parameterMap);

		ExportImportConfiguration exportImportConfiguration =
			ExportImportConfigurationLocalServiceUtil.
				addDraftExportImportConfiguration(
					user.getUserId(),
					ExportImportConfigurationConstants.
						TYPE_PUBLISH_LAYOUT_LOCAL,
					publishLayoutLocalSettingsMap);

		File file = ExportImportLocalServiceUtil.exportLayoutsAsFile(
			exportImportConfiguration);

		CompanyTestUtil.resetCompanyLocales(
			TestPropsValues.getCompanyId(), languageIds, defaultLanguageId);

		ExportImportLocalServiceUtil.importLayouts(
			exportImportConfiguration, file);

		JournalArticleResource articleResource =
			JournalArticleResourceLocalServiceUtil.
				fetchJournalArticleResourceByUuidAndGroupId(
					article.getArticleResourceUuid(),
					_targetGroup.getGroupId());

		Assert.assertNotNull(articleResource);

		JournalArticle stagingArticle =
			JournalArticleLocalServiceUtil.getLatestArticle(
				articleResource.getResourcePrimKey(),
				WorkflowConstants.STATUS_ANY, false);

		if (languageIds.contains(defaultContentLanguageId)) {
			Assert.assertEquals(
				article.getDefaultLanguageId(),
				stagingArticle.getDefaultLanguageId());
		}
		else {
			Assert.assertEquals(
				defaultLanguageId, stagingArticle.getDefaultLanguageId());
		}

		for (Locale locale : _locales) {
			if (languageIds.contains(LocaleUtil.toLanguageId(locale)) ||
				languageIds.contains(defaultContentLanguageId)) {

				Assert.assertEquals(
					article.getTitle(locale), stagingArticle.getTitle(locale));
			}
			else {
				Assert.assertEquals(
					article.getTitle(defaultLanguageId),
					stagingArticle.getTitle(locale));
			}
		}
	}

	private Set<Locale> _availableLocales;
	private Locale _defaultLocale;
	private final List<Locale> _locales = Arrays.asList(
		LocaleUtil.US, LocaleUtil.GERMANY, LocaleUtil.SPAIN);

	@DeleteAfterTestRun
	private Group _sourceGroup;

	@DeleteAfterTestRun
	private Group _targetGroup;

}