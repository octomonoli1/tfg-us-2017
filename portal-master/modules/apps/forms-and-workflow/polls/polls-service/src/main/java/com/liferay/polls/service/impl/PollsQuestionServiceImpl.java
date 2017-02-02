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

package com.liferay.polls.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.service.base.PollsQuestionServiceBaseImpl;
import com.liferay.polls.service.permission.PollsQuestionPermissionChecker;
import com.liferay.polls.service.permission.PollsResourcePermissionChecker;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 */
@ProviderType
public class PollsQuestionServiceImpl extends PollsQuestionServiceBaseImpl {

	@Override
	public PollsQuestion addQuestion(
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			List<PollsChoice> choices, ServiceContext serviceContext)
		throws PortalException {

		PollsResourcePermissionChecker.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_QUESTION);

		return pollsQuestionLocalService.addQuestion(
			getUserId(), titleMap, descriptionMap, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, choices, serviceContext);
	}

	@Override
	public void deleteQuestion(long questionId) throws PortalException {
		PollsQuestionPermissionChecker.check(
			getPermissionChecker(), questionId, ActionKeys.DELETE);

		pollsQuestionLocalService.deleteQuestion(questionId);
	}

	@Override
	public PollsQuestion getQuestion(long questionId) throws PortalException {
		PollsQuestionPermissionChecker.check(
			getPermissionChecker(), questionId, ActionKeys.VIEW);

		return pollsQuestionLocalService.getQuestion(questionId);
	}

	@Override
	public PollsQuestion updateQuestion(
			long questionId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, List<PollsChoice> choices,
			ServiceContext serviceContext)
		throws PortalException {

		PollsQuestionPermissionChecker.check(
			getPermissionChecker(), questionId, ActionKeys.UPDATE);

		return pollsQuestionLocalService.updateQuestion(
			getUserId(), questionId, titleMap, descriptionMap,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, choices,
			serviceContext);
	}

}