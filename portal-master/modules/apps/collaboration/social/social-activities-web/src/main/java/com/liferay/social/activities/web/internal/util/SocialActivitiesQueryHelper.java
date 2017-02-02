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

package com.liferay.social.activities.web.internal.util;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.social.kernel.model.SocialActivitySet;
import com.liferay.social.kernel.model.SocialRelationConstants;
import com.liferay.social.kernel.service.SocialActivitySetLocalService;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(immediate = true, service = SocialActivitiesQueryHelper.class)
public class SocialActivitiesQueryHelper {

	public List<SocialActivitySet> getSocialActivitySets(
		Group group, Layout layout, Scope scope, int start, int end) {

		if (scope == Scope.ALL) {
			if (!group.isUser()) {
				return _socialActivitySetLocalService.getGroupActivitySets(
					group.getGroupId(), start, end);
			}

			return _socialActivitySetLocalService.getUserActivitySets(
				group.getClassPK(), start, end);
		}
		else if (group.isOrganization()) {
			return _socialActivitySetLocalService.getOrganizationActivitySets(
				group.getOrganizationId(), start, end);
		}
		else if (!group.isUser()) {
			return _socialActivitySetLocalService.getGroupActivitySets(
				group.getGroupId(), start, end);
		}
		else if (layout.isPublicLayout() || (scope == Scope.ME)) {
			return _socialActivitySetLocalService.getUserActivitySets(
				group.getClassPK(), start, end);
		}
		else if (scope == Scope.CONNECTIONS) {
			return _socialActivitySetLocalService.getRelationActivitySets(
				group.getClassPK(), SocialRelationConstants.TYPE_BI_CONNECTION,
				start, end);
		}
		else if (scope == Scope.FOLLOWING) {
			return _socialActivitySetLocalService.getRelationActivitySets(
				group.getClassPK(), SocialRelationConstants.TYPE_UNI_FOLLOWER,
				start, end);
		}
		else if (scope == Scope.MY_SITES) {
			return _socialActivitySetLocalService.getUserGroupsActivitySets(
				group.getClassPK(), start, end);
		}
		else {
			return Collections.emptyList();
		}
	}

	public enum Scope {

		ALL("all"), CONNECTIONS("connections"), FOLLOWING("following"),
		ME("me"), MY_SITES("my-sites");

		public static Scope fromValue(String value) {
			for (Scope scope : values()) {
				if (value.equals(scope.getValue())) {
					return scope;
				}
			}

			throw new IllegalArgumentException(value);
		}

		public String getValue() {
			return _value;
		}

		private Scope(String value) {
			_value = value;
		}

		private final String _value;

	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	protected void setSocialActivitySetLocalService(
		SocialActivitySetLocalService socialActivitySetLocalService) {

		_socialActivitySetLocalService = socialActivitySetLocalService;
	}

	private GroupLocalService _groupLocalService;
	private SocialActivitySetLocalService _socialActivitySetLocalService;

}