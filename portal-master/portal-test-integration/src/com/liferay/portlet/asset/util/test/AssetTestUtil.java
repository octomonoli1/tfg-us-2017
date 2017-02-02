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

package com.liferay.portlet.asset.util.test;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portlet.asset.util.AssetVocabularySettingsHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Mate Thurzo
 */
public class AssetTestUtil {

	public static AssetEntry addAssetEntry(long groupId) throws Exception {
		return addAssetEntry(groupId, null);
	}

	public static AssetEntry addAssetEntry(long groupId, Date publishDate)
		throws Exception {

		long assetEntryId = CounterLocalServiceUtil.increment();

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.createAssetEntry(
			assetEntryId);

		assetEntry.setClassName(RandomTestUtil.randomString());
		assetEntry.setClassPK(RandomTestUtil.randomLong());
		assetEntry.setGroupId(groupId);
		assetEntry.setPublishDate(publishDate);
		assetEntry.setVisible(true);

		return AssetEntryLocalServiceUtil.updateAssetEntry(assetEntry);
	}

	public static AssetCategory addCategory(long groupId, long vocabularyId)
		throws Exception {

		return addCategory(
			groupId, vocabularyId,
			AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);
	}

	public static AssetCategory addCategory(
			long groupId, long vocabularyId, long parentCategoryId)
		throws Exception {

		Map<Locale, String> titleMap = new HashMap<>();

		Locale locale = LocaleUtil.getSiteDefault();

		titleMap.put(locale, RandomTestUtil.randomString());

		Map<Locale, String> descriptionMap = new HashMap<>();

		descriptionMap.put(locale, RandomTestUtil.randomString());

		String[] categoryProperties = null;

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				groupId, TestPropsValues.getUserId());

		return AssetCategoryLocalServiceUtil.addCategory(
			TestPropsValues.getUserId(), groupId, parentCategoryId, titleMap,
			descriptionMap, vocabularyId, categoryProperties, serviceContext);
	}

	public static AssetTag addTag(long groupId) throws Exception {
		long userId = TestPropsValues.getUserId();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId, userId);

		return AssetTagLocalServiceUtil.addTag(
			userId, groupId, RandomTestUtil.randomString(), serviceContext);
	}

	public static AssetVocabulary addVocabulary(long groupId) throws Exception {
		long userId = TestPropsValues.getUserId();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId, userId);

		return AssetVocabularyLocalServiceUtil.addVocabulary(
			userId, groupId, RandomTestUtil.randomString(), serviceContext);
	}

	public static AssetVocabulary addVocabulary(
			long groupId, long classNameId, long classTypePK, boolean required)
		throws Exception {

		Map<Locale, String> titleMap = new HashMap<>();

		Locale locale = LocaleUtil.getSiteDefault();

		titleMap.put(locale, RandomTestUtil.randomString());

		Map<Locale, String> descriptionMap = new HashMap<>();

		descriptionMap.put(locale, RandomTestUtil.randomString());

		AssetVocabularySettingsHelper vocabularySettingsHelper =
			new AssetVocabularySettingsHelper();

		vocabularySettingsHelper.setClassNameIdsAndClassTypePKs(
			new long[] {classNameId}, new long[] {classTypePK},
			new boolean[] {required});
		vocabularySettingsHelper.setMultiValued(true);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				groupId, TestPropsValues.getUserId());

		AssetVocabulary vocabulary = AssetVocabularyServiceUtil.addVocabulary(
			groupId, RandomTestUtil.randomString(), titleMap, descriptionMap,
			vocabularySettingsHelper.toString(), serviceContext);

		return vocabulary;
	}

}