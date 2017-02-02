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

package com.liferay.portal.action;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.MathUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.JSONAction;
import com.liferay.ratings.kernel.model.RatingsStats;
import com.liferay.ratings.kernel.service.RatingsEntryServiceUtil;
import com.liferay.ratings.kernel.service.RatingsStatsLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 */
public class RateEntryAction extends JSONAction {

	@Override
	public String getJSON(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String className = getClassName(request);
		long classPK = getClassPK(request);
		double score = ParamUtil.getDouble(request, "score");

		if (score == -1) {
			RatingsEntryServiceUtil.deleteEntry(className, classPK);
		}
		else {
			RatingsEntryServiceUtil.updateEntry(className, classPK, score);
		}

		RatingsStats stats = RatingsStatsLocalServiceUtil.getStats(
			className, classPK);

		double averageScore = MathUtil.format(stats.getAverageScore(), 1, 1);

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		jsonObj.put("averageScore", averageScore);
		jsonObj.put("score", score);
		jsonObj.put("totalEntries", stats.getTotalEntries());
		jsonObj.put("totalScore", stats.getTotalScore());

		return jsonObj.toString();
	}

	protected String getClassName(HttpServletRequest request) {
		return ParamUtil.getString(request, "className");
	}

	protected long getClassPK(HttpServletRequest request) {
		return ParamUtil.getLong(request, "classPK");
	}

}