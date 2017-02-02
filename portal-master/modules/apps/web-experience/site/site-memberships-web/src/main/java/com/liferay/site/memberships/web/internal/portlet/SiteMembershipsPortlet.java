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

package com.liferay.site.memberships.web.internal.portlet;

import com.liferay.portal.kernel.exception.MembershipRequestCommentsException;
import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.MembershipRequest;
import com.liferay.portal.kernel.model.MembershipRequestConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupGroupRole;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.membershippolicy.MembershipPolicyException;
import com.liferay.portal.kernel.service.MembershipRequestService;
import com.liferay.portal.kernel.service.OrganizationService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserGroupGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserGroupGroupRoleService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleService;
import com.liferay.portal.kernel.service.UserGroupService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.liveusers.LiveUsers;
import com.liferay.site.memberships.web.internal.constants.SiteMembershipsPortletKeys;
import com.liferay.site.memberships.web.internal.display.context.SiteMembershipsDisplayContext;
import com.liferay.users.admin.kernel.util.UsersAdmin;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-communities",
		"com.liferay.portlet.icon=/icons/site_memberships_admin.png",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.system=true",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Site Memberships Admin",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + SiteMembershipsPortletKeys.SITE_MEMBERSHIPS_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class SiteMembershipsPortlet extends MVCPortlet {

	public void addGroupOrganizations(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Group group = _getGroup(actionRequest, actionResponse);

		long[] addOrganizationIds = ParamUtil.getLongValues(
			actionRequest, "rowIds");

		_organizationService.addGroupOrganizations(
			group.getGroupId(), addOrganizationIds);
	}

	public void addGroupUserGroups(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Group group = _getGroup(actionRequest, actionResponse);

		long[] addUserGroupIds = ParamUtil.getLongValues(
			actionRequest, "rowIds");

		_userGroupService.addGroupUserGroups(
			group.getGroupId(), addUserGroupIds);
	}

	public void addGroupUsers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Group group = _getGroup(actionRequest, actionResponse);

		long groupId = group.getGroupId();

		long[] addUserIds = ParamUtil.getLongValues(actionRequest, "rowIds");

		addUserIds = filterAddUserIds(groupId, addUserIds);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		_userService.addGroupUsers(groupId, addUserIds, serviceContext);

		LiveUsers.joinGroup(group.getCompanyId(), groupId, addUserIds);
	}

	public void changeDisplayStyle(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		hideDefaultSuccessMessage(actionRequest);

		String displayStyle = ParamUtil.getString(
			actionRequest, "displayStyle");

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(actionRequest);

		portalPreferences.setValue(
			SiteMembershipsPortletKeys.SITE_MEMBERSHIPS_ADMIN, "display-style",
			displayStyle);
	}

	public void deleteGroupOrganizations(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Group group = _getGroup(actionRequest, actionResponse);

		long[] removeOrganizationIds = null;

		long removeOrganizationId = ParamUtil.getLong(
			actionRequest, "removeOrganizationId");

		if (removeOrganizationId > 0) {
			removeOrganizationIds = new long[] {removeOrganizationId};
		}
		else {
			removeOrganizationIds = ParamUtil.getLongValues(
				actionRequest, "rowIds");
		}

		_organizationService.unsetGroupOrganizations(
			group.getGroupId(), removeOrganizationIds);
	}

	public void deleteGroupUserGroups(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Group group = _getGroup(actionRequest, actionResponse);

		long[] removeUserGroupIds = null;

		long removeUserGroupId = ParamUtil.getLong(
			actionRequest, "removeUserGroupId");

		if (removeUserGroupId > 0) {
			removeUserGroupIds = new long[] {removeUserGroupId};
		}
		else {
			removeUserGroupIds = ParamUtil.getLongValues(
				actionRequest, "rowIds");
		}

		_userGroupService.unsetGroupUserGroups(
			group.getGroupId(), removeUserGroupIds);
	}

	public void deleteGroupUsers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Group group = _getGroup(actionRequest, actionResponse);

		long groupId = group.getGroupId();

		long[] removeUserIds = null;

		long removeUserId = ParamUtil.getLong(actionRequest, "removeUserId");

		if (removeUserId > 0) {
			removeUserIds = new long[] {removeUserId};
		}
		else {
			removeUserIds = ParamUtil.getLongValues(actionRequest, "rowIds");
		}

		removeUserIds = filterRemoveUserIds(groupId, removeUserIds);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		_userService.unsetGroupUsers(groupId, removeUserIds, serviceContext);

		LiveUsers.leaveGroup(group.getCompanyId(), groupId, removeUserIds);
	}

	public void editUserGroupGroupRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Group group = _getGroup(actionRequest, actionResponse);

		long userGroupId = ParamUtil.getLong(actionRequest, "userGroupId");

		long[] roleIds = ParamUtil.getLongValues(actionRequest, "rowIds");

		List<UserGroupGroupRole> userGroupGroupRoles =
			_userGroupGroupRoleLocalService.getUserGroupGroupRoles(
				userGroupId, group.getGroupId());

		List<Long> curRoleIds = ListUtil.toList(
			userGroupGroupRoles, UsersAdmin.USER_GROUP_GROUP_ROLE_ID_ACCESSOR);

		List<Long> removeRoleIds = new ArrayList<>();

		for (long roleId : curRoleIds) {
			if (!ArrayUtil.contains(roleIds, roleId)) {
				removeRoleIds.add(roleId);
			}
		}

		_userGroupGroupRoleService.addUserGroupGroupRoles(
			userGroupId, group.getGroupId(), roleIds);
		_userGroupGroupRoleService.deleteUserGroupGroupRoles(
			userGroupId, group.getGroupId(),
			ArrayUtil.toLongArray(removeRoleIds));
	}

	public void editUserGroupRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		User user = PortalUtil.getSelectedUser(actionRequest, false);

		if (user == null) {
			return;
		}

		Group group = _getGroup(actionRequest, actionResponse);

		long[] roleIds = ParamUtil.getLongValues(actionRequest, "rowIds");

		List<UserGroupRole> userGroupRoles =
			_userGroupRoleLocalService.getUserGroupRoles(
				user.getUserId(), group.getGroupId());

		List<Long> curRoleIds = ListUtil.toList(
			userGroupRoles, UsersAdmin.USER_GROUP_ROLE_ID_ACCESSOR);

		List<Long> removeRoleIds = new ArrayList<>();

		for (long roleId : curRoleIds) {
			if (!ArrayUtil.contains(roleIds, roleId)) {
				removeRoleIds.add(roleId);
			}
		}

		_userGroupRoleService.updateUserGroupRoles(
			user.getUserId(), group.getGroupId(), roleIds,
			ArrayUtil.toLongArray(removeRoleIds));
	}

	public void editUserGroupsSiteRoles(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Group group = _getGroup(actionRequest, actionResponse);

		long[] userGroupIds = ParamUtil.getLongValues(actionRequest, "rowIds");

		long[] roleIds = ParamUtil.getLongValues(actionRequest, "rowIdsRole");

		for (long roleId : roleIds) {
			_userGroupGroupRoleService.addUserGroupGroupRoles(
				userGroupIds, group.getGroupId(), roleId);
		}
	}

	public void editUsersSiteRoles(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Group group = _getGroup(actionRequest, actionResponse);

		long[] userIds = ParamUtil.getLongValues(actionRequest, "rowIds");

		long[] roleIds = ParamUtil.getLongValues(actionRequest, "rowIdsRole");

		for (long roleId : roleIds) {
			_userGroupRoleService.addUserGroupRoles(
				userIds, group.getGroupId(), roleId);
		}
	}

	public void replyMembershipRequest(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Group group = _getGroup(actionRequest, actionResponse);

		long membershipRequestId = ParamUtil.getLong(
			actionRequest, "membershipRequestId");

		long statusId = ParamUtil.getLong(actionRequest, "statusId");
		String replyComments = ParamUtil.getString(
			actionRequest, "replyComments");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		_membershipRequestService.updateStatus(
			membershipRequestId, replyComments, statusId, serviceContext);

		if (statusId == MembershipRequestConstants.STATUS_APPROVED) {
			MembershipRequest membershipRequest =
				_membershipRequestService.getMembershipRequest(
					membershipRequestId);

			LiveUsers.joinGroup(
				group.getCompanyId(), membershipRequest.getGroupId(),
				new long[] {membershipRequest.getUserId()});
		}

		SessionMessages.add(actionRequest, "membershipReplySent");

		sendRedirect(actionRequest, actionResponse);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (SessionErrors.contains(
				renderRequest, NoSuchGroupException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, NoSuchRoleException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, PrincipalException.getNestedClasses())) {

			include("/error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	protected long[] filterAddUserIds(long groupId, long[] userIds)
		throws Exception {

		Set<Long> filteredUserIds = new HashSet<>(userIds.length);

		for (long userId : userIds) {
			if (!_userLocalService.hasGroupUser(groupId, userId)) {
				filteredUserIds.add(userId);
			}
		}

		return ArrayUtil.toArray(
			filteredUserIds.toArray(new Long[filteredUserIds.size()]));
	}

	protected long[] filterRemoveUserIds(long groupId, long[] userIds)
		throws Exception {

		Set<Long> filteredUserIds = new HashSet<>(userIds.length);

		for (long userId : userIds) {
			if (_userLocalService.hasGroupUser(groupId, userId)) {
				filteredUserIds.add(userId);
			}
		}

		return ArrayUtil.toArray(
			filteredUserIds.toArray(new Long[filteredUserIds.size()]));
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof MembershipPolicyException ||
			cause instanceof MembershipRequestCommentsException ||
			cause instanceof NoSuchGroupException ||
			cause instanceof NoSuchRoleException ||
			cause instanceof PrincipalException ||
			super.isSessionErrorException(cause)) {

			return true;
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setMembershipRequestService(
		MembershipRequestService membershipRequestService) {

		_membershipRequestService = membershipRequestService;
	}

	@Reference(unbind = "-")
	protected void setOrganizationService(
		OrganizationService organizationService) {

		_organizationService = organizationService;
	}

	@Reference(unbind = "-")
	protected void setUserGroupGroupRoleLocalService(
		UserGroupGroupRoleLocalService userGroupGroupRoleLocalService) {

		_userGroupGroupRoleLocalService = userGroupGroupRoleLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserGroupGroupRoleService(
		UserGroupGroupRoleService userGroupGroupRoleService) {

		_userGroupGroupRoleService = userGroupGroupRoleService;
	}

	@Reference(unbind = "-")
	protected void setUserGroupRoleLocalService(
		UserGroupRoleLocalService userGroupRoleLocalService) {

		_userGroupRoleLocalService = userGroupRoleLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserGroupRoleService(
		UserGroupRoleService userGroupRoleService) {

		_userGroupRoleService = userGroupRoleService;
	}

	@Reference(unbind = "-")
	protected void setUserGroupService(UserGroupService userGroupService) {
		_userGroupService = userGroupService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserService(UserService userService) {
		_userService = userService;
	}

	private Group _getGroup(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortalException {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		LiferayPortletResponse liferayPortletResponse =
			PortalUtil.getLiferayPortletResponse(portletResponse);

		SiteMembershipsDisplayContext siteMembershipsDisplayContext =
			new SiteMembershipsDisplayContext(request, liferayPortletResponse);

		return siteMembershipsDisplayContext.getGroup();
	}

	private MembershipRequestService _membershipRequestService;
	private OrganizationService _organizationService;
	private UserGroupGroupRoleLocalService _userGroupGroupRoleLocalService;
	private UserGroupGroupRoleService _userGroupGroupRoleService;
	private UserGroupRoleLocalService _userGroupRoleLocalService;
	private UserGroupRoleService _userGroupRoleService;
	private UserGroupService _userGroupService;
	private UserLocalService _userLocalService;
	private UserService _userService;

}