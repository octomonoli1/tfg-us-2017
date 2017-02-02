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

package com.liferay.users.admin.demo.data.creator.internal;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.users.admin.demo.data.creator.BasicUserDemoDataCreator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
public abstract class BaseUserDemoDataCreator
	implements BasicUserDemoDataCreator {

	public User createBaseUser(long companyId, String emailAddress)
		throws PortalException {

		User user = userLocalService.fetchUserByEmailAddress(
			companyId, emailAddress);

		if (user != null) {
			return user;
		}

		String[] fullNameArray = getFullNameArray(emailAddress);

		String firstName = fullNameArray[0];
		String lastName = fullNameArray[1];

		boolean autoPassword = false;
		String password1 = "test";
		String password2 = "test";
		long facebookId = 0;
		String openId = StringPool.BLANK;
		Locale locale = LocaleUtil.SPAIN;
		String middleName = StringPool.BLANK;
		long prefixId = 0;
		long suffixId = 0;
		boolean male = true;
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = StringUtil.randomString();
		long[] groupIds = null;
		long[] organizationIds = null;
		long[] roleIds = null;
		long[] userGroupIds = null;
		boolean sendMail = false;

		user = userLocalService.addUser(
			UserConstants.USER_ID_DEFAULT, companyId, autoPassword, password1,
			password2, true, StringPool.BLANK, emailAddress, facebookId, openId,
			locale, firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
			organizationIds, roleIds, userGroupIds, sendMail,
			new ServiceContext());

		_userIds.add(user.getUserId());

		return user;
	}

	@Override
	public void delete() throws PortalException {
		for (long userId : _userIds) {
			userLocalService.deleteUser(userId);
		}
	}

	protected String[] getFullNameArray(String emailAddress) {
		String emailAccountName = emailAddress.substring(
			0, emailAddress.indexOf(StringPool.AT));

		String[] fullNameArray = StringUtil.split(
			emailAccountName, StringPool.PERIOD);

		String firstName = StringUtil.randomString();
		String lastName = StringUtil.randomString();

		if (fullNameArray.length > 0) {
			firstName = StringUtil.upperCaseFirstLetter(fullNameArray[0]);
		}

		if (fullNameArray.length > 1) {
			lastName = StringUtil.upperCaseFirstLetter(fullNameArray[1]);
		}

		return new String[] {firstName, lastName};
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	protected UserLocalService userLocalService;

	private final List<Long> _userIds = new ArrayList();

}