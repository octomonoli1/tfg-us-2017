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

package com.liferay.portlet.ratings.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.model.BlogsStatsUser;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.social.SocialActivityManagerUtil;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portlet.ratings.service.base.RatingsEntryLocalServiceBaseImpl;
import com.liferay.ratings.kernel.exception.EntryScoreException;
import com.liferay.ratings.kernel.model.RatingsEntry;
import com.liferay.ratings.kernel.model.RatingsStats;
import com.liferay.social.kernel.model.SocialActivityConstants;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Zsolt Berentey
 */
public class RatingsEntryLocalServiceImpl
	extends RatingsEntryLocalServiceBaseImpl {

	@Override
	public void deleteEntry(long userId, String className, long classPK)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(className);

		RatingsEntry entry = ratingsEntryPersistence.fetchByU_C_C(
			userId, classNameId, classPK);

		ratingsEntryLocalService.deleteEntry(entry, userId, className, classPK);
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public void deleteEntry(
			RatingsEntry entry, long userId, String className, long classPK)
		throws PortalException {

		// Entry

		if (entry == null) {
			return;
		}

		long classNameId = classNameLocalService.getClassNameId(className);

		double oldScore = entry.getScore();

		ratingsEntryPersistence.removeByU_C_C(userId, classNameId, classPK);

		// Stats

		RatingsStats stats = ratingsStatsLocalService.getStats(
			className, classPK);

		int totalEntries = stats.getTotalEntries() - 1;
		double totalScore = stats.getTotalScore() - oldScore;
		double averageScore = 0;

		if (totalEntries > 0) {
			averageScore = totalScore / totalEntries;
		}

		stats.setTotalEntries(totalEntries);
		stats.setTotalScore(totalScore);
		stats.setAverageScore(averageScore);

		ratingsStatsPersistence.update(stats);

		// Indexer

		reindex(stats);
	}

	@Override
	public RatingsEntry fetchEntry(
		long userId, String className, long classPK) {

		long classNameId = classNameLocalService.getClassNameId(className);

		return ratingsEntryPersistence.fetchByU_C_C(
			userId, classNameId, classPK);
	}

	@Override
	public List<RatingsEntry> getEntries(
		long userId, String className, List<Long> classPKs) {

		long classNameId = classNameLocalService.getClassNameId(className);

		return ratingsEntryFinder.findByU_C_C(userId, classNameId, classPKs);
	}

	@Override
	public List<RatingsEntry> getEntries(String className, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(className);

		return ratingsEntryPersistence.findByC_C(classNameId, classPK);
	}

	@Override
	public List<RatingsEntry> getEntries(
		String className, long classPK, double score) {

		long classNameId = classNameLocalService.getClassNameId(className);

		return ratingsEntryPersistence.findByC_C_S(classNameId, classPK, score);
	}

	@Override
	public int getEntriesCount(String className, long classPK, double score) {
		long classNameId = classNameLocalService.getClassNameId(className);

		return ratingsEntryPersistence.countByC_C_S(
			classNameId, classPK, score);
	}

	@Override
	public RatingsEntry getEntry(long userId, String className, long classPK)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(className);

		return ratingsEntryPersistence.findByU_C_C(
			userId, classNameId, classPK);
	}

	@Override
	public RatingsEntry updateEntry(
			long userId, String className, long classPK, double score,
			ServiceContext serviceContext)
		throws PortalException {

		// Entry

		boolean newEntry = false;

		long classNameId = classNameLocalService.getClassNameId(className);
		double oldScore = 0;

		validate(score);

		RatingsEntry entry = ratingsEntryPersistence.fetchByU_C_C(
			userId, classNameId, classPK);

		if (entry != null) {
			oldScore = entry.getScore();

			entry.setScore(score);

			ratingsEntryPersistence.update(entry);

			// Stats

			RatingsStats stats = ratingsStatsLocalService.getStats(
				className, classPK);

			stats.setTotalScore(stats.getTotalScore() - oldScore + score);
			stats.setAverageScore(
				stats.getTotalScore() / stats.getTotalEntries());

			ratingsStatsPersistence.update(stats);

			// Indexer

			reindex(stats);
		}
		else {
			newEntry = true;

			User user = userPersistence.findByPrimaryKey(userId);

			long entryId = counterLocalService.increment();

			entry = ratingsEntryPersistence.create(entryId);

			entry.setCompanyId(user.getCompanyId());
			entry.setUserId(user.getUserId());
			entry.setUserName(user.getFullName());
			entry.setClassNameId(classNameId);
			entry.setClassPK(classPK);
			entry.setScore(score);

			ratingsEntryPersistence.update(entry);

			// Stats

			RatingsStats stats = ratingsStatsLocalService.getStats(
				className, classPK);

			stats.setTotalEntries(stats.getTotalEntries() + 1);
			stats.setTotalScore(stats.getTotalScore() + score);
			stats.setAverageScore(
				stats.getTotalScore() / stats.getTotalEntries());

			ratingsStatsPersistence.update(stats);

			// Indexer

			reindex(stats);
		}

		// Blogs entry

		if (className.equals(BlogsEntry.class.getName())) {
			BlogsEntry blogsEntry = blogsEntryPersistence.findByPrimaryKey(
				classPK);

			BlogsStatsUser blogsStatsUser =
				blogsStatsUserLocalService.getStatsUser(
					blogsEntry.getGroupId(), blogsEntry.getUserId());

			int ratingsTotalEntries = blogsStatsUser.getRatingsTotalEntries();
			double ratingsTotalScore = blogsStatsUser.getRatingsTotalScore();
			double ratingsAverageScore =
				blogsStatsUser.getRatingsAverageScore();

			if (newEntry) {
				ratingsTotalEntries++;
				ratingsTotalScore += score;
			}
			else {
				ratingsTotalScore = ratingsTotalScore - oldScore + score;
			}

			ratingsAverageScore = ratingsTotalScore / ratingsTotalEntries;

			blogsStatsUser.setRatingsTotalEntries(ratingsTotalEntries);
			blogsStatsUser.setRatingsTotalScore(ratingsTotalScore);
			blogsStatsUser.setRatingsAverageScore(ratingsAverageScore);

			blogsStatsUserPersistence.update(blogsStatsUser);
		}

		// Social

		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
			className, classPK);

		if (assetEntry != null) {
			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

			extraDataJSONObject.put("title", assetEntry.getTitle());

			SocialActivityManagerUtil.addActivity(
				userId, assetEntry, SocialActivityConstants.TYPE_ADD_VOTE,
				extraDataJSONObject.toString(), 0);
		}

		return entry;
	}

	protected void reindex(RatingsStats stats) throws PortalException {
		String className = PortalUtil.getClassName(stats.getClassNameId());

		Indexer<?> indexer = IndexerRegistryUtil.nullSafeGetIndexer(className);

		indexer.reindex(className, stats.getClassPK());
	}

	protected void validate(double score) throws PortalException {
		if ((score > 1) || (score < 0)) {
			throw new EntryScoreException(
				"Score " + score + " is not a value between 0 and 1");
		}
	}

}