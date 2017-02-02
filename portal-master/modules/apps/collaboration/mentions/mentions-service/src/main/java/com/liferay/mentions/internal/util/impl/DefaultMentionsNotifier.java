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

package com.liferay.mentions.internal.util.impl;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.mentions.constants.MentionsConstants;
import com.liferay.mentions.util.MentionsNotifier;
import com.liferay.mentions.util.MentionsUserFinder;
import com.liferay.mentions.web.constants.MentionsPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.social.kernel.util.SocialInteractionsConfiguration;
import com.liferay.social.kernel.util.SocialInteractionsConfigurationUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component
public class DefaultMentionsNotifier implements MentionsNotifier {

	@Override
	public void notify(
			long userId, long groupId, String title, String content,
			String className, long classPK,
			LocalizedValuesMap subjectLocalizedValuesMap,
			LocalizedValuesMap bodyLocalizedValuesMap,
			ServiceContext serviceContext)
		throws PortalException {

		String[] mentionedUsersScreenNames = getMentionedUsersScreenNames(
			userId, content);

		if (ArrayUtil.isEmpty(mentionedUsersScreenNames)) {
			return;
		}

		User user = _userLocalService.getUser(userId);

		String contentURL = (String)serviceContext.getAttribute("contentURL");

		String messageUserEmailAddress = PortalUtil.getUserEmailAddress(userId);
		String messageUserName = PortalUtil.getUserName(
			userId, StringPool.BLANK);

		String fromName = PrefsPropsUtil.getString(
			user.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_NAME);
		String fromAddress = PrefsPropsUtil.getString(
			user.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setLocalizedBodyMap(
			LocalizationUtil.getMap(bodyLocalizedValuesMap));
		subscriptionSender.setClassName(className);
		subscriptionSender.setClassPK(classPK);
		subscriptionSender.setCompanyId(user.getCompanyId());
		subscriptionSender.setContextAttribute("[$CONTENT$]", content, false);
		subscriptionSender.setContextAttributes(
			"[$ASSET_ENTRY_NAME$]",
			getAssetEntryName(className, serviceContext), "[$USER_ADDRESS$]",
			messageUserEmailAddress, "[$USER_NAME$]", messageUserName,
			"[$CONTENT_URL$]", contentURL);
		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(title);
		subscriptionSender.setEntryURL(contentURL);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("mb_discussion", classPK);
		subscriptionSender.setNotificationType(
			MentionsConstants.NOTIFICATION_TYPE_MENTION);
		subscriptionSender.setPortletId(MentionsPortletKeys.MENTIONS);
		subscriptionSender.setScopeGroupId(groupId);
		subscriptionSender.setServiceContext(serviceContext);
		subscriptionSender.setLocalizedSubjectMap(
			LocalizationUtil.getMap(subjectLocalizedValuesMap));

		for (int i = 0; i < mentionedUsersScreenNames.length; i++) {
			String mentionedUserScreenName = mentionedUsersScreenNames[i];

			User mentionedUser = _userLocalService.fetchUserByScreenName(
				user.getCompanyId(), mentionedUserScreenName);

			if (mentionedUser == null) {
				continue;
			}

			subscriptionSender.addRuntimeSubscribers(
				mentionedUser.getEmailAddress(), mentionedUser.getFullName());
		}

		subscriptionSender.flushNotificationsAsync();
	}

	protected String getAssetEntryName(
		String className, ServiceContext serviceContext) {

		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		if (assetRendererFactory != null) {
			return assetRendererFactory.getTypeName(serviceContext.getLocale());
		}

		return StringPool.BLANK;
	}

	protected String[] getMentionedUsersScreenNames(long userId, String content)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		SocialInteractionsConfiguration socialInteractionsConfiguration =
			SocialInteractionsConfigurationUtil.
				getSocialInteractionsConfiguration(
					user.getCompanyId(), MentionsPortletKeys.MENTIONS);

		Set<String> mentionedUsersScreenNames = new HashSet<>();

		for (String mentionedUserScreenName : MentionsMatcher.match(content)) {
			List<User> users = _mentionsUserFinder.getUsers(
				user.getCompanyId(), userId, mentionedUserScreenName,
				socialInteractionsConfiguration);

			for (User curUser : users) {
				if (mentionedUserScreenName.equals(curUser.getScreenName())) {
					mentionedUsersScreenNames.add(mentionedUserScreenName);

					break;
				}
			}
		}

		return mentionedUsersScreenNames.toArray(
			new String[mentionedUsersScreenNames.size()]);
	}

	@Reference(unbind = "-")
	protected void setMentionsUserFinder(
		MentionsUserFinder mentionsUserFinder) {

		_mentionsUserFinder = mentionsUserFinder;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private MentionsUserFinder _mentionsUserFinder;
	private UserLocalService _userLocalService;

}