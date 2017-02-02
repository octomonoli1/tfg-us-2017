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

package com.liferay.portlet.usersadmin.atom;

import com.liferay.portal.atom.AtomPager;
import com.liferay.portal.atom.AtomUtil;
import com.liferay.portal.kernel.atom.AtomEntryContent;
import com.liferay.portal.kernel.atom.AtomRequestContext;
import com.liferay.portal.kernel.atom.BaseAtomCollectionAdapter;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.UserServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Igor Spasic
 */
public class UserAtomCollectionAdapter extends BaseAtomCollectionAdapter<User> {

	@Override
	public String getCollectionName() {
		return _COLLECTION_NAME;
	}

	@Override
	public List<String> getEntryAuthors(User user) {
		List<String> authors = new ArrayList<>();

		authors.add(user.getFullName());

		return authors;
	}

	@Override
	public AtomEntryContent getEntryContent(
		User user, AtomRequestContext atomRequestContext) {

		StringBundler content = new StringBundler();

		content.append(user.getScreenName());
		content.append(StringPool.NEW_LINE);
		content.append(user.getEmailAddress());
		content.append(StringPool.NEW_LINE);
		content.append(user.getFullName());
		content.append(StringPool.NEW_LINE);
		content.append(user.getJobTitle());
		content.append(StringPool.NEW_LINE);

		try {
			List<Address> userAddresses = user.getAddresses();

			for (Address address : userAddresses) {
				content.append(address.getStreet1());
				content.append(StringPool.NEW_LINE);
				content.append(address.getStreet2());
				content.append(StringPool.NEW_LINE);
				content.append(address.getStreet3());
				content.append(StringPool.NEW_LINE);
			}
		}
		catch (Exception e) {
		}

		return new AtomEntryContent(content.toString());
	}

	@Override
	public String getEntryId(User user) {
		return String.valueOf(user.getUserId());
	}

	@Override
	public String getEntrySummary(User user) {
		return user.getFullName();
	}

	@Override
	public String getEntryTitle(User user) {
		return user.getScreenName();
	}

	@Override
	public Date getEntryUpdated(User user) {
		return user.getModifiedDate();
	}

	@Override
	public String getFeedTitle(AtomRequestContext atomRequestContext) {
		String portletId = PortletProviderUtil.getPortletId(
			User.class.getName(), PortletProvider.Action.VIEW);

		return AtomUtil.createFeedTitleFromPortletName(
			atomRequestContext, portletId);
	}

	@Override
	protected User doGetEntry(
			String resourceName, AtomRequestContext atomRequestContext)
		throws Exception {

		long userId = GetterUtil.getLong(resourceName);

		return UserServiceUtil.getUserById(userId);
	}

	@Override
	protected Iterable<User> doGetFeedEntries(
			AtomRequestContext atomRequestContext)
		throws Exception {

		long groupId = atomRequestContext.getLongParameter("groupId");

		if (groupId > 0) {
			List<User> users = UserServiceUtil.getGroupUsers(groupId);

			return users;
		}

		long organizationId = atomRequestContext.getLongParameter(
			"organizationId");

		if (organizationId > 0) {
			List<User> users = UserServiceUtil.getOrganizationUsers(
				organizationId);

			return users;
		}

		long userGroupId = atomRequestContext.getLongParameter("userGroupId");

		if (userGroupId > 0) {
			List<User> users = UserServiceUtil.getUserGroupUsers(userGroupId);

			return users;
		}

		long companyId = CompanyThreadLocal.getCompanyId();

		if (companyId > 0) {
			int usersCount = UserServiceUtil.getCompanyUsersCount(companyId);

			AtomPager atomPager = new AtomPager(atomRequestContext, usersCount);

			AtomUtil.saveAtomPagerInRequest(atomRequestContext, atomPager);

			List<User> users = UserServiceUtil.getCompanyUsers(
				companyId, atomPager.getStart(), atomPager.getEnd() + 1);

			return users;
		}

		return Collections.emptyList();
	}

	private static final String _COLLECTION_NAME = "users";

}