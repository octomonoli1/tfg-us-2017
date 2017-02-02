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

package com.liferay.asset.publisher.lar.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.asset.publisher.test.util.AssetPublisherTestUtil;
import com.liferay.asset.publisher.web.constants.AssetPublisherPortletKeys;
import com.liferay.asset.publisher.web.display.context.AssetEntryResult;
import com.liferay.asset.publisher.web.display.context.AssetPublisherDisplayContext;
import com.liferay.asset.publisher.web.util.AssetPublisherUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.test.util.DDMStructureTestUtil;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationSettingsMapFactory;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalServiceUtil;
import com.liferay.exportimport.kernel.service.ExportImportLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.PortletInstance;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.lar.test.BasePortletExportImportTestCase;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.test.LayoutTestUtil;
import com.liferay.portlet.asset.util.test.AssetTestUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.portlet.MockPortletRequest;

/**
 * @author Julio Camarero
 */
@RunWith(Arquillian.class)
@Sync
public class AssetPublisherExportImportTest
	extends BasePortletExportImportTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@Override
	public String getPortletId() throws Exception {
		PortletInstance portletInstance = new PortletInstance(
			AssetPublisherPortletKeys.ASSET_PUBLISHER,
			RandomTestUtil.randomString());

		return portletInstance.getPortletInstanceKey();
	}

	@Before
	@Override
	public void setUp() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		super.setUp();

		_permissionChecker = PermissionCheckerFactoryUtil.create(
			TestPropsValues.getUser());
	}

	@Test
	public void testAnyDLFileEntryType() throws Exception {
		Map<String, String[]> preferenceMap = new HashMap<>();

		long dlFileEntryClassNameId = PortalUtil.getClassNameId(
			DLFileEntry.class);

		preferenceMap.put(
			"anyAssetType",
			new String[] {String.valueOf(dlFileEntryClassNameId)});
		preferenceMap.put(
			"anyClassTypeDLFileEntryAssetRendererFactory",
			new String[] {String.valueOf(Boolean.TRUE)});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		long anyAssetType = GetterUtil.getLong(
			portletPreferences.getValue("anyAssetType", null));

		Assert.assertEquals(dlFileEntryClassNameId, anyAssetType);

		String anyClassTypeDLFileEntryAssetRendererFactory =
			portletPreferences.getValue(
				"anyClassTypeDLFileEntryAssetRendererFactory", null);

		Assert.assertEquals(
			anyClassTypeDLFileEntryAssetRendererFactory,
			String.valueOf(Boolean.TRUE));
	}

	@Test
	public void testAnyJournalStructure() throws Exception {
		Map<String, String[]> preferenceMap = new HashMap<>();

		long journalArticleClassNameId = PortalUtil.getClassNameId(
			JournalArticle.class);

		preferenceMap.put(
			"anyAssetType",
			new String[] {String.valueOf(journalArticleClassNameId)});
		preferenceMap.put(
			"anyClassTypeJournalArticleAssetRendererFactory",
			new String[] {String.valueOf(Boolean.TRUE)});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		long anyAssetType = GetterUtil.getLong(
			portletPreferences.getValue("anyAssetType", null));

		Assert.assertEquals(journalArticleClassNameId, anyAssetType);

		String anyClassTypeDLFileEntryAssetRendererFactory =
			portletPreferences.getValue(
				"anyClassTypeJournalArticleAssetRendererFactory", null);

		Assert.assertEquals(
			anyClassTypeDLFileEntryAssetRendererFactory,
			String.valueOf(Boolean.TRUE));
	}

	@Test
	public void testAssetCategories() throws Exception {
		AssetVocabulary assetVocabulary = AssetTestUtil.addVocabulary(
			group.getGroupId());

		AssetCategory assetCategory = AssetTestUtil.addCategory(
			group.getGroupId(), assetVocabulary.getVocabularyId());

		Map<String, String[]> preferenceMap = new HashMap<>();

		preferenceMap.put("queryName0", new String[] {"assetCategories"});

		preferenceMap.put(
			"queryValues0",
			new String[] {String.valueOf(assetCategory.getCategoryId())});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		long importedAssetCategoryId = GetterUtil.getLong(
			portletPreferences.getValue("queryValues0", null));

		Assert.assertNotEquals(importedAssetCategoryId, 0L);

		Assert.assertNotEquals(
			assetCategory.getCategoryId(), importedAssetCategoryId);

		AssetCategory importedAssetCategory =
			AssetCategoryLocalServiceUtil.fetchAssetCategory(
				importedAssetCategoryId);

		Assert.assertNotNull(importedAssetCategory);
		Assert.assertEquals(
			assetCategory.getUuid(), importedAssetCategory.getUuid());
	}

	@Test
	public void testChildLayoutScopeIds() throws Exception {
		Map<String, String[]> preferenceMap = new HashMap<>();

		Group childGroup = GroupTestUtil.addGroup(group.getGroupId());

		preferenceMap.put(
			"scopeIds",
			new String[] {
				AssetPublisherUtil.SCOPE_ID_CHILD_GROUP_PREFIX +
					childGroup.getGroupId()
			});

		try {
			PortletPreferences portletPreferences =
				getImportedPortletPreferences(preferenceMap);

			Assert.assertEquals(
				null, portletPreferences.getValue("scopeId", null));
			Assert.assertTrue(
				"The child group ID should have been filtered out on import",
				ArrayUtil.isEmpty(
					portletPreferences.getValues("scopeIds", null)));
		}
		finally {
			GroupLocalServiceUtil.deleteGroup(childGroup);
		}
	}

	@Test
	public void testDisplayStyle() throws Exception {
		Map<String, String[]> preferenceMap = new HashMap<>();

		String displayStyle = RandomTestUtil.randomString();

		preferenceMap.put("displayStyle", new String[] {displayStyle});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		Assert.assertEquals(
			displayStyle, portletPreferences.getValue("displayStyle", null));
		Assert.assertTrue(
			"The display style should not be null",
			Validator.isNotNull(
				portletPreferences.getValue("displayStyle", null)));
	}

	@Test
	public void testDynamicExportImportAssetCategoryFiltering()
		throws Exception {

		AssetVocabulary assetVocabulary = AssetTestUtil.addVocabulary(
			group.getGroupId());

		AssetCategory assetCategory = AssetTestUtil.addCategory(
			group.getGroupId(), assetVocabulary.getVocabularyId());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		serviceContext.setAssetCategoryIds(
			new long[] {assetCategory.getCategoryId()});

		List<AssetEntry> expectedAssetEntries = addAssetEntries(
			group, 2, new ArrayList<AssetEntry>(), serviceContext);

		Map<String, String[]> preferenceMap = new HashMap<>();

		preferenceMap.put("queryContains0", new String[] {"true"});
		preferenceMap.put("queryName0", new String[] {"assetCategories"});
		preferenceMap.put(
			"queryValues0",
			new String[] {String.valueOf(assetCategory.getCategoryId())});

		testDynamicExportImport(preferenceMap, expectedAssetEntries, true);
	}

	@Test
	public void testDynamicExportImportAssetTagFiltering() throws Exception {
		AssetTag assetTag = AssetTestUtil.addTag(group.getGroupId());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		serviceContext.setAssetTagNames(new String[] {assetTag.getName()});

		List<AssetEntry> expectedAssetEntries = addAssetEntries(
			group, 2, new ArrayList<AssetEntry>(), serviceContext);

		Map<String, String[]> preferenceMap = new HashMap<>();

		preferenceMap.put(
			"queryContains0", new String[] {Boolean.TRUE.toString()});
		preferenceMap.put("queryValues0", new String[] {assetTag.getName()});

		testDynamicExportImport(preferenceMap, expectedAssetEntries, true);
	}

	@Test
	public void testDynamicExportImportAssetVocabularyFiltering()
		throws Exception {

		AssetVocabulary assetVocabulary = AssetTestUtil.addVocabulary(
			group.getGroupId());

		AssetCategory assetCategory1 = AssetTestUtil.addCategory(
			group.getGroupId(), assetVocabulary.getVocabularyId());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		serviceContext.setAssetCategoryIds(
			new long[] {assetCategory1.getCategoryId()});

		List<AssetEntry> expectedAssetEntries = addAssetEntries(
			group, 1, new ArrayList<AssetEntry>(), serviceContext);

		AssetCategory assetCategory2 = AssetTestUtil.addCategory(
			group.getGroupId(), assetVocabulary.getVocabularyId());

		serviceContext.setAssetCategoryIds(
			new long[] {assetCategory2.getCategoryId()});

		expectedAssetEntries = addAssetEntries(
			group, 1, expectedAssetEntries, serviceContext);

		Map<String, String[]> preferenceMap = new HashMap<>();

		preferenceMap.put(
			"assetVocabularyId",
			new String[] {String.valueOf(assetVocabulary.getVocabularyId())});

		testDynamicExportImport(preferenceMap, expectedAssetEntries, true);
	}

	@Test
	public void testDynamicExportImportClassTypeFiltering() throws Exception {
		List<AssetEntry> expectedAssetEntries = new ArrayList<>();

		JournalArticle journalArticle = JournalTestUtil.addArticle(
			group.getGroupId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(100),
			ServiceContextTestUtil.getServiceContext());

		expectedAssetEntries.add(getAssetEntry(journalArticle));

		Map<String, String[]> preferenceMap = new HashMap<>();

		long journalArticleClassNameId = PortalUtil.getClassNameId(
			JournalArticle.class);

		preferenceMap.put(
			"anyAssetType",
			new String[] {String.valueOf(journalArticleClassNameId)});

		DDMStructure ddmStructure = journalArticle.getDDMStructure();

		preferenceMap.put(
			"classTypeIds",
			new String[] {String.valueOf(ddmStructure.getStructureId())});

		testDynamicExportImport(preferenceMap, expectedAssetEntries, true);
	}

	@Test
	public void testDynamicExportImportLayoutFiltering() throws Exception {
		List<AssetEntry> expectedAssetEntries = new ArrayList<>();

		Map<Locale, String> titleMap = new HashMap<>();

		titleMap.put(LocaleUtil.getDefault(), RandomTestUtil.randomString());

		Map<Locale, String> contentMap = new HashMap<>();

		contentMap.put(
			LocaleUtil.getDefault(), RandomTestUtil.randomString(100));

		JournalArticle journalArticle = JournalTestUtil.addArticle(
			group.getGroupId(), JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			JournalArticleConstants.CLASSNAME_ID_DEFAULT, titleMap, titleMap,
			contentMap, layout.getUuid(), LocaleUtil.getDefault(), null, false,
			false, ServiceContextTestUtil.getServiceContext());

		expectedAssetEntries.add(getAssetEntry(journalArticle));

		Map<String, String[]> preferenceMap = new HashMap<>();

		preferenceMap.put(
			"showOnlyLayoutAssets", new String[] {Boolean.TRUE.toString()});

		testDynamicExportImport(preferenceMap, expectedAssetEntries, true);
	}

	@Test
	public void testDynamicExportImportOtherClassNameFiltering()
		throws Exception {

		Map<String, String[]> preferenceMap = new HashMap<>();

		long dlFileEntryClassNameId = PortalUtil.getClassNameId(
			DLFileEntry.class);

		preferenceMap.put(
			"anyAssetType",
			new String[] {String.valueOf(dlFileEntryClassNameId)});

		testDynamicExportImport(
			preferenceMap, new ArrayList<AssetEntry>(), true);
	}

	@Test
	public void testDynamicExportImportWithNoFiltering() throws Exception {
		List<AssetEntry> expectedAssetEntries = addAssetEntries(
			group, 2, new ArrayList<AssetEntry>(),
			ServiceContextTestUtil.getServiceContext());

		testDynamicExportImport(
			new HashMap<String, String[]>(), expectedAssetEntries, false);
	}

	@Test
	public void testExportImportAssetEntries() throws Exception {
		testExportImportAssetEntries(group);
	}

	@Ignore
	@Override
	@Test
	public void testExportImportAssetLinks() throws Exception {
	}

	@Test
	public void testExportImportLayoutScopedAssetEntries() throws Exception {
		Group layoutGroup = GroupTestUtil.addGroup(
			TestPropsValues.getUserId(), layout);

		testExportImportAssetEntries(layoutGroup);
	}

	@Test
	public void testExportImportSeveralScopedAssetEntries() throws Exception {
		List<Group> groups = new ArrayList<>();

		Company company = CompanyLocalServiceUtil.getCompany(
			layout.getCompanyId());

		Group companyGroup = company.getGroup();

		groups.add(companyGroup);

		groups.add(group);

		Group group2 = GroupTestUtil.addGroup();

		groups.add(group2);

		Group layoutGroup1 = GroupTestUtil.addGroup(
			TestPropsValues.getUserId(), layout);

		groups.add(layoutGroup1);

		Layout layout2 = LayoutTestUtil.addLayout(group);

		Group layoutGroup2 = GroupTestUtil.addGroup(
			TestPropsValues.getUserId(), layout2);

		groups.add(layoutGroup2);

		testExportImportAssetEntries(groups);
	}

	@Test
	public void testGlobalScopeId() throws Exception {
		Map<String, String[]> preferenceMap = new HashMap<>();

		Company company = CompanyLocalServiceUtil.getCompany(
			layout.getCompanyId());

		Group companyGroup = company.getGroup();

		preferenceMap.put(
			"scopeIds",
			new String[] {
				AssetPublisherUtil.SCOPE_ID_GROUP_PREFIX +
					companyGroup.getGroupId()
			});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		Assert.assertEquals(
			AssetPublisherUtil.SCOPE_ID_GROUP_PREFIX +
				companyGroup.getGroupId(),
			portletPreferences.getValue("scopeIds", null));
		Assert.assertEquals(null, portletPreferences.getValue("scopeId", null));
	}

	@Test
	public void testLayoutScopeId() throws Exception {
		Map<String, String[]> preferenceMap = new HashMap<>();

		GroupTestUtil.addGroup(TestPropsValues.getUserId(), layout);

		preferenceMap.put(
			"scopeIds",
			new String[] {
				AssetPublisherUtil.SCOPE_ID_LAYOUT_UUID_PREFIX +
					layout.getUuid()
			});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		Assert.assertEquals(
			AssetPublisherUtil.SCOPE_ID_LAYOUT_UUID_PREFIX +
				importedLayout.getUuid(),
			portletPreferences.getValue("scopeIds", null));
		Assert.assertEquals(null, portletPreferences.getValue("scopeId", null));
	}

	@Test
	public void testLegacyLayoutScopeId() throws Exception {
		Map<String, String[]> preferenceMap = new HashMap<>();

		GroupTestUtil.addGroup(TestPropsValues.getUserId(), layout);

		preferenceMap.put(
			"scopeIds",
			new String[] {
				AssetPublisherUtil.SCOPE_ID_LAYOUT_PREFIX + layout.getLayoutId()
			});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		Assert.assertEquals(
			AssetPublisherUtil.SCOPE_ID_LAYOUT_UUID_PREFIX +
				importedLayout.getUuid(),
			portletPreferences.getValue("scopeIds", null));
		Assert.assertEquals(null, portletPreferences.getValue("scopeId", null));
	}

	@Test
	public void testOneDLFileEntryType() throws Exception {
		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			group.getGroupId(), DLFileEntryMetadata.class.getName());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId());

		DLFileEntryType dlFileEntryType = addDLFileEntryType(
			group.getGroupId(), ddmStructure.getStructureId(), serviceContext);

		serviceContext.setUuid(ddmStructure.getUuid());

		DDMStructure importedDDMStructure = DDMStructureTestUtil.addStructure(
			importedGroup.getGroupId(), DLFileEntryMetadata.class.getName(), 0,
			ddmStructure.getDDMForm(), LocaleUtil.getDefault(), serviceContext);

		serviceContext.setUuid(dlFileEntryType.getUuid());

		DLFileEntryType importedDLFileEntryType = addDLFileEntryType(
			importedGroup.getGroupId(), importedDDMStructure.getStructureId(),
			serviceContext);

		Map<String, String[]> preferenceMap = new HashMap<>();

		long dlFileEntryClassNameId = PortalUtil.getClassNameId(
			DLFileEntry.class);

		preferenceMap.put(
			"anyAssetType",
			new String[] {String.valueOf(dlFileEntryClassNameId)});
		preferenceMap.put(
			"anyClassTypeDLFileEntryAssetRendererFactory",
			new String[] {
				String.valueOf(dlFileEntryType.getFileEntryTypeId())
			});
		preferenceMap.put(
			"classTypeIds",
			new String[] {
				String.valueOf(dlFileEntryType.getFileEntryTypeId())
			});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		long anyClassTypeDLFileEntryAssetRendererFactory = GetterUtil.getLong(
			portletPreferences.getValue(
				"anyClassTypeDLFileEntryAssetRendererFactory", null));

		Assert.assertEquals(
			anyClassTypeDLFileEntryAssetRendererFactory,
			importedDLFileEntryType.getFileEntryTypeId());

		long anyAssetType = GetterUtil.getLong(
			portletPreferences.getValue("anyAssetType", null));

		Assert.assertEquals(dlFileEntryClassNameId, anyAssetType);

		long classTypeIds = GetterUtil.getLong(
			portletPreferences.getValue("classTypeIds", null));

		Assert.assertEquals(
			importedDLFileEntryType.getFileEntryTypeId(), classTypeIds);
	}

	@Test
	public void testOneJournalStructure() throws Exception {
		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			group.getGroupId(), JournalArticle.class.getName());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		serviceContext.setUuid(ddmStructure.getUuid());

		DDMStructure importedDDMStructure = DDMStructureTestUtil.addStructure(
			importedGroup.getGroupId(), JournalArticle.class.getName(), 0,
			ddmStructure.getDDMForm(), LocaleUtil.getDefault(), serviceContext);

		Map<String, String[]> preferenceMap = new HashMap<>();

		long journalArticleClassNameId = PortalUtil.getClassNameId(
			JournalArticle.class);

		preferenceMap.put(
			"anyAssetType",
			new String[] {String.valueOf(journalArticleClassNameId)});
		preferenceMap.put(
			"anyClassTypeJournalArticleAssetRendererFactory",
			new String[] {String.valueOf(ddmStructure.getStructureId())});
		preferenceMap.put(
			"classTypeIds",
			new String[] {String.valueOf(ddmStructure.getStructureId())});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		long anyClassTypeJournalArticleAssetRendererFactory =
			GetterUtil.getLong(
				portletPreferences.getValue(
					"anyClassTypeJournalArticleAssetRendererFactory", null));

		Assert.assertEquals(
			anyClassTypeJournalArticleAssetRendererFactory,
			importedDDMStructure.getStructureId());

		long anyAssetType = GetterUtil.getLong(
			portletPreferences.getValue("anyAssetType", null));

		Assert.assertEquals(journalArticleClassNameId, anyAssetType);

		long classTypeIds = GetterUtil.getLong(
			portletPreferences.getValue("classTypeIds", null));

		Assert.assertEquals(
			importedDDMStructure.getStructureId(), classTypeIds);
	}

	@Test
	public void testSeveralDLFileEntryTypes() throws Exception {
		DDMStructure ddmStructure1 = DDMStructureTestUtil.addStructure(
			group.getGroupId(), DLFileEntryMetadata.class.getName());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId());

		DLFileEntryType dlFileEntryType1 = addDLFileEntryType(
			group.getGroupId(), ddmStructure1.getStructureId(), serviceContext);

		serviceContext.setUuid(ddmStructure1.getUuid());

		DDMStructure importedDDMStructure1 = DDMStructureTestUtil.addStructure(
			importedGroup.getGroupId(), DLFileEntryMetadata.class.getName(), 0,
			ddmStructure1.getDDMForm(), LocaleUtil.getDefault(),
			serviceContext);

		serviceContext.setUuid(dlFileEntryType1.getUuid());

		DLFileEntryType importedDLFileEntryType1 = addDLFileEntryType(
			importedGroup.getGroupId(), importedDDMStructure1.getStructureId(),
			serviceContext);

		DDMStructure ddmStructure2 = DDMStructureTestUtil.addStructure(
			group.getGroupId(), DLFileEntryMetadata.class.getName());

		serviceContext.setUuid(null);

		DLFileEntryType dlFileEntryType2 = addDLFileEntryType(
			group.getGroupId(), ddmStructure2.getStructureId(), serviceContext);

		serviceContext.setUuid(ddmStructure2.getUuid());

		DDMStructure importedDDMStructure2 = DDMStructureTestUtil.addStructure(
			importedGroup.getGroupId(), DLFileEntryMetadata.class.getName(), 0,
			ddmStructure2.getDDMForm(), LocaleUtil.getDefault(),
			serviceContext);

		serviceContext.setUuid(dlFileEntryType2.getUuid());

		DLFileEntryType importedDLFileEntryType2 = addDLFileEntryType(
			importedGroup.getGroupId(), importedDDMStructure2.getStructureId(),
			serviceContext);

		Map<String, String[]> preferenceMap = new HashMap<>();

		preferenceMap.put(
			"anyClassTypeDLFileEntryAssetRendererFactory",
			new String[] {String.valueOf(Boolean.FALSE)});

		preferenceMap.put(
			"classTypeIdsDLFileEntryAssetRendererFactory",
			new String[] {
				String.valueOf(dlFileEntryType1.getFileEntryTypeId()),
				String.valueOf(dlFileEntryType2.getFileEntryTypeId())
			});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		Assert.assertEquals(
			importedDLFileEntryType1.getFileEntryTypeId() + StringPool.COMMA +
				importedDLFileEntryType2.getFileEntryTypeId(),
			StringUtil.merge(
				portletPreferences.getValues(
					"classTypeIdsDLFileEntryAssetRendererFactory", null)));
	}

	@Test
	public void testSeveralJournalStructures() throws Exception {
		DDMStructure ddmStructure1 = DDMStructureTestUtil.addStructure(
			group.getGroupId(), JournalArticle.class.getName());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		serviceContext.setUuid(ddmStructure1.getUuid());

		DDMStructure importedDDMStructure1 = DDMStructureTestUtil.addStructure(
			importedGroup.getGroupId(), JournalArticle.class.getName(), 0,
			ddmStructure1.getDDMForm(), LocaleUtil.getDefault(),
			serviceContext);

		DDMStructure ddmStructure2 = DDMStructureTestUtil.addStructure(
			group.getGroupId(), JournalArticle.class.getName());

		serviceContext.setUuid(ddmStructure2.getUuid());

		DDMStructure importedDDMStructure2 = DDMStructureTestUtil.addStructure(
			importedGroup.getGroupId(), JournalArticle.class.getName(), 0,
			ddmStructure1.getDDMForm(), LocaleUtil.getDefault(),
			serviceContext);

		Map<String, String[]> preferenceMap = new HashMap<>();

		preferenceMap.put(
			"anyClassTypeJournalArticleAssetRendererFactory",
			new String[] {String.valueOf(Boolean.FALSE)});

		preferenceMap.put(
			"classTypeIdsJournalArticleAssetRendererFactory",
			new String[] {
				String.valueOf(ddmStructure1.getStructureId()),
				String.valueOf(ddmStructure2.getStructureId())
			});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		Assert.assertEquals(
			importedDDMStructure1.getStructureId() + StringPool.COMMA +
				importedDDMStructure2.getStructureId(),
			StringUtil.merge(
				portletPreferences.getValues(
					"classTypeIdsJournalArticleAssetRendererFactory", null)));
	}

	@Test
	public void testSeveralLayoutScopeIds() throws Exception {
		Company company = CompanyLocalServiceUtil.getCompany(
			layout.getCompanyId());

		Layout secondLayout = LayoutTestUtil.addLayout(group);

		GroupTestUtil.addGroup(TestPropsValues.getUserId(), secondLayout);

		Map<String, String[]> preferenceMap = new HashMap<>();

		GroupTestUtil.addGroup(TestPropsValues.getUserId(), layout);

		Group companyGroup = company.getGroup();

		preferenceMap.put(
			"scopeIds",
			new String[] {
				AssetPublisherUtil.SCOPE_ID_GROUP_PREFIX +
					companyGroup.getGroupId(),
				AssetPublisherUtil.SCOPE_ID_LAYOUT_UUID_PREFIX +
					layout.getUuid(),
				AssetPublisherUtil.SCOPE_ID_LAYOUT_UUID_PREFIX +
					secondLayout.getUuid()
			});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		Layout importedSecondLayout =
			LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(
				secondLayout.getUuid(), importedGroup.getGroupId(),
				importedLayout.isPrivateLayout());

		Assert.assertEquals(null, portletPreferences.getValue("scopeId", null));

		StringBundler sb = new StringBundler(8);

		sb.append(AssetPublisherUtil.SCOPE_ID_GROUP_PREFIX);
		sb.append(companyGroup.getGroupId());
		sb.append(StringPool.COMMA);
		sb.append(AssetPublisherUtil.SCOPE_ID_LAYOUT_UUID_PREFIX);
		sb.append(importedLayout.getUuid());
		sb.append(StringPool.COMMA);
		sb.append(AssetPublisherUtil.SCOPE_ID_LAYOUT_UUID_PREFIX);
		sb.append(importedSecondLayout.getUuid());

		Assert.assertEquals(
			sb.toString(),
			StringUtil.merge(portletPreferences.getValues("scopeIds", null)));
	}

	@Test
	public void testSeveralLegacyLayoutScopeIds() throws Exception {
		Layout secondLayout = LayoutTestUtil.addLayout(group);

		GroupTestUtil.addGroup(TestPropsValues.getUserId(), secondLayout);

		Map<String, String[]> preferenceMap = new HashMap<>();

		GroupTestUtil.addGroup(TestPropsValues.getUserId(), layout);

		preferenceMap.put(
			"scopeIds",
			new String[] {
				AssetPublisherUtil.SCOPE_ID_LAYOUT_PREFIX +
					layout.getLayoutId(),
				AssetPublisherUtil.SCOPE_ID_LAYOUT_PREFIX +
					secondLayout.getLayoutId()
			});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		Layout importedSecondLayout =
			LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(
				secondLayout.getUuid(), importedGroup.getGroupId(),
				importedLayout.isPrivateLayout());

		Assert.assertEquals(null, portletPreferences.getValue("scopeId", null));

		StringBundler sb = new StringBundler(5);

		sb.append(AssetPublisherUtil.SCOPE_ID_LAYOUT_UUID_PREFIX);
		sb.append(importedLayout.getUuid());
		sb.append(StringPool.COMMA);
		sb.append(AssetPublisherUtil.SCOPE_ID_LAYOUT_UUID_PREFIX);
		sb.append(importedSecondLayout.getUuid());

		Assert.assertEquals(
			sb.toString(),
			StringUtil.merge(portletPreferences.getValues("scopeIds", null)));
	}

	@Test
	public void testSortByAssetVocabulary() throws Exception {
		testSortByAssetVocabulary(false);
	}

	@Test
	public void testSortByGlobalAssetVocabulary() throws Exception {
		testSortByAssetVocabulary(true);
	}

	protected List<AssetEntry> addAssetEntries(
			Group group, int count, List<AssetEntry> assetEntries,
			ServiceContext serviceContext)
		throws Exception {

		for (int i = 0; i < count; i++) {
			JournalArticle journalArticle = JournalTestUtil.addArticle(
				group.getGroupId(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString(100), serviceContext);

			assetEntries.add(getAssetEntry(journalArticle));
		}

		return assetEntries;
	}

	protected DLFileEntryType addDLFileEntryType(
			long groupId, long ddmStructureId, ServiceContext serviceContext)
		throws Exception {

		return DLFileEntryTypeLocalServiceUtil.addFileEntryType(
			serviceContext.getUserId(), groupId, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), new long[] {ddmStructureId},
			serviceContext);
	}

	protected void assertAssetEntries(
		List<AssetEntry> expectedAssetEntries,
		List<AssetEntry> actualAssetEntries) {

		Assert.assertEquals(
			expectedAssetEntries.size(), actualAssetEntries.size());

		Iterator<AssetEntry> expectedAssetEntriesIterator =
			expectedAssetEntries.iterator();
		Iterator<AssetEntry> actualAssetEntriesIterator =
			expectedAssetEntries.iterator();

		while (expectedAssetEntriesIterator.hasNext() &&
			   actualAssetEntriesIterator.hasNext()) {

			AssetEntry expectedAssetEntry = expectedAssetEntriesIterator.next();
			AssetEntry actualAssetEntry = actualAssetEntriesIterator.next();

			Assert.assertEquals(
				expectedAssetEntry.getClassName(),
				actualAssetEntry.getClassName());
			Assert.assertEquals(
				expectedAssetEntry.getClassUuid(),
				actualAssetEntry.getClassUuid());
		}
	}

	@Override
	protected void exportImportPortlet(String portletId) throws Exception {
		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			layout.getGroupId(), layout.isPrivateLayout());

		User user = TestPropsValues.getUser();

		Map<String, Serializable> exportLayoutSettingsMap =
			ExportImportConfigurationSettingsMapFactory.
				buildExportLayoutSettingsMap(
					user, layout.getGroupId(), layout.isPrivateLayout(),
					ExportImportHelperUtil.getLayoutIds(layouts),
					getExportParameterMap());

		ExportImportConfiguration exportConfiguration =
			ExportImportConfigurationLocalServiceUtil.
				addDraftExportImportConfiguration(
					user.getUserId(),
					ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT,
					exportLayoutSettingsMap);

		larFile = ExportImportLocalServiceUtil.exportLayoutsAsFile(
			exportConfiguration);

		// Import site LAR

		Map<String, Serializable> importLayoutSettingsMap =
			ExportImportConfigurationSettingsMapFactory.
				buildImportLayoutSettingsMap(
					user, importedGroup.getGroupId(), layout.isPrivateLayout(),
					null, getImportParameterMap());

		ExportImportConfiguration importConfiguration =
			ExportImportConfigurationLocalServiceUtil.
				addDraftExportImportConfiguration(
					user.getUserId(),
					ExportImportConfigurationConstants.TYPE_IMPORT_LAYOUT,
					importLayoutSettingsMap);

		ExportImportLocalServiceUtil.importLayouts(
			importConfiguration, larFile);

		importedLayout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(
			layout.getUuid(), importedGroup.getGroupId(),
			layout.isPrivateLayout());

		Assert.assertNotNull(importedLayout);
	}

	protected String[] getAssetEntriesXmls(List<AssetEntry> assetEntries) {
		String[] assetEntriesXmls = new String[assetEntries.size()];

		for (int i = 0; i < assetEntries.size(); i++) {
			assetEntriesXmls[i] = AssetPublisherTestUtil.getAssetEntryXml(
				assetEntries.get(i));
		}

		return assetEntriesXmls;
	}

	@Override
	protected Map<String, String[]> getExportParameterMap() throws Exception {
		Map<String, String[]> parameterMap = new HashMap<>();

		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_CONFIGURATION,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_CONFIGURATION_ALL,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_DATA,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_SETUP_ALL,
			new String[] {Boolean.TRUE.toString()});

		return parameterMap;
	}

	protected long[] getGroupIdsFromScopeIds(String[] scopeIds, Layout layout)
		throws Exception {

		long[] groupIds = new long[scopeIds.length];

		for (int i = 0; i < scopeIds.length; i++) {
			groupIds[i] = AssetPublisherUtil.getGroupIdFromScopeId(
				scopeIds[i], layout.getGroupId(), layout.isPrivateLayout());
		}

		return groupIds;
	}

	@Override
	protected Map<String, String[]> getImportParameterMap() throws Exception {
		return getExportParameterMap();
	}

	protected void testDynamicExportImport(
			Map<String, String[]> preferenceMap,
			List<AssetEntry> expectedAssetEntries, boolean filtering)
		throws Exception {

		if (filtering) {

			// Creating entries to validate filtering

			addAssetEntries(
				group, 2, new ArrayList<AssetEntry>(),
				ServiceContextTestUtil.getServiceContext());
		}

		String scopeId = AssetPublisherUtil.getScopeId(
			group, group.getGroupId());

		preferenceMap.put("scopeIds", new String[] {scopeId});

		preferenceMap.put("selectionStyle", new String[] {"dynamic"});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		ThemeDisplay themeDisplay = new ThemeDisplay();

		Company company = CompanyLocalServiceUtil.getCompany(
			TestPropsValues.getCompanyId());

		themeDisplay.setCompany(company);

		themeDisplay.setLayout(importedLayout);
		themeDisplay.setScopeGroupId(importedGroup.getGroupId());
		themeDisplay.setUser(TestPropsValues.getUser());

		mockHttpServletRequest.setAttribute(
			WebKeys.THEME_DISPLAY, themeDisplay);

		AssetPublisherDisplayContext assetPublisherDisplayContext =
			new AssetPublisherDisplayContext(
				mockHttpServletRequest, portletPreferences);

		SearchContainer<AssetEntry> searchContainer = new SearchContainer<>();

		searchContainer.setTotal(10);

		List<AssetEntryResult> actualAssetEntryResults =
			AssetPublisherUtil.getAssetEntryResults(
				assetPublisherDisplayContext, searchContainer,
				portletPreferences);

		List<AssetEntry> actualAssetEntries = new ArrayList<>();

		for (AssetEntryResult assetEntryResult : actualAssetEntryResults) {
			actualAssetEntries.addAll(assetEntryResult.getAssetEntries());
		}

		assertAssetEntries(expectedAssetEntries, actualAssetEntries);
	}

	protected void testExportImportAssetEntries(Group scopeGroup)
		throws Exception {

		List<Group> groups = new ArrayList<>();

		groups.add(scopeGroup);

		testExportImportAssetEntries(groups);
	}

	protected void testExportImportAssetEntries(List<Group> scopeGroups)
		throws Exception {

		List<AssetEntry> assetEntries = new ArrayList<>();
		String[] scopeIds = new String[0];

		for (Group scopeGroup : scopeGroups) {
			ServiceContext serviceContext =
				ServiceContextTestUtil.getServiceContext();

			if (scopeGroup.isLayout()) {

				// Creating structures and templates in layout scope group is
				// not possible

				Company company = CompanyLocalServiceUtil.getCompany(
					layout.getCompanyId());

				serviceContext.setAttribute("ddmGroupId", company.getGroupId());
			}

			assetEntries = addAssetEntries(
				scopeGroup, 3, assetEntries, serviceContext);

			String scopeId = AssetPublisherUtil.getScopeId(
				scopeGroup, group.getGroupId());

			scopeIds = ArrayUtil.append(scopeIds, scopeId);
		}

		Map<String, String[]> preferenceMap = new HashMap<>();

		preferenceMap.put("assetEntryXml", getAssetEntriesXmls(assetEntries));
		preferenceMap.put("scopeIds", scopeIds);

		PortletPreferences importedPortletPreferences =
			getImportedPortletPreferences(preferenceMap);

		String[] importedScopeIds = importedPortletPreferences.getValues(
			"scopeIds", null);

		long[] selectedGroupIds = getGroupIdsFromScopeIds(
			importedScopeIds, importedLayout);

		List<AssetEntry> actualAssetEntries =
			AssetPublisherUtil.getAssetEntries(
				new MockPortletRequest(), importedPortletPreferences,
				_permissionChecker, selectedGroupIds, false, false);

		assertAssetEntries(assetEntries, actualAssetEntries);
	}

	protected void testSortByAssetVocabulary(boolean globalVocabulary)
		throws Exception {

		long groupId = group.getGroupId();

		if (globalVocabulary) {
			Company company = CompanyLocalServiceUtil.getCompany(
				layout.getCompanyId());

			groupId = company.getGroupId();
		}

		AssetVocabulary assetVocabulary =
			AssetVocabularyLocalServiceUtil.addVocabulary(
				TestPropsValues.getUserId(), groupId,
				RandomTestUtil.randomString(),
				ServiceContextTestUtil.getServiceContext(groupId));

		Map<String, String[]> preferenceMap = new HashMap<>();

		preferenceMap.put(
			"assetVocabularyId",
			new String[] {String.valueOf(assetVocabulary.getVocabularyId())});

		PortletPreferences portletPreferences = getImportedPortletPreferences(
			preferenceMap);

		Assert.assertNotNull(
			"Portlet preference \"assetVocabularyId\" is null",
			portletPreferences.getValue("assetVocabularyId", null));

		long importedAssetVocabularyId = GetterUtil.getLong(
			portletPreferences.getValue("assetVocabularyId", null));

		AssetVocabulary importedVocabulary =
			AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(
				importedAssetVocabularyId);

		Assert.assertNotNull(
			"Vocabulary " + importedAssetVocabularyId + " does not exist",
			importedVocabulary);

		long expectedGroupId = groupId;

		if (!globalVocabulary) {
			expectedGroupId = importedGroup.getGroupId();
		}

		Assert.assertEquals(
			"Vocabulary " + importedAssetVocabularyId +
				" does not belong to group " + expectedGroupId,
			expectedGroupId, importedVocabulary.getGroupId());

		AssetVocabularyLocalServiceUtil.deleteAssetVocabulary(assetVocabulary);
	}

	private PermissionChecker _permissionChecker;

}